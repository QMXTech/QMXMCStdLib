package com.qmxtech.qmxmcstdlib.computers.ctif.colorspace;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Colorspaces.java
// Matthew J. Schultz (Korynkai) | Created : 17AUG19 | Last Modified : 19AUG19 by Matthew J. Schultz (Korynkai)
// Version : 0.0.1
// This is a source file for 'QMXMCStdLib'; it defines a static list of valid color spaces for ChenThread Image Format.
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
// The 'Colorspaces' Class
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

// TODO: Flagged for rebase; further refactoring

public final class Colorspaces
{
    // Public Static Final Fields (Color Space Constants)

        public static final Colorspace YIQ = new ColorspaceMatrix(
            new float[]
            {
                    0.299f, 0.587f, 0.114f,
                    0.595716f, -0.274453f, -0.321263f,
                    0.211456f, -0.522591f, 0.311135f
            }
        );

        public static final Colorspace YUV = new ColorspaceMatrix(
            new float[]
            {
                    0.299f, 0.587f, 0.114f,
                    -0.147f, -0.289f, 0.436f,
                    0.615f, -0.515f, -0.100f
            }
        );

        public static final Colorspace RGB = new Colorspace()
        {
            @Override
            public float[] fromRGB( final float[] value )
            {
                return value;
            }

            @Override
            public float[] toRGBArray( final float[] value )
            {
                return value;
            }
        };
}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// End of 'Colorspaces.java'
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
