package com.qmxtech.qmxmcstdlib.conduit.endpoint;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// SinglePhaseEndpoint.java
// Matthew J. Schultz (Korynkai) | Created : 31AUG19 | Last Modified : 02SEP19 by Matthew J. Schultz (Korynkai)
// Version : 0.0.1
// This is a source file for 'QMXMCStdLib'; it defines a single-phase (single-color) conduit endpoint.
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
import com.qmxtech.qmxmcstdlib.conduit.channel.IFlowingChannel;
import com.qmxtech.qmxmcstdlib.conduit.channel.IChannel;
import com.qmxtech.qmxmcstdlib.conduit.channel.IFilterableChannel;
import com.qmxtech.qmxmcstdlib.conduit.channel.ItemChannel;

import net.minecraft.util.EnumFacing;

import java.util.HashSet;
import java.util.Set;
import java.util.Iterator;
import java.util.UUID;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// The 'SinglePhaseEndpoint' Class
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

public class SinglePhaseEndpoint< C extends IChannel > extends Endpoint
{
    // Public Constructor

        public SinglePhaseEndpoint( final UUID identifier, final String label, final DimensionalPosition position, final EnumFacing side )
        {
            super( identifier, label, position, side );
        }

        public SinglePhaseEndpoint( final UUID identifier, final DimensionalPosition position, final EnumFacing side )
        {
            super( identifier, position, side );
        }

        public SinglePhaseEndpoint( final String label, final DimensionalPosition position, final EnumFacing side )
        {
            super( label, position, side );
        }

        public SinglePhaseEndpoint( final DimensionalPosition position, final EnumFacing side )
        {
            super( position, side );
        }

        public SinglePhaseEndpoint( final UUID identifier, final String label, final DimensionalPosition position, final EnumFacing side, final C channel )
        {
            this( identifier, label, position, side );
            setChannel( channel );
        }

        public SinglePhaseEndpoint( final UUID identifier, final DimensionalPosition position, final EnumFacing side, final C channel )
        {
            this( identifier, position, side );
            setChannel( channel );
        }

        public SinglePhaseEndpoint( final String label, final DimensionalPosition position, final EnumFacing side, final C channel )
        {
            this( label, position, side );
            setChannel( channel );
        }

        public SinglePhaseEndpoint( final DimensionalPosition position, final EnumFacing side, final C channel )
        {
            this( position, side );
            setChannel( channel );
        }

    // Public Methods

        @Override public final boolean isDirectional()
        {
            boolean retval = super.isDirectional();

            if( channel instanceof IFlowingChannel )
                retval = true;
            
            return retval;
        }

        @Override public final boolean isFilterable()
        {
            boolean retval = super.isFilterable();

            if( channel instanceof IFilterableChannel )
                retval = true;

            return retval;
        }

        @Override public void addFilter( IEndpointFilter< ? > filter )
        {
            if( channel instanceof IFilterableChannel )
            {
                if( filterSet == null )
                    filterSet = new HashSet< IEndpointFilter< ? > >();
                
                if( getChannelType() == ItemChannel.TYPE )
                {
                    if( filter instanceof EndpointItemFilter )
                    {
                        filterSet.add( filter );
                        markDirty();
                    }
                }
            }
        }

        @Override public void updateFilter( IEndpointFilter< ? > oldFilter, IEndpointFilter< ? > newFilter )
        {
            if( filterSet != null )
            {
                if( filterSet.contains( oldFilter ) )
                {
                    if( filterSet.remove( oldFilter ) )
                    {
                        filterSet.add( newFilter );
                        markDirty();
                    }
                }
            }
        }

        @Override public void removeFilter( IEndpointFilter< ? > filter )
        {
            if( filterSet != null )
            {
                if( filterSet.contains( filter ) )
                {
                    filterSet.remove( filter );
                    markDirty();
                }
            }
        }

        @Override public Set< IEndpointFilter< ? > > getFilterSet()
        {
            return filterSet;
        }

        @Override public void setFilterSet( Set< IEndpointFilter< ? > > filterSet )
        {
            this.filterSet = filterSet;
        }

        @Override public Iterator< IEndpointFilter< ? > > getFilterIterator()
        {
            return filterSet.iterator();
        }

        public C getChannel()
        {
            return channel;
        }

        public boolean hasChannel( IChannel channel )
        {
            return this.channel.equals( channel );
        }

        public void setChannel( C channel )
        {
            _setChannel( channel );
            markDirty();
        }

        // Bypass markDirty()
        public void _setChannel( C channel )
        {
            this.channel = channel;
        }

        @Override public final String getChannelType()
        {
            return channel.getChannelType();
        }


    // Protected Fields
    
        protected C channel;
        protected Set< IEndpointFilter< ? > > filterSet = null;

}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// End of 'SinglePhaseEndpoint.java'
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
