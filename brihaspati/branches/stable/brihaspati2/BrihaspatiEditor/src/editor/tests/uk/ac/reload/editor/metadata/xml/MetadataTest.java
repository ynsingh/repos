
package uk.ac.reload.editor.metadata.xml;

import java.io.File;
import java.io.FileNotFoundException;

import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.IllegalAddException;
import org.jdom.Namespace;
import org.jdom.filter.ElementFilter;

import uk.ac.reload.diva.undo.UndoHandler;
import uk.ac.reload.editor.contentpackaging.xml.ContentPackage;
import uk.ac.reload.editor.metadata.MD_EditorHandler;
import uk.ac.reload.jdom.XMLDocumentClipboard;
import uk.ac.reload.jdom.XMLPath;
import uk.ac.reload.jdom.XMLUtils;
import uk.ac.reload.moonunit.SchemaDocumentAbstractTest;
import uk.ac.reload.moonunit.schema.SchemaAttribute;
import uk.ac.reload.moonunit.schema.SchemaElement;
import uk.ac.reload.testsupport.cp.CP_Package1;
import uk.ac.reload.testsupport.md.MD_File1;


/**
 * MetadataTest
 * 
 * @author Phillip Beauvoir
 * @version $Id: MetadataTest.java,v 1.1 1998/03/26 15:50:37 ynsingh Exp $
 */
public class MetadataTest extends SchemaDocumentAbstractTest {

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

    // ---------------------------------------------------------------------------------------------

    public void testMetadata_booleanMD_SchemaController1() throws Exception {
        Metadata md = new Metadata(true, new MD121_SchemaController());
        assertNotNull("Metadata is null", md);
        assertNotNull("SchemaController is null", md.getSchemaController());
        assertNotNull("Metadata doc is null", md.getDocument());
        assertTrue("Metadata doc should have Root Element", md.getDocument().hasRootElement());
        assertTrue("Metadata should be standalone", md.isStandalone());
        assertFalse("Metadata should not be dirty", md.isDirty());
    }

    public void testMetadata_booleanMD_SchemaController2() throws Exception {
        Metadata md = new Metadata(false, (MD121_SchemaController)null);
        assertNotNull("Metadata is null", md);
        assertNull("SchemaController should be null", md.getSchemaController());
        assertNotNull("Metadata doc is null", md.getDocument());
        assertFalse("Metadata doc should not have Root Element", md.getDocument().hasRootElement());
        assertFalse("Metadata should not be standalone", md.isStandalone());
        assertFalse("Metadata should not be dirty", md.isDirty());
    }

    // ---------------------------------------------------------------------------------------------

    public void testMetadata_booleanDocument1() throws Exception {
        Metadata md = new Metadata(true, new Document());
        assertNotNull("Metadata is null", md);
        assertNotNull("SchemaController is null", md.getSchemaController());
        assertNotNull("Metadata doc is null", md.getDocument());
        assertTrue("Metadata should be standalone", md.isStandalone());
        assertFalse("Metadata should not be dirty", md.isDirty());
    }

    public void testMetadata_booleanDocument2() throws Exception {
        try {
            Metadata md = new Metadata(true, (Document)null);
            fail("Should have thrown NullPointerException");
        }
        catch(NullPointerException ex) {
            assertTrue(true);
        }
    }

    // ---------------------------------------------------------------------------------------------

    public void testMetadata_File1() throws Exception {
        Metadata md = new Metadata(new File(pathTestData, MD_File1.fileMD));
        assertNotNull("Metadata is null", md);
        assertTrue("SchemaController is wrong", md.getSchemaController() instanceof MD122_SchemaController);
        assertNotNull("Metadata doc is null", md.getDocument());
        assertTrue("Metadata should be standalone", md.isStandalone());
    }

    public void testMetadata_File2() throws Exception {
        try {
            Metadata md = new Metadata(null);
            fail("Should have thrown NullPointerException");
        }
        catch(NullPointerException ex) {
            assertTrue(true);
        }
    }

    public void testMetadata_File3() throws Exception {
        try {
            Metadata md = new Metadata(new File("bogus_file.xml"));
            fail("Should have thrown FileNotFoundException");
        }
        catch(FileNotFoundException ex) {
            assertTrue(true);
        }
    }

    // ---------------------------------------------------------------------------------------------

    public void testLoadSchemaControllerInstance() throws Exception {
        Metadata md = new Metadata(true, new MD121_SchemaController());
        assertTrue("SchemaController is wrong", md.getSchemaController() instanceof MD121_SchemaController);
    }

    // ---------------------------------------------------------------------------------------------

    public void testCreateEmbeddedMetadata() throws Exception {
        Metadata md = new Metadata(true, new MD121_SchemaController());
        Metadata md2 = md.createEmbeddedMetadata();
        
        assertNotNull("Metadata is null", md2);
        assertNotNull("SchemaController is null", md2.getSchemaController());
        assertNotSame("Documents should be different", md.getDocument(), md2.getDocument());
        assertFalse("Metadata should not be standalone", md2.isStandalone());
        assertFalse("Metadata should not be dirty", md2.isDirty());
        assertTrue("Attributes should be empty", md2.getDocument().getRootElement().getAttributes().isEmpty());
        assertEquals("Should have Namespace prefix", "imsmd",  md2.getDocument().getRootElement().getNamespacePrefix());
    }

    // ---------------------------------------------------------------------------------------------

    public void testCreateStandaloneMetadata() throws Exception {
        Metadata md = new Metadata(true, new MD121_SchemaController());
        Metadata md2 = md.createEmbeddedMetadata();
        Metadata md3 = md2.createStandaloneMetadata();
        
        assertNotNull("Metadata is null", md3);
        assertNotNull("SchemaController is null", md3.getSchemaController());
        assertNotSame("Documents should be different", md3.getDocument(), md2.getDocument());
        assertTrue("Metadata should be standalone", md3.isStandalone());
        assertFalse("Metadata should not be dirty", md3.isDirty());
        assertFalse("Attributes should not be empty", md3.getDocument().getRootElement().getAttributes().isEmpty());
        assertEquals("Should have no Namespace prefix", "",  md3.getDocument().getRootElement().getNamespacePrefix());
    }

    // ---------------------------------------------------------------------------------------------

    public void testGetComments() throws Exception {
        Metadata md = new Metadata(true, new MD121_SchemaController());
        assertNotNull("Comments are null", md.getComments());
    }

    // ---------------------------------------------------------------------------------------------

    public void testAddRootDeclarations() throws Exception {
        Metadata md = new Metadata(true, new MD121_SchemaController());
        Element root = md.getRootElement();
        assertEquals("Should have Schema Instance Namespace", XMLUtils.XSI_Namespace,
                root.getNamespace("xsi"));
        assertEquals("Should have Schema Location Attribute", "http://www.imsglobal.org/xsd/imsmd_rootv1p2p1 imsmd_rootv1p2p1.xsd",
                root.getAttribute(XMLUtils.XSI_SchemaLocation, XMLUtils.XSI_Namespace).getValue());
    }

    // ---------------------------------------------------------------------------------------------

    public void testIsStandalone() throws Exception {
        Metadata md = new Metadata(true, new MD121_SchemaController());
        assertTrue("Metadata should be standalone", md.isStandalone());
    }

    // ---------------------------------------------------------------------------------------------
    
    public void testGetRootNamespaceEmbedded() throws Exception {
        Metadata md = new Metadata(true, new MD121_SchemaController());
        assertEquals("Wrong Namespace Prefix", "imsmd",
                md.getRootNamespaceEmbedded().getPrefix());
        assertEquals("Wrong Namespace URI", MD_EditorHandler.IMSMD_NAMESPACE_121.getURI(),
                md.getRootNamespaceEmbedded().getURI());
    }

    // ---------------------------------------------------------------------------------------------
    
    public void testCanCopyElement1() throws Exception {
        Metadata md = new Metadata(true, new MD121_SchemaController());
        assertFalse("Should not be able to copy element", md.canCopyElement(md.getRootElement()));
    }

    public void testCanCopyElement2() throws Exception {
        Metadata md = new Metadata(new File(pathTestData, MD_File1.fileMD));
        Element element = md.getRootElement().getChild("general", md.getRootElement().getNamespace());
        assertTrue("Should be able to copy element", md.canCopyElement(element));
    }

    // ---------------------------------------------------------------------------------------------
    
    public void testCanCutElement1() throws Exception {
        Metadata md = new Metadata(new File(pathTestData, MD_File1.fileMD));
        Element element = md.getRootElement().getChild("general", md.getRootElement().getNamespace());
        assertFalse("Should not be able to cut element", md.canCutElement(md.getRootElement()));
    }

    public void testCanCutElement2() throws Exception {
        Metadata md = new Metadata(new File(pathTestData, MD_File1.fileMD));
        Element element = md.getRootElement().getChild("general", md.getRootElement().getNamespace());
        assertTrue("Should be able to cut element", md.canCutElement(element));
    }

    // ---------------------------------------------------------------------------------------------
    
    public void testCanPasteFromClipboard1() throws Exception {
        Metadata md = new Metadata(new File(pathTestData, MD_File1.fileMD));
        
        Element element = new Element("langstring", md.getRootElement().getNamespace());
        XMLDocumentClipboard.addCopiedElement(element);
        
        Element parentElement = md.getRootElement().getChild("general", md.getRootElement().getNamespace());
        parentElement = parentElement.getChild("title", md.getRootElement().getNamespace());
        
        assertTrue("Should be able to paste element", md.canPasteFromClipboard(parentElement));
    }

    public void testCanPasteFromClipboard2() throws Exception {
        Metadata md = new Metadata(new File(pathTestData, MD_File1.fileMD));
        
        Element element = new Element("title", md.getRootElement().getNamespace());
        XMLDocumentClipboard.addCopiedElement(element);
        
        Element parentElement = md.getRootElement().getChild("general", md.getRootElement().getNamespace());
        
        assertFalse("Should not be able to paste element", md.canPasteFromClipboard(parentElement));
    }

    public void testCanPasteFromClipboard3() throws Exception {
        Metadata md = new Metadata(new File(pathTestData, MD_File1.fileMD));
        Element parentElement = md.getRootElement().getChild("general", md.getRootElement().getNamespace());
        assertFalse("Should not be able to paste element", md.canPasteFromClipboard(parentElement));
    }

    // ---------------------------------------------------------------------------------------------
    // ---------------------------------- From SchemaDocument --------------------------------------
    // ---------------------------------------------------------------------------------------------
    
    public void testGetSchemaController() throws Exception {
        Metadata md = new Metadata(new File(pathTestData, MD_File1.fileMD));
        itestGetSchemaController(md, MD122_SchemaController.class);
    }
    
    // ---------------------------------------------------------------------------------------------
    
    public void testGetRootNamespace1() throws Exception {
        Metadata md = new Metadata(true, new MD121_SchemaController());
        itestGetRootNamespace(md, MD_EditorHandler.IMSMD_NAMESPACE_121);
    }

    public void testGetRootNamespace2() throws Exception {
        Metadata md = new Metadata(true, new MD121_SchemaController());
        Metadata md2 = md.createEmbeddedMetadata();
        itestGetRootNamespace(md2, Namespace.getNamespace("imsmd", MD_EditorHandler.IMSMD_NAMESPACE_121.getURI()));
    }

    // ---------------------------------------------------------------------------------------------

    public void testGetTargetNamespace1() throws Exception {
        Metadata md = new Metadata(true, new MD121_SchemaController());
        itestGetTargetNamespace(md, MD_EditorHandler.IMSMD_NAMESPACE_121);
    }

    public void testGetTargetNamespace2() throws Exception {
        Metadata md = new Metadata(true, new MD121_SchemaController());
        Metadata md2 = md.createEmbeddedMetadata();
        itestGetTargetNamespace(md2, MD_EditorHandler.IMSMD_NAMESPACE_121);
    }

    // ---------------------------------------------------------------------------------------------

    public void testGetElement1() throws Exception {
        Metadata md = new Metadata(new File(pathTestData, MD_File1.fileMD));
        SchemaElement schemaElement = md.getSchemaController()
                                      .getSchemaModel()
                                      .getRootElement();
        itestGetElement(md, schemaElement);
    }

    public void testGetElement2() throws Exception {
        Metadata md = new Metadata(new File(pathTestData, MD_File1.fileMD));
        SchemaElement schemaElement = md.getSchemaController()
        							  .getSchemaModel()
        							  .getRootElement()
        							  .getChild("general")
        							  .getChild("title")
        							  .getChild("langstring");
        itestGetElement(md, schemaElement);
    }

    // ---------------------------------------------------------------------------------------------

    public void testGetElements1() throws Exception {
        Metadata md = new Metadata(new File(pathTestData, MD_File1.fileMD));
        SchemaElement schemaElement = md.getSchemaController()
		                              .getSchemaModel()
									  .getRootElement();
        itestGetElements(md, schemaElement, 1);
    }
    
    public void testGetElements2() throws Exception {
        Metadata md = new Metadata(new File(pathTestData, MD_File1.fileMD));
        SchemaElement schemaElement = md.getSchemaController()
        							  .getSchemaModel()
        							  .getRootElement()
        							  .getChild("general");
        itestGetElements(md, schemaElement, 1);
    }

    public void testGetElements3() throws Exception {
        Metadata md = new Metadata(new File(pathTestData, MD_File1.fileMD));
        SchemaElement schemaElement = md.getSchemaController()
        							  .getSchemaModel()
        							  .getRootElement()
        							  .getChild("general")
        							  .getChild("keyword");
        itestGetElements(md, schemaElement, 2);
    }

    // ---------------------------------------------------------------------------------------------

    public void testAddCommentsToDocument() throws Exception {
        Metadata md = new Metadata(true, new MD121_SchemaController());
        itestAddCommentsToDocument(md);
    }
    
    // ---------------------------------------------------------------------------------------------

    public void testAddElementUniqueBySchema1() throws Exception {
        Metadata md = new Metadata(new File(pathTestData, MD_File1.fileMD));
        SchemaElement schemaElement = md.getSchemaController()
									  .getSchemaModel()
									  .getRootElement()
									  .getChild("general")
									  .getChild("title");
        itestAddElementUniqueBySchema(md, schemaElement);
    }

    public void testAddElementUniqueBySchema2() throws Exception {
        Metadata md = new Metadata(true, new MD121_SchemaController());
        SchemaElement schemaElement = md.getSchemaController()
									  .getSchemaModel()
									  .getRootElement()
									  .getChild("general")
									  .getChild("title")
									  .getChild("langstring");
        itestAddElementUniqueBySchema(md, schemaElement);
    }

    public void testAddElementUniqueBySchema3() throws Exception {
        Metadata md = new Metadata(new File(pathTestData, MD_File1.fileMD));
        SchemaElement schemaElement = md.getSchemaController()
									  .getSchemaModel()
									  .getRootElement();
        itestAddElementUniqueBySchema(md, schemaElement);
    }

    // ---------------------------------------------------------------------------------------------

    public void testAddElementUniqueBySchemaUndoable1() throws Exception {
        Metadata md = new Metadata(new File(pathTestData, MD_File1.fileMD));
        SchemaElement schemaElement = md.getSchemaController()
									  .getSchemaModel()
									  .getRootElement()
									  .getChild("general")
									  .getChild("title");
        itestAddElementUniqueBySchemaUndoable(md, schemaElement);
    }

    public void testAddElementUniqueBySchemaUndoable2() throws Exception {
        Metadata md = new Metadata(true, new MD121_SchemaController());
        SchemaElement schemaElement = md.getSchemaController()
									  .getSchemaModel()
									  .getRootElement()
									  .getChild("general")
									  .getChild("title")
									  .getChild("langstring");
        itestAddElementUniqueBySchemaUndoable(md, schemaElement);
    }

    public void testAddElementUniqueBySchemaUndoable3() throws Exception {
        Metadata md = new Metadata(new File(pathTestData, MD_File1.fileMD));
        SchemaElement schemaElement = md.getSchemaController()
									  .getSchemaModel()
									  .getRootElement();
        itestAddElementUniqueBySchemaUndoable(md, schemaElement);
    }

    // ---------------------------------------------------------------------------------------------

    public void testAddElementBySchemaUndoable() throws Exception {
        Metadata md = new Metadata(true, new MD121_SchemaController());
        SchemaElement schemaElement = md.getSchemaController()
									  .getSchemaModel()
									  .getRootElement()
									  .getChild("general");
        itestAddElementBySchemaUndoable(md, schemaElement);
    }

    // ---------------------------------------------------------------------------------------------

    /**
     * Add to MD embedded in CP
     */
    public void testAddElementBySchema1() throws Exception {
        ContentPackage cp = new ContentPackage(new File(pathTestData, CP_Package1.fileManifest));
        Element parentElement = cp.getRootElement()
        						.getChild("metadata", cp.getRootElement().getNamespace())
        						.getChild("lom", cp.getRootElement().getNamespace("imsmd"))
        						.getChild("general", cp.getRootElement().getNamespace("imsmd"))
        						.getChild("title", cp.getRootElement().getNamespace("imsmd"));
        
        SchemaElement schemaElement = new MD121_SchemaController().getSchemaModel()
        								  .getRootElement()
        								  .getChild("general")
        								  .getChild("title")
        								  .getChild("langstring");
        
        Element element = itestAddElementBySchema(cp, parentElement, schemaElement);
    }

    /**
     * All the bits got added
     */
    public void testAddElementBySchema2() throws Exception {
        Metadata md = new Metadata(true, new MD121_SchemaController());
        
        Element parentElement = new Element("general", md.getRootElement().getNamespace());
        md.getRootElement().addContent(parentElement);
        
        SchemaElement schemaElement = md.getSchemaController()
		  							  .getSchemaModel()
		  							  .getRootElement()
		  							  .getChild("general")
		  							  .getChild("title");
        
        Element element = itestAddElementBySchema(md, parentElement, schemaElement);
        
        assertEquals("Wrong Element", "title", element.getName());
        assertEquals("Wrong Parent", "general", element.getParent().getName());
        
        Element child = element.getChild("langstring", md.getRootElement().getNamespace());
        assertNotNull("No mandatory Element added", child);
        
        Attribute att = child.getAttribute("lang", Namespace.XML_NAMESPACE);
        assertNotNull("No lang Attribute added", att);
    }

    /**
     * All the bits got added
     */
    public void testAddElementBySchema3() throws Exception {
        Metadata md = new Metadata(true, new MD121_SchemaController());
        
        Element general = new Element("general", md.getRootElement().getNamespace());
        md.getRootElement().addContent(general);
        Element parentElement = new Element("title", md.getRootElement().getNamespace());
        general.addContent(parentElement);
        
        SchemaElement schemaElement = md.getSchemaController()
		  							  .getSchemaModel()
		  							  .getRootElement()
		  							  .getChild("general")
		  							  .getChild("title")
		  							  .getChild("langstring");
        
        Element element = itestAddElementBySchema(md, parentElement, schemaElement);
        
        assertEquals("Wrong Element", "langstring", element.getName());
        assertEquals("Wrong Parent", "title", element.getParent().getName());
        assertTrue("Element should have no children", element.getContent().isEmpty());
        
        Attribute att = element.getAttribute("lang", Namespace.XML_NAMESPACE);
        assertNotNull("No lang Attribute added", att);
    }

    // ---------------------------------------------------------------------------------------------

    public void testAddElementByXMLPath1() throws Exception {
        Metadata md = new Metadata(true, new MD121_SchemaController());
        
        XMLPath xmlPath = new XMLPath("general");
        Element element = itestAddElementByXMLPath(md, md.getRootElement(), xmlPath);
        assertEquals("Wrong Parent", "lom", element.getParent().getName());
        assertTrue("Element should have no children", element.getContent().isEmpty());
    }
    
    public void testAddElementByXMLPath2() throws Exception {
        Metadata md = new Metadata(true, new MD122_SchemaController());
        
        XMLPath xmlPath = new XMLPath("general/title/langstring");
        Element element = itestAddElementByXMLPath(md, md.getRootElement(), xmlPath);
        assertEquals("Wrong Parent", "title", element.getParent().getName());
        assertTrue("Element should have no children", element.getContent().isEmpty());
        
        Attribute att = element.getAttribute("lang", Namespace.XML_NAMESPACE);
        assertNotNull("No lang Attribute added", att);
    }

    // ---------------------------------------------------------------------------------------------
    
    public void testAddElementUndoable() throws Exception {
        Metadata md = new Metadata(true, new MD121_SchemaController());
        Element childElement = new Element("general", md.getRootElement().getNamespace());
        itestAddElementUndoable(md, md.getRootElement(), childElement);
    }

    // ---------------------------------------------------------------------------------------------

    public void testAddElement() throws Exception {
        Metadata md = new Metadata(true, new MD121_SchemaController());
        Element childElement = new Element("general", md.getRootElement().getNamespace());
        itestAddElement(md, md.getRootElement(), childElement);
    }

    // ---------------------------------------------------------------------------------------------

    public void testDeleteElementUndoable() throws Exception {
        Metadata md = new Metadata(true, new MD121_SchemaController());
        Element element = new Element("general", md.getRootElement().getNamespace());
        md.getRootElement().addContent(element);
        itestDeleteElementUndoable(md, element);
    }

    // ---------------------------------------------------------------------------------------------

    public void testCutElementUndoable() throws Exception {
        Metadata md = new Metadata(true, new MD121_SchemaController());
        Element element = new Element("general", md.getRootElement().getNamespace());
        md.getRootElement().addContent(element);
        itestCutElementUndoable(md, element);
    }

    // ---------------------------------------------------------------------------------------------

    public void testMoveElementUndoable() throws Exception {
        Metadata md = new Metadata(true, new MD121_SchemaController());
        Element general = new Element("general", md.getRootElement().getNamespace());
        md.getRootElement().addContent(general);
        Element element = new Element("title", md.getRootElement().getNamespace());
        general.addContent(element);
        
        itestMoveElementUndoable(md, element, md.getRootElement());
    }

    // ---------------------------------------------------------------------------------------------

    public void testCopyElementUndoable() throws Exception {
        Metadata md = new Metadata(true, new MD121_SchemaController());
        Element general = new Element("general", md.getRootElement().getNamespace());
        md.getRootElement().addContent(general);
        Element element = new Element("title", md.getRootElement().getNamespace());
        general.addContent(element);
        
        itestCopyElementUndoable(md, element, md.getRootElement());
    }

    // ---------------------------------------------------------------------------------------------

    public void testPasteElementUndoable() throws Exception {
        Metadata md = new Metadata(true, new MD121_SchemaController());
        Element general = new Element("general", md.getRootElement().getNamespace());
        md.getRootElement().addContent(general);
        Element element = new Element("title", md.getRootElement().getNamespace());
        general.addContent(element);
        
        itestPasteElementUndoable(md, element, md.getRootElement());
    }

    // ---------------------------------------------------------------------------------------------

    public void testCanAddElement1() throws Exception {
        Metadata md = new Metadata(new File(pathTestData, MD_File1.fileMD));
        SchemaElement schemaElement = md.getSchemaController()
        							  .getSchemaModel()
        							  .getRootElement()
        							  .getChild("general")
        							  .getChild("keyword");
        Element parentElement = md.getRootElement().getChild("general", md.getRootElement().getNamespace());        
        itestCanAddElement(md, parentElement, schemaElement, true);
    }

    public void testCanAddElement2() throws Exception {
        Metadata md = new Metadata(new File(pathTestData, MD_File1.fileMD));
        SchemaElement schemaElement = md.getSchemaController()
        							  .getSchemaModel()
        							  .getRootElement()
        							  .getChild("general");
        Element parentElement = md.getRootElement();
        itestCanAddElement(md, parentElement, schemaElement, false);
    }

    // ---------------------------------------------------------------------------------------------

    /**
     * Different Namespaces
     */
    public void testIsAllowedChild1() throws Exception {
        Metadata md = new Metadata(new File(pathTestData, MD_File1.fileMD));
        Element parentElement = md.getRootElement();
        Element childElement = new Element("ponk");
        itestIsAllowedChild(md, parentElement, childElement, false);
    }

    public void testIsAllowedChild2() throws Exception {
        Metadata md = new Metadata(new File(pathTestData, MD_File1.fileMD));
        Element parentElement = md.getRootElement();
        Element childElement = new Element("general", md.getRootElement().getNamespace());
        itestIsAllowedChild(md, parentElement, childElement, false);
    }

    public void testIsAllowedChild3() throws Exception {
        Metadata md = new Metadata(new File(pathTestData, MD_File1.fileMD));
        Element parentElement = md.getRootElement();
        Element childElement = new Element("annotation", md.getRootElement().getNamespace());
        itestIsAllowedChild(md, parentElement, childElement, true);
    }

    // ---------------------------------------------------------------------------------------------

    public void testCanDeleteElement1() throws Exception {
        Metadata md = new Metadata(new File(pathTestData, MD_File1.fileMD));
        Element element = md.getRootElement();
        itestCanDeleteElement(md, element, false);
    }

    public void testCanDeleteElement2() throws Exception {
        Metadata md = new Metadata(new File(pathTestData, MD_File1.fileMD));
        Element element = md.getRootElement()
        				  .getChild("general", md.getRootElement().getNamespace());
        itestCanDeleteElement(md, element, true);
    }

    public void testCanDeleteElement3() throws Exception {
        Metadata md = new Metadata(new File(pathTestData, MD_File1.fileMD));
        Element element = md.getRootElement()
        				  .getChild("general", md.getRootElement().getNamespace())
        				  .getChild("catalogentry", md.getRootElement().getNamespace())
        				  .getChild("catalog", md.getRootElement().getNamespace());
        itestCanDeleteElement(md, element, false);
    }

    // ---------------------------------------------------------------------------------------------

    public void testCanMoveElementUp1() throws Exception {
        Metadata md = new Metadata(new File(pathTestData, MD_File1.fileMD));
        Element element = md.getRootElement();
        itestCanMoveElementUp(md, element, false);
    }

    public void testCanMoveElementUp2() throws Exception {
        Metadata md = new Metadata(new File(pathTestData, MD_File1.fileMD));
        Element element = md.getRootElement()
        				  .getChild("general", md.getRootElement().getNamespace());
        itestCanMoveElementUp(md, element, false);
    }

    public void testCanMoveElementUp3() throws Exception {
        Metadata md = new Metadata(new File(pathTestData, MD_File1.fileMD));
        Element element = (Element)md.getRootElement()
        				  .getChild("general", md.getRootElement().getNamespace())
        				  .getChild("title", md.getRootElement().getNamespace())
        				  .getContent(new ElementFilter()).get(0);
        itestCanMoveElementUp(md, element, false);
    }

    public void testCanMoveElementUp4() throws Exception {
        Metadata md = new Metadata(new File(pathTestData, MD_File1.fileMD));
        Element element = (Element)md.getRootElement()
        				  .getChild("general", md.getRootElement().getNamespace())
        				  .getChild("title", md.getRootElement().getNamespace())
        				  .getContent(new ElementFilter()).get(1);
        itestCanMoveElementUp(md, element, true);
    }

    // ---------------------------------------------------------------------------------------------

    public void testCanMoveElementDown1() throws Exception {
        Metadata md = new Metadata(new File(pathTestData, MD_File1.fileMD));
        Element element = md.getRootElement();
        itestCanMoveElementDown(md, element, false);
    }

    public void testCanMoveElementDown2() throws Exception {
        Metadata md = new Metadata(new File(pathTestData, MD_File1.fileMD));
        Element element = md.getRootElement()
        				  .getChild("general", md.getRootElement().getNamespace());
        itestCanMoveElementDown(md, element, false);
    }

    public void testCanMoveElementDown3() throws Exception {
        Metadata md = new Metadata(new File(pathTestData, MD_File1.fileMD));
        Element element = (Element)md.getRootElement()
        				  .getChild("general", md.getRootElement().getNamespace())
        				  .getChild("title", md.getRootElement().getNamespace())
        				  .getContent(new ElementFilter()).get(0);
        itestCanMoveElementDown(md, element, true);
    }

    public void testCanMoveElementDown4() throws Exception {
        Metadata md = new Metadata(new File(pathTestData, MD_File1.fileMD));
        Element element = (Element)md.getRootElement()
        				  .getChild("general", md.getRootElement().getNamespace())
        				  .getChild("title", md.getRootElement().getNamespace())
        				  .getContent(new ElementFilter()).get(1);
        itestCanMoveElementDown(md, element, false);
    }

    // ---------------------------------------------------------------------------------------------

    public void testAddAttribute() throws Exception {
        Metadata md = new Metadata(true, new MD121_SchemaController());
        SchemaAttribute schemaAttribute = md.getSchemaController()
        								  .getSchemaModel()
        								  .getRootElement()
        								  .getChild("general")
        								  .getChild("title")
        								  .getChild("langstring")
        								  .getSchemaAttribute("xml:lang");

        itestAddAttribute(md, md.getRootElement(), schemaAttribute);
    }

    /**
     * Add an already existing one - throws Exception
     */
    public void testAddAttribute2() throws Exception {
        Metadata md = new Metadata(new File(pathTestData, MD_File1.fileMD));
        SchemaAttribute schemaAttribute = md.getSchemaController()
        								  .getSchemaModel()
        								  .getRootElement()
        								  .getChild("general")
        								  .getChild("title")
        								  .getChild("langstring")
        								  .getSchemaAttribute("xml:lang");

        Element element = md.getRootElement()
						  .getChild("general", md.getRootElement().getNamespace())
						  .getChild("title", md.getRootElement().getNamespace())
						  .getChild("langstring", md.getRootElement().getNamespace());

		try {
		    itestAddAttribute(md, element, schemaAttribute);
		    fail("Should have thrown IllegalAddException");
		}
		catch(IllegalAddException ex) {
		    assertTrue(true);
		}
    }

    // ---------------------------------------------------------------------------------------------

    public void testAddAttributeWithDefaultValue1() throws Exception {
        Metadata md = new Metadata(true, new MD121_SchemaController());
        
        Element element = md.getRootElement();
        element.addContent(element = new Element("general", md.getRootElement().getNamespace()));
        element.addContent(element = new Element("title", md.getRootElement().getNamespace()));
        element.addContent(element = new Element("langstring", md.getRootElement().getNamespace()));

        itestAddAttributeWithDefaultValue(md, element, "xml:lang");
    }
    
    // ---------------------------------------------------------------------------------------------

    public void testGetInsertPositionOfElement_ElementElement1() throws Exception {
        Metadata md = new Metadata(true, new MD121_SchemaController());
        
        Element technical = new Element("technical", md.getRootElement().getNamespace());
        itestGetInsertPositionOfElement_ElementElement(md, md.getRootElement(), technical, 0);
        md.addElement(this, md.getRootElement(), technical, false);
        
        Element general = new Element("general", md.getRootElement().getNamespace());
        itestGetInsertPositionOfElement_ElementElement(md, md.getRootElement(), general, 0);
        md.addElement(this, md.getRootElement(), general, false);
        
        Element lifecycle = new Element("lifecycle", md.getRootElement().getNamespace());
        itestGetInsertPositionOfElement_ElementElement(md, md.getRootElement(), lifecycle, 1);
        md.addElement(this, md.getRootElement(), lifecycle, false);

        Element classification = new Element("classification", md.getRootElement().getNamespace());
        itestGetInsertPositionOfElement_ElementElement(md, md.getRootElement(), classification, 3);
        md.addElement(this, md.getRootElement(), classification, false);
    }

    public void testGetInsertPositionOfElement_ElementElement2() throws Exception {
        Metadata md = new Metadata(true, new MD121_SchemaController());
        
        Element technical = new Element("technical", md.getRootElement().getNamespace());
        itestGetInsertPositionOfElement_ElementElement(md, md.getRootElement(), technical, 0);
        md.addElement(this, md.getRootElement(), technical, false);
        
        Element general = new Element("general", md.getRootElement().getNamespace());
        itestGetInsertPositionOfElement_ElementElement(md, md.getRootElement(), general, 0);
        md.addElement(this, md.getRootElement(), general, false);
        
        Element lifecycle = new Element("ponk");
        itestGetInsertPositionOfElement_ElementElement(md, md.getRootElement(), lifecycle, 2);
    }

    // ---------------------------------------------------------------------------------------------

    public void testGetInsertPositionOfElement_ElementSchemaElement() throws Exception {
        Metadata md = new Metadata(true, new MD121_SchemaController());
        
        SchemaElement schemaElement = md.getSchemaController()
		  							  .getSchemaModel()
		  							  .getRootElement()
		  							  .getChild("technical");
        itestGetInsertPositionOfElement_ElementSchemaElement(md, md.getRootElement(), schemaElement, 0);
        md.addElementBySchema(this, md.getRootElement(), schemaElement, false);
        
        schemaElement = md.getSchemaController()
        				.getSchemaModel()
        				.getRootElement()
        				.getChild("general");
        itestGetInsertPositionOfElement_ElementSchemaElement(md, md.getRootElement(), schemaElement, 0);
        md.addElementBySchema(this, md.getRootElement(), schemaElement, false);
        
        schemaElement = md.getSchemaController()
        				.getSchemaModel()
        				.getRootElement()
        				.getChild("lifecycle");
        itestGetInsertPositionOfElement_ElementSchemaElement(md, md.getRootElement(), schemaElement, 1);
        md.addElementBySchema(this, md.getRootElement(), schemaElement, false);
        
        schemaElement = md.getSchemaController()
        				.getSchemaModel()
        				.getRootElement()
        				.getChild("classification");
        itestGetInsertPositionOfElement_ElementSchemaElement(md, md.getRootElement(), schemaElement, 3);
        md.addElementBySchema(this, md.getRootElement(), schemaElement, false);
    }

    // ---------------------------------------------------------------------------------------------

    public void testGetInsertPositionOfAttribute() throws Exception {
        Metadata md = new Metadata(new File(pathTestData, MD_File1.fileMD));
        
        SchemaAttribute schemaAttribute = md.getSchemaController()
        								  .getSchemaModel()
        								  .getRootElement()
        								  .getChild("general")
        								  .getChild("title")
        								  .getChild("langstring")
        								  .getSchemaAttribute("xml:lang");
        
        Element element = md.getRootElement()
        				  .getChild("general", md.getRootElement().getNamespace())
        				  .getChild("title", md.getRootElement().getNamespace())
        				  .getChild("langstring", md.getRootElement().getNamespace());
        
        element.setAttributes(null);
        
        itestGetInsertPositionOfAttribute(md, element, schemaAttribute, 0);
    }

    // ---------------------------------------------------------------------------------------------

    public void testSetUndoHandler() throws Exception {
        Metadata md = new Metadata(true, new MD121_SchemaController());
        itestSetUndoHandler(md, new UndoHandler());
    }

}
