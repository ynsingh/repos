package org.iitk.brihaspati.modules.actions;
/*
 * @(#)remoteAuthRes.java	
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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.lang.StringUtils;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;
import org.apache.turbine.modules.actions.VelocityAction;
import org.apache.torque.util.Criteria;

import org.iitk.brihaspati.om.TurbineUserPeer;
import org.iitk.brihaspati.om.TurbineUser;
import org.iitk.brihaspati.om.RemoteUsersPeer;
import org.iitk.brihaspati.om.RemoteUsers;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.iitk.brihaspati.modules.utils.UserManagement;

import org.iitk.brihaspati.modules.utils.security.RandPasswordUtil;
import org.iitk.brihaspati.modules.utils.security.EncrptDecrpt;
import org.iitk.brihaspati.modules.utils.security.ReadNWriteInTxt;
import org.iitk.brihaspati.modules.utils.security.EncryptionUtil;
import org.iitk.brihaspati.modules.utils.security.RemoteAuthProperties;

/**
 * Action class for authenticating a apllication form authentic server 
 * and send the data to that application also maintain a log
 *
 *  @author <a href="mailto:nksinghiitk@gmail.com">Nagendra Kumar Singh</a>
 */

public class remoteAuthRes extends VelocityAction{
	private Log log = LogFactory.getLog(this.getClass());
	

	/**
	 * This method is invoked upon when user logging in
	 * @param data RunData instance
	 * @param context Context instance
	 */
	
	public void doPerform( RunData data, Context context )
	{
		String remoteUrl="";
		String rurl="";
		String srcid="";
		String pass=data.getParameters().getString("password");
		//ErrorDumpUtil.ErrorLog("The vale of password is "+pass);
		String url=data.getParameters().getString("url");
		//ErrorDumpUtil.ErrorLog("The vale of URL is "+url);

		String url1=StringUtils.substringBefore(url,"?");
		String email1=StringUtils.substringBetween(url,"?","&");
		email1=StringUtils.substringAfter(email1,"email=");
		String sess=StringUtils.substringAfter(url,"sess=");

	// get the return url from database
		Criteria crit=new Criteria();
                crit.add(RemoteUsersPeer.USERID,email1);
 	        crit.add(RemoteUsersPeer.RANDOMKEY,sess);
		List v=null;
		try{
                	v=RemoteUsersPeer.doSelect(crit);
		}
		catch(Exception ex){
                                ErrorDumpUtil.ErrorLog("The error in selecting value in remote auth res action "+ex);
                        }
		for(int i=0;i<v.size();i++){
                        RemoteUsers ur=(RemoteUsers)v.get(i);
        	        rurl=ur.getApplication();
        	        srcid=ur.getSourceid();
                }

		String password="";
		List vec=null;
		if(pass.equals("")){
//			ErrorDumpUtil.ErrorLog("I am going to template "+pass);
			data.setScreenTemplate("remPass.vm");
		}
		else{
//			ErrorDumpUtil.ErrorLog("I am not going to template "+pass);
			try{
				password=EncryptionUtil.createDigest("MD5",pass);
			}
                	catch(Exception ex){
				ErrorDumpUtil.ErrorLog("The error in creating encription in remote auth res action "+ex);
                	}
			boolean exist=UserManagement.checkUserExist(email1);
			if(exist==false){
				crit = new Criteria();
				crit.add(TurbineUserPeer.LOGIN_NAME,email1);
				try{
					vec=TurbineUserPeer.doSelect(crit);
				}
				catch(Exception ex){
					ErrorDumpUtil.ErrorLog("The error in getting detail of user in remote auth res action "+ex);
                		}
//				ErrorDumpUtil.ErrorLog("I am not going to template1 "+vec.size());
				if(vec.size() != 0) {
        	                        TurbineUser element=(TurbineUser)vec.get(0);
					String pass1=element.getPasswordValue().toString();
					if(pass1.equals(password)){

				// generate encript string ( email, sess, random no) with shared secret
						String params = "email="+email1+"&sess="+sess;
						params=EncrptDecrpt.encrypt(params,srcid);
					// generate keyed hash
						String randm = RandPasswordUtil.randmPass();
						String hdir=System.getProperty("user.home");
						String path=hdir+"/remote_auth/brihaspati3-remote-access.properties";
						String line=ReadNWriteInTxt.readLin(path,srcid);
				                String skey=StringUtils.substringBetween(line,";",";");
			        	//      url=StringUtils.substringAfterLast(line,";");
	
						String hashcode=EncrptDecrpt.keyedHash(email1,randm,skey);
					// create url for redirect encript string + keyed hash
						rurl=rurl+"?encd="+params+"&rand="+randm+"&hash="+hashcode;
						Criteria crit1=new Criteria();
		               			crit1.add(RemoteUsersPeer.USERID,email1);
						try{
							RemoteUsersPeer.doDelete(crit1);
						}
                				catch(Exception ex){
							ErrorDumpUtil.ErrorLog("The error in deleting record from remote user in remote auth res action "+ex);
               					}
				//ErrorDumpUtil.ErrorLog("The result url  in  remote auth res is  "+rurl);
						try{
							data.getResponse().sendRedirect(rurl);
						}
                				catch(Exception ex){
							ErrorDumpUtil.ErrorLog("The error in correct redirection in remote auth res action "+ex);
                				}
					}
					else{
						Criteria crit2=new Criteria();
                                        	crit2.add(RemoteUsersPeer.USERID,email1);
                                        	try{
                                        		RemoteUsersPeer.doDelete(crit2);
                                        	}
                                        	catch(Exception ex){
							ErrorDumpUtil.ErrorLog("The error in deleting record from remote user in remote auth res action "+ex);
                                        	}
						remoteUrl=rurl+"?msg=Your userid or password is incorrect";
                        			try{
                        				data.getResponse().sendRedirect(remoteUrl);
                        			}
                        			catch (Exception ex){
							ErrorDumpUtil.ErrorLog("The error in redirection in incorrect userid or password in remote auth res action "+ex);
                        			}

					}//password check
				}//v.size check
			}//user exist check
			else{
			//remoteUrl=remoteUrl+"?msg=Your userid or password is incorrect";
				Criteria crit3=new Criteria();
                                crit3.add(RemoteUsersPeer.USERID,email1);
                                try{
                                        RemoteUsersPeer.doDelete(crit3);
                                }
                                catch(Exception ex){
					ErrorDumpUtil.ErrorLog("The error in deleting record from remote user in remote auth res action "+ex);
                                }
				remoteUrl=rurl+"?msg=Your userid or password is incorrect";
                                try{
         	                       data.getResponse().sendRedirect(remoteUrl);
                                }
                                catch (Exception ex){
					ErrorDumpUtil.ErrorLog("The error in redirection in incorrect userid or password in remote auth res action "+ex);
                                }
			}
		}//null pass else close
	}
}
