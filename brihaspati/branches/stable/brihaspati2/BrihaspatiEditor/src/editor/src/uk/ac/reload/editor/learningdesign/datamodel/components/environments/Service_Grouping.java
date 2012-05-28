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
import uk.ac.reload.editor.learningdesign.datamodel.LD_DataModel;
import uk.ac.reload.editor.learningdesign.datamodel.LD_Grouping;
import uk.ac.reload.moonunit.learningdesign.LD_Core;


/**
 * Service Grouping
 *
 * @author Phillip Beauvoir
 * @version $Id: Service_Grouping.java,v 1.1 1998/03/26 15:42:28 ynsingh Exp $
 */
public class Service_Grouping
extends LD_Grouping
implements IDataComponentIcon
{
    static final String TITLE = "Services";
    static final String DESCRIPTION = "Add Services to the Learning Design.";

    
    /**
     * Constructor
     * @param ldDataModel
     */
    public Service_Grouping(LD_DataModel ldDataModel, Environment environment) {
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
        Iterator it = getElement().getChildren(LD_Core.SERVICE, getElement().getNamespace()).iterator();
        while(it.hasNext()) {
            Element element = (Element)it.next();
            LD_Component ldComponent = createComponent(element);
            if(ldComponent != null) {
                addChild(ldComponent);
            }
        }
    }

    /**
     * Over-ride to add a child element
     */
    public DataComponent addChildElement(String elementName, String title) {
        if(!isService(elementName)) {
            return null;
        }
        
        LD_Component ldComponent = null;
        
        // Create the <service> element
        Element parentElement = addChildElement(getElement(), LD_Core.SERVICE);
        
        // Now add a new child element to this parent
        Element element = addChildElement(parentElement, elementName);
        
        if(element != null) {
            // Create a component from this element
            ldComponent = createComponent(element);
            if(ldComponent != null) {
                addChild(ldComponent);
            
                // Set Title if there is one
                if(title != null) {
                    ldComponent.setTitle(title);
                }
            
            	// Tell Listeners
            	getDataModel().fireDataComponentAdded(ldComponent);
            }
        }

        return ldComponent;
    }

    /**
     * Factory method to create child Component
     * @param element The wrapped JDOM Element
     * @return The newly created LD_Component or null
     */
    private LD_Component createComponent(Element element) {
        if(element == null) {
            return null;
        }
        
        // If Service, get child
        if(LD_Core.SERVICE.equals(element.getName())) {
            element = (Element)element.getChildren().get(0);
            if(element == null) {
                return null;
            }
        }

        LD_Component ldComponent = null;
        
        String elementName = element.getName();
        
        if(LD_Core.SENDMAIL.equals(elementName)) {
            ldComponent = new SendMail(getLD_DataModel(), element);
        }
        else if(LD_Core.CONFERENCE.equals(elementName)) {
            ldComponent = new Conference(getLD_DataModel(), element);
        }
        else if(LD_Core.INDEX_SEARCH.equals(elementName)) {
            ldComponent = new IndexSearch(getLD_DataModel(), element);
        }
        else if(LD_Core.MONITOR.equals(elementName)) {
            ldComponent = new Monitor(getLD_DataModel(), element);
        }
        
        return ldComponent;
    }

    /**
     * Check whether a child member is a Service type
     * @param elementName The element name
     * @return True if the element is a Service type
     */
    private boolean isService(String elementName) {
        return LD_Core.SENDMAIL.equals(elementName) ||
        	   LD_Core.CONFERENCE.equals(elementName) ||
        	   LD_Core.INDEX_SEARCH.equals(elementName) ||
        	   LD_Core.MONITOR.equals(elementName);
    }

    /* (non-Javadoc)
     * @see uk.ac.reload.editor.learningdesign.datamodel.ILD_IconInterface#getIcon()
     */
    public Icon getIcon() {
        return DweezilUIManager.getIcon(ICON_SERVICE);
    }
}
