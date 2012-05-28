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

import uk.ac.reload.dweezil.gui.ComponentHiderLabelPanel;
import uk.ac.reload.dweezil.gui.LabelComponentPanel;
import uk.ac.reload.editor.gui.widgets.DataElementTextField;
import uk.ac.reload.editor.learningdesign.datamodel.ItemModelType;

/**
 * A Panel to edit the ItemModelType elements.<br>
 * <br>
 * This panel contains:<br>
 * <br>
 * title<br>
 * item<br>
 * metadata<br>
 * <br>
 * The first version will not show the Metadata button to launch the Metadata Editor.
 * The user needs to see:<br>
 * <br>
 * - A text box for the Title<br>
 * - A text box for the Item's File location.  This will point, via the identifierref attribute,
 * to a Resource in the Manifest<br>
 *
 * @author Phillip Beauvoir
 * @version $Id: ItemModelTypePanel.java,v 1.1 1998/03/26 15:40:32 ynsingh Exp $
 */
public class ItemModelTypePanel
extends JPanel
{
	
    /**
     * The backing ItemModelType
     */
    private ItemModelType _itemModelType;

	/**
	 * The hider for the panel
	 */
    private ComponentHiderLabelPanel _hiderPanel;
	
	/**
	 * Title
	 */
	private DataElementTextField _tfTitle;

	/**
	 * The ItemTypePanel
	 */
	private ItemTypePanel _itemTypePanel;
	
    /**
     * Metadata Panel
     */
    private MetadataPanel _metadataPanel;
	
	
	/**
	 * Constructor
	 * @param isVisible Whether the sub-panel is initially visible or not
	 */
	public ItemModelTypePanel(boolean isVisible) {
	    setupView(isVisible);
	}
	
	/**
	 * Set up the view
	 */
	protected void setupView(boolean isVisible) {
		setOpaque(false);
		setLayout(new BorderLayout(10, 10));
		
		// Sub-panel
		JPanel subPanel = new JPanel(new BorderLayout(10, 10));
		subPanel.setBackground(new Color(240, 240, 254));
		subPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.gray),
		        BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		add(subPanel, BorderLayout.CENTER);

		// ComponentHider Panel to show/hide the sub-panel
		_hiderPanel = new ComponentHiderLabelPanel("", "");
		_hiderPanel.getComponentHiderButton().setComponent(subPanel);
		_hiderPanel.getComponentHiderButton().showComponent(isVisible);
		add(_hiderPanel, BorderLayout.NORTH);

		// Title Field
		_tfTitle = new DataElementTextField();
		LabelComponentPanel panelTitle = new LabelComponentPanel("Title:", _tfTitle, 0.3, 0.0);
		subPanel.add(panelTitle, BorderLayout.NORTH);

		// ItemTypePanel
		_itemTypePanel = new ItemTypePanel();
		subPanel.add(_itemTypePanel, BorderLayout.CENTER);
		
		// Metadata
		_metadataPanel = new MetadataPanel();
		//_metadataPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
		subPanel.add(_metadataPanel, BorderLayout.SOUTH);
	}
	
	/**
	 * Set the ItemModelType sub-component
	 * @param itemModelType
	 */
	public void setItemModelType(ItemModelType itemModelType) {
	    _itemModelType = itemModelType;
		
	    // Title Label
	    String title = itemModelType.getTitle();
	    if(title != null) {
	        _hiderPanel.getTitleLabel().setText("<html>" + title);
	    }
		
	    // Description label
	    String description = itemModelType.getDescription();
	    if(description != null) {
	        _hiderPanel.getDescriptionLabel().setText("<html>" + description);
	    }
		
	    // Title Element
		_tfTitle.setElement(itemModelType.getTitleElement());
		
		// Item Sub-Panel
		_itemTypePanel.setItemType(itemModelType.getItemType());
		
	    // Metadata
	    _metadataPanel.setMetadataType(itemModelType.getMetadataType());
	}
	
	/** 
	 * Clean up
	 */
	public void cleanup() {
	    if(_itemTypePanel != null) {
	        _itemTypePanel.cleanup();
	    }
	    if(_metadataPanel != null) {
	        _metadataPanel.cleanup();
	    }
	}
}
