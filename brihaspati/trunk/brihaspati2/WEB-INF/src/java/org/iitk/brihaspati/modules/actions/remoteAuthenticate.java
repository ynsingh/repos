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

import java.util.List;
import javax.servlet.ServletOutputStream;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.lang.StringUtils;

import org.apache.turbine.util.RunData;
import org.apache.turbine.modules.actions.VelocityAction;
import org.apache.velocity.context.Context;
import org.apache.torque.util.Criteria;

import org.iitk.brihaspati.om.RemoteUsersPeer;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;

import org.iitk.brihaspati.modules.utils.security.RandPasswordUtil;
import org.iitk.brihaspati.modules.utils.security.EncrptDecrpt;
import org.iitk.brihaspati.modules.utils.security.ReadNWriteInTxt;
import org.iitk.brihaspati.modules.utils.security.RemoteAuthProperties;



/**
 * Action class for authenticating a apllication form authentic server 
 * and send the data to that application also maintain a log
 *
 *  @author <a href="mailto:nksinghiitk@gmail.com">Nagendra Kumar Singh</a>
 */

public class remoteAuthenticate extends VelocityAction{
	private Log log = LogFactory.getLog(this.getClass());

	public void getRemoteAuth( RunData data, Context context){
	//Getting value
		String email=data.getParameters().getString("email");
		String randomNo=data.getParameters().getString("rand") ;
		String hash=data.getParameters().getString("hash");
		String remoteUrl=data.getParameters().getString("url");
		String sourceid=data.getParameters().getString("srcid");
		ErrorDumpUtil.ErrorLog("The getting value from parameter"+remoteUrl +" "+email+" "+sourceid);
		String hdir=System.getProperty("user.home");
	// get return url from client	and source id
		String skey=""; 
		String url="";
	
		String path=hdir+"/remote_auth/brihaspati3-remote-server.properties";
		String line=ReadNWriteInTxt.readLin(path,sourceid);
		skey=StringUtils.substringBetween(line,";",";");
		url=StringUtils.substringAfterLast(line,";");
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
			//remove the value if previously exist
			List us=null;
			Criteria crit=new Criteria();
			crit.add(RemoteUsersPeer.USERID,email);
			try{
                                us=RemoteUsersPeer.doSelect(crit);
                        }
                        catch (Exception ex){
                        ErrorDumpUtil.ErrorLog("The error in select value from db in remote authenticate action "+ex);
                        }
			if(us.size()>0){
				try{
					RemoteUsersPeer.doDelete(crit);
				}
				catch (Exception ex){
					ErrorDumpUtil.ErrorLog("The error in deleting record from remote user in remote authenticat action "+ex);
				}
			}
//			ErrorDumpUtil.ErrorLog("Here I am before wrting value in db in remote authenticate action ");
			//store in db;
			crit=new Criteria();
                        crit.add(RemoteUsersPeer.USERID,email);
                        crit.add(RemoteUsersPeer.RANDOMKEY,randno);
                        crit.add(RemoteUsersPeer.APPLICATION,remoteUrl);//return url comming from web client
                        crit.add(RemoteUsersPeer.SOURCEID,sourceid);//return url comming from web client
			try{
                	        RemoteUsersPeer.doInsert(crit);
			}
			catch (Exception ex){
			ErrorDumpUtil.ErrorLog("The error in insert value from db in remote authenticate action ");
			}
//this url lift from server conf file
			String url1="email="+email+"&sess="+randno+"&url="+url;
			// ErrorDumpUtil.ErrorLog("Here I am before writing the responce  in remote authenticate in action "+url1);
			try{
				url1=EncrptDecrpt.encrypt(url1,sourceid);
				String genHashN=EncrptDecrpt.keyedHash(email,randno,skey);
				url1=url1+"&hash="+genHashN;
				//add keyed hash
				ErrorDumpUtil.ErrorLog("The value of responce  in remote authenticate in action "+url1);
				ServletOutputStream out=data.getResponse().getOutputStream();
				byte[] buf=url1.getBytes();
                                out.write(buf);
				out.close();
			}
			catch (Exception ex){
				ErrorDumpUtil.ErrorLog("The error in redirection url 1 in remote authenticate in action Remoteauth method "+ex);
			}
		}
		else{
			remoteUrl=remoteUrl+"?msg=You are not coming from authentic server";
			try{
				data.getResponse().sendRedirect(remoteUrl);
			}
                        catch (Exception ex){
				ErrorDumpUtil.ErrorLog("The hash is not matched in remote authenticate action "+ex);
                        }
			
		}
	}	

	/**
	 * This method is invoked upon when user logging in
	 * @param data RunData instance
	 * @param context Context instance
	 */
	
	public void doPerform( RunData data, Context context )
	{
		String action=data.getParameters().getString("aname","");
                ErrorDumpUtil.ErrorLog("I am in remote data action and value is "+action);
		RemoteAccessData RAD=new RemoteAccessData();
                if(action.equals("getIID")){
                        RAD.getInstituteId(data,context);}
                if(action.equals("getRole")){
                        RAD.getRole(data,context);}
                if(action.equals("getCrsLst")){
                        RAD.getCourseList(data,context);}
                if(action.equals("getUsrEst")){
                        RAD.checkUsrExist(data,context);}
                if(action.equals("getILst")){
                        RAD.getInstituteList(data,context);}
                if(action.equals("getPerInfo")){
                        RAD.getPersonalInfo(data,context);}
                if(action.equals("getRegInfo")){
                        RAD.getRegistrationInfo(data,context);}
                if(action.equals("getIntMark")){
                        RAD.getInternalMarks(data,context);}
                if(action.equals("remAuth")){
                        getRemoteAuth(data,context);}
		if(action.equals(" ")){	
			try{
				String url1="NULL?msg=You are not coming from authentic server";
				ServletOutputStream out=data.getResponse().getOutputStream();
                                byte[] buf=url1.getBytes();
                                out.write(buf);
                                out.close();
			}
			catch (Exception ex){
				ErrorDumpUtil.ErrorLog("The error in redirection url 1  in remote authenticate in action  dot equal null value  "+ex);
			}
		}
	}
}
