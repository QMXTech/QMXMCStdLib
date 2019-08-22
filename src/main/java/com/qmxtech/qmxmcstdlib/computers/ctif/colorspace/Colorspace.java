package com.qmxtech.qmxmcstdlib.computers.ctif.colorspace;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Colorspace.java
// Matthew J. Schultz (Korynkai) | Created : 17AUG19 | Last Modified : 19AUG19 by Matthew J. Schultz (Korynkai)
// Version : 0.0.1
// This is a source file for 'QMXMCStdLib'; it defines an abstract color space for ChenThread Image Format.
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
// The 'Colorspace' Abstract Class
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

// TODO: Flagged for rebase; further refactoring

public abstract class Colorspace
{
	// Public Methods

		public int toRGB( final float[] value )
		{
			float[] rgb = toRGBArray( value );

			if( rgb[ 0 ] < 0 )
				rgb[ 0 ] = 0;
			else if( rgb[ 0 ] > 1 )
				rgb[ 0 ] = 1;

			if( rgb[ 1 ] < 0 )
				rgb[ 1 ] = 0;
			else if( rgb[ 1 ] > 1 )
				rgb[ 1 ] = 1;

			if( rgb[ 2 ] < 0 )
				rgb[ 2 ] = 0;
			else if( rgb[ 2 ] > 1 )
				rgb[ 2 ] = 1;

			return ( Math.round( rgb[ 0 ] * 255.0f ) << 16 ) | ( Math.round( rgb[ 1 ] * 255.0f ) << 8 ) | Math.round( rgb[ 2 ] * 255.0f );
		}

		public float[] fromRGB( final int value )
		{
			return fromRGB(
				new float[]
				{
					( ( ( value >> 16 ) & 0xFF ) / 255.0f ),
					( ( ( value >> 8 ) & 0xFF ) / 255.0f ),
					( ( value & 0xFF ) / 255.0f )
				}
			);
		}

	// Public Abstract Methods

		public abstract float[] fromRGB( final float[] value );

		public abstract float[] toRGBArray( final float[] value );
}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// End of 'Colorspaces.java'
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
