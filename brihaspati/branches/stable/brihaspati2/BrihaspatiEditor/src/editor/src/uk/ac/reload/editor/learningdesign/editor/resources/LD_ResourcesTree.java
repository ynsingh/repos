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

package uk.ac.reload.editor.learningdesign.editor.resources;

import java.awt.Point;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;

import javax.swing.tree.TreePath;

import org.jdom.Element;

import uk.ac.reload.dweezil.dnd.DNDUtils;
import uk.ac.reload.dweezil.dnd.DragObject;
import uk.ac.reload.dweezil.gui.tree.DweezilTree;
import uk.ac.reload.dweezil.gui.tree.DweezilTreeDragDropHandler;
import uk.ac.reload.dweezil.util.DweezilUIManager;
import uk.ac.reload.editor.contentpackaging.datamodel.CP_Resource;
import uk.ac.reload.editor.datamodel.DataModel;
import uk.ac.reload.editor.gui.Editor_Tree;
import uk.ac.reload.editor.gui.Editor_TreeNode;
import uk.ac.reload.editor.gui.Editor_TreeRenderer;
import uk.ac.reload.editor.learningdesign.datamodel.LD_Component;
import uk.ac.reload.editor.learningdesign.datamodel.LD_DataModel;
import uk.ac.reload.editor.learningdesign.datamodel.resources.LD_Resource;
import uk.ac.reload.editor.learningdesign.datamodel.resources.LD_Resources;


/**
 * The Tree to display the Resources
 * 
 * @author Phillip Beauvoir
 * @version $Id: LD_ResourcesTree.java,v 1.1 1998/03/26 15:40:32 ynsingh Exp $
 */
public class LD_ResourcesTree
extends Editor_Tree  {
    
    /**
     * Drag and Drop Handler
     */
    private ResourcesTreeDragDropHandler _dragdropHandler;

    /**
     * Default Constructor
     */
    public LD_ResourcesTree() {
        setEditor_TreeModel(new LD_ResourcesTreeModel(this));
        
		// Tree Renderer
		setCellRenderer(new Editor_TreeRenderer());

		// Drag and Drop handler
		_dragdropHandler = new ResourcesTreeDragDropHandler(this);
    }

    /**
     * Set the DataModel
     * @param ldDataModel The DataModel
     */
    public void setDataModel(DataModel dataModel) {
        super.setDataModel(dataModel);

        // Expand node
		expandNode(getEditor_TreeModel().getRootNode(), true);
        
		// Select first node
		selectRootNode();
    }
	
    
    //	==============================================================================
	//	DRAG AND DROP HANDLER
	//	==============================================================================
	
	/**
	 * Drag and drop handler
	 */
	class ResourcesTreeDragDropHandler extends DweezilTreeDragDropHandler {

        /**
         * Constructor
         * @param tree
         */
        public ResourcesTreeDragDropHandler(DweezilTree tree) {
            super(tree);
        }
	    
    	// =================== Drag and Drop Target events =========================
    	
    	/**
    	 * We are dragging and want to know whether we can drop
    	 */
    	public boolean isDropOK(DropTargetDragEvent event) {
    		// Get the node we're dragging over
    		Editor_TreeNode targetNode = (Editor_TreeNode)getDragOverTreeNode(event);
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
    						// Return whether the CP/LD can accept this
    					    LD_Component ldComponent = (LD_Component)targetNode.getUserObject();
    						return (ldComponent instanceof LD_Resources)
    								|| (ldComponent instanceof LD_Resource);
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
    		
    		Editor_TreeNode targetNode = (Editor_TreeNode)treePath.getLastPathComponent();
    		
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
					    LD_Component ldComponent = (LD_Component)targetNode.getUserObject();
					    Element element = ldComponent.getElement();
    					Element newElement = ((LD_DataModel)getDataModel()).getLearningDesign().addCP_Resources((CP_Resource[]) userObject, element);
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
