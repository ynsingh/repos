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

package uk.ac.reload.editor.scorm;

import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

import javax.swing.Icon;

import org.jdom.Document;
import org.jdom.JDOMException;
import org.jdom.Namespace;

import uk.ac.reload.dweezil.util.DweezilUIManager;
import uk.ac.reload.editor.EditorHandler;
import uk.ac.reload.editor.EditorInternalFrame;
import uk.ac.reload.editor.Messages;
import uk.ac.reload.editor.contentpackaging.CP_EditorHandler;
import uk.ac.reload.editor.metadata.MD_EditorHandler;
import uk.ac.reload.editor.metadata.xml.MD_SchemaController;
import uk.ac.reload.editor.prefs.EditorPrefs;
import uk.ac.reload.editor.scorm.editor.SCORM12_Editor;
import uk.ac.reload.editor.scorm.editor.SCORM12_NewDialog;
import uk.ac.reload.editor.scorm.xml.SCORM12_CP112_SchemaController;
import uk.ac.reload.editor.scorm.xml.SCORM12_CP113_SchemaController;
import uk.ac.reload.editor.scorm.xml.SCORM12_Package;
import uk.ac.reload.editor.scorm.xml.SCORM12_SchemaController;
import uk.ac.reload.jdom.XMLUtils;
import uk.ac.reload.moonunit.SchemaController;
import uk.ac.reload.moonunit.schema.SchemaException;


/**
 * The Handler implementation for IMS Content Packaging 1.1.x versions.<br>
 * This currently supports IMS Content Packaging 1.1.2 and 1.1.3<br>
 * 
 * We don't support earlier IMS Content Packaging versions since the Schema is deprecated<br>
 * 
 * @author Phillip Beauvoir
 * @version $Id: SCORM12_EditorHandler.java,v 1.1 1998/03/26 15:24:12 ynsingh Exp $
 */
public class SCORM12_EditorHandler
extends CP_EditorHandler
{
    public static final String ADL_SCORM_1_2 = "ADL SCORM 1.2"; //$NON-NLS-1$
    public static final String ADL_SCORM_1_3 = "ADL SCORM 1.3"; //$NON-NLS-1$

    public static final String ADL_SCORM_1_2_CP_1_1_2 = ADL_SCORM_1_2 + ", " + IMS_CONTENT_PACKAGING_1_1_2; //$NON-NLS-1$
    public static final String ADL_SCORM_1_2_CP_1_1_3 = ADL_SCORM_1_2 + ", " + IMS_CONTENT_PACKAGING_1_1_3; //$NON-NLS-1$

    
    static Hashtable SUPPORTED_SCORM_NAMESPACES = new Hashtable();
    
    static {
		// Add to table of supported SCORM Namespaces mapped to Version Names
		SUPPORTED_SCORM_NAMESPACES.put(ADLCP_NAMESPACE_12, ADL_SCORM_1_2);
    }
    
    public static final File HELPER_FOLDER = new File(EditorHandler.HELPERFOLDER, "scorm"); //$NON-NLS-1$
    public static final File PROFILE_FOLDER = new File(HELPER_FOLDER, "profile"); //$NON-NLS-1$
    public static final File SCHEMAHELPER_FOLDER = new File(HELPER_FOLDER, "schemahelper"); //$NON-NLS-1$
    public static final File VOCAB_FOLDER = new File(HELPER_FOLDER, "vocab"); //$NON-NLS-1$

    /**
	 * Constructor
	 */
	public SCORM12_EditorHandler() {
	
	}

	public boolean canEditFile(File file) {
        Document doc = null;
        try {
            doc = getDocument(file);
        }
        catch(Exception ex) {
            return false;
        }
        
        // The first thing we do is to see if there is a root Namespace in the Document
		Namespace nameSpace = XMLUtils.getDocumentNamespace(doc);
		
		// No Namespace, sorry we don't know what it is!
		if(nameSpace == null || nameSpace.equals(Namespace.NO_NAMESPACE)) {
		    return false;
		}
		
		// Now find out if it is a SCORM Document and if so whether we support it
		// We'll search all elements for the ADL Namespace
        Namespace nsSCORM = getSCORM_Namespace(doc);
        if(nsSCORM == null) {
            return false;
        }
        
        // Do we support this version of SCORM?
        if(SUPPORTED_SCORM_NAMESPACES.containsKey(nsSCORM) == false) {
            return false;
        }

		// And simply check against our list of supported CP Namespaces
		return SUPPORTED_CP_NAMESPACES.containsKey(nameSpace);
	}
	
	public EditorInternalFrame editFile(File file) throws JDOMException, SchemaException, IOException {
	    // Test if a zip file
	    if(file.getName().toLowerCase().endsWith(".zip")) { //$NON-NLS-1$
	        File manifest = unzipManifest(file);
	        // If we've got a new file, reference the imsmanifest.xml
	        if(manifest != null) {
	            file = manifest;
	        }
	        else {
	            throw new IOException("No manifest file"); //$NON-NLS-1$
	        }
	    }

    	SCORM12_Package sp = new SCORM12_Package(file);
        return new SCORM12_Editor(sp);
    }

	public EditorInternalFrame newDocument() throws JDOMException, SchemaException, IOException {
		// Get default CP & MD versions from user Prefs
		String versionCP = EditorPrefs.getInstance().getValue(EditorPrefs.SCORM_DEFAULT_CP_VERSION);
		String versionMD = EditorPrefs.getInstance().getValue(EditorPrefs.SCORM_DEFAULT_MD_VERSION);
		
		String[] versionsMD = EditorHandler.MD_EDITORHANDLER.getSupportedVersions();
		String[] versionsCP = super.getSupportedVersions();
		
		// Ask for New SCORM CP Folder
		SCORM12_NewDialog dialog = new SCORM12_NewDialog(versionsCP, versionCP, versionsMD, versionMD);
		File cpFolder = dialog.showDialog();
		// User cancelled or Project Folder not valid
		if(cpFolder == null) {
		    return null;
		}
		
		versionCP = dialog.getVersionCP();
		versionMD = dialog.getVersionMD();
		
		// Save to user Prefs
		EditorPrefs.getInstance().putValue(EditorPrefs.SCORM_DEFAULT_CP_VERSION, versionCP);
		EditorPrefs.getInstance().putValue(EditorPrefs.SCORM_DEFAULT_MD_VERSION, versionMD);
		
		String versionSCORM = ADL_SCORM_1_2 + ", " + versionCP; //$NON-NLS-1$
	    SCORM12_SchemaController scormController = (SCORM12_SchemaController)getSchemaControllerInstance(versionSCORM);
		MD_SchemaController mdController = (MD_SchemaController)EditorHandler.MD_EDITORHANDLER.getSchemaControllerInstance(versionMD);
		SCORM12_Package sp = new SCORM12_Package(cpFolder, scormController, mdController);
		return new SCORM12_Editor(sp);
	}

	public String getName() {
	    return Messages.getString("uk.ac.reload.editor.contentpackaging.scorm12.SCORM12_EditorHandler.0"); //$NON-NLS-1$
	}
	
	public Icon getIcon() {
	    return DweezilUIManager.getIcon(ICON_CPSCORM);
	}

	/** 
	 * @return The default version of CP to use
	 */
	public String getDefaultVersion() {
	    return IMS_CONTENT_PACKAGING_1_1_2;
	}

	/** 
	 * @return The default version of MD to use
	 */
	public String getDefaultMDVersion() {
	    return MD_EditorHandler.IMS_METADATA_1_2_1;
	}

	/** 
	 * @return Supported versions of CP
	 */
	public String[] getSupportedVersions() {
	    return new String[] {
	            ADL_SCORM_1_2_CP_1_1_2,
	            ADL_SCORM_1_2_CP_1_1_3
	    };
	}

	/** 
	 * @return the Special SCORM Version String base on the CP Namespace
	 */
	public String getVersion(Namespace ns) {
	    if(ns == null) {
	        return null;
	    }
	    
	    return ADL_SCORM_1_2 + ", " + (String)SUPPORTED_CP_NAMESPACES.get(ns); //$NON-NLS-1$
	}

	/**
	 * Get a new instance implementation of the appropriate SchemaController
	 * for a given version key.<br>
	 * @throws JDOMException
	 * @throws IOException
	 * @throws SchemaException
	 */
	public SchemaController getSchemaControllerInstance(String version) throws JDOMException, SchemaException, IOException {
	    SchemaController schemaController = null;
	    
	    if(ADL_SCORM_1_2_CP_1_1_2.equals(version)) {
	        schemaController = new SCORM12_CP112_SchemaController();
	    }
	    else if(ADL_SCORM_1_2_CP_1_1_3.equals(version)) {
	        schemaController = new SCORM12_CP113_SchemaController();
	    }
	    
	    return schemaController;
	}

}
