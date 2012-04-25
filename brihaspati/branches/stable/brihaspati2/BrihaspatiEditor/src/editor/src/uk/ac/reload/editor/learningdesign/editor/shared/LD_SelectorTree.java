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

package uk.ac.reload.editor.learningdesign.editor.shared;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Enumeration;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import uk.ac.reload.dweezil.gui.FlatCheckBox;
import uk.ac.reload.editor.datamodel.DataComponent;
import uk.ac.reload.editor.datamodel.IDataComponentIcon;
import uk.ac.reload.editor.gui.Editor_Tree;
import uk.ac.reload.editor.gui.Editor_TreeNode;
import uk.ac.reload.editor.learningdesign.datamodel.LD_Component;
import uk.ac.reload.editor.learningdesign.datamodel.LD_Grouping;



/**
 * JTree for selecting Components by a tick box
 * 
 * @author Phillip Beauvoir
 * @version $Id: LD_SelectorTree.java,v 1.1 1998/03/26 15:40:32 ynsingh Exp $
 */
public abstract class LD_SelectorTree
extends Editor_Tree
{

    /**
     * Default Constructor
     */
    protected LD_SelectorTree() {
		// Single selection mode
		getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

		// Have to listen to mouse click events rather than tree selection events because
		// the latter only fire once, once a node has been selected
		addMouseListener(new NodeSelectionListener());
    }

    
    /**
     * Set the selected nodes according to Components 
     */
    public void setSelectedNodes(LD_Component[] components) {
        // Clear them all first
        clearSelectedNodes();
        
        // Set them
        if(components == null) {
            return;
        }
        
        for(int i = 0; i < components.length; i++) {
            Editor_TreeNode node = (Editor_TreeNode)getNode(components[i]);
            if(node != null) {
                node.setSelected(true);
                updateNode(node);
            }
        }
    }

    /**
     * @return An array of Components that are selected
     */
    public LD_Component[] getSelectedComponents() {
        Vector v = new Vector();
        
        Enumeration nodes = getNodes();
        if(nodes != null) {
            while(nodes.hasMoreElements()) {
                Editor_TreeNode node = (Editor_TreeNode)nodes.nextElement();
                if(node.isSelected() && (node.getUserObject() instanceof LD_Component)) {
                    v.add(node.getUserObject());
                }
			}            
        }
        
        LD_Component[] components = new LD_Component[v.size()];
        v.copyInto(components);
        return components;
    }
    
    /**
     * De-select all nodes
     */
    public void clearSelectedNodes() {
        Enumeration nodes = getNodes();
        if(nodes != null) {
            while(nodes.hasMoreElements()) {
                Editor_TreeNode node = (Editor_TreeNode)nodes.nextElement();
                node.setSelected(false);
                updateNode(node);
			}            
        }
    }

    /**
     * A node was selected
     * @param node The selected node
     */
    public abstract void nodeSelected(Editor_TreeNode node);
    
    
    //  ========================= MOUSE SELECTION LISTENER ==================================

    /**
     * Mouse Listener to set node selected
     */
    class NodeSelectionListener
    extends MouseAdapter {
        
        public void mousePressed(MouseEvent e) {
            int x = e.getX();
            int y = e.getY();
            int row = getRowForLocation(x, y);
            TreePath path = getPathForRow(row);
            if(path != null) {
                Editor_TreeNode node = (Editor_TreeNode)path.getLastPathComponent();
                nodeSelected(node);
                updateNode(node);
                // Need to revalidate if node is root.
                if(row == 0) {
                    revalidate();
                    repaint();
                }
            }
        }
    }

    //  ========================= TREE RENDERER ==================================
    
    /**
     * Cell renderer for Tree nodes with checkboxes
     */
    public static class LD_SelectorTreeTreeRenderer
    extends JPanel
    implements TreeCellRenderer {
        protected FlatCheckBox cb;
        protected DefaultTreeCellRenderer label;
        protected Font normalFont, boldFont;

        public LD_SelectorTreeTreeRenderer() {
            super(new BorderLayout());
            setBackground(null);
            
            cb = new FlatCheckBox();
            cb.setBackground(UIManager.getColor("Tree.textBackground"));
            add(cb, BorderLayout.WEST);

            label = new DefaultTreeCellRenderer();
            add(label, BorderLayout.CENTER);
            
            normalFont = label.getFont();
            boldFont = label.getFont().deriveFont(Font.BOLD);
        }

        public Component getTreeCellRendererComponent(JTree tree, Object value,
                boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {

            if(value instanceof Editor_TreeNode) {
                Editor_TreeNode selNode = (Editor_TreeNode)value;
                Object obj = selNode.getUserObject();
                
                cb.setSelected(selNode.isSelected());
                
                if(selNode.isSelected()) {
                    label.setFont(boldFont);
                }
                else {
                    label.setFont(normalFont);
                }
                
                // Delegate
                label.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
                
                if(obj instanceof IDataComponentIcon) {
                    label.setIcon(((IDataComponentIcon)obj).getIcon());
                }
            }

            return this;
        }
        
        /* (non-Javadoc)
         * @see java.awt.Component#getPreferredSize()
         * This has to be done for Java 1.5.0
         */
        public Dimension getPreferredSize() {
            int cbWidth = cb.isVisible() ? cb.getPreferredSize().width : 0;
            return new Dimension(cbWidth + label.getPreferredSize().width,
                    super.getPreferredSize().height);
        }
    }
    
    // =============================== Tree Grouping ======================================
    
    /**
     * A Grouping type - LD_Grouping is not suitable as is because we don't want to set
     * the children's parent to this
     * 
     */
    public class LD_TreeGrouping
    extends LD_Grouping {
        
        public void addChild(DataComponent child) {
            if(_children == null) {
                _children = new Vector();
            }
            
            if(child != null && !_children.contains(child)) {
                _children.add(child);
            }
        }
        
        public void addChildAt(DataComponent child, int index) {
            if(_children == null) {
                _children = new Vector();
            }
            
            if(child != null && !_children.contains(child)) {
                _children.add(index, child);
            }
        }
        
        public void removeChild(DataComponent child) {
            if(child != null && _children != null) {
                _children.remove(child);
            }
        }
        
        public void removeChildren() {
            if(_children != null) {
                _children.clear();
            }
        }
    }
    
}
