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
//  babylonPanel.java
//

import java.awt.*;
import java.awt.event.*;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.io.*;
import java.util.*;
import java.util.Vector;
import java.net.*;
import babylon.*;
import javax.swing.JFileChooser;

public class babylonPanel extends Panel implements ActionListener, ItemListener, KeyListener, MouseListener,
	       MouseMotionListener
{
    public String expiryTime = "",groupName="";
    public int clientId = 0;
    public String name = "";
    public String plainPassword = "";
    public String encryptedPassword = "";
    public String host = "localhost";
    public String port = "12468";
    public int portNumber = 12468;
    public String additional = "";
    protected babylonRoomInfo currentRoom;
    protected babylonRoomInfo[] roomInfoArray;
    protected boolean lockSettings = false;
    protected boolean requirePassword = true;
    public int drawFontNumber;
    public int drawStyle;
    public int drawSize;
    public String drawText;
    public URL babylonURL;
    protected String buffer;
    protected String textStr;
    protected int overallSizex;
    protected int overallSizey;
    protected int oldResizePosition;
    protected int newResizePosition;
    protected boolean playSound = true;

    public GridBagLayout mainLayout = new GridBagLayout();

    public static final Font XsmallFont =
	new Font("Helvetica", Font.PLAIN, 10);
    public static final Font smallFont =
	new Font("Helvetica", Font.PLAIN, 12);
    public static final Font largeFont =
	new Font("Helvetica", Font.PLAIN, 14);
    public static final Font largeBoldFont =
	new Font("Helvetica", Font.BOLD, 14);
    public static final Font XlargeFont =
	new Font("Helvetica", Font.PLAIN, 16);

    protected babylonPasswordEncryptor passwordEncryptor = null;

    protected babylonRoomsDialog roomsDialog;
    protected babylonRoomControlDialog roomControlDialog;
    protected Vector instantMessageDialogs;

    // socket stuff

    protected babylonClient client;
    protected boolean connected;

    // If we're operating as an applet
    protected babylonApplet thisApplet = null;

    // ...otherwise
    protected Frame parentWindow = new Frame();

    // For managing strings according to locale
    protected babylonStringManager strings = null;
    //private Class thisClass = babylonPanel.class;
    //public static babylonStringManager strings = null;
    public static Class thisClass = babylonPanel.class;

    // If this is an administrator console (setting this to true won't
    // grant special privileges as far as the server is concerned -- it's
    // only for the benefit of this window so don't bother trying ;-)
    protected boolean adminConsole = false;

    // The button panel

    protected Panel buttonPanel;
    protected Button connectButton;
    protected Button disconnectButton;
    protected Button pastePictureButton;
    protected Button saveTextButton;
    protected Button saveCanvasButton;
    protected Button playRecordButton;
    protected Button ignoreButton;
    protected Button settingsButton;
    protected Button chatRoomControlButton;
    protected Button manualButton;
    protected Button aboutButton;
 
    // Left (content) side

    protected Panel contentPanel;
    protected Label conferenceLabel;
    protected TextArea typedArea;
    protected TextArea messagesArea;
   // public static TextArea messagesArea;
    protected Panel canvasPanel;
    protected Label drawCanvasLabel;
    protected Button resizeButton;
    protected Button tmpResizeButton;
    //public babylonCanvas canvas;
    protected babylonCanvas canvas;

    // Right controls side

    protected Panel controlsPanel;
    protected Label nameLabel;
    protected TextField activityField;
    protected Label sendToLabel;
    protected Checkbox sendToAllCheckbox;
    //public java.awt.List sendToList;
    protected java.awt.List sendToList;
    protected Button userInfoButton;
    protected Button pageButton;
    protected Button chatRoomsButton;
    protected Button messagingButton;

    // Drawing controls panel

    protected Panel drawingControlsPanel;
    protected Label drawingControlsLabel;
    protected Choice colorChoice;
    protected Choice thicknessChoice;
    protected Choice fillTypeChoice;
    protected Choice selectGroupFile;
    protected Checkbox freehandCheckbox;
    protected Checkbox lineCheckbox;
    protected Checkbox rectangleCheckbox;
    protected Checkbox ovalCheckbox;
    protected Checkbox textCheckbox;
    protected Checkbox eraseCheckbox;
    protected CheckboxGroup drawTypeGroup;
    protected Button clearCanvasButton;
    protected Vector groupListVector1=null; 
    protected String choice="";
    public babylonPanel(String userName, String userPassword,
			String hostName, String portName,
			boolean isCanvas, URL myURL)
    {
	super();

	// Set the username, host, and port values if they've been specified
	if (userName != null)
	    if (!userName.equals(""))
		name = userName;
	if (userPassword != null)
	    if (!userPassword.equals(""))
		// This password is still unencrypted
		plainPassword = userPassword;
	if (hostName != null)
	    if (!hostName.equals(""))
		host = hostName;
	if (portName != null)
	    if (!portName.equals(""))
		port = portName;

	// Get the URL of the current directory, so that we can find the
	// rest of our files.
	babylonURL = myURL;
	//System.out.println("D..Lang in Panel"+thisApplet.dLang);
	groupName = thisApplet.room;
	
	if(!(thisApplet.dLang.equals("")) || thisApplet.dLang != null)
		strings = new babylonStringManager(babylonURL,thisApplet.dLang);
	else
		strings = new babylonStringManager(babylonURL,
						   Locale.getDefault().getLanguage());
						   //"fr");
					   
	// Any active instant messaging dialogs
	instantMessageDialogs = new Vector();

	// set background color
	Color mycolor = Color.lightGray;
	setBackground(mycolor);

	// set default font
	drawFontNumber = 0;
	drawStyle = Font.PLAIN;
	drawSize = 10;

	overallSizex = 600;
	overallSizey = 500;
	setSize(overallSizex, overallSizey);

	// set up all of the window crap

	setLayout(mainLayout);

	// The top button panel
	
	buttonPanel = new Panel();
	buttonPanel.setLayout(mainLayout);

	connectButton = new Button(strings.get(thisClass, "connect"));
	connectButton.setFont(XsmallFont);
	connectButton.addActionListener(this);
	buttonPanel.add(connectButton,
			//new babylonConstraints(0, 0, 1, 1, 1.0, 1.0,
			//new babylonConstraints(0, 0, 1, 1, 0.0, 0.0,
			new babylonConstraints(0, 0, 1, 1, 1.0, 0.0,
					       GridBagConstraints.WEST,
					       //GridBagConstraints.HORIZONTAL,
					       GridBagConstraints.BOTH,
					       new Insets(0, 0, 0, 0),
					       0, 0));

	disconnectButton = new Button(strings.get(thisClass, "disconnect"));
	disconnectButton.setFont(XsmallFont);
	disconnectButton.addActionListener(this);
	disconnectButton.setEnabled(false);
	buttonPanel.add(disconnectButton,
			//new babylonConstraints(0, 1, 1, 1, 1.0, 1.0,
			//new babylonConstraints(1, 0, 1, 1, 0.0, 0.0,
			new babylonConstraints(1, 0, 1, 1, 1.0, 0.0,
					       GridBagConstraints.WEST,
					       //GridBagConstraints.HORIZONTAL,
					       GridBagConstraints.BOTH,
					       new Insets(0, 0, 0, 0),
					       0, 0));
/**

	settingsButton = new Button(strings.get(thisClass, "settings"));
	settingsButton.setFont(XsmallFont);
	settingsButton.addActionListener(this);
	buttonPanel.add(settingsButton,
			new babylonConstraints(0, 2, 1, 1, 1.0, 1.0,
					       GridBagConstraints.WEST,
					       GridBagConstraints.HORIZONTAL,
					       new Insets(0, 0, 0, 0),
					       0, 0));

	chatRoomsButton = new Button(strings.get(thisClass, "chatrooms"));
	chatRoomsButton.setFont(XsmallFont);
	chatRoomsButton.addActionListener(this);
	chatRoomsButton.setEnabled(false);
	buttonPanel.add(chatRoomsButton,
			new babylonConstraints(1, 0, 1, 1, 1.0, 1.0,
					       GridBagConstraints.WEST,
					       GridBagConstraints.HORIZONTAL,
					       new Insets(0, 0, 0, 0),
					       0, 0));

	messagingButton = new Button(strings.get(thisClass, "messaging"));
	messagingButton.setFont(XsmallFont);
	messagingButton.addActionListener(this);
	messagingButton.setEnabled(false);
	buttonPanel.add(messagingButton,
			new babylonConstraints(1, 1, 1, 1, 1.0, 1.0,
					       GridBagConstraints.WEST,
					       GridBagConstraints.HORIZONTAL,
					       new Insets(0, 0, 0, 0),
					       0, 0));

	chatRoomControlButton = new Button(strings.get(thisClass,
						       "chatroomcontrol"));
	chatRoomControlButton.setFont(XsmallFont);
	chatRoomControlButton.addActionListener(this);
	chatRoomControlButton.setEnabled(false);
	buttonPanel.add(chatRoomControlButton,
			new babylonConstraints(1, 2, 1, 1, 1.0, 1.0,
					       GridBagConstraints.WEST,
					       GridBagConstraints.HORIZONTAL,
					       new Insets(0, 0, 0, 0),
					       0, 0));

	pastePictureButton = new Button(strings.get(thisClass,
						    "pastepicture"));
	pastePictureButton.setFont(XsmallFont);
	pastePictureButton.addActionListener(this);
	buttonPanel.add(pastePictureButton,
			new babylonConstraints(2, 0, 1, 1, 1.0, 1.0,
					       GridBagConstraints.WEST,
					       GridBagConstraints.HORIZONTAL,
					       new Insets(0, 0, 0, 0),
					       0, 0));
**/
	saveTextButton = new Button(strings.get(thisClass, "savechat"));
	saveTextButton.setFont(XsmallFont);
	saveTextButton.addActionListener(this);
	buttonPanel.add(saveTextButton,
			//new babylonConstraints(2, 1, 1, 1, 1.0, 1.0,
			//new babylonConstraints(2, 0, 1, 1, 0.0, 0.0,
			new babylonConstraints(2, 0, 1, 1, 1.0, 0.0,
					       GridBagConstraints.WEST,
					       //GridBagConstraints.HORIZONTAL,
					       GridBagConstraints.BOTH,
					       new Insets(0, 0, 0, 0),
					       0, 0));
	if(thisApplet.role.equals("student"))
	{
		saveCanvasButton = new Button(strings.get(thisClass, "savecanvas"));
		saveCanvasButton.setFont(XsmallFont);
		saveCanvasButton.addActionListener(this);
		buttonPanel.add(saveCanvasButton,
				//new babylonConstraints(2, 2, 1, 1, 1.0, 1.0,
				new babylonConstraints(3, 0, 1, 1, 1.0, 0.0,
						       GridBagConstraints.WEST,
						       //GridBagConstraints.HORIZONTAL,
						       GridBagConstraints.BOTH,
						       new Insets(0, 0, 0, 0),
						       0, 0));
	}

	playRecordButton = new Button(strings.get(thisClass, "playRecordData"));
	playRecordButton.setFont(XsmallFont);
	playRecordButton.addActionListener(this);
			//new babylonConstraints(2, 2, 1, 1, 1.0, 1.0,
		if(thisApplet.role.equals("student"))
			buttonPanel.add(playRecordButton,
				new babylonConstraints(4, 0, 1, 1, 1.0, 0.0,
					       GridBagConstraints.WEST,
					       //GridBagConstraints.HORIZONTAL,
					       GridBagConstraints.BOTH,
					       new Insets(0, 0, 0, 0),
					       0, 0));
		else
			buttonPanel.add(playRecordButton,
				new babylonConstraints(3, 0, 1, 1, 1.0, 0.0,
					       GridBagConstraints.WEST,
					       //GridBagConstraints.HORIZONTAL,
					       GridBagConstraints.BOTH,
					       new Insets(0, 0, 0, 0),
					       0, 0));

	selectGroupFile = new Choice();
        selectGroupFile.setFont(smallFont);
        selectGroupFile.setEnabled(true);
        selectGroupFile.addItemListener(this);
	try {
		groupListVector1 = thisApplet.groupListVector;	
		selectGroupFile.addItem(strings.get(thisClass, "playchoice"));
		if(groupListVector1!=null && groupListVector1.size() >0){
			for(int i=0;i<groupListVector1.size();i++){
			        selectGroupFile.addItem(groupListVector1.get(i).toString());
			}
			groupListVector1.clear();
		}else {
			groupListVector1.clear();
		}
		
	}catch(Exception e){
		groupListVector1.clear();
		//System.out.println("------------------>"+e.getMessage());
	}	
	if(thisApplet.role.equals("student"))
       		//controlsPanel
		buttonPanel.add(selectGroupFile,
                //new babylonConstraints(4, 0, 5, 1, 0.0, 0.0,
                	new babylonConstraints(5, 0, 1, 1, 1.0, 1.0,
                                        GridBagConstraints.WEST,
                                        GridBagConstraints.BOTH,
                                        //new Insets(0, 0, 0, 1),
                                        new Insets(0, 0, 0, 0),
                                        0, 0));
	else
		buttonPanel.add(selectGroupFile,
                	new babylonConstraints(4, 0, 1, 1, 1.0, 1.0,
                                        GridBagConstraints.WEST,
                                        GridBagConstraints.BOTH,
                                        //new Insets(0, 0, 3, 0),
                                        //new Insets(0, 0, 0, 1),
                                        new Insets(0, 0, 0, 0),
                                        0, 0));
/**

	manualButton = new Button(strings.get(thisClass, "manual"));
	manualButton.setFont(XsmallFont);
	manualButton.addActionListener(this);
	buttonPanel.add(manualButton,
			//new babylonConstraints(3, 0, 1, 1, 1.0, 1.0,
			new babylonConstraints(5, 0, 1, 1, 1.0, 1.0,
					       GridBagConstraints.WEST,
					       GridBagConstraints.HORIZONTAL,
					       new Insets(0, 0, 0, 0),
					       0, 0));

	aboutButton = new Button(strings.get(thisClass, "about"));
	aboutButton.setFont(XsmallFont);
	aboutButton.addActionListener(this);
	buttonPanel.add(aboutButton,
			//new babylonConstraints(3, 1, 1, 1, 1.0, 1.0,
			new babylonConstraints(6, 0, 1, 1, 1.0, 1.0,
					       GridBagConstraints.WEST,
					       GridBagConstraints.HORIZONTAL,
					       new Insets(0, 0, 0, 0),
					       0, 0));
**/

	//add(buttonPanel, new babylonConstraints(0, 0, 2, 1, 1.0, 0.0,
	  //add(buttonPanel, new babylonConstraints(0, 0, 1, 1, 1.0, 0.0,
	  add(buttonPanel, new babylonConstraints(0, 0, 0, 0, 1.0, 0.0,
						GridBagConstraints.NORTHWEST,
						GridBagConstraints.HORIZONTAL,
						new Insets(2, 2, 0, 2),
						//new Insets(2, 2, 0, 1),
						0, 0));
	
	// The left content panel

	contentPanel = new Panel();
	contentPanel.setLayout(mainLayout);


	conferenceLabel = new Label(strings.get(thisClass, "conferencetext"));
	conferenceLabel.setFont(smallFont);
	contentPanel.add(conferenceLabel,
			 //new babylonConstraints(0, 1, 1, 1, 0.0, 0.0,
			 new babylonConstraints(0, 0, 1, 1, 0.0, 0.0,
						GridBagConstraints.NORTHWEST,
						GridBagConstraints.BOTH,
						new Insets(0, 0, 0, 0),
						0, 0));

	messagesArea = new TextArea("", 8, 19,
				TextArea.SCROLLBARS_VERTICAL_ONLY);
	messagesArea.setEditable(false);
	messagesArea.setFont(smallFont);
	contentPanel.add(messagesArea,
			 //new babylonConstraints(0, 2, 2, 1, 1.0, 1.0,
			 new babylonConstraints(0, 1, 1, 1, 0.0, 0.0,
						GridBagConstraints.NORTHWEST,
						GridBagConstraints.BOTH,
						new Insets(0, 0, 0, 0),
						0, 0));

	typedArea = new TextArea("", 2, 19,
				 TextArea.SCROLLBARS_VERTICAL_ONLY);
	typedArea.setEditable(true);
	typedArea.setFont(largeFont);
	typedArea.addKeyListener(this);
	contentPanel.add(typedArea,
			 //new babylonConstraints(0, 0, 2, 1, 1.0, 0.0,
			 new babylonConstraints(0, 2, 1, 1, 0.0, 0.0,
						GridBagConstraints.NORTHWEST,
						GridBagConstraints.BOTH,
						new Insets(0, 0, 0, 0),
						0, 0));
	add(contentPanel,
		//new babylonConstraints(0, 3, 1, 1, 0.0, 0.0, 
		new babylonConstraints(0, 2, 1, 1, 0.0, 0.0, 
						 GridBagConstraints.NORTHWEST,
						 //GridBagConstraints.BOTH,
						 GridBagConstraints.NONE,
						 //new Insets(0, 5, 5, 5),
						 new Insets(0, 2, 2, 2),
						 0, 0));
	canvasPanel = new Panel();
	canvasPanel.setLayout(mainLayout);
	
	drawCanvasLabel = new Label(strings.get(thisClass, "drawingcanvas"));
	canvasPanel.add(drawCanvasLabel,
			 //new babylonConstraints(0, 3, 1, 1, 0.0, 0.0,
			 //new babylonConstraints(1, 0, 1, 1, 0.0, 0.0,
			 new babylonConstraints(0, 0, 1, 1, 1.0, 0.0,
						GridBagConstraints.NORTHWEST,
						GridBagConstraints.BOTH,
						new Insets(0, 0, 0, 0),
						0, 0));
/**
	resizeButton = new Button("[" + strings.get(thisClass, "resize") +
				  "]");
	resizeButton.setFont(XsmallFont);
	resizeButton.setSize(5, 5);
	resizeButton.addMouseListener(this);
	resizeButton.addMouseMotionListener(this);
	contentPanel.add(resizeButton,
			 //new babylonConstraints(1, 3, 1, 1, 0.0, 0.0,
			 //new babylonConstraints(1, 1, 2, 1, 1.0, 0.0,
			 new babylonConstraints(2, 1, 2, 1, 1.0, 0.0,
						GridBagConstraints.NORTHEAST,
						GridBagConstraints.NONE,
						new Insets(0, 0, 0, 0),
						0, 0));

	tmpResizeButton = new Button("[" + strings.get(thisClass, "resize") +
				  "]");
	tmpResizeButton.setFont(XsmallFont);
	tmpResizeButton.setSize(resizeButton.getSize());
	contentPanel.add(tmpResizeButton,
			 //new babylonConstraints(1, 3, 1, 1, 0.0, 0.0,
			 //new babylonConstraints(1, 1, 2, 1, 1.0, 0.0,
			 new babylonConstraints(2, 1, 2, 1, 1.0, 0.0,
						GridBagConstraints.NORTHEAST,
						GridBagConstraints.NONE,
						new Insets(0, 0, 0, 0),
						0, 0));
**/
	canvas = new babylonCanvas(this);
	canvasPanel.add(canvas,
			 //new babylonConstraints(1, 2, 3, 3, 3.0, 3.0,
			 //new babylonConstraints(0, 1, 4, 5, 2.0, 2.0,
			 new babylonConstraints(0, 1, 0, 0, 2.0, 2.0,
						GridBagConstraints.NORTHWEST,
						GridBagConstraints.BOTH,
						new Insets(0, 0, 0, 0),
						0, 0));

	//add(contentPanel, new babylonConstraints(0, 1, 1, 2, 3.0, 1.0,
	add(canvasPanel,new babylonConstraints(1, 1, 2, 5, 2.0, 2.0, 
						 GridBagConstraints.NORTHWEST,
						 GridBagConstraints.BOTH,
						 //new Insets(5, 5, 5, 0),
						 //new Insets(1, 5, 5, 0),
						 new Insets(1, 1, 1, 0),
						 0, 0));

	// The right controls panel

	controlsPanel = new Panel();
	controlsPanel.setLayout(mainLayout);

	nameLabel = new Label(name);
	nameLabel.setFont(largeBoldFont);
	controlsPanel.add(nameLabel,
			  //new babylonConstraints(0, 0, 1, 1, 0.0, 0.0,
			  new babylonConstraints(0, 3, 1, 1, 0.0, 0.0,
						 GridBagConstraints.NORTHWEST,
						 GridBagConstraints.BOTH,
						 new Insets(0, 0, 0, 0),
						 0, 0));

	activityField = new TextField(19);
	activityField.setEditable(false);
	activityField.setFont(smallFont);
	controlsPanel
	    .add(activityField,
		 //new babylonConstraints(0, 1, 1, 1, 1.0, 1.0,
			 new babylonConstraints(0, 4, 5, 1, 0.0, 0.0,
						GridBagConstraints.NORTHWEST,
						GridBagConstraints.HORIZONTAL,
						new Insets(0, 0, 0, 0),
						0, 0));

	sendToLabel = new Label(strings.get(thisClass, "sendingto"));
	sendToLabel.setFont(smallFont);
	controlsPanel.add(sendToLabel,
			  //new babylonConstraints(0, 2, 1, 1, 0.0, 0.0,
			  new babylonConstraints(0, 5, 1, 1, 0.0, 0.0,
						 GridBagConstraints.NORTHWEST,
						 GridBagConstraints.BOTH,
						 new Insets(0, 0, 0, 0),
						 0, 0));

	sendToList = new java.awt.List(4);
	sendToList.setFont(XsmallFont);
	sendToList.addItemListener(this);
	sendToList.setMultipleMode(true);
	controlsPanel.add(sendToList,
			  //new babylonConstraints(0, 3, 1, 1, 1.0, 1.0,
			  //new babylonConstraints(0, 6, 5, 1, 2.0, 1.0,
			  new babylonConstraints(0, 6, 5, 1, 0.0, 0.0,
						 GridBagConstraints.NORTHWEST,
						 GridBagConstraints.BOTH,
						 new Insets(0, 0, 0, 0),
						 0, 0));

	sendToAllCheckbox = new Checkbox(strings.get(thisClass, "sendtoall"),
					 true);
	sendToAllCheckbox.setFont(XsmallFont);
	sendToAllCheckbox.addItemListener(this);
	controlsPanel.add(sendToAllCheckbox,
			  //new babylonConstraints(0, 4, 1, 1, 0.0, 0.0,
			  new babylonConstraints(0, 7, 5, 1, 0.0, 0.0,
						 GridBagConstraints.NORTHWEST,
						 GridBagConstraints.NONE,
						 new Insets(0, 0, 0, 0),
						 0, 0));

	userInfoButton = new Button(strings.get(thisClass, "userinfo"));
	userInfoButton.setFont(XsmallFont);
	userInfoButton.addActionListener(this);
	userInfoButton.setEnabled(false);
	controlsPanel.add(userInfoButton,
			 //new babylonConstraints(0, 5, 1, 1, 1.0, 0.0,
			 new babylonConstraints(0, 8, 5, 1, 0.0, 0.0,
					GridBagConstraints.NORTHWEST,
					GridBagConstraints.HORIZONTAL,
					new Insets(0, 0, 0, 0),
					0, 0));

	pageButton = new Button(strings.get(thisClass, "pageusers"));
	pageButton.setFont(XsmallFont);
	pageButton.addActionListener(this);
	pageButton.setEnabled(false);
	controlsPanel
	    .add(pageButton,
		 //new babylonConstraints(0, 6, 1, 1, 1.0, 0.0,
		 new babylonConstraints(0, 9, 5, 1, 0.0, 0.0,
					GridBagConstraints.NORTHWEST,
					GridBagConstraints.HORIZONTAL,
					new Insets(0, 0, 0, 0),
					0, 0));

	ignoreButton = new Button(strings.get(thisClass, "ignoreusers"));
	ignoreButton.setFont(XsmallFont);
	ignoreButton.addActionListener(this);
	controlsPanel
	    .add(ignoreButton,
		 //new babylonConstraints(0, 7, 1, 1, 1.0, 0.0,
		 new babylonConstraints(0, 10, 5, 1, 0.0, 0.0,
					GridBagConstraints.NORTHWEST,
					GridBagConstraints.HORIZONTAL,
					new Insets(0, 0, 0, 0),
					0, 0));
/**
	add(buttonPanel, new babylonConstraints(0, 0, 1, 1, 1.0, 0.0,
	add(contentPanel, new babylonConstraints(0, 1, 1, 2, 3.0, 1.0,
	add(controlsPanel,
	    new babylonConstraints(1, 1, 1, 1, 0.0, 1.0,
				   GridBagConstraints.NORTHWEST,
				   GridBagConstraints.VERTICAL,
				   new Insets(0, 5, 0, 5),
				   0, 0));
                                                 GridBagConstraints.NORTHWEST,
                                                 GridBagConstraints.BOTH,
                                                 new Insets(5, 5, 5, 0),
                                                 new Insets(5, 5, 0, 5),
	add(controlsPanel,
	    //new babylonConstraints(0, 1, 1, 1, 0.0, 0.0,
	    new babylonConstraints(0, 1, 5, 1, 1.0, 1.0,
				   GridBagConstraints.NORTHWEST,
				   GridBagConstraints.VERTICAL,
				   new Insets(0, 5, 0, 5),
                                                 0, 0));

**/
	// The drawing controls panel	

	//drawingControlsPanel = new Panel();
	//drawingControlsPanel.setLayout(mainLayout);

	drawingControlsLabel = new Label(strings.get(thisClass,
						     "drawingcontrols"));
	drawingControlsLabel.setFont(smallFont);
	//drawingControlsPanel
	controlsPanel
	    .add(drawingControlsLabel,
		 //new babylonConstraints(0, 0, 2, 1, 0.0, 0.0,
		 new babylonConstraints(0, 11, 1, 1, 0.0, 0.0,
					GridBagConstraints.NORTHWEST,
					GridBagConstraints.BOTH,
					new Insets(0, 0, 0, 0),
					0, 0));

	drawTypeGroup = new CheckboxGroup();

	freehandCheckbox = new Checkbox(strings.get(thisClass, "freehand"),
					drawTypeGroup, true);
	freehandCheckbox.setFont(smallFont);
	freehandCheckbox.addItemListener(this);
	//drawingControlsPanel
	controlsPanel
	    .add(freehandCheckbox,
		 new babylonConstraints(0, 12, 5, 1, 0.0, 0.0,
					GridBagConstraints.NORTHWEST,
					GridBagConstraints.NONE,
					new Insets(0, 0, 0, 0),
					0, 0));

	lineCheckbox = new Checkbox(strings.get(thisClass, "line"),
				    drawTypeGroup, false);
	lineCheckbox.setFont(smallFont);
	lineCheckbox.addItemListener(this);
	//drawingControlsPanel
	controlsPanel
	    .add(lineCheckbox,
		 //new babylonConstraints(1, 1, 1, 1, 1.0, 1.0,
		 new babylonConstraints(1, 12, 1, 1, 0.0, 0.0,
					GridBagConstraints.NORTHWEST,
					GridBagConstraints.NONE,
					new Insets(0, 0, 0, 0),
					0, 0));

	rectangleCheckbox = new Checkbox(strings.get(thisClass, "rectangle"),
					 drawTypeGroup, false);
	rectangleCheckbox.setFont(smallFont);
	rectangleCheckbox.addItemListener(this);
	//drawingControlsPanel
	controlsPanel
	    .add(rectangleCheckbox,
		 new babylonConstraints(0, 13, 5, 1, 0.0, 0.0,
					GridBagConstraints.NORTHWEST,
					GridBagConstraints.NONE,
					new Insets(0, 0, 0, 0),
					0, 0));

	ovalCheckbox = new Checkbox(strings.get(thisClass, "oval"),
				    drawTypeGroup, false);
	ovalCheckbox.setFont(smallFont);
	ovalCheckbox.addItemListener(this);
	//drawingControlsPanel
	controlsPanel
	    .add(ovalCheckbox,
		 //new babylonConstraints(1, 2, 1, 1, 1.0, 1.0,
		 new babylonConstraints(1, 13, 1, 1, 0.0, 0.0,
					GridBagConstraints.NORTHWEST,
					GridBagConstraints.NONE,
					new Insets(0, 0, 0, 0),
					0, 0));

	textCheckbox = new Checkbox(strings.get(thisClass, "text"),
				    drawTypeGroup, false);
	textCheckbox.setFont(smallFont);
	textCheckbox.addItemListener(this);
	//drawingControlsPanel
	controlsPanel
	    .add(textCheckbox,
		 new babylonConstraints(0, 14, 5, 1, 0.0, 0.0,
					GridBagConstraints.NORTHWEST,
					GridBagConstraints.NONE,
					new Insets(0, 0, 0, 0),
					0, 0));

	eraseCheckbox = new Checkbox(strings.get(thisClass, "erase"),
				     drawTypeGroup, true);
	eraseCheckbox.setFont(smallFont);
	eraseCheckbox.addItemListener(this);
	//drawingControlsPanel
	controlsPanel
	    .add(eraseCheckbox,
		 //new babylonConstraints(1, 3, 1, 1, 1.0, 1.0,	
		 new babylonConstraints(1, 14, 1, 1, 0.0, 0.0,	
					GridBagConstraints.NORTHWEST,
					GridBagConstraints.NONE,
					new Insets(0, 0, 0, 0),
					0, 0));
            
	colorChoice = new Choice();
	colorChoice.setFont(smallFont);
	colorChoice.addItemListener(this);
	String[] colors =
	    new String[] { strings.get(thisClass, "black"),
			   strings.get(thisClass, "blue"),
			   strings.get(thisClass, "cyan"),
			   strings.get(thisClass, "darkgray"),
			   strings.get(thisClass, "gray"),
			   strings.get(thisClass, "green"),
			   strings.get(thisClass, "lightgray"),
			   strings.get(thisClass, "magenta"),
			   strings.get(thisClass, "orange"),
			   strings.get(thisClass, "pink"),
			   strings.get(thisClass, "red"),
			   strings.get(thisClass, "white"),
			   strings.get(thisClass, "yellow") };
	for (int count = 0; count < colors.length; count ++)
	    colorChoice.addItem(colors[count]);
	//drawingControlsPanel
	controlsPanel
	    .add(colorChoice,
		 //new babylonConstraints(0, 4, 2, 1, 1.0, 1.0,
		 new babylonConstraints(0, 15, 5, 1, 0.0, 0.0,
					GridBagConstraints.NORTHWEST,
					GridBagConstraints.BOTH,
					new Insets(0, 0, 3, 0),
					0, 0));

	thicknessChoice = new Choice();
	thicknessChoice.setFont(smallFont);
	thicknessChoice.addItemListener(this);
	for (int count = 1; count < 10; count ++)
	    thicknessChoice.addItem(strings.get(thisClass, "thickness") +
				    " " + count);
	//drawingControlsPanel
	controlsPanel
	    .add(thicknessChoice,
		 //new babylonConstraints(0, 5, 2, 1, 1.0, 1.0,
		 new babylonConstraints(0, 16, 5, 1, 0.0, 0.0,
					GridBagConstraints.NORTHWEST,
					GridBagConstraints.BOTH,
					new Insets(0, 0, 3, 0),
					0, 0));

	fillTypeChoice = new Choice();
	fillTypeChoice.setFont(smallFont);
	fillTypeChoice.setEnabled(false);
	fillTypeChoice.addItemListener(this);
	fillTypeChoice.addItem(strings.get(thisClass, "outlined"));
	fillTypeChoice.addItem(strings.get(thisClass, "filled"));
	//drawingControlsPanel
	controlsPanel.add(fillTypeChoice,
		 //new babylonConstraints(0, 6, 2, 1, 1.0, 1.0,
		 new babylonConstraints(0, 17, 5, 1, 0.0, 0.0,
					GridBagConstraints.NORTHWEST,
					GridBagConstraints.BOTH,
					new Insets(0, 0, 3, 0),
					0, 0));

	clearCanvasButton = new Button(strings.get(thisClass, "clearcanvas"));
	clearCanvasButton.setFont(smallFont);
	clearCanvasButton.addActionListener(this);
	//drawingControlsPanel
	controlsPanel
	    .add(clearCanvasButton,
		 //new babylonConstraints(0, 7, 2, 1, 1.0, 1.0,
		 new babylonConstraints(0, 18, 5, 1, 0.0, 0.0,
					GridBagConstraints.NORTHWEST,
					GridBagConstraints.HORIZONTAL,
					new Insets(0, 0, 0, 0),
					0, 0));

	//add(drawingControlsPanel,
	    //new babylonConstraints(1, 2, 1, 1, 0.0, 1.0,
	add(controlsPanel,
	    new babylonConstraints(0, 1, 5, 1, 0.0, 0.0,
				   GridBagConstraints.NORTHWEST,
				   GridBagConstraints.NONE,
				   new Insets(0, 5, 5, 5),
				   0, 0));

	// register to receive the various events
	addKeyListener(this);
	addMouseListener(this);
	
	// Show the drawing canvas or not?
	showCanvas(isCanvas);

	setVisible(true);
	offline();
	typedArea.requestFocus();

	// Create our password encryptor and excrupt our password, if
	// applicable
	passwordEncryptor = new babylonPasswordEncryptor();
	encryptedPassword = passwordEncryptor.encryptPassword(plainPassword);
    }
    
    public void setParent(Component comp)
    {
	// What is our parent frame?  Thanks to Paul Wheaton for posting the
	// example
        synchronized(comp.getTreeLock())
	    {
		while((comp != null) && !(comp instanceof Frame))
		    comp = comp.getParent();
	    }
        parentWindow = (Frame) comp;
    }

    public void setTitle(String title)
    {
	if (thisApplet == null)
	    parentWindow.setTitle(title);
	else
	    thisApplet.setTitle(title);
    }

    public void online()
    {
	connected = true;
	connectButton.setEnabled(false);
	disconnectButton.setEnabled(true);
	saveTextButton.setEnabled(true);
	//chatRoomsButton.setEnabled(true);
	pageButton.setEnabled(true);
	//messagingButton.setEnabled(true);
	if (adminConsole)
	    roomOwner(true);
	setTitle(strings.get(thisClass, "babylonversion") + babylon.VERSION +
		 " - " + strings.get(thisClass, "onlineat") + " " + host);
	return;
    }

    public void offline()
    {
	connected = false;
	connectButton.setEnabled(true);
	saveTextButton.setEnabled(true);
	disconnectButton.setEnabled(false);
	//chatRoomsButton.setEnabled(false);
	pageButton.setEnabled(false);
	ignoreButton.setEnabled(false);
	userInfoButton.setEnabled(false);
	//messagingButton.setEnabled(false);
	freehandCheckbox.setState(true);
	lineCheckbox.setState(false);
	ovalCheckbox.setState(false);
	rectangleCheckbox.setState(false);
	textCheckbox.setState(false);
	eraseCheckbox.setState(false);
	if (sendToList.getItemCount() > 0)
	    sendToList.removeAll();
	sendToAllCheckbox.setState(true);
	currentRoom = new babylonRoomInfo();
	roomOwner(false);
	setTitle(strings.get(thisClass, "babylonversion") + babylon.VERSION +
		 " - " + strings.get(thisClass, "offline"));
	return;
    }

    protected boolean haveRequiredInformation()
    {
	// Do we have everything we need to connect to the server?
	if (name.equals("") ||
	    (requirePassword && encryptedPassword.equals("")) ||
	    host.equals("") ||
	    port.equals(""))
	    return (false);
	else
	    return (true);
    }

    protected void connect()
    {
	// Have all the settings been entered?
	if (!haveRequiredInformation())
	    {
		// Give the user a chance to enter it
		new babylonSettingsDialog(this);
		
		// Do we have it NOW?
		if (!haveRequiredInformation())
		    {
			new babylonInfoDialog(parentWindow,
			      strings.get(thisClass, "canceled"),
			      true, strings.get(thisClass, "missinginfo"),
			      strings.get("ok"));
			return;
		    }
	    }
	
	canvas.clear();
	synchronized (messagesArea)
	    {
		messagesArea.setText("");
	    }

	// open up my socket
	try {
	    portNumber = Integer.parseInt(port);
	}
	catch (NumberFormatException n) {;}

	try {
	        client = new babylonClient(host, name, portNumber, this);
	}
	catch (UnknownHostException a) {
	    new babylonInfoDialog(parentWindow,
				  strings.get(thisClass, "couldntconnect"),
				  true,
				  strings.get(thisClass, "nofindserver") +
				  " " + host,
				  strings.get("ok"));
	    return;
	}
	catch (IOException b) {
	    new babylonInfoDialog(parentWindow,
				  strings.get(thisClass, "couldntconnect"),
				  true,
				  strings.get(thisClass, "noserveronport") +
				  " " + portNumber + " " + 
				  strings.get(thisClass, "onserver") + " " +
				  host,
				  strings.get("ok"));
	    return;
	}
	catch (Exception c)
	    {
		// Hmm, some other connection error.  Try to make the error
		// dialog helpful at least for debugging
		
		String errorString = strings.get(thisClass, "javaexception") +
		    "\n";
		errorString += c.toString() + "\n";
		
		// Is this an applet?
		if (thisApplet != null)
		    errorString += "\n" +
			strings.get(thisClass, "unsignednote") + "\n";

		errorString += "\n" + strings.get(thisClass, "bugreports") +
		    "\n";

		new babylonTextDialog(parentWindow,
				      strings.get(thisClass,
						  "couldntconnect"),
				      errorString, 60, 15,
				      TextArea.SCROLLBARS_VERTICAL_ONLY,
				      true, strings.get("dismiss"));
		return;
	    }
	
	online();
	return;
    }

    protected synchronized void disconnect()
    {
	// Tell the server
	try {
	    client.stop = true;
	    client.sendDisconnect();
	    client.shutdown(false);
	}
	catch (IOException e) {
	    // It's ok
	}
	
	offline();
	return;
    }

    protected void setApplet(babylonApplet ap)
    {
	// This should get called when we're being used as an applet
	
	// Save a reference to the applet
	thisApplet = ap;

	// Disable features that aren't supported for applets
// This is  true for testing which is doing by nagendra so it is commented
	//pastePictureButton.setEnabled(true);
	saveTextButton.setEnabled(true);

	if(thisApplet.role.equals("student")	)
		saveCanvasButton.setEnabled(true);

	playRecordButton.setEnabled(true);
    }

    protected void roomOwner(boolean owner)
    {
	if (owner)
	    {
		if (currentRoom != null)
		    currentRoom.roomOwner = true;
		//chatRoomControlButton.setEnabled(true);
	    }

	else if (!adminConsole)
	    {
		if (currentRoom != null)
		    currentRoom.roomOwner = false;
		//chatRoomControlButton.setEnabled(false);
		if (roomControlDialog != null)
		    {
			roomControlDialog.dispose();
			roomControlDialog = null;
		    }
	    }
    }

    protected void showCanvas(boolean state)
    {
	drawCanvasLabel.setVisible(state);
	//resizeButton.setVisible(state);
	//tmpResizeButton.setVisible(state);
	canvas.setVisible(state);
	drawingControlsLabel.setVisible(state);
	clearCanvasButton.setVisible(state);
	colorChoice.setVisible(state);
	thicknessChoice.setVisible(state);
	fillTypeChoice.setVisible(state);
	freehandCheckbox.setVisible(state);
	lineCheckbox.setVisible(state);
	ovalCheckbox.setVisible(state);
	rectangleCheckbox.setVisible(state);
	textCheckbox.setVisible(state);
   	eraseCheckbox.setVisible(state);
			
	if (state)
	    {
		setSize((getSize()).width + 1,
			(getSize()).height + 1);
	    }
	else
	    {
		messagesArea.setSize((messagesArea.getSize()).width, 
				 (messagesArea.getSize()).height);
		setSize((getSize()).width + 1, 
			(getSize()).height + 1);
	    }
	
	parentWindow.pack();
	return;
    }

    protected void showUserInfo()
    {
	// This will show info about another user in a text dialog
	
	String message = "";
	String[] selectedUsers;

	selectedUsers = sendToList.getSelectedItems();

	// Loop for each user that's selected
	for (int count1 = 0; count1 < selectedUsers.length; count1 ++)
	    {
		// Find this user in our user list
		for (int count2 = 0; count2 < client.userList.size();
		     count2++)
		    {
			babylonUser tmp = (babylonUser) client.userList
			    .elementAt(count2);

			if (selectedUsers[count1].equals(tmp.name))
			    {
				// Here's one.
				message = strings.get(thisClass,
						      "loginname") +
				    "\t" + tmp.name + "\n" +
				    strings.get(thisClass, "language") +
				    "\t" + (new Locale(tmp.language, "US"))
				    .getDisplayLanguage() + "\n\n" +
				    strings.get(thisClass, "additionalinfo") +
				    "\n" + tmp.additional;

				new babylonTextDialog(parentWindow,
				      strings.get(thisClass, "userinfofor") +
				      " " + tmp.name, 
				      message, 40, 10,
				      TextArea.SCROLLBARS_VERTICAL_ONLY,
				      false, strings.get("dismiss"));
			    }
		    }
	    }
    }

    protected void pastePictureFile()
    {
	File pictureFile = null;
	
	FileDialog getPictureDialog =
	    new FileDialog(parentWindow,
			   strings.get(thisClass, "pastepicture"), 
			   FileDialog.LOAD);
	getPictureDialog.show();
	
	if (getPictureDialog.getFile() == null)
	    // Nothing was selected
	    return;

	pictureFile = new File(getPictureDialog.getDirectory() +
			       getPictureDialog.getFile());
	
	if (pictureFile == null)
	    {
		// ???
		new babylonInfoDialog(parentWindow,
				      strings.get(thisClass, "failed"), true,
				      strings.get(thisClass, "cantopen"),
				      strings.get("ok"));
		return;
	    }
	
	// Are we allowed to read this file?
	if (!pictureFile.canRead())
	    {
		// Not allowed to read the file
		new babylonInfoDialog(parentWindow,
				      strings.get(thisClass,
						  "permissiondenied"), true,
				      strings.get(thisClass,
						  "noreadpermission"),
				      strings.get("ok"));
		return;
	    }
	
	canvas.floatPicture(pictureFile);
	return;
    }

    protected void saveText() 
    {
	//+ +Save the chat text as a file.
	File textFile = null;
	FileOutputStream fileStream = null;

	// Fire up a file dialog to let the user choose the file location
	FileDialog saveTextDialog =new FileDialog(parentWindow, strings.get(thisClass, "savechat"),FileDialog.SAVE);
	saveTextDialog.show();

	if (saveTextDialog.getFile() == null)
	    // Nothing was selected
	    return;

	// Try to create the file
	try {
    		textFile = new File(saveTextDialog.getDirectory() +
		       saveTextDialog.getFile());
		fileStream = new FileOutputStream(textFile);
		//byte[] bytes = messagesArea.getText().getBytes();
		//System.out.println("textStr"+textStr);
		fileStream.write(textStr.getBytes());
	} 
	catch (IOException F) { 
		new babylonInfoDialog(parentWindow,
			  strings.get(thisClass, "failed"), true,
			  strings.get(thisClass, "cantwrite"),
			  strings.get("ok"));
		    return;
	}
////////////////////////
/**
	try
	{
		Robot robot = new Robot();
		BufferedImage screenShot = robot.createScreenCapture(new
	        Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
		System.out.println("This is babylon Panel.java"+babylonClientSocket.user.name);
		ImageIO.write(screenShot, "JPG", new File("screenShot.jpg"));
	}
	catch(Exception e) 
	{
		new babylonInfoDialog(parentWindow,
		strings.get(thisClass, "failed"), true,
		strings.get(thisClass, "cantwrite"),
		strings.get("ok"));
		return;
	
	}
**/			
//////////////////////
    }

    protected void saveCanvas()
    {
	// Saves the canvas as an image file.
	
	// Fire up a file dialog to let the user choose the file location
	FileDialog saveCanvasDialog =
	    new FileDialog(parentWindow, strings.get(thisClass, "savecanvas"),
			   FileDialog.SAVE);
	saveCanvasDialog.show();

	if (saveCanvasDialog.getFile() == null)
	    // Nothing was selected
	    return;

	// Create an object to process the image data
	babylonBmpIO writer = new babylonBmpIO();

	// Get the contents of the canvas
	Image canvasImage = canvas.getContents();
	    
	try {
	    writer.writeBmp(canvasImage, canvasImage.getWidth(canvas),
			    canvasImage.getHeight(canvas),
			    saveCanvasDialog.getDirectory() +
			    saveCanvasDialog.getFile());
	}
	catch (Exception e) {
	    new babylonInfoDialog(parentWindow,
				  strings.get(thisClass, "cantwrite"), false,
				  e.toString(),
				  strings.get("ok"));
	    e.printStackTrace();
	    return;
	}
    }

    public static void centerDialogOnScreen(Dialog dialog)
    {
	Dimension size = dialog.getSize();
	Dimension screenSize = dialog.getToolkit().getScreenSize();
	dialog.setLocation((((screenSize.width - size.width) / 2)),
			   (((screenSize.height - size.height) / 2)));
    }

    public static void centerDialog(Frame frame, Dialog dialog)
    {
	// Center dialogs on any Frame

	Dimension size = dialog.getSize();
	Dimension parentSize = frame.getSize();
	Point parentLocation = frame.getLocationOnScreen();
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

    public void centerDialog(Dialog dialog)
    {
	// For centering dialogs on this panel
	
	// If we are an embedded applet, center the dialog on the screen
	// independent of the location of the applet itself
	if ((thisApplet != null) && thisApplet.embed)
	    centerDialogOnScreen(dialog);
	else
	    centerDialog(parentWindow, dialog);
    }

    public void actionPerformed(ActionEvent E)
    {
	if (E.getSource() == connectButton)
	    {
		connect();
		return;
	    }

	if (E.getSource() == disconnectButton)
	    {
		disconnect();
		return;
	    }
    
	if (E.getSource() == pastePictureButton)
	    {
		pastePictureFile();
		return;
	    }

	if (E.getSource() == saveTextButton)
	    {
		//Commented by shaista
		if (connected)
                	try {
				if(thisApplet.role.equals("instructor"))
	                		client.receiveSaveChat();
				else
				{
					textStr = messagesArea.getText();
					if ( textStr.startsWith("Welcome to Babylon Chat") && textStr.endsWith(">>\n")){
							new babylonInfoDialog(parentWindow,
			 				strings.get(thisClass, "noDataSaving"),true,
                		                      	strings.get(thisClass,"noDataToSave"),
                        		              	strings.get("ok"));
					}
					else{
					/**
						FileOutputStream fileStream = null;
				                JFileChooser c = new JFileChooser();
                				int rVal = c.showSaveDialog(parentWindow);
                				if (rVal == JFileChooser.APPROVE_OPTION) {
                        				try {
	                                			File file = c.getSelectedFile();
	        	                        		fileStream = new FileOutputStream(file);	
        	        	                		System.out.println("------------> textStr "+textStr);
                	        	        		byte[] bytes = textStr.getBytes();
                        	        			fileStream.write(bytes);
                       					} catch (IOException F) {
                                				new babylonInfoDialog(parentWindow,
                                  				strings.get(thisClass, "failed"), true,
                                  				strings.get(thisClass, "cantwrite"),
	                                  			strings.get("ok"));
        	                			}
                	        			return;
	                			}
						if (rVal == JFileChooser.CANCEL_OPTION) {	
                	        			return;
                				}
					**/
						saveText();
					}
				}
				textStr ="";
			}
	               	catch (IOException e) {
                		client.lostConnection();
	        		return;
                   	 }

		return;
	    }
    
	if (E.getSource() == saveCanvasButton)
	    {
		if (connected)
			try {
				saveCanvas();
			}
			catch (Exception e) {
                        	client.lostConnection();
	                        return;
			}
		return;
	    }
	if (E.getSource() == playRecordButton)
	{
		/**  MINE CODE **/
		if (connected)
			try {
				if(choice != null &&  !(selectGroupFile.getSelectedItem()).equals("Select File"))
					client.sendPlayRecorder(choice);
				else{
					new babylonInfoDialog(parentWindow, 
					strings.get(thisClass, "fileNotSelected"), true, 
					strings.get(thisClass, "fileNotSelected")+strings.get(thisClass,"toPlay"), 
					strings.get("ok"));
		        	        return;
				}
			}
			catch (Exception e) {
                        	client.lostConnection();
	                        return;
			}
		return;
	    }

	// The 'ignore user(s)' button
	if (E.getSource() == ignoreButton)
	    {
		client.addToIgnored();
		return;
	    }

	// the 'settings' button
	if (E.getSource() == settingsButton)
	    {
		new babylonSettingsDialog(this);
		return;
	    }

	// the 'chat room control panel' button
	if (E.getSource() == chatRoomControlButton)
	    {
		if (roomControlDialog != null)
		    roomControlDialog.dispose();
		roomControlDialog = new babylonRoomControlDialog(this);
		return;
	    }

	if (E.getSource() == manualButton)
	    {
		try {
		    URL manualURL = new URL(babylonURL.getProtocol(),
				    babylonURL.getHost(),
				    babylonURL.getFile() + "MANUAL.TXT");

		    BufferedReader in = new BufferedReader(
                                        new InputStreamReader(
					manualURL.openStream()));

		    String inputLine = new String("");
		    String input = new String("");

		    while ((inputLine = in.readLine()) != null) 
			input = input.concat(inputLine + "\n");
		    in.close();

		    new babylonTextDialog(parentWindow,
					  strings.get(thisClass,
						      "onlinemanual"), 
					  input, 65, 25,
					  TextArea.SCROLLBARS_VERTICAL_ONLY,
					  false, strings.get("dismiss"));
		    return;
		}
		catch (IOException G) {
		    new babylonInfoDialog(parentWindow,
					  strings.get(thisClass, "nodata"), 
					  true,
					  strings.get(thisClass, "nomanual"),
					  strings.get("ok"));
		    return;
		}
	    }

	if (E.getSource() == aboutButton)
	    {
		String aboutTitle = strings.get(thisClass, "aboutbabylon");

		String aboutText = strings.get(thisClass, "babylonversion") +
		    babylon.VERSION + "\n" +
		    strings.get(thisClass, "aboutstring");

		if (!strings.getTranslator().equals(""))
		    aboutText += "\n\n" + strings.getTranslator();
		
		new babylonTextDialog(parentWindow, aboutTitle,
				      aboutText, 60, 25,
				      TextArea.SCROLLBARS_VERTICAL_ONLY,
				      false, strings.get("dismiss"));
		return;
	    }

	// the 'who is' button
	if (E.getSource() == userInfoButton)
	    {
		showUserInfo();
		return;
	    }

	// the 'page users' button
	if (E.getSource() == pageButton)
	    {
		try {
		    client.sendPageUser();
		}
		catch (IOException e) {
		    client.lostConnection();
		    return;
		}
		return;
	    }

	// the 'chat rooms' button
	if (E.getSource() == chatRoomsButton)
	    {
		if (roomsDialog != null)
		    roomsDialog.dispose();
		roomsDialog = new babylonRoomsDialog(this);
		return;
	    }

	// the 'messaging' button
	if (E.getSource() == messagingButton)
	    {
		new babylonMessagingDialog(this);
		return;
	    }

	// the 'clear canvas button'
	if (E.getSource() == clearCanvasButton)
	    {
		canvas.clear();
		if (connected)
		    try {
			client.sendClearCanv();
		    }
		    catch (IOException e) {
			client.lostConnection();
			return;
		    }
		return;
	    }
    }

    public void keyPressed(KeyEvent E)
    {
    }

    public void keyReleased(KeyEvent E)
    {
	if (E.getSource() == typedArea)
	    {
		// the 'enter' key in the send text field
		if (E.getKeyCode() == E.VK_ENTER) 
		    {
			// Is this a private communication?
			if (!sendToAllCheckbox.getState())
			    {
				String wholist[];

				messagesArea
				    .append("*" + strings.get(thisClass,
							      "privateto") +
					    " ");
				wholist = sendToList.getSelectedItems();
				for (int count = 0; count < wholist.length;
				     count ++)
				    {
					messagesArea.append(wholist[count]);
					if (count < (wholist.length - 1))
					    messagesArea.append(", ");
				    }
				messagesArea.append("*: ");
			    }
			else
			    messagesArea.append(name + ": ");

			// Print the rest.
			messagesArea.append(typedArea.getText());

			if (connected == true)
			    try {
				// Send it.
				client.sendChatText(typedArea.getText());
			    }
			    catch (IOException e) {
				client.lostConnection();
				return;
			    }

			// Empty the typing and activity fields
			typedArea.setText("");
			activityField.setText("");
			return;
		    }
		else
		    {
			String typingString =
			    strings.get(thisClass, "typing") + " " + name;
			if (!activityField.getText().equals(typingString))
			    activityField.setText(typingString);

			if (connected == true)
			    try {
				// Send a message to indicate that our user is
				// busy typing something
				client.sendActivity(babylonCommand
						       .ACTIVITY_TYPING);
			    }
			    catch (IOException e) {
				client.lostConnection();
				return;
			    }
			return;
		    }
	    }
    }

    public void keyTyped(KeyEvent E)
    {
    }   

    public void mouseClicked(MouseEvent E)
    {
    }   

    public void mouseEntered(MouseEvent E)
    {
    }   

    public void mouseExited(MouseEvent E)
    {
    }   

    public void mousePressed(MouseEvent E)
    {
	if (E.getSource() == resizeButton)
	    {
		// The user is resizing the text/canvas ratio
		oldResizePosition = E.getY();
	    }
    }

    public void mouseReleased(MouseEvent E)
    {
	if (E.getSource() == resizeButton)
	    {
		// Make the real resize button catch up with the rest of the
		// apparatus after the resize operation
		//resizeButton.setLocation(tmpResizeButton.getLocation().x,
		//			 tmpResizeButton.getLocation().y);
	    }
    }   

    public void mouseDragged(MouseEvent E)
    {

/**

	if (E.getSource() == resizeButton)
	    {
		int positionChange;

		// The user is resizing the text/canvas ratio
		newResizePosition = E.getY();

		if (messagesArea.getSize().height <= 0)

		    // If they hit the top, stop
		    positionChange = 1;
		else if (canvas.getSize().height <= 0)
		    // If they hit the bottom, stop
		    positionChange = -1;
		else
		    positionChange = (newResizePosition - oldResizePosition);
		
		messagesArea.setSize(messagesArea.getSize().width,
				     (messagesArea.getSize().height + 
				      positionChange));
		drawCanvasLabel.setLocation(drawCanvasLabel.getLocation().x,
					    (drawCanvasLabel.getLocation().y +
					     positionChange));
		//tmpResizeButton.setLocation(tmpResizeButton.getLocation().x,
		//			    (tmpResizeButton.getLocation().y +
					     positionChange));
		canvas.setLocation(canvas.getLocation().x,
				   (canvas.getLocation().y +
				    positionChange));
		canvas.setSize(canvas.getSize().width,
			       (canvas.getSize().height - 
				positionChange));

		oldResizePosition = newResizePosition;
	    }

**/
    }

    public void mouseMoved(MouseEvent E)
    {
    }

    public void itemStateChanged(ItemEvent E)
    {
	// The 'sendToAll' checkbox
	if (E.getSource() == sendToAllCheckbox)
	    {
		// If 'send to all' is selected, we should deselect all
		// the items in the sendTo list.
		if (sendToAllCheckbox.getState())
		    {
			int items = sendToList.getRows();
			for (int count = 0; count < items; count ++)
			    sendToList.deselect(count);
			ignoreButton.setEnabled(false);
			userInfoButton.setEnabled(false);
		    }

		// Also make sure that this checkbox is selected if
		// nothing is selected in the sendTo window
		else
		    {
			if (sendToList.getSelectedItems().length == 0)
			    {
				sendToAllCheckbox.setState(true);
				ignoreButton.setEnabled(false);
				userInfoButton.setEnabled(false);
			    }
		    }
	    }

	// the 'sendTo' window
	if (E.getSource() == sendToList)
	    {
		if (sendToList.getSelectedItems().length == 0)
		    {
			// Nothing is selected in this list.  Make the
			// 'sendToAll' checkbox be checked.
			sendToAllCheckbox.setState(true);
			ignoreButton.setEnabled(false);
			userInfoButton.setEnabled(false);
		    }
		
		else
		    {
			// Don't allow "everyone" to be selected if any
			// individual users are selected
			sendToAllCheckbox.setState(false);
			ignoreButton.setEnabled(true);
			userInfoButton.setEnabled(true);
		    }
		return;
	    }

	// the line thicknesses
	if (E.getSource() == thicknessChoice)
	    {
		canvas.drawThickness =
		    (thicknessChoice.getSelectedIndex() + 1);
		return;
	    }
	// the fill types
	if (E.getSource() == selectGroupFile) {
		//int which = selectGroupFile.getSelectedIndex();
               	//System.out.println("choice==="+choice);  
		String choice1 = selectGroupFile.getSelectedItem();
		choice=thisApplet.babylonPath+"/"+choice1;
	        return;
        }
	

	
	if (E.getSource() == fillTypeChoice)
	    {
		if (fillTypeChoice.getSelectedIndex() == 0)
		    canvas.fill = false;

		if (fillTypeChoice.getSelectedIndex() == 1)
		    canvas.fill = true;

		return;
	    }

	// the draw colors
	if (E.getSource() == colorChoice)
	    {
		canvas.drawColor =
		    canvas.colourArray[colorChoice.getSelectedIndex()];
		return;
	    }            

	// the draw types
	if (E.getSource() == freehandCheckbox)
	    {
		canvas.drawType = canvas.FREEHAND;
		thicknessChoice.setEnabled(true);
		fillTypeChoice.setEnabled(false);
		freehandCheckbox.setState(true);
		lineCheckbox.setState(false);
		ovalCheckbox.setState(false);
		rectangleCheckbox.setState(false);
		textCheckbox.setState(false);
		eraseCheckbox.setState(false);
		return;
	    }

	if (E.getSource() == lineCheckbox)
	    {
		canvas.drawType = canvas.LINE;
		thicknessChoice.setEnabled(true);
		fillTypeChoice.setEnabled(false);
		freehandCheckbox.setState(false);
		lineCheckbox.setState(true);
		ovalCheckbox.setState(false);
		rectangleCheckbox.setState(false);
		textCheckbox.setState(false);
		eraseCheckbox.setState(false);
		return;
	    }

	if (E.getSource() == ovalCheckbox)
	    {
		canvas.drawType = canvas.OVAL;
		thicknessChoice.setEnabled(true);
		fillTypeChoice.setEnabled(true);
		freehandCheckbox.setState(false);
		lineCheckbox.setState(false);
		ovalCheckbox.setState(true);
		rectangleCheckbox.setState(false);
		textCheckbox.setState(false);
		eraseCheckbox.setState(false);
		return;
	    }

	if (E.getSource() == rectangleCheckbox)
	    {
		canvas.drawType = canvas.RECTANGLE;
		thicknessChoice.setEnabled(true);
		fillTypeChoice.setEnabled(true);
		freehandCheckbox.setState(false);
		lineCheckbox.setState(false);
		ovalCheckbox.setState(false);
		rectangleCheckbox.setState(true);
		textCheckbox.setState(false);
		eraseCheckbox.setState(false);
		return;
	    }

	if (E.getSource() == textCheckbox)
	    {
		canvas.drawType = canvas.TEXT;
		thicknessChoice.setEnabled(false);
		fillTypeChoice.setEnabled(false);
		freehandCheckbox.setState(false);
		lineCheckbox.setState(false);
		ovalCheckbox.setState(false);
		rectangleCheckbox.setState(false);
		textCheckbox.setState(true);
		eraseCheckbox.setState(false);
		return;
	    }
	if (E.getSource() == eraseCheckbox)
	    {
		canvas.drawType = canvas.ERASE;
		thicknessChoice.setEnabled(false);
		fillTypeChoice.setEnabled(false);
		freehandCheckbox.setState(false);
		lineCheckbox.setState(false);
		ovalCheckbox.setState(false);
		rectangleCheckbox.setState(false);
		textCheckbox.setState(false);
		eraseCheckbox.setState(true);
		return;
	    }
    }
}
