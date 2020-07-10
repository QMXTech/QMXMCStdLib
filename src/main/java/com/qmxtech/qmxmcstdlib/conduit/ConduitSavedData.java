package com.qmxtech.qmxmcstdlib.conduit;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// ConduitSavedData.java
// Matthew J. Schultz (Korynkai) | Created : 18SEP19 | Last Modified : 20SEP19 by Matthew J. Schultz (Korynkai)
// Version : 0.0.1
// This is a source file for 'QMXMCStdLib'; it defines a class to facilitate saving conduit data in the Minecraft world data.
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
// The 'ConduitSavedData' Class
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

public final class ConduitSavedData extends WorldSavedData implements IHasNBTSaveableData
{
    // Public Static Methods

        public static void setStorage( MapStorage mapstorage )
        {
            storage = mapstorage;
        }

        public static ConduitSavedData getInstance()
        {
            instance = ( ConduitSavedData ) storage.getOrLoadData( ConduitSavedData.class, DATA_NAME );

            if ( instance == null )
            {
                instance = new ConduitSavedData();
                storage.setData( DATA_NAME, instance );
            }

            return instance;
        }

    // Public Methods

        @Override public void readFromNBT( NBTTagCompound nbt )
        {
            ConduitManager.getInstance().readFromNBT( nbt );
        }

        @Override public NBTTagCompound writeToNBT( NBTTagCompound nbt )
        {
            return ConduitManager.getInstance().writeToNBT( nbt );
        }

    // Private Fields

        private static String DATA_NAME = BuildInfo.MOD_ID + "_conduits";
        private static ConduitSavedData instance;
        private static MapStorage storage;

    // Private Constructors

        private ConduitSavedData()
        {
            super( DATA_NAME );
        }

        private ConduitSavedData( String name )
        {
            super( name );
        }

}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// End of 'ConduitSavedData.java'
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
