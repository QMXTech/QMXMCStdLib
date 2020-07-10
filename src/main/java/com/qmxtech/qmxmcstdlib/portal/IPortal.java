package com.qmxtech.qmxmcstdlib.portal;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// IPortal.java
// Matthew J. Schultz (Korynkai) | Created : 11OCT19 | Last Modified : 11OCT19 by Matthew J. Schultz (Korynkai)
// Version : 0.0.1
// This is a source file for 'QMXMCStdLib'; it defines an interface to a portal object (always denotes the portal controller).
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

import com.qmxtech.qmxmcstdlib.position.IHasDimensionalPosition;

import net.minecraft.nbt.NBTTagCompound;

import com.qmxtech.qmxmcstdlib.generic.IHasLabel;
import com.qmxtech.qmxmcstdlib.generic.IHasUUID;
import com.qmxtech.qmxmcstdlib.generic.IActivatable;
import com.qmxtech.qmxmcstdlib.nbt.IHasNBTSaveableData;

import java.util.UUID;
import java.util.NoSuchElementException;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// The 'IPortal' Interface
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

public interface IPortal extends IHasDimensionalPosition, IHasLabel, IHasUUID, IActivatable, IHasNBTSaveableData
{
    // Methods

        PortalNetwork getNetwork();
        void setPublic( boolean isPublic );
        < P extends IPortal> void setDestination( P portal );
        void setDestination( UUID identifier ) throws NoSuchElementException;
        void setDestination( String label ) throws NoSuchElementException;
        IPortal getDestination();
        String getDestinationLabel() throws NoSuchFieldException;
        UUID getDestinationUUID();
        
        default boolean hasNetwork()
        {
            return getNetwork() != null;
        }

        // NOTE: This method MUST be overridden: network needs to be stored by the implementing object.
        // Use IPortal.super.addToNetwork( network ) to call this method from the overridden method.
        default void addToNetwork( PortalNetwork network )
        {
            network.addPortal( this );
        }

        default void addToNetwork( UUID network ) throws NoSuchElementException
        {
            addToNetwork( PortalManager.getInstance().getNetwork( network ) );
        }

        default void addToNetwork( String network ) throws NoSuchElementException
        {
            addToNetwork( PortalManager.getInstance().getNetwork( network ) );
        }

        default void removeFromNetwork()
        {
            if( hasNetwork() )
                getNetwork().deletePortal( this );
        }

        default boolean isPublic()
        {
            return false;
        }

        // Note: Don't forget to override equals( object ) and hashCode()! Use UUID!

        // Note: this method is typically overridden by an implementing TileEntity.
        // Use IPortal.super.readFromNBT( nbt ) to call this method from the overridden method.
        @Override default void readFromNBT( NBTTagCompound nbt )
        {
            if( !nbt.getString( "network" ).equals( "null" ) )
                addToNetwork( UUID.fromString( nbt.getString( "network" ) ) );
        }

        // Note: this method is typically overridden by an implementing TileEntity.
        // Use IPortal.super.readFromNBT( nbt ) to call this method from the overridden method.
        @Override default NBTTagCompound writeToNBT( NBTTagCompound nbt )
        {
            nbt.setString( "network", hasNetwork() ? getNetwork().getUUID().toString() : "null" );
            return nbt;
        }
}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// End of 'IPortal.java'
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
