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

package uk.ac.reload.editor.contentpackaging;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Vector;

import org.jdom.Element;

import uk.ac.reload.diva.util.FileUtils;
import uk.ac.reload.diva.util.GeneralUtils;
import uk.ac.reload.diva.util.IProgressMonitor;
import uk.ac.reload.dweezil.gui.DweezilFolderChooser;
import uk.ac.reload.dweezil.gui.DweezilProgressMonitor;
import uk.ac.reload.dweezil.util.NativeLauncher;
import uk.ac.reload.editor.EditorFrame;
import uk.ac.reload.editor.Messages;
import uk.ac.reload.editor.contentpackaging.xml.ContentPackage;
import uk.ac.reload.editor.properties.EditorProperties;
import uk.ac.reload.moonunit.contentpackaging.CP_Core;

/**
 * The Content Package Editor Viewer
 *
 * @author Phillip Beauvoir
 * @author Paul Sharples
 * @version $Id: CP_Viewer.java,v 1.1 1998/03/26 15:24:11 ynsingh Exp $
 */
public class CP_Viewer {
	/**
	 * The Content Package to Display
	 */
	private ContentPackage _contentPackage;
	
	/**
	 * Used to keep track of how many organizations there are in a manifest
	 * (this is array based, so initially set to -1)
	 */
	private int _orgCount = -1;
	
	/**
	 * Used to keep track of how many items there are in a organization
	 * (this is array based, so initially set to -1)
	 */    
	private int _itemCount = -1; 
	
	/**
	 * Hold the default organization id. 
	 */
	private String _defaultOrganization;
	
	
	/**
	 * Constructor
	 */
	public CP_Viewer(ContentPackage cp) {
		_contentPackage = cp;
	}
	
	/**
	 * Launch the Package in the Browser.  
	 */
	public void launch() throws IOException {
		// Parse and write results to Strings using Absolute Paths
	    String[] javascriptStrings = parse(false);
		
		// Save Strings to Navfile
		writeItemsToFile(javascriptStrings, getNavigationFile());
	    
		// Launch
		NativeLauncher.launchFile(getPreviewFile());
	}

	/**
	 * Parse the manifest
	 * @param useRelativePaths If true will use the relative paths else absolute paths
	 * @return A String Array for the Nav File
	 */
	protected String[] parse(boolean useRelativePaths) {
	    // New Vector
	    Vector v = new Vector();

		// Package Links - use a clone so we can add referenced elements
	    Element manifestRoot = (Element)_contentPackage.getRootElement().clone();
	    
	    // now get the organizations node    	
	    Element orgs = manifestRoot.getChild(CP_Core.ORGANIZATIONS, manifestRoot.getNamespace());        	
	    // Figure out the default org
	    if(orgs.getAttributeValue(CP_Core.DEFAULT) != null) {
	        _defaultOrganization = orgs.getAttributeValue(CP_Core.DEFAULT);
	    }  
	    
	    writePackageSettings(v, "packageName", _contentPackage.getProjectName());    		 //$NON-NLS-1$
	    
	    // now call createNavLinks() which should interrogate the org/item structure
	    createNavLinks(v, manifestRoot, "menu", useRelativePaths);    	 //$NON-NLS-1$
	    
	    // Convert Vector to String array
	    String[] javascriptStrings = new String[v.size()];
	    v.copyInto(javascriptStrings);
	    return javascriptStrings;
	}
	
	/**
	 * Takes the array of strings (javascript code) and writes them to a file
	 * 
	 * @param javascriptStrings
	 * @param navigationFile
	 * @throws IOException
	 */
	protected void writeItemsToFile(String[] javascriptStrings, File navigationFile) throws IOException {
	    // New Writer    
	    BufferedWriter out = new BufferedWriter(new FileWriter(navigationFile));
	    // Now write everything in the array to file...
	    for(int i = 0; i < javascriptStrings.length; i++) {
	        out.write(javascriptStrings[i] + System.getProperty("line.separator")); //$NON-NLS-1$
	    }
	    out.flush();
	    out.close();
	}
	
	/**
	 * @return - a File referencing the "preview" folder
	 */
	public File getPreviewFolder() {
		return EditorProperties.getFileProperty("preview.dir"); //$NON-NLS-1$
	}    
	
	/**
	 * @return - a File referencing the preview file
	 */
	public File getPreviewFile() {
		return new File(getPreviewFolder(), "ReloadContentPreview.htm"); //$NON-NLS-1$
	}    

	/**
	 * @return - a File referencing the Nav file
	 */
	public File getNavigationFile() {
		return new File(getPreviewFolder(), "ReloadContentPreviewFiles/CPOrgs.js"); //$NON-NLS-1$
	}    

	/**
	 * Recurses down the JDOM Document. When organizations or Items are found,
	 * then the desired attributes are found and then added to a vector (they are eventually written 
	 * to a javascript file which the tree widget can understand.)
	 * 
	 * @param javascriptStrings The Vector of Strings to save
	 * @param element - the JDOM element within the document
	 * @param menuParent - Used for the tree widget - each node on the javascript tree, needs to know it parent
	 * @param useRelativePaths
	 */
	protected void createNavLinks(Vector javascriptStrings, Element element, String menuParent, boolean useRelativePaths) {
		String name = element.getName();
		
		// ORGANIZATION    	
		if(name.equals(CP_Core.ORGANIZATION) && _contentPackage.isDocumentNamespace(element)) {
			_itemCount = -1;
			++_orgCount;
			String orgId = element.getAttributeValue(CP_Core.IDENTIFIER);    		
			menuParent = "menu"; //$NON-NLS-1$
			String title = "Organization"; //$NON-NLS-1$
			// Display Title if there is one
			Element titleElement = element.getChild(CP_Core.TITLE, element.getNamespace());
			if(titleElement != null) {
				if(!titleElement.getText().equals("")) { //$NON-NLS-1$
				    title = titleElement.getText();
				}
			}
			// find out if this the default organization...    		
			if(_defaultOrganization != null){
				if(_defaultOrganization.equals(orgId)){
					writePackageSettings(javascriptStrings, "_defaultOrg", _orgCount); //$NON-NLS-1$
				}
			}    		
			writeOrganization(javascriptStrings, title, orgId);
		}    	
		
		// ITEM
		else if(name.equals(CP_Core.ITEM) && _contentPackage.isDocumentNamespace(element)) {
			++_itemCount;
			
			String itemId = element.getAttributeValue(CP_Core.IDENTIFIER);
			String hyperLink = ""; //$NON-NLS-1$
			
			// Display Title if there is one
			String title = "Item"; //$NON-NLS-1$
			Element titleElement = element.getChild(CP_Core.TITLE, element.getNamespace());
			if(titleElement != null) {
				if(!titleElement.getText().equals("")) { //$NON-NLS-1$
				    title = titleElement.getText();
				}
			}
			
			// check to see that the isvisible attribute is not set to false...
			String isVisibleAttrib = element.getAttributeValue(CP_Core.ISVISIBLE);
			if(isVisibleAttrib != null) {
				if(isVisibleAttrib.equals("false")) title = "* hidden"; //$NON-NLS-1$ //$NON-NLS-2$
			}
			
			// What does this Item reference?
			Element ref_element = _contentPackage.getReferencedElement(element);
			
			if(ref_element != null) {
				String ref_name = ref_element.getName();
				
				// A RESOURCE
				if(ref_name.equals(CP_Core.RESOURCE)) {
				    String url;
				    // Relative path for export - Note the "../" is relative to where the Nav file is!
				    if(useRelativePaths) {
				        url = _contentPackage.getRelativeURL(element);
				        // Only if local path add relative bit
				        if(GeneralUtils.isExternalURL(url) == false) {
				            url = "../" + url; //$NON-NLS-1$
				        }
					}
				    // Absolute Paths for Previewing in-situ
					else {
					    url = _contentPackage.getAbsoluteURL(element);
					    // Escape any backslashes
					    url = escapeBackslashes(url);
					}
				    
					if(url != null) {
					    hyperLink = url;    					
					}
				}
				
				// A sub-MANIFEST
				else if(ref_name.equals(CP_Core.MANIFEST)) {
					// Get ORGANIZATIONS Element
					Element orgsElement = ref_element.getChild(CP_Core.ORGANIZATIONS, ref_element.getNamespace());
					// Now we have to get the default ORGANIZATION
					if(orgsElement != null) {
					    ref_element = _contentPackage.getDefaultOrganization(orgsElement);
					}
					
					// Get the children of the referenced <organization> element and graft clones
					if(ref_element != null) {
						Iterator it = ref_element.getChildren().iterator();
						while(it.hasNext()) {
							Element ref_child = (Element) it.next();
							element.addContent((Element) ref_child.clone());
						}
					}
				}
			}
			
			else hyperLink = "javascript:void(0)"; //$NON-NLS-1$
			
			// title url id parent
			writeItem(javascriptStrings, title, hyperLink, itemId, menuParent);
			
			menuParent = itemId;
		}   
		
		// round we go again...
		Iterator it = element.getChildren().iterator();
		while(it.hasNext()) {
			Element child = (Element) it.next();    		
			createNavLinks(javascriptStrings, child, menuParent, useRelativePaths);
		}
	}
	
	/**
	 * Writes "top level" CPAPI settings to the vector of javascript code.
	 * @param name - the name of the item to set
	 * @param value - the value of the item to set (String)
	 */
	protected void writePackageSettings(Vector javascriptStrings, String name, String value) {
		javascriptStrings.add("CPAPI." + name + " = \"" + value + "\";"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	}
	
	/**
	 * Writes "top level" CPAPI settings to the vector of javascript code.
	 * @param name - the name of the item to set
	 * @param value - the value of the item to set (int)
	 */
	protected void writePackageSettings(Vector javascriptStrings, String name, int value) {
		javascriptStrings.add("CPAPI." + name + " = " + value + ";"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	}
	
	/**
	 * Writes "organization" CPAPI settings to the vector of javascript code.
	 * @param title - the title of this organization
	 * @param orgId - the organization identifier
	 */
	protected void writeOrganization(Vector javascriptStrings, String title, String orgId) {
		javascriptStrings.add("CPAPI.orgArray(" + _orgCount + ").organizationName = \"" + escapeQuotes(title) + "\";"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		javascriptStrings.add("CPAPI.orgArray(" + _orgCount + ").organizationIdentifier = \"" + orgId + "\";");    	 //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	}
	
	/**
	 * Writes "item" CPAPI settings to the vector of javascript code.
	 * @param title - the title of this item
	 * @param url - the launch url of this item    
	 * @param itemId - the item identifier
	 * @param parentMenu -  the tree node to attach this to.
	 */   
	protected void writeItem(Vector javascriptStrings, String title, String url, String itemId, String parentMenu) {
		// the javscript tree widget doesn't like hyphens, so replace them with underscores...
		parentMenu = parentMenu.replace('-', '_');    	
		// add the item...
		javascriptStrings.add("CPAPI.orgArray(" + _orgCount + ").itemArray(" + _itemCount + ").itemTitle = \"" + escapeQuotes(title) + "\";"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		javascriptStrings.add("CPAPI.orgArray(" + _orgCount + ").itemArray(" + _itemCount + ").itemIdentifier = \"" + itemId + "\";"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		javascriptStrings.add("CPAPI.orgArray(" + _orgCount + ").itemArray(" + _itemCount + ").itemParent = \"" + parentMenu + "\";"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		javascriptStrings.add("CPAPI.orgArray(" + _orgCount + ").itemArray(" + _itemCount + ").itemHyper = \"" + url + "\";");    	 //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
	}
	
	/**
	 * Utility method to escape all string delimiters found with a string
	 * @param inputString - the string to escape
	 * @return - the string with escaped quotes (in javascript format)
	 */
	protected String escapeQuotes(String inputString) {
		inputString = inputString.replaceAll("'", "\\\\\\\\'"); // replace a single quote with \\'                  //$NON-NLS-1$ //$NON-NLS-2$
		inputString = inputString.replaceAll("\"","\\\\\""); // replace a double quote with \" //$NON-NLS-1$ //$NON-NLS-2$
		return inputString;
	}
	
	/**
	 * Utility method to escape all backslashes found with a string
	 * @param inputString - the string to escape
	 * @return - the string with escaped backslashes (in javascript format)
	 */
	protected String escapeBackslashes(String inputString) {
	    inputString = inputString.replaceAll("\\\\", "\\\\\\\\"); //$NON-NLS-1$ //$NON-NLS-2$
	    return inputString;
	}
	
	// -----------------------------------------------------------------------------------------
	// Export routines
	// -----------------------------------------------------------------------------------------
	
	/**
	 * Prompt the user for a folder to export files to...
	 * @return The File or null if cancelled
	 */
	public File askExportFolder() {
		// Ask for the File Name
		DweezilFolderChooser chooser = new DweezilFolderChooser();
		chooser.setDialogTitle(Messages.getString("uk.ac.reload.editor.contentpackaging.CP_Viewer.0")); //$NON-NLS-1$
		int returnVal = chooser.showOpenDialog(EditorFrame.getInstance());
		// User Cancelled
		if(returnVal != DweezilFolderChooser.APPROVE_OPTION) return null;
		// Get the chosen File
		File file = chooser.getSelectedFileAndStore();
		// Create if it doesn't exist
		file.mkdirs();
		return file;
	}
	
	/**
	 * Export Content package Preview Files with optional DweezilProgressMonitor.
	 * 
	 * @param exportFolder
	 * @param progressMonitor Optional DweezilProgressMonitor. Can be null.
	 * @throws IOException
	 */
	public void exportContentPackage(File exportFolder, DweezilProgressMonitor progressMonitor) throws IOException {	
	    // Can't copy to a sub-folder
	    for(File dest = exportFolder.getParentFile(); dest != null; dest = dest.getParentFile()) {
	        if(dest.equals(_contentPackage.getProjectFolder())) {
	            if(progressMonitor != null) {
	                progressMonitor.close();
	            }
	            throw new IOException(Messages.getString("uk.ac.reload.editor.contentpackaging.CP_Viewer.2")); //$NON-NLS-1$
	        }
	    }
	    
	    // Parse and write results to Strings with Relative Paths
		String[] javascriptStrings = parse(true);
		
	    // Copy Resources over only if the export folder is different otherwise you will zap
		// the original files!!!!
		if(!exportFolder.equals(_contentPackage.getProjectFolder())) {
		    boolean ok = copyResourceFiles(_contentPackage, exportFolder, progressMonitor);
		    if(!ok) {
		        return;
		    }
		}
		
		// Find out where the Preview files are
		File previewHomeFolder = getPreviewFolder();
		
		// Copy Preview files
		copyPreviewFiles(previewHomeFolder, exportFolder);
		
		// New Nav file in Export Folder
		File targetNavFile = new File(exportFolder, "ReloadContentPreviewFiles/CPOrgs.js"); //$NON-NLS-1$
		
		// Save Strings to file
		writeItemsToFile(javascriptStrings, targetNavFile);
	}
	
	/**
	 * Export Content package Preview Files with no DweezilProgressMonitor.
	 * 
	 * @param exportFolder The folder to export to
	 * @throws IOException
	 */
	public void exportContentPackage(File exportFolder) throws IOException {	
	    exportContentPackage(exportFolder, null);
	}
	
	/**
	 * Copy all referenced package files within the manifest to the folder specified by user.
	 * 
	 * NOTE - We just copy over the whole lot because some packages
	 * don't reference all the supporting files in their manifest and so the preview doesn't work.
	 * Maybe we should give an option?
	 * 
	 * @param cp the Content package
	 * @param exportFolder The folder to export to
	 * @param progressMonitor an optional ProgressMonitor. Can be null.
	 * @return False if user hits cancel in the DweezilProgressMonitor if there is one.
	 */
	protected boolean copyResourceFiles(ContentPackage cp, File exportFolder, IProgressMonitor progressMonitor) throws IOException {
        if(exportFolder.equals(_contentPackage.getProjectFolder())) {
            if(progressMonitor != null) {
                progressMonitor.close();
            }
            throw new IOException(Messages.getString("uk.ac.reload.editor.contentpackaging.CP_Viewer.1")); //$NON-NLS-1$
        }
        
        boolean result = FileUtils.copyFolder(cp.getProjectFolder(), exportFolder, progressMonitor);

        // Delete the imsmanifest.xml
        File manifest = new File(exportFolder, CP_Core.MANIFEST_NAME);
        manifest.delete();
        
        return result;
    }

	/**
	 * Copy over the html and widgets needed to display
	 * the preview in standalone mode (obtained from user.home)
	 */
	protected void copyPreviewFiles(File previewHomeFolder, File exportFolder) throws IOException {
	    FileUtils.copyFolder(previewHomeFolder, exportFolder);
	}
}