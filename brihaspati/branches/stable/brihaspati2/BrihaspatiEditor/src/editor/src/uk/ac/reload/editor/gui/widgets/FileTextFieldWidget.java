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

import javax.swing.Icon;
import javax.swing.event.DocumentListener;

import uk.ac.reload.dweezil.gui.FileTextField;

/**
 * A Combination of JTextField and File Chooser for selecting files or folders.<br>
 * <br>
 * A filechooser will be launched when the button is clicked and the result of the chosen
 * file or folder will be set in the Text Field.
 *
 * @author Phillip Beauvoir
 * @version $Id: FileTextFieldWidget.java,v 1.1 1998/03/26 15:32:14 ynsingh Exp $
 */
public class FileTextFieldWidget
extends FileTextField
implements Widget
{
	/**
	 * The maximum number of characters allowed
	 */
    private int _maxLength;
	
	/**
	 * Constructor
	 * @param type The file type to select - This can be FileTextFieldWidget.FOLDER_TYPE to select
	 * folders or FileTextFieldWidget.FILE_TYPE to select files
	 * @param text The Text to display in the the File Chooser
	 * @param icon The Icon to display on the JButton.  If this is null then "..." will be displayed.
	 */
	public FileTextFieldWidget(int type, String text, Icon icon) {
		super(type, text, icon);
	}

	/**
	 * Set the Value
	 * @param value
	 */
	public void setTextValue(String value) {
		super.setTextValue(value);
	}
	
	/**
	 * Insert some text
	 * @param text the Text to insert
	 */
	public void insertTextValue(String text) {
		// Do something
	}
	
	/**
	 * @return the text value
	 */
	public String getTextValue() {
		return super.getTextValue();
	}
	
	/**
	 * Set the maximum allowed of digits
	 * @param maxLength the max length
	 */
	public void setMaxLength(int maxLength) {
		_maxLength = maxLength;
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
		getTextField().getDocument().addDocumentListener(dl);
	}
	
	/**
	 * Remove a DocumentListener from the Text Box
	 * @param dl
	 */
	public void removeDocumentListener(DocumentListener dl) {
		getTextField().getDocument().removeDocumentListener(dl);
	}
}