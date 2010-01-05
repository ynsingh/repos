
package uk.ac.reload.editor.contentpackaging.xml;

import java.io.File;

import javax.swing.Icon;

import org.jdom.Namespace;

import uk.ac.reload.diva.util.FileUtils;
import uk.ac.reload.editor.contentpackaging.xml.CP_SchemaController;
import uk.ac.reload.editor.metadata.xml.MD_SchemaController;
import uk.ac.reload.moonunit.ProfiledSchemaControllerAbstractTest;
import uk.ac.reload.testsupport.FileSupport;



/**
 * CP_SchemaControllerAbstractTest
 * 
 * @author Phillip Beauvoir
 * @version $Id: CP_SchemaControllerAbstractTest.java,v 1.1 1998/03/26 15:50:37 ynsingh Exp $
 */
public abstract class CP_SchemaControllerAbstractTest extends ProfiledSchemaControllerAbstractTest {

    protected void setUp() throws Exception {
		// Set Properties file to test properties
		System.setProperty("editor.properties.file", "uk.ac.reload.editor.testproperties.rb");
    }

    protected void tearDown() throws Exception {
        // Clean up
        FileUtils.deleteFolder(FileSupport.getMainTestFolder());
    }
    
    // ---------------------------------------------------------------------------------------------
    
    public Icon itestGetLeafIcon(String name, Namespace ns) {
        CP_SchemaController cpsc = (CP_SchemaController)sc;
        Icon icon = cpsc.getLeafIcon(name, ns);
        assertNotNull("Icon null", icon);
        return icon;
    }
    
    // ---------------------------------------------------------------------------------------------
    
    public Icon itestGetOpenIcon(String name, Namespace ns) {
        CP_SchemaController cpsc = (CP_SchemaController)sc;
        Icon icon = cpsc.getOpenIcon(name, ns);
        assertNotNull("Icon null", icon);
        return icon;
    }
    
    // ---------------------------------------------------------------------------------------------
    
    public Icon itestGetClosedIcon(String name, Namespace ns) {
        CP_SchemaController cpsc = (CP_SchemaController)sc;
        Icon icon = cpsc.getClosedIcon(name, ns);
        assertNotNull("Icon null", icon);
        return icon;
    }

    // ---------------------------------------------------------------------------------------------
    
    public void itestCopySchemaFilesToFolder(File[] expectedFiles, File targetFolder) throws Exception {
        CP_SchemaController cpsc = (CP_SchemaController)sc;
        File tmpFolder = FileSupport.getTempFolder("cp");
        cpsc.copySchemaFilesToFolder(targetFolder);
        
        // Have to delete the CVS files
        FileUtils.deleteFolder(new File(tmpFolder, "CVS"));
        
        assertEquals("Wrong number of files copied", expectedFiles.length, tmpFolder.listFiles().length); 
        for(int i = 0; i < expectedFiles.length; i++) {
            assertTrue("Copied file does not exist: " + expectedFiles[i], expectedFiles[i].exists());
        }
    }
    
    // ---------------------------------------------------------------------------------------------
    
    public void itestGetMetadataVersion(String expected) {
        CP_SchemaController cpsc = (CP_SchemaController)sc;
        assertEquals("Incorrect Metadata version", expected, cpsc.getMetadataVersion()); 
    }
    
    // ---------------------------------------------------------------------------------------------

    public void itestGetMD_SchemaController(MD_SchemaController expected) {
        CP_SchemaController cpsc = (CP_SchemaController)sc;
        assertEquals("Incorrect MD_SchemaController", expected, cpsc.getMD_SchemaController()); 
    }
    
    // ---------------------------------------------------------------------------------------------
    
    public void itestGetDefaultMD_SchemaController(Class expected) throws Exception {
        CP_SchemaController cpsc = (CP_SchemaController)sc;
        assertEquals("Incorrect MD_SchemaController", expected, cpsc.getDefaultMD_SchemaController().getClass()); 
    }

    // ---------------------------------------------------------------------------------------------
    
}
