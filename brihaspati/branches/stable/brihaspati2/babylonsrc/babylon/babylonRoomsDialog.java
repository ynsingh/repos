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
//  babylonRoomsDialog.java
//

// This file contains the code for the dialog that pops up when a user
// presses the "chat rooms" button on the main console.  It contains a list
// of all the currently active chat rooms, along with associated information,
// and provides an interface for joining a new chat room

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import babylon.*;


public class babylonRoomsDialog
    extends Dialog
    implements ActionListener, ItemListener, KeyListener, WindowListener
{
    protected babylonPanel mainPanel;
    private Class thisClass = babylonRoomsDialog.class;

    protected GridBagLayout myLayout;

    protected Panel p;
    protected java.awt.List roomList;
    protected Button enterRoom;
    protected Button roomInfo;
    protected Button createRoom;
    protected Button updateList;
    protected Button dismiss;

    protected boolean haveRoomList = false;


    babylonRoomsDialog(babylonPanel panel)
    {
	super(panel.parentWindow,
	      panel.strings.get(babylonRoomsDialog.class, "chatrooms"),
	      false);

	mainPanel = panel;

	// Make all of the widgets

	myLayout = new GridBagLayout();
	setLayout(myLayout);

	p = new Panel();
	p.setLayout(myLayout);

	roomList = new java.awt.List(10);
	roomList.setFont(babylonPanel.smallFont);
	roomList.addItemListener(this);
	roomList.addKeyListener(this);
	roomList.setMultipleMode(false);
	p.add(roomList,
	      new babylonConstraints(0, 0, 5, 1, 1.0, 1.0,
				     GridBagConstraints.NORTHWEST,
				     GridBagConstraints.BOTH,
				     new Insets(0, 0, 0, 0),
				     0, 0));

	enterRoom = new Button(mainPanel.strings.get(thisClass, "enterroom"));
	enterRoom.setFont(babylonPanel.smallFont);
	enterRoom.addActionListener(this);
	enterRoom.addKeyListener(this);
	enterRoom.setEnabled(false);
	p.add(enterRoom,
	      new babylonConstraints(0, 1, 1, 1, 1.0, 1.0,
				     GridBagConstraints.NORTHWEST,
				     GridBagConstraints.BOTH,
				     new Insets(0, 0, 0, 0),
				     0, 0));

	roomInfo = new Button(mainPanel.strings.get(thisClass, "roominfo"));
	roomInfo.setFont(babylonPanel.smallFont);
	roomInfo.addActionListener(this);
	roomInfo.addKeyListener(this);
	roomInfo.setEnabled(false);
	p.add(roomInfo,
	      new babylonConstraints(1, 1, 1, 1, 1.0, 1.0,
				     GridBagConstraints.NORTHWEST,
				     GridBagConstraints.BOTH,
				     new Insets(0, 0, 0, 0),
				     0, 0));

	createRoom =
	    new Button(mainPanel.strings.get(thisClass, "createroom"));
	createRoom.setFont(babylonPanel.smallFont);
	createRoom.addActionListener(this);
	createRoom.addKeyListener(this);
	createRoom.setEnabled(false);
	p.add(createRoom,
	      new babylonConstraints(2, 1, 1, 1, 1.0, 1.0,
				     GridBagConstraints.NORTHWEST,
				     GridBagConstraints.BOTH,
				     new Insets(0, 0, 0, 0),
				     0, 0));

	updateList =
	    new Button(mainPanel.strings.get(thisClass, "updatelist"));
	updateList.setFont(babylonPanel.smallFont);
	updateList.addActionListener(this);
	updateList.addKeyListener(this);
	updateList.setEnabled(true);
	p.add(updateList,
	      new babylonConstraints(3, 1, 1, 1, 1.0, 1.0,
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
	      new babylonConstraints(4, 1, 1, 1, 1.0, 1.0,
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

	mainPanel.centerDialog(this);

	setVisible(true);
	enterRoom.requestFocus();

	getRoomList();
    }

    protected void getRoomList()
    {
	// Ask the server for a list of chat rooms
	
	synchronized (roomList) {
	    haveRoomList = false;

	    // Clear out the room list
	    if (roomList.getItemCount() > 0)
		roomList.removeAll();

	    // Disable various things while we wait for the room list.
	    enterRoom.setEnabled(false);
	    roomInfo.setEnabled(false);
	    
	    // Show a message just in case it takes a moment
	    roomList.add(mainPanel.strings.get(thisClass, "requestinglist"));
	    
	    // Make the request
	    try {
		mainPanel.client.requestRoomList();
	    }
	    catch (IOException e) {
		mainPanel.client.lostConnection();
		return;
	    }

	    // ... and now we wait for the client to fill up our list.
	}
    }

    public void receivedList()
    {
	// This routine will be called by the client when it has acquired
	// a list of the current chat rooms from the server
	
	synchronized (roomList) {
	    
	    // Clear out the room list
	    if (roomList.getItemCount() > 0)
		roomList.removeAll();

	    // Now add the names of the chat rooms to the list widget
	    for (int count = 0; count < mainPanel.roomInfoArray.length;
		 count ++)
		roomList.add(mainPanel.roomInfoArray[count].name);

	    haveRoomList = true;
	}
    }

    protected void goEnter()
    {
	// The user wants to join the selected chat room.  Send
	// a message to the server
	int index = 0;
	babylonRoomInfo selectedRoom;

	index = roomList.getSelectedIndex();

	if (index >= 0)
	    {
		selectedRoom = mainPanel.roomInfoArray[index];

		if (mainPanel.currentRoom == selectedRoom)
		    {
			new babylonInfoDialog(mainPanel.parentWindow,
					      mainPanel.strings
					      .get(thisClass, "nochange"),
					      true, mainPanel.strings
					      .get(thisClass,
						   "alreadyinroom") + " \"" +
					      selectedRoom.name + "\"",
					      mainPanel.strings.get("ok"));
			return;
		    }

		String password = "";
		// If this room is private, and we have not been previously
		// invited, prompt for a password
		if (selectedRoom.priv && !selectedRoom.invited)
		    {
			babylonPasswordDialog passDialog = 
			    new babylonPasswordDialog(mainPanel,
				      mainPanel.strings.get(thisClass,
							    "enterpassword") +
				      " \"" + selectedRoom.name+ "\"", true);
			password = passDialog.getPassword();
		    }
		
		try {
		    mainPanel.client.sendEnterRoom(selectedRoom.name,
						   selectedRoom.priv,
						   password);
		}
		catch (IOException e) {
		    mainPanel.client.lostConnection();
		    return;
		}
	    }
	
	mainPanel.roomsDialog = null;

	// DON'T do any other "active" stuff to change chat rooms here.
	// The move between chat rooms only becomes "official" when the
	// server sends out an "enter" message.  There are a couple of
	// reasons for this; one is that it's possible to move between
	// rooms without choosing to do so from this dialog box (users
	// can be booted by room owners).  Another is that the server
	// must authorize the move before it happens.
    }

    protected void showInfo()
    {
	// Gather all of the information we have about this room,
	// construct a big String to contain it, and display it
	// in a dialog box
	int index = 0;
	babylonRoomInfo selectedRoom;
	String infoString = "";

	index = roomList.getSelectedIndex();

	if (index >= 0)
	    {
		selectedRoom = mainPanel.roomInfoArray[index];

		infoString += mainPanel.strings.get(thisClass, "roomname") +
		    " " + selectedRoom.name + "\n" +
		    mainPanel.strings.get(thisClass, "creator") + " " +
		    selectedRoom.creatorName + "\n" +
		    mainPanel.strings.get(thisClass, "private") + " ";

		if (selectedRoom.priv)
		    {
			infoString += mainPanel.strings
			    .get(thisClass, "yes") + "\n" +
			    mainPanel.strings.get(thisClass, "invited") +
			    " ";
			if (selectedRoom.invited)
			    infoString += mainPanel.strings.get(thisClass,
								"yes");
			else
			    infoString += mainPanel.strings.get(thisClass,
								"no");
		    }
		else
		    infoString += mainPanel.strings.get(thisClass, "no");

		infoString += "\n" +
		    mainPanel.strings.get(thisClass, "users") + " " +
		    selectedRoom.userNames.size() + "\n\n";

		for (int count = 0; count < selectedRoom.userNames.size();
		     count ++)
		    {
			infoString +=
			    (String) selectedRoom.userNames.elementAt(count);
			if (count < (selectedRoom.userNames.size() - 1))
			    infoString += ", ";
		    }

		// Now create the dialog box
		new babylonTextDialog(mainPanel.parentWindow,
				      mainPanel.strings
				      .get(thisClass, "information"),
				      infoString, 30, 10,
				      TextArea.SCROLLBARS_VERTICAL_ONLY,
				      false,
				      mainPanel.strings.get("dismiss"));
	    }
    }

    public void actionPerformed(ActionEvent E)
    {
	if (E.getSource() == enterRoom)
	    {
		goEnter();
		mainPanel.roomOwner(false);
		dispose();
		return;
	    }

	if (E.getSource() == roomInfo)
	    {
		showInfo();
		return;
	    }

	if (E.getSource() == createRoom)
	    {
		new babylonCreateRoomDialog(mainPanel);
		dispose();
		return;
	    }

	if (E.getSource() == updateList)
	    {
		getRoomList();
		return;
	    }

	if (E.getSource() == dismiss)
	    {
		mainPanel.roomsDialog = null;
		dispose();
		return;
	    }
    }

    public void itemStateChanged(ItemEvent E)
    {
	if (E.getSource() == roomList)
	    {
		// If we don't have the room list yet, then the user has
		// selected the 'hold on a minute' message thing we place
		// in the window while we're waiting.  Ignore it
		if (!haveRoomList)
		    return;
		
		boolean somethingSelected =
		    (roomList.getSelectedItems().length > 0);

		enterRoom.setEnabled(somethingSelected);
		roomInfo.setEnabled(somethingSelected);

		// If the selected room is private, enable the password
		// bits.
		babylonRoomInfo selectedRoom =
		    mainPanel.roomInfoArray[roomList.getSelectedIndex()];
	    }
    }

    public void keyPressed(KeyEvent E)
    {
    }

    public void keyReleased(KeyEvent E)
    {
	if (E.getKeyCode() == E.VK_ENTER)
	    {
		if (E.getSource() == enterRoom)
		    {
			goEnter();
			dispose();
			return;
		    }
		else if (E.getSource() == roomInfo)
		    {
			showInfo();
			return;
		    }
		else if (E.getSource() == createRoom)
		    {
			new babylonCreateRoomDialog(mainPanel);
			dispose();
			return;
		    }
		else if (E.getSource() == updateList)
		    {
			getRoomList();
			return;
		    }
		else if (E.getSource() == dismiss)
		    {
			mainPanel.roomsDialog = null;
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
	mainPanel.roomsDialog = null;
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
