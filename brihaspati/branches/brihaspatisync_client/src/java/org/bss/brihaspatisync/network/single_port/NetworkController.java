package org.bss.brihaspatisync.network.singleport;

/**
 * NetworkController.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2013, ETRG, IIT Kanpur.
 **/

import java.util.Vector;
import java.util.Hashtable;

/**
 * @author <a href="mailto:arvindjss17@gmail.com">Arvind Pal </a> 
 **/

public class NetworkController {
	
	private static Hashtable<String,Vector> ht_for_queue = new Hashtable<String,Vector>();

	protected static void Hashtable(String type,long store_diff_time) { 
		try {
			if(!(ht_for_queue.containsKey(type))) 
				ht_for_queue.put(type,new Vector(4));
			Vector v=(Vector)ht_for_queue.get(type);
			if(v.size()==4)
				v.remove(0);
			v.add(String.valueOf(store_diff_time));
		} catch(Exception e) { System.out.println("Exception in NetworkController class  put hash table value !! "); }
	}
	
	public static void remove_Ht_Key(String type) {
		if(ht_for_queue.containsKey(type))
			ht_for_queue.remove(type);	
	}
	
	public static Hashtable getHashtable(){
		return ht_for_queue;
	}
}
