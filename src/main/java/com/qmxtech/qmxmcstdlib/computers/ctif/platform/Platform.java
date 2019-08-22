package com.qmxtech.qmxmcstdlib.computers.ctif.platform;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Platform.java
// Matthew J. Schultz (Korynkai) | Created : 17AUG19 | Last Modified : 19AUG19 by Matthew J. Schultz (Korynkai)
// Version : 0.0.1
// This is a source file for 'QMXMCStdLib'; it defines an abstract platform for ChenThread Image Format.
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
// The 'Platform' Abstract Class
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

// TODO: Flagged for rebase; further refactoring

@SuppressWarnings({ "unused", "WeakerAccess" })
public abstract class Platform
{
	// Public Methods

		public int getPlatformId()
		{
			return platformId;
		}

		public int getCharWidth()
		{
			return cw;
		}

		public int getCharHeight()
		{
			return ch;
		}

		public int getWidth()
		{
			return w;
		}

		public int getHeight()
		{
			return h;
		}

		public int getChars()
		{
			return ( w * h );
		}

		public int getCustomColorCount()
		{
			return ccc;
		}

		public Color[] getPalette()
		{
			if ( palette == null )
				palette = generatePalette();

			return palette.clone();
		}

		public float getDefaultAspectRatio()
		{
			return ( float )( w * h );
		}

		public final int getWidthPx()
		{
			return ( w * cw );
		}

		public int getHeightPx()
		{
			return ( h * ch );
		}

		public int getCharsPx()
		{
			return ( ( ( w * h ) * cw ) * ch );
		}

	// Protected Fields

		protected int cw, ch, w, h, ccc, platformId;
		protected Color[] palette = null;

	// Protected Constructor

		protected Platform( final int platformId, final int charWidth, final int charHeight, final int width, final int height, final int customColorCount )
		{
			this.platformId = platformId;
			cw = charWidth;
			ch = charHeight;
			w = width;
			h = height;
			ccc = customColorCount;
		}

	// Protected Abstract Methods

		abstract protected Color[] generatePalette();

}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// End of 'Platform.java'
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
