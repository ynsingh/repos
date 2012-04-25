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

package uk.ac.reload.editor.gui.schemaviewer;

import java.awt.Color;

import javax.swing.JTextPane;

import uk.ac.reload.dweezil.util.DweezilUIManager;
import uk.ac.reload.jdom.XMLPath;
import uk.ac.reload.moonunit.SchemaController;
import uk.ac.reload.moonunit.schema.SchemaAttribute;
import uk.ac.reload.moonunit.schema.SchemaElement;
import uk.ac.reload.moonunit.vocab.VocabularyList;

/**
 * A Viewer TextPane that will display the information associated with
 * a SchemaElement and its Attributes.  This is for testing purposes only.
 *
 * @author Phillip Beauvoir
 * @version $Id: NodeViewer.java,v 1.1 1998/03/26 15:32:14 ynsingh Exp $
 */
public class NodeViewer
extends JTextPane
{
    private SchemaController _schemaController;

    /**
     * Default Constructor
     */
    public NodeViewer(SchemaController schemaController) {
        setEditable(false);
        setCursor(DweezilUIManager.TEXT_CURSOR);
        _schemaController = schemaController;
        setBackground(Color.lightGray);
    }

    /**
     * Set the viewer to display the information for a SchemaElement.
     * @param schemaElement The SchemaElement to display info on
     */
    public void setElement(SchemaElement schemaElement) {
    	String CR = System.getProperty("line.separator");
    	
    	StringBuffer sb = new StringBuffer();

        XMLPath xmlPath = schemaElement.getXMLPath();

        sb.append("Name:\t" + schemaElement.getName() + CR);
        sb.append("Friendly:\t" + _schemaController.getElementFriendlyName(xmlPath) + CR);
        sb.append("Tip:\t" + _schemaController.getElementTip(xmlPath) + CR);
        sb.append("Path:\t" + xmlPath.getPath() + CR);
        sb.append("Type:\t" + schemaElement.getTypeName() + CR);
        sb.append("Base:\t" + schemaElement.getBaseTypeName() + CR);
        sb.append("Atomic Base:\t" + schemaElement.getAtomicBaseTypeName() + CR);
        sb.append("Min:\t" + schemaElement.getMinOccurs() + CR);
        sb.append("Max:\t" + schemaElement.getMaxOccurs() + CR);
        sb.append("MinLength:\t" + schemaElement.getFacetValue("minLength") + CR);
        sb.append("MaxLength:\t" + schemaElement.getFacetValue("maxLength") + CR);
        sb.append("Default:\t" + _schemaController.getDefaultValue(schemaElement) + CR);

        // Get vocab list
        VocabularyList rvList = _schemaController.getVocabularyList(schemaElement);
        if(rvList != null) {
            String[] vocab = rvList.getList();
            sb.append("Vocabulary:\t");
            for(int i = 0; i < vocab.length; i++) {
                sb.append(vocab[i]);
                if(i < vocab.length-1) sb.append(", ");
            }
            sb.append(CR);
        }

        // "choice" sequence
        if(schemaElement.isChoiceElement()) {
            sb.append(CR);
            sb.append("Is part of a Choice grouping: " + CR);
            sb.append("\tGroup Min:\t" + schemaElement.getOrderMinOccurs() + CR);
            sb.append("\tGroup Max:\t" + schemaElement.getOrderMaxOccurs() + CR);
        }

        sb.append(CR);

        // Attributes
        if(schemaElement.hasSchemaAttributes()) {
            sb.append("Attributes" + CR);
            sb.append("----------" + CR);
            SchemaAttribute[] atts = schemaElement.getSchemaAttributes();
            for(int i = 0; i < atts.length; i++) {
                SchemaAttribute schemaAttribute = atts[i];
                xmlPath = schemaAttribute.getXMLPath();

                sb.append("Name:\t" + schemaAttribute.getName() + CR);
                sb.append("Friendly:\t" + _schemaController.getElementFriendlyName(xmlPath) + CR);
                sb.append("Tip:\t" + _schemaController.getElementTip(xmlPath) + CR);
                sb.append("Path:\t" + xmlPath.getPath() + CR);
                sb.append("Type:\t" + schemaAttribute.getTypeName() + CR);
                sb.append("Base:\t" + schemaAttribute.getBaseTypeName() + CR);
                sb.append("Atomic Base:\t" + schemaAttribute.getAtomicBaseTypeName() + CR);
                sb.append("Use:\t" + schemaAttribute.getUse() + CR);
                sb.append("MinLength:\t" + schemaAttribute.getMinLength() + CR);
                sb.append("MaxLength:\t" + schemaAttribute.getMaxLength() + CR);
                sb.append("Default:\t" + _schemaController.getDefaultValue(schemaAttribute) + CR);

                // Vocab
                rvList = _schemaController.getVocabularyList(schemaAttribute);
                if(rvList != null) {
                    String[] vocab = rvList.getList();
                    sb.append("Vocabulary:\t");
                    for(int j = 0; j < vocab.length; j++) {
                        sb.append(vocab[j]);
                        if(j < vocab.length-1) sb.append(", ");
                    }
                    sb.append(CR);
                }
                sb.append(CR);
            }
        }

        setText(sb.toString());
        setCaretPosition(0);
    }

}
