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

package uk.ac.reload.editor.learningdesign.datamodel;

import java.util.Iterator;
import java.util.List;

import org.jdom.Element;

import uk.ac.reload.editor.datamodel.DataComponent;
import uk.ac.reload.editor.learningdesign.xml.LearningDesign;
import uk.ac.reload.jdom.XMLPath;
import uk.ac.reload.moonunit.SchemaDocument;
import uk.ac.reload.moonunit.learningdesign.LD_Core;

/**
 * Root LD 2nd level DataModel Component
 *
 * @author Phillip Beauvoir
 * @version $Id: LD_Component.java,v 1.1 1998/03/26 15:32:14 ynsingh Exp $
 */
public class LD_Component
extends DataComponent
{
    /**
     * The XMLPath to the <learning-design> element from the root of the CP
     */
    public static XMLPath XMLPATH_ROOT_LD = new XMLPath("manifest/organizations/imsld:learning-design");
    

    /**
     * Default Constructor
     */
    public LD_Component() {
    }
    
    /**
     * Constructor with LD_DataModel
     * @param ldDataModel The LD_DataModel
     */
    public LD_Component(LD_DataModel ldDataModel) {
        super(ldDataModel);
    }

    /**
     * @return The LD_DataModel
     */
    public LD_DataModel getLD_DataModel() {
        return (LD_DataModel)getDataModel();
    }
    
    /**
     * @return The identifier attribute of the bound Element or null if not found
     */
    public String getIdentifier() {
        String id = null;
        
        Element element = getElement();
        if(element != null) {
            id = element.getAttributeValue(LD_Core.IDENTIFIER);
        }
        
        return id;
    }
    
    /**
     * Add a new unique identifier attribute to the bound Element
     */
    public void addIdentifier() {
        Element element = getElement();
        if(element != null) {
            LearningDesign ld = (LearningDesign)getDataModel().getSchemaDocument();
            element.setAttribute(LD_Core.IDENTIFIER, ld.generateUniqueID(element));
        }
    }
    
    /**
     * Set the text for and ensure we have a <title> Element
     * @param title The Title to set
     */
    public void setTitleElement(String title) {
        // Double check
        if("".equals(title) || title == null) {
            title = getDefaultTitle();
        }
        
        // Ensure there is a <title> element
        Element titleElement = ensureTitleElement();
        if(titleElement != null) {
            titleElement.setText(title);
            // Notify DOM
            SchemaDocument schemaDocument = getDataModel().getSchemaDocument();
            if(schemaDocument != null) {
                schemaDocument.changedElement(this, getElement());
            }
        }
        
        // super, not this one
        super.setTitle(title);
    }
    
    /**
     * Ensure we have a <title> Element
     * @return the <title> Element or null
     */
    protected Element ensureTitleElement() {
        Element element = getElement();
        if(element == null) {
            return null;
        }

        // Do we have a <title> element?
        Element titleElement = element.getChild(LD_Core.TITLE, element.getNamespace());
        // If not, add one
        if(titleElement == null) {
            titleElement = new Element(LD_Core.TITLE, element.getNamespace());
            SchemaDocument schemaDocument = getDataModel().getSchemaDocument();
            schemaDocument.addElement(this, element, titleElement, false);
        }
        
        // Make sure we have a sensible title
        if("".equals(titleElement.getText())) {
            // Use Identifier if it isn't our GUID
            String id = getIdentifier();
            if(id != null && !id.startsWith("LD-")) {
                titleElement.setText(id);
            }
            else {
                titleElement.setText(getDefaultTitle());
            }
        }
        
        // Set title here
        super.setTitle(titleElement.getText());
        
        return titleElement;
    }
    
    /**
     * Get a child component given its identifier attribute.
     * @param id The identifier attribute
     * @return The child component  or null if not found
     */
    public LD_Component getChildByIdentifer(String id) {
        if(id == null || "".equals(id) || !hasChildren()) {
            return null;
        }
        
        DataComponent[] children = getChildren();
        for(int i = 0; i < children.length; i++) {
            LD_Component ldComponent = (LD_Component)children[i];
            String id2 = ldComponent.getIdentifier();
            if(id.equals(id2)) {
                return ldComponent;
            }
        }
        
        return null;
    }
    
    /**
     * Add a new Reference to another component
     * @param component The Component to reference
     * @param parentElement The Parent JDOM Element to add it to
     * @param childElementName The name of the JDOM Element ref
     * @param attributeRefName The name of the JDOM Attribute ref
     */
    public void addRef(LD_Component component, Element parentElement, String childElementName,
            String attributeRefName) {
        
        if(component == null || parentElement == null) {
            return;
        }
        
        // Because the identifier attribute of some components is optional, there may not be one.  Stupid! 
        if(component.getIdentifier() == null) {
            component.addIdentifier();
        }

        // Make sure we don't already have it
        Element element = getRefTypeElement(component.getIdentifier(), parentElement, childElementName, attributeRefName);
        if(element != null) {
            return;
        }
        
        // Add to XML DOM
        element = addChildElement(parentElement, childElementName);
        if(element != null) {
            element.setAttribute(attributeRefName, component.getIdentifier());
            // And notify listeners that we changed
            getDataModel().fireDataComponentChanged(this);
        }
    }

    /**
     * Remove a Referenced Component
     * @param component The Component reference
     * @param parentElement The Parent JDOM Element to remove it from
     * @param childElementName The name pf the child Element
     * @param attributeRefName The name of the JDOM Attribute ref
     */
    public void removeRef(LD_Component component, Element parentElement, String childElementName,
            String attributeRefName) {
        
        if(component == null || parentElement == null) {
            return;
        }

        // Get the ref child element
        Element element = getRefTypeElement(component.getIdentifier(), parentElement, childElementName, attributeRefName);
        if(element != null) {
            SchemaDocument schemaDocument = getDataModel().getSchemaDocument();
            schemaDocument.removeElement(this, element);

            // And notify listeners
            getDataModel().fireDataComponentChanged(this);
        }
    }
    
    /**
     * Move a referenced Component up
     * @param component The Component to move up
     * @param parentElement The Parent JDOM Element
     * @param childElementName The name pf the child Element
     * @param attributeRefName The name of the JDOM Attribute ref
     */
    public void moveRefUp(LD_Component component, Element parentElement, String childElementName,
            String attributeRefName) {
        
        if(component == null || parentElement == null) {
            return;
        }
        
        // Get the ref child element
        Element element = getRefTypeElement(component.getIdentifier(), parentElement, childElementName, attributeRefName);
        if(element != null) {
            SchemaDocument schemaDocument = getDataModel().getSchemaDocument();
            if(schemaDocument.canMoveElementUp(element)) {
                schemaDocument.moveElementUp(this, element, false);
            }

            // And notify listeners
            getDataModel().fireDataComponentChanged(this);
        }
    }
    
    /**
     * Move a referenced Component down
     * @param component The Component to move up
     * @param parentElement The Parent JDOM Element
     * @param childElementName The name pf the child Element
     * @param attributeRefName The name of the JDOM Attribute ref
     */
    public void moveRefDown(LD_Component component, Element parentElement, String childElementName,
            String attributeRefName) {
        
        if(component == null || parentElement == null) {
            return;
        }

        // Get the ref child element
        Element element = getRefTypeElement(component.getIdentifier(), parentElement, childElementName, attributeRefName);
        if(element != null) {
            SchemaDocument schemaDocument = getDataModel().getSchemaDocument();
            if(schemaDocument.canMoveElementDown(element)) {
                schemaDocument.moveElementDown(this, element, false);
            }

            // And notify listeners
            getDataModel().fireDataComponentChanged(this);
        }
    }
    
    /**
     * Get a ref type child JDOM Element given a component's identifer that it might reference.
     * Example = <environment-ref ref="ENV_ID1" />.
     * So, given the id ENV_ID1, this method would return the environment-ref element.
     * 
     * @param identifier The identifier to find
     * @param parentElement The parent JDOM Element to search all child Elements
     * @param childElementName The name pf the child Element
     * @param attributeRefName The name of the JDOM Attribute ref
     * @return The Element or null if not referenced
     */
    public Element getRefTypeElement(String identifier, Element parentElement, String childElementName,
            String attributeRefName) {
        
        if(identifier == null || parentElement == null) {
            return null;
        }
        
        Iterator it = parentElement.getChildren(childElementName, parentElement.getNamespace()).iterator();
        while(it.hasNext()) {
            Element element = (Element)it.next();
            String ref = element.getAttributeValue(attributeRefName);
            if(identifier.equals(ref)) {
                return element;
            }            
        }
        
        return null;
    }

    /**
     * Ensure that all reference type elements reference a Component. If not, then it will be removed.
     * 
     * @param parentElement The parent JDOM Element of the reference type elements
     * @param childElementName The chile Element ref name
     * @param attributeRefName The Attribute reference name
     * @param parentComponent The parent Component to look for the referenced component
     * @return True if the reference type element was removed
     */
    public boolean ensureReferences(Element parentElement, String childElementName,
            String attributeRefName, LD_Component parentComponent) {
        
        if(parentElement == null) {
            return false;
        }
        
        boolean modified = false;
        
        List list = parentElement.getChildren(childElementName, parentElement.getNamespace());
        Object[] elements = list.toArray();  // Convert to array, because the list is "live"
        
        for(int i = 0; i < elements.length; i++) {
            Element element = (Element)elements[i];
            String ref = element.getAttributeValue(attributeRefName);
            LD_Component component = parentComponent.getChildByIdentifer(ref);
            if(component == null) {
                // Remove it
                SchemaDocument schemaDocument = getDataModel().getSchemaDocument();
                schemaDocument.removeElement(this, element);
                modified = true;
            }
        }
        
        return modified;
    }
}
