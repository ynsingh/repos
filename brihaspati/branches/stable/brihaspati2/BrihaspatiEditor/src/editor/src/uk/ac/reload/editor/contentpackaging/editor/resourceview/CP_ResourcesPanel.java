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

package uk.ac.reload.editor.contentpackaging.editor.resourceview;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

import uk.ac.reload.diva.util.FileUtils;
import uk.ac.reload.dweezil.gui.CoolBarPanel;
import uk.ac.reload.dweezil.gui.DweezilProgressMonitor;
import uk.ac.reload.dweezil.gui.ErrorDialogBox;
import uk.ac.reload.dweezil.gui.IComponentSelectionListener;
import uk.ac.reload.dweezil.gui.YesAllNoDialog;
import uk.ac.reload.dweezil.gui.tree.DweezilTreeNode;
import uk.ac.reload.dweezil.gui.tree.DweezilTreePopupHandler;
import uk.ac.reload.dweezil.menu.DweezilMenuEvent;
import uk.ac.reload.dweezil.menu.MenuAction;
import uk.ac.reload.dweezil.menu.ProxyAction;
import uk.ac.reload.dweezil.util.DweezilUIManager;
import uk.ac.reload.dweezil.util.NativeLauncher;
import uk.ac.reload.editor.EditorFrame;
import uk.ac.reload.editor.IIcons;
import uk.ac.reload.editor.Messages;
import uk.ac.reload.editor.contentpackaging.datamodel.CP_Resource;
import uk.ac.reload.editor.menu.MainMenu;
import uk.ac.reload.editor.menu.MenuAction_ImportResources;
import uk.ac.reload.editor.properties.EditorProperties;


/**
 * Resources Tree Panel
 *
 * @author Phillip Beauvoir
 * @author Paul Sharples
 * @version $Id: CP_ResourcesPanel.java,v 1.1 1998/03/26 15:40:32 ynsingh Exp $
 */
public class CP_ResourcesPanel
extends CoolBarPanel
implements IIcons
{
	/**
	 * The Resources Tree
	 */
    private CP_ResourcesTree _resourcesTree;
	
	/**
	 * The Root Folder
	 */
    private File _rootFolder;
	
	/**
	 * Popup Menu Handler
	 */
    private DweezilTreePopupHandler _popupMenuHandler;
	
	/**
	 * Refresh Menu Action
	 */
    private MenuAction_Refresh _actionRefresh;
	
	/**
	 * Delete Resources Menu Action
	 */
    private MenuAction_DeleteResources _actionDeleteResources;
	
	/**
	 * New folder Menu Action
	 */
    private MenuAction_NewFolder _actionNewFolder;
	
	/**
	 * Our import handler from the main menu and panel
	 */
    private ProxyImportHandler _proxyImportHandler, _proxyImportHandlerLocal;
	
	/**
	 * The Proxy View File Handler
	 */
    private ProxyLaunchFileHandler _proxylaunchFileHandler;
	
	/**
	 * Constructor
	 */
	public CP_ResourcesPanel() {
	    setText(Messages.getString("uk.ac.reload.editor.contentpackaging.resourceview.CP_ResourcesPanel.11")); //$NON-NLS-1$
	    setIcon(DweezilUIManager.getIcon(ICON_RESOURCES));
	    
		// Import handler local
		MenuAction_ImportResources importMenuAction = new MenuAction_ImportResources();
		importMenuAction.setEnabled(true);
		_proxyImportHandlerLocal = new ProxyImportHandler(importMenuAction);
		
		// Proxy Import handler from Main menu
		_proxyImportHandler = new ProxyImportHandler(MainMenu.getSharedInstance().actionImport);
		
		// Refresh
		_actionRefresh = new MenuAction_Refresh();
		
		// Delete
		_actionDeleteResources = new MenuAction_DeleteResources();
		
		// New Folder
		_actionNewFolder = new MenuAction_NewFolder();
		
		// View File handler
		_proxylaunchFileHandler = new ProxyLaunchFileHandler();

		// CoolBar buttons
		addMenuActionToCoolBar(_proxyImportHandlerLocal.getMenuAction());
		addMenuActionToCoolBar(_actionNewFolder);
		addMenuActionToCoolBar(_actionDeleteResources);
		addMenuActionToCoolBar(_actionRefresh);
		
		// Tree
		_resourcesTree = new CP_ResourcesTree();
		JScrollPane sp = new JScrollPane(_resourcesTree);
		sp.setBorder(null);
		setMainComponent(sp);
		
		// We'll forward on CoolBar focus to the tree
		addComponentSelectionListener(new IComponentSelectionListener() {
            public void componentSelected(Component component) {
                _resourcesTree.requestFocus();
            }
		});
		
		// Tree Selection Listener
		_resourcesTree.addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent e) {
			    // Only interested in new selections
			    if(!e.isAddedPath()) {
			        return;
			    }
			    
			    updateMenus();
			}
		});

		// Our proxy handlers need to listen to tree focus as well as tree selection events
		_resourcesTree.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
				if(!e.isTemporary()) {
				    _proxylaunchFileHandler.update();
				    _proxyImportHandler.update();
				}
			}
			
			public void focusLost(FocusEvent e) {
				if(!e.isTemporary()) {
				    _proxylaunchFileHandler.clear();
				    _proxyImportHandler.clear();
				}
			}
		});

	}
	
	/**
	 * Set the File View
	 */
	public void setFileView(File rootFolder) {
		_rootFolder = rootFolder;
		_resourcesTree.setFileView(rootFolder);
	}
	
    /**
	 * Clean up
	 */
	public void cleanup() {
		clearMenus();
	}
	
	/**
	 * Update our buttons and menus depending on resource selected
	 */
	private synchronized void updateMenus() {
	    // Delete
	    _actionDeleteResources.update();
		
		// New Folder
	    _actionNewFolder.update();
		
		// Import Files
		_proxyImportHandler.update();
        _proxyImportHandlerLocal.update();
        
        // View File
        _proxylaunchFileHandler.update();

        // Popup Menu - best to Remove all and re-add them
        _resourcesTree.getPopupMenu().removeAll();
        _resourcesTree.getPopupMenu().add(_proxyImportHandlerLocal.getMenuAction());
        _resourcesTree.getPopupMenu().add(_actionNewFolder);
        _resourcesTree.getPopupMenu().add(_actionDeleteResources);
        _resourcesTree.getPopupMenu().add(_proxylaunchFileHandler.getMenuAction());
	}
	
    /**
	 * Clear any menus
	 */
	private synchronized void clearMenus() {
		_proxyImportHandler.clear();
		_proxyImportHandlerLocal.clear();
	    _proxylaunchFileHandler.clear();
	}

	//	=============================================================================
	
	
	/**
	 * Dialogue routine to allow user to import a new resource or folder
	 * Checks to see if the user wants to import dependent files and
	 * then instantiates CP_ResourceImporter to bring in the files
	 * Finally, it refreshes the resource tree to show the new listing
	 */
	protected void importResources(CP_Resource folder){
		CP_ResourceChooser fileChooserOption = new CP_ResourceChooser();
		
		int returnVal = fileChooserOption.showOpenDialog(EditorFrame.getInstance());
		
		// User Cancelled
		if(returnVal != JFileChooser.APPROVE_OPTION) {
		    return;
		}
		
		// Get the chosen File(s)
		final File[] files = fileChooserOption.getSelectedFilesAndStore();
		
		// Set the checkbox to remember if it was selected previously
		//fileChooserOption.setCheckbox(fileChooserOption.isCheckboxChecked());
		
		DweezilProgressMonitor progressMonitor = null;
		
		try {
			// Disable buttons
			_proxyImportHandler.setEnabled(false);
			_proxyImportHandlerLocal.setEnabled(false);
			
			progressMonitor = new DweezilProgressMonitor(EditorFrame.getInstance(),
			        Messages.getString("uk.ac.reload.editor.contentpackaging.resourceview.CP_ResourcesPanel.0"), //$NON-NLS-1$
					Messages.getString("uk.ac.reload.editor.contentpackaging.resourceview.CP_ResourcesPanel.1"), //$NON-NLS-1$
					"", //$NON-NLS-1$
					true,
					DweezilUIManager.getIcon(ICON_APP16));
			
			// Call routine
			CP_ResourceImporter importRes = new CP_ResourceImporter(files, folder,
					fileChooserOption.isCheckboxChecked(), progressMonitor);
			
			// Only refresh the tree if the user imported a file
			if(importRes.hasImportedFiles()) {
			    refreshResourceTree();
			}
		}
		catch(Exception ex) {
            if(EditorProperties.getString("DEBUG").equals("true")) { //$NON-NLS-1$ //$NON-NLS-2$
                ex.printStackTrace();
            }
		    ErrorDialogBox.showWarning(Messages.getString("uk.ac.reload.editor.contentpackaging.resourceview.CP_ResourcesPanel.0"), //$NON-NLS-1$
		            Messages.getString("uk.ac.reload.editor.contentpackaging.resourceview.CP_ResourcesPanel.2"), //$NON-NLS-1$
		            ex);
		}
		finally {
			if(progressMonitor != null) {
			    progressMonitor.close();
			}
			// Enable buttons
			_proxyImportHandler.setEnabled(true);
			_proxyImportHandlerLocal.setEnabled(true);
		}
	}
	
	/**
	 * Delete resources from the file system
	 * @param resources
	 * @return True if something was deleted
	 * @throws IOException
	 */
	protected boolean deleteResources(CP_Resource[] resources) throws IOException {
		boolean yesToAll = false;
		boolean hasDeleted = false;
		
		for(int i = 0; i < resources.length; i++) {
			CP_Resource aFile = resources[i];
			// Might be deleted now if parent was deleted, so check
			if(!aFile.exists()) {
			    continue;
			}
			
			// NOT Yes To All
			if(!yesToAll) {
				int answer = askConfirmDeleteFile(aFile);
				if(answer == YesAllNoDialog.YES_TO_ALL) {
				    yesToAll = true;
				}
				
				// Yes or Yes to all
				if(answer == YesAllNoDialog.YES || answer == YesAllNoDialog.YES_TO_ALL) {
					if(aFile.isDirectory()) {
					    FileUtils.deleteFolder(aFile);
					    hasDeleted = true;
					}
					else {
					    aFile.delete();
					    hasDeleted = true;
					}
				}
				// Cancel
				else if(answer == YesAllNoDialog.CANCEL || answer == YesAllNoDialog.CLOSE) {
					return hasDeleted;
				}
			}
			
			// YES TO ALL 
			else {
				if(aFile.isDirectory()) {
					FileUtils.deleteFolder(aFile);
					hasDeleted = true;
				}
				else {
				    aFile.delete();
				    hasDeleted = true;
				}
			}
		}
		
		return hasDeleted;
	}
	
	/**
	 * Ask user if they want to delete a file
	 * and return the users choice
	 * @param res - a CP_Resource object
	 * @return users choice for yes, yes to all, no, cancel or close
	 */
	protected int askConfirmDeleteFile(CP_Resource res){
		YesAllNoDialog dialog = new YesAllNoDialog();
		String fileOrFolder = res.isDirectory() ? Messages.getString("uk.ac.reload.editor.contentpackaging.resourceview.CP_ResourcesPanel.3") : Messages.getString("uk.ac.reload.editor.contentpackaging.resourceview.CP_ResourcesPanel.4"); //$NON-NLS-1$ //$NON-NLS-2$
		String message = res.getAbsoluteFile().toString() + System.getProperty("line.separator") + //$NON-NLS-1$
			Messages.getString("uk.ac.reload.editor.contentpackaging.resourceview.CP_ResourcesPanel.5") + " " + fileOrFolder + "?"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		return dialog.getUserResponse(EditorFrame.getInstance(), message, Messages.getString("uk.ac.reload.editor.contentpackaging.resourceview.CP_ResourcesPanel.6")); //$NON-NLS-1$
	}

	/**
	 * Refresh the Resource tree after changes
	 */
	public void refreshResourceTree(){
		_resourcesTree.refresh();
	}
	
	//	==============================================================================
	//	================================= MENU HANDLERS ==============================
	//	==============================================================================
	
	/**
	 * Deals with Import Menu Events
	 */
	class ProxyImportHandler extends ProxyAction {
		CP_Resource _resource;
		
		public ProxyImportHandler(MenuAction menuAction) {
			super(menuAction);
		}
		
		public void update() {
		    DweezilTreeNode node = _resourcesTree.getSelectedNode();
		    if(node == null) {
		        return;
		    }
		    
		    _resource = (CP_Resource)node.getUserObject();

		    if(_resource.isDirectory()) {
		        setEnabled(true);
		        addListener();
		    }
			else {
			    clear();
			}
		}
		
		public void menuActionPerformed(DweezilMenuEvent event) {
			Thread thread = new Thread() {
				public void run() {
					if(_resource != null) {
					    importResources(_resource);
					}
				}
			};
			
			thread.start();
		}
	}
	
	/**
	 * Deals with delete resource Events
	 */
	class MenuAction_DeleteResources extends MenuAction {
		
	    public MenuAction_DeleteResources() {
		    super(Messages.getString("uk.ac.reload.editor.menu.MenuAction_DeleteResources.0"), ICON_DELETE); //$NON-NLS-1$
		}
		
		public void update() {
		    DweezilTreeNode node = _resourcesTree.getSelectedNode();
		    if(node == null) {
		        return;
		    }
		    
			// Make sure the delete resource button is disabled if root selected
		    CP_Resource resource = (CP_Resource)node.getUserObject();
			setEnabled(!resource.equals(_rootFolder));
		}

		// when user clicks 'remove resource' from the resources panel...
	    public void actionPerformed(ActionEvent e) {
			try {
			    // Ascertain selected files to remove
			    CP_Resource[] resources = _resourcesTree.getSelectedResourcesToDelete();
                // Delete them
			    boolean hasDeleted = deleteResources(resources);
			    if(hasDeleted) {
			        // Select the root node
			        _resourcesTree.setSelectionRow(0);
			        // Refresh
			        _resourcesTree.refresh();
			    }
                
            } catch(IOException ex) {
                if(EditorProperties.getString("DEBUG").equals("true")) { //$NON-NLS-1$ //$NON-NLS-2$
                    ex.printStackTrace();
                }
                ErrorDialogBox.showWarning(Messages.getString("uk.ac.reload.editor.contentpackaging.resourceview.CP_ResourcesPanel.7"), Messages.getString("uk.ac.reload.editor.contentpackaging.resourceview.CP_ResourcesPanel.8"), ex); //$NON-NLS-1$ //$NON-NLS-2$
            }
		}
	}
	
	/**
	 * Deals with refresh Menu Events
	 */
	class MenuAction_Refresh extends MenuAction {
		
	    public MenuAction_Refresh() {
		    super(Messages.getString("uk.ac.reload.editor.menu.MenuAction_Refresh.0"), ICON_REFRESH); //$NON-NLS-1$
		}
		
		// when user clicks 'import' from the main menu...
	    public void actionPerformed(ActionEvent e) {
			Thread thread = new Thread() {
				public void run() {
					setCursor(DweezilUIManager.WAIT_CURSOR);
					setEnabled(false);
					refreshResourceTree();
					setEnabled(true);
					setCursor(DweezilUIManager.DEFAULT_CURSOR);
				}
			};
			
			thread.start();
		}
	}
	
	/**
	 * New Folder Action
	 */
	class MenuAction_NewFolder extends MenuAction {
		CP_Resource _resource;
		
		public MenuAction_NewFolder() {
		    super(Messages.getString("uk.ac.reload.editor.menu.MenuAction_NewFolder.0"), ICON_FOLDER_NEW); //$NON-NLS-1$
		}
		
		public void update() {
		    DweezilTreeNode node = _resourcesTree.getSelectedNode();
		    if(node == null) {
		        return;
		    }
		    
		    _resource = (CP_Resource)node.getUserObject();
			setEnabled(_resource.isDirectory());
		}
		
		/**
		 * Make a New Folder
		 * @param event
		 */
		public void actionPerformed(ActionEvent e) {
			if(_resource != null) {
				String s = JOptionPane.showInputDialog(EditorFrame.getInstance(),
				        Messages.getString("uk.ac.reload.editor.contentpackaging.resourceview.CP_ResourcesPanel.9"), //$NON-NLS-1$
				        Messages.getString("uk.ac.reload.editor.contentpackaging.resourceview.CP_ResourcesPanel.10"), //$NON-NLS-1$
				        JOptionPane.PLAIN_MESSAGE);
				if(s != null) {
					File file = new File(_resource, s);
					if(!file.exists()) {
						file.mkdir();
						refreshResourceTree();
					}
				}
			}
		}
	}

	/**
	 * Launch File Proxy Action
	 */
	class ProxyLaunchFileHandler extends ProxyAction {
		CP_Resource _resource;
		
		public ProxyLaunchFileHandler() {
			super(MainMenu.getSharedInstance().actionViewFile);
		}
		
		public void update() {
		    DweezilTreeNode node = _resourcesTree.getSelectedNode();
		    if(node == null) {
		        return;
		    }
		    
		    _resource = (CP_Resource)node.getUserObject();
			if(_resource.isFile()) {
			    setEnabled(true);
			    addListener();
			}
			else {
			    clear();
			}
		}
		
		/**
		 * Launch the file
		 * @param event
		 */
		public void menuActionPerformed(DweezilMenuEvent event) {
			if(_resource != null) {
				// Start the loader thread
				Thread thread = new Thread() {
					public void run() {
						setCursor(DweezilUIManager.WAIT_CURSOR);
						NativeLauncher.launchFile(_resource);
						setCursor(DweezilUIManager.DEFAULT_CURSOR);
					}
				};
				
				thread.start();
			}
		}

	}

}