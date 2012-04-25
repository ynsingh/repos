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

package uk.ac.reload.editor.learningdesign.editor;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import uk.ac.reload.editor.contentpackaging.editor.CP_NewDialog;

/**
 * Ask user for LD Folder and (optional) versions
 *
 * @author Phillip Beauvoir
 * @version $Id: LD_NewDialog.java,v 1.1 1998/03/26 15:32:14 ynsingh Exp $
 */
public class LD_NewDialog
extends CP_NewDialog
{
    /**
     * The Level Selection Combo 
     */
    private JComboBox _comboLevel;
    
    
	/**
	 * Constructor for choosing folder and version
	 * @param title The Titlebar title
	 */
	public LD_NewDialog(String title) {
	    super(title);
	    
	    createAccessoryPanel();
	}

    /**
     * Create the accessory panel for the version chooser
     */
    private void createAccessoryPanel() {
        JPanel subPanel = new JPanel();
	    subPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
	    subPanel.setLayout(new GridBagLayout());
	    getAccessoryPanel().add(subPanel);
	    
	    GridBagConstraints gc = new GridBagConstraints();
	    gc.fill = GridBagConstraints.HORIZONTAL;
	    gc.insets = new Insets(0, 0, 5, 0);
	    
	    JLabel label = new JLabel("LD Level" + ": ");
	    gc.weightx = 1;
	    gc.gridx = 0;
	    gc.gridy = 0;
	    subPanel.add(label, gc);
	    
	    _comboLevel = new JComboBox(new String[] {"A", "B", "C"});
	    
	    ///// TEMP
	    _comboLevel.setEnabled(false);
	    ///// TEMP
	    
        gc.gridx = 1;
        subPanel.add(_comboLevel, gc);   
    }
	
	/**
	 * @return The LD Level selected - "A", "B", or "C"
	 */
	public String getLevelLD() {
	    return _comboLevel == null ? null : (String)_comboLevel.getSelectedItem();
	}
	
}
