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
import javax.swing.JLabel;
import javax.swing.JSplitPane;
import javax.swing.border.Border;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;

import uk.ac.reload.dweezil.gui.CoolBarPanelGroup;
import uk.ac.reload.dweezil.gui.GradientPanel;
import uk.ac.reload.dweezil.gui.tree.DweezilTreeNode;
import uk.ac.reload.editor.contentpackaging.editor.resourceview.CP_ResourcesPanel;
import uk.ac.reload.editor.learningdesign.datamodel.ILD_DataModelHandler;
import uk.ac.reload.editor.learningdesign.datamodel.LD_Component;
import uk.ac.reload.editor.learningdesign.datamodel.LD_DataModel;



/**
 * Learning Design Resources Panel
 *
 * @author Phillip Beauvoir
 * @version $Id: LD_ResourcesPanel.java,v 1.1 1998/03/26 15:40:32 ynsingh Exp $
 */
public class LD_ResourcesPanel
extends GradientPanel
implements ILD_DataModelHandler, TreeSelectionListener
{
    /**
     * The LD_DataModel
     */
    private LD_DataModel _ldDataModel;
    
    /**
     * The SplitPane
     */
    private JSplitPane _splitPane;
    
    /**
     * Resources Panel
     */
    private CP_ResourcesPanel _cpResourcesPanel;
    
    /**
     * Editor Panel
     */
    private LD_ResourcesEditorPanel _editorpanel;
    

	/**
	 * Default Constructor
	 */
	public LD_ResourcesPanel() {
		super(new BorderLayout());
		
		// Add Label
		JLabel label = new JLabel("Resources");
		label.setFont(label.getFont().deriveFont(Font.BOLD, 16));
		add(label, BorderLayout.NORTH);
		
		// SplitPane
        _splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        _splitPane.setOneTouchExpandable(true);
        add(_splitPane, BorderLayout.CENTER);
		
        _splitPane.setLeftComponent(getCP_ResourcesPanel());
        _splitPane.setRightComponent(getLD_ResourcesEditorPanel());
        
        CoolBarPanelGroup coolbarGroup = new CoolBarPanelGroup();
        coolbarGroup.addCoolBarPanel(getCP_ResourcesPanel());
        coolbarGroup.addCoolBarPanel(getLD_ResourcesEditorPanel().getResourcesTreePanel());
        
		// Listen to Tree selections to pass on to Resource Editor panel
        getLD_ResourcesEditorPanel().getResourcesTreePanel().getTree().addTreeSelectionListener(this);
	}
	
	/* (non-Javadoc)
	 * @see uk.ac.reload.editor.learningdesign.datamodel.ILD_DataModelHandler#setDataModel(uk.ac.reload.editor.learningdesign.datamodel.LD_DataModel)
	 */
	public void setDataModel(LD_DataModel ldDataModel) {
	    _ldDataModel = ldDataModel;
	    getCP_ResourcesPanel().setFileView(ldDataModel.getLearningDesign().getProjectFolder());
	    getLD_ResourcesEditorPanel().setDataModel(ldDataModel);
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
        getCP_ResourcesPanel().setSelected(true);
    }

	/**
	 * Clean up
	 */
	public void cleanup() {
	    if(_cpResourcesPanel != null) {
	        _cpResourcesPanel.cleanup();
	    }
	    
	    if(_editorpanel != null) {
	        _editorpanel.cleanup();
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
     * @return The CP_ResourcesPanel
     */
    public CP_ResourcesPanel getCP_ResourcesPanel() {
        if(_cpResourcesPanel == null) {
            _cpResourcesPanel = new CP_ResourcesPanel();
        }
        
        return _cpResourcesPanel;
    }
    
    /**
     * @return The LD_ResourcesEditorPanel
     */
    public LD_ResourcesEditorPanel getLD_ResourcesEditorPanel() {
        if(_editorpanel == null) {
            _editorpanel = new LD_ResourcesEditorPanel();
        }
        
        return _editorpanel;
    }

	/** 
	 * Tree Selection Listener.  Pass on to the Resource Editor
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
                getLD_ResourcesEditorPanel().getResourceEditor().setComponent((LD_Component)obj);
            }
        }
	}
}
