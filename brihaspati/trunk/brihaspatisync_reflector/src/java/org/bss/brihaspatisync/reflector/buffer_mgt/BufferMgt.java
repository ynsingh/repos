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

	private java.util.LinkedList<byte[]> au_data=new java.util.LinkedList<byte[]>();

	private PointerStoreAndUserRemove pointerStore_userRemove=null;

	public BufferMgt() {
		pointerStore_userRemove=new PointerStoreAndUserRemove();
		buffer=new Buffer();
	}

        /**          
 	 * create removeBufferAndSetPointer method to remove the packets from a specific queue.
         */                   

        private synchronized void removeBufferAndSetPointer() {
		try {
                       	java.util.Vector pointer=pointerStore_userRemove.getAllPointer();
			if(pointer.size()>0) {
	                        int p1=(Integer)pointer.get(0); int maxpointer=buffer.size();
				if(maxpointer>1000) {
        	                	pointerStore_userRemove.resetPointer(40+maxpointer-1000);
	        	               	buffer.removeRange(40+maxpointer-1000);
				} else if(p1>0) {
					pointerStore_userRemove.resetPointer(p1);
					buffer.removeRange(p1);
                             	}
			} else 
				buffer.removeRange(buffer.size());
            	} catch(Exception e) { System.out.println("Exception remove method to Increase Pointer in BufferMgt class "+e.getMessage()); }
    	}
	
	/**
	 * This method is used to get data from buffer 
	 * and increase pointer from pointer buffer .
	 */  
	public synchronized byte[] sendDataAndIncreasePointer(String user_id) {
		byte[] str=null;
		try {	
			int cur_position_pointer = pointerStore_userRemove.getPointerBy_UserId(user_id);
			int size=buffer.size();
			while( cur_position_pointer < size) {
				String get_userid=(buffer.getSourceUser_id(cur_position_pointer)).toString();
	               		if(!(get_userid.equals(user_id))) {
					str=buffer.getObject(cur_position_pointer);
					if(str != null) 
        	                		pointerStore_userRemove.setPointer(user_id,++cur_position_pointer);
	                        } else 
                	              	pointerStore_userRemove.setPointer(user_id,++cur_position_pointer);
				if(str !=null)
					break;
			}
			removeBufferAndSetPointer();	
		} catch(Exception e){ System.out.println("Exception in send Data Increase Pointer in BufferMgt class "+e.getMessage()); }
		return str;
	}

	public synchronized byte[] sendData_AudioIncreasePointer(String user_id) {	
		byte[] audiodata=null;
		try {
			au_data.clear();
			int cur_position_pointer = pointerStore_userRemove.getPointerBy_UserId(user_id);
                        int size=buffer.size();
                        while( cur_position_pointer < size) {
                                String get_userid=(buffer.getSourceUser_id(cur_position_pointer)).toString();
                                if(!(get_userid.equals(user_id))) {
                                        byte[] str=buffer.getObject(cur_position_pointer);
                                        if(str != null){
						au_data.addLast(str);
                                                pointerStore_userRemove.setPointer(user_id,++cur_position_pointer);
					}
                                } else
                                        pointerStore_userRemove.setPointer(user_id,++cur_position_pointer);
                                if(au_data.size()>50)
                                        break;
                        }
                        removeBufferAndSetPointer();
			int currentOffset = 0;
			size=au_data.size(); 
			if(size>0)
				audiodata=new byte[(74*size)];
                        for (int i=0;i<size;i++) {
                                byte[] currentArray=au_data.remove();
                                if(currentArray != null) {
                                        System.arraycopy(currentArray, 0,audiodata, currentOffset,currentArray.length);
                                        currentOffset += currentArray.length;
                                }
                        }
                } catch(Exception e){ System.out.println("Exception in send Audio Data Increase Pointer in BufferMgt class "+e.getMessage());}
                return audiodata;
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
				byte[] audiobytes=java.util.Arrays.copyOfRange(data,i,(i+74));
				putByte(audiobytes,user_id);
			}
                }catch(Exception e){ System.out.println("Exception in putByte method in BufferMgt class "+e.getMessage()); }
        }

	/**
	 * if user is loged out or disconnected then remove (login id / user id ) in pointer hash table 
	 */ 
	public void removeUseridKey(String login_name) {
		pointerStore_userRemove.removeUseridKey(login_name);		
		removeBufferAndSetPointer();
	}	
}
