package com.qmxtech.qmxmcstdlib;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// QMXMCStdLib.java
// Matthew J. Schultz (Korynkai) | Created : 16AUG19 | Last Modified : 16AUG19 by Matthew J. Schultz (Korynkai)
// Version : 0.0.1
// This is a source file for 'QMXMCStdLib'; it defines the base mod class.
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

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import com.qmxtech.qmxmcstdlib.proxy.CommonProxy;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// The 'QMXMCStdLib' Class
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

@Mod(
		modid = BuildInfo.MOD_ID,
		name = BuildInfo.NAME,
		version = BuildInfo.VERSION + "." + BuildInfo.BUILD_NUMBER,
		dependencies = "required-after:enderio;required-after:opencomputers;after:albedo;after:mirage;"
)
public class QMXMCStdLib
{
	// Fields

		@Instance( BuildInfo.MOD_ID ) public static QMXMCStdLib instance;
		@SidedProxy( clientSide = BuildInfo.CLIENT_PROXY, serverSide = BuildInfo.SERVER_PROXY ) public static CommonProxy proxy;

	// Methods

		@EventHandler public void preInit( FMLPreInitializationEvent event )
		{
			// Perform necessary pre-initialization.

				proxy.preInit();
		}

		@EventHandler public void init( FMLInitializationEvent event )
		{
			// Perform necessary initialization.

				proxy.init();
		}

		@EventHandler public void postInit( FMLPostInitializationEvent event )
		{
			// Perform necessary post-initialization.

				proxy.postInit();
		}
}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// End of 'QMXMCStdLib.java'
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
