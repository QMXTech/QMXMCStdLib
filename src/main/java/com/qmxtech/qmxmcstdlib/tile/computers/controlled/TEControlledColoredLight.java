package com.qmxtech.qmxmcstdlib.tile.computers.controlled;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// TEControlledColoredLight.java
// Matthew J. Schultz (Korynkai) | Created : 20AUG19 | Last Modified : 27SEP19 by Matthew J. Schultz (Korynkai)
// Version : 0.0.1
// This is a source file for 'QMXMCStdLib'; it defines an abstract base TileEntity for a computer controlled colored light.
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
import com.qmxtech.qmxmcstdlib.computers.controls.IControlColoredLight;
import com.qmxtech.qmxmcstdlib.lighting.IColoredLight;

import li.cil.oc.api.machine.Arguments;
import li.cil.oc.api.machine.Callback;
import li.cil.oc.api.machine.Context;
import li.cil.oc.api.network.Node;
import net.minecraft.nbt.NBTTagCompound;

import javax.annotation.Nonnull;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// The 'TEControlledColoredLight' Abstract Class
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

// TODO: getComponentName() moved to CadmusInfrastructure implementation.

@SuppressWarnings( "unused" )
public abstract class TEControlledColoredLight extends TEControlledLight implements IControlColoredLight, IColoredLight
{
    // Public Methods

        @Callback( doc = "function(color:number):number -- Set the color by ordinal color value ('colors' API). Returns a number representing the new ordinal color.", direct = true, limit = 32 )
        public Object[] setColor( Context context, Arguments args ) throws Exception
        {
            return IControlColoredLight.super.setColor( context, args );
        }

        @SuppressWarnings( "unused" )
        @Callback( doc = "function(color/r:number[,g:number,b:number]):number,{r:number,g:number,b:number} -- Set the color using the given RGB value, or the " +
                "given individual RGB values. Returns a number representing the new RGB value, and an object representing the individual RGB values.", direct = true, limit = 32 )
        public Object[] setColorRGB( Context context, Arguments args ) throws Exception
        {
            return IControlColoredLight.super.setColorRGB( context, args );
        }

        @SuppressWarnings( "unused" )
        @Callback( doc = "function():number[,number,{r:number,g:number,b:number}] -- Get the current color by ordinal value ('colors' API). Returns a number " +
                "representing the ordinal color. If this value is '16', a custom color was set and its RGB value and an object representing the individual RGB " +
                "values will also be returned. Guaranteed not to throw.", direct = true, limit = 32 )
        public Object[] getColor( Context context, Arguments args )
        {
            return IControlColoredLight.super.getColor( context, args );
        }


        @SuppressWarnings( "unused" )
        @Callback( doc = "function():number,{r:number,g:number,b:number} -- Get the current color as an RGB value. Returns a number representing the new RGB value, " +
                "and an object representing the individual RGB values. Guaranteed not to throw.", direct = true, limit = 32 )
        public Object[] getColorRGB( Context context, Arguments args )
        {
            return IControlColoredLight.super.getColorRGB( context, args );
        }

        @Override public void setColor( ColorValue color, boolean withWorldUpdate )
        {
            this.color = color;

            if( withWorldUpdate )
                doWorldUpdate();
        }

        @Override public ColorValue getColor()
        {
            return color;
        }

        @Override public void readFromNBT( @Nonnull NBTTagCompound nbt )
        {
            super.readFromNBT( nbt );
            setColor( ColorValue.fromValue( nbt.getInteger( "color" ) ), false );
        }

        @Override @Nonnull public NBTTagCompound writeToNBT( NBTTagCompound nbt )
        {
            super.writeToNBT( nbt );
            nbt.setInteger( "color", getColor().value() );
            return nbt;
        }

    // Protected Fields

        protected ColorValue color = ColorValue.WHITE;
}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// End of 'TEControlledColoredLight.java'
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
