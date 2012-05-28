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
//  babylonMessagingDialog.java
//

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
import babylon.*;


public class babylonMessagingDialog
    extends Dialog
    implements ActionListener, ItemListener, KeyListener, WindowListener
{
    protected babylonPanel mainPanel;
    
    private Class thisClass = babylonMessagingDialog.class;

    protected GridBagLayout myLayout;

    protected Panel p1;
    protected Button readMessages;
    protected Label instantForLabel;
    protected java.awt.List allUsersList;
    protected Label saveForLabel;
    protected TextField saveFor;
    protected Label messageTextLabel;
    protected TextArea messageText;
    protected Panel p2;
    protected Button ok;
    protected Button cancel;


    public babylonMessagingDialog(babylonPanel panel)
    {
	super(panel.parentWindow, "Messaging", false);

	mainPanel = panel;

	myLayout = new GridBagLayout();
	setLayout(myLayout);

	p1 = new Panel();
	p1.setLayout(myLayout);

	readMessages =
	    new Button(mainPanel.strings.get(thisClass, "readsaved"));
	readMessages.addActionListener(this);
	readMessages.addKeyListener(this);
	p1.add(readMessages,
	       new babylonConstraints(0, 0, 1, 1, 1.0, 1.0,
				      GridBagConstraints.NORTHWEST,
				      GridBagConstraints.BOTH,
				      new Insets(0, 0, 0, 0),
				      0, 0));

	instantForLabel =
	    new Label(mainPanel.strings.get(thisClass, "sendto"));
	p1.add(instantForLabel,
	       new babylonConstraints(0, 1, 1, 1, 1.0, 1.0,
				      GridBagConstraints.NORTHWEST,
				      GridBagConstraints.NONE,
				      new Insets(0, 0, 0, 0),
				      0, 0));

	allUsersList = new java.awt.List(5);
	allUsersList.setFont(babylonPanel.smallFont);
	allUsersList.addItemListener(this);
	allUsersList.addKeyListener(this);
	allUsersList.setMultipleMode(false);
	p1.add(allUsersList,
	       new babylonConstraints(0, 2, 1, 1, 1.0, 1.0,
				      GridBagConstraints.NORTHWEST,
				      GridBagConstraints.BOTH,
				      new Insets(0, 0, 0, 0),
				      0, 0));

	saveForLabel = new Label(mainPanel.strings.get(thisClass, "orsave"));
	p1.add(saveForLabel,
	       new babylonConstraints(0, 3, 1, 1, 1.0, 1.0,
				      GridBagConstraints.NORTHWEST,
				      GridBagConstraints.NONE,
				      new Insets(0, 0, 0, 0),
				      0, 0));

	saveFor = new TextField(20);
	saveFor.addKeyListener(this);
	p1.add(saveFor,
	       new babylonConstraints(0, 4, 1, 1, 1.0, 1.0,
				      GridBagConstraints.NORTHWEST,
				      GridBagConstraints.BOTH,
				      new Insets(0, 0, 0, 0),
				      0, 0));

	messageTextLabel =
	    new Label(mainPanel.strings.get(thisClass, "message"));
	p1.add(messageTextLabel,
	       new babylonConstraints(0, 5, 1, 1, 1.0, 1.0,
				      GridBagConstraints.NORTHWEST,
				      GridBagConstraints.NONE,
				      new Insets(0, 0, 0, 0),
				      0, 0));

	messageText =
	    new TextArea("", 2, 20, TextArea.SCROLLBARS_VERTICAL_ONLY);
	messageText.addKeyListener(this);
	p1.add(messageText,
	       new babylonConstraints(0, 6, 1, 1, 1.0, 1.0,
				      GridBagConstraints.NORTHWEST,
				      GridBagConstraints.BOTH,
				      new Insets(0, 0, 0, 0),
				      0, 0));
	
	add(p1, new babylonConstraints(0, 0, 1, 1, 1.0, 1.0,
				       GridBagConstraints.NORTHWEST,
				       GridBagConstraints.BOTH,
				       new Insets(5, 5, 0, 5),
				       0, 0));

	p2 = new Panel();
	p2.setLayout(myLayout);

	ok = new Button(mainPanel.strings.get("ok"));
	ok.addActionListener(this);
	ok.addKeyListener(this);
	p2.add(ok, new babylonConstraints(0, 0, 1, 1, 1.0, 1.0,
					  GridBagConstraints.EAST,
					  GridBagConstraints.NONE,
					  new Insets(0, 0, 0, 0),
					  0, 0));

	cancel = new Button(mainPanel.strings.get("cancel"));
	cancel.addActionListener(this);
	cancel.addKeyListener(this);
	p2.add(cancel, new babylonConstraints(1, 0, 1, 1, 1.0, 1.0,
					      GridBagConstraints.WEST,
					      GridBagConstraints.NONE,
					      new Insets(0, 0, 0, 0),
					      0, 0));

	add(p2, new babylonConstraints(0, 1, 1, 1, 1.0, 1.0,
				       GridBagConstraints.CENTER,
				       GridBagConstraints.BOTH,
				       new Insets(5, 5, 5, 5),
				       0, 0));

	setSize(600,400);
	pack();
	setResizable(false);

	mainPanel.centerDialog(this);

	addKeyListener(this);
	addWindowListener(this);
	setVisible(true);
	updateLists();
	allUsersList.requestFocus();
    }

    public void updateLists()
    {
	// Update our lists.  This is called when something changes, such
	// as a user logs on/off
	
	if (allUsersList.getItemCount() > 0)
	    allUsersList.removeAll();

	Vector usersVector = mainPanel.client.userList;

	// Add all of the connected users to the 'all users' list
	for (int count = 0; count < usersVector.size(); count ++)
	    {
		babylonUser user = (babylonUser) usersVector.elementAt(count);
		allUsersList.add(user.name);
	    }

	return;
    }

    private void sendMessage()
    {
	String instantUser = allUsersList.getSelectedItem();
	String saveUser = saveFor.getText();

	if ((instantUser == null) && saveUser.equals(""))
	    {
		new babylonInfoDialog(mainPanel.parentWindow,
				      mainPanel.strings
				      .get(thisClass, "needrecipient"),
				      true,
				      mainPanel.strings
				      .get(thisClass, "specifyrecipient"),
				      mainPanel.strings.get("ok"));
		return;
	    }

	if (mainPanel.connected != true)
	    {
		new babylonInfoDialog(mainPanel.parentWindow,
				      mainPanel.strings
				      .get(thisClass, "notconnected"),
				      true,
				      mainPanel.strings
				      .get(thisClass, "mustbeconn"),
				      mainPanel.strings.get("ok"));
		return;
	    }

	// If the user has typed a name, leave the message on the server
	// for that user
	if (!saveUser.equals(""))
	    try {
		// Send the message to the server
		mainPanel.client.sendLeaveMess(saveUser,
						     messageText.getText());
	    }
	    catch (IOException e) {
		mainPanel.client.lostConnection();
		return;
	    }
	else
	    {
		// Send an instant message.  First we need to find the user
		// id that matches the name that's selected

		Vector usersVector = mainPanel.client.userList;

		for (int count = 0; count < usersVector.size(); count ++)
		    {
			babylonUser user =
			    (babylonUser) usersVector.elementAt(count);

			if (user.name.equals(instantUser))
			    {
				try {
				    mainPanel.client
					.sendInstantMess(user.id,
						 messageText.getText());
				}
				catch (IOException e) {
				    mainPanel.client.lostConnection();
				    return;
				}
				break;
			    }
		    }
	    }

	return;
    }


    public void actionPerformed(ActionEvent E)
    {
	if (E.getSource() == readMessages)
	    {
		try {
		    mainPanel.client.sendReadMess();
		}
		catch (IOException e) {
		    mainPanel.client.lostConnection();
		}
		dispose();
		return;
	    }

	else if (E.getSource() == ok)
	    {
		sendMessage();
		dispose();
		return;
	    }
	
	else if (E.getSource() == cancel)
	    {
		dispose();
		return;
	    }
    }

    public void itemStateChanged(ItemEvent E)
    {
	if (E.getSource() == allUsersList)
	    {
		// If a user name has been selected, empty out the saveFor
		// field
		if (allUsersList.getSelectedItem() != null)
		    saveFor.setText("");
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
		if (E.getSource() == readMessages)
		    {
			try {
			    mainPanel.client.sendReadMess();
			}
			catch (IOException e) {
			    mainPanel.client.lostConnection();
			}
			dispose();
			return;
		    }

		else if ((E.getSource() == ok) ||
		    (E.getSource() == saveFor) ||
		    (E.getSource() == messageText))
		    {
			sendMessage();
			dispose();
			return;
		    }
		
		else if (E.getSource() == cancel)
		    {
			dispose();
			return;
		    }
	    }

	else if (E.getSource() == saveFor)
	    {
		// The user is typing a name, so make sure no names are
		// selected in the allUsersList
		int items = allUsersList.getRows();
		for (int count = 0; count < items; count ++)
		    allUsersList.deselect(count);
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
