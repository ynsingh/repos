
package uk.ac.reload.jdom;

import java.io.File;
import java.io.IOException;
import java.util.List;

import junit.framework.TestCase;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.Namespace;

import uk.ac.reload.testsupport.FileSupport;


/**
 * XMLActiveDocumentTest
 * 
 * @author Phillip Beauvoir
 * @version $Id: XMLActiveDocumentTest.java,v 1.1 1998/03/26 16:00:44 ynsingh Exp $
 */
public class XMLActiveDocumentTest extends TestCase {

    /**
     * Flag for listener notification
     */
    boolean _notified;

    protected void setUp() {
    }
    
    protected void tearDown() throws Exception {
        // Clean up
        FileSupport.deleteFolder(FileSupport.getMainTestFolder());
    }

    /**
     * @param withFile With temp file or not
     * @return An XMLActiveDocument
     */
    private XMLActiveDocument getTestDocument(boolean withFile) {
        Document doc = new Document(new Element("root"));
        XMLActiveDocument xmlDocument = new XMLActiveDocument(doc);
        if(withFile) {
            File file = new File(FileSupport.getTempFolder("xml"), "test.xml");
            xmlDocument.setFile(file);
        }
        return xmlDocument;
    }
    
    
    // ---------------------------------------------------------------------------------------------
    
    /**
     * Should be no exceptions with null doc and file
     */
    public void testSaveDocument1() {
        XMLActiveDocument xmlDocument = new XMLActiveDocument();
        try {
            xmlDocument.saveDocument();
        }
        catch(IOException ex) {
            fail("Should not have thrown Exception");
        }
        assertTrue(true);
    }

    public void testSaveDocument2() throws Exception {
        XMLActiveDocument xmlDocument = getTestDocument(true);
        
        xmlDocument.setDirty(true);
        xmlDocument.saveDocument();
        
        assertTrue("File should exist", xmlDocument.getFile().exists());
        assertFalse("Should not have been dirty", xmlDocument.isDirty());
    }

    // ---------------------------------------------------------------------------------------------

    public void testAddElementAtIndex1() {
        XMLActiveDocument xmlDocument = getTestDocument(false);
        Document doc = xmlDocument.getDocument();
        
        int pos = 2;
        Element element = new Element("inserted");
        
        doc.getRootElement().addContent(new Element("one"));
        doc.getRootElement().addContent(new Element("two"));
        doc.getRootElement().addContent(new Element("three"));
        List list = xmlDocument.getRootElement().getContent();

        assertEquals("Wrong Element size", 3, list.size());
        
        xmlDocument.addElementAtIndex(this, doc.getRootElement(), element, pos, true);
        
        assertEquals("Wrong Element size", 4, list.size());
        assertEquals("Wrong Element", element, list.get(pos));
        assertTrue("Should have been dirty", xmlDocument.isDirty());
    }

    /**
     * Check added at index position 0 if pos > child size
     */
    public void testAddElementAtIndex2() {
        XMLActiveDocument xmlDocument = getTestDocument(false);
        Document doc = xmlDocument.getDocument();
        
        int pos = 4;
        Element element = new Element("inserted");
        
        doc.getRootElement().addContent(new Element("one"));
        doc.getRootElement().addContent(new Element("two"));
        doc.getRootElement().addContent(new Element("three"));
        List list = xmlDocument.getRootElement().getContent();

        assertEquals("Wrong Element size", 3, list.size());
        
        xmlDocument.addElementAtIndex(this, doc.getRootElement(), element, pos, true);

        assertEquals("Wrong Element size", 4, list.size());
        assertEquals("Wrong Element", element, list.get(0));
        assertTrue("Should have been dirty", xmlDocument.isDirty());
    }

    // ---------------------------------------------------------------------------------------------
    
    public void testRemoveElement() {
        XMLActiveDocument xmlDocument = getTestDocument(false);
        Document doc = xmlDocument.getDocument();
        
        Element element = new Element("deleted");

        doc.getRootElement().addContent(new Element("one"));
        doc.getRootElement().addContent(new Element("two"));
        doc.getRootElement().addContent(element);
        doc.getRootElement().addContent(new Element("three"));
        List list = xmlDocument.getRootElement().getContent();

        assertEquals("Wrong Element size", 4, list.size());

        xmlDocument.removeElement(this, element);
        
        assertEquals("Wrong List size", 3, list.size());
        assertFalse("Should have been removed", list.contains(element));
        assertTrue("Should have been dirty", xmlDocument.isDirty());
    }

    // ---------------------------------------------------------------------------------------------
    
    public void testMoveElementUp1() {
        XMLActiveDocument xmlDocument = getTestDocument(false);
        Document doc = xmlDocument.getDocument();
        
        Element element = new Element("three");

        doc.getRootElement().addContent(new Element("one"));
        doc.getRootElement().addContent(new Element("two"));
        doc.getRootElement().addContent(element);
        doc.getRootElement().addContent(new Element("four"));
        List list = xmlDocument.getRootElement().getContent();

        assertEquals("Wrong Element size", 4, list.size());
        assertEquals("Wrong Element position", 2, list.indexOf(element));

        xmlDocument.moveElementUp(this, element, true);
        
        assertEquals("Wrong Element size", 4, list.size());
        assertEquals("Wrong Element position", 1, list.indexOf(element));
    }

    public void testMoveElementUp2() {
        XMLActiveDocument xmlDocument = getTestDocument(false);
        Document doc = xmlDocument.getDocument();
        
        Element element = new Element("one");
        doc.getRootElement().addContent(element);
        List list = xmlDocument.getRootElement().getContent();

        assertEquals("Wrong Element size", 1, list.size());
        assertEquals("Wrong Element position", 0, list.indexOf(element));

        xmlDocument.moveElementUp(this, element, true);
        
        assertEquals("Wrong Element size", 1, list.size());
        assertEquals("Wrong Element position", 0, list.indexOf(element));
    }

    // ---------------------------------------------------------------------------------------------
    
    public void testMoveElementUpSameType() {
        XMLActiveDocument xmlDocument = getTestDocument(false);
        Document doc = xmlDocument.getDocument();
        Element root = xmlDocument.getRootElement();
        List list = root.getContent();
        
        Namespace ns = Namespace.getNamespace("www.reload.ac.uk");
        
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
        
        xmlDocument.moveElementUpSameType(this, e5, false);
        assertEquals("Wrong Element position", 1, list.indexOf(e5));
        
        xmlDocument.moveElementUpSameType(this, e6, false);
        assertEquals("Wrong Element position", 0, list.indexOf(e6));
        
        xmlDocument.moveElementUpSameType(this, e4, false);
        assertEquals("Wrong Element position", 4, list.indexOf(e4));

        xmlDocument.moveElementUpSameType(this, e6, false);
        assertEquals("Wrong Element position", 0, list.indexOf(e6));
    }
    
    // ---------------------------------------------------------------------------------------------

    public void testMoveElementDown1() {
        XMLActiveDocument xmlDocument = getTestDocument(false);
        Document doc = xmlDocument.getDocument();

        Element element = new Element("three");
        doc.getRootElement().addContent(new Element("one"));
        doc.getRootElement().addContent(new Element("two"));
        doc.getRootElement().addContent(element);
        doc.getRootElement().addContent(new Element("four"));
        List list = xmlDocument.getRootElement().getContent();

        assertEquals("Wrong Element size", 4, list.size());
        assertEquals("Wrong Element position", 2, list.indexOf(element));

        xmlDocument.moveElementDown(this, element, true);
        
        assertEquals("Wrong Element size", 4, list.size());
        assertEquals("Wrong Element position", 3, list.indexOf(element));
    }

    public void testMoveElementDown2() {
        XMLActiveDocument xmlDocument = getTestDocument(false);
        Document doc = xmlDocument.getDocument();

        Element element = new Element("one");
        doc.getRootElement().addContent(element);
        List list = xmlDocument.getRootElement().getContent();

        assertEquals("Wrong Element size", 1, list.size());
        assertEquals("Wrong Element position", 0, list.indexOf(element));

        xmlDocument.moveElementDown(this, element, true);
        
        assertEquals("Wrong Element size", 1, list.size());
        assertEquals("Wrong Element position", 0, list.indexOf(element));
    }

    // ---------------------------------------------------------------------------------------------
    
    public void testMoveElementDownSameType() {
        XMLActiveDocument xmlDocument = getTestDocument(false);
        Document doc = xmlDocument.getDocument();
        Element root = xmlDocument.getRootElement();
        List list = root.getContent();
        
        Namespace ns = Namespace.getNamespace("www.reload.ac.uk");
        
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
        
        xmlDocument.moveElementDownSameType(this, e2, false);
        assertEquals("Wrong Element position", 4, list.indexOf(e2));
        
        xmlDocument.moveElementDownSameType(this, e1, false);
        assertEquals("Wrong Element position", 5, list.indexOf(e1));
        
        xmlDocument.moveElementDownSameType(this, e4, false);
        assertEquals("Wrong Element position", 2, list.indexOf(e4));

        xmlDocument.moveElementDownSameType(this, e1, false);
        assertEquals("Wrong Element position", 5, list.indexOf(e1));
    }

    // ---------------------------------------------------------------------------------------------
    
    public void testChangedElement() {
        XMLActiveDocument xmlDocument = getTestDocument(false);
        Document doc = xmlDocument.getDocument();

        xmlDocument.changedElement(this, doc.getRootElement());
        assertTrue("Should have been dirty", xmlDocument.isDirty());
    }

    // ---------------------------------------------------------------------------------------------
    
    public void testDestroy() {
        XMLActiveDocument xmlDocument = getTestDocument(false);

        xmlDocument.destroy();
        assertNull("Document should be null", xmlDocument.getDocument());
    }

    // ---------------------------------------------------------------------------------------------
    
    public void testAddXMLDocumentListener() {
        XMLActiveDocument xmlDocument = getTestDocument(false);
        Document doc = xmlDocument.getDocument();

        XMLDocumentListener listener = new XMLDocumentListenerAdapter() {
            public void elementChanged(XMLDocumentListenerEvent event) {
                _notified = true;
            }
        };
        
        xmlDocument.addXMLDocumentListener(listener);
        xmlDocument.changedElement(this, doc.getRootElement());
        assertTrue("Should have been notified", _notified);
    }

    // ---------------------------------------------------------------------------------------------
    
    public void testRemoveXMLDocumentListener() {
        XMLActiveDocument xmlDocument = getTestDocument(false);
        Document doc = xmlDocument.getDocument();

        XMLDocumentListener listener = new XMLDocumentListenerAdapter() {
            public void elementChanged(XMLDocumentListenerEvent event) {
                _notified = true;
            }
        };
        
        xmlDocument.addXMLDocumentListener(listener);
        xmlDocument.removeXMLDocumentListener(listener);
        xmlDocument.changedElement(this, doc.getRootElement());
        assertFalse("Should not have been notified", _notified);
    }

    // ---------------------------------------------------------------------------------------------
    
    public void testFireElementAdded() {
        final XMLActiveDocument xmlDocument = getTestDocument(false);
        Document doc = xmlDocument.getDocument();

        final Element element = new Element("new");
        
        XMLDocumentListener listener = new XMLDocumentListenerAdapter() {
            public void elementAdded(XMLDocumentListenerEvent event) {
                _notified = true;
                assertEquals("Wrong Source", XMLActiveDocumentTest.this, event.getSource());
                assertEquals("Wrong XMLDocument", xmlDocument, event.getXMLDocument());
                assertTrue("doSelect wrong", event.doSelect());
                assertEquals("Wrong Element", element, event.getElement());
            }
        };
        
        xmlDocument.addXMLDocumentListener(listener);
        xmlDocument.addElementAtIndex(this, doc.getRootElement(), element, 0, true);
        assertTrue("Should have been notified", _notified);
    }

    // ---------------------------------------------------------------------------------------------
    
    public void testFireElementRemoved() {
        final XMLActiveDocument xmlDocument = getTestDocument(false);
        Document doc = xmlDocument.getDocument();

        final Element element = new Element("deleted");
        doc.getRootElement().addContent(element);

        XMLDocumentListener listener = new XMLDocumentListenerAdapter() {
            public void elementRemoved(XMLDocumentListenerEvent event) {
                _notified = true;
                assertEquals("Wrong Source", XMLActiveDocumentTest.this, event.getSource());
                assertEquals("Wrong XMLDocument", xmlDocument, event.getXMLDocument());
                assertFalse("doSelect wrong", event.doSelect());
                assertEquals("Wrong Element", element, event.getElement());
            }
        };
        
        xmlDocument.addXMLDocumentListener(listener);
        xmlDocument.removeElement(this, element);
        assertTrue("Should have been notified", _notified);
    }

    // ---------------------------------------------------------------------------------------------
    
    public void testFireElementChanged() {
        final XMLActiveDocument xmlDocument = getTestDocument(false);
        final Document doc = xmlDocument.getDocument();

        XMLDocumentListener listener = new XMLDocumentListenerAdapter() {
            public void elementChanged(XMLDocumentListenerEvent event) {
                _notified = true;
                assertEquals("Wrong Source", XMLActiveDocumentTest.this, event.getSource());
                assertEquals("Wrong XMLDocument", xmlDocument, event.getXMLDocument());
                assertFalse("doSelect wrong", event.doSelect());
                assertEquals("Wrong Element", doc.getRootElement(), event.getElement());
            }
        };
        
        xmlDocument.addXMLDocumentListener(listener);
        xmlDocument.changedElement(this, doc.getRootElement());
        assertTrue("Should have been notified", _notified);
    }

    // ---------------------------------------------------------------------------------------------
    
    public void testFireDocumentSaved() throws Exception {
        XMLActiveDocument xmlDocument = getTestDocument(false);
        Document doc = xmlDocument.getDocument();

        XMLDocumentListener listener = new XMLDocumentListenerAdapter() {
            public void documentSaved(XMLDocument doc) {
                _notified = true;
            }
        };

        xmlDocument.addXMLDocumentListener(listener);
        xmlDocument.saveDocument();
        assertTrue("Should have been notified", _notified);
    }

}
