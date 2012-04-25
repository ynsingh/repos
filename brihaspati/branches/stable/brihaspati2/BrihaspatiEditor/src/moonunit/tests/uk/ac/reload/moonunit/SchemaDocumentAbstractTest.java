
package uk.ac.reload.moonunit;

import junit.framework.TestCase;

import org.jdom.Attribute;
import org.jdom.Element;
import org.jdom.Namespace;

import uk.ac.reload.diva.undo.UndoHandler;
import uk.ac.reload.diva.undo.UndoableAction;
import uk.ac.reload.jdom.XMLPath;
import uk.ac.reload.moonunit.schema.SchemaAttribute;
import uk.ac.reload.moonunit.schema.SchemaElement;


/**
 * SchemaDocumentAbstractTest
 * 
 * @author Phillip Beauvoir
 * @version $Id: SchemaDocumentAbstractTest.java,v 1.1 1998/03/25 20:27:36 ynsingh Exp $
 */
public abstract class SchemaDocumentAbstractTest extends TestCase {


    // ---------------------------------------------------------------------------------------------
    
    public void itestGetSchemaController(SchemaDocument schemaDocument, Class schemaController) {
        assertTrue("SchemaController is wrong", schemaDocument.getSchemaController().getClass() == schemaController);
    }

    // ---------------------------------------------------------------------------------------------

    public void itestGetRootNamespace(SchemaDocument schemaDocument, Namespace expectedNamespace) {
        assertEquals("Wrong Namespace Prefix", expectedNamespace.getPrefix(),
                schemaDocument.getRootNamespace().getPrefix());
        assertEquals("Wrong Namespace URI", expectedNamespace.getURI(),
                schemaDocument.getRootNamespace().getURI());
    }
    
    // ---------------------------------------------------------------------------------------------

    public void itestGetTargetNamespace(SchemaDocument schemaDocument, Namespace expectedNamespace) {
        assertEquals("Wrong Namespace Prefix", expectedNamespace.getPrefix(),
                schemaDocument.getTargetNamespace().getPrefix());
        assertEquals("Wrong Namespace URI", expectedNamespace.getURI(),
                schemaDocument.getTargetNamespace().getURI());
    }
    
    // ---------------------------------------------------------------------------------------------
    
    public Element itestGetElement(SchemaDocument schemaDocument, SchemaElement schemaElement) {
        assertNotNull("SchemaElement is null", schemaElement);
        Element element = schemaDocument.getElement(schemaElement);
        assertNotNull("Element is null", element);
        assertEquals("Wrong Element found", schemaElement.getName(), element.getName());
        assertEquals("Wrong Element found", schemaElement.getNamespace(), element.getNamespace());
        return element;
    }
    
    // ---------------------------------------------------------------------------------------------
    
    public Element[] itestGetElements(SchemaDocument schemaDocument, SchemaElement schemaElement, int expectedSize) {
        assertNotNull("SchemaElement is null", schemaElement);
        Element[] elements = schemaDocument.getElements(schemaElement);
        assertEquals("Elements size wrong", expectedSize, elements.length);
        for(int i = 0; i < elements.length; i++) {
            assertEquals("Wrong Element", schemaElement.getName(), elements[i].getName());
        }
        return elements;
    }
    
    // ---------------------------------------------------------------------------------------------
    
    public void itestAddCommentsToDocument(SchemaDocument schemaDocument) {
        schemaDocument.addCommentsToDocument();
        assertNotNull("Comments should not be null", schemaDocument.getComments());
    }

    // ---------------------------------------------------------------------------------------------

    public Element itestAddElementUniqueBySchema(SchemaDocument schemaDocument, SchemaElement schemaElement) {
        assertNotNull("SchemaElement is null", schemaElement);
        Element element = schemaDocument.addElementUniqueBySchema(this, schemaElement, true);
        assertNotNull("Element is null", element);
        assertNotNull("Document Element not added", element.getDocument());
        return element;
    }
    
    // ---------------------------------------------------------------------------------------------

    public Element itestAddElementUniqueBySchemaUndoable(SchemaDocument schemaDocument, SchemaElement schemaElement) {
        assertNotNull("SchemaElement is null", schemaElement);
        Element element = schemaDocument.addElementUniqueBySchemaUndoable(this, schemaElement, true);
        assertNotNull("Element is null", element);
        assertNotNull("Document Element not added", element.getDocument());
        return element;
    }
    
    // ---------------------------------------------------------------------------------------------
    
    public Element itestAddElementBySchemaUndoable(SchemaDocument schemaDocument, SchemaElement schemaElement) {
        assertNotNull("SchemaElement is null", schemaElement);
        
        UndoHandler undoHandler = new UndoHandler();
        schemaDocument.setUndoHandler(undoHandler);

        Element element = schemaDocument.addElementBySchemaUndoable(this, schemaDocument.getRootElement(), schemaElement, true);
        assertNotNull("Element is null", element);
        assertNotNull("Document Element not added", element.getDocument());
        
        UndoableAction undoAction = undoHandler.nextUndoAction();
        assertNotNull("UndoableAction is null", undoAction);
        
        undoHandler.undoLastAction();
        
        undoAction = undoHandler.nextUndoAction();
        assertNull("UndoableAction should be null", undoAction);
        assertNull("Document Element not undone", element.getDocument());

        return element;
    }
    
    // ---------------------------------------------------------------------------------------------
    
    /**
     * Internal Test for addElementBySchema()
     */
    public Element itestAddElementBySchema(SchemaDocument schemaDocument, Element parentElement, SchemaElement schemaElement) {
        assertNotNull("SchemaElement is null", schemaElement);
        assertNotNull("Element is null", parentElement);
        
        Element element = schemaDocument.addElementBySchema(this, parentElement, schemaElement, true);
        assertNotNull("Element is null", element);
        assertNotNull("Document Element not added", element.getDocument());
        assertEquals("Wrong parent", parentElement, element.getParent());
        return element;
    }

    // ---------------------------------------------------------------------------------------------
    
    /**
     * Internal Test for addElementByXMLPath()
     */
    public Element itestAddElementByXMLPath(SchemaDocument schemaDocument, Element parentElement, XMLPath xmlPath) {
        assertNotNull("XMLPath is null", xmlPath);
        assertNotNull("Element is null", parentElement);
        
        Element element = schemaDocument.addElementByXMLPath(this, parentElement, xmlPath, true);
        assertNotNull("Element is null", element);
        
        int index = xmlPath.getLastPart().indexOf(":");
        String name = (index == -1) ? xmlPath.getLastPart() : xmlPath.getLastPart().substring(index+1);
        assertEquals("Wrong Element Name", name, element.getName());
        
        // Check we get the same one again
        Element element2 = schemaDocument.addElementByXMLPath(this, parentElement, xmlPath, true);
        assertSame("Element not the same", element, element2);

        return element;
    }

    // ---------------------------------------------------------------------------------------------
    
    public Element itestAddElementUndoable(SchemaDocument schemaDocument, Element parentElement, Element childElement) {
        assertNotNull("Element is null", parentElement);
        assertNotNull("Element is null", childElement);
        
        UndoHandler undoHandler = new UndoHandler();
        schemaDocument.setUndoHandler(undoHandler);

        Element element = schemaDocument.addElementUndoable(this, parentElement, childElement, true);
        assertNotNull("Element is null", element);
        assertNotNull("Document Element not added", element.getDocument());
        assertEquals("Wrong parent", parentElement, element.getParent());
        
        UndoableAction undoAction = undoHandler.nextUndoAction();
        assertNotNull("UndoableAction is null", undoAction);
        
        undoHandler.undoLastAction();
        
        undoAction = undoHandler.nextUndoAction();
        assertNull("UndoableAction should be null", undoAction);
        assertNull("Document Element not undone", element.getDocument());
        
        return element;
    }

    // ---------------------------------------------------------------------------------------------
    
    public Element itestAddElement(SchemaDocument schemaDocument, Element parentElement, Element childElement) {
        assertNotNull("Element is null", parentElement);
        assertNotNull("Element is null", childElement);
        
        Element element = schemaDocument.addElement(this, parentElement, childElement, true);
        assertNotNull("Element is null", element);
        assertNotNull("Document Element not added", element.getDocument());
        assertEquals("Wrong parent", parentElement, element.getParent());
        
        return element;
    }

    // ---------------------------------------------------------------------------------------------
    
    public Element itestDeleteElementUndoable(SchemaDocument schemaDocument, Element element) {
        assertNotNull("Element is null", element);

        Element parentElement = element.getParent();
        assertNotNull("Parent is null", parentElement);
        
        UndoHandler undoHandler = new UndoHandler();
        schemaDocument.setUndoHandler(undoHandler);

        element = schemaDocument.deleteElementUndoable(this, element);
        assertNotNull("Element is null", element);
        assertNull("Document Element not removed", element.getDocument());
        assertNull("Parent should be null", element.getParent());
        
        UndoableAction undoAction = undoHandler.nextUndoAction();
        assertNotNull("UndoableAction is null", undoAction);
        
        undoHandler.undoLastAction();
        
        undoAction = undoHandler.nextUndoAction();
        assertNull("UndoableAction should be null", undoAction);
        assertNotNull("Document Element not re-added", element.getDocument());
        assertNotNull("Parent should not be null", element.getParent());
        assertEquals("Wrong Parent", parentElement, element.getParent());
        
        return element;
    }

    // ---------------------------------------------------------------------------------------------
    
    public Element itestCutElementUndoable(SchemaDocument schemaDocument, Element element) {
        assertNotNull("Element is null", element);

        Element parentElement = element.getParent();
        assertNotNull("Parent is null", parentElement);
        
        UndoHandler undoHandler = new UndoHandler();
        schemaDocument.setUndoHandler(undoHandler);

        element = schemaDocument.cutElementUndoable(this, element);
        assertNotNull("Element is null", element);
        assertNull("Document Element not removed", element.getDocument());
        assertNull("Parent should be null", element.getParent());
        
        UndoableAction undoAction = undoHandler.nextUndoAction();
        assertNotNull("UndoableAction is null", undoAction);
        
        undoHandler.undoLastAction();
        
        undoAction = undoHandler.nextUndoAction();
        assertNull("UndoableAction should be null", undoAction);
        assertNotNull("Document Element not re-added", element.getDocument());
        assertNotNull("Parent should not be null", element.getParent());
        assertEquals("Wrong Parent", parentElement, element.getParent());
        
        return element;
    }

    // ---------------------------------------------------------------------------------------------
    
    public void itestMoveElementUndoable(SchemaDocument schemaDocument, Element element, Element newParent) {
        assertNotNull("Element is null", element);
        assertNotNull("Element is null", newParent);

        Element parentElement = element.getParent();
        assertNotNull("Parent is null", parentElement);
        
        UndoHandler undoHandler = new UndoHandler();
        schemaDocument.setUndoHandler(undoHandler);

        element = schemaDocument.moveElementUndoable(this, element, newParent, true);
        assertNotNull("Element is null", element);
        assertFalse("Child should be removed", parentElement.getContent().contains(element));
        assertNotNull("Document Element not re-added", element.getDocument());
        assertEquals("Wrong Parent", newParent, element.getParent());
        
        UndoableAction undoAction = undoHandler.nextUndoAction();
        assertNotNull("UndoableAction is null", undoAction);
        
        undoHandler.undoLastAction();
        
        undoAction = undoHandler.nextUndoAction();
        assertFalse("Child should be removed", newParent.getContent().contains(element));
        assertNotNull("Document Element not re-added", element.getDocument());
        assertEquals("Wrong Parent", parentElement, element.getParent());
    }

    // ---------------------------------------------------------------------------------------------
    
    public void itestCopyElementUndoable(SchemaDocument schemaDocument, Element element, Element newParent) {
        assertNotNull("Element is null", element);
        assertNotNull("Element is null", newParent);

        Element parentElement = element.getParent();
        assertNotNull("Parent is null", parentElement);
        
        UndoHandler undoHandler = new UndoHandler();
        schemaDocument.setUndoHandler(undoHandler);

        element = schemaDocument.copyElementUndoable(this, element, newParent, true);
        assertNotNull("Element is null", element);
        assertFalse("Child should be a copy", parentElement.getContent().contains(element));
        assertNotNull("Document Element not re-added", element.getDocument());
        assertEquals("Wrong Parent", newParent, element.getParent());
        
        UndoableAction undoAction = undoHandler.nextUndoAction();
        assertNotNull("UndoableAction is null", undoAction);
        
        undoHandler.undoLastAction();
        
        undoAction = undoHandler.nextUndoAction();
        assertFalse("Child should be removed", newParent.getContent().contains(element));
        assertNull("Document Element not removed", element.getDocument());
        assertNull("Parent should be null", element.getParent());
    }

    // ---------------------------------------------------------------------------------------------
    
    public void itestPasteElementUndoable(SchemaDocument schemaDocument, Element element, Element newParent) {
        assertNotNull("Element is null", element);
        assertNotNull("Element is null", newParent);

        Element parentElement = element.getParent();
        assertNotNull("Parent is null", parentElement);
        
        UndoHandler undoHandler = new UndoHandler();
        schemaDocument.setUndoHandler(undoHandler);

        element = schemaDocument.pasteElementUndoable(this, element, newParent, true);
        assertNotNull("Element is null", element);
        assertFalse("Child should be a copy", parentElement.getContent().contains(element));
        assertNotNull("Document Element not re-added", element.getDocument());
        assertEquals("Wrong Parent", newParent, element.getParent());
        
        UndoableAction undoAction = undoHandler.nextUndoAction();
        assertNotNull("UndoableAction is null", undoAction);
        
        undoHandler.undoLastAction();
        
        undoAction = undoHandler.nextUndoAction();
        assertFalse("Child should be removed", newParent.getContent().contains(element));
        assertNull("Document Element not removed", element.getDocument());
        assertNull("Parent should be null", element.getParent());
    }

    // ---------------------------------------------------------------------------------------------
    
    public void itestCanAddElement(SchemaDocument schemaDocument, Element parentElement,
            							SchemaElement schemaElement, boolean expected) {
        assertNotNull("Element is null", parentElement);
        assertNotNull("SchemaElement is null", schemaElement);
        
        boolean result = schemaDocument.canAddElement(parentElement, schemaElement);
        assertEquals("Wrong result on canAddElement()", expected, result);
    }

    // ---------------------------------------------------------------------------------------------
    
    public void itestIsAllowedChild(SchemaDocument schemaDocument, Element parentElement,
            							Element childElement, boolean expected) {
        assertNotNull("Element is null", parentElement);
        assertNotNull("Element is null", childElement);
        
        boolean result = schemaDocument.isAllowedChild(parentElement, childElement);
        assertEquals("Wrong result on isAllowedChild()", expected, result);
    }

    // ---------------------------------------------------------------------------------------------
    
    public void itestCanDeleteElement(SchemaDocument schemaDocument, Element element, boolean expected) {
        assertNotNull("Element is null", element);
        boolean result = schemaDocument.canDeleteElement(element);
        assertEquals("Wrong result on canDeleteElement()", expected, result);
    }

    // ---------------------------------------------------------------------------------------------
    
    public void itestCanMoveElementUp(SchemaDocument schemaDocument, Element element, boolean expected) {
        assertNotNull("Element is null", element);
        boolean result = schemaDocument.canMoveElementUp(element);
        assertEquals("Wrong result on canMoveElementUp()", expected, result);
    }

    // ---------------------------------------------------------------------------------------------
    
    public void itestCanMoveElementDown(SchemaDocument schemaDocument, Element element, boolean expected) {
        assertNotNull("Element is null", element);
        boolean result = schemaDocument.canMoveElementDown(element);
        assertEquals("Wrong result on canMoveElementDown()", expected, result);
    }

    // ---------------------------------------------------------------------------------------------
    
    public Attribute itestAddAttribute(SchemaDocument schemaDocument, Element element, SchemaAttribute schemaAtt) {
        assertNotNull("Element is null", element);
        assertNotNull("SchemaAttribute is null", schemaAtt);
        Attribute att = schemaDocument.addAttribute(this, element, schemaAtt);
        assertNotNull("Attribute is null", att);
        assertEquals("Attribute is wrong", att, element.getAttribute(schemaAtt.getName(), schemaAtt.getNamespace()));
        return att;
    }

    // ---------------------------------------------------------------------------------------------
    
    public Attribute itestAddAttributeWithDefaultValue(SchemaDocument schemaDocument, Element parentElement, String attName) {
        assertNotNull("Element is null", parentElement);
        assertNotNull("attName is null", attName);
        Attribute att = schemaDocument.addAttributeWithDefaultValue(this, parentElement, attName);
        assertNotNull("Attribute is null", att);
        return att;
    }

    // ---------------------------------------------------------------------------------------------
    
    public void itestGetInsertPositionOfElement_ElementElement(SchemaDocument schemaDocument, Element parentElement,
            										Element childElement, int expected) {
        assertNotNull("Element is null", parentElement);
        assertNotNull("Element is null", childElement);
        int result = schemaDocument.getInsertPositionOfElement(parentElement, childElement);
        assertEquals("Wrong result on getInsertPositionOfElement()", expected, result);
    }

    // ---------------------------------------------------------------------------------------------
    
    public void itestGetInsertPositionOfElement_ElementSchemaElement(SchemaDocument schemaDocument, Element parentElement,
            										SchemaElement childSchemaElement, int expected) {
        assertNotNull("Element is null", parentElement);
        assertNotNull("SchemaElement is null", childSchemaElement);
        int result = schemaDocument.getInsertPositionOfElement(parentElement, childSchemaElement);
        assertEquals("Wrong result on getInsertPositionOfElement()", expected, result);
    }

    // ---------------------------------------------------------------------------------------------
    
    public void itestGetInsertPositionOfAttribute(SchemaDocument schemaDocument, 
            									Element parentElement, SchemaAttribute schemaAttribute, int expected) {
        assertNotNull("Element is null", parentElement);
        assertNotNull("SchemaAttribute is null", schemaAttribute);
        int result = schemaDocument.getInsertPositionOfAttribute(parentElement, schemaAttribute);
        assertEquals("Wrong result on getInsertPositionOfAttribute()", expected, result);
    }

    // ---------------------------------------------------------------------------------------------
    
    public void itestSetUndoHandler(SchemaDocument schemaDocument, UndoHandler undoHandler) {
        assertNotNull("UndoHandler is null", undoHandler);
        schemaDocument.setUndoHandler(undoHandler);
        assertNotNull("UndoHandler is null", schemaDocument.getUndoHandler());
    }

    // ---------------------------------------------------------------------------------------------
    
}
