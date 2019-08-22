package com.qmxtech.qmxmcstdlib.log;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Log.java
// Matthew J. Schultz (Korynkai) | Created : 17AUG19 | Last Modified : 18AUG19 by Matthew J. Schultz (Korynkai)
// Version : 0.0.1
// This is a source file for 'QMXMCStdLib'; it defines a generic interface to the Minecraft log4j log.
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

// QMXMCStdLib local imports
import com.qmxtech.qmxmcstdlib.BuildInfo;

// Mod local imports
// import com.qmxtech.qmxmcstdlib.log.Logger;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.message.EntryMessage;
import org.apache.logging.log4j.message.Message;
import org.apache.logging.log4j.message.MessageFactory;
import org.apache.logging.log4j.util.MessageSupplier;
import org.apache.logging.log4j.util.Supplier;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// The 'Log' Class
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

// NOTE: This class is universal and may be copied for use in any GPL or QMX mod.

@SuppressWarnings( "unused" )
public final class Log
{
    // Public Static Initialization Methods

        @SuppressWarnings( "WeakerAccess" ) // Need to remove this when this is used in MCStdLib
        public static Logger getLogger()
        {
            if( localInstance == null )
                localInstance = new Logger( BuildInfo.MOD_ID );

            return localInstance;
        }

        /* Mod local copy of getLogger():
         public static Logger getLogger()
         {
            if( localInstance == null )
                localInstance = com.qmxtech.qmxmcstdlib.log.Log.getLogger( BuildInfo.MOD_ID );

            return localInstance;
         }
         */

        //@SuppressWarnings( "WeakerAccess" )
        public static Logger getLogger( final String id )
        {
            return getLogger( id, false );
        }

        /**
         * Create a new Log class instance beside our own. Use this for other mods and copy the static methods.
         *
         * @param id
         *  The Mod Identifier.
         * @param forceNoPrefix
         *  Force log not to have "QMX -> " prefix.
         * @return
         *  The Log instance.
         */

        @SuppressWarnings( "WeakerAccess" )
        public static Logger getLogger( final String id, final boolean forceNoPrefix )
        {
            Logger RetVal;

            if( forceNoPrefix )
                RetVal = new Logger( id );
            else
                RetVal = new Logger( "QMX -> " + id );

            return RetVal;
        }

    // Public Static Proxy Methods (so this may act as a static Logger and provide its full functionality)
    // See org.apache.logging.log4j.Logger for full method descriptions.

        public static void catching( Level level, Throwable t )
        {
            getLogger().catching( level, t );
        }

        public static void catching( Throwable t )
        {
            getLogger().catching( t );
        }

        public static void debug( CharSequence message )
        {
            getLogger().debug( message );
        }

        public static void debug( CharSequence message, Throwable t )
        {
            getLogger().debug( message, t );
        }

        public static void debug( Marker marker, CharSequence message )
        {
            getLogger().debug( marker, message );
        }

        public static void debug( Marker marker, CharSequence message, Throwable t )
        {
            getLogger().debug( marker, message, t );
        }

        public static void debug( Marker marker, Message msg )
        {
            getLogger().debug( marker, msg );
        }

        public static void debug( Marker marker, MessageSupplier msgSupplier )
        {
            getLogger().debug( marker, msgSupplier );
        }

        public static void debug( Marker marker, MessageSupplier msgSupplier, Throwable t )
        {
            getLogger().debug( marker, msgSupplier, t );
        }

        public static void debug( Marker marker, Message msg, Throwable t )
        {
            getLogger().debug( marker, msg, t );
        }

        public static void debug( Marker marker, Object message )
        {
            getLogger().debug( marker, message );
        }

        public static void debug( Marker marker, Object message, Throwable t )
        {
            getLogger().debug( marker, message, t );
        }

        public static void debug( Marker marker, String message )
        {
            getLogger().debug( marker, message );
        }

        public static void debug( Marker marker, String message, Object... params )
        {
            getLogger().debug( marker, message, params );
        }

        public static void debug( Marker marker, String message, Object p0 )
        {
            getLogger().debug( marker, message, p0 );
        }

        public static void debug( Marker marker, String message, Object p0, Object p1 )
        {
            getLogger().debug( marker, message, p0, p1 );
        }

        public static void debug( Marker marker, String message, Object p0, Object p1, Object p2 )
        {
            getLogger().debug( marker, message, p0, p1, p2 );
        }

        public static void debug( Marker marker, String message, Object p0, Object p1, Object p2, Object p3 )
        {
            getLogger().debug( marker, message, p0, p1, p2, p3 );
        }

        public static void debug( Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4 )
        {
            getLogger().debug( marker, message, p0, p1, p2, p3, p4 );
        }

        public static void debug( Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5 )
        {
            getLogger().debug( marker, message, p0, p1, p2, p3, p4, p5 );
        }

        public static void debug( Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6 )
        {
            getLogger().debug( marker, message, p0, p1, p2, p3, p4, p5, p6 );
        }

        public static void debug( Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7 )
        {
            getLogger().debug( marker, message, p0, p1, p2, p3, p4, p5, p6, p7 );
        }

        public static void debug( Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8 )
        {
            getLogger().debug( marker, message, p0, p1, p2, p3, p4, p5, p6, p7, p8 );
        }

        public static void debug( Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9 )
        {
            getLogger().debug( marker, message, p0, p1, p2, p3, p4, p5, p6, p7, p8, p9 );
        }

        public static void debug( Marker marker, String message, Supplier< ? >... paramSuppliers )
        {
            getLogger().debug( marker, message, paramSuppliers );
        }

        public static void debug( Marker marker, String message, Throwable t )
        {
            getLogger().debug( marker, message, t );
        }

        public static void debug( Marker marker, Supplier< ? > msgSupplier )
        {
            getLogger().debug( marker, msgSupplier );
        }

        public static void debug( Marker marker, Supplier< ? > msgSupplier, Throwable t )
        {
            getLogger().debug( marker, msgSupplier, t );
        }

        public static void debug( Message msg )
        {
            getLogger().debug( msg );
        }

        public static void debug( MessageSupplier msgSupplier )
        {
            getLogger().debug( msgSupplier );
        }

        public static void debug( MessageSupplier msgSupplier, Throwable t )
        {
            getLogger().debug( msgSupplier, t );
        }

        public static void debug( Message msg, Throwable t )
        {
            getLogger().debug( msg, t );
        }

        public static void debug( Object message )
        {
            getLogger().debug( message );
        }

        public static void debug( Object message, Throwable t )
        {
            getLogger().debug( message, t );
        }

        public static void debug( String message )
        {
            getLogger().debug( message );
        }

        public static void debug( String message, Object... params )
        {
            getLogger().debug( message, params );
        }

        public static void debug( String message, Object p0 )
        {
            getLogger().debug( message, p0 );
        }

        public static void debug( String message, Object p0, Object p1 )
        {
            getLogger().debug( message, p0, p1 );
        }

        public static void debug( String message, Object p0, Object p1, Object p2 )
        {
            getLogger().debug( message, p0, p1, p2 );
        }

        public static void debug( String message, Object p0, Object p1, Object p2, Object p3 )
        {
            getLogger().debug( message, p0, p1, p2, p3 );
        }

        public static void debug( String message, Object p0, Object p1, Object p2, Object p3, Object p4 )
        {
            getLogger().debug( message, p0, p1, p2, p3, p4 );
        }

        public static void debug( String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5 )
        {
            getLogger().debug( message, p0, p1, p2, p3, p4, p5 );
        }

        public static void debug( String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6 )
        {
            getLogger().debug( message, p0, p1, p2, p3, p4, p5, p6 );
        }

        public static void debug( String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7 )
        {
            getLogger().debug( message, p0, p1, p2, p3, p4, p5, p6, p7 );
        }

        public static void debug( String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8 )
        {
            getLogger().debug( message, p0, p1, p2, p3, p4, p5, p6, p7, p8 );
        }

        public static void debug( String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9 )
        {
            getLogger().debug( message, p0, p1, p2, p3, p4, p5, p6, p7, p8, p9 );
        }

        public static void debug( String message, Supplier< ? >... paramSuppliers )
        {
            getLogger().debug( message, paramSuppliers );
        }

        public static void debug( String message, Throwable t )
        {
            getLogger().debug( message, t );
        }

        public static void debug( Supplier< ? > msgSupplier )
        {
            getLogger().debug( msgSupplier );
        }

        public static void debug( Supplier< ? > msgSupplier, Throwable t )
        {
            getLogger().debug( msgSupplier, t );
        }

        @Deprecated
        public static void entry()
        {
            getLogger().entry();
        }

        @Deprecated
        public static void entry( Object... params )
        {
            getLogger().entry( params );
        }

        public static void error( CharSequence message )
        {
            getLogger().error( message );
        }

        public static void error( CharSequence message, Throwable t )
        {
            getLogger().error( message, t );
        }

        public static void error( Marker marker, CharSequence message )
        {
            getLogger().error( marker, message );
        }

        public static void error( Marker marker, CharSequence message, Throwable t )
        {
            getLogger().error( marker, message, t );
        }

        public static void error( Marker marker, Message msg )
        {
            getLogger().error( marker, msg );
        }

        public static void error( Marker marker, MessageSupplier msgSupplier )
        {
            getLogger().error( marker, msgSupplier );
        }

        public static void error( Marker marker, MessageSupplier msgSupplier, Throwable t )
        {
            getLogger().error( marker, msgSupplier, t );
        }

        public static void error( Marker marker, Message msg, Throwable t )
        {
            getLogger().error( marker, msg, t );
        }

        public static void error( Marker marker, Object message )
        {
            getLogger().error( marker, message );
        }

        public static void error( Marker marker, Object message, Throwable t )
        {
            getLogger().error( marker, message, t );
        }

        public static void error( Marker marker, String message )
        {
            getLogger().error( marker, message );
        }

        public static void error( Marker marker, String message, Object... params )
        {
            getLogger().error( marker, message, params );
        }

        public static void error( Marker marker, String message, Object p0 )
        {
            getLogger().error( marker, message, p0 );
        }

        public static void error( Marker marker, String message, Object p0, Object p1 )
        {
            getLogger().error( marker, message, p0, p1 );
        }

        public static void error( Marker marker, String message, Object p0, Object p1, Object p2 )
        {
            getLogger().error( marker, message, p0, p1, p2 );
        }

        public static void error( Marker marker, String message, Object p0, Object p1, Object p2, Object p3 )
        {
            getLogger().error( marker, message, p0, p1, p2, p3 );
        }

        public static void error( Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4 )
        {
            getLogger().error( marker, message, p0, p1, p2, p3, p4 );
        }

        public static void error( Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5 )
        {
            getLogger().error( marker, message, p0, p1, p2, p3, p4, p5 );
        }

        public static void error( Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6 )
        {
            getLogger().error( marker, message, p0, p1, p2, p3, p4, p5, p6 );
        }

        public static void error( Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7 )
        {
            getLogger().error( marker, message, p0, p1, p2, p3, p4, p5, p6, p7 );
        }

        public static void error( Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8 )
        {
            getLogger().error( marker, message, p0, p1, p2, p3, p4, p5, p6, p7, p8 );
        }

        public static void error( Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9 )
        {
            getLogger().error( marker, message, p0, p1, p2, p3, p4, p5, p6, p7, p8, p9 );
        }

        public static void error( Marker marker, String message, Supplier< ? >... paramSuppliers )
        {
            getLogger().error( marker, message, paramSuppliers );
        }

        public static void error( Marker marker, String message, Throwable t )
        {
            getLogger().error( marker, message, t );
        }

        public static void error( Marker marker, Supplier< ? > msgSupplier )
        {
            getLogger().error( marker, msgSupplier );
        }

        public static void error( Marker marker, Supplier< ? > msgSupplier, Throwable t )
        {
            getLogger().error( marker, msgSupplier, t );
        }

        public static void error( Message msg )
        {
            getLogger().error( msg );
        }

        public static void error( MessageSupplier msgSupplier )
        {
            getLogger().error( msgSupplier );
        }

        public static void error( MessageSupplier msgSupplier, Throwable t )
        {
            getLogger().error( msgSupplier, t );
        }

        public static void error( Message msg, Throwable t )
        {
            getLogger().error( msg, t );
        }

        public static void error( Object message )
        {
            getLogger().error( message );
        }

        public static void error( Object message, Throwable t )
        {
            getLogger().error( message, t );
        }

        public static void error( String message )
        {
            getLogger().error( message );
        }

        public static void error( String message, Object... params )
        {
            getLogger().error( message, params );
        }

        public static void error( String message, Object p0 )
        {
            getLogger().error( message, p0 );
        }

        public static void error( String message, Object p0, Object p1 )
        {
            getLogger().error( message, p0, p1 );
        }

        public static void error( String message, Object p0, Object p1, Object p2 )
        {
            getLogger().error( message, p0, p1, p2 );
        }

        public static void error( String message, Object p0, Object p1, Object p2, Object p3 )
        {
            getLogger().error( message, p0, p1, p2, p3 );
        }

        public static void error( String message, Object p0, Object p1, Object p2, Object p3, Object p4 )
        {
            getLogger().error( message, p0, p1, p2, p3, p4 );
        }

        public static void error( String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5 )
        {
            getLogger().error( message, p0, p1, p2, p3, p4, p5 );
        }

        public static void error( String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6 )
        {
            getLogger().error( message, p0, p1, p2, p3, p4, p5, p6 );
        }

        public static void error( String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7 )
        {
            getLogger().error( message, p0, p1, p2, p3, p4, p5, p6, p7 );
        }

        public static void error( String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8 )
        {
            getLogger().error( message, p0, p1, p2, p3, p4, p5, p6, p7, p8 );
        }

        public static void error( String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9 )
        {
            getLogger().error( message, p0, p1, p2, p3, p4, p5, p6, p7, p8, p9 );
        }

        public static void error( String message, Supplier< ? >... paramSuppliers )
        {
            getLogger().error( message, paramSuppliers );
        }

        public static void error( String message, Throwable t )
        {
            getLogger().error( message, t );
        }

        public static void error( Supplier< ? > msgSupplier )
        {
            getLogger().error( msgSupplier );
        }

        public static void error( Supplier< ? > msgSupplier, Throwable t )
        {
            getLogger().error( msgSupplier, t );
        }

        @Deprecated
        public static void exit()
        {
            getLogger().exit();
        }

        @Deprecated
        public static < R > R exit( R result )
        {
            return getLogger().exit( result );
        }

        public static void fatal( CharSequence message )
        {
            getLogger().fatal( message );
        }

        public static void fatal( CharSequence message, Throwable t )
        {
            getLogger().fatal( message, t );
        }

        public static void fatal( Marker marker, CharSequence message )
        {
            getLogger().fatal( marker, message );
        }

        public static void fatal( Marker marker, CharSequence message, Throwable t )
        {
            getLogger().fatal( marker, message, t );
        }

        public static void fatal( Marker marker, Message msg )
        {
            getLogger().fatal( marker, msg );
        }

        public static void fatal( Marker marker, MessageSupplier msgSupplier )
        {
            getLogger().fatal( marker, msgSupplier );
        }

        public static void fatal( Marker marker, MessageSupplier msgSupplier, Throwable t )
        {
            getLogger().fatal( marker, msgSupplier, t );
        }

        public static void fatal( Marker marker, Message msg, Throwable t )
        {
            getLogger().fatal( marker, msg, t );
        }

        public static void fatal( Marker marker, Object message )
        {
            getLogger().fatal( marker, message );
        }

        public static void fatal( Marker marker, Object message, Throwable t )
        {
            getLogger().fatal( marker, message, t );
        }

        public static void fatal( Marker marker, String message )
        {
            getLogger().fatal( marker, message );
        }

        public static void fatal( Marker marker, String message, Object... params )
        {
            getLogger().fatal( marker, message, params );
        }

        public static void fatal( Marker marker, String message, Object p0 )
        {
            getLogger().fatal( marker, message, p0 );
        }

        public static void fatal( Marker marker, String message, Object p0, Object p1 )
        {
            getLogger().fatal( marker, message, p0, p1 );
        }

        public static void fatal( Marker marker, String message, Object p0, Object p1, Object p2 )
        {
            getLogger().fatal( marker, message, p0, p1, p2 );
        }

        public static void fatal( Marker marker, String message, Object p0, Object p1, Object p2, Object p3 )
        {
            getLogger().fatal( marker, message, p0, p1, p2, p3 );
        }

        public static void fatal( Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4 )
        {
            getLogger().fatal( marker, message, p0, p1, p2, p3, p4 );
        }

        public static void fatal( Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5 )
        {
            getLogger().fatal( marker, message, p0, p1, p2, p3, p4, p5 );
        }

        public static void fatal( Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6 )
        {
            getLogger().fatal( marker, message, p0, p1, p2, p3, p4, p5, p6 );
        }

        public static void fatal( Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7 )
        {
            getLogger().fatal( marker, message, p0, p1, p2, p3, p4, p5, p6, p7 );
        }

        public static void fatal( Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8 )
        {
            getLogger().fatal( marker, message, p0, p1, p2, p3, p4, p5, p6, p7, p8 );
        }

        public static void fatal( Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9 )
        {
            getLogger().fatal( marker, message, p0, p1, p2, p3, p4, p5, p6, p7, p8, p9 );
        }

        public static void fatal( Marker marker, String message, Supplier< ? >... paramSuppliers )
        {
            getLogger().fatal( marker, message, paramSuppliers );
        }

        public static void fatal( Marker marker, String message, Throwable t )
        {
            getLogger().fatal( marker, message, t );
        }

        public static void fatal( Marker marker, Supplier< ? > msgSupplier )
        {
            getLogger().fatal( marker, msgSupplier );
        }

        public static void fatal( Marker marker, Supplier< ? > msgSupplier, Throwable t )
        {
            getLogger().fatal( marker, msgSupplier, t );
        }

        public static void fatal( Message msg )
        {
            getLogger().fatal( msg );
        }

        public static void fatal( MessageSupplier msgSupplier )
        {
            getLogger().fatal( msgSupplier );
        }

        public static void fatal( MessageSupplier msgSupplier, Throwable t )
        {
            getLogger().fatal( msgSupplier, t );
        }

        public static void fatal( Message msg, Throwable t )
        {
            getLogger().fatal( msg, t );
        }

        public static void fatal( Object message )
        {
            getLogger().fatal( message );
        }

        public static void fatal( Object message, Throwable t )
        {
            getLogger().fatal( message, t );
        }

        public static void fatal( String message )
        {
            getLogger().fatal( message );
        }

        public static void fatal( String message, Object... params )
        {
            getLogger().fatal( message, params );
        }

        public static void fatal( String message, Object p0 )
        {
            getLogger().fatal( message, p0 );
        }

        public static void fatal( String message, Object p0, Object p1 )
        {
            getLogger().fatal( message, p0, p1 );
        }

        public static void fatal( String message, Object p0, Object p1, Object p2 )
        {
            getLogger().fatal( message, p0, p1, p2 );
        }

        public static void fatal( String message, Object p0, Object p1, Object p2, Object p3 )
        {
            getLogger().fatal( message, p0, p1, p2, p3 );
        }

        public static void fatal( String message, Object p0, Object p1, Object p2, Object p3, Object p4 )
        {
            getLogger().fatal( message, p0, p1, p2, p3, p4 );
        }

        public static void fatal( String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5 )
        {
            getLogger().fatal( message, p0, p1, p2, p3, p4, p5 );
        }

        public static void fatal( String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6 )
        {
            getLogger().fatal( message, p0, p1, p2, p3, p4, p5, p6 );
        }

        public static void fatal( String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7 )
        {
            getLogger().fatal( message, p0, p1, p2, p3, p4, p5, p6, p7 );
        }

        public static void fatal( String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8 )
        {
            getLogger().fatal( message, p0, p1, p2, p3, p4, p5, p6, p7, p8 );
        }

        public static void fatal( String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9 )
        {
            getLogger().fatal( message, p0, p1, p2, p3, p4, p5, p6, p7, p8, p9 );
        }

        public static void fatal( String message, Supplier< ? >... paramSuppliers )
        {
            getLogger().fatal( message, paramSuppliers );
        }

        public static void fatal( String message, Throwable t )
        {
            getLogger().fatal( message, t );
        }

        public static void fatal( Supplier< ? > msgSupplier )
        {
            getLogger().fatal( msgSupplier );
        }

        public static void fatal( Supplier< ? > msgSupplier, Throwable t )
        {
            getLogger().fatal( msgSupplier, t );
        }

        public static Level getLevel()
        {
            return getLogger().getLevel();
        }

        public static < MF extends MessageFactory > MF getMessageFactory()
        {
            return getLogger().getMessageFactory();
        }

        public static String getName()
        {
            return getLogger().getName();
        }

        public static void info( CharSequence message )
        {
            getLogger().info( message );
        }

        public static void info( CharSequence message, Throwable t )
        {
            getLogger().info( message, t );
        }

        public static void info( Marker marker, CharSequence message )
        {
            getLogger().info( marker, message );
        }

        public static void info( Marker marker, CharSequence message, Throwable t )
        {
            getLogger().info( marker, message, t );
        }

        public static void info( Marker marker, Message msg )
        {
            getLogger().info( marker, msg );
        }

        public static void info( Marker marker, MessageSupplier msgSupplier )
        {
            getLogger().info( marker, msgSupplier );
        }

        public static void info( Marker marker, MessageSupplier msgSupplier, Throwable t )
        {
            getLogger().info( marker, msgSupplier, t );
        }

        public static void info( Marker marker, Message msg, Throwable t )
        {
            getLogger().info( marker, msg, t );
        }

        public static void info( Marker marker, Object message )
        {
            getLogger().info( marker, message );
        }

        public static void info( Marker marker, Object message, Throwable t )
        {
            getLogger().info( marker, message, t );
        }

        public static void info( Marker marker, String message )
        {
            getLogger().info( marker, message );
        }

        public static void info( Marker marker, String message, Object... params )
        {
            getLogger().info( marker, message, params );
        }

        public static void info( Marker marker, String message, Object p0 )
        {
            getLogger().info( marker, message, p0 );
        }

        public static void info( Marker marker, String message, Object p0, Object p1 )
        {
            getLogger().info( marker, message, p0, p1 );
        }

        public static void info( Marker marker, String message, Object p0, Object p1, Object p2 )
        {
            getLogger().info( marker, message, p0, p1, p2 );
        }

        public static void info( Marker marker, String message, Object p0, Object p1, Object p2, Object p3 )
        {
            getLogger().info( marker, message, p0, p1, p2, p3 );
        }

        public static void info( Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4 )
        {
            getLogger().info( marker, message, p0, p1, p2, p3, p4 );
        }

        public static void info( Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5 )
        {
            getLogger().info( marker, message, p0, p1, p2, p3, p4, p5 );
        }

        public static void info( Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6 )
        {
            getLogger().info( marker, message, p0, p1, p2, p3, p4, p5, p6 );
        }

        public static void info( Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7 )
        {
            getLogger().info( marker, message, p0, p1, p2, p3, p4, p5, p6, p7 );
        }

        public static void info( Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8 )
        {
            getLogger().info( marker, message, p0, p1, p2, p3, p4, p5, p6, p7, p8 );
        }

        public static void info( Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9 )
        {
            getLogger().info( marker, message, p0, p1, p2, p3, p4, p5, p6, p7, p8, p9 );
        }

        public static void info( Marker marker, String message, Supplier< ? >... paramSuppliers )
        {
            getLogger().info( marker, message, paramSuppliers );
        }

        public static void info( Marker marker, String message, Throwable t )
        {
            getLogger().info( marker, message, t );
        }

        public static void info( Marker marker, Supplier< ? > msgSupplier )
        {
            getLogger().info( marker, msgSupplier );
        }

        public static void info( Marker marker, Supplier< ? > msgSupplier, Throwable t )
        {
            getLogger().info( marker, msgSupplier, t );
        }

        public static void info( Message msg )
        {
            getLogger().info( msg );
        }

        public static void info( MessageSupplier msgSupplier )
        {
            getLogger().info( msgSupplier );
        }

        public static void info( MessageSupplier msgSupplier, Throwable t )
        {
            getLogger().info( msgSupplier, t );
        }

        public static void info( Message msg, Throwable t )
        {
            getLogger().info( msg, t );
        }

        public static void info( Object message )
        {
            getLogger().info( message );
        }

        public static void info( Object message, Throwable t )
        {
            getLogger().info( message, t );
        }

        public static void info( String message )
        {
            getLogger().info( message );
        }

        public static void info( String message, Object... params )
        {
            getLogger().info( message, params );
        }

        public static void info( String message, Object p0 )
        {
            getLogger().info( message, p0 );
        }

        public static void info( String message, Object p0, Object p1 )
        {
            getLogger().info( message, p0, p1 );
        }

        public static void info( String message, Object p0, Object p1, Object p2 )
        {
            getLogger().info( message, p0, p1, p2 );
        }

        public static void info( String message, Object p0, Object p1, Object p2, Object p3 )
        {
            getLogger().info( message, p0, p1, p2, p3 );
        }

        public static void info( String message, Object p0, Object p1, Object p2, Object p3, Object p4 )
        {
            getLogger().info( message, p0, p1, p2, p3, p4 );
        }

        public static void info( String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5 )
        {
            getLogger().info( message, p0, p1, p2, p3, p4, p5 );
        }

        public static void info( String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6 )
        {
            getLogger().info( message, p0, p1, p2, p3, p4, p5, p6 );
        }

        public static void info( String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7 )
        {
            getLogger().info( message, p0, p1, p2, p3, p4, p5, p6, p7 );
        }

        public static void info( String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8 )
        {
            getLogger().info( message, p0, p1, p2, p3, p4, p5, p6, p7, p8 );
        }

        public static void info( String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9 )
        {
            getLogger().info( message, p0, p1, p2, p3, p4, p5, p6, p7, p8, p9 );
        }

        public static void info( String message, Supplier< ? >... paramSuppliers )
        {
            getLogger().info( message, paramSuppliers );
        }

        public static void info( String message, Throwable t )
        {
            getLogger().info( message, t );
        }

        public static void info( Supplier< ? > msgSupplier )
        {
            getLogger().info( msgSupplier );
        }

        public static void info( Supplier< ? > msgSupplier, Throwable t )
        {
            getLogger().info( msgSupplier, t );
        }

        public static boolean isDebugEnabled()
        {
            return getLogger().isDebugEnabled();
        }

        public static boolean isDebugEnabled( Marker marker )
        {
            return getLogger().isDebugEnabled( marker );
        }

        public static boolean isEnabled( Level level )
        {
            return getLogger().isEnabled( level );
        }

        public static boolean isEnabled( Level level, Marker marker )
        {
            return getLogger().isEnabled( level, marker );
        }

        public static boolean isErrorEnabled()
        {
            return getLogger().isErrorEnabled();
        }

        public static boolean isErrorEnabled( Marker marker )
        {
            return getLogger().isErrorEnabled( marker );
        }

        public static boolean isFatalEnabled()
        {
            return getLogger().isFatalEnabled();
        }

        public static boolean isFatalEnabled( Marker marker )
        {
            return getLogger().isFatalEnabled( marker );
        }

        public static boolean isInfoEnabled()
        {
            return getLogger().isInfoEnabled();
        }

        public static boolean isInfoEnabled( Marker marker )
        {
            return getLogger().isInfoEnabled( marker );
        }

        public static boolean isTraceEnabled()
        {
            return getLogger().isTraceEnabled();
        }

        public static boolean isTraceEnabled( Marker marker )
        {
            return getLogger().isTraceEnabled( marker );
        }

        public static boolean isWarnEnabled()
        {
            return getLogger().isWarnEnabled();
        }

        public static boolean isWarnEnabled( Marker marker )
        {
            return getLogger().isWarnEnabled( marker );
        }

        public static void	log( Level level, CharSequence message )
        {
            getLogger().log( level, message );
        }

        public static void log( Level level, CharSequence message, Throwable t )
        {
            getLogger().log( level, message, t );
        }

        public static void log( Level level, Marker marker, CharSequence message )
        {
            getLogger().log( level, marker, message );
        }

        public static void log( Level level, Marker marker, CharSequence message, Throwable t )
        {
            getLogger().log( level, marker, message, t );
        }

        public static void log( Level level, Marker marker, Message msg )
        {
            getLogger().log( level, marker, msg );
        }

        public static void log( Level level, Marker marker, MessageSupplier msgSupplier )
        {
            getLogger().log( level, marker, msgSupplier );
        }

        public static void log( Level level, Marker marker, MessageSupplier msgSupplier, Throwable t )
        {
            getLogger().log( level, marker, msgSupplier, t );
        }

        public static void log( Level level, Marker marker, Message msg, Throwable t )
        {
            getLogger().log( level, marker, msg, t );
        }

        public static void log( Level level, Marker marker, Object message )
        {
            getLogger().log( level, marker, message );
        }

        public static void log( Level level, Marker marker, Object message, Throwable t )
        {
            getLogger().log( level, marker, message, t );
        }

        public static void log( Level level, Marker marker, String message )
        {
            getLogger().log( level, marker, message );
        }

        public static void log( Level level, Marker marker, String message, Object... params )
        {
            getLogger().log( level, marker, message, params );
        }

        public static void log( Level level, Marker marker, String message, Object p0 )
        {
            getLogger().log( level, marker, message, p0 );
        }

        public static void log(Level level, Marker marker, String message, Object p0, Object p1)
        {
            getLogger().log( level, marker, message, p0, p1 );
        }

        public static void log( Level level, Marker marker, String message, Object p0, Object p1, Object p2 )
        {
            getLogger().log( level, marker, message, p0, p1, p2 );
        }

        public static void log( Level level, Marker marker, String message, Object p0, Object p1, Object p2, Object p3 )
        {
            getLogger().log( level, marker, message, p0, p1, p2, p3 );
        }

        public static void log( Level level, Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4 )
        {
            getLogger().log( level, marker, message, p0, p1, p2, p3, p4 );
        }

        public static void log(Level level, Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5)
        {
            getLogger().log( level, marker, message, p0, p1, p2, p3, p4, p5 );
        }

        public static void log( Level level, Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6 )
        {
            getLogger().log( level, marker, message, p0, p1, p2, p3, p4, p5, p6 );
        }

        public static void log( Level level, Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7 )
        {
            getLogger().log( level, marker, message, p0, p1, p2, p3, p4, p5, p6, p7 );
        }

        public static void log( Level level, Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8 )
        {
            getLogger().log( level, marker, message, p0, p1, p2, p3, p4, p5, p6, p7, p8 );
        }

        public static void log( Level level, Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9 )
        {
            getLogger().log( level, marker, message, p0, p1, p2, p3, p4, p5, p6, p7, p8, p9 );
        }

        public static void log( Level level, Marker marker, String message, Supplier< ? >... paramSuppliers )
        {
            getLogger().log( level, marker, message, paramSuppliers );
        }

        public static void log( Level level, Marker marker, String message, Throwable t )
        {
            getLogger().log( level, marker, message, t );
        }

        public static void log( Level level, Marker marker, Supplier< ? > msgSupplier )
        {
            getLogger().log( level, marker, msgSupplier );
        }

        public static void log( Level level, Marker marker, Supplier< ? > msgSupplier, Throwable t )
        {
            getLogger().log( level, marker, msgSupplier, t );
        }

        public static void log( Level level, Message msg )
        {
            getLogger().log( level, msg );
        }

        public static void log( Level level, MessageSupplier msgSupplier )
        {
            getLogger().log( level, msgSupplier );
        }

        public static void log( Level level, MessageSupplier msgSupplier, Throwable t )
        {
            getLogger().log( level, msgSupplier, t );
        }

        public static void log( Level level, Message msg, Throwable t )
        {
            getLogger().log( level, msg, t );
        }

        public static void log( Level level, Object message )
        {
            getLogger().log( level, message );
        }

        public static void log( Level level, Object message, Throwable t )
        {
            getLogger().log( level, message, t );
        }

        public static void log( Level level, String message )
        {
            getLogger().log( level, message );
        }

        public static void log( Level level, String message, Object... params )
        {
            getLogger().log( level, message, params );
        }

        public static void log( Level level, String message, Object p0 )
        {
            getLogger().log( level, message, p0 );
        }

        public static void log( Level level, String message, Object p0, Object p1 )
        {
            getLogger().log( level, message, p0, p1 );
        }

        public static void log( Level level, String message, Object p0, Object p1, Object p2 )
        {
            getLogger().log( level, message, p0, p1, p2 );
        }

        public static void log( Level level, String message, Object p0, Object p1, Object p2, Object p3 )
        {
            getLogger().log( level, message, p0, p1, p2, p3 );
        }

        public static void log( Level level, String message, Object p0, Object p1, Object p2, Object p3, Object p4 )
        {
            getLogger().log( level, message, p0, p1, p2, p3, p4 );
        }

        public static void log( Level level, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5 )
        {
            getLogger().log( level, message, p0, p1, p2, p3, p4, p5 );
        }

        public static void log( Level level, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6 )
        {
            getLogger().log( level, message, p0, p1, p2, p3, p4, p5, p6 );
        }

        public static void log( Level level, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7 )
        {
            getLogger().log( level, message, p0, p1, p2, p3, p4, p5, p6, p7 );
        }

        public static void log( Level level, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8 )
        {
            getLogger().log( level, message, p0, p1, p2, p3, p4, p5, p6, p7, p8 );
        }

        public static void log( Level level, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9 )
        {
            getLogger().log( level, message, p0, p1, p2, p3, p4, p5, p6, p7, p8, p9 );
        }

        public static void log( Level level, String message, Supplier< ? >... paramSuppliers )
        {
            getLogger().log( level, message, paramSuppliers );
        }

        public static void log( Level level, String message, Throwable t )
        {
            getLogger().log( level, message, t );
        }

        public static void log( Level level, Supplier< ? > msgSupplier )
        {
            getLogger().log( level, msgSupplier );
        }

        public static void log( Level level, Supplier< ? > msgSupplier, Throwable t )
        {
            getLogger().log( level, msgSupplier, t );
        }

        public static void printf( Level level, Marker marker, String format, Object... params )
        {
            getLogger().printf( level, marker, format, params );
        }

        public static void printf( Level level, String format, Object... params )
        {
            getLogger().printf( level, format, params );
        }

        public static < T extends Throwable > T throwing( Level level, T t )
        {
            return getLogger().throwing( level, t );
        }

        public static < T extends Throwable > T throwing( T t )
        {
            return getLogger().throwing( t );
        }

        public static void trace( CharSequence message )
        {
            getLogger().trace( message );
        }

        public static void trace( CharSequence message, Throwable t )
        {
            getLogger().trace( message, t );
        }

        public static void trace( Marker marker, CharSequence message )
        {
            getLogger().trace( marker, message );
        }

        public static void trace( Marker marker, CharSequence message, Throwable t )
        {
            getLogger().trace( marker, message, t );
        }

        public static void trace( Marker marker, Message msg )
        {
            getLogger().trace( marker, msg );
        }

        public static void trace( Marker marker, MessageSupplier msgSupplier )
        {
            getLogger().trace( marker, msgSupplier );
        }

        public static void trace( Marker marker, MessageSupplier msgSupplier, Throwable t )
        {
            getLogger().trace( marker, msgSupplier, t );
        }

        public static void trace( Marker marker, Message msg, Throwable t )
        {
            getLogger().trace( marker, msg, t );
        }

        public static void trace( Marker marker, Object message )
        {
            getLogger().trace( marker, message );
        }

        public static void trace( Marker marker, Object message, Throwable t )
        {
            getLogger().trace( marker, message, t );
        }

        public static void trace( Marker marker, String message )
        {
            getLogger().trace( marker, message );
        }

        public static void trace( Marker marker, String message, Object... params )
        {
            getLogger().trace( marker, message, params );
        }

        public static void trace( Marker marker, String message, Object p0 )
        {
            getLogger().trace( marker, message, p0 );
        }

        public static void trace( Marker marker, String message, Object p0, Object p1 )
        {
            getLogger().trace( marker, message, p0, p1 );
        }

        public static void trace( Marker marker, String message, Object p0, Object p1, Object p2 )
        {
            getLogger().trace( marker, message, p0, p1, p2 );
        }

        public static void trace( Marker marker, String message, Object p0, Object p1, Object p2, Object p3 )
        {
            getLogger().trace( marker, message, p0, p1, p2, p3 );
        }

        public static void trace( Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4 )
        {
            getLogger().trace( marker, message, p0, p1, p2, p3, p4 );
        }

        public static void trace( Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5 )
        {
            getLogger().trace( marker, message, p0, p1, p2, p3, p4, p5 );
        }

        public static void trace( Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6 )
        {
            getLogger().trace( marker, message, p0, p1, p2, p3, p4, p5, p6 );
        }

        public static void trace( Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7 )
        {
            getLogger().trace( marker, message, p0, p1, p2, p3, p4, p5, p6, p7 );
        }

        public static void trace( Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8 )
        {
            getLogger().trace( marker, message, p0, p1, p2, p3, p4, p5, p6, p7, p8 );
        }

        public static void trace( Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9 )
        {
            getLogger().trace( marker, message, p0, p1, p2, p3, p4, p5, p6, p7, p8, p9 );
        }

        public static void trace( Marker marker, String message, Supplier< ? >... paramSuppliers )
        {
            getLogger().trace( marker, message, paramSuppliers );
        }

        public static void trace( Marker marker, String message, Throwable t )
        {
            getLogger().trace( marker, message, t );
        }

        public static void trace( Marker marker, Supplier< ? > msgSupplier )
        {
            getLogger().trace( marker, msgSupplier );
        }

        public static void trace( Marker marker, Supplier<?> msgSupplier, Throwable t )
        {
            getLogger().trace( marker, msgSupplier, t );
        }

        public static void trace( Message msg )
        {
            getLogger().trace( msg );
        }

        public static void trace( MessageSupplier msgSupplier )
        {
            getLogger().trace( msgSupplier );
        }

        public static void trace( MessageSupplier msgSupplier, Throwable t )
        {
            getLogger().trace( msgSupplier, t );
        }

        public static void trace( Message msg, Throwable t )
        {
            getLogger().trace( msg, t );
        }

        public static void trace( Object message )
        {
            getLogger().trace( message );
        }

        public static void trace( Object message, Throwable t )
        {
            getLogger().trace( message, t );
        }

        public static void trace( String message )
        {
            getLogger().trace( message );
        }

        public static void trace( String message, Object... params )
        {
            getLogger().trace( message, params );
        }

        public static void trace( String message, Object p0 )
        {
            getLogger().trace( message, p0 );
        }

        public static void trace( String message, Object p0, Object p1 )
        {
            getLogger().trace( message, p0, p1 );
        }

        public static void trace( String message, Object p0, Object p1, Object p2 )
        {
            getLogger().trace( message, p0, p1, p2 );
        }

        public static void trace( String message, Object p0, Object p1, Object p2, Object p3 )
        {
            getLogger().trace( message, p0, p1, p2, p3 );
        }

        public static void trace( String message, Object p0, Object p1, Object p2, Object p3, Object p4 )
        {
            getLogger().trace( message, p0, p1, p2, p3, p4 );
        }

        public static void trace( String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5 )
        {
            getLogger().trace( message, p0, p1, p2, p3, p4, p5 );
        }

        public static void trace( String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6 )
        {
            getLogger().trace( message, p0, p1, p2, p3, p4, p5, p6 );
        }

        public static void trace( String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7 )
        {
            getLogger().trace( message, p0, p1, p2, p3, p4, p5, p6, p7 );
        }

        public static void trace( String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8 )
        {
            getLogger().trace( message, p0, p1, p2, p3, p4, p5, p6, p7, p8 );
        }

        public static void trace( String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9 )
        {
            getLogger().trace( message, p0, p1, p2, p3, p4, p5, p6, p7, p8, p9 );
        }

        public static void trace( String message, Supplier< ? >... paramSuppliers )
        {
            getLogger().trace( message, paramSuppliers );
        }

        public static void trace( String message, Throwable t )
        {
            getLogger().trace( message, t );
        }

        public static void trace( Supplier< ? > msgSupplier )
        {
            getLogger().trace( msgSupplier );
        }

        public static void trace( Supplier< ? > msgSupplier, Throwable t )
        {
            getLogger().trace( msgSupplier, t );
        }

        public static EntryMessage traceEntry()
        {
            return getLogger().traceEntry();
        }

        public static EntryMessage traceEntry( Message message )
        {
            return getLogger().traceEntry( message );
        }

        public static EntryMessage traceEntry( String format, Object... params )
        {
            return getLogger().traceEntry( format, params );
        }

        public static EntryMessage traceEntry( String format, Supplier< ? >... paramSuppliers )
        {
            return getLogger().traceEntry( format, paramSuppliers );
        }

        public static EntryMessage traceEntry( Supplier< ? >... paramSuppliers )
        {
            return getLogger().traceEntry( paramSuppliers );
        }

        public static void traceExit()
        {
            getLogger().traceExit();
        }

        public static void traceExit( EntryMessage message )
        {
            getLogger().traceExit( message );
        }

        public static < R > R traceExit( EntryMessage message, R result )
        {
            return getLogger().traceExit( message, result );
        }

        public static < R > R traceExit( Message message, R result )
        {
            return getLogger().traceExit( message, result );
        }

        public static < R > R traceExit( R result )
        {
            return getLogger().traceExit( result );
        }

        public static < R > R traceExit( String format, R result )
        {
            return getLogger().traceExit( format, result );
        }

        public static void warn( CharSequence message )
        {
            getLogger().warn( message );
        }

        public static void warn( CharSequence message, Throwable t )
        {
            getLogger().warn( message, t );
        }

        public static void warn( Marker marker, CharSequence message )
        {
            getLogger().warn( marker, message );
        }

        public static void warn( Marker marker, CharSequence message, Throwable t )
        {
            getLogger().warn( marker, message, t );
        }

        public static void warn( Marker marker, Message msg )
        {
            getLogger().warn( marker, msg );
        }

        public static void warn( Marker marker, MessageSupplier msgSupplier )
        {
            getLogger().warn( marker, msgSupplier );
        }

        public static void warn( Marker marker, MessageSupplier msgSupplier, Throwable t )
        {
            getLogger().warn( marker, msgSupplier, t );
        }

        public static void warn( Marker marker, Message msg, Throwable t )
        {
            getLogger().warn( marker, msg, t );
        }

        public static void warn( Marker marker, Object message )
        {
            getLogger().warn( marker, message );
        }

        public static void warn( Marker marker, Object message, Throwable t )
        {
            getLogger().warn( marker, message, t );
        }

        public static void warn( Marker marker, String message )
        {
            getLogger().warn( marker, message );
        }

        public static void warn( Marker marker, String message, Object... params )
        {
            getLogger().warn( marker, message, params );
        }

        public static void warn( Marker marker, String message, Object p0 )
        {
            getLogger().warn( marker, message, p0 );
        }

        public static void warn( Marker marker, String message, Object p0, Object p1 )
        {
            getLogger().warn( marker, message, p0, p1 );
        }

        public static void warn( Marker marker, String message, Object p0, Object p1, Object p2 )
        {
            getLogger().warn( marker, message, p0, p1, p2 );
        }

        public static void warn( Marker marker, String message, Object p0, Object p1, Object p2, Object p3 )
        {
            getLogger().warn( marker, message, p0, p1, p2, p3 );
        }

        public static void warn( Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4 )
        {
            getLogger().warn( marker, message, p0, p1, p2, p3, p4 );
        }

        public static void warn( Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5 )
        {
            getLogger().warn( marker, message, p0, p1, p2, p3, p4, p5 );
        }

        public static void warn( Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6 )
        {
            getLogger().warn( marker, message, p0, p1, p2, p3, p4, p5, p6 );
        }

        public static void warn( Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7 )
        {
            getLogger().warn( marker, message, p0, p1, p2, p3, p4, p5, p6, p7 );
        }

        public static void warn( Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8 )
        {
            getLogger().warn( marker, message, p0, p1, p2, p3, p4, p5, p6, p7, p8 );
        }

        public static void warn( Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9 )
        {
            getLogger().warn( marker, message, p0, p1, p2, p3, p4, p5, p6, p7, p8, p9 );
        }

        public static void warn( Marker marker, String message, Supplier< ? >... paramSuppliers )
        {
            getLogger().warn( marker, message, paramSuppliers );
        }

        public static void warn( Marker marker, String message, Throwable t )
        {
            getLogger().warn( marker, message, t );
        }

        public static void warn( Marker marker, Supplier< ? > msgSupplier )
        {
            getLogger().warn( marker, msgSupplier );
        }

        public static void warn( Marker marker, Supplier< ? > msgSupplier, Throwable t )
        {
            getLogger().warn( marker, msgSupplier, t );
        }

        public static void warn( Message msg )
        {
            getLogger().warn( msg );
        }

        public static void warn( MessageSupplier msgSupplier )
        {
            getLogger().warn( msgSupplier );
        }

        public static void warn( MessageSupplier msgSupplier, Throwable t )
        {
            getLogger().warn( msgSupplier, t );
        }

        public static void warn( Message msg, Throwable t )
        {
            getLogger().warn( msg, t );
        }

        public static void warn( Object message )
        {
            getLogger().warn( message );
        }

        public static void warn( Object message, Throwable t )
        {
            getLogger().warn( message, t );
        }

        public static void warn( String message )
        {
            getLogger().warn( message );
        }

        public static void warn( String message, Object... params )
        {
            getLogger().warn( message, params );
        }

        public static void warn( String message, Object p0 )
        {
            getLogger().warn( message, p0 );
        }

        public static void warn( String message, Object p0, Object p1 )
        {
            getLogger().warn( message, p0, p1 );
        }

        public static void warn( String message, Object p0, Object p1, Object p2 )
        {
            getLogger().warn( message, p0, p1, p2 );
        }

        public static void warn( String message, Object p0, Object p1, Object p2, Object p3 )
        {
            getLogger().warn( message, p0, p1, p2, p3 );
        }

        public static void warn( String message, Object p0, Object p1, Object p2, Object p3, Object p4 )
        {
            getLogger().warn( message, p0, p1, p2, p3, p4 );
        }

        public static void warn( String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5 )
        {
            getLogger().warn( message, p0, p1, p2, p3, p4, p5 );
        }

        public static void warn( String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6 )
        {
            getLogger().warn( message, p0, p1, p2, p3, p4, p5, p6 );
        }

        public static void warn( String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7 )
        {
            getLogger().warn( message, p0, p1, p2, p3, p4, p5, p6, p7 );
        }

        public static void warn( String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8 )
        {
            getLogger().warn( message, p0, p1, p2, p3, p4, p5, p6, p7, p8 );
        }

        public static void warn( String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9 )
        {
            getLogger().warn( message, p0, p1, p2, p3, p4, p5, p6, p7, p8, p9 );
        }

        public static void warn( String message, Supplier< ? >... paramSuppliers )
        {
            getLogger().warn( message, paramSuppliers );
        }

        public static void warn( String message, Throwable t )
        {
            getLogger().warn( message, t );
        }

        public static void warn( Supplier< ? > msgSupplier )
        {
            getLogger().warn( msgSupplier );
        }

        public static void warn( Supplier< ? > msgSupplier, Throwable t )
        {
            getLogger().warn( msgSupplier, t );
        }

    // Private Static Fields

        private static Logger localInstance = null;

}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// End of 'Log.java'
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////