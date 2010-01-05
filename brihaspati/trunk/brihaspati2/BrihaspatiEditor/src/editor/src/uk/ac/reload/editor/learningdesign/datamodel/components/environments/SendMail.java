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
import uk.ac.reload.editor.learningdesign.datamodel.LD_DataModel;
import uk.ac.reload.editor.learningdesign.datamodel.MetadataType;
import uk.ac.reload.editor.learningdesign.datamodel.components.roles.Learner;
import uk.ac.reload.editor.learningdesign.datamodel.components.roles.Learner_Grouping;
import uk.ac.reload.editor.learningdesign.datamodel.components.roles.Role;
import uk.ac.reload.jdom.XMLPath;
import uk.ac.reload.moonunit.SchemaDocument;
import uk.ac.reload.moonunit.learningdesign.LD_Core;


/**
 * Learning Design SendMail Element
 *
 * @author Phillip Beauvoir
 * @version $Id: SendMail.java,v 1.1 1998/03/26 15:42:28 ynsingh Exp $
 */
public class SendMail
extends Service 
{
    public static XMLPath XMLPATH = new XMLPath(XMLPATH_ROOT_LD).appendElementName("components/environments/environment/service/send-mail");
    public static XMLPath XMLPATH_EMAILDATA = new XMLPath(XMLPATH).appendElementName(LD_Core.EMAIL_DATA);
    
    static final String DESCRIPTION = "Send Mail Service.";

    /**
     * MetadataType
     */
    private MetadataType _mdType;

    /**
     * Constructor with backing Element
     * @param ldDataModel The LD_DataModel
     * @param element The backing JDOM Element
     */
    public SendMail(LD_DataModel ldDataModel, Element element) {
        super(ldDataModel, element);
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
        ensureDefaults();
    }

    /**
     * Ensure all default attributes and elements are set
     */
    protected void ensureDefaults() {
        // Check the Role Refs
        ensureRoleRefs();
        
        // "select" attribute
        ensureAttribute(getElement(), LD_Core.SELECT, "all-persons-in-role");
    }
    
    /**
     * Ensure that each <role-ref> element references a Role. If not, then we will remove it.<p>
     * If there is only one <role-ref> and it is invalid, we will reference the default learner.
     * @return True if we made an adjustment
     */
    protected boolean ensureRoleRefs() {
        boolean modified = false;
        
        Learner_Grouping learnerGrouping = getLD_DataModel().getRoles_Grouping().getLearner_Grouping();
        Learner learner = (Learner)learnerGrouping.getChildAt(0);
        String learnerId = (learner == null) ? "" : learner.getIdentifier();
        
        // Get the <role-ref> child elements
        Element[] roleRefs = getRoleRefElements();
        for(int i = 0; i < roleRefs.length; i++) {
            String ref = roleRefs[i].getAttributeValue(LD_Core.REF);
            Role role = (Role)getLD_DataModel().getRoles_Grouping().getChildByIdentifer(ref);
            // No matching Role
            if(role == null) {
                // If only one role-ref
                if(roleRefs.length == 1) {
                    roleRefs[i].setAttribute(LD_Core.REF, learnerId);
                }
                else { 
                    // Remove the parent of <role-ref> which is <email-data>
                    SchemaDocument schemaDocument = getDataModel().getSchemaDocument();
                    schemaDocument.removeElement(this, roleRefs[i].getParent());
                }
                modified = true;
            }
        }  
        
        return modified;
    }
    
    /**
     * Ensure that no matter what happens there will always be at least one default
     * role-ref - this will reference the first Learner Role, because there will always be
     * at least one Learner role.
     * @return True if we made an adjustment
     */
    protected boolean ensureDefaultRoleRef() {
        boolean modified = false;
        
        // Get the <role-ref> child elements
        Element[] roleRefs = getRoleRefElements();
        
        // What? No Role-refs?!!!  Add Learner
        if(roleRefs.length == 0) {
            // Get Learner number one
            Learner_Grouping learnerGrouping = getLD_DataModel().getRoles_Grouping().getLearner_Grouping();
            Learner learner = (Learner)learnerGrouping.getChildAt(0);
            if(learner != null) {
                addRoleRef(learner);
                modified = true;
            }
        }
        
        return modified;
    }

    /**
     * Add a new Role Reference
     * @param role The Role to reference
     */
    public void addRoleRef(Role role) {
        // Make sure we don't already have it
        Element elRoleRef = getRoleRefElement(role);
        if(elRoleRef != null) {
            return;
        }
        
        // Add to XML DOM
        
        // Add <e-mail-data> element
        Element elEmailData = addChildElement(getElement(), LD_Core.EMAIL_DATA);
        if(elEmailData != null) {
            // Get <role-ref> element which will have been added automagically
            elRoleRef = elEmailData.getChild(LD_Core.ROLE_REF, elEmailData.getNamespace());
            if(elRoleRef != null) {
                elRoleRef.setAttribute(LD_Core.REF, role.getIdentifier());

        		// And notify listeners that we changed
        		getDataModel().fireDataComponentChanged(this);
            }
        }
    }
    
    /**
     * Remove a Role Reference
     * @param role The Role to reference
     */
    public void removeRoleRef(Role role) {
        // Get the <role-ref> child element
        Element elRoleRef = getRoleRefElement(role);
        if(elRoleRef != null) {
            // Remove the parent of <role-ref> which is <email-data>
            SchemaDocument schemaDocument = getDataModel().getSchemaDocument();
            schemaDocument.removeElement(this, elRoleRef.getParent());

            // And notify listeners
            getDataModel().fireDataComponentChanged(this);

            // Now ensure that there is at least one role-ref
            ensureDefaultRoleRef();
        }
    }
    
    /**
     * @return The Roles to send the mail to.  These are the referenced Roles.
     */
    public Role[] getRoles() {
        Vector v = new Vector();
        
        // Get the <role-ref> child elements
        Element[] roleRefs = getRoleRefElements();
        for(int i = 0; i < roleRefs.length; i++) {
    		// Find the 'ref' attribute of <role-ref> element
            String ref = roleRefs[i].getAttributeValue(LD_Core.REF);
            // Find the referenced Role Component
            Role role = (Role)getLD_DataModel().getRoles_Grouping().getChildByIdentifer(ref);
            if(role != null) {
                // Add to Vector
                v.add(role);
            }
        }        
        
        // Turn the Vector into an array
        Role[] roles = new Role[v.size()];
        v.copyInto(roles);
        return roles;
    }
    
    /**
     * @return An array of all child <role-ref> JDOM Elements.
     */
    public Element[] getRoleRefElements() {
        Vector v = new Vector();
        
        Iterator it = getElement().getChildren(LD_Core.EMAIL_DATA, getElement().getNamespace()).iterator();
        while(it.hasNext()) {
            Element elEmailData = (Element)it.next();
            // Get the <role-ref> child element
            Element elRoleRef = elEmailData.getChild(LD_Core.ROLE_REF, elEmailData.getNamespace());
            if(elRoleRef != null) {
                v.add(elRoleRef);
            }            
        }

        // Turn the Vector into an array
        Element[] elements = new Element[v.size()];
        v.copyInto(elements);
        return elements;
    }
    
    /**
     * @return An array of all child <email-data> JDOM Elements.
     */
    public Element[] getEmailDataElements() {
        Vector v = new Vector();

        Iterator it = getElement().getChildren(LD_Core.EMAIL_DATA, getElement().getNamespace()).iterator();
        while(it.hasNext()) {
            Element elEmailData = (Element)it.next();
            v.add(elEmailData);
        }

        // Turn the Vector into an array
        Element[] elements = new Element[v.size()];
        v.copyInto(elements);
        return elements;
    }

    /**
     * @return True if there is only one role-ref element.
     * This is used to ensure that we don't remove the last role ref, i.e that there is always
     * at least one, as per the LD Schema.
     */
    public boolean isLastRoleRef() {
        return getElement().getChildren(LD_Core.EMAIL_DATA, getElement().getNamespace()).size() == 1;
    }
    
    /**
     * Get a <role-ref> Element given a Role component
     * @param role
     * @return The <role-ref> Element or null
     */
    public Element getRoleRefElement(Role role) {
        if(role == null) {
            return null;
        }
        
        String id = role.getIdentifier();
        
        // Get the <role-ref> child elements
        Element[] roleRefs = getRoleRefElements();
        for(int i = 0; i < roleRefs.length; i++) {
            String ref = roleRefs[i].getAttributeValue(LD_Core.REF);
            if(id.equals(ref)) {
                return roleRefs[i];
            }            
        }
        
        return null;
    }

    /**
     * @return A sensible default Title
     */
    public String getDefaultTitle() {
        return "Send Mail";
    }

    /* (non-Javadoc)
     * @see uk.ac.reload.editor.learningdesign.datamodel.ILD_IconInterface#getIcon()
     */
    public Icon getIcon() {
        return DweezilUIManager.getIcon(ICON_SENDMAIL);
    }

    /* (non-Javadoc)
     * @see uk.ac.reload.editor.learningdesign.datamodel.LD_Component#getXMLPath()
     */
    public XMLPath getXMLPath() {
        return XMLPATH;
    }
    
    /**
     * @return The MetadataType
     */
    public MetadataType getMetadataType() {
        if(_mdType == null) {
            _mdType = new MetadataType(getDataElement(), "Metadata", "Metadata for the SendMail.");
        }
        return _mdType;
    }

    
    //========================= Listener Events ==================================
    
	/**
     * We heard that a LD_Component has been removed from the Data Model
     */
    public void componentRemoved(DataComponent component) {
        // If a Role is removed then remove the role-ref if we have it
        // The safest way to do this is to check all role-refs. We might have been referencing a child Role.
        if(component instanceof Role) {
            boolean modified = ensureRoleRefs();
            if(modified) {
                getDataModel().fireDataComponentChanged(this);
            }
        }
    }
}
