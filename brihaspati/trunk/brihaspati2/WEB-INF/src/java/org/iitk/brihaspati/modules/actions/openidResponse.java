package org.iitk.brihaspati.modules.actions;
/*
 * @(#)openidResponse.java        
 *
 *  Copyright (c) 2011 ETRG,IIT Kanpur. 
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
import java.util.Map;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.velocity.context.Context;
import org.apache.torque.util.Criteria;
import org.apache.turbine.util.RunData;
import org.apache.turbine.util.security.AccessControlList;
import org.apache.turbine.modules.actions.VelocityAction;
import org.apache.turbine.services.security.TurbineSecurity;

import org.openid4java.OpenIDException;
import org.openid4java.discovery.Identifier;
import org.openid4java.discovery.DiscoveryInformation;
import org.openid4java.consumer.ConsumerManager;
import org.openid4java.consumer.ConsumerException;
import org.openid4java.consumer.VerificationResult;
import org.openid4java.consumer.InMemoryNonceVerifier;
import org.openid4java.consumer.InMemoryConsumerAssociationStore;
import org.openid4java.message.AuthSuccess;
import org.openid4java.message.ax.AxMessage;
import org.openid4java.message.ParameterList;
import org.openid4java.message.ax.FetchResponse;
 
import org.iitk.brihaspati.modules.utils.UserUtil;
import org.iitk.brihaspati.modules.utils.LoginUtils;
import org.iitk.brihaspati.modules.utils.CommonUtility;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;

/** Action file to receive authentication response from OpenID Provider
 *  and verify the response.
 *  Most of this code modeled after ConsumerServlet, part of the openid4java 
 *  sample code available at 
 *  http://code.google.com/p/openid4java/wiki/SampleConsumer.
 *   
 *  @author Nagendra Kumar singh
 *  @author http://sites.google.com/site/nksinghiitk/
 *
 */
public  class openidResponse extends VelocityAction{

 private Log log = LogFactory.getLog(this.getClass());

 private static ConsumerManager consumerManager;
        /**
         * Retrieves an instance of the ConsumerManager object. It is static
         * (see note in Class-level JavaDoc comments above) because openid4java
         * likes it that way.
         *
         * Note: if you are planning to debug the code, set the lifespan parameter
         * of the InMemoryNonceVerifier high enough to outlive your debug cycle, or
         * you may notice Nonce Verification errors. Depending on where you are
         * debugging, this might pose an artificial problem for you (of your own
         * making) that has nothing to do with either your code or openid4java.
         *
         * @return ConsumerManager - The ConsumerManager object that handles
         *  communication with the openid4java API.
         */
        private ConsumerManager getConsumerManager() {
                try {
                        if (consumerManager == null) {
                                consumerManager = new ConsumerManager();
                                consumerManager.setAssociations(new InMemoryConsumerAssociationStore());
                                consumerManager.setNonceVerifier(new InMemoryNonceVerifier(10000));
                        }
                } catch (ConsumerException e) {
                        String message = "Exception creating ConsumerManager!";
                        log.error(message, e);
                        throw new RuntimeException(message, e);
                }
                return consumerManager;
        }

/**
 * This method is invoked upon when user logging in
 * @param data RunData instance
 * @param context Context instance
 */
	public void doPerform( RunData data, Context context )
        {
		String page=new String();
                System.gc();
                Criteria crit = null;
                String userLanguage = "";
		ErrorDumpUtil.ErrorLog("I am in open id responce");
		HttpServletRequest httpReq=data.getRequest(); 

		try {    
			// extract the parameters from the authentication response
			// (which comes in as a HTTP request from the OpenID provider)
			Map pmap = (Map)httpReq.getParameterMap();  
			ParameterList response = new ParameterList(pmap);
			// retrieve the previously stored discovery information
			HttpSession https = httpReq.getSession();
		       	DiscoveryInformation discovered = (DiscoveryInformation)https.getAttribute("openid-disc");

			ErrorDumpUtil.ErrorLog("I open id responce = discovered "+discovered);
          
    			// extract the receiving URL from the HTTP request
       			StringBuffer receivingURL = httpReq.getRequestURL();
		ErrorDumpUtil.ErrorLog("I am in open id responce receiving url "+receivingURL);
       			String queryString = httpReq.getQueryString();
		ErrorDumpUtil.ErrorLog("I am in open id responce query string "+ queryString);
      // ErrorDumpUtil.ErrorLog("OOOOOOOOOOOOOOOOOOOOOOOO  "+queryString);

       			if(queryString != null)
         		{
          			receivingURL.append("?").append(queryString);
         		}

		ErrorDumpUtil.ErrorLog("Add receiving URL and Query String "+receivingURL);
    			// verify the response; ConsumerManager needs to be the same
    			// (static) instance used to place the authentication request
        		VerificationResult verification = getConsumerManager().verify( receivingURL.toString(),response, discovered);
		ErrorDumpUtil.ErrorLog("I am in open id responce after verification "+verification);
   
    			// examine the verification result and extract the verified identifier
        		Identifier verified = verification.getVerifiedId();
		ErrorDumpUtil.ErrorLog("I am in open id responce after verifi "+verified);
				String emal="";
				String password="";
				String flag=data.getParameters().getString("flag","true");
				String lang=data.getParameters().getString("lang","english");
        		if(verified != null)
        		{
        			// retrieving the successful authentication response
         			AuthSuccess authSuccess = (AuthSuccess) verification.getAuthResponse();
		ErrorDumpUtil.ErrorLog("I am in open id responce after authsucess"+authSuccess);

           			AxMessage axmsg = new AxMessage();
         			if(authSuccess.hasExtension(axmsg.getTypeUri()))
         			{

           				//  Getting the Type URI that identifies the Attribute Exchange extension.
           				FetchResponse fetchResp = (FetchResponse) authSuccess.getExtension(axmsg.getTypeUri());
           				List emails = fetchResp.getAttributeValues("email");
           				// Email of user fetched
           				emal = (String) emails.get(0);
		ErrorDumpUtil.ErrorLog("I am in open id responce and get email"+ emal);
         			}
        		}
			else{
		ErrorDumpUtil.ErrorLog("I am in open id responce else part");
				String nme=data.getParameters().getString("openid.sreg.fullname");
				emal=data.getParameters().getString("openid.sreg.email");
				String pcode=data.getParameters().getString("openid.sreg.postcode");
				String dob=data.getParameters().getString("openid.sreg.dob");
			}
		ErrorDumpUtil.ErrorLog("I am in open id responce else part"+ emal);
				
				int cmpid=-1;
                        // get User ID
                                int uid=UserUtil.getUID(emal);
                                boolean Result= uid == cmpid;
                                ErrorDumpUtil.ErrorLog("This Exception comes (in side First try) in the user id" +uid +" "+ Result);
                                if(Result){
					String url1=data.getServerScheme()+"://"+data.getServerName()+":"+data.getServerPort()+"/brihaspati/servlet/brihaspati/template/BrihaspatiLogin.vm";
					ErrorDumpUtil.ErrorLog("I am in result uid compare second "+url1);
					try{
						data.setMessage("You are not registered in Brihaspati LMS");
						data.getResponse().sendRedirect(data.getServerScheme()+"://"+data.getServerName()+":"+data.getServerPort()+"/brihaspati/servlet/brihaspati/template/BrihaspatiLogin.vm?msg=You are not registered in Brihaspati LMS"); 
					}
		                        catch (Exception ex){
                        		        ErrorDumpUtil.ErrorLog("User not registered in Brihaspati LMS "+ex);
        		                }
	
                                }
                        
				LoginUtils.CheckSession(emal);		
				Date date=new Date();
		//                lang=LoginUtils.SetUserData(emal, password, flag, lang, data);
	//			user.setTemp("emal",emal);
				//context.put("emal",emal);	
				data.setMessage(emal);
		//		ModifyTUTable mtut=new ModifyTUTable();
		  //              mtut.doPerform(data,context);
				lang=LoginUtils.SetUserData(username, password, flag, lang, data);
				data.unsetMessage(); 
                		context.put("lang",lang);
		ErrorDumpUtil.ErrorLog("I am in open id responce ande part set user data");
                                LoginUtils.UpdateUsageData(uid);
				
				try{
                        		AccessControlList acl = data.getACL();
		                        if( acl == null ){
                	                	acl = TurbineSecurity.getACL( data.getUser() );
                        		        data.getSession().setAttribute( AccessControlList.SESSION_KEY,(Object)acl );
                        		}
		ErrorDumpUtil.ErrorLog("I am in open id responce else part act setting");
		                        data.setACL(acl);
                		        data.save();
                        	}
	                        catch(Exception ex){data.setMessage("Error in setting Access rules :- "+ex);}

                                boolean CL=CommonUtility.CleanSystem();
                                if(!CL)
                                        data.addMessage("The Error in Clean System: see Common Utility");
                                boolean AB=CommonUtility.IFLoginEntry(uid,date);
				LoginUtils.SetHintQues(uid, data);
		ErrorDumpUtil.ErrorLog("I am in open id responce else part hint question");
				System.gc();
			
      		}//try 1
      		catch(OpenIDException e){ErrorDumpUtil.ErrorLog("The error  in open id responce");}
    } //method

}//class
