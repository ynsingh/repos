
package uk.ac.reload.moonunit.metadata;

import java.io.File;

import junit.framework.TestCase;
import uk.ac.reload.jdom.XMLDocument;
import uk.ac.reload.testsupport.md.MD_File1;


/**
 * MD_CoreTest
 * 
 * @author Phillip Beauvoir
 * @version $Id: MD_CoreTest.java,v 1.1 1998/03/25 20:29:30 ynsingh Exp $
 */
public class MD_CoreTest extends TestCase {

    MD_Core mdCore;
    
    String pathTestData;
    
    protected void setUp() throws Exception {
        // See if we have a testdata dir set from an Ant script
        pathTestData = System.getProperty("testdata.dir");
        if(pathTestData == null) {
            // Set a default
            pathTestData = "../junit-tests";
        }

        XMLDocument doc = new XMLDocument();
        doc.loadDocument(new File(pathTestData, MD_File1.fileMD));
        mdCore = new MD_Core(doc);
    }
    
    // ---------------------------------------------------------------------------------------------

    public void testROOT_NAME() {
        assertEquals("Document null", "lom", MD_Core.ROOT_NAME);
    }

    // ---------------------------------------------------------------------------------------------
}
