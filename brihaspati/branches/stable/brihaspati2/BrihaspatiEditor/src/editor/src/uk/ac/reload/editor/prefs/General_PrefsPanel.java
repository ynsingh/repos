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

import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import uk.ac.reload.diva.prefs.UserPrefs;
import uk.ac.reload.diva.util.CopyTask;
import uk.ac.reload.dweezil.gui.FileTextField;
import uk.ac.reload.dweezil.gui.layout.XYConstraints;
import uk.ac.reload.dweezil.gui.layout.XYLayout;
import uk.ac.reload.dweezil.prefs.PrefsPanel;
import uk.ac.reload.dweezil.util.DweezilUIManager;
import uk.ac.reload.editor.EditorFrame;
import uk.ac.reload.editor.IIcons;
import uk.ac.reload.editor.Messages;



/**
 * A General Settings User Prefs Panel
 *
 * @author Phillip Beauvoir
 * @author Julian Wood
 * @version $Id: General_PrefsPanel.java,v 1.1 1998/03/26 15:24:12 ynsingh Exp $
 */
public class General_PrefsPanel
extends PrefsPanel
implements IIcons
{
    /**
     * Default folder
     */
    private FileTextField _tfDefaultFolder;
    
    /**
     * Checkbox for repairing support jar
     */
    private JCheckBox _checkSupportBox;

    /**
     * Constructor
     */
    public General_PrefsPanel() {
        setLayout(new XYLayout());
        constructFields();
    }

    /**
     * Set up the fields
     */
    private void constructFields() {
        int x = 10;
        int y = 10;

        // ============ Default Open Folder ==================
        JLabel label = new JLabel(Messages.getString("uk.ac.reload.editor.prefs.General_PrefsPanel.0")); //$NON-NLS-1$
        add(label, new XYConstraints(x, y + 2, 0, 0));
        _tfDefaultFolder = new FileTextField(FileTextField.FOLDER_TYPE, Messages.getString("uk.ac.reload.editor.prefs.General_PrefsPanel.1"), DweezilUIManager.getIcon(ICON_OPEN)); //$NON-NLS-1$
        add(_tfDefaultFolder, new XYConstraints(x + 230, y, 300, TEXTBOX_HEIGHT));

        _checkSupportBox = new JCheckBox(Messages.getString("uk.ac.reload.editor.prefs.General_PrefsPanel.2")); //$NON-NLS-1$
        
        JButton repairButton = new JButton(new AbstractAction(Messages.getString("uk.ac.reload.editor.prefs.General_PrefsPanel.3")) { //$NON-NLS-1$
            public void actionPerformed(ActionEvent e) {
                try {
                    checkSupportFolder();
                    JOptionPane.showMessageDialog(General_PrefsPanel.this,
                            Messages.getString("uk.ac.reload.editor.prefs.General_PrefsPanel.4") //$NON-NLS-1$
                    );
                } catch (IOException e1) {
                    JOptionPane.showMessageDialog(General_PrefsPanel.this,
                            Messages.getString("uk.ac.reload.editor.prefs.General_PrefsPanel.5") + ": " + e1.getMessage()); //$NON-NLS-1$ //$NON-NLS-2$
                }
            }
        });
        
        add(_checkSupportBox, new XYConstraints(x, y + 30, 0, 0));
        add(repairButton, new XYConstraints(x + 230, y + 27, 0, 0));
    }

    /**
     * Update UserPrefs according to the controls' settings
     */
    public void saveToUserPrefs(UserPrefs prefs) {
        prefs.putValue(EditorPrefs.GENERAL_PREFS_DEFAULT_FOLDER, _tfDefaultFolder.getTextValue());
        prefs.putBooleanValue(EditorPrefs.GENERAL_CHECK_SUPPORT, _checkSupportBox.isSelected());
    }

    /**
     * Set the fields' settings from those found in UserPrefs
     */
    public void setFields(UserPrefs prefs) {
        // Set value in field
        _tfDefaultFolder.setTextValue(prefs.getValue(EditorPrefs.GENERAL_PREFS_DEFAULT_FOLDER));
        _checkSupportBox.setSelected(prefs.getBooleanValue(EditorPrefs.GENERAL_CHECK_SUPPORT));
    }

    /* 
     * Cancel any changes
     */
    public void cancel() {
    	
    }

    /**
     * Copy any necessary files from the reload-support.jar into the local support folder
     * @throws IOException
     */
    private void checkSupportFolder() throws IOException {
        try {
            // Local copy of jar whilst developing
            new CopyTask(EditorFrame.SUPPORT_JAR_LOCATION, EditorPrefs.getInstance().getPrefsFolder()).execute(true);
        } catch (IOException e) {
            // Copy from jar in classpath
            new CopyTask(EditorFrame.SUPPORT_JAR_RESOURCE, EditorPrefs.getInstance().getPrefsFolder()).execute(true);
        }
    }

}
