package org.bss.brihaspatisync.network.http;

/*
 * HTTPTimer.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2007-2008
 */

//import java.util.Vector;

import java.awt.Frame;
import java.lang.Long;
import javax.swing.JOptionPane;

import java.util.Timer;
import java.util.TimerTask;
import java.util.StringTokenizer;

import java.net.URL;
import java.net.HttpURLConnection;

import java.io.DataInputStream;
import java.io.BufferedInputStream;

import org.bss.brihaspatisync.util.ClientObject;
import org.bss.brihaspatisync.util.RuntimeDataObject;
import org.bss.brihaspatisync.Client;
import org.bss.brihaspatisync.network.Log;
import org.bss.brihaspatisync.network.ReceiveQueueHandler;

import org.bss.brihaspatisync.network.util.Queue;
import org.bss.brihaspatisync.network.util.UtilObject;

/**
 * @author <a href="mailto: ashish.knp@gmail.com"  >Ashish Yadav</a>
 * @author <a href="mailto: arvindjss17@gmail.com" > Arvind Pal </a>
 */

public class HTTPClient extends TimerTask {

	private String lect_id="";
	
	private String reflectorIP ="";
	
	private Log log=Log.getController();

	private UtilObject utilObject=UtilObject.getController();

        private ClientObject clientObject=ClientObject.getController();
	
	private final String refHttpPort=RuntimeDataObject.getController().getRefHttpPort();

	public HTTPClient(){ }

	public HTTPClient(String reflectorIP,String lect_id){
		this.reflectorIP=reflectorIP;
		this.lect_id=lect_id;
        }

	public void run() {
                try {
                        try {
				String datastr="nodata";
				try {
					if(utilObject.getSendQueueSize() != 0) {
                        	        	datastr=utilObject.getSendQueue();
                                        	datastr=java.net.URLEncoder.encode(datastr);
                                	}
				}catch(Exception e){log.setLog("Error in Send data "+datastr); }
				
				String reg="";
				if(clientObject.getParentReflectorIP()!= null ){
                                        reg=clientObject.getParentReflectorIP();
                                        clientObject.setParentReflectorIP("null");
                                }else{
                                        reg="null";
                                }
				String url="http://"+reflectorIP+":"+refHttpPort+"/"+clientObject.getUserRole()+","+lect_id+"req"+datastr+"req"+reg;
				URL serverAddress = new URL(url);
				HttpURLConnection connection = (HttpURLConnection)serverAddress.openConnection();
                       		connection.setRequestMethod("GET");
                        	connection.setDoOutput(true);
                        	connection.connect();
				try{
                                	DataInputStream dis =new DataInputStream(new BufferedInputStream(connection.getInputStream()));
                        		String str="";
					while((str=dis.readUTF())!=null){
						str=java.net.URLDecoder.decode(str);
						utilObject.setRecQueue(str);
				       	}
                                	dis.close();
                 		}catch(Exception ep){
                        		try {
                                		connection.disconnect();
                                        	connection=null;
                                	}catch(Exception ex){ 
						log.setLog("connection is not disconnect()"+connection);
					}
                   		}	
                  	}catch(Exception ex) { 
					
				JOptionPane.showMessageDialog(null,"Reflector connection failed !!","Reflector Message",JOptionPane.ERROR_MESSAGE);

				log.setLog(" HTTPClient could not connect  "+ex.getMessage()); 
			}
         	}catch(Exception e){ 
			
		}
  	}
}
