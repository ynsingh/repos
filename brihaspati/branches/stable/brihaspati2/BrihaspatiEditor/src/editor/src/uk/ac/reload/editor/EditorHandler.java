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

package uk.ac.reload.editor;

import java.awt.event.ActionEvent;
import java.beans.PropertyVetoException;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

import uk.ac.reload.dweezil.gui.*;
import uk.ac.reload.dweezil.menu.MenuAction;
import uk.ac.reload.dweezil.util.DweezilUIManager;
import uk.ac.reload.editor.contentpackaging.CP_EditorHandler;
import uk.ac.reload.editor.learningdesign.LD_EditorHandler;
import uk.ac.reload.editor.menu.MainMenu;
import uk.ac.reload.editor.metadata.MD_EditorHandler;
import uk.ac.reload.editor.prefs.EditorPrefs;
import uk.ac.reload.editor.properties.EditorProperties;
import uk.ac.reload.editor.scorm.SCORM12_EditorHandler;
import uk.ac.reload.editor.simplesequencing.SS_EditorHandler;
import uk.ac.reload.moonunit.SchemaController;
import uk.ac.reload.editor.EditorFrame;

/**
 * Description
 *
 * @author Phillip Beauvoir
 * @version $Id: EditorHandler.java,v 1.1 1998/03/26 15:24:11 ynsingh Exp $
 */
public final class EditorHandler
implements InternalFrameListener
{

    /**
	 * Helpers root Folder
	 */
	public static File HELPERFOLDER = EditorProperties.getFileProperty("helpers.dir"); //$NON-NLS-1$
    
    /**
	 * Schema root Folder
	 */
	public static File SCHEMAROOTFOLDER = EditorProperties.getFileProperty("schema.dir"); //$NON-NLS-1$
	
	/**
	 * Schema model Folder
	 */
	public static File SCHEMAMODELFOLDER = new File(SCHEMAROOTFOLDER, "model"); //$NON-NLS-1$

	/**
	 * Schema shipped Folder
	 */
	public static File SCHEMASHIPPEDFOLDER = new File(SCHEMAROOTFOLDER, "shipped"); //$NON-NLS-1$
	
	// ==================================================================================================

	// Static inbuilt Handlers
	public static MD_EditorHandler MD_EDITORHANDLER = new MD_EditorHandler();
	public static CP_EditorHandler CP_EDITORHANDLER = new CP_EditorHandler();
	public static SCORM12_EditorHandler SCORM12_EDITORHANDLER = new SCORM12_EditorHandler();
	public static LD_EditorHandler LD_EDITORHANDLER = new LD_EditorHandler();
	
	
	// ==================================================================================================
	
	/**
	 * The Single Shared Instance
	 */
	private static EditorHandler _sharedInstance;
	
	/**
	 * @return The Single Shared Instance
	 */
	public static EditorHandler getSharedInstance() {
	    if(_sharedInstance == null) {
	        _sharedInstance = new EditorHandler();
	    }
	    return _sharedInstance;
	}
	
	/**
	 * Private Constructor
	 */
	private EditorHandler() {
	    
	}
	
	/**
	 * Register the Main app's JDesktopPane so we can handle internal frames
	 * @param deskTop
	 */
	public void registerDesktop(JDesktopPane deskTop) {
		_internalFrameManager = new DweezilInternalFrameManager(deskTop);
	}
	
    /**
     * Register Inbuilt Editor Handlers
     */
    public void registerInbuiltEditorHandlers() {
        // Metadata Editor
        registerEditorHandler(MD_EDITORHANDLER);
	    
		// Content Packaging
        registerEditorHandler(CP_EDITORHANDLER);

		// SCORM 1.2
        registerEditorHandler(SCORM12_EDITORHANDLER);

		// Learning Design
        registerEditorHandler(LD_EDITORHANDLER);
		
		// Simple Sequencing
        if(EditorProperties.getString("DEBUG").equals("true")) { //$NON-NLS-1$ //$NON-NLS-2$
            registerEditorHandler(new SS_EditorHandler()); 
        }
    }
    
	/**
	 * Ask and Open a file, determine what it is, load it, get the SchemaController
	 * and open an appropriate Editor
	 */
	public void openFile() {
		// Ask for file
		DweezilFileFilter filter = new DweezilFileFilter(
		        new String[] {"xml", "zip"}, 				//$NON-NLS-1$ //$NON-NLS-2$
		        Messages.getString("uk.ac.reload.editor.EditorHandler.1"));    //$NON-NLS-1$
		
		File file = EditorOpenDialog.askFileNameOpen(null, Messages.getString("uk.ac.reload.editor.EditorHandler.2"), filter); //$NON-NLS-1$
		if(file == null) {
		    return;
		}
		
		openFile(file);
	}
    
	/**
	 * Open a file by asking a registered Editor to deal with it
	 * @param file
	 */
	public void openFile(File file) {
		// If we already have it open select and maximise it
		DweezilInternalFrame frame = DweezilInternalFrameManager.getInternalFrame(file);
		if(frame != null) {
			try {
				frame.setIcon(false);
				frame.setSelected(true);
			}
			catch(PropertyVetoException ex) {}
			return;
		}
		
		// Then check it exists
		if(!file.exists()) {
		    JOptionPane.showMessageDialog(EditorFrame.getInstance(),
		            Messages.getString("uk.ac.reload.editor.EditorHandler.3") + ": " + file, //$NON-NLS-1$ //$NON-NLS-2$
		            Messages.getString("uk.ac.reload.editor.EditorHandler.4"), //$NON-NLS-1$
		            JOptionPane.WARNING_MESSAGE); 
		    return;
		}
		
		// OK, we don't have it open so see if we have a registered Editor
		IEditorHandler handler = null;
		
		try {
			// Wait cursor
			EditorFrame.getInstance().setCursor(DweezilUIManager.WAIT_CURSOR);
			
			// Get a Handler
			handler = getEditorHandler(file);

			if(handler != null) {
		        EditorInternalFrame iframe = handler.editFile(file);
		        if(iframe == null) return;
		        
		        final EditorInternalFrame iframe2 = iframe;
		        SwingUtilities.invokeLater(new Runnable() {
		            public void run() {
		                addInternalFrame(iframe2);
		                iframe2.show();
		            }
		        });
		        
		        // OK, so if it's being edited add it to the list of opened files
		        registerOpenedFile(file, iframe);
		    }
		    
		    else {
				JOptionPane.showMessageDialog(EditorFrame.getInstance(),
				        Messages.getString("uk.ac.reload.editor.EditorHandler.5") +  "\r\n" + //$NON-NLS-1$ //$NON-NLS-2$
				        Messages.getString("uk.ac.reload.editor.EditorHandler.6"),   //$NON-NLS-1$
				        EditorProperties.getString("APP_NAME"),   //$NON-NLS-1$
						JOptionPane.WARNING_MESSAGE);
		    }
		    
		}
		catch(Exception ex) {
		    if(EditorProperties.getString("DEBUG").equals("true")) { //$NON-NLS-1$ //$NON-NLS-2$
		        ex.printStackTrace(); 
		    }
		    ErrorDialogBox.showWarning(Messages.getString("uk.ac.reload.editor.EditorHandler.7") + ": " + file.getName(), //$NON-NLS-1$ //$NON-NLS-2$
		            Messages.getString("uk.ac.reload.editor.EditorHandler.7"), ex);  //$NON-NLS-1$
		} 
		finally {
			EditorFrame.getInstance().setCursor(DweezilUIManager.DEFAULT_CURSOR);
		}
	}
	
	/**
	 * Create a New Document
	 * @param handler
	 * @return The Editor Fame
	 */
	public EditorInternalFrame newDocument(IEditorHandler handler) {
	    EditorInternalFrame iframe = null;
	    
	    try {
			// Wait cursor
			EditorFrame.getInstance().setCursor(DweezilUIManager.WAIT_CURSOR);

			iframe = handler.newDocument();
            if(iframe != null) {
                addInternalFrame(iframe);
                iframe.show();
            }
        }
        catch(Exception ex) {
		    if(EditorProperties.getString("DEBUG").equals("true")) { //$NON-NLS-1$ //$NON-NLS-2$
		        ex.printStackTrace(); 
		    }
		    ErrorDialogBox.showWarning(Messages.getString("uk.ac.reload.editor.EditorHandler.8"), //$NON-NLS-1$ 
		            Messages.getString("uk.ac.reload.editor.EditorHandler.9"), ex); //$NON-NLS-1$
        }
        finally {
            EditorFrame.getInstance().setCursor(DweezilUIManager.DEFAULT_CURSOR);
        }
	    
        return iframe;
	}

	/**
	 * @return All Registered Editors' "New" Menu Actions
	 */
	public MenuAction[] getNewDocumentMenuActions() {
	    Vector v = new Vector();

	    IEditorHandler[] handlers = getRegisteredEditorHandlers();
	    for(int i = 0; i < handlers.length; i++) {
	        final IEditorHandler handler = handlers[i];
	        
	        // Only if you can do it
	        if(handler.canCreateDocuments()) {
	            MenuAction action = new MenuAction() {
	                public void actionPerformed(ActionEvent e) {
	                    // Loading of Document on Thread
	                    Thread thread = new Thread() {
	                        public void run() {
	                            newDocument(handler);
	                        }
	                    };
	                    
	                    thread.start();
	                }
	            };
	            
	            action.setMenuIcon(handler.getIcon());
	            action.setText(handler.getName());
	            
	            v.addElement(action);
	        }
	    }
	    
	    MenuAction[] actions = new MenuAction[v.size()];
	    v.copyInto(actions);
	    return actions;
	}
	
	/**
	 * Search through all registered Handlers and try to find a SchemaController Instance
	 * from a given version name.
	 * 
	 * @param version
	 * @return A SchemaController Intance by version or null if not found.
	 */
	public SchemaController getSchemaControllerInstance(String version) {
	    // Iterate thru handlers and find a controller
		IEditorHandler[] handlers = getRegisteredEditorHandlers();
		for(int i = 0; i < handlers.length; i++) {
            try {
                SchemaController schemaController = handlers[i].getSchemaControllerInstance(version);
                if(schemaController != null) {
                    return schemaController;
                }
            } catch(Exception ex) {
                // We catch the exception so we can ask all handlers
                ex.printStackTrace();
            }
		}
	    
		return null;
	}
	
	/**
	 * Register to the host that a file is being handled by a frame.
	 * This means that if the user opens it again whilst editing it, the focus
	 * will move to the frame.
	 * 
	 * @param file
	 * @param frame
	 */
	public void registerOpenedFile(File file, EditorInternalFrame frame) {
	    DweezilInternalFrameManager.addInternalFrameToWindowMap(file, frame);
        // Add to Recent File List
        EditorPrefs.getInstance().addFileToHistory(file);
	}
	
	
    ////////////////////////////////////////////////////////////////////////////
	//   EDITOR Registration  //////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////
    
    /**
	 * List of registered Editor Handlers
	 */
	private Vector _editorHandlers = new Vector();
	
	/**
	 * Register a IEditorHandler
	 * @param handler The IEditorHandler
	 */
	public synchronized void registerEditorHandler(IEditorHandler handler) {
		if(!_editorHandlers.contains(handler)) {
		    _editorHandlers.addElement(handler);
		}
	}
	
	/**
	 * Remove a IEditorHandler
	 * @param handler The IEditorHandler
	 */
	public synchronized void removeEditorhandler(IEditorHandler handler) {
	    _editorHandlers.removeElement(handler);
	}
	
    /**
     * @return An array of all registered IEditorHandler
     */
    public IEditorHandler[] getRegisteredEditorHandlers() {
    	IEditorHandler[] array = new IEditorHandler[_editorHandlers.size()];
    	_editorHandlers.copyInto(array);
    	return array;
    }
    
    /**
     * @param file
     * @return A IEditorHandler that can edit the File or null if none found
     */
    public IEditorHandler getEditorHandler(File file) {
        IEditorHandler[] infos = getRegisteredEditorHandlers();
        for(int i = 0; i < infos.length; i++) {
            if(infos[i].canEditFile(file)) {
                return infos[i];
            }
        }
        
        return null;
    }

    ////////////////////////////////////////////////////////////////////////////
	//   APPLICATION LISTENERS  ////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////
	
	/**
	 * List of Listeners
	 */
	private Vector _applisteners = new Vector();

	/**
	 * Add an Application Listener
	 * @param listener The Application Listener
	 */
	public synchronized void addApplicationListener(ApplicationListener listener) {
		if(!_applisteners.contains(listener)) {
		    _applisteners.addElement(listener);
		}
	}
	
	/**
	 * Remove an Application Listener
	 * @param listener The Application Listener
	 */
	public synchronized void removeApplicationListener(ApplicationListener listener) {
	    _applisteners.removeElement(listener);
	}
	
	/**
	 * Quit the application
	 * Put any cleanup code here.
	 */
	public void closeApplication() {
		// Tell any registered listeners
		boolean result = fireApplicationClosing();
		if(result) {
			// Save Prefs again.  This saves most recent list.
			try {
				EditorPrefs.getInstance().save();
			}
			catch(IOException ex) {
				ErrorDialogBox.showWarning(Messages.getString("uk.ac.reload.editor.EditorHandler.10") + ": ", //$NON-NLS-1$ //$NON-NLS-2$ 
				        Messages.getString("uk.ac.reload.editor.EditorHandler.11"), ex);               //$NON-NLS-1$
			}
                        /**
                         *changes done for don't close browser
                         *only close applet application
                         */ 
			EditorFrame.getInstance().setVisible(false);
			 //Quit
			//System.exit(1);
			//System.exit(0);
		}
	}

	/**
	 * Tell our listeners that we are closing the App
	 * They must reply by answering with true for OK, or false for not OK
	 */
	private boolean fireApplicationClosing() {
		boolean ok = true;
		// Go from last to first in case anyone removes themselves
		for(int i = _applisteners.size() - 1; i >= 0; i--) {
		//for(int i = _applisteners.size() - 0; i >= 0; i--) {
			ApplicationListener listener = (ApplicationListener)_applisteners.elementAt(i);
			boolean result = listener.applicationClosing();
			if(result == false) {
			    ok = false;
			}
		}
		return ok;
	}
	
	///////////////////////////////////////////////////////////////////////////////
	// INTERNAL FRAME MANAGEMENT
	///////////////////////////////////////////////////////////////////////////////
	
	/**
	 * The InternalFrameManager
	 */
	private DweezilInternalFrameManager _internalFrameManager;

	/**
	 * @return The DweezilInternalFrameManager
	 */
	public DweezilInternalFrameManager getInternalFrameManager() {
		return _internalFrameManager;
	}

	/**
	 * Add a new DweezilInternalFrame to the Desktop with autosize true
	 * @param frame The new Frame
	 */
	public void addInternalFrame(EditorInternalFrame frame) {
		addInternalFrame(frame, true);
	}
	
	/**
	 * Add a new DweezilInternalFrame to the Desktop with autosize true or false
	 * @param frame The new Frame
	 * @param autoSize Whether to give the frame an auto size and position
	 */
	public void addInternalFrame(EditorInternalFrame frame, boolean autoSize) {
	    // Listen to the Host App
        addApplicationListener(frame);
		// Add us as a frame listener
		frame.addInternalFrameListener(this);
		// Add to Internal Frame Manager
		_internalFrameManager.addInternalFrame(frame, autoSize);
		// Add to main menu
		MainMenu.getSharedInstance().addInternalFrame(frame);
	}
	
	/**
	 * Remove a new DweezilInternalFrame
	 * @param frame The Frame
	 */
	public void removeInternalFrame(EditorInternalFrame frame) {
	    removeApplicationListener(frame);
		// Remove us as a frame listener
		frame.removeInternalFrameListener(this);
		// Remove from Internal Frame Manager
		_internalFrameManager.removeInternalFrame(frame);
		// Remove from main menu
		MainMenu.getSharedInstance().removeInternalFrame(frame);
	}
	
	
	////////////////////////////////////////////////////////////////////////////
	// Listeners to InternalFrameListener
	////////////////////////////////////////////////////////////////////////////
	
	public void internalFrameOpened(InternalFrameEvent e) {
	}
	
	public void internalFrameClosing(InternalFrameEvent e) {
	}
	
	/**
	 * The Internal frame is closed so we need to remove it.
	 * @param e
	 */
	public void internalFrameClosed(InternalFrameEvent e) {
		Object o = e.getSource();
		if(o instanceof EditorInternalFrame) {
		    removeInternalFrame((EditorInternalFrame)o);
		}
	}
	
	public void internalFrameIconified(InternalFrameEvent e) {
	}
	
	public void internalFrameDeiconified(InternalFrameEvent e) {
	}
	
	public void internalFrameActivated(InternalFrameEvent e) {
	}
	
	public void internalFrameDeactivated(InternalFrameEvent e) {
	}
	
}
