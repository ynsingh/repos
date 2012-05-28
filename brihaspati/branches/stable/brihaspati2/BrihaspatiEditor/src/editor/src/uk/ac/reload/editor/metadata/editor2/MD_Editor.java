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
 *  Technical Contact:
 *  Phillip Beauvoir
 *  e-mail:   p.beauvoir@bolton.ac.uk
 *  Web:      http://www.reload.ac.uk
 */

package uk.ac.reload.editor.metadata.editor2;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.*;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import uk.ac.reload.dweezil.gui.DweezilFileChooser;
import uk.ac.reload.dweezil.gui.DweezilFileFilter;
import uk.ac.reload.dweezil.gui.DweezilInternalFrameManager;
import uk.ac.reload.dweezil.gui.ErrorDialogBox;
import uk.ac.reload.dweezil.menu.DweezilMenuEvent;
import uk.ac.reload.dweezil.menu.MenuAction;
import uk.ac.reload.dweezil.menu.ProxyAction;
import uk.ac.reload.dweezil.util.DweezilUIManager;
import uk.ac.reload.dweezil.util.UIUtils;
import uk.ac.reload.editor.EditorHandler;
import uk.ac.reload.editor.EditorInternalFrame;
import uk.ac.reload.editor.IIcons;
import uk.ac.reload.editor.Messages;
import uk.ac.reload.editor.menu.MainMenu;
import uk.ac.reload.editor.metadata.MD_EditorHandler;
import uk.ac.reload.editor.metadata.datamodel.MD_DataModel;
import uk.ac.reload.editor.metadata.editor.tableview.MD_TreeTable;
import uk.ac.reload.editor.metadata.xml.Metadata;
import uk.ac.reload.jdom.XMLDocument;
import uk.ac.reload.jdom.XMLDocumentListener;
import uk.ac.reload.jdom.XMLDocumentListenerEvent;

/**
 * The Metadata Editor Frame
 * @author Phillip Beauvoir
 * @version $Id: MD_Editor.java,v 1.1 1998/03/26 15:32:14 ynsingh Exp $
 */
public class MD_Editor
extends EditorInternalFrame
implements XMLDocumentListener, IIcons
{
	/**
	 * The MD_DataModel
	 */
    private MD_DataModel _mdDataModel;

    /**
     * The Metadata Editor Panel
     */
    private MD_EditorPanel _mdEditorPanel;

    /**
     * Handles Save and SaveAs Events
     */
    private ProxySaveHandler _saveHandler, _saveAsHandler;

    /**
     * Constructor
     */
    public MD_Editor() {
        super(Messages.getString("uk.ac.reload.editor.metadata.MD_Editor.0"), ICON_MD); //$NON-NLS-1$

        // Ensure we handle closing
        setDefaultCloseOperation(JInternalFrame.DO_NOTHING_ON_CLOSE);

        // We listen for what this frame is doing
        addInternalFrameListener(new InternalFrameAdapter() {
            // The Frame is closing
            public void internalFrameClosing(InternalFrameEvent e) {
                // Make sure button stuff is completed
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        checkNeedsSaving();
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
        
        // New MD_EditorPanel Panel
        _mdEditorPanel = new MD_EditorPanel(MainMenu.getSharedInstance().editMenu);
        getContentPane().add(_mdEditorPanel);
    }
    
 
    /**
     * New Metadata Editor 
     * @param md
     */
    public MD_Editor(MD_DataModel mdDataModel) {
        this();
        
        _mdDataModel = mdDataModel;
        Metadata md = _mdDataModel.getMetadata();
        
        if(md.getFile() == null) {
            setTitle(Messages.getString("uk.ac.reload.editor.metadata.MD_Editor.0") + " - " + Messages.getString("uk.ac.reload.editor.metadata.MD_Editor.1")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        }
        else {
            setTitle(Messages.getString("uk.ac.reload.editor.metadata.MD_Editor.0") + " - " + md.getFile().getPath());  //$NON-NLS-1$ //$NON-NLS-2$
        }
        
        // Load
        _mdEditorPanel.setDataModel(mdDataModel);

        // Document listener
        md.addXMLDocumentListener(this);

        // Proxy Save Handlers
        _saveHandler = new ProxySaveHandler(MainMenu.getSharedInstance().actionSave);
        _saveAsHandler = new ProxySaveHandler(MainMenu.getSharedInstance().actionSaveAs);
    }

    /**
     * Over-ride this so we can set view stuff
     */
    public void show() {
        super.show();
        _mdEditorPanel.initView();
    }


    // =============================================================================
    // Document Listener Events so we know when to set Save Menu
    // =============================================================================

    public void elementAdded(XMLDocumentListenerEvent e) {
        if(isSelected()) {
            _saveHandler.setEnabled(_mdDataModel.getMetadata().isDirty());
        }
    }

    public void elementChanged(XMLDocumentListenerEvent e) {
        if(isSelected()) {
            _saveHandler.setEnabled(_mdDataModel.getMetadata().isDirty());
        }
    }

    public void elementRemoved(XMLDocumentListenerEvent e) {
        if(isSelected()) {
            _saveHandler.setEnabled(_mdDataModel.getMetadata().isDirty());
        }
    }

    public void documentSaved(XMLDocument doc) {
        if(isSelected()) {
            _saveHandler.setEnabled(_mdDataModel.getMetadata().isDirty());
        }
    }

    // =============================================================================
    // Focus Handler
    // =============================================================================

    /**
     * We got the focus
     */
    protected void setFocusGained() {
        if(_mdDataModel.getMetadata() != null) {
            _mdEditorPanel.getUndoManager().setFocusGained();

            // A bit long-winded to update the Tree menu!
            MD_TreeTable treeTable = _mdEditorPanel.getMD_TablePanel().getMD_TreeTable();
            if(treeTable != null) {
                treeTable.updateMenus();
            }

            _saveHandler.setEnabled(_mdDataModel.getMetadata().isDirty());
            _saveHandler.addListener();

            _saveAsHandler.setEnabled(true);
            _saveAsHandler.addListener();
        }
    }

    /**
     * We lost the focus
     */
    protected void setFocusLost() {
        _mdEditorPanel.getUndoManager().setFocusLost();
        
        // A bit long-winded to update the Tree menu!
        MD_TreeTable treeTable = _mdEditorPanel.getMD_TablePanel().getMD_TreeTable();
        if(treeTable != null) {
            treeTable.clearMenus();
        }

        _saveHandler.setEnabled(false);
        _saveHandler.removeListener();

        _saveAsHandler.setEnabled(false);
        _saveAsHandler.removeListener();
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
     * @return true if all is OK, false if not.
     */
    protected boolean checkNeedsSaving() {
        boolean isOK = true;
        
        Metadata md = _mdDataModel.getMetadata();

        if(md != null && md.isDirty()) {
        	File file = md.getFile();
            String msg;
            if(file != null) {
                msg = file.getName() + " " + Messages.getString("uk.ac.reload.editor.metadata.MD_Editor.2"); //$NON-NLS-1$ //$NON-NLS-2$
            }
            else {
                msg = Messages.getString("uk.ac.reload.editor.metadata.MD_Editor.3"); //$NON-NLS-1$
            }
        	
        	int doSave = JOptionPane.showConfirmDialog(this,
                msg + " " + //$NON-NLS-1$
                Messages.getString("uk.ac.reload.editor.metadata.MD_Editor.4"), //$NON-NLS-1$
                Messages.getString("uk.ac.reload.editor.metadata.MD_Editor.5"), //$NON-NLS-1$
                JOptionPane.YES_NO_CANCEL_OPTION);

            if(doSave == JOptionPane.YES_OPTION) {
                isOK = saveDocument();
            }
            else if(doSave == JOptionPane.CANCEL_OPTION) {
                isOK = false;
            }
        }

        if(isOK) {
            dispose();
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
        
        if(_saveHandler != null) {
            _saveHandler.removeListener();
        }
        
        if(_saveAsHandler != null) {
            _saveAsHandler.removeListener();
        }

        if(_mdEditorPanel != null) {
            Metadata md = _mdDataModel.getMetadata();
            if(md != null) {
                md.removeXMLDocumentListener(this);
            }
            _mdEditorPanel.cleanup();
        }
    }

    /**
     * If the Metadata file has been edited, ask whether we should save it
     * @return true if OK, false if not
     */
    protected boolean saveDocument() {
        // If we don't have a file name, ask for one
        if(_mdDataModel.getMetadata().getFile() == null) {
            return saveAsDocument();
        }

        try {
            _mdDataModel.getMetadata().saveDocument();
        }
        catch(IOException ex) {
            ErrorDialogBox.showWarning(this,
                    Messages.getString("uk.ac.reload.editor.metadata.MD_Editor.6"), //$NON-NLS-1$
                    Messages.getString("uk.ac.reload.editor.metadata.MD_Editor.5"), ex); //$NON-NLS-1$
            return false;
        }
        return true;
    }

    /**
     * Save As...
     * @return true if OK
     */
    protected boolean saveAsDocument() {
        // Ask for name
        DweezilFileFilter filter = new DweezilFileFilter(new String[] {"xml"}, "xml files"); //$NON-NLS-1$ //$NON-NLS-2$
        File file = DweezilFileChooser.askFileNameSave(this, Messages.getString("uk.ac.reload.editor.metadata.MD_Editor.5"), filter, "xml"); //$NON-NLS-1$ //$NON-NLS-2$
        // User cancelled
        if(file == null)  return false;

        // Set Title bar
        setTitle(Messages.getString("uk.ac.reload.editor.metadata.MD_Editor.0") + " - " + file.getPath()); //$NON-NLS-1$ //$NON-NLS-2$

        try {
            _mdDataModel.getMetadata().saveAsDocument(file);
        }
        catch(IOException ex) {
            ErrorDialogBox.showWarning(this,
                    Messages.getString("uk.ac.reload.editor.metadata.MD_Editor.6"), //$NON-NLS-1$
                    Messages.getString("uk.ac.reload.editor.metadata.MD_Editor.5"), //$NON-NLS-1$
                    ex);
            return false;
        }

        // Put it in the opened list
        EditorHandler.getSharedInstance().registerOpenedFile(file, this);

        return true;
    }


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
                saveAsDocument();
            }
        }
    }
    
    //  ==============================================================================
    //  ============================= Testing launcher ===============================
    //  ==============================================================================
	
    /**
	 * Testing launcher
	 * @param args
	 */
	public static void main(String[] args) {
	    System.setProperty("editor.properties.file", "uk.ac.reload.editor.properties.rb");
		System.setProperty("swing.noxp", "true");
	    
    	try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch(Exception ex) {
			ex.printStackTrace();
		}

		JFrame f = new JFrame();
	    f.setTitle("MD EDitor");
		
    	JDesktopPane deskTop = new JDesktopPane();
		f.setContentPane(deskTop);
		DweezilInternalFrameManager manager = new DweezilInternalFrameManager(deskTop);
		
        UIUtils.centreWindowProportional(f, 0.8, 0.8);
        f.setVisible(true);

		try {
		    f.setCursor(DweezilUIManager.WAIT_CURSOR);
		    MD_DataModel dataModel = MD_EditorHandler.loadNewTestInstance();
		    final MD_Editor editor = new MD_Editor(dataModel);
		    manager.addInternalFrame(editor, true);
		    editor.show();

		    // Add a listener to Close our window down on exit
		    f.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
				    editor.saveDocument();
				    System.exit(1);
				}
			});
		}
        catch(Exception ex) {
            ex.printStackTrace();
        }
		finally {
		    f.setCursor(DweezilUIManager.DEFAULT_CURSOR);
		}
        
    }
	
}
