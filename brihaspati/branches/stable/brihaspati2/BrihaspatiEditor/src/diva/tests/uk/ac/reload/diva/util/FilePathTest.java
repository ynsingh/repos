package uk.ac.reload.diva.util;

import java.io.File;

import junit.framework.TestCase;
import uk.ac.reload.testsupport.FileSupport;


/**
 * Description
 *
 * @author Phillip Beauvoir
 * @version $Id: FilePathTest.java,v 1.1 1998/03/25 20:20:17 ynsingh Exp $
 */
public class FilePathTest extends TestCase {
    
    protected void setUp() throws Exception {

    }
    
    // ---------------------------------------------------------------------------------------------

    /**
     * Path starts with file:///
     */
    public void testGetURL_FilePrefix1() {
        File testFile = new File("Some Path/afile.txt");
        FilePath fPath = new FilePath(testFile);
        assertNotNull("FilePath was null", fPath);
        
        String path = fPath.getURL();
        assertEquals("Path should start with \"file:///\" - " + path, "file:///", path.substring(0, 8));
        assertTrue("Too many slashes - " + path, path.charAt(9) != '/');
    }

    /**
     * Path starts with file:/// on paths starting with "/"
     */
    public void testGetURL_FilePrefix2() {
        File testFile = new File("/Some Path/afile.txt");
        FilePath fPath = new FilePath(testFile);
        assertNotNull("FilePath was null", fPath);
        
        String path = fPath.getURL();
        assertEquals("Path should start with \"file:///\" - " + path, "file:///", path.substring(0, 8));
        assertTrue("Too many slashes - " + path, path.charAt(9) != '/');
    }

    /**
     * Path respects network drive
     */
    public void testGetURL_NetworkPath() {
        switch(GeneralUtils.getOS()) {
            case GeneralUtils.WINDOWS_XP:
            case GeneralUtils.WINDOWS_9x:
            case GeneralUtils.WINDOWS_NT:
            case GeneralUtils.WINDOWS_2000:
                File testFile = new File("\\\\Network Drive\\a path\\a file.html");
            	FilePath fPath = new FilePath(testFile);
            	assertNotNull("FilePath was null", fPath);
            
            	String expected = "file:///\\\\Network%20Drive\\a%20path\\a%20file.html";
            	String path = fPath.getURL();
            	assertEquals("Network path is wrong", expected, path);
            default:
                assertTrue(true);
        }
    }
    
    /**
     * Path escapes chars
     */
    public void testGetURL_Escaped() {
        File testFile = new File("Some Path/a file.txt");
        FilePath fPath = new FilePath(testFile);
        assertNotNull("FilePath was null", fPath);

        String path = fPath.getURL();
        String expected = "Some%20Path/a%20file.txt";
        assertTrue("Path should be escaped - " + path, fPath.getURL().endsWith(expected));
    }

    /**
     * Directory gets "/" appended
     */
    public void testGetURL_Dir() {
        File testFolder = FileSupport.getTempFolder("testFolder");
        FilePath fPath = new FilePath(testFolder);
        assertNotNull("FilePath was null", fPath);
        
        String path = fPath.getURL();
        assertTrue("Path should have end slash - " + path, path.endsWith("/"));
    }
    
    /**
     * params are appended
     */
    public void testGetURL_Params() {
        File testFile = new File("Some Path/afile.txt");
        String params = "?params";
        FilePath fPath = new FilePath(testFile, params);
        assertNotNull("FilePath was null", fPath);
        
        String path = fPath.getURL();
        assertTrue("Path should end with params: " + path, path.endsWith(params));
        assertFalse("Path should not have slash: " + path, path.endsWith("/" + params));
    }
    
    // ---------------------------------------------------------------------------------------------
    
}
