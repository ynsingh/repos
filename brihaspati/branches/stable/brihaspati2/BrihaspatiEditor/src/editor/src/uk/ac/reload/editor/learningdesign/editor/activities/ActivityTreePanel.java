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

package uk.ac.reload.editor.learningdesign.editor.activities;

import java.awt.event.ActionEvent;

import uk.ac.reload.dweezil.gui.tree.DweezilTreeNode;
import uk.ac.reload.dweezil.menu.MenuAction;
import uk.ac.reload.dweezil.menu.PopupMenuAction;
import uk.ac.reload.dweezil.util.DweezilUIManager;
import uk.ac.reload.editor.datamodel.DataComponent;
import uk.ac.reload.editor.gui.Editor_Tree;
import uk.ac.reload.editor.learningdesign.datamodel.components.activities.Activity;
import uk.ac.reload.editor.learningdesign.datamodel.components.activities.ActivityStructure_Grouping;
import uk.ac.reload.editor.learningdesign.datamodel.components.activities.LearningActivity_Grouping;
import uk.ac.reload.editor.learningdesign.datamodel.components.activities.SupportActivity_Grouping;
import uk.ac.reload.editor.learningdesign.editor.shared.LD_TreePanel;
import uk.ac.reload.moonunit.learningdesign.LD_Core;

/**
 * Activity Tree Panel
 *
 * @author Phillip Beauvoir
 * @version $Id: ActivityTreePanel.java,v 1.1 1998/03/26 15:40:32 ynsingh Exp $
 */
public class ActivityTreePanel
extends LD_TreePanel
{
    /**
     * The Activity JTree in this Panel
     */
    private ActivityTree _tree;
    
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
	public ActivityTreePanel() {
		setText("Activities");
	    
		setIcon(DweezilUIManager.getIcon(ICON_ACTIVITIES));
		
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
	        _tree = new ActivityTree();
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
	    _menuAdd.newLearningActivity.setEnabled(component instanceof LearningActivity_Grouping);
	    _menuAdd.newSupportActivity.setEnabled(component instanceof SupportActivity_Grouping);
	    _menuAdd.newActivityStructure.setEnabled(component instanceof ActivityStructure_Grouping);
	    
	    // Delete
        _menuDelete.setEnabled(component != null && component.canDelete());
	    
        // Rename
	    _menuRename.setEnabled(component instanceof Activity);
        
        // Move up
	    _menuMoveUp.setEnabled(component != null && component.canMoveUp());
        
        // Move down
        _menuMoveDown.setEnabled(component != null && component.canMoveDown());

        // Popup Menu
	    getTree().getPopupMenu().removeAll();
	    getTree().getPopupMenu().add(_menuAdd.newLearningActivity);
	    getTree().getPopupMenu().add(_menuAdd.newSupportActivity);
	    getTree().getPopupMenu().add(_menuAdd.newActivityStructure);
	    getTree().getPopupMenu().addSeparator();
	    getTree().getPopupMenu().add(_menuDelete);
	    getTree().getPopupMenu().add(_menuRename);
	}

	// ====================================================================================
	
	/**
	 * "Add" Menu Button
	 */
	class MenuAction_Add extends PopupMenuAction {
	    MenuAction newLearningActivity;
	    MenuAction newSupportActivity;
	    MenuAction newActivityStructure;
	    
	    public MenuAction_Add() {
			super("Add", ICON_ADD);
			
			newLearningActivity = new MenuAction("New Learning Activity", ICON_LEARNINGACTIVITY) {
                public void actionPerformed(ActionEvent e) {
                    addNewElement("New Learning Activity", getMenuIcon(), LD_Core.LEARNING_ACTIVITY);
                }
			};
			getPopupMenu().add(newLearningActivity);
			
			newSupportActivity = new MenuAction("New Support Activity", ICON_SUPPORTACTIVITY) {
                public void actionPerformed(ActionEvent e) {
                    addNewElement("New Support Activity", getMenuIcon(), LD_Core.SUPPORT_ACTIVITY);
                }
			};
			getPopupMenu().add(newSupportActivity);

			newActivityStructure = new MenuAction("New Activity Structure", ICON_ACTIVITYSTRUCTURE) {
                public void actionPerformed(ActionEvent e) {
                    addNewElement("New Activity Structure", getMenuIcon(), LD_Core.ACTIVITY_STRUCTURE);
                }
			};
			getPopupMenu().add(newActivityStructure);
		}
	}	
}
