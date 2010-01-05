
package uk.ac.reload.diva.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipOutputStream;

import junit.framework.TestCase;
import uk.ac.reload.testsupport.FileSupport;
import uk.ac.reload.testsupport.cp.CP_Package1;
import uk.ac.reload.testsupport.cp.Zip_Package1;


/**
 * ZipUtilsTest
 * 
 * @author Phillip Beauvoir
 * @version $Id: ZipUtilsTest.java,v 1.1 1998/03/25 20:20:17 ynsingh Exp $
 */
public class ZipUtilsTest extends TestCase {

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

    public void testAddFolderToZip() throws Exception {
        // Zip file
        File tmpZipFile = FileSupport.getTempFile(".zip");

        File rootFolder = new File(pathTestData, CP_Package1.folderCP);
        File srcFolder = new File(rootFolder, "zappa");
        
        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(tmpZipFile));
		ZipOutputStream zOut = new ZipOutputStream(out);

		ZipUtils.addFolderToZip(rootFolder, srcFolder, zOut, tmpZipFile);

		zOut.flush();
		zOut.close();
		
		// Extract them to see if they are there
		File tmpOutFolder = FileSupport.getTempFolder("ziptest");
		ZipUtils.unpackZip(tmpZipFile, tmpOutFolder);

		// Compare them
		FileSupport.checkSourceAndTargetFolderSame(srcFolder, new File(tmpOutFolder, "zappa"));
		assertTrue(true);
    }

    // ---------------------------------------------------------------------------------------------

    public void testAddFilesToZip() throws Exception {
        // Zip file
        File tmpZipFile = FileSupport.getTempFile(".zip");

        File rootFolder = new File(pathTestData, CP_Package1.folderCP);
        
        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(tmpZipFile));
		ZipOutputStream zOut = new ZipOutputStream(out);

		File[] files = rootFolder.listFiles();
		
		ZipUtils.addFilesToZip(rootFolder, files, zOut, tmpZipFile);

		zOut.flush();
		zOut.close();
		
		// Extract them to see if they are there
		File tmpOutFolder = FileSupport.getTempFolder("ziptest");
		ZipUtils.unpackZip(tmpZipFile, tmpOutFolder);

		// Compare them
		FileSupport.checkSourceAndTargetFolderSame(tmpOutFolder, rootFolder);
		assertTrue(true);
    }

    // ---------------------------------------------------------------------------------------------

    public void testAddFileToZip() throws Exception {
        // Zip file
        File tmpZipFile = FileSupport.getTempFile(".zip");
        
        // File to add
        File srcFile = new File(pathTestData, CP_Package1.fileManifest);
        
        // Add it
        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(tmpZipFile));
		ZipOutputStream zOut = new ZipOutputStream(out);
		
		ZipUtils.addFileToZip(srcFile, srcFile.getName(), zOut);
		
		zOut.flush();
		zOut.close();
        
		// Extract it to see if it's there
		File tmpOutFolder = FileSupport.getTempFolder("ziptest");
		ZipUtils.unpackZip(tmpZipFile, tmpOutFolder);
		File outFile = new File(tmpOutFolder, srcFile.getName());
		
		// Compare file
		FileSupport.checkSourceAndTargetFileSame(srcFile, outFile);
		assertTrue(true);
    }

    // ---------------------------------------------------------------------------------------------

    public void testAddStringToZip() throws Exception {
        // Zip file
        File tmpZipFile = FileSupport.getTempFile(".zip");
        
        // Add Entry
        String text = "Phillipus is cool!";
        String entryName = "sub/phillipus.text";

        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(tmpZipFile));
		ZipOutputStream zOut = new ZipOutputStream(out);
		
		ZipUtils.addStringToZip(text, entryName, zOut);
		
		zOut.flush();
		zOut.close();
		
		String textReturned = ZipUtils.extractZipEntry(tmpZipFile, entryName);
		assertEquals("Did not add string to zip file", text, textReturned);
    }

    // ---------------------------------------------------------------------------------------------

    public void testHasZipEntry_True() throws Exception {
        String entry = "space dir/aFile.txt";
        boolean result = ZipUtils.hasZipEntry(new File(pathTestData, Zip_Package1.fileZip), entry);
        assertTrue("Zip Entry not found", result);
    }

    public void testHasZipEntry_False() throws Exception {
        String entry = "space dir/bogus.txt";
        boolean result = ZipUtils.hasZipEntry(new File(pathTestData, Zip_Package1.fileZip), entry);
        assertFalse("Zip Entry should not found", result);
    }

    // ---------------------------------------------------------------------------------------------

    public void testExtractZipEntry_String() throws Exception {
        String entry = "space dir/aFile.txt";
        String text = ZipUtils.extractZipEntry(new File(pathTestData, Zip_Package1.fileZip), entry);
        assertEquals("Zip Entry wrong text", "This is a file", text);
    }

    // ---------------------------------------------------------------------------------------------

    public void testExtractZipEntry_File() throws Exception {
        String entry = "zappa/assets/zappa.jpg";
        File fileTemp = new File(FileSupport.getMainTestFolder(), entry);
        ZipUtils.extractZipEntry(new File(pathTestData, Zip_Package1.fileZip), entry, fileTemp);
        // Check it's OK by checking its file length
        assertEquals("Zip Entry wrong size", 15379, fileTemp.length());
    }

    // ---------------------------------------------------------------------------------------------

    public void testGetZipFileEntryNames() throws Exception {
        String[] entries = ZipUtils.getZipFileEntryNames(new File(pathTestData, Zip_Package1.fileZip));
        
        assertEquals("Wrong number of Zip entries", Zip_Package1.files.length / 2, entries.length);
        
        for(int i = 0, j = 0; i < entries.length; i++, j += 2) {
            assertEquals("Zip entry wrong", Zip_Package1.files[j], entries[i]);
        }
    }

    // ---------------------------------------------------------------------------------------------

    /**
     * Full Test on unpackZipFile
     */
    public void testUnpackZipFile1() throws Exception {
        // Get a temp folder, but we don't want to create it, because we want to test
        // that the routine will create it
        File folderTemp = new File(FileSupport.getMainTestFolder(), "ziptest");
        
        // Unzip
        ZipUtils.unpackZip(new File(pathTestData, Zip_Package1.fileZip), folderTemp);
        
        // Test output folder exists
        assertTrue("Zip Output folder not created: " + folderTemp, folderTemp.exists());
        
        // Compare files
        for(int i = 0; i < Zip_Package1.files.length; i += 2) {
            File file = new File(folderTemp, Zip_Package1.files[i]);
            
            // Correct File size is proof
            long size = Long.parseLong(Zip_Package1.files[i + 1]);
            assertEquals("Unzipped file wrong size: " + file.getPath(), size, file.length());
        }
    }

    /**
     * Bogus zip file not existing should throw Exception
     */
    public void testUnpackZipFile2() {
        try {
            ZipUtils.unpackZip(new File("bogus_file.zip"), FileSupport.getTempFolder("ziptest"));
            fail("Should have thrown Exception");
        }
        catch(IOException ex) {
            assertTrue(true);
        }
    }

    // ---------------------------------------------------------------------------------------------

}
