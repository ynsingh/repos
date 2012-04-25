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

package uk.ac.reload.editor.learningdesign.editor.roles;

import java.awt.Component;

import javax.swing.JTree;

import uk.ac.reload.editor.gui.Editor_TreeNode;
import uk.ac.reload.editor.learningdesign.datamodel.components.roles.Role;
import uk.ac.reload.editor.learningdesign.editor.shared.LD_SelectorTree;


/**
 * A Tree for selecting Roles
 * 
 * @author Phillip Beauvoir
 * @version $Id: RoleSelectorTree.java,v 1.1 1998/03/26 15:40:32 ynsingh Exp $
 */
public abstract class RoleSelectorTree
extends LD_SelectorTree
{
    
    /**
     * Default Constructor
     */
    protected RoleSelectorTree() {
        setRootVisible(false);
        
        RoleTreeModel model = new RoleTreeModel(this);
        setEditor_TreeModel(model);
        
        // Don't want to select nodes when we hear they are added/moved
        model.setSelectNodeOnAdd(false);
        
		// Tree Renderer
		setCellRenderer(new RoleSelectorTreeRenderer());
		
		// Stop double-clicks
		setToggleClickCount(0);
    }
    
    /**
     * A node was selected on the tree
     * @param node
     */
    public void nodeSelected(Editor_TreeNode node) {
        node.setSelected(!node.isSelected());
    }

    // ======================================================================================
    
    /**
     * Cell renderer for Tree nodes with checkboxes
     */
    class RoleSelectorTreeRenderer
    extends LD_SelectorTreeTreeRenderer {

        public RoleSelectorTreeRenderer() {
        }

        public Component getTreeCellRendererComponent(JTree tree, Object value,
                boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {

            // Don't show Group nodes
            if(value instanceof Editor_TreeNode) {
                Editor_TreeNode selNode = (Editor_TreeNode)value;
                Object obj = selNode.getUserObject();
                cb.setVisible(obj instanceof Role);
            }

            return super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
        }
        
    }

}
