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

package uk.ac.reload.editor.contentpackaging.editor.resourceview;

import java.awt.Component;
import java.io.File;

import javax.swing.JCheckBox;
import javax.swing.JFileChooser;

import uk.ac.reload.dweezil.gui.DweezilFileChooser;
import uk.ac.reload.editor.Messages;

/**
 * An implementation of JFileChooser that includes an option box.
 *
 * @author Paul Sharples
 * @author Phillip Beauvoir
 * @version $Id: CP_ResourceChooser.java,v 1.1 1998/03/26 15:40:31 ynsingh Exp $
 */
public class CP_ResourceChooser
extends DweezilFileChooser {
	
	/**
	 * Variable to remember if the checkbox was last checked or not
	 */
    private static boolean _wasChecked = false;
	
	/**
	 * The checkbox to be included in the file chooser
	 */
    private JCheckBox _checkbox;
	
	/**
	 * Constuctor - sets up our checkbox
	 */
	public CP_ResourceChooser(){
		super();
		init();
	}
	
	/**
	 * Constructor with File parameter
	 * @param folder
	 */
	public CP_ResourceChooser(File folder) {
		super(folder);
		init();
	}
	
	/**
	 * Over-ride so we can store checkbox state
	 */
	public int showOpenDialog(Component parent) {
		int r = super.showOpenDialog(parent);
		_wasChecked = _checkbox.isSelected();
		return r;
	}
	
	/**
	 * Routine to pass in the question text to be shown along side
	 * the checkbox in the filechooser
	 */
	private void init() {
		setDialogTitle(Messages.getString("uk.ac.reload.editor.contentpackaging.resourceview.CP_ResourceChooser.0")); //$NON-NLS-1$
		setMultiSelectionEnabled(true);
		setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		_checkbox = new JCheckBox(Messages.getString("uk.ac.reload.editor.contentpackaging.resourceview.CP_ResourceChooser.1")); //$NON-NLS-1$
		_checkbox.setSelected(_wasChecked);
		setAccessory(_checkbox);
	}
	
	/**
	 * Accessor method to check the state of the option box
	 * @return
	 */
	public boolean isCheckboxChecked(){
		return _checkbox.isSelected();
	}
	
	/**
	 * Set the checkbox
	 */
	public void setCheckbox(boolean set){
		_checkbox.setSelected(set);
	}
	
}
