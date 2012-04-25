
package uk.ac.reload.editor.metadata.xml;

import java.io.File;

import uk.ac.reload.editor.metadata.MD_EditorHandler;




/**
 * MD122_SchemaControllerTest
 * 
 * @author Phillip Beauvoir
 * @version $Id: MD122_SchemaControllerTest.java,v 1.1 1998/03/26 15:50:37 ynsingh Exp $
 */
public class MD122_SchemaControllerTest extends MD121_SchemaControllerTest {

    protected void setUp() throws Exception {
		super.setUp();
        sc = new MD122_SchemaController();
    }
    
    // ---------------------------------------------------------------------------------------------
    
    public void testGetVersion() {
        itestGetVersion(MD_EditorHandler.IMS_METADATA_1_2_2);
    }
    
    // ---------------------------------------------------------------------------------------------
    
    public void testGetRootElementName() {
        itestGetRootElementName("lom");
    }
    
    // ---------------------------------------------------------------------------------------------
    
    public void testGetSchemaFile() {
        File file = itestGetSchemaFile();
        assertEquals("Wrong Schema File", MD122_SchemaController.fileSchemaMD1_2_2, file);
    }

    // ---------------------------------------------------------------------------------------------
    
}
