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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;

import uk.ac.reload.dweezil.gui.DweezilComboBox;
import uk.ac.reload.editor.metadata.xml.Metadata;
import uk.ac.reload.moonunit.schema.SchemaElement;
import uk.ac.reload.moonunit.vocab.VocabularyList;


/**
 * A MD Combo Box widget.
 * This can hold an Element or an Attribute.
 *
 * @author Phillip Beauvoir
 * @version $Id: MD_ComboBox.java,v 1.1 1998/03/26 15:40:32 ynsingh Exp $
 */
public class MD_ComboBox
extends MD_Field
implements ActionListener
{
    private boolean _allowNotification = true;
	
    private DweezilComboBox _comboBox;
	
	public MD_ComboBox(Metadata metadata, SchemaElement schemaElement, VocabularyList rvList) {
		super(metadata, schemaElement);
		
		_comboBox = new DweezilComboBox(true);
		
		// Border
		_comboBox.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		
		_comboBox.setItems(rvList.getList());
		
		setElement(metadata.getElement(getSchemaElement()));
		if(getElement() != null) setValue(getElement().getText());
		else setValue("");
		
		// We listen to Text Changes
		_comboBox.addActionListener(this);
		
		// We listen to Element changes
		metadata.addXMLDocumentListener(this);
	}
	
	public void cleanup() {
	    getSchemaDocument().removeXMLDocumentListener(this);
		_comboBox.removeActionListener(this);
	}
	
	/**
	 * Get the value of this Widget
	 * @return The value contained in the Widget
	 */
	public String getValue() {
		return (String)_comboBox.getSelectedItem();
	}
	
	/**
	 * Set the value of the Widget
	 * @param value The value
	 */
	public void setValue(String value) {
		_allowNotification = false;
		_comboBox.setSelectedItem(value);
		_allowNotification = true;
	}
	
	public Component getComponent() {
		return _comboBox;
	}
	
	public void actionPerformed(ActionEvent event) {
		if(_allowNotification) fireElementChanged();
	}
}
