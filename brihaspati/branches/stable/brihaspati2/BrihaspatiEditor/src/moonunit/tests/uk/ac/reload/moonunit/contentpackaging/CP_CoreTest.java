
package uk.ac.reload.moonunit.contentpackaging;

import java.io.File;

import junit.framework.TestCase;

import org.jdom.Element;
import org.jdom.Namespace;

import uk.ac.reload.jdom.XMLDocument;
import uk.ac.reload.moonunit.metadata.MD_Core;
import uk.ac.reload.testsupport.ITestsGlobals;
import uk.ac.reload.testsupport.cp.CP_Package1;
import uk.ac.reload.testsupport.cp.CP_Package2;


/**
 * Description
 * 
 * @author Phillip Beauvoir
 * @version $Id: CP_CoreTest.java,v 1.1 1998/03/25 20:30:05 ynsingh Exp $
 */
public class CP_CoreTest
extends TestCase
implements ITestsGlobals
{

    CP_Core cpCore;
    
    String pathTestData;
    
    protected void setUp() throws Exception {
        // See if we have a testdata dir set from an Ant script
        pathTestData = System.getProperty("testdata.dir");
        if(pathTestData == null) {
            // Set a default
            pathTestData = "../junit-tests";
        }
    }
    
    private void loadContentPackage1() throws Exception {
        XMLDocument doc = new XMLDocument();
        doc.loadDocument(new File(pathTestData, CP_Package1.fileManifest));
        cpCore = new CP_Core(doc);
    }
    
    private void loadContentPackage2() throws Exception {
        XMLDocument doc = new XMLDocument();
        doc.loadDocument(new File(pathTestData, CP_Package2.fileManifest));
        cpCore = new CP_Core(doc);
    }

    // ---------------------------------------------------------------------------------------------

    public void testGetRootmanifestElement() throws Exception {
        loadContentPackage1();
        Element element = cpCore.getRootManifestElement();
        assertEquals("Manifest Root wrong", CP_Core.MANIFEST, element.getName());
    }

    // ---------------------------------------------------------------------------------------------

    public void testGetReferencedElement1() throws Exception {
        loadContentPackage1();
        // Item -> Resource
        Element element = cpCore.getElementByIdentifier(cpCore.getRootManifestElement(),
                CP_Package1.ITEM1_ID);
        Element refElement = cpCore.getReferencedElement(element);
        assertEquals("Could not get referenced Element", CP_Package1.RESOURCE1_ID,
                refElement.getAttributeValue(CP_Core.IDENTIFIER));
    }
    
    public void testGetReferencedElement2() throws Exception {
        loadContentPackage1();
        // Item -> sub-Manifest
        Element element = cpCore.getElementByIdentifier(cpCore.getRootManifestElement(),
                CP_Package1.ITEM4_ID);
        Element refElement = cpCore.getReferencedElement(element);
        assertEquals("Could not get referenced Element", CP_Package1.SUB_MANIFEST1_ID,
                refElement.getAttributeValue(CP_Core.IDENTIFIER));
    }
        
    public void testGetReferencedElement3() throws Exception {
        loadContentPackage1();
        // Organization -> should not reference anything, should return null
        Element element = cpCore.getElementByIdentifier(cpCore.getRootManifestElement(),
                CP_Package1.ORG1_ID);
        Element refElement = cpCore.getReferencedElement(element);
        assertNull("Referenced Element should be null", refElement);
    }
    
    // ---------------------------------------------------------------------------------------------

    public void testIsReferencingElement1() throws Exception {
        loadContentPackage1();
        // Item - true
        Element element = cpCore.getElementByIdentifier(cpCore.getRootManifestElement(),
                CP_Package1.ITEM1_ID);
        assertTrue("Should be referencing element", cpCore.isReferencingElement(element));
    }
    
    public void testIsReferencingElement2() throws Exception {
        loadContentPackage1();
        // Organization - false
        Element element = cpCore.getElementByIdentifier(cpCore.getRootManifestElement(),
                CP_Package1.ORG1_ID);
        assertFalse("Should not be referencing element", cpCore.isReferencingElement(element));
    }
    
    public void testIsReferencingElement3() throws Exception {
        loadContentPackage1();
        // Dependency Element - true
        Element element = cpCore.getElementByIdentifier(cpCore.getRootManifestElement(),
                CP_Package1.RESOURCE1_ID);
        element = element.getChild(CP_Core.DEPENDENCY, element.getNamespace());
        assertTrue("Should be referencing element", cpCore.isReferencingElement(element));
    }

    // ---------------------------------------------------------------------------------------------

    public void testGetReferencedIdentifersAllowed1() throws Exception {
        loadContentPackage1();
        // Item element - should be OK
        Element element = cpCore.getElementByIdentifier(cpCore.getRootManifestElement(),
                CP_Package1.ITEM1_ID);
        
        String[] ids = cpCore.getReferencedIdentifersAllowed(element);
        assertTrue("List should not be empty", ids.length > 0);
        for(int i = 0; i < ids.length; i++) {
            Element ref_element = cpCore.getElementByIdentifier(cpCore.getRootManifestElement(), ids[i]);
            String name = ref_element.getName();
            assertTrue("Referenced Element should be Resource or sub-Manifest",
                    name.equals(CP_Core.RESOURCE) || name.equals(CP_Core.MANIFEST));
        }
    }

    public void testGetReferencedIdentifersAllowed2() throws Exception {
        loadContentPackage1();
        // Dependency element - should only reference Resources
        Element element = cpCore.getElementByIdentifier(cpCore.getRootManifestElement(),
                CP_Package1.RESOURCE1_ID);
        element = element.getChild(CP_Core.DEPENDENCY, element.getNamespace());
        String[] ids = cpCore.getReferencedIdentifersAllowed(element);
        assertTrue("List should not be empty", ids.length > 0);
        for(int i = 0; i < ids.length; i++) {
            Element ref_element = cpCore.getElementByIdentifier(cpCore.getRootManifestElement(), ids[i]);
            String name = ref_element.getName();
            assertEquals("Referenced Element should be Resource", CP_Core.RESOURCE, name);
        }
    }
        
    public void testGetReferencedIdentifersAllowed3() throws Exception {
        loadContentPackage1();
        // Organization - should be empty array
        Element element = cpCore.getElementByIdentifier(cpCore.getRootManifestElement(),
                CP_Package1.ORG1_ID);
        String[] ids = cpCore.getReferencedIdentifersAllowed(element);
        assertEquals("Referenced Elements should be Empty", 0, ids.length);
    }
    
    /**
     * <dependency> element should not include parent <resource> in list
     * @throws Exception
     */
    public void testGetReferencedIdentifersAllowed4() throws Exception {
        loadContentPackage1();
        // Dependency element - should only reference Resources
        Element resourceElement = cpCore.getElementByIdentifier(cpCore.getRootManifestElement(),
                CP_Package1.RESOURCE1_ID);
        Element dependencyElement = resourceElement.getChild(CP_Core.DEPENDENCY, resourceElement.getNamespace());
        String[] ids = cpCore.getReferencedIdentifersAllowed(dependencyElement);
        assertTrue("List should not be empty", ids.length > 0);
        for(int i = 0; i < ids.length; i++) {
            Element ref_element = cpCore.getElementByIdentifier(cpCore.getRootManifestElement(), ids[i]);
            assertTrue("Should not contain parent Resource in list", ref_element != resourceElement);
        }
    }

    // ---------------------------------------------------------------------------------------------

    public void testGetReferencedElementsAllowed1() throws Exception {
        loadContentPackage1();
        // Item - should be OK
        Element element = cpCore.getElementByIdentifier(cpCore.getRootManifestElement(),
                CP_Package1.ITEM1_ID);
        
        Element[] elements = cpCore.getReferencedElementsAllowed(element);
        assertTrue("List should not be empty", elements.length > 0);
        for(int i = 0; i < elements.length; i++) {
            String name = elements[i].getName();
            assertTrue("Referenced Element should be Resource or sub-Manifest",
                    name.equals(CP_Core.RESOURCE) || name.equals(CP_Core.MANIFEST));
        }
    }
        
    public void testGetReferencedElementsAllowed2() throws Exception {
        loadContentPackage1();
        // <dependency> element - should only reference Resources
        Element element = cpCore.getElementByIdentifier(cpCore.getRootManifestElement(),
                CP_Package1.RESOURCE1_ID);
        element = element.getChild(CP_Core.DEPENDENCY, element.getNamespace());
        Element[] elements = cpCore.getReferencedElementsAllowed(element);
        assertTrue("List should not be empty", elements.length > 0);
        for(int i = 0; i < elements.length; i++) {
            String name = elements[i].getName();
            assertEquals("Referenced Element should be Resource", CP_Core.RESOURCE, name);
        }
    }
    
    public void testGetReferencedElementsAllowed3() throws Exception {
        loadContentPackage1();
        // Organization - should be empty array
        Element element = cpCore.getElementByIdentifier(cpCore.getRootManifestElement(),
                CP_Package1.ORG1_ID);
        Element[] elements = cpCore.getReferencedElementsAllowed(element);
        assertEquals("Referenced Elements should be Empty", 0, elements.length);
    }
    
    /**
     * <dependency> element should not include parent <resource> in list
     * @throws Exception
     */
    public void testGetReferencedElementsAllowed4() throws Exception {
        loadContentPackage1();
        Element resourceElement = cpCore.getElementByIdentifier(cpCore.getRootManifestElement(),
                CP_Package1.RESOURCE1_ID);
        Element dependencyElement = resourceElement.getChild(CP_Core.DEPENDENCY, resourceElement.getNamespace());
        Element[] elements = cpCore.getReferencedElementsAllowed(dependencyElement);
        assertTrue("List should not be empty", elements.length > 0);
        for(int i = 0; i < elements.length; i++) {
            assertTrue("Should not contain parent Resource in list", elements[i] != resourceElement);
        }
    }
    
    // ---------------------------------------------------------------------------------------------

    public void testGetElementsInManifest1() throws Exception {
        loadContentPackage1();
        // Resources
        Element[] elements = cpCore.getElementsInManifest(cpCore.getRootManifestElement(),
                CP_Core.RESOURCE, cpCore.getRootManifestElement().getNamespace());
        assertTrue("List should not be empty", elements.length > 0);
        for(int i = 0; i < elements.length; i++) {
            Element element = elements[i];
            assertEquals("Element should be a Resource", CP_Core.RESOURCE, element.getName());
        }
    }
    
    public void testGetElementsInManifest2() throws Exception {    
        loadContentPackage1();
        // Organizations
        Element[] elements = cpCore.getElementsInManifest(cpCore.getRootManifestElement(),
                CP_Core.ORGANIZATION, cpCore.getRootManifestElement().getNamespace());
        assertTrue("List should not be empty", elements.length > 0);
        for(int i = 0; i < elements.length; i++) {
            Element element = elements[i];
            assertEquals("Element should be an Organization", CP_Core.ORGANIZATION, element.getName());
        }
    }
        
    public void testGetElementsInManifest3() throws Exception {
        loadContentPackage1();
    	// Bogus
        Element[] elements = cpCore.getElementsInManifest(cpCore.getRootManifestElement(),
                "bogus", cpCore.getRootManifestElement().getNamespace());
        assertEquals("Elements should be Empty", 0, elements.length);
    }
        
    public void testGetElementsInManifest4() throws Exception {
        loadContentPackage1();
        // Metadata
        Element[] elements = cpCore.getElementsInManifest(cpCore.getRootManifestElement(),
                MD_Core.ROOT_NAME, IMSMD_NAMESPACE_122);
        assertTrue("Metadata Elements should be found", elements.length > 0);
    }
    
    // ---------------------------------------------------------------------------------------------

    public void testGetParentManifestElement1() throws Exception {
        loadContentPackage1();
        // Root Manifest
        Element element = cpCore.getElementByIdentifier(cpCore.getRootManifestElement(),
                CP_Package1.RESOURCE1_ID);
        Element manifest = cpCore.getParentManifestElement(element);
        assertEquals("Parent Manifest is wrong", CP_Package1.MANIFEST1_ID,
                manifest.getAttributeValue(CP_Core.IDENTIFIER));
    }
        
    public void testGetParentManifestElement2() throws Exception {
        loadContentPackage1();
        // sub-Manifest
        Element element = cpCore.getElementByIdentifier(cpCore.getRootManifestElement(),
                CP_Package1.SUBMANIFEST_RESOURCE2_ID);
        Element manifest = cpCore.getParentManifestElement(element);
        assertEquals("Parent Manifest is wrong", CP_Package1.SUB_MANIFEST1_ID,
                manifest.getAttributeValue(CP_Core.IDENTIFIER));
        
    }
    
    // ---------------------------------------------------------------------------------------------

    public void testGetElementByIdentifier1() throws Exception {
        loadContentPackage1();
        Element element = cpCore.getElementByIdentifier(cpCore.getRootManifestElement(),
                CP_Package1.ITEM1_ID);
        assertEquals("Could not find Element by Identifier", CP_Package1.ITEM1_ID,
                element.getAttributeValue(CP_Core.IDENTIFIER));
    }
        
    public void testGetElementByIdentifier2() throws Exception {
        loadContentPackage1();
        Element element = cpCore.getElementByIdentifier(cpCore.getRootManifestElement(),
                CP_Package1.ITEM2_ID);
        assertEquals("Could not find Element by Identifier", CP_Package1.ITEM2_ID,
                element.getAttributeValue(CP_Core.IDENTIFIER));
    }
        
    public void testGetElementByIdentifier3() throws Exception {
        loadContentPackage1();
        Element element = cpCore.getElementByIdentifier(cpCore.getRootManifestElement(),
                CP_Package1.SUBMANIFEST_RESOURCE2_ID);
        assertEquals("Could not find Element by Identifier", CP_Package1.SUBMANIFEST_RESOURCE2_ID,
                element.getAttributeValue(CP_Core.IDENTIFIER));
        
    }

    // ---------------------------------------------------------------------------------------------

    public void testGetOrganizations1() throws Exception {
        loadContentPackage1();
        // True 
        Element orgs_element = cpCore.getRootManifestElement().getChild(CP_Core.ORGANIZATIONS,
                cpCore.getRootManifestElement().getNamespace());
        Element[] orgs = cpCore.getOrganizations(orgs_element);
        assertTrue("List should not be empty", orgs.length > 0);
        for(int i = 0; i < orgs.length; i++) {
            Element element = orgs[i];
            assertEquals("Should be an Organization Element", CP_Core.ORGANIZATION, element.getName());
        }      
    }
    
    public void testGetOrganizations2() throws Exception {
        loadContentPackage1();
        // False 
        Element orgs_element = cpCore.getRootManifestElement().getChild(CP_Core.RESOURCES,
                cpCore.getRootManifestElement().getNamespace());
        Element[] orgs = cpCore.getOrganizations(orgs_element);
        assertEquals("Organizations should be Empty", 0, orgs.length);
    }
    
    // ---------------------------------------------------------------------------------------------

    public void testGetOrganizationsAllowed() throws Exception {
        loadContentPackage1();
        Element orgs_element = cpCore.getRootManifestElement().getChild(CP_Core.ORGANIZATIONS,
                cpCore.getRootManifestElement().getNamespace());
        Element[] orgs = cpCore.getOrganizationsAllowed(orgs_element);  
        assertTrue("List should not be empty", orgs.length > 0);
        for(int i = 0; i < orgs.length; i++) {
            Element element = orgs[i];
            String id = element.getAttributeValue(CP_Core.IDENTIFIER);
            assertTrue("Organization should have ID", id != null && !id.equals(""));
            assertEquals("Should be an Organization Element", CP_Core.ORGANIZATION, element.getName());
        }
    }
    
    // ---------------------------------------------------------------------------------------------

    public void testGetDefaultOrganization1() throws Exception {
        loadContentPackage1();
        // With default organization set
        Element orgs_element = cpCore.getRootManifestElement().getChild(CP_Core.ORGANIZATIONS,
                cpCore.getRootManifestElement().getNamespace());
        Element org = cpCore.getDefaultOrganization(orgs_element);  
        assertEquals("Should be default Organization", CP_Package1.ORG1_ID,
                org.getAttributeValue(CP_Core.IDENTIFIER));
    }
    
    public void testGetDefaultOrganization2() throws Exception {
        loadContentPackage1();
        // With NO default organization
        Element orgs_element = cpCore.getRootManifestElement().getChild(CP_Core.ORGANIZATIONS,
                cpCore.getRootManifestElement().getNamespace());
        orgs_element.removeAttribute(CP_Core.DEFAULT);
        Element org = cpCore.getDefaultOrganization(orgs_element);  
        assertEquals("Should be default Organization", CP_Package1.ORG1_ID,
                org.getAttributeValue(CP_Core.IDENTIFIER));
    }
    
    // ---------------------------------------------------------------------------------------------

    /**
     * Ensure Relative URL is OK with params appended
     * @throws Exception
     */
    public void testGetRelativeURL_Local() throws Exception {
        loadContentPackage1();
        Element element = cpCore.getElementByIdentifier(cpCore.getRootManifestElement(),
                CP_Package1.ITEM1_ID);
        String url = cpCore.getRelativeURL(element);
        assertEquals("Relative URL not correct", CP_Package1.ITEM1_HREF_WITHPARAMS, url);
    }

    /**
     * Ensure Relative URL is OK with base attribute
     * @throws Exception
     */
    public void testGetRelativeURL_Local_WithBaseAttribute() throws Exception {
        loadContentPackage1();
        Element element = cpCore.getElementByIdentifier(cpCore.getRootManifestElement(),
                CP_Package1.SUBMANIFEST_RESOURCE2_ID);
        String url = cpCore.getRelativeURL(element);
        assertEquals("Relative URL not correct", CP_Package1.SUBMANIFEST_RESOURCE2_HREF, url);
    }

    /**
     * Ensure Relative URL gets "http://" prepended
     * @throws Exception
     */
    public void testGetRelativeURL_WebLink() throws Exception {
        loadContentPackage1();
        Element element = cpCore.getElementByIdentifier(cpCore.getRootManifestElement(),
                CP_Package1.ITEM3_ID);
        String url = cpCore.getRelativeURL(element);
        assertEquals("Relative URL not correct", "http://" + CP_Package1.ITEM3_HREF, url);
    }

    // ---------------------------------------------------------------------------------------------

    /**
     * Ensure Absolute URL is of form file://d:\whatever.txt
     * @throws Exception
     */
    public void testGetAbsoluteURL_Local1() throws Exception {
        loadContentPackage1();
        Element element = cpCore.getElementByIdentifier(cpCore.getRootManifestElement(),
                CP_Package1.ITEM1_ID);
        String url = cpCore.getAbsoluteURL(element);
        assertTrue("Absolute URL does not start with \"file:///\" - " + url, url.startsWith("file:///"));
        assertTrue("Absolute URL does not end with relative URL - " + url, url.endsWith(cpCore.getRelativeURL(element)));
    }

    /**
     * Ensure parameters are preserved if contained in the Resource itself
     * @throws Exception
     */
    public void testGetAbsoluteURL_Local_ParametersInResource() throws Exception {
        loadContentPackage2();
        Element element = cpCore.getElementByIdentifier(cpCore.getRootManifestElement(),
                CP_Package2.ITEM1_ID);
        String url = cpCore.getAbsoluteURL(element);
        assertTrue("Absolute URL does not start with \"file:///\" - " + url, url.startsWith("file:///"));
        assertEquals("Absolute URL wrong", "CMD_5987023_M/my_files/ims_import/ber/intro.html?ponk", cpCore.getRelativeURL(element));
    }

    /**
     * Ensure Absolute URL gets "http://" prepended
     * @throws Exception
     */
    public void testGetAbsoluteURL_WebLink() throws Exception {
        loadContentPackage1();
        Element element = cpCore.getElementByIdentifier(cpCore.getRootManifestElement(),
                CP_Package1.ITEM3_ID);
        String url = cpCore.getAbsoluteURL(element);
        assertEquals("Absolute URL not correct", "http://" + CP_Package1.ITEM3_HREF, url);
    }

    // ---------------------------------------------------------------------------------------------

    public void testGetElementHREF1() throws Exception {
        loadContentPackage1();
        // Local
        Element element = cpCore.getElementByIdentifier(cpCore.getRootManifestElement(),
                CP_Package1.ITEM1_ID);
        String url = cpCore.getElementHREF(element);
        assertEquals("ElementHREF not correct", "zappa/0-intro.html", url);
    }
        
    public void testGetElementHREF2() throws Exception {
        loadContentPackage1();
        // External
        Element element = cpCore.getElementByIdentifier(cpCore.getRootManifestElement(),
                CP_Package1.ITEM3_ID);
        String url = cpCore.getElementHREF(element);
        assertEquals("ElementHREF not correct", CP_Package1.ITEM3_HREF, url);
    }

    public void testGetElementHREF3() throws Exception {
        loadContentPackage1();
        // Backslash converted to forward
        Element element = cpCore.getElementByIdentifier(cpCore.getRootManifestElement(),
                CP_Package1.ITEM5_ID);
        String url = cpCore.getElementHREF(element);
        assertEquals("ElementHREF not correct", "bogus/backslash", url);
    }
        
    public void testGetElementHREF4() throws Exception {
        loadContentPackage1();
        // base attribute prefix
        Element element = cpCore.getElementByIdentifier(cpCore.getRootManifestElement(),
                CP_Package1.SUBMANIFEST_ITEM2_ID);
        String url = cpCore.getElementHREF(element);
        assertEquals("ElementHREF not correct", CP_Package1.SUBMANIFEST_RESOURCE2_HREF, url);
    }
        
    public void testGetElementHREF5() throws Exception {
        loadContentPackage1();
        // External with NO base attribute prefix
        Element element = cpCore.getElementByIdentifier(cpCore.getRootManifestElement(),
                CP_Package1.SUBMANIFEST_ITEM1_ID);
        String url = cpCore.getElementHREF(element);
        assertEquals("ElementHREF not correct", CP_Package1.SUBMANIFEST_RESOURCE1_HREF, url);
    }
    
    // ---------------------------------------------------------------------------------------------

    public void testGetElementBase1() throws Exception {
        loadContentPackage1();
        Element element = cpCore.getElementByIdentifier(cpCore.getRootManifestElement(),
                CP_Package1.SUBMANIFEST_RESOURCE1_ID);
        assertEquals("ElementBase not correct", "submanifest1/", cpCore.getElementBase(element));
    }
        
    public void testGetElementBase2() throws Exception {
        loadContentPackage1();
        // Artificial test 1
        Element root = new Element("root");
        Element child1 = new Element("child1");
        root.addContent(child1);
        Element child2 = new Element("child2");
        child1.addContent(child2);

        child1.setAttribute(CP_Core.BASE, "http://reload.ac.uk/", Namespace.XML_NAMESPACE);
        child2.setAttribute(CP_Core.BASE, "offset/", Namespace.XML_NAMESPACE);
        assertEquals("ElementBase wrong", "http://reload.ac.uk/offset/", cpCore.getElementBase(child2));
    }
        
    public void testGetElementBase3() throws Exception {
        loadContentPackage1();
        // Artificial test 2
        Element root = new Element("root");
        Element child1 = new Element("child1");
        root.addContent(child1);
        Element child2 = new Element("child2");
        child1.addContent(child2);

        child1.setAttribute(CP_Core.BASE, "/top/", Namespace.XML_NAMESPACE);
        child2.setAttribute(CP_Core.BASE, "offset/", Namespace.XML_NAMESPACE);
        assertEquals("ElementBase wrong", "/top/offset/", cpCore.getElementBase(child2));
    }
        
    public void testGetElementBase4() throws Exception {
        loadContentPackage1();
        // Artificial test 3 - no base
        Element root = new Element("root");
        Element child1 = new Element("child1");
        root.addContent(child1);
        Element child2 = new Element("child2");
        child1.addContent(child2);

        child1.removeAttribute(CP_Core.BASE, Namespace.XML_NAMESPACE);
        child2.removeAttribute(CP_Core.BASE, Namespace.XML_NAMESPACE);
        assertNull("ElementBase wrong", cpCore.getElementBase(child2));
    }
    
    // ---------------------------------------------------------------------------------------------

    public void testGetParameters1() throws Exception {
        loadContentPackage1();
        Element element = cpCore.getElementByIdentifier(cpCore.getRootManifestElement(),
                CP_Package1.ITEM1_ID);
        assertEquals("Parameters wrong", "?someParams", cpCore.getParameters(element));
    }
        
    public void testGetParameters2() throws Exception {
        loadContentPackage1();
        // Should be null
        Element element = cpCore.getRootManifestElement();
        assertNull("Parameters should be null", cpCore.getParameters(element));
    }
        
    public void testGetParameters3() throws Exception {
        loadContentPackage1();
        // Prefix a ?
        Element element = new Element("test");
        element.setAttribute(CP_Core.PARAMETERS, "someParams");
        assertEquals("Parameters wrong", "?someParams", cpCore.getParameters(element));
    }

    public void testGetParameters4() throws Exception {
        loadContentPackage1();
        // Check for #
        Element element = new Element("test");
        element.setAttribute(CP_Core.PARAMETERS, "#someParams");
        assertEquals("Parameters wrong", "#someParams", cpCore.getParameters(element));
    }
    
    // ---------------------------------------------------------------------------------------------

    public void testGetResourceFile1() throws Exception {
        loadContentPackage1();
        // File should exist
        Element element = cpCore.getElementByIdentifier(cpCore.getRootManifestElement(),
                CP_Package1.ITEM1_ID);
        assertNotNull("Resource File should exist", cpCore.getResourceFile(element));
    }
        
    public void testGetResourceFile2() throws Exception {
        loadContentPackage1();
        // Bogus Element
        Element element = cpCore.getElementByIdentifier(cpCore.getRootManifestElement(),
                CP_Package1.ORG1_ID);
        assertNull("Resource File should not exist", cpCore.getResourceFile(element));
    }

    public void testGetResourceFile3() throws Exception {
        loadContentPackage1();
        // %20
        Element element = cpCore.getElementByIdentifier(cpCore.getRootManifestElement(),
                CP_Package1.RESOURCE2_ID);
        assertNotNull("Resource File should exist", cpCore.getResourceFile(element));
    }
    
    // ---------------------------------------------------------------------------------------------

    public void testGetResourceFiles1() throws Exception {
        loadContentPackage1();
        // Should not be empty
        Element element = cpCore.getRootManifestElement();
        File[] files = cpCore.getResourceFiles(element);
        assertTrue("File list should not be empty", files.length > 0);
        for(int i = 0; i < files.length; i++) {
            assertTrue("File should exist", files[i].exists());
        }
    }
    
    public void testGetResourceFiles2() throws Exception {    
        loadContentPackage1();
        // Should be empty if using an Item
        Element element = cpCore.getElementByIdentifier(cpCore.getRootManifestElement(),
                CP_Package1.ITEM1_ID);
        File[] files = cpCore.getResourceFiles(element);
        assertEquals("File list should be empty", 0, files.length);
    }
    
    // ---------------------------------------------------------------------------------------------

    public void testGetResourceElementByHREF1() throws Exception {
        loadContentPackage1();
        // Normal
        Element resources = cpCore.getResourcesElement(cpCore.getRootManifestElement());
        Element element = cpCore.getResourceElementByHREF("zappa/0-intro.html", resources);
        assertEquals("Wrong Resource Element", element.getAttributeValue(CP_Core.IDENTIFIER),
                CP_Package1.RESOURCE1_ID);
    }
        
    public void testGetResourceElementByHREF2() throws Exception {
        loadContentPackage1();
        // %20
        Element resources = cpCore.getResourcesElement(cpCore.getRootManifestElement());
        Element element = cpCore.getResourceElementByHREF("Another%20File.txt", resources);
        assertEquals("Wrong Resource Element", element.getAttributeValue(CP_Core.IDENTIFIER),
                CP_Package1.RESOURCE2_ID);
    }

    public void testGetResourceElementByHREF3() throws Exception {
        loadContentPackage1();
        // URL
        Element resources = cpCore.getResourcesElement(cpCore.getRootManifestElement());
        Element element = cpCore.getResourceElementByHREF("www.reload.ac.uk", resources);
        assertEquals("Wrong Resource Element", element.getAttributeValue(CP_Core.IDENTIFIER),
                CP_Package1.RESOURCE3_ID);
    }
     
    public void testGetResourceElementByHREF4() throws Exception {
        loadContentPackage1();
        // Should be Null if not searching on Resources Element
        Element element = cpCore.getResourceElementByHREF("www.reload.ac.uk", cpCore.getRootManifestElement());
        assertNull("Element should be null", element);
    }
    
    // ---------------------------------------------------------------------------------------------

    public void testGetFileElementByHREF1() throws Exception {
        loadContentPackage1();
        // Normal
        Element resource = cpCore.getElementByIdentifier(cpCore.getRootManifestElement(),
                CP_Package1.RESOURCE1_ID);
        String href = "zappa/assets/bkgrd.jpg";
        Element element = cpCore.getFileElementByHREF(href, resource);
        assertEquals("Wrong File Element", CP_Core.FILE, element.getName());
        assertEquals("Wrong File Element", href, element.getAttributeValue(CP_Core.HREF));
    }
     
    public void testGetFileElementByHREF2() throws Exception {
        loadContentPackage1();
        // %20
        Element resource = cpCore.getElementByIdentifier(cpCore.getRootManifestElement(),
                CP_Package1.RESOURCE2_ID);
        String href = "Another%20File.txt";
        Element element = cpCore.getFileElementByHREF(href, resource);
        assertEquals("Wrong File Element", CP_Core.FILE, element.getName());
        assertEquals("Wrong File Element", href, element.getAttributeValue(CP_Core.HREF));
	}

    public void testGetFileElementByHREF3() throws Exception {
        loadContentPackage1();
        // Should be Null if not searching on Resource Element
        String href = "Another%20File.txt";
        Element element = cpCore.getFileElementByHREF(href, cpCore.getRootManifestElement());
        assertNull("Element should be null", element);
    }
    
    // ---------------------------------------------------------------------------------------------

    public void testGetResourcesElement1() throws Exception {
        loadContentPackage1();
        // Root manifest
        Element manifest = cpCore.getRootManifestElement();
        Element element = cpCore.getResourcesElement(manifest);
        assertEquals("Wrong Resources Element", CP_Core.RESOURCES, element.getName());
        assertEquals("Wrong Resources Element", CP_Package1.MANIFEST1_ID,
                manifest.getAttributeValue(CP_Core.IDENTIFIER));
    }

    public void testGetResourcesElement2() throws Exception {
        loadContentPackage1();
        // sub-manifest
        Element manifest = cpCore.getElementByIdentifier(cpCore.getRootManifestElement(),
                CP_Package1.SUB_MANIFEST1_ID);
        Element element = cpCore.getResourcesElement(manifest);
        assertEquals("Wrong Resources Element", CP_Core.RESOURCES, element.getName());
        assertEquals("Wrong Resources Element", CP_Package1.SUB_MANIFEST1_ID,
                manifest.getAttributeValue(CP_Core.IDENTIFIER));
    }
    
    // ---------------------------------------------------------------------------------------------

}
