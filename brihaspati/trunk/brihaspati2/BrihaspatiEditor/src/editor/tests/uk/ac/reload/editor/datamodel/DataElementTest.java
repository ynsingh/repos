
package uk.ac.reload.editor.datamodel;

import java.io.File;

import junit.framework.TestCase;

import org.jdom.Element;

import uk.ac.reload.diva.util.FileUtils;
import uk.ac.reload.editor.learningdesign.datamodel.LD_DataModel;
import uk.ac.reload.editor.learningdesign.xml.LearningDesign;
import uk.ac.reload.jdom.XMLPath;
import uk.ac.reload.testsupport.FileSupport;
import uk.ac.reload.testsupport.ld.LD_File2;


/**
 * DataElementTest
 * 
 * @author Phillip Beauvoir
 * @version $Id: DataElementTest.java,v 1.1 1998/03/26 15:50:37 ynsingh Exp $
 */
public class DataElementTest extends TestCase {

    /**
     * The path to the Test Data
     */
    String pathTestData;
    
    protected void setUp() throws Exception {
        // See if we have a testdata dir set from an Ant script
        pathTestData = System.getProperty("testdata.dir");
        if(pathTestData == null) {
            // Set a default
            pathTestData = "../junit-tests";
        }

        // Set Properties file to test properties
		System.setProperty("editor.properties.file", "uk.ac.reload.editor.testproperties.rb");
    }

    protected void tearDown() throws Exception {
        // Clean up
        FileUtils.deleteFolder(FileSupport.getMainTestFolder());
    }

    /**
     * Create a new LD_DataModel
     * @return LD_DataModel
     * @throws Exception
     */
    protected LD_DataModel createLD_DataModel() throws Exception {
        File file = new File(pathTestData, LD_File2.fileLD);
        LearningDesign ld = new LearningDesign(file);
        return new LD_DataModel(ld);
    }

    // ---------------------------------------------------------------------------------------------
    
    public void testGetElement() throws Exception {
        LD_DataModel dataModel = createLD_DataModel();
        LearningDesign ld = dataModel.getLearningDesign();
        
        // Root Element of manifest
        Element ancestor = ld.getRootElement();
        
        // Organizations
        XMLPath xmlPathFragment = new XMLPath("organizations");
        DataElement ldElement = new DataElement(dataModel, ancestor, xmlPathFragment);
        assertEquals("Element is wrong", "organizations", ldElement.getElement().getName());
        
        // Learning Design
        xmlPathFragment = new XMLPath("organizations/imsld:learning-design");
        ldElement = new DataElement(dataModel, ancestor, xmlPathFragment);
        assertEquals("Element is wrong", "learning-design", ldElement.getElement().getName());
        
        // Components
        xmlPathFragment = new XMLPath("organizations/imsld:learning-design/components");
        ldElement = new DataElement(dataModel, ancestor, xmlPathFragment);
        assertEquals("Element is wrong", "components", ldElement.getElement().getName());

        // Title should be null
        xmlPathFragment = new XMLPath("organizations/imsld:learning-design/title");
        ldElement = new DataElement(dataModel, ancestor, xmlPathFragment);
        assertNull("Element should be null", ldElement.getElement());
    }

    // ---------------------------------------------------------------------------------------------
    
    public void testCreateElement() throws Exception {
        LD_DataModel dataModel = createLD_DataModel();
        LearningDesign ld = dataModel.getLearningDesign();
        
        // Root Element of manifest
        Element ancestor = ld.getRootElement();
        
        // Title should be null
        XMLPath xmlPathFragment = new XMLPath("organizations/imsld:learning-design/title");
        DataElement ldElement = new DataElement(dataModel, ancestor, xmlPathFragment);
        assertNull("Element should be null", ldElement.getElement());
        
        // Now create it
        Element element = ldElement.createElement();
        assertEquals("Element not created", "title", element.getName());
       
        // Title should be null
        xmlPathFragment = new XMLPath("organizations/imsld:learning-design/learning-objectives/item/title");
        ldElement = new DataElement(dataModel, ancestor, xmlPathFragment);
        assertNull("Element should be null", ldElement.getElement());
        
        // Now create it
        element = ldElement.createElement();
        assertEquals("Element not created", "title", element.getName());
    }

    // ---------------------------------------------------------------------------------------------
    
    public void testDeleteElement() throws Exception {
        LD_DataModel dataModel = createLD_DataModel();
        LearningDesign ld = dataModel.getLearningDesign();
        
        // Root Element of manifest
        Element ancestor = ld.getRootElement();
        
        // Item should be null
        XMLPath xmlPathFragment = new XMLPath("organizations/imsld:learning-design/prerequisites/item");
        DataElement ldElement = new DataElement(dataModel, ancestor, xmlPathFragment);
        assertNull("Element should be null", ldElement.getElement());
        
        // Now create it
        Element element = ldElement.createElement();
        assertEquals("Element not created", "item", element.getName());
        
        // Now delete it
        ldElement.deleteElement();
        
        // Should be null
        assertNull("Element not deleted", ldElement.getElement());
        
        // Parent should still exist, though
        xmlPathFragment = new XMLPath("organizations/imsld:learning-design/prerequisites");
        ldElement = new DataElement(dataModel, ancestor, xmlPathFragment);
        assertNotNull("Element's parent should exist", ldElement.getElement());
    }
    
    // ---------------------------------------------------------------------------------------------
    
    public void testGetXMLElementPath() throws Exception {
        LD_DataModel dataModel = createLD_DataModel();
        LearningDesign ld = dataModel.getLearningDesign();
        
        Element ancestor = ld.getRootElement().getChild("organizations",
                ld.getRootElement().getNamespace());
        XMLPath xmlPathFragment = new XMLPath("imsld:learning-design/learning-objectives/item/title");
        DataElement ldElement = new DataElement(dataModel, ancestor, xmlPathFragment);
        assertEquals("Wrong XMLPath",
                "manifest/organizations/imsld:learning-design/learning-objectives/item/title", 
                ldElement.getXMLElementPath().getPath());
        
        ancestor = ancestor.getChild("learning-design", ld.getLDNamespace());
        xmlPathFragment = new XMLPath("learning-objectives/item/title");
        ldElement = new DataElement(dataModel, ancestor, xmlPathFragment);
        assertEquals("Wrong XMLPath",
                "manifest/organizations/imsld:learning-design/learning-objectives/item/title", 
                ldElement.getXMLElementPath().getPath());
        
        
        // Test with existing element
        Element element = ld.getLDElement();
        ldElement = new DataElement(dataModel, element);
        assertEquals("Wrong XMLPath",
                "manifest/organizations/imsld:learning-design", 
                ldElement.getXMLElementPath().getPath());

    }

    // ---------------------------------------------------------------------------------------------
}
