package org.iitk.brihaspati.modules.actions;
/*
 * @(#)LoginFromBrihspti.java	
 *
 *  Copyright (c) 2012 ETRG,IIT Kanpur. 
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
 *  Contributors : members of ETRG, IIT Kanpur  
 *  
 */

import org.apache.velocity.context.Context;
import org.apache.turbine.util.RunData;
import org.apache.turbine.modules.screens.VelocityScreen;
import org.apache.turbine.util.parser.ParameterParser;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.apache.commons.lang.StringUtils;

import org.iitk.brihaspati.modules.utils.security.ReadNWriteInTxt;
import org.iitk.brihaspati.modules.utils.security.EncrptDecrpt;

import java.net.URLDecoder;
import org.apache.turbine.modules.actions.VelocityAction;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.torque.util.Criteria;

import org.iitk.brihaspati.modules.utils.LoginUtils;
import org.iitk.brihaspati.modules.utils.UserUtil;
import org.apache.turbine.om.security.User;

import org.apache.turbine.util.security.AccessControlList;
import org.apache.turbine.services.security.TurbineSecurity;
import org.iitk.brihaspati.modules.utils.UpdateMailthread;

import java.util.Date;
import org.iitk.brihaspati.modules.utils.CommonUtility;




/**
 * Action class for authenticating a apllication form authentic server 
 * and send the data to that application also maintain a log
 *
 *  @author <a href="mailto:nksinghiitk@gmail.com">Nagendra Kumar Singh</a>
 */

public class LoginFromBrihspti extends VelocityAction{
	private Log log = LogFactory.getLog(this.getClass());

	/**
	 * This method is invoked upon when user logging in
	 * @param data RunData instance
	 * @param context Context instance
	 */
	
	public void doPerform( RunData data, Context context )
	{
		try{
			data.setMessage(" ");
			String msg="";
                        ParameterParser pp=data.getParameters();
                        String lang=pp.getString("lang","english");
                        context.put("lang",lang);
                        ErrorDumpUtil.ErrorLog("message in LoginFromBrihspti action screen=="+msg);

                        String encd=data.getParameters().getString("encd");
                        String randomNo=data.getParameters().getString("rand") ;
                        String hash=data.getParameters().getString("hash");

                        ErrorDumpUtil.ErrorLog("message in LoginFromBrihspti action 10 =="+encd+" "+ randomNo+" "+ hash);

                        ErrorDumpUtil.ErrorLog("message in LoginFromBrihspti action 1 =="+encd+" "+ randomNo+" "+ hash);
                        String hdir=System.getProperty("user.home");
                        String path=hdir+"/remote_auth/brihaspati3-remote-access.properties";
                        String srcid="iitk_test";
                        String line=ReadNWriteInTxt.readLin(path,srcid);
                        String skey=StringUtils.substringBetween(line,";",";");
                        ErrorDumpUtil.ErrorLog("message in LoginFromBrihspti action  2 =="+skey);


                        String enUrl1=EncrptDecrpt.decrypt(encd,srcid);
                        ErrorDumpUtil.ErrorLog("message in LoginFromBrihspti action aa =="+enUrl1);
                        String email1=StringUtils.substringBetween(enUrl1,"email=","&sess=");
                        ErrorDumpUtil.ErrorLog("message in LoginFromBrihspti action bb ==  "+email1);
                        String sessno=StringUtils.substringAfter(enUrl1,"&sess=");
                        ErrorDumpUtil.ErrorLog("message in LoginFromBrihspti action 3 =="+enUrl1+" "+email1+" "+sessno);

                        boolean flag=false;
			String hashcodeR=EncrptDecrpt.keyedHash(email1,randomNo,skey);
                        if(hash.equals(hashcodeR)){
                            //    msg=pp.getString("msg","My hash code matched ");
                              //  data.setMessage(msg);

                        	ErrorDumpUtil.ErrorLog("message in LoginFromBrihspti action 4  hash matched==");
                                String path1=hdir+"/remote_auth/remote-user.txt";
                                flag=ReadNWriteInTxt.readF(path1,email1+";"+sessno);

                                if(flag){
                                //        data.addMessage(" I am verified");
                                        ErrorDumpUtil.ErrorLog("message in LoginFromBrihspti action   verified==");
					/**
					 * If you make any change below the code then make sure that 
					 * make the same change in myLogin.java action
					 *
					 */
					String userLanguage = "";
					Criteria crit = null;
					String password="";
					String flag1="true";
					int uid=UserUtil.getUID(email1);
					if(uid != -1){
						/** 
                                  		*  Get the session if exist then remove and create new session
					 	**/
	        	                        User user=null;
		                                LoginUtils.CheckSession(email1);
                	                	ErrorDumpUtil.ErrorLog("After checking the session LoginFromBrihspti");

						user = null;
        	                	        lang=LoginUtils.SetUserData(email1, password, "", flag1, lang, data);
	                                	context.put("lang",lang);
	                	                ErrorDumpUtil.ErrorLog("After setting User data LoginFromBrihspti");
				
						userLanguage = null;
	        	                        crit = null;
        	        	                LoginUtils.UpdateUsageData(uid);
                	        	        ErrorDumpUtil.ErrorLog("After updating usage data LoginFromBrihspti");
		
						//If there is an error redirect to login page with a message"Cannot Login"
						try{
		                                        AccessControlList acl = data.getACL();
                		                        if( acl == null ){
                                		                acl = TurbineSecurity.getACL( data.getUser() );
                                                		ErrorDumpUtil.ErrorLog("If ACL null LoginFromBrihspti");
		                                                data.getSession().setAttribute( AccessControlList.SESSION_KEY,(Object)acl );
                		                        }
                                		        data.setACL(acl);
		                                        data.save();
                		                }
                                		catch(Exception ex){
	                                        ErrorDumpUtil.ErrorLog("Error in setting Access rules :- "+ex +" The account '' does not exist Or password is incorrect");
        	                                data.addMessage("The account does not exist Or password is incorrect");
                		                }
                                		ErrorDumpUtil.ErrorLog("After setting the ACL LoginFromBrihspti");

						/*calling UpdateMailThread Util*/
						UpdateMailthread.getController().UpdateMailSystem();
	                	                Date date=new Date();
        		                        boolean AB=CommonUtility.IFLoginEntry(uid,date);
						/**
						 * Check the user for hint question when login at the first time.
						 **/
						LoginUtils.SetHintQues(uid, data);
		                                ErrorDumpUtil.ErrorLog("After checking hint question LoginFromBrihspti");
                		                /**
						 * Called the method from utils for Insert record when user (Student) already exist
						 * in Turbine User Table
						 **/
		                                System.gc();
                	        	}//if for uid (-1)
					else{
                        		        data.addMessage("User does not exist in this application. So Please register first.");
                	                	data.setScreenTemplate("BrihaspatiLogin.vm");
		                        }

                                }
                        }
                }
                catch(Exception ex)
                {
                        data.addMessage("The error in LoginFromBrihspti action !! "+ex);
                }
	}
}
