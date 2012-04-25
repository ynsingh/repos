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

package uk.ac.reload.editor.learningdesign.editor.roles;

import javax.swing.JPanel;

import uk.ac.reload.dweezil.gui.LabelComponentPanel;
import uk.ac.reload.dweezil.gui.layout.RelativeLayoutManager;
import uk.ac.reload.dweezil.gui.layout.SGLayout;
import uk.ac.reload.editor.datamodel.DataComponent;
import uk.ac.reload.editor.datamodel.DataElement;
import uk.ac.reload.editor.gui.IdentifierLabelTextField;
import uk.ac.reload.editor.gui.TitledEditorPanel;
import uk.ac.reload.editor.gui.widgets.DataElementComboBox;
import uk.ac.reload.editor.gui.widgets.DataElementNumberField;
import uk.ac.reload.editor.gui.widgets.DataElementTextField;
import uk.ac.reload.editor.learningdesign.datamodel.components.roles.Role;
import uk.ac.reload.editor.learningdesign.editor.shared.ItemModelTypePanel;
import uk.ac.reload.editor.learningdesign.editor.shared.MetadataPanel;
import uk.ac.reload.moonunit.learningdesign.LD_Core;

/**
 * Role Editor Panel
 *
 * @author Phillip Beauvoir
 * @version $Id: RoleEditorPanel.java,v 1.1 1998/03/26 15:40:32 ynsingh Exp $
 */
public class RoleEditorPanel
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
	 * HREF
	 */
	private DataElementTextField _tfHREF;
	
	/**
	 * Min Persons
	 */
	private DataElementNumberField _tfMinPersons;
	 
	/**
	 * Max Persons
	 */
	private DataElementNumberField _tfMaxPersons;
	
	/**
	 * Create New
	 */
	private DataElementComboBox _cbCreateNew;
	
	/**
	 * Match Persons
	 */
	private DataElementComboBox _cbMatchPersons;
	

	/**
	 * The Information Panel
	 */
	private ItemModelTypePanel _infoPanel;
	
    /**
     * Metadata Panel
     */
    private MetadataPanel _metadataPanel;

	

	/**
	 * Default Constructor
	 */
	public RoleEditorPanel() {
	    super();
	}
	
	/** 
	 * Over-ride to set elements
	 */
	public void setComponent(DataComponent dataComponent) {
	    super.setComponent(dataComponent);
	    
	    Role role = (Role)dataComponent;
	    
	    // Wrap the Role Element
	    DataElement roleElement = dataComponent.getDataElement();
	    
	    // Identifier
	    if(SHOW_IDENTIFIER) {
	        _tfIdentifier.setElement(roleElement);
	    }
	    
	    // HREF
	    _tfHREF.setAttribute(roleElement, LD_Core.HREF, null);
	    
		// MIN PERSONS
		_tfMinPersons.setAttribute(roleElement, LD_Core.MIN_PERSONS, null);
	    
		// MAX PERSONS
		_tfMaxPersons.setAttribute(roleElement, LD_Core.MAX_PERSONS, null);
		
		// CREATE NEW
		_cbCreateNew.setAttribute(roleElement, LD_Core.CREATE_NEW, null);

		// MATCH PERSONS
		_cbMatchPersons.setAttribute(roleElement, LD_Core.MATCH_PERSONS, null);

		// Information Panel
        _infoPanel.setItemModelType(role.getInformationItemModelType());
        
        // Metadata
        _metadataPanel.setMetadataType(role.getMetadataType());
	}
	
	/**
	 * Set up the view
	 */
	protected void setupView() {
		super.setupView();
		
		RelativeLayoutManager layoutManager = getRelativeLayoutManager();
		
		// Field Panel
		SGLayout fieldPanelLayout = new SGLayout(SHOW_IDENTIFIER ? 6 : 5, 1, 0, 5);
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

		// HREF
		_tfHREF = new DataElementTextField();
		LabelComponentPanel panelHREF = new LabelComponentPanel("HREF:", _tfHREF, 0.3, 0);
		fieldPanel.add(panelHREF);
		
		// MIN PERSONS
		_tfMinPersons = new DataElementNumberField(4);
		LabelComponentPanel panelMinPersons = new LabelComponentPanel("Min Persons:", _tfMinPersons, 0.3, 5);
		fieldPanel.add(panelMinPersons);
		
		// MAX PERSONS
		_tfMaxPersons = new DataElementNumberField(4);
		LabelComponentPanel panelMaxPersons = new LabelComponentPanel("Max Persons:", _tfMaxPersons, 0.3, 5);
		fieldPanel.add(panelMaxPersons);
		
		// CREATE NEW
		_cbCreateNew = new DataElementComboBox(new String[] {"", "allowed", "not-allowed" });
		LabelComponentPanel panelCreateNew = new LabelComponentPanel("Create New:", _cbCreateNew, 0.3, 1.5);
		fieldPanel.add(panelCreateNew);
		
		// MATCH PERSONS
		_cbMatchPersons = new DataElementComboBox(new String[] {"", "exclusively-in-roles", "not-exclusively" });
		LabelComponentPanel panelMatchPersons = new LabelComponentPanel("Match Persons:", _cbMatchPersons, 0.3, 1.5);
		fieldPanel.add(panelMatchPersons);
		
		// INFORMATION PANEL
		_infoPanel = new ItemModelTypePanel(false);
		layoutManager.addFromLeftToRightEdges(_infoPanel, "_infoPanel", "fieldPanel",
				RelativeLayoutManager.BOTTOM, 10, 10);

		// Metadata
		_metadataPanel = new MetadataPanel();
		layoutManager.addFromLeftToRightEdges(_metadataPanel, "_metadataPanel", "_infoPanel",
				RelativeLayoutManager.BOTTOM, 20, 10);
	}

    /**
     * Clean up
     */
    public void cleanup() {
        super.cleanup();
        
        if(_infoPanel != null) {
            _infoPanel.cleanup();
        }
        
        if(_metadataPanel != null) {
            _metadataPanel.cleanup();
        }
    }
	
}
