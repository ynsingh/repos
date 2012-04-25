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

package uk.ac.reload.editor.learningdesign.datamodel.resources;

import java.util.Iterator;

import javax.swing.Icon;

import org.jdom.Element;

import uk.ac.reload.dweezil.util.DweezilUIManager;
import uk.ac.reload.editor.datamodel.DataComponent;
import uk.ac.reload.editor.datamodel.IDataComponentIcon;
import uk.ac.reload.editor.learningdesign.datamodel.LD_Component;
import uk.ac.reload.editor.learningdesign.datamodel.LD_DataModel;
import uk.ac.reload.editor.learningdesign.datamodel.MetadataType;
import uk.ac.reload.editor.learningdesign.xml.LearningDesign;
import uk.ac.reload.jdom.XMLDocumentListenerEvent;
import uk.ac.reload.moonunit.contentpackaging.CP_Core;



/**
 * This class wraps a <resource> XML element
 * 
 * @author Phillip Beauvoir
 * @version $Id: LD_Resource.java,v 1.1 1998/03/26 15:40:32 ynsingh Exp $
 */
public class LD_Resource
extends LD_Component
implements IDataComponentIcon
{

    /**
     * MetadataType
     */
    private MetadataType _mdType;

    /**
     * Constructor
     * 
     * @param ldDataModel The LD_DataModel
     * @param resourceElement The wrapped resource XML element
     */
    public LD_Resource(LD_DataModel ldDataModel, Element resourceElement) {
        setDataModel(ldDataModel);
        setElement(resourceElement);
        
        addChildren();
        
        // Listen to the element being changed
        ldDataModel.getLearningDesign().addXMLDocumentListener(this);
    }
    
    /** 
     * Over-ride to ensure there's a type attribute
     */
    public void setElement(Element element) {
        super.setElement(element);
        
        if(element != null) {
            String val = element.getAttributeValue(CP_Core.TYPE);
            if(val == null || "".equals(val)) {
                element.setAttribute(CP_Core.TYPE, "webcontent");
            }
        }
    }
    
    /**
     * Add Child Components
     */
    protected void addChildren() {
        if(getElement() != null) {
            addChildren(getElement(), CP_Core.FILE);
            addChildren(getElement(), CP_Core.DEPENDENCY);
        }
    }

    /**
     * Add any given child Elements
     * @param element the Parent Element
     */
    protected void addChildren(Element parentElement, String childName) {
        Iterator it = parentElement.getChildren(childName, parentElement.getNamespace()).iterator();
        while(it.hasNext()) {
            Element element = (Element)it.next();
            
            LD_Component ldComponent = createComponent(element);
            if(ldComponent != null) {
                addChild(ldComponent);
            }
        }
    }

    /**
     * Factory method to create child Component
     * @param element The wrapped JDOM Element
     * @return The newly created LD_Component or null
     */
    protected LD_Component createComponent(Element element) {
        LD_Component ldComponent = null;
        
        String elementName = element.getName();
        
        if(CP_Core.FILE.equals(elementName)) {
            ldComponent = new LD_File(getLD_DataModel(), element);
        }
        else if(CP_Core.DEPENDENCY.equals(elementName)) {
            ldComponent = new LD_Dependency(getLD_DataModel(), element);
        }
        
        return ldComponent;
    }

    /** 
     * Over-ride this to set HREF attribute
     */
    public void setTitle(String title) {
        Element element = getElement();
        if(element != null) {
            element.setAttribute(CP_Core.HREF, title);
        }
    }
    
    /**
     * Over-ride to add a child element
     */
    public DataComponent addChildElement(String elementName, String title) {
        if(!CP_Core.FILE.equals(elementName) && !CP_Core.DEPENDENCY.equals(elementName)) {
            return null;
        }
        
        LD_Component ldComponent = null;
        Element element = addChildElement(getElement(), elementName);

        // Now create Component
        if(element != null) {
            if(CP_Core.FILE.equals(elementName)) {
                ldComponent = new LD_File(getLD_DataModel(), element);
            }
            else {
                ldComponent = new LD_Dependency(getLD_DataModel(), element);
            }
            addChild(ldComponent);
            // Tell Listeners
            getDataModel().fireDataComponentAdded(ldComponent);
        }

        return ldComponent;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    public String toString() {
        Element element = getElement();
        
        // A non-resource (the Resource Selector combo box has a null first item)
        if(element == null) {
            return "";
        }
        // Return the HREF or IDENTIFIER if none
        else {
			String name = element.getAttributeValue(CP_Core.HREF);
			if(name == null || "".equals(name)) {
			    name = element.getAttributeValue(CP_Core.IDENTIFIER);
			}
            return name;
        }
    }

    /* (non-Javadoc)
	 * @see uk.ac.reload.editor.learningdesign.datamodel.ILD_IconInterface#getIcon()
	 */
	public Icon getIcon() {
        return DweezilUIManager.getIcon(ICON_RESOURCE);
    }
    
    /**
     * @return The MetadataType
     */
    public MetadataType getMetadataType() {
        if(_mdType == null) {
            _mdType = new MetadataType(getDataElement(), "Metadata", "Metadata for the Resource.");
        }
        return _mdType;
    }

    //========================= Listener Events ==================================
    
    /**
     * An Element was added - from a drag and drop operation
     */
    public void elementAdded(XMLDocumentListenerEvent event) {
        // If the source was a LearningDesign then it was a drag and drop from the File Tree
        // We don't want to do this for any other event since the element would be added twice
        if(event.getSource() instanceof LearningDesign) {
            Element element = event.getElement();
            String name = element.getName();
            // Only file elements that belong to this resource
            if(CP_Core.FILE.equals(name) && element.getParent() == getElement()) {
                LD_File ldFile = new LD_File(getLD_DataModel(), element);
                addChild(ldFile);
                getDataModel().fireDataComponentAdded(ldFile);
            }
        }
    }

    /** 
     * An Element changed - we need to fire on identifier and href changes
     */
    public void elementChanged(XMLDocumentListenerEvent event) {
        Element element = event.getElement();
        if(element.equals(getElement())) {
            getDataModel().fireDataComponentChanged(this);
        }
    }
}
