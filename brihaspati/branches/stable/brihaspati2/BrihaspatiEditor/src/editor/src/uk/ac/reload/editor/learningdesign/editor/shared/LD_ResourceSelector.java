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

package uk.ac.reload.editor.learningdesign.editor.shared;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.*;

import org.jdom.Element;

import uk.ac.reload.dweezil.gui.ComboBoxTipRenderer;
import uk.ac.reload.dweezil.gui.FlatComboBox;
import uk.ac.reload.dweezil.util.DweezilUIManager;
import uk.ac.reload.dweezil.util.NativeLauncher;
import uk.ac.reload.editor.EditorFrame;
import uk.ac.reload.editor.IIcons;
import uk.ac.reload.editor.datamodel.DataComponent;
import uk.ac.reload.editor.datamodel.DataElement;
import uk.ac.reload.editor.datamodel.IDataModelListener;
import uk.ac.reload.editor.learningdesign.datamodel.LD_DataModel;
import uk.ac.reload.editor.learningdesign.datamodel.resources.LD_Resource;
import uk.ac.reload.editor.learningdesign.xml.LearningDesign;
import uk.ac.reload.moonunit.learningdesign.LD_Core;


/**
 * Widget to select LD Resources
 * 
 * @author Phillip Beauvoir
 * @version $Id: LD_ResourceSelector.java,v 1.1 1998/03/26 15:40:32 ynsingh Exp $
 */
public class LD_ResourceSelector
extends JPanel
implements ItemListener, IIcons, IDataModelListener
{
	/**
     * The LD_DataModel
     */
    private LD_DataModel _ldDataModel;

    /**
     * The wrapped element
     */
    private DataElement _dataElement;
    
    /**
     * Resources
     */
    private LD_Resource[] _ldResources;
    
    /**
     * The Combo Box
     */
    private JComboBox _comboBox;
    
    /**
     * The View File Button
     */
    private JButton _buttonFileView;

    
    /**
     * Constructor
     */
    public LD_ResourceSelector() {
        setup();
    }
    
	/**
	 * Setup the components.<p>
	 * This will:<p>
	 * 1. Add the combo box to the CENTER<p>
	 * 2. Add the file view button to the EAST<p>
	 * 3. Call setupFileViewAction() to add the ActionListener to the file view button
	 */
	protected void setup() {
	    // Layout
	    setLayout(new BorderLayout());
	    
		// Add Combo Box
		add(getComboBox(), BorderLayout.CENTER);
		
		// Add File View Button
		add(getFileViewButton(), BorderLayout.EAST);
		
		// Set up the view file button's Action behaviour
		setupFileViewAction();
		
		getComboBox().addItemListener(this);
	}

	/**
	 * Set the Element to be edited
	 * @param dataElement The wrapped element - either an item or dependency element
	 */
	public void setElement(DataElement dataElement) {
	    _dataElement = dataElement;
	    
	    // First time
	    if(_ldDataModel == null) {
	        _ldDataModel = (LD_DataModel)_dataElement.getDataModel();
	        getComboBox().setModel(new DefaultComboBoxModel(getLD_Resources()));
	        _ldDataModel.addIDataModelListener(this);
	    }
	    
	    // Set selected Item
	    setSelectedItem();
	}
	
	/**
	 * Update the Combo box
	 */
	private void updateComboBox() {
	    getComboBox().removeItemListener(this);
        
	    // Reset combo box and reselect
        _ldResources = null;
	    getComboBox().setModel(new DefaultComboBoxModel(getLD_Resources()));
	    
	    // Set selected Item
	    setSelectedItem();

	    getComboBox().addItemListener(this);
	}
	
	/**
	 * Set the selected item in the combo box according to the Item's identifierref
	 */
	private void setSelectedItem() {
	    LD_Resource selection = null;
	    
	    Element element = _dataElement.getElement();
	    if(element != null) {
	        String id_ref = element.getAttributeValue(LD_Core.IDENTIFIER_REF);
	        selection = _ldDataModel.getResources().getLD_Resource(id_ref);
	    }

	    getComboBox().removeItemListener(this);
        getComboBox().setSelectedItem(selection);
        getComboBox().addItemListener(this);
	}
    
	/**
	 * @return the Combo Box.
	 * This can be over-ridden by sub-classes to offer an alternative widget.
	 */
	public JComboBox getComboBox() {
		if(_comboBox == null) {
		    _comboBox = new FlatComboBox();
		    _comboBox.setRenderer(new ComboBoxTipRenderer());  // show tooltips
		}
		return _comboBox;
	}
	
    /**
	 * @return the JButton for the "View File" Action
	 * This can be over-ridden by sub-classes to offer an alternative button.
	 */
	public JButton getFileViewButton() {
		if(_buttonFileView == null) {
			Icon icon = DweezilUIManager.getIcon(ICON_VIEWFILE);
		    _buttonFileView = new JButton(icon);
		    _buttonFileView.setPreferredSize(new Dimension(24, 0));
		    _buttonFileView.setToolTipText("View File");
		}
		return _buttonFileView;
	}
	
	/**
     * Set up the view file button's Action behaviour
     */
    protected void setupFileViewAction() {
		if(getFileViewButton() != null) {
		    getFileViewButton().addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				    if(_dataElement == null || _dataElement.getElement() == null) {
				        return;
				    }
				    
				    LearningDesign ld = _ldDataModel.getLearningDesign();
				    String url = ld.getAbsoluteURL(_dataElement.getElement());
				    if(url != null) {
				        NativeLauncher.launchURL(url);
				    }
				    else {
			            JOptionPane.showMessageDialog(EditorFrame.getInstance(),
			    				"The Resource's URL does not exist!",
			    				"View File",
			    				JOptionPane.WARNING_MESSAGE);

				    }
				}
			});
		}
    }

    /**
     * @return An array of LD_Resources.  The first one will be a blank dummy Resource
     * for "no Resource".
     */
    protected LD_Resource[] getLD_Resources() {
	    if(_ldResources == null) {
	        DataComponent[] ldResources = _ldDataModel.getResources().getChildren();
	        
	        // Need to add a blank one
	        _ldResources = new LD_Resource[ldResources.length + 1];
	        
	        // The blank Resource
	        _ldResources[0] = new LD_Resource(_ldDataModel, null);
	        
		    System.arraycopy(ldResources, 0, _ldResources, 1, ldResources.length);
	    }
        
        return _ldResources;
    }
    
	/* (non-Javadoc)
     * @see java.awt.event.ItemListener#itemStateChanged(java.awt.event.ItemEvent)
     */
    public void itemStateChanged(ItemEvent e) {
        if(e.getStateChange() == ItemEvent.DESELECTED) {
            return;
        }
        
        Element element = _dataElement.getElement();
        
        // Create if need be
        if(element == null) {
            element = _dataElement.createElement();
        }
        
        LD_Resource ldResource = (LD_Resource)getComboBox().getSelectedItem();
        String id = ldResource.getIdentifier();
        
        // If null selection, then remove attribute
        if(id == null) {
            element.removeAttribute(LD_Core.IDENTIFIER_REF);
        }
        else {
            element.setAttribute(LD_Core.IDENTIFIER_REF, id);
        }
        
        // Notify
        _ldDataModel.getSchemaDocument().changedElement(this, element);
    }
    
    /**
     * Clean up
     */
    public void cleanup() {
        if(_comboBox != null) {
            _comboBox.removeItemListener(this);
        }
        
        if(_ldDataModel != null) {
            _ldDataModel.removeIDataModelListener(this);
        }
        
        _ldDataModel = null;
        _dataElement = null;
        _ldResources = null;
    }

    // ====================== ILD_DataModelListener methods =======================================
    
    /**
     * We heard that a LD_Component has been added to the Data Model
     */
    public void componentAdded(DataComponent component) {
        if(component instanceof LD_Resource) {
            updateComboBox();
        }
    }
    
	/**
     * We heard that a LD_Component has been removed from the Data Model
     */
    public void componentRemoved(DataComponent component) {
        if(component instanceof LD_Resource) {
            updateComboBox();
        }
    }

    public void componentMoved(DataComponent component) {
    }

    /**
	 * A Component was changed in some way (maybe the text or Attribute)
	 */
	public void componentChanged(DataComponent component) {
	}
}
