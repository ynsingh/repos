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

package uk.ac.reload.editor.learningdesign.xml;

import java.io.File;
import java.io.IOException;

import uk.ac.reload.editor.EditorHandler;
import uk.ac.reload.moonunit.SchemaController;
import uk.ac.reload.moonunit.schema.SchemaException;

/**
 * The Learning Design SchemaModel Controller
 *
 * @author Phillip Beauvoir
 * @version $Id: LD_SchemaController.java,v 1.1 1998/03/26 15:32:14 ynsingh Exp $
 */
public abstract class LD_SchemaController
extends SchemaController
implements ILD_SchemaController
{
    /**
	 * Schema model Folder IMS LD 1.00
	 */
	public static File SCHEMAMODELFOLDER100 = new File(EditorHandler.SCHEMAMODELFOLDER, "imsld_100");


	/**
	 * Constructor
	 * @throws SchemaException
	 * @throws IOException
	 */
	protected LD_SchemaController() throws SchemaException, IOException {
	    super();
	    loadSchemaModel();
	}
	
    /* (non-Javadoc)
     * @see uk.ac.reload.moonunit.SchemaController#getRootElementName()
     */
    public String getRootElementName() {
        return "learning-design";
    }
}