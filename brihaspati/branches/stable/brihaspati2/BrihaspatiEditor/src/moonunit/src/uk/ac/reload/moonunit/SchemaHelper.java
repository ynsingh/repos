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
import java.io.IOException;
import java.util.Hashtable;
import java.util.Iterator;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;

import uk.ac.reload.jdom.XMLPath;
import uk.ac.reload.jdom.XMLUtils;

/**
 * The Schema Helper - this provides a bunch of helper methods to the
 * SchemaModel class, like friendly names etc.  It's a middleman class.  It reads
 * in the Helper XML File.
 *
 * @author Phillip Beauvoir
 * @version $Id: SchemaHelper.java,v 1.1 1998/03/25 20:28:22 ynsingh Exp $
 */
public class SchemaHelper {

    public static final String ELEMENT = "element"; //$NON-NLS-1$
    public static final String PATH = "path"; //$NON-NLS-1$
    public static final String FNAME = "fname"; //$NON-NLS-1$
    public static final String TIP = "tip"; //$NON-NLS-1$
    public static final String WIDGET = "widget"; //$NON-NLS-1$
    public static final String MAXLENGTH = "maxLength"; //$NON-NLS-1$

    /**
     * The JDOM Document of the XML Helper File
     */
    private Document _docHelper;

    /**
     * The File of the XML Helper File
     */
    private File _fileHelper;


    /**
     * Static SchemaHelpers - we only want to create these just once for each spec and re-use them
     */
    private static Hashtable _schemaHelperTable = new Hashtable();

    /**
     * Factory method for getting a static re-usable SchemaHelper
     * @param fileHelper The file of the Helper file to get
     * @return The SchemaHelper or null if not found
     * @throws JDOMException
     * @throws IOException
     */
    public static SchemaHelper getSchemaHelper(File fileHelper) throws JDOMException, IOException {
        // Do we have it already?
        SchemaHelper schemaHelper = (SchemaHelper) _schemaHelperTable.get(fileHelper);
        // No, first time creation, create and store in table
        if(schemaHelper == null) {
            schemaHelper = new SchemaHelper(fileHelper);
            _schemaHelperTable.put(fileHelper, schemaHelper);
        }
        return schemaHelper;
    }

    /**
     * Constructor.
     * @param fileHelper The XML Helper File to read in
     * @throws JDOMException
     * @throws IOException
     */
    public SchemaHelper(File fileHelper) throws JDOMException, IOException {
        _fileHelper = fileHelper;
        // Get the helper JDOM Document
        _docHelper = loadSchemaHelperFile(fileHelper);
    }

    /**
     * Load the Schema Helper File into a JDOM Document
     * @param helperFile The XML Helper File to read in
     * @return The JDOM Document
     * @throws JDOMException
     * @throws IOException
     */
    protected Document loadSchemaHelperFile(File helperFile) throws JDOMException, IOException {
        return XMLUtils.readXMLFile(helperFile);
    }

    /**
     * Get the JDOM Helper Document.
     * @return The JDOM Helper Document
     */
    protected Document getSchemaHelperDocument() {
        return _docHelper;
    }

    /**
     * @return The name of the SchemaHelper. This is the fileName
     */
    public String getSchemaHelperName() {
        return _fileHelper == null ? Messages.getString("uk.ac.reload.moonunit.SchemaHelper.1") : _fileHelper.getName(); //$NON-NLS-1$
    }

    /**
     * @param xmlPath The XMLPath binding
     * @param helperKey The Helper key to search for
     * @return a helper value for an Element's XMLPath
     */
    public String getHelperValue(XMLPath xmlPath, String helperKey) {
         if(_docHelper != null && _docHelper.hasRootElement()) {
            String path = xmlPath.getPath();
            Element root = _docHelper.getRootElement();
            Iterator i = root.getChildren(ELEMENT).iterator();
            while(i.hasNext()) {
                Element childElement = (Element)i.next();
                String name = childElement.getAttributeValue(PATH);
                if(name != null) {
                    if(path.equals(name) || path.endsWith(name)) {
                        // Try the attribute first
                        String value = childElement.getAttributeValue(helperKey);
                        // Try a child element
                        if(value == null) {
                            Element child = childElement.getChild(helperKey);
                            if(child != null) {
                                return child.getText();
                            }
                        }
                        else {
                            return value;
                        }
                    }
                }
            }
        }

        return null;
    }

    /**
     * @return the file
     */
    public File getFile() {
        return _fileHelper;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return getSchemaHelperName();
    }
}