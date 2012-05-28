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
//  babylonCreateRoomDialog.java
//

// This file contains the code for the dialog that pops up when a user
// presses the "create room" button on the chat rooms dialog console.

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import babylon.*;


public class babylonCreateRoomDialog
    extends Dialog
    implements ActionListener, ItemListener, KeyListener, WindowListener
{
    protected babylonPanel mainPanel;

    private Class thisClass = babylonCreateRoomDialog.class;

    protected Panel p;
    protected Label roomNameLabel;
    protected TextField roomName;
    protected Checkbox priv;
    protected Label passwordLabel;
    protected TextField password;
    protected Label passwordWarningLabel1;
    protected Label passwordWarningLabel2;
    protected Button ok;
    protected Button cancel;

    protected GridBagLayout myLayout;


    babylonCreateRoomDialog(babylonPanel panel)
    {
	super(panel.parentWindow, panel.strings
	      .get(babylonCreateRoomDialog.class, "create"),
	      true);

	mainPanel = panel;

	// Make all of the widgets

	myLayout = new GridBagLayout();
	setLayout(myLayout);

	p = new Panel();
	p.setLayout(myLayout);
	
	roomNameLabel =
	    new Label(mainPanel.strings.get(thisClass, "roomname"));
	roomNameLabel.setFont(babylonPanel.smallFont);
	p.add(roomNameLabel,
	      new babylonConstraints(0, 0, 2, 1, 1.0, 1.0,
				     GridBagConstraints.NORTHWEST,
				     GridBagConstraints.NONE,
				     new Insets(0, 0, 0, 0),
				     0, 0));

	roomName = new TextField(30);
	roomName.setFont(babylonPanel.smallFont);
	roomName.addKeyListener(this);
	roomName.setEditable(true);
	roomName.setEnabled(true);
	p.add(roomName, new babylonConstraints(0, 1, 2, 1, 1.0, 1.0,
					       GridBagConstraints.NORTHWEST,
					       GridBagConstraints.BOTH,
					       new Insets(0, 0, 0, 0),
					       0, 0));

	priv = new Checkbox(mainPanel.strings.get(thisClass, "isprivate"),
			    false);
	priv.setFont(babylonPanel.smallFont);
	priv.setEnabled(true);
	priv.addItemListener(this);
	p.add(priv, new babylonConstraints(0, 2, 2, 1, 1.0, 1.0,
					   GridBagConstraints.NORTHWEST,
					   GridBagConstraints.NONE,
					   new Insets(0, 0, 0, 0),
					   0, 0));

	passwordLabel =
	    new Label(mainPanel.strings.get(thisClass, "password"));
	passwordLabel.setFont(babylonPanel.smallFont);
	passwordLabel.setEnabled(priv.getState());
	p.add(passwordLabel,
	      new babylonConstraints(0, 3, 2, 1, 1.0, 1.0,
				     GridBagConstraints.NORTHWEST,
				     GridBagConstraints.NONE,
				     new Insets(0, 0, 0, 0),
				     0, 0));

	password = new TextField();
	password.setFont(babylonPanel.smallFont);
	password.addKeyListener(this);
	password.setEditable(true);
	password.setEnabled(priv.getState());
	password.setEchoChar(new String("*").charAt(0));
	p.add(password, new babylonConstraints(0, 4, 2, 1, 1.0, 1.0,
					       GridBagConstraints.NORTHWEST,
					       GridBagConstraints.BOTH,
					       new Insets(0, 0, 0, 0),
					       0, 0));

	passwordWarningLabel1 =
	    new Label(mainPanel.strings.get(thisClass, "cantencrypt1"));
	passwordWarningLabel1
	    .setVisible(!mainPanel.passwordEncryptor.canEncrypt);
	passwordWarningLabel1.setFont(babylonPanel.XsmallFont);
	passwordWarningLabel1.setEnabled(priv.getState());
	p.add(passwordWarningLabel1,
	      new babylonConstraints(0, 5, 2, 1, 1.0, 1.0,
				     GridBagConstraints.NORTHWEST,
				     GridBagConstraints.NONE,
				     new Insets(0, 0, 0, 0),
				     0, 0));

	passwordWarningLabel2 =
	    new Label(mainPanel.strings.get(thisClass, "cantencrypt2"));
	passwordWarningLabel2
	    .setVisible(!mainPanel.passwordEncryptor.canEncrypt);
	passwordWarningLabel2.setFont(babylonPanel.XsmallFont);
	passwordWarningLabel2.setEnabled(priv.getState());
	p.add(passwordWarningLabel2,
	      new babylonConstraints(0, 6, 2, 1, 1.0, 1.0,
				     GridBagConstraints.NORTHWEST,
				     GridBagConstraints.NONE,
				     new Insets(0, 0, 0, 0),
				     0, 0));

	ok = new Button(mainPanel.strings.get("ok"));
	ok.setFont(babylonPanel.smallFont);
	ok.addActionListener(this);
	ok.addKeyListener(this);
	ok.setEnabled(false);
	p.add(ok, new babylonConstraints(0, 7, 1, 1, 1.0, 1.0,
					 GridBagConstraints.EAST,
					 GridBagConstraints.NONE,
					 new Insets(0, 0, 0, 0),
					 0, 0));

	cancel = new Button(mainPanel.strings.get("cancel"));
	cancel.setFont(babylonPanel.smallFont);
	cancel.addActionListener(this);
	cancel.addKeyListener(this);
	cancel.setEnabled(true);
	p.add(cancel, new babylonConstraints(1, 7, 1, 1, 1.0, 1.0,
					     GridBagConstraints.WEST,
					     GridBagConstraints.NONE,
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
	roomName.requestFocus();
    }

    protected void goCreate()
    {
	if (!roomName.getText().equals(""))
	    {
		String pword = "";
		if (priv.getState())
		    pword = mainPanel.passwordEncryptor
			.encryptPassword(password.getText());

		mainPanel.canvas.clear();

		try {
		    mainPanel.client
			.sendEnterRoom(roomName.getText(), priv.getState(),
				       pword);
		}
		catch (IOException e) {
		    mainPanel.client.lostConnection();
		    return;
		}

		mainPanel.currentRoom.name = roomName.getText();
		mainPanel.roomOwner(true);
		return;
	    }
    }

    public void actionPerformed(ActionEvent E)
    {
	if (E.getSource() == ok)
	    {
		goCreate();
		dispose();
		return;
	    }

	if (E.getSource() == cancel)
	    {
		dispose();
		return;
	    }
    }

    public void itemStateChanged(ItemEvent E)
    {
	if (E.getSource() == priv)
	    {
		boolean state = priv.getState();
		passwordLabel.setEnabled(state);
		password.setEnabled(state);
		passwordWarningLabel1.setEnabled(state);
		passwordWarningLabel2.setEnabled(state);
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
		if (E.getSource() == cancel)
		    {
			dispose();
			return;
		    }

		else
		    {
			goCreate();
			dispose();
			return;
		    }
	    }

	else if (E.getSource() == roomName)
	    {
		if (roomName.getText().equals(""))
		    ok.setEnabled(false);
		else
		    ok.setEnabled(true);
		return;
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
