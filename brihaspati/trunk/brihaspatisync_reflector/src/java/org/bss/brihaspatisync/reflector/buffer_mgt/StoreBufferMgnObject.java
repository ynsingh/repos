package org.bss.brihaspatisync.reflector.buffer_mgt;

/**
 * MyHashTable.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2009.2013 ETRG, IIT Kanpur.
 */


import java.util.Hashtable;

/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a> 
 * @author <a href="mailto:arvindjss17@gmail.com">Arvind Pal  </a> 
 */


public class  StoreBufferMgnObject {
        
	private static Hashtable ht = new Hashtable();
	
	public StoreBufferMgnObject() { }	
	
	/**
	 *  This method is used to check bufferMgt Object in hash table according to media streeming
	 *  if media streem object is availabe or not 
	 */  	
	public static boolean getStatusBufferMgtObject(String type_lecture_id) {
		return (ht.containsKey(type_lecture_id)) ?  true : false ;
	}
	
	/**
	 * This method is used to add bufferMgt Object in hash table according to media streeming  
	 */
	public static void setBufferMgtObject(String type_lecture_id,BufferMgt bm) {
		ht.put(type_lecture_id,bm);
	}	

	/**
	 * This method is used to get bufferMgt Object in hash table according to media streeming 	
	 */
	public static BufferMgt getBufferMgtObject(String type_lecture_id) {
                return (BufferMgt)ht.get(type_lecture_id);
        }

	/**
	 * This method is used to remove bufferMgt Object in hash table according to media streeming
	 * if session time out .
	 */  		
	public static void removeAllBufferMgtObject(String lecture_id) {
		try {
			if(ht.size()>0) {
				java.util.ArrayList myArrayList=new java.util.ArrayList(ht.entrySet());
				java.util.Iterator itr=myArrayList.iterator();
                	        while(itr.hasNext()) {
                        		java.util.Map.Entry e=(java.util.Map.Entry)itr.next();
		                        String streemAndlecture_id = (String)e.getKey();
					if(streemAndlecture_id.endsWith(lecture_id)){
						ht.remove(streemAndlecture_id);
					}
				}
			}
		}catch(Exception e){ System.out.println("Exception in MyHashTable class !! ");}
        }
	
	
	public static void removeBufferMgtObject(String type_lecture_id)  {
                try {
                        if(ht.size()>0) {
                        	ht.remove(type_lecture_id);
                        }
                }catch(Exception e){ System.out.println("Exception in MyHashTable class !! ");}
        }
}

