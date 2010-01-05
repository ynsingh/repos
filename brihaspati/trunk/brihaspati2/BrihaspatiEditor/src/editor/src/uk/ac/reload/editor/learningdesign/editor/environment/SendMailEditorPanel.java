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
import uk.ac.reload.editor.learningdesign.datamodel.components.environments.SendMail;
import uk.ac.reload.editor.learningdesign.editor.shared.MetadataPanel;
import uk.ac.reload.moonunit.learningdesign.LD_Core;


/**
 * Edit the SendMail Component
 *
 * @author Phillip Beauvoir
 * @version $Id: SendMailEditorPanel.java,v 1.1 1998/03/26 15:40:32 ynsingh Exp $
 */
public class SendMailEditorPanel
extends ServiceEditorPanel
{
	
    /**
     * Select
     */
    private DataElementComboBox _cbSelect;
    
    /**
     * RoleSelectorTree
     */
    private SendMailRoleSelectorTree _roleSelectorTree;
    
    /**
     * Metadata Panel
     */
    private MetadataPanel _metadataPanel;

    /**
	 * Default Constructor
	 */
	public SendMailEditorPanel() {
	    super();
	}
	
	/** 
	 * Over-ride to set elements
	 */
	public void setComponent(DataComponent dataComponent) {
	    super.setComponent(dataComponent);
	    
	    // Wrap the select Attribute
	    DataElement selectElement = dataComponent.getDataElement();
	    _cbSelect.setAttribute(selectElement, LD_Core.SELECT, "all-persons-in-role");
	    
	    // Tell the Role Selector Tree
	    _roleSelectorTree.setSendMailComponent((SendMail)dataComponent);
	    
        // Metadata
        _metadataPanel.setMetadataType(((SendMail)dataComponent).getMetadataType());
	}

	/**
	 * Set up the view
	 */
	protected void setupView() {
		super.setupView();

		RelativeLayoutManager layoutManager = getRelativeLayoutManager();

		// Select
		_cbSelect = new DataElementComboBox(new String[] {"all-persons-in-role", "persons-in-role" });
		LabelComponentPanel panelSelect = new LabelComponentPanel("Select:", _cbSelect, 0.3, 1);
		layoutManager.addFromLeftToRightEdges(panelSelect, "panelSelect", "fieldPanel",
				RelativeLayoutManager.BOTTOM, 5, 10);
		
		// Recipients
		ComponentHiderLabelPanel hider = new ComponentHiderLabelPanel("Recipients", "Add Mail Recipients.");
		layoutManager.addFromLeftToRightEdges(hider, "hider", "panelSelect",
				RelativeLayoutManager.BOTTOM, 15, 10);

		_roleSelectorTree = new SendMailRoleSelectorTree();
		JScrollPane sp1 = new JScrollPane(_roleSelectorTree);
		sp1.setPreferredSize(new Dimension(0, 200));
		sp1.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		JPanel panel1 = new JPanel(new BorderLayout());
		panel1.add(sp1);
		hider.getComponentHiderButton().setComponent(panel1);
		hider.getComponentHiderButton().showComponent(false);
		
		layoutManager.addFromLeftToRightEdges(panel1, "rolePicker", "hider",
				RelativeLayoutManager.BOTTOM, 5, 10);
		
		// Metadata
		_metadataPanel = new MetadataPanel();
		layoutManager.addFromLeftToRightEdges(_metadataPanel, "_metadataPanel", "rolePicker",
				RelativeLayoutManager.BOTTOM, 20, 10);

	}

    /**
     * Clean up
     */
    public void cleanup() {
        super.cleanup();
        
        if(_roleSelectorTree != null) {
            _roleSelectorTree.cleanup();
        }
        
        if(_metadataPanel != null) {
            _metadataPanel.cleanup();
        }
    }
}
