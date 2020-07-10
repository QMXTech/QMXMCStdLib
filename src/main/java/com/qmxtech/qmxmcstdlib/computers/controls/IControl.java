package com.qmxtech.qmxmcstdlib.computers.controls;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// IControl.java
// Matthew J. Schultz (Korynkai) | Created : 20AUG19 | Last Modified : 20AUG19 by Matthew J. Schultz (Korynkai)
// Version : 0.0.1
// This is a source file for 'QMXMCStdLib'; it defines an abstract OpenComputers controller interface.
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

import com.qmxtech.qmxmcstdlib.BuildInfo;
import com.qmxtech.qmxmcstdlib.nbt.IHasNBTSaveableData;
import com.qmxtech.qmxmcstdlib.tile.TileEntityBase;

import li.cil.oc.api.driver.DeviceInfo;
import li.cil.oc.api.network.Environment;
import li.cil.oc.api.network.Node;
import li.cil.oc.api.network.Message;
import li.cil.oc.api.network.Visibility;
import li.cil.oc.api.Network;

import net.minecraft.nbt.NBTTagCompound;

import java.util.HashMap;
import java.util.Map;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// The 'IControl' Interface
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

public interface IControl extends Environment, DeviceInfo, IHasNBTSaveableData
{
    // Methods

        @Override default Map< String, String > getDeviceInfo()
        {
            Map< String, String > deviceInfo = new HashMap<>();

            deviceInfo.put( DeviceAttribute.Vendor, BuildInfo.VENDOR_NAME );

            return deviceInfo;
        }
        
        default void removeNode()
        {
            if( node() != null )
                node().remove();
        }

        @Override default void onConnect( Node node )
        {
            // Do nothing
        }

        @Override default void onDisconnect( Node node )
        {
            // Do nothing
        }

        @Override default void onMessage( Message message )
        {
            // Do nothing
        }

        @Override default Node node()
        {
            if( _getNode() == null )
                _setNode( Network.newNode( this, Visibility.Network ).withComponent( getPeripheralName(), Visibility.Network ).create() );
            
            if( ( _getNode() != null ) && ( _getNode().network() == null ) && ( this instanceof TileEntityBase ) )
                Network.joinOrCreateNetwork( ( TileEntityBase ) this );
            
            return _getNode();
        }

        @Override default void readFromNBT( NBTTagCompound nbt )
        {
            if ( ( _getNode() != null ) && ( _getNode().host() == this ) )
			    _getNode().load( nbt.getCompoundTag( "oc:node" ) );

        }

        @Override default NBTTagCompound writeToNBT( NBTTagCompound nbt )
        {
            NBTTagCompound nodeNbt = null;

            if ( ( _getNode() != null ) && ( _getNode().host() == this ) )
            {
                nodeNbt = new NBTTagCompound();
                _getNode().save( nodeNbt );
                nbt.setTag( "oc:node", nodeNbt );
            }

            return nbt;
        }

        String getPeripheralName();

    // NOTE: These methods should be treated as protected but Java doesn't allow this "by design". (BS if you ask me, one of many reasons why I prefer C++...)
        void _setNode( Node node );
        Node _getNode();
}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// End of 'IControl.java'
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
