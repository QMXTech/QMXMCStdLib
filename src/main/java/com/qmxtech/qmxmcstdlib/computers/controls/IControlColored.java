package com.qmxtech.qmxmcstdlib.computers.controls;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// IControlColored.java
// Matthew J. Schultz (Korynkai) | Created : 20AUG19 | Last Modified : 20AUG19 by Matthew J. Schultz (Korynkai)
// Version : 0.0.1
// This is a source file for 'QMXMCStdLib'; it defines an OpenComputers color controller interface.
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

import com.qmxtech.qmxmcstdlib.color.ColorValue;
import com.qmxtech.qmxmcstdlib.color.IColored;

import com.qmxtech.qmxmcstdlib.log.Log;
import li.cil.oc.api.machine.Arguments;
import li.cil.oc.api.machine.Context;
import li.cil.oc.api.machine.Callback;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// The 'IControlColored' Interface
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

public interface IControlColored extends IColored, IControl
{
    // Methods

        @SuppressWarnings( "unused" )
        @Callback( doc = "function(color:number):number -- Set the color by ordinal color value ('colors' API). Returns a number representing the new ordinal color.", direct = true, limit = 32 )
        default Object[] setColor( Context context, Arguments args ) throws Exception
        {
            if( args.count() != 1 )
                throw new Exception( "Invalid number of arguments, expected 1" );

            int buf = args.checkInteger( 0 );

            if( ( buf < 0 ) || ( buf > 15 ) )
                throw new Exception( "Ordinal color value is 0 to 15. Use of 'colors' API is preferred.");

            setColor( ColorValue.fromOrdinal( buf ) );

            return new Object[] { getColor().ordinal() };
        }

        @SuppressWarnings( "unused" )
        @Callback( doc = "function(color/r:number[,g:number,b:number]):number,{r:number,g:number,b:number} -- Set the color using the given RGB value, or the " +
                "given individual RGB values. Returns a number representing the new RGB value, and an object representing the individual RGB values.", direct = true, limit = 32 )
        default Object[] setColorRGB( Context context, Arguments args ) throws Exception
        {
            if( ( args.count() != 1 ) && ( args.count() != 3 ) )
            {
                Log.warn( "Arg count: " + args.count() );
                throw new Exception( "Invalid number of arguments, expected 1 or 3" );
            }

            if( args.count() == 1 )
            {
                int buf = args.checkInteger( 0 );

                if( ( buf > 0xFFFFFF ) || ( buf < 0x000000 ) )
                    throw new Exception( "Valid RGB range is 0x000000 to 0xFFFFFF" );

                setColor( ColorValue.fromValue( buf ) );
            }
            else
            {
                int r = args.checkInteger( 0 );
                int g = args.checkInteger( 1 );
                int b = args.checkInteger( 2 );

                if( ( r > 255 ) || ( r < 0 ) || ( g > 255 ) || ( g < 0 ) || ( b > 255 ) || ( b < 0 ) )
                    throw new Exception( "Valid range for individual RGB values is 0 to 255" );

                setColor( ColorValue.fromRGBInt( r, g, b ) );
            }

            //doBlockUpdate();

            return new Object[]{ getColor().value(), getColor().getRGBObject() };
        }

        @SuppressWarnings( "unused" )
        @Callback( doc = "function():number[,number,{r:number,g:number,b:number}] -- Get the current color by ordinal value ('colors' API). Returns a number " +
                "representing the ordinal color. If this value is '16', a custom color was set and its RGB value and an object representing the individual RGB " +
                "values will also be returned. Guaranteed not to throw.", direct = true, limit = 32 )
        default Object[] getColor( Context context, Arguments args )
        {
            if ( getColor().ordinal() == 16 )
                return new Object[] { getColor().ordinal(), getColor().value(), getColor().getRGBObject() };
            else
                return new Object[] { getColor().ordinal() };
        }


        @SuppressWarnings( "unused" )
        @Callback( doc = "function():number,{r:number,g:number,b:number}; Get the current color as an RGB value. Returns a number representing the new RGB value, " +
                "and an object representing the individual RGB values. Guaranteed not to throw.", direct = true, limit = 32 )
        default Object[] getColorRGB( Context context, Arguments args )
        {
            return new Object[]{ getColor().value(), getColor().getRGBObject() };
        }
}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// End of 'IControlColored.java'
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
