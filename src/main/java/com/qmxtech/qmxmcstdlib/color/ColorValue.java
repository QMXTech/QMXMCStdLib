package com.qmxtech.qmxmcstdlib.color;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// ColorValue.java
// Matthew J. Schultz (Korynkai) | Created : 16AUG19 | Last Modified : 16AUG19 by Matthew J. Schultz (Korynkai)
// Version : 0.0.1
// This is a source file for 'QMXMCStdLib'; it defines a container for holding a color value (RGB or ordinal).
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
// The 'ColorValue' Class
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings( "unused" )
public final class ColorValue
{
    // Public Constants (Ordinal Colors)

        public static final ColorValue WHITE = new ColorValue( OrdinalColor.white );
        public static final ColorValue ORANGE = new ColorValue( OrdinalColor.orange );
        public static final ColorValue MAGENTA = new ColorValue( OrdinalColor.magenta );
        public static final ColorValue LIGHTBLUE = new ColorValue( OrdinalColor.lightblue );
        public static final ColorValue YELLOW = new ColorValue( OrdinalColor.yellow );
        public static final ColorValue LIME = new ColorValue( OrdinalColor.lime );
        public static final ColorValue PINK = new ColorValue( OrdinalColor.pink );
        public static final ColorValue GRAY = new ColorValue( OrdinalColor.gray );
        public static final ColorValue SILVER = new ColorValue( OrdinalColor.silver );
        public static final ColorValue CYAN = new ColorValue( OrdinalColor.cyan );
        public static final ColorValue PURPLE = new ColorValue( OrdinalColor.purple );
        public static final ColorValue BLUE = new ColorValue( OrdinalColor.blue );
        public static final ColorValue BROWN = new ColorValue( OrdinalColor.brown );
        public static final ColorValue GREEN = new ColorValue( OrdinalColor.green );
        public static final ColorValue RED = new ColorValue( OrdinalColor.red );
        public static final ColorValue BLACK = new ColorValue( OrdinalColor.black );

    // Public Static Methods

        public static ColorValue fromOrdinal( final int o )
        {
            // Create a new ColorValue object from an ordinal color value integer.

                return new ColorValue( OrdinalColor.fromOrdinal( o ) );
        }

        public static ColorValue fromValue( final int v )
        {
            // Create a new ColorValue object from an RGB integer.

                return new ColorValue( v );
        }

        public static ColorValue fromRGBInt( final int r, final int g, final int b )
        {
            // Create a new ColorValue object from separate RGB integers.

                return new ColorValue( ( r << 16 ) | ( g << 8 ) | b );
        }

        public static ColorValue fromRGBFloat( final int r, final int g, final int b )
        {
                // Create a new ColorValue object from separate RGB integers.

                return new ColorValue( ( ( int )( r * 255.0f ) << 16 ) | ( ( int )( g * 255.0f ) << 8 ) | ( int )( b * 255.0f ) );
        }

        public static ColorValue fromString( final String s )
        {
            // Create a new ColorValue object from an ordinal color string.

                return new ColorValue( OrdinalColor.fromString( s ) );
        }

        @SuppressWarnings( "WeakerAccess" )
        public static boolean isEqual( final Object lhs, final Object rhs )
        {
            // Initialize local variables.

                boolean RetVal = false;
                OrdinalColor lhsc, rhsc;

            // Compare the two objects.

                if( ( lhs instanceof OrdinalColor ) && ( rhs instanceof OrdinalColor ) )
                {
                    lhsc = ( OrdinalColor ) lhs;
                    rhsc = ( OrdinalColor ) rhs;

                    if( lhsc.ordinal() == OrdinalColor.custom.ordinal() )
                    {
                        if( rhsc.ordinal() == OrdinalColor.custom.ordinal() )
                            RetVal = ( lhsc.value() == rhsc.value() );
                    } else
                    {
                        if( rhsc.ordinal() != OrdinalColor.custom.ordinal() )
                        {
                            RetVal = ( lhsc.ordinal() == rhsc.ordinal() );
                        }
                    }
                }

            // Return the result of the comparison.

                return RetVal;
        }

    // Public Methods

        public float rFloat()
        {
            // Return the Red color value.

                return ( ( ( value >> 16 ) & 0xFF ) / 255.0f );
        }

        public float gFloat()
        {
            // Return the Green color value.

                return ( ( ( value >> 8 ) & 0xFF ) / 255.0f );
        }

        public float bFloat()
        {
            // Return the Blue color value.

                return ( ( value & 0xFF ) / 255.0f );
        }

        @SuppressWarnings( "WeakerAccess" )
        public int r()
        {
            // Return the Red color value.

                return ( ( value >> 16 ) & 0xFF );
        }

        @SuppressWarnings( "WeakerAccess" )
        public int g()
        {
            // Return the Green color value.

                return ( ( value >> 8 ) & 0xFF );
        }

        @SuppressWarnings( "WeakerAccess" )
        public int b()
        {
            // Return the Blue color value.

                return ( value & 0xFF );
        }

        public Map getRGBObject()
        {
            // Return an object holding the RGB values

                HashMap< String, Integer > RetMap = new HashMap<>();

                RetMap.put( "r", r() );
                RetMap.put( "g", g() );
                RetMap.put( "b", b() );

                return RetMap;
        }

        public int ordinal()
        {
            // Return the Ordinal color value.

                return this.oc.ordinal();
        }

        public int value()
        {
            // Return the RGB integer color value.

                return this.value;
        }

        public String toString()
        {
            // Return the ordinal color string, or 'custom' if not ordinal.

                return this.oc.toString();
        }

        public boolean isEqual( final Object v )
        {
            // Return whether this object is equal to another.

                return isEqual( this, v );
        }

    // Private Types

        private enum OrdinalColor
        {
            // Ordinal Color Values

                white( 0xFFFFFF ),
                orange( 0xFFA500 ),
                magenta( 0xFF00FF ),
                lightblue( 0x87CEFA ),
                yellow( 0xFFFF00 ),
                lime( 0x00FF00 ),
                pink( 0xFFB6C1 ),
                gray( 0x808080 ),
                silver( 0xC0C0C0 ),
                cyan( 0x00FFFF ),
                purple( 0x800080 ),
                blue( 0x0000FF ),
                brown( 0x8B4513 ),
                green( 0x008000 ),
                red( 0xFF0000 ),
                black( 0x000000 ),
                custom;

            // Public Static Methods

                public static OrdinalColor fromValue( final int v )
                {
                    // Initialize return value.

                        OrdinalColor RetVal = custom;

                    // Select the OrdinalColor value from an RGB integer, if it is ordinal.

                        for( OrdinalColor oc : values() )
                        {
                            if( oc.value() == v )
                                RetVal = oc;
                        }

                    // Return the return value.

                        return RetVal;
                }

                public static OrdinalColor fromString( final String s )
                {
                    // Initialize return value.

                        OrdinalColor RetVal = null;

                    // Select the OrdinalColor value from its string value, if it is ordinal.

                        for( OrdinalColor oc : values() )
                        {
                            if( oc.toString().equals( s ) )
                                RetVal = oc;
                        }

                    // Return the return value.

                        return RetVal;
                }

                public static OrdinalColor fromOrdinal( final int o )
                {
                    // Initialize return value.

                        OrdinalColor RetVal = null;

                    // Select the OrdinalColor from its ordinal value.

                        for( OrdinalColor oc : values() )
                        {
                            if( oc.ordinal() == o )
                                RetVal = oc;
                        }

                    // Return the return value.

                        return RetVal;
                }

            // Public Methods

                public int value()
                {
                    // Return the RGB value integer.

                        return this.value;
                }

            // Private Fields

                private int value;

            // Enum Constructors (private)

                OrdinalColor() { /* Do nothing */ }

                OrdinalColor( final int v )
                {
                    // Initialize the enum value from an RGB integer.

                        this.value = v;
                }
        }

    // Private Fields

        private int value;
        private OrdinalColor oc;

    // Private Constructors

        private ColorValue( final int v )
        {
            // Initialize the ColorValue object from an RGB integer.

                this.oc = OrdinalColor.fromValue( v );

                if( this.oc == OrdinalColor.custom )
                    this.value = v;
                else
                    this.value = this.oc.value();
        }

        private ColorValue( final OrdinalColor c )
        {
            // Initialize the ColorValue object from an OrdinalColor enum value.

                this.oc = c;
                this.value = this.oc.value();
        }
}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// End of 'ColorValue.java'
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
