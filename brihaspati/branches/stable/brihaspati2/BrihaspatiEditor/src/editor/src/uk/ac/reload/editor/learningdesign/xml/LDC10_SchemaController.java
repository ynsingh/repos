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

import uk.ac.reload.editor.learningdesign.LD_EditorHandler;
import uk.ac.reload.moonunit.schema.SchemaException;


/**
 * Learning Design 1.0 C Schema
 */
public class LDC10_SchemaController extends LD_SchemaController {
	
	// LD Schema file
	public static File fileSchemaLDC1_0 = new File(SCHEMAMODELFOLDER100, "author/C/IMS_LD_Level_C.xsd");
	// public static File fileSchemaLDC1_0 = new File(SCHEMAMODELFOLDER100, "ims/C/IMS_LD_Level_C.xsd");

    /**
     * Constructor
     * @throws SchemaException
     * @throws IOException
     */
    public LDC10_SchemaController() throws SchemaException, IOException {
        super();
    }
    
    /* (non-Javadoc)
     * @see uk.ac.reload.moonunit.SchemaController#getVersion()
     */
    public String getVersion() {
        return LD_EditorHandler.IMS_LEARNING_DESIGN_C_1_0;
    }

    /* (non-Javadoc)
     * @see uk.ac.reload.moonunit.SchemaController#getSchemaFile()
     */
    public File getSchemaFile() {
        return fileSchemaLDC1_0;
    }
    
    /* (non-Javadoc)
     * @see uk.ac.reload.editor.learningdesign.xml.LD_SchemaController#getLevel()
     */
    public String getLevel() {
        return "C";
    }
}