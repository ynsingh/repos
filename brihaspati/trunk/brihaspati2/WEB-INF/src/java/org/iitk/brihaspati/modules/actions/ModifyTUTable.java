package org.iitk.brihaspati.modules.actions;
/*
 * @(#)ModifyTUTable.java	
 *
 *  Copyright (c)2011 ETRG,IIT Kanpur. 
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
import org.apache.velocity.context.Context;

import org.apache.turbine.util.RunData;
import org.apache.turbine.om.security.User;
import org.apache.turbine.modules.actions.VelocityAction;
import org.apache.turbine.services.security.TurbineSecurity;
import org.apache.torque.util.Criteria;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.lang.StringUtils;

import org.iitk.brihaspati.om.TurbineUserPeer;
import org.iitk.brihaspati.om.TurbineUser;
import org.iitk.brihaspati.modules.utils.UserUtil;
import org.iitk.brihaspati.modules.utils.StringUtil;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.iitk.brihaspati.modules.utils.EncryptionUtil;
import org.iitk.brihaspati.modules.utils.MultilingualUtil;
import java.util.Vector;

/**
 * This Action class update the language in the database table 
 * 'TURBINE_USER' when user select language other than english
 * This class also contains code for recording login statistics of 
 * users.This class is invoked whenever a user logs in to the system
 * @author <a href="mailto:singh_jaivir@rediffmail.com">Jaivir Singh</a>
 * @author <a href="mailto:nksinghiitk@gmail.com">Nagendra Kumar Singh</a>
 */

public class ModifyTUTable extends VelocityAction{
		private Log log = LogFactory.getLog(this.getClass());
	
//	public void doPerform( RunData data, Context context, String username, String password, String flag, String lang )
	public void doPerform( RunData data, Context context)
	{
		String userLanguage = "";
		
		/** Getting Language according to Selection of Language in lang Variable
                 *  Getting Property file  according to Selection of Language
		 */
		User user=null;
		Criteria crit = null;

                String flag=data.getParameters().getString("flag");
                String LangFile =data.getParameters().getString("Langfile","");
                String lang=data.getParameters().getString("lang","english");
		try{
		String username = data.getParameters().getString("username", "" );
		if(StringUtils.isBlank(username)) {
			//username="";
			username = data.getMessage();
		}
                ErrorDumpUtil.ErrorLog("The user name is (ModifyTUTable) "+username);
		String password = data.getParameters().getString("password", "" );

			String str=new String();
			List vec=null;
			int uid=UserUtil.getUID(username);
			try{
                                crit= new Criteria();
				crit.add(TurbineUserPeer.USER_ID,uid);
                                crit.addGroupByColumn(TurbineUserPeer.USER_LANG);
                                vec=TurbineUserPeer.doSelect(crit);
                        }
			catch(Exception e){
                                ErrorDumpUtil.ErrorLog("This Exception comes (in side First try) in the Login Utils-SetUserData Facility"+e);
                        }

                       TurbineUser element=(TurbineUser)vec.get(0);
			// Authenticate with local database of that user and get the object.
			if(StringUtils.isNotBlank(password)){
                                password=EncryptionUtil.createDigest("MD5",password);
                        }
                        else{
                                password=element.getPasswordValue().toString();
                        }



			// Authenticate the user and get the object.
			//password=EncryptionUtil.createDigest("MD5",password);
			user=TurbineSecurity.getAuthenticatedUser(username, password );

			// Store the user object.
			data.setUser(user);
			
			// Mark the user as being logged in.
			user.setHasLoggedIn(new Boolean(true));
			// get User ID 
//			int uid=UserUtil.getUID(username);
			Date date=new Date();
				// Set the last_login date in the database.
				user.updateLastLogin();
				vec=null;
				crit= new Criteria();
                                crit.add(TurbineUserPeer.USER_ID,uid);
                                crit.addGroupByColumn(TurbineUserPeer.USER_LANG);
                                vec=TurbineUserPeer.doSelect(crit);
				crit = null;
                                element=(TurbineUser)vec.get(0);
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
								try{	
        		                                        crit = new Criteria();
                		                                crit.add(TurbineUserPeer.USER_ID,uid);
                        		                        crit.and(TurbineUserPeer.USER_LANG,lang);
                                		                TurbineUserPeer.doUpdate(crit);
								//UpdateTUTable.UpdateLanguageField(uid,lang);
								userLanguage=lang;
								}catch(Exception e){data.setMessage("error in update language======"+e);}
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
		}catch(Exception ex){ErrorDumpUtil.ErrorLog("The error in action!!"+ex);}	
		System.gc();
       }
}
