package com.qmxtech.qmxmcstdlib.conduit.channel;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Channel.java
// Matthew J. Schultz (Korynkai) | Created : 31AUG19 | Last Modified : 11SEP19 by Matthew J. Schultz (Korynkai)
// Version : 0.0.1
// This is a source file for 'QMXMCStdLib'; it defines an abstract conduit channel.
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

// TODO: AE yet?

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Imports
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

import java.util.UUID;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// The 'Channel' Abstract Class
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

public abstract class Channel implements IChannel
{
    // Public Constructor

        public Channel( final UUID identifier, final String label, final String type )
        {
            this.identifier = identifier;
            this.label = label;
            this.type = type;
        }

        public Channel( final String label, final String type )
        {
            this( UUID.randomUUID(), label, type );
        }

        public Channel( final String type )
        {
            this( null, type );
        }

    // Public Methods

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

        @Override public String getChannelType()
        {
            return type;
        }

        @Override public boolean equals( Object object )
        {
            boolean retval = false;

            if( object instanceof Channel )
                retval = ( ( Channel ) object ).getUUID().equals( getUUID() );
            
            return retval;
        }

        @Override public int hashCode()
        {
            return 31 * getUUID().hashCode();
        }

    // Protected Fields

        protected UUID identifier;
        protected String label;
        protected String type;
}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// End of 'Channel.java'
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
