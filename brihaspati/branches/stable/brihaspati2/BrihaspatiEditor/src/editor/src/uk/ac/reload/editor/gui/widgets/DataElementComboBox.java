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

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import org.jdom.Attribute;
import org.jdom.Element;

import uk.ac.reload.dweezil.gui.FlatComboBox;
import uk.ac.reload.editor.datamodel.DataElement;

/**
 * A Combo Box used tied to a JDOM Element or Attribute<br>
 * <br>
 *
 * @author Phillip Beauvoir
 * @version $Id: DataElementComboBox.java,v 1.1 1998/03/26 15:32:14 ynsingh Exp $
 */
public class DataElementComboBox
extends FlatComboBox
implements ItemListener
{

    /**
	 * The bound JDOM Element
	 */
	private DataElement _dataElement;
	
	/**
	 * The Attribute we may be bound to.
	 * If null, no attribute
	 */
	private String _attName;


	/**
	 * Default Constructor
	 */
	public DataElementComboBox() {
	}
	
	/**
	 * Constructor
	 * @param objects
	 */
	public DataElementComboBox(Object[] objects) {
		super(objects);
		setUI();
	}
	
	/**
	 * Set the Attribute to be edited.  The Attribute will be edited.
	 * @param dataElement The bound JDOM parent Element of the Attribute to be edited
	 * @param attName The name of the Attribute to be edited
	 * @param defValue The default value.  If null, then the attribute is not set.
	 */
	public void setAttribute(DataElement dataElement, String attName, String defValue) {
	    _dataElement = dataElement;
	    _attName = attName;
	    
	    Element element = _dataElement.getElement();
	    
	    if(element != null) {
		    // Do we have the attribute?
	        Attribute att = element.getAttribute(attName);
		    
	        // Yes
	        if(att != null && !"".equals(att.getValue())) {
	            defValue = att.getValue();
		    }
	        // No, so set to a default if not null
		    else {
		        // New Attribute
		        if(defValue != null) {
		            element.setAttribute(_attName, defValue);
		        }
		    }

	        // Set in Combo
	        removeItemListener(this);
	        setSelectedItem(defValue);
	    }

	    addItemListener(this);
	}

	/**
	 * Set the element to be edited.  The Element itself will be edited.
	 * @param ldelement The bound JDOM Element
	 * @param defValue The default value.  If null, then the value is not set.
	 */
	public void setElement(DataElement dataElement, String defValue) {
	    _dataElement = dataElement;
	    _attName = null;
	    
	    Element element = _dataElement.getElement();
	    
	    if(element != null) {
	        if(!"".equals(element.getText())) {
	            defValue = element.getText();
		    }
	        // No, so set to a default if not null
		    else {
		        // New Attribute
		        if(defValue != null) {
		            element.setText(defValue);
		        }
		    }

	        // Set in Combo
	        removeItemListener(this);
	        setSelectedItem(defValue);
	    }

	    addItemListener(this);
	}

	/* (non-Javadoc)
     * @see java.awt.event.ItemListener#itemStateChanged(java.awt.event.ItemEvent)
     */
    public void itemStateChanged(ItemEvent e) {
        if(e.getStateChange() == ItemEvent.DESELECTED) {
            return;
        }
        
        Element element = _dataElement.getElement();
        
        // Create if need be
        if(element == null) {
            element = _dataElement.createElement();
        }
        
        if(element != null) {
            String selection = getSelectedItem().toString();
            
            // An Attribute
            if(_attName != null) {
                // If null selection, then remove attribute
                if("".equals(selection)) {
                    element.removeAttribute(_attName);
                }
                // Or set
                else {
                    element.setAttribute(_attName, selection);
                }
            }
            // An Element
            else {
                element.setText(selection);
            }
            
            // Notify
            _dataElement.getDataModel()
            		  .getSchemaDocument()
            		  .changedElement(this, element);
        }
    }
}