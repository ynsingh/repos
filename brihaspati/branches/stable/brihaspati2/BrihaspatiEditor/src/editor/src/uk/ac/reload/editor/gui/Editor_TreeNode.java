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

import java.util.Enumeration;

import uk.ac.reload.dweezil.gui.tree.DweezilTreeNode;



/**
 * TreeNode
 *
 * @author Phillip Beauvoir
 * @version $Id: Editor_TreeNode.java,v 1.1 1998/03/26 15:24:11 ynsingh Exp $
 */
public class Editor_TreeNode
extends DweezilTreeNode
{
    public final static int SINGLE_SELECTION = 0;
    public final static int DIGIN_SELECTION = 4;
    
    private int _selectionMode;
    
    private boolean _isSelected;
    
    public Editor_TreeNode(Object o) {
        super(o);
        setSelectionMode(SINGLE_SELECTION);
    }
    
    public void setSelectionMode(int mode) {
        _selectionMode = mode;
    }
    
    public int getSelectionMode() {
        return _selectionMode;
    }
    
    public void setSelected(boolean isSelected) {
        _isSelected = isSelected;
        
        if((_selectionMode == DIGIN_SELECTION) && (children != null)) {
            Enumeration e = children.elements();      
            while(e.hasMoreElements()) {
                Editor_TreeNode node = (Editor_TreeNode)e.nextElement();
                node.setSelected(isSelected);
            }
        }
    }
    
    public boolean isSelected() {
        return _isSelected;
    }
}
