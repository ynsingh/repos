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

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

import uk.ac.reload.diva.util.CopyTask;
import uk.ac.reload.dweezil.gui.DweezilFileChooser;
import uk.ac.reload.dweezil.gui.SplashScreen;
import uk.ac.reload.dweezil.gui.StatusWindow;
import uk.ac.reload.dweezil.util.DweezilUIManager;
import uk.ac.reload.dweezil.util.NativeLauncher;
import uk.ac.reload.dweezil.util.OSXApplication;
import uk.ac.reload.dweezil.util.UIUtils;
import uk.ac.reload.editor.menu.MainMenu;
import uk.ac.reload.editor.menu.MenuAction_About;
import uk.ac.reload.editor.prefs.EditorPrefs;
import uk.ac.reload.editor.prefs.PrefsDialog;
import uk.ac.reload.editor.properties.EditorProperties;


/**
 * The Main Editor Frame
 *
 * @author Phillip Beauvoir
 * @version $Id: EditorFrame.java,v 1.1 1998/03/26 15:24:11 ynsingh Exp $
 */
public class EditorFrame
extends JFrame
implements OSXApplication, IIcons {

    /**
     * If running locally whilst developing, this is the path relative to your working dir of the support jar
     * The support jar can contain helpers, schemas, and so on that will be unpacked to the prefs folder
     */

	//static File path= new File(EditorProperties.getFileProperty("user.dir")+"lib/reload-support.jar");


   // public static final String SUPPORT_JAR_LOCATION = path.getAbsolutePath();

	//System.out.println("The location is :" + SUPPORT_JAR_LOCATION);

	 public static final String SUPPORT_JAR_LOCATION = "reload-support.jar"; //$NON-NLS-1$

    /**
     * Retrieving this resource will allow you to locate the SUPPORT_JAR_LOCATION
     * The support jar can contains helpers, schemas, and help if you choose
     */
    public static final String SUPPORT_JAR_RESOURCE = "helpers"; //$NON-NLS-1$

    /**
	 * The Status Window
	 */
	private StatusWindow _statusWindow;
	
	/**
	 * The singleton instance of the app
	 */
	private static EditorFrame _instance;
	

	/**
	 * Constructor for new Editor
	 */
	public EditorFrame() {
		_instance = this;
	//System.out.println("The path is :" + path.toString());
	//System.out.println("The location is :" + SUPPORT_JAR_LOCATION);
		/*try{
		FileWriter fw=new FileWriter("/home/guest/seema.txt");
		fw.write("The path is :" + path.toString()+"The location is :" + SUPPORT_JAR_LOCATION);
		fw.close();
		}//try
		catch(Exception e){}*/

		
		// Set Properties file
		System.setProperty("editor.properties.file", "uk.ac.reload.editor.properties.rb"); //$NON-NLS-1$ //$NON-NLS-2$
		
		// Turn off Java 1.4.2 Windows XP L&F - it's bad
		System.setProperty("swing.noxp", "true"); //$NON-NLS-1$ //$NON-NLS-2$
		
		// UI Defaults
		DweezilUIManager.setMacUIDefaults(EditorProperties.getString("APP_NAME"), getInstance()); //$NON-NLS-1$
		
		// SplashScreen
		SplashScreen splash = new SplashScreen(DweezilUIManager.getIcon(IMAGE_SPLASH));
		
		boolean DEBUG = EditorProperties.getString("DEBUG").equals("true"); //$NON-NLS-1$ //$NON-NLS-2$
		
		// Set Language Locale
		EditorPrefs.getInstance().setDefaultLocale();
		
		// Get the Status Window going
		_statusWindow = new StatusWindow(EditorProperties.getString("APP_NAME"), //$NON-NLS-1$
		        EditorProperties.getString("VERSION"),    //$NON-NLS-1$
		        EditorProperties.getString("BUILD_DATE"), //$NON-NLS-1$
				//DweezilUIManager.getIcon(ICON_APP32),
				DweezilUIManager.getIcon(bss_bsspllogo07),
				!DEBUG,
				EditorPrefs.getInstance().getPrefsFolder());
		
		// Output some useful information
		System.out.println(Messages.getString("uk.ac.reload.editor.EditorFrame.0") + ":\t" + EditorProperties.getFileProperty("prefs.dir")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		System.out.println(Messages.getString("uk.ac.reload.editor.EditorFrame.1") + ":\t\t" + EditorProperties.getFileProperty("helpers.dir")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		System.out.println(Messages.getString("uk.ac.reload.editor.EditorFrame.2") + ":\t\t" + EditorProperties.getFileProperty("schema.dir")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		System.out.println(Messages.getString("uk.ac.reload.editor.EditorFrame.3") + ":\t\t" + EditorProperties.getFileProperty("preview.dir")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		
	    // Register the in-built Editors early
		EditorHandler.getSharedInstance().registerInbuiltEditorHandlers();

	    // Check that the prefs (and helpers) folder is in working order
		boolean checkSupport = EditorPrefs.getInstance().getBooleanValue(EditorPrefs.GENERAL_CHECK_SUPPORT);
		if(checkSupport) {
		    checkSupportFolder();
		}
		
		// Set Look And Feel from User Prefs
		String lf = EditorPrefs.getInstance().getValue(EditorPrefs.GENERAL_PREFS_LF);
		if(lf == null) {
		    lf = UIManager.getSystemLookAndFeelClassName();
		}
		DweezilUIManager.setLookAndFeel(lf, new Component[] {getInstance(), getStatusWindow()});
		
		// Set default folder from User Prefs
		String defFolder = EditorPrefs.getInstance().getValue(EditorPrefs.GENERAL_PREFS_DEFAULT_FOLDER);
		if(defFolder != null) {
		    DweezilFileChooser.setDefaultFolder(new File(defFolder));
		}
		
		// Set application title in title bar
		super.setTitle(EditorProperties.getString("APP_NAME")); //$NON-NLS-1$
		
		// Set application Icon
		ImageIcon icon = DweezilUIManager.getIcon(bss_bsspllogo07);
		if(icon != null) {
		    setIconImage(icon.getImage());
		}
		
		// Trap window closing event for our Window listener
               /*change by kishore*/
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		
		// Add a listener to Close our window down on exit
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
			    EditorHandler.getSharedInstance().closeApplication();
			}
		});
		
		// Set up desktop and internal frame manager
		JPanel panel = new JPanel(new BorderLayout());
		EditorDesktopPane deskTop = new EditorDesktopPane();
		
		// Register the Desktop with the EditorHandler
		EditorHandler.getSharedInstance().registerDesktop(deskTop);
		
		panel.add(deskTop, BorderLayout.CENTER);
		setContentPane(panel);
		
		// Create Menu Bar (after setting up the desktop, please!)
		setJMenuBar(MainMenu.getSharedInstance());
		
		// Create Toolbar
		getContentPane().add(MainMenu.getSharedInstance().getToolBar(), BorderLayout.NORTH);
		
	    splash.close();
		
		// Show the main Frame Window, centred
		UIUtils.centreWindowProportional(this, 0.9, 0.9);
		setVisible(true);
	}
	
	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Main program entry point
	 * @param args Command line arguments
	 */
	public static void main(String[] args) {
	    // Check for some args for over-riding system properties
        // User can create a shortcut to the application and add a switch to
	    // the end of the command line such as:
        // user.home=mydir
        // If there are spaces in the value, use quotes like:
        // "user.home=d://some dir"
	    for(int i = 0; i < args.length; i++) {
	        int pos = args[i].indexOf("=");
	        if(pos != -1) {
	            try {
	                String key = args[i].substring(0, pos).trim();
	                String value = args[i].substring(pos + 1).trim();
	                System.setProperty(key, value);
	            }
	            catch(IndexOutOfBoundsException ex) {
	            }
	        }
        }
	    
		new EditorFrame();
	}
	
	/**
	 * Gets the Singleton Instance of the Editor
	 * @return The Singleton Instance of the Editor
	 */
	public static EditorFrame getInstance() {
		return _instance;
	}
	
	/**
	 * @return The Status Window
	 */
	public StatusWindow getStatusWindow() {
		return _statusWindow;
	}
	
	/**
	 * @return the Help File
	 */
	public File getHelpFile() {
		return EditorProperties.getFileProperty("help.file"); //$NON-NLS-1$
	}
	
	/**
	 * Show the Help File
	 */
	public void showHelp() {
		File file = getHelpFile();
		if(file != null) {
		    NativeLauncher.launchFile(file);
		}
	}

	/**
	 * Set the Title Bar String
	 * @param title The Title Bar String
	 */
	public void setTitle(String title) {
		super.setTitle(EditorProperties.getString("APP_NAME") + " - " + title); //$NON-NLS-1$ //$NON-NLS-2$
	}
	
	/**
	 * Make sure the prefs and all support files are in working order
	 */
	private void checkSupportFolder() {
		CopyTask copyTask = null;
		try {
			// Local copy of jar whilst developing
			copyTask = new CopyTask(EditorFrame.SUPPORT_JAR_LOCATION, EditorPrefs.getInstance().getPrefsFolder());
		}
		catch(IOException e) {
			// From jar in classpath - we can look for "helpers" in classpath
			try {
				copyTask = new CopyTask(EditorFrame.SUPPORT_JAR_RESOURCE, EditorPrefs.getInstance().getPrefsFolder());
			}
			catch (Exception e1) {
				e1.printStackTrace();
				System.err.println(Messages.getString("uk.ac.reload.editor.EditorFrame.4")); //$NON-NLS-1$
				return;
			}
		}
		final CopyTask copyTask1 = copyTask;
		new Runnable() {
			public void run() {
				// We only need to backup if we're worried that the copy doesn't do it's job properly
				// or if the user has changed the default helper files and wants to preserve those changes
				// copyTask1.backup();
				try {
					copyTask1.execute(true);
				}
				catch(IOException e) {
					e.printStackTrace();
				}
			}
		}.run();
	}
	
	////////////////////////////////////////////////////////////////////////////
	// Implement OSXApplication
	////////////////////////////////////////////////////////////////////////////

	public void preferences() {
	    PrefsDialog.getSharedInstance().show();
	}
	
	public void about() {
		MenuAction_About.doShowMessageDialog();
	}
	
	public void quit() {
		EditorHandler.getSharedInstance().closeApplication();
	}
	
	public OSXApplication getAppInstance() {
		return getInstance();
	}

}
