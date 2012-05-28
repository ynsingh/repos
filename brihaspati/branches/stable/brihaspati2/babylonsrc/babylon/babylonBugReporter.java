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
//  babylonBugReport.java
//

import java.io.*;
import java.net.*;
import babylon.*;


public class babylonBugReporter
{
    // This will send a simple bug report email

    public static String sendTo = "jamesamc@yahoo.com";
    public static String smtpHost = "mx1.mail.yahoo.com";
    
    protected Socket socket;
    protected BufferedReader istream;
    protected BufferedWriter ostream;


    public babylonBugReporter(String text)
	throws Exception
	{
	    String hostName = InetAddress.getLocalHost().getHostName();
	    String response;

	    // set up the client socket
	    socket = new Socket(smtpHost, 25);
	    
	    // Get an input stream
	    istream = new BufferedReader(
			 new InputStreamReader(socket.getInputStream()));
	    
	    // Get the output stream
	    ostream = new BufferedWriter(
			 new OutputStreamWriter(socket.getOutputStream()));
	    
	    // First, we should get a 'ready' 220
	    expect("220");

	    // Send 'HELO'
	    send("HELO " + hostName);
	    // Should get 'ok', 250
	    expect("250");

	    // Send "MAIL FROM:"
	    send("MAIL FROM:<babylon@" + hostName + ">");
	    // Should get 'ok', 250
	    expect("250");

	    // Send "RCPT TO:"
	    send("RCPT TO:<" + sendTo + ">");
	    // Should get 'ok', 250
	    expect("250");

	    // Tell em the data is coming
	    send("DATA");
	    // Should get "start mail", 354
	    expect("354");

	    // Send the mail
	    send("From: babylon@"  + hostName);
	    send("To: " + sendTo);
	    send("Subject: Babylon Chat bug report\n");
	    send(text);

	    // Tell it we're finished
	    send(".\r");
	    // Should get 'ok', 250
	    expect("250");

	    // Quit
	    send("QUIT");
	    // Don't wait for a response.  Some MTAs don't even like that
	    // command, it seems

	    try { istream.close(); } catch (IOException a) {}
	    try { ostream.close(); } catch (IOException b) {}
	    try { socket.close(); } catch (IOException c) {}
	    return;
	}

    private void send(String send)
	throws Exception
    {
	// System.out.println(send);
	ostream.write(send + "\n");
	ostream.flush();
    }

    private String expect(String code)
	throws Exception
    {
	//System.out.println("wait for message");
	String response = istream.readLine();
	// System.out.println(response);
	if (!response.startsWith(code))
	    throw error(response);
	return (response);
    }

    private Exception error(String error)
    {
	try { istream.close(); } catch (IOException a) {}
	try { ostream.close(); } catch (IOException b) {}
	try { socket.close(); } catch (IOException c) {}
	return (new Exception("SMTP error: " + error));
    }
}
