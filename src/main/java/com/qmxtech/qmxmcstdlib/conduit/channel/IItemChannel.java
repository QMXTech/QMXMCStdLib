package com.qmxtech.qmxmcstdlib.conduit.channel;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// IItemChannel.java
// Matthew J. Schultz (Korynkai) | Created : 30NOV19 | Last Modified : 05DEC19 by Matthew J. Schultz (Korynkai)
// Version : 0.0.1
// This is a source file for 'QMXMCStdLib'; it defines an interface for an item conduit channel.
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

import net.minecraftforge.items.IItemHandler;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicReference;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// The 'IItemChannel' Interface
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

public interface IItemChannel extends IFilterableChannel< ItemStack, IItemHandler >
{
    // Methods

        @Override default boolean hasPath( ItemStack object )
        {
            AtomicReference< Boolean > fromPath = new AtomicReference<>( false );
            AtomicReference< Boolean > toPath = new AtomicReference<>( false );

            if( hasPath() )
            {
                from().forEach( ( handler ) ->
                {
                    ItemStack stack = null;
                    
                    for( int i = 0; i < handler.getSlots(); i++ )
                    {
                        stack = handler.getStackInSlot( i );

                        if( ( stack != null ) && stack.isItemEqual( object ) )
                        {
                            fromPath.set( true );
                            break;
                        }
                    }
                });

                to().forEach( ( handler ) ->
                {
                    ItemStack stack = null;

                    for( int i = 0; i < handler.getSlots(); i++ )
                    {
                        stack = handler.getStackInSlot( i );

                        if( ( ( ( stack == null ) || stack.isEmpty() ) && handler.isItemValid( i, object ) ) || 
                            ( stack.isItemEqual( object ) && ( stack.getCount() < stack.getMaxStackSize() ) )
                        )
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
            Iterator< IItemHandler > fromIt = from().iterator();
            Iterator< IItemHandler > toIt = to().iterator();
            IItemHandler f, t;
            ArrayList< IItemHandler > fromUsable = new ArrayList<>();
            ArrayList< IItemHandler > toUsable = new ArrayList<>();
            int held = 0, count = 0, ml, i;
            ItemStack currentItem = null, stack = null, newStack = null;
    
            if( hasPath() )
            {
                while( fromIt.hasNext() )
                {
                    f = fromIt.next();

                    for( i = 0; i < f.getSlots(); i++ )
                    {
                        stack = f.getStackInSlot( i );

                        if( filter( stack ) && hasPath( stack ) )
                        {
                            if( currentItem == null )
                            {
                                currentItem = stack;
                                fromUsable.add( f );
                            }
                            else if( ( stack != null ) && !stack.isEmpty() && stack.isItemEqual( currentItem ) )
                            {
                                fromUsable.add( f );
                            }
                        }
                    }
                }
    
                while( toIt.hasNext() )
                {
                    t = toIt.next();

                    for( i = 0; i < t.getSlots(); i++ )
                    {
                        stack = t.getStackInSlot( i );

                        if( ( ( ( stack == null ) || stack.isEmpty() ) && t.isItemValid( i, currentItem ) ) || 
                            ( stack.isItemEqual( currentItem ) && ( stack.getCount() < stack.getMaxStackSize() ) ) )
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
                    ml = 0;

                    for( i = 0; i < f.getSlots(); i++ )
                    {
                        stack = f.getStackInSlot( i );

                        if( ( stack != null ) && stack.isItemEqual( currentItem ) )
                        {
                            if( ( stack.getCount() + ml ) < getRate() )
                                count = stack.getCount();
                            else
                                count = getRate() - ml;
                            
                            f.extractItem( i, count, false );

                            ml += count;
                            
                            if( ml == getRate() )
                                break;
                        }
                    }

                    held += ml;
                }
    
                while( toIt.hasNext() )
                {
                    t = toIt.next();

                    for( i = 0; i < t.getSlots(); i++ )
                    {
                        stack = t.getStackInSlot( i );

                        if( ( ( ( stack == null ) || stack.isEmpty() ) && t.isItemValid( i, currentItem ) ) || 
                            ( stack.isItemEqual( currentItem ) && ( stack.getCount() < stack.getMaxStackSize() ) ) )
                        {
                            newStack = currentItem.copy();

                            count = ( stack.getMaxStackSize() - stack.getCount() ) > getRate() ? 
                                        ( ( held > getRate() ) ? getRate() : held ) : 
                                        ( stack.getMaxStackSize() - stack.getCount () );

                            
                            newStack = t.insertItem( i, newStack, false );

                            if( newStack.getCount() == 0 )
                                held -= count;
                            else
                                held -= ( count - newStack.getCount() );
                        }
                    }

                }
    
                // Should we error here when held > 0?
            }
            
        }
}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// End of 'IItemChannel.java'
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
