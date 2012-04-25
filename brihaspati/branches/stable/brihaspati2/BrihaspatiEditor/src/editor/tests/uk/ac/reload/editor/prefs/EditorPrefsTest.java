package uk.ac.reload.editor.prefs;

import java.io.File;
import java.io.IOException;

import uk.ac.reload.diva.prefs.UserPrefs;
import uk.ac.reload.diva.prefs.UserPrefsAbstractTest;
import uk.ac.reload.testsupport.FileSupport;


/**
 * Description
 *
 * @author Phillip Beauvoir
 * @version $Id: EditorPrefsTest.java,v 1.1 1998/03/26 15:50:37 ynsingh Exp $
 */
public class EditorPrefsTest extends UserPrefsAbstractTest {
    
    protected void setUp() throws Exception {
		// Set Properties file to test properties - first!
		System.setProperty("editor.properties.file", "uk.ac.reload.editor.testproperties.rb");
        
		super.setUp();
    }

    /** 
     * @return concrete case
     */
    public UserPrefs getUserPrefs() {
        return new TestPrefs();
    }

    // ---------------------------------------------------------------------------------------------

    /**
     * Prefs Folder should be created
     */
    public void testGetPrefsFolder() {
        File file = ((TestPrefs)prefs).getPrefsFolder();
        assertTrue("Prefs Folder not created", file.exists());
    }
    
    // ---------------------------------------------------------------------------------------------

    /**
     * getElementRootName() should return correct root name
     */
    public void testGetElementRootName() {
        assertEquals("ElementRootName not correct", EditorPrefs.USER_PREFS_ELEMENTNAME,
                prefs.getElementRootName());
    }

    // ---------------------------------------------------------------------------------------------

    public void testGetFileHistory() {
        assertNotNull("File History was null", ((EditorPrefs)prefs).getFileHistory());
    }
    
    // ---------------------------------------------------------------------------------------------

    public void testAddFileToHistory() {
        File file1 = new File("Path/afile1.xml");
        File file2 = new File("Path/afile2.xml");
        ((EditorPrefs)prefs).addFileToHistory(file1);
        ((EditorPrefs)prefs).addFileToHistory(file2);
        String[] s = ((EditorPrefs)prefs).getFileHistory();
        assertEquals("File History was wrong", file1.getPath(), s[1]);
        assertEquals("File History was wrong", file2.getPath(), s[0]);
    }
    
    // ---------------------------------------------------------------------------------------------
    // ---------------------------------------------------------------------------------------------
    // ---------------------------------------------------------------------------------------------
    // ---------------------------------------------------------------------------------------------

    /**
     * Extended EditorPrefs Class so we can be sure it's there and also write to it
     */
    class TestPrefs extends EditorPrefs {
        // Reusable test file
        File tmpFile = null;
        
        public File getPrefsFile() {
            if(tmpFile == null)
                try {
                    tmpFile = FileSupport.getTempFile(".xml");
                } catch(IOException ex) {
                    ex.printStackTrace();
                }
            return tmpFile;
        }

        public File getPrefsFolder() {
            return getPrefsFile().getParentFile();
        }
    }
}
