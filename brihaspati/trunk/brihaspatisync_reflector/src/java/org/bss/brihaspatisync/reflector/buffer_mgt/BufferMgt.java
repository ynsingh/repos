package org.bss.brihaspatisync.reflector.buffer_mgt;

/***
 * BufferMgt.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2011,2013 ETRG,IIT Kanpur.
 */

/**
 *@author <a href="mailto:arvindjss17@gmail.com">Arvind Pal </a>
 *@author <a href="mailto:meera.knit@gmail.com">Meera Pal </a>
 *@author <a href="mailto:shikhashuklaa@gmail.com">Shikha Shukla </a>
 *@date 19/06/2012
 */

public class  BufferMgt {

	private Buffer buffer=null;

	private CreateHashTable createhashtable=null;

	private java.util.LinkedList<byte[]> data=null;
	
	public BufferMgt() {
		createhashtable=new CreateHashTable();
		data=new java.util.LinkedList<byte[]>();
		buffer=new Buffer();
	}

        /**          
 	 * create removeBufferAndSetPointer method to remove the packets from a specific queue.
         */                   

        private synchronized void removeBufferAndSetPointer(){
		try {
                       	java.util.Vector pointer=createhashtable.getPointer();
                        int p1=(Integer)pointer.get(0);
                        int maxpointer=(Integer)pointer.get(((pointer.size())-1));
			if(p1>20) {
				p1=p1-1;
				if(((maxpointer-p1) >10) && (buffer.size()==100)) {
                               		createhashtable.resetPointer(10);
	                               	buffer.removeRange(10);
				} else {
					createhashtable.resetPointer(p1);
					buffer.removeRange(p1);
                             	}
                     	}
            	} catch(Exception e) {System.out.println("Exception in send Data Increase Pointer in BufferMgt class "+e.getMessage()); }
    	}
	
	/**
	 * This method is used to get data from buffer 
	 * and encrease pointer from ponter buffer .
	 */  
	public synchronized byte[] sendDataAndIncreasePointer(String user_id) {
		try {	
			user_id=user_id.trim();
			int curpointer = createhashtable.getValue(user_id);
			int size=buffer.size();
			if((curpointer < size) && (curpointer != -1)) {
				byte[] str=null;	
				while( curpointer < size) {
					String get_userid=(buffer.getSourceUser_id(curpointer)).toString();
					if(get_userid != null) {
	                                        if(!(get_userid.startsWith(user_id))) {
							str=buffer.getObject(curpointer);
							if(str != null) {
	                	                                curpointer++;
        	                	                        createhashtable.setPointer(user_id,curpointer);
							}
	                                        }else {
        	                                	curpointer++;
                	                        	createhashtable.setPointer(user_id,curpointer);
						}
					}
					if(str !=null)
						break;
				}
				removeBufferAndSetPointer();	
				return str;
			}  
		} catch(Exception e){ System.out.println("Exception in send Data Increase Pointer in BufferMgt class "+e.getMessage());}
		return null;
	}

	public byte[] sendData_AudioIncreasePointer(String user_id) {	
		byte[] bigArray=null;
		try {
			user_id=user_id.trim();data.clear();int curpointer = createhashtable.getValue(user_id);
                        int size=buffer.size();
			try {
                        if((curpointer < size) && (curpointer != -1)) {
                                while( curpointer < size) {
                                        String get_userid=(buffer.getSourceUser_id(curpointer)).toString();
                                        if(get_userid != null) {
                                                if(!(get_userid.startsWith(user_id))) {
                                                        byte[] str=buffer.getObject(curpointer);
                                                        if(str != null) {
								data.addLast(str);
                                                                curpointer++;
                                                                createhashtable.setPointer(user_id,curpointer);
                                                        }
                                                }else {
                                                	curpointer++;
                                               		createhashtable.setPointer(user_id,curpointer);
						}
                                        }
                                }
			}
			} catch(Exception ex){ System.out.println("send Audio Data Increase Pointer "+ex.getMessage()); }
			int currentOffset = 0;
                        for (int i=0;i<data.size();i++) {
                                byte[] currentArray=data.get(i);
                                if(currentArray != null) {
                                        if(bigArray==null)
                                                bigArray=new byte[(74*(data.size()))];
                                        System.arraycopy(currentArray, 0,bigArray, currentOffset,currentArray.length);
                                        currentOffset += currentArray.length;
                                }
                        }
                        removeBufferAndSetPointer();
                        
                } catch(Exception e){ System.out.println("Exception in send Audio Data Increase Pointer in BufferMgt class "+e.getMessage());}
                return bigArray;
	}
         /**
          * Create putByte method to insert the packets in appropriate queue after matching 
          * packet type and queue type.                
          */                  

	 public synchronized void putByte(byte[] data,String user_id){
		try {
                        buffer.putObjectAndUserId(user_id, data);
		}catch(Exception e){ System.out.println("Exception in putByte method in BufferMgt class "+e.getMessage()); }
	}
	
	public synchronized void putAudioBytes(byte[] data,String user_id){
                try {
			for(int i=0;i<data.length;i=i+74) {
				int k=i+74;
				byte[] audiobytes=java.util.Arrays.copyOfRange(data,i,k);
				buffer.putObjectAndUserId(user_id,audiobytes);
			}
                }catch(Exception e){ System.out.println("Exception in putByte method in BufferMgt class "+e.getMessage()); }
        }
}
