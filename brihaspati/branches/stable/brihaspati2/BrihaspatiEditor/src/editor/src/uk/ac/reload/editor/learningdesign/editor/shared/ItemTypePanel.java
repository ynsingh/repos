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
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import uk.ac.reload.dweezil.gui.LabelComponentPanel;
import uk.ac.reload.dweezil.gui.layout.SGLayout;
import uk.ac.reload.editor.datamodel.DataElement;
import uk.ac.reload.editor.learningdesign.datamodel.ItemType;
import uk.ac.reload.editor.learningdesign.xml.LearningDesign;
import uk.ac.reload.moonunit.learningdesign.LD_Core;

/**
 * A Panel to edit the Item element in an ItemModelType.<p>
 * <p>
 *
 * @author Phillip Beauvoir
 * @version $Id: ItemTypePanel.java,v 1.1 1998/03/26 15:40:32 ynsingh Exp $
 */
public class ItemTypePanel
extends JPanel
{

	/**
	 * Whether to show the "advanced" elements or not
	 */
	static boolean SHOW_ADVANCED = true;
	
    /**
     * The LearningDesign
     */
    private LearningDesign _learningDesign;
    
    /**
     * The ItemType
     */
    private ItemType _itemType;
    
    /**
     * Resource Selector
     */
    private LD_ResourceSelector _resourceSelector;
    
    /**
     * IsVisible/parameters
     */
    private IsVisibleParametersPanel _isVisibleParametersPanel;
    
    /**
     * Metadata
     */
    private MetadataPanel _metadataPanel;

    /**
	 * Constructor
	 * 
	 * @param showTextPanel Whether to show the Text panel or not
	 */
	public ItemTypePanel() {
		setLayout(new BorderLayout());
		setBackground(new Color(250, 250, 254));
		
		setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.gray),
		        BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		
		// Field Panel
		SGLayout fieldPanelLayout = new SGLayout(SHOW_ADVANCED ? 2 : 1, 1, 0, 5);
		JPanel fieldPanel = new JPanel(fieldPanelLayout);
		fieldPanel.setOpaque(false);
		add(fieldPanel, BorderLayout.CENTER);

		// RESOURCE
		_resourceSelector = new LD_ResourceSelector();
		LabelComponentPanel panelItem = new LabelComponentPanel("Resource:", _resourceSelector,	0.3, 0.0);
		fieldPanel.add(panelItem);

		if(SHOW_ADVANCED) {
		    // Sub-panel
		    _isVisibleParametersPanel = new IsVisibleParametersPanel();
		    fieldPanel.add(_isVisibleParametersPanel);
		}
		
		_metadataPanel = new MetadataPanel();
		_metadataPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
		add(_metadataPanel, BorderLayout.SOUTH);
	}
	
	/**
	 * Set the ItemType
	 * @param itemType The ItemType
	 */
	public void setItemType(ItemType itemType) {
	    _itemType = itemType;
	    
	    DataElement itemElement = itemType.getItemElement();
	    
	    // ITEM RESOURCE
	    _resourceSelector.setElement(itemElement);
	    
		if(SHOW_ADVANCED) {
		    // ISVISIBLE
		    _isVisibleParametersPanel.getIsVisibleCheckBox().setAttribute(itemElement, LD_Core.ISVISIBLE, true);
			// PARAMETERS
		    _isVisibleParametersPanel.getParametersField().setAttribute(itemElement, LD_Core.PARAMETERS, null);
		}
	    
	    // Metadata
	    _metadataPanel.setMetadataType(itemType.getMetadataType());
	}

	/** 
	 * Clean up
	 */
	public void cleanup() {
	    if(_resourceSelector != null) {
	        _resourceSelector.cleanup();
	    }
	    
	    if(_metadataPanel != null) {
	        _metadataPanel.cleanup();
	    }
	    
	    _learningDesign = null;
	    _itemType = null;
	}
	
}
