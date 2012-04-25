
package uk.ac.reload.moonunit.schema;

import java.io.File;

import org.exolab.castor.xml.schema.Facet;
import org.jdom.Attribute;
import org.jdom.Namespace;

import uk.ac.reload.jdom.XMLPath;
import uk.ac.reload.moonunit.vocab.VocabularyList;
import uk.ac.reload.testsupport.ITestsGlobals;
import uk.ac.reload.testsupport.Schemas;


/**
 * Description
 * 
 * @author Phillip Beauvoir
 * @version $Id: SchemaAttributeTest.java,v 1.1 1998/03/25 20:29:30 ynsingh Exp $
 */
public class SchemaAttributeTest
extends SchemaNodeAbstractTest
implements ITestsGlobals
{

    // ---------------------------------------------------------------------------------------------

    /**
     * Load a SchemaModel and return the root SchemaElement therein
     * @param schemaFile The Schema File location
     * @param rootName The root element name 
     * @return The root SchemaElement
     * @throws Exception
     */
    protected SchemaElement loadSchemaElement(File schemaFile, String rootName) throws Exception {
        SchemaModel schemaModel = new SchemaModel(schemaFile, rootName);
        return schemaModel.getRootElement();
    }

    /**
     * @return The MD 1.2.1 Root Schema Element
     * @throws Exception
     */
    protected SchemaElement getMD121_SchemaElement() throws Exception {
        File schemaFile = new File(pathTestData, Schemas.FILE_SCHEMA_MD121);
        return loadSchemaElement(schemaFile, "lom");
    }

    /**
     * @return The CP 1.1.3 Root Schema Element
     * @throws Exception
     */
    protected SchemaElement getCP113_SchemaElement() throws Exception {
        File schemaFile = new File(pathTestData, Schemas.FILE_SCHEMA_CP113);
        return loadSchemaElement(schemaFile, "manifest");
    }

    /**
     * @return The SCORM 1.2 CP 1.1.3 Root Schema Element
     * @throws Exception
     */
    protected SchemaElement getSCORM12_113_SchemaElement() throws Exception {
        File schemaFile = new File(pathTestData, Schemas.FILE_SCHEMA_SCORM12_IMCP113);
        return loadSchemaElement(schemaFile, "manifest");
    }

    /* 
     * Return something concrete for the abstract test
     * @see uk.ac.reload.moonunit.schema.SchemaNodeAbstractTest#getSchemaNodeImpl()
     */
    public SchemaNode getSchemaNodeImpl() throws Exception {
        // We'll use a CP one for the Abstract Test
        return getCP113_SchemaElement().getSchemaAttribute("identifier");
    }

    // ---------------------------------------------------------------------------------------------

    public void testGetName1() throws Exception {
        SchemaAttribute schemaAttribute = getCP113_SchemaElement().getSchemaAttribute("identifier");
        assertEquals("Wrong Attribute Name", "identifier", schemaAttribute.getName());
    }

    public void testGetName2() throws Exception {
        SchemaAttribute schemaAttribute = getCP113_SchemaElement().getSchemaAttribute("xml:base");
        assertEquals("Wrong Attribute Name", "base", schemaAttribute.getName());
    }

    // ---------------------------------------------------------------------------------------------

    public void testGetAttributeDecl() throws Exception {
        SchemaAttribute schemaAttribute = getCP113_SchemaElement().getSchemaAttribute("identifier");
        assertNotNull("AttributeDecl was null", schemaAttribute.getAttributeDecl());
    }

    // ---------------------------------------------------------------------------------------------

    public void testGetUse1() throws Exception {
        SchemaAttribute schemaAttribute = getCP113_SchemaElement().getSchemaAttribute("xml:base");
        assertEquals("Wrong Attribute Use", "optional", schemaAttribute.getUse());
    }

    public void testGetUse2() throws Exception {
        SchemaAttribute schemaAttribute = getCP113_SchemaElement().getSchemaAttribute("identifier");
        assertEquals("Wrong Attribute Use", "required", schemaAttribute.getUse());
    }

    // ---------------------------------------------------------------------------------------------

    public void testGetDefaultValue() throws Exception {
        SchemaElement schemaElement = getSCORM12_113_SchemaElement()
		  							  .getChild("organizations")
		  							  .getChild("organization");
        SchemaAttribute schemaAttribute = schemaElement.getSchemaAttribute("structure");
        assertEquals("Wrong Default Value", "hierarchical", schemaAttribute.getDefaultValue());
    }

    // ---------------------------------------------------------------------------------------------

    public void testGetNamespacePrefix1() throws Exception {
        SchemaAttribute schemaAttribute = getCP113_SchemaElement().getSchemaAttribute("identifier");
        assertEquals("Wrong Namespace Prefix", "", schemaAttribute.getNamespacePrefix());
    }

    public void testGetNamespacePrefix2() throws Exception {
        SchemaAttribute schemaAttribute = getCP113_SchemaElement().getSchemaAttribute("xml:base");
        assertEquals("Wrong Namespace Prefix", "xml", schemaAttribute.getNamespacePrefix());
    }

    // ---------------------------------------------------------------------------------------------

    public void testGetNamespace1() throws Exception {
        SchemaAttribute schemaAttribute = getCP113_SchemaElement().getSchemaAttribute("identifier");
        assertEquals("Wrong Namespace", Namespace.NO_NAMESPACE, schemaAttribute.getNamespace());
    }

    public void testGetNamespace2() throws Exception {
        SchemaAttribute schemaAttribute = getCP113_SchemaElement().getSchemaAttribute("xml:base");
        assertEquals("Wrong Namespace", Namespace.XML_NAMESPACE, schemaAttribute.getNamespace());
    }

    // ---------------------------------------------------------------------------------------------

    public void testGetVocabularyList() throws Exception {
        SchemaElement schemaElement = getSCORM12_113_SchemaElement()
		  							  .getChild("resources")
		  							  .getChild("resource");

        SchemaAttribute schemaAttribute = schemaElement.getSchemaAttribute("adlcp:scormtype");
        
        VocabularyList vList = schemaAttribute.getVocabularyList();
        
        assertEquals("Vocab wrong length", 2, vList.getList().length);
        
        String[] expected = new String[] {
                "asset",
                "sco",
        };

        for(int i = 0; i < vList.getList().length; i++) {
            assertEquals("Vocab wrong", expected[i], vList.getList()[i]);
        }
    }

    // ---------------------------------------------------------------------------------------------

    public void testGetTypeName() throws Exception {
        SchemaAttribute schemaAttribute = getCP113_SchemaElement().getSchemaAttribute("identifier");
        assertEquals("Wrong TypeName", "ID", schemaAttribute.getTypeName());
    }

    // ---------------------------------------------------------------------------------------------

    public void testGetMaxLength() throws Exception {
        SchemaAttribute schemaAttribute = getCP113_SchemaElement().getSchemaAttribute("identifier");
        assertEquals("Wrong MaxLength", null, schemaAttribute.getMaxLength());
    }

    // ---------------------------------------------------------------------------------------------

    public void testGetMinLength() throws Exception {
        SchemaAttribute schemaAttribute = getCP113_SchemaElement().getSchemaAttribute("identifier");
        assertEquals("Wrong MinLength", null, schemaAttribute.getMinLength());
    }

    // ---------------------------------------------------------------------------------------------

    public void testGetBaseTypeName() throws Exception {
        SchemaAttribute schemaAttribute = getCP113_SchemaElement().getSchemaAttribute("identifier");
        assertEquals("Wrong BaseTypeName", "NCName", schemaAttribute.getBaseTypeName());
    }

    // ---------------------------------------------------------------------------------------------

    public void testGetAtomicBaseTypeName() throws Exception {
        SchemaAttribute schemaAttribute = getCP113_SchemaElement().getSchemaAttribute("identifier");
        assertEquals("Wrong AtomicBaseTypeName", "string", schemaAttribute.getAtomicBaseTypeName());
    }

    // ---------------------------------------------------------------------------------------------

    public void testGetFacetValue() throws Exception {
        SchemaAttribute schemaAttribute = getCP113_SchemaElement().getSchemaAttribute("identifier");
        schemaAttribute.getAttributeDecl().getSimpleType().addFacet(new Facet("ponk", "nothing"));
        assertEquals("Wrong FacetValue", "nothing", schemaAttribute.getFacetValue("ponk"));
    }

    // ---------------------------------------------------------------------------------------------

    public void testCreateAttribute1() throws Exception {
        SchemaAttribute schemaAttribute = getCP113_SchemaElement().getSchemaAttribute("identifier");
        Attribute att = schemaAttribute.createAttribute();
        assertEquals("Wrong Attribute name", "identifier", att.getName());
        assertEquals("Wrong Attribute namespace", Namespace.NO_NAMESPACE, att.getNamespace());
    }

    public void testCreateAttribute2() throws Exception {
        SchemaAttribute schemaAttribute = getCP113_SchemaElement().getSchemaAttribute("xml:base");
        Attribute att = schemaAttribute.createAttribute();
        assertEquals("Wrong Attribute name", "base", att.getName());
        assertEquals("Wrong Attribute namespace", Namespace.XML_NAMESPACE, att.getNamespace());
    }

    public void testCreateAttribute3() throws Exception {
        SchemaAttribute schemaAttribute = getSCORM12_113_SchemaElement()
		  								  .getChild("resources")
		  								  .getChild("resource")
		  								  .getSchemaAttribute("adlcp:scormtype");
        Attribute att = schemaAttribute.createAttribute();
        assertEquals("Wrong Attribute name", "scormtype", att.getName());
        assertEquals("Wrong Attribute namespace", ADLCP_NAMESPACE_12, att.getNamespace());
    }

    // ---------------------------------------------------------------------------------------------

    public void testGetXMLPath1() throws Exception {
        SchemaAttribute schemaAttribute = getCP113_SchemaElement().getSchemaAttribute("identifier");
        XMLPath xmlPath = schemaAttribute.getXMLPath();
        assertEquals("Wrong Attribute name", "manifest@identifier", xmlPath.getPath());
    }

    public void testGetXMLPath2() throws Exception {
        SchemaAttribute schemaAttribute = getCP113_SchemaElement().getSchemaAttribute("xml:base");
        XMLPath xmlPath = schemaAttribute.getXMLPath();
        assertEquals("Wrong Attribute name", "manifest@xml:base", xmlPath.getPath());
    }

    public void testGetXMLPath3() throws Exception {
        SchemaAttribute schemaAttribute = getSCORM12_113_SchemaElement()
        								  .getChild("resources")
        								  .getChild("resource")
        								  .getSchemaAttribute("adlcp:scormtype");
        XMLPath xmlPath = schemaAttribute.getXMLPath();
        assertEquals("Wrong Attribute name", "manifest/resources/resource@adlcp:scormtype", xmlPath.getPath());
    }
}
