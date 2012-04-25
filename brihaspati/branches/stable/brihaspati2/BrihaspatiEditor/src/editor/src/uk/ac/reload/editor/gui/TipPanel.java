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
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import uk.ac.reload.dweezil.gui.GradientPanel;
import uk.ac.reload.dweezil.util.DweezilUIManager;
import uk.ac.reload.jdom.XMLPath;
import uk.ac.reload.moonunit.SchemaController;
import uk.ac.reload.moonunit.schema.SchemaElement;


/**
 * A Panel to display the tip
 *
 * @author Phillip Beauvoir
 * @version $Id: TipPanel.java,v 1.1 1998/03/26 15:24:11 ynsingh Exp $
 */
public class TipPanel
extends GradientPanel
{
	/**
	 * The JLabel to display the friendly name for the Element
	 */
    private JLabel _nameLabel;
	
	/**
	 * The Textpane to display the tip
	 */
    private JLabel _tipLabel;
	
	/**
	 * Constructor
	 */
	public TipPanel() {
		setLayout(new BorderLayout());
		
		// Name label
		_nameLabel = new JLabel();
		//_nameLabel.setForeground(Color.BLUE);
		_nameLabel.setFont(DweezilUIManager.boldFont12);
		add(_nameLabel, BorderLayout.NORTH);
		
		_tipLabel = new JLabel();
		_tipLabel.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));
		_tipLabel.setVerticalAlignment(SwingConstants.TOP);
		
		add(_tipLabel, BorderLayout.CENTER);
		
		Border border = BorderFactory.createCompoundBorder(BorderFactory.createLoweredBevelBorder(),
				BorderFactory.createEmptyBorder(5, 10, 5, 10));
		setBorder(border);
	}
	
	/**
	 * Clear the Settings
	 */
	public void clear() {
		_nameLabel.setText("");
		_tipLabel.setText("");
	}
	
	/**
	 * Update the text tip panel
	 */
	public void setTip(SchemaController schemaController, SchemaElement schemaElement) {
		if(schemaElement != null) {
			XMLPath xmlPath = schemaElement.getXMLPath();
			
			// Friendly Name
			String fname = schemaController.getElementFriendlyName(xmlPath);
			if(fname == null) fname = schemaElement.getName();
			_nameLabel.setText(" " + fname);
			
			// Tip
			String tip = schemaController.getElementTip(xmlPath);
			if(tip == null) _tipLabel.setText("");
			//else _tipLabel.setText("<html>" + "<font color=blue>" + tip + "</font><br>");
			else _tipLabel.setText("<html>" + tip + "</font><br>");
		}
		else {
			_nameLabel.setText("");
			_tipLabel.setText("");
		}
	}
	
}
