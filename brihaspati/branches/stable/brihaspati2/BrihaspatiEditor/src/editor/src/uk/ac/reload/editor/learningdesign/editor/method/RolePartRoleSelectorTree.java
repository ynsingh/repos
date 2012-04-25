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

package uk.ac.reload.editor.learningdesign.editor.method;

import javax.swing.JLabel;

import uk.ac.reload.editor.datamodel.DataComponent;
import uk.ac.reload.editor.datamodel.DataModel;
import uk.ac.reload.editor.datamodel.IDataComponentIcon;
import uk.ac.reload.editor.datamodel.IDataModelListener;
import uk.ac.reload.editor.gui.Editor_TreeNode;
import uk.ac.reload.editor.learningdesign.datamodel.LD_Component;
import uk.ac.reload.editor.learningdesign.datamodel.components.roles.Role;
import uk.ac.reload.editor.learningdesign.datamodel.method.RolePart;
import uk.ac.reload.editor.learningdesign.editor.roles.RoleSelectorTree;


/**
 * A Tree for selecting Roles as RoleParts
 * 
 * @author Phillip Beauvoir
 * @version $Id: RolePartRoleSelectorTree.java,v 1.1 1998/03/26 15:40:32 ynsingh Exp $
 */
public class RolePartRoleSelectorTree
extends RoleSelectorTree
implements IDataModelListener 
{
    
    /**
     * The associated RolePart Component 
     */
    private RolePart _rolePart;
    
    /**
     * The display label
     */
    private JLabel _label;
    
    /**
     * Default Constructor
     */
    public RolePartRoleSelectorTree(JLabel label) {
        super();
        _label = label;
    }
    
    /**
     * Set the RolePart Component 
     * @param rolePart RolePart Component 
     */
    public void setRolePartComponent(RolePart rolePart) {
        _rolePart = rolePart;
        
        // Lazily set the DataModel
        if(getDataModel() == null) {
            setDataModel(rolePart.getDataModel());
            expandTree(true);
        }

        update();
    }
    
    /**
     * Update the nodes and label
     */
    private void update() {
        Role role = _rolePart.getReferencedRole();
        setSelectedNodes(new LD_Component[] { role });
        updateLabel(role);
    }
    
    /**
     * Over-ride this to add ourself as a listener.
     * We'll need to listen to Component changes so we can set selections.
     */
    public void setDataModel(DataModel ldDataModel) {
        super.setDataModel(ldDataModel);
        
		// Listen to DataModel changes
		ldDataModel.addIDataModelListener(this);		
    }
    
    /**
     * A node was selected on the tree
     * @param node
     */
    public void nodeSelected(Editor_TreeNode node) {
        Object o = node.getUserObject();
        if(o instanceof Role) {
            Role role = (Role)o;
            _rolePart.setReferencedRole(role);
        }
    }

	/**
	 * Update the Label
	 * @param role The Role
	 */
	protected void updateLabel(LD_Component component) {
	    if(component != null) {
	        _label.setText("<html>" + component.getTitle());
	        _label.setIcon(((IDataComponentIcon)component).getIcon());
	    }
	    else {
	        _label.setText("(not set)");
	        _label.setIcon(null);
	    }
 	}

    /**
     * Clean up
     */
    public void cleanup() {
        if(getDataModel() != null) {
            getDataModel().removeIDataModelListener(this);
        }

        super.cleanup();
        
        _rolePart = null;
    }

    //========================= Component Listener Events ==================================

    public void componentAdded(DataComponent component) {
    }
    
    public void componentRemoved(DataComponent component) {
    }

    public void componentMoved(DataComponent component) {
    }

    public void componentChanged(DataComponent component) {
        // ...we won't respond to the Role changing directly because our RolePart Component will
        // take care of that and will notify via componentChanged().
        // So we'll listen in on the RolePart Component being changed and set the nodes here...
        if(component == _rolePart) {
            update();
        }
	}
}
