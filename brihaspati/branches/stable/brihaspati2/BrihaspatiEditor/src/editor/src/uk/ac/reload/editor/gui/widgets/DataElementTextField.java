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

import java.awt.Color;

import javax.swing.BorderFactory;

import org.jdom.Namespace;

import uk.ac.reload.editor.datamodel.DataElement;

/**
 * A Text Field that can be bound either to a JDOM Element or Attribute.  Any text changes will be
 * notified to listeners via the DocumentListenerAdapter.
 * <p>
 *
 * @author Phillip Beauvoir
 * @version $Id: DataElementTextField.java,v 1.1 1998/03/26 15:32:14 ynsingh Exp $
 */
public class DataElementTextField
extends TextFieldWidget
{
    /**
     * Document Change Listener Adapter
     */
    private DocumentListenerAdapter _docListenerAdapater;
    
	/**
	 * Default Constructor
	 */
	public DataElementTextField() {
		super();
	}
	
	/**
	 * Constructor with text
	 */
	public DataElementTextField(String text) {
		this();
		setText(text);
	}

	/**
	 * Constructor for max length of String
	 * @param maxLength the Maximum String length
	 */
	public DataElementTextField(int maxLength) {
		super(maxLength);
	}
	
	/**
	 * @return The LD Document Listener Adapter
	 */
	public DocumentListenerAdapter getDocumentListenerAdapter() {
	    if(_docListenerAdapater == null) {
	        _docListenerAdapater = new DocumentListenerAdapter(this);
	    }
	    return _docListenerAdapater;
	}
	
	/**
	 * Set the UI
	 */
	protected void setUI() {
		setBorder(BorderFactory.createLineBorder(Color.GRAY));
	}
	
	/**
	 * Set the Attribute to be edited.  The Attribute will be edited.
	 * @param ldelement The bound JDOM Element of the Attribute to be edited
	 * @param attName The name of the Attribute to be edited
	 * @param ns The Namespace of the attribute.  If null the then no namespace.
	 */
	public void setAttribute(DataElement dataElement, String attName, Namespace ns) {
	    getDocumentListenerAdapter().setAttribute(dataElement, attName, ns);
	}

	/**
	 * Set the element to be edited.  The Element itself will be edited.
	 * @param ldelement The bound JDOM Element
	 */
	public void setElement(DataElement dataElement) {
	    getDocumentListenerAdapter().setElement(dataElement);
	}
}