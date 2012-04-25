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

import javax.swing.Icon;

import org.jdom.Element;

import uk.ac.reload.dweezil.gui.tree.DweezilTreeNode;
import uk.ac.reload.editor.datamodel.ElementBinding;
import uk.ac.reload.moonunit.SchemaDocument;
import uk.ac.reload.moonunit.schema.SchemaElement;



/**
 * An implementation of DefaultMutableTreeNode for Containing:<p>
 * ElementBinding
 * SchemaElement
 * SchemaDocument
 *
 * @author Phillip Beauvoir
 * @version $Id: ElementTreeNode.java,v 1.1 1998/03/26 15:24:11 ynsingh Exp $
 */
public abstract class ElementTreeNode
extends DweezilTreeNode
{
	/**
	 * The associated SchemaElement
	 */
    private SchemaElement _schemaElement;
	
	/**
	 * The Parent Document
	 */
    private SchemaDocument _doc;
	
	/**
	 * The ElementBinding
	 */
    private ElementBinding _eb;
	
	
	/**
	 * Constructor for Root Element
	 * @param doc The SchemaDocument DOM Document
	 */
	public ElementTreeNode(SchemaDocument doc) {
		super(doc.getRootElement());
		_schemaElement = doc.getSchemaController().getSchemaModel().getRootElement();
		_doc = doc;
	}
	
	/**
	 * Constructor for child Element
	 * @param element The JDOM Element
	 */
	public ElementTreeNode(Element element) {
		super(element);
	}
	
	/**
	 * Because other classes may not be interested in tree nodes we can return
	 * the important bits wrapped up in a ElementBinding
	 * @return ElementBinding
	 */
	public ElementBinding createElementBinding() {
		if(_eb == null) {
		    _eb = new ElementBinding(getElement(), getSchemaElement(), getSchemaDocument());
		}
		return _eb;
	}
	
	/**
	 * Get the JDOM Element
	 * @return The JDOM Element
	 */
	public Element getElement() {
		return (Element)getUserObject();
	}
	
	/**
	 * Get the SchemaElement
	 * @return The associated SchemaElement
	 */
	public SchemaElement getSchemaElement() {
		if(_schemaElement == null && getParent() != null) {
			_schemaElement = ((ElementTreeNode) getParent()).getSchemaElement();
			if(_schemaElement != null) {
			    _schemaElement = _schemaElement.getChild(getElement().getName(), getElement().getNamespace());
			}
		}
		return _schemaElement;
	}
	
	/**
	 * @return the Parent SchemaDocument
	 */
	public SchemaDocument getSchemaDocument() {
		if(_doc == null && getParent() != null) {
		    _doc = ((ElementTreeNode)getParent()).getSchemaDocument();
		}
		return _doc;
	}
	
	/**
	 * @return the name of the Element
	 */
	public String getName() {
		return getElement().getName();
	}
	
	/**
	 * @return the Leaf Icon to display
	 */
	public Icon getLeafIcon() {
		return ((TreeIconInterface)getSchemaDocument()
		        .getSchemaController())
		        .getLeafIcon(getElement().getName(), getElement().getNamespace());
	}
	
	/**
	 * @return the Open Icon to display
	 */
	public Icon getOpenIcon() {
		return ((TreeIconInterface)getSchemaDocument()
		        .getSchemaController())
		        .getOpenIcon(getElement().getName(), getElement().getNamespace());
	}
	
	/**
	 * @return the Closed Icon to display
	 */
	public Icon getClosedIcon() {
		return ((TreeIconInterface)getSchemaDocument()
		        .getSchemaController())
		        .getClosedIcon(getElement().getName(), getElement().getNamespace());
	}
	
}
