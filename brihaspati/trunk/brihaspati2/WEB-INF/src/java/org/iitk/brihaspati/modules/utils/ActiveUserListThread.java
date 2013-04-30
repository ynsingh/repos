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
import org.iitk.brihaspati.modules.utils.ActiveUserListController;


public class ActiveUserListThread implements Runnable {

        private boolean flag=false;
        private static Thread runner=null;
	private Vector user_id=new Vector();
	private java.util.Hashtable ht1 = new java.util.Hashtable();	
        private static ActiveUserListThread activeuserlistThread=null;
	
        /**
         * Controller for this class to use as a singleton.
         */
        public static ActiveUserListThread getController(){
                if(activeuserlistThread==null) {
                        activeuserlistThread=new ActiveUserListThread();
			activeuserlistThread.start();	
		}
                return activeuserlistThread;
        }
	
        public void activeUser(int userid) throws Exception {
		try {
			user_id.add(userid);
                        if(ht1.containsKey(userid))
                                ht1.remove(userid);
                        Vector cId=new Vector();
                        if(userid==1){
                                cId.add("admin");
                        } else if(userid==0){
                                cId.add("guest");
                        }else if((userid!=1) && (userid!=0)){
				 cId=InstituteIdUtil.getAllInstId(userid);
			}
                        ht1.put(userid,cId);
		}catch(Exception e){}
        }
	

        /**
        * Start ActiveUserListThread Thread.
        */
        private void start(){
		if (runner == null) {
                        flag=true;
                        runner = new Thread(this);
                        runner.start();
                }
        }

        /**
         * Stop ActiveUserListThread Thread.
         */
        private void stop() {
                if (runner != null) {
                        flag=false;
                        runner.interrupt();
                        runner = null;
                }
        }

        /**
         * ActiveUserListThread for shows currently login user with time.
         */
        public synchronized void run() {
                while(flag) {
                       try {
				runner.sleep(1000);
                                runner.yield();
				if(user_id.size()>0 ) {
					Vector InsId=(Vector)ht1.remove((Integer)user_id.get(0));
                                        int userid=(Integer)(user_id.get(0));
                                        user_id.remove(0);
                                        for (int x = 0; x < InsId.size(); x++) {
                                        	java.util.Hashtable ht=ActiveUserListController.getController().gettemp_Hashtable();
                                                Object e=InsId.get(x);
                                                if(ht.containsKey(e.toString()))
                                                	ht.remove(e.toString());
                                        }
					java.util.Collection au=org.apache.turbine.services.session.TurbineSession.getActiveUsers();
					java.util.Iterator it=au.iterator();
        	               		while(it.hasNext()){
                	               		String ss=it.next().toString();
                        	      		String u=(ss.substring(0,(ss.length()-3)));
                              			int uid=UserUtil.getUID(u);
						String time=InstituteIdUtil.getTimeCalculation(uid);
						if(userid ==1){
							Object e=InsId.get(0);
                	                        	String uIdTime=u+" "+"("+time+")";
							Vector returnvector=ActiveUserListController.getController().getempVector(e.toString().trim());
                                	                returnvector.add(uIdTime);
                                        	}else if(userid ==0) {
							Object e=InsId.get(0);
							Vector returnvector=ActiveUserListController.getController().getempVector(e.toString().trim());
							if(u.equals("guest")){
	        	                                        String uIdTime=u+" "+"("+time+")";
								returnvector.add(uIdTime);

							}
						}else {
							Vector lId=InstituteIdUtil.getAllInstId(uid);
							for (int x = 0; x < InsId.size(); x++) {
                	               				Object e=InsId.get(x);
								Vector returnvector=ActiveUserListController.getController().getempVector(e.toString().trim());
								if(lId.contains(e)) {
                                		       			String uIdTime=u+" "+"("+time+")";
									uIdTime=uIdTime.trim();
									if(!(returnvector.contains(uIdTime))) {
                                                		                returnvector.add(uIdTime);
                                                        		}
								}
							}
						}
					}
					
					try {
						for (int x = 0; x < InsId.size(); x++) {
							Object e=InsId.get(x);
							Vector returnvector=ActiveUserListController.getController().getempVector(e.toString().trim());
							java.util.Hashtable ht=ActiveUserListController.getController().getHashtable();
							ht.put(e.toString().trim(),returnvector);
						}
					}catch(Exception e){ ErrorDumpUtil.ErrorLog("Exception in thread class 2"+e.getMessage()); }
			}
		}catch(Exception es){ ErrorDumpUtil.ErrorLog("Exception thread "+es.getMessage()); }
         	}
        }
}
