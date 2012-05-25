
package org.iitk.brihaspati.modules.utils.security;

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
import java.io.*;
import java.util.List;

/**
 * This class provide the listing of years
 * @author <a href="mailto:nksinghiitk@gmail.com">Nagendra Kumar Singh</a>
 */

public class RemoteAuth{
	/**
  	 * Method To Authentication of Remote User
  	 */
	public static String AuthR(String email,String returl, String srcid) {

		String randompswd = RandPasswordUtil.randmPass();
		String hdir=System.getProperty("user.home");
		String path=hdir+"/remote_auth/brihaspati3-remote-access.properties";
		String skey="";
                String serverUrl="";
						String kline=ReadNWriteInTxt.readLin(path,srcid);
                                                skey=StringUtils.substringBetween(kline,";",";");
                                                serverUrl=StringUtils.substringAfterLast(kline,";");
		 System.out.println("The value of skey and Server URL is"+skey+" "+serverUrl);
              /*  try{
                        skey = RemoteAuthProperties.getValue(path,"security_key");
                        serverUrl=RemoteAuthProperties.getValue(path,"server_url");
                }
                catch(Exception ex){
                        System.out.println("The problem in getting value from properties file");
                }*/
  //                      ErrorDumpUtil.ErrorLog("The value of server url and keys are "+skey+" and  "+serverUrl);

//		String hkey="email="+email+";random="+randompswd+";secret="+skey+";";
                String hashcode=EncrptDecrpt.keyedHash(email,randompswd,skey);
 //                       ErrorDumpUtil.ErrorLog("The value of hash code  are "+hashcode);
		try{
			URL url = new URL(serverUrl);
                        HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
                        urlConnection.setDoInput(true);
                        urlConnection.setDoOutput(true);
                        urlConnection.setRequestMethod("POST");
			String params = "email="+email+"&srcid="+srcid+"&url="+returl+"&rand="+randompswd+"&hash="+hashcode;
				System.out.println("The parameter pass to the connection connection  "+params);
                        urlConnection.connect();
                        OutputStream os = urlConnection.getOutputStream();
                        os.write(params.getBytes("UTF-8"));
                        os.close();

			InputStream is = urlConnection.getInputStream();
                        BufferedReader rd = new BufferedReader(new InputStreamReader(is));
                        String line;
                        StringBuffer response = new StringBuffer();
                        while((line = rd.readLine()) != null) {
                                response.append(line);
                        //        response.append('\n');
                        }
                        rd.close();
                        String reslt=response.toString();
//			ErrorDumpUtil.ErrorLog("The value of result  are "+reslt);

			String clentHash=StringUtils.substringAfter(reslt,"&hash=");
			System.out.println("The value of result  are 1 "+clentHash+"I am");
			String encriptData=StringUtils.substringBefore(reslt,"&hash=");
	//		ErrorDumpUtil.ErrorLog("The value of result  are 2 "+encriptData);

			String enUrl1=EncrptDecrpt.decrypt(encriptData,srcid);
	//		ErrorDumpUtil.ErrorLog("The value of result  are 3 "+enUrl1);
			String email1=StringUtils.substringBetween(enUrl1,"email=","&sess=");
                        String randomNo=StringUtils.substringBetween(enUrl1,"&sess=","&url=");
                        String redirUrl=StringUtils.substringAfter(enUrl1,"&url=");

//			String hkeyN="email="+email1+";random="+randomNo+";secret="+skey+";";
	                String hashcodeN=EncrptDecrpt.keyedHash(email1,randomNo,skey);
			System.out.println("The value of hashcode n   are "+hashcodeN+"I am");
			String fpath=hdir+"/remote_auth/remote-user.txt";
			if(clentHash.equals(hashcodeN)){
		//		ErrorDumpUtil.ErrorLog("I am inside if");
				String valu=email1+";"+randomNo+";"+redirUrl;
				ReadNWriteInTxt.writeF(fpath,valu);	
				
				String khash=EncrptDecrpt.keyedHash(email1,randomNo,skey);	
				redirUrl=redirUrl+"?email="+email1+"&sess="+randomNo+"&hash="+khash;
				returl=redirUrl;
			}
			else{
					returl=returl+"?msg=You are not comming from authentic server";
			}
		}
                catch (Exception ex){
			returl=returl+"?msg="+ex;
                        System.out.println("The problem in httpurl connection  "+ex);
                }
	return returl;
	}
}
