package uk.ac.reload.diva.util;

import java.io.File;
import java.io.IOException;

import junit.framework.TestCase;
import uk.ac.reload.testsupport.FileSupport;
import uk.ac.reload.testsupport.cp.CP_Package1;


/**
 * Description
 *
 * @author Phillip Beauvoir
 * @version $Id: FileUtilsTest.java,v 1.1 1998/03/25 20:20:17 ynsingh Exp $
 */
public class FileUtilsTest extends TestCase {

    String pathTestData;

    protected void setUp() throws Exception {
        // See if we have a testdata dir set from an Ant script
        pathTestData = System.getProperty("testdata.dir");
        if(pathTestData == null) {
            // Set a default
            pathTestData = "../junit-tests";
        }
    }

    protected void tearDown() throws Exception {
        // Clean up
        FileUtils.deleteFolder(FileSupport.getMainTestFolder());
    }
    
    // ---------------------------------------------------------------------------------------------

    /**
     * getFileExtension() works and is lower case
     */
    public void testGetFileExtension1() {
        File file = new File("test/file.TXT");
        String ext = FileUtils.getFileExtension(file);
        assertEquals("Wrong file extension", "txt", ext);
        assertTrue("Should not contain dot", ext.indexOf(".") == -1);
    }
    
    /**
     * getFileExtension() works on no extension
     */
    public void testGetFileExtension2() {
        File file = new File("test/file");
        String ext = FileUtils.getFileExtension(file);
        assertEquals("Wrong file extension", "", ext);
    }
    
    // ---------------------------------------------------------------------------------------------

    /**
     * getFileNameWithoutExtension() works and is correct case
     */
    public void testGetFileNameWithoutExtension1() {
        File file = new File("test/File.txt");
        String name = FileUtils.getFileNameWithoutExtension(file);
        assertEquals("Wrong file extension", "File", name);
    }
    
    /**
     * getFileNameWithoutExtension() works on no extension and is correct case
     */
    public void testGetFileNameWithoutExtension2() {
        File file = new File("test/File");
        String name = FileUtils.getFileNameWithoutExtension(file);
        assertEquals("Wrong file extension", "File", name);
    }

    // ---------------------------------------------------------------------------------------------

    /**
     * Don't copy to same folder
     */
    public void testCopyFolder_SameFolder() {
        try {
            File folderSrc = FileSupport.getTempFolder("src");
            FileUtils.copyFolder(folderSrc, folderSrc);
            // Shouldn't reach here
            fail("Should have thrown an Exception");
        }
        catch(IOException ex) {
            assertTrue(true);
        }
    }
    
    /**
     * Don't copy to sub folder
     */
    public void testCopyFolder_SubFolder() {
        try {
            File folderSrc = FileSupport.getTempFolder("src");
            File folderDest = FileSupport.getTempFolder("src/dest");
            FileUtils.copyFolder(folderSrc, folderDest);
            // Shouldn't reach here
            fail("Should have thrown an Exception");
        }
        catch(IOException ex) {
            assertTrue(true);
        }
    }

    /**
     * Copy Folder works OK
     */
    public void testCopyFolder_FilesValid() throws Exception {
        File folderSrc = new File(pathTestData, CP_Package1.folderCP);
        File folderTgt = FileSupport.getTempFolder("tgt");
        FileUtils.copyFolder(folderSrc, folderTgt);
        
        FileSupport.checkSourceAndTargetFolderSame(folderSrc, folderTgt);
        assertTrue(true);
    }

    /**
     * Copy Folder rejects non-existing folders
     */
    public void testCopyFolder_NotExists() {
        File folderSrc = new File("absolutely_bogus/");
        File folderTgt = FileSupport.getTempFolder("tgt");
        try {
            FileUtils.copyFolder(folderSrc, folderTgt);
            // Shouldn't reach here
            fail("Should have thrown an Exception");
        } catch(Exception ex) {
            assertTrue(true);
        }
    }

    // ---------------------------------------------------------------------------------------------

    /**
     * Don't copy to same file
     */
    public void testCopyFile_SameFile() {
        try {
            File fileSrc = FileSupport.getTempFile(".txt");
            FileUtils.copyFile(fileSrc, fileSrc);
            // Shouldn't reach here
            fail("Should have thrown an Exception");
        }
        catch(IOException ex) {
            assertTrue(true);
        }
    }
    
    /**
     * Copy File works OK
     */
    public void testCopyFile_FilesValid() throws Exception {
        File fileSrc = new File(pathTestData, CP_Package1.fileManifest);
        File fileTgt = FileSupport.getTempFile(".txt");
        FileUtils.copyFile(fileSrc, fileTgt);
        
        FileSupport.checkSourceAndTargetFileSame(fileSrc, fileTgt);
        assertTrue(true);
    }

    /**
     * Copy File rejects non-existing folders
     */
    public void testCopyFile_NotExists() throws Exception {
        File fileSrc = new File("absolutely_bogus.txt");
        File fileTgt = FileSupport.getTempFile(".txt");
        try {
            FileUtils.copyFile(fileSrc, fileTgt);
            // Shouldn't reach here
            fail("Should have thrown an Exception");
        } catch(Exception ex) {
            assertTrue(true);
        }
    }

    // ---------------------------------------------------------------------------------------------

    public void testMoveFile() throws Exception {
        File folderSrc = FileSupport.getTempFolder("src");
        File srcFile = new File(folderSrc, "temp.xml");
        
        File folderTgt = FileSupport.getTempFolder("tgt");
        File tgtFile = new File(folderTgt, "temp.xml");
        
        FileUtils.copyFile(new File(pathTestData, CP_Package1.fileManifest), srcFile);
        assertTrue("Test Source File should exist", srcFile.exists());
        
        FileUtils.moveFile(srcFile, tgtFile);
        assertFalse("Source File should not exist", srcFile.exists());
        assertTrue("Target File should exist", tgtFile.exists());
    }
    
    // ---------------------------------------------------------------------------------------------
    
    public void testDeleteFolder() throws Exception {
        File folder = FileSupport.getTempFolder("delete_folder");
        FileUtils.copyFolder(new File(pathTestData, CP_Package1.folderCP), folder);
        FileUtils.deleteFolder(folder);
        assertFalse("Deleted Folder should not exist", folder.exists());
    }
    
    /**
     * Shouldn't be able to delete a single file
     */
    public void testDeleteFolder_IfFile() throws Exception {
        File file = FileSupport.getTempFile(".del");
        assertTrue("Test File should exist", file.exists());
        
        FileUtils.deleteFolder(file);
        assertTrue("File should exist", file.exists());
    }
    
    /**
     * DeleteFolder ignores non-existing folders
     */
    public void testDeleteFolder_NotExists() {
        File folderSrc = new File("/aFolder/absolutely_bogus/");
        try {
            FileUtils.deleteFolder(folderSrc);
            assertTrue(true);
        } catch(IOException ex) {
            fail("Shouldn't throw Exception");
        }
    }

    // ---------------------------------------------------------------------------------------------
    

    public void testSortFiles() throws Exception {
        File folder = FileSupport.getTempFolder("sort_folder");
        
        File f0 = new File(folder, "a.txt"); f0.createNewFile();
        File f1 = new File(folder, "b.txt"); f1.createNewFile();
        File f2 = new File(folder, "/a"); f2.mkdir();
        File f3 = new File(folder,"/b"); f3.mkdir();
        
        File[] sorted = FileUtils.sortFiles(folder.listFiles());
        assertEquals("File not in right position", sorted[0], f2);
        assertEquals("File not in right position", sorted[1], f3);
        assertEquals("File not in right position", sorted[2], f0);
        assertEquals("File not in right position", sorted[3], f1);
    }
    
    // ---------------------------------------------------------------------------------------------
    
    /**
     * Root drive case-sensitivity issue on Windows
     */
    public void testGetRelativePath1() {
        switch(GeneralUtils.getOS()) {
            case GeneralUtils.WINDOWS_XP:
            case GeneralUtils.WINDOWS_9x:
            case GeneralUtils.WINDOWS_NT:
            case GeneralUtils.WINDOWS_2000:
                File rootFolder = new File("c:/rootfolder");
            	File file = new File("C:/rootfolder/dir/file.txt");
            	String path = FileUtils.getRelativePath(rootFolder, file);
            	assertFalse("Relative Path is wrong: " + path, path.startsWith("/"));
            	break;
            default:
                assertTrue(true);
        }
    }

    /**
     * Path should be subset of parent path and preserve case
     */
    public void testGetRelativePath2() {
        File rootFolder = new File("/RootFolder");
        File file = new File("/RootFolder/Dir/FileHere.txt");
        String path = FileUtils.getRelativePath(rootFolder, file);
        assertTrue("Relative Path is wrong: " + path, path.equals("Dir/FileHere.txt"));
    }
    
    /**
     * Test for absolute path
     */
    public void testGetRelativePath3() {
        File rootFolder = new File("/rootfolder");
        File file = new File("/anotherfolder/dir/file.txt");
        String path = FileUtils.getRelativePath(rootFolder, file);
        assertTrue("Absolute Path is wrong: " + path, path.startsWith("/"));
    }

    // ---------------------------------------------------------------------------------------------
    
}
