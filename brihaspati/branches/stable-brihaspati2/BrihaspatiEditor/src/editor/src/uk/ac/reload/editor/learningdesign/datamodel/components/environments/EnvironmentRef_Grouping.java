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

import javax.swing.Icon;

import org.jdom.Element;

import uk.ac.reload.dweezil.util.DweezilUIManager;
import uk.ac.reload.editor.datamodel.DataComponent;
import uk.ac.reload.editor.datamodel.IDataComponentIcon;
import uk.ac.reload.editor.learningdesign.datamodel.LD_Component;
import uk.ac.reload.editor.learningdesign.datamodel.LD_ComponentRef;
import uk.ac.reload.editor.learningdesign.datamodel.LD_DataModel;
import uk.ac.reload.editor.learningdesign.datamodel.LD_Grouping;
import uk.ac.reload.moonunit.learningdesign.LD_Core;


/**
 * Environment Ref Grouping
 *
 * @author Phillip Beauvoir
 * @version $Id: EnvironmentRef_Grouping.java,v 1.1 1998/03/26 15:42:28 ynsingh Exp $
 */
public class EnvironmentRef_Grouping
extends LD_Grouping
implements IDataComponentIcon
{
    static final String TITLE = "Environment References";
    static final String DESCRIPTION = "Add Environment References to the Learning Design.";

    
    /**
     * Constructor
     * @param ldDataModel
     */
    public EnvironmentRef_Grouping(LD_DataModel ldDataModel, Environment environment) {
        setDataModel(ldDataModel);
        
        setElement(environment.getElement());
        
        setTitle(TITLE);
        setDescription(DESCRIPTION);
        
        addChildren();
    }

    /**
     * Add Child Components
     */
    protected void addChildren() {
        Iterator it = getElement().getChildren(LD_Core.ENVIRONMENT_REF, getElement().getNamespace()).iterator();
        while(it.hasNext()) {
            Element element = (Element)it.next();
            LD_Component ldComponent = new EnvironmentRef(getLD_DataModel(), element);
            addChild(ldComponent);
        }
    }

    /**
     * Over-ride to add a child element
     */
    public DataComponent addChildElement(String elementName, String title) {
        if(!LD_Core.ENVIRONMENT_REF.equals(elementName)) {
            return null;
        }
        
        LD_Component ldComponent = null;
        
        // Create a new Element
        Element element = addChildElement(getElement(), elementName);
        if(element != null) {
            ldComponent = new EnvironmentRef(getLD_DataModel(), element);
            addChild(ldComponent);
            
            // Set Title if there is one
            if(title != null) {
                ldComponent.setTitle(title);
            }
            
            // Tell Listeners
            getDataModel().fireDataComponentAdded(ldComponent);
        }

        return ldComponent;
    }

    /**
     * @return True if this Environment already contains the EnvironmentRef of Environment
     */
    public boolean hasEnvironmentRef(Environment environment) {
        if(environment == null) {
            return false;
        }
        
        DataComponent[] env_refs = getChildren();
        for(int i = 0; i < env_refs.length; i++) {
            LD_Component refComponent = ((LD_ComponentRef)env_refs[i]).getLD_ComponentRef();
            if(environment == refComponent) {
                return true;
            }
        }
        return false;
    }

    /**
     * Update the Environment Component references to EnvironmentRef Components
     */
    public void updateEnvironmentRefs(Environments_Grouping envGrouping) {
        DataComponent[] env_refs = getChildren();
        for(int i = 0; i < env_refs.length; i++) {
            EnvironmentRef envRef = (EnvironmentRef)env_refs[i];
            LD_Component env = envGrouping.getChildByIdentifer(envRef.getRef());
            if(env != null) {
                envRef.setLD_ComponentRef(env);
            }
        }
    }

    /* (non-Javadoc)
     * @see uk.ac.reload.editor.learningdesign.datamodel.ILD_IconInterface#getIcon()
     */
    public Icon getIcon() {
        return DweezilUIManager.getIcon(ICON_ENVIRONMENTS);
    }
}
