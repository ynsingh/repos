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

import uk.ac.reload.dweezil.gui.tree.DweezilTreeNode;
import uk.ac.reload.moonunit.schema.SchemaElement;


/**
 * A Viewer Tree Node that will display the hierarchical contents of an XML Schema.
 * The TestTreeModel class takes care of taking the Schema and creating tree nodes.
 * A NodeViewer TextPane will display the information for a node when it is selected.
 * This is for testing purposes only.
 *
 * @author Phillip Beauvoir
 * @version $Id: SchemaTreeNode.java,v 1.1 1998/03/26 15:32:14 ynsingh Exp $
 */
public class SchemaTreeNode
extends DweezilTreeNode
{
    /**
     * We've already added the children
     */
    boolean childNodesAdded;

    /**
     * Constructor
     * @param schemaElement The SchemaElement
     */
    public SchemaTreeNode(SchemaElement schemaElement) {
        super(schemaElement);
    }

    /**
     * @return The wrapped SchemaElement
     */
    public SchemaElement getSchemaElement() {
        return (SchemaElement)getUserObject();
    }
    
    /**
     * @return The Name of the SchemaElement
     */
    public String getName() {
        return getSchemaElement().getName();
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    public String toString() {
        String s = getName();
        
        if(((SchemaElement)getUserObject()).isChoiceElement()) {
            s = "(choice) " + s;
        }
        
        return s;
    }
}
