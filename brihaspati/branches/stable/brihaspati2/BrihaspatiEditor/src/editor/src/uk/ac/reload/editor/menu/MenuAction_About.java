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

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import uk.ac.reload.diva.util.GeneralUtils;
import uk.ac.reload.dweezil.menu.MenuAction;
import uk.ac.reload.dweezil.util.DweezilUIManager;
import uk.ac.reload.editor.EditorFrame;
import uk.ac.reload.editor.IIcons;
import uk.ac.reload.editor.Messages;
import uk.ac.reload.editor.properties.EditorProperties;

/**
 * The "About" Menu Item for the Application.
 * Displays Version, Author, Contact details etc.
 * Extends MenuAction.
 *
 * @author Phillip Beauvoir
 * @version $Id: MenuAction_About.java,v 1.1 1998/03/26 15:24:11 ynsingh Exp $
 */
public class MenuAction_About
extends MenuAction
implements IIcons
{
    private static MessageLabel box;
	
	/**
	 * Default Constructor
	 */
	public MenuAction_About() {
		super(Messages.getString("uk.ac.reload.editor.menu.MenuAction_About.0") + EditorProperties.getString("APP_NAME") + "..."); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	}
	
	/**
	 * The Menu Item was selected.
	 * @param e ActionEvent
	 */
	public void actionPerformed(ActionEvent e) {
		doShowMessageDialog();
	}
	
	public static void doShowMessageDialog() {
		// Lazily create
		if(box == null) {
		    box = new MessageLabel();
		}
		
		JOptionPane.showMessageDialog(EditorFrame.getInstance(),
		        box,
		        Messages.getString("uk.ac.reload.editor.menu.MenuAction_About.1") + " " //$NON-NLS-1$ //$NON-NLS-2$
		        + EditorProperties.getString("APP_NAME"), //$NON-NLS-1$
				JOptionPane.PLAIN_MESSAGE,
				DweezilUIManager.getIcon(bss_bsspllogo07));
		
	}
	
	static class MessageLabel extends JLabel {
		MessageLabel() {
			final Icon icon = DweezilUIManager.getIcon(IMAGE_EASTER_EGG);
			if(icon != null) {
				setPreferredSize(new Dimension(icon.getIconWidth(), icon.getIconHeight()));
			}
			else {
			    setPreferredSize(new Dimension(350, 400));
			}
			
			setVerticalAlignment(SwingConstants.TOP);
			
			final String msg = "<html>" + //$NON-NLS-1$
			"<font size=\"+1\" color=blue><b>" + EditorProperties.getString("APP_NAME") + "</b></font><br>" + //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			"<font color=black>" + //$NON-NLS-1$
			Messages.getString("uk.ac.reload.editor.menu.MenuAction_About.2") + " " + EditorProperties.getString("VERSION") + " (" + EditorProperties.getString("BUILD_DATE") + ")" + "<br>" + //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$
			EditorProperties.getString("COPYRIGHT") + "<br><br>" + //$NON-NLS-1$ //$NON-NLS-2$
			EditorProperties.getString("AUTHORS") + "<br>" + //$NON-NLS-1$ //$NON-NLS-2$
			EditorProperties.getString("PROJMANAGER") + "<br>" + //$NON-NLS-1$ //$NON-NLS-2$
			EditorProperties.getString("BILL") + "<br>" + //$NON-NLS-1$ //$NON-NLS-2$
			EditorProperties.getString("DOCDISS") + "<br><br>" + //$NON-NLS-1$ //$NON-NLS-2$
			EditorProperties.getString("CONTRIBUTORS") + "<br><br>" + //$NON-NLS-1$ //$NON-NLS-2$
			"<u>" + EditorProperties.getString("EMAIL_CONTACT") + "</u>" + "<br>" + //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
			"<u>" + EditorProperties.getString("WEB_PAGE") + "</u>" +"<br><br>" + //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
			"<i>Java Version " + GeneralUtils.getJavaVersion() + "</i><br>"; //$NON-NLS-1$ //$NON-NLS-2$
			
			
			setText(msg);
			
			addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					if(e.getClickCount() == 2 && e.isShiftDown()) {
						setIcon(getIcon() == null ? icon : null);
						setText(getText() == null ? msg : null);
					}
				}
			});
			
		}
	}
}
