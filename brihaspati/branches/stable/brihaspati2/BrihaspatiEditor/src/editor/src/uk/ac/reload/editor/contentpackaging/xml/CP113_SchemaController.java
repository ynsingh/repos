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

package uk.ac.reload.editor.contentpackaging.xml;

import java.io.File;
import java.io.IOException;

import org.jdom.JDOMException;

import uk.ac.reload.diva.util.FileUtils;
import uk.ac.reload.editor.EditorHandler;
import uk.ac.reload.editor.contentpackaging.CP_EditorHandler;
import uk.ac.reload.editor.metadata.xml.MD122_SchemaController;
import uk.ac.reload.editor.metadata.xml.MD_SchemaController;
import uk.ac.reload.moonunit.schema.SchemaException;

/**
 * The CP Schema Controller
 *
 * @author Phillip Beauvoir
 * @version $Id: CP113_SchemaController.java,v 1.1 1998/03/26 15:32:14 ynsingh Exp $
 */
public class CP113_SchemaController
extends CP_SchemaController
{
	// Schema file
	public static File fileSchemaCP1_1_3 = new File(new File(EditorHandler.SCHEMAMODELFOLDER, "imscp_113"), "imscp_v1p1.xsd");

	/**
	 * Default Constructor
	 * @throws JDOMException
	 * @throws IOException
	 * @throws SchemaException
	 */
	public CP113_SchemaController() throws JDOMException, SchemaException, IOException {
	    super();
	}
	
	public String getVersion() {
	    return CP_EditorHandler.IMS_CONTENT_PACKAGING_1_1_3;
	}

	/**
	 * Copy the Schema Files to the Project Folder
	 */
	public void copySchemaFilesToFolder(File projectFolder) throws IOException {
		super.copySchemaFilesToFolder(projectFolder);
	    FileUtils.copyFolder(new File(EditorHandler.SCHEMASHIPPEDFOLDER, "imscp_113"), projectFolder);
	}

    /**
     * @return The default MD_SchemaController for this type of CP
     * @throws JDOMException
     * @throws IOException
     * @throws SchemaException
     */
    public MD_SchemaController getDefaultMD_SchemaController() throws JDOMException, SchemaException, IOException {
        return new MD122_SchemaController();
    }

    /* (non-Javadoc)
     * @see uk.ac.reload.moonunit.handler.SchemaController#getSchemaFile()
     */
    public File getSchemaFile() {
        return fileSchemaCP1_1_3;
    }
}