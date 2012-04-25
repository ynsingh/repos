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
//  babylonUserToolDialog.java
//

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import babylon.*;


public class babylonUserToolDialog
    extends Dialog
    implements ActionListener, ItemListener, KeyListener, WindowListener
{
    private babylonUserTool userTool = new babylonUserTool();

    protected babylonServerWindow parentFrame;
    private Class thisClass = babylonUserToolDialog.class;

    protected GridBagLayout myLayout;
    protected Panel p;
    protected Label nameLabel;
    protected TextField name;
    protected Label passwordLabel;
    protected TextField password;
    protected Button create;
    protected Button finished;
    java.awt.List allUsersList;
    protected Button delete;
    protected Label statusLabel;


    public babylonUserToolDialog(babylonServerWindow parent)
    {
	super(parent,
	      parent.server.strings.get(babylonUserToolDialog.class,
					"usertool"),
	      false);

	parentFrame = parent;
	
	myLayout = new GridBagLayout();
	setLayout(myLayout);

	p = new Panel();
	p.setLayout(myLayout);

	nameLabel =
	    new Label(parentFrame.server.strings.get(thisClass, "username"));
	p.add(nameLabel, new babylonConstraints(0, 0, 1, 1, 0.0, 0.0,
						GridBagConstraints.NORTHWEST,
						GridBagConstraints.NONE,
						new Insets(0, 0, 0, 0),
						0, 0));

	name = new TextField(20);
	name.addKeyListener(this);
	p.add(name, new babylonConstraints(1, 0, 1, 1, 1.0, 1.0,
					   GridBagConstraints.NORTHWEST,
					   GridBagConstraints.HORIZONTAL,
					   new Insets(0, 0, 0, 0),
					   0, 0));

	create =
	    new Button(parentFrame.server.strings.get(thisClass, "create"));
	create.addActionListener(this);
	create.addKeyListener(this);
	p.add(create, new babylonConstraints(2, 0, 1, 1, 1.0, 1.0,
					     GridBagConstraints.NORTHWEST,
					     GridBagConstraints.HORIZONTAL,
					     new Insets(0, 5, 0, 0),
					     0, 0));

	passwordLabel =
	    new Label(parentFrame.server.strings.get(thisClass, "password"));
	p.add(passwordLabel,
	      new babylonConstraints(0, 1, 1, 1, 0.0, 0.0,
				     GridBagConstraints.NORTHWEST,
				     GridBagConstraints.NONE,
				     new Insets(0, 0, 0, 0),
				     0, 0));

	password = new TextField(20);
	password.setEchoChar(new String("*").charAt(0));
	password.addKeyListener(this);
	p.add(password, new babylonConstraints(1, 1, 1, 1, 1.0, 1.0,
					       GridBagConstraints.NORTHWEST,
					       GridBagConstraints.HORIZONTAL,
					       new Insets(0, 0, 0, 0),
					       0, 0));

	allUsersList = new java.awt.List(10);
	allUsersList.addItemListener(this);
	allUsersList.addKeyListener(this);
	allUsersList.setMultipleMode(true);
	p.add(allUsersList, new babylonConstraints(0, 2, 2, 1, 1.0, 1.0,
					     GridBagConstraints.NORTHWEST,
					     GridBagConstraints.BOTH,
					     new Insets(5, 0, 0, 0),
					     0, 0));

	delete =
	    new Button(parentFrame.server.strings.get(thisClass, "delete"));
	delete.addActionListener(this);
	delete.addKeyListener(this);
	p.add(delete, new babylonConstraints(2, 2, 1, 1, 1.0, 1.0,
					     GridBagConstraints.NORTHWEST,
					     GridBagConstraints.HORIZONTAL,
					     new Insets(5, 5, 0, 0),
					     0, 0));

	statusLabel = new Label("");
	p.add(statusLabel, new babylonConstraints(0, 3, 2, 1, 1.0, 1.0,
					     GridBagConstraints.CENTER,
					     GridBagConstraints.HORIZONTAL,
					     new Insets(0, 0, 0, 0),
					     0, 0));

	finished =
	    new Button(parentFrame.server.strings.get(thisClass, "finished"));
	finished.addActionListener(this);
	finished.addKeyListener(this);
	p.add(finished, new babylonConstraints(2, 3, 1, 1, 1.0, 1.0,
					       GridBagConstraints.NORTHWEST,
					       GridBagConstraints.HORIZONTAL,
					       new Insets(0, 5, 0, 0),
					       0, 0));

	add(p, new babylonConstraints(0, 0, 1, 1, 1.0, 1.0,
				      GridBagConstraints.NORTHWEST,
				      GridBagConstraints.NONE,
				      new Insets(5, 5, 5, 5),
				      0, 0));

	fillUsersList();

	//setSize(600, 600);
	pack();
	setResizable(false);

	parentFrame.centerDialog(this);

	addKeyListener(this);
	addWindowListener(this);
	setVisible(true);
	name.requestFocus();
    }

    private void fillUsersList()
    {
	// Get the list of users from the userTool and put it in our
	// list widget

	String[] usersList = null;

	try {
	    usersList = userTool.listUsers();
	}
	catch (Exception e) {}

	allUsersList.removeAll();
	delete.setEnabled(false);

	if (usersList == null)
	    return;

	for (int count = 0; count < usersList.length; count ++)
	    allUsersList.add(usersList[count]);
    }

    private void createUser()
    {
	// Encrypt the password
	String encryptedPassword = new babylonPasswordEncryptor()
	    .encryptPassword(password.getText());

	try {
	    userTool.createUser(name.getText(),
				encryptedPassword);
	}
	catch (Exception e) {
	    statusLabel.setText(parentFrame.server.strings
				.get(thisClass, "createerror"));
	    new babylonInfoDialog(parentFrame, parentFrame.server.strings
				  .get(thisClass, "createerror"),
				  true, e.toString(),
				  parentFrame.server.strings.get("ok"));
	    return;
	}

	name.setText("");
	password.setText("");

	fillUsersList();

	statusLabel.setText(parentFrame.server.strings
			    .get(thisClass, "usercreated"));
	name.requestFocus();
	return;
    }

    private void deleteUsers()
    {
	String[] users = allUsersList.getSelectedItems();
		
	for (int count = 0; count < users.length; count ++)
	    {
		try {
		    userTool.deleteUser(users[count]);
		}
		catch (Exception e) {
		    statusLabel.setText(parentFrame.server.strings
					.get(thisClass, "deleteerror"));
		    new babylonInfoDialog(parentFrame,
					  parentFrame.server.strings
					  .get(thisClass, "deleteerror"),
					  true, e.toString(),
					  parentFrame.server.strings
					  .get("ok"));
		    return;
		}
	    }

	fillUsersList();

	statusLabel.setText(parentFrame.server.strings
			    .get(thisClass, "usersdeleted"));
	return;
    }

    public void actionPerformed(ActionEvent E)
    {
	if (E.getSource() == create)
	    {
		createUser();
		return;
	    }

	else if (E.getSource() == delete)
	    {
		deleteUsers();
		return;
	    }

	else if (E.getSource() == finished)
	    {
		dispose();
		return;
	    }
    }

    public void itemStateChanged(ItemEvent E)
    {
	if (E.getSource() == allUsersList)
	    {
		// If user names are selected, enable the delete
		// button.  If not, vice-vers
		if (allUsersList.getSelectedItems().length == 0)
		    delete.setEnabled(false);
		else
		    delete.setEnabled(true);
	    }
    }

    public void keyPressed(KeyEvent E)
    {
    }

    public void keyReleased(KeyEvent E)
    {
	if (E.getKeyCode() == E.VK_ENTER) 
	    {
		if (E.getSource() == create)
		    {
			createUser();
			return;
		    }
		
		else if (E.getSource() == delete)
		    {
			deleteUsers();
			return;
		    }

		else if (E.getSource() == finished)
		    {
			dispose();
			return;
		    }
		
		else if ((E.getSource() == name) ||
			 (E.getSource() == password))
		    {
			createUser();
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
