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

package uk.ac.reload.editor.metadata.datamodel;

import javax.swing.Icon;

import org.jdom.Element;

import uk.ac.reload.dweezil.util.DweezilUIManager;
import uk.ac.reload.editor.datamodel.DataElement;
import uk.ac.reload.editor.datamodel.IDataComponentIcon;
import uk.ac.reload.jdom.XMLPath;


/**
 * LifeCycle
 *
 * @author Phillip Beauvoir
 * @version $Id: MD_LifeCycle.java,v 1.1 1998/03/26 15:32:14 ynsingh Exp $
 */
public class MD_LifeCycle
extends MD_Component
implements IDataComponentIcon
{
    public static final String TITLE = "Life Cycle";
    public static final String DESCRIPTION = "History and current state of the resource.";

    /**
     * Default Constructor
     */
    public MD_LifeCycle() {
        super();
    }
    
    /**
     * @return The Status DataElement
     */
    public DataElement getStatus() {
        Element rootElement = getDataModel().getSchemaDocument().getRootElement();
	    return new DataElement(getDataModel(), rootElement, new XMLPath("lifecycle/status/value/langstring"));
    }

    /**
     * Constructor with LearningDesign
     * @param md The Metadata DOM
     */
    public MD_LifeCycle(MD_DataModel mdDataModel) {
        super(mdDataModel);
    }

    /* (non-Javadoc)
     * @see uk.ac.reload.editor.datamodel.DataComponent#getTitle()
     */
    public String getTitle() {
        return TITLE;
    }
    
    /* (non-Javadoc)
     * @see uk.ac.reload.editor.datamodel.DataComponent#getDescription()
     */
    public String getDescription() {
        return DESCRIPTION;
    }

    /* (non-Javadoc)
     * @see uk.ac.reload.editor.datamodel.IDataComponentIcon#getIcon()
     */
    public Icon getIcon() {
        return DweezilUIManager.getIcon(ICON_MD);
    }
}
