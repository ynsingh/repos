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

import javax.swing.JPopupMenu;

import uk.ac.reload.dweezil.menu.MenuAction;
import uk.ac.reload.editor.EditorHandler;
import uk.ac.reload.editor.IIcons;
import uk.ac.reload.editor.Messages;

/**
 * New File Menu Action
 *
 * @author Phillip Beauvoir
 * @version $Id: MenuAction_New.java,v 1.1 1998/03/26 15:24:12 ynsingh Exp $
 */
public class MenuAction_New
extends MenuAction
implements IIcons
{
	
    private MenuAction[] newActions;
    
	/**
	 * Constructor
	 */
	public MenuAction_New() {
		super(Messages.getString("uk.ac.reload.editor.menu.MenuAction_New.0"), ICON_NEW); //$NON-NLS-1$

		// Add Registered "New" Menu Items
		newActions = EditorHandler.getSharedInstance().getNewDocumentMenuActions();
	}
	
	/** 
	 * Show the Popup Menu
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {
		JPopupMenu popupMenu = new JPopupMenu();
		for(int i = 0; i < newActions.length; i++) {
		    popupMenu.add(newActions[i]);
        }
		popupMenu.show(getButton(), 0, getButton().getHeight());
	}
	
}