/**
 *  RELOAD TOOLS
 *
 *  Copyright (c) 2003 Oleg Liber, Bill Olivier, Phillip Beauvoir
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

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import uk.ac.reload.dweezil.gui.CoolBarPanel;
import uk.ac.reload.dweezil.gui.IComponentSelectionListener;
import uk.ac.reload.dweezil.menu.DweezilMenuEvent;
import uk.ac.reload.dweezil.menu.MenuAction;
import uk.ac.reload.dweezil.menu.ProxyAction;
import uk.ac.reload.editor.IIcons;
import uk.ac.reload.editor.datamodel.DataModel;
import uk.ac.reload.editor.datamodel.IDataComponentIcon;
import uk.ac.reload.editor.datamodel.IDataModelListener;
import uk.ac.reload.editor.learningdesign.datamodel.LD_Component;
import uk.ac.reload.editor.menu.MainMenu;


/**
 * A List View to display components
 * 
 * @author Phillip Beauvoir
 * @version $Id: ComponentListPanel.java,v 1.1 1998/03/26 15:40:32 ynsingh Exp $
 */
public abstract class ComponentListPanel
extends CoolBarPanel
implements IDataModelListener, IIcons 
{
    /**
     * The DataModel
     */
    private DataModel _dataModel;
    
    /**
     * The JList
     */
    private JList _list;
    
    /**
     * "Add" menu Item
     */
    private MenuAction_Add _menuAdd;
    
    /**
     * "Delete" menu Item
     */
    private MenuAction_Delete _menuDelete;
    
    /**
     * "Move Up" menu Item
     */
    private MenuAction_MoveUp _menuMoveUp;
    
    /**
     * "Move Down" menu Item
     */
    private MenuAction_MoveDown _menuMoveDown;
    
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
     * Default Constructor
     * @param title The title to display in the CoolBar
     * @param icon The icon to display in the CoolBar
     * @param listHeight The height of the list
     */
    protected ComponentListPanel(String title, Icon icon, int listHeight) {
		setText(title);
		setIcon(icon);
		
		_menuAdd = new MenuAction_Add();
		addMenuActionToCoolBar(_menuAdd);

		_menuDelete = new MenuAction_Delete();
		addMenuActionToCoolBar(_menuDelete);

		_menuMoveUp = new MenuAction_MoveUp();
		addMenuActionToCoolBar(_menuMoveUp);

		_menuMoveDown = new MenuAction_MoveDown();
		addMenuActionToCoolBar(_menuMoveDown);

        JScrollPane sp = new JScrollPane(getList());
        sp.setPreferredSize(new Dimension(0, listHeight));
        sp.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        setMainComponent(sp);
        
        // Tree Renderer
        getList().setCellRenderer(new ComponentListRenderer());
        
        // List Selection listener
        getList().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if(!e.getValueIsAdjusting()) {
                    updateMenus();
                }
            }
        });
        
		// We'll forward on CoolBar focus to the list
		addComponentSelectionListener(new IComponentSelectionListener() {
            public void componentSelected(Component component) {
                getList().requestFocus();
            }
		});

		// Highlight coolbar
        setSelected(true);
        
		// Proxy Menu handlers
		_deleteHandler = new ProxyDeleteHandler();
		_moveUpHandler = new ProxyMoveUpHandler();
		_moveDownHandler = new ProxyMoveDownHandler();
		
		// Our proxy handlers need to listen to list focus as well as list selection events
		getList().addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                if(!e.isTemporary()) {
                    _deleteHandler.update();
                    _moveUpHandler.update();
                    _moveDownHandler.update();
                }
            }

            public void focusLost(FocusEvent e) {
                if(!e.isTemporary()) {
                    _deleteHandler.clear();
                    _moveUpHandler.clear();
                    _moveDownHandler.clear();
                }
            }
		});

    }
    
    /**
     * Set the DataModel
     * We'll need to listen to Component changes so we can update on deletions and name changes.
     * @param dataModel The DataModel
     */
    public void setDataModel(DataModel dataModel) {
        _dataModel = dataModel;
        
		// Listen to DataModel changes
        _dataModel.addIDataModelListener(this);		
    }
    
    /**
     * @return The DataModel
     */
    public DataModel getDataModel() {
        return _dataModel;
    }

    /**
     * @return The JList
     */
    public JList getList() {
        if(_list == null) {
            _list = new JList();
        }
        
        return _list;
    }
    
    /**
     * Update the menu items
     */
    protected void updateMenus() {
        Object[] components = getList().getSelectedValues();
        
        _menuDelete.setEnabled(components.length > 0);
        _menuMoveUp.setEnabled(components.length > 0);
        _menuMoveDown.setEnabled(components.length > 0);
        
	    // Proxy Menu Handlers
	    _deleteHandler.update();
	    _moveUpHandler.update();
        _moveDownHandler.update();
    }
    
    /**
     * Set enabled or not
     * @param enabled
     */
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        
        if(!enabled) {
            _menuAdd.setEnabled(false);
            _menuDelete.setEnabled(false);
            _menuMoveUp.setEnabled(false);
            _menuMoveDown.setEnabled(false);
        }
        else {
            _menuAdd.setEnabled(true);
            updateMenus();
        }
        
        getList().setEnabled(enabled);
    }
    
    /**
     * Update the List Model
     */
    protected abstract void updateListModel();
    
    /**
     * Show the selector dialog
     */
    protected abstract void showSelectorDialog();
    
    /**
     * Delete selected list items
     */
    protected abstract void deleteListItems();
    
    /**
     * Move a list item up
     */
    protected abstract void moveListItemUp();
    
    /**
     * Move a list item down
     */
    protected abstract void moveListItemDown();
    
	/**
	 * Over-ride to Clean up
	 */
	public void cleanup() {
	    // Unregister and clear the proxy menus
        _deleteHandler.clear();
        _moveUpHandler.clear();
        _moveDownHandler.clear();
        
        if(_dataModel != null) {
            _dataModel.removeIDataModelListener(this);
        }
        
        // This is important
        _dataModel = null;
	}

	
	//	========================= List Model ==================================
	
	protected class ComponentListModel
	extends AbstractListModel
	{

	    private LD_Component[] _components;
	    
	    /**
	     * Default Constructor
	     */
	    public ComponentListModel(LD_Component[] components) {
	        _components = components;
	    }
	    
        /* (non-Javadoc)
         * @see javax.swing.ListModel#getSize()
         */
        public int getSize() {
            return _components.length;
        }

        /* (non-Javadoc)
         * @see javax.swing.ListModel#getElementAt(int)
         */
        public Object getElementAt(int index) {
            return _components[index];
        }

	}
	
	
    // =========================== Renderer ==============================================
    
    /**
     * Cell renderer
     */
	protected class ComponentListRenderer
    extends DefaultListCellRenderer {

        public ComponentListRenderer() {
        }

        public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            if(value instanceof IDataComponentIcon) {
                setIcon(((IDataComponentIcon)value).getIcon());
            }
            return this;
        }
    }
    
    
    //  =========================== Menu Actions ==============================================
    
	/**
	 * "Add" Menu Button
	 */
	protected class MenuAction_Add extends MenuAction {
		public MenuAction_Add() {
		    super("Add...", ICON_ADD);
		}
		
		public void actionPerformed(ActionEvent e) {
		    showSelectorDialog();
		}
	}	

	/**
	 * "Delete" Menu Button
	 */
	protected class MenuAction_Delete extends MenuAction {
		public MenuAction_Delete() {
		    super("Delete", ICON_DELETE);
		}
		
		public void actionPerformed(ActionEvent e) {
		    deleteListItems();
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
		    moveListItemUp();
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
		    moveListItemDown();
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
		    deleteListItems();
		}

		public void update() {
		    Object[] components = getList().getSelectedValues();
		    if(components.length > 0) {
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
		    moveListItemUp();
		}

		public void update() {
		    Object[] components = getList().getSelectedValues();
		    if(components.length > 0) {
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
		    moveListItemDown();
		}

		public void update() {
		    Object[] components = getList().getSelectedValues();
		    if(components.length > 0) {
		        setEnabled(true);
		        addListener();
		    }
            else {
                clear();
            }
		}
	}
}
