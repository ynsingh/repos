package com.erp.nfes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

public class UploadFiles extends HttpServlet {
	private File resourceDir;
	private File tmpDir;
	private File destinationDir;
	Connection conn = null;
	String filename="";
	String controlname="";
	String userid="";
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
				
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
	    response.setContentType("text/html; charset=UTF-8");
	    out.println("<HTML>\n<HEAD>\n<TITLE>File Uploading</TITLE>");
		
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
			
		    String dir = "";
			String propFileName = "fileuploadpath.properties";
			Properties prop = GetPropertiesFile.GetPropertiesFileFromCONF(propFileName);
			String ROOT_DIR_PATH = prop.getProperty("ROOT_DIR_PATH");
			String RESOURCE_DIR_PATH = prop.getProperty("RESOURCE_DIR_PATH");
			resourceDir = new File(RESOURCE_DIR_PATH);
			if(!resourceDir.isDirectory()){
				CreateDir.CreateFolder(ROOT_DIR_PATH,"resources");
			}
			String TMP_DIR_PATH = prop.getProperty("TMP_DIR_PATH");
			tmpDir = new File(TMP_DIR_PATH);
			if(!tmpDir.isDirectory()){
				CreateDir.CreateFolder(RESOURCE_DIR_PATH,"temp");
			}
			String DESTINATION_DIR_PATH = prop.getProperty("DESTINATION_DIR_PATH");
			destinationDir = new File(DESTINATION_DIR_PATH);
			if(!destinationDir.isDirectory()){
				CreateDir.CreateFolder(RESOURCE_DIR_PATH,"nfes_files");
			}
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
				
				
				/*---- to get user id , in case other user upload a file Rajitha. */
				if(item.getFieldName().equals("userId")){
				  dir=item.getString();				  
				} 
				/*------------------------------------*/
				
				if((!(item.isFormField())) && isBlank(item.getName())) {
					String fn= item.getName();					
			        /*=============== Added on 21-02-2011 ===============*/
					controlname=item.getFieldName();
					if(controlname.equals("upload_photo")){
						destinationDir = new File(DESTINATION_DIR_PATH+"/"+dir+"/photo");	
					}
					else{
						destinationDir = new File(DESTINATION_DIR_PATH+"/"+dir);
					}
															
					if(!destinationDir.isDirectory()) {
						CreateDir.CreateFolder(DESTINATION_DIR_PATH,dir);
					   	CreateDir.CreateFolder(DESTINATION_DIR_PATH+"/"+dir,"photo"); 
					}    		       
					
					if(fn.indexOf("\\")>0)
					{
					   int lo=fn.lastIndexOf("\\");
					   fn=fn.substring((lo+1));
				    }
                    filename=fn;
					/*
					 * Write file to the ultimate location.
					 */

					File file = new File(destinationDir,fn);
					item.write(file);
					break;
				} 
				
			}
			out.println("<script language=\"javascript\">\n" +
			"function init(){\nwindow.parent.document.getElementById('upload_status_"+controlname+"').style.color='green';\n" +
			"window.parent.document.getElementById('upload_status_"+controlname+"').innerHTML='File uploading completed';");
			out.println("var r = confirm(\"Would you like to save uploaded file name now?\");\nif(r==true){");
		/*	String str="'<li><a href=\"./DownloadFile?filename="+filename+"&amp;userId="+userid+ "&amp;ctrlName="+ controlname + "\" target=\"_blank\">"+filename+"</a></li>'";
			if(controlname.equals("upload_photo")){
				out.println("window.parent.document.getElementById('"+controlname+"_filelist').innerHTML="+str+";");
			}else{
				out.println("var oldHTML=window.parent.document.getElementById('"+controlname+"_filelist').innerHTML;\n" +
				"window.parent.document.getElementById('"+controlname+"_filelist').innerHTML="+str+"+oldHTML;");
			}*/
			out.println("window.parent.document.getElementById('btsave').focus();\n" +
			"window.parent.document.getElementById('btsave').click();}else{");
		    out.println("window.parent.document.getElementById('"+controlname+"').value='';");
			// out.println("alert(\"Click Save button to save uploaded file name\")");
			out.println("window.parent.document.getElementById('upload_status_"+controlname+"').style.color='black';\n" +
			"window.parent.document.getElementById('upload_status_"+controlname+"').innerHTML='';");
			out.println("}\n}\n</script>\n</HEAD>\n<BODY onload=\"init();\">\n<form></form>\n" +
			"</BODY>\n</HTML>");
			out.close();
		}catch(FileUploadException ex) {
			log("Error encountered while parsing the request",ex);
		} catch(Exception ex) {
			log("Error encountered while uploading file",ex);
		}
	
	}

	private boolean isBlank(String name){
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