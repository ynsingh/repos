package org.iitk.brihaspati.modules.utils;
/*
 * @(#)ModuleTimeThread.java
 *
 *  Copyright (c) 2013 ETRG,IIT Kanpur.
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
 *  Contributors: Members of ETRG, I.I.T. Kanpur
 *
 */

import java.io.File;
import java.util.Date;
import java.util.Vector;
import org.iitk.brihaspati.modules.utils.CourseTimeUtil;
import org.iitk.brihaspati.modules.utils.ModuleTimeUtil;

/**
 *@author <a href="mailto:smita37uiet@gmail.com">SmitaPal</a>
 */

public class ModuleTimeThread implements Runnable {
	/*Entry_id for every unique login */
	/*User_id of currently login user*/
	private Vector userid_list=null; 
	private Vector eid_list=null; 
	private boolean flag=false;
	private static Thread runner=null;
	private static ModuleTimeThread moduletimeThread=null;
	/**
	 * Controller for this class to use as a singleton.
	 */

	public ModuleTimeThread(){
		userid_list=new Vector();
		eid_list=new Vector();
		
	}
	
        public static ModuleTimeThread getController(){
                if(moduletimeThread==null)
                        moduletimeThread=new ModuleTimeThread();
                return moduletimeThread;
        }
		
	
	public void CourseTimeSystem(int userid,int eid){
		userid_list.add(userid);
		eid_list.add(eid);
                start();
        }
	
	/**
        * Start Time Thread.
        */ 
        private void start(){
                if (runner==null) {
			flag=true;	
                        runner=new Thread(this);
                        runner.start();
                }
        }

        /**
         * Stop TimeThread.
         */
        private void stop() {
                if (runner!=null) {
			flag=false;
                        runner.interrupt();
                        runner=null;
                }
        }
	
	/**
	 * Calulation thread for Time Calulation
	 */
      	public synchronized void run() {
		while(flag) {
			try {
				for(int i=0;i<userid_list.size();i++){
					int userid =(Integer)userid_list.get(i);
					int eid =(Integer)eid_list.get(i);
					userid_list.remove(i);
					eid_list.remove(i);
					try{
				                Date CreTime=CourseTimeUtil.getDatetime(userid);
			                        Date mreTime=ModuleTimeUtil.getMrecenttime(userid);
			                        if(mreTime!=null){
				                        if((CreTime.getTime()<mreTime.getTime()) || (CreTime.getTime()==mreTime.getTime())) {
								CourseTimeUtil.getCalculation(userid);
								ModuleTimeUtil.getModuleCalculation(userid);
			                                	if(eid!=0)
				                                	CourseTimeUtil.getchangeStatus(eid);
			                        	} else {
								CourseTimeUtil.getCalculation(userid);
			                                	if(eid!=0)
                               						CourseTimeUtil.getchangeStatus(eid);
			                        	}
                       				}
					}catch(Exception ex){ ErrorDumpUtil.ErrorLog("Error in ModuleTimeUtil in Method AllCalculations---"+ex);}
					runner.sleep(10);
					runner.yield();
				}
				stop();
			}catch(Exception es){ ErrorDumpUtil.ErrorLog("Error in catch in run method "+es.getMessage()); }
		}	
    	}
}// End of file.
