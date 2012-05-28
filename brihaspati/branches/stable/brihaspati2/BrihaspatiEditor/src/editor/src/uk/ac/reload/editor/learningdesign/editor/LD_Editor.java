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

package uk.ac.reload.editor.learningdesign.editor;

import java.io.File;
import java.io.IOException;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import uk.ac.reload.dweezil.gui.DweezilProgressMonitor;
import uk.ac.reload.dweezil.gui.ErrorDialogBox;
import uk.ac.reload.dweezil.menu.DweezilMenuEvent;
import uk.ac.reload.dweezil.menu.MenuAction;
import uk.ac.reload.dweezil.menu.ProxyAction;
import uk.ac.reload.dweezil.util.DweezilUIManager;
import uk.ac.reload.editor.EditorFrame;
import uk.ac.reload.editor.EditorHandler;
import uk.ac.reload.editor.EditorInternalFrame;
import uk.ac.reload.editor.IIcons;
import uk.ac.reload.editor.Messages;
import uk.ac.reload.editor.contentpackaging.editor.CP_NewDialog;
import uk.ac.reload.editor.learningdesign.LD_EditorHandler;
import uk.ac.reload.editor.learningdesign.datamodel.ILD_DataModelHandler;
import uk.ac.reload.editor.learningdesign.datamodel.LD_DataModel;
import uk.ac.reload.editor.learningdesign.editor.activities.ActivitiesPanel;
import uk.ac.reload.editor.learningdesign.editor.environment.EnvironmentsPanel;
import uk.ac.reload.editor.learningdesign.editor.general.GeneralPanel;
import uk.ac.reload.editor.learningdesign.editor.method.MethodPanel;
import uk.ac.reload.editor.learningdesign.editor.resources.LD_ResourcesPanel;
import uk.ac.reload.editor.learningdesign.editor.roles.RolesPanel;
import uk.ac.reload.editor.learningdesign.xml.LearningDesign;
import uk.ac.reload.editor.menu.MainMenu;
import uk.ac.reload.jdom.XMLDocument;
import uk.ac.reload.jdom.XMLDocumentListener;
import uk.ac.reload.jdom.XMLDocumentListenerEvent;



/**
 * Learning Design Editor Frame.
 *
 * @author Phillip Beauvoir
 * @version $Id: LD_Editor.java,v 1.1 1998/03/26 15:32:14 ynsingh Exp $
 */
public class LD_Editor
extends EditorInternalFrame
implements XMLDocumentListener, IIcons
{
	/**
	 * The LD_DataModel
	 */
    private LD_DataModel _ldDataModel;
	
    /**
     * Handles Save and SaveAs Events
     */
    private ProxySaveHandler _saveHandler, _saveAsHandler;
    
	/**
	 * Our shared zip handler from the main menu
	 */
    private ProxyZipHandler _zipHandler;

    /**
     * The Undo Menu Manager for this Window
     */
    //private UndoMenuManager _undoMenuManager;
    
    /**
     * The Tabbed Pane 
     */
    private JTabbedPane _tabPane;
    
    /**
     * The Panels 
     */
    private ILD_DataModelHandler[] _panels;
    
    /**
     * The General Panel 
     */
    private GeneralPanel _generalPanel;

    /**
     * The Resources Panel 
     */
    private LD_ResourcesPanel _resourcesPanel;

    /**
     * The Roles Panel 
     */
    private RolesPanel _rolesPanel;

    /**
     * The Activities Panel 
     */
    private ActivitiesPanel _activitiesPanel;

    /**
     * The Environments Panel 
     */
    private EnvironmentsPanel _envPanel;
    
    /**
     * The Method Panel 
     */
    private MethodPanel _methodPanel;

    
    
    /**
     * Default Constructor
     */
    public LD_Editor() {
        setFrameIcon(ICON_LD);
        setTitle("Learning Design");

        // Tabbed Pane
        _tabPane = new JTabbedPane(JTabbedPane.BOTTOM);
        getContentPane().add(_tabPane);
        
        // Enumeration of sub-panels
        _panels = new ILD_DataModelHandler[] {
        	_generalPanel = new GeneralPanel(),
        	_resourcesPanel = new LD_ResourcesPanel(),
        	_rolesPanel = new RolesPanel(),
			_envPanel = new EnvironmentsPanel(),
        	_activitiesPanel = new ActivitiesPanel(),
        	_methodPanel = new MethodPanel()
        };
        
        _tabPane.addTab("General", DweezilUIManager.getIcon(ICON_LD), _generalPanel);
        _tabPane.addTab("Roles", DweezilUIManager.getIcon(ICON_ROLES), _rolesPanel);
        _tabPane.addTab("Environments", DweezilUIManager.getIcon(ICON_ENVIRONMENTS), _envPanel);
        _tabPane.addTab("Activities", DweezilUIManager.getIcon(ICON_ACTIVITIES), _activitiesPanel);
        _tabPane.addTab("Method", DweezilUIManager.getIcon(ICON_METHOD), _methodPanel);
        _tabPane.addTab("Resources", DweezilUIManager.getIcon(ICON_RESOURCES), _resourcesPanel);
        
        // Ensure we handle closing
        setDefaultCloseOperation(JInternalFrame.DO_NOTHING_ON_CLOSE);

        // We listen for what this frame is doing
        addInternalFrameListener(new InternalFrameAdapter() {
            // The Frame is closing
            public void internalFrameClosing(InternalFrameEvent e) {
                // Make sure button stuff is completed
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        boolean ok = checkNeedsSaving();
                        if(ok) {
                            dispose();
                        }
                    }
                });
            }

            // The Frame is activated
            public void internalFrameActivated(InternalFrameEvent e) {
                setFocusGained();
            }

            // The Frame is deactivated
            public void internalFrameDeactivated(InternalFrameEvent e) {
                setFocusLost();
            }
        });
        
        // New Proxy Save Handlers
        _saveHandler = new ProxySaveHandler(MainMenu.getSharedInstance().actionSave);
        _saveAsHandler = new ProxySaveHandler(MainMenu.getSharedInstance().actionSaveAs);
        
		// Proxy Zip handler
		_zipHandler = new ProxyZipHandler();

        // Undo
        //_undoMenuManager = new UndoMenuManager(MainMenu.getSharedInstance().editMenu.actionUndo,
        //		MainMenu.getSharedInstance().editMenu.actionRedo);
        //ldcp.setUndoHandler(_undoMenuManager.getUndoHandler());
    }

    /**
     * Set the data model
     * @param ldDataModel The LD DataModel
     */
    public void setDataModel(LD_DataModel ldDataModel) {
        _ldDataModel = ldDataModel;
        LearningDesign ld = _ldDataModel.getLearningDesign();
        
        setTitle("Learning Design " + ld.getLevel() + " - " + ld.getProjectName());
        
        // Set the DataModel in the Panels
        for(int i = 0; i < _panels.length; i++) {
			_panels[i].setDataModel(_ldDataModel);
		}
        
		// We'll listen to edits
        ld.addXMLDocumentListener(this);
        
		// Put it in the opened list - need to do this in case this is a New Document, not an Opened one
        EditorHandler.getSharedInstance().registerOpenedFile(ld.getFile(), this);
    }
    
    /**
     * Over-ride this so we can set view stuff
     */
    public void show() {
        super.show();
        
        // Init View of Panels
    	_rolesPanel.initView();
		_envPanel.initView();
    	_activitiesPanel.initView();
    	_methodPanel.initView();
    	_resourcesPanel.initView();
    }

	/**
	 * @return The LD_DataModel in this editor
	 */
	public LD_DataModel get_LD_DataModel() {
		return _ldDataModel;
	}
	

	/**
	 * Zip up the Content Package
	 */
	protected void zipIt() {
	    LearningDesign ld = _ldDataModel.getLearningDesign();
	    
		if(ld != null) {
			// Check whether to save
			boolean doSave = checkNeedsSaving();
			if(doSave == false) {
			    return;
			}
			
			// Stop it happening again
			_zipHandler.setEnabled(false);
			
			LD_EditorHandler.zipContentPackage(ld);
			
			// OK
			_zipHandler.setEnabled(true);
		}
	}

	//	=============================================================================
	//	Document Listener Events so we know when to set the Save Menu
	//	=============================================================================
	
	public void elementAdded(XMLDocumentListenerEvent e) {
		if(isSelected()) {
		    _saveHandler.setEnabled(true);
		}
	}
	
	public void elementChanged(XMLDocumentListenerEvent e) {
		if(isSelected()) {
		    _saveHandler.setEnabled(true);
		}
	}
	
	public void elementRemoved(XMLDocumentListenerEvent e) {
		if(isSelected()) {
		    _saveHandler.setEnabled(true);
		}
	}
	
	public void documentSaved(XMLDocument doc) {
		if(isSelected()) {
		    LearningDesign ld = _ldDataModel.getLearningDesign();
		    _saveHandler.setEnabled(ld.isDirty() && _saveHandler.isEnabled());
		}
	}

	// =============================================================================
    // Focus Handler
    // =============================================================================

    /**
     * We got the focus
     */
    private void setFocusGained() {
    	//_undoMenuManager.setFocusGained();

        LearningDesign ld = _ldDataModel.getLearningDesign();
    	
    	_saveHandler.setEnabled(ld.isDirty());
        _saveHandler.addListener();

        _saveAsHandler.setEnabled(true);
        _saveAsHandler.addListener();
        
		_zipHandler.setEnabled(true);
		_zipHandler.addListener();
    }

    /**
     * We lost the focus
     */
    private void setFocusLost() {
        //_undoMenuManager.setFocusLost();
        _saveHandler.clear();
        _saveAsHandler.clear();
        _zipHandler.clear();
    }


    // =============================================================================
    // Window Closing, Cleanup and Saving Handlers
    // =============================================================================

    /**
     * We have been told by the Application that it is closing.
     * Here we can save if need be.
     * @return true if all is OK, false if not.
     */
    public boolean applicationClosing() {
        return checkNeedsSaving();
    }

    /**
     * We can check if the File needs saving
     * @return true if all is OK (YES or NO), false if not (CANCEL).
     */
    protected boolean checkNeedsSaving() {
		boolean isOK = true;
		
		LearningDesign ld = _ldDataModel.getLearningDesign();	
		if(_saveHandler.isEnabled()) {
			String name = ld.getProjectName();
			String msg;
			if(name != null) {
			    msg = name + " " + "has been modified."; 
			}
			else {
			    msg = "Unsaved Learning Design."; 
			}
			
			int doSave = JOptionPane.showConfirmDialog(this,
					msg + " " + "Do you wish to save these changes?", 
					"Save Learning Design",
					JOptionPane.YES_NO_CANCEL_OPTION);
			
			if(doSave == JOptionPane.YES_OPTION) {
			    // Save XML Document
			    isOK = saveDocument();
		        // Also check for other items that need Saving
		        for(int i = 0; i < _panels.length; i++) {
		            isOK = isOK && _panels[i].doSave();
		        }
			}
			else if(doSave == JOptionPane.CANCEL_OPTION) {
			    isOK = false;
			}
		}

		return isOK;
    }

    /**
     * Dispose of this Window and clean up
     */
    public void dispose() {
        cleanup();
        super.dispose();
    }
    
    /**
     * Clean up
     */
    public void cleanup() {
        // Unregister Proxy Handlers
        if(_saveHandler != null) {
            _saveHandler.clear();
        }
        
        if(_saveAsHandler != null) {
            _saveAsHandler.clear();
        }

        if(_zipHandler != null) {
		    _zipHandler.clear();
		}

        // Undo Manager
        //if(_undoMenuManager != null) {
        //    _undoMenuManager.destroy();
        //}
        
        // Cleanup the Panels
        for(int i = 0; i < _panels.length; i++) {
            _panels[i].cleanup();
        }
        
        _ldDataModel.destroy();
        _ldDataModel = null;
    }

    /**
     * If the manifest has been edited, ask whether we should save it
     * @return true if OK, false if not
     */
    protected boolean saveDocument() {
        try {
            // Don't Save the Document if not dirty.
            // (The Save button may be enabled for other LD documents that need saving)
            LearningDesign ld = _ldDataModel.getLearningDesign();
            if(ld.isDirty()) {
                ld.saveDocument();
            }
        }
        catch(IOException ex) {
			ErrorDialogBox.showWarning(this,
			        "Could not save file" + ": ", 
			        "Save Learning Design", ex);
			return false;
        }
        
        return true;
    }

	/**
	 * Save a copy of this Learning Design
	 * @return False if the user cancelled
	 */
    protected boolean saveDocumentAs() {
	    DweezilProgressMonitor progressMonitor = null;
	    try {
			// Ask for Folder
		    CP_NewDialog dialog = new CP_NewDialog(Messages.getString("uk.ac.reload.editor.contentpackaging.CP_Editor.29")); //$NON-NLS-1$
			File targetFolder = dialog.showDialog();
			// User cancelled or Folder not valid
			if(targetFolder == null || !targetFolder.exists()) {
			    return false;
			}
			
			// Progress Monitor
			progressMonitor = new DweezilProgressMonitor(EditorFrame.getInstance(),
			        Messages.getString("uk.ac.reload.editor.contentpackaging.CP_Editor.31"), //$NON-NLS-1$
					Messages.getString("uk.ac.reload.editor.contentpackaging.CP_Editor.16"), //$NON-NLS-1$
					Messages.getString("uk.ac.reload.editor.contentpackaging.CP_Editor.16"), //$NON-NLS-1$
					true,
					DweezilUIManager.getIcon(ICON_APP16));
			
			LearningDesign ld = _ldDataModel.getLearningDesign();
			
			// Do it
			boolean result = ld.saveDocumentAs(targetFolder, progressMonitor);
			progressMonitor.close();
			
			// User cancelled
			if(!result) {
			    return false;
			}

			// Reset
			setDataModel(_ldDataModel);

	        // Show message
			JOptionPane.showMessageDialog(this,
			        "Learning Design Saved", 
			        "Save As", 
			        JOptionPane.INFORMATION_MESSAGE,
			        null);
			
			return true;
		}
		catch(Exception ex) {
			if(progressMonitor != null) {
			    progressMonitor.close();
			}
		    ErrorDialogBox.showWarning(this,
		            Messages.getString("uk.ac.reload.editor.contentpackaging.CP_Editor.32"), //$NON-NLS-1$
		            Messages.getString("uk.ac.reload.editor.contentpackaging.CP_Editor.33"), //$NON-NLS-1$
		            ex);
		    return false;
		}
    }

    //==============================================================================
    //======================PROXY MENU HANDLERS=====================================
    //==============================================================================


    /**
     * Deals with Save Menu Events
     */
    class ProxySaveHandler extends ProxyAction {

        public ProxySaveHandler(MenuAction menuAction) {
            super(menuAction);
        }

		public void menuActionPerformed(DweezilMenuEvent event) {
            if(isSelected() && event.getSource() == MainMenu.getSharedInstance().actionSave) {
                saveDocument();
            }
            
            if(isSelected() && event.getSource() == MainMenu.getSharedInstance().actionSaveAs) {
                // Save on Thread
				Thread thread = new Thread() {
					public void run() {
					    saveDocumentAs();
					}
				};
				
				thread.start();
            }
        }
    }

	/**
	 * Deals with shared Zip Menu Item from the main menu
	 */
	class ProxyZipHandler extends ProxyAction {
		public ProxyZipHandler() {
			super(MainMenu.getSharedInstance().actionZipIt);
		}
		
		// When user clicks 'Zip' from the main menu...
		public void menuActionPerformed(DweezilMenuEvent event) {
			Thread thread = new Thread() {
				public void run() {
					zipIt();
				}
			};
			
			if(isSelected()) {
			    thread.start();
			}
		}
	}
}
