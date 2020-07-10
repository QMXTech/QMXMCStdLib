package com.qmxtech.qmxmcstdlib.computers.controls;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// IControlLight.java
// Matthew J. Schultz (Korynkai) | Created : 20AUG19 | Last Modified : 20AUG19 by Matthew J. Schultz (Korynkai)
// Version : 0.0.1
// This is a source file for 'QMXMCStdLib'; it defines an OpenComputers light controller interface.
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

import com.qmxtech.qmxmcstdlib.lighting.ILight;
import li.cil.oc.api.machine.Arguments;
//import li.cil.oc.api.machine.Callback;
import li.cil.oc.api.machine.Context;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// The 'IControlLight' Interface
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

public interface IControlLight extends ILight, IControl
{
    // Methods

        @SuppressWarnings( "unused" )
        //@Callback( doc = "function(brightness:number):number -- Set the brightness of the light. Returns the new brightness." )
        default Object[] setBrightness( Context context, Arguments args ) throws Exception
        {
            if( args.count() != 1 )
                throw new Exception( "Invalid number of arguments, expected 1" );

            int buf = args.checkInteger( 0 );

            if( ( buf > 15 ) || ( buf < 0 ) )
                throw new Exception( "Valid brightness range is 0 to 15" );

            setBrightness( buf );

            return new Object[]{ getBrightness() };
        }

        @SuppressWarnings( "unused" )
        //@Callback( doc = "function():number -- Get the brightness of the light." )
        default Object[] getBrightness( Context context, Arguments args )
        {
            return new Object[]{ getBrightness() };
        }
}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// End of 'IControlLight.java'
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
