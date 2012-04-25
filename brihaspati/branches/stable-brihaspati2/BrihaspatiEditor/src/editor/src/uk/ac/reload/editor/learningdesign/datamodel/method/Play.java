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
 * Play object
 * 
 * @author Phillip Beauvoir
 * @version $Id: Play.java,v 1.1 1998/03/26 15:40:32 ynsingh Exp $
 */
public class Play
extends LD_Component
implements IDataComponentIcon
{
    public static XMLPath XMLPATH = new XMLPath(XMLPATH_ROOT_LD).appendElementName("method/play");

    static final String DEFAULT_TITLE = "Play";
    static final String DESCRIPTION = "This section provides information about the Play.";
    
    static final String FEEDBACKDESCRIPTION_TITLE = "Completion Feedback Description";
    static final String FEEDBACKDESCRIPTION_DESCRIPTION = "May be used to provide additional information about the Completion of this Play.";

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
    public Play(LD_DataModel ldDataModel, Element element) {
        setDataModel(ldDataModel);
        setElement(element);
        setDescription(DESCRIPTION);
        addChildren();
    }
    
    /**
     * Add Child Act Components
     */
    protected void addChildren() {
        // Add <act> elements from XML DOM
        Iterator it = getElement().getChildren(LD_Core.ACT, getElement().getNamespace()).iterator();
        while(it.hasNext()) {
            Element child = (Element)it.next();
            Act act = new Act(getLD_DataModel(), child);
            addChild(act);
        }
    }

    /**
     * Over-ride to add a child element
     */
    public DataComponent addChildElement(String elementName, String title) {
        if(!LD_Core.ACT.equals(elementName)) {
            return null;
        }
        
        Act act = null;
        
        Element element = addChildElement(getElement(), elementName);
        if(element != null) {
            act = new Act(getLD_DataModel(), element);
            addChild(act);
            
            // Set Title if there is one
            if(title != null) {
                act.setTitle(title);
            }
            
            // Tell Listeners
            getDataModel().fireDataComponentAdded(act);
        }

        return act;
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
     * Over-ride this to set and ensure we have a <title> Element
     */
    public void setTitle(String title) {
        setTitleElement(title);
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
     * Get a DataElement type for the "complete-play" element.
     * Note - the DataElement is created afresh each time in case the bound element has been
     * deleted or created elsewhere.
     * @return The DataElement for the "complete-play" element
     */
    public DataElement getCompletePlayDataElement() {
        return new DataElement(getDataModel(), getElement(), new XMLPath("complete-play"));
    }

    /**
     * Get a DataElement type for the "complete-play/when-last-act-completed" element.
     * Note - the DataElement is created afresh each time in case the bound element has been
     * deleted or created elsewhere.
     * @return The DataElement for the "complete-play/when-last-act-completed" element
     */
    public DataElement getWhenLastActCompletedDataElement() {
        return new DataElement(getDataModel(), getElement(), new XMLPath("complete-play/when-last-act-completed"));
    }

    /**
     * Get a DataElement type for the "complete-play/time-limit" element.
     * Note - the DataElement is created afresh each time in case the bound element has been
     * deleted or created elsewhere.
     * @return The DataElement for the "complete-play/time-limit" element
     */
    public DataElement getTimeLimitDataElement() {
        return new DataElement(getDataModel(), getElement(), new XMLPath("complete-play/time-limit"));
    }
    
    /**
     * Get a DataElement type for the "complete-play/when-property-value-is-set" element.
     * Note - the DataElement is created afresh each time in case the bound element has been
     * deleted or created elsewhere.
     * @return The DataElement for the "complete-play/when-property-value-is-set" element
     */
    public DataElement getWhenPropertyValueSetDataElement() {
        return new DataElement(getDataModel(), getElement(), new XMLPath("complete-play/when-property-value-is-set"));
    }

    /* (non-Javadoc)
     * @see uk.ac.reload.editor.learningdesign.datamodel.LD_Component#canDelete()
     */
    public boolean canDelete() {
        // Don't allow last Play to be removed
        return getParent() == null ? true : ((Method)getParent()).getChildren().length > 1;
    }

    /* (non-Javadoc)
     * @see uk.ac.reload.editor.learningdesign.datamodel.ILD_IconInterface#getIcon()
     */
    public Icon getIcon() {
        return DweezilUIManager.getIcon(ICON_PLAY);
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
            _mdType = new MetadataType(getDataElement(), "Metadata", "Metadata for the Play.");
        }
        return _mdType;
    }

}
