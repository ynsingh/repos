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

import java.util.Enumeration;

import uk.ac.reload.dweezil.gui.tree.DweezilTreeModel;
import uk.ac.reload.moonunit.schema.SchemaElement;
import uk.ac.reload.moonunit.schema.SchemaModel;


/**
 * A Viewer TreeModel that will display the hierarchical contents of an XML Schema.
 * The SchemaTreeModel class takes care of taking the Schema and creating tree nodes.
 * A NodeViewer TextPane will display the information for a node when it is selected.
 * This is for development purposes only.
 *
 * @author Phillip Beauvoir
 * @version $Id: SchemaTreeModel.java,v 1.1 1998/03/26 15:32:14 ynsingh Exp $
 */
public class SchemaTreeModel
extends DweezilTreeModel
{

	/**
     * Constructor
     * @param schemaModel The Schema Model we are representing
     */
    public SchemaTreeModel(SchemaModel schemaModel) {
        root = new SchemaTreeNode(schemaModel.getRootElement());
        buildFirstNodes((SchemaTreeNode)root);
    }

	/**
	 * Build up initial level child nodes
	 * Only dig down one level, the Tree Expansion Listener in the Tree will do the rest
	 * @param node The node to add to the Tree
	 */
    private void buildFirstNodes(SchemaTreeNode parentNode) {
		if(parentNode == null) {
		    return;
		}
	    
		// Already done
		if(parentNode.childNodesAdded) {
		    return;
		}

		SchemaElement[] children = parentNode.getSchemaElement().getChildren();
        
        for(int i = 0; i < children.length; i++) {
            SchemaElement child = children[i];
            SchemaTreeNode newNode = new SchemaTreeNode(child);
            parentNode.add(newNode);
            
            SchemaElement[] children2 = child.getChildren();
            for(int j = 0; j < children2.length; j++) {
                SchemaElement child2 = children2[j];
                SchemaTreeNode newNode2 = new SchemaTreeNode(child2);
                newNode.add(newNode2);
            }
        }
        
        parentNode.childNodesAdded = true;
    }


    /**
	 * Add the grandchild nodes of the parent node
	 * @param parentNode The node to add the grandchildren
	 */
	public void buildChildNodes(SchemaTreeNode parentNode) {
		if(parentNode == null) {
		    return;
		}
	    
		// Already done
		if(parentNode.childNodesAdded) {
		    return;
		}

		Enumeration enum1 = parentNode.children();
		while(enum1.hasMoreElements()) {
		    SchemaTreeNode sub_node = (SchemaTreeNode)enum1.nextElement();
		    SchemaElement schemaElement = sub_node.getSchemaElement();
		    SchemaElement[] children = schemaElement.getChildren();
		    for(int i = 0; i < children.length; i++) {
		        SchemaTreeNode newNode = new SchemaTreeNode(children[i]);
		        insertNodeInto(newNode, sub_node, sub_node.getChildCount());
		    }
		}
		
		parentNode.childNodesAdded = true;
	}
}
