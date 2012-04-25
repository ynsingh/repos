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
import java.util.Enumeration;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import uk.ac.reload.editor.datamodel.DataComponent;
import uk.ac.reload.editor.datamodel.DataModel;
import uk.ac.reload.editor.gui.Editor_Tree;
import uk.ac.reload.editor.gui.Editor_TreeNode;
import uk.ac.reload.editor.gui.Editor_TreeRenderer;
import uk.ac.reload.editor.learningdesign.datamodel.method.Method;


/**
 * The Tree to display the Method and Plays and Acts
 * 
 * @author Phillip Beauvoir
 * @version $Id: MethodTree.java,v 1.1 1998/03/26 15:40:32 ynsingh Exp $
 */
public class MethodTree
extends Editor_Tree  {
    
    /**
     * Default Constructor
     */
    public MethodTree() {
        setEditor_TreeModel(new MethodTreeModel(this));
        
		// Tree Renderer
		setCellRenderer(new Editor_TreeRenderer());
		//setCellRenderer(new MethodTreeRenderer());
    }

    /**
     * Set the LD_DataModel
     * @param ldDataModel The LD_DataModel
     */
    public void setDataModel(DataModel ldDataModel) {
        super.setDataModel(ldDataModel);

        // Expand node
		//expandNode(getLD_TreeModel().getRootNode(), true);
		
        // Expand Play/Act nodes
		Enumeration e = getEditor_TreeModel().getRootNode().children();
		while(e.hasMoreElements()) {
		    expandNode((DefaultMutableTreeNode)e.nextElement(), true);
		}
        
		// Select first node
		selectRootNode();
    }
    
    /**
     * Tree cell Renderer should we feel the need to prefix the nodes with "Act: ", "Play: ", "Role Part: "
     */
    class MethodTreeRenderer extends Editor_TreeRenderer {
        
        public Component getTreeCellRendererComponent(JTree tree, Object value,
                boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {

            Component c = super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
            if(value instanceof Editor_TreeNode) {
                Editor_TreeNode selNode = (Editor_TreeNode) value;
                DataComponent dc = (DataComponent)selNode.getUserObject();
                if(!(dc instanceof Method)) {
                    setText(dc.getDefaultTitle() + ": " + getText());
                }
            }
            return c;
        }

    }
}
