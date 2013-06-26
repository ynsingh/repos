package org.iitk.brihaspati.modules.utils;

/*@(#)ActiveUserCourseListThread.java
 *  Copyright (c) 2013 ETRG,IIT Kanpur. http://www.iitk.ac.in/
 *  All Rights Reserved.
 *
 *  Redistribution and use in source and binary forms, with or 
 *  without modification, are permitted provided that the following 
 *  conditions are met:
 * 
 *  Redistributions of source code must retain the above copyright  
 *  notice, this  list of conditions and the following disclaimer.
 * 
 *  Redistribution in binary form must reproducuce the above copyright 
 *  notice, this list of conditions and the following disclaimer in 
 *  the documentation and/or other materials provided with the 
 *  distribution.
 * 
 *  THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 *  WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 *  OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 *  DISCLAIMED.  IN NO EVENT SHALL ETRG OR ITS CONTRIBUTORS BE LIABLE
 *  FOR ANY DIRECT, INDIRECT, INCIDENTAL,SPECIAL, EXEMPLARY, OR 
 *  CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT
 *  OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR 
 *  BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 *  WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE 
 *  OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, 
 *  EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *  
 *  
 */

import java.util.Vector;
import org.iitk.brihaspati.modules.utils.UserGroupRoleUtil;
/**
 * @author <a href="mailto:smita37uiet@gmail.com">Smita Pal</a>
 */

public class ActiveUserCourseListThread implements Runnable {
	private static ActiveUserCourseListThread activeusercourselistThread=null;
	private ActiveUserCourseController controller=ActiveUserCourseController.getController();
        private boolean flag=false;
        private Thread runner=null;
        private Vector user_id=new Vector();
	private Vector group_id=new Vector();
        /**
         * Controller for this class to use as a singleton.
         */
        public static ActiveUserCourseListThread getController() throws Exception {
                if(activeusercourselistThread==null) {
                        activeusercourselistThread=new ActiveUserCourseListThread();
                }
               activeusercourselistThread.start();
                return activeusercourselistThread;
        }
	
	 /**
         * This method is used to set curently login id in vector 
         * and put in a hash table with coresponding inst id .
         */

        public void setActiveUserId(int userid,int groupid) throws Exception {
                try {
                        user_id.add(userid);
			group_id.add(groupid);
                }catch(Exception e){ErrorDumpUtil.ErrorLog("Exception in setActiveUserId method in ActiveUserListThread class !"+e.getMessage());}
        }
	
	 /**
        * Start ActiveUserListThread Thread.
        */
        private void start() throws Exception {
                if (runner == null) {
                        flag=true;
                        runner = new Thread(this);
                        runner.start();
                }
        }
	/**11
         * Stop ActiveUserListThread Thread.
         */
        private void stop() throws Exception {
                if (runner != null) {
                        flag=false;
                        runner = null;
                }
        }
	public synchronized void run() {
                while(flag) {
                       		try {
				runner.sleep(1000);
				runner.yield();
				java.util.Collection au=org.apache.turbine.services.session.TurbineSession.getActiveUsers();
				if(user_id.size()>0 ) {
					for(int i=0;i<user_id.size();i++){
						/*
						 * Get userid & group_id from the vector.and remove 						     * value from vector.
						*/
						int userid=(Integer)(user_id.get(i));
						int groupid=(Integer)(group_id.get(i));
                                        	user_id.remove(i);
						group_id.remove(i);
						/*
						 *These lines of code are used to remove tempvector						      according to group_id.
						 */
                                        	Vector tempvec=controller.getempVector(groupid);
                                        	tempvec.clear();
						/*
						 *These lines of code are use to pick all user accor						     *ding to given group_id from USERGROUPROLE table 
						 * from database.
						 */
						Vector user_groupid=UserGroupRoleUtil.getUID(groupid);
						/*
						 * Get users one by one from current session list 
						 * and check their group_id.if user belongs to given 						     *	list add in vector.
						 */
						java.util.Iterator it=au.iterator();
                                        	while(it.hasNext()){

                                                	String ss=it.next().toString();
                                                	String u=(ss.substring(0,(ss.length()-3)));
                                                	int uid=UserUtil.getUID(u);
							if(user_groupid.contains(Integer.toString(uid)))
							{
								tempvec=controller.getempVector(groupid);							    String cname=CourseTimeUtil.getCloginTime(uid);
								if(org.apache.commons.lang.StringUtils.isNotBlank(cname))
			       				 	tempvec.add(cname);
							}
						}

                                     	Vector returnvec=controller.getempVector(groupid);                                                java.util.Hashtable final_courseuserlist=controller.getHashtable();
                                       final_courseuserlist.put(groupid,returnvec);   
				   }
                     		   	}else{
					/*
					 * If no user is login then clear both hashtables.
					 */	
					if(au.size()==0){
						java.util.Hashtable final_courseuserlist=controller.getHashtable();
						java.util.Hashtable temp_courseuserlist=controller.getHashtable();
						final_courseuserlist.clear();
						temp_courseuserlist.clear();
						stop();
					
					}
				}
							
		}catch(Exception e){ErrorDumpUtil.ErrorLog("Exception in Run method in ActiveUserCourseList !!"+e);}
			
		}	
	}
}

