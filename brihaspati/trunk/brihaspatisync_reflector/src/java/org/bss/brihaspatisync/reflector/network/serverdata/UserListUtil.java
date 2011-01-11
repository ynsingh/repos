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
import org.bss.brihaspatisync.reflector.network.tcp.MaintainLog;	
import org.bss.brihaspatisync.reflector.util.RuntimeDataObject;

/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a>
 * @author <a href="mailto:arvindjss17@gmail.com">Arvind Pal </a>
 */

public class UserListUtil {

	private static UserListUtil util=null;
       
	private MaintainLog log=MaintainLog.getController();
	 
	private MyHashTable temp_ht=RuntimeDataObject.getController().getUserListMyHashTable();
		
	protected UserListUtil() { }
	private boolean flag=false;

        public static UserListUtil getContriller() {
                if(util == null)
                        util=new UserListUtil();
                return util;
        }
	
	public void clearDataForVector(String course_id){
		try {
			if(!(temp_ht.getStatusCourseId(course_id))){
                        	temp_ht.setCourseIdUserListVector(course_id,new VectorClass());
                        }
			VectorClass vectorclass=temp_ht.getCourseIdUserListVector(course_id);
                        vectorclass.clear();
		}catch(Exception e){
			log.setString("Error in UserListUtil.java line no 42 "+e.getMessage());
		}
	}
	
	public void addDataForVector(String course_id,String data) {
                try {
			VectorClass vectorclass=temp_ht.getCourseIdUserListVector(course_id);
			vectorclass.addValue(data);
		} catch(Exception e){
			log.setString("Error in UserListUtil.java line no 50 "+e.getMessage());
		}
        }
	
	public String getDataForVector(String course_id) {
		String data="";
                try {
			VectorClass vectorclass=temp_ht.getCourseIdUserListVector(course_id);
			if(vectorclass!=null){
	                        Vector vector=vectorclass.getValue();
        	                for(int i=0;i<vector.size();i++){
                	        	data=data+","+vector.get(i).toString();
					String str=vector.get(i).toString();
					StringTokenizer st = new StringTokenizer(str, "$");
					while(st.hasMoreTokens()) {
						String key = st.nextToken();
    						String val = st.nextToken();
						if(val.equals("Allow-Mic")){
                        	                	System.out.println("Allow-Mic found");
							if(!flag) {
                                	                	flag=true;
								RuntimeDataObject.getController().setHandraiseFlag(flag);
							}
                                     		}
					}
				}
				vector.clear();
			}
		}catch(Exception e){
			log.setString("Error in UserListUtil.java line no 62 "+e.getMessage());
		}
		return data;	
        }
}

	
