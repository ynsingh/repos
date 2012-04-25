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

package uk.ac.reload.editor.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JViewport;
import javax.swing.Scrollable;

import uk.ac.reload.dweezil.gui.GradientPanel;
import uk.ac.reload.dweezil.gui.UIFactory;
import uk.ac.reload.dweezil.gui.layout.RelativeLayoutManager;
import uk.ac.reload.editor.datamodel.DataComponent;
import uk.ac.reload.editor.datamodel.DataModel;
import uk.ac.reload.editor.datamodel.IDataComponentIcon;
import uk.ac.reload.editor.datamodel.IDataModelListener;
import uk.ac.reload.editor.gui.widgets.TitleLabelTextField;

/**
 * Editor Panel based on GradientPanel with Title and Description<br>
 * Although this is not an abstract class, it is meant to be extended.
 *
 * @author Phillip Beauvoir
 * @version $Id: TitledEditorPanel.java,v 1.1 1998/03/26 15:24:11 ynsingh Exp $
 */
public class TitledEditorPanel
extends GradientPanel
implements IDataModelListener, Scrollable
{
	
    public final static String TOPPANEL_ID = "TitledEditorPanel.TOPPANEL_ID";
    
    /**
     * The DataModel
     */
    private DataModel _dataModel;
    
    /**
     * The backing Component
     */
    private DataComponent _dataComponent;
	
    /**
     * The RelativeLayoutManager used to set out this panel
     */
    private RelativeLayoutManager _layoutManager;
    
    /**
	 * The Title label Text Field
	 */
	private TitleLabelTextField _labelTitle;

	/**
	 * The Description label
	 */
	private JLabel _labelDescription;
	
	
    /**
	 * Default Constructor
	 */
	public TitledEditorPanel() {
	    super();
	}
	
	/**
	 * Set the backing Component
	 * @param dataComponent the backing Component
	 */
	public void setComponent(DataComponent dataComponent) {
	    _dataComponent = dataComponent;
        
	    if(dataComponent instanceof IDataComponentIcon) {
	        getTitleLabel().setIcon(((IDataComponentIcon)dataComponent).getIcon());
        }

	    getTitleLabel().setComponent(dataComponent);

	    getDescriptionLabel().setText("<html>" + getDescription());
	    
	    // Lazily set the DataModel and listen to changes
	    if(_dataModel == null) {
	        _dataModel = dataComponent.getDataModel();
	        if(_dataModel != null) {
	            _dataModel.addIDataModelListener(this);
	        }
	    }
	}
	
	/**
	 * @return the backing Component
	 */
	public DataComponent getComponent() {
	    return _dataComponent;
	}
	
	/**
	 * @return The Title LabelText Field
	 */
	public TitleLabelTextField getTitleLabel() {
	    if(_labelTitle == null) {
	        _labelTitle = new TitleLabelTextField("Title");
		    _labelTitle.setFont(_labelTitle.getFont().deriveFont(Font.BOLD, 16));
	    }
	    return _labelTitle;
	}
	
	/**
	 * @return The Description Label 
	 */
	public JLabel getDescriptionLabel() {
	    if(_labelDescription == null) {
	        _labelDescription = new JLabel("Description");
	        _labelDescription.setBorder(BorderFactory.createEmptyBorder(0, 2, 0, 0));
	    }
	    return _labelDescription;
	}
	
	/**
	 * @return The Title of this Panel
	 */
	public String getTitle() {
	    return (_dataComponent == null) ? "Title" : _dataComponent.getTitle();
	}

	/**
	 * @return The Description of this Panel
	 */
	public String getDescription() {
	    return (_dataComponent == null) ? "Description" : _dataComponent.getDescription();
	}
	
	/**
	 * @return The RelativeLayoutManager used to set out this panel
	 */
	public RelativeLayoutManager getRelativeLayoutManager() {
	    if(_layoutManager == null) {
	        _layoutManager = new RelativeLayoutManager(this);
	    }
	    return _layoutManager;
	}

	/**
	 * Set up the view
	 */
	protected void setupView() {
		super.setupView();
		
		setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 60));
		
	    RelativeLayoutManager layoutManager = getRelativeLayoutManager();
		
		// Add Top Panel
	    JPanel topPanel = new JPanel(new BorderLayout());
	    topPanel.setOpaque(false);
	    topPanel.add(getTitleLabel(), BorderLayout.NORTH);
	    topPanel.add(UIFactory.createGradientUnderLineBar(), BorderLayout.CENTER);
	    topPanel.add(getDescriptionLabel(), BorderLayout.SOUTH);
		layoutManager.addFromLeftToRightEdges(topPanel, TOPPANEL_ID, RelativeLayoutManager.ROOT_NAME,
				RelativeLayoutManager.TOP, 0, 0);
	}
	
	/**
	 * Clean up
	 */
	public void cleanup() {
	    _dataComponent = null;
	    _dataModel = null;
	}
	
	// ======================= Scrollable Interface ==================================
	
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
		return 15; 
	}

	/* (non-Javadoc)
	 * @see javax.swing.Scrollable#getScrollableUnitIncrement(java.awt.Rectangle, int, int)
	 */
	public int getScrollableUnitIncrement(Rectangle visibleRect, int orientation, int direction) {
		return 15; 
	}
	

	// ======================= Data Model Listeners ==================================
	
    public void componentAdded(DataComponent component) {
    }

    public void componentRemoved(DataComponent component) {
    }

    /**
     * If this component changed, set Text
     */
    public void componentChanged(DataComponent component) {
        if(component == _dataComponent) {
            getTitleLabel().setText(getTitle());
        }
    }
    
    public void componentMoved(DataComponent component) {
    }

}
