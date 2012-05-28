
package uk.ac.reload.jdom;

import java.util.StringTokenizer;

import junit.framework.TestCase;

import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.Namespace;


/**
 * XMLPathTest
 * 
 * @author Phillip Beauvoir
 * @version $Id: XMLPathTest.java,v 1.1 1998/03/26 16:00:44 ynsingh Exp $
 */
public class XMLPathTest extends TestCase {

    String testPath;
    XMLPath xmlPath;
    
    protected void setUp() {
        testPath = "root/sub1/element";
        xmlPath = new XMLPath(testPath);
    }
    
    // ---------------------------------------------------------------------------------------------
    
    /**
     * Test Constructor
     */
    public void testXMLPath_XMLPath() {
        XMLPath xmlPath2 = new XMLPath(xmlPath);
        assertEquals("XMLPath incorrect", testPath, xmlPath2.getPath());
    }

    // ---------------------------------------------------------------------------------------------
    
    public void testGetPath() {
        assertEquals("XMLPath incorrect", testPath, xmlPath.getPath());
    }

    // ---------------------------------------------------------------------------------------------
    
    public void testAppendElementName1() {
        xmlPath.appendElementName("tail");
        String expected = testPath + "/tail";
        assertEquals("XMLPath incorrect", expected, xmlPath.getPath());
    }

    /**
     * Can't add on null or empty string
     */
    public void testAppendElementName2() {
        xmlPath.appendElementName("");
        String expected = testPath;
        assertEquals("XMLPath incorrect", expected, xmlPath.getPath());
    }

    /**
     * Can't add on null or empty string
     */
    public void testAppendElementName3() {
        xmlPath.appendElementName(null);
        String expected = testPath;
        assertEquals("XMLPath incorrect", expected, xmlPath.getPath());
    }

    // ---------------------------------------------------------------------------------------------
    
    public void testPrependElementName() {
        xmlPath.prependElementName("tail");
        String expected = "tail/" + testPath;
        assertEquals("XMLPath incorrect", expected, xmlPath.getPath());
    }

    // ---------------------------------------------------------------------------------------------
    
    public void testAppendAttributeName() {
        xmlPath.appendAttributeName("att");
        String expected = testPath + "@att";
        assertEquals("XMLPath incorrect", expected, xmlPath.getPath());
    }

    // ---------------------------------------------------------------------------------------------
    
    public void testGetElements() {
        // Add an attribute to prove we don't get that returned
        xmlPath.appendAttributeName("att");
        
        StringTokenizer t = xmlPath.getElements();
        String[] s = new String[3];
        
        assertEquals("Wrong number of elements", 3, t.countTokens());
        
        int i = 0;
        while(t.hasMoreElements()) {
            s[i++] = t.nextToken();
        }
        
        assertEquals("XMLPath incorrect", "root", s[0]);
        assertEquals("XMLPath incorrect", "sub1", s[1]);
        assertEquals("XMLPath incorrect", "element", s[2]);
    }

    // ---------------------------------------------------------------------------------------------
    
    public void testIsAttribute_True() {
        xmlPath.appendAttributeName("att");
        assertTrue("IsAttribute should be true", xmlPath.isAttribute());
    }

    public void testIsAttribute_False() {
        assertFalse("IsAttribute should be false", xmlPath.isAttribute());
    }

    // ---------------------------------------------------------------------------------------------
    
    public void testGetAttributePart() {
        xmlPath.appendAttributeName("att");
        assertEquals("Attribute incorrect", "att", xmlPath.getAttributePart());
    }

    // ---------------------------------------------------------------------------------------------
    
    public void testGetElementsPart() {
        xmlPath.appendAttributeName("att");
        assertEquals("XMLPath incorrect", testPath, xmlPath.getElementsPart());
    }

    // ---------------------------------------------------------------------------------------------
    
    public void testGetRootPart() {
        assertEquals("XMLPath incorrect", "root", xmlPath.getRootPart());
    }

    // ---------------------------------------------------------------------------------------------
    
    public void testGetLastPart1() {
        assertEquals("XMLPath incorrect", "element", xmlPath.getLastPart());
    }

    public void testGetLastPart2() {
        xmlPath.appendAttributeName("att");
        assertEquals("XMLPath incorrect", "att", xmlPath.getLastPart());
    }

    // ---------------------------------------------------------------------------------------------
    public void testEquals_True() {
        XMLPath xmlPath2 = new XMLPath(testPath);
        assertTrue("XMLPath should be same", xmlPath.equals(xmlPath2));
    }

    public void testEquals_False() {
        XMLPath xmlPath2 = new XMLPath("root/sub1/element@pok");
        assertFalse("XMLPath should not be same", xmlPath.equals(xmlPath2));
    }

    // ---------------------------------------------------------------------------------------------
    
    public void testEndsWith_True() {
        XMLPath xmlPath2 = new XMLPath("sub1/element");
        assertTrue("XMLPath should end with string", xmlPath.endsWith(xmlPath2));
    }

    public void testEndsWith_False() {
        XMLPath xmlPath2 = new XMLPath("sub1/element@pok");
        assertFalse("XMLPath should not end with string", xmlPath.endsWith(xmlPath2));
    }

    // ---------------------------------------------------------------------------------------------
    
    public void testGetXMLPathForElement1() {
        Element element1 = new Element("root");
        Element element2 = new Element("sub1");
        element1.addContent(element2);
        
        // This should not show up
        element2.setAttribute(new Attribute("att", "att_value"));
        
        XMLPath xmlPath = XMLPath.getXMLPathForElement(element2);
        assertEquals("XMLPath incorrect", "root/sub1", xmlPath.getPath());
    }

    public void testGetXMLPathForElement2() {
        // Try with different Namespace
        
        Element element1 = new Element("root", Namespace.getNamespace("imscp", "http://www.somehow.com"));
        Element element2 = new Element("sub1", Namespace.getNamespace("imsmd", "http://www.somewhere.com"));
        Element element3 = new Element("diff");
        element1.addContent(element2);
        element2.addContent(element3);
        
        XMLPath xmlPath = XMLPath.getXMLPathForElement(element3);
        assertEquals("XMLPath incorrect", "root/imsmd:sub1/diff", xmlPath.getPath());
    }

    public void testGetXMLPathForElement3() {
        // Try with different Namespace
        
        Element element1 = new Element("root", Namespace.getNamespace("imscp", "http://www.somehow.com"));
        Element element2 = new Element("sub1");
        Element element3 = new Element("diff", Namespace.getNamespace("imsmd", "http://www.somewhere.com"));
        element1.addContent(element2);
        element2.addContent(element3);
        
        XMLPath xmlPath = XMLPath.getXMLPathForElement(element3);
        assertEquals("XMLPath incorrect", "root/sub1/imsmd:diff", xmlPath.getPath());
    }
    
    /**
     * Test for case where there is an inline namespace declaration as well as in the root
     */
    public void testGetXMLPathForElement4() throws Exception {
        StringBuffer docString = new StringBuffer("<?xml version=\"1.0\"?>");
        docString.append("<manifest xmlns=\"http://www.imsglobal.org/xsd/imscp_v1p1\" xmlns:imsld=\"http://www.imsglobal.org/xsd/imsld_v1p0\">");
        docString.append("<organizations>");
        docString.append("<learning-design xmlns=\"http://www.imsglobal.org/xsd/imsld_v1p0\">");
        docString.append("</learning-design>");
        docString.append("</organizations>");
        docString.append("</manifest>");
        
        Namespace nsCP = Namespace.getNamespace("http://www.imsglobal.org/xsd/imscp_v1p1");
        Namespace nsLD = Namespace.getNamespace("http://www.imsglobal.org/xsd/imsld_v1p0");
        
        Document doc = XMLUtils.readXMLString(docString.toString());
        Element element = doc.getRootElement();
        element = element.getChild("organizations", nsCP);
        element = element.getChild("learning-design", nsLD);
        
        XMLPath xmlPath = XMLPath.getXMLPathForElement(element);
        assertEquals("XMLPath incorrect", "manifest/organizations/imsld:learning-design", xmlPath.getPath());
    }

    // ---------------------------------------------------------------------------------------------
    
    public void testGetXMLPathForAttribute1() {
        Attribute att = new Attribute("att", "att_value");
        XMLPath xmlPath = XMLPath.getXMLPathForAttribute(att);
        assertEquals("XMLPath incorrect", "att", xmlPath.getPath());
    }

    public void testGetXMLPathForAttribute2() {
        Attribute att = new Attribute("att", "att_value", Namespace.XML_NAMESPACE);
        XMLPath xmlPath = XMLPath.getXMLPathForAttribute(att);
        assertEquals("XMLPath incorrect", "xml:att", xmlPath.getPath());
    }

    public void testGetXMLPathForAttribute3() {
        Element element1 = new Element("root");
        Element element2 = new Element("sub1");
        element1.addContent(element2);
        Attribute att = new Attribute("att", "att_value");
        element2.setAttribute(att);
        
        XMLPath xmlPath = XMLPath.getXMLPathForAttribute(att);
        assertEquals("XMLPath incorrect", "root/sub1@att", xmlPath.getPath());
    }

    public void testGetXMLPathForAttribute4() {
        Element element1 = new Element("root");
        Element element2 = new Element("sub1");
        element1.addContent(element2);
        Attribute att = new Attribute("att", "att_value", Namespace.XML_NAMESPACE);
        element2.setAttribute(att);
        
        XMLPath xmlPath = XMLPath.getXMLPathForAttribute(att);
        assertEquals("XMLPath incorrect", "root/sub1@xml:att", xmlPath.getPath());
    }
}
