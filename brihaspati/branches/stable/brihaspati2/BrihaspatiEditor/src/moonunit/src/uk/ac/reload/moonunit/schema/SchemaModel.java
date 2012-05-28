/**
 *  RELOAD TOOLS
 *
 *  Copyright (c) 2003 Oleg Liber, Bill Olivier, Phillip Beauvoir
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in
 *  all copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 *  Project Management Contact:
 *
 *  Oleg Liber
 *  Bolton Institute of Higher Education
 *  Deane Road
 *  Bolton BL3 5AB
 *  UK
 *
 *  e-mail:   o.liber@bolton.ac.uk
 *
 *
 *  Technical Contact:
 *
 *  Phillip Beauvoir
 *  e-mail:   p.beauvoir@bolton.ac.uk
 *
 *  Web:      http://www.reload.ac.uk
 *
 */

package uk.ac.reload.moonunit.schema;

import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

import org.exolab.castor.xml.schema.ComplexType;
import org.exolab.castor.xml.schema.ElementDecl;
import org.exolab.castor.xml.schema.Group;
import org.exolab.castor.xml.schema.Schema;
import org.exolab.castor.xml.schema.reader.SchemaReader;


/**
 * The SchemaModel Class - a Java representation of an XML Schema. Together with
 * certain XML Schema helper files an XML Schema is loaded from file, parsed and constructed
 * in memory as a hierarchical linked list of SchemaNode objects - SchemaElement
 * and SchemaAttribute.<br><br>
 * 
 * It relies on Castor (www.castor.org) and JDOM (www.jdom.org) to do the donkey work.<br>
 *
 * @author Phillip Beauvoir
 * @version $Id: SchemaModel.java,v 1.1 1998/03/25 20:30:05 ynsingh Exp $
 */
public class SchemaModel {

    /**
     * The Castor Schema that we will query.
     */
    private Schema _castorSchema;

    /**
     * The root SchemaElement
     */
    private SchemaElement _root;

    /**
     * The name of the Root Element
     */
    private String _rootName;
    
    /**
     * The Schema File
     */
    private File _schemaFile;

    /**
     * The following are static instantiations of the various specs.  We could
     * make SchemaModel an abstract class and implement a class for each spec
     * but this would mean doing it for every new spec version.
     *
     * We only want to create these just once for each spec and re-use them
     */
    private static Hashtable _schemaTable = new Hashtable();

    /**
     * Factory method for getting a static re-usable SchemaModel
     * @param version The SchemaController unique version, used as a hash key
     * @param fileSchema The file of the Schema XSD to get
     * @param rootName We need to know the root name element.
     * @return The SchemaModel or null if not found
     * @throws IOException
     * @throws SchemaException
     */
    public static SchemaModel getSchemaModel(String version, File fileSchema, String rootName) throws SchemaException, IOException  {
        // Do we have it already?
        SchemaModel schema = (SchemaModel)_schemaTable.get(version);
        // No, first time creation, create and store in table
        if(schema == null) {
            schema = new SchemaModel(fileSchema, rootName);
            _schemaTable.put(version, schema);
        }
        return schema;
    }

    /**
     * Default Constructor
     */
    public SchemaModel() {
    }

    /**
     * Constructor that automatically loads in the Schema from file
     * @param schemaFile The XML Schema File to read
     * @param rootName The name of the root element in the Schema
     * @throws IOException 
     * @throws SchemaException
     */
    public SchemaModel(File schemaFile, String rootName) throws IOException, SchemaException {
        // Get the schema document
        _castorSchema = loadSchema(schemaFile);

        _schemaFile = schemaFile;
        _rootName = rootName;

        // Get a Root Element
        ElementDecl rootElementDecl = _castorSchema.getElementDecl(_rootName);
        
        if(rootElementDecl == null) {
            throw new SchemaException("Root Element was null for rootname \"" + rootName + "\"");
        }
        
        _root = new SchemaElement(rootElementDecl);
        _root.setSchemaModel(this);
    }

    /**
     * Set the Schema File to load
     * @param file
     */
    public void setSchemaFile(File file) {
        _schemaFile = file;
    }
    
    /**
     * @return Returns the schema File
     */
    public File getSchemaFile() {
        return _schemaFile;
    }

    /**
     * Get the root element
     * @return The root SchemaElement
     */
    public SchemaElement getRootElement() {
        return _root;
    }

    /**
     * Set the Schema root name
     * @param rootName
     */
    public void setRootElementName(String rootName) {
        _rootName = rootName;
    }
    
    /**
     * Get the name of the Root Element in this Schema
     * @return The Root Element Name
     */
    public String getRootElementName() {
        return _rootName;
    }

    /**
     * Get the raw Castor Schema
     * @return The Castor Schema
     */
    public Schema getSchema() {
        return _castorSchema;
    }

	/**
	 * Add a sub-Schema and its Namespace and prefix to this one.
	 * 
	 * @param subSchemaModel The sub-SchemaModel to attach
	 * @param nsPrefix The sub-Schema's namespace prefix
	 * @throws SchemaException
	 */
	public void addImportedSchema(SchemaModel subSchemaModel, String nsPrefix) throws SchemaException {
	    Schema parentSchema = getSchema();
        Schema attachedSchema = subSchemaModel.getSchema();
	    
	    // Try to attach the Schema and Namespace, though can't attach more than once
        if(parentSchema.getImportedSchema(attachedSchema.getTargetNamespace()) == null) {
            try {
                parentSchema.addImportedSchema(attachedSchema);
                parentSchema.addNamespace(nsPrefix, attachedSchema.getTargetNamespace());
            }
            catch(org.exolab.castor.xml.schema.SchemaException ex) {
                ex.printStackTrace();
                throw new SchemaException(ex.getMessage());
            }
        }
	}
	
	/**
	 * Attach an element declaration from a sub-Schema to this one at a given point in this Schema.
	 * This assumes that the sub-Schema has already been attached by calling addImportedSchema()
	 * 
	 * @param subSchemaModel The sub-SchemaModel to attach
	 * @param complexTypeName The Name of this Schema's ComplexType to attach the child to
	 * @param elementName The name of the element in the attached Schema to add
	 * @throws SchemaException
	 */
	public void attachSchemaElement(SchemaModel subSchemaModel, String complexTypeName, String elementName) throws SchemaException {
	    Schema parentSchema = getSchema();
	    Schema attachedSchema = subSchemaModel.getSchema();
	    
	    ComplexType orgsType = parentSchema.getComplexType(complexTypeName);
        Group group = (Group)orgsType.getParticle(0);
        
        ElementDecl ed = group.getElementDecl(elementName);
        // We already have added it
        if(ed != null) {
            return;
        }
        
        ed = new ElementDecl(attachedSchema, elementName);
        ed.setReference(ed);
        
        try {
            group.addElementDecl(ed);
        }
        catch(org.exolab.castor.xml.schema.SchemaException ex) {
            ex.printStackTrace();
            throw new SchemaException(ex.getMessage());
        }
	}

	////////////////////////////////////////////////////////////////////////////
    // SCHEMA ACCESSORS (delegate to Castor)
    ////////////////////////////////////////////////////////////////////////////

    /**
     * Returns the target namespace URI for this Schema, or null if no
     * namespace has been defined.
     * @return the target namespace for this Schema, or null if no
     * namespace has been defined
     */
    public String getTargetNamespaceURI() {
        return _castorSchema.getTargetNamespace();
    }

    /**
     * Returns the namespace URI of the XML Schema
     * Note: This is not the same as targetNamespace. This is the namespace of "XML Schema" itself 
     * at time of writing this is usually - http://www.w3.org/2001/XMLSchema
     * but can be the old one - http://www.w3.org/2000/10/XMLSchema
     * @return the namespace of the XML Schema
     */
    public String getSchemaNamespaceURI() {
        return _castorSchema.getSchemaNamespace();
    }

    /**
     * Returns the full absolute path schemaLocation for a schema given its Namespace URI
     * This is an absolute file path depending on the local machine on which this is run
     * e.g - file:/D:/projekts/reload/cvs/junit-tests/../editor/schema/model/imsmd_121/imsmd_rootv1p2p1.xsd
     * @param namespaceURI Namespace URI for the schema in question
     * @return the schemaLocation for a given Namespace URI
     */
    public String getSchemaLocation(String namespaceURI) {
        Schema schema = _castorSchema.getImportedSchema(namespaceURI);
        return schema == null ? null : schema.getSchemaLocation();
    }

    /**
     * Returns the __short__ schemaLocation for this schema
     * @return the __short__ schemaLocation for this schema
     */
    public String getSchemaName() {
        return getSchemaFile().getName();
    }

    /**
     * Returns the __short__ schemaLocation for this schema given its Namespace URI
     * @param namespaceURI Namespace URI for the schema in question
     * @return the __short__ schemaLocation for a schema given its Namespace URI
     */
    public String getSchemaName(String namespaceURI) {
        String path = getSchemaLocation(namespaceURI);
        return path == null ? null : new File(path).getName();
    }

    /**
     * Returns the namespace URI associated with the given prefix.
     *
     * @param prefix The Namespace prefix to get the URI for.
     * @return the namespace associated with the given prefix, or null if no associated namespace exists.
     */
    public String getNamespaceURI(String prefix) {
        return _castorSchema.getNamespace(prefix);
    }

    /**
     * Returns the version information of the XML Schema definition
     * represented by this Schema instance.
     *
     * @return the version information of the XML Schema definition, or null if no version information exists.
     */
    public String getVersion() {
        return _castorSchema.getVersion();
    }

    /**
     * Load the Castor Schema
     * @param schemaFile The XML Schema File
     * @return The Castor Schema
     * @throws IOException
     */
    protected Schema loadSchema(File schemaFile) throws IOException {
        // We need the URL String, not a file path to work on Mac
        String url = schemaFile.toURL().toString();
        SchemaReader reader = new SchemaReader(url);
        return reader.read();
    }
}