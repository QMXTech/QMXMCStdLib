package com.qmxtech.qmxmcstdlib.computers.ctif.util;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// PaletteGeneratorKMeans.java
// Matthew J. Schultz (Korynkai) | Created : 17AUG19 | Last Modified : 19AUG19 by Matthew J. Schultz (Korynkai)
// Version : 0.0.1
// This is a source file for 'QMXMCStdLib'; it defines a palette generator for ChenThread Image Format using K-Means Clustering vector quantization.
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Original code Copyright (C) 2016 Adrian Siekierka (AsieKierka), licensed under MIT:
//
// Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in
// the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
// the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
//
// The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
//
// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
// FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
// IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Original code may be found at https://github.com/ChenThread/ctif.git
//
// The ComputerCraft code has been removed from CTIF for use within QMXMCStdlib as QMX has decided to favor OpenComputers and consider ComputerCraft deprecated.
//
// This code, as refactored and encapsulated within QMXMCStdLib, is hereby sublicensed GPLv3, with attribution to Adrian Siekierka (AsieKierka), granted under
// the terms and conditions of the MIT license.
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Copyright (C) 2019 QuantuMatriX Software, a QuantuMatriX Technologies Cooperative Partnership.
//
// This file is part of 'QMXMCStdLib'.
//
// 'QMXMCStdLib' is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software
// Foundation, either version 3 of the License, or (at your option) any later version.
//
// 'QMXMCStdLib' is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
// FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License for more details.
//
// You should have received a copy of the GNU General Public License along with 'QMXMCStdLib'.  If not, see <http://www.gnu.org/licenses/>.
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Imports
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

import com.qmxtech.qmxmcstdlib.computers.ctif.colorspace.Colorspace;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// The 'PaletteGeneratorKMeans' Class
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

// TODO: Flagged for rebase; further refactoring

@SuppressWarnings( "WeakerAccess" )
public class PaletteGeneratorKMeans
{

	// Public Types

		public class Worker implements Runnable
		{
			public Result result;

			@Override
			public void run()
			{
				result = generateKMeans();
			}
		}

	// Public Constructor

		@SuppressWarnings( "UnnecessaryLocalVariable" )
		public PaletteGeneratorKMeans( BufferedImage image, Color[] base, int colors, int samplingRes, Colorspace colorspace )
		{
			this.colors = colors;
			this.colorspace = colorspace;
			this.image = image;
			this.base = base;
			this.centroids = new float[ base.length ][];
			int jx, jy, i;
			double bestError = Float.MAX_VALUE;
			float[] key;
			int bestCentroid = 0;

			if( samplingRes > 0 )
			{
				int maximum = samplingRes;
				float stepX = ( float ) ( image.getWidth() / maximum );
				float stepY = ( float ) ( image.getHeight() / maximum );
				int stepIX = ( int ) Math.ceil( stepX );
				int stepIY = ( int ) Math.ceil( stepY );

				for( jy = 0; jy < maximum; jy++ )
				{
					for( jx = 0; jx < ( maximum * 2 ); jx++ )
					{
						i = image.getRGB( ( random.nextInt( stepIX ) + ( int ) ( ( jx % maximum ) * stepX ) ), ( random.nextInt( stepIY ) + ( int ) ( jy * stepY ) ) );

						if( !pointsAdded.containsKey( i ) )
						{
							key = colorspace.fromRGB( i );
							pointsAdded.put( i, key );
							pointsWeight.put( key, 1 );
						}
						else
						{
							pointsWeight.put( pointsAdded.get( i ), ( pointsWeight.get( pointsAdded.get( i ) ) + 1 ) );
						}
					}
				}
			}
			else
			{
				for( int x : Utils.getRGB( image ) )
				{
					if( !pointsAdded.containsKey( x ) )
					{
						key = colorspace.fromRGB( x );
						pointsAdded.put( x, key );
						pointsWeight.put( key, 1 );
					}
					else
					{
						pointsWeight.put( pointsAdded.get( x ), ( pointsWeight.get( pointsAdded.get( x ) ) + 1 ) );
					}
				}
			}

			for( i = colors; i < centroids.length; i++ )
				centroids[ i ] = colorspace.fromRGB( base[ i ].getRGB() );

			for( Map.Entry< float[], Integer > weight : pointsWeight.entrySet() )
			{
				for( i = colors; i < centroids.length; i++ )
				{
					double err = Utils.getColorDistanceSq( weight.getKey(), centroids[ i ] );
					if( err < bestError )
					{
						bestError = err;
						bestCentroid = i;

						if( err == 0 )
							break;
					}
				}

				knownBestError.put( weight.getKey(), bestError );
				knownBestCentroid.put( weight.getKey(), bestCentroid );
			}
		}

		public Color[] generate( int threads )
		{
			Result bestResult = null;
			Worker[] workers = new Worker[ 10 ];
			ExecutorService executorService = Executors.newFixedThreadPool( threads );
			int i;

			for( i = 0; i < workers.length; i++ )
			{
				workers[ i ] = new Worker();
				executorService.submit( workers[ i ] );
			}

			executorService.shutdown();

			try
			{
				executorService.awaitTermination( Long.MAX_VALUE, TimeUnit.SECONDS );
			}
			catch( InterruptedException e )
			{
				e.printStackTrace();
			}

			for( i = 0; i < workers.length; i++ )
			{
				Result result = workers[ i ].result;

				if( ( bestResult == null ) || ( bestResult.error > result.error ) )
					bestResult = result;
			}

			return bestResult.colors;
		}

	// Protected Types

		protected static class Result
		{
			protected Color[] colors;
			protected double error;

			public Result( Color[] colors, double error )
			{
				this.colors = colors;
				this.error = error;
			}
		}

	// Protected Fields

		protected int colors;
		protected BufferedImage image;
		protected Color[] base;
		protected Random random = new Random();
		protected Map< Integer, float[] > pointsAdded = new HashMap<>();
		protected Map< float[], Integer > pointsWeight = new HashMap<>();
		protected float[][] centroids;
		protected Map< float[], Double > knownBestError = new HashMap<>();
		protected Map< float[], Integer > knownBestCentroid = new HashMap<>();
		protected Colorspace colorspace;


	// Protected Methods

		protected Result generateKMeans()
		{
			boolean changed;
			int i, k, reps, bestCentroid, mul;
			double bestError, err;
			double totalError = 0d;
			float n0, n1, n2;
			float[][] means;
			int[] meanDivs;
			Color[] out;

			for( i = 0; i < colors; i++ )
				centroids[ i ] = colorspace.fromRGB( image.getRGB( random.nextInt( image.getWidth() ), random.nextInt( image.getHeight() ) ) );

			for( reps = 0; reps < 128; reps++ )
			{
				means = new float[ centroids.length ][ 3 ];
				meanDivs = new int[ centroids.length ];

				for( Map.Entry< float[], Integer > weight : pointsWeight.entrySet() )
				{
					bestError = knownBestError.get( weight.getKey() );
					bestCentroid = knownBestCentroid.get( weight.getKey() );
					mul = weight.getValue();

					for( i = 0; i < colors; i++ )
					{
						err = Utils.getColorDistanceSq( weight.getKey(), centroids[ i ] );
						if( err < bestError )
						{
							bestError = err;
							bestCentroid = i;

							if( err == 0 )
								break;
						}
					}

					totalError += ( bestError * mul );
					means[ bestCentroid ][ 0 ] += ( weight.getKey()[ 0 ] * mul );
					means[ bestCentroid ][ 1 ] += ( weight.getKey()[ 1 ] * mul );
					means[ bestCentroid ][ 2 ] += ( weight.getKey()[ 2 ] * mul );
					meanDivs[ bestCentroid ] += mul;
				}

				changed = false;

				for( i = 0; i < colors; i++ )
				{
					if( meanDivs[ i ] > 0 )
					{
						n0 = ( means[ i ][ 0 ] / meanDivs[ i ] );
						n1 = ( means[ i ][ 1 ] / meanDivs[ i ] );
						n2 = ( means[ i ][ 2 ] / meanDivs[ i ] );

						if( ( n0 != centroids[ i ][ 0 ] ) || ( n1 != centroids[ i ][ 1 ] ) || ( n2 != centroids[ i ][ 2 ] ) )
						{
							centroids[ i ][ 0 ] = n0;
							centroids[ i ][ 1 ] = n1;
							centroids[ i ][ 2 ] = n2;
							changed = true;
						}
					}
				}

				if( !changed )
					break;
			}

			out = Arrays.copyOf( base, base.length );

			for( k = 0; k < colors; k++ )
				out[ k ] = new Color( colorspace.toRGB( centroids[ k ]) | 0xFF000000 );

			return new Result( out, totalError );
		}
}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// End of 'PaletteGeneratorKMeans.java'
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
