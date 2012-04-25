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

package uk.ac.reload.editor.datamodel;

import java.io.Serializable;

import org.jdom.Element;

import uk.ac.reload.jdom.XMLPath;
import uk.ac.reload.moonunit.SchemaController;
import uk.ac.reload.moonunit.SchemaDocument;
import uk.ac.reload.moonunit.schema.SchemaElement;


/**
 * A convenience class to contain a JDOM Element, its associated SchemaElement
 * and the SchemaDocument to which the Element belongs.<br>
 * It has to implement Serializable for drag and drop to work
 *
 * @author Phillip Beauvoir
 * @version $Id: ElementBinding.java,v 1.1 1998/03/26 15:24:11 ynsingh Exp $
 */
public class ElementBinding implements Serializable {
	
	/**
	 * The JDOM Element
	 * This is transient to stop drag and drop wanting serializable members
	 */
    private transient Element _element;
	
	/**
	 * The JDOM Parent Element - useful if you don't know the Element
	 * This is transient to stop drag and drop wanting serializable members
	 */
    private transient Element _parent;
	
	/**
	 * The associated SchemaElement
	 * This is transient to stop drag and drop wanting serializable members
	 */
    private transient SchemaElement _schemaElement;
	
	/**
	 * The SchemaDocument to which the Element belongs
	 * This is transient to stop drag and drop wanting serializable members
	 */
    private transient SchemaDocument _doc;
	
	/**
	 * Constructor
	 * @param element The JDOM Element
	 * @param schemaElement The associated SchemaElement
	 * @param doc The SchemaDocument to which the Element belongs
	 */
	public ElementBinding(Element element, SchemaElement schemaElement, SchemaDocument doc) {
		_element = element;
		_schemaElement = schemaElement;
		_doc = doc;
	}
	
	/**
	 * @return the JDOM Element
	 */
	public Element getElement() {
		return _element;
	}
	
	/**
	 * Set the Parent Element
	 */
	public void setParentElement(Element parent) {
		_parent = parent;
	}
	
	/**
	 * Get the SchemaElement
	 * @return The associated SchemaElement
	 */
	public SchemaElement getSchemaElement() {
		return _schemaElement;
	}
	
	/**
	 * Could use this method instead if we had an Element but no SchemaElement
	 * @return The associated SchemaElement
	 */
	public SchemaElement __getSchemaElement() {
		if(_schemaElement == null && _element != null) {
			XMLPath xmlPath = XMLPath.getXMLPathForElement(_element);
			_schemaElement = (SchemaElement)getSchemaController().getSchemaNode(xmlPath);
		}
		return _schemaElement;
	}
	
	/**
	 * Get the SchemaDocument
	 * @return The SchemaDocument to which the Element belongs
	 */
	public SchemaDocument getSchemaDocument() {
		return _doc;
	}
	
	/**
	 * @return The SchemaController
	 */
	public SchemaController getSchemaController() {
		return _doc.getSchemaController();
	}
	
	/**
	 * If the Element is null (as it might well be) try to create a new one given
	 * that we have the parent Element set
	 * @return the newly created Element or null if not successful
	 */
	public Element createElement(Object source) {
		if(_parent != null) {
			_element = _doc.addElementBySchema(source, _parent, getSchemaElement(), false);
		}
		return _element;
	}
}