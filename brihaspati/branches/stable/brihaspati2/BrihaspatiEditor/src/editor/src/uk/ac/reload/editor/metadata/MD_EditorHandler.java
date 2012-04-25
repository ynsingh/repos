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

package uk.ac.reload.editor.metadata;

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
import uk.ac.reload.editor.IEditorHandler;
import uk.ac.reload.editor.IIcons;
import uk.ac.reload.editor.Messages;
import uk.ac.reload.editor.metadata.datamodel.MD_DataModel;
import uk.ac.reload.editor.metadata.editor.MD_Editor;
import uk.ac.reload.editor.metadata.editor.MD_NewDialog;
import uk.ac.reload.editor.metadata.xml.*;
import uk.ac.reload.editor.prefs.EditorPrefs;
import uk.ac.reload.jdom.XMLUtils;
import uk.ac.reload.moonunit.SchemaController;
import uk.ac.reload.moonunit.schema.SchemaException;


/**
 * The Editor implementation for IMS Metadata 1.2.x versions.<br>
 * This currently supports IMS Metadata 1.1, 1.2.1 and 1.2.2<br>
 * We don't support IEEE Metadata 1.0 because the Schemas are too complex for Castor/Xerces.<br> 
 *
 * @author Phillip Beauvoir
 * @version $Id: MD_EditorHandler.java,v 1.1 1998/03/26 15:24:12 ynsingh Exp $
 */
public class MD_EditorHandler
implements IEditorHandler, IIcons
{
    // IEEE LOM Metadata
    public static final String IEEE_METADATA_1_0_0 = "IEEE LOM Metadata 1.0"; //$NON-NLS-1$
    
    // Namespace prefix
    // Note - What is the Namespace prefix for IEEE LOM?  Is it "lom"?
    public static String IEEE_NAMESPACE_PREFIX = "lom"; //$NON-NLS-1$

    // Namespace
    public static Namespace IEEE_NAMESPACE_100 = Namespace.getNamespace("http://ltsc.ieee.org/xsd/LOMv1p0"); //$NON-NLS-1$

    // ==========================================================================================
    
    // IMS Metadata
    public static final String IMS_METADATA_1_1 = "IMS Metadata 1.1"; //$NON-NLS-1$
    public static final String IMS_METADATA_1_2 = "IMS Metadata 1.2"; //$NON-NLS-1$
    public static final String IMS_METADATA_1_2_1 = "IMS Metadata 1.2.1"; //$NON-NLS-1$
    public static final String IMS_METADATA_1_2_2 = "IMS Metadata 1.2.2"; //$NON-NLS-1$
    
    // Namespace prefix
    public static String IMSMD_NAMESPACE_PREFIX = "imsmd"; //$NON-NLS-1$

    // MD Namespace Version 1.2.2
    public static Namespace IMSMD_NAMESPACE_122 = Namespace.getNamespace("http://www.imsglobal.org/xsd/imsmd_v1p2");         //$NON-NLS-1$
    
    // MD Namespace Version 1.2.1
    public static Namespace IMSMD_NAMESPACE_121 = Namespace.getNamespace("http://www.imsglobal.org/xsd/imsmd_rootv1p2p1");     //$NON-NLS-1$
    
    // MD Namespace Version 1.2 - Not supported, has out of date Schema
    public static Namespace IMSMD_NAMESPACE_12 = Namespace.getNamespace("http://www.imsproject.org/xsd/imsmd_rootv1p2");     //$NON-NLS-1$
    
    // MD Namespace Version 1.1 
    public static Namespace IMSMD_NAMESPACE_11 = Namespace.getNamespace("http://www.imsproject.org/xsd/ims_md_rootv1p1");     //$NON-NLS-1$

    // MD Bogus Namespace as in WebCT
    public static Namespace MD_BOGUS_NAMESPACE1 = Namespace.getNamespace("http://www.imsproject.org/metadata"); //$NON-NLS-1$

    // ==========================================================================================
    
    
    static Hashtable SUPPORTED_NAMESPACES = new Hashtable();
    
    static {
		// Add to table of Supported Namespaces mapped to Version names
		SUPPORTED_NAMESPACES.put(IMSMD_NAMESPACE_11, IMS_METADATA_1_1);
		SUPPORTED_NAMESPACES.put(IMSMD_NAMESPACE_121, IMS_METADATA_1_2_1);
		SUPPORTED_NAMESPACES.put(IMSMD_NAMESPACE_122, IMS_METADATA_1_2_2);
		// SUPPORTED_NAMESPACES.put(IEEE_NAMESPACE_100, IEEE_METADATA_1_0_0);
    }
    
    public static final File HELPER_FOLDER = new File(EditorHandler.HELPERFOLDER, "md"); //$NON-NLS-1$
    public static final File PROFILE_FOLDER = new File(HELPER_FOLDER, "profile"); //$NON-NLS-1$
    public static final File SCHEMAHELPER_FOLDER = new File(HELPER_FOLDER, "schemahelper"); //$NON-NLS-1$
    public static final File VOCAB_FOLDER = new File(HELPER_FOLDER, "vocab"); //$NON-NLS-1$
    
    // ==========================================================================================

    /**
	 * Constructor
	 */
	public MD_EditorHandler() {

	}
	
    /* (non-Javadoc)
     * @see uk.ac.reload.editor.IEditorHandler#canCreateDocuments()
     */
    public boolean canCreateDocuments() {
        return true;
    }

    /* (non-Javadoc)
     * @see uk.ac.reload.editor.IEditorHandler#canEditFile(java.io.File)
     */
    public boolean canEditFile(File file) {
		// Try to load the Document
		Document doc;
        try {
            doc = XMLUtils.readXMLFile(file);
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
		
		// And simply check against our list of supported Namespaces
		return SUPPORTED_NAMESPACES.containsKey(nameSpace);
	}

	
    /* (non-Javadoc)
     * @see uk.ac.reload.editor.IEditorHandler#editFile(java.io.File)
     */
    public EditorInternalFrame editFile(File file) throws JDOMException, SchemaException, IOException {
    	Metadata md = new Metadata(file);
        return new MD_Editor(md);
    }
    

	/* (non-Javadoc)
	 * @see uk.ac.reload.editor.IEditorHandler#newDocument()
	 */
	public EditorInternalFrame newDocument() throws JDOMException, SchemaException, IOException {
		// Get default version from user Prefs
		String version = EditorPrefs.getInstance().getValue(EditorPrefs.MD_DEFAULT_VERSION);
		
		// Ask for version with a dialog
		boolean doAsk = EditorPrefs.getInstance().getBooleanValue(EditorPrefs.MD_DO_ASK_VERSION) || version == null;
		if(doAsk) {
		    String[] versionsMD = getSupportedVersions();
		    
		    MD_NewDialog dialog = new MD_NewDialog(versionsMD, version);
		    int response = dialog.showDialog();
		    if(response == MD_NewDialog.OK_OPTION) {
		        // Get chosen version
		        version = dialog.getVersion();
		        
		        boolean dontAsk = dialog.getDontAsk();
		        // Save to user Prefs
		        if(dontAsk) {
		            EditorPrefs.getInstance().putBooleanValue(EditorPrefs.MD_DO_ASK_VERSION, false);
		            EditorPrefs.getInstance().putValue(EditorPrefs.MD_DEFAULT_VERSION, version);
		        }
		        
		    }
		    else {
		        return null;
		    }
		}
		
		MD_SchemaController controller = (MD_SchemaController)getSchemaControllerInstance(version);
		Metadata md = new Metadata(true, controller);
		return new MD_Editor(md);
	}
	
	/**
	 * Load a new MD_DataModel with the latest MD versions for testing
	 * @return a MD_DataModel
	 * @throws JDOMException
	 * @throws SchemaException
	 * @throws IOException
	 */
	public static MD_DataModel loadNewTestInstance() throws JDOMException, SchemaException, IOException {
	    File file = new File("z-md-test/md.xml");
	    
	    Metadata md;
	    
	    if(file.exists()) {
	        md = new Metadata(file);
	    }
	    else {
		    MD122_SchemaController mdController = new MD122_SchemaController();
	        md = new Metadata(true, mdController);
	        md.setFile(file);
	    }
	    
		return new MD_DataModel(md);
	}

	/* (non-Javadoc)
	 * @see uk.ac.reload.editor.IEditorHandler#getName()
	 */
	public String getName() {
	    return Messages.getString("uk.ac.reload.editor.metadata.MD_EditorHandler.0"); //$NON-NLS-1$
	}
	
	/* (non-Javadoc)
	 * @see uk.ac.reload.editor.IEditorHandler#getIcon()
	 */
	public Icon getIcon() {
	    return DweezilUIManager.getIcon(ICON_MD);
	}

	/* (non-Javadoc)
	 * @see uk.ac.reload.editor.IEditorHandler#getSupportedVersions()
	 */
	public String[] getSupportedVersions() {
	    return new String[] {
	            IMS_METADATA_1_1,
	            IMS_METADATA_1_2_1,
	            IMS_METADATA_1_2_2,
	            // IEEE Not supported yet
	            // IEEE_METADATA_1_0_0
	    };
	}

	/**
	 * @return The default Version to use in new documents
	 */
	public String getDefaultVersion() {
	    return IMS_METADATA_1_2_2;
	}
	
	/**
	 * @param ns Namespace
	 * @return A version depending on Namespace
	 */
	public String getVersion(Namespace ns) {
	    return ns == null ? null : (String)SUPPORTED_NAMESPACES.get(ns);
	}

	/* (non-Javadoc)
	 * @see uk.ac.reload.editor.IEditorHandler#getSchemaControllerInstance(java.lang.String)
	 */
    public SchemaController getSchemaControllerInstance(String version) throws JDOMException, SchemaException, IOException {
        SchemaController schemaController = null;
        
        if(IMS_METADATA_1_2_1.equals(version)) {
            schemaController = new MD121_SchemaController();
        }
        else if(IMS_METADATA_1_2_2.equals(version)) {
            schemaController = new MD122_SchemaController();
        }
        else if(IMS_METADATA_1_1.equals(version)) {
            schemaController = new MD11_SchemaController();
        }
        
        // IEEE Not supported yet!!
        else if(IEEE_METADATA_1_0_0.equals(version)) {
        	schemaController = new MD_IEEE_100_SchemaController();
        }
        
        return schemaController;
    }

}
