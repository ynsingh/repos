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

package uk.ac.reload.editor.contentpackaging.datamodel;

import java.io.File;
import java.util.Vector;

import javax.swing.Icon;
import javax.swing.filechooser.FileSystemView;

import uk.ac.reload.dweezil.util.DweezilUIManager;
import uk.ac.reload.editor.IIcons;

/**
 * A CP_Resource is a File in the Content Package - somewhere under or in the root folder
 *
 * @author Phillip Beauvoir
 * @version $Id: CP_Resource.java,v 1.1 1998/03/26 15:32:14 ynsingh Exp $
 */
public class CP_Resource
extends File
implements IIcons
{
	
	/**
	 * Constructor
	 * @param file The wrapped file
	 */
	public CP_Resource(File file) {
		super(file.toString());
	}
	
	/**
	 * @return a list of child CP_Resource objects.<p>
	 * Returns null if this is not a folder and no children.<p>
	 * Puts folders first.
	 */
	public CP_Resource[] getChildren() {
		if(!isDirectory()) return null;
		
		// Do this in two passes so we can do the folders first
		
		File[] files = listFiles();
		if(files == null) return null;
		
		Vector v = new Vector();
		
		// Folders
		for(int i = 0; i < files.length; i++) {
			File file = files[i];
			if(file.isDirectory()) {
				v.add(new CP_Resource(files[i]));
			}
		}
		
		// Files
		for(int i = 0; i < files.length; i++) {
			File file = files[i];
			if(!file.isDirectory()){
				v.add(new CP_Resource(files[i]));
			}
		}
		
		CP_Resource[] resources = new CP_Resource[v.size()];
		v.copyInto(resources);
		return resources;
	}
	
	/**
	 * @return The friendly name for a CP_Resource.
	 */
	public String getFriendlyName(){
		String s = null;
		// Need to do these checks because of system null pointers
		if(exists() && !isHidden()) {
		    FileSystemView view = FileSystemView.getFileSystemView();
		    try {
		        s = view.getSystemTypeDescription(this);
		    }
		    catch(Exception ex) {
		    }
		}
		return s == null ? "System file" : s;
	}
	
	/**
	 * @return The system icon for this file
	 */
	public Icon getResourceIcon(){
		Icon icon = null;
		// Need to do these checks because of system null pointers
		if(exists() && !isHidden()) {
		    FileSystemView view = FileSystemView.getFileSystemView();
		    try {
		        icon = view.getSystemIcon(this);
		    }
		    catch(Exception ex) {
		    }
		}
		return icon == null ? DweezilUIManager.getIcon(ICON_FILE) : icon;
	}
	
	/**
	 * @return The File Name
	 */
	public String toString(){
		return getName();
	}
	
}
