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

 
public class  PointerStoreAndUserRemove {
	
	private Vector vector=new Vector();
		
	private Hashtable<String, Integer> pointer_hashtable = null;
        /* For each media type defined by String, a new hashtable is added. This
         * pointed hashable, has user_name and head location upto which data has been
         * received by the user from reflector.
         */ 

	protected PointerStoreAndUserRemove() { 
		pointer_hashtable = new Hashtable<String, Integer> ();
	}	
                          
	/**
        * resetPointer method is used to reset the values stored in hashtable after packets have
        * been deleted from queue.
        */ 
        protected void resetPointer(Integer decreasepointer){
                try{
			ArrayList myArrayList=new ArrayList(pointer_hashtable.entrySet());
                        Iterator itr=myArrayList.iterator();
                        while(itr.hasNext()) {
                                Map.Entry e=(Map.Entry)itr.next();
                                String key = (String)e.getKey();
                                Integer value=pointer_hashtable.get(key);
				value=value-decreasepointer;
				if(value <0) value=0;
                                setPointer(key,value);
                        }
                }catch(Exception e){ System.out.println("Exception in resetPointer Method in CreateHashTable class "+e.getMessage());}
        } 
  
        /**
         * setPointer method is used to set the login_name and the new pointer
         * value upto which packets have been received by the user. 
         */ 
	     	
        protected void setPointer(String login_name,Integer pointer){
                try {
                        pointer_hashtable.put(login_name,pointer);
		}catch(Exception e){System.out.println("Exception in setPointer Method in CreateHashTable class ");}
        }
 
	/**
	 * removePointer method is used to remove the login_name key in hashtable .
	 */ 	
	protected void removeUseridKey(String login_name){
                try {
                        pointer_hashtable.remove(login_name);
                }catch(Exception e){System.out.println("Exception in setPointer Method in CreateHashTable class ");}
        } 
	/**
	 * This method is used to get all the pointer values stored in
         * pointer_hashtable, and then sorting and finally storing them in vector.
         * This vector is returned by this function. 
	 */ 
	protected synchronized Vector getAllPointer() {
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
	
	protected int getPointerBy_UserId(String login_name) {
                try {
			if(pointer_hashtable.containsKey(login_name)) {
                        	Integer ik=pointer_hashtable.get(login_name);
             			return ik;
			}else {
				setPointer(login_name,0);
				return 0;	
			}
               	} catch(Exception e){System.out.println("Exception in getValue Method in CreateHashTable class "+e.getMessage()); return 0;}
        } 
}
