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

import org.jdom.Element;

import uk.ac.reload.editor.datamodel.DataComponent;
import uk.ac.reload.editor.datamodel.IDataComponentIcon;
import uk.ac.reload.jdom.XMLDocumentListenerEvent;
import uk.ac.reload.moonunit.learningdesign.LD_Core;


/**
 * Reference to another Component
 *
 * @author Phillip Beauvoir
 * @version $Id: LD_ComponentRef.java,v 1.1 1998/03/26 15:32:14 ynsingh Exp $
 */
public abstract class LD_ComponentRef
extends LD_Component
implements IDataComponentIcon
{
    /**
     * The LD_Component that we reference
     */
    private LD_Component _ldComponent;

    /**
     * Default Constructor
     */
    protected LD_ComponentRef() {
        setTitle("(Unknown Reference)");
    }

	/**
     * @return Returns the LD_Component that this references
     */
    public LD_Component getLD_ComponentRef() {
        return _ldComponent;
    }
    
    /**
     * Over-ride this to add ouselcves as listener
     */
    public void setDataModel(LD_DataModel ldDataModel) {
        super.setDataModel(ldDataModel);
        
        ldDataModel.addIDataModelListener(this);
        ldDataModel.getLearningDesign().addXMLDocumentListener(this);
        
        setTitle("(Unknown Reference)");
    }
    
    /**
     * Set the Referenced LD_Component
     * @param component The _LD_Component to reference.
     */
    public void setLD_ComponentRef(LD_Component ldComponent) {
        _ldComponent = ldComponent;
        
        Element element = getElement();
        if(element != null) {
            element.setAttribute(LD_Core.REF, ldComponent.getIdentifier());
        }
        
        getDataModel().fireDataComponentChanged(this);
    }

    
    /* (non-Javadoc)
     * @see uk.ac.reload.editor.learningdesign.datamodel.LD_Component#getTitle()
     */
    public String getTitle() {
        return getLD_ComponentRef() != null ? getLD_ComponentRef().getTitle() : super.getTitle();
    }
    
    /**
     * @return The ref attribute value or null if not found
     */
    public String getRef() {
        String ref = null;
        
        Element element = getElement();
        if(element != null) {
            ref = element.getAttributeValue(LD_Core.REF);
        }
        
        return ref;
    }
    
    /**
     * Update our ref to the ID of the referenced JDOM Element
     * @param refElement
     */
    private void updateRefID(Element refElement) {
        String id = refElement.getAttributeValue(LD_Core.IDENTIFIER);
        String ref = getRef();
        if(id != null && !id.equals(ref)) {
            Element element = getElement();
            if(element != null) {
                element.setAttribute(LD_Core.REF, id);
            }
        }
    }

    //========================= Listener Events ==================================
    
	/**
	 * Component was changed - so fire that we changed
	 */
	public void componentChanged(DataComponent component) {
	    // The component that we reference has changed
	    if(component == getLD_ComponentRef()) {
	        getDataModel().fireDataComponentChanged(this);
	    }
	}
	
	/**
     * We heard that a LD_Component has been removed from the Data Model
     */
    public void componentRemoved(DataComponent component) {
        // The component that we reference has been deleted, so delete us as well
        if(component == getLD_ComponentRef()) {
            delete();
        }
    }

    /** 
     * An Element changed - maybe it was the ID of our referenced Element, so we need to change our ref
     */
    public void elementChanged(XMLDocumentListenerEvent event) {
        Element refElement = event.getElement();
        if(refElement.equals(getLD_ComponentRef().getElement())) {
            updateRefID(refElement);
        }
    }
}
