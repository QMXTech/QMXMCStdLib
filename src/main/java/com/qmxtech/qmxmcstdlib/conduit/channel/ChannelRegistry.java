package com.qmxtech.qmxmcstdlib.conduit.channel;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// ChannelRegistry.java
// Matthew J. Schultz (Korynkai) | Created : 11SEP19 | Last Modified : 11SEP19 by Matthew J. Schultz (Korynkai)
// Version : 0.0.1
// This is a source file for 'QMXMCStdLib'; it defines the global conduit channel registry.
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

import com.qmxtech.qmxmcstdlib.log.Log;

import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.fml.common.Loader;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// The 'ChannelRegistry' Class
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

public final class ChannelRegistry
{
    // Public Static Methods

        public static void initialize()
        {
            if( instance == null )
                instance = new ChannelRegistry();
        }

        public static ChannelRegistry getInstance()
        {
            initialize();
            return instance;
        }

    // Public Methods

        public void register( Class< ? extends IChannel > channel )
        {
            String type;
		
            try
            {
                type = ( String ) channel.getField( "TYPE" ).get( null );
                
                if( !channels.containsKey( type ) )
                {
                    channels.put( type, channel );
                }
                else
                {
                    Log.error( new TextComponentTranslation( "exception.qmxmcstdlib.conduit.channelregistry.channelalreadyexists", channel.getName(), type ).getFormattedText()  );
                    return;
                }
            }
            catch( Exception e )
            {
                Log.error( e );
                Log.error( new TextComponentTranslation( "exception.qmxmcstdlib.conduit.channelregistry.unknownchannel", channel.getName() ).getFormattedText()  );
                return;
            }
        }

        public List< String > getChannelList()
        {
            return new ArrayList<>( channels.keySet() );
        }

        public IChannel createChannel( final String type, final UUID identifier, final String label )
        {
            IChannel retval = null;

            try
            {
                retval = channels.get( type ).getConstructor( UUID.class, String.class ).newInstance( identifier, label );
            }
            catch( Exception e )
            {
                Log.error( e );
                Log.error( new TextComponentTranslation( "exception.qmxmcstdlib.conduit.channelregistry.undefinedchannel", type ).getFormattedText()  );
            }

            return retval;
        }

        public IChannel createChannel( final String type, final String label )
        {
            IChannel retval = null;

            try
            {
                retval = channels.get( type ).getConstructor( String.class ).newInstance( label );
            }
            catch( Exception e )
            {
                Log.error( e );
                Log.error( new TextComponentTranslation( "exception.qmxmcstdlib.conduit.channelregistry.undefinedchannel", type ).getFormattedText()  );
            }

            return retval;
        }

        public IChannel createChannel( final String type )
        {
            IChannel retval = null;

            try
            {
                retval = channels.get( type ).getConstructor().newInstance();
            }
            catch( Exception e )
            {
                Log.error( e );
                Log.error( new TextComponentTranslation( "exception.qmxmcstdlib.conduit.channelregistry.undefinedchannel", type ).getFormattedText()  );
            }

            return retval;
        }

        public Class< ? extends IChannel > getChannelClass( final String type )
        {
            return channels.get( type );
        }

    // Private Static Fields

        private static ChannelRegistry instance = null;

    // Private Fields

        private Map< String, Class< ? extends IChannel > > channels;

    // Private Constructor

        private ChannelRegistry()
        {
            channels = new HashMap<>();
            register();
        }

    // Private Methods

        private void register()
        {
            register( ItemChannel.class );
            register( LightingChannel.class );
            //register( AudioChannel.class );
            //register( VideoChannel.class );
            register( FluidChannel.class );
            //register( NetworkChannel.class );
            register( EnergyChannel.class );
            //register( RedstoneChannel.class );

            if( Loader.isModLoaded( "mekanism" ) )
                register( GasChannel.class );
        }
}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// End of 'ChannelRegistry.java'
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
