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

import javax.swing.Icon;

import org.jdom.JDOMException;
import org.jdom.Namespace;

import uk.ac.reload.diva.util.FileUtils;
import uk.ac.reload.dweezil.util.DweezilUIManager;
import uk.ac.reload.editor.EditorHandler;
import uk.ac.reload.editor.IIcons;
import uk.ac.reload.editor.contentpackaging.CP_EditorHandler;
import uk.ac.reload.editor.gui.TreeIconInterface;
import uk.ac.reload.editor.metadata.MD_EditorHandler;
import uk.ac.reload.editor.metadata.xml.MD_SchemaController;
import uk.ac.reload.editor.prefs.EditorPrefs;
import uk.ac.reload.moonunit.ProfiledSchemaController;
import uk.ac.reload.moonunit.contentpackaging.CP_Core;
import uk.ac.reload.moonunit.schema.SchemaException;

/**
 * The CP Schema Controller
 *
 * @author Phillip Beauvoir
 * @version $Id: CP_SchemaController.java,v 1.1 1998/03/26 15:32:14 ynsingh Exp $
 */
public abstract class CP_SchemaController
extends ProfiledSchemaController
implements TreeIconInterface, IIcons
{
	/**
	 * Default Profile
	 */
	public static String defaultCPProfile = "CP Default Profile";

	/**
	 * The MetadataSchemaController for MD elements
	 */
	private MD_SchemaController _mdController;

	/**
	 * Default Constructor
	 * @throws JDOMException
	 * @throws IOException
	 * @throws SchemaException
	 */
	public CP_SchemaController() throws JDOMException, SchemaException, IOException {
	    super();
	}
	
	/**
	 * Set the MD_SchemaController
	 * @param mdController
	 */
	public void setMD_SchemaController(MD_SchemaController mdController) {
        _mdController = mdController;
	}
	
	/**
	 * @return the Root element name
	 */
	public String getRootElementName() {
		return "manifest";
	}
	
	/**
	 * Get The Leaf Icon
	 */
	public Icon getLeafIcon(String name, Namespace ns) {
		// If the element's Namespace is not equal to the root Namespace then
		// we are dealing with an unknown Namespace
		if(!ns.getURI().equals(getSchemaModel().getTargetNamespaceURI())) {
			return DweezilUIManager.getIcon(ICON_NODE);
		}
		
		// Set some icons - HARD CODED FOR NOW
		if(name.equals(CP_Core.MANIFEST)) {
			return DweezilUIManager.getIcon(ICON_CP);
		}
		else if(name.equals(CP_Core.ORGANIZATIONS)) {
			return DweezilUIManager.getIcon(ICON_ORGS);
		}
		else if(name.equals(CP_Core.ORGANIZATION)) {
			return DweezilUIManager.getIcon(ICON_ORG);
		}
		else if(name.equals(CP_Core.ITEM)) {
			return DweezilUIManager.getIcon(ICON_ITEM);
		}
		else if(name.equals(CP_Core.FILE)) {
			return DweezilUIManager.getIcon(ICON_FILE);
		}
		else if(name.equals(CP_Core.METADATA)) {
			return DweezilUIManager.getIcon(ICON_MD);
		}
		else if(name.equals(CP_Core.RESOURCES)) {
			return DweezilUIManager.getIcon(ICON_RESOURCES);
		}
		else if(name.equals(CP_Core.RESOURCE)) {
			return DweezilUIManager.getIcon(ICON_RESOURCE);
		}
		//else if(name.equals(CP_Core.TITLE)) {
		//    return DweezilUIManager.getIcon(ICON_TITLE);
		//}
		else if(name.equals(CP_Core.DEPENDENCY)) {
			return DweezilUIManager.getIcon(ICON_DEPENDENCY);
		}
		else {
			return DweezilUIManager.getIcon(ICON_MDLEAF);
		}
	}
	
	/**
	 * Get The Open Icon
	 */
	public Icon getOpenIcon(String name, Namespace ns) {
		return getLeafIcon(name, ns);
	}
	
	/**
	 * Get The Closed Icon
	 */
	public Icon getClosedIcon(String name, Namespace ns) {
		return getLeafIcon(name, ns);
	}
	
	/**
	 * Copy the Schema Files to the Project Folder
	 */
	public void copySchemaFilesToFolder(File projectFolder) throws IOException {
	    String versionMD = getMetadataVersion();
	    if(versionMD != null) {
		    // MD 121
		    if(MD_EditorHandler.IMS_METADATA_1_2_1.equals(versionMD)) {
				FileUtils.copyFolder(new File(EditorHandler.SCHEMASHIPPEDFOLDER, "imsmd_121"), projectFolder);
			}
			// MD 122
		    else if(MD_EditorHandler.IMS_METADATA_1_2_2.equals(versionMD)) {
				FileUtils.copyFolder(new File(EditorHandler.SCHEMASHIPPEDFOLDER, "imsmd_122"), projectFolder);
			}
			// MD 11
		    else if(MD_EditorHandler.IMS_METADATA_1_1.equals(versionMD)) {
				FileUtils.copyFolder(new File(EditorHandler.SCHEMAMODELFOLDER, "imsmd_11"), projectFolder);
			}
	    }
	}

	/**
	 * @return The MD Version used
	 */
	public String getMetadataVersion() {
		return _mdController == null ? null : _mdController.getVersion();
	}

    /**
     * @return Returns the mdController
     */
    public MD_SchemaController getMD_SchemaController() {
        return _mdController;
    }
    
    /**
     * @return The default MD_SchemaController for this type of CP
     * @throws SchemaException
     */
    public abstract MD_SchemaController getDefaultMD_SchemaController() throws JDOMException, IOException, SchemaException;
    
    /* (non-Javadoc)
     * @see uk.ac.reload.moonunit.SchemaController#getProfileFolder()
     */
    public File getProfileFolder() {
        return CP_EditorHandler.PROFILE_FOLDER;
    }

    /* (non-Javadoc)
     * @see uk.ac.reload.moonunit.SchemaController#getSchemaHelperFolder()
     */
    public File getSchemaHelperFolder() {
        return CP_EditorHandler.SCHEMAHELPER_FOLDER;
    }

    /* (non-Javadoc)
     * @see uk.ac.reload.moonunit.SchemaController#getVocabFolder()
     */
    public File getVocabFolder() {
        return CP_EditorHandler.VOCAB_FOLDER;
    }

    /* (non-Javadoc)
     * @see uk.ac.reload.moonunit.SchemaController#getDefaultProfilename()
     */
    public String getDefaultProfileName() {
   		// Get from User Prefs
   		String defProfileName = EditorPrefs.getInstance().getValue(EditorPrefs.CP_DEFAULT_PROFILE);
        return defProfileName == null ? defaultCPProfile : defProfileName;
    }
    
}