package org.bss.brihaspatisync.network.video_capture;

/**
 * StudentBufferImage.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2011.
 */


import java.util.Vector; 
import java.awt.image.BufferedImage;

/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a> 
 * @author <a href="mailto:arvindjss17@gmail.com">Arvind Pal  </a> 
 * @author <a href="mailto:pradeepmca30@gmail.com">Pradeep Kumar Pal  </a>
 */

public class StudentBufferImage { 

	private Vector buffer; 
	private static StudentBufferImage bufferImage=null; 

	public static StudentBufferImage getController(){
                if(bufferImage==null)
                        bufferImage=new StudentBufferImage();
                return bufferImage;
        }
	    
	/**
         * Create an empty Buffer
         */
	
	public StudentBufferImage(){
		buffer= new Vector();
	} 
	
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
		if(bufferSize()>25) {
			for(int k=0; k<10; k++){
				if(k/2==0)
                	       		buffer.remove(k);
			}
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

class QueueEmptyException12 extends Exception { 
}
