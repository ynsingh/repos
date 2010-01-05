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

package uk.ac.reload.editor.gui;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import uk.ac.reload.editor.datamodel.ElementBinding;


/**
 * A Panel to hold the Element and Attribute Editors
 *
 * @author Phillip Beauvoir
 * @version $Id: ElementAttributePanel.java,v 1.1 1998/03/26 15:24:11 ynsingh Exp $
 */
public class ElementAttributePanel
extends JPanel
{
	/**
	 * The ElementEditor
	 */
    private ElementEditor _elementPanel;
	
	/**
	 * The AttributeEditor
	 */
    private AttributeEditor _attrPanel;
	
	/**
	 * SplitPane
	 */
    private JSplitPane _splitPane;
	
	/**
	 * Constructor
	 */
	public ElementAttributePanel() {
		setOpaque(false);
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));
		
		// Value Panel
		_elementPanel = createElementEditor();
		
		// Attribute Panel
		_attrPanel = createAttributeEditor();
		
		// Splitter
		_splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		_splitPane.setOneTouchExpandable(true);
		_splitPane.setTopComponent(_elementPanel);
		_splitPane.setBottomComponent(_attrPanel);
		add(_splitPane, BorderLayout.CENTER);
	}
	
	/**
	 * Called after Panel is shown so we can init stuff
	 */
	public void initView() {
		// For some reason this figure has to be set much lower than it should be
		_splitPane.setDividerLocation(0.12);
	}
	
	/**
	 * Setup the element Panel
	 */
	protected ElementEditor createElementEditor() {
		return new ElementEditor();
	}
	
	/**
	 * Setup the Attribute Panel
	 */
	protected AttributeEditor createAttributeEditor() {
		return new AttributeEditor();
	}
	
	/**
	 * @return the element Panel
	 */
	public ElementEditor getElementEditor() {
		return _elementPanel;
	}
	
	/**
	 * @return the Attribute Panel
	 */
	public AttributeEditor getAttributeEditor() {
		return _attrPanel;
	}
	
	/**
	 * @return the Splitpane
	 */
	public JSplitPane getSplitpane() {
		return _splitPane;
	}
	
	/**
	 * Set The Panels to display the data for the Element
	 * @param elementBinding The ElementBinding class containing Element,
	 * SchemaElement and Document
	 */
	public void setElementBinding(ElementBinding elementBinding) {
		// Update Value Panel
		_elementPanel.setElementBinding(elementBinding);
		
		// Update Attribute panel
		_attrPanel.setElementBinding(elementBinding);
	}
	
	/**
	 * Clear the Settings
	 */
	public void clear() {
		//_elementPanel.clear();
		//_attrPanel.clear();
	}
	
	/**
	 * Clean up
	 */
	public void cleanup() {
		_elementPanel.cleanup();
		_attrPanel.cleanup();
	}
}
