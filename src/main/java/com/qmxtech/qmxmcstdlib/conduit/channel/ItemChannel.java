package com.qmxtech.qmxmcstdlib.conduit.channel;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// ItemChannel.java
// Matthew J. Schultz (Korynkai) | Created : 31AUG19 | Last Modified : 05DEC19 by Matthew J. Schultz (Korynkai)
// Version : 0.0.1
// This is a source file for 'QMXMCStdLib'; it defines an item conduit channel.
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

import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;

import java.util.UUID;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// The 'ItemChannel' Class
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

public class ItemChannel extends FilterableChannel< ItemStack, IItemHandler > implements IItemChannel
{
    // Public Constants

        public static final String TYPE = "item";

    // Public Constructor

        public ItemChannel( final UUID identifier, final String label )
        {
            super( identifier, label, TYPE );
        }

        public ItemChannel( final String label )
        {
            super( label, TYPE );
        }

        public ItemChannel()
        {
            super( TYPE );
        }
}

// Insert (from cond): { Priority:int, Filter:Filter }
// Extract (to cond): { Round Robin:boolean, Self Feed:boolean, Redstone:Redstone, Filter:Filter, Speed:int }
// Redstone: { Sel: Active W/, Active W/out, Always Active, Never Active }
// Charge: { Value, Sel: >, >=, =, <=, < }
// Damage: { Sel: <=25%, >25%, <=50%, >50%, <= 75%, >75%, 0%, 1-100%, can be, cannot be, ignore }
// Filter: { whitelist/blacklist:inventory w/ switch, metadata:boolean, nbt:boolean, oredict:boolean, damage, modid: string, sticky: boolean(force to this or other sticky), stack size: int, preexisting/invsnapshot, charge, soul (EIO), enchantment }
// endpoint has filters; splits each stack into individual items (as ItemStack)
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// End of 'ItemChannel.java'
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
