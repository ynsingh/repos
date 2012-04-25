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

package uk.ac.reload.editor.learningdesign.editor.resources;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;

import uk.ac.reload.dweezil.gui.GradientPanel;
import uk.ac.reload.dweezil.gui.UIFactory;
import uk.ac.reload.dweezil.util.DweezilUIManager;
import uk.ac.reload.editor.IIcons;
import uk.ac.reload.editor.learningdesign.datamodel.LD_DataModel;

/**
 * Resources Editor Panel
 *
 * @author Phillip Beauvoir
 * @version $Id: LD_ResourcesEditorPanel.java,v 1.1 1998/03/26 15:40:32 ynsingh Exp $
 */
public class LD_ResourcesEditorPanel
extends GradientPanel
implements IIcons
{

    /**
     * The LD_DataModel
     */
    private LD_DataModel _ldDataModel;

    /**
     * Resources Tree Panel
     */
    private LD_ResourcesTreePanel _resourcesTreePanel;
    
    /**
     * Resource Editor Panel
     */
    private LD_ResourceEditor _resourceEditorPanel;
    
    /**
	 * Default Constructor
	 */
	public LD_ResourcesEditorPanel() {
	    super();
	}
	
	/**
	 * Set up the view
	 */
	protected void setupView() {
		super.setupView();
		
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
		
		// Add Top Panel
	    JPanel topPanel = new JPanel(new BorderLayout());
	    topPanel.setOpaque(false);
	    add(topPanel, BorderLayout.NORTH);
	    
	    JLabel labelTitle = new JLabel("<html>" + "Resources to include in the Learning Design");
	    labelTitle.setIcon(DweezilUIManager.getIcon(ICON_RESOURCES));
	    labelTitle.setFont(labelTitle.getFont().deriveFont(Font.BOLD, 16));
	    topPanel.add(labelTitle, BorderLayout.NORTH);
	    
	    topPanel.add(UIFactory.createGradientUnderLineBar(), BorderLayout.CENTER);
	    topPanel.add(Box.createVerticalStrut(10), BorderLayout.SOUTH);
	    
	    add(getResourcesTreePanel(), BorderLayout.CENTER);
	    add(getResourceEditor(), BorderLayout.SOUTH);
	}
	
	/**
	 * Set the DataModel
	 * @param ldDataModel
	 */
	public void setDataModel(LD_DataModel ldDataModel) {
	    _ldDataModel = ldDataModel;
	    getResourcesTreePanel().setDataModel(ldDataModel);
	}

	/**
	 * @return The Resources Tree Panel
	 */
	public LD_ResourcesTreePanel getResourcesTreePanel() {
	    if(_resourcesTreePanel == null) {
	        _resourcesTreePanel = new LD_ResourcesTreePanel();
	    }
	    return _resourcesTreePanel;
	}

	/**
	 * @return The Resource Editor Panel
	 */
	public LD_ResourceEditor getResourceEditor() {
	    if(_resourceEditorPanel == null) {
	        _resourceEditorPanel = new LD_ResourceEditor();
	    }
	    return _resourceEditorPanel;
	}
	
	/**
	 * Clean up
	 */
	public void cleanup() {
	    if(_resourcesTreePanel != null) {
	        _resourcesTreePanel.cleanup();
	    }
	    
	    if(_resourceEditorPanel != null) {
	        _resourceEditorPanel.cleanup();
	    }
	    
	    _ldDataModel = null;
	}

}
