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

import uk.ac.reload.dweezil.gui.tree.DweezilTree;
import uk.ac.reload.editor.datamodel.DataComponent;
import uk.ac.reload.editor.gui.Editor_TreeModel;
import uk.ac.reload.editor.gui.Editor_TreeNode;
import uk.ac.reload.editor.learningdesign.datamodel.LD_Grouping;
import uk.ac.reload.editor.learningdesign.datamodel.method.Method;
import uk.ac.reload.editor.learningdesign.editor.shared.LD_SelectorTree;


/**
 * A Tree for selecting Plays in a Method
 * 
 * @author Phillip Beauvoir
 * @version $Id: MethodPlaySelectorTree.java,v 1.1 1998/03/26 15:40:32 ynsingh Exp $
 */
public class MethodPlaySelectorTree
extends LD_SelectorTree
{
    
    /**
     * The associated Method 
     */
    private Method _method;
    
    /**
     * Default Constructor
     */
    public MethodPlaySelectorTree() {
        setRootVisible(false);
        
        setEditor_TreeModel(new PlaySelectorTreeModel(this));
        
		// Tree Renderer
		setCellRenderer(new LD_SelectorTreeTreeRenderer());
		
		// Stop double-clicks
		setToggleClickCount(0);
    }
    
    /**
     * Set the Method 
     * @param method Method 
     */
    public void setMethod(Method method) {
        _method = method;
        
        // Lazily set the DataModel
        if(getDataModel() == null) {
            setDataModel(method.getDataModel());
        }

        // Set Model
        ((PlaySelectorTreeModel)getEditor_TreeModel()).setMethod(method);
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
        _method = null;
    }
	
	//  ========================= TREE MODEL ==================================
    
    /**
     * Tree Model
     */
    class PlaySelectorTreeModel
    extends Editor_TreeModel {
	    /**
	     * Components that have already been referenced by the Method
	     */
	    private DataComponent[] _referencedComponents;

	    /**
	     * The Root Group
	     */
	    private Method _method;

	    /**
	     * Default Constructor
	     * @param tree The Owning Tree
	     */
        public PlaySelectorTreeModel(DweezilTree tree) {
            super(tree);

            // Don't want to select nodes when we hear they are added/moved
            setSelectNodeOnAdd(false);
        }
        
	    /**
	     * Set the Method Component 
	     */
	    public void setMethod(Method method) {
	        _method = method;
	        
	        // Eliminate previously selected components
	        _referencedComponents = method.getPlaysCompleted();
	        
	        // Create the child nodes
	        root = new Editor_TreeNode(_method);
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
            
            if(obj instanceof DataComponent) {
                DataComponent component = (DataComponent)obj;
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
