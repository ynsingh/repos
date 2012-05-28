
package uk.ac.reload.editor.scorm.xml;

import java.io.File;
import java.io.FileNotFoundException;

import org.jdom.Element;

import uk.ac.reload.editor.scorm.xml.SCORM12_CP112_SchemaController;
import uk.ac.reload.editor.scorm.xml.SCORM12_Package;
import uk.ac.reload.jdom.XMLPath;
import uk.ac.reload.moonunit.SchemaDocumentAbstractTest;
import uk.ac.reload.testsupport.scorm.SCORM12_Package1;


/**
 * LD_ContentPackageTest
 * 
 * @author Phillip Beauvoir
 * @version $Id: SCORM12_PackageTest.java,v 1.1 1998/03/26 15:50:37 ynsingh Exp $
 */
public class SCORM12_PackageTest extends SchemaDocumentAbstractTest {

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

    // ---------------------------------------------------------------------------------------------

    public void testSCORM12_Package_File1() throws Exception {
        SCORM12_Package sp = new SCORM12_Package(new File(pathTestData, SCORM12_Package1.fileManifest));
        assertNotNull("SCORM12_Package is null", sp);
        assertTrue("SchemaController is wrong", sp.getSchemaController() instanceof SCORM12_CP112_SchemaController);
        assertNotNull("SCORM12_Package doc is null", sp.getDocument());
    }

    public void testSCORM12_Package_File2() throws Exception {
        try {
            SCORM12_Package sp = new SCORM12_Package((File)null);
            fail("Should have thrown NullPointerException");
        }
        catch(NullPointerException ex) {
            assertTrue(true);
        }
    }

    public void testSCORM12_Package_File3() throws Exception {
        try {
            SCORM12_Package sp = new SCORM12_Package(new File("bogus_file.xml"));
            fail("Should have thrown FileNotFoundException");
        }
        catch(FileNotFoundException ex) {
            assertTrue(true);
        }
    }
    
    // ---------------------------------------------------------------------------------------------

    public void testAddElementByXMLPath1() throws Exception {
        SCORM12_Package sp = new SCORM12_Package(new File(pathTestData, SCORM12_Package1.fileManifest));
        
        XMLPath xmlPath = new XMLPath("manifest");
        Element element = itestAddElementByXMLPath(sp, sp.getRootElement(), xmlPath);
        assertEquals("Wrong Parent", "manifest", element.getParent().getName());
        assertTrue("Element should have children", !element.getContent().isEmpty());
    }
    
    public void testAddElementByXMLPath2() throws Exception {
        SCORM12_Package sp = new SCORM12_Package(new File(pathTestData, SCORM12_Package1.fileManifest));
        
        XMLPath xmlPath = new XMLPath("organizations/organization/item/adlcp:prerequisites");
        Element element = itestAddElementByXMLPath(sp, sp.getRootElement(), xmlPath);
        assertEquals("Wrong Parent", "item", element.getParent().getName());
        assertTrue("Element should have no children", element.getContent().isEmpty());
    }

    // ---------------------------------------------------------------------------------------------
    
}
