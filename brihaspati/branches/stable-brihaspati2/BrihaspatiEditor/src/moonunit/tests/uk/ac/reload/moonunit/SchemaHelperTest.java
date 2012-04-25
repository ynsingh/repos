
package uk.ac.reload.moonunit;

import java.io.File;
import java.io.IOException;

import junit.framework.TestCase;

import org.jdom.Document;
import org.jdom.JDOMException;

import uk.ac.reload.jdom.XMLPath;
import uk.ac.reload.testsupport.Helpers;


/**
 * Description
 * 
 * @author Phillip Beauvoir
 * @version $Id: SchemaHelperTest.java,v 1.1 1998/03/25 20:27:36 ynsingh Exp $
 */
public class SchemaHelperTest extends TestCase {

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

    protected SchemaHelper getTestSchemaHelper() throws JDOMException, IOException {
        return new SchemaHelper(new File(pathTestData, Helpers.fileTestHelper1));
    }
    
    // ---------------------------------------------------------------------------------------------
    
    /**
     * Get SchemaHelper Not Null
     */
    public void testGetSchemaHelper_NotNull() throws Exception {
        SchemaHelper helper = SchemaHelper.getSchemaHelper(new File(pathTestData, Helpers.fileTestHelper1));
        assertNotNull("Schema Helper was null", helper);
    }

    // ---------------------------------------------------------------------------------------------
    
    /**
     * Get SchemaHelper throws exception OK
     */
    public void testGetSchemaHelper_ThrowsException() {
        try {
            SchemaHelper.getSchemaHelper(new File("bogus"));
            fail("Should have thrown an exception");
        }
        catch(Exception ex) {
            assertTrue(true);
        }
    }

    // ---------------------------------------------------------------------------------------------
    
    /**
     * Get Document from Helper
     */
    public void testGetSchemaHelperDocument() throws Exception {
        SchemaHelper helper = getTestSchemaHelper();
        Document doc = helper.getSchemaHelperDocument();
        assertNotNull("Helper Document was null", doc);
    }

    // ---------------------------------------------------------------------------------------------
    
    /**
     * Helper Name
     */
    public void testGetSchemaHelperName() throws Exception {
        SchemaHelper helper = getTestSchemaHelper();
        String name = helper.getSchemaHelperName();
        assertEquals("Helper name wrong", new File(pathTestData, Helpers.fileTestHelper1).getName(), name);
    }

    // ---------------------------------------------------------------------------------------------
    
    static final String[] testValues = new String[] {
            "anything/ponk@type", SchemaHelper.FNAME, "Path 1",
            "path1/path2/path3@xml:lang", SchemaHelper.FNAME, "Path 2",
            "@xml:lang", SchemaHelper.FNAME, "Path 3",
            "path1/path2/path3", SchemaHelper.FNAME, "Path 4",
            "path1", SchemaHelper.FNAME, "Path 5",
            "path1", SchemaHelper.TIP, "Comments on something",
            "path1", SchemaHelper.WIDGET, "textpane",
            "path1", SchemaHelper.MAXLENGTH, "30",
            "path1", SchemaHelper.TIP, "Comments on something",
            "keyword", SchemaHelper.TIP, "Contains keyword description of the resource.<br>" 
    };
    
    /**
     * Get Helper Values
     */
    public void testGetHelperValue() throws Exception {
        SchemaHelper helper = getTestSchemaHelper();
        
        for(int i = 0; i < testValues.length; i+=3) {
            XMLPath xmlPath = new XMLPath(testValues[i]);
            String key = testValues[i+1];
            String expected = testValues[i+2];
            String result = helper.getHelperValue(xmlPath, key);
            assertEquals("Helper Value wrong", expected, result);
        }
    }

    // ---------------------------------------------------------------------------------------------
    
    /**
     * File
     */
    public void testGetFile() throws Exception {
        SchemaHelper helper = getTestSchemaHelper();
        assertEquals("Helper file wrong", new File(pathTestData, Helpers.fileTestHelper1), helper.getFile());
    }

    // ---------------------------------------------------------------------------------------------
    
}
