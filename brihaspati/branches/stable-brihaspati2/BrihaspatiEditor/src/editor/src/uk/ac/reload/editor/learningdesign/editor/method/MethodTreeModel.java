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

import uk.ac.reload.dweezil.gui.tree.DweezilTree;
import uk.ac.reload.editor.datamodel.DataComponent;
import uk.ac.reload.editor.datamodel.DataModel;
import uk.ac.reload.editor.gui.Editor_TreeModel;
import uk.ac.reload.editor.gui.Editor_TreeNode;
import uk.ac.reload.editor.learningdesign.datamodel.LD_DataModel;
import uk.ac.reload.editor.learningdesign.datamodel.method.Act;
import uk.ac.reload.editor.learningdesign.datamodel.method.Method;
import uk.ac.reload.editor.learningdesign.datamodel.method.Play;
import uk.ac.reload.editor.learningdesign.datamodel.method.RolePart;


/**
 * Method TreeModel
 * 
 * @author Phillip Beauvoir
 * @version $Id: MethodTreeModel.java,v 1.1 1998/03/26 15:40:32 ynsingh Exp $
 */
public class MethodTreeModel
extends Editor_TreeModel
{
    
    /**
     * Constructor
     * @param tree The Owning Tree
     */
    public MethodTreeModel(DweezilTree tree) {
        super(tree);
    }
    
    /**
     * Set the DataModel
     * @param ldDataModel The DataModel
     */
    public void setDataModel(DataModel ldDataModel) {
        super.setDataModel(ldDataModel);
        
        // Get Root Data Point
        Method rootGroup = ((LD_DataModel)ldDataModel).getMethod();

        // Create the child nodes
        root = new Editor_TreeNode(rootGroup);
        buildChildren((Editor_TreeNode)root);
		
        // Reload the Model
		reload();
		
		// Listen to DataModel changes
		ldDataModel.addIDataModelListener(this);		
    }
    
    //========================= Component Listener Events ==================================

    /**
     * Over-ride this to only add suitable Components
     * @param component The DataComponent added
     */
    public void componentAdded(DataComponent component) {
        if(isMethodTypeComponent(component)) {
            super.componentAdded(component);
        }
    }
    
	/**
     * Over-ride this to only remove suitable Components
     * @param component The DataComponent
     */
    public void componentRemoved(DataComponent component) {
        if(isMethodTypeComponent(component)) {
            super.componentRemoved(component);
        }
    }

	/**
     * Over-ride this to only move suitable Components
     * @param component The DataComponent
     */
    public void componentMoved(DataComponent component) {
        if(isMethodTypeComponent(component)) {
            super.componentMoved(component);
        }
    }

    /**
     * Over-ride this to only change suitable Components
     * @param component The DataComponent
	 */
	public void componentChanged(DataComponent component) {
        if(isMethodTypeComponent(component)) {
            super.componentChanged(component);
        }
	}

    /**
     * Convenience method
     * @param component The component to query
     * @return True if component is of the right type to process
     */
    private boolean isMethodTypeComponent(DataComponent component) {
        return component instanceof Play
                || component instanceof Act
                || component instanceof RolePart;
    }
}
