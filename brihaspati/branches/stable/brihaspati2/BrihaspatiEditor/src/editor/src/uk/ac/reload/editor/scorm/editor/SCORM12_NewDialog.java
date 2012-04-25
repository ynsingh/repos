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

package uk.ac.reload.editor.scorm.editor;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.JPanel;

import uk.ac.reload.dweezil.gui.DweezilToolBar;
import uk.ac.reload.dweezil.menu.MenuAction;
import uk.ac.reload.editor.IIcons;
import uk.ac.reload.editor.Messages;
import uk.ac.reload.editor.contentpackaging.editor.CP_NewDialog;
import uk.ac.reload.editor.properties.EditorProperties;

/**
 * Ask user for CP Folder and (optional) versions
 *
 * @author Phillip Beauvoir
 * @version $Id: SCORM12_NewDialog.java,v 1.1 1998/03/26 15:32:14 ynsingh Exp $
 */
public class SCORM12_NewDialog
extends CP_NewDialog
implements IIcons
{
	/**
	 * Constructor with option to choose versions
	 * 
	 * @param versionsCP The available CP versions
	 * @param defaultVersionCP The default CP version
	 * @param versionsMD The available MD versions
	 * @param defaultVersionMD The default MD version
	 */
	public SCORM12_NewDialog(String[] versionsCP, String defaultVersionCP, String[] versionsMD, String defaultVersionMD) {
	    super(versionsCP, defaultVersionCP, versionsMD, defaultVersionMD);
	}
	
	/**
	 * Constructor for choosing folder
	 * 
	 * @param title The Titlebar title
	 */
	public SCORM12_NewDialog(String title) {
	    super(title);
	}
	
	/**
	 * Over-ride this to add a button to take us to the folder where the Reload SCORM Player
	 * likes to keep its packages
	 */
	protected void createAccessoryPanel(String[] versionsCP, String defaultVersionCP, String[] versionsMD, String defaultVersionMD) {
	    // Only if folder exists
	    final File folderSCORM = EditorProperties.getFileProperty("scormpackage.dir"); //$NON-NLS-1$
	    if(folderSCORM != null && folderSCORM.exists()) {
	        MenuAction action = new MenuAction("SCORM Player " + //$NON-NLS-1$
	                Messages.getString("uk.ac.reload.editor.contentpackaging.scorm12.SCORM12_NewDialog.0"), //$NON-NLS-1$
	                ICON_CPSCORM) {
	            
	            public void actionPerformed(ActionEvent e) {
	                File folderSCORM = EditorProperties.getFileProperty("scormpackage.dir"); //$NON-NLS-1$
	                if(folderSCORM != null) {
	                    if(!folderSCORM.exists()) {
	                        folderSCORM.mkdirs();
	                    }
	                    getFolderChooser().setCurrentDirectory(folderSCORM);
	                }
	            }
	        };
	        
	        DweezilToolBar toolBar = new DweezilToolBar();
	        toolBar.add(action);
	        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
	        panel.add(toolBar);
	        getAccessoryPanel().add(panel);
	    }
	    
	    super.createAccessoryPanel(versionsCP, defaultVersionCP, versionsMD, defaultVersionMD);
	}
}
