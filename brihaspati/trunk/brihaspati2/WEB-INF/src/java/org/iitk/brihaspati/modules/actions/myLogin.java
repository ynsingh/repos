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

import java.util.Date;
import java.util.List;
import java.util.Iterator;
import java.security.NoSuchAlgorithmException;

import com.workingdogs.village.Record;
import org.apache.velocity.context.Context;
import org.apache.turbine.Turbine;
import org.apache.turbine.TurbineConstants;

import org.apache.turbine.util.RunData;
import org.apache.turbine.util.security.AccessControlList;
import org.apache.turbine.util.security.TurbineSecurityException;
import org.apache.turbine.om.security.User;
import org.apache.turbine.modules.actions.VelocityAction;
import org.apache.turbine.services.security.TurbineSecurity;
import org.apache.turbine.Turbine;
import org.apache.torque.util.Criteria;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.iitk.brihaspati.om.TurbineUserPeer;
import org.iitk.brihaspati.om.TurbineUser;
import org.iitk.brihaspati.om.StudentExpiryPeer;
import org.iitk.brihaspati.om.StudentExpiry;
import org.iitk.brihaspati.om.UsageDetailsPeer;
import org.iitk.brihaspati.om.UserConfigurationPeer;
import org.iitk.brihaspati.modules.utils.UserUtil;
import org.iitk.brihaspati.modules.utils.StringUtil;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.iitk.brihaspati.modules.utils.CommonUtility;
import org.iitk.brihaspati.modules.utils.EncryptionUtil;
import org.iitk.brihaspati.modules.utils.MultilingualUtil;
import org.iitk.brihaspati.modules.utils.ExpiryUtil;
import org.apache.turbine.services.session.TurbineSession;
import org.apache.turbine.services.session.TurbineSessionService;
import javax.servlet.http.HttpSession ;
import java.util.Collection;
import java.util.Vector;

//import java.util.Iterator;

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
		
		/** Getting Language according to Selection of Language in lang Variable
                 *  Getting Property file  according to Selection of Language
		 */

                String flag=data.getParameters().getString("flag");
                //String LangFile =data.getParameters().getString("Langfile","");
                String lang=data.getParameters().getString("lang","english");

		String username = data.getParameters().getString("username", "" );
		if(StringUtil.checkString(username) != -1) username="";
		String password = data.getParameters().getString("password", "" );
		User user=null;
		/**
 		*Insert record in table with status
		*if user already exist in other table
		*/
 		try{
			boolean flag1=false;
                        int []in={0,1};
                        crit = new Criteria();
                        crit.addGroupByColumn(TurbineUserPeer.USER_ID);
                        crit.addNotIn(TurbineUserPeer.USER_ID,in);
                        List lst1 =TurbineUserPeer.doSelect(crit);
			String c_date=ExpiryUtil.getCurrentDate("-");
                        String E_date=ExpiryUtil.getExpired(c_date,180);
                        Date expdate=java.sql.Date.valueOf(E_date);
			if(lst1.size()!=0)
			{
                        	for(int p=0;p<lst1.size();p++)
                       	 	{
                                	TurbineUser element=(TurbineUser)lst1.get(p);
                                	int user_id1 = element.getUserId();
                                	crit = new Criteria();
                                	crit.addGroupByColumn(StudentExpiryPeer.UID);
                                	List lst2 = StudentExpiryPeer.doSelect(crit);
                                	for(int a=0; a<lst2.size();a++)
                                	{
                                        	StudentExpiry element1=(StudentExpiry)lst2.get(a);
                                        	int user_id2=element1.getUid();
                                        	if((Integer.toString(user_id1)).equals(Integer.toString(user_id2)))
                                        	{
                                                	flag1=true;
                                        	}
				
					}
                                	if(!flag1)
                                	{
                                		crit=new Criteria();
                                        	crit.add(StudentExpiryPeer.UID,user_id1);
                                        	crit.add(StudentExpiryPeer.EMAIL,"");
                                        	crit.add(StudentExpiryPeer.CID,"");
                                        	crit.add(StudentExpiryPeer.ROLL_NO,"");
                                        	crit.add(StudentExpiryPeer.EXPIRY_DAYS,180);
                                        	crit.add(StudentExpiryPeer.EXPIRY_DATE,expdate);
                                        	crit.add(StudentExpiryPeer.STATUS,"ENABLE");
                                        	StudentExpiryPeer.doInsert(crit);
					}
				}
			}
		}
	        catch(Exception e){data.setMessage("exception is ! "+e);}

			/** 
			  *  Get the session if exist then remove and create new session
			**/
			try{
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
                                        //User u=TurbineSecurity.getUser(username);
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
                                ErrorDumpUtil.ErrorLog("This error comes from Single session in myLogin Action "+ev.getMessage());
                        }

		user = null;
		String page=new String();
		// Provide a logger with the class name as category. This
		// is recommended because you can split your log files
		// by packages in the Log4j.properties. You can provide
		// any other category name here, though.

		log.info("this message would go to any facility configured to use the " + this.getClass().getName() + " Facility");//
		try{//(1)
			String str=new String();
			// Authenticate the user and get the object.
			password=EncryptionUtil.createDigest("MD5",password);
			user=TurbineSecurity.getAuthenticatedUser(username, password );

			// Store the user object.
			data.setUser(user);
			
			// Mark the user as being logged in.
			user.setHasLoggedIn(new Boolean(true));
			// get User ID 
			int uid=UserUtil.getUID(username);
			Date date=new Date();
			try{//(2)
				// Set the last_login date in the database.
 		
				user.updateLastLogin();
//////////////////////////////////////////////////////////////////////////////
				List vec=null;
				crit= new Criteria();
                                crit.add(TurbineUserPeer.USER_ID,uid);
                                crit.addGroupByColumn(TurbineUserPeer.USER_LANG);
                                vec=TurbineUserPeer.doSelect(crit);
				crit = null;
                                TurbineUser element=(TurbineUser)vec.get(0);
       	                        userLanguage=element.getUserLang().toString();
				if(vec != null){
					if((userLanguage.equals("")))
					{
                        	                crit = new Criteria();
                                	        crit.add(TurbineUserPeer.USER_ID,uid);
                                       		crit.add(TurbineUserPeer.USER_LANG, lang);
	                                        TurbineUserPeer.doUpdate(crit);
	                                	user.setTemp("lang",lang);
						user.setTemp("LangFile", MultilingualUtil.LanguageSelectionForScreenMessage(lang));
        	                               	context.put("lang",lang);
                	                }else {
						if((!userLanguage.equals(lang)) && (!username.equals("guest"))){
							if(flag.equals("false")) {
        		                                        crit = new Criteria();
                		                                crit.add(TurbineUserPeer.USER_ID,uid);
                        		                        crit.and(TurbineUserPeer.USER_LANG,lang);
                                		                TurbineUserPeer.doUpdate(crit);
								userLanguage=lang;
							}
							user.setTemp("lang",userLanguage);
     							context.put("lang",userLanguage);
							user.setTemp("LangFile", MultilingualUtil.LanguageSelectionForScreenMessage(userLanguage));
	                                        }
						else{
        	        	                	// Store the LangFile & lang object in Temporary Variable.
		                        	        //user.setTemp("LangFile",LangFile);
               	                                	user.setTemp("lang",lang);
							context.put("lang",lang);
							user.setTemp("LangFile", MultilingualUtil.LanguageSelectionForScreenMessage(lang));
						}
                                	}
				} //vec close
				else
				{
                                        context.put("lang",lang);
					//LangFile =data.getParameters().getString("Langfile");
					data.setMessage(MultilingualUtil.ConvertedString("brih_langMsg", MultilingualUtil.LanguageSelectionForScreenMessage(lang)));
					data.setScreenTemplate("BrihaspatiLogin.vm");
					

				}	
				userLanguage = null;
				crit = null;
				int least_entry=0,count=0;

				//code for usage details starts here
				crit=new Criteria();
				crit.add(UsageDetailsPeer.USER_ID,uid);
				List entry=UsageDetailsPeer.doSelect(crit);
				count=entry.size();
				String find_minimum="SELECT MIN(ENTRY_ID) FROM USAGE_DETAILS WHERE USER_ID="+uid;
				ErrorDumpUtil.ErrorLog("fm from usage details=="+find_minimum);
				if(count >= 10)
				{
					List v=UsageDetailsPeer.executeQuery(find_minimum);
					for(Iterator j=v.iterator();j.hasNext();)
					{
						Record item2=(Record)j.next();
						least_entry=item2.getValue("MIN(ENTRY_ID)").asInt();
						ErrorDumpUtil.ErrorLog("least_entry from usage details=="+least_entry);
					}
					crit=new Criteria();
					crit.add(UsageDetailsPeer.ENTRY_ID,Integer.toString(least_entry));
					UsageDetailsPeer.doDelete(crit);
				} //end of 'if'

				crit=new Criteria();
				crit.add(UsageDetailsPeer.USER_ID,Integer.toString(uid));
				crit.add(UsageDetailsPeer.LOGIN_TIME,date);
				crit.add(UsageDetailsPeer.LOGOUT_TIME,date);
				UsageDetailsPeer.doInsert(crit);
				
			}//try(2)
			catch(Exception e){
				data.setMessage("Cannot Login !! The error is :- "+e);
				page=Turbine.getConfiguration().getString("BrihaspatiLogin.vm");
				data.setScreen(page);
				
				log.info("this message would go to any facility configured to use the " + this.getClass().getName() + " Facility");
			}
			//If there is an error redirect to login page with a message"Cannot Login"
			AccessControlList acl = data.getACL();
			if( acl == null ){
				acl = TurbineSecurity.getACL( data.getUser() );
				data.getSession().setAttribute( AccessControlList.SESSION_KEY,(Object)acl );
				ErrorDumpUtil.ErrorLog("acl in mylogin action=="+acl);	
			}
			data.setACL(acl);
			data.save();

			
					boolean CL=CommonUtility.CleanSystem();
					if(!CL)
						data.addMessage("The Error in Clean System: see Common Utility");

					boolean AB=CommonUtility.IFLoginEntry(uid,date);

			/**
                          *Check the user for hint question when login at the first time.
                          */
                        try
			{
                                /**
                                *Check for the admin and the guest.
                                */
                                if(uid!=0 && uid!=1)
                                {
                                        crit=new Criteria();
                                        crit.add(UserConfigurationPeer.USER_ID,uid);
                                        crit.add(UserConfigurationPeer.QUESTION_ID,0);
                                        List check=UserConfigurationPeer.doSelect(crit);
                                        if((check.size()!=0))
                                        {
                                                setTemplate(data,"call,SelectHintQuestion.vm");
                                        }//end of 'if'
                                }//end of 'if'
                        }
                        catch(Exception e)
			{
				data.setMessage("Error in selecting hint question.Exception is :- "+e);
			}

		}//end try1		
		
	         /** In case of an error, get the appropriate error message from
	          *  TurbineResources.properties  
		  */
	 	catch ( TurbineSecurityException e ){
			//LangFile =data.getParameters().getString("Langfile");
			String msg1=MultilingualUtil.ConvertedString("t_msg",MultilingualUtil.LanguageSelectionForScreenMessage(lang));
			//page=Turbine.getConfiguration().getString("login.error");
			data.setMessage(msg1);
			data.setScreenTemplate("BrihaspatiLogin.vm");
			log.info("this message would go to any facility configured to use the " + this.getClass().getName() + " Facility"+e);
		}
		catch (NoSuchAlgorithmException e){
			data.setMessage("Could not find the required implementation");
			page=Turbine.getConfiguration().getString("screen.login");
			data.setScreenTemplate(page);
		}
		System.gc();
                //----------------code for the groupmanagement--------//
          //  CommonUtility.grpLeader();
        //----------------code for the groupmanagement--------//

	}
}
