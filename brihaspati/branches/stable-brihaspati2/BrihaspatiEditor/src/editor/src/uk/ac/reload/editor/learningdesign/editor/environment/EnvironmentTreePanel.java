/**
 *  RELOAD TOOLS
 *
 *  Copyright (c) 2004 Oleg Liber, Bill Olivier, Phillip Beauvoir
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in
 *  all copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 *  Project Management Contact:
 *
 *  Oleg Liber
 *  Bolton Institute of Higher Education
 *  Deane Road
 *  Bolton BL3 5AB
 *  UK
 *
 *  e-mail:   o.liber@bolton.ac.uk
 *
 *
 *  Technical Contact:
 *
 *  Phillip Beauvoir
 *  e-mail:   p.beauvoir@bolton.ac.uk
 *
 *  Web:      http://www.reload.ac.uk
 *
 */

package uk.ac.reload.editor.learningdesign.editor.environment;

import java.awt.event.ActionEvent;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import uk.ac.reload.dweezil.gui.ShortcutIcon;
import uk.ac.reload.dweezil.gui.tree.DweezilTreeNode;
import uk.ac.reload.dweezil.menu.MenuAction;
import uk.ac.reload.dweezil.menu.PopupMenuAction;
import uk.ac.reload.dweezil.util.DweezilUIManager;
import uk.ac.reload.editor.EditorFrame;
import uk.ac.reload.editor.datamodel.DataComponent;
import uk.ac.reload.editor.datamodel.DataModel;
import uk.ac.reload.editor.gui.Editor_Tree;
import uk.ac.reload.editor.learningdesign.datamodel.LD_DataModel;
import uk.ac.reload.editor.learningdesign.datamodel.components.environments.*;
import uk.ac.reload.editor.learningdesign.datamodel.components.environments.Environment;
import uk.ac.reload.editor.learningdesign.datamodel.components.environments.EnvironmentRef;
import uk.ac.reload.editor.learningdesign.datamodel.components.environments.Environments_Grouping;
import uk.ac.reload.editor.learningdesign.datamodel.components.environments.LearningObject;
import uk.ac.reload.editor.learningdesign.datamodel.components.environments.Service;
import uk.ac.reload.editor.learningdesign.editor.shared.LD_TreePanel;
import uk.ac.reload.moonunit.learningdesign.LD_Core;

/**
 * Environment Tree Panel
 *
 * @author Phillip Beauvoir
 * @version $Id: EnvironmentTreePanel.java,v 1.1 1998/03/26 15:40:32 ynsingh Exp $
 */
public class EnvironmentTreePanel
extends LD_TreePanel
{
    /**
     * The Tree in this Panel
     */
    private EnvironmentTree _tree;
    
    /**
     * "Add" Menu Action
     */
    private MenuAction_Add _menuAdd;
    
    /**
     * "Delete" Menu Action
     */
    private MenuAction_Delete _menuDelete;
    
    /**
     * "Rename" Menu Action
     */
    private MenuAction_Rename _menuRename;

    /**
     * "Move Up" menu Item
     */
    private MenuAction_MoveUp _menuMoveUp;
    
    /**
     * "Move Down" menu Item
     */
    private MenuAction_MoveDown _menuMoveDown;
    
    /**
     * Flag for level A LD
     */
    private boolean _isLevelA;
    
    
    /**
	 * Constructor
     */
	public EnvironmentTreePanel() {
		setText("Environments");
		
		setIcon(DweezilUIManager.getIcon(ICON_ENVIRONMENTS));
	    
		_menuAdd = new MenuAction_Add();
		addMenuActionToCoolBar(_menuAdd);

		_menuDelete = new MenuAction_Delete();
		addMenuActionToCoolBar(_menuDelete);
		
		_menuMoveUp = new MenuAction_MoveUp();
		addMenuActionToCoolBar(_menuMoveUp);

		_menuMoveDown = new MenuAction_MoveDown();
		addMenuActionToCoolBar(_menuMoveDown);

		_menuRename = new MenuAction_Rename();
	}
	
	/**
	 * Over-ride to do stuff
	 */
	public void setDataModel(DataModel dataModel) {
	    super.setDataModel(dataModel);
	    
	    // Get level
	    String level = ((LD_DataModel)getDataModel()).getLearningDesign().getLevel();
		if("A".equals(level)) {
		    _isLevelA = true;
		}
		
		// Setup menu after we know what level we are
		_menuAdd.addMenuItems();
	}
	
	/**
	 * @return The Tree
	 */
	public Editor_Tree getTree() {
	    if(_tree == null) {
	        _tree = new EnvironmentTree();
	    }
	    return _tree;
	}

	/**
	 * Update the Menu Items
	 */
	public synchronized void updateMenus() {
	    super.updateMenus();

	    // Single component
	    DataComponent component = null;
	    DweezilTreeNode selNode = getTree().getSelectedNode();
	    if(selNode != null) {
	        component = (DataComponent)selNode.getUserObject();
	    }
	    
	    // Set Menu Items enabled/disabled
	    _menuAdd.newEnvironment.setEnabled(component instanceof Environments_Grouping);
	    _menuAdd.newLO.setEnabled(component instanceof LearningObject_Grouping);
	    _menuAdd.newSendMail.setEnabled(component instanceof Service_Grouping);
	    _menuAdd.newConference.setEnabled(component instanceof Service_Grouping);
	    _menuAdd.newIndexSearch.setEnabled(component instanceof Service_Grouping);
        _menuAdd.newMonitor.setEnabled(component instanceof Service_Grouping);
	    
	    // Enable Environment Refs only if there are valid Environments
	    if(component instanceof EnvironmentRef_Grouping) {
	        Environment env = (Environment)component.getParent();
	        Environment[] envs = env.getAllowedEnvironmentsToReference();
	        _menuAdd.newEnvironmentRef.setEnabled(envs.length != 0);
	    }
	    else {
	        _menuAdd.newEnvironmentRef.setEnabled(false);
	    }
	    
	    // Delete
	    _menuDelete.setEnabled(component != null && component.canDelete());
	    
	    // Rename
	    _menuRename.setEnabled(component instanceof Environment
	            || component instanceof Service
	            || component instanceof LearningObject);
	    
        // Move up
	    _menuMoveUp.setEnabled(component != null && component.canMoveUp());
        
        // Move down
        _menuMoveDown.setEnabled(component != null && component.canMoveDown());

        // Popup Menu - best to Remove all and re-add them
	    getTree().getPopupMenu().removeAll();
	    getTree().getPopupMenu().add(_menuAdd.newEnvironment);
	    getTree().getPopupMenu().addSeparator();
	    getTree().getPopupMenu().add(_menuAdd.newLO);
	    getTree().getPopupMenu().addSeparator();
	    getTree().getPopupMenu().add(_menuAdd.newSendMail);
	    getTree().getPopupMenu().add(_menuAdd.newConference);
	    getTree().getPopupMenu().add(_menuAdd.newIndexSearch);
	    if(!_isLevelA) { // Monitor not in level B and C
	        getTree().getPopupMenu().add(_menuAdd.newMonitor);
	    }
	    getTree().getPopupMenu().addSeparator();
	    getTree().getPopupMenu().add(_menuAdd.newEnvironmentRef);
	    getTree().getPopupMenu().addSeparator();
	    getTree().getPopupMenu().add(_menuDelete);
	    getTree().getPopupMenu().add(_menuRename);
	}

	/**
	 * Add a new Environment Ref
	 * @param message The displayed message
	 * @param icon The Icon in the message box
	 */
	private void addNewEnvironmentRef(String message, Icon icon) {
	    DataComponent component = null;
	    DweezilTreeNode selNode = getTree().getSelectedNode();
	    if(selNode != null) {
	        component = (DataComponent)selNode.getUserObject();
	    }

	    if(!(component instanceof EnvironmentRef_Grouping)) {
	        return;
	    }
	    
	    // The currently selected Environment
	    Environment environment = (Environment)component.getParent();
	    
	    // Get all Environment Components in a list
	    Environment[] envs = environment.getAllowedEnvironmentsToReference();
	    if(envs.length == 0) {
	        return;
	    }
	    
	    // Select Environment from Dialog box in a list
	    Environment env = (Environment)JOptionPane.showInputDialog(EditorFrame.getInstance(),
	            "Environment",
	            message,
	            JOptionPane.QUESTION_MESSAGE,
	            icon,
	            envs, null);
	    
	    if(env != null) {
	        EnvironmentRef envRef = (EnvironmentRef)getTree().addNewElement(LD_Core.ENVIRONMENT_REF, null);
	        if(envRef != null) {
	            envRef.setLD_ComponentRef(env);
	        }
	    }
	}

    // ====================================================================================
	
	/**
	 * "Add" Menu Button
	 */
	class MenuAction_Add extends PopupMenuAction {
	    MenuAction newEnvironment;
	    MenuAction newLO;
	    MenuAction newSendMail;
	    MenuAction newConference;
	    MenuAction newIndexSearch;
	    MenuAction newMonitor;
	    MenuAction newEnvironmentRef;
	    
	    /**
	     * Constuctor
	     * Initiate these Menu Actions now so they are not null
	     */
	    public MenuAction_Add() {
			super("Add", ICON_ADD);
			
			newEnvironment = new MenuAction("New Environment", ICON_ENVIRONMENT) {
                public void actionPerformed(ActionEvent e) {
                    addNewElement("New Environment", getMenuIcon(), LD_Core.ENVIRONMENT);
                }
			};
			
			newLO = new MenuAction("New Learning Object", ICON_LEARNINGOBJECT) {
                public void actionPerformed(ActionEvent e) {
                    addNewElement("New Learning Object", getMenuIcon(), LD_Core.LEARNING_OBJECT);
                }
			};

			newSendMail = new MenuAction("New Send Mail", ICON_SENDMAIL) {
                public void actionPerformed(ActionEvent e) {
                    addNewElement("New Send Mail", getMenuIcon(), LD_Core.SENDMAIL);
                }
			};

			newConference = new MenuAction("New Conference", ICON_CONFERENCE) {
                public void actionPerformed(ActionEvent e) {
                    addNewElement("New Conference", getMenuIcon(), LD_Core.CONFERENCE);
                }
			};

			newIndexSearch = new MenuAction("New Index Search", ICON_INDEXSEARCH) {
                public void actionPerformed(ActionEvent e) {
                    addNewElement("New Index Search", getMenuIcon(), LD_Core.INDEX_SEARCH);
                }
			};

			newMonitor = new MenuAction("New Monitor", ICON_MONITOR) {
		        public void actionPerformed(ActionEvent e) {
		            addNewElement("New Monitor", getMenuIcon(), LD_Core.MONITOR);
		        }
		    };
		    
			newEnvironmentRef = new MenuAction("New Environment Reference") {
			    {
			        ImageIcon icon = DweezilUIManager.getIcon(ICON_ENVIRONMENT);
			        setMenuIcon(new ShortcutIcon(icon));
			    }
			    
                public void actionPerformed(ActionEvent e) {
                    addNewEnvironmentRef("New Environment Reference", getMenuIcon());
                }
			};
	    }

        /**
         * Add the menu items after we have ascertained what LD level it is
         */
        public void addMenuItems() {
			getPopupMenu().add(newEnvironment);
			getPopupMenu().addSeparator();
			getPopupMenu().add(newLO);
			getPopupMenu().addSeparator();
			getPopupMenu().add(newSendMail);
			getPopupMenu().add(newConference);
			getPopupMenu().add(newIndexSearch);
			
			// If Level B or C add Monitor
			if(!_isLevelA) {
			    getPopupMenu().add(newMonitor);
			}
			
			getPopupMenu().addSeparator();
			getPopupMenu().add(newEnvironmentRef);
        }
	}	
}
