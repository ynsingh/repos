package uk.ac.reload.diva.prefs;

import java.io.File;

import junit.framework.TestCase;


/**
 * Description
 *
 * @author Phillip Beauvoir
 * @version $Id: UserPrefsAbstractTest.java,v 1.1 1998/03/25 20:20:17 ynsingh Exp $
 */
public abstract class UserPrefsAbstractTest extends TestCase {

    // Re-usable test strings
    static String key = "key", value = "value";
    
    /**
     * Implementations of this Test must return a concrete UserPrefs
     * @return a concrete UserPrefs
     */
    public abstract UserPrefs getUserPrefs();
    
    protected UserPrefs prefs;
    
    protected void setUp() throws Exception {
		prefs = getUserPrefs();
    }
    
    // ---------------------------------------------------------------------------------------------

    /**
     * Prefs File 
     */
    public void testGetPrefsFile() {
        File file = prefs.getPrefsFile();
        assertNotNull("Prefs File is null", file);
    }
    
    // ---------------------------------------------------------------------------------------------

    /**
     * Prefs can be saved
     */
    public void testSave() throws Exception {
        prefs.save();
        assertTrue(true);
    }

    // ---------------------------------------------------------------------------------------------

    /**
     * getValue() should be OK
     */
    public void testGetValue() {
        prefs.putValue(key, value);
        assertEquals("getValue not the same as putValue", value, prefs.getValue(key));
    }

    // ---------------------------------------------------------------------------------------------

    /**
     * getBooleanValue() should be OK
     */
    public void testGetBooleanValue1() {
        prefs.putBooleanValue(key, true);
        assertTrue("putBooleanValue not the same as getBooleanValue", prefs.getBooleanValue(key));
    }
    
    public void testGetBooleanValue2() {    
        prefs.putBooleanValue(key, false);
        assertFalse("putBooleanValue not the same as getBooleanValue", prefs.getBooleanValue(key));
    }

    // ---------------------------------------------------------------------------------------------

    /**
     * removeValue() should be OK
     */
    public void testRemoveValue1() {
        prefs.putValue(key, value);
        prefs.removeValue(key);
        assertNull("Value not removed", prefs.getValue(key));
    }

    // ---------------------------------------------------------------------------------------------

}
