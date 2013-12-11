package org.bss.brihaspatisync.reflector.network.singleport;

/**
 * UserConnectionController.java
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2013,ETRG, IIT Kanpur.
 **/

import java.util.Map;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Hashtable;

import org.bss.brihaspatisync.reflector.buffer_mgt.BufferMgt;
import org.bss.brihaspatisync.reflector.buffer_mgt.StoreBufferMgnObject;
import org.bss.brihaspatisync.reflector.network.serverdata.*;
/**
 * @author <a href="mailto:arvindjss17@gmail.com"> Arvind Pal  </a>
 */

public class UserConnectionController implements Runnable {
	
	private Thread runner=null;
	private boolean flag=false;
	private Hashtable<String,Hashtable> create_hashtable_lectureid = new Hashtable<String,Hashtable> ();
	
	public UserConnectionController() { 
		startThread();
	}
	
	/**
	 * Start Thread
	 */
 	
	private void startThread(){
                if (runner == null) {
			flag=true;
                        runner = new Thread(this);
                        runner.start();
                        System.out.println(" UserConnectionController start successfully !!");
                }
       	}

	/**
	 * Stop Thread
	 */  
	public void stopThread() {
                if (runner != null) {
			flag=false;
			runner.stop();
			runner=null;
			System.out.println(" UserConnectionController stop successfully !!");
		}
	}
		
	/**
	 * This method are used to remove user in hash table .
	 */
  	
	public void run() {
                while(flag) {
			try {	
				if((create_hashtable_lectureid.size()>0) && (create_hashtable_lectureid != null) ) {		
					ArrayList myArrayList=new ArrayList(create_hashtable_lectureid.entrySet());
        	                	Iterator itr=myArrayList.iterator();
                	        	while(itr.hasNext()) {
                        	        	Map.Entry e=(Map.Entry)itr.next();
	                        	        String lecture_id = (String)e.getKey();
        	                        	Hashtable hashtable=create_hashtable_lectureid.get(lecture_id);
						VectorClass vectorclass=UserListHashTable.getCourseIdUserListVector(lecture_id);
						if((vectorclass!=null) && (StoreBufferMgnObject.getStatusBufferMgtObject("Desktop_Data"+lecture_id))) {
                        			        String senduserlist_to_client=vectorclass.getValue();
							if(!(senduserlist_to_client.matches("(.*)Allow(.*)"))) {
								BufferMgt buffer_mgt=StoreBufferMgnObject.getBufferMgtObject("Desktop_Data"+lecture_id);	
								StoreBufferMgnObject.removeBufferMgtObject("Desktop_Data"+lecture_id);
							}
			                        }
						if(hashtable.size()>0) {
							ArrayList userlistarray=new ArrayList(hashtable.entrySet());
			                                Iterator itr_1=userlistarray.iterator();
							while(itr_1.hasNext()) {
								Map.Entry e_1=(Map.Entry)itr_1.next();
	                        		                String login_name = (String)e_1.getKey();
								long l=(Long)hashtable.get(login_name);
								long long_diff=System.currentTimeMillis()-l;
								if(long_diff > 50000) {
									try {
									hashtable.remove(login_name);
									BufferMgt buffer_mgt=StoreBufferMgnObject.getBufferMgtObject("Audio_Data"+lecture_id);
									if(buffer_mgt != null)
										buffer_mgt.removeUseridKey(login_name);
									buffer_mgt=StoreBufferMgnObject.getBufferMgtObject("Desktop_Data"+lecture_id);	
									if(buffer_mgt != null)
	                                                                        buffer_mgt.removeUseridKey(login_name);
	
									buffer_mgt=StoreBufferMgnObject.getBufferMgtObject("Chat_Wb_Data"+lecture_id);
									if(buffer_mgt != null)
	                                                                        buffer_mgt.removeUseridKey(login_name);
									buffer_mgt=StoreBufferMgnObject.getBufferMgtObject("ins_video"+lecture_id);
									if(buffer_mgt != null)
	                                                                        buffer_mgt.removeUseridKey(login_name);
									buffer_mgt=StoreBufferMgnObject.getBufferMgtObject("stud_video"+lecture_id);
									if(buffer_mgt != null)
	                                                                        buffer_mgt.removeUseridKey(login_name);
									org.bss.brihaspatisync.reflector.RegisterToIndexServer.request_For_RemoveUser(lecture_id,login_name);
									} catch(Exception ex){}				
								}
							}
						} else 
							create_hashtable_lectureid.remove(lecture_id);	
					}
				}
				runner.yield();
				runner.sleep(50000);
			} catch(Exception e) { System.out.println("Exception in UserConnectionController class  "+e.getMessage()); }
		}
	}	
	
	protected void setLoginidAndTime(String lecture_id,String user_id) {
		/**
		 * create hash table according to lecture id ,
		 * then lecture id hash table contains user id and curent time 
		 */
		if(!(create_hashtable_lectureid.containsKey(lecture_id))) {
			create_hashtable_lectureid.put(lecture_id,new Hashtable<String,Long>());
		}
		Hashtable temp_ht=(Hashtable)create_hashtable_lectureid.get(lecture_id);
		temp_ht.put(user_id,System.currentTimeMillis());
	} 	
}
