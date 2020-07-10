package com.qmxtech.qmxmcstdlib.tile.portal;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// TEPortalControllerBase.java
// Matthew J. Schultz (Korynkai) | Created : 11OCT19 | Last Modified : 11OCT19 by Matthew J. Schultz (Korynkai)
// Version : 0.0.1
// This is a source file for 'QMXMCStdLib'; it defines an abstract base TileEntity for a portal controller.
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

import com.qmxtech.qmxmcstdlib.portal.IPortal;
import com.qmxtech.qmxmcstdlib.portal.PortalNetwork;
import com.qmxtech.qmxmcstdlib.portal.PortalManager;
import com.qmxtech.qmxmcstdlib.computers.controls.IControlPortal;

import li.cil.oc.api.network.Node;

//import net.minecraft.nbt.NBTTagCompound;

import java.util.UUID;
import java.util.NoSuchElementException;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// The 'TEPortalControllerBase' Abstract Class
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

@SuppressWarnings( "WeakerAccess" )
public abstract class TEPortalControllerBase extends TEPortalPartBase implements IControlPortal //, IPortal
{
    // Public Methods

        @Override public void addToNetwork( PortalNetwork network )
        {
            IControlPortal.super.addToNetwork( network );
            this.network = network;
        }

        @Override public PortalNetwork getNetwork()
        {
            return network;
        }

        @Override public void setPublic( boolean isPublic )
        {
            if( isPublic )
                PortalManager.getInstance().addPublicPortal( this );
            else
                PortalManager.getInstance().deletePublicPortal( this );
            
            this.isPublic = isPublic;
        }

        @Override public boolean isPublic()
        {
            return isPublic;
        }

        // Uhh, check if PortalManager has one first???
        @Override public void setLabel( String label )
        {
            this.label = label;
        }

        @Override public String getLabel()
        {
            return label;
        }

        // May be stored in NBT, needs to be generated otherwise?
        @Override public UUID getUUID()
        {
            return uuid;
        }

        @Override public < P extends IPortal > void setDestination( P portal )
        {
            destination = portal;
        }

        @Override public void setDestination( String label ) throws NoSuchElementException
        {
            // First search network, then public
            if( network.hasPortal( label ) )
                destination = network.getPortal( label );
            else if( PortalManager.getInstance().hasPublicPortal( label ) )
                destination = PortalManager.getInstance().getPublicPortal( label );
            else
                throw new NoSuchElementException( "TEPortalControllerBase: No Such Portal for Labelled Destination: " + label );
        }

        @Override public void setDestination( UUID identifier )
        {
            // First search network, then public
            if( network.hasPortal( identifier ) )
                destination = network.getPortal( identifier );
            else if( PortalManager.getInstance().hasPublicPortal( identifier ) )
                destination = PortalManager.getInstance().getPublicPortal( identifier );
            else
                throw new NoSuchElementException( "TEPortalControllerBase: No Such Portal for Identified Destination: " + identifier.toString() );
        }

        @Override public IPortal getDestination()
        {
            return destination;
        }

        @Override public String getDestinationLabel()
        {
            return destination.getLabel();
        }

        @Override public UUID getDestinationUUID()
        {
            return destination.getUUID();
        }

        @Override public boolean activate()
        {
            

            return isActive(); // true on success
        }

        @Override public boolean deactivate()
        {

            return !isActive(); // true on success
        }

        @Override public boolean isActive()
        {
            return isActive;
        }

        /*@Override public void readFromNBT( NBTTagCompound nbt )
        {
            
        }

        @Override public NBTTagCompound writeToNBT( NBTTagCompound nbt )
        {

            return nbt;
        }*/

        @Override public Node _getNode()
        {
            return node;
        }

        @Override public void _setNode( Node node )
        {
            this.node = node;
        }

    // Protected Fields

        protected Node node;
        protected PortalNetwork network;
        protected boolean isPublic;
        protected IPortal destination;
        protected String label;
        protected UUID uuid;
        protected boolean isActive;
}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// End of 'TEPortalControllerBase.java'
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
