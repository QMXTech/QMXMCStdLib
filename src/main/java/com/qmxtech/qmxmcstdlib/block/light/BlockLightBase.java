package com.qmxtech.qmxmcstdlib.block.light;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// BlockLightBase.java
// Matthew J. Schultz (Korynkai) | Created : 20AUG19 | Last Modified : 20AUG19 by Matthew J. Schultz (Korynkai)
// Version : 0.0.1
// This is a source file for 'QMXMCStdLib'; it defines an abstract base light Block.
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

import com.qmxtech.qmxmcstdlib.block.BlockBase;

import com.qmxtech.qmxmcstdlib.lighting.ILight;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

import javax.annotation.Nonnull;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// The 'BlockLightBase' Abstract Class
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

@SuppressWarnings( "unused" )
public abstract class BlockLightBase extends BlockBase
{
    // Public Static Fields (Block Properties)

        public static final PropertyInteger BRIGHTNESS = PropertyInteger.create( "brightness", 0, 15 );

    // Public Constructor

        public BlockLightBase( Material material )
        {
            super( material );
        }

    // Public Methods

        @Override @Nonnull protected BlockStateContainer createBlockState()
        {
            return new BlockStateContainer( this, BRIGHTNESS );
        }

        @SuppressWarnings( "deprecation" )
        @Deprecated @Override @Nonnull public IBlockState getStateFromMeta( int meta )
        {
            return this.getDefaultState().withProperty( BRIGHTNESS, meta );
        }

        @Override
        public int getMetaFromState( IBlockState state )
        {
            return state.getValue( BRIGHTNESS );
        }

        @Override @Nonnull public IBlockState getExtendedState( @Nonnull IBlockState state, IBlockAccess world, BlockPos pos )
        {
            if( world.getTileEntity( pos ) instanceof ILight )
            {
                return state.withProperty( BRIGHTNESS, state.getValue( BRIGHTNESS ) );
            }

            return state;
        }

        @SuppressWarnings( "deprecation" )
        @Deprecated @Override public int getLightValue( IBlockState state )
        {
            lightValue = state.getValue( BRIGHTNESS );
            return lightValue;
        }

        @Override
        public int getLightValue( IBlockState state, IBlockAccess world, BlockPos pos )
        {
            lightValue = state.getValue( BRIGHTNESS );
            return lightValue;
        }
}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// End of 'BlockLightBase.java'
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
