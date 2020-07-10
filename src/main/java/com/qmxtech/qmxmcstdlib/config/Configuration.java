package com.qmxtech.qmxmcstdlib.config;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Configuration.java
// Matthew J. Schultz (Korynkai) | Created : 10OCT19 | Last Modified : 10OCT19 by Matthew J. Schultz (Korynkai)
// Version : 0.0.1
// This is a source file for 'QMXMCStdLib'; it defines the mod's configuration class.
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

import com.qmxtech.qmxmcstdlib.BuildInfo;
import com.qmxtech.qmxmcstdlib.log.Log;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// The 'ConfigBase' Abstract Class
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

public final class Configuration extends ConfigBase
{
    // Public Static Methods

        public static void init()
        {
            if( instance == null )
                instance = new Configuration();
        }

        public static Configuration getInstance()
        {
            init();            
            return instance;
        }
    
    // Public Methods

        

    // Private Constructors

        private Configuration()
        {
            this( new File( getConfigDirectory(), BuildInfo.CONFIG_FILE ) );
        }

        private Configuration( File file )
        {
            super( file );

            try
            {
                load();

                // General Configuration
                config.setCategoryComment( "general", "General configuration parameters for QMXMCStdLib." );

                recipesVanilla = config.get( "general", "recipesVanilla", recipesVanilla, "Enable vanilla recipes. Default: true" ).getBoolean( true );
                recipesThermalExpansion = config.get( "general", "recipesThermalExpansion", recipesThermalExpansion,
                                                        "Enable Thermal Expansion recipes. Default: true" ).getBoolean( true );
                //recipesProjectRed = config.get( "general", "recipesProjectRed", recipesProjectRed, "Enable Project Red recipes. Default: true" )
                //                                    .getBoolean( true );
                //recipesEnderIO = config.get( "general", "recipesEnderIO", recipesEnderIO, "Enable EnderIO Recipes. Default: true" ).getBoolean( true );
                //recipesMekanism = config.get( "general", "recipesMekanism", recipesMekanism, "Enable Mekanism Recipes. Default: true" ).getBoolean( true );
                //recipesRailcraft = config.get( "general", "recipesRailcraft", recipesRailcraft, "Enable Railcraft Recipes. Default: true" ).getBoolean( true );

                // Conduit Configuration
                //config.setCategoryComment( "conduits", "Configuration parameters for QMXMCStdLib conduits.");

                // Portal Configuration
                config.setCategoryComment( "portals", "Configuration parameters for QMXMCStdLib portals.");

                portalParticles = config.get( "portals", "particleEffects", portalParticles, "Enable portal particle effects. Default: true" ).getBoolean( true );
                portalSounds = config.get( "portals", "soundEffects", portalSounds, "Enable portal sound effects. Default: true" ).getBoolean( true );
                portalForceFrameOverlay = config.get( "portals", "forceFrameOverlay", portalForceFrameOverlay, "Force portal frame overlay. Default: false" )
                                                    .getBoolean( false );
                portalDestroysBlocks = config.get( "portals", "destroysBlocks", portalDestroysBlocks, "Destroy blocks within a portal. Default: false")
                                                    .getBoolean( false );
                portalRequiresPower = config.get( "portals", "requiresPower", portalRequiresPower, "Portals require power to function. Default: true")
                                                    .getBoolean( true );
                portalInitCost = config.get( "portals", "initCost", portalInitCost, "Portal power initialization cost. Default: 10000" ).getInt( 10000 );
                portalEntityBaseCost = config.get( "portals", "entityBaseCost", portalEntityBaseCost, "Portal power entity base cost. Default: 1000" )
                                                    .getInt( 1000 );
                portalKeepAliveCost = config.get( "portals", "keepAliveCost", portalKeepAliveCost, "Portal power keepalive cost. Default: 10" ).getInt( 10 );

                // Audio Streaming Configuration
                //config.setCategoryComment( "audio", "Configuration parameters for QMXMCStdLib audio streams." );

                // Video Streaming Configuration
                //config.setCategoryComment( "video", "Configuration parameters for QMXMCStdLib ")

                // Images Configuration
                //config.setCategoryComment( "images", "Configuration parameters for QMXMCStdLib images." );
            }
            catch( Exception e )
            {
                Log.error( "QMXMCStdLib encountered a problem with loading its config file." );
            }
            finally
            {
                save();
            }
        }

    // Private Fields

        private static Configuration instance;

        // General Config Values -- TODO: umm, think about & sort out recipes? If we need to, we can still use GameRegistry instead of JSON...
        private boolean recipesVanilla = true;
        private boolean recipesThermalExpansion = true;
        //private boolean recipesProjectRed = true;
        //private boolean recipesEnderIO = true;
        //private boolean recipesMekanism = true;
        //private boolean recipesRailcraft = true;

        // Portal Config Values
        private boolean portalParticles = true;
        private boolean portalSounds = true;
        private boolean portalForceFrameOverlay = false;
        private boolean portalDestroysBlocks = false;
        private boolean portalRequiresPower = true;
        private int portalInitCost = 10000;
        private int portalEntityBaseCost = 1000;
        private int portalKeepAliveCost = 10;



}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// End of 'ConfigBase.java'
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////