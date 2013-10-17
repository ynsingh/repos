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


public class  MyHashTable {
        
	private static Hashtable ht = new Hashtable();
	
	public MyHashTable() { }	
	
	public static boolean getStatusBufferMgtObject(String type_lecture_id) {
		return (ht.containsKey(type_lecture_id)) ?  true : false ;
	}	
	
	public static void setBufferMgtObject(String type_lecture_id,BufferMgt bm){
		ht.put(type_lecture_id,bm);
	}	
	
	public static BufferMgt getBufferMgtObject(String type_lecture_id){
                return (BufferMgt)ht.get(type_lecture_id);
        }
}

