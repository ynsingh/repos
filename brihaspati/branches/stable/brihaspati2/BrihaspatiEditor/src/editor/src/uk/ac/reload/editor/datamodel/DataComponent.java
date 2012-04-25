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

package uk.ac.reload.editor.datamodel;

import java.util.Iterator;
import java.util.Vector;

import org.jdom.Element;

import uk.ac.reload.jdom.XMLDocument;
import uk.ac.reload.jdom.XMLDocumentListener;
import uk.ac.reload.jdom.XMLDocumentListenerEvent;
import uk.ac.reload.jdom.XMLPath;
import uk.ac.reload.moonunit.SchemaDocument;
import uk.ac.reload.moonunit.schema.SchemaElement;

/**
 * Root LD 2nd level DataModel Component
 *
 * @author Phillip Beauvoir
 * @version $Id: DataComponent.java,v 1.1 1998/03/26 15:24:11 ynsingh Exp $
 */
public abstract class DataComponent
implements IDataComponent, IDataContainer, IDataModelListener, XMLDocumentListener
{
    /**
     * The DataModel
     */
    private DataModel _dataModel;
    
    /**
     * The backing JDOM XML Element
     */
    private Element _element;
    
    /**
     * Children
     */
    protected Vector _children;

    /**
     * The Parent Component
     */
    private DataComponent _parent;
    
    /**
     * Title
     */
    private String _title;
    
    /**
     * Description
     */
    private String _description;
    

    /**
     * Default Constructor
     */
    protected DataComponent() {
    }
    
    /**
     * Constructor with DataModel
     * @param dataModel The DataModel
     */
    protected DataComponent(DataModel dataModel) {
        _dataModel = dataModel;
    }

    /**
     * Set the DataModel
     * @param dataModel The DataModel
     */
    public void setDataModel(DataModel dataModel) {
        _dataModel = dataModel;
    }

    /**
     * @return The DataModel
     */
    public DataModel getDataModel() {
        return _dataModel;
    }

    /**
     * @return The backing JDOM Element
     */
    public Element getElement() {
        return _element;
    }
    
    /**
     * Set the backing JDOM Element
     * @param element The backing JDOM Element
     */
    public void setElement(Element element) {
        _element = element;
    }
    
    /**
     * @return Returns Parent Component
     */
    public DataComponent getParent() {
        return _parent;
    }
    
    /**
     * @param parent Parent Component to set.
     */
    public void setParent(DataComponent parent) {
        _parent = parent;
    }

    /**
     * @return A Title
     */
    public String getTitle() {
        return _title;
    }
	
    /**
     * Set the title
     * @param title The Title
     */
    public void setTitle(String title) {
        _title = title;
    }

    /**
     * @return A sensible default Title
     */
    public String getDefaultTitle() {
        return "Title";
    }

    /** 
	 * @return A Description
	 */
	public String getDescription() {
	    return _description;
	}
	
    /**
     * Set the Description
     * @param description The Description
     */
    public void setDescription(String description) {
        _description = description;
    }
    
    /**
     * @return A bound XMLPath
     */
    public XMLPath getXMLPath() {
        return null;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return getTitle();
    }
    
    /**
     * @return A DataElement wrapper for this component's Element
     */
    public DataElement getDataElement() {
        return new DataElement(getDataModel(), getElement());
    }
    
    /**
     * Add a Child JDOM XML Element
     * @param elementName The Element Name
     * @param title The Title
     * @return The DataComponent child
     */
    public DataComponent addChildElement(String elementName, String title) {
        return null;
    }
    
    /**
     * Add a Child JDOM XML Element to a parent Element (that is not JDOM undoable)
     * @param parentElement The parent Element, which may not be the bound element, it may be its parent
     * @param elementName The Element Name
     * @return The Element added or null
     */
    protected Element addChildElement(Element parentElement, String elementName) {
        Element element = null;
        
        SchemaDocument schemaDocument = getDataModel().getSchemaDocument();
        XMLPath xmlPath = XMLPath.getXMLPathForElement(parentElement);
        xmlPath.appendElementName(elementName);
        SchemaElement schemaElement = (SchemaElement)schemaDocument.getSchemaController().getSchemaNode(xmlPath);
        if(schemaElement != null) {
            element = schemaDocument.addElementBySchema(this, parentElement, schemaElement, true);
        }
        else {
            System.out.println("Could not get SchemaElement for: " + xmlPath);
        }
        
        return element;
    }
    
    /**
     * Delete this Component
     */
    public void delete() {
        // First delete from XML DOM Model
        Element element = getElement();
        if(element != null) {
            getDataModel().getSchemaDocument().removeElement(this, element);
        }
        
        // Then from DataModel
        if(getParent() != null) {
            getParent().removeChild(this);
        }
        
        // Notify
        getDataModel().fireDataComponentRemoved(this);
        
        // Remove any possible registered listeners
        getDataModel().removeIDataModelListener(this);
        getDataModel().getSchemaDocument().removeXMLDocumentListener(this);
    }
    
    /**
     * Sub-classers will want to over-ride this
     * @return True if allowed to delete this component, false if not
     */
    public boolean canDelete() {
        return true;
    }
    
    /**
     * @return True if allowed to move this component up, false if not
     */
    public boolean canMoveUp() {
        if(getParent() == null) {
            return false;
        }
        
        return getParent().indexOf(this) > 0;
    }

    /**
     * @return True if allowed to move this component down, false if not
     */
    public boolean canMoveDown() {
        if(getParent() == null) {
            return false;
        }
        
        return getParent().indexOf(this) < getParent().getChildren().length - 1;
    }
    
    /**
     * Move this component up
     */
    public void moveUp() {
        if(canMoveUp()) {
            // Save ref to parent because removeChild() will set it to null
            DataComponent parent = getParent();
            
            // Move up in the Data Model
            int index = parent.indexOf(this);
            parent.removeChild(this);
            parent.addChildAt(this, index - 1);
            getDataModel().fireDataComponentMoved(this);
            
            // Move up in the JDOM Model
            moveElementUp();
        }
    }
    
    /**
     * Move the bound element up
     */
    protected void moveElementUp() {
        Element element = getElement();
        if(element != null) {
            SchemaDocument schemaDocument = getDataModel().getSchemaDocument();
            if(schemaDocument.canMoveElementUp(element)) {
                schemaDocument.moveElementUp(this, element, true);
            }
        }
    }
    
    /**
     * Move the bound element up to one above the same type element.
     */
    protected void moveElementUpSameType() {
        Element element = getElement();
        if(element != null) {
            SchemaDocument schemaDocument = getDataModel().getSchemaDocument();
            if(schemaDocument.canMoveElementUp(element)) {
                schemaDocument.moveElementUpSameType(this, element, true);
            }
        }
    }
    
    /**
     * Move this component down
     */
    public void moveDown() {
        if(canMoveDown()) {
            // Save ref to parent because removeChild() will set it to null
            DataComponent parent = getParent();
            
            // Move down in the Data Model
            int index = parent.indexOf(this);
            parent.removeChild(this);
            parent.addChildAt(this, index + 1);
            getDataModel().fireDataComponentMoved(this);
            
            // Move down in the JDOM Model
            moveElementDown();
        }
    }

    /**
     * Move the bound element up
     */
    protected void moveElementDown() {
        Element element = getElement();
        if(element != null) {
            SchemaDocument schemaDocument = getDataModel().getSchemaDocument();
            if(schemaDocument.canMoveElementDown(element)) {
                schemaDocument.moveElementDown(this, element, true);
            }
        }
    }

    /**
     * Move the bound element down to one below the same type element.
     */
    protected void moveElementDownSameType() {
        Element element = getElement();
        if(element != null) {
            SchemaDocument schemaDocument = getDataModel().getSchemaDocument();
            if(schemaDocument.canMoveElementDown(element)) {
                schemaDocument.moveElementDownSameType(this, element, true);
            }
        }
    }

    /**
     * Ensure that the given element has a given default attribute if it is null or the empty string
     * @param element The Element, which may not be the bound element, it may be its child
     * @param attName The Attribute Name
     * @param defaultVal The Default value
     */
    public void ensureAttribute(Element element, String attName, String defaultVal) {
        if(element != null) {
            String val = element.getAttributeValue(attName);
            if(val == null || "".equals(val)) {
                element.setAttribute(attName, defaultVal);
            }
        }
    }

    /**
     * Ensure that the given element has a given default child element
     * @param parentElement The parent Element, which may not be the bound element, it may be its parent
     * @param elementName The child element name
     * @return The element either created or if exists, or null
     */
    public Element ensureChildElement(Element parentElement, String elementName) {
        if(parentElement != null) {
            Element child = parentElement.getChild(elementName, parentElement.getNamespace());
            if(child == null) {
                addChildElement(parentElement, elementName);
            }
            else {
                return child;
            }
        }
        return null;
    }
    
    /**
     * Return a child element given its text value
     * @param parentElement
     * @param elementName
     * @param text
     * @return a child element or null
     */
    public Element getChildElementByTextValue(Element parentElement, String elementName, String text) {
        if(parentElement != null && elementName != null && text != null) {
            Iterator it = parentElement.getChildren(elementName, parentElement.getNamespace()).iterator();
            while(it.hasNext()) {
                Element element = (Element)it.next();
                String s = element.getText();
                if(text.equals(s)) {
                    return element;
                }
            }
        }
        
        return null;
    }
    
    // ============================= Child Component Operations =====================================
    
    public void addChild(DataComponent child) {
        if(_children == null) {
            _children = new Vector();
        }
        
        if(child != null && !_children.contains(child)) {
            _children.add(child);
            child.setParent(this);
        }
    }
    
    public void addChildAt(DataComponent child, int index) {
        if(_children == null) {
            _children = new Vector();
        }
        
        if(child != null && !_children.contains(child)) {
            _children.add(index, child);
            child.setParent(this);
        }
    }

    public void removeChild(DataComponent child) {
        if(child != null && _children != null) {
            _children.remove(child);
            child.setParent(null);
        }
    }

    public void removeChildren() {
        if(_children != null) {
            for(int i = 0; i < _children.size(); i++) {
                DataComponent child = (DataComponent)_children.get(i);
                child.setParent(null);
            }
            
            _children.clear();
        }
    }

    public boolean hasChildren() {
        return _children == null ? false : !_children.isEmpty();
    }
    
    public int indexOf(DataComponent child) {
        if(child != null && _children != null) {
            return _children.indexOf(child);
        }
        else {
            return -1;
        }
    }
    
    public DataComponent getChildAt(int index) {
        if(!hasChildren()) {
            return null;
        }
        if(index >= getChildren().length) {
            return null;
        }
        return getChildren()[index];
    }

    /** 
     * Return an array of all immediate Child Components
     * @return An array of child components.  It will never be null, but may be empty.
     */
    public DataComponent[] getChildren() {
        if(_children == null) {
            _children = new Vector();
        }
        
        DataComponent[] children = new DataComponent[_children.size()];
        _children.copyInto(children);
        
        return children;
    }
    
    /**
     * Return an array of immediate Child Components given the child element name.
     * @param elementName The child element name.
     * @return An array of child components.  It will never be null, but may be empty.
     */
    public DataComponent[] getChildren(String elementName) {
        Vector v = new Vector();
        
        DataComponent[] children = getChildren();
        for(int i = 0; i < children.length; i++) {
            Element element = children[i].getElement();
            if(element != null) {
                String name = element.getName();
                if(name.equals(elementName)) {
                    v.add(children[i]);
                }
            }
        }
        
        DataComponent[] c = new DataComponent[v.size()];
        v.copyInto(c);
        return c;
    }

    /**
     * @return A deep list of all children and their children.
     */
    public DataComponent[] getAllChildren() {
        return getAllChildren(null);
    }
    
    /**
     * @return A deep list of all children and their children given the child element name. 
     */
    public DataComponent[] getAllChildren(String elementName) {
        Vector v = new Vector();
        __getAllChildren(v, elementName);
        DataComponent[] c = new DataComponent[v.size()];
        v.copyInto(c);
        return c;
    }

    /**
     * Helper to get all child components
     * @param v Collector vector
     * @param elementName Element name to select on.  If null then select all child elements.
     */
    void __getAllChildren(Vector v, String elementName) {
        DataComponent[] children;
        
        if(elementName == null) {
            children = getChildren();
        }
        else {
            children = getChildren(elementName);
        }
        
        for(int i = 0; i < children.length; i++) {
            v.add(children[i]);
            children[i].__getAllChildren(v, elementName);
        }
    }
    
    /**
     * Get a child component given its bound element.
     * @param element The Element to find the child by
     * @return The child component or null if not found
     */
    public DataComponent getChildByElement(Element element) {
        if(element == null || !hasChildren()) {
            return null;
        }
        
        DataComponent[] children = getChildren();
        for(int i = 0; i < children.length; i++) {
            Element boundElement = children[i].getElement();
            if(element == boundElement) {
                return children[i];
            }
        }
        
        return null;
    }
    
    /**
     * @return The next previous component or null if there isn't one
     */
    public DataComponent getPreviousSibling() {
        DataComponent parent = getParent();
        if(parent == null) {
            return null;
        }
        
        int index = parent.indexOf(this);
        
        // First one
        if(index < 1) {
            return null;
        }
        
        return parent.getChildAt(index - 1);
    }

    /**
     * @return The next sibling component or null if there isn't one
     */
    public DataComponent getNextSibling() {
        DataComponent parent = getParent();
        if(parent == null) {
            return null;
        }
        
        int index = parent.indexOf(this);
        
        // Last one
        if(index == parent.getChildren().length - 1) {
            return null;
        }
        
        return parent.getChildAt(index + 1);
    }


    // ====================== XMLDocumentListener methods =======================================
    
    /**
     * We heard that a JDOM XML Element has been added
     */
    public void elementAdded(XMLDocumentListenerEvent event) {
    }

    /**
     * We heard that a JDOM XML Element has been remobed
     */
    public void elementRemoved(XMLDocumentListenerEvent event) {
    }

    /**
     * We heard that a JDOM XML Element has been changed
     */
    public void elementChanged(XMLDocumentListenerEvent event) {
    }

    /**
     * We heard that the JDOM Document has been saved
     */
    public void documentSaved(XMLDocument doc) {
    }

    // ====================== ILD_DataModelListener methods =======================================
    
    /**
     * We heard that a Component has been added to the Data Model
     */
    public void componentAdded(DataComponent component) {
    }
    
	/**
     * We heard that a Component has been removed from the Data Model
     */
    public void componentRemoved(DataComponent component) {
    }

	/**
     * We heard that a Component has been moved in the Data Model
     */
    public void componentMoved(DataComponent component) {
    }

    /**
	 * A Component was changed in some way (maybe the text or Attribute)
	 */
	public void componentChanged(DataComponent component) {
	}
}
