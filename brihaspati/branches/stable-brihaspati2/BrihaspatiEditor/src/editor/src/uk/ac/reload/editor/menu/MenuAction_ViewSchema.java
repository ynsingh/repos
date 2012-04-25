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

package uk.ac.reload.editor.menu;

import java.awt.event.ActionEvent;

import javax.swing.Icon;
import javax.swing.SwingUtilities;

import uk.ac.reload.dweezil.gui.ErrorDialogBox;
import uk.ac.reload.dweezil.menu.MenuAction;
import uk.ac.reload.dweezil.util.DweezilUIManager;
import uk.ac.reload.editor.EditorFrame;
import uk.ac.reload.editor.EditorHandler;
import uk.ac.reload.editor.gui.schemaviewer.SchemaWindow;
import uk.ac.reload.moonunit.SchemaController;

/**
 * A Menu Item to display the Schema Node Viewer
 *
 * @author Phillip Beauvoir
 * @version $Id: MenuAction_ViewSchema.java,v 1.1 1998/03/26 15:24:12 ynsingh Exp $
 */
public class MenuAction_ViewSchema extends MenuAction {
    
    private String _version;
    private Icon _icon;
    
    public MenuAction_ViewSchema(String version, Icon icon) {
        _version = version;
        _icon = icon;
        setText(version);
        setMenuIcon(icon);
    }
    
    /**
     * Show a new window - we put this on a thread so we can close the menu item
     * @param e
     */
    public void actionPerformed(ActionEvent e) {
        Thread thread = new Thread() {
            public void run() {
                SchemaController schemaController =
                    EditorHandler.getSharedInstance().getSchemaControllerInstance(_version);
                
                if(schemaController == null) {
                    ErrorDialogBox.showWarning("Could not get SchemaController for version: " + _version,
                            "Schema Viewer", null);
                    return;
                }
                
                EditorFrame.getInstance().setCursor(DweezilUIManager.WAIT_CURSOR);
                
                // New Internal window
                final SchemaWindow testWindow = new SchemaWindow(schemaController, _version);
                testWindow.setFrameIcon(_icon);
                
                // Show the Window later in the Event queue
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        EditorHandler.getSharedInstance().addInternalFrame(testWindow);
                        testWindow.show();
                    }
                });
                
                EditorFrame.getInstance().setCursor(DweezilUIManager.DEFAULT_CURSOR);
                
            }
        };
        
        thread.start();
    }
}

