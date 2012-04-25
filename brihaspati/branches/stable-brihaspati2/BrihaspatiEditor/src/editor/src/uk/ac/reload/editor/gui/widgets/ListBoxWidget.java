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

import java.awt.BorderLayout;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * A Combination of TextFieldWidget and JList
 *
 * @author Phillip Beauvoir
 * @version $Id: ListBoxWidget.java,v 1.1 1998/03/26 15:32:14 ynsingh Exp $
 */
public class ListBoxWidget
extends JPanel
implements Widget, ListSelectionListener
{
	/**
	 * The maximum number of characters allowed
	 */
    private int _maxLength;
	
	/**
	 * A Textfield to display the value
	 */
    private TextFieldWidget _textField;
	
	/**
	 * A list box to display the list
	 */
    private JList _listBox;
	
	/**
	 * Constructor
	 */
	public ListBoxWidget() {
		super(new BorderLayout());
		
		// Add text field
		_textField = new TextFieldWidget();
		add(_textField, BorderLayout.NORTH);
		
		// Add List
		_listBox = new JList();
		_listBox.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		add(new JScrollPane(_listBox), BorderLayout.CENTER);
		
		// Listen to List Selections
		_listBox.addListSelectionListener(this);
	}
	
	/**
	 * @return The Text Field Widget
	 */
	public TextFieldWidget getTextFieldWidget() {
		return _textField;
	}
	
	/**
	 * @return The List Box
	 */
	public JList getListBox() {
		return _listBox;
	}
	
	/**
	 * Set the Lists Data
	 * @param values
	 */
	public void setListData(Object[] values) {
		_listBox.setListData(values);
	}
	
	/**
	 * Set the Value
	 * @param value
	 */
	public void setTextValue(String value) {
		_listBox.setSelectedValue(value, true);
		_textField.setText(value);
		//textField.requestFocus();
	}
	
	/**
	 * Insert some text
	 */
	public void insertTextValue(String text) {
		
	}
	
	/**
	 * @return The Text Value
	 */
	public String getTextValue() {
		return _textField.getText();
	}
	
	/**
	 * Set the maximum allowed of digits
	 * @param maxLength
	 */
	public void setMaxLength(int maxLength) {
		_maxLength = maxLength;
		_textField.setMaxLength(maxLength);
	}
	
	/**
	 * @return Whether this widget only needs a single line to display itself
	 */
	public boolean isSingleLine() {
		return false;
	}
	
	/**
	 * Add a DocumentListener to the Text Box
	 * @param dl
	 */
	public void addDocumentListener(DocumentListener dl) {
		_textField.addDocumentListener(dl);
	}
	
	/**
	 * Remove a DocumentListener from the Text Box
	 * @param dl
	 */
	public void removeDocumentListener(DocumentListener dl) {
		_textField.removeDocumentListener(dl);
	}
	
	/**
	 * Set the components enabled/disabled
	 * @param enabled
	 */
	public void setEnabled(boolean enabled) {
		_textField.setEnabled(enabled);
		_listBox.setEnabled(enabled);
	}
	
	/**
	 * ListSelection Listener for the Listbox to update the Text Field
	 */
	public void valueChanged(ListSelectionEvent e) {
		if(e.getSource() == _listBox) {
			Object object = _listBox.getSelectedValue();
			if(object != null) _textField.setText(object.toString());
		}
	}
	
}