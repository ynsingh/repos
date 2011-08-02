package com.erp.nfes;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import java.util.Properties;


public class GetImageServlet extends HttpServlet{

	  public void doGet(HttpServletRequest  request, HttpServletResponse response)
	     throws ServletException, IOException{

		  Properties properties = new Properties();
		  properties.load(new FileInputStream("../conf/fileuploadpath.properties"));
		  String path = properties.getProperty("DESTINATION_DIR_PATH");
		  String filename =path+"/"+ request.getParameter("filename");
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