
package uk.ac.reload.diva.util;

import java.io.File;

import junit.framework.TestCase;
import uk.ac.reload.testsupport.cp.CP_Package1;


/**
 * HTMLUtilsTest
 * 
 * @author Phillip Beauvoir
 * @version $Id: HTMLUtilsTest.java,v 1.1 1998/03/25 20:20:17 ynsingh Exp $
 */
public class HTMLUtilsTest extends TestCase {


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

    public void testGetTagText1() throws Exception {
        File file = new File(pathTestData, CP_Package1.fileHTMLPageIntro);
        String expected = "Introduction";
        String text = HTMLUtils.getTagText(file, "title");
        assertEquals("HTML Tag wrong", expected, text);
    }

    public void testGetTagText2() throws Exception {
        File file = new File(pathTestData, CP_Package1.fileHTMLPageSpanish);
        String expected = "Aproximándonos a OpenOffice";
        String text = HTMLUtils.getTagText(file, "title");
        assertEquals("HTML Tag wrong", expected, text);
    }

    // ---------------------------------------------------------------------------------------------

    public void testGetCharset1() throws Exception {
        File file = new File(pathTestData, CP_Package1.fileHTMLPageIntro);
        String expected = "iso-8859-1";
        String text = HTMLUtils.getCharset(file);
        assertEquals("Charset wrong", expected, text);
    }

    public void testGetCharset2() throws Exception {
        File file = new File(pathTestData, CP_Package1.fileHTMLPageSpanish);
        String expected = "UTF-8";
        String text = HTMLUtils.getCharset(file);
        assertEquals("Charset wrong", expected, text);
    }

    // ---------------------------------------------------------------------------------------------
}
