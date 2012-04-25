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

package uk.ac.reload.moonunit;

import java.io.File;
import java.io.IOException;
import java.util.StringTokenizer;

import uk.ac.reload.jdom.XMLPath;
import uk.ac.reload.moonunit.schema.SchemaAttribute;
import uk.ac.reload.moonunit.schema.SchemaElement;
import uk.ac.reload.moonunit.schema.SchemaException;
import uk.ac.reload.moonunit.schema.SchemaModel;
import uk.ac.reload.moonunit.schema.SchemaNode;
import uk.ac.reload.moonunit.vocab.Vocabulary;
import uk.ac.reload.moonunit.vocab.VocabularyList;


/**
 * The Schema Controller - this controls access to helper and vocabulary files
 * as well as various default values.
 *
 * @author Phillip Beauvoir
 * @version $Id: SchemaController.java,v 1.1 1998/03/25 20:28:22 ynsingh Exp $
 */
public abstract class SchemaController {

    /**
     * Schema Model
     */
    private SchemaModel _schemaModel;
    
    /**
     * Vocabulary
     */
    private Vocabulary _vocab;
    
    /**
     * SchemaHelper
     */
    private SchemaHelper _schemaHelper;

    /**
     * Default Constructor.
     * It's up to the caller to call loadSchemaModel() or setSchemaModel()
     */
    protected SchemaController() { 
    }

    /**
     * Constructor with known SchemaModel
     * @param schemaModel
     */
    protected SchemaController(SchemaModel schemaModel) { 
        setSchemaModel(schemaModel);
    }
    
    /**
     * Constructor with known SchemaModel, Vocabulary and SchemaHelper
     * @param schemaModel
     * @param vocab
     * @param schemaHelper
     */
    protected SchemaController(SchemaModel schemaModel, Vocabulary vocab, SchemaHelper schemaHelper) {
        setSchemaModel(schemaModel);
        setVocabulary(vocab);
        setSchemaHelper(schemaHelper);
    }
    
    /**
     * Load in the SchemaModel as determined by getSchemaFile() and getRootElementName().
     * If the SchemaModel has already been set and loaded, nothing happens.
     * @throws SchemaException
     * @throws IOException
     */
    public void loadSchemaModel() throws SchemaException, IOException {
        if(getSchemaModel() == null) {
            // Get cached version
            _schemaModel = SchemaModel.getSchemaModel(getVersion(), getSchemaFile(), getRootElementName());
        }
    }

	/**
	 * Add a sub-Schema and its Namespace and prefix to this one.
	 * 
	 * @param subSchemaController The sub-SchemaController to attach
	 * @param nsPrefix The sub-Schema's namespace prefix
	 * @throws SchemaException
	 */
	public void addImportedSchema(SchemaController subSchemaController, String nsPrefix) throws SchemaException {
	    getSchemaModel().addImportedSchema(subSchemaController.getSchemaModel(), nsPrefix);
	}
	
	/**
	 * Attach an element declaration from a sub-Schema to this one at a given point in this Schema.
	 * This assumes that the sub-Schema has already been attached by calling addImportedSchema()
	 * 
	 * @param subSchemaController The sub-SchemaController to attach
	 * @param complexTypeName The Name of this Schema's ComplexType to attach the child to
	 * @param elementName The name of the element in the attached Schema to add
	 * @throws SchemaException
	 */
	public void attachSchemaElement(SchemaController subSchemaController, String complexTypeName, String elementName) throws SchemaException {
	    getSchemaModel().attachSchemaElement(subSchemaController.getSchemaModel(), complexTypeName, elementName);
	}

	/**
     * Set the Default Language for the Language vocabulary
     */
    public void setDefaultVocabLanguage(String lang) {
        Vocabulary vocab = getVocabulary();
        if(vocab != null) {
            vocab.setDefaultLanguage(lang);
        }
    }

	/**
	 * Find out if the given Element is a top level one below the root level
	 * @param schemaElement The Schema Element to query
	 * @return true if the parent of schemaElement is the root Element
	 */
	public boolean isTopLevelElement(SchemaElement schemaElement) {
		return schemaElement.getParent() == getSchemaModel().getRootElement();
	}

	/**
     * For each JDOM Path there is a corresponding
     * SchemaNode.  This method finds that corresponding
     * SchemaNode in this Schema and returns it or null if not found.
     * @param xmlPath The Path to find the SchemaElement for.
     * @return The SchemaNode or null if not found
     */
    public SchemaNode getSchemaNode(XMLPath xmlPath) {
        // Get root Schema Element
        SchemaElement schemaElement = getSchemaModel().getRootElement();
        
        // Now enumerate thru the XMLPath's elements to drill into the SchemaModel
        // to find the correct SchemaElement point in the SchemaModel
        StringTokenizer t = xmlPath.getElements();
        while(t.hasMoreElements()) {
            // Get next Element name
            String name = t.nextToken();
            // If schemaElement is not root because we are at the top level
            if(!name.equals(getSchemaModel().getRootElementName())) {
                schemaElement = schemaElement.getChild(name);
                if(schemaElement == null) {
                    return null; // Oops!
                }
            }
        }

        // If this path represents an Attribute, then get that from the parent SchemaElement
        // and return a SchemaAttribute
        if(xmlPath.isAttribute()) {
            String attName = xmlPath.getAttributePart();
            SchemaAttribute schemaAttribute = schemaElement.getSchemaAttribute(attName);
            if(schemaAttribute != null) {
                return schemaAttribute;
            }
        }

        return schemaElement;
    }

    /**
     * @return a Helper value for a given key
     */
    public String getElementHelperValue(XMLPath xmlPath, String helperKey) {
        SchemaHelper helper = getSchemaHelper();
        return helper == null ? null : helper.getHelperValue(xmlPath, helperKey);
    }

    /**
     * @return a Friendly name
     */
    public String getElementFriendlyName(XMLPath xmlPath) {
        return getElementHelperValue(xmlPath, SchemaHelper.FNAME);
    }

    /**
     * @return a Tip
     */
    public String getElementTip(XMLPath xmlPath) {
        return getElementHelperValue(xmlPath, SchemaHelper.TIP);
    }

    /**
     * @return the widget type or null if not set
     */
    public String getWidgetType(XMLPath xmlPath) {
        return getElementHelperValue(xmlPath, SchemaHelper.WIDGET);
    }

    /**
     * Get the Vocabulary list for a given element or attribute.
     * We first of all look to see if we have a Helper Vocabulary defined.
     * If not, look for one as defined in the Schema.
     * @param schemaNode The Element or Attribute for which to get the vocab list
     * @return The Vocabulary List or null if no list present
     */
    public VocabularyList getVocabularyList(SchemaNode schemaNode) {
        if(schemaNode == null) return null;

        VocabularyList vList = null;

        // Check for Helper list first
        Vocabulary vocab = getVocabulary();
        if(vocab != null) {
            vList = vocab.getVocabularyList(schemaNode);
        }

        // Now check for internal Schema
        if(vList == null) {
            vList = schemaNode.getVocabularyList();
        }

        return vList;
    }

    /**
     * Get the default value of a SchemaNode
     * @param schemaNode
     * @return The default value or null
     */
    public String getDefaultValue(SchemaNode schemaNode) {
        String def = null;

        // Check for helper value first in a (possible) vocabulary list
        VocabularyList vList = getVocabularyList(schemaNode);
        if(vList != null) {
            def = vList.getDefaultValue();
        }

        // Not found, so check for Schema
        if(def == null) {
            def = schemaNode.getDefaultValue();
        }

        return def;
    }

    /**
     * @return a Facet value of an Element or attribute.  Will look in the Helper file
     * first and then the Schema.  The Facet has to be a valid Schema type Facet.
     */
    public String getFacetValue(SchemaNode schemaNode, String facetName) {
        // Try Helper file first
        String value = getElementHelperValue(schemaNode.getXMLPath(), facetName);
        // Try Schema
        if(value == null) {
            value = schemaNode.getFacetValue(facetName);
        }
        return value;
    }

    /**
     * Set the Vocabulary
     * @param vocab
     */
    public void setVocabulary(Vocabulary vocab) {
        _vocab = vocab;
    }
    
	/**
     * @return The Vocabulary
     */
    public Vocabulary getVocabulary() {
        return _vocab;
    }

    /**
     * Set the SchemaHelper
     * @param schemaHelper
     */
    public void setSchemaHelper(SchemaHelper schemaHelper) {
        _schemaHelper = schemaHelper;
    }

    /**
     * @return The Schema Helper
     */
    public SchemaHelper getSchemaHelper() {
        return _schemaHelper;
    }

    /**
     * Set the SchemaModel
     * @param schemaModel
     */
    public void setSchemaModel(SchemaModel schemaModel) {
        _schemaModel = schemaModel;
    }
    
    /**
     * @return the SchemaModel connected with this Controller
     */
    public SchemaModel getSchemaModel() {
        return _schemaModel;
    }

    // =========================================================================
    // ============================ ABSTRACTIONS ===============================
    // =========================================================================

    /**
	 * @return The Version name of this Schema Controller
	 */
	public abstract String getVersion();

	/**
     * @return The root element name
     */
    public abstract String getRootElementName();

    /**
     * @return the Schema File for this Schema
     */
    public abstract File getSchemaFile();

}