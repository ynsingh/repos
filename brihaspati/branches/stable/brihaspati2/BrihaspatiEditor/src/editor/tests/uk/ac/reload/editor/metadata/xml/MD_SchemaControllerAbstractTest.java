
package uk.ac.reload.editor.metadata.xml;

import javax.swing.Icon;

import org.jdom.Namespace;

import uk.ac.reload.editor.metadata.xml.MD_SchemaController;
import uk.ac.reload.moonunit.ProfiledSchemaControllerAbstractTest;
import uk.ac.reload.moonunit.schema.SchemaElement;



/**
 * MD_SchemaControllerAbstractTest
 * 
 * @author Phillip Beauvoir
 * @version $Id: MD_SchemaControllerAbstractTest.java,v 1.1 1998/03/26 15:50:37 ynsingh Exp $
 */
public abstract class MD_SchemaControllerAbstractTest extends ProfiledSchemaControllerAbstractTest {

    protected void setUp() throws Exception {
		// Set Properties file to test properties
		System.setProperty("editor.properties.file", "uk.ac.reload.editor.testproperties.rb");
    }

    // ---------------------------------------------------------------------------------------------
    
    public void testGetTopLevelElements() {
        MD_SchemaController mdsc = (MD_SchemaController)sc;
        SchemaElement[] schemaElements = mdsc.getTopLevelElements();
        assertNotNull("Null TopLevelElements", schemaElements);
        assertEquals("Wrong number of TopLevelElements", 9, schemaElements.length);
    }

    // ---------------------------------------------------------------------------------------------
    
    public Icon itestGetLeafIcon(String name, Namespace ns) {
        MD_SchemaController mdsc = (MD_SchemaController)sc;
        Icon icon = mdsc.getLeafIcon(name, ns);
        assertNotNull("Icon null", icon);
        return icon;
    }
    
    // ---------------------------------------------------------------------------------------------
    
    public Icon itestGetOpenIcon(String name, Namespace ns) {
        MD_SchemaController mdsc = (MD_SchemaController)sc;
        Icon icon = mdsc.getOpenIcon(name, ns);
        assertNotNull("Icon null", icon);
        return icon;
    }
    
    // ---------------------------------------------------------------------------------------------
    
    public Icon itestGetClosedIcon(String name, Namespace ns) {
        MD_SchemaController mdsc = (MD_SchemaController)sc;
        Icon icon = mdsc.getClosedIcon(name, ns);
        assertNotNull("Icon null", icon);
        return icon;
    }

    // ---------------------------------------------------------------------------------------------
    
}
