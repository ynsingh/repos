
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

package uk.ac.reload.editor.metadata.editor.formview;

import uk.ac.reload.editor.metadata.xml.Metadata;
import uk.ac.reload.moonunit.ProfiledSchemaController;
import uk.ac.reload.moonunit.schema.SchemaElement;
import uk.ac.reload.moonunit.schema.SchemaNode;
import uk.ac.reload.moonunit.vocab.VocabularyList;

/**
 * The Metadata Form View Model
 * This reads in a MD User Profile and provides the Elements and form view mappings
 *
 * @author Phillip Beauvoir
 * @version $Id: MD_FormModel.java,v 1.1 1998/03/26 15:40:32 ynsingh Exp $
 */
public class MD_FormModel {
    /**
     * The Metadata
     */
    private Metadata _metadata;

    /**
     * The Controller
     */
    private ProfiledSchemaController _schemaController;

    /**
     * The Metadata Profile root element
     */
    private MD_ProfileElement _rootProfileElement;


    /**
     * Constructor
     */
    public MD_FormModel() {

    }

    /**
     * Update the Document
     */
    public void setDocument(Metadata metadata) {
        _metadata = metadata;
        _schemaController = (ProfiledSchemaController)_metadata.getSchemaController();
    }
    
    /**
     * @return The Metadata Document
     */
    public Metadata getDocument() {
        return _metadata;
    }

    /**
     * Get the Root Element of the Metadata Profile
     * @return
     */
    public MD_ProfileElement getRootElement() {
        return new MD_ProfileElement(_schemaController
                						.getHelperProfile()
                						.getDocument()
                						.getRootElement());
    }

    /**
     * Create a suitable component for a Schema Element or Attribute
     * @param profileElement
     * @return The Widget
     */
    public MD_Field createWidget(MD_ProfileElement profileElement) {
        SchemaNode schemaNode = _schemaController.getSchemaNode(profileElement.getXMLPath());

        // Attribute
        //if(schemaNode instanceof SchemaAttribute)
        //    return createAttributeWidget((SchemaAttribute)schemaNode);

        // Element
        if(schemaNode instanceof SchemaElement)
            return createElementWidget((SchemaElement)schemaNode);

        else return null;
    }

    /**
     * Create a widget bound to an Element
     * @param schemaElement The Element to Widgetise
     * @return A MD_Field
     */
    protected MD_Field createElementWidget(SchemaElement schemaElement) {
        // Do we have a vocabulary?
        VocabularyList vList = _schemaController.getVocabularyList(schemaElement);

        // If we do, make a combo box
        if(vList != null) return new MD_ComboBox(_metadata, schemaElement, vList);

        // Else make a Text Field
        else return new MD_TextField(_metadata, schemaElement);
    }

    /**
     * Create a widget bound to an Element's Attribute
     * @param att The Attribute to Widgetise
     * @return A MD_Field
     */
/*    protected MD_Field createAttributeWidget(SchemaAttribute att) {
        // Do we have a vocabulary?
        VocabularyList vList = _schemaController.getVocabularyList(att);

        // If we do, make a combo box
        if(rvList != null) return new MD_ComboBox(att, metadata, vList);

        // Else make a StringPanel
        else return new MD_TextField(att, metadata);
    }
*/
}
