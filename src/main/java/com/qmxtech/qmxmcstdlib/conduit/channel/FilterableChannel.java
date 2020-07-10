package com.qmxtech.qmxmcstdlib.conduit.channel;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// FilterableChannel.java
// Matthew J. Schultz (Korynkai) | Created : 02SEP19 | Last Modified : 05DEC19 by Matthew J. Schultz (Korynkai)
// Version : 0.0.1
// This is a source file for 'QMXMCStdLib'; it defines an abstract conduit channel.
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

import com.qmxtech.qmxmcstdlib.conduit.filter.IFilter;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// The 'FilterableChannel' Abstract Class
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

public abstract class FilterableChannel< Filterable extends Object, Handler extends Object > 
    extends FlowingChannel< Handler > implements IFilterableChannel< Filterable, Handler >
{
    // Public Constructor

        public FilterableChannel( final UUID identifier, final String label, final String type )
        {
            super( identifier, label, type );
        }

        public FilterableChannel( final String label, final String type )
        {
            super( label, type );
        }

        public FilterableChannel( final String type )
        {
            super( type );
        }
    
    // Public Methods

        @Override public void addFilter( IFilter< Filterable > filter )
        {
            filterList.add( filter );
        }

        @Override public void removeFilter( IFilter< Filterable > filter )
        {
            filterList.remove( filter );
        }

        @Override public boolean hasFilter( IFilter< Filterable > filter )
        {
            return filterList.contains( filter );
        }

        @Override public void clearFilterList()
        {
            filterList.clear();
        }

        @Override public List< IFilter< Filterable > > getFilterList()
        {
            return filterList;
        }

        @Override public boolean filter( Filterable object )
        {
            Iterator< IFilter< Filterable > > flit = null;
            boolean retval = false;

            if( filterList.isEmpty() )
            {
                retval = true; // default to whitelist all
            }
            else
            {
                flit = filterList.iterator();

                while( flit.hasNext() )
                {
                    if( flit.next().filter( object ) )
                    {
                        retval = true;
                        break;
                    }
                }
            }

            return retval;
        }

    // Protected Fields

        protected LinkedList< IFilter< Filterable > > filterList = new LinkedList<>();
}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// End of 'IChannel.java'
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
