package org.iitk.brihaspati.modules.actions;

/*
 * @(#)AOpenid.java	
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

import java.io.IOException;

import javax.servlet.http.HttpSession ;

import org.apache.velocity.context.Context;
import org.apache.torque.util.Criteria;
import org.apache.turbine.util.RunData;
import org.apache.turbine.modules.actions.VelocityAction;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.openid4java.message.AuthRequest;
import org.openid4java.consumer.ConsumerManager;
import org.openid4java.discovery.DiscoveryInformation;

import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;

/**
 * Action class for authenticating a user with open id and redirect with the brihaspati system
 * This class also contains code for recording login statistics of 
 * users.This class is invoked whenever a user logs in to the system
 *
 *  @author <a href="mailto:nksinghiitk@gmail.com">Nagendra Kumar Singh</a>
 *  @author http://sites.google.com/site/nksinghiitk/
 */

public class AOpenid extends VelocityAction{
	private Log log = LogFactory.getLog(this.getClass());

	private static ConsumerManager consumerManager;
	private DiscoveryInformation discoveryInformation =null;
	private boolean discoveryInformationStoredInSession;
        public static final String DISCOVERY_INFORMATION = "openid-disc";
       	HttpSession httpsess; 

	public void setDiscoveryInformation(DiscoveryInformation discoveryInformation) {
               setDiscoveryInformation(discoveryInformation, false);
        }
/**
 * This method is invoked upon when set discovery information in session
 * @param discoveryInformationdata DiscoveryInformation
 * @param storeInSession boolean
 */

        public void setDiscoveryInformation(DiscoveryInformation discoveryInformation, boolean storeInSession) {
                this.discoveryInformation = discoveryInformation;
                if (storeInSession) {
     //           ErrorDumpUtil.ErrorLog("OPENID set discovery info method---OPENID-----  "+httpsess);
                        httpsess.setAttribute(DISCOVERY_INFORMATION, discoveryInformation);
                        discoveryInformationStoredInSession = true;
                }
        }

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

                String flag=data.getParameters().getString("flag","");
                String lang=data.getParameters().getString("lang","english");
	//	String openidurl = data.getParameters().getString("openid_identifier", "http://202.141.40.217:8080/openid/" );
		String openidurl = data.getParameters().getString("openid_identifier", "http://hind.iitk.ernet.in:8080/openid/" );

		// configure the return_to URL where your application will receive
	        // the authentication responses from the OpenID provider
		String returnToUrl = RegistrationService.getReturnToUrl(data,context);
                ErrorDumpUtil.ErrorLog("Getting the return URL "+returnToUrl);
/*
                  // perform discovery on the user-supplied identifier
		DiscoveryInformation discovered = consumerManager.associate(discoveries);
		session.setAttribute("discovered", discovered);
		AuthRequest authReq = consumerManager.authenticate(discovered, returnToUrl);
		httpResp.sendRedirect(authReq.getDestinationUrl(true));
*/
		try{
        	        ErrorDumpUtil.ErrorLog("OPENID 1-----OPENID-----  "+openidurl);
			discoveryInformation = RegistrationService.performDiscoveryOnUserSuppliedIdentifier(openidurl);
        	        ErrorDumpUtil.ErrorLog("OPENID 2-----OPENID-----  "+discoveryInformation);
		}catch (Exception e) {
			String message = "Error occurred during discovery!";
			log.error(message, e);
			ErrorDumpUtil.ErrorLog("Error occured during discovery openid identifier"+ e);
			String url1=data.getServerScheme()+"://"+data.getServerName()+":"+data.getServerPort()+"/brihaspati/servlet/brihaspati/template/BrihaspatiLogin.vm?msg=Your Openid server is not accessible";
        	        ErrorDumpUtil.ErrorLog("After discovery function the url is  "+url1);
			try{
				data.setMessage("Your Openid server is not accessible");
				data.getResponse().sendRedirect(url1);
			}
			catch (Exception ex){
				ErrorDumpUtil.ErrorLog("After discovery function the url is exception "+ex);			
			}
			throw new RuntimeException(message, e);
		  }
                 // attempt to associate with the OpenID provider
                // and retrieve one service endpoint for authentication
         //        discovered = manager.associate(discoveries);
         	
       		httpsess = data.getSession();

               // store the discovery information in the user's session
                ErrorDumpUtil.ErrorLog("OPENID 4-----OPENID-----  "+httpsess);
               	setDiscoveryInformation(discoveryInformation, true);
                ErrorDumpUtil.ErrorLog("Here I set the Session Attribute");

		 AuthRequest authRequest = null;
                
                try {
                        // Create the AuthRequest object
                         authRequest = RegistrationService.createOpenIdAuthRequest(discoveryInformation, returnToUrl);
			// Now take the AuthRequest and forward it on to the OP

                } catch (Exception e) {
                        String message = "Exception occurred while building AuthRequest object!";
                        log.error(message, e);
			String url1=data.getServerScheme()+"://"+data.getServerName()+":"+data.getServerPort()+"/brihaspati/servlet/brihaspati/template/BrihaspatiLogin.vm?msg=Your Openid server is not accessible";
                        ErrorDumpUtil.ErrorLog("After discovery function the url is  "+url1);
                        try{
                                data.setMessage("Your Openid server is not accessible");
                                data.getResponse().sendRedirect(url1);
                        }
                        catch (Exception ex){
                                ErrorDumpUtil.ErrorLog("After discovery function the url is exception "+ex);
                        }

			//data.setScreenTemplate("BrihaspatiLogin.vm");
                        throw new RuntimeException(message, e);
                }
		

               try{
                   // GET HTTP-redirect to the OpenID Provider endpoint
                   // The only method supported in OpenID 1.x
                   // redirect-URL usually limited ~2048 bytes
	               //  getRequestCycle().setRedirect(false);
	               ErrorDumpUtil.ErrorLog("Here I send authenticate request to destination URL");
                      // httpResp.sendRedirect(authRequest.getDestinationUrl(true));
                       data.getResponse().sendRedirect(authRequest.getDestinationUrl(true));
                 }
                catch(IOException e)
                {
                 String message = "Error occured during redirect";
                 throw new RuntimeException (message,e);
                }

	}
        

}
