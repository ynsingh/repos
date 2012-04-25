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

package uk.ac.reload.editor.prefs;

import javax.swing.JCheckBox;
import javax.swing.JLabel;

import uk.ac.reload.diva.prefs.UserPrefs;
import uk.ac.reload.dweezil.gui.DweezilComboBox;
import uk.ac.reload.dweezil.gui.layout.XYConstraints;
import uk.ac.reload.dweezil.gui.layout.XYLayout;
import uk.ac.reload.dweezil.prefs.PrefsPanel;
import uk.ac.reload.editor.EditorHandler;
import uk.ac.reload.editor.Messages;
import uk.ac.reload.editor.metadata.MD_EditorHandler;
import uk.ac.reload.moonunit.HelperProfile;



/**
 * A Metadata Settings User Prefs Panel
 *
 * @author Phillip Beauvoir
 * @version $Id: MD_PrefsPanel.java,v 1.1 1998/03/26 15:24:12 ynsingh Exp $
 */
public class MD_PrefsPanel
extends PrefsPanel
{
	
	/**
	 * Combo Box for Default MD Profile
	 */
    private DweezilComboBox _profileCombobox;
	
	/**
	 * Combo Box for Default MD Version
	 */
    private DweezilComboBox _versionCombobox;

    /**
     * Ask for version
     */
    private JCheckBox _askversionCheckBox;
    
    
	/**
	 * Constructor
	 */
	public MD_PrefsPanel() {
		setLayout(new XYLayout());
		constructFields();
	}
	
	/**
	 * Set up the fields
	 */
	private void constructFields() {
		int x = 10;
		int y = 10;
		
		// ============ Default Metadata Profile ==================
		JLabel label = new JLabel(Messages.getString("uk.ac.reload.editor.prefs.MD_PrefsPanel.0")); //$NON-NLS-1$
		add(label, new XYConstraints(x, y + 2, 0, 0));
		
		_profileCombobox = new DweezilComboBox(false);
		add(_profileCombobox, new XYConstraints(x += 230, y, 200, COMBOBOX_HEIGHT));
		
		y += 30;
		
		// ============ Default Metadata Version ==================
		x = 10;
		
		label = new JLabel(Messages.getString("uk.ac.reload.editor.prefs.MD_PrefsPanel.1")); //$NON-NLS-1$
		add(label, new XYConstraints(x, y + 2, 0, 0));
		
		_versionCombobox = new DweezilComboBox(false);
		add(_versionCombobox, new XYConstraints(x += 230, y, 200, COMBOBOX_HEIGHT));
		
	    y += 30;
	    
		// ============ Prompt for Metadata Version ==================
		x = 5;
	    
		_askversionCheckBox = new JCheckBox(Messages.getString("uk.ac.reload.editor.prefs.MD_PrefsPanel.2")); //$NON-NLS-1$
        add(_askversionCheckBox, new XYConstraints(x, y, 0, CHECKBOX_HEIGHT));
	}
	
	/**
	 * Update UserPrefs according to the controls' settings
	 */
	public void saveToUserPrefs(UserPrefs prefs) {
		// Default MD Profile
	    String profile = (String)_profileCombobox.getSelectedItem();
		if(profile != null) {
		    prefs.putValue(EditorPrefs.MD_DEFAULT_PROFILE, profile);
		}
		
		// Default MD Version
	    String version = (String)_versionCombobox.getSelectedItem();
		if(version != null) {
		    prefs.putValue(EditorPrefs.MD_DEFAULT_VERSION, version);
		}
		
		// Prompt for Metadata Version
		prefs.putBooleanValue(EditorPrefs.MD_DO_ASK_VERSION, _askversionCheckBox.isSelected());
	}
	
	/**
	 * Set the controls' settings from those found in UserPrefs
	 */
	public void setFields(UserPrefs prefs) {
	    // Default MD Profile
		String[] profiles = HelperProfile.getHelperProfileNames(MD_EditorHandler.PROFILE_FOLDER);
		_profileCombobox.setItems(profiles);

		String profile = prefs.getValue(EditorPrefs.MD_DEFAULT_PROFILE);
		if(profile != null) {
		    _profileCombobox.setSelectedItem(profile);
		}

		// Default MD Version
		String[] versions = EditorHandler.MD_EDITORHANDLER.getSupportedVersions();
	    _versionCombobox.setItems(versions);

	    String version = prefs.getValue(EditorPrefs.MD_DEFAULT_VERSION);
		if(version != null) {
		    _versionCombobox.setSelectedItem(version);
		}

		// Prompt for Metadata Version
		_askversionCheckBox.setSelected(prefs.getBooleanValue(EditorPrefs.MD_DO_ASK_VERSION));
	}
	
	/* 
	 * Cancel any changes
	 */
	public void cancel() {
		
	}
}
