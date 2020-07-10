package com.qmxtech.qmxmcstdlib.config;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// ConfigBase.java
// Matthew J. Schultz (Korynkai) | Created : 10OCT19 | Last Modified : 10OCT19 by Matthew J. Schultz (Korynkai)
// Version : 0.0.1
// This is a source file for 'QMXMCStdLib'; it defines an abstract mod configuration class.
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

import java.io.File;

import net.minecraft.client.Minecraft;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.common.config.Configuration;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// The 'ConfigBase' Abstract Class
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

public abstract class ConfigBase
{
    // Protected Constructors

        protected ConfigBase()
        {
            // Do nothing.
        }

        protected ConfigBase( File file )
        {
            config = new Configuration( file );
        }

    // Protected Fields

        protected Configuration config;

    // Protected Static Methods

        protected static File getConfigDirectory()
        {
            File retval = null;
            MinecraftServer dsInstance = FMLCommonHandler.instance().getMinecraftServerInstance();

	        if( ( dsInstance != null ) && dsInstance.isDedicatedServer() )
                retval = new File( "./config" );
            else
                retval = new File( Minecraft.getMinecraft().gameDir, "config" );

            return retval;
        }

    // Protected Methods

        protected void save()
        {
            if( config.hasChanged() )
			    config.save();
        }

        protected void load()
        {
            config.load();
        }
}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// End of 'ConfigBase.java'
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////