package com.qmxtech.qmxmcstdlib.position;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// IHasPosition.java
// Matthew J. Schultz (Korynkai) | Created : 20AUG19 | Last Modified : 11OCT19 by Matthew J. Schultz (Korynkai)
// Version : 0.0.1
// This is a source file for 'QMXMCStdLib'; it defines an interface to an object which has a Minecraft BlockPos object.
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
import net.minecraft.util.math.ChunkPos;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// The 'IHasPosition' Interface
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

public interface IHasPosition
{
    // Methods

        BlockPos getPosition();

        default ChunkPos getChunkPosition()
        {
            return new ChunkPos( getPosition() );
        }

        default int getX()
        {
            return getPosition().getX();
        }

        default int getY()
        {
            return getPosition().getY();
        }

        default int getZ()
        {
            return getPosition().getZ();
        }

        default boolean isNeighbor( IHasPosition position )
        {
            boolean retval = false;

            if( ( getPosition().up() == position.getPosition() ) || ( getPosition().down() == position.getPosition() ) ||
                ( getPosition().north() == position.getPosition() ) || ( getPosition().south() == position.getPosition() ) ||
                ( getPosition().east() == position.getPosition() ) || ( getPosition().west() == position.getPosition() ) )
                retval = true;
            
            return retval;
        }
}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// End of 'IHasPosition.java'
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
