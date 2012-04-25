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
//  babylonWindow.java
//

import java.awt.*;
import java.awt.event.*;
import javax.swing.ImageIcon;
import java.awt.datatransfer.*;
import java.io.*;
import java.net.*;
import babylon.*;


public class babylonWindow
    extends Frame
    implements WindowListener
{
    babylonPanel contentPanel = null;

    public babylonWindow(babylonPanel panel)
    {
	// This is the containing window for the panel that makes up
	// the chat client.

	super();

	contentPanel = panel;

	// Tell the panel "who's your daddy"
	contentPanel.parentWindow = this;

	setLayout(new GridBagLayout());

	add(contentPanel, new babylonConstraints(0, 0, 1, 1, 1.0, 1.0,
						 GridBagConstraints.CENTER,
						 GridBagConstraints.BOTH,
						 new Insets(0, 0, 0, 0),
						 0, 0));

	addWindowListener(this);

	// show the window and get going
	pack();
    }

    public void setIcon(URL babylonURL)
    {
	// Internet Exploder 5.5 just can't handle this, apparently.  Only do
	// it if we're running as an application

	try {
	    URL iconUrl =
		new URL(babylonURL.getProtocol(), babylonURL.getHost(),
			babylonURL.getFile() + "babylonIcon.jpg");
	    ImageIcon icon = new ImageIcon(iconUrl);
	    this.setIconImage(icon.getImage());
	}
	catch (Exception e) { }
    }

    public void windowActivated(WindowEvent E)
    {
    }

    public void windowClosed(WindowEvent E)
    {
    }

    public void windowClosing(WindowEvent E)
    {
	if (contentPanel.connected == true)
	    contentPanel.disconnect();
	dispose();

	if (contentPanel.thisApplet != null)
	    contentPanel.thisApplet.destroy();
	else if (!contentPanel.adminConsole)
	    System.exit(0);

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
