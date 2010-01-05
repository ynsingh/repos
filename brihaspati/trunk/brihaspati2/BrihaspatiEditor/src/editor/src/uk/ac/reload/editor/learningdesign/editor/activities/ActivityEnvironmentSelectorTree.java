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

package uk.ac.reload.editor.learningdesign.editor.activities;

import uk.ac.reload.dweezil.gui.tree.DweezilTree;
import uk.ac.reload.editor.datamodel.DataComponent;
import uk.ac.reload.editor.datamodel.DataModel;
import uk.ac.reload.editor.datamodel.IDataContainer;
import uk.ac.reload.editor.gui.Editor_TreeModel;
import uk.ac.reload.editor.gui.Editor_TreeNode;
import uk.ac.reload.editor.learningdesign.datamodel.LD_DataModel;
import uk.ac.reload.editor.learningdesign.datamodel.LD_Grouping;
import uk.ac.reload.editor.learningdesign.datamodel.components.activities.Activity;
import uk.ac.reload.editor.learningdesign.datamodel.components.environments.Environments_Grouping;
import uk.ac.reload.editor.learningdesign.editor.shared.LD_SelectorTree;


/**
 * A Tree for selecting Environments in an Activity
 * 
 * @author Phillip Beauvoir
 * @version $Id: ActivityEnvironmentSelectorTree.java,v 1.1 1998/03/26 15:40:32 ynsingh Exp $
 */
public class ActivityEnvironmentSelectorTree
extends LD_SelectorTree
{
    
    /**
     * The associated Activity 
     */
    private Activity _activity;
    
    /**
     * Default Constructor
     */
    public ActivityEnvironmentSelectorTree() {
        setRootVisible(false);
        
        setEditor_TreeModel(new EnvironmentSelectorTreeModel(this));
        
		// Tree Renderer
		setCellRenderer(new LD_SelectorTreeTreeRenderer());
		
		// Stop double-clicks
		setToggleClickCount(0);
    }
    
    /**
     * Set the Activity 
     * @param activity Activity 
     */
    public void setActivity(Activity activity) {
        _activity = activity;
        
        // Lazily set the DataModel
        if(getDataModel() == null) {
            setDataModel(activity.getDataModel());
        }

        // Set Model
        ((EnvironmentSelectorTreeModel)getEditor_TreeModel()).setActivity(activity);
    }

    /**
     * A node was ticked on the tree
     * @param node
     */
    public void nodeSelected(Editor_TreeNode node) {
        Object o = node.getUserObject();
        if(!(o instanceof LD_Grouping)) {
            node.setSelected(!node.isSelected());
        }
    }
    
    public void cleanup() {
        super.cleanup();
        
        _activity = null;
    }
	
	//  ========================= TREE MODEL ==================================
    
    /**
     * Tree Model
     */
    class EnvironmentSelectorTreeModel
    extends Editor_TreeModel {
	    /**
	     * Components that have already been referenced by the ActivityStructure
	     */
	    private DataComponent[] _referencedComponents;

	    /**
	     * The Root Group
	     */
	    private Environments_Grouping _rootGroup;

	    /**
	     * Default Constructor
	     * @param tree The Owning Tree
	     */
        public EnvironmentSelectorTreeModel(DweezilTree tree) {
            super(tree);
            
            // Don't want to select nodes when we hear theya are added/moved
            setSelectNodeOnAdd(false);
        }
        
        /**
         * Set the LD_DataModel
         * @param ldDataModel The DataModel
         */
        public void setDataModel(DataModel ldDataModel) {
            super.setDataModel(ldDataModel);
            
            // Get Root Data Point
            _rootGroup = ((LD_DataModel)ldDataModel).getEnvironment_Grouping();
        }

	    /**
	     * ReSet the Activity Component 
	     */
	    public void setActivity(Activity activity) {
	        // Eliminate previously selected components
	        _referencedComponents = activity.getEnvironments();
	        
	        // Create the child nodes
	        root = new Editor_TreeNode(_rootGroup);
	        buildChildren((Editor_TreeNode)root);
			
	        // Reload the Model
			reload();
	    }

	    /**
         * Over-ride to only add top Environments
         * @param node Starting node
         */
        protected void buildChildren(Editor_TreeNode node) {
            Object obj = node.getUserObject();
            
            if(obj instanceof IDataContainer) {
                IDataContainer component = (IDataContainer)obj;
                if(component.hasChildren()) {
                    DataComponent[] children = component.getChildren();
                    for(int i = 0; i < children.length; i++) {
                        if(!isAlreadyReferencedComponent(children[i])) {
                            Editor_TreeNode childNode = new Editor_TreeNode(children[i]);
                            node.add(childNode);
                        }
                    }
                }
            }
        }
        
	    /**
	     * @param component
	     * @return True if component is already referenced
	     */
	    private boolean isAlreadyReferencedComponent(DataComponent component) {
	        for(int i = 0; i < _referencedComponents.length; i++) {
	            if(component == _referencedComponents[i]) {
	                return true;
	            }
	        }
	        
	        return false;
	    }

	    public void cleanup() {
	        super.cleanup();
	        _referencedComponents = null;
	    }
    }
}
