package org.bss.brihaspatisync.reflector.network.serverdata;

/**
 * UserListUtil.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2007-2008 ETRG,IIT Kanpur.
 */

import java.util.Vector;
import java.util.StringTokenizer;
import org.bss.brihaspatisync.reflector.buffer_mgt.MyHashTable;
import org.bss.brihaspatisync.reflector.util.RuntimeDataObject;

/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a>
 * @author <a href="mailto:arvindjss17@gmail.com">Arvind Pal </a>
 */

public class UserListUtil {

	private int count_value=0;
	private boolean hrflag=false;
	private boolean presflag=false;
 	private Vector vector=new Vector();
	private static UserListUtil util=null;
	private String senduserlist_to_client="";
	private Vector status_vector=new Vector();	
	private MyHashTable temp_ht=RuntimeDataObject.getController().getUserListMyHashTable();
	
		
	protected UserListUtil() { }

        public static UserListUtil getContriller() {
                if(util == null)
                        util=new UserListUtil();
                return util;
        }
	
	public void addDataForVector(String course_id,String userlistdata) {
                try {
			System.out.println("Add user list ---->  "+userlistdata+" course_id "+course_id);	
			if(userlistdata.equals("noUser")){	
	                       	RuntimeDataObject.getController().resetMastrerReflecterCourseid(course_id);
				temp_ht.removeCourseIdUserListVector(course_id);
				senduserlist_to_client="sessionlist_timeout";
			} else {
				if(!(temp_ht.getStatusCourseId(course_id))) {
                                	temp_ht.setCourseIdUserListVector(course_id,new VectorClass());
                        	}
				VectorClass vectorclass=temp_ht.getCourseIdUserListVector(course_id);
	                        if(!(vectorclass.getValue().equals(userlistdata)))
        	                        vectorclass.addValue(userlistdata);
			}
		} catch(Exception e){ System.out.println("Error in UserListUtil.java line no 50 "+e.getMessage()); }
        }
	
	public String getDataForVector(String course_id) {
                try {
			VectorClass vectorclass=temp_ht.getCourseIdUserListVector(course_id);
			if(vectorclass!=null){
	                        senduserlist_to_client=vectorclass.getValue();
			}
		}catch(Exception e){ System.out.println("Error in UserListUtil.java line no 62 "+e.getMessage());}
		
		String temp_string=senduserlist_to_client;
		System.out.println("send userlist to client "+senduserlist_to_client);
		senduserlist_to_client="";
		return temp_string;	
        }
}
