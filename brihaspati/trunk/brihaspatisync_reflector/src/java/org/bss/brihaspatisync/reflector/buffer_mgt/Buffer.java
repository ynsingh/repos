package org.bss.brihaspatisync.reflector.buffer_mgt;

/**
 * Buffer.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2012-13 ETRG,IIT Kanpur.
 */


import java.io.File; 
import java.util.Vector; 
import java.util.LinkedList;

/**
 * @author <a href="mailto:arvindjss17@gmail.com">Arvind Pal  </a> 
 * @author <a href="mailto:meera.knit@gmail.com">Meera Pal </a> 
 * @author <a href="mailto:ynsingh@iitk.ac.in">Y.N.Singh</a>
 */

public class Buffer { 

	private Vector<String> userid_store=null; 
        /* The userid_store contains the user_id of the source node which has generated the corresponding packet (byte[])
         * in the LinkedList<byte[]>.
         */

  	private LinkedList<byte[]> data;  
	/**
         * Create an empty Buffer
         */
	protected Buffer(){
		userid_store= new Vector<String> ();
		data=new LinkedList<byte[]>();
	} 
    
       	/** 
         * Return the Buffer head 
         */ 
	
	protected synchronized String getSourceUser_id(int temp) throws Exception { 
		if((size() > temp) && (!isEmpty())) 
	              	return userid_store.get(temp); 
		else	
			return null;
       	} 
		
	protected synchronized byte[] getObject(int temp) throws Exception {
		if((size() > temp) && (!isEmpty())) 
                        return data.get(temp); 
                else    
                        return null;
        }
        
	protected synchronized void putObjectAndUserId(String x, byte[] y) {
                userid_store.add(x);
                data.addLast(y);
        }
	
	/**
	 * remove element from buffer 
	 */
	
	protected synchronized void removeRange(int endIndex) {
		for(int i=-1;i<endIndex;i++) {
			userid_store.remove(0);
			data.remove(0);
			//System.out.println("remove data ");
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

