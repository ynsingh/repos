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

import java.awt.Component;

import javax.swing.Icon;
import javax.swing.JTree;

import uk.ac.reload.dweezil.gui.tree.DweezilTree;
import uk.ac.reload.dweezil.util.DweezilUIManager;
import uk.ac.reload.editor.IIcons;
import uk.ac.reload.editor.datamodel.DataComponent;
import uk.ac.reload.editor.datamodel.DataModel;
import uk.ac.reload.editor.datamodel.IDataComponentIcon;
import uk.ac.reload.editor.datamodel.IDataContainer;
import uk.ac.reload.editor.gui.Editor_TreeModel;
import uk.ac.reload.editor.gui.Editor_TreeNode;
import uk.ac.reload.editor.learningdesign.datamodel.LD_DataModel;
import uk.ac.reload.editor.learningdesign.datamodel.LD_Grouping;
import uk.ac.reload.editor.learningdesign.datamodel.components.environments.Environment;
import uk.ac.reload.editor.learningdesign.datamodel.components.environments.IndexSearch;
import uk.ac.reload.editor.learningdesign.datamodel.components.environments.LearningObject;
import uk.ac.reload.editor.learningdesign.datamodel.components.environments.Service;
import uk.ac.reload.editor.learningdesign.editor.shared.LD_SelectorTree;


/**
 * A Tree for selecting Components that can be indexed
 * 
 * @author Phillip Beauvoir
 * @version $Id: IndexElementSelectorTree.java,v 1.1 1998/03/26 15:40:32 ynsingh Exp $
 */
public class IndexElementSelectorTree
extends LD_SelectorTree
implements IIcons
{
    
    /**
     * The associated IndexSearch Component 
     */
    private IndexSearch _is;
    
    /**
     * Default Constructor
     */
    public IndexElementSelectorTree() {
        setRootVisible(false);
        
        setEditor_TreeModel(new IndexSearchSelectorTreeModel(this));
        
		// Tree Renderer
		setCellRenderer(new IndexSearchSelectorTreeTreeRenderer());
    }
    
    /**
     * Set the IndexSearch Component 
     * @param iss IndexSearch Component 
     */
    public void setIndexSearch(IndexSearch is) {
        _is = is;
        
        // Lazily set the DataModel
        if(getDataModel() == null) {
            setDataModel(is.getDataModel());
        }
        
        // Set Model
        ((IndexSearchSelectorTreeModel)getEditor_TreeModel()).setIndexSearch(is);
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
        _is = null;
    }

	//	========================= Tree Model ==================================
	
	class IndexSearchSelectorTreeModel
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
	     * LearningObject Grouping
	     */
	    private Tree_LearningObjectGrouping _loGroup;
	    
	    /**
	     * Services Grouping
	     */
	    private Tree_ServiceGrouping _servicesGroup;

	    
	    /**
	     * Default Constructor
	     * @param tree The Owning Tree
	     */
	    public IndexSearchSelectorTreeModel(DweezilTree tree) {
	        super(tree);

	        // Don't want to select nodes when we hear they are added/moved
            setSelectNodeOnAdd(false);
	    }
	    
	    /**
	     * Over-ride to setup
	     */
	    public void setDataModel(DataModel dataModel) {
	        super.setDataModel(dataModel);

	        _rootGroup = new LD_TreeGrouping();
	        
	        LD_DataModel ldDataModel = (LD_DataModel)getDataModel();
	        
	        // Environments
	        LD_Grouping envGroup = ldDataModel.getEnvironment_Grouping();
	        _rootGroup.addChild(envGroup);
	        
	        // Learning Objects
	        _loGroup = new Tree_LearningObjectGrouping(ldDataModel);
	        _rootGroup.addChild(_loGroup);
	        
	        // Services
	        _servicesGroup = new Tree_ServiceGrouping(ldDataModel);
	        _rootGroup.addChild(_servicesGroup);
	        
	        // Learning Activities
	        LD_Grouping act1Group = ldDataModel.getActivities_Grouping().getLearningActivity_Grouping();
	        _rootGroup.addChild(act1Group);
	        
	        // Support Activities
	        LD_Grouping act2Group = ldDataModel.getActivities_Grouping().getSupportActivity_Grouping();
	        _rootGroup.addChild(act2Group);

	        // Activity Structures
	        LD_Grouping act3Group = ldDataModel.getActivities_Grouping().getActivityStructure_Grouping();
	        _rootGroup.addChild(act3Group);
	    }
	    
	    /**
	     * Reset the IndexSearch Component 
	     */
	    public void setIndexSearch(IndexSearch is) {
	        // Eliminate previously selected components
	        _referencedComponents = is.getIndexElements();
	        
	        // Reset sub-groups
	        _loGroup.reset();
	        _servicesGroup.reset();
	        
	        // Create the child nodes
	        root = new Editor_TreeNode(_rootGroup);
	        buildChildren((Editor_TreeNode)root);
			
	        // Reload the Model
			reload();
	    }

	    /**
	     * Over-ride to only show non-selected components and not this IndexSearch
	     * @param node Starting node
	     */
	    protected void buildChildren(Editor_TreeNode node) {
	        Object obj = node.getUserObject();
	        
	        if(obj instanceof IDataContainer) {
	            IDataContainer component = (IDataContainer)obj;
	            if(component.hasChildren()) {
	                DataComponent[] children = component.getChildren();
	                for(int i = 0; i < children.length; i++) {
	                    if(isAllowedTreeNode(children[i])) {
	                        Editor_TreeNode childNode = new Editor_TreeNode(children[i]);
	                        node.add(childNode);
	                        buildChildren(childNode);
	                    }
	                }
	            }
	        }
	    }
	    
	    /**
	     * @return True if we should show a tree node for a DataComponent
	     */
	    private boolean isAllowedTreeNode(DataComponent component) {
	        if(component == _is) {
	            return false;
	        }
	        
	        // No Environment sub-groups
	        if(component.getParent() instanceof Environment) {
	            return false;
	        }

	        if(isAlreadyReferencedComponent(component)) {
	            return false;
	        }
	        
	        return true;
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

	        if(_loGroup != null) {
	            _loGroup.removeChildren();
	        }
	        
	        if(_servicesGroup != null) {
	            _servicesGroup.removeChildren();
	        }
	    }
	}
	
	
    // =========================== Renderer ==============================================
    
    /**
     * Cell renderer for Tree nodes with checkboxes
     */
    class IndexSearchSelectorTreeTreeRenderer
    extends LD_SelectorTreeTreeRenderer {

        public IndexSearchSelectorTreeTreeRenderer() {
        }

        public Component getTreeCellRendererComponent(JTree tree, Object value,
                boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {

            // Don't show combo box in Group nodes
            if(value instanceof Editor_TreeNode) {
                Editor_TreeNode selNode = (Editor_TreeNode)value;
                Object obj = selNode.getUserObject();
                cb.setVisible(!(obj instanceof LD_Grouping));
            }

            return super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
        }
    }
    
    
    // ====================== Tree Groupings =================================================
    
    class Tree_LearningObjectGrouping
    extends LD_TreeGrouping
    implements IDataComponentIcon
    {
        
        public Tree_LearningObjectGrouping(DataModel dataModel) {
            setDataModel(dataModel);
        }
        
        /**
         * Re-populate
         */
        public void reset() {
            removeChildren();
            
            LearningObject[] los = getLD_DataModel().getEnvironment_Grouping().getLearningObjects();
            for(int i = 0; i < los.length; i++) {
                addChild(los[i]);
            }
        }
        
        public String getTitle() {
            return "Learning Objects";
        }
        
        public Icon getIcon() {
            return DweezilUIManager.getIcon(ICON_LEARNINGOBJECTS);
        }

    }
    
    
    class Tree_ServiceGrouping
    extends LD_TreeGrouping
    implements IDataComponentIcon
    {
        
        public Tree_ServiceGrouping(DataModel dataModel) {
            setDataModel(dataModel);
        }
        
        /**
         * Re-populate
         */
        public void reset() {
            removeChildren();
            
            Service[] services = getLD_DataModel().getEnvironment_Grouping().getServices();
            for(int i = 0; i < services.length; i++) {
                addChild(services[i]);
            }
        }
        
        public String getTitle() {
            return "Services";
        }
        
        public Icon getIcon() {
            return DweezilUIManager.getIcon(ICON_SERVICE);
        }

    }

}
