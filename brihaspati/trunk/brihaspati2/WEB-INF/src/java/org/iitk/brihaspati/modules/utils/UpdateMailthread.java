package org.iitk.brihaspati.modules.utils;


/*@(#)CommonUtility.java
 *  Copyright (c) 2005-2008,2010-2011 ETRG,IIT Kanpur. http://www.iitk.ac.in/
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
//java
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.iitk.brihaspati.modules.utils.CommonUtility;


/**
 * @author <a href="mailto:kishore.shukla@gmail.com">Kishore shukla</a>
 */
public class  UpdateMailthread implements Runnable {

        private boolean flag=false;
        private static Thread runner=null;
        private static  UpdateMailthread  updateMailthread=null;


        public static  UpdateMailthread getController(){
                if(updateMailthread== null)
                         updateMailthread =new UpdateMailthread();
                return  updateMailthread;
        }
 	/*
        * Start  UpdateMailthread Thread.
        */
        private void start(){
        	if (runner == null) {
                	flag=true;
                        runner = new Thread(this);
                        runner.start();
			//ErrorDumpUtil.ErrorLog("========>UpdateMailthread thread running======");
                }
        }

        /**
         * Stop Thread.
         */
        private void stop() {
                if (runner != null) {
                        flag=false;
                        runner.interrupt();
                        runner = null;
                }
        }

	public void UpdateMailSystem(){
		start();
	}
   	public void run() {
		try{
			//calling Cleansystem method 
			while(flag){
		 		boolean bl=CommonUtility.CleanSystem();
        			//ErrorDumpUtil.ErrorLog("========>UpdateMailthread thread running=3=====");
				stop();
			}
		}
		catch(Exception e){ErrorDumpUtil.ErrorLog("Error in UpdateMailthread class==="+e);}
	}
}//end of class
