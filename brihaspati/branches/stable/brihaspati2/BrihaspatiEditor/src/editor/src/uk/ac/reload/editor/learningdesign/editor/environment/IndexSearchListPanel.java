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

package uk.ac.reload.editor.learningdesign.editor.environment;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.*;
import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.Icon;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import uk.ac.reload.dweezil.gui.CoolBarPanel;
import uk.ac.reload.dweezil.gui.IComponentSelectionListener;
import uk.ac.reload.dweezil.menu.DweezilMenuEvent;
import uk.ac.reload.dweezil.menu.MenuAction;
import uk.ac.reload.dweezil.menu.ProxyAction;
import uk.ac.reload.dweezil.util.DweezilUIManager;
import uk.ac.reload.editor.IIcons;
import uk.ac.reload.editor.datamodel.DataComponent;
import uk.ac.reload.editor.datamodel.DataModel;
import uk.ac.reload.editor.datamodel.IDataComponentIcon;
import uk.ac.reload.editor.datamodel.IDataModelListener;
import uk.ac.reload.editor.learningdesign.datamodel.components.environments.IndexSearch;
import uk.ac.reload.editor.menu.MainMenu;


/**
 * A List View to display index search elements
 * 
 * @author Phillip Beauvoir
 * @version $Id: IndexSearchListPanel.java,v 1.1 1998/03/26 15:40:32 ynsingh Exp $
 */
public abstract class IndexSearchListPanel
extends CoolBarPanel
implements IDataModelListener, IIcons 
{
    /**
     * The DataModel
     */
    private DataModel _dataModel;
    
    /**
     * IndexSearch component
     */
    private IndexSearch _is;

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
	 * The Proxy Delete Handler
	 */
    private ProxyDeleteHandler _deleteHandler;
    
    
    /**
     * Default Constructor
     * @param title The title to display in the CoolBar
     * @param icon The icon to display in the CoolBar
     * @param listHeight The height of the list
     */
    protected IndexSearchListPanel(String title, Icon icon, int listHeight) {
		setText(title);
		setIcon(icon);
		
		_menuAdd = new MenuAction_Add();
		addMenuActionToCoolBar(_menuAdd);

		_menuDelete = new MenuAction_Delete();
		addMenuActionToCoolBar(_menuDelete);

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
		
		// Our proxy handlers need to listen to list focus as well as list selection events
		getList().addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                if(!e.isTemporary()) {
                    _deleteHandler.update();
                }
            }

            public void focusLost(FocusEvent e) {
                if(!e.isTemporary()) {
                    _deleteHandler.clear();
                }
            }
		});

    }
    
    /**
     * Set the IndexSearch component
     * @param is
     */
    public void setIndexSearch(IndexSearch is) {
        _is = is;
        
        // Lazily set the DataModel
        if(getDataModel() == null) {
            setDataModel(is.getDataModel());
        }

        updateListModel();
        updateMenus();
    }
    
    /**
     * @return The IndexSearch component
     */
    public IndexSearch getIndexSearch() {
        return _is;
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
        
	    // Proxy Menu Handlers
	    _deleteHandler.update();
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
	 * Over-ride to Clean up
	 */
	public void cleanup() {
	    // Unregister and clear the proxy menus
        _deleteHandler.clear();
        
        if(_dataModel != null) {
            _dataModel.removeIDataModelListener(this);
        }
        
        // This is important
        _dataModel = null;
	}

	
    //	========================= String List Model ==================================
	
	protected class StringListModel
	extends AbstractListModel
	{

	    private String[] _strings;
	    
	    /**
	     * Default Constructor
	     */
	    public StringListModel(String[] strings) {
	        _strings = strings;
	    }
	    
        /* (non-Javadoc)
         * @see javax.swing.ListModel#getSize()
         */
        public int getSize() {
            return _strings.length;
        }

        /* (non-Javadoc)
         * @see javax.swing.ListModel#getElementAt(int)
         */
        public Object getElementAt(int index) {
            return _strings[index];
        }
 
	}
	
    // =========================== Renderer ==============================================
    
    /**
     * Cell renderer
     */
	protected class ComponentListRenderer
    extends DefaultListCellRenderer {
	    Icon indexClassIcon = DweezilUIManager.getIcon(ICON_NODE);
	    
        public ComponentListRenderer() {
        }

        public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            if(value instanceof IDataComponentIcon) {
                setIcon(((IDataComponentIcon)value).getIcon());
            }
            else {
                setIcon(indexClassIcon);
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
	
	//========================= Component Listener Events ==================================

    public void componentAdded(DataComponent component) {
    }

    public void componentRemoved(DataComponent component) {
    }

    public void componentMoved(DataComponent component) {
    }

    public void componentChanged(DataComponent component) {
        if(component == getIndexSearch()) {
            updateListModel();
        }
    }
}
