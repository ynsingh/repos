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

import org.jdom.Element;

import uk.ac.reload.editor.datamodel.DataElement;
import uk.ac.reload.editor.learningdesign.datamodel.ItemModelType;
import uk.ac.reload.editor.learningdesign.datamodel.LD_DataModel;
import uk.ac.reload.jdom.XMLPath;


/**
 * Learning Activity and Support Activity type
 * 
 * @author Phillip Beauvoir
 * @version $Id: PrimaryActivity.java,v 1.1 1998/03/26 15:42:28 ynsingh Exp $
 */
public abstract class PrimaryActivity
extends Activity
{
    static final String DESCRIPTION_TITLE = "Description";
    static final String DESCRIPTION_DESCRIPTION = "The cue given to the user to describe the Activity to be performed.";

    static final String FEEDBACKDESCRIPTION_TITLE = "Completion Feedback Description";
    static final String FEEDBACKDESCRIPTION_DESCRIPTION = "May be used to provide additional information about the Completion of this Activity.";

    /**
     * The Description ItemModelType
     */
    private ItemModelType _description;

    /**
     * The Feedback Description ItemModelType
     */
    private ItemModelType _feedbackdescription;
    
    /**
     * Constructor with backing Element
     * @param ldDataModel The DataModel
     * @param element The backing JDOM XML Element
     */
    protected PrimaryActivity(LD_DataModel ldDataModel, Element element) {
        super(ldDataModel, element);
    }
    
    /**
     * @return The Description ItemModelType
     */
    public ItemModelType getDescriptionItemModelType() {
        if(_description == null) {
            DataElement dataElement = new DataElement(getDataModel(), getElement(), new XMLPath("activity-description")); 
            _description = new ItemModelType(dataElement, DESCRIPTION_TITLE, DESCRIPTION_DESCRIPTION);
        }
        return _description; 
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
     * Get a DataElement type for the "complete-activity" element.
     * Note - the DataElement is created afresh each time in case the bound element has been
     * deleted or created elsewhere.
     * @return The DataElement for the "complete-activity" element
     */
    public DataElement getCompleteActivityDataElement() {
        return new DataElement(getDataModel(), getElement(), new XMLPath("complete-activity"));
    }
    
    /**
     * Get a DataElement type for the "complete-activity/user-choice" element.
     * Note - the DataElement is created afresh each time in case the bound element has been
     * deleted or created elsewhere.
     * @return The DataElement for the "complete-activity/user-choice" element
     */
    public DataElement getUserChoiceDataElement() {
        return new DataElement(getDataModel(), getElement(), new XMLPath("complete-activity/user-choice"));
    }
    
    /**
     * Get a DataElement type for the "complete-activity/time-limit" element.
     * Note - the DataElement is created afresh each time in case the bound element has been
     * deleted or created elsewhere.
     * @return The DataElement for the "complete-activity/time-limit" element
     */
    public DataElement getTimeLimitDataElement() {
        return new DataElement(getDataModel(), getElement(), new XMLPath("complete-activity/time-limit"));
    }
    
    /**
     * Get a DataElement type for the "complete-activity/when-property-value-is-set" element.
     * Note - the DataElement is created afresh each time in case the bound element has been
     * deleted or created elsewhere.
     * @return The DataElement for the "complete-activity/when-property-value-is-set" element
     */
    public DataElement getWhenPropertyValueSetDataElement() {
        return new DataElement(getDataModel(), getElement(), new XMLPath("complete-activity/when-property-value-is-set"));
    }
    
}