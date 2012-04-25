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

package uk.ac.reload.editor.metadata.editor.formview;

import java.util.Iterator;
import java.util.Vector;

import org.jdom.Element;

import uk.ac.reload.jdom.XMLPath;

/**
 * A Metadata Profile Element
 *
 * @author Phillip Beauvoir
 * @version $Id: MD_ProfileElement.java,v 1.1 1998/03/26 15:40:32 ynsingh Exp $
 */
public class MD_ProfileElement {

    public static final String ELEMENT_GROUP_NAME = "group";
	public static final String ELEMENT_ITEM_NAME = "element";
	
	public static final String ATTRIBUTE_LABEL = "name";
	public static final String ATTRIBUTE_PATH = "path";
	public static final String ATTRIBUTE_DEFAULT = "default";
	
    /**
     * The Path of the Element
     */
	private XMLPath _xmlPath;

    /**
     * The name to display for this Element
     */
	private String _name;

    /**
     * The Parent, or null if no parent
     */
	private MD_ProfileElement _parent;

    /**
     * This Element's children
     */
	private MD_ProfileElement[] _children;

    /**
     * Whether this is a Grouping
     */
	private boolean _isGroup;

    /**
     * Constructor
     */
    public MD_ProfileElement(Element element) {
        _name = element.getAttributeValue(ATTRIBUTE_LABEL);
        _xmlPath = new XMLPath(element.getAttributeValue(ATTRIBUTE_PATH));
        _isGroup = element.getName().equals(ELEMENT_GROUP_NAME);
        _children = makeChildren(element);
    }

    /**
     * Get the path for this Profile Element
     * @return
     */
    public XMLPath getXMLPath() {
        return _xmlPath;
    }

    /**
     * Get the name to display for this Element
     * @return
     */
    public String getName() {
        return _name;
    }

    /**
     * Find out if this Element is merely a container for others
     * @return
     */
    public boolean isGroup() {
        return _isGroup;
    }

    /**
     * Return the Children of this Profile Element
     * @return
     */
    public MD_ProfileElement[] getChildren() {
        return _children;
    }

    /**
     * Get the parent
     * @return
     */
    public MD_ProfileElement getParent() {
        return _parent;
    }

    /**
     * Wrap each child Element in a MD_ProfileElement
     * @param element
     * @return
     */
    private MD_ProfileElement[] makeChildren(Element element) {
        Vector v = new Vector();

        // Get all "element" children
        Iterator elementlist = element.getChildren().iterator();

        // Enumerate through them
        while(elementlist.hasNext()) {
            // Get the Element
            Element child = (Element) elementlist.next();
            // New MetadataProfileField
            MD_ProfileElement mdProfileChildElement = new MD_ProfileElement(child);
            mdProfileChildElement._parent = this;
            v.addElement(mdProfileChildElement);
        }

        MD_ProfileElement[] array = new MD_ProfileElement[v.size()];
        v.copyInto(array);
        return array;
    }
}