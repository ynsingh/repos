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

import javax.swing.JOptionPane;

import uk.ac.reload.dweezil.util.DweezilUIManager;
import uk.ac.reload.editor.datamodel.DataComponent;
import uk.ac.reload.editor.learningdesign.datamodel.LD_Component;
import uk.ac.reload.editor.learningdesign.datamodel.method.Act;
import uk.ac.reload.editor.learningdesign.datamodel.method.RolePart;
import uk.ac.reload.editor.learningdesign.editor.shared.ComponentListPanel;


/**
 * A List View to display Role Parts in the Act Editor
 * 
 * @author Phillip Beauvoir
 * @version $Id: ActRolePartListPanel.java,v 1.1 1998/03/26 15:40:32 ynsingh Exp $
 */
public class ActRolePartListPanel
extends ComponentListPanel
{
    /**
     * The associated Act Component 
     */
    private Act _act;
    
    /**
     * Play Selector Dialog
     */
    private ActRolePartSelectorDialog _dialog;
    
    
    /**
     * Default Constructor
     */
    public ActRolePartListPanel() {
        super("Role Parts", DweezilUIManager.getIcon(ICON_ROLEPART), 100);
    }
    
    /**
     * Enable, set the Act Component and update the List and menu buttons 
     * @param act Act Component 
     */
    public void setAct(Act act) {
        setEnabled(true);
        
        _act = act;
        
        // Lazily set the DataModel
        if(getDataModel() == null) {
            setDataModel(act.getDataModel());
        }

        updateListModel();
        updateMenus();
    }
    
    /**
     * Clear and disable the list
     */
    public void clear() {
        _act = null;
        updateListModel();
        setEnabled(false);
    }
    
    /**
     * Delete selected list items
     */
    protected void deleteListItems() {
	    Object[] components = getList().getSelectedValues();
	    
	    if(components.length == 0) {
	        return;
	    }
	    
	    int result = JOptionPane.showConfirmDialog(getParent(),
	            "Are you sure you want to delete the reference(s)?",
	            "Confirm Delete",
	            JOptionPane.YES_NO_OPTION);
	    
	    if(result != JOptionPane.YES_OPTION) {
	        return;
	    }
	    
	    // Determine list item to select after deletion
	    int pos = getList().getSelectedIndex() - 1;
	    
	    for(int i = 0; i < components.length; i++) {
	        _act.removeRolePartCompleted((RolePart)components[i]);
        }
	    
	    // Select fallback
	    if(pos == -1 && getList().getModel().getSize() > 0) {
	        pos = 0;
	    }
	    getList().setSelectedIndex(pos);
    }
    
    /**
     * Move a list item up
     */
    protected void moveListItemUp() {
	    Object component = getList().getSelectedValue();
	    if(component != null) {
	        _act.moveRolePartCompletedUp((RolePart)component);
	        getList().setSelectedValue(component, true);
	    }
    }
    
    /**
     * Move a list item down
     */
    protected void moveListItemDown() {
	    Object component = getList().getSelectedValue();
	    if(component != null) {
	        _act.moveRolePartCompletedDown((RolePart)component);
	        getList().setSelectedValue(component, true);
	    }
    }
    
    /**
     * Update the Model
     */
    protected void updateListModel() {
        if(_act != null) {
            LD_Component[] components = _act.getRolePartsCompleted();
            getList().setModel(new ComponentListModel(components));
        }
        
        else {
            getList().setListData(new String[] { ""} );
        }
    }
    
    /* (non-Javadoc)
     * @see uk.ac.reload.editor.learningdesign.editor.shared.ComponentListPanel#showSelectorDialog()
     */
    protected void showSelectorDialog() {
	    if(_dialog == null) {
	        _dialog = new ActRolePartSelectorDialog();
	    }
	    
	    _dialog.setAct(_act);
	    _dialog.show();
    }

    public void cleanup() {
        super.cleanup();
        
        if(_dialog != null) {
            _dialog.cleanup();
        }
    }
    
    
	//========================= Component Listener Events ==================================

    public void componentAdded(DataComponent component) {
    }
    
    public void componentRemoved(DataComponent component) {
    }

    public void componentMoved(DataComponent component) {
    }

    public void componentChanged(DataComponent component) {
        // ...we won't respond to a Role Part being deleted directly because our Act Component will
        // take care of that and will notify via componentChanged().
        // So we'll listen in on the Act Component being changed and set the model here...
        if(component == _act) {
            updateListModel();
        }
	}
	
	
}
