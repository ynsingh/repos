
package uk.ac.reload.editor.learningdesign.datamodel.components.roles;

import java.io.File;

import junit.framework.TestCase;

import org.jdom.Element;

import uk.ac.reload.diva.util.FileUtils;
import uk.ac.reload.editor.datamodel.DataComponent;
import uk.ac.reload.editor.learningdesign.datamodel.LD_DataModel;
import uk.ac.reload.editor.learningdesign.xml.LDA10_CP113_SchemaController;
import uk.ac.reload.editor.learningdesign.xml.LearningDesign;
import uk.ac.reload.editor.metadata.xml.MD122_SchemaController;
import uk.ac.reload.editor.metadata.xml.MD_SchemaController;
import uk.ac.reload.testsupport.FileSupport;
import uk.ac.reload.testsupport.ld.LD_File2;




/**
 * Roles_GroupingTest
 * 
 * @author Phillip Beauvoir
 * @version $Id: Roles_GroupingTest.java,v 1.1 1998/03/26 15:50:37 ynsingh Exp $
 */
public class Roles_GroupingTest extends TestCase {

    /**
     * The path to the Test Data
     */
    String pathTestData;
    
    protected void setUp() throws Exception {
        // See if we have a testdata dir set from an Ant script
        pathTestData = System.getProperty("testdata.dir");
        if(pathTestData == null) {
            // Set a default
            pathTestData = "../junit-tests";
        }

        // Set Properties file to test properties
		System.setProperty("editor.properties.file", "uk.ac.reload.editor.testproperties.rb");
    }
    
    protected void tearDown() throws Exception {
        // Clean up
        FileUtils.deleteFolder(FileSupport.getMainTestFolder());
    }

    /**
     * Load a Minimal LD_DataModel
     * @return LD_DataModel
     * @throws Exception
     */
    protected LD_DataModel loadMinimalLD_DataModel() throws Exception {
        File file = new File(pathTestData, LD_File2.fileLD);
        LearningDesign ld = new LearningDesign(file);
        return new LD_DataModel(ld);
    }

    /**
     * Create a new LD_DataModel
     * @return LD_DataModel
     * @throws Exception
     */
    protected LD_DataModel createNewLD_DataModel() throws Exception {
		LDA10_CP113_SchemaController ldController = new LDA10_CP113_SchemaController();
		MD_SchemaController mdController = new MD122_SchemaController();
        File folder = FileSupport.getTempFolder("cp-test");
		LearningDesign ld = new LearningDesign(folder, ldController, mdController);
		return new LD_DataModel(ld);
    }

    // ---------------------------------------------------------------------------------------------
    
    /**
     * Test a blank Roles Grouping
     */
    public void testRoles_Grouping_New() throws Exception {
		LD_DataModel dataModel = createNewLD_DataModel();
		Roles_Grouping rolesGrouping = dataModel.getRoles_Grouping();
		itestRoles_Grouping(rolesGrouping);
    }
    
    /**
     * Test a loaded Roles Grouping
     */
    public void testRoles_Grouping_File() throws Exception {
        LD_DataModel dataModel = loadMinimalLD_DataModel();
        Roles_Grouping rolesGrouping = dataModel.getRoles_Grouping();
        itestRoles_Grouping(rolesGrouping);
    }
    
    /**
     * Internal Test on Roles_Grouping
     * @param rolesGrouping Roles_Grouping
     */
    private void itestRoles_Grouping(Roles_Grouping rolesGrouping) {
		assertNotNull("Roles Grouping was null", rolesGrouping);
		
        DataComponent[] children = rolesGrouping.getChildren();
        assertEquals("Wrong number of children", 2, children.length);
        assertEquals("Wrong child class", Learner_Grouping.class, children[0].getClass());
        assertEquals("Wrong child class", Staff_Grouping.class, children[1].getClass());
        
        assertNotNull("Learner Grouping was null", rolesGrouping.getLearner_Grouping());
        assertNotNull("Staff Grouping was null", rolesGrouping.getStaff_Grouping());
        
        Element element = rolesGrouping.getElement();
        assertEquals("<roles> element was wrong", "roles", element.getName());
    }
}
