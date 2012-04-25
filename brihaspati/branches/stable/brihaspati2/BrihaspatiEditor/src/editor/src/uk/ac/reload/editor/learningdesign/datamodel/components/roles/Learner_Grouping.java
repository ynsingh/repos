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

package uk.ac.reload.editor.learningdesign.datamodel.components.roles;

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
 * Learner Grouping
 *
 * @author Phillip Beauvoir
 * @version $Id: Learner_Grouping.java,v 1.1 1998/03/26 15:42:28 ynsingh Exp $
 */
public class Learner_Grouping
extends LD_Grouping
implements IDataComponentIcon
{
    
    static final String TITLE = "Learners";
    static final String DESCRIPTION = "Add Learner roles to the Learning Design. You can add Learner types and Learner Group types.";
    
    /**
     * Constructor
     * @param ldDataModel The LD DataModel
     * @param element The bound XML JDOM Element
     */
    public Learner_Grouping(LD_DataModel ldDataModel, Element element) {
        setDataModel(ldDataModel);
        setElement(element);
        
        setTitle(TITLE);
        setDescription(DESCRIPTION);
        
        addChildren();
    }
    
    /**
     * Add Child Components
     */
    protected void addChildren() {
        // Add <learner> elements from XML DOM
        Iterator it = getElement().getChildren(LD_Core.LEARNER, getElement().getNamespace()).iterator();
        while(it.hasNext()) {
            Element child = (Element)it.next();
            Learner learner = new Learner(getLD_DataModel(), child);
            addChild(learner);
        }
    }

    /**
     * Over-ride to add a child element
     */
    public DataComponent addChildElement(String elementName, String title) {
        if(!LD_Core.LEARNER.equals(elementName)) {
            return null;
        }
        
        Learner learner = null;
        
        Element element = addChildElement(getElement(), elementName);
        if(element != null) {
            learner = new Learner(getLD_DataModel(), element);
            addChild(learner);
            
            // Set Title if there is one
            if(title != null) {
                learner.setTitle(title);
            }
            
            // Tell Listeners
            getDataModel().fireDataComponentAdded(learner);
        }

        return learner;
    }
    
    /**
     * Over-ride to do a deep search
     * @param id The identifier attribute
     * @return The child component  or null if not found
     */
    public LD_Component getChildByIdentifer(String id) {
        if(id == null || "".equals(id)) {
            return null;
        }
        
        DataComponent[] children = getAllChildren();
        for(int i = 0; i < children.length; i++) {
            LD_Component ldComponent = (LD_Component)children[i];
            String id2 = ldComponent.getIdentifier();
            if(id.equals(id2)) {
                return ldComponent;
            }
        }
        
        return null;
    }

    /* (non-Javadoc)
	 * @see uk.ac.reload.editor.learningdesign.datamodel.ILD_IconInterface#getIcon()
	 */
	public Icon getIcon() {
        return DweezilUIManager.getIcon(ICON_LEARNERS);
    }
}
