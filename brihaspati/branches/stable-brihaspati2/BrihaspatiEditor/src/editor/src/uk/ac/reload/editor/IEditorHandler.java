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

package uk.ac.reload.editor;

import java.io.File;

import javax.swing.Icon;

import uk.ac.reload.moonunit.SchemaController;


/**
 * The Interface for Reload Editors
 *
 * @author Phillip Beauvoir
 * @version $Id: IEditorHandler.java,v 1.1 1998/03/26 15:24:11 ynsingh Exp $
 */
public interface IEditorHandler {
	
    /**
     * Can you create New Documents?
     * @return True if you can
     */
    boolean canCreateDocuments();
    
    /**
     * Can you handle the Editing of this file?
     * @param file
     * @return True if you can
     */
    boolean canEditFile(File file);

	/**
	 * Edit a File, assuming you can handle it
	 * @param file
	 * @return An Editor Frame with Edited Document
	 * @throws Exception
	 */
	EditorInternalFrame editFile(File file) throws Exception;
	
	/**
	 * @return An Editor Frame with New Document
	 * @throws Exception
	 */
	EditorInternalFrame newDocument() throws Exception;
	
	/**
	 * @return The name to display in a Menu
	 */
	String getName();

	/**
	 * @return The Icon to display in a Menu
	 */
	Icon getIcon();
	
	/**
	 * @return All supported Version names
	 */
	String[] getSupportedVersions();
	
	/**
	 * @return A Schema Controller Instance for a version
	 */
	SchemaController getSchemaControllerInstance(String version) throws Exception;
}
