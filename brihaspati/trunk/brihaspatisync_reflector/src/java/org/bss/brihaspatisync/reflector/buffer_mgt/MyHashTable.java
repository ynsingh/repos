package org.bss.brihaspatisync.reflector.buffer_mgt;

/**
 * MyHashTable.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2009 ETRG, IIT Kanpur.
 */


import java.util.Hashtable;

import org.bss.brihaspatisync.reflector.network.serverdata.VectorClass;

/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a> 
 * @author <a href="mailto:arvindjss17@gmail.com">Arvind Pal  </a> 
 */


public class  MyHashTable {
        
	private Hashtable ht = new Hashtable();
	
	public MyHashTable(){ }	
	
	public boolean getStatus(String req) {
		return (ht.containsKey(req)) ?  true : false ;
	}	
	
	public void setValues(String st,BufferMgt bm){
		ht.put(st,bm);
	}	
	
	public BufferMgt getValues(String st){
                return (BufferMgt)ht.get(st);
        }
	
	/** get VectorClass object to corresponding Course id for user list */
	public void setCourseIdUserListVector(String st,VectorClass vc){
                ht.put(st,vc);
        }
	
        public VectorClass getCourseIdUserListVector(String st){
                return (VectorClass)ht.get(st);
        }
		
	public boolean getStatusCourseId(String req) {
                return (ht.containsKey(req)) ?  true : false ;
        }
	
	public void removeCourseIdUserListVector(String st){
                ht.remove(st);
        }
}

