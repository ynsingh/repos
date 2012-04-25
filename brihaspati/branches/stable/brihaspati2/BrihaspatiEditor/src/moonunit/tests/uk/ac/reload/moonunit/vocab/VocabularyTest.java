
package uk.ac.reload.moonunit.vocab;

import java.io.File;
import java.util.Arrays;

import junit.framework.TestCase;
import uk.ac.reload.jdom.XMLPath;
import uk.ac.reload.moonunit.schema.SchemaAttribute;
import uk.ac.reload.moonunit.schema.SchemaElement;
import uk.ac.reload.moonunit.schema.SchemaModel;
import uk.ac.reload.testsupport.Helpers;
import uk.ac.reload.testsupport.ITestsGlobals;
import uk.ac.reload.testsupport.Schemas;


/**
 * VocabularyTest
 * 
 * @author Phillip Beauvoir
 * @version $Id: VocabularyTest.java,v 1.1 1998/03/25 20:28:22 ynsingh Exp $
 */
public class VocabularyTest
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

    // ---------------------------------------------------------------------------------------------

    public void testGetVocabulary() throws Exception {
        File fileVocab = new File(pathTestData, Helpers.fileVocab1);
        Vocabulary vocab = Vocabulary.getVocabulary(fileVocab);
        assertNotNull("Vocabulary should not be null", vocab);
        
        // Get again, should be cached
        vocab = Vocabulary.getVocabulary(fileVocab);
        assertNotNull("Vocabulary should not be null", vocab);
    }

    // ---------------------------------------------------------------------------------------------

    public void testVocabulary() throws Exception {
        File fileVocab = new File(pathTestData, Helpers.fileVocab1);
        Vocabulary vocab = new Vocabulary(fileVocab);
        assertNotNull("Vocabulary should not be null", vocab);
    }

    // ---------------------------------------------------------------------------------------------

    public void testGetVocabularyDocument() throws Exception {
        File fileVocab = new File(pathTestData, Helpers.fileVocab1);
        Vocabulary vocab = new Vocabulary(fileVocab);
        assertNotNull("Document should not be null", vocab.getVocabularyDocument());
    }

    // ---------------------------------------------------------------------------------------------

    public void testGetVocabularyName() throws Exception {
        File fileVocab = new File(pathTestData, Helpers.fileVocab1);
        Vocabulary vocab = new Vocabulary(fileVocab);
        assertEquals("Vocabulary name wrong", "Test Vocab.xml", vocab.getVocabularyName());
    }

    // ---------------------------------------------------------------------------------------------

    public void testGetVocabularyList_SchemaNode() throws Exception {
        File schemaFile = new File(pathTestData, Schemas.FILE_SCHEMA_MD121);
        SchemaModel schemaModel = new SchemaModel(schemaFile, "lom");
        SchemaElement schemaElement = schemaModel.getRootElement()
        							  .getChild("general")
        							  .getChild("structure")
        							  .getChild("value")
        							  .getChild("langstring");
        
        File fileVocab = new File(pathTestData, Helpers.fileVocabMD1);
        Vocabulary vocab = new Vocabulary(fileVocab);
        VocabularyList vList = vocab.getVocabularyList(schemaElement);
        
        assertEquals("Vocabulary list wrong size", 8, vList.getList().length);

        String[] expected = new String[] {
                "Atomic",
                "Branched",
                "Collection",
                "Hierarchical",
                "Linear",
                "Mixed",
                "Networked",
                "Parceled"
        };
        
        for(int i = 0; i < expected.length; i++) {
            assertEquals("Vocabulary list wrong item", expected[i], vList.getList()[i]);
        }
        
        SchemaAttribute schemaAttribute = schemaElement.getSchemaAttribute("xml:lang");
        vList = vocab.getVocabularyList(schemaAttribute);
        assertEquals("Vocabulary list wrong size", 1, vList.getList().length);
        assertEquals("Vocabulary list wrong item", "x-none", vList.getList()[0]);
        assertEquals("Vocabulary list default value", "x-none", vList.getDefaultValue());
    }

    // ---------------------------------------------------------------------------------------------

    public void testGetVocabularyList_XMLPath1() throws Exception {
        File fileVocab = new File(pathTestData, Helpers.fileVocabMD1);
        Vocabulary vocab = new Vocabulary(fileVocab);
        
        XMLPath xmlPath = new XMLPath("lom/general/structure/value/langstring");
        VocabularyList vList = vocab.getVocabularyList(xmlPath);
        
        assertEquals("Vocabulary list wrong size", 8, vList.getList().length);

        String[] expected = new String[] {
                "Atomic",
                "Branched",
                "Collection",
                "Hierarchical",
                "Linear",
                "Mixed",
                "Networked",
                "Parceled"
        };
        
        for(int i = 0; i < expected.length; i++) {
            assertEquals("Vocabulary list wrong item", expected[i], vList.getList()[i]);
        }
    }

    public void testGetVocabularyList_XMLPath2() throws Exception {
        File fileVocab = new File(pathTestData, Helpers.fileVocabMD1);
        Vocabulary vocab = new Vocabulary(fileVocab);
        
        XMLPath xmlPath = new XMLPath("value/langstring@xml:lang");
        VocabularyList vList = vocab.getVocabularyList(xmlPath);
        
        assertEquals("Vocabulary list wrong size", 1, vList.getList().length);
        assertEquals("Vocabulary list wrong item", "x-none", vList.getList()[0]);
        assertEquals("Vocabulary list default value", "x-none", vList.getDefaultValue());
    }

    public void testGetVocabularyList_XMLPath3() throws Exception {
        File fileVocab = new File(pathTestData, Helpers.fileVocabMD1);
        Vocabulary vocab = new Vocabulary(fileVocab);
        
        XMLPath xmlPath = new XMLPath("langstring@xml:lang");
        VocabularyList vList = vocab.getVocabularyList(xmlPath);
        
        assertTrue("Vocabulary list should be language list", vList.isLangList());
        assertEquals("Vocabulary list default value", "en", vList.getDefaultValue());
    }

    // ---------------------------------------------------------------------------------------------

    public void testGetVocabularyList_String1() throws Exception {
        File fileVocab = new File(pathTestData, Helpers.fileVocab1);
        Vocabulary vocab = new Vocabulary(fileVocab);
        assertTrue("Vocabulary list wrong", vocab.getVocabularyList("lang").isLangList());
    }
    
    public void testGetVocabularyList_String2() throws Exception {
        File fileVocab = new File(pathTestData, Helpers.fileVocab1);
        Vocabulary vocab = new Vocabulary(fileVocab);
        VocabularyList vList = vocab.getVocabularyList("list1");
        assertEquals("Vocabulary list wrong", "list1", vList.getListName());
        assertEquals("Vocabulary list default value", "Item 1", vList.getDefaultValue());
    }

    // ---------------------------------------------------------------------------------------------

    public void testGetVocabFile() throws Exception {
        File fileVocab = new File(pathTestData, Helpers.fileVocab1);
        Vocabulary vocab = new Vocabulary(fileVocab);
        assertEquals("Vocabulary file wrong", fileVocab, vocab.getVocabFile());
    }

    // ---------------------------------------------------------------------------------------------

    public void testGetVocabularyLists() throws Exception {
        File fileVocab = new File(pathTestData, Helpers.fileVocab1);
        Vocabulary vocab = new Vocabulary(fileVocab);
        
        String[] expected = new String[] {
                "lang",
                "list1",
                "list2",
                "list3",
                "truefalse",
                "yesno"
        };
        
        int size = vocab.getVocabularyLists().size();
        assertEquals("Vocabulary lists wrong size", 6, size);
        
        VocabularyList[] vLists = new VocabularyList[size];
        vocab.getVocabularyLists().toArray(vLists);
        Arrays.sort(vLists);
        for(int i = 0; i < vLists.length; i++) {
            assertEquals("Vocabulary list wrong item", expected[i], vLists[i].getListName());
        }
    }

    // ---------------------------------------------------------------------------------------------

    public void testGetDefaultLanguage1() throws Exception {
        File fileVocab = new File(pathTestData, Helpers.fileVocab1);
        Vocabulary vocab = new Vocabulary(fileVocab);
        assertEquals("Vocabulary default language wrong", "en", vocab.getDefaultLanguage());
    }

    public void testGetDefaultLanguage2() throws Exception {
        File fileVocab = new File(pathTestData, Helpers.fileVocab2);
        Vocabulary vocab = new Vocabulary(fileVocab);
        assertEquals("Vocabulary default language wrong", "nl", vocab.getDefaultLanguage());
    }

    // ---------------------------------------------------------------------------------------------

    public void testSetDefaultLanguage() throws Exception {
        File fileVocab = new File(pathTestData, Helpers.fileVocab1);
        Vocabulary vocab = new Vocabulary(fileVocab);
        assertEquals("Vocabulary default language wrong", "en", vocab.getDefaultLanguage());
        
        vocab.setDefaultLanguage("nl");
        assertEquals("Vocabulary default language wrong", "nl", vocab.getDefaultLanguage());
        VocabularyList vList = vocab.getVocabularyList("lang");
        assertEquals("Vocabulary default language wrong", "nl", vList.getDefaultValue());
    }

    // ---------------------------------------------------------------------------------------------

    public void testGetRootAttributeValue() throws Exception {
        File fileVocab = new File(pathTestData, Helpers.fileVocab1);
        Vocabulary vocab = new Vocabulary(fileVocab);
        assertEquals("Root Attribute Value wrong", "en", vocab.getRootAttributeValue("lang"));
    }

    // ---------------------------------------------------------------------------------------------

    public void testToString() throws Exception {
        File fileVocab = new File(pathTestData, Helpers.fileVocab1);
        Vocabulary vocab = new Vocabulary(fileVocab);
        assertEquals("Vocabulary name wrong", "Test Vocab.xml", vocab.toString());
    }

}
