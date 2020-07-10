package com.qmxtech.qmxmcstdlib.portal;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// PortalSavedData.java
// Matthew J. Schultz (Korynkai) | Created : 11OCT19 | Last Modified : 11OCT19 by Matthew J. Schultz (Korynkai)
// Version : 0.0.1
// This is a source file for 'QMXMCStdLib'; it defines a class to facilitate saving portal data in the Minecraft world data.
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

import com.qmxtech.qmxmcstdlib.BuildInfo;
import com.qmxtech.qmxmcstdlib.nbt.IHasNBTSaveableData;

import net.minecraft.world.storage.WorldSavedData;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.storage.MapStorage;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// The 'PortalSavedData' Class
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

public final class PortalSavedData extends WorldSavedData implements IHasNBTSaveableData
{
    // Public Static Methods

        public static void setStorage( MapStorage mapstorage )
        {
            storage = mapstorage;
        }

        public static PortalSavedData getInstance()
        {
            instance = ( PortalSavedData ) storage.getOrLoadData( PortalSavedData.class, DATA_NAME );

            if ( instance == null )
            {
                instance = new PortalSavedData();
                storage.setData( DATA_NAME, instance );
            }

            return instance;
        }

    // Public Methods

        @Override public void readFromNBT( NBTTagCompound nbt )
        {
            PortalManager.getInstance().readFromNBT( nbt );
        }

        @Override public NBTTagCompound writeToNBT( NBTTagCompound nbt )
        {
            return PortalManager.getInstance().writeToNBT( nbt );
        }

    // Private Fields

        private static String DATA_NAME = BuildInfo.MOD_ID + "_portals";
        private static PortalSavedData instance;
        private static MapStorage storage;

    // Private Constructors

        private PortalSavedData()
        {
            super( DATA_NAME );
        }

        private PortalSavedData( String name )
        {
            super( name );
        }

}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// End of 'PortalSavedData.java'
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
