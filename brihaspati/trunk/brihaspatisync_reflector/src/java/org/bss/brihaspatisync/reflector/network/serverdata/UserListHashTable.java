package org.bss.brihaspatisync.reflector.network.serverdata;

/**
 * UserListHashTable.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2013 ETRG, IIT Kanpur.
 */


import java.util.Hashtable;

/**
 * @author <a href="mailto:arvindjss17@gmail.com">Arvind Pal  </a> 
 */


public class  UserListHashTable {
        
	private static Hashtable ht = new Hashtable();
	
	public UserListHashTable() { }	
	
	/** get VectorClass object to corresponding Course id for user list */
	public static void setCourseIdUserListVector(String lecture_id,VectorClass vc){
                ht.put(lecture_id,vc);
        }
	
        public static VectorClass getCourseIdUserListVector(String lecture_id){
                return (VectorClass)ht.get(lecture_id);
        }
		
	public static boolean getStatusCourseId(String lecture_id) {
                return (ht.containsKey(lecture_id)) ?  true : false ;
        }
	
	public static void removeCourseIdUserListVector(String lecture_id){
                ht.remove(lecture_id);
        }
}

