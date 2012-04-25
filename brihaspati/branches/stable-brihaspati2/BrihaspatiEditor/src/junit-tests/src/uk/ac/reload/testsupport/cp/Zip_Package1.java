
package uk.ac.reload.testsupport.cp;



/**
 * Data in the Zipped Content Package
 * 
 * @author Phillip Beauvoir
 * @version $Id: Zip_Package1.java,v 1.1 1998/03/26 16:06:29 ynsingh Exp $
 */
public class Zip_Package1 {

    /**
     * Test Zip File - the path is relative to the unit test
     */
    public static String fileZip = "testdata/cp/package1.zip";
    
    /**
     * The files in the zip file, sorted, and their file sizes in bytes
     */
    public static final String[] files = {
            "A File.txt", "6",
            "Another File.txt", "6",
            "ims_xml.xsd", "1104",
            "imscp_v1p1.xsd", "16736",
            "imsmanifest.xml", "24345",
            "imsmd_v1p2p2.xsd", "24229",
            "space dir/New Text Document.txt", "6",
            "space dir/aFile.txt", "14",
            "submanifest1/ball-ramp.dcr", "184500",
            "submanifest1/ball_ramp.htm", "1043",
            "submanifest1/ims_xml.xsd", "1104",
            "submanifest1/imscp_v1p1.xsd", "16736",
            "submanifest1/imsmanifest.xml", "2108",
            "submanifest1/imsmd_v1p2p2.xsd", "24229",
            "zappa/0-intro.html", "2184",
            "zappa/1-freak_out.html", "1714",
            "zappa/2-ab_free.html", "1347",
            "zappa/3-money.html", "1822",
            "zappa/4-lumpy.html", "2168",
            "zappa/5-ruben.html", "1346",
            "zappa/6-unclemeat.html", "2830",
            "zappa/7-hot_rats.html", "958",
            "zappa/assets/001.gif", "4551",
            "zappa/assets/002.gif", "4063",
            "zappa/assets/003.gif", "6868",
            "zappa/assets/004.gif", "4346",
            "zappa/assets/005.gif", "6646",
            "zappa/assets/006.gif", "7369",
            "zappa/assets/007.gif", "5413",
            "zappa/assets/bkgrd.jpg", "21557",
            "zappa/assets/zappa.jpg", "15379",
    };

}
