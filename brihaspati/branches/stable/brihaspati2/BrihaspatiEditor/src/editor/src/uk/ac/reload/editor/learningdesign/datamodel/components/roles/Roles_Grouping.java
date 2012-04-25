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

import javax.swing.Icon;

import org.jdom.Element;

import uk.ac.reload.dweezil.util.DweezilUIManager;
import uk.ac.reload.editor.datamodel.IDataComponentIcon;
import uk.ac.reload.editor.learningdesign.datamodel.LD_Component;
import uk.ac.reload.editor.learningdesign.datamodel.LD_DataModel;
import uk.ac.reload.editor.learningdesign.datamodel.LD_Grouping;
import uk.ac.reload.editor.learningdesign.xml.LearningDesign;
import uk.ac.reload.jdom.XMLPath;
import uk.ac.reload.moonunit.learningdesign.LD_Core;
import uk.ac.reload.moonunit.schema.SchemaElement;


/**
 * Grouping Component for Roles
 *
 * @author Phillip Beauvoir
 * @version $Id: Roles_Grouping.java,v 1.1 1998/03/26 15:42:28 ynsingh Exp $
 */
public class Roles_Grouping
extends LD_Grouping
implements IDataComponentIcon
{
    public static XMLPath XMLPATH = new XMLPath(XMLPATH_ROOT_LD).appendElementName("components/roles");
    
    /**
     * Learners
     */
    private Learner_Grouping _learnerGrouping;
    
    /**
     * Staff
     */
    private Staff_Grouping _staffGrouping;

    /**
     * Constructor
     * @param ldDataModel
     */
    public Roles_Grouping(LD_DataModel ldDataModel) {
        setDataModel(ldDataModel);
        
        setTitle("Roles");
        setDescription("Roles in the Learning Design.");
        
        // Do this first - make sure there is a <roles> Element in the XML DOM
        // This will automatically add one Learner Role
        LearningDesign ld = ldDataModel.getLearningDesign();
        Element elementRoles = ld.getElement(XMLPATH);
        if(elementRoles == null) {
            SchemaElement schemaElement = (SchemaElement)ld.getSchemaController().getSchemaNode(XMLPATH);
            elementRoles = ld.addElementUniqueBySchema(this, schemaElement, false);
        }
        
        setElement(elementRoles);
        
        // Add an identifier to <roles> to be on the safe side
        if(elementRoles.getAttributeValue(LD_Core.IDENTIFIER) == null) {
            elementRoles.setAttribute(LD_Core.IDENTIFIER, ld.generateUniqueID(elementRoles));
        }
        
        // Learners
        _learnerGrouping = new Learner_Grouping(ldDataModel, elementRoles);
        addChild(_learnerGrouping);
        
        // Staff
        _staffGrouping = new Staff_Grouping(ldDataModel, elementRoles);
        addChild(_staffGrouping);
    }
    
    /**
     * @return The Learner Grouping
     */
    public Learner_Grouping getLearner_Grouping() {
        return _learnerGrouping;
    }

    /**
     * @return The Staff Grouping
     */
    public Staff_Grouping getStaff_Grouping() {
        return _staffGrouping;
    }

    /**
     * Over-ride to look in Staff and Learner groups
     * @param id The identifier attribute
     * @return The Role or null if not found
     */
    public LD_Component getChildByIdentifer(String id) {
        LD_Component component = getLearner_Grouping().getChildByIdentifer(id);
        return component != null ? component : getStaff_Grouping().getChildByIdentifer(id);
    }

    /* (non-Javadoc)
     * @see uk.ac.reload.editor.learningdesign.datamodel.ILD_IconInterface#getIcon()
     */
    public Icon getIcon() {
        return DweezilUIManager.getIcon(ICON_ROLES);
    }
}
