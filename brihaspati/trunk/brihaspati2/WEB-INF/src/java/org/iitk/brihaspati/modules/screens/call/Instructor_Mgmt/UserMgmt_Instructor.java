package org.iitk.brihaspati.modules.screens.call.Instructor_Mgmt;

/*
 * @(#)UserMgmt_Instructor.java      
 *
 *  Copyright (c) 2005-2006 ETRG,IIT Kanpur. 
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
 */


import java.io.File;
import java.util.List;
import java.util.Vector;
import org.apache.turbine.util.RunData;
import org.apache.torque.util.Criteria;
import org.apache.velocity.context.Context;
import org.apache.turbine.om.security.User;
import org.iitk.brihaspati.modules.screens.call.SecureScreen;
import org.apache.turbine.services.security.torque.om.TurbineUserPeer;
import org.apache.turbine.util.parser.ParameterParser;
import org.iitk.brihaspati.modules.utils.AdminProperties;
import org.iitk.brihaspati.modules.utils.GroupUtil;
import org.iitk.brihaspati.modules.utils.UserUtil;
import org.iitk.brihaspati.modules.utils.MultilingualUtil;
import org.iitk.brihaspati.modules.utils.ListManagement;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.iitk.brihaspati.modules.utils.InstituteIdUtil;
import org.iitk.brihaspati.modules.utils.UserGroupRoleUtil;
import org.iitk.brihaspati.om.InstructorPermissionsPeer;
import org.iitk.brihaspati.om.InstructorPermissions;

	/*
         *   This class contains code for listing of all Instructor's
         *   List of Instructor for Institute admin and Instructor.
         *   @author  <a href="mail2sunil00@gmail.com">Sunil Yadav</a>
         */

public class UserMgmt_Instructor extends SecureScreen{

	public void doBuildTemplate( RunData data, Context context ){

		String institudeName=data.getParameters().getString("institudeName","");
		String mode=data.getParameters().getString("mode","");
        	String counter=data.getParameters().getString("count"," ");
        	context.put("tdcolor",counter);
        	context.put("mode",mode);
		String LangFile = null;
        	User user=data.getUser();
		context.put("course1",(String)user.getTemp("course_name"));
        	LangFile=(String)user.getTemp("LangFile");
        	String loginname=user.getName();
		String CourseList="";
		if(institudeName.equals("ListAll")){
			CourseList=data.getParameters().getString("cName","");	
			context.put("institudeName",institudeName);
		}else{
        		CourseList=(String)user.getTemp("course_id");
		}
        	context.put("cName",CourseList);
        	context.put("institudeName",institudeName);
		Vector userList=new Vector();
                Vector userpermission=new Vector();
                List users=null;
		
        	try {
                	String courseName=(String)user.getTemp("course_name");
	                context.put("course",courseName);
        	        context.put("tdcolor",counter);
	                if(CourseList.indexOf(loginname)>0){
				int uid=UserUtil.getUID(loginname);
                	        int GID=GroupUtil.getGID(CourseList);
                        	Criteria crit=new Criteria();
	                        crit.add(InstructorPermissionsPeer.USER_ID,uid);
        	                List l=InstructorPermissionsPeer.doSelect(crit);
                	        if(l.size()==0){
                        	        crit=new Criteria();
                                	crit.add(InstructorPermissionsPeer.USER_ID,uid);
	                                crit.add(InstructorPermissionsPeer.GROUP_NAME,GID);
        	                        crit.add(InstructorPermissionsPeer.PERMISSION_STATUS,1);
                	                InstructorPermissionsPeer.doInsert(crit);
                        	}
		                context.put("permission","1");
				context.put("flag","0");
                	}
			
 			else {
				int uid=UserUtil.getUID(loginname);
                                int GID=GroupUtil.getGID(CourseList);
                                Criteria crit=new Criteria();
				crit.add(InstructorPermissionsPeer.USER_ID,uid);
				List l=InstructorPermissionsPeer.doSelect(crit);
				if(l.size()>0){
					context.put("permission","1");
					context.put("flag",loginname);
					
				}else {
	                                context.put("permission","0");
					context.put("flag","0");
				}
			}
			
		}catch(Exception ep){}
		//}
        	try{
			context.put("tdcolor",counter);
                        int gid=GroupUtil.getGID(CourseList);
                        int current_user_id=UserUtil.getUID(user.getName());
                        String courseName=CourseList;
                        Vector UID=UserGroupRoleUtil.getUID(gid,2);
                        users=null;
                        for(int i=0;i<UID.size();i++)
                        {
                                int uid=Integer.parseInt(UID.elementAt(i).toString());
				Criteria crit=new Criteria();
                        	crit.add(InstructorPermissionsPeer.USER_ID,uid);
                        	List l=InstructorPermissionsPeer.doSelect(crit);
				if(l.size()>0) {
					for(int j=0;j<l.size();j++) {
						InstructorPermissions element=(InstructorPermissions)(l.get(j));	
					        if(i==0)
							userpermission.add(Integer.toString(element.getPermissionStatus()));
						else
							userpermission.add(1,Integer.toString(element.getPermissionStatus()));
					}
				} else {
					userpermission.add("0");
				}
				crit=new Criteria();
                                crit.add(TurbineUserPeer.USER_ID,uid);
                                try{
                                        users=TurbineUserPeer.doSelect(crit);
                                }
                                catch(Exception e){data.setMessage("The error in UserMgmt_Instructor !!"+e);}
				if(l.size()>0) {
					if(i==0)
						userList.add(users);
					else
						userList.add(1,users);
				}else
					userList.add(users);
                        }
                        String status=new String();
                        if(userList.isEmpty()){
                          String msg1=MultilingualUtil.ConvertedString("ins_msg",LangFile);
                          if(LangFile.endsWith("hi.properties"))
                           data.setMessage(courseName+" "+msg1);
                          else
                            data.setMessage(msg1+" "+courseName);
			    status="empty";
			} else{
				status="notempty";
				String path=data.getServletContext().getRealPath("/WEB-INF")+"/conf"+"/"+"Admin.properties";
                                int AdminConf = Integer.valueOf(AdminProperties.getValue(path,"brihaspati.admin.listconfiguration.value"));
				
                                context.put("AdminConf",new Integer(AdminConf));
                                context.put("AdminConf_str",Integer.toString(AdminConf));
                                ParameterParser pp=data.getParameters();
                                int startIndex=pp.getInt("startIndex",0);
				if(AdminConf != startIndex)
					context.put("as",0);
				else
					context.put("as",-1);
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
                                 	Vector userpermiss=ListManagement.listDivide(userpermission,startIndex,AdminConf);
					context.put("usrprms",userpermiss);
                        	}
                  	}
			context.put("course",courseName);
                        context.put("status",status);
                }
                catch(Exception e){
                        data.setMessage("The exception is :- "+e);
                }
        }
}


	
				
