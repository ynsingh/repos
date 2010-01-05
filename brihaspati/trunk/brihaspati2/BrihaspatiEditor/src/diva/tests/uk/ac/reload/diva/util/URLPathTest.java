package uk.ac.reload.diva.util;

import java.net.MalformedURLException;

import junit.framework.TestCase;


/**
 * Description
 *
 * @author Phillip Beauvoir
 * @version $Id: URLPathTest.java,v 1.1 1998/03/25 20:20:17 ynsingh Exp $
 */
public class URLPathTest extends TestCase {
    
    protected void setUp() throws Exception {

    }
    
    // ---------------------------------------------------------------------------------------------

    /**
     * getURL() is correct
     */
    public void testGetURL() throws MalformedURLException {
        String urlString = "http://www.reload.ac.uk";
        URLPath urlPath = new URLPath(urlString);
        assertEquals("URLPath should be the same", urlString, urlPath.getURL());
    }

    /**
     * getURL() fails - we want it to fail so that we can test that a String is *not* a URL
     */
    public void testURLPath2() {
        String urlString = "adir/afile.txt";
        try {
            new URLPath(urlString);
            // Shouldn't get here
            fail("MalformedURLException should have been thrown");
        } catch(MalformedURLException ex) {
            assertTrue(true);
        }
    }
    
    
    // ---------------------------------------------------------------------------------------------
    
}
