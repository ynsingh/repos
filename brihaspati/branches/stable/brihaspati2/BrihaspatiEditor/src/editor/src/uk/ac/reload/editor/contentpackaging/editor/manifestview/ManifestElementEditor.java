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

package uk.ac.reload.editor.contentpackaging.editor.manifestview;

import org.jdom.Element;

import uk.ac.reload.editor.contentpackaging.xml.ContentPackage;
import uk.ac.reload.editor.datamodel.ElementBinding;
import uk.ac.reload.editor.gui.ElementEditor;
import uk.ac.reload.editor.gui.widgets.FileTextFieldWidget;
import uk.ac.reload.jdom.XMLDocumentListenerEvent;
import uk.ac.reload.moonunit.SchemaDocument;
import uk.ac.reload.moonunit.contentpackaging.CP_Core;
import uk.ac.reload.moonunit.schema.SchemaElement;


/**
 * A Content Package Manifest Element Editor
 *
 * @author Phillip Beauvoir
 * @version $Id: ManifestElementEditor.java,v 1.1 1998/03/26 15:40:31 ynsingh Exp $
 */
public class ManifestElementEditor
extends ElementEditor {
	
	/**
	 * Whether we display a child Title value
	 */
    private boolean isTitleValue;
	
	/**
	 * The Title Element if we are editing an Item or Organizaton
	 */
    private Element _titleElement;
	
	/**
	 * Constructor
	 */
	public ManifestElementEditor() {
		super();
	}
	
	/**
	 * Set The Panel to display the data for the Element
	 * Over-ride this so we can get the Title Element
	 */
	public void setElementBinding(ElementBinding elementBinding) {
		// Remove old Listener
		if(_elementBinding != null) _elementBinding.getSchemaDocument().removeXMLDocumentListener(this);
		
		_elementBinding = elementBinding;
		
		// Add Listener
		_elementBinding.getSchemaDocument().addXMLDocumentListener(this);
		
		Element element = _elementBinding.getElement();
		String name = element.getName();
		
		// ORGANIZATION & ITEM elements display the child TITLE element
		if(name.equals(CP_Core.ORGANIZATION) || name.equals(CP_Core.ITEM)) {
			if(_elementBinding.getSchemaDocument().isDocumentNamespace(element)) {
				isTitleValue = true;
				SchemaElement schemaElement = _elementBinding.getSchemaElement();
				if(schemaElement != null) schemaElement = schemaElement.getChild(CP_Core.TITLE);
				// Set this to the Title element
				_titleElement = element.getChild(CP_Core.TITLE, element.getNamespace());
				setupWidget(_elementBinding.getSchemaDocument(), _titleElement, schemaElement);
			}
		}
		
		else {
			isTitleValue = false;
			setupWidget(_elementBinding.getSchemaDocument(), _elementBinding.getElement(), _elementBinding.getSchemaElement());
		}
	}
	
	/**
	 * Over-ride to do stuff
	 */
	protected void setupWidget(SchemaDocument doc, Element element, SchemaElement schemaElement) {
		super.setupWidget(doc, element, schemaElement);
		
		if(getWidget() != null) {
			// SCORM location widget needs relative path offset
			if(getWidget() instanceof FileTextFieldWidget) {
				if(schemaElement.getName().equals("location") && doc instanceof ContentPackage) {
					((FileTextFieldWidget)getWidget()).setRelativePath(((ContentPackage)doc).getProjectFolder());
				}
			}
		}
	}
	
	/**
	 * Over-ride to trap this
	 */
	public void elementChanged(XMLDocumentListenerEvent event) {
		// Don't listen to events that we have fired
		if(event.getSource() == this) return;
		
		// Are we changing the Title Element?
		if(isTitleValue && _titleElement != null) {
			Element changed_element = event.getElement();
			if(_titleElement == changed_element) setText(_titleElement);
		}
		// Nope
		else super.elementChanged(event);
	}
	
	/**
	 * Update the Model
	 */
	protected synchronized void fireElementChanged(String text) {
		// We actually edited the child Title Element
		if(isTitleValue) {
			Element element = _elementBinding.getElement();
			
			// If value is blank remove the element
			if(text.equals("")) {
				if(_titleElement != null) {
					_elementBinding.getSchemaDocument().removeElement(this, _titleElement);
					_titleElement = null;
				}
			}
			
			// Else set Title value
			else {
				if(_titleElement == null) {
					_titleElement = new Element(CP_Core.TITLE, element.getNamespace());
					_elementBinding.getSchemaDocument().addElement(this, element, _titleElement, false);
				}
				
				_titleElement.setText(text);
			}
			
			// Tell the Parent
			_elementBinding.getSchemaDocument().changedElement(this, element);
		}
		
		else super.fireElementChanged(text);
	}
}