package com.qmxtech.qmxmcstdlib.tile.conduit;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// TEConduitConnectorBase.java
// Matthew J. Schultz (Korynkai) | Created : 30OCT19 | Last Modified : 30OCT19 by Matthew J. Schultz (Korynkai)
// Version : 0.0.1
// This is a source file for 'QMXMCStdLib'; it defines an abstract base TileEntity for a conduit connector.
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

import com.qmxtech.qmxmcstdlib.tile.TileEntityBase;

import net.minecraft.nbt.NBTTagCompound;

import javax.annotation.Nonnull;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// The 'TEConduitConnectorBase' Abstract Class
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

@SuppressWarnings( "WeakerAccess" )
public abstract class TEConduitConnectorBase extends TileEntityBase
{
    // TODO: Methods
}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// End of 'TEConduitConnectorBase.java'
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

// NOTE: can normally provide up to one of each channel per face for singlephase, or 16 of each for multiphase
// NOTE: Multiphase CANNOT be AE or RF (apparently; EIO does not handle this)
// NOTE: needs to be camoflagueable per face -> so needs a renderer
// NOTE: a colored light channel may hook into a computer network channel, but needs to act as a component
// NOTE: a colored light channel may have its own endpoint, also acting as a component
// NOTE: a colored light channel may ALSO hook into 17(? or 32?) redstone channels (16 provide color, rest provide brightness) (uhh, OC, RedNet & PR support redstone values up to 255?)
// NOTE: Forge Capabilities can eliminate several issues, see CompactMachines' TileEntityTunnel; Bundle Endpoint can have its own capability; note mitigations needed for ME & others (Mekanism Gas)