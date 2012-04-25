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

package uk.ac.reload.editor.prefs;

import java.io.File;
import java.util.Locale;
import java.util.StringTokenizer;
import java.util.Vector;

import uk.ac.reload.diva.prefs.UserPrefs;
import uk.ac.reload.editor.EditorHandler;
import uk.ac.reload.editor.properties.EditorProperties;

/**
 * The User Prefs file.  Implementers will have to fulfil the abstract methods
 *
 * @author Phillip Beauvoir
 * @version $Id: EditorPrefs.java,v 1.1 1998/03/26 15:24:12 ynsingh Exp $
 */
public class EditorPrefs extends UserPrefs {

    /**
     * The Prefs Filename
     */
    public static final String USER_PREFS_FILENAME = "reload_prefs.xml";

    /**
     * The Prefs root element name
     */
    public static final String USER_PREFS_ELEMENTNAME = "reload_prefs";

    /**
     * Our singleton User Prefs
     */
    private static EditorPrefs _userPrefs = new EditorPrefs();

    // ======================== GENERAL KEYS ===================================

    // The default Open folder
    public static final String GENERAL_PREFS_DEFAULT_FOLDER = "default_folder";

    // Look and Feel
    public static final String GENERAL_PREFS_LF = "look_and_feel";
    
    // Recent File History
    public static final String GENERAL_HISTORY = "history";
    
    // Check prefs - starts up a little faster if set to false,
    // but more error-prone if you fool with the reload prefs folder
    // default is true
    public static final String GENERAL_CHECK_SUPPORT = "check_support";

    
    // ============================= CP KEYS ===================================

    // Hide Resources Element
    public static final String CP_HIDE_RESOURCES = "hide_resources";

    // Hide SCORMS Elements
    public static final String CP_HIDE_SCORM = "hide_scorm";

    // Only zip referenced files - doesn't work
    //public static final String CP_ZIP_REFERENCED_FILES = "zip_referenced";
    
    // Default CP Profile
    public static final String CP_DEFAULT_PROFILE = "default_cp_profile";

    /**
     * Default CP Version for CP
     */
    public static final String CP_DEFAULT_CP_VERSION = "default_cp_version";

    /**
     * Default MD Version for CP
     */
    public static final String CP_DEFAULT_MD_VERSION = "default_cp_md_version";

    /**
     * Default SCORM Profile
     */
    public static final String SCORM_DEFAULT_PROFILE = "default_scorm_profile";

    /**
     * Default CP Version for SCORM
     */
    public static final String SCORM_DEFAULT_CP_VERSION = "default_scorm_cp_version";

    /**
     * Default MD Version for SCORM
     */
    public static final String SCORM_DEFAULT_MD_VERSION = "default_scorm_md_version";
    

    // ============================= MD KEYS ===================================

    /**
     * Default MD Profile
     */
    public static final String MD_DEFAULT_PROFILE = "default_md_profile";

    /**
     * Default MD Version
     */
    public static final String MD_DEFAULT_VERSION = "default_md_version";

    /**
     * Do ask again next time
     */
    public static final String MD_DO_ASK_VERSION = "do_ask_md_version";
    
    
    // ========================== APPEARANCE KEYS =============================
    
    // (Look and Feel has been put under General)
    
    /**
     * Language
     */
    public static final String LANGUAGE = "language";
   
	/**
	 * Default Language Setting
	 */
    public static final String DEFAULT_LANGUAGE = "(default)";
	
	

    //  =======================================================================
    
    /**
     * @return the Singleton
     */
    public static EditorPrefs getInstance() {
        return _userPrefs;
    }

    /**
     * Set some default values
     */
    protected void setDefaultValues() {
        // Hide Resource Nodes
        String value = getValue(CP_HIDE_RESOURCES);
        if(value == null) {
            putBooleanValue(CP_HIDE_RESOURCES, false);
        }

        // Hide SCORM Nodes
        value = getValue(CP_HIDE_SCORM);
        if(value == null) {
            putBooleanValue(CP_HIDE_SCORM, true);
        }

        // Only zip referenced files
        //value = getValue(CP_ZIP_REFERENCED_FILES);
        //if(value == null) {
        //    putBooleanValue(CP_ZIP_REFERENCED_FILES, false);
        //}
        
        // Check prefs at startup
        value = getValue(GENERAL_CHECK_SUPPORT);
        if(value == null) {
            putBooleanValue(GENERAL_CHECK_SUPPORT, true);
        }
        
        // Ideally we need to have all Preferences pluggable so that a registered EditorHandler
        // Take care of specific Preferences

        // Prompt for MD Version
        value = getValue(MD_DO_ASK_VERSION);
        if(value == null) {
            putBooleanValue(MD_DO_ASK_VERSION, true);
        }
        
        // Default MD Version
        value = getValue(MD_DEFAULT_VERSION);
        if(value == null) {
            putValue(MD_DEFAULT_VERSION, EditorHandler.MD_EDITORHANDLER.getDefaultVersion());
        }

        // Default CP Version
        value = getValue(CP_DEFAULT_CP_VERSION);
        if(value == null) {
            putValue(CP_DEFAULT_CP_VERSION, EditorHandler.CP_EDITORHANDLER.getDefaultVersion());
        }

        // Default CP/MD Version
        value = getValue(CP_DEFAULT_MD_VERSION);
        if(value == null) {
            putValue(CP_DEFAULT_MD_VERSION, EditorHandler.MD_EDITORHANDLER.getDefaultVersion());
        }

        // Default SCORM CP Version
        value = getValue(SCORM_DEFAULT_CP_VERSION);
        if(value == null) {
            putValue(SCORM_DEFAULT_CP_VERSION, EditorHandler.SCORM12_EDITORHANDLER.getDefaultVersion());
        }

        // Default SCORM/MD Version
        value = getValue(SCORM_DEFAULT_MD_VERSION);
        if(value == null) {
            putValue(SCORM_DEFAULT_MD_VERSION, EditorHandler.SCORM12_EDITORHANDLER.getDefaultMDVersion());
        }
        
        // Default Language
        value = getValue(LANGUAGE);
        if(value == null) {
            putValue(LANGUAGE, DEFAULT_LANGUAGE);
        }
    }

    /**
     * @return the Preferences File
     */
    public File getPrefsFile() {
        return new File(getPrefsFolder(), USER_PREFS_FILENAME);
    }

    /**
     * Location of Editor Prefs folder
     * @return the Preferences Folder
     */
    public File getPrefsFolder() {
    	File reloadHome = EditorProperties.getFileProperty("prefs.dir");
        if(reloadHome != null) reloadHome.mkdirs();
        return reloadHome;
    }

    /**
     * @return the root Element name
     */
    public String getElementRootName() {
        return USER_PREFS_ELEMENTNAME;
    }

    /**
     * @return the Recent File History as an array of Strings
     */
    public String[] getFileHistory() {
        Vector v = new Vector();

        String val = getValue(GENERAL_HISTORY);

        if(val != null) {
            StringTokenizer t = new StringTokenizer(val, ";");
            while(t.hasMoreElements()) {
                String path = t.nextToken();
                v.addElement(path);
            }
        }

        String[] vals = new String[v.size()];
        v.copyInto(vals);
        return vals;
    }

    /**
     * Add a new entry to the Recent File list, or if already present move to
     * front of list, and limit to 8 entries if need be
     */
    public void addFileToHistory(File file) {
        String val = file.getPath();
        int count = 1;

        String[] history = getFileHistory();

        for(int i = 0; i < history.length && count < 8; i++) {
            if(!history[i].equalsIgnoreCase(file.getPath())) {
                val += ";" + history[i];
                count++;
            }
        }

        putValue(GENERAL_HISTORY, val);
    }
    
	
	/**
	 * Set the System Default Locale from the Prefs
	 */
    public void setDefaultLocale() {
		String languageCode = getValue(LANGUAGE);
		if(languageCode != null && !languageCode.equals(DEFAULT_LANGUAGE)) {
		    setDefaultLocale(languageCode);
		}
    }
    
    /**
	 * Set the System Default Locale
	 * @param languageCode A language code such as "en", "en_GB", "fr", "fr_CA", "es_ES_Traditional_WIN"
	 */
    public void setDefaultLocale(String languageCode) {
		if(languageCode != null) {
		    StringTokenizer t = new StringTokenizer(languageCode, "_");
		    int tokens = t.countTokens();

		    if(tokens == 1) {
		        Locale.setDefault(new Locale(languageCode));
		    }
		    else if(tokens == 2) {
		        String language = t.nextToken();
		        String country = t.nextToken();
		        Locale.setDefault(new Locale(language, country));
		    }
		    else if(tokens >= 3) {
		        String language = t.nextToken();
		        String country = t.nextToken();
		        String variant = t.nextToken();
		        while(t.hasMoreTokens()) {
		            variant += "_" + t.nextToken();
		        }
		        Locale.setDefault(new Locale(language, country, variant));
		    }
		}
	}
}
