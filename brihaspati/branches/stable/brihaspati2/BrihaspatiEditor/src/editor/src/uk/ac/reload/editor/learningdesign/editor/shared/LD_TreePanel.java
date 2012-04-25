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

package uk.ac.reload.editor.learningdesign.editor.shared;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.Icon;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

import uk.ac.reload.dweezil.gui.CoolBarPanel;
import uk.ac.reload.dweezil.gui.IComponentSelectionListener;
import uk.ac.reload.dweezil.gui.tree.DweezilTreeNode;
import uk.ac.reload.dweezil.menu.DweezilMenuEvent;
import uk.ac.reload.dweezil.menu.MenuAction;
import uk.ac.reload.dweezil.menu.ProxyAction;
import uk.ac.reload.editor.EditorFrame;
import uk.ac.reload.editor.IIcons;
import uk.ac.reload.editor.datamodel.DataComponent;
import uk.ac.reload.editor.datamodel.DataModel;
import uk.ac.reload.editor.datamodel.IDataComponentIcon;
import uk.ac.reload.editor.gui.Editor_Tree;
import uk.ac.reload.editor.menu.MainMenu;

/**
 * Panel that contains the Main Tree
 *
 * @author Phillip Beauvoir
 * @version $Id: LD_TreePanel.java,v 1.1 1998/03/26 15:40:32 ynsingh Exp $
 */
public abstract class LD_TreePanel
extends CoolBarPanel
implements IIcons
{
    /**
     * The DataModel
     */
    private DataModel _dataModel;
	
	/**
	 * The Proxy Delete Handler
	 */
    private ProxyDeleteHandler _deleteHandler;
    
	/**
	 * The Proxy MoveUp Handler
	 */
    private ProxyMoveUpHandler _moveUpHandler;

	/**
	 * The Proxy MoveDown Handler
	 */
    private ProxyMoveDownHandler _moveDownHandler;
    

    /**
	 * Constructor
     */
	protected LD_TreePanel() {
		JScrollPane sp = new JScrollPane(getTree());
		sp.setBorder(null);
		setMainComponent(sp);
		
		// Listen to Tree selections
		getTree().addTreeSelectionListener(new TreeSelectionListener() {
            public void valueChanged(TreeSelectionEvent e) {
                updateMenus();
            }
		});
		
		// We'll forward on CoolBar focus to the tree
		addComponentSelectionListener(new IComponentSelectionListener() {
            public void componentSelected(Component component) {
                getTree().requestFocus();
            }
		});
		
		// Our delete proxy handler need to listen to tree focus as well as tree selection events
		getTree().addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                if(!e.isTemporary()) {
                    getProxyDeleteHandler().update();
                    getProxyMoveUpHandler().update();
                    getProxyMoveDownHandler().update();
                }
            }

            public void focusLost(FocusEvent e) {
                if(!e.isTemporary()) {
                    getProxyDeleteHandler().clear();
                    getProxyMoveUpHandler().clear();
                    getProxyMoveDownHandler().clear();
                }
            }
		});

	}
	
	/**
	 * Set the DataModel
	 * @param dataModel The DataModel
	 */
	public void setDataModel(DataModel dataModel) {
	    _dataModel = dataModel;
	    getTree().setDataModel(dataModel);
	}
	
	/**
	 * @return The DataModel
	 */
	public DataModel getDataModel() {
	    return _dataModel;
	}
	
	/**
	 * @return The ProxyDeleteHandler
	 */
	public ProxyDeleteHandler getProxyDeleteHandler() {
	    if(_deleteHandler == null) {
	        _deleteHandler = new ProxyDeleteHandler();
	    }
	    return _deleteHandler;
	}

	/**
	 * @return The ProxyMoveUpHandler
	 */
	public ProxyMoveUpHandler getProxyMoveUpHandler() {
	    if(_moveUpHandler == null) {
	        _moveUpHandler = new ProxyMoveUpHandler();
	    }
	    return _moveUpHandler;
	}

	/**
	 * @return The ProxyMoveDownHandler
	 */
	public ProxyMoveDownHandler getProxyMoveDownHandler() {
	    if(_moveDownHandler == null) {
	        _moveDownHandler = new ProxyMoveDownHandler();
	    }
	    return _moveDownHandler;
	}

	/**
	 * @return The Editor_Tree
	 */
	public abstract Editor_Tree getTree();
	
	/**
	 * Update the Menu Items
	 */
	public synchronized void updateMenus() {
	    // Proxy Menu Handlers
	    getProxyDeleteHandler().update();
	    getProxyMoveUpHandler().update();
	    getProxyMoveDownHandler().update();
	}

	/**
	 * Add a new Element to the XMLDocument depending on the currently selected Tree Node
	 * @param message The displayed message
	 * @param icon The Icon in the message box
	 */
	public void addNewElement(String message, Icon icon, String elementName) {
        String title = (String)JOptionPane.showInputDialog(EditorFrame.getInstance(),
	            "Title",
	            message,
	            JOptionPane.QUESTION_MESSAGE,
	            icon,
	            null, message);
        
        if(title != null) {
            getTree().addNewElement(elementName, title);
        }
	}
	
	/**
	 * Rename the currently selected Component
	 */
	protected void renameComponent() {
	    DataComponent component = null;
	    DweezilTreeNode selNode = getTree().getSelectedNode();
	    if(selNode != null) {
	        component = (DataComponent)selNode.getUserObject();
	    }
	    
	    if(component != null) {
	        Icon icon = null;
	        
	        if(component instanceof IDataComponentIcon) {
	            icon = ((IDataComponentIcon)component).getIcon();
	        }
	        
	        String title = (String)JOptionPane.showInputDialog(EditorFrame.getInstance(),
		            "Title",
		            "Rename",
		            JOptionPane.QUESTION_MESSAGE,
		            icon,
		            null, component.getTitle());
	        
	        if(title != null && !"".equals(title)) {
	            component.setTitle(title);
	            getDataModel().fireDataComponentChanged(component);
	        }
	    }
	}

	/**
	 * Delete currently selected components
	 */
	protected void deleteComponents() {
	    int result = JOptionPane.showConfirmDialog(EditorFrame.getInstance(),
	            "Are you sure you want to delete the component(s)?",
	            "Confirm Delete",
	            JOptionPane.YES_NO_OPTION);
	    
	    if(result == JOptionPane.YES_OPTION) {
	        getTree().removeSelectedElements();
	    }
	}
	
    /**
     * Move an item up
     */
    protected void moveItemUp() {
	    DataComponent component = null;
	    DweezilTreeNode selNode = getTree().getSelectedNode();
	    if(selNode != null) {
	        component = (DataComponent)selNode.getUserObject();
	    }
	    
	    if(component != null) {
	        component.moveUp();
	    }        
    }
    
    /**
     * Move an item down
     */
    protected void moveItemDown() {
	    DataComponent component = null;
	    DweezilTreeNode selNode = getTree().getSelectedNode();
	    if(selNode != null) {
	        component = (DataComponent)selNode.getUserObject();
	    }
	    
	    if(component != null) {
	        component.moveDown();
	    }        
    }

	/**
     * Clean up
     */
    public void cleanup() {
        if(getTree() != null) {
            getTree().cleanup();
        }
        
        if(_deleteHandler != null) {
	        _deleteHandler.clear();
	    }
        
        if(_moveUpHandler != null) {
            _moveUpHandler.clear();
	    }

        if(_moveDownHandler != null) {
            _moveDownHandler.clear();
	    }
        
        _dataModel = null;
    }

    // ====================================================================================
	
	/**
	 * "Delete" Menu Button
	 */
	protected class MenuAction_Delete extends MenuAction {
		public MenuAction_Delete() {
		    super("Delete", ICON_DELETE);
		}
		
		public void actionPerformed(ActionEvent e) {
		    deleteComponents();
		}
	}
	
	/**
	 * "Rename..." Menu Button
	 */
	protected class MenuAction_Rename extends MenuAction {
		public MenuAction_Rename() {
		    super("Rename...");
		}
		
		public void actionPerformed(ActionEvent e) {
		    renameComponent();
		}
	}	

	/**
	 * "Move Up" Menu Button
	 */
	protected class MenuAction_MoveUp extends MenuAction {
		public MenuAction_MoveUp() {
		    super("Move Up", ICON_UP);
		}
		
		public void actionPerformed(ActionEvent e) {
		    moveItemUp();
		}
	}	

	/**
	 * "Move Down" Menu Button
	 */
	protected class MenuAction_MoveDown extends MenuAction {
		public MenuAction_MoveDown() {
		    super("Move Down", ICON_DOWN);
		}
		
		public void actionPerformed(ActionEvent e) {
		    moveItemDown();
		}
	}	

	//	==============================================================================
	//	PROXY MENU HANDLERS
	//	==============================================================================
	
	/**
	 * Delete Proxy Action
	 */
	protected class ProxyDeleteHandler extends ProxyAction {
				
		public ProxyDeleteHandler() {
			super(MainMenu.getSharedInstance().editMenu.actionDelete);
		}
		
		public void menuActionPerformed(DweezilMenuEvent event) {
		    deleteComponents();
		}

		public void update() {
		    DataComponent component = null;
		    DweezilTreeNode selNode = getTree().getSelectedNode();
		    if(selNode != null) {
		        component = (DataComponent)selNode.getUserObject();
		    }
		    
		    if(component != null && component.canDelete()) {
		        setEnabled(true);
		        addListener();
		    }
            else {
                clear();
            }
		}
	}

	/**
	 * Move Up Proxy Action
	 */
	protected class ProxyMoveUpHandler extends ProxyAction {
				
		public ProxyMoveUpHandler() {
			super(MainMenu.getSharedInstance().editMenu.actionMoveUp);
		}
		
		public void menuActionPerformed(DweezilMenuEvent event) {
		    moveItemUp();
		}

		public void update() {
		    DataComponent component = null;
		    DweezilTreeNode selNode = getTree().getSelectedNode();
		    if(selNode != null) {
		        component = (DataComponent)selNode.getUserObject();
		    }

		    if(component != null && component.canMoveUp()) {
		        setEnabled(true);
		        addListener();
		    }
            else {
                clear();
            }
		}
	}

	/**
	 * Move Down Proxy Action
	 */
	protected class ProxyMoveDownHandler extends ProxyAction {
				
		public ProxyMoveDownHandler() {
			super(MainMenu.getSharedInstance().editMenu.actionMoveDown);
		}
		
		public void menuActionPerformed(DweezilMenuEvent event) {
		    moveItemDown();
		}

		public void update() {
		    DataComponent component = null;
		    DweezilTreeNode selNode = getTree().getSelectedNode();
		    if(selNode != null) {
		        component = (DataComponent)selNode.getUserObject();
		    }

		    if(component != null && component.canMoveDown()) {
		        setEnabled(true);
		        addListener();
		    }
            else {
                clear();
            }
		}
	}
}
