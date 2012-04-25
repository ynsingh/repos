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

package uk.ac.reload.editor.learningdesign;

import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

import javax.swing.Icon;

import org.jdom.Document;
import org.jdom.JDOMException;
import org.jdom.Namespace;

import uk.ac.reload.dweezil.util.DweezilUIManager;
import uk.ac.reload.editor.EditorInternalFrame;
import uk.ac.reload.editor.contentpackaging.CP_EditorHandler;
import uk.ac.reload.editor.contentpackaging.xml.CP_SchemaController;
import uk.ac.reload.editor.learningdesign.datamodel.LD_DataModel;
import uk.ac.reload.editor.learningdesign.editor.LD_Editor;
import uk.ac.reload.editor.learningdesign.editor.LD_NewDialog;
import uk.ac.reload.editor.learningdesign.xml.LDA10_CP113_SchemaController;
import uk.ac.reload.editor.learningdesign.xml.LDB10_CP113_SchemaController;
import uk.ac.reload.editor.learningdesign.xml.LDC10_CP113_SchemaController;
import uk.ac.reload.editor.learningdesign.xml.LearningDesign;
import uk.ac.reload.editor.metadata.MD_EditorHandler;
import uk.ac.reload.editor.metadata.xml.MD_SchemaController;
import uk.ac.reload.jdom.XMLUtils;
import uk.ac.reload.moonunit.SchemaController;
import uk.ac.reload.moonunit.schema.SchemaException;


/**
 * The Editor implementation for LD<br>

 * @author Phillip Beauvoir
 * @version $Id: LD_EditorHandler.java,v 1.1 1998/03/26 15:24:11 ynsingh Exp $
 */
public class LD_EditorHandler
extends CP_EditorHandler
{
    public static final String IMS_LEARNING_DESIGN_A_1_0 = "IMS Learning Design A 1.0";
    public static final String IMS_LEARNING_DESIGN_B_1_0 = "IMS Learning Design B 1.0";
    public static final String IMS_LEARNING_DESIGN_C_1_0 = "IMS Learning Design C 1.0";
    
    public static final String IMS_LEARNING_DESIGN_A_1_0_CP_1_1_3 = IMS_LEARNING_DESIGN_A_1_0 + ", " + IMS_CONTENT_PACKAGING_1_1_3; 
    public static final String IMS_LEARNING_DESIGN_B_1_0_CP_1_1_3 = IMS_LEARNING_DESIGN_B_1_0 + ", " + IMS_CONTENT_PACKAGING_1_1_3; 
    public static final String IMS_LEARNING_DESIGN_C_1_0_CP_1_1_3 = IMS_LEARNING_DESIGN_C_1_0 + ", " + IMS_CONTENT_PACKAGING_1_1_3; 

    static Hashtable SUPPORTED_LD_NAMESPACES = new Hashtable();

    static {
		// Add to table of Supported LD Namespaces mapped to Version names
        SUPPORTED_LD_NAMESPACES.put(IMSLD_NAMESPACE_10, IMS_LEARNING_DESIGN_A_1_0);
    }
    
    // Magic Folder for Reload LD files
    public static final String LD_MAGIC_FOLDER = "reload-magicfolder";
    
    // Author Notes File
    public static final String AUTHOR_NOTES_FILE = LD_MAGIC_FOLDER + "/ld-authornotes.txt";
	
    /**
	 * Constructor
	 */
	public LD_EditorHandler() {
	}
	
    public boolean canCreateDocuments() {
        return true;
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
		
		// Now find out if it is a LD Document and if so whether we support it
		// We'll search all elements for the LD Namespace
        Namespace nsLD = getLD_Namespace(doc);
        if(nsLD == null) {
            return false;
        }
        
        // Do we support this version of LD?
        if(SUPPORTED_LD_NAMESPACES.containsKey(nsLD) == false) {
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
	    
    	// Get a new LD instance
	    LearningDesign ld = new LearningDesign(file);
	    LD_DataModel ldDataModel = new LD_DataModel(ld);
	    
	    LD_Editor editor = new LD_Editor();
	    editor.setDataModel(ldDataModel);
	    
        // The Editor may have added some (<title> and other) elements, so the Document will now be dirty.
	    // So don't indicate that, but we shouldn't save it yet.
        ld.setDirty(false);

    	return editor;
    }

	/* (non-Javadoc)
	 * @see uk.ac.reload.editor.IEditorHandler#newDocument()
	 */
	public EditorInternalFrame newDocument() throws JDOMException, SchemaException, IOException {
	    // Ask for New LD Folder
	    LD_NewDialog dialog = new LD_NewDialog("Choose Folder for Learning Design");
	    File ldFolder = dialog.showDialog();
	    // User cancelled or Project Folder not valid
	    if(ldFolder == null) {
	        return null;
	    }
	    
	    // Get LD Level
	    String level = dialog.getLevelLD();
	    
	    // Choose level "A", "B" or "C"
	    CP_SchemaController ldController = null;
	    
	    if("A".equals(level)) {
	        ldController = new LDA10_CP113_SchemaController();
	    }
	    else if("B".equals(level)) {
	        ldController = new LDB10_CP113_SchemaController();
	    }
	    else if("C".equals(level)) {
	        ldController = new LDC10_CP113_SchemaController();
	    }
	    else {
	        throw new SchemaException("Could not ascertain level");
	    }
	    
	    // Get Schema Controllers and Data Model
	    MD_SchemaController mdController = ldController.getDefaultMD_SchemaController();
	    LearningDesign ld = new LearningDesign(ldFolder, ldController, mdController);
	    LD_DataModel ldDataModel = new LD_DataModel(ld);
	    
	    // New Editor
	    LD_Editor editor = new LD_Editor();
	    editor.setDataModel(ldDataModel);
	    
	    /*
	     * Save it here again because loading the new XML Document into the editor will have added
	     * some default attributes and set some things (kludgey, I know...)
	     */
	    ldDataModel.getLearningDesign().saveDocument();
	    
	    return editor;
	}
	
	public String getName() {
	    return "IMS Learning Design";
	}
	
	public Icon getIcon() {
	    return DweezilUIManager.getIcon(ICON_LD);
	}

	/** 
	 * @return The default version of CP to use
	 */
	public String getDefaultVersion() {
	    return IMS_LEARNING_DESIGN_A_1_0;
	}

	/** 
	 * @return The default version of MD to use
	 */
	public String getDefaultMDVersion() {
	    return MD_EditorHandler.IMS_METADATA_1_2_1;
	}

	public String[] getSupportedVersions() {
	    return new String[] {
	            IMS_LEARNING_DESIGN_A_1_0_CP_1_1_3,
	            IMS_LEARNING_DESIGN_B_1_0_CP_1_1_3,
	            IMS_LEARNING_DESIGN_C_1_0_CP_1_1_3
	    };
	}

	/**
	 * @param ns Namespace
	 * @param level Level "A", "B" or "C"
	 * @return A version depending on Namespace and level
	 */
	public String getVersion(Namespace ns, String level) {
	    if(ns == null || level == null) {
	        return null;
	    }
	    
	    if(level.equalsIgnoreCase("A")) {
		    return IMS_LEARNING_DESIGN_A_1_0 + ", " + (String)SUPPORTED_CP_NAMESPACES.get(ns); //$NON-NLS-1$
	    }
	    else if(level.equalsIgnoreCase("B")) {
	        return IMS_LEARNING_DESIGN_B_1_0 + ", " + (String)SUPPORTED_CP_NAMESPACES.get(ns); //$NON-NLS-1$
	    }
	    else {
	        return IMS_LEARNING_DESIGN_C_1_0 + ", " + (String)SUPPORTED_CP_NAMESPACES.get(ns); //$NON-NLS-1$
	    }
	}

	/**
	 * Get a new instance implementation of the appropriate SchemaController
	 * for a given version key.<br>
	 * @throws IOException
	 * @throws SchemaException
	 */
	public SchemaController getSchemaControllerInstance(String version) throws JDOMException, SchemaException, IOException {
	    SchemaController schemaController = null;
	    
	    if(IMS_LEARNING_DESIGN_A_1_0_CP_1_1_3.equals(version)) {
	        schemaController = new LDA10_CP113_SchemaController();
	    }
	    else if(IMS_LEARNING_DESIGN_B_1_0_CP_1_1_3.equals(version)) {
	        schemaController = new LDB10_CP113_SchemaController();
	    }
	    else if(IMS_LEARNING_DESIGN_C_1_0_CP_1_1_3.equals(version)) {
	        schemaController = new LDC10_CP113_SchemaController();
	    }
	    
	    return schemaController;
	}
}
