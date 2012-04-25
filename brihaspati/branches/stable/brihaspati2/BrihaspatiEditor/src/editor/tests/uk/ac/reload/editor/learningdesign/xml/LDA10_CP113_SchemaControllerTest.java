
package uk.ac.reload.editor.learningdesign.xml;

import java.io.File;

import org.jdom.Namespace;

import uk.ac.reload.editor.contentpackaging.xml.CP113_SchemaControllerTest;
import uk.ac.reload.editor.contentpackaging.xml.CP_SchemaController;
import uk.ac.reload.editor.learningdesign.LD_EditorHandler;
import uk.ac.reload.editor.metadata.xml.MD11_SchemaController;
import uk.ac.reload.editor.metadata.xml.MD121_SchemaController;
import uk.ac.reload.jdom.XMLPath;
import uk.ac.reload.moonunit.schema.SchemaAttribute;
import uk.ac.reload.moonunit.schema.SchemaElement;
import uk.ac.reload.moonunit.schema.SchemaNode;
import uk.ac.reload.moonunit.vocab.VocabularyList;
import uk.ac.reload.testsupport.FileSupport;




/**
 * LDA10_CP113_SchemaControllerTest
 * 
 * @author Phillip Beauvoir
 * @version $Id: LDA10_CP113_SchemaControllerTest.java,v 1.1 1998/03/26 15:50:37 ynsingh Exp $
 */
public class LDA10_CP113_SchemaControllerTest extends CP113_SchemaControllerTest {

    protected void setUp() throws Exception {
		super.setUp();
        sc = new LDA10_CP113_SchemaController();
    }
    
    // ---------------------------------------------------------------------------------------------
    // ------------------------------------ From Here ----------------------------------------------
    // ---------------------------------------------------------------------------------------------

    public void testGetLevel() {
        assertEquals("A", ((LDA10_CP113_SchemaController)sc).getLevel());
    }
    
    // ---------------------------------------------------------------------------------------------
    // ----------------------------- From CP113_SchemaController -----------------------------------
    // ---------------------------------------------------------------------------------------------
    
    /**
     * No Metadata SchemaController
     */
    public void testCopySchemaFilesToFolder1() throws Exception {
        File tmpFolder = FileSupport.getTempFolder("cp");
        File[] files = {
                new File(tmpFolder, "ims_xml.xsd"),
                new File(tmpFolder, "imscp_v1p1.xsd"),
                new File(tmpFolder, "IMS_LD_Level_A.xsd"),
                new File(tmpFolder, "IMS_LD_Level_A_emaildata.xsd"),
                new File(tmpFolder, "IMS_LD_Level_A_reusables.xsd"),
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
                new File(tmpFolder, "imscp_v1p1.xsd"),
                new File(tmpFolder, "IMS_LD_Level_A.xsd"),
                new File(tmpFolder, "IMS_LD_Level_A_emaildata.xsd"),
                new File(tmpFolder, "IMS_LD_Level_A_reusables.xsd"),
                new File(tmpFolder, "imsmd_v1p2p2.xsd")
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
                new File(tmpFolder, "imscp_v1p1.xsd"),
                new File(tmpFolder, "IMS_LD_Level_A.xsd"),
                new File(tmpFolder, "IMS_LD_Level_A_emaildata.xsd"),
                new File(tmpFolder, "IMS_LD_Level_A_reusables.xsd"),
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
                new File(tmpFolder, "imscp_v1p1.xsd"),
                new File(tmpFolder, "IMS_LD_Level_A.xsd"),
                new File(tmpFolder, "IMS_LD_Level_A_emaildata.xsd"),
                new File(tmpFolder, "IMS_LD_Level_A_reusables.xsd"),
                new File(tmpFolder, "ims_md_rootv1p1.xsd")
        };
        
        CP_SchemaController cpsc = (CP_SchemaController)sc;
        cpsc.setMD_SchemaController(new MD11_SchemaController());
        itestCopySchemaFilesToFolder(files, tmpFolder);
    }

    
    // ---------------------------------------------------------------------------------------------

    
    // ---------------------------------------------------------------------------------------------
    // ----------------------------- From SchemaController -----------------------------------------
    // ---------------------------------------------------------------------------------------------
    

    // ---------------------------------------------------------------------------------------------
    
    public void testIsTopLevelElement_False() {
		SchemaElement schemaElement = sc.getSchemaModel()
			.getRootElement()
			.getChild("organizations")
			.getChild("imsld:learning-design")
			.getChild("components")
			.getChild("roles")
			.getChild("learner");
        
        itestIsTopLevelElement(schemaElement, false);
    }

    public void testIsTopLevelElement_True() {
		SchemaElement schemaElement = sc.getSchemaModel()
			.getRootElement()
			.getChild("organizations");
        
        itestIsTopLevelElement(schemaElement, true);
    }

    // ---------------------------------------------------------------------------------------------
    
    public void testGetSchemaNode1() {
        XMLPath xmlPath = new XMLPath("");
        SchemaNode node = itestGetSchemaNode(xmlPath);
        assertEquals("Wrong SchemaNode", "manifest", node.getName());
    }

    public void testGetSchemaNode2() {
        XMLPath xmlPath = new XMLPath("organizations/imsld:learning-design/components");
        SchemaNode node = itestGetSchemaNode(xmlPath);
        assertEquals("Wrong SchemaNode", "components", node.getName());
    }

    public void testGetSchemaNode3() {
        XMLPath xmlPath = new XMLPath("organizations/imsld:learning-design/prerequisites/item/item@identifier");
        SchemaNode node = itestGetSchemaNode(xmlPath);
        assertEquals("Wrong SchemaNode", "identifier", node.getName());
        assertEquals("Wrong SchemaNode namespace", Namespace.NO_NAMESPACE, node.getNamespace());
    }

    /**
     * Works if root part is missing
     */
    public void testGetSchemaNode4() {
        XMLPath xmlPath = new XMLPath("organizations/imsld:learning-design/prerequisites/item/item@identifier");
        SchemaNode node = itestGetSchemaNode(xmlPath);
        assertEquals("Wrong SchemaNode", "identifier", node.getName());
        assertEquals("Wrong SchemaNode namespace", Namespace.NO_NAMESPACE, node.getNamespace());
    }

    // ---------------------------------------------------------------------------------------------
    
    public void testGetVocabularyList1() {
        SchemaAttribute schemaAtt = sc.getSchemaModel()
        	.getRootElement()
			.getChild("organizations")
			.getChild("imsld:learning-design")
        	.getChild("components")
        	.getChild("environments")
        	.getChild("environment")
        	.getChild("service")
        	.getChild("send-mail")
        	.getSchemaAttribute("select");
        VocabularyList vList = itestGetVocabularyList(schemaAtt);
        assertNotNull("VocabularyList was null", vList);
    }

    public void testGetVocabularyList2() {
        SchemaAttribute schemaAtt = sc.getSchemaModel()
        	.getRootElement()
			.getChild("organizations")
			.getChild("imsld:learning-design")
        	.getChild("components")
        	.getChild("environments")
        	.getChild("environment")
        	.getChild("service")
        	.getChild("conference")
        	.getSchemaAttribute("conference-type");
        VocabularyList vList = itestGetVocabularyList(schemaAtt);
        assertNotNull("VocabularyList was null", vList);
    }

    // ---------------------------------------------------------------------------------------------
    
    public void testGetDefaultValue1() {
        SchemaAttribute schemaAtt = sc.getSchemaModel()
	        .getRootElement()
			.getChild("organizations")
			.getChild("imsld:learning-design")
	        .getChild("components")
	        .getChild("roles")
	        .getChild("learner")
	        .getSchemaAttribute("create-new");
        String value = itestGetDefaultValue(schemaAtt, "allowed");
    }
    
    // ---------------------------------------------------------------------------------------------
    
    public void testGetVersion() {
        itestGetVersion(LD_EditorHandler.IMS_LEARNING_DESIGN_A_1_0_CP_1_1_3);
    }
    
    // ---------------------------------------------------------------------------------------------
    
    public void testGetRootElementName() {
        itestGetRootElementName("manifest");
    }
    
    // ---------------------------------------------------------------------------------------------
    
    public void testGetSchemaFile() {
        File file = itestGetSchemaFile();
        assertEquals("Wrong Schema File", LDA10_CP113_SchemaController.fileSchemaCP1_1_3, file);
    }

    // ---------------------------------------------------------------------------------------------
    
}
