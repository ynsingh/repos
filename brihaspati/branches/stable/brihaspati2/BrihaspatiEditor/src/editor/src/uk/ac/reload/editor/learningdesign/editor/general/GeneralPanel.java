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

package uk.ac.reload.editor.learningdesign.editor.general;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.*;
import javax.swing.border.Border;

import uk.ac.reload.dweezil.gui.GradientPanel;
import uk.ac.reload.dweezil.gui.PaddedPanel;
import uk.ac.reload.dweezil.gui.layout.RelativeLayoutManager;
import uk.ac.reload.editor.learningdesign.datamodel.ILD_DataModelHandler;
import uk.ac.reload.editor.learningdesign.datamodel.LD_DataModel;
import uk.ac.reload.editor.learningdesign.editor.shared.ItemModelTypePanel;



/**
 * General Panel
 *
 * @author Phillip Beauvoir
 * @version $Id: GeneralPanel.java,v 1.1 1998/03/26 15:40:32 ynsingh Exp $
 */
public class GeneralPanel
extends GradientPanel
implements ILD_DataModelHandler
{
    /**
     * The LD_DataModel
     */
    private LD_DataModel _ldDataModel;
    
	/**
	 * Overview Panel
	 */
    private OverViewPanel _overViewPanel;
    
    /**
     * Objectives Panel
     */
    private ItemModelTypePanel _objectivesPanel;
    
    /**
     * Pre-requisites Panel
     */
    private ItemModelTypePanel _prereqPanel;
	
	/**
	 * Padding
	 */
	static final double RIGHTPAD = 0.5;

	/**
	 * Default Constructor
	 */
	public GeneralPanel() {
		super(new BorderLayout());
		
		// Add Label
		JLabel label = new JLabel("General");
		label.setFont(label.getFont().deriveFont(Font.BOLD, 16));
		add(label, BorderLayout.NORTH);

		PaddedPanel padPanel = new ScrollPanel(PaddedPanel.RIGHT, RIGHTPAD);
		
		JScrollPane sp = new JScrollPane(padPanel);
		sp.setBorder(null);
		add(sp, BorderLayout.CENTER);

		JPanel subPanel = new JPanel();
		padPanel.add(subPanel);
		
		// All three of these have to be set in order to work
		subPanel.setOpaque(false);
		sp.setOpaque(false);
		sp.getViewport().setOpaque(false);

		RelativeLayoutManager layoutManager = new RelativeLayoutManager(subPanel);
		
		// Add OverViewPanel
		_overViewPanel = new OverViewPanel();
		layoutManager.addFromLeftToRightEdges(_overViewPanel, "_overViewPanel", RelativeLayoutManager.ROOT_NAME,
				RelativeLayoutManager.TOP, 10, 10);
		
		// Add ObjectivesPanel
		_objectivesPanel = new ItemModelTypePanel(false);
		layoutManager.addFromLeftToRightEdges(_objectivesPanel, "_objectivesPanel", "_overViewPanel",
				RelativeLayoutManager.BOTTOM, 40, 10);
		
		// Add PrerequisitesPanel
		_prereqPanel = new ItemModelTypePanel(false);
		layoutManager.addFromLeftToRightEdges(_prereqPanel, "_prereqPanel", "_objectivesPanel",
				RelativeLayoutManager.BOTTOM, 40, 10);
	}
	
	/* (non-Javadoc)
	 * @see uk.ac.reload.editor.learningdesign.datamodel.ILD_DataModelHandler#setDocument(uk.ac.reload.editor.learningdesign.LD_ContentPackage)
	 */
	public void setDataModel(LD_DataModel ldDataModel) {
	    _ldDataModel = ldDataModel;
	    
	    // OVERVIEW PANEL
	    _overViewPanel.setDataModel(ldDataModel);
	    
		// LEARNING OBJECTIVES PANEL
		_objectivesPanel.setItemModelType(ldDataModel.getLearningObjectives());

		// PREREQUISITES PANEL
		_prereqPanel.setItemModelType(ldDataModel.getPrerequisites());
	}
	
	/**
	 * Set up the view
	 */
	protected void setupView() {
		super.setupView();
		Border border = BorderFactory.createEmptyBorder(5, 10, 5, 10);
		setBorder(border);
	}

	/**
	 * Clean up
	 */
	public void cleanup() {
    	_overViewPanel.cleanup();
    	_objectivesPanel.cleanup();
    	_prereqPanel.cleanup();
    	
    	_ldDataModel = null;
	}

    /**
     * Save any additional information
     * @return True if all OK, false if not
     */
    public boolean doSave() {
        return _overViewPanel.doSave();
    }

	
	class ScrollPanel extends PaddedPanel implements Scrollable {
        /**
         * @param type
         * @param padFactor
         */
        public ScrollPanel(int type, double padFactor) {
            super(type, padFactor);
        }

        /* (non-Javadoc)
		 * @see javax.swing.Scrollable#getScrollableTracksViewportHeight()
		 */
		public boolean getScrollableTracksViewportHeight() {
			if (getParent() instanceof JViewport) {
			    return (((JViewport)getParent()).getHeight() > getPreferredSize().height);
			}
			return false;
		}

		/* (non-Javadoc)
		 * @see javax.swing.Scrollable#getScrollableTracksViewportWidth()
		 */
		public boolean getScrollableTracksViewportWidth() {
			if (getParent() instanceof JViewport) {
			    return (((JViewport)getParent()).getWidth() > getPreferredSize().width);
			}
			return false;
		}

		/* (non-Javadoc)
		 * @see javax.swing.Scrollable#getPreferredScrollableViewportSize()
		 */
		public Dimension getPreferredScrollableViewportSize() {
			return getPreferredSize();
		}

		/* (non-Javadoc)
		 * @see javax.swing.Scrollable#getScrollableBlockIncrement(java.awt.Rectangle, int, int)
		 */
		public int getScrollableBlockIncrement(Rectangle visibleRect, int orientation, int direction) {
			return 20; 
		}

		/* (non-Javadoc)
		 * @see javax.swing.Scrollable#getScrollableUnitIncrement(java.awt.Rectangle, int, int)
		 */
		public int getScrollableUnitIncrement(Rectangle visibleRect, int orientation, int direction) {
			return 20; 
		}
	    
	}
}
