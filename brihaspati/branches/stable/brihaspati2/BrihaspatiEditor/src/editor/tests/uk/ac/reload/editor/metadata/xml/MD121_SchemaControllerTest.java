
package uk.ac.reload.editor.metadata.xml;

import java.io.File;
import java.io.FileNotFoundException;

import org.jdom.Namespace;

import uk.ac.reload.editor.metadata.MD_EditorHandler;
import uk.ac.reload.jdom.XMLPath;
import uk.ac.reload.moonunit.schema.SchemaAttribute;
import uk.ac.reload.moonunit.schema.SchemaElement;
import uk.ac.reload.moonunit.schema.SchemaNode;
import uk.ac.reload.moonunit.vocab.VocabularyList;




/**
 * MD121_SchemaControllerTest
 * 
 * @author Phillip Beauvoir
 * @version $Id: MD121_SchemaControllerTest.java,v 1.1 1998/03/26 15:50:37 ynsingh Exp $
 */
public class MD121_SchemaControllerTest extends MD_SchemaControllerAbstractTest {

    protected void setUp() throws Exception {
		super.setUp();
        sc = new MD121_SchemaController();
    }
    
    // ---------------------------------------------------------------------------------------------
    // ----------------------------- From MD_SchemaController --------------------------------------
    // ---------------------------------------------------------------------------------------------

    public void testGetLeafIcon() {
        itestGetLeafIcon("lom", sc.getSchemaModel().getRootElement().getNamespace());
        itestGetLeafIcon("general", sc.getSchemaModel().getRootElement().getNamespace());
    }
    
    // ---------------------------------------------------------------------------------------------

    public void testGetOpenIcon() {
        itestGetOpenIcon("lom", sc.getSchemaModel().getRootElement().getNamespace());
        itestGetOpenIcon("general", sc.getSchemaModel().getRootElement().getNamespace());
    }
    
    // ---------------------------------------------------------------------------------------------
    
    public void testGetClosedIcon() {
        itestGetClosedIcon("lom", sc.getSchemaModel().getRootElement().getNamespace());
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
        itestLoadHelperProfile("IMS LRM Profile");
    }

    public void testLoadHelperProfile3() throws Exception {
        itestLoadHelperProfile("LTSN Profile");
    }

    // ---------------------------------------------------------------------------------------------

    public void testGetHelperProfileNames() {
        String[] names = itestGetHelperProfileNames();
        assertTrue("Wrong Number of Profiles", names.length == 3);
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
			.getChild("general")
			.getChild("title")
			.getChild("langstring");
        
        itestIsTopLevelElement(schemaElement, false);
    }

    public void testIsTopLevelElement_True() {
		SchemaElement schemaElement = sc.getSchemaModel()
			.getRootElement()
			.getChild("general");
        
        itestIsTopLevelElement(schemaElement, true);
    }

    // ---------------------------------------------------------------------------------------------
    
    public void testGetSchemaNode1() {
        XMLPath xmlPath = new XMLPath("");
        SchemaNode node = itestGetSchemaNode(xmlPath);
        assertEquals("Wrong SchemaNode", "lom", node.getName());
    }

    public void testGetSchemaNode2() {
        XMLPath xmlPath = new XMLPath("lom/general");
        SchemaNode node = itestGetSchemaNode(xmlPath);
        assertEquals("Wrong SchemaNode", "general", node.getName());
    }

    public void testGetSchemaNode3() {
        XMLPath xmlPath = new XMLPath("lom/general/title/langstring@xml:lang");
        SchemaNode node = itestGetSchemaNode(xmlPath);
        assertEquals("Wrong SchemaNode", "lang", node.getName());
        assertEquals("Wrong SchemaNode namespace", Namespace.XML_NAMESPACE, node.getNamespace());
    }

    /**
     * Works if root part is missing
     */
    public void testGetSchemaNode4() {
        XMLPath xmlPath = new XMLPath("general/title/langstring@xml:lang");
        SchemaNode node = itestGetSchemaNode(xmlPath);
        assertEquals("Wrong SchemaNode", "lang", node.getName());
        assertEquals("Wrong SchemaNode namespace", Namespace.XML_NAMESPACE, node.getNamespace());
    }

    // ---------------------------------------------------------------------------------------------
    
    public void testGetElementHelperValue1() {
        XMLPath xmlPath = new XMLPath("lom/catalogentry");
        String value = itestGetElementHelperValue(xmlPath, "fname", "Catalog Entry");
	}

    public void testGetElementHelperValue2() {
        XMLPath xmlPath = new XMLPath("lom/general/title/langstring@xml:lang");
        String value = itestGetElementHelperValue(xmlPath, "fname", "Language");
	}

    // ---------------------------------------------------------------------------------------------
    
    public void testGetElementFriendlyName1() {
        XMLPath xmlPath = new XMLPath("lom/catalogentry");
        String value = itestGetElementFriendlyName(xmlPath, "Catalog Entry");
	}

    public void testGetElementFriendlyName2() {
        XMLPath xmlPath = new XMLPath("lom/general/title/langstring@xml:lang");
        String value = itestGetElementFriendlyName(xmlPath, "Language");
	}

    // ---------------------------------------------------------------------------------------------
    
    public void testGetElementTip1() {
        XMLPath xmlPath = new XMLPath("lom/catalogentry");
        String value = itestGetElementTip(xmlPath, "Designation given to resource or meta-data instance.");
	}

    public void testGetElementTip2() {
        XMLPath xmlPath = new XMLPath("lom/general/title/langstring@xml:lang");
        String value = itestGetElementTip(xmlPath, "Choose Language");
	}

    // ---------------------------------------------------------------------------------------------
    
    public void testGetWidgetType1() {
        XMLPath xmlPath = new XMLPath("lom/general/title/langstring");
        String value = itestGetWidgetType(xmlPath, "textpane");
	}

    public void testGetWidgetType2() {
        XMLPath xmlPath = new XMLPath("blah/size");
        String value = itestGetWidgetType(xmlPath, "numberfield");
	}

    // ---------------------------------------------------------------------------------------------
    
    public void testGetVocabularyList1() {
        SchemaElement schemaElement = sc.getSchemaModel()
        	.getRootElement()
        	.getChild("general")
        	.getChild("title")
        	.getChild("langstring");
        VocabularyList vList = itestGetVocabularyList(schemaElement);
        assertNull("VocabularyList should be null", vList);
    }
    
    public void testGetVocabularyList2() {
        SchemaAttribute schemaAtt = sc.getSchemaModel()
        	.getRootElement()
        	.getChild("general")
        	.getChild("title")
        	.getChild("langstring")
        	.getSchemaAttribute("xml:lang");
        VocabularyList vList = itestGetVocabularyList(schemaAtt);
        assertNotNull("VocabularyList was null", vList);
    }

    // ---------------------------------------------------------------------------------------------
    
    public void testGetDefaultValue1() {
        SchemaAttribute schemaAtt = sc.getSchemaModel()
	        .getRootElement()
	        .getChild("general")
	        .getChild("title")
	        .getChild("langstring")
	        .getSchemaAttribute("xml:lang");
        String value = itestGetDefaultValue(schemaAtt, "en");
    }
    
    public void testGetDefaultValue2() {
        SchemaElement schemaElement = sc.getSchemaModel()
	        .getRootElement()
	        .getChild("general")
	        .getChild("structure")
	        .getChild("source")
	        .getChild("langstring");
        String value = itestGetDefaultValue(schemaElement, "LOMv1.0");
    }

    // ---------------------------------------------------------------------------------------------
    
    public void testGetFacetValue() {
        SchemaAttribute schemaAtt = sc.getSchemaModel()
	        .getRootElement()
	        .getChild("general")
	        .getChild("title")
	        .getChild("langstring")
	        .getSchemaAttribute("xml:lang");
        String value = itestGetFacetValue(schemaAtt, "fname", "Language");
    }
    
    // ---------------------------------------------------------------------------------------------
    
    public void testGetVersion() {
        itestGetVersion(MD_EditorHandler.IMS_METADATA_1_2_1);
    }
    
    // ---------------------------------------------------------------------------------------------
    
    public void testGetRootElementName() {
        itestGetRootElementName("lom");
    }
    
    // ---------------------------------------------------------------------------------------------
    
    public void testGetSchemaFile() {
        File file = itestGetSchemaFile();
        assertEquals("Wrong Schema File", MD121_SchemaController.fileSchemaMD1_2_1, file);
    }

    // ---------------------------------------------------------------------------------------------
    
}
