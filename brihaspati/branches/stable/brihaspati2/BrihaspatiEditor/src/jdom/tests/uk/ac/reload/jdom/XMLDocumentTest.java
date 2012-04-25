
package uk.ac.reload.jdom;

import java.io.File;

import junit.framework.TestCase;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.Namespace;

import uk.ac.reload.testsupport.FileSupport;
import uk.ac.reload.testsupport.ITestsGlobals;
import uk.ac.reload.testsupport.cp.CP_Package1;
import uk.ac.reload.testsupport.ld.LD_File1;
import uk.ac.reload.testsupport.md.MD_File1;
import uk.ac.reload.testsupport.scorm.SCORM12_Package1;


/**
 * XMLDocumentTest
 * 
 * @author Phillip Beauvoir
 * @version $Id: XMLDocumentTest.java,v 1.1 1998/03/26 16:00:44 ynsingh Exp $
 */
public class XMLDocumentTest
extends TestCase
implements ITestsGlobals
{

    String pathTestData;

    XMLDocument xmlDocument;
    
    protected void setUp() throws Exception {
        // See if we have a testdata dir set from an Ant script
        pathTestData = System.getProperty("testdata.dir");
        if(pathTestData == null) {
            // Set a default
            pathTestData = "../junit-tests";
        }

        xmlDocument = new XMLDocument();
    }

    protected void tearDown() throws Exception {
        // Clean up
        FileSupport.deleteFolder(FileSupport.getMainTestFolder());
    }

    // ---------------------------------------------------------------------------------------------
    
    public void testSetDocument() {
        Document doc = new Document();
        xmlDocument.setDocument(doc);
        assertEquals("Document incorrect", doc, xmlDocument.getDocument());
    }

    // ---------------------------------------------------------------------------------------------
    
    public void testGetDocument() {
        Document doc = new Document();
        xmlDocument.setDocument(doc);
        assertEquals("Document incorrect", doc, xmlDocument.getDocument());
    }

    // ---------------------------------------------------------------------------------------------
    
    public void testSetFile() {
        File file = new File("afile");
        xmlDocument.setFile(file);
        assertEquals("File incorrect", file, xmlDocument.getFile());
    }

    // ---------------------------------------------------------------------------------------------
    
    public void testGetFile() {
        File file = new File("afile");
        xmlDocument.setFile(file);
        assertEquals("File incorrect", file, xmlDocument.getFile());
    }

    // ---------------------------------------------------------------------------------------------
    
    public void testLoadDocument() throws Exception {
        File file = new File(pathTestData, CP_Package1.fileManifest);
        xmlDocument.loadDocument(file);
        assertNotNull("Document is null", xmlDocument.getDocument());
        assertEquals("File incorrect", file, xmlDocument.getFile());
    }

    // ---------------------------------------------------------------------------------------------
    
    public void testSaveDocument() throws Exception {
        File tmpFile = new File(FileSupport.getTempFolder("xml"), "test.xml");
        xmlDocument.setFile(tmpFile);
        xmlDocument.setDocument(FileSupport.getTestDocument());
        xmlDocument.saveDocument();
        assertTrue("Document File does not exist", tmpFile.exists());
    }

    // ---------------------------------------------------------------------------------------------
    
    public void testIsDirty() throws Exception {
        xmlDocument.loadDocument(new File(pathTestData, CP_Package1.fileManifest));
        assertFalse("XMLDocument should not be dirty", xmlDocument.isDirty());
    }

    // ---------------------------------------------------------------------------------------------
    
    public void testSetDirty() {
        xmlDocument.setDirty(true);
        assertTrue("XMLDocument should be dirty", xmlDocument.isDirty());
    }

    // ---------------------------------------------------------------------------------------------
    
    public void testSaveAsDocument() throws Exception {
        File tmpFile = new File(FileSupport.getTempFolder("xml"), "test.xml");
        xmlDocument.setDocument(FileSupport.getTestDocument());
        xmlDocument.saveAsDocument(tmpFile);
        assertTrue("Document File does not exist", tmpFile.exists());
        assertEquals("File incorrect", tmpFile, xmlDocument.getFile());
    }

    // ---------------------------------------------------------------------------------------------
    
    public void testGetRootElement() {
        xmlDocument.setDocument(FileSupport.getTestDocument());
        Element element = xmlDocument.getRootElement();
        assertNotNull("Root element null", element);
        assertEquals("Root element incorrect", "root", element.getName());
    }

    // ---------------------------------------------------------------------------------------------
    
    public void testGetRootNamespace() throws Exception {
        xmlDocument.loadDocument(new File(pathTestData, CP_Package1.fileManifest));
        assertEquals("Schema Namespace incorrect", IMSCP_NAMESPACE_113, xmlDocument.getRootNamespace());
    }

    // ---------------------------------------------------------------------------------------------
    
    public void testIsDocumentNamespace_True() throws Exception {
        xmlDocument.loadDocument(new File(pathTestData, CP_Package1.fileManifest));
        Element element = new Element("ponk", IMSCP_NAMESPACE_113);
        assertTrue("Wrong Namespace", xmlDocument.isDocumentNamespace(element));
    }

    public void testIsDocumentNamespace_False() throws Exception {
        xmlDocument.loadDocument(new File(pathTestData, CP_Package1.fileManifest));
        Element element = new Element("ponk");
        assertFalse("Wrong Namespace", xmlDocument.isDocumentNamespace(element));
    }

    // ---------------------------------------------------------------------------------------------

    public void testIndexOfElement() throws Exception {
        xmlDocument.loadDocument(new File(pathTestData, MD_File1.fileMD));

        Element element = xmlDocument.getRootElement();
        assertEquals("Wrong Index", 0, xmlDocument.indexOfElement(element));
        
        element = element.getChild("technical", element.getNamespace());
        assertEquals("Wrong Index", 3, xmlDocument.indexOfElement(element));
        
        element = element.getChild("requirement", element.getNamespace());
        assertEquals("Wrong Index", 3, xmlDocument.indexOfElement(element));

        element = element.getChild("type", element.getNamespace());
        assertEquals("Wrong Index", 0, xmlDocument.indexOfElement(element));

        element = element.getChild("value", element.getNamespace());
        assertEquals("Wrong Index", 1, xmlDocument.indexOfElement(element));
    }
    
    // ---------------------------------------------------------------------------------------------

    public void testGetPreviousSiblingSameType_Element() {
        Namespace ns = Namespace.getNamespace("www.reload.ac.uk");
        Element root = new Element("root");
        Document doc = new Document(root);
        XMLDocument xmlDocument = new XMLDocument(doc);
        
        Element e1 = new Element("type1");
        root.addContent(e1);
        Element e2 = new Element("type1", ns);
        root.addContent(e2);
        Element e3 = new Element("type2");
        root.addContent(e3);
        Element e4 = new Element("type2", ns);
        root.addContent(e4);
        Element e5 = new Element("type1", ns);
        root.addContent(e5);
        Element e6 = new Element("type1");
        root.addContent(e6);
        
        Element retElement = xmlDocument.getPreviousSiblingSameType(e5);
        assertEquals("Element wrong", e2, retElement);
        
        retElement = xmlDocument.getPreviousSiblingSameType(e6);
        assertEquals("Element wrong", e1, retElement);
        
        retElement = xmlDocument.getPreviousSiblingSameType(e4);
        assertNull("Element wrong", retElement);
    }
    
    // ---------------------------------------------------------------------------------------------
    
    public void testGetPreviousSibling_Element() {
        Namespace ns = Namespace.getNamespace("www.reload.ac.uk");
        Element root = new Element("root");
        Document doc = new Document(root);
        XMLDocument xmlDocument = new XMLDocument(doc);
        
        Element e1 = new Element("type1");
        root.addContent(e1);
        Element e2 = new Element("type1", ns);
        root.addContent(e2);
        Element e3 = new Element("type2");
        root.addContent(e3);
        Element e4 = new Element("type2", ns);
        root.addContent(e4);
        Element e5 = new Element("type1", ns);
        root.addContent(e5);
        Element e6 = new Element("type1");
        root.addContent(e6);
        
        Element retElement = xmlDocument.getPreviousSibling(e6);
        assertEquals("Element wrong", e5, retElement);
        
        retElement = xmlDocument.getPreviousSibling(e5);
        assertEquals("Element wrong", e4, retElement);
        
        retElement = xmlDocument.getPreviousSibling(e1);
        assertNull("Element wrong", retElement);
    }
    
    // ---------------------------------------------------------------------------------------------
    
    public void testGetNextSiblingSameType_Element() {
        Namespace ns = Namespace.getNamespace("www.reload.ac.uk");
        Element root = new Element("root");
        Document doc = new Document(root);
        XMLDocument xmlDocument = new XMLDocument(doc);
        
        Element e1 = new Element("type1");
        root.addContent(e1);
        Element e2 = new Element("type1", ns);
        root.addContent(e2);
        Element e3 = new Element("type2");
        root.addContent(e3);
        Element e4 = new Element("type2", ns);
        root.addContent(e4);
        Element e5 = new Element("type1", ns);
        root.addContent(e5);
        Element e6 = new Element("type1");
        root.addContent(e6);
        
        Element retElement = xmlDocument.getNextSiblingSameType(e2);
        assertEquals("Element wrong", e5, retElement);
        
        retElement = xmlDocument.getNextSiblingSameType(e1);
        assertEquals("Element wrong", e6, retElement);
        
        retElement = xmlDocument.getNextSiblingSameType(e4);
        assertNull("Element wrong", retElement);
    }
    
    // ---------------------------------------------------------------------------------------------
    
    public void testGetNextSibling_Element() {
        Namespace ns = Namespace.getNamespace("www.reload.ac.uk");
        Element root = new Element("root");
        Document doc = new Document(root);
        XMLDocument xmlDocument = new XMLDocument(doc);
        
        Element e1 = new Element("type1");
        root.addContent(e1);
        Element e2 = new Element("type1", ns);
        root.addContent(e2);
        Element e3 = new Element("type2");
        root.addContent(e3);
        Element e4 = new Element("type2", ns);
        root.addContent(e4);
        Element e5 = new Element("type1", ns);
        root.addContent(e5);
        Element e6 = new Element("type1");
        root.addContent(e6);
        
        Element retElement = xmlDocument.getNextSibling(e1);
        assertEquals("Element wrong", e2, retElement);
        
        retElement = xmlDocument.getNextSibling(e4);
        assertEquals("Element wrong", e5, retElement);
        
        retElement = xmlDocument.getNextSibling(e6);
        assertNull("Element wrong", retElement);
    }

    // ---------------------------------------------------------------------------------------------
    
    public void testGetElement1() throws Exception {
        xmlDocument.loadDocument(new File(pathTestData, MD_File1.fileMD));
        XMLPath xmlPath = new XMLPath("lom");
        assertEquals("Wrong Element", "lom", xmlDocument.getElement(xmlPath).getName());
    }
    
    public void testGetElement2() throws Exception {
        xmlDocument.loadDocument(new File(pathTestData, MD_File1.fileMD));
        XMLPath xmlPath = new XMLPath("lom/general/title/langstring");
        assertEquals("Wrong Element", "Draft Standard for Learning Object Metadata",
                xmlDocument.getElement(xmlPath).getText());
    }

    public void testGetElement3() throws Exception {
        xmlDocument.loadDocument(new File(pathTestData, CP_Package1.fileManifest));
        XMLPath xmlPath = new XMLPath("manifest/metadata/imsmd:lom/general/title/langstring");
        assertEquals("Wrong Element", "Draft Standard for Learning Object Metadata",
                xmlDocument.getElement(xmlPath).getText());
    }

    public void testGetElement4() throws Exception {
        xmlDocument.loadDocument(new File(pathTestData, SCORM12_Package1.fileManifest));
        XMLPath xmlPath = new XMLPath("manifest/metadata/imsmd:lom/general/title/langstring");
        assertEquals("Wrong Element", "Test Metadata",
                xmlDocument.getElement(xmlPath).getText());
    }

    // ---------------------------------------------------------------------------------------------
    
    public void testGetElements1() throws Exception {
        xmlDocument.loadDocument(new File(pathTestData, MD_File1.fileMD));
        XMLPath xmlPath = new XMLPath("lom");
        Element[] elements = xmlDocument.getElements(xmlPath);
        assertEquals("Elements size wrong", 1, elements.length);
        assertEquals("Wrong Element", "lom", elements[0].getName());
    }

    public void testGetElements2() throws Exception {
        xmlDocument.loadDocument(new File(pathTestData, MD_File1.fileMD));
        XMLPath xmlPath = new XMLPath("lom/general");
        Element[] elements = xmlDocument.getElements(xmlPath);
        assertEquals("Elements size wrong", 1, elements.length);
        assertEquals("Wrong Element", "general", elements[0].getName());
    }

    public void testGetElements3() throws Exception {
        xmlDocument.loadDocument(new File(pathTestData, MD_File1.fileMD));
        XMLPath xmlPath = new XMLPath("lom/general/keyword");
        Element[] elements = xmlDocument.getElements(xmlPath);
        assertEquals("Elements size wrong", 2, elements.length);
        for(int i = 0; i < elements.length; i++) {
            assertEquals("Wrong Element", "keyword", elements[i].getName());
        }
    }

    public void testGetElements4() throws Exception {
        xmlDocument.loadDocument(new File(pathTestData, CP_Package1.fileManifest));
        XMLPath xmlPath = new XMLPath("manifest/metadata/imsmd:lom/general/keyword");
        Element[] elements = xmlDocument.getElements(xmlPath);
        assertEquals("Elements size wrong", 2, elements.length);
        for(int i = 0; i < elements.length; i++) {
            assertEquals("Wrong Element", "keyword", elements[i].getName());
        }
    }

    // ---------------------------------------------------------------------------------------------

    /**
     * getElement(Element, XMLPath)
     */
    public void testGetElement_Element_XMLPath1() throws Exception {
        xmlDocument.loadDocument(new File(pathTestData, MD_File1.fileMD));
        
        XMLPath xmlPath = new XMLPath("general");
        Element parent = xmlDocument.getRootElement();
        Element element = xmlDocument.getElement(parent, xmlPath);
        assertNotNull("Element is null", element);
        assertEquals("Element is wrong", "general", element.getName());
        
        xmlPath = new XMLPath("general/title/langstring");
        element = xmlDocument.getElement(parent, xmlPath);
        assertNotNull("Element is null", element);
        assertEquals("Element is wrong", "langstring", element.getName());
    }
    
    /**
     * getElement(Element, XMLPath) with Namespace
     */
    public void testGetElement_Element_XMLPath2() throws Exception {
        xmlDocument.loadDocument(new File(pathTestData, LD_File1.fileLD));
        
        XMLPath xmlPath = new XMLPath("organizations/imsld:learning-design");
        Element parent = xmlDocument.getRootElement();
        Element element = xmlDocument.getElement(parent, xmlPath);
        assertNotNull("Element is null", element);
        assertEquals("Element is wrong", "learning-design", element.getName());

        xmlPath = new XMLPath("imsld:learning-design");
        parent = xmlDocument.getRootElement().getChild("organizations", parent.getNamespace());
        element = xmlDocument.getElement(parent, xmlPath);
        assertNotNull("Element is null", element);
        assertEquals("Element is wrong", "learning-design", element.getName());
    }

    // ---------------------------------------------------------------------------------------------
}
