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
//  babylonClient.java
//

import java.applet.*;
import java.awt.*;
import java.io.*;
import java.net.*;
import java.util.*;
import babylon.*;
import javax.swing.JOptionPane;	
//import brandgang.tunnel.*;

public class babylonClient
    extends Thread
{
    protected Socket socket;
    protected DataInputStream istream;
    protected DataOutputStream ostream;
    protected boolean stop = false;
    protected boolean conformUser = false;
    protected double protocolVersion = 2.1; // by default
    protected babylonPanel mainPanel;
    protected Vector userList = new Vector();
    protected Vector dataList = new Vector();
    protected Vector roomList = new Vector();
    protected Vector ignoredUsers = new Vector();
    protected Vector vc = new Vector();
    	
    String output="";
    String data1="",from = "",filePath="";

    private Class thisClass = babylonClient.class;


    public babylonClient(String host, String name, int portnumber,
			 babylonPanel panel)
	throws UnknownHostException, IOException, Exception 
    {
	super("Babylon Chat client thread");

	mainPanel = panel;
	babylonApplet applet=new babylonApplet();
	
// set up the client socket
	socket = new Socket(host, portnumber); 
//commented by nks
//	int port=portnumber;
//	Socket socket=new Socket(host,port);
//	HtcSocket socket=new HtcSocket(applet.getAppletContext());
	// Get the output stream
	ostream = new DataOutputStream(socket.getOutputStream());

	// Get an input stream to correspond to the client socket
	istream = new DataInputStream(socket.getInputStream());

	synchronized (istream) {
	    synchronized (ostream) {
		// We will send our preferred protocol version to the server.
		// The server will respond with whatever version it wants us
		// to use (which will never be greater than the version
		// we've asked for)
		sendProtocol(protocolVersion);

		if (istream.readShort() == babylonCommand.SETPROTO)
		    receiveProtocol();
		else
		    {
			// Arhg.  Netscape 4.x for Windows seems to lose it
			// here after disconnecting/connecting a couple of
			// times.  This is a workaround.  Just drain the
			// stream without negotiating the protocol.  Put up
			// a dialog so that the user knows that their client
			// sucks.
			istream.readFully(new byte[istream.available()]);

			new babylonTextDialog(mainPanel.parentWindow,
			      mainPanel.strings.get(thisClass, "warning"),
			      mainPanel.strings.get(thisClass, "lostdata"),
			      60, 15, TextArea.SCROLLBARS_VERTICAL_ONLY,
			      false, mainPanel.strings.get("dismiss"));
		    }
	    }
	}

	// Start listening for stuff from the server
	start();

	// Now send the server some information about this user
	sendUserInfo();
    }

   public boolean conformUser()
   {
	// Checking whether client is authorized to run this client.
	String check="brij";
	if (check.equals("brij"))
		return (true);
	else
		return (false);
   }
   

    
    public void run()
    {
	while (!stop)
	    try {
		parseCommand();
		ostream.flush();
	    }
	    catch (EOFException a) {
		// This can happen occasionally, almost as if the
		// Java stream thinks something is available to be read
		// momentarily before it actually is... Just reset to
		// the marked position
		if (!stop)
		    {
			try {
			    istream.reset();
			    continue;
			}
			catch (IOException b) {
			    if (attemptReconnect())
				// Try to continue
				continue;
			    else
				{
				    // Failed
				    b.printStackTrace();
				    lostConnection();
				}
			}
		    }
		return;
	    }
	    catch (IOException c) {
		if (!stop)
		    {
			if (attemptReconnect())
			    // Try to continue
			    continue;
			else
			    {
				// Failed
				c.printStackTrace();
				lostConnection();
			    }
		    }
		return;
	    }
	    catch (Exception d) {
		// Any exception caught here is definitely a bug of some
		// sort.  If we are not running as an applet, make a
		// dialog box and ask for a bug report.
		new babylonBugReportDialog(mainPanel, d);

		// Eesh, try to ignore it if possible
		continue;
	    }

	return;
    }

    private boolean attemptReconnect()
    {
	System.out.println("Connection lost; attempting silent reconnect");

	try {
	    // Try to silently reconnect set up the client socket
	    synchronized (istream) {
		synchronized (ostream) {
		    socket = new Socket(mainPanel.host,
					mainPanel.portNumber);
		    ostream = new DataOutputStream(socket
						   .getOutputStream());
		    istream = new DataInputStream(socket
						  .getInputStream());
		}
	    }
	}
	catch (Exception ee) {
	    // No good
	    return (false);
	}

	// Lookin' good
	return (true);
    }

    private void parseCommand()
	throws IOException
    {
	// This routine figures out which command we are being sent, and 
	// dispatches it to the appropriate subroutine, below.

	short commandType = 0;

	synchronized (istream) {

	    // We need to do all input processing from within this
	    // 'synchronized' block, since we read the first part of the
	    // command here, followed by the rest in subroutines.

	    // Mark the current point so that we can return to it if
	    // necessary
	    istream.mark(1024);

	    // Try to read a short from the stream.  This indicates the
	    // command type
	    commandType = istream.readShort();

	    // Make sure that we didn't get stopped while we were blocking,
	    // waiting for that data
	    if (stop)
		return;

	    // Now that we have a command to read, we will call the
	    // appropriate client routine to interpret it.

	    if (protocolVersion >= 2.0)
		{
		    // The following commands are supported by protocol
		    // version 2.0

		    // The commands LEAVEMESS, READMESS, and EXIT are never
		    // sent from the server to the client (only vice-versa),
		    // so we don't need to worry about them here
			
		    switch (commandType) {
		    
		    case babylonCommand.NOOP:
			{
			    // Do nothing
			    break;
			}

		    case babylonCommand.PING:
			{
			    // The server is just sending us a ping.
			    break;
			}

		    case babylonCommand.CONNECT:
			{
			    // The server is telling us that a new user
			    // connected
			    receiveConnect();
			    break;
			}

		    case babylonCommand.USERINFO:
			{
			    // The server is sending info about a user
			    // (maybe us)
			    receiveUserInfo();
			    break;
			}

		    case babylonCommand.SERVERMESS:
			{
			    // The server is sending us a 'dialog box' message
			    receiveServerMess();
			    break;
			}

		    case babylonCommand.DISCONNECT:
			{
			    // Somebody is disconnecting.  Maybe us :)
			    receiveDisconnect();
			    break;
			}

		    case babylonCommand.ROOMLIST:
			{
			    // The server is supplying us with a list of
			    // current chat rooms
			    receiveRoomList();
			    break;
			}

		    case babylonCommand.INVITE:
			{
			    // Someone is inviting us to join a chat room
			    receiveInvite();
			    break;
			}

		    case babylonCommand.ENTERROOM:
			{
			    // Someone is entering a chat room
			    receiveEnterRoom();
			    break;		   
			}

		    case babylonCommand.BOOTUSER:
			{
			    // We have been booted from our chat room
			    receiveBootUser();
			    break;		   
			}

		    case babylonCommand.BANUSER:
			{
			    // We have been banned from our requested chat
			    // room
			    receiveBanUser();
			    break;		   
			}

		    case babylonCommand.ALLOWUSER:
			{
			    // Someone has allowed us into a chat room
			    receiveAllowUser();
			    break;		   
			}

		    case babylonCommand.ACTIVITY:
			{
			    // Someone is typing, drawing, etc.
			    receiveActivity();
			    break;
			}

		    case babylonCommand.CHATTEXT:
			{
			    // There's incoming chat text from another user
			    receiveChatText();
			    break;
			}

		    case babylonCommand.LINE:
			{
			    // Someone drew a line on the canvas
			    receiveLine();
			    break;
			}

		    case babylonCommand.ERASE:
			{
			    // Someone drew a line on the canvas
			    receiveErase();
			    break;
			}

		    case babylonCommand.RECT:
			{
			    // Someone drew a rectangle on the canvas
			    receivePoly(babylonCommand.RECT);
			    break;
			}

		    case babylonCommand.OVAL:
			{
			    // Someone drew an oval on the canvas
			    receivePoly(babylonCommand.OVAL);
			    break;
			}

		    case babylonCommand.DRAWTEXT:
			{
			    // Someone drew some text on the canvas
			    receiveDrawText();
			    break;
			}

		    case babylonCommand.DRAWPICTURE:
			{
			    // Someone pasted an image on the canvas
			    receiveDrawPicture();
			    break;
			}

		    case babylonCommand.CLEARCANV:
			{
			    // Someone cleared the canvas
			    receiveClearCanv();
			    break;
			}

		    case babylonCommand.PAGEUSER:
			{
			    // Someone is paging us
			    receivePageUser();
			    break;
			}

		    case babylonCommand.INSTANTMESS:
			{
			    // The server is sending us an instant message
			    receiveInstantMess();
			    break;
			}

		    case babylonCommand.STOREDMESS:
			{
			    // The server is sending one of our messgaes to us
			    receiveStoredMess();
			    break;
			}
		    case babylonCommand.SAVE:
			{
				//String data12=istream.readUTF();
                                //System.out.println("data12------------> "+data12);
				// The server is sending one of our messgaes to us
			  	//receiveSaveChat();
				//System.out.println("testing from Client");  
			break;
			}
		    case babylonCommand.PLAY: {
			/**
				String data12=istream.readUTF();
                                System.out.println("data12 FROM CLIENT --> "+data12);
				vc.addElement(data12);
			**/
				receivePlayRecorder();
				break;
			}
		    case babylonCommand.PLAYER: {
				dataToPlay();
			/**
				int line = vc.size();
				if(line >0){
	                                mainPanel.canvas.receivePlayRecorder(vc);
					vc.clear();
	       			        JOptionPane.showMessageDialog(null,"   END OF TEXT FILE "+"\n"+"   TOTAL LINES PLAYED  "+line);
				}
				else
      				        JOptionPane.showMessageDialog(null," FILE IS NOT EXIST OR FILE IS EMPTY SEE IN BABYLON ");
				line = 0;
			**/
                                break;
                        }

		    case babylonCommand.ERROR:
			{
			    // Someone is refusing a page from us
			    receiveError();
			    break;
			}

		    default:
			{
			    // Eek.  We don't understand this command

			    byte[] foo = new byte[istream.available()];
			    istream.readFully(foo);

			    // Since we don't understand whatever the server
			    // is sending to us, is it possible that we've
			    // tried to connect to a Babylon Chat v1.x server
			    // that doesn't speak our language?
			    String stringFoo = new String(foo);
			    if (stringFoo.startsWith("lcome to Babylon"))
				{
				    notifyObsoleteV1();
				    shutdown(false);
				    mainPanel.offline();
				    return;
				}

				System.out.println(mainPanel.strings
						   .get(thisClass,
							"unknowncommand") +
						   " " + commandType);
			    break;
			}
		    }
		}
	}
    }

    protected void notifyObsoleteV1()
    {
	// This function is used to notify the client that this server is
	// a Babylon Chat v1.x server, and can't accept a connection from
	// this client.
	new babylonInfoDialog(mainPanel.parentWindow,
			      mainPanel.strings.get(thisClass, "obsolete"),
			      true,
			      mainPanel.strings.get(thisClass,
						    "pleaseupgrade"),
			      mainPanel.strings.get("ok"));
    }

    protected babylonUser findUser(int userId)
    {
	// Find a user in the list

	babylonUser tmpUser = null;
	babylonUser returnUser = null;

	// Is it "nobody"?
	if (userId == 0)
	    return (null);

	// Go through the list of connected users, looking for one with the
	// correct id
	for (int count = 0; count < userList.size(); count ++)
	    {
		tmpUser = (babylonUser) userList.elementAt(count);

		if (tmpUser.id == userId)
		    {
			returnUser = tmpUser;
			break;
		    }
	    }

	return (returnUser);
    }

    protected babylonUser readUser()
	throws IOException
    {
	// This will read a user id from the input stream and return the
	// corresponding user object.  It will return null if the user id
	// is zero ("nobody") or if the user is not found in the list
	
	int userId = 0;

	// What's the user ID of the user?
	userId = istream.readInt();

	return (findUser(userId));
    }

    protected void sendRecipients()
	throws IOException
    {
	// This function will construct a recipient list and send it
	// down the pipe based on which users are selected in the 'send to'
	// list of the parent window

	String[] selectedUsers;
	int numberUsers = 0;

	// Is "send to all" selected?
	if (mainPanel.sendToAllCheckbox.getState())
	    {
		// Send an empty user list
		ostream.writeInt(0);
	    }
	else
	    {
		// Get the list of selected user names
		selectedUsers = mainPanel.sendToList.getSelectedItems();
	
		// How many are there?
		numberUsers = selectedUsers.length;

		// Write out how many
		ostream.writeInt(numberUsers);
			
		// Loop for each 
		for (int count1 = 0; count1 < numberUsers; count1 ++)
		    {
			// Find this user in our user list
			for (int count2 = 0; count2 < userList.size();
			     count2++)
			    {
				babylonUser tmp = (babylonUser)
				    userList.elementAt(count2);
					
				if (selectedUsers[count1].equals(tmp.name))
				    ostream.writeInt(tmp.id);
			    }
		    }
	    }
    }

    protected void addToIgnored(String name)
    {
	// Ignore a specific user

	// Is the user already being ignored?
	if (!isIgnoredUser(name))
	    ignoredUsers.addElement(name);

	// Output a message
	mainPanel.messagesArea.append("<<" + mainPanel.strings
				      .get(thisClass, "ignoreusers") + " ");
	for (int count = 0; count < ignoredUsers.size(); count ++)
	    {
		mainPanel.messagesArea
		    .append((String) ignoredUsers.elementAt(count));
		if (count < (ignoredUsers.size() - 1))
		    mainPanel.messagesArea.append(", ");
	    }
	mainPanel.messagesArea.append(">>\n");
    }

    protected void addToIgnored()
    {
	// This routine will add the selected users from the user list to
	// our list of ignored users

	String[] selectedUsers;
	int numberUsers = 0;

	// Get the list of selected user names
	selectedUsers = mainPanel.sendToList.getSelectedItems();
	
	// How many are there?
	numberUsers = selectedUsers.length;

	// Loop for each 
	for (int count1 = 0; count1 < numberUsers; count1 ++)
	    {
		// Find this user in our user list
		for (int count2 = 0; count2 < userList.size(); count2++)
		    {
			babylonUser tmp = (babylonUser)
			    userList.elementAt(count2);

			if (selectedUsers[count1].equals(tmp.name))
			    addToIgnored(tmp.name);
		    }
	    }
    }

    protected boolean isIgnoredUser(String who)
    {
	// This will return true if the supplied user name is in our list of
	// ignored users
	for (int count = 0; count < ignoredUsers.size(); count ++)
	    if (((String) ignoredUsers.elementAt(count)).equals(who))
		return (true);
	return (false);
    }


    // The following routines will send and receive the various command
    // types

    protected void sendProtocol(double version)
	throws IOException
    {
	// Send a message to the server about our desired protocol
	synchronized (ostream)
	    {
		ostream.writeShort(babylonCommand.SETPROTO);
		ostream.writeDouble(version);
	    }
    }

    protected void receiveProtocol()
	throws IOException
    {
	// The server is telling us which protocol to use

	// The client thread has already absorbed the 'command id'
	// from the stream
	protocolVersion = istream.readDouble();
    }

    protected void receiveConnect()
	throws IOException
    {
	// A new user has connected.  We only use this to output a message
	// saying that the user has connected; we get a USERINFO command
	// that will actually tell us about the user later
	
	String userName = istream.readUTF();

	mainPanel.messagesArea
	    .append("<<" + mainPanel.strings.get(thisClass, "newuser") +
		    " \"" + userName + "\" " +
		    mainPanel.strings.get(thisClass, "connected") + ">>\n");
    }

    protected void sendUserInfo()
	throws IOException
    {
	// Send the server some information about this user
	synchronized (ostream)
	    {
		ostream.writeShort(babylonCommand.USERINFO);
		ostream.writeInt(0); // We don't know our id
		ostream.writeUTF(mainPanel.name);
		ostream.writeUTF(mainPanel.encryptedPassword);
		ostream.writeBoolean(mainPanel.passwordEncryptor
				     .canEncrypt);
		ostream.writeUTF(mainPanel.additional);

		if (protocolVersion >= 2.1)
		    // Send our preferred language as well
		    ostream.writeUTF(mainPanel.strings.language);
	    }
    }

    protected void receiveUserInfo()
	throws IOException
    {
	int tmpId = 0;
	String tmpName = "";
	String tmpAdditional = "";
	String tmpLanguage = "en"; // English by default
	String tmpChatroomName = "";
	babylonUser newUser;

	// The server is sending new information about some user.
	tmpId = istream.readInt();
	tmpName = istream.readUTF();
	// Password field will be empty
	istream.readUTF();
	istream.readBoolean();
	tmpAdditional = istream.readUTF();

	if (protocolVersion >= 2.1)
	    // Read the user's preferred language.
	    tmpLanguage = istream.readUTF();

	// Is the user name ours?  If so, the server is telling us our
	// own user id number.
	if (tmpName.equals(mainPanel.name))
	    {
		mainPanel.clientId = tmpId;
	    }
	else
	    {
		// Some new user has connected.  Create a new user object
		newUser = new babylonUser(tmpId, tmpName, "", tmpAdditional,
					  tmpLanguage);

		// Add the user to our collection of users
		userList.addElement(newUser);
	    }
    }

    protected void receiveServerMess()
	throws IOException
    {
	String message = "";

	// The server is sending us a message.  Get the message.
	message = istream.readUTF();

	// Make a dialog box with the message
	
	new babylonInfoDialog(mainPanel.parentWindow,
			      mainPanel.strings.get(thisClass,
						    "servermessage"),
			      true, message,
			      mainPanel.strings.get("ok"));
    }

    protected void sendDisconnect()
	throws IOException
    {
	// Tell the server that we're disconnecting
	synchronized (ostream)
	    {
		ostream.writeShort(babylonCommand.DISCONNECT);
		ostream.writeInt(mainPanel.clientId);
		ostream.writeUTF("");
	    }
    }

    protected void receiveDisconnect()
	throws IOException
    {
	int tmpId = 0;
	babylonUser tmpUser;
	String disconnectMess = "";
	java.awt.List list = mainPanel.sendToList;

	tmpId = istream.readInt();
	disconnectMess = istream.readUTF();
	istream.readFully(new byte[istream.available()]);

	// Who is it?  Is it us?
	if ((tmpId == mainPanel.clientId) || (tmpId == 0))
	    {
		// If it's us, make a dialog box with the disconnection
		// message
	
		if (disconnectMess.equals(""))
		    disconnectMess =
			mainPanel.strings.get(thisClass, "noreason");

		new babylonInfoDialog(mainPanel.parentWindow,
				      mainPanel.strings.get(thisClass,
							    "disconnected"),
				      true, disconnectMess,
				      mainPanel.strings.get("ok"));

		shutdown(false);
		mainPanel.offline();
	    }
	else
	    {
		// Some other user disconnected.  Output a message that this
		// user has left the chat.

		tmpUser = findUser(tmpId);

		if (tmpUser == null)
		    // Don't know who this is.  Ignore.
		    return;

		mainPanel.messagesArea
		    .append("<<" + tmpUser.name + " " +
			    mainPanel.strings.get(thisClass,
						  "isdisconnecting")
			    + ">>\n");

		// Remove this name from our 'currently sending to' list
		synchronized (list)
		    {
			for (int count2 = 0; count2 < list.getItemCount();
			     count2 ++)
			    {
				if (list.getItem(count2).equals(tmpUser.name))
				    {
					if (list.isIndexSelected(count2))
					    list.select(0);
					list.remove(count2);
					break;
				    }
			    }
			
			// If there's nothing left in the list, make sure
			// the 'send to all' checkbox is checked
			if (list.getSelectedItems().length == 0)
			    mainPanel.sendToAllCheckbox.setState(true);
		    }

		// Remove this name from the user list of its chat room
		// info
		babylonRoomInfo roomInfo = null;

		if (mainPanel.roomInfoArray != null)
		    {
			for (int count2 = 0;
			     count2 < mainPanel.roomInfoArray.length;
			     count2 ++)
			    // Is this the user's chat room?
			    if (mainPanel.roomInfoArray[count2]
				.name.equals(tmpUser.chatroomName))
				{
				    roomInfo = mainPanel
					.roomInfoArray[count2];
				    break;
				}
			if (roomInfo != null)
			    {
				// Remove the user from the room's list
				// of users
				roomInfo.userNames
				    .removeElement(tmpUser.name);
				roomInfo.userNames.trimToSize();
			    }
		    }

		// Remove this name from our user list also
		userList.removeElement((Object) tmpUser);
		userList.trimToSize();

		// If there's a babylonRoomControlDialog, tell it to update
		// itself
		if (mainPanel.roomControlDialog != null)
		    mainPanel.roomControlDialog.updateLists();
	    }
    }

    protected void requestRoomList()
	throws IOException
    {
	// This will ask the server to send us a list of the current
	// chat rooms
	synchronized (ostream)
	    {
		ostream.writeShort(babylonCommand.ROOMLIST);
		ostream.writeShort(0); // We're asking for a list,
		// not supplying one
	    }
    }

    protected void receiveRoomList()
	throws IOException
    {
	int howManyRooms = 0;
	babylonRoomInfo[] roomList;
	babylonRoomInfo tmp = null;

	// This will receive the list of chat rooms from the server,
	// place them into an array (and supply the array to the
	// babylonroomsDialog, if one exists).

	// How many chat rooms are there?
	howManyRooms = istream.readShort();

	// Now that we know the number, we can create the array
	roomList = new babylonRoomInfo[howManyRooms];

	// Now loop and fill out the information in each babylonRoomInfo
	// structure
	for (int count1 = 0; count1 < howManyRooms; count1 ++)
	    {
		tmp = new babylonRoomInfo();

		tmp.name = istream.readUTF();
		tmp.creatorName = istream.readUTF();
		tmp.priv = istream.readBoolean();
		tmp.invited = istream.readBoolean();
		int numUsers = istream.readInt();

		// Fill out the user names array
		for (int count2 = 0; count2 < numUsers; count2 ++)
		    tmp.userNames.addElement(istream.readUTF());

		roomList[count1] = tmp;

		if (tmp.name.equals(mainPanel.currentRoom.name))
		    mainPanel.currentRoom = tmp;
	    }

	// Save the list of chat room info
	mainPanel.roomInfoArray = roomList;

	// Now notify the chat rooms dialog box, if it exists, so that
	// changes will be shown there.
	if (mainPanel.roomsDialog != null)
	    mainPanel.roomsDialog.receivedList();

	// If there's a babylonRoomControlDialog, tell it to update
	// itself
	if (mainPanel.roomControlDialog != null)
	    mainPanel.roomControlDialog.updateLists();
    }

    protected void sendInvite(int userId, String roomName)
	throws IOException
    {
	// Tell the server that we want to invite a user into our chat room
	synchronized (ostream)
	    {
		ostream.writeShort(babylonCommand.INVITE);
		ostream.writeInt(mainPanel.clientId);
		ostream.writeUTF(roomName);
		ostream.writeInt(userId);
	    }
    }

    protected void receiveInvite()
	throws IOException
    {
	babylonUser fromUser = null;
	String roomName = null;

	// This user has been invited to join a chat room

	// Who did it?
	fromUser = readUser();

	roomName = istream.readUTF();
	istream.readInt(); // Discard our user id

	if (fromUser == null)
	    // Ack.  No such user.  It can happen if someone logs out at
	    // just the right moment
	    return;

	if (isIgnoredUser(fromUser.name))
	    return;

	// Make a dialog box with a message so that the user knows that
	// they're invited to join
	new babylonInfoDialog(mainPanel.parentWindow,
			      mainPanel.strings.get(thisClass, "invitation"),
			      false, mainPanel.strings.get(thisClass,
							   "invitedtoroom") +
			      " \"" + roomName + "\" " +
			      mainPanel.strings.get(thisClass, "byuser") +
			      " " + fromUser.name,
			      mainPanel.strings.get("ok"));
    }

    protected void sendEnterRoom(String roomName, boolean priv,
				 String password)
	throws IOException
    {
	if (roomName.equals(""))
	    // Skip it, no such thing as a room with an empty name
	    return;

	// Tell the server that we want to join a different chat room
	synchronized (ostream)
	    {
		ostream.writeShort(babylonCommand.ENTERROOM);
		ostream.writeInt(mainPanel.clientId);
		ostream.writeUTF(roomName);
		ostream.writeBoolean(priv);
		ostream.writeUTF(password);
		ostream.writeBoolean(mainPanel.passwordEncryptor
				     .canEncrypt);
	    }
    }

    protected void receiveEnterRoom()
	throws IOException
    {
	int userId = 0;
	String newRoomName = "";
	babylonUser tmpUser;
	java.awt.List list = mainPanel.sendToList;

	// Some user (maybe us) has just entered a chat room.
	userId = istream.readInt();
	newRoomName = istream.readUTF();
	istream.readBoolean();
	istream.readUTF(); // Ignore empty password
	istream.readBoolean(); // Not encrypted
	
	// Who is the user?  If it's us, we need to set the name of our
	// chat room.  If it's another user, we need to set the name of
	// their chat room, and add them to our 'send to' list if they're
	// in the same room as us.

	if (userId == mainPanel.clientId)
	    {
		// It's us.
		if (!mainPanel.currentRoom.name.equals(""))
		    mainPanel.messagesArea
			.append("<<" +
				mainPanel.strings.get(thisClass, "entering") +
				" \"" + newRoomName + "\">>\n");
		
		// Clear out the 'send to' list
		if (mainPanel.sendToList.getItemCount() > 0)
		    mainPanel.sendToList.removeAll();
		mainPanel.sendToAllCheckbox.setState(true);

		// Clear the canvas
		mainPanel.canvas.clear();

		// Who else is currently in our new room?  Add them to our
		// 'send to' list
		for (int count1 = 0; count1 < userList.size(); count1 ++)
		    {
			tmpUser = (babylonUser) userList.elementAt(count1);

			if (tmpUser.chatroomName.equals(newRoomName))
			    mainPanel.sendToList.add(tmpUser.name);
		    }

		// Get the room info for the room we're entering.

		for (int count1 = 0;
		     count1 < mainPanel.roomInfoArray.length; count1 ++)
		    {
			babylonRoomInfo roomInfo =
			    mainPanel.roomInfoArray[count1];

			if (roomInfo.name.equals(newRoomName))
			    {
				// Found it
				mainPanel.currentRoom = roomInfo;
				break;
			    }
		    }

		// If we had a room-control dialog box open, we should
		// discard it.  However, we should also load another one
		// if we own the new chat room as well
		if (mainPanel.roomControlDialog != null)
		    {
			mainPanel.roomControlDialog.dispose();
			mainPanel.roomControlDialog = null;
		    }
		if (mainPanel.currentRoom.creatorName
		    .equals(mainPanel.name))
		    mainPanel.roomControlDialog =
			new babylonRoomControlDialog(mainPanel);

		return;
	    }

	// Loop through our user list and find the one with this id
	for (int count1 = 0; count1 < userList.size(); count1 ++)
	    {
		tmpUser = (babylonUser) userList.elementAt(count1);

		if (tmpUser.id != userId)
		    continue;

		// This is the one.  Was the user previously in our chat
		// room?  If so, we need to remove them from our 'currently
		// sending to' list
		String oldRoomName = tmpUser.chatroomName;
		if (oldRoomName.equals(mainPanel.currentRoom.name))
		    {
			if (!oldRoomName.equals(""))
			    mainPanel.messagesArea
				.append("<<" + tmpUser.name + " " +
					mainPanel.strings.get(thisClass,
							      "movedto") +
					" \"" + newRoomName + "\">>\n");
			
			// Remove this name from our 'currently sending to'
			// list
			synchronized (list) {
			    for (int count2 = 0; count2 < list.getItemCount();
				 count2 ++)
				{
				    if (list.getItem(count2)
					.equals(tmpUser.name))
					{
					    if (list.isIndexSelected(count2))
						list.select(0);
					    list.remove(count2);
					    break;
					}
				}
			}
		    }
		// Is the user entering our chat room?
		else if (newRoomName.equals(mainPanel.currentRoom.name))
		    {
			if (!oldRoomName.equals(""))
			    mainPanel.messagesArea
				.append("<<" + tmpUser.name + " " +
					mainPanel.strings.get(thisClass,
							      "entering") +
					">>\n");

			// Add this name to our 'currently sending to' list
			synchronized (list) {
			    list.add(tmpUser.name);
			}
		    }

		// Set the user's new room name.
		tmpUser.chatroomName = newRoomName;
		
		break;
	    }

	// If there's a babylonRoomControlDialog, get a room list update
	if (mainPanel.roomControlDialog != null)
	    requestRoomList();
    }

    protected void sendBootUser(int userId, String roomName)
	throws IOException
    {
	// Tell the server that we want to boot the user from the
	// current chat room
	synchronized (ostream)
	    {
		ostream.writeShort(babylonCommand.BOOTUSER);
		ostream.writeInt(mainPanel.clientId);
		ostream.writeUTF(roomName);
		ostream.writeInt(userId);
	    }
    }

    protected void receiveBootUser()
	throws IOException
    {
	babylonUser fromUser = null;
	String roomName = null;

	// This user is being booted from the current chat room

	// Who did it?
	fromUser = readUser();

	roomName = istream.readUTF();
	istream.readInt(); // Discard our user id

	// Dont't ignore this, even if the user that booted us is an
	// ignored user; an ignored user still has the right to boot us
	// from their chat room, and we should be notified if we get booted.

	if (fromUser == null)
	    // Ack.  No such user.  It can happen if someone logs out at
	    // just the right moment
	    return;

	// Make a dialog box with a message so that the user notices
	// they're a goner.
	new babylonInfoDialog(mainPanel.parentWindow,
			      mainPanel.strings.get(thisClass, "booted"), true,
			      mainPanel.strings.get(thisClass, "bootedfrom") +
			      " \"" + roomName + "\" " +
			      mainPanel.strings.get(thisClass, "byuser") +
			      " " + fromUser.name,
			      mainPanel.strings.get("ok"));
    }

    protected void sendBanUser(int userId, String roomName)
	throws IOException
    {
	// Tell the server that we want to ban the user from the
	// current chat room
	synchronized (ostream)
	    {
		ostream.writeShort(babylonCommand.BANUSER);
		ostream.writeInt(mainPanel.clientId);
		ostream.writeUTF(roomName);
		ostream.writeInt(userId);
	    }
    }

    protected void receiveBanUser()
	throws IOException
    {
	babylonUser fromUser = null;
	String roomName = null;

	// This user has been banned from the requested chat room

	// Who did it?
	fromUser = readUser();

	roomName = istream.readUTF();
	istream.readInt(); // Discard our user id

	// Dont't ignore this, even if the user that banned us is an
	// ignored user; an ignored user still has the right to ban us
	// from their chat room, and we should be notified if we get banned.

	// Make a dialog box with a message so that the user notices
	// they're a goner.
	new babylonInfoDialog(mainPanel.parentWindow,
			      mainPanel.strings.get(thisClass, "banned"), true,
			      mainPanel.strings.get(thisClass, "bannedfrom") +
			      " \"" + roomName + "\"",
			      mainPanel.strings.get("ok"));
    }

    protected void sendAllowUser(int userId, String roomName)
	throws IOException
    {
	// Tell the server that we want to un-ban the user from the
	// current chat room
	synchronized (ostream)
	    {
		ostream.writeShort(babylonCommand.ALLOWUSER);
		ostream.writeInt(mainPanel.clientId);
		ostream.writeUTF(roomName);
		ostream.writeInt(userId);
	    }
    }

    protected void receiveAllowUser()
	throws IOException
    {
	babylonUser fromUser = null;
	String roomName = null;

	// This user has been un-banned from a chat room

	// Who did it?
	fromUser = readUser();

	roomName = istream.readUTF();
	istream.readInt(); // Discard our user id

	if (fromUser == null)
	    // Ack.  No such user.  It can happen if someone logs out at
	    // just the right moment
	    return;

	if (isIgnoredUser(fromUser.name))
	    return;

	// Make a dialog box with a message so that the user knows that
	// they're allowed back in
	new babylonInfoDialog(mainPanel.parentWindow,
			      mainPanel.strings.get(thisClass, "allowed"),
			      true,
			      mainPanel.strings.get(thisClass, "allowedto") +
			      " \"" + roomName + "\"",
			      mainPanel.strings.get("ok"));
    }

    protected void sendActivity(short activity)
	throws IOException
    {
	// This tells our selected recipients that we're in the middle
	// of typing something.
	synchronized (ostream)
	    {
		ostream.writeShort(babylonCommand.ACTIVITY);
		ostream.writeInt(mainPanel.clientId);
		ostream.writeShort(activity);

		// Who's it for?  Send the recipient list
		sendRecipients();
	    }
    }

    protected void receiveActivity()
	throws IOException
    {
	// Some user is doing some activity, such as typing or drawing.

	babylonUser fromUser = null;
	short activity = 0;
	int numForUsers = 0;

	// Who is it?
	fromUser = readUser();

	// Read the activity and discard the recipient list, if there is one
	activity = istream.readShort();
	numForUsers = istream.readInt();
	for (int count = 0; count < numForUsers; count ++)
	    istream.readInt();
	
	if (fromUser == null)
	    // Ack.  No such user.  It can happen if someone logs out at
	    // just the right moment
	    return;

	// Are we ignoring this user?
	if (isIgnoredUser(fromUser.name))
	    return;

	// Set the appropriate message in the activity window
	String tmpString = "";
	if (activity == babylonCommand.ACTIVITY_DRAWING)
	    tmpString = mainPanel.strings.get(thisClass, "drawing") + " " +
		fromUser.name;
	else if (activity == babylonCommand.ACTIVITY_TYPING)
	    tmpString = mainPanel.strings.get(thisClass, "typing") + " " +
		fromUser.name;
	// Else what?  Ignore it.
	if (!mainPanel.activityField.getText().equals(tmpString))
	    mainPanel.activityField.setText(tmpString);
    }

    protected void sendChatText(String data)
	throws IOException
    {
	dataList.addElement("--"+Long.toString(System.currentTimeMillis())+"$15$"+data);
	// This sends a line of chat text to the selected recipients
	synchronized (ostream)
	    {
		ostream.writeShort(babylonCommand.CHATTEXT);
		ostream.writeInt(mainPanel.clientId);
		if (mainPanel.sendToAllCheckbox.getState())
		    // Public
		    ostream.writeBoolean(false);
		else
		    // Private
		   ostream.writeBoolean(true);
		   ostream.writeShort(0); // No colour
                   ostream.writeUTF(data);

		
		// Who's it for?  Send the recipient list
		   sendRecipients();
	    }
    }

    protected void receiveChatText()
	throws IOException
    {
	babylonUser fromUser = null;
	boolean priv = false;
	short colour = 0;
	String data = "";
	String output = "";
	int numForUsers = 0;

	// There is incoming chat text.

	// From whom is this message?
	fromUser = readUser();

	// Is this message private?  Also grab the colour value (unused),
	// the data itself, and the recipient list
	priv = istream.readBoolean();
	colour = istream.readShort();
	data = istream.readUTF();
	
	// Discard the recipient list, if there is one
	numForUsers = istream.readInt();
	for (int count = 0; count < numForUsers; count ++)
	    istream.readInt();
	
	if (fromUser != null)
	    {
		if (isIgnoredUser(fromUser.name))
		    return;

		// Precede the message with the name of the user that sent it
		// Is the message public or private?
		if (fromUser != null)
		    {
			if (priv)
			    output += "*" + mainPanel.strings
				.get(thisClass, "privatefrom") + " " +
				fromUser.name + "*: ";
			else
			    output += fromUser.name + ": ";
		    }
	    }

	// Append the actual message
	output += data;
	// Clear the 'activity' window
	mainPanel.activityField.setText("");
	// Put all the text in the chat window
	mainPanel.messagesArea.append(output);
    }

    protected void sendLine(short colour, short startx, short starty,
			    short endx, short endy, short thick)
	throws IOException
    {
	// This sends a graphic line to the selected recipients

	synchronized (ostream)
	    {
		ostream.writeShort(babylonCommand.LINE);
		ostream.writeInt(mainPanel.clientId);
		ostream.writeShort(colour);
		ostream.writeShort(startx);
		ostream.writeShort(starty);
		ostream.writeShort(endx);
		ostream.writeShort(endy);
		ostream.writeShort(thick);

		// Who's it for?  Send the recipient list
		sendRecipients();
	    }
	data1="$16$"+Short.toString(colour)+"!"+Short.toString(startx)+"!"+Short.toString(starty)+"!"+Short.toString(endx)+"!"+Short.toString(endy)+"!"+Short.toString(thick);
	dataList.addElement(Long.toString(System.currentTimeMillis())+data1);
    }

    protected void sendErase(short colour, short startx, short starty,
			    short endx, short endy, short thick)
	throws IOException
    {
	// This sends a erase work to the selected recipients

	synchronized (ostream)
	    {
		ostream.writeShort(babylonCommand.LINE);
		ostream.writeInt(mainPanel.clientId);
		ostream.writeShort(11);
		ostream.writeShort(startx);
		ostream.writeShort(starty);
		ostream.writeShort(endx);
		ostream.writeShort(endy);
		ostream.writeShort(8);

		// Who's it for?  Send the recipient list
		sendRecipients();
	    }
	data1="$28$"+Short.toString(colour)+"!"+Short.toString(startx)+"!"+Short.toString(starty)+"!"+Short.toString(endx)+"!"+Short.toString(endy)+"!"+Short.toString(thick);
	dataList.addElement(Long.toString(System.currentTimeMillis())+data1);
    }

    protected void receiveLine()
	throws IOException
    {
	// There is an incoming graphic line.

	babylonUser fromUser = null;
	short colournum = 0;
	short startx = 0;
	short starty = 0;
	short endx = 0;
	short endy = 0;
	short thick = 0;
	int numForUsers = 0;
	Color colour;

	// From whom is this message?
	fromUser = readUser();

	// Grab the colour value, x, y, and thickness, and the recipient list
	colournum = istream.readShort();
	startx = istream.readShort();
	starty = istream.readShort();
	endx = istream.readShort();
	endy = istream.readShort();
	thick = istream.readShort();

	// Discard the recipient list, if there is one
	numForUsers = istream.readInt();
	for (int count = 0; count < numForUsers; count ++)
	    istream.readInt();

	if (fromUser != null)
	    if (isIgnoredUser(fromUser.name))
		return;

	// Now draw the line
	colour = mainPanel.canvas.colourArray[colournum];
	mainPanel.canvas.drawLine(colour, startx, starty, endx, endy,
				     thick, babylonCanvas.MODE_PAINT);
    }

    protected void receiveErase()
	throws IOException
    {
	// There is an incoming erase.

	babylonUser fromUser = null;
	short colournum = 0;
	short startx = 0;
	short starty = 0;
	short endx = 0;
	short endy = 0;
	short thick = 0;
	int numForUsers = 0;
	Color colour;

	// From whom is this message?
	fromUser = readUser();

	// Grab the colour value, x, y, and thickness, and the recipient list
	colournum = istream.readShort();
	startx = istream.readShort();
	starty = istream.readShort();
	endx = istream.readShort();
	endy = istream.readShort();
	thick = istream.readShort();

	// Discard the recipient list, if there is one
	numForUsers = istream.readInt();
	for (int count = 0; count < numForUsers; count ++)
	    istream.readInt();

	if (fromUser != null)
	    if (isIgnoredUser(fromUser.name))
		return;

	// Now draw the line
	colour = mainPanel.canvas.colourArray[11];
	mainPanel.canvas.drawLine(colour, startx, starty, endx, endy,
				     8, babylonCanvas.MODE_PAINT);
    }

    protected void sendPoly(short colour, short x, short y, short width,
			    short height, short thick, boolean fill, int kind)
	throws IOException
    {
	// This sends a graphic polygon to the selected recipients.

	synchronized (ostream)
	    {
		ostream.writeShort(kind);
		ostream.writeInt(mainPanel.clientId);
		ostream.writeShort(colour);
		ostream.writeShort(x);
		ostream.writeShort(y);
		ostream.writeShort(width);
		ostream.writeShort(height);
		ostream.writeShort(thick);
		ostream.writeBoolean(fill);

		// Who's it for?  Send the recipient list
		sendRecipients();
	data1="$1$"+Short.toString(colour)+"!"+Short.toString(x)+"!"+Short.toString(y)+"!"+Short.toString(width)+"!"+Short.toString(height)+"!"+Short.toString(thick)+"!"+Boolean.toString(fill)+"!"+Integer.toString(kind);
	dataList.addElement(Long.toString(System.currentTimeMillis())+data1);
	    }
    }

    protected void receivePoly(short kind)
	throws IOException
    {
	// There is an incoming graphic polygon.

	babylonUser fromUser = null;
	short colournum = 0;
	short x = 0;
	short y = 0;
	short height = 0;
	short width = 0;
	short thick = 0;
	boolean fill = false;
	int numForUsers = 0;
	Color colour;

	// From whom is this message?
	fromUser = readUser();

	// Grab the colour value, x, y, width, height, thickness and fill,
	// and the recipient list
	colournum = istream.readShort();
	x = istream.readShort();
	y = istream.readShort();
	width = istream.readShort();
	height = istream.readShort();
	thick = istream.readShort();
	fill = istream.readBoolean();

	// Discard the recipient list, if there is one
	numForUsers = istream.readInt();
	for (int count = 0; count < numForUsers; count ++)
	    istream.readInt();

	if (fromUser != null)
	    if (isIgnoredUser(fromUser.name))
		return;

	// Get the colour
	colour = mainPanel.canvas.colourArray[colournum];

	// Now draw the shape
	if (kind == babylonCommand.RECT) {
	    mainPanel.canvas.drawRect(colour, x, y, width, height, fill,
					 thick, babylonCanvas.MODE_PAINT);
	  //  data1="$2$"+Short.toString(colournum)+"!"+Short.toString(x)+"!"+Short.toString(y)+"!"+Short.toString(width)+"!"+Short.toString(height)+"!"+Short.toString(thick)+"!"+Boolean.toString(fill)+"!"+Integer.toString(kind);
	}
	else if (kind == babylonCommand.OVAL) {
	    mainPanel.canvas.drawOval(colour, x, y, width, height, fill,
					 thick, babylonCanvas.MODE_PAINT);
	}
    }

    protected void sendDrawText(short colour, short x, short y, short type,
				short attribs, short size, String text)
	throws IOException
    {
	// This sends graphic text to the selected recipients.
	synchronized (ostream)
	    {
		ostream.writeShort(babylonCommand.DRAWTEXT);
		ostream.writeInt(mainPanel.clientId);
		ostream.writeShort(colour);
		ostream.writeShort(x);
		ostream.writeShort(y);
		ostream.writeShort(type);
		ostream.writeShort(attribs);
		ostream.writeShort(size);
		ostream.writeUTF(text);

		// Who's it for?  Send the recipient list
		sendRecipients();
	    }
	data1="$19$"+Short.toString(colour)+"!"+Short.toString(x)+"!"+Short.toString(y)+"!"+Short.toString(type)+"!"+Short.toString(attribs)+"!"+Short.toString(size)+"!"+text;
	dataList.addElement(Long.toString(System.currentTimeMillis())+data1);
    }

    protected void receiveDrawText()
	throws IOException
    {
	// There is an incoming graphic polygon.

	babylonUser fromUser = null;
	short colournum = 0;
	short x = 0;
	short y = 0;
	short type = 0;
	short size = 0;
	short attribs = 0;
	String text = "";
	int numForUsers = 0;
	Color colour;

	// From whom is this message?
	fromUser = readUser();

	// Grab the colour value, x, y, type, size, and attributes,
	// and the recipient list
	colournum = istream.readShort();
	x = istream.readShort();
	y = istream.readShort();
	type = istream.readShort();
	attribs = istream.readShort();
	size = istream.readShort();
	text = istream.readUTF();

	// Discard the recipient list, if there is one
	numForUsers = istream.readInt();
	for (int count = 0; count < numForUsers; count ++)
	    istream.readInt();

	if (fromUser != null)
	    if (isIgnoredUser(fromUser.name))
		return;

	// Get the colour
	colour = mainPanel.canvas.colourArray[colournum];

	// Now draw the text
	mainPanel.canvas.drawText(colour, x, y, type, attribs, size, text, 
				     mainPanel.canvas.MODE_PAINT);
    }

    protected void sendDrawPicture(short x, short y, File pictureFile)
	throws IOException
    {
	// This sends graphical images to the selected recipients.

	// How many bytes is the picture file?
	int fileLength = (int) pictureFile.length();;

	// Read the picture file into a byte array
	byte[] byteArray = new byte[fileLength];
	try {
	    FileInputStream fileStream = new FileInputStream(pictureFile);

	    if (fileStream.read(byteArray) < fileLength)
		{
		    new babylonInfoDialog(mainPanel.parentWindow,
					  mainPanel.strings
					  .get(thisClass, "error"),
					  true,
					  mainPanel.strings
					  .get(thisClass, "readpictureerror"),
					  mainPanel.strings.get("ok"));
		    return;
		}
	}
	catch (Exception e) {
	    e.printStackTrace();
	    return;
	}

	synchronized (ostream)
	    {
		ostream.writeShort(babylonCommand.DRAWPICTURE);
		ostream.writeInt(mainPanel.clientId);
		ostream.writeShort(x);
		ostream.writeShort(y);
		ostream.writeInt(fileLength);
		ostream.write(byteArray, 0, fileLength);

		// Who's it for?  Send the recipient list
		sendRecipients();
	    }
    }

    protected void receiveDrawPicture()
	throws IOException
    {
	// There is an incoming picture.

	babylonUser fromUser = null;
	short x = 0;
	short y = 0;
	int length = 0;
	byte[] data = null;
 	int numForUsers = 0;

	// From whom is this thing?
	fromUser = readUser();

	// Grab the x, y coordinates, the length, the image data,
	// and the recipient list
	x = istream.readShort();
	y = istream.readShort();
	length = istream.readInt();

	// We know how long the data is.  Make an array to hold
	// it, and read the data
	data = new byte[length];
	int read = 0;
	while (read < length)
	    read += istream.read(data, read, (length - read));

	// Discard the recipient list, if there is one
	numForUsers = istream.readInt();
	for (int count = 0; count < numForUsers; count ++)
	    istream.readInt();

	if (fromUser != null)
	    if (isIgnoredUser(fromUser.name))
		return;

	Image theImage = null;

	// Turn the raw image data into an image and draw it
	try {
	    theImage = mainPanel.getToolkit().createImage(data);
	    mainPanel.getToolkit().prepareImage(theImage, -1, -1,
						   mainPanel.canvas);
	    // We have to wait until the image is ready
	    while ((mainPanel.getToolkit().checkImage(theImage, -1, -1,
		 mainPanel.canvas) & mainPanel.canvas.ALLBITS) == 0)
		{}
	}
	catch (Exception e) {
	    e.printStackTrace();
	    return;
	}
	
	mainPanel.canvas.drawPicture(x, y, theImage);
    }

    protected void sendClearCanv()
	throws IOException
    {
	// Sends the 'clear canvas' signal to the specified users
	synchronized (ostream)
	    {
		ostream.writeShort(babylonCommand.CLEARCANV);
		ostream.writeInt(mainPanel.clientId);

		// Who's it for?  Send the recipient list
		sendRecipients();
	    }
    }
/////////////////////////

    protected void receiveSaveChat() throws IOException {
	synchronized (ostream) {
		if(dataList.size()>0) {
			boolean flag=false;
			System.out.println("sizeeeeeeeeeeeeeeeeeeeeeee--> "+dataList.size());
			for (int i=0;i<dataList.size();i++){
				String tempMessage = (String) dataList.get(i);
				if(!flag) {
					ostream.writeShort(babylonCommand.SAVE);
					String str="CreateFile"+mainPanel.groupName;
                        		ostream.writeUTF(str);
					flag=true;
				}
				ostream.writeShort(babylonCommand.SAVE);
                        	ostream.writeUTF(tempMessage);
                		System.out.println("Data save on Client side !! "+tempMessage);
			}
			dataList.clear();
                	//JOptionPane.showMessageDialog(null, " Save data to client side ");
			new babylonInfoDialog(mainPanel.parentWindow,
                              mainPanel.strings.get(thisClass, "savingData"),
                              true,
                              mainPanel.strings.get(thisClass,
                                                    "saveData"),
                              mainPanel.strings.get("ok"));
			
		}else {
                	//JOptionPane.showMessageDialog(null, "There is no data to Save like a Babylon chat and Canvas data ");
			new babylonInfoDialog(mainPanel.parentWindow,
                              mainPanel.strings.get(thisClass, "noDataSaving"),
                              true,
                              mainPanel.strings.get(thisClass,
                                                    "noDataToSave"),
                              mainPanel.strings.get("ok"));
                }
	    }
    }	
    protected void sendPlayRecorder(String filepath) throws IOException
    {
             synchronized(ostream){
		ostream.writeShort(babylonCommand.PLAY);
                ostream.writeUTF(filepath);
                System.out.println("file path "+filepath);
                System.out.println("testing from Client"+babylonCommand.PLAY);
	    }
    }
    protected void receivePlayRecorder() throws IOException
    {
	String data12=istream.readUTF();
	System.out.println("data12 FROM CLIENT --> "+data12);
        vc.addElement(data12);

    }
    protected void dataToPlay()
    {
	int line = vc.size();
	if(line >0){
               	mainPanel.canvas.receivePlayRecorder(vc);
                //JOptionPane.showMessageDialog(null,"   END OF TEXT FILE "+"\n"+"   TOTAL LINES PLAYED  "+line);
		new babylonInfoDialog(mainPanel.parentWindow,
                	mainPanel.strings.get(thisClass, "showingline"),
                        true,
                        mainPanel.strings.get(thisClass,
                                              "linePlayed")+line,
                        mainPanel.strings.get("ok"));
	}
        else
               	//JOptionPane.showMessageDialog(null," FILE IS NOT EXIST OR FILE IS EMPTY SEE IN BABYLON ");
		new babylonInfoDialog(mainPanel.parentWindow,
                	mainPanel.strings.get(thisClass, "fileNotExisting"),
                        true,
                        mainPanel.strings.get(thisClass,
                                              "fileNotExist"),
                        mainPanel.strings.get("ok"));
	line = 0;
	vc.clear();

    }


/////////////////////////
    protected void receiveClearCanv()
	throws IOException
    {
	babylonUser fromUser = null;
	int numForUsers = 0;

	// Someone has cleared the canvas

	// Who was it?
	fromUser = readUser();

	// Discard the recipient list, if there is one
	numForUsers = istream.readInt();
	for (int count = 0; count < numForUsers; count ++)
	    istream.readInt();
	
	if (fromUser == null)
	    // Ack.  No such user.  It can happen if someone logs out at
	    // just the right moment
	    return;

	if (isIgnoredUser(fromUser.name))
	    return;

	// Clear the canvas
	mainPanel.canvas.clear();	
	mainPanel.messagesArea
	    .append("<<" + fromUser.name + " " +
		    mainPanel.strings.get(thisClass, "clearedcanvas") +
		    ">>\n");
    }

    protected void sendPageUser()
	throws IOException
    {
	// Sends the paging signal to the specified users

	String[] selectedUsers;
	int numberUsers = 0;

	synchronized (ostream)
	    {
		ostream.writeShort(babylonCommand.PAGEUSER);
		ostream.writeInt(mainPanel.clientId);

		// Who's it for?  Send the recipient list
		sendRecipients();
	    }

	// Print a message to our own screen stating that the page has
	// been issued.

	// Is the first item selected?  If so, this means we're sending
	// to everybody
	if (mainPanel.sendToAllCheckbox.getState())
	    mainPanel.messagesArea
		.append("<<" + mainPanel.strings.get(thisClass, "pagingall") +
			">>\n");
	
	// Otherwise, list the users that have been paged
	else
	    {
		// Get the list of selected user names
		selectedUsers = mainPanel.sendToList.getSelectedItems();
	
		// How many are there?
		numberUsers = selectedUsers.length;

		if (numberUsers == 1)
		    mainPanel.messagesArea
			.append("<<" + mainPanel.strings.get(thisClass,
							     "paging") +
				" " + selectedUsers[0] + ">>\n");
		else
		    {
			mainPanel.messagesArea
			    .append("<<" + mainPanel.strings.get(thisClass,
								 "pagingsome"));
			// Loop for each 
			for (int count = 0; count < numberUsers; count ++)
			    mainPanel.messagesArea.append(" "
						 + selectedUsers[count]);
			mainPanel.messagesArea.append(">>\n");
		    }
	    }
    }

    protected void receivePageUser()
	throws IOException
    {
	babylonUser fromUser = null;
	int numForUsers = 0;

	// Someone is paging us

	// Who was it?
	fromUser = readUser();

	// Discard the recipient list, if there is one
	numForUsers = istream.readInt();
	for (int count = 0; count < numForUsers; count ++)
	    istream.readInt();
	
	if (fromUser == null)
	    // Ack.  No such user.  It can happen if someone logs out at
	    // just the right moment
	    return;

	if (isIgnoredUser(fromUser.name))
	    return;

	// Write a message to the conference area so that the user can
	// see he's been paged
	mainPanel.messagesArea.append("<<" + fromUser.name + " " +
				      mainPanel.strings.get(thisClass,
							    "pagingyou") +
				      ">>\n");
		
	// If the user has not turned the paging feature off, play
	// the paging sound.  Otherwise, return a message stating that
	// the page has been refused.
	if (mainPanel.playSound)
	    {
		// If this Java version is less than 1.2, we can't play
		// this sound
		double javaVersion = 0.0;
		try {
		    javaVersion =
			new Double(System.getProperty("java.version")
				   .substring(0, 3)).doubleValue();
		}
		catch (NumberFormatException e) {}

		if (javaVersion < 1.2)
		    sendError(fromUser.id, babylonCommand.ERROR_NOSOUND);
		else
		    {
			// Try to play the paging sound
			try {
			    URL soundURL =
				new URL(mainPanel.babylonURL.getProtocol(),
					mainPanel.babylonURL.getHost(),
					mainPanel.babylonURL.getFile() +
					"babylonPage.au");
			    Applet.newAudioClip(soundURL).play();
			}
			catch (Exception argh) {
			    // This client can't play the paging sound.

			    // Try beeps instead
			    for (int count = 0; count < 3; count ++)
				mainPanel.getToolkit().beep();

			    // Send a message
			    sendError(fromUser.id,
				      babylonCommand.ERROR_NOSOUND);
			}
		    }
	    }
	else
	    // The user is not accepting pages.  Send a message.
	    sendError(fromUser.id, babylonCommand.ERROR_NOPAGE);
    }

    protected void sendError(int toWhom, short code)
	throws IOException
    {
	// Sends the requested advisory to the specified user
	synchronized (ostream)
	    {
		ostream.writeShort(babylonCommand.ERROR);
		ostream.writeInt(mainPanel.clientId);
		ostream.writeShort(code);
		ostream.writeInt(1); // 1 recipient
		ostream.writeInt(toWhom);
	    }
    }

    public void sendInstantMess(int whoFor, String message)
	throws IOException
    {
	// Sends an instant message to the server for the specified user
	synchronized (ostream)
	    {
		ostream.writeShort(babylonCommand.INSTANTMESS);
		ostream.writeInt(mainPanel.clientId);
		ostream.writeInt(whoFor);
		ostream.writeUTF(message);
	    }
    }

    public void receiveInstantMess()
	throws IOException
    {
	babylonUser fromUser = null;
	String message = "";
			
	// From whom is this message?
	fromUser = readUser();

	// What's the message?
	istream.readInt(); // Our id
	message = istream.readUTF();

	if (fromUser == null)
	    // Ack.  No such user.  It can happen if someone logs out at
	    // just the right moment
	    return;

	if (isIgnoredUser(fromUser.name))
	    return;

	// Is there an existing instant messaging dialog?
	for (int count = 0; count < mainPanel.instantMessageDialogs.size();
	     count ++)
	    {
		babylonInstantMessageDialog tmp =
		    (babylonInstantMessageDialog) mainPanel
		    .instantMessageDialogs.elementAt(count);

		if (tmp.fromUser == fromUser)
		    {
			// There's already a dialog showing.  Just send
			// the additional message to it.
			tmp.addMessage(message);
			return;
		    }
	    }

	// Place it on the screen as a dialog box
	mainPanel.instantMessageDialogs
	    .addElement(new babylonInstantMessageDialog(mainPanel, fromUser,
							message));

	return;
    }

    public void sendLeaveMess(String whofor, String message)
	throws IOException
    {
	// Sends our message to the server for the specified user
	synchronized (ostream)
	    {
		ostream.writeShort(babylonCommand.LEAVEMESS);
		ostream.writeInt(mainPanel.clientId);
		ostream.writeUTF(whofor);
		ostream.writeUTF(message);
	    }
    }

    public void sendReadMess()
	throws IOException
    {
	// Sends our message to the server for the specified user
	synchronized (ostream)
	    {
		ostream.writeShort(babylonCommand.READMESS);
		ostream.writeInt(mainPanel.clientId);
	    }
    }

    public void receiveStoredMess()
	throws IOException
    {
	short numberMessages = 0;
	//String from = "";
	String message = "";
			
	// Read the command header
	numberMessages = istream.readShort();

	if (numberMessages == 0)
	    {
		new babylonInfoDialog(mainPanel.parentWindow,
				      mainPanel.strings.get(thisClass,
							    "none"), true,
				      mainPanel.strings.get(thisClass,
							    "nounread"),
				      mainPanel.strings.get("ok")); 
		return;
	    }

	// Loop for each message
	for (int count = 0; count < numberMessages; count ++)
	    {
		// What's the message?
		from = istream.readUTF();
		message = istream.readUTF();

		// Place it on the screen as a dialog box
		new babylonTextDialog(mainPanel.parentWindow, 
				      mainPanel.strings.get(thisClass,
							    "messagefrom") +
				      " " + from, message, 40, 10,
				      TextArea.SCROLLBARS_VERTICAL_ONLY,
				      true, mainPanel.strings.get("dismiss"));
	    }

	// Tell the user that that's all
	new babylonInfoDialog(mainPanel.parentWindow,
			      mainPanel.strings.get(thisClass, "done"), true,
			      mainPanel.strings.get(thisClass,
						    "endmessages"),
			      mainPanel.strings.get("ok")); 
	return;
    }

    protected void receiveError()
	throws IOException
    {
	// Another user has sent an error code to us

	babylonUser fromUser = null;
	short errorCode = 0;
	int numForUsers = 0;

	// Who was it?
	fromUser = readUser();

	// What was the error code?
	errorCode = istream.readShort();

	// Discard the recipient list, if there is one
	numForUsers = istream.readInt();
	for (int count = 0; count < numForUsers; count ++)
	    istream.readInt();
	
	if (fromUser == null)
	    // Ack.  No such user.  It can happen if someone logs out at
	    // just the right moment
	    return;

	if (isIgnoredUser(fromUser.name))
	    return;

	// Now do different things depending on the advisory type
	switch(errorCode)
	    {
	    case babylonCommand.ERROR_NOPAGE:
		{
		    // The user is not accepting our page
		    mainPanel.messagesArea
			.append("<<" + fromUser.name + " " +
				mainPanel.strings.get(thisClass, "nopages") +
				">>\n");
		    break;
		}
	    case babylonCommand.ERROR_NOSOUND:
		{
		    // This client can't play sounds
		    mainPanel.messagesArea
			.append("<<" + fromUser.name + " " +
				mainPanel.strings.get(thisClass, "nosound") +
				">>\n");
		    break;
		}
	    default:
		{
		    // Or else what?
		    mainPanel.messagesArea
			.append("<<" + mainPanel.strings.get(thisClass,
							     "unknownerror") +
				" " + fromUser.name + ">>\n");
		    break;
		}
	    }
    }

    public void lostConnection()
    {
	if (!stop)
	    {
		// Try to reconnect silently
				

		shutdown(true);
		mainPanel.offline();
	    }
	return;
    }

    public synchronized void shutdown(boolean notifyUser)
    {
	// Shut down the reader thread
	stop = true;

	// Close my input and output data streams.  Don't do this
	// synchronized, since the client reader will be sitting there
	// blocking, waiting for data.
	try {
	    istream.close();
	}
	catch (IOException e) {}
	
	try {
	    synchronized (ostream)
		{
		    ostream.flush();
		    ostream.close();
		}

	    // close up my socket
	    socket.close();
	}
	catch (IOException e) {}

	// Empty out our user list
	userList.removeAllElements();

	// No more chat room
	mainPanel.currentRoom = null;

	// Get rid of any 'chat room list' or 'chat room control'
	// or instant message dialog boxes that might be hanging
	// around
	if (mainPanel.roomsDialog != null)
	    {
		mainPanel.roomsDialog.dispose();
		mainPanel.roomsDialog = null;
	    }
	if (mainPanel.roomControlDialog != null)
	    {
		mainPanel.roomControlDialog.dispose();
		mainPanel.roomControlDialog = null;
	    }
	for (int count = 0; count < mainPanel.instantMessageDialogs.size();
	     count ++)
	    {
		babylonInstantMessageDialog tmp =
		    (babylonInstantMessageDialog)
		    mainPanel.instantMessageDialogs.elementAt(count);
		tmp.dispose();
	    }
	mainPanel.instantMessageDialogs.removeAllElements();

	if (notifyUser)
	    // Make a message to the user
	    new babylonInfoDialog(mainPanel.parentWindow,
				  mainPanel.strings.get(thisClass,
							"disconnected"), true,
				  mainPanel.strings.get(thisClass,
							"disconnectedfrom") +
				  " " + mainPanel.host,
				  mainPanel.strings.get("ok"));

	// Force garbage collection, since some clients seem to hold on
	// to the connection somehow
	System.gc();

	return;
    }
}
