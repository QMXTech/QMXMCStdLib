package com.qmxtech.qmxmcstdlib.position;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// IDimensionalPosition.java
// Matthew J. Schultz (Korynkai) | Created : 13SEP19 | Last Modified : 13SEP19 by Matthew J. Schultz (Korynkai)
// Version : 0.0.1
// This is a source file for 'QMXMCStdLib'; it defines a class which contains both a Minecraft BlockPos object, and a Minecraft dimension value.
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

import net.minecraft.util.math.BlockPos;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.entity.Entity;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// The 'DimensionalPosition' Class
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

public class DimensionalPosition implements IDimensionalPosition
{
    // Public Constructor

        public DimensionalPosition( final BlockPos position, final int dimension )
        {
            this.position = position;
            this.dimension = dimension;
        }

        public DimensionalPosition( final int x, final int y, final int z, final int dimension )
        {
            this( new BlockPos( x, y, z ), dimension );
        }
    
    // Public Static Methods

        public static DimensionalPosition fromObject( Object object )
        {
            DimensionalPosition retval = null;

            if( object instanceof IHasDimensionalPosition )
                retval = ( (IHasDimensionalPosition) object ).getDimensionalPosition();
            else if( object instanceof TileEntity )
                retval = new DimensionalPosition( ( (TileEntity) object ).getPos(), ( (TileEntity) object ).getWorld().provider.getDimension() );
            else if( object instanceof Entity )
                retval = new DimensionalPosition( ( (Entity) object ).getPosition(), ( (Entity) object ).getEntityWorld().provider.getDimension() );
            
            return retval;
        }

    // Public Methods

        @Override public BlockPos getPosition()
        {
            return position;
        }

        @Override public int getDimension()
        {
            return dimension;
        }

        @Override public boolean equals( final Object object )
        {
            boolean retval = super.equals( object );

            if( ( !retval ) && ( object != null ) && ( object instanceof DimensionalPosition ) && ( ( ( DimensionalPosition ) object ).getDimension() == getDimension() ) ) 
                retval = ( ( DimensionalPosition ) object ).getPosition().equals( getPosition() );
            
            return retval;            
        }

        @Override public int hashCode()
        {
            return 31 * getPosition().hashCode() + getDimension();
        }

    // Protected Fields

        protected BlockPos position;
        protected int dimension;

}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// End of 'DimensionalPosition.java'
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
