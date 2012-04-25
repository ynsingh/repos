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

import uk.ac.reload.editor.datamodel.DataComponent;
import uk.ac.reload.editor.gui.TitledEditorPanel;



/**
 * A Panel to provide information about a Reference to an Element
 *
 * @author Phillip Beauvoir
 * @version $Id: ReferencePanel.java,v 1.1 1998/03/26 15:40:32 ynsingh Exp $
 */
public class ReferencePanel
extends TitledEditorPanel {
    
    /**
	 * Default Constructor
	 */
	public ReferencePanel() {
	    
	}
	
	/* (non-Javadoc)
	 * @see uk.ac.reload.editor.gui.TitledEditorPanel#setComponent(uk.ac.reload.editor.datamodel.DataComponent)
	 */
	public void setComponent(DataComponent dataComponent) {
	    super.setComponent(dataComponent);
	}
	
	/**
	 * Set up the view
	 */
	protected void setupView() {
		super.setupView();
		getTitleLabel().getLabel().setToolTipText(null);
		getTitleLabel().setEditable(false);
	}
}
