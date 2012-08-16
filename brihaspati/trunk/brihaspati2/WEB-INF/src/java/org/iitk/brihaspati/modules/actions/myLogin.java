package org.iitk.brihaspati.modules.actions;
/*
 * @(#)myLogin.java	
 *
 *  Copyright (c) 2004-2008,2009,2011 ETRG,IIT Kanpur. 
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

import java.util.List;
import java.util.Date;
import org.apache.velocity.context.Context;
import org.apache.turbine.util.RunData;
import org.apache.turbine.util.security.AccessControlList;
import org.apache.turbine.om.security.User;
import org.apache.turbine.modules.actions.VelocityAction;
import org.apache.turbine.services.security.TurbineSecurity;
import org.apache.torque.util.Criteria;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.iitk.brihaspati.om.UserPrefPeer;
import org.iitk.brihaspati.om.UserPref;
import org.iitk.brihaspati.modules.utils.UserUtil;
import org.iitk.brihaspati.modules.utils.StringUtil;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.iitk.brihaspati.modules.utils.CommonUtility;
import org.iitk.brihaspati.modules.utils.LoginUtils;
import org.iitk.brihaspati.modules.utils.UpdateMailthread;
//import org.iitk.brihaspati.modules.utils.UpdateInfoMail;

/**
 * Action class for authenticating a user into the system
 * This class also contains code for recording login statistics of 
 * users.This class is invoked whenever a user logs in to the system
 *
 * NOTE :- Use 'Turbine.getConfiguration().getString(String str)' instead of
 * 'TurbineResources.getString(String str)'.This is deprecated in TDK2.3.
 *
 *  @author <a href="mailto:shaistashekh@gmail.com">Shaista</a>
 *  @author <a href="mailto:awadhesh_trivedi@yahoo.co.in">Awadhesh Kuamr Trivedi</a>
 *  @author <a href="mailto:nksngh_p@yahoo.co.in">Nagendra Kumar Singh</a>
 *  @author <a href="mailto:singh_jaivir@rediffmail.com">Jaivir Singh</a>
 *  @author <a href="mailto:tejdgurung20@gmail.com">Tej Bahadur</a>
 *  @author <a href="mailto:palseema30@gmail.com">Manorama pal</a>
 *  @author modified date 04 Oct 2011<a href="mailto:kishore.shukla@gmail.com">kishore shukla</a>
 * @author modified date 09-08-2012<a href="mailto:rpriyanka12@ymail.com">Priyanka Rawat</a>
 */

public class myLogin extends VelocityAction{
	private Log log = LogFactory.getLog(this.getClass());
		
	/**
	 * This method is invoked upon when user logging in
	 * @param data RunData instance
	 * @param context Context instance
	 */
	
	public void doPerform( RunData data, Context context )
	{
		System.gc();
		Criteria crit = null;
		String userLanguage = "";
		String a_key = "";	
	
		List list = null;	

		/** Getting Language according to Selection of Language in lang Variable
                 *  Getting Property file  according to Selection of Language
		 */
		String flag=data.getParameters().getString("flag");
                //String LangFile =data.getParameters().getString("Langfile","");
                String lang=data.getParameters().getString("lang","");

		String username = data.getParameters().getString("username", "" );
		if(StringUtil.checkString(username) != -1) username="";
		String password = data.getParameters().getString("password", "" );
		if (password.equals(" ")){
			data.setScreenTemplate("BrihaspatiLogin.vm");
		}
		else{
			/**
			 * If you make any change below the code then make sure that 
			 * make the same change in LoginFromBrihspti.java action
			 */
                                         
			int uid=UserUtil.getUID(username);
			if(uid != -1){
		// Following lines added by Priyanka
				try{
					crit = new Criteria();
					crit.add(UserPrefPeer.USER_ID,uid);
					list = UserPrefPeer.doSelect(crit);
				//	list_size = list.size();
			//		ErrorDumpUtil.ErrorLog("LIST IS	"+list);		
					a_key = ((UserPref)list.get(0)).getActivation(); 
					//len = str.indexOf("Activation");
					 
					//ErrorDumpUtil.ErrorLog("I M HERE......"+a_key+" ACTIVATE");
 
					if (a_key == null || a_key.equalsIgnoreCase("NULL"))
					{
						 try{
                	                              data.setMessage("Your account has some problem, contact to administrator or re register.");
                        	                      data.getResponse().sendRedirect(data.getServerScheme()+"://"+data.getServerName()+":"+data.getServerPort()+"/brihaspati/servlet/brihaspati/template/BrihaspatiLogin.vm?msg=Your account has some problem, contact to administrator or re register.");
                                 	         }
	                                         catch (Exception ex){
                	                                ErrorDumpUtil.ErrorLog("User's account activated not activated........... "+ex);
         	                                 }
					}
					              
					if (a_key == "ACTIVATE" || a_key.equalsIgnoreCase("ACTIVATE"))
					{
			//..........			
						/** 
				 		*  Get the session if exist then remove and create new session
				 		**/
						User user=null;
						LoginUtils.CheckSession(username);
						ErrorDumpUtil.ErrorLog("After checking the session");

						// Provide a logger with the class name as category. This
						// is recommended because you can split your log files
						// by packages in the Log4j.properties. You can provide
						// any other category name here, though.
						log.info("this message would go to any facility configured to use the " + this.getClass().getName() + " Facility");

						user = null;
						lang=LoginUtils.SetUserData(username, password, flag, lang, data);
						context.put("lang",lang);
						ErrorDumpUtil.ErrorLog("After setting User data");

						userLanguage = null;
						crit = null;
						LoginUtils.UpdateUsageData(uid);
						ErrorDumpUtil.ErrorLog("After updating usage data");

						//If there is an error redirect to login page with a message"Cannot Login"
						try{
							AccessControlList acl = data.getACL();
							if( acl == null ){
								acl = TurbineSecurity.getACL( data.getUser() );
								ErrorDumpUtil.ErrorLog("If ACL null");
								data.getSession().setAttribute( AccessControlList.SESSION_KEY,(Object)acl );
							}
							data.setACL(acl);
							data.save();
						}
						catch(Exception ex){
							ErrorDumpUtil.ErrorLog("Error in setting Access rules :- "+ex +" The account '' does not exist Or password is incorrect");
							data.setMessage("The account does not exist Or password is incorrect");
						}
						ErrorDumpUtil.ErrorLog("After setting the ACL");
			
						/*calling UpdateMailThread Util*/
						UpdateMailthread.getController().UpdateMailSystem();
						Date date=new Date();
						boolean AB=CommonUtility.IFLoginEntry(uid,date);

						/**
        	                  		*Check the user for hint question when login at the first time.
                	          		*/
						LoginUtils.SetHintQues(uid, data);
						ErrorDumpUtil.ErrorLog("After checking hint question");
						/**
	 					* Called the method from utils for Insert record when user (Student) already exist
 						* in Turbine User Table
 						*/
						System.gc();
					}
			// Foolowing check added by Priyanka
					else
					{
						try{
                                                      data.setMessage("Your account is not activated. For activation please check your mail./n If you did not get the mail, please click on the Resend Activation link.");
                                                      data.getResponse().sendRedirect(data.getServerScheme()+"://"+data.getServerName()+":"+data.getServerPort()+"/brihaspati/servlet/brihaspati/template/BrihaspatiLogin.vm?msg=Your account is not activated. For activation please check your mail. If you did not get the mail, please click on the Resend Activation link.");
                                                 }
                                                 catch (Exception ex){
							String msg = "Error in login";
                                                        ErrorDumpUtil.ErrorLog("User's account not activated........... "+ex);
							 throw new RuntimeException(msg,ex);
                                                 }

					}
				}
				catch(Exception e){
                	                String message = "Error in activation   ";
        	                        throw new RuntimeException(message, e);
	                        }
		//..........
			}//if for uid (-1)
			else{
				data.setMessage("User does not exist.");
                                data.setScreenTemplate("BrihaspatiLogin.vm");
			}
       		}//end else of password check
	}
}
