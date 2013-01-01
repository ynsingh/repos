package org.iitk.livetv.util;

/**
 * @(#)JavaGetUrl.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2012 ETRG, IIT Kanpur.
 */

import java.io.*;
import java.net.*;

/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav</a>Created on 05Dec2012
 */


public class JavaGetUrl {

	private static JavaGetUrl getUrl=null;

	public static JavaGetUrl getController(){
		if(getUrl==null){
			getUrl=new JavaGetUrl();
		}
		return getUrl;
	}


    	public static void downloadFile(String urlstr, String destino) throws MalformedURLException,IOException  {
       
        	/*
         	 * Get a connection to the URL and start up a buffered reader.
         	 */

       		long startTime = System.currentTimeMillis();
        	System.out.println("Connecting to Mura site...\n");
        	URL url = new URL(urlstr);
        	url.openConnection();
        	InputStream reader = url.openStream();

        	/*
         	 * Setup a buffered file writer to write out what we read from the website.
         	 */

       		FileOutputStream writer = new FileOutputStream(destino);
        	byte[] buffer = new byte[153600];
       	 	int totalBytesRead = 0;
        	int bytesRead = 0;

        	System.out.println("Reading ZIP file 150KB blocks at a time.\n");

        	while ((bytesRead = reader.read(buffer)) > 0){  
           		writer.write(buffer, 0, bytesRead);
           		buffer = new byte[153600];
           		totalBytesRead += bytesRead;
        	}

        	long endTime = System.currentTimeMillis();
        	System.out.println("Done. " + (new Integer(totalBytesRead).toString()) + " bytes read (" + (new Long(endTime - startTime).toString()) + " millseconds).\n");
        	writer.close();
        	reader.close();
    	}
   
} // end of class


