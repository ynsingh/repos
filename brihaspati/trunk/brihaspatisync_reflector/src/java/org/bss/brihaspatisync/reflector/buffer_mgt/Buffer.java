package org.bss.brihaspatisync.reflector.buffer_mgt;

/**
 * Buffer.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2012 ETRG,IIT Kanpur.
 */


import java.io.File; 
import java.util.Vector; 
import  java.util.LinkedList;

/**
 * @author <a href="mailto:arvindjss17@gmail.com">Arvind Pal  </a> 
 * @author <a href="mailto:meera.knit@gmail.com">Meera Pal </a> 
 */

public class Buffer { 

	private Vector ipstore; 
  	private LinkedList<byte[]> data;  
	/**
         * Create an empty Buffer
         */
	public Buffer(){
		ipstore= new Vector(11);
		data=new LinkedList<byte[]>();
	} 
    
       	/** 
         * Return the Buffer head 
         */ 
	
	public synchronized Object get(int temp) throws QueueEmptyException { 
           	if(isEmpty()) 
                	throw new QueueEmptyException(); 
               	Object obj = ipstore.elementAt(temp); 
               	return obj; 
       	} 
		
	public synchronized byte[] getObject(int temp) throws QueueEmptyException {
                if(isEmpty())
                      return null;
                byte[] obj = data.get(temp);      
                return obj;
        }
        
	public synchronized void put(Object x) {
                ipstore.add(x);
        }
    	
	public synchronized void putObject(byte[] x) {
                data.addLast(x);
        }
	
	/**
	 * remove element from buffer 
	 */
	
	public synchronized void removeRange(int fromIndex, int endIndex,String type) {
		if(ipstore.size() > endIndex){
			for(int j=fromIndex;j<endIndex;j++){
				ipstore.removeElementAt(0);
				data.remove(0);
			}
		}
   	}
	
	/**
         * Return <b>true</b> if the buffer is empty.
         */
        
	public boolean isEmpty() {
                return ipstore.size() == 0;
        }

        /**
         * Return the size of the buffer
         */

        public int size() {
                return ipstore.size();
        }
	
	/**
         * Notify Queue
         */
        
	public synchronized void Notify(){
        	ipstore.notify();
	}
}

class QueueEmptyException extends Exception { }
