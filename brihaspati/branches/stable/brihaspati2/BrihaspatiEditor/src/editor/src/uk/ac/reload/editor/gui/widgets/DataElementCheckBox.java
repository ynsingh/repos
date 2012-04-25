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

package uk.ac.reload.editor.gui.widgets;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.jdom.Attribute;
import org.jdom.Element;

import uk.ac.reload.dweezil.gui.FlatCheckBox;
import uk.ac.reload.editor.datamodel.DataElement;

/**
 * A Check Box bound to a JDOM Attribute or Element.
 *
 * @author Phillip Beauvoir
 * @version $Id: DataElementCheckBox.java,v 1.1 1998/03/26 15:32:14 ynsingh Exp $
 */
public class DataElementCheckBox
extends FlatCheckBox
implements ActionListener
{
    /**
	 * The bound JDOM Element
	 */
	private DataElement _dataElement;
	
	/**
	 * The Attribute name we may be bound to.
	 * If null, no attribute
	 */
	private String _attName;

	/**
     * Default Constructor
     */
    public DataElementCheckBox() {
        super();
		addActionListener(this);
    }

    /**
     * Creates a check box with text and specifies whether 
     * or not it is initially selected.
     *
     * @param text the text of the check box.
     * @param selected a boolean value indicating the initial selection
     *        state. If <code>true</code> the check box is selected
     */
    public DataElementCheckBox(String text, boolean selected) {
        super(text, selected);
		addActionListener(this);
    }
    
    /**
     * Creates an initially unselected check box with text.
     * @param text the text of the check box.
     */
	public DataElementCheckBox(String text) {
		super(text);
		addActionListener(this);
	}
	
	/**
	 * Set the Attribute to be edited.  The Attribute will be edited.
	 * @param dataElement The bound JDOM parent Element of the Attribute to be edited
	 * @param attName The name of the Attribute to be edited
	 * @param defaultVal The default value, true or false
	 */
	public void setAttribute(DataElement dataElement, String attName, boolean defaultVal) {
	    _dataElement = dataElement;
	    _attName = attName;
	    
	    Element element = dataElement.getElement();
	    if(element != null) {
		    Attribute att = element.getAttribute(attName);
		    if(att != null) {
		        boolean set = "true".equals(att.getValue());
		        setSelected(set);
		    }
		    else {
		        setSelected(defaultVal);
		    }
	    }
	    else {
	        setSelected(defaultVal);
	    }
	}

	/* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent e) {
        if(_dataElement == null) {
            return;
        }
        
        Element element = _dataElement.getElement();
        
        // Create if need be
        if(element == null) {
            element = _dataElement.createElement();
        }
        
        if(element != null) {
            String text = isSelected() ? "true" : "false";
            // An Attribute
            if(_attName != null) {
                element.setAttribute(_attName, text);
            }
            // An Element
            else {
                element.setText(text);
            }
            // Notify
            _dataElement.getDataModel()
            		  .getSchemaDocument()
            		  .changedElement(this, element);
        }
    }
}