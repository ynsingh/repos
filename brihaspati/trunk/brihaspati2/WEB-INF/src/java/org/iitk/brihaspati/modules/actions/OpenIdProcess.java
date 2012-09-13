package org.iitk.brihaspati.modules.actions;

/**
 * @(#)OpenIdProcess.java  
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
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT
 *  OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR 
 *  BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 *  WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE 
 *  OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, 
 *  EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */


import org.expressme.openid.OpenIdManager;
import org.expressme.openid.Authentication;
import org.expressme.openid.Endpoint;
import org.expressme.openid.Association;
import javax.servlet.http.*;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.turbine.util.security.AccessControlList;
import org.apache.turbine.modules.actions.VelocityAction;
import org.apache.turbine.services.security.TurbineSecurity;
import org.expressme.openid.OpenIdException;

import org.iitk.brihaspati.modules.utils.UserUtil;
import org.iitk.brihaspati.modules.utils.LoginUtils;
import org.iitk.brihaspati.modules.utils.CommonUtility;
import org.apache.torque.util.Criteria;
import org.iitk.brihaspati.om.Openid;
import org.iitk.brihaspati.om.OpenidPeer;
import java.util.Iterator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import org.apache.velocity.context.Context;
import org.apache.torque.util.Criteria;
import org.apache.turbine.util.RunData;
import org.apache.turbine.modules.actions.VelocityAction;
import java.io.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;

/**
 * This class performs task for processing Openid request and response.  
 * @author <a href="mailto:rpriyanka12@ymail.com">Priyanka Rawat</a>  
 */

public class OpenIdProcess
{
	private Log log = LogFactory.getLog(OpenIdProcess.class);
	private RunData data;
	static final long ONE_HOUR = 3600000L;
        static final long TWO_HOUR = ONE_HOUR * 2L;
	Criteria crit = null;
	Criteria criteria = null;

	public OpenIdProcess(RunData data)
	{
		this.data=data;
	}

	public OpenIdProcess(){}	

	/**
         * Processes the returned information from an authentication request
         * from the OP.
         * @param authentication Authentication An instance of Authentication class
         *  that contains all the user's data fetched from the Openid response.
         *  Only that data will be present here, that has been agreed by user
         *  for sharing.
         * @return UserData 
         */

	public UserData setUserData(Authentication authentication)
	{
		UserData user = new UserData();
		String identity = authentication.getIdentity();
		user.setIdentity(identity);
		String email =authentication.getEmail();
		user.setEmail(email);
		String fullname = authentication.getFullname();
		user.setFullname(fullname);
		String firstname = authentication.getFirstname();
		user.setFirstname(firstname);
		String lastname = authentication.getLastname();
		user.setLastname(lastname);
		String language = authentication.getLanguage();
		user.setLanguage(language);
		String gender = authentication.getGender();
		user.setGender(gender);
        	ErrorDumpUtil.ErrorLog("In setUserData of OpenIdProcess email =  "+email);
		
		return user;
	}//method


	/**
 	 * Set the Openid Provider for whom request has to be sent.
 	 * @param openidurl String OpenId Provider's name
 	 *  whose discovery endpoint url is to be fetched.
 	 * @return Endpoint Provider's enpoint url on which
 	 *  Discovery will be performed.
 	 */

	public Endpoint performDiscovery(String openidurl)
	{
		Endpoint endpoint = null;
		if(openidurl.equalsIgnoreCase("Google"))
        	{
                	try{
                        	endpoint = createOpenIdManagerObject().lookupEndpoint("Google");
                        	ErrorDumpUtil.ErrorLog("OPENID 2-----OPENID-----  "+endpoint);
        			//return endpoint;
	        	}catch (Exception e) {
                  		final Throwable throwable = new Exception(e);
		                ErrorDumpUtil.ErrorLog("STACK TRACE in performDiscovery of OpenIdProcess  "+getStackTrace(throwable));
		                throw new RuntimeException ("Error in discovery",e);

			}
        	}
        	if(openidurl.equalsIgnoreCase("Yahoo"))
        	{
                	try{
                        	endpoint = createOpenIdManagerObject().lookupEndpoint("Yahoo");
                        	ErrorDumpUtil.ErrorLog("OPENID 3-----OPENID-----  "+endpoint);
				//return endpoint;
                	}catch (Exception e) {
		                final Throwable throwable = new Exception(e);
               		 	ErrorDumpUtil.ErrorLog("STACK TRACE in performDiscovery of OpenIdProcess  "+getStackTrace(throwable));
                		throw new RuntimeException ("Error in discovery",e);

			}

        	}
		
			return endpoint;
		
	}//method


/**
 * Assocation between RP and OP is established,
 * and will be cached in memory for a certain time. 
 * @param endpoint Endpoint endpoint url
 * @return Association Will consist of association handle
 *  set up between Openid Provider and Brihaspati
 */

public Association setupAssociation(Endpoint endpoint)
{
	Association association = null;
	try
        {
                association = createOpenIdManagerObject().lookupAssociation(endpoint);
		//ErrorDumpUtil.ErrorLog("OPENID 4-----OPENID-----  "+association);
		//return association;
        }
        catch (Exception e) {
    		String str = ("An error occured while fetching Authentication Url!!");
                final Throwable throwable = new Exception(e);
                ErrorDumpUtil.ErrorLog("STACK TRACE in setupAssociation of OpenIdProcess  "+getStackTrace(throwable));
                //throw new RuntimeException (str,e);
        }

	return association;
}//method


/**
 * Authentication Url for sending authentication
 * request to Openid Provider is fetched. 
 * The url consist of all the necessary parameters
 * required for authentication request.   
 * @param endpoint Endpoint endpoint url
 * @return Association Will consist of association handle
 *  set up between Openid Provider and Brihaspati
 */

public String getAuthUrl(Endpoint endpoint,Association association)
{
	String url = "";
	try{
                url = createOpenIdManagerObject().getAuthenticationUrl(endpoint, association);
        	//ErrorDumpUtil.ErrorLog("OPENID 5-----OPENID-----  "+url);
	}
        catch(Exception e)
        {
                String str = ("An error occured while fetching Authentication Url!!");
                final Throwable throwable = new Exception(e);
                ErrorDumpUtil.ErrorLog("STACK TRACE in setAuthUrl of OpenIdProcess  "+getStackTrace(throwable));
                //throw new RuntimeException (str,e);
        }

	return url;
}//method

private OpenIdManager manager;
/**
 * Creates OpenIdManager object, to carry out
 * all OpenId operations.
 * @return OpenIdManager - The OpenIdManager object that handles
 * communication with the jopenid API.
 *
 */

public OpenIdManager createOpenIdManagerObject()
{
        if(manager == null)
        {
		manager = new OpenIdManager(); 
               manager.setReturnTo(data.getServerScheme()+"://"+data.getServerName()+":"+data.getServerPort()+"/brihaspati/servlet/brihaspati/action/OpenIdResponse?is_return=true");
                manager.setRealm(data.getServerScheme()+"://"+data.getServerName()+":"+data.getServerPort()+"/brihaspati/servlet/brihaspati");
                manager.setTimeOut(10000);
        }
        //ErrorDumpUtil.ErrorLog("i'm here 5");
        return manager;
}//method

public String getStackTrace(Throwable throwable)
{
             Writer writer = new StringWriter();
             PrintWriter printWriter = new PrintWriter(writer);
             throwable.printStackTrace(printWriter);
             return writer.toString();
}


/**
 * This method checks response_nonce to prevent replay-attack.
 * @param String nonce Nonce fetched from HTTP request
 * @param String opurl OpenId Provider from whom 
 *  request has been received.
 */

public void checkNonce(String nonce,String opurl)
{
	if (nonce==null || nonce.length()<20)
            throw new OpenIdException("Nonce size null or less than 20. Verify failed.");
	if (opurl==null ||opurl.equals(null))
	    throw new OpenIdException("OpenId Provider's name has value null. Verify failed.");
        long nonceTime = getNonceTime(nonce);
        long diff = System.currentTimeMillis() - nonceTime;
        if (diff < 0)
            diff = (-diff);
        if (diff > ONE_HOUR)
            throw new OpenIdException("Bad nonce time.");
	//removeNonce();	
	boolean set = isNonceExist(nonce,opurl);
	ErrorDumpUtil.ErrorLog("isNonceExist in checkNonce() "+set);
	if (set)
            throw new OpenIdException("Verify nonce failed.");
	else
            storeNonce(nonce, nonceTime, opurl);	
}

public long getNonceTime(String nonce) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                    .parse(nonce.substring(0, 19) + "+0000")
                    .getTime();
        }
        catch(ParseException e) {
		final Throwable throwable = new Exception(e);
                ErrorDumpUtil.ErrorLog("STACK TRACE of getNonceTime of OpenIdProcess  "+getStackTrace(throwable));
                throw new OpenIdException("Bad nonce time.");
        }
}


/**
 * This method checks whether nonce for "this"
 * openid provider exists in database,
 * existence of nonce in database specifies
 * a replay attack, and absence, a new request.
 * It returns true if nonce already exist
 * in database, and false otherwise. 
 * @param String nonce Nonce fetched from HTTP request
 * @param String opurl OpenId Provider from whom 
 *  request has been received.
 * @return boolean 
 */

protected boolean isNonceExist(String nonce,String opurl)
{
	String stored_nonce = null;
	try{
		ErrorDumpUtil.ErrorLog("Inside isNonceExist() in OpenIdProcess ");
		crit = new Criteria();
		ErrorDumpUtil.ErrorLog("111111");
                crit.add(OpenidPeer.NONCE,nonce);
		ErrorDumpUtil.ErrorLog("2222");
		crit.add(OpenidPeer.PROVIDER,opurl);
		ErrorDumpUtil.ErrorLog("333333");
                List list = OpenidPeer.doSelect(crit);
		//int i = list.size();
		ErrorDumpUtil.ErrorLog("4444");
		if(list.size()>0)
		{
			ErrorDumpUtil.ErrorLog("555555");
                	stored_nonce =((Openid)list.get(0)).getNonce();
		}
		ErrorDumpUtil.ErrorLog("666666 "+stored_nonce);	
		//if (stored_nonce.equals(null) || stored_nonce == null)
		if(stored_nonce!=null)
		{
			ErrorDumpUtil.ErrorLog("7777");
			return true;
		}
		else
		{
		//	return true;
			ErrorDumpUtil.ErrorLog("88888");
			return false;
		}
		//ErrorDumpUtil.ErrorLog("9999999");
	}
	catch(Exception e)
	{
		log.error("Error while nonce checking",e);
		final Throwable throwable = new Exception(e);
                ErrorDumpUtil.ErrorLog("STACK TRACE of isNonceExist of OpenIdProcess  "+getStackTrace(throwable));
		throw new OpenIdException ("Error while nonce checking");
	}
	//return false;		
}

/**
 * This method stores the fresh nonce in database,
 * the nonce expiry time has been set to one hour.
 */

private void storeNonce(String nonce, long nonceTime, String provider)
{
	try{
                crit = new Criteria();
                crit.add(OpenidPeer.NONCE,nonce);
                crit.add(OpenidPeer.PROVIDER,provider);
		crit.add(OpenidPeer.TO_DATE,nonceTime);
                OpenidPeer.doInsert(crit);
		//ErrorDumpUtil.ErrorLog("Inside storeNonce()");
        }
        catch(Exception e)
        {
//		ErrorDumpUtil.ErrorLog("Error in storeNonce() method of OpenIdProcess"+e);
		final Throwable throwable = new Exception(e);
                ErrorDumpUtil.ErrorLog("STACK TRACE of storeNonce() of OpenIdProcess  "+getStackTrace(throwable));
                throw new OpenIdException ("Error while inserting nonce in database");
        }
}

}//class
