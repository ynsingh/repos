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

package uk.ac.reload.editor.contentpackaging.editor;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.File;

import javax.swing.*;

import uk.ac.reload.dweezil.gui.DweezilFolderChooser;
import uk.ac.reload.editor.EditorFrame;
import uk.ac.reload.editor.Messages;

/**
 * Ask user for CP Folder and (optional) versions
 *
 * @author Phillip Beauvoir
 * @version $Id: CP_NewDialog.java,v 1.1 1998/03/26 15:32:14 ynsingh Exp $
 */
public class CP_NewDialog {
    
    /**
     * The File Chooser
     */
    private DweezilFolderChooser _chooser;
    
    /**
     * The CP Selection Combo 
     */
    private JComboBox _comboCP;

    /**
     * The MD Selection Combo 
     */
    private JComboBox _comboMD;
    
    /**
     * The Accessory panel
     */
    private JPanel _accessoryPanel;

    
	/**
	 * Constructor with option to choose versions
	 * 
	 * @param versionsCP The available CP versions
	 * @param defaultVersionCP The default CP version
	 * @param versionsMD The available MD versions
	 * @param defaultVersionMD The default MD version
	 */
	public CP_NewDialog(String[] versionsCP, String defaultVersionCP, String[] versionsMD, String defaultVersionMD) {
	    _chooser = new DweezilFolderChooser();
	    _chooser.setDialogTitle(Messages.getString("uk.ac.reload.editor.contentpackaging.CP_NewDialog.0")); //$NON-NLS-1$
		
		createAccessoryPanel(versionsCP, defaultVersionCP, versionsMD, defaultVersionMD);
		
		_chooser.setPreferredSize(new Dimension(600, _chooser.getPreferredSize().height));
	}
	
	/**
	 * Constructor for choosing folder, no accessories
	 * 
	 * @param title The Titlebar title
	 */
	public CP_NewDialog(String title) {
	    _chooser = new DweezilFolderChooser();
	    _chooser.setDialogTitle(title);
	}
	
	/**
	 * Show the dialog
     * @return the Folder chosen or null if the user cancelled
	 */
	public File showDialog() {
		// Ask for the File Name
		int returnVal = _chooser.showOpenDialog(EditorFrame.getInstance());
		
		// User Cancelled
		if(returnVal != DweezilFolderChooser.APPROVE_OPTION) {
		    return null;
		}
		
		// Get the chosen File
		File file = _chooser.getSelectedFileAndStore();
		
		// Create if it doesn't exist
		file.mkdirs();
		
		// Sanity check
		if(!file.exists() || !file.isDirectory()) {
			JOptionPane.showMessageDialog(EditorFrame.getInstance(),
					Messages.getString("uk.ac.reload.editor.contentpackaging.CP_NewDialog.1"), //$NON-NLS-1$
					Messages.getString("uk.ac.reload.editor.contentpackaging.CP_NewDialog.2"), //$NON-NLS-1$
					JOptionPane.WARNING_MESSAGE);
			return null;
		}
		
		// If there is already an imsmanifest.xml file warn the user
		File imsManifest = new File(file, "imsmanifest.xml"); //$NON-NLS-1$
		if(imsManifest.exists()) {
			JOptionPane.showMessageDialog(EditorFrame.getInstance(),
					Messages.getString("uk.ac.reload.editor.contentpackaging.CP_NewDialog.3"), //$NON-NLS-1$
					Messages.getString("uk.ac.reload.editor.contentpackaging.CP_NewDialog.2"), //$NON-NLS-1$
					JOptionPane.WARNING_MESSAGE);
			return null;
		}
		
		return file;
	}
	
	/**
	 * @return The Accessory Panel.  It has a BoxLayout.
	 */
	protected JPanel getAccessoryPanel() {
	    if(_accessoryPanel == null) {
	        _accessoryPanel = new JPanel();
	        _accessoryPanel.setLayout(new BoxLayout(_accessoryPanel, BoxLayout.Y_AXIS));
	        _chooser.setAccessory(_accessoryPanel);
	    }
	    return _accessoryPanel;
	}
	
	/**
	 * @return The MD Version selected
	 */
	public String getVersionMD() {
	    return _comboMD == null ? null : (String)_comboMD.getSelectedItem();
	}
	
	/**
	 * @return The CP Version selected
	 */
	public String getVersionCP() {
	    return _comboCP == null ? null : (String)_comboCP.getSelectedItem();
	}

	/**
	 * @return The Folder Chooser
	 */
	public DweezilFolderChooser getFolderChooser() {
	    return _chooser;
	}
	
	/**
	 * Create the Accessory Panel
	 * @return A JPanel with the UI
	 */
	protected void createAccessoryPanel(String[] versionsCP, String defaultVersionCP, String[] versionsMD, String defaultVersionMD) {
	    JPanel subPanel = new JPanel();
	    getAccessoryPanel().add(subPanel);
	    
	    subPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
	    GridBagLayout layout = new GridBagLayout();
	    subPanel.setLayout(layout);
	    
	    GridBagConstraints gc = new GridBagConstraints();
	    gc.fill = GridBagConstraints.HORIZONTAL;
	    gc.insets = new Insets(0, 0, 5, 0);
	    
	    JLabel label = new JLabel(Messages.getString("uk.ac.reload.editor.contentpackaging.CP_NewDialog.4") + ": "); //$NON-NLS-1$ //$NON-NLS-2$
	    gc.weightx = 0.8;
	    gc.gridx = 0;
	    gc.gridy = 0;
	    subPanel.add(label, gc);
	    
	    _comboCP = new JComboBox(versionsCP);
	    if(defaultVersionCP != null) {
	        _comboCP.setSelectedItem(defaultVersionCP);
	    }
        gc.gridx = 1;
        subPanel.add(_comboCP, gc);
	    
	    label = new JLabel(Messages.getString("uk.ac.reload.editor.contentpackaging.CP_NewDialog.5") + ": "); //$NON-NLS-1$ //$NON-NLS-2$
	    gc.weightx = 0.8;
	    gc.gridx = 0;
	    gc.gridy = 1;
	    subPanel.add(label, gc);
	    
	    _comboMD = new JComboBox(versionsMD);
	    if(defaultVersionMD != null) {
	        _comboMD.setSelectedItem(defaultVersionMD);
	    }
        gc.gridx = 1;
        subPanel.add(_comboMD, gc);
	}
}
