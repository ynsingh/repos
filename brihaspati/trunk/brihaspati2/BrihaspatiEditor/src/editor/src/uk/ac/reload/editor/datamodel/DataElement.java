/**
 *  RELOAD TOOLS
 *
 *  Copyright (c) 2004 Oleg Liber, Bill Olivier, Phillip Beauvoir
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

package uk.ac.reload.editor.datamodel;

import org.jdom.Element;

import uk.ac.reload.jdom.XMLPath;

/**
 * This is a wrapper class for a JDOM Element that may or may not exist yet.<p>
 * All we may know about the Element is its last known ancestor and the remaining
 * XML Path fragment offset required to get it and create it if need be.  This is used for
 * Text Fields that need to be bound to a JDOM Element that may or may not exist yet.
 * Once an edit happens on the Text field, the JDOM Element can be created at that point.
 * It is also used to wrap an existing known JDOM Element.
 *
 * @author Phillip Beauvoir
 * @version $Id: DataElement.java,v 1.1 1998/03/26 15:24:11 ynsingh Exp $
 */
public class DataElement {
    
    /**
     * The DataModel
     */
    private DataModel _dataModel;

    /**
     * The last known Ancestor JDOM Element
     */
    private Element _ancestorElement;
    
    /**
     * The XMLPath fragment to append to the ancestor to get to the element
     */
    private XMLPath _xmlElementPathFragment;
    
    /**
     * The actual bound Element
     */
    private Element _element;
    
    /**
     * Default Constructor
     */
    public DataElement() {
    }
    
    /**
     * Constructor for possibly uncreated Element.
     * 
     * @param dataModel The DataModel
     * @param ancestorElement The last known Ancestor JDOM Element
     * @param xmlElementPath The XMLPath fragment to append to the ancestor to get to the element
     */
    public DataElement(DataModel dataModel, Element ancestorElement, XMLPath xmlElementPathFragment) {
        _dataModel = dataModel;
        _ancestorElement = ancestorElement;
        _xmlElementPathFragment = xmlElementPathFragment;
        
        // Get the Element. This may be null if it hasn't been created yet.
        _element = dataModel.getSchemaDocument()
        		   .getElement(ancestorElement, xmlElementPathFragment);
    }
    
    /**
     * Constructor for known existing Element.
     * @param dataModel The DataModel
     * @param element The JDOM XML Element that exists
     */
    public DataElement(DataModel dataModel, Element element) {
        _dataModel = dataModel;
        _element = element;
        _ancestorElement = element;
        _xmlElementPathFragment = new XMLPath("");
    }
    
    /**
     * @return The associated DataModel
     */
    public DataModel getDataModel() {
        return _dataModel;
    }
    
    /**
     * @return The XMLPath fragment to append to the ancestor to get to the element
     */
    public XMLPath getXMLElementPathFragment() {
        return _xmlElementPathFragment;
    }

    /**
     * @return The full XMLPath of the ancestor element plus appended XMLPath fragment
     */
    public XMLPath getXMLElementPath() {
        XMLPath xmlPath = XMLPath.getXMLPathForElement(_ancestorElement);
        return xmlPath.appendElementName(_xmlElementPathFragment.getPath());
    }

    /**
     * @return The ancestor Element.
     */
    public Element getAncestorElement() {
        return _ancestorElement;
    }

    /**
     * @return The actual bound Element.  This may be null if it hasn't been created yet.
     */
    public Element getElement() {
        return _element;
    }
    
    /**
     * Create the bound Element in the XML Document.
     * @return The actual Element.
     */
    public Element createElement() {
        if(_element == null && _ancestorElement != null && _xmlElementPathFragment != null) {
            _element = getDataModel().getSchemaDocument()
            		   .addElementByXMLPath(this, _ancestorElement, _xmlElementPathFragment, false);
        }
        return _element;
    }
    
    /**
     * Delete the bound Element in the XML Document (if it exists).  
     * This will not delete the parent Elements as per the xml path fragment
     */
    public void deleteElement() {
        if(_element != null) {
            getDataModel().getSchemaDocument().removeElement(this, _element);
            _element = null;
        }
    }
}
