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

package uk.ac.reload.editor.learningdesign.editor.shared;

import javax.swing.JPanel;

import uk.ac.reload.dweezil.gui.LabelComponentPanel;
import uk.ac.reload.dweezil.gui.layout.SGLayout;
import uk.ac.reload.editor.gui.widgets.DataElementCheckBox;
import uk.ac.reload.editor.gui.widgets.DataElementTextField;


/**
 * Tiny Sub-Panel for IsVisible checkbox and Parameters field
 *
 * @author Phillip Beauvoir
 * @version $Id: IsVisibleParametersPanel.java,v 1.1 1998/03/26 15:40:32 ynsingh Exp $
 */
public class IsVisibleParametersPanel
extends JPanel
{
    /**
     * Is Visible Check box
     */
    private DataElementCheckBox _cbIsVisible;

    /**
	 * Parameters field
	 */
	private DataElementTextField _tfParameters;


    /**
     * Constructor
     */
    public IsVisibleParametersPanel() {
		setOpaque(false);
	    
		SGLayout layout = new SGLayout(1, 2, 0, 5);
	    layout.setColumnScale(0, 0.3);
	    setLayout(layout);
	    
	    // ISVISIBLE
	    add(getIsVisibleCheckBox());

	    // PARAMETERS
	    LabelComponentPanel panelParameters = new LabelComponentPanel("Parameters:", getParametersField(), 0.3, 0.0);
	    add(panelParameters);
    }
    
    /**
     * @return The Parameters Text Field
     */
    public DataElementTextField getParametersField() {
        if(_tfParameters == null) {
            _tfParameters = new DataElementTextField();
        }
        
        return _tfParameters;
    }
    
    /**
     * @return Is Visible Check box
     */
    public DataElementCheckBox getIsVisibleCheckBox() {
        if(_cbIsVisible == null) {
            _cbIsVisible  = new DataElementCheckBox("Visible", true);
            _cbIsVisible.setBorder(null);
        }
        
        return _cbIsVisible;
    }
}