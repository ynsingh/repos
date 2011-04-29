package com.erp.nfes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;
import java.io.*;

import javax.mail.Session;
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
	Connection conn = null;
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
				
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
	    response.setContentType("text/plain");
	    out.println("<h1>File Uploading</h1>");
		
		DiskFileItemFactory  fileItemFactory = new DiskFileItemFactory ();
		/*
		 *Set the size threshold, above which content will be stored on disk.
		 */
		fileItemFactory.setSizeThreshold(1*1024*1024);
		/*
		 * Set the temporary directory to store the uploaded files of size above threshold.
		 */
		fileItemFactory.setRepository(tmpDir);
		
		ServletFileUpload uploadHandler = new ServletFileUpload(fileItemFactory);
		try {
			
		    /*============== Added on 19-02-2011 ========================*/
			String dir="";
			ConnectDB conobj= new ConnectDB();
			conn = conobj.getMysqlConnection();
			PreparedStatement pst=conn.prepareStatement("select id from users where username=?");
		    pst.setString(1,request.getUserPrincipal().getName());
			ResultSet rs=pst.executeQuery();
			while(rs.next())
			{
				dir=Integer.toString(rs.getInt(1));
				
			}	
			
			Properties prop = new Properties();
			
			try {
				prop.load(new FileInputStream("../conf/fileuploadpath.properties"));
			} catch (FileNotFoundException e) {
				System.out.println("../conf/fileuploadpath.properties FileNotFoundException");
				e.printStackTrace();
			} catch (IOException e) {
				System.out.println("../conf/fileuploadpath.properties IOException");
				e.printStackTrace();
			}
			String TMP_DIR_PATH = prop.getProperty("TMP_DIR_PATH");
			tmpDir = new File(TMP_DIR_PATH);
			if(!tmpDir.isDirectory()) {
				throw new ServletException(TMP_DIR_PATH + " is not a directory");
			}
		  /*  String DESTINATION_DIR_PATH = prop.getProperty("DESTINATION_DIR_PATH");
			destinationDir = new File(DESTINATION_DIR_PATH+"/"+dir);
			if(!destinationDir.isDirectory()) {
				throw new ServletException(DESTINATION_DIR_PATH+" is not a directory");
			}*/
			
			/*============== End of added on 19-02-2011 ========================*/	
			
			
			/*
			 * Parse the request
			 */			
			List items = uploadHandler.parseRequest(request);
			Iterator itr = items.iterator();
			while(itr.hasNext()) {
				FileItem item = (FileItem) itr.next();
				/*
				 * Handle Form Fields.
				 */
				if((!(item.isFormField())) && isBlank(item.getName())) {
					String fn= item.getName();
					
			        /*=============== Added on 21-02-2011 ===============*/
					String DESTINATION_DIR_PATH = prop.getProperty("DESTINATION_DIR_PATH");
					if(item.getFieldName().equals("upload_photo")){
						destinationDir = new File(DESTINATION_DIR_PATH+"/"+dir+"/photo");
					}
					else{
						destinationDir = new File(DESTINATION_DIR_PATH+"/"+dir);
					}
					if(!destinationDir.isDirectory()) {
						throw new ServletException(DESTINATION_DIR_PATH+" is not a directory");
					}
    		       /*=============== End of added on 21-02-2011 ===============*/
					
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
				out.close();
			}
		}catch(FileUploadException ex) {
			log("Error encountered while parsing the request",ex);
		} catch(Exception ex) {
			log("Error encountered while uploading file",ex);
		}
	
	}

	private boolean isBlank(String name) {
		if(name.equals("")||name.equals(" ")||name.equals("null"))
		{
			return false;
		}
		else
		{
			return true;
		}
	}


}