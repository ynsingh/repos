
package uk.ac.reload.moonunit;

import java.io.File;



/**
 * ProfiledSchemaControllerAbstractTest
 * 
 * @author Phillip Beauvoir
 * @version $Id: ProfiledSchemaControllerAbstractTest.java,v 1.1 1998/03/25 20:27:36 ynsingh Exp $
 */
public abstract class ProfiledSchemaControllerAbstractTest extends SchemaControllerAbstractTest {

    // ---------------------------------------------------------------------------------------------

    public void testProfiledSchemaController() {
        ProfiledSchemaController psc = (ProfiledSchemaController)sc;
        assertNotNull("Helper Profile null", psc.getHelperProfile());
        assertNotNull("Vocabulary null", psc.getVocabulary());
        assertNotNull("SchemaHelper null", psc.getSchemaHelper());
    }
    
    // ---------------------------------------------------------------------------------------------

    public void itestLoadHelperProfile(String profileName) throws Exception {
        ProfiledSchemaController psc = (ProfiledSchemaController)sc;
        psc.loadHelperProfile(profileName);
        assertNotNull("Helper Profile null", psc.getHelperProfile());
        assertNotNull("Vocabulary null", psc.getVocabulary());
        assertNotNull("SchemaHelper null", psc.getSchemaHelper());
    }
    
    // ---------------------------------------------------------------------------------------------
    
    public void testGetHelperProfile() {
        ProfiledSchemaController psc = (ProfiledSchemaController)sc;
        assertNotNull("Helper Profile null", psc.getHelperProfile());
    }
        
    // ---------------------------------------------------------------------------------------------
    
    public String[] itestGetHelperProfileNames() {
        ProfiledSchemaController psc = (ProfiledSchemaController)sc;
        String[] names = psc.getHelperProfileNames();
        assertNotNull("Helper Profile Names null", names);
        return names;
    }
        
    // ---------------------------------------------------------------------------------------------
    
    public void testGetProfileFolder() {
        ProfiledSchemaController psc = (ProfiledSchemaController)sc;
        File file = psc.getProfileFolder();
        assertTrue("File does not exist", file.exists());
    }
    
    // ---------------------------------------------------------------------------------------------
    
    public void testGetDefaultProfileName() {
        ProfiledSchemaController psc = (ProfiledSchemaController)sc;
        assertNotNull("Null default Profile name", psc.getDefaultProfileName());
    }
    
    // ---------------------------------------------------------------------------------------------
    
    public void testGetSchemaHelperFolder() {
        ProfiledSchemaController psc = (ProfiledSchemaController)sc;
        File file = psc.getSchemaHelperFolder();
        assertTrue("File does not exist", file.exists());
    }
    
    // ---------------------------------------------------------------------------------------------

    public void testGetVocabFolder() {
        ProfiledSchemaController psc = (ProfiledSchemaController)sc;
        File file = psc.getVocabFolder();
        assertTrue("File does not exist", file.exists());
    }
    
    // ---------------------------------------------------------------------------------------------
}
