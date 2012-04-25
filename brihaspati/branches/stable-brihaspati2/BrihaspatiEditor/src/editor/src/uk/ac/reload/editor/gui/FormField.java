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


package uk.ac.reload.editor.gui;

import java.awt.Component;

import org.jdom.Element;

import uk.ac.reload.moonunit.SchemaDocument;
import uk.ac.reload.moonunit.schema.SchemaElement;

/**
 * An Edit Field Form Text widget abstract class
 *
 * @author Phillip Beauvoir
 * @version $Id: FormField.java,v 1.1 1998/03/26 15:24:11 ynsingh Exp $
 */
public abstract class FormField {
	/**
	 * The owner
	 */
    private SchemaDocument _schemaDoc;
	
	/**
	 * The Element we represent
	 */
    private Element _element;
	
	/**
	 * Our schema template
	 */
    private SchemaElement _schemaElement;
	
	/**
	 * Default Constructor
	 */
	public FormField() { }
	
	/**
	 * Constructor
	 */
	public FormField(SchemaDocument schemaDocument, SchemaElement schemaElement) {
		_schemaDoc = schemaDocument;
		_schemaElement = schemaElement;
	}
	/**
	 * Constructor
	 */
	public FormField(SchemaDocument schemaDocument, Element element, SchemaElement schemaElement) {
		_schemaDoc = schemaDocument;
		_element = element;
		_schemaElement = schemaElement;
	}
	
	/**
	 * Set the Element
	 * @param element
	 */
	public void setElement(Element element) {
	    _element = element;
	}
	
	/**
	 * @return The Element
	 */
	public Element getElement() {
	    return _element;
	}
	
	/**
	 * @return The SchemaDocument
	 */
	public SchemaDocument getSchemaDocument() {
	    return _schemaDoc;
	}
	
	/**
	 * @return The SchemaElement
	 */
	public SchemaElement getSchemaElement() {
	    return _schemaElement;
	}

	/**
	 * Get the value of this Widget
	 * @return The value contained in the Widget
	 */
	public abstract String getValue();
	
	/**
	 * Set the value of the Widget
	 * @param value The value
	 */
	public abstract void setValue(String value);
	
	/**
	 * @return the component used
	 */
	public abstract Component getComponent();
	
	/**
	 * Clean up
	 */
	public abstract void cleanup();
}