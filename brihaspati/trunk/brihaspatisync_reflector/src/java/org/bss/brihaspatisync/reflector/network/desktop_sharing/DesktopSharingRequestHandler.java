package org.bss.brihaspatisync.reflector.network.desktop_sharing;

/**
 * DesktopSharingRequestHandler.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2010
 */


import java.net.Socket;

import java.io.File;
import java.io.PrintWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;

import java.util.Vector;
import javax.imageio.ImageIO;
import java.util.StringTokenizer;
import java.awt.image.BufferedImage;


public class DesktopSharingRequestHandler {

	private static DesktopSharingRequestHandler req_handler=null;

	private DesktopSharingRequestHandler(){}

	public synchronized static DesktopSharingRequestHandler getController()  {
		if( req_handler == null ) {
       			req_handler = new DesktopSharingRequestHandler();
     		}
     		return req_handler;
   	}

	protected synchronized String ProcessClientRequest(Socket s) {
		try {
			synchronized(s) {
				DataInputStream dis=new DataInputStream(s.getInputStream());
				DataOutputStream output = new DataOutputStream(s.getOutputStream());
        	                String request=dis.readLine();
                	        StringTokenizer st=new StringTokenizer(request);
                        	String header=st.nextToken();
	                        if(header.equals("GET")){
					String str=HTTPDesktopSharing.getController().runstart();
        	                        java.io.FileInputStream fin=new java.io.FileInputStream(new java.io.File("saved.jpeg"));
	                                int ch;
                	                do
                        	        {
                                	        ch=fin.read();
        	                                output.writeUTF(String.valueOf(ch));
                	                        output.flush();
                        	        }
                                	while(ch!=-1);
		         	        fin.close();
                	        	output.close();
        	                      	return "successfull";
                	      	}
			}
		} catch(Exception e){
                        System.out.println("Error on reading !!! "+e);
                }
		return "unsuccessfull";
	}
}
