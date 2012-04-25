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

import java.awt.Event;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.JComponent;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import uk.ac.reload.dweezil.menu.DweezilMenu;
import uk.ac.reload.dweezil.menu.MenuAction;
import uk.ac.reload.editor.Messages;

/**
 * The Edit Menu
 *
 * @author Phillip Beauvoir
 * @version $Id: Menu_Edit.java,v 1.1 1998/03/26 15:24:12 ynsingh Exp $
 */
public class Menu_Edit extends DweezilMenu {
	
	public MenuAction_Undo actionUndo;
	public MenuAction_Redo actionRedo;
	public MenuAction_Cut actionCut;
	public MenuAction_Copy actionCopy;
	public MenuAction_Paste actionPaste;
	public MenuAction_Delete actionDelete;
	public MenuAction_MoveUp actionMoveUp;
	public MenuAction_MoveDown actionMoveDown;
	
	
	public Menu_Edit() {
	    String menuText = Messages.getString("uk.ac.reload.editor.menu.Menu_Edit.0"); //$NON-NLS-1$
		setText(MenuAction.getRemoveMnemonicText(menuText));
		setMnemonic(MenuAction.getMnemonic(menuText));
		
		actionUndo = new MenuAction_Undo();
		actionRedo = new MenuAction_Redo();
		actionCut = new MenuAction_Cut();
		actionCopy = new MenuAction_Copy();
		actionPaste = new MenuAction_Paste();
		actionDelete = new MenuAction_Delete();
		actionMoveUp = new MenuAction_MoveUp();
		actionMoveDown = new MenuAction_MoveDown();
		
		int keyMask = Toolkit.getDefaultToolkit().getMenuShortcutKeyMask();
		
		// Undo
		JMenuItem item = add(actionUndo);
		item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, keyMask));
		item.setMnemonic(actionUndo.getMnemonic());
		
		// Redo
		item = add(actionRedo);
		item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, Event.SHIFT_MASK + keyMask));
		item.setMnemonic(actionRedo.getMnemonic());
		
		addSeparator();
		
		// Cut
		item = add(actionCut);
		item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, keyMask));
		item.setMnemonic(actionCut.getMnemonic());
		
		// Copy
		item = add(actionCopy);
		item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, keyMask));
		item.setMnemonic(actionCopy.getMnemonic());
		
		// Paste
		item = add(actionPaste);
		item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, keyMask));
		item.setMnemonic(actionPaste.getMnemonic());
		
		// Delete
		item = add(actionDelete);
		item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0));
		item.setMnemonic(actionDelete.getMnemonic());
		
		addSeparator();
		
		// Move Up
		item = add(actionMoveUp);
		item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_UP, InputEvent.ALT_MASK));
		
		// Move Down
		item = add(actionMoveDown);
		item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, InputEvent.ALT_MASK));
	}
	
	/**
	 * Some components need the Ctrl-X, Ctrl-C, and Ctrl-V key bindings remapped
	 * @param component
	 */
	public void remapKeyStrokes(JComponent component) {
		int keyMask = Toolkit.getDefaultToolkit().getMenuShortcutKeyMask();
		component.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_X, keyMask), "CUT"); //$NON-NLS-1$
		component.getActionMap().put("CUT", actionCut); //$NON-NLS-1$
		component.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_C, keyMask), "COPY"); //$NON-NLS-1$
		component.getActionMap().put("COPY", actionCopy); //$NON-NLS-1$
		component.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_V, keyMask), "PASTE"); //$NON-NLS-1$
		component.getActionMap().put("PASTE", actionPaste); //$NON-NLS-1$
	}
	
}