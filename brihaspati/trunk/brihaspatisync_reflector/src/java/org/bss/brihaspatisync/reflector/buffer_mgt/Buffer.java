package org.bss.brihaspatisync.reflector.buffer_mgt;

/**
 * Buffer.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2012 ETRG,IIT Kanpur.
 */


import java.util.Vector; 
import java.io.File; 

/**
 * @author <a href="mailto:arvindjss17@gmail.com">Arvind Pal  </a> 
 * @author <a href="mailto:meera.knit@gmail.com">Meera Pal </a> 
 */

public class Buffer { 

	private Vector buffer; 
	private Vector data; 
  	  
	/**
         * Create an empty Buffer
         */
	public Buffer(){
		buffer= new Vector(11);
		data= new Vector(11);
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
		
	public synchronized Object getObject(int temp) throws QueueEmptyException {
                if(isEmpty())
                        throw new QueueEmptyException();
                Object obj = data.elementAt(temp);      
                return obj;
        }
        
	public synchronized void put(Object x) {
                buffer.add(x);
        }
    	
	public synchronized void putObject(Object x) {
                data.add(x);
        }
	 
	/**
	 * remove element from buffer 
	 */
	
	public synchronized void removeRange(int fromIndex, int endIndex,String type) {
		if(buffer.size() > endIndex){
			for(int j=fromIndex;j<endIndex;j++){
				buffer.removeElementAt(0);
				data.removeElementAt(0);
				if(type.startsWith("Audio_Post")){
					renameFileName(type);
				}
			}
		}
   	}
		
	private void renameFileName(String type){
		try {	
			for(int i=0;i<data.size();i++){
				int old_filePrefix=Integer.parseInt(getObject(i).toString());
				int new_filePrefix=old_filePrefix-1;
				data.setElementAt(Integer.toString(kk),i);
				{
					String str=type.replace("Audio_Post","");
                                        File oldfile=new File(str+"/"+Integer.toString(new_filePrefix)+".wav"); //0
					oldfile.delete();
                                        File newfile=new File(str+"/"+Integer.toString(old_filePrefix)+".wav");//1
					newfile.renameTo(oldfile);
				}
			}
		}catch(Exception e){System.out.println(e.getMessage());}
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

class QueueEmptyException extends Exception { }
