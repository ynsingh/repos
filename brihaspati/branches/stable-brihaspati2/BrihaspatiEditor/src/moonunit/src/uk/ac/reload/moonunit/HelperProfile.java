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


package uk.ac.reload.moonunit;

import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Vector;

import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;

import uk.ac.reload.diva.util.FileUtils;
import uk.ac.reload.jdom.XMLUtils;


/**
 * The Profile for a given editing View - this contains a ref to the schemahelper and the
 * vocab file to use for a given type of IMS document
 *
 * @author Phillip Beauvoir
 * @version $Id: HelperProfile.java,v 1.1 1998/03/25 20:28:22 ynsingh Exp $
 */
public class HelperProfile {
	
    /**
     * The JDOM Document of the XML Helper File
     */
    private Document _doc;

    /**
     * The File of the Profile File
     */
    private File _file;

	/**
	 * Root Element name 
	 */
    public static final String ELEMENT_ROOT_NAME = "profile"; //$NON-NLS-1$

	/**
     * Attribute names used in the root element of a profile Document
     */
    public static final String ATT_VOCAB = "vocabfile"; //$NON-NLS-1$
    public static final String ATT_SCHEMA_HELPER = "schemahelperfile"; //$NON-NLS-1$
    public static final String ATT_SCHEMA_VERSION = "schemaversion"; //$NON-NLS-1$
    
    /**
     * The folder location of the Vocabularies
     */
    private File _vocabFolder;
    
    /**
     * The folder location of the SchemaHelper
     */
    private File _schemaHelperFolder;
    
    
    /**
     * Static SchemaHelpers - we only want to create these just once for each spec and re-use them
     */
    private static Hashtable _helperProfileTable = new Hashtable();

    /**
     * Factory method for getting a static re-usable HelperProfile
     * @param fileProfile The Profile File
     * @param vocabFolder The Vocabulary Folder
     * @param schemaHelperFolder The Schema Helper Folder
     * @return The HelperProfile or null if not found
     * @throws JDOMException
     * @throws IOException
     */
    public static HelperProfile getHelperProfile(File fileProfile, File vocabFolder, File schemaHelperFolder) throws JDOMException, IOException {
        // Do we have it already?
        HelperProfile profile = (HelperProfile) _helperProfileTable.get(fileProfile);
        // No, first time creation, create and store in table
        if(profile == null) {
            profile = new HelperProfile(fileProfile, vocabFolder, schemaHelperFolder);
            _helperProfileTable.put(fileProfile, profile);
        }
        return profile;
    }

    /**
     * Default Constructor with new JDOM Document
     */
    public HelperProfile() {
        _doc = new Document();
		_doc.setRootElement(new Element(ELEMENT_ROOT_NAME));
    }

    /**
     * Constructor
     * @param fileProfile
     * @throws JDOMException
     * @throws IOException
     */
    public HelperProfile(File fileProfile, File vocabFolder, File schemaHelperFolder) throws JDOMException, IOException {
        _file = fileProfile;
        _vocabFolder = vocabFolder;
        _schemaHelperFolder = schemaHelperFolder;
        load();
    }

	/**
	 * @return All HelperProfile (file) names in a given profile folder
	 */
	public static String[] getHelperProfileNames(File profileFolder) {
		// Set up filter to only display xml files
		FileFilter filter = new FileFilter() {
			public boolean accept(File file) {
				if(file.isDirectory()) return false;
				if(FileUtils.getFileExtension(file).equals("xml")) { //$NON-NLS-1$
				    return true; 
				}
				return false;
			}
		};
		
		Vector v = new Vector();
		
		// Get the Files
		File[] files = profileFolder.listFiles(filter);
		if(files != null) {
			for(int i = 0; i < files.length; i++) {
			    v.addElement(FileUtils.getFileNameWithoutExtension(files[i]));
			}
		}
		
		String[] profiles = new String[v.size()];
		v.copyInto(profiles);
		
		// Sort them
		Arrays.sort(profiles);
		
		return profiles;
	}

	/**
     * Load the XML Profile File into a JDOM Document
     *
     * @throws FileNotFoundException
     * @throws JDOMException
     * @throws IOException
     */
    public void load() throws FileNotFoundException, JDOMException, IOException {
    	_doc = XMLUtils.readXMLFile(getFile());
    }

    /**
     * Saves the XML file
     * @throws IOException
     */
    public void save() throws IOException {
        if(_file != null) {
            XMLUtils.write2XMLFile(_doc, getFile());
        }
    }

    /**
     * Sets the backing XML file
     * @param file
     */
    protected void setFile(File file) {
        _file = file;
    }

    /**
     * @return the actual File
     */
    public File getFile() {
        return _file;
    }

    /**
     * @return the JDOM Document
     */
    public Document getDocument() {
        return _doc;
    }

    /**
     * Get name of the Profile.  This is the File short name without extension
     * @return The name of the Profile
     */
    public String getProfileName() {
        return _file == null ? Messages.getString("uk.ac.reload.moonunit.HelperProfile.1") : FileUtils.getFileNameWithoutExtension(_file); //$NON-NLS-1$
    }
    
    /**
     * Get the file name of the associated Vocabulary file.  This is stored
     * in the XML Profile file against the attribute "vocabfile"
     * @return The name of the Vocabulary file
     */
    public String getVocabularyFileName() {
        return getRootAttributeValue(ATT_VOCAB);
    }

    /**
     * Set the Vocabulary File name
     * @param vocabularyFilename
     */
    public void setVocabularyFileName(String vocabularyFilename) {
        _doc.getRootElement().setAttribute(ATT_VOCAB, vocabularyFilename);
    }

    /**
     * Get the file name of the associated Schema Helper file.  This is stored
     * in the XML Profile file against the attribute "schemahelperfile"
     * @return The name of the Vocabulary file
     */
    public String getSchemaHelperFileName() {
        return getRootAttributeValue(ATT_SCHEMA_HELPER);
    }

    /**
     * Set the Schema Helper File name
     * @param schemaHelperFileName
     */
    public void setSchemaHelperFileName(String schemaHelperFileName) {
        _doc.getRootElement().setAttribute(ATT_SCHEMA_HELPER, schemaHelperFileName);
    }

    /**
     * get the version of this schema
     * this will correspond to one of the schemaversions retrievable through DocumentHandler.getVersion(Document doc)
     * @return
     */
    public String getSchemaVersion() {
        return getRootAttributeValue(ATT_SCHEMA_VERSION);
    }

    /**
     * Set the Schema Version
     * @param schemaVersion
     */
    public void setSchemaVersion(String schemaVersion) {
        _doc.getRootElement().setAttribute(ATT_SCHEMA_VERSION, schemaVersion);
    }

	/**
	 * @return the Vocabulary File referenced in the Profile
	 */
	public File getVocabFile() {
		return new File(getVocabFolder(), getVocabularyFileName());
	}
	
	/**
	 * @return the Schema Helper File referenced in the Profile
	 */
	public File getSchemaHelperFile() {
		return new File(getSchemaHelperFolder(), getSchemaHelperFileName());
	}

	/**
     * @return the Vocabulary Folder
     */
    public File getVocabFolder() {
        return _vocabFolder;
    }

    /**
     * @return the Schema Helper Folder
     */
    public File getSchemaHelperFolder() {
        return _schemaHelperFolder;
    }

    /**
     * @return the value of a root attribute or null if not found
     */
    protected String getRootAttributeValue(String attName) {
        if(_doc != null && _doc.hasRootElement()) {
            Element root = _doc.getRootElement();
            Attribute att = root.getAttribute(attName);
            return att == null ? null : att.getValue();
        }
        return null;
    }

    /**
     * @return String representation
     */
    public String toString() {
        return getProfileName();
    }
}
