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
//  babylonSettingsDialog.java
//

import java.awt.*;
import java.awt.event.*;
import babylon.*;


public class babylonSettingsDialog
    extends Dialog
    implements ActionListener, ItemListener, KeyListener, WindowListener
{
    private babylonPanel mainPanel;
    private Class thisClass = babylonSettingsDialog.class;

    private Panel p1;
    private Label nameLabel;
    private TextField name;
    private Label passwordLabel;
    private TextField password;
    private Label passwordWarningLabel1;
    private Label passwordWarningLabel2;
    private Label additionalLabel;
    private TextArea additional;

    private Label hostLabel;
    private TextField host;
    private Label portLabel;
    private TextField port;
    private Checkbox playSoundCheckbox;
    private Checkbox showCanvasCheckbox;

    private Panel p2;
    private Button ok;
    private Button cancel;

    private GridBagLayout mainLayout;


    babylonSettingsDialog(babylonPanel parent)
    {
	super(parent.parentWindow,
	      parent.strings.get(babylonSettingsDialog.class, "settings"),
	      true);

	mainPanel = (babylonPanel) parent;

	mainLayout = new GridBagLayout();
	setLayout(mainLayout);

	p1 = new Panel();
	p1.setLayout(mainLayout);

	// set up the 'name' field

	nameLabel = new Label(mainPanel.strings.get(thisClass, "username"));
	p1.add(nameLabel, new babylonConstraints(0, 0, 1, 1, 1.0, 1.0,
						 GridBagConstraints.WEST,
						 GridBagConstraints.NONE,
						 new Insets(0, 0, 0, 0),
						 0, 0));

	passwordLabel =
	    new Label(mainPanel.strings.get(thisClass, "password"));
	p1.add(passwordLabel, new babylonConstraints(1, 0, 1, 1, 1.0, 1.0,
						     GridBagConstraints.WEST,
						     GridBagConstraints.NONE,
						     new Insets(0, 0, 0, 0),
						     0, 0));

	name = new TextField(20);
	name.addKeyListener(this);
	name.setText(mainPanel.name);
	p1.add(name, new babylonConstraints(0, 1, 1, 1, 1.0, 1.0,
					    GridBagConstraints.WEST,
					    GridBagConstraints.NONE,
					    new Insets(0, 0, 0, 0),
					    0, 0));

	password = new TextField(20);
	if (mainPanel.requirePassword)
	    {
		password.setEchoChar(new String("*").charAt(0));
		password.setText(mainPanel.plainPassword);
	    }
	else
	    {
		password.setText(mainPanel.strings.get(thisClass,
						       "nopassword"));
		password.setEditable(false);
	    }
	password.addKeyListener(this);
	p1.add(password, new babylonConstraints(1, 1, 1, 1, 1.0, 1.0,
						GridBagConstraints.WEST,
						GridBagConstraints.NONE,
						new Insets(0, 0, 0, 0),
						0, 0));

	passwordWarningLabel1 =
	    new Label(mainPanel.strings.get(thisClass, "noencrypt1"));
	passwordWarningLabel1.setVisible(mainPanel.requirePassword &&
				    !mainPanel.passwordEncryptor
				    .canEncrypt);
	passwordWarningLabel1.setFont(babylonPanel.XsmallFont);
	p1.add(passwordWarningLabel1,
	       new babylonConstraints(0, 2, 2, 1, 1.0, 1.0,
				      GridBagConstraints.WEST,
				      GridBagConstraints.NONE,
				      new Insets(0, 0, 0, 0),
				      0, 0));

	passwordWarningLabel2 =
	    new Label(mainPanel.strings.get(thisClass, "noencrypt2"));
	passwordWarningLabel2.setVisible(mainPanel.requirePassword &&
				    !mainPanel.passwordEncryptor
				    .canEncrypt);
	passwordWarningLabel2.setFont(babylonPanel.XsmallFont);
	p1.add(passwordWarningLabel2,
	       new babylonConstraints(0, 3, 2, 1, 1.0, 1.0,
				      GridBagConstraints.WEST,
				      GridBagConstraints.NONE,
				      new Insets(0, 0, 0, 0),
				      0, 0));

	hostLabel = new Label(mainPanel.strings.get(thisClass, "servername"));
	p1.add(hostLabel, new babylonConstraints(0, 4, 1, 1, 1.0, 1.0,
						 GridBagConstraints.WEST,
						 GridBagConstraints.NONE,
						 new Insets(0, 0, 0, 0),
						 0, 0));

	portLabel =
	    new Label(mainPanel.strings.get(thisClass, "networkport"));
	p1.add(portLabel, new babylonConstraints(1, 4, 1, 1, 1.0, 1.0,
						 GridBagConstraints.WEST,
						 GridBagConstraints.NONE,
						 new Insets(0, 0, 0, 0),
						 0, 0));

	host = new TextField(20);
	host.addKeyListener(this);
	host.setText(mainPanel.host);
	//host.setText("172.28.44.9");
	p1.add(host, new babylonConstraints(0, 5, 1, 1, 1.0, 1.0,
					    GridBagConstraints.WEST,
					    GridBagConstraints.NONE,
					    new Insets(0, 0, 0, 0),
					    0, 0));

	port = new TextField(20);
	port.addKeyListener(this);
	port.setText(mainPanel.port);
	p1.add(port, new babylonConstraints(1, 5, 1, 1, 1.0, 1.0,
					    GridBagConstraints.WEST,
					    GridBagConstraints.NONE,
					    new Insets(0, 0, 0, 0),
					    0, 0));

	additionalLabel =
	    new Label(mainPanel.strings.get(thisClass, "additionalinfo"));
	p1.add(additionalLabel,
	       new babylonConstraints(0, 6, 1, 1, 1.0, 1.0,
				      GridBagConstraints.WEST,
				      GridBagConstraints.NONE,
				      new Insets(0, 0, 0, 0),
				      0, 0));

	additional = new TextArea(5, 20);
	additional.addKeyListener(this);
	additional.setText(mainPanel.additional);
	p1.add(additional, new babylonConstraints(0, 7, 1, 5, 1.0, 1.0,
						  GridBagConstraints.WEST,
						  GridBagConstraints.NONE,
						  new Insets(0, 0, 0, 0),
						  0, 0));

	playSoundCheckbox =
	    new Checkbox(mainPanel.strings.get(thisClass, "playsound"));
	playSoundCheckbox.addItemListener(this);
	playSoundCheckbox.setState(true);
	p1.add(playSoundCheckbox,
	       new babylonConstraints(1, 7, 1, 1, 0.0, 0.0,
				      GridBagConstraints.NORTHWEST,
				      GridBagConstraints.NONE,
				      new Insets(0, 0, 0, 0),
				      0, 0));

	showCanvasCheckbox =
	    new Checkbox(mainPanel.strings.get(thisClass, "showcanvas"));
	showCanvasCheckbox.addItemListener(this);
	showCanvasCheckbox.setState(mainPanel.canvas.isVisible());
	p1.add(showCanvasCheckbox,
	       new babylonConstraints(1, 8, 1, 1, 0.0, 0.0,
				      GridBagConstraints.NORTHWEST,
				      GridBagConstraints.NONE,
				      new Insets(0, 0, 0, 0),
				      0, 0));

	add(p1, new babylonConstraints(0, 0, 1, 1, 1.0, 1.0,
				       GridBagConstraints.CENTER,
				       GridBagConstraints.NONE,
				       new Insets(5, 5, 0, 5),
				       0, 0));

	p2 = new Panel();
	p2.setLayout(mainLayout);

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
				       GridBagConstraints.NONE,
				       new Insets(5, 5, 5, 5),
				       0, 0));

	setSize(200,200);
	pack();

	mainPanel.centerDialog(this);

	if (mainPanel.connected || mainPanel.lockSettings)
	    {
		name.setEnabled(false);
		password.setEnabled(false);
		host.setEnabled(false);
		port.setEnabled(false);
	    }
	else
	    {
		name.setEnabled(true);
		password.setEnabled(true);
		host.setEnabled(true);
		port.setEnabled(true);
	    }

	addKeyListener(this);
	addWindowListener(this);
	setResizable(false);
	setVisible(true);
	name.requestFocus();
    }

    private void setValues()
    {
	mainPanel.name = name.getText();
	mainPanel.nameLabel.setText(mainPanel.name);
	mainPanel.port = port.getText();
	mainPanel.host = host.getText();
	mainPanel.additional = additional.getText();

	// Are we sending a password?
	if (mainPanel.requirePassword)
	    {
		mainPanel.plainPassword = password.getText();
		mainPanel.encryptedPassword =
		    mainPanel.passwordEncryptor
		    .encryptPassword(mainPanel.plainPassword);
	    }
	return;
    }

    public void actionPerformed(ActionEvent E)
    {
	if (E.getSource() == ok)
	    {
		setValues();
		dispose();
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
		if ((E.getSource() == ok) ||
		    (E.getSource() == name) ||
		    (E.getSource() == password) ||
		    (E.getSource() == port) ||
		    (E.getSource() == host))
		    {
			setValues();
			dispose();
			return;
		    }

		else if (E.getSource() == cancel)
		    {
			dispose();
			return;
		    }
	    }

	else if (E.getSource() == additional)
	    {
		if (E.getKeyCode() == E.VK_TAB)
		    additional.transferFocus();
	    }
    }

    public void keyTyped(KeyEvent E)
    {
    }   

    public void itemStateChanged(ItemEvent E)
    {
	// the 'show canvas' checkbox
	if (E.getSource() == showCanvasCheckbox)
	    {
		mainPanel.showCanvas(showCanvasCheckbox.getState());
		return;
	    }

	// The 'play sound' checkbox
	if (E.getSource() == playSoundCheckbox)
	    {
		mainPanel.playSound = playSoundCheckbox.getState();
		return;
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
