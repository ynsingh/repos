package org.bss.brihaspatisync.network.desktop_sharing;

/**
 * DesktopSharingRequestHandler.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2010
 */


import java.net.Socket;

public class DesktopSharingRequestHandler {

	private static DesktopSharingRequestHandler req_handler=null;

	private DesktopSharingRequestHandler(){}
	private LD l=new LD();
	
	public synchronized static DesktopSharingRequestHandler getController()  {
		if( req_handler == null ) {
       			req_handler = new DesktopSharingRequestHandler();
     		}
     		return req_handler;
   	}

	protected synchronized String ProcessClientRequest(Socket s) {
		try {
			synchronized(s) {
				java.io.DataInputStream dis=new java.io.DataInputStream(s.getInputStream());
				java.io.DataOutputStream out = new java.io.DataOutputStream(s.getOutputStream());
        	                String request=dis.readLine();
                	        java.util.StringTokenizer st=new java.util.StringTokenizer(request);
                        	String header=st.nextToken();
	                        if(header.equals("GET")) {
	                               	l.CaptureScreenByteArray();
					java.io.FileInputStream fin=new java.io.FileInputStream(new java.io.File("saved.jpeg"));
                                        int ch;
                                        do
                                        {
                                                ch=fin.read();
                                                out.writeUTF(String.valueOf(ch));
                                                out.flush();
                                        }
                                        while(ch!=-1);
                                        fin.close();
                                	out.close();
                                        dis.close();
					return "successfull";
				}
			}
       		} catch(Exception e) {
         		org.bss.brihaspatisync.network.Log.getController().setLog("Exceptions in DesktopSharingRequestHandler in ProcessClientRequest  method !!! "+e.getMessage());
		}
		return "unsuccessfull";
	}
}
