
package uk.ac.reload.moonunit.schema;

import java.io.File;
import java.io.IOException;

import junit.framework.TestCase;

import org.exolab.castor.xml.schema.Schema;

import uk.ac.reload.testsupport.ITestsGlobals;
import uk.ac.reload.testsupport.Schemas;


/**
 * SchemaModelTest
 * 
 * @author Phillip Beauvoir
 * @version $Id: SchemaModelTest.java,v 1.1 1998/03/25 20:29:30 ynsingh Exp $
 */
public class SchemaModelTest
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

    /**
     * @return The MD 1.2.1 Schema Model
     * @throws Exception
     */
    protected SchemaModel getMD121_SchemaModel() throws Exception {
        File schemaFile = new File(pathTestData, Schemas.FILE_SCHEMA_MD121);
        return new SchemaModel(schemaFile, "lom");
    }

    /**
     * @return The CP 1.1.3 Schema Model
     * @throws Exception
     */
    protected SchemaModel getCP113_SchemaModel() throws Exception {
        File schemaFile = new File(pathTestData, Schemas.FILE_SCHEMA_CP113);
        return new SchemaModel(schemaFile, "manifest");
    }

    /**
     * @return The SCORM 1.2 CP 1.1.3 Schema Model
     * @throws Exception
     */
    protected SchemaModel getSCORM12_113_SchemaModel() throws Exception {
        File schemaFile = new File(pathTestData, Schemas.FILE_SCHEMA_SCORM12_IMCP113);
        return new SchemaModel(schemaFile, "manifest");
    }

    /**
     * @return The The LD A Author 1.0 Schema Model
     * @throws Exception
     */
    protected SchemaModel getLDA10_SchemaModelAuthor() throws Exception {
        File schemaFile = new File(pathTestData, Schemas.FILE_SCHEMA_LDA10_AUTHOR);
        return new SchemaModel(schemaFile, "learning-design");
    }

    /**
     * @return The The LD A IMS 1.0 Schema Model
     * @throws Exception
     */
    protected SchemaModel getLDA10_SchemaModelIMS() throws Exception {
        File schemaFile = new File(pathTestData, Schemas.FILE_SCHEMA_LDA10_IMS);
        return new SchemaModel(schemaFile, "learning-design");
    }

    // ---------------------------------------------------------------------------------------------

    /**
     * Test the Schema Models are cached
     */
    public void testGetSchemaModel() throws Exception {
        File schemaFile = new File(pathTestData, Schemas.FILE_SCHEMA_MD121);
        SchemaModel schemaModel = SchemaModel.getSchemaModel("md121", schemaFile, "lom");
        assertEquals("Wrong Schema Model", "lom", schemaModel.getRootElement().getName());
        
        // Get it again - should be cached
        SchemaModel schemaModel2 = SchemaModel.getSchemaModel("md121", schemaFile, "lom");
        assertSame("Schema Model not cached", schemaModel, schemaModel2);
    }
   
    // ---------------------------------------------------------------------------------------------

    /**
     * Test exception thrown if we provide a wrong root name
     */
    public void testSchemaModel_BogusRootName() throws Exception {
        File schemaFile = new File(pathTestData, Schemas.FILE_SCHEMA_CP113);
        try {
            SchemaModel schemaModel = new SchemaModel(schemaFile, "bogus");
            fail("SchemaException ahould have been thrown");
        }
        catch(SchemaException ex) {
            assertTrue(true);
        }
    }

    /**
     * Test exception thrown if we provide a wrong file name
     */
    public void testSchemaModel_BogusFileName() throws Exception {
        File schemaFile = new File("bogus.xsd");
        try {
            SchemaModel schemaModel = new SchemaModel(schemaFile, "manifest");
            fail("SchemaException ahould have been thrown");
        }
        catch(IOException ex) {
            assertTrue(true);
        }
    }

    // ---------------------------------------------------------------------------------------------

    public void testGetSchemaFile1() throws Exception {
        File schemaFile = new File(pathTestData, Schemas.FILE_SCHEMA_CP113);
        SchemaModel schemaModel = getCP113_SchemaModel();
        assertEquals("Schema File wrong", schemaFile, schemaModel.getSchemaFile());
    }

    public void testGetSchemaFile2() throws Exception {
        File schemaFile = new File(pathTestData, Schemas.FILE_SCHEMA_LDA10_IMS);
        SchemaModel schemaModel = getLDA10_SchemaModelIMS();
        assertEquals("Schema File wrong", schemaFile, schemaModel.getSchemaFile());
    }

    // ---------------------------------------------------------------------------------------------

    public void testGetRootElement() throws Exception {
        SchemaModel schemaModel = getCP113_SchemaModel();
        assertEquals("Schema Root Element wrong", "manifest", schemaModel.getRootElement().getName());
    }

    // ---------------------------------------------------------------------------------------------

    public void testGetRootElementName() throws Exception {
        SchemaModel schemaModel = getMD121_SchemaModel();
        assertEquals("Schema Root Element name wrong", "lom", schemaModel.getRootElementName());
    }

    // ---------------------------------------------------------------------------------------------

    public void testGetSchema() throws Exception {
        SchemaModel schemaModel = getMD121_SchemaModel();
        assertNotNull("Schema null", schemaModel.getSchema());
    }

    // ---------------------------------------------------------------------------------------------

    public void testAddImportedSchema() throws Exception {
        SchemaModel cpSchemaModel = getCP113_SchemaModel();
        SchemaModel ldSchemaModel = getLDA10_SchemaModelAuthor();
        
        // Try doing it twice to see if it's happy
        try {
            cpSchemaModel.addImportedSchema(ldSchemaModel, "imsld");
            cpSchemaModel.addImportedSchema(ldSchemaModel, "imsld");
        }
        catch(SchemaException ex) {
            fail("Threw SchemaException while importing Schema");
        }
        
        Schema ldSchema = cpSchemaModel.getSchema().getImportedSchema(ldSchemaModel.getTargetNamespaceURI());
        assertNotNull("Imported Schema was null", ldSchema);
    }
    
    // ---------------------------------------------------------------------------------------------

    /**
     * Attach LD 1.0 to CP 1.1.3
     */
    public void testAttachSchemaElement1() throws Exception {
        SchemaModel cpSchemaModel = getCP113_SchemaModel();
        SchemaModel ldSchemaModel = getLDA10_SchemaModelAuthor();
        
        cpSchemaModel.addImportedSchema(ldSchemaModel, "imsld");
        cpSchemaModel.attachSchemaElement(ldSchemaModel, "organizationsType", "learning-design");
        
        SchemaElement schemaElement = cpSchemaModel.getRootElement()
		  							  .getChild("organizations")
		  							  .getChild("learning-design", IMSLD_NAMESPACE_100)
		  							  .getChild("prerequisites");
        
        assertNotNull("SchemaElement was null", schemaElement);
    }

    /**
     * Attach MD 1.2.1 to CP 1.1.3
     */
    public void testAttachSchemaElement2() throws Exception {
        SchemaModel cpSchemaModel = getCP113_SchemaModel();
        SchemaModel ldSchemaModel = getMD121_SchemaModel();
        
        cpSchemaModel.addImportedSchema(ldSchemaModel, "imsmd");
        cpSchemaModel.attachSchemaElement(ldSchemaModel, "organizationsType", "lom");
        
        SchemaElement schemaElement = cpSchemaModel.getRootElement()
		  							  .getChild("organizations")
		  							  .getChild("lom", IMSMD_NAMESPACE_121)
		  							  .getChild("general");
        
        assertNotNull("SchemaElement was null", schemaElement);
    }

    // ---------------------------------------------------------------------------------------------

    public void testGetTargetNamespaceURI1() throws Exception {
        SchemaModel schemaModel = getMD121_SchemaModel();
        assertEquals("Target Namespace URI wrong", "http://www.imsglobal.org/xsd/imsmd_rootv1p2p1",
                schemaModel.getTargetNamespaceURI());
    }

    public void testGetTargetNamespaceURI2() throws Exception {
        SchemaModel schemaModel = getSCORM12_113_SchemaModel();
        assertEquals("Target Namespace URI wrong", "http://www.imsglobal.org/xsd/imscp_v1p1",
                schemaModel.getTargetNamespaceURI());
    }

    // ---------------------------------------------------------------------------------------------

    public void testGetSchemaNamespaceURI() throws Exception {
        SchemaModel schemaModel = getMD121_SchemaModel();
        assertEquals("Schema Namespace URI wrong", "http://www.w3.org/2001/XMLSchema",
                schemaModel.getSchemaNamespaceURI());
    }

    // ---------------------------------------------------------------------------------------------

    public void testGetSchemaLocation_String1() throws Exception {
        File schemaFile = new File(pathTestData, Schemas.FILE_SCHEMA_SCORM12_IMCP113);
        SchemaModel schemaModel = getSCORM12_113_SchemaModel();
        assertEquals("Schema Location wrong", schemaFile.toURL().toString(),
                schemaModel.getSchemaLocation("http://www.imsglobal.org/xsd/imscp_v1p1"));
    }

    public void testGetSchemaLocation_String2() throws Exception {
        File schemaFile = new File(pathTestData, Schemas.FILE_SCHEMA_SCORM12);
        SchemaModel schemaModel = getSCORM12_113_SchemaModel();
        assertEquals("Schema Location wrong", schemaFile.toURL().toString(),
                schemaModel.getSchemaLocation("http://www.adlnet.org/xsd/adlcp_rootv1p2"));
    }

    // ---------------------------------------------------------------------------------------------

    public void testGetSchemaName1() throws Exception {
        SchemaModel schemaModel = getMD121_SchemaModel();
        assertEquals("Schema Name wrong", "imsmd_rootv1p2p1.xsd", schemaModel.getSchemaName());
    }

    public void testGetSchemaName2() throws Exception {
        SchemaModel schemaModel = getCP113_SchemaModel();
        assertEquals("Schema Name wrong", "imscp_v1p1.xsd", schemaModel.getSchemaName());
    }

    // ---------------------------------------------------------------------------------------------

    public void testGetSchemaName_String1() throws Exception {
        SchemaModel schemaModel = getSCORM12_113_SchemaModel();
        assertEquals("Schema Name wrong", "imscp_v1p1.xsd",
                schemaModel.getSchemaName("http://www.imsglobal.org/xsd/imscp_v1p1"));
    }

    public void testGetSchemaName_String2() throws Exception {
        SchemaModel schemaModel = getSCORM12_113_SchemaModel();
        assertEquals("Schema Name wrong", "adlcp_rootv1p2.xsd",
                schemaModel.getSchemaName("http://www.adlnet.org/xsd/adlcp_rootv1p2"));
    }

    // ---------------------------------------------------------------------------------------------

    public void testGetNamespaceURI1() throws Exception {
        SchemaModel schemaModel = getSCORM12_113_SchemaModel();
        assertEquals("Namespace URI wrong", "http://www.imsglobal.org/xsd/imscp_v1p1",
                schemaModel.getNamespaceURI(""));
    }

    public void testGetNamespaceURI2() throws Exception {
        SchemaModel schemaModel = getSCORM12_113_SchemaModel();
        assertEquals("Namespace URI wrong", "http://www.adlnet.org/xsd/adlcp_rootv1p2",
                schemaModel.getNamespaceURI("adlcp"));
    }

    public void testGetNamespaceURI3() throws Exception {
        SchemaModel schemaModel = getLDA10_SchemaModelAuthor();
        assertEquals("Namespace URI wrong", "http://www.imsglobal.org/xsd/imsld_v1p0",
                schemaModel.getNamespaceURI(""));
    }

    // ---------------------------------------------------------------------------------------------

    public void testGetVersion1() throws Exception {
        SchemaModel schemaModel = getMD121_SchemaModel();
        assertEquals("Schema Name wrong", "1.2:1.1 IMS:MD1.2",
                schemaModel.getVersion());
    }

    public void testGetVersion2() throws Exception {
        SchemaModel schemaModel = getLDA10_SchemaModelAuthor();
        assertEquals("Schema Name wrong", "IMS LD Level A version 1.0 Final Draft",
                schemaModel.getVersion());
    }

    public void testGetVersion3() throws Exception {
        SchemaModel schemaModel = getCP113_SchemaModel();
        assertEquals("Schema Name wrong", "IMS CP 1.1.3",
                schemaModel.getVersion());
    }

    public void testGetVersion4() throws Exception {
        SchemaModel schemaModel = getSCORM12_113_SchemaModel();
        assertEquals("Schema Name wrong", "IMS CP 1.1.3",
                schemaModel.getVersion());
    }

    // ---------------------------------------------------------------------------------------------

}
