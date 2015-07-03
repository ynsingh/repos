package org.bss.brihaspatisync.reflector.buffer_mgt;

/**
 * Buffer.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2012-13,2015 ETRG,IIT Kanpur.
 */


import java.io.File; 
import java.util.Vector; 
import java.util.LinkedList;

/**
 * @author <a href="mailto:arvindjss17@gmail.com">Arvind Pal  </a> 
 * @author <a href="mailto:meera.knit@gmail.com">Meera Pal </a> 
 * @author <a href="mailto:ynsingh@iitk.ac.in">Y.N.Singh</a>
 * @author <a href="mailto:pradeepmca30@iitk.ac.in">Pradeep Kumar Pal</a>
 */

public class Buffer { 

	/** The userid_store contains the user_id of the source node which has generated the
 	 *  corresponding packet (byte[]) in the LinkedList<byte[]>.
	 */
	private Vector<String> userid_store=null; 
	private LinkedList<byte[]> data;

	/**
         * Create an empty Buffer that consists of a vector and a linklist.Vector store the user name 
         * where as linklist store the corresponding byte array.
         */
	protected Buffer(){
		userid_store= new Vector<String> ();
		data=new LinkedList<byte[]>();
	} 
    
       	/** 
         * Return the Buffer head.This function retuns the user name store in vector by acceepting index value.
         */ 
	protected synchronized String getSourceUser_id(int temp) throws Exception { 
		if((size() > temp) && (!isEmpty())) 
	              	return userid_store.get(temp); 
		else	
			return null;
       	} 
		
	/**
  	* This funtion retuns the corresponting the byte array from the link list by accepting index as argument.
  	*/
 	protected synchronized byte[] getDataObject(int temp) throws Exception {
		if((size() > temp) && (!isEmpty())) 
                        return data.get(temp); 
                else    
                        return null;
        }

	/**
        * Store the user name in the vector and its corresponding byte arry at the end of linklist.
	*/
	protected synchronized void putDataObjectAndUserId(String x, byte[] y) {
                userid_store.add(x);
                data.addLast(y);
        }
	
	/**
	 * Remove element from buffer.Remove the element from the vector and link list by accepting endindex as argument.
	 */
	protected synchronized void removeRange(int endIndex) {
		for(int i=0;i<endIndex;i++) {
			userid_store.remove(0);
			data.remove(0);
			//System.out.println("Remove data from Buffer Class...  ");
		}
	}
	
	/**
         * Check the buffer value.if the buffer is empty Return <b>true</b>
         */
        private boolean isEmpty() {
		if(userid_store.size() == 0)
			return true;
		else
			return false;
        }

        /**
         * Return the size of the user name vector.
         */
        protected synchronized int size() {
                return userid_store.size();
        }
}

