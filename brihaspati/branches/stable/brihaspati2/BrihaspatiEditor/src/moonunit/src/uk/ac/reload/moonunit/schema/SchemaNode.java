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

import org.exolab.castor.xml.schema.SimpleTypesFactory;
import org.jdom.Namespace;

import uk.ac.reload.jdom.XMLPath;
import uk.ac.reload.moonunit.vocab.VocabularyList;

/**
 * An abstract node that represents either an Element or an Attribute of the Schema.
 * These are hierarchical and contained by a SchemaModel class.
 *
 * @author Phillip Beauvoir
 * @version $Id: SchemaNode.java,v 1.1 1998/03/25 20:30:05 ynsingh Exp $
 */
public abstract class SchemaNode {
    /**
     * A Castor Simple types factory that we shall re-use
     */
    static SimpleTypesFactory SIMPLETYPESFACTORY = new SimpleTypesFactory();

    /**
     * The parent of this node.  Either a SchemaElement or the topmost SchemaModel
     */
    private Object _parent;

    /**
     * Default Constructor
     */
    public SchemaNode() {

    }

    /**
     * Set the parent of this node.
     * @param parent The parent node - Either a SchemaElement or the topmost SchemaModel
     * @return This node
     */
    public SchemaNode setParent(Object parent) {
        _parent = parent;
        return this;
    }

    /**
     * @return The parent node or null if there isn't one or if the parent is the
     * topmost SchemaModel
     */
    public SchemaNode getParent() {
        if(_parent instanceof SchemaNode) return (SchemaNode)_parent;
        return null;
    }

    /**
     * @return The owning SchemaModel that this node is a member of or null if
     * there isn't one.
     */
    public SchemaModel getSchemaModel() {
        if(_parent instanceof SchemaModel) return (SchemaModel)_parent;
        if(_parent instanceof SchemaElement) return ((SchemaElement)_parent).getSchemaModel();
        return null;
    }

    /**
     * @return whether this belongs to another Namespace
     */
    public boolean isExternalNamespace() {
        return !getNamespacePrefix().equals("");
    }

    /**
     * @return the name of this node - implementations should provide this
     */
    public abstract String getName();

    /**
     * @return the path to this node - implementations should provide this
     */
    public abstract XMLPath getXMLPath();

    /**
     * @return the vocabulary list for this node - implementations should provide this
     */
    public abstract VocabularyList getVocabularyList();

    /**
     * @return the type name of this node - implementations should provide this
     */
    public abstract String getTypeName();

    /**
     * @return The base type name of this node - implementations should provide this
     */
    public abstract String getBaseTypeName();

    /**
     * @return the default value of this node or null if no default was specified.
     */
    public abstract String getDefaultValue();

    /**
     * @return a Facet Value or null
     */
    public abstract String getFacetValue(String facetName);

    /**
     * @return The Prefix of this Node, or "" if no prefix
     */
    public abstract String getNamespacePrefix();

    /**
     * @return The Namespace of this Attribute
     */
    public abstract Namespace getNamespace();
}