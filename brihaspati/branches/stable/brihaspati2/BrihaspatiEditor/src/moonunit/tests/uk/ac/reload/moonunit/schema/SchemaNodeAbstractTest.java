
package uk.ac.reload.moonunit.schema;

import junit.framework.TestCase;


/**
 * SchemaNodeAbstractTest
 * 
 * @author Phillip Beauvoir
 * @version $Id: SchemaNodeAbstractTest.java,v 1.1 1998/03/25 20:29:30 ynsingh Exp $
 */
public abstract class SchemaNodeAbstractTest extends TestCase {

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
     * Sub-classes of this Abstract Test must return a concrete SchemaNode sub-class
     * @return a concrete SchemaNode sub-class
     */
    public abstract SchemaNode getSchemaNodeImpl() throws Exception;
    
    // ---------------------------------------------------------------------------------------------

    public void testSetParent() throws Exception {
        SchemaNode schemaNode = getSchemaNodeImpl();
        SchemaNode node = schemaNode.setParent(new Object());
        assertEquals("Returned SchemaNode should be this", schemaNode, node);
    }

    // ---------------------------------------------------------------------------------------------

    public void testGetSchemaModel() throws Exception {
        SchemaNode schemaNode = getSchemaNodeImpl();
        assertNotNull("SchemaModel should not be null", schemaNode.getSchemaModel());
    }

    // ---------------------------------------------------------------------------------------------

}
