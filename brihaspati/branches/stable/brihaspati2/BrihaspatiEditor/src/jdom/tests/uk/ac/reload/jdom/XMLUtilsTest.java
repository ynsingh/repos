
package uk.ac.reload.jdom;

import java.io.File;

import junit.framework.TestCase;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.Namespace;

import uk.ac.reload.testsupport.FileSupport;
import uk.ac.reload.testsupport.ITestsGlobals;
import uk.ac.reload.testsupport.cp.CP_Package1;
import uk.ac.reload.testsupport.scorm.SCORM12_Package1;
import uk.ac.reload.testsupport.scorm.SCORM12_Package2;


/**
 * Description
 * 
 * @author Phillip Beauvoir
 * @version $Id: XMLUtilsTest.java,v 1.1 1998/03/26 16:00:44 ynsingh Exp $
 */
public class XMLUtilsTest
extends TestCase
implements ITestsGlobals
{
    
    String pathTestData;

    protected void setUp() throws Exception {
        // See if we have a testdata dir set from an Ant script
        pathTestData = System.getProperty("testdata.dir");
        if(pathTestData == null) {
            // Set a default
            pathTestData = "../junit-tests";
        }
    }

    protected void tearDown() throws Exception {
        // Clean up
        FileSupport.deleteFolder(FileSupport.getMainTestFolder());
    }

    // ---------------------------------------------------------------------------------------------
    
    /**
     * Write an XML file
     */
    public void testWrite2XMLFile() throws Exception {
        File tmpFile = new File(FileSupport.getTempFolder("xml"), "test.xml");
        Document doc = FileSupport.getTestDocument();
        XMLUtils.write2XMLFile(doc, tmpFile);
        assertTrue("Document File does not exist", tmpFile.exists());
    }

    // ---------------------------------------------------------------------------------------------
    
    /**
     * Write an XML String
     */
    public void testWrite2XMLString() throws Exception {
        Document doc = FileSupport.getTestDocument();
        String CR = System.getProperty("line.separator");
        String expected = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + CR + "<root />" + CR + CR;
        String result = XMLUtils.write2XMLString(doc);
        assertEquals("Document String wrong", expected, result);
    }

    // ---------------------------------------------------------------------------------------------
    
    public void testReadXMLFile_FileStringString() {
        // We don't use this yet
    }

    // ---------------------------------------------------------------------------------------------
    
    /**
     * Reading an XML File and that Document is not null
     */
    public void testReadXMLFile_File() throws Exception {
        Document doc = XMLUtils.readXMLFile(new File(pathTestData, CP_Package1.fileManifest));
        assertNotNull("Document was null", doc);
    }

    // ---------------------------------------------------------------------------------------------
    
    /**
     * Read an XML String and return a Document
     */
    public void testReadXMLString() throws Exception {
        String testString = "<root> <element att=\"hello\">Some text</element> </root>";
        Document doc = XMLUtils.readXMLString(testString);
        assertNotNull("Document was null", doc);
    }

    // ---------------------------------------------------------------------------------------------
    
    /**
     * Replace Namespaces
     */
    public void testReplaceNamespaces() throws Exception {
        Document doc = XMLUtils.readXMLFile(new File(pathTestData, CP_Package1.fileManifest));
        
        // Replace Metadata Namespace
        Element root = doc.getRootElement();
        Namespace oldNS = root.getNamespace("imsmd");
        Namespace newNS = Namespace.getNamespace("reload", "http://www.reload.ac.uk");
        XMLUtils.replaceNamespaces(root, oldNS, newNS);
        
        // Now try to get an Element with that Namespace
        Element mdElement = root.getChild("metadata", root.getNamespace());
        Element lomElement = mdElement.getChild("lom", newNS);
        
        assertNotNull("Namespace should have been replaced", lomElement);
    }

    // ---------------------------------------------------------------------------------------------
    
    public void testGetSchemaLocation() throws Exception {
        Document doc = XMLUtils.readXMLFile(new File(pathTestData, CP_Package1.fileManifest));
        
        String sl = XMLUtils.getSchemaLocation(doc, doc.getRootElement().getNamespace());
        assertEquals("Schema location not correct", "imscp_v1p1.xsd", sl);
    }

    // ---------------------------------------------------------------------------------------------
    
    public void testGetDocumentNamespace_Document() throws Exception {
        Document doc = XMLUtils.readXMLFile(new File(pathTestData, CP_Package1.fileManifest));
        
        Namespace ns = XMLUtils.getDocumentNamespace(doc);
        Namespace expected = Namespace.getNamespace("http://www.imsglobal.org/xsd/imscp_v1p1");
        assertEquals("Namespace not correct", expected, ns);
    }

    // ---------------------------------------------------------------------------------------------
    
    public void testGetDocumentNamespace_DocumentString() throws Exception {
        Document doc = XMLUtils.readXMLFile(new File(pathTestData, CP_Package1.fileManifest));
        
        Namespace ns = XMLUtils.getDocumentNamespace(doc, "imsmd");
        Namespace expected = Namespace.getNamespace("http://www.imsglobal.org/xsd/imsmd_v1p2");
        assertEquals("Namespace not correct", expected, ns);
    }

    // ---------------------------------------------------------------------------------------------
    
    public void testContainsNamespace_Doc1() throws Exception {
        Document doc = XMLUtils.readXMLFile(new File(pathTestData, CP_Package1.fileManifest));
        
        // Normal
        Namespace ns = IMSCP_NAMESPACE_113;
        assertTrue("Namespace not found", XMLUtils.containsNamespace(doc, ns));
        
        // Metadata
        ns = IMSMD_NAMESPACE_122;
        assertTrue("Namespace not found", XMLUtils.containsNamespace(doc, ns));

        // Not present
        ns = IMSMD_NAMESPACE_121;
        assertFalse("Namespace should not be found", XMLUtils.containsNamespace(doc, ns));

        // Embedded NS
        ns = Namespace.getNamespace("reload", "http://www.reload.ac.uk");
        assertTrue("Namespace not found", XMLUtils.containsNamespace(doc, ns));
        
        ns = Namespace.getNamespace("http://www.reload.ac.uk");
        assertTrue("Namespace not found", XMLUtils.containsNamespace(doc, ns));
        
        // Embedded NS that is an additional NS
        ns = Namespace.getNamespace("http://www.somewhere.com");
        assertTrue("Namespace not found", XMLUtils.containsNamespace(doc, ns));
    }
        
    public void testContainsNamespace_Doc2() throws Exception {
        // SCORM 
        Document doc = XMLUtils.readXMLFile(new File(pathTestData, SCORM12_Package1.fileManifest));
        Namespace ns = ADLCP_NAMESPACE_12;
        assertTrue("Namespace not found", XMLUtils.containsNamespace(doc, ns));
    }
        
    public void testContainsNamespace_Doc3() throws Exception {
        // SCORM with no elements, Namespace is additional namespace
        Document doc = XMLUtils.readXMLFile(new File(pathTestData, SCORM12_Package2.fileManifest));
        Namespace ns = ADLCP_NAMESPACE_12;
        assertTrue("Namespace not found", XMLUtils.containsNamespace(doc, ns));
    }

    // ---------------------------------------------------------------------------------------------
    
    public void testIsMemberOfSameDocument_False() {
        Document doc1 = new Document();
        Element root1 = new Element("root");
        doc1.setRootElement(root1);
        
        Document doc2 = new Document();
        Element root2 = new Element("root");
        doc2.setRootElement(root2);
        
        assertFalse("Elements should be in different Documents",
                XMLUtils.isMemberOfSameDocument(root1, root2));
    }

    public void testIsMemberOfSameDocument_True() {
        Document doc = new Document();
        Element root = new Element("root");
        doc.setRootElement(root);
        
        Element element = new Element("element");
        root.addContent(element);
        
        assertTrue("Elements should be in same Document",
                XMLUtils.isMemberOfSameDocument(root, element));
    }

    // ---------------------------------------------------------------------------------------------
    

}
