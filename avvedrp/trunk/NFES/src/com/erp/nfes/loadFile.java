package com.erp.nfes;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.ServletException;
import java.io.*;
import java.util.Properties;

import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.ContentHandler;

public class loadFile  extends HttpServlet
{
    public void doGet(HttpServletRequest  request, HttpServletResponse response)
			     throws ServletException, IOException
    {
        
        String filename = request.getParameter("filename");
	    Properties properties = new Properties();
        BufferedInputStream in = null;
        try
        {     
      		properties.load(new FileInputStream("../conf/fileuploadpath.properties"));
			String path = properties.getProperty("DESTINATION_DIR_PATH");
			String contentType="";
						
			    FileInputStream is = null;
			    try {
			      File f = new File(path+"/"+filename);
			      is = new FileInputStream(f);

			      ContentHandler contenthandler = new BodyContentHandler();
			      Metadata metadata = new Metadata();
			      metadata.set(Metadata.RESOURCE_NAME_KEY, f.getName());
			      Parser parser = new AutoDetectParser();
			      //OOXMLParser parser = new OOXMLParser();
			      parser.parse(is, contenthandler, metadata);
			      contentType= metadata.get(Metadata.CONTENT_TYPE);
			     
			      //System.out.println("Mime: " + metadata.get(Metadata.CONTENT_TYPE));
			     // System.out.println("Title: " + metadata.get(Metadata.TITLE));
			     // System.out.println("Author: " + metadata.get(Metadata.AUTHOR));
			     // System.out.println("content: " + contenthandler.toString());
			    
			    }
			    catch (Exception e) {
			      e.printStackTrace();
			    }
			    finally {
			        if (is != null) is.close();
			    }
			   
    		System.out.println("ContentType: "+contentType);
        	in = new BufferedInputStream(getClass().getResourceAsStream(path+ "/" + filename));

            response.setContentType(contentType);
            response.setHeader("Content-Disposition",  " inline; filename=" + filename);

            ServletOutputStream out = response.getOutputStream();
            
            byte[] buffer = new byte[4 * 1024];

            int data;
            while((data = in.read(buffer)) != -1)
            {
                out.write(buffer, 0, data);
            }
              out.flush();
        }
        catch(Exception e)
        {
           e.printStackTrace();
	   return;
        }
        finally
        {
            try
            {
                in.close();
            }
            catch(Exception ee)
            {
                ee.printStackTrace();
            }
        }
    }        
}
