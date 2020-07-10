package com.qmxtech.qmxmcstdlib.conduit;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// ConduitManager.java
// Matthew J. Schultz (Korynkai) | Created : 10SEP19 | Last Modified : 05DEC19 by Matthew J. Schultz (Korynkai)
// Version : 0.0.1
// This is a source file for 'QMXMCStdLib'; it defines the global conduit network manager.
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

import com.qmxtech.qmxmcstdlib.conduit.channel.ChannelRegistry;
import com.qmxtech.qmxmcstdlib.conduit.channel.IChannel;
import com.qmxtech.qmxmcstdlib.nbt.IHasNBTSaveableData;
import com.qmxtech.qmxmcstdlib.generic.IRefreshable;
import com.qmxtech.qmxmcstdlib.position.IDimensionalPosition;

import net.minecraft.nbt.NBTTagCompound;

import java.util.concurrent.atomic.AtomicReference;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.UUID;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// The 'ConduitManager' Class
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

public final class ConduitManager implements IConduitMarkDirty, IHasNBTSaveableData, IRefreshable
{
    // Public Static Methods

        public static void initialize()
        {
            ChannelRegistry.initialize(); // Pre-init ChannelRegistry

            if( instance == null )
                instance = new ConduitManager();
        }

        public static ConduitManager getInstance()
        {
            initialize();
            return instance;
        }

    // Public Methods

        public ConduitNetwork createNetwork()
        {
            ConduitNetwork retval = new ConduitNetwork();

            addNetwork( retval );

            return retval;
        }

        public ConduitNetwork createNetwork( final UUID identifier )
        {
            ConduitNetwork retval = null;
            
            if( !hasNetwork( identifier ) )
            {
                retval = new ConduitNetwork( identifier );
                addNetwork( retval );
            }

            return retval;
        }

        public ConduitNetwork createNetwork( final String label )
        {
            ConduitNetwork retval = null;

            if( !hasNetwork( label ) )
            {
                retval = new ConduitNetwork( label );
                addNetwork( retval );
            }

            return retval;
        }

        public ConduitNetwork createNetwork( final UUID identifier, final String label )
        {
            ConduitNetwork retval = null;

            if( !hasNetwork( identifier ) && !hasNetwork( label ) )
            {
                retval = new ConduitNetwork( identifier, label );
                addNetwork( retval );
            }

            return retval;
        }

        public void addNetwork( ConduitNetwork network )
        {
            networks.add( network );
            markDirty();
        }

        public ConduitNetwork getNetwork( final UUID identifier )
        {
            AtomicReference< ConduitNetwork > retval = new AtomicReference<>( null );
            
            networks.forEach( ( net ) ->
            {
                if( net.getUUID().equals( identifier ) )
                    retval.set( net );
            } );

            return retval.get();
        }

        public ConduitNetwork getNetwork( final String label )
        {
            AtomicReference< ConduitNetwork > retval = new AtomicReference<>( null );
            
            networks.forEach( ( net ) ->
            {
                if( net.getLabel().equals( label ) )
                    retval.set( net );
            } );

            return retval.get();
        }

        public ConduitNetwork getNetwork( final IDimensionalPosition position )
        {
            AtomicReference< ConduitNetwork > retval = new AtomicReference<>( null );
            
            networks.forEach( ( net ) ->
            {
                if( net.isRegisteredPosition( position ) )
                    retval.set( net );
            } );

            return retval.get();
        }

        public < C extends IChannel > ConduitNetwork getNetwork( final C channel )
        {
            AtomicReference< ConduitNetwork > retval = new AtomicReference<>( null );

            networks.forEach( ( net ) ->
            {
                if( net.hasChannel( channel ) )
                    retval.set( net );
            });

            return retval.get();
        }

        public boolean hasNetwork( final IDimensionalPosition position )
        {
            return getNetwork( position ) != null;
        }

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
            Iterator< ConduitNetwork > iterator = networks.iterator();
            ConduitNetwork net = null;
            
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

        public void deleteNetwork( ConduitNetwork network )
        {
            deleteNetwork( network.getUUID() );
            markDirty();
        }

        // Called on server tick event
        @Override public void refresh()
        {
            networks.forEach( ( net ) ->
            {
                net.refresh();
            });
        }

        // Static proxy method called on server tick event (calls above method)
        static public void onServerTickEvent()
        {
            getInstance().refresh();
        }

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
                    networks.add( ConduitNetwork.fromNBT( identifier, temp ) );
            });
        }

        @Override public NBTTagCompound writeToNBT( NBTTagCompound nbt )
        {
            networks.forEach( ( n ) -> { nbt.setTag( n.getUUID().toString(), n.writeToNBT( new NBTTagCompound() ) ); } );
            return nbt;
        }
        
        // need: saveNBT/loadNBT & hooks

    // Private Static Fields

        private static ConduitManager instance = null;

    // Private Fields

        private List< ConduitNetwork > networks;

    // Private Constructor

        private ConduitManager()
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
