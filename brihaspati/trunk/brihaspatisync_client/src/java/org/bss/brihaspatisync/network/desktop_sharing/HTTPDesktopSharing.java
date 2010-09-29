package org.bss.brihaspatisync.network.desktop_sharing;

/*
 * HTTPDesktopSharing.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2010
 */


import javax.swing.JOptionPane;

import org.bss.brihaspatisync.tools.desktop_sharing.Desktop_Sharing;

/**
 * @author <a href="mailto: arvindjss17@gmail.com" > Arvind Pal </a>
 */

public class HTTPDesktopSharing  implements Runnable {
	
	private Thread runner=null;
	
	private boolean flag=false;

	private String reflectorIP ="";
	
	private org.bss.brihaspatisync.util.ClientObject clientObject=org.bss.brihaspatisync.util.ClientObject.getController();
	
	public  HTTPDesktopSharing(){ }

	private org.bss.brihaspatisync.network.Log log=org.bss.brihaspatisync.network.Log.getController();	
	/**
         * Start TCPSender Thread.
         */
        
	public void start(){
                if (runner == null) {
			flag=true;
                        runner = new Thread(this);
                        runner.start();
			log.setLog("Strat client  HTTPDesktopSharing !!");
		}
        }

        /**
         * Stop TCPSender Thread.
         */
        
	public void stop() {
                if (runner != null) {
			flag=false;
                        runner.stop();
                        runner = null;
                }
        }

	public void run() {
		if((clientObject.getUserRole()).equals("instructor")) {
			try {
				DesktopSharing.getController().start();
			}catch(Exception e){}
               	} else if((clientObject.getUserRole()).equals("student")) {
			while(flag) {
			        try {
				    Thread.sleep(10);
				    java.net.URL serverAddress = new java.net.URL("http://"+clientObject.getReflectorIP()+":8883/saved.jpeg");
			            java.net.HttpURLConnection connection = (java.net.HttpURLConnection)serverAddress.openConnection();
                        	    connection.setRequestMethod("GET");
                                    connection.setDoOutput(true);
				    connection.setReadTimeout(100000);	
                                    connection.connect();
				    java.io.DataInputStream dis =new java.io.DataInputStream(new java.io.BufferedInputStream(connection.getInputStream()));
				    java.io.FileOutputStream fout=new java.io.FileOutputStream(new java.io.File("saved.jpeg"));
        	                    String str="";
                	            int ch;
                        	    try{
                                	while((str=dis.readUTF())!=null){
                                        	ch=Integer.parseInt(str);
                                                if(ch!=-1) {
        	                                           fout.write(ch);
                	                       	}
                        	      	}
                                    }finally {
                                    	if(dis != null) {
                                        	dis.close();
	                                        fout.close();
                                        	connection.disconnect();
	                                        connection=null;
        	                     	}
                	            }
				} catch(Exception ep){
                                	try {
						System.out.println(ep.getMessage());
				        } catch(Exception ex){ }
                        	}
				try {
                        		Desktop_Sharing.getController().runDesktopSharing();
				}catch(Exception e){
					log.setLog("Exception in HTTPDesktopSharing !!");
				}
			}		
  		}
	}
}
