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

import java.awt.Component;

import javax.swing.JTree;

import uk.ac.reload.dweezil.gui.tree.DweezilTree;
import uk.ac.reload.editor.datamodel.DataComponent;
import uk.ac.reload.editor.datamodel.DataModel;
import uk.ac.reload.editor.datamodel.IDataContainer;
import uk.ac.reload.editor.gui.Editor_TreeModel;
import uk.ac.reload.editor.gui.Editor_TreeNode;
import uk.ac.reload.editor.learningdesign.datamodel.LD_DataModel;
import uk.ac.reload.editor.learningdesign.datamodel.LD_Grouping;
import uk.ac.reload.editor.learningdesign.datamodel.components.activities.Activity;
import uk.ac.reload.editor.learningdesign.datamodel.components.activities.ActivityStructure;
import uk.ac.reload.editor.learningdesign.editor.shared.LD_SelectorTree;


/**
 * A Tree for selecting Activities for Activity Structures
 * 
 * @author Phillip Beauvoir
 * @version $Id: ActivityStructureSelectorTree.java,v 1.1 1998/03/26 15:40:32 ynsingh Exp $
 */
public class ActivityStructureSelectorTree
extends LD_SelectorTree
{
    
    /**
     * The associated ActivityStructure Component 
     */
    private ActivityStructure _as;
    
    /**
     * Default Constructor
     */
    public ActivityStructureSelectorTree() {
        setRootVisible(false);
        
        setEditor_TreeModel(new ActivityStructureSelectorTreeModel(this));
        
		// Tree Renderer
		setCellRenderer(new ActivitySelectorTreeTreeRenderer());
    }
    
    /**
     * Set the ActivityStructure Component 
     * @param as ActivityStructure Component 
     */
    public void setActivityStructure(ActivityStructure as) {
        _as = as;
        
        // Lazily set the DataModel
        if(getDataModel() == null) {
            setDataModel(as.getDataModel());
        }
        
        // Set Model
        ((ActivityStructureSelectorTreeModel)getEditor_TreeModel()).setActivityStructure(as);
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
        
        _as = null;
    }

	//	========================= Tree Model ==================================
	
	class ActivityStructureSelectorTreeModel
	extends Editor_TreeModel
	{
	    /**
	     * Components that have already been referenced by the ActivityStructure
	     */
	    private DataComponent[] _referencedComponents;
	    
	    /**
	     * The Root Group
	     */
	    private LD_TreeGrouping _rootGroup;
	    
	    /**
	     * Default Constructor
	     * @param tree The Owning Tree
	     */
	    public ActivityStructureSelectorTreeModel(DweezilTree tree) {
	        super(tree);

	        // Don't want to select nodes when we hear theya are added/moved
            setSelectNodeOnAdd(false);
	    }
	    
	    /**
	     * Over-ride to setup
	     */
	    public void setDataModel(DataModel dataModel) {
	        super.setDataModel(dataModel);
	        
	        _rootGroup = new LD_TreeGrouping();
	        
	        LD_DataModel ldDataModel = (LD_DataModel)dataModel;
	        
	        LD_Grouping act1Group = ldDataModel.getActivities_Grouping().getLearningActivity_Grouping();
	        _rootGroup.addChild(act1Group);
	        
	        LD_Grouping act2Group = ldDataModel.getActivities_Grouping().getSupportActivity_Grouping();
	        _rootGroup.addChild(act2Group);

	        LD_Grouping act3Group = ldDataModel.getActivities_Grouping().getActivityStructure_Grouping();
	        _rootGroup.addChild(act3Group);
	    }
	    
	    /**
	     * ReSet the ActivityStructure Component 
	     */
	    public void setActivityStructure(ActivityStructure as) {
	        // Eliminate previously selected components
	        _referencedComponents = as.getReferencedComponents();
	        
	        // Create the child nodes
	        root = new Editor_TreeNode(_rootGroup);
	        buildChildren((Editor_TreeNode)root);
			
	        // Reload the Model
			reload();
	    }

	    /**
	     * Over-ride to only show non-selected components and not this ActivityStructure
	     * @param node Starting node
	     */
	    protected void buildChildren(Editor_TreeNode node) {
	        Object obj = node.getUserObject();
	        
	        if(obj instanceof IDataContainer) {
	            IDataContainer component = (IDataContainer)obj;
	            if(component.hasChildren()) {
	                DataComponent[] children = component.getChildren();
	                for(int i = 0; i < children.length; i++) {
	                    if(children[i] != _as && !isAlreadyReferencedComponent(children[i])) {
	                        Editor_TreeNode childNode = new Editor_TreeNode(children[i]);
	                        node.add(childNode);
	                        buildChildren(childNode);
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
	        
	        if(_rootGroup != null) {
	            _rootGroup.removeChildren();
	        }
	    }
	}
	
	
    // =========================== Renderer ==============================================
    
    /**
     * Cell renderer for Tree nodes with checkboxes
     */
    class ActivitySelectorTreeTreeRenderer
    extends LD_SelectorTreeTreeRenderer {

        public ActivitySelectorTreeTreeRenderer() {
        }

        public Component getTreeCellRendererComponent(JTree tree, Object value,
                boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {

            // Don't show combo box in Group nodes
            if(value instanceof Editor_TreeNode) {
                Editor_TreeNode selNode = (Editor_TreeNode)value;
                Object obj = selNode.getUserObject();
                cb.setVisible(obj instanceof Activity);
            }

            return super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
        }
    }
}
