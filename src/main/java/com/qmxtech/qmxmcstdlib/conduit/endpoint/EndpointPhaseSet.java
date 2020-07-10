package com.qmxtech.qmxmcstdlib.conduit.endpoint;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// EndpointPhaseSet.java
// Matthew J. Schultz (Korynkai) | Created : 31AUG19 | Last Modified : 02SEP19 by Matthew J. Schultz (Korynkai)
// Version : 0.0.1
// This is a source file for 'QMXMCStdLib'; it defines a conduit endpoint phase set.
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

import com.qmxtech.qmxmcstdlib.color.ColorValue;
import com.qmxtech.qmxmcstdlib.conduit.IConduitMarkDirty;
import com.qmxtech.qmxmcstdlib.conduit.channel.IChannel;
import com.qmxtech.qmxmcstdlib.conduit.channel.IFilterableChannel;
import com.qmxtech.qmxmcstdlib.log.Log;

import net.minecraft.util.text.TextComponentTranslation;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nullable;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// The 'EndpointPhaseSet' class
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

public class EndpointPhaseSet< C extends IChannel > implements IConduitMarkDirty
{
    // Public Constructor

        public EndpointPhaseSet()
        {
            this( null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null );
        }

        public EndpointPhaseSet( 
            @Nullable C channelWhite,
            @Nullable C channelOrange,
            @Nullable C channelMagenta,
            @Nullable C channelLightBlue,
            @Nullable C channelYellow,
            @Nullable C channelLime,
            @Nullable C channelPink,
            @Nullable C channelGray,
            @Nullable C channelSilver,
            @Nullable C channelCyan,
            @Nullable C channelPurple,
            @Nullable C channelBlue,
            @Nullable C channelBrown,
            @Nullable C channelGreen,
            @Nullable C channelRed,
            @Nullable C channelBlack
        )
        {
            try
            {
                type = ( String ) getClass().getTypeParameters()[ 0 ].getGenericDeclaration().getField( "TYPE" ).get( null );
                filterable = getClass().getTypeParameters()[ 0 ].getGenericDeclaration().isAssignableFrom( IFilterableChannel.class );
            }
            catch( Exception e )
            {
                Log.error( e );
                Log.error( new TextComponentTranslation( "exception.qmxmcstdlib.conduit.endpointphaseset.typereflection" ).getFormattedText() );
            }
            
            phaseSet = new HashMap<>();
            constructPhase( ColorValue.WHITE, channelWhite );
            constructPhase( ColorValue.ORANGE, channelOrange );
            constructPhase( ColorValue.MAGENTA, channelMagenta );
            constructPhase( ColorValue.LIGHTBLUE, channelLightBlue );
            constructPhase( ColorValue.YELLOW, channelYellow );
            constructPhase( ColorValue.LIME, channelLime );
            constructPhase( ColorValue.PINK, channelPink );
            constructPhase( ColorValue.GRAY, channelGray );
            constructPhase( ColorValue.SILVER, channelSilver );
            constructPhase( ColorValue.CYAN, channelCyan );
            constructPhase( ColorValue.PURPLE, channelPurple );
            constructPhase( ColorValue.BLUE, channelBlue );
            constructPhase( ColorValue.BROWN, channelBrown );
            constructPhase( ColorValue.GREEN, channelGreen );
            constructPhase( ColorValue.RED, channelRed );
            constructPhase( ColorValue.BLACK, channelBlack );
        }

    // Public Methods

        public Map< ColorValue, C > getPhaseSet()
        {
            return phaseSet;
        }

        public @Nullable C getChannel( ColorValue color )
        {
            return phaseSet.get( color );
        }

        public boolean hasChannel( IChannel channel )
        {
            return phaseSet.values().contains( channel );
        }

        public void setChannel( ColorValue color, @Nullable C channel )
        {
            _setChannel( color, channel );
            markDirty();
        }

        public void _setChannel( ColorValue color, @Nullable C channel )
        {
            if( channel != null )
            {
                if( type == "invalid" )
                    type = channel.getChannelType();

                if( channel.getChannelType() != type )
                    return;
                
                if( filterable == false )
                    filterable = ( channel instanceof IFilterableChannel );
            }
                
            phaseSet.replace( color, channel );
        }

        public String getChannelType()
        {
            return type;
        }

        public boolean isFilterable()
        {
            return filterable;
        }

    // Protected Fields

        protected Map< ColorValue, C > phaseSet;
        protected String type = "invalid";
        protected boolean filterable = false;

    // Protected Methods

        protected void constructPhase( ColorValue color, C channel )
        {
            if( channel != null )
            {                
                if( channel.getChannelType() == type )                    
                    phaseSet.put( color, channel );
                else
                    phaseSet.put( color, null );
            }
            else
            {
                phaseSet.put( color, null );
            }
        }
}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// End of 'EndpointPhaseSet.java'
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
