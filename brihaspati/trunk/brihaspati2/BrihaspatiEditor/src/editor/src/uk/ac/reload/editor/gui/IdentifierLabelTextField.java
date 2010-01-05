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

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JTextField;

import org.jdom.Attribute;
import org.jdom.Element;

import uk.ac.reload.dweezil.gui.HTMLLabelTextField;
import uk.ac.reload.editor.datamodel.DataElement;
import uk.ac.reload.editor.gui.widgets.DataElementTextField;

/**
 * 
 * HTMLLabelTextField for editing Identifier Fields.<p>
 * This will always be the "identifier" attribute of a given element.<p>
 *
 * @author Phillip Beauvoir
 * @version $Id: IdentifierLabelTextField.java,v 1.1 1998/03/26 15:24:11 ynsingh Exp $
 */
public class IdentifierLabelTextField
extends HTMLLabelTextField
{
	
    /**
     * The Background Color
     */
    public static final Color BKG_COLOR = new Color(240, 240, 255);

    /**
	 * The bound JDOM Element that is the parent of the "identifier" attribute
	 */
	private DataElement _dataElement;

	/**
	 * The Textfield
	 */
	private DataElementTextField _textField;
	
	/**
	 * Default Constructor
	 */
	public IdentifierLabelTextField() {
	    this("");
	}

	/**
	 * Constructor
	 * @param text The text to set in the Field
	 */
	public IdentifierLabelTextField(String text) {
	    super(text);
	}
	
	/**
	 * Over-ride to do stuff
	 */
	protected void setup() {
	    super.setup();
	    getLabel().setToolTipText("Double-click to edit Identifier");
	    getLabel().setBorder(BorderFactory.createLineBorder(Color.GRAY));
	    setBackground(BKG_COLOR);
	}
	
	/**
	 * Set the UI
	 */
	protected void setUI() {
	    setOpaque(true);
	}

	/**
	 * Set the parent element of the "identifier" attribute to be edited.
	 * @param dataElement The bound JDOM Element
	 */
	public void setElement(DataElement dataElement) {
	    /*
	     * Because a focus lost event happens *after* a new component selection,
	     * it's possible this can be set to a new component before we've finished editing.
	     */
	    if(isEditing()) {
	        finishEdit();
	    }

	    _dataElement = dataElement;
	    
	    Element element = _dataElement.getElement();
	    if(element != null) {
		    Attribute att = element.getAttribute("identifier");
		    if(att != null) {
		        setText(att.getValue());
		    }
		    else {
		        setText("");
		    }
	    }
	}

	/**
	 * Over-ride finishing the TextField Edit and set the "identifier" attribute to the field text
	 */
	protected void finishEdit() {
	    super.finishEdit();
	    
	    if(_dataElement != null) {
	        Element element = _dataElement.getElement();
	        if(element != null) {
	            element.setAttribute("identifier", getText());
	            _dataElement.getDataModel().getSchemaDocument()
	            						 .changedElement(this, element);
	        }
	    }
	}

	/**
	 * @return The Text Field to use
	 */
	public JTextField getTextField() {
	    if(_textField == null) {
	        _textField = new DataElementTextField();
	    }
	    return _textField;
	}
}