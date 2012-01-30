package org.iitk.brihaspati.modules.utils;


/*@(#)LoginUtils.java
 *  Copyright (c) 2011 ETRG,IIT Kanpur. http://www.iitk.ac.in/
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
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.Vector;
import javax.servlet.http.HttpSession ;
import java.util.Collection;
import org.apache.turbine.services.session.TurbineSession;
//import org.apache.turbine.services.session.TurbineSessionService;
import java.util.Iterator;
import org.apache.torque.util.Criteria;
////turbine
import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;
import org.apache.turbine.om.security.User;
import org.apache.turbine.services.security.TurbineSecurity;
import org.apache.turbine.Turbine;
import org.apache.turbine.util.security.TurbineSecurityException;
import java.security.NoSuchAlgorithmException;
import org.iitk.brihaspati.om.UserPrefPeer;
import org.iitk.brihaspati.om.UserPref;
import org.iitk.brihaspati.om.TurbineUserPeer;
import org.iitk.brihaspati.om.TurbineUser;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.lang.StringUtils;
import org.iitk.brihaspati.om.UsageDetailsPeer;
import com.workingdogs.village.Record;
import org.iitk.brihaspati.om.UserConfigurationPeer;


/**
 * This class is used for call the method in mylogin 
 * like Create index for Search, Clean the system 
 * 
 * @author <a href="mailto:nksinghiitk@gmail.com">Nagendra Kumar Singh</a>
 * @version 1.0
 * @since 1.0
 */
public class LoginUtils{
	/** 
          *  Get the session if exist then remove and create new session
          *  @param username String
          *  @return 
          **/
	public static void CheckSession(String username){

                        try{
				User user=null;
                                Vector ve=new Vector();
                                Collection aul=TurbineSession.getActiveUsers();
                                Iterator it=aul.iterator();
                                while(it.hasNext()){
                                       String ss=it.next().toString();
                                       ve.add(ss.substring(0,(ss.length()-3)));
                                }
                                if(!username.equals("guest"))
                                {
                                	if(ve.contains(username)){
                                        	user=TurbineSecurity.getUser(username);
						Collection au=TurbineSession.getSessionsForUser(user);
        	                                for(Iterator i=au.iterator();i.hasNext();)
                	                        {
                        	                       HttpSession session=(HttpSession) i.next();
                                	                session.invalidate();
	                                        }
	                                }
				}// try for single session
			}catch(Exception ev){
                                ErrorDumpUtil.ErrorLog("This error comes from Single session in LoginUtils utils "+ev.getMessage());
                        }
	}
	/** 
	 *  Set the user data for session in temp variable
	 *  @param username String
	 *  @param password String
	 *  @param flag String
	 *  @param lang String
	 *  @param data RunData
	 *  @return String
	 **/
	public static String SetUserData(String username, String password, String flag, String lang, RunData data){
		User user=null;		
        	String userLanguage = "";
		String page=new String();
		Criteria crit = null;
		 try{
			if(StringUtils.isBlank(username)) {
                        	username = data.getMessage();
                        }
			List vec=null;
			int uid=UserUtil.getUID(username);
			try{
                       		crit= new Criteria();
	                       	crit.add(TurbineUserPeer.USER_ID,uid);
                	       	vec=TurbineUserPeer.doSelect(crit);
			}
			catch(Exception e){
				ErrorDumpUtil.ErrorLog("This Exception comes (in side First try) in the Login Utils-SetUserData Facility"+e+"\n");
                        }
			if(vec.size() != 0) {
                       		TurbineUser element=(TurbineUser)vec.get(0);
			
				// Authenticate with local database of that user and get the object.
				if(StringUtils.isNotBlank(password)){
					password=EncryptionUtil.createDigest("MD5",password);
				}
				else{
					password=element.getPasswordValue().toString();
				}
	                        user=TurbineSecurity.getAuthenticatedUser(username, password );
				// Store the user object.
				data.setUser(user);
				// Mark the user as being logged in.
				user.setHasLoggedIn(new Boolean(true));
        	                Date date=new Date();
				try{
					// Set the last_login date in the database.
					user.updateLastLogin();
					crit = new Criteria();
					crit.add(UserPrefPeer.USER_ID,uid);
					List llst=UserPrefPeer.doSelect(crit);
					UserPref lelement=(UserPref)llst.get(0);
//	                                crit = null;
        	                        userLanguage=lelement.getUserLang().toString();
                                //if(vec != null){
                                        if((userLanguage.equals("")))
                                        {
                                                crit = new Criteria();
                                                crit.add(UserPrefPeer.USER_ID,uid);
                                                crit.add(UserPrefPeer.USER_LANG, lang);
                                                UserPrefPeer.doUpdate(crit);
                                                user.setTemp("lang",lang);
                                                user.setTemp("LangFile", MultilingualUtil.LanguageSelectionForScreenMessage(lang));
                                            //    context.put("lang",lang);
                                        }else {
                                                if((!userLanguage.equals(lang)) && (!username.equals("guest"))){
                                                        if(flag.equals("false")) {
                                                                crit = new Criteria();
                                                                crit.add(UserPrefPeer.USER_ID,uid);
                                                                crit.and(UserPrefPeer.USER_LANG,lang);
                                                                UserPrefPeer.doUpdate(crit);
                                                                userLanguage=lang;
                                                        }
							user.setTemp("lang",userLanguage);
                                              //          context.put("lang",userLanguage);
                                                        user.setTemp("LangFile", MultilingualUtil.LanguageSelectionForScreenMessage(userLanguage));
                                                }else{
							// Store the LangFile & lang object in Temporary Variable.
							user.setTemp("lang",lang);
                                                //        context.put("lang",lang);
                                                        user.setTemp("LangFile", MultilingualUtil.LanguageSelectionForScreenMessage(lang));
                                                }
                                        }
			//	}
			//	else
                          //      {
                                       // context.put("lang",lang);
			//		data.setMessage(MultilingualUtil.ConvertedString("brih_langMsg", MultilingualUtil.LanguageSelectionForScreenMessage(lang)));
                          //              data.setScreenTemplate("BrihaspatiLogin.vm");
			//	}	
				}
				catch(Exception e){
                	                data.setMessage("Cannot Login !! The error is :- "+e);
        	                        page=Turbine.getConfiguration().getString("BrihaspatiLogin.vm");
	                                data.setScreen(page);
 //                               log.info("this message would go to any facility configured to use the " + this.getClass().getName() + " Facility");
                                	ErrorDumpUtil.ErrorLog("This Exception comes in the Login Utils-SetUserData Facility"+e);
                        	}
			}//end if
		}

		catch ( TurbineSecurityException e ){
			String msg1=MultilingualUtil.ConvertedString("t_msg",MultilingualUtil.LanguageSelectionForScreenMessage(lang));
			data.setMessage(msg1);
                        data.setScreenTemplate("BrihaspatiLogin.vm");
   //                     log.info("this message would go to any facility configured to use the " + this.getClass().getName() + " Facility"+e);
                        ErrorDumpUtil.ErrorLog("This TurbineSecurityException comes in the Login Utils-SetUserData Facility"+e);
                }
                catch (NoSuchAlgorithmException e){
                        data.setMessage("Could not find the required implementation");
                        page=Turbine.getConfiguration().getString("screen.login");
                        data.setScreenTemplate(page);
                        ErrorDumpUtil.ErrorLog("This NoSuchAlgorithmException comes in the Login Utils-SetUserData Facility"+e);
                }
	return lang;
	}
	/** 
	 *  Update user login date in database
	 *  @param uid int
	 *  @return 
	 **/
        public static void UpdateUsageData(int uid){
		try{
			int least_entry=0,count=0;
			Date date=new Date();
			Criteria crit=new Criteria();
                        crit.add(UsageDetailsPeer.USER_ID,uid);
                        List entry=UsageDetailsPeer.doSelect(crit);
                        count=entry.size();
                        String find_minimum="SELECT MIN(ENTRY_ID) FROM USAGE_DETAILS WHERE USER_ID="+uid;
			if(count >= 10)
                                {
                                        List v=UsageDetailsPeer.executeQuery(find_minimum);
                                        for(Iterator j=v.iterator();j.hasNext();)
                                        {
                                                Record item2=(Record)j.next();
                                                least_entry=item2.getValue("MIN(ENTRY_ID)").asInt();
					}
                                        crit=new Criteria();
                                        crit.add(UsageDetailsPeer.ENTRY_ID,Integer.toString(least_entry));
                                        UsageDetailsPeer.doDelete(crit);
			}
			crit=new Criteria();
                        crit.add(UsageDetailsPeer.USER_ID,Integer.toString(uid));
                        crit.add(UsageDetailsPeer.LOGIN_TIME,date);
                        crit.add(UsageDetailsPeer.LOGOUT_TIME,date);
                        UsageDetailsPeer.doInsert(crit);
		}
		catch(Exception e){
                                ErrorDumpUtil.ErrorLog("This Exception comes in the Login Utils-updateUsageData Facility"+e);
                }

	}
	/** 
	 *  Check the user for hint question when login at the first time
	 *  @param uid int
	 *  @param data RunData
	 *  @return 
	 **/
        public static void SetHintQues(int uid, RunData data){
		try{
			/**
			 * Check for the admin and the guest
			 */
			if(uid!=0 && uid!=1)
                                {
                                        Criteria crit=new Criteria();
                                        crit.add(UserConfigurationPeer.USER_ID,uid);
                                        crit.add(UserConfigurationPeer.QUESTION_ID,0);
                                        List check=UserConfigurationPeer.doSelect(crit);
                                        if((check.size()!=0))
                                        {
                                                data.setScreenTemplate("call,SelectHintQuestion.vm");
					}
			}
		}
                        catch(Exception e)
                        {
                                data.setMessage("Error in selecting hint question.Exception is :- "+e);
                        }

		}
}//end of class
