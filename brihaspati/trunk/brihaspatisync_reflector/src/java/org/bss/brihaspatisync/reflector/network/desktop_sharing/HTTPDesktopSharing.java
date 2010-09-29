package org.bss.brihaspatisync.reflector.network.desktop_sharing;

/*
 * HTTPDesktopSharing.java
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2010
 */


import javax.swing.JOptionPane;

/**
 * @author <a href="mailto: arvindjss17@gmail.com" > Arvind Pal </a>
 */

public class HTTPDesktopSharing  {
	
	private boolean flag=false;
	private String clientip="";
	
	private static HTTPDesktopSharing httpDesktopSharing =null;
	
	public void setHTTPDesktopSharingIP(String clientip){ 
		this.clientip=clientip.trim();
	}
	
	public synchronized static  HTTPDesktopSharing getController()  {
                if(  httpDesktopSharing == null ) {
                        httpDesktopSharing = new  HTTPDesktopSharing();
                }
                return httpDesktopSharing;
        }

	public synchronized String runstart() {
		try {
        		java.net.URL serverAddress = new java.net.URL("http://"+clientip+":8884/Lecti_id");
                        java.net.HttpURLConnection connection = (java.net.HttpURLConnection)serverAddress.openConnection();
                	connection.setRequestMethod("GET");
	                connection.setDoOutput(true);
	                connection.setDoInput(true);
                        connection.connect();
			if(connection !=null) {
                           try {
          		     	java.io.DataInputStream dis =new java.io.DataInputStream(new java.io.BufferedInputStream(connection.getInputStream()));
        	                java.io.FileOutputStream fout=new java.io.FileOutputStream(new java.io.File("saved.jpeg"));
				try {
					String  str="";
                	               	while((str=dis.readUTF())!=null) {
						fout.write(Integer.parseInt(str)); 
						fout.flush();
                               	        }
				} catch(Exception ex) {  System.out.println("Error in 1 "+ex.getMessage()); }
                               	fout.close();
	                        dis.close();
				return "Successfully";
                	   } catch(Exception ep){
                        	try {
                               		connection.disconnect();
                                       	connection=null;
					System.out.println("Error in "+ep.getMessage());
        	               	}catch(Exception ex){ }
                     	   }
			}else 
				System.out.println("Error in cinnection !! ");
             	}catch(Exception ex) { System.out.println("Error in last catch "+ex.getMessage()); }
		return "";
     	}
	
	
}
