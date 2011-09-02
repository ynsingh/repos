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

	private String data="";
	private boolean hrflag=false;
	private boolean presflag=false;
 	private Vector vector=new Vector();
	private static UserListUtil util=null;
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
			if(!(temp_ht.getStatusCourseId(course_id))) {
                                temp_ht.setCourseIdUserListVector(course_id,new VectorClass());
                        }
			
			VectorClass vectorclass=temp_ht.getCourseIdUserListVector(course_id);
			if(!(vectorclass.getValue().equals(userlistdata)))
				vectorclass.addValue(userlistdata);
			if(userlistdata.equals("noUser")){
	                        RuntimeDataObject.getController().resetMastrerReflecterCourseid(course_id);
				temp_ht.removeCourseIdUserListVector(course_id);
				RuntimeDataObject.getController().setHandraiseFlag(false);
                              	RuntimeDataObject.getController().setHandraiseFlag(false);

			}
			strat_stop_HandraiseFlag();
		} catch(Exception e){ System.out.println("Error in UserListUtil.java line no 50 "+e.getMessage()); }
        }
	
	public String getDataForVector(String course_id) {
		String userlistdata="";
                try {
			VectorClass vectorclass=temp_ht.getCourseIdUserListVector(course_id);
			if(vectorclass!=null){
	                        userlistdata=vectorclass.getValue();
				data=userlistdata;
			}
		}catch(Exception e){
			System.out.println("Error in UserListUtil.java line no 62 "+e.getMessage());
		}
		return userlistdata;	
        }
	
	private void strat_stop_HandraiseFlag(){
		try {
			String str=data;
                       	str=str.replaceAll(","," ");
                        if(str.length()>0){
                        	java.util.StringTokenizer Tok = new java.util.StringTokenizer(str);
                                while(Tok.hasMoreElements()) {
                                	String str1=(String)Tok.nextElement();
                                        vector.add(str1);
                           	}
                      	}
			for(int i=0;i<vector.size();i++) {
   				str=vector.get(i).toString();
                                StringTokenizer st = new StringTokenizer(str, "$");
                                while(st.hasMoreTokens()) {
                                	String key = st.nextToken();
                                        String val = st.nextToken();
                                        status_vector.add(val);
                               	}
					
                                try {
                                	if(status_vector.contains("Allow-Mic")) {
                                                if(!hrflag) {
                                                	hrflag=true;
                                                        RuntimeDataObject.getController().setHandraiseFlag(hrflag);
                                               	}
                                        }else if(status_vector.contains("Allow-Screen")){
						if(!presflag) {
                                                        presflag=true;
                                                        RuntimeDataObject.getController().setPresentationFlag(presflag);
                                                }
					}else{
                                        	if(hrflag){
                                                	hrflag=false;
                                                        RuntimeDataObject.getController().setHandraiseFlag(hrflag);
                                                }
						if(presflag){
                                                        presflag=false;
                                                        RuntimeDataObject.getController().setHandraiseFlag(presflag);
                                                }
                                      	}

                             	} catch(Exception e){}
                      	}
                        vector.clear();
                        status_vector.clear();
		}catch(Exception w){}	
	}
	

}

	
