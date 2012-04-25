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

import javax.swing.JPanel;

import uk.ac.reload.dweezil.gui.ComponentHiderLabelPanel;
import uk.ac.reload.dweezil.gui.LabelComponentPanel;
import uk.ac.reload.dweezil.gui.layout.RelativeLayoutManager;
import uk.ac.reload.dweezil.gui.layout.SGLayout;
import uk.ac.reload.editor.datamodel.DataComponent;
import uk.ac.reload.editor.datamodel.DataElement;
import uk.ac.reload.editor.gui.IdentifierLabelTextField;
import uk.ac.reload.editor.gui.TitledEditorPanel;
import uk.ac.reload.editor.gui.widgets.DataElementComboBox;
import uk.ac.reload.editor.gui.widgets.DataElementTextField;
import uk.ac.reload.editor.learningdesign.datamodel.components.environments.LearningObject;
import uk.ac.reload.editor.learningdesign.editor.shared.IsVisibleParametersPanel;
import uk.ac.reload.editor.learningdesign.editor.shared.ItemTypePanel;
import uk.ac.reload.editor.learningdesign.editor.shared.MetadataPanel;
import uk.ac.reload.moonunit.learningdesign.LD_Core;

/**
 * Editor Panel to edit a LearningObject
 *
 * @author Phillip Beauvoir
 * @version $Id: LearningObjectEditorPanel.java,v 1.1 1998/03/26 15:40:32 ynsingh Exp $
 */
public class LearningObjectEditorPanel
extends TitledEditorPanel
{
    static final String RESOURCE_DESCRIPTION = "Reference to a Resource.";

    /**
	 * Whether or not to show the Identifier field
	 */
	static final boolean SHOW_IDENTIFIER = false;

	/**
	 * Identifier
	 */
	private IdentifierLabelTextField _tfIdentifier;
	
	/**
	 * Class
	 */
	private DataElementTextField _tfClass;

    /**
     * IsVisible/parameters
     */
    private IsVisibleParametersPanel _isVisibleParametersPanel;
	
	/**
	 * Type
	 */
	private DataElementComboBox _cbType;

	/**
	 * ItemType Panel
	 */
	private ItemTypePanel _itemPanel;
	
    /**
     * Metadata Panel
     */
    private MetadataPanel _metadataPanel;

	
    /**
	 * Default Constructor
	 */
	public LearningObjectEditorPanel() {

	}
	
	/**
	 * Set up the view
	 */
	protected void setupView() {
		super.setupView();

		RelativeLayoutManager layoutManager = getRelativeLayoutManager();
		
		// Field Panel
		SGLayout fieldPanelLayout = new SGLayout(SHOW_IDENTIFIER ? 4 : 3, 1, 0, 5);
		JPanel fieldPanel = new JPanel(fieldPanelLayout);
		fieldPanel.setOpaque(false);
		layoutManager.addFromLeftToRightEdges(fieldPanel, "fieldPanel", TOPPANEL_ID,
	            RelativeLayoutManager.BOTTOM, 10, 10);
		
		// Identifier Label & Field
		if(SHOW_IDENTIFIER) {
			_tfIdentifier = new IdentifierLabelTextField();
			LabelComponentPanel panelIdentifier = new LabelComponentPanel("Identifier:", _tfIdentifier, 0.3, 0);
			fieldPanel.add(panelIdentifier);
		}

		// Class
		_tfClass = new DataElementTextField();
		LabelComponentPanel panelClass = new LabelComponentPanel("Class:", _tfClass, 0.3, 0);
		fieldPanel.add(panelClass);

		// Sub-panel
		_isVisibleParametersPanel = new IsVisibleParametersPanel();
	    fieldPanel.add(_isVisibleParametersPanel);
		
		// Type
		_cbType = new DataElementComboBox(new String[] {"", "knowledge-object", "tool-object", "test-object" });
		LabelComponentPanel panelType = new LabelComponentPanel("Type:", _cbType, 0.3, 1);
		fieldPanel.add(panelType);

		// Resource Panel
		ComponentHiderLabelPanel hider = new ComponentHiderLabelPanel("Resource", RESOURCE_DESCRIPTION);
		layoutManager.addFromLeftToRightEdges(hider, "hider", "fieldPanel", RelativeLayoutManager.BOTTOM, 15, 10);
		_itemPanel = new ItemTypePanel();
		hider.getComponentHiderButton().setComponent(_itemPanel);
		hider.getComponentHiderButton().showComponent(false);
		layoutManager.addFromLeftToRightEdges(_itemPanel, "_itemPanel", "hider", RelativeLayoutManager.BOTTOM, 10, 10);
		
		// Metadata
		_metadataPanel = new MetadataPanel();
		layoutManager.addFromLeftToRightEdges(_metadataPanel, "_metadataPanel", "_itemPanel",
				RelativeLayoutManager.BOTTOM, 15, 10);
	}

	/** 
	 * Over-ride to set sub-panels
	 * @see uk.ac.reload.editor.learningdesign.editor.shared.ILD_EditorPanel#setComponent(uk.ac.reload.editor.learningdesign.datamodel.LD_Component)
	 */
	public void setComponent(DataComponent dataComponent) {
	    super.setComponent(dataComponent);
        
	    // Wrap the LO Element
	    DataElement loElement = dataComponent.getDataElement();
	    
	    // Identifier
	    if(SHOW_IDENTIFIER) {
	        _tfIdentifier.setElement(loElement);
	    }
	    
	    // Class
	    _tfClass.setAttribute(loElement, LD_Core.CLASS, null);

	    // ISVISIBLE
	    _isVisibleParametersPanel.getIsVisibleCheckBox().setAttribute(loElement, LD_Core.ISVISIBLE, true);
		
	    // PARAMETERS
	    _isVisibleParametersPanel.getParametersField().setAttribute(loElement, LD_Core.PARAMETERS, null);

	    // Type
	    _cbType.setAttribute(loElement, LD_Core.TYPE, null);
	    
	    // Item (Resource)
	    _itemPanel.setItemType(((LearningObject)dataComponent).getItemType());
	    
	    // Metadata
        _metadataPanel.setMetadataType(((LearningObject)dataComponent).getMetadataType());
	}
	
    /**
     * Clean up
     */
    public void cleanup() {
        super.cleanup();
        
        if(_itemPanel != null) {
            _itemPanel.cleanup();
        }
        
        if(_metadataPanel != null) {
            _metadataPanel.cleanup();
        }

    }
}
