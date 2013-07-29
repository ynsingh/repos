package org.iitk.brihaspati.modules.actions;
/*
 * @(#)myLogin.java	
 *
 *  Copyright (c) 2004-2008,2009,2011,2013 ETRG,IIT Kanpur. 
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
import java.util.Collection;
import org.apache.commons.lang.StringUtils;
import org.apache.torque.util.Criteria;
import org.apache.turbine.modules.actions.VelocityAction;
import org.apache.turbine.util.RunData;
import org.apache.turbine.util.security.AccessControlList;
import org.apache.turbine.services.security.TurbineSecurity;
import org.apache.velocity.context.Context;
import org.iitk.brihaspati.om.UserPrefPeer;
import org.iitk.brihaspati.om.UserPref;
import org.iitk.brihaspati.modules.utils.UserUtil;
import org.iitk.brihaspati.modules.utils.StringUtil;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.iitk.brihaspati.modules.utils.CommonUtility;
import org.iitk.brihaspati.modules.utils.LoginUtils;
import org.iitk.brihaspati.modules.utils.UpdateMailthread;
import org.iitk.brihaspati.modules.utils.QuotationThread;
import org.iitk.brihaspati.modules.utils.MultilingualUtil;
import org.iitk.brihaspati.modules.utils.AdminProperties;

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
 *  @author modifieddate 09-08-2012, 01-10-2012, 09-05-2013 <a href="mailto:rpriyanka12@ymail.com">Priyanka Rawat</a>
 */

public class myLogin extends VelocityAction{
		
	/**
	 * This method is invoked upon when user logging in
	 * @param data RunData instance
	 * @param context Context instance
	 */
	
	public void doPerform( RunData data, Context context )
	{
		//Start time
		long startTime = System.nanoTime();
		int load_flag =0;

		System.gc();

		/** Getting Language according to Selection of Language in lang Variable
                 *  Getting Property file  according to Selection of Language
		 */
		// This flag is used to update language in database if previous selected language is different
		String flag=data.getParameters().getString("flag");
		// Getting language selected by user
                String lang=data.getParameters().getString("lang","");
		//Getting  language tag file on the basis of selected language
		String LangFile=MultilingualUtil.LanguageSelectionForScreenMessage(lang);
		//Getting user name and checks for legal character
		String username = data.getParameters().getString("username", "" );
		if(StringUtil.checkString(username) != -1) username="";
		// Getting base name for ldap auth
		String lcat = data.getParameters().getString("lcate", "" );

		String password = data.getParameters().getString("password", "" );
		if ((StringUtils.isEmpty(password))||(StringUtils.isEmpty(username))){
			data.setScreenTemplate("BrihaspatiLogin.vm");
		}
		else{

			/**
			 * If you make any change code below then make sure that 
			 * the similer change in LoginFromBrihspti.java action
			 */
                                         
			int uid=UserUtil.getUID(username);
			// uid will be returned as -1 if user does not exists.

			if(uid != -1){
					String str;
					List list = null;
				try{
					Criteria crit = new Criteria();
					crit.add(UserPrefPeer.USER_ID,uid);
					list = UserPrefPeer.doSelect(crit);
					String a_key = ((UserPref)list.get(0)).getActivation(); 
 
					if (a_key == null || a_key.equalsIgnoreCase("NULL"))
					{
						 try{
                	                              	str=MultilingualUtil.ConvertedString("act_prb",LangFile);
                                                        data.setMessage(str);
                        	                        data.getResponse().sendRedirect(data.getServerScheme()+"://"+data.getServerName()+":"+data.getServerPort()+"/brihaspati/servlet/brihaspati/template/BrihaspatiLogin.vm?msg="+str);
                                 	         }
	                                         catch (Exception ex){
                	                                ErrorDumpUtil.ErrorLog("User's account activated not activated........... "+ex);
         	                                 }
					}
					else if (a_key == "ACTIVATE" || a_key.equalsIgnoreCase("ACTIVATE"))
					{
						/** 
				 		*  Get the session if exist then remove and create new session
				 		**/
						lang=LoginUtils.SetUserData(username, password, lcat, flag, lang, data);
						context.put("lang",lang);
						LoginUtils.UpdateUsageData(uid);
					//	ErrorDumpUtil.ErrorLog("After updating usage data");
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
							data.setMessage(MultilingualUtil.ConvertedString("accountNotCorrect", LangFile));
						}
						//ErrorDumpUtil.ErrorLog("After setting the ACL");
			
						/*calling UpdateMailThread Util*/
						UpdateMailthread.getController().UpdateMailSystem();
						Date date=new Date();
						//Update login entry in database
						boolean AB=CommonUtility.IFLoginEntry(uid,date);
						// Call change password after configured time		
						LoginUtils.getChangePasswordtemp(date,uid,data);

						/**
        	                  		*Check the user for hint question when login at the first time.
                	          		*/
						LoginUtils.SetHintQues(uid, data);
					//	ErrorDumpUtil.ErrorLog("After checking hint question");
						/**
	 					* Called the method from utils for Insert record when user (Student) already exist
 						* in Turbine User Table
 						*/
						//Calculating time taken to execute the above code
						try
						{
							String path=data.getServletContext().getRealPath("/WEB-INF")+"/conf"+"/"+"Admin.properties";
                					String normalTrafficTime = AdminProperties.getValue(path,"brihaspati.admin.normalTraffic.value");
							double normal_trafficTime = Double.parseDouble(normalTrafficTime);
							String highTrafficTime = AdminProperties.getValue(path,"brihaspati.admin.highTraffic.value");
							double high_trafficTime = Double.parseDouble(highTrafficTime);
							long estimatedTime = System.nanoTime() - startTime;
							//double elapsedTime = (double)estimatedTime / 60000000000.0;
							//in seconds
							double elapsedTime = (double)estimatedTime / 1000000000.0;
							if(elapsedTime < normal_trafficTime)
							{
								load_flag=0;	
							}
							else if(elapsedTime < high_trafficTime)
							{
								load_flag=1;
							}
							else 
							{
								load_flag=2;
							}

							/**
				 			 * Number of active users is being calculated here.
				 			 * When a user visits Brihaspati's login page
				 			 * and load flag has value 2, then
				 			 * this number will then be compared with the
				 			 * number of active users at that time. If number 
				 			 * of active users would have been decreased then 
				 			 * the value of load_flag will be set to "0".
				 			 * Decreased number of active users signifies that
				 			 * some of the users who have logged in are not active,
				 			 * thus load on the system will be low.
  			 				*/
							Collection au=org.apache.turbine.services.session.TurbineSession.getActiveUsers();
							QuotationThread.getController().setActiveUser(au.size());
							QuotationThread.getController().setLoadFlag(load_flag);
						}
						catch(Exception ex)
						{
							ErrorDumpUtil.ErrorLog("An exception occurred while calculating loadfactor: myLogin class "+ex);
						}	
						System.gc();
					}
					// Foolowing check added by Priyanka
					else
					{
						try{
							str=MultilingualUtil.ConvertedString("reAct_mail",LangFile);
                                                        data.setMessage(str);
                                                        data.getResponse().sendRedirect(data.getServerScheme()+"://"+data.getServerName()+":"+data.getServerPort()+"/brihaspati/servlet/brihaspati/template/BrihaspatiLogin.vm?msg="+str);
                                                 }
                                                 catch (Exception ex){
                                                        ErrorDumpUtil.ErrorLog("User's account is not activated........... "+ex);
							data.setMessage(MultilingualUtil.ConvertedString("accountNotActivate", LangFile));
                                                 }

					}
				}
				catch(Exception e){
					ErrorDumpUtil.ErrorLog("User's account is not activated........... "+e);
                                        data.setMessage(MultilingualUtil.ConvertedString("accountNotActivate", LangFile));
	                        }
			}//if for uid (-1)
			else{
				data.setMessage(MultilingualUtil.ConvertedString("accountNotCorrect", LangFile));
                                data.setScreenTemplate("BrihaspatiLogin.vm");
			}
       		}//end else of password check
	}
}
