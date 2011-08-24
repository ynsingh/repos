package com.erp.nfes;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.ServletException;
import java.io.*;
import java.util.Properties;

public class DownloadFile  extends HttpServlet
{
    public void doGet(HttpServletRequest  request, HttpServletResponse response)
			     throws ServletException, IOException
    {

        String filename = request.getParameter("filename");
        String userId=request.getParameter("userId");
        String ctrlName=request.getParameter("ctrlName");
        
	    Properties properties = new Properties();
        try
        {
      		properties.load(new FileInputStream("../conf/fileuploadpath.properties"));
			String path = properties.getProperty("DESTINATION_DIR_PATH")+"/"+userId;
			if (ctrlName.equals("upload_photo")){
				path=path + "/photo";
			}
			doDownload(request,response,(path+"/"+filename),filename);

        }
        catch(Exception e)
        {
           e.printStackTrace();
	   return;
        }

    }
    private void doDownload( HttpServletRequest req, HttpServletResponse resp,String filename, String original_filename )throws IOException
    {
    	File                f        = new File(filename);
    	int                 length   = 0;
    	ServletOutputStream op       = resp.getOutputStream();
    	ServletContext      context  = getServletConfig().getServletContext();
    	String              mimetype = context.getMimeType( filename );

    	//
    	//  Set the response and go!
    	//
    	//
    	resp.setContentType( (mimetype != null) ? mimetype : "application/octet-stream" );
    	resp.setContentLength( (int)f.length() );
    	resp.setHeader( "Content-Disposition", "attachment; filename=\"" + original_filename + "\"" );

    	//
    	//  Stream to the requester.
    	//
    	byte[] bbuf = new byte[4 * 1024];
    	DataInputStream in = new DataInputStream(new FileInputStream(f));

    	while ((in != null) && ((length = in.read(bbuf)) != -1))
    	{
    		op.write(bbuf,0,length);
    	}

    	in.close();
    	op.flush();
    	op.close();
    }
}
