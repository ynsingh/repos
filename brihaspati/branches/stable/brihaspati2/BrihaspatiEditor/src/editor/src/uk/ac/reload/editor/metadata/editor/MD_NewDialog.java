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

package uk.ac.reload.editor.metadata.editor;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;

import javax.swing.*;

import uk.ac.reload.editor.EditorFrame;
import uk.ac.reload.editor.Messages;

/**
 * Ask user for Metadata version
 *
 * @author Phillip Beauvoir
 * @version $Id: MD_NewDialog.java,v 1.1 1998/03/26 15:32:14 ynsingh Exp $
 */
public class MD_NewDialog extends JDialog {
    
    /** 
     * Return value if OK is chosen.
     */
    public static final int OK_OPTION = 0;

    /**
     * Return value if CANCEL is chosen.
     */
    public static final int CANCEL_OPTION = -1;
    
    /**
     * The user response
     */
    private int _response = CANCEL_OPTION;
    
    /**
     * The Selection Combo 
     */
    private JComboBox _combo;

    /**
     * The "Don't Ask" Checkbox
     */
    private JCheckBox _checkbox;
    
	/**
	 * Constructor
	 */
	public MD_NewDialog(String[] versions, String defaultVersion) {
		super(EditorFrame.getInstance(), Messages.getString("uk.ac.reload.editor.metadata.MD_NewDialog.0"), true); //$NON-NLS-1$
		
		//setSize(400, 150);
		setResizable(false);
		
		JPanel mainPanel = createPanel(versions, defaultVersion);
		getContentPane().add(mainPanel, BorderLayout.CENTER);
		
		// Button Panel
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		// OK Button
		JButton btnOK = new JButton(Messages.getString("uk.ac.reload.editor.metadata.MD_NewDialog.1")); //$NON-NLS-1$
		btnOK.addActionListener(new OKClick());
		getRootPane().setDefaultButton(btnOK);
		buttonPanel.add(btnOK);
		
		// Cancel Buttton
		JButton btnCancel = new JButton(Messages.getString("uk.ac.reload.editor.metadata.MD_NewDialog.2")); //$NON-NLS-1$
		btnCancel.addActionListener(new CancelClick());
		buttonPanel.add(btnCancel);
		
		// Add bottom Panel
		JPanel bottomPanel = new JPanel(new BorderLayout());
		bottomPanel.add(buttonPanel, BorderLayout.EAST);
		getContentPane().add(bottomPanel, BorderLayout.SOUTH);
		
		pack();
		setLocationRelativeTo(EditorFrame.getInstance());
	}
	
	/**
	 * Show the dialog
	 * @return The user response either OK_OPTION or CANCEL_OPTION
	 */
	public int showDialog() {
	    show();
	    return _response;
	}
	
	/**
	 * @return The MD Version selected
	 */
	public String getVersion() {
	    return (String)_combo.getSelectedItem();
	}
	
	/**
	 * @return True if user doesn't want this dialog everytime 
	 */
	public boolean getDontAsk() {
	    return _checkbox.isSelected();
	}
	
	/**
	 * @return A JPanel with the UI
	 */
	protected JPanel createPanel(String[] versions, String defaultVersion) {
	    JPanel panel = new JPanel();
	    panel.setBorder(BorderFactory.createEmptyBorder(10, 20, 5, 20));
	    GridBagLayout layout = new GridBagLayout();
	    panel.setLayout(layout);
	    
	    GridBagConstraints gc = new GridBagConstraints();
	    gc.fill = GridBagConstraints.HORIZONTAL;
	    gc.insets = new Insets(0, 0, 5, 0);

	    JLabel label = new JLabel(Messages.getString("uk.ac.reload.editor.metadata.MD_NewDialog.3") + ": "); //$NON-NLS-1$ //$NON-NLS-2$
	    gc.weightx = 0.8;
	    gc.gridx = 0;
	    gc.gridy = 0;
	    panel.add(label, gc);
	    
	    _combo = new JComboBox(versions);
	    if(defaultVersion != null) _combo.setSelectedItem(defaultVersion);
        gc.gridx = 1;
	    panel.add(_combo, gc);
	    
	    _checkbox = new JCheckBox(Messages.getString("uk.ac.reload.editor.metadata.MD_NewDialog.4")); //$NON-NLS-1$
        gc.weightx = 1.0;
	    gc.gridx = 0;
	    gc.gridy = 1;
	    gc.gridwidth = 2;
	    panel.add(_checkbox, gc);
	    
	    return panel;
	}
	
	/**
	 * OK handler
	 */
	private class OKClick extends AbstractAction {
		public void actionPerformed(ActionEvent e) {
		    _response = OK_OPTION;
		    dispose();
		}
	}
	
	/**
	 * Cancel handler
	 */
	private class CancelClick extends AbstractAction {
		public void actionPerformed(ActionEvent e) {
		    _response = CANCEL_OPTION;
		    dispose();
		}
	}
}
