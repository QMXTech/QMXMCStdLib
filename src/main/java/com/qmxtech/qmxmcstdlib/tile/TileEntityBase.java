package com.qmxtech.qmxmcstdlib.tile;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// TileEntityBase.java
// Matthew J. Schultz (Korynkai) | Created : 20AUG19 | Last Modified : 20AUG19 by Matthew J. Schultz (Korynkai)
// Version : 0.0.1
// This is a source file for 'QMXMCStdLib'; it defines an abstract base TileEntity.
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

import com.qmxtech.qmxmcstdlib.position.DimensionalPosition;

import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// The 'TileEntityBase' Abstract Class
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

@SuppressWarnings( "unused" )
public abstract class TileEntityBase extends TileEntity implements ITileEntityBase
{
    // Public Methods

        @Override public DimensionalPosition getDimensionalPosition()
        {
            return new DimensionalPosition( getPos(), getWorld().provider.getDimension() );
        }

        @Override @Nullable public SPacketUpdateTileEntity getUpdatePacket()
        {
            return new SPacketUpdateTileEntity( getPos(), 0, getUpdateTag() );
        }

        @Override @Nonnull public NBTTagCompound getUpdateTag()
        {
            NBTTagCompound tagCom = super.getUpdateTag();
            writeToNBT( tagCom );
            return tagCom;
        }

        @Override public void handleUpdateTag( @Nonnull NBTTagCompound tag )
        {
            this.readFromNBT( tag );
        }

        @Override public void onDataPacket( NetworkManager net, SPacketUpdateTileEntity packet )
        {
            readFromNBT( packet.getNbtCompound() );
            world.notifyBlockUpdate( getPos(), world.getBlockState( getPos() ), world.getBlockState( getPos() ), 3 );
        }

        @Override public boolean shouldRefresh( World world, BlockPos pos, IBlockState oldState, IBlockState newState )
        {
            return ( oldState.getBlock() != newState.getBlock() );
        }

    // Protected Methods

        protected void doWorldUpdate()
        {
            getUpdateTag();
            world.notifyBlockUpdate( getPos(), world.getBlockState( getPos() ), world.getBlockState( getPos() ), 2 );
            world.markBlockRangeForRenderUpdate( getPos(), getPos() );
            markDirty();
        }
}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// End of 'TileEntityBase.java'
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
