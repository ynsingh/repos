package org.bss.brihaspatisync.reflector.network.video_server;

/**
 * VideoBufferImage.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2011.
 */


import java.util.Vector; 
import java.awt.image.BufferedImage;

/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a> 
 * @author <a href="mailto:arvindjss17@gmail.com">Arvind Pal  </a>
 * @author <a href="mailto:pradeepmca30@gmail.com"> Pradeep Kumar Pal</a> 
 */

public class VideoBufferImage { 

	private Vector buffer; 
	private static VideoBufferImage bufferimage=null; 
    
	/**
         * Create an empty Buffer
         */
	
	public VideoBufferImage(){
		buffer= new Vector();
	} 
	
	public static VideoBufferImage getController(){
                if(bufferimage==null)
                        bufferimage=new VideoBufferImage();
                return bufferimage;
        }

       	/** 
         * Return the Buffer head 
         */ 
       	
	public synchronized BufferedImage get(int temp) throws QueueEmptyException1 { 
           	if(isEmpty()) 
                	throw new QueueEmptyException1(); 
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

class QueueEmptyException1 extends Exception { 
}
