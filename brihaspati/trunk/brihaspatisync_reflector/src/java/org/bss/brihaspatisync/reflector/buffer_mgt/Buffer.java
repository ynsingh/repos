package org.bss.brihaspatisync.reflector.buffer_mgt;

/**
 * Buffer.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2007-2008 ETRG,IIT Kanpur.
 */


import java.util.Vector; 

/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a> 
 * @author <a href="mailto:arvindjss17@gmail.com">Arvind Pal  </a> 
 */

public class Buffer { 

	private Vector buffer; 
	private static Buffer que=null; 
    
	/**
         * Create an empty Buffer
         */
	public Buffer(){
		buffer= new Vector();
	} 
    
       	/** 
         * Return the Buffer head 
         */ 
       	
	public synchronized Object get(int temp) throws QueueEmptyException { 
           	if(isEmpty()) 
                	throw new QueueEmptyException(); 
               	Object obj = buffer.elementAt(temp); 
               	return obj; 
           	
       	} 
    	
      	/** 
         * Return the buffer head, as a String 
         */ 
		
      	public String getString(int temp) throws QueueEmptyException { 
          	return (String)get(temp); 
      	} 
   
  	/**
         * Insert an Object in the buffer
         */
        public synchronized void put(Object obj) {
        	buffer.addElement(obj);
        }
	
      	/** 
         * Insert a byte in the buffer 
         */ 
      	public void putByte(byte x) { 
        	put(new Byte(x)); 
      	} 
	
      	/** 
       	 * Insert a char in the buffer
       	 */ 
      
	public void putString(String x) { 
		put(new String(x));
      	} 
   
	/**
         * Remove an Object from buffer
         */
	
	public synchronized void remove() {
                if(buffer.size() > 0){
			buffer.removeElementAt(0);
                }
        }
	
	/**
	 * remove element from buffer 
	 */

	public void removeRange(int fromIndex, int endIndex) {
		if(buffer.size() > 0){
			for(int j=fromIndex;j<endIndex;j++){
				buffer.removeElementAt(0);
			}
		}
   	}
	

	/**
         * Return <b>true</b> if the buffer is empty.
         */
        
	public boolean isEmpty() {
                return buffer.size() == 0;
        }

        /**
         * Return the size of the buffer
         */

        public int size() {
                return buffer.size();
        }
	
	/**
         * Notify Queue
         */
        
	public synchronized void Notify(){
        	buffer.notify();
	}
	
}

class QueueEmptyException extends Exception { 
}
