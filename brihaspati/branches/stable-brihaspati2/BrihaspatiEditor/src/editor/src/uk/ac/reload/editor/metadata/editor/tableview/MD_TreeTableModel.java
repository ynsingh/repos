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

package uk.ac.reload.editor.metadata.editor.tableview;

import java.util.Iterator;
import java.util.List;

import org.jdom.Attribute;
import org.jdom.Element;
import org.jdom.Namespace;

import uk.ac.reload.dweezil.gui.tree.DweezilTreeModel;
import uk.ac.reload.dweezil.gui.treetable.TreeTableModel;
import uk.ac.reload.editor.Messages;
import uk.ac.reload.editor.metadata.xml.Metadata;
import uk.ac.reload.moonunit.schema.SchemaElement;


/**
 * The Metadata Tree Table Model for the MD_TreeTable
 *
 * @author Phillip Beauvoir
 * @version $Id: MD_TreeTableModel.java,v 1.1 1998/03/26 15:40:32 ynsingh Exp $
 */
public class MD_TreeTableModel
extends DweezilTreeModel
implements TreeTableModel
{

    /**
     * The Class types for the Columns - this is important because the tree
     * won't display properly without it.
     */
    private static Class[] cTypes = { TreeTableModel.class, String.class, String.class};

    /**
     * The Column Names
     */
    private static String[] cNames = {
            Messages.getString("uk.ac.reload.editor.metadata.tableview.MD_TreeTableModel.0"), //$NON-NLS-1$
            Messages.getString("uk.ac.reload.editor.metadata.tableview.MD_TreeTableModel.1"), //$NON-NLS-1$
            Messages.getString("uk.ac.reload.editor.metadata.tableview.MD_TreeTableModel.2") //$NON-NLS-1$
    };

    /**
     * The Metadata DOM Document
     */
    private Metadata _metadata;

    /**
     * Constructor
     * @param metadata The Metadata DOM Document
     */
    public MD_TreeTableModel(Metadata metadata) {
        _metadata = metadata;
        // Create the root node
        root = new MD_TreeNode(metadata);
        // Build the child nodes
        buildChildren((MD_TreeNode)root);
    }

    /**
     * Build up child nodes
     * @param node The Parent Node
     */
    public void buildChildren(MD_TreeNode node) {
        Iterator children = node.getElement().getChildren().iterator();
        while(children.hasNext()) {
            Element child = (Element)children.next();
            if(doShowNode(child)) {
                MD_TreeNode newNode = new MD_TreeNode(child);
                node.add(newNode);
                buildChildren(newNode);
            }
        }
    }

    /**
     * Decide whether to display an Element on the Tree
     * @param element the JDOM element concerned
     * @return true if the element has to be displayed, false otherwise
     */
    protected boolean doShowNode(Element element) {
        return doShowNode(element.getName(), element.getNamespace());
    }

    /**
     * Decide whether to display an Element on the Tree
     * @param schemaElement the schema element of the JDOM element concerned
     * @return true if the element has to be displayed, false otherwise
     */
    protected boolean doShowNode(SchemaElement schemaElement) {
        return doShowNode(schemaElement.getName(), schemaElement.getNamespace());
    }

    /**
     * Decide whether to display an Element on the Tree
     * @param elementName the name of the JDOM element concerned
     * 		  ns the schema namespace of the element
     * @return true if the element has to be displayed, false otherwise
     */
    protected boolean doShowNode(String elementName, Namespace ns) {
        return true;
    }

    /**
     * Decide whether to allow to add an Element on the Tree
     * @param element the JDOM element concerned
     * @return true to allow the element to be added, false otherwise
     */
    protected boolean canAddNode(Element element) {
        return canAddNode(element.getName(), element.getNamespace());
    }

    /**
     * Decide whether to allow to add an Element on the Tree
     * @param schemaElement the schema element of the JDOM element concerned
     * @return true to allow the element to be added, false otherwise
     */
    protected boolean canAddNode(SchemaElement schemaElement) {
        return canAddNode(schemaElement.getName(), schemaElement.getNamespace());
    }

    /**
     * Decide whether to allow to add an Element on the Tree
     * @param elementName the name of the JDOM element concerned
     * 	      ns the schema namespace of the element
     * @return true to allow the element to be added, false otherwise
     */
    protected boolean canAddNode(String elementName, Namespace ns) {
        return doShowNode(elementName, ns);
    }

    /**
     * Get the Metadata document
     * @return the metadata document
     */
    public Metadata getMetadata() {
        return _metadata;
    }

    /**
     * Get the Child count in the Model
     * @param parent the parent node
     * @return the Number of children of parent
     */
    public int getChildCount(Object parent) {
        MD_TreeNode elementNode = (MD_TreeNode) parent;
        return elementNode.getChildCount();
    }

    /**
     * Get the child node at index point
     * @param parent The parent node
     * @param index The index of the child
     * @return The Child node at the index point
     */
    public Object getChild(Object parent, int index) {
        MD_TreeNode elementNode = (MD_TreeNode) parent;
        return elementNode.getChildAt(index);
    }

    /**
     * Set the value of a node
     * @param aValue The value to set
     * @param node The node whose value will be set
     * @param column The column of the node
     */
    public void setValueAt(Object aValue, Object node, int column) {

    }

    /**
     * Get the value for a given node and column.
     * This asks for the value at column 1 or greater, column 0 is the JTree
     * @param node The Node to get the value for
     * @param column The column
     * @return The appropriate value.
     */
    public Object getValueAt(Object node, int column) {
        MD_TreeNode elementNode = (MD_TreeNode)node;
        switch(column) {
            case 1:
                // Return the Text of the JDOM Element
                return elementNode.getElement().getText();
            case 2:
                // Return the Attribute value, if any
                List list = elementNode.getElement().getAttributes();
                if(!list.isEmpty()) {
                    Attribute att = (Attribute)list.get(0);
                    return att.getValue();
                }
                return ""; //$NON-NLS-1$
        }
        return ""; //$NON-NLS-1$
    }

    /**
     * Determine whether a particular cell is editable.
     * @param node The node we are asking.
     * @param column The column to query
     * @return true if the cell is editable, false otherwise
     */
    public boolean isCellEditable(Object node, int column) {
        switch(column) {
            // Column 0 (the JTree) has to be true so that the TreeTable expands and contracts
            case 0:
                return true;
            case 1:
                return false;
            case 2:
                return false;
            default:
                return false;
        }
    }

    /**
     * Return the type of class for each column.
     * Column 0 has to be of type TreeTableModel.class in order for it to work
     * @param column The column to query
     * @return The class associated with each column
     */
    public Class getColumnClass(int column) {
        return cTypes[column];
    }

    /**
     * Get the number of columns in our model.
     * @return The number of columns in our model.
     */
    public int getColumnCount() {
        return cNames.length;
    }

    /**
     * Get the name for a particular column.
     * @param column The column to ask.
     * @return The name of the column.
     */
    public String getColumnName(int column) {
        return cNames[column];
    }
}

