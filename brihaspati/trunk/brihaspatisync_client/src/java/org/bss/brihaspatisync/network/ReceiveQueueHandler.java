package org.bss.brihaspatisync.network;

/**
 * ReceiveQueueHandler.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2007-2008,2012 ETRG, IIT Kanpur.
 */

import java.io.IOException;
import java.util.LinkedList;
import java.util.StringTokenizer;

import org.bss.brihaspatisync.network.util.UtilObject;
import org.bss.brihaspatisync.util.ClientObject;
import org.bss.brihaspatisync.util.ThreadController;
import org.bss.brihaspatisync.tools.chat.ChatPanel;
import org.bss.brihaspatisync.tools.whiteboard.WhiteBoardDraw;

/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a>
 * @author <a href="mailto:arvindjass17@gmail.com">Arvind Pal </a>	
 * @author <a href="mailto:pratibhaayadav@gmail.com">Pratibha </a> Modified ShowMsg() for received chat signalling	
 */


public class ReceiveQueueHandler implements Runnable{

	private int value_count=0;
        private Thread runner = null;
	private boolean flag = false;	
	private boolean startallthreadflag = false;	
        private static ReceiveQueueHandler rqh=null;
	
	private UtilObject utilobject = UtilObject.getController();
        /**
         * Create Class Controller.
         */
	public static ReceiveQueueHandler getController(){
                if(rqh==null){
                        rqh=new ReceiveQueueHandler();
                }
                return rqh;
        }

	public ReceiveQueueHandler(){ }

	/**
         * Start ReceiveQueueHandler Thread.
         */
	
        public synchronized void start() throws IOException {
		if (runner == null) {
			flag=true;
                        runner = new Thread(this);
			utilobject.addType("Chat_Wb_Data");
			utilobject.addType("UserList_Data");
			runner.start();
			System.out.println("ReceiveQueueHandler has started.");
                }
        }

	/**
         * Stop receiveQueueHandler Thread.
         */
        private synchronized void stop() {
                if (runner != null) {
			flag=false;
			runner = null;
			startallthreadflag=false;
			System.out.println("ReceiveQueueHandler has stopped.");
             	}
        }

	/**
         * Get Entry from Receive Queue and send it to appropriate tool for performing Action on GUI. 
         */
	public void run(){
		while(flag && ThreadController.getThreadFlag()){
			try{
				synchronized(utilobject){
					LinkedList cha_wb_queue=utilobject.getReceiveQueue("Chat_Wb_Data");
					if(cha_wb_queue.size()>0) {
	                        	        String str=new String((byte[])cha_wb_queue.remove()); 	
						str=java.net.URLDecoder.decode(str);
						StringTokenizer st=new StringTokenizer(str,"$");
						while(st.hasMoreTokens()){
                                                	String type=st.nextToken();
							if(type.equals("wb")){
                	                			WhiteBoardDraw.getController().getDraw_vector().addElement(str); 
							}else if(type.equals("ch")){
								String data=st.nextToken();
								ChatPanel.getController().showMsg(data);
							}
						}
					}
						
					LinkedList user_list_data=utilobject.getReceiveQueue("UserList_Data");
					if(user_list_data.size()>0) {
						String str=new String((byte[])user_list_data.remove());
						if(!str.equals("nodata")) {
                                                	if(str.equals("sessionlist_timeout") && (value_count>5)){
                                                        	new org.bss.brihaspatisync.gui.Logout().sessionOutMessage();
                                                     	}else
                                                        	org.bss.brihaspatisync.util.RuntimeDataObject.getController().setUserList(str);
                                                      	if(value_count<7)
                                                        	value_count++;
                                           	}
					}
					
					if(ClientObject.getParentReflectorIP() != null ) {
                                                String parentip=ClientObject.getParentReflectorIP();
                                                ClientObject.setParentReflectorIP("");
						String message="nodata"+"req"+parentip;
						java.util.LinkedList sendqueue=utilobject.getSendQueue("UserList_Data");
		                                sendqueue.addLast(message.getBytes());
                                        }
						
					/**
					 * This method is used to netwrok very slow . 
					 * then remove data from sending queue 
					 *//* 	
					try {
						java.util.Hashtable hashtable=utilobject.get_send_queue_hashTable();
						java.util.Enumeration en=hashtable.keys();
						while (en.hasMoreElements()) {
							java.util.LinkedList sendqueue=utilobject.getSendQueue((String)en.nextElement());
				                        if(sendqueue.size()>10) {
                        				        sendqueue.clear();
	                        			}
						}
					} catch(Exception ex){ System.out.println("Exception in ReceiveQueueHandler class to remove queue for network slow"+ex.getMessage());}*/
					if(!startallthreadflag) {
						try {
							startallthreadflag=true;
				                        org.bss.brihaspatisync.network.singleport.SinglePortClient.getController().start();
        	                			WhiteBoardDraw.getController().start();
				                        ReceiveQueueHandler.getController().start();
                        				org.bss.brihaspatisync.gui.HandRaiseThreadController.getController().startHandRaiseThread();
			        	                //start audio thread
                        				String a_status=org.bss.brihaspatisync.util.AudioUtilObject.getAudioStatus();
			                        	if(a_status.equals("1")){
                        			        	org.bss.brihaspatisync.tools.audio.AudioClient.getController().startThread();
				                                if((ClientObject.getUserRole()).equals("instructor"))
        	                			                org.bss.brihaspatisync.tools.audio.AudioClient.getController().postAudio(true);
				                        }
	
        	                			//start video thread
				                        String v_status=org.bss.brihaspatisync.util.AudioUtilObject.getVideoStatus();
                        				if((ClientObject.getUserRole()).equals("instructor")) {
		        		                        if(v_status.equals("1")){
                	        			                org.bss.brihaspatisync.network.video_capture.LocalServer.getController().startLocalServer();
		                	        	                org.bss.brihaspatisync.network.video_capture.PostVideoCapture.getController().startVideoCaptureThread(false);
                			                	}
				                        } else{
				                                org.bss.brihaspatisync.network.video_capture.PostVideoCapture.getController().startVideoCaptureThread(true);
                	        			}
						} catch(Exception ex){ System.out.println("Exception in ReceiveQueueHandler class to start all thread "+ex.getMessage());}
					}
				}
				
				runner.yield();
				runner.sleep(10);
			}catch(Exception e){}
		}
		stop();
	}
}
