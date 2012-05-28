
package uk.ac.reload.editor.learningdesign.editor.environment;

import javax.swing.AbstractListModel;
import javax.swing.JOptionPane;

import uk.ac.reload.dweezil.util.DweezilUIManager;
import uk.ac.reload.editor.learningdesign.datamodel.LD_Component;


/**
 * IndexElementListPanel
 * 
 * @author Phillip Beauvoir
 * @version $Id: IndexElementListPanel.java,v 1.1 1998/03/26 15:40:32 ynsingh Exp $
 */
public class IndexElementListPanel
extends IndexSearchListPanel
{

    /**
     * Selector Dialog
     */
    private IndexElementSelectorDialog _dialog;
    
    /**
     * Constructor
     */
    public IndexElementListPanel() {
        super("Index Element", DweezilUIManager.getIcon(ICON_INDEXSEARCH), 100);
    }

    protected void updateListModel() {
        LD_Component[] components = getIndexSearch().getIndexElements();
        // Model
        getList().setModel(new ComponentListModel(components));
    }

    protected void showSelectorDialog() {
	    if(_dialog == null) {
	        _dialog = new IndexElementSelectorDialog();
	    }
	    
	    _dialog.setIndexSearch(getIndexSearch());
	    _dialog.show();
    }

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
	        getIndexSearch().removeIndexElement((LD_Component)components[i]);
        }
	    
	    // Select fallback
	    if(pos == -1 && getList().getModel().getSize() > 0) {
	        pos = 0;
	    }
	    getList().setSelectedIndex(pos);
    }
    
    public void cleanup() {
        super.cleanup();

        if(_dialog != null) {
            _dialog.cleanup();
        }
    }

	//	========================= List Model ==================================
	
	protected class ComponentListModel
	extends AbstractListModel
	{

	    private LD_Component[] _components;
	    
	    /**
	     * Default Constructor
	     */
	    public ComponentListModel(LD_Component[] components) {
	        _components = components;
	    }
	    
        /* (non-Javadoc)
         * @see javax.swing.ListModel#getSize()
         */
        public int getSize() {
            return _components.length;
        }

        /* (non-Javadoc)
         * @see javax.swing.ListModel#getElementAt(int)
         */
        public Object getElementAt(int index) {
            return _components[index];
        }
	}
}
