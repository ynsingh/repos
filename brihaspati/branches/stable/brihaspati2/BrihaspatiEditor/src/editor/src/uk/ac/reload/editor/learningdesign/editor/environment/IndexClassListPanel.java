
package uk.ac.reload.editor.learningdesign.editor.environment;

import javax.swing.JOptionPane;

import uk.ac.reload.dweezil.util.DweezilUIManager;
import uk.ac.reload.editor.EditorFrame;


/**
 * IndexClass ListPanel
 * 
 * @author Phillip Beauvoir
 * @version $Id: IndexClassListPanel.java,v 1.1 1998/03/26 15:40:32 ynsingh Exp $
 */
public class IndexClassListPanel
extends IndexSearchListPanel
{

    /**
     * Constructor
     */
    public IndexClassListPanel() {
        super("Index Class", DweezilUIManager.getIcon(ICON_INDEXSEARCH), 100);
    }

    protected void updateListModel() {
        String[] classes = getIndexSearch().getIndexClasses();
        // Model
        getList().setModel(new StringListModel(classes));
    }

    protected void showSelectorDialog() {
        String s = (String)JOptionPane.showInputDialog(EditorFrame.getInstance(),
	            "Class name",
	            "Add new class",
	            JOptionPane.QUESTION_MESSAGE,
	            DweezilUIManager.getIcon(ICON_INDEXSEARCH),
	            null, "");
        
        if(s != null && !"".equals(s)) {
            getIndexSearch().addIndexClass(s);
        }
    }

    protected void deleteListItems() {
	    Object[] components = getList().getSelectedValues();
	    
	    if(components.length == 0) {
	        return;
	    }
	    
	    int result = JOptionPane.showConfirmDialog(getParent(),
	            "Are you sure you want to delete the class(es)?",
	            "Confirm Delete",
	            JOptionPane.YES_NO_OPTION);
	    
	    if(result != JOptionPane.YES_OPTION) {
	        return;
	    }
	    
	    // Determine list item to select after deletion
	    int pos = getList().getSelectedIndex() - 1;
	    
	    for(int i = 0; i < components.length; i++) {
	        getIndexSearch().removeIndexClass((String)components[i]);
        }
	    
	    // Select fallback
	    if(pos == -1 && getList().getModel().getSize() > 0) {
	        pos = 0;
	    }
	    getList().setSelectedIndex(pos);
    }

    public void cleanup() {
        super.cleanup();
    }

}
