package org.bss.brihaspatisync.network.util;

/**
 * Queue.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2007-2008 ETRG,IIT Kanpur.
 */

import java.util.Vector; 

/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a>
 */
     
public class Queue { 

	private Vector queue; 
	private static Queue que=null; 
    
	/**
         * Create an empty queue
         */
	public Queue(){
		queue= new Vector();
	} 
    
       	/** 
         * Return the queue head 
         */ 
       	public synchronized Object get() throws QueueEmptyException { 
           		if(isEmpty()) 
                   		throw new QueueEmptyException(); 
               		Object obj1 = queue.elementAt(0); 
               		Object obj = obj1; 
               		return obj; 
           	
       	} 
    
        /** 
         * Return the queue head, as a byte 
         */ 
       	public byte getByte() throws QueueEmptyException { 
           	return ((Byte)get()).byteValue(); 
       	} 
    
      	/** 
         * Return the queue head, as a String 
         */ 
      	public String getString() throws QueueEmptyException { 
          	return (String)get(); 
      	} 
   
  	/**
         * Insert an Object in the queue
         */
        public synchronized void put(Object obj) {
        	queue.addElement(obj);
                //    Notify();               
        }
	
      	/** 
         * Insert a byte in the queue 
         */ 
      	public void putByte(byte x) { 
        	put(new Byte(x)); 
      	} 
	
	/**
         * Insert a byte in the queue
         */
        public void putByte(byte [] x) {
		for(int i=0;i<x.length;i++){
                	put(new Byte(x[i]));
		}
        }
 
      	/** 
       	 * Insert a char in the queue 
       	 */ 
      	public void putString(String x) { 
		put(new String(x));
      	} 
   
	/**
         * Remove an Object from Queue
         */
	public synchronized void remove() {
                if(queue.size() > 0){
			queue.removeElementAt(0);
                }

        }

	/**
         * Remove All Object from Queue
         */
        public synchronized void removeAll() {
                if(queue.size() > 0) 
                	queue.removeAllElements();
                
        }
	
	/**
         * Return <b>true</b> if the queue is empty.
         */
        public boolean isEmpty() {
                return queue.size() == 0;
        }

        /**
         * Return the size of the queue
         */
        public int size() {
                return queue.size();
        }
	
	/**
         * Reset Queue as Empty Queue
         */
        public synchronized void Reset() {
                synchronized(queue) {
                        queue.removeAllElements();
                }
        }

	/**
         * Notify Queue
         */
        public synchronized void Notify(){
                queue.notify();
                
        }
}

class QueueEmptyException extends Exception { 
}
