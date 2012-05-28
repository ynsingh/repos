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

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import uk.ac.reload.dweezil.gui.FileTextField;
import uk.ac.reload.dweezil.util.DweezilUIManager;
import uk.ac.reload.dweezil.util.NativeLauncher;
import uk.ac.reload.editor.EditorFrame;
import uk.ac.reload.editor.IIcons;

/**
 * A Text Field used in the Learning Design Editor for selecting Files and Folders,
 * and also launching the file natively<p>
 *
 * @author Phillip Beauvoir
 * @version $Id: FileSelectorTextField.java,v 1.1 1998/03/26 15:32:14 ynsingh Exp $
 */
public class FileSelectorTextField
extends FileTextField
implements IIcons
{

    /**
     * The Text Field
     */
    private DataElementTextField _textField;
    
    /**
     * The View File Button
     */
    private JButton _buttonFileView;
    
    /**
     * Constructor
     * @param type The file type to select - This can be FileTextFieldWidget.FOLDER_TYPE to select
     * folders or FileTextFieldWidget.FILE_TYPE to select files
     * @param text The Text to display in the the File Chooser
     * @param icon The Icon to display on the JButton
     */
	public FileSelectorTextField(int type, String text, Icon icon) {
		super(type, text, icon);
	}

	/**
     * Over-ride to add a View button.
     */
	protected void setup() {
	    // Layout
	    setLayout(new BorderLayout());
	    
		// Add text field
		add(getTextField(), BorderLayout.CENTER);
		
		// Button Panel
		JPanel subPanel = new JPanel(new GridLayout());
		subPanel.add(getFileOpenButton());
		subPanel.add(getFileViewButton());
		add(subPanel, BorderLayout.EAST);

		// Set up the file open button's Action behaviour
		setupFileOpenAction();

		// Set up the view file button's Action behaviour
		setupFileViewAction();
	}

	/**
     * Set up the view file button's Action behaviour
     */
    protected void setupFileViewAction() {
		if(getFileViewButton() != null) {
		    getFileViewButton().addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				    String text = getTextValue();
				    if("".equals(text) == false) {
				        File file = new File(text);
				        if(file.exists()) {
				            NativeLauncher.launchFile(file);
				        }
				        else {
				            JOptionPane.showMessageDialog(EditorFrame.getInstance(),
				    				text + " does not exist!",
				    				"Open File",
				    				JOptionPane.WARNING_MESSAGE);
				        }
				    }
				}
			});
		}
    }

    /**
	 * @return the JButton for the "View File" Action
	 * This can be over-ridden by sub-classes to offer an alternative button.
	 */
	public JButton getFileViewButton() {
		if(_buttonFileView == null) {
			Icon icon = DweezilUIManager.getIcon(ICON_VIEWFILE);
		    _buttonFileView = new JButton(icon);
		    _buttonFileView.setPreferredSize(new Dimension(24, 0));
		}
		return _buttonFileView;
	}

	/**
     * Over-ride to return a LD_TextField
     * @return the JTextField.
     */
    public JTextField getTextField() {
    	if(_textField == null) {
    		_textField = new DataElementTextField();
    	}
    	return _textField;
    }

    /**
     * @return the JButton for 
     */
    public JButton getFileOpenButton() {
    	JButton b = super.getFileOpenButton();
    	b.setPreferredSize(new Dimension(24, 0));
    	return b;
    }
}