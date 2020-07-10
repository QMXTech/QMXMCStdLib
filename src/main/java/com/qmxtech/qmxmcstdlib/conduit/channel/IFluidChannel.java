package com.qmxtech.qmxmcstdlib.conduit.channel;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// IFluidChannel.java
// Matthew J. Schultz (Korynkai) | Created : 30NOV19 | Last Modified : 06DEC19 by Matthew J. Schultz (Korynkai)
// Version : 0.0.1
// This is a source file for 'QMXMCStdLib'; it defines an interface for a fluid conduit channel.
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

import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidTankProperties;
import net.minecraftforge.fluids.FluidStack;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicReference;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// The 'IFluidChannel' Interface
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

public interface IFluidChannel extends IFilterableChannel< FluidStack, IFluidHandler >
{
    // Methods

        @Override default boolean hasPath( FluidStack object )
        {
            AtomicReference< Boolean > fromPath = new AtomicReference<>( false );
            AtomicReference< Boolean > toPath = new AtomicReference<>( false );

            if( hasPath() )
            {
                from().forEach( ( handler ) ->
                {
                    IFluidTankProperties tanks[] = handler.getTankProperties();

                    for( int i = 0; i < tanks.length; i++ )
                    {
                        if( ( tanks[ i ].getContents() != null ) && tanks[ i ].getContents().isFluidEqual( object ) )
                        {
                            fromPath.set( true );
                            break;
                        }
                    }
                });

                to().forEach( ( handler ) ->
                {
                    IFluidTankProperties tanks[] = handler.getTankProperties();

                    for( int i = 0; i < tanks.length; i++ )
                    {
                        if( ( tanks[ i ].getContents() == null ) || tanks[ i ].getContents().isFluidEqual( object ) )
                        {
                            toPath.set( true );
                            break;
                        }
                    }
                });
            }

            return fromPath.get() && toPath.get();
        }

        @Override default void refresh()
        {
            Iterator< IFluidHandler > fromIt = from().iterator();
            Iterator< IFluidHandler > toIt = to().iterator();
            IFluidHandler f, t;
            ArrayList< IFluidHandler > fromUsable = new ArrayList<>();
            ArrayList< IFluidHandler > toUsable = new ArrayList<>();
            int processed, held = 0, maxFrom = 0, max = 0, i;
            IFluidTankProperties tanks[] = null;
            FluidStack currentFluid = null;
            boolean mod = false;
    
            if( hasPath() )
            {
                while( fromIt.hasNext() )
                {
                    f = fromIt.next();

                    tanks = f.getTankProperties();

                    for( i = 0; i < tanks.length; i++ )
                    {
                        if( filter( tanks[ i ].getContents() ) && hasPath( tanks[ i ].getContents() ) )
                        {
                            if( currentFluid == null )
                            {
                                currentFluid = tanks[ i ].getContents();

                                mod = true;
                            }

                            if( tanks[ i ].canDrainFluidType( currentFluid ) )
                            {
                                fromUsable.add( f );

                                if( mod )
                                    mod = false;
                            }
                            else if( mod )
                            {
                                currentFluid = null;
                            }
                        }
                    }
                }
    
                while( toIt.hasNext() )
                {
                    t = toIt.next();

                    tanks = t.getTankProperties();

                    for( i = 0; i < tanks.length; i++ )
                    {
                        if( tanks[ i ].canFillFluidType( currentFluid ) )
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
                    maxFrom += fromIt.next().drain( new FluidStack( currentFluid.getFluid(), getRate() ), false ).amount;
    
                while( toIt.hasNext() )
                    max += toIt.next().fill( new FluidStack( currentFluid.getFluid(), ( maxFrom < getRate() ) ? maxFrom : getRate() ), false );
                
                fromIt = fromUsable.iterator();
                toIt = toUsable.iterator();
    
                while( fromIt.hasNext() )
                {
                    processed = fromIt.next().drain( new FluidStack( currentFluid.getFluid(), ( max < getRate() ) ? max : getRate() ), true ).amount;
                    max -= processed;
                    held += processed;
                }
    
                while( toIt.hasNext() )
                    held -= toIt.next().fill( new FluidStack( currentFluid.getFluid(), ( held < getRate() ) ? held : getRate() ), true );
    
                // Should we error here when held > 0?
            }
            
        }
}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// End of 'IFluidChannel.java'
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
