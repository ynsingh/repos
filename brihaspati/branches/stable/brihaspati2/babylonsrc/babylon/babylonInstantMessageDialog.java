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
//  babylonInstantMessageDialog.java
//

import java.io.*;
import java.awt.*;
import java.awt.event.*;
import babylon.*;


public class babylonInstantMessageDialog
    extends babylonTextDialog
{
    // An extension of the text dialog which allows users to return an
    // instant message to the sender

    public babylonUser fromUser;
    private babylonPanel mainPanel;
    private Class thisClass = babylonInstantMessageDialog.class;

    private TextArea replyArea;
    private Button replyButton;
    private Button ignoreButton;
    private Panel p2;

    public babylonInstantMessageDialog(babylonPanel parent, babylonUser from,
				       String contents)
    {
	super(parent.parentWindow, parent.strings
	      .get(babylonInstantMessageDialog.class, "instantmessage") +
	      " " + from.name, "", 40, 10,
	      TextArea.SCROLLBARS_VERTICAL_ONLY, false,
	      parent.strings.get("dismiss"));

	fromUser = from;
	mainPanel = parent;

	if (!contents.endsWith("\n"))
	    contents += "\n";
	textArea.setText(fromUser.name + "> " + contents);

	// Remove the dismiss button temporarily
	p.remove(dismissButton);

	// Make a second panel
	p2 = new Panel();
	p2.setLayout(myLayout);

	replyArea =
	    new TextArea("", 2, 40, TextArea.SCROLLBARS_VERTICAL_ONLY);
	replyArea.addKeyListener(this);
	replyArea.setEditable(true);
	replyArea.setFont(babylonPanel.smallFont);
	p2.add(replyArea,
	       new babylonConstraints(0, 0, 3, 1, 1.0, 1.0,
				      GridBagConstraints.NORTHWEST,
				      GridBagConstraints.HORIZONTAL,
				      new Insets(0, 0, 5, 0),
				      0, 0));

	replyButton = new Button(mainPanel.strings.get(thisClass, "reply"));
	replyButton.setFont(babylonPanel.smallFont);
	replyButton.addActionListener(this);
	replyButton.addKeyListener(this);
	p2.add(replyButton, new babylonConstraints(0, 1, 1, 1, 1.0, 1.0,
						   GridBagConstraints.EAST,
						   GridBagConstraints.NONE,
						   new Insets(0, 0, 0, 0),
						   0, 0));

	ignoreButton = new Button(mainPanel.strings.get(thisClass, "ignore"));
	ignoreButton.setFont(babylonPanel.smallFont);
	ignoreButton.addActionListener(this);
	ignoreButton.addKeyListener(this);
	p2.add(ignoreButton, new babylonConstraints(1, 1, 1, 1, 1.0, 1.0,
						    GridBagConstraints.CENTER,
						    GridBagConstraints.NONE,
						    new Insets(0, 0, 0, 0),
						    0, 0));

	// Put the dismiss button into our new panel
	p2.add(dismissButton, new babylonConstraints(2, 1, 1, 1, 1.0, 1.0,
					       GridBagConstraints.WEST,
					       GridBagConstraints.NONE,
					       new Insets(0, 0, 0, 0),
					       0, 0));

	add(p2, new babylonConstraints(0, 1, 1, 1, 1.0, 1.0,
				       GridBagConstraints.NORTHWEST,
				       GridBagConstraints.BOTH,
				       new Insets(5, 5, 5, 5),
				       0, 0));

	super.setResizable(true);
	setResizable(true);
	pack();
    }

    public void reply()
    {
	// Take the text from the reply area and send it back as another
	// instant message

	String reply = replyArea.getText();
	if (!reply.endsWith("\n"))
	    reply += "\n";

	try {
	    mainPanel.client.sendInstantMess(fromUser.id, reply);
	}
	catch (IOException e) {
	    mainPanel.client.lostConnection();
	    return;
	}

	// Add to the text area
	textArea.append(mainPanel.name + "> " + reply);

	// Ckear the reply area
	replyArea.setText("");
    }

    public void ignore()
    {
	mainPanel.client.addToIgnored(fromUser.name);
	return;
    }

    public void dismiss()
    {
	mainPanel.instantMessageDialogs.removeElement(this);
	dispose();
	return;
    }

    public void addMessage(String newMessage)
    {
	// There's another incoming message
	if (!newMessage.endsWith("\n"))
	    newMessage += "\n";
	textArea.append(fromUser.name + "> " + newMessage);
    }

    public void actionPerformed(ActionEvent E)
    {
	if (E.getSource() == replyButton)
	    {
		reply();
		return;
	    }
	else if (E.getSource() == ignoreButton)
	    {
		ignore();
		dismiss();
		return;
	    }
	else if (E.getSource() == dismissButton)
	    {
		dismiss();
		super.actionPerformed(E);
		return;
	    }
    }

    public void keyReleased(KeyEvent E)
    {
	if (E.getKeyCode() == E.VK_ENTER)
	    {
		if ((E.getSource() == replyArea) ||
		    (E.getSource() == replyButton))
		    {
			reply();
			return;
		    }
		else if (E.getSource() == ignoreButton)
		    {
			ignore();
			dismiss();
			return;
		    }
		else if (E.getSource() == dismissButton)
		    {
			dismiss();
			super.keyReleased(E);
			return;
		    }
	    }
	else if (E.getKeyCode() == E.VK_TAB)
	    {
		if (E.getSource() == replyArea)
		    {
			// Tab out of the reply area
			replyArea.transferFocus();
			return;
		    }
	    }
	else
	    // Pass to superclass
	    super.keyReleased(E);
    }
}
