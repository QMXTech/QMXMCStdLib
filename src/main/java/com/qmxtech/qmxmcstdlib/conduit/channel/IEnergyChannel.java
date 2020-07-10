package com.qmxtech.qmxmcstdlib.conduit.channel;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// IEnergyChannel.java
// Matthew J. Schultz (Korynkai) | Created : 01DEC19 | Last Modified : 05DEC19 by Matthew J. Schultz (Korynkai)
// Version : 0.0.1
// This is a source file for 'QMXMCStdLib'; it defines an interface for an energy conduit channel.
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

import net.minecraftforge.energy.IEnergyStorage;

import java.util.Iterator;
import java.util.ArrayList;
import java.util.Collections;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// The 'IEnergyChannel' Interface
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

public interface IEnergyChannel extends IFlowingChannel< IEnergyStorage >
{
    // Methods

        @Override default void refresh()
        {
            Iterator< IEnergyStorage > fromIt = from().iterator();
            Iterator< IEnergyStorage > toIt = to().iterator();
            IEnergyStorage f, t;
            ArrayList< IEnergyStorage > fromUsable = new ArrayList<>();
            ArrayList< IEnergyStorage > toUsable = new ArrayList<>();
            int processed, held = 0, maxFrom = 0, max = 0;

            if( hasPath() )
            {
                while( fromIt.hasNext() )
                {
                    f = fromIt.next(); // But the generic already casts you?

                    if( f.canExtract() )
                        fromUsable.add( f );
                }

                while( toIt.hasNext() )
                {
                    t = toIt.next(); // But the generic already casts you?

                    if( t.canReceive() )
                        toUsable.add( t );
                }

                // Randomize order so one input isn't always sending to the same output
                // Should make transfers even where rates are lower or storage objects are empty or near empty
                // TODO: I suppose we could average it out by using max values from both from and to?

                Collections.shuffle( fromUsable ); 
                Collections.shuffle( toUsable );

                fromIt = fromUsable.iterator();
                toIt = toUsable.iterator();

                while( fromIt.hasNext() )
                    maxFrom += fromIt.next().extractEnergy( getRate(), true );

                while( toIt.hasNext() )
                    max += toIt.next().receiveEnergy( ( maxFrom < getRate() ) ? maxFrom : getRate(), true );
                
                fromIt = fromUsable.iterator();
                toIt = toUsable.iterator();

                while( fromIt.hasNext() )
                {
                    processed = fromIt.next().extractEnergy( ( max < getRate() ) ? max : getRate(), false );
                    max -= processed;
                    held += processed;
                }

                while( toIt.hasNext() )
                {
                    processed = toIt.next().receiveEnergy( ( held < getRate() ) ? held : getRate(), false );
                    held -= processed;
                }

                // Should we error here when held > 0?
            }
        }
}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// End of 'IEnergyChannel.java'
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
