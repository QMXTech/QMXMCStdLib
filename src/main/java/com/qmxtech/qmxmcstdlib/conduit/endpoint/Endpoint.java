package com.qmxtech.qmxmcstdlib.conduit.endpoint;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Endpoint.java
// Matthew J. Schultz (Korynkai) | Created : 31AUG19 | Last Modified : 01SEP19 by Matthew J. Schultz (Korynkai)
// Version : 0.0.1
// This is a source file for 'QMXMCStdLib'; it defines an abstract conduit endpoint.
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

import com.qmxtech.qmxmcstdlib.position.DimensionalPosition;

import java.util.UUID;
import net.minecraft.util.EnumFacing;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// The 'Endpoint' Abstract Class
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

public abstract class Endpoint implements IEndpoint
{
    // Public Constructors

        public Endpoint( final UUID identifier, final String label, final DimensionalPosition position, final EnumFacing side )
        {
            this.identifier = identifier;
            this.label = label;
            this.position = position;
            this.side = side;
        }

        public Endpoint( final UUID identifier, final DimensionalPosition position, final EnumFacing side )
        {
            this( identifier, null, position, side );
        }

        public Endpoint( final String label, final DimensionalPosition position, final EnumFacing side )
        {
            this( UUID.randomUUID(), label, position, side );
        }

        public Endpoint( final DimensionalPosition position, final EnumFacing side )
        {
            this( UUID.randomUUID(), null, position, side );
        }

    // Public Methods

        @Override public DimensionalPosition getDimensionalPosition()
        {
            return position;
        }

        @Override public boolean isDirectional()
        {
            return IEndpoint.super.isDirectional();
        }

        @Override public void setDirection( final EEndpointDirection direction )
        {
            if( isDirectional() )
            {
                this.direction = direction;
                markDirty();
            }
        }

        @Override public EEndpointDirection getDirection()
        {
            return direction;
        }

        @Override public String getChannelType()
        {
            return IEndpoint.super.getChannelType();
        }

        @Override public String getEndpointType()
        {
            return IEndpoint.super.getEndpointType();
        }

        @Override public UUID getUUID()
        {
            return identifier;
        }

        @Override public String getLabel()
        {
            return label;
        }

        @Override public void setLabel( final String label )
        {
            this.label = label;
            markDirty();
        }

        @Override public EnumFacing getSideFacing()
        {
            return side;
        }

        @Override public boolean equals( Object object )
        {
            boolean retval = false;

            if( object instanceof Endpoint )
                retval = ( ( Endpoint ) object ).getUUID().equals( getUUID() );
            
            return retval;
        }

        @Override public int hashCode()
        {
            return 31 * getUUID().hashCode() + ( 31 * getDimensionalPosition().hashCode() + side.hashCode() );
        }

        @Override public boolean isActive()
        {
            return active;
        }

        @Override public boolean setActive( boolean value )
        {
            active = value;
            markDirty();
            return active;
        }


    // Protected Fields
    
        protected DimensionalPosition position;
        protected EnumFacing side;
        protected EEndpointDirection direction = EEndpointDirection.NONE;
        protected UUID identifier;
        protected String label;
        protected boolean active;

}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// End of 'Endpoint.java'
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
