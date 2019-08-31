package com.qmxtech.qmxmcstdlib.lighting;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// IColoredLight.java
// Matthew J. Schultz (Korynkai) | Created : 17AUG19 | Last Modified : 18AUG19 by Matthew J. Schultz (Korynkai)
// Version : 0.0.1
// This is a source file for 'QMXMCStdLib'; it defines a colored light mixin for a TileEntity object.
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

import com.qmxtech.qmxmcstdlib.color.IColored;
import com.qmxtech.qmxmcstdlib.position.IHasPosition;

import elucent.albedo.lighting.ILightProvider;

import com.elytradev.mirage.event.GatherLightsEvent;
import com.elytradev.mirage.lighting.ILightEventConsumer;

import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// The 'IColoredLight' Interface
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

// NOTE: com.elytradev.mirage.lighting.IColoredLight is to be removed after 1.12.2.
// See comment in com.elytradev.mirage.lighting.IColoredLight for more details.
@SuppressWarnings( "unused" )
@Optional.InterfaceList({
        @Optional.Interface( iface = "elucent.albedo.lighting.ILightProvider", modid = "albedo" ),
        @Optional.Interface( iface = "com.elytradev.mirage.lighting.ILightEventConsumer", modid = "mirage" )
})
public interface IColoredLight extends ILight, IColored, IHasPosition, ILightProvider, ILightEventConsumer
{
    // Methods

        @Override @SideOnly( Side.CLIENT ) @Optional.Method( modid = "albedo" ) default elucent.albedo.lighting.Light provideLight()
        {
            return elucent.albedo.lighting.Light.builder()
                    .pos( getPosition() )
                    .color( getColor().value(), false )
                    .radius( getBrightness() )
                    .build();
        }

        @Override @SideOnly( Side.CLIENT ) @Optional.Method( modid = "mirage" ) default void gatherLights( GatherLightsEvent event )
        {
            event.add( com.elytradev.mirage.lighting.Light.builder()
                    .pos( getPosition() )
                    .color( getColor().value(), false )
                    .radius( getBrightness() )
                    .intensity( ( 0.25f / 15 ) * getBrightness() )
                    .build() );
        }
}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// End of 'IColoredLight.java'
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
