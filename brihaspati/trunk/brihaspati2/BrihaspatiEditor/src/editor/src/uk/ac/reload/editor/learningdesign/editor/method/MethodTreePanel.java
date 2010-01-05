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

package uk.ac.reload.editor.learningdesign.editor.method;

import java.awt.event.ActionEvent;

import uk.ac.reload.dweezil.gui.tree.DweezilTreeNode;
import uk.ac.reload.dweezil.menu.MenuAction;
import uk.ac.reload.dweezil.menu.PopupMenuAction;
import uk.ac.reload.dweezil.util.DweezilUIManager;
import uk.ac.reload.editor.datamodel.DataComponent;
import uk.ac.reload.editor.gui.Editor_Tree;
import uk.ac.reload.editor.learningdesign.datamodel.method.Act;
import uk.ac.reload.editor.learningdesign.datamodel.method.Method;
import uk.ac.reload.editor.learningdesign.datamodel.method.Play;
import uk.ac.reload.editor.learningdesign.datamodel.method.RolePart;
import uk.ac.reload.editor.learningdesign.editor.shared.LD_TreePanel;
import uk.ac.reload.moonunit.learningdesign.LD_Core;

/**
 * Method Tree Panel
 *
 * @author Phillip Beauvoir
 * @version $Id: MethodTreePanel.java,v 1.1 1998/03/26 15:40:32 ynsingh Exp $
 */
public class MethodTreePanel
extends LD_TreePanel
{
    /**
     * The Tree in this Panel
     */
    private MethodTree _tree;
    
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
	public MethodTreePanel() {
		setText("Method");
		
		setIcon(DweezilUIManager.getIcon(ICON_METHOD));
	    
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
	        _tree = new MethodTree();
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
	    _menuAdd.newPlay.setEnabled(component instanceof Method);
	    _menuAdd.newAct.setEnabled(component instanceof Play);
	    _menuAdd.newRolePart.setEnabled(component instanceof Act);
	    
	    // Delete
	    _menuDelete.setEnabled(component != null && component.canDelete());
	    
	    // Rename
	    _menuRename.setEnabled(component instanceof Play
	            || component instanceof Act
	            || component instanceof RolePart);
	    
        // Move up
	    _menuMoveUp.setEnabled(component != null && component.canMoveUp());
        
        // Move down
        _menuMoveDown.setEnabled(component != null && component.canMoveDown());
	    
	    // Popup Menu - best to Remove all and re-add them
	    getTree().getPopupMenu().removeAll();
	    getTree().getPopupMenu().add(_menuAdd.newPlay);
	    getTree().getPopupMenu().add(_menuAdd.newAct);
	    getTree().getPopupMenu().add(_menuAdd.newRolePart);
	    getTree().getPopupMenu().addSeparator();
	    getTree().getPopupMenu().add(_menuDelete);
	    getTree().getPopupMenu().add(_menuRename);
	}

    // ====================================================================================
	
	/**
	 * "Add" Menu Button
	 */
	class MenuAction_Add extends PopupMenuAction {
	    MenuAction newPlay;
	    MenuAction newAct;
	    MenuAction newRolePart;
	    
	    public MenuAction_Add() {
			super("Add", ICON_ADD);
			
			newPlay = new MenuAction("New Play", ICON_PLAY) {
                public void actionPerformed(ActionEvent e) {
                    addNewElement("New Play", getMenuIcon(), LD_Core.PLAY);
                }
			};
			getPopupMenu().add(newPlay);
			
			newAct = new MenuAction("New Act", ICON_ACT) {
                public void actionPerformed(ActionEvent e) {
                    addNewElement("New Act", getMenuIcon(), LD_Core.ACT);
                }
			};
			getPopupMenu().add(newAct);

			newRolePart = new MenuAction("New Role Part", ICON_ROLEPART) {
                public void actionPerformed(ActionEvent e) {
                    addNewElement("New Role Part", getMenuIcon(), LD_Core.ROLE_PART);
                }
			};
			getPopupMenu().add(newRolePart);
		}
	}	
}
