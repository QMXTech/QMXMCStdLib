package com.qmxtech.qmxmcstdlib.file;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// FileUtils.java
// Matthew J. Schultz (Korynkai) | Created : 16AUG19 | Last Modified : 16AUG19 by Matthew J. Schultz (Korynkai)
// Version : 0.0.1
// This is a source file for 'QMXMCStdLib'; it defines a set of file utility methods.
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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Enumeration;
import java.util.Objects;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.apache.commons.lang3.StringUtils;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// The 'FileUtils' Class
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

@SuppressWarnings("unused")
public class FileUtils
{
    // Public Static Methods

        @SuppressWarnings( "WeakerAccess" )
        public static boolean copyFile( final File toCopy, final File destFile )
        {
            // Initialize Return Value.

                boolean RetVal = false;

            // Attempt to copy file, or print the error.

                try
                {
                    RetVal = FileUtils.copyStream( new FileInputStream( toCopy ), new FileOutputStream( destFile ) );
                }
                catch ( final FileNotFoundException e )
                {
                    e.printStackTrace();
                }

            // Return the return value.

                return RetVal;
        }

        @SuppressWarnings( "WeakerAccess" )
        public static boolean copyJarResourcesRecursively( final File destDir, final JarURLConnection jarConnection ) throws IOException
        {
            // Initialize Return Value.

                boolean RetVal = false;

            // Attempt to copy Jar resources recursively.

                final JarFile jarFile = jarConnection.getJarFile();

                for( final Enumeration< JarEntry > e = jarFile.entries(); e.hasMoreElements(); )
                {
                    final JarEntry entry = e.nextElement();

                    if( entry.getName().startsWith( jarConnection.getEntryName() ) )
                    {
                        final String filename = StringUtils.removeStart( entry.getName(), jarConnection.getEntryName() );
                        final File f = new File( destDir, filename );

                        if( !entry.isDirectory() )
                        {
                            final InputStream entryInputStream = jarFile.getInputStream( entry );
                            RetVal = FileUtils.copyStream( entryInputStream, f );
                            entryInputStream.close();
                        }
                        else
                        {
                            if( !FileUtils.ensureDirectoryExists( f ) ) // What about when it does exist? Just returns false right now...
                                throw new IOException( "Could not create directory: " + f.getAbsolutePath() );
                        }
                    }
                }

                return RetVal;
        }

        public static boolean copyResourcesRecursively( final URL originUrl, final File destination )
        {
            // Initialize Return Value.

                boolean RetVal = false;

            // Attempt to copy resources recursively, or print the error.

                try
                {
                    final URLConnection urlConnection = originUrl.openConnection();

                    if ( urlConnection instanceof JarURLConnection )
                        RetVal = FileUtils.copyJarResourcesRecursively( destination, ( JarURLConnection ) urlConnection );
                    else
                        RetVal = FileUtils.copyFilesRecursively( new File( originUrl.getPath() ), destination );

                }
                catch ( final IOException e )
                {
                    e.printStackTrace();
                }

            // Return the return value.

                return RetVal;
        }

    // Private Static Methods.

        private static boolean copyFilesRecursively( final File toCopy, final File destDir )
        {
            // Initialize Return Value.

                boolean RetVal = false;

            // Attempt to copy files recursively.

                assert destDir.isDirectory();

                if ( !toCopy.isDirectory() )
                {
                    RetVal = FileUtils.copyFile( toCopy, new File( destDir, toCopy.getName() ) );
                }
                else
                {
                    final File newDestDir = new File( destDir, toCopy.getName() );

                    if ( newDestDir.exists() || newDestDir.mkdir() )
                    {
                        RetVal = true;

                        for( final File child : Objects.requireNonNull( toCopy.listFiles() ) )
                        {
                            if( !FileUtils.copyFilesRecursively( child, newDestDir ) )
                            {
                                RetVal = false;
                                break;
                            }
                        }
                    }
                }

            // Return the return value.

                return RetVal;
        }

        private static boolean copyStream( final InputStream is, final File f )
        {
            // Initialize Return Value.

                boolean RetVal = false;

            // Attempt to copy InputStream to file or print the error.

                try
                {
                    RetVal = FileUtils.copyStream( is, new FileOutputStream( f ) );
                }
                catch ( final FileNotFoundException e )
                {
                    e.printStackTrace();
                }

            // Return the return value.

                return RetVal;
        }

        private static boolean copyStream( final InputStream is, final OutputStream os )
        {
            // Initialize Return Value.

                boolean RetVal = false;

            // Attempt to copy InputStream to OutputStream (with a 1kB buffer) or print the error.

                try
                {
                    final byte[] buf = new byte[1024];

                    for( int len = is.read( buf ); len > 0; len = is.read( buf ) )
                        os.write( buf, 0, len );

                    is.close();
                    os.close();

                    RetVal = true;
                }
                catch ( final IOException e )
                {
                    e.printStackTrace();
                }

            // Return the return value.

                return RetVal;
        }

        private static boolean ensureDirectoryExists( final File f )
        {
            // Ensure the directory exists.

                return f.exists() || f.mkdir();
        }
}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// End of 'FileUtils.java'
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
