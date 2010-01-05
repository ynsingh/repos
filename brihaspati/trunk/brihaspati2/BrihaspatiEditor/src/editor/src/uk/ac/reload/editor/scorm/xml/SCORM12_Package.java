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


package uk.ac.reload.editor.scorm.xml;

import java.io.File;
import java.io.IOException;

import org.jdom.Attribute;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.Namespace;

import uk.ac.reload.editor.EditorHandler;
import uk.ac.reload.editor.contentpackaging.CP_EditorHandler;
import uk.ac.reload.editor.contentpackaging.xml.CP_SchemaController;
import uk.ac.reload.editor.contentpackaging.xml.ContentPackage;
import uk.ac.reload.editor.metadata.xml.MD_SchemaController;
import uk.ac.reload.editor.properties.EditorProperties;
import uk.ac.reload.jdom.XMLUtils;
import uk.ac.reload.moonunit.schema.SchemaException;
import uk.ac.reload.moonunit.schema.SchemaModel;

/**
 * The SCORM 1.2 Content Package Class
 *
 * @author Phillip Beauvoir
 * @version $Id: SCORM12_Package.java,v 1.1 1998/03/26 15:32:14 ynsingh Exp $
 */
public class SCORM12_Package extends ContentPackage {
    
	/**
	 * Our unique signature
	 */
	static final String[] scorm_comments = {
			"This is a Reload version " + EditorProperties.getString("VERSION") + " SCORM 1.2 Content Package document",
			"Spawned from the Reload Content Package Generator - http://www.reload.ac.uk"
	};
	
	/**
	 * Default Constructor
	 */
	public SCORM12_Package() {
		super();
	}
	
	/**
	 * Constructor for new SCORM CP
	 */
	public SCORM12_Package(File projectFolder, SCORM12_SchemaController scormController, MD_SchemaController mdController) throws IOException {
	    setSchemaController(scormController);
	    scormController.setMD_SchemaController(mdController);
	    
	    // New Default Document
		init(projectFolder, scormController);
		
		// Save it
		saveDocument();
		
		// Clear the dirty flag
		setDirty(false);
		
		// Copy the Schema Files to the Project Folder
		scormController.copySchemaFilesToFolder(projectFolder);
	}

	/**
	 * Constructor for existing document
	 * @throws JDOMException
	 * @throws IOException
	 * @throws SchemaException
	 */
	public SCORM12_Package(File file) throws JDOMException, SchemaException, IOException {
    	setFile(file);

    	// Load the Document
    	setDocument(XMLUtils.readXMLFile(file));
	    
    	// Get version from Namespace
        Namespace nameSpace = XMLUtils.getDocumentNamespace(getDocument());
    	String versionSCORM = EditorHandler.SCORM12_EDITORHANDLER.getVersion(nameSpace);

    	SCORM12_SchemaController scormController = (SCORM12_SchemaController)EditorHandler.SCORM12_EDITORHANDLER.getSchemaControllerInstance(versionSCORM);
    	setSchemaController(scormController);

	    // Ascertain MD Controller
    	// If not found in root element, don't set global one because the Namespace might be declared in-line
        Namespace nsMD = getDocument().getRootElement().getNamespace("imsmd");
        if(nsMD != null) {
            String version = EditorHandler.MD_EDITORHANDLER.getVersion(nsMD);
        	if(version != null) {
        		MD_SchemaController mdController = (MD_SchemaController)EditorHandler.MD_EDITORHANDLER.getSchemaControllerInstance(version);
        		scormController.setMD_SchemaController(mdController);
        	}
        }
	}

	/**
	 * Setup the JDOM Document with Namespaces set and root element set and some default attributes.
	 */
	protected void init(File projectFolder, CP_SchemaController cpController) {
		super.init(projectFolder, cpController);
	    
	    Element root = getDocument().getRootElement();
		
		SchemaModel cpSchema = cpController.getSchemaModel();
		
		// Add ADL SCORM Namespace
		root.addNamespaceDeclaration(CP_EditorHandler.ADLCP_NAMESPACE_12);
		
		// Add Schema Location Attribute
		Attribute att = root.getAttribute(XMLUtils.XSI_SchemaLocation, XMLUtils.XSI_Namespace);
		if(att != null) {
			String value = att.getValue();
			if(value == null) value = "";
			StringBuffer schemaLocationURI = new StringBuffer(value);
			schemaLocationURI.append(" ");
			schemaLocationURI.append(CP_EditorHandler.ADLCP_NAMESPACE_12.getURI());
			schemaLocationURI.append(" ");
			schemaLocationURI.append(cpSchema.getSchemaName(CP_EditorHandler.ADLCP_NAMESPACE_12.getURI()));
			att.setValue(schemaLocationURI.toString());
		}
	}
	
	/**
	 * @return the Comments to add to the XML Document
	 */
	public String[] getComments() {
		return scorm_comments;
	}
}