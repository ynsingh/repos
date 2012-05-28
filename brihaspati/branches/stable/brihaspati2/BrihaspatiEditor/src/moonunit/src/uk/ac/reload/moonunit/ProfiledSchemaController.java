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
import java.io.FileNotFoundException;
import java.io.IOException;

import org.jdom.JDOMException;

import uk.ac.reload.moonunit.schema.SchemaException;
import uk.ac.reload.moonunit.vocab.Vocabulary;


/**
 * The Profiled SchemaController - Does all that SchemaController does but gets the location
 * of the Vocabulary and SchemaHelper files from a HelperProfile (xml file)
 *
 * @author Phillip Beauvoir
 * @version $Id: ProfiledSchemaController.java,v 1.1 1998/03/25 20:28:22 ynsingh Exp $
 */
public abstract class ProfiledSchemaController
extends SchemaController
{

    /**
     * The current HelperProfile
     */
    protected HelperProfile _helperProfile;
    
    /**
     * Default Constructor.
     * This will automatically load the SchemaModel returned from getSchemaFile() and getRootElementName().
     * The default HelperProfile will also be loaded by calling loadHelperProfile(getDefaultProfileName())
     * @throws IOException
     * @throws SchemaException
     */
    protected ProfiledSchemaController() throws JDOMException, SchemaException, IOException { 
        loadSchemaModel();
        loadHelperProfile(getDefaultProfileName());
    }

    /**
     * Load the Helper Profile give its Profile Name.
     * After The HelperProfile is loaded it is parsed for the names of the Vocabulary and SchemaHelper.
     * These in turn will be loaded from the entries found in the HelperProfile.
     * 
     * @param profileName
     * @throws JDOMException
     * @throws IOException
     */
    public void loadHelperProfile(String profileName) throws JDOMException, IOException {
		File fileProfile = new File(getProfileFolder(), profileName + ".xml");
		if(!fileProfile.exists()) {
		    throw new FileNotFoundException("Could not load Profile: " + profileName);
		}
		
		_helperProfile = HelperProfile.getHelperProfile(fileProfile, getVocabFolder(), getSchemaHelperFolder());
        setVocabulary(Vocabulary.getVocabulary(_helperProfile.getVocabFile()));
        setSchemaHelper(SchemaHelper.getSchemaHelper(_helperProfile.getSchemaHelperFile()));
    }
    
    /**
     * @return The current Helper Profile
     */
    public HelperProfile getHelperProfile() {
        return _helperProfile;
    }

	/**
	 * @return all available Profile Names
	 */
	public String[] getHelperProfileNames() {
		return HelperProfile.getHelperProfileNames(getProfileFolder());
	}

    // =========================================================================
    // ============================ ABSTRACTIONS ===============================
    // =========================================================================

	/**
	 * @return The Profiles Folder
	 */
	public abstract File getProfileFolder();
	
	/**
	 * @return The Default Profile Name
	 */
	public abstract String getDefaultProfileName();
	
	/**
	 * @return The Schema Helper Folder
	 */
	public abstract File getSchemaHelperFolder();
	
	/**
	 * @return The Vocab Folder
	 */
	public abstract File getVocabFolder();
	
}