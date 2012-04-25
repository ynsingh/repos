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

import java.awt.KeyboardFocusManager;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JTextPane;
import javax.swing.KeyStroke;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;

import uk.ac.reload.dweezil.util.DweezilUIManager;

/**
 * A TextPane
 *
 * @author Phillip Beauvoir
 * @version $Id: TextPaneWidget.java,v 1.1 1998/03/26 15:32:14 ynsingh Exp $
 */
public class TextPaneWidget
extends JTextPane
implements Widget
{
	/**
	 * The maximum number of characters allowed
	 */
    private int _maxLength;
	
	/**
	 * Constructor
	 */
	public TextPaneWidget() {
		setTabKeyBehaviour();
		setUI();
	}
	
	/**
	 * Set the Value
	 * @param value
	 */
	public void setTextValue(String value) {
		setText(value);
		setCaretPosition(0);
	}
	
	/**
	 * Insert some text
	 */
	public void insertTextValue(String text) {
		try {
			getDocument().insertString(getCaretPosition(), text, null);
		}
		catch(BadLocationException ex) {
		}
	}
	
	/**
	 * Get the Value
	 * @return The Text Value
	 */
	public String getTextValue() {
		return getText();
	}
	
	/**
	 * Set the maximum allowed of digits
	 * @param maxLength
	 */
	public void setMaxLength(int maxLength) {
		_maxLength = maxLength;
	}
	
	/**
	 * @return whether this widget only needs a single line to display itself
	 */
	public boolean isSingleLine() {
		return false;
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
	 * Set the UI
	 */
	protected void setUI() {
		setFont(DweezilUIManager.plainFont11);
	}
	
	/**
	 * Update the UI
	 */
	public void updateUI() {
		super.updateUI();
		setUI();
	}
	
	/**
	 * Make sure we can use the Tab key for traversal
	 */
	protected void setTabKeyBehaviour() {
		Set forwardTraversalKeys = new HashSet();
		forwardTraversalKeys.add(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0));
		forwardTraversalKeys.add(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, InputEvent.CTRL_MASK));
		setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, forwardTraversalKeys);
		Set backwardTraversalKeys = new HashSet();
		backwardTraversalKeys.add(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, InputEvent.SHIFT_MASK));
		backwardTraversalKeys.add(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, InputEvent.SHIFT_MASK | InputEvent.CTRL_MASK));
		setFocusTraversalKeys(KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS, backwardTraversalKeys);
	}
}