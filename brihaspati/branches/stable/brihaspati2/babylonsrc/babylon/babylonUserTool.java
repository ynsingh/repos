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
//  babylonUserTool.java
//

import java.io.*;
import java.util.*;
import java.net.*;
import babylon.*;


public class babylonUserTool
{
    // This class creates/deletes user account entries in the password file.
    // It can be used from the command line, but it also gets invoked from the
    // server window.

    private static babylonStringManager strings = null; 
    private static Class thisClass = babylonUserTool.class;

    private Vector users = null;

    public babylonUserTool()
    {
	// Get a URL to describe the invocation directory
	try {
	    // Get the correct set of strings for our language
	    strings = new babylonStringManager(new URL("file", "localhost",
						       "./"),
					       Locale.getDefault()
					       .getLanguage());
	}
	catch (Exception E) {
	    System.out.println(E);
	    System.exit(1);
	}
	
	// Read the information from the current password file, if any.
	// This will fill up the 'users' Vector.
	readPasswordFile();
    }

    private void readPasswordFile()
    {
	// Read the whole password file, and turn all the entries into
	// babylonUser objects in the 'users' Vector
	
	DataInputStream passwordStream = null;
	users = new Vector();

	try {
	    passwordStream = new DataInputStream(new
		FileInputStream(babylonServer.userPasswordFileName));
	    
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
			break;
		    }

		    // Create the babylonUser object and append it to
		    // the vector
		    users.addElement(new babylonUser(0, tempUserName,
						     tempPassword, "", ""));
		}
	}
	catch (IOException e) {}
    }

    private void writePasswordFile()
	throws Exception
    {
	// Overwrite the password file.  Loop through the users Vector
	// and write each name to the file.

	DataOutputStream passwordStream = null;

	// Open up the password file
	try {
	    passwordStream =
		new DataOutputStream(new
		    FileOutputStream(babylonServer.userPasswordFileName));

	    for (int count = 0; count < users.size(); count ++)
		{
		    babylonUser tmpUser =
			(babylonUser) users.elementAt(count);

		    // Append this user to the end of the file
		    passwordStream.writeUTF(tmpUser.name);
		    passwordStream.writeUTF(tmpUser.password);
		}

	    passwordStream.close();
	}
	catch (IOException E) {
	    if (passwordStream != null)
		passwordStream.close();
	    throw new Exception(strings.get(thisClass,
					    "passwordfilewriteerror"));
	}
    }

    private void appendUser(String userName, String encryptedPassword)
	throws Exception
    {
	DataOutputStream passwordStream = null;

	// The password should already be encrypted
	
	// Open up the password file
	try {
	    passwordStream =
		new DataOutputStream(new
		    FileOutputStream(babylonServer.userPasswordFileName,
				     true));
	    // Append our stuff to the end of the file
	    passwordStream.writeUTF(userName);
	    passwordStream.writeUTF(encryptedPassword);
	    passwordStream.close();
	}
	catch (IOException E) {
	    if (passwordStream != null)
		passwordStream.close();
	    throw new Exception(strings.get(thisClass,
					    "passwordfilewriteerror"));
	}
    }

    public void createUser(String userName, String encryptedPassword)
	throws Exception
    {
	// We can't have empty user names or passwords
	if (userName.equals("") || encryptedPassword.equals(""))
	    throw new Exception(strings.get(thisClass, "emptyfield"));

	// Make sure that the user doesn't already exist
	for (int count = 0; count < users.size(); count ++)
	    {
		babylonUser user = (babylonUser) users.elementAt(count);
		if (user.name.equals(userName))
		    throw new Exception(strings.get(thisClass, "userexist"));
	    }

	// Add the user to our Vector
	users.addElement(new babylonUser(0, userName, encryptedPassword, "",
					 ""));
	
	// Append the new user to the password file
	appendUser(userName, encryptedPassword);

	return;
    }

    public void deleteUser(String userName)
	throws Exception
    {
	// Loop through the list of users, find the one in question,
	// remove it from our users Vector, and rewrite the password
	// file.
	
	babylonUser user = null;

	for (int count = 0; count < users.size(); count ++)
	    {
		babylonUser tmpUser = (babylonUser) users.elementAt(count);

		if (tmpUser.name.equals(userName))
		    {
			user = tmpUser;
			break;
		    }
	    }
	
	if (user == null)
	    // Not found
	    throw new Exception(strings.get(thisClass, "usernotexist"));

	// Get rid
	users.removeElement(user);

	// Write the password file again
	writePasswordFile();
    }

    public String[] listUsers()
	throws Exception
    {
	// Return a String array with all the user names

	String[] userList = new String[users.size()];

	for (int count = 0; count < users.size(); count ++)
	    userList[count] = ((babylonUser) users.elementAt(count)).name;
	
	return (userList);
    }

    private static void usage()
    {
	System.out.println("\n" + strings.get(thisClass, "usage"));
	System.out.println("java babylonUserTool -create <user name> "
			   + "<password>");
	System.out.println("                     -delete <user name>");
	System.out.println("                     -list");
	return;
    }

    public static void main(String[] args)
    {
	babylonUserTool tool;
	String userName = "";
	String password = "";

	// Create our user tool object
	tool = new babylonUserTool();

	// Parse the arguments
	for (int count = 0; count < args.length; count ++)
	    {
		if (args[count].equals("-create"))
		    {
			if (++count < args.length)
			    userName = args[count];
			if (++count < args.length)
			    password = args[count];

			// Encrypt the password
			password = new babylonPasswordEncryptor()
			    .encryptPassword(password);

			try {
			    // Create the user
			    tool.createUser(userName, password);
			}
			catch (Exception e) {
			    System.out.println(strings.get(thisClass,
							   "createerror") +
					       " " + e.toString());
			    System.exit(1);
			}

			// Success
			System.out.println(strings.get(thisClass,
						       "usercreated"));
			return;
		    }

		else if (args[count].equals("-delete"))
		    {
			if (++count < args.length)
			    userName = args[count];

			try {
			    // Delete the user
			    tool.deleteUser(userName);
			}
			catch (Exception e) {
			    System.out.println(strings.get(thisClass,
							   "deleteerror") +
					       " " + e.toString());
			    System.exit(1);
			}

			// Success
			System.out.println(strings.get(thisClass,
						       "userdeleted"));
			return;
		    }

		else if (args[count].equals("-list"))
		    {
			String[] userList = null;

			try {
			    userList = tool.listUsers();
			}
			catch (Exception e) {
			    System.out.println(strings.get(thisClass,
							   "listingerror") +
					       " " + e.toString());
			    System.exit(1);
			}

			// Print out the results
			for (int count2 = 0; count2 < userList.length; 
			     count2 ++)
			    System.out.print(userList[count2] + " ");
			System.out.println("");
		    }

		else if (args[count].equals("-help"))
		    {
			usage();
			System.exit(1);
		    }

		else
		    {
			System.out.println("\n" + strings.get(thisClass,
							      "unknownarg") +
					   " " + args[count]);
			System.out.println(strings
					   .get(thisClass, "forusage"));
			System.exit(1);
		    }
	    }

	return;
    }
}
