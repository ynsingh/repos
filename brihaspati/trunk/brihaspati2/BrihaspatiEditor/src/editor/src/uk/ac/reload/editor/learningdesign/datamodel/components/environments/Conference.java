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
import uk.ac.reload.editor.learningdesign.datamodel.ItemType;
import uk.ac.reload.editor.learningdesign.datamodel.LD_Component;
import uk.ac.reload.editor.learningdesign.datamodel.LD_DataModel;
import uk.ac.reload.editor.learningdesign.datamodel.MetadataType;
import uk.ac.reload.editor.learningdesign.datamodel.components.roles.Learner;
import uk.ac.reload.editor.learningdesign.datamodel.components.roles.Learner_Grouping;
import uk.ac.reload.editor.learningdesign.datamodel.components.roles.Role;
import uk.ac.reload.jdom.XMLPath;
import uk.ac.reload.moonunit.learningdesign.LD_Core;


/**
 * Learning Design Conference Element
 *
 * @author Phillip Beauvoir
 * @version $Id: Conference.java,v 1.1 1998/03/26 15:42:28 ynsingh Exp $
 */
public class Conference
extends Service
{
    public static XMLPath XMLPATH = new XMLPath(XMLPATH_ROOT_LD).appendElementName("components/environments/environment/service/conference");
    
    static final String DESCRIPTION = "Conference Service.";
    
    /**
     * The Element bound to the ItemType
     */
    private ItemType _itemType;
    
    /**
     * MetadataType
     */
    private MetadataType _mdType;
    
    /**
     * Participants
     */
    private Role[] _participants;

    /**
     * Observers
     */
    private Role[] _observers;

    
    /**
     * Constructor with backing Element
     * @param ldDataModel The LD_DataModel
     * @param element The backing JDOM Element
     */
    public Conference(LD_DataModel ldDataModel, Element element) {
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
        // Ensure valid refs (there will be a blank ref created by inserting the <confernce> element)
        ensureAllReferences();
        
        // Ensure one participant
        ensureDefaultParticipant();
        
        // "conference-type" attribute
        ensureAttribute(getElement(), LD_Core.CONFERENCE_TYPE, "synchronous");
    }
    
    /**
     * Ensure that each reference type element references a Component. If not, then we will remove it.<p>
     * @return True if we made an adjustment
     */
    protected boolean ensureAllReferences() {
        boolean modified = ensureReferences(getElement(), LD_Core.PARTICIPANT, LD_Core.ROLE_REF, getLD_DataModel().getRoles_Grouping());
        modified |= ensureReferences(getElement(), LD_Core.OBSERVER, LD_Core.ROLE_REF, getLD_DataModel().getRoles_Grouping());
        modified |= ensureReferences(getElement(), LD_Core.CONFERENCE_MANAGER, LD_Core.ROLE_REF, getLD_DataModel().getRoles_Grouping());
        modified |= ensureReferences(getElement(), LD_Core.MODERATOR, LD_Core.ROLE_REF, getLD_DataModel().getRoles_Grouping());
        return modified;
    }

    /**
     * Ensure that no matter what happens there will always be at least one default
     * Participant - this will reference the first Learner Role, because there will always be
     * at least one Learner role.
     * @return True if we made an adjustment
     */
    protected boolean ensureDefaultParticipant() {
        boolean modified = false;
        
        // Get the <role-ref> child elements
        Role[] participants = getParticipants();
        
        // What? No Participants?!!!  Add Learner
        if(participants.length == 0) {
            // Get Learner number one
            Learner_Grouping learnerGrouping = getLD_DataModel().getRoles_Grouping().getLearner_Grouping();
            Learner learner = (Learner)learnerGrouping.getChildAt(0);
            if(learner != null) {
                addParticipant(learner);
                modified = true;
            }
        }
        
        return modified;
    }

    /**
     * @return The Conference Participants. These are the referenced Roles.
     */
    public Role[] getParticipants() {
        if(_participants == null) {
            Vector v = new Vector();
            
            Iterator it = getElement().getChildren(LD_Core.PARTICIPANT, getElement().getNamespace()).iterator();
            while(it.hasNext()) {
                Element element = (Element)it.next();
                String ref = element.getAttributeValue(LD_Core.ROLE_REF);
                LD_Component component = getLD_DataModel().getRoles_Grouping().getChildByIdentifer(ref);
                if(component != null) {
                    v.add(component);
                }
            }
            
            _participants = new Role[v.size()];
            v.copyInto(_participants);
        }
        
        return _participants;
    }
    
    /**
     * Add a Role reference as a Participant
     * @param role
     */
    public void addParticipant(Role role) {
        addRef(role, getElement(), LD_Core.PARTICIPANT, LD_Core.ROLE_REF);
        // reset 
        _participants = null;
    }
    
    /**
     * Remove a Role reference as a Participant
     * @param role
     */
    public void removeParticipant(Role role) {
        removeRef(role, getElement(), LD_Core.PARTICIPANT, LD_Core.ROLE_REF);
        // reset 
        _participants = null;
    }

    /**
     * @param role
     * @return True if role is a Participant in this Conference
     */
    public boolean isParticipant(Role role) {
        if(role == null) {
            return false;
        }
        
        String id = role.getIdentifier();
        if(id == null || "".equals(id)) {
            return false;
        }
        
        Role[] participants = getParticipants();
        for(int i = 0; i < participants.length; i++) {
            String id2 = participants[i].getIdentifier();
            if(id.equals(id2)) {
                return true;
            }
        }
        
        return false;
    }

    /**
     * @return True if there is only one Participant.
     * This is used to ensure that we don't remove the last Participant, i.e that there is always
     * at least one, as per the LD Schema.
     */
    public boolean isLastParticipant() {
        return getElement().getChildren(LD_Core.PARTICIPANT, getElement().getNamespace()).size() == 1;
    }

    /**
     * @return The Conference observers. These are the referenced Roles.
     */
    public Role[] getObservers() {
        if(_observers == null) {
            Vector v = new Vector();
            
            Iterator it = getElement().getChildren(LD_Core.OBSERVER, getElement().getNamespace()).iterator();
            while(it.hasNext()) {
                Element element = (Element)it.next();
                String ref = element.getAttributeValue(LD_Core.ROLE_REF);
                LD_Component component = getLD_DataModel().getRoles_Grouping().getChildByIdentifer(ref);
                if(component != null) {
                    v.add(component);
                }
            }
            
            _observers = new Role[v.size()];
            v.copyInto(_observers);
        }
        
        return _observers;
    }
    
    /**
     * Add a Role reference as an Observer
     * @param role
     */
    public void addObserver(Role role) {
        addRef(role, getElement(), LD_Core.OBSERVER, LD_Core.ROLE_REF);
        // reset 
        _observers = null;
    }
    
    /**
     * Remove a Role reference as an Observer
     * @param role
     */
    public void removeObserver(Role role) {
        removeRef(role, getElement(), LD_Core.OBSERVER, LD_Core.ROLE_REF);
        // reset 
        _observers = null;
    }

    /**
     * @param role
     * @return True if role is an observer in this Conference
     */
    public boolean isObserver(Role role) {
        if(role == null) {
            return false;
        }
        
        String id = role.getIdentifier();
        if(id == null || "".equals(id)) {
            return false;
        }
        
        Role[] observers = getObservers();
        for(int i = 0; i < observers.length; i++) {
            String id2 = observers[i].getIdentifier();
            if(id.equals(id2)) {
                return true;
            }
        }
        
        return false;
    }
    
    /**
     * @return The Conference manager Role or null.
     */
    public Role getManager() {
        Role manager = null;
        
        Element element = getElement().getChild(LD_Core.CONFERENCE_MANAGER, getElement().getNamespace());
        if(element != null) {
            String ref = element.getAttributeValue(LD_Core.ROLE_REF);
            manager = (Role)getLD_DataModel().getRoles_Grouping().getChildByIdentifer(ref);
        }
            
        return manager;
    }

    /**
     * Add a Role reference as a Manager
     * @param role
     */
    public void addManager(Role role) {
        // Remove the previous one first
        removeManager(getManager());
        
        addRef(role, getElement(), LD_Core.CONFERENCE_MANAGER, LD_Core.ROLE_REF);
    }
    
    /**
     * Remove a Role reference as a Manager
     * @param role
     */
    public void removeManager(Role role) {
        removeRef(role, getElement(), LD_Core.CONFERENCE_MANAGER, LD_Core.ROLE_REF);
    }

    /**
     * @param role
     * @return True if role is the Manager in this Conference
     */
    public boolean isManager(Role role) {
        if(role == null) {
            return false;
        }
        
        String id = role.getIdentifier();
        if(id == null || "".equals(id)) {
            return false;
        }
        
        Role manager = getManager();
        if(manager != null) {
            String id2 = manager.getIdentifier();
            if(id.equals(id2)) {
                return true;
            }
        }
        
        return false;
    }

    /**
     * @return The Conference Moderator Role or null.
     */
    public Role getModerator() {
        Role moderator = null;
        
        Element element = getElement().getChild(LD_Core.MODERATOR, getElement().getNamespace());
        if(element != null) {
            String ref = element.getAttributeValue(LD_Core.ROLE_REF);
            moderator = (Role)getLD_DataModel().getRoles_Grouping().getChildByIdentifer(ref);
        }
        
        return moderator;
    }

    /**
     * Add a Role reference as a Moderator
     * @param role
     */
    public void addModerator(Role role) {
        // Remove the previous one first
        removeModerator(getModerator());

        addRef(role, getElement(), LD_Core.MODERATOR, LD_Core.ROLE_REF);
    }
    
    /**
     * Remove a Role reference as a Moderator
     * @param role
     */
    public void removeModerator(Role role) {
        removeRef(role, getElement(), LD_Core.MODERATOR, LD_Core.ROLE_REF);
    }

    /**
     * @param role
     * @return True if role is the Moderator in this Conference
     */
    public boolean isModerator(Role role) {
        if(role == null) {
            return false;
        }
        
        String id = role.getIdentifier();
        if(id == null || "".equals(id)) {
            return false;
        }
        
        Role moderator = getModerator();
        if(moderator != null) {
            String id2 = moderator.getIdentifier();
            if(id.equals(id2)) {
                return true;
            }
        }
        
        return false;
    }

    /**
     * @return The ItemType
     */
    public ItemType getItemType() {
        if(_itemType == null) {
            _itemType = new ItemType(getDataElement(), "Conference", "Conference");
        }
        return _itemType;
    }

    /**
     * @return The MetadataType
     */
    public MetadataType getMetadataType() {
        if(_mdType == null) {
            _mdType = new MetadataType(getDataElement(), "Metadata", "Metadata for the Conference.");
        }
        return _mdType;
    }

    /**
     * @return A sensible default Title
     */
    public String getDefaultTitle() {
        return "Conference";
    }

    /* (non-Javadoc)
     * @see uk.ac.reload.editor.learningdesign.datamodel.ILD_IconInterface#getIcon()
     */
    public Icon getIcon() {
        return DweezilUIManager.getIcon(ICON_CONFERENCE);
    }

    /* (non-Javadoc)
     * @see uk.ac.reload.editor.learningdesign.datamodel.LD_Component#getXMLPath()
     */
    public XMLPath getXMLPath() {
        return XMLPATH;
    }

    //========================= Listener Events ==================================
    
	/**
     * We heard that a Role has been removed from the Data Model so we need to check
     * whether it was a component that we reference.  If it is, we will remove the component
     * and fire that we changed.
     */
    public void componentRemoved(DataComponent component) {
        if(component instanceof Role) {
            boolean modified = ensureAllReferences();
        	if(modified) {
        	    // Reset lists first
        	    _participants = null;
        	    _observers = null;
        	    
        	    // Now, ensure one participant
                ensureDefaultParticipant();
                
                // Tell listeners
        	    getDataModel().fireDataComponentChanged(this);
        	}
        }
    }

}
