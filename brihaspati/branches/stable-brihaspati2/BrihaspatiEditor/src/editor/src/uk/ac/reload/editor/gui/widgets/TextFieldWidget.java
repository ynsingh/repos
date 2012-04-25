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
import javax.swing.event.DocumentListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 * A TextField Widget
 *
 * @author Phillip Beauvoir
 * @version $Id: TextFieldWidget.java,v 1.1 1998/03/26 15:32:14 ynsingh Exp $
 */
public class TextFieldWidget extends JTextField
implements Widget
{
	/**
	 * The maximum number of characters allowed
	 */
    private int _maxLength;
	
	/**
	 * Whether to Verify or not
	 */
    private boolean _doVerify = true;
	
	/**
	 * Default Constructor
	 */
	public TextFieldWidget() {
		this(-1);
	}
	
	/**
	 * Default Constructor
	 */
	public TextFieldWidget(int maxLength) {
		setMaxLength(maxLength);
		setDocument(new TextFieldVerifier());
		setUI();
	}
	
	/**
	 * Insert some text
	 */
	public void insertTextValue(String text) {
		
	}
	
	/**
	 * Set the maximum allowed of digits
	 * @param maxLength
	 */
	public void setMaxLength(int maxLength) {
		_maxLength = maxLength;
	}
	
	/**
	 * Set the UI - ovr-ride if needed
	 */
	protected void setUI() {
	}
	
	/**
	 * Update the UI
	 */
	public void updateUI() {
		super.updateUI();
		setUI();
	}
	
	/**
	 * Set the Value
	 * @param value
	 */
	public void setTextValue(String value) {
		setText(value);
	}
	
	/**
	 * Over-ride
	 * @param value
	 */
	public void setText(String value) {
		_doVerify = false;
		super.setText(value);
		_doVerify = true;
	}
	
	/**
	 * Get the Value
	 * @return The Text Value
	 */
	public String getTextValue() {
		return getText();
	}
	
	/**
	 * @return whether this widget only needs a single line to display itself
	 */
	public boolean isSingleLine() {
		return true;
	}
	
	/**
	 * Add a DocumentListener to the Text Box
	 * @param dl
	 */
	public void addDocumentListener(DocumentListener dl) {
		getDocument().addDocumentListener(dl);
	}
	
	/**
	 * Remove a DocumentListener from the Text Box
	 * @param dl
	 */
	public void removeDocumentListener(DocumentListener dl) {
		getDocument().removeDocumentListener(dl);
	}
	
	/**
	 * Verify text as user types it - check for max length
	 * 
	 */
	class TextFieldVerifier extends PlainDocument {
		
		public void insertString(int offset, String str, AttributeSet attSet) throws BadLocationException {
			if(str == null) return;
			
			if(_doVerify) {
				boolean valid = isLengthOK(str);
				if(!valid) return;
				valid = isStringOK(str);
				if(!valid) return;
			}
			
			super.insertString(offset, str, attSet);
		}
		
		boolean isStringOK(String str) {
			return true;
		}
		
		boolean isLengthOK(String str) throws BadLocationException {
			// Check length
			if(_maxLength != -1) {
				String oldStr = getText(0, getLength());
				if(oldStr.length() + str.length() > _maxLength) return false;
			}
			
			return true;
		}
		
	}
}

