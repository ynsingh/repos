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

import uk.ac.reload.dweezil.util.DweezilUIManager;
import uk.ac.reload.editor.learningdesign.datamodel.LD_Component;


/**
 * A List View to display Environment refs in an Activity Editor
 * 
 * @author Phillip Beauvoir
 * @version $Id: ActivityEnvironmentsListPanel.java,v 1.1 1998/03/26 15:40:32 ynsingh Exp $
 */
public class ActivityEnvironmentsListPanel
extends ActivityListPanel
{
    /**
     * Environment Selector Dialog
     */
    private ActivityEnvironmentSelectorDialog _dialog;
    
    /**
     * Default Constructor
     */
    public ActivityEnvironmentsListPanel() {
		super("Environments", DweezilUIManager.getIcon(ICON_ENVIRONMENTS), 200);
    }
    
    /**
     * Update the Model
     */
    protected void updateListModel() {
        LD_Component[] components = _activity.getEnvironments();
        // Model
        getList().setModel(new ComponentListModel(components));
    }
    
    /* (non-Javadoc)
     * @see uk.ac.reload.editor.learningdesign.editor.shared.ComponentListPanel#showSelectorDialog()
     */
    protected void showSelectorDialog() {
	    if(_dialog == null) {
	        _dialog = new ActivityEnvironmentSelectorDialog();
	    }
	    
	    _dialog.setActivity(_activity);
	    _dialog.show();
    }
    
    /* (non-Javadoc)
     * @see uk.ac.reload.editor.learningdesign.editor.shared.ComponentListPanel#cleanup()
     */
    public void cleanup() {
        super.cleanup();
        
        if(_dialog != null) {
            _dialog.cleanup();
        }
    }
}
