package org.bss.brihaspatisync.reflector.buffer_mgt;

/**
 * CreateHashTable.java
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2012 ETRG,IIT Kanpur.
 */
 
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;
import java.util.Enumeration;

/**
 * @author <a href="mailto:arvindjss17@gmail.com">Arvind Pal  </a> 
 * @author <a href="mailto:meera.knit@gmail.com">Meera Pal </a>
 */

 
public class  CreateHashTable {
	
	private Vector vector=null;
	private Hashtable pointer_hashtable = new Hashtable();
        private Hashtable storedata_hashtable = new Hashtable();
         
	public CreateHashTable() { }	
                          
         /**
         * Create set_getBuffer method to check incoming packet type
         *queue is available or not if not then create queue of that type.
         *         
         */          
	
	public Buffer set_getBuffer(String type) {
		if(!storedata_hashtable.containsKey(type)){
                	storedata_hashtable.put(type,new Buffer());
		}
		return (Buffer)storedata_hashtable.get(type);
	}

	/**
         *  Create setPointertoHashtable method to check incoming packet type hashTable
         *  is available or not if not then create a hashTable of that type.
         *                          
         */     
	public Hashtable setPointertoHashtable(String type) {
                if(!pointer_hashtable.containsKey(type)){
                        pointer_hashtable.put(type,new Hashtable());
                }
                return (Hashtable)pointer_hashtable.get(type);
        }

	/**
        * resetPointer method is used to reset the values stored in hashtable after packets has deleted from queue.
        *
        */ 
        public void resetPointer(int decreasepointer,String type){
                try{
			Hashtable ht=setPointertoHashtable(type);
                        ArrayList myArrayList=new ArrayList(ht.entrySet());
                        Collections.sort(myArrayList, new MyComparator());
                        Iterator itr=myArrayList.iterator();
                        while(itr.hasNext()){
                                Map.Entry e=(Map.Entry)itr.next();
                                String key = (String)e.getKey();
                                int value = ((Integer)e.getValue()).intValue();
                                if((value-decreasepointer) >0 ) {
                                        value=value-decreasepointer;
                                        ht.put(key,value);
                                     
                                }
				else {
                                        ht.put(key,decreasepointer);
                                }
                        }
                }catch(Exception e){ System.out.println("Error in resetPointer Method in CreateHashTable class ");}
        } 
  
        /**
         * setPointer method is used to set the ip of incomming packet as key  and how
         * many times packets from this ip is come is stored as value in hashtable,after
         * this, store the value of hashtable in a vector. 
         *
         */ 
	     	
        public void setPointer(String ip,int pointer ,String type){
		Hashtable ht=setPointertoHashtable(type);
                try {
                        ht.put(ip,pointer);
                        if(vector==null )
                               vector=new Vector();
                        vector.clear();
			
                        ArrayList myArrayList=new ArrayList(ht.entrySet());   
                        Collections.sort(myArrayList, new MyComparator());
                        Iterator itr=myArrayList.iterator();
                        while(itr.hasNext()){
                                Map.Entry e=(Map.Entry)itr.next();
                                String key = (String)e.getKey();
                                int value = ((Integer)e.getValue()).intValue();        
                                vector.add(value);
                     	}
		}catch(Exception e){System.out.println("Error in setPointer Method in CreateHashTable class ");}
        }
    
	public Vector getPointer() throws Exception {
  		return this.vector;
        }

        /**
         * To get the pointer value of given ip.
         */
	
	public int getValue(String ip,String type) {
		Hashtable ht=setPointertoHashtable(type);
                try {
			if(ht.containsKey(ip)){
                        	int ik=((Integer)(ht.get(ip))).intValue();
             			return ik;
			}
			return 0;
               	} catch(Exception e){System.out.println("Error in getValue Method in CreateHashTable class "+e.getMessage()); return 0;}
		
        } 
}
