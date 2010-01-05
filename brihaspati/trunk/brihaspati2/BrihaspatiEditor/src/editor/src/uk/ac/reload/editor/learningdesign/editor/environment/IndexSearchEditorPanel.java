/**
 *  RELOAD TOOLS
 *
 *  Copyright (c) 2004 Oleg Liber, Bill Olivier, Phillip Beauvoir
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

import uk.ac.reload.dweezil.gui.ComponentHiderLabelPanel;
import uk.ac.reload.dweezil.gui.LabelComponentPanel;
import uk.ac.reload.dweezil.gui.layout.RelativeLayoutManager;
import uk.ac.reload.editor.datamodel.DataComponent;
import uk.ac.reload.editor.gui.widgets.DataElementComboBox;
import uk.ac.reload.editor.learningdesign.datamodel.components.environments.IndexSearch;
import uk.ac.reload.editor.learningdesign.editor.shared.MetadataPanel;
import uk.ac.reload.moonunit.learningdesign.LD_Core;


/**
 * Edit the IndexSearch Component
 *
 * @author Phillip Beauvoir
 * @version $Id: IndexSearchEditorPanel.java,v 1.1 1998/03/26 15:40:32 ynsingh Exp $
 */
public class IndexSearchEditorPanel
extends ServiceEditorPanel
{
	/**
	 * Search Type
	 */
	private DataElementComboBox _cbType;
	
	/**
	 * Index Class List Panel
	 */
	private IndexClassListPanel _indexClassListPanel;
	
	/**
	 * Index Element List Panel
	 */
	private IndexElementListPanel _indexElementListPanel;

	/**
	 * Index Type of Element List Panel
	 */
	private IndexTypeOfElementListPanel _indexTypeOfElementListPanel;
	
	/**
     * Metadata Panel
     */
    private MetadataPanel _metadataPanel;

    
    /**
	 * Default Constructor
	 */
	public IndexSearchEditorPanel() {
	}
	
	/** 
	 * Over-ride to set sub-panels
	 */
	public void setComponent(DataComponent dataComponent) {
	    super.setComponent(dataComponent);
	    
	    IndexSearch is = (IndexSearch)dataComponent;
	    
	    // Search Type
	    _cbType.setAttribute(is.getSearchDataElement(), LD_Core.SEARCH_TYPE, "free-text-search");
	    
	    // Index Class
	    _indexClassListPanel.setIndexSearch(is);
	    
	    // Index Element
	    _indexElementListPanel.setIndexSearch(is);

	    // Index Element Type
	    _indexTypeOfElementListPanel.setIndexSearch(is);

	    // Metadata
        _metadataPanel.setMetadataType(is.getMetadataType());
	}
	    
	/**
	 * Set up the view
	 */
	protected void setupView() {
		super.setupView();

		RelativeLayoutManager layoutManager = getRelativeLayoutManager();
		
		// Type
		_cbType = new DataElementComboBox(new String[] {
		        "free-text-search",
		        "index-with-reference",
		        "index-without-reference"
		});
		
		LabelComponentPanel panelType = new LabelComponentPanel("Type:", _cbType, 0.3, 1);
		layoutManager.addFromLeftToRightEdges(panelType, "panelType", "fieldPanel",
				RelativeLayoutManager.BOTTOM, 5, 10);
		
		// Index Class List Panel
		ComponentHiderLabelPanel hider1 = new ComponentHiderLabelPanel("Index Class",
        															   "Add Class types to search on.");
		layoutManager.addFromLeftToRightEdges(hider1, "hider1", "panelType",
		        							  RelativeLayoutManager.BOTTOM, 20, 10);
		_indexClassListPanel = new IndexClassListPanel();
		layoutManager.addFromLeftToRightEdges(_indexClassListPanel, "_indexClassListPanel", "hider1",
		        							  RelativeLayoutManager.BOTTOM, 5, 10);
		hider1.getComponentHiderButton().setComponent(_indexClassListPanel);
		hider1.getComponentHiderButton().showComponent(false);

		
		// Index Element List Panel
		ComponentHiderLabelPanel hider2 = new ComponentHiderLabelPanel("Index Element",
																	   "Add Elements to search on.");
		layoutManager.addFromLeftToRightEdges(hider2, "hider2", "_indexClassListPanel",
		        							  RelativeLayoutManager.BOTTOM, 20, 10);
		_indexElementListPanel = new IndexElementListPanel();
		layoutManager.addFromLeftToRightEdges(_indexElementListPanel, "_indexElementListPanel", "hider2",
				  							  RelativeLayoutManager.BOTTOM, 5, 10);
		hider2.getComponentHiderButton().setComponent(_indexElementListPanel);
		hider2.getComponentHiderButton().showComponent(false);
		
		// Index Type of Element List Panel
		ComponentHiderLabelPanel hider3 = new ComponentHiderLabelPanel("Index Type of Element",
																	   "Add Element types to search on.");
		layoutManager.addFromLeftToRightEdges(hider3, "hider3", "_indexElementListPanel",
		        							  RelativeLayoutManager.BOTTOM, 20, 10);
		_indexTypeOfElementListPanel = new IndexTypeOfElementListPanel();
		layoutManager.addFromLeftToRightEdges(_indexTypeOfElementListPanel, "_indexTypeOfElementListPanel", "hider3",
				  							  RelativeLayoutManager.BOTTOM, 5, 10);
		hider3.getComponentHiderButton().setComponent(_indexTypeOfElementListPanel);
		hider3.getComponentHiderButton().showComponent(false);
		
		// Metadata
		_metadataPanel = new MetadataPanel();
		layoutManager.addFromLeftToRightEdges(_metadataPanel, "_metadataPanel", "_indexTypeOfElementListPanel",
				RelativeLayoutManager.BOTTOM, 20, 10);
	}

    /**
     * Clean up
     */
    public void cleanup() {
        super.cleanup();
        
        if(_indexClassListPanel != null) {
            _indexClassListPanel.cleanup();
        }
        
        if(_indexElementListPanel != null) {
            _indexElementListPanel.cleanup();
        }
        
        if(_indexTypeOfElementListPanel != null) {
            _indexTypeOfElementListPanel.cleanup();
        }

        if(_metadataPanel != null) {
            _metadataPanel.cleanup();
        }
    }
}
