package org.bss.brihaspatisync.reflector.network.util;

/**
 * @(#)RuntimeObject.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2009 ETRG, IIT Kanpur.
 */

import java.util.Vector;
import org.bss.brihaspatisync.reflector.buffer_mgt.MyHashTable;
import org.bss.brihaspatisync.reflector.network.desktop_sharing.HTTPDesktopSharing; 
/**
 * @author <a href="mailto:arvindjss17@gmail.com"> Arvind Pal  </a>Created on 25Feb2009
 * @author <a href="mailto:ashish.knp@gmail.com"> Ashish Yadav </a>Review code and modify on 20March2009
 */

public class RuntimeObject {

	private MyHashTable ht=null;

	private String indexServerAddr="";
	
	private Vector vector= new  Vector();
	
	private static RuntimeObject obj=null;
	
	private MyHashTable userListVector=null;
	
	private Vector courseid = new  Vector();

	private Vector master_ref= new  Vector();
	
	public static RuntimeObject getController(){
		if(obj==null)
			obj=new RuntimeObject();
		return obj;
	}
	
	public Vector getCourseid_IP(String course_id){
		Vector v=new Vector();
		for(int i=0;i<vector.size();i++) {
			String str=vector.get(i).toString();
			if(str.startsWith(course_id)){
				str=str.replaceAll(course_id+"#","");
				v.add(str);
			}
		}
                return v;
        }
	
	public void setCourseid_IP(String courseid_IP){
                vector.add(courseid_IP);
        }

        public boolean getStatusCourseidIP(String courseid_IP){
                return (vector.contains(courseid_IP)) ? false : true ;
        }

	public void setMastrerReflecterCourseid(String course_id,String client_ip){
		if(!master_ref.contains(course_id)) {
        		master_ref.add(course_id);
			try {
				HTTPDesktopSharing.getController().setHTTPDesktopSharingIP(client_ip);
			}catch(Exception e){}
		}
        }
	public Vector getMastrerReflecterCourseid(){
		return master_ref;
        }

	
	
	public void setindexServerAddr(String value){
		if(indexServerAddr.equals(""))
                	indexServerAddr=value+"/ProcessRequest?";
        }
	public String getindexServerAddr(){
		if(!(indexServerAddr.equals("")))
                	return indexServerAddr;
		return null;
        }

	public MyHashTable getMyHashTable(){
		if(ht == null)
                        ht=new MyHashTable();
		return ht;
	}

	/** get UserList Vector 
	 *  object according to course id 
	 */
 	
	public MyHashTable getUserListMyHashTable(){
                if(userListVector == null)
                        userListVector = new MyHashTable();
                return userListVector;
        }
	
	/** set course id */
	public void setCourseID(String value){
		if(!courseid.contains(value))
			courseid.add(value);
        }
        public Vector getCourseID(){
                return courseid;
        }
	
}
