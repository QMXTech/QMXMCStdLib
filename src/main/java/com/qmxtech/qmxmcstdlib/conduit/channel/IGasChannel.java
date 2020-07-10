package com.qmxtech.qmxmcstdlib.conduit.channel;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// IFluidChannel.java
// Matthew J. Schultz (Korynkai) | Created : 01DEC19 | Last Modified : 05DEC19 by Matthew J. Schultz (Korynkai)
// Version : 0.0.1
// This is a source file for 'QMXMCStdLib'; it defines an interface for a Mekanism gas conduit channel.
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

import com.qmxtech.qmxmcstdlib.conduit.ConduitManager;
import com.qmxtech.qmxmcstdlib.position.DimensionalPosition;

import mekanism.api.gas.IGasHandler;
import mekanism.api.gas.GasStack;
import mekanism.api.gas.GasTankInfo;

import java.util.Iterator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicReference;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// The 'IGasChannel' Interface
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

public interface IGasChannel extends IFilterableChannel< GasStack, IGasHandler >
{
    // Methods

        @Override default boolean hasPath( GasStack object )
        {
            AtomicReference< Boolean > fromPath = new AtomicReference<>( false );
            AtomicReference< Boolean > toPath = new AtomicReference<>( false );

            if( hasPath() )
            {
                from().forEach( ( handler ) ->
                {
                    GasTankInfo tanks[] = handler.getTankInfo();

                    for( int i = 0; i < tanks.length; i++ )
                    {
                        if( ( tanks[ i ].getGas() != null ) && tanks[ i ].getGas().isGasEqual( object ) )
                        {
                            fromPath.set( true );
                            break;
                        }
                    }
                });

                to().forEach( ( handler ) ->
                {
                    GasTankInfo tanks[] = handler.getTankInfo();

                    for( int i = 0; i < tanks.length; i++ )
                    {
                        if( ( tanks[ i ].getGas() == null ) || tanks[ i ].getGas().isGasEqual( object ) )
                        {
                            toPath.set( true );
                            break;
                        }
                    }
                });
            }

            return fromPath.get() && toPath.get();
        }

        // I have no f***ing clue how I'm going to get the EnumFacing that Mekanism wants.... WHY ON EARTH IS THIS EVEN NECESSARY?!
        // ConduitManager.getInstance().getNetwork( this ).getEndpointContainer( position, this ).getSideFacing().getOpposite(); // <-- Okay, this is how (OMFG what a pain)
        @Override default void refresh()
        {
            Iterator< IGasHandler > fromIt = from().iterator();
            Iterator< IGasHandler > toIt = to().iterator();
            IGasHandler f, t;
            ArrayList< IGasHandler > fromUsable = new ArrayList<>();
            ArrayList< IGasHandler > toUsable = new ArrayList<>();
            int processed, i, held = 0, maxFrom = 0, max = 0;
            GasTankInfo tanks[] = null;
            GasStack currentGas = null;
            boolean mod = false;
    
            if( hasPath() )
            {
                while( fromIt.hasNext() )
                {
                    f = fromIt.next();

                    tanks = f.getTankInfo();

                    for( i = 0; i < tanks.length; i++ )
                    {
                        if( filter( tanks[ i ].getGas() ) && hasPath( tanks[ i ].getGas() ) )
                        {
                            if( currentGas == null )
                            {
                                currentGas = tanks[ i ].getGas();
                                mod = true;
                            }

                            if( f.canDrawGas(
                                    ConduitManager
                                        .getInstance()
                                        .getNetwork( this )
                                        .getEndpointContainer( DimensionalPosition.fromObject( f ), this )
                                        .getSideFacing()
                                        .getOpposite(),
                                    currentGas.getGas()
                                ) 
                            )
                            {
                                fromUsable.add( f );

                                if( mod )
                                    mod = false;
                            }
                            else if( mod )
                            {
                                currentGas = null;
                            }
                        }
                    }
                }
    
                while( toIt.hasNext() )
                {
                    t = toIt.next();

                    tanks = t.getTankInfo();

                    for( i = 0; i < tanks.length; i++ )
                    {
                        if( t.canReceiveGas(
                                ConduitManager
                                    .getInstance()
                                    .getNetwork( this )
                                    .getEndpointContainer( DimensionalPosition.fromObject( t ), this )
                                    .getSideFacing()
                                    .getOpposite(),
                                currentGas.getGas()
                            )
                        )
                            toUsable.add( t );
                    }
                }
    
                // Randomize order so one input isn't always sending to the same output
                // Should make transfers even where rates are lower or storage objects are empty or near empty
                // TODO: I suppose we could average it out by using max values from both from and to?
    
                Collections.shuffle( fromUsable ); 
                Collections.shuffle( toUsable );
    
                fromIt = fromUsable.iterator();
                toIt = toUsable.iterator();
    
                while( fromIt.hasNext() )
                {
                    f = fromIt.next();

                    maxFrom += f.drawGas(
                        ConduitManager
                            .getInstance()
                            .getNetwork( this )
                            .getEndpointContainer( DimensionalPosition.fromObject( f ), this )
                            .getSideFacing()
                            .getOpposite(), 
                        getRate(),
                        false 
                    ).amount;
                }
    
                while( toIt.hasNext() )
                {
                    t = toIt.next();

                    max += t.receiveGas( 
                        ConduitManager
                            .getInstance()
                            .getNetwork( this )
                            .getEndpointContainer( DimensionalPosition.fromObject( t ), this )
                            .getSideFacing()
                            .getOpposite(),
                        new GasStack(
                            currentGas.getGas(),
                            ( maxFrom < getRate() ) ? maxFrom : getRate()
                        ), 
                        true
                    );
                }
                
                fromIt = fromUsable.iterator();
                toIt = toUsable.iterator();
    
                while( fromIt.hasNext() )
                {
                    f = fromIt.next();

                    processed = f.drawGas(
                        ConduitManager
                            .getInstance()
                            .getNetwork( this )
                            .getEndpointContainer( DimensionalPosition.fromObject( f ), this )
                            .getSideFacing()
                            .getOpposite(),
                        ( max < getRate() ) ? max : getRate(),
                        true
                    ).amount;
                    max -= processed;
                    held += processed;
                }
    
                while( toIt.hasNext() )
                {
                    t = toIt.next();

                    held -= t.receiveGas(
                        ConduitManager
                            .getInstance()
                            .getNetwork( this )
                            .getEndpointContainer( DimensionalPosition.fromObject( t ), this )
                            .getSideFacing()
                            .getOpposite(),
                        new GasStack(
                            currentGas.getGas(),
                            ( held < getRate() ) ? held : getRate() ),
                        true
                    );
                }
    
                // Should we error here when held > 0?
            }
            
        }
}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// End of 'IGasChannel.java'
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
