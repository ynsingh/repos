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

import javax.swing.Icon;

import uk.ac.reload.dweezil.util.DweezilUIManager;
import uk.ac.reload.editor.datamodel.DataElement;
import uk.ac.reload.editor.datamodel.IDataComponentIcon;
import uk.ac.reload.jdom.XMLPath;


/**
 * An IMS Metadata type.
 * <p><p>
 *
 * @author Phillip Beauvoir
 * @version $Id: MetadataType.java,v 1.1 1998/03/26 15:32:14 ynsingh Exp $
 */
public class MetadataType
extends LD_Component
implements IDataComponentIcon
{
    
    /**
     * The Element bound
     */
    private DataElement _ldelement;

    /**
     * Default Constructor
     */
    public MetadataType(DataElement ldelement, String title, String description) {
        _ldelement = ldelement;
        setDataModel(ldelement.getDataModel());
        setTitle(title);
        setDescription(description);
    }
    
    /**
     * @return The JDOM Element bound to the "metadata" Element
     */
    public DataElement getMetadataDataElement() {
        return new DataElement(getDataModel(), _ldelement.getAncestorElement(),
                new XMLPath(_ldelement.getXMLElementPathFragment()).appendElementName("metadata"));
    }

    /**
     * @return The DataElement for the "schema" element
     */
    public DataElement getSchemaDataElement() {
        return new DataElement(getDataModel(), _ldelement.getAncestorElement(),
                new XMLPath(_ldelement.getXMLElementPathFragment()).appendElementName("metadata/schema"));
    }
    
    /**
     * @return The DataElement for the "schemaversion" element
     */
    public DataElement getSchemaVersionDataElement() {
        return new DataElement(getDataModel(), _ldelement.getAncestorElement(),
                new XMLPath(_ldelement.getXMLElementPathFragment()).appendElementName("metadata/schemaversion"));
    }

    /* (non-Javadoc)
     * @see uk.ac.reload.editor.learningdesign.datamodel.ILD_IconInterface#getIcon()
     */
    public Icon getIcon() {
        return DweezilUIManager.getIcon(ICON_MD);
    }
}
