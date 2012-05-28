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


package uk.ac.reload.editor.learningdesign.xml;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.jdom.Attribute;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.Namespace;

import uk.ac.reload.diva.util.RandomGUID;
import uk.ac.reload.editor.EditorHandler;
import uk.ac.reload.editor.contentpackaging.CP_EditorHandler;
import uk.ac.reload.editor.contentpackaging.xml.CP_SchemaController;
import uk.ac.reload.editor.contentpackaging.xml.ContentPackage;
import uk.ac.reload.editor.metadata.xml.MD_SchemaController;
import uk.ac.reload.editor.properties.EditorProperties;
import uk.ac.reload.jdom.XMLPath;
import uk.ac.reload.jdom.XMLUtils;
import uk.ac.reload.moonunit.contentpackaging.CP_Core;
import uk.ac.reload.moonunit.learningdesign.LD_Core;
import uk.ac.reload.moonunit.schema.SchemaException;
import uk.ac.reload.moonunit.schema.SchemaModel;


/**
 * Learning Design XML Document
 * 
 * @author Phillip Beauvoir
 * @version $Id: LearningDesign.java,v 1.1 1998/03/26 15:32:14 ynsingh Exp $
 */
public class LearningDesign
extends ContentPackage
{
    /**
     * Our unique signature
     */
    static final String[] ld_comments = {
        "This is a Reload version " + EditorProperties.getString("VERSION") + " Learning Design document",
        "Spawned from the Reload Learning Design Generator - http://www.reload.ac.uk"
    };

    /**
	 * Default Constructor
	 */
    public LearningDesign() {
		super();
	}

	/**
     * Constructor for new LD
	 * @throws IOException
     */
    public LearningDesign(File projectFolder, CP_SchemaController ldController, MD_SchemaController mdController) throws IOException {
        super(projectFolder, ldController, mdController);
    }
    
    /**
     * Constructor for a LD File that exists
     * @throws JDOMException
     * @throws IOException
     * @throws SchemaException
     */
    public LearningDesign(File file) throws JDOMException, SchemaException, IOException {
    	setFile(file);

    	// Load the Document
    	setDocument(XMLUtils.readXMLFile(file));
    	
        // Get version string from Namespace and level
        Namespace nameSpace = XMLUtils.getDocumentNamespace(getDocument());
    	String versionLD = EditorHandler.LD_EDITORHANDLER.getVersion(nameSpace, getLevel());

    	// And finally get and set Schema Controller from version string
    	CP_SchemaController ldController = (CP_SchemaController)EditorHandler.LD_EDITORHANDLER.getSchemaControllerInstance(versionLD);
    	setSchemaController(ldController);

	    /*
	     * Ascertain MD Controller
	     * If not found in root element, don't set global one because the Namespace might be declared in-line
	     */ 
        Namespace nsMD = getDocument().getRootElement().getNamespace("imsmd");
        if(nsMD != null) {
            String version = EditorHandler.MD_EDITORHANDLER.getVersion(nsMD);
        	if(version != null) {
        		MD_SchemaController mdController = (MD_SchemaController)EditorHandler.MD_EDITORHANDLER.getSchemaControllerInstance(version);
        		ldController.setMD_SchemaController(mdController);
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
		
		// Add LD Namespace
		root.addNamespaceDeclaration(getLDNamespace());
		
		// Add Schema Location Attribute
		Attribute att = root.getAttribute(XMLUtils.XSI_SchemaLocation, XMLUtils.XSI_Namespace);
		if(att != null) {
			String value = att.getValue();
			if(value == null) {
			    value = "";
			}
			StringBuffer schemaLocationURI = new StringBuffer(value);
			schemaLocationURI.append(" ");
			schemaLocationURI.append(getLDNamespace().getURI());
			schemaLocationURI.append(" ");
			schemaLocationURI.append(cpSchema.getSchemaName(getLDNamespace().getURI()));
			att.setValue(schemaLocationURI.toString());
		}
		
		// Set Level according to Schema
		setLevel(((ILD_SchemaController)cpController).getLevel());
	}
	
    /**
     * @return the Comments to add to the XML Document
     */
    public String[] getComments() {
        return ld_comments;
    }
    
    /**
     * Get the LD Namespace for this Document
     */
    public Namespace getLDNamespace() {
        // Note - this should really get the Namespace from the document
        return CP_EditorHandler.IMSLD_NAMESPACE_10;
    }

    /**
     * Set the LD Level - this has to be "A", "B", or "C".
     * If null, will be set to "C".
     */
    public void setLevel(String level) {
        if(level == null) {
            level = "C";
        }
        else {
            level = level.toUpperCase();
        }
        
        getLDElement().setAttribute(LD_Core.LEVEL, level);
    }

    /**
     * @return The LD Level - "A", "B", or "C"
     */
    public String getLevel() {
        String level = getLDElement().getAttributeValue(LD_Core.LEVEL);
        if(level == null) {
            level = "C";
            setLevel(level);   // Set it in document
        }
        else { 
            level = level.toUpperCase();
            if(!(level.equals("A") || level.equals("B") || level.equals("C"))) {
                level = "C"; //default
                setLevel(level);
            }
        }
        
        return level;
    }
    
    /**
	 * Generate a Unique Identifier for an Element
	 */
	public String generateUniqueID(Element element){
		if(element.getNamespace().equals(getLDNamespace())) {
		    String prefix = "LD-";
		    return RandomGUID.getUniqueID(prefix);
		}
		else {
		    return super.generateUniqueID(element);
		}
	}

	/**
	 * @return The <learning-design> Element.
	 * If the element does not exist, it will be created
	 */
	public Element getLDElement() {
	    Element element = getRootElement().getChild("organizations", getRootElement().getNamespace());
	    if(element == null) {
	        element = addElementByXMLPath(this, getRootElement(), new XMLPath(CP_Core.ORGANIZATIONS), false);
	    }
	    
	    element = element.getChild("learning-design", getLDNamespace());
	    if(element == null) {
	        element = addElementByXMLPath(this, getRootElement(), new XMLPath("imsld:learning-design"), false);
	    }
	    
	    return element;
	}

	/**
     * @return The root <resources> Element for this Content Package.
     * If the element does not exist, it will be created
     */
    public Element getResourcesElement() {
        Element elResources = getResourcesElement(getRootElement());
        if(elResources == null) {
            elResources = addElementByXMLPath(this, getRootElement(), new XMLPath(CP_Core.RESOURCES), false);
        }
        return elResources;
    }
    
    /**
     * @return Any and all <resource> elements from the root <resources> Element for this Content Package.
     */
    public Element[] getResourceElements() {
        Element elResources = getResourcesElement();
        List list = elResources.getChildren(CP_Core.RESOURCE, elResources.getNamespace());
        Element[] elements = new Element[list.size()];
        list.toArray(elements);
        return elements;
    }

}
