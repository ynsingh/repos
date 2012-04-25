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

package uk.ac.reload.editor.metadata.xml;

import java.io.File;
import java.io.IOException;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.Namespace;

import uk.ac.reload.editor.EditorHandler;
import uk.ac.reload.editor.properties.EditorProperties;
import uk.ac.reload.jdom.XMLDocumentClipboard;
import uk.ac.reload.jdom.XMLUtils;
import uk.ac.reload.moonunit.SchemaDocument;
import uk.ac.reload.moonunit.schema.SchemaElement;
import uk.ac.reload.moonunit.schema.SchemaException;
import uk.ac.reload.moonunit.schema.SchemaModel;


/**
 * The Metadata Class
 *
 * @author Phillip Beauvoir
 * @version $Id: Metadata.java,v 1.1 1998/03/26 15:32:14 ynsingh Exp $
 */
public class Metadata extends SchemaDocument
{
    /**
     * Our unique signature
     */
    static final String[] md_comments = {
        "This is a Reload version " + EditorProperties.getString("VERSION") + " Metadata document",
        "Spawned from the Reload Metadata Generator - http://www.reload.ac.uk"
    };

    /**
     * Whether we are a standalone file or attached
     */
    private boolean _isStandalone;
    
    /**
     * Constructor for blank MD
     */
    public Metadata(boolean isStandalone, MD_SchemaController controller) {
        _isStandalone = isStandalone;
        
        setSchemaController(controller);

        setDocument(new Document());

        // Add our signature
        if(_isStandalone) addCommentsToDocument();

        // Add Root Element according to Schema
        if(controller != null) {
            SchemaModel mdSchema = controller.getSchemaModel();
            Element root = addElementBySchema(this, null, mdSchema.getRootElement(), true);
            getDocument().setRootElement(root);
            if(_isStandalone) addRootDeclarations();
        }
        
        // Clear the dirty flag because adding the root made it dirty
        setDirty(false);
    }

    /**
     * Constructor for a MD Doc that exists
     * @throws JDOMException
     * @throws IOException
     * @throws SchemaException
     */
    public Metadata(boolean isStandalone, Document doc) throws JDOMException, SchemaException, IOException {
        if(doc == null) {
            throw new NullPointerException("Document cannot be null in Metadata constructor");
        }
        
        // Load the Document
        setDocument(doc);
        
        // Get the Schema Controller *after* setting the doc
        loadSchemaControllerInstance();
        
        _isStandalone = isStandalone;
    }

    /**
     * Constructor for a MD File that exists - will be Standalone
     * @throws JDOMException
     * @throws IOException
     * @throws SchemaException
     */
    public Metadata(File file) throws JDOMException, SchemaException, IOException {
        if(file == null) {
            throw new NullPointerException("File cannot be null in Metadata constructor");
        }

        setFile(file);
		
        // Load the Document
		setDocument(XMLUtils.readXMLFile(file));

		// Get the Schema Controller *after* setting the doc
        loadSchemaControllerInstance();
        
        _isStandalone = true;
    }
    
    /**
     * Load the SchemaControllerInstance  
     * @throws JDOMException
     * @throws IOException
     * @throws SchemaException
     */
    protected void loadSchemaControllerInstance() throws JDOMException, SchemaException, IOException {
    	// Get version from Namespace
    	Namespace nameSpace = XMLUtils.getDocumentNamespace(getDocument());
    	String version = EditorHandler.MD_EDITORHANDLER.getVersion(nameSpace);
    	// No, use default
    	if(version == null) {
    	    version = EditorHandler.MD_EDITORHANDLER.getDefaultVersion();
    	}

    	setSchemaController(EditorHandler.MD_EDITORHANDLER.getSchemaControllerInstance(version));
    }

    /**
     * Create a non-standalone (embedded) MD that is based on this standalone one.
     * Used when importing MD into CP
     */
    public Metadata createEmbeddedMetadata() {
        Metadata md = new Metadata(false, (MD_SchemaController)getSchemaController());
        Document clone = (Document)getDocument().clone();
        md.setDocument(clone);
        
        // Get the Root Element
        Element root = clone.getRootElement();

        // Clear any Attributes
        root.setAttributes(null);

        // Add Namespace prefixes to all Elements
        Namespace ns = root.getNamespace();
        if(ns != null) {
            XMLUtils.replaceNamespaces(root, ns, getRootNamespaceEmbedded());
        }

        return md;
    }

    /**
     * If this is a non-standalone embedded MD create a standalone version suitable for exporting or editing as standalone
     */
    public Metadata createStandaloneMetadata() {
        Metadata md = new Metadata(true, (MD_SchemaController)getSchemaController());
        
        // Clone of root
        Element root = (Element)getDocument().getRootElement().clone();
        
        // Remove Namespace prefixes from all Elements
        Namespace ns = root.getNamespace();
        if(ns != null) {
            XMLUtils.replaceNamespaces(root, ns, Namespace.getNamespace(ns.getURI()));
        }
        
        // Set to root
        md.getDocument().setRootElement(root);
        
        // Add root declarations
        md.addRootDeclarations();
        
        return md;
    }

    /**
     * @return the Comments to add to the XML Document
     */
    public String[] getComments() {
        return md_comments;
    }

    /**
     * Add namespace and other attribute declarations to the root element
     */
    protected void addRootDeclarations() {
        Element root = getDocument().getRootElement();
        
        // Add XSI Schema Instance Namespace
        root.addNamespaceDeclaration(XMLUtils.XSI_Namespace);

        // Add Schema Location Attribute which is constructed from Target Namespace
        // and file name of Schema
        SchemaModel mdSchema = getSchemaController().getSchemaModel();
        String schemaLocationURI = mdSchema.getTargetNamespaceURI() + " " + mdSchema.getSchemaName();
        root.setAttribute(XMLUtils.XSI_SchemaLocation, schemaLocationURI, XMLUtils.XSI_Namespace);
    }

    /**
     * Return whether we are a standalone file
     * @return True if standalone (not embedded in another namespaced doc)
     */
    public boolean isStandalone() {
        return _isStandalone;
    }

    /**
     * Get the root Namespace with added prefix
     * This is for IMS Metadata - "imsmd" - for IEEE Metadata this will be different
     */
    public Namespace getRootNamespaceEmbedded() {
        return Namespace.getNamespace("imsmd", getRootNamespace().getURI());
    }

	/**
	 * Over-ride this so we can intercept it to do stuff
	 */
	public Element addElementBySchema(Object source, Element parentElement, SchemaElement newSchemaElement, boolean doSelect) {
		Element newElement = super.addElementBySchema(source, parentElement, newSchemaElement, doSelect);
		
		if(newElement != null) {
			// We'll add a default lang attribute
		    if(newSchemaElement.hasSchemaAttribute("lang", Namespace.XML_NAMESPACE)) {
			    addAttributeWithDefaultValue(source, newElement, "xml:lang");
			}
		}

		return newElement;
	}
	

// ==========================================================================
// ELEMENT MOVING/COPYING/DRAGGING HANDLING
// ==========================================================================

    /**
     * @param element the Element to copy
     * @return whether we can copy an element
     */
    public boolean canCopyElement(Element element) {
        if(element == null) return false;
        return !element.isRootElement();
    }

    /**
     * @param element the Element to cut
     * @return whether we can cut an element
     */
    public boolean canCutElement(Element element) {
        if(element == null) return false;
        return canDeleteElement(element);
    }

    /**
     * @param parentElement the Parent Element
     * @return whether we can paste the XMLDocumentClipboard element as a child of element
     */
    public boolean canPasteFromClipboard(Element parentElement) {
        if(parentElement == null) return false;
        Element clipboardElement = XMLDocumentClipboard.getElement();
        if(clipboardElement == null) return false;
        return clipboardElement != null && isAllowedChild(parentElement, clipboardElement);
    }
}