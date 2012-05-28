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

package uk.ac.reload.editor.contentpackaging.editor.resourceview;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.swing.JOptionPane;

import uk.ac.reload.diva.util.FileUtils;
import uk.ac.reload.dweezil.gui.DweezilProgressMonitor;
import uk.ac.reload.dweezil.gui.YesAllNoDialog;
import uk.ac.reload.editor.EditorFrame;
import uk.ac.reload.editor.Messages;
import uk.ac.reload.editor.contentpackaging.htmlparser.FileType;
import uk.ac.reload.editor.contentpackaging.htmlparser.HTMLParserImporter;

/**
 * CP_ResourceImporter. Used to decide what type of resource
 * the user is trying to import into the resources pool.
 * Then either call the HTMLParser to find dependencies or
 * simply copy over the files (or folder contents) into the project folder (resource pool).
 *
 * @author Paul Sharples
 * @author Phillip Beauvoir
 * @version $Id: CP_ResourceImporter.java,v 1.1 1998/03/26 15:40:31 ynsingh Exp $
 */
public class CP_ResourceImporter
{
	
	/**
	 * Flag of when user clicks "Yes to All"
	 */
    private boolean _yesToAll;
	
	/**
	 * Flag of when user clicks cancel.
	 */
    private boolean _cancelOperation;
	
	/**
	 * During the process of importing resources a user can import a file, then
	 * subsequently click cancel when prompted to overwrite a file that is
	 * already in the resource pool.  This flag will tell us if a user
	 * has actually copied somthing into the project folder, so we can
	 * notify the resource tree to update.
	 */
	private boolean _hasImportedFiles;
	
	/**
	 * ProgressMonitor
	 */
	private DweezilProgressMonitor _progressMonitor;
	
	/**
	 * Flag that there were higher or absolute links
	 */
	private boolean _hadBadLinks;

	
	
	/**
	 * Constructor
	 * 
	 * @param aFile - The file we want to import
	 * @param destFolder - The destination folder to copy it to
	 * @param obtainDependencies - Does the user want to pull all dependencies across
	 */
	public CP_ResourceImporter(File aFile, File destFolder, boolean obtainDependencies,
	        			DweezilProgressMonitor progressMonitor) throws IOException {
		
	    _progressMonitor = progressMonitor;
		
		_yesToAll = false;
		
		importFile(aFile, destFolder, obtainDependencies);
		
		if(_progressMonitor != null) {
		    _progressMonitor.close();
		}
		
		if(_hadBadLinks) {
		    showWarningMessage();
		}
	}
	
	/**
	 * Constructor
	 * 
	 * @param files - An array of files we wish to import
	 * @param destFolder - The destination folder to copy it to
	 * @param obtainDependencies - Does the user want to pull all dependencies across
	 */
	public CP_ResourceImporter(File[] files, File destFolder, boolean obtainDependencies,
	        			DweezilProgressMonitor progressMonitor) throws IOException {
		
	    _progressMonitor = progressMonitor;
		
		_yesToAll = false;
		
		for(int i = 0; i < files.length; i++){
			importFile(files[i], destFolder, obtainDependencies);
		}

		if(_progressMonitor != null) {
		    _progressMonitor.close();
		}

		if(_hadBadLinks) {
		    showWarningMessage();
		}
	}
	
	/**
	 * @return True if anything was imported
	 */
	public boolean hasImportedFiles(){
		return _hasImportedFiles;
	}

	/**
	 * Determine file type and import it
	 * 
	 * @param aFile - A file to import
	 * @param destFolder - Folder to copy it to
	 * @param obtainDependencies - Does the user want to pull all dependencies across
	 */
	protected void importFile(File aFile, File destFolder, boolean obtainDependencies) throws IOException {
	    // If it's a folder don't check for parseable files
	    if(aFile.isDirectory()){
			importFolder(aFile, destFolder);
		}
	    
	    // Check the file extension to see if it is one of the files to pass to the HTMLParser
	    else if(obtainDependencies && FileType.isParseableFile(aFile)) {
	        importHTMLFile(aFile, destFolder);
	    }
	    
	    else {
	        importSingleResource(aFile, destFolder);
	    }
	}
	
	/**
	 * Copy a folder's contents into the resource pool
	 * 
	 * @param theFolder - Top level folder the user wishes to copy across
	 * @param destFolder - Where the user wants to copy it to
	 */
	protected void importFolder(File theFolder, File destFolder) throws IOException {
		// Sanity check
		if(!theFolder.isDirectory()) {
		    return;
		}
		
		// Check for project folder and return (we don't want to import the project folder)
		if(theFolder.equals(destFolder)) {
		    return;
		}
		
		File destinationFolder = new File(destFolder, theFolder.getName());
		
		if(!destinationFolder.exists()) {
		    destinationFolder.mkdirs();
		}
		
		copyTheFilesAndFolders(theFolder.listFiles(), destinationFolder);
	}
	
	/**
	 * Find all of the files and folders contained within a folder and copy them over
	 * 
	 * @param allTheFiles
	 * @param destFolder
	 */
	protected void copyTheFilesAndFolders(File[] allTheFiles, File destFolder) throws IOException {
		if(isCancelled()) {
		    return;
		}
		
		// Do this in two passes so we can do the folders first
		for(int i = 0; i < allTheFiles.length; i++) {
			File file = allTheFiles[i];
			if(file.isDirectory()) {
				// copy it across
				File destinationFolder = new File(destFolder, file.getName());
				if(!destinationFolder.exists()) {
				    destinationFolder.mkdir();
				}
				copyTheFilesAndFolders(file.listFiles(), destinationFolder);
			}
		}
		
		for(int i = 0; i < allTheFiles.length; i++) {
			File file = allTheFiles[i];
			if(!file.isDirectory()) {
				importSingleResource(file, destFolder);
			}
		}
	}
	
	/**
	 * Copy over a single resource into the project folder.  It needs
	 * to flag up when the user has selected the cancel operation on a dialogue
	 * window, so it returns a boolean value to tell the calling code.
	 * 
	 * @param originalFile
	 * @param destFolder
	 */
	protected void importSingleResource(File originalFile, File destFolder) throws IOException {
		if(isCancelled()) return;
		
		File destinationFile = new File(destFolder, originalFile.getName());
		
		// If file exists
		if(destinationFile.exists() && (!_yesToAll)) {
		    // Ask user to over-write
		    int answer = askUserOverwriteFile(destinationFile);
		    
		    // Yes to All
		    if(answer == YesAllNoDialog.YES_TO_ALL) {
		        _yesToAll = true;
		    }
		    
		    // Yes
		    if(answer == YesAllNoDialog.YES || answer == YesAllNoDialog.YES_TO_ALL) {
		        FileUtils.copyFile(originalFile, destinationFile);
		        _hasImportedFiles = true;
		    }
		    
		    // Cancel
		    else if(answer == YesAllNoDialog.CANCEL || answer == YesAllNoDialog.CLOSE) {
		        _cancelOperation = true;
		    }
		}
		
		// Else copy
		else {
		    FileUtils.copyFile(originalFile, destinationFile);
		    _hasImportedFiles = true;
		}
	}
	
	
	/**
	 * Import the HTML page by first parsing the page using HTMLParser
	 * which will return a Hashtable of all the original links within each
	 * page and the destination to copy them.
	 * 
	 * @param theFile
	 * @param destFolder
	 */
	protected void importHTMLFile(File theFile, File destFolder) throws IOException  {
		// Pass the start page and target folder to HTMLParser
		HTMLParserImporter htmlParser = new HTMLParserImporter(theFile, destFolder);
		
		// Store flag if a link is higher or absolute
		if((htmlParser.isHigherLocalLinkFound() || htmlParser.isAbsoluteLocalLinkFound())) {
		    _hadBadLinks = true;
		}
		
		// Retrieve all the local links
		Hashtable allLinks = htmlParser.getLinks();
		
		// Enumerate thru all elements copying keys(source path) to values (destination path)
		Enumeration keys = allLinks.keys();
		
		while(keys.hasMoreElements()) {
			if(isCancelled()) return;
			
			File originalFile = (File)keys.nextElement();
			File destinationFile = (File)allLinks.get(originalFile);
			
			destinationFile.getParentFile().mkdirs();
			
			// check for any ../. (parent folder)links and ignore them...
			if(!destinationFile.isDirectory()){
			    // Check if file exists and ask user
			    if(destinationFile.exists() && (!_yesToAll)) {
			        int answer = askUserOverwriteFile(destinationFile);
			        
			        if(answer == YesAllNoDialog.YES_TO_ALL) {
			            _yesToAll = true;
			        }
			        
			        if(answer == YesAllNoDialog.YES || answer == YesAllNoDialog.YES_TO_ALL){
			            FileUtils.copyFile(originalFile, destinationFile);
			            _hasImportedFiles = true;
			        }
			        
			        else if(answer == YesAllNoDialog.CANCEL || answer == YesAllNoDialog.CLOSE) {
			            _cancelOperation = true;
			            return;
			        }
			    }
			    
			    else {
			        FileUtils.copyFile(originalFile, destinationFile);
			        _hasImportedFiles = true;
			    }
			}
		}
	}
	
	/**
	 * Ask user whether they want to over-write a file or folder.
	 * 
	 * @param file - The file/folder in question
	 * @return users choice for yes, yes to all, no, cancel or close
	 */
	protected int askUserOverwriteFile(File file){
		YesAllNoDialog dialog = new YesAllNoDialog();
		String message = file.getName() + " " + Messages.getString("uk.ac.reload.editor.contentpackaging.resourceview.CP_ResourceImporter.0") + "\n" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
													   + Messages.getString("uk.ac.reload.editor.contentpackaging.resourceview.CP_ResourceImporter.1"); //$NON-NLS-1$
		
		return dialog.getUserResponse(EditorFrame.getInstance(), message, Messages.getString("uk.ac.reload.editor.contentpackaging.resourceview.CP_ResourceImporter.2")); //$NON-NLS-1$
	}
	
	/**
	 * Find out if user cancelled
	 * 
	 * @return True if user pressed cancel at any point
	 */
	protected boolean isCancelled() {
		boolean progressCancelled = false;
		if(_progressMonitor != null) {
		    progressCancelled = _progressMonitor.isCanceled();
		}
		return _cancelOperation || progressCancelled;
	}
	
	/**
	 * Show a message that some links were higher or absolute
	 */
	protected void showWarningMessage() {
		JOptionPane.showMessageDialog(EditorFrame.getInstance(),
		        Messages.getString("uk.ac.reload.editor.contentpackaging.resourceview.CP_ResourceImporter.3"),  //$NON-NLS-1$ 
				// + "\n" + Messages.getString("uk.ac.reload.editor.contentpackaging.resourceview.CP_ResourceImporter.4"), //$NON-NLS-1$ //$NON-NLS-2$
				Messages.getString("uk.ac.reload.editor.contentpackaging.resourceview.CP_ResourceImporter.5"), //$NON-NLS-1$
				JOptionPane.WARNING_MESSAGE);
	}
	
}
