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

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import uk.ac.reload.dweezil.gui.ComponentHiderLabelPanel;
import uk.ac.reload.dweezil.gui.layout.RelativeLayoutManager;
import uk.ac.reload.editor.datamodel.DataComponent;
import uk.ac.reload.editor.learningdesign.datamodel.components.activities.SupportActivity;
import uk.ac.reload.editor.learningdesign.editor.shared.ItemModelTypePanel;
import uk.ac.reload.editor.learningdesign.editor.shared.MetadataPanel;


/**
 * Support Activity Editor Panel
 *
 * @author Phillip Beauvoir
 * @version $Id: SupportActivityEditorPanel.java,v 1.1 1998/03/26 15:40:32 ynsingh Exp $
 */
public class SupportActivityEditorPanel
extends ActivityEditorPanel
{
	
	/**
	 * Description panel
	 */
	private ItemModelTypePanel _descPanel;

	/**
	 * CompleteActivityPanel
	 */
	private ActivityCompletionPanel _completeActivityPanel;
	
	
	/**
	 * Role Picker
	 */
	private SupportActivityRoleSelectorTree _roleSelectorTree;
	
    /**
     * Metadata Panel
     */
    private MetadataPanel _metadataPanel;

	

	/**
	 * Default Constructor
	 */
	public SupportActivityEditorPanel() {
	    super();
	}
	
	/** 
	 * Over-ride to set sub-panels
	 * @see uk.ac.reload.editor.learningdesign.editor.shared.ILD_EditorPanel#setComponent(uk.ac.reload.editor.learningdesign.datamodel.LD_Component)
	 */
	public void setComponent(DataComponent dataComponent) {
	    super.setComponent(dataComponent);

	    SupportActivity sa = (SupportActivity)dataComponent;
	    
	    // Description
	    _descPanel.setItemModelType(sa.getDescriptionItemModelType());
	    
	    // Completion Panel
	    _completeActivityPanel.setComponent(dataComponent);

	    // Role Selector tree
	    _roleSelectorTree.setActivity(sa);
	    
        // Metadata
        _metadataPanel.setMetadataType(sa.getMetadataType());

	}

	/**
	 * Set up the view
	 */
	protected void setupView() {
		super.setupView();

		RelativeLayoutManager layoutManager = getRelativeLayoutManager();
		
		// Activity Description
		_descPanel = new ItemModelTypePanel(false);
		layoutManager.addFromLeftToRightEdges(_descPanel, "_descPanel", "fieldPanel",
		        RelativeLayoutManager.BOTTOM, 15, 10);
		
		// Complete Activity - Choice of "User Choice" or "Time Limit" & Completion Feedback Panel
		_completeActivityPanel = new ActivityCompletionPanel(false);
		layoutManager.addFromLeftToRightEdges(_completeActivityPanel, "_completeActivityPanel", "_descPanel",
				RelativeLayoutManager.BOTTOM, 15, 10);

		// Environment Refs
		_envListPanel = addEnvironmentListPanel("_completeActivityPanel");

		// Role Refs
		_roleSelectorTree = addRoleSelectorTree("envlistPanel");
		
		// Metadata
		_metadataPanel = new MetadataPanel();
		layoutManager.addFromLeftToRightEdges(_metadataPanel, "_metadataPanel", "rolePicker",
				RelativeLayoutManager.BOTTOM, 20, 10);

	}
	
	/**
	 * Add a Role Picker
	 * @return RoleSelectorTree
	 */
	protected SupportActivityRoleSelectorTree addRoleSelectorTree(String parentAnchor) {
		RelativeLayoutManager layoutManager = getRelativeLayoutManager();

		ComponentHiderLabelPanel rolepicker_hider = new ComponentHiderLabelPanel("Roles",
		        "Add Roles that this Activity references.");
		layoutManager.addFromLeftToRightEdges(rolepicker_hider, "rolepicker_hider", parentAnchor,
				RelativeLayoutManager.BOTTOM, 20, 10);

		SupportActivityRoleSelectorTree rolePicker = new SupportActivityRoleSelectorTree();
		JScrollPane sp1 = new JScrollPane(rolePicker);
		sp1.setPreferredSize(new Dimension(0, 200));
		sp1.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		JPanel panel1 = new JPanel(new BorderLayout());
		panel1.add(sp1);
		
		layoutManager.addFromLeftToRightEdges(panel1, "rolePicker", "rolepicker_hider",
				RelativeLayoutManager.BOTTOM, 5, 10);
		
		rolepicker_hider.getComponentHiderButton().setComponent(panel1);
		rolepicker_hider.getComponentHiderButton().showComponent(false);
	    
		return rolePicker;
	}
	
    /**
     * Clean up
     */
    public void cleanup() {
        super.cleanup();
        
        if(_descPanel != null) {
            _descPanel.cleanup();
        }
        
        if(_completeActivityPanel != null) {
            _completeActivityPanel.cleanup();
        }
        
        if(_roleSelectorTree != null) {
            _roleSelectorTree.cleanup();
        }
        
        if(_metadataPanel != null) {
            _metadataPanel.cleanup();
        }

    }
}
