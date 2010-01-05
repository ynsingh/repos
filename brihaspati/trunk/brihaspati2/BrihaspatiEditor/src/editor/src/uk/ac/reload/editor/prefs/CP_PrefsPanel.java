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
import uk.ac.reload.editor.Messages;
import uk.ac.reload.editor.contentpackaging.CP_EditorHandler;
import uk.ac.reload.editor.scorm.SCORM12_EditorHandler;
import uk.ac.reload.moonunit.HelperProfile;



/**
 * A Content Packaging Settings User Prefs Panel
 *
 * @author Phillip Beauvoir
 * @version $Id: CP_PrefsPanel.java,v 1.1 1998/03/26 15:24:12 ynsingh Exp $
 */
public class CP_PrefsPanel
extends PrefsPanel
{
    /**
     * Hide Resources check box
     */
    private JCheckBox _checkbox_hideResources;

    /**
     * Hide SCORM Elements check box
     */
    private JCheckBox _checkbox_hideSCORM;

    /**
     * Only zip referenced files check box
     */
    //private JCheckBox _checkbox_onlyZipReferencedFiles;
    
    /**
     * Combo Box for Default CP Profile
     */
    private DweezilComboBox _profileCPCombobox;

    /**
     * Combo Box for Default SCORM Profile
     */
    private DweezilComboBox _profileSCORMCombobox;

    /**
     * Constructor
     */
    public CP_PrefsPanel() {
        setLayout(new XYLayout());
        constructFields();
    }

    /**
     * Set up the fields
     */
    private void constructFields() {
        int x = 5;
        int y = 10;

        // ============ Hide Resources ==================
        _checkbox_hideResources = new JCheckBox(Messages.getString("uk.ac.reload.editor.prefs.CP_PrefsPanel.0")); //$NON-NLS-1$
        add(_checkbox_hideResources, new XYConstraints(x, y, 0, CHECKBOX_HEIGHT));

        y += 20;

        // ============ Hide SCORM ==================
        _checkbox_hideSCORM = new JCheckBox(Messages.getString("uk.ac.reload.editor.prefs.CP_PrefsPanel.1")); //$NON-NLS-1$
        add(_checkbox_hideSCORM, new XYConstraints(x, y, 0, CHECKBOX_HEIGHT));

       // y += 20;

		// ======= zip referenced files ==================
       // _checkbox_onlyZipReferencedFiles = new JCheckBox("Zip referenced files only (effective the next time you open a Manifest)");
       // add(_checkbox_onlyZipReferencedFiles, new XYConstraints(x, y, 0, CHECKBOX_HEIGHT));

        y += 30;
        
        // ============ Default CP Profile ==================

        x = 10;

        JLabel label = new JLabel(Messages.getString("uk.ac.reload.editor.prefs.CP_PrefsPanel.2")); //$NON-NLS-1$
        add(label, new XYConstraints(x, y + 2, 0, 0));

        _profileCPCombobox = new DweezilComboBox(false);
        add(_profileCPCombobox, new XYConstraints(x += 230, y, 200, COMBOBOX_HEIGHT));

        y += 30;

        // ============ Default SCORM Profile ==================

        x = 10;

        label = new JLabel(Messages.getString("uk.ac.reload.editor.prefs.CP_PrefsPanel.3")); //$NON-NLS-1$
        add(label, new XYConstraints(x, y + 2, 0, 0));

        _profileSCORMCombobox = new DweezilComboBox(false);
        add(_profileSCORMCombobox, new XYConstraints(x += 230, y, 200, COMBOBOX_HEIGHT));

        y += 20;
    }

    /**
     * Update UserPrefs according to the controls' settings
     */
    public void saveToUserPrefs(UserPrefs prefs) {
        prefs.putBooleanValue(EditorPrefs.CP_HIDE_RESOURCES, _checkbox_hideResources.isSelected());
        prefs.putBooleanValue(EditorPrefs.CP_HIDE_SCORM, _checkbox_hideSCORM.isSelected());
		//prefs.putBooleanValue(EditorPrefs.CP_ZIP_REFERENCED_FILES, _checkbox_onlyZipReferencedFiles.isSelected());
        
        // Default CP Profile
        String cp_profile = (String)_profileCPCombobox.getSelectedItem();
		if(cp_profile != null) {
		    prefs.putValue(EditorPrefs.CP_DEFAULT_PROFILE, cp_profile);
		}

		// Default SCORM Profile
		String scorm_profile = (String)_profileSCORMCombobox.getSelectedItem();
		if(scorm_profile != null) {
		    prefs.putValue(EditorPrefs.SCORM_DEFAULT_PROFILE, scorm_profile);
		}
    }

    /**
     * Set the controls' settings from those found in UserPrefs
     */
    public void setFields(UserPrefs prefs) {
        _checkbox_hideResources.setSelected(prefs.getBooleanValue(EditorPrefs.CP_HIDE_RESOURCES));
        _checkbox_hideSCORM.setSelected(prefs.getBooleanValue(EditorPrefs.CP_HIDE_SCORM));
		//_checkbox_onlyZipReferencedFiles.setSelected(prefs.getBooleanValue(EditorPrefs.CP_ZIP_REFERENCED_FILES));
        
        // Default CP Profile
        String[] cp_profiles = HelperProfile.getHelperProfileNames(CP_EditorHandler.PROFILE_FOLDER);
        _profileCPCombobox.setItems(cp_profiles);

        String profile = prefs.getValue(EditorPrefs.CP_DEFAULT_PROFILE);
        if(profile != null) {
            _profileCPCombobox.setSelectedItem(profile);
        }

        // Default SCORM Profile
        String[] scorm_profiles = HelperProfile.getHelperProfileNames(SCORM12_EditorHandler.PROFILE_FOLDER);
        _profileSCORMCombobox.setItems(scorm_profiles);

        profile = prefs.getValue(EditorPrefs.SCORM_DEFAULT_PROFILE);
        if(profile != null)  {
            _profileSCORMCombobox.setSelectedItem(profile);
        }
    }

    /* 
     * Cancel any changes
     */
    public void cancel() {
    	
    }
}
