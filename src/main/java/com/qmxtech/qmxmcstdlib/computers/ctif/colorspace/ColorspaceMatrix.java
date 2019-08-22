package com.qmxtech.qmxmcstdlib.computers.ctif.colorspace;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Colorspace.java
// Matthew J. Schultz (Korynkai) | Created : 17AUG19 | Last Modified : 19AUG19 by Matthew J. Schultz (Korynkai)
// Version : 0.0.1
// This is a source file for 'QMXMCStdLib'; it defines a color space matrix for ChenThread Image Format.
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
// The 'ColorspaceMatrix' Class
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

// TODO: Flagged for rebase; further refactoring

@SuppressWarnings( "WeakerAccess" )
public class ColorspaceMatrix extends Colorspace
{
	// Public Methods

		@Override
		public float[] fromRGB( final float[] value )
		{
			return mmul3( ( ( parent != null ) ? parent.fromRGB( value ) : value ), matrixFromRGB );
		}

		@Override
		public float[] toRGBArray( final float[] value )
		{
			return ( parent != null ) ? parent.toRGBArray( mmul3( value, matrixToRGB ) ) : mmul3( value, matrixToRGB );
		}

	// Protected Fields

		protected Colorspace parent;
		protected float[] matrixFromRGB;
		protected float[] matrixToRGB;

	// Protected Constructors

		protected ColorspaceMatrix( final float[] matrix )
		{
			this( null, matrix );
		}

		protected ColorspaceMatrix( final Colorspace parent, final float[] matrix )
		{
			this.parent = parent;
			matrixFromRGB = matrix;
			matrixToRGB = new float[ 9 ];

			float detInv = 1f / (
				( matrix[ 0 ] * matrix[ 4 ] * matrix[ 8 ] ) +
				( matrix[ 1 ] * matrix[ 5 ] * matrix[ 6 ] ) +
				( matrix[ 2 ] * matrix[ 3 ] * matrix[ 7 ] ) -
				( matrix[ 2 ] * matrix[ 4 ] * matrix[ 6 ] ) +
				( matrix[ 0 ] * matrix[ 5 ] * matrix[ 7 ] ) +
				( matrix[ 1 ] * matrix[ 3 ] * matrix[ 8 ] ) );

			matrixToRGB[ 0 ] = detInv * (
				( matrix[ 4 ] * matrix[ 8 ] ) -
				( matrix[ 5 ] * matrix[ 7 ] ) );

			matrixToRGB[ 3 ] = ( -detInv * (
				( matrix[ 3 ] * matrix[ 8 ] ) -
				( matrix[ 5 ] * matrix[ 6 ] ) ) );

			matrixToRGB[ 6 ] = ( -detInv * (
				( matrix[ 4 ] * matrix[ 6 ] ) -
				( matrix[ 3 ] * matrix[ 7 ] ) ) );

			matrixToRGB[ 1 ] = ( -detInv * (
				( matrix[ 1 ] * matrix[ 8 ] ) -
				( matrix[ 2 ] * matrix[ 7 ] ) ) );

			matrixToRGB[ 4 ] = detInv * (
				( matrix[ 0 ] * matrix[ 8 ] ) -
				( matrix[ 2 ] * matrix[ 6 ] ) );

			matrixToRGB[ 7 ] = detInv * (
				( matrix[ 1 ] * matrix[ 6 ] ) -
				( matrix[ 0 ] * matrix[ 7 ] ) );

			matrixToRGB[ 2 ] = detInv * (
				( matrix[ 1 ] * matrix[ 5 ] ) -
				( matrix[ 2 ] * matrix[ 4 ] ) );

			matrixToRGB[ 5 ] = ( -detInv * (
				( matrix[ 0 ] * matrix[ 5 ] ) -
				( matrix[ 2 ] * matrix[ 3 ] ) ) );

			matrixToRGB[ 8 ] = ( -detInv * (
				( matrix[ 1 ] * matrix[ 3 ] ) -
				( matrix[ 0 ] * matrix[ 4 ] ) ) );
		}

	// Protected Methods

		protected final float[] mmul3( final float[] a, final float[] b )
		{
			return new float[]
			{
				( ( a[ 0 ] * b[ 0 ] ) +
					( a[ 1 ] * b[ 1 ] ) +
					( a[ 2 ] * b[ 2 ] ) ),
				( ( a[ 0 ] * b[ 3 ] ) +
					( a[ 1 ] * b[ 4 ] ) +
					( a[ 2 ] * b[ 5 ] ) ),
				( ( a[ 0 ] * b[ 6 ] ) +
					( a[ 1 ] * b[ 7 ] ) +
					( a[ 2 ] * b[ 8 ] ) )
			};
		}
}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// End of 'ColorspaceMatrix.java'
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
