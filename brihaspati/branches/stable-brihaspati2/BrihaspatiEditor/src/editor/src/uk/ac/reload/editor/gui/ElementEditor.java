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

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import org.jdom.Element;

import uk.ac.reload.editor.datamodel.ElementBinding;
import uk.ac.reload.editor.gui.widgets.TextPaneWidget;
import uk.ac.reload.editor.gui.widgets.Widget;
import uk.ac.reload.editor.gui.widgets.WidgetFactory;
import uk.ac.reload.jdom.XMLDocument;
import uk.ac.reload.jdom.XMLDocumentListener;
import uk.ac.reload.jdom.XMLDocumentListenerEvent;
import uk.ac.reload.moonunit.SchemaDocument;
import uk.ac.reload.moonunit.schema.SchemaElement;


/**
 * A Panel to display and edit a JDOM Element.
 * This could be a single-line text field or a larger text area.
 *
 * @author Phillip Beauvoir
 * @version $Id: ElementEditor.java,v 1.1 1998/03/26 15:24:11 ynsingh Exp $
 */
public class ElementEditor
extends JPanel
implements XMLDocumentListener, DocumentListener
{
	/**
	 * The ElementBinding we are editing
	 */
    protected ElementBinding _elementBinding;
	
	/**
	 * The widget
	 */
    private Widget _widget;
	
	/**
	 * Whether we notify or not
	 */
    private boolean _allowNotification = true;
	
	/**
	 * Constructor
	 */
	public ElementEditor() {
		setLayout(new BorderLayout());
		setOpaque(false);  // Set Opaque so portions don't show up
	}
	
	/**
	 * Set The Panel to display the data for the Element
	 */
	public void setElementBinding(ElementBinding elementBinding) {
		// Remove old Listener
		if(_elementBinding != null) {
		    _elementBinding.getSchemaDocument().removeXMLDocumentListener(this);
		}
		
		_elementBinding = elementBinding;
		
		// Add Listener
		_elementBinding.getSchemaDocument().addXMLDocumentListener(this);
		
		// Set it up
		setupWidget(_elementBinding.getSchemaDocument(), _elementBinding.getElement(), _elementBinding.getSchemaElement());
	}
	
	/**
	 * @return The ElementBinding
	 */
	public ElementBinding getElementBinding() {
	    return _elementBinding;
	}
	
	/**
	 * Set The Widget to display the data for the Element
	 */
	protected void setupWidget(SchemaDocument doc, Element element, SchemaElement schemaElement) {
		if(_widget != null) {
		    _widget.removeDocumentListener(this);
		}
		
		// Remove all components from the panel
		removeAll();
		
		// If this Element is merely a container do not show
		boolean enabled = schemaElement == null ? true : schemaElement.isValue();
		
		if(enabled) {
			// Get and display the Widget
			_widget = WidgetFactory.getWidget(doc.getSchemaController(), schemaElement);
			
			if(_widget.isSingleLine()) {
				add((Component)_widget, BorderLayout.NORTH);
			}
			else if(_widget instanceof TextPaneWidget) {
				JScrollPane scrollPane = new JScrollPane((Component) _widget);
				add(scrollPane, BorderLayout.CENTER);
			}
			else {
				add((Component) _widget, BorderLayout.CENTER);
			}
			
			// Set the text value
			setText(element);
			
			// Listener
			_widget.addDocumentListener(this);
		}
	}
	
    /**
     * @return Returns the widget.
     */
    public Widget getWidget() {
        return _widget;
    }
	
	/**
	 * Set the Text in the widget to that in the Element
	 */
	protected void setText(Element element) {
		_allowNotification = false;
		if(_widget != null) {
		    _widget.setTextValue(element == null ? "" : element.getText());
		}
		_allowNotification = true;
	}
	
	/**
	 * Insert text in the widget
	 */
	public void insertText(String text) {
		if(_widget != null) {
		    _widget.insertTextValue(text);
		}
	}
	
	/**
	 * Set the focus
	 */
	public void setFocus() {
		if(_widget != null) {
		    ((Component)_widget).requestFocus();
		}
	}
	
	/**
	 * Clean up
	 */
	public void cleanup() {
	    if(_elementBinding != null) {
	        _elementBinding.getSchemaDocument().removeXMLDocumentListener(this);
	    }
	    
	    if(_widget != null) {
	        _widget.removeDocumentListener(this);
	    }
	}
	
	/**
	 * Update the Model
	 */
	protected synchronized void fireElementChanged(String text) {
		Element element = _elementBinding.getElement();
		
		// Element was null so create new one
		if(element == null) {
		    element = _elementBinding.createElement(this);
		}
		
		if(element != null) {
			element.setText(text);
			_elementBinding.getSchemaDocument().changedElement(this, element);
		}
	}
	
//	=============================================================================
//	===================== These events are received FROM the Document =====
//	=============================================================================
	
	public void elementChanged(XMLDocumentListenerEvent event) {
		// Don't listen to events that we have fired
		if(event.getSource() == this) return;
		
		// If it's us update
		Element element = _elementBinding.getElement();
		if(element != null) {
			Element changed_element = event.getElement();
			if(element == changed_element) {
			    setText(element);
			}
		}
	}
	
	public void elementRemoved(XMLDocumentListenerEvent event) {}
	public void elementAdded(XMLDocumentListenerEvent event) {}
	public void documentSaved(XMLDocument doc) {}
	
	
//	==============================================================================
//	DocumentListener for changes in the Widget
//	==============================================================================
	
	public void removeUpdate(DocumentEvent evt) {
		if(_allowNotification && _widget != null) fireElementChanged(_widget.getTextValue());
	}
	
	public void changedUpdate(DocumentEvent evt) {
		if(_allowNotification && _widget != null) fireElementChanged(_widget.getTextValue());
	}
	
	public void insertUpdate(DocumentEvent evt) {
		if(_allowNotification && _widget != null) fireElementChanged(_widget.getTextValue());
	}
}
