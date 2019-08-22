package com.qmxtech.qmxmcstdlib.render;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// LightHalo.java
// Matthew J. Schultz (Korynkai), Robert M. Baker (Malacheye) | Created : 20AUG19 | Last Modified : 20AUG19 by Matthew J. Schultz (Korynkai)
// Version : 0.0.1
// This is a source file for 'QMXMCStdLib'; it defines a TileEntitySpecialRenderer to create a halo around a light.
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

import com.qmxtech.qmxmcstdlib.color.ColorValue;
import com.qmxtech.qmxmcstdlib.color.IColored;

import com.qmxtech.qmxmcstdlib.tile.lighting.TELightBase;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// The 'LightHalo' Class
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

@SuppressWarnings( "unused" )
public class LightHalo extends TileEntitySpecialRenderer< TELightBase >
{
    // Public Methods

        @Override public boolean isGlobalRenderer( TELightBase target )
        {
            // Report whether or not this tile entity should be rendered globally to calling routine.

                return false;
        }

        @Override public void render(
                TELightBase tileEntity,
                double relativeX,
                double relativeY,
                double relativeZ,
                float partialTicks,
                int blockDamageProgress,
                float alpha
        )
        {
            // Create local variables.

                ColorValue color = ColorValue.WHITE;
                float brightness = ( tileEntity.getBrightness() / 15.0f );

                try
                {
                    if( tileEntity instanceof IColored )
                        color = ( (IColored) tileEntity ).getColor();

                    Tessellator tessellator = Tessellator.getInstance();
                    BufferBuilder bufferBuilder = tessellator.getBuffer();

                    GL11.glPushMatrix();
                    GL11.glPushAttrib( GL11.GL_ENABLE_BIT );
                    GL11.glEnable( GL11.GL_BLEND );
                    GL11.glDisable( GL11.GL_LIGHTING );
                    GL11.glDepthMask( false );
                    GL11.glBlendFunc( GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA );

                    GL11.glColor4f( color.rFloat(), color.gFloat(), color.bFloat(), brightness );
                    GlStateManager.translate( relativeX, relativeY, relativeZ );
                    OpenGlHelper.setLightmapTextureCoords( OpenGlHelper.lightmapTexUnit, ( 240.0f * brightness ), 0.0f );
                    bindTexture( haloTexture );
                    bufferBuilder.begin( GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX );
                    addHaloVertices( bufferBuilder );
                    tessellator.draw();
                }
                finally
                {
                    GL11.glPopAttrib();
                    GL11.glPopMatrix();
                }
        }

    // Protected Fields

        @SuppressWarnings( "WeakerAccess" )
        protected static final ResourceLocation haloTexture = new ResourceLocation( "qmxmcstdlib:textures/entity/lighthalo.png" );

    // Protected Methods

        @SuppressWarnings( "WeakerAccess" )
        protected void addHaloVertices( BufferBuilder bufferBuilder )
        {
            // Create local variables.

                final double[][] vertices = {
                        { 1.05, -0.05, -0.05, 1.000, 1.000 },
                        { 1.05, -0.05, 1.05, 1.000, 0.000 },
                        { -0.05, -0.05, 1.05, 0.000, 0.000 },
                        { -0.05, -0.05, -0.05, 0.000, 1.000 },

                        { -0.05, 1.05, -0.05, 0.000, 1.000 },
                        { -0.05, 1.05, 1.05, 0.000, 0.000 },
                        { 1.05, 1.05, 1.05, 1.000, 0.000 },
                        { 1.05, 1.05, -0.05, 1.000, 1.000 },

                        { -0.05, 1.05, -0.05, 0.000, 1.000 },
                        { -0.05, -0.05, -0.05, 0.000, 0.000 },
                        { -0.05, -0.05, 1.05, 1.000, 0.000 },
                        { -0.05, 1.05, 1.05, 1.000, 1.000 },

                        { 1.05, 1.05, 1.05, 1.000, 1.000 },
                        { 1.05, -0.05, 1.05, 1.000, 0.000 },
                        { 1.05, -0.05, -0.05, 0.000, 0.000 },
                        { 1.05, 1.05, -0.05, 0.000, 1.000 },

                        { -0.05, 1.05, 1.05, 0.000, 1.000 },
                        { -0.05, -0.05, 1.05, 0.000, 0.000 },
                        { 1.05, -0.05, 1.05, 1.000, 0.000 },
                        { 1.05, 1.05, 1.05, 1.000, 1.000 },

                        { 1.05, 1.05, -0.05, 1.000, 1.000 },
                        { 1.05, -0.05, -0.05, 1.000, 0.000 },
                        { -0.05, -0.05, -0.05, 0.000, 0.000 },
                        { -0.05, 1.05, -0.05, 0.000, 1.000 }
                };

            // Add halo vertices to renderer.

                for( double[] vertex : vertices )
                    bufferBuilder.pos( vertex[ 0 ], vertex[ 1 ], vertex[ 2 ] ).tex( vertex[ 3 ], vertex[ 4 ] ).endVertex();
        }
}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// End of 'LightHalo.java'
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
