package com.erp.nfes;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.ServletException;
import java.util.Properties;


public class GetImageServlet extends HttpServlet{

	  public void doGet(HttpServletRequest  request, HttpServletResponse response)
	     throws ServletException, IOException{
		  
		  String propFileName = "fileuploadpath.properties";
		  Properties properties = GetPropertiesFile.GetPropertiesFileFromCONF(propFileName);
		  String path = properties.getProperty("DESTINATION_DIR_PATH");		  
		  String filename =path+"/"+ request.getParameter("filename");
		  //System.out.println("File Name:"+filename);
		  File file = new File(filename);
      try{
    	 OutputStream os 			= response.getOutputStream();
         FileInputStream fis 		= new FileInputStream(file);
         BufferedInputStream bis 	= new BufferedInputStream(fis);
         ServletContext context  	= getServletConfig().getServletContext();
     	 String  mimetype 			= context.getMimeType( filename );
         byte[] bytes 				= new byte[bis.available()];
         response.setContentType((mimetype != null) ? mimetype : "application/octet-stream" );
         bis.read(bytes);
         os.write(bytes);
      }
      catch(IOException e){

      }
   }
}