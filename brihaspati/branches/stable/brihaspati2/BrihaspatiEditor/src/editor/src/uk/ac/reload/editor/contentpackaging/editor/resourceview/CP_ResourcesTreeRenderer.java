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

package uk.ac.reload.editor.contentpackaging.editor.resourceview;

import java.awt.Component;

import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;

import uk.ac.reload.dweezil.util.DweezilUIManager;
import uk.ac.reload.editor.IIcons;
import uk.ac.reload.editor.Messages;
import uk.ac.reload.editor.contentpackaging.datamodel.CP_Resource;


/**
 * A Tree Renderer for the CP Resources Tree
 *
 * @author Phillip Beauvoir
 * @author Paul Sharples
 * @version $Id: CP_ResourcesTreeRenderer.java,v 1.1 1998/03/26 15:40:32 ynsingh Exp $
 */
public class CP_ResourcesTreeRenderer
extends DefaultTreeCellRenderer
implements IIcons
{
	
	public CP_ResourcesTreeRenderer() {
		
	}
	
	public Component getTreeCellRendererComponent(JTree tree, Object value,
			boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
		
		if(value instanceof CP_ResourcesTreeNode) {
			CP_ResourcesTreeNode selNode = (CP_ResourcesTreeNode) value;
			
			// Hilight node as we drag over it
			if(selNode.isHiLited) {
			    selected = true;
			}
			
			// Set some icons
			if(selNode.getParent() == null) { // Root Node
				closedIcon = DweezilUIManager.getIcon(ICON_RESOURCES);
				openIcon = closedIcon;
				leafIcon = closedIcon;
				setToolTipText(Messages.getString("uk.ac.reload.editor.contentpackaging.resourceview.CP_ResourcesTreeRenderer.0")); //$NON-NLS-1$
			}
			else {
				CP_Resource file = selNode.getCP_Resource();
				if(file.isDirectory()) {
					closedIcon = DweezilUIManager.getIcon(ICON_FOLDER_CLOSED);
					openIcon = DweezilUIManager.getIcon(ICON_FOLDER_OPEN);
					leafIcon = closedIcon;
					setToolTipText(Messages.getString("uk.ac.reload.editor.contentpackaging.resourceview.CP_ResourcesTreeRenderer.1")); //$NON-NLS-1$
				}
				else {
					closedIcon = file.getResourceIcon();
					openIcon = closedIcon;
					leafIcon = closedIcon;
					setToolTipText(file.getFriendlyName());
				}
			}
		}
		
		return super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
	}
	
}