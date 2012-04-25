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

import java.util.Iterator;
import java.util.Vector;

import javax.swing.Icon;

import org.jdom.Element;

import uk.ac.reload.dweezil.util.DweezilUIManager;
import uk.ac.reload.editor.datamodel.DataComponent;
import uk.ac.reload.editor.datamodel.IDataComponentIcon;
import uk.ac.reload.editor.learningdesign.datamodel.LD_DataModel;
import uk.ac.reload.editor.learningdesign.datamodel.LD_Grouping;
import uk.ac.reload.jdom.XMLPath;
import uk.ac.reload.moonunit.SchemaDocument;
import uk.ac.reload.moonunit.learningdesign.LD_Core;
import uk.ac.reload.moonunit.schema.SchemaElement;


/**
 * Environments_Grouping
 *
 * @author Phillip Beauvoir
 * @version $Id: Environments_Grouping.java,v 1.1 1998/03/26 15:42:28 ynsingh Exp $
 */
public class Environments_Grouping
extends LD_Grouping
implements IDataComponentIcon
{
    public static XMLPath XMLPATH = new XMLPath(XMLPATH_ROOT_LD).appendElementName("components/environments");
    
    static final String TITLE = "Environments";
    static final String DESCRIPTION = "Add Environments to the Learning Design.";
    
    /**
     * Constructor
     * @param ldDataModel
     */
    public Environments_Grouping(LD_DataModel ldDataModel) {
        setDataModel(ldDataModel);
        
        setTitle(TITLE);
        setDescription(DESCRIPTION);

        addChildren();
        
        // Now we have to update the Environment Refs' referenced components
        updateEnvironmentRefs();

        // Listen to Component Changes
        ldDataModel.addIDataModelListener(this);
    }

    /**
     * Ensure and create if need be the <environments> element.<p>
     * This will automatically add one Environment.
     * @return The <environments> element
     */
    protected Element ensureEnvironmentsElement() {
        Element elementEnvs = getEnvironmentsElement();
        if(elementEnvs == null) {
            // Add <environments> element as per Schema to the XML DOM
            SchemaDocument schemaDocument = getDataModel().getSchemaDocument();
            SchemaElement schemaElement = (SchemaElement)schemaDocument.getSchemaController().getSchemaNode(XMLPATH);
            elementEnvs = schemaDocument.addElementUniqueBySchema(this, schemaElement, false);
            // Bind the <environments> Element
            setElement(elementEnvs);
        }
        
        return elementEnvs;
    }
    
    /**
     * @return The <environments> element from the XMl DOM.  This may be null if not present.
     */
    public Element getEnvironmentsElement() {
        SchemaDocument schemaDocument = getDataModel().getSchemaDocument();
        return schemaDocument.getElement(XMLPATH);
    }

    /**
     * @return All Learning Objects
     */
    public LearningObject[] getLearningObjects() {
        Vector v = new Vector();
        DataComponent[] envs = getChildren();
        for(int i = 0; i < envs.length; i++) {
            Environment env = (Environment)envs[i];
            DataComponent[] los = env.getLearningObject_Grouping().getChildren();
            for(int j = 0; j < los.length; j++) {
                v.add(los[j]);
            }
        }
        
        LearningObject[] los = new LearningObject[v.size()];
        v.copyInto(los);
        return los;
    }

    /**
     * @return All Service Type components
     */
    public Service[] getServices() {
        Vector v = new Vector();
        DataComponent[] envs = getChildren();
        for(int i = 0; i < envs.length; i++) {
            Environment env = (Environment)envs[i];
            DataComponent[] los = env.getService_Grouping().getChildren();
            for(int j = 0; j < los.length; j++) {
                v.add(los[j]);
            }
        }
        
        Service[] services = new Service[v.size()];
        v.copyInto(services);
        return services;
    }

    /**
     * Add Child Components
     */
    protected void addChildren() {
        // Try for the <environments> element
        Element elementEnvs = getEnvironmentsElement();
        
        if(elementEnvs != null) {
            // Bind the <environments> Element
            setElement(elementEnvs);

            // Add <environment> elements from XML DOM
            Iterator it = elementEnvs.getChildren(LD_Core.ENVIRONMENT, elementEnvs.getNamespace()).iterator();
            while(it.hasNext()) {
                Element child = (Element)it.next();
                Environment env = new Environment(getLD_DataModel(), child);
                addChild(env);
            }
        }
    }
    
    /**
     * Update the Environment Components to EnvironmentRef Components
     */
    private void updateEnvironmentRefs() {
        DataComponent[] envs = getChildren();
        for(int i = 0; i < envs.length; i++) {
            Environment env = (Environment)envs[i];
            env.getEnvironmentRef_Grouping().updateEnvironmentRefs(this);
        }
    }

    /**
     * Over-ride to add a child element
     */
    public DataComponent addChildElement(String elementName, String title) {
        if(!LD_Core.ENVIRONMENT.equals(elementName)) {
            return null;
        }
        
        Element element = null;
        Environment environment = null;
        
        // Get the <environments> Element
        Element elementEnvs = getEnvironmentsElement();
        // If there isn't one we will create one which will also automatically create
        // a new <environment> Element which we will use
        if(elementEnvs == null) {
            elementEnvs = ensureEnvironmentsElement();
            element = elementEnvs.getChild(LD_Core.ENVIRONMENT, elementEnvs.getNamespace());
        }
        // Else create a new one
        else {
            element = addChildElement(getElement(), elementName);
        }

        // Now create Component
        if(element != null) {
            environment = new Environment(getLD_DataModel(), element);
            addChild(environment);
            
            // Set Title if there is one
            if(title != null) {
                environment.setTitle(title);
            }
            
            // Tell Listeners
            getDataModel().fireDataComponentAdded(environment);
        }

        return environment;
    }

    /* (non-Javadoc)
	 * @see uk.ac.reload.editor.learningdesign.datamodel.ILD_IconInterface#getIcon()
	 */
	public Icon getIcon() {
        return DweezilUIManager.getIcon(ICON_ENVIRONMENTS);
    }
	
    //========================= Listener Events ==================================
    
	/**
     * We heard that an Environment has been removed from the Data Model
     */
    public void componentRemoved(DataComponent component) {
        // If there are no <environment> elements, remove the parent <environments> element
        if(component instanceof Environment && hasChildren() == false) {
            Element elementEnvs = getEnvironmentsElement();
            if(elementEnvs != null) {
                elementEnvs.getParent().getContent().remove(elementEnvs);
            }
        }
    }
	
}
