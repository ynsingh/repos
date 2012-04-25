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
import uk.ac.reload.editor.learningdesign.xml.LearningDesign;
import uk.ac.reload.jdom.XMLPath;
import uk.ac.reload.moonunit.learningdesign.LD_Core;
import uk.ac.reload.moonunit.schema.SchemaElement;


/**
 * Method class
 * 
 * @author Phillip Beauvoir
 * @version $Id: Method.java,v 1.1 1998/03/26 15:40:32 ynsingh Exp $
 */
public class Method
extends LD_Component
implements IDataComponentIcon
{
    public static XMLPath XMLPATH = new XMLPath(XMLPATH_ROOT_LD).appendElementName("method");

    static final String TITLE = "Method";
    static final String DESCRIPTION = "This section provides information about the Method";
    
    static final String FEEDBACKDESCRIPTION_TITLE = "Completion Feedback Description";
    static final String FEEDBACKDESCRIPTION_DESCRIPTION = "May be used to provide additional information about the Completion of this Method.";

    /**
     * The Feedback Description ItemModelType
     */
    private ItemModelType _feedbackdescription;

    /**
     * Constructor
     * @param ldDataModel
     */
    public Method(LD_DataModel ldDataModel) {
        setDataModel(ldDataModel);
        
        setTitle(TITLE);
        setDescription(DESCRIPTION);

        // Do this first - make sure there is a <method> Element in the XML DOM
        // This will automatically add one Play
        LearningDesign ld = ldDataModel.getLearningDesign();
        Element elementMethod = ld.getElement(XMLPATH);
        if(elementMethod == null) {
            SchemaElement schemaElement = (SchemaElement)ld.getSchemaController().getSchemaNode(XMLPATH);
            elementMethod = ld.addElementUniqueBySchema(this, schemaElement, false);
        }
        
        setElement(elementMethod);
        
        addChildren();
        
        // Listen to the DataModel
        ldDataModel.addIDataModelListener(this);
    }
    
    /**
     * Add Child Components - these will be Plays
     */
    protected void addChildren() {
        // Add <play> elements from XML DOM
        Iterator it = getElement().getChildren(LD_Core.PLAY, getElement().getNamespace()).iterator();
        while(it.hasNext()) {
            Element child = (Element)it.next();
            Play play = new Play(getLD_DataModel(), child);
            addChild(play);
        }
    }

    /**
     * Over-ride to add a child element
     */
    public DataComponent addChildElement(String elementName, String title) {
        if(!LD_Core.PLAY.equals(elementName)) {
            return null;
        }
        
        Play play = null;
        
        Element element = addChildElement(getElement(), elementName);
        if(element != null) {
            play = new Play(getLD_DataModel(), element);
            addChild(play);
            
            // Set Title if there is one
            if(title != null) {
                play.setTitle(title);
            }
            
            // Tell Listeners
            getDataModel().fireDataComponentAdded(play);
        }

        return play;
    }
    
    /**
     * @return The Plays Completed for "complete-unit-of-learning/when-play-completed".
     * These are referenced Plays.
     */
    public Play[] getPlaysCompleted() {
        Vector v = new Vector();
        
        Element elementCompleteUOL = getElement().getChild(LD_Core.COMPLETE_UNIT_OF_LEARNING, getElement().getNamespace());
        if(elementCompleteUOL != null) {
            Iterator it = elementCompleteUOL.getChildren(LD_Core.WHEN_PLAY_COMPLETED, elementCompleteUOL.getNamespace()).iterator();
            while(it.hasNext()) {
                Element element = (Element)it.next();
                String ref = element.getAttributeValue(LD_Core.REF);
                LD_Component component = getChildByIdentifer(ref);
                if(component != null) {
                    v.add(component);
                }
            }
        }
        
        Play[] plays = new Play[v.size()];
        v.copyInto(plays);
        return plays;
    }

    /**
     * Add a Play reference for "complete-unit-of-learning/when-play-completed"
     * @param play The Play to reference
     */
    public void addPlayCompleted(Play play) {
        DataElement completeUnitOfLearning = getCompleteUnitOfLearningDataElement();
        
        // Ensure "complete-unit-of-learning" element exists
        Element element = completeUnitOfLearning.createElement();

        // Add it
        addRef(play, element, LD_Core.WHEN_PLAY_COMPLETED, LD_Core.REF);
    }

    /**
     * Remove a a Play reference for "complete-unit-of-learning/when-play-completed"
     * @param play The Play to reference
     */
    public void removePlayCompleted(Play play) {
        DataElement completeUnitOfLearning = getCompleteUnitOfLearningDataElement();
        removeRef(play, completeUnitOfLearning.getElement(), LD_Core.WHEN_PLAY_COMPLETED, LD_Core.REF);
        
        // If last one then remove "complete-unit-of-learning" element
        if(getPlaysCompleted().length == 0) {
            completeUnitOfLearning.deleteElement();
        }
    }

    /**
     * Move a referenced Play up
     * @param play The Play to reference
     */
    public void movePlayCompletedUp(Play play) {
        DataElement completeUnitOfLearning = getCompleteUnitOfLearningDataElement();
        moveRefUp(play, completeUnitOfLearning.getElement(), LD_Core.WHEN_PLAY_COMPLETED, LD_Core.REF);
    }
    
    /**
     * Move a referenced Play down
     * @param play The Play to reference
     */
    public void movePlayCompletedDown(Play play) {
        DataElement completeUnitOfLearning = getCompleteUnitOfLearningDataElement();
        moveRefDown(play, completeUnitOfLearning.getElement(), LD_Core.WHEN_PLAY_COMPLETED, LD_Core.REF);
    }

    /**
     * Ensure that each reference type element references a Component. If not, then we will remove it.<p>
     * @return True if we made an adjustment
     */
    protected boolean ensureAllReferences() {
        DataElement completeUnitOfLearning = getCompleteUnitOfLearningDataElement();
        Element parent = completeUnitOfLearning.getElement();
        if(parent == null) {
            return false;
        }

        boolean modified = ensureReferences(parent, LD_Core.WHEN_PLAY_COMPLETED, LD_Core.REF, this);
        
        // If no Play refs then remove "complete-unit-of-learning" element
        if(getPlaysCompleted().length == 0) {
            completeUnitOfLearning.deleteElement();
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
     * Get a DataElement type for the "complete-unit-of-learning" element.
     * Note - the DataElement is created afresh each time in case the bound element has been
     * deleted or created elsewhere.
     * @return The DataElement for the "complete-unit-of-learning" element
     */
    public DataElement getCompleteUnitOfLearningDataElement() {
        return new DataElement(getDataModel(), getElement(), new XMLPath("complete-unit-of-learning"));
    }

    /**
     * Get a DataElement type for the "complete-unit-of-learning/when-play-completed" element.
     * Note - the DataElement is created afresh each time in case the bound element has been
     * deleted or created elsewhere.
     * @return The DataElement for the "complete-unit-of-learning/when-play-completed" element
     */
    public DataElement getWhenPlayCompletedDataElement() {
        return new DataElement(getDataModel(), getElement(), new XMLPath("complete-unit-of-learning/when-play-completed"));
    }

    /**
     * Get a DataElement type for the "complete-unit-of-learning/time-limit" element.
     * Note - the DataElement is created afresh each time in case the bound element has been
     * deleted or created elsewhere.
     * @return The DataElement for the "complete-unit-of-learning/time-limit" element
     */
    public DataElement getTimeLimitDataElement() {
        return new DataElement(getDataModel(), getElement(), new XMLPath("complete-unit-of-learning/time-limit"));
    }
    
    /**
     * Get a DataElement type for the "complete-unit-of-learning/when-property-value-is-set" element.
     * Note - the DataElement is created afresh each time in case the bound element has been
     * deleted or created elsewhere.
     * @return The DataElement for the "complete-unit-of-learning/when-property-value-is-set" element
     */
    public DataElement getWhenPropertyValueSetDataElement() {
        return new DataElement(getDataModel(), getElement(), new XMLPath("complete-unit-of-learning/when-property-value-is-set"));
    }

    /* (non-Javadoc)
     * @see uk.ac.reload.editor.learningdesign.datamodel.LD_Component#canDelete()
     */
    public boolean canDelete() {
        return false;
    }

    /* (non-Javadoc)
     * @see uk.ac.reload.editor.datamodel.DataComponent#canMoveUp()
     */
    public boolean canMoveUp() {
        return false;
    }

    /* (non-Javadoc)
     * @see uk.ac.reload.editor.datamodel.DataComponent#canMoveDown()
     */
    public boolean canMoveDown() {
        return false;
    }

    /* (non-Javadoc)
     * @see uk.ac.reload.editor.learningdesign.datamodel.ILD_IconInterface#getIcon()
     */
    public Icon getIcon() {
        return DweezilUIManager.getIcon(ICON_METHOD);
    }

    /* (non-Javadoc)
     * @see uk.ac.reload.editor.learningdesign.datamodel.LD_Component#getXMLPath()
     */
    public XMLPath getXMLPath() {
        return XMLPATH;
    }

    //========================= Listener Events ==================================
    
	/**
     * We heard that a Play has been removed from the Data Model so we need to check
     * whether it was a Play that we reference.  If it is, we will remove the component
     * and fire that we changed.
     */
    public void componentRemoved(DataComponent component) {
        if(component instanceof Play) {
            boolean modified = ensureAllReferences();
            if(modified) {
                getDataModel().fireDataComponentChanged(this);
            }
        }
    }
}
