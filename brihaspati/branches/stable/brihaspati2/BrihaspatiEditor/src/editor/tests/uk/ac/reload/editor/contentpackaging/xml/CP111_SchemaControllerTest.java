
package uk.ac.reload.editor.contentpackaging.xml;

import java.io.File;
import java.io.FileNotFoundException;

import org.jdom.Namespace;

import uk.ac.reload.editor.contentpackaging.CP_EditorHandler;
import uk.ac.reload.editor.metadata.xml.MD11_SchemaController;
import uk.ac.reload.editor.metadata.xml.MD121_SchemaController;
import uk.ac.reload.jdom.XMLPath;
import uk.ac.reload.moonunit.schema.SchemaAttribute;
import uk.ac.reload.moonunit.schema.SchemaElement;
import uk.ac.reload.moonunit.schema.SchemaNode;
import uk.ac.reload.moonunit.vocab.VocabularyList;
import uk.ac.reload.testsupport.FileSupport;




/**
 * CP111_SchemaControllerTest
 * 
 * @author Phillip Beauvoir
 * @version $Id: CP111_SchemaControllerTest.java,v 1.1 1998/03/26 15:50:37 ynsingh Exp $
 */
public class CP111_SchemaControllerTest extends CP_SchemaControllerAbstractTest {

    protected void setUp() throws Exception {
		super.setUp();
        sc = new CP111_SchemaController();
    }
    
    // ---------------------------------------------------------------------------------------------
    // ---------------------------------------------------------------------------------------------
    // ----------------------------- From CP_SchemaController --------------------------------------
    // ---------------------------------------------------------------------------------------------

    /**
     * No Metadata SchemaController
     */
    public void testCopySchemaFilesToFolder1() throws Exception {
        File tmpFolder = FileSupport.getTempFolder("cp");
        File[] files = {
                new File(tmpFolder, "ims_xml.xsd"),
                new File(tmpFolder, "ims_cp_rootv1p1.xsd"),
        };
        itestCopySchemaFilesToFolder(files, tmpFolder);
    }
    
    /**
     * Default Metadata SchemaController
     */
    public void testCopySchemaFilesToFolder2() throws Exception {
        File tmpFolder = FileSupport.getTempFolder("cp");
        File[] files = {
                new File(tmpFolder, "ims_xml.xsd"),
                new File(tmpFolder, "ims_cp_rootv1p1.xsd"),
                new File(tmpFolder, "ims_md_rootv1p1.xsd")
        };
        
        CP_SchemaController cpsc = (CP_SchemaController)sc;
        cpsc.setMD_SchemaController(cpsc.getDefaultMD_SchemaController());
        itestCopySchemaFilesToFolder(files, tmpFolder);
    }

    /**
     * 1.2.1 Metadata SchemaController
     */
    public void testCopySchemaFilesToFolder3() throws Exception {
        File tmpFolder = FileSupport.getTempFolder("cp");
        File[] files = {
                new File(tmpFolder, "ims_xml.xsd"),
                new File(tmpFolder, "ims_cp_rootv1p1.xsd"),
                new File(tmpFolder, "imsmd_rootv1p2p1.xsd")
        };
        
        CP_SchemaController cpsc = (CP_SchemaController)sc;
        cpsc.setMD_SchemaController(new MD121_SchemaController());
        itestCopySchemaFilesToFolder(files, tmpFolder);
    }

    /**
     * 1.1 Metadata SchemaController
     */
    public void testCopySchemaFilesToFolder4() throws Exception {
        File tmpFolder = FileSupport.getTempFolder("cp");
        File[] files = {
                new File(tmpFolder, "ims_xml.xsd"),
                new File(tmpFolder, "ims_cp_rootv1p1.xsd"),
                new File(tmpFolder, "ims_md_rootv1p1.xsd")
        };
        
        CP_SchemaController cpsc = (CP_SchemaController)sc;
        cpsc.setMD_SchemaController(new MD11_SchemaController());
        itestCopySchemaFilesToFolder(files, tmpFolder);
    }

    // ---------------------------------------------------------------------------------------------
    
    public void testGetMetadataVersion() throws Exception {
        itestGetMetadataVersion(null);
        MD11_SchemaController mdSchemaController = new MD11_SchemaController();
        CP_SchemaController cpsc = (CP_SchemaController)sc;
        cpsc.setMD_SchemaController(mdSchemaController);
        itestGetMetadataVersion("IMS Metadata 1.1");
    }
    
    // ---------------------------------------------------------------------------------------------

    public void testGetMD_SchemaController() throws Exception {
        itestGetMD_SchemaController(null);
        MD11_SchemaController mdSchemaController = new MD11_SchemaController();
        CP_SchemaController cpsc = (CP_SchemaController)sc;
        cpsc.setMD_SchemaController(mdSchemaController);
        itestGetMD_SchemaController(mdSchemaController);
    }
    
    // ---------------------------------------------------------------------------------------------
    
    public void testGetDefaultMD_SchemaController() throws Exception {
        itestGetDefaultMD_SchemaController(MD11_SchemaController.class);
    }

    // ---------------------------------------------------------------------------------------------
    
    public void testGetLeafIcon() {
        itestGetLeafIcon("manifest", sc.getSchemaModel().getRootElement().getNamespace());
        itestGetLeafIcon("general", sc.getSchemaModel().getRootElement().getNamespace());
    }
    
    // ---------------------------------------------------------------------------------------------

    public void testGetOpenIcon() {
        itestGetOpenIcon("manifest", sc.getSchemaModel().getRootElement().getNamespace());
        itestGetOpenIcon("general", sc.getSchemaModel().getRootElement().getNamespace());
    }
    
    // ---------------------------------------------------------------------------------------------
    
    public void testGetClosedIcon() {
        itestGetClosedIcon("manifest", sc.getSchemaModel().getRootElement().getNamespace());
        itestGetOpenIcon("general", sc.getSchemaModel().getRootElement().getNamespace());
    }
    
    // ---------------------------------------------------------------------------------------------
    // ---------------------------------------------------------------------------------------------
    // ----------------------------- From ProfiledSchemaController ---------------------------------
    // ---------------------------------------------------------------------------------------------

    public void testLoadHelperProfile1() throws Exception {
        try {
            itestLoadHelperProfile("Ponk");
            fail("Should have thrown FileNotFoundException");
        }
        catch(FileNotFoundException ex) {
            assertTrue(true);
        }
    }
    
    public void testLoadHelperProfile2() throws Exception {
        itestLoadHelperProfile("CP Default Profile");
    }

    public void testLoadHelperProfile3() throws Exception {
        itestLoadHelperProfile("CP Example Profile");
    }

    // ---------------------------------------------------------------------------------------------

    public void testGetHelperProfileNames() {
        String[] names = itestGetHelperProfileNames();
        assertTrue("Wrong Number of Profiles", names.length == 2);
    }

    // ---------------------------------------------------------------------------------------------
    // ----------------------------- From SchemaController -----------------------------------------
    // ---------------------------------------------------------------------------------------------
    
    public void testSetDefaultVocabLanguage() {
        itestSetDefaultVocabLanguage("en");
    }

    // ---------------------------------------------------------------------------------------------
    
    public void testIsTopLevelElement_False() {
		SchemaElement schemaElement = sc.getSchemaModel()
			.getRootElement()
			.getChild("manifest")
			.getChild("resources")
			.getChild("resource");
        
        itestIsTopLevelElement(schemaElement, false);
    }

    public void testIsTopLevelElement_True() {
		SchemaElement schemaElement = sc.getSchemaModel()
			.getRootElement()
			.getChild("manifest");
        
        itestIsTopLevelElement(schemaElement, true);
    }

    // ---------------------------------------------------------------------------------------------
    
    public void testGetSchemaNode1() {
        XMLPath xmlPath = new XMLPath("");
        SchemaNode node = itestGetSchemaNode(xmlPath);
        assertEquals("Wrong SchemaNode", "manifest", node.getName());
    }

    public void testGetSchemaNode2() {
        XMLPath xmlPath = new XMLPath("manifest/metadata");
        SchemaNode node = itestGetSchemaNode(xmlPath);
        assertEquals("Wrong SchemaNode", "metadata", node.getName());
    }

    public void testGetSchemaNode3() {
        XMLPath xmlPath = new XMLPath("manifest/resources/resource@xml:base");
        SchemaNode node = itestGetSchemaNode(xmlPath);
        assertEquals("Wrong SchemaNode", "base", node.getName());
        assertEquals("Wrong SchemaNode namespace", Namespace.XML_NAMESPACE, node.getNamespace());
    }

    /**
     * Works if root part is missing
     */
    public void testGetSchemaNode4() {
        XMLPath xmlPath = new XMLPath("resources/resource@xml:base");
        SchemaNode node = itestGetSchemaNode(xmlPath);
        assertEquals("Wrong SchemaNode", "base", node.getName());
        assertEquals("Wrong SchemaNode namespace", Namespace.XML_NAMESPACE, node.getNamespace());
    }

    // ---------------------------------------------------------------------------------------------
    
    public void testGetElementHelperValue1() {
        XMLPath xmlPath = new XMLPath("manifest/organizations");
        String value = itestGetElementHelperValue(xmlPath, "fname", "Organizations");
	}

    public void testGetElementHelperValue2() {
        XMLPath xmlPath = new XMLPath("manifest/resources/resource@xml:base");
        String value = itestGetElementHelperValue(xmlPath, "fname", "Base");
	}

    // ---------------------------------------------------------------------------------------------
    
    public void testGetElementFriendlyName1() {
        XMLPath xmlPath = new XMLPath("manifest/organizations");
        String value = itestGetElementFriendlyName(xmlPath, "Organizations");
	}

    public void testGetElementFriendlyName2() {
        XMLPath xmlPath = new XMLPath("manifest/resources/resource@xml:base");
        String value = itestGetElementFriendlyName(xmlPath, "Base");
	}

    // ---------------------------------------------------------------------------------------------
    
    public void testGetElementTip1() {
        XMLPath xmlPath = new XMLPath("manifest/organizations/organization");
        String value = itestGetElementTip(xmlPath, "Describes a particular hierarchical organization.");
	}

    public void testGetElementTip2() {
        XMLPath xmlPath = new XMLPath("manifest/resources/resource@xml:base");
        String value = itestGetElementTip(xmlPath, "Provides the relative path offset for the content file(s).");
	}

    // ---------------------------------------------------------------------------------------------
    
    /**
     * Should return null for CP because we don't have any widget elements
     */
    public void testGetWidgetType() {
        XMLPath xmlPath = new XMLPath("blah/size");
        String value = itestGetWidgetType(xmlPath, null);
	}

    // ---------------------------------------------------------------------------------------------
    
    public void testGetVocabularyList1() {
        SchemaElement schemaElement = sc.getSchemaModel()
        	.getRootElement()
        	.getChild("manifest")
        	.getChild("resources")
        	.getChild("resource");
        VocabularyList vList = itestGetVocabularyList(schemaElement);
        assertNull("VocabularyList should be null", vList);
    }
    
    public void testGetVocabularyList2() {
        SchemaAttribute schemaAtt = sc.getSchemaModel()
	        .getRootElement()
	        .getChild("manifest")
	        .getChild("organizations")
	        .getChild("organization")
	        .getChild("item")
	        .getSchemaAttribute("isvisible");
        VocabularyList vList = itestGetVocabularyList(schemaAtt);
        assertNotNull("VocabularyList was null", vList);
    }

    // ---------------------------------------------------------------------------------------------
    
    public void testGetDefaultValue1() {
        SchemaAttribute schemaAtt = sc.getSchemaModel()
	        .getRootElement()
	        .getChild("manifest")
	        .getChild("organizations")
	        .getChild("organization")
	        .getChild("item")
	        .getSchemaAttribute("isvisible");
        String value = itestGetDefaultValue(schemaAtt, "true");
    }
    
    public void testGetDefaultValue2() {
        SchemaAttribute schemaAtt = sc.getSchemaModel()
	        .getRootElement()
	        .getChild("manifest")
	        .getChild("resources")
	        .getChild("resource")
	        .getSchemaAttribute("type");
        String value = itestGetDefaultValue(schemaAtt, "webcontent");
    }

    // ---------------------------------------------------------------------------------------------
    
    public void testGetFacetValue() {
        SchemaElement schemaElement = sc.getSchemaModel()
	        .getRootElement()
	        .getChild("manifest")
	        .getChild("organizations")
	        .getChild("organization");
        String value = itestGetFacetValue(schemaElement, "fname", "Organization");
    }
    
    // ---------------------------------------------------------------------------------------------
    
    public void testGetVersion() {
        itestGetVersion(CP_EditorHandler.IMS_CONTENT_PACKAGING_1_1_1);
    }
    
    // ---------------------------------------------------------------------------------------------
    
    public void testGetRootElementName() {
        itestGetRootElementName("manifest");
    }
    
    // ---------------------------------------------------------------------------------------------
    
    public void testGetSchemaFile() {
        File file = itestGetSchemaFile();
        assertEquals("Wrong Schema File", CP111_SchemaController.fileSchemaCP1_1_1, file);
    }

    // ---------------------------------------------------------------------------------------------
    
}
