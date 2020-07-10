package com.qmxtech.qmxmcstdlib.conduit.endpoint;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// MultiPhaseEndpoint.java
// Matthew J. Schultz (Korynkai) | Created : 31AUG19 | Last Modified : 02SEP19 by Matthew J. Schultz (Korynkai)
// Version : 0.0.1
// This is a source file for 'QMXMCStdLib'; it defines a multi-phase (single-color) conduit endpoint.
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

import com.qmxtech.qmxmcstdlib.color.ColorValue;
import com.qmxtech.qmxmcstdlib.conduit.channel.IChannel;
import com.qmxtech.qmxmcstdlib.conduit.channel.ItemChannel;
import com.qmxtech.qmxmcstdlib.position.DimensionalPosition;

import net.minecraft.util.EnumFacing;

import javax.annotation.Nullable;

import java.util.HashSet;
import java.util.Set;
import java.util.Iterator;
import java.util.UUID;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// The 'MultiPhaseEndpoint' Class
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

public class MultiPhaseEndpoint< C extends IChannel > extends Endpoint
{
    // Public Constructor

        public MultiPhaseEndpoint( final UUID identifier, final String label, final DimensionalPosition position, final EnumFacing side )
        {
            super( identifier, label, position, side );
            this.phaseSet = new EndpointPhaseSet< C >();
        }

        public MultiPhaseEndpoint( final UUID identifier, final DimensionalPosition position, final EnumFacing side )
        {
            super( identifier, position, side );
            this.phaseSet = new EndpointPhaseSet< C >();
        }

        public MultiPhaseEndpoint( final String label, final DimensionalPosition position, final EnumFacing side )
        {
            super( label, position, side );
            this.phaseSet = new EndpointPhaseSet< C >();
        }

        public MultiPhaseEndpoint( final DimensionalPosition position, final EnumFacing side )
        {
            super( position, side );
            this.phaseSet = new EndpointPhaseSet< C >();
        }

        public MultiPhaseEndpoint( final UUID identifier, final String label, final DimensionalPosition position, final EnumFacing side,
            final EndpointPhaseSet< C > phaseSet
        )
        {
            super( identifier, label, position, side );
            this.phaseSet = phaseSet;
        }

        public MultiPhaseEndpoint( final UUID identifier, final DimensionalPosition position, final EnumFacing side, 
            final EndpointPhaseSet< C > phaseSet
        )
        {
            super( identifier, position, side );
            this.phaseSet = phaseSet;
        }

        public MultiPhaseEndpoint( final String label, final DimensionalPosition position, final EnumFacing side,
            final EndpointPhaseSet< C > phaseSet
        )
        {
            super( label, position, side );
            this.phaseSet = phaseSet;
        }

        public MultiPhaseEndpoint( final DimensionalPosition position, final EnumFacing side, final EndpointPhaseSet< C > phaseSet )
        {
            super( position, side );
            this.phaseSet = phaseSet;
        }

        public MultiPhaseEndpoint( final UUID identifier, final String label, final DimensionalPosition position, final EnumFacing side,
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
            super( identifier, label, position, side );
            this.phaseSet = new EndpointPhaseSet< C >(
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
            );
        }

        public MultiPhaseEndpoint( final UUID identifier, final DimensionalPosition position, final EnumFacing side,
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
            super( identifier, position, side );
            this.phaseSet = new EndpointPhaseSet< C >(
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
            );
        }

        public MultiPhaseEndpoint( final String label, final DimensionalPosition position, final EnumFacing side,
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
            super( label, position, side );
            this.phaseSet = new EndpointPhaseSet< C >(
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
            );
        }

        public MultiPhaseEndpoint( final DimensionalPosition position, final EnumFacing side,
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
            super( position, side );
            this.phaseSet = new EndpointPhaseSet< C >(
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
            );
        }

    // Public Methods

        public void setPhaseChannel( ColorValue color, C channel )
        {
            phaseSet.setChannel( color, channel );
            markDirty();
        }

        public C getPhaseChannel( ColorValue color )
        {
            return phaseSet.getChannel( color );
        }

        public boolean hasChannel( IChannel channel )
        {
            return phaseSet.hasChannel( channel );
        }

        public final String getEndpointType()
        {
            return "multiphase";
        }

        @Override public final String getChannelType()
        {
            return phaseSet.getChannelType();
        }

        public EndpointPhaseSet< C > getPhaseSet()
        {
            return phaseSet;
        }

        @Override public final boolean isFilterable()
        {
            return phaseSet.isFilterable();
        }

        @Override public void addFilter( final IEndpointFilter< ? > filter )
        {
            if( isFilterable() )
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

        @Override public void updateFilter( final IEndpointFilter< ? > oldFilter, final IEndpointFilter< ? > newFilter )
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

        @Override public void removeFilter( final IEndpointFilter< ? > filter )
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

    // Protected Fields

        protected EndpointPhaseSet< C > phaseSet;
        protected Set< IEndpointFilter< ? > > filterSet = null;
}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// End of 'MultiPhaseEndpoint.java'
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
