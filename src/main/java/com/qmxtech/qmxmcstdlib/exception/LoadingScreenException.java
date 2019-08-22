package com.qmxtech.qmxmcstdlib.exception;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// LoadingScreenException.java
// Matthew J. Schultz (Korynkai) | Created : 17AUG19 | Last Modified : 18AUG19 by Matthew J. Schultz (Korynkai)
// Version : 0.0.1
// This is a source file for 'QMXMCStdLib'; it defines a generic loading screen exception.
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
import com.qmxtech.qmxmcstdlib.log.Log;

import net.minecraft.client.gui.GuiErrorScreen;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.fml.client.CustomModLoadingErrorDisplayException;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;
import java.util.ArrayList;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// The 'LoadingScreenException' Class
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

@SuppressWarnings( "unused" )
@SideOnly( Side.CLIENT ) public class LoadingScreenException extends CustomModLoadingErrorDisplayException
{
    // Public Types

        static public class ScreenFormattedMessage
        {
            // Public Methods

                @SuppressWarnings( "WeakerAccess" )
                public ScreenFormattedMessage addLine( final String string, final ColorValue color )
                {
                    messageList.add( new MessageLine( string, color.value() ) );
                    return this;
                }

                public ScreenFormattedMessage addLine( final String string, final int color )
                {
                    messageList.add( new MessageLine( string, color ) );
                    return this;
                }

                @SuppressWarnings( "WeakerAccess" )
                public int lineCount()
                {
                    return messageList.size();
                }

                @SuppressWarnings( "WeakerAccess" )
                public MessageLine getLine( final int line )
                {
                    return messageList.get( line );
                }

            // Protected Types

                @SuppressWarnings( "WeakerAccess" )
                static protected class MessageLine
                {
                    // Public Constructor

                        public MessageLine( final String string, final int color )
                        {
                            this.string = string;
                            this.color = color;
                        }

                    // Public Methods

                        public String getString()
                        {
                            return string;
                        }

                        public int getColor()
                        {
                            return color;
                        }

                    // Private Fields

                        private String string;
                        private int color;
                }

            // Protected Fields

                @SuppressWarnings( "WeakerAccess" )
                protected List< MessageLine > messageList;

            // Protected Constructor

                @SuppressWarnings( "WeakerAccess" )
                protected ScreenFormattedMessage()
                {
                    messageList = new ArrayList<>();
                }
        }

    // Public Constructors

        public LoadingScreenException( final ScreenFormattedMessage message )
        {
            this.message = message;
        }

    // Public Static Property Initialization Methods

        public static ScreenFormattedMessage createMessage( final String header, final ColorValue color )
        {
            return new ScreenFormattedMessage().addLine( header, color );
        }

        public static ScreenFormattedMessage createMessage( final String header, final int color )
        {
            return new ScreenFormattedMessage().addLine( header, color );
        }

    // Public Methods

        public void initGui( GuiErrorScreen errorScreen, FontRenderer fontRenderer )
        {
            // Do nothing.
        }

        public void drawScreen( GuiErrorScreen errorScreen, FontRenderer fontRenderer, int mouseRelX, int mouseRelY, float tickTime )
        {

            //ok: add center lines first, multiply by 15, add header and footer by 25
            int bodyYCount = 0;
            int fullYCount = 0;
            int currentYOffset = 0;
            int ln;
            boolean oneLiner = false;

            if( message.lineCount() > 2 )
                bodyYCount = 15 * ( message.lineCount() - 2 );

            if( message.lineCount() > 1 )
            {
                fullYCount = 50 + bodyYCount;
            }
            else if( message.lineCount() == 1 )
            {
                oneLiner = true;
            }
            else
            {
                Log.fatal( I18n.format( "exception.qmxmcstdlib.loadingscreenexception.nomessage" ) );
                return;
            }

            if( oneLiner )
            {
                errorScreen.drawDefaultBackground();
                errorScreen.drawCenteredString( fontRenderer, message.getLine( 0 ).getString(), ( errorScreen.width / 2 ), ( errorScreen.height / 2 ), message.getLine( 0 ).getColor() );
            }
            else
            {
                // first: ( ( errorScreen.height - fullYCount ) / 2 ); then: + 15 until last
                // Note: is this a mockup until I figure out the actual calculations? Or are these the actual calculations?

                if( message.lineCount() > ( errorScreen.height - 4 ) )
                {
                    tooLargeForScreen();
                    return;
                }

                for( ln = 0; ln < message.lineCount(); ln++ )
                {
                    if( message.getLine( ln ).getString().length() > ( errorScreen.width - 4 ) )
                    {
                        tooLargeForScreen();
                        return;
                    }
                }

                errorScreen.drawDefaultBackground();

                for( ln = 0; ln < message.lineCount(); ln++ )
                {
                    if( ln == 0 )
                        currentYOffset = ( ( errorScreen.height - fullYCount ) / 2 );

                    if( ( ln == 1 ) || ( ln == ( message.lineCount() - 2 ) ) )
                        currentYOffset += 25;

                    if( ( ln >= 2 ) && ( ln < ( message.lineCount() - 2 ) ) )
                        currentYOffset += 15;

                    errorScreen.drawCenteredString( fontRenderer, message.getLine( ln ).getString(), ( errorScreen.width / 2 ), currentYOffset, message.getLine( ln ).getColor() );
                }
            }
        }

    // Protected Fields

        @SuppressWarnings( "WeakerAccess" )
        protected ScreenFormattedMessage message;

    // Protected Constructor

        protected LoadingScreenException()
        {
            // Do nothing.
        }

    // Protected Methods

        @SuppressWarnings( "WeakerAccess" )
        protected void tooLargeForScreen()
        {
            int ln;

            Log.fatal( I18n.format( "exception.qmxmcstdlib.loadingscreenexception.toolargeforscreen.line1" ) );
            Log.fatal( I18n.format( "exception.qmxmcstdlib.loadingscreenexception.toolargeforscreen.line2") );

            for( ln = 0; ln < message.lineCount(); ln++ )
                Log.fatal( message.getLine( ln ).getString() );
        }
}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// End of 'LoadingScreenException.java'
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////