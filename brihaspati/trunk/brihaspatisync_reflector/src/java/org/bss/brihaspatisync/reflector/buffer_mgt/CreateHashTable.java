package org.bss.brihaspatisync.reflector.buffer_mgt;

/**
 * CreateHashTable.java
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2012.2013 ETRG,IIT Kanpur.
 */
 
import java.util.Map;
import java.util.Vector;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Collections;

/**
 * @author <a href="mailto:arvindjss17@gmail.com">Arvind Pal  </a> 
 * @author <a href="mailto:meera.knit@gmail.com">Meera Pal </a>
 */

 
public class  CreateHashTable {
	
	private Vector vector=new Vector();
		
	private Hashtable<String, Integer> pointer_hashtable = null;
        /* For each media type defined by String, a new hashtable is added. This
         * pointed hashable, has user_name and head location upto which data has been
         * received by the user from reflector.
         */ 

	public CreateHashTable() { 
		pointer_hashtable = new Hashtable<String, Integer> ();
	}	
                          
	/**
        * resetPointer method is used to reset the values stored in hashtable after packets have
        * been deleted from queue.
        */ 
        public void resetPointer(Integer decreasepointer){
                try{
			ArrayList myArrayList=new ArrayList(pointer_hashtable.entrySet());
                        Iterator itr=myArrayList.iterator();
                        while(itr.hasNext()){
                                Map.Entry e=(Map.Entry)itr.next();
                                String key = (String)e.getKey();
                                Integer value=pointer_hashtable.get(key);
				value=value-decreasepointer;
                                if(value >-1 ) 
                                        pointer_hashtable.put(key,value);
					
                        }
                }catch(Exception e){ System.out.println("Exception in resetPointer Method in CreateHashTable class "+e.getMessage());}
        } 
  
        /**
         * setPointer method is used to set the login_name of incomming packet as key  and how
         * many times packets from this login_name is come is stored as value in hashtable,after
         * this, store the value of hashtable in a vector. 
         *
         */ 
	     	
        public void setPointer(String login_name,Integer pointer){
                try {
                        pointer_hashtable.put(login_name,pointer);
		}catch(Exception e){System.out.println("Exception in setPointer Method in CreateHashTable class ");}
        }
  
	/**
	 * This method are used to set all pointer in increesing order 
	 */ 
	protected synchronized Vector getPointer() {
                try {
                        vector.clear();	
			ArrayList myArrayList=new ArrayList(pointer_hashtable.entrySet());
                        Iterator itr=myArrayList.iterator();
                        while(itr.hasNext()){
				Map.Entry e=(Map.Entry)itr.next();
                                String key = (String)e.getKey();
                                Integer value=pointer_hashtable.get(key);
				vector.add(value);
                        }
			Collections.sort(vector);
                }catch(Exception e){ System.out.println("Exception in resetPointer Method in CreateHashTable class ");}
                return vector;
        }
 
        /**
         * To get the pointer value of given login name.
         */
	
	public int getValue(String login_name) {
                try {
			if(pointer_hashtable.containsKey(login_name)) {
                        	Integer ik=pointer_hashtable.get(login_name);// ((Integer)(pointer_hashtable.get(login_name))).intValue();
             			return ik;
			}
			return 0;
               	} catch(Exception e){System.out.println("Exception in getValue Method in CreateHashTable class "+e.getMessage()); return 0;}
		
        } 
}
