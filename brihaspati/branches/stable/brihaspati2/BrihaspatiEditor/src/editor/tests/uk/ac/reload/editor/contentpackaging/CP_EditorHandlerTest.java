
package uk.ac.reload.editor.contentpackaging;

import java.io.File;

import junit.framework.TestCase;

import org.jdom.Comment;
import org.jdom.Document;
import org.jdom.Namespace;

import uk.ac.reload.diva.util.FileUtils;
import uk.ac.reload.editor.EditorInternalFrame;
import uk.ac.reload.editor.contentpackaging.editor.CP_Editor;
import uk.ac.reload.editor.contentpackaging.xml.CP112_SchemaController;
import uk.ac.reload.editor.contentpackaging.xml.CP113_SchemaController;
import uk.ac.reload.editor.metadata.MD_EditorHandler;
import uk.ac.reload.editor.properties.EditorProperties;
import uk.ac.reload.editor.scorm.SCORM12_EditorHandler;
import uk.ac.reload.jdom.XMLUtils;
import uk.ac.reload.moonunit.SchemaController;
import uk.ac.reload.moonunit.contentpackaging.CP_Core;
import uk.ac.reload.testsupport.FileSupport;
import uk.ac.reload.testsupport.cp.CP_Package1;
import uk.ac.reload.testsupport.cp.WebCT_CP_Package1;
import uk.ac.reload.testsupport.cp.Zip_Package1;
import uk.ac.reload.testsupport.md.MD_File1;
import uk.ac.reload.testsupport.scorm.SCORM12_Package1;
import uk.ac.reload.testsupport.scorm.SCORM12_Package2;


/**
 * CP_EditorHandlerTest
 * 
 * @author Phillip Beauvoir
 * @version $Id: CP_EditorHandlerTest.java,v 1.1 1998/03/26 15:50:37 ynsingh Exp $
 */
public class CP_EditorHandlerTest extends TestCase {

    CP_EditorHandler handlerCP;
    
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

		handlerCP = new CP_EditorHandler();
    }
    
    protected void tearDown() throws Exception {
        // Clean up
        FileUtils.deleteFolder(FileSupport.getMainTestFolder());
    }

    // ---------------------------------------------------------------------------------------------
    
    public void testCanCreateDocuments() {
        assertTrue("Should be able to create Documents", handlerCP.canCreateDocuments());
    }

    // ---------------------------------------------------------------------------------------------
    
    public void testCanEditFile1() {
        File manifest = new File(pathTestData, WebCT_CP_Package1.fileManifest);
        assertTrue("Should be able to Edit File: " + manifest, handlerCP.canEditFile(manifest));
    }

    public void testCanEditFile2() {
        File manifest = new File(pathTestData, CP_Package1.fileManifest);
        assertTrue("Should be able to Edit File: " + manifest, handlerCP.canEditFile(manifest));
    }
        
    public void testCanEditFile3() {
        // MD file should not load
        File manifest = new File(pathTestData, MD_File1.fileMD);
        assertFalse("Should not be able to Edit File: " + manifest, handlerCP.canEditFile(manifest));
    }

    public void testCanEditFile4() {
        // SCORM files should not load
        File manifest = new File(pathTestData, SCORM12_Package1.fileManifest);
        assertFalse("Should not be able to Edit File: " + manifest, handlerCP.canEditFile(manifest));
        manifest = new File(pathTestData, SCORM12_Package2.fileManifest);
        assertFalse("Should not be able to Edit File: " + manifest, handlerCP.canEditFile(manifest));
    }

    // ---------------------------------------------------------------------------------------------
    
    public void testEditFile() throws Exception {
        // Need a Canonical File for this test
        File manifest = new File(pathTestData, CP_Package1.fileManifest).getCanonicalFile();
        EditorInternalFrame frame = handlerCP.editFile(manifest);
        assertTrue("EditorInternalFrame wrong", frame instanceof CP_Editor);
    }

    // ---------------------------------------------------------------------------------------------
    
    public void testReplaceBogusNamespace() throws Exception {
        // Make a copy of the manifest
        File src = new File(pathTestData, WebCT_CP_Package1.fileManifest);
        File manifest = FileSupport.getTempFile(".xml");
        FileUtils.copyFile(src, manifest);
        
        boolean result = handlerCP.replaceBogusNamespace(manifest, false);
        
        assertTrue("CheckforBogusNamespace should return true", result);
        
        File bak = new File(manifest.getPath() + ".bak");
        assertTrue("CheckforBogusNamespace should have made backup file", bak.exists());
        
        Document doc = XMLUtils.readXMLFile(manifest);
        Namespace nsCP = XMLUtils.getDocumentNamespace(doc);
        
        assertEquals("Namespace wrong", CP_EditorHandler.IMSCP_NAMESPACE_113, nsCP);
        assertTrue("Namespace wrong", XMLUtils.containsNamespace(doc, MD_EditorHandler.IMSMD_NAMESPACE_122));
        
        assertEquals("Comment wrong", "Converted deprecated Namespaces to correct Namespaces by " +
                EditorProperties.getString("APP_NAME"), ((Comment)doc.getContent().get(0)).getText());
        
    }

    // ---------------------------------------------------------------------------------------------
    
    public void testGetDocument1() throws Exception {
        File manifest = new File(pathTestData, CP_Package1.fileManifest);
        Document doc = handlerCP.getDocument(manifest);
        assertNotNull("Document was null", doc);
    }
        
    public void testGetDocument2() throws Exception {
        // Zip file
        File zipFile = new File(pathTestData, Zip_Package1.fileZip);
        Document doc = handlerCP.getDocument(zipFile);
        assertNotNull("Document was null", doc);
    }
    
    public void testGetDocument3() {    
        try {
            // Bogus
            handlerCP.getDocument(new File("abogusfile"));
            fail("Should have thrown Exception");
        }
        catch(Exception ex) {
            assertTrue(true);
        } 
    }
        
    public void testGetDocument4() {
        try {
            // Null
            handlerCP.getDocument(null);
            fail("Should have thrown Exception");
        }
        catch(Exception ex) {
            assertTrue(true);
        } 
    }

    // ---------------------------------------------------------------------------------------------
    
    public void testGetName() {
        assertNotNull("No Name", handlerCP.getName());
    }

    // ---------------------------------------------------------------------------------------------
    
    public void testGetIcon() {
        assertNotNull("No Icon", handlerCP.getIcon());
    }

    // ---------------------------------------------------------------------------------------------
    
    public void testGetSupportedVersions() {
        assertNotNull("No Supported Versions", handlerCP.getSupportedVersions());
    }

    // ---------------------------------------------------------------------------------------------
    
    public void testGetDefaultVersion() {
        assertNotNull("No Default Version", handlerCP.getDefaultVersion());
    }

    // ---------------------------------------------------------------------------------------------
    
    public void testGetDefaultMDVersion() {
        assertNotNull("No Default MD Version", handlerCP.getDefaultVersion());
    }

    // ---------------------------------------------------------------------------------------------
    
    public void testGetVersion1() {
        String version = handlerCP.getVersion(CP_EditorHandler.IMSCP_NAMESPACE_112);
        assertEquals("Version wrong", CP_EditorHandler.IMS_CONTENT_PACKAGING_1_1_2, version);
    }

    public void testGetVersion2() {
    	String version = handlerCP.getVersion(CP_EditorHandler.IMSCP_NAMESPACE_113);
        assertEquals("Version wrong", CP_EditorHandler.IMS_CONTENT_PACKAGING_1_1_3, version);
    }
    
    public void testGetVersion3() {
        String version = handlerCP.getVersion(Namespace.getNamespace("http://www.bogus.com"));
        assertNull("Version wrong", version);
    }

    // ---------------------------------------------------------------------------------------------
    
    public void testContainsManifest1() throws Exception {
        File zipFile = new File(pathTestData, Zip_Package1.fileZip);
        assertTrue("Should contain a manifest", CP_EditorHandler.containsManifest(zipFile));
    }
        
    public void testContainsManifest2() throws Exception {
        File manifest = new File(pathTestData, CP_Package1.fileManifest);
        assertFalse("Should not contain a manifest", CP_EditorHandler.containsManifest(manifest));
    }

    // ---------------------------------------------------------------------------------------------
    
    public void testUnzipContentPackage() throws Exception {
        File zipFile = new File(pathTestData, Zip_Package1.fileZip);
        File tempFolder = FileSupport.getTempFolder("zip");
        CP_EditorHandler.unzipContentPackage(zipFile, tempFolder, null);
        File manifestFile = new File(tempFolder, CP_Core.MANIFEST_NAME);
        assertTrue("Unzipped Manifest should exist", manifestFile.exists());
    }

    // ---------------------------------------------------------------------------------------------
    
    public void testGetSCORM_Namespace1() throws Exception {
        File file = new File(pathTestData, SCORM12_Package1.fileManifest);
        Document doc = handlerCP.getDocument(file);
        assertEquals("Should be SCORM Package", SCORM12_EditorHandler.ADLCP_NAMESPACE_12,
                CP_EditorHandler.getSCORM_Namespace(doc));
    }
        
    public void testGetSCORM_Namespace2() throws Exception {
        File file = new File(pathTestData, SCORM12_Package2.fileManifest);
        Document doc = handlerCP.getDocument(file);
        assertEquals("Should be SCORM Package", SCORM12_EditorHandler.ADLCP_NAMESPACE_12,
                CP_EditorHandler.getSCORM_Namespace(doc));
    }

    // ---------------------------------------------------------------------------------------------
    
    public void testGetSchemaControllerInstance1() throws Exception {
        SchemaController controller =
            handlerCP.getSchemaControllerInstance(CP_EditorHandler.IMS_CONTENT_PACKAGING_1_1_2);
        assertTrue("Schema Controller not found", controller instanceof CP112_SchemaController);
    }

    public void testGetSchemaControllerInstance2() throws Exception {
        SchemaController controller =
            handlerCP.getSchemaControllerInstance(CP_EditorHandler.IMS_CONTENT_PACKAGING_1_1_3);
        assertTrue("Schema Controller not found", controller instanceof CP113_SchemaController);
    }

    // ---------------------------------------------------------------------------------------------
    
}
