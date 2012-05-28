
package uk.ac.reload.testsupport;

import java.io.File;
import java.io.IOException;
import java.net.URI;

import javax.swing.filechooser.FileSystemView;

import org.jdom.Document;
import org.jdom.Element;


/**
 * FileSupport Utils for Testing
 * 
 * @author Phillip Beauvoir
 * @version $Id: FileSupport.java,v 1.1 1998/03/26 16:03:03 ynsingh Exp $
 */
public class FileSupport {
    
    /**
     * Temporary file prefix name
     */
    public static final String strTmpFilePrefix = "~reloadUnitTest";
    
    /**
     * @return The Temporary Testing Main Folder for testing.
     * It's best that Test routines that create folders delete this folder on tearDown()
     */
    public static File getMainTestFolder() {
        File tmpFolder = new File(System.getProperty("user.home"), "~reload_unit_tests~");
        tmpFolder.mkdirs();
        tmpFolder.deleteOnExit();
        return tmpFolder;
    }

    /**
     * @return a Temporary Folder for testing.  Will be deleted on exit (if empty).
     */
    public static File getTempFolder(String folderName) {
        File tmp = new File(getMainTestFolder(), folderName);
        tmp.mkdirs();
        tmp.deleteOnExit();
        return tmp;
    }

    /**
     * @return a Temporary File handle for testing.  Will be deleted on exit.
     */
    public static File getTempFile(String extension) throws IOException {
        File tmp = File.createTempFile(strTmpFilePrefix, extension, getMainTestFolder());
        tmp.deleteOnExit();
        return tmp;
    }

    /**
     * @return A Dummy test Document with root Element set to "root"
     */
    public static Document getTestDocument() {
        Document doc = new Document();
        Element root = new Element("root");
        doc.setRootElement(root);
        return doc;
    }
    
    /**
     * Will compare a given source Folder with a target Folder and compare that the files
     * therein are all there and are the same sizes
     * @param srcFolder
     * @param targetFolder
     * @throws IOException
     */
    public static void checkSourceAndTargetFolderSame(File srcFolder, File targetFolder) throws IOException {
        File[] srcFiles = srcFolder.listFiles();
        for(int i = 0; i < srcFiles.length; i++) {
            File srcFile = srcFiles[i];
            // Get relative path
            String path = getRelativePath(srcFolder, srcFile);
            if(srcFile.isDirectory()) {
                File newtargetFolder = new File(targetFolder, path);
                checkSourceAndTargetFolderSame(srcFile, newtargetFolder);
            }
            else {
                File targetFile = new File(targetFolder, path);
                checkSourceAndTargetFileSame(srcFile, targetFile);
            }
        }
    }
    
    /**
     * Will compare a given source File with a target File and compare that the file is there
     * and is the same size as the source File
     * @param srcFile
     * @param targetFile
     * @throws IOException
     */
    public static void checkSourceAndTargetFileSame(File srcFile, File targetFile) throws IOException {
        if(!srcFile.exists()) {
            throw new IOException("Source File doesn't exist: " + targetFile);
        }
        if(!targetFile.exists()) {
            throw new IOException("Target File doesn't exist: " + targetFile);
        }
        if(targetFile.length() != srcFile.length()) {
            throw new IOException("Files don't compare in size: " + srcFile + " and " + targetFile);
        }
    }
    
	/**
	 * Get a relative path for a file given its relationship to rootFolder
	 */
	public static String getRelativePath(File rootFolder, File file) {
        // Important - URI.relativize() is case sensitive
        // So relativing d:\myfolder and D:\myfolder\afile.text will fail
        // So we use the Canonical path
		try {
            rootFolder = rootFolder.getCanonicalFile();
    	    file = file.getCanonicalFile();
        } catch(IOException ex) {
            ex.printStackTrace();
        }
		
		URI uriRoot = rootFolder.toURI();
		URI uriFile = file.toURI();
		
		URI result = uriRoot.relativize(uriFile);
		if(result == null) {
		    return file.getName();
		}
		
		String str = result.getPath();
		return str == null ? file.getName() : str;
	}
    
	/**
	 * Delete a folder and its contents
	 * @param afolder -  a folder
	 */
	public static void deleteFolder(File afolder) throws IOException {
	    if(afolder == null) {
	        return;
	    }
	    
	    // Not root directories
	    // This way does not work where afolder = new File("aFolder");
	    // File parent = afolder.getParentFile();
	    File parent = new File(afolder.getAbsolutePath()).getParentFile();
	    if(parent == null) {
	        throw new IOException("Cannot delete root folder");
	    }
	    
	    FileSystemView view = FileSystemView.getFileSystemView();
	    if(view.isRoot(afolder)) {
	        throw new IOException("Cannot delete root folder");
	    }
	    
	    if(afolder.exists() && afolder.isDirectory()) {
	        //delete content of directory:
	        File[] files = afolder.listFiles();
	        int count = files.length;
	        for(int i = 0; i < count; i++) {
	            File f = files[i];
	            if(f.isFile()) {
	                f.delete();
	            }
	            else if(f.isDirectory()) {
	                deleteFolder(f);
	            }
	        }
	        afolder.delete();
	    }
	}
}
