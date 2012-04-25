package uk.ac.reload.diva.util;

import junit.framework.TestCase;


/**
 * Description
 *
 * @author Phillip Beauvoir
 * @version $Id: RandomGUIDTest.java,v 1.1 1998/03/25 20:20:17 ynsingh Exp $
 */
public class RandomGUIDTest extends TestCase {
    
    // ---------------------------------------------------------------------------------------------

    /**
     * GUID is not null and has prefix
     * (null test is implicit in test - JUnit will throw error if referencing null object)
     */
    public void testGetUniqueID() {
        String prefix = "phillipus";
        String guid = RandomGUID.getUniqueID(prefix);
        assertTrue("RandomGUID has incorrect prefix", guid.startsWith(prefix));
    }
    
    // ---------------------------------------------------------------------------------------------

}
