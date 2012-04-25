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

import org.jdom.Element;

import uk.ac.reload.editor.datamodel.IDataComponentIcon;
import uk.ac.reload.editor.learningdesign.datamodel.LD_Component;
import uk.ac.reload.editor.learningdesign.datamodel.LD_DataModel;
import uk.ac.reload.moonunit.SchemaDocument;
import uk.ac.reload.moonunit.learningdesign.LD_Core;

/**
 * Learning Design Service Element
 *
 * @author Phillip Beauvoir
 * @version $Id: Service.java,v 1.1 1998/03/26 15:42:28 ynsingh Exp $
 */
public abstract class Service
extends LD_Component
implements IDataComponentIcon
{
    
    /**
     * Default Constructor
     */
    protected Service() {
    }
    
    /**
     * Constructor with backing Element
     * @param ldDataModel The LD_DataModel
     * @param element The backing JDOM Element
     */
    protected Service(LD_DataModel ldDataModel, Element element) {
        setDataModel(ldDataModel);
        setElement(element);
    }
    
    /**
     * Set the backing JDOM Element
     * @param element The backing JDOM Element
     */
    public void setElement(Element element) {
        super.setElement(element);
        ensureTitleElement();
    }

    /**
     * Over-ride this to set and ensure we have a <title> Element
     */
    public void setTitle(String title) {
        setTitleElement(title);
    }
    
    /**
     * Over-ride this to delete the parent <service> element
     */
    public void delete() {
        // First delete from XML DOM Model
        Element element = getElement();
        if(element != null) {
            getDataModel().getSchemaDocument()
            			  .removeElement(this, element.getParent());
        }
        
        if(getParent() != null) {
            getParent().removeChild(this);
        }
        
        getDataModel().fireDataComponentRemoved(this);

        // Remove any possible registered listeners
        getDataModel().removeIDataModelListener(this);
        getDataModel().getSchemaDocument().removeXMLDocumentListener(this);
    }

    /**
     * Over-ride this to get the parent <service> element's identifier
     * @return The identifier attribute of the bound Element or null if not found
     */
    public String getIdentifier() {
        String id = null;
        
        Element element = getElement();
        if(element != null) {
            element = element.getParent();
            if(element != null) {
                id = element.getAttributeValue(LD_Core.IDENTIFIER);
            }
        }
        
        return id;
    }

    /**
     * Over-ride this to get the parent "service" element and jump up 
     */
    protected void moveElementUp() {
        Element element = getElement();
        if(element != null) {
            element = element.getParent();
            if(element != null) {
                SchemaDocument schemaDocument = getDataModel().getSchemaDocument();
                if(schemaDocument.canMoveElementUp(element)) {
                    schemaDocument.moveElementUpSameType(this, element, true);
                }
            }
        }
    }
    
    /**
     * Over-ride this to get the parent "service" element and jump up 
     */
    protected void moveElementDown() {
        Element element = getElement();
        if(element != null) {
            element = element.getParent();
            if(element != null) {
                SchemaDocument schemaDocument = getDataModel().getSchemaDocument();
                if(schemaDocument.canMoveElementDown(element)) {
                    schemaDocument.moveElementDownSameType(this, element, true);
                }
            }
        }
    }
    
}
