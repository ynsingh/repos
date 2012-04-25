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

package uk.ac.reload.editor.simplesequencing;

import java.io.File;
import java.io.IOException;

import org.jdom.JDOMException;

import uk.ac.reload.editor.EditorHandler;
import uk.ac.reload.moonunit.ProfiledSchemaController;
import uk.ac.reload.moonunit.schema.SchemaException;

/**
 * The Simple Sequencing SchemaModel Controller
 *
 * @author Phillip Beauvoir
 * @version $Id: SS_SchemaController.java,v 1.1 1998/03/26 15:24:12 ynsingh Exp $
 */
public class SS_SchemaController
extends ProfiledSchemaController
{
    /**
     * Schema file
     */ 
	public static File fileSchemaSS1_0 = new File(new File(EditorHandler.SCHEMAMODELFOLDER, "imsss_100"), "imsss_v1p0.xsd");
	
	/**
	 * Default Profile
	 */
	public static String defaultSSProfile = "SS Default Profile";

    /**
	 * Constructor with Default Profile
     * @throws JDOMException
     * @throws IOException
     * @throws SchemaException
	 */
	public SS_SchemaController() throws JDOMException, SchemaException, IOException {
	    super();
	}

    public String getRootElementName() {
		return "sequencing";
	}
	
	public String getVersion() {
		return SS_EditorHandler.IMS_SIMPLE_SEQUENCING_1_0;
	}

    /* (non-Javadoc)
     * @see uk.ac.reload.moonunit.handler.SchemaController#getSchemaFile()
     */
    public File getSchemaFile() {
        return fileSchemaSS1_0;
    }

    /* (non-Javadoc)
     * @see uk.ac.reload.moonunit.SchemaController#getProfileFolder()
     */
    public File getProfileFolder() {
        return SS_EditorHandler.PROFILE_FOLDER;
    }

    /* (non-Javadoc)
     * @see uk.ac.reload.moonunit.SchemaController#getSchemaHelperFolder()
     */
    public File getSchemaHelperFolder() {
        return SS_EditorHandler.SCHEMAHELPER_FOLDER;
    }

    /* (non-Javadoc)
     * @see uk.ac.reload.moonunit.SchemaController#getVocabFolder()
     */
    public File getVocabFolder() {
        return SS_EditorHandler.VOCAB_FOLDER;
    }
    
    /* (non-Javadoc)
     * @see uk.ac.reload.moonunit.SchemaController#getDefaultProfilename()
     */
    public String getDefaultProfileName() {
        return defaultSSProfile;
    }
}