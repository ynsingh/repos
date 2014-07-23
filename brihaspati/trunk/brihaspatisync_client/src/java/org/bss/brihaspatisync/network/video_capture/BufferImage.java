package org.bss.brihaspatisync.network.video_capture;

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
 * @author <a href="mailto:pradeepmca30@gmail.com">Pradeep Kumar Pal  </a>
 */

public class BufferImage { 

	private Vector buffer; 
	private static BufferImage bufferImage=null; 

	public static BufferImage getController(){
                if(bufferImage==null)
                        bufferImage=new BufferImage();
                return bufferImage;
        }
	    
	/**
         * Create an empty Buffer
         */
	
	public BufferImage(){
		buffer= new Vector();
	} 
	
       	/** 
         * Return the Buffer head 
         */ 
       	
	public synchronized BufferedImage get(int temp) throws Exception { 
           	if(buffer.size() >0 ) 
               	 	return (BufferedImage)buffer.elementAt(temp); 
		else
			return null;
       	} 
    	
  	/**
         * Insert an Object in the buffer
         */
	
        public synchronized void put(BufferedImage obj) {
        	buffer.addElement(obj);
        }

	/**
 	 * Remove an Obect in the buffer
 	 */
	public synchronized void remove() {
                if(buffer.size() > 0){
			buffer.removeElementAt(0);
                }
        }
	
	/**
	 * Remove an Obect in the buffer if buffer is full .
	 */
	public synchronized void handleBuffer() {
		if(bufferSize()>10) {
			for(int k=0; k<5; k++){
                	       	buffer.remove(0);
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
}

