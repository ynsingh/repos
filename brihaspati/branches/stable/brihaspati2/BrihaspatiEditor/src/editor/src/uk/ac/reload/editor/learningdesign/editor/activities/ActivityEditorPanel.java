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
import uk.ac.reload.editor.learningdesign.datamodel.components.activities.Activity;
import uk.ac.reload.editor.learningdesign.editor.shared.IsVisibleParametersPanel;
import uk.ac.reload.moonunit.learningdesign.LD_Core;

/**
 * Root Panel for Activity Editor Components
 *
 * @author Phillip Beauvoir
 * @version $Id: ActivityEditorPanel.java,v 1.1 1998/03/26 15:40:32 ynsingh Exp $
 */
public abstract class ActivityEditorPanel
extends TitledEditorPanel
{
	/**
	 * Whether or not to show the Identifier field
	 */
	static final boolean SHOW_IDENTIFIER = false;

	/**
	 * Environment List
	 */
	protected ActivityEnvironmentsListPanel _envListPanel;

	/**
	 * Identifier
	 */
	protected IdentifierLabelTextField _tfIdentifier;
	
    /**
     * IsVisible/parameters
     */
	protected IsVisibleParametersPanel _isVisibleParametersPanel;
    
    /**
	 * Default Constructor
	 */
	protected ActivityEditorPanel() {
	}
	
	/** 
	 * Over-ride to set sub-panels
	 * @see uk.ac.reload.editor.learningdesign.editor.shared.ILD_EditorPanel#setComponent(uk.ac.reload.editor.learningdesign.datamodel.LD_Component)
	 */
	public void setComponent(DataComponent dataComponent) {
	    super.setComponent(dataComponent);

	    // Wrap the Activity Element
	    DataElement activityElement = dataComponent.getDataElement();
	    
	    // Identifier
	    if(SHOW_IDENTIFIER) {
	        _tfIdentifier.setElement(activityElement);
	    }
	    
	    // ISVISIBLE
	    _isVisibleParametersPanel.getIsVisibleCheckBox().setAttribute(activityElement, LD_Core.ISVISIBLE, true);

	    // PARAMETERS
	    _isVisibleParametersPanel.getParametersField().setAttribute(activityElement, LD_Core.PARAMETERS, null);
	    
	    // Environments Tree
	    _envListPanel.setActivity((Activity)dataComponent);
	}
	
	/**
	 * Set up the view
	 */
	protected void setupView() {
		super.setupView();

		RelativeLayoutManager layoutManager = getRelativeLayoutManager();
		
		// Field Panel
		SGLayout fieldPanelLayout = new SGLayout(SHOW_IDENTIFIER ? 2 : 1, 1, 0, 5);
		JPanel fieldPanel = new JPanel(fieldPanelLayout);
		fieldPanel.setOpaque(false);
		layoutManager.addFromLeftToRightEdges(fieldPanel, "fieldPanel", TOPPANEL_ID,
	            RelativeLayoutManager.BOTTOM, 10, 10);

		// Identifier
		if(SHOW_IDENTIFIER) {
		    _tfIdentifier = new IdentifierLabelTextField();
		    LabelComponentPanel panelIdentifier = new LabelComponentPanel("Identifier:", _tfIdentifier, 0.3, 0);
		    fieldPanel.add(panelIdentifier);
		}
		
	    // Sub-panel
		_isVisibleParametersPanel = new IsVisibleParametersPanel();
	    fieldPanel.add(_isVisibleParametersPanel);
	}
	
	/**
	 * Add an Environment List Panel
	 * @return ActivityEnvironmentsListPanel
	 */
	protected ActivityEnvironmentsListPanel addEnvironmentListPanel(String parentAnchor) {
		RelativeLayoutManager layoutManager = getRelativeLayoutManager();

		ComponentHiderLabelPanel envpicker_hider = new ComponentHiderLabelPanel("Environments",
		        "Add Environments that this Activity references.");
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
     * Clean up
     */
    public void cleanup() {
        super.cleanup();
        
        if(_envListPanel != null) {
            _envListPanel.cleanup();
        }
    }
}
