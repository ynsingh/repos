package uk.ac.reload.editor.contentpackaging.xml;

import java.awt.dnd.DnDConstants;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.IllegalAddException;
import org.jdom.filter.ElementFilter;

import uk.ac.reload.diva.undo.UndoHandler;
import uk.ac.reload.diva.util.FileUtils;
import uk.ac.reload.editor.contentpackaging.CP_EditorHandler;
import uk.ac.reload.editor.metadata.MD_EditorHandler;
import uk.ac.reload.editor.metadata.xml.MD11_SchemaController;
import uk.ac.reload.editor.metadata.xml.MD121_SchemaController;
import uk.ac.reload.editor.metadata.xml.MD122_SchemaController;
import uk.ac.reload.jdom.XMLPath;
import uk.ac.reload.jdom.XMLUtils;
import uk.ac.reload.moonunit.SchemaDocumentAbstractTest;
import uk.ac.reload.moonunit.contentpackaging.CP_Core;
import uk.ac.reload.moonunit.schema.SchemaAttribute;
import uk.ac.reload.moonunit.schema.SchemaElement;
import uk.ac.reload.testsupport.FileSupport;
import uk.ac.reload.testsupport.cp.CP_Package1;


/**
 * ContentPackageTest
 * 
 * @author Phillipus
 * @version $Id: ContentPackageTest.java,v 1.1 1998/03/26 15:50:37 ynsingh Exp $
 */
public class ContentPackageTest extends SchemaDocumentAbstractTest {

    String pathTestData;
    File file1;
    
    protected void setUp() throws Exception {
        // See if we have a testdata dir set from an Ant script
        pathTestData = System.getProperty("testdata.dir");
        if(pathTestData == null) {
            // Set a default
            pathTestData = "../junit-tests";
        }

        file1 = new File(pathTestData, CP_Package1.fileManifest);

        // Set Properties file to test properties
		System.setProperty("editor.properties.file", "uk.ac.reload.editor.testproperties.rb");
    }

    protected void tearDown() throws Exception {
        // Clean up
        FileUtils.deleteFolder(FileSupport.getMainTestFolder());
    }

    // ---------------------------------------------------------------------------------------------

    public void testContentPackage_FileCP_SchemaControllerMD_SchemaController1() throws Exception {
        File folder = FileSupport.getTempFolder("cp-test");
        ContentPackage cp = new ContentPackage(folder, new CP112_SchemaController(),
                new MD121_SchemaController());
        assertNotNull("ContentPackage is null", cp);
        assertNotNull("SchemaController is null", cp.getSchemaController());
        assertNotNull("ContentPackage doc is null", cp.getDocument());
        assertTrue("ContentPackage doc should have Root Element", cp.getDocument().hasRootElement());
        assertFalse("ContentPackage should not be dirty", cp.isDirty());
    }
    
    public void testContentPackage_FileCP_SchemaControllerMD_SchemaController2() throws Exception {
        try {
            ContentPackage cp = new ContentPackage(null, new CP112_SchemaController(),
                    new MD121_SchemaController());
            fail("Should have thrown NullPointerException");
        }
        catch(NullPointerException ex) {
            assertTrue(true);
        }
    }
    
    public void testContentPackage_FileCP_SchemaControllerMD_SchemaController3() throws Exception {
        try {
            File folder = FileSupport.getTempFolder("cp-test");
            ContentPackage cp = new ContentPackage(folder, null,
                    new MD121_SchemaController());
            fail("Should have thrown NullPointerException");
        }
        catch(NullPointerException ex) {
            assertTrue(true);
        }
    }

    public void testContentPackage_FileCP_SchemaControllerMD_SchemaController4() throws Exception {
        try {
            File folder = FileSupport.getTempFolder("cp-test");
            ContentPackage cp = new ContentPackage(folder, new CP112_SchemaController(), null);
            fail("Should have thrown NullPointerException");
        }
        catch(NullPointerException ex) {
            assertTrue(true);
        }
    }

    // ---------------------------------------------------------------------------------------------

    public void testContentPackage_File1() throws Exception {
        ContentPackage cp = new ContentPackage(file1);
        assertNotNull("ContentPackage is null", cp);
        assertTrue("SchemaController is wrong", cp.getSchemaController() instanceof CP113_SchemaController);
        assertNotNull("ContentPackage doc is null", cp.getDocument());
        assertFalse("ContentPackage should not be dirty", cp.isDirty());
    }

    public void testContentPackage_File2() throws Exception {
        try {
            ContentPackage cp = new ContentPackage(null);
            fail("Should have thrown NullPointerException");
        }
        catch(NullPointerException ex) {
            assertTrue(true);
        }
    }

    public void testContentPackage_File3() throws Exception {
        try {
            ContentPackage cp = new ContentPackage(new File("bogus_file.xml"));
            fail("Should have thrown FileNotFoundException");
        }
        catch(FileNotFoundException ex) {
            assertTrue(true);
        }
    }

    // ---------------------------------------------------------------------------------------------

    public void testInit1() throws Exception {
        File folder = FileSupport.getTempFolder("cp-test");
        ContentPackage cp = new ContentPackage(folder, new CP113_SchemaController(),
                new MD122_SchemaController());

        assertFalse("ContentPackage should not be dirty", cp.isDirty());
        
        // Test all things that are added - attributes, comments etc
        assertNotNull("Comments are null", cp.getComments());
        
        Element root = cp.getRootElement();
        assertNotNull("Root is null", root);
        assertEquals("Wrong root Namespace", CP_EditorHandler.IMSCP_NAMESPACE_113, root.getNamespace());
        assertEquals("Wrong root name", "manifest", root.getName());
        assertEquals("Wrong MD Namespace", MD_EditorHandler.IMSMD_NAMESPACE_122, root.getNamespace("imsmd"));
        assertEquals("No XSI Namespace", XMLUtils.XSI_Namespace, root.getNamespace("xsi"));
        assertNotNull("Identifier Attribute is null", root.getAttribute(CP_Core.IDENTIFIER));
        assertEquals("Should have Schema Location Attribute",
                "http://www.imsglobal.org/xsd/imscp_v1p1 imscp_v1p1.xsd http://www.imsglobal.org/xsd/imsmd_v1p2 imsmd_v1p2p2.xsd",
                root.getAttribute(XMLUtils.XSI_SchemaLocation, XMLUtils.XSI_Namespace).getValue());
        
        assertNotNull("No organizations element added", root.getChild("organizations", CP_EditorHandler.IMSCP_NAMESPACE_113));
        assertNotNull("No resources element added", root.getChild("resources", CP_EditorHandler.IMSCP_NAMESPACE_113));
    }
    
    public void testInit2() throws Exception {
        File folder = FileSupport.getTempFolder("cp-test");
        ContentPackage cp = new ContentPackage(folder, new CP113_SchemaController(),
                new MD11_SchemaController());

        assertFalse("ContentPackage should not be dirty", cp.isDirty());
        
        // Test all things that are added - attributes, comments etc
        assertNotNull("Comments are null", cp.getComments());
        
        Element root = cp.getRootElement();
        assertNotNull("Root is null", root);
        assertEquals("Wrong root Namespace", CP_EditorHandler.IMSCP_NAMESPACE_113, root.getNamespace());
        assertEquals("Wrong MD Namespace", MD_EditorHandler.IMSMD_NAMESPACE_11, root.getNamespace("imsmd"));
        assertEquals("No XSI Namespace", XMLUtils.XSI_Namespace, root.getNamespace("xsi"));
        assertNotNull("Identifier Attribute is null", root.getAttribute(CP_Core.IDENTIFIER));
        assertEquals("Should have Schema Location Attribute",
                "http://www.imsglobal.org/xsd/imscp_v1p1 imscp_v1p1.xsd http://www.imsproject.org/xsd/ims_md_rootv1p1 ims_md_rootv1p1.xsd",
                root.getAttribute(XMLUtils.XSI_SchemaLocation, XMLUtils.XSI_Namespace).getValue());
    }

    // ---------------------------------------------------------------------------------------------

    public void testGetComments() throws Exception {
        File folder = FileSupport.getTempFolder("cp-test");
        ContentPackage cp = new ContentPackage(folder, new CP112_SchemaController(),
                new MD121_SchemaController());
        assertNotNull("Comments are null", cp.getComments());
    }

    // ---------------------------------------------------------------------------------------------

    // TO DO write these tests
    
    public void _testImportManifest() {
        
    }
    
    // ---------------------------------------------------------------------------------------------

    public void _testExportManifest() {
        
    }
    
    // ---------------------------------------------------------------------------------------------

    public void testSaveDocumentAs() throws Exception {
        ContentPackage cp = new ContentPackage(file1);
        assertNotNull("ContentPackage is null", cp);

        File targetFolder = FileSupport.getTempFolder("cp-test");
        cp.saveDocumentAs(targetFolder, null);
        
        // Contents same
        // Delete the imsmanifest.xml as it will be different
        cp.getFile().delete();
        FileSupport.checkSourceAndTargetFolderSame(cp.getProjectFolder(), file1.getParentFile());
    }
    
    // ---------------------------------------------------------------------------------------------

    public void testGetProjectName() throws Exception {
        ContentPackage cp = new ContentPackage();
        cp.setFile(file1);
        assertEquals("CP Project Name is wrong", "package1", cp.getProjectName());
    }
    
    // ---------------------------------------------------------------------------------------------

    public void testGetProjectFolder() throws Exception {
        ContentPackage cp = new ContentPackage();
        cp.setFile(file1);
        assertEquals("CP Project Folder is wrong", file1.getParentFile(), cp.getProjectFolder());
    }
    
    // ---------------------------------------------------------------------------------------------

    // TO DO write this test
    
    public void _testGetMetadataLomElement() {
        
    }
    
    // ---------------------------------------------------------------------------------------------

    /**
     * Just a simple test until ContentPackage class supports Copy / Cut and Paste
     */
    public void testCanCopyElement() {
        ContentPackage cp = new ContentPackage();
        assertFalse("Should not be able to Copy Element", cp.canCopyElement(new Element("anything")));
        assertFalse("Should not be able to Copy Element",
                cp.canCopyElement(new Element("item", CP_EditorHandler.IMSCP_NAMESPACE_113)));
    }
    
    // ---------------------------------------------------------------------------------------------

    /**
     * Just a simple test until ContentPackage class supports Copy / Cut and Paste
     */
    public void testCanCutElement() {
        ContentPackage cp = new ContentPackage();
        assertFalse("Should not be able to Cut Element", cp.canCutElement(new Element("anything")));
        assertFalse("Should not be able to Cut Element",
                cp.canCutElement(new Element("item", CP_EditorHandler.IMSCP_NAMESPACE_113)));
    }
    
    // ---------------------------------------------------------------------------------------------

    /**
     * Just a simple test until ContentPackage class supports Copy / Cut and Paste
     */
    public void testCanPasteFromClipboard() {
        ContentPackage cp = new ContentPackage();
        assertFalse("Should not be able to Paste Element", cp.canPasteFromClipboard(new Element("anything")));
        assertFalse("Should not be able to Paste Element",
                cp.canPasteFromClipboard(new Element("item", CP_EditorHandler.IMSCP_NAMESPACE_113)));
    }
    
    // ---------------------------------------------------------------------------------------------

    public void testCanDragElement() throws Exception {
        ContentPackage cp = new ContentPackage();
        cp.setSchemaController(new CP113_SchemaController());
        
        Element element = new Element("anything");
        assertFalse("Should not be able to Drag Element", cp.canDragElement(element, DnDConstants.ACTION_COPY));
        
        element = new Element("file", CP_EditorHandler.IMSCP_NAMESPACE_113);
        assertFalse("Should not be able to Drag Element", cp.canDragElement(element, DnDConstants.ACTION_COPY));

        element = new Element("resource", CP_EditorHandler.IMSCP_NAMESPACE_113);
        assertTrue("Should be able to Drag Element", cp.canDragElement(element, DnDConstants.ACTION_COPY));
    
        element = new Element("metadata", CP_EditorHandler.IMSCP_NAMESPACE_113);
        assertTrue("Should be able to Drag Element", cp.canDragElement(element, DnDConstants.ACTION_COPY));

        element = new Element("item", CP_EditorHandler.IMSCP_NAMESPACE_113);
        assertTrue("Should be able to Drag Element", cp.canDragElement(element, DnDConstants.ACTION_COPY));

        element = new Element("organization", CP_EditorHandler.IMSCP_NAMESPACE_113);
        assertTrue("Should be able to Drag Element", cp.canDragElement(element, DnDConstants.ACTION_COPY));

        element = new Element("manifest", CP_EditorHandler.IMSCP_NAMESPACE_113);
        assertTrue("Should be able to Drag Element", cp.canDragElement(element, DnDConstants.ACTION_COPY));
    }
    
    // ---------------------------------------------------------------------------------------------

    // TO DO write these tests
    
    public void _testAcceptElement() {
        
    }
    
    // ---------------------------------------------------------------------------------------------

    public void _testShiftElement() {
        
    }
    
    // ---------------------------------------------------------------------------------------------

    public void _testCopyElementUndoable() {
        
    }
    
    // ---------------------------------------------------------------------------------------------

    public void _testPasteElementUndoable() {
        
    }
    
    // ---------------------------------------------------------------------------------------------

    public void _testCopypasteElement() {
        
    }
    
    // ---------------------------------------------------------------------------------------------

    public void _testAddItemManifest() {
        
    }
    
    // ---------------------------------------------------------------------------------------------

    public void _testAddItemResource() {
        
    }
    
    // ---------------------------------------------------------------------------------------------

    public void _testAddOrganization() {
        
    }
    
    // ---------------------------------------------------------------------------------------------

    public void _testAddTitle() {
        
    }
    
    // ---------------------------------------------------------------------------------------------

    public void _testAddItemTitle() {
        
    }
    
    // ---------------------------------------------------------------------------------------------

    /**
     * Test a few Element Display names - no need to test all
     * @throws Exception
     */
    public void testGetElementDisplayName() throws Exception {
        ContentPackage cp = new ContentPackage(file1);
        assertNotNull("ContentPackage is null", cp);
        
        // Null
        Element element = null;
        assertNull("Should have null display name", cp.getElementDisplayName(element));
        
        // Plain Item
        element = new Element(CP_Core.ITEM, CP_EditorHandler.IMSCP_NAMESPACE_113);
        assertEquals("Incorrect display name", "Item", cp.getElementDisplayName(element));
        
        // Item with Title child
        Element child = new Element(CP_Core.TITLE, CP_EditorHandler.IMSCP_NAMESPACE_113);
        child.setText("Hello World");
        element.addContent(child);
        assertEquals("Incorrect display name", "Hello World", cp.getElementDisplayName(element));
        
        // Resource
        element = cp.getElementByIdentifier(cp.getRootElement(), CP_Package1.RESOURCE1_ID);
        assertNotNull("Element was null", element);
        assertEquals("Incorrect display name", "zappa/0-intro.html", cp.getElementDisplayName(element));
        
        // Dependency of previous Resource points to Resource href
        element = element.getChild(CP_Core.DEPENDENCY, CP_EditorHandler.IMSCP_NAMESPACE_113);
        assertNotNull("Element was null", element);
        assertEquals("Incorrect display name", "zappa/3-money.html", cp.getElementDisplayName(element));
        
        // Manifest
        element = cp.getRootElement();
        assertEquals("Incorrect display name", CP_Package1.MANIFEST1_ID, cp.getElementDisplayName(element));
    }
    
    // ---------------------------------------------------------------------------------------------

    public void testCreateNewIDs() throws Exception {
        ContentPackage cp = new ContentPackage(file1);
        assertNotNull("ContentPackage is null", cp);
        
        Hashtable idmap = cp.createNewIDs(cp.getRootElement());
        Enumeration e = idmap.keys();
        while(e.hasMoreElements()) {
            String newID = (String)e.nextElement();
            Element element = (Element)idmap.get(newID);
            assertNotNull("Element should have <identifier> value", element.getAttributeValue(CP_Core.IDENTIFIER));
            assertEquals("Element not in Namespace", cp.getRootElement().getNamespace(), element.getNamespace());
            assertTrue("Identifiers should be different", newID != element.getAttributeValue(CP_Core.IDENTIFIER));
        }

        // Start at root element and drill in to test
        itestCreateNewIDs(cp.getRootElement(), idmap);
    }

    /**
     * Test that the old identifiers no longer reference an Element
     * @param element The Element to test
     * @param idmap Hash Map of old identifiers mapped to Elements
     */
    private void itestCreateNewIDs(Element element, Hashtable idmap) {
        String id = element.getAttributeValue(CP_Core.IDENTIFIER);
        if(id != null && !id.equals("")) {
            Element refElement = (Element)idmap.get(id);
            assertNull("Old Reference should not exist: " + refElement, refElement);
        }
        
        // Drill in to children
        List list = element.getChildren();
        for(Iterator iter = list.iterator(); iter.hasNext();) {
            itestCreateNewIDs((Element)iter.next(), idmap);
        }
    }

    // ---------------------------------------------------------------------------------------------

    public void testCreateNewIDRefs() throws Exception {
        ContentPackage cp = new ContentPackage(file1);
        assertNotNull("ContentPackage is null", cp);
        
        Hashtable idmap = cp.createNewIDs(cp.getRootElement());
        cp.createNewIDRefs(cp.getRootElement(), idmap);
        
        // Start at root element and drill in to test
        itestCreateNewIDRefs(cp.getRootElement(), idmap);
    }
    
    /**
     * Test that the old identifiers no longer reference an Element
     * @param element The Element to test
     * @param idmap Hash Map of old identifiers mapped to Elements
     */
    private void itestCreateNewIDRefs(Element element, Hashtable idmap) {
        String idref = element.getAttributeValue(CP_Core.IDENTIFIERREF);
        if(idref != null && !idref.equals("")) {
            Element refElement = (Element)idmap.get(idref);
            assertNull("Old Reference should not exist: " + refElement, refElement);
        }
        
        // Drill in to children
        List list = element.getChildren();
        for(Iterator iter = list.iterator(); iter.hasNext();) {
            itestCreateNewIDRefs((Element)iter.next(), idmap);
        }
    }
    
    // ---------------------------------------------------------------------------------------------

    // TO DO write these tests

    public void _testCreateNewPrerequisiteRefs() {
        
    }
    
    // ---------------------------------------------------------------------------------------------

    public void _testCheckIDRefs() {
        
    }
    
    // ---------------------------------------------------------------------------------------------

    public void _testGenerateUniqueID() {
        
    }
    
    // ---------------------------------------------------------------------------------------------

    public void _testSetDefaultOrganization() {
        
    }
    
    // ---------------------------------------------------------------------------------------------

    public void _testAddCP_Resources() {
        
    }
    
    // ---------------------------------------------------------------------------------------------

    public void _testAddCP_Resource_CP_ResourceElement() {
        
    }
    
    // ---------------------------------------------------------------------------------------------

    public void _testAddCP_Resource_CP_ResourceElementBoolean() {
        
    }
    
    // ---------------------------------------------------------------------------------------------

    public void _testAddCP_ResourceToResourcesElement() {
        
    }
    
    // ---------------------------------------------------------------------------------------------

    public void _testGetResourceByCP_Resource() {
        
    }
    
    // ---------------------------------------------------------------------------------------------

    public void _testGetFileByCP_Resource() {
        
    }
    
    // ---------------------------------------------------------------------------------------------

    public void _testContainsResource_ElementElement() {
        
    }
    
    // ---------------------------------------------------------------------------------------------

    public void _testContainsResource_StringElement() {
        
    }
    
    // ---------------------------------------------------------------------------------------------

    public void _testContainsFile_StringElement() {
        
    }
    
    // ---------------------------------------------------------------------------------------------

    public void _testAcceptsCP_Resources() {
        
    }
    
    // ---------------------------------------------------------------------------------------------

    public void _testAddFileElements() {
        
    }
    
    // ---------------------------------------------------------------------------------------------

    public void _testAddFileElement() {
        
    }
    
    // ---------------------------------------------------------------------------------------------
    // ---------------------------------- From SchemaDocument --------------------------------------
    // ---------------------------------------------------------------------------------------------
    
    public void testGetSchemaController() throws Exception {
        ContentPackage cp = new ContentPackage(file1);
        itestGetSchemaController(cp, CP113_SchemaController.class);
    }

    // ---------------------------------------------------------------------------------------------
    
    public void testGetRootNamespace() throws Exception {
        File folder = FileSupport.getTempFolder("cp-test");
        ContentPackage cp = new ContentPackage(folder, new CP112_SchemaController(),
                new MD121_SchemaController());
        itestGetRootNamespace(cp, CP_EditorHandler.IMSCP_NAMESPACE_112);
    }

    // ---------------------------------------------------------------------------------------------
    
    public void testGetTargetNamespace1() throws Exception {
        File folder = FileSupport.getTempFolder("cp-test");
        ContentPackage cp = new ContentPackage(folder, new CP112_SchemaController(),
                new MD121_SchemaController());
        itestGetTargetNamespace(cp, CP_EditorHandler.IMSCP_NAMESPACE_112);
    }

    public void testGetTargetNamespace2() throws Exception {
        ContentPackage cp = new ContentPackage(file1);
        itestGetTargetNamespace(cp, CP_EditorHandler.IMSCP_NAMESPACE_113);
    }

    // ---------------------------------------------------------------------------------------------
    
    public void testGetElement1() throws Exception {
        ContentPackage cp = new ContentPackage(file1);
        SchemaElement schemaElement = cp.getSchemaController()
                                      .getSchemaModel()
                                      .getRootElement();
        itestGetElement(cp, schemaElement);
    }

    public void testGetElement2() throws Exception {
        ContentPackage cp = new ContentPackage(file1);
        SchemaElement schemaElement = cp.getSchemaController()
        							  .getSchemaModel()
        							  .getRootElement()
        							  .getChild("organizations")
        							  .getChild("organization")
        							  .getChild("item")
        							  .getChild("title");
        itestGetElement(cp, schemaElement);
    }

    // ---------------------------------------------------------------------------------------------

    public void testGetElements1() throws Exception {
        ContentPackage cp = new ContentPackage(file1);
        SchemaElement schemaElement = cp.getSchemaController()
		                              .getSchemaModel()
									  .getRootElement();
        itestGetElements(cp, schemaElement, 1);
    }
    
    public void testGetElements2() throws Exception {
        ContentPackage cp = new ContentPackage(file1);
        SchemaElement schemaElement = cp.getSchemaController()
        							  .getSchemaModel()
        							  .getRootElement()
        							  .getChild("organizations");
        itestGetElements(cp, schemaElement, 1);
    }

    public void testGetElements3() throws Exception {
        ContentPackage cp = new ContentPackage(file1);
        SchemaElement schemaElement = cp.getSchemaController()
        							  .getSchemaModel()
        							  .getRootElement()
        							  .getChild("resources")
        							  .getChild("resource");
        itestGetElements(cp, schemaElement, 14);
    }

    // ---------------------------------------------------------------------------------------------

    public void testAddCommentsToDocument() {
        ContentPackage cp = new ContentPackage();
        cp.setDocument(new Document());
        itestAddCommentsToDocument(cp);
    }
    
    // ---------------------------------------------------------------------------------------------

    public void testAddElementUniqueBySchema1() throws Exception {
        ContentPackage cp = new ContentPackage(file1);
        SchemaElement schemaElement = cp.getSchemaController()
									  .getSchemaModel()
									  .getRootElement()
									  .getChild("resources")
									  .getChild("resource");
        itestAddElementUniqueBySchema(cp, schemaElement);
    }

    public void testAddElementUniqueBySchema2() throws Exception {
        ContentPackage cp = new ContentPackage(file1);
        SchemaElement schemaElement = cp.getSchemaController()
									  .getSchemaModel()
									  .getRootElement()
									  .getChild("resources")
									  .getChild("resource")
									  .getChild("file");
        itestAddElementUniqueBySchema(cp, schemaElement);
    }

    public void testAddElementUniqueBySchema3() throws Exception {
        ContentPackage cp = new ContentPackage(file1);
        SchemaElement schemaElement = cp.getSchemaController()
									  .getSchemaModel()
									  .getRootElement();
        itestAddElementUniqueBySchema(cp, schemaElement);
    }

    // ---------------------------------------------------------------------------------------------

    public void testAddElementUniqueBySchemaUndoable1() throws Exception {
        ContentPackage cp = new ContentPackage(file1);
        SchemaElement schemaElement = cp.getSchemaController()
									  .getSchemaModel()
									  .getRootElement()
									  .getChild("resources")
									  .getChild("resource");
        itestAddElementUniqueBySchemaUndoable(cp, schemaElement);
    }

    public void testAddElementUniqueBySchemaUndoable2() throws Exception {
        ContentPackage cp = new ContentPackage(file1);
        SchemaElement schemaElement = cp.getSchemaController()
									  .getSchemaModel()
									  .getRootElement()
									  .getChild("resources")
									  .getChild("resource")
									  .getChild("file");
        itestAddElementUniqueBySchemaUndoable(cp, schemaElement);
    }

    public void testAddElementUniqueBySchemaUndoable3() throws Exception {
        ContentPackage cp = new ContentPackage(file1);
        SchemaElement schemaElement = cp.getSchemaController()
									  .getSchemaModel()
									  .getRootElement();
        itestAddElementUniqueBySchemaUndoable(cp, schemaElement);
    }

    // ---------------------------------------------------------------------------------------------

    public void testAddElementBySchemaUndoable() throws Exception {
        ContentPackage cp = new ContentPackage(file1);
        SchemaElement schemaElement = cp.getSchemaController()
        							  .getSchemaModel()
        							  .getRootElement()
        							  .getChild("resources")
        							  .getChild("resource");
        itestAddElementBySchemaUndoable(cp, schemaElement);
    }

    // ---------------------------------------------------------------------------------------------

    /**
     * Add to MD embedded in CP
     */
    public void testAddElementBySchema1() throws Exception {
        ContentPackage cp = new ContentPackage(file1);
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
        ContentPackage cp = new ContentPackage(file1);
        
        Element parentElement = cp.getRootElement()
								.getChild("resources", cp.getRootElement().getNamespace());
        
        SchemaElement schemaElement = cp.getSchemaController()
		  							  .getSchemaModel()
		  							  .getRootElement()
		  							  .getChild("resources")
		  							  .getChild("resource");
        
        Element element = itestAddElementBySchema(cp, parentElement, schemaElement);
        
        assertEquals("Wrong Element", "resource", element.getName());
        assertEquals("Wrong Parent", "resources", element.getParent().getName());
        
        Attribute att = element.getAttribute("identifier");
        assertNotNull("No identifier Attribute added", att);
        att = element.getAttribute("type");
        assertNotNull("No type Attribute added", att);
        assertEquals("Type attribute not set to default", "webcontent", att.getValue());
    }

    /**
     * All the bits got added
     */
    public void testAddElementBySchema3() throws Exception {
        ContentPackage cp = new ContentPackage(file1);
        
        Element parentElement = cp.getRootElement()
								.getChild("organizations", cp.getRootElement().getNamespace())
								.getChild("organization", cp.getRootElement().getNamespace());
        
        SchemaElement schemaElement = cp.getSchemaController()
		  							  .getSchemaModel()
		  							  .getRootElement()
		  							  .getChild("organizations")
		  							  .getChild("organization")
		  							  .getChild("item");
        
        Element element = itestAddElementBySchema(cp, parentElement, schemaElement);
        
        assertEquals("Wrong Element", "item", element.getName());
        assertEquals("Wrong Parent", "organization", element.getParent().getName());
        assertNotNull("Element should have title child", element.getChild("title", cp.getRootElement().getNamespace()));
        
        Attribute att = element.getAttribute("identifier");
        assertNotNull("No identifier Attribute added", att);
        att = element.getAttribute("isvisible");
        assertNotNull("No isvisible Attribute added", att);
        assertEquals("Type attribute not set to default", "true", att.getValue());
    }

    /**
     * All the bits got added
     */
    public void testAddElementBySchema4() throws Exception {
        ContentPackage cp = new ContentPackage(file1);
        
        Element parentElement = cp.getRootElement()
								.getChild("organizations", cp.getRootElement().getNamespace());
        
        SchemaElement schemaElement = cp.getSchemaController()
		  							  .getSchemaModel()
		  							  .getRootElement()
		  							  .getChild("organizations")
		  							  .getChild("organization");
        
        Element element = itestAddElementBySchema(cp, parentElement, schemaElement);
        
        assertEquals("Wrong Element", "organization", element.getName());
        assertEquals("Wrong Parent", "organizations", element.getParent().getName());
        assertFalse("Element should have no children", element.getChildren().size() == 0);
        
        Attribute att = element.getAttribute("structure");
        assertNotNull("No structure Attribute added", att);
        assertEquals("Structure attribute not set to default", "hierarchical", att.getValue());
    }

    // ---------------------------------------------------------------------------------------------

    public void testAddElementByXMLPath1() throws Exception {
        File folder = FileSupport.getTempFolder("cp-test");
        ContentPackage cp = new ContentPackage(folder, new CP112_SchemaController(),
                new MD121_SchemaController());
        
        XMLPath xmlPath = new XMLPath("manifest");
        Element element = itestAddElementByXMLPath(cp, cp.getRootElement(), xmlPath);
        assertEquals("Wrong Parent", "manifest", element.getParent().getName());
        assertTrue("Element should have children", !element.getContent().isEmpty());
    }
    
    public void testAddElementByXMLPath2() throws Exception {
        File folder = FileSupport.getTempFolder("cp-test");
        ContentPackage cp = new ContentPackage(folder, new CP112_SchemaController(),
                new MD121_SchemaController());
        
        XMLPath xmlPath = new XMLPath("manifest/resources/resource/file");
        Element element = itestAddElementByXMLPath(cp, cp.getRootElement(), xmlPath);
        assertEquals("Wrong Parent", "resource", element.getParent().getName());
        assertTrue("Element should have no children", element.getContent().isEmpty());
    }

    // ---------------------------------------------------------------------------------------------

    public void testAddElementUndoable() throws Exception {
        ContentPackage cp = new ContentPackage(file1);
        Element childElement = new Element("metadata", cp.getRootElement().getNamespace());
        itestAddElementUndoable(cp, cp.getRootElement(), childElement);
    }

    // ---------------------------------------------------------------------------------------------

    public void testAddElement() throws Exception {
        ContentPackage cp = new ContentPackage(file1);
        Element childElement = new Element("metadata", cp.getRootElement().getNamespace());
        itestAddElement(cp, cp.getRootElement(), childElement);
    }

    // ---------------------------------------------------------------------------------------------

    public void testDeleteElementUndoable() throws Exception {
        ContentPackage cp = new ContentPackage(file1);
        Element element = new Element("metadata", cp.getRootElement().getNamespace());
        cp.getRootElement().addContent(element);
        itestDeleteElementUndoable(cp, element);
    }

    // ---------------------------------------------------------------------------------------------

    public void testCutElementUndoable() throws Exception {
        ContentPackage cp = new ContentPackage(file1);
        Element element = new Element("metadata", cp.getRootElement().getNamespace());
        cp.getRootElement().addContent(element);
        itestCutElementUndoable(cp, element);
    }

    // ---------------------------------------------------------------------------------------------

    public void testMoveElementUndoable() throws Exception {
        ContentPackage cp = new ContentPackage(file1);
        Element element = cp.getRootElement()
						  .getChild("organizations", cp.getRootElement().getNamespace())
						  .getChild("organization", cp.getRootElement().getNamespace());
        itestMoveElementUndoable(cp, element, cp.getRootElement());
    }

    // ---------------------------------------------------------------------------------------------

    public void testCopyElementUndoable() throws Exception {
        ContentPackage cp = new ContentPackage(file1);
        Element element = cp.getRootElement()
        				  .getChild("organizations", cp.getRootElement().getNamespace())
        				  .getChild("organization", cp.getRootElement().getNamespace());
        itestCopyElementUndoable(cp, element, cp.getRootElement());
    }

    // ---------------------------------------------------------------------------------------------

    public void testPasteElementUndoable() throws Exception {
        ContentPackage cp = new ContentPackage(file1);
        Element element = cp.getRootElement()
        				  .getChild("organizations", cp.getRootElement().getNamespace())
        				  .getChild("organization", cp.getRootElement().getNamespace());
        itestPasteElementUndoable(cp, element, cp.getRootElement());
    }

    // ---------------------------------------------------------------------------------------------

    public void testCanAddElement1() throws Exception {
        ContentPackage cp = new ContentPackage(file1);
        SchemaElement schemaElement = cp.getSchemaController()
        							  .getSchemaModel()
        							  .getRootElement()
        							  .getChild("resources")
        							  .getChild("resource");
        Element parentElement = cp.getRootElement().getChild("resources", cp.getRootElement().getNamespace());        
        itestCanAddElement(cp, parentElement, schemaElement, true);
    }

    public void testCanAddElement2() throws Exception {
        ContentPackage cp = new ContentPackage(file1);
        SchemaElement schemaElement = cp.getSchemaController()
        							  .getSchemaModel()
        							  .getRootElement()
        							  .getChild("metadata");
        Element parentElement = cp.getRootElement();
        itestCanAddElement(cp, parentElement, schemaElement, false);
    }

    // ---------------------------------------------------------------------------------------------

    /**
     * Different Namespaces
     */
    public void testIsAllowedChild1() throws Exception {
        ContentPackage cp = new ContentPackage(file1);
        Element parentElement = cp.getRootElement();
        Element childElement = new Element("ponk");
        itestIsAllowedChild(cp, parentElement, childElement, false);
    }

    public void testIsAllowedChild2() throws Exception {
        ContentPackage cp = new ContentPackage(file1);
        Element parentElement = cp.getRootElement();
        Element childElement = new Element("metadata", cp.getRootElement().getNamespace());
        itestIsAllowedChild(cp, parentElement, childElement, false);
    }

    public void testIsAllowedChild3() throws Exception {
        ContentPackage cp = new ContentPackage(file1);
        Element parentElement = cp.getRootElement();
        Element childElement = new Element("manifest", cp.getRootElement().getNamespace());
        itestIsAllowedChild(cp, parentElement, childElement, true);
    }

    // ---------------------------------------------------------------------------------------------

    public void testCanDeleteElement1() throws Exception {
        ContentPackage cp = new ContentPackage(file1);
        Element element = cp.getRootElement();
        itestCanDeleteElement(cp, element, false);
    }

    public void testCanDeleteElement2() throws Exception {
        ContentPackage cp = new ContentPackage(file1);
        Element element = cp.getRootElement()
        				  .getChild("metadata", cp.getRootElement().getNamespace());
        itestCanDeleteElement(cp, element, true);
    }

    public void testCanDeleteElement3() throws Exception {
        ContentPackage cp = new ContentPackage(file1);
        Element element = cp.getRootElement()
        				  .getChild("resources", cp.getRootElement().getNamespace());
        itestCanDeleteElement(cp, element, false);
    }

    // ---------------------------------------------------------------------------------------------

    public void testCanMoveElementUp1() throws Exception {
        ContentPackage cp = new ContentPackage(file1);
        Element element = cp.getRootElement();
        itestCanMoveElementUp(cp, element, false);
    }

    public void testCanMoveElementUp2() throws Exception {
        ContentPackage cp = new ContentPackage(file1);
        Element element = cp.getRootElement()
        				  .getChild("metadata", cp.getRootElement().getNamespace());
        itestCanMoveElementUp(cp, element, false);
    }

    public void testCanMoveElementUp3() throws Exception {
        ContentPackage cp = new ContentPackage(file1);
        Element element = (Element)cp.getRootElement()
        				  .getChild("resources", cp.getRootElement().getNamespace())
        				  .getContent(new ElementFilter()).get(0);
        itestCanMoveElementUp(cp, element, false);
    }

    public void testCanMoveElementUp4() throws Exception {
        ContentPackage cp = new ContentPackage(file1);
        Element element = (Element)cp.getRootElement()
        				  .getChild("resources", cp.getRootElement().getNamespace())
        				  .getContent(new ElementFilter()).get(1);
        itestCanMoveElementUp(cp, element, true);
    }

    // ---------------------------------------------------------------------------------------------

    public void testCanMoveElementDown1() throws Exception {
        ContentPackage cp = new ContentPackage(file1);
        Element element = cp.getRootElement();
        itestCanMoveElementDown(cp, element, false);
    }

    public void testCanMoveElementDown2() throws Exception {
        ContentPackage cp = new ContentPackage(file1);
        Element element = cp.getRootElement()
        				  .getChild("metadata", cp.getRootElement().getNamespace());
        itestCanMoveElementDown(cp, element, false);
    }

    public void testCanMoveElementDown3() throws Exception {
        ContentPackage cp = new ContentPackage(file1);
        Element element = (Element)cp.getRootElement()
        				  .getChild("resources", cp.getRootElement().getNamespace())
        				  .getContent(new ElementFilter()).get(0);
        itestCanMoveElementDown(cp, element, true);
    }

    public void testCanMoveElementDown4() throws Exception {
        ContentPackage cp = new ContentPackage(file1);
        List list = cp.getRootElement()
        			.getChildren("resources", cp.getRootElement().getNamespace());
        itestCanMoveElementDown(cp, (Element)list.get(list.size() - 1), false);
    }

    // ---------------------------------------------------------------------------------------------

    public void testAddAttribute() throws Exception {
        ContentPackage cp = new ContentPackage(file1);
        SchemaAttribute schemaAttribute = cp.getSchemaController()
        								  .getSchemaModel()
        								  .getRootElement()
        								  .getChild("resources")
        								  .getChild("resource")
        								  .getSchemaAttribute("xml:base");

        itestAddAttribute(cp, cp.getRootElement(), schemaAttribute);
    }

    /**
     * Add an already existing one - throws Exception
     */
    public void testAddAttribute2() throws Exception {
        ContentPackage cp = new ContentPackage(file1);
        SchemaAttribute schemaAttribute = cp.getSchemaController()
        								  .getSchemaModel()
        								  .getRootElement()
        								  .getChild("resources")
        								  .getChild("resource")
        								  .getSchemaAttribute("identifier");

        Element element = cp.getRootElement()
						  .getChild("resources", cp.getRootElement().getNamespace())
						  .getChild("resource", cp.getRootElement().getNamespace());

		try {
		    itestAddAttribute(cp, element, schemaAttribute);
		    fail("Should have thrown IllegalAddException");
		}
		catch(IllegalAddException ex) {
		    assertTrue(true);
		}
    }

    // ---------------------------------------------------------------------------------------------

    /**
     * Test that the structure attribute is added with default value
     */
    public void testAddAttributeWithDefaultValue1() throws Exception {
        File folder = FileSupport.getTempFolder("cp-test");
        ContentPackage cp = new ContentPackage(folder, new CP112_SchemaController(),
                new MD121_SchemaController());
        
        Element parentElement = cp.getRootElement()
								.getChild("organizations", cp.getRootElement().getNamespace());

        SchemaElement schemaElement = cp.getSchemaController()
				  .getSchemaModel()
				  .getRootElement()
				  .getChild("organizations")
				  .getChild("organization");

        Element element = cp.addElementBySchema(this, parentElement, schemaElement, true);

        Attribute att = element.getAttribute(CP_Core.STRUCTURE);
        assertNotNull("Default Attribute was null", att);
        assertEquals("Default Attribute value was wrong", "hierarchical",att.getValue());
    }

    // ---------------------------------------------------------------------------------------------

    public void testGetInsertPositionOfElement_ElementElement1() throws Exception {
        ContentPackage cp = new ContentPackage(file1);
        
        Element metadata = new Element("metadata", cp.getRootElement().getNamespace());
        itestGetInsertPositionOfElement_ElementElement(cp, cp.getRootElement(), metadata, 1);
        
        Element organizations = new Element("organizations", cp.getRootElement().getNamespace());
        itestGetInsertPositionOfElement_ElementElement(cp, cp.getRootElement(), organizations, 2);
        
        Element resources = new Element("resources", cp.getRootElement().getNamespace());
        itestGetInsertPositionOfElement_ElementElement(cp, cp.getRootElement(), resources, 3);

        Element ext = new Element("extension");
        itestGetInsertPositionOfElement_ElementElement(cp, cp.getRootElement(), ext, 4);
    }

    // ---------------------------------------------------------------------------------------------

    public void testGetInsertPositionOfElement_ElementSchemaElement() throws Exception {
        ContentPackage cp = new ContentPackage(file1);
        
        SchemaElement schemaElement = cp.getSchemaController()
		  							  .getSchemaModel()
		  							  .getRootElement()
		  							  .getChild("metadata");
        itestGetInsertPositionOfElement_ElementSchemaElement(cp, cp.getRootElement(), schemaElement, 1);
        
        schemaElement = cp.getSchemaController()
        				.getSchemaModel()
        				.getRootElement()
        				.getChild("organizations");
        itestGetInsertPositionOfElement_ElementSchemaElement(cp, cp.getRootElement(), schemaElement, 2);
        
        schemaElement = cp.getSchemaController()
        				.getSchemaModel()
        				.getRootElement()
        				.getChild("resources");
        itestGetInsertPositionOfElement_ElementSchemaElement(cp, cp.getRootElement(), schemaElement, 3);
        
        schemaElement = cp.getSchemaController()
        				.getSchemaModel()
        				.getRootElement()
        				.getChild("manifest");
        itestGetInsertPositionOfElement_ElementSchemaElement(cp, cp.getRootElement(), schemaElement, 4);
    }

    // ---------------------------------------------------------------------------------------------

    public void testGetInsertPositionOfAttribute() throws Exception {
        ContentPackage cp = new ContentPackage(file1);
        
        SchemaAttribute schemaAttribute = cp.getSchemaController()
        								  .getSchemaModel()
        								  .getRootElement()
        								  .getChild("resources")
        								  .getChild("resource")
        								  .getSchemaAttribute("xml:base");
        
        Element element = cp.getRootElement()
        				  .getChild("resources", cp.getRootElement().getNamespace())
        				  .getChild("resource", cp.getRootElement().getNamespace());
        
        itestGetInsertPositionOfAttribute(cp, element, schemaAttribute, 2);
    }

    // ---------------------------------------------------------------------------------------------

    public void testSetUndoHandler() throws Exception {
        ContentPackage cp = new ContentPackage(file1);
        itestSetUndoHandler(cp, new UndoHandler());
    }

}
