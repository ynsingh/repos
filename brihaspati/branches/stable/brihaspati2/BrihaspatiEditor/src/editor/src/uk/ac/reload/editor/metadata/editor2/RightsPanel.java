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

package uk.ac.reload.editor.metadata.editor2;

import javax.swing.JPanel;

import uk.ac.reload.dweezil.gui.LabelComponentPanel;
import uk.ac.reload.dweezil.gui.layout.RelativeLayoutManager;
import uk.ac.reload.dweezil.gui.layout.SGLayout;
import uk.ac.reload.editor.datamodel.DataComponent;
import uk.ac.reload.editor.gui.widgets.DataElementComboBox;
import uk.ac.reload.editor.metadata.datamodel.MD_DataModel;
import uk.ac.reload.editor.metadata.datamodel.MD_Rights;


/**
 * Rights Panel
 * 
 * @author Phillip Beauvoir
 * @version $Id: RightsPanel.java,v 1.1 1998/03/26 15:32:14 ynsingh Exp $
 */
public class RightsPanel
extends MD_Panel
{
    
    /**
     * Cost
     */
    private DataElementComboBox _cbCost;
    
    /**
     * Copyright and other restrictions
     */
    private DataElementComboBox _cbCopyright;
    

    /**
     * Default Constructor
     */
    public RightsPanel() {
    }

    /* (non-Javadoc)
     * @see uk.ac.reload.editor.metadata.datamodel.IMD_DataModelHandler#setDataModel(uk.ac.reload.editor.metadata.datamodel.MD_DataModel)
     */
    public void setDataModel(MD_DataModel mdDataModel) {
        super.setDataModel(mdDataModel);
        setComponent(mdDataModel.getMD_Rights());
    }

	/* (non-Javadoc)
	 * @see uk.ac.reload.editor.gui.TitledEditorPanel#setComponent(uk.ac.reload.editor.datamodel.DataComponent)
	 */
	public void setComponent(DataComponent component) {
	    super.setComponent(component);
	    
	    MD_Rights mdRights = (MD_Rights)component;
	    
	    // Cost
		setComboList(_cbCost, mdRights.getCost(), null);
	    
	    // Copyright and other restrictions
		setComboList(_cbCopyright, mdRights.getCopyright(), null);
	}

	/**
	 * Set up the view
	 */
	protected void setupView() {
		super.setupView();
		RelativeLayoutManager layoutManager = getRelativeLayoutManager();
		
		// Field Panel
		SGLayout fieldPanelLayout = new SGLayout(2, 1, 0, 5);
		JPanel fieldPanel = new JPanel(fieldPanelLayout);
		fieldPanel.setOpaque(false);
		layoutManager.addFromLeftToRightEdges(fieldPanel, "fieldPanel", TOPPANEL_ID,
	            RelativeLayoutManager.BOTTOM, 10, 10);
		
		// Cost
		_cbCost = new DataElementComboBox();
		LabelComponentPanel panelCost = new LabelComponentPanel("Cost:", _cbCost, 0.3, 6);
		fieldPanel.add(panelCost);
		
		// Copyright and other restrictions
		_cbCopyright = new DataElementComboBox();
		LabelComponentPanel panelCopyright = new LabelComponentPanel("Copyright and other restrictions:", _cbCopyright, 0.3, 6);
		fieldPanel.add(panelCopyright);
	}
}
