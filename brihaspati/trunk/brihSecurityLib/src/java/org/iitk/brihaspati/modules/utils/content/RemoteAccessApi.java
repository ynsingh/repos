
package org.iitk.brihaspati.modules.utils.content;

/*@(#)RemoteAuth.java
 *  Copyright (c) 2012 ETRG,IIT Kanpur. http://www.iitk.ac.in/
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


import org.apache.commons.lang.StringUtils;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.util.List;

import org.iitk.brihaspati.modules.utils.security.EncrptDecrpt;
import org.iitk.brihaspati.modules.utils.security.ReadNWriteInTxt;
import org.iitk.brihaspati.modules.utils.security.RandPasswordUtil;

/**
 * This class provide the API for accessing remote data
 * @author <a href="mailto:nksinghiitk@gmail.com">Nagendra Kumar Singh</a>
 */

public class RemoteAccessApi{
	private static String hdir=System.getProperty("user.home");
	private static String osnme=System.getProperty("os.name");
   	private static String path="";
	private RemoteAccessApi(){
                if(osnme.startsWith("Win")){
                        path=hdir+"\\remote_auth\\brihaspati3-remote-access.properties";
                }
                else{
                        path=hdir+"/remote_auth/brihaspati3-remote-access.properties";
                }
	}
//        private static String path=hdir+"/remote_auth/brihaspati3-remote-access.properties";

	/**
	 *  Method To make connection and return data from Remote server
         */

	private static String connectAndGetData(String serverUrl, String param){
		String reslt=null;
		StringBuffer response=null;
		try{
			URL url = new URL(serverUrl);
			HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
			urlConnection.setDoInput(true);
	                urlConnection.setDoOutput(true);
        	        urlConnection.setRequestMethod("POST");
	//		param=URLEncoder.encode(param, "UTF-8");
        	        urlConnection.connect();
                	OutputStream os = urlConnection.getOutputStream();
	                os.write(param.getBytes("UTF-8"));
        	        os.close();

                	InputStream is = urlConnection.getInputStream();
	                BufferedReader rd = new BufferedReader(new InputStreamReader(is));
        	        String line;
                	response = new StringBuffer();
	                while((line = rd.readLine()) != null) {
		                response.append(line);
              		//        response.append('\n');
                	}
	                rd.close();
			reslt=response.toString();
			System.out.println("The value return by the method "+reslt);
		}
		catch (MalformedURLException ex) {
			ex.printStackTrace();
			System.out.println("The problem in httpurl connection RemoteAccessApi util "+ex);
      		} catch (ProtocolException ex) {
          		ex.printStackTrace();
			System.out.println("The problem in httpurl connection RemoteAccessApi util "+ex);
      		} catch (IOException ex) {
          		ex.printStackTrace();
			System.out.println("The problem in httpurl connection RemoteAccessApi util "+ex);
      		}catch(Exception ex){
			System.out.println("The problem in httpurl connection RemoteAccessApi util "+ex);
		}
	return reslt;
	}
	/**
	 *  Method To get the institute id on behalf of institute name 
	 *  If institute exist then return indtitute code else it return null
 	 * @param  Iname  Institute name 
 	 * @param  srcid this is application resource institute identification code
 	 * @return      String institute identification code
         */
	public  static String getInstituteId(String Iname, String srcid){
		String vale=null;
		try{
			Iname=URLEncoder.encode(Iname, "UTF-8");
			String randompswd = RandPasswordUtil.randmPass();
			String kline=ReadNWriteInTxt.readLin(path,srcid);
        	        String skey=StringUtils.substringBetween(kline,";",";");
			String serverUrl=StringUtils.substringAfterLast(kline,";");
			String hashcode=EncrptDecrpt.keyedHash(srcid,randompswd,skey);
			String params="iname="+Iname+"&srcid="+srcid+"&rand="+randompswd+"&hash="+hashcode+"&aname=getIID";
			vale=connectAndGetData(serverUrl,params);
		}catch(Exception ex){
                        System.out.println("The problem in httpurl connection RemoteAccessApi util "+ex);
                }

	return vale;
	}
	/**
	 *  Method To get the roles on behalf of email(user name)
 	 * @param  email  this is user email/ login name
 	 * @param  srcid this is application resource institute identification code
 	 * @return      String semicolon separated list of roles 
         */
	public static String getRole(String email, String srcid){
		String vale=null;
		try{
			String randompswd = RandPasswordUtil.randmPass();
                        String kline=ReadNWriteInTxt.readLin(path,srcid);
                        String skey=StringUtils.substringBetween(kline,";",";");
                        String serverUrl=StringUtils.substringAfterLast(kline,";");
                        String hashcode=EncrptDecrpt.keyedHash(srcid,randompswd,skey);
			String params="email="+email+"&srcid="+srcid+"&rand="+randompswd+"&hash="+hashcode+"&aname=getRole";
                        vale=connectAndGetData(serverUrl,params);
		}
		catch(Exception ex){
                        System.out.println("The problem in httpurl connection RemoteAccessApi util "+ex);
                }

        return vale;
	}
	/**
 	 * Method to get course list on the basis of email and institute id
 	 * @param  email  this is user email/ login name
 	 * @param  instituteId  this is institute identification code
 	 * @param  srcid this is application resource institute identification code
 	 * @return      String combination of (Groupname-coursename-role) 
 	 */
	public static String getCourseList(String email, String instituteId, String srcid){
		String vale=null;
		try{
                        String randompswd = RandPasswordUtil.randmPass();
                        String kline=ReadNWriteInTxt.readLin(path,srcid);
                        String skey=StringUtils.substringBetween(kline,";",";");
                        String serverUrl=StringUtils.substringAfterLast(kline,";");
                        String hashcode=EncrptDecrpt.keyedHash(srcid,randompswd,skey);
                        String params="email="+email+"&iid="+instituteId+"&srcid="+srcid+"&rand="+randompswd+"&hash="+hashcode+"&aname=getCrsLst";
                        vale=connectAndGetData(serverUrl,params);
                }
                catch(Exception ex){
                        System.out.println("The problem in httpurl connection RemoteAccessApi util (getCourseList) "+ex);
                }
	return vale;
	}	
	/**
 	 * Method to check user exist in the system or not
 	 * If user exist it return false and if user does not exit it return true
 	 * @param  email  this is user email/ login name
 	 * @param  srcid this is application resource institute identification code
 	 * @return      String "false" for user exist and "true" for user not exist
 	 */
	public static String checkUserExist(String email, String srcid){
		String vale="false";
		try{
                        String randompswd = RandPasswordUtil.randmPass();
                        String kline=ReadNWriteInTxt.readLin(path,srcid);
                        String skey=StringUtils.substringBetween(kline,";",";");
                        String serverUrl=StringUtils.substringAfterLast(kline,";");
                        String hashcode=EncrptDecrpt.keyedHash(srcid,randompswd,skey);
                        String params="email="+email+"&srcid="+srcid+"&rand="+randompswd+"&hash="+hashcode+"&aname=getUsrEst";
                        vale=connectAndGetData(serverUrl,params);
                }
                catch(Exception ex){
                        System.out.println("The problem in httpurl connection RemoteAccessApi util (checkUserExist) "+ex);
                }
	return vale;
	}	
	/**
 	 * Method to be used for getting the institute list on behalf of user
 	 * @param  email  this is user email/ login name
 	 * @param  srcid this is application resource institute identification code
 	 * @return      String list 
 	 */
	public static String getInstituteList(String email, String srcid){
		String vale="false";
		try{
                        String randompswd = RandPasswordUtil.randmPass();
                        String kline=ReadNWriteInTxt.readLin(path,srcid);
                        String skey=StringUtils.substringBetween(kline,";",";");
                        String serverUrl=StringUtils.substringAfterLast(kline,";");
                        String hashcode=EncrptDecrpt.keyedHash(srcid,randompswd,skey);
                        String params="email="+email+"&srcid="+srcid+"&rand="+randompswd+"&hash="+hashcode+"&aname=getILst";
                        vale=connectAndGetData(serverUrl,params);
                }
                catch(Exception ex){
                        System.out.println("The problem in httpurl connection RemoteAccessApi util (getInstituteList) "+ex);
                }
	return vale;
	}	
	/**
 	 * Method to be used for gettting personal information before use this method pls call userexist() method
 	 * @param  email  this is user email/ login name
 	 * @param  srcid this is application resource institute identification code
 	 * @return      String 
 	 */
	public static String getPersonalInfo(String email, String srcid){
		String vale="false";
		try{
                        String randompswd = RandPasswordUtil.randmPass();
                        String kline=ReadNWriteInTxt.readLin(path,srcid);
                        String skey=StringUtils.substringBetween(kline,";",";");
                        String serverUrl=StringUtils.substringAfterLast(kline,";");
                        String hashcode=EncrptDecrpt.keyedHash(srcid,randompswd,skey);
                        String params="email="+email+"&srcid="+srcid+"&rand="+randompswd+"&hash="+hashcode+"&aname=getPerInfo";
                        vale=connectAndGetData(serverUrl,params);
                }
                catch(Exception ex){
                        System.out.println("The problem in httpurl connection RemoteAccessApi util (getPersonalInfo) "+ex);
                }
	return vale;
	}	
	/**
 	 * Method to be used for getting registration information before use this method pls call userexist() method
 	 * @param  email  this is user email/ login name
 	 * @param  srcid this is application resource institute identification code
 	 * @return      String 
 	 */
	public static String getRegistartionInfo(String email, String srcid){
		String vale="false";
		try{
                        String randompswd = RandPasswordUtil.randmPass();
                        String kline=ReadNWriteInTxt.readLin(path,srcid);
                        String skey=StringUtils.substringBetween(kline,";",";");
                        String serverUrl=StringUtils.substringAfterLast(kline,";");
                        String hashcode=EncrptDecrpt.keyedHash(srcid,randompswd,skey);
                        String params="email="+email+"&srcid="+srcid+"&rand="+randompswd+"&hash="+hashcode+"&aname=getRegInfo";
                        vale=connectAndGetData(serverUrl,params);
                }
                catch(Exception ex){
                        System.out.println("The problem in httpurl connection RemoteAccessApi util (getRegistartionInfo) "+ex);
                }
	return vale;
	}	
	/**
 	 * Method to be used for getting registration information before use this method pls call userexist() method
 	 * @param  email  this is user email/ login name
 	 * @param  srcid this is application resource institute identification code
 	 * @return      String 
 	 */
	public static String getInternalMarks(String email, String InstituteId, String ProgramId, String srcid){
		String vale="false";
		try{
                        String randompswd = RandPasswordUtil.randmPass();
                        String kline=ReadNWriteInTxt.readLin(path,srcid);
                        String skey=StringUtils.substringBetween(kline,";",";");
                        String serverUrl=StringUtils.substringAfterLast(kline,";");
                        String hashcode=EncrptDecrpt.keyedHash(srcid,randompswd,skey);
                        String params="email="+email+"&iid="+InstituteId+"&prgid="+ProgramId+"&srcid="+srcid+"&rand="+randompswd+"&hash="+hashcode+"&aname=getIntMark";
                        vale=connectAndGetData(serverUrl,params);
                }
                catch(Exception ex){
                        System.out.println("The problem in httpurl connection RemoteAccessApi util (getRegistartionInfo) "+ex);
                }
	return vale;
	}	
}
