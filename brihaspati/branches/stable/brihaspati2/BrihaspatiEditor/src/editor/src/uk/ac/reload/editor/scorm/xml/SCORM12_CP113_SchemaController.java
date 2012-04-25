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

package uk.ac.reload.editor.scorm.xml;

import java.io.File;
import java.io.IOException;

import org.jdom.JDOMException;

import uk.ac.reload.diva.util.FileUtils;
import uk.ac.reload.editor.EditorHandler;
import uk.ac.reload.editor.metadata.xml.MD121_SchemaController;
import uk.ac.reload.editor.metadata.xml.MD_SchemaController;
import uk.ac.reload.editor.scorm.SCORM12_EditorHandler;
import uk.ac.reload.moonunit.schema.SchemaException;

/**
 * The SCORM 1.2 CP 1.1.3 SchemaModel Controller
 *
 * @author Phillip Beauvoir
 * @version $Id: SCORM12_CP113_SchemaController.java,v 1.1 1998/03/26 15:32:14 ynsingh Exp $
 */
public class SCORM12_CP113_SchemaController
extends SCORM12_SchemaController
{
    // Schema file
	public static File fileSchemaSCORM1_2_IMCP1_1_3 = new File(new File(EditorHandler.SCHEMAMODELFOLDER, "adlcp_120_113"), "imscp_v1p1.xsd");
	
    // ADL Schema file
	public static File fileSchemaADLSCORM1_2_IMCP1_1_3 = new File(new File(EditorHandler.SCHEMAMODELFOLDER, "adlcp_120_113"), "adlcp_rootv1p2.xsd");

	/**
	 * Default Constructor
	 * @throws JDOMException
	 * @throws IOException
	 * @throws SchemaException
	 */
	public SCORM12_CP113_SchemaController() throws JDOMException, SchemaException, IOException {
	    super();
	}
	
	public String getVersion() {
	    return SCORM12_EditorHandler.ADL_SCORM_1_2_CP_1_1_3;
	}

	/**
	 * Copy the Schema Files to the Project Folder
	 */
	public void copySchemaFilesToFolder(File projectFolder) throws IOException {
	    super.copySchemaFilesToFolder(projectFolder);
	    FileUtils.copyFolder(new File(EditorHandler.SCHEMASHIPPEDFOLDER, "adlcp_120_113"), projectFolder);
	}

    /**
     * @return The default MD_SchemaController for this type of CP
     * @throws JDOMException
     * @throws IOException
     * @throws SchemaException
     */
    public MD_SchemaController getDefaultMD_SchemaController() throws JDOMException, SchemaException, IOException {
        return new MD121_SchemaController();
    }

    /* (non-Javadoc)
     * @see uk.ac.reload.moonunit.handler.SchemaController#getSchemaFile()
     */
    public File getSchemaFile() {
        return fileSchemaSCORM1_2_IMCP1_1_3;
    }
}