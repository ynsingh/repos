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

package uk.ac.reload.editor.learningdesign.editor.resources;

import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JOptionPane;

import org.jdom.Element;

import uk.ac.reload.dweezil.gui.tree.DweezilTreeNode;
import uk.ac.reload.dweezil.menu.DweezilMenuEvent;
import uk.ac.reload.dweezil.menu.MenuAction;
import uk.ac.reload.dweezil.menu.PopupMenuAction;
import uk.ac.reload.dweezil.menu.ProxyAction;
import uk.ac.reload.dweezil.util.DweezilUIManager;
import uk.ac.reload.dweezil.util.NativeLauncher;
import uk.ac.reload.editor.EditorFrame;
import uk.ac.reload.editor.datamodel.DataComponent;
import uk.ac.reload.editor.gui.Editor_Tree;
import uk.ac.reload.editor.learningdesign.datamodel.LD_DataModel;
import uk.ac.reload.editor.learningdesign.datamodel.resources.LD_Resource;
import uk.ac.reload.editor.learningdesign.datamodel.resources.LD_Resources;
import uk.ac.reload.editor.learningdesign.editor.shared.LD_TreePanel;
import uk.ac.reload.editor.menu.MainMenu;
import uk.ac.reload.moonunit.contentpackaging.CP_Core;

/**
 * Resources Tree Panel
 *
 * @author Phillip Beauvoir
 * @version $Id: LD_ResourcesTreePanel.java,v 1.1 1998/03/26 15:40:32 ynsingh Exp $
 */
public class LD_ResourcesTreePanel
extends LD_TreePanel
{
    /**
     * The Tree in this Panel
     */
    private LD_ResourcesTree _tree;
    
    /**
     * "Add" Menu Action
     */
    private MenuAction_Add _menuAdd;
    
    /**
     * "Delete" Menu Action
     */
    private MenuAction_Delete _menuDelete;
    
	/**
	 * The Proxy View File Handler
	 */
    private ProxyLaunchFileHandler _launchFileHandler;


    /**
	 * Constructor
     */
	public LD_ResourcesTreePanel() {
		setText("Resources");
		
		setIcon(DweezilUIManager.getIcon(ICON_RESOURCES));
	    
		_menuAdd = new MenuAction_Add();
		addMenuActionToCoolBar(_menuAdd);

		_menuDelete = new MenuAction_Delete();
		addMenuActionToCoolBar(_menuDelete);
		
		// View file Proxy Menu handler
		_launchFileHandler = new ProxyLaunchFileHandler();
		
		// Our proxy handler needs to listen to tree focus as well as tree selection events
		getTree().addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                if(!e.isTemporary()) {
                    _launchFileHandler.update();
                }
            }

            public void focusLost(FocusEvent e) {
                if(!e.isTemporary()) {
                    _launchFileHandler.clear();
                }
            }
		});
	}
	
	/**
	 * @return The Tree
	 */
	public Editor_Tree getTree() {
	    if(_tree == null) {
	        _tree = new LD_ResourcesTree();
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
	    _menuAdd.newResource.setEnabled(component instanceof LD_Resources);
	    _menuAdd.newFile.setEnabled(component instanceof LD_Resource);
	    _menuAdd.newDependency.setEnabled(component instanceof LD_Resource);
	    
	    // Remove
	    _menuDelete.setEnabled(component != null && component.canDelete());
	    
	    // Popup Menu - best to Remove all and re-add them
	    getTree().getPopupMenu().removeAll();
	    getTree().getPopupMenu().add(_menuAdd.newResource);
	    getTree().getPopupMenu().add(_menuAdd.newFile);
	    getTree().getPopupMenu().add(_menuAdd.newDependency);
	    getTree().getPopupMenu().addSeparator();
	    getTree().getPopupMenu().add(_menuDelete);
	    
	    // Proxy Menu Handlers
	    getProxyLaunchFileHandler().update();
	    getProxyDeleteHandler().update();
	}

	/**
	 * Add a new Element to the XMLDocument depending on the currently selected Tree Node
	 * @param menuAction The calling MenuAction
	 * @param elementName The name of the Element
	 */
	private void addNewResource(MenuAction menuAction) {
        String title = (String)JOptionPane.showInputDialog(EditorFrame.getInstance(),
	            "Location:",
	            menuAction.getText(),
	            JOptionPane.QUESTION_MESSAGE,
	            menuAction.getMenuIcon(),
	            null, "http://");
        
        if(title != null) {
            getTree().addNewElement(CP_Core.RESOURCE, title);
        }
	}
	
	/**
	 * @return The ProxyLaunchFileHandler
	 */
	public ProxyLaunchFileHandler getProxyLaunchFileHandler() {
	    if(_launchFileHandler == null) {
	        _launchFileHandler = new ProxyLaunchFileHandler();
	    }
	    return _launchFileHandler;
	}

	/**
	 * Clean up
	 */
	public void cleanup() {
	    // Unregister and clear the proxy menus
        if(_launchFileHandler != null) {
            _launchFileHandler.clear();
        }

        super.cleanup();
	}

	// ====================================================================================
	
	/**
	 * "Add" Menu Button
	 */
	class MenuAction_Add extends PopupMenuAction {
	    MenuAction newResource;
	    MenuAction newFile;
	    MenuAction newDependency;
	    
	    public MenuAction_Add() {
			super("Add", ICON_ADD);
			
			newResource = new MenuAction("New Resource", ICON_RESOURCE) {
                public void actionPerformed(ActionEvent e) {
                    addNewResource(this);
                }
			};
			getPopupMenu().add(newResource);
			
			newFile = new MenuAction("New File", ICON_FILE) {
                public void actionPerformed(ActionEvent e) {
                    getTree().addNewElement(CP_Core.FILE, null);
                }
			};
			getPopupMenu().add(newFile);

			newDependency = new MenuAction("New Dependency", ICON_DEPENDENCY) {
                public void actionPerformed(ActionEvent e) {
                    getTree().addNewElement(CP_Core.DEPENDENCY, null);
                }
			};
			getPopupMenu().add(newDependency);
		}
	}	

	//	==============================================================================
	//	PROXY ACTION HANDLERS
	//	==============================================================================

	
	/**
	 * Launch File Proxy Action
	 */
	class ProxyLaunchFileHandler extends ProxyAction {
		String url;
		
		public ProxyLaunchFileHandler() {
			super(MainMenu.getSharedInstance().actionViewFile);
		}
		
		/**
		 * Add ourselves as proxy listener and set enabled
		 */
		public void update() {
		    DataComponent component = null;
		    DweezilTreeNode selNode = getTree().getSelectedNode();
		    if(selNode != null) {
		        component = (DataComponent)selNode.getUserObject();
		    }
		    
		    if(component != null) {
			    Element element = component.getElement();
	            url = ((LD_DataModel)getDataModel()).getLearningDesign().getAbsoluteURL(element);
	            if(url != null) {
	                setEnabled(true);
	                addListener();
	            }
	            else {
	                clear();
	            }
		    }
		    else {
		        clear();
		    }
		}
		
		/**
		 * Launch the file
		 * @param event
		 */
		public void menuActionPerformed(DweezilMenuEvent event) {
			if(url != null) {
				// Start the loader thread
				Thread thread = new Thread() {
					public void run() {
					    setCursor(DweezilUIManager.WAIT_CURSOR);
						NativeLauncher.launchURL(url);
						setCursor(DweezilUIManager.DEFAULT_CURSOR);
					}
				};
				
				thread.start();
			}
		}
	}

}
