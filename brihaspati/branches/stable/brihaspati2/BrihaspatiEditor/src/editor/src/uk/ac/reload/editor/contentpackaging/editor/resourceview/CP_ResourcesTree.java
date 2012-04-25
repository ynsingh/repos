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

package uk.ac.reload.editor.contentpackaging.editor.resourceview;

import java.awt.Point;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragSource;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.AbstractList;
import java.util.Vector;

import javax.swing.JPopupMenu;
import javax.swing.ToolTipManager;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import javax.swing.tree.TreePath;

import uk.ac.reload.dweezil.dnd.DNDUtils;
import uk.ac.reload.dweezil.dnd.DragObject;
import uk.ac.reload.dweezil.gui.DweezilProgressMonitor;
import uk.ac.reload.dweezil.gui.ErrorDialogBox;
import uk.ac.reload.dweezil.gui.tree.DweezilTree;
import uk.ac.reload.dweezil.gui.tree.DweezilTreeDragDropHandler;
import uk.ac.reload.dweezil.gui.tree.DweezilTreePopupHandler;
import uk.ac.reload.dweezil.util.DweezilUIManager;
import uk.ac.reload.dweezil.util.NativeLauncher;
import uk.ac.reload.editor.EditorFrame;
import uk.ac.reload.editor.IIcons;
import uk.ac.reload.editor.Messages;
import uk.ac.reload.editor.contentpackaging.datamodel.CP_Resource;
import uk.ac.reload.editor.properties.EditorProperties;



/**
 * A Content Package Resources Tree for the CP UI
 *
 * @author Phillip Beauvoir
 * @author Paul Sharples
 * @version $Id: CP_ResourcesTree.java,v 1.1 1998/03/26 15:40:32 ynsingh Exp $
 */
public class CP_ResourcesTree
extends DweezilTree
implements IIcons
{
	/**
	 * The Tree Model
	 */
    private CP_ResourcesTreeModel _resourcesTreeModel;
	
    /**
     * Drag and Drop Handler
     */
    private CP_ResourcesTreeDragDropHandler _dragdropHandler;
	
	/**
	 * Popup Menu Handler
	 */
    private DweezilTreePopupHandler _popupMenuHandler;
	

    /**
	 * Constructor
	 */
	public CP_ResourcesTree() {
		_resourcesTreeModel = new CP_ResourcesTreeModel();
		setModel(_resourcesTreeModel);
		
		setCellRenderer(new CP_ResourcesTreeRenderer());
		
		// Tooltips
		ToolTipManager.sharedInstance().registerComponent(this);
		
		// Tree Expansion Listener
		addTreeExpansionListener(new TreeExpansionListener() {
			/**
			 * Tree Expansion Listener - get the next directory level down
			 */
			public void treeExpanded(TreeExpansionEvent e) {
			    TreePath path = e.getPath();
			    CP_ResourcesTreeNode node = (CP_ResourcesTreeNode)path.getLastPathComponent();
			    _resourcesTreeModel.buildChildNodes(node);
			}
			
			public void treeCollapsed(TreeExpansionEvent e) {
			}
		});

		// Listen for double-clicks to launch file
		addMouseListener(new MouseAdapter() {
			// Double-click launches file
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 2) {
				    TreePath path = getPathForLocation(e.getX(), e.getY());
					if(path != null) {
					    CP_ResourcesTreeNode targetNode = (CP_ResourcesTreeNode)path.getLastPathComponent();
					    if(targetNode != null && targetNode.getCP_Resource().isFile()) {
					        setCursor(DweezilUIManager.WAIT_CURSOR);
					        NativeLauncher.launchFile(targetNode.getCP_Resource());
					        setCursor(DweezilUIManager.DEFAULT_CURSOR);
					    }
					}
				}
			}
		});
		
		// Drag and Drop handler
		_dragdropHandler = new CP_ResourcesTreeDragDropHandler(this);
	}
	
	/**
	 * Set a new File View to be displayed
	 */
	public void setFileView(File rootFolder) {
		// Tell Model
		_resourcesTreeModel.setFileView(rootFolder);
		
		// Select Root Node
		selectNode(_resourcesTreeModel.getRootNode());
	}
	
    /**
     * @return The DweezilTreePopupHandler
     */
    public DweezilTreePopupHandler getDweezilTreePopupHandler() {
        if(_popupMenuHandler == null) {
            _popupMenuHandler = new DweezilTreePopupHandler(this);
        }
        return _popupMenuHandler;
    }

    /**
     * @return The PopupMenu
     */
    public JPopupMenu getPopupMenu() {
        return getDweezilTreePopupHandler().getPopupMenu();
    }

	/**
	 * Refresh the tree and model
	 */
	public void refresh() {
	    _resourcesTreeModel.refresh();
	    selectNode(_resourcesTreeModel.getRootNode());
	}
	
	/**
	 * @return which nodes the user has selected to delete
	 */
	public CP_Resource[] getSelectedResourcesToDelete(){
		Vector v = new Vector();
		TreePath[] paths = getSelectionPaths();
		if(paths != null) {
		    for(int i = 0; i < paths.length; i++) {
		        CP_ResourcesTreeNode fileSysEntity = (CP_ResourcesTreeNode)paths[i].getLastPathComponent();
		        // Need to make sure this does not pass the root folder back!
		        if(!fileSysEntity.isRoot()){
		            CP_Resource aFileToDelete = fileSysEntity.getCP_Resource();
		            v.add(aFileToDelete);
		        }
		    }
		}
		CP_Resource[] resources = new CP_Resource[v.size()];
		v.copyInto(resources);
		return resources;
	}
	
	/**
	 * Drop some files on a node
	 * @param files
	 */
	protected void addFiles(final File[] files, final CP_ResourcesTreeNode targetNode) {
		final File targetFolder = targetNode.getCP_Resource();
		
		// Sanity check
		if(!targetFolder.isDirectory()) {
		    return;
		}
		
		Thread thread = new Thread() {
			public void run() {
				DweezilProgressMonitor progressMonitor = new DweezilProgressMonitor(EditorFrame.getInstance(),
				        Messages.getString("uk.ac.reload.editor.contentpackaging.resourceview.CP_ResourcesTree.0"), //$NON-NLS-1$
						Messages.getString("uk.ac.reload.editor.contentpackaging.resourceview.CP_ResourcesTree.1"), //$NON-NLS-1$
						"", //$NON-NLS-1$
						true,
						DweezilUIManager.getIcon(ICON_APP16));
				
				try {
                    // Note - should we do dependencies?
                    new CP_ResourceImporter(files, targetFolder, false, progressMonitor);
                } catch(IOException ex) {
                    if(EditorProperties.getString("DEBUG").equals("true")) ex.printStackTrace(); //$NON-NLS-1$ //$NON-NLS-2$
                    ErrorDialogBox.showWarning(Messages.getString("uk.ac.reload.editor.contentpackaging.resourceview.CP_ResourcesTree.2"), //$NON-NLS-1$
                            Messages.getString("uk.ac.reload.editor.contentpackaging.resourceview.CP_ResourcesTree.3"), //$NON-NLS-1$
                            ex);
                }
                finally {
                    progressMonitor.close();
                }
				
				refresh();
			}
		};
		
		thread.start();
	}

	//	==============================================================================
	//	DRAG AND DROP HANDLER
	//	==============================================================================

	/**
	 * Drag and drop handler
	 */
	class CP_ResourcesTreeDragDropHandler extends DweezilTreeDragDropHandler {

        /**
         * Constructor
         * @param tree
         */
        public CP_ResourcesTreeDragDropHandler(DweezilTree tree) {
            super(tree);
        }
	    
    	// =================== Drag and Drop Source events =========================
    	
    	/**
    	 * A drag gesture has been initiated from this tree
    	 * <h3>Amendment History</h3>
    	 * <p>Basic outline code for handling a ghosted drag image.  This works
    	 * on the Mac, but not on PC.  Without this code on the Mac, a drag event
    	 * shows the whole JPanel as a ghosted image.<p/>
    	 */
    	public void dragGestureRecognized(DragGestureEvent event) {
    		if((event.getDragAction() & DnDConstants.ACTION_COPY_OR_MOVE) == 0) return;
    		
    		TreePath[] paths = getSelectionPaths();
    		if(paths != null) {
    			Vector v = new Vector();
    			
    			for(int i = 0; i < paths.length; i++) {
    				CP_ResourcesTreeNode node = (CP_ResourcesTreeNode) paths[i].getLastPathComponent();
    				v.add(node.getCP_Resource());
    			}
    			
    			if(v.isEmpty() == false) {
    				CP_Resource[] resources = new CP_Resource[v.size()];
    				v.copyInto(resources);
    				
    				DragObject dragObject = new DragObject(resources, CP_ResourcesTree.this);
    				
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
    	 * We accept Native Drops
    	 */
    	public boolean isDropOK(DropTargetDragEvent event) {
    		// Get the node we're dragging over
    		CP_ResourcesTreeNode targetNode = (CP_ResourcesTreeNode)getDragOverTreeNode(event);
    		if(targetNode == null) {
    		    return false;
    		}
    		
    		// Only allow to drop on a folder
    		CP_Resource file = targetNode.getCP_Resource();
    		if(!file.isDirectory()) {
    		    return false;
    		}
    		
    		// Only support javaFileListFlavor
    		DataFlavor[] flavors = event.getCurrentDataFlavors();
    		for(int i = 0; i < flavors.length; i++) {
    			if(flavors[i].equals(DataFlavor.javaFileListFlavor)) {
    				return true;
    			}
    		}
    		
    		return false;
    	}
    	
    	/**
    	 * We just Dropped some files
    	 */
    	public void drop(DropTargetDropEvent event) {
    		// Where did we drop it?
    		Point location = event.getLocation();
    		TreePath treePath = getPathForLocation(location.x, location.y);
    		if(treePath == null) return;
    		
    		CP_ResourcesTreeNode targetNode = (CP_ResourcesTreeNode)treePath.getLastPathComponent();
    		
    		// Get what we dropped
    		Transferable transferable = event.getTransferable();
    		
    		// If we are the correct drag object
    		if(transferable.isDataFlavorSupported(DataFlavor.javaFileListFlavor)){
    			// Accept the Drop
    			event.acceptDrop(DNDUtils.getCorrectDropContext(event));
    			
    			// Get the User Object and do something
    			try {
    				Object userObject = transferable.getTransferData(DataFlavor.javaFileListFlavor);
    				if(userObject instanceof AbstractList) {
    					AbstractList list = (AbstractList)userObject;
    					Object[] files = list.toArray();
    					addFiles((File[])files, targetNode);
    				}
    			}
    			catch(Exception ex) {
    				ex.printStackTrace();
    			}
    			finally {
    				
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