package com.qmxtech.qmxmcstdlib.computers.ctif;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// CTIF.java
// Matthew J. Schultz (Korynkai) | Created : 17AUG19 | Last Modified : 19AUG19 by Matthew J. Schultz (Korynkai)
// Version : 0.0.1
// This is a source file for 'QMXMCStdLib'; it defines the main class for ChenThread Image Format conversion.
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
import com.qmxtech.qmxmcstdlib.computers.ctif.colorspace.Colorspaces;
import com.qmxtech.qmxmcstdlib.computers.ctif.platform.*;
import com.qmxtech.qmxmcstdlib.computers.ctif.util.*;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.Math;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// The 'CTIF' Class
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

// TODO: Flagged for rebase; further refactoring

@SuppressWarnings( "unused" )
public final class CTIF
{
	// Public Static Final Fields (Mode Constants)

		public static final int OC_TIER2 = 0;
		public static final int OC_TIER3 = 1;

	// Public Static Methods

		public static CTIFImage convert( BufferedImage image, int mode, int colorspace, int maxWidth, int maxHeight ) throws IOException
		{
			int width = 0;
			int height = 0;
			BufferedImage resizedImage;
			Color[] palette;
			Converter conv;
			ByteArrayOutputStream outStream = new ByteArrayOutputStream();
			Platforms p = Platforms.get( mode );
			Colorspace cs;

			if( ( image.getWidth() <= maxWidth ) && ( image.getHeight() <= maxHeight ) )
			{
				resizedImage = image;
			}
			else
			{
				if( image.getWidth() != image.getHeight() )
				{
					if( image.getWidth() > image.getHeight() )
					{
						width = maxWidth;
						height = Math.round( maxHeight * ( ( ( float ) image.getHeight() ) / ( ( float ) image.getWidth() ) ) );
					}
					else
					{
						height = maxHeight;
						width = Math.round( maxWidth * ( ( ( float ) image.getWidth() ) / ( ( float ) image.getHeight() ) ) );
					}
				}
				else
				{
					width = maxWidth;
					height = maxHeight;
				}

				resizedImage = Utils.resize( image, width, height );
			}

			switch( colorspace )
			{
				case 0:
				default:
					cs = Colorspaces.YIQ;
					break;
				case 1:
					cs = Colorspaces.YUV;
					break;
				case 2:
					cs = Colorspaces.RGB;
					break;
			}

			palette = p.generatePalette( resizedImage, cs );

			conv = new Converter(
				palette,
				resizedImage,
				Converter.DitherMode.ERROR,
				new float[]{ 0, 0, 0, 0, 0, ( 7.0f / 16.0f ), ( 3.0f / 16.0f ), ( 5.0f / 16.0f ), ( 1.0f / 16.0f ) },
				p.get(),
				cs
			);

			conv.write( outStream );

			return new CTIFImage( outStream.toByteArray(), width, height );
		}

	// Private Types

		private enum Platforms
		{
			oc_tier2( new PlatformOpenComputers( 2 ) ),
			oc_tier3( new PlatformOpenComputers( 3 ) );

			private Platform value;

			Platforms( Platform p )
			{
				this.value = p;
			}

			public Platform get()
			{
				return value;
			}

			public static Platforms get( int mode )
			{
				Platforms ret = null;

				for( Platforms p : values() )
				{
					if( p.ordinal() == mode )
						ret = p;
				}

				return ret;
			}

			public Color[] generatePalette( BufferedImage image, Colorspace cs )
			{
				PaletteGeneratorKMeans gen = new PaletteGeneratorKMeans( image, value.getPalette(), value.getCustomColorCount(), 0, cs );
				return gen.generate( 1 );
			}
		}
}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// End of 'CTIF.java'
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
