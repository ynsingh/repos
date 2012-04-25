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

import javax.swing.Icon;

import org.jdom.Element;

import uk.ac.reload.dweezil.util.DweezilUIManager;
import uk.ac.reload.editor.datamodel.DataComponent;
import uk.ac.reload.editor.datamodel.IDataComponentIcon;
import uk.ac.reload.editor.learningdesign.datamodel.LD_DataModel;
import uk.ac.reload.editor.learningdesign.datamodel.LD_Grouping;
import uk.ac.reload.moonunit.learningdesign.LD_Core;


/**
 * ActivityStructure_Grouping
 *
 * @author Phillip Beauvoir
 * @version $Id: ActivityStructure_Grouping.java,v 1.1 1998/03/26 15:42:28 ynsingh Exp $
 */
public class ActivityStructure_Grouping
extends LD_Grouping
implements IDataComponentIcon
{
    static final String TITLE = "Activity Structures";
    static final String DESCRIPTION = "Add Activity Structures to the Learning Design.";
     
    /**
     * The Parent Grouping
     */
    private Activities_Grouping _activitiesGrouping;

    /**
     * Constructor
     * @param ldDataModel
     */
    public ActivityStructure_Grouping(LD_DataModel ldDataModel, Activities_Grouping activitiesGrouping) {
        setDataModel(ldDataModel);
        _activitiesGrouping = activitiesGrouping;
        
        setTitle(TITLE);
        setDescription(DESCRIPTION);
        
        addChildren();
    }
    
    /**
     * Add Child Components
     */
    protected void addChildren() {
        // Try for the <activities> element
        Element elementActivities = _activitiesGrouping.getActivitiesElement();
        
        if(elementActivities != null) {
            // Bind the <activities> Element
            setElement(elementActivities);

            // Add <activity-structure> elements from XML DOM
            Iterator it = elementActivities.getChildren(LD_Core.ACTIVITY_STRUCTURE, elementActivities.getNamespace()).iterator();
            while(it.hasNext()) {
                Element child = (Element)it.next();
                ActivityStructure as = new ActivityStructure(getLD_DataModel(), child);
                addChild(as);
            }
        }
    }

    /**
     * Over-ride to add a child element
     */
    public DataComponent addChildElement(String elementName, String title) {
        if(!LD_Core.ACTIVITY_STRUCTURE.equals(elementName)) {
            return null;
        }
        
        ActivityStructure as = null;
        
        // Get the <activities> Element
        Element elementActivities = _activitiesGrouping.getActivitiesElement();
        // If there isn't one we will create one
        if(elementActivities == null) {
            elementActivities = _activitiesGrouping.ensureActivitiesElement();
        }
        
        // Create a new Element
        Element element = addChildElement(getElement(), elementName);

        // Now create Component
        if(element != null) {
            as = new ActivityStructure(getLD_DataModel(), element);
            addChild(as);
            
            // Set Title if there is one
            if(title != null) {
                as.setTitle(title);
            }
            
            // Tell Listeners
            getDataModel().fireDataComponentAdded(as);
        }

        return as;
    }

    /* (non-Javadoc)
     * @see uk.ac.reload.editor.learningdesign.datamodel.ILD_IconInterface#getIcon()
     */
    public Icon getIcon() {
        return DweezilUIManager.getIcon(ICON_ACTIVITYSTRUCTURES);
    }
}
