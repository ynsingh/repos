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

package uk.ac.reload.editor.learningdesign.editor.roles;

import uk.ac.reload.editor.datamodel.DataModel;
import uk.ac.reload.editor.gui.Editor_Tree;
import uk.ac.reload.editor.gui.Editor_TreeRenderer;


/**
 * The Tree to display Roles
 * 
 * @author Phillip Beauvoir
 * @version $Id: RoleTree.java,v 1.1 1998/03/26 15:40:32 ynsingh Exp $
 */
public class RoleTree
extends Editor_Tree 
{
    
    /**
     * Default Constructor
     */
    public RoleTree() {
        setRootVisible(false);
        
        setEditor_TreeModel(new RoleTreeModel(this));
        
		// Tree Renderer
		setCellRenderer(new Editor_TreeRenderer());
    }

    /**
     * Set the LD_DataModel
     * @param ldDataModel The LD_DataModel
     */
    public void setDataModel(DataModel ldDataModel) {
        super.setDataModel(ldDataModel);

        // Expand nodes
		expandTree(true);
        
		// Select first node
		selectFirstNode();
    }
    
}
