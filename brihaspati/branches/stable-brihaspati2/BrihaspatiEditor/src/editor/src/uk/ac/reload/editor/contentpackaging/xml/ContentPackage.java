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

package uk.ac.reload.editor.contentpackaging.xml;

import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Vector;

import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.Namespace;

import uk.ac.reload.diva.util.FileUtils;
import uk.ac.reload.diva.util.HTMLUtils;
import uk.ac.reload.diva.util.IProgressMonitor;
import uk.ac.reload.diva.util.RandomGUID;
import uk.ac.reload.editor.EditorHandler;
import uk.ac.reload.editor.Messages;
import uk.ac.reload.editor.contentpackaging.CP_EditorHandler;
import uk.ac.reload.editor.contentpackaging.datamodel.CP_Resource;
import uk.ac.reload.editor.contentpackaging.htmlparser.FileType;
import uk.ac.reload.editor.contentpackaging.htmlparser.HTMLParser;
import uk.ac.reload.editor.metadata.MD_EditorHandler;
import uk.ac.reload.editor.metadata.xml.MD_SchemaController;
import uk.ac.reload.editor.properties.EditorProperties;
import uk.ac.reload.jdom.XMLDocumentClipboard;
import uk.ac.reload.jdom.XMLDocumentListenerEvent;
import uk.ac.reload.jdom.XMLPath;
import uk.ac.reload.jdom.XMLUtils;
import uk.ac.reload.moonunit.SchemaDocument;
import uk.ac.reload.moonunit.contentpackaging.CP_Core;
import uk.ac.reload.moonunit.contentpackaging.SCORM12_Core;
import uk.ac.reload.moonunit.schema.SchemaElement;
import uk.ac.reload.moonunit.schema.SchemaException;
import uk.ac.reload.moonunit.schema.SchemaModel;

/**
 * The Content Package Class
 *
 * @author Phillip Beauvoir
 * @version $Id: ContentPackage.java,v 1.1 1998/03/26 15:32:14 ynsingh Exp $
 */
public class ContentPackage
extends SchemaDocument
{
	
	/**
	 * Our unique signature
	 */
	static final String[] cp_comments = {
			"This is a Reload version " + EditorProperties.getString("VERSION") + " Content Package document", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			"Spawned from the Reload Content Package Generator - http://www.reload.ac.uk" //$NON-NLS-1$
	};
	
	/**
	 * Delegate class for common CP functionality
	 */
	private CP_Core _cpCore = new CP_Core(this);

	/**
	 * Default Constructor
	 */
	public ContentPackage() {
		super();
	}
	
	/**
	 * Constructor for new CP
	 */
	public ContentPackage(File projectFolder, CP_SchemaController cpController, MD_SchemaController mdController) throws IOException {
        if(projectFolder == null) {
            throw new NullPointerException("File cannot be null in ContentPackage constructor"); //$NON-NLS-1$
        }

        if(cpController == null) {
            throw new NullPointerException("CP_SchemaController cannot be null in ContentPackage constructor"); //$NON-NLS-1$
        }

        if(mdController == null) {
            throw new NullPointerException("MD_SchemaController cannot be null in ContentPackage constructor"); //$NON-NLS-1$
        }

        setSchemaController(cpController);
	    cpController.setMD_SchemaController(mdController);
	    
	    // New Default Document
		init(projectFolder, cpController);
		
		// Save it
		saveDocument();
		
		// Clear the dirty flag
		setDirty(false);
		
		// Copy the Schema Files to the Project Folder
		cpController.copySchemaFilesToFolder(projectFolder);
	}

	/**
	 * Constructor for existing document
	 * @throws JDOMException
	 * @throws IOException
	 * @throws SchemaException
	 */
	public ContentPackage(File file) throws JDOMException, SchemaException, IOException {
        if(file == null) {
            throw new NullPointerException("File cannot be null in ContentPackage constructor");
        }

    	setFile(file);

    	// Load the Document
    	setDocument(XMLUtils.readXMLFile(file));
	    
    	// Get version from Namespace
        Namespace nameSpace = XMLUtils.getDocumentNamespace(getDocument());
        String versionCP = EditorHandler.CP_EDITORHANDLER.getVersion(nameSpace);

	    CP_SchemaController cpController = (CP_SchemaController)EditorHandler.CP_EDITORHANDLER.getSchemaControllerInstance(versionCP);
	    setSchemaController(cpController);

	    // Ascertain MD Controller
    	// If not found in root element, don't set global one because the Namespace might be declared in-line
        Namespace nsMD = getDocument().getRootElement().getNamespace("imsmd"); //$NON-NLS-1$
        if(nsMD != null) {
            String version = EditorHandler.MD_EDITORHANDLER.getVersion(nsMD);
        	if(version != null) {
        		MD_SchemaController mdController = (MD_SchemaController)EditorHandler.MD_EDITORHANDLER.getSchemaControllerInstance(version);
        		cpController.setMD_SchemaController(mdController);
        	}
        }
	}
	
    /**
	 * Setup the JDOM Document with Namespaces set and root element set and some default attributes.
	 */
	protected void init(File projectFolder, CP_SchemaController cpController) {
		setDocument(new Document());
		
		// Add our signature
		addCommentsToDocument();
		
		// Content Packaging Schema
		SchemaModel cpSchema = cpController.getSchemaModel();
		
		// Set Root Element
		Element root = addElementBySchema(this, null, cpSchema.getRootElement(), false);
		getDocument().setRootElement(root);
		
		// Metadata Schema
		SchemaModel mdSchema = cpController.getMD_SchemaController().getSchemaModel();
		
		// Add Metadata Namespace
		Namespace mdNamespace = Namespace.getNamespace(MD_EditorHandler.IMSMD_NAMESPACE_PREFIX, mdSchema.getTargetNamespaceURI());
		root.addNamespaceDeclaration(mdNamespace);
		
		// Add XSI Schema Instance Namespace
		root.addNamespaceDeclaration(XMLUtils.XSI_Namespace);
		
		// Add Schema Location Attribute which is constructed from Target Namespace
		// and file name of Schema, and also with Metadata Schema location
		StringBuffer schemaLocationURI = new StringBuffer();
		schemaLocationURI.append(cpSchema.getTargetNamespaceURI());
		schemaLocationURI.append(" "); //$NON-NLS-1$
		schemaLocationURI.append(cpSchema.getSchemaName());
		schemaLocationURI.append(" "); //$NON-NLS-1$
		schemaLocationURI.append(mdSchema.getTargetNamespaceURI());
		schemaLocationURI.append(" "); //$NON-NLS-1$
		schemaLocationURI.append(mdSchema.getSchemaName());
		root.setAttribute(XMLUtils.XSI_SchemaLocation, schemaLocationURI.toString(), XMLUtils.XSI_Namespace);
		
		// Set as File
		setFile(new File(projectFolder, CP_Core.MANIFEST_NAME));
	}
	
    /**
     * Destroy this Document
     */
    public void destroy() {
        _cpCore.destroy();
        _cpCore = null;
    }

    /**
	 * @return the Comments to add to the XML Document
	 */
	public String[] getComments() {
		return cp_comments;
	}
	
	/**
	 * Import (aggregate) another Package into this Content Package at manifestElement
	 */
	public Element importManifest(File manifestFile, Element manifestElement, String base) throws FileNotFoundException, JDOMException, IOException {
		Element importedRootElement = null;
		
		// Read in imsmanifest.xml
		Document importedDoc = XMLUtils.readXMLFile(manifestFile);
		
		if(importedDoc != null) {
			// Root Element detach
			importedRootElement = importedDoc.getRootElement().detach();
			
			// Check we got the right kind of file
			String rootSource = importedRootElement.getName();
			if(!rootSource.equals(CP_Core.MANIFEST)) {
			    throw new FileNotFoundException(Messages.getString("uk.ac.reload.editor.contentpackaging.ContentPackage.0")); //$NON-NLS-1$
			}
			
			// Set Namespace to ours
			Namespace ns = importedRootElement.getNamespace();
			if(ns != null) XMLUtils.replaceNamespaces(importedRootElement, ns, getRootNamespace());
			
			// Remove stuff
			importedRootElement.removeAttribute(XMLUtils.XSI_SchemaLocation, XMLUtils.XSI_Namespace);
			importedRootElement.removeAttribute(XMLUtils.XSI_SchemaLocation, XMLUtils.XSI_NamespaceOLD);
			importedRootElement.removeNamespaceDeclaration(XMLUtils.XSI_Namespace);
			importedRootElement.removeNamespaceDeclaration(XMLUtils.XSI_NamespaceOLD);
			
			// Remove Namespaces
			Vector v = new Vector(); // Use a copy of the list so we don't get a concurrent modification exception
			Iterator it = importedRootElement.getAdditionalNamespaces().listIterator();
			while(it.hasNext()) {
				ns = (Namespace) it.next();
				v.add(ns);
			}
			it = v.iterator();
			while(it.hasNext()) {
				ns = (Namespace) it.next();
				importedRootElement.removeNamespaceDeclaration(ns);
				// Add to root
				Namespace ns2 = getRootElement().getNamespace(ns.getPrefix());
				if(ns2 == null) {
				    getRootElement().addNamespaceDeclaration(ns);
				}
			}
			
			// Create new IDENTIFIERs and map to old IDENTIFIERs
			Hashtable idmap = createNewIDs(importedRootElement);
			
			// Change each IDENTIFIERREF to new IDENTIFIERs
			createNewIDRefs(importedRootElement, idmap);
			
			// SCORM Prerequisites Refs
			createNewPrerequisiteRefs(importedRootElement, idmap);
			
			// Set Base attribute to target folder
			importedRootElement.setAttribute(CP_Core.BASE, base + "/", Namespace.XML_NAMESPACE); //$NON-NLS-1$
			
			// Add it
			addElementUndoable(this, manifestElement, importedRootElement, false);
		}
		
		return importedRootElement;
	}
	
	/**
	 * Export (disagggregate) manifestElement to new Content Package
	 * @param manifestElement
	 * @param targetFolder
	 * @return
	 * @throws IOException
	 */
	public boolean exportManifest(Element manifestElement, File targetFolder) throws IOException {
		// If sub-Manifest has valid xml:base property, find sub-Folder of that name in current package
		// If this sub-Folder exists and imsmanifest.xml exists, copy contents to new folder. Finish.
		String base = getElementBase(manifestElement);
		if(base != null) {
			File sourceFolder = new File(getProjectFolder(), base);
			if(sourceFolder.exists() && targetFolder.exists()) {
				// Do we have an imsmanifest.xml file?
				File imsmanifestFile = new File(sourceFolder, CP_Core.MANIFEST_NAME);
				if(imsmanifestFile.exists()) {
					FileUtils.copyFolder(sourceFolder, targetFolder);
					return true;
				}
			}
		}
		
		// Nope - so try this:-
		
		// Walk the tree to get all referenced files, and copy over to new Folder
		File[] files = getResourceFiles(manifestElement);
		for(int i = 0; i < files.length; i++) {
			String path = FileUtils.getRelativePath(getProjectFolder(), files[i]);
			File newFile = new File(targetFolder, path);
			newFile.getParentFile().mkdirs();
			FileUtils.copyFile(files[i], newFile);
		}
		
		// Create new imsmanifest.xml file from sub-Manifest node

		// New imsmanifest.xml Filename
		File newFile = new File(targetFolder, CP_Core.MANIFEST_NAME);
		
		// Dummy ContentPackage
		ContentPackage cp = new ContentPackage();
		cp.setDocument(new Document());
		cp.setFile(newFile);
		
		// Add comments first
		cp.addCommentsToDocument();
		
		// Clone the root
		Element newRoot = (Element)manifestElement.clone();
		cp.getDocument().setRootElement(newRoot);
		
		// Document root
		Element documentRoot = getDocument().getRootElement();

		// Document Root attribute declarations
		newRoot.setAttributes(null);
		Iterator it = documentRoot.getAttributes().iterator();
		while(it.hasNext()) {
		    Attribute att = (Attribute)it.next();
		    newRoot.setAttribute((Attribute)att.clone());
		}
		
		// Document Root Namespaces
		it = documentRoot.getAdditionalNamespaces().iterator();
		while(it.hasNext()) {
		    Namespace ns = (Namespace)it.next();
		    newRoot.addNamespaceDeclaration(ns);
		}

        // New Identifier for unique GUID
		newRoot.setAttribute(CP_Core.IDENTIFIER,  RandomGUID.getUniqueID("MANIFEST-")); //$NON-NLS-1$

		// Save it
		cp.saveDocument();
		
		// Copy the Schema Files to the target Folder

		// Set up filter for .xsd files
		FileFilter filter = new FileFilter() {
			public boolean accept(File file) {
				if(file.isDirectory()) {
				    return false;
				}
				return file.getName().toLowerCase().endsWith(".xsd"); //$NON-NLS-1$
			}
		};
		
		// Copy the Files over
		files = getProjectFolder().listFiles(filter);
		if(files != null) {
			for(int i = 0; i < files.length; i++) {
			    FileUtils.copyFile(files[i], new File(targetFolder, files[i].getName()));
			}
		}
		
		return true;
	}
	
	/**
	 * Save a copy of this Content Package to target folder with new GUIDs
	 * @param targetFolder The new target folder
	 * @param a Progress Monitor
	 * @return false if user cancelled
	 * @throws IOException
	 */
	public boolean saveDocumentAs(File targetFolder, IProgressMonitor monitor) throws IOException {
	    File targetManifest = new File(targetFolder, getFile().getName());
		
		boolean result = FileUtils.copyFolder(getProjectFolder(), targetFolder, monitor);
		if(!result) {
		    return false;
		}

		// Create new IDENTIFIERs and map to old IDENTIFIERs
		Hashtable idmap = createNewIDs(getRootElement());
		
		// Change each IDENTIFIERREF to new IDENTIFIERs
		createNewIDRefs(getRootElement(), idmap);
		
		// SCORM Prerequisites Refs
		createNewPrerequisiteRefs(getRootElement(), idmap);
		
		// Save
		setFile(targetManifest);
		saveDocument();
		
		return true;
	}
	
	/**
	 * @return the project name - the project folder
	 */
	public String getProjectName() {
		return getProjectFolder() == null ? null : getProjectFolder().getName();
	}
	
	/**
	 * @return the CP Project Folder which is the containing folder of imsmanifest.xml
	 */
	public File getProjectFolder() {
		return getFile() == null ? null : getFile().getParentFile();
	}
	
    /**
     * @param mdElement The CP's "metadata" Element to look in
     * @return the "lom" Metadata Element from the CP's given "metadata" Element or null if not found
     */
    public Element getMetadataLomElement(Element mdElement) {
        Element lomElement = null;
        
        // Get Root MD Namespace from CP Document root
        Namespace ns = getDocument().getRootElement().getNamespace("imsmd"); //$NON-NLS-1$
        
        // See if we have a "lom" Element with this Namespace
        if(ns != null) {
            lomElement = mdElement.getChild("lom", ns); //$NON-NLS-1$
            
            // Try 1.1 "record"
            if(lomElement == null) {
                lomElement = mdElement.getChild("record", ns); //$NON-NLS-1$
            }
        }

        // No Root MD Namespace, so try to find a "lom" child Element without using this Namespace
        if(lomElement == null) {
            Iterator i = mdElement.getChildren().iterator();
            while(i.hasNext()) {
                Element element = (Element) i.next();
                if(element.getName().equals("lom") || element.getName().equals("record")) { //$NON-NLS-1$ //$NON-NLS-2$
                    lomElement = element;
                    break;
                }
            }
        }

        return lomElement;
    }
	
//	==========================================================================
//	ELEMENT MOVING/COPYING/DRAGGING HANDLING
//	==========================================================================
	
	/**
	 * @return whether we can copy an element
	 */
	public boolean canCopyElement(Element element) {
		return false;
	}
	
	/**
	 * @return whether we can cut an element
	 */
	public boolean canCutElement(Element element) {
		return false;
	}
	
	/**
	 * @return whether we can paste an element from the Clipboard to element
	 */
	public boolean canPasteFromClipboard(Element element) {
		return false;
	}
	
	/**
	 * @return whether we can drag an element around
	 */
	public boolean canDragElement(Element element, int action) {
		if(element != null) {
			// Has to be our namespace
			if(!isDocumentNamespace(element)) {
			    return false;
			}
			
			String name = element.getName();
			if(name.equals(CP_Core.RESOURCE) ||
					name.equals(CP_Core.METADATA) ||
					name.equals(CP_Core.ITEM) ||
					name.equals(CP_Core.ORGANIZATION) ||
					name.equals(CP_Core.MANIFEST)) {
			    return true;
			}
		}
		return false;
	}
	
	/**
	 * @return whether we can copy/move sourceElement to targetElement
	 */
	public boolean acceptElement(Element sourceElement, Element targetElement, int actionCopyMove) {
		if(sourceElement != null && targetElement != null) {
			// Must be our Namespace
			if(!sourceElement.getNamespace().equals(targetElement.getNamespace())) {
			    return false;
			}
			
			// Can't copy/move onto self
			if(sourceElement.equals(targetElement)) {
			    return false;
			}
			
			// General Rules for Moving an Element
			if((actionCopyMove & XMLDocumentClipboard.ACTION_MOVE) != 0) {
				// Can't move if already there
				if(targetElement.getContent().contains(sourceElement)) {
				    return false;
				}
				// Can't move to a child
				if(targetElement.isAncestor(sourceElement)) {
				    return false;
				}
			}
			
			// Get Target & Source Parent Manifests
			Element targetManifest = getParentManifestElement(targetElement);
			Element sourceManifest = getParentManifestElement(sourceElement);
			
			if(targetManifest == null || sourceManifest == null) {
			    return false;
			}
			
			String sourceName = sourceElement.getName();
			String targetName = targetElement.getName();
			
			// Different Document?
			boolean differentDoc = !XMLUtils.isMemberOfSameDocument(sourceElement, targetElement);
			if(differentDoc) {
				// Only Manifests
				//return sourceName.equals(MANIFEST) && targetName.equals(MANIFEST);
				return false;
			}
			
			// Source == RESOURCE
			if(sourceName.equals(CP_Core.RESOURCE)) {
				if(targetName.equals(CP_Core.ORGANIZATION) || targetName.equals(CP_Core.ITEM)) {
					// Same manifest
					if(targetManifest == sourceManifest) return true;
					// Target must be higher than resource
					return sourceManifest.isAncestor(targetManifest);
				}
				// Dragging into RESOURCES Element
				else if(targetName.equals(CP_Core.RESOURCES)) {
					return !containsResource(sourceElement, targetElement);
				}
			}
			
			// Source == ITEM
			if(sourceName.equals(CP_Core.ITEM)) {
				if(targetName.equals(CP_Core.ORGANIZATION) || targetName.equals(CP_Core.ITEM)) {
					// Same manifest
					if(targetManifest == sourceManifest) return true;
					// If there is an identifierref, the referenced object's (parent) manifest must be below or same as the target's Parent Manifest
					Element refElement = getReferencedElement(sourceElement);
					if(refElement != null) {
						Element ref_targetManifest = getParentManifestElement(refElement);
						return ref_targetManifest.isAncestor(targetManifest) || (ref_targetManifest == targetManifest);
					}
					else {
					    return true;
					}
				}
			}
			
			// Source == MANIFEST
			else if(sourceName.equals(CP_Core.MANIFEST)) {
				if(targetName.equals(CP_Core.ORGANIZATION) || targetName.equals(CP_Core.ITEM)) {
					// Target must be higher than manifest
					return sourceManifest.isAncestor(targetManifest);
				}
				// Dragging into MANIFEST Element
				else if(targetName.equals(CP_Core.MANIFEST)) {
					return false;
				}
			}
			
			// Default
			else {
			    return isAllowedChild(targetElement, sourceElement);
			}
			
		}
		
		return false;
	}
	
	/**
	 * Copy/Move sourceElement to targetElement
	 * action is either ACTION_COPY or ACTION MOVE
	 */
	public Element shiftElement(Element sourceElement, Element targetElement, int action) {
		if(sourceElement != null && targetElement != null) {
			
			// Get Target & Source Parent Manifests
			Element targetManifest = getParentManifestElement(targetElement);
			Element sourceManifest = getParentManifestElement(sourceElement);
			
			if(targetManifest == null || sourceManifest == null) {
			    return null;
			}
			
			//boolean isSameDocument = XMLUtils.isMemberOfSameDocument(sourceElement, targetElement);
			
			// If Moving from another Manifest make sure we only copy it
			if(targetManifest != sourceManifest) {
			    action = XMLDocumentClipboard.ACTION_COPY;
			}
			
			String sourceName = sourceElement.getName();
			String targetName = targetElement.getName();
			
			// Copying/Moving a RESOURCE
			if(sourceName.equals(CP_Core.RESOURCE)) {
				if(targetName.equals(CP_Core.ORGANIZATION) || targetName.equals(CP_Core.ITEM)) {
					// Add ITEM that references this RESOURCE
					return addItemResource(targetElement, sourceElement, true);
				}
				else if(targetName.equals(CP_Core.RESOURCES)) {
					return shiftElement(sourceElement, targetElement, action, true);
				}
			}
			
			// Copying a MANIFEST
			else if(sourceName.equals(CP_Core.MANIFEST)) {
				if(targetName.equals(CP_Core.ORGANIZATION) || targetName.equals(CP_Core.ITEM)) {
					// Add ITEM that references this MANIFEST
					return addItemManifest(sourceElement, targetElement, true);
				}
				if(targetName.equals(CP_Core.MANIFEST)) {
					return shiftElement(sourceElement, targetElement, action, true);
				}
			}
			
			// Default
			else if(isAllowedChild(targetElement, sourceElement)) {
				return shiftElement(sourceElement, targetElement, action, true);
			}
		}
		
		return null;
	}
	
	protected Element shiftElement(Element sourceElement, Element targetElement, int action, boolean doSelect) {
		// Copy
		if((action & XMLDocumentClipboard.ACTION_COPY) != 0) {
			return copyElementUndoable(this, sourceElement, targetElement, doSelect);
		}
		// Move
		if((action & XMLDocumentClipboard.ACTION_MOVE) != 0) {
			return moveElementUndoable(this, sourceElement, targetElement, doSelect);
		}
		return null;
	}
	
	
	/**
	 * Over-ride this so we can intercept it to do stuff
	 */
	public Element addElementBySchema(Object source, Element parentElement, SchemaElement newSchemaElement, boolean doSelect) {
		Element newElement = super.addElementBySchema(source, parentElement, newSchemaElement, doSelect);
		
		if(newElement != null && isDocumentNamespace(newElement)) {
			String name = newElement.getName();
			
			if(name.equals(CP_Core.ORGANIZATION)) {
				// If user added first Organization, set it as default
				setDefaultOrganization(newElement);
				// Add Title Element
				addTitle(newElement, "Organization"); //$NON-NLS-1$

				// Add structure attribute
				addAttributeWithDefaultValue(source, newElement, CP_Core.STRUCTURE);
			}
			
			else if(name.equals(CP_Core.ITEM)) {
				// Add Title Element
				addTitle(newElement, "Item"); //$NON-NLS-1$
				
				// Add isvisible attribute
				addAttributeWithDefaultValue(source, newElement, CP_Core.ISVISIBLE);
			}
		}
		
		// Need to do this to update Tree
		fireElementChanged(new XMLDocumentListenerEvent(source, this, newElement, false));
		
		return newElement;
	}
	
	/**
	 * Over-ride this so we can intercept it to do stuff
	 */
	public Element copyElementUndoable(Object source, Element element, Element newParent, boolean doSelect) {
		Element newElement = super.copyElementUndoable(source, element, newParent, doSelect);
		copypasteElement(newElement);
		return newElement;
	}
	
	/**
	 * Over-ride this so we can intercept it to do stuff
	 */
	public Element pasteElementUndoable(Object source, Element element, Element newParent, boolean doSelect) {
		Element newElement = super.pasteElementUndoable(source, element, newParent, doSelect);
		copypasteElement(newElement);
		return newElement;
	}
	
	/**
	 * When an Element is copied over or pasted we need to do a bunch of stuff
	 */
	protected void copypasteElement(Element newElement) {
		if(newElement != null) {
			String name = newElement.getName();
			
			if(name.equals(CP_Core.ITEM) || name.equals(CP_Core.RESOURCE)) {
				// Create new IDs
				createNewIDs(newElement);
			}
			
			else if(name.equals(CP_Core.ORGANIZATION)) {
				// Create new IDs
				createNewIDs(newElement);
				// If user added first Organization, set it as default
				setDefaultOrganization(newElement);
				// Check valid IDENTIFIERREFs
				checkIDRefs(newElement);
			}
			
			else if(name.equals(CP_Core.MANIFEST)) {
				// Create new IDENTIFIERs and map to old IDENTIFIERs
				Hashtable map = createNewIDs(newElement);
				// Change each IDENTIFIERREF to new IDENTIFIERs
				createNewIDRefs(newElement, map);
			}
		}
	}
	
	/**
	 * Add an Item to Item or Organization parent - this references a sub-Manifest
	 */
	protected Element addItemManifest(Element manifestElement, Element targetElement, boolean doSelect) {
		// Add it
		XMLPath xmlPath = XMLPath.getXMLPathForElement(targetElement);
		xmlPath.appendElementName(CP_Core.ITEM);
		SchemaElement schemaElement = (SchemaElement)getSchemaController().getSchemaNode(xmlPath);
		Element itemElement = addElementBySchemaUndoable(this, targetElement, schemaElement, doSelect);
		
		// Set IDENTIFIERREF to MANIFEST's IDENTIFIER if specified
		if(itemElement != null) {
			String manifestID = manifestElement.getAttributeValue(CP_Core.IDENTIFIER);
			if(manifestID != null) {
			    itemElement.setAttribute(CP_Core.IDENTIFIERREF, manifestID);
			}
		}
		
		return itemElement;
	}
	
	/**
	 * Add an Item to Item or Organization parent - this references a Resource
	 * If resourcesElement is not null set the Item's identifierref to it
	 */
	protected Element addItemResource(Element parentElement, Element resourceElement, boolean doSelect) {
		// Add it
		XMLPath xmlPath = XMLPath.getXMLPathForElement(parentElement);
		xmlPath.appendElementName(CP_Core.ITEM);
		SchemaElement schemaElement = (SchemaElement)getSchemaController().getSchemaNode(xmlPath);
		Element itemElement = addElementBySchemaUndoable(this, parentElement, schemaElement, doSelect);
		
		if(itemElement != null && resourceElement != null) {
			// Set IDENTIFIERREF to resource's IDENTIFIER if specified
			String resourceID = resourceElement.getAttributeValue(CP_Core.IDENTIFIER);
			if(resourceID != null) {
			    itemElement.setAttribute(CP_Core.IDENTIFIERREF, resourceID);
			}
			// Add Title
			addItemTitle(itemElement, resourceElement);
		}
		
		return itemElement;
	}
	
	/**
	 * Add an Organization
	 */
	protected Element addOrganization(Element parentElement, boolean doSelect) {
		XMLPath xmlPath = XMLPath.getXMLPathForElement(parentElement);
		xmlPath.appendElementName(CP_Core.ORGANIZATION);
		SchemaElement schemaElement = (SchemaElement)getSchemaController().getSchemaNode(xmlPath);
		return addElementBySchemaUndoable(this, parentElement, schemaElement, doSelect);
	}
	
	/**
	 * Add a Title element (only one allowed).
	 * If a Title Element already exists the text will be replaced by new text
	 */
	protected Element addTitle(Element parentElement, String text) {
		Element titleElement = parentElement.getChild(CP_Core.TITLE, parentElement.getNamespace());
		
		if(titleElement != null) {
			titleElement.setText(text);
		}
		
		else {
			titleElement = new Element(CP_Core.TITLE, parentElement.getNamespace());
			titleElement.setText(text);
			addElement(this, parentElement, titleElement, false);
		}
		
		return titleElement;
	}
	
	/**
	 * Add a Title to an Item based on the Resource it References.
	 * If it's an html file use the <title> element from that or the file name
	 */
	protected Element addItemTitle(Element itemElement, Element resourceElement) {
		Element titleElement = null;
		
		if(itemElement != null && resourceElement != null) {
			String title = null;
			File file = getResourceFile(resourceElement);
			if(file != null) {
				String ext = FileUtils.getFileExtension(file);
				if(ext.equals("html") || ext.equals("htm")) { //$NON-NLS-1$ //$NON-NLS-2$
					try {
                        title = HTMLUtils.getTagText(file, "title"); //$NON-NLS-1$
                        if(title == null) {
                            title = FileUtils.getFileNameWithoutExtension(file);
                        }
                    } catch(IOException ex) {
                        ex.printStackTrace();
                        title = FileUtils.getFileNameWithoutExtension(file);
                    }
				}
				else {
				    title = FileUtils.getFileNameWithoutExtension(file);
				}
			}
			titleElement = addTitle(itemElement, title);
		}
		
		return titleElement;
	}
	
	/**
	 * @return a nice name to display for an Element depending on element type
	 */
	public String getElementDisplayName(Element element) {
		String fname = null;
		
		if(element != null) {
			String name = element.getName();
			
			// ITEM & ORGANIZATION - display Title Element
			if(name.equals(CP_Core.ITEM) || name.equals(CP_Core.ORGANIZATION)) {
				Element titleElement = element.getChild(CP_Core.TITLE, element.getNamespace());
				if(titleElement != null) {
				    fname = titleElement.getText();
				}
			}
			
			// DEPENDENCY - display HREF of referenced Resource
			else if(name.equals(CP_Core.DEPENDENCY)) {
				fname = getElementHREF(element);
			}
			
			// MANIFEST - display Identifier of referenced Resource
			else if(name.equals(CP_Core.MANIFEST)) {
				fname = element.getAttributeValue(CP_Core.IDENTIFIER);
			}
			
			// RESOURCE - display HREF if there is one, else use Identifier
			else if(name.equals(CP_Core.RESOURCE)) {
				fname = element.getAttributeValue(CP_Core.HREF);
				if(fname == null) {
				    fname = element.getAttributeValue(CP_Core.IDENTIFIER);
				}
			}
			
			// FILE - display HREF if there is one
			else if(name.equals(CP_Core.FILE)) {
				fname = element.getAttributeValue(CP_Core.HREF);
			}
			
			// MD SCHEMA & SCHEMAVERSION
			else if(name.equals(CP_Core.SCHEMA) || name.equals(CP_Core.SCHEMAVERSION)) {
				fname = element.getText();
			}
			
			// Get Friendly name if possible, or qualified name if not
			if(fname == null || fname.equals("")) { //$NON-NLS-1$
				fname = getSchemaController().getElementFriendlyName(XMLPath.getXMLPathForElement(element));
				if(fname == null) {
				    fname = element.getQualifiedName();
				}
			}
		}
		
		return fname;
	}
	
//	=============================================================================
//	Delegate Methods to ContentPackageCore class
//	=============================================================================
	
    /**
     * @param sourceElement
     * @return The Element referenced by identifierref in sourceElement.
     * The search digs downward from sourceElement - this is important.
     * This will be either a Resource or a sub-Manifest.
     */
	public Element getReferencedElement(Element sourceElement) {
		return _cpCore.getReferencedElement(sourceElement);
	}
	
    /**
     * @param element The element to test
     * @return True if element has an identifierref attribute
     */
	public boolean isReferencingElement(Element element) {
		return _cpCore.isReferencingElement(element);
	}
	
    /**
     * @param element
     * @return all available Identifiers from Resources and sub-Manifests
     * that an element can legally reference.
     * For an Item element this will be Resources and sub-manifests
     * For a Dependency element this will be Resources
     */
	public String[] getReferencedIdentifersAllowed(Element element) {
		return _cpCore.getReferencedIdentifersAllowed(element);
	}
	
    /**
     * @param element
     * @return all available Elements (Resources and sub-Manifests)
     * that an element can legally reference.
     * For an Item element this will be Resources and sub-manifests
     * For a Dependency element this will be Resources
     */
	public Element[] getReferencedElementsAllowed(Element element) {
		return _cpCore.getReferencedElementsAllowed(element);
	}
	
    /**
     * @param orgsElement
     * @return all ORGANIZATION elements in orgsElement
     */
	public Element[] getOrganizations(Element orgsElement) {
		return _cpCore.getOrganizations(orgsElement);
	}
	
    /**
     * @param orgsElement
     * @return all ORGANIZATION elements in an ORGANIZATIONS element that can be referenced
     * i.e they have IDENTIFIER attributes
     */
	public Element[] getOrganizationsAllowed(Element orgsElement) {
		return _cpCore.getOrganizationsAllowed(orgsElement);
	}
	
    /**
     * @param orgsElement
     * @return the default Organization for an ORGANIZATIONS element
     * If there is a DEFAULT ref in the ORGANIZATIONS element will return that
     * otherwise return first ORGANIZATION Element if found
     */
	public Element getDefaultOrganization(Element orgsElement) {
		return _cpCore.getDefaultOrganization(orgsElement);
	}
	
    /**
     * @param element
     * @return the local parent "manifest" Element for a given Element
     * If element is the manifest, will return that
     */
	public Element getParentManifestElement(Element element) {
		return _cpCore.getParentManifestElement(element);
	}
	
    /**
     * @param manifestElement
     * @param elementName Name of element
     * @param ns Namespace
     * @return all sub-Elements of type elementName from a given parent Manifest
     * This does a deep recursive search.
     */
	public Element[] getElementsInManifest(Element manifestElement, String elementName, Namespace ns) {
		return _cpCore.getElementsInManifest(manifestElement, elementName, ns);
	}
	
    /**
     * @param parent
     * @param identifier
     * @return an Element by its IDENTIFIER attribute starting at parent element
     * This will do a deep recursive search from parent Element
     */
	public Element getElementByIdentifier(Element parent, String identifier) {
		return _cpCore.getElementByIdentifier(parent, identifier);
	}
	
    /**
     * @param name
     * @return True if name is the "lom" element name
     */
	public boolean isMetadataRoot(String name) {
		return _cpCore.isMetadataRoot(name);
	}
	
    /**
     * @param name
     * @return True if name is the "metadata" element
     */
	public boolean isMetadataElement(String name) {
		return _cpCore.isMetadataElement(name);
	}
	
    /**
     * @param element
     * @return The Relative URL string that an Element references
     */
	public String getRelativeURL(Element element) {
		return _cpCore.getRelativeURL(element);
	}

    /**
     * @param element
     * @return The Absolute URL string that an Element references
     */
	public String getAbsoluteURL(Element element) {
		return _cpCore.getAbsoluteURL(element);
	}
	
    /**
     * @param element
     * @return An actual HREF reference from an Element's HREF taking into account xml:base
     */
	public String getElementHREF(Element element) {
		return _cpCore.getElementHREF(element);
	}
	
    /**
     * @param element
     * @return An actual BASE reference for an Element taking into account xml:base
     */
	public String getElementBase(Element element) {
		return _cpCore.getElementBase(element);
	}
	
    /**
     * @param element
     * @return the parameters attribute of an element
     */
	public String getParameters(Element element) {
		return _cpCore.getParameters(element);
	}
	
    /**
     * @param element
     * @return An actual File reference that an Element references.
     * If it's not a local file or does not exist, return null
     */
	public File getResourceFile(Element element) {
		return _cpCore.getResourceFile(element);
	}
	
    /**
     * @param manifestElement
     * @return All Existing Local Files for a given Manifest Element taking into account xml:base
     * External URLS are not included and the file has to physically exist
     */
	public File[] getResourceFiles(Element manifestElement) {
		return _cpCore.getResourceFiles(manifestElement);
	}
	
    /**
     * @param href
     * @param resourcesElement
     * @return a Resource Element given its HREF - the first one found
     */
	public Element getResourceElementByHREF(String href, Element resourcesElement) {
		return _cpCore.getResourceElementByHREF(href, resourcesElement);
	}
	
    /**
     * @param href
     * @param resourceElement
     * @return a File Element given its HREF - the first one found
     */
	public Element getFileElementByHREF(String href, Element resourceElement) {
		return _cpCore.getFileElementByHREF(href, resourceElement);
	}
	
    /**
     * Find the local "resources" Element for a given Element position.  This is because there may
     * be more than one with sub-manifests
     * @param element The starting element position
     * @return The element if found, else null
     */
	public Element getResourcesElement(Element element) {
		return _cpCore.getResourcesElement(element);
	}
	
	//	==============================================================================
	//	UTILS
	//	==============================================================================
	
	/**
	 * Create a new ID for an Element that has an IDENTIFIER attribute and also for its children
	 * Only works if the Element already has an IDENTIFIER attribute
	 * @return a Hashtable of old IDs mapped to Elements
	 */
	protected Hashtable createNewIDs(Element element) {
		Hashtable idmap = new Hashtable();
		createNewIDs(element, idmap);
		return idmap;
	}
	
	/**
	 * Store the old identifier attribute in a map and set the element to a new one
	 * 
	 * @param element
	 * @param idmap
	 */
	protected void createNewIDs(Element element, Hashtable idmap) {
		if(isRemappableIDElement(element)) {
			String oldID = element.getAttributeValue(CP_Core.IDENTIFIER);
			if(oldID != null) {
				String newID = generateUniqueID(element);
				idmap.put(oldID, element);
				element.setAttribute(CP_Core.IDENTIFIER, newID);
			}
		}

		Iterator it = element.getChildren().iterator();
		while(it.hasNext()) {
		    Element child = (Element) it.next();
		    createNewIDs(child, idmap);
		}
	}
	
	/**
	 * @param element
	 * @return True if element is an element that needs to have its identifer attribute remapped when we do a 
	 * "Save As..."  For Content Packages these are:

	 * manifest
	 * organization
	 * item
	 * resource
	 */
	protected boolean isRemappableIDElement(Element element) {
	    if(element == null) {
	        return false;
	    }
	    
	    String name = element.getName();
	    if(name.equals(CP_Core.MANIFEST) || name.equals(CP_Core.ORGANIZATION)
	            || name.equals(CP_Core.ITEM) || name.equals(CP_Core.RESOURCE)) {
	        return true;
	    }
	    
	    return false;
	}
	
	/**
	 * Create new IDENTIFIERREFs and DEFAULT attributes
	 * @param element The start Element
	 * @param idmap Hash Map of old identifiers mapped to Elements
	 */
	protected void createNewIDRefs(Element element, Hashtable idmap) {
	    String name = element.getName();
	    
	    String attrib;
	    
	    if(name.equals(CP_Core.ORGANIZATIONS)) {
	        attrib = CP_Core.DEFAULT;
	    }
	    else {
	        attrib = CP_Core.IDENTIFIERREF;
	    }
	    
	    String idref = element.getAttributeValue(attrib);
	    if(idref != null && !idref.equals("")) { //$NON-NLS-1$
	        Element ref_element = (Element) idmap.get(idref);
	        if(ref_element != null) {
	            String newID = ref_element.getAttributeValue(CP_Core.IDENTIFIER);
	            if(newID != null) {
	                element.setAttribute(attrib, newID);
	            }
	        }
	    }
	    
	    // Children
	    Iterator it = element.getChildren().iterator();
	    while(it.hasNext()) {
	        Element child = (Element) it.next();
	        createNewIDRefs(child, idmap);
	    }
	}
	
	/**
	 * Create new SCORM Prerequisite Refs
	 * Really this method should be in the SCORM12_Package class
	 */
	protected void createNewPrerequisiteRefs(Element element, Hashtable id_map) {
		if(element != null && isDocumentNamespace(element)) {
			// SCORM <prerequisite> elements
			Element prereq = element.getChild(SCORM12_Core.PREREQUISITES, CP_EditorHandler.ADLCP_NAMESPACE_12);
			if(prereq != null) {
				String script = prereq.getText();
				if(script != null && !script.equals("")) { //$NON-NLS-1$
					// Parse script String for this
					Enumeration e = id_map.keys();
					while(e.hasMoreElements()) {
						String oldID = (String)e.nextElement();
						int index = script.indexOf(oldID);
						if(index != -1) {
							Element ref_element = (Element) id_map.get(oldID);
							if(ref_element != null) {
								String newID = ref_element.getAttributeValue(CP_Core.IDENTIFIER);
								if(newID != null) {
								    script = script.replaceAll(oldID, newID);
								}
							}
						}
					}
					prereq.setText(script);
				}
			}
			
			// Children
			Iterator it = element.getChildren().iterator();
			while(it.hasNext()) {
				Element child = (Element) it.next();
				createNewPrerequisiteRefs(child, id_map);
			}
		}
	}
	
	/**
	 * If we move or copy an Element that contains an IDENTIFIERREF to another Element
	 * we check that the Element referenced is still below us. If it is then OK, if not the
	 * reference is invalid so we remove it - also for its children
	 */
	protected void checkIDRefs(Element element) {
		if(element != null && isDocumentNamespace(element)) {
			Element ref_element = getReferencedElement(element);
			// Null means it's not found and therefore below us
			if(ref_element == null) {
				Attribute att = element.getAttribute(CP_Core.IDENTIFIERREF);
				if(att != null) {
				    element.removeAttribute(att);
				}
			}
			Iterator it = element.getChildren().iterator();
			while(it.hasNext()) {
				Element child = (Element) it.next();
				checkIDRefs(child);
			}
		}
	}
	
	/**
	 * Generate a Unique Identifier for an Element
	 */
	public String generateUniqueID(Element element){
		String prefix;
		
		String elementName = element.getName();
		
		if(elementName.equals(CP_Core.MANIFEST)) {
		    prefix = "MANIFEST-"; //$NON-NLS-1$
		}
		else if(elementName.equals(CP_Core.ORGANIZATION)) {
		    prefix = "ORG-"; //$NON-NLS-1$
		}
		else if(elementName.equals(CP_Core.ITEM)) {
		    prefix = "ITEM-"; //$NON-NLS-1$
		}
		else if(elementName.equals(CP_Core.RESOURCE)) {
		    prefix = "RES-"; //$NON-NLS-1$
		}
		else {
		    prefix = "RLD-"; //$NON-NLS-1$
		}
		
		return RandomGUID.getUniqueID(prefix);
	}
	
	/**
	 * When an Organization element is added/copied to an Organizations Element
	 * and it is the only one, set the Organizations "default" attribute to it
	 */
	protected void setDefaultOrganization(Element organization) {
		Element orgs = organization.getParent();
		if(orgs.getChildren(CP_Core.ORGANIZATION, orgs.getNamespace()).size() == 1) {
			String id = organization.getAttributeValue(CP_Core.IDENTIFIER);
			if(id != null) {
			    orgs.setAttribute(CP_Core.DEFAULT, id);
			}
		}
	}
	
	
	
//	=============================================================================
//	RESOURCE HANDLING
//	=============================================================================
	
	/**
	 * Add a bunch of CP_Resources to the Manifest
	 * @return the last added Element
	 */
	public Element addCP_Resources(CP_Resource[] reloadResources, Element parentElement) {
		Element element = null;
		
		for(int i = 0; i < reloadResources.length; i++) {
			element = addCP_Resource(reloadResources[i], parentElement);
		}
		
		return element;
	}
	
	/**
	 * Add a CP_Resource to the Manifest
	 * @return the last added Element
	 */
	public Element addCP_Resource(CP_Resource reloadResource, Element parentElement) {
		// We are adding a Folder of Resources
		if(reloadResource.isDirectory()) {
			
			String name = parentElement.getName();
			
			// A folder added to ORGANIZATIONS Element so create new Organization
			if(name.equals(CP_Core.ORGANIZATIONS)) {
				// Add new Organization
				parentElement = addOrganization(parentElement, false);
				// Add Title Element - the name of the folder - only here
				addTitle(parentElement, reloadResource.getName());
			}
			
			// A folder added to ORGANIZATION or ITEM
			else if(name.equals(CP_Core.ORGANIZATION) || name.equals(CP_Core.ITEM)) {
				// Add new empty Item
				parentElement = addItemResource(parentElement, null, false);
				// Add Title Element - the name of the folder - only here
				addTitle(parentElement, reloadResource.getName());
			}
			
			// Recurse for all children
			CP_Resource[] resources = reloadResource.getChildren();
			if(resources != null) {
				for(int i = 0; i < resources.length; i++) {
					addCP_Resource(resources[i], parentElement);
				}
			}
			
			return parentElement;
		}
		
		// A Single Resource
		else return addCP_Resource(reloadResource, parentElement, false);
	}
	
	/**
	 * Add a CP_Resource to a given Element
	 */
	protected Element addCP_Resource(CP_Resource reloadResource, Element parentElement, boolean doSelect) {
		String name = parentElement.getName();
		
		// If we are adding the CP_Resource to a <resource> element then add it as a <file> element
		if(name.equals(CP_Core.RESOURCE)) {
			// Do we already have the File in our Manifest?
			Element fileElement = getFileByCP_Resource(reloadResource, parentElement);
			if(fileElement == null) {
				fileElement = addFileElement(reloadResource, parentElement, doSelect);
			}
			return fileElement;
		}
		
		// ----------  Add to <resources> element -------------------
		
		// Do we already have the Resource in our Manifest?
		Element resourcesElement = getResourcesElement(parentElement);
		Element resourceElement = getResourceByCP_Resource(reloadResource, resourcesElement);
		
		// Nope - so add to local Resources Element
		if(resourceElement == null) {
			resourceElement = addCP_ResourceToResourcesElement(reloadResource, resourcesElement, doSelect);
		}
		
		// Once added to <resources> element we can add it to the <organization> or <item>
		// as a reference to the <resource>
		
		// We have added it to Organization or Item Element
		if(name.equals(CP_Core.ORGANIZATION) || name.equals(CP_Core.ITEM)) {
			// Add Item that references the Resource
			return addItemResource(parentElement, resourceElement, doSelect);
		}
		
		return resourceElement;
	}
	
	/**
	 * Add a Resource to the "resources" element
	 */
	protected Element addCP_ResourceToResourcesElement(CP_Resource reloadResource, Element resourcesElement, boolean doSelect) {
		XMLPath xmlPath = XMLPath.getXMLPathForElement(resourcesElement);
		xmlPath.appendElementName(CP_Core.RESOURCE);
		SchemaElement schemaElement = (SchemaElement)getSchemaController().getSchemaNode(xmlPath);
		
		Element resourceElement = addElementBySchema(this, resourcesElement, schemaElement, doSelect);
		
		if(resourceElement != null) {
			// HREF
			String href = FileUtils.getRelativePath(getProjectFolder(), reloadResource);
			href = href.replaceAll(" ", "%20"); //$NON-NLS-1$ //$NON-NLS-2$
			resourceElement.setAttribute(CP_Core.HREF, href);
			
			// Add sub-Files
			addFileElements(reloadResource, resourceElement, doSelect);
			
			// Undo Event
			if(getUndoHandler() != null) {
				UndoableAddAction addAction = new UndoableAddAction(resourcesElement, resourceElement);
				getUndoHandler().addUndoableAction(addAction);
			}
			
		}
		
		return resourceElement;
	}
	
	/**
	 * @return a Resource in reference to a CP_Resource
	 */
	protected Element getResourceByCP_Resource(CP_Resource reloadResource, Element resourcesElement) {
		// Get a Relative Path
		String href = FileUtils.getRelativePath(getProjectFolder(), reloadResource);
		return getResourceElementByHREF(href, resourcesElement);
	}
	
	/**
	 * @return a File in reference to a CP_Resource
	 */
	protected Element getFileByCP_Resource(CP_Resource reloadResource, Element resourceElement) {
		// Get a Relative Path
		String href = FileUtils.getRelativePath(getProjectFolder(), reloadResource);
		return getFileElementByHREF(href, resourceElement);
	}
	
	/**
	 * Return whether resourcesElement already contains sourceElement as a Resource
	 * This searches by the HREF attribute
	 */
	public boolean containsResource(Element sourceElement, Element resourcesElement) {
		if(sourceElement != null && resourcesElement != null) {
			if(sourceElement.getName().equals(CP_Core.RESOURCE) && resourcesElement.getName().equals(CP_Core.RESOURCES)) {
				String href = sourceElement.getAttributeValue(CP_Core.HREF);
				if(href != null) {
				    return containsResource(href, resourcesElement);
				}
			}
		}
		return false;
	}
	
	/**
	 * Return whether a Resources Element contains a Resource by HREF
	 */
	protected boolean containsResource(String href, Element resourcesElement) {
		Element element = getResourceElementByHREF(href, resourcesElement);
		return element != null;
	}
	
	/**
	 * Return whether a <resource> Element contains a <file> element by HREF
	 */
	protected boolean containsFile(String href, Element resourceElement) {
	    Element element = _cpCore.getFileElementByHREF(href, resourceElement);
		return element != null;
	}

	/**
	 * Return whether targetElement can accept CP_Resources
	 */
	public boolean acceptsCP_Resources(Element targetElement) {
		// Correct Namespace?
		if(!targetElement.getNamespace().equals(getTargetNamespace())) {
		    return false;
		}
		
		String name = targetElement.getName();
		
		if(name.equals(CP_Core.MANIFEST) ||
				name.equals(CP_Core.RESOURCES) ||
				name.equals(CP_Core.RESOURCE) ||
				name.equals(CP_Core.ORGANIZATION) ||
				name.equals(CP_Core.ITEM) ||
				name.equals(CP_Core.ORGANIZATIONS)) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * Add <file> elements for a CP_Resource. Note - this is NOT an UNDOABLE EVENT

	 * @param cpResource The CP_Resource to add
	 * @param resourceElement The <resource> element to add to
	 * @param doSelect A hint as to whether a GUI should select the element
	 */
	protected void addFileElements(CP_Resource cpResource, Element resourceElement, boolean doSelect) {
		// Add the resource itself as a <file> element
		XMLPath xmlPath = XMLPath.getXMLPathForElement(resourceElement);
		xmlPath.appendElementName(CP_Core.FILE);
		SchemaElement fileSchemaElement = (SchemaElement)getSchemaController().getSchemaNode(xmlPath);
		Element fileElement = addElementBySchema(this, resourceElement, fileSchemaElement, doSelect);
		
		// Now add the href
		if(fileElement != null) {
			String href = FileUtils.getRelativePath(getProjectFolder(), cpResource);
			href = href.replaceAll(" ", "%20"); //$NON-NLS-1$ //$NON-NLS-2$
			fileElement.setAttribute(CP_Core.HREF, href);
		}
		
		// Add the other <file> elements if this is a Parseable resource file
		if(FileType.isParseableFile(cpResource)) {
		    HTMLParser parser = new HTMLParser(cpResource, getProjectFolder());
			File[] files = parser.getAllLinks();
			for(int i = 0; i < files.length; i++) {
			    String href = FileUtils.getRelativePath(getProjectFolder(), files[i]);
			    href = href.replaceAll(" ", "%20"); //$NON-NLS-1$ //$NON-NLS-2$
			    // Make sure we don't already have it
			    if(!containsFile(href, resourceElement)) {
			        fileElement = addElementBySchema(this, resourceElement, fileSchemaElement, doSelect);
			        fileElement.setAttribute(CP_Core.HREF, href);
			    }
			}
		}
	}
	
	/**
	 * Add a <file> element to a <resource> element from a CP_Resource. Note - this IS an UNDOABLE EVENT
	 * @return the added <file> element
	 */
	protected Element addFileElement(File reloadResource, Element resourceElement, boolean doSelect) {
		// Add it
		XMLPath xmlPath = XMLPath.getXMLPathForElement(resourceElement);
		xmlPath.appendElementName(CP_Core.FILE);
		SchemaElement fileSchemaElement = (SchemaElement)getSchemaController().getSchemaNode(xmlPath);
		Element fileElement = addElementBySchemaUndoable(this, resourceElement, fileSchemaElement, doSelect);
		
		// Now add the href
		if(fileElement != null) {
			String href = FileUtils.getRelativePath(getProjectFolder(), reloadResource);
			href = href.replaceAll(" ", "%20"); //$NON-NLS-1$ //$NON-NLS-2$
			fileElement.setAttribute(CP_Core.HREF, href);
			// Seems as if we need to notify to repaint the tree
			fireElementChanged(new XMLDocumentListenerEvent(this, this, fileElement, false));
		}
		
		return fileElement;
	}
}
