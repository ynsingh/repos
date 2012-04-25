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

import java.util.Locale;

/**
 * A Vocabulary List - A String list and default value
 *
 * @author Phillip Beauvoir
 * @version $Id: VocabularyList.java,v 1.1 1998/03/25 20:29:30 ynsingh Exp $
 */
public class VocabularyList implements Comparable {
    // Special list names - these can be used in the Vocabulary XML files
	public static String LANG_LISTNAME = "lang";
	public static String YESNO_LISTNAME = "yesno";
	public static String TRUEFALSE_LISTNAME = "truefalse";
	public static String NONE_LISTNAME = "none";
	
	/**
	 * Re-usable ISO Language list
	 */
	public static String[] ISO_LANGS = Locale.getISOLanguages();

	/**
	 * Re-usable yes/no list
	 */
	public static VocabularyList getYesNoList() {
		return new VocabularyList(YESNO_LISTNAME, new String[] { "", "yes", "no" });
	}
	
	/**
	 * Re-usable true/false list
	 */
	public static VocabularyList getTrueFalseList() {
		return new VocabularyList(TRUEFALSE_LISTNAME, new String[] { "", "true", "false" });
	}

	/**
	 * Re-usable ISO Language list
	 */
	public static VocabularyList getLangList() {
		return new VocabularyList(LANG_LISTNAME, ISO_LANGS);
	}

    /**
     * The Actual String list
     */
	private String[] _list;
    
    /**
     * The name of the list
     */
    private String _listName;

    /**
     * The default value which may be null
     */
    private String _defaultValue;

    /**
     * Default Constructor with empty list
     */
    public VocabularyList() {
    	_listName = "blank";
    	_list = new String[0];
    }

    /**
     * Constructor with name, list and default value
     * @param listName
     * @param list
     * @param defaultValue
     */
    public VocabularyList(String listName, String[] list, String defaultValue) {
    	_listName = listName;
    	_list = list;
    	_defaultValue = defaultValue;
    }

    /**
     * Constructor with name and list but no default value
     * @param listName
     * @param list
     */
    public VocabularyList(String listName, String[] list) {
        this(listName, list, null);
    }

    /**
     * @return the Vocab list.  It should at least be an array of one empty String
     */
    public String[] getList() {
        return _list;
    }

    /**
     * @return the Name of the List
     */
    public String getListName() {
        return _listName;
    }

    /**
     * @return true if this is a language list
     */
    public boolean isLangList() {
        return _listName.equals(LANG_LISTNAME);
    }

    /**
     * Set the default value for this vocab list
     * @param defaultValue
     */
    public void setDefaultValue(String defaultValue) {
        _defaultValue = defaultValue;
    }

    /**
     * @return The default value for this vocab list which may be null
     */
    public String getDefaultValue() {
        return _defaultValue;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return _listName;
    }

    /* (non-Javadoc)
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    public int compareTo(Object o) {
        if(o instanceof VocabularyList && getListName() != null) {
            VocabularyList vlist = (VocabularyList)o;
            return getListName().compareTo(vlist.getListName());
        }
        return 0;
    }
}