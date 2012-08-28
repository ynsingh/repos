package org.iitk.brihaspati.modules.actions;

/**
 * @(#)OpenIdRequest.java  
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
 */


import org.expressme.openid.Association;
import org.expressme.openid.Endpoint;
import org.expressme.openid.OpenIdManager;
import org.iitk.brihaspati.modules.utils.MultilingualUtil;
import javax.servlet.http.*;
import java.lang.reflect.Proxy;
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
 * Action class for authenticating a user with open id and redirect with the brihaspati system.
 * This class also contains code for recording login statistics of
 * users.This class is invoked whenever a user logs in to the system using Openid.
 * @author <a href="mailto:rpriyanka12@ymail.com">Priyanka Rawat</a>    
 */


public class OpenIdRequest extends VelocityAction
{

 	 private Log log = LogFactory.getLog(this.getClass());
	 static final long ONE_HOUR = 3600000L;
    	 static final long TWO_HOUR = ONE_HOUR * 2L;
    	 static final String ATTR_MAC = "openid_mac";
    	 static final String ATTR_ALIAS = "openid_alias";
	 HttpSession httpsess;
	 private String LangFile="";

/**
 * This method is invoked upon when user logs in
 * @param data RunData instance
 * @param context Context instance
 */

 public void doPerform( RunData data, Context context )
 {

        System.gc();
        Criteria crit = null;
        String userLanguage = "";
        String flag=data.getParameters().getString("flag","");
        String lang=data.getParameters().getString("lang","");
	LangFile = MultilingualUtil.LanguageSelectionForScreenMessage(lang);
        String openidurl = data.getParameters().getString("openid_identifier", "" );
        //ErrorDumpUtil.ErrorLog("Openid Provider = "+openidurl);
        HttpServletRequest httpReq = data.getRequest();
        HttpServletResponse httpResp = data.getResponse();
	//ErrorDumpUtil.ErrorLog("i'm here 2");
	
	OpenIdProcess openid = new OpenIdProcess(data);	
	//ErrorDumpUtil.ErrorLog("i'm here 1");
/**
 * Set properties for proxy,
 * since system is behind the firewall.
 */
	java.util.Properties props = System.getProperties();
	props.put("proxySet", "true");
	props.put("proxyHost", data.getServerName());
	props.put("proxyPort", data.getServerPort());
	//ErrorDumpUtil.ErrorLog("i'm here 2");



/**
 * Set the Openid Provider for whom request has to be sent
 * and then fetch the discovery document.
 */
	 Endpoint endpoint = null;
	if(openidurl.equalsIgnoreCase("Google") || openidurl.equalsIgnoreCase("Yahoo"))
	{
		//ErrorDumpUtil.ErrorLog("i'm here 3");
		try{
                        endpoint = openid.performDiscovery(openidurl);
                        //ErrorDumpUtil.ErrorLog("Endpoint -----  "+endpoint);
                }catch (Exception e) {
                        String message = "Error occurred during looking up endpoint!";
                        log.error(message, e);
                        final Throwable throwable = new Exception(e);
                	ErrorDumpUtil.ErrorLog("STACK TRACE   "+getStackTrace(throwable));
			//ErrorDumpUtil.ErrorLog("Error occured during looking up openid identifier*** "+ e);
			String str=MultilingualUtil.ConvertedString("openid_msg_1",LangFile);
                        String url1=data.getServerScheme()+"://"+data.getServerName()+":"+data.getServerPort()+"/brihaspati/servlet/brihaspati/template/BrihaspatiLogin.vm?msg="+str;
                        ErrorDumpUtil.ErrorLog("After look up function the url is  "+url1);
                        try{
                		data.setMessage(str);
		                //data.setMessage("Your Openid server is not accessible");
                                data.getResponse().sendRedirect(url1);
                        }
                        catch (Exception ex){
                                ErrorDumpUtil.ErrorLog("After look up function the url is exception "+ex);
        			throw new RuntimeException (message,ex);
	                }
                 }

	}
	else
	{
		String str=MultilingualUtil.ConvertedString("openid_msg_2",LangFile);
		try{
                   	data.setMessage(str);
                        data.getResponse().sendRedirect(data.getServerScheme()+"://"+data.getServerName()+":"+data.getServerPort()+"/brihaspati/servlet/brihaspati/template/BrihaspatiLogin.vm?msg="+str);
                   }
                   catch (Exception ex){
                  	String msg = "ERROR IN OPENID ";
                        ErrorDumpUtil.ErrorLog("ERROR IN JOPENID. USER COULD NOT LOGIN "+ex);
                        throw new RuntimeException(msg,ex);
                    }
	}



/**
 * Get Openid Assocation instance. 
 */
	Association association = null;
	try
	{
		association = openid.setupAssociation(endpoint);
	}
	catch(Exception e){
               String message = "Error occured during association!";
               log.error(message, e);
	       final Throwable throwable = new Exception(e);
               ErrorDumpUtil.ErrorLog("STACK TRACE   "+getStackTrace(throwable));
		String str=MultilingualUtil.ConvertedString("openid_msg_1",LangFile);
               String url1=data.getServerScheme()+"://"+data.getServerName()+":"+data.getServerPort()+"/brihaspati/servlet/brihaspati/template/BrihaspatiLogin.vm?msg="+str;
                try{
                      data.setMessage(str);
                      data.getResponse().sendRedirect(url1);
                }
                catch (Exception ex){
                       ErrorDumpUtil.ErrorLog("After association function exception "+ex);
			throw new RuntimeException (message,ex);
                }
        
        }

	
/**
 * Get raw MAC key as bytes,
 * get extension alias of Openid Provider's end point
 * and store in HttpSeesion's instance. 
 */
	byte[] mac_key = association.getRawMacKey();
        String aliaS =  endpoint.getAlias();
	setSessionData(data, mac_key,aliaS);

/**
 * Get the Authentication Url of the Openid Provider
 */
	String url ="";
	try{
		url = openid.getAuthUrl(endpoint, association);
        }
        catch(Exception e)
        {
		String message = "Exception occurred while setting up association!";
               log.error(message, e);
               final Throwable throwable = new Exception(e);
               ErrorDumpUtil.ErrorLog("STACK TRACE   "+getStackTrace(throwable));
		String str=MultilingualUtil.ConvertedString("openid_msg_1",LangFile);
               String url1=data.getServerScheme()+"://"+data.getServerName()+":"+data.getServerPort()+"/brihaspati/servlet/brihaspati/template/BrihaspatiLogin.vm?msg="+str;
               ErrorDumpUtil.ErrorLog("After association function the url is  "+url1);
                try{
                      data.setMessage(str);
                      data.getResponse().sendRedirect(url1);
                }
                catch (Exception ex){
                       ErrorDumpUtil.ErrorLog("After association function the url is exception "+ex);
                	throw new RuntimeException (message,ex);
		}
        }

	

/**
 * Redirect user to the selected Openid Provider
 */	
	try{
		data.getResponse().sendRedirect(url);
	}
	catch(Exception e)
	{
		String message = "An error occured during redirect!";
               log.error(message, e);
               final Throwable throwable = new Exception(e);
               ErrorDumpUtil.ErrorLog("STACK TRACE   "+getStackTrace(throwable));
		String str=MultilingualUtil.ConvertedString("openid_msg_1",LangFile);
               String url1=data.getServerScheme()+"://"+data.getServerName()+":"+data.getServerPort()+"/brihaspati/servlet/brihaspati/template/BrihaspatiLogin.vm?msg="+str;
               ErrorDumpUtil.ErrorLog("After association function the url is  "+url1);
                try{
                      data.setMessage(str);
                      data.getResponse().sendRedirect(url1);
                }
                catch (Exception ex){
                       ErrorDumpUtil.ErrorLog("After association function the url is exception "+ex);
			throw new RuntimeException (message,ex);
                }
	}
 }//method

public static String getStackTrace(Throwable throwable) 
{
             Writer writer = new StringWriter();
             PrintWriter printWriter = new PrintWriter(writer);
             throwable.printStackTrace(printWriter);
             return writer.toString();
}


/**
 * This method is invoked to set association information in session
 * @param macKey String
 * @param alias String
 */
public void setSessionData(RunData data, byte[] macKey, String alias)
{
	httpsess = data.getSession();
        httpsess.setAttribute(ATTR_MAC, macKey);
        httpsess.setAttribute(ATTR_ALIAS, alias);
}

}//class





