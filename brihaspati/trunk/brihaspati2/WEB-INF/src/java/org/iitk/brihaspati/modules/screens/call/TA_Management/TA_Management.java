package org.iitk.brihaspati.modules.screens.call.TA_Management;

/*
 * @(#)TA_Management.java 
 *
 *  Copyright (c) 2010 ETRG,IIT Kanpur.
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
 *
 *  Contributors: Members of ETRG, I.I.T. Kanpur
 *
 */



import java.io.File;
import java.util.List;
import java.util.Vector;
import org.apache.turbine.util.RunData;
import org.apache.torque.util.Criteria;
//import org.apache.velocity.context.Context;
//import org.apache.turbine.om.security.User;
//import org.iitk.brihaspati.modules.screens.call.SecureScreen;
import org.apache.turbine.services.security.torque.om.TurbineUserPeer;
import org.apache.turbine.util.parser.ParameterParser;
import org.iitk.brihaspati.modules.utils.AdminProperties;
import org.iitk.brihaspati.modules.utils.GroupUtil;
import org.iitk.brihaspati.modules.utils.UserUtil;
import org.iitk.brihaspati.modules.utils.UserGroupRoleUtil;
import org.iitk.brihaspati.modules.utils.MultilingualUtil;
import org.iitk.brihaspati.modules.utils.ListManagement;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;

import org.iitk.brihaspati.om.CourseModulePeer;
import org.iitk.brihaspati.om.CourseModule;
import org.iitk.brihaspati.om.ModulePermissionPeer;
import org.iitk.brihaspati.om.ModulePermission;


 	/**
         *   This class contains code for disply all assignment
         *   only instructor and do update/delete
         *   @author  <a href="mail2sunil00@gmail.com">Sunil Yadav</a>
         */

public class TA_Management extends org.iitk.brihaspati.modules.screens.call.SecureScreen {

        public void doBuildTemplate( RunData data, org.apache.velocity.context.Context context ){
		String LangFile = null;
	
		try {	
			//User user = data.getUser();	
			org.apache.turbine.om.security.User user = data.getUser();	
			LangFile=(String)user.getTemp("LangFile");
			ParameterParser pp=data.getParameters();

			String mode=data.getParameters().getString("mode","");
                        ErrorDumpUtil.ErrorLog("mode===============>>" +mode);
                        context.put("mode",mode);
	                String counter=data.getParameters().getString("count"," ");
	                context.put("tdcolor",counter);
			context.put("course1",(String)user.getTemp("course_name"));			
			int gid=GroupUtil.getGID(user.getTemp("course_id").toString());
			int current_user_id=UserUtil.getUID(user.getName());
			String courseName=(String)user.getTemp("course_name");
			Vector UID=UserGroupRoleUtil.getUID(gid,8);
			Vector userList=new Vector();
			List users=null;
		
			String uname = pp.getString("username","");
                        int userid=UserUtil.getUID(uname);
                        context.put("username",uname);

			for(int i=0;i<UID.size();i++)
	                        { 
	                                int uid=Integer.parseInt(UID.elementAt(i).toString());
	                                Criteria crit=new Criteria();
	                                crit.add(TurbineUserPeer.USER_ID,uid);
	                                try{
	                                        users=TurbineUserPeer.doSelect(crit);
	                                }
        	                        catch(Exception e){data.setMessage("The error in userdeatails !!"+e);}
	                                userList.addElement(users);
					
        	                }
				String status=new String();
			
	                        if(userList.isEmpty())
				{
	                                String msg1=MultilingualUtil.ConvertedString("ta_msg1",LangFile);
	                                if(LangFile.endsWith("hi.properties"))
	                                        data.setMessage(msg1);
	                                else
	                                        data.setMessage(msg1);

                                	status="empty";
                        	}
		 		else
				{
	                                status="notempty";
	                                String path=data.getServletContext().getRealPath("/WEB-INF")+"/conf"+"/"+"Admin.properties";
	                                int AdminConf = Integer.valueOf(AdminProperties.getValue(path,"brihaspati.admin.listconfiguration.value"));
	
	                                context.put("AdminConf",new Integer(AdminConf));
	                                context.put("AdminConf_str",Integer.toString(AdminConf));
	                                //ParameterParser pp=data.getParameters();
	                                int startIndex=pp.getInt("startIndex",0);
                                	int t_size=userList.size();
	                                if(userList.size()!=0){
                                        int value[]=new int[7];
                                        value=ListManagement.linkVisibility(startIndex,t_size,AdminConf);

                                        int k=value[6];
                                        context.put("k",String.valueOf(k));

                                        Integer total_size=new Integer(t_size);
                                        context.put("total_size",total_size);

                                        int eI=value[1];
                                        Integer endIndex=new Integer(eI);
                                        context.put("endIndex",endIndex);
                                        int check_first=value[2];
                                        context.put("check_first",String.valueOf(check_first));

                                        int check_pre=value[3];
                                        context.put("check_pre",String.valueOf(check_pre));
                                        int check_last1=value[4];
                                        context.put("check_last1",String.valueOf(check_last1));

                                        int check_last=value[5];
                                        context.put("check_last",String.valueOf(check_last));
                                        context.put("startIndex",String.valueOf(eI));
                                        Vector splitlist=ListManagement.listDivide(userList,startIndex,AdminConf);
					context.put("userdetail",splitlist);
		
                        }
                        context.put("course",courseName);
                        context.put("status",status);	

			}

	
	 }catch(Exception e){
                        data.setMessage("The exception is :- "+e);
                }


	}
	
}
