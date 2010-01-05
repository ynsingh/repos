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

package uk.ac.reload.editor.contentpackaging.editor.manifestview;

import java.awt.Point;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragSource;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.image.BufferedImage;
import java.util.Iterator;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;
import javax.swing.ToolTipManager;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import org.jdom.Element;

import uk.ac.reload.dweezil.dnd.DNDUtils;
import uk.ac.reload.dweezil.dnd.DragObject;
import uk.ac.reload.dweezil.gui.ErrorDialogBox;
import uk.ac.reload.dweezil.gui.tree.DweezilTree;
import uk.ac.reload.dweezil.gui.tree.DweezilTreeDragDropHandler;
import uk.ac.reload.dweezil.gui.tree.DweezilTreePopupHandler;
import uk.ac.reload.dweezil.menu.DweezilMenuEvent;
import uk.ac.reload.dweezil.menu.MenuAction;
import uk.ac.reload.dweezil.menu.ProxyAction;
import uk.ac.reload.dweezil.util.DweezilUIManager;
import uk.ac.reload.dweezil.util.NativeLauncher;
import uk.ac.reload.editor.EditorFrame;
import uk.ac.reload.editor.IIcons;
import uk.ac.reload.editor.Messages;
import uk.ac.reload.editor.contentpackaging.datamodel.CP_Resource;
import uk.ac.reload.editor.contentpackaging.editor.CP_Editor;
import uk.ac.reload.editor.contentpackaging.xml.ContentPackage;
import uk.ac.reload.editor.datamodel.ElementBinding;
import uk.ac.reload.editor.gui.ElementTreeNode;
import uk.ac.reload.editor.gui.TreeIconInterface;
import uk.ac.reload.editor.menu.MainMenu;
import uk.ac.reload.editor.menu.Menu_Edit;
import uk.ac.reload.editor.metadata.editor.MD_EditorDialog;
import uk.ac.reload.editor.properties.EditorProperties;
import uk.ac.reload.editor.scorm.editor.SCORM12_EditorDialog;
import uk.ac.reload.editor.scorm.xml.SCORM12_Package;
import uk.ac.reload.jdom.XMLDocument;
import uk.ac.reload.jdom.XMLDocumentClipboard;
import uk.ac.reload.jdom.XMLDocumentListener;
import uk.ac.reload.jdom.XMLDocumentListenerEvent;
import uk.ac.reload.moonunit.SchemaController;
import uk.ac.reload.moonunit.contentpackaging.CP_Core;
import uk.ac.reload.moonunit.schema.SchemaElement;


/**
 * A Viewer Tree that will display the CP Manifest.
 *
 * @author Phillip Beauvoir
 * @author Paul Sharples
 * @version $Id: ManifestTree.java,v 1.1 1998/03/26 15:40:31 ynsingh Exp $
 */
public class ManifestTree
extends DweezilTree
implements XMLDocumentListener, IIcons
{
	/**
	 * The Editor
	 */
    private CP_Editor _cpEditor;
	
	/**
	 * The Element Value/Attribute Edit Panel
	 */
    private ManifestInfoPanel _infoPanel;
	
	/**
	 * The Tree Model
	 */
    private ManifestTreeModel _treeModel;
    
    /**
     * Drag and Drop Handler
     */
    private ManifestTreeDragDropHandler _dragdropHandler;
	
	/**
	 * Popup Menu Handler
	 */
    private DweezilTreePopupHandler _popupMenuHandler;
	
	/**
	 * Keeping note of the current selected tree node means we don't have to do
	 * unneccesary updating
	 */
    private TreePath _currentTreePath;
	
	/**
	 * The Schema Controller
	 */
    private SchemaController _schemaController;
	
	/**
	 * The Edit menu which we will use
	 */
    private Menu_Edit _editMenu;
	
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
	 * The Proxy View File Handler
	 */
    private ProxyLaunchFileHandler _launchFileHandler;
	
	/**
	 * The Proxy Edit Metadata Handler
	 */
    private ProxyEditMetadataHandler _editMetadataHandler;
	
	/**
	 * The Proxy Edit SCORM Handler
	 */
    private ProxyEditSCORMHandler _editSCORMHandler;
	
	/**
	 * The CP Document
	 */
    private ContentPackage _contentPackage;
	
	
	/**
	 * Constructor
	 */
	public ManifestTree(CP_Editor cpEditor, ManifestInfoPanel infoPanel) {
		super();
		
		_treeModel = new ManifestTreeModel();
		setModel(_treeModel);
		
		_cpEditor = cpEditor;
		_infoPanel = infoPanel;
		
		// Cell renderer
		setCellRenderer(new ManifestTreeRenderer());
		
		// Tooltips
		ToolTipManager.sharedInstance().registerComponent(this);
		
		// Popup menu handler
		_popupMenuHandler = new DweezilTreePopupHandler(this);
		// Workaround - when a popupmenu is invoked, focus (temporary type) is lost
		// to the popup.  If user then moves to another window, focus is not lost
		// properly and therefore menu items are not cleared
		//popupMenu.setFocusable(false);
		
		// Get the Edit menu which we will use
		_editMenu = MainMenu.getSharedInstance().editMenu;
		
		// Remap key bindings
		_editMenu.remapKeyStrokes(this);
		
		// Drag and Drop handler
		_dragdropHandler = new ManifestTreeDragDropHandler(this);
		
		// Proxy Menu handlers
		_cutHandler = new ProxyCutHandler(_editMenu.actionCut);
		_copyHandler = new ProxyCopyHandler(_editMenu.actionCopy);
		_pasteHandler = new ProxyPasteHandler(_editMenu.actionPaste);
		_deleteHandler = new ProxyDeleteHandler(_editMenu.actionDelete);
		
		_moveUpHandler = new ProxyMoveUpHandler(_editMenu.actionMoveUp);
		_moveDownHandler = new ProxyMoveDownHandler(_editMenu.actionMoveDown);

		_editMetadataHandler = new ProxyEditMetadataHandler();
		_editSCORMHandler = new ProxyEditSCORMHandler();
		
		_launchFileHandler = new ProxyLaunchFileHandler();

		// Tree selection Listener
		addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent e) {
			    // Only interested in new selections
			    if(!e.isAddedPath()) {
			        return;
			    }
			    
				TreePath selPath = e.getPath();
				if(selPath != _currentTreePath) {
					updateMenus(selPath);
					updateInfoPanel(selPath);
					_currentTreePath = selPath;
				}
			}
		});
		
		// Focus Listener
		addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
				if(!e.isTemporary()) {
					updateMenus(getSelectionPath());
				}
			}
			
			public void focusLost(FocusEvent e) {
				if(!e.isTemporary()) {
					clearMenus();
				}
			}
		});
	}
	
	/**
	 * Set a new Content Package to be displayed
	 * @param cp
	 */
	public void setContentPackage(ContentPackage cp) {
		_contentPackage = cp;
		// Get the Schema Controller first!
		_schemaController = _contentPackage.getSchemaController();
		// Tell Tree Model
		_treeModel.setContentPackage(_contentPackage);
		// Select Root Node
		selectNode(_treeModel.getRootNode());
		// Add ourselves as Element Listener
		_contentPackage.addXMLDocumentListener(this);
	}
	
	/**
	 * Clean up
	 */
	public void cleanup() {
		_contentPackage.removeXMLDocumentListener(this);
		clearMenus();
	}
	
	/**
	 * Update the Info Panel according to the selected node on the tree.
	 * @param selPath The TreePath that is selected on the Tree.
	 */
	protected synchronized void updateInfoPanel(TreePath selPath) {
		if(_infoPanel != null) {
			if(selPath == null) {
				_infoPanel.clear();
				return;
			}
			
			Object selectedNode = selPath.getLastPathComponent();
			if(selectedNode instanceof ManifestTreeNode) {
				// Cast it
				ManifestTreeNode treeNode = (ManifestTreeNode) selectedNode;
				
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
	    updateMenus(getSelectionPath());
	}

	/**
	 * Update the menus according to the selected node on the tree.
	 * This has to be synchronized so different threads don't access it at the same time
	 * @param selPath The TreePath that is selected on the Tree.
	 */
	private synchronized void updateMenus(TreePath selPath) {
		// Clear the deck regardless
		clearMenus();
		
		if(selPath == null) {
		    return;
		}
		
		Object selectedNode = selPath.getLastPathComponent();
		if(selectedNode instanceof ManifestTreeNode) {
			ManifestTreeNode treeNode = (ManifestTreeNode)selectedNode;
			Element element = treeNode.getElement();
			SchemaElement schemaElement = treeNode.getSchemaElement();
			String elementName = element.getName();
			
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
					// Only certain nodes
					if(_treeModel.canAddNode(childSchemaElement)) {
						Action_AddChildElement actionAddElement = new Action_AddChildElement(element, childSchemaElement);
						popupMenu.add(actionAddElement);
						_editMenu.addAdditionalItem(actionAddElement);
					}
				}
			}
			
			
			// If we selected the "metadata" node show an "Edit Metadata" Menu item
			if(_contentPackage.isMetadataElement(elementName)) {
				// Separator
				_editMenu.addAdditionalItem(new JPopupMenu.Separator());
				popupMenu.addSeparator();
				
				Action_EditMetadata actionEditMD = new Action_EditMetadata(element);
				popupMenu.add(actionEditMD);
				_editMenu.addAdditionalItem(actionEditMD);
				
				// Menu Item Proxy
				_editMetadataHandler.update(element);
			}
			
			// SCORM Item
			else if(elementName.equals(CP_Core.ITEM) && _contentPackage instanceof SCORM12_Package) {
				if(schemaElement != null) {
					// Separator
					_editMenu.addAdditionalItem(new JPopupMenu.Separator());
					popupMenu.addSeparator();
					
					Action_EditSCORM actionEditSCORM = new Action_EditSCORM(element, schemaElement);
					popupMenu.add(actionEditSCORM);
					_editMenu.addAdditionalItem(actionEditSCORM);
					
					// Menu Item Proxy
					_editSCORMHandler.update(element, schemaElement);
				}
			}
			
			// If we selected a sub-Manifest node show a Menu item
			else if(elementName.equals(CP_Core.MANIFEST) && _contentPackage.isDocumentNamespace(element)) {
				_editMenu.addAdditionalItem(new JPopupMenu.Separator());
				popupMenu.addSeparator();
				
				// Import (Aggregate)
				Action_ImportManifest actionImportManifest = new Action_ImportManifest(element);
				popupMenu.add(actionImportManifest);
				_editMenu.addAdditionalItem(actionImportManifest);
				
				// Export (Disaggregate)
				if(!element.isRootElement()) {
					Action_ExportManifest actionExportManifest = new Action_ExportManifest(element);
					popupMenu.add(actionExportManifest);
					_editMenu.addAdditionalItem(actionExportManifest);
				}
			}
			
			// Find Referenced Node
			if(_contentPackage.isReferencingElement(element)) {
				_editMenu.addAdditionalItem(new JPopupMenu.Separator());
				popupMenu.addSeparator();
				
				Action_JumpToRefNode actionJumpNode = new Action_JumpToRefNode(element);
				_editMenu.addAdditionalItem(actionJumpNode);
				popupMenu.add(actionJumpNode);
			}
			
			// View file
			_launchFileHandler.update(element);
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
		_popupMenuHandler.setPopupMenu(new JPopupMenu());
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
		if(_launchFileHandler != null) {
		    _launchFileHandler.clear();
		}
		if(_editMetadataHandler != null) {
		    _editMetadataHandler.clear();
		}
		if(_editSCORMHandler != null) {
		    _editSCORMHandler.clear();
		}
	}
	
	/**
	 * Edit the Metadata in the Metadata dialog Editor
	 */
	protected void editMetadata(Element mdElement) {
	    MD_EditorDialog mdDialog = null;
	    
	    try {
	        if(_editMetadataHandler != null) {
	            _editMetadataHandler.setEnabled(false);  // block multiple key presses
	        }
	        EditorFrame.getInstance().setCursor(DweezilUIManager.WAIT_CURSOR);
	        mdDialog = new MD_EditorDialog(_contentPackage, mdElement);
	        mdDialog.show();
	    }
	    catch(Exception ex) {
	        if(EditorProperties.getString("DEBUG").equals("true")) { //$NON-NLS-1$ //$NON-NLS-2$
	            ex.printStackTrace();
	        }
	        if(mdDialog != null) {
	            mdDialog.dispose();
	        }
	        ErrorDialogBox.showWarning(Messages.getString("uk.ac.reload.editor.contentpackaging.manifestview.ManifestTree.0"), //$NON-NLS-1$
	                Messages.getString("uk.ac.reload.editor.contentpackaging.manifestview.ManifestTree.1"), //$NON-NLS-1$
	                ex);
	    }
	    finally {
	        EditorFrame.getInstance().setCursor(DweezilUIManager.DEFAULT_CURSOR);
	    }
	}
	
	/**
	 * Edit the SCORM Elements in the SCORM dialog Editor
	 */
	protected void editSCORM(final Element itemElement, final SchemaElement schemaElement) {
		Thread thread = new Thread() {
			SCORM12_EditorDialog editor;
			
			public void run() {
				if(_editSCORMHandler != null) {
				    _editSCORMHandler.setEnabled(false);  // block multiple key presses
				}
				editor = new SCORM12_EditorDialog(_contentPackage, itemElement, schemaElement);
				// Show the Window later in the Event queue
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						editor.show();
					}
				});
			}
		};
		
		thread.start();
	}
	
	////////////////////////ELEMENT LISTENER //////////////////////////////////////
	
	/**
	 * An Element was added to the Document so we must add a new Tree node
	 * @param event The XMLDocumentListenerEvent
	 */
	public void elementAdded(XMLDocumentListenerEvent event) {
		Element element = event.getElement();
		
		// Get Parent node
		Element parent = element.getParent();
		DefaultMutableTreeNode parentNode = getNode(parent);
		
		if(parentNode != null) {
			ManifestTreeNode newNode = addNode(parentNode, element);
			if(newNode != null && event.doSelect()) {
			    selectNode(newNode);
			}
		}
	}
	
	/**
	 * Add an Element
	 */
	private ManifestTreeNode addNode(DefaultMutableTreeNode parentNode, Element element) {
		ManifestTreeNode newNode = null;
		
		// Ascertain correct position to insert
		int index = getInsertNodePosition(parentNode, element);
		
		// If not allowed to insert, don't bother
		if(index != -1) {
			// New node
			newNode = new ManifestTreeNode(element);
			// Insert into model
			_treeModel.insertNodeInto(newNode, parentNode, index);
			
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
	 * @return where to correctly insert the node for an Element.
	 * Some nodes might not be visible and therefore the node to Elements count is wrong
	 * Return -1 if we shouldn't insert it
	 */
	protected int getInsertNodePosition(DefaultMutableTreeNode parentNode, Element element) {
		// Don't display certain nodes
		if(_treeModel.doShowNode(element) == false) {
		    return -1;
		}
		
		int index = 0;
		
		// Because we might not display certain nodes we have to compensate
		java.util.List children = element.getParent().getChildren();
		for(int i = 0; i < children.size(); i++) {
			Element child = (Element)children.get(i);
			if(child == element) {
			    break;
			}
			if(_treeModel.doShowNode(child)) {
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
	 * An Element was removed from the Document so we must find its tree node and
	 * remove that from the Tree.
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
				_treeModel.removeNodeFromParent(nodeToDelete);
				// Select previous node this will take care of updating menus
				selectNode(prevNode);
			}
			
			// Else we were sat on another node and need to update menus
			else {
				// Delete node
				_treeModel.removeNodeFromParent(nodeToDelete);
				// Update Menus
				updateMenus(getSelectionPath());
			}
		}
	}
	
	/**
	 * An Element was changed in some way (maybe the text or Attribute) so we
	 * inform the Tree Table Model
	 * @param event The XMLDocumentListenerEvent
	 */
	public void elementChanged(XMLDocumentListenerEvent event) {
		final DefaultMutableTreeNode node = getNode(event.getElement());
		if(node != null) {
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					_treeModel.nodeChanged(node);
				}
			});
		}
	}
	
	public void documentSaved(XMLDocument doc) {}
	
	//	==============================================================================
	//	ACTION MENU HANDLERS
	//	==============================================================================
	
	/**
	 * An Action Class that allows us to add a new Child Element to a Parent Element
	 * There will be some validity checks.
	 */
	class Action_AddChildElement extends AbstractAction {
		Element parentElement;
		SchemaElement childSchemaElement;
		String name;
		
		public Action_AddChildElement(Element parentElement, SchemaElement childSchemaElement) {
			this.parentElement = parentElement;
			this.childSchemaElement = childSchemaElement;
			
			// Set up name that will be displayed on the Menu
			name = _schemaController.getElementFriendlyName(childSchemaElement.getXMLPath());
			if(name == null) {
			    name = childSchemaElement.getName();
			}
			putValue(Action.NAME, Messages.getString("uk.ac.reload.editor.contentpackaging.manifestview.ManifestTree.2") + " " + name); //$NON-NLS-1$ //$NON-NLS-2$
			
			putValue(Action.SMALL_ICON,
			        ((TreeIconInterface)_schemaController).getLeafIcon(childSchemaElement.getName(),
			         childSchemaElement.getNamespace()));
			
			// Set Enabled according to whether we are allowed to add this child
			setEnabled(_contentPackage.canAddElement(parentElement, childSchemaElement));
		}
		
		public void actionPerformed(ActionEvent e) {
			// Add new Element
			Element element = _contentPackage.addElementBySchemaUndoable(ManifestTree.this, parentElement, childSchemaElement, true);
			// If Metadata Element, launch Editor
//			if(element != null) {
//			    if(_contentPackage.isMetadataElement(element.getName())) {
//			        editMetadata(element);
//			    }
//			}
		}
	}
	
	
	/**
	 * An Action Class that allows us to edit the Metadata
	 */
	class Action_EditMetadata extends AbstractAction {
		Element _element;
		
		public Action_EditMetadata(Element element) {
			super(Messages.getString("uk.ac.reload.editor.contentpackaging.manifestview.ManifestTree.3"), DweezilUIManager.getIcon(ICON_MD)); //$NON-NLS-1$
			_element = element;
		}
		
		public void actionPerformed(ActionEvent e) {
			editMetadata(_element);
		}
	}
	
	/**
	 * An Action Class that allows us to edit the SCORM Elements
	 */
	class Action_EditSCORM extends AbstractAction {
		Element _element;
		SchemaElement _schemaElement;
		
		public Action_EditSCORM(Element element, SchemaElement schemaElement) {
			super(Messages.getString("uk.ac.reload.editor.contentpackaging.manifestview.ManifestTree.4"), DweezilUIManager.getIcon(ICON_ADL)); //$NON-NLS-1$
			_element = element;
			_schemaElement = schemaElement;
		}
		
		public void actionPerformed(ActionEvent e) {
			editSCORM(_element, _schemaElement);
		}
	}
	
	/**
	 * An Action Class that allows us to Import (aggregate) a Manifest
	 */
	class Action_ImportManifest extends AbstractAction {
		Element element;
		
		public Action_ImportManifest(Element element) {
			super(Messages.getString("uk.ac.reload.editor.contentpackaging.manifestview.ManifestTree.5"), ((TreeIconInterface)_schemaController).getLeafIcon(element.getName(), //$NON-NLS-1$
			        element.getNamespace()));
			this.element = element;
		}
		
		public void actionPerformed(ActionEvent e) {
			// Loading of Document on Thread
			Thread thread = new Thread() {
				public void run() {
					_cpEditor.importManifest(element);
				}
			};
			
			thread.start();
		}
	}
	
	/**
	 * An Action Class that allows us to Export (disaggregate) a Manifest
	 */
	class Action_ExportManifest extends AbstractAction {
		Element element;
		
		public Action_ExportManifest(Element element) {
			super(Messages.getString("uk.ac.reload.editor.contentpackaging.manifestview.ManifestTree.6"), ((TreeIconInterface)_schemaController).getLeafIcon(element.getName(), //$NON-NLS-1$
			        element.getNamespace()));
			this.element = element;
		}
		
		public void actionPerformed(ActionEvent e) {
			// Loading of Document on Thread
			Thread thread = new Thread() {
				public void run() {
					_cpEditor.exportManifest(element);
				}
			};
			
			thread.start();
		}
	}
	
	/**
	 * An Action Class that allows us to jump to a referenced Node
	 */
	class Action_JumpToRefNode extends AbstractAction {
		Element element;
		
		public Action_JumpToRefNode(Element element) {
			super(Messages.getString("uk.ac.reload.editor.contentpackaging.manifestview.ManifestTree.7"), DweezilUIManager.getIcon(ICON_JUMP)); //$NON-NLS-1$
			this.element = element;
		}
		
		public void actionPerformed(ActionEvent e) {
			Element ref_element = _contentPackage.getReferencedElement(element);
			if(ref_element != null) {
			    selectNodeByObject(ref_element);
			}
		}
	}
	
	//	==============================================================================
	//	PROXY ACTION HANDLERS
	//	==============================================================================
	
	/**
	 * Move Up Element Proxy Action
	 */
	class ProxyMoveUpHandler extends ProxyAction {
		Element element;
		
		public ProxyMoveUpHandler(MenuAction proxyMenuAction) {
			super(proxyMenuAction);
		}
		
		public void update(Element element) {
			this.element = element;
			if(_contentPackage.canMoveElementUp(element)) {
			    setEnabled(true);
			    addListener();
			}
			else {
			    clear();
			}
		}

		public void menuActionPerformed(DweezilMenuEvent event) {
			_contentPackage.moveElementUp(ManifestTree.this, element, true);
		}
	}
	
	/**
	 * Move Down Element Proxy Action
	 */
	class ProxyMoveDownHandler extends ProxyAction {
		Element element;
		
		public ProxyMoveDownHandler(MenuAction proxyMenuAction) {
			super(proxyMenuAction);
		}
		
		public void update(Element element) {
			this.element = element;
			if(_contentPackage.canMoveElementDown(element)) {
			    setEnabled(true);
			    addListener();
			}
			else {
			    clear();
			}
		}
		
		public void menuActionPerformed(DweezilMenuEvent event) {
			_contentPackage.moveElementDown(ManifestTree.this, element, true);
		}
	}
	
	/**
	 * Delete Element Proxy Action
	 */
	class ProxyDeleteHandler extends ProxyAction {
		Element element;
		
		public ProxyDeleteHandler(MenuAction proxyMenuAction) {
			super(proxyMenuAction);
		}
		
		public void update(Element element) {
			this.element = element;
			if(_contentPackage.canDeleteElement(element)) {
			    setEnabled(true);
			    addListener();
			}
			else {
			    clear();
			}
		}
		
		public void menuActionPerformed(DweezilMenuEvent event) {
			_contentPackage.deleteElementUndoable(ManifestTree.this, element);
		}
	}
	
	/**
	 * Cut Element Proxy Action
	 */
	class ProxyCutHandler extends ProxyAction {
		Element element;
		
		public ProxyCutHandler(MenuAction proxyMenuAction) {
			super(proxyMenuAction);
		}
		
		public void update(Element element) {
			this.element = element;
			if(_contentPackage.canCutElement(element)) {
			    setEnabled(true);
			    addListener();
			}
			else {
			    clear();
			}
		}
		
		public void menuActionPerformed(DweezilMenuEvent event) {
			_contentPackage.cutElementUndoable(ManifestTree.this, element);
			XMLDocumentClipboard.addCutElement(element);
		}
	}
	
	/**
	 * Copy Element Proxy Action
	 */
	class ProxyCopyHandler extends ProxyAction {
		Element element;
		
		public ProxyCopyHandler(MenuAction proxyMenuAction) {
			super(proxyMenuAction);
		}
		
		public void update(Element element) {
			this.element = element;
			if(_contentPackage.canCopyElement(element)) {
			    setEnabled(true);
			    addListener();
			}
			else {
			    clear();
			}
		}
		
		public void menuActionPerformed(DweezilMenuEvent event) {
			XMLDocumentClipboard.addCopiedElement(element);
			// Update Menus
			updateMenus(getSelectionPath());
		}
	}
	
	/**
	 * Paste Element Proxy Action
	 */
	class ProxyPasteHandler extends ProxyAction {
		Element element;
		
		public ProxyPasteHandler(MenuAction proxyMenuAction) {
			super(proxyMenuAction);
		}
		
		public void update(Element element) {
			this.element = element;
			if(_contentPackage.canPasteFromClipboard(element)) {
			    setEnabled(true);
			    addListener();
			}
			else {
			    clear();
			}
		}

		public void menuActionPerformed(DweezilMenuEvent event) {
			if(_contentPackage.canPasteFromClipboard(element)) {
				Element clipBoardElement = XMLDocumentClipboard.getElement();
				_contentPackage.pasteElementUndoable(ManifestTree.this, clipBoardElement, element, false);
				// Update Menus
				updateMenus(getSelectionPath());
			}
		}
	}
	
	/**
	 * Edit Metadata Proxy Action
	 */
	class ProxyEditMetadataHandler extends ProxyAction {
		Element _element;
		
		public ProxyEditMetadataHandler() {
			super(MainMenu.getSharedInstance().actionEditMetadata);
		}
		
		public void update(Element element) {
		    _element = element;
		    setEnabled(true);
		    addListener();
		}
		
		public void menuActionPerformed(DweezilMenuEvent event) {
			editMetadata(_element);
		}
	}
	
	/**
	 * Edit SCORM Proxy Action
	 */
	class ProxyEditSCORMHandler extends ProxyAction {
		Element _element;
		SchemaElement _schemaElement;
		
		public ProxyEditSCORMHandler() {
			super(MainMenu.getSharedInstance().actionEditSCORM);
		}
		
		public void update(Element element, SchemaElement schemaElement) {
		    _element = element;
		    _schemaElement = schemaElement;
		    setEnabled(true);
		    addListener();
		}
		
		public void menuActionPerformed(DweezilMenuEvent event) {
			editSCORM(_element, _schemaElement);
		}
	}
	
	
	/**
	 * Launch File Proxy Action
	 */
	class ProxyLaunchFileHandler extends ProxyAction {
		String url;
		
		public ProxyLaunchFileHandler() {
			super(MainMenu.getSharedInstance().actionViewFile);
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
						NativeLauncher.launchURL(url);
					}
				};
				
				thread.start();
			}
		}
		
		public void update(Element element) {
			url = _contentPackage.getAbsoluteURL(element);
			if(url != null) {
			    addListener();
			    setEnabled(true);
			}
			else {
			    clear();
			}
		}
	}
	
	//	==============================================================================
	//	DRAG AND DROP HANDLER
	//	==============================================================================
	
	/**
	 * Drag and drop handler
	 */
	class ManifestTreeDragDropHandler extends DweezilTreeDragDropHandler {

        /**
         * Constructor
         * @param tree
         */
        public ManifestTreeDragDropHandler(DweezilTree tree) {
            super(tree);
        }
	    
    	// =================== Drag and Drop Source events =========================
    	
    	/**
    	 * A drag gesture has been initiated from this tree
    	 */
    	public void dragGestureRecognized(DragGestureEvent event) {
    		int action = event.getDragAction();
    		if((action & DnDConstants.ACTION_COPY_OR_MOVE) == 0) {
    		    return;
    		}
    		
    		ElementTreeNode node = (ElementTreeNode)getDragSourceTreeNode(event);
    		if(node != null) {
    			ElementBinding eb = node.createElementBinding();
    			if(_contentPackage.canDragElement(eb.getElement(), action)) {
    				DragObject dragObject = new DragObject(eb, ManifestTree.this);
    				
    				Point ptDragOrigin = event.getDragOrigin();
    				TreePath path = getPathForLocation(ptDragOrigin.x, ptDragOrigin.y);
    				if(path == null) {
    				    return;
    				}
    				
    				// Get the Drag Image
    				BufferedImage imgGhost = getDragImage(path, ptDragOrigin);
    				event.startDrag(DragSource.DefaultCopyNoDrop, imgGhost, new Point(5, 5), dragObject, this);
    			}
    		}
    	}
    	
    	// =================== Drag and Drop Target events =========================
    	
    	/**
    	 * We are dragging and want to know whether we can drop
    	 */
    	public boolean isDropOK(DropTargetDragEvent event) {
    		// Get the node we're dragging over
    		ElementTreeNode targetNode = (ElementTreeNode)getDragOverTreeNode(event);
    		if(targetNode == null) {
    		    return false;
    		}
    		
    		// Workaround for Java bug ID #4357494 and ID #4248542 and ID #4378091
    		DropTargetDropEvent tempDTDropEvent = new  DropTargetDropEvent(event.getDropTargetContext(),
    				event.getLocation(), DnDConstants.ACTION_COPY, 0);
    		Transferable transferable = tempDTDropEvent.getTransferable();
    		
    		// Find out whether we are the correct object
    		if(transferable.isDataFlavorSupported(DragObject.flavor)){
    			// Check for DnD Action
    			int action = DNDUtils.getCorrectDropContext(event);
    			// Copy & Move is OK
    			if((action & DnDConstants.ACTION_COPY) != 0 || (action & DnDConstants.ACTION_MOVE) != 0) {
    				// Get the User Object
    				try {
    					Object userObject = transferable.getTransferData(DragObject.flavor);
    					
    					// A CP_Resource array
    					if(userObject instanceof CP_Resource[]) {
    						// Return whether the CP can accept this
    						return _contentPackage.acceptsCP_Resources(targetNode.getElement());
    					}
    					
    					// An internal DnD
    					else if(userObject instanceof ElementBinding) {
    						// Return whether the CP can accept this
    						return _contentPackage.acceptElement(((ElementBinding)userObject).getElement(), targetNode.getElement(), action);
    					}
    				}
    				catch(Exception ex) {
    					ex.printStackTrace();
    				}
    			}
    			return false;
    		}
    		
    		else {
    		    return false;
    		}
    	}
    	
    	/**
    	 * We just Dropped something!
    	 */
    	public void drop(DropTargetDropEvent event) {
    		// Where did we drop it?
    		Point location = event.getLocation();
    		TreePath treePath = getPathForLocation(location.x, location.y);
    		if(treePath == null) {
    		    return;
    		}
    		
    		ElementTreeNode targetNode = (ElementTreeNode)treePath.getLastPathComponent();
    		
    		// Get what we dropped
    		Transferable transferable = event.getTransferable();
    		
    		// If we are the correct drag object
    		if(transferable.isDataFlavorSupported(DragObject.flavor)) {
    			// Accept the Drop
    			event.acceptDrop(DNDUtils.getCorrectDropContext(event));
    			
    			// Get the User Object and do something
    			try {
    				Object userObject = transferable.getTransferData(DragObject.flavor);
    				
    				// Get Action - Copy or Move
    				int action = DNDUtils.getCorrectDropContext(event);
    				
    				setCursor(DweezilUIManager.WAIT_CURSOR);
    				
    				// Dropped an array of CP_Resource(s)
    				if(userObject instanceof CP_Resource[]) {
    					Element newElement = _contentPackage.addCP_Resources((CP_Resource[]) userObject, targetNode.getElement());
    					selectNodeByObject(newElement);
    				}
    				
    				// Internal Drop
    				else if(userObject instanceof ElementBinding) {
    					_contentPackage.shiftElement(((ElementBinding) userObject).getElement(), targetNode.getElement(), action);
    				}
    			}
    			catch(Exception ex) {
    				ex.printStackTrace();
    			}
    			finally {
    				setCursor(DweezilUIManager.DEFAULT_CURSOR);
    			}
    			
    			hiliteNode(_prevHilitedNode, false);
    			event.getDropTargetContext().dropComplete(true);
    		}
    		
    		// Else we are not the right object
    		else {
    		    event.rejectDrop();
    		}
    	}
        
	}
}
