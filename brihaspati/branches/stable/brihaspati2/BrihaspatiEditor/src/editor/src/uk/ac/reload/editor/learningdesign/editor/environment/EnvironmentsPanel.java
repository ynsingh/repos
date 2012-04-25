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
import uk.ac.reload.editor.learningdesign.datamodel.components.environments.*;
import uk.ac.reload.editor.learningdesign.editor.shared.ReferencePanel;
import uk.ac.reload.jdom.XMLPath;


/**
 * Environments Panel
 *
 * @author Phillip Beauvoir
 * @version $Id: EnvironmentsPanel.java,v 1.1 1998/03/26 15:40:32 ynsingh Exp $
 */
public class EnvironmentsPanel
extends GradientPanel
implements TreeSelectionListener, ILD_DataModelHandler
{
    /**
     * The XMLPath
     */
    public static XMLPath XMLPATH = new XMLPath("learning-design/components/environments");

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
    private EnvironmentTreePanel _treePanel;
    
    /**
     * The LearningObject Editor Panel
     */
    private LearningObjectEditorPanel _learningobjectEditorPanel;
    
    /**
     * The ScrollPane for the LearningObject Editor Panel
     */
    private JScrollPane _learningobjectEditorPanelScrollPane;
    
    /**
     * The Environment Editor Panel
     */
    private EnvironmentEditorPanel _envEditorPanel;
    
    /**
     * The ScrollPane for the Environment Editor Panel
     */
    private JScrollPane _envEditorPanelScrollPane;

    /**
     * The Conference Service Editor Panel
     */
    private ConferenceEditorPanel _conferenceEditorPanel;
    
    /**
     * The ScrollPane for the Conference Service Editor Panel
     */
    private JScrollPane _conferenceEditorPanelScrollPane;
    
    /**
     * The SendMail Service Editor Panel
     */
    private SendMailEditorPanel _sendmailEditorPanel;
    
    /**
     * The ScrollPane for the SendMail Service Editor Panel
     */
    private JScrollPane _sendmailEditorPanelScrollPane;

    /**
     * The Index Search Service Editor Panel
     */
    private IndexSearchEditorPanel _indexSearchEditorPanel;
    
    /**
     * The ScrollPane for the Index Search Service Editor Panel
     */
    private JScrollPane _indexSearchEditorPanelScrollPane;
    
    /**
     * The Monitor Service Editor Panel
     */
    private MonitorEditorPanel _monitorEditorPanel;
    
    /**
     * The ScrollPane for the Monitor Service Editor Panel
     */
    private JScrollPane _monitorEditorPanelScrollPane;

    /**
     * ReferencePanel Panel on right
     */
    private ReferencePanel _referencePanel;
    
    /**
     * The Grouping Panel
     */
    private GroupingPanel _blankPanel;

    /**
     * The currently selected LD_Component
     */
    private LD_Component _currentSelectedLD_Component;

    /**
	 * Default Constructor
	 */
	public EnvironmentsPanel() {
		super(new BorderLayout());
		
		// Add Label
		JLabel label = new JLabel("Environments");
		label.setFont(label.getFont().deriveFont(Font.BOLD, 16));
		add(label, BorderLayout.NORTH);

		// SplitPane
        _splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        _splitPane.setOneTouchExpandable(true);
        add(_splitPane, BorderLayout.CENTER);
        
        // Left Panel - Environment Tree
        _splitPane.setLeftComponent(getEnvironmentTreePanel());
        
        // Right Panel - Card Panel
        _cardPanel = new JPanel(new CardLayout());
        _splitPane.setRightComponent(_cardPanel);

        getEnvironmentTreePanel().getTree().addTreeSelectionListener(this);
	}
	
	/* (non-Javadoc)
	 * @see uk.ac.reload.editor.learningdesign.datamodel.ILD_DataModelHandler#setDataModel(uk.ac.reload.editor.learningdesign.datamodel.LD_DataModel)
	 */
	public void setDataModel(LD_DataModel ldDataModel) {
	    _ldDataModel = ldDataModel;
	    getEnvironmentTreePanel().setDataModel(ldDataModel);
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
        if(ldComponent instanceof LD_ComponentRef) {
            panelName = "#REF_PANEL";
            // If the panel has not been added to the CardPanel, add it...
            if(getReferencePanel().getParent() != _cardPanel) {
                _cardPanel.add(getReferencePanel(), panelName);
            }
            getReferencePanel().setComponent(ldComponent);
        }
        else if(ldComponent instanceof LearningObject) {
            panelName = "#LO_PANEL";
            if(getLearningobjectEditorPanelScrollPane().getParent() != _cardPanel) {
                _cardPanel.add(getLearningobjectEditorPanelScrollPane(), panelName);
            }
            getLearningObjectEditorPanel().setComponent(ldComponent);
        }
        else if(ldComponent instanceof Environment) {
            panelName = "#ENV_PANEL";
            if(getEnvironmentEditorScrollPane().getParent() != _cardPanel) {
                _cardPanel.add(getEnvironmentEditorScrollPane(), panelName);
            }
            getEnvironmentEditorPanel().setComponent(ldComponent);
        }
        else if(ldComponent instanceof Conference) {
            panelName = "#CONF_PANEL";
            if(getConferenceEditorScrollPane().getParent() != _cardPanel) {
                _cardPanel.add(getConferenceEditorScrollPane(), panelName);
            }
            getConferenceEditorPanel().setComponent(ldComponent);
        }
        else if(ldComponent instanceof SendMail) {
            panelName = "#SENDMAIL_PANEL";
            if(getSendMailEditorScrollPane().getParent() != _cardPanel) {
                _cardPanel.add(getSendMailEditorScrollPane(), panelName);
            }
            getSendMailEditorPanel().setComponent(ldComponent);
        }
        else if(ldComponent instanceof IndexSearch) {
            panelName = "#INDEXSEARCH_PANEL";
            if(getIndexSearchEditorScrollPane().getParent() != _cardPanel) {
                _cardPanel.add(getIndexSearchEditorScrollPane(), panelName);
            }
            getIndexSearchEditorPanel().setComponent(ldComponent);
        }
        else if(ldComponent instanceof Monitor) {
            panelName = "#MONITOR_PANEL";
            if(getMonitorEditorScrollPane().getParent() != _cardPanel) {
                _cardPanel.add(getMonitorEditorScrollPane(), panelName);
            }
            getMonitorEditorPanel().setComponent(ldComponent);
        }
        else {
            panelName = "#BLANK_PANEL";
            if(getBlankPanel().getParent() != _cardPanel) {
                _cardPanel.add(getBlankPanel(), panelName);
            }
            getBlankPanel().setComponent(ldComponent);
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
     * Called after Panel is shown so we can init stuff
     */
    public void initView() {
        _splitPane.setDividerLocation(0.33);
        _treePanel.setSelected(true);    
    }
    
    /**
     * Clean up
     */
    public void cleanup() {
        if(_treePanel != null) {
            _treePanel.cleanup();
        }
        
        if(_learningobjectEditorPanel != null) {
            _learningobjectEditorPanel.cleanup();
        }
        
        if(_envEditorPanel != null) {
            _envEditorPanel.cleanup();
        }
        
        if(_conferenceEditorPanel != null) {
            _conferenceEditorPanel.cleanup();
        }
        
        if(_sendmailEditorPanel != null) {
            _sendmailEditorPanel.cleanup();
        }
        
        if(_indexSearchEditorPanel != null) {
            _indexSearchEditorPanel.cleanup();
        }
        
        if(_monitorEditorPanel != null) {
            _monitorEditorPanel.cleanup();
        }
        
        if(_referencePanel != null) {
            _referencePanel.cleanup();
        }
        
        if(_blankPanel != null) {
            _blankPanel.cleanup();
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
     * @return The JScrollPane for the IndexSearch Editor Panel
     */
    public JScrollPane getIndexSearchEditorScrollPane() {
        if(_indexSearchEditorPanelScrollPane == null) {
            _indexSearchEditorPanelScrollPane = new JScrollPane(getIndexSearchEditorPanel());
            _indexSearchEditorPanelScrollPane.setBorder(null);
        }
        return _indexSearchEditorPanelScrollPane;
    }
    
    /**
     * @return The IndexSearch Editor Panel
     */
    public IndexSearchEditorPanel getIndexSearchEditorPanel() {
        if(_indexSearchEditorPanel == null) {
            _indexSearchEditorPanel = new IndexSearchEditorPanel();
        }
        return _indexSearchEditorPanel;
    }
    
    /**
     * @return The JScrollPane for the SendMail Editor Panel
     */
    public JScrollPane getSendMailEditorScrollPane() {
        if(_sendmailEditorPanelScrollPane == null) {
            _sendmailEditorPanelScrollPane = new JScrollPane(getSendMailEditorPanel());
            _sendmailEditorPanelScrollPane.setBorder(null);
        }
        return _sendmailEditorPanelScrollPane;
    }
    
    /**
     * @return The SendMail Editor Panel
     */
    public SendMailEditorPanel getSendMailEditorPanel() {
        if(_sendmailEditorPanel == null) {
            _sendmailEditorPanel = new SendMailEditorPanel();
        }
        return _sendmailEditorPanel;
    }

    /**
     * @return The JScrollPane for the Monitor Editor Panel
     */
    public JScrollPane getMonitorEditorScrollPane() {
        if(_monitorEditorPanelScrollPane == null) {
            _monitorEditorPanelScrollPane = new JScrollPane(getMonitorEditorPanel());
            _monitorEditorPanelScrollPane.setBorder(null);
        }
        return _monitorEditorPanelScrollPane;
    }
    
    /**
     * @return The Monitor Editor Panel
     */
    public MonitorEditorPanel getMonitorEditorPanel() {
        if(_monitorEditorPanel == null) {
            _monitorEditorPanel = new MonitorEditorPanel();
        }
        return _monitorEditorPanel;
    }
    
    /**
     * @return The JScrollPane for the Conference Editor Panel
     */
    public JScrollPane getConferenceEditorScrollPane() {
        if(_conferenceEditorPanelScrollPane == null) {
            _conferenceEditorPanelScrollPane = new JScrollPane(getConferenceEditorPanel());
            _conferenceEditorPanelScrollPane.setBorder(null);
        }
        return _conferenceEditorPanelScrollPane;
    }
    
    
    /**
     * @return The Conference Editor Panel
     */
    public ConferenceEditorPanel getConferenceEditorPanel() {
        if(_conferenceEditorPanel == null) {
            _conferenceEditorPanel = new ConferenceEditorPanel();
        }
        return _conferenceEditorPanel;
    }

    /**
     * @return The JScrollPane for the EnvironmentEditorPanel
     */
    public JScrollPane getEnvironmentEditorScrollPane() {
        if(_envEditorPanelScrollPane == null) {
            _envEditorPanelScrollPane = new JScrollPane(getEnvironmentEditorPanel());
            _envEditorPanelScrollPane.setBorder(null);
        }
        return _envEditorPanelScrollPane;
    }
    
    
    /**
     * @return The EnvironmentEditorPanel
     */
    public EnvironmentEditorPanel getEnvironmentEditorPanel() {
        if(_envEditorPanel == null) {
            _envEditorPanel = new EnvironmentEditorPanel();
        }
        return _envEditorPanel;
    }

    /**
     * @return The JScrollPane for the LearningObjectEditorPanel
     */
    public JScrollPane getLearningobjectEditorPanelScrollPane() {
        if(_learningobjectEditorPanelScrollPane == null) {
            _learningobjectEditorPanelScrollPane = new JScrollPane(getLearningObjectEditorPanel());
            _learningobjectEditorPanelScrollPane.setBorder(null);
        }
        return _learningobjectEditorPanelScrollPane;
    }
    
    
    /**
     * @return The LearningObjectEditorPanel
     */
    public LearningObjectEditorPanel getLearningObjectEditorPanel() {
        if(_learningobjectEditorPanel == null) {
            _learningobjectEditorPanel = new LearningObjectEditorPanel();
        }
        return _learningobjectEditorPanel;
    }
    
    /**
     * @return The EnvironmentTreePanel
     */
    public EnvironmentTreePanel getEnvironmentTreePanel() {
        if(_treePanel == null) {
            _treePanel = new EnvironmentTreePanel();
        }
        return _treePanel;
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
     * @return The Blank Panel
     */
    public GroupingPanel getBlankPanel() {
        if(_blankPanel == null) {
            _blankPanel = new GroupingPanel();
        }
        return _blankPanel;
    }
}
