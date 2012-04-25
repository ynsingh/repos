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

package uk.ac.reload.moonunit;

import java.util.List;
import java.util.StringTokenizer;

import org.jdom.Attribute;
import org.jdom.Comment;
import org.jdom.Element;
import org.jdom.Namespace;

import uk.ac.reload.diva.undo.UndoHandler;
import uk.ac.reload.diva.undo.UndoableAction;
import uk.ac.reload.diva.util.RandomGUID;
import uk.ac.reload.jdom.XMLActiveDocument;
import uk.ac.reload.jdom.XMLPath;
import uk.ac.reload.moonunit.schema.SchemaAttribute;
import uk.ac.reload.moonunit.schema.SchemaElement;
import uk.ac.reload.moonunit.schema.SchemaModel;

/**
 * This encapsulates the JDOM XML Document file and the SchemaController associated
 * with it plus helper methods and Undo/Redo support.
 * 
 * Implementors of a specification will sub-class this.
 *
 * @author Phillip Beauvoir
 * @version $Id: SchemaDocument.java,v 1.1 1998/03/25 20:28:22 ynsingh Exp $
 */
public abstract class SchemaDocument
extends XMLActiveDocument
{
    /**
     * The Schema that will be our template
     */
    private SchemaController _schemaController;

    /**
     * The Undo Handler
     */
    private UndoHandler _undoHandler;

    /**
     * Default Constructor
     */
    public SchemaDocument() {
        super();
    }

    /**
     * Set the SchemaController
     * @param schemaController
     */
    public void setSchemaController(SchemaController schemaController) {
        _schemaController = schemaController;
    }

    /**
     * Get the SchemaController for this Document
     * @return The SchemaController
     */
    public SchemaController getSchemaController() {
        return _schemaController;
    }

    /**
     * @return the Root Namespace of the Document,
     * or the Schema Target Namespace if there isn't one set in the Document
     */
    public Namespace getRootNamespace() {
        // Use Document's if we have one
        Namespace ns = super.getRootNamespace();
        if(ns != null) {
            return ns;
        }
        // Otherwise, from Target Schema
        else {
            return getTargetNamespace();
        }
    }

    /**
     * @return The Target Schema Namespace of the Document
     */
    public Namespace getTargetNamespace() {
        SchemaModel schemaModel = _schemaController.getSchemaModel();
        return Namespace.getNamespace(schemaModel.getTargetNamespaceURI());
    }

    /**
     * @return The *first* found Element for the given SchemaElement or null if not found
     */
    public Element getElement(SchemaElement schemaElement) {
        if(schemaElement == null) {
            return null;
        }
        
        SchemaElement[] schemaElements = schemaElement.getSchemaElements();

        // Get to the Root
        Element element = getDocument().getRootElement();
        
        // Root element
        if(schemaElements.length <= 1) {
            return element;
        }
        
        // Iterate thru, ignoring root Element
        for(int i = 1; i < schemaElements.length; i++) {
            // Get Child Element
            element = element.getChild(schemaElements[i].getName(), schemaElements[i].getNamespace());
            // There is no child
            if(element == null) {
                return null;
            }
        }
        
        return element;
    }

    /**
     * @return All Elements for the given SchemaElement Path or null if not found
     */
    public Element[] getElements(SchemaElement schemaElement) {
        if(schemaElement == null) {
            return null;
        }
        
        Element element = getElement(schemaElement);

        if(element == null) {
            return null;
        }
        
        Element parent = element.getParent();
        
        // Must be root element
        if(parent == null) {
        	return new Element[] { element };
        }
        
        List list = element.getParent().getChildren(element.getName(), element.getNamespace());
        Element[] elements = new Element[list.size()];
        if(!list.isEmpty()) {
            list.toArray(elements);
        }

        return elements;
    }

	/**
     * @return the Comments to add to the XML Document
     * Implementors must over-ride this if they want comments added to the XML Document
     */
    public String[] getComments() {
        return null;
    }

    /**
     * Add the signature comments to the XML Document - taken from getComments()
     */
    protected void addCommentsToDocument() {
        String[] comments = getComments();
        if(comments != null) {
            for(int i = 0; i < comments.length; i++) {
                Comment comment = new Comment(comments[i]);
                getDocument().addContent(comment);
            }
        }
    }

    /**
     * Destroy this Document
     */
    public void destroy() {
        super.destroy();
        _schemaController = null;
        _undoHandler = null;
    }

    // =============================================================================
    // ========================== ELEMENT OPERATIONS ===============================
    // =============================================================================

    /**
     * Add a new single unique Element given a and register an Undo event
     * All neccessary parent elements will be created.
     * If an Element of that type already exists, the first one found is returned instead.
     * @param source Who is adding it
     * @param newSchemaElement A full SchemaElement going back to the root
     * @param doSelect A hint to any registered listeners that they might want to select the Element
     * @return the added Element or the first one found if already present
     */
    public Element addElementUniqueBySchema(Object source, SchemaElement newSchemaElement, boolean doSelect) {
        if(newSchemaElement == null) {
            return null;
        }

        // Get the SchemaElement components
        SchemaElement[] schemaElements = newSchemaElement.getSchemaElements();
        
        // Root is already added
        if(schemaElements.length < 2) {
            return getDocument().getRootElement();
        }
        
        // Get to the Root
        Element element = getDocument().getRootElement();
        
        // Iterate thru, ignoring root Element
        for(int i = 1; i < schemaElements.length; i++) {
            // Get Child Element
            Element childElement = element.getChild(schemaElements[i].getName(), schemaElements[i].getNamespace());
            // There is no child, so create one
            if(childElement == null) {
                childElement = addElementBySchema(source, element, schemaElements[i], doSelect);
            }
            element = childElement;
        }
        
        return element;
    }

    /**
     * Add a new single unique Element given a SchemaElement with an undoable action.
     * All neccessary parent elements will be created.
     * If an Element of that type already exists, the first one found is returned instead.
     * @param source Who is adding it
     * @param newSchemaElement A full SchemaElement going back to the root
     * @param doSelect A hint to any registered listeners that they might want to select the Element
     * @return the added Element or the first one found if already present
     */
    public Element addElementUniqueBySchemaUndoable(Object source, SchemaElement newSchemaElement, boolean doSelect) {
        if(newSchemaElement == null) {
            return null;
        }

        // Get the SchemaElement components
        SchemaElement[] schemaElements = newSchemaElement.getSchemaElements();
        
        // Root is already added
        if(schemaElements.length < 2) {
            return getDocument().getRootElement();
        }
        
        // Get to the Root
        Element element = getDocument().getRootElement();
        
        // Iterate thru, ignoring root Element
        for(int i = 1; i < schemaElements.length; i++) {
            // Get Child Element
            Element childElement = element.getChild(schemaElements[i].getName(), schemaElements[i].getNamespace());
            // There is no child, so create one
            if(childElement == null) {
                childElement = addElementBySchemaUndoable(source, element, schemaElements[i], doSelect);
            }
            element = childElement;
        }
        
        return element;
    }
        
    /**
     * Add a new Element to the parent Element based on SchemaElement and register an Undo event
     * Create new JDOM Element based on childSchemaElement
     * Insert into the correct position!
     * Set any defaults of the Element
     * Add any default child Elements if needed
     * 
     * @param source Who is telling us this
     * @param parentElement The parent Element to add to
     * @param childSchemaElement The Type of Element child we want to add
     * @param doSelect A hint to any registered listeners that they might want to select the Element
     * @return The Element added
     */
    public Element addElementBySchemaUndoable(Object source, Element parentElement, SchemaElement childSchemaElement, boolean doSelect) {
        if(childSchemaElement == null) {
            return null;
        }
        
        Element newElement = addElementBySchema(source, parentElement, childSchemaElement, doSelect);

        // Get Undoable Action after adding
        if(_undoHandler != null && newElement != null) {
            UndoableAddAction addAction = new UndoableAddAction(parentElement, newElement);
            _undoHandler.addUndoableAction(addAction);
        }

        return newElement;
    }


    /**
     * Add a new Element to the parent Element based on SchemaElement
     * Create new JDOM Element based on childSchemaElement
     * Insert into the correct position!
     * Set any defaults of the Element
     * Add any default child Elements if needed
     * 
     * @param source Who is telling us this
     * @param parentElement The parent Element to add to
     * @param childSchemaElement The Type of Element we want to add
     * @param doSelect A hint to any registered listeners that they might want to select the Element
     * @return The Element added
     */
    public Element addElementBySchema(Object source, Element parentElement, SchemaElement childSchemaElement, boolean doSelect) {
        if(childSchemaElement == null) {
            return null;
        }

        // Get the Namespace
        Namespace ns;
        // If no parent use Document Namespace or given Namespace
        if(parentElement == null) {
            ns = getRootNamespace();
        }
        // Else use schema namespace
        else {
            ns = childSchemaElement.getNamespace();
        }

        // Create Element
        Element newElement = new Element(childSchemaElement.getName(), ns);

        // If we have a default value, add it
        String defaultValue = getSchemaController().getDefaultValue(childSchemaElement);
        if(defaultValue != null)  {
            newElement.setText(defaultValue);
        }

        // Add any required Attributes.
        // They will be added as per Schema but their value may well be empty ""
        // So it's up to the super class to over-ride this method and andd any values
        if(childSchemaElement.hasSchemaAttributes()) {
            SchemaAttribute[] atts = childSchemaElement.getSchemaAttributes();
            
            for(int i = 0; i < atts.length; i++) {
                if("required".equals(atts[i].getUse())) {
                    Attribute att = atts[i].createAttribute();
                    
                    // If an identifier then generate one
                    if(att.getName().equals("identifier")) {
                        att.setValue(generateUniqueID(newElement));
                    }
                    // Is there a default value?
                    else {
                        String attValue = getSchemaController().getDefaultValue(atts[i]);
                        if(attValue == null) {
                            attValue = "";
                        }
                        att.setValue(attValue);
                    }
                    
                    newElement.setAttribute(att);
                }
            }
        }

        // Add to parent at correct position
        // If parent is null, that's OK it's probably the Document
        if(parentElement != null) {
            int index = getInsertPositionOfElement(parentElement, childSchemaElement);
            addElementAtIndex(source, parentElement, newElement, index, doSelect);
        }

        // Add any mandatory children that have a MinOccurs of one and are not part of a choice grouping
        SchemaElement[] schemaChildren = childSchemaElement.getChildren();
        for(int i = 0; i < schemaChildren.length; i++) {
            SchemaElement child = schemaChildren[i];
            if(child.getMinOccurs() == 1 && !child.isChoiceElement()) {
                addElementBySchema(source, newElement, child, doSelect);
            }
        }

        return newElement;
    }

	/**
	 * Add a Child Element given its Parent Element and XML offset path for the child.
	 * The XMLPath can consist of more than one element - element1/element2/element3 and is used as the
	 * child pattern from the parent element.
	 * If an Element already exists that matches the pattern then that will be returned.
	 * 
     * @param source Who is telling us this
	 * @param parentElement The known existing ancestor parent element.
	 * @param xmlChildPath The XML path fragment required to construct the child Element.
     * @param doSelect A hint to any registered listeners that they might want to select the Element once it has been added.
	 * @return The first Element that matches parentElement + xmlChildPath, creating it if need be.
	 */
    public Element addElementByXMLPath(Object source, Element parentElement, XMLPath xmlChildPath, boolean doSelect) {
        Element element = null;
        Namespace ns = null;
        XMLPath xmlElementPath = XMLPath.getXMLPathForElement(parentElement);
        
        StringTokenizer t = xmlChildPath.getElements();
        while(t.hasMoreElements()) {
            String path = t.nextToken();
            xmlElementPath.appendElementName(path);
            
            // Check for Namespace prefix
            int idx = path.indexOf(':');
            if(idx >= 0) {
                String prefix = path.substring(0, idx);
                path = path.substring(idx + 1);
                ns = parentElement.getNamespace(prefix);
            }
            else {
                ns = parentElement.getNamespace();
            }
            
            // Do we have it already?
            element = parentElement.getChild(path, ns);
            // Create new Element
            if(element == null) {
                SchemaElement schemaElement = (SchemaElement)getSchemaController().getSchemaNode(xmlElementPath);
                element = addElementBySchema(source, parentElement, schemaElement, doSelect);
            }
            parentElement = element;
        }
        
        return element;
    }

	/**
     * Just add a new Element that can be undone.  Listeners will only be notified once. Undo Event will be fired.
     * 
     * @param source Who is telling us this
     * @param parentElement The parent Element to add to
     * @param childElement The child Element to add
     * @param doSelect A hint to any registered listeners that they might want to select the Element
     * @return The Element added
     */
    public Element addElementUndoable(Object source, Element parentElement, Element childElement, boolean doSelect) {
        Element newElement = addElement(source, parentElement, childElement, doSelect);

        // Get Undoable Action after adding
        if(_undoHandler != null && newElement != null) {
            UndoableAddAction addAction = new UndoableAddAction(parentElement, newElement);
            _undoHandler.addUndoableAction(addAction);
        }

        return newElement;
    }

    /**
     * Just add a new Element.  Listeners will only be notified once.
     * If the new Element has children it's up to the listener to handle them
     * 
     * @param source Who is telling us this
     * @param parentElement The parent Element to add to
     * @param childElement The child Element to add
     * @param doSelect A hint to any registered listeners that they might want to select the Element
     * @return The Element added
     */
    public Element addElement(Object source, Element parentElement, Element childElement, boolean doSelect) {
        // Ascertain correct position
        int index = getInsertPositionOfElement(parentElement, childElement);
        return addElementAtIndex(source, parentElement, childElement, index, doSelect);
    }

    /**
     * Delete a child Element from its parent and notify any listeners and Undo
     * 
     * @param source Who is telling us this
     * @param element The Element to remove
     * @return The Element deleted
     */
    public synchronized Element deleteElementUndoable(Object source, Element element) {
        Element parent = element.getParent();
        if(parent != null) {
            // Get Undoable Action First so we can get full dom path
            UndoableAction action = null;
            if(_undoHandler != null) {
                action = new UndoableDeleteAction(parent, element);
            }

            // Do it
            removeElement(source, element);

            if(_undoHandler != null && action != null) {
                _undoHandler.addUndoableAction(action);
            }
        }
        return element;
    }

    /**
     * Cut a child Element from its parent and notify any listeners and Undo
     * 
     * @param source Who is telling us this
     * @param element The Element to remove
     * @return The Element cut
     */
    public synchronized Element cutElementUndoable(Object source, Element element) {
        Element parent = element.getParent();
        if(parent != null) {
            // Get Undoable Action First so we can get full dom path
            UndoableAction action = null;
            if(_undoHandler != null) {
                action = new UndoableCutAction(parent, element);
            }

            // Do it
            removeElement(source, element);

            if(_undoHandler != null && action != null) {
                _undoHandler.addUndoableAction(action);
            }
        }
        return element;
    }

    /**
     * Move an Element to a new Parent
     * 
     * @param source Who is telling us this
     * @param element The Element to move
     * @param newParent The new parent Element to move it to
     * @param doSelect A hint to any registered listeners that they might want to select the Element
     * @return the Element moved
     */
    public Element moveElementUndoable(Object source, Element element, Element newParent, boolean doSelect) {
        Element oldParent = element.getParent();
        if(oldParent != null) {
            // Get Undoable Action First so we can get index position
            UndoableAction action = null;
            if(_undoHandler != null) {
                action = new UndoableMoveAction(oldParent, newParent, element);
            }

            // Do it
            removeElement(source, element);
            addElement(this, newParent, element, doSelect);

            if(_undoHandler != null && action != null) {
                _undoHandler.addUndoableAction(action);
            }
        }
        return element;
    }

    /**
     * Copy an Element to a new Parent
     * 
     * @param source Who is telling us this
     * @param element The Element to copy
     * @param newParent  The new parent Element to copy it to
     * @param doSelect A hint to any registered listeners that they might want to select the Element
     * @return the new Element copied
     */
    public Element copyElementUndoable(Object source, Element element, Element newParent, boolean doSelect) {
        Element newElement = (Element)element.clone();
        addElement(this, newParent, newElement, doSelect);
        if(_undoHandler != null) {
            UndoableAction action = new UndoableCopyAction(newParent, newElement);
            _undoHandler.addUndoableAction(action);
        }
        return newElement;
    }


    /**
     * Paste an Element to a new Parent. This differs from copyElementUndoable in that an 
     * UndoablePasteAction is generated.
     * 
     * @param source Who is telling us this
     * @param element The Element to copy
     * @param newParent  The new parent Element to copy it to
     * @param doSelect A hint to any registered listeners that they might want to select the Element
     * @return the Element copied
     */
    public Element pasteElementUndoable(Object source, Element element, Element newParent, boolean doSelect) {
        Element newElement = (Element)element.clone();
        addElement(this, newParent, newElement, doSelect);
        if(_undoHandler != null) {
            UndoableAction action = new UndoablePasteAction(newParent, newElement);
            _undoHandler.addUndoableAction(action);
        }
        return newElement;
    }

    /**
     * Generate a ID for an idenetifier
     * 
     * @param newElement The parent Element
     * @return A Unique identifier
     */
	public String generateUniqueID(Element element){
		String prefix = "RLD-";
		return RandomGUID.getUniqueID(prefix);
	}

    // ==========================================================================
    // =============== ELEMENT ADDING/MOVING/COPYING/DELETING HANDLING ==========
    // ==========================================================================

    /**
     * Decide whether a child Element can be added to a parent Element by max amount
     * 
     * @param parentElement The parent Element to which we wish to add
     * @param childSchemaElement The SchemaElement of the child to add
     * @return True if we can add it, or false if not
     */
    public boolean canAddElement(Element parentElement, SchemaElement childSchemaElement) {
        if(childSchemaElement == null) {
            return true;
        }

        // Find out how many we can add
        int max = childSchemaElement.getMaxOccurs();
        // Unlimited
        if(max == -1) {
            return true;
        }
        
        // Compare with how many we have - we must use the namespace!
        List list = parentElement.getChildren(childSchemaElement.getName(), childSchemaElement.getNamespace());
        return list.size() < max;
    }

    /**
     * @param parentElement The Parent Element
     * @param childElement The child Element we want to add
     * @return true if we are allowed (per Schema rules) to add a child to a parent Element
     */
    public boolean isAllowedChild(Element parentElement, Element childElement) {
        // For now, don't allow different namespaces
        if(!parentElement.getNamespace().equals(childElement.getNamespace())) {
            return false;
        }

        XMLPath xmlPath = XMLPath.getXMLPathForElement(parentElement);
        SchemaElement parentSchemaElement = (SchemaElement)getSchemaController().getSchemaNode(xmlPath);
        if(parentSchemaElement != null) {
            SchemaElement childSchemaElement = parentSchemaElement.getChild(childElement.getName());
            if(childSchemaElement == null) {
                return false;
            }
            return canAddElement(parentElement, childSchemaElement);
        }

        return false;
    }

    /**
     * Decide whether a Element can be deleted
     * @param element The Element which we wish to delete
     * @return True if we can delete it, or false if not
     */
    public boolean canDeleteElement(Element element) {
        XMLPath xmlPath = XMLPath.getXMLPathForElement(element);
        SchemaElement schemaElement = (SchemaElement)getSchemaController().getSchemaNode(xmlPath);
        
        // This might be a foreign element that is not in our Schema
        if(schemaElement == null) {
            return true;
        }
        
        // Root
        Element parentElement = element.getParent();
        if(parentElement == null) {
            return false;
        }

        // If we have to have a minimum of one
        List list = parentElement.getChildren(schemaElement.getName(), schemaElement.getNamespace());
        return list.size() > schemaElement.getMinOccurs();
    }

    /**
     * Whether we can move the Element up one place<p>
     * <p>
     * This is possible only if part of a sequence grouping if:<p>
     * 1. The index position of element is greater than zero and<p>
     * 2. The previous sibling's Name is the same as element's and<p>
     * 3. The previous sibling's Namespace is the same as element's<p>
     * <p>
     * This is possible only if part of a choice grouping if:<p>
     * 1. The index position of element is greater than zero and<p>
     * 2. The previous sibling is part of the same choice grouping<p>
     * <p>
     * 
     * @return True if we can move the Element up one place.<p>
     */
    public boolean canMoveElementUp(Element element) {
        // Determine whether we are part of a "choice" grouping
        XMLPath xmlPath = XMLPath.getXMLPathForElement(element);
        SchemaElement schemaElement = (SchemaElement)getSchemaController().getSchemaNode(xmlPath);
        
        // This might be a foreign element that is not in our Schema
        if(schemaElement == null) {
            return false;
        }
        
        boolean isChoiceElement = schemaElement.isChoiceElement();
        
        Element parent = element.getParent();
        if(parent != null) {
            List children = parent.getChildren();
            if(children.size() > 1) {
                int index = children.indexOf(element);
                if(index > 0) {
                    Element previousSibling = (Element)children.get(index - 1);
                    // Choice
                    if(isChoiceElement) {
                        // See if element and previous sibling are part of the same group
                        xmlPath = XMLPath.getXMLPathForElement(previousSibling);
                        SchemaElement schemaElement2 = (SchemaElement)getSchemaController().getSchemaNode(xmlPath);
                        if(schemaElement2 != null) {
                            if(schemaElement.getElementDecl().getParent() == schemaElement2.getElementDecl().getParent()) {
                            	return true;
                        	}
                        }
                    }
                    // Sequence
                    else {
                        // Only if same type and namespace
                        if(previousSibling.getName().equals(element.getName()) &&
                                previousSibling.getNamespace().equals(element.getNamespace())) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
    
    /**
     * Whether we can move the Element down one place<p>
     * <p>
     * This is possible only if part of a choice grouping if:<p>
     * 1. The index position of element is less than parent child size and<p>
     * 2. The next sibling's Name is the same as element's and<p>
     * 3. The next sibling's Namespace is the same as element's<p>
     * <p>
     * This is possible only if part of a choice grouping if:<p>
     * 1. The index position of element is less than parent child size and<p>
     * 2. The next sibling is part of the same choice grouping<p>
     * <p>
     * 
     * @return True if we can move the Element down one place<p>
     */
    public boolean canMoveElementDown(Element element) {
        // Determine whether we are part of a "choice" grouping
        XMLPath xmlPath = XMLPath.getXMLPathForElement(element);
        SchemaElement schemaElement = (SchemaElement)getSchemaController().getSchemaNode(xmlPath);
        
        // This might be a foreign element that is not in our Schema
        if(schemaElement == null) {
            return false;
        }
        
        boolean isChoiceElement = schemaElement.isChoiceElement();

        Element parent = element.getParent();
        if(parent != null) {
            List children = parent.getChildren();
            if(children.size() > 1) {
                int index = children.indexOf(element);
                if(index < children.size() - 1) {
                    Element nextSibling = (Element)children.get(index + 1);
                    // Choice
                    if(isChoiceElement) {
                        // See if element and next sibling are part of the same group
                        xmlPath = XMLPath.getXMLPathForElement(nextSibling);
                        SchemaElement schemaElement2 = (SchemaElement)getSchemaController().getSchemaNode(xmlPath);
                        if(schemaElement2 != null) {
                            if(schemaElement.getElementDecl().getParent() == schemaElement2.getElementDecl().getParent()) {
                            	return true;
                        	}
                        }
                    }
                    // Sequence
                    else {
                        // Only if same type and namespace
                        if(nextSibling.getName().equals(element.getName()) &&
                                nextSibling.getNamespace().equals(element.getNamespace())) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
    
    // =============================================================================
    // ========================== ATTRIBUTE OPERATIONS =============================
    // =============================================================================

    /**
     * Create and Add a new Attribute to a given Element as per the Schema
     * @param source Who is telling us this
     * @param parentElement The parent Element
     * @param newSchemaAttribute The SchemaAttribute of the Attribute we want to add
     * @return The created attribute or null if it cannot be done
     */
    public Attribute addAttribute(Object source, Element parentElement, SchemaAttribute newSchemaAttribute) {
        if(parentElement == null || newSchemaAttribute == null) return null;

        Attribute att = newSchemaAttribute.createAttribute();
        if(att != null) {
            // Add it at the correct position
            java.util.List list = parentElement.getAttributes();
            int index = getInsertPositionOfAttribute(parentElement, newSchemaAttribute);
            if(index < 0 || index > list.size()) {
                index = 0;
            }
            list.add(index, att);
        }

        return att;
    }

	/**
	 * Add an Attribute to an Element with any default value.
	 * The Atttribute's default value is set from the SchemaController if there is one.
	 * No notification is sent.
	 * 
	 * @param source The source object
	 * @param parentElement The Element to which we are adding the Attribute
	 * @param schemaElement The Corresponding SchemaElement
	 * @param attName The Attribute Name
	 * @return The Attribute or null
	 */
	protected Attribute addAttributeWithDefaultValue(Object source, Element parentElement, String attName) {
	    Attribute att = null;
	    
	    XMLPath xmlPath = XMLPath.getXMLPathForElement(parentElement).appendAttributeName(attName);
	    if(xmlPath != null) {
	        SchemaAttribute schemaAtt = (SchemaAttribute)getSchemaController().getSchemaNode(xmlPath);
	        if(schemaAtt != null) {
		        att = addAttribute(source, parentElement, schemaAtt);
		        if(att != null) {
			        String attValue = getSchemaController().getDefaultValue(schemaAtt);
			        if(attValue != null) {
				        att.setValue(attValue);
			        }
		        }
	        }
	    }
	    
		return att;
	}

	// =============================================================================
    // ============================== SOME USEFUL ROUTINES  ========================
    // =============================================================================

    /**
     * @param parentElement The parent Element
     * @param childElement The element we want to add
     * @return the correct position to insert a child Element
     */
    public int getInsertPositionOfElement(Element parentElement, Element childElement) {
        // Ascertain the SchemaElement for the child Element
        XMLPath xmlPath = XMLPath.getXMLPathForElement(parentElement);
        xmlPath.appendElementName(childElement.getName());
        SchemaElement schemaElement = (SchemaElement)getSchemaController().getSchemaNode(xmlPath);
        return getInsertPositionOfElement(parentElement, schemaElement);
    }
        
    /**
     * @param parentElement The parent Element
     * @param childSchemaElement The SchemaElement of the element we want to add
     * @return the correct position to insert an Element given its SchemaElement
     */
    public int getInsertPositionOfElement(Element parentElement, SchemaElement childSchemaElement) {
        if(childSchemaElement == null) {
            return parentElement.getChildren().size();
        }

        // No children
        if(parentElement.getChildren().size() == 0) {
            return 0;
        }
        
        int index = 0;

        // New Element's name
        String name = childSchemaElement.getName();

        // Get the Parent SchemaElement
        SchemaElement parentSchemaElement = (SchemaElement)childSchemaElement.getParent();

        // If it's null we must be at the root
        if(parentSchemaElement == null) {
            parentSchemaElement = childSchemaElement;
        }

        // Get the Parent SchemaElement's children
        SchemaElement[] schemaChildren = parentSchemaElement.getChildren();

        // Iterate thru the Parent Element's children
        List children = parentElement.getChildren();
        for(int i = 0; i < children.size(); i++) {
            Element element = (Element)children.get(i);

            // Get Position of this Element as per Schema
            String elementName = element.getName();
            int pos = parentSchemaElement.indexofChild(elementName, element.getNamespace());

            // Check for -1 - this means an element not in the Schema
            if(pos != -1) {
                for(int j = pos; j < schemaChildren.length; j++) {
                    if(name.equals(schemaChildren[j].getName())) {
                        index = i + 1;
                    }
                }
            }
        }

        return index;
    }

    /**
     * @param parentElement The parent Element
     * @param schemaAttribute The SchemaAttribute of the Attribute we want to add
     * @return the correct position to insert an Attribute in an Element
     */
    public int getInsertPositionOfAttribute(Element parentElement, SchemaAttribute schemaAttribute) {
        int index = 0;

        // Get Element's Attributes
        java.util.List elementAtts = parentElement.getAttributes();

        // None here
        if(elementAtts.size() == 0) {
            return 0;
        }

        // Attributes's name
        String name = schemaAttribute.getName();

        // Get the Parent SchemaElement
        SchemaElement parentSchemaElement = (SchemaElement)schemaAttribute.getParent();

        // Get the Parent SchemaElement's schema attributes
        SchemaAttribute[] schemaAtts = parentSchemaElement.getSchemaAttributes();

        // Iterate thru the Parent Element's attributes
        for(int i = 0; i < elementAtts.size(); i++) {
            Attribute att = (Attribute)elementAtts.get(i);

            // Get Position of this Attribute as per Schema
            String attName = att.getName();
            int pos = parentSchemaElement.indexofSchemaAttribute(attName);

            // Check for -1 - this means an Attribute not in the Schema
            if(pos != -1) {
                for(int j = pos; j < schemaAtts.length; j++) {
                    if(name.equals(schemaAtts[j].getName())) index = i + 1;
                }
            }
            else {
                index = i + 1;
            }
        }

        return index;
    }


	// =============================================================================
    // ============================ UNDO HANDLING ==================================
    // =============================================================================

    /**
     * Set the UndoHandler
     * @param undoHandler The UndoHandler 
     */
    public void setUndoHandler(UndoHandler undoHandler) {
        _undoHandler = undoHandler;
    }

    /**
     * @return The UndoHandler or null if there isn't one set
     */
    public UndoHandler getUndoHandler() {
        return _undoHandler;
    }

    /**
     * Handles Undo/Redo for Deleting Elements
     */
    protected class UndoableDeleteAction implements UndoableAction {
        Element parent;
        Element element;
        int index;
        String name;

        public UndoableDeleteAction(Element parent, Element element) {
            this.parent = parent;
            this.element = element;
            index = indexOfElement(element);
            // Friendly Name
            name = getSchemaController().getElementFriendlyName(XMLPath.getXMLPathForElement(element));
            if(name == null) {
                name = element.getName();
            }
        }

        public void undo() {
            addElementAtIndex(null, parent, element, index, true);
        }

        public void redo() {
            index = indexOfElement(element);
            removeElement(null, element);
        }

        public String getName() {
            return Messages.getString("uk.ac.reload.moonunit.SchemaDocument.1") + " " + name; //$NON-NLS-1$ //$NON-NLS-2$
        }
    }

    /**
     * Handles Undo/Redo for Cutting Elements
     */
    protected class UndoableCutAction extends UndoableDeleteAction {

        public UndoableCutAction(Element parent, Element element) {
            super(parent, element);
        }

        public String getName() {
            return Messages.getString("uk.ac.reload.moonunit.SchemaDocument.2") + " " + name; //$NON-NLS-1$ //$NON-NLS-2$
        }
    }

    /**
     * Handles Undo/Redo for Adding Elements
     */
    protected class UndoableAddAction implements UndoableAction {
        Element parent;
        Element element;
        int index;
        String name;

        public UndoableAddAction(Element parent, Element element) {
            this.parent = parent;
            this.element = element;
            index = indexOfElement(element);
            // Friendly Name
            name = getSchemaController().getElementFriendlyName(XMLPath.getXMLPathForElement(element));
            if(name == null) {
                name = element.getName();
            }
        }

        public void undo() {
            index = indexOfElement(element);
            removeElement(null, element);
        }

        public void redo() {
            addElementAtIndex(null, parent, element, index, true);
        }

        public String getName() {
            return Messages.getString("uk.ac.reload.moonunit.SchemaDocument.3") + " " + name; //$NON-NLS-1$ //$NON-NLS-2$
        }
    }

    /**
     * Handles Undo/Redo for Copying Elements
     */
    protected class UndoableCopyAction extends UndoableAddAction {
        public UndoableCopyAction(Element parent, Element element) {
            super(parent, element);
        }

        public String getName() {
            return Messages.getString("uk.ac.reload.moonunit.SchemaDocument.4") + " " + name; //$NON-NLS-1$ //$NON-NLS-2$
        }
    }

    /**
     * Handles Undo/Redo for Pasting Elements
     */
    protected class UndoablePasteAction extends UndoableAddAction {
        public UndoablePasteAction(Element parent, Element element) {
            super(parent, element);
        }

        public String getName() {
            return Messages.getString("uk.ac.reload.moonunit.SchemaDocument.5") + " " + name; //$NON-NLS-1$ //$NON-NLS-2$
        }
    }

    /**
     * Handles Undo/Redo for Moving Elements
     */
    protected class UndoableMoveAction implements UndoableAction {
        Element oldParent;
        Element newParent;
        Element element;
        int oldIndex, newIndex;
        String name;

        public UndoableMoveAction(Element oldParent, Element newParent, Element element) {
            this.oldParent = oldParent;
            this.newParent = newParent;
            this.element = element;
            oldIndex = indexOfElement(element);
            // Friendly Name
            name = getSchemaController().getElementFriendlyName(XMLPath.getXMLPathForElement(element));
            if(name == null) {
                name = element.getName();
            }
        }

        public void undo() {
            newIndex = indexOfElement(element);
            removeElement(null, element);
            addElementAtIndex(null, oldParent, element, oldIndex, true);
        }

        public void redo() {
            oldIndex = indexOfElement(element);
            removeElement(null, element);
            addElementAtIndex(null, newParent, element, newIndex, true);
        }

        public String getName() {
            return Messages.getString("uk.ac.reload.moonunit.SchemaDocument.6") + " " + name; //$NON-NLS-1$ //$NON-NLS-2$
        }
    }
}
