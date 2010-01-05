package uk.ac.reload.diva.util;

import java.io.File;
import java.util.Locale;
import java.util.MissingResourceException;

import junit.framework.TestCase;


/**
 * Description
 *
 * @author Phillip Beauvoir
 * @version $Id: ResourceBundleManagerTest.java,v 1.1 1998/03/25 20:20:17 ynsingh Exp $
 */
public class ResourceBundleManagerTest extends TestCase {
    
    /**
     * Fully qualified location of test properties file
     */
    static final String TEST_PROPERTIES = "uk.ac.reload.diva.util.test";
    
    /**
     * @return The Test ResourceBundleManager
     */
    ResourceBundleManager getResourceBundleManager() {
        ResourceBundleManager rbManager = new ResourceBundleManager(TEST_PROPERTIES);
        assertNotNull(ResourceBundleManager.class + " is null",
                rbManager.getResourceBundle());
        return rbManager;
    }
    
    // ---------------------------------------------------------------------------------------------

    /**
     * ResourceBundle is found for all Locales
     */
    public void testResourceBundleManager_Locales() {
        // Save existing Locale
        Locale old_locale = Locale.getDefault();
        
        Locale[] locales = Locale.getAvailableLocales();
        for(int i = 0; i < locales.length; i++) {
            Locale.setDefault(locales[i]);
            getResourceBundleManager();
            // Exception would be thrown if not found
            assertTrue(true);
        }
        
        // Restore old Locale
        Locale.setDefault(old_locale);
    }

    // ---------------------------------------------------------------------------------------------

    /**
     * Test for null
     */
    public void testGetFileProperty_Null() {
        ResourceBundleManager rbManager = getResourceBundleManager();
        File file = rbManager.getFileProperty("bogus");
        assertNull("File Property is null", file);
    }

    /**
     * Test for user.home
     */
    public void testGetFileProperty_userhome() {
        ResourceBundleManager rbManager = getResourceBundleManager();
        File file = rbManager.getFileProperty("test1.dir");
        assertNotNull("File Property is null", file);
        assertTrue("File Property does not exist", file.exists());
        assertEquals("File Property not found", System.getProperty("user.home"), file.getAbsolutePath());
    }

    /**
     * Test for user.dir
     */
    public void testGetFileProperty_userdir() {
        ResourceBundleManager rbManager = getResourceBundleManager();
        File file = rbManager.getFileProperty("test2.dir");
        assertNotNull("File Property is null", file);
        assertTrue("File Property does not exist", file.exists());
        assertEquals("File Property not found", System.getProperty("user.dir"), file.getAbsolutePath());
    }

    /**
     * Test for absolute path on Windows
     */
    public void testGetFileProperty_absoluteWin1() {
        switch(GeneralUtils.getOS()) {
            case GeneralUtils.WINDOWS_XP:
            case GeneralUtils.WINDOWS_9x:
            case GeneralUtils.WINDOWS_NT:
            case GeneralUtils.WINDOWS_2000:
                ResourceBundleManager rbManager = getResourceBundleManager();
            	File file = rbManager.getFileProperty("test3.dir");
            	assertNotNull("File Property is null", file);
            	assertEquals("File Property not found", "c:\\testfolder", file.getAbsolutePath());
                break;
            default:
                assertTrue(true);
        }
    }

    /**
     * Test for absolute path on Windows
     */
    public void testGetFileProperty_absoluteWin2() {
        switch(GeneralUtils.getOS()) {
            case GeneralUtils.WINDOWS_XP:
            case GeneralUtils.WINDOWS_9x:
            case GeneralUtils.WINDOWS_NT:
            case GeneralUtils.WINDOWS_2000:
                ResourceBundleManager rbManager = getResourceBundleManager();
            	File file = rbManager.getFileProperty("test4.dir");
            	assertNotNull("File Property is null", file);
            	assertEquals("File Property not found", "c:\\testfolder\\path1\\path2", file.getAbsolutePath());
                break;
            default:
                assertTrue(true);
        }
    }

    /**
     * Test for absolute path on Windows
     */
    public void testGetFileProperty_absoluteWin3() {
        switch(GeneralUtils.getOS()) {
            case GeneralUtils.WINDOWS_XP:
            case GeneralUtils.WINDOWS_9x:
            case GeneralUtils.WINDOWS_NT:
            case GeneralUtils.WINDOWS_2000:
                ResourceBundleManager rbManager = getResourceBundleManager();
            	File file = rbManager.getFileProperty("test11.dir");
            	assertNotNull("File Property is null", file);
            	assertEquals("File Property not found", "c:\\", file.getAbsolutePath());
                break;
            default:
                assertTrue(true);
        }
    }

    /**
     * Test for absolute path on Windows
     */
    public void testGetFileProperty_absoluteWin4() {
        switch(GeneralUtils.getOS()) {
            case GeneralUtils.WINDOWS_XP:
            case GeneralUtils.WINDOWS_9x:
            case GeneralUtils.WINDOWS_NT:
            case GeneralUtils.WINDOWS_2000:
                ResourceBundleManager rbManager = getResourceBundleManager();
            	File file = rbManager.getFileProperty("test12.dir");
            	assertNotNull("File Property is null", file);
            	assertEquals("File Property not found", "c:\\", file.getAbsolutePath());
                break;
            default:
                assertTrue(true);
        }
    }

    /**
     * Test for relative path
     */
    public void testGetFileProperty_relative1() throws Exception {
        ResourceBundleManager rbManager = getResourceBundleManager();
        File file = rbManager.getFileProperty("test5.dir");
        assertNotNull("File Property is null", file);
        File baseDir = new File(System.getProperty("user.dir"));
        String expected = new File(baseDir, "testfolder/path1/path2").getAbsolutePath();
        expected = expected.replace(File.separatorChar, '/');
        String result = file.getCanonicalPath().replace(File.separatorChar, '/');
        assertEquals("File Property not found", expected, result);
    }

    /**
     * Test for relative path
     */
    public void testGetFileProperty_relative2() throws Exception {
        ResourceBundleManager rbManager = getResourceBundleManager();
        File file = rbManager.getFileProperty("test6.dir");
        assertNotNull("File Property is null", file);
        String expected = new File("/testfolder/path1/path2").getAbsolutePath();
        expected = expected.replace(File.separatorChar, '/');
        String result = file.getCanonicalPath().replace(File.separatorChar, '/');
        assertEquals("File Property not found", expected, result);
    }
    
    /**
     * Test for relative path
     */
    public void testGetFileProperty_relative3() throws Exception {
        ResourceBundleManager rbManager = getResourceBundleManager();
        File file = rbManager.getFileProperty("test7.dir");
        assertNotNull("File Property is null", file);
        File baseDir = new File(System.getProperty("user.dir"));
        String expected = new File(baseDir, "testfolder/path1/path2").getAbsolutePath();
        expected = expected.replace(File.separatorChar, '/');
        String result = file.getCanonicalPath().replace(File.separatorChar, '/');
        assertEquals("File Property not found", expected, result);
    }

    /**
     * Test for relative path with ..
     */
    public void testGetFileProperty_relative4() throws Exception {
        ResourceBundleManager rbManager = getResourceBundleManager();
        File file = rbManager.getFileProperty("test8.dir");
        assertNotNull("File Property is null", file);
        File baseDir = new File(System.getProperty("user.dir")).getParentFile();
        String expected = new File(baseDir, "testfolder").getAbsolutePath();
        expected = expected.replace(File.separatorChar, '/');
        String result = file.getCanonicalPath().replace(File.separatorChar, '/');
        assertEquals("File Property not found", expected, result);
    }

    /**
     * Test for user path
     */
    public void testGetFileProperty_user1() throws Exception {
        ResourceBundleManager rbManager = getResourceBundleManager();
        File file = rbManager.getFileProperty("test9.dir");
        assertNotNull("File Property is null", file);
        File baseDir = new File(System.getProperty("user.home"));
        String expected = new File(baseDir, "testfolder").getAbsolutePath();
        expected = expected.replace(File.separatorChar, '/');
        String result = file.getCanonicalPath().replace(File.separatorChar, '/');
        assertEquals("File Property not found", expected, result);
    }

    /**
     * Test for user path
     */
    public void testGetFileProperty_user2() throws Exception {
        ResourceBundleManager rbManager = getResourceBundleManager();
        File file = rbManager.getFileProperty("test10.dir");
        assertNotNull("File Property is null", file);
        File baseDir = new File(System.getProperty("user.home"));
        String expected = new File(baseDir, "testfolder/testfolder2").getAbsolutePath();
        expected = expected.replace(File.separatorChar, '/');
        String result = file.getCanonicalPath().replace(File.separatorChar, '/');
        assertEquals("File Property not found", expected, result);
    }

    /**
     * Test for System property being bogus - should return root
     * @throws Exception
     */
    public void testGetFileProperty_SystemProperty_IsNull() throws Exception {
        ResourceBundleManager rbManager = getResourceBundleManager();
        File file = rbManager.getFileProperty("test13.dir");
        assertNotNull("File Property should not be null", file);
        String expected = new File(System.getProperty("user.dir")).getAbsolutePath();
        expected = expected.replace(File.separatorChar, '/');
        String result = file.getCanonicalPath().replace(File.separatorChar, '/');
        assertEquals("File Property not found", expected, result);
    }
    
    /**
     * Test for System property being correct - should return file
     * @throws Exception
     */
    public void testGetFileProperty_SystemProperty_NotNull1() throws Exception {
        System.setProperty("my.dir", ".");
        ResourceBundleManager rbManager = getResourceBundleManager();
        File file = rbManager.getFileProperty("test13.dir");
        assertNotNull("File Property should not be null", file);
        String expected = new File(System.getProperty("user.dir")).getAbsolutePath();
        expected = expected.replace(File.separatorChar, '/');
        String result = file.getCanonicalPath().replace(File.separatorChar, '/');
        assertEquals("File Property not found", expected, result);
    }

    /**
     * Test for System property being ignored if not set - should return file
     * @throws Exception
     */
    public void testGetFileProperty_SystemProperty_NotNull2() throws Exception {
        System.getProperties().remove("my.dir"); // Must clear it
        ResourceBundleManager rbManager = getResourceBundleManager();
        File file = rbManager.getFileProperty("test14.dir");
        assertNotNull("File Property should not be null", file);
        File baseDir = new File(System.getProperty("user.dir"));
        String expected = new File(baseDir, "testfolder").getAbsolutePath();
        expected = expected.replace(File.separatorChar, '/');
        String result = file.getCanonicalPath().replace(File.separatorChar, '/');
        assertEquals("File Property not found", expected, result);
    }

    // ---------------------------------------------------------------------------------------------

    /**
     * Single string
     */
    public void testGetString1() {
        ResourceBundleManager rbManager = getResourceBundleManager();
        assertEquals("getString() is wrong", "Reload Editor", rbManager.getString("APP_NAME"));
    }

    /**
     * Running string
     */
    public void testGetString2() {
        ResourceBundleManager rbManager = getResourceBundleManager();
        assertEquals("getString() is wrong", "One, Two, Three", rbManager.getString("RUNNING"));
    }

    /**
     * Missing resource should throw MissingResourceException
     */
    public void testGetString3() {
        ResourceBundleManager rbManager = getResourceBundleManager();
        try {
            rbManager.getString("BOGUS");
            fail("Should have thrown MissingResourceException");
        }
        catch(MissingResourceException ex) {
            assertTrue(true);
        }
    }

    // ---------------------------------------------------------------------------------------------

    
    /**
     * Test Locale property files are loaded
     */
    public void testGetString_Locale1() {
        // Save existing Locale
        Locale old_locale = Locale.getDefault();

        ResourceBundleManager rbManager = getResourceBundleManager();
        assertEquals("getString() is wrong", "Reload Editor", rbManager.getString("APP_NAME"));

        Locale.setDefault(Locale.FRANCE);
        rbManager = getResourceBundleManager();
        assertEquals("getString() is wrong", "Le Reload Editor Français", rbManager.getString("APP_NAME"));
        
        Locale.setDefault(Locale.GERMANY);
        rbManager = getResourceBundleManager();
        assertEquals("getString() is wrong", "Das Deutsches Reload Editor", rbManager.getString("APP_NAME"));

        // Restore old Locale
        Locale.setDefault(old_locale);
    }

    /**
     * Test Locale property files are loaded
     */
    public void testGetString_Locale2() {
        // Save existing Locale
        Locale old_locale = Locale.getDefault();
        
        ResourceBundleManager rbManager = getResourceBundleManager();
        assertEquals("getString() is wrong", "One, Two, Three", rbManager.getString("RUNNING"));

        Locale.setDefault(Locale.FRANCE);
        rbManager = getResourceBundleManager();
        assertEquals("getString() is wrong", "Un, Deux, Trois", rbManager.getString("RUNNING"));
        
        Locale.setDefault(Locale.GERMANY);
        rbManager = getResourceBundleManager();
        assertEquals("getString() is wrong", "Eins, Zwei, Drei", rbManager.getString("RUNNING"));

        // Restore old Locale
        Locale.setDefault(old_locale);
    }
}
