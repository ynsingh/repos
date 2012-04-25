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

import javax.swing.Icon;

import org.jdom.Element;

import uk.ac.reload.dweezil.util.DweezilUIManager;
import uk.ac.reload.editor.datamodel.DataComponent;
import uk.ac.reload.editor.datamodel.IDataComponentIcon;
import uk.ac.reload.editor.learningdesign.datamodel.LD_Component;
import uk.ac.reload.editor.learningdesign.datamodel.LD_DataModel;
import uk.ac.reload.editor.learningdesign.xml.LearningDesign;
import uk.ac.reload.jdom.XMLDocumentListenerEvent;
import uk.ac.reload.jdom.XMLPath;
import uk.ac.reload.moonunit.contentpackaging.CP_Core;



/**
 * This class wraps the <resources> XML element
 * 
 * @author Phillip Beauvoir
 * @version $Id: LD_Resources.java,v 1.1 1998/03/26 15:40:32 ynsingh Exp $
 */
public class LD_Resources
extends LD_Component
implements IDataComponentIcon
{
    public static XMLPath XMLPATH = new XMLPath("manifest/resources");
    
    static final String TITLE = "Resources";
    static final String DESCRIPTION = "Resources to include in the Learning Design";

    /**
     * Constructor
     * 
     * @param ldDataModel The LD_DataModel
     */
    public LD_Resources(LD_DataModel ldDataModel) {
        setDataModel(ldDataModel);
        
        setTitle(TITLE);
        setDescription(DESCRIPTION);

        addChildren();
        
        // Listen to Component Changes
        ldDataModel.addIDataModelListener(this);
        
        // Listen to DOM Changes
        ldDataModel.getLearningDesign().addXMLDocumentListener(this);
    }
    
    /**
     * Add Child Components
     */
    protected void addChildren() {
        LearningDesign ld = (LearningDesign)getDataModel().getSchemaDocument();

        setElement(ld.getResourcesElement());
        
        // Get the <resource> elements
        Element[] elResources = ld.getResourceElements();
        for(int i = 0; i < elResources.length; i++) {
            LD_Resource ldResource = new LD_Resource(getLD_DataModel(), elResources[i]);
            addChild(ldResource);
        }
    }
     
    /**
     * Over-ride to add a child element
     */
    public DataComponent addChildElement(String elementName, String title) {
        if(!CP_Core.RESOURCE.equals(elementName)) {
            return null;
        }
        
        LD_Resource ldResource = null;
        
        Element element = addChildElement(getElement(), elementName);

        // Now create Component
        if(element != null) {
            ldResource = new LD_Resource(getLD_DataModel(), element);
            addChild(ldResource);

            // Set HREF if there is one
            if(title != null) {
                ldResource.setTitle(title);
            }
            
            // Tell Listeners
            getDataModel().fireDataComponentAdded(ldResource);
        }

        return ldResource;
    }

    /**
     * Get a resource given an identifier ref.
     * @param idRef The ID Ref from the Item to the Resource
     * @return The found LD_Resource or null if not found
     */
    public LD_Resource getLD_Resource(String idRef) {
        if(idRef == null || "".equals(idRef)) {
            return null;
        }
        
        DataComponent[] ldresources = getChildren();
        for(int i = 0; i < ldresources.length; i++) {
            String id = ((LD_Component)ldresources[i]).getIdentifier();
            if(idRef.equals(id)) {
                return (LD_Resource)ldresources[i];
            }
        }
        
        return null;
    }
    
    /* (non-Javadoc)
     * @see uk.ac.reload.editor.learningdesign.datamodel.LD_Component#canDelete()
     */
    public boolean canDelete() {
        return false;
    }
    
    /* (non-Javadoc)
	 * @see uk.ac.reload.editor.learningdesign.datamodel.ILD_IconInterface#getIcon()
	 */
	public Icon getIcon() {
        return DweezilUIManager.getIcon(ICON_RESOURCES);
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
            if(CP_Core.RESOURCE.equals(name)) {
                LD_Resource ldResource = new LD_Resource(getLD_DataModel(), element);
                addChild(ldResource);
                getDataModel().fireDataComponentAdded(ldResource);
            }
        }
    }
    
	//========================= Listener Events ==================================
    
	/**
     * We heard that a LD_Component has been removed from the Data Model
     */
    public void componentRemoved(DataComponent component) {
        // If a <resource> is removed we'll be in charge of going through all <item> and <dependency>
        // elements in the dom and removing any refs to the resource
        if(component instanceof LD_Resource) {
            String id = ((LD_Resource)component).getIdentifier();
            
            // Sanity
            if(id == null || "".equals(id)) {
                return;
            }
            
            LearningDesign ld = (LearningDesign)getDataModel().getSchemaDocument();
            
            // Get all <item> elements
            Element[] elements = ld.getElementsInManifest(ld.getLDElement(), CP_Core.ITEM, ld.getLDNamespace());
            for(int i = 0; i < elements.length; i++) {
                String ref = elements[i].getAttributeValue(CP_Core.IDENTIFIERREF);
                if(id.equals(ref)) {
                    elements[i].removeAttribute(CP_Core.IDENTIFIERREF);
                    ld.changedElement(this, elements[i]);
                }
            }
            
            // Get all <dependency> elements
            elements = ld.getElementsInManifest(ld.getRootElement(), CP_Core.DEPENDENCY, ld.getRootElement().getNamespace());
            for(int i = 0; i < elements.length; i++) {
                String ref = elements[i].getAttributeValue(CP_Core.IDENTIFIERREF);
                if(id.equals(ref)) {
                    elements[i].removeAttribute(CP_Core.IDENTIFIERREF);
                    ld.changedElement(this, elements[i]);
                }
            }
        }
    }
}
