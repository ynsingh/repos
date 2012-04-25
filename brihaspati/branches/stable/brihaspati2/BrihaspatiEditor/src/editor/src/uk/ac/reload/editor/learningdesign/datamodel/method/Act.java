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

package uk.ac.reload.editor.learningdesign.datamodel.method;

import java.util.Iterator;
import java.util.Vector;

import javax.swing.Icon;

import org.jdom.Element;

import uk.ac.reload.dweezil.util.DweezilUIManager;
import uk.ac.reload.editor.datamodel.DataComponent;
import uk.ac.reload.editor.datamodel.DataElement;
import uk.ac.reload.editor.datamodel.IDataComponentIcon;
import uk.ac.reload.editor.learningdesign.datamodel.ItemModelType;
import uk.ac.reload.editor.learningdesign.datamodel.LD_Component;
import uk.ac.reload.editor.learningdesign.datamodel.LD_DataModel;
import uk.ac.reload.editor.learningdesign.datamodel.MetadataType;
import uk.ac.reload.jdom.XMLPath;
import uk.ac.reload.moonunit.learningdesign.LD_Core;


/**
 * Act class
 * 
 * @author Phillip Beauvoir
 * @version $Id: Act.java,v 1.1 1998/03/26 15:40:32 ynsingh Exp $
 */
public class Act
extends LD_Component
implements IDataComponentIcon
{
    public static XMLPath XMLPATH = new XMLPath(XMLPATH_ROOT_LD).appendElementName("method/play/act");

    static final String DEFAULT_TITLE = "Act";
    static final String DESCRIPTION = "This section provides information about the Act.";
    
    static final String FEEDBACKDESCRIPTION_TITLE = "Completion Feedback Description";
    static final String FEEDBACKDESCRIPTION_DESCRIPTION = "May be used to provide additional information about the Completion of this Act.";

    /**
     * The Feedback Description ItemModelType
     */
    private ItemModelType _feedbackdescription;
    
    /**
     * MetadataType
     */
    private MetadataType _mdType;


    /**
     * Constructor
     * @param ldDataModel LD DataModel
     * @param element The bound XML <staff> element
     */
    public Act(LD_DataModel ldDataModel, Element element) {
        setDataModel(ldDataModel);
        setElement(element);
        setDescription(DESCRIPTION);
        addChildren();

        // Listen to the DataModel
        ldDataModel.addIDataModelListener(this);
    }
    
    /**
     * Set the backing JDOM Element
     * @param element The backing JDOM Element
     */
    public void setElement(Element element) {
        super.setElement(element);
        
        // Because the identifier attribute is optional, there may not be one. We'll need one for referencing.
        if(getIdentifier() == null) {
            addIdentifier();
        }

        ensureTitleElement();
    }
    
    /**
     * Add <role-part> Child Components
     */
    protected void addChildren() {
        // Add <role-part> elements from XML DOM
        Iterator it = getElement().getChildren(LD_Core.ROLE_PART, getElement().getNamespace()).iterator();
        while(it.hasNext()) {
            Element child = (Element)it.next();
            RolePart rolePart = new RolePart(getLD_DataModel(), child);
            addChild(rolePart);
        }
        
        // Ensure default of at least one role-part *after* adding children
        ensureChildElement(getElement(), LD_Core.ROLE_PART);
    }

    /**
     * Over-ride to add a child element
     */
    public DataComponent addChildElement(String elementName, String title) {
        if(!LD_Core.ROLE_PART.equals(elementName)) {
            return null;
        }
        
        RolePart rolePart = null;
        
        Element element = addChildElement(getElement(), elementName);
        if(element != null) {
            rolePart = new RolePart(getLD_DataModel(), element);
            addChild(rolePart);
            
            // Set Title if there is one
            if(title != null) {
                rolePart.setTitle(title);
            }
            
            // Tell Listeners
            getDataModel().fireDataComponentAdded(rolePart);
        }

        return rolePart;
    }

    /**
     * @return The Role Parts Completed for "complete-act/when-role-part-completed".
     * These are referenced Plays.
     */
    public RolePart[] getRolePartsCompleted() {
        Vector v = new Vector();
        
        Element elementCompleteAct = getElement().getChild(LD_Core.COMPLETE_ACT, getElement().getNamespace());
        if(elementCompleteAct != null) {
            Iterator it = elementCompleteAct.getChildren(LD_Core.WHEN_ROLE_PART_COMPLETED, elementCompleteAct.getNamespace()).iterator();
            while(it.hasNext()) {
                Element element = (Element)it.next();
                String ref = element.getAttributeValue(LD_Core.REF);
                LD_Component component = getChildByIdentifer(ref);
                if(component != null) {
                    v.add(component);
                }
            }
        }
        
        RolePart[] roleparts = new RolePart[v.size()];
        v.copyInto(roleparts);
        return roleparts;
    }

    /**
     * Add a RolePart reference for "complete-act/when-role-part-completed"
     * @param rolePart The RolePart to reference
     */
    public void addRolePartCompleted(RolePart rolePart) {
        DataElement completeAct = getCompleteActDataElement();
        
        // Ensure "complete-act" element exists
        Element element = completeAct.createElement();

        // Add it
        addRef(rolePart, element, LD_Core.WHEN_ROLE_PART_COMPLETED, LD_Core.REF);
    }

    /**
     * Remove a a RolePart reference for "complete-act/when-role-part-completed"
     * @param rolePart The RolePart to reference
     */
    public void removeRolePartCompleted(RolePart rolePart) {
        DataElement completeAct = getCompleteActDataElement();
        removeRef(rolePart, completeAct.getElement(), LD_Core.WHEN_ROLE_PART_COMPLETED, LD_Core.REF);
        
        // If last one then remove "complete-act" element
        if(getRolePartsCompleted().length == 0) {
            completeAct.deleteElement();
        }
    }

    /**
     * Move a referenced RolePart up
     * @param rolePart The RolePart to reference
     */
    public void moveRolePartCompletedUp(RolePart rolePart) {
        DataElement completeAct = getCompleteActDataElement();
        moveRefUp(rolePart, completeAct.getElement(), LD_Core.WHEN_ROLE_PART_COMPLETED, LD_Core.REF);
    }
    
    /**
     * Move a referenced RolePart down
     * @param rolePart The RolePart to reference
     */
    public void moveRolePartCompletedDown(RolePart rolePart) {
        DataElement completeAct = getCompleteActDataElement();
        moveRefDown(rolePart, completeAct.getElement(), LD_Core.WHEN_ROLE_PART_COMPLETED, LD_Core.REF);
    }

    /**
     * Ensure that each reference type element references a Component. If not, then we will remove it.<p>
     * @return True if we made an adjustment
     */
    protected boolean ensureAllReferences() {
        DataElement completeAct = getCompleteActDataElement();
        Element parent = completeAct.getElement();
        if(parent == null) {
            return false;
        }

        boolean modified = ensureReferences(parent, LD_Core.WHEN_ROLE_PART_COMPLETED, LD_Core.REF, this);
        
        // If no RolePart refs then remove "complete-act" element
        if(getRolePartsCompleted().length == 0) {
            completeAct.deleteElement();
        }
        
        return modified;
    }

    /**
     * @return The Feedback Description ItemModelType
     */
    public ItemModelType getFeedbackDescriptionItemModelType() {
        if(_feedbackdescription == null) {
            DataElement dataElement = new DataElement(getDataModel(), getElement(), new XMLPath("on-completion/feedback-description")); 
            _feedbackdescription = new ItemModelType(dataElement, FEEDBACKDESCRIPTION_TITLE, FEEDBACKDESCRIPTION_DESCRIPTION);
        }
        return _feedbackdescription; 
    }

    /**
     * Get a DataElement type for the "complete-act" element.
     * Note - the DataElement is created afresh each time in case the bound element has been
     * deleted or created elsewhere.
     * @return The DataElement for the "complete-act" element
     */
    public DataElement getCompleteActDataElement() {
        return new DataElement(getDataModel(), getElement(), new XMLPath("complete-act"));
    }

    /**
     * Get a DataElement type for the "complete-act/when-role-part-completed" element.
     * Note - the DataElement is created afresh each time in case the bound element has been
     * deleted or created elsewhere.
     * @return The DataElement for the "complete-act/when-role-part-completed" element
     */
    public DataElement getWhenRolePartCompletedDataElement() {
        return new DataElement(getDataModel(), getElement(), new XMLPath("complete-act/when-role-part-completed"));
    }

    /**
     * Get a DataElement type for the "complete-act/time-limit" element.
     * Note - the DataElement is created afresh each time in case the bound element has been
     * deleted or created elsewhere.
     * @return The DataElement for the "complete-act/time-limit" element
     */
    public DataElement getTimeLimitDataElement() {
        return new DataElement(getDataModel(), getElement(), new XMLPath("complete-act/time-limit"));
    }
    
    /**
     * Get a DataElement type for the "complete-act/when-property-value-is-set" element.
     * Note - the DataElement is created afresh each time in case the bound element has been
     * deleted or created elsewhere.
     * @return The DataElement for the "complete-act/when-property-value-is-set" element
     */
    public DataElement getWhenPropertyValueSetDataElement() {
        return new DataElement(getDataModel(), getElement(), new XMLPath("complete-act/when-property-value-is-set"));
    }

    /**
     * Get a DataElement type for the "complete-act/when-condition-true" element.
     * Note - the DataElement is created afresh each time in case the bound element has been
     * deleted or created elsewhere.
     * @return The DataElement for the "complete-act/when-condition-true" element
     */
    public DataElement getWhenConditionTrueDataElement() {
        return new DataElement(getDataModel(), getElement(), new XMLPath("complete-act/when-condition-true"));
    }

    /**
     * Over-ride this to set and ensure we have a <title> Element
     */
    public void setTitle(String title) {
        setTitleElement(title);
    }

    /* (non-Javadoc)
     * @see uk.ac.reload.editor.learningdesign.datamodel.LD_Component#canDelete()
     */
    public boolean canDelete() {
        // Don't allow last Act to be removed
        return getParent() == null ? true : getParent().getChildren().length > 1;
    }

    /* (non-Javadoc)
     * @see uk.ac.reload.editor.learningdesign.datamodel.ILD_IconInterface#getIcon()
     */
    public Icon getIcon() {
        return DweezilUIManager.getIcon(ICON_ACT);
    }

    /* (non-Javadoc)
     * @see uk.ac.reload.editor.learningdesign.datamodel.LD_Component#getXMLPath()
     */
    public XMLPath getXMLPath() {
        return XMLPATH;
    }
    
    /* (non-Javadoc)
     * @see uk.ac.reload.editor.learningdesign.datamodel.components.roles.Role#getDefaultTitle()
     */
    public String getDefaultTitle() {
        return DEFAULT_TITLE;
    }
    
    /**
     * @return The MetadataType
     */
    public MetadataType getMetadataType() {
        if(_mdType == null) {
            _mdType = new MetadataType(getDataElement(), "Metadata", "Metadata for the Act.");
        }
        return _mdType;
    }

    //========================= Listener Events ==================================
    
	/**
     * We heard that a RolePart has been removed from the DataModel so we need to check
     * whether it was a RolePart that we reference.  If it is, we will remove the component
     * and fire that we changed.
     */
    public void componentRemoved(DataComponent component) {
        if(component instanceof RolePart) {
            boolean modified = ensureAllReferences();
            if(modified) {
                getDataModel().fireDataComponentChanged(this);
            }
        }
    }
}
