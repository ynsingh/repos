package org.bss.brihaspatisync.reflector.buffer_mgt;

/**
 * MyHashTable.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2009 ETRG, IIT Kanpur.
 */


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

//import org.bss.brihaspatisync.reflector.network.tcp.MaintainLog;
import org.bss.brihaspatisync.reflector.network.serverdata.VectorClass;

/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a> 
 * @author <a href="mailto:arvindjss17@gmail.com">Arvind Pal  </a> 
 */


public class  MyHashTable {
        
	private int pointer=0;
	private Vector vector=null;
	private Hashtable ht = new Hashtable();
	
	public MyHashTable(){ }	
	
	public void resetPointer(int minpoint){
        	try{
                        ArrayList myArrayList=new ArrayList(ht.entrySet());
                        Collections.sort(myArrayList, new MyComparator());
                        Iterator itr=myArrayList.iterator();
                        while(itr.hasNext()){
                                Map.Entry e=(Map.Entry)itr.next();
                                String key = (String)e.getKey();
                                int value = ((Integer)e.getValue()).intValue();
				if((value-minpoint) >0 ) {
	                                value=value-minpoint;
					ht.put(key,value);
				}else {
					ht.put(key,minpoint);
				}
			}
		}catch(Exception e){
			System.out.println("Error in reSetPointer(int minpoint) in Hash table "+e.getMessage());
                }
        }
		

	public void setPointer(String ip,int pointer ) {
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
			
		}catch(Exception e){
			System.out.println("Error in MyHashTable.java ");
		}	
       	}
	
	public Hashtable getHashtable(){
		return this.ht;
	}
		
	public synchronized int getValue(String str){
		try {
        	        int ik=(Integer)ht.get(str);
			return ik;
		}catch(Exception e){return 0;}
        }
	
	public int getSize(){
                int ik=(Integer)ht.size();
                return ik;
        }

	
	public boolean getStatus(String req) {
		boolean flag=false;	
		if(ht.containsKey(req)){
			flag=true;				
		}
		return flag;
	}	
	
	public void setValues(String st,BufferMgt bm){
		ht.put(st,bm);
	}	
	
	public BufferMgt getValues(String st){
                return (BufferMgt)ht.get(st);
        }
		
	public Vector getPointer(){
		if(vector==null )
			vector=new Vector();
		return this.vector;		
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

	/** end for Userlist **/
}

