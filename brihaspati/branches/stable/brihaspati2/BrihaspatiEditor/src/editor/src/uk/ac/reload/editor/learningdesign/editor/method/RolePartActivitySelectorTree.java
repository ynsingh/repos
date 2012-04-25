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

package uk.ac.reload.editor.learningdesign.editor.method;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTree;

import uk.ac.reload.dweezil.gui.tree.DweezilTree;
import uk.ac.reload.editor.datamodel.DataComponent;
import uk.ac.reload.editor.datamodel.DataModel;
import uk.ac.reload.editor.datamodel.IDataComponentIcon;
import uk.ac.reload.editor.datamodel.IDataModelListener;
import uk.ac.reload.editor.gui.Editor_TreeModel;
import uk.ac.reload.editor.gui.Editor_TreeNode;
import uk.ac.reload.editor.learningdesign.datamodel.LD_Component;
import uk.ac.reload.editor.learningdesign.datamodel.LD_DataModel;
import uk.ac.reload.editor.learningdesign.datamodel.LD_Grouping;
import uk.ac.reload.editor.learningdesign.datamodel.components.activities.Activity;
import uk.ac.reload.editor.learningdesign.datamodel.components.environments.Environment;
import uk.ac.reload.editor.learningdesign.datamodel.components.environments.Environments_Grouping;
import uk.ac.reload.editor.learningdesign.datamodel.method.RolePart;
import uk.ac.reload.editor.learningdesign.editor.shared.LD_SelectorTree;


/**
 * A Tree for selecting Activities and Environments for RoleParts
 * 
 * @author Phillip Beauvoir
 * @version $Id: RolePartActivitySelectorTree.java,v 1.1 1998/03/26 15:40:32 ynsingh Exp $
 */
public class RolePartActivitySelectorTree
extends LD_SelectorTree
implements IDataModelListener 
{
    
    /**
     * The associated RolePart Component 
     */
    private RolePart _rolePart;
    
    /**
     * The display label
     */
    private JLabel _label;
    
    /**
     * Default Constructor
     */
    public RolePartActivitySelectorTree(JLabel label) {
        setRootVisible(false);
        
        setEditor_TreeModel(new ActivityEnvironmentTreeModel(this));
        
		// Tree Renderer
		setCellRenderer(new ActivitySelectorTreeTreeRenderer());
		
		_label = label;
    }
    
    /**
     * Set the RolePart Component 
     * @param rolePart RolePart Component 
     */
    public void setRolePartComponent(RolePart rolePart) {
        _rolePart = rolePart;

        // Lazily set the DataModel
        if(getDataModel() == null) {
            setDataModel(rolePart.getDataModel());
            expandTree(true);
        }

        update();
    }
    
    /**
     * Update the nodes and label
     */
    private void update() {
        LD_Component component = _rolePart.getReferencedComponent();
        setSelectedNodes(new LD_Component[] { component });
        updateLabel(component);
    }
    
    /**
     * Over-ride this to add ourself as a listener.
     * We'll need to listen to Component changes so we can set selections.
     */
    public void setDataModel(DataModel ldDataModel) {
        super.setDataModel(ldDataModel);
        
		// Listen to DataModel changes
		ldDataModel.addIDataModelListener(this);		
    }
    
    /**
     * A node was ticked on the tree
     * @param node
     */
    public void nodeSelected(Editor_TreeNode node) {
        Object o = node.getUserObject();
        if(!(o instanceof LD_Grouping)) {
            LD_Component component = (LD_Component)o;
            _rolePart.setReferencedComponent(component);
        }
    }

	/**
	 * Update the Label
	 * @param role The Role
	 */
	protected void updateLabel(LD_Component component) {
	    if(component != null) {
	        _label.setText("<html>" + component.getTitle());
	        _label.setIcon(((IDataComponentIcon)component).getIcon());
	    }
	    else {
	        _label.setText("(not set)");
	        _label.setIcon(null);
	    }
 	}

    /**
     * Clean up
     */
    public void cleanup() {
        if(getDataModel() != null) {
            getDataModel().removeIDataModelListener(this);
        }

        super.cleanup();
        
        _rolePart = null;
    }

    //========================= Component Listener Events ==================================

    public void componentAdded(DataComponent component) {
    }
    
    public void componentRemoved(DataComponent component) {
    }

    public void componentMoved(DataComponent component) {
    }

    public void componentChanged(DataComponent component) {
        // ...we won't respond to the Component changing directly because our RolePart Component will
        // take care of that and will notify via componentChanged().
        // So we'll listen in on the RolePart Component being changed and set the nodes here...
        if(component == _rolePart) {
            update();
        }
	}
	
	//	========================= Tree Model ==================================
	
	class ActivityEnvironmentTreeModel
	extends Editor_TreeModel
	{

	    /**
	     * Default Constructor
	     * @param tree The Owning Tree
	     */
	    public ActivityEnvironmentTreeModel(DweezilTree tree) {
	        super(tree);

	        // Don't want to select nodes when we hear they are added/moved
            setSelectNodeOnAdd(false);
	    }
	    
	    /**
	     * Set the LD_DataModel
	     * @param dataModel The LD_DataModel
	     */
	    public void setDataModel(DataModel dataModel) {
	        super.setDataModel(dataModel);
	        
	        LD_Grouping rootGroup = new LD_Grouping();
	        
	        LD_DataModel ldDataModel = (LD_DataModel)dataModel;
	        
	        LD_Grouping act1Group = ldDataModel.getActivities_Grouping().getLearningActivity_Grouping();
	        rootGroup.addChild(act1Group);
	        
	        LD_Grouping act2Group = ldDataModel.getActivities_Grouping().getSupportActivity_Grouping();
	        rootGroup.addChild(act2Group);

	        LD_Grouping act3Group = ldDataModel.getActivities_Grouping().getActivityStructure_Grouping();
	        rootGroup.addChild(act3Group);

	        Environments_Grouping envGroup = ldDataModel.getEnvironment_Grouping();
	        rootGroup.addChild(envGroup);

	        // Create the child nodes
	        root = new Editor_TreeNode(rootGroup);
	        buildChildren((Editor_TreeNode)root);
			
	        // Reload the Model
			reload();
			
			// Listen to DataModel changes
			dataModel.addIDataModelListener(this);		
	    }

        /**
         * Over-ride to only add top Environments
         * @param node Starting node
         */
        protected void buildChildren(Editor_TreeNode node) {
    		Object obj = node.getUserObject();
    		
    		if(obj instanceof LD_Component) {
    		    LD_Component ldComponent = (LD_Component)obj;
    		    if(ldComponent.hasChildren()) {
    		        DataComponent[] children = ldComponent.getChildren();
    		        for(int i = 0; i < children.length; i++) {
    		            Editor_TreeNode childNode = new Editor_TreeNode(children[i]);
    		            node.add(childNode);
    		            if(!(children[i] instanceof Environment)) {
    		                buildChildren(childNode);
    		            }
    		        }
    		    }
    		}
        }

        //========================= Component Listener Events ==================================

	    /**
	     * Over-ride this to only add Role Components
	     * @param component The LD_Component added
	     */
	    public void componentAdded(DataComponent component) {
	        if(isComponentOK(component)) {
	            super.componentAdded(component);
	        }
	    }
	    
		/**
	     * Over-ride this to only remove Role Components
	     * @param component The LD_Component
	     */
	    public void componentRemoved(DataComponent component) {
	        if(isComponentOK(component)) {
	            super.componentRemoved(component);
	        }
	    }

		/**
	     * Over-ride this to only move Role Components
	     * @param component The LD_Component
	     */
	    public void componentMoved(DataComponent component) {
	        if(isComponentOK(component)) {
	            super.componentMoved(component);
	        }
	    }

	    /**
	     * Over-ride this to only change Role Components
	     * @param component The LD_Component
		 */
		public void componentChanged(DataComponent component) {
	        if(isComponentOK(component)) {
	            super.componentChanged(component);
	        }
		}

		/**
		 * @return True of we deal with this type of component
		 */
		private boolean isComponentOK(DataComponent component) {
		    return component instanceof Activity
		    		|| component instanceof Environment; 
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

            // Don't show Group nodes
            if(value instanceof Editor_TreeNode) {
                Editor_TreeNode selNode = (Editor_TreeNode)value;
                Object obj = selNode.getUserObject();
                cb.setVisible((obj instanceof Activity) || (obj instanceof Environment));
            }

            return super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
        }
        
    }
}
