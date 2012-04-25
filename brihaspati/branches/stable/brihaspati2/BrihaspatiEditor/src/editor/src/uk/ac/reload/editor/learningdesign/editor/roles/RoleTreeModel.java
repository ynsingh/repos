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

import uk.ac.reload.dweezil.gui.tree.DweezilTree;
import uk.ac.reload.editor.datamodel.DataComponent;
import uk.ac.reload.editor.datamodel.DataModel;
import uk.ac.reload.editor.gui.Editor_TreeModel;
import uk.ac.reload.editor.gui.Editor_TreeNode;
import uk.ac.reload.editor.learningdesign.datamodel.LD_DataModel;
import uk.ac.reload.editor.learningdesign.datamodel.components.roles.Role;
import uk.ac.reload.editor.learningdesign.datamodel.components.roles.Roles_Grouping;


/**
 * RoleTreeModel
 * 
 * @author Phillip Beauvoir
 * @version $Id: RoleTreeModel.java,v 1.1 1998/03/26 15:40:32 ynsingh Exp $
 */
public class RoleTreeModel
extends Editor_TreeModel
{

    /**
     * Default Constructor
     * @param tree The Owning Tree
     */
    public RoleTreeModel(DweezilTree tree) {
        super(tree);
    }
    
    /**
     * Set the LD_DataModel
     * @param ldDataModel The LD_DataModel
     */
    public void setDataModel(DataModel dataModel) {
        super.setDataModel(dataModel);
        
        // Get Root Data Point
        Roles_Grouping rootGroup = ((LD_DataModel)dataModel).getRoles_Grouping();

        // Create the child nodes
        root = new Editor_TreeNode(rootGroup);
        buildChildren((Editor_TreeNode)root);
		
        // Reload the Model
		reload();
		
		// Listen to DataModel changes
		dataModel.addIDataModelListener(this);		
    }

    //========================= Component Listener Events ==================================

    /**
     * Over-ride this to only add Role Components
     * @param component The DataComponent added
     */
    public void componentAdded(DataComponent component) {
        if(component instanceof Role) {
            super.componentAdded(component);
        }
    }
    
	/**
     * Over-ride this to only remove Role Components
     * @param component The DataComponent
     */
    public void componentRemoved(DataComponent component) {
        if(component instanceof Role) {
            super.componentRemoved(component);
        }
    }

	/**
     * Over-ride this to only move Role Components
     * @param component The DataComponent
     */
    public void componentMoved(DataComponent component) {
        if(component instanceof Role) {
            super.componentMoved(component);
        }
    }

    /**
     * Over-ride this to only change Role Components
     * @param component The DataComponent
	 */
	public void componentChanged(DataComponent component) {
        if(component instanceof Role) {
            super.componentChanged(component);
        }
	}
}
