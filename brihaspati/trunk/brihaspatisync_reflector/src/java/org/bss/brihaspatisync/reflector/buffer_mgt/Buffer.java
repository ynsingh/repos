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

	private Vector userid_store; 
  	private LinkedList<byte[]> data;  
	/**
         * Create an empty Buffer
         */
	public Buffer(){
		userid_store= new Vector(11);
		data=new LinkedList<byte[]>();
	} 
    
       	/** 
         * Return the Buffer head 
         */ 
	
	protected synchronized Object get(int temp) throws QueueEmptyException { 
		if((size() > temp) && (!isEmpty())) 
	              	return userid_store.elementAt(temp); 
		else	
			return null;
       	} 
		
	protected synchronized byte[] getObject(int temp) throws QueueEmptyException {
		if((size() > temp) && (!isEmpty())) 
                        return data.get(temp); 
                else    
                        return null;
        }
        
	protected synchronized void put(Object x) {
                userid_store.add(x);
        }
    	
	protected synchronized void putObject(byte[] x) {
                data.addLast(x);
        }
	
	/**
	 * remove element from buffer 
	 */
	
	protected synchronized void removeRange(int fromIndex, int endIndex,String type) {
		if(userid_store.size() > endIndex){
			for(int j=fromIndex;j<endIndex;j++){
				userid_store.removeElementAt(0);
				data.remove(0);
			}
		}
   	}
	
	/**
         * Return <b>true</b> if the buffer is empty.
         */
        
	private boolean isEmpty() {
		if(userid_store.size() == 0)
			return true;
		else
			return false;
        }

        /**
         * Return the size of the buffer
         */
        protected synchronized int size() {
                return userid_store.size();
        }
}

class QueueEmptyException extends Exception { }
