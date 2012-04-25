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

import javax.swing.Icon;

import org.jdom.Element;

import uk.ac.reload.dweezil.util.DweezilUIManager;
import uk.ac.reload.editor.datamodel.DataComponent;
import uk.ac.reload.editor.datamodel.IDataComponentIcon;
import uk.ac.reload.editor.learningdesign.datamodel.LD_Component;
import uk.ac.reload.editor.learningdesign.datamodel.LD_DataModel;
import uk.ac.reload.editor.learningdesign.datamodel.components.activities.ActivityStructure;
import uk.ac.reload.editor.learningdesign.datamodel.components.activities.LearningActivity;
import uk.ac.reload.editor.learningdesign.datamodel.components.activities.SupportActivity;
import uk.ac.reload.editor.learningdesign.datamodel.components.environments.Environment;
import uk.ac.reload.editor.learningdesign.datamodel.components.roles.Learner;
import uk.ac.reload.editor.learningdesign.datamodel.components.roles.Learner_Grouping;
import uk.ac.reload.editor.learningdesign.datamodel.components.roles.Role;
import uk.ac.reload.jdom.XMLPath;
import uk.ac.reload.moonunit.learningdesign.LD_Core;


/**
 * RolePart
 * 
 * @author Phillip Beauvoir
 * @version $Id: RolePart.java,v 1.1 1998/03/26 15:40:32 ynsingh Exp $
 */
public class RolePart
extends LD_Component
implements IDataComponentIcon
{
    public static XMLPath XMLPATH = new XMLPath(XMLPATH_ROOT_LD).appendElementName("method/play/act/role-part");

    static final String DEFAULT_TITLE = "Role Part";
    static final String DESCRIPTION = "This section provides information about the Role Part. " +
        "A Role Part consists of one Role and one Activity, Environment or Unit of Learning.";
    
    /**
     * The referenced role
     */
    private Role _role;
    
    /**
     * The referenced Activity/Environment component
     */
    private LD_Component _component;
    

    /**
     * Constructor
     * @param ldDataModel LD DataModel
     * @param element The bound XML <staff> element
     */
    public RolePart(LD_DataModel ldDataModel, Element element) {
        setDataModel(ldDataModel);
        setElement(element);
        setDescription(DESCRIPTION);
        
        // Listen to Roles being removed from the DataModel
        ldDataModel.addIDataModelListener(this);
    }
    
    /**
     * Set the backing JDOM Element
     * @param element The backing JDOM Element
     */
    public void setElement(Element element) {
        super.setElement(element);
        
        ensureTitleElement();

        // Because the identifier attribute is optional, there may not be one. We'll need one for referencing.
        if(getIdentifier() == null) {
            addIdentifier();
        }

        ensureRoleRef();
    }
    
    /**
     * Ensure that the <role-ref> element references a Role. If not, we will reference the default learner.<p>
     * @return True if we made an adjustment
     */
    protected boolean ensureRoleRef() {
        boolean modified = false;
        
        Learner_Grouping learnerGrouping = getLD_DataModel().getRoles_Grouping().getLearner_Grouping();
        Learner learner = (Learner)learnerGrouping.getChildAt(0);
        String learnerId = (learner == null) ? "" : learner.getIdentifier();
        
        // Force to null in order to get it again
        _role = null;
        _role = getReferencedRole();
        
        // No matching Role, so set to default Learner
        if(_role == null) {
            getRoleRefElement().setAttribute(LD_Core.REF, learnerId);
            _role = learner;
            modified = true;
        }
        
        return modified;
    }

    /**
     * @return The <role-ref> JDOM Element.
     */
    public Element getRoleRefElement() {
        return ensureChildElement(getElement(), LD_Core.ROLE_REF);
    }
    
    /**
     * @return The Role that this RolePart References
     */
    public Role getReferencedRole() {
        if(_role == null) {
            Element roleRef = getRoleRefElement();
            String ref = roleRef.getAttributeValue(LD_Core.REF);
            _role = (Role)getLD_DataModel().getRoles_Grouping().getChildByIdentifer(ref);
        }
        
        return _role;
    }
    
    /**
     * Set the Role that this RolePart references
     * @param role
     */
    public void setReferencedRole(Role role) {
        Element roleRef = getRoleRefElement();
        String roleRefID = roleRef.getAttributeValue(LD_Core.REF);
        String roleId = role.getIdentifier();
        
        if(roleRefID != null && !roleRefID.equals(roleId)) {
            roleRef.setAttribute(LD_Core.REF, roleId);
            _role = role;
            
            // And notify listeners
            getDataModel().fireDataComponentChanged(this);
            getDataModel().getSchemaDocument().changedElement(this, roleRef);
        }
    }

    /**
     * @return The referenced Activity, Environment or UOL
     */
    public LD_Component getReferencedComponent() {
        if(_component == null) {
            // Environment
            _component = __getReferencedComponent(LD_Core.ENVIRONMENT_REF, getLD_DataModel().getEnvironment_Grouping());
            // Learning Activity
            if(_component == null) {
                _component = __getReferencedComponent(LD_Core.LEARNING_ACTIVITY_REF, getLD_DataModel().getActivities_Grouping().getLearningActivity_Grouping());
            }
            // Support Activity
            if(_component == null) {
                _component = __getReferencedComponent(LD_Core.SUPPORT_ACTIVITY_REF, getLD_DataModel().getActivities_Grouping().getSupportActivity_Grouping());
            }
            // Activity Structure
            if(_component == null) {
                _component = __getReferencedComponent(LD_Core.ACTIVITY_STRUCTURE_REF, getLD_DataModel().getActivities_Grouping().getActivityStructure_Grouping());
            }
            // UOL href
            //Element element = getElement().getChild(LD_Core.UOL_HREF, getElement().getNamespace());
            //if(element != null) {
                //String ref = element.getAttributeValue(LD_Core.HREF);
                // _component = new UOL_HREF();
            //}
            
        }
        
        return _component;
    }
    
    private LD_Component __getReferencedComponent(String type, LD_Component parentComponent) {
        Element element = getElement().getChild(type, getElement().getNamespace());
        if(element != null) {
            String ref = element.getAttributeValue(LD_Core.REF);
            return parentComponent.getChildByIdentifer(ref);
        }
        return null;
    }

    /**
     * Set the Activity/Environment Component that this RolePart references
     * @param role
     */
    public void setReferencedComponent(LD_Component component) {
        // Find old one...
        
        // Environment
        Element element = getElement().getChild(LD_Core.ENVIRONMENT_REF, getElement().getNamespace());
        // Learning Activity
        if(element == null) {
            element = getElement().getChild(LD_Core.LEARNING_ACTIVITY_REF, getElement().getNamespace());
        }
        // Support Activity
        if(element == null) {
            element = getElement().getChild(LD_Core.SUPPORT_ACTIVITY_REF, getElement().getNamespace());
        }
        // Activity Structure
        if(element == null) {
            element = getElement().getChild(LD_Core.ACTIVITY_STRUCTURE_REF, getElement().getNamespace());
        }
        // UOL HREF
        if(element == null) {
            element = getElement().getChild(LD_Core.UOL_HREF, getElement().getNamespace());
        }


        // Remove it
        if(element != null) {
            getDataModel().getSchemaDocument().removeElement(this, element);
        }


        // Add new child element
        if(component instanceof Environment) {
            element = addChildElement(getElement(), LD_Core.ENVIRONMENT_REF);
        }
        else if(component instanceof LearningActivity) {
            element = addChildElement(getElement(), LD_Core.LEARNING_ACTIVITY_REF);
        }
        else if(component instanceof SupportActivity) {
            element = addChildElement(getElement(), LD_Core.SUPPORT_ACTIVITY_REF);
        }
        else if(component instanceof ActivityStructure) {
            element = addChildElement(getElement(), LD_Core.ACTIVITY_STRUCTURE_REF);
        }
        // Must do - UOL href
        
        // Set ref attribute
        if(element != null && component != null) {
            String id = component.getIdentifier();
            element.setAttribute(LD_Core.REF, id);
        }
        
        // Reset
        _component = null;
        
        // And notify listeners
        getDataModel().fireDataComponentChanged(this);
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
        // Don't allow last RolePart to be removed
        return getParent() == null ? true : ((Act)getParent()).getChildren().length > 1;
    }

    /* (non-Javadoc)
     * @see uk.ac.reload.editor.learningdesign.datamodel.ILD_IconInterface#getIcon()
     */
    public Icon getIcon() {
        return DweezilUIManager.getIcon(ICON_ROLEPART);
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
    
    
    
    // ========================= Listener Events ==================================
    
	public void componentChanged(DataComponent component) {
        // Either the referenced Role or Activity changed.  So do we...
	    if(component == getReferencedRole() || component == getReferencedComponent()) {
            getDataModel().fireDataComponentChanged(this);
        }
	}
	
	/**
     * We heard that a LD_Component has been removed from the Data Model
     */
    public void componentRemoved(DataComponent component) {
        // If a Role was removed then remove the role-ref if we have it
        // The safest way to do this is to check all role-refs. We might have been referencing a child Role.
        if(component instanceof Role) {
            boolean modified = ensureRoleRef();
            if(modified) {
                getDataModel().fireDataComponentChanged(this);
            }
        }
        
        // If a Component was removed then remove the ref if we have it
        // The best way to do this is to set the component to null, forcing a new get
        else if(component == getReferencedComponent()) {
            setReferencedComponent(null);
        }
    }
}
