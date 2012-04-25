
package uk.ac.reload.moonunit;

import java.io.File;

import junit.framework.TestCase;
import uk.ac.reload.testsupport.Helpers;


/**
 * Description
 * 
 * @author Phillip Beauvoir
 * @version $Id: HelperProfileTest.java,v 1.1 1998/03/25 20:27:36 ynsingh Exp $
 */
public class HelperProfileTest extends TestCase {

    protected HelperProfile hp;
    
    String pathTestData;
    
    protected void setUp() throws Exception {
        // See if we have a testdata dir set from an Ant script
        pathTestData = System.getProperty("testdata.dir");
        if(pathTestData == null) {
            // Set a default
            pathTestData = "../junit-tests";
        }

        File profile = new File(pathTestData, Helpers.fileProfile1);
        File vocabFolder = new File(pathTestData, Helpers.folderVocab);
        File schemaHelperFolder = new File(pathTestData, Helpers.folderSchemaHelper);
        
		hp = new HelperProfile(profile, vocabFolder, schemaHelperFolder);
    }
    
    // ---------------------------------------------------------------------------------------------

    /**
     * Get HelperProfile throws exception OK
     */
    public void testGetHelperProfile_ThrowsException() {
        try {
            HelperProfile.getHelperProfile(new File("bogus"), new File("bogus"), new File("bogus"));
            fail("Should have thrown an exception");
        }
        catch(Exception ex) {
            assertTrue(true);
        }
    }

    // ---------------------------------------------------------------------------------------------

    public void testGetHelperProfileNames1() throws Exception {
        String[] names = HelperProfile.getHelperProfileNames(new File(pathTestData, Helpers.folderProfile));
        assertTrue("Wrong Number of Profiles", names.length == 3);
    }
    
    /**
     * All profile names from folder, sorted
     */
    public void testGetHelperProfileNames2() throws Exception {
        String[] names = HelperProfile.getHelperProfileNames(new File(pathTestData, Helpers.folderProfile));
        assertEquals("Wrong Profile Name", "Test Profile", names[0]);
        assertEquals("Wrong Profile Name", "Test Profile 2", names[1]);
        assertEquals("Wrong Profile Name", "test profile 3", names[2]);
    }

    // ---------------------------------------------------------------------------------------------

    public void testGetFile() {
        assertNotNull("Get file was null", hp.getFile());
    }

    // ---------------------------------------------------------------------------------------------

    public void testGetDocument() {
        assertNotNull(hp.getDocument());
    }

    // ---------------------------------------------------------------------------------------------

    public void testGetProfileName1() {
        assertEquals("Wrong Profile Name", "Test Profile", hp.getProfileName());
    }
        
    public void testGetProfileName2() {
        // Null File name
        hp.setFile(null);
        assertEquals("Wrong Profile Name", "(Unnamed Profile)", hp.getProfileName());
    }

    // ---------------------------------------------------------------------------------------------

    public void testGetVocabularyFileName() {
        assertEquals("Wrong Vocabulary FileName", hp.getVocabFile().getName(), hp.getVocabularyFileName());
    }

    // ---------------------------------------------------------------------------------------------

    public void testSetVocabularyFileName() {
        hp.setVocabularyFileName("A File.xml");
        assertEquals("Wrong Vocabulary FileName", "A File.xml", hp.getVocabularyFileName());
    }

    // ---------------------------------------------------------------------------------------------

    public void testGetSchemaHelperFileName() {
        assertEquals("Wrong SchemaHelper FileName", hp.getSchemaHelperFile().getName(), hp.getSchemaHelperFileName());
    }

    // ---------------------------------------------------------------------------------------------

    public void testSetSchemaHelperFileName() {
        hp.setSchemaHelperFileName("A File.xml");
        assertEquals("Wrong SchemaHelper FileName", "A File.xml", hp.getSchemaHelperFileName());
    }

    // ---------------------------------------------------------------------------------------------

    public void testGetSchemaVersion() {
        assertNull("Shouldn't be a Schema Version", hp.getSchemaVersion());
    }

    // ---------------------------------------------------------------------------------------------

    public void testSetSchemaVersion() {
        hp.setSchemaVersion("A File.xml");
        assertEquals("Wrong Schema Version", "A File.xml", hp.getSchemaVersion());
    }

    // ---------------------------------------------------------------------------------------------

    public void testGetVocabFile() {
        assertTrue("Vocab file not found",
                hp.getVocabFile().equals(new File(pathTestData, Helpers.fileVocab1)));
    }

    // ---------------------------------------------------------------------------------------------

    public void testGetSchemaHelperFile() {
        assertTrue("SchemaHelper file not found",
                hp.getSchemaHelperFile().equals(new File(pathTestData, Helpers.fileTestHelper1)));
    }

    // ---------------------------------------------------------------------------------------------

    public void testGetVocabFolder() {
        assertTrue("Vocab folder not found",
                hp.getVocabFolder().equals(new File(pathTestData, Helpers.folderVocab)));
    }

    // ---------------------------------------------------------------------------------------------

    public void testGetSchemaHelperFolder() {
        assertTrue("SchemaHelper folder not found",
                hp.getSchemaHelperFolder().equals(new File(pathTestData, Helpers.folderSchemaHelper)));
    }

    // ---------------------------------------------------------------------------------------------
}
