package org.iitk.brihaspati.modules.utils;

/*@(#)LoginUtils.java
 *  Copyright (c) 2011, 2013 ETRG,IIT Kanpur. http://www.iitk.ac.in/
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
import java.util.Hashtable;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.DirContext;
import javax.naming.NamingException;
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
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
//import org.apache.turbine.Turbine;
import org.apache.turbine.services.servlet.TurbineServlet;
import org.apache.turbine.modules.actions.VelocityAction;
/**
 * This class is used for call the method in mylogin 
 * like Create index for Search, Clean the system 
 * 
 * @author <a href="mailto:nksinghiitk@gmail.com">Nagendra Kumar Singh</a>
 * @author <a href="mailto:vipulk@iitk.ac.in">Vipul Kumar Pal</a>
 * @modified date : 07-10-2015 (Seemanti Shukla)
 * @version 1.0
 * @since 1.0
 */
public class LoginUtils{
	/** 
          *  Get the session if exist then remove and create new session
          *  @param username String
          *  @return 
          **/
	private static Log log = LogFactory.getLog(LoginUtils.class);
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
							break;
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
	 *  @param lcat String
	 *  @param flag String
	 *  @param lang String
	 *  @param data RunData
	 *  @return String
	 **/
	public static String SetUserData(String username, String password, String lcat, String flag, String lang, RunData data){
		User user=null;
        	String userLanguage = "";
		String page=new String();
		Criteria crit = null;
		String msg = "";
                String[] temp=TurbineServlet.getServerName().split("\\.");
                if(temp[0].equals("172") || temp[0].equals("10") || temp[0].equals("192")){
                	msg = "Behind Firewall";
                }else{
                	msg = "Public IP address";
                }
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
				try{
				String confpath=data.getServletContext().getRealPath("/WEB-INF")+"/conf"+"/"+"Admin.properties";
			//	String authm = AdminProperties.getValue(confpath,"brihaspati.admin.authmethod.value");
				if(StringUtils.isNotBlank(lcat)){
					//add method for ldap
					try{
						// the base and ldap url getting from properties
						String base=AdminProperties.getValue(confpath,"brihaspati.admin.ldapbase.value"); // "OU=SOU,DC=example,DC=com";
						String ldapURL=AdminProperties.getValue(confpath,"brihaspati.admin.ldapurl.value"); // "ldap://abc.example.com:389";
						String unm=StringUtils.substringBefore(username,"@");
						if((StringUtils.isNotBlank(base))&&(StringUtils.isNotBlank(ldapURL))){
							//String dn = "cn=" + username + "," + base;
							String dn = "uid="+unm+ ", OU=" +lcat+","+base;
							// Set up the environment for creating the initial context
							Hashtable<String, String> env = new Hashtable<String, String>();
	//						javax.naming.Context cntxt;
							env.put(javax.naming.Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
							env.put(javax.naming.Context.PROVIDER_URL, ldapURL);
							env.put(javax.naming.Context.SECURITY_AUTHENTICATION, "simple");
							env.put(javax.naming.Context.SECURITY_PRINCIPAL, dn); 
							env.put(javax.naming.Context.SECURITY_CREDENTIALS, password);
					
							// Create the initial context
							DirContext ctx = new InitialDirContext(env);
							boolean result = ctx != null;
							if(ctx != null)
								ctx.close();
							password="";
						}
						else{
							ErrorDumpUtil.ErrorLog("The auth method is LDAP but ldap url amd base are not present in the Login Utils-SetUserData Facility\n", TurbineServlet.getRealPath("/logs")+"/LdapLog.txt");
						}
					}
					catch (NamingException namEx) {
						ErrorDumpUtil.ErrorLog("This is ldap Exception comes (in side First try) in the Login Utils-SetUserData Facility"+namEx+"\n", TurbineServlet.getRealPath("/logs")+"/LdapLog.txt");
          				//	return false;
	        			} 
					catch (Exception e)
					{
						ErrorDumpUtil.ErrorLog("This is ldap Exception comes (in side First try) in the Login Utils-SetUserData Facility"+e+"\n", TurbineServlet.getRealPath("/logs")+"/LdapLog.txt");
						//return false;
					}
				}
			
				}catch (Exception ex){ErrorDumpUtil.ErrorLog("This is reading property file Exception comes (in side First try) in the Login Utils-SetUserData Facility"+ex+"\n", TurbineServlet.getRealPath("/logs")+"/LdapLog.txt");}

                       		TurbineUser element=(TurbineUser)vec.get(0);
				// make a copy of original password for SHA1 encription 
				String password1=password;
				// Authenticate with local database (Brihaspati Database) of that user and get the object.
				// This is used for SHA1 hash
				if(StringUtils.isNotEmpty(password)){
					password=EncryptionUtil.createDigest("SHA1",password);
				}
				else{
					password=element.getPasswordValue().toString();
				}
		/* The above piece will allow one to login when if just space is being used in the password field.
		*/		try{
	                        	user=TurbineSecurity.getAuthenticatedUser(username, password );
				}
				catch (TurbineSecurityException eu){ErrorDumpUtil.ErrorLog(" The value of User ( "+username+ " ) with SHA1 is "+user +" and password is "+ password +" and Exception is "+eu, TurbineServlet.getRealPath("/logs")+"/Loginauth.txt"); }

				//This is used for MD5 hash
				if((user == null)||(user.equals(null))){
					if(StringUtils.isNotEmpty(password1)){
						password=EncryptionUtil.createDigest("MD5",password1);
					}
	                        	user=TurbineSecurity.getAuthenticatedUser(username, password );
				}
				//Get the session if exist then remove and create new session
				CheckSession(username);
				// Store the user object.
				data.setUser(user);
				// Mark the user as being logged in.
				user.setHasLoggedIn(new Boolean(true));
        	                Date date=new Date();
				/**
                                  *create log file for user login
                                  *Parameters are user name, login time and IP address
                                  */
				log.info("User Name --> "+username + "| Succesfull Login | Login Time --> "+date +"| IP Address --> "+data.getRemoteAddr() +"/"+msg);
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
			Date dt=new Date();
			log.info("User Name --> "+username + "| Unsuccesfull Login Attempt | Login Time --> "+dt +"| IP Address --> "+data.getRemoteAddr() +"/"+msg);
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
			 * Check whether the uid is neither admin nor guest and thus only the SetHintQues() method should be executed.
			 */
			if(uid!=0 && uid!=1)
                                {
                                        Criteria crit=new Criteria();
                                        crit.add(UserConfigurationPeer.USER_ID,uid);
                                        crit.add(UserConfigurationPeer.QUESTION_ID,0);//Select only those rows whose QUESTION_ID=0 as these are not yet saved the ANSWER.
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
	/**
	*Method for Check Users PassWord Date is Today.
	**/
	public static void getChangePasswordtemp(Date date,int uid,RunData data)
	{
		try{
			if(uid!=0 && uid!=1)
			{
				Criteria crit=new Criteria();
				crit.add(UserPrefPeer.USER_ID,uid);	
				List v=UserPrefPeer.doSelect(crit);
				UserPref element=(UserPref)v.get(0);
				Date Expirydate=element.getPasswordExpiry();
				if(Expirydate!=null)
				{
					if(Expirydate.equals(date) || Expirydate.before(date))
                                	{
						
                                        	data.setScreenTemplate("call,UserMgmt_User,changePassword.vm");
                                	}
				}
				else{	
					Date expdate=UserManagement.getExpirydate();
					crit=new Criteria();
					crit.add(UserPrefPeer.USER_ID,uid);
					crit.add(UserPrefPeer.PASSWORD_EXPIRY,expdate);
					UserPrefPeer.doUpdate(crit);
				}

			}
		}catch(Exception e)
			{
			data.setMessage("Error in get changePassword template is :- "+e);
			}

	}
   /**
    * Method for checking if Admin.properties file is holding the mandatory fields or not.
    * if all necessary parameters are there , return true
    * else return false.
    **/
   public static boolean checkAdminFileEmpty(String path)
   {  
      boolean check = false;
      try 
      {  
         String PassExp = null;
         String mailSpoolingExpiry = null;
         String mailResendTime = null;
         String iquota = null;
         String hdir = null;
         String ldap_cate = null;
         String ldap_base = null;
         String ldap_url = null;
         String twtexp = null;
         String normal_traffic = null;
         String high_traffic = null;
         PassExp = AdminProperties.getValue(path,"brihaspati.admin.passwordExpiry");
         mailSpoolingExpiry = AdminProperties.getValue(path,"brihaspati.admin.mailSpoolingExpiry.value");
         mailResendTime = AdminProperties.getValue(path,"brihaspati.admin.spoolMailResendTime.value");
         iquota = AdminProperties.getValue(path,"brihaspati.user.iquota.value");
         hdir = AdminProperties.getValue(path,"brihaspati.home.dir.value");
         ldap_cate = AdminProperties.getValue(path,"brihaspati.admin.ldapcate.value");
         ldap_base = AdminProperties.getValue(path,"brihaspati.admin.ldapbase.value");
         ldap_url = AdminProperties.getValue(path,"brihaspati.admin.ldapurl.value");
         twtexp = AdminProperties.getValue(path,"brihaspati.admin.twtexpiry.value");
         normal_traffic = AdminProperties.getValue(path,"brihaspati.admin.normalTraffic.value");
         high_traffic = AdminProperties.getValue(path,"brihaspati.admin.highTraffic.value");
         if(PassExp != null)
            check = false;
         if(mailSpoolingExpiry != null)
            check = false;
         if(mailResendTime != null)
            check =  false;
         if(iquota != null)
            check = false;
         if(hdir != null)
            check = false;
         if(ldap_cate != null)
            check = false;
         if(ldap_base !=  null)
            check = false;
         if(ldap_url !=  null)
            check = false;
         if(twtexp !=  null)
            check = false;
         if(normal_traffic != null)
            check = false;
         if(high_traffic !=  null)
            check = false;
         else   
            check = true;
      }
      catch(Exception e)
      {
         ErrorDumpUtil.ErrorLog("Error in getting values from Admin.properties file. "+e);
      }  
      return check ;
   }

}//end of class
