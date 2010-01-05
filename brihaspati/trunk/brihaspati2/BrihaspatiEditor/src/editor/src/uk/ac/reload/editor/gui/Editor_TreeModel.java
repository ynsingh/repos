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

package uk.ac.reload.editor.gui;

import javax.swing.SwingUtilities;
import javax.swing.tree.DefaultMutableTreeNode;

import uk.ac.reload.dweezil.gui.tree.DweezilTree;
import uk.ac.reload.dweezil.gui.tree.DweezilTreeModel;
import uk.ac.reload.editor.datamodel.DataComponent;
import uk.ac.reload.editor.datamodel.DataModel;
import uk.ac.reload.editor.datamodel.IDataModelListener;
import uk.ac.reload.editor.learningdesign.datamodel.LD_Component;


/**
 * Tree Model for LD_Tree
 * 
 * @author Phillip Beauvoir
 * @version $Id: Editor_TreeModel.java,v 1.1 1998/03/26 15:24:11 ynsingh Exp $
 */
public abstract class Editor_TreeModel
extends DweezilTreeModel
implements IDataModelListener 
{
    /**
     * The Owning Tree
     */
    private DweezilTree _tree;
    
    /**
     * The DataModel
     */
    private DataModel _dataModel;

    /**
     * Whether or not to select the tree node when added
     */
    private boolean _doSelectNodeOnAdd = false;
    
    /**
     * Default Constructor
     */
    protected Editor_TreeModel() {
    }

    /**
     * Constructor
     * @param tree The Owning Tree
     */
    protected Editor_TreeModel(DweezilTree tree) {
        _tree = tree;
    }
    
    /**
     * @return The Tree
     */
    public DweezilTree getTree() {
        return _tree;
    }
    
    /**
     * Set the DataModel
     * @param dataModel The DataModel
     */
    public void setDataModel(DataModel dataModel) {
        _dataModel = dataModel;
    }
    
    /**
     * @return The DataModel
     */
    public DataModel getDataModel() {
        return _dataModel;
    }
    
    /**
     * Build the child tree nodes from the DOM
     * @param node Starting node
     */
    protected void buildChildren(Editor_TreeNode node) {
        Object obj = node.getUserObject();
        
        if(obj instanceof DataComponent) {
            DataComponent component = (LD_Component)obj;
            if(component.hasChildren()) {
                DataComponent[] children = component.getChildren();
                for(int i = 0; i < children.length; i++) {
                    Editor_TreeNode childNode = new Editor_TreeNode(children[i]);
                    node.add(childNode);
                    buildChildren(childNode);
                }
            }
        }
    }

    /**
     * Add a Tree node and any of its children
     * @param parentNode parent tree node
     * @param component The LD_Component to wrap in a node
     * @return The added Node
     */
    protected DefaultMutableTreeNode addNode(DefaultMutableTreeNode parentNode, DataComponent component) {
		// Ascertain correct position to insert
		int index = getInsertNodePosition(component);

		Editor_TreeNode newNode = new Editor_TreeNode(component);
        insertNodeInto(newNode, parentNode, index);
        
		// Add Children
        if(component.hasChildren()) {
            DataComponent[] children = component.getChildren();
            for(int i = 0; i < children.length; i++) {
                addNode(newNode, children[i]); 
            }
        }

		return newNode;
    }

	/**
	 * @return where to correctly insert the node.
	 */
    protected int getInsertNodePosition(DataComponent component) {
		int index = 0;
		
		if(component.getParent() != null) {
		    DataComponent parent = component.getParent();
		    index = parent.indexOf(component);
		    if(index == -1) {
		        index = 0;
		    }
		}
		
		return index;
	}
    
    /**
     * Set whether or not to select the tree node when added
     * @param select If true the tree node will be selected when added
     */
    public void setSelectNodeOnAdd(boolean select) {
        _doSelectNodeOnAdd = select;
    }

    /**
     * @return True if we are to select the tree node when added
     */
    public boolean doSetSelectNodeOnAdd() {
        return _doSelectNodeOnAdd;
    }
    
    /**
     * Clean up
     */
    public void cleanup() {
        if(_dataModel != null) {
            _dataModel.removeIDataModelListener(this);
        }
        
        _dataModel = null;
    }

    //========================= Component Listener Events ==================================
    
    /**
     * We heard that a LD_Component has been added to the Data Model...
     * So add a new Tree node
     * @param component The LD_Component added
     */
    public void componentAdded(DataComponent component) {
        if(component != null) {
            // Do we have its parent on this tree?
            DataComponent parentComponent = component.getParent();
            DefaultMutableTreeNode parentNode = _tree.getNode(parentComponent);
            // If we do, then add the child node
            if(parentNode != null) {
                DefaultMutableTreeNode childNode = addNode(parentNode, component);
                // Should we select it?
                if(_doSelectNodeOnAdd) {
                    _tree.selectNode(childNode);
                    _tree.expandNode(childNode, true);
                }
                else {
                    _tree.setSelectionPath(null); // Deselect and reselect forces update
                    _tree.selectNode(parentNode);
                    _tree.expandNode(parentNode, true);
                    _tree.expandNode(childNode, true);
                }
            }
        }
    }
    
	/**
     * We heard that a DataComponent has been removed from the Data Model...
     * So remove the Tree node if we have it
     * @param component The DataComponent removed
     */
    public void componentRemoved(DataComponent component) {
        if(component != null) {
    		DefaultMutableTreeNode nodeToDelete = _tree.getNode(component);
    		if(nodeToDelete != null) {
    		    // Save previous node to select
    		    DefaultMutableTreeNode prevNode = null;
    		    
    		    // The currently selected node is a descendent of/same as the node being deleted
    			if(nodeToDelete.isNodeDescendant(_tree.getSelectedNode()))  {
    				// Save sibling/parent
    				prevNode = nodeToDelete.getPreviousSibling();
    				if(prevNode == null) {
    				    prevNode = (DefaultMutableTreeNode)nodeToDelete.getParent();
    				}
    			}
    			
				// Delete node
				removeNodeFromParent(nodeToDelete);
				
				// Select previous node
				if(prevNode != null) {
				    _tree.selectNode(prevNode);
				}
    		}
        }
    }

	/**
     * We heard that a DataComponent has been moved...
     * So remove the Tree node if we have it and add it again
     * @param component The LD_Component moved
     */
    public void componentMoved(DataComponent component) {
        if(component != null) {
            
    		DefaultMutableTreeNode nodeToDelete = _tree.getNode(component);
    		if(nodeToDelete != null) {
    		    
    		    // Whether to select node again
    		    // This may be another tree
    		    boolean doSelectNode = nodeToDelete == getTree().getSelectedNode();
    		    
    		    // Delete node
				removeNodeFromParent(nodeToDelete);
				
    		    // Get (possibly new) parent component node
    		    DataComponent parentComponent = component.getParent();
    		    DefaultMutableTreeNode parentNode = _tree.getNode(parentComponent);
    		    if(parentNode != null) {
    				// Add again
    				DefaultMutableTreeNode childNode = addNode(parentNode, component);
    				
    				// Do select again
    				if(doSelectNode) {
        				_tree.selectNode(childNode);
    				}
    		    }
    		}
        }
    }
    
	/**
	 * A Component was changed in some way (maybe the text or Attribute)
	 * @param component The DataComponent
	 */
	public void componentChanged(DataComponent component) {
		final DefaultMutableTreeNode node = _tree.getNode(component);
		if(node != null) {
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					nodeChanged(node);
				}
			});
		}
	}
}
