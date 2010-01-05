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
import java.util.Hashtable;

import javax.swing.Icon;

import org.jdom.JDOMException;
import org.jdom.Namespace;

import uk.ac.reload.dweezil.util.DweezilUIManager;
import uk.ac.reload.editor.EditorHandler;
import uk.ac.reload.editor.EditorInternalFrame;
import uk.ac.reload.editor.IEditorHandler;
import uk.ac.reload.editor.IIcons;
import uk.ac.reload.moonunit.SchemaController;
import uk.ac.reload.moonunit.schema.SchemaException;


/**
 * The Editor implementation for IMS SS.<br>
 *
 * @author Phillip Beauvoir
 * @version $Id: SS_EditorHandler.java,v 1.1 1998/03/26 15:24:12 ynsingh Exp $
 */
public class SS_EditorHandler
implements IEditorHandler, IIcons
{
    public static final String IMS_SIMPLE_SEQUENCING_1_0 = "IMS Simple Sequencing 1.0";

	// Namespace prefix
    public static String IMSSS_NAMESPACE_PREFIX = "imsss";
    
    // SS Version 1.0
    public static Namespace IMSSS_NAMESPACE_10 = Namespace.getNamespace("http://www.imsglobal.org/xsd/imsss");

    static Hashtable SUPPORTED_NAMESPACES = new Hashtable();
    
    static {
		// Add to table of Supported Namespaces mapped to Version names
		SUPPORTED_NAMESPACES.put(IMSSS_NAMESPACE_10, IMS_SIMPLE_SEQUENCING_1_0);
    }
    
    public static final File HELPER_FOLDER = new File(EditorHandler.HELPERFOLDER, "ss");
    public static final File PROFILE_FOLDER = new File(HELPER_FOLDER, "profile");
    public static final File SCHEMAHELPER_FOLDER = new File(HELPER_FOLDER, "schemahelper");
    public static final File VOCAB_FOLDER = new File(HELPER_FOLDER, "vocab");

    /**
	 * Constructor
	 */
	public SS_EditorHandler() {

	}
	
    public boolean canCreateDocuments() {
        return false;
    }

    public boolean canEditFile(File file) {
	    return false;
	}

    public EditorInternalFrame editFile(File file) {
        return null;
    }

	public EditorInternalFrame newDocument() {
		return null;
	}
	
	public String getName() {
	    return "IMS Simple Sequencing";
	}
	
	public Icon getIcon() {
	    return DweezilUIManager.getIcon(ICON_NODE);
	}

	public String[] getSupportedVersions() {
	    return new String[] {
	            IMS_SIMPLE_SEQUENCING_1_0
	    };
	}

	public String getDefaultVersion() {
	    return IMS_SIMPLE_SEQUENCING_1_0;
	}

	/**
     * Get a new instance implementation of the appropriate SchemaController
     * for a given version key.<br>
	 * @throws JDOMException
	 * @throws IOException
	 * @throws SchemaException
    */
    public SchemaController getSchemaControllerInstance(String version) throws JDOMException, SchemaException, IOException {
        SchemaController schemaController = null;
        
        if(IMS_SIMPLE_SEQUENCING_1_0.equals(version)) {
            schemaController = new SS_SchemaController();
        }
        
        return schemaController;
    }
}
