/**
 *  RELOAD TOOLS
 *
 *  Copyright (c) 2003 Oleg Liber, Bill Olivier, Phillip Beauvoir
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

package uk.ac.reload.editor.learningdesign.datamodel.resources;

import javax.swing.Icon;

import org.jdom.Element;

import uk.ac.reload.dweezil.util.DweezilUIManager;
import uk.ac.reload.editor.datamodel.IDataComponentIcon;
import uk.ac.reload.editor.learningdesign.datamodel.LD_Component;
import uk.ac.reload.editor.learningdesign.datamodel.LD_DataModel;
import uk.ac.reload.jdom.XMLDocumentListenerEvent;
import uk.ac.reload.moonunit.contentpackaging.CP_Core;



/**
 * This class wraps a dependency XML element
 * 
 * @author Phillip Beauvoir
 * @version $Id: LD_Dependency.java,v 1.1 1998/03/26 15:40:32 ynsingh Exp $
 */
public class LD_Dependency
extends LD_Component
implements IDataComponentIcon
{

    /**
     * Constructor
     * 
     * @param ldDataModel The LD_DataModel
     * @param dependencyElement The wrapped dependency XML element
     */
    public LD_Dependency(LD_DataModel ldDataModel, Element dependencyElement) {
        setDataModel(ldDataModel);
        setElement(dependencyElement);

        // Listen to the element being changed
        ldDataModel.getLearningDesign().addXMLDocumentListener(this);
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    public String toString() {
        Element element = getElement();
        
        // A non-resource
        if(element == null) {
            return "Dependency";
        }
        // Return the HREF
        else {
			String id_ref = element.getAttributeValue(CP_Core.IDENTIFIERREF);
			if(id_ref == null || "".equals(id_ref)) {
			    id_ref = "Dependency";
			}
			else {
			    LD_Resource resource = getLD_DataModel().getResources().getLD_Resource(id_ref);
			    if(resource != null) {
			        id_ref = resource.toString();
			    }
			}
            return id_ref;
        }
    }

    /* (non-Javadoc)
	 * @see uk.ac.reload.editor.learningdesign.datamodel.ILD_IconInterface#getIcon()
	 */
	public Icon getIcon() {
        return DweezilUIManager.getIcon(ICON_DEPENDENCY);
    }

	//========================= Listener Events ==================================
    
    /** 
     * An Element changed - we need to fire on identifier and href changes
     */
    public void elementChanged(XMLDocumentListenerEvent event) {
        Element element = event.getElement();
        if(element.equals(getElement())) {
            getDataModel().fireDataComponentChanged(this);
        }
    }
}
