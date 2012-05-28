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

package uk.ac.reload.editor.learningdesign.datamodel.components.activities;

import java.util.Iterator;
import java.util.Vector;

import javax.swing.Icon;

import org.jdom.Element;

import uk.ac.reload.dweezil.util.DweezilUIManager;
import uk.ac.reload.editor.learningdesign.datamodel.ItemModelType;
import uk.ac.reload.editor.learningdesign.datamodel.LD_Component;
import uk.ac.reload.editor.learningdesign.datamodel.LD_DataModel;
import uk.ac.reload.editor.learningdesign.datamodel.MetadataType;
import uk.ac.reload.editor.learningdesign.datamodel.components.roles.Role;
import uk.ac.reload.jdom.XMLPath;
import uk.ac.reload.moonunit.learningdesign.LD_Core;


/**
 * SupportActivity
 * 
 * @author Phillip Beauvoir
 * @version $Id: SupportActivity.java,v 1.1 1998/03/26 15:42:28 ynsingh Exp $
 */
public class SupportActivity
extends PrimaryActivity
{
    public static XMLPath XMLPATH = new XMLPath(XMLPATH_ROOT_LD).appendElementName("components/activities/support-activity");
    
    static final String DESCRIPTION = "This section provides information about the Support Activity.";
    
    /**
     * The Description ItemModelType
     */
    private ItemModelType _description;

    /**
     * The Feedback Description ItemModelType
     */
    private ItemModelType _feedbackdescription;

    /**
     * MetadataType
     */
    private MetadataType _mdType;

    
    /**
     * Constructor with backing Element
     * @param ldDataModel The DataModel
     * @param element The backing JDOM XML Element
     */
    protected SupportActivity(LD_DataModel ldDataModel, Element element) {
        super(ldDataModel, element);
        setDescription(DESCRIPTION);
    }
    
    /**
     * Over-ride to ensure references
     */
    protected boolean ensureAllReferences() {
        boolean modified = super.ensureAllReferences();
        modified |= ensureReferences(getElement(), LD_Core.ROLE_REF, LD_Core.REF, getLD_DataModel().getRoles_Grouping());
        return modified;
    }

    /**
     * @return The Roles to send the mail to.  These are the referenced Roles.
     */
    public Role[] getRoles() {
        Vector v = new Vector();
        
        Iterator it = getElement().getChildren(LD_Core.ROLE_REF, getElement().getNamespace()).iterator();
        while(it.hasNext()) {
            Element element = (Element)it.next();
            String ref = element.getAttributeValue(LD_Core.REF);
            LD_Component component = getLD_DataModel().getRoles_Grouping().getChildByIdentifer(ref);
            if(component != null) {
                v.add(component);
            }
        }

        Role[] roles = new Role[v.size()];
        v.copyInto(roles);
        return roles;
    }

    /**
     * @return The MetadataType
     */
    public MetadataType getMetadataType() {
        if(_mdType == null) {
            _mdType = new MetadataType(getDataElement(), "Metadata", "Metadata for the Support Activity.");
        }
        return _mdType;
    }

    /**
     * @return A sensible default Title
     */
    public String getDefaultTitle() {
        return "Support Activity";
    }

    /* (non-Javadoc)
     * @see uk.ac.reload.editor.learningdesign.datamodel.LD_Component#getXMLPath()
     */
    public XMLPath getXMLPath() {
        return XMLPATH;
    }
    
    /* (non-Javadoc)
     * @see uk.ac.reload.editor.learningdesign.datamodel.ILD_IconInterface#getIcon()
     */
    public Icon getIcon() {
        return DweezilUIManager.getIcon(ICON_SUPPORTACTIVITY);
    }
}
