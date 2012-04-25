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

package uk.ac.reload.editor.learningdesign.datamodel.components.activities;

import java.util.Iterator;
import java.util.Vector;

import org.jdom.Element;

import uk.ac.reload.editor.datamodel.DataComponent;
import uk.ac.reload.editor.datamodel.IDataComponentIcon;
import uk.ac.reload.editor.learningdesign.datamodel.LD_Component;
import uk.ac.reload.editor.learningdesign.datamodel.LD_DataModel;
import uk.ac.reload.editor.learningdesign.datamodel.components.environments.Environment;
import uk.ac.reload.editor.learningdesign.datamodel.components.roles.Role;
import uk.ac.reload.moonunit.learningdesign.LD_Core;


/**
 * Activity class
 * 
 * @author Phillip Beauvoir
 * @version $Id: Activity.java,v 1.1 1998/03/26 15:42:28 ynsingh Exp $
 */
public abstract class Activity
extends LD_Component 
implements IDataComponentIcon
{
    
    /**
     * Constructor with backing Element
     * @param ldDataModel The DataModel
     * @param element The backing JDOM XML Element
     */
    protected Activity(LD_DataModel ldDataModel, Element element) {
        setDataModel(ldDataModel);
        setElement(element);

        // Listen to the DataModel
        ldDataModel.addIDataModelListener(this);
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
     * @return The Environments.  These are the referenced Environments.
     */
    public Environment[] getEnvironments() {
        Vector v = new Vector();
        
        Iterator it = getElement().getChildren(LD_Core.ENVIRONMENT_REF, getElement().getNamespace()).iterator();
        while(it.hasNext()) {
            Element element = (Element)it.next();
            String ref = element.getAttributeValue(LD_Core.REF);
            LD_Component component = getLD_DataModel().getEnvironment_Grouping().getChildByIdentifer(ref);
            if(component != null) {
                v.add(component);
            }
        }
        Environment[] envs = new Environment[v.size()];
        v.copyInto(envs);
        return envs;
    }
    
    /**
     * Ensure that each reference type element references a Component. If not, then we will remove it.<p>
     * @return True if we made an adjustment
     */
    protected boolean ensureAllReferences() {
        return ensureReferences(getElement(), LD_Core.ENVIRONMENT_REF, LD_Core.REF, getLD_DataModel().getEnvironment_Grouping());
    }
    
    /**
     * Add a new Referenced Component
     * @param component The Component to reference
     */
    public void addRef(LD_Component component) {
        String elementName = getElementRefName(component);
        if(elementName != null) {
            addRef(component, getElement(), elementName, LD_Core.REF);
        }
    }
    
    /**
     * Remove a Referenced Component
     * @param component The Component to reference
     */
    public void removeRef(LD_Component component) {
        String elementName = getElementRefName(component);
        if(elementName != null) {
            removeRef(component, getElement(), elementName, LD_Core.REF);
        }
    }
    
    /**
     * Move a referenced Component up
     * @param component The Component to move up
     */
    public void moveRefUp(LD_Component component) {
        String elementName = getElementRefName(component);
        if(elementName != null) {
            moveRefUp(component, getElement(), elementName, LD_Core.REF);
        }
    }
    
    /**
     * Move a referenced Component down
     * @param component The Component to move up
     */
    public void moveRefDown(LD_Component component) {
        String elementName = getElementRefName(component);
        if(elementName != null) {
            moveRefDown(component, getElement(), elementName, LD_Core.REF);
        }
    }
    
    /**
     * @param component The Component to reference
     * @return A JDOM Element name ref for a component or null
     */
    private String getElementRefName(LD_Component component) {
        if(component instanceof Role) {
            return LD_Core.ROLE_REF;
        }
        else if(component instanceof Environment) {
            return LD_Core.ENVIRONMENT_REF;
        }
        else if(component instanceof LearningActivity) {
            return LD_Core.LEARNING_ACTIVITY_REF;
        }
        else if(component instanceof SupportActivity) {
            return LD_Core.SUPPORT_ACTIVITY_REF;
        }
        else if(component instanceof ActivityStructure) {
            return LD_Core.ACTIVITY_STRUCTURE_REF;
        }
        else {
            return null;
        }
    }
    
    /**
     * Move the bound element up.
     * Over-ride this.  Because we've put the Activities in subgroups, moving the element up one place
     * doesn't necessarily mean it will move above the previous sibling of the same type.
     */
    protected void moveElementUp() {
        moveElementUpSameType();
    }
    
    /**
     * Move the bound element down.
     * Over-ride this.  Because we've put the Activities in subgroups, moving the element down one place
     * doesn't necessarily mean it will move below the next sibling of the same type.
     */
    protected void moveElementDown() {
        moveElementDownSameType();
    }

    //========================= Listener Events ==================================
    
	/**
     * We heard that a DataComponent has been removed from the Data Model so we need to check
     * whether it was a component that we reference.  If it is, we will remove the component
     * and fire that we changed.
     */
    public void componentRemoved(DataComponent component) {
        boolean modified = ensureAllReferences();
        if(modified) {
            getDataModel().fireDataComponentChanged(this);
        }
    }
}
