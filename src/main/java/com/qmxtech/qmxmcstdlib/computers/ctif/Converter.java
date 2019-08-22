package com.qmxtech.qmxmcstdlib.computers.ctif;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Converter.java
// Matthew J. Schultz (Korynkai) | Created : 17AUG19 | Last Modified : 19AUG19 by Matthew J. Schultz (Korynkai)
// Version : 0.0.1
// This is a source file for 'QMXMCStdLib'; it defines an image converter class to convert to ChenThread Image Format.
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
import com.qmxtech.qmxmcstdlib.computers.ctif.util.Utils;
import com.qmxtech.qmxmcstdlib.computers.ctif.platform.Platform;
import com.qmxtech.qmxmcstdlib.computers.ctif.platform.PlatformOpenComputers;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// The 'Converter' Class
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

// TODO: Flagged for rebase; further refactoring

@SuppressWarnings({ "WeakerAccess", "unused", "FieldCanBeLocal" })
public class Converter
{
	// Public Types

		public enum DitherMode
		{
			NONE,
			ERROR,
			ORDERED
		}

	// Public Constructor

		public Converter( Color[] colors, BufferedImage image, DitherMode ditherMode, float[] ditherMatrix, Platform platform, Colorspace colorspace )
		{
			int i;

			this.platform = platform;
			this.colorspace = colorspace;
			this.ditherMode = ditherMode;
			this.ditherMatrix = ditherMatrix;
			if( ditherMode == DitherMode.ORDERED )
			{
				assert ( ditherMatrix != null );

				ditherMatrixSize = ( int ) Math.sqrt( ditherMatrix.length - 1 );
				ditherMatrixOffset = 0;
				ditherMax = ( int ) ditherMatrix[ ditherMatrix.length - 1 ];
			} else
			{
				ditherMatrixSize = ( ( ditherMatrix != null ) ? ( int ) Math.sqrt( ditherMatrix.length ) : 0 );
				ditherMatrixOffset = ( ( ditherMatrixSize - 1 ) / 2 );
				ditherMax = 0;
			}

			this.image = image;
			palette = colors;

			img = new float[ image.getWidth() * image.getHeight() ][ 3 ];
			pal = new float[ colors.length ][ 3 ];

			pw = platform.getCharWidth();
			ph = platform.getCharHeight();
			cw = image.getWidth() / pw;
			ch = image.getHeight() / ph;

			for( i = 0; i < img.length; i++ )
				img[ i ] = colorspace.fromRGB( image.getRGB( ( i % image.getWidth() ), ( i / image.getWidth() ) ) );

			for( i = 0; i < colors.length; i++ )
				pal[ i ] = colorspace.fromRGB( colors[ i ].getRGB() );
		}

	// Public Methods

		@SuppressWarnings( "UnusedReturnValue" )
		public BufferedImage write( OutputStream stream ) throws IOException
		{
			BufferedImage output = new BufferedImage( image.getWidth(), image.getHeight(), BufferedImage.TYPE_3BYTE_BGR );
			int i;

			stream.write( 'C' );
			stream.write( 'T' );
			stream.write( 'I' );
			stream.write( 'F' );

			stream.write( 1 ); // Header version
			stream.write( 0 ); // Platform variant (0 - default)
			stream.write( platform.getPlatformId() );
			stream.write( platform.getPlatformId() >> 8 ); // Platform ID
			stream.write( cw & 0xFF );
			stream.write( cw >> 8 ); // Width in chars
			stream.write( ch & 0xFF );
			stream.write( ch >> 8 ); // Height in chars
			stream.write( pw ); // Char width
			stream.write( ph ); // Char height

			stream.write( palette.length > 16 ? 8 : 4 ); // BPP (byte)

			if( platform.getCustomColorCount() > 0 )
			{
				stream.write( 3 ); // Palette entry size
				stream.write( 16 );
				stream.write( 0 ); // Palette array size

				for( i = 0; i < 16; i++ )
				{
					stream.write( palette[ i ].getRGB() & 0xFF );
					stream.write( ( palette[ i ].getRGB() >> 8 ) & 0xFF );
					stream.write( ( palette[ i ].getRGB() >> 16 ) & 0xFF );
				}
			} else
			{
				stream.write( 0 ); // Palette array size
				stream.write( 0 );
				stream.write( 0 ); // Palette entry size
			}

			writePixelData( stream, output );
			stream.close();
			return output;
		}

	// Protected Fields

		protected Color[] palette;
		protected BufferedImage image;
		protected DitherMode ditherMode;
		protected float[] ditherMatrix;
		protected int ditherMatrixSize, ditherMatrixOffset;
		protected float[][] img;
		protected float[][] pal;
		protected int cw, ch, pw, ph;
		protected int ditherMax;
		protected Platform platform;
		protected Colorspace colorspace;

	// Protected Methods

		protected void addQuantError( float[][] pixelArray, int x, int y, int w, int h, float[] expected, float[] received, float mul )
		{
			if( ( x >= 0 ) && ( y >= 0 ) && ( x < w ) && ( y < h ) )
				Utils.addQuantError( pixelArray[ y * w + x ], expected, received, mul );
		}

		@SuppressWarnings( "all" )
		protected void writePixelData( OutputStream stream, BufferedImage output ) throws IOException
		{
			int ew = pw + ( ditherMatrixOffset * 2 );
			int eh = ph + ( ditherMatrixOffset * 2 );
			int i, t, cy, cx, py, px, rgb, red, green, blue, rr, rg, rb, col, bgIndex, fgIndex;
			int[] colorsUsed, quadrant;
			int bci1 = 0, bci2 = 0;
			double bcerr = Double.MAX_VALUE;

			int quadrantLen = ( ( pw * ph ) + 7 ) / 8;

			float[][] pixels = new float[ pw * ph ][];

			float[][] bcea = new float[ ew * eh ][ 3 ];
			float[][] tPixels = new float[ pixels.length ][ 3 ];
			float[][] errors = new float[ ew * eh ][ 3 ];
			int[] bcq = new int[ quadrantLen ];
			int[] cq = new int[ quadrantLen ];

			float[] colA = new float[ 3 ];

			boolean usePalMap = ( ( platform instanceof PlatformOpenComputers ) && ( ( PlatformOpenComputers ) platform ).getTier() == 3 );

			int[] palMap = new int[ palette.length ];

			int palMapLength;

			for( i = 0; i < 16; i++ )
				palMap[ i ] = i;

			int t3OffRed = 3;
			int t3OffGreen = 3;
			int t3OffBlue = 2;

			for( cy = 0; cy < ch; cy++ )
			{
				for( cx = 0; cx < cw; cx++ )
				{
					for( py = 0; py < ph; py++ )
					{
						for( px = 0; px < pw; px++ )
							pixels[ ( py * pw ) + px ] = img[ ( ( ( cy * ph ) + py ) * image.getWidth() ) + ( cx * pw ) + px ];
					}

					if( usePalMap )
					{
						palMapLength = 16;
						colorsUsed = new int[ palette.length ];
						for( py = 0; py < ph; py++ )
						{
							for( px = 0; px < pw; px++ )
							{
								rgb = image.getRGB( ( ( cx * pw ) + px ), ( ( cy * ph ) + py ) );
								red = ( ( ( rgb >> 16 ) & 0xFF ) * 6 ) / 256;
								green = ( ( ( rgb >> 8 ) & 0xFF ) * 8 ) / 256;
								blue = ( ( rgb & 0xFF ) * 5 ) / 256;

								for( rr = ( red - t3OffRed ); rr <= ( red + t3OffRed ); rr++ )
								{
									for( rg = ( green - t3OffGreen ); rg <= ( green + t3OffGreen ); rg++ )
									{
										for( rb = ( blue - t3OffBlue ); rb <= ( blue + t3OffBlue ); rb++ )
										{
											if( ( rr >= 0 ) && ( rg >= 0 ) && ( rb >= 0 ) && ( rr < 6 ) && ( rg < 8 ) && ( rb < 5 ) )
											{
												col = 16 + ( rr * 40 ) + ( rg * 5 ) + rb;

												if( colorsUsed[ col ] == 0 )
												{
													palMap[ palMapLength++ ] = col;
													colorsUsed[ col ] = 1;
												}
											}
										}
									}
								}
							}
						}
					} else
					{
						palMapLength = palette.length;
					}

					quadrant = bcq;
					bgIndex = bci1;
					fgIndex = bci2;

					if( bgIndex == fgIndex )
					{
						for( i = 0; i < quadrantLen; i++ )
							quadrant[ i ] = 0;
					}

					if( ( platform instanceof PlatformOpenComputers ) && ( ( pw * ph ) > 2 ) )
					{
						if( bgIndex > fgIndex )
						{
							t = fgIndex;
							fgIndex = bgIndex;
							bgIndex = t;
							quadrant[ 0 ] ^= ( 1 << ( pw * ph ) ) - 1;
						}
					}

					if( ( ( pw * ph ) == 2 ) && ( quadrant[ 0 ] == 1 ) )
					{
						t = fgIndex;
						fgIndex = bgIndex;
						bgIndex = t;
						quadrant[ 0 ] = 0;
					}

					if( palette.length > 2 )
					{
						if( ( pw * ph ) == 1 )
						{
							stream.write( fgIndex );
						} else
						{
							if( palette.length > 16 )
							{
								stream.write( bgIndex );
								stream.write( fgIndex );
							} else
							{
								stream.write( ( bgIndex << 4 ) | fgIndex );
							}
						}

						if( pw * ph > 2 )
						{
							for( i = 0; i < quadrantLen; i++ )
								stream.write( quadrant[ i ] );
						}
					} else
					{
						for( i = 0; i < quadrantLen; i++ )
							stream.write( quadrant[ i ] );
					}

					for( py = 0; py < ph; py++ )
					{
						for( px = 0; px < pw; px++ )
						{
							i = ( ( pw * ph ) - 1 ) - ( ( py * pw ) + px );

							if( ( quadrant[ i >> 3 ] & ( 1 << ( i & 7 ) ) ) != 0 )
								output.setRGB( ( ( cx * pw ) + px ), ( ( cy * ph ) + py ), palette[ fgIndex ].getRGB() );
							else
								output.setRGB( ( ( cx * pw ) + px ), ( ( cy * ph ) + py ), palette[ bgIndex ].getRGB() );
						}
					}
				}
			}
		}
}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// End of 'Converter.java'
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
