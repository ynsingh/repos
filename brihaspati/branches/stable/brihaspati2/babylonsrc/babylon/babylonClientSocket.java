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
//  babylonClientSocket.java
//

import java.net.*;
import java.awt.*;
import java.util.*;
import java.text.*;
import java.io.*;
import javax.swing.JOptionPane; 
import java.io.BufferedReader;
import java.io.FileReader;
import babylon.*;


class babylonCanvasCommand
{
    // This is so we can keep a vector of canvas commands to keep track
    // of all drawing

    short commandtype;
    int whodrew;
    short x1, y1, x2, y2;
    short colour, type, attribs, size, thick;
    boolean fill;
    String text;
    byte[] pictureData;

    babylonCanvasCommand(short mycommandtype, int mywhodrew, short mycolour,
			 short myx1, short myy1, short myx2, short myy2,
			 short mytype, short myattribs, short mysize,
			 short mythick, boolean myfill, String mytext,
			 byte[] mypicturedata)
    {
	commandtype = mycommandtype;
	whodrew = mywhodrew;
	colour = mycolour;
	x1 = myx1;
	y1 = myy1;
	x2 = myx2;
	y2 = myy2;
	type = mytype;
	attribs = myattribs;
	size = mysize;
	thick = mythick;
	fill = myfill;
	text = mytext;
	pictureData = mypicturedata;
    }
}


class babylonClientTickler
    extends Thread
{
    // This class just runs as a separate thread, pinging the client
    // every second to make sure we haven't lost the connection
     
    babylonClientSocket client;
 
    public babylonClientTickler(babylonClientSocket c)
    {
	super("Babylon Chat client onnection tickler");
 
	client = c;
 
	start();
    }
 
    public void run()
    {
	while(!client.stop)
	    {
		try {
		    client.sendPing();
		}
		catch (IOException e) {
		    client.online = false;
		    if (!client.stop)
			if (client.waitReconnect())
			    {
				client.online = true;
				continue;
			    }
		    break;
		}

		// Sleep for some amount of time
		try {
		    sleep (5000);
		}
		catch (InterruptedException e) {}
	    }
    }
}


public class babylonClientSocket
    extends Thread
{
    protected double protocolVersion = 2.1; // by default
    protected babylonServer server;

    protected babylonClient client;

    protected Socket mySocket;
    protected DataInputStream istream;
    protected DataOutputStream ostream;
    protected babylonUser user;
    protected Date connectDate;
    protected boolean stop = false;
    protected int pings = 0;
    protected boolean online = false;
///////////////
    public static String folderName=null;
    protected babylonServerWindow window = null;
///////////////
    protected babylonStringManager strings = null;
    private Class thisClass = babylonClientSocket.class;

    protected SimpleDateFormat dateFormatter =
	new SimpleDateFormat("MMM dd, yyyy hh:mm a");

    public babylonClientSocket(babylonServer parent, Socket s, 
			       ThreadGroup threadgroup)
    {
	super("Babylon Chat client connection");

	server = parent;
	mySocket = s;

	// Get a 'user' object
	user = new babylonUser(server);

	// Initially, use the language of the server
	strings = server.strings;
	user.language = strings.language;

	// Make an initial, dummy chat room
	user.chatroom = new babylonChatRoom("", "", false, "");

	// set up the streams
	try {
	    istream = new DataInputStream(mySocket.getInputStream());
	    ostream = new DataOutputStream(mySocket.getOutputStream());
	} 
	catch (IOException a) {
	    server.serverOutput(server.strings
				.get(thisClass, "errorstreams") + "\n");
	    try {
		mySocket.close();
	    } 
	    catch (IOException b) {
		server.serverOutput(server.strings
				    .get(thisClass, "errorclose") + "\n");
	    }
	}

	online = true;

	// Record the date/time of the connection
	connectDate = new Date();

	start();
    }

    public void run()
    {
	try {
	    // Wait for the client to begin the protocol negotiation
	    synchronized (istream) {
		short command = istream.readShort();
	
		if (command != babylonCommand.SETPROTO)
		    {
			server.serverOutput(server.strings
					    .get(thisClass, "noprotocol") +
					    "\n");
			server.disconnect(this, false);
			return;
		    }
		
		negotiateProtocol();
	    }
	}
	catch (IOException e) {
	    // This is not going well.  Couldn't even get the connection
	    // started
	    online = false;
	    server.disconnect(this, false);
	    return;
	}

	// Make a tickler to ensure that we know if the client
	// disconnects
	//new babylonClientTickler(this);

	while (!stop)
	    {
		try {
		    parseCommand();
		    ostream.flush();
		}
		catch (EOFException a) {
		    // This can happen occasionally, almost as if the
		    // Java stream thinks something is available to be read
		    // momentarily before it actually is... Just reset to
		    // the marked position
		    online = false;
		    if (!stop)
			{
			    System.out.println("resetting stream (" +
					       user.name + ")");
					       
			    try {
				istream.reset();
				online = true;
				continue;
			    }
			    catch (IOException b) {
				if (waitReconnect())
				    {
					online = true;
					continue;
				    }
			    }
			}
		    return;
		}
		catch (IOException e) {
		    online = false;
		    if (!stop)
			if (waitReconnect())
			    {
				online = true;
				continue;
			    }
		    return;
		}
		catch (Exception f) {
		    // Any exception caught here is definitely a bug of some
		    // sort.  Put it in the log, and if we are running in
		    // graphical mode, put up a dialog box and ask for a bug
		    // report.

		    // Turn the exception stack trace into a string
		    StringWriter sw = new StringWriter();
		    f.printStackTrace(new PrintWriter(sw));
		    String stackTrace = sw.toString();

		    server.serverOutput(server.strings
					.get(thisClass, "problem") + ": \n" +
					stackTrace);
		    
		    if (server.graphics && !server.autoBugReports)
			new babylonBugReportDialog(server.window, f);
		    
		    else if (server.autoBugReports)
			{
			    try {
				// Always send this in English, because that
				// is Mr. McLaughlin's language of choice
				new babylonBugReporter("Babylon Chat " +
						       "SERVER version " +
						       babylon.VERSION +
						       "\n\n" + stackTrace);
				server.serverOutput("(" + server.strings
						    .get(thisClass,
							 "sentreport") +
						    ")\n");
			    }
			    catch (Exception e) {
				server.serverOutput("(" + server.strings
						    .get(thisClass,
							 "sendfailed") +
						    ": " + e.toString() +
						    ")\n");
			    }
			}
		    else if (!server.graphics)
			{
			    server.serverOutput(server.strings.get(thisClass,
						   "noautoreport") + "\n");
			}

		    // Eesh, try to ignore it if possible
		    continue;
		}
	    }

	// One way or another, we're done
	return;
    }

    protected boolean waitReconnect()
    {
	// We've been unexpectedly disconnected.  Wait a second or two to
	// see whether the client automatically reconnects
	try { sleep(3000); }
	catch (InterruptedException ee) {
	    // Reconnected
	    System.out.println(user.name + " reconnected");
	    return (true);
	}
	
	// Gone
	server.disconnect(this, false);
	return (false);
    }

    public void parseCommand()
	throws IOException
    {
	short commandType = 0;
	
	synchronized (istream) {

	    // We need to do all input processing from within this
	    // 'synchronized' block, since we read the first part of the
	    // command here, followed by the rest in subroutines.

	    // Mark the current point so that we can return to it if
	    // necessary
	    istream.mark(1024);

	    // Try to read a short from the stream.  It indicates the
	    // command type
	    commandType = istream.readShort();
	    // Make sure we weren't stopped while blocking for input
	    if (stop)
		return;

	    // Now we examine the message being passed, and determine what
	    // to do with it.

	    if (protocolVersion >= 2.0) {
			
		    // The following commands are supported by protocol
		    // version 2.0.  See the file babylonCommand.java for
		    // the protocol definition
			
	            //System.out.println("This is CleintSocket.java commandType="+ commandType);
		    switch (commandType) {
			
		    	case babylonCommand.NOOP:
			{
			    // Do nothing
			    break;
			}

		    	case babylonCommand.PING:
			{
			    // We're receiving a ping reply from the client.
			    receivePing();
			    break;
			}

		    	case babylonCommand.USERINFO:
			{
			    // The new user is supplying information about
			    // themselves.
			    receiveUserInfo();
			    break;
			}

		    	case babylonCommand.DISCONNECT:
			{
			    // The user is telling us that he's disconnecting.
			    receiveDisconnect();
			    break;
			}

		    	case babylonCommand.ROOMLIST:
			{
			    // The client is requesting a list of chat rooms
			    receiveRoomList();
			    break;
			}

		    	case babylonCommand.INVITE:
			{
			    // The user is inviting another to join a chat
			    // room
			    receiveInvite();
			    break;
			}

		    	case babylonCommand.ENTERROOM:
			{
			    // The client is attempting to create/enter a chat
			    // room
			    receiveEnterRoom();
			    break;
			}
			
		    	case babylonCommand.BOOTUSER:
			{
			    // The client is attempting to boot another user
			    // from a chat room
			    receiveBootUser();
			    break;
			}

		    	case babylonCommand.BANUSER:
			{
			    // The client is attempting to ban another user
			    // from a chat room
			    receiveBanUser();
			    break;
			}

		    	case babylonCommand.ALLOWUSER:
			{
			    // The client wants to allow a banned user back
			    // into a chat room
			    receiveAllowUser();
			    break;
			}

		    	case babylonCommand.ACTIVITY:
			{
			    // The client is sending a message about the
			    // user's activity
			    receiveActivity();
			    break;
			}

		    	case babylonCommand.CHATTEXT:
			{
			    // The client is sending a line of chat text
			    receiveChatText();
			    break;
			}

		    	case babylonCommand.LINE:
			{
			    // The user drew a line on the canvas
			    receiveLine();
			    break;
			}

		    	case babylonCommand.ERASE:
			{
			    // The user erase on the canvas
			    receiveErase();
			    break;
			}

		    	case babylonCommand.RECT:
			{
			    	// The user drew a rectangle on the canvas
			    	receivePoly(babylonCommand.RECT);
			    	break;
			}

		    	case babylonCommand.OVAL:
			{
			    // The user drew an oval on the canvas
			    receivePoly(babylonCommand.OVAL);
			    break;
			}

		    	case babylonCommand.DRAWTEXT:
			{
			    // The user drew some text on the canvas
			    receiveDrawText();
			    break;
			}

		    	case babylonCommand.DRAWPICTURE:
			{
			    // The user pasted an image on the canvas
			    receiveDrawPicture();
			    break;
			}

		    case babylonCommand.CLEARCANV:
			{
			    // This client is clearing the canvas
			    receiveClearCanv();
			    break;
			}

		    case babylonCommand.PAGEUSER:
			{
			    // The user is paging other clients
			    receivePageUser();
			    break;
			}

		    case babylonCommand.INSTANTMESS:
			{
			    // The client wants to send someone an instant
			    // message
			    receiveInstantMess();
			    break;
			}

		    case babylonCommand.LEAVEMESS:
			{
			    // The client wants to leave someone a message
			    receiveLeaveMess();
			    break;
			}

		    case babylonCommand.READMESS:
			{
			    // The client wants to collect its messages
			    receiveReadMess();
			    break;
			}

		    case babylonCommand.ERROR:
			{
			    // The client is sending error information
			    receiveError();
			    break;
			}
		    case babylonCommand.SAVE: {
				receiveDataSave();
                                break;
                   	}
		//////////////////////////////////////////////////////////
		    case babylonCommand.PLAY: {
					
                                //System.out.println("PLAY BUTTON......");
				//folderName	
				String filepath=istream.readUTF();
                                //System.out.println("PLAY BUTTON..with file path -in server ...."+filepath);
				
				FileReader fr = new FileReader(filepath);
				BufferedReader br = new BufferedReader(fr);
				String s;
				while((s = br.readLine()) != null) {
					ostream.writeShort(babylonCommand.PLAY);
                                        ostream.writeUTF(s);
					//System.out.println("send data server to client "+s);
				}
				ostream.writeShort(babylonCommand.PLAYER);
				fr.close(); 
                                break;
                        }
			//////////////////////////////////////////////////////////


		    default:
			{
			    // Eek.  We don't understand this command,

			    byte[] foo;

			    // Get whatever data is in the
			    // stream
			    foo = new byte[istream.available()];
			    istream.readFully(foo);

			    // Hmm.  This client seems to be speaking a
			    // language that we don't understand.  Is it
			    // possibly a Babylon Chat 1.x client?  Let's
			    // check to see whether we can recognize any
			    // 1.x-era stuff in there
			    String stringFoo = new String(foo);
			    if (stringFoo.startsWith("AME "))
				{
				    // This is probably a 1.x client.
				    // Send a 1.x-style error message
				    // to the client and disconnect them.
				    notifyObsoleteV1();
				    shutdown();
				    return;
				}
				
			    System.out.println(server.strings.get(thisClass,
								  "unknown") +
					       " (" + user.name + ")");
			    break;
			}
		    }
		}
	}
    }

    protected void notifyObsoleteV1()
	throws IOException
    {
	// This function is used to notify a client that this server can't
	// accept connections from a Babylon Chat v1.x client.
	PrintWriter tmpOutput =
	    new PrintWriter(mySocket.getOutputStream(), true);

	tmpOutput.println("/SERVERMESSAGE " +
			  strings.get(thisClass, "incompatible"));
	tmpOutput.println("/SERVERMESSAGE " + 
			  strings.get(thisClass, "disconnected"));
	tmpOutput.flush();
    }

    protected babylonClientSocket findClientSocket(int userid)
    {
	// This will return the client socket object for the user with the
	// requested ID
	
	babylonClientSocket tempClient = null;
	babylonClientSocket returnClient = null;

	synchronized (server.connections) 
	    {
		for (int count = 0; count < server.currentConnections;
		     count ++)
		    {
			tempClient = (babylonClientSocket)
			    server.connections.elementAt(count);

			if (tempClient.user.id == userid)
			    {
				returnClient = tempClient;
				break;
			    }
		    }
	    }

	return (returnClient);
    }

    protected void catchUpWithCanvas(babylonChatRoom room)
	throws IOException
    {
	for (int count = 0; count < user.chatroom.canvasHistory.size();
	     count ++)
	    {
		babylonCanvasCommand command;
		try {
		    command = (babylonCanvasCommand)
			user.chatroom.canvasHistory.elementAt(count);
		}
		catch (ArrayIndexOutOfBoundsException e) {
		    // In case someone clears the canvas just as we're trying
		    // to grab the command object from the vector.
		    break;
		}

		// Send each drawing element depending on what it is.
		switch (command.commandtype)
		    {
		    case babylonCommand.LINE:
			{
			    sendLine(command.whodrew, command.colour,
				     command.x1, command.y1, command.x2,
				     command.y2, command.thick);
			    break;
			}
                    
		    case babylonCommand.ERASE:
			{
			    sendErase(command.whodrew, command.colour,
				     command.x1, command.y1, command.x2,
				     command.y2, command.thick);
			    break;
			}

		    case babylonCommand.RECT:
		    case babylonCommand.OVAL:
			{
			    sendPoly(command.commandtype, command.whodrew,
				     command.colour, command.x1, command.y1,
				     command.x2, command.y2, command.thick,
				     command.fill);
			    break;
			}
		    case babylonCommand.DRAWTEXT:
			{
			    sendDrawText(command.whodrew, command.colour,
					 command.x1, command.y1, command.type,
					 command.attribs, command.size,
					 command.text);
			    break;
			}
		    case babylonCommand.DRAWPICTURE:
			{
			    sendDrawPicture(command.whodrew, command.x1,
					    command.y1, command.pictureData);
			    break;
			}
		    }
	    }
    }

    protected void enterChatRoom(babylonChatRoom room)
	throws IOException
    {
	// This routine will add a user to a chat room, and announce the
	// user to all the other participants.

	babylonClientSocket tmpClient;

	// Add the user to the chat room
	room.addClient(this);

	// Set our chat room pointer
	user.chatroom = room;

	// Send an 'enter' message to all clients, including this one
	synchronized (server.connections)
	    {
		for (int count = 0; count < server.currentConnections;
		     count ++)
		    {
			tmpClient = (babylonClientSocket)
			    server.connections.elementAt(count);
			
			tmpClient.sendEnterRoom(user.id,
						user.chatroom);
		    }
	    }

	// Now, get this client up-to-date with whatever is currently on
	// the drawing canvas in the chat room
	catchUpWithCanvas(room);

	// Done
	return;
    }

    protected void leaveChatRoom()
	throws IOException
    {
	// This routine will remove a user from a chat room, and tell the
	// remaining users about it if required.  It will also close a chat
	// room if it's not the "main" chat room and there are no remaining
	// participants.

	babylonClientSocket tmpClient;

	// If there's no chat room for whatever reason, exit
	if (user.chatroom == null)
	    return;

	// Remove the user from the chat room
	user.chatroom.remClient(this);

	// If we're leaving some chat room other than the main one, and
	// there are no more users left in the room, we will delete the
	// chat room
	if (user.chatroom != server.mainRoom)
	    {
		if (user.chatroom.clients.size() == 0)
		    {
			// Delete the chat room
			server.chatRooms.removeElement(user.chatroom);
			server.chatRooms.trimToSize();

			synchronized (server.connections)
			    {
				// Send the updated chat room list to all
				// the clients
				for (int count = 0;
				     count < server.currentConnections;
				     count ++)
				    {
					tmpClient = (babylonClientSocket)
					    server.connections
					    .elementAt(count);
					tmpClient.sendRoomList();
				    }
			    }
		    }
	    }

	// Done
	return;
    }


    // The following routines will send and receive the various command
    // types

    protected void negotiateProtocol()
	throws IOException
    {
	double preferred = 0;

	// Read the client's preferred protocol version from the input
	// stream
	preferred = istream.readDouble();

	// Send a message to the user about which protocol we'll be
	// using.  It will always be the lesser of the one requested
	// or the one we know.

	if (preferred < protocolVersion)
	    protocolVersion = preferred;

	synchronized (ostream)
	    {
		ostream.writeShort(babylonCommand.SETPROTO);
		ostream.writeDouble(protocolVersion);
	    }
    }

    protected void sendPing()
	throws IOException
    {
	// Send a ping request to the client.  For the moment we're going to
	// try NOT disconnecting clients that don't respond to pings.  Instead
	// we'll see if this is sufficient to 'tickle' the connection,
	// resulting in an IOException if the connection is down.  Yes, this
	// seems to do the trick.  We don't need to enforce responses to 
	// ping requests at this stage (although the client has been
	// programmed to always return them).

	if (!online)
	    return;

	synchronized (ostream)
	    {
		ostream.writeShort(babylonCommand.PING);
	    }
    
	// Add one to the number of pings sent
	pings++;

	// Have we got too many unanswered pings?
    }

    protected void receivePing()
    {
	// The client has sent back a ping to us.  Subtract 1 from the number
	// of pings outstanding
	pings--;
    }

    protected void sendConnect(String userName)
	throws IOException
    {
	// Send a notification that a new user has connected

	if (!online)
	    return;

	synchronized (ostream)
	    {
		ostream.writeShort(babylonCommand.CONNECT);
		ostream.writeUTF(userName);
	    }
    }
    
    protected void sendUserInfo(babylonUser newuser)
	throws IOException
    {
	// Send information about a new user to this participant

	if (!online)
	    return;

	synchronized (ostream)
	    {
		ostream.writeShort(babylonCommand.USERINFO);
		ostream.writeInt(newuser.id);
		ostream.writeUTF(newuser.name);
		ostream.writeUTF(""); // Don't send password info
		ostream.writeBoolean(false); // Not encrypted
		ostream.writeUTF(newuser.additional);

		if (protocolVersion >= 2.1)
		    // Send the user's preferred language.
		    ostream.writeUTF(newuser.language);
	    }
    }

    protected void receiveUserInfo()
	throws IOException
    {
	babylonClientSocket tmpClient;

	// Receive information about a user
	istream.readInt(); // WE assign the user IDs
	user.name = istream.readUTF();
	user.password = istream.readUTF();
	if (!istream.readBoolean())
	    user.password =
		server.passwordEncryptor.encryptPassword(user.password);
	user.additional = istream.readUTF();
	
	if (protocolVersion >= 2.1)
	    // Read the user's preferred language.
	    user.language = istream.readUTF();

	// Set the language appropriate to this user
	strings = new babylonStringManager(server.myURL, user.language);

	synchronized (server.connections)
	    {
		// Make sure we don't already have a user by the same name.
		// Loop through all of the currently connected users
		for (int count = 0; count < server.currentConnections;
		     count ++)
		    {
			babylonClientSocket tmpSocket =
			    ((babylonClientSocket)
			     server.connections.elementAt(count));
				
			if (tmpSocket.user.name.equals(user.name))
			    {
				sendDisconnect(0, strings.get(thisClass,
							      "already") +
					       " " + user.name + " " +
					       strings.get(thisClass,
							   "loggedin"));

				// Give the client a moment to receive the
				// message before we disconnect him
				try { sleep(1000); }
				catch (InterruptedException e) {}

				this.shutdown();
				return;
			    }
		    }
	    }

	// Check for the reserved user name "Administrator".  This
	// name can only be used when connecting from the same system as
	// the server (via the Administrator console for example)
	if  (this.user.name.equals("Administrator"))
	    {
		// Is this a local connection?
		InetAddress tmpAddr = mySocket.getInetAddress();
		if (!tmpAddr.getHostAddress()
		    .equals(InetAddress.getLocalHost().getHostAddress()) &&
		    !tmpAddr.getHostAddress().equals("127.0.0.1"))
		    {
			sendDisconnect(0, strings.get(thisClass,
						      "noadministrator"));
			server.serverOutput(tmpAddr.getHostName() + " " +
					    server.strings
					    .get(thisClass,
						 "badadminattempt") +
					    "\n");

			// Give the client a moment to receive the message
			// before we disconnect him
			try { sleep(1000); } catch (InterruptedException e) {}

			this.shutdown();
			return;
		    }

		// Save a reference to this client socket in the server so
		// that the server can easily recognize the special status
		// of this connection
		server.administratorClient = this;
	    }

	// If we are requiring passwords, make sure the password matches
	// the one in the password file
	else if (server.requirePasswords)
	    {
 		boolean authenticated = false;
		try {
		    authenticated = server
			.checkPassword(babylonServer.userPasswordFileName,
				       user.name, user.password, "brihaspati");
		}
		catch (Exception e) {
		    // This user does not exist.  Should we create it?
		    if (server.allowNewUsers)
			{
			    // Create the new user account
			    try {
				server.createNewUser(user.name,
						     user.password);
			    }
			    catch (Exception f) {
				sendDisconnect(0, strings
					       .get(thisClass,
						    "nonewaccount"));

				// Give the client a moment to receive the
				// message before we disconnect him
				try { sleep(1000); }
				catch (InterruptedException g) {}

				this.shutdown();
				return;
			    }
			    //authenticated = true;
			}
		}

		if (!authenticated)
		    {
			// The user/password combo don't match.
			sendDisconnect(0, strings.get(thisClass,
						      "badpassword"));
			InetAddress tmpAddr =
			    mySocket.getInetAddress();
			server.serverOutput(server.strings
					    .get(thisClass, "failedlogin") +
					    " \"" + user.name + "\" " +
					    server.strings
					    .get(thisClass, "fromhost") +
					    " " + tmpAddr.getHostName() +
					    "\n");

			// Give the client a moment to receive the message
			// before we disconnect him
			try { sleep(1000); } catch (InterruptedException e) {}

			this.shutdown();
			return;
		    }
	    }
	
	synchronized (server.connections)
	    {
		// Tell our user about themselves
		sendUserInfo(user);
		
		// Send the list of chat rooms to the user
		sendRoomList();

		// Set our chat room pointer
		user.chatroom = server.mainRoom;

		// Add the user to the chat room
		user.chatroom.addClient(this);

		// Tell our user what room they're in
		sendEnterRoom(user.id, user.chatroom);

		// We need to tell all the other users about us and
		// vice-versa
		for (int count = 0; count < server.currentConnections;
		     count ++)
		    {
			tmpClient = (babylonClientSocket)
			    server.connections.elementAt(count);

			// Tell this other user that we have connected,
			// and tell them about us
			tmpClient.sendConnect(user.name);
			tmpClient.sendUserInfo(user);
			tmpClient.sendEnterRoom(user.id, user.chatroom);

			// Tell our user about them also
			sendUserInfo(tmpClient.user);
			sendEnterRoom(tmpClient.user.id,
				      tmpClient.user.chatroom);
		    }

		// Add this user to the list of connections
		server.connections.addElement(this);

		// Update our statistics
		server.currentConnections = server.connections.size();
		server.totalConnections++;
		
		// Has the peak number of connections been exceeded?
		if (server.currentConnections > server.peakConnections)
		    server.peakConnections = server.currentConnections;
	    }

	server.serverOutput(server.strings.get(thisClass, "newuser") + " " +
			    user.name + " " +
			    server.strings.get(thisClass, "loggingonat") +
			    " " + dateFormatter.format(connectDate) + " " +
			    server.strings.get(thisClass, "from") + " " +
			    mySocket.getInetAddress().getHostName() + "\n");
	server.serverOutput(server.strings.get(thisClass, "thereare") + " " +
			    server.currentConnections + " " +
			    server.strings.get(thisClass, "usersconnected") +
			    "\n");

	if (server.graphics)
	    {
		// Update the server window
		server.window.userList.add(user.name);
		server.window.disconnectAll.setEnabled(true);
		server.window.updateStats();
	    }

	// Send the banner message to the user
	sendServerText(strings.get(thisClass, "welcome") + " " +
		       babylon.VERSION + ".\n\n");

	// Send the welcome message, if there is one
	try {
	    URL welcomeURL = new URL(server.myURL.getProtocol(),
				     server.myURL.getHost(),
				     server.myURL.getFile()
				     + babylonServer
				     .welcomeMessageName);
	    BufferedReader in =
		new BufferedReader(new InputStreamReader(
						 welcomeURL.openStream()));
	    String inputLine = new String("");
	    String input = new String("");

	    while ((inputLine = in.readLine()) != null) 
		input = input.concat(inputLine + "\n");
	    in.close();

	    // Output.
	    sendServerText(input + "\n");
	}
	catch (Exception a) {}

	String mess = "";
	if (server.currentConnections == 1) 
	    // Tell them they're the only user
	    mess = strings.get(thisClass, "firstuser");

	else if (server.currentConnections > 2)
	    {
		// Say how many users are online, and how many chat
		// rooms, if applicable
		mess = strings.get(thisClass, "thereare") + " " +
		    (server.currentConnections - 1) + " " +
		    strings.get(thisClass, "otherusers");
		if (server.chatRooms.size() > 0)
		    mess += ", " + server.chatRooms.size() + " " +
			strings.get(thisClass, "chatrooms");
	    }
	else
	    {
		// There is one other user, possibly in another chat room
		mess = strings.get(thisClass, "oneother");
		if (server.chatRooms.size() > 0)
		    mess += " " + strings.get(thisClass, "inanother");
	    }
	sendServerText(mess + "\n\n");

	// Catch up with the drawing canvas in our room
	catchUpWithCanvas(user.chatroom);

	// Are there any saved messages waiting for this user name?
	int numberMessages = 0;
	for (int count = 0; count < server.messages.size(); count ++)
	    if (((babylonMessage) server.messages.elementAt(count))
		.messageFor.equals(user.name))
		numberMessages++;
	if (numberMessages > 0)
	    sendServerMessage(strings.get(thisClass, "youhave") +
			      " " + numberMessages + " " +
			      strings.get(thisClass, "messageswaiting"));
	// All set
    }

    protected void sendServerMessage(String data)
	throws IOException
    {
	// This will cause a message dialog to appear on the screen of
	// the user

	if (!online)
	    return;

	synchronized (ostream)
	    {
		ostream.writeShort(babylonCommand.SERVERMESS);
		ostream.writeUTF(data);
	    }
    }

    protected void sendDisconnect(int userId, String mess)
	throws IOException
    {
	// Tell this user that a user (possibly the user himself) is
	// disconnecting

	if (!online)
	    return;

	synchronized (ostream)
	    {
		ostream.writeShort(babylonCommand.DISCONNECT);
		ostream.writeInt(userId);
		ostream.writeUTF(mess);
	    }
    }

    protected void receiveDisconnect()
	throws IOException
    {
	// The user is disconnecting from the server.  Just clear out the
	// stream
	istream.readInt();
	istream.readUTF();
	istream.readFully(new byte[istream.available()]);

	// Do it.  Don't attempt to notify this user, obviously.
	server.disconnect(this, false);
    }

    protected void sendRoomList()
 	throws IOException
   {
	babylonClientSocket tmpClient;

	if (!online)
	    return;

	// Send a list of the available chat rooms to the client
	synchronized (ostream)
	    {
		ostream.writeShort(babylonCommand.ROOMLIST);
		ostream.writeShort(server.chatRooms.size() + 1);

		// Send info about the main chat room
		ostream.writeUTF(server.mainRoom.name);
		ostream.writeUTF(server.mainRoom.creatorName);
		ostream.writeBoolean(server.mainRoom.priv);
		ostream.writeBoolean(true); // Everyone's invited!
		ostream.writeInt(server.mainRoom.clients.size());

		// Write out the names of each user in the main chat room
		for (int count1 = 0;
		     count1 < server.mainRoom.clients.size();
		     count1++)
		    {
			tmpClient = (babylonClientSocket)
			    server.mainRoom.clients.elementAt(count1);

			ostream.writeUTF(tmpClient.user.name);
		    }

		// Now loop for each of the other chat rooms
		for (int count1 = 0; count1 < server.chatRooms.size();
		     count1 ++)
		    {
			babylonChatRoom room = (babylonChatRoom)
			    server.chatRooms.elementAt(count1);

			ostream.writeUTF(room.name);
			ostream.writeUTF(room.creatorName);
			ostream.writeBoolean(room.priv);

			if (room.priv)
			    {
				// Is the user invited?
				boolean invited = false;
				for (int count2 = 0;
				     count2 < room.invitedUsers.size();
				     count2++)
				    if (((Integer) room.invitedUsers
					 .elementAt(count2))
					.intValue() == user.id)
					{
					    invited = true;
					    break;
					}
				ostream.writeBoolean(invited);
			    }
			else
			    {
				ostream.writeBoolean(true);
			    }				

			ostream.writeInt(room.clients.size());

			// Write out the names of each user in the
			// chat room
			for (int count2 = 0; count2 < room.clients.size();
			     count2++)
			    {
				tmpClient = (babylonClientSocket)
				    room.clients.elementAt(count2);

				ostream.writeUTF(tmpClient.user.name);
			    }
		    }
	    }
    }

    protected void receiveRoomList()
 	throws IOException
   {
	// The client is requesting a list of the available chat rooms.
	// Read the null 'number' value from the stream
       istream.readShort();

	// Now we send the list
	sendRoomList();
    }

    protected void sendInvite(int userId, String roomName)
	throws IOException
    {
	// Tell the client they have been invited to join a chat room

	if (!online)
	    return;

	synchronized (ostream)
	    {
		ostream.writeShort(babylonCommand.INVITE);
		ostream.writeInt(userId);
		ostream.writeUTF(roomName);
		ostream.writeInt(user.id);
	    }
    }

    protected void receiveInvite()
	throws IOException
    {
	// The client wants to invite another user into a chat room

	String roomName = "";
	babylonChatRoom inviteRoom;
	int inviteUserId = 0;
	babylonClientSocket inviteUser = null;

	istream.readInt(); // user id
	roomName = istream.readUTF();
	inviteUserId = istream.readInt();

	// Find the requested chat room
	inviteRoom = server.findChatRoom(roomName);

	if (inviteRoom == null)
	    {
		// There's no such room, we guess
		sendServerMessage(strings.get(thisClass, "thechatroom") +
				  " \"" + roomName + "\" " +
				  strings.get(thisClass, "doesntexist"));
		return;
	    }

	// Check whether the requesting user is allowed to do this.  Users
	// are only allowed to invite others into rooms that they own.  Of
	// course, this is flawed since it's only based on names (and not
	// ids), but it will do for now.
	if (!inviteRoom.creatorName.equals(user.name) &&
	    (this != server.administratorClient))
	    {
		// This is not the owner of this chat room.  Tell them
		// to go away
		sendServerMessage(strings.get(thisClass, "noban"));
		return;
	    }

	// Find the client socket associated with the user id
	inviteUser = findClientSocket(inviteUserId);

	if (inviteUser == null)
	    {
		// Umm, the user can't be found
		sendServerMessage(strings.get(thisClass, "nosuchuser"));
		return;
	    }

	// Add the name of the user to the chat room's list of banned
	// users
	inviteRoom.inviteUser(inviteUser.user.id);

	// Send out an updated room list to the user.
	inviteUser.sendRoomList();

	// Send out the invitation notice to the invited user
	inviteUser.sendInvite(user.id, roomName);
    }

    protected void sendEnterRoom(int userId, babylonChatRoom room)
	throws IOException
    {
	// Tell the client that a user is entering a chatroom

	if (!online)
	    return;

	synchronized (ostream)
	    {
		ostream.writeShort(babylonCommand.ENTERROOM);
		ostream.writeInt(userId);
		ostream.writeUTF(room.name);
		ostream.writeBoolean(room.priv);
		ostream.writeUTF(""); // No password info
		ostream.writeBoolean(false); // Not encrypted
	    }
    }

    protected void receiveEnterRoom()
	throws IOException
    {
	// The client is requesting to enter/create a new chat room.
	// name of the room.
	String newRoomName = "";
	boolean priv = false;
	String password = "";
	babylonChatRoom newRoom;
	babylonClientSocket tmpClient = null;

	istream.readInt(); // user id
	newRoomName = istream.readUTF();
	priv = istream.readBoolean();
	password = istream.readUTF();
	if (!istream.readBoolean())
	    password = server.passwordEncryptor.encryptPassword(password);

	// Find the requested new chat room
	newRoom = server.findChatRoom(newRoomName);

	if (newRoom == null)
	    {
		// There's no such room, we guess.  We will create it.

		// Make sure the requested name is legal
		if (newRoomName.equals(""))
		    {
			sendServerMessage(strings.get(thisClass,
						      "needroomname"));
			return;
		    }

		// If it's private, it must have a password
		if (priv && password.equals(""))
		    {
			sendServerMessage(strings.get(thisClass,
						      "needpassword"));
			return;
		    }

		// Create the chat room
		newRoom = new babylonChatRoom(newRoomName, user.name, priv,
					      password);
		try {
		    newRoom.setLogging(server.logChats);
		}
		catch (IOException e) {
		    server.serverOutput(server.strings
					.get(thisClass, "nolog") + " " +
					newRoom.name + "\n");
		}

		// Add it to the list of chat rooms
		server.chatRooms.addElement(newRoom);

		synchronized (server.connections)
		    {
			// Send the updated chat room list to all the clients
			for (int count = 0; count < server.currentConnections;
			     count ++)
			    {
				tmpClient = (babylonClientSocket)
				    server.connections.elementAt(count);
				tmpClient.sendRoomList();
			    }
		    }
	    }
	else
	    {
		// The room already exists.  Make sure that this user is
		// allowed to enter this room (i.e. they have not been
		// banned, or whatever)
		if (!server.isUserAllowed(newRoom, this, password))
		    return;
	    }

	// Leave our current chat room
	if (user.chatroom != null)
	    leaveChatRoom();

	// Join the chat room.
	enterChatRoom(newRoom);

	// Save it.
	user.chatroom = newRoom;
    }

    protected void sendBootUser(int userId, String roomName)
	throws IOException
    {
	// Tell the client they are being booted from a chat room

	if (!online)
	    return;

	synchronized (ostream)
	    {
		ostream.writeShort(babylonCommand.BOOTUSER);
		ostream.writeInt(userId);
		ostream.writeUTF(roomName);
		ostream.writeInt(user.id);
	    }
    }

    protected void receiveBootUser()
	throws IOException
    {
	// The client wants to boot another user from a chat room

	String roomName = "";
	babylonChatRoom bootRoom;
	int bootUserId = 0;
	babylonClientSocket bootUser = null;

	istream.readInt(); // user id
	roomName = istream.readUTF();
	bootUserId = istream.readInt();

	// Find the requested chat room
	bootRoom = server.findChatRoom(roomName);

	if (bootRoom == null)
	    {
		// There's no such room, we guess
		sendServerMessage(strings.get(thisClass, "thechatroom") +
				  " \"" + roomName + "\" " +
				  strings.get(thisClass, "doesntexist"));
		return;
	    }

	// Check whether the requesting user is allowed to do this.  Users
	// are only allowed to boot others from rooms that they own.  Of
	// course, this is flawed since it's only based on names (and not
	// ids), but it will do for now.
	if (!bootRoom.creatorName.equals(user.name) &&
	    (this != server.administratorClient))
	    {
		// This is not the owner of this chat room.  Tell them
		// to go away
		sendServerMessage(strings.get(thisClass, "noboot"));
		return;
	    }

	// Find the client socket associated with the user id
	bootUser = findClientSocket(bootUserId);

	if (bootUser == null)
	    {
		// Umm, the user can't be found
		sendServerMessage(strings.get(thisClass, "nosuchuser"));
		return;
	    }

	// Send the user a message that they're being booted
	bootUser.sendBootUser(user.id, roomName);

	// If this is the main chat room, disconnect the user.
	if (bootRoom == server.mainRoom)
	    {
		server.disconnect(bootUser, true);
		return;
	    }
	
	// Otherwise, remove the user from the chat room
	else
	    {
		bootUser.leaveChatRoom();

		// Put the user back in the main chat room
		bootUser.enterChatRoom(server.mainRoom);

		bootUser.user.chatroom = server.mainRoom;
	    }
    }

    protected void sendBanUser(int userId, String roomName)
	throws IOException
    {
	// Tell the client they have been banned from a chat room

	if (!online)
	    return;

	synchronized (ostream)
	    {
		ostream.writeShort(babylonCommand.BANUSER);
		ostream.writeInt(userId);
		ostream.writeUTF(roomName);
		ostream.writeInt(user.id);
	    }
    }

    protected void receiveBanUser()
	throws IOException
    {
	// The client wants to ban another user from a chat room

	String roomName = "";
	babylonChatRoom banRoom;
	int banUserId = 0;
	babylonClientSocket banUser = null;

	istream.readInt(); // user id
	roomName = istream.readUTF();
	banUserId = istream.readInt();

	// Find the requested chat room
	banRoom = server.findChatRoom(roomName);

	if (banRoom == null)
	    {
		// There's no such room, we guess
		sendServerMessage(strings.get(thisClass, "thechatroom") +
				  " \"" + roomName + "\" " +
				  strings.get(thisClass, "doesntexist"));
		return;
	    }

	// Check whether the requesting user is allowed to do this.  Users
	// are only allowed to ban others from rooms that they own.  Of
	// course, this is flawed since it's only based on names (and not
	// ids), but it will do for now.
	if (!banRoom.creatorName.equals(user.name) &&
	    (this != server.administratorClient))
	    {
		// This is not the owner of this chat room.  Tell them
		// to go away
		sendServerMessage(strings.get(thisClass, "noban"));
		return;
	    }

	// Find the client socket associated with the user id
	banUser = findClientSocket(banUserId);

	if (banUser == null)
	    {
		// Umm, the user can't be found
		sendServerMessage(strings.get(thisClass, "nosuchuser"));
		return;
	    }

	// Add the name of the user to the chat room's list of banned
	// users
	banRoom.banUserName(banUser.user.name);

	// We don't need to notify the banned user; they will get a message
	// only if they try to enter the room they were banned from.
    }

    protected void sendAllowUser(int userId, String roomName)
	throws IOException
    {
	// Tell the client they have been allowed back into a chat room

	if (!online)
	    return;

	synchronized (ostream)
	    {
		ostream.writeShort(babylonCommand.ALLOWUSER);
		ostream.writeInt(userId);
		ostream.writeUTF(roomName);
		ostream.writeInt(user.id);
	    }
    }

    protected void receiveAllowUser()
	throws IOException
    {
	// The client wants to allow a banned user back into a chat room

	String roomName = "";
	babylonChatRoom allowRoom;
	int allowUserId = 0;
	babylonClientSocket allowUser = null;

	istream.readInt(); // user id
	roomName = istream.readUTF();
	allowUserId = istream.readInt();

	// Find the requested chat room
	allowRoom = server.findChatRoom(roomName);

	if (allowRoom == null)
	    {
		// There's no such room, we guess
		sendServerMessage(strings.get(thisClass, "thechatroom") +
				  " \"" + roomName + "\" " +
				  strings.get(thisClass, "doesntexist"));
		return;
	    }

	// Check whether the requesting user is allowed to do this.  Users
	// are only allowed to authorize others to rooms that they own.
	// Of course, this is flawed since it's only based on names (and not
	// ids), but it will do for now.
	if (!allowRoom.creatorName.equals(user.name) &&
	    (this != server.administratorClient))
	    {
		// This is not the owner of this chat room.  Tell them
		// to go away
		sendServerMessage(strings.get(thisClass, "noallow"));
		return;
	    }

	// Find the client socket associated with the user id
	allowUser = findClientSocket(allowUserId);

	if (allowUser == null)
	    {
		// Umm, the user can't be found
		sendServerMessage(strings.get(thisClass, "nosuchuser"));
		return;
	    }

	// Is the user in the list of banned users?
	for (int count = 0; count < allowRoom.bannedUserNames.size();
	     count ++)
	    {
		if (allowRoom.bannedUserNames.elementAt(count)
		    .equals(allowUser.user.name))
		    {
			// Remove the name of the user from the chat room's
			// list of banned users
			allowRoom.allowUserName(allowUser.user.name);

			// Notify the user that they are now allowed to enter
			// the room
			allowUser.sendAllowUser(user.id, roomName);
			
			break;
		    }
	    }
    }

    protected void sendActivity(int fromid, short activity)
	throws IOException
    {
	// This will inform the other users that we're in the process of
	// typing or drawing something

	if (!online)
	    return;

	synchronized (ostream)
	    {
		ostream.writeShort(babylonCommand.ACTIVITY);
		ostream.writeInt(fromid);
		ostream.writeShort(activity);
		ostream.writeInt(0); // Empty recipient list
	    }
    }

    protected void receiveActivity()
	throws IOException
    {
	// This will receive a signal that our client is busy doing some
	// activity such as typing or drawing and output it to the
	// appropriate recipients

	short activity = 0;
	int numRecipients = 0;
	babylonClientSocket toUser;

	// Read the sender user id (ours) and discard it.  Then read the
	// activity and the number of recipients
	istream.readInt();
	activity = istream.readShort();
	numRecipients = istream.readInt();

	// Is this a public action?  If so, send it to everyone in the current
	// chat room
	if (numRecipients == 0)
	    {
		for (int count = 0; count < user.chatroom.clients.size();
		     count ++)
		    {
			toUser = (babylonClientSocket)
			    user.chatroom.clients.elementAt(count);
			if (toUser != this)
			    toUser.sendActivity(user.id, activity);
		    }
	    }
	else
	    {
		// Loop for each of the intended recipients
		for (int count = 0; count < numRecipients; count ++)
		    {
			toUser = findClientSocket(istream.readInt());
		
			if (toUser != null)
			    if (toUser != this)
				// Send it out to this recipient
				toUser.sendActivity(user.id, activity);
		    }
	    }
    }

    protected void sendChatText(int fromid, boolean priv, String data)
	throws IOException
    {
	// This will send a line of chat text to our client

	if (!online)
	    return;

	synchronized (ostream)
	    {
		ostream.writeShort(babylonCommand.CHATTEXT);
		ostream.writeInt(fromid);
		ostream.writeBoolean(priv);
		ostream.writeShort(0); // No colour
		ostream.writeUTF(data);
		ostream.writeInt(0); // Empty recipient list
	    }
    }

    protected void receiveChatText()
	throws IOException
    {
	// This will receive a line of chat text and output it to the
	// appropriate recipients

	boolean priv = false;
	short colour = 0;
	String data = "";
	int numRecipients = 0;
	babylonClientSocket toUser;

	// Read the sender user id (ours) and discard it.  Then read the
	// privacy value, the colour, the data, and the number of recipients
	istream.readInt();
	priv = istream.readBoolean();
	colour = istream.readShort();
	data = istream.readUTF();
	numRecipients = istream.readInt();

	// Is this a public action?  If so, send it to everyone in the
	// current chat room
	if (!priv)
	    {
		for (int count = 0; count < user.chatroom.clients.size();
		     count ++)
		    {
			toUser = (babylonClientSocket)
			    user.chatroom.clients.elementAt(count);
			if (toUser != this)
			    toUser.sendChatText(user.id, priv, data);
		    }

		// Write it to the chat log file, if applicable
		if (server.logChats)
		    try {
			user.chatroom.log(user.name + ": " + data);
		    }
		    catch (IOException e) {
			server.serverOutput(server.strings
					    .get(thisClass, "nowritelog") +
					    "\n");
			return;
		    }			
	    }
	else
	    {
		// Loop for each of the intended recipients
		for (int count = 0; count <  numRecipients; count ++)
		    {
			toUser = findClientSocket(istream.readInt());
			
			if (toUser != null)
			    if (toUser != this)
				// Send it out to this recipient
				toUser.sendChatText(user.id, priv, data);
		    }
	    }
    }

    protected void sendLine(int fromid, short colour, short startx,
			    short starty, short endx, short endy, short thick)
	throws IOException
    {
	// Send out a line drawn by another user to our client

	if (!online)
	    return;

	synchronized (ostream)
	    {
		ostream.writeShort(babylonCommand.LINE);
		ostream.writeInt(fromid);
		ostream.writeShort(colour);
		ostream.writeShort(startx);
		ostream.writeShort(starty);
		ostream.writeShort(endx);
		ostream.writeShort(endy);
		ostream.writeShort(thick);
		ostream.writeInt(0); // Empty recipient list
	    }
    }

    protected void sendErase(int fromid, short colour, short startx,
			    short starty, short endx, short endy, short thick)
	throws IOException
    {
	// Send out a erase done by another user to our client

	if (!online)
	    return;

	synchronized (ostream)
	    {
		ostream.writeShort(babylonCommand.ERASE);
		ostream.writeInt(fromid);
		ostream.writeShort(11);
		ostream.writeShort(startx);
		ostream.writeShort(starty);
		ostream.writeShort(endx);
		ostream.writeShort(endy);
		ostream.writeShort(8);
		ostream.writeInt(0); // Empty recipient list
	    }
    }

    protected void receiveLine()
	throws IOException
    {
	// Our client has drawn a line on the screen.  We need to read
	// it in and send it out to the other users.

	short colour = 0;
	short startx = 0;
	short starty = 0;
	short endx = 0;
	short endy = 0;
	short thick = 0;
	int numRecipients = 0;
	babylonClientSocket toUser;

	// Read the sender user id (ours) and discard it.  Then read the
	// colour, x/y coordinates, the thickness, and the number of
	// recipients
	istream.readInt();
	colour = istream.readShort();
	startx = istream.readShort();
	starty = istream.readShort();
	endx = istream.readShort();
	endy = istream.readShort();
	thick = istream.readShort();
	numRecipients = istream.readInt();

	// Is this a public action?  If so, send it to everyone in the
	// current chat room
	if (numRecipients == 0)
	    {
		for (int count = 0; count < user.chatroom.clients.size();
		     count ++)
		    {
			toUser = (babylonClientSocket)
			    user.chatroom.clients.elementAt(count);
			if (toUser != this)
			    toUser.sendLine(user.id, colour, startx, starty,
					    endx, endy, thick);
		    }

		// Add this line to our canvas vector
		user.chatroom.canvasHistory.addElement(
		       new babylonCanvasCommand(babylonCommand.LINE, user.id,
						colour, startx, starty, endx,
						endy, (short) 0, (short) 0,
						(short) 0, thick, false, null,
						null));
	    }
	else
	    {
		// Now loop for each of the intended recipients
		for (int count = 0; count <  numRecipients; count ++)
		    {
			toUser = findClientSocket(istream.readInt());
			
			if (toUser != null)
			    if (toUser != this)
				// Send it out to this recipient
				toUser.sendLine(user.id, colour, startx,
						starty, endx, endy, thick);
		    }
	    }
    }

    protected void receiveErase()
	throws IOException
    {
	// Our client has erase on the screen.  We need to read
	// it in and send it out to the other users.

	short colour = 0;
	short startx = 0;
	short starty = 0;
	short endx = 0;
	short endy = 0;
	short thick = 0;
	int numRecipients = 0;
	babylonClientSocket toUser;

	// Read the sender user id (ours) and discard it.  Then read the
	// colour, x/y coordinates, the thickness, and the number of
	// recipients
	istream.readInt();
	colour = istream.readShort();
	startx = istream.readShort();
	starty = istream.readShort();
	endx = istream.readShort();
	endy = istream.readShort();
	thick = istream.readShort();
	numRecipients = istream.readInt();
	// Is this a public action?  If so, send it to everyone in the
	// current chat room
	if (numRecipients == 0)
	    {
		for (int count = 0; count < user.chatroom.clients.size();
		     count ++)
		    {
			toUser = (babylonClientSocket)
			    user.chatroom.clients.elementAt(count);
			if (toUser != this)
			    toUser.sendErase(user.id, colour, startx, starty,
					    endx, endy, thick);
		    }

		// Add this line to our canvas vector
		user.chatroom.canvasHistory.addElement(
		       new babylonCanvasCommand(babylonCommand.ERASE, user.id,
						colour, startx, starty, endx,
						endy, (short) 0, (short) 0,
						(short) 0, thick, false, null,
						null));
	    }
	else
	    {
		// Now loop for each of the intended recipients
		for (int count = 0; count <  numRecipients; count ++)
		    {
			toUser = findClientSocket(istream.readInt());
			
			if (toUser != null)
			    if (toUser != this)
				// Send it out to this recipient
				toUser.sendErase(user.id, colour, startx,
						starty, endx, endy, thick);
		    }
	    }
    }

    protected void sendPoly(short kind, int fromid, short colour, short x,
			    short y, short width, short height, short thick,
			    boolean fill)
	throws IOException
    {
	// Send out a polygon drawn by another user to our client.

	if (!online)
	    return;

	synchronized (ostream)
	    {
		ostream.writeShort(kind);
		ostream.writeInt(fromid);
		ostream.writeShort(colour);
		ostream.writeShort(x);
		ostream.writeShort(y);
		ostream.writeShort(width);
		ostream.writeShort(height);
		ostream.writeShort(thick);
		ostream.writeBoolean(fill);
		ostream.writeInt(0); // Empty recipient list
	    }
    }

    protected void receivePoly(short kind)
	throws IOException
    {
	// Our client has drawn a polygon on the screen.  We need to read
	// it in and send it out to the other users.

	short colour = 0;
	short x = 0;
	short y = 0;
	short width = 0;
	short height = 0;
	short thick = 0;
	boolean fill = false;
	int numRecipients = 0;
	babylonClientSocket toUser;

	// Read the sender user id (ours) and discard it.  Then read the
	// colour, x/y coordinates, the thickness, and the number of
	// recipients
	istream.readInt();
	colour = istream.readShort();
	x = istream.readShort();
	y = istream.readShort();
	width = istream.readShort();
	height = istream.readShort();
	thick = istream.readShort();
	fill = istream.readBoolean();
	numRecipients = istream.readInt();
	// Is this a public action?  If so, send it to everyone in the
	// current chat room
	if (numRecipients == 0)
	    {
		for (int count = 0; count < user.chatroom.clients.size();
		     count ++)
		    {
			toUser = (babylonClientSocket)
			    user.chatroom.clients.elementAt(count);
			if (toUser != this)
			    toUser.sendPoly(kind, user.id, colour, x, y,
					    width, height, thick, fill);
		    }
		// Add this shape to our canvas vector
		user.chatroom.canvasHistory.addElement(
		       new babylonCanvasCommand(kind, user.id, colour, x, y,
						width, height, (short) 0,
						(short) 0, (short) 0, thick,
						fill, null, null));
	    }
	else
	    {
		// Now loop for each of the intended recipients
		for (int count = 0; count <  numRecipients; count ++)
		    {
			toUser = findClientSocket(istream.readInt());
			
			if (toUser != null)
			    if (toUser != this)
				// Send it out to this recipient
				toUser.sendPoly(kind, user.id, colour, x, y,
						width, height, thick, fill);
		    }
	    }
    }

    protected void sendDrawText(int fromid, short colour, short x, short y,
				short type, short attribs, short size, 
				String text)
	throws IOException
    {
	// Send out some text drawn by another user to our client.

	if (!online)
	    return;

	synchronized (ostream)
	    {
		ostream.writeShort(babylonCommand.DRAWTEXT);
		ostream.writeInt(fromid);
		ostream.writeShort(colour);
		ostream.writeShort(x);
		ostream.writeShort(y);
		ostream.writeShort(type);
		ostream.writeShort(attribs);
		ostream.writeShort(size);
		ostream.writeUTF(text);
		ostream.writeInt(0); // Empty recipient list
	    }
    }

    protected void receiveDrawText()
	throws IOException
    {
	// Our client has drawn some text on the screen.  We need to read
	// it in and send it out to the other users.

	short colour = 0;
	short x = 0;
	short y = 0;
	short type = 0;
	short attribs = 0;
	short size = 0;
	String text = "";
	int numRecipients = 0;
	babylonClientSocket toUser;
	// Read the sender user id (ours) and discard it.  Then read the
	// colour, x/y coordinates, the type, size, and attributes, and the
	// number of recipients
	istream.readInt();
	colour = istream.readShort();
	x = istream.readShort();
	y = istream.readShort();
	type = istream.readShort();
	attribs = istream.readShort();
	size = istream.readShort();
	text = istream.readUTF();
	numRecipients = istream.readInt();

	// Is this a public action?  If so, send it to everyone in the
	// current chat room
	if (numRecipients == 0)
	    {
		for (int count = 0; count < user.chatroom.clients.size();
		     count ++)
		    {
			toUser = (babylonClientSocket)
			    user.chatroom.clients.elementAt(count);
			if (toUser != this)
			    toUser.sendDrawText(user.id, colour, x, y, type,
						attribs, size, text);
		    }
		// Add this text to our canvas vector
		user.chatroom.canvasHistory.addElement(
		       new babylonCanvasCommand(babylonCommand.DRAWTEXT,
						user.id, colour, x, y,
						(short) 0, (short) 0, type,
						attribs, size, (short) 0,
						false, text, null));
	    }
	else
	    {
		// Now loop for each of the intended recipients
		for (int count = 0; count <  numRecipients; count ++)
		    {
			toUser = findClientSocket(istream.readInt());
			
			if (toUser != null)
			    if (toUser != this)
				// Send it out to this recipient
				toUser.sendDrawText(user.id, colour, x, y,
					    type, attribs, size, text);
		    }
	    }
    }

    protected void sendDrawPicture(int fromid, short x, short y,
				   byte[] pictureData)
	throws IOException
    {
	// Send out an image pasted by another user to our client.

	if (!online)
	    return;

	synchronized (ostream)
	    {
		ostream.writeShort(babylonCommand.DRAWPICTURE);
		ostream.writeInt(fromid);
		ostream.writeShort(x);
		ostream.writeShort(y);
		ostream.writeInt(pictureData.length);
		ostream.write(pictureData, 0, pictureData.length);
		ostream.writeInt(0); // Empty recipient list
	    }
    }

    protected void receiveDrawPicture()
	throws IOException
    {
	// Our client has pasted a picture on the screen.  We need to read
	// it in and send it out to the other users.

	short x = 0;
	short y = 0;
	int length = 0;
	byte[] data = null;
	int numRecipients = 0;
	babylonClientSocket toUser;

	// Read the sender user id (ours) and discard it.  Then read the
	// colour, x/y coordinates, the type, size, and attributes, and the
	// number of recipients
	istream.readInt();
	x = istream.readShort();
	y = istream.readShort();
	length = istream.readInt();
		
	data = new byte[length];
	int read = 0;

	while (read < length)
	    read += istream.read(data, read, (length - read));
	
	numRecipients = istream.readInt();

	// Is this a public action?  If so, send it to everyone in the
	// current chat room
	if (numRecipients == 0)
	    {
		for (int count = 0; count < user.chatroom.clients.size();
		     count ++)
		    {
			toUser = (babylonClientSocket)
			    user.chatroom.clients.elementAt(count);
			if (toUser != this)
			    toUser.sendDrawPicture(user.id, x, y, data);
		    }
		// Add this picture to our canvas vector
		user.chatroom.canvasHistory.addElement(
		       new babylonCanvasCommand(babylonCommand.DRAWPICTURE,
						user.id, (short) 0, x, y,
						(short) 0, (short) 0,
						(short) 0, (short) 0,
						(short) 0, (short) 0,
						false, null, data));
	    }
	else
	    {
		// Now loop for each of the intended recipients
		for (int count = 0; count <  numRecipients; count ++)
		    {
			toUser = findClientSocket(istream.readInt());
			
			if (toUser != null)
			    if (toUser != this)
				// Send it out to this recipient
				toUser.sendDrawPicture(user.id, x, y, data);
		    }
	    }
    }

    protected void sendClearCanv(int fromid)
	throws IOException
    {
	// This will tell our client to clear the canvas

	if (!online)
	    return;

	synchronized (ostream)
	    {
		ostream.writeShort(babylonCommand.CLEARCANV);
		ostream.writeInt(fromid);
		ostream.writeInt(0); // Empty recipient list
	    }
    }

    protected void receiveClearCanv() throws IOException {
	// This will receive a signal that our client wants to clear
	// the canvas

	int numRecipients = 0;
	babylonClientSocket toUser;

	// Read the sender user id (ours) and discard it.  Then read the
	// number of recipients
	istream.readInt();
	numRecipients = istream.readInt();
	// Is this a public action?  If so, send it to everyone in the
	// current chat room
	if (numRecipients == 0)
	    {
		for (int count = 0; count < user.chatroom.clients.size();
		     count ++)
		    {
			toUser = (babylonClientSocket)
			    user.chatroom.clients.elementAt(count);
			if (toUser != this)
			    toUser.sendClearCanv(user.id);
		    }

		// Empty out our canvas vector
		user.chatroom.canvasHistory.removeAllElements();
	    }
	else
	    {
		// Loop for each of the intended recipients
		for (int count = 0; count < numRecipients; count ++)
		    {
			toUser = findClientSocket(istream.readInt());
		
			if (toUser != null)
			    if (toUser != this)
				// Send it out to this recipient
				toUser.sendClearCanv(user.id);
		    }
	    }
    }

    protected void sendPageUser(int fromid)
	throws IOException
    {
	// This will tell our client to page our user

	if (!online)
	    return;

	synchronized (ostream)
	    {
		ostream.writeShort(babylonCommand.PAGEUSER);
		ostream.writeInt(fromid);
		ostream.writeInt(0); // Empty recipient list
	    }
    }

    protected void receivePageUser()
	throws IOException
    {
	// This will receive a signal that our client wants to page
	// one or more other users

	int numRecipients = 0;
	babylonClientSocket toUser;

	// Read the sender user id (ours) and discard it.  Then read the
	// number of recipients
	istream.readInt();
	numRecipients = istream.readInt();

	// Is this a public action?  If so, send it to everyone in the
	// current chat room
	if (numRecipients == 0)
	    {
		for (int count = 0; count < user.chatroom.clients.size();
		     count ++)
		    {
			toUser = (babylonClientSocket)
			    user.chatroom.clients.elementAt(count);
			if (toUser != this)
			    toUser.sendPageUser(user.id);
		    }
	    }
	else
	    {
		// Loop for each of the intended recipients
		for (int count = 0; count < numRecipients; count ++)
		    {
			toUser = findClientSocket(istream.readInt());
		
			if (toUser != null)
			    if (toUser != this)
				// Send it out to this recipient
				toUser.sendPageUser(user.id);
		    }
	    }
    }

    protected void sendInstantMess(int whoFrom, String message)
	throws IOException
    {
	// Someone wants to send an instant message to our client

	if (!online)
	    return;

	synchronized (ostream)
	    {
		ostream.writeShort(babylonCommand.INSTANTMESS);
		ostream.writeInt(whoFrom);
		ostream.writeInt(user.id); // Our id
		ostream.writeUTF(message);
	    }
    }

    protected void receiveInstantMess()
	throws IOException
    {
	int whoFor = 0;
	String message = "";

	// Our client wants to send an instant message to another user.
	// First, read our own user ID and discard it.  Then, read the name
	// of the recipient followed by the message text.
	istream.readInt();
	whoFor = istream.readInt();
	message = istream.readUTF();

	synchronized (server.connections)
	    {
		// Find the recipient and send the message
		for (int count = 0; count < server.currentConnections;
		     count ++)
		    {
			babylonClientSocket toUser = (babylonClientSocket)
			    server.connections.elementAt(count);
			if (toUser.user.id == whoFor)
			    {
				toUser.sendInstantMess(user.id, message);
				break;
			    }
		    }
	    }
    }
//////////////////////////////////////////////////////////////////////////
public void receiveDataSave()
    {
	try{
		folderName = istream.readUTF();
        	//System.out.println(" Data="+folderName+"-------startsWith=="+folderName.startsWith("--"));
	        if(folderName.startsWith("--"))
        	{
        		folderName = folderName.replaceAll("--","");
	                folderName = folderName.replaceAll("\n","");
        	        StringTokenizer st=new StringTokenizer(folderName,"$");
                	String time=st.nextToken();
	                String token=st.nextToken();
        	        String token1=st.nextToken();
                	//System.out.println("time="+time+"===token="+token+"====token1="+token1);
	                folderName = time+"$"+token+"$"+user.name + ":"+ token1;
		}
	        if(folderName.startsWith("CreateFile")) {
        		folderName=folderName.replaceAll("CreateFile","");
                	Log.getController().createFile(folderName);
	                Log.getController().start();
		}else if(folderName.startsWith("Create File")){
	        	folderName=folderName.replaceAll("Create File","");
                	Log.getController().createFile(folderName);
        	        Log.getController().start();
		}else {
        		Log.getController().setLog(folderName);
		}
	} //try
	catch(Exception e){}
    }

//////////////////////////////////////////////////////////////////////////////////
    protected void receiveLeaveMess()
	throws IOException
    {
	String whofor = "";
	String message = "";

	// Our client wants to leave a message for another user.  First, read
	// our own user ID and discard it.  Then, read the name of the
	// recipient followed by the message text.
	istream.readInt();
	whofor = istream.readUTF();
	message = istream.readUTF();

	// Add it to the list of messages
	server.messages.addElement(new babylonMessage(whofor, user.name, 
							message));
    }

    protected void receiveReadMess()
	throws IOException
    {
	// The client is requesting his messages

	babylonMessage m;

	// Read the sender user id (ours) and discard it.
	istream.readInt();

	// Now send all of the user's stored messages
	sendStoredMess();
    }

    protected void sendStoredMess()
	throws IOException
    {
	// This will send all of the messages that are being stored for
	// this user
	short numberMessages = 0;
	babylonMessage message;

	// Figure out how many messages are for this user.
	for (int count = 0; count < server.messages.size(); count ++)
	    if (((babylonMessage) server.messages.elementAt(count))
		.messageFor.equals(user.name))
		numberMessages++;

	if (!online)
	    return;

	synchronized (ostream)
	    {
		ostream.writeShort(babylonCommand.STOREDMESS);
		ostream.writeShort(numberMessages);

		// Now send all of the messages themselved
		for (int count = 0; count < server.messages.size(); count ++)
		    {
			message =
			    (babylonMessage) server.messages.elementAt(count);

			if (message.messageFor.equals(user.name))
			    {
				// Send the message to the client
				ostream.writeUTF(message.messageFrom);
				ostream.writeUTF(message.text);
			
				server.messages.removeElement(message);
				count -= 1;
			    }
		    }
	    }
    }

    protected void sendError(int fromid, short errorCode)
	throws IOException
    {
	// This will send an error notice from another client to ours.

	if (!online)
	    return;

	synchronized (ostream)
	    {
		ostream.writeShort(babylonCommand.ERROR);
		ostream.writeInt(fromid);
		ostream.writeShort(errorCode);
		ostream.writeInt(0); // Empty recipient list
	    }
    }

    protected void receiveError()
	throws IOException
    {
	// This will receive an error message from our client and forward
	// it to the appropriate user(s)

	int numRecipients = 0;
	short errorCode = 0;
	babylonClientSocket toUser;

	// Read the sender user id (ours) and discard it.
	istream.readInt();
	errorCode = istream.readShort();
	numRecipients = istream.readInt();

	// Is this a public action?  If so, send it to everyone in the
	// current chat room
	if (numRecipients == 0)
	    {
		for (int count = 0; count < user.chatroom.clients.size();
		     count ++)
		    {
			toUser = (babylonClientSocket)
			    user.chatroom.clients.elementAt(count);
			if (toUser != this)
			    toUser.sendError(user.id, errorCode);
		    }
	    }
	else
	    {
		// Loop for each of the intended recipients
		for (int count = 0; count < numRecipients; count ++)
		    {
			toUser = findClientSocket(istream.readInt());
		
			if (toUser != null)
			    if (toUser != this)
				// Send it out to this recipient
				toUser.sendError(user.id, errorCode);
		    }
	    }
    }

    protected void sendServerText(String data)
	throws IOException
    {
	// This will just output a text message from the server to be
	// displayed in the user's chat window

	if (!online)
	    return;

	synchronized (ostream)
	    {
		ostream.writeShort(babylonCommand.CHATTEXT);
		ostream.writeInt(0); // From user "nobody"
		ostream.writeBoolean(false); // Not private
		ostream.writeShort(0); // No colour
		ostream.writeUTF(data);
		ostream.writeInt(0); // Empty recipient list
	    }
    }

    protected void shutdown()
    {
	// Stop the thread for this client socket
	stop = true;

	// Close the data streams
	try {
	    // Don't do this synchronized, since the client reader will be
	    // sitting there blocking, waiting for data.
	    istream.close();
	}
	catch (IOException a) {}

	try {
	    synchronized (ostream)
		{
		    ostream.flush();
		    ostream.close();
		}
	}
	catch (IOException a) {}

	// Close the socket
	try {
	    mySocket.close();
	} 
	catch (IOException F) {}

	// Force garbage collection, since some clients seem to hold on
	// to the connection somehow
	System.gc();
	
	return;
    }
}
