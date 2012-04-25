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

package uk.ac.reload.editor.metadata.xml;

import java.io.File;
import java.io.IOException;

import javax.swing.Icon;

import org.jdom.JDOMException;
import org.jdom.Namespace;

import uk.ac.reload.dweezil.util.DweezilUIManager;
import uk.ac.reload.editor.IIcons;
import uk.ac.reload.editor.gui.TreeIconInterface;
import uk.ac.reload.editor.metadata.MD_EditorHandler;
import uk.ac.reload.editor.prefs.EditorPrefs;
import uk.ac.reload.moonunit.ProfiledSchemaController;
import uk.ac.reload.moonunit.schema.SchemaElement;
import uk.ac.reload.moonunit.schema.SchemaException;

/**
 * The Metadata Schema Controller - this interfaces from the various helpers
 *
 * @author Phillip Beauvoir
 * @version $Id: MD_SchemaController.java,v 1.1 1998/03/26 15:32:14 ynsingh Exp $
 */
public abstract class MD_SchemaController
extends ProfiledSchemaController
implements TreeIconInterface, IIcons
{
	/**
	 * Default Profile
	 */
	public static String defaultMDProfile = "IMS LRM Profile";

	/**
	 * Default Constructor
	 * @throws JDOMException
	 * @throws IOException
	 * @throws SchemaException
	 */
	public MD_SchemaController() throws JDOMException, SchemaException, IOException {
	    super();
	}
	
	/**
	 * Get the (9 in this case) top level IMS Element Categories
	 * @return An Enumeration of SchemaElement objects
	 */
	public SchemaElement[] getTopLevelElements() {
		return getSchemaModel().getRootElement().getChildren();
	}
	
	/**
	 * Get The Leaf Icon
	 */
	public Icon getLeafIcon(String name, Namespace ns) {
		if(name.equals(getRootElementName())) {
		    return DweezilUIManager.getIcon(ICON_MD);
		}
		else {
		    return DweezilUIManager.getIcon(ICON_MDLEAF);
		}
	}
	
	/**
	 * Get The Open Icon
	 */
	public Icon getOpenIcon(String name, Namespace ns) {
		if(name.equals(getRootElementName())) {
		    return DweezilUIManager.getIcon(ICON_MD);
		}
		else {
		    return DweezilUIManager.getIcon(ICON_MDFOLDER);
		}
	}
	
	/**
	 * Get The Closed Icon
	 */
	public Icon getClosedIcon(String name, Namespace ns) {
		return getOpenIcon(name, ns);
	}
	
    /* (non-Javadoc)
     * @see uk.ac.reload.moonunit.ProfiledSchemaController#getProfileFolder()
     */
    public File getProfileFolder() {
        return MD_EditorHandler.PROFILE_FOLDER;
    }

    /* (non-Javadoc)
     * @see uk.ac.reload.moonunit.ProfiledSchemaController#getSchemaHelperFolder()
     */
    public File getSchemaHelperFolder() {
        return MD_EditorHandler.SCHEMAHELPER_FOLDER;
    }

    /* (non-Javadoc)
     * @see uk.ac.reload.moonunit.ProfiledSchemaController#getVocabFolder()
     */
    public File getVocabFolder() {
        return MD_EditorHandler.VOCAB_FOLDER;
    }
    
    /* (non-Javadoc)
     * @see uk.ac.reload.moonunit.ProfiledSchemaController#getDefaultProfileName()
     */
    public String getDefaultProfileName() {
   		// Get from User Prefs
   		String defProfileName = EditorPrefs.getInstance().getValue(EditorPrefs.MD_DEFAULT_PROFILE);
        return defProfileName == null ? defaultMDProfile : defProfileName;
    }
}