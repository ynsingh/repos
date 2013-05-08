package org.iitk.brihaspati.modules.screens;

/*
 * @(#)Index.java	
 *
 *  Copyright (c) 2004, 2009,2010 ETRG,IIT Kanpur. 
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
 */
import java.util.Date;
import java.util.Vector;
import java.util.List;
import org.apache.torque.util.Criteria;
import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;
import org.apache.turbine.om.security.User;
import org.iitk.brihaspati.modules.utils.UserGroupRoleUtil;
import org.iitk.brihaspati.modules.utils.UserUtil;
import org.iitk.brihaspati.modules.utils.InstituteIdUtil;
import org.iitk.brihaspati.modules.screens.call.SecureScreen;
import org.iitk.brihaspati.om.UserConfigurationPeer;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.iitk.brihaspati.modules.utils.ExpiryUtil;
import org.iitk.brihaspati.modules.utils.CommonUtility;
import org.iitk.brihaspati.modules.utils.InstituteIdUtil;
import org.iitk.brihaspati.om.UserConfiguration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import javax.servlet.http.HttpSession;
import org.apache.turbine.services.session.TurbineSession;
import org.iitk.brihaspati.modules.utils.UsageDetailsUtil;
import org.iitk.brihaspati.modules.utils.CourseTimeUtil;
import org.iitk.brihaspati.modules.utils.ModuleTimeThread;
import org.iitk.brihaspati.modules.utils.LoginUtils;
import org.iitk.brihaspati.modules.utils.AdminProperties;
import org.apache.turbine.services.servlet.TurbineServlet;
import org.iitk.brihaspati.modules.utils.ActiveUserListThread;
import org.iitk.brihaspati.modules.utils.ActiveUserListController;
import org.apache.turbine.util.parser.ParameterParser;
import org.apache.commons.lang.StringUtils;

/**
 * @author <a href="mailto:sharad23nov@yahoo.com">Sharad Singh</a>
 * @author <a href="mailto:singh_jaivir@rediffmail.com">Jaivir Singh</a>
 * @author <a href="mailo:awadhk_t@yahoo.com">Awadhesh Kumar Trivedi</a>
 * @author <a href="mailto:smita37uiet@gmail.com">Smita Pal</a>
 * @author <a href="mailto:richa.tandon1@gmail.com">Richa Tandon</a>
 * @author <a href="mailto:sunil0711@gmail.com">Sunil Yadav</a>
 * @author <a href="mailto:sisaudiya.dewan17@gmail.com">Dewanshu singh sisaudiya</a>
 * @ mdified date 05-05-2010,13-07-2010,5-10-2010(Smita),23-12-2010
 * @ mdified date 04-04-2011 (Shaista),25-07-2011(Tej),23-08-2012(Sunil)
 */

public class Index extends SecureScreen{
	public void doBuildTemplate( RunData data, Context context ){
		try{
			ParameterParser pp=data.getParameters();
			String ip=data.getServerName();
                        String port=Integer.toString(data.getServerPort());
                        String sch=data.getServerScheme(); 
                        String ipadd=sch+"://"+ip+":"+port;
                        context.put("ipadd",ipadd);
			String viewAll=pp.getString("viewAll","");
			context.put("viewAll",viewAll);
                        /*
                         * getting the current user 
			 * & check current user is superAdmin,InsAdmin,Instructor,student or guest
                         */
			//CourseTimeUtil.getCalculation();
			String instName = "";
			User user=data.getUser();
                        String username=user.getName();
                        int uid=UserUtil.getUID(username);
			context.put("au",ActiveUserListThread.getController().getActUsersListSize());
			ActiveUserListThread.getController().setActiveUserId(uid);		
			Vector cId=new Vector();
			if(uid==1){
                                cId.add("admin");
				if(viewAll.equals("ViewAll")) {
	                        	context.put("uList",ActiveUserListController.getController().getUserListVector(cId,1));
				}
				else 
				{
					context.put("uList",ActiveUserListController.getController().getUserListVector(cId,0));
				}
			}else if(uid==0){
                                cId.add("guest");
				if(viewAll.equals("ViewAll"))
                                	context.put("uList",ActiveUserListController.getController().getUserListVector(cId,1));
                               else
				      	context.put("uList",ActiveUserListController.getController().getUserListVector(cId,0));
                        }
			else{
				/**
				 * Getting all institute id in a Vector in which logged in user is registered.
				 * Adding unique name of the institute according to institute id in a Vector.
				 * Putting the vector of institute name in context.
				**/
				Vector instNameList = new Vector();
				cId=(InstituteIdUtil.getAllInstId(uid));
				for(int inst = 0; inst < cId.size(); inst ++){
					instName = InstituteIdUtil.getIstName(Integer.parseInt(cId.get(inst).toString()));
					if(!instNameList.contains(instName)){
						instNameList.add(instName);
					}
				}
				context.put("instNameList",instNameList);
				if(viewAll.equals("ViewAll")){
					context.put("uList",ActiveUserListController.getController().getUserListVector((InstituteIdUtil.getAllInstId(uid)),1));
				}else{
					context.put("uList",ActiveUserListController.getController().getUserListVector(InstituteIdUtil.getAllInstId(uid),0));
				}
			}
			/*
			 *Code for ActiveUserList.
			 */

			context.put("Uid",uid);
			/** 
			 *	code for Photo display 
			 */
				Criteria crt=new Criteria();
			        crt.add(UserConfigurationPeer.USER_ID,uid);
			        List qu=UserConfigurationPeer.doSelect(crt);
				context.put("Image",qu);
			String flname=UserUtil.getFullName(uid);
			String fname=user.getFirstName();
			String lname=user.getLastName();
                        context.put("username",username);
                        context.put("firstname",fname);
                        context.put("lastname",lname);
			if((fname==null) || (lname ==null)){
			context.put("fullname",username);
			}
			else{
			context.put("fullname",fname+lname);
			}
			context.put("flname",flname);
			String lang=user.getTemp("lang").toString();
                        context.put("lang",lang);
			lang = "";
			user.setTemp("role","");
			user.setTemp("modulename","");
			user.setTemp("mInststat","");
			user.setTemp("course_id","");
			user.setTemp("Institute_id","");
			user.setTemp("DomainName","");
		// check for Admin Role
			Vector Admin_Role=UserGroupRoleUtil.getGID(uid,1);
		// check for Instructor Role
			Vector Instructor_Role=UserGroupRoleUtil.getGID(uid,2);
		// check for Student Role
			Vector Student_Role=UserGroupRoleUtil.getGID(uid,3);
		// check for GroupAdmin Role
			Vector GAdmin_Role=UserGroupRoleUtil.getGID(uid,4);
		// check for ContentAuthor Role
			Vector Author_Role=UserGroupRoleUtil.getGID(uid,5);
		// check for InstituteAdmin Role
			Vector InstituteAdmin_Role=UserGroupRoleUtil.getGID(uid,7);
		// check for ta Role
			Vector TeacherAssistant_Role=UserGroupRoleUtil.getGID(uid,8);

			if(Admin_Role.size()!=0)
			{
	                        context.put("Role1","AdminRole");
				//user.setTemp("role","turbine_root");
			}

			if(Instructor_Role.size()!=0)
			{
                        	context.put("Role2","InstructorRole");
			}
			
			if(Student_Role.size()!=0)
			{
                        	context.put("Role3","StudentRole");
			}

			if(GAdmin_Role.size()!=0)
			{
                        	context.put("Role4","GAdminRole");
			}

			if(Author_Role.size()!=0)
			{
                        	context.put("Role5","AuthorRole");
			}
			if(InstituteAdmin_Role.size()!=0)
			{
				context.put("Role7","InstituteAdminRole");
			}
			if(TeacherAssistant_Role.size()!=0)
                        {
                                context.put("Role8","TeacherAssistantRole");
                        }
	
			if(user.getName().equals("guest")){
				context.put("guest_login","true");
			}
			else{
				context.put("guest_login","false");
			}
			/**
                          * Getting message if any required field is empty 
                          */
                        String LangFile=(String)data.getUser().getTemp("LangFile");
                        String Mssg = CommonUtility.CheckData(username,LangFile);
                        context.put("message",Mssg);
			Date date=new Date();
			LoginUtils.getChangePasswordtemp(date,uid,data);
			try{	
				 /*entry id fron USAGE_DETAILS*/
                	        int eid1=UsageDetailsUtil.getentryId(uid);
                       		 /*entry id from COURSE_TIME */
                	        int eid2=CourseTimeUtil.getentryid(uid);
				if(eid1==eid2)
                       		 {
					ModuleTimeThread.getController().CourseTimeSystem(uid,eid2);			
                               	}
		
			} catch(Exception e){ ErrorDumpUtil.ErrorLog("The error is :- "+e); }
			try{
                                /**
                                 * Getting tweets expirey from admin properties 
                                 */
                                String current_date=ExpiryUtil.getCurrentDate("-");
                                String path=TurbineServlet.getRealPath("/WEB-INF")+"/conf"+"/"+"Admin.properties";
                                String twtexp = AdminProperties.getValue(path,"brihaspati.admin.twtexpiry.value");
                                int texp=Integer.parseInt(twtexp);
                                texp=texp-1;
                                String expdate=ExpiryUtil.getExpired(current_date,texp);
                                context.put("expdate",expdate);

                        } catch(Exception error){ ErrorDumpUtil.ErrorLog("The error is :- "+error); }

			                        /*poll default institute name*/
                        Iterator iter = cId.iterator();
                        if (iter.hasNext()) {
                                Object instFirst = iter.next();
                                context.put("instN",instFirst);
                                }
                        context.put("usrNme", username);
                        context.put("UserId",uid);


		}
		catch(Exception e){
			data.setMessage("The error is :- "+e);
			}
	}
}
