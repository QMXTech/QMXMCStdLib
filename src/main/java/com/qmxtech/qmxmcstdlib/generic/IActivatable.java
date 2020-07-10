package com.qmxtech.qmxmcstdlib.generic;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// IActivatable.java
// Matthew J. Schultz (Korynkai) | Created : 31AUG19 | Last Modified : 02SEP19 by Matthew J. Schultz (Korynkai)
// Version : 0.0.1
// This is a source file for 'QMXMCStdLib'; it defines an interface for an activatable generic object.
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
// The 'IActivatable' Interface
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

public interface IActivatable
{
    // Methods

        // toggle() should just exist, regardless of handling methods.
        default boolean toggle()
        {
            boolean retval = false;

            if( isActive() )
            {
                if( deactivate() )
                    retval = isActive();
            }
            else
            {
                if( activate() )
                    retval = isActive();
            }

            return retval;
        }
        
        // isActive() should always return whether or not this object is *really* active.
        default boolean isActive()
        {
            return false;
        }

        // Basically, either activate()/deactivate() or setActive(bool) need to be implemented to have the functionality of both handling methods.
        // Both methods of handling return true on success, and false on failure.
        // Also, if this is passively active (always active), simply override isActive to return true instead of false.
        default boolean activate()
        {
            return setActive( true );
        }


        default boolean deactivate()
        {
            return setActive( false );
        }


        default boolean setActive( boolean value )
        {
            boolean retval = false;

            if( value )
            {
                if( !isActive() )
                    retval = activate();
            }
            else
            {
                if( isActive() )
                    retval = deactivate();
            }

            return retval;
        }
}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// End of 'IActivatable.java'
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
