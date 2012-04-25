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

package uk.ac.reload.editor.learningdesign.editor.activities;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.border.Border;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;

import uk.ac.reload.dweezil.gui.GradientPanel;
import uk.ac.reload.dweezil.gui.tree.DweezilTreeNode;
import uk.ac.reload.editor.gui.GroupingPanel;
import uk.ac.reload.editor.learningdesign.datamodel.ILD_DataModelHandler;
import uk.ac.reload.editor.learningdesign.datamodel.LD_Component;
import uk.ac.reload.editor.learningdesign.datamodel.LD_ComponentRef;
import uk.ac.reload.editor.learningdesign.datamodel.LD_DataModel;
import uk.ac.reload.editor.learningdesign.datamodel.LD_Grouping;
import uk.ac.reload.editor.learningdesign.datamodel.components.activities.ActivityStructure;
import uk.ac.reload.editor.learningdesign.datamodel.components.activities.LearningActivity;
import uk.ac.reload.editor.learningdesign.datamodel.components.activities.SupportActivity;
import uk.ac.reload.editor.learningdesign.datamodel.components.activities.UnitOfLearningHREF;
import uk.ac.reload.editor.learningdesign.editor.shared.ReferencePanel;
import uk.ac.reload.jdom.XMLPath;



/**
 * Activities Panel
 *
 * @author Phillip Beauvoir
 * @version $Id: ActivitiesPanel.java,v 1.1 1998/03/26 15:40:32 ynsingh Exp $
 */
public class ActivitiesPanel
extends GradientPanel
implements TreeSelectionListener, ILD_DataModelHandler
{
    /**
     * The XMLPath
     */
    public static XMLPath XMLPATH = new XMLPath("learning-design/components/activities");

    /**
     * The LD_DataModel
     */
    private LD_DataModel _ldDataModel;
    
    /**
     * The SplitPane
     */
    private JSplitPane _splitPane;
	
    /**
     * The Container Panel on the right
     */
    private JPanel _cardPanel;

    /**
     * Tree Panel - left panel holds the tree
     */
    private ActivityTreePanel _treePanel;
    
    /**
     * Grouping Panel
     */
    private GroupingPanel _groupingPanel;

    /**
     * LearningActivity Editor Panel
     */
    private LearningActivityEditorPanel _learningActivityEditorPanel;
    
    /**
     * The ScrollPane for the LearningActivity Editor Panel
     */
    private JScrollPane _learningActivityEditorPanelScrollPane;
    
    /**
     * SupportActivity Editor Panel
     */
    private SupportActivityEditorPanel _supportActivityEditorPanel;
    
    /**
     * The ScrollPane for the SupportActivity Editor Panel
     */
    private JScrollPane _supportActivityEditorPanelScrollPane;

    /**
     * Activity Structure Editor Panel
     */
    private ActivityStructureEditorPanel _structureEditorPanel;
    
    /**
     * The ScrollPane for the Activity Structure Editor Panel
     */
    private JScrollPane _structureEditorPanelScrollPane;

    /**
     * Unit of Learning HREF Editor Panel
     */
    private UnitOfLearningHREFEditorPanel _uolHREFEditorPanel;
    
    /**
     * The ScrollPane for the Unit of Learning HREF Editor Panel
     */
    private JScrollPane _uolHREFEditorPanelScrollPane;

    /**
     * ReferencePanel Panel on right
     */
    private ReferencePanel _referencePanel;
    
    /**
     * The currently selected LD_Component
     */
    private LD_Component _currentSelectedLD_Component;

    
    /**
	 * Default Constructor
	 */
	public ActivitiesPanel() {
		super(new BorderLayout());
		
		// Add Label
		JLabel label = new JLabel("Activities");
		label.setFont(label.getFont().deriveFont(Font.BOLD, 16));
		add(label, BorderLayout.NORTH);

		// SplitPane left
        _splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        _splitPane.setOneTouchExpandable(true);
        add(_splitPane, BorderLayout.CENTER);
        
        // Left Panel - Tree Panel
        _splitPane.setLeftComponent(getActivityTreePanel());
        
        // Right Panel - Card Panel
        _cardPanel = new JPanel(new CardLayout());
        _splitPane.setRightComponent(_cardPanel);
        
        getActivityTreePanel().getTree().addTreeSelectionListener(this);
	}
	
	/* (non-Javadoc)
	 * @see uk.ac.reload.editor.learningdesign.datamodel.ILD_DataModelHandler#setDataModel(uk.ac.reload.editor.learningdesign.datamodel.LD_DataModel)
	 */
	public void setDataModel(LD_DataModel ldDataModel) {
	    _ldDataModel = ldDataModel;
	    getActivityTreePanel().setDataModel(ldDataModel);
	}
	
	/** 
	 * Tree Selection Listener
	 */
	public void valueChanged(TreeSelectionEvent e) {
	    // Only interested in new selections
	    if(!e.isAddedPath()) {
	        return;
	    }

	    TreePath selPath = e.getPath();
        Object selectedNode = selPath.getLastPathComponent();

        if(selectedNode instanceof DweezilTreeNode) {
            Object obj = ((DweezilTreeNode)selectedNode).getUserObject();
            if(obj instanceof LD_Component) {
                updateEditorPanel((LD_Component)obj);
            }
        }
	}
	
	/**
	 * Update the Editor Panel with the currently selected tree node
	 * @param ldComponent
	 */
	protected void updateEditorPanel(DweezilTreeNode treeNode) {
        if(treeNode == null) {
            return;
        }
	    Object obj = treeNode.getUserObject();
        if(treeNode.getUserObject() instanceof LD_Component) {
            updateEditorPanel((LD_Component)obj);
        }
	}
	
	/**
	 * Update the Editor Panel with the current selected LD Component
	 * @param ldComponent
	 */
	protected synchronized void updateEditorPanel(LD_Component ldComponent) {
	    // Stop double updates
	    if(ldComponent == _currentSelectedLD_Component) {
	        return;
	    }
	    
	    String panelName = null;
	    
	    // This first
        if(ldComponent instanceof LD_ComponentRef) {
            panelName = "#REF_PANEL";
            if(getReferencePanel().getParent() != _cardPanel) {
                _cardPanel.add(getReferencePanel(), panelName);
            }
            getReferencePanel().setComponent(ldComponent);
        }
	    else if(ldComponent instanceof LD_Grouping) {
            panelName = "#GROUPING_PANEL";
	        if(getGroupingPanel().getParent() != _cardPanel) {
	            _cardPanel.add(getGroupingPanel(), panelName);
	        }
	        getGroupingPanel().setComponent(ldComponent);
	    }
	    else if(ldComponent instanceof LearningActivity) {
            panelName = "#LEARNING_ACTIVITY_PANEL";
	        if(getLearningActivityEditorScrollPane().getParent() != _cardPanel) {
	            _cardPanel.add(getLearningActivityEditorScrollPane(), panelName);
	        }
	        getLearningActivityEditorPanel().setComponent(ldComponent);
	    }
	    else if(ldComponent instanceof SupportActivity) {
            panelName = "#SUPPORT_ACTIVITY_PANEL";
	        if(getSupportActivityEditorScrollPane().getParent() != _cardPanel) {
	            _cardPanel.add(getSupportActivityEditorScrollPane(), panelName);
	        }
	        getSupportActivityEditorPanel().setComponent(ldComponent);
	    }
	    else if(ldComponent instanceof ActivityStructure) {
            panelName = "#ACT_STRUCT_PANEL";
	        if(getActivityStructureEditorScrollPane().getParent() != _cardPanel) {
	            _cardPanel.add(getActivityStructureEditorScrollPane(), panelName);
	        }
	        getActivityStructureEditorPanel().setComponent(ldComponent);
	    }
	    else if(ldComponent instanceof UnitOfLearningHREF) {
            panelName = "#UOL_PANEL";
	        if(getUnitOfLearningHREFEditorScrollPane().getParent() != _cardPanel) {
	            _cardPanel.add(getUnitOfLearningHREFEditorScrollPane(), panelName);
	        }
	        getUnitOfLearningHREFEditorPanel().setComponent(ldComponent);
	    }
	    
	    if(panelName != null) {
	        ((CardLayout)_cardPanel.getLayout()).show(_cardPanel, panelName); 
	    }
	    
	    _currentSelectedLD_Component = ldComponent;
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
     * Called after Panel is shown so we can init stuff if need be
     */
    public void initView() {
        _splitPane.setDividerLocation(0.33);
        getActivityTreePanel().setSelected(true);
    }

	/**
	 * Clean up
	 */
	public void cleanup() {
	    if(_treePanel != null) {
	        _treePanel.cleanup();
	    }
	    
	    if(_learningActivityEditorPanel != null) {
	        _learningActivityEditorPanel.cleanup();
	    }
	    
	    if(_supportActivityEditorPanel != null) {
	        _supportActivityEditorPanel.cleanup();
	    }

	    if(_structureEditorPanel != null) {
	        _structureEditorPanel.cleanup();
	    }
	    
	    if(_uolHREFEditorPanel != null) {
	        _uolHREFEditorPanel.cleanup();
	    }
	    
	    if(_referencePanel != null) {
	        _referencePanel.cleanup();
	    }
	    
	    _ldDataModel = null;
	}

    /**
     * Save any additional information
     * @return True if all OK, false if not
     */
    public boolean doSave() {
        return true;
    }

    /**
     * @return The JScrollPane for the LearningActivity Editor Panel
     */
    public JScrollPane getLearningActivityEditorScrollPane() {
        if(_learningActivityEditorPanelScrollPane == null) {
            _learningActivityEditorPanelScrollPane = new JScrollPane(getLearningActivityEditorPanel());
            _learningActivityEditorPanelScrollPane.setBorder(null);
        }
        return _learningActivityEditorPanelScrollPane;
    }
    
    
    /**
     * @return The LearningActivity Editor Panel
     */
    public LearningActivityEditorPanel getLearningActivityEditorPanel() {
        if(_learningActivityEditorPanel == null) {
            _learningActivityEditorPanel = new LearningActivityEditorPanel();
        }
        return _learningActivityEditorPanel;
    }

    /**
     * @return The JScrollPane for the SupportActivity Editor Panel
     */
    public JScrollPane getSupportActivityEditorScrollPane() {
        if(_supportActivityEditorPanelScrollPane == null) {
            _supportActivityEditorPanelScrollPane = new JScrollPane(getSupportActivityEditorPanel());
            _supportActivityEditorPanelScrollPane.setBorder(null);
        }
        return _supportActivityEditorPanelScrollPane;
    }
    
    
    /**
     * @return The SupportActivity Editor Panel
     */
    public SupportActivityEditorPanel getSupportActivityEditorPanel() {
        if(_supportActivityEditorPanel == null) {
            _supportActivityEditorPanel = new SupportActivityEditorPanel();
        }
        return _supportActivityEditorPanel;
    }

    /**
     * @return The JScrollPane for the ActivityStructure Editor Panel
     */
    public JScrollPane getActivityStructureEditorScrollPane() {
        if(_structureEditorPanelScrollPane == null) {
            _structureEditorPanelScrollPane = new JScrollPane(getActivityStructureEditorPanel());
            _structureEditorPanelScrollPane.setBorder(null);
        }
        return _structureEditorPanelScrollPane;
    }
    
    /**
     * @return The ActivityStructure Editor Panel
     */
    public ActivityStructureEditorPanel getActivityStructureEditorPanel() {
        if(_structureEditorPanel == null) {
            _structureEditorPanel = new ActivityStructureEditorPanel();
        }
        return _structureEditorPanel;
    }

    /**
     * @return The JScrollPane for the UnitOfLearningHREF Editor Panel
     */
    public JScrollPane getUnitOfLearningHREFEditorScrollPane() {
        if(_uolHREFEditorPanelScrollPane == null) {
            _uolHREFEditorPanelScrollPane = new JScrollPane(getUnitOfLearningHREFEditorPanel());
            _uolHREFEditorPanelScrollPane.setBorder(null);
        }
        return _uolHREFEditorPanelScrollPane;
    }
    
    /**
     * @return The UnitOfLearningHREF Editor Panel
     */
    public UnitOfLearningHREFEditorPanel getUnitOfLearningHREFEditorPanel() {
        if(_uolHREFEditorPanel == null) {
            _uolHREFEditorPanel = new UnitOfLearningHREFEditorPanel();
        }
        return _uolHREFEditorPanel;
    }
    
    /**
     * @return The Grouping Panel
     */
    public GroupingPanel getGroupingPanel() {
        if(_groupingPanel == null) {
            _groupingPanel = new GroupingPanel();
        }
        return _groupingPanel;
    }

    /**
     * @return The Reference Panel
     */
    public ReferencePanel getReferencePanel() {
        if(_referencePanel == null) {
            _referencePanel = new ReferencePanel();
        }
        return _referencePanel;
    }

    /**
     * @return The Activity Tree Panel
     */
    public ActivityTreePanel getActivityTreePanel() {
        if(_treePanel == null) {
            _treePanel = new ActivityTreePanel();
        }
        return _treePanel;
    }
}
