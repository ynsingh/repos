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


package uk.ac.reload.editor.gui.widgets;


import uk.ac.reload.dweezil.util.DweezilUIManager;
import uk.ac.reload.editor.IIcons;
import uk.ac.reload.moonunit.SchemaController;
import uk.ac.reload.moonunit.schema.SchemaElement;
import uk.ac.reload.moonunit.vocab.VocabularyList;

/**
 * A Widget factory for a view.
 *
 * @author Phillip Beauvoir
 * @version $Id: WidgetFactory.java,v 1.1 1998/03/26 15:32:14 ynsingh Exp $
 */
public final class WidgetFactory
implements IIcons
{
	
	/**
	 * Constructor
	 */
	private WidgetFactory() {
	}
	
	/**
	 * @return A Text Widget suitable for the Value Panel display.
	 * This actually needs to be mapped in a Helper File
	 */
	public static Widget getWidget(SchemaController schemaController, SchemaElement schemaElement) {
		// Default editor for unknown type
		if(schemaElement == null) {
		    // Return, because there's no point in querying a null schemaElement
		    return getTextFieldWidget();
		}
		
		Widget component = null;
		
		// Try for a Vocabulary first
		VocabularyList vocab = schemaController.getVocabularyList(schemaElement);
		
		// If a Vocabulary, use a listbox
		if(vocab != null) {
		    component = getListBoxWidget();
		    ((ListBoxWidget) component).setListData(vocab.getList());
		}
		
		// Not a Vocabulary
		else {
		    String widget_type = schemaController.getWidgetType(schemaElement.getXMLPath());
		    if(widget_type != null) {
		        if(widget_type.equals("textpane")) {
		            component = getTextPaneWidget();
		        }
		        else if(widget_type.equals("numberfield")) {
		            component = getNumberFieldWidget();
		        }
		        else if(widget_type.equals("filechooser")) {
		            component = getFileTextFieldWidget();
		        }
		        else {
		            component = getTextFieldWidget();
		        }
		    }
		    // Default
		    else {
		        component = getTextFieldWidget();
		    }
		}
		
		// Set Max String length if there is one
		int maxLength = -1;
		String value = schemaController.getFacetValue(schemaElement, "maxLength");
		if(value != null) {
		    maxLength = Integer.parseInt(value);
		}
		component.setMaxLength(maxLength);

		return component;
	}
	
	/**
	 * @return the Widget
	 */
	public static TextFieldWidget getTextFieldWidget() {
		return new TextFieldWidget();
	}
	
	/**
	 * @return the Widget
	 */
	public static ListBoxWidget getListBoxWidget() {
		return new ListBoxWidget();
	}
	
	/**
	 * @return the Widget
	 */
	public static TextPaneWidget getTextPaneWidget() {
		return new TextPaneWidget();
	}
	
	/**
	 * @return the Widget
	 */
	public static NumberFieldWidget getNumberFieldWidget() {
		return new NumberFieldWidget();
	}
	
	/**
	 * @return a File chooser
	 */
	public static FileTextFieldWidget getFileTextFieldWidget() {
		return new FileTextFieldWidget(FileTextFieldWidget.FILE_TYPE, "Select File", DweezilUIManager.getIcon(ICON_OPEN));
	}
	
}