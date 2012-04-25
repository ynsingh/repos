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
//  babylonServerWindow.java
//

import java.awt.*;
import java.awt.event.*;
import javax.swing.ImageIcon;
import java.io.*;
import java.net.*;
import babylon.*;


public class babylonServerWindow
    extends Frame
    implements ActionListener, ItemListener, WindowListener
{
    protected GridBagLayout myLayout;

    protected Panel p;
    protected Label listening;
    protected List userList;
    protected Button disconnect;
    protected Button disconnectAll;
    protected Button console;
    protected Button userAdmin;
    protected Checkbox logChat;
    protected Button shutdown;
    protected TextField stats;
    protected TextArea logWindow;
    protected babylonPictureCanvas canvas;

    protected babylonServer server;
    protected babylonWindow consoleWindow;
    private Class thisClass = babylonServerWindow.class;


    public babylonServerWindow(babylonServer parent, String Name)
    {
	super(Name);
	server = parent;

	myLayout = new GridBagLayout();
	setLayout(myLayout);

	p = new Panel();
	p.setLayout(myLayout);

	listening = new Label(server.strings.get(thisClass, "listenport") +
			      " " + server.port);
	p.add(listening,
	      new babylonConstraints(0, 0, 1, 1, 0.0, 0.0,
				     GridBagConstraints.NORTHWEST,
				     GridBagConstraints.NONE,
				     new Insets(0, 0, 0, 0),
				     0, 0));

	logChat = new Checkbox(server.strings.get(thisClass, "logchats"),
			       server.logChats);
	logChat.addItemListener(this);
	p.add(logChat,
	      new babylonConstraints(1, 0, 1, 1, 1.0, 1.0,
				     GridBagConstraints.NORTHWEST,
				     GridBagConstraints.NONE,
				     new Insets(0, 0, 0, 0),
				     0, 0));

	userList = new List(4, false);
	userList.addItemListener(this);
	p.add(userList,
	      new babylonConstraints(0, 1, 1, 5, 1.0, 1.0,
				     GridBagConstraints.NORTHWEST,
				     GridBagConstraints.BOTH,
				     new Insets(0, 0, 5, 0),
				     0, 0));

	userAdmin =
	    new Button(server.strings.get(thisClass, "usermanagement"));
	userAdmin.addActionListener(this);
	p.add(userAdmin,
	      new babylonConstraints(1, 1, 1, 1, 1.0, 1.0,
				     GridBagConstraints.NORTHWEST,
				     GridBagConstraints.HORIZONTAL,
				     new Insets(0, 0, 0, 0),
				     0, 0));

	console = new Button(server.strings.get(thisClass, "adminclient"));
	console.addActionListener(this);
	p.add(console,
	      new babylonConstraints(1, 2, 1, 1, 1.0, 1.0,
				     GridBagConstraints.NORTHWEST,
				     GridBagConstraints.HORIZONTAL,
				     new Insets(0, 0, 0, 0),
				     0, 0));

	disconnect =
	    new Button(server.strings.get(thisClass, "disconnectuser"));
	disconnect.setEnabled(false);
	disconnect.addActionListener(this);
	p.add(disconnect,
	      new babylonConstraints(1, 3, 1, 1, 1.0, 1.0,
				     GridBagConstraints.NORTHWEST,
				     GridBagConstraints.HORIZONTAL,
				     new Insets(0, 0, 0, 0),
				     0, 0));

	disconnectAll =
	    new Button(server.strings.get(thisClass, "disconnectall"));
	disconnectAll.setEnabled(false);
	disconnectAll.addActionListener(this);
	p.add(disconnectAll,
	      new babylonConstraints(1, 4, 1, 1, 1.0, 1.0,
				     GridBagConstraints.NORTHWEST,
				     GridBagConstraints.HORIZONTAL,
				     new Insets(0, 0, 0, 0),
				     0, 0));

	shutdown = new Button(server.strings.get(thisClass, "shutdown"));
	shutdown.addActionListener(this);
	p.add(shutdown,
	      new babylonConstraints(1, 5, 1, 1, 1.0, 1.0,
				     GridBagConstraints.NORTHWEST,
				     GridBagConstraints.HORIZONTAL,
				     new Insets(0, 0, 0, 0),
				     0, 0));

	stats = new TextField(server.strings
			      .get(thisClass, "connectionscurrent") + " 0  " +
			      server.strings
			      .get(thisClass, "connectionspeak") + " 0  " +
			      server.strings
			      .get(thisClass, "connectionstotal") + " 0", 40);
	stats.setEditable(false);
	p.add(stats,
	      new babylonConstraints(0, 7, 2, 1, 1.0, 1.0,
				     GridBagConstraints.NORTHWEST,
				     GridBagConstraints.BOTH,
				     new Insets(0, 0, 0, 0),
				     0, 0));

	logWindow =
	    new TextArea(server.strings.get(thisClass, "activitylog") + 
			 "\n", 10, 40,
			 TextArea.SCROLLBARS_VERTICAL_ONLY);
	logWindow.setEditable(false);
	p.add(logWindow,
	      new babylonConstraints(0, 8, 2, 1, 1.0, 1.0,
				     GridBagConstraints.NORTHWEST,
				     GridBagConstraints.BOTH,
				     new Insets(0, 0, 5, 0),
				     0, 0));

	canvas = new babylonPictureCanvas(this);
	p.add(canvas,
	      new babylonConstraints(0, 9, 2, 1, 1.0, 1.0,
				     GridBagConstraints.NORTHWEST,
				     GridBagConstraints.BOTH,
				     new Insets(0, 0, 0, 0),
				     0, 0));

	add(p, new babylonConstraints(0, 0, 1, 1, 1.0, 1.0,
				      GridBagConstraints.NORTHWEST,
				      GridBagConstraints.BOTH,
				      new Insets(5, 5, 5, 5),
				      0, 0));

	try {
	    URL url = new URL ("file", "localhost", "babylonPic.jpg");
	    Image image = getToolkit().getImage(url);

	    canvas.setimage(image);
	} 
	catch (Exception e) { 
	    System.out.println(e);
	}

	try {
	    URL iconUrl = new URL("file", "localhost", "babylonIcon.jpg");
	    ImageIcon icon = new ImageIcon(iconUrl);
	    this.setIconImage(icon.getImage());
	}
	catch (Exception e) { /* Not important */ }

	addWindowListener(this);

	setSize(600, 600);
	pack();
    }

    
    public void updateStats()
    {
	// This just updates any statistics that are shown on the face of
	// the server window
	stats.setText(server.strings.get(thisClass, "connectionscurrent") +
		      " " + server.currentConnections + "  " +
		      server.strings.get(thisClass, "connectionspeak") +
		      " " + server.peakConnections + "  " +
		      server.strings.get(thisClass, "connectionstotal") +
		      " " + server.totalConnections);
	return;
    }

    public void centerDialog(Dialog dialog)
    {
	// For centering dialogs

	Dimension size = dialog.getSize();
	Dimension parentSize = getSize();
	Point parentLocation = getLocationOnScreen();
	if ((parentSize.width <= size.width) ||
	    (parentSize.height <= size.height))
	    // If this window is bigger than the parent window,
	    // place it at the same coordinates as the parent.
	    dialog.setLocation(parentLocation.x, parentLocation.y);
	else
	    // Otherwise, place it centered within the parent window.
	    dialog.setLocation((((parentSize.width - size.width) / 2)
				+ parentLocation.x),
			       (((parentSize.height - size.height) / 2)
				+ parentLocation.y));
    }

    public void actionPerformed(ActionEvent E)
    {
	if (E.getSource() == userAdmin)
	    {
		babylonUserToolDialog userTool =
		    new babylonUserToolDialog(this);
		return;
	    }

	if (E.getSource() == console)
	    {
		babylonInfoDialog tmp =
		    new babylonInfoDialog(this, server.strings
					  .get(thisClass, "loading"), false,
					  server.strings
					  .get(thisClass, "startingclient"),
					  server.strings.get("ok"));

		consoleWindow =
		    new babylonWindow(new babylonPanel("Administrator", "",
				       "localhost",
				       Integer.toString(server.port),
				       true, server.myURL));
		tmp.dispose();

		consoleWindow.contentPanel.adminConsole = true;
		consoleWindow.contentPanel.lockSettings = true;
		consoleWindow.contentPanel.requirePassword = false;

		// Show the window
		consoleWindow.show();

		// Connect
		consoleWindow.contentPanel.connect();

		return;
	    }

	if (E.getSource() == disconnect)
	    {
		String disconnectUser;

		synchronized (userList) {
		    disconnectUser = userList.getSelectedItem();
		}

		if (disconnectUser != null)
		    {
			// Loop through all of the current connections to find
			// the object that corresponds to this name
			
			for (int count = 0;
			     count < server.currentConnections;
			     count ++)
			    {
				babylonClientSocket tempuser =
				    (babylonClientSocket)
				    server.connections.elementAt(count);
				
				if (tempuser.user.name.equals(disconnectUser))
				    {
					server.disconnect(tempuser, true);
					break;
				    }
			    }
		    }
		return;
	    }

	if (E.getSource() == disconnectAll)
	    {
		server.disconnectAll(true);
		return;
	    }

	if (E.getSource() == shutdown)
	    {
		if (server.currentConnections > 0)
		    new babylonServerShutdownDialog(this, server);
		else
		    server.shutdown();
		return;
	    }
    }

    public void itemStateChanged(ItemEvent E)
    {
	// The 'log chat' checkbox
	if (E.getSource() == logChat)
	    {
		server.logChats = logChat.getState();
		
		// Loop through all of the chat rooms, and set the logging
		// state to be the same as the value of the checkbox
		for (int count = 0; count < server.chatRooms.size();
		     count ++)
		    {
			babylonChatRoom tmp = (babylonChatRoom)
			    server.chatRooms.elementAt(count);
			try {
			    tmp.setLogging(server.logChats);
			}
			catch (IOException e) {
			    server.serverOutput(server.strings
						.get(thisClass,
						     "togglelogerror") +
						" " + tmp.name + "\n");
			}
		    }
	    }

	// The user list
	if (E.getSource() == userList)
	    {
		// If anything is selected, enable the 'disconnect user'
		// button, otherwise disable it
		synchronized (userList) {
		    disconnect.setEnabled(userList.getSelectedItem() !=
					  null);
		}
		
	    }
    }

    public void windowActivated(WindowEvent E)
    {
    }

    public void windowClosed(WindowEvent E)
    {
    }

    public void windowClosing(WindowEvent E)
    {
	if (server.currentConnections > 0)
	    new babylonServerShutdownDialog(this, server);
	else
	    server.shutdown();
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


class babylonServerShutdownDialog
    extends Dialog
    implements ActionListener, KeyListener, WindowListener
{
    protected babylonServerWindow parentWindow;
    protected babylonServer server;
    protected GridBagLayout myLayout;

    protected Panel p;
    protected Label message1;
    protected Label message2;
    protected Button yes;
    protected Button cancel;


    public babylonServerShutdownDialog(babylonServerWindow serverWindow,
				       babylonServer theServer)
    {
	super(serverWindow, theServer.strings.get(babylonServerWindow.class,
						  "servershutdown"), true);
	parentWindow = serverWindow;
	server = theServer;

	myLayout = new GridBagLayout();
	setLayout(myLayout);

	p = new Panel();
	p.setLayout(myLayout);

	message1 = new Label(server.strings.get(babylonServerWindow.class,
						"areyousure1"));
	p.add(message1, new babylonConstraints(0, 0, 2, 1, 0.0, 0.0,
					       GridBagConstraints.NORTHWEST,
					       GridBagConstraints.NONE,
					       new Insets(0, 0, 0, 0),
					       0, 0));
	
	message2 = new Label(server.strings.get(babylonServerWindow.class,
						"areyousure2"));
	p.add(message2, new babylonConstraints(0, 1, 2, 1, 0.0, 0.0,
					       GridBagConstraints.NORTHWEST,
					       GridBagConstraints.NONE,
					       new Insets(0, 0, 0, 0),
					       0, 0));

	yes = new Button(server.strings.get(babylonServerWindow.class,
					    "yes"));
	yes.addKeyListener(this);
	yes.addActionListener(this);
	p.add(yes, new babylonConstraints(0, 2, 1, 1, 1.0, 1.0,
					  GridBagConstraints.EAST,
					  GridBagConstraints.NONE,
					  new Insets(0, 0, 0, 0),
					  0, 0));

	cancel = new Button(server.strings.get("cancel"));
	cancel.addKeyListener(this);
	cancel.addActionListener(this);
	p.add(cancel, new babylonConstraints(1, 2, 1, 1, 1.0, 1.0,
					     GridBagConstraints.WEST,
					     GridBagConstraints.NONE,
					     new Insets(0, 0, 0, 0),
					     0, 0));
	
	add(p, new babylonConstraints(0, 0, 1, 1, 1.0, 1.0,
				      GridBagConstraints.NORTHWEST,
				      GridBagConstraints.NONE,
				      new Insets(5, 5, 5, 5),
				      0, 0));

	setBackground(Color.lightGray);
	pack();
	setResizable(false);
	parentWindow.centerDialog(this);
    
	addKeyListener(this);
	addWindowListener(this);
	setVisible(true);
	yes.requestFocus();
    }

    public void actionPerformed(ActionEvent E)
    {
	if (E.getSource() == yes)
	    {
		dispose();
		server.shutdown();
		return;
	    }

	else if (E.getSource() == cancel)
	    {
		dispose();
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
		if (E.getSource() == yes)
		    {
			dispose();
			server.shutdown();
			return;
		    }

		else if (E.getSource() == cancel)
		    {
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


class babylonPictureCanvas
    extends Canvas
{

    private babylonServerWindow main;
    private Image image;
    
    public babylonPictureCanvas(babylonServerWindow mainwindow)
    {
	super();
	main = mainwindow;
	setBackground(Color.lightGray);
	setSize(200, 75);
	repaint();
	setVisible(true);
    }

    public void paint(Graphics g)
    {
	if (image != null)
	    {
		g.drawImage(image, 0, 0, getSize().width, getSize().height, 
			    this);
	    }
    }

    public void setimage(Image theimage)
    {
	image = theimage;
	repaint();
    }
}
