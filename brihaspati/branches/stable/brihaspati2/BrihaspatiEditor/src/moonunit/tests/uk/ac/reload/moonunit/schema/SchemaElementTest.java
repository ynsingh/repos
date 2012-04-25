
package uk.ac.reload.moonunit.schema;

import java.io.File;

import org.exolab.castor.xml.schema.ComplexType;
import org.exolab.castor.xml.schema.SimpleType;
import org.exolab.castor.xml.schema.XMLType;
import org.jdom.Namespace;

import uk.ac.reload.moonunit.vocab.VocabularyList;
import uk.ac.reload.testsupport.ITestsGlobals;
import uk.ac.reload.testsupport.Schemas;



/**
 * SchemaElementTest
 * 
 * @author Phillip Beauvoir
 * @version $Id: SchemaElementTest.java,v 1.1 1998/03/25 20:29:30 ynsingh Exp $
 */
public class SchemaElementTest
extends SchemaNodeAbstractTest
implements ITestsGlobals
{

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
    
    /**
     * @return The LD A 1.0 Root Schema Element
     * @throws Exception
     */
    protected SchemaElement getLDA10_SchemaElement() throws Exception {
        File schemaFile = new File(pathTestData, Schemas.FILE_SCHEMA_LDA10_AUTHOR);
        return loadSchemaElement(schemaFile, "learning-design");
    }

    /**
     * @return The LD A 1.0 CP 1.1.3 Root Schema Element
     * @throws Exception
     */
    protected SchemaElement getLDA10_CP113_SchemaElement() throws Exception {
        File cpSchemaFile = new File(pathTestData, Schemas.FILE_SCHEMA_CP113);
        File ldSchemaFile = new File(pathTestData, Schemas.FILE_SCHEMA_LDA10_AUTHOR);
        SchemaModel cpSchemaModel = new SchemaModel(cpSchemaFile, "manifest");
        SchemaModel ldSchemaModel = new SchemaModel(ldSchemaFile, "learning-design");
        
        cpSchemaModel.addImportedSchema(ldSchemaModel, "imsld");
        cpSchemaModel.attachSchemaElement(ldSchemaModel, "organizationsType", "learning-design");
        
        return cpSchemaModel.getRootElement();
    }

    /* 
     * Return something concrete for the abstract test
     * @see uk.ac.reload.moonunit.schema.SchemaNodeAbstractTest#getSchemaNodeImpl()
     */
    public SchemaNode getSchemaNodeImpl() throws Exception {
        // We'll use a MD one for the Abstract Test
        return getMD121_SchemaElement();
    }

    // ---------------------------------------------------------------------------------------------

    public void testGetName() throws Exception {
        SchemaElement schemaElement = getCP113_SchemaElement();
        assertEquals("Name wrong", "manifest", schemaElement.getName());
        SchemaElement child = schemaElement.getChild("resources");
        assertEquals("Name wrong", "resources", child.getName());
    }

    // ---------------------------------------------------------------------------------------------

    public void testGetElementDecl() throws Exception {
        SchemaElement schemaElement = getCP113_SchemaElement();
        assertNotNull("ElementDecl was null", schemaElement.getElementDecl());
    }

    // ---------------------------------------------------------------------------------------------

    public void testGetNamespacePrefix1() throws Exception {
        SchemaElement schemaElement = getCP113_SchemaElement();
        String prefix = schemaElement.getNamespacePrefix();
        assertEquals("NamespacePrefix wrong", "", prefix);
    }

    public void testGetNamespacePrefix2() throws Exception {
        SchemaElement schemaElement = getSCORM12_113_SchemaElement()
        							  .getChild("metadata")
        							  .getChild("location", ADLCP_NAMESPACE_12);
        String prefix = schemaElement.getNamespacePrefix();
        assertEquals("NamespacePrefix wrong", "adlcp", prefix);
    }

    public void testGetNamespacePrefix3() throws Exception {
        SchemaElement schemaElement = getLDA10_CP113_SchemaElement()
        							  .getChild("organizations")
        							  .getChild("learning-design", IMSLD_NAMESPACE_100);
        String prefix = schemaElement.getNamespacePrefix();
        assertEquals("NamespacePrefix wrong", "imsld", prefix);
    }

    public void testGetNamespacePrefix4() throws Exception {
        SchemaElement schemaElement = getLDA10_CP113_SchemaElement()
        							  .getChild("organizations")
        							  .getChild("learning-design", IMSLD_NAMESPACE_100)
        							  .getChild("prerequisites");
        String prefix = schemaElement.getNamespacePrefix();
        assertEquals("NamespacePrefix wrong", "imsld", prefix);
    }

    public void testGetNamespacePrefix5() throws Exception {
        SchemaElement schemaElement = getLDA10_CP113_SchemaElement()
        							  .getChild("organizations");
        String prefix = schemaElement.getNamespacePrefix();
        assertEquals("NamespacePrefix wrong", "", prefix);
    }

    // ---------------------------------------------------------------------------------------------

    public void testGetNamespace1() throws Exception {
        SchemaElement schemaElement = getCP113_SchemaElement();
        Namespace ns = schemaElement.getNamespace();
        assertEquals("Namespace wrong", IMSCP_NAMESPACE_113, ns);
    }

    public void testGetNamespace2() throws Exception {
        SchemaElement schemaElement = getSCORM12_113_SchemaElement()
        							  .getChild("metadata")
		   							  .getChild("location", ADLCP_NAMESPACE_12);
        Namespace ns = schemaElement.getNamespace();
        assertEquals("Namespace wrong", ADLCP_NAMESPACE_12, ns);
    }

    public void testGetNamespace3() throws Exception {
        SchemaElement schemaElement = getLDA10_CP113_SchemaElement()
        							  .getChild("organizations")
		   							  .getChild("learning-design", IMSLD_NAMESPACE_100);
        Namespace ns = schemaElement.getNamespace();
        assertEquals("Namespace wrong", IMSLD_NAMESPACE_100, ns);
    }

    public void testGetNamespace4() throws Exception {
        SchemaElement schemaElement = getLDA10_CP113_SchemaElement()
        							  .getChild("organizations")
		   							  .getChild("learning-design", IMSLD_NAMESPACE_100)
		   							  .getChild("prerequisites");
        Namespace ns = schemaElement.getNamespace();
        assertEquals("Namespace wrong", IMSLD_NAMESPACE_100, ns);
    }

    public void testGetNamespace5() throws Exception {
        SchemaElement schemaElement = getLDA10_CP113_SchemaElement()
        							  .getChild("organizations");
        Namespace ns = schemaElement.getNamespace();
        assertEquals("Namespace wrong", IMSCP_NAMESPACE_113, ns);
    }

    // ---------------------------------------------------------------------------------------------

    public void testIsRoot_True() throws Exception {
        SchemaElement schemaElement = getCP113_SchemaElement();
        assertTrue("Should be Root", schemaElement.isRoot());
    }

    public void testIsRoot_False() throws Exception {
        SchemaElement schemaElement = getCP113_SchemaElement();
        assertFalse("Should not be Root", schemaElement.getChild("metadata").isRoot());
    }

    // ---------------------------------------------------------------------------------------------

    public void testGetXMLPath1() throws Exception {
        SchemaElement schemaElement = getCP113_SchemaElement();
        assertEquals("XMLPath wrong", "manifest", schemaElement.getXMLPath().getPath());
    }

    public void testGetXMLPath2() throws Exception {
        SchemaElement schemaElement = getSCORM12_113_SchemaElement()
        							  .getChild("metadata")
		                              .getChild("location", ADLCP_NAMESPACE_12);
        assertEquals("XMLPath wrong", "manifest/metadata/adlcp:location", schemaElement.getXMLPath().getPath());
    }

    // ---------------------------------------------------------------------------------------------

    public void testGetVocabularyList_Null() throws Exception {
        SchemaElement schemaElement = getCP113_SchemaElement();
        VocabularyList vList = schemaElement.getVocabularyList();
        assertNull("VocabularyList should be null", vList);
    }

    public void testGetVocabularyList_InternalList() throws Exception {
        SchemaElement schemaElement = getSCORM12_113_SchemaElement()
        							  .getChild("organizations")
                                      .getChild("organization")
                                      .getChild("item")
                                      .getChild("timelimitaction", ADLCP_NAMESPACE_12);
        VocabularyList vList = schemaElement.getVocabularyList();
        
        assertEquals("VocabularyList is wrong length", 4, vList.getList().length);
        
        String[] expected = {
                "exit,no message",
                "exit,message",
                "continue,no message",
                "continue,message"
        };
        
        for(int i = 0; i < expected.length; i++) {
            assertEquals("VocabularyList member wrong", expected[i], vList.getList()[i]);
        }
    }

    // ---------------------------------------------------------------------------------------------

    public void testGetTypeName() throws Exception {
        SchemaElement schemaElement = getMD121_SchemaElement()
        							  .getChild("technical")
        							  .getChild("size");
        assertEquals("Returned wrong type name", "sizeType", schemaElement.getTypeName());
    }

    // ---------------------------------------------------------------------------------------------

    public void testGetBaseTypeName() throws Exception {
        SchemaElement schemaElement = getMD121_SchemaElement()
		  						      .getChild("technical")
		  						      .getChild("size");
        assertEquals("Returned wrong base type name", "int", schemaElement.getBaseTypeName());
    }

    // ---------------------------------------------------------------------------------------------

    public void testGetAtomicBaseTypeName() throws Exception {
        SchemaElement schemaElement = getMD121_SchemaElement()
	      							  .getChild("technical")
	      							  .getChild("size");
        assertEquals("Returned wrong base type name", "decimal", schemaElement.getAtomicBaseTypeName());
    }

    // ---------------------------------------------------------------------------------------------

    public void testIsValue1() throws Exception {
        SchemaElement schemaElement = getMD121_SchemaElement();
        assertFalse("SchemaElement should not be valuable", schemaElement.isValue());
        schemaElement = schemaElement.getChild("general");
        assertFalse("SchemaElement should not be valuable", schemaElement.isValue());
        schemaElement = schemaElement.getChild("title");
        assertFalse("SchemaElement should not be valuable", schemaElement.isValue());
        schemaElement = schemaElement.getChild("langstring");
        assertTrue("SchemaElement should be valuable", schemaElement.isValue());
    }

    public void testIsValue2() throws Exception {
        SchemaElement schemaElement = getCP113_SchemaElement();
        assertFalse("SchemaElement should not be valuable", schemaElement.isValue());
        schemaElement = schemaElement.getChild("organizations");
        assertFalse("SchemaElement should not be valuable", schemaElement.isValue());
        schemaElement = schemaElement.getChild("organization");
        assertFalse("SchemaElement should not be valuable", schemaElement.isValue());
        schemaElement = schemaElement.getChild("item");
        assertFalse("SchemaElement should not be valuable", schemaElement.isValue());
        schemaElement = schemaElement.getChild("title");
        assertTrue("SchemaElement should be valuable", schemaElement.isValue());
    }

    // ---------------------------------------------------------------------------------------------

    public void testIsStringType_False() throws Exception {
        SchemaElement schemaElement = getMD121_SchemaElement()
		  							  .getChild("technical")
		                              .getChild("size");
        assertFalse("SchemaElement should not be String", schemaElement.isStringType());
    }

    public void testIsStringType_True() throws Exception {
        SchemaElement schemaElement = getMD121_SchemaElement()
		  							  .getChild("general")
		                              .getChild("title")
		                              .getChild("langstring");
        assertTrue("SchemaElement should be String", schemaElement.isStringType());
    }

    // ---------------------------------------------------------------------------------------------

    public void testGetMinOccurs1() throws Exception {
        SchemaElement schemaElement = getMD121_SchemaElement();
        assertEquals("Min occurs wrong", 1, schemaElement.getMinOccurs());
        schemaElement = schemaElement.getChild("general");
        assertEquals("Min occurs wrong", 0, schemaElement.getMinOccurs());
        schemaElement = schemaElement.getChild("title");
        assertEquals("Min occurs wrong", 0, schemaElement.getMinOccurs());
    }

    /**
     * Test for "choice" types
     */
    public void testGetMinOccurs2() throws Exception {
        SchemaElement schemaElement = getLDA10_SchemaElement();
        
        SchemaElement schemaElement1 = schemaElement
        							   .getChild("components")
        							   .getChild("activities")
        							   .getChild("learning-activity");
        assertEquals("Min occurs wrong", 0, schemaElement1.getMinOccurs());
        
        SchemaElement schemaElement2 = schemaElement
		   							   .getChild("components")
		   							   .getChild("environments")
		   							   .getChild("environment")
		   							   .getChild("learning-object");
        
        assertEquals("Min occurs wrong", 0, schemaElement2.getMinOccurs());

        SchemaElement schemaElement3 = schemaElement
		   							   .getChild("components")
		   							   .getChild("environments")
		   							   .getChild("environment")
		   							   .getChild("service")
		   							   .getChild("send-mail");

        assertEquals("Min occurs wrong", 0, schemaElement3.getMinOccurs());
    }

    // ---------------------------------------------------------------------------------------------

    public void testGetMaxOccurs1() throws Exception {
        SchemaElement schemaElement = getMD121_SchemaElement();
        assertEquals("Min occurs wrong", 1, schemaElement.getMaxOccurs());
        schemaElement = schemaElement.getChild("general");
        assertEquals("Min occurs wrong", 1, schemaElement.getMaxOccurs());
        schemaElement = schemaElement.getChild("title");
        assertEquals("Min occurs wrong", 1, schemaElement.getMaxOccurs());
        schemaElement = schemaElement.getChild("langstring");
        assertEquals("Min occurs wrong", -1, schemaElement.getMaxOccurs());
    }

    /**
     * Test for "choice" types
     */
    public void testGetMaxOccurs2() throws Exception {
        SchemaElement schemaElement = getLDA10_SchemaElement();
        
        SchemaElement schemaElement1 = schemaElement
        							   .getChild("components")
        							   .getChild("activities")
        							   .getChild("learning-activity");
        assertEquals("Min occurs wrong", -1, schemaElement1.getMaxOccurs());
        
        SchemaElement schemaElement2 = schemaElement
		   							   .getChild("components")
		   							   .getChild("environments")
		   							   .getChild("environment")
		   							   .getChild("learning-object");
        
        assertEquals("Min occurs wrong", -1, schemaElement2.getMaxOccurs());
        
        SchemaElement schemaElement3 = schemaElement
		   							   .getChild("components")
		   							   .getChild("environments")
		   							   .getChild("environment")
		   							   .getChild("service")
		   							   .getChild("send-mail");

        assertEquals("Min occurs wrong", 1, schemaElement3.getMaxOccurs());
    }

    // ---------------------------------------------------------------------------------------------

    public void testGetDefaultValue() throws Exception {
        SchemaElement schemaElement = getMD121_SchemaElement();
        assertNull("Default value should be null", schemaElement.getDefaultValue());
        // I don't think we have any test cases that have a default value set...
    }

    // ---------------------------------------------------------------------------------------------

    public void testGetXMLType() throws Exception {
        SchemaElement schemaElement = getMD121_SchemaElement();
        XMLType xmlType = schemaElement.getXMLType();
        assertTrue("XMLType should be ComplexType", xmlType instanceof ComplexType);
        
        schemaElement = schemaElement.getChild("general");
        xmlType = schemaElement.getXMLType();
        assertTrue("XMLType should be ComplexType", xmlType instanceof ComplexType);

        schemaElement = schemaElement.getChild("identifier");
        xmlType = schemaElement.getXMLType();
        assertTrue("XMLType should be SimpleType", xmlType instanceof SimpleType);

    }

    // ---------------------------------------------------------------------------------------------

    public void testIsComplexType_True() throws Exception {
        SchemaElement schemaElement = getMD121_SchemaElement();
        XMLType xmlType = schemaElement.getXMLType();
        assertTrue("XMLType should be ComplexType", xmlType.isComplexType());
    }

    public void testIsComplexType_False() throws Exception {
        SchemaElement schemaElement = getMD121_SchemaElement()
		  							  .getChild("general")
		  							  .getChild("title")
		  							  .getChild("langstring");
        XMLType xmlType = schemaElement.getXMLType();
        assertTrue("XMLType should not be ComplexType", xmlType.isComplexType());
    }

    // ---------------------------------------------------------------------------------------------

    public void testIsChoiceElement_True() throws Exception {
        SchemaElement schemaElement = getLDA10_SchemaElement()
        							  .getChild("components")
        							  .getChild("activities")
        							  .getChild("learning-activity");
        assertTrue("SchemaElement should be Choice", schemaElement.isChoiceElement());
    }

    public void testIsChoiceElement_False() throws Exception {
        SchemaElement schemaElement = getMD121_SchemaElement();
        assertFalse("SchemaElement should not be Choice", schemaElement.isChoiceElement());
    }

    // ---------------------------------------------------------------------------------------------

    public void testIsSequenceElement_True() throws Exception {
        SchemaElement schemaElement = getMD121_SchemaElement()
        						      .getChild("general");
        assertTrue("SchemaElement should be Sequence", schemaElement.isSequenceElement());
    }

    public void testIsSequenceElement_False() throws Exception {
        SchemaElement schemaElement = getMD121_SchemaElement();
        assertFalse("SchemaElement should not be Sequence", schemaElement.isSequenceElement());
    }

    // ---------------------------------------------------------------------------------------------

    public void testGetOrderType1() throws Exception {
        SchemaElement schemaElement = getMD121_SchemaElement();
        assertNull("Wrong Order Type", schemaElement.getOrderType());
        schemaElement = schemaElement.getChild("general");
        assertEquals("Wrong Order Type", "sequence", schemaElement.getOrderType());
    }

    public void testGetOrderType2() throws Exception {
        SchemaElement schemaElement = getLDA10_SchemaElement()
      							      .getChild("components")
      								  .getChild("activities")
      								  .getChild("learning-activity");
        assertEquals("Wrong Order Type", "choice", schemaElement.getOrderType());
    }

    // ---------------------------------------------------------------------------------------------

    public void testGetOrderMaxOccurs() throws Exception {
        SchemaElement schemaElement = getLDA10_SchemaElement()
	      							  .getChild("components")
	      							  .getChild("activities")
	      							  .getChild("learning-activity");
        assertEquals("Wrong Order Max", -1, schemaElement.getOrderMaxOccurs());
    }

    // ---------------------------------------------------------------------------------------------

    public void testGetOrderMinOccurs() throws Exception {
        SchemaElement schemaElement = getLDA10_SchemaElement()
        							  .getChild("components")
        							  .getChild("activities")
        							  .getChild("learning-activity");
        assertEquals("Wrong Order Min", 1, schemaElement.getOrderMinOccurs());
    }

    // ---------------------------------------------------------------------------------------------

    public void testGetFacetValue() throws Exception {     
        SchemaElement schemaElement = getSCORM12_113_SchemaElement()
        							  .getChild("metadata")
        							  .getChild("location", ADLCP_NAMESPACE_12);
        assertEquals("Wrong facet Value", "2000", schemaElement.getFacetValue("maxLength"));
    }

    // ---------------------------------------------------------------------------------------------

    public void testGetSchemaAttributes() throws Exception {
        SchemaElement schemaElement = getCP113_SchemaElement();
        SchemaAttribute[] atts = schemaElement.getSchemaAttributes();
        assertNotNull("Attributes should not be null", atts);
        
        assertEquals("Attributes wrong length", 3, atts.length);

        String[] expected = {
                "base",
                "identifier",
                "version"
        };
        
        for(int i = 0; i < atts.length; i++) {
            assertEquals("Wrong Attribute", expected[i], atts[i].getName());
        }
    }

    // ---------------------------------------------------------------------------------------------

    public void testGetSchemaAttribute_String1() throws Exception {
        SchemaElement schemaElement = getCP113_SchemaElement();
        SchemaAttribute att = schemaElement.getSchemaAttribute("version");
        assertNotNull("Attribute should not be null", att);
        assertEquals("Wrong Attribute", "version", att.getName());
    }

    public void testGetSchemaAttribute_String2() throws Exception {
        SchemaElement schemaElement = getSCORM12_113_SchemaElement()
        							  .getChild("resources")
        							  .getChild("resource");
        
        SchemaAttribute att = schemaElement.getSchemaAttribute("adlcp:scormtype");
        assertNotNull("Attribute should not be null", att);
        assertEquals("Wrong Attribute", "scormtype", att.getName());
    }

    // ---------------------------------------------------------------------------------------------

    public void testGetSchemaAttribute_StringNamespace1() throws Exception {
        SchemaElement schemaElement = getSCORM12_113_SchemaElement();
        
        SchemaAttribute att = schemaElement.getSchemaAttribute("identifier", Namespace.NO_NAMESPACE);
        assertNotNull("Attribute should not be null", att);
        assertEquals("Wrong Attribute", "identifier", att.getName());
    }

    public void testGetSchemaAttribute_StringNamespace2() throws Exception {
        SchemaElement schemaElement = getSCORM12_113_SchemaElement();
        
        SchemaAttribute att = schemaElement.getSchemaAttribute("base", Namespace.XML_NAMESPACE);
        assertNotNull("Attribute should not be null", att);
        assertEquals("Wrong Attribute", "base", att.getName());
        assertEquals("Wrong Namespace", Namespace.XML_NAMESPACE, att.getNamespace());
    }

    public void testGetSchemaAttribute_StringNamespace3() throws Exception {
        SchemaElement schemaElement = getSCORM12_113_SchemaElement()
        							  .getChild("resources")
        							  .getChild("resource");
        
        SchemaAttribute att = schemaElement.getSchemaAttribute("scormtype", ADLCP_NAMESPACE_12);
        assertNotNull("Attribute should not be null", att);
        assertEquals("Wrong Attribute", "scormtype", att.getName());
    }

    // ---------------------------------------------------------------------------------------------

    public void testGetSchemaAttributeAtIndex() throws Exception {
        SchemaElement schemaElement = getCP113_SchemaElement();
        String[] expected = {
                "base",
                "identifier",
                "version"
        };
        
        for(int i = 0; i < expected.length; i++) {
            assertEquals("Wrong Attribute", expected[i], schemaElement.getSchemaAttributeAtIndex(i).getName());
        }
    }

    // ---------------------------------------------------------------------------------------------

    public void testHasSchemaAttributes_True() throws Exception {
        SchemaElement schemaElement = getCP113_SchemaElement();
        assertTrue("SchemaElement should have attributes", schemaElement.hasSchemaAttributes());
    }

    public void testHasSchemaAttributes_False() throws Exception {
        SchemaElement schemaElement = getCP113_SchemaElement()
        							  .getChild("metadata");
        assertFalse("SchemaElement should not have attributes", schemaElement.hasSchemaAttributes());
    }

    // ---------------------------------------------------------------------------------------------

    public void testHasSchemaAttribute_True() throws Exception {
        SchemaElement schemaElement = getCP113_SchemaElement();
        assertTrue("SchemaElement should have attribute",
                schemaElement.hasSchemaAttribute("identifier"));
    }

    public void testHasSchemaAttribute_False() throws Exception {
        SchemaElement schemaElement = getCP113_SchemaElement();
        assertFalse("SchemaElement should not have attribute",
                schemaElement.hasSchemaAttribute("base"));
    }

    // ---------------------------------------------------------------------------------------------

    public void testHasSchemaAttribute_Namespace_True1() throws Exception {
        SchemaElement schemaElement = getCP113_SchemaElement();
        assertTrue("SchemaElement should have attribute",
                schemaElement.hasSchemaAttribute("base", Namespace.XML_NAMESPACE));
    }

    public void testHasSchemaAttribute_Namespace_True2() throws Exception {
        SchemaElement schemaElement = getCP113_SchemaElement();
        assertTrue("SchemaElement should have attribute",
                schemaElement.hasSchemaAttribute("identifier", Namespace.NO_NAMESPACE));
    }

    public void testHasSchemaAttribute_Namespace_False() throws Exception {
        SchemaElement schemaElement = getCP113_SchemaElement();
        assertFalse("SchemaElement should not have attribute",
                schemaElement.hasSchemaAttribute("bogus", Namespace.NO_NAMESPACE));
    }

    // ---------------------------------------------------------------------------------------------

    public void testIndexofSchemaAttribute_SchemaAttribute() throws Exception {
        SchemaElement schemaElement = getCP113_SchemaElement();

        String[] expected = {
                "xml:base",
                "identifier",
                "version"
        };

        for(int i = 0; i < expected.length; i++) {
            SchemaAttribute att = schemaElement.getSchemaAttribute(expected[i]);
            assertEquals("Wrong Attribute Index", i, schemaElement.indexofSchemaAttribute(att));
        }
    }

    public void testIndexofSchemaAttribute_SchemaAttribute_NotFound() throws Exception {
        // Get Attribute from one SchemaElement
        SchemaElement schemaElement = getCP113_SchemaElement();
        SchemaAttribute att = schemaElement.getSchemaAttribute("identifier");
        assertNotNull("Attribute was null", att);
        
        // Get another SchemaElement
        SchemaElement schemaElement2 = getMD121_SchemaElement();
        
        assertEquals("Wrong Attribute Index", -1, schemaElement2.indexofSchemaAttribute(att));
    }

    // ---------------------------------------------------------------------------------------------

    public void testIndexofSchemaAttribute_String() throws Exception {
        SchemaElement schemaElement = getCP113_SchemaElement();

        assertEquals("Wrong Attribute Index", -1, schemaElement.indexofSchemaAttribute("base"));
        assertEquals("Wrong Attribute Index", 1, schemaElement.indexofSchemaAttribute("identifier"));
        assertEquals("Wrong Attribute Index", 2, schemaElement.indexofSchemaAttribute("version"));
    }

    // ---------------------------------------------------------------------------------------------

    public void testIndexofSchemaAttribute_StringNamespace() throws Exception {
        SchemaElement schemaElement = getCP113_SchemaElement();

        Object[] expected = {
                "base", Namespace.XML_NAMESPACE,
                "identifier", Namespace.NO_NAMESPACE,
                "version", Namespace.NO_NAMESPACE
        };

        for(int i = 0; i < expected.length; i += 2) {
            assertEquals("Wrong Attribute Index", i / 2,
                    schemaElement.indexofSchemaAttribute((String)expected[i], (Namespace)expected[i+1]));
        }
    }

    // ---------------------------------------------------------------------------------------------

    public void testToString() throws Exception {
        SchemaElement schemaElement = getCP113_SchemaElement();
        assertEquals("ToString wrong", "manifest", schemaElement.toString());
    }

    // ---------------------------------------------------------------------------------------------

    public void testGetChildren() throws Exception {
        SchemaElement schemaElement = getCP113_SchemaElement();
        SchemaElement[] children = schemaElement.getChildren();
        assertNotNull("Children should not be null", children);
        
        assertEquals("Children wrong length", 4, children.length);

        String[] expected = {
                "metadata",
                "organizations",
                "resources",
                "manifest"
        };
        
        for(int i = 0; i < children.length; i++) {
            assertEquals("Child wrong", expected[i], children[i].getName());
            assertEquals("Child wrong namespace", IMSCP_NAMESPACE_113, schemaElement.getNamespace());
        }
    }
    
    /**
     * Should never have null children array, but should be just empty
     * @throws Exception
     */
    public void testGetChildren_NeverNull() throws Exception {
        SchemaElement schemaElement = getCP113_SchemaElement()
        						      .getChild("metadata")
        						      .getChild("schema");
        assertNotNull("SchemaElement was null", schemaElement);
        SchemaElement[] children = schemaElement.getChildren();
        assertNotNull("SchemaElement was null", children);
        assertEquals("Children should be zero length array", children.length, 0);
    }

    // ---------------------------------------------------------------------------------------------

    public void testGetChildren_Namespace1() throws Exception {
        SchemaElement schemaElement = getCP113_SchemaElement();
        SchemaElement[] children = schemaElement.getChildren(schemaElement.getNamespace());
        assertNotNull("Children should not be null", children);
        
        assertEquals("Children wrong length", 4, children.length);

        String[] expected = {
                "metadata",
                "organizations",
                "resources",
                "manifest"
        };
        
        for(int i = 0; i < children.length; i++) {
            assertEquals("Child wrong", expected[i], children[i].getName());
            assertEquals("Child wrong namespace", IMSCP_NAMESPACE_113, children[i].getNamespace());
        }
    }

    public void testGetChildren_Namespace2() throws Exception {
        SchemaElement schemaElement = getSCORM12_113_SchemaElement()
        							  .getChild("organizations")
        							  .getChild("organization")
        							  .getChild("item");
        SchemaElement[] children = schemaElement.getChildren(ADLCP_NAMESPACE_12);
        assertNotNull("Children should not be null", children);
        
        assertEquals("Children wrong length", 5, children.length);

        String[] expected = {
                "prerequisites",
                "maxtimeallowed",
                "timelimitaction",
                "datafromlms",
                "masteryscore"
        };
        
        for(int i = 0; i < children.length; i++) {
            assertEquals("Child wrong", expected[i], children[i].getName());
            assertEquals("Child wrong namespace", ADLCP_NAMESPACE_12, children[i].getNamespace());
        }
    }

    // ---------------------------------------------------------------------------------------------

    public void testGetChildCount() throws Exception {
        SchemaElement schemaElement = getCP113_SchemaElement();
        assertEquals("Child count wrong length", 4, schemaElement.getChildCount());
    }

    // ---------------------------------------------------------------------------------------------

    public void testHasChildren() throws Exception {
        SchemaElement schemaElement = getCP113_SchemaElement();
        assertTrue("Child count wrong length", schemaElement.hasChildren());
    }

    // ---------------------------------------------------------------------------------------------

    public void testHasChild_True() throws Exception {
        SchemaElement schemaElement = getCP113_SchemaElement();
        assertTrue("Child should be present", schemaElement.hasChild("resources"));
    }

    public void testHasChild_False() throws Exception {
        SchemaElement schemaElement = getCP113_SchemaElement();
        assertFalse("Child should not be present", schemaElement.hasChild("bogus"));
    }

    // ---------------------------------------------------------------------------------------------

    public void testGetChild_String1() throws Exception {
        SchemaElement schemaElement = getCP113_SchemaElement();
        assertEquals("Child wrong", "resources", schemaElement.getChild("resources").getName());
        assertEquals("Child wrong namespace", IMSCP_NAMESPACE_113, schemaElement.getNamespace());
    }

    public void testGetChild_String2() throws Exception {
        SchemaElement schemaElement = getSCORM12_113_SchemaElement()
		  							  .getChild("organizations")
		  							  .getChild("organization")
		  							  .getChild("item")
                                      .getChild("adlcp:prerequisites");
        assertEquals("Child wrong", "prerequisites", schemaElement.getName());
        assertEquals("Child wrong namespace", ADLCP_NAMESPACE_12, schemaElement.getNamespace());
    }

    public void testGetChild_String3() throws Exception {
        SchemaElement schemaElement = getCP113_SchemaElement();
        assertNull("Child should be null", schemaElement.getChild("bogus:resources"));
    }

    public void testGetChild_String4() throws Exception {
        SchemaElement schemaElement = getSCORM12_113_SchemaElement()
                                      .getChild("organizations")
                                      .getChild("organization")
                                      .getChild("item")
                                      .getChild("item")
                                      .getChild("item")
                                      .getChild("item");
        assertEquals("Child wrong", "item", schemaElement.getName());
        assertEquals("Child wrong namespace", IMSCP_NAMESPACE_113, schemaElement.getNamespace());
    }

    // ---------------------------------------------------------------------------------------------

    public void testGetChild_StringNamespace1() throws Exception {
        SchemaElement schemaElement = getCP113_SchemaElement()
                                      .getChild("resources", IMSCP_NAMESPACE_113);
        assertEquals("Child wrong", "resources", schemaElement.getName());
        assertEquals("Child wrong namespace", IMSCP_NAMESPACE_113, schemaElement.getNamespace());
    }

    public void testGetChild_StringNamespace2() throws Exception {
        SchemaElement schemaElement = getSCORM12_113_SchemaElement()
        							  .getChild("organizations")
        							  .getChild("organization")
        							  .getChild("item")
                                      .getChild("prerequisites", ADLCP_NAMESPACE_12);
        assertEquals("Child wrong", "prerequisites", schemaElement.getName());
        assertEquals("Child wrong namespace", ADLCP_NAMESPACE_12, schemaElement.getNamespace());
    }
    
    public void testGetChild_StringNamespace3() throws Exception {
        SchemaElement schemaElement = getCP113_SchemaElement()
                                      .getChild("resources", null);
        assertNull("Child should be null", schemaElement);
    }

    // ---------------------------------------------------------------------------------------------

    public void testGetChildAt() throws Exception {
        SchemaElement schemaElement = getCP113_SchemaElement();
        
        String[] expected = {
                "metadata",
                "organizations",
                "resources",
                "manifest"
        };
        
        for(int i = 0; i < expected.length; i++) {
            assertEquals("Child wrong", expected[i], schemaElement.getChildAt(i).getName());
            assertEquals("Child wrong namespace", IMSCP_NAMESPACE_113, schemaElement.getChildAt(i).getNamespace());
        }
    }

    // ---------------------------------------------------------------------------------------------

    public void testIndexofChild_SchemaElement() throws Exception {
        SchemaElement schemaElement = getCP113_SchemaElement();
        SchemaElement child  = schemaElement.getChild("resources");
        assertEquals("Child wrong", 2, schemaElement.indexofChild(child));
    }

    public void testIndexofChild_SchemaElement_NotFound() throws Exception {
        SchemaElement schemaElement = getCP113_SchemaElement();
        SchemaElement child  = schemaElement.getChild("bogus");
        assertEquals("Child wrong", -1, schemaElement.indexofChild(child));
    }

    // ---------------------------------------------------------------------------------------------

    public void testIndexofChild_String1() throws Exception {
        SchemaElement schemaElement = getCP113_SchemaElement();

        String[] expected = {
                "metadata",
                "organizations",
                "resources",
                "manifest"
        };
        
        for(int i = 0; i < expected.length; i++) {
            assertEquals("Child wrong", i, schemaElement.indexofChild(expected[i],
                    schemaElement.getNamespace()));
        }
    }

    public void testIndexofChild_String2() throws Exception {
        SchemaElement schemaElement = getSCORM12_113_SchemaElement()
        							  .getChild("organizations")
        							  .getChild("organization")
        							  .getChild("item");
        
        Object[] expected = {
                "title", schemaElement.getNamespace(),
                "item", schemaElement.getNamespace(),
                "metadata", schemaElement.getNamespace(),
                "prerequisites", ADLCP_NAMESPACE_12,
                "maxtimeallowed", ADLCP_NAMESPACE_12,
                "timelimitaction", ADLCP_NAMESPACE_12,
                "datafromlms", ADLCP_NAMESPACE_12,
                "masteryscore", ADLCP_NAMESPACE_12
        };

        for(int i = 0; i < expected.length; i += 2) {
            assertEquals("Child wrong", i /2 , schemaElement.indexofChild((String)expected[i],
                    (Namespace)expected[i + 1]));
        }
    }

    // ---------------------------------------------------------------------------------------------

    public void testGetChildNames() throws Exception {
        SchemaElement schemaElement = getCP113_SchemaElement();

        String[] expected = {
                "metadata",
                "organizations",
                "resources",
                "manifest"
        };
        
        String[] results = schemaElement.getChildNames(); 
        assertEquals("Child count wrong length", 4, results.length);
        
        for(int i = 0; i < expected.length; i++) {
            assertEquals("Child wrong", expected[i], results[i]);
        }
    }

    // ---------------------------------------------------------------------------------------------

    public void testHasAncestor_True() throws Exception {
        SchemaElement schemaElement = getSCORM12_113_SchemaElement()
        							  .getChild("organizations")
        							  .getChild("organization")
        							  .getChild("item");
        SchemaElement child = schemaElement.getChild("item");
        
        assertTrue("SchemaElement should have ancestor", child.hasAncestor(schemaElement));
    }

    public void testHasAncestor_False() throws Exception {
        SchemaElement schemaElement = getSCORM12_113_SchemaElement()
        							  .getChild("organizations")
        							  .getChild("organization")
        							  .getChild("item");
        SchemaElement child = getSCORM12_113_SchemaElement()
        					  .getChild("resources");
        
        assertFalse("SchemaElement should not have ancestor", child.hasAncestor(schemaElement));
    }

    // ---------------------------------------------------------------------------------------------

    /**
     * If root SchemaNode, parent should be null signifying that the parent is the SchemaModel
     */
    public void testGetParent1() throws Exception {
        SchemaElement schemaElement = getCP113_SchemaElement();
        assertNull("Parent should be null", schemaElement.getParent());
    }

    /**
     * Parent should be SchemaElement
     */
    public void testGetParent2() throws Exception {
        SchemaElement schemaElement = getCP113_SchemaElement();
        SchemaElement child = schemaElement.getChild("resources");
        assertTrue("Parent should be a SchemaElement", child.getParent() instanceof SchemaElement);
    }

    // ---------------------------------------------------------------------------------------------

    public void testIsExternalNamespace1() throws Exception {
        SchemaElement schemaElement = getCP113_SchemaElement();
        assertFalse("Namespace should not be external", schemaElement.isExternalNamespace());
    }

    public void testIsExternalNamespace2() throws Exception {
        SchemaElement schemaElement = getSCORM12_113_SchemaElement();
        SchemaElement child = schemaElement.getChild("metadata")
                                           .getChild("location",
                                           ADLCP_NAMESPACE_12);
        assertTrue("Namespace should be external", child.isExternalNamespace());
    }

    // ---------------------------------------------------------------------------------------------

    public void testGetSchemaElements1() throws Exception {
        SchemaElement schemaElement = getSCORM12_113_SchemaElement();
        
        SchemaElement[] schemaElements = schemaElement.getSchemaElements();
        assertNotNull("SchemaElements should not be null", schemaElements);
        assertEquals("SchemaElements wrong length", 1, schemaElements.length);
        assertEquals("SchemaElement should be root", schemaElement, schemaElements[0]);
    }

    public void testGetSchemaElements2() throws Exception {
        SchemaElement schemaElement = getSCORM12_113_SchemaElement()
									  .getChild("organizations")
									  .getChild("organization")
									  .getChild("item")
									  .getChild("timelimitaction", ADLCP_NAMESPACE_12);
        
        SchemaElement[] schemaElements = schemaElement.getSchemaElements();
        assertNotNull("SchemaElements should not be null", schemaElements);
        assertEquals("SchemaElements wrong length", 5, schemaElements.length);

        Object[] expected = {
                "manifest", IMSCP_NAMESPACE_113,
                "organizations", IMSCP_NAMESPACE_113,
                "organization", IMSCP_NAMESPACE_113,
                "item", IMSCP_NAMESPACE_113,
                "timelimitaction", ADLCP_NAMESPACE_12
        };
        
        for(int i = 0; i < schemaElements.length; i += 2) {
            assertEquals("SchemaElement wrong", expected[i], schemaElements[i / 2].getName());
            assertEquals("SchemaElement namespace wrong", expected[i + 1], schemaElements[i / 2].getNamespace());
        }
    }
}
