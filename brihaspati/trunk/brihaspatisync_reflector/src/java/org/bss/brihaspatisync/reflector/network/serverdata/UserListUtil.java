package org.bss.brihaspatisync.reflector.network.serverdata;

/**
 * UserListUtil.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2007-2008 ETRG,IIT Kanpur.
 */

import java.util.Vector;
import org.bss.brihaspatisync.reflector.util.RuntimeDataObject;
import org.bss.brihaspatisync.reflector.buffer_mgt.MyHashTable;

/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a>
 * @author <a href="mailto:arvindjss17@gmail.com">Arvind Pal </a>
 */

public class UserListUtil {

	private static UserListUtil util=null;
	private String senduserlist_to_client="";
	private long timeout=0;
		
	protected UserListUtil() { }

        public static UserListUtil getContriller() {
                if(util == null)
                        util=new UserListUtil();
                return util;
        }
	
	public void addDataForVector(String course_id,String userlistdata) {
                try {
			if(userlistdata.equals("noUser")) {	
				senduserlist_to_client="sessionlist_timeout";
				if(timeout==0)
					timeout=System.currentTimeMillis();
				else if((System.currentTimeMillis()-timeout)> 50000) {	
		                       	RuntimeDataObject.getController().resetMastrerReflecterCourseid(course_id);
					UserListHashTable.removeCourseIdUserListVector(course_id);
					MyHashTable.removeBufferMgtObject(course_id);
					timeout=0;
				}
			} else {
				if(!(UserListHashTable.getStatusCourseId(course_id))) {
                                	UserListHashTable.setCourseIdUserListVector(course_id,new VectorClass());
                        	}
				VectorClass vectorclass=UserListHashTable.getCourseIdUserListVector(course_id);
	                        if(!(vectorclass.getValue().equals(userlistdata)))
        	                        vectorclass.addValue(userlistdata);
			}
		} catch(Exception e){ System.out.println("Error in UserListUtil.java line no 50 "+e.getMessage()); }
        }
	
	public String getDataForVector(String course_id) {
                try {
			VectorClass vectorclass=UserListHashTable.getCourseIdUserListVector(course_id);
			if(vectorclass!=null){
	                        senduserlist_to_client=vectorclass.getValue();
			}
		}catch(Exception e){ System.out.println("Error in UserListUtil.java line no 62 "+e.getMessage());}
		String temp_string=senduserlist_to_client;
		senduserlist_to_client="";
		return temp_string;	
        }
}
