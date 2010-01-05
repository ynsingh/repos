
package uk.ac.reload.editor.learningdesign.xml;

import java.io.File;
import java.io.FileNotFoundException;

import org.jdom.Attribute;
import org.jdom.Element;
import org.jdom.IllegalAddException;
import org.jdom.filter.ElementFilter;

import uk.ac.reload.diva.undo.UndoHandler;
import uk.ac.reload.diva.util.FileUtils;
import uk.ac.reload.editor.contentpackaging.CP_EditorHandler;
import uk.ac.reload.editor.learningdesign.LD_EditorHandler;
import uk.ac.reload.editor.metadata.xml.MD122_SchemaController;
import uk.ac.reload.editor.metadata.xml.MD_SchemaController;
import uk.ac.reload.jdom.XMLPath;
import uk.ac.reload.jdom.XMLUtils;
import uk.ac.reload.moonunit.SchemaDocumentAbstractTest;
import uk.ac.reload.moonunit.learningdesign.LD_Core;
import uk.ac.reload.moonunit.schema.SchemaAttribute;
import uk.ac.reload.moonunit.schema.SchemaElement;
import uk.ac.reload.testsupport.FileSupport;
import uk.ac.reload.testsupport.ld.LD_File1;


/**
 * LearningDesignA10Test
 * 
 * @author Phillip Beauvoir
 * @version $Id: LearningDesignA10Test.java,v 1.1 1998/03/26 15:50:37 ynsingh Exp $
 */
public class LearningDesignA10Test extends SchemaDocumentAbstractTest {

    /**
     * The path to the Test Data
     */
    String pathTestData;
    
    protected void setUp() throws Exception {
        // See if we have a testdata dir set from an Ant script
        pathTestData = System.getProperty("testdata.dir");
        if(pathTestData == null) {
            // Set a default
            pathTestData = "../junit-tests";
        }

        // Set Properties file to test properties
		System.setProperty("editor.properties.file", "uk.ac.reload.editor.testproperties.rb");
    }

    protected void tearDown() throws Exception {
        // Clean up
        FileUtils.deleteFolder(FileSupport.getMainTestFolder());
    }

    protected LearningDesign createNewLD() throws Exception {
        LDA10_CP113_SchemaController ldController = new LDA10_CP113_SchemaController();
        MD_SchemaController mdController = new MD122_SchemaController();
        File folder = FileSupport.getTempFolder("ld-test");
        return new LearningDesign(folder, ldController, mdController);
    }

    // ---------------------------------------------------------------------------------------------

    public void testLearningDesign_New() throws Exception {
        LearningDesign ld = createNewLD();
        assertNotNull("LearningDesign is null", ld);
        assertTrue("SchemaController is wrong", ld.getSchemaController() instanceof LDA10_CP113_SchemaController);
        assertNotNull("LearningDesign doc is null", ld.getDocument());
        assertTrue("LearningDesign doc should have Root Element", ld.getDocument().hasRootElement());
        assertFalse("LearningDesign should not be dirty", ld.isDirty());
        assertNotNull("Comments are null", ld.getComments());
    }

    // ---------------------------------------------------------------------------------------------
    
    public void testLearningDesign_File1() throws Exception {
        LearningDesign ld = new LearningDesign(new File(pathTestData, LD_File1.fileLD));
        assertNotNull("LearningDesign is null", ld);
        assertTrue("SchemaController is wrong", ld.getSchemaController() instanceof LDA10_CP113_SchemaController);
        assertNotNull("LearningDesign doc is null", ld.getDocument());
    }

    public void testLearningDesign_File2() throws Exception {
        try {
            LearningDesign ld = new LearningDesign((File)null);
            fail("Should have thrown NullPointerException");
        }
        catch(NullPointerException ex) {
            assertTrue(true);
        }
    }

    public void testLearningDesign_File3() throws Exception {
        try {
            LearningDesign ld = new LearningDesign(new File("bogus_file.xml"));
            fail("Should have thrown FileNotFoundException");
        }
        catch(FileNotFoundException ex) {
            assertTrue(true);
        }
    }

    // ---------------------------------------------------------------------------------------------

    public void testGetComments() throws Exception {
        LearningDesign ld = createNewLD();
        assertNotNull("Comments are null", ld.getComments());
    }

    // ---------------------------------------------------------------------------------------------

    public void testAddRootDeclarations() throws Exception {
        LearningDesign ld = createNewLD();
        Element root = ld.getRootElement();
        assertEquals("Should have Schema Instance Namespace", XMLUtils.XSI_Namespace,
                root.getNamespace("xsi"));
        assertEquals("Should have Schema Location Attribute",
                "http://www.imsglobal.org/xsd/imscp_v1p1 imscp_v1p1.xsd http://www.imsglobal.org/xsd/imsmd_v1p2 imsmd_v1p2p2.xsd http://www.imsglobal.org/xsd/imsld_v1p0 IMS_LD_Level_A.xsd",
                root.getAttribute(XMLUtils.XSI_SchemaLocation, XMLUtils.XSI_Namespace).getValue());
    }

    // ---------------------------------------------------------------------------------------------

    /**
     * Just a simple test until LearningDesign class supports Copy / Cut and Paste
     */
    public void testCanCopyElement() {
        LearningDesign ld = new LearningDesign();
        assertFalse("Should not be able to Copy Element", ld.canCopyElement(new Element("anything")));
        assertFalse("Should not be able to Copy Element",
                ld.canCopyElement(new Element("item", LD_EditorHandler.IMSLD_NAMESPACE_10)));
    }
    
    // ---------------------------------------------------------------------------------------------

    /**
     * Just a simple test until LearningDesign class supports Copy / Cut and Paste
     */
    public void testCanCutElement() {
        LearningDesign ld = new LearningDesign();
        assertFalse("Should not be able to Cut Element", ld.canCutElement(new Element("anything")));
        assertFalse("Should not be able to Cut Element",
                ld.canCutElement(new Element("item", LD_EditorHandler.IMSLD_NAMESPACE_10)));
    }
    
    // ---------------------------------------------------------------------------------------------

    /**
     * Just a simple test until LearningDesign class supports Copy / Cut and Paste
     */
    public void testCanPasteFromClipboard() {
        LearningDesign ld = new LearningDesign();
        assertFalse("Should not be able to Paste Element", ld.canPasteFromClipboard(new Element("anything")));
        assertFalse("Should not be able to Paste Element",
                ld.canPasteFromClipboard(new Element("item", LD_EditorHandler.IMSLD_NAMESPACE_10)));
    }
    
    // ---------------------------------------------------------------------------------------------

    public void testGetLevel() throws Exception {
        LearningDesign ld = createNewLD();
        assertEquals("A", ld.getLevel());
    }
    
    // ---------------------------------------------------------------------------------------------

    public void testGetLDElement() throws Exception {
        LearningDesign ld = createNewLD();
        assertNotNull("LD Element was null", ld.getLDElement());
    }
    
    // ---------------------------------------------------------------------------------------------

    // ---------------------------------------------------------------------------------------------
    // ---------------------------------- From SchemaDocument --------------------------------------
    // ---------------------------------------------------------------------------------------------
    
    public void testGetSchemaController() throws Exception {
        LearningDesign ld = new LearningDesign(new File(pathTestData, LD_File1.fileLD));
        itestGetSchemaController(ld, LDA10_CP113_SchemaController.class);
    }
    
    // ---------------------------------------------------------------------------------------------
    
    public void testGetRootNamespace() throws Exception {
        LearningDesign ld = createNewLD();
        itestGetRootNamespace(ld, CP_EditorHandler.IMSCP_NAMESPACE_113);
    }

    // ---------------------------------------------------------------------------------------------

    public void testGetTargetNamespace1() throws Exception {
        LearningDesign ld = createNewLD();
        itestGetTargetNamespace(ld, CP_EditorHandler.IMSCP_NAMESPACE_113);
    }

    // ---------------------------------------------------------------------------------------------

    public void testGetElement1() throws Exception {
        LearningDesign ld = new LearningDesign(new File(pathTestData, LD_File1.fileLD));
        SchemaElement schemaElement = ld.getSchemaController()
        							  .getSchemaModel()
        							  .getRootElement();
        itestGetElement(ld, schemaElement);
    }

    public void testGetElement2() throws Exception {
        LearningDesign ld = new LearningDesign(new File(pathTestData, LD_File1.fileLD));
        SchemaElement schemaElement = ld.getSchemaController()
		  							  .getSchemaModel()
		  							  .getRootElement()
		  							  .getChild("organizations")
		  							  .getChild("imsld:learning-design")		  							  
		  							  .getChild("components")
		  							  .getChild("roles")
		  							  .getChild("learner");
        itestGetElement(ld, schemaElement);
    }

    // ---------------------------------------------------------------------------------------------

    public void testGetElements1() throws Exception {
        LearningDesign ld = new LearningDesign(new File(pathTestData, LD_File1.fileLD));
        SchemaElement schemaElement = ld.getSchemaController()
		                              .getSchemaModel()
									  .getRootElement();
        itestGetElements(ld, schemaElement, 1);
    }
    
    public void testGetElements2() throws Exception {
        LearningDesign ld = new LearningDesign(new File(pathTestData, LD_File1.fileLD));
        SchemaElement schemaElement = ld.getSchemaController()
        							  .getSchemaModel()
        							  .getRootElement()
        							  .getChild("organizations")
        							  .getChild("imsld:learning-design")
        							  .getChild("components");
        itestGetElements(ld, schemaElement, 1);
    }

    public void testGetElements3() throws Exception {
        LearningDesign ld = new LearningDesign(new File(pathTestData, LD_File1.fileLD));
        SchemaElement schemaElement = ld.getSchemaController()
        							  .getSchemaModel()
        							  .getRootElement()
        							  .getChild("organizations")
        							  .getChild("imsld:learning-design")
        							  .getChild("components")
        							  .getChild("roles")
        							  .getChild("staff");
        itestGetElements(ld, schemaElement, 2);
    }

    // ---------------------------------------------------------------------------------------------

    public void testAddCommentsToDocument() throws Exception {
        LearningDesign ld = createNewLD();
        itestAddCommentsToDocument(ld);
    }
    
    // ---------------------------------------------------------------------------------------------

    public void testAddElementUniqueBySchema1() throws Exception {
        LearningDesign ld = new LearningDesign(new File(pathTestData, LD_File1.fileLD));
        SchemaElement schemaElement = ld.getSchemaController()
									  .getSchemaModel()
									  .getRootElement()
									  .getChild("organizations")
									  .getChild("imsld:learning-design")
									  .getChild("components")
									  .getChild("roles")
									  .getChild("staff");
        itestAddElementUniqueBySchema(ld, schemaElement);
    }

    public void testAddElementUniqueBySchema2() throws Exception {
        LearningDesign ld = createNewLD();
        SchemaElement schemaElement = ld.getSchemaController()
									  .getSchemaModel()
									  .getRootElement()
									  .getChild("organizations")
									  .getChild("imsld:learning-design")
									  .getChild("components")
									  .getChild("roles")
									  .getChild("learner");
        itestAddElementUniqueBySchema(ld, schemaElement);
    }

    public void testAddElementUniqueBySchema3() throws Exception {
        LearningDesign ld = new LearningDesign(new File(pathTestData, LD_File1.fileLD));
        SchemaElement schemaElement = ld.getSchemaController()
									  .getSchemaModel()
									  .getRootElement();
        itestAddElementUniqueBySchema(ld, schemaElement);
    }

    // ---------------------------------------------------------------------------------------------
    public void testAddElementUniqueBySchemaUndoable1() throws Exception {
        LearningDesign ld = new LearningDesign(new File(pathTestData, LD_File1.fileLD));
        SchemaElement schemaElement = ld.getSchemaController()
									  .getSchemaModel()
									  .getRootElement()
									  .getChild("organizations")
									  .getChild("imsld:learning-design")
									  .getChild("components")
									  .getChild("roles")
									  .getChild("staff");
        itestAddElementUniqueBySchemaUndoable(ld, schemaElement);
    }

    public void testAddElementUniqueBySchemaUndoable2() throws Exception {
        LearningDesign ld = createNewLD();
        SchemaElement schemaElement = ld.getSchemaController()
									  .getSchemaModel()
									  .getRootElement()
									  .getChild("organizations")
									  .getChild("imsld:learning-design")
									  .getChild("components")
									  .getChild("roles")
									  .getChild("learner");
        itestAddElementUniqueBySchemaUndoable(ld, schemaElement);
    }

    public void testAddElementUniqueBySchemaUndoable3() throws Exception {
        LearningDesign ld = new LearningDesign(new File(pathTestData, LD_File1.fileLD));
        SchemaElement schemaElement = ld.getSchemaController()
									  .getSchemaModel()
									  .getRootElement();
        itestAddElementUniqueBySchemaUndoable(ld, schemaElement);
    }

    // ---------------------------------------------------------------------------------------------

    public void testAddElementBySchemaUndoable() throws Exception {
        LearningDesign ld = createNewLD();
        SchemaElement schemaElement = ld.getSchemaController()
									  .getSchemaModel()
									  .getRootElement()
									  .getChild("organizations")
									  .getChild("imsld:learning-design")
									  .getChild("components")
									  .getChild("roles")
									  .getChild("learner");
        itestAddElementBySchemaUndoable(ld, schemaElement);
    }

    // ---------------------------------------------------------------------------------------------

    /**
     * Add to LD 
     */
    public void testAddElementBySchema1() throws Exception {
        LearningDesign ld = new LearningDesign(new File(pathTestData, LD_File1.fileLD));
        Element parentElement = ld.getLDElement()
        						.getChild("method", ld.getRootElement().getNamespace("imsld"))
        						.getChild("play", ld.getRootElement().getNamespace("imsld"));
        
        SchemaElement schemaElement = new LDA10_CP113_SchemaController().getSchemaModel()
        								  .getRootElement()
        								  .getChild("organizations")
        								  .getChild("imsld:learning-design")
        								  .getChild("method")
        								  .getChild("play")
        								  .getChild("act");
        
        Element element = itestAddElementBySchema(ld, parentElement, schemaElement);
    }

    /**
     * All the bits got added
     */
    public void testAddElementBySchema2() throws Exception {
        LearningDesign ld = createNewLD();
        
        Element parentElement = ld.getLDElement()
        						  .getChild("components", ld.getRootElement().getNamespace("imsld"))
								  .getChild("roles", ld.getRootElement().getNamespace("imsld"));
        assertNotNull("parentElement was null", parentElement);

        SchemaElement schemaElement = ld.getSchemaController()
		  							  .getSchemaModel()
		  							  .getRootElement()
       								  .getChild("organizations")
       								  .getChild("imsld:learning-design")
		  							  .getChild("components")
		  							  .getChild("roles")
		  							  .getChild("staff");
        
        Element element = itestAddElementBySchema(ld, parentElement, schemaElement);
        
        assertEquals("Wrong Element", "staff", element.getName());
        
        assertEquals("Wrong Parent", "roles", element.getParent().getName());
        
        assertTrue("Should have no child elements", element.getChildren().isEmpty());
        
        Attribute att = element.getAttribute("identifier");
        assertNotNull("No identifier Attribute added", att);
        assertFalse("No identifier added", att.getValue().equals(""));
    }

    // ---------------------------------------------------------------------------------------------

    public void testAddElementByXMLPath1() throws Exception {
        LearningDesign ld = createNewLD();
        
        XMLPath xmlPath = new XMLPath("organizations/imsld:learning-design/title");
        Element element = itestAddElementByXMLPath(ld, ld.getRootElement(), xmlPath);
        assertEquals("Wrong Parent", "learning-design", element.getParent().getName());
        assertTrue("Element should have no children", element.getContent().isEmpty());
    }
    
    public void testAddElementByXMLPath2() throws Exception {
        LearningDesign ld = createNewLD();
        
        XMLPath xmlPath = new XMLPath("organizations/imsld:learning-design/prerequisites/item/title");
        Element element = itestAddElementByXMLPath(ld, ld.getRootElement(), xmlPath);
        assertEquals("Wrong Parent", "item", element.getParent().getName());
        assertTrue("Element should have no children", element.getContent().isEmpty());
    }

    // ---------------------------------------------------------------------------------------------

    public void testAddElementUndoable() throws Exception {
        LearningDesign ld = createNewLD();
        Element childElement = new Element("learning-objectives", ld.getRootElement().getNamespace());
        itestAddElementUndoable(ld, ld.getRootElement(), childElement);
    }

    // ---------------------------------------------------------------------------------------------

    public void testAddElement() throws Exception {
        LearningDesign ld = createNewLD();
        Element childElement = new Element("learning-objectives", ld.getRootElement().getNamespace());
        itestAddElement(ld, ld.getRootElement(), childElement);
    }

    // ---------------------------------------------------------------------------------------------

    public void testDeleteElementUndoable() throws Exception {
        LearningDesign ld = createNewLD();
        Element element = new Element("learning-objectives", ld.getRootElement().getNamespace());
        ld.getRootElement().addContent(element);
        itestDeleteElementUndoable(ld, element);
    }

    // ---------------------------------------------------------------------------------------------

    public void testCutElementUndoable() throws Exception {
        LearningDesign ld = createNewLD();
        Element element = new Element("learning-objectives", ld.getRootElement().getNamespace());
        ld.getRootElement().addContent(element);
        itestCutElementUndoable(ld, element);
    }

    // ---------------------------------------------------------------------------------------------

    public void testMoveElementUndoable() throws Exception {
        LearningDesign ld = createNewLD();
        Element lo = new Element("learning-objectives", ld.getRootElement().getNamespace());
        ld.getRootElement().addContent(lo);
        Element element = new Element("title", ld.getRootElement().getNamespace());
        lo.addContent(element);
        
        itestMoveElementUndoable(ld, element, ld.getRootElement());
    }

    // ---------------------------------------------------------------------------------------------

    public void testCopyElementUndoable() throws Exception {
        LearningDesign ld = createNewLD();
        Element lo = new Element("learning-objectives", ld.getRootElement().getNamespace());
        ld.getRootElement().addContent(lo);
        Element element = new Element("title", ld.getRootElement().getNamespace());
        lo.addContent(element);
        
        itestCopyElementUndoable(ld, element, ld.getRootElement());
    }

    // ---------------------------------------------------------------------------------------------

    public void testPasteElementUndoable() throws Exception {
        LearningDesign ld = createNewLD();
        Element lo = new Element("learning-objectives", ld.getRootElement().getNamespace());
        ld.getRootElement().addContent(lo);
        Element element = new Element("title", ld.getRootElement().getNamespace());
        lo.addContent(element);
        
        itestPasteElementUndoable(ld, element, ld.getRootElement());
    }

    // ---------------------------------------------------------------------------------------------

    public void testCanAddElement_True() throws Exception {
        LearningDesign ld = new LearningDesign(new File(pathTestData, LD_File1.fileLD));
        SchemaElement schemaElement = ld.getSchemaController()
        							  .getSchemaModel()
        							  .getRootElement()
        							  .getChild("organizations")
        							  .getChild("imsld:learning-design")
        							  .getChild("learning-objectives")
        							  .getChild("item");
        Element parentElement = ld.getLDElement().getChild("learning-objectives", ld.getLDElement().getNamespace());        
        itestCanAddElement(ld, parentElement, schemaElement, true);
    }

    public void testCanAddElement_False() throws Exception {
        LearningDesign ld = new LearningDesign(new File(pathTestData, LD_File1.fileLD));
        SchemaElement schemaElement = ld.getSchemaController()
        							  .getSchemaModel()
        							  .getRootElement()
        							  .getChild("organizations")
        							  .getChild("imsld:learning-design")
        							  .getChild("learning-objectives")
        							  .getChild("title");
        Element parentElement = ld.getLDElement().getChild("learning-objectives", ld.getLDElement().getNamespace());        
        itestCanAddElement(ld, parentElement, schemaElement, false);
    }

    // ---------------------------------------------------------------------------------------------

    /**
     * Different Namespaces
     */
    public void testIsAllowedChild1() throws Exception {
        LearningDesign ld = new LearningDesign(new File(pathTestData, LD_File1.fileLD));
        Element parentElement = ld.getRootElement();
        Element childElement = new Element("ponk");
        itestIsAllowedChild(ld, parentElement, childElement, false);
    }

    public void testIsAllowedChild2() throws Exception {
        LearningDesign ld = new LearningDesign(new File(pathTestData, LD_File1.fileLD));
        Element parentElement = ld.getRootElement();
        Element childElement = new Element("learning-objectives", ld.getRootElement().getNamespace());
        itestIsAllowedChild(ld, parentElement, childElement, false);
    }

    public void testIsAllowedChild3() throws Exception {
        LearningDesign ld = new LearningDesign(new File(pathTestData, LD_File1.fileLD));
        Element parentElement = ld.getLDElement();
        Element childElement = new Element("prerequisites", ld.getLDElement().getNamespace());
        itestIsAllowedChild(ld, parentElement, childElement, true);
    }

    // ---------------------------------------------------------------------------------------------

    public void testCanDeleteElement1() throws Exception {
        LearningDesign ld = new LearningDesign(new File(pathTestData, LD_File1.fileLD));
        Element element = ld.getRootElement();
        itestCanDeleteElement(ld, element, false);
    }

    public void testCanDeleteElement2() throws Exception {
        LearningDesign ld = new LearningDesign(new File(pathTestData, LD_File1.fileLD));
        Element element = ld.getLDElement()
        				  .getChild("learning-objectives", ld.getLDElement().getNamespace());
        itestCanDeleteElement(ld, element, true);
    }

    public void testCanDeleteElement3() throws Exception {
        LearningDesign ld = new LearningDesign(new File(pathTestData, LD_File1.fileLD));
        Element element = ld.getLDElement()
        				  .getChild("components", ld.getLDElement().getNamespace())
        				  .getChild("roles", ld.getLDElement().getNamespace());
        itestCanDeleteElement(ld, element, false);
    }

    // ---------------------------------------------------------------------------------------------

    public void testCanMoveElementUp1() throws Exception {
        LearningDesign ld = new LearningDesign(new File(pathTestData, LD_File1.fileLD));
        Element element = ld.getRootElement();
        itestCanMoveElementUp(ld, element, false);
    }

    public void testCanMoveElementUp2() throws Exception {
        LearningDesign ld = new LearningDesign(new File(pathTestData, LD_File1.fileLD));
        Element element = ld.getLDElement()
        				  .getChild("components", ld.getLDElement().getNamespace());
        itestCanMoveElementUp(ld, element, false);
    }

    public void testCanMoveElementUp3() throws Exception {
        LearningDesign ld = new LearningDesign(new File(pathTestData, LD_File1.fileLD));
        Element element = (Element)ld.getLDElement()
        				  .getChild("components", ld.getLDElement().getNamespace())
        				  .getChild("roles", ld.getLDElement().getNamespace())
        				  .getContent(new ElementFilter()).get(0);
        itestCanMoveElementUp(ld, element, false);
    }

    public void testCanMoveElementUp4() throws Exception {
        LearningDesign ld = new LearningDesign(new File(pathTestData, LD_File1.fileLD));
        Element element = (Element)ld.getLDElement()
        				  .getChild("components", ld.getLDElement().getNamespace())
        				  .getChild("roles", ld.getLDElement().getNamespace())
        				  .getContent(new ElementFilter()).get(1);
        itestCanMoveElementUp(ld, element, true);
    }

    /**
     * Test for mixture of "choice" and "sequence" types
     */
    public void testCanMoveElementUp5() throws Exception {
        LearningDesign ld = new LearningDesign(new File(pathTestData, LD_File1.fileLD));
        Element parentElement = ld.getLDElement()
        				  .getChild("components", ld.getLDElement().getNamespace())
        				  .getChild("activities", ld.getLDElement().getNamespace())
        				  .getChild("activity-structure", ld.getLDElement().getNamespace());
        				  
        Element element = (Element)parentElement.getContent(new ElementFilter()).get(0);
        assertEquals("Not the right Element", "AS1", element.getText()); 
        itestCanMoveElementUp(ld, element, false);

        element = (Element)parentElement.getContent(new ElementFilter()).get(1);
        assertEquals("Not the right Element", "LD-235E471A-4957-3B78-5567-BAB6E1794D37", element.getAttributeValue("ref")); 
        itestCanMoveElementUp(ld, element, false);
        
        element = (Element)parentElement.getContent(new ElementFilter()).get(2);
        assertEquals("Not the right Element", "LD-BF98979F-3CE0-9CFC-549C-B6CECB273AA8", element.getAttributeValue("ref")); 
        itestCanMoveElementUp(ld, element, true);

        element = (Element)parentElement.getContent(new ElementFilter()).get(3);
        assertEquals("Not the right Element", "LD-675B5FA1-90D0-E667-3285-D10E0BDEB4D2", element.getAttributeValue("ref")); 
        itestCanMoveElementUp(ld, element, false);

        element = (Element)parentElement.getContent(new ElementFilter()).get(4);
        assertEquals("Not the right Element", "LD-C4E6964B-8144-5F19-B3F5-867463779632", element.getAttributeValue("ref")); 
        itestCanMoveElementUp(ld, element, true);

        element = (Element)parentElement.getContent(new ElementFilter()).get(5);
        assertEquals("Not the right Element", "LD-56BEA4C2-1370-BF88-1864-F2AC9BEC3C96", element.getAttributeValue("ref")); 
        itestCanMoveElementUp(ld, element, true);

        element = (Element)parentElement.getContent(new ElementFilter()).get(6);
        assertEquals("Not the right Element", "LD-875CA2C5-892D-2F8B-D8A4-62F62E331979", element.getAttributeValue("ref")); 
        itestCanMoveElementUp(ld, element, true);

        element = (Element)parentElement.getContent(new ElementFilter()).get(7);
        assertEquals("Not the right Element", "LD-010BF4DB-FCD0-7B8A-18FE-DB4A6A96E1F8", element.getAttributeValue("ref")); 
        itestCanMoveElementUp(ld, element, true);
    }

    // ---------------------------------------------------------------------------------------------

    public void testCanMoveElementDown1() throws Exception {
        LearningDesign ld = new LearningDesign(new File(pathTestData, LD_File1.fileLD));
        Element element = ld.getLDElement();
        itestCanMoveElementDown(ld, element, false);
    }

    public void testCanMoveElementDown2() throws Exception {
        LearningDesign ld = new LearningDesign(new File(pathTestData, LD_File1.fileLD));
        Element element = ld.getLDElement()
        				  .getChild("components", ld.getLDElement().getNamespace());
        itestCanMoveElementDown(ld, element, false);
    }

    public void testCanMoveElementDown3() throws Exception {
        LearningDesign ld = new LearningDesign(new File(pathTestData, LD_File1.fileLD));
        Element element = (Element)ld.getLDElement()
        				  .getChild("components", ld.getLDElement().getNamespace())
        				  .getChild("roles", ld.getLDElement().getNamespace())
        				  .getContent(new ElementFilter()).get(0);
        itestCanMoveElementDown(ld, element, true);
    }

    public void testCanMoveElementDown4() throws Exception {
        LearningDesign ld = new LearningDesign(new File(pathTestData, LD_File1.fileLD));
        Element element = (Element)ld.getLDElement()
        				  .getChild("components", ld.getLDElement().getNamespace())
        				  .getChild("roles", ld.getLDElement().getNamespace())
        				  .getContent(new ElementFilter()).get(1);
        itestCanMoveElementDown(ld, element, false);
    }

    /**
     * Test for mixture of "choice" and "sequence" types
     */
    public void testCanMoveElementDown5() throws Exception {
        LearningDesign ld = new LearningDesign(new File(pathTestData, LD_File1.fileLD));
        Element parentElement = ld.getLDElement()
        				  .getChild("components", ld.getLDElement().getNamespace())
        				  .getChild("activities", ld.getLDElement().getNamespace())
        				  .getChild("activity-structure", ld.getLDElement().getNamespace());
        				  
        Element element = (Element)parentElement.getContent(new ElementFilter()).get(0);
        assertEquals("Not the right Element", "AS1", element.getText()); 
        itestCanMoveElementDown(ld, element, false);

        element = (Element)parentElement.getContent(new ElementFilter()).get(1);
        assertEquals("Not the right Element", "LD-235E471A-4957-3B78-5567-BAB6E1794D37", element.getAttributeValue("ref")); 
        itestCanMoveElementDown(ld, element, true);
        
        element = (Element)parentElement.getContent(new ElementFilter()).get(2);
        assertEquals("Not the right Element", "LD-BF98979F-3CE0-9CFC-549C-B6CECB273AA8", element.getAttributeValue("ref")); 
        itestCanMoveElementDown(ld, element, false);

        element = (Element)parentElement.getContent(new ElementFilter()).get(3);
        assertEquals("Not the right Element", "LD-675B5FA1-90D0-E667-3285-D10E0BDEB4D2", element.getAttributeValue("ref")); 
        itestCanMoveElementDown(ld, element, true);

        element = (Element)parentElement.getContent(new ElementFilter()).get(4);
        assertEquals("Not the right Element", "LD-C4E6964B-8144-5F19-B3F5-867463779632", element.getAttributeValue("ref")); 
        itestCanMoveElementDown(ld, element, true);

        element = (Element)parentElement.getContent(new ElementFilter()).get(5);
        assertEquals("Not the right Element", "LD-56BEA4C2-1370-BF88-1864-F2AC9BEC3C96", element.getAttributeValue("ref")); 
        itestCanMoveElementDown(ld, element, true);

        element = (Element)parentElement.getContent(new ElementFilter()).get(6);
        assertEquals("Not the right Element", "LD-875CA2C5-892D-2F8B-D8A4-62F62E331979", element.getAttributeValue("ref")); 
        itestCanMoveElementDown(ld, element, true);

        element = (Element)parentElement.getContent(new ElementFilter()).get(7);
        assertEquals("Not the right Element", "LD-010BF4DB-FCD0-7B8A-18FE-DB4A6A96E1F8", element.getAttributeValue("ref")); 
        itestCanMoveElementDown(ld, element, false);
    }

    // ---------------------------------------------------------------------------------------------

    public void testAddAttribute() throws Exception {
        LearningDesign ld = createNewLD();
        SchemaAttribute schemaAttribute = ld.getSchemaController()
        								  .getSchemaModel()
        								  .getRootElement()
        								  .getChild("organizations")
        								  .getChild("imsld:learning-design")
        								  .getChild("learning-objectives")
        								  .getChild("item")
        								  .getSchemaAttribute("isvisible");

        itestAddAttribute(ld, ld.getRootElement(), schemaAttribute);
    }

    /**
     * Add an already existing one - throws Exception
     */
    public void testAddAttribute2() throws Exception {
        LearningDesign ld = new LearningDesign(new File(pathTestData, LD_File1.fileLD));
        SchemaAttribute schemaAttribute = ld.getSchemaController()
        								  .getSchemaModel()
        								  .getRootElement()
        								  .getChild("organizations")
        								  .getChild("imsld:learning-design")
        								  .getChild("components")
        								  .getChild("roles")
        								  .getChild("learner")
        								  .getSchemaAttribute("identifier");

        Element element = ld.getLDElement()
						  .getChild("components", ld.getLDElement().getNamespace())
						  .getChild("roles", ld.getLDElement().getNamespace())
						  .getChild("learner", ld.getLDElement().getNamespace());

		try {
		    itestAddAttribute(ld, element, schemaAttribute);
		    fail("Should have thrown IllegalAddException");
		}
		catch(IllegalAddException ex) {
		    assertTrue(true);
		}
    }

    // ---------------------------------------------------------------------------------------------

    /**
     * Test that the level attribute is added with default value
     */
    public void testAddAttributeWithDefaultValue1() throws Exception {
        LearningDesign ld = createNewLD();

        Attribute att = ld.getLDElement().getAttribute(LD_Core.LEVEL);
        assertNotNull("Default Attribute was null", att);
        assertEquals("Default Attribute value was wrong", "A", att.getValue());
    }

    // ---------------------------------------------------------------------------------------------

    public void testGetInsertPositionOfElement_ElementElement1() throws Exception {
        LearningDesign ld = createNewLD();
        
        Element prereq = new Element("prerequisites", ld.getLDElement().getNamespace());
        itestGetInsertPositionOfElement_ElementElement(ld, ld.getLDElement(), prereq, 0);
        ld.addElement(this, ld.getLDElement(), prereq, false);
        
        Element title = new Element("title", ld.getLDElement().getNamespace());
        itestGetInsertPositionOfElement_ElementElement(ld, ld.getLDElement(), title, 0);
        ld.addElement(this, ld.getLDElement(), title, false);

        Element lo = new Element("learning-objectives", ld.getLDElement().getNamespace());
        itestGetInsertPositionOfElement_ElementElement(ld, ld.getLDElement(), lo, 1);
        ld.addElement(this, ld.getLDElement(), lo, false);
        
        Element md = new Element("metadata", ld.getLDElement().getNamespace());
        itestGetInsertPositionOfElement_ElementElement(ld, ld.getLDElement(), md, 5);
    }

    public void testGetInsertPositionOfElement_ElementElement2() throws Exception {
        LearningDesign ld = createNewLD();
        
        Element prereq = new Element("prerequisites", ld.getLDElement().getNamespace());
        itestGetInsertPositionOfElement_ElementElement(ld, ld.getLDElement(), prereq, 0);
        ld.addElement(this, ld.getLDElement(), prereq, false);
        
        Element title = new Element("title", ld.getLDElement().getNamespace());
        itestGetInsertPositionOfElement_ElementElement(ld, ld.getLDElement(), title, 0);
        ld.addElement(this, ld.getLDElement(), title, false);
        
        Element ponk = new Element("ponk");
        itestGetInsertPositionOfElement_ElementElement(ld, ld.getLDElement(), ponk, 4);
    }

    // ---------------------------------------------------------------------------------------------

    public void testGetInsertPositionOfElement_ElementSchemaElement() throws Exception {
        LearningDesign ld = createNewLD();
        
        SchemaElement schemaElement = ld.getSchemaController()
		  							  .getSchemaModel()
		  							  .getRootElement()
		  							  .getChild("organizations")
		  							  .getChild("imsld:learning-design")
		  							  .getChild("prerequisites");
        itestGetInsertPositionOfElement_ElementSchemaElement(ld, ld.getLDElement(), schemaElement, 0);
        ld.addElementBySchema(this, ld.getLDElement(), schemaElement, false);
        
        schemaElement = ld.getSchemaController()
        				.getSchemaModel()
        				.getRootElement()
        				.getChild("organizations")
        				.getChild("imsld:learning-design")
        				.getChild("title");
        itestGetInsertPositionOfElement_ElementSchemaElement(ld, ld.getLDElement(), schemaElement, 0);
        ld.addElementBySchema(this, ld.getLDElement(), schemaElement, false);
        
        schemaElement = ld.getSchemaController()
        				.getSchemaModel()
        				.getRootElement()
        				.getChild("organizations")
        				.getChild("imsld:learning-design")
        				.getChild("learning-objectives");
        itestGetInsertPositionOfElement_ElementSchemaElement(ld, ld.getLDElement(), schemaElement, 1);
        ld.addElementBySchema(this, ld.getLDElement(), schemaElement, false);
        
        schemaElement = ld.getSchemaController()
        				.getSchemaModel()
        				.getRootElement()
        				.getChild("organizations")
        				.getChild("imsld:learning-design")
        				.getChild("metadata");
        itestGetInsertPositionOfElement_ElementSchemaElement(ld, ld.getLDElement(), schemaElement, 5);
        ld.addElementBySchema(this, ld.getLDElement(), schemaElement, false);
    }

    // ---------------------------------------------------------------------------------------------

    public void testGetInsertPositionOfAttribute() throws Exception {
        LearningDesign ld = new LearningDesign(new File(pathTestData, LD_File1.fileLD));
        
        SchemaAttribute schemaAttribute = ld.getSchemaController()
        								  .getSchemaModel()
        								  .getRootElement()
        								  .getChild("organizations")
        								  .getChild("imsld:learning-design")
        								  .getChild("components")
        								  .getChild("roles")
        								  .getChild("learner")
        								  .getSchemaAttribute("match-persons");
        
        Element element = ld.getLDElement()
        				  .getChild("components", ld.getLDElement().getNamespace())
        				  .getChild("roles", ld.getLDElement().getNamespace())
        				  .getChild("learner", ld.getLDElement().getNamespace());
        
        itestGetInsertPositionOfAttribute(ld, element, schemaAttribute, 2);
    }

    // ---------------------------------------------------------------------------------------------

    public void testSetUndoHandler() throws Exception {
        LearningDesign ld = createNewLD();
        itestSetUndoHandler(ld, new UndoHandler());
    }

    // ---------------------------------------------------------------------------------------------

}
