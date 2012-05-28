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


import org.jdom.Element;

import uk.ac.reload.editor.gui.ElementTreeNode;
import uk.ac.reload.editor.metadata.xml.Metadata;
import uk.ac.reload.moonunit.schema.SchemaElement;


/**
 * A Tree Node for the MD Treetable Editor
 *
 * @author Phillip Beauvoir
 * @version $Id: MD_TreeNode.java,v 1.1 1998/03/26 15:40:32 ynsingh Exp $
 */
public class MD_TreeNode
extends ElementTreeNode
{
    /**
     * Constructor for Root Element
     * @param md The Metadata DOM Document
     */
    public MD_TreeNode(Metadata md) {
        super(md);
    }

    /**
     * Constructor for child Element
     * @param element The JDOM Element
     */
    public MD_TreeNode(Element element) {
        super(element);
    }

    /**
     * Over-ride this to do special stuff
     */
    public String toString() {
        // Get Friendly name if possible, or qualified name if not
        String value = null;
        SchemaElement schemaElement = getSchemaElement();
        if(schemaElement != null) {
            value = getSchemaDocument().getSchemaController().getElementFriendlyName(schemaElement.getXMLPath());
        }
        if(value == null) value = getElement().getQualifiedName();
        return value;
    }

}
