package com.qmxtech.qmxmcstdlib.conduit.channel;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// LightingChannel.java
// Matthew J. Schultz (Korynkai) | Created : 31AUG19 | Last Modified : 11SEP19 by Matthew J. Schultz (Korynkai)
// Version : 0.0.1
// This is a source file for 'QMXMCStdLib'; it defines a lighting conduit channel.
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

import java.util.UUID;

import com.qmxtech.qmxmcstdlib.color.ColorValue;
import com.qmxtech.qmxmcstdlib.color.IColored;
import com.qmxtech.qmxmcstdlib.lighting.ILight;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// The 'LightingChannel' Class
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

public class LightingChannel extends Channel implements IColored, ILight
{
    // Public Constants

        public static final String TYPE = "lighting";

    // Public Constructor

        public LightingChannel( final UUID identifier, final String label )
        {
            super( identifier, label, TYPE );
        }

        public LightingChannel( final String label )
        {
            super( label, TYPE ); 
        }

        public LightingChannel()
        {
            super( TYPE );
        }

    // Public Methods

        public void setColor( final ColorValue color, final boolean ignored ) // I think we don't need this boolean here and we can just call IColored.setColor(ColorValue)?
        {
            this.color = color;
        }

        public ColorValue getColor()
        {
            return color;
        }

        public void setBrightness( final int brightness, final boolean ignored )
        {
            this.brightness = brightness;
        }

        public int getBrightness()
        {
            return brightness;
        }

    // Protected Fields

        protected int brightness;
        protected ColorValue color;
}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// End of 'LightingChannel.java'
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
