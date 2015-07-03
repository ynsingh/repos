package org.bss.brihaspatisync.reflector.buffer_mgt;

/**
 * StoreBufferMgnObject.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2009.2013,2015 ETRG, IIT Kanpur.
 */


import java.util.Hashtable;

/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a> 
 * @author <a href="mailto:arvindjss17@gmail.com">Arvind Pal  </a>
 * @author <a href="mailto:pradeepmca30@gmail.com">Prdeep Kumar Pal</a>
 */


public class  StoreBufferMgnObject {
	private static Hashtable storebuffermgnbjectHashtable = new Hashtable();
	
	public StoreBufferMgnObject() { }	
	
	/**
	 *  This method is used to check bufferMgt Object(type+lecture_id----Key,Value BufferMgt object)
	 *  in hash table according to media streeming.if media streem object is availabe or not 
	 */  	
	public static boolean getStatusBufferMgtObject(String type_lecture_id) {
		return (storebuffermgnbjectHashtable.containsKey(type_lecture_id)) ?  true : false ;
	}
	
	/**
	 * This method is used to add bufferMgt Object in hash table according to media streeming  
	 */
	public static void setBufferMgtObject(String type_lecture_id,BufferMgt bm) {
		storebuffermgnbjectHashtable.put(type_lecture_id,bm);
	}	

	/**
	 * This method is used to get bufferMgt Object in hash table according to media streeming 	
	 */
	public static BufferMgt getBufferMgtObject(String type_lecture_id) {
                return (BufferMgt)storebuffermgnbjectHashtable.get(type_lecture_id);
        }

	/**
	 * If session is time out then this method is remove bufferMgt Object in hash table according to media streeming
	 */  		
	public static void removeAllBufferMgtObject(String lecture_id) {
		try {
			if(storebuffermgnbjectHashtable.size()>0) {
				java.util.ArrayList myArrayList=new java.util.ArrayList(storebuffermgnbjectHashtable.entrySet());
				java.util.Iterator itr=myArrayList.iterator();
                	        while(itr.hasNext()) {
                        		java.util.Map.Entry e=(java.util.Map.Entry)itr.next();
		                        String streemAndlecture_id = (String)e.getKey();
					if(streemAndlecture_id.endsWith(lecture_id)){
						storebuffermgnbjectHashtable.remove(streemAndlecture_id);
					}
				}
			}
		}catch(Exception e){ System.out.println("Exception in removeAllBufferMgtObject method in StoreBufferMgnObject class !!!! ");}
        }
		
	/**
 	* This method removes the entry from hashtable corresponding to this type and lecture_id.
 	*/
	
	public static void removeBufferMgtObject(String type_lecture_id)  {
                try {
                        if(storebuffermgnbjectHashtable.size()>0) {
                        	storebuffermgnbjectHashtable.remove(type_lecture_id);
                        }
                }catch(Exception e){ System.out.println("Exception in removeAllBufferMgtObject method in StoreBufferMgnObject class !!!!! ");}
        }
	
}

