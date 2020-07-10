package com.qmxtech.qmxmcstdlib.registry;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// EventRegistry.java
// Matthew J. Schultz (Korynkai) | Created : 22SEP19 | Last Modified : 27SEP19 by Matthew J. Schultz (Korynkai)
// Version : 0.0.1
// This is a source file for 'QMXMCStdLib'; it defines the mod's event bus subscriber.
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

import com.qmxtech.qmxmcstdlib.conduit.ConduitSavedData;
import com.qmxtech.qmxmcstdlib.conduit.ConduitManager;
import com.qmxtech.qmxmcstdlib.portal.PortalSavedData;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// The EventRegistry' Class
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

@Mod.EventBusSubscriber public final class EventRegistry
{

    // Public Static Methods (Event Listeners)

        @SubscribeEvent public static void onEvent( WorldEvent.Save event )
        {
            ConduitSavedData.setStorage( event.getWorld().getMapStorage() );
            PortalSavedData.setStorage( event.getWorld().getMapStorage() );
        }

        @SubscribeEvent public static void onEvent( WorldEvent.Load event )
        {
            ConduitSavedData.setStorage( event.getWorld().getMapStorage() );
            PortalSavedData.setStorage( event.getWorld().getMapStorage() );
        }

        @SubscribeEvent public static void onEvent( TickEvent.ServerTickEvent event )
        {
            ConduitManager.onServerTickEvent();
        }
}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// End of 'EventRegistry.java'
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
