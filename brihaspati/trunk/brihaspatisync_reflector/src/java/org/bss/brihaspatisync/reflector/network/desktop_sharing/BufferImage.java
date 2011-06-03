package org.bss.brihaspatisync.reflector.network.desktop_sharing;

/**
 * BufferImage.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2011.
 */


import java.util.Vector; 
import java.awt.image.BufferedImage;

/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a> 
 * @author <a href="mailto:arvindjss17@gmail.com">Arvind Pal  </a> 
 */

public class BufferImage { 

	private Vector buffer; 
	private static BufferImage bufferimage=null; 
    
	/**
         * Create an empty Buffer
         */
	
	public BufferImage(){
		buffer= new Vector();
	} 
	
	/*
	public static BufferImage getController(){
                if(bufferimage==null)
                        bufferimage=new BufferImage();
                return bufferimage;
        }
    	*/

       	/** 
         * Return the Buffer head 
         */ 
       	
	public synchronized BufferedImage get(int temp) throws QueueEmptyException { 
           	if(isEmpty()) 
                	throw new QueueEmptyException(); 
               	 return (BufferedImage)buffer.elementAt(temp); 
       	} 
    	
  	/**
         * Insert an Object in the buffer
         */
	
        public synchronized void put(BufferedImage obj) {
        	buffer.addElement(obj);
        }
	
	public synchronized void remove() {
                if(buffer.size() > 0){
			buffer.removeElementAt(0);
                }
        }
	
	
	public synchronized void handleBuffer(){
		for(int k=0; k<25; k++){
                       	buffer.remove(k);
		}
	}
	

	/**
         * Return <b>true</b> if the buffer is empty.
         */
        
	
	private boolean isEmpty() {
                return buffer.size() == 0;
        }
	
		
        /**
         * Return the size of the buffer
         */
	public synchronized int bufferSize() {
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
