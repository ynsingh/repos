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

package uk.ac.reload.editor.metadata.editor2;

import uk.ac.reload.editor.datamodel.DataElement;
import uk.ac.reload.editor.gui.GroupingPanel;
import uk.ac.reload.editor.gui.widgets.DataElementComboBox;
import uk.ac.reload.editor.metadata.datamodel.IMD_DataModelHandler;
import uk.ac.reload.editor.metadata.datamodel.MD_DataModel;
import uk.ac.reload.moonunit.SchemaController;
import uk.ac.reload.moonunit.schema.SchemaNode;
import uk.ac.reload.moonunit.vocab.VocabularyList;


/**
 * Base Panel
 * 
 * @author Phillip Beauvoir
 * @version $Id: MD_Panel.java,v 1.1 1998/03/26 15:32:14 ynsingh Exp $
 */
public abstract class MD_Panel
extends GroupingPanel
implements IMD_DataModelHandler
{
    private static String[] blanklist = {"<list should be here>" };
    
    protected MD_DataModel _mdDataModel;
    
    /**
     * Default Constructor
     */
    protected MD_Panel() {
    }
    
    /* (non-Javadoc)
     * @see uk.ac.reload.editor.metadata.datamodel.IMD_DataModelHandler#setDataModel(uk.ac.reload.editor.metadata.datamodel.MD_DataModel)
     */
    public void setDataModel(MD_DataModel mdDataModel) {
        _mdDataModel = mdDataModel;
    }
    
	/**
	 * Populate a combo box with items from a vocab list
	 * @param comboBox The Combo Box to set
	 * @param dataElement The DataElement
	 * @param defvalue The default value or null if no default
	 */
	protected void setComboList(DataElementComboBox comboBox, DataElement dataElement, String defvalue) {
	    SchemaController schemaController = _mdDataModel.getSchemaDocument().getSchemaController();
	    SchemaNode schemaNode = schemaController.getSchemaNode(dataElement.getXMLElementPath());
		VocabularyList vList = schemaController.getVocabularyList(schemaNode);
		if(vList != null) {
		    String[] list = vList.getList();
		    comboBox.setItems(list);
		}
		else {
			comboBox.setItems(blanklist);
		}
		
		comboBox.setElement(dataElement, defvalue);
	}
}
