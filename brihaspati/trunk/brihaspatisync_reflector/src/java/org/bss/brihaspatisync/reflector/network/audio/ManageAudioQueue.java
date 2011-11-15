package org.bss.brihaspatisync.reflector.network.audio;

/**
 *  ManageAudioQueue.java
 *  
 *  See LICENCE file for usage and redistribution terms
 *  Copyright (c) 2011 ETRG, IIT Kanpur
 */

import java.util.Vector;
import java.util.Hashtable;
import java.util.Enumeration;

public class ManageAudioQueue implements Runnable{

        private static ManageAudioQueue maq=null;
        private Hashtable hashtable = new Hashtable();
        private Thread mgr_thread=null;


        public static ManageAudioQueue getController(){
                if(maq==null)
                        maq=new ManageAudioQueue();
                return maq;
        }

        public void startThread(){
                try {
                        if (mgr_thread == null) {
                                mgr_thread = new Thread(this);
                                mgr_thread.start();
                        }
                        System.out.println("Start audio queue manager thread ");
                }catch(Exception e){
                        System.out.println("Error in start audio queue manager thread  : ");
                }
        }

        public void stopThread(){
                if(mgr_thread != null){
                        mgr_thread.stop();
                        mgr_thread=null;
                }
                System.out.println("Stop audio queue manager thread ");

        }

        public void run(){
                while(!(mgr_thread.isInterrupted())){
                        try{
				if(hashtable.size() > 0){
                                	if(AudioQueue.getController().size() > 0){
						Enumeration e=hashtable.keys();
						System.out.println("Keys in hashtable : "+e);
						
						while(e.hasMoreElements()){	
							Vector obj= (Vector) hashtable.get(e.nextElement());
							System.out.println("Get vector of key : "+e.nextElement()+ "and size of obj vector is "+obj.size());
	
							if(obj.size() < 3){
								obj.addElement(AudioQueue.getController().peek());
								System.out.println("Add value from Audio Queue to obj vector "+AudioQueue.getController().peek());
							}else{
								obj.removeElementAt(0);
							}
						
						} 					
						AudioQueue.getController().removeElement();
						Thread.sleep(5000);
						Thread.yield();
					}
					//else{
					//	System.out.println("Audio queue is empty");
					//}
				}
			}catch(Exception e){System.out.println("Erron on run() of mgr_thread " +e.getMessage());}
		}
	}

	public void createRequestQueue(String ipAdd){
		if(!(hashtable.contains("ipAdd"))){
			hashtable.put(ipAdd, new Vector());
			System.out.println("IP Address "+ipAdd+" Added to hashtable");
		}else{
			return;
		}
		System.out.println("Size of hashtable : "+hashtable.size());
	}

	public Hashtable getHashtable(){
		return hashtable;
	}
}
