
package uk.ac.reload.editor.learningdesign.editor.environment;

import javax.swing.JOptionPane;

import uk.ac.reload.dweezil.util.DweezilUIManager;
import uk.ac.reload.editor.EditorFrame;
import uk.ac.reload.editor.learningdesign.datamodel.components.environments.IndexSearch;


/**
 * Description
 * 
 * @author Phillip Beauvoir
 * @version $Id: IndexTypeOfElementListPanel.java,v 1.1 1998/03/26 15:40:32 ynsingh Exp $
 */
public class IndexTypeOfElementListPanel
extends IndexSearchListPanel
{

    /**
     * Constructor
     */
    public IndexTypeOfElementListPanel() {
        super("Index Type of Element", DweezilUIManager.getIcon(ICON_INDEXSEARCH), 100);
    }

    
    protected void updateListModel() {
        String[] strings = getIndexSearch().getIndexTypeOfElements();
        // Model
        getList().setModel(new StringListModel(strings));
    }

    protected void showSelectorDialog() {
        String s = (String)JOptionPane.showInputDialog(EditorFrame.getInstance(),
                "Element type name",
	            "",
	            JOptionPane.QUESTION_MESSAGE,
	            DweezilUIManager.getIcon(ICON_INDEXSEARCH),
	            IndexSearch.ELEMENT_NAMES, null);
        
        if(s != null) {
            getIndexSearch().addIndexTypeOfElement(s);
        }
    }

    protected void deleteListItems() {
	    Object[] components = getList().getSelectedValues();
	    
	    if(components.length == 0) {
	        return;
	    }
	    
	    int result = JOptionPane.showConfirmDialog(getParent(),
	            "Are you sure you want to delete the element type(s)?",
	            "Confirm Delete",
	            JOptionPane.YES_NO_OPTION);
	    
	    if(result != JOptionPane.YES_OPTION) {
	        return;
	    }
	    
	    // Determine list item to select after deletion
	    int pos = getList().getSelectedIndex() - 1;
	    
	    for(int i = 0; i < components.length; i++) {
	        getIndexSearch().removeIndexTypeOfElement((String)components[i]);
        }
	    
	    // Select fallback
	    if(pos == -1 && getList().getModel().getSize() > 0) {
	        pos = 0;
	    }
	    getList().setSelectedIndex(pos);
    }

}
