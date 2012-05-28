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

import java.awt.Color;
import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import uk.ac.reload.editor.gui.widgets.NumberFieldWidget;
import uk.ac.reload.editor.gui.widgets.TextAreaWidget;
import uk.ac.reload.editor.gui.widgets.TextFieldWidget;
import uk.ac.reload.editor.gui.widgets.Widget;
import uk.ac.reload.editor.metadata.xml.Metadata;
import uk.ac.reload.moonunit.SchemaController;
import uk.ac.reload.moonunit.schema.SchemaElement;



/**
 * A Text Area widget.
 *
 * @author Phillip Beauvoir
 * @version $Id: MD_TextField.java,v 1.1 1998/03/26 15:40:32 ynsingh Exp $
 */
public class MD_TextField
extends MD_Field
implements DocumentListener
{
	/**
	 * An internal flag
	 */
    private boolean _allowNotification = true;
	
	/**
	 * The suitable Component we shall use
	 */
    private Widget _widget;
	
	/**
	 * Constructor
	 */
	public MD_TextField(Metadata metadata, SchemaElement schemaElement) {
		super(metadata, schemaElement);
		
		// Determine type of text field
		_widget = getMetadataTextFieldWidget(schemaElement);
		
		// Border
		((JComponent)_widget).setBorder(BorderFactory.createLineBorder(Color.GRAY));
		
		setElement(metadata.getElement(getSchemaElement()));
		if(getElement() != null) setValue(getElement().getText());
		
		// We listen to Text Changes
		_widget.addDocumentListener(this);
		
		// We listen to Element changes
		metadata.addXMLDocumentListener(this);
	}
	
	/**
	 * @return A Text Widget suitable for the MD Form display
	 */
	protected Widget getMetadataTextFieldWidget(SchemaElement schemaElement) {
		if(schemaElement == null) return getDefaultWidget();
		
		Widget component = null;
		
		SchemaController schemaController = getSchemaDocument().getSchemaController();
		
		String widget_type = schemaController.getWidgetType(schemaElement.getXMLPath());
		
		if(widget_type != null) {
			if(widget_type.equals("textpane")) {
				component = new TextAreaWidget();
				((TextAreaWidget)component).setColumns(50);
			}
			else if(widget_type.equals("numberfield")) {
				component = new NumberFieldWidget();
				((NumberFieldWidget)component).setColumns(30);
			}
			else component = getDefaultWidget();
		}
		else component = getDefaultWidget();
		
		// Set Max String length
		int maxLength = -1;
		String value = schemaController.getFacetValue(schemaElement, "maxLength");
		if(value != null) maxLength = Integer.parseInt(value);
		component.setMaxLength(maxLength);
		
		return component;
	}
	
	/**
	 * @return a default widget
	 */
	private Widget getDefaultWidget() {
		TextFieldWidget textField = new TextFieldWidget();
		textField.setColumns(50);
		return textField;
	}
	
	/**
	 * Clean up
	 */
	public void cleanup() {
		_widget.removeDocumentListener(this);
		getSchemaDocument().removeXMLDocumentListener(this);
	}
	
	/**
	 * Get the value of this Widget
	 * @return The value contained in the Widget
	 */
	public String getValue() {
		return _widget.getTextValue();
	}
	
	/**
	 * Set the value of the Widget
	 * @param value The value
	 */
	public void setValue(String value) {
		_allowNotification = false;
		_widget.setTextValue(value);
		_allowNotification = true;
	}
	
	/**
	 * @return the Component that we're using
	 */
	public Component getComponent() {
		return (Component)_widget;
	}
	
//	=============================================================================
//	=== These events are fired from the JTextArea when the textfield changes ====
//	=============================================================================
	
	public void removeUpdate(DocumentEvent evt) {
		if(_allowNotification) fireElementChanged();
	}
	
	public void changedUpdate(DocumentEvent evt) {
		if(_allowNotification) fireElementChanged();
	}
	
	public void insertUpdate(DocumentEvent evt) {
		if(_allowNotification) fireElementChanged();
	}
	
}