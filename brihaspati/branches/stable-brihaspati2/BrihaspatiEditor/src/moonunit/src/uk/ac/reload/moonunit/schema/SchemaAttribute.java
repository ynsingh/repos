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

import java.util.Enumeration;
import java.util.Vector;

import org.exolab.castor.xml.schema.AttributeDecl;
import org.exolab.castor.xml.schema.Facet;
import org.exolab.castor.xml.schema.SimpleType;
import org.exolab.castor.xml.schema.SimpleTypesFactory;
import org.exolab.castor.xml.schema.XMLType;
import org.jdom.Attribute;
import org.jdom.Namespace;

import uk.ac.reload.jdom.XMLPath;
import uk.ac.reload.moonunit.vocab.VocabularyList;



/**
 * An Attribute node that represents an Attribute of an Element in the Schema.
 *
 * @author Phillip Beauvoir
 * @version $Id: SchemaAttribute.java,v 1.1 1998/03/25 20:30:05 ynsingh Exp $
 */
public class SchemaAttribute
extends SchemaNode
{
    /**
     * The wrapped Castor AttributeDecl that we will interrogate
     */
    private AttributeDecl _attDecl;

    /**
     * Constructor.
     * @param attDecl The wrapped Castor AttributeDecl
     */
    public SchemaAttribute(AttributeDecl attDecl) {
        _attDecl = attDecl;
    }

    /**
     * Get the name of the Attribute
     * @return The Attribute Schema name
     */
    public String getName() {
        return _attDecl.getName();
    }

    /**
     * @return Returns the _AttributeDecl.
     */
    public AttributeDecl getAttributeDecl() {
        return _attDecl;
    }

    /**
     * Get the use of this attribute.  This can be "optional", "required" or "prohibited"
     * @return The use.
     */
    public String getUse() {
        return _attDecl.getUse();
    }

    /**
     * Returns the default value of this Attribute.
     * This method should only be called from a SchemaController
     * @return the default value of this Attribute or null if no default was specified.
     */
    public String getDefaultValue() {
        return _attDecl.getDefaultValue();
    }

    /**
     * @return The Prefix of this Attribute, or "" if no prefix
     */
    public String getNamespacePrefix() {
        String refname = _attDecl.getReferenceName();
        if(refname != null) {
            int idx = refname.indexOf(':');
            if(idx >= 0) {
                String prefix = refname.substring(0, idx);
                if(prefix.equals("x")) prefix = "xml";    // WORKAROUND FOR THE x: thing
                return prefix;
            }
        }
        return "";
    }

    /**
     * @return The Namespace of this Attribute
     */
    public Namespace getNamespace() {
        String nsPrefix = getNamespacePrefix();
        if("".equals(nsPrefix)) return Namespace.NO_NAMESPACE;
        if(nsPrefix.equals("xml")) return Namespace.XML_NAMESPACE;
        String namespaceURI = _attDecl.getSchema().getNamespace(nsPrefix);
        return Namespace.getNamespace(nsPrefix, namespaceURI);
    }

    /**
     * Get the inbuilt vocabulary list for this Attribute.  If it's a Simple type of type
     * BOOLEAN_TYPE we can return "true, false" as the list, otherwise we will look
     * in the enumeration facets.
     * This method should be called from a SchemaController so that it can check its Helper files
     * for an additional over-riding VocabularyList before checking for one in the Schema.
     * @return vocabulary list for this Attribute or null if there isn't one
     */
    public VocabularyList getVocabularyList() {
        VocabularyList rvList = null;

        // Are we a simple type?
        SimpleType simpleType = _attDecl.getSimpleType();
        if(simpleType != null) {
            int type = simpleType.getTypeCode();
            switch(type) {
                // If boolean type use true/false
                case SimpleTypesFactory.BOOLEAN_TYPE:
                    rvList = VocabularyList.getTrueFalseList();
                    break;

                // Look at the enumeration facets in the Schema
                // Use these as a list
                default:
                    Vector v = null;
                    Enumeration e = simpleType.getFacets(Facet.ENUMERATION);
                    while(e.hasMoreElements()) {
                        if(v == null) v = new Vector();
                        Facet facet = (Facet)e.nextElement();
                        v.addElement(facet.getValue());
                    }
                    if(v != null) {
                        String[] s = new String[v.size()];
                        v.copyInto(s);
                        rvList = new VocabularyList("$schema$", s);
                    }
            }

        }

        return rvList;
    }

    /**
     * Get the type name of this Attribute.
     * @return The type name of this Attribute
     */
    public String getTypeName() {
        SimpleType simpleType = _attDecl.getSimpleType();
        return simpleType == null ? "Not known" : simpleType.getName();
    }

    /**
     * Get the max length of this Attribute.
     * @return The max length of this Attribute or null
     */
    public Long getMaxLength() {
        // Are we a simple type?
        SimpleType simpleType = _attDecl.getSimpleType();
        if(simpleType != null) {
            return simpleType.getMaxLength();
        }
        return null;
    }

    /**
     * Get the min length of this Attribute.
     * @return The min length of this Attribute or null
     */
    public Long getMinLength() {
        // Are we a simple type?
        SimpleType simpleType = _attDecl.getSimpleType();
        if(simpleType != null) {
            return simpleType.getMinLength();
        }
        return null;
    }

    /**
     * Get the base type name of this Attribute.
     * @return The base type name of this Attribute
     */
    public String getBaseTypeName() {
        SimpleType simpleType = _attDecl.getSimpleType();
        if(simpleType != null) {
            XMLType baseType = simpleType.getBaseType();
            return baseType == null ?  simpleType.getName() : baseType.getName();
        }
        return "Unknown BaseType";
    }

    /**
     * Get the Atomic Base type name of this Attribute.
     * @return The base type name of this Attribute or the type name if there is none
     */
    public String getAtomicBaseTypeName() {
        String name = null;

        SimpleType simpleType = _attDecl.getSimpleType();
        if(simpleType != null) {
            XMLType baseType = simpleType.getBaseType();

            while(baseType != null) {
                name = baseType.getName();
                baseType = baseType.getBaseType();
            }

            return name == null ? simpleType.getName() : name;
        }
        return "Unknown BaseType";
    }

    /**
     * @return a Facet Value or null
     */
    public String getFacetValue(String facetName) {
        String val = null;

        // If simple type
        SimpleType simpleType = _attDecl.getSimpleType();
        if(simpleType != null) {
            Facet facet = simpleType.getFacet(facetName);
            if(facet != null) val = facet.getValue();
        }

        return val;
    }

    /**
     * Create a JDOM Attribute based upon this SchemaAttribute
     * @return A JDOM Attribute based upon this SchemaAttribute
     */
    public Attribute createAttribute() {
        Attribute att = null;
        String name = getName();

        if(isExternalNamespace()) {
           Namespace ns = getNamespace();
           att = new Attribute(name, "", ns);
        }
        else {
            att = new Attribute(name, "");
        }

        return att;
    }

    /**
     * Get the path to this Attribute.  This will be the full path of all parent
     * elements plus a "@" and this Attribute's name.  For example: manifest.resources.resource@identifier
     * @return The path for this Attribute
     */
    public XMLPath getXMLPath() {
        XMLPath xmlPath = getParent().getXMLPath();
        Attribute att = createAttribute();
        xmlPath.appendAttributeName(att.getQualifiedName());
        return xmlPath;
    }

}