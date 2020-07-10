package com.qmxtech.qmxmcstdlib.portal;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// PortalManager.java
// Matthew J. Schultz (Korynkai) | Created : 11OCT19 | Last Modified : 11OCT19 by Matthew J. Schultz (Korynkai)
// Version : 0.0.1
// This is a source file for 'QMXMCStdLib'; it defines the global portal network manager.
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

import com.qmxtech.qmxmcstdlib.nbt.IHasNBTSaveableData;

import net.minecraft.nbt.NBTTagCompound;

import java.util.concurrent.atomic.AtomicReference;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.UUID;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// The 'PortalManager' Class
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

public final class PortalManager implements IPortalMarkDirty, IHasNBTSaveableData
{
    // Public Static Methods

        public static void initialize()
        {
            if( instance == null )
                instance = new PortalManager();
        }

        public static PortalManager getInstance()
        {
            initialize();
            return instance;
        }

    // Public Methods

        public PortalNetwork createNetwork()
        {
            PortalNetwork retval = new PortalNetwork();

            addNetwork( retval );

            return retval;
        }

        public PortalNetwork createNetwork( final UUID identifier )
        {
            PortalNetwork retval = null;
            
            if( !hasNetwork( identifier ) )
            {
                retval = new PortalNetwork( identifier );
                addNetwork( retval );
            }

            return retval;
        }

        public PortalNetwork createNetwork( final String label )
        {
            PortalNetwork retval = null;

            if( !hasNetwork( label ) )
            {
                retval = new PortalNetwork( label );
                addNetwork( retval );
            }

            return retval;
        }

        public PortalNetwork createNetwork( final UUID identifier, final String label )
        {
            PortalNetwork retval = null;

            if( !hasNetwork( identifier ) && !hasNetwork( label ) )
            {
                retval = new PortalNetwork( identifier, label );
                addNetwork( retval );
            }

            return retval;
        }

        public void addNetwork( PortalNetwork network )
        {
            networks.add( network );
            markDirty();
        }

        public PortalNetwork getNetwork( final UUID identifier )
        {
            AtomicReference< PortalNetwork > retval = new AtomicReference<>( null );
            
            networks.forEach( ( net ) ->
            {
                if( net.getUUID().equals( identifier ) )
                    retval.set( net );
            } );

            return retval.get();
        }

        public PortalNetwork getNetwork( final String label )
        {
            AtomicReference< PortalNetwork > retval = new AtomicReference<>( null );
            
            networks.forEach( ( net ) ->
            {
                if( net.getLabel().equals( label ) )
                    retval.set( net );
            } );

            return retval.get();
        }

        // Unnecessary? Originally taken from ConduitManager...
        /*public PortalNetwork getNetwork( final DimensionalPosition position )
        {
            AtomicReference< PortalNetwork > retval = new AtomicReference<>( null );
            
            networks.forEach( ( net ) ->
            {
                if( net.isRegisteredPosition( position ) )
                    retval.set( net );
            } );

            return retval.get();
        }

        public boolean hasNetwork( final DimensionalPosition position )
        {
            return getNetwork( position ) != null;
        }*/

        public boolean hasNetwork( UUID identifier )
        {
            return getNetwork( identifier ) != null;
        }

        public boolean hasNetwork( String label )
        {
            return getNetwork( label ) != null;
        }

        public void deleteNetwork( final UUID identifier )
        {
            Iterator< PortalNetwork > iterator = networks.iterator();
            PortalNetwork net = null;
            
            while( iterator.hasNext() )
            {
                net = iterator.next();
                
                if( net.getUUID().equals( identifier ) )
                {
                    iterator.remove();
                    markDirty();
                    break;
                }
            }
        }

        public void deleteNetwork( final PortalNetwork network )
        {
            deleteNetwork( network.getUUID() );
        }

        public < P extends IPortal > void addPublicPortal( P portal )
        {
            if( !hasPublicPortal( portal ) )
                publicPortals.add( portal );
        }

        public boolean hasPublicPortal( final UUID identifier )
        {
            AtomicReference< Boolean > retval = new AtomicReference<>( false );

            publicPortals.forEach( ( portal ) -> 
            {
                if( portal.getUUID().equals( identifier ) )
                    retval.set( true );
            });

            return retval.get().booleanValue();
        }

        public boolean hasPublicPortal( final String label )
        {
            AtomicReference< Boolean > retval = new AtomicReference<>( false );

            publicPortals.forEach( ( portal ) -> 
            {
                if( portal.getLabel().equals( label ) )
                    retval.set( true );
            });

            return retval.get().booleanValue();
        }

        public < P extends IPortal > boolean hasPublicPortal( final P portal )
        {
            return publicPortals.contains( portal );
        }

        public IPortal getPublicPortal( final UUID identifier )
        {
            AtomicReference< IPortal > retval = new AtomicReference<>( null );

            publicPortals.forEach( ( portal ) -> 
            {
                if( portal.getUUID().equals( identifier ) )
                    retval.set( portal );
            });

            return retval.get();
        }

        public IPortal getPublicPortal( final String label )
        {
            AtomicReference< IPortal > retval = new AtomicReference<>( null );

            publicPortals.forEach( ( portal ) -> 
            {
                if( portal.getLabel().equals( label ) )
                    retval.set( portal );
            });

            return retval.get();
        }

        public < P extends IPortal > void deletePublicPortal( final P portal )
        {
            if( hasPublicPortal( portal ) )
                publicPortals.remove( portal );
        }

        // Apparently need to keep portal positions stored & auto load chunks....
        @Override public void readFromNBT( NBTTagCompound nbt )
        {
            Set< String > networkIDs = nbt.getKeySet();

            networkIDs.forEach( ( nid ) ->
            {
                NBTTagCompound temp = nbt.getCompoundTag( nid );
                UUID identifier = UUID.fromString( nid );

                if( hasNetwork( identifier ) )
                    getNetwork( identifier ).readFromNBT( temp );
                else
                    networks.add( PortalNetwork.fromNBT( identifier, temp ) );
            });
        }

        @Override public NBTTagCompound writeToNBT( NBTTagCompound nbt )
        {
            networks.forEach( ( n ) -> { nbt.setTag( n.getUUID().toString(), n.writeToNBT( new NBTTagCompound() ) ); } );
            return nbt;
        }

        public void doPortalChunkLoad()
        {
            
        }
        
        // need: saveNBT/loadNBT & hooks

    // Private Static Fields

        private static PortalManager instance = null;

    // Private Fields

        private List< PortalNetwork > networks;
        private List< IPortal > publicPortals;

    // Private Constructor

        private PortalManager()
        {
            networks = new ArrayList<>();
        }

    // Private Methods


    // Methods

        // ID, name, channels themselves <names and ids>, endpoints list <names and ids>
}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// End of 'ConduitManager.java'
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
