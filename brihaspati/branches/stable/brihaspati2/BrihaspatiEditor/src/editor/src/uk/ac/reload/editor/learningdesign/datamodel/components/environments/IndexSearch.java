/**
 *  RELOAD TOOLS
 *
 *  Copyright (c) 2004 Oleg Liber, Bill Olivier, Phillip Beauvoir
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

package uk.ac.reload.editor.learningdesign.datamodel.components.environments;

import java.util.Iterator;
import java.util.Vector;

import javax.swing.Icon;

import org.jdom.Element;

import uk.ac.reload.dweezil.util.DweezilUIManager;
import uk.ac.reload.editor.datamodel.DataComponent;
import uk.ac.reload.editor.datamodel.DataElement;
import uk.ac.reload.editor.learningdesign.datamodel.LD_Component;
import uk.ac.reload.editor.learningdesign.datamodel.LD_DataModel;
import uk.ac.reload.editor.learningdesign.datamodel.MetadataType;
import uk.ac.reload.jdom.XMLPath;
import uk.ac.reload.moonunit.SchemaDocument;
import uk.ac.reload.moonunit.learningdesign.LD_Core;


/**
 * Learning Design IndexSearch Element
 *
 * @author Phillip Beauvoir
 * @version $Id: IndexSearch.java,v 1.1 1998/03/26 15:42:28 ynsingh Exp $
 */
public class IndexSearch
extends Service 
{
    public static XMLPath XMLPATH = new XMLPath(XMLPATH_ROOT_LD).appendElementName("components/environments/environment/service/index-search");
    
    public static final String DESCRIPTION = "Index Search Service.";
    
    /**
     * Element names that we can add
     */
    public static final String[] ELEMENT_NAMES = {
            "environment",
            "learning-object",
            "send-mail",
            "conference",
            "index-search",
            "learning-activity",
            "support-activity",
            "activity-structure",
    };
    
    /**
     * MetadataType
     */
    private MetadataType _mdType;


    /**
     * Constructor with backing Element
     * @param ldDataModel The LD_DataModel
     * @param element The backing JDOM Element
     */
    protected IndexSearch(LD_DataModel ldDataModel, Element element) {
        super(ldDataModel, element);
        setDescription(DESCRIPTION);
        
        // Listen to the DataModel
        ldDataModel.addIDataModelListener(this);
    }

    /**
     * Set the backing JDOM Element
     * @param element The backing JDOM Element
     */
    public void setElement(Element element) {
        super.setElement(element);
        ensureDefaults();
    }

    /**
     * Ensure all default attributes and elements are set
     */
    protected void ensureDefaults() {
        // index element
        //Element indexElement = ensureChildElement(getElement(), LD_Core.INDEX);
        
        // Let's just put a child in if there isn't one
        //if(indexElement != null && indexElement.getChildren().size() == 0) {
        //    addIndexClass("class");
        //}
        
        // search element
        Element searchElement = ensureChildElement(getElement(), LD_Core.SEARCH);
        // search-type attribute
        ensureAttribute(searchElement, LD_Core.SEARCH_TYPE, "free-text-search");
    }

    /**
     * Get a DataElement type for the "index" element.
     * Note - the DataElement is created afresh each time in case the bound element has been
     * deleted or created elsewhere.
     * @return The DataElement for the "index" element
     */
    public DataElement getIndexDataElement() {
        return new DataElement(getDataModel(), getElement(), new XMLPath(LD_Core.INDEX));
    }

    /**
     * Get a DataElement type for the "search" element.
     * Note - the DataElement is created afresh each time in case the bound element has been
     * deleted or created elsewhere.
     * @return The DataElement for the "search" element
     */
    public DataElement getSearchDataElement() {
        return new DataElement(getDataModel(), getElement(), new XMLPath(LD_Core.SEARCH));
    }
    
    // ==================================== INDEX CLASS ===========================================
    
    /**
     * @return The index class Strings.
     */
    public String[] getIndexClasses() {
        Vector v = new Vector();
        
        Element elIndex = getElement().getChild(LD_Core.INDEX, getElement().getNamespace());
        if(elIndex != null) {
            Iterator it = elIndex.getChildren(LD_Core.INDEX_CLASS, elIndex.getNamespace()).iterator();
            while(it.hasNext()) {
                Element element = (Element)it.next();
                v.add(element.getText());
            }
        }
        
        String[] s = new String[v.size()];
        v.copyInto(s);
        
        return s;
    }
    
    /**
     * Add a class string
     * @param name
     */
    public void addIndexClass(String name) {
        if(name == null || "".equals(name)) {
            return;
        }
        
        // Do we have it already?
        Element elIndex = getElement().getChild(LD_Core.INDEX, getElement().getNamespace());
        Element element = getChildElementByTextValue(elIndex, LD_Core.INDEX_CLASS, name);
        if(element != null) {
            return;
        }

        elIndex = ensureChildElement(getElement(), LD_Core.INDEX);
        element = addChildElement(elIndex, LD_Core.INDEX_CLASS);
        element.setText(name);
        
        // And notify listeners that we changed
        getDataModel().fireDataComponentChanged(this);
    }

    /**
     * Delete a class string
     * @param name
     */
    public void removeIndexClass(String name) {
        if(name == null) {
            return;
        }
        
        Element elIndex = getElement().getChild(LD_Core.INDEX, getElement().getNamespace());
        if(elIndex != null) {
            Element element = getChildElementByTextValue(elIndex, LD_Core.INDEX_CLASS, name);
            if(element != null) {
                SchemaDocument schemaDocument = getDataModel().getSchemaDocument();
                schemaDocument.removeElement(this, element);
                // And notify listeners that we changed
                getDataModel().fireDataComponentChanged(this);
            }
        }
    }

    // ==================================== INDEX ELEMENT ===========================================
    
    /**
     * @return The index Elements. These are the referenced components.
     */
    public LD_Component[] getIndexElements() {
        Vector v = new Vector();
        
        Element elIndex = getElement().getChild(LD_Core.INDEX, getElement().getNamespace());
        if(elIndex != null) {
            Iterator it = elIndex.getChildren(LD_Core.INDEX_ELEMENT, elIndex.getNamespace()).iterator();
            while(it.hasNext()) {
                Element element = (Element)it.next();
                String index = element.getAttributeValue(LD_Core.INDEX);
                LD_Component component = getLD_DataModel().getChildByIdentifer(index);
                if(component != null) {
                    v.add(component);
                }
            }
        }

        LD_Component[] components = new LD_Component[v.size()];
        v.copyInto(components);
        
        return components;
    }

    /**
     * Add an IndexElement as a Component
     * @param component
     */
    public void addIndexElement(LD_Component component) {
        Element elIndex = ensureChildElement(getElement(), LD_Core.INDEX);
        addRef(component, elIndex, LD_Core.INDEX_ELEMENT, LD_Core.INDEX);
    }

    /**
     * Remove an IndexElement as a Component
     * @param component
     */
    public void removeIndexElement(LD_Component component) {
        Element elIndex = getElement().getChild(LD_Core.INDEX, getElement().getNamespace());
        removeRef(component, elIndex, LD_Core.INDEX_ELEMENT, LD_Core.INDEX);
    }


    // ==================================== INDEX TYPE OF ELEMENT ========================================
    

    /**
     * @return The index type of element Strings.
     */
    public String[] getIndexTypeOfElements() {
        Vector v = new Vector();
        
        Element elIndex = getElement().getChild(LD_Core.INDEX, getElement().getNamespace());
        if(elIndex != null) {
            Iterator it = elIndex.getChildren(LD_Core.INDEX_TYPE_OF_ELEMENT, elIndex.getNamespace()).iterator();
            while(it.hasNext()) {
                Element element = (Element)it.next();
                v.add(element.getText());
            }
        }
        
        String[] s = new String[v.size()];
        v.copyInto(s);
        
        return s;
    }

    /**
     * Add an index type of element string
     * @param name
     */
    public void addIndexTypeOfElement(String name) {
        if(name == null || "".equals(name)) {
            return;
        }
        
        // Do we have it already?
        Element elIndex = getElement().getChild(LD_Core.INDEX, getElement().getNamespace());
        Element element = getChildElementByTextValue(elIndex, LD_Core.INDEX_TYPE_OF_ELEMENT, name);
        if(element != null) {
            return;
        }

        elIndex = ensureChildElement(getElement(), LD_Core.INDEX);
        element = addChildElement(elIndex, LD_Core.INDEX_TYPE_OF_ELEMENT);
        element.setText(name);
        
        // And notify listeners that we changed
        getDataModel().fireDataComponentChanged(this);
    }

    /**
     * Delete a class string
     * @param name
     */
    public void removeIndexTypeOfElement(String name) {
        if(name == null) {
            return;
        }
        
        Element elIndex = getElement().getChild(LD_Core.INDEX, getElement().getNamespace());
        if(elIndex != null) {
            Element element = getChildElementByTextValue(elIndex, LD_Core.INDEX_TYPE_OF_ELEMENT, name);
            if(element != null) {
                SchemaDocument schemaDocument = getDataModel().getSchemaDocument();
                schemaDocument.removeElement(this, element);
                // And notify listeners that we changed
                getDataModel().fireDataComponentChanged(this);
            }
        }
    }

    // ================================================================================================
            
    /**
     * @return The MetadataType
     */
    public MetadataType getMetadataType() {
        if(_mdType == null) {
            _mdType = new MetadataType(getDataElement(), "Metadata", "Metadata for the Index Search.");
        }
        return _mdType;
    }

    /**
     * @return A sensible default Title
     */
    public String getDefaultTitle() {
        return "Index Search";
    }

    /* (non-Javadoc)
     * @see uk.ac.reload.editor.learningdesign.datamodel.ILD_IconInterface#getIcon()
     */
    public Icon getIcon() {
        return DweezilUIManager.getIcon(ICON_INDEXSEARCH);
    }

    /* (non-Javadoc)
     * @see uk.ac.reload.editor.learningdesign.datamodel.LD_Component#getXMLPath()
     */
    public XMLPath getXMLPath() {
        return XMLPATH;
    }
    
    //========================= Listener Events ==================================
    
	/**
     * We heard that a DataComponent has been removed from the Data Model so we need to check
     * whether it was a component that we reference.  If it is, we will remove the component
     * and fire that we changed.
     */
    public void componentRemoved(DataComponent component) {
        removeIndexElement((LD_Component)component);
    }
}
