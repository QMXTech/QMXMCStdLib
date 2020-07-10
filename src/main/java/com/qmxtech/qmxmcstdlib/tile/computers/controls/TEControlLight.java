package com.qmxtech.qmxmcstdlib.tile.computers.controls;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// TEControlLight.java
// Matthew J. Schultz (Korynkai) | Created : 20AUG19 | Last Modified : 27SEP19 by Matthew J. Schultz (Korynkai)
// Version : 0.0.1
// This is a source file for 'QMXMCStdLib'; it defines an abstract base TileEntity for a computer control of a light.
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

import com.qmxtech.qmxmcstdlib.computers.controls.IControlLight;

import li.cil.oc.api.machine.Arguments;
import li.cil.oc.api.machine.Callback;
import li.cil.oc.api.machine.Context;
import net.minecraft.nbt.NBTTagCompound;

import javax.annotation.Nonnull;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// The 'TEControlLight' Abstract Class
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

public abstract class TEControlLight extends TEControl implements IControlLight
{
    // Public Methods

        @Callback( doc = "function(brightness:number):number -- Set the brightness of the light. Returns the new brightness." )
        @Override public Object[] setBrightness( Context context, Arguments args ) throws Exception
        {
            return IControlLight.super.setBrightness( context, args );
        }

        @SuppressWarnings( "unused" )
        @Callback( doc = "function():number -- Get the brightness of the light." )
        @Override public Object[] getBrightness( Context context, Arguments args )
        {
            return IControlLight.super.getBrightness( context, args );
        }

        @Override public void setBrightness( int brightness, boolean withWorldUpdate )
        {
            this.brightness = brightness;

            if( withWorldUpdate )
                doWorldUpdate();
        }

        @Override public int getBrightness()
        {
            return brightness;
        }

        @Override public void readFromNBT( @Nonnull NBTTagCompound nbt )
        {
            super.readFromNBT( nbt );
            setBrightness( nbt.getInteger( "brightness" ) );
        }

        @Override @Nonnull public NBTTagCompound writeToNBT( NBTTagCompound nbt )
        {
            super.writeToNBT( nbt );
            nbt.setInteger( "brightness", getBrightness() );
            return nbt;
        }

    // Protected Fields

        @SuppressWarnings( "WeakerAccess" )
        protected int brightness = 0;
}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// End of 'TEControlLight.java'
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
