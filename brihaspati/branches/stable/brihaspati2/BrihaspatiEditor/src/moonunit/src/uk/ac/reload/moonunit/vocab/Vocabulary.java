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

package uk.ac.reload.moonunit.vocab;

import java.io.File;
import java.io.IOException;
import java.util.*;

import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;

import uk.ac.reload.jdom.XMLPath;
import uk.ac.reload.jdom.XMLUtils;
import uk.ac.reload.moonunit.schema.SchemaNode;


/**
 * The Vocabulary - Defines a Vocabulary mapping between Elements/Attributes
 * and a Vocabulary List.  The actual Vocabulary is an XML File that defines the mappings.
 *
 * @author Phillip Beauvoir
 * @author Julian Wood
 * @version $Id: Vocabulary.java,v 1.1 1998/03/25 20:29:30 ynsingh Exp $
 */
public class Vocabulary {
	
    /**
     * Static Vocabularies - we only want to create these just once and re-use them
     */
    private static Hashtable _vocabTable = new Hashtable();

    /**
     * The JDOM Document that is created from the XML File
     */
    private Document _docVocab;

    /**
     * The XML Vocabulary File.
     */
    private File _fileVocab;

    /**
     * Map of Element Paths
     */
    private HashMap _pathListMap = new HashMap();

    /**
     * Map of Vocabulary Lists
     */
    private TreeMap _vocabLists = new TreeMap();

    /**
     * The default ISO language code ("en", "fr") for this Vocabulary
     */
    private String _defaultLanguage;

    /**
     * Factory method for getting a static re-usable Vocabulary
     * @param fileVocab The file of the Vocabulary file to get
     * @return The Vocabulary or null if not found
     * @throws JDOMException
     * @throws IOException
     */
    public static Vocabulary getVocabulary(File fileVocab) throws JDOMException, IOException {
        if(fileVocab == null) return null;
        // Do we have it already?
        Vocabulary vocab = (Vocabulary)_vocabTable.get(fileVocab);
        // No, first time creation, create and store in table
        if(vocab == null) {
            vocab = new Vocabulary(fileVocab);
            _vocabTable.put(fileVocab, vocab);
        }
        return vocab;
    }

    /**
     * Constructor
     *
     * @param fileVocab The Vocabulary XML File
     * @throws JDOMException
     * @throws IOException
     */
    public Vocabulary(File fileVocab) throws JDOMException, IOException {
        _fileVocab = fileVocab;

        // Load the vocab document
        _docVocab = loadVocabularyFile(fileVocab);

        // Map the lists
        loadLists();

        // Set the default language of the language list as specified in the XML file
        setDefaultLanguage(getRootAttributeValue("lang"));
    }

    /**
     * Load the Lists into maps
     */
    protected void loadLists() {
        // Add the special lists
        _vocabLists.put(VocabularyList.LANG_LISTNAME, VocabularyList.getLangList());
        _vocabLists.put(VocabularyList.TRUEFALSE_LISTNAME, VocabularyList.getTrueFalseList());
        _vocabLists.put(VocabularyList.YESNO_LISTNAME, VocabularyList.getYesNoList());

        // Collect the vocabulary lists from the xml file
        Iterator vocablist = _docVocab.getRootElement().getChildren("vocabList").iterator();
        while(vocablist.hasNext()) {
            Element vocabElement = (Element) vocablist.next();

            // Get the name of the vocab list
            String vocabName = vocabElement.getAttributeValue("name");

            // Oops!
            if(vocabName == null) {
                System.err.println("Vocab warning: vocabList has no name attribute.");
                break;
            }

            // Get the default value.  This may be null.  That's OK.
            String defaultValue = vocabElement.getAttributeValue("default");
            
            // Get each "item" in the list
            List items = vocabElement.getChildren("item");
            
            // Create an appropriate String array
            String[] s = new String[items.size()];
            for(int i = 0; i < items.size(); i++) {
                Element e = (Element) items.get(i);
                s[i] = e.getText();
            }
            
            _vocabLists.put(vocabName, new VocabularyList(vocabName, s, defaultValue));
        }

        // Now map all the Dom Paths and their corresponding list names
        
        // Get all "element" children
        Iterator elementlist = _docVocab.getRootElement().getChildren("element").iterator();

        // Enumerate through them
        while(elementlist.hasNext()) {
            // Get the Element
            Element childElement = (Element) elementlist.next();

            // Get the Path and List name
            String listPath = childElement.getAttributeValue("path");
            String listName = childElement.getAttributeValue("list");

            if(listPath != null && listName != null) {
                _pathListMap.put(listPath, listName);
            }
            else {
                System.err.println("Warning: Vocabulary - bad <element> tag");
            }
        }
    }

    /**
     * Load the XML Vocabulary file into a JDOM Document.
     * @param fileVocab
     * @return the JDOM Document
     * @throws JDOMException
     * @throws IOException
     */
    protected Document loadVocabularyFile(File fileVocab) throws JDOMException, IOException {
        return XMLUtils.readXMLFile(fileVocab);
    }

    /**
     * @return The Vocabulary JDOM Document
     */
    public Document getVocabularyDocument() {
        return _docVocab;
    }

    /**
     * @return The name of the vocabulary. This is the short file name
     */
    public String getVocabularyName() {
        return _fileVocab == null ? "(unknown)" : _fileVocab.getName();
    }

    /**
     * Get a Vocabulary List for a Given Element or Attribute.
     * @param schemaNode The Element or Attribute
     * @return The Vocabulary List or null if there isn't one
     */
    public VocabularyList getVocabularyList(SchemaNode schemaNode) {
        if(schemaNode == null) return null;

        // Get the path of this node
        XMLPath nodePath = schemaNode.getXMLPath();
        
        return getVocabularyList(nodePath);
    }

    /**
     * @param xmlPath
     * @return a Vocabulary List for a given element's XMLPath
     */
    public VocabularyList getVocabularyList(XMLPath xmlPath) {
        VocabularyList vocabularyList = null;

        String path = xmlPath.getPath();

        // check in cache for full path
        String listName = (String) _pathListMap.get(path);

        // look for partial matches for partial path
        if(listName == null) {
            Iterator it = _pathListMap.keySet().iterator();
            while(it.hasNext()) {
            	String itPath = (String)it.next();
                if(path.endsWith(itPath)) {
                    listName = (String)_pathListMap.get(itPath);
                    break;
                }
            }
        }

        // An over-riding "none" list returns as null otherwise get the list
        if(listName != null && listName != VocabularyList.NONE_LISTNAME) {
            vocabularyList = (VocabularyList) _vocabLists.get(listName);
        }

        return vocabularyList;
    }
    
    /**
     * Get a VocabularyList by its name
     * @param listName The name of the list
     * @return VocabularyList or null
     */
    public VocabularyList getVocabularyList(String listName) {
        return (VocabularyList)_vocabLists.get(listName);
    }

    /**
     * @return the actual xml file from which this vocab is derived
     */
    public File getVocabFile() {
        return _fileVocab;
    }

    /**
     * @return a list of unique vocabs
     */
    public Collection getVocabularyLists() {
        return _vocabLists.values();
    }
    
    /**
     * @return The default ISO language of the Language list in this Vocabulary
     * of format "en", "fr" etc
     */
    public String getDefaultLanguage() {
        return _defaultLanguage;
    }

    /**
     * Set the default language of the Language list in this Vocabulary
     * @param language ISO language of format "en", "fr" etc
     */
    public void setDefaultLanguage(String language) {
        // If null, set to default locale language
        if(language == null) language = Locale.getDefault().getLanguage();

        // Get our single language list
        VocabularyList langList = (VocabularyList) _vocabLists.get(VocabularyList.LANG_LISTNAME);
        if(langList != null) langList.setDefaultValue(language);

        _defaultLanguage = language;
    }

    /**
     * @param attName The Attribute Name
     * @return the value of a root attribute in the JDOM Document or null if not found
     */
    protected String getRootAttributeValue(String attName) {
        if(_docVocab != null && _docVocab.hasRootElement()) {
            Element root = _docVocab.getRootElement();
            Attribute att = root.getAttribute(attName);
            return att == null ? null : att.getValue();
        }
        return null;
    }

    /**
     * @return the name of this vocab
     */
    public String toString() {
        return getVocabularyName();
    }
}