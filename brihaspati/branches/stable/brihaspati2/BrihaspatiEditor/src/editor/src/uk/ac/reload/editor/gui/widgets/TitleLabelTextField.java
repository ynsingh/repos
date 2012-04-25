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

package uk.ac.reload.editor.gui.widgets;

import javax.swing.JTextField;

import uk.ac.reload.dweezil.gui.HTMLLabelTextField;
import uk.ac.reload.editor.datamodel.DataComponent;

/**
 * 
 * HTMLLabelTextField for editing Title Fields.
 *
 * @author Phillip Beauvoir
 * @version $Id: TitleLabelTextField.java,v 1.1 1998/03/26 15:32:14 ynsingh Exp $
 */
public class TitleLabelTextField
extends HTMLLabelTextField
{
	
	/**
	 * The Textfield
	 */
	private DataElementTextField _textField;
	
	/**
	 * The DataComponent
	 */
	private DataComponent _component;
	
	/**
	 * Default Constructor
	 */
	public TitleLabelTextField() {
	    this("");
	}

	/**
	 * Constructor
	 * @param text The text to set in the Field
	 */
	public TitleLabelTextField(String text) {
	    super(text);
	}
	
	/**
	 * Set the associated DataComponent that has a Title
	 * @param component
	 */
	public void setComponent(DataComponent component) {
	    /*
	     * Because a focus lost event happens *after* a new component selection,
	     * it's possible this can be set to a new component before we've finished editing.
	     */
	    if(isEditing()) {
	        finishEdit();
	    }
	    
	    _component = component;
	    setText((component == null) ? "Title" : component.getTitle());
	}
	
	/**
	 * Over-ride to do stuff
	 */
	protected void setup() {
	    super.setup();
	    getLabel().setToolTipText("Double-click to edit title");
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

	/**
	 * Over-ride Finish the TextField Edit
	 */
	protected void finishEdit() {
	    super.finishEdit();
	    if(_component != null) {
	        if(getText() != null && !getText().equals(_component.getTitle())) {
		        _component.setTitle(getText());
		        // Tell Listeners
		        _component.getDataModel().fireDataComponentChanged(_component);
	        }
	    }
	}
}