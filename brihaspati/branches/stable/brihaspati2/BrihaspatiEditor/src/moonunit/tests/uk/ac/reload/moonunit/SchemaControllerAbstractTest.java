
package uk.ac.reload.moonunit;

import java.io.File;

import junit.framework.TestCase;
import uk.ac.reload.jdom.XMLPath;
import uk.ac.reload.moonunit.schema.SchemaElement;
import uk.ac.reload.moonunit.schema.SchemaNode;
import uk.ac.reload.moonunit.vocab.Vocabulary;
import uk.ac.reload.moonunit.vocab.VocabularyList;


/**
 * SchemaControllerAbstractTest
 * 
 * @author Phillip Beauvoir
 * @version $Id: SchemaControllerAbstractTest.java,v 1.1 1998/03/25 20:27:36 ynsingh Exp $
 */
public abstract class SchemaControllerAbstractTest extends TestCase {

    protected SchemaController sc;
    
    // ---------------------------------------------------------------------------------------------
    
    /**
     * Test SchemaModel loaded
     */
    public void testLoadSchemaModel() throws Exception {
        sc.loadSchemaModel();
        assertNotNull("SchemaModel was not initialised", sc.getSchemaModel());
    }

    // ---------------------------------------------------------------------------------------------
    
    public void itestSetDefaultVocabLanguage(String lang) {
        sc.setDefaultVocabLanguage(lang);
        Vocabulary vocab = sc.getVocabulary();
        if(vocab != null) {
            assertEquals("Default Vocab Language wrong", lang, vocab.getDefaultLanguage());
        }
    }

    // ---------------------------------------------------------------------------------------------
    
    public void itestIsTopLevelElement(SchemaElement schemaElement, boolean expected) {
        assertNotNull("SchemaElement null", schemaElement);
        assertEquals("Wrong top level element", expected, sc.isTopLevelElement(schemaElement));
    }

    // ---------------------------------------------------------------------------------------------
    
    public SchemaNode itestGetSchemaNode(XMLPath xmlPath) {
        assertNotNull("XMLPath null", xmlPath);
        SchemaNode node = sc.getSchemaNode(xmlPath);
        assertNotNull("SchemaNode was null", node);
        return node;
    }

    // ---------------------------------------------------------------------------------------------
    
    public String itestGetElementHelperValue(XMLPath xmlPath, String key, String expected) {
        assertNotNull("XMLPath null", xmlPath);
        String value = sc.getElementHelperValue(xmlPath, key);
        assertEquals("Wrong Helper value", expected, value);
        return value;
    }

    // ---------------------------------------------------------------------------------------------
    
    public String itestGetElementFriendlyName(XMLPath xmlPath, String expected) {
        assertNotNull("XMLPath null", xmlPath);
        String value = sc.getElementFriendlyName(xmlPath);
        assertEquals("Wrong Friendly value", expected, value);
        return value;
    }
    
    // ---------------------------------------------------------------------------------------------
    
    public String itestGetElementTip(XMLPath xmlPath, String expected) {
        assertNotNull("XMLPath null", xmlPath);
        String value = sc.getElementTip(xmlPath);
        assertEquals("Wrong Friendly value", expected, value);
        return value;
    }
    
    // ---------------------------------------------------------------------------------------------
    
    public String itestGetWidgetType(XMLPath xmlPath, String expected) {
        assertNotNull("XMLPath null", xmlPath);
        String value = sc.getWidgetType(xmlPath);
        assertEquals("Wrong Friendly value", expected, value);
        return value;
    }
    
    // ---------------------------------------------------------------------------------------------
    
    public VocabularyList itestGetVocabularyList(SchemaNode schemaNode) {
        assertNotNull("SchemaNode null", schemaNode);
        VocabularyList vList = sc.getVocabularyList(schemaNode);
        return vList;
    }
    
    // ---------------------------------------------------------------------------------------------
    
    public String itestGetDefaultValue(SchemaNode schemaNode, String expected) {
        assertNotNull("SchemaNode null", schemaNode);
        String value = sc.getDefaultValue(schemaNode);
        assertEquals("Wrong Default value", expected, value);
        return value;
    }
    
    // ---------------------------------------------------------------------------------------------
    
    public String itestGetFacetValue(SchemaNode schemaNode, String facetName, String expected) {
        assertNotNull("SchemaNode null", schemaNode);
        String value = sc.getFacetValue(schemaNode, facetName);
        assertEquals("Wrong Facet value", expected, value);
        return value;
    }
    
    // ---------------------------------------------------------------------------------------------
    
    public String itestGetVersion(String expected) {
        String version = sc.getVersion();
        assertEquals("Wrong Version ", expected, version);
        return version;
    }
    
    // ---------------------------------------------------------------------------------------------
    
    public String itestGetRootElementName(String expected) {
        String root = sc.getRootElementName();
        assertEquals("Wrong root element name", expected, root);
        return root;
    }
    
    // ---------------------------------------------------------------------------------------------
    
    public File itestGetSchemaFile() {
        File file = sc.getSchemaFile();
        assertTrue("Schema File does not exist", file.exists());
        return file;
    }

    // ---------------------------------------------------------------------------------------------
    
}
