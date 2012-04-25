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
//  babylonChatRoom.java
//

import java.util.*;
import java.io.*;
import babylon.*;


public class babylonChatRoom
{
    // This class is used to manage chat rooms.  It's defined in its own
    // source file because it is used by both the babylonserver and
    // babylonClientSocket classes

    public String name = "";
    public String creatorName = "";
    public boolean priv;
    public String password = "";
    public Vector clients;
    public Vector canvasHistory;
    public Vector invitedUsers;
    public Vector bannedUserNames;
   
    private FileOutputStream chatLogStream;

    babylonChatRoom(String myName, String myCreator, boolean isPrivate,
		    String myPassword)
    {
	name = myName;
	creatorName = myCreator;
	priv = isPrivate;
	if (password != null)
	    password = myPassword;
	clients = new Vector();
	canvasHistory = new Vector();
	if (priv)
	    invitedUsers = new Vector();
	bannedUserNames = new Vector();
    }

    public void addClient(babylonClientSocket newClient)
    {
	// Adds a client to this chatroom
	clients.addElement(newClient);

	// Put a reference to this chatroom in the user object
	newClient.user.chatroom = this;
    }

    public void remClient(babylonClientSocket oldClient)
    {
	// Removes a client from this chatroom
	clients.removeElement(oldClient);
	clients.trimToSize();
    }

    public void inviteUser(int userId)
    {
	// We only keep a vector of invited users if this is a private room
	if (priv)
	    {
		Integer Id = new Integer(userId);
		invitedUsers.addElement(Id);
	    }
    }

    public void uninviteUser(int userId)
    {
	// Remove the user id from the list of invited users

	// We only keep a vector of invited users if this is a private room
	if (priv)
	    {
		for (int count = 0; count < invitedUsers.size(); count ++)
		    {
			Integer Id = new Integer(userId);

			if ((Integer) invitedUsers.elementAt(count) == Id)
			    {
				invitedUsers.removeElement(invitedUsers
							   .elementAt(count));
				invitedUsers.trimToSize();
				break;
			    }
		    }
	    }
    }

    public void banUserName(String userName)
    {
	bannedUserNames.addElement(userName);
    }

    public void allowUserName(String userName)
    {
	// Remove the user name from the list of banned users

	for (int count = 0; count < bannedUserNames.size(); count ++)
	    {
		if (bannedUserNames.elementAt(count).equals(userName))
		    {
			bannedUserNames.removeElement(bannedUserNames
						      .elementAt(count));
			bannedUserNames.trimToSize();
			break;
		    }
	    }
    }

    public void setLogging(boolean onOff)
	throws IOException
    {
	// Turn logging on or off for this chat room
	
	if (onOff)
	    {
		// open up the log file
		File chatLogFile = new File(name + ".chatlog");
		chatLogStream = new FileOutputStream(chatLogFile);
	    }
	else
	    {
		// Close any existing log file
		if (chatLogStream != null)
		    {
			chatLogStream.close();
			chatLogStream = null;
		    }
	    }
			
    }

    protected void log(String logText)
	throws IOException
    {
	if (chatLogStream != null)
	    {
		try {
		    synchronized(chatLogStream)
			{
			    byte[] bytes = logText.getBytes();
			    chatLogStream.write(bytes);
			}
		}
		catch (IOException e) {
		    chatLogStream.close();
		    chatLogStream = null;
		    throw(e);
		}
	    }
    }

    protected void finalize()
    {
	// The chat room is disappearing.  Close the log file.
	try {
	    chatLogStream.close();
	}
	catch (IOException e) {}
    }
}
