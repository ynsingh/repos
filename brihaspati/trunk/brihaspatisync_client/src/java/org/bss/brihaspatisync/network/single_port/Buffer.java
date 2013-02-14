package org.bss.brihaspatisync.network.singleport;

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
 */

public class Buffer { 

	private Vector typestore; 
  	private LinkedList<byte[]> data;  
	
	private static Buffer buffer=null;
	
	/**
         * Create an empty Buffer
         */
	public Buffer(){
		typestore= new Vector(11);
		data=new LinkedList<byte[]>();
	} 
   
	/**
	 * Controller for the class.
	 **/
	
        public static synchronized Buffer getController(){
                if(buffer==null)
                        buffer=new Buffer();
                return buffer;
        }
	 
       	/** 
         * Return the Buffer head 
         */ 
	
	public Object get(int temp) { 
           	try {
			if(size()> 0 ) {
               			Object obj = typestore.elementAt(temp); 
	               		return obj; 
			}
		} catch(Exception e){System.out.println("Error in Object get "+e.getMessage());}	
        	return null;
       	} 
		
	public byte[] getObject(int temp) throws Exception {
		try {
	                if(size() > 0) {
                		byte[] obj = data.get(temp);      
	                	return obj;
			}
		}catch(Exception e){System.out.println("Error in getObject "+e.getMessage());}
		return null;
        }
        
	public void putType(Object x) {
		if(size()<11)		
                	typestore.add(x);
        }
    	
	public void putData(byte[] x) {
		if(size()<11)
                	data.addLast(x);
        }
	
	/**
	 * remove element from buffer 
	 */
	
	public void removeRange(int fromIndex, int endIndex,String type) {
		if(typestore.size() > endIndex){
			for(int j=fromIndex;j<endIndex;j++){
				typestore.removeElementAt(0);
				data.remove(0);
			}
		}
   	}

	public void remove() {
		if(size()>0){
	        	typestore.removeElementAt(0);
        	        data.remove(0);
		}
        }
	
        /**
         * Return the size of the buffer
         */

        public int size() {
                return typestore.size();
        }
	
	/**
         * Notify Queue
         */
        
	public void Notify(){
        	typestore.notify();
	}
}
