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
//  babylonRoomControlDialog.java
//

// This file contains the code for the dialog that pops up when a user
// "owns" a chat room, so that they can control the room and the users in
// it.

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
import babylon.*;


public class babylonRoomControlDialog
    extends Dialog
    implements ActionListener, ItemListener, KeyListener, WindowListener
{
    protected babylonPanel mainPanel;
    private Class thisClass = babylonRoomControlDialog.class;

    protected GridBagLayout myLayout;

    // Widgets
    protected Panel p;
    protected Label roomNameLabel;
    protected Label roomUsersLabel;
    protected java.awt.List roomUsersList;
    protected Label allUsersLabel;
    protected java.awt.List allUsersList;
    protected Button inviteUsers;
    protected Button bootUsers;
    protected Button banUsers;
    protected Button allowUsers;
    protected Button dismiss;


    babylonRoomControlDialog(babylonPanel panel)
    {
	super(panel.parentWindow,
	      panel.strings.get(babylonRoomControlDialog.class,
				"roomcontrol"),
	      false);

	mainPanel = panel;

	if (mainPanel.roomControlDialog != null)
	    {
		mainPanel.roomControlDialog.dispose();
		mainPanel.roomControlDialog = null;
	    }

	// Make all of the widgets

	myLayout = new GridBagLayout();
	setLayout(myLayout);

	p = new Panel();
	p.setLayout(myLayout);
	
	roomNameLabel = new Label(mainPanel.currentRoom.name);
	roomNameLabel.setFont(mainPanel.XlargeFont);
	p.add(roomNameLabel,
	      new babylonConstraints(0, 0, 1, 1, 1.0, 1.0,
				     GridBagConstraints.NORTHWEST,
				     GridBagConstraints.NONE,
				     new Insets(0, 0, 0, 0),
				     0, 0));

	roomUsersLabel =
	    new Label(mainPanel.strings.get(thisClass, "usersinroom"));
	roomUsersLabel.setFont(babylonPanel.smallFont);
	p.add(roomUsersLabel,
	      new babylonConstraints(0, 1, 1, 1, 1.0, 1.0,
				     GridBagConstraints.NORTHWEST,
				     GridBagConstraints.NONE,
				     new Insets(0, 0, 0, 0),
				     0, 0));

	roomUsersList = new java.awt.List(5);
	roomUsersList.setFont(babylonPanel.smallFont);
	roomUsersList.addItemListener(this);
	roomUsersList.addKeyListener(this);
	roomUsersList.setMultipleMode(true);
	p.add(roomUsersList,
	      new babylonConstraints(0, 2, 1, 1, 1.0, 1.0,
				     GridBagConstraints.NORTHWEST,
				     GridBagConstraints.BOTH,
				     new Insets(0, 0, 0, 0),
				     0, 0));

	bootUsers = new Button(mainPanel.strings.get(thisClass, "bootusers"));
	bootUsers.setFont(babylonPanel.smallFont);
	bootUsers.addActionListener(this);
	bootUsers.addKeyListener(this);
	bootUsers.setEnabled(false);
	p.add(bootUsers,
	      new babylonConstraints(0, 3, 1, 1, 1.0, 1.0,
				     GridBagConstraints.NORTHWEST,
				     GridBagConstraints.BOTH,
				     new Insets(0, 0, 0, 0),
				     0, 0));

	allUsersLabel =
	    new Label(mainPanel.strings.get(thisClass, "otherusers"));
	allUsersLabel.setFont(babylonPanel.smallFont);
	p.add(allUsersLabel,
	      new babylonConstraints(0, 4, 1, 1, 1.0, 1.0,
				     GridBagConstraints.NORTHWEST,
				     GridBagConstraints.NONE,
				     new Insets(0, 0, 0, 0),
				     0, 0));

	allUsersList = new java.awt.List(10);
	allUsersList.setFont(babylonPanel.smallFont);
	allUsersList.addItemListener(this);
	allUsersList.addKeyListener(this);
	allUsersList.setMultipleMode(true);
	p.add(allUsersList,
	      new babylonConstraints(0, 5, 1, 1, 1.0, 1.0,
				     GridBagConstraints.NORTHWEST,
				     GridBagConstraints.BOTH,
				     new Insets(0, 0, 0, 0),
				     0, 0));

	inviteUsers =
	    new Button(mainPanel.strings.get(thisClass, "inviteusers"));
	inviteUsers.setFont(babylonPanel.smallFont);
	inviteUsers.addActionListener(this);
	inviteUsers.addKeyListener(this);
	inviteUsers.setEnabled(false);
	p.add(inviteUsers,
	      new babylonConstraints(0, 6, 1, 1, 1.0, 1.0,
				     GridBagConstraints.NORTHWEST,
				     GridBagConstraints.BOTH,
				     new Insets(0, 0, 0, 0),
				     0, 0));

	banUsers = new Button(mainPanel.strings.get(thisClass, "banusers"));
	banUsers.setFont(babylonPanel.smallFont);
	banUsers.addActionListener(this);
	banUsers.addKeyListener(this);
	banUsers.setEnabled(false);
	p.add(banUsers,
	      new babylonConstraints(0, 7, 1, 1, 1.0, 1.0,
				     GridBagConstraints.NORTHWEST,
				     GridBagConstraints.BOTH,
				     new Insets(0, 0, 0, 0),
				     0, 0));

	allowUsers =
	    new Button(mainPanel.strings.get(thisClass, "allowusers"));
	allowUsers.setFont(babylonPanel.smallFont);
	allowUsers.addActionListener(this);
	allowUsers.addKeyListener(this);
	allowUsers.setEnabled(false);
	p.add(allowUsers,
	      new babylonConstraints(0, 8, 1, 1, 1.0, 1.0,
				     GridBagConstraints.NORTHWEST,
				     GridBagConstraints.BOTH,
				     new Insets(0, 0, 0, 0),
				     0, 0));

	dismiss = new Button(mainPanel.strings.get("dismiss"));
	dismiss.setFont(babylonPanel.smallFont);
	dismiss.addActionListener(this);
	dismiss.addKeyListener(this);
	dismiss.setEnabled(true);
	p.add(dismiss,
	      new babylonConstraints(0, 9, 1, 1, 1.0, 1.0,
				     GridBagConstraints.NORTHWEST,
				     GridBagConstraints.BOTH,
				     new Insets(0, 0, 0, 0),
				     0, 0));

	add(p, new babylonConstraints(0, 0, 1, 1, 1.0, 1.0,
				     GridBagConstraints.NORTHWEST,
				     GridBagConstraints.BOTH,
				     new Insets(5, 5, 5, 5),
				     0, 0));

	// register to receive the various events
	addKeyListener(this);
	addWindowListener(this);

	// show the window and get going
	setSize(200, 200);
	pack();
	
	if ((mainPanel.thisApplet != null) &&
	    (mainPanel.thisApplet.embed))
	    babylonPanel.centerDialogOnScreen(this);
	else
	    setLocation(mainPanel.getLocation().x,
			mainPanel.getLocation().y);

	setVisible(true);
	inviteUsers.requestFocus();

	// Ask the server for information about the chat rooms, etc.  This
	// will cause our updateLists function, below, to fill up our lists.
	if (mainPanel.client != null)
	    try {
		mainPanel.client.requestRoomList();
	    }
	    catch (IOException e) {
		mainPanel.client.lostConnection();
		return;
	    }

	return;
    }

    private boolean userInRoom(String userName, babylonRoomInfo room)
    {
	for (int count = 0; count < room.userNames.size(); count ++)
	    {
		if (((String) room.userNames.elementAt(count))
		    .equals(userName))
		    return (true);
	    }

	return (false);
    }

    public void updateLists()
    {
	// Update our lists.  This is called when something changes, such
	// as a user logs on/off, or a user leaves/joins our chat room
	
	if (roomUsersList.getItemCount() > 0)
	    roomUsersList.removeAll();
	if (allUsersList.getItemCount() > 0)
	    allUsersList.removeAll();

	babylonRoomInfo currentRoom = mainPanel.currentRoom;

	// Add all of the users from this chat room to the 'chat room users'
	// list.
	for (int count = 0; count < currentRoom.userNames.size(); count ++)
	    roomUsersList.add((String)
			      currentRoom.userNames.elementAt(count));

	Vector usersVector = mainPanel.client.userList;

	// Add all of the connected users to the 'all users' list
	for (int count = 0; count < usersVector.size(); count ++)
	    {
		babylonUser user = (babylonUser) usersVector.elementAt(count);

		if (!userInRoom(user.name, currentRoom))
		    allUsersList.add(user.name);
	    }

	bootUsers.setEnabled(false);
	inviteUsers.setEnabled(false);
	banUsers.setEnabled(false);
	allowUsers.setEnabled(false);
	
	return;
    }

    protected void goBootUsers()
    {
	// This gets called when the room owner wants to boot another
	// user (or users) from the chat room.
	
	String[] selectedUsers;

	// Get the names of all selected users
	selectedUsers = roomUsersList.getSelectedItems();

	// For each selected user name, we will loop through our list of
	// connected users and send a BOOTUSER command to the server

	Vector usersVector = mainPanel.client.userList;

	for (int count1 = 0; count1 < selectedUsers.length; count1 ++)
	    {
		// We've got one of the selected users; now find the user
		// data structure so we can get the user id
		for (int count2 = 0; count2 < usersVector.size(); count2 ++)
		    {
			babylonUser user = (babylonUser)
			    usersVector.elementAt(count2);

			if (user.name.equals(selectedUsers[count1]))
			    try {
				// Send the command
				mainPanel.client.sendBootUser(user.id,
					    mainPanel.currentRoom.name);
			    }
			    catch (IOException e) {
				mainPanel.client.lostConnection();
				return;
			    }
		    }
	    }      

	// Refresh the user lists
	updateLists();
    }

    protected void goInviteUsers()
    {
	// This gets called when the room owner wants to invite another
	// user (or users) into a chat room.
	
	String[] selectedUsers;

	// Get the names of all selected users
	selectedUsers = allUsersList.getSelectedItems();

	// For each selected user name, we will loop through our list of
	// connected users and send a INVITE command to the server

	Vector usersVector = mainPanel.client.userList;

	for (int count1 = 0; count1 < selectedUsers.length; count1 ++)
	    {
		// We've got one of the selected users; now find the user
		// data structure so we can get the user id
		for (int count2 = 0; count2 < usersVector.size(); count2 ++)
		    {
			babylonUser user = (babylonUser)
			    usersVector.elementAt(count2);

			if (user.name.equals(selectedUsers[count1]))
			    try {
				// Send the command
				mainPanel.client.sendInvite(user.id,
					  mainPanel.currentRoom.name);
			    }
			    catch (IOException e) {
				mainPanel.client.lostConnection();
				return;
			    }
		    }
	    }      

	// Refresh the user lists
	updateLists();
    }

    protected void goBanUsers()
    {
	// This gets called when the room owner wants to ban another
	// user (or users) from the chat room.
	
	String[] selectedUsers;

	// Get the names of all selected users
	selectedUsers = allUsersList.getSelectedItems();

	// For each selected user name, we will loop through our list of
	// connected users and send a BANUSER command to the server

	Vector usersVector = mainPanel.client.userList;

	for (int count1 = 0; count1 < selectedUsers.length; count1 ++)
	    {
		// We've got one of the selected users; now find the user
		// data structure so we can get the user id
		for (int count2 = 0; count2 < usersVector.size(); count2 ++)
		    {
			babylonUser user = (babylonUser)
			    usersVector.elementAt(count2);

			if (user.name.equals(selectedUsers[count1]))
			    try {
				// Send the command
				mainPanel.client.sendBanUser(user.id,
					   mainPanel.currentRoom.name);
			    }
			    catch (IOException e) {
				mainPanel.client.lostConnection();
				return;
			    }
		    }
	    }      

	// Refresh the user lists
	updateLists();
    }

    protected void goAllowUsers()
    {
	// This gets called when the room owner wants to un-ban another
	// user (or users) from the chat room.
	
	String[] selectedUsers;

	// Get the names of all selected users
	selectedUsers = allUsersList.getSelectedItems();

	// For each selected user name, we will loop through our list of
	// connected users and send a ALLOWUSER command to the server

	Vector usersVector = mainPanel.client.userList;

	for (int count1 = 0; count1 < selectedUsers.length; count1 ++)
	    {
		// We've got one of the selected users; now find the user
		// data structure so we can get the user id
		for (int count2 = 0; count2 < usersVector.size(); count2 ++)
		    {
			babylonUser user = (babylonUser)
			    usersVector.elementAt(count2);

			if (user.name.equals(selectedUsers[count1]))
			    try {
				// Send the command
				mainPanel.client.sendAllowUser(user.id,
					     mainPanel.currentRoom.name);
			    }
			    catch (IOException e) {
				mainPanel.client.lostConnection();
				return;
			    }
		    }
	    }      

	// Refresh the user lists
	updateLists();
    }

    public void actionPerformed(ActionEvent E)
    {
	if (E.getSource() == bootUsers)
	    {
		// We need to send a command to the server to boot the
		// selected users
		goBootUsers();
		return;
	    }
	else if (E.getSource() == inviteUsers)
	    {
		// We need to send a command to the server to invite the
		// selected users
		goInviteUsers();
		return;
	    }
	else if (E.getSource() == banUsers)
	    {
		// We need to send a command to the server to ban the
		// selected users
		goBanUsers();
		return;
	    }
	else if (E.getSource() == allowUsers)
	    {
		// We need to send a command to the server to allow the
		// selected users
		goAllowUsers();
		return;
	    }
	else if (E.getSource() == dismiss)
	    {
		mainPanel.roomControlDialog = null;
		dispose();
		return;
	    }
    }

    public void itemStateChanged(ItemEvent E)
    {
	if (E.getSource() == roomUsersList)
	    {
		boolean somethingSelected =
		    (roomUsersList.getSelectedItems().length > 0);

		bootUsers.setEnabled(somethingSelected);
		return;
	    }
	
	else if (E.getSource() == allUsersList)
	    {
		boolean somethingSelected =
		    (allUsersList.getSelectedItems().length > 0);

		inviteUsers.setEnabled(somethingSelected);
		banUsers.setEnabled(somethingSelected);
		allowUsers.setEnabled(somethingSelected);
		return;
	    }
    }

    public void keyPressed(KeyEvent E)
    {
    }

    public void keyReleased(KeyEvent E)
    {
	if (E.getKeyCode() == E.VK_ENTER)
	    {
		if (E.getSource() == bootUsers)
		    {
			// We need to send a command to the server to boot
			// the selected users
			goBootUsers();
			return;
		    }
		else if (E.getSource() == inviteUsers)
		    {
			// We need to send a command to the server to invite
			// the selected users
			goInviteUsers();
			return;
		    }
		else if (E.getSource() == banUsers)
		    {
			// We need to send a command to the server to ban
			// the selected users
			goBanUsers();
			return;
		    }
		else if (E.getSource() == allowUsers)
		    {
			// We need to send a command to the server to ban
			// the selected users
			goAllowUsers();
			return;
		    }
		else if (E.getSource() == dismiss)
		    {
			mainPanel.roomControlDialog = null;
			dispose();
			return;
		    }
	    }	
    }

    public void keyTyped(KeyEvent E)
    {
    }   

    public void windowActivated(WindowEvent E)
    {
    }

    public void windowClosed(WindowEvent E)
    {
    }

    public void windowClosing(WindowEvent E)
    {
	mainPanel.roomControlDialog = null;
 	dispose();
	return;
    }

    public void windowDeactivated(WindowEvent E)
    {
    }

    public void windowDeiconified(WindowEvent E)
    {
    }

    public void windowIconified(WindowEvent E)
    {
    }

    public void windowOpened(WindowEvent E)
    {
    }
}
