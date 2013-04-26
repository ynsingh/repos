package org.bss.brihaspatisync.network.util;

/**
 * UtilObject.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2007-2008
 */

import  java.util.Hashtable;
import  java.util.LinkedList;

import org.bss.brihaspatisync.network.util.Queue;
import org.bss.brihaspatisync.network.ReceiveQueueHandler;

/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a>
 * @author <a href="mailto:arvindjss17@gmail.com">Arvind Pal </a>
 */

public class UtilObject {

	private Queue recQueue=null;	
	private Queue sendQueue=null;
        private Queue frameQueue=null;
	private java.util.Hashtable ht_for_queue = null;
	private java.util.Hashtable ht_for_send_queue = null;
	
	private static UtilObject nob=null;
	
	public static UtilObject getController(){
		if(nob==null)
			nob=new UtilObject();
		return nob;
	}
	
	/**
	 * This method are used to return send queue hash table .
	 */  
	public java.util.Hashtable get_send_queue_hashTable() {
		return ht_for_send_queue;
	}
	
	/**
 	 * This method are used to create rechive queue according to type .
 	 */
	public LinkedList getQueue(String type) {
                if(!(ht_for_queue.containsKey(type))){
			LinkedList<byte[]> data=new LinkedList<byte[]>();
			ht_for_queue.put(type,data);
		}
		return (LinkedList)ht_for_queue.get(type);
        }

	/**
 	 * This method are used to create send queue according to type .
 	 */
        public LinkedList getSendQueue(String type) {
                if(!(ht_for_send_queue.containsKey(type))){
                        LinkedList<byte[]> data=new LinkedList<byte[]>();
                        ht_for_send_queue.put(type,data);
                }
                return (LinkedList)ht_for_send_queue.get(type);
        }	
	
	public UtilObject(){
		this.recQueue  =new Queue();
		this.sendQueue =new Queue();
                this.frameQueue=new Queue();  
		this.ht_for_queue = new java.util.Hashtable();	
		this.ht_for_send_queue = new java.util.Hashtable();	
		
	}
        public void setframeQueue(String value){
               frameQueue.putString(value);
        }
	public void setRecQueue(String value){
                recQueue.putString(value);
        }
	public int getRecQueueSize(){
                return recQueue.size();
        }
	public String getRecQueue(){
		String value="";
		try {
                 	value =recQueue.getString().toString();
			recQueue.remove();
		}catch (Exception e){}
		return value;
        }
	
	public void setSendQueue(String value){
                sendQueue.putString(value);
        }
	
        public String getSendQueue(){
		String value="";
                try {
			value= sendQueue.getString().toString();
			sendQueue.remove();
		}catch (Exception e){}
		return value;
        }
	
	public int getSendQueueSize(){
                return sendQueue.size();
        }
	
}
