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

package uk.ac.reload.editor.learningdesign.editor.activities;

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
import uk.ac.reload.editor.gui.widgets.DataElementNumberField;
import uk.ac.reload.editor.learningdesign.datamodel.components.activities.ActivityStructure;
import uk.ac.reload.editor.learningdesign.editor.shared.ItemModelTypePanel;
import uk.ac.reload.editor.learningdesign.editor.shared.MetadataPanel;
import uk.ac.reload.moonunit.learningdesign.LD_Core;

/**
 * Activity Structure Editor Panel
 *
 * @author Phillip Beauvoir
 * @version $Id: ActivityStructureEditorPanel.java,v 1.1 1998/03/26 15:40:32 ynsingh Exp $
 */
public class ActivityStructureEditorPanel
extends TitledEditorPanel
{
	/**
	 * Whether or not to show the Identifier field
	 */
	static final boolean SHOW_IDENTIFIER = false;

	/**
	 * Identifier
	 */
	private IdentifierLabelTextField _tfIdentifier;
	
	/**
	 * Number to select
	 */
	private DataElementNumberField _tfNumber;
	
	/**
	 * Sort
	 */
	private DataElementComboBox _cbSort;
	
	/**
	 * Structure type
	 */
	private DataElementComboBox _cbStructureType;
	
	/**
	 * Information
	 */
	private ItemModelTypePanel _infoPanel;
	
	/**
	 * Environment List
	 */
	protected ActivityEnvironmentsListPanel _envListPanel;

	/**
	 * Activity List
	 */
	private ActivityStructureListPanel _activityListPanel;
	
    /**
     * Metadata Panel
     */
    private MetadataPanel _metadataPanel;

	

    /**
	 * Default Constructor
	 */
	public ActivityStructureEditorPanel() {
	    super();
	}
	
	/** 
	 * Over-ride to set sub-panels
	 * @see uk.ac.reload.editor.learningdesign.editor.shared.ILD_EditorPanel#setComponent(uk.ac.reload.editor.learningdesign.datamodel.LD_Component)
	 */
	public void setComponent(DataComponent dataComponent) {
	    super.setComponent(dataComponent);
	    
	    ActivityStructure as = (ActivityStructure)dataComponent;
	    
	    // Wrap the Activity Element
	    DataElement activityElement = dataComponent.getDataElement();
	    
	    // Identifier
	    if(SHOW_IDENTIFIER) {
	        _tfIdentifier.setElement(activityElement);
	    }
	    
	    // Number to select
	    _tfNumber.setAttribute(activityElement, LD_Core.NUMBER_TO_SELECT, null);
	    
	    // Sort
	    _cbSort.setAttribute(activityElement, LD_Core.SORT, null);
	    
	    // Structure type
	    _cbStructureType.setAttribute(activityElement, LD_Core.STRUCTURE_TYPE, null);

	    // Information Panel
	    _infoPanel.setItemModelType(as.getInformationItemModelType());
	    
	    // Environments Tree
	    _envListPanel.setActivity(as);

	    // Activity List
	    _activityListPanel.setActivity(as);
	    
        // Metadata
        _metadataPanel.setMetadataType(as.getMetadataType());
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
		
		// Number to Select
		_tfNumber = new DataElementNumberField();
		LabelComponentPanel panelNumber = new LabelComponentPanel("Number to select:", _tfNumber, 0.3, 5);
		fieldPanel.add(panelNumber);
		
		// Sort
		_cbSort = new DataElementComboBox(new String[] {"", "as-is", "visibility-order" });
		LabelComponentPanel panelSort = new LabelComponentPanel("Sort:", _cbSort, 0.3, 2);
		fieldPanel.add(panelSort);

		// Structure type
		_cbStructureType = new DataElementComboBox(new String[] {"", "sequence", "selection" });
		LabelComponentPanel panelStructureType = new LabelComponentPanel("Structure:", _cbStructureType, 0.3, 2);
		fieldPanel.add(panelStructureType);
		
		// Information
		_infoPanel = new ItemModelTypePanel(false);
		layoutManager.addFromLeftToRightEdges(_infoPanel, "_infoPanel", "fieldPanel",
				RelativeLayoutManager.BOTTOM, 15, 10);
		
		// Environments List
		_envListPanel = addEnvironmentListPanel("_infoPanel");
		
		// Activity List
		_activityListPanel = addActivityStructureListPanel("envlistPanel");
		
		// Metadata
		_metadataPanel = new MetadataPanel();
		layoutManager.addFromLeftToRightEdges(_metadataPanel, "_metadataPanel", "actlistPanel",
				RelativeLayoutManager.BOTTOM, 20, 10);
	}

	/**
	 * Add an Environment List Panel
	 * @return ActivityEnvironmentsListPanel
	 */
	protected ActivityEnvironmentsListPanel addEnvironmentListPanel(String parentAnchor) {
		RelativeLayoutManager layoutManager = getRelativeLayoutManager();

		ComponentHiderLabelPanel envpicker_hider = new ComponentHiderLabelPanel("Environments",
		        "Add Environments that this Activity Structure references.");
		layoutManager.addFromLeftToRightEdges(envpicker_hider, "envpicker_hider", parentAnchor,
				RelativeLayoutManager.BOTTOM, 15, 10);

		ActivityEnvironmentsListPanel listPanel = new ActivityEnvironmentsListPanel();
		layoutManager.addFromLeftToRightEdges(listPanel, "envlistPanel", "envpicker_hider",
				RelativeLayoutManager.BOTTOM, 5, 10);
		
		envpicker_hider.getComponentHiderButton().setComponent(listPanel);
		envpicker_hider.getComponentHiderButton().showComponent(false);
	    
		return listPanel;
	}

	/**
	 * Add an Activity List Panel
	 * @return ActivityStructureListPanel
	 */
	protected ActivityStructureListPanel addActivityStructureListPanel(String parentAnchor) {
		RelativeLayoutManager layoutManager = getRelativeLayoutManager();

		ComponentHiderLabelPanel picker_hider = new ComponentHiderLabelPanel("Activities",
		        "Add Activities that this Activity Structure references.");
		layoutManager.addFromLeftToRightEdges(picker_hider, "actpicker_hider", parentAnchor,
				RelativeLayoutManager.BOTTOM, 20, 10);

		ActivityStructureListPanel listPanel = new ActivityStructureListPanel();
		layoutManager.addFromLeftToRightEdges(listPanel, "actlistPanel", "actpicker_hider",
				RelativeLayoutManager.BOTTOM, 5, 10);
		
		picker_hider.getComponentHiderButton().setComponent(listPanel);
		picker_hider.getComponentHiderButton().showComponent(false);
	    
		return listPanel;
	}
	
	/**
	 * Clean up
	 */
	public void cleanup() {
	    super.cleanup();
	    
	    if(_infoPanel != null) {
	        _infoPanel.cleanup();
	    }
	    
	    if(_envListPanel != null) {
	        _envListPanel.cleanup();
	    }
	    
	    if(_activityListPanel != null) {
	        _activityListPanel.cleanup();
	    }
	    
        if(_metadataPanel != null) {
            _metadataPanel.cleanup();
        }

	}

}
