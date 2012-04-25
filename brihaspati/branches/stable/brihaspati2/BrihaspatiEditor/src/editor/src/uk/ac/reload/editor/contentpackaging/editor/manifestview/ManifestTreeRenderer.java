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

package uk.ac.reload.editor.contentpackaging.editor.manifestview;

import java.awt.Component;

import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;



/**
 * Extend and over-ride the DefaultTreeCellRenderer so that we can highlight the dragged over node
 *
 * @author Phillip Beauvoir
 * @version $Id: ManifestTreeRenderer.java,v 1.1 1998/03/26 15:40:31 ynsingh Exp $
 */
public class ManifestTreeRenderer
extends DefaultTreeCellRenderer
{
	
	public ManifestTreeRenderer() {
		
	}
	
	public Component getTreeCellRendererComponent(JTree tree, Object value,
			boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
		
		if(value instanceof ManifestTreeNode) {
			ManifestTreeNode selNode = (ManifestTreeNode) value;
			
			// Hilight node as we drag over it
			if(selNode.isHiLited) selected = true;
			
			// Set some icons
			setLeafIcon(selNode == null ? null : selNode.getLeafIcon());
			setClosedIcon(selNode == null ? null : selNode.getClosedIcon());
			setOpenIcon(selNode == null ? null : selNode.getOpenIcon());
			
			// Tooltip
			setToolTipText(selNode.toString());
		}
		
		return super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
	}
}
