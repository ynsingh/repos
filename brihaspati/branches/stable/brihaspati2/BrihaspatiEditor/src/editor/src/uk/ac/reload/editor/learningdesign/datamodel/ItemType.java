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

import uk.ac.reload.editor.datamodel.DataElement;
import uk.ac.reload.jdom.XMLPath;


/**
 * Item Data object
 * 
 * @author Phillip Beauvoir
 * @version $Id: ItemType.java,v 1.1 1998/03/26 15:32:14 ynsingh Exp $
 */
public class ItemType
extends LD_Component 
{
    
    /**
     * The Element bound to the ItemType root Ancestor
     */
    private DataElement _ldElement;

    /**
     * The Element bound to the Title Element
     */
    private DataElement _elementTitle;

    /**
     * The Element bound to the Item Element
     */
    private DataElement _elementItem;

    /**
     * Child Item Element
     */
    private ItemType _itemType;
    
    /**
     * MetadataType
     */
    private MetadataType _mdType;

    
    /**
     * Constructor
     * @param ldelement The JDOM Element bound to the ItemType root Element
     */
    public ItemType(DataElement ldElement, String title, String description) {
        _ldElement = ldElement;
        setDataModel(ldElement.getDataModel());
        
        setTitle(title);
        setDescription(description);
    }
    
    /**
     * @return The JDOM Element bound to the ItemModelType root Element
     */
    public Element getElement() {
        return _ldElement.getElement();
    }
    
    /**
     * @return The root DataElement
     */
    public DataElement getDataElement() {
        return _ldElement;
    }
    
    /**
     * @return The JDOM Element bound to the ItemModelType Title Element
     */
    public DataElement getTitleElement() {
        if(_elementTitle == null) {
            _elementTitle = new DataElement(getDataModel(), _ldElement.getAncestorElement(),
                    new XMLPath(_ldElement.getXMLElementPathFragment()).appendElementName("title"));
        }
        return _elementTitle;
    }
    
    /**
     * @return The (root) Item Element
     */
    public DataElement getItemElement() {
        if(_elementItem == null) {
            _elementItem = new DataElement(getDataModel(), _ldElement.getAncestorElement(),
                    new XMLPath(_ldElement.getXMLElementPathFragment()).appendElementName("item"));
        }
        return _elementItem;
    }

    /**
     * @return The child ItemType
     */
    public ItemType getItemType() {
        if(_itemType == null) {
            _itemType = new ItemType(getItemElement(), "", "");
        }
        return _itemType;
    }

    /**
     * @return The MetadataType
     */
    public MetadataType getMetadataType() {
        if(_mdType == null) {
            _mdType = new MetadataType(getItemElement(), "Item Metadata", "Metadata for the Item.");
        }
        return _mdType;
    }

}
