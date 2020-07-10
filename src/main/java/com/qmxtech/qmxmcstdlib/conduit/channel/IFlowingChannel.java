package com.qmxtech.qmxmcstdlib.conduit.channel;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// IFlowingChannel.java
// Matthew J. Schultz (Korynkai) | Created : 31AUG19 | Last Modified : 05DEC19 by Matthew J. Schultz (Korynkai)
// Version : 0.0.1
// This is a source file for 'QMXMCStdLib'; it defines an interface for a flowing conduit channel (fluid, gas or power).
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

import com.qmxtech.qmxmcstdlib.position.IDimensionalPosition;
import com.qmxtech.qmxmcstdlib.generic.IRefreshable;

import java.util.List;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// The 'IFlowingChannel' Interface
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

public interface IFlowingChannel< Handler extends Object > extends IChannel, IRefreshable
{
    // Methods

        int getRate();
        void setRate( int rate );

        // NOTE: Typically from() and to() targets are TileEntities. In fact, I can barely, if at all, think of any instance where they aren't...
        // NOTE: However, these may also be instances of such objects as IItemHandler or IFluidHandler; appropriate checks should be made on each object.
        // NOTE: Targets in addFrom and addTo MUST be valid in/out objects for this channel (i.e. a TileEntity containing a tank, chest, etc...)
        // NOTE: All other methods may accept an IDimensionalPosition instance instead.
        List< Handler > from();
        Handler from( IDimensionalPosition position );
        List< Handler > to();
        Handler to( IDimensionalPosition position );
        void addFrom( Handler target );
        void addTo( Handler target );
        void removeFrom( Object target );
        void removeTo( Object target );
        boolean hasFrom();
        boolean hasFrom( Object target );
        boolean hasTo();
        boolean hasTo( Object target );

        default boolean hasPath()
        {
            return hasFrom() && hasTo();
        }

        default boolean hasPath( Object from, Object to )
        {
            return hasFrom( from ) && hasTo( to );
        }
}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// End of 'IFlowingChannel.java'
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
