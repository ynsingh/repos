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
//  babylonPasswordDialog.java
//

import java.awt.*;
import java.awt.event.*;
import babylon.*;


public class babylonPasswordDialog
    extends Dialog
    implements ActionListener, KeyListener, WindowListener
{
    private babylonPanel mainPanel;
    private babylonPasswordEncryptor passwordEncryptor;
    private Class thisClass = babylonPasswordDialog.class;

    private Panel p;
    private Label passwordLabel;
    private TextField passwordField;
    private Label passwordWarningLabel1;
    private Label passwordWarningLabel2;
    private Button ok;
    private String password = "";
    private GridBagLayout myLayout;


    public babylonPasswordDialog(babylonPanel panel, String myLabel,
				 boolean IsModal)
    {
	super(panel.parentWindow,
	      panel.strings.get(babylonPasswordDialog.class, "enterpass"),
	      IsModal);

	mainPanel = panel;
	
	myLayout = new GridBagLayout();
	setLayout(myLayout);

	p = new Panel();
	p.setLayout(myLayout);

	passwordLabel = new Label(myLabel);
	p.add(passwordLabel,
	      new babylonConstraints(0, 0, 1, 1, 1.0, 1.0,
				     GridBagConstraints.NORTHWEST,
				     GridBagConstraints.NONE,
				     new Insets(0, 0, 0, 0),
				     0, 0));

	passwordField = new TextField(20);
	passwordField.setEchoChar(new String("*").charAt(0));
	passwordField.addKeyListener(this);
	p.add(passwordField,
	      new babylonConstraints(0, 1, 1, 1, 1.0, 1.0,
				     GridBagConstraints.NORTHWEST,
				     GridBagConstraints.BOTH,
				     new Insets(0, 0, 0, 0),
				     0, 0));

	passwordWarningLabel1 =
	    new Label(mainPanel.strings.get(thisClass, "noencrypt1"));
	passwordWarningLabel1
	    .setVisible(!mainPanel.passwordEncryptor.canEncrypt);
	passwordWarningLabel1.setFont(babylonPanel.XsmallFont);
	p.add(passwordWarningLabel1,
	      new babylonConstraints(0, 2, 1, 1, 1.0, 1.0,
				     GridBagConstraints.NORTHWEST,
				     GridBagConstraints.NONE,
				     new Insets(0, 0, 0, 0),
				     0, 0));

	passwordWarningLabel2 =
	    new Label(mainPanel.strings.get(thisClass, "noencrypt2"));
	passwordWarningLabel2
	    .setVisible(!mainPanel.passwordEncryptor.canEncrypt);
	passwordWarningLabel2.setFont(babylonPanel.XsmallFont);
	p.add(passwordWarningLabel2,
	      new babylonConstraints(0, 3, 1, 1, 1.0, 1.0,
				     GridBagConstraints.NORTHWEST,
				     GridBagConstraints.NONE,
				     new Insets(0, 0, 0, 0),
				     0, 0));

	ok = new Button(mainPanel.strings.get("ok"));
	ok.setFont(babylonPanel.smallFont);
	ok.addActionListener(this);
	ok.addKeyListener(this);
	p.add(ok, new babylonConstraints(0, 4, 1, 1, 1.0, 1.0,
					 GridBagConstraints.CENTER,
					 GridBagConstraints.NONE,
					 new Insets(5, 0, 0, 0),
					 0, 0));

	add(p, new babylonConstraints(0, 0, 1, 1, 1.0, 1.0,
				      GridBagConstraints.NORTHWEST,
				      GridBagConstraints.BOTH,
				      new Insets(5, 5, 5, 5),
				      0, 0));

	pack();

	mainPanel.centerDialog(this);

	addKeyListener(this);
	addWindowListener(this);
	setResizable(false);
	setVisible(true);
	passwordField.requestFocus();
    }

    public String getPassword()
    {
	// Return the password that the user entered
	return (password);
    }

    public void actionPerformed(ActionEvent E)
    {
	if (E.getSource() == ok)
	    {
		password = mainPanel.passwordEncryptor
		    .encryptPassword(passwordField.getText());
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
		if ((E.getSource() == passwordField) || (E.getSource() == ok))
		    {
			password = mainPanel.passwordEncryptor
			    .encryptPassword(passwordField.getText());
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
