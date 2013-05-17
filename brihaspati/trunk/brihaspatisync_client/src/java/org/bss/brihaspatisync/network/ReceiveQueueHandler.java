package org.bss.brihaspatisync.network;

/**
 * ReceiveQueueHandler.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2007-2008,2012 ETRG, IIT Kanpur.
 */

import java.io.IOException;
import java.util.StringTokenizer;

import org.bss.brihaspatisync.network.util.Queue;
import org.bss.brihaspatisync.network.util.UtilObject;
import org.bss.brihaspatisync.util.Recorder;
import org.bss.brihaspatisync.util.ThreadController;
import org.bss.brihaspatisync.tools.chat.ChatPanel;
import org.bss.brihaspatisync.tools.whiteboard.WhiteBoardDraw;

/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a>
 * @author <a href="mailto:arvindjass17@gmail.com">Arvind Pal </a>	
 * @author <a href="mailto:pratibhaayadav@gmail.com">Pratibha </a> Modified ShowMsg() for received chat signalling	
 */


public class ReceiveQueueHandler implements Runnable{

        private Thread runner = null;

	private boolean rec_Flag = false;
	
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
		if(rec_Flag!=true){
			rec_Flag=true;
		}
		if (runner == null) {
                        runner = new Thread(this);
			runner.start();
			System.out.println("ReceiveQueueHandler has started.");
                }
        }

	/**
         * Stop receiveQueueHandler Thread.
         */
        public synchronized void stop() {
		if(rec_Flag!=false){
			rec_Flag=false;
		}
                if (runner != null) {
                        runner.stop();
			runner = null;
			System.out.println("ReceiveQueueHandler has stopped.");
             	}
        }

	/**
         * Get Entry from Receive Queue and send it to appropriate tool for performing Action on GUI. 
         */
	public void run(){
		while(rec_Flag && ThreadController.getController().getThreadFlag()){
			try{
				synchronized(utilobject){
					while(utilobject.getRecQueueSize() != 0){
						String datastr=utilobject.getRecQueue();
						StringTokenizer st=new StringTokenizer(datastr,"$");
						while(st.hasMoreTokens()){
							String type=st.nextToken();
							if(type.equals("wb")){
                	                       			WhiteBoardDraw.getController().getDraw_vector().addElement(datastr); 
							}else if(type.equals("ch")){
								String data=st.nextToken();
								// Modified by pratibha
								ChatPanel.getController().showMsg(data);
								//end of modification
							}
						}
					}

					/**
					 * This method is used to netwrok very slow . 
					 * then remove data from sending queue 
					 */ 	
					try {
						java.util.Hashtable hashtable=utilobject.get_send_queue_hashTable();
						java.util.Enumeration en=hashtable.keys();
						while (en.hasMoreElements()) {
							java.util.LinkedList sendqueue=utilobject.getSendQueue((String)en.nextElement());
				                        if(sendqueue.size()>10) {
                        				        sendqueue.clear();
	                        			}
						}
					} catch(Exception ex){ System.out.println("Exception in ReceiveQueueHandler class to remove queue for network slow"+ex.getMessage());}
				}
				runner.yield();
				runner.sleep(10);
			}catch(Exception e){}
		}	
	}
}
