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

import javax.swing.Icon;

import org.jdom.Element;

import uk.ac.reload.dweezil.util.DweezilUIManager;
import uk.ac.reload.editor.datamodel.DataElement;
import uk.ac.reload.editor.learningdesign.datamodel.ItemModelType;
import uk.ac.reload.editor.learningdesign.datamodel.LD_Component;
import uk.ac.reload.editor.learningdesign.datamodel.LD_DataModel;
import uk.ac.reload.editor.learningdesign.datamodel.MetadataType;
import uk.ac.reload.jdom.XMLPath;
import uk.ac.reload.moonunit.learningdesign.LD_Core;


/**
 * ActivityStructure
 * 
 * @author Phillip Beauvoir
 * @version $Id: ActivityStructure.java,v 1.1 1998/03/26 15:42:28 ynsingh Exp $
 */
public class ActivityStructure
extends Activity 
{
    public static XMLPath XMLPATH = new XMLPath(XMLPATH_ROOT_LD).appendElementName("components/activities/activity-structure");

    static final String DESCRIPTION = "This section provides information about the Activity Structure.";
    
    /**
     * Information Item Model Type
     */
    private ItemModelType _informationItemModelType;
    
    /**
     * MetadataType
     */
    private MetadataType _mdType;

    private static final String INFORMATION_TITLE = "Information";
    private static final String INFORMATION_DESCRIPTION = "May be used to provide additional information about the Activity Structure.";

    /**
     * Constructor with backing Element
     * @param ldDataModel The DataModel
     * @param element The backing JDOM XML Element
     */
    protected ActivityStructure(LD_DataModel ldDataModel, Element element) {
        super(ldDataModel, element);
        setDescription(DESCRIPTION);
    }
    
    /**
     * Over-ride to ensure references
     * DON'T CALL THIS IN THE CONSTRUCTOR OR THERE'LL BE INFINITE RECURSION!
     */
    protected boolean ensureAllReferences() {
        boolean modified = super.ensureAllReferences();
        modified |= ensureReferences(getElement(), LD_Core.LEARNING_ACTIVITY_REF, LD_Core.REF, getLD_DataModel().getActivities_Grouping().getLearningActivity_Grouping());
        modified |= ensureReferences(getElement(), LD_Core.SUPPORT_ACTIVITY_REF, LD_Core.REF, getLD_DataModel().getActivities_Grouping().getSupportActivity_Grouping());
        modified |= ensureReferences(getElement(), LD_Core.ACTIVITY_STRUCTURE_REF, LD_Core.REF, getLD_DataModel().getActivities_Grouping().getActivityStructure_Grouping());
        return modified;
    }

    /**
     * Get the referenced Activities and UOLs.  These have to be in sequence order.
     * @return The referenced Activities and UOLs.
     */
    public LD_Component[] getReferencedComponents() {
        Vector v = new Vector();
        
        // Iterate thru all child elements and pick the ones we want
        Iterator it = getElement().getChildren().iterator();
        while(it.hasNext()) {
            Element element = (Element)it.next();
            // Our namespace
            if(element.getNamespace().equals(getElement().getNamespace())) {
                LD_Component component = null;
                String ref = element.getAttributeValue(LD_Core.REF);

                // Learning Activities
                if(element.getName().equals(LD_Core.LEARNING_ACTIVITY_REF)) {
                    component = getLD_DataModel().getActivities_Grouping().getLearningActivity_Grouping().getChildByIdentifer(ref);
                }
                // Support Activities
                else if(element.getName().equals(LD_Core.SUPPORT_ACTIVITY_REF)) {
                    component = getLD_DataModel().getActivities_Grouping().getSupportActivity_Grouping().getChildByIdentifer(ref);
                }
                // Activity Structures
                else if(element.getName().equals(LD_Core.ACTIVITY_STRUCTURE_REF)) {
                    component = getLD_DataModel().getActivities_Grouping().getActivityStructure_Grouping().getChildByIdentifer(ref);
                }
                // TO DO get UOL hrefs

                if(component != null) {
                    v.add(component);
                }
            }
        }
        
        LD_Component[] components = new LD_Component[v.size()];
        v.copyInto(components);
        return components;
    }
    
    /**
     * @return The child information class
     */
    public ItemModelType getInformationItemModelType() {
        if(_informationItemModelType == null) {
            DataElement dataElement = new DataElement(getDataModel(), getElement(), new XMLPath("information")); 
            _informationItemModelType = new ItemModelType(dataElement, INFORMATION_TITLE, INFORMATION_DESCRIPTION);
        }
        return _informationItemModelType; 
    }

    /**
     * @return The MetadataType
     */
    public MetadataType getMetadataType() {
        if(_mdType == null) {
            _mdType = new MetadataType(getDataElement(), "Metadata", "Metadata for the Activity Structure.");
        }
        return _mdType;
    }

    /**
     * @return A sensible default Title
     */
    public String getDefaultTitle() {
        return "Activity Structure";
    }

    /* (non-Javadoc)
     * @see uk.ac.reload.editor.learningdesign.datamodel.LD_Component#getXMLPath()
     */
    public XMLPath getXMLPath() {
        return XMLPATH;
    }
    
    /* (non-Javadoc)
     * @see uk.ac.reload.editor.learningdesign.datamodel.ILD_IconInterface#getIcon()
     */
    public Icon getIcon() {
        return DweezilUIManager.getIcon(ICON_ACTIVITYSTRUCTURE);
    }
    
}
