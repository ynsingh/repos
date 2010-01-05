
package uk.ac.reload.testsupport.cp;



/**
 * Data in the cp/package1 Test Content Package
 * 
 * @author Phillip Beauvoir
 * @version $Id: CP_Package1.java,v 1.1 1998/03/26 16:06:29 ynsingh Exp $
 */
public class CP_Package1 {

    /**
     * Content Package Folder 
     */
    public static String folderCP = "testdata/cp/package1/";
    
    /**
     * Content Package File imsmanifest.xml
     */
    public static String fileManifest = folderCP + "imsmanifest.xml";
    
    /**
     * HTML Page 0-intro.html
     */
    public static String fileHTMLPageIntro = folderCP + "zappa/0-intro.html";

    /**
     * HTML Page spanish.htm
     */
    public static String fileHTMLPageSpanish = folderCP + "spanish.htm";

    // ---------------------------------------------------------------------------------------------

    // Root Manifest
    public static String MANIFEST1_ID = "MANIFEST-3A0A8AE0-807D-2920-8DBB-F20EC79FEBE8";

    // First Organization
    public static String ORG1_ID = "ORG-399F20CB-A067-CFD4-2900-797F77E6ADE6";
    
    // The First Item in the First Organization
    public static String ITEM1_ID = "ITEM-89907E8C-2BD3-9F12-6239-37F8EEC274DA";
    // Item href with params
    public static String ITEM1_HREF_WITHPARAMS = "zappa/0-intro.html?someParams";

    // Another Item
    public static String ITEM2_ID = "ITEM-F1C76311-7549-FAF8-4DE2-BB7019838091";
    
    // Item pointing to www.reload.ac.uk
    public static String ITEM3_ID = "ITEM-EDC65222-51AC-8D44-8666-5198B4F8953D";
    public static String ITEM3_HREF = "www.reload.ac.uk";
    
    // Item referencing sub-Manifest
    public static String ITEM4_ID = "ITEM-202FC808-355A-B899-9500-8FF6331BEE94";
    
    // Item referencing Resource with backslash
    public static String ITEM5_ID = "ITEM-97E1DD15-0147-B57B-3CCA-62BBAFB9FA7E";

    // First Resource referenced by ITEM1_ID
    public static String RESOURCE1_ID = "RES-EE530B25-8229-93F4-8527-885569008E5F";
    
    // Resource with %20
    public static String RESOURCE2_ID = "RES-94B3C107-75B8-8B03-DE6D-8E53A95A9D8C";

    // Resource with www.reload.ac.uk
    public static String RESOURCE3_ID = "RES-7AA156FF-F062-C94F-9943-9FC0E7ED4D58";

    
    // SubManifest
    public static String SUB_MANIFEST1_ID = "MANIFEST-BDA05D06-A052-9A5F-9F7C-253BFB0F90D8";
    
    // Item in sub-manifest referenceing SUBMANIFEST_RESOURCE1_ID
    public static String SUBMANIFEST_ITEM1_ID = "ITEM-CBBA5246-E3B7-709B-BBFD-C483A0CDA1E0";

    // Item in sub-manifest referenceing SUBMANIFEST_RESOURCE2_ID
    public static String SUBMANIFEST_ITEM2_ID = "ITEM-F88CC028-BC72-2777-F628-0729B1754EC5";

    // Resource in sub-manifest 
    public static String SUBMANIFEST_RESOURCE1_ID = "RES-ED2C9314-1E0D-557E-48C5-4E01FC7C2CA7";
    public static String SUBMANIFEST_RESOURCE1_HREF = "www.bolton.ac.uk";

    // Resource in sub-manifest 
    public static String SUBMANIFEST_RESOURCE2_ID = "RES-ED2C9314-1E0D-557E-48C5-4E01FC7C2CA7";
    public static String SUBMANIFEST_RESOURCE2_HREF = "submanifest1/ball_ramp.htm";

    // ---------------------------------------------------------------------------------------------

}
