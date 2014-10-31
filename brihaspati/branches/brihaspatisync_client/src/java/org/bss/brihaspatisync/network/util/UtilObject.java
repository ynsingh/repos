package org.bss.brihaspatisync.network.util;

/**
 * UtilObject.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2007-2008
 */

import  java.util.Vector;
import  java.util.Hashtable;
import  java.util.LinkedList;

import org.bss.brihaspatisync.network.ReceiveQueueHandler;

/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a>
 * @author <a href="mailto:arvindjss17@gmail.com">Arvind Pal </a>
 */

public class UtilObject {

	private java.util.Hashtable ht_for_queue = null;
	private java.util.Hashtable ht_for_send_queue = null;
	
	private Vector type_vector=new Vector();	

	private static UtilObject nob=null;
	
	public static UtilObject getController(){
		if(nob==null)
			nob=new UtilObject();
		return nob;
	}
	
	/**
	 * This method are used to return send queue hash table .
	 */  
	private synchronized java.util.Hashtable get_send_queue_hashTable() {
		return ht_for_send_queue;
	}
	
	/**
 	 * This method are used to create rechive queue according to type .
 	 */
	public synchronized LinkedList getReceiveQueue(String type) {
                if(!(ht_for_queue.containsKey(type))){
			ht_for_queue.put(type,new LinkedList<byte[]>());
		}
		return (LinkedList)ht_for_queue.get(type);
        }

	/**
         * This method are used to remove rechive queue according to type .
         */
	
	public synchronized void removeReceiveQueue(String type) {
                if(ht_for_queue.containsKey(type)){
                        ht_for_queue.remove(type);
                }
        }
	
	/**
 	 * This method are used to create send queue according to type .
 	 */
        public synchronized LinkedList getSendQueue(String type) {
                if(!(ht_for_send_queue.containsKey(type))){
                        ht_for_send_queue.put(type,new LinkedList<byte[]>());
                }
                return (LinkedList)ht_for_send_queue.get(type);
        }	
	
	/**
         * This method are used to remove send queue according to type .
         */
        public synchronized void removeSendQueue(String type) {
                if(ht_for_send_queue.containsKey(type)){
                        ht_for_send_queue.remove(type);
                }
        }

	public UtilObject(){
		this.ht_for_queue = new java.util.Hashtable();	
		this.ht_for_send_queue = new java.util.Hashtable();	
		
	}
	public synchronized void addType(String type) {
                if(!(type_vector.contains(type)))
                        type_vector.add(type);
        }

        public synchronized void removeType(String type) {
                type_vector.remove(type);
                org.bss.brihaspatisync.network.singleport.NetworkController.remove_Ht_Key(type);
        }
	
	public synchronized Vector getTypeVector(){
		return type_vector;
	}	
}
