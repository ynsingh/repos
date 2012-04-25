
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

import org.jdom.Element;

import uk.ac.reload.editor.gui.FormField;
import uk.ac.reload.jdom.XMLDocument;
import uk.ac.reload.jdom.XMLDocumentListener;
import uk.ac.reload.jdom.XMLDocumentListenerEvent;
import uk.ac.reload.moonunit.SchemaDocument;
import uk.ac.reload.moonunit.schema.SchemaElement;


/**
 * The MD Form Widget
 *
 * @author Phillip Beauvoir
 * @version $Id: MD_Field.java,v 1.1 1998/03/26 15:40:32 ynsingh Exp $
 */
public abstract class MD_Field
extends FormField
implements XMLDocumentListener
{
	/**
	 * Constructor
	 * @param doc The parent SchemaDocument
	 * @param schemaElement The bound SchemaElement
	 */
	public MD_Field(SchemaDocument doc, SchemaElement schemaElement) {
		super(doc, schemaElement);
	}
	
	/**
	 * Tell the SchemaDocument that we changed
	 */
	protected synchronized void fireElementChanged() {
		String text = getValue();
		
		// Check first if we are not yet bound to an Element.  If not, add a new, unique, Element
		if(getElement() == null) {
		    // This call will also take care of firing the appropriate event
		    Element element = getSchemaDocument().addElementUniqueBySchemaUndoable(this, getSchemaElement(), true);
		    setElement(element);
		}
		
		// Now we have the Element(check for null just in case), set the Element's text value and tell the SchemaDocument
		if(getElement() != null) {
		    getElement().setText(text);
		    getSchemaDocument().changedElement(this, getElement());
		}
	}
	
	
	//	=============================================================================
	//	===================== These events are received FROM the Document ===========
	//	=============================================================================
	
	/* (non-Javadoc)
	 * @see uk.ac.reload.dweezil.xml.XMLDocumentListener#elementAdded(uk.ac.reload.dweezil.xml.XMLDocumentListenerEvent)
	 */
	public void elementAdded(XMLDocumentListenerEvent event) {
		// Don't listen to events that we have fired
		if(event.getSource() == this) {
		    return;
		}
		
		addElement(event.getElement());
	}
	
	/**
	 * Add a new Element to this form field
	 * @param element The Element to add
	 */
	protected void addElement(Element element) {
		// Is this us?
		if(element == getSchemaDocument().getElement(getSchemaElement())) {
			setElement(element);
			setValue(getElement().getText());
		}
		// Check children (recurse)
		else {
			Iterator it = element.getChildren().iterator();
			while(it.hasNext()) {
				Element child = (Element) it.next();
				addElement(child);
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see uk.ac.reload.dweezil.xml.XMLDocumentListener#elementChanged(uk.ac.reload.dweezil.xml.XMLDocumentListenerEvent)
	 */
	public void elementChanged(XMLDocumentListenerEvent event) {
		// Don't listen to events that we have fired
		if(event.getSource() == this) {
		    return;
		}
		
		// We changed!
		Element element = event.getElement();
		if(element == getElement()) {
		    setValue(getElement().getText());
		}
	}
	
	/* (non-Javadoc)
	 * @see uk.ac.reload.dweezil.xml.XMLDocumentListener#elementRemoved(uk.ac.reload.dweezil.xml.XMLDocumentListenerEvent)
	 */
	public void elementRemoved(XMLDocumentListenerEvent event) {
		// Don't listen to events that we have fired
		if(event.getSource() == this) return;
		
		Element element = event.getElement();
		if(getElement() != null) {
			// It was an Element like us that was removed!
			if(element == getElement() || getElement().isAncestor(element)) {
				// Is there another one like us?
				setElement(getSchemaDocument().getElement(getSchemaElement()));
				// No
				if(getElement() == null) {
				    setValue("");
				}
				// Yes
				else {
				    setValue(getElement().getText());
				}
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see uk.ac.reload.dweezil.xml.XMLDocumentListener#documentSaved(uk.ac.reload.dweezil.xml.XMLDocument)
	 */
	public void documentSaved(XMLDocument doc) {}
}
