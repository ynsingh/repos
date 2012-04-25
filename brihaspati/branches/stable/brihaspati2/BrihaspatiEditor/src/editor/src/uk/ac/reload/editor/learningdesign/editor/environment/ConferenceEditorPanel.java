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

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import uk.ac.reload.dweezil.gui.ComponentHiderLabelPanel;
import uk.ac.reload.dweezil.gui.LabelComponentPanel;
import uk.ac.reload.dweezil.gui.layout.RelativeLayoutManager;
import uk.ac.reload.editor.datamodel.DataComponent;
import uk.ac.reload.editor.datamodel.DataElement;
import uk.ac.reload.editor.gui.widgets.DataElementComboBox;
import uk.ac.reload.editor.learningdesign.datamodel.components.environments.Conference;
import uk.ac.reload.editor.learningdesign.editor.shared.ItemTypePanel;
import uk.ac.reload.editor.learningdesign.editor.shared.MetadataPanel;
import uk.ac.reload.moonunit.learningdesign.LD_Core;


/**
 * Edit the Conference Component
 *
 * @author Phillip Beauvoir
 * @version $Id: ConferenceEditorPanel.java,v 1.1 1998/03/26 15:40:32 ynsingh Exp $
 */
public class ConferenceEditorPanel
extends ServiceEditorPanel
{
    static final String RESOURCE_DESCRIPTION = "Reference to a Resource.";

    /**
     * Type
     */
    private DataElementComboBox _cbType;
    
    /**
	 * ItemType Panel
	 */
	private ItemTypePanel _itemPanel;
	
	/**
	 * ConferenceRoleTable
	 */
	private ConferenceRoleTable _rolePicker;
	
    /**
     * Metadata Panel
     */
    private MetadataPanel _metadataPanel;


	/**
	 * Default Constructor
	 */
	public ConferenceEditorPanel() {
	    super();
	}
	
	/** 
	 * Over-ride to set elements
	 */
	public void setComponent(DataComponent dataComponent) {
	    super.setComponent(dataComponent);
	    
	    // Wrap the conference-type Attribute
	    DataElement typeElement = dataComponent.getDataElement();
	    _cbType.setAttribute(typeElement, LD_Core.CONFERENCE_TYPE, "synchronous");
	    
	    // We should have got a Conference component
	    Conference conference = (Conference)dataComponent;

	    // Item (Resource)
	    _itemPanel.setItemType(conference.getItemType());

	    // Tell the Role Selector Table
	    _rolePicker.setConference(conference);
	    
        // Metadata
        _metadataPanel.setMetadataType(conference.getMetadataType());
	}

	/**
	 * Set up the view
	 */
	protected void setupView() {
		super.setupView();

		RelativeLayoutManager layoutManager = getRelativeLayoutManager();

		// Type
		_cbType = new DataElementComboBox(new String[] {"synchronous", "asynchronous", "announcement" });
		LabelComponentPanel panelType = new LabelComponentPanel("Type:", _cbType, 0.3, 1);
		layoutManager.addFromLeftToRightEdges(panelType, "panelType", "fieldPanel",
				RelativeLayoutManager.BOTTOM, 5, 10);
		
		// Resource Panel
		ComponentHiderLabelPanel hider = new ComponentHiderLabelPanel("Resource", RESOURCE_DESCRIPTION);
		layoutManager.addFromLeftToRightEdges(hider, "hider", "panelType",
				RelativeLayoutManager.BOTTOM, 15, 10);
		_itemPanel = new ItemTypePanel();
		hider.getComponentHiderButton().setComponent(_itemPanel);
		hider.getComponentHiderButton().showComponent(false);
		layoutManager.addFromLeftToRightEdges(_itemPanel, "_itemPanel", "hider", RelativeLayoutManager.BOTTOM, 10, 10);

		// Roles Panel
		ComponentHiderLabelPanel hider2 = new ComponentHiderLabelPanel("Roles", "Add Conference Roles.");
		layoutManager.addFromLeftToRightEdges(hider2, "hider2", "_itemPanel",
				RelativeLayoutManager.BOTTOM, 15, 10);
		_rolePicker = new ConferenceRoleTable();
		JScrollPane sp1 = new JScrollPane(_rolePicker);
		sp1.setPreferredSize(new Dimension(0, 200));
		sp1.setBorder(BorderFactory.createLineBorder(Color.GRAY));

		JPanel panel1 = new JPanel(new BorderLayout());
		panel1.add(sp1);
		hider2.getComponentHiderButton().setComponent(panel1);
		hider2.getComponentHiderButton().showComponent(false);
		layoutManager.addFromLeftToRightEdges(panel1, "panel1", "hider2",
				RelativeLayoutManager.BOTTOM, 5, 10);
		
		// Metadata
		_metadataPanel = new MetadataPanel();
		layoutManager.addFromLeftToRightEdges(_metadataPanel, "_metadataPanel", "panel1",
				RelativeLayoutManager.BOTTOM, 20, 10);

	}
	
    /**
     * Clean up
     */
    public void cleanup() {
        super.cleanup();
        
        if(_itemPanel != null) {
            _itemPanel.cleanup();
        }
        
        if(_rolePicker != null) {
            _rolePicker.cleanup();
        }
        
        if(_metadataPanel != null) {
            _metadataPanel.cleanup();
        }
    }
	
}
