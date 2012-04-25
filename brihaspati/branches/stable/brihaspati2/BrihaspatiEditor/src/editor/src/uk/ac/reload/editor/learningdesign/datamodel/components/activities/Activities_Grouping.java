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

import javax.swing.Icon;

import org.jdom.Element;

import uk.ac.reload.dweezil.util.DweezilUIManager;
import uk.ac.reload.editor.datamodel.DataComponent;
import uk.ac.reload.editor.datamodel.IDataComponentIcon;
import uk.ac.reload.editor.learningdesign.datamodel.LD_DataModel;
import uk.ac.reload.editor.learningdesign.datamodel.LD_Grouping;
import uk.ac.reload.jdom.XMLPath;
import uk.ac.reload.moonunit.SchemaDocument;
import uk.ac.reload.moonunit.schema.SchemaElement;


/**
 * Grouping Component for Activities
 *
 * @author Phillip Beauvoir
 * @version $Id: Activities_Grouping.java,v 1.1 1998/03/26 15:42:28 ynsingh Exp $
 */
public class Activities_Grouping
extends LD_Grouping
implements IDataComponentIcon
{
    public static XMLPath ACTIVITIES_XMLPATH = new XMLPath(XMLPATH_ROOT_LD).appendElementName("components/activities");
    
    /**
     * Learning Activity Grouping
     */
    private LearningActivity_Grouping _learningActivityGrouping;
    
    /**
     * Support Activity Grouping
     */
    private SupportActivity_Grouping _supportActivityGrouping;
    
    /**
     * Activity Structure Grouping
     */
    private ActivityStructure_Grouping _activityStructureGrouping;
    

    /**
     * Constructor
     * @param ldDataModel
     */
    public Activities_Grouping(LD_DataModel ldDataModel) {
        setDataModel(ldDataModel);
        
        setTitle("Activities");
        setDescription("Activities in the Learning Design.");
        
        // Learning Activity Grouping
        _learningActivityGrouping = new LearningActivity_Grouping(ldDataModel, this);
        addChild(_learningActivityGrouping);
        
        // Support Activity Grouping
        _supportActivityGrouping = new SupportActivity_Grouping(ldDataModel, this);
        addChild(_supportActivityGrouping);

        // Activity Structure Grouping
        _activityStructureGrouping = new ActivityStructure_Grouping(ldDataModel, this);
        addChild(_activityStructureGrouping);
        
        // Listen to Component Changes
        ldDataModel.addIDataModelListener(this);
    }
    
    /**
     * Ensure and create if need be the <activities> element.<p>
     * @return The <activities> element
     */
    protected Element ensureActivitiesElement() {
        Element elementActivities = getActivitiesElement();
        if(elementActivities == null) {
            // Add <activities> element as per Schema to the XML DOM
            SchemaDocument schemaDocument = getDataModel().getSchemaDocument();
            SchemaElement schemaElement = (SchemaElement)schemaDocument.getSchemaController().getSchemaNode(ACTIVITIES_XMLPATH);
            elementActivities = schemaDocument.addElementUniqueBySchema(this, schemaElement, false);
            
            // Bind the <activities> Element
            setElement(elementActivities);
            getLearningActivity_Grouping().setElement(elementActivities);
            getSupportActivity_Grouping().setElement(elementActivities);
            getActivityStructure_Grouping().setElement(elementActivities);
        }
        
        return elementActivities;
    }

    /**
     * @return The <activities> element from the XML DOM.  This may be null if not present.
     */
    public Element getActivitiesElement() {
        SchemaDocument schemaDocument = getDataModel().getSchemaDocument();
        return schemaDocument.getElement(ACTIVITIES_XMLPATH);
    }
    
    /**
     * @return Learning Activity Grouping
     */
    public LearningActivity_Grouping getLearningActivity_Grouping() {
        return _learningActivityGrouping;
    }

    /**
     * @return The Support Activity Grouping
     */
    public SupportActivity_Grouping getSupportActivity_Grouping() {
        return _supportActivityGrouping;
    }

    /**
     * @return Activity Structure Grouping
     */
    public ActivityStructure_Grouping getActivityStructure_Grouping() {
        return _activityStructureGrouping;
    }

    /* (non-Javadoc)
     * @see uk.ac.reload.editor.learningdesign.datamodel.ILD_IconInterface#getIcon()
     */
    public Icon getIcon() {
        return DweezilUIManager.getIcon(ICON_ACTIVITIES);
    }
    
    //========================= Listener Events ==================================
    
	/**
     * We heard that an Activity has been removed from the Data Model
     */
    public void componentRemoved(DataComponent component) {
        // If there are no activity elements, then remove the parent <activities> element
        if(component instanceof Activity && !getLearningActivity_Grouping().hasChildren()
                && !getSupportActivity_Grouping().hasChildren()
                && !getActivityStructure_Grouping().hasChildren()) {
            Element elementActivities = getActivitiesElement();
            if(elementActivities != null) {
                elementActivities.getParent().getContent().remove(elementActivities);
            }
        }
    }
}
