package com.qmxtech.qmxmcstdlib.computers.ctif.platform;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// PlatformOpenComputers.java
// Matthew J. Schultz (Korynkai) | Created : 17AUG19 | Last Modified : 19AUG19 by Matthew J. Schultz (Korynkai)
// Version : 0.0.1
// This is a source file for 'QMXMCStdLib'; it defines an OpenComputers platform for ChenThread Image Format.
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

import java.awt.*;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// The 'PlatformOpenComputers' Class
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

// TODO: Flagged for rebase; further refactoring

@SuppressWarnings( "WeakerAccess" )
public class PlatformOpenComputers extends Platform
{
	// Public Constructor

		public PlatformOpenComputers( final int tier )
		{
			super( 1, 2, 4, 0, 0, 0 );
			this.tier = tier;
		}

	// Public Methods

		@Override
		public int getHeight()
		{
			return getWidth();
		}

		@Override
		public float getDefaultAspectRatio()
		{
			return ( float )( getWidth() / getRealHeight() );
		}

		@Override
		public int getChars()
		{
			return ( getWidth() * getRealHeight() );
		}

		@Override
		public int getCustomColorCount()
		{
			switch( tier )
			{
				case 1:
				default:
					return 0;
				case 2:
				case 3:
					return 16;
			}
		}

		@Override
		public int getWidth()
		{
			switch( tier )
			{
				case 1:
					return 40;
				case 2:
					return 80;
				case 3:
					return 160;
				default:
					return 0;
			}
		}

		public int getTier()
		{
			return tier;
		}

	// Protected Fields

		protected int tier;

	// Protected Methods

		@Override
		protected Color[] generatePalette()
		{
			Color[] colors = new Color[ getColorCount() ];
			int c, i;

			if ( tier == 1 )
			{
				colors[ 0 ] = new Color( 0, 0, 0 );
				colors[ 1 ] = new Color( 255, 255, 255 );
			}
			else
			{
				for ( i = 0; i < 16; i++ )
				{
					c = ( 17 * i );
					colors[ i ] = new Color( c, c, c );
				}

				if ( tier == 3 )
				{
					for ( i = 0; i < 240; i++) {
						colors[ i + 16 ] = new Color(
								( ( ( ( i / 40 ) % 6 ) * 255 ) / 5 ),
								( ( ( ( i / 5 ) % 8 ) * 255 ) / 7 ),
								( ( ( i % 5 ) * 255 ) / 4 ) );
					}
				}
			}
			return colors;
		}

		protected int getRealHeight() {
			switch( tier )
			{
				case 1:
				case 2:
					return 25;
				case 3:
					return 50;
				default:
					return 0;
			}
		}

		protected int getColorCount()
		{
			switch ( tier )
			{
				case 1:
					return 2;
				case 2:
				default:
					return 16;
				case 3:
					return 256;
			}
		}
}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// End of 'PlatformOpenComputers.java'
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
