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

package uk.ac.reload.editor.learningdesign.editor.roles;

import java.awt.event.ActionEvent;

import uk.ac.reload.dweezil.gui.tree.DweezilTreeNode;
import uk.ac.reload.dweezil.menu.MenuAction;
import uk.ac.reload.dweezil.menu.PopupMenuAction;
import uk.ac.reload.dweezil.util.DweezilUIManager;
import uk.ac.reload.editor.datamodel.DataComponent;
import uk.ac.reload.editor.gui.Editor_Tree;
import uk.ac.reload.editor.learningdesign.datamodel.components.roles.Learner;
import uk.ac.reload.editor.learningdesign.datamodel.components.roles.Learner_Grouping;
import uk.ac.reload.editor.learningdesign.datamodel.components.roles.Role;
import uk.ac.reload.editor.learningdesign.datamodel.components.roles.Staff;
import uk.ac.reload.editor.learningdesign.datamodel.components.roles.Staff_Grouping;
import uk.ac.reload.editor.learningdesign.editor.shared.LD_TreePanel;
import uk.ac.reload.moonunit.learningdesign.LD_Core;

/**
 * Panel that contains the Roles Tree
 *
 * @author Phillip Beauvoir
 * @version $Id: RoleTreePanel.java,v 1.1 1998/03/26 15:40:32 ynsingh Exp $
 */
public class RoleTreePanel
extends LD_TreePanel
{
    /**
     * The Role JTree in this Panel
     */
    private RoleTree _tree;
    
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
	 * Constructor
     */
	public RoleTreePanel() {
		setText("Roles");
	    
		setIcon(DweezilUIManager.getIcon(ICON_ROLES));
		
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
	 * @return The Tree
	 */
	public Editor_Tree getTree() {
	    if(_tree == null) {
	        _tree = new RoleTree();
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
	    
	    // Add Menu Items
	    _menuAdd.newLearner.setEnabled(component instanceof Learner
	            						|| component instanceof Learner_Grouping);
	    
	    _menuAdd.newStaff.setEnabled(component instanceof Staff
	            						|| component instanceof Staff_Grouping);
	    
	    // Delete
	    _menuDelete.setEnabled(component != null && component.canDelete());
	    
	    // Rename
	    _menuRename.setEnabled(component instanceof Role);

        // Move up
	    _menuMoveUp.setEnabled(component != null && component.canMoveUp());
        
        // Move down
        _menuMoveDown.setEnabled(component != null && component.canMoveDown());

        // Popup Menu
	    getTree().getPopupMenu().removeAll();
	    getTree().getPopupMenu().add(_menuAdd.newLearner);
	    getTree().getPopupMenu().add(_menuAdd.newStaff);
	    getTree().getPopupMenu().addSeparator();
	    getTree().getPopupMenu().add(_menuDelete);
	    getTree().getPopupMenu().add(_menuRename);
	}


    // ====================================================================================
	
	/**
	 * "Add" Menu Button
	 */
	class MenuAction_Add extends PopupMenuAction {
	    MenuAction newLearner;
	    MenuAction newStaff;
	    
	    public MenuAction_Add() {
			super("Add", ICON_ADD);
			
			newLearner = new MenuAction("New Learner Role", ICON_LEARNER) {
                public void actionPerformed(ActionEvent e) {
                    addNewElement("New Learner Role", getMenuIcon(), LD_Core.LEARNER);
                }
			};
			getPopupMenu().add(newLearner);
			
			newStaff = new MenuAction("New Staff Role", ICON_STAFF) {
                public void actionPerformed(ActionEvent e) {
                    addNewElement("New Staff Role", getMenuIcon(), LD_Core.STAFF);
                }
			};
			getPopupMenu().add(newStaff);
		}
	}	

}
