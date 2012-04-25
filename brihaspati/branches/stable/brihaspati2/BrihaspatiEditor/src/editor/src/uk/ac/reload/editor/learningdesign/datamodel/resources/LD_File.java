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
import uk.ac.reload.editor.learningdesign.datamodel.MetadataType;
import uk.ac.reload.jdom.XMLDocumentListenerEvent;
import uk.ac.reload.moonunit.contentpackaging.CP_Core;



/**
 * This class wraps a <file> XML element
 * 
 * @author Phillip Beauvoir
 * @version $Id: LD_File.java,v 1.1 1998/03/26 15:40:32 ynsingh Exp $
 */
public class LD_File
extends LD_Component
implements IDataComponentIcon
{

    /**
     * MetadataType
     */
    private MetadataType _mdType;

    /**
     * Constructor
     * 
     * @param ldDataModel The LD_DataModel
     * @param fileElement The wrapped file XML element
     */
    public LD_File(LD_DataModel ldDataModel, Element fileElement) {
        setDataModel(ldDataModel);
        setElement(fileElement);

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
            return "File";
        }
        // Return the HREF
        else {
			String name = element.getAttributeValue(CP_Core.HREF);
			if(name == null || "".equals(name)) {
			    name = "File";
			}
            return name;
        }
    }

    /**
     * @return The MetadataType
     */
    public MetadataType getMetadataType() {
        if(_mdType == null) {
            _mdType = new MetadataType(getDataElement(), "Metadata", "Metadata for the File.");
        }
        return _mdType;
    }

    /* (non-Javadoc)
	 * @see uk.ac.reload.editor.learningdesign.datamodel.ILD_IconInterface#getIcon()
	 */
	public Icon getIcon() {
        return DweezilUIManager.getIcon(ICON_FILE);
    }
	
	//========================= Listener Events ==================================
    
    /** 
     * An Element changed - we need to fire on href changes
     */
    public void elementChanged(XMLDocumentListenerEvent event) {
        Element element = event.getElement();
        if(element.equals(getElement())) {
            getDataModel().fireDataComponentChanged(this);
        }
    }
}
