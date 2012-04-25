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

import javax.swing.Icon;

import org.jdom.Element;

import uk.ac.reload.dweezil.util.DweezilUIManager;
import uk.ac.reload.editor.datamodel.IDataComponentIcon;
import uk.ac.reload.editor.learningdesign.datamodel.ItemType;
import uk.ac.reload.editor.learningdesign.datamodel.LD_Component;
import uk.ac.reload.editor.learningdesign.datamodel.LD_DataModel;
import uk.ac.reload.editor.learningdesign.datamodel.MetadataType;
import uk.ac.reload.jdom.XMLPath;

/**
 * Learning Design Learning Object Element
 *
 * @author Phillip Beauvoir
 * @version $Id: LearningObject.java,v 1.1 1998/03/26 15:42:28 ynsingh Exp $
 */
public class LearningObject
extends LD_Component
implements IDataComponentIcon
{
    public static XMLPath XMLPATH = new XMLPath(XMLPATH_ROOT_LD).appendElementName("components/environments/environment/learning-object");

    static final String DESCRIPTION = "This section provides information about the Learning Object. " +
		"You can add a reference to a Resource.";
    
    /**
     * The Element bound to the ItemType
     */
    private ItemType _itemType;
    
    /**
     * MetadataType
     */
    private MetadataType _mdType;


    /**
     * Default Constructor
     */
    public LearningObject() {
    }
    
    /**
     * Constructor with backing Element
     * @param ldDataModel The LD_DataModel
     * @param element The backing JDOM Element
     */
    public LearningObject(LD_DataModel ldDataModel, Element element) {
        setDataModel(ldDataModel);
        setElement(element);
        setDescription(DESCRIPTION);
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
     * @return A sensible default Title
     */
    public String getDefaultTitle() {
        return "Learning Object";
    }

    /**
     * @return The ItemType
     */
    public ItemType getItemType() {
        if(_itemType == null) {
            _itemType = new ItemType(getDataElement(), "Learning Object", "Learning Object");
        }
        return _itemType;
    }

    /**
     * @return The MetadataType
     */
    public MetadataType getMetadataType() {
        if(_mdType == null) {
            _mdType = new MetadataType(getDataElement(), "Metadata", "Metadata for the Learning Object.");
        }
        return _mdType;
    }

    /**
     * Move the bound element up.
     * Over-ride this.  Because we've put the Learning Objects in subgroups, moving the element up one place
     * doesn't necessarily mean it will move above the previous sibling of the same type.
     */
    protected void moveElementUp() {
        moveElementUpSameType();
    }
    
    /**
     * Move the bound element down.
     * Over-ride this.  Because we've put the Learning Objects in subgroups, moving the element down one place
     * doesn't necessarily mean it will move below the next sibling of the same type.
     */
    protected void moveElementDown() {
        moveElementDownSameType();
    }

    /* (non-Javadoc)
     * @see uk.ac.reload.editor.learningdesign.datamodel.ILD_IconInterface#getIcon()
     */
    public Icon getIcon() {
        return DweezilUIManager.getIcon(ICON_LEARNINGOBJECT);
    }

    /* (non-Javadoc)
     * @see uk.ac.reload.editor.learningdesign.datamodel.LD_Component#getXMLPath()
     */
    public XMLPath getXMLPath() {
        return XMLPATH;
    }
}
