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

package uk.ac.reload.editor.learningdesign.editor.roles;

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
import uk.ac.reload.editor.learningdesign.datamodel.LD_DataModel;
import uk.ac.reload.editor.learningdesign.datamodel.LD_Grouping;
import uk.ac.reload.editor.learningdesign.datamodel.components.roles.Role;
import uk.ac.reload.jdom.XMLPath;



/**
 * Roles Panel
 *
 * @author Phillip Beauvoir
 * @version $Id: RolesPanel.java,v 1.1 1998/03/26 15:40:32 ynsingh Exp $
 */
public class RolesPanel
extends GradientPanel
implements TreeSelectionListener, ILD_DataModelHandler
{
    /**
     * The XMLPath
     */
    public static XMLPath XMLPATH = new XMLPath("learning-design/components/roles");
    
    /**
     * The LD_DataModel
     */
    private LD_DataModel _ldDataModel;
    
    /**
     * The SplitPane
     */
    private JSplitPane _splitPane;
	
    /**
     * Tree Panel
     */
    private RoleTreePanel _treePanel;

    /**
     * Role Editor Panel
     */
    private RoleEditorPanel _editorPanel;

    /**
     * JScrollPane for Role Editor Panel
     */
    private JScrollPane _editorPanelScrollPane;
    
    /**
     * Grouping Panel on right
     */
    private GroupingPanel _groupingPanel;
    
    /**
     * Container for right hand panel
     */
    private JPanel _cardPanel;
    
    /**
     * The currently selected LD_Component
     */
    private LD_Component _currentSelectedLD_Component;

    
    /**
	 * Default Constructor
	 */
	public RolesPanel() {
		super(new BorderLayout());
		
		// Add Label
		JLabel label = new JLabel("Roles");
		label.setFont(label.getFont().deriveFont(Font.BOLD, 16));
		add(label, BorderLayout.NORTH);

		// SplitPane
        _splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        _splitPane.setOneTouchExpandable(true);
        add(_splitPane, BorderLayout.CENTER);
		
        // Left Panel - Tree Panel
        _splitPane.setLeftComponent(getRoleTreePanel());

        // Right Panel - Card Panel
        _cardPanel = new JPanel(new CardLayout());
        _splitPane.setRightComponent(_cardPanel);
        
        getRoleTreePanel().getTree().addTreeSelectionListener(this);
	}
	
	/* (non-Javadoc)
	 * @see uk.ac.reload.editor.learningdesign.datamodel.ILD_DataModelHandler#setDataModel(uk.ac.reload.editor.learningdesign.datamodel.LD_DataModel)
	 */
	public void setDataModel(LD_DataModel ldDataModel) {
	    _ldDataModel = ldDataModel;
	    getRoleTreePanel().setDataModel(ldDataModel);
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
	 * Update the Editor Panel with the current selected LD Component
	 * @param ldComponent
	 */
	protected void updateEditorPanel(LD_Component ldComponent) {
	    // Stop double updates
	    if(ldComponent == _currentSelectedLD_Component) {
	        return;
	    }
	    
	    String panelName = null;
	    
        // This first
        if(ldComponent instanceof LD_Grouping) {
            panelName = "#GROUPING_PANEL";
	        if(getGroupingPanel().getParent() != _cardPanel) {
	            _cardPanel.add(getGroupingPanel(), panelName);
	        }
            getGroupingPanel().setComponent(ldComponent);
        }
        else if(ldComponent instanceof Role) {
            panelName = "#ROLE_PANEL";
	        if(getEditorPanelScrollPane().getParent() != _cardPanel) {
	            _cardPanel.add(getEditorPanelScrollPane(), panelName);
	        }
            getRoleEditorPanel().setComponent(ldComponent);
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
        getRoleTreePanel().setSelected(true);
    }
    
	/**
	 * Clean up
	 */
	public void cleanup() {
	    if(_treePanel != null) {
	        _treePanel.cleanup();
	    }

	    if(_editorPanel != null) {
	        _editorPanel.cleanup();
	    }
	    
	    if(_groupingPanel != null) {
	        _groupingPanel.cleanup();
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
     * @return The RoleEditorPanel
     */
    public RoleEditorPanel getRoleEditorPanel() {
        if(_editorPanel == null) {
            _editorPanel = new RoleEditorPanel();
        }
        return _editorPanel;
    }
    
    /**
     * @return The scroll pane containing the RoleEditorPanel
     */
    public JScrollPane getEditorPanelScrollPane() {
        if(_editorPanelScrollPane == null) {
            _editorPanelScrollPane = new JScrollPane(getRoleEditorPanel());
            _editorPanelScrollPane.setBorder(null);
        }
        return _editorPanelScrollPane;
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
     * @return The RoleTreePanel
     */
    public RoleTreePanel getRoleTreePanel() {
        if(_treePanel == null) {
            _treePanel = new RoleTreePanel();
        }
        return _treePanel;
    }
}
