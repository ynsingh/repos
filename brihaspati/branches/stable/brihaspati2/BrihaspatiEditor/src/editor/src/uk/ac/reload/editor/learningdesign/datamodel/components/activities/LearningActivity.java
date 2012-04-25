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
import uk.ac.reload.editor.datamodel.DataElement;
import uk.ac.reload.editor.learningdesign.datamodel.ItemModelType;
import uk.ac.reload.editor.learningdesign.datamodel.LD_DataModel;
import uk.ac.reload.editor.learningdesign.datamodel.MetadataType;
import uk.ac.reload.jdom.XMLPath;


/**
 * LearningActivity
 * 
 * @author Phillip Beauvoir
 * @version $Id: LearningActivity.java,v 1.1 1998/03/26 15:42:28 ynsingh Exp $
 */
public class LearningActivity
extends PrimaryActivity
{
    public static XMLPath XMLPATH = new XMLPath(XMLPATH_ROOT_LD).appendElementName("components/activities/learning-activity");

    static final String DESCRIPTION = "This section provides information about the Learning Activity.";
    
    static final String LO_TITLE = "Learning Objectives";
    static final String LO_DESCRIPTION = "This section describes the Learning Objectives of this Activity.";
    
    static final String PREREQ_TITLE = "Prerequisites";
    static final String PREREQ_DESCRIPTION = "This section describes the Prerequisites of this Activity.";

    /**
     * The Learning Objectives ItemModelType
     */
    private ItemModelType _learningObjectives;
    
    /**
     * The Pre-requisites ItemModelType
     */
    private ItemModelType _prerequisites;

    /**
     * The Description ItemModelType
     */
    private ItemModelType _description;

    /**
     * The Feedback Description ItemModelType
     */
    private ItemModelType _feedbackdescription;
    
    /**
     * MetadataType
     */
    private MetadataType _mdType;

    /**
     * Constructor with backing Element
     * @param ldDataModel The DataModel
     * @param element The backing JDOM XML Element
     */
    protected LearningActivity(LD_DataModel ldDataModel, Element element) {
        super(ldDataModel, element);
        setDescription(DESCRIPTION);
    }
    
    /**
     * @return The Learning Objectives ItemModelType
     */
    public ItemModelType getLearningObjectivesItemModelType() {
        if(_learningObjectives == null) {
            DataElement dataElement = new DataElement(getDataModel(), getElement(), new XMLPath("learning-objectives")); 
            _learningObjectives = new ItemModelType(dataElement, LO_TITLE, LO_DESCRIPTION);
        }
        return _learningObjectives; 
    }

    /**
     * @return The Pre-requisites ItemModelType
     */
    public ItemModelType getPrerequisitesItemModelType() {
        if(_prerequisites == null) {
            DataElement dataElement = new DataElement(getDataModel(), getElement(), new XMLPath("prerequisites")); 
            _prerequisites = new ItemModelType(dataElement, PREREQ_TITLE, PREREQ_DESCRIPTION);
        }
        return _prerequisites; 
    }

    /**
     * @return The MetadataType
     */
    public MetadataType getMetadataType() {
        if(_mdType == null) {
            _mdType = new MetadataType(getDataElement(), "Metadata", "Metadata for the Learning Activity.");
        }
        return _mdType;
    }

    /**
     * @return A sensible default Title
     */
    public String getDefaultTitle() {
        return "Learning Activity";
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
        return DweezilUIManager.getIcon(ICON_LEARNINGACTIVITY);
    }
}