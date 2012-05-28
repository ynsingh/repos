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

import org.jdom.JDOMException;

import uk.ac.reload.diva.util.FileUtils;
import uk.ac.reload.editor.EditorHandler;
import uk.ac.reload.editor.contentpackaging.xml.CP113_SchemaController;
import uk.ac.reload.editor.learningdesign.LD_EditorHandler;
import uk.ac.reload.moonunit.schema.SchemaException;


/**
 * The Learning Design C CP 1.1.3 SchemaModel Controller
 *
 * @author Phillip Beauvoir
 * @version $Id: LDC10_CP113_SchemaController.java,v 1.1 1998/03/26 15:32:14 ynsingh Exp $
 */
public class LDC10_CP113_SchemaController
extends CP113_SchemaController
implements ILD_SchemaController
{
	/**
	 * The child LD SchemaController
	 */
	private LDC10_SchemaController _ldSchemaController;

	/**
     * Default Constructor
	 * @throws SchemaException
	 * @throws IOException
	 * @throws JDOMException
     */
	public LDC10_CP113_SchemaController() throws JDOMException, SchemaException, IOException { 
	    super();
	    
	    // Attach sub-Schema
	    _ldSchemaController = new LDC10_SchemaController();
	    addImportedSchema(_ldSchemaController, "imsld");
	    attachSchemaElement(_ldSchemaController, "organizationsType", "learning-design");
	}

	public String getVersion() {
	    return LD_EditorHandler.IMS_LEARNING_DESIGN_C_1_0_CP_1_1_3;
	}

    /* (non-Javadoc)
     * @see uk.ac.reload.editor.learningdesign.xml.ILD_SchemaController#getLevel()
     */
    public String getLevel() {
        return _ldSchemaController.getLevel();
    }

    /**
	 * Copy the Schema Files to the Project Folder
	 */
	public void copySchemaFilesToFolder(File projectFolder) throws IOException {
		super.copySchemaFilesToFolder(projectFolder);
	    FileUtils.copyFolder(new File(EditorHandler.SCHEMASHIPPEDFOLDER, "imsld_100/A"), projectFolder);
	    FileUtils.copyFolder(new File(EditorHandler.SCHEMASHIPPEDFOLDER, "imsld_100/B"), projectFolder);
	    FileUtils.copyFolder(new File(EditorHandler.SCHEMASHIPPEDFOLDER, "imsld_100/C"), projectFolder);
	}
}