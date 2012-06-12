package org.iitk.brihaspati.modules.actions;
/*
 * @(#)RemoteAccessData.java	
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
import java.util.List;
import java.util.Vector;
import java.net.URLEncoder;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import org.apache.turbine.modules.actions.VelocityAction;

import org.apache.torque.util.Criteria;
import org.apache.commons.lang.StringUtils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.iitk.brihaspati.modules.utils.UserUtil;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.iitk.brihaspati.modules.utils.UserGroupRoleUtil;
import org.iitk.brihaspati.modules.utils.RemoteData;
import org.iitk.brihaspati.modules.utils.UserManagement;

import org.iitk.brihaspati.modules.utils.security.EncrptDecrpt;
import org.iitk.brihaspati.modules.utils.security.RandPasswordUtil;
import org.iitk.brihaspati.modules.utils.security.ReadNWriteInTxt;

import org.iitk.brihaspati.om.InstituteAdminRegistrationPeer;
import org.iitk.brihaspati.om.InstituteAdminRegistration;

/**
 * Action class for getting and sending data back to the client 
 * and send the data to that application also maintain a log
 *
 *  @author <a href="mailto:nksinghiitk@gmail.com">Nagendra Kumar Singh</a>
 */

public class RemoteAccessData extends VelocityAction{

	private Log log = LogFactory.getLog(this.getClass());
	String hdir=System.getProperty("user.home");
	/**
	 * This method is used for writing response
	 * @param data RunData instance
	 * @param val String
	 */

	private void writeRespose(String val, RunData data){
		try{
        		ServletOutputStream out=data.getResponse().getOutputStream();
	                byte[] buf=val.getBytes();
        	        out.write(buf);
                	out.close();
        	}
                catch(Exception ex){
                        ErrorDumpUtil.ErrorLog("The error in sending responce in remote access data action (writeRespose)  "+ex);
                }

	}
	/**
	 * This method is invoked When user want to access institute Id
	 * @param data RunData instance
	 * @param context Context instance
	 */
	
	public void getInstituteId(RunData data, Context context)
        {
		String res=null;
		String datas=null;
		//Getting value
		String iName=data.getParameters().getString("iname");
                String randomNo=data.getParameters().getString("rand") ;
                String hash=data.getParameters().getString("hash");
                String sourceid=data.getParameters().getString("srcid");
		//Getting the key from properties file		
		String path=hdir+"/remote_auth/brihaspati3-remote-access.properties";
                String line=ReadNWriteInTxt.readLin(path,sourceid);
                String skey=StringUtils.substringBetween(line,";",";");
		//generate keyed hash
		String genHash=EncrptDecrpt.keyedHash(sourceid,randomNo,skey);
		//match hash
		if (hash.equals(genHash)){
			int instid=0;
			List inm=null;
			//get the institute id
			Criteria crit=new Criteria();
			crit.add(InstituteAdminRegistrationPeer.INSTITUTE_NAME,iName);
			try{
				inm=InstituteAdminRegistrationPeer.doSelect(crit);
			}
			catch(Exception ex){
				ErrorDumpUtil.ErrorLog("The error in selecting record from institute table in remote access data action (getInstitute Id) "+ex);
			}
			if(inm.size()>0){
                                InstituteAdminRegistration element=(InstituteAdminRegistration)inm.get(0);
                                instid=element.getInstituteId();
				//convert it to String
				String v=Integer.toString(instid);
				//generate random number
				String randno=RandPasswordUtil.randmPass();
				//generate new keyed hash
				String genHashN=EncrptDecrpt.keyedHash(sourceid,randno,skey);
				//genertare responce
				datas=v+"&random="+randno+"&hash="+genHashN;
				try{
					// encript responce and encodeurl before writting
					datas=URLEncoder.encode((EncrptDecrpt.encrypt(datas,sourceid)),"UTF-8");
				}
				catch(java.io.UnsupportedEncodingException ex){
					ErrorDumpUtil.ErrorLog("The error in encoding responce in remote access data action (get Institute Id) "+ex);
				}
				catch(Exception ex){
                                        ErrorDumpUtil.ErrorLog("The error in sending responce in remote access data action (getInstitute Id)  "+ex);
                                }

				
				try{
					writeRespose(datas, data);
				}
				catch(Exception ex){
					ErrorDumpUtil.ErrorLog("The error in sending responce in remote access data action (getInstitute Id)  "+ex);
				}
                        }
			else{
				res="Either the institute name does not match or institute does not exist. So please verify at Registratered Institute list or make a request for institute registration";
				ErrorDumpUtil.ErrorLog("remote access data action (getInstitute Id)"+res);
				writeRespose(datas,data);
			}
		}
		else{
			res="Hash is not matched, so that we will not send the data";
			ErrorDumpUtil.ErrorLog("remote access data action (getInstitute Id)"+res);
			writeRespose(datas,data);
		}
	}
	/**
	 * This method is invoked When application want to access the roles of thst user
	 * @param data RunData instance
	 * @param context Context instance
	 */
	public void getRole(RunData data, Context context)
	{
		String res=null;
		String datas=null;
		//Getting value
		String email=data.getParameters().getString("email");
                String randomNo=data.getParameters().getString("rand") ;
                String hash=data.getParameters().getString("hash");
                String sourceid=data.getParameters().getString("srcid");
	//	ErrorDumpUtil.ErrorLog("The value of parameter are in get role method"+email + randomNo + sourceid + hash);
		//Getting the key from properties file		
		String path=hdir+"/remote_auth/brihaspati3-remote-access.properties";
                String line=ReadNWriteInTxt.readLin(path,sourceid);
                String skey=StringUtils.substringBetween(line,";",";");
		//generate keyed hash
		String genHash=EncrptDecrpt.keyedHash(sourceid,randomNo,skey);
		if (hash.equals(genHash)){
	//	ErrorDumpUtil.ErrorLog("The value of match hash are in get role method"+genHash+ sourceid + hash);
			String v=null;
			List inm=null;
			//get the roles
			int uid=UserUtil.getUID(email);
			Vector rid=UserGroupRoleUtil.getRID(uid);
			//ErrorDumpUtil.ErrorLog("The value of vector size of role  are in get role method"+rid.size());
			if(rid.size()>0){
				for ( int i = 0; i < rid.size(); ++i )
				
	    			{
					int name = Integer.parseInt((rid.elementAt( i )).toString());
					if(name!=6){
						String rnm=UserGroupRoleUtil.getRoleName(name); 
						boolean flage=v.contains(rnm);
						if(!flage){
	//		ErrorDumpUtil.ErrorLog("The value of vector size of role name  are in get role method"+rnm);
							if(i==0){
								v=rnm;
							}
							else{
								v=v+";"+rnm;
							}
						}
					}
				}
				//generate random number
				String randno=RandPasswordUtil.randmPass();
				//generate new keyed hash
				String genHashN=EncrptDecrpt.keyedHash(sourceid,randno,skey);
				//genertare and write responce
				datas=v+"&random="+randno+"&hash="+genHashN;
// ErrorDumpUtil.ErrorLog("The value of data are in get role method"+datas);
				try{
					// encript responce and encodeurl before writting
					datas=URLEncoder.encode((EncrptDecrpt.encrypt(datas,sourceid)),"UTF-8");
				}
				catch(java.io.UnsupportedEncodingException ex){
					ErrorDumpUtil.ErrorLog("The error in encoding responce in remote access data action (getRole) "+ex);
				}
				catch(Exception ex){
                                        ErrorDumpUtil.ErrorLog("The error in sending responce in remote access data action (getRole)  "+ex);
                                }
				try{
                                        writeRespose(datas, data);
                                }
                                catch(Exception ex){
                                        ErrorDumpUtil.ErrorLog("The error in sending responce in remote access data action (get role Id)  "+ex);
                                }

			}
			else{
				res="Either the email does not match or email does not exist. So please make a request for registration";
                                ErrorDumpUtil.ErrorLog("remote access data action (get role Id)"+res);
                                writeRespose(datas,data);
			}
		}
		else{
			res="Hash is not matched, so that we will not send the data";
                        ErrorDumpUtil.ErrorLog("remote access data action (getRole Id)"+res);
                        writeRespose(datas,data);
		}
		
	}
	/**
	 * This method is invoked When user want to access the course list of specific user
	 * @param data RunData instance
	 * @param context Context instance
	 */
	public void getCourseList(RunData data, Context context)
        {
		String res=null;
                String datas=null;
		//Getting value
		String email=data.getParameters().getString("email");
                String randomNo=data.getParameters().getString("rand") ;
                String hash=data.getParameters().getString("hash");
                String sourceid=data.getParameters().getString("srcid");
                String insid=data.getParameters().getString("iid");
                //Getting the key from properties file
                String path=hdir+"/remote_auth/brihaspati3-remote-access.properties";
                String line=ReadNWriteInTxt.readLin(path,sourceid);
                String skey=StringUtils.substringBetween(line,";",";");
                //generate keyed hash
                String genHash=EncrptDecrpt.keyedHash(sourceid,randomNo,skey);
                if (hash.equals(genHash)){
                	//call usermanagement api
                        Vector ucdata=RemoteData.getUserCrsList(email,insid);
                        //generate random number
                        String randno=RandPasswordUtil.randmPass();
                        //generate new keyed hash
                        String genHashN=EncrptDecrpt.keyedHash(sourceid,randno,skey);
                       	//genertare and write responce
                       	datas=(ucdata.toString())+"&random="+randno+"&hash="+genHashN;
			try{
				// encript responce and encodeurl before writting
				datas=URLEncoder.encode((EncrptDecrpt.encrypt(datas,sourceid)),"UTF-8");
			}
			catch(java.io.UnsupportedEncodingException ex){
				ErrorDumpUtil.ErrorLog("The error in encoding responce in remote access data action (getCourseList) "+ex);
			}
			catch(Exception ex){
                                ErrorDumpUtil.ErrorLog("The error in sending responce in remote access data action (getCourseList)  "+ex);
                       }
                        try{
                                writeRespose(datas, data);
                        }
                        catch(Exception ex){
                               ErrorDumpUtil.ErrorLog("The error in sending responce in remote access data action (getCourseList)  "+ex);
                         }

                }
                else{
                        res="Hash is not matched, so that we will not send the data";
                        ErrorDumpUtil.ErrorLog("remote access data action (getCourseList)"+res);
                        writeRespose(datas,data);
                }

	}
	/**
	 * This method is invoked When application to know user exist or not
	 * @param data RunData instance
	 * @param context Context instance
	 */
	public void checkUsrExist(RunData data, Context context)
	{
		String res=null;
                String datas=null;
                //Getting value
                String email=data.getParameters().getString("email");
                String randomNo=data.getParameters().getString("rand") ;
                String hash=data.getParameters().getString("hash");
                String sourceid=data.getParameters().getString("srcid");
                String insid=data.getParameters().getString("iid");
                //Getting the key from properties file
                String path=hdir+"/remote_auth/brihaspati3-remote-access.properties";
                String line=ReadNWriteInTxt.readLin(path,sourceid);
                String skey=StringUtils.substringBetween(line,";",";");
                //generate keyed hash
                String genHash=EncrptDecrpt.keyedHash(sourceid,randomNo,skey);
                if (hash.equals(genHash)){
                        //call usermanagement api
                        boolean flg=UserManagement.checkUserExist(email);
			String btos=new Boolean(flg).toString();
                        //generate random number
                        String randno=RandPasswordUtil.randmPass();
                        //generate new keyed hash
                        String genHashN=EncrptDecrpt.keyedHash(sourceid,randno,skey);
                       	//genertare and write responce
                       	datas=btos+"&random="+randno+"&hash="+genHashN;
			try{
				// encript responce and encodeurl before writting
				datas=URLEncoder.encode((EncrptDecrpt.encrypt(datas,sourceid)),"UTF-8");
			}
			catch(java.io.UnsupportedEncodingException ex){
				ErrorDumpUtil.ErrorLog("The error in encoding responce in remote access data action (checkUserExist) "+ex);
			}
			catch(Exception ex){
                                ErrorDumpUtil.ErrorLog("The error in sending responce in remote access data action (checkUserExist)  "+ex);
                        }
                        try{
                                writeRespose(datas, data);
                        }
                        catch(Exception ex){
                               ErrorDumpUtil.ErrorLog("The error in sending responce in remote access data action (checkUserExist)  "+ex);
                         }
                }
                else{
                        res="Hash is not matched, so that we will not send the data";
                        ErrorDumpUtil.ErrorLog("remote access data action (checkUserExist)"+res);
                        writeRespose(datas,data);
                }
	}
	
	/**
	 * This method is invoked When user want to access institute  list where user is registered
	 * @param data RunData instance
	 * @param context Context instance
	 */
	
	public void getInstituteList(RunData data, Context context)
	{
		String res=null;
                String datas=null;
                //Getting value
                String email=data.getParameters().getString("email");
                String randomNo=data.getParameters().getString("rand") ;
                String hash=data.getParameters().getString("hash");
                String sourceid=data.getParameters().getString("srcid");
                //Getting the key from properties file
                String path=hdir+"/remote_auth/brihaspati3-remote-access.properties";
                String line=ReadNWriteInTxt.readLin(path,sourceid);
                String skey=StringUtils.substringBetween(line,";",";");
                //generate keyed hash
                String genHash=EncrptDecrpt.keyedHash(sourceid,randomNo,skey);
                if (hash.equals(genHash)){
                        //call usermanagement api
                        boolean flg=UserManagement.checkUserExist(email);
			if(!flg){
				//call the method from remote data classs in util get the list of institute id- name - role
				Vector ucdata=RemoteData.getUserInsList(email);
        	                //generate random number
                	        String randno=RandPasswordUtil.randmPass();
                        	//generate new keyed hash
	                        String genHashN=EncrptDecrpt.keyedHash(sourceid,randno,skey);
        	               	//genertare and write responce
                	       	datas=(ucdata.toString())+"&random="+randno+"&hash="+genHashN;
				try{
					// encript responce and encodeurl before writting
					datas=URLEncoder.encode((EncrptDecrpt.encrypt(datas,sourceid)),"UTF-8");
				}
				catch(java.io.UnsupportedEncodingException ex){
					ErrorDumpUtil.ErrorLog("The error in encoding responce in remote access data action (getInstituteList) "+ex);
				}
				catch(Exception ex){
                	                ErrorDumpUtil.ErrorLog("The error in sending responce in remote access data action (getInstituteList)  "+ex);
                        	}
	                        try{
        	                        writeRespose(datas, data);
                	        }
                        	catch(Exception ex){
                               		ErrorDumpUtil.ErrorLog("The error in sending responce in remote access data action (getInstituteList)  "+ex);
                         	}
			}
			else{
				datas=null;
				res="User does not exist and data is null";
        	                ErrorDumpUtil.ErrorLog("remote access data action (getInstituteList)"+res);
	                        writeRespose(datas,data);

			}
                }
                else{
                        res="Hash is not matched, so that we will not send the data";
                        ErrorDumpUtil.ErrorLog("remote access data action (getInstituteList)"+res);
                        writeRespose(datas,data);
                }
	}
	
	/**
	 * This method is invoked When user want to access personal information
	 * @param data RunData instance
	 * @param context Context instance
	 */
	public void getPersonalInfo(RunData data, Context context)
	{
		String res=null;
                String datas=null;
                //Getting value
                String email=data.getParameters().getString("email");
                String randomNo=data.getParameters().getString("rand") ;
                String hash=data.getParameters().getString("hash");
                String sourceid=data.getParameters().getString("srcid");
                //Getting the key from properties file
                String path=hdir+"/remote_auth/brihaspati3-remote-access.properties";
                String line=ReadNWriteInTxt.readLin(path,sourceid);
                String skey=StringUtils.substringBetween(line,";",";");
                //generate keyed hash
                String genHash=EncrptDecrpt.keyedHash(sourceid,randomNo,skey);
                if (hash.equals(genHash)){
                        //call usermanagement api
                        boolean flg=UserManagement.checkUserExist(email);
			if(!flg){
				int uid=UserUtil.getUID(email);
				String pinfo=UserUtil.getFullName(uid); 
                	        //generate random number
                        	String randno=RandPasswordUtil.randmPass();
	                        //generate new keyed hash
        	                String genHashN=EncrptDecrpt.keyedHash(sourceid,randno,skey);
                	       	//genertare and write responce
                       		datas=pinfo+"&random="+randno+"&hash="+genHashN;
				try{
					// encript responce and encodeurl before writting
					datas=URLEncoder.encode((EncrptDecrpt.encrypt(datas,sourceid)),"UTF-8");
				}
				catch(java.io.UnsupportedEncodingException ex){
					ErrorDumpUtil.ErrorLog("The error in encoding responce in remote access data action (getPersonalInfo) "+ex);
				}
				catch(Exception ex){
        	                        ErrorDumpUtil.ErrorLog("The error in sending responce in remote access data action (getPersonalInfo)  "+ex);
                	        }
                        	try{
                                	writeRespose(datas, data);
                        	}
	                        catch(Exception ex){
        		                ErrorDumpUtil.ErrorLog("The error in sending responce in remote access data action (getPersonalInfo)  "+ex);
                         	}
			}
                        else{
                                datas=null;
                                res="User does not exist and data is null";
                                ErrorDumpUtil.ErrorLog("remote access data action (getPersonalInfo)"+res);
                                writeRespose(datas,data);

                        }
                }
                else{
                        res="Hash is not matched, so that we will not send the data";
                        ErrorDumpUtil.ErrorLog("remote access data action (getPersonalInfo)"+res);
                        writeRespose(datas,data);
                }
	}
	/**
	 * This method is invoked When user want to access registration info
	 * @param data RunData instance
	 * @param context Context instance
	 */
	public void getRegistrationInfo(RunData data, Context context)
	{
		String res=null;
                String datas=null;
                //Getting value
                String email=data.getParameters().getString("email");
                String randomNo=data.getParameters().getString("rand") ;
                String hash=data.getParameters().getString("hash");
                String sourceid=data.getParameters().getString("srcid");
                //Getting the key from properties file
                String path=hdir+"/remote_auth/brihaspati3-remote-access.properties";
                String line=ReadNWriteInTxt.readLin(path,sourceid);
                String skey=StringUtils.substringBetween(line,";",";");
                //generate keyed hash
                String genHash=EncrptDecrpt.keyedHash(sourceid,randomNo,skey);
                if (hash.equals(genHash)){
                        //call usermanagement api
                        boolean flg=UserManagement.checkUserExist(email);
			if(!flg){
				Vector rData=RemoteData.getUsrRegInfo(email);
        	                //generate random number
                	        String randno=RandPasswordUtil.randmPass();
                        	//generate new keyed hash
	                        String genHashN=EncrptDecrpt.keyedHash(sourceid,randno,skey);
        	               	//genertare and write responce
                	       	datas=(rData.toString())+"&random="+randno+"&hash="+genHashN;
				try{
					// encript responce and encodeurl before writting
					datas=URLEncoder.encode((EncrptDecrpt.encrypt(datas,sourceid)),"UTF-8");
				}
				catch(java.io.UnsupportedEncodingException ex){
					ErrorDumpUtil.ErrorLog("The error in encoding responce in remote access data action (getRegistrationInfo) "+ex);
				}
				catch(Exception ex){
                	                ErrorDumpUtil.ErrorLog("The error in sending responce in remote access data action (getRegistrationInfo)  "+ex);
                        	}
	                        try{
        	                        writeRespose(datas, data);
                	        }
                        	catch(Exception ex){
                               		ErrorDumpUtil.ErrorLog("The error in sending responce in remote access data action (getRegistrationInfo)  "+ex);
                         	}
			}
                        else{
                                datas=null;
                                res="User does not exist and data is null";
                                ErrorDumpUtil.ErrorLog("remote access data action (getRegistrationInfo)"+res);
                                writeRespose(datas,data);
                        }
                }
                else{
                        res="Hash is not matched, so that we will not send the data";
                        ErrorDumpUtil.ErrorLog("remote access data action (getRegistrationInfo)"+res);
                        writeRespose(datas,data);
                }
	}
	/**
	 * This method is invoked When user want to access marks 
	 * @param data RunData instance
	 * @param context Context instance
	 */
	public void getInternalMarks(RunData data, Context context)
	{
		String res=null;
                String datas=null;
                //Getting value
                String email=data.getParameters().getString("email");
                String insid=data.getParameters().getString("iid");
                String progid=data.getParameters().getString("prgid");
                String randomNo=data.getParameters().getString("rand") ;
                String hash=data.getParameters().getString("hash");
                String sourceid=data.getParameters().getString("srcid");
                //Getting the key from properties file
                String path=hdir+"/remote_auth/brihaspati3-remote-access.properties";
                String line=ReadNWriteInTxt.readLin(path,sourceid);
                String skey=StringUtils.substringBetween(line,";",";");
                //generate keyed hash
                String genHash=EncrptDecrpt.keyedHash(sourceid,randomNo,skey);
                if (hash.equals(genHash)){
                        //call usermanagement api
                        boolean flg=UserManagement.checkUserExist(email);
			if(!flg){
				Vector rData=RemoteData.getStuMark(email,insid,progid);
        	                //generate random number
                	        String randno=RandPasswordUtil.randmPass();
                        	//generate new keyed hash
	                        String genHashN=EncrptDecrpt.keyedHash(sourceid,randno,skey);
        	               	//genertare and write responce
                	       	datas=(rData.toString())+"&random="+randno+"&hash="+genHashN;
				try{
					// encript responce and encodeurl before writting
					datas=URLEncoder.encode((EncrptDecrpt.encrypt(datas,sourceid)),"UTF-8");
				}
				catch(java.io.UnsupportedEncodingException ex){
					ErrorDumpUtil.ErrorLog("The error in encoding responce in remote access data action (getInternalMarks) "+ex);
				}
				catch(Exception ex){
                	                ErrorDumpUtil.ErrorLog("The error in sending responce in remote access data action (getInternalMarks)  "+ex);
                        	}
	                        try{
        	                        writeRespose(datas, data);
                	        }
                        	catch(Exception ex){
                               		ErrorDumpUtil.ErrorLog("The error in sending responce in remote access data action (getInternalMarks)  "+ex);
                         	}
			}
                        else{
                                datas=null;
                                res="User does not exist and data is null";
                                ErrorDumpUtil.ErrorLog("remote access data action (getInternalMarks)"+res);
                                writeRespose(datas,data);
                        }
                }
                else{
                        res="Hash is not matched, so that we will not send the data";
                        ErrorDumpUtil.ErrorLog("remote access data action (getInternalMarks)"+res);
                        writeRespose(datas,data);
                }
	}

/*
        private String api_current_version(){
	}
	
	private String api_latest_version(){
	}
	
	private String api_conf_current_version(){
	}

	private String api_conf_latest_version(){
        }

*/
	
	/**
	 * This method is invoked upon when user logging in
	 * @param data RunData instance
	 * @param context Context instance
	 */
	public void doPerform(RunData data,Context context) throws Exception
        {
                String action=data.getParameters().getString("aname","");
		ErrorDumpUtil.ErrorLog("I am in remote data action and value is "+action);
                if(action.equals("getIID"))
                        getInstituteId(data,context);
                if(action.equals("getRole"))
                        getRole(data,context);
        }
}
