package com.qmxtech.qmxmcstdlib.tile.lighting;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// BaseTELight.java
// Matthew J. Schultz (Korynkai) | Created : 20AUG19 | Last Modified : 20AUG19 by Matthew J. Schultz (Korynkai)
// Version : 0.0.1
// This is a source file for 'QMXMCStdLib'; it defines an abstract base TileEntity for a light.
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
import com.qmxtech.qmxmcstdlib.tile.TileEntityBase;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// The 'TELightBase' Abstract Class
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

public abstract class TELightBase extends TileEntityBase implements ILight
{
    // Public Constructor

        public TELightBase()
        {
            setBrightness( 0, false );
        }

    // Public Methods

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

        @SideOnly( Side.CLIENT ) @Override @Nonnull public AxisAlignedBB getRenderBoundingBox()
        {
            // Return render bounding box to calling routine.

                return ( new AxisAlignedBB( getPos(), getPos().add( 1, 1, 1 ) ) );
        }

    // Protected Fields

        protected int brightness;
}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// End of 'TELightBase.java'
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
