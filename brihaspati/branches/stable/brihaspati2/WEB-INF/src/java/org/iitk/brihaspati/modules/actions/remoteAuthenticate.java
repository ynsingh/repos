package org.iitk.brihaspati.modules.actions;
/*
 * @(#)remoteAuthenticate.java	
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
 * 
 *  Contributors : members of ETRG, IIT Kanpur  
 *  
 */

import javax.servlet.ServletOutputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.apache.turbine.util.RunData;
import org.apache.turbine.modules.actions.VelocityAction;
import org.apache.velocity.context.Context;
import org.apache.torque.util.Criteria;

import org.iitk.brihaspati.om.RemoteUsersPeer;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;

import org.iitk.brihaspati.modules.utils.security.RandPasswordUtil;
import org.iitk.brihaspati.modules.utils.security.EncrptDecrpt;
import org.iitk.brihaspati.modules.utils.security.RemoteAuthProperties;



/**
 * Action class for authenticating a apllication form authentic server 
 * and send the data to that application also maintain a log
 *
 *  @author <a href="mailto:nksinghiitk@gmail.com">Nagendra Kumar Singh</a>
 */

public class remoteAuthenticate extends VelocityAction{
	private Log log = LogFactory.getLog(this.getClass());
	

	/**
	 * This method is invoked upon when user logging in
	 * @param data RunData instance
	 * @param context Context instance
	 */
	
	public void doPerform( RunData data, Context context )
	{
		
		String email=data.getParameters().getString("email");
		String randomNo=data.getParameters().getString("rand") ;
		String hash=data.getParameters().getString("hash");
		String remoteUrl=data.getParameters().getString("url");
	// get return url from client	and source id
		String skey=""; 
//		String retUrl="";
			ErrorDumpUtil.ErrorLog("The problem in getting value from properties file");
		String path=data.getServletContext().getRealPath("/WEB-INF/conf/brihaspati3-remote-access.properties");
		try{
        	        skey = RemoteAuthProperties.getValue(path,"security_key");
        	       // retUrl = AdminProperties.getValue(path,"server_url");
		}
		catch(Exception ex){
			ErrorDumpUtil.ErrorLog("The problem in getting value from properties file");
		}
//this get from retrun url
		
//		ErrorDumpUtil.ErrorLog("I am here remote email action in action file=="+remoteUrl);
		String genHash=EncrptDecrpt.keyedHash(email,randomNo,skey);
	//	boolean match=match hash;
		if (hash.equals(genHash)){
			//check user exist or not
			//boolean exist=UserManagement.checkUserExist(email);
			//ErrorDumpUtil.ErrorLog("The value of user exist is "+exist);
			//if(exist==false){
			//generate random number;
			String randno=RandPasswordUtil.randmPass();
			//store in db;
			Criteria crit=new Criteria();
                        crit.add(RemoteUsersPeer.USERID,email);
                        crit.add(RemoteUsersPeer.RANDOMKEY,randno);
                        crit.add(RemoteUsersPeer.APPLICATION,remoteUrl);//return url comming from web client
			try{
                	        RemoteUsersPeer.doInsert(crit);
			}
			catch (Exception ex){
			ErrorDumpUtil.ErrorLog("The error in insert value ");
			}
//this url lift from server conf file
			String url="http://172.26.82.17:8080/brihaspati/servlet/brihaspati/template/remPass.vm/lang/english";
			String url1="email="+email+"&sess="+randno+"&url="+url;
			try{
				url1=EncrptDecrpt.encrypt(url1);
				String genHashN=EncrptDecrpt.keyedHash(email,randno,skey);
				url1=url1+"&hash="+genHashN;
				//add keyed hash
				ErrorDumpUtil.ErrorLog("The error in redirection url  after encription "+url1);
				ServletOutputStream out=data.getResponse().getOutputStream();
				byte[] buf=url1.getBytes();
                                out.write(buf);
				out.close();
			}
			catch (Exception ex){
				ErrorDumpUtil.ErrorLog("The error in redirection url 1 ");
			}
		}
		else{
			remoteUrl=remoteUrl+"?msg=You are not coming from authentic server";
			try{
				data.getResponse().sendRedirect(remoteUrl);
			}
                        catch (Exception ex){
				ErrorDumpUtil.ErrorLog("The hash is not matched ");
                        }
			
		}
	}
}
