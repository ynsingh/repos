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

import javax.swing.JPopupMenu;
import javax.swing.ToolTipManager;

import uk.ac.reload.dweezil.gui.tree.DweezilTree;
import uk.ac.reload.dweezil.gui.tree.DweezilTreeNode;
import uk.ac.reload.dweezil.gui.tree.DweezilTreePopupHandler;
import uk.ac.reload.editor.datamodel.DataComponent;
import uk.ac.reload.editor.datamodel.DataModel;


/**
 * JTree for Components
 * 
 * @author Phillip Beauvoir
 * @version $Id: Editor_Tree.java,v 1.1 1998/03/26 15:24:11 ynsingh Exp $
 */
public abstract class Editor_Tree
extends DweezilTree
{

    /**
     * The DataModel
     */
    private DataModel _dataModel;
    
    /**
     * The Tree Model
     */
    private Editor_TreeModel _treeModel;
    
	/**
	 * Popup Menu Handler
	 */
    private DweezilTreePopupHandler _popupMenuHandler;
    
    
    /**
     * Default Constructor
     */
    protected Editor_Tree() {
		// Tooltips
		ToolTipManager.sharedInstance().registerComponent(this);
    }
        
    /**
     * Set the DataModel
     * @param dataModel The DataModel
     */
    public void setDataModel(DataModel dataModel) {
        _dataModel = dataModel;
        getEditor_TreeModel().setDataModel(_dataModel);
    }
    
    /**
     * @return The DataModel
     */
    public DataModel getDataModel() {
        return _dataModel;
    }
    
    /**
     * @return The Tree Model.
     */
    public Editor_TreeModel getEditor_TreeModel() {
        return _treeModel;
    }
    
    /**
     * Set the Tree Model
     * @param treeModel The Editor_TreeModel
     */
    public void setEditor_TreeModel(Editor_TreeModel treeModel) {
        _treeModel = treeModel;
        setModel(_treeModel);
    }
    
    /**
     * @return The DweezilTreePopupHandler
     */
    public DweezilTreePopupHandler getDweezilTreePopupHandler() {
        if(_popupMenuHandler == null) {
            _popupMenuHandler = new DweezilTreePopupHandler(this);
        }
        return _popupMenuHandler;
    }

    /**
     * @return The PopupMenu
     */
    public JPopupMenu getPopupMenu() {
        return getDweezilTreePopupHandler().getPopupMenu();
    }
    
	/**
	 * Add a new Element to the Data Model depending on the currently selected Tree Node.
	 * @param elementName The Element Name
	 * @param title The title
	 * @return The LD_Component created or null
	 */
	public DataComponent addNewElement(String elementName, String title) {
	    // Get current selected Tree node and Component
	    DweezilTreeNode selNode = getSelectedNode();
	    if(selNode != null) {
	        DataComponent component = (DataComponent)selNode.getUserObject();
	        return component.addChildElement(elementName, title);
	    }
	    else {
	        return null;
	    }
	}
	
	/**
	 * Remove the currently selected Elements
	 */
	public void removeSelectedElements() {
	    DweezilTreeNode[] selNodes = getSelectedNodes();
	    if(selNodes == null) {
	        return;
	    }
	    
	    for(int i = 0; i < selNodes.length; i++) {
		    DataComponent component = (DataComponent)selNodes[i].getUserObject();
		    if(component.canDelete()) {
		        component.delete();
		    }
        }
	    
	    // Have to call this, because a change in font to italic in the tre-renderer can create ellipses in nodes
	    updateNodes();
	}
    
    /**
     * Clean up
     */
    public void cleanup() {
        if(_treeModel != null) {
            _treeModel.cleanup();
        }
        
        // This is important!
        _dataModel = null;
    }
}
