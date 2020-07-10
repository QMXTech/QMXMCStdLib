package com.qmxtech.qmxmcstdlib.conduit.channel;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// FlowingChannel.java
// Matthew J. Schultz (Korynkai) | Created : 31AUG19 | Last Modified : 05DEC19 by Matthew J. Schultz (Korynkai)
// Version : 0.0.1
// This is a source file for 'QMXMCStdLib'; it defines an abstract flowing conduit channel (liquid or power).
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
import com.qmxtech.qmxmcstdlib.position.IDimensionalPosition;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// The 'FlowingChannel' Abstract Class
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

public abstract class FlowingChannel< Handler extends Object > extends Channel implements IFlowingChannel< Handler >
{
    // Public Constructor

        public FlowingChannel( final UUID identifier, final String name, final String type )
        {
            super( identifier, name, type );
        }

        public FlowingChannel( final String name, final String type )
        {
            super( name, type );
        }

        public FlowingChannel( final String type )
        {
            super( type );
        }

    // Public Methods

        @Override public void setRate( final int rate )
        {
            this.rate = rate;
            markDirty();
        }

        @Override public int getRate()
        {
            return rate;
        }

        @Override public List< Handler > from()
        {
            return new ArrayList< Handler >( from.values() );
        }

        @Override public Handler from( final IDimensionalPosition position )
        {
            return from.get( position );
        }

        @Override public List< Handler > to()
        {
            return new ArrayList< Handler >( to.values() );
        }

        @Override public Handler to( final IDimensionalPosition position )
        {
            return to.get( position );
        }

        @Override public void addFrom( final Handler target )
        {
            from.put( DimensionalPosition.fromObject( target ), target );
        }

        @Override public void addTo( final Handler target )
        {
            to.put( DimensionalPosition.fromObject( target ), target );
        }

        @Override public void removeFrom( final Object target )
        {
            if( target instanceof IDimensionalPosition )
                from.remove( target );
            else
                from.remove( DimensionalPosition.fromObject( target ) );
        }

        @Override public void removeTo( final Object target )
        {
            if( target instanceof IDimensionalPosition )
                to.remove( target );
            else
                to.remove( DimensionalPosition.fromObject( target ) );
        }

        @Override public boolean hasFrom()
        {
            return !from.isEmpty();
        }

        @Override public boolean hasFrom( final Object target )
        {
            boolean retval = false;

            if( target instanceof IDimensionalPosition )
                retval = from.containsKey( target );
            else
                retval = from.containsKey( DimensionalPosition.fromObject( target ) );

            return retval;
        }
        
        @Override public boolean hasTo()
        {
            return !to.isEmpty();
        }
        
        @Override public boolean hasTo( final Object target )
        {
            boolean retval = false;

            if( target instanceof IDimensionalPosition )
                retval = to.containsKey( target );
            else
                retval = to.containsKey( DimensionalPosition.fromObject( target ) );

            return retval;
        }

    // Protected Fields

        protected HashMap< IDimensionalPosition, Handler > from = new HashMap<>();
        protected HashMap< IDimensionalPosition, Handler > to = new HashMap<>();
        protected int rate;
//        protected int capacity;
//        protected int volume;
}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// End of 'FlowingChannel.java'
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
