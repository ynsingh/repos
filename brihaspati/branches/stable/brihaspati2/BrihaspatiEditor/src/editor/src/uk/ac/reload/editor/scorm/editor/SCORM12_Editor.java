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


package uk.ac.reload.editor.scorm.editor;

import uk.ac.reload.editor.contentpackaging.editor.CP_Editor;
import uk.ac.reload.editor.scorm.xml.SCORM12_Package;


/**
 * The SCORM Content Package Editor Frame
 *
 *
 * @author Phillip Beauvoir
 * @version $Id: SCORM12_Editor.java,v 1.1 1998/03/26 15:32:14 ynsingh Exp $
 */
public class SCORM12_Editor extends CP_Editor {
	
	/**
	 * Constructor
	 */
	public SCORM12_Editor() {
		super();
		setFrameIcon(ICON_CPSCORM);
		setTitle("SCORM Package");
	}
	
	public SCORM12_Editor(SCORM12_Package cp) {
	    super(cp);
		setFrameIcon(ICON_CPSCORM);
	    setTitle("SCORM Package - " + cp.getProjectName());
	}
}