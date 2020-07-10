// setDestination( String/UUID )
// activate()
// deactivate()
// ??
package com.qmxtech.qmxmcstdlib.computers.controls;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// IControlPortal.java
// Matthew J. Schultz (Korynkai) | Created : 12OCT19 | Last Modified : 12OCT19 by Matthew J. Schultz (Korynkai)
// Version : 0.0.1
// This is a source file for 'QMXMCStdLib'; it defines an OpenComputers portal controller interface.
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

import com.qmxtech.qmxmcstdlib.nbt.IHasNBTSaveableData;
import com.qmxtech.qmxmcstdlib.portal.IPortal;
import li.cil.oc.api.machine.Arguments;
//import li.cil.oc.api.machine.Callback;
import li.cil.oc.api.machine.Context;
import net.minecraft.nbt.NBTTagCompound;

import java.util.UUID;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// The 'IControlPortal' Interface
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

public interface IControlPortal extends IPortal, IControl, IHasNBTSaveableData
{
    // Methods

        @SuppressWarnings( "unused" )
        //@Callback( doc = "function(string:uuid/label):string:uuid[,string:label] -- Set the destination of the portal. Returns the UUID and optional label" +
        //                 " of the destination." )
        default Object[] setDestination( Context context, Arguments args ) throws Exception
        {
            Object[] retval = null;

            if( args.count() != 1 )
                throw new Exception( "Invalid number of arguments, expected 1" );

            String buf = args.checkString( 0 );

            UUID uuid = null;

            // Just convert if we can. Otherwise we assume it's a label.
            try { uuid = UUID.fromString( buf ); } catch( IllegalArgumentException ignored ) { /* Ignored */ }

            if( uuid == null )
                setDestination( buf );
            else
                setDestination( uuid );

            // Format output

            String label = null;

            try{ label = getDestinationLabel(); } catch( NoSuchFieldException ignored ) { /* Ignored */ }

            if( label == null )
                retval = new Object[]{ getDestinationUUID().toString() };
            else
                retval = new Object[]{ getDestinationUUID().toString(), label };                

            return retval;
        }

        @SuppressWarnings({ "unused", "unhandled" })
        //@Callback( doc = "function():string:uuid[,string:label] -- Get the UUID and optional label of the destination. Guaranteed not to throw." )
        default Object[] getDestination( Context context, Arguments args )// throws Exception
        {
            Object[] retval = null;
            String label = null;

            try{ label = getDestinationLabel(); } catch( NoSuchFieldException ignored ) { /* Ignored */ }

            if( label == null )
                retval = new Object[]{ getDestinationUUID().toString() };
            else
                retval = new Object[]{ getDestinationUUID().toString(), label };
            
            return retval;
        }

        @SuppressWarnings({ "unused" })
        //@Callback( doc = "function():boolean:success -- Activate the portal. Returns true on success. Guaranteed not to throw." )
        default Object[] activate( Context context, Arguments args )// throws Exception
        {
            return new Object[]{ activate() };
        }

        @SuppressWarnings({ "unused" })
        //@Callback( doc = "function():boolean:success -- Deactivate the portal. Returns true on success. Guaranteed not to throw." )
        default Object[] deactivate( Context context, Arguments args )// throws Exception
        {
            return new Object[]{ deactivate() };
        }

        @SuppressWarnings({ "unused" })
        //@Callback( doc = "function():boolean:active -- Check if the portal is active. Returns true if the portal is active. Guaranteed not to throw." )
        default Object[] isActive( Context context, Arguments args )// throws Exception
        {
            return new Object[]{ isActive() };
        }

        @SuppressWarnings({ "unused" })
        //@Callback( doc = "function(boolean:active):boolean:success -- Set whether the portal is active. Returns true on success." )
        default Object[] setActive( Context context, Arguments args ) throws Exception
        {
            if( args.count() != 1 )
                throw new Exception( "Invalid number of arguments, expected 1" );
            
            return new Object[]{ setActive( args.checkBoolean( 0 )) };
        }

        @Override default void readFromNBT( NBTTagCompound nbt )
        {
            IControl.super.readFromNBT( nbt );
            IPortal.super.readFromNBT( nbt );
        }

        @Override default NBTTagCompound writeToNBT( NBTTagCompound nbt )
        {
            return IControl.super.writeToNBT( IPortal.super.writeToNBT( nbt ) );
        }

        // other things???
}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// End of 'IControlPortal.java'
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
