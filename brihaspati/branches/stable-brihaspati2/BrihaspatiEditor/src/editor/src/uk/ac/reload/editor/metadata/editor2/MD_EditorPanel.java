/**
 *  RELOAD TOOLS
 *
 *  Copyright (c) 2003 Oleg Liber, Bill Olivier, Phillip Beauvoir
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

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;

import uk.ac.reload.dweezil.gui.DweezilComboBox;
import uk.ac.reload.dweezil.gui.ErrorDialogBox;
import uk.ac.reload.dweezil.menu.UndoMenuManager;
import uk.ac.reload.editor.Messages;
import uk.ac.reload.editor.menu.Menu_Edit;
import uk.ac.reload.editor.metadata.datamodel.IMD_DataModelHandler;
import uk.ac.reload.editor.metadata.datamodel.MD_DataModel;
import uk.ac.reload.editor.metadata.editor.tableview.MD_TablePanel;
import uk.ac.reload.editor.metadata.xml.Metadata;
import uk.ac.reload.editor.properties.EditorProperties;
import uk.ac.reload.moonunit.ProfiledSchemaController;

/**
 * The Core Metadata Editor
 *
 *
 * @author Phillip Beauvoir
 * @version $Id: MD_EditorPanel.java,v 1.1 1998/03/26 15:32:14 ynsingh Exp $
 */
public class MD_EditorPanel
extends JPanel
{
    /**
     * The Metadata DataModel
     */
    private MD_DataModel _mdDataModel;

    /**
     * The Prefs panel
     */
    private MetadataPrefsPanel _prefsPanel;

    /**
     * The Tabbed Pane 
     */
    private JTabbedPane _tabPane;
    
    /**
     * The Panels 
     */
    private IMD_DataModelHandler[] _panels;

    /**
     * General Panel
     */
    private GeneralPanel _generalPanel;
    
    /**
     * LifeCycle Panel
     */
    private LifeCyclePanel _lifecyclePanel;

    /**
     * MetaMetadata Panel
     */
    private MetaMetadataPanel _metametadataPanel;

    /**
     * Technical Panel
     */
    private TechnicalPanel _technicalPanel;

    /**
     * Educational Panel
     */
    private EducationalPanel _educationalPanel;

    /**
     * Rights Panel
     */
    private RightsPanel _rightsPanel;

    /**
     * Relation Panel
     */
    private RelationPanel _relationPanel;

    /**
     * Annotation Panel
     */
    private AnnotationPanel _annotationPanel;

    /**
     * Classification Panel
     */
    private ClassificationPanel _classificationPanel;

    /**
     * The Table View
     */
    private MD_TablePanel _tablePanel;

    /**
     * The Edit Menu
     */
    private Menu_Edit _editMenu;

    /**
     * The Undo Menu Manager for this Window
     */
    private UndoMenuManager _undoMenuManager;

    /**
     * Constructor
     * @param editMenu
     */
    public MD_EditorPanel(Menu_Edit editMenu) {
        _editMenu = editMenu;

        setLayout(new BorderLayout());

        // Add a Prefs panel
        _prefsPanel = new MetadataPrefsPanel();
        add(_prefsPanel, BorderLayout.NORTH);

        // Tab Pane
        _tabPane = new JTabbedPane(JTabbedPane.BOTTOM);
        add(_tabPane, BorderLayout.CENTER);

        // Enumeration of sub-panels
        _panels = new IMD_DataModelHandler[] {
        	_generalPanel = new GeneralPanel(),
        	_lifecyclePanel = new LifeCyclePanel(),
        	_metametadataPanel = new MetaMetadataPanel(),
        	_technicalPanel = new TechnicalPanel(),
        	_educationalPanel = new EducationalPanel(),
        	_rightsPanel = new RightsPanel(),
        	_relationPanel = new RelationPanel(),
        	_annotationPanel = new AnnotationPanel(),
        	_classificationPanel = new ClassificationPanel()
        };
        
        // Table View
        _tablePanel = new MD_TablePanel(editMenu);
        
        _tabPane.addTab("General", _generalPanel);
        _tabPane.addTab("Life Cycle", _lifecyclePanel);
        _tabPane.addTab("Metametadata", _metametadataPanel);
        _tabPane.addTab("Technical", _technicalPanel);
        _tabPane.addTab("Educational", _educationalPanel);
        _tabPane.addTab("Rights", _rightsPanel);
        _tabPane.addTab("Relation", _relationPanel);
        _tabPane.addTab("Annotation", _annotationPanel);
        _tabPane.addTab("Classification", _classificationPanel);
        
        _tabPane.addTab("Tree View", _tablePanel);
    }

    /**
     * Get the MD_DataModel
     * @return MD_DataModel
     */
    public MD_DataModel getDataModel() {
        return _mdDataModel;
    }

    /**
     * Set the Editor to a Metadata Document
     * @param metadata The Metadata Document
     */
    public void setDataModel(MD_DataModel mdDataModel) {
        _mdDataModel = mdDataModel;

        Metadata md = _mdDataModel.getMetadata();
        
        // Set the DataModel in the Panels
        for(int i = 0; i < _panels.length; i++) {
			_panels[i].setDataModel(mdDataModel);
		}

        // Tell Panels
        _prefsPanel.setDocument(md);
        _tablePanel.setDocument(md);

        // New Undo Menu Manager
        if(_undoMenuManager != null) {
            _undoMenuManager.cleanup();
        }
        _undoMenuManager = new UndoMenuManager(_editMenu.actionUndo, _editMenu.actionRedo);
        md.setUndoHandler(_undoMenuManager.getUndoHandler());
    }

    /**
     * Over-ride this so we can set view stuff
     */
    public void initView() {
        _tablePanel.initView();
    }

    /**
     * @return the UndoManager
     */
    public UndoMenuManager getUndoManager() {
        return _undoMenuManager;
    }

    /**
     * @return Returns the prefsPanel.
     */
    public MetadataPrefsPanel getPrefsPanel() {
        return _prefsPanel;
    }

    /**
     * Clean up
     */
    public void cleanup() {
        try {
            // Have to check for nulls.  Since launching this Editor is on a thread,
            // if user closes the app before this window appears then these objects will be null
            
            if(_undoMenuManager != null) {
                _undoMenuManager.cleanup();
            }
            
            // Cleanup the Panels
            for(int i = 0; i < _panels.length; i++) {
                _panels[i].cleanup();
    		}
            
            if(_tablePanel != null) {
                _tablePanel.cleanup();
            }
            
            if(_mdDataModel.getMetadata() != null) {
                _mdDataModel.getMetadata().destroy();
            }
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * The Preferences Panel
     */
    class MetadataPrefsPanel extends JPanel {

        /**
         * Profile Combo box
         */
        DweezilComboBox profileCombobox;
        
        /**
         * Profile Combo box Listener
         */
        ComboBoxListener listener;

        /**
         * Constructor
         */
        MetadataPrefsPanel() {
            setLayout(new FlowLayout(FlowLayout.LEFT));

            // PROFILE COMBO BOX
            add(new JLabel(Messages.getString("uk.ac.reload.editor.metadata.MD_EditorPanel.2") + ": ")); //$NON-NLS-1$ //$NON-NLS-2$
            profileCombobox = new DweezilComboBox(false);
            add(profileCombobox);

            listener = new ComboBoxListener();
        }

        /**
         * Initialise
         */
        public void setDocument(Metadata metadata) {
            // Remove Listener or else!
            profileCombobox.removeActionListener(listener);
            
            // Populate it
            String[] profiles = ((ProfiledSchemaController)metadata.getSchemaController()).getHelperProfileNames();
            profileCombobox.setItems(profiles);
            
            // Set selected
            String profile = ((ProfiledSchemaController)metadata.getSchemaController()).getHelperProfile().getProfileName();
            profileCombobox.setSelectedItem(profile);

            // Listen to changes on the Profile Combo
            profileCombobox.addActionListener(listener);
        }
        
        class ComboBoxListener extends AbstractAction {
        
            public void actionPerformed(ActionEvent e) {
                String profileName = (String)profileCombobox.getSelectedItem();
                try {
                    ((ProfiledSchemaController)_mdDataModel.getMetadata().getSchemaController()).loadHelperProfile(profileName);
                }
                catch(Exception ex) {
                    if(EditorProperties.getString("DEBUG").equals("true")) { //$NON-NLS-1$ //$NON-NLS-2$
                        ex.printStackTrace();
                    }
                    ErrorDialogBox.showWarning(Messages.getString("uk.ac.reload.editor.metadata.MD_EditorPanel.2"), //$NON-NLS-1$
                            Messages.getString("uk.ac.reload.editor.metadata.MD_EditorPanel.3")+ ": " + profileName, //$NON-NLS-1$ //$NON-NLS-2$
                            ex);
                }
                
                // Tell Panels - they will need refreshing
                SwingUtilities.invokeLater(new Runnable() {
                    public void run()  {
                        //_formPanel.refresh();
                        _tablePanel.refresh();
                    }
                });
                
            }
        }
    }
    
    /**
     * @return MD_TablePanel
     */
    public MD_TablePanel getMD_TablePanel() {
        return _tablePanel;
    }
}
