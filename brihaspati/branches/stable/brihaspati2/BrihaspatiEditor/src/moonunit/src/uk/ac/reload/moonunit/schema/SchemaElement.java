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

import org.exolab.castor.xml.schema.*;
import org.jdom.Namespace;

import uk.ac.reload.jdom.XMLPath;
import uk.ac.reload.moonunit.vocab.VocabularyList;


/**
 * An Element node that represents an Element in the Schema.
 * Essentially - this is a wrapper for an ElementDecl type
 *
 * @author Phillip Beauvoir
 * @version $Id: SchemaElement.java,v 1.1 1998/03/25 20:30:05 ynsingh Exp $
 */
public class SchemaElement
extends SchemaNode
{
    /**
     * The wrapped Castor ElementDecl that we will interrogate
     */
    private ElementDecl _elementDecl;

    /**
     * Child SchemaElement objects
     */
    private SchemaElement[] _children;

    /**
     * Attributes
     */
    private SchemaAttribute[] _attributes;

    /**
     * Constructor to wrap ElementDecl.
     * @param elementDecl The wrapped Castor ElementDecl
     */
    public SchemaElement(ElementDecl elementDecl) {
        _elementDecl = elementDecl;
    }

    ////////////////////////////////////////////////////////////////////////////
    ///// PUBLIC METHODS
    ////////////////////////////////////////////////////////////////////////////

    /**
     * Set the parent owning SchemaModel
     * @param schemaModel The parent owning SchemaModel
     * @return This Element
     */
    public SchemaElement setSchemaModel(SchemaModel schemaModel) {
        setParent(schemaModel);
        return this;
    }

    /**
     * Get the name of the ElementDecl
     * @return The Element name
     */
    public String getName() {
        return _elementDecl.getName();
    }

    /**
     * @return the ElementDecl
     */
    public ElementDecl getElementDecl() {
        return _elementDecl;
    }

    /**
     * @return The Prefix of this Element, or "" if no prefix
     */
    public String getNamespacePrefix() {
        // Get the name of the element
        String refname = _elementDecl.getReferenceName();
        if(refname != null) {
            // This might already have a prefix
            int idx = refname.indexOf(':');
            if(idx >= 0) {
                String prefix = refname.substring(0, idx);
                return prefix;
            }
            
            // If not, check the main Schema for declared Namespace prefixes
            
            // Get the element's Schema
            Schema elementSchema = _elementDecl.getSchema();
            // Get this Schema
            Schema thisSchema = getSchemaModel().getSchema();
            // If different
            if(elementSchema != thisSchema) {
                return thisSchema.getNamespaces().getNamespacePrefix(elementSchema.getTargetNamespace());
            }
        }
        
        return "";
    }

    /**
     * @return The Namespace of this Schema Element
     */
    public Namespace getNamespace() {
        String nsPrefix = getNamespacePrefix();
        String nsURI = getSchemaModel().getSchema().getNamespace(nsPrefix);
        return Namespace.getNamespace(nsPrefix, nsURI);
    }

    /**
     * Return whether we are the Root Element, i.e our parent is null
     * @return
     */
    public boolean isRoot() {
        return getParent() == null;
    }

    /**
     * Get the path to this Element.  This will be the full path of all parent
     * elements plus this Element's name.  For example: manifest/resources/resource
     * This XMLPath is allocated dynamically, otherwise it doesn't work!
     * @return The path for this Element
     */
    public XMLPath getXMLPath() {
        if(getParent() instanceof SchemaElement) {
            XMLPath xmlPath = ((SchemaElement)getParent()).getXMLPath();
            String name = getName();
            // Add external namespace prefix
            if(isExternalNamespace()) name = getNamespacePrefix() + ":" + name;
            xmlPath.appendElementName(name);
            return xmlPath;
        }
        return new XMLPath(getName());
    }

    /**
     * Get the inbuilt vocabulary list for this Element.  If it's a Simple type of type
     * BOOLEAN_TYPE we can return "true, false" as the list, otherwise we will look
     * in the enumeration facets.
     * This method should be called from a SchemaController so that it can check its Helper files
     * for an additional over-riding VocabularyList before checking for one in the Schema.
     * @return vocabulary list for this Element or null if there isn't one
     */
    public VocabularyList getVocabularyList() {
        VocabularyList rvList = null;

        XMLType xmlType = getXMLType();

        // If simple type
        if(xmlType.isSimpleType()) {
            SimpleType simpleType = (SimpleType)xmlType;
            int type = simpleType.getTypeCode();
            switch(type) {
                // If boolean
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
                    break;
            }

        }

        return rvList;
    }

    /**
     * Get the type name of this Element.
     * @return The type name of this Element
     */
    public String getTypeName() {
        return getXMLType().getName();
    }

    /**
     * Get the Base type name of this Element.
     * @return The base type name of this Element or the type name if there is none
     */
    public String getBaseTypeName() {
        XMLType xmlType = getXMLType();
        XMLType baseType = xmlType.getBaseType();
        return baseType == null ?  xmlType.getName() : baseType.getName();
    }

    /**
     * Get the Atomic Base type name of this Element.
     * @return The base type name of this Element or the type name if there is none
     */
    public String getAtomicBaseTypeName() {
        String name = null;

        XMLType xmlType = getXMLType();
        if(xmlType != null) {
            XMLType baseType = xmlType.getBaseType();

            while(baseType != null) {
                name = baseType.getName();
                baseType = baseType.getBaseType();
            }

            return name == null ? xmlType.getName() : name;
        }

        return "Unknown BaseType";
    }

    /**
     * @return whether this can hold an actual text/number value rather than just being a container for
     * sub-elements
     */
    public boolean isValue() {
        String baseTypeName = getAtomicBaseTypeName();
        if(baseTypeName == null) return false;
        if(baseTypeName.equals("string") || baseTypeName.equals("int") || baseTypeName.equals("integer")
           || baseTypeName.equals("decimal")) return true;
        return false;
    }

    /**
     * @return whether this is a String type
     */
    public boolean isStringType() {
        String baseTypeName = getAtomicBaseTypeName();
        return baseTypeName != null && baseTypeName.equals("string");
    }

    /**
     * @return the minimum amount of times this element can occur - 0 means optional
     */
    public int getMinOccurs() {
        // If part of a choice group return zero, because it isn't mandatory
        if(isChoiceElement()) {
            return 0;
        }
        
        return _elementDecl.getMinOccurs();
    }

    /**
     * Get the maximum amount of times this element can occur - -1 means unbounded.
     * @return The maximum amount of times this element can occur
     */
    public int getMaxOccurs() {
        // If part of a choice group return parent group's max if unbounded because it over-rides it
        if(isChoiceElement() && getOrderMaxOccurs() == -1) {
            return -1;
        }
        
        return _elementDecl.getMaxOccurs();
    }

    /**
     * Returns the default value of this element definition if it is an ElementDecl
     * This method should only be called from a SchemaController - look here first then then the SchemaController
     * @return the default value of this element definition or null if no default was specified.
     */
    public String getDefaultValue() {
        return _elementDecl.getDefaultValue();
    }

    /**
     * Get the xml type of the ElementDecl - this is either ComplexType or SimpleType.
     * This only applies to ElementDecl
     * @return The xml type of the ElementDecl or null if this Particle is not an ElementDecl
     */
    protected XMLType getXMLType() {
        return _elementDecl.getType();
    }

    /**
     * Return whether this Element is made up of other Elements.
     * @return True if this Element is a Complex Type, or false if not
     */
    public boolean isComplexType() {
        return getXMLType().isComplexType();
    }

    /**
     * @return true of this element is part of a "choice" grouping
     */
    public boolean isChoiceElement() {
        return "choice".equals(getOrderType());
    }

    /**
     * @return true of this element is part of a "sequence" grouping
     */
    public boolean isSequenceElement() {
        return "sequence".equals(getOrderType());
    }

    /**
     * @return the Order type that this is a member of.  Can be "choice", "sequence", "all" or null
     */
    public String getOrderType() {
        Structure s = _elementDecl.getParent();
        if(s instanceof Group) {
            Group g = (Group)s;
            return g.getOrder().toString();
        }
        return null;
    }

    /**
     * @return the Max Occurs of the parent Grouping (choice, sequence) or 0
     */
    public int getOrderMaxOccurs() {
        Structure s = _elementDecl.getParent();
        if(s instanceof Group) {
            Group g = (Group)s;
            return g.getMaxOccurs();
        }
        return 0;
    }

    /**
     * @return the Max Occurs of the parent Grouping (choice, sequence) or 0
     */
    public int getOrderMinOccurs() {
        Structure s = _elementDecl.getParent();
        if(s instanceof Group) {
            Group g = (Group)s;
            return g.getMinOccurs();
        }
        return 0;
    }

    /**
     * @return a Facet Value or null
     */
    public String getFacetValue(String facetName) {
        String val = null;

        // If simple type
        XMLType xmlType = getXMLType();
        if(xmlType.isSimpleType()) {
            SimpleType simpleType = (SimpleType)xmlType;
            Facet facet = simpleType.getFacet(facetName);
            if(facet != null) {
                val = facet.getValue();
            }
        }

        return val;
    }

    /**
     * @return an array of SchemaAttribute objects or null if there aren't any
     */
    public SchemaAttribute[] getSchemaAttributes() {
        // Build only once
        if(_attributes == null) {
            Vector v = new Vector();
            buildAttributes(v);
            _attributes = new SchemaAttribute[v.size()];
            v.copyInto(_attributes);
            v = null; // thank-you
        }
        
        return _attributes;
    }

    /**
     * Build any SchemaAttribute objects
     */
    private void buildAttributes(Vector v) {
        XMLType xmlType = getXMLType();

        if(xmlType instanceof ComplexType) {
            Enumeration e = ((ComplexType)xmlType).getAttributeDecls();
            while(e.hasMoreElements()) {
                AttributeDecl attDecl = (AttributeDecl) e.nextElement();
                SchemaAttribute att = new SchemaAttribute(attDecl);
                att.setParent(this);
                v.add(att);
            }
        }
    }

    /**
     * @return a SchemaAttribute by its name.
     * If the attribute has a different Namespace then it should be prefixed such as xml:base
     */
    public SchemaAttribute getSchemaAttribute(String attName) {
        Namespace ns;

        // Check for Namespace prefix - external Namespace
        int idx = attName.indexOf(':');
        if(idx >= 0) {
            String prefix = attName.substring(0, idx);
            attName = attName.substring(idx + 1);
            String nsURI = _elementDecl.getSchema().getNamespace(prefix);
            ns = Namespace.getNamespace(prefix, nsURI);
        }
        // Parent = no Namespace
        else {
            ns = Namespace.NO_NAMESPACE;
        }

        return getSchemaAttribute(attName, ns);
    }

    /**
     * @return a SchemaAttribute by its name and Namespace
     */
    public SchemaAttribute getSchemaAttribute(String attName, Namespace ns) {
        SchemaAttribute[] atts = getSchemaAttributes();
        for(int i = 0; i < atts.length; i++) {
            if(atts[i].getName().equals(attName) && atts[i].getNamespace().equals(ns)) {
                return atts[i];
            }
        }
        return null;
    }

    /**
     * Return a SchemaAttribute by its index position
     * @param index The index position of the Attribute
     * @return The SchemaAttribute or null if there isn't one
     */
    public SchemaAttribute getSchemaAttributeAtIndex(int index) {
        if(index >= getSchemaAttributes().length || index < 0) {
            return null;
        }
        return getSchemaAttributes()[index];
    }

    /**
     * Return whether this Element has SchemaAttributes
     * @return True or False
     */
    public boolean hasSchemaAttributes() {
        return getSchemaAttributes().length > 0;
    }

    /**
     * Return whether this Element has a SchemaAttribute by name using the Default Namespace.NO_NAMESPACE
     * @param attName
     * @return
     */
    public boolean hasSchemaAttribute(String attName) {
        if(hasSchemaAttributes()) {
            return indexofSchemaAttribute(attName, Namespace.NO_NAMESPACE) != -1;
        }
        return false;
    }

    /**
     * Return whether this Element has a SchemaAttribute by Name and Namespace
     * @param attName
     * @param ns Namespace
     * @return
     */
    public boolean hasSchemaAttribute(String attName, Namespace ns) {
        if(hasSchemaAttributes()) {
            return indexofSchemaAttribute(attName, ns) != -1;
        }
        return false;
    }

    /**
     * Return the index of a SchemaAttribute
     * @return The index of the Attribute or -1 if not found
     */
    public int indexofSchemaAttribute(SchemaAttribute att) {
        if(att == null) {
            return -1;
        }
        return indexofSchemaAttribute(att.getName(), att.getNamespace());
    }

    /**
     * Return the index of a SchemaAttribute by name using the Default Namespace.NO_NAMESPACE
     * @param attName
     * @return The index of the Attribute or -1 if not found
     */
    public int indexofSchemaAttribute(String attName) {
        return indexofSchemaAttribute(attName, Namespace.NO_NAMESPACE);
    }

    /**
     * Return the index of a SchemaAttribute by name and Namespace
     * @param attName
     * @param ns
     * @return The index of the Attribute or -1 if not found
     */
    public int indexofSchemaAttribute(String attName, Namespace ns) {
        if(attName == null) {
            return -1;
        }

        SchemaAttribute[] atts = getSchemaAttributes();
        for(int i = 0; i < atts.length; i++) {
            if(atts[i].getName().equals(attName) && atts[i].getNamespace().equals(ns)) {
                return i;
            }
        }

        return -1;
    }

    /**
     * A String representation of this Element.
     * @return the name of the Element
     */
    public String toString() {
        return getName();
    }

///////////////////////// CHILDREN //////////////////////////////////////////////

    /**
     * Get the Children Elements of this Element.
     * @return An Array of SchemaElement child objects.  It will never be null
     */
    public SchemaElement[] getChildren() {
        return buildChildren();
    }

    /**
     * Get the Children Elements of this Element with a given Namespace.
     * @return An Array of SchemaElement child objects.  It will never be null
     */
    public SchemaElement[] getChildren(Namespace ns) {
        Vector v = new Vector();
        SchemaElement[] children = getChildren();
        for(int i = 0; i < children.length; i++) {
            if(children[i].getNamespace() == ns) {
                v.add(children[i]);
            }
        }
        SchemaElement[] elements = new SchemaElement[v.size()];
        v.copyInto(elements);
        v = null;
        return elements;
    }

    /**
     * Get the child count of SchemaElement child objects.
     * @return The Child Count.
     */
    public int getChildCount() {
        return getChildren().length;
    }

    /**
     * Return whether this Element has Child Elements.
     * @return True if this Element has Children, or false if not
     */
    public boolean hasChildren() {
        return getChildCount() > 0;
    }

    /**
     * Return whether this SchemaElement has a child Element by name
     * If the child has a different Namespace then it should be prefixed such as imscp:chilName
     * @param childName The name of the Child Element's name
     * @return True if it has
     */
    public boolean hasChild(String childName) {
        return getChild(childName) != null;
    }

    /**
     * Get a child schema element by name
     * If the child has a different Namespace then it should be prefixed such as imscp:childName
     * @return a child SchemaElement by name or null
     */
    public SchemaElement getChild(String childName) {
        Namespace ns;

        // Check for Namespace prefix
        int idx = childName.indexOf(':');
        if(idx >= 0) {
            String prefix = childName.substring(0, idx);
            childName = childName.substring(idx + 1);
            String nsURI = _elementDecl.getSchema().getNamespace(prefix);
            if(nsURI == null || "".equals(nsURI)) {
                ns = null;
            }
            else {
                ns = Namespace.getNamespace(prefix, nsURI);
            }
        }
        else {
            ns = getNamespace();
        }

        return getChild(childName, ns);
    }

    /**
     * Get a child schema element by name with given namespace
     * @return a child SchemaElement by name or null
     */
    public SchemaElement getChild(String childName, Namespace ns) {
        SchemaElement[] children = getChildren();
        for(int i = 0; i < children.length; i++) {
            if(children[i].getName().equals(childName) && children[i].getNamespace().equals(ns)) {
                return children[i];
            }
        }
        return null;
    }

    /**
     * @return the child SchemaElement at index position or null if no children
     */
    public SchemaElement getChildAt(int index) {
        if(index >= getChildCount() || index < 0) {
            return null;
        }
        return getChildren()[index];
    }

    /**
     * @return The index of the child or -1 if not found
     */
    public int indexofChild(SchemaElement child) {
        if(child == null) {
            return -1;
        }
        
        SchemaElement[] children = getChildren();
        for(int i = 0; i < children.length; i++) {
            if(children[i] == child) {
                return i;
            }
        }
        
        return -1;
    }

    /**
     * Return the index of a child by name and Namespace
     * @param childName
     * @param ns
     * @return The index of the child or -1 if not found
     */
    public int indexofChild(String childName, Namespace ns) {
        if(childName == null) {
            return -1;
        }

        SchemaElement[] children = getChildren();
        for(int i = 0; i < children.length; i++) {
            if(children[i].getName().equals(childName) && children[i].getNamespace().equals(ns)) {
                return i;
            }
        }

        return -1;
    }

    /**
     * Return a String array of child names
     * @return
     */
    public String[] getChildNames() {
        SchemaElement[] children = getChildren();
        String s[] = new String[children.length];
        for(int i = 0; i < children.length; i++) {
            s[i] = children[i].getName();
        }
        return s;
    }


    ////////////////////////////////////////////////////////////////////////////
    // Building methods
    ////////////////////////////////////////////////////////////////////////////

    /**
     * Recurse and build up all child Elements and Attributes of ElementDecl
     */
    protected SchemaElement[] buildChildren() {
        // Build only once
        if(_children == null) {
            Vector v = new Vector();

            XMLType xmlType = getXMLType();
            
            // If Complex Type build children
            if(xmlType instanceof ComplexType) {
                ComplexType complexType = (ComplexType) xmlType;
                // Build/Add sub-group child structures
                Enumeration cTypeBits = complexType.enumerate();
                while(cTypeBits.hasMoreElements()) {
                    Structure structure = (Structure) cTypeBits.nextElement();

                    switch(structure.getStructureType()) {
                        case Structure.GROUP:
                            buildChildGroup((Group) structure, v);
                            break;

                        case Structure.MODELGROUP:
                            buildChildGroup((ModelGroup) structure, v);
                            break;

                        default:
                            System.out.println("Other ComplexType Structure: " + structure.toString());
                    }
                }
            }
            
            _children = new SchemaElement[v.size()];
            v.copyInto(_children);
            v = null; // thank-you
        }

        return _children;
    }

    /**
     * Build a child Group type - this can contain more Elements and sub-Groups.
     *
     * TO DO:
     *
     * A Group consists of either a "sequence", "choice" or "all" grouping.  This
     * can be deduced from Group.getOrder().  A "sequence" is OK and we can add the
     * group's ElementDecl children normally.  But if it's a "choice" grouping then this needs a
     * different approach.
     *
     * The members of the "choice" grouping should really be added as a
     * SchemaGroup or SchemaModelGroup child container object.
     *
     * At the moment the only way to group them is to get a SchemaElement's ElementDecl,
     * get its parent Group, see if it's a "choice" group and get its children as siblings.
     *
     * We could retrofit this by adding a separate Vector of SchemaGroup children that also
     * contain their SchemaElement objects.  Then we could have methods such as
     * getChildGroups().
     *
     */
    private void buildChildGroup(Group group, Vector v) {
        Enumeration groupMembers = group.enumerate();
        while(groupMembers.hasMoreElements()) {
            Particle particle = (Particle)groupMembers.nextElement();

            switch(particle.getStructureType()) {
                // Another Element Decl
                case Structure.ELEMENT:
                    ElementDecl ed = (ElementDecl)particle;
                    addChildElementDecl(ed, v);
                    break;

                // A Model Group
                case Structure.MODELGROUP:
                    ModelGroup mg = (ModelGroup)particle;
                    buildChildGroup(mg, v);
                    break;

                // Another sub-group
                case Structure.GROUP:
                    Group gp = (Group)particle;
                    buildChildGroup(gp, v);
                    break;

                case Structure.WILDCARD:
                    break;

                default:
                    System.out.println("Group Particle: " + particle.toString());
            } // switch
        } // while
    }

    /**
     * Add another sub-ElementDecl Child
     * @param childElementDecl The ElementDecl to add
     */
    private void addChildElementDecl(ElementDecl childElementDecl, Vector v) {
        SchemaElement child = new SchemaElement(childElementDecl);
        child.setParent(this);
        v.add(child);
    }

    /**
     * @return true if schemaElement has an ancestor of the same name and Namespace
     */
    public boolean hasAncestor(SchemaElement schemaElement) {
        if(getParent() instanceof SchemaElement) {
            SchemaElement parent = (SchemaElement)getParent();
            return  parent.getName().equals(schemaElement.getName()) &&
                    parent.getNamespace().equals(schemaElement.getNamespace())
            			? true :
            			  parent.hasAncestor(schemaElement);
        }
        
        return false;
    }
    
    /**
     * @return An array of SchemaElement objects representing the full path
     * for example manifest/resources/resource
     * The root SchemaElement will be array item zero
     */
    public SchemaElement[] getSchemaElements() {
        Vector v = new Vector();
        __getSchemaElements(v);
        SchemaElement[] schemaElements = new SchemaElement[v.size()];
        v.copyInto(schemaElements);
        return schemaElements;
    }
    
    private void __getSchemaElements(Vector v) {
        v.add(0, this);
        if(getParent() instanceof SchemaElement) {
            ((SchemaElement)getParent()).__getSchemaElements(v);
        }
    }
}