package com.qmxtech.qmxmcstdlib.conduit.filter;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// ItemFilter.java
// Matthew J. Schultz (Korynkai) | Created : 03SEP19 | Last Modified : 04DEC19 by Matthew J. Schultz (Korynkai)
// Version : 0.0.1
// This is a source file for 'QMXMCStdLib'; it defines a conduit item filter.
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

import com.qmxtech.qmxmcstdlib.generic.ENumericComparisonOperator;

//import crazypants.enderio.util.CapturedMob;

//import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

//import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.oredict.OreDictionary;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// The 'ItemFilter' Class
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

public class ItemFilter implements IFilter< ItemStack >
{
    // Public Static Methods

        public static ItemFilter whitelist()
        {
            return new ItemFilter();
        }

        public static ItemFilter blacklist()
        {
            return new ItemFilter( true );
        }

        public static ItemFilter fromNBT( NBTTagCompound nbt )
        {
            ItemFilter retval = new ItemFilter();

            // VALUES CODE

            return retval;
        }

    // Public Methods

        public ItemFilter withItem( ItemStack item )
        {
            this.item = item;
            return this;
        }

        public ItemFilter withItemMatch( ItemStack item )
        {
            this.item = item;
            this.itemMatch = true;
            return this;
        }

        public ItemFilter withItemMatch()
        {
            this.itemMatch = true;
            return this;
        }

        public ItemFilter withMetadata()
        {
            metadata = true;
            return this;
        }

        public ItemFilter withNBT()
        {
            nbt = true;
            return this;
        }

        public ItemFilter withOredict()
        {
            oredict = true;
            return this;
        }

        public ItemFilter withModid( String modid )
        {
            this.modid = modid;
            return this;
        }

        public ItemFilter withStackSize( int size )
        {
            this.size = size;
            return this;
        }

        /*public ItemFilter withCharge( ENumericComparisonOperator op, int charge )
        {
            this.chargeOp = op;
            this.charge = charge;
            return this;
        }

        public ItemFilter withCharge( int charge )
        {
            return withCharge( ENumericComparisonOperator.EQUALS, charge );
        }

        public ItemFilter withCharge( int chargeLow, int chargeHigh )
        {
            this.chargeHigh = chargeHigh;
            return withCharge( ENumericComparisonOperator.RANGE, chargeLow );
        }*/

        public ItemFilter undamaged()
        {
            this.undamaged = true;
            return this;
        }

        public ItemFilter damaged()
        {
            this.damaged = true;
            return this;
        }

        public ItemFilter matchDamage()
        {
            this.matchDamage = true;
            return this;
        }

        public ItemFilter withDamage( ENumericComparisonOperator op, int damage )
        {
            this.damageOp = op;
            this.damage = damage;
            return this;
        }

        public ItemFilter withDamage( int damage )
        {
            return withDamage( ENumericComparisonOperator.EQUALS, damage );
        }

        public ItemFilter withDamage( int damageLow, int damageHigh )
        {
            this.damageHigh = damageHigh;
            return withDamage( ENumericComparisonOperator.RANGE, damageLow );
        }

        /*public ItemFilter withEnchantment( Enchantment enchantment )
        {
            this.enchantment = enchantment;
            return this;
        }

        public ItemFilter withPriority( int priority )
        {
            this.priority = priority;
            return this;
        }

        @Optional.Method( modid = "enderio" )
        public ItemFilter withSoul( CapturedMob soul )
        {
            this.soul = soul;
            return this;
        }*/

        @Override public boolean filter( ItemStack item )
        {
            boolean retval = true;

            if( ( this.item != null ) && ( 
                ( itemMatch && !( matchDamage ? item.isItemEqual( this.item ) : item.isItemEqualIgnoreDurability( this.item ) ) ) || 
                ( oredict && !OreDictionary.itemMatches( this.item, item, false ) ) || 
                ( metadata && ( item.getMetadata() != this.item.getMetadata() ) ) ||
                ( nbt && !item.getTagCompound().equals( this.item.getTagCompound() ) ) ) ||
                ( ( size > 0 ) && ( item.getCount() != size ) ) )
            {
                retval = false;
            }
            /*else if( chargeOp != ENumericComparisonOperator.NONE )
            {
                switch( chargeOp )
                {
                    case EQUALS:
                        if( )
                            retval = false;
                        break;
                    case GREATER:
                        if( )
                            retval = false;
                        break;
                    case GREATER_EQUALS:
                        if( )
                            retval = false;
                        break;
                    case LESS:
                        if( )
                            retval = false;
                        break;
                    case LESS_EQUALS:
                        if( )
                            retval = false;
                        break;
                }
            }*/
            else if( damageOp != ENumericComparisonOperator.NONE )
            {
                switch( damageOp )
                {
                    case EQUALS:
                        if( item.getItemDamage() != damage )
                            retval = false;
                        break;

                    case GREATER:
                        if( item.getItemDamage() <= damage )
                            retval = false;
                        break;

                    case GREATER_EQUALS:
                        if( item.getItemDamage() < damage )
                            retval = false;
                        break;

                    case LESS:
                        if( item.getItemDamage() >= damage )
                            retval = false;
                        break;

                    case LESS_EQUALS:
                        if( item.getItemDamage() > damage )
                            retval = false;
                        break;

                    case RANGE:
                        if( ( item.getItemDamage() < damage ) || ( item.getItemDamage() > damageHigh ) )
                            retval = false;
                        break;

                    case NONE:
                    default:
                        break;
                }
            }
            else if( ( undamaged && item.isItemDamaged() ) || ( damaged && !item.isItemDamaged() ) )
            {
                retval = false;
            }

            // charge, enchantment, priority, soul

            if( blacklist )
                retval = !retval;

            return retval;

        }

        @Override public NBTTagCompound writeToNBT( NBTTagCompound nbt )
        {
            // VALUES CODE
            return nbt;
        }

        @Override public boolean equals( Object object )
        {
            ItemFilter eif = null;
            boolean retval = false;

            if( object instanceof ItemFilter )
            {
                eif = (ItemFilter) object;

                retval = ( 
                    ( eif.item == item ) &&
                    ( eif.itemMatch == itemMatch ) &&
                    ( eif.matchDamage == matchDamage ) &&
                    ( eif.undamaged == undamaged ) &&
                    ( eif.damaged == damaged ) &&
                    ( eif.blacklist == blacklist ) &&
                    ( eif.metadata == metadata ) &&
                    ( eif.nbt == nbt ) && 
                    ( eif.oredict == oredict ) &&
                    ( eif.modid == modid ) &&
                    ( eif.size == size ) &&
                    //( eif.chargeOp == chargeOp ) &&
                    ( eif.damageOp == damageOp ) &&
                    //( eif.charge == charge ) &&
                    //( eif.chargeHigh == chargeHigh ) &&
                    ( eif.damage == damage ) &&
                    ( eif.damageHigh == damageHigh ) );
                    //( eif.enchantment == enchantment ) &&
                    //( eif.priority == priority ) &&
                    //( eif.soul == soul ) );
            }

            return retval;
        }

    // Protected Fields

        protected ItemStack item = null;
        protected boolean itemMatch = false;
        protected boolean matchDamage = false;
        protected boolean undamaged = false;
        protected boolean damaged = false;
        protected boolean blacklist = false;
        protected boolean metadata = false;
        protected boolean nbt = false;
        protected boolean oredict = false;
        protected String modid = null;
        protected int size = -1;
        //protected ENumericComparisonOperator chargeOp = ENumericComparisonOperator.NONE;
        protected ENumericComparisonOperator damageOp = ENumericComparisonOperator.NONE;
        //protected int charge = -1;
        //protected int chargeHigh = -1;
        protected int damage = -1;
        protected int damageHigh = -1;
        //protected Enchantment enchantment = null;
        //protected int priority = -1;
        //protected Object soul = null;

    // Protected Constructor

        protected ItemFilter()
        {
            // Do nothing.
        }

        protected ItemFilter( boolean blacklist )
        {
            this.blacklist = blacklist;
        }

}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// End of 'ItemFilter.java'
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////