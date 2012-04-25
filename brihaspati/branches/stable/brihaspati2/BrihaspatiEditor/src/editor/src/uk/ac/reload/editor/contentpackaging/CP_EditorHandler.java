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

package uk.ac.reload.editor.contentpackaging;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Hashtable;
import java.util.zip.ZipOutputStream;

import javax.swing.Icon;
import javax.swing.JOptionPane;

import org.jdom.Comment;
import org.jdom.Document;
import org.jdom.JDOMException;
import org.jdom.Namespace;

import uk.ac.reload.diva.util.FileUtils;
import uk.ac.reload.diva.util.ZipUtils;
import uk.ac.reload.dweezil.gui.DweezilFileChooser;
import uk.ac.reload.dweezil.gui.DweezilFileFilter;
import uk.ac.reload.dweezil.gui.DweezilProgressMonitor;
import uk.ac.reload.dweezil.gui.ErrorDialogBox;
import uk.ac.reload.dweezil.util.DweezilUIManager;
import uk.ac.reload.editor.*;
import uk.ac.reload.editor.contentpackaging.editor.CP_Editor;
import uk.ac.reload.editor.contentpackaging.editor.CP_NewDialog;
import uk.ac.reload.editor.contentpackaging.xml.CP111_SchemaController;
import uk.ac.reload.editor.contentpackaging.xml.CP112_SchemaController;
import uk.ac.reload.editor.contentpackaging.xml.CP113_SchemaController;
import uk.ac.reload.editor.contentpackaging.xml.CP_SchemaController;
import uk.ac.reload.editor.contentpackaging.xml.ContentPackage;
import uk.ac.reload.editor.metadata.MD_EditorHandler;
import uk.ac.reload.editor.metadata.xml.MD_SchemaController;
import uk.ac.reload.editor.prefs.EditorPrefs;
import uk.ac.reload.editor.properties.EditorProperties;
import uk.ac.reload.jdom.XMLUtils;
import uk.ac.reload.moonunit.SchemaController;
import uk.ac.reload.moonunit.contentpackaging.CP_Core;
import uk.ac.reload.moonunit.schema.SchemaException;


/**
 * The Handler implementation for IMS Content Packaging 1.1.x versions.<br>
 * This currently supports IMS Content Packaging 1.1.2 and 1.1.3<br>
 * 
 * We don't support earlier IMS Content Packaging versions since the Schema is deprecated<br>
 * 
 * @author Phillip Beauvoir
 * @version $Id: CP_EditorHandler.java,v 1.1 1998/03/26 15:24:11 ynsingh Exp $
 */
public class CP_EditorHandler
implements IEditorHandler, IIcons
{
    // Version names
    
    public static final String IMS_CONTENT_PACKAGING_1_1 = "IMS Content Packaging 1.1"; //$NON-NLS-1$
    public static final String IMS_CONTENT_PACKAGING_1_1_1 = "IMS Content Packaging 1.1.1"; //$NON-NLS-1$
    public static final String IMS_CONTENT_PACKAGING_1_1_2 = "IMS Content Packaging 1.1.2"; //$NON-NLS-1$
    public static final String IMS_CONTENT_PACKAGING_1_1_3 = "IMS Content Packaging 1.1.3"; //$NON-NLS-1$

    // CP Namespace prefix
    public static String IMSCP_NAMESPACE_PREFIX = "imscp"; //$NON-NLS-1$

    // CP Version 1.1.3
    public static Namespace IMSCP_NAMESPACE_113 =  Namespace.getNamespace("http://www.imsglobal.org/xsd/imscp_v1p1"); //$NON-NLS-1$
    
    // CP Version 1.1.2
    public static Namespace IMSCP_NAMESPACE_112 = Namespace.getNamespace("http://www.imsproject.org/xsd/imscp_rootv1p1p2"); //$NON-NLS-1$
    
    // CP Version 1.1.1 
    public static Namespace IMSCP_NAMESPACE_111 = Namespace.getNamespace("http://www.imsproject.org/xsd/ims_cp_rootv1p1"); //$NON-NLS-1$

    // CP Bogus Namespace as in WebCT
    public static Namespace CP_BOGUS_NAMESPACE1 = Namespace.getNamespace("http://www.imsproject.org/content"); //$NON-NLS-1$
    
    /**
     * Namespace prefix for ADL SCORM
     */ 
    public static String ADLCP_NAMESPACE_PREFIX = "adlcp"; //$NON-NLS-1$

    // ADL SCORM Version 1.2
    public static Namespace ADLCP_NAMESPACE_12 = Namespace.getNamespace("adlcp", "http://www.adlnet.org/xsd/adlcp_rootv1p2"); //$NON-NLS-1$ //$NON-NLS-2$

    // ADL SCORM Version 1.3 - we don't support this
    public static Namespace ADLCP_NAMESPACE_13 = Namespace.getNamespace("adlcp", "http://www.adlnet.org/xsd/adlcp_v1p3"); //$NON-NLS-1$ //$NON-NLS-2$

    /**
     * Namespace prefix for Learning Design
     */ 
    public static String IMSLD_NAMESPACE_PREFIX = "imsld"; //$NON-NLS-1$

    // LD Version 1.0
    public static Namespace IMSLD_NAMESPACE_10 = Namespace.getNamespace("imsld", "http://www.imsglobal.org/xsd/imsld_v1p0");

    /**
     * Namespace prefix for IMS Metadata
     */ 
    public static String IMSMD_NAMESPACE_PREFIX = "imsmd"; //$NON-NLS-1$

    /**
     * Supported CP Namespaces
     */
    protected static Hashtable SUPPORTED_CP_NAMESPACES = new Hashtable();
    
    static {
		// Add to table of Supported Namespaces mapped to Version Names
        SUPPORTED_CP_NAMESPACES.put(IMSCP_NAMESPACE_111, IMS_CONTENT_PACKAGING_1_1_1);
        SUPPORTED_CP_NAMESPACES.put(IMSCP_NAMESPACE_112, IMS_CONTENT_PACKAGING_1_1_2);
        SUPPORTED_CP_NAMESPACES.put(IMSCP_NAMESPACE_113, IMS_CONTENT_PACKAGING_1_1_3);
		
		// Bogus support
        SUPPORTED_CP_NAMESPACES.put(CP_BOGUS_NAMESPACE1, IMS_CONTENT_PACKAGING_1_1_3);
    }
    
    
    
    public static final File HELPER_FOLDER = new File(EditorHandler.HELPERFOLDER, "cp"); //$NON-NLS-1$
    public static final File PROFILE_FOLDER = new File(HELPER_FOLDER, "profile"); //$NON-NLS-1$
    public static final File SCHEMAHELPER_FOLDER = new File(HELPER_FOLDER, "schemahelper"); //$NON-NLS-1$
    public static final File VOCAB_FOLDER = new File(HELPER_FOLDER, "vocab"); //$NON-NLS-1$

    
    
    /**
	 * Constructor
	 */
	public CP_EditorHandler() {
	
	}

    public boolean canCreateDocuments() {
        return true;
    }

    /* (non-Javadoc)
     * @see uk.ac.reload.editor.IEditorHandler#canEditFile(java.io.File)
     */
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
		
		// Now find out if it is a SCORM Document. If so, say No!
		if(getSCORM_Namespace(doc) != null) {
		    return false;
		}
		
		// Now find out if it is a LD Document. If so, say No!
		if(getLD_Namespace(doc) != null) {
		    return false;
		}

		// And simply check against our list of supported CP Namespaces
		return SUPPORTED_CP_NAMESPACES.containsKey(nameSpace);
	}
	
	/* (non-Javadoc)
	 * @see uk.ac.reload.editor.IEditorHandler#editFile(java.io.File)
	 */
	public EditorInternalFrame editFile(File file) throws JDOMException, SchemaException, IOException {
	    // Test if a zip file
	    if(file.getName().toLowerCase().endsWith(".zip")) { //$NON-NLS-1$
	        File manifest = unzipManifest(file);
	        if(manifest == null) {
	            return null;
	        }
	        else {
		        // If we've got a new file, reference the imsmanifest.xml
	            file = manifest;
	        }
	    }
	    
	    // If it's the bogus Namespace ask to change to correct one
	    boolean ok = replaceBogusNamespace(file, true);
	    if(!ok) {
	        return null;
	    }

    	// Get a new ContentPackage instance
    	ContentPackage cp = new ContentPackage(file);
    	return new CP_Editor(cp);
    }
	
	/**
	 * Check to see if we have the Bogus Namespace and ask to change it
	 * 
	 * @param manifest The imsmanifest.xml file
	 * @throws IOException
	 * @throws JDOMException
	 */
	protected boolean replaceBogusNamespace(File manifest, boolean askUser) throws IOException, JDOMException {
	    Document doc = getDocument(manifest);
	    Namespace ns = XMLUtils.getDocumentNamespace(doc);
	    
	    if(CP_BOGUS_NAMESPACE1.equals(ns)) {
	        if(askUser) {
	            int result = JOptionPane.showConfirmDialog(EditorFrame.getInstance(),
	                    Messages.getString("uk.ac.reload.editor.contentpackaging.CP_EditorHandler.0") + ":     " + ns.getURI() + "\n" + //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	                    Messages.getString("uk.ac.reload.editor.contentpackaging.CP_EditorHandler.1") + "\n\n" + //$NON-NLS-1$ //$NON-NLS-2$
	                    Messages.getString("uk.ac.reload.editor.contentpackaging.CP_EditorHandler.2") + "\n" + //$NON-NLS-1$ //$NON-NLS-2$
	                    Messages.getString("uk.ac.reload.editor.contentpackaging.CP_EditorHandler.3") + "\n" + //$NON-NLS-1$ //$NON-NLS-2$
	                    Messages.getString("uk.ac.reload.editor.contentpackaging.CP_EditorHandler.4"), //$NON-NLS-1$
	                    Messages.getString("uk.ac.reload.editor.contentpackaging.CP_EditorHandler.13"), //$NON-NLS-1$
	                    JOptionPane.YES_NO_OPTION);
	            
	            if(result != JOptionPane.YES_OPTION) {
	                return false;
	            }
	        }
	        
	        // Backup copy
	        FileUtils.copyFile(manifest, new File(manifest.getPath() + ".bak")); //$NON-NLS-1$
	        
	        // CP Namespace
	        XMLUtils.replaceNamespaces(doc.getRootElement(), ns, IMSCP_NAMESPACE_113);
	        
	        // MD Namespace
	        XMLUtils.replaceNamespaces(doc.getRootElement(), MD_EditorHandler.MD_BOGUS_NAMESPACE1,
	                Namespace.getNamespace("imsmd", MD_EditorHandler.IMSMD_NAMESPACE_122.getURI())); //$NON-NLS-1$
	        
	        // Insert comment to this effect
	        doc.getContent().add(0, new Comment(Messages.getString("uk.ac.reload.editor.contentpackaging.CP_EditorHandler.5") + //$NON-NLS-1$
	                EditorProperties.getString("APP_NAME"))); //$NON-NLS-1$
	        
	        // Resave
	        XMLUtils.write2XMLFile(doc, manifest);
	    }
	    
	    return true;
	}

	/* (non-Javadoc)
	 * @see uk.ac.reload.editor.IEditorHandler#newDocument()
	 */
	public EditorInternalFrame newDocument() throws JDOMException, SchemaException, IOException {
		// Get default CP & MD versions from user Prefs
		String versionCP = EditorPrefs.getInstance().getValue(EditorPrefs.CP_DEFAULT_CP_VERSION);
		String versionMD = EditorPrefs.getInstance().getValue(EditorPrefs.CP_DEFAULT_MD_VERSION);
		
		String[] versionsMD = EditorHandler.MD_EDITORHANDLER.getSupportedVersions();
		String[] versionsCP = getSupportedVersions();
		
		// Ask for New CP Folder
		CP_NewDialog dialog = new CP_NewDialog(versionsCP, versionCP, versionsMD, versionMD);
		File cpFolder = dialog.showDialog();
		// User cancelled or Project Folder not valid
		if(cpFolder == null) {
		    return null;
		}
		
		versionCP = dialog.getVersionCP();
		versionMD = dialog.getVersionMD();
		
		// Save to user Prefs
		EditorPrefs.getInstance().putValue(EditorPrefs.CP_DEFAULT_CP_VERSION, versionCP);
		EditorPrefs.getInstance().putValue(EditorPrefs.CP_DEFAULT_MD_VERSION, versionMD);
		
		// Load CP Editor
	    CP_SchemaController cpController = (CP_SchemaController)getSchemaControllerInstance(versionCP);
		MD_SchemaController mdController = (MD_SchemaController)EditorHandler.MD_EDITORHANDLER.getSchemaControllerInstance(versionMD);
		ContentPackage cp = new ContentPackage(cpFolder, cpController, mdController);
		return new CP_Editor(cp);
	}

	/**
	 * Pull the JDOM Document out of the file
	 * @return The JDOM Document or null
	 */
	protected Document getDocument(File file) throws IOException, JDOMException {
	    // Check for zip file
	    if(file.getName().toLowerCase().endsWith(".zip")) { //$NON-NLS-1$
	        String manifest = ZipUtils.extractZipEntry(file, CP_Core.MANIFEST_NAME);
	        if(manifest != null) {
	            return XMLUtils.readXMLString(manifest);
	        }
	        else {
	            throw new IOException(Messages.getString("uk.ac.reload.editor.contentpackaging.CP_EditorHandler.6")); //$NON-NLS-1$
	        }
	    }
		
	    // Try to load the Document
	    else {
	        return XMLUtils.readXMLFile(file);
	    }
	}
	
	
	/* (non-Javadoc)
	 * @see uk.ac.reload.editor.IEditorHandler#getName()
	 */
	public String getName() {
	    return Messages.getString("uk.ac.reload.editor.contentpackaging.CP_EditorHandler.7"); //$NON-NLS-1$
	}
	
	/* (non-Javadoc)
	 * @see uk.ac.reload.editor.IEditorHandler#getIcon()
	 */
	public Icon getIcon() {
	    return DweezilUIManager.getIcon(ICON_CP);
	}

	/** 
	 * @return Supported versions of CP
	 */
	public String[] getSupportedVersions() {
	    return new String[] {
	            IMS_CONTENT_PACKAGING_1_1_1,
	            IMS_CONTENT_PACKAGING_1_1_2,
	            IMS_CONTENT_PACKAGING_1_1_3
	    };
	}

	/** 
	 * @return The default version of CP to use
	 */
	public String getDefaultVersion() {
	    return IMS_CONTENT_PACKAGING_1_1_3;
	}

	/** 
	 * @return The default version of MD to use
	 */
	public String getDefaultMDVersion() {
	    return MD_EditorHandler.IMS_METADATA_1_2_2;
	}

	/**
	 * @param ns
	 * @return The Version String for a given Namespace
	 */
	public String getVersion(Namespace ns) {
	    return ns == null ? null : (String)SUPPORTED_CP_NAMESPACES.get(ns);
	}
	
	/**
	 * If file is a zip file, check for imsmanifest and unzip to chosen folder
	 * @param fileCP The file to unzip
	 * @return The File reference to the unzipped imsmanifest.xml file or null if there wasn't one
	 * or the file wasn't a zip file
	 */
	protected File unzipManifest(File fileCP) throws IOException {
	    File manifest = null;
	    DweezilProgressMonitor progressMonitor = null;
	    
	    // First check that archive contains a manifest file
	    if(!containsManifest(fileCP)) {
	        throw new IOException(Messages.getString("uk.ac.reload.editor.contentpackaging.CP_EditorHandler.8")); //$NON-NLS-1$
	    }
	    
	    // Ask for new project folder
	    CP_NewDialog dialog = new CP_NewDialog(Messages.getString("uk.ac.reload.editor.contentpackaging.CP_EditorHandler.9") + fileCP.getName()); //$NON-NLS-1$
	    File targetFolder = dialog.showDialog();
	    // If the folder already contains the manifest or there was some other problem then return
	    if(targetFolder == null) {
	        return null;
	    }
	    
	    progressMonitor = new DweezilProgressMonitor(EditorFrame.getInstance(),
	            Messages.getString("uk.ac.reload.editor.contentpackaging.CP_EditorHandler.10"), //$NON-NLS-1$
	            Messages.getString("uk.ac.reload.editor.contentpackaging.CP_EditorHandler.11"), //$NON-NLS-1$
	            Messages.getString("uk.ac.reload.editor.contentpackaging.CP_EditorHandler.12"), //$NON-NLS-1$
	            true,
	            DweezilUIManager.getIcon(ICON_APP16));
	    
	    try {
            // Unzip and get imsmanifest
            manifest = unzipContentPackage(fileCP, targetFolder, progressMonitor);
        } 
	    finally {
		    if(progressMonitor != null) {
		        progressMonitor.close();
		    }
        }
	    
	    return manifest;
	}
	
    /**
     * Looks for imsmanifest.xml in a zip archive
     * @param zipFile
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static boolean containsManifest(File zipFile) throws FileNotFoundException, IOException {
        return ZipUtils.hasZipEntry(zipFile, CP_Core.MANIFEST_NAME);
    }
	
	/**
	 * Unzip a Content Package Archive File
	 * @param zipFile
	 * @param targetFolder
	 * @param progressMonitor
	 * @return the Unzipped imsmanifest.xml file or null
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static File unzipContentPackage(File zipFile, File targetFolder, DweezilProgressMonitor progressMonitor) throws FileNotFoundException, IOException {
		File manifestFile = null;
		
		boolean result = ZipUtils.unpackZip(zipFile, targetFolder, progressMonitor);
		
		// Pressed Cancel
		if(!result) {
		    return null;
		}
		
		// Get the unzipped manifest
		manifestFile = new File(targetFolder, CP_Core.MANIFEST_NAME);
		
		// Make sure it is there in case something went wrong...
		if(!manifestFile.exists()) {
		    return null;
		}
		
		return manifestFile;
	}

	
	/**
	 * @return The SCORM Namespace if this doc a SCORM Document - we look for the ADL Namespaces
	 * or null if not found in the Document
	 */
	public static Namespace getSCORM_Namespace(Document doc) {
		// We'll search all elements for the ADL Namespace
		boolean found = XMLUtils.containsNamespace(doc, ADLCP_NAMESPACE_12);
		if(found) {
		    return ADLCP_NAMESPACE_12;
		}
		
		found = XMLUtils.containsNamespace(doc, ADLCP_NAMESPACE_13);
		if(found) {
		    return ADLCP_NAMESPACE_13;
		}
		
		return null;
	}
	
	/**
	 * @return The LearningDesign Namespace if this doc is a LD Document - we look for the LD Namespaces.
	 * or null if not found in the Document
	 */
	public static Namespace getLD_Namespace(Document doc) {
		// We'll search all elements for the LD 1.0 Namespace
		boolean found = XMLUtils.containsNamespace(doc, IMSLD_NAMESPACE_10);
		if(found) {
		    return IMSLD_NAMESPACE_10;
		}
		return null;
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
	    
	    if(IMS_CONTENT_PACKAGING_1_1_2.equals(version)) {
	        schemaController = new CP112_SchemaController();
	    }
	    else if(IMS_CONTENT_PACKAGING_1_1_3.equals(version)) {
	        schemaController = new CP113_SchemaController();
	    }
	    else if(IMS_CONTENT_PACKAGING_1_1_1.equals(version)) {
	        schemaController = new CP111_SchemaController();
	    }
	    
	    return schemaController;
	}
	
	// ============================= Other shared useful routines ==================================
	
	/**
	 * Zip up a Content Package
	 */
	static public void zipContentPackage(ContentPackage cp) {
		if(cp != null) {
			DweezilProgressMonitor progressMonitor = null;
			
			try {
				// Ask for a file name
				DweezilFileFilter filter = new DweezilFileFilter(new String[] {"zip", "jar"}, Messages.getString("uk.ac.reload.editor.contentpackaging.CP_Editor.19")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				File file = DweezilFileChooser.askFileNameSave(EditorFrame.getInstance(), Messages.getString("uk.ac.reload.editor.contentpackaging.CP_Editor.20"), filter, "zip"); //$NON-NLS-1$ //$NON-NLS-2$
				if(file != null) {
					progressMonitor = new DweezilProgressMonitor(EditorFrame.getInstance(),
					        Messages.getString("uk.ac.reload.editor.contentpackaging.CP_Editor.21"), //$NON-NLS-1$
							Messages.getString("uk.ac.reload.editor.contentpackaging.CP_Editor.44"), //$NON-NLS-1$
							"", //$NON-NLS-1$
							true,
							DweezilUIManager.getIcon(ICON_APP16));
					
					BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file));
					ZipOutputStream zOut = new ZipOutputStream(out);
					//if (EditorPrefs.getInstance().getBooleanValue(EditorPrefs.CP_ZIP_REFERENCED_FILES)){ // prefs check here...
					//	File[] referencedResources = _contentPackage.getResourceFiles(_contentPackage.getRootElement());                 
					//	ZipUtils.addFilesToZip(_contentPackage.getProjectFolder(),referencedResources, zOut, file );
					//}
					//else{
					ZipUtils.addFolderToZip(cp.getProjectFolder(), cp.getProjectFolder(), zOut, file);
					//}
					zOut.flush();
					zOut.close();
				}
			}
			catch(Exception ex) {
				if(EditorProperties.getString("DEBUG").equals("true")) { //$NON-NLS-1$ //$NON-NLS-2$
				    ex.printStackTrace();
				}
				ErrorDialogBox.showWarning(EditorFrame.getInstance(),
				        Messages.getString("uk.ac.reload.editor.contentpackaging.CP_Editor.21"), //$NON-NLS-1$
				        Messages.getString("uk.ac.reload.editor.contentpackaging.CP_Editor.22"), //$NON-NLS-1$
				        ex);
			}
			finally {
				if(progressMonitor != null) {
				    progressMonitor.close();
				}
			}
		}
	}

}
