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

package uk.ac.reload.editor.metadata.editor.tableview;

import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Iterator;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JPopupMenu;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.TableColumnModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import org.jdom.Element;

import uk.ac.reload.dweezil.gui.treetable.DweezilTreeTable;
import uk.ac.reload.dweezil.gui.treetable.DweezilTreeTablePopupHandler;
import uk.ac.reload.dweezil.menu.DweezilMenuEvent;
import uk.ac.reload.dweezil.menu.MenuAction;
import uk.ac.reload.dweezil.menu.ProxyAction;
import uk.ac.reload.editor.Messages;
import uk.ac.reload.editor.datamodel.ElementBinding;
import uk.ac.reload.editor.gui.ElementInfoPanel;
import uk.ac.reload.editor.gui.TreeIconInterface;
import uk.ac.reload.editor.menu.Menu_Edit;
import uk.ac.reload.editor.metadata.xml.Metadata;
import uk.ac.reload.jdom.XMLDocument;
import uk.ac.reload.jdom.XMLDocumentClipboard;
import uk.ac.reload.jdom.XMLDocumentListener;
import uk.ac.reload.jdom.XMLDocumentListenerEvent;
import uk.ac.reload.moonunit.SchemaController;
import uk.ac.reload.moonunit.schema.SchemaElement;

/**
 * The Metadata Tree Table Editor
 *
 * @author Phillip Beauvoir
 * @version $Id: MD_TreeTable.java,v 1.1 1998/03/26 15:40:32 ynsingh Exp $
 */
public class MD_TreeTable
extends DweezilTreeTable
implements TreeSelectionListener, XMLDocumentListener
{
	/**
	 * The Element Value/Attribute Edit Panel
	 */
    private ElementInfoPanel _infoPanel;
	
	/**
	 * Popup Menu Handler
	 */
    private DweezilTreeTablePopupHandler _popupMenuHandler;
	
	/**
	 * The Metadata Document
	 */
    private Metadata _metadata;
	
	/**
	 * The Schema Controller
	 */
    private SchemaController _schemaController;
	
	/**
	 * The Edit menu which we will use
	 */
    private Menu_Edit _editMenu;
	
	/**
	 * Keeping note of the current selected tree node means we don't have to do
	 * unneccesary updating
	 */
    private TreePath _currentTreePath;
	
	/**
	 * The Proxy Delete Handler
	 */
    private ProxyDeleteHandler _deleteHandler;
	
	/**
	 * The Proxy Cut Handler
	 */
    private ProxyCutHandler _cutHandler;
	
	/**
	 * The Proxy Copy Handler
	 */
    private ProxyCopyHandler _copyHandler;
	
	/**
	 * The Proxy Paste Handler
	 */
    private ProxyPasteHandler _pasteHandler;
	
	/**
	 * The Proxy Move Up Handler
	 */
    private ProxyMoveUpHandler _moveUpHandler;
	
	/**
	 * The Proxy Move Down Handler
	 */
    private ProxyMoveDownHandler _moveDownHandler;
	
	
	/**
	 * Constructor
	 * @param treeTableModel The TreeTabelModel
	 * @param infoPanel the ElementInfoPanel that displays a tip, the value and attributes of a JDOM element
	 * @param editMenu the Edit Menu
	 */
	public MD_TreeTable(MD_TreeTableModel treeTableModel, ElementInfoPanel infoPanel, Menu_Edit editMenu) {
		super(treeTableModel);
		
		_infoPanel = infoPanel;
		
		// DOM Document
		_metadata = treeTableModel.getMetadata();
		
		// Add ourselves as Element Listener
		_metadata.addXMLDocumentListener(this);
		
		_schemaController = _metadata.getSchemaController();
		
		// Set up the Table
		getTableHeader().setReorderingAllowed(false);
		
		// Columns
		TableColumnModel tcm = getTableHeader().getColumnModel();
		tcm.getColumn(0).setPreferredWidth(100);
		tcm.getColumn(1).setPreferredWidth(300);
		tcm.getColumn(2).setPreferredWidth(80);
		//tcm.getColumn(2).setMaxWidth(80);
		
		setShowVerticalLines(true);
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		// Make sure we have a renderer for the Tree
		getTree().setCellRenderer(new MD_TreeRenderer());
		
		// We listen for tree selections
		getTree().addTreeSelectionListener(this);
		
		// Popup menu handler
		_popupMenuHandler = new DweezilTreeTablePopupHandler(this);
		// Workaround - when a popupmenu is invoked, focus (temporary type) is lost
		// to the popup.  If user then moves to another window, focus is not lost
		// properly and therefore menu items are not cleared
		//popupMenu.setFocusable(false);
		
		// Focus Listener
		addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
				if(!e.isTemporary()) {
					updateMenus();
				}
			}
			
			public void focusLost(FocusEvent e) {
				if(!e.isTemporary()) {
					clearMenus();
				}
			}
		});
		
		// Get the Edit menu which we will use
		_editMenu = editMenu;
		
		// Remap key bindings
		_editMenu.remapKeyStrokes(this);
		
		// Proxy Menu handlers
		_cutHandler = new ProxyCutHandler(_editMenu.actionCut);
		_copyHandler = new ProxyCopyHandler(_editMenu.actionCopy);
		_pasteHandler = new ProxyPasteHandler(_editMenu.actionPaste);
		_deleteHandler = new ProxyDeleteHandler(_editMenu.actionDelete);
		_moveUpHandler = new ProxyMoveUpHandler(_editMenu.actionMoveUp);
		_moveDownHandler = new ProxyMoveDownHandler(_editMenu.actionMoveDown);
	}
	
	/**
	 * Clean up
	 */
	public void cleanup() {
		_metadata.removeXMLDocumentListener(this);
		clearMenus();
	}
	
	/**
	 * Refresh the view
	 */
	public void refresh() {
		updateInfoPanel(getTree().getSelectionPath());
	}
	
	/**
	 * A node has been selected on the tree.
	 * @param event The TreeSelectionEvent fired from us being a TreeSelectionListener
	 */
	public void valueChanged(TreeSelectionEvent event) {
		TreePath selPath = event.getPath();
		if(selPath != _currentTreePath) {
			updateMenus(selPath);
			updateInfoPanel(selPath);
			_currentTreePath = selPath;
		}
	}
	
	/**
	 * Update the Info Panel according to the selected node on the tree.
	 * @param selPath the path of the selected node on the tree
	 */
	protected synchronized void updateInfoPanel(TreePath selPath) {
		if(_infoPanel != null) {
			if(selPath == null) {
				_infoPanel.clear();
				return;
			}
			
			Object selectedNode = selPath.getLastPathComponent();
			if(selectedNode instanceof MD_TreeNode) {
				// Cast it
				MD_TreeNode treeNode = (MD_TreeNode) selectedNode;
				
				// And get the ElementBinding
				ElementBinding eb = treeNode.createElementBinding();
				
				// Tell the panel
				_infoPanel.setElementBinding(eb);
			}
		}
	}
	
	/**
	 * Public access to update the menus
	 */
	public void updateMenus() {
	    updateMenus(getTree().getSelectionPath());
	}
	
	/**
	 * Update the menus according to the selected node on the tree.
	 * This has to be synchronized so different threads don't access it at the same time
	 * @param selPath the path of the selected node on the tree
	 */
	private synchronized void updateMenus(TreePath selPath) {
		// Clear the deck regardless
		clearMenus();
		
		if(selPath == null) {
		    return;
		}
		
		Object selectedNode = selPath.getLastPathComponent();
		if(selectedNode instanceof MD_TreeNode) {
			MD_TreeNode treeNode = (MD_TreeNode) selectedNode;
			Element element = treeNode.getElement();
			SchemaElement schemaElement = treeNode.getSchemaElement();
			
			JPopupMenu popupMenu = _popupMenuHandler.getPopupMenu();

			// Cut Handler
			_cutHandler.update(element);
			popupMenu.add(_cutHandler.getMenuAction());
			// Copy Handler
			_copyHandler.update(element);
			popupMenu.add(_copyHandler.getMenuAction());
			// Paste Handler
			_pasteHandler.update(element);
			popupMenu.add(_pasteHandler.getMenuAction());
			// Delete Handler
			_deleteHandler.update(element);
			popupMenu.add(_deleteHandler.getMenuAction());
			popupMenu.addSeparator();
			
			// Move Up Handler
			_moveUpHandler.update(element);
			popupMenu.add(_moveUpHandler.getMenuAction());
			
			// Move Down Handler
			_moveDownHandler.update(element);
			popupMenu.add(_moveDownHandler.getMenuAction());
			
			
			// If the Schema Element has Children display them in the menu
			if(schemaElement != null && schemaElement.hasChildren()) {
				// Separator
				_editMenu.addAdditionalItem(new JPopupMenu.Separator());
				popupMenu.addSeparator();
				
				SchemaElement[] children = schemaElement.getChildren();
				for(int i = 0; i < children.length; i++) {
					SchemaElement childSchemaElement = children[i];
					if(((MD_TreeTableModel)getTreeTableModel()).canAddNode(childSchemaElement)) {
						Action_AddChildElement actionAddElement = new Action_AddChildElement(element, childSchemaElement);
						popupMenu.add(actionAddElement);
						_editMenu.addAdditionalItem(actionAddElement);
					}
				}
			}
		}
	}
	
	/**
	 * Clear any popup or other menus
	 */
	public synchronized void clearMenus() {
		// We can remove all from the popup menu
		//_popupMenu.removeAll();
		// create a new pop up menu because otherwise macos X does not
		// respond after the L&F has been updated
	    JPopupMenu popupMenu = new JPopupMenu();
	    _popupMenuHandler.setPopupMenu(popupMenu);
	    
		// Remove any menu items we created
		_editMenu.removeAdditionalItems();
		
		// Clear the Handlers
		if(_cutHandler != null) {
		    _cutHandler.clear();
		}
		
		if(_copyHandler != null) {
		    _copyHandler.clear();
		}
		
		if(_pasteHandler != null) {
		    _pasteHandler.clear();
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
	}
	
	
    //////////////////////// ELEMENT LISTENER //////////////////////////////////////
	
	/**
	 * An Element was added to the the Document so we must add it to the Tree
	 * at the correct node position.
	 * @param event the XMLDocumentListenerEvent event
	 */
	public void elementAdded(XMLDocumentListenerEvent event) {
		Element element = event.getElement();
		
		// Get Parent node
		Element parent = element.getParent();
		DefaultMutableTreeNode parentNode = getNode(parent);
		
		if(parentNode != null) {
			MD_TreeNode newNode = addNode(parentNode, element);
			if(newNode != null && event.doSelect()) {
			    selectNode(newNode);
			}
		}
	}
	
	/**
	 * Add an Element
	 * @param parentNode the parent tree node
	 * @param element the element to be added to the parent
	 */
	private MD_TreeNode addNode(DefaultMutableTreeNode parentNode, Element element) {
		MD_TreeNode newNode = null;
		
		// Ascertain correct position to insert
		int index = getInsertNodePosition(parentNode, element);
		
		// If not allowed to insert, don't bother
		if(index != -1) {
			// New node
			newNode = new MD_TreeNode(element);
			// Insert into model
			((DefaultTreeModel)getTreeTableModel()).insertNodeInto(newNode, parentNode, index);
			
			// Children
			Iterator it = element.getChildren().iterator();
			while(it.hasNext()) {
				Element child = (Element) it.next();
				addNode(newNode, child);
			}
		}
		
		return newNode;
	}
	
	/**
	 * Determine where to correctly insert the node for an Element.
	 * Some nodes might not be visible and therefore the node to Elements count is wrong.
	 * Return -1 if we shouldn't insert it.
	 * @param parentNode the parent tree node
	 *	      element the element to be added to the parent
	 * @return correct insert position of the node for an element, or -1 if we should'nt insert it
	 */
	protected int getInsertNodePosition(DefaultMutableTreeNode parentNode, Element element) {
		// Don't display certain nodes
		if(((MD_TreeTableModel)getTreeTableModel()).doShowNode(element) == false) {
		    return -1;
		}
		
		int index = 0;
		
		// Because we might not display certain nodes we have to compensate
		java.util.List children = element.getParent().getChildren();
		for(int i = 0; i < children.size(); i++) {
			Element child = (Element) children.get(i);
			if(child == element) {
			    break;
			}
			if(((MD_TreeTableModel) getTreeTableModel()).doShowNode(child)) {
			    index++;
			}
		}
		
		// Make sure we don't exceed the child count
		if(index > parentNode.getChildCount()) {
		    index = parentNode.getChildCount();
		}
		if(index < 0) {
		    index = 0;
		}
		
		return index;
	}
	
	/**
	 * An Element was removed from the Document so we must find it tree node and
	 * remove that from the Tree.
	 * @param event the XMLDocumentListenerEvent event
	 */
	public void elementRemoved(XMLDocumentListenerEvent event) {
		// Find the node
		Element element = event.getElement();
		
		DefaultMutableTreeNode nodeToDelete = getNode(element);
		if(nodeToDelete != null) {
			// The currently selected node is a descendent of/same as the node being deleted
			if(nodeToDelete.isNodeDescendant(getSelectedNode()))  {
				// Save sibling/parent
				DefaultMutableTreeNode prevNode = nodeToDelete.getPreviousSibling();
				if(prevNode == null) {
				    prevNode = (DefaultMutableTreeNode)nodeToDelete.getParent();
				}
				
				// Delete node
				((DefaultTreeModel)getTreeTableModel()).removeNodeFromParent(nodeToDelete);
				// Select previous node this will take care of updating menus
				selectNode(prevNode);
			}
			
			// Else we were sat on another node and need to update menus
			else {
				// Delete node
				((DefaultTreeModel)getTreeTableModel()).removeNodeFromParent(nodeToDelete);
				// Update Menus
				updateMenus(getTree().getSelectionPath());
			}
		}
	}
	
	
	/**
	 * An Element was changed in some way (maybe the text or Attribute) so we
	 * inform the Tree Table Model
	 * @param event the XMLDocumentListenerEvent event
	 */
	public void elementChanged(XMLDocumentListenerEvent event) {
		//DefaultMutableTreeNode node = getNode(event.getElement());
		//if(node != null) getTreeTableModel().nodeChanged(node);
		
		final DefaultMutableTreeNode node = getNode(event.getElement());
		if(node != null)
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					((DefaultTreeModel)getTreeTableModel()).nodeChanged(node);
				}
			});
	}
	
	/**
	 * The SchemaDocument was saved (do nothing).
	 */
	public void documentSaved(XMLDocument doc) {}
	
//	==============================================================================
//	ADD HANDLER
//	==============================================================================
	
	/**
	 * An Action Class that allows us to add a new Child Element to a Parent Element
	 * There will be some validity checks.
	 */
	class Action_AddChildElement extends AbstractAction {
		
		/**
		 * The parent element
		 */
		Element parentElement;
		
		/**
		 * The schema element of the child element that we want to add
		 */
		SchemaElement childSchemaElement;
		
		/**
		 * The name that will be displayed on the Menu
		 */
		String name;
		
		/**
		 * Constructor
		 * @param parentElement the parent element
		 * @param childSchemaElement the schema element of the child element that we want to add
		 */
		public Action_AddChildElement(Element parentElement, SchemaElement childSchemaElement) {
			this.parentElement = parentElement;
			this.childSchemaElement = childSchemaElement;
			
			// Set up name that will be displayed on the Menu
			name = _schemaController.getElementFriendlyName(childSchemaElement.getXMLPath());
			if(name == null) {
			    name = childSchemaElement.getName();
			}
			putValue(Action.NAME,
			        Messages.getString("uk.ac.reload.editor.metadata.tableview.MD_TreeTable.0") //$NON-NLS-1$
			        + " " + name); //$NON-NLS-1$
			
			putValue(Action.SMALL_ICON,
			        ((TreeIconInterface)_schemaController).getLeafIcon(childSchemaElement.getName(),
			                childSchemaElement.getNamespace()));
			
			// Set Enabled according to whether we are allowed to add this child
			setEnabled(_metadata.canAddElement(parentElement, childSchemaElement));
		}
		
		/**
		 * Add action to be performed
		 * @param e the ActionEvent
		 */
		public void actionPerformed(ActionEvent e) {
			_metadata.addElementBySchemaUndoable(MD_TreeTable.this, parentElement, childSchemaElement, true);
		}
	}
	
	
	//	==============================================================================
	//	PROXY ACTION HANDLERS
	//	==============================================================================
	
	/**
	 * Move Up Element Proxy Action
	 */
	class ProxyMoveUpHandler extends ProxyAction {
		/**
		 * The element to move up
		 */
		Element element;
		
		/**
		 * Constructor
		 * @param proxyMenuAction the MenuAction
		 */
		public ProxyMoveUpHandler(MenuAction proxyMenuAction) {
			super(proxyMenuAction);
		}
		
		public void update(Element element) {
			this.element = element;
			if(_metadata.canMoveElementUp(element)) {
			    setEnabled(true);
			    addListener();
			}
			else {
			    clear();
			}
		}

		/**
		 * The move up action to be performed when the menu item is selected
		 * @param event the DweezilMenuEvent
		 */
		public void menuActionPerformed(DweezilMenuEvent event) {
			_metadata.moveElementUp(MD_TreeTable.this, element, true);
		}
	}
	
	/**
	 * Move Down Element Proxy Action
	 */
	class ProxyMoveDownHandler extends ProxyAction {
		
		/**
		 * The element to move down
		 */
		Element element;
		
		/**
		 * Constructor
		 * @param proxyMenuAction the MenuAction
		 */
		public ProxyMoveDownHandler(MenuAction proxyMenuAction) {
			super(proxyMenuAction);
		}
		
		public void update(Element element) {
			this.element = element;
			if(_metadata.canMoveElementDown(element)) {
			    setEnabled(true);
			    addListener();
			}
			else {
			    clear();
			}
		}
		/**
		 * The move down action to be performed when the menu item is selected
		 * @param event the DweezilMenuEvent
		 */
		public void menuActionPerformed(DweezilMenuEvent event) {
			_metadata.moveElementDown(MD_TreeTable.this, element, true);
		}
	}
	
	/**
	 * Delete Element Proxy Action
	 */
	class ProxyDeleteHandler extends ProxyAction {
		
		/**
		 * The element to delete
		 */
		Element element;
		
		/**
		 * Constructor
		 * @param proxyMenuAction the MenuAction
		 */
		public ProxyDeleteHandler(MenuAction proxyMenuAction) {
			super(proxyMenuAction);
		}
		
		public void update(Element element) {
			this.element = element;
			if(_metadata.canDeleteElement(element)) {
			    setEnabled(true);
			    addListener();
			}
			else {
			    clear();
			}
		}
		
		/**
		 * The delete element action to be performed when the menu item is selected
		 * @param event the DweezilMenuEvent
		 */
		public void menuActionPerformed(DweezilMenuEvent event) {
			_metadata.deleteElementUndoable(MD_TreeTable.this, element);
		}
	}
	
	/**
	 * Cut Element Proxy Action
	 */
	class ProxyCutHandler extends ProxyAction {
		
		/**
		 * The element to cut
		 */
		Element element;
		
		/**
		 * Constructor
		 * @param proxyMenuAction the MenuAction
		 */
		public ProxyCutHandler(MenuAction proxyMenuAction) {
			super(proxyMenuAction);
		}
		
		public void update(Element element) {
			this.element = element;
			if(_metadata.canCutElement(element)) {
			    setEnabled(true);
			    addListener();
			}
			else {
			    clear();
			}
		}
		
		/**
		 * The cut element action to be performed when the menu item is selected
		 * @param event the DweezilMenuEvent
		 */
		public void menuActionPerformed(DweezilMenuEvent event) {
			_metadata.cutElementUndoable(MD_TreeTable.this, element);
			XMLDocumentClipboard.addCutElement(element);
		}
	}
	
	/**
	 * Copy Element Proxy Action
	 */
	class ProxyCopyHandler extends ProxyAction {
		
		/**
		 * The element to copy
		 */
		Element element;
		
		/**
		 * Constructor
		 * @param proxyMenuAction the MenuAction
		 * @param element the element to copy
		 */
		public ProxyCopyHandler(MenuAction proxyMenuAction) {
			super(proxyMenuAction);
		}
		
		public void update(Element element) {
			this.element = element;
			if(_metadata.canCopyElement(element)) {
			    setEnabled(true);
			    addListener();
			}
			else {
			    clear();
			}
		}
		
		/**
		 * The copy element action to be performed when the menu item is selected
		 * @param event the DweezilMenuEvent
		 */
		public void menuActionPerformed(DweezilMenuEvent event) {
			XMLDocumentClipboard.addCopiedElement(element);
			// Update Menus
			updateMenus(getTree().getSelectionPath());
		}
	}
	
	/**
	 * Paste Element Proxy Action
	 */
	class ProxyPasteHandler extends ProxyAction {
		
	    /**
		 * The element to paste
		 */
		Element element;
		
		/**
		 * Constructor
		 * @param proxyMenuAction the MenuAction
		 */
		public ProxyPasteHandler(MenuAction proxyMenuAction) {
			super(proxyMenuAction);
		}
		
		public void update(Element element) {
			this.element = element;
			if(_metadata.canPasteFromClipboard(element)) {
			    setEnabled(true);
			    addListener();
			}
			else {
			    clear();
			}
		}
		
		/**
		 * The paste element action to be performed when the menu item is selected
		 * @param event the DweezilMenuEvent
		 */
		public void menuActionPerformed(DweezilMenuEvent event) {
			if(_metadata.canPasteFromClipboard(element)) {
				Element clipBoardElement = XMLDocumentClipboard.getElement();
				_metadata.pasteElementUndoable(MD_TreeTable.this, clipBoardElement, element, false);
				// Update Menus
				updateMenus(getTree().getSelectionPath());
			}
		}
	}
}
