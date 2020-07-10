package com.qmxtech.qmxmcstdlib.portal;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// PortalNetwork.java
// Matthew J. Schultz (Korynkai) | Created : 11OCT19 | Last Modified : 11OCT19 by Matthew J. Schultz (Korynkai)
// Version : 0.0.1
// This is a source file for 'QMXMCStdLib'; it defines a container for a portal network.
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

import com.qmxtech.qmxmcstdlib.generic.IHasUUID;
import com.qmxtech.qmxmcstdlib.nbt.IHasNBTSaveableData;
import com.qmxtech.qmxmcstdlib.generic.IHasLabel;
//import com.qmxtech.qmxmcstdlib.position.DimensionalPosition;

//import net.minecraft.util.EnumFacing;
import net.minecraft.nbt.NBTTagCompound;

import java.util.concurrent.atomic.AtomicReference;
import java.util.ArrayList;
import java.util.List;
//import java.util.Set;
import java.util.UUID;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// The 'PortalNetwork' Class
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

public class PortalNetwork implements IPortalMarkDirty, IHasNBTSaveableData, IHasUUID, IHasLabel
{
    // Public Methods

        public final UUID getUUID()
        {
            return identifier;
        }

        public final String getLabel()
        {
            return label;
        }

        public void setLabel( final String label )
        {
            this.label = label;
            markDirty();
        }

        public < P extends IPortal > void addPortal( P portal )
        {
            if( !hasPortal( portal ) )
            {
                portals.add( portal );

                if( portal.isPublic() )
                    PortalManager.getInstance().addPublicPortal( portal );
                
                markDirty();
            }
        }

        public IPortal getPortal( final UUID identifier )
        {
            AtomicReference< IPortal > retval = new AtomicReference<>( null );

            portals.forEach( ( portal ) -> 
            {
                if( portal.getUUID().equals( identifier ) )
                    retval.set( portal );
            });

            return retval.get();
        }

        public IPortal getPortal( final String label )
        {
            AtomicReference< IPortal > retval = new AtomicReference<>( null );

            portals.forEach( ( portal ) -> 
            {
                if( portal.getLabel().equals( label ) )
                    retval.set( portal );
            });

            return retval.get();
        }

        public boolean hasPortal( final UUID identifier )
        {
            AtomicReference< Boolean > retval = new AtomicReference<>( false );

            portals.forEach( ( portal ) -> 
            {
                if( portal.getUUID().equals( identifier ) )
                    retval.set( true );
            });

            return retval.get().booleanValue();
        }

        public boolean hasPortal( final String label )
        {
            AtomicReference< Boolean > retval = new AtomicReference<>( false );

            portals.forEach( ( portal ) -> 
            {
                if( portal.getLabel().equals( label ) )
                    retval.set( true );
            });

            return retval.get().booleanValue();
        }

        public < P extends IPortal > boolean hasPortal( final P portal )
        {
            return portals.contains( portal );
        }

        public < P extends IPortal > void deletePortal( final P portal )
        {
            if( hasPortal( portal ) )
            {
                portals.remove( portal );

                if( portal.isPublic() && PortalManager.getInstance().hasPublicPortal( portal ) )
                    PortalManager.getInstance().deletePublicPortal( portal );

                markDirty();
            }
        }

        // Apparently need to keep portal positions stored & auto load chunks....
        @Override public void readFromNBT( NBTTagCompound nbt )
        {
            // Do stuff
        }

        @Override public NBTTagCompound writeToNBT( NBTTagCompound nbt )
        {
            // Do stuff
            return nbt;
        }

        public void doPortalChunkLoad()
        {
            
        }

    // Protected Fields

        protected UUID identifier;
        protected String label;
        protected List< IPortal > portals = new ArrayList<>();

    // Protected Constructors

        protected PortalNetwork( final UUID identifier, final String label )
        {
            this.identifier = identifier;
            this.label = label;
        }

        protected PortalNetwork( final String label )
        {
            this( UUID.randomUUID(), label );
        }
        
        protected PortalNetwork( final UUID identifier )
        {
            this( identifier, null );
        }

        protected PortalNetwork()
        {
            this( UUID.randomUUID(), null );
        }

        protected static PortalNetwork fromNBT( UUID identifier, NBTTagCompound nbt )
        {
            PortalNetwork retval = new PortalNetwork( identifier, nbt.getString( "label" ) );

            retval.readFromNBT( nbt );

            return retval;
        }

}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// End of 'ConduitNetwork.java'
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////