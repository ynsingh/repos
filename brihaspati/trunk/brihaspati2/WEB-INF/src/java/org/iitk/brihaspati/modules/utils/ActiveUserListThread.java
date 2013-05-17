package org.iitk.brihaspati.modules.utils;

/*@(#)ActiveUserListThread.java
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

/**
 * @author <a href="mailto:smita37uiet@gmail.com">Smita Pal</a>
 */

public class ActiveUserListThread implements Runnable {

        private boolean flag=false;
        private Thread runner=null;
	private Vector user_id=new Vector();
	private java.util.Collection au=null;
        private static ActiveUserListThread activeuserlistThread=null;
	private java.util.Hashtable userid_instid = new java.util.Hashtable();	
	private ActiveUserListController controller=ActiveUserListController.getController();

        /**
         * Controller for this class to use as a singleton.
         */
        public static ActiveUserListThread getController() throws Exception {
                if(activeuserlistThread==null) {
                        activeuserlistThread=new ActiveUserListThread();
		}
		activeuserlistThread.start();	
                return activeuserlistThread;
        }
	
	/**
	 * This method is used to set curently login id in vector 
	 * and put in a hash table with coresponding inst id .
	 */	
	
        public void setActiveUserId(int userid) throws Exception {
		try {
			user_id.add(userid);
                        Vector cid=new Vector();
                        if(userid==1) {
                                cid.add("admin");
                        } else if(userid==0) {
                                cid.add("guest");
                        } else if((userid !=1) && (userid !=0)) {
				 cid=InstituteIdUtil.getAllInstId(userid);
			}
                        userid_instid.put(userid,cid);
		}catch(Exception e){ErrorDumpUtil.ErrorLog("Exception in setActiveUserId method in ActiveUserListThread class !"+e.getMessage());}
        }
	
	/**
	 * This method is used to get size list of all login user
	 */
	
	public int getActUsersListSize() throws Exception {
                try {
                        if(au != null)
                                return au.size();
                        else
                                return 0;
                } catch(Exception e) { ErrorDumpUtil.ErrorLog("Exception in getActUsersListSize method in ActiveUserListThread class !"+e.getMessage()); }
                return 0;
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

        /**
         * Stop ActiveUserListThread Thread.
         */
        private void stop() throws Exception {
                if (runner != null) {
                        flag=false;
                        runner.interrupt();
			//runner.stop(); this is Deprecated api 
                        runner = null;
                }
        }

        /**
         * ActiveUserListThread for shows currently login user with time.
         */
        public void run() {
                while(flag) {
                       try {
				runner.sleep(1000);
                                runner.yield();
				au=org.apache.turbine.services.session.TurbineSession.getActiveUsers();
				if(user_id.size()>0 ) {
					/**
					 * These lines of code are used to get all inst id according to user id 
					 * and remove key and value from hashtable and vector .
					 */
					int userid=(Integer)(user_id.get(0));
					Vector InsId=(Vector)userid_instid.get(userid);
                                        user_id.remove(0);
					/**
					 * These lines of code are used to remove temp vector according to inst id .
					 */
						
                                        for (int x = 0; x < InsId.size(); x++) {
						Object e=InsId.get(x);
                                                Vector tempvector=controller.getempVector(e.toString());
                                                tempvector.clear();	
                                        }
					/**
					 * These lines of code get all ActiveUsers from the session in collection 
					 * and get every user one by one and set them into a vector according to insituteid .
					 */
					java.util.Iterator it=au.iterator();
        	               		while(it.hasNext()){
                	               		String ss=it.next().toString();
                        	      		String u=(ss.substring(0,(ss.length()-3)));
                              			int uid=UserUtil.getUID(u);
						String time=InstituteIdUtil.getTimeCalculation(uid);
						if(userid ==1){
							Object e=InsId.get(0);
                	                        	String uIdTime=u+" "+"("+time+")";
							Vector returnvector=controller.getempVector(e.toString().trim());
                                	                returnvector.add(0,uIdTime);
                                        	}else if(userid ==0) {
							Object e=InsId.get(0);
							Vector returnvector=controller.getempVector(e.toString().trim());
							if(u.equals("guest")){
	        	                                        String uIdTime=u+" "+"("+time+")";
								returnvector.add(0,uIdTime);

							}
						}else {
							Vector lId=InstituteIdUtil.getAllInstId(uid);
							for (int x = 0; x < InsId.size(); x++) {
                	               				Object e=InsId.get(x);
								Vector returnvector=controller.getempVector(e.toString().trim());
								if(lId.contains(e)) {
                                		       			String uIdTime=u+" "+"("+time+")";
									uIdTime=uIdTime.trim();
									if(!(returnvector.contains(uIdTime))) {
                                                		                returnvector.add(0,uIdTime);
                                                        		}
								}
							}
						}
					}
					/**
					 * These lines of code are used to set a single copy in final hash table .
					 */
					try {
						for (int x = 0; x < InsId.size(); x++) {
							Object e=InsId.get(x);
							Vector returnvector=controller.getempVector(e.toString().trim());
							java.util.Hashtable final_userlist=controller.getHashtable();
							final_userlist.put(e.toString().trim(),returnvector);
						}
					}catch(Exception e){ ErrorDumpUtil.ErrorLog("Exception in ActiveUserListThread class line no 168 "+e.getMessage()); }
				}else{
					userid_instid.clear();
					if(au.size()==0) {
						java.util.Hashtable final_userlist=controller.getHashtable();
						java.util.Hashtable temp_userlist=controller.getTempHashtable();
						temp_userlist.clear();
						final_userlist.clear();	
						stop();
					}
				}
			}catch(Exception es){ ErrorDumpUtil.ErrorLog("Exception in ActiveUserListThread class  "+es.getMessage()); }
         	}
        }
}
