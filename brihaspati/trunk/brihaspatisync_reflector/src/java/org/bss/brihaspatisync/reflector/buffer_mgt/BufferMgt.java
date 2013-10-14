package org.bss.brihaspatisync.reflector.buffer_mgt;

/***
 * BufferMgt.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2011,2013 ETRG,IIT Kanpur.
 */
import java.util.Vector;
import java.util.Enumeration;
import java.io.*;	
import java.util.Hashtable;
import javax.sound.sampled.*;

/**
 *@author <a href="mailto:arvindjss17@gmail.com">Arvind Pal </a>
 *@author <a href="mailto:meera.knit@gmail.com">Meera Pal </a>
 *@author <a href="mailto:shikhashuklaa@gmail.com">Shikha Shukla </a>
 *@date 19/06/2012
 */

public class  BufferMgt {
	
	private CreateHashTable createhashtable=new CreateHashTable();

	public BufferMgt() {}

        /**          
 	 * create removeBufferAndSetPointer method to remove the packets from a specific queue.
         */                   

        private void removeBufferAndSetPointer(String type){
		try {
        		Buffer buffer=createhashtable.set_getBuffer(type);
                       	int psize=0;                      
                       	Vector pointer=createhashtable.getPointer();
                        psize=pointer.size();
                        int maxpointer=(Integer)pointer.get(0);
                        int p1=(Integer)pointer.get((psize-1));
                        if(p1>0) {
                        	p1=p1-1;
				if(((maxpointer-p1) >10)|| (buffer.size()>10)) {
                                	createhashtable.resetPointer(5,type);
                                      	buffer.removeRange(0,5);
                                } else {
                                	createhashtable.resetPointer(p1,type);
					buffer.removeRange(0,p1);
                             	}
                     	}
            	}catch(Exception e){System.out.println("Exception in send Data Increase Pointer in BufferMgt class "+e.getMessage()); }
    	}
	
	/**
	 * This method is used to get data from buffer 
	 * and encrease pointer from ponter buffer .
	 */  
	
	private byte[] sendData_IncreasePointer(String user_id,String type) throws Exception {
		try {	
             		int curpointer = createhashtable.getValue(user_id,type);
			Buffer buffer=createhashtable.set_getBuffer(type);
			int size=buffer.size();
			if(curpointer < size){
				byte[] str=null;	
				while( curpointer < size) {
					String get_userid=(buffer.getSourceUser_id(curpointer)).toString();
                                        user_id=user_id.trim();
					if(get_userid != null) {
	                                        if(!(get_userid.startsWith(user_id))) {
							str=buffer.getObject(curpointer);
							if(str != null) {
	                	                                curpointer++;
        	                	                        setPointer(user_id,curpointer,type);
							}
                                                	break;
	                                        }
        	                                curpointer++;
                	                        setPointer(user_id,curpointer,type);
					}
				}
				removeBufferAndSetPointer(type);
				return str;
			}  
		} catch(Exception e){ System.out.println("Exception in send Data Increase Pointer in BufferMgt class "+e.getMessage());}
		return null;
	 }


         /**
          * Create putByte method to insert the packets in appropriate queue after matching 
          * packet type and queue type.                
          */                  

	 public  synchronized void putByte(byte[] data,String user_id,String type){
		try {
			Buffer buffer=createhashtable.set_getBuffer(type);
                        buffer.put(user_id, data);
		}catch(Exception e){ System.out.println("Exception in putByte method in BufferMgt class "+e.getMessage()); }
	}

 	public byte[] sendData(String user_id,String type) {
                try {
			if(!user_id.equals("")) {
				return sendData_IncreasePointer(user_id,type);
			}
               	}catch(Exception s){ System.out.println("Exception in sendData method in BufferMgt class "+s.getMessage());}
		return null;
	}
	
	private synchronized void setPointer(String setip , int pointer,String type) {
		try {	
			createhashtable.setPointer(setip,pointer,type);
		}catch(Exception e){}
      	}  
}
