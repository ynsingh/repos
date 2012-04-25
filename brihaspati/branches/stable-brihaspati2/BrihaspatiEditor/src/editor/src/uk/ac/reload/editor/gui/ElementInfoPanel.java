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
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import uk.ac.reload.editor.datamodel.ElementBinding;



/**
 * A Panel to display the tip, the Value and Attributes of a JDOM Element
 *
 * @author Phillip Beauvoir
 * @version $Id: ElementInfoPanel.java,v 1.1 1998/03/26 15:24:11 ynsingh Exp $
 */
public class ElementInfoPanel
extends JPanel
{
	/**
	 * The Panel to hold the value and attribute panels
	 */
    private JPanel _mainPanel;
	
	/**
	 * The Panel to display the tip
	 */
    private TipPanel _tipPanel;
	
	/**
	 * The Panel to edit the Element
	 */
    private ElementAttributePanel _elementAttPanel;
	
	/**
	 * Constructor
	 */
	public ElementInfoPanel() {
		setBackground(Color.lightGray);
		setLayout(new BorderLayout());
		setMinimumSize(new Dimension(0, 0));
		
		// Main Panel
		_mainPanel = setupMainPanel();
		
		// Value Panel
		_elementAttPanel = createElementAttributePanel();
		_mainPanel.add(_elementAttPanel);
		
		// Tip Panel
		_tipPanel = new TipPanel();
		_mainPanel.add(_tipPanel);
		
		add(_mainPanel, BorderLayout.CENTER);
	}
	
	/**
	 * Called after Panel is shown so we can init stuff
	 */
	public void initView() {
		_elementAttPanel.initView();
	}
	
	/**
	 * Over-ride this to setup a Content Packaging Element Panel
	 */
	protected ElementAttributePanel createElementAttributePanel() {
		return new ElementAttributePanel();
	}
	
	/**
	 * Setup the Main Panel
	 */
	protected JPanel setupMainPanel() {
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(1, 2));
		mainPanel.setOpaque(false);
		mainPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		return mainPanel;
	}
	
	/**
	 * Set The Panels to display the data for the Element
	 * @param elementBinding The ElementBinding class containing Element,
	 * SchemaElement and Document
	 */
	public void setElementBinding(ElementBinding elementBinding) {
		// Update Tip Panel
		_tipPanel.setTip(elementBinding.getSchemaController(), elementBinding.getSchemaElement());
		
		// Update Value Panel
		_elementAttPanel.setElementBinding(elementBinding);
		
		// Repaint
		validate();
		repaint();
	}
	
	/**
	 * Clear the Settings
	 */
	public void clear() {
		_tipPanel.clear();
		_elementAttPanel.clear();
	}
	
	/**
	 * Clean up
	 */
	public void cleanup() {
		_elementAttPanel.cleanup();
	}
}