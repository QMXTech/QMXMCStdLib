package com.qmxtech.qmxmcstdlib.proxy;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// ClientProxy.java
// Matthew J. Schultz (Korynkai) | Created : 16AUG19 | Last Modified : 16AUG19 by Matthew J. Schultz (Korynkai)
// Version : 0.0.1
// This is a source file for 'QMXMCStdLib'; it defines a client-side proxy class.
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

import com.qmxtech.qmxmcstdlib.exception.LoadingScreenException;
import com.qmxtech.qmxmcstdlib.render.LightHalo;
import com.qmxtech.qmxmcstdlib.tile.lighting.TELightBase;

import net.minecraft.client.resources.I18n;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Loader;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// The 'ClientProxy' Class
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

@SuppressWarnings( "unused" )
public class ClientProxy extends CommonProxy
{
	// Methods

		@Override public void preInit()
		{
			// Call superclass method.

				super.preInit();

			// Perform necessary client-side pre-initialization.

			// Be nice and error client if the erroneous condition of loading both Mirage and Albedo exists.

				if( Loader.isModLoaded( "mirage" ) && Loader.isModLoaded( "albedo" ) )
				{
					throw new LoadingScreenException(
						LoadingScreenException.createMessage( I18n.format( "exception.qmxmcstdlib.albedomiragesimul.line1" ), 0xffffff )
							.addLine( I18n.format( "exception.qmxmcstdlib.albedomiragesimul.line2" ), 0xeeeeee )
							.addLine( I18n.format( "exception.qmxmcstdlib.albedomiragesimul.line3" ), 0xeeeeee )
							.addLine( I18n.format( "exception.qmxmcstdlib.albedomiragesimul.line4" ), 0xeeeeee )
							.addLine( I18n.format( "exception.qmxmcstdlib.albedomiragesimul.line5" ), 0xffffff )
					);
				}
		}

		@Override public void init()
		{
			// Call superclass method.

				super.init();

			// Perform necessary client-side initialization.

				ClientRegistry.bindTileEntitySpecialRenderer( TELightBase.class, ( new LightHalo() ) );
		}

		@Override public void postInit()
		{
			// Call superclass method.

				super.postInit();

			// Perform necessary client-side post-initialization.

				/* CODE */
		}
}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// End of 'ClientProxy.java'
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
