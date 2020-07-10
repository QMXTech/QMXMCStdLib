package com.qmxtech.qmxmcstdlib.conduit.endpoint;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// EndpointContainer.java
// Matthew J. Schultz (Korynkai) | Created : 31AUG19 | Last Modified : 22SEP19 by Matthew J. Schultz (Korynkai)
// Version : 0.0.1
// This is a source file for 'QMXMCStdLib'; it defines an interface for a conduit endpoint.
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
import com.qmxtech.qmxmcstdlib.color.ColorValue;
import com.qmxtech.qmxmcstdlib.conduit.ConduitManager;
import com.qmxtech.qmxmcstdlib.conduit.ConduitNetwork;
import com.qmxtech.qmxmcstdlib.conduit.channel.IChannel;
import com.qmxtech.qmxmcstdlib.log.Log;
import com.qmxtech.qmxmcstdlib.nbt.IHasNBTSaveableData;

import net.minecraft.util.EnumFacing;
import net.minecraft.nbt.NBTTagCompound;

import java.util.concurrent.atomic.AtomicReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.UUID;

import javax.annotation.Nullable;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// The 'EndpointContainer' class
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

// Note: Up to 6 (really 5 because of conduit) EndpointContainer classes may be constructed per endpoint block (as per face). Each EndpointContainer may contain
// up to 1 of any Single or Multi Phase Endpoint per channel type.

public class EndpointContainer extends Endpoint implements IHasNBTSaveableData
{
    // Public Constructor

        public EndpointContainer( final UUID identifier, final String label, final DimensionalPosition position, final EnumFacing side )
        {
            super( identifier, label, position, side );
            endpoints = new ArrayList<>();
        } 

        public EndpointContainer( final String label, final DimensionalPosition position, final EnumFacing side )
        {
            super( label, position, side );
            endpoints = new ArrayList<>();
        }

        public EndpointContainer( final DimensionalPosition position, final EnumFacing side )
        {
            super( position, side );
            endpoints = new ArrayList<>();
        }

    // Public Methods

        public < C extends IChannel > void addSinglePhaseEndpoint( final Class< C > channelClass )
        {
            endpoints.add( new SinglePhaseEndpoint< C >( position, side ) );
            markDirty();
        }

        public < C extends IChannel > void addSinglePhaseEndpoint( final UUID identifier, final Class< C > channelClass )
        {
            endpoints.add( new SinglePhaseEndpoint< C >( identifier, position, side ) );
            markDirty();
        }

        public < C extends IChannel > void addSinglePhaseEndpoint( final String label, final Class< C > channelClass )
        {
            endpoints.add( new SinglePhaseEndpoint< C >( label, position, side ) );
            markDirty();
        }

        public < C extends IChannel > void addSinglePhaseEndpoint( final UUID identifier, final String label, final Class< C > channelClass )
        {
            endpoints.add( new SinglePhaseEndpoint< C >( identifier, label, position, side ) );
            markDirty();
        }

        public < C extends IChannel > void addSinglePhaseEndpoint( final C channel )
        {
            endpoints.add( new SinglePhaseEndpoint< C >( position, side, channel ) );
            markDirty();
        }

        public < C extends IChannel > void addSinglePhaseEndpoint( final UUID identifier, final C channel )
        {
            endpoints.add( new SinglePhaseEndpoint< C >( identifier, position, side, channel ) );
            markDirty();
        }

        public < C extends IChannel > void addSinglePhaseEndpoint( final String label, final C channel )
        {
            endpoints.add( new SinglePhaseEndpoint< C >( label, position, side, channel ) );
            markDirty();
        }

        public < C extends IChannel > void addSinglePhaseEndpoint( final UUID identifier, final String label, final C channel )
        {
            endpoints.add( new SinglePhaseEndpoint< C >( identifier, label, position, side, channel ) );
            markDirty();
        }

        public < C extends IChannel > void addMultiPhaseEndpoint( final Class< C > channelClass )
        {
            endpoints.add( new MultiPhaseEndpoint< C >( position, side ) );
            markDirty();
        }

        public < C extends IChannel > void addMultiPhaseEndpoint( final UUID identifier, final Class< C > channelClass )
        {
            endpoints.add( new MultiPhaseEndpoint< C >( identifier, position, side ) );
            markDirty();
        }

        public < C extends IChannel > void addMultiPhaseEndpoint( final String label, final Class< C > channelClass )
        {
            endpoints.add( new MultiPhaseEndpoint< C >( label, position, side ) );
            markDirty();
        }

        public < C extends IChannel > void addMultiPhaseEndpoint( final UUID identifier, final String label, final Class< C > channelClass )
        {
            endpoints.add( new MultiPhaseEndpoint< C >( identifier, label, position, side ) );
            markDirty();
        }

        public < C extends IChannel > void addMultiPhaseEndpoint( final EndpointPhaseSet< C > set )
        {
            endpoints.add( new MultiPhaseEndpoint< C >( position, side, set ) );
            markDirty();
        }

        public < C extends IChannel > void addMultiPhaseEndpoint( final UUID identifier, final EndpointPhaseSet< C > set )
        {
            endpoints.add( new MultiPhaseEndpoint< C >( identifier, position, side, set ) );
            markDirty();
        }

        public < C extends IChannel > void addMultiPhaseEndpoint( final String label, final EndpointPhaseSet< C > set )
        {
            endpoints.add( new MultiPhaseEndpoint< C >( label, position, side, set ) );
            markDirty();
        }

        public < C extends IChannel > void addMultiPhaseEndpoint( final UUID identifier, final String label, final EndpointPhaseSet< C > set )
        {
            endpoints.add( new MultiPhaseEndpoint< C >( identifier, label, position, side, set ) );
            markDirty();
        }

        public < C extends IChannel > void addMultiPhaseEndpoint( final UUID identifier,
            @Nullable final C channelWhite,
            @Nullable final C channelOrange,
            @Nullable final C channelMagenta,
            @Nullable final C channelLightBlue,
            @Nullable final C channelYellow,
            @Nullable final C channelLime,
            @Nullable final C channelPink,
            @Nullable final C channelGray,
            @Nullable final C channelSilver,
            @Nullable final C channelCyan,
            @Nullable final C channelPurple,
            @Nullable final C channelBlue,
            @Nullable final C channelBrown,
            @Nullable final C channelGreen,
            @Nullable final C channelRed,
            @Nullable final C channelBlack
        )
        {
            endpoints.add( new MultiPhaseEndpoint< C >( identifier, position, side,
                channelWhite,
                channelOrange,
                channelMagenta,
                channelLightBlue,
                channelYellow,
                channelLime,
                channelPink,
                channelGray,
                channelSilver,
                channelCyan,
                channelPurple,
                channelBlue,
                channelBrown,
                channelGreen,
                channelRed,
                channelBlack
            ));
            markDirty();
        }

        public < C extends IChannel > void addMultiPhaseEndpoint( final String label,
            @Nullable final C channelWhite,
            @Nullable final C channelOrange,
            @Nullable final C channelMagenta,
            @Nullable final C channelLightBlue,
            @Nullable final C channelYellow,
            @Nullable final C channelLime,
            @Nullable final C channelPink,
            @Nullable final C channelGray,
            @Nullable final C channelSilver,
            @Nullable final C channelCyan,
            @Nullable final C channelPurple,
            @Nullable final C channelBlue,
            @Nullable final C channelBrown,
            @Nullable final C channelGreen,
            @Nullable final C channelRed,
            @Nullable final C channelBlack
        )
        {
            endpoints.add( new MultiPhaseEndpoint< C >( label, position, side,
                channelWhite,
                channelOrange,
                channelMagenta,
                channelLightBlue,
                channelYellow,
                channelLime,
                channelPink,
                channelGray,
                channelSilver,
                channelCyan,
                channelPurple,
                channelBlue,
                channelBrown,
                channelGreen,
                channelRed,
                channelBlack
            ));
            markDirty();
        }

        public < C extends IChannel > void addMultiPhaseEndpoint( final UUID identifier, final String label,
            @Nullable final C channelWhite,
            @Nullable final C channelOrange,
            @Nullable final C channelMagenta,
            @Nullable final C channelLightBlue,
            @Nullable final C channelYellow,
            @Nullable final C channelLime,
            @Nullable final C channelPink,
            @Nullable final C channelGray,
            @Nullable final C channelSilver,
            @Nullable final C channelCyan,
            @Nullable final C channelPurple,
            @Nullable final C channelBlue,
            @Nullable final C channelBrown,
            @Nullable final C channelGreen,
            @Nullable final C channelRed,
            @Nullable final C channelBlack
        )
        {
            endpoints.add( new MultiPhaseEndpoint< C >( identifier, label, position, side,
                channelWhite,
                channelOrange,
                channelMagenta,
                channelLightBlue,
                channelYellow,
                channelLime,
                channelPink,
                channelGray,
                channelSilver,
                channelCyan,
                channelPurple,
                channelBlue,
                channelBrown,
                channelGreen,
                channelRed,
                channelBlack
            ));
            markDirty();
        }

        public IEndpoint getEndpoint( String label )
        {
            AtomicReference< IEndpoint > retval = new AtomicReference<>( null );
            
            endpoints.forEach( ( e ) ->
            {
                if( e.getLabel().equals( label ) )
                    retval.set( e );
            } );

            return retval.get();
        }

        public IEndpoint getEndpoint( UUID identifier )
        {
            AtomicReference< IEndpoint > retval = new AtomicReference<>( null );
            
            endpoints.forEach( ( e ) ->
            {
                if( e.getUUID().equals( identifier ) )
                    retval.set( e );
            } );

            return retval.get();
        }

        public boolean hasEndpoint( UUID identifier )
        {
            AtomicReference< Boolean > retval = new AtomicReference<>( false );
            
            endpoints.forEach( ( e ) ->
            {
                if( e.getUUID().equals( identifier ) )
                    retval.set( true );
            } );

            return retval.get().booleanValue();
        }

        public boolean hasChannel( IChannel channel )
        {
            AtomicReference< Boolean > retval = new AtomicReference<>( false );

            endpoints.forEach( ( e ) ->
            {
                if( e.hasChannel( channel ) )
                    retval.set( true );
            });

            return retval.get().booleanValue();
        }

        public boolean hasChannelTypeEndpoint( String type )
        {
            AtomicReference< Boolean > retval = new AtomicReference<>( false );
            
            endpoints.forEach( ( e ) ->
            {
                if( e.getChannelType().equals( type ) )
                    retval.set( true );
            } );

            return retval.get().booleanValue();
        }

        public IEndpoint getEndpointByChannelType( String type )
        {
            AtomicReference< IEndpoint > retval = new AtomicReference<>( null );
            
            endpoints.forEach( ( e ) ->
            {
                if( e.getChannelType().equals( type ) )
                    retval.set( e );
            } );

            return retval.get();
        }

        public IEndpoint removeEndpoint( IEndpoint endpoint )
        {
            IEndpoint retval = endpoints.remove( endpoints.indexOf( endpoint ) );
            markDirty();
            return retval;
        }

        public IEndpoint removeEndpoint( String label )
        {
            return removeEndpoint( getEndpoint( label ) );
        }

        public IEndpoint removeEndpoint( UUID identifier )
        {
            return removeEndpoint( getEndpoint( identifier ) );
        }

        public IEndpoint removeEndpointByChannelType( String type )
        {
            return removeEndpoint( getEndpointByChannelType( type ) );
        }

        @SuppressWarnings( "unchecked" )
        @Override public void readFromNBT( NBTTagCompound nbt )
        {
            // TODO: Directional, Filters -> NBTTagCompound.hasKey( String key[, int type])?
            Set< String > endpointIDs = nbt.getKeySet();
            ConduitNetwork network = ConduitManager.getInstance().getNetwork( position );

            endpointIDs.forEach( ( eid ) ->
            {
                UUID identifier = UUID.fromString( eid );
                AtomicReference< Set< IEndpointFilter< ? > > > filters = new AtomicReference<>( null );
                IEndpoint e = null;
                NBTTagCompound temp = nbt.getCompoundTag( eid );
                NBTTagCompound ftemp = null;
                Set< String > fids = null;
                EndpointPhaseSet< IChannel > set = null;
                String endpointType = null;
                
                if( !hasEndpoint( identifier ) )
                {
                    switch( temp.getString( "type" ) )
                    {
                        case "singlephase":
                            endpoints.add(
                                new SinglePhaseEndpoint< IChannel >( 
                                    identifier, 
                                    temp.getString( "label" ),
                                    position,
                                    side,
                                    network.getChannel( UUID.fromString( temp.getString( "channel" ) ) )
                                )
                            );
                            break;
                        case "multiphase":
                            endpoints.add(
                                new MultiPhaseEndpoint< IChannel >(
                                    identifier,
                                    temp.getString( "label" ) != "null" ? temp.getString( "label" ) : null,
                                    position,
                                    side,
                                    temp.getString( "white" ) != "null" ? 
                                        network.getChannel( UUID.fromString( temp.getString( "white" ) ) ) : null,
                                    temp.getString( "orange" ) != "null" ? 
                                        network.getChannel( UUID.fromString( temp.getString( "orange" ) ) ) : null,
                                    temp.getString( "magenta" ) != "null" ? 
                                        network.getChannel( UUID.fromString( temp.getString( "magenta" ) ) ) : null,
                                    temp.getString( "lightblue" ) != "null" ? 
                                        network.getChannel( UUID.fromString( temp.getString( "lightblue" ) ) ) : null, 
                                    temp.getString( "yellow" ) != "null" ? 
                                        network.getChannel( UUID.fromString( temp.getString( "yellow" ) ) ) : null,
                                    temp.getString( "lime" ) != "null" ? 
                                        network.getChannel( UUID.fromString( temp.getString( "lime" ) ) ) : null,
                                    temp.getString( "pink" ) != "null" ? 
                                        network.getChannel( UUID.fromString( temp.getString( "pink" ) ) ) : null,
                                    temp.getString( "gray" ) != "null" ? 
                                        network.getChannel( UUID.fromString( temp.getString( "gray" ) ) ) : null,
                                    temp.getString( "silver" ) != "null" ? 
                                        network.getChannel( UUID.fromString( temp.getString( "silver" ) ) ) : null,
                                    temp.getString( "cyan" ) != "null" ? 
                                        network.getChannel( UUID.fromString( temp.getString( "cyan" ) ) ) : null, 
                                    temp.getString( "purple" ) != "null" ? 
                                        network.getChannel( UUID.fromString( temp.getString( "purple" ) ) ) : null,
                                    temp.getString( "blue" ) != "null" ? 
                                        network.getChannel( UUID.fromString( temp.getString( "blue" ) ) ) : null, 
                                    temp.getString( "brown" ) != "null" ? 
                                        network.getChannel( UUID.fromString( temp.getString( "brown" ) ) ) : null,
                                    temp.getString( "green" ) != "null" ? 
                                        network.getChannel( UUID.fromString( temp.getString( "green" ) ) ) : null, 
                                    temp.getString( "red" ) != "null" ? 
                                        network.getChannel( UUID.fromString( temp.getString( "red" ) ) ) : null,
                                    temp.getString( "black" ) != "null" ? 
                                        network.getChannel( UUID.fromString( temp.getString( "black" ) ) ) : null
                                )
                            );
                            break;
                        case "bundle":
                            // TODO: stub
                            break;
                    }
                }
                else
                {
                    endpointType = temp.getString( "type" );
                    e = getEndpoint( identifier );
                    
                    
                    if( e instanceof SinglePhaseEndpoint )
                    {
                        if( endpointType == "singlephase" )
                        {
                            ( ( SinglePhaseEndpoint< IChannel > ) e )._setChannel( network.getChannel( UUID.fromString( temp.getString( "channel" ) ) ) );
                        }
                        else
                        {
                            Log.warn( "EndpointContainer: Invalid NBT save data state. Endpoint type stored in NBT data differs from preexisting endpoint." );
                            Log.info( "Endpoint UUID associated with previous warning: %s", identifier.toString() );
                        }
                    }
                    else if( e instanceof MultiPhaseEndpoint )
                    {
                        if( endpointType == "multiphase" )
                        {
                            set = ( ( MultiPhaseEndpoint< IChannel > ) getEndpoint( identifier ) ).getPhaseSet();

                            for( ColorValue c : ColorValue.ordinalValues() )
                            {
                                if( !set.getChannel( c ).getUUID().equals( UUID.fromString( temp.getString( c.toString() ) ) ) )
                                    set._setChannel( c, temp.getString( c.toString() ) != "null" ? 
                                        network.getChannel( UUID.fromString( temp.getString( c.toString() ) ) ) : null );
                            };
                        }
                        else
                        {
                            Log.warn( "EndpointContainer: Invalid NBT save data state. Endpoint type stored in NBT data differs from preexisting endpoint." );
                            Log.info( "Endpoint UUID associated with previous warning: %s", identifier.toString() ); // localize?
                        }
                    }
                    // ...
                }

                if( temp.hasKey( "filters" ) )
                {
                    e = getEndpoint( identifier );

                    if( e.getChannelType() == "item" )
                    {
                        filters.set( new HashSet<>() );
                        ftemp = temp.getCompoundTag( "filters" );
                        fids = ftemp.getKeySet();

                        fids.forEach( ( fid ) -> { filters.get().add( EndpointItemFilter.fromNBT( temp.getCompoundTag( fid ) ) ); } );
                    }
                    // ...
                    
                    getEndpoint( identifier ).setFilterSet( filters.get() );
                }

                getEndpoint( identifier ).setActive( temp.getBoolean( "active" ) );
            });
        }

        //@SuppressWarnings( "unchecked" )
        @Override public NBTTagCompound writeToNBT( NBTTagCompound nbt )
        {
            // TODO: Directional, Filters
            endpoints.forEach( ( e ) ->
            {
                NBTTagCompound temp = new NBTTagCompound();
                NBTTagCompound ftemp = new NBTTagCompound();
                AtomicReference< Integer > fid = new AtomicReference<>( 0 ); // No UUID for filters, so we make a serial identifier.
                Set< IEndpointFilter< ? > > fset = null;
                MultiPhaseEndpoint< ? > mpe;

                temp.setString( "label", e.getLabel() != null ? e.getLabel() : "null" );
                
                //temp.setString( "type", e.getEndpointType() );

                if( e instanceof SinglePhaseEndpoint )
                {
                    temp.setString( "channel", ( ( SinglePhaseEndpoint< ? > ) e ).getChannel().getUUID().toString() );
                    temp.setString( "type", "singlephase" );
                }
                else if( e instanceof MultiPhaseEndpoint )
                {
                    mpe = ( MultiPhaseEndpoint< ? > ) e;

                    for( ColorValue c : ColorValue.ordinalValues() )
                        temp.setString( c.toString(), mpe.getPhaseChannel( c ).getUUID().toString() );
                    
                    temp.setString( "type", "multiphase" );
                }
                //else if( e instanceof )

                if( e.hasFilterSet() )
                {
                    fset = e.getFilterSet();

                    fset.forEach( ( f ) ->
                    {
                        ftemp.setTag( fid.get().toString(), f.writeToNBT( new NBTTagCompound() ) );
                        fid.set( fid.get() + 1 );
                    });

                    temp.setTag( "filters", ftemp );
                }

                temp.setBoolean( "active", e.isActive() );
                nbt.setTag( e.getUUID().toString(), temp );
            });

            return nbt;
        }

        @Override public String getChannelType()
        {
            return "container";
        }

    // Protected Fields

        protected List< IEndpoint > endpoints;
        protected UUID identifier;
        protected String label;
}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// End of 'EndpointContainer.java'
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
