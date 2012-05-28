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

package uk.ac.reload.editor.learningdesign.editor.activities;

import javax.swing.Icon;
import javax.swing.JOptionPane;

import uk.ac.reload.editor.datamodel.DataComponent;
import uk.ac.reload.editor.learningdesign.datamodel.LD_Component;
import uk.ac.reload.editor.learningdesign.datamodel.components.activities.Activity;
import uk.ac.reload.editor.learningdesign.editor.shared.ComponentListPanel;


/**
 * A List View to display components in an Activity Editor
 * 
 * @author Phillip Beauvoir
 * @version $Id: ActivityListPanel.java,v 1.1 1998/03/26 15:40:32 ynsingh Exp $
 */
public abstract class ActivityListPanel
extends ComponentListPanel
{
    /**
     * The associated Activity Component 
     */
    protected Activity _activity;
    
    
    /**
     * Default Constructor
     * @param title The title to display in the CoolBar
     * @param icon The icon to display in the CoolBar
     * @param listHeight The height of the list
     */
    protected ActivityListPanel(String title, Icon icon, int listHeight) {
        super(title, icon, listHeight);
    }
    
    /**
     * Set the Activity Component 
     * @param activity Activity Component 
     */
    public void setActivity(Activity activity) {
        _activity = activity;
        
        // Lazily set the DataModel
        if(getDataModel() == null) {
            setDataModel(activity.getDataModel());
        }

        updateListModel();
        updateMenus();
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
	        _activity.removeRef((LD_Component)components[i]);
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
	        _activity.moveRefUp((LD_Component)component);
	        getList().setSelectedValue(component, true);
	    }
    }
    
    /**
     * Move a list item down
     */
    protected void moveListItemDown() {
	    Object component = getList().getSelectedValue();
	    if(component != null) {
	        _activity.moveRefDown((LD_Component)component);
	        getList().setSelectedValue(component, true);
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
        // ...we won't respond to a Component being deleted directly because our Activity Component will
        // take care of that and will notify via componentChanged().
        // So we'll listen in on the Activity Component being changed and set the model here...
        if(component == _activity) {
            updateListModel();
        }
	}
	
	
}
