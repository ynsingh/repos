package com.erp.nfes;

import java.util.*;
import java.io.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class fileupload extends HttpServlet {

	private File tmpDir;
	private File destinationDir;
	int fs;
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		Properties prop = new Properties();
		
		try {
			prop.load(new FileInputStream("../conf/fileuploadpath.properties"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String TMP_DIR_PATH = prop.getProperty("TMP_DIR_PATH");
		fs=Integer.parseInt(prop.getProperty("filesize"));
		tmpDir = new File(TMP_DIR_PATH);
		if(!tmpDir.isDirectory()) {
			throw new ServletException(TMP_DIR_PATH + " is not a directory");
		}
		String DESTINATION_DIR_PATH = prop.getProperty("DESTINATION_DIR_PATH");
		destinationDir = new File(DESTINATION_DIR_PATH);
		if(!destinationDir.isDirectory()) {
			throw new ServletException(DESTINATION_DIR_PATH+" is not a directory");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		DiskFileItemFactory  fileItemFactory = new DiskFileItemFactory ();
		/*
		 *Set the size threshold, above which content will be stored on disk.
		 */
		fileItemFactory.setSizeThreshold(fs*1024*1024);
		/*
		 * Set the temporary directory to store the uploaded files of size above threshold.
		 */
		fileItemFactory.setRepository(tmpDir);
		
		ServletFileUpload uploadHandler = new ServletFileUpload(fileItemFactory);
		try {
			/*
			 * Parse the request
			 */System.out.println("01");
			List items = uploadHandler.parseRequest(request);System.out.println("02");
			Iterator itr = items.iterator();System.out.println("03");
			while(itr.hasNext()) {
				FileItem item = (FileItem) itr.next();
				/*
				 * Handle Form Fields.
				 */
				if(!(item.isFormField())) {
					String fn= item.getName();
					if(fn.indexOf("\\")>0)
					{
					   int lo=fn.lastIndexOf("\\");
					   fn=fn.substring((lo+1));
				    }

					/*
					 * Write file to the ultimate location.
					 */

					File file = new File(destinationDir,fn);
					item.write(file);
				}
			}
		}catch(FileUploadException ex) {
			log("Error encountered while parsing the request",ex);
		} catch(Exception ex) {
			log("Error encountered while uploading file",ex);
		}
	
	}


}