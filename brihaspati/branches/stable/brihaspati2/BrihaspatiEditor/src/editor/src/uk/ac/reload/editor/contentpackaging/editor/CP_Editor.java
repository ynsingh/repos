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

package uk.ac.reload.editor.contentpackaging.editor;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSplitPane;
import javax.swing.SwingUtilities;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import org.jdom.Element;

import uk.ac.reload.diva.util.FileUtils;
import uk.ac.reload.dweezil.gui.*;
import uk.ac.reload.dweezil.menu.DweezilMenuEvent;
import uk.ac.reload.dweezil.menu.MenuAction;
import uk.ac.reload.dweezil.menu.ProxyAction;
import uk.ac.reload.dweezil.menu.UndoMenuManager;
import uk.ac.reload.dweezil.util.DweezilUIManager;
import uk.ac.reload.editor.EditorFrame;
import uk.ac.reload.editor.EditorHandler;
import uk.ac.reload.editor.EditorInternalFrame;
import uk.ac.reload.editor.IIcons;
import uk.ac.reload.editor.Messages;
import uk.ac.reload.editor.contentpackaging.CP_EditorHandler;
import uk.ac.reload.editor.contentpackaging.CP_Viewer;
import uk.ac.reload.editor.contentpackaging.editor.manifestview.ManifestPanel;
import uk.ac.reload.editor.contentpackaging.editor.resourceview.CP_ResourcesPanel;
import uk.ac.reload.editor.contentpackaging.xml.ContentPackage;
import uk.ac.reload.editor.menu.MainMenu;
import uk.ac.reload.editor.properties.EditorProperties;
import uk.ac.reload.editor.scorm.xml.SCORM12_Package;
import uk.ac.reload.jdom.XMLDocument;
import uk.ac.reload.jdom.XMLDocumentListener;
import uk.ac.reload.jdom.XMLDocumentListenerEvent;
import uk.ac.reload.moonunit.ProfiledSchemaController;
import uk.ac.reload.moonunit.contentpackaging.CP_Core;


/**
 * The Content Package Editor Frame
 *
 *
 * @author Phillip Beauvoir
 * @author Paul Sharples
 * @version $Id: CP_Editor.java,v 1.1 1998/03/26 15:32:14 ynsingh Exp $
 */
public class CP_Editor
extends EditorInternalFrame
implements XMLDocumentListener, IIcons
{
	/**
	 * The ContentPackage Document
	 */
    private ContentPackage _contentPackage;
	
	/**
	 * The Resources Panel
	 */
    private CP_ResourcesPanel _resourcesPanel;
	
	/**
	 * The CP Manifest Tree Panel
	 */
    private ManifestPanel _manifestPanel;
	
	/**
	 * The SplitPane
	 */
    private JSplitPane _splitPane;
	
	/**
	 * Handles Save and SaveAs Events
	 */
    private ProxySaveHandler _saveHandler, _saveAsHandler;
	
	/**
	 * Our shared zip handler from the main menu
	 */
    private ProxyZipHandler _zipHandler;
	
	/**
	 * Our shared view CP handler from the main menu
	 */
    private ProxyViewCPHandler _viewCPHandler;
	
	/**
	 * Our shared export preview handler from the main menu
	 */
    private ProxyExportCPPreviewHandler _exportPreviewHandler;
	
	/**
	 * The Undo Menu Manager for this Window
	 */
    private UndoMenuManager _undoMenuManager;
	
	/**
	 * The CoolBar Panel
	 */
    private CP_CoolBarPanel _coolBarPanel;
	
	/**
	 * Default Constructor
	 */
	public CP_Editor() {
		setFrameIcon(ICON_CP);
		setTitle(Messages.getString("uk.ac.reload.editor.contentpackaging.CP_Editor.0")); //$NON-NLS-1$
		
		// Resources Panel
		_resourcesPanel = new CP_ResourcesPanel();
		
		// Manifest Panel
		_manifestPanel = new ManifestPanel(this);
		
		// CoolBar Panel
		_coolBarPanel = new CP_CoolBarPanel(_manifestPanel);
		
		// Panel Grouping
		CoolBarPanelGroup panelGroup = new CoolBarPanelGroup();
		panelGroup.addCoolBarPanel(_resourcesPanel);
		panelGroup.addCoolBarPanel(_coolBarPanel);
		
		// SplitPane
		_splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		_splitPane.setOneTouchExpandable(true);
		
		getContentPane().add(_splitPane);
		
		_splitPane.setLeftComponent(_resourcesPanel);
		_splitPane.setRightComponent(_coolBarPanel);
		
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
						if(ok) dispose();
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
	}
	
	/**
	 * Constructor
	 * @param cp The ContentPackage
	 */
	public CP_Editor(ContentPackage cp) {
	    this();
		_contentPackage = cp;

        setTitle(Messages.getString("uk.ac.reload.editor.contentpackaging.CP_Editor.0") + " - " + cp.getProjectName()); //$NON-NLS-1$ //$NON-NLS-2$
		
		_resourcesPanel.setFileView(cp.getProjectFolder());
		
		_manifestPanel.setContentPackage(cp);
		
		// Proxy Save Handlers
		_saveHandler = new ProxySaveHandler(MainMenu.getSharedInstance().actionSave);
		_saveAsHandler = new ProxySaveHandler(MainMenu.getSharedInstance().actionSaveAs);
		
		// Proxy Zip handler
		_zipHandler = new ProxyZipHandler();
		
		// Proxy View CP handler
		_viewCPHandler = new ProxyViewCPHandler();
		
		_exportPreviewHandler = new ProxyExportCPPreviewHandler();
		
		// We'll listen to edits
		cp.addXMLDocumentListener(this);
		
		// New Undo Manager
		_undoMenuManager = new UndoMenuManager(MainMenu.getSharedInstance().editMenu.actionUndo, MainMenu.getSharedInstance().editMenu.actionRedo);
		cp.setUndoHandler(_undoMenuManager.getUndoHandler());
		
		// CoolBar Panel needs to know
		_coolBarPanel.setDocument(cp);

		// Put it in the opened list - need to do this in case this is a New Document, not an Opened one
        EditorHandler.getSharedInstance().registerOpenedFile(cp.getFile(), this);
	}
	
	/**
	 * Over-ride this so we can set view stuff
	 */
	public void show() {
		super.show();
		_splitPane.setDividerLocation(0.25);
		_manifestPanel.initView();
		_resourcesPanel.setSelected(true);
	}
	
	
	//	==============================================================================
	//	EXPORT MANIFEST
	//	==============================================================================
	
	/**
	 * Export (Disaggregate) a sub-Manifest to a new folder
	 */
	public void exportManifest(Element manifestElement) {
		
		try {
			// Ask for Folder
		    CP_NewDialog dialog = new CP_NewDialog(Messages.getString("uk.ac.reload.editor.contentpackaging.CP_Editor.1")); //$NON-NLS-1$
			File targetFolder = dialog.showDialog();
			// User cancelled or Folder not valid
			if(targetFolder == null || !targetFolder.exists()) {
			    return;
			}
			
			// Do it
			boolean result = _contentPackage.exportManifest(manifestElement, targetFolder);
			
			// Show message
			if(result) {
				JOptionPane.showMessageDialog(this,
				        Messages.getString("uk.ac.reload.editor.contentpackaging.CP_Editor.2"), //$NON-NLS-1$
				        Messages.getString("uk.ac.reload.editor.contentpackaging.CP_Editor.3"), //$NON-NLS-1$
				        JOptionPane.INFORMATION_MESSAGE,
				        null);
			}
			else {
				JOptionPane.showMessageDialog(this,
				        Messages.getString("uk.ac.reload.editor.contentpackaging.CP_Editor.4"), //$NON-NLS-1$
				        Messages.getString("uk.ac.reload.editor.contentpackaging.CP_Editor.3"), //$NON-NLS-1$
				        JOptionPane.ERROR_MESSAGE,
				        null);
			}
		}
		catch(Exception ex) {
			ErrorDialogBox.showWarning(this,
			        Messages.getString("uk.ac.reload.editor.contentpackaging.CP_Editor.4"), //$NON-NLS-1$
			        Messages.getString("uk.ac.reload.editor.contentpackaging.CP_Editor.5"), //$NON-NLS-1$
			        ex);
		}
	}
	
	
	//	==============================================================================
	//	IMPORT MANIFEST
	//	==============================================================================
	
	/**
	 * Import a Package as a sub-Manifest.
	 * manifestElement is where we are going to graft it
	 */
	public void importManifest(Element manifestElement) {
		DweezilProgressMonitor progressMonitor = null;
		
		try {
			// Ask for Manifest/Zip to Import
			
			// Make a Filter
			javax.swing.filechooser.FileFilter filter = new javax.swing.filechooser.FileFilter() {
				public boolean accept(File file) {
					if(file.isDirectory()) {
					    return true;
					}
					return file.getName().equalsIgnoreCase(CP_Core.MANIFEST_NAME) || file.getName().toLowerCase().endsWith(".zip"); //$NON-NLS-1$
				}
				public String getDescription() {
					return Messages.getString("uk.ac.reload.editor.contentpackaging.CP_Editor.6"); //$NON-NLS-1$
				}
			};

			File manifestFile = DweezilFileChooser.askFileNameOpen(this, Messages.getString("uk.ac.reload.editor.contentpackaging.CP_Editor.7"), filter); //$NON-NLS-1$
			
			// User cancelled or Manifest file not valid
			if(manifestFile == null || !manifestFile.exists()) {
			    return;
			}
			
			// Make sure it's not the target!
			if(manifestFile.equals(_contentPackage.getFile())) {
				JOptionPane.showMessageDialog(this,
				        Messages.getString("uk.ac.reload.editor.contentpackaging.CP_Editor.8"), //$NON-NLS-1$
				        Messages.getString("uk.ac.reload.editor.contentpackaging.CP_Editor.9"), //$NON-NLS-1$
				        JOptionPane.WARNING_MESSAGE);
				return;
			}
			
			// File base
			File fileBase = null;
			
			// Do we already have a manifest base folder?
			String base = _contentPackage.getElementBase(manifestElement);
			if(base != null) {
				fileBase = new File(_contentPackage.getProjectFolder(), base);
			}
			else {
			    fileBase = _contentPackage.getProjectFolder();
			}
			
			// Ascertain sub-Folder
			File targetFolder = null;
			int i = 1;
			do {
				targetFolder = new File(fileBase, "submanifest" + i); //$NON-NLS-1$
				i++;
			}
			while(targetFolder.exists());
			
			// If it's a zip file, unzip contents to new folder
			if(manifestFile.getName().toLowerCase().endsWith(".zip")){ //$NON-NLS-1$
				// Check that archive contains a manifest file
				if(!CP_EditorHandler.containsManifest(manifestFile)) {
					// no manifest found in archive
					JOptionPane.showMessageDialog(EditorFrame.getInstance(),
							Messages.getString("uk.ac.reload.editor.contentpackaging.CP_Editor.43") + " " + CP_Core.MANIFEST_NAME + " " + //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
							Messages.getString("uk.ac.reload.editor.contentpackaging.CP_Editor.10"), //$NON-NLS-1$
							Messages.getString("uk.ac.reload.editor.contentpackaging.CP_Editor.11"), //$NON-NLS-1$
							JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				// Create the sub-folder
				targetFolder.mkdirs();
				
				progressMonitor = new DweezilProgressMonitor(EditorFrame.getInstance(),
				        Messages.getString("uk.ac.reload.editor.contentpackaging.CP_Editor.12"), //$NON-NLS-1$
						Messages.getString("uk.ac.reload.editor.contentpackaging.CP_Editor.13"), Messages.getString("uk.ac.reload.editor.contentpackaging.CP_Editor.14"), //$NON-NLS-1$ //$NON-NLS-2$
						true,
						DweezilUIManager.getIcon(ICON_APP16));
				
				// Unzip and get imsmanifest
				manifestFile = CP_EditorHandler.unzipContentPackage(manifestFile, targetFolder, progressMonitor);
				if(manifestFile == null) {
					progressMonitor.close();
					return;
				}
			}
			
			// Not a zip, so copy files to new folder
			else {
				progressMonitor = new DweezilProgressMonitor(EditorFrame.getInstance(),
				        Messages.getString("uk.ac.reload.editor.contentpackaging.CP_Editor.15"), //$NON-NLS-1$
						Messages.getString("uk.ac.reload.editor.contentpackaging.CP_Editor.16"), //$NON-NLS-1$
						Messages.getString("uk.ac.reload.editor.contentpackaging.CP_Editor.14"), //$NON-NLS-1$
						true,
						DweezilUIManager.getIcon(ICON_APP16));
				
				// Create the sub-folder
				targetFolder.mkdirs();
				
				// Copy files
				FileUtils.copyFolder(manifestFile.getParentFile(), targetFolder, progressMonitor);
			}
			
			// Graft Manifest file to manifestElement
			_contentPackage.importManifest(manifestFile, manifestElement, targetFolder.getName());
		}
		catch(Exception ex) {
			if(EditorProperties.getString("DEBUG").equals("true")) { //$NON-NLS-1$ //$NON-NLS-2$
			    ex.printStackTrace();
			}
			ErrorDialogBox.showWarning(this,
			        Messages.getString("uk.ac.reload.editor.contentpackaging.CP_Editor.17"), //$NON-NLS-1$
			        Messages.getString("uk.ac.reload.editor.contentpackaging.CP_Editor.18"), //$NON-NLS-1$
			        ex);
		}
		finally {
			if(progressMonitor != null) {
			    progressMonitor.close();
			}
			// Refresh Resources Panel
			_resourcesPanel.refreshResourceTree();
		}
		
	}
	
	//	==============================================================================
	//	==============================================================================
	//	==============================================================================
	
	/**
	 * Zip up the Content Package
	 */
	protected void zipIt() {
		if(_contentPackage != null) {
			// Check whether to save
			boolean doSave = checkNeedsSaving();
			if(doSave == false) {
			    return;
			}
			
			// Stop it happening again
			_zipHandler.setEnabled(false);
			
			CP_EditorHandler.zipContentPackage(_contentPackage);
			
			// OK
			_zipHandler.setEnabled(true);
		}
	}
	
	
	/**
	 * Add all .xsd (Schema) files to zip file
	 */
//	private static void addSchemaFilesToZip(File rootFolder, File folder, ZipOutputStream zOut) {
//	    File[] files = folder.listFiles();
//	    for(int i = 0; i < files.length; i++) {
//	        if(files[i].isDirectory()) addSchemaFilesToZip(rootFolder, files[i], zOut);
//	        else if(FileUtils.getFileExtension(files[i]).equals("xsd")) {
//	            String entryName = FileUtils.getRelativePath(rootFolder, files[i]);
//	            ZipUtils.addFileToZip(files[i], entryName, zOut);
//	        }
//	    }
//	}
	
	/**
	 * @return The ContentPackage in this editor
	 */
	public ContentPackage getContentPackage() {
		return _contentPackage;
	}
	

	//	=============================================================================
	//	Document Listener Events so we know when to set Save Menu
	//	=============================================================================
	
	public void elementAdded(XMLDocumentListenerEvent e) {
		if(isSelected()) {
		    _saveHandler.setEnabled(_contentPackage.isDirty());
		}
	}
	
	public void elementChanged(XMLDocumentListenerEvent e) {
		if(isSelected()) {
		    _saveHandler.setEnabled(_contentPackage.isDirty());
		}
	}
	
	public void elementRemoved(XMLDocumentListenerEvent e) {
		if(isSelected()) {
		    _saveHandler.setEnabled(_contentPackage.isDirty());
		}
	}
	
	public void documentSaved(XMLDocument doc) {
		if(isSelected()) {
		    _saveHandler.setEnabled(_contentPackage.isDirty());
		}
	}
	
	
	//	=============================================================================
	//	Focus Handler
	//	=============================================================================
	
	/**
	 * We got the focus
	 */
	private void setFocusGained() {
		if(_contentPackage != null) {
			_undoMenuManager.setFocusGained();
			
			// Pass this on
			_manifestPanel.getManifestTree().updateMenus();
			
			_zipHandler.setEnabled(true);
			_zipHandler.addListener();
			
			_viewCPHandler.setEnabled(true);
			_viewCPHandler.addListener();
			
			_exportPreviewHandler.setEnabled(true);
			_exportPreviewHandler.addListener();
			
			_saveHandler.setEnabled(_contentPackage.isDirty());
			_saveHandler.addListener();
			
			_saveAsHandler.setEnabled(true);  // Save As == Save Copy
			_saveAsHandler.addListener();
		}
	}
	
	/**
	 * We lost the focus
	 */
	private void setFocusLost() {
		_undoMenuManager.setFocusLost();
		
		// Pass this on
		_manifestPanel.getManifestTree().clearMenus();

		_zipHandler.clear();
		_viewCPHandler.clear();
		_exportPreviewHandler.clear();
		_saveHandler.clear();
		_saveAsHandler.clear();
	}
	
	
	//	=============================================================================
	//	Window Closing, Cleanup and Saving Handlers
	//	=============================================================================
	
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
		
		if(_contentPackage != null && _contentPackage.isDirty()) {
			String name = _contentPackage.getProjectName();
			String msg;
			if(name != null) {
			    msg = name + " " + Messages.getString("uk.ac.reload.editor.contentpackaging.CP_Editor.23"); //$NON-NLS-1$ //$NON-NLS-2$
			}
			else {
			    msg = Messages.getString("uk.ac.reload.editor.contentpackaging.CP_Editor.24"); //$NON-NLS-1$
			}
			
			int doSave = JOptionPane.showConfirmDialog(this,
					msg + " " + Messages.getString("uk.ac.reload.editor.contentpackaging.CP_Editor.25"), //$NON-NLS-1$ //$NON-NLS-2$
					Messages.getString("uk.ac.reload.editor.contentpackaging.CP_Editor.26"), //$NON-NLS-1$
					JOptionPane.YES_NO_CANCEL_OPTION);
			
			if(doSave == JOptionPane.YES_OPTION) {
			    isOK = saveDocument();
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
        // Have to check for nulls.  Since launching this Editor is on a thread,
        // if user closes the app before this window appears then these objects will be null

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
		
		if(_viewCPHandler != null) {
		    _viewCPHandler.clear();
		}
		
		if(_exportPreviewHandler != null) {
		    _exportPreviewHandler.clear();
		}
		
		// Cleanup Resources Panel
		if(_resourcesPanel != null) {
		    _resourcesPanel.cleanup();
		}
		
		// Destroy Manifest Editor and Manifest
		if(_contentPackage != null) {
		    _contentPackage.removeXMLDocumentListener(this);
		}
		
		if(_manifestPanel != null) {
		    _manifestPanel.cleanup();
		}
		
		// Content Package
		if(_contentPackage != null) {
		    _contentPackage.destroy();
		}
		
		// Undo Manager
		if(_undoMenuManager != null) {
		    _undoMenuManager.cleanup();
		}
	}
	
	/**
	 * If the manifest has been edited, ask whether we should save it
	 * @return true if OK, false if not
	 */
	protected boolean saveDocument() {
		// If we don't have a file name, ask for one
		if(_contentPackage.getFile() == null) {
		    return saveDocumentAskName();
		}
		
		try {
			_contentPackage.saveDocument();
		}
		catch(IOException ex) {
			ErrorDialogBox.showWarning(this,
			        Messages.getString("uk.ac.reload.editor.contentpackaging.CP_Editor.27") + ": ", //$NON-NLS-1$ //$NON-NLS-2$
			        Messages.getString("uk.ac.reload.editor.contentpackaging.CP_Editor.28"), ex); //$NON-NLS-1$
			return false;
		}
		return true;
	}
	
	/**
	 * Save and ask user for name
	 * @return false if user cancelled, else true
	 */
	protected boolean saveDocumentAskName() {
		// Ask for name
		DweezilFileFilter filter = new DweezilFileFilter(new String[] {
		        "xml"}, //$NON-NLS-1$
		        "xml files"); //$NON-NLS-1$
		
		File file = DweezilFileChooser.askFileNameSave(this,
		        Messages.getString("uk.ac.reload.editor.contentpackaging.CP_Editor.28"), //$NON-NLS-1$
		        filter,
		        "xml"); //$NON-NLS-1$
		
		// User cancelled
		if(file == null) {
		    return false;
		}
		
		// Set Title bar
		setTitle(Messages.getString("uk.ac.reload.editor.contentpackaging.CP_Editor.0") + " - " + file.getPath()); //$NON-NLS-1$ //$NON-NLS-2$
		
		try {
			_contentPackage.saveAsDocument(file);
		}
		catch(IOException ex) {
			ErrorDialogBox.showWarning(this,
			        Messages.getString("uk.ac.reload.editor.contentpackaging.CP_Editor.27") + ": ", //$NON-NLS-1$ //$NON-NLS-2$
			        Messages.getString("uk.ac.reload.editor.contentpackaging.CP_Editor.28"), //$NON-NLS-1$
			        ex);
			return false;
		}
		
		// Put it in the opened list
        EditorHandler.getSharedInstance().registerOpenedFile(_contentPackage.getFile(), this);
		
		return true;
	}
	
	/**
	 * Save a copy of this Content Package
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
			
			// Do it
			boolean result = _contentPackage.saveDocumentAs(targetFolder, progressMonitor);
			progressMonitor.close();
			
			// User cancelled
			if(!result) {
			    return false;
			}
			
			// New Title
			setTitle(Messages.getString("uk.ac.reload.editor.contentpackaging.CP_Editor.0") + " - " + _contentPackage.getProjectName()); //$NON-NLS-1$ //$NON-NLS-2$
			
			// Reset Resources Tree
			_resourcesPanel.setFileView(_contentPackage.getProjectFolder());

			// Reset Manifest Tree
			_manifestPanel.setContentPackage(_contentPackage);

			// Put it in the opened list
	        EditorHandler.getSharedInstance().registerOpenedFile(_contentPackage.getFile(), this);

	        // Show message
			JOptionPane.showMessageDialog(this,
			        Messages.getString("uk.ac.reload.editor.contentpackaging.CP_Editor.30"), //$NON-NLS-1$
			        Messages.getString("uk.ac.reload.editor.contentpackaging.CP_Editor.31"), //$NON-NLS-1$
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
	
	
	//	==============================================================================
	//	========================== COOLBAR PANEL =====================================
	//	==============================================================================

	/**
	 * The CoolBar Panel
	 */
	protected class CP_CoolBarPanel extends CoolBarPanel {
		
		/**
		 * Profile Combo box
		 */
		private DweezilComboBox profileCombobox;
		
		/**
		 * Constructor
		 */
		protected CP_CoolBarPanel(final ManifestPanel manifestPanel) {
			setMainComponent(manifestPanel);
		    
		    setText(Messages.getString("uk.ac.reload.editor.contentpackaging.CP_Editor.0")); //$NON-NLS-1$
		    setIcon(DweezilUIManager.getIcon(ICON_CP));
		    
		    // PROFILE COMBO BOX
		    addComponentToCoolBar(new JLabel(Messages.getString("uk.ac.reload.editor.contentpackaging.CP_Editor.41") + ": ")); //$NON-NLS-1$ //$NON-NLS-2$
			profileCombobox = new DweezilComboBox(false);
			addComponentToCoolBar(profileCombobox);
			
			// We'll forward on CoolBar focus to the Manifest tree
			addComponentSelectionListener(new IComponentSelectionListener() {
	            public void componentSelected(Component component) {
	                manifestPanel.getManifestTree().requestFocus();
	            }
			});
		}
		
		/**
		 * Initialise
		 */
		public void setDocument(ContentPackage cp) {
            // Set Icon
		    setIcon((cp instanceof SCORM12_Package) ? DweezilUIManager.getIcon(ICON_CPSCORM) : DweezilUIManager.getIcon(ICON_CP));
		    
		    // Populate it
            String[] profiles = ((ProfiledSchemaController)cp.getSchemaController()).getHelperProfileNames();
            profileCombobox.setItems(profiles);

            // Set selected
            String profile = ((ProfiledSchemaController)cp.getSchemaController()).getHelperProfile().getProfileName();
            profileCombobox.setSelectedItem(profile);

            // Listen to changes on the Profile Combo
            profileCombobox.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String profileName = (String)profileCombobox.getSelectedItem();
                    try {
                        ((ProfiledSchemaController)_contentPackage.getSchemaController()).loadHelperProfile(profileName);
                    } 
                    catch(Exception ex) {
                        if(EditorProperties.getString("DEBUG").equals("true")) { //$NON-NLS-1$ //$NON-NLS-2$
                            ex.printStackTrace();
                        }
                        ErrorDialogBox.showWarning(Messages.getString("uk.ac.reload.editor.contentpackaging.CP_Editor.41"), //$NON-NLS-1$
                                Messages.getString("uk.ac.reload.editor.contentpackaging.CP_Editor.42") + ": " + profileName, //$NON-NLS-1$ //$NON-NLS-2$
                                ex);
                    }
                }
            });
		}
	}
	

	
	//	==============================================================================
	//	====================== PROXY MENU HANDLERS ===================================
	//	==============================================================================

	/**
	 * Deals with Save Menu Events
	 */
	class ProxySaveHandler extends ProxyAction {
		public ProxySaveHandler(MenuAction menuAction) {
			super(menuAction);
		}
		
		public void menuActionPerformed(DweezilMenuEvent event) {
			// Save
		    if(isSelected() && event.getSource() == MainMenu.getSharedInstance().actionSave) {
			    saveDocument();
			}
			
		    // Save As...
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
	
	/**
	 * Deals with shared preview CP Menu Item from the main menu
	 */
	class ProxyViewCPHandler extends ProxyAction {
		public ProxyViewCPHandler() {
			super(MainMenu.getSharedInstance().actionViewCP);
		}
		
		// When user clicks 'preview CP' from the main menu...
		public void menuActionPerformed(DweezilMenuEvent event) {
			// On a thread...
		    Thread thread = new Thread() {
				public void run() {
				    try {
				        CP_Viewer cpViewer = new CP_Viewer(_contentPackage);
						cpViewer.launch();
				    }
				    catch (Exception ex) {    	
				        ErrorDialogBox.showWarning(CP_Editor.this,
				                Messages.getString("uk.ac.reload.editor.contentpackaging.CP_Editor.34"), //$NON-NLS-1$
				                Messages.getString("uk.ac.reload.editor.contentpackaging.CP_Editor.35"), //$NON-NLS-1$
				                ex);
				        return;
				    }
				}
			};
			
			if(isSelected()) {
			    thread.start();
			}
		}
	}
	
	/**
	 * Deals with the shared "preview export" item 
	 */         
	class ProxyExportCPPreviewHandler extends ProxyAction {
		public ProxyExportCPPreviewHandler(){
			super(MainMenu.getSharedInstance().exportPreview);
		}
		
		public void menuActionPerformed(DweezilMenuEvent event) {
			// On a thread...
		    Thread thread = new Thread() {
				public void run() {
				    DweezilProgressMonitor progressMonitor = null;
				    try {
					    // New Exporter
					    CP_Viewer cpViewer = new CP_Viewer(_contentPackage);
					    
					    // Ask for folder to export to
					    File exportFolder = cpViewer.askExportFolder();
					    // User cancelled
					    if(exportFolder == null) {
					        return;
					    }
					    
					    // Sanity check
					    if(!exportFolder.exists() || !exportFolder.isDirectory()) {
					        JOptionPane.showMessageDialog(CP_Editor.this,
					                Messages.getString("uk.ac.reload.editor.contentpackaging.CP_Editor.36"), //$NON-NLS-1$
					                Messages.getString("uk.ac.reload.editor.contentpackaging.CP_Editor.37"), //$NON-NLS-1$
					                JOptionPane.WARNING_MESSAGE);
					        return;
					    }
					    
					    // Progress Monitor
					    progressMonitor = new DweezilProgressMonitor(EditorFrame.getInstance(),
					            Messages.getString("uk.ac.reload.editor.contentpackaging.CP_Editor.38"), //$NON-NLS-1$
					            Messages.getString("uk.ac.reload.editor.contentpackaging.CP_Editor.16"), //$NON-NLS-1$
					            Messages.getString("uk.ac.reload.editor.contentpackaging.CP_Editor.16"), //$NON-NLS-1$
					            true, DweezilUIManager.getIcon(ICON_APP16));			
					    
					    // Do it
					    cpViewer.exportContentPackage(exportFolder, progressMonitor);
					}
					catch(IOException ex) {
					    if(EditorProperties.getString("DEBUG").equals("true")) { //$NON-NLS-1$ //$NON-NLS-2$
					        ex.printStackTrace();
					    }
					    ErrorDialogBox.showWarning(CP_Editor.this,
					            Messages.getString("uk.ac.reload.editor.contentpackaging.CP_Editor.39"), //$NON-NLS-1$
					            Messages.getString("uk.ac.reload.editor.contentpackaging.CP_Editor.37"), //$NON-NLS-1$
					            ex);
					    return;
					} 
					finally {
					    if(progressMonitor != null) {
					        progressMonitor.close();
					    }
					}
					
					JOptionPane.showMessageDialog(EditorFrame.getInstance(),
					        Messages.getString("uk.ac.reload.editor.contentpackaging.CP_Editor.40"),  //$NON-NLS-1$
					        Messages.getString("uk.ac.reload.editor.contentpackaging.CP_Editor.37"),  //$NON-NLS-1$
					        JOptionPane.INFORMATION_MESSAGE);	
					
				}
		    };
			
			if(isSelected()) thread.start();    	
		}
	}
}
