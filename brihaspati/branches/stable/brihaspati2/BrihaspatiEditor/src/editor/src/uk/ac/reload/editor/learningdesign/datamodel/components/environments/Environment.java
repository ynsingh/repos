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

import java.util.Vector;

import javax.swing.Icon;

import org.jdom.Element;

import uk.ac.reload.dweezil.util.DweezilUIManager;
import uk.ac.reload.editor.datamodel.DataComponent;
import uk.ac.reload.editor.datamodel.IDataComponentIcon;
import uk.ac.reload.editor.learningdesign.datamodel.LD_Component;
import uk.ac.reload.editor.learningdesign.datamodel.LD_DataModel;
import uk.ac.reload.editor.learningdesign.datamodel.MetadataType;
import uk.ac.reload.jdom.XMLPath;
import uk.ac.reload.moonunit.learningdesign.LD_Core;

/**
 * Learning Design Environment Component
 *
 * @author Phillip Beauvoir
 * @version $Id: Environment.java,v 1.1 1998/03/26 15:42:28 ynsingh Exp $
 */
public class Environment
extends LD_Component
implements IDataComponentIcon
{
    public static XMLPath XMLPATH = new XMLPath(XMLPATH_ROOT_LD).appendElementName("components/environments/environment");
    
    static final String DESCRIPTION = "An Environment can contain any number of Learning Objects, Services and references to other Environments.";
    
    /**
     * Learning Object Grouping
     */
    private LearningObject_Grouping _learningObjectGrouping;

    /**
     * Environment Ref Grouping
     */
    private EnvironmentRef_Grouping _envRefGrouping;

    /**
     * Service Grouping
     */
    private Service_Grouping _serviceGrouping;

    /**
     * MetadataType
     */
    private MetadataType _mdType;

    /**
     * Default Constructor
     */
    public Environment() {
    }

    /**
     * Constructor with backing Element
     * @param ldDataModel The DataModel
     * @param element The backing JDOM XML Element
     */
    public Environment(LD_DataModel ldDataModel, Element element) {
        setDataModel(ldDataModel);
        setElement(element);
        setDescription(DESCRIPTION);
        
        // Learning Object Grouping
        _learningObjectGrouping = new LearningObject_Grouping(ldDataModel, this);
        addChild(_learningObjectGrouping);

        // Service Grouping
        _serviceGrouping = new Service_Grouping(ldDataModel, this);
        addChild(_serviceGrouping);

        // Environment Ref Grouping
        _envRefGrouping = new EnvironmentRef_Grouping(ldDataModel, this);
        addChild(_envRefGrouping);
    }
    
    /**
     * Over-ride this to set and ensure we have a <title> Element
     */
    public void setTitle(String title) {
        setTitleElement(title);
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
     * @return The EnvironmentRef_Grouping
     */
    public EnvironmentRef_Grouping getEnvironmentRef_Grouping() {
        return _envRefGrouping;
    }
    
    /**
     * @return The LearningObject_Grouping
     */
    public LearningObject_Grouping getLearningObject_Grouping() {
        return _learningObjectGrouping;
    }

    /**
     * @return The Service_Grouping
     */
    public Service_Grouping getService_Grouping() {
        return _serviceGrouping;
    }

    
    /**
     * @return All Environments that we can add as an Environment Ref.
     * i.e All Environments that we don't have already as an Environment Ref.
     */
    public Environment[] getAllowedEnvironmentsToReference() {
        Vector v = new Vector();
        
        if(getParent() != null) {
            DataComponent[] envs = getParent().getChildren(LD_Core.ENVIRONMENT);
            for(int i = 0; i < envs.length; i++) {
                Environment env = ((Environment)envs[i]);
                if((env != this) && !getEnvironmentRef_Grouping().hasEnvironmentRef(env)) {
                    v.add(env);
                }
            }
        }
        
        Environment[] envs_valid = new Environment[v.size()];
        v.copyInto(envs_valid);
        return envs_valid;
    }
    
    /**
     * @return The MetadataType
     */
    public MetadataType getMetadataType() {
        if(_mdType == null) {
            _mdType = new MetadataType(getDataElement(), "Metadata", "Metadata for the Environment.");
        }
        return _mdType;
    }

    
    /**
     * @return A sensible default Title
     */
    public String getDefaultTitle() {
        return "Environment";
    }
    
    /* (non-Javadoc)
     * @see uk.ac.reload.editor.learningdesign.datamodel.ILD_IconInterface#getIcon()
     */
    public Icon getIcon() {
        return DweezilUIManager.getIcon(ICON_ENVIRONMENT);
    }

    /* (non-Javadoc)
     * @see uk.ac.reload.editor.learningdesign.datamodel.LD_Component#getXMLPath()
     */
    public XMLPath getXMLPath() {
        return XMLPATH;
    }
    
}
