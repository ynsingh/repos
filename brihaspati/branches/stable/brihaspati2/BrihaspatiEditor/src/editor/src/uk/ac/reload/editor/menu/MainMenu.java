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

package uk.ac.reload.editor.menu;

import java.awt.Event;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.Icon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import uk.ac.reload.diva.util.GeneralUtils;
import uk.ac.reload.dweezil.gui.DweezilToolBar;
import uk.ac.reload.dweezil.menu.MenuAction;
import uk.ac.reload.dweezil.util.DweezilUIManager;
import uk.ac.reload.editor.EditorHandler;
import uk.ac.reload.editor.EditorInternalFrame;
import uk.ac.reload.editor.IEditorHandler;
import uk.ac.reload.editor.IIcons;
import uk.ac.reload.editor.Messages;
import uk.ac.reload.editor.prefs.EditorPrefs;
import uk.ac.reload.editor.properties.EditorProperties;




/**
 * The Main Menu for the Application.  It extends JMenuBar.  It also takes care
 * of the Toolbar and any main Popup menus.  It can listen to any refresh requests
 * so that for example, a change will set enabled and disabled menu items and buttons.
 * This class is tied in with the MainMenuAction and MenuAction classes.
 *
 * @author Phillip Beauvoir
 * @version $Id: MainMenu.java,v 1.1 1998/03/26 15:24:11 ynsingh Exp $
 */
public class MainMenu
extends JMenuBar
implements IIcons
{
	/**
	 * The Singleton instance
	 */
	private static MainMenu _sharedInstance;
	
	public static MainMenu getSharedInstance() {
	    if(_sharedInstance == null) {
	        _sharedInstance = new MainMenu();
	    }
	    return _sharedInstance;
	}
	
	public MenuAction_Save actionSave;
	public MenuAction_SaveAs actionSaveAs;
	public MenuAction_ImportResources actionImport;
	public MenuAction_ExportCPPreview exportPreview;
	public MenuAction_EditMetadata actionEditMetadata;
	public MenuAction_EditSCORM actionEditSCORM;
	public MenuAction_ZipIt actionZipIt;
	public MenuAction_ViewFile actionViewFile;
	public MenuAction_ViewCP actionViewCP;
	//public MenuAction_Refresh actionRefresh;
	
	/**
	 * The main application toolbar
	 */
	private DweezilToolBar toolBar;
	
	/**
	 * The Key Mask will be 'Meta' for Mac and 'Ctrl' for PC/Unix
	 */
	private int keyMask = Toolkit.getDefaultToolkit().getMenuShortcutKeyMask();
	
	/**
	 * Constructor
	 */
	private MainMenu() {
	    // Set up Actions
		actionSave = new MenuAction_Save();
		actionSaveAs = new MenuAction_SaveAs();
		actionImport = new MenuAction_ImportResources();
		exportPreview = new MenuAction_ExportCPPreview();
		actionEditMetadata = new MenuAction_EditMetadata();
		actionEditSCORM = new MenuAction_EditSCORM();
		actionZipIt = new MenuAction_ZipIt();
		actionViewFile = new MenuAction_ViewFile();
		actionViewCP = new MenuAction_ViewCP();
		//actionRefresh = new MenuAction_Refresh();
	    
		// Attach Actions to Menu Items
		createMenuBar();
		
		// Create the ToolBar
		toolBar = createToolBar();
	}
	
	/**
	 * Add an Internal Frame Window to the "Window" Menu Item.
	 * We get the Menu Item from the Frame itself.
	 * @param frame The frame to add to the menu
	 */
	public void addInternalFrame(EditorInternalFrame frame) {
		windowMenu.add(frame.getMenuItem());
	}
	
	/**
	 * Remove an Internal Frame Window from the "Window" Menu Item.
	 * We get the Menu Item from the Frame itself.
	 * @param frame The frame to remove from the menu
	 */
	public void removeInternalFrame(EditorInternalFrame frame) {
		windowMenu.remove(frame.getMenuItem());
	}
	
	// =========================== MENU BAR ==================================
	/**
	 * The File Menu
	 */
	public JMenu fileMenu;
	
	/**
	 * The Edit Menu
	 */
	public Menu_Edit editMenu;
	
	/**
	 * The Schema Viewer Menu
	 */
	public JMenu schemaViewerMenu;
	
	/**
	 * The Tools Menu
	 */
	public JMenu toolsMenu;
	
	/**
	 * The View Menu
	 */
	public JMenu viewMenu;
	
	/**
	 * The Window Menu
	 */
	public JMenu windowMenu;
	
	/**
	 * The Help Menu
	 */
	public JMenu helpMenu;
	
	/**
	 * Create the Menu Bar
	 */
	private void createMenuBar() {
		// File Menu
		fileMenu = createFileMenu();
		
		// Edit menu
		editMenu = new Menu_Edit();
		add(editMenu);
		
		// Schema Viewer menu
		if(EditorProperties.getString("DEBUG").equals("true")) { //$NON-NLS-1$ //$NON-NLS-2$
			schemaViewerMenu = createSchemaViewerMenu();
		}
		
		// Tools menu
		toolsMenu = createToolsMenu();
		
		// View Menu
		viewMenu = createViewMenu();
		
		// Window Menu
		windowMenu = createWindowMenu();
		
		// Help Menu
		helpMenu = createHelpMenu();
	}
	
	/**
	 * Create the File Menu
	 * @return The JMenu
	 */
	private JMenu createFileMenu() {
	    String menuText = Messages.getString("uk.ac.reload.editor.menu.MainMenu.0"); //$NON-NLS-1$
		JMenu jmenu = add(new JMenu(MenuAction.getRemoveMnemonicText(menuText)));
		jmenu.setMnemonic(MenuAction.getMnemonic(menuText));
		
		// New
		menuText = Messages.getString("uk.ac.reload.editor.menu.MainMenu.1"); //$NON-NLS-1$
		JMenu newMenu = new JMenu(MenuAction.getRemoveMnemonicText(menuText));
		newMenu.setMnemonic(MenuAction.getMnemonic(menuText));
		newMenu.setIcon(DweezilUIManager.getIcon(ICON_NEW));
		jmenu.add(newMenu);
		
		// Add Registered "New" Menu Items
		MenuAction[] newActions = EditorHandler.getSharedInstance().getNewDocumentMenuActions();
		for(int i = 0; i < newActions.length; i++) {
		    newMenu.add(newActions[i]);
        }
		
		// Open
		MenuAction_Open actionOpen = new MenuAction_Open();
		JMenuItem item = jmenu.add(actionOpen);
		item.setMnemonic(actionOpen.getMnemonic());
		
		// Import Resources
		item = jmenu.add(actionImport);
		item.setMnemonic(actionImport.getMnemonic());
		
		// Export
		//fileMenuText = "&Export";
		//JMenu exportMenu = new JMenu(MenuAction.getRemoveMnemonicText(fileMenuText));
		//exportMenu.setIcon(DweezilUIManager.getIcon(ICON_EXPORT));
		//jmenu.add(exportMenu);
		
		jmenu.addSeparator();
		
		// Save
		item = jmenu.add(actionSave);
		item.setMnemonic(actionSave.getMnemonic());
		item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, keyMask));
		
		// Save As
		item = jmenu.add(actionSaveAs);
		item.setMnemonic(actionSaveAs.getMnemonic());
		item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, Event.SHIFT_MASK + keyMask));
		
		jmenu.addSeparator();
		
		// Zip
		item = jmenu.add(actionZipIt);
		item.setMnemonic(actionZipIt.getMnemonic());
		
		// Save Content Package Previewer
		item = jmenu.add(exportPreview);
		item.setMnemonic(exportPreview.getMnemonic());

		jmenu.addSeparator();
		
		// Recent File List
		menuText = Messages.getString("uk.ac.reload.editor.menu.MainMenu.2"); //$NON-NLS-1$
		final JMenu recentMenu = new JMenu(MenuAction.getRemoveMnemonicText(menuText));
		recentMenu.setMnemonic(MenuAction.getMnemonic(menuText));
		jmenu.add(recentMenu);
		
		// Listen to this menu selection so we can populate it
		recentMenu.addMenuListener(new MenuListener() {
			public void menuSelected(MenuEvent e) {
				recentMenu.removeAll();
				String[] s = EditorPrefs.getInstance().getFileHistory();
				for(int i = 0; i < s.length; i++) {
					File file = new File(s[i]);
					recentMenu.add(new MenuAction_RecentOpen(i + 1, file));
				}
			}
			
			public void menuDeselected(MenuEvent e) {}
			
			public void menuCanceled(MenuEvent e) {}
		});
		
		if(GeneralUtils.getOS() != GeneralUtils.MACINTOSH) {
			jmenu.addSeparator();
			
			// Exit
			MenuAction_Exit actionExit = new MenuAction_Exit();
			item = jmenu.add(actionExit);
			item.setMnemonic(actionExit.getMnemonic());
		}
		
		return jmenu;
	}
	
	/**
	 * Create the Schema Viewer Menu
	 * @return The JMenu
	 */
	private JMenu createSchemaViewerMenu() {
		JMenu jmenu = add(new JMenu("Schemas")); //$NON-NLS-1$
		jmenu.setMnemonic('s');
		
		IEditorHandler[] handlers = EditorHandler.getSharedInstance().getRegisteredEditorHandlers();
		for(int i = 0; i < handlers.length; i++) {
            IEditorHandler handler = handlers[i];
            String[] versions = handler.getSupportedVersions();
            Icon icon = handler.getIcon();
            for(int j = 0; j < versions.length; j++) {
                String version = versions[j];
                jmenu.add(new JMenuItem(new MenuAction_ViewSchema(version, icon)));
            }
            jmenu.addSeparator();
        }
		return jmenu;
	}
	
	/**
	 * Create the Options Menu
	 * @return The JMenu
	 */
	private JMenu createToolsMenu() {
		JMenu jmenu = null;
		
		if(GeneralUtils.getOS() != GeneralUtils.MACINTOSH) {
		    String menuText = Messages.getString("uk.ac.reload.editor.menu.MainMenu.3"); //$NON-NLS-1$
			jmenu = add(new JMenu(MenuAction.getRemoveMnemonicText(menuText)));
			jmenu.setMnemonic(MenuAction.getMnemonic(menuText));
			
			//jmenu.addSeparator();
			MenuAction_Prefs actionOptions = new MenuAction_Prefs();
			JMenuItem item = jmenu.add(actionOptions);
			item.setMnemonic(actionOptions.getMnemonic());
		}
		
		return jmenu;
	}
	
	/**
	 * Create the View Menu
	 * @return The JMenu
	 */
	private JMenu createViewMenu() {
	    String menuText = Messages.getString("uk.ac.reload.editor.menu.MainMenu.4"); //$NON-NLS-1$
		JMenu jmenu = add(new JMenu(MenuAction.getRemoveMnemonicText(menuText)));
		jmenu.setMnemonic(MenuAction.getMnemonic(menuText));
		
		// Launch File
		JMenuItem item = jmenu.add(actionViewFile);
		item.setMnemonic(actionViewFile.getMnemonic());
		
		// View CP
		item = jmenu.add(actionViewCP);
		item.setMnemonic(actionViewCP.getMnemonic());
		
		// Refresh
		//item = jmenu.add(actionRefresh);
		//item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0));
		
		jmenu.addSeparator();
		
		// Status Window
		MenuAction_StatusWindow actionStatus = new MenuAction_StatusWindow();
		item = jmenu.add(actionStatus);
		item.setMnemonic(actionStatus.getMnemonic());
		
		return jmenu;
	}
	
	
	/**
	 * Create the Window Menu
	 * @return The JMenu
	 */
	private JMenu createWindowMenu() {
	    String menuText = Messages.getString("uk.ac.reload.editor.menu.MainMenu.5"); //$NON-NLS-1$
		JMenu jmenu = add(new JMenu(MenuAction.getRemoveMnemonicText(menuText)));
		jmenu.setMnemonic(MenuAction.getMnemonic(menuText));
		
		// Cascade
		MenuAction_CascadeWindow actionCascade = new MenuAction_CascadeWindow();
		JMenuItem item = jmenu.add(actionCascade);
		item.setMnemonic(actionCascade.getMnemonic());
		
		// Tile H
		MenuAction_TileHorizontalWindow actionTileHorizontal = new MenuAction_TileHorizontalWindow();
		item = jmenu.add(actionTileHorizontal);
		item.setMnemonic(actionTileHorizontal.getMnemonic());
		
		// Tile V
		MenuAction_TileVerticalWindow actionTileVertical = new MenuAction_TileVerticalWindow();
		item = jmenu.add(actionTileVertical);
		item.setMnemonic(actionTileVertical.getMnemonic());
		
		jmenu.addSeparator();
		
		return jmenu;
	}
	
	/**
	 * Create the Help Menu
	 * @return The JMenu
	 */
	private JMenu createHelpMenu() {
	    String menuText = Messages.getString("uk.ac.reload.editor.menu.MainMenu.6"); //$NON-NLS-1$
		JMenu jmenu = add(new JMenu(MenuAction.getRemoveMnemonicText(menuText)));
		jmenu.setMnemonic(MenuAction.getMnemonic(menuText));
		
		// Help
		MenuAction_Help actionHelp = new MenuAction_Help();
		JMenuItem item = jmenu.add(actionHelp);
		item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
		item.setMnemonic(actionHelp.getMnemonic());
		
		if(GeneralUtils.getOS() != GeneralUtils.MACINTOSH) {
			jmenu.addSeparator();
			
			// About
			MenuAction_About actionAbout = new MenuAction_About();
			item = jmenu.add(actionAbout);
			item.setMnemonic(actionAbout.getMnemonic());
		}
		
		return jmenu;
	}
	
	// =========================== TOOLBAR ==================================
	
	/**
	 * Create the Toolbar.
	 * @return The Toolbar
	 */
	private DweezilToolBar createToolBar() {
		DweezilToolBar tBar = new DweezilToolBar();
		
		// New
		MenuAction_New actionNew = new MenuAction_New();
		tBar.add(actionNew);
		
		// Open
		MenuAction_Open actionOpen = new MenuAction_Open();
		tBar.add(actionOpen);
		
		// Save
		tBar.add(actionSave);
		
		// Add a separator
		tBar.addSeparator();
		
		// Undo
		tBar.add(editMenu.actionUndo);
		
		// Redo
		tBar.add(editMenu.actionRedo);
		
		// Add a separator
		tBar.addSeparator();
		
		// Cut
		tBar.add(editMenu.actionCut);
		
		// Copy
		tBar.add(editMenu.actionCopy);
		
		// Paste
		tBar.add(editMenu.actionPaste);
		
		// Delete
		tBar.add(editMenu.actionDelete);
		
		// Add a separator
		tBar.addSeparator();
		
		// Move Up
		tBar.add(editMenu.actionMoveUp);
		
		// Move Down
		tBar.add(editMenu.actionMoveDown);
		
		// Add a separator
		tBar.addSeparator();
		
		// Edit Metadata
		tBar.add(actionEditMetadata);
		
		// Edit SCORM
		tBar.add(actionEditSCORM);
		
		// Add a separator
		tBar.addSeparator();
		
		// Zip
		tBar.add(actionZipIt);
		
		// View File
		tBar.add(actionViewFile);
		
		// View CP
		tBar.add(actionViewCP);
		
		return tBar;
	}
	
	/* 
	 * Workaround for Sun Java bug #4910521 where buttons grow big when changing L&F
	 * http://developer.java.sun.com/developer/bugParade/bugs/4910521.html
	 * @see javax.swing.JComponent#updateUI()
	 */
	public void updateUI() {
	    super.updateUI();
	    if(toolBar != null) {
	        toolBar.updateUI();
	    }
	}
	
	/**
	 * Get the ToolBar.
	 * @return The main toolbar
	 */
	public DweezilToolBar getToolBar() {
		return toolBar;
	}
}
