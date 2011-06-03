package org.bss.brihaspatisync.network;

/**
 * ReceiveQueueHandler.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2007-2008 ETRG, IIT Kanpur.
 */

import java.io.IOException;
import java.util.StringTokenizer;

import org.bss.brihaspatisync.network.util.Queue;
import org.bss.brihaspatisync.network.util.UtilObject;
import org.bss.brihaspatisync.util.Recorder;
import org.bss.brihaspatisync.tools.chat.ChatPanel;
import org.bss.brihaspatisync.tools.whiteboard.WhiteBoardDraw;

/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a>
 * @author <a href="mailto:arvindjass17@gmail.com">Arvind Pal </a>	
 * @author <a href="mailto:pratibhaayadav@gmail.com">Pratibha </a> Modified ShowMsg() for received chat signalling	
 */


public class ReceiveQueueHandler implements Runnable{

	private int tempInt=0;
	
        private int tempInt1=0;	

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
			System.out.println("ReceiveQueueHandler is Start");
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
			System.out.println("ReceiveQueueHandler is Stop");
             	}
        }

	/**
         * Get Entry from Receive Queue and send it to appropriate tool for performing Action on GUI. 
         */
	public void run(){
		while(rec_Flag){
			try{
				synchronized(utilobject){
					while(utilobject.getRecQueueSize() != 0){
						String datastr=utilobject.getRecQueue();
						StringTokenizer st=new StringTokenizer(datastr,"$");
						while(st.hasMoreTokens()){
							String type=st.nextToken();
							if(!type.equals("ppt")){
								//file write in txt file
								try {
									Recorder.getController().Record(type);
								}catch(Exception e){}
							}
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
				}
				runner.yield();
				runner.sleep(10);
			}catch(Exception e){}
		}	
	}
}
