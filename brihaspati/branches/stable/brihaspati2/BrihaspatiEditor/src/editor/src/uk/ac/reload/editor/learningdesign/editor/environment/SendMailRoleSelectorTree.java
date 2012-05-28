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

package uk.ac.reload.editor.learningdesign.editor.environment;

import uk.ac.reload.editor.datamodel.DataComponent;
import uk.ac.reload.editor.datamodel.DataModel;
import uk.ac.reload.editor.datamodel.IDataModelListener;
import uk.ac.reload.editor.gui.Editor_TreeNode;
import uk.ac.reload.editor.learningdesign.datamodel.components.environments.SendMail;
import uk.ac.reload.editor.learningdesign.datamodel.components.roles.Role;
import uk.ac.reload.editor.learningdesign.editor.roles.RoleSelectorTree;


/**
 * A Tree for selecting Roles as Recipients for SendMail
 * 
 * @author Phillip Beauvoir
 * @version $Id: SendMailRoleSelectorTree.java,v 1.1 1998/03/26 15:40:32 ynsingh Exp $
 */
public class SendMailRoleSelectorTree
extends RoleSelectorTree
implements IDataModelListener 
{
    /**
     * The associated SendMail Component 
     */
    private SendMail _sendMail;
    
    /**
     * Constructor
     * @param label The display label
     */
    public SendMailRoleSelectorTree() {
        super();
    }
    
    /**
     * Set the SendMail Component 
     * @param sendMail SendMail Component 
     */
    public void setSendMailComponent(SendMail sendMail) {
        _sendMail = sendMail;
        
        // Lazily set the DataModel
        if(getDataModel() == null) {
            setDataModel(sendMail.getDataModel());
            expandTree(true);
        }
        
        setSelectedNodes(_sendMail.getRoles());
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
        // Can't de-select the last role-ref
        if(_sendMail.isLastRoleRef() && node.isSelected()) {
            return;
        }
        
        // This will cause the SendMail Component to notify us below...
        Object o = node.getUserObject();
        if(o instanceof Role) {
            Role role = (Role)o;
            if(!node.isSelected()) {
                _sendMail.addRoleRef(role);
            }
            else {
                _sendMail.removeRoleRef(role);
            }
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
    }

    //========================= Component Listener Events ==================================

    public void componentAdded(DataComponent component) {
    }
    
    public void componentRemoved(DataComponent component) {
    }

    public void componentMoved(DataComponent component) {
    }

	public void componentChanged(DataComponent component) {
        // ...we won't respond if a Role is removed because our SendMail Component will
	    // take care of that and might set a default role-ref.
        // So we'll listen in on the SendMail Component being changed and set the nodes here...
        if(component == _sendMail) {
            setSelectedNodes(_sendMail.getRoles());
        }
	}
}
