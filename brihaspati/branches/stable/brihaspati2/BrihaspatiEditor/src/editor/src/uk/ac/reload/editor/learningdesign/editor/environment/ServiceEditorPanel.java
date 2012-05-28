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

import javax.swing.JPanel;

import org.jdom.Element;

import uk.ac.reload.dweezil.gui.LabelComponentPanel;
import uk.ac.reload.dweezil.gui.layout.RelativeLayoutManager;
import uk.ac.reload.dweezil.gui.layout.SGLayout;
import uk.ac.reload.editor.datamodel.DataComponent;
import uk.ac.reload.editor.datamodel.DataElement;
import uk.ac.reload.editor.gui.IdentifierLabelTextField;
import uk.ac.reload.editor.gui.TitledEditorPanel;
import uk.ac.reload.editor.gui.widgets.DataElementTextField;
import uk.ac.reload.editor.learningdesign.editor.shared.IsVisibleParametersPanel;
import uk.ac.reload.moonunit.learningdesign.LD_Core;

/**
 * Root Panel for Service Editor Component
 *
 * @author Phillip Beauvoir
 * @version $Id: ServiceEditorPanel.java,v 1.1 1998/03/26 15:40:32 ynsingh Exp $
 */
public abstract class ServiceEditorPanel
extends TitledEditorPanel
{
	/**
	 * Whether or not to show the Identifier field
	 */
	static final boolean SHOW_IDENTIFIER = false;

	/**
	 * Identifier
	 */
	protected IdentifierLabelTextField _tfIdentifier;
	
    /**
     * IsVisible/parameters
     */
	protected IsVisibleParametersPanel _isVisibleParametersPanel;
	
	/**
	 * Class
	 */
	protected DataElementTextField _tfClass;
	
    
    /**
	 * Default Constructor
	 */
	protected ServiceEditorPanel() {
	    super();
	}
	
	/** 
	 * Over-ride to set elements
	 */
	public void setComponent(DataComponent dataComponent) {
	    super.setComponent(dataComponent);
	    
	    // Wrap the Service Element
	    Element element = dataComponent.getElement().getParent();
	    DataElement serviceElement = new DataElement(dataComponent.getDataModel(), element);
	    
	    // Identifier
	    if(SHOW_IDENTIFIER) {
	        _tfIdentifier.setElement(serviceElement);
	    }

	    // Class
	    _tfClass.setAttribute(serviceElement, LD_Core.CLASS, null);

	    // ISVISIBLE
	    _isVisibleParametersPanel.getIsVisibleCheckBox().setAttribute(serviceElement, LD_Core.ISVISIBLE, true);
		
	    // PARAMETERS
	    _isVisibleParametersPanel.getParametersField().setAttribute(serviceElement, LD_Core.PARAMETERS, null);
	}
	
	/**
	 * Set up the view
	 */
	protected void setupView() {
		super.setupView();

		RelativeLayoutManager layoutManager = getRelativeLayoutManager();
		
		// Field Panel
		SGLayout fieldPanelLayout = new SGLayout(SHOW_IDENTIFIER ? 3 : 2, 1, 0, 5);
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
		
		// Class
		_tfClass = new DataElementTextField();
		LabelComponentPanel panelClass = new LabelComponentPanel("Class:", _tfClass, 0.3, 0);
		fieldPanel.add(panelClass);
		
	    // Sub-panel
		_isVisibleParametersPanel = new IsVisibleParametersPanel();
	    fieldPanel.add(_isVisibleParametersPanel);
	}
}
