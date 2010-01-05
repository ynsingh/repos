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

import org.jdom.Element;

import uk.ac.reload.editor.datamodel.DataElement;
import uk.ac.reload.editor.datamodel.IDataComponentIcon;
import uk.ac.reload.editor.learningdesign.datamodel.ItemModelType;
import uk.ac.reload.editor.learningdesign.datamodel.LD_Component;
import uk.ac.reload.editor.learningdesign.datamodel.LD_DataModel;
import uk.ac.reload.editor.learningdesign.datamodel.MetadataType;
import uk.ac.reload.jdom.XMLPath;


/**
 * Role class - the supertype of Learner and Staff classes
 * 
 * @author Phillip Beauvoir
 * @version $Id: Role.java,v 1.1 1998/03/26 15:42:28 ynsingh Exp $
 */
public abstract class Role
extends LD_Component
implements IDataComponentIcon
{
    private static final String INFORMATION_TITLE = "Information";
    private static final String INFORMATION_DESCRIPTION = "May be used to provide additional information about this role.";
    
    /**
     * Information Item Model Type
     */
    private ItemModelType _informationItemModelType;
    
    /**
     * MetadataType
     */
    private MetadataType _mdType;

    
    /**
     * Constructor with backing Element
     * @param ldDataModel The DataModel
     * @param element The backing JDOM XML Element
     */
    protected Role(LD_DataModel ldDataModel, Element element) {
        setDataModel(ldDataModel);
        setElement(element);
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
     * Over-ride this to set and ensure we have a <title> Element
     */
    public void setTitle(String title) {
        setTitleElement(title);
    }
    
    /**
     * @return The child information class
     */
    public ItemModelType getInformationItemModelType() {
        if(_informationItemModelType == null) {
            DataElement dataElement = new DataElement(getDataModel(), getElement(), new XMLPath("information")); 
            _informationItemModelType = new ItemModelType(dataElement, INFORMATION_TITLE, INFORMATION_DESCRIPTION);
        }
        return _informationItemModelType; 
    }

    /**
     * @return The MetadataType
     */
    public MetadataType getMetadataType() {
        if(_mdType == null) {
            _mdType = new MetadataType(getDataElement(), "Metadata", "Metadata for the Role.");
        }
        return _mdType;
    }
}
