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

package uk.ac.reload.editor.gui.schemaviewer;

import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;

import uk.ac.reload.dweezil.gui.tree.DweezilTree;
import uk.ac.reload.dweezil.gui.tree.DweezilTreeRenderer;
import uk.ac.reload.moonunit.SchemaController;


/**
 * A Viewer Tree that will display the hierarchical contents of an XML Schema.
 * The TestTreeModel class takes care of taking the Schema and creating tree nodes.
 * A NodeViewer TextPane will display the information for a node when it is selected.
 * This is for testing purposes only.
 *
 * @author Phillip Beauvoir
 * @version $Id: SchemaTree.java,v 1.1 1998/03/26 15:32:14 ynsingh Exp $
 */
public class SchemaTree
extends DweezilTree
implements TreeSelectionListener, TreeExpansionListener
{
    /**
     * The Node viewer pane
     */
    private NodeViewer _nodeViewer;
    
    /**
     * The SchemaTree Model
     */
    private SchemaTreeModel _schemaTreeModel;

    
    /**
     * Constructor
     * 
     * @param schemaController The SchemaController to display
     * @param nodeViewer The NodeViewer TextPane that will display the information
     * for a selected node.
     */
    public SchemaTree(SchemaController schemaController, NodeViewer nodeViewer) {
        _nodeViewer = nodeViewer;

        _schemaTreeModel = new SchemaTreeModel(schemaController.getSchemaModel());
        setModel(_schemaTreeModel);

        setCellRenderer(new DweezilTreeRenderer());

        // Expand the Tree
        TreePath path = new TreePath(_schemaTreeModel.getRootNode().getPath());
        expandPath(path);

        addTreeSelectionListener(this);
        addTreeExpansionListener(this);
    }

    /**
     * A node selection has been made on the tree.  This will call setElement(element)
     * on the NodeViewer and then display the node's information values.
     * @param event The TreeSelectionEvent fired from us being a TreeSelectionListener
     */
    public void valueChanged(TreeSelectionEvent event) {
	    // Only interested in new selections
	    if(!event.isAddedPath()) {
	        return;
	    }
        
        TreePath selPath = event.getPath();
        if(selPath == null) {
            return;
        }

        // Get selected node
        SchemaTreeNode selectedNode = (SchemaTreeNode)selPath.getLastPathComponent();
        if(selectedNode == null) {
            return;
        }

        _nodeViewer.setElement(selectedNode.getSchemaElement());
    }

	/**
	 * Tree Expansion Listener - get the next level down
	 */
	public void treeExpanded(TreeExpansionEvent e) {
	    TreePath path = e.getPath();
	    SchemaTreeNode node = (SchemaTreeNode)path.getLastPathComponent();
	    _schemaTreeModel.buildChildNodes(node);
	}

	/**
	 * Tree Collapsed Listener
	 */
	public void treeCollapsed(TreeExpansionEvent e) {

	}
}