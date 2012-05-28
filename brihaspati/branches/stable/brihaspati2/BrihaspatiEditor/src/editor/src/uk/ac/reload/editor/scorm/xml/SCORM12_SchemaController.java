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

import javax.swing.Icon;

import org.jdom.JDOMException;
import org.jdom.Namespace;

import uk.ac.reload.dweezil.util.DweezilUIManager;
import uk.ac.reload.editor.contentpackaging.CP_EditorHandler;
import uk.ac.reload.editor.contentpackaging.xml.CP_SchemaController;
import uk.ac.reload.editor.prefs.EditorPrefs;
import uk.ac.reload.editor.scorm.SCORM12_EditorHandler;
import uk.ac.reload.moonunit.contentpackaging.CP_Core;
import uk.ac.reload.moonunit.schema.SchemaException;

/**
 * The SCORM 1.2 CP Abstract SchemaController
 *
 * @author Phillip Beauvoir
 * @version $Id: SCORM12_SchemaController.java,v 1.1 1998/03/26 15:32:14 ynsingh Exp $
 */
public abstract class SCORM12_SchemaController
extends CP_SchemaController
{
	/**
	 * Default Profile
	 */
	public static String defaultSCORMProfile = "SCORM 1.2 Default Profile";

	/**
	 * Default Constructor
	 * @throws JDOMException
	 * @throws IOException
	 * @throws SchemaException
	 */
	public SCORM12_SchemaController() throws JDOMException, SchemaException, IOException {
	    super();
	}
	
	/**
	 * Over-ride to get The Leaf Icon
	 */
	public Icon getLeafIcon(String name, Namespace ns) {
		// Manifest
		if(name.equals(CP_Core.MANIFEST)) {
			return DweezilUIManager.getIcon(ICON_CPSCORM);
		}
		
		// ADL Namespace
		if(CP_EditorHandler.ADLCP_NAMESPACE_12.equals(ns)) {
			return DweezilUIManager.getIcon(ICON_ADL);
		}
		
		return super.getLeafIcon(name, ns);
	}

    /* (non-Javadoc)
     * @see uk.ac.reload.moonunit.SchemaController#getProfileFolder()
     */
    public File getProfileFolder() {
        return SCORM12_EditorHandler.PROFILE_FOLDER;
    }

    /* (non-Javadoc)
     * @see uk.ac.reload.moonunit.SchemaController#getSchemaHelperFolder()
     */
    public File getSchemaHelperFolder() {
        return SCORM12_EditorHandler.SCHEMAHELPER_FOLDER;
    }

    /* (non-Javadoc)
     * @see uk.ac.reload.moonunit.SchemaController#getVocabFolder()
     */
    public File getVocabFolder() {
        return SCORM12_EditorHandler.VOCAB_FOLDER;
    }
	
    /* (non-Javadoc)
     * @see uk.ac.reload.moonunit.SchemaController#getDefaultProfilename()
     */
    public String getDefaultProfileName() {
   		// Get from User Prefs
   		String defProfileName = EditorPrefs.getInstance().getValue(EditorPrefs.SCORM_DEFAULT_PROFILE);
        return defProfileName == null ? defaultSCORMProfile : defProfileName;
    }
}