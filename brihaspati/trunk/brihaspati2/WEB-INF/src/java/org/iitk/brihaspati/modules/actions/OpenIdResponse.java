package org.iitk.brihaspati.modules.actions;

/**
 * @(#)OpenIdResponse.java  
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


import org.expressme.openid.OpenIdManager;
import org.expressme.openid.Authentication;
import javax.servlet.http.*;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.turbine.util.security.AccessControlList;
import org.apache.turbine.modules.actions.VelocityAction;
import org.apache.turbine.services.security.TurbineSecurity;
import org.iitk.brihaspati.modules.utils.MultilingualUtil;
import org.iitk.brihaspati.modules.utils.UserUtil;
import org.iitk.brihaspati.modules.utils.LoginUtils;
import org.iitk.brihaspati.modules.utils.CommonUtility;
import org.iitk.brihaspati.om.UserPrefPeer;
import org.iitk.brihaspati.om.UserPref;

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
 * Action class to receive authentication response from OpenID Provider
 * and fetch required parameters from the response.
 * @author <a href="mailto:rpriyanka12@ymail.com">Priyanka Rawat</a>
 * modified date : 01-10-2012
 */

public class OpenIdResponse extends VelocityAction
{

    private Log log = LogFactory.getLog(this.getClass());
    static final long ONE_HOUR = 3600000L;
    static final long TWO_HOUR = ONE_HOUR * 2L;
    static final String ATTR_MAC = "openid_mac";
    static final String ATTR_ALIAS = "openid_alias";
    HttpSession httpsess;
    byte[] mac_key;
    private String alias;
    String[] args={};
    //int count=0;
    private String LangFile="";
    OpenIdProcess open = new OpenIdProcess(); 
    String str;   

 public void doPerform( RunData data, Context context )
 {
	OpenIdProcess openid = new OpenIdProcess(data);
        //ErrorDumpUtil.ErrorLog("i'm here 1");
        HttpServletRequest httpReq = data.getRequest();
        httpsess = data.getSession();
        HttpServletResponse httpResp = data.getResponse();
        System.gc();
        Criteria crit = null;
        String email = "";
        String flag=data.getParameters().getString("flag","");
        String lang=data.getParameters().getString("lang","english");
	LangFile = MultilingualUtil.LanguageSelectionForScreenMessage(lang);
	String password = "";
	String new_url= "";
	String opurl = "";
	String a_key = "";
	 List list = null;

/**
 * Creates OpenIdManager object, to carry out 
 * all OpenId operations.
 * @return OpenIdManager - The OpenIdManager object that handles
 * communication with the jopenid API.
 */
	OpenIdManager manager = new OpenIdManager();
        manager.setReturnTo(data.getServerScheme()+"://"+data.getServerName()+":"+data.getServerPort()+"/brihaspati/servlet/brihaspati/action/OpenIdResponse?is_return=true");
        manager.setRealm(data.getServerScheme()+"://"+data.getServerName()+":"+data.getServerPort()+"/brihaspati/servlet/brihaspati");

	try
	{
		mac_key = (byte[])httpsess.getAttribute(ATTR_MAC);
	        alias = (String)httpsess.getAttribute(ATTR_ALIAS);
		opurl = (String)httpsess.getAttribute("provider");

		Map pmap = (Map)httpReq.getParameterMap();
		//ErrorDumpUtil.ErrorLog("i m here	"+pmap);
	 	StringBuffer receivingURL = httpReq.getRequestURL();
                //ErrorDumpUtil.ErrorLog("I am in open id responce receiving url "+receivingURL);
                String receivingUrl = receivingURL.toString();
                String queryString = httpReq.getQueryString();

                        if(!(queryString.equalsIgnoreCase(null)))
                        {
                        	new_url =  receivingUrl.concat("?");
				new_url = new_url.concat(queryString);
				 //ErrorDumpUtil.ErrorLog("Add receiving URL and Query String2 "+new_url);
                        }

       		       HttpServletRequest request = createRequest(new_url);
        		
			// Fetch nonce information from HTTP request
        		 String nonce = request.getParameter("openid.response_nonce");
			 try{
				open.checkNonce(nonce,opurl);
			 }catch(Exception e)
			  {
				log.error("error while nonce checking",e);
				str=MultilingualUtil.ConvertedString("openid_msg_1",LangFile);
				String url1=data.getServerScheme()+"://"+data.getServerName()+":"+data.getServerPort()+"/brihaspati/servlet/brihaspati/template/BrihaspatiLogin.vm?msg="+str;
				try{
                                       data.setMessage(str);
                                       data.getResponse().sendRedirect(url1);
                                 }
                                 catch (Exception ex){
                                        ErrorDumpUtil.ErrorLog("After getAuthentication function the url is exception "+ex);
                                        //      throw new RuntimeException(message,ex);
                                 }
				throw new RuntimeException("error while nonce checking", e);                                        
			  }			
				
			/**
                         * Get Authentication response returned by OpenId Provider
                         * fom HttpServletRequest instance. 
                         */
				Authentication authentication= null;
				UserData user = null;
				try{
  					authentication = manager.getAuthentication(request, mac_key, alias);
                                	ErrorDumpUtil.ErrorLog("authentication  "+authentication);
                                	user = openid.setUserData(authentication);
                                 }catch (Exception e) {
                        		String message = "Error occurred during authentication!";
                        		log.error(message, e);
                        		final Throwable throwable = new Exception(e);
                        		ErrorDumpUtil.ErrorLog("STACK TRACE   "+getStackTrace(throwable));
                        		ErrorDumpUtil.ErrorLog("Error occured during authentication*** "+ e);
    					str=MultilingualUtil.ConvertedString("openid_msg_1",LangFile);
	                    		String url1=data.getServerScheme()+"://"+data.getServerName()+":"+data.getServerPort()+"/brihaspati/servlet/brihaspati/template/BrihaspatiLogin.vm?msg="+str;
                        		ErrorDumpUtil.ErrorLog("After getAuthentication function the url is  "+url1);
                        		try{
                                		data.setMessage(str);
                                		data.getResponse().sendRedirect(url1);
                        		}
                        		catch (Exception ex){
                                		ErrorDumpUtil.ErrorLog("After getAuthentication function the url is exception "+ex);
                        		//	throw new RuntimeException(message,ex);
					}
					throw new RuntimeException(message,e);
                        	}

	
			/**
                         * Fetching the user's email id from the response returned by OpenId Provider 
                         */
				try{
					email = user.getEmail();
			  	}
				catch(NullPointerException nl)
				{
					String msg = "No value fetched for email";
					 log.error(msg, nl);
                                        final Throwable throwable = new Exception(nl);
                                        ErrorDumpUtil.ErrorLog("STACK TRACE   "+getStackTrace(throwable));
                                        ErrorDumpUtil.ErrorLog("Error occured while fetching data*** "+ nl);
					str=MultilingualUtil.ConvertedString("openid_msg_1",LangFile);
                                        String url1=data.getServerScheme()+"://"+data.getServerName()+":"+data.getServerPort()+"/brihaspati/servlet/brihaspati/template/BrihaspatiLogin.vm?msg="+str;
                                        try{
                                                data.setMessage(str);
                                                data.getResponse().sendRedirect(url1);
                                        }
                                        catch (Exception ex){
                                                ErrorDumpUtil.ErrorLog("Inside OpenIdResponse "+ex);
                                               // throw new RuntimeException(msg,ex);
                                        }
					throw new RuntimeException(msg,nl);

					
				}
		
			/**
 			 * Check whether user is registered with Brihaspati
 			 * or not. If user has an account in Brihaspati, 
 			 * then after checking authorisation he will be 
 			 * redirected to his homepage. Else, he will
 			 * be redirected to Brihaspati login page with
 			 * an error message
 			 */
			int cmpid=-1;
                        int uid=UserUtil.getUID(email);
                        boolean Result= uid == cmpid;
                        ErrorDumpUtil.ErrorLog("This Exception comes (in side First try) in the user id" +uid +" "+ Result);
                        if(Result)
			{
				//String str=MultilingualUtil.ConvertedString("openid_msg_1",LangFile);
                                String url1=data.getServerScheme()+"://"+data.getServerName()+":"+data.getServerPort()+"/brihaspati/servlet/brihaspati/template/BrihaspatiLogin.vm?msg=User does not exist!";
                        	ErrorDumpUtil.ErrorLog("I am in result uid compare second "+url1);
                                try{
					str=MultilingualUtil.ConvertedString("usr_doesntExist",LangFile);
                                        data.setMessage(str);
				       //data.setMessage("User does not exist!");
                                       data.getResponse().sendRedirect(url1);
                                }
                                catch (Exception ex){
                                       ErrorDumpUtil.ErrorLog("User not registered in Brihaspati LMS "+ex);
                                }

                        }
                
			 try{
                                        crit = new Criteria();
                                        crit.add(UserPrefPeer.USER_ID,uid);
                                        list = UserPrefPeer.doSelect(crit);
					a_key = ((UserPref)list.get(0)).getActivation();
					ErrorDumpUtil.ErrorLog(a_key);
					if (a_key == null || a_key.equalsIgnoreCase("NULL"))
                                        {
						 ErrorDumpUtil.ErrorLog(a_key+" is null");
                                                 try{
						      str=MultilingualUtil.ConvertedString("act_prb",LangFile);
		                                      data.setMessage(str);
                                                      //data.setMessage("Your account has some problem, contact to administrator or re register.");
                                                      data.getResponse().sendRedirect(data.getServerScheme()+"://"+data.getServerName()+":"+data.getServerPort()+"/brihaspati/servlet/brihaspati/template/BrihaspatiLogin.vm?msg="+str);
                                                 }
                                                 catch (Exception ex){
                                                        ErrorDumpUtil.ErrorLog("User's account activated not activated........... "+ex);
                                                 }
                                        }
					if (a_key == "ACTIVATE" || a_key.equalsIgnoreCase("ACTIVATE"))
                                        {
						        
						ErrorDumpUtil.ErrorLog(a_key+" is not null");
		         			LoginUtils.CheckSession(email);
                         			Date date=new Date();
                         			data.setMessage(email);
						lang=LoginUtils.SetUserData(email, password, flag, lang, data);
                         			data.unsetMessage();
                         			context.put("lang",lang);
                         			//ErrorDumpUtil.ErrorLog("I am in open id responce ande part set user data");
                         			LoginUtils.UpdateUsageData(uid);
                         			try{
                                 			AccessControlList acl = data.getACL();
                                 			if( acl == null ){
                                        		acl = TurbineSecurity.getACL( data.getUser() );
                                        		data.getSession().setAttribute( AccessControlList.SESSION_KEY,(Object)acl );
                                  			}
                                  		//ErrorDumpUtil.ErrorLog("I am in open id responce else part act setting");
                                  		data.setACL(acl);
                                  		data.save();
 		          			}
                          			catch(Exception ex){
							data.setMessage("Error in setting Access rules :- "+ex);
			  			}

                          			boolean CL=CommonUtility.CleanSystem();
                          			if(!CL)
                                			data.addMessage("The Error in Clean System: see Common Utility");
                          			boolean AB=CommonUtility.IFLoginEntry(uid,date);
                          			LoginUtils.SetHintQues(uid, data);
                          			//ErrorDumpUtil.ErrorLog("I am in open id responce else part hint question");
                          			System.gc();
					}//if
					else
                                        {
                                                try{
							str=MultilingualUtil.ConvertedString("reAct_mail",LangFile);
		                                        data.setMessage(str);
                                                      //data.setMessage("Your account is not activated. For activation please check your mail./n If you did not get the mail, please click on the Resend Activation link.");
                                                      data.getResponse().sendRedirect(data.getServerScheme()+"://"+data.getServerName()+":"+data.getServerPort()+"/brihaspati/servlet/brihaspati/template/BrihaspatiLogin.vm?msg=str");
                                                 }
                                                 catch (Exception ex){
                                                        String msg = "Error in login";
                                                        ErrorDumpUtil.ErrorLog("User's account not activated........... "+ex);
                                                         throw new RuntimeException(msg,ex);
                                                 }

                                        }//else
				}//try
				catch(Exception e){
                                        String message = "Error in activation   ";
                                        throw new RuntimeException(message, e);
                                }//catch
	}//try
	catch(Exception e){
	str=MultilingualUtil.ConvertedString("openid_msg_2",LangFile);
	ErrorDumpUtil.ErrorLog("Exception in OpenIdResponse	"+e);
	final Throwable throwable = new Exception(e);
        ErrorDumpUtil.ErrorLog("STACK TRACE   "+getStackTrace(throwable));
	throw new RuntimeException(str, e);
	}//catch

 }//method


public static String getStackTrace(Throwable throwable) 
{
         Writer writer = new StringWriter();
         PrintWriter printWriter = new PrintWriter(writer);
         throwable.printStackTrace(printWriter);
         return writer.toString();
}

/**
 * Create HttpServletRequest instance 
 * from the key value pairs fetched from the 
 * returned url.
 * @param url String
 * @return HttpServletRequest
 */

static HttpServletRequest createRequest(String url) throws UnsupportedEncodingException {
        
	int pos = url.indexOf('?');
        if (pos==(-1))
            throw new IllegalArgumentException("Bad url.>>>>>");
        String query = url.substring(pos + 1);
	//ErrorDumpUtil.ErrorLog("Create1 "+query);
        String[] params = query.split("[\\&]+");
        final Map<String, String> map = new HashMap<String, String>();
        for (String param : params) {
	//ErrorDumpUtil.ErrorLog("Create2 "+param);
            pos = param.indexOf('=');
            if (pos==(-1))
                throw new IllegalArgumentException("Bad url.");
            String key = param.substring(0, pos);
	    //ErrorDumpUtil.ErrorLog("Key = "+key);
            String value = param.substring(pos + 1);
	    //ErrorDumpUtil.ErrorLog("Value = "+value);
            map.put(key, URLDecoder.decode(value, "UTF-8"));
        }
        return (HttpServletRequest) Proxy.newProxyInstance(
                OpenIdResponse.class.getClassLoader(),
                new Class[] { HttpServletRequest.class },
                new InvocationHandler() {
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        		//int count=0;  
	              if (method.getName().equals("getParameter"))
			{
			     //ErrorDumpUtil.ErrorLog("Create3 "+map.get((String)args[0]));	
                            return map.get((String)args[0]);
			//	++count;
                	}
			//ErrorDumpUtil.ErrorLog("Inside invoke() after if ");
		
		        throw new UnsupportedOperationException(method.getName());
                    }
                }
        );
    }//method


}//class
