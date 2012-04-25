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

package uk.ac.reload.editor.learningdesign.datamodel;

import org.jdom.Element;

import uk.ac.reload.editor.datamodel.DataComponent;
import uk.ac.reload.editor.datamodel.DataElement;
import uk.ac.reload.editor.datamodel.DataModel;
import uk.ac.reload.editor.learningdesign.datamodel.components.activities.Activities_Grouping;
import uk.ac.reload.editor.learningdesign.datamodel.components.environments.Environment;
import uk.ac.reload.editor.learningdesign.datamodel.components.environments.Environments_Grouping;
import uk.ac.reload.editor.learningdesign.datamodel.components.roles.Roles_Grouping;
import uk.ac.reload.editor.learningdesign.datamodel.method.Method;
import uk.ac.reload.editor.learningdesign.datamodel.resources.LD_Resources;
import uk.ac.reload.editor.learningdesign.xml.LearningDesign;
import uk.ac.reload.jdom.XMLPath;



/**
 * 2nd level DataModel
 *
 * @author Phillip Beauvoir
 * @version $Id: LD_DataModel.java,v 1.1 1998/03/26 15:32:14 ynsingh Exp $
 */
public class LD_DataModel
extends DataModel
{
    
    static final String LO_TITLE = "Learning Objectives";
    static final String LO_DESCRIPTION = "This section describes the Learning Objectives of the Learning Design.";
    
    static final String PREREQ_TITLE = "Prerequisites";
    static final String PREREQ_DESCRIPTION = "This section describes the Prerequisites for the Learning Design.";
    
    /**
     * Learning Objectives
     */
    private ItemModelType _learningObjectivesItemModelType;
    
    /**
     * Pre-requisites
     */
    private ItemModelType _prerequisitesItemModelType;

    /**
     * The Roles Grouping
     */
    private Roles_Grouping _rolesGrouping;
    
    /**
     * The Environments Grouping
     */
    private Environments_Grouping _envsGrouping;

    /**
     * The Activities Grouping
     */
    private Activities_Grouping _activitiesGrouping;
    
    /**
     * The Method
     */
    private Method _method;
    
    /**
     * The Resources
     */
    private LD_Resources _resources;
    
    /**
     * MetadataType
     */
    private MetadataType _mdType;


    
    /**
     * Constructor
     * @param ld The LearningDesign XML Document
     */
    public LD_DataModel(LearningDesign ld) {
        super(ld);
    }
    
    /**
     * @return The LearningDesign XML Document
     */
    public LearningDesign getLearningDesign() {
        return (LearningDesign)getSchemaDocument();
    }
    
    /**
     * @return The Learning Objectives ItemModelType
     */
    public ItemModelType getLearningObjectives() {
        if(_learningObjectivesItemModelType == null) {
    	    // We're working on the root element
    	    Element rootElement = getLearningDesign().getLDElement();
            DataElement dataElement = new DataElement(this, rootElement, new XMLPath("learning-objectives")); 
            _learningObjectivesItemModelType = new ItemModelType(dataElement, LO_TITLE, LO_DESCRIPTION);
        }
        
        return _learningObjectivesItemModelType; 
    }
    
    /**
     * @return The Prerequisites ItemModelType
     */
    public ItemModelType getPrerequisites() {
        if(_prerequisitesItemModelType == null) {
    	    // We're working on the root element
            Element rootElement = getLearningDesign().getLDElement();
            DataElement dataElement = new DataElement(this, rootElement, new XMLPath("prerequisites")); 
            _prerequisitesItemModelType = new ItemModelType(dataElement, PREREQ_TITLE, PREREQ_DESCRIPTION);
        }
        
        return _prerequisitesItemModelType; 
    }

    /**
     * @return The Roles Grouping.
     */
    public Roles_Grouping getRoles_Grouping() {
        if(_rolesGrouping == null) {
            _rolesGrouping = new Roles_Grouping(this);
        }
        
        return _rolesGrouping;
    }

    /**
     * @return The Environments Grouping.
     */
    public Environments_Grouping getEnvironment_Grouping() {
        if(_envsGrouping == null) {
            _envsGrouping = new Environments_Grouping(this);
        }

        return _envsGrouping;
    }
    
    /**
     * @return The Activities Grouping.
     */
    public Activities_Grouping getActivities_Grouping() {
        if(_activitiesGrouping == null) {
            _activitiesGrouping = new Activities_Grouping(this);
        }

        return _activitiesGrouping;
    }
    
    /**
     * @return The Method Grouping.
     */
    public Method getMethod() {
        if(_method == null) {
            _method = new Method(this);
        }
        
        return _method;
    }
    
    /**
     * @return The LD Resources
     */
    public LD_Resources getResources() {
        if(_resources == null) {
            _resources = new LD_Resources(this);
        }
        
        return _resources;
    }
    
    /**
     * @return The MetadataType
     */
    public MetadataType getMetadataType() {
        if(_mdType == null) {
            DataElement dataElement = new DataElement(this, getLearningDesign().getLDElement());
            _mdType = new MetadataType(dataElement, "Metadata", "Metadata for the Learning Design");
        }
        return _mdType;
    }

    /**
     * @param index
     * @return A child component by its reference id or null if not found 
     */
    public LD_Component getChildByIdentifer(String refid) {
        // Really we should have a lookup table - next version!
        
        // Learning Activity
        LD_Component component = getActivities_Grouping().getLearningActivity_Grouping().getChildByIdentifer(refid);
        if(component != null) {
            return component;
        }
        
        // Support Activity
        component = getActivities_Grouping().getSupportActivity_Grouping().getChildByIdentifer(refid);
        if(component != null) {
            return component;
        }
        
        // Activity Structure
        component = getActivities_Grouping().getActivityStructure_Grouping().getChildByIdentifer(refid);
        if(component != null) {
            return component;
        }
        
        // Environment
        component = getEnvironment_Grouping().getChildByIdentifer(refid);
        if(component != null) {
            return component;
        }
        
        // Learning Object
        DataComponent[] components = getEnvironment_Grouping().getChildren();
        for(int i = 0; i < components.length; i++) {
            component = ((Environment)components[i]).getLearningObject_Grouping().getChildByIdentifer(refid);
            if(component != null) {
                return component;
            }
        }
        
        // Services
        for(int i = 0; i < components.length; i++) {
            component = ((Environment)components[i]).getService_Grouping().getChildByIdentifer(refid);
            if(component != null) {
                return component;
            }
        }
        
        return null;
    }

    public void destroy() {
        super.destroy();
        
        _learningObjectivesItemModelType = null;
        _prerequisitesItemModelType = null;
        _rolesGrouping = null;
        _envsGrouping = null;
        _activitiesGrouping = null;
        _method = null;
        _resources = null;
    }
}
