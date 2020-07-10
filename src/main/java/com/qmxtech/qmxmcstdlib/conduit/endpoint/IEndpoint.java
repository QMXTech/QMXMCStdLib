package com.qmxtech.qmxmcstdlib.conduit.endpoint;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// IEndpoint.java
// Matthew J. Schultz (Korynkai) | Created : 31AUG19 | Last Modified : 31AUG19 by Matthew J. Schultz (Korynkai)
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

import com.qmxtech.qmxmcstdlib.conduit.IConduitMarkDirty;
import com.qmxtech.qmxmcstdlib.conduit.channel.IChannel;
import com.qmxtech.qmxmcstdlib.position.IHasDimensionalPosition;
import com.qmxtech.qmxmcstdlib.position.IHasSideFacing;
import com.qmxtech.qmxmcstdlib.generic.IActivatable;
import com.qmxtech.qmxmcstdlib.generic.IHasUUID;
import com.qmxtech.qmxmcstdlib.generic.IHasLabel;

import java.util.Iterator;
import java.util.Set;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// The 'IEndpoint' Interface
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

public interface IEndpoint extends IConduitMarkDirty, IHasDimensionalPosition, IHasSideFacing, IActivatable, IHasUUID, IHasLabel
{
    // Methods

        default boolean hasChannel( IChannel channel )
        {
            return false;
        }

        default boolean isDirectional()
        {
            return false;
        }

        default boolean isFilterable()
        {
            return false;
        }

        default void addFilter( final IEndpointFilter< ? > filter ){}

        default void updateFilter( final IEndpointFilter< ? > oldFilter, final IEndpointFilter< ? > newFilter ){}

        default void removeFilter( final IEndpointFilter< ? > filter ){}

        default Set< IEndpointFilter< ? > > getFilterSet()
        {
            return null;
        }

        default void setFilterSet( Set< IEndpointFilter< ? > > filterSet ){}

        default boolean hasFilterSet()
        {
            return ( getFilterSet() != null );
        }

        default Iterator< IEndpointFilter< ? > > getFilterIterator()
        {
            return null;
        }
        
        default void setDirection( final EEndpointDirection direction ){}

        default EEndpointDirection getDirection()
        {
            return EEndpointDirection.NONE;
        }

        default String getChannelType()
        {
            return "invalid";
        }

        default String getEndpointType()
        {
            return "invalid";
        }
}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// End of 'IEndpoint.java'
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
