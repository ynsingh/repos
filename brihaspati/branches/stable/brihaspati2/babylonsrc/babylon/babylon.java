package babylon;

//  Babylon Chat
//  Copyright (C) 1997-2002 J. Andrew McLaughlin
// 
//  This program is free software; you can redistribute it and/or modify it
//  under the terms of the GNU General Public License as published by the Free
//  Software Foundation; either version 2 of the License, or (at your option)
//  any later version.
// 
//  This program is distributed in the hope that it will be useful, but
//  WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
//  or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License
//  for more details.
//  
//  You should have received a copy of the GNU General Public License along
//  with this program; if not, write to the Free Software Foundation, Inc.,
//  59 Temple Place - Suite 330, Boston, MA 02111-1307, USA.
//
//  babylon.java
//

import java.awt.*;
import java.io.*;
import java.util.*;
import java.net.*;
import babylon.*;

//import babylon.babylonWindow;
//import babylon.babylonStringManager;
//import babylon.babylonPanel;

public class babylon
    extends Object
    implements Runnable
{
    public static final String VERSION = "2.1_BETA";

    public static String usernameParam     = "-username";
    public static String passwordParam     = "-password";
    public static String servernameParam   = "-servername";
    public static String portnumberParam   = "-portnumber";
    public static String chatroomParam     = "-chatroom";
    public static String widthParam        = "-xsize";
    public static String heightParam       = "-ysize";
    public static String nopasswordsParam  = "-nopasswords";
    public static String locksettingsParam = "-locksettings";
    public static String autoconnectParam  = "-autoconnect";
    public static String hidecanvasParam   = "-hidecanvas";

    private babylonWindow window;
    private URL myURL = null;
    private Class thisClass = babylon.class;
    private String name = "";
    private String password = "";
    private String host = "";
    private String port = "";
    private String room = "";
    private int windowWidth = 0;
    private int windowHeight = 0;
    private boolean requirePasswords = true;
    private boolean lockSettings = false;
    private boolean autoConnect = false;
    private boolean showCanvas = true;

    // For managing strings according to the locale
    protected babylonStringManager strings = null;


    public babylon(String[] args)
    {
	// Get a URL to describe the invocation directory
	try {
	    myURL = new URL("file", "localhost", "./");
	}
	catch (Exception E) {
	    System.out.println(E);
	    System.exit(1);
	}
	
	// For managing strings according to locale
	strings = new babylonStringManager(myURL,
					   Locale.getDefault().getLanguage());

	// Parse our args.  Only continue if successful
	if (!parseArgs(args))
	    System.exit(1);

	// If "username" is blank, that's OK.  However, if the server and/or
	// port are blank, we'll supply some default ones here
	if ((host == null) || host.equals(""))
	    host = "visopsys.org";
	if ((port == null) || port.equals(""))
	    port = "12468";

	// Open the window
	window = new babylonWindow(new babylonPanel(name, password, host,
						    port, showCanvas,
						    myURL));

	// Set the window width and height, if applicable
	Dimension tmpSize = window.getSize();
	if (windowWidth > 0)
	    tmpSize.width = windowWidth;
	if (windowHeight > 0)
	    tmpSize.height = windowHeight;
	window.setSize(tmpSize);

	// Make the pretty icon
	window.setIcon(myURL);

	// Should the window prompt users for passwords automatically?
	window.contentPanel.requirePassword = requirePasswords;

	// Should the user name, server name, and port name be locked
	// against user changes?
	window.contentPanel.lockSettings = lockSettings;

	// Show the window
	window.show();

	// Are we supposed to attempt an automatic connection?
	if (autoConnect)
	    window.contentPanel.connect();
	else
	    window.contentPanel.offline();

	// Is the user supposed to be placed in an initial chat room?
	if (!room.equals(""))
	    if (window.contentPanel.client != null)
		try {
		    window.contentPanel.client.sendEnterRoom(room, false,
								"");
		}
		catch (IOException e) {
		    window.contentPanel.client.lostConnection();
		    return;
		}

	// Done
	return;
    }

    public void run()
    {
	// Nothing to do here.
	return;
    }

    private void usage()
    {
	System.out.println("\n" + strings.get(thisClass, "usage"));
	System.out.println("java babylon [" +
			   usernameParam + " name] [" +
			   passwordParam + " password] [" +
			   servernameParam + " host] [" +
			   portnumberParam + " port] [" + 
			   chatroomParam + " room] [" +
			   widthParam + " number] [" +
			   heightParam + " number] [" +
			   nopasswordsParam + "] [" +
			   locksettingsParam + "] [" +
			   autoconnectParam + "] [" +
			   hidecanvasParam + "]");
	return;
    }

    private boolean parseArgs(String[] args)
    {
	// Loop through any command line arguments
	for (int count = 0; count < args.length; count ++)
	    {
		if (args[count].equals(usernameParam))
		    {
			if (++count < args.length)
			    name = args[count];
		    }

		else if (args[count].equals(passwordParam))
		    {
			if (++count < args.length)
			    password = args[count];
		    }

		else if (args[count].equals(servernameParam))
		    {
			if (++count < args.length)
			    host = args[count];
		    }

		else if (args[count].equals(portnumberParam))
		    {
			if (++count < args.length)
			    port = args[count];
		    }

		else if (args[count].equals(chatroomParam))
		    {
			if (++count < args.length)
			    room = args[count];
		    }

		else if (args[count].equals(widthParam))
		    {
			if (++count < args.length)
			    windowWidth = Integer.parseInt(args[count]);
		    }

		else if (args[count].equals(heightParam))
		    {
			if (++count < args.length)
			    windowHeight = Integer.parseInt(args[count]);
		    }

		else if (args[count].equals(nopasswordsParam))
		    requirePasswords = false;

		else if (args[count].equals(locksettingsParam))
		    lockSettings = true;

		else if (args[count].equals(autoconnectParam))
		    autoConnect = true;

		else if (args[count].equals(hidecanvasParam))
		    showCanvas = false;

		else if (args[count].equals("-help"))
		    {
			usage();
			return (false);
		    }

		else
		    {
			System.out.println("\n" + strings.get(thisClass,
							      "unknownarg") +
					   " " + args[count]);
			System.out.println(strings.get(thisClass, "forusage"));

			return (false);
		    }
	    }

	return (true);
    }

    public static void main(String[] args)
    {
	babylon firstInstance = new babylon(args);
	firstInstance.run();
	return;
    }
}
