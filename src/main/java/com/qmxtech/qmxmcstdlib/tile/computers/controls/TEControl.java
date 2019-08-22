package com.qmxtech.qmxmcstdlib.tile.computers.controls;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// TEControl.java
// Matthew J. Schultz (Korynkai) | Created : 20AUG19 | Last Modified : 20AUG19 by Matthew J. Schultz (Korynkai)
// Version : 0.0.1
// This is a source file for 'QMXMCStdLib'; it defines an abstract base TileEntity for a computer control.
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

import com.qmxtech.qmxmcstdlib.computers.controls.IControl;
import com.qmxtech.qmxmcstdlib.tile.TileEntityBase;

import li.cil.oc.api.Network;
import li.cil.oc.api.network.Node;
import li.cil.oc.api.network.Visibility;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ITickable;

import javax.annotation.Nonnull;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// The 'TEControl' Abstract Class
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

@SuppressWarnings( "WeakerAccess" )
public class TEControl extends TileEntityBase implements IControl, ITickable
{
    // Public Constructor

        public TEControl()
        {
            node = Network.newNode( this, Visibility.Network ).withComponent( "coloredlightcontroller", Visibility.Network ).create();
        }

    // Public Methods

        @Override public Node node()
        {
            return node;
        }

        @Override public void readFromNBT( @Nonnull NBTTagCompound nbt )
        {
            super.readFromNBT( nbt );

            if ( ( node != null ) && ( node.host() == this ) )
                node.load( nbt.getCompoundTag( "oc:node" ) );
        }

        @Override @Nonnull public NBTTagCompound writeToNBT( NBTTagCompound nbt )
        {
            super.writeToNBT( nbt );

            if ( ( node != null ) && ( node.host() == this ) )
            {
                final NBTTagCompound nodeNbt = new NBTTagCompound();
                node.save( nodeNbt );
                nbt.setTag( "oc:node", nodeNbt );
            }

            return nbt;
        }

        @Override public void update()
        {
            if( ( node != null ) && ( node.network() == null ) )
                Network.joinOrCreateNetwork(this);
        }

        @Override public void onChunkUnload()
        {
            super.onChunkUnload();

            if( node != null )
                node.remove();
        }

        @Override public void invalidate()
        {
            super.invalidate();

            if( node != null )
                node.remove();
        }

    // Protected Fields

        @SuppressWarnings( "WeakerAccess" )
        protected Node node;
}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// End of 'TEControl.java'
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
