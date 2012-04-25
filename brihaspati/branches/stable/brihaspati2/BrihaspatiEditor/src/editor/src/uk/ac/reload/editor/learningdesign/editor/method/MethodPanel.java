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

package uk.ac.reload.editor.learningdesign.editor.method;

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
import uk.ac.reload.editor.learningdesign.datamodel.ILD_DataModelHandler;
import uk.ac.reload.editor.learningdesign.datamodel.LD_Component;
import uk.ac.reload.editor.learningdesign.datamodel.LD_DataModel;
import uk.ac.reload.editor.learningdesign.datamodel.method.Act;
import uk.ac.reload.editor.learningdesign.datamodel.method.Method;
import uk.ac.reload.editor.learningdesign.datamodel.method.Play;
import uk.ac.reload.editor.learningdesign.datamodel.method.RolePart;
import uk.ac.reload.jdom.XMLPath;



/**
 * MethodPanel
 *
 * @author Phillip Beauvoir
 * @version $Id: MethodPanel.java,v 1.1 1998/03/26 15:40:32 ynsingh Exp $
 */
public class MethodPanel
extends GradientPanel
implements TreeSelectionListener, ILD_DataModelHandler
{
    /**
     * The XMLPath
     */
    public static XMLPath XMLPATH = new XMLPath("learning-design/method");

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
     * The Tree Panel
     */
    private MethodTreePanel _treePanel;

    /**
     * The Method Editor Panel
     */
    private MethodEditorPanel _methodEditorPanel;
    
    /**
     * The ScrollPane for the Method Editor Panel
     */
    private JScrollPane _methodEditorPanelScrollPane;

    /**
     * The Play Editor Panel
     */
    private PlayEditorPanel _playEditorPanel;
    
    /**
     * The ScrollPane for the Play Editor Panel
     */
    private JScrollPane _playEditorPanelScrollPane;

    /**
     * The Act Editor Panel
     */
    private ActEditorPanel _actEditorPanel;
    
    /**
     * The ScrollPane for the Act Editor Panel
     */
    private JScrollPane _actEditorPanelScrollPane;

    /**
     * The RolePart Editor Panel
     */
    private RolePartEditorPanel _rolepartEditorPanel;
    
    /**
     * The ScrollPane for the RolePart Editor Panel
     */
    private JScrollPane _rolepartEditorPanelScrollPane;

    /**
     * The currently selected LD_Component
     */
    private LD_Component _currentSelectedLD_Component;

    
	/**
	 * Default Constructor
	 */
	public MethodPanel() {
		super(new BorderLayout());
		
		// Add Label
		JLabel label = new JLabel("Method");
		label.setFont(label.getFont().deriveFont(Font.BOLD, 16));
		add(label, BorderLayout.NORTH);

		// SplitPane
        _splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        _splitPane.setOneTouchExpandable(true);
        add(_splitPane, BorderLayout.CENTER);
        
        // Left Panel - Environment Tree
        _splitPane.setLeftComponent(getMethodTreePanel());
        
        // Right Panel - Card Panel
        _cardPanel = new JPanel(new CardLayout());
        _splitPane.setRightComponent(_cardPanel);

        getMethodTreePanel().getTree().addTreeSelectionListener(this);
	}
	
	/* (non-Javadoc)
	 * @see uk.ac.reload.editor.learningdesign.datamodel.ILD_DataModelHandler#setDataModel(uk.ac.reload.editor.learningdesign.datamodel.LD_DataModel)
	 */
	public void setDataModel(LD_DataModel ldDataModel) {
	    _ldDataModel = ldDataModel;
	    getMethodTreePanel().setDataModel(ldDataModel);
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
	    
        if(ldComponent instanceof Method) {
            panelName = "#METHOD_PANEL";
            if(getMethodEditorPanelScrollPane().getParent() != _cardPanel) {
                _cardPanel.add(getMethodEditorPanelScrollPane(), panelName);
            }
            getMethodEditorPanel().setComponent(ldComponent);
        }
        else if(ldComponent instanceof Play) {
            panelName = "#PLAY_PANEL";
            if(getPlayEditorPanelScrollPane().getParent() != _cardPanel) {
                _cardPanel.add(getPlayEditorPanelScrollPane(), panelName);
            }
            getPlayEditorPanel().setComponent(ldComponent);
        }
        else if(ldComponent instanceof Act) {
            panelName = "#ACT_PANEL";
            if(getActEditorPanelScrollPane().getParent() != _cardPanel) {
                _cardPanel.add(getActEditorPanelScrollPane(), panelName);
            }
            getActEditorPanel().setComponent(ldComponent);
        }
        else if(ldComponent instanceof RolePart) {
            panelName = "#ROLEPART_PANEL";
            if(getRolePartEditorPanelScrollPane().getParent() != _cardPanel) {
                _cardPanel.add(getRolePartEditorPanelScrollPane(), panelName);
            }
            getRolePartEditorPanel().setComponent(ldComponent);
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
        getMethodTreePanel().setSelected(true);
    }

	/**
	 * Clean up
	 */
	public void cleanup() {
	    if(_treePanel != null) {
	        _treePanel.cleanup();
	    }
	    
	    if(_methodEditorPanel != null) {
	        _methodEditorPanel.cleanup();
	    }
	    
	    if(_playEditorPanel != null) {
	        _playEditorPanel.cleanup();
	    }
	    
	    if(_actEditorPanel != null) {
	        _actEditorPanel.cleanup();
	    }
	    
	    if(_rolepartEditorPanel != null) {
	        _rolepartEditorPanel.cleanup();
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
     * @return The JScrollPane for the MethodEditorPanel
     */
    public JScrollPane getMethodEditorPanelScrollPane() {
        if(_methodEditorPanelScrollPane == null) {
            _methodEditorPanelScrollPane = new JScrollPane(getMethodEditorPanel());
            _methodEditorPanelScrollPane.setBorder(null);
        }
        return _methodEditorPanelScrollPane;
    }
    
    
    /**
     * @return The MethodEditorPanel
     */
    public MethodEditorPanel getMethodEditorPanel() {
        if(_methodEditorPanel == null) {
            _methodEditorPanel = new MethodEditorPanel();
        }
        return _methodEditorPanel;
    }

    /**
     * @return The JScrollPane for the PlayEditorPanel
     */
    public JScrollPane getPlayEditorPanelScrollPane() {
        if(_playEditorPanelScrollPane == null) {
            _playEditorPanelScrollPane = new JScrollPane(getPlayEditorPanel());
            _playEditorPanelScrollPane.setBorder(null);
        }
        return _playEditorPanelScrollPane;
    }
    
    /**
     * @return The PlayEditorPanel
     */
    public PlayEditorPanel getPlayEditorPanel() {
        if(_playEditorPanel == null) {
            _playEditorPanel = new PlayEditorPanel();
        }
        return _playEditorPanel;
    }

    /**
     * @return The JScrollPane for the PlayEditorPanel
     */
    public JScrollPane getActEditorPanelScrollPane() {
        if(_actEditorPanelScrollPane == null) {
            _actEditorPanelScrollPane = new JScrollPane(getActEditorPanel());
            _actEditorPanelScrollPane.setBorder(null);
        }
        return _actEditorPanelScrollPane;
    }
    
    /**
     * @return The ActEditorPanel
     */
    public ActEditorPanel getActEditorPanel() {
        if(_actEditorPanel == null) {
            _actEditorPanel = new ActEditorPanel();
        }
        return _actEditorPanel;
    }

    /**
     * @return The JScrollPane for the RolePartEditorPanel
     */
    public JScrollPane getRolePartEditorPanelScrollPane() {
        if(_rolepartEditorPanelScrollPane == null) {
            _rolepartEditorPanelScrollPane = new JScrollPane(getRolePartEditorPanel());
            _rolepartEditorPanelScrollPane.setBorder(null);
        }
        return _rolepartEditorPanelScrollPane;
    }
    
    /**
     * @return The RolePartEditorPanel
     */
    public RolePartEditorPanel getRolePartEditorPanel() {
        if(_rolepartEditorPanel == null) {
            _rolepartEditorPanel = new RolePartEditorPanel();
        }
        return _rolepartEditorPanel;
    }

    /**
     * @return The MethodTreePanel
     */
    public MethodTreePanel getMethodTreePanel() {
        if(_treePanel == null) {
            _treePanel = new MethodTreePanel();
        }
        return _treePanel;
    }
}
