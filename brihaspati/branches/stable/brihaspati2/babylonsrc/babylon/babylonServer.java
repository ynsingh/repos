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
//  babylonserver.java
//

import java.net.*;
import java.util.*;
import java.text.*;
import java.io.*;
import babylon.*;

class babylonServerShutdown
    extends Thread
{
    // This gets called when the VM is shutting down.  If the server has
    // already terminated properly (for example, from the administrator
    // pushing the 'shut down' button), then there's nothing to do.  But,
    // if the administrator sends a KILL signal, or presses CTRL-C or
    // something like that, then this thread will shut down the server
    // properly

    private babylonServer server;
    
    public babylonServerShutdown(babylonServer s)
    {
	server = s;
    }

    public void run()
    {
	if (server.stop)
	    // The server has already shut down by itself (i.e. not
	    // from an external signal)
	    return;

	server.externalShutdown = true;
	server.shutdown();
    }
}

public class babylonServer
    extends Thread
{
    protected static String userPasswordFileName = "User.passwords";
    protected static String serverLogName = "Server.log";
    protected static String messageFileName = "Messages.saved";
    protected static String welcomeMessageName = "WELCOME.TXT";
  
    public static String authorizedUser = "brihaspati";
    protected static String portnumberParam = "-portnumber";
    protected static String usepasswordsParam = "-usepasswords";
    protected static String newusersParam = "-newusers";
    protected static String nographicsParam = "-nographics";
    protected static String chatlogsParam = "-chatlogs";
    protected static String bugreportsParam = "-bugreports";
		
    protected static int DEFAULTPORT = 12468;

    protected URL myURL;
    protected int port = DEFAULTPORT;
    protected babylonServerWindow window;
    protected boolean graphics = true;
    protected boolean requirePasswords = false;
    protected boolean allowNewUsers = false;
    protected boolean logChats = false;
    protected boolean autoBugReports = false;
    private Integer userIdCounter = new Integer(1);

    protected ServerSocket myServerSocket;
    protected Socket mySocket;
    protected ThreadGroup myThreadGroup;
    protected babylonClientSocket client;

    protected Vector connections;
    public Vector messages;
    public babylonChatRoom mainRoom;
    public Vector chatRooms;

    private FileOutputStream log;
    protected babylonPasswordEncryptor passwordEncryptor;

    protected babylonClientSocket administratorClient;

    protected int currentConnections = 0;
    protected int peakConnections = 0;
    protected int totalConnections = 0;
    
    protected boolean stop = false;
    protected boolean externalShutdown = false;

    // For managing strings according to locale
    protected babylonStringManager strings = null;
    private Class thisClass = babylonServer.class;

    protected SimpleDateFormat dateFormatter =
	new SimpleDateFormat("MMM dd, yyyy hh:mm a");


    
    public babylonServer(String[] args)
    {
	super("Babylon Chat server");

	// Get a URL to describe the invocation directory
	try {
	    myURL = new URL("file", "localhost", "./");
	}
	catch (Exception E) {
	    System.out.println(E);
	    System.exit(1);
	}
	
	strings = new babylonStringManager(myURL,
					   Locale.getDefault().getLanguage());

	// Parse our args.  Only continue if successful
	if (!parseArgs(args))
	    System.exit(1);

	// Start up the log file
	try {
	    File logFile = new File(serverLogName);
	    log = new FileOutputStream(logFile);
	}
	catch (IOException e) {
	    // Oops, no log file
	    serverOutput(strings.get(thisClass, "noopen") + " " +
			 serverLogName + "\n");
	}

	// if user wants graphics, set up simple window

	if (graphics)
	    {
		window =
		    new babylonServerWindow(this,
					    strings.get(thisClass,
							"babylonversion") +
					    babylon.VERSION);
		window.setSize(400, 400);
		window.setVisible(true);
	    }

	else
	    {
		System.out.println("\n" + strings.get(thisClass,
						      "serverstatus"));
		System.out.println(strings.get(thisClass, "listenport") +
				   " " + port);
		System.out.println(strings.get(thisClass, "connections"));
	    }

	try {
	    myServerSocket = new ServerSocket(port);
	} 
	catch (IOException e) {
	    System.out.println("\n" + strings.get(thisClass,
						  "noserversocket"));
	    System.exit(1);
	}

	connections = new Vector();
	messages = new Vector();
	chatRooms = new Vector();
	myThreadGroup = new ThreadGroup("Clients");

	// Set up the object for encrypting passwords
	passwordEncryptor = new babylonPasswordEncryptor();

	if (requirePasswords)
	    // Try to make sure the password file exists
	    try {
		new FileOutputStream(userPasswordFileName, true).close();
	    }
	    catch (IOException f) {}

	// Create the initial 'main' chat room
	mainRoom = new babylonChatRoom("Main", "Administrator", false, null);
	try {
	    mainRoom.setLogging(logChats);
	}	
	catch (IOException e) {
	    serverOutput(strings.get(thisClass, "noroomlog") + " " +
			 mainRoom.name + "\n");
	}

	serverOutput(strings.get(thisClass, "readingmessages") + "\n");
	readMessages();

	serverOutput(strings.get(thisClass, "waiting") + "\n");

	// To catch shutdown events, such as CTRL-C.  Note that this Java
	// feature was only introduced as of 1.3, so if you are getting
	// compilation errors, you should check your Javac version or comment
	// out this bit.
	double javaVersion = 0.0;
	try {
	    javaVersion = new Double(System.getProperty("java.version")
				     .substring(0, 3)).doubleValue();
	}
	catch (NumberFormatException e) {}

	// If you get compile errors here, you can upgrade to JDK 1.3
	// or delete the following 2 lines
	if (javaVersion >= 1.3)
	    Runtime.getRuntime()
		.addShutdownHook(new babylonServerShutdown(this));

	start();
    }

    public boolean checkPassword(String fileName, String userName,
				 String password, String check )
	throws Exception
    {
	// Return true if the supplied user name/password combo match
	
	DataInputStream passwordStream = null;

	// Try to find the user name/password combo in the password file
	try {
	    
	    passwordStream =
		new DataInputStream(new FileInputStream(fileName));
	    
	    // Read entry by entry.
	    while(true)
		{
		    String tempUserName = "";
		    String tempPassword = "";
		    try {
			tempUserName = passwordStream.readUTF();
			tempPassword = passwordStream.readUTF();
		    }
		    catch (EOFException e) {
			// Reached the end of the file, no match
			throw new Exception(strings.get(thisClass,
							"nosuchuser"));
		    }

		    // Do the user name and password match?
		    if (tempUserName.equals(userName))
			{
			    if (authorizedUser.equals(check))
			    {
			    if (tempPassword.equals(password))
				return (true);
			    else
				// The user name exists, but the password
				// doesn't match
				return (false);
			    }
 			    
			    else
			       // The user is not authorizedUser.
			    return(false);
			}
		}
	} 
	catch (IOException e) {
	    serverOutput(strings.get(thisClass, "errorpasswordfile") +
			 " " + e.toString() + "\n");
	    return (false);
	}
    }

    protected babylonChatRoom findChatRoom(String roomName)
    {
	// This will return the chat room object with the corresponding
	// name
	
	babylonChatRoom tempRoom = null;
	babylonChatRoom returnRoom = null;

	// Is it the main chat room?
	if (mainRoom.name.equals(roomName))
	    returnRoom = mainRoom;

	else
	    {
		for (int count = 0; count < chatRooms.size(); count ++)
		    {
			tempRoom = (babylonChatRoom)
			    chatRooms.elementAt(count);

			if (tempRoom.name.equals(roomName))
			    {
				returnRoom = tempRoom;
				break;
			    }
		    }
	    }

	return (returnRoom);
    }

    public boolean isUserAllowed(babylonChatRoom room,
			        babylonClientSocket client, String password)
    {
	// Return true if a user is allowed to enter a chat room.  False
	// otherwise.

	// Private room?
	if (room.priv)
	    {
		boolean invited = false;
			
		// Make sure that this user was either invited, or supplied
		// the correct password.  Check the password first
		
		if (!room.password.equals(password))
		    {
			// No correct password supplied.  Check the list
			// of invitees		    

			for (int count2 = 0; 
			     count2 < room.invitedUsers.size(); count2 ++)
			    {
				Integer Id = (Integer) room.invitedUsers
				    .elementAt(count2);
				
				if (Id.intValue() == client.user.id)
				    {
					invited = true;
					break;
				    }
			    }
		
			if (!invited)
			    {
				try {
				    client.sendServerMessage(
				     client.strings.get(thisClass,
							"notallowed") +
				     " " + room.name);
				}
				catch (IOException e) {
				    disconnect(client, false);
				    return (false);
				}
				return (false);
			    }
		    }
	    }

	// Make sure the user has not been banned from the room
	for (int count1 = 0; count1 < room.bannedUserNames.size(); count1 ++)
	    {
		if (room.bannedUserNames.elementAt(count1)
		    .equals(client.user.name))
		    {
			// This user has been banned from this room.  Send
			// them a message and quit
			try {
			    client.sendBanUser(client.user.id, room.name);
			}
			catch (IOException e) {
			    disconnect(client, false);
			    return (false);
			}
			return (false);
		    }
	    }
		
	// The user is allowed
	return (true);
    }

    public synchronized void disconnect(babylonClientSocket who,
					boolean notify)
    {
	int count;
	babylonChatRoom chatRoom;

	if (notify)
	    {
		try {
		    // Try to let the user know they're being disconnected 
		    who.sendDisconnect(who.user.id, strings
				       .get(thisClass, "disconnected"));
		}
		catch (IOException e) {}
	    }
	
	// Shut down the client socket
	who.shutdown();
		
	// Remove the user from their chat room.
	try {
	    who.leaveChatRoom();
	}
	catch (IOException e) {}
	
	// Remove the user from our list of connections
	synchronized (connections)
	    {
		connections.removeElement(who);
		connections.trimToSize();
		currentConnections = connections.size();

		// Tell all the other clients to ditch this user
		for (count = 0; count < currentConnections; count ++)
		    {
			babylonClientSocket other = (babylonClientSocket)
			    connections.elementAt(count);
			try {
			    other.sendDisconnect(who.user.id, "");
			}
			catch (IOException e) {}
		    }
	    }

	serverOutput(strings.get(thisClass, "user") + " " + who.user.name +
		     " " + strings.get(thisClass, "disconnectedat") + " " +
		     dateFormatter.format(new Date()) + "\n");

	serverOutput(strings.get(thisClass, "thereare") + " " +
		     currentConnections + " " +
		     strings.get(thisClass, "usersconnected") + "\n");
	
	try { 
	    sleep(250); 
	} 
	catch (InterruptedException I) {}

	if (graphics)
	    {
		synchronized (window.userList) {
		    // Remove the user name from the list widget.  We do this
		    // loop to make sure it hasn't already been removed,
		    // since disconnect() can get called multiple times for
		    // one disconnection.
		    for (count = 0; count < window.userList.getItemCount();
			 count ++)
			{
			    if (window.userList.getItem(
					  count).equals(who.user.name))
				{
				    window.userList.remove(who.user.name);
				    break;
				}
			}
		}
		
		window.updateStats();

		window.disconnectAll.setEnabled(currentConnections > 0);
		synchronized (window.userList)
		    {
			if ((currentConnections <= 0) ||
			    window.userList.getSelectedItem() == null)
			    window.disconnect.setEnabled(false);
		    }
	    }
	return;
    }


    public synchronized void disconnectAll(boolean notify)
    {
	int count;

	synchronized (connections)
	    {
		// Loop backwards through all of the current connections
		for(count = (currentConnections - 1); count >= 0; count --)
		    {
			babylonClientSocket temp = (babylonClientSocket)
			    connections.elementAt(count);
			
			if (temp == null)
			    continue;

			disconnect(temp, notify);
		    }
	    }

	return;
    }


    public void run()
    {
	while (!stop) 
	    {
		try {
		    mySocket = myServerSocket.accept();
		} 
		catch (IOException e) { 
		    serverOutput(strings.get(thisClass, "socketerror") +
				 "\n");
		    try {
			myServerSocket.close();
		    } 
		    catch (IOException f) {
			serverOutput(strings.get(thisClass,
						 "closesocketerror") + "\n");
		    }
		    System.exit(1);
		}

		if (mySocket == null)
		    {
			serverOutput(strings.get(thisClass, "nullsocket") +
				     "\n");
			try {
			    myServerSocket.close();
			} 
			catch (IOException g) {
			    serverOutput(strings.get(thisClass,
						     "closesocketerror") +
					 "\n");
			}
			System.exit(1);
		    }

		// Now, this might be an automatic reconnection attempt
		// by a client who was accidentally disconnected.  Check
		// to see whether we already have a client from this
		// InetAddress
		boolean reconnection = false;
		String tmpAddr = mySocket.getInetAddress().getHostAddress();
		synchronized (connections)
		    {
			for (int count = 0; count < connections.size();
			     count ++)
			    {
				babylonClientSocket tmpClient =
				    (babylonClientSocket)
				    connections.elementAt(count);
			    
				if (!tmpClient.online &&
				    tmpAddr.equals(tmpClient.mySocket
						   .getInetAddress()
						   .getHostAddress()))
				    {
					// The client already exists.  Just
					// give it the new socket and
					// wake it up
					acceptReconnect(tmpClient);
					reconnection = true;
					break;
				    }
			    }
		    }

		if (!reconnection)
		    // New connection
		    new babylonClientSocket(this, mySocket, myThreadGroup);
	    }
    }

    protected void acceptReconnect(babylonClientSocket client)
    {
	try {
	    client.mySocket.close();
	}
	catch (IOException e) {}

	client.mySocket = mySocket;

	// Get new input and output streams
	try {
	    client.istream =
		new DataInputStream(client.mySocket.getInputStream());
	    client.ostream =
		new DataOutputStream(client.mySocket.getOutputStream());
	    client.interrupt();
	} 
	catch (IOException a) {}
    }

    protected int getUserId()
    {
	// Returns a number to be used as a user Id
	
	int tmp;

	synchronized (userIdCounter)
	    {
		tmp = userIdCounter.intValue();
		userIdCounter = new Integer(tmp + 1);
	    }

	return (tmp);
    }

    protected void createNewUser(String userName, String encryptedPassword)
	throws Exception
    {
	// Create a new user acount.
	new babylonUserTool().createUser(userName, encryptedPassword);
    }

    protected void serverOutput(String message) 
    {
	if (graphics)
	    window.logWindow.append(message);
	else
	    System.out.print(message);

	// Write it to the log file
	if (log != null) 
	    {
		try {
		    byte[] messagebytes = message.getBytes();
		    log.write(messagebytes);
		} 
		catch (IOException F) {
		    if (graphics)
			window.logWindow
			    .append(strings.get(thisClass, "writelogerror") +
				    "\n");
		    else
			System.out.print(strings.get(thisClass,
						     "writelogerror") + "\n");
		}
	    }

	return;
    }

    protected void readMessages()
    {
	String tempFor = "";
	String tempFrom = "";
	String tempMessage = "";
	
	DataInputStream messageStream = null;

	try {
	    messageStream =
		new DataInputStream(new FileInputStream(messageFileName));

	    // Read entry by entry.
	    while(true)
		{
		    try {
			tempFor = messageStream.readUTF();
			tempFrom = messageStream.readUTF();
			tempMessage = messageStream.readUTF();
		    }
		    catch (EOFException e) {
			// Reached the end of the file
			break;
		    }
		    
		    messages.addElement(new babylonMessage(tempFor, tempFrom,
							   tempMessage));
		}

	    messageStream.close();
	}
	catch (IOException E) {}
	
	return;
    }

    protected void saveMessages()
    {
	DataOutputStream messageStream = null;

	try {
	    messageStream =
		new DataOutputStream(new FileOutputStream(messageFileName));
	    
	    for (int count = 0; count < messages.size(); count ++)
		{
		    babylonMessage tempMessage =
			(babylonMessage) messages.elementAt(count);
		    
		    messageStream.writeUTF(tempMessage.messageFor);
		    messageStream.writeUTF(tempMessage.messageFrom);
		    messageStream.writeUTF(tempMessage.text);
		}
	    
	    messageStream.close();
	} 
	catch (IOException E) {
	    serverOutput(strings.get(thisClass, "writemsgerror") + "\n"); 
	}

	return;
    }

    protected void shutdown()
    {
	serverOutput(strings.get(thisClass, "shutting") + "\n");

	if (currentConnections > 0)
	    {
		serverOutput(strings.get(thisClass, "disconnecting") + "\n");

		synchronized (connections)
		    {
			// Loop through all of the users, sending them the
			// message that the server is shutting down
			for (int count = 0; count < currentConnections;
			     count ++)
			    {
				babylonClientSocket who =
				    (babylonClientSocket)
				    connections.elementAt(count);
				
				try {
				    who.sendDisconnect(who.user.id,
					       who.strings.get(thisClass,
						       "shuttinggoodbye"));
				}
				catch (IOException e) {}
			    }
		    }

		// Make sure
		disconnectAll(true);
	    }
	else
	    serverOutput(strings.get(thisClass, "nousers") + "\n");

	// Print some stats
	serverOutput(strings.get(thisClass, "peakconn") + " " +
		     peakConnections + "\n");
	serverOutput(strings.get(thisClass, "totalconn") + " " +
		     totalConnections + "\n");

	serverOutput(strings.get(thisClass, "savingmsg") + "\n");
	saveMessages();

	serverOutput(strings.get(thisClass, "closinglog") + "\n");
	try {
	    log.close();
	} 
	catch (IOException F) { 
	    serverOutput(strings.get(thisClass, "closelogerror") + "\n"); 
	}

	if (graphics)
	    window.dispose();

	stop = true;

	// If we aren't using the GUI window, we should provide some
	// visual feedback that the server has terminated.
	if (!graphics)
	    {
		System.out.println("");
		System.out.println(strings.get(thisClass,
					       "shutdowncomplete"));
	    }

	// This function can be called by the babylonServerShutdown thread
	// when the server gets killed by an external signal.  If so, it
	// sets the externalShutdown flag, and we shouldn't call the
	// System.exit() function
	if (!externalShutdown)
	    System.exit(0);
    }

    private void usage()
    {
	System.out.println("\n" + strings.get(thisClass, "usage"));
	System.out.println("java babylonServer [" +
			   portnumberParam + " number] [" +
			   usepasswordsParam + "] [" +
			   newusersParam + "] [" +
			   nographicsParam + "] [" +
			   chatlogsParam + "] [" +
			   bugreportsParam + "]");
	return;
    }

    private boolean parseArgs(String[] args)
    {
	// Parse the arguments
	for (int count = 0; count < args.length; count ++)
	    {
		if (args[count].equals(portnumberParam))
		    {
			if (++count < args.length)
			    {
				try {
				    port = Integer.parseInt(args[count]);
				}
				catch (Exception E) {
				    System.out.println("\n" +
				       strings.get(thisClass, "illegalport") +
				       " " + args[count]);
				    System.out.println(strings
						       .get(thisClass,
							    "forusage"));
				    return (false);
				}			
			    }
		    }

		else if (args[count].equals(usepasswordsParam))
		    requirePasswords = true;

		else if (args[count].equals(newusersParam))
		    allowNewUsers = true;

		else if (args[count].equals(nographicsParam))
		    graphics = false;

		else if (args[count].equals(chatlogsParam))
		    logChats = true;

		else if (args[count].equals(bugreportsParam))
		    autoBugReports = true;

		else if (args[count].equals("-help"))
		    {
			usage();
			return (false);
		    }

		else
		    {
			System.out.println("\n" +
				   strings.get(thisClass, "unknownarg") +
				   " " + args[count]);
			System.out.println(strings.get(thisClass,
						       "forusage"));
			return (false);
		    }
	    }
	
	return (true);
    }

    public static void main(String[] args)
    {
	// Start the server
	new babylonServer(args);
//	new babylonServerShutdown(args);
	return;
    }
}
