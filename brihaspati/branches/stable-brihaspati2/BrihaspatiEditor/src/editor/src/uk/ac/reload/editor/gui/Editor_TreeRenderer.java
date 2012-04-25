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

package uk.ac.reload.editor.gui;

import java.awt.Component;
import java.awt.Font;

import javax.swing.Icon;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;

import uk.ac.reload.editor.datamodel.DataComponent;
import uk.ac.reload.editor.datamodel.IDataComponentIcon;
import uk.ac.reload.editor.learningdesign.datamodel.LD_Grouping;


/**
 * Editor Tree Cell Renderer
 *
 * @author Phillip Beauvoir
 * @version $Id: Editor_TreeRenderer.java,v 1.1 1998/03/26 15:24:11 ynsingh Exp $
 */
public class Editor_TreeRenderer
extends DefaultTreeCellRenderer {
    Font _italicFont, _plainFont;

    public Editor_TreeRenderer() {
    }

    public Component getTreeCellRendererComponent(JTree tree, Object value,
            boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {

        if(value instanceof Editor_TreeNode) {
            Editor_TreeNode selNode = (Editor_TreeNode) value;
            DataComponent dc = (DataComponent)selNode.getUserObject();
            
			// Hilight node as we drag over it
			if(selNode.isHiLited) {
			    selected = true;
			}
            
            if(dc instanceof IDataComponentIcon) {
                IDataComponentIcon iconType = (IDataComponentIcon)dc;
                Icon icon = iconType.getIcon();
                setLeafIcon(icon);
                setClosedIcon(icon);
                setOpenIcon(icon);
            }
            
			// Tooltip
			setToolTipText(dc.toString());
			
			// Font
			setFont(dc);
        }
        
        return super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
    }
    
    /**
     * Set the font according to the DataComponent
     * @param dc
     */
    protected void setFont(DataComponent dc) {
        if(dc instanceof LD_Grouping) {
            setFont(dc.hasChildren() ? getPlainFont() : getItalicFont());
        }
        else {
            setFont(getPlainFont());
        }
    }
    
    public Font getPlainFont() {
        if(_plainFont == null) {
            _plainFont = getFont();
        }
        return _plainFont;
    }

    public Font getItalicFont() {
        if(_italicFont == null) {
           if(getPlainFont() != null) {
                _italicFont = getPlainFont().deriveFont(Font.ITALIC);
            }
        }
        return _italicFont;
    }
}

