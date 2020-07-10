package com.qmxtech.qmxmcstdlib.conduit;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// ConduitNetwork.java
// Matthew J. Schultz (Korynkai) | Created : 31AUG19 | Last Modified : 22SEP19 by Matthew J. Schultz (Korynkai)
// Version : 0.0.1
// This is a source file for 'QMXMCStdLib'; it defines a container for a conduit network.
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

import com.qmxtech.qmxmcstdlib.conduit.channel.IChannel;
import com.qmxtech.qmxmcstdlib.conduit.channel.ChannelRegistry;
import com.qmxtech.qmxmcstdlib.conduit.endpoint.EndpointContainer;
import com.qmxtech.qmxmcstdlib.generic.IHasUUID;
import com.qmxtech.qmxmcstdlib.generic.IRefreshable;
import com.qmxtech.qmxmcstdlib.nbt.IHasNBTSaveableData;
import com.qmxtech.qmxmcstdlib.generic.IHasLabel;
import com.qmxtech.qmxmcstdlib.position.IDimensionalPosition;
import com.qmxtech.qmxmcstdlib.position.DimensionalPosition;

import net.minecraft.util.EnumFacing;
import net.minecraft.nbt.NBTTagCompound;

import java.util.concurrent.atomic.AtomicReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// The 'ConduitNetwork' Class
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

public class ConduitNetwork implements IConduitMarkDirty, IHasNBTSaveableData, IHasUUID, IHasLabel, IRefreshable
{
    // Public Methods

        public final UUID getUUID()
        {
            return identifier;
        }

        public final String getLabel()
        {
            return label;
        }

        public void setLabel( final String label )
        {
            this.label = label;
            markDirty();
        }

        public < C extends IChannel > void addChannel( C channel )
        {
            if( !hasChannel( channel ) )
            {
                channels.add( channel );
                markDirty();
            }
        }

        public IChannel getChannel( final UUID identifier )
        {
            AtomicReference< IChannel > retval = new AtomicReference<>( null );

            channels.forEach( ( chan ) -> 
            {
                if( chan.getUUID().equals( identifier ) )
                    retval.set( chan );
            });

            return retval.get();
        }

        public IChannel getChannel( final String label )
        {
            AtomicReference< IChannel > retval = new AtomicReference<>( null );

            channels.forEach( ( chan ) -> 
            {
                if( chan.getLabel().equals( label ) )
                    retval.set( chan );
            });

            return retval.get();
        }

        public boolean hasChannel( final UUID identifier )
        {
            AtomicReference< Boolean > retval = new AtomicReference<>( false );

            channels.forEach( ( chan ) -> 
            {
                if( chan.getUUID().equals( identifier ) )
                    retval.set( true );
            });

            return retval.get().booleanValue();
        }

        public boolean hasChannel( final String label )
        {
            AtomicReference< Boolean > retval = new AtomicReference<>( false );

            channels.forEach( ( chan ) -> 
            {
                if( chan.getLabel().equals( label ) )
                    retval.set( true );
            });

            return retval.get().booleanValue();
        }

        public < C extends IChannel > boolean hasChannel( final C channel )
        {
            return channels.contains( channel );
        }

        public < C extends IChannel > void deleteChannel( final C channel )
        {
            if( hasChannel( channel ) )
            {
                channels.remove( channel );
                markDirty();
            }
        }

        // NOTE: Commented code removed as unnecessary; Use EndpointContainer
        /*public < E extends IEndpoint > void addEndpoint( E endpoint )
        {
            endpoints.add( endpoint );
        }

        public IEndpoint getEndpoint( final UUID identifier )
        {
            AtomicReference< IEndpoint > retval = new AtomicReference<>( null );

            endpoints.forEach( ( e ) -> 
            {
                if( e.getUUID().equals( identifier ) )
                    retval.set( e );
            });

            return retval.get();
        }

        public IEndpoint getEndpoint( final String label )
        {
            AtomicReference< IEndpoint > retval = new AtomicReference<>( null );

            endpoints.forEach( ( e ) -> 
            {
                if( e.getLabel().equals( label ) )
                    retval.set( e );
            });

            return retval.get();
        }

        public IEndpoint getEndpoint( final DimensionalPosition position, final EnumFacing direction, final String channelType )
        {
            AtomicReference< IEndpoint > retval = new AtomicReference<>( null );

            endpoints.forEach( ( e ) -> 
            {
                if( e.getDimensionalPosition().equals( position ) && e.getSideFacing().equals( direction ) && e.getChannelType().equals( channelType ) )
                    retval.set( e );
            });

            return retval.get();
        }

        public boolean hasEndpoint( final UUID identifier )
        {
            AtomicReference< Boolean > retval = new AtomicReference<>( false );

            endpoints.forEach( ( e ) -> 
            {
                if( e.getUUID().equals( identifier ) )
                    retval.set( true );
            });

            return retval.get().booleanValue();
        }

        public boolean hasEndpoint( final String label )
        {
            AtomicReference< Boolean > retval = new AtomicReference<>( false );

            endpoints.forEach( ( e ) -> 
            {
                if( e.getLabel().equals( label ) )
                    retval.set( true );
            });

            return retval.get().booleanValue();
        }

        public boolean hasEndpoint( final DimensionalPosition position, final EnumFacing direction, final String channelType )
        {
            AtomicReference< Boolean > retval = new AtomicReference<>( false );

            endpoints.forEach( ( e ) -> 
            {
                if( e.getDimensionalPosition().equals( position ) && e.getSideFacing().equals( direction ) && e.getChannelType().equals( channelType ) )
                    retval.set( true );
            });

            return retval.get().booleanValue();
        }

        public < E extends IEndpoint > boolean hasEndpoint( final E endpoint )
        {
            return endpoints.contains( endpoint );
        }

        public < E extends IEndpoint > void deleteEndpoint( final E endpoint )
        {
            endpoints.remove( endpoint );
        }*/

        public void addEndpointContainer( EndpointContainer endpoint )
        {
            if( !hasEndpointContainer( endpoint ) )
            {
                endpoints.add( endpoint );
                markDirty();
            }
        }

        public EndpointContainer getEndpointContainer( final IDimensionalPosition position, final EnumFacing direction )
        {
            AtomicReference< EndpointContainer > retval = new AtomicReference<>( null );

            endpoints.forEach( ( e ) -> 
            {
                if( e.getDimensionalPosition().equals( position ) && e.getSideFacing().equals( direction ) /*&& ( e instanceof EndpointContainer )*/ )
                    retval.set( ( EndpointContainer ) e );
            });

            return retval.get();
        }

        // "Fuzzy" version of method for channels when only the channel and +/- 1 block position are known
        // NOTE: position is +/- 1 block (equal or neighbor)
        public < C extends IChannel > EndpointContainer getEndpointContainer( final IDimensionalPosition position, final C channel )
        {
            AtomicReference< EndpointContainer > retval = new AtomicReference<>( null );

            endpoints.forEach( ( e ) ->
            {
                if( e.getDimensionalPosition().equals( position ) || e.getDimensionalPosition().isNeighbor( position ) )
                    retval.set( ( EndpointContainer ) e );
            });

            return retval.get();
        }

        public EndpointContainer getEndpointContainer( final UUID identifier )
        {
            AtomicReference< EndpointContainer > retval = new AtomicReference<>( null );

            endpoints.forEach( ( e ) -> 
            {
                if( e.getUUID().equals( identifier ) )
                    retval.set( e );
            });

            return retval.get();
        }

        public EndpointContainer getEndpointContainer( final String label )
        {
            AtomicReference< EndpointContainer > retval = new AtomicReference<>( null );

            endpoints.forEach( ( e ) -> 
            {
                if( e.getLabel().equals( label ) )
                    retval.set( e );
            });

            return retval.get();
        }

        public boolean hasEndpointContainer( final IDimensionalPosition position, final EnumFacing direction )
        {
            return getEndpointContainer( position, direction ) != null;
        }

        public boolean hasEndpointContainer( final UUID identifier )
        {
            return getEndpointContainer( identifier ) != null;
        }

        public boolean hasEndpointContainer( final String label )
        {
            return getEndpointContainer( label ) != null;
        }

        public boolean hasEndpointContainer( final EndpointContainer endpoint )
        {
            return endpoints.contains( endpoint );
        }

        public void deleteEndpointContainer( final EndpointContainer endpoint )
        {
            if( hasEndpointContainer( endpoint ) )
            {
                endpoints.remove( endpoint );
                markDirty();
            }
        }

        public void registerPosition( final IDimensionalPosition position )
        {
            if( !isRegisteredPosition( position ) && !ConduitManager.getInstance().hasNetwork( position ) ) 
            {
                positions.add( position );
                markDirty();
            }
        }

        public void unregisterPosition( final IDimensionalPosition position )
        {
            if( isRegisteredPosition( position ) )
            {
                positions.remove( position );
                markDirty();
            }
        }

        public boolean isRegisteredPosition( final IDimensionalPosition position )
        {
            return positions.contains( position );
        }

        @Override public void refresh()
        {
            channels.forEach( ( chan ) ->
            {
                if( chan instanceof IRefreshable )
                    ( (IRefreshable) chan ).refresh();
            });
        }

        @Override public void readFromNBT( NBTTagCompound nbt )
        {
            NBTTagCompound channelNBT = nbt.getCompoundTag( "channels" );
            NBTTagCompound positionNBT = nbt.getCompoundTag( "positions" );
            NBTTagCompound endpointNBT = nbt.getCompoundTag( "endpoints" );
            Set< String > channelIDs = channelNBT.getKeySet();
            Set< String > endpointIDs = endpointNBT.getKeySet();
            Set< String > positionIDs = positionNBT.getKeySet();

            channelIDs.forEach( ( cid ) ->
            {
                NBTTagCompound temp = channelNBT.getCompoundTag( cid );
                UUID identifier = UUID.fromString( cid );

                if( !hasChannel( identifier ) )
                    channels.add( ChannelRegistry.getInstance().createChannel( temp.getString( "type" ), identifier, temp.getString( "label" ) ) );
            } );

            positionIDs.forEach( ( pid ) ->
            {
                NBTTagCompound temp = positionNBT.getCompoundTag( pid );
                DimensionalPosition position = new DimensionalPosition( 
                    temp.getInteger( "x" ), 
                    temp.getInteger( "y" ), 
                    temp.getInteger( "z" ), 
                    temp.getInteger( "dimension" )
                );

                if( !isRegisteredPosition( position ) )
                    positions.add( position );
            } );

            endpointIDs.forEach( ( eid ) ->
            {
                NBTTagCompound temp = endpointNBT.getCompoundTag( eid );
                UUID identifier = UUID.fromString( eid );
                EndpointContainer container;

                if( !hasEndpointContainer( identifier ) )
                {
                    endpoints.add( 
                        new EndpointContainer( 
                            identifier, 
                            temp.getString( "label" ), 
                            new DimensionalPosition( 
                                temp.getInteger( "x" ), 
                                temp.getInteger( "y" ), 
                                temp.getInteger( "z" ), 
                                temp.getInteger( "dimension" ) 
                            ),
                            EnumFacing.byName( temp.getString( "facing" ) )
                        )
                    );
                }

                container = getEndpointContainer( identifier );
                container.setActive( temp.getBoolean( "active" ) );
                container.readFromNBT( temp.getCompoundTag( "endpoints" ) );
            });
        }

        @Override public NBTTagCompound writeToNBT( NBTTagCompound nbt )
        {
            NBTTagCompound channelNBT = new NBTTagCompound();
            NBTTagCompound positionNBT = new NBTTagCompound();
            AtomicReference< Integer > positionID = new AtomicReference<>( 0 ); // No UUID for position, so we make a serial identifier.
            NBTTagCompound endpointNBT = new NBTTagCompound();

            channels.forEach( ( c ) ->
            { // Channels need buffers, too! Prolly put that in IChannel...
                NBTTagCompound temp = new NBTTagCompound();
                temp.setString( "label", c.getLabel() != null ? c.getLabel() : "null" );
                temp.setString( "type", c.getChannelType() );
                channelNBT.setTag( c.getUUID().toString(), temp );
            });

            positions.forEach( ( p ) ->
            {
                NBTTagCompound temp = new NBTTagCompound();
                temp.setInteger( "x", p.getX() );
                temp.setInteger( "y", p.getY() );
                temp.setInteger( "z", p.getZ() );
                temp.setInteger( "dimension", p.getDimension() );
                positionNBT.setTag( positionID.get().toString(), temp );
                positionID.set( positionID.get() + 1 ); // Increment the position serial identifier.
            });

            endpoints.forEach( ( e ) ->
            {
                NBTTagCompound temp = new NBTTagCompound();
                NBTTagCompound temp2 = e.writeToNBT( new NBTTagCompound() );
                temp.setString( "label", e.getLabel() != null ? e.getLabel() : "null" );
                temp.setInteger( "x", e.getX() );
                temp.setInteger( "y", e.getY() );
                temp.setInteger( "z", e.getZ() );
                temp.setInteger( "dimension", e.getDimension() );
                temp.setString( "facing", e.getSideFacing().getName() );
                temp.setBoolean( "active", e.isActive() );
                temp.setTag( "endpoints", temp2 );
                endpointNBT.setTag( e.getUUID().toString(), temp );
            });

            nbt.setTag( "channels", channelNBT );
            nbt.setTag( "positions", positionNBT );
            nbt.setTag( "endpoints", endpointNBT );

            return nbt;
        }

        // ID, name, channels themselves <names and ids>, endpoints list <names and ids>

    // Protected Fields

        protected UUID identifier;
        protected String label;
        protected List< IChannel > channels = new ArrayList<>();
        protected List< EndpointContainer > endpoints = new ArrayList<>();
        protected List< IDimensionalPosition > positions = new ArrayList<>();
    
    // Protected Constructors

        protected ConduitNetwork( final UUID identifier, final String label )
        {
            this.identifier = identifier;
            this.label = label;
        }

        protected ConduitNetwork( final String label )
        {
            this( UUID.randomUUID(), label );
        }
        
        protected ConduitNetwork( final UUID identifier )
        {
            this( identifier, null );
        }

        protected ConduitNetwork()
        {
            this( UUID.randomUUID(), null );
        }

        protected static ConduitNetwork fromNBT( UUID identifier, NBTTagCompound nbt )
        {
            ConduitNetwork retval = new ConduitNetwork( identifier, nbt.getString( "label" ) );

            retval.readFromNBT( nbt );

            return retval;
        }
    
}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// End of 'ConduitNetwork.java'
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
