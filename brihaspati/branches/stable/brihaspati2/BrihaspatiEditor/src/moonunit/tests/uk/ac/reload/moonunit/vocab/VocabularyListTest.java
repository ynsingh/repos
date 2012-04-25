
package uk.ac.reload.moonunit.vocab;

import java.util.Arrays;

import junit.framework.TestCase;


/**
 * VocabularyListTest
 * 
 * @author Phillip Beauvoir
 * @version $Id: VocabularyListTest.java,v 1.1 1998/03/25 20:28:22 ynsingh Exp $
 */
public class VocabularyListTest extends TestCase {

    // ---------------------------------------------------------------------------------------------

    public void testGetYesNoList() {
        VocabularyList vList = VocabularyList.getYesNoList();
        assertEquals("YesNo list wrong name", "yesno", vList.getListName());
        assertEquals("YesNo list wrong size", 3, vList.getList().length);
        
        String[] expected = new String[] {
                "",
                "yes",
                "no"
        };
        
        for(int i = 0; i < expected.length; i++) {
            assertEquals("YesNo list wrong item", expected[i], vList.getList()[i]);
        }
    }

    // ---------------------------------------------------------------------------------------------

    public void testGetTrueFalseList() {
        VocabularyList vList = VocabularyList.getTrueFalseList();
        assertEquals("TrueFalse list wrong name", "truefalse", vList.getListName());
        assertEquals("TrueFalse list wrong size", 3, vList.getList().length);
        
        String[] expected = new String[] {
                "",
                "true",
                "false"
        };
        
        for(int i = 0; i < expected.length; i++) {
            assertEquals("TrueFalse list wrong item", expected[i], vList.getList()[i]);
        }
    }

    // ---------------------------------------------------------------------------------------------

    public void testGetLangList() {
        VocabularyList vList = VocabularyList.getLangList();
        assertEquals("LangList list wrong name", "lang", vList.getListName());
        assertEquals("TrueFalse list wrong size", 142, vList.getList().length);
    }

    // ---------------------------------------------------------------------------------------------

    public void testVocabularyList() {
        VocabularyList vList = new VocabularyList();
        assertEquals("VocabularyList wrong name", "blank", vList.getListName());
        assertEquals("VocabularyList wrong size", 0, vList.getList().length);
    }

    // ---------------------------------------------------------------------------------------------

    public void testGetList() {
        String[] expected = new String[] {
                "one",
                "two",
                "three"
        };

        VocabularyList vList = new VocabularyList("test", expected);
        assertEquals("VocabularyList list wrong size", 3, vList.getList().length);
        for(int i = 0; i < expected.length; i++) {
            assertEquals("VocabularyList list wrong item", expected[i], vList.getList()[i]);
        }
    }

    // ---------------------------------------------------------------------------------------------

    public void testGetListName() {
        VocabularyList vList = new VocabularyList("test", new String[0]);
        assertEquals("VocabularyList wrong name", "test", vList.getListName());
    }

    // ---------------------------------------------------------------------------------------------

    public void testIsLangList_True() {
        VocabularyList vList = VocabularyList.getLangList();
        assertTrue("Should be LangList", vList.isLangList());
    }

    public void testIsLangList_False() {
        VocabularyList vList = new VocabularyList("test", new String[0]);
        assertFalse("Should not be LangList", vList.isLangList());
    }

    // ---------------------------------------------------------------------------------------------

    public void testSetDefaultValue() {
        VocabularyList vList = new VocabularyList("test", new String[0]);
        vList.setDefaultValue("ponk");
        assertEquals("VocabularyList wrong default value", "ponk", vList.getDefaultValue());
    }

    // ---------------------------------------------------------------------------------------------

    public void testGetDefaultValue() {
        VocabularyList vList = new VocabularyList("test", new String[0], "ponk");
        vList.setDefaultValue("ponk");
        assertEquals("VocabularyList wrong default value", "ponk", vList.getDefaultValue());
    }

    // ---------------------------------------------------------------------------------------------

    public void testToString() {
        VocabularyList vList = new VocabularyList("test", new String[0]);
        vList.setDefaultValue("ponk");
        assertEquals("VocabularyList ToString", "test", vList.toString());
    }
    
    // ---------------------------------------------------------------------------------------------

    public void testCompareTo() {
        VocabularyList[] vList = new VocabularyList[] {
                new VocabularyList("c", new String[0]),
                new VocabularyList("b", new String[0]),
                new VocabularyList("A", new String[0]),
                new VocabularyList("a", new String[0])
        };
        
        Arrays.sort(vList);
        
        String[] expected = new String[] { "A", "a", "b", "c" };
        
        for(int i = 0; i < vList.length; i++) {
            assertEquals("VocabularyList sorted wrongly", expected[i], vList[i].getListName());
        }
    }
}
