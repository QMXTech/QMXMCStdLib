package com.qmxtech.qmxmcstdlib.computers.ctif.util;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Utils.java
// Matthew J. Schultz (Korynkai) | Created : 17AUG19 | Last Modified : 19AUG19 by Matthew J. Schultz (Korynkai)
// Version : 0.0.1
// This is a source file for 'QMXMCStdLib'; it defines miscellaneous utilities for ChenThread Image Format conversion.
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

import java.awt.*;
import java.awt.image.BufferedImage;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// The 'Utils' Class
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

// TODO: Flagged for rebase; further refactoring

@SuppressWarnings( "unused" )
public final class Utils
{
	// Public Static Methods

		public static BufferedImage resize( BufferedImage image, int width, int height )
		{
			BufferedImage resizedImage = new BufferedImage( width, height, image.getType() );
			Graphics2D g = resizedImage.createGraphics();
			//g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
			g.setRenderingHint( RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC );
			g.drawImage( image, 0, 0, width, height, null );
			g.dispose();
			return resizedImage;
		}

		@SuppressWarnings( "WeakerAccess" )
		public static int[] getRGB(BufferedImage image )
		{
			return image.getRGB( 0, 0, image.getWidth(), image.getHeight(), null, 0, image.getWidth() );
		}

		public static void addQuantError( float[] target, float[] expected, float[] received, float mul )
		{
			int i;

			if (mul != 0.0f) {
				for ( i = 0; i < target.length; i++ )
					target[ i ] += ( ( expected[ i ] - received[ i ] ) * mul );
			}
		}

		public static double getColorDistance( int c1, int c2, Colorspace colorspace )
		{
			return Math.sqrt(getColorDistanceSq( c1, c2, colorspace ) );
		}

		@SuppressWarnings( "WeakerAccess" )
		public static double getColorDistanceSq( int c1, int c2, Colorspace colorspace )
		{
			return getColorDistanceSq( colorspace.fromRGB( c1 ), colorspace.fromRGB( c2 ) );
		}

		@SuppressWarnings( "WeakerAccess" )
		public static double getColorDistanceSq( float[] f1, float[] f2 )
		{
			return ( ( ( f1[0] - f2[0] ) * ( f1[0] - f2[0] ) ) +
					( ( f1[1] - f2[1] ) * ( f1[1] - f2[1] ) ) +
					( ( f1[2] - f2[2] ) * ( f1[2] - f2[2] ) ) );
		}

	// Private Constructor (to prevent instantiation)

		private Utils()
		{
			/* Do nothing */
		}
}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// End of 'Utils.java'
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
