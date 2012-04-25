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

package uk.ac.reload.editor;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import uk.ac.reload.dweezil.gui.DweezilFileChooser;
import uk.ac.reload.dweezil.gui.DweezilToolBar;
import uk.ac.reload.dweezil.menu.MenuAction;
import uk.ac.reload.editor.properties.EditorProperties;

/**
 * Open Dialog with added value
 *
 * @author Phillip Beauvoir
 * @version $Id: EditorOpenDialog.java,v 1.1 1998/03/26 15:24:11 ynsingh Exp $
 */
public class EditorOpenDialog
implements IIcons
{
    
    /**
     * The static instance
     */
    private static EditorOpenDialog _dialog = new EditorOpenDialog();
    
    /**
     * The File Chooser
     */
    private DweezilFileChooser _chooser;
    
    
	/**
	 * Ask the user for a File name to open
	 * @return File or null if cancelled
	 */
	public static File askFileNameOpen(Component parent, String title, javax.swing.filechooser.FileFilter filter) {
	    _dialog.getFileChooser().setDialogTitle(title);
	    _dialog.getFileChooser().setFileFilter(filter);
		int returnVal = _dialog.getFileChooser().showOpenDialog(parent);
		
		// User Cancelled
		if(returnVal != JFileChooser.APPROVE_OPTION) {
		    return null;
		}
		
		// Get the chosen File
		return _dialog.getFileChooser().getSelectedFileAndStore();
	}

	/**
	 * Default Constructor
	 */
	public EditorOpenDialog() {
	    _chooser = new DweezilFileChooser();
		createAccessoryPanel();
	}
	
	/**
	 * @return The DweezilFileChooser
	 */
	public DweezilFileChooser getFileChooser() {
	    return _chooser;
	}
	
	/**
	 * Add the SCORM Player Folder Button
	 */
	protected void createAccessoryPanel() {
	    // Only if folder exists
	    final File folderSCORM = EditorProperties.getFileProperty("scormpackage.dir"); //$NON-NLS-1$
	    if(folderSCORM == null || !folderSCORM.exists()) {
	        return;
	    }

	    JPanel panel = new JPanel(new BorderLayout());
	    panel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
	    
	    MenuAction action = new MenuAction("SCORM Player " + //$NON-NLS-1$
	            Messages.getString("uk.ac.reload.editor.EditorOpenDialog.0"), //$NON-NLS-1$
	            ICON_CPSCORM) {
	    	
	        public void actionPerformed(ActionEvent e) {
	            _chooser.setCurrentDirectory(folderSCORM);
	    	}
	    };
	    
	    DweezilToolBar toolBar = new DweezilToolBar();
	    toolBar.setOrientation(JToolBar.VERTICAL);
	    toolBar.add(action);
	    
	    panel.add(toolBar);
	    
		_chooser.setAccessory(panel);
	}
}
