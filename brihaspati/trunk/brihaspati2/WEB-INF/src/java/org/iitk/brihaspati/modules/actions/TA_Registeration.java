package org.iitk.brihaspati.modules.actions;

/*
 * @(#)TA_Registeration.java     
 *
 *  Copyright (c) 2005,2010 ETRG,IIT Kanpur. 
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


import java.io.File;
import java.util.Properties;
import java.util.List;
import java.util.Vector;
import java.util.StringTokenizer;
import org.apache.velocity.context.Context;
import org.apache.turbine.util.RunData;
import org.apache.torque.util.Criteria;
import org.apache.turbine.util.parser.ParameterParser;
import org.iitk.brihaspati.modules.utils.UserManagement;
import org.iitk.brihaspati.modules.utils.UserUtil;
import org.iitk.brihaspati.modules.utils.GroupUtil;
//import org.iitk.brihaspati.modules.utils.InstituteIdUtil;
import org.iitk.brihaspati.modules.utils.MultilingualUtil;
//import org.iitk.brihaspati.modules.utils.StringUtil;
import org.apache.turbine.om.security.User;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;

import org.iitk.brihaspati.om.TurbineUserGroupRolePeer;
//import org.iitk.brihaspati.om.CourseModulePeer;
//import org.iitk.brihaspati.om.CourseModule;
import org.iitk.brihaspati.om.ModulePermissionPeer;
import org.iitk.brihaspati.om.ModulePermission;
//import org.iitk.brihaspati.om.InstructorPermissions;
//import org.iitk.brihaspati.modules.utils.CourseManagement;
import org.iitk.brihaspati.modules.actions.SecureAction_Instructor;

/**
 * This class is responsible for different actions of Teacher assistant.
 * @author <a href="mailto:mail2sunil00@gmail.com">Sunil Yadav</a>
 */

public class TA_Registeration extends SecureAction_Instructor {
	private String LangFile=null;
	String msg="";

	/*
	 * doRegister method is used to Registration of new Teacher Assist.
         * Teacher Assistant Profile is created using UserManagement util.
	 */
	
	public void doRegister(RunData data, Context context)	{
		
		MultilingualUtil m_u=new MultilingualUtil();
		
		try {
		
			User user = data.getUser();
			String gName, email, fname, lname, passwd; 
			LangFile=(String)data.getUser().getTemp("LangFile");
			ParameterParser pp = data.getParameters();
			gName = (String)user.getTemp("course_id");
			email = pp.getString("EMAIL");
			fname = pp.getString("FNAME");
			lname = pp.getString("LNAME");
			passwd = pp.getString("PASSWD");
			String inst_id=(data.getUser().getTemp("Institute_id")).toString();
			if(passwd.equals("")){
				String []starr=email.split("@");
				passwd =starr[0];
			}
			String mail_msg="";
			String serverName=data.getServerName();
			int srvrPort=data.getServerPort();
			String serverPort=Integer.toString(srvrPort);
			String msg=UserManagement.CreateUserProfile(email,passwd,fname,lname,"",email,gName,"teacher_assistant",serverName,serverPort,LangFile,"","","");
			data.setMessage(msg +" "+ mail_msg);			

		} catch(Exception ex) {
			data.setMessage("The error Teacher assist. registration !!!"+ex);
			//ErrorDumpUtil.ErrorLog("The error !!!" +ex.getMessage());
		}

	}

	/*
	 * This Method is used for Removing Teacher assistant role in course.	
	 */

	public void doRemoveUser(RunData data, Context context)	 {
	
                User user=data.getUser();
	        ParameterParser pp=data.getParameters();
        	String mid_delete = pp.getString("deleteFileNames","");
			
		try{
                        if(!mid_delete.equals("")) {
                                java.util.StringTokenizer st=new java.util.StringTokenizer(mid_delete,"^");
                                for(int j=0;st.hasMoreTokens();j++)  {
                                        int uid=UserUtil.getUID(st.nextToken());
                                        int GID=GroupUtil.getGID((String)user.getTemp("course_id"));
                                        Criteria crit=new Criteria();
                                        crit.add(TurbineUserGroupRolePeer.USER_ID,uid);
                                        crit.add(TurbineUserGroupRolePeer.GROUP_ID,GID);
                                        crit.add(TurbineUserGroupRolePeer.ROLE_ID,8);
                                        TurbineUserGroupRolePeer.doDelete(crit);
                                }
                                msg=MultilingualUtil.ConvertedString("brih_remta",LangFile);
                        }
                        data.addMessage(msg);
                }catch (Exception ex){ data.setMessage("Error in Removing Teacher Assistant !!  " +ex); }
        }

	
	/*
	 * doSelect Method is used for providing course Module authorization 
	 * permission to Teacher assistant.
	 */

	 public void doSelect(RunData data, Context context)
        {
                try{
			User user=data.getUser();
			ParameterParser pp = data.getParameters();
                        LangFile=(String)data.getUser().getTemp("LangFile");
                        int InstId =Integer.parseInt((String)data.getUser().getTemp("Institute_id"));
                        String moduleList=data.getParameters().getString("selectFileNames","");
                        context.put("selectFile",moduleList);
                        String moduleId="";
			String uname = pp.getString("username","");
			int userid=UserUtil.getUID(uname);
			context.put("username",uname);
			int gid=GroupUtil.getGID(user.getTemp("course_id").toString());
                        /**
                         * Use StringTokenizer to break string after "^".
                         */
                        StringTokenizer st=new StringTokenizer(moduleList,"^");
                        Vector v=new Vector();

                        for(int i=0;st.hasMoreTokens();i++)
                        {
                                v.addElement(st.nextToken());
                        }
                        /**
                         * All the moduleId obtained from the list 
                         * then insert into table one by one
                         */
                        for(int i=0;i<v.size();i++)
                        {
                                moduleId=v.elementAt(i).toString();
                                Criteria crit=new Criteria();
                                crit.add(ModulePermissionPeer.MODULE_ID,moduleId);
                                crit.add(ModulePermissionPeer.USER_ID,userid);
                                crit.add(ModulePermissionPeer.GROUP_NAME,gid);
                                crit.add(ModulePermissionPeer.INSTITUTE_ID,InstId);
                                crit.add(ModulePermissionPeer.MODULE_STATUS,1);
                                ModulePermissionPeer.doInsert(crit);
                                String Instmsg = MultilingualUtil.ConvertedString("brih_authcm",LangFile);
                                String Slmsg= MultilingualUtil.ConvertedString("brih_successfully",LangFile);
                                data.setMessage(Instmsg+" "+Slmsg);
                        }

                }
                catch(Exception ex)
		{
                        ErrorDumpUtil.ErrorLog("Error in Course Module authorization !!"+ex);
                }
        }

	/*
	 * This method is used to Disable the Module authorization of Teacher assistant.
	 */
	
		
	 public void doRemoveModulePermission(RunData data, Context context) {
                
		User user=data.getUser();
                String gName="";
                ParameterParser pp=data.getParameters();
                gName=(String)user.getTemp("course_id");
                String inst_id=(data.getUser().getTemp("Institute_id")).toString();
		String uname = pp.getString("username","");
		String module_id = pp.getString("moduleId");
		int uid=UserUtil.getUID(uname);
		int GID=GroupUtil.getGID(gName);
                try{
                                        Criteria crit=new Criteria();
                                        crit.add(ModulePermissionPeer.USER_ID,uid);
                                        crit.add(ModulePermissionPeer.MODULE_ID,module_id);
                                        crit.add(ModulePermissionPeer.GROUP_NAME,GID);
                                        crit.add(ModulePermissionPeer.INSTITUTE_ID,inst_id);
                                        ModulePermissionPeer.doDelete(crit);
                                        ErrorDumpUtil.ErrorLog(" crit-------------" +crit);
                                msg=MultilingualUtil.ConvertedString("brih_cmpermission",LangFile);
                        	data.addMessage(msg);
                }catch (Exception ex){ data.setMessage("Error in Removing Module Permission !!  " +ex); }
        }

	

	 public void doPerform(RunData data,Context context) throws Exception{
                String action=data.getParameters().getString("actionName","");
                LangFile=(String)data.getUser().getTemp("LangFile");
                if(action.equals("eventSubmit_doRegister")) {
                        doRegister(data,context);
                } else if(action.equals("eventSubmit_doRemoveUser")){
                        doRemoveUser(data,context);
		} else if(action.equals("eventSubmit_doSelect")){
                        doSelect(data,context);
                } else if(action.equals("eventSubmit_doRemoveModulePermission")){
                        doRemoveModulePermission(data,context);
                }
		 else {
                        String c_msg=MultilingualUtil.ConvertedString("c_msg",LangFile);
                        data.setMessage(c_msg);
                }
        }
} 
