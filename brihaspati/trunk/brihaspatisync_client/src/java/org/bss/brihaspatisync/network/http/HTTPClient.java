package org.bss.brihaspatisync.network.http;

/**
 * HTTPClient.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2012, ETRG, IIT Kanpur.
 */

import java.awt.Frame;
import java.lang.Long;

import  java.util.LinkedList;
import java.util.StringTokenizer;

import java.io.DataInputStream;
import java.io.BufferedInputStream;
import org.bss.brihaspatisync.util.Language;
import org.bss.brihaspatisync.util.ClientObject;
import org.bss.brihaspatisync.util.RuntimeDataObject;
import org.bss.brihaspatisync.util.ThreadController;

import org.bss.brihaspatisync.Client;
import org.bss.brihaspatisync.network.ReceiveQueueHandler;

import org.bss.brihaspatisync.network.util.Queue;
import org.bss.brihaspatisync.network.util.UtilObject;


/**
 * @author <a href="mailto: ashish.knp@gmail.com"  >Ashish Yadav</a>
 * @author <a href="mailto: arvindjss17@gmail.com" > Arvind Pal </a> Modify on 2013
 * @author <a href="mailto:shikhashuklaa@gmail.com">Shikha Shukla </a>Modify for multilingual implementation. 
 */

public class HTTPClient extends Thread {

	private int value_count=0;
	private String lect_id="";
	private String message_diff="";
	private UtilObject utilObject=UtilObject.getController();
        private ClientObject clientObject=ClientObject.getController();
	private RuntimeDataObject runtime_object=RuntimeDataObject.getController();
	private final String refHttpPort=runtime_object.getRefHttpPort();
	public HTTPClient(){ }

	public HTTPClient(String lect_id){
		this.lect_id=lect_id;
		org.bss.brihaspatisync.network.singleport.SinglePortClient.getController().addType("ch_wb_Data");
        }

	public void run() {
		
		while(ThreadController.getController().getThreadFlag()){
                     	try {
				if(ThreadController.getController().getReflectorStatusThreadFlag()) {
					String datastr="nodata";
					if(utilObject.getSendQueueSize() != 0) {
        	                		datastr=utilObject.getSendQueue();
						datastr=java.net.URLEncoder.encode(datastr);
	                	      	}
					
					String reg="";
					if(clientObject.getParentReflectorIP()!= null ) {
        		                	reg=clientObject.getParentReflectorIP();
                		                clientObject.setParentReflectorIP("null");
                       			}else {
                                		reg="null";
	                                }
					LinkedList send_queue=UtilObject.getController().getSendQueue("ch_wb_Data");
					String message=clientObject.getUserRole()+","+lect_id+"req"+datastr+"req"+reg;
					if(!(message.equals(message_diff)) || (send_queue.size()== 0)) {
						message_diff=message;
						send_queue.addLast((message_diff).getBytes());
					}
				
					LinkedList cha_wb_queue=UtilObject.getController().getQueue("ch_wb_Data");
					if(cha_wb_queue.size()>0) {
						String str=new String((byte[])cha_wb_queue.get(0));
						cha_wb_queue.remove(0);		
						java.util.StringTokenizer Tok = new java.util.StringTokenizer(str);
						org.bss.brihaspatisync.gui.StatusPanel.getController().sethttpClient("yes");
						if (Tok.hasMoreElements()) {
							String str1=(String)Tok.nextElement();
							String str2=(String)Tok.nextElement();
							str2=java.net.URLDecoder.decode(str2);
							if(!str1.equals("nodata")) {
								if(str1.equals("sessionlist_timeout") && (value_count>5)){
									org.bss.brihaspatisync.gui.Logout.getController().sessionOutMessage();
								}else
									RuntimeDataObject.getController().setUserList(str1);
								if(value_count<7)
									value_count++;
							}
							if(!str1.equals("nodata"))
								utilObject.setRecQueue(str2);	
						}
					}
					networkHandler();
				}
				this.sleep(2000);this.yield();
			}catch(Exception ex) {	System.out.println("Error in HTTP Client "+ex.getMessage());   }
		}
  	}

	/**
	 * This method is used to netwrok very slow . 
	 * then remove data from sending queue 
	 */ 

        private void networkHandler() {
                try {
                        LinkedList sendqueue=UtilObject.getController().getSendQueue("ch_wb_Data");
                        if(sendqueue.size()>15) {
                                for(int i=0;i<5;i++) {
                                        sendqueue.remove(0);
                                }
                        }
                }catch(Exception epe){System.out.println("Error in networkHandler class "); }
        }
}
