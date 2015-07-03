package org.bss.brihaspatisync.reflector.buffer_mgt;

/**
 * PointerStoreAndUserRemove.java
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2012.2013,2015 ETRG,IIT Kanpur.
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
 * @author <a href="mailto:pradeepmca30@gmail.com">Pradeep Kumar Pal </a>
 */

 
public class  PointerStoreAndUserRemove {
	
	private Vector vector=new Vector();
		
	private Hashtable<String, Integer> pointerstoreanduserremove_hashtable = null;
        /**
 	 * For each media type defined by String, a new hashtable is added.This pointed hashable,
 	 * has user_name and head location upto which data has been received by the user from reflector.
         */ 

	protected PointerStoreAndUserRemove() { 
		pointerstoreanduserremove_hashtable = new Hashtable<String, Integer> ();
	}	
                          
	/**
        * resetPointer method is used to reset the values stored in hashtable after packets have
        * been deleted from queue.
        */ 
        protected void resetPointer(Integer decreasepointer){
                try{
			ArrayList myArrayList=new ArrayList(pointerstoreanduserremove_hashtable.entrySet());
                        Iterator itr=myArrayList.iterator();
                        while(itr.hasNext()) {
                                Map.Entry e=(Map.Entry)itr.next();
                                String key = (String)e.getKey();
                                Integer value=pointerstoreanduserremove_hashtable.get(key);
				value=value-decreasepointer;
				if(value <0) value=0;
                                setPointer(key,value);
                        }
                }catch(Exception e){ System.out.println("Exception in resetPointer Method in PointerStoreAndUserRemove class "+e.getMessage());}
        } 
  
        /**
         * setPointer method is used to set the login_name and the new pointer value upto which packets have been received by the user. 
         */ 
	     	
        protected void setPointer(String login_name,Integer login_pointer){
                try {
                        pointerstoreanduserremove_hashtable.put(login_name,login_pointer);
		}catch(Exception e){System.out.println("Exception in setPointer Method in PointerStoreAndUserRemove class ");}
        }
 
	/**
	 * removePointer method is used to remove the login_name key in hashtable after the user logged out from the system.
	 */ 	
	protected void removeUseridKey(String login_name){
                try {
                        pointerstoreanduserremove_hashtable.remove(login_name);
                }catch(Exception e){System.out.println("Exception in removeUseridKey Method in PointerStoreAndUserRemove class ");}
        } 
	
	/**
	 * This method is used to get all the pointer values stored in pointerstoreanduserremove_hashtable,
	 * and then sorting and finally storing them in vector.This vector is returned by this function. 
	 */ 
	protected synchronized Vector getAllPointer() {
                try {
                        vector.clear();	
			ArrayList myArrayList=new ArrayList(pointerstoreanduserremove_hashtable.entrySet());
                        Iterator itr=myArrayList.iterator();
                        while(itr.hasNext()){
				Map.Entry e=(Map.Entry)itr.next();
                                String key = (String)e.getKey();
                                Integer value=pointerstoreanduserremove_hashtable.get(key);
				vector.add(value);
                        }
			Collections.sort(vector);
                }catch(Exception e){ System.out.println("Exception in getAllPointer Method in PointerStoreAndUserRemove class ");}
                return vector;
        }
 
        /**
         * To get the pointer value of given login name.instally this function set the pointer of the given login name as zero,
         * in the subseqent calls it retuns the interger pointer corresponding to that login name.
         */
	
	protected int getPointerBy_UserId(String login_name) {
                try {
			if(pointerstoreanduserremove_hashtable.containsKey(login_name)) {
                        	Integer ik=pointerstoreanduserremove_hashtable.get(login_name);
             			return ik;
			}else {
				setPointer(login_name,0);
				return 0;	
			}
               	} catch(Exception e){System.out.println("Exception in getPointerBy_UserId Method in PointerStoreAndUserRemove class "+e.getMessage()); return 0;}
        } 
}
