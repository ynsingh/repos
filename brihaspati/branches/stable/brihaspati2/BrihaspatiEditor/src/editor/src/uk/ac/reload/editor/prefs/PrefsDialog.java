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

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.*;

import uk.ac.reload.diva.prefs.UserPrefs;
import uk.ac.reload.dweezil.gui.ErrorDialogBox;
import uk.ac.reload.dweezil.util.DweezilUIManager;
import uk.ac.reload.editor.EditorFrame;
import uk.ac.reload.editor.IIcons;
import uk.ac.reload.editor.Messages;

/**
 * The Preferences Dialog Window
 *
 * @author Phillip Beauvoir
 * @version $Id: PrefsDialog.java,v 1.1 1998/03/26 15:24:12 ynsingh Exp $
 */
public class PrefsDialog
extends JDialog
implements IIcons
{
	
	/**
	 * The General Prefs Panel
	 */
	private General_PrefsPanel _generalPrefsPanel;
	
	/**
	 * The Content Packager Prefs Panel
	 */
	private CP_PrefsPanel _cpPrefsPanel;
	
	/**
	 * The Metadata Editor Prefs Panel
	 */
	private MD_PrefsPanel _mdPrefsPanel;
	
	/**
	 * The User Preferences
	 */
	private UserPrefs _userPrefs;
	
	/**
	 * The Look & Feel
	 */
	private AppearancePrefsPanel _appearancePrefsPanel;
	
	private static PrefsDialog _instance;
	
	/**
	 * @return Static instance
	 */
	public static PrefsDialog getSharedInstance() {
	    if(_instance == null) {
	        _instance = new PrefsDialog();
	    }
	    return _instance;
	}
	
	/**
	 * Constructor
	 */
	public PrefsDialog() {
		super(EditorFrame.getInstance(), Messages.getString("uk.ac.reload.editor.prefs.PrefsDialog.0"), true); //$NON-NLS-1$
		// Dialog Size
		setSize(580, 370);
		setLocationRelativeTo(EditorFrame.getInstance());
		setResizable(false);
		
		// Make some panels
		_generalPrefsPanel = new General_PrefsPanel();
		_cpPrefsPanel = new CP_PrefsPanel();
		_mdPrefsPanel = new MD_PrefsPanel();
		_appearancePrefsPanel = new AppearancePrefsPanel();
		
		// Get the User Prefs from file
		_userPrefs = EditorPrefs.getInstance();
		
		// Tabbed Pane
		JTabbedPane tabPane = new JTabbedPane();
		tabPane.addTab(Messages.getString("uk.ac.reload.editor.prefs.PrefsDialog.1"), DweezilUIManager.getIcon(ICON_APP16), _generalPrefsPanel); //$NON-NLS-1$
		tabPane.addTab(Messages.getString("uk.ac.reload.editor.prefs.PrefsDialog.2"), DweezilUIManager.getIcon(ICON_CP), _cpPrefsPanel); //$NON-NLS-1$
		tabPane.addTab(Messages.getString("uk.ac.reload.editor.prefs.PrefsDialog.3"), DweezilUIManager.getIcon(ICON_MD), _mdPrefsPanel); //$NON-NLS-1$
		tabPane.addTab(Messages.getString("uk.ac.reload.editor.prefs.PrefsDialog.4"), DweezilUIManager.getIcon(ICON_THREAD), _appearancePrefsPanel); //$NON-NLS-1$
		getContentPane().add(tabPane, BorderLayout.CENTER);
		
		// Button Panel
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		// OK Button
		JButton btnOK = new JButton(Messages.getString("uk.ac.reload.editor.prefs.PrefsDialog.5")); //$NON-NLS-1$
		btnOK.addActionListener(new OKClick());
		getRootPane().setDefaultButton(btnOK);
		buttonPanel.add(btnOK);
		
		// Cancel Buttton
		JButton btnCancel = new JButton(Messages.getString("uk.ac.reload.editor.prefs.PrefsDialog.6")); //$NON-NLS-1$
		btnCancel.addActionListener(new CancelClick());
		buttonPanel.add(btnCancel);
		
		// Add bottom Panel
		JPanel bottomPanel = new JPanel(new BorderLayout());
		bottomPanel.add(buttonPanel, BorderLayout.EAST);
		getContentPane().add(bottomPanel, BorderLayout.SOUTH);
	}
	
	/**
	 * Over-ride this so we can set up the panels
	 */
	public void show() {
		// Do this later because a modal dialog will block
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				// Update the Panels
				_generalPrefsPanel.setFields(_userPrefs);
				_cpPrefsPanel.setFields(_userPrefs);
				_mdPrefsPanel.setFields(_userPrefs);
				_appearancePrefsPanel.setFields(_userPrefs);
			}
		});
		super.show();
	}
	
	/**
	 * User pressed OK
	 */
	protected void finish() {
		// Update the User Prefs
		_generalPrefsPanel.saveToUserPrefs(_userPrefs);
		_cpPrefsPanel.saveToUserPrefs(_userPrefs);
		_mdPrefsPanel.saveToUserPrefs(_userPrefs);
		_appearancePrefsPanel.saveToUserPrefs(_userPrefs);
		
		try {
			_userPrefs.save();
		}
		catch(IOException ex) {
			ErrorDialogBox.showWarning(this,
			        Messages.getString("uk.ac.reload.editor.prefs.PrefsDialog.7") + ": ", //$NON-NLS-1$ //$NON-NLS-2$
			        Messages.getString("uk.ac.reload.editor.prefs.PrefsDialog.0"), //$NON-NLS-1$ 
			        ex); 
		}
		finally {
			dispose();
		}
	}
	
	/**
	 * OK handler
	 */
	private class OKClick extends AbstractAction {
		public void actionPerformed(ActionEvent e) {
			finish();
		}
	}
	
	/**
	 * Cancel handler
	 */
	private class CancelClick extends AbstractAction {
		public void actionPerformed(ActionEvent e) {
			// undo changes
			_generalPrefsPanel.cancel();
			_cpPrefsPanel.cancel();
			_mdPrefsPanel.cancel();
			_appearancePrefsPanel.cancel();
			
			dispose();
		}
	}
	
}