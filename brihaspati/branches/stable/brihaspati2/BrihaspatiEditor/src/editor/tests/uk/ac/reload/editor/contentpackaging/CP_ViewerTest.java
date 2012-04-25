
package uk.ac.reload.editor.contentpackaging;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import junit.framework.TestCase;
import uk.ac.reload.diva.util.FileUtils;
import uk.ac.reload.editor.contentpackaging.xml.ContentPackage;
import uk.ac.reload.testsupport.FileSupport;
import uk.ac.reload.testsupport.cp.CP_Package1;


/**
 * Description
 * 
 * @author Phillip Beauvoir
 * @version $Id: CP_ViewerTest.java,v 1.1 1998/03/26 15:50:37 ynsingh Exp $
 */
public class CP_ViewerTest extends TestCase {
    
    ContentPackage cp;
    File exportFolder;
    CP_Viewer cpViewer;
    
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
        
		cp = new ContentPackage(new File(pathTestData, CP_Package1.fileManifest));
        cpViewer = new CP_Viewer(cp);
    }
    
    protected void tearDown() throws Exception {
        // Clean up
        FileUtils.deleteFolder(FileSupport.getMainTestFolder());
    }
    
    // ---------------------------------------------------------------------------------------------

    public void testParse_AbsoluteLinks() {
        doParseTest(false);
    }
    

    public void testParse_RelativeLinks() {
        doParseTest(true);
    }
    
    private void doParseTest(boolean relativePaths) {
        String[] javaStrings = cpViewer.parse(relativePaths);
        
        assertTrue("Array length is zero", javaStrings.length > 0);
        
        assertEquals("First line incorrect", "CPAPI.packageName = \"" + cp.getProjectName() + "\";",
                javaStrings[0]);
        
        for(int i = 0; i < javaStrings.length; i++) {
            String string = javaStrings[i];
            
            assertTrue("No semicolon", string.endsWith(";"));
            
            assertTrue("External URL shouldn't be relative", string.indexOf("../www") == -1);
            assertTrue("External URL shouldn't be relative", string.indexOf("../http") == -1);
            assertTrue("External URL shouldn't be relative", string.indexOf("../ftp:") == -1);
        }
        
        // Should be more things to test...
    }
    
    // ---------------------------------------------------------------------------------------------
    
    public void testWriteItemsToFile() throws Exception {
        File testFile = FileSupport.getTempFile(".js");
        String[] testStrings = {
                "This is Test String 1",
                "This is Test String 2",
                "This is Test String 3",
        };
        cpViewer.writeItemsToFile(testStrings, testFile);
        
        BufferedReader in = new BufferedReader(new FileReader(testFile));
        for(int i = 0; i < testStrings.length; i++) {
            assertEquals("Written Line not the same", testStrings[i], in.readLine());
        }
        
        assertNull("Too many lines written", in.readLine());
        
        in.close();
    }
    
    // ---------------------------------------------------------------------------------------------
    
    public void testEscapeQuotes1() {
        String inputString = "'phil'";
        String expectedString = "\\\\'phil\\\\'";
        assertEquals("Escaped String wrong", expectedString, cpViewer.escapeQuotes(inputString));
    }
        
    public void testEscapeQuotes2() {
        String inputString = "\"phil\"";
        String expectedString = "\\\"phil\\\"";
        assertEquals("Escaped String wrong", expectedString, cpViewer.escapeQuotes(inputString));
    }
    
    // ---------------------------------------------------------------------------------------------

    public void testEscapeBackslashes() {
        String inputString = "\\\\NetworkDrive\\path\\afile.txt";
        String expectedString = "\\\\\\\\NetworkDrive\\\\path\\\\afile.txt";
        assertEquals("Escaped String wrong", expectedString, cpViewer.escapeBackslashes(inputString));
    }
    
    // ---------------------------------------------------------------------------------------------

    /**
     * Make sure Resource files get copied over OK
     */    
    public void testCopyResourceFiles() throws Exception {
        exportFolder = FileSupport.getTempFolder("cp_export");
        cpViewer.copyResourceFiles(cp, exportFolder, null);

        File[] allResources = cp.getResourceFiles(cp.getRootElement());
        for(int i = 0; i < allResources.length; i++) {
            File srcFile = allResources[i];
            // Get relative path
            String path = FileUtils.getRelativePath(cp.getProjectFolder(), srcFile);
            // Get target file path
            File targetFile = new File(exportFolder, path);
            // Check it's all there
            FileSupport.checkSourceAndTargetFileSame(srcFile, targetFile);
            assertTrue(true);
        }
    }
    
    /**
     * Make sure we don't copy to the same folder as the source files
     */    
    public void testCopyResourceFiles_SameFolder() {
        try {
            cpViewer.copyResourceFiles(cp, cp.getProjectFolder(), null);
            // Shouldn't reach here
            fail("Should have thrown an Exception");
        }
        catch(IOException ex) {
            assertTrue(true);
        }
    }
    // ---------------------------------------------------------------------------------------------
    
    /**
     * Make sure Preview files get copied over OK
     */
    public void testCopyPreviewFiles() throws Exception {
        exportFolder = FileSupport.getTempFolder("cp_export");
        cpViewer.copyPreviewFiles(cpViewer.getPreviewFolder(), exportFolder);
        
        FileSupport.checkSourceAndTargetFolderSame(cpViewer.getPreviewFolder(), exportFolder);
        assertTrue(true);
    }
    
    // ---------------------------------------------------------------------------------------------
    
}
