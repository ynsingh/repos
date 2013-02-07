package org.iitk.brihaspati.modules.actions;

/*
 * @(#)UserManagement_RemoveUser.java	
 *
 *  Copyright (c) 2005,2008,2009,2012 ETRG,IIT Kanpur. 
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

/** 
 * @author  <a href="awadhesh_trivedi@yahoo.co.in">Awadhesh Kumar Trivedi</a>
 * @author  <a href="singh_jaivir@rediffmail.com">Jaivir Singh</a>29oct2012
 * @author  <a href="palseema@rediffmail.com">Manorama Pal</a>
 * @author  <a href="shaistashekh@hotmail.com">Shaista</a>
 * @modified date: 08-08-2012
 */

import java.util.Vector;
import java.util.List;
import java.util.StringTokenizer;
import org.apache.torque.util.Criteria; 
import org.apache.velocity.context.Context;
import org.apache.turbine.util.RunData;
import org.apache.turbine.om.security.User;
import org.apache.turbine.services.security.torque.om.TurbineUserPeer;
import org.apache.turbine.services.security.torque.om.TurbineUser;
import org.apache.turbine.util.parser.ParameterParser;
import org.iitk.brihaspati.modules.utils.UserManagement;
import org.iitk.brihaspati.modules.utils.CourseManagement;
import org.iitk.brihaspati.modules.utils.GroupUtil;
import org.iitk.brihaspati.modules.utils.CourseUserDetail;
import org.iitk.brihaspati.modules.utils.UserGroupRoleUtil;
import org.iitk.brihaspati.modules.utils.MultilingualUtil;
import org.iitk.brihaspati.modules.utils.MailNotification;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.apache.turbine.services.servlet.TurbineServlet;
import org.iitk.brihaspati.modules.utils.UserUtil;
import org.iitk.brihaspati.modules.utils.InstituteIdUtil;
import org.iitk.brihaspati.modules.utils.InstituteDetailsManagement;

public class UserManagement_RemoveUser extends SecureAction_Admin{
	/**
          * ActionEvent responsible for search user in a specified group or all
          * @param data RunData
          * @param context Context
          * @return nothing
          */
	private String LangFile=null;
	public void doSearch(RunData data,Context context)
	{
		MultilingualUtil m_u=new MultilingualUtil();

		try{

			/**
	                 * Getting the value of file from temporary variable 		
			 * According to selection of Language.  	
			 */
			LangFile=(String)data.getUser().getTemp("LangFile");
			ParameterParser pp=data.getParameters();
			String status=new String();
		
			/**
		 	* Get the group and role selected by the admin from the previous screen
		 	*/
			String instName=pp.getString("group");
			context.put("group",instName);
			String role=pp.getString("role");
			context.put("role",role);
			int roleId=0;
			if(role.equals("instructor"))
				roleId=2;
			else
				roleId=3;

			Vector g=new Vector();
			List user=null;
			Vector user_list=new Vector();
			if(!instName.equals("")){  

				/**
			 	* This 'if' is executed when the users to be
			 	* listed are for a specific group selected by
			 	* the user
			 	*/
				boolean flag=false;
				if(!instName.equals("All")){
					int instId=InstituteIdUtil.getIst_Id(instName); 
					try{
						Vector CourseList=InstituteDetailsManagement.getInstituteCourseDetails(Integer.toString(instId));
						context.put("courseList",CourseList);
						if(CourseList.size()==0)
						{
							String c_msg01=m_u.ConvertedString("course_exists",LangFile);
                                        		data.setMessage(c_msg01+""+instName);
							//data.setMessage("Courses are not exists in this Institute"+" "+instName);
							flag=true;
						}	
					}catch(Exception e){data.setMessage("error testing"+e);}

					/**
				 	* Find the group id depending on the group name obtained
				 	* @see GroupUtil in utils
				 	*/
					if(flag==false){
						String gName=pp.getString("Cgroup","");
						if(!gName.equals("")){
							if(gName.endsWith(Integer.toString(instId))){
                        					context.put("Cgroup",gName);
								int GID=GroupUtil.getGID(gName);

								/**
				 				* Get all the user ids having the specified role in the
				 				* group selected
				 				* @see UserGroupRoleUtil in utils
				 				*/

								Vector UID=UserGroupRoleUtil.getUID(GID,roleId);

								/**
				 				* For all the user ids obtained above, add the user details in
				 				* a vector and the name of group in another vector
				 				*/

								for(int i=0;i<UID.size();i++){  
									int uid=Integer.parseInt(UID.elementAt(i).toString());

									Criteria crit=new Criteria();
									crit.add(TurbineUserPeer.USER_ID,uid);
									try{
										user=TurbineUserPeer.doSelect(crit);
									}catch(Exception e){}
									user_list.addElement(user);
									/**
						 			* Obtaining the list of all groupId of particular user and role, in vector
									*/
									Vector gid=UserGroupRoleUtil.getGID(uid,roleId);      
									for(int j=0;j<gid.size();j++){ 
										String gname=GroupUtil.getGroupName(Integer.parseInt(gid.elementAt(j).toString()));
										if(gName.equals(gname))
											g.addElement(gname);
									} 
								} 
								/**
				 				* If no users are found satisfying the condition, then the
				 				* proper error message should be displayed. Else the vectors
				 				* having user details and group should be put into context.
				 				*/

								if(user_list.size()==0){ 
									if(roleId==3){ 
										String stu_msgc=m_u.ConvertedString("stu_msgc",LangFile);
										data.setMessage(stu_msgc +" "+gName);
									}


									else{ 
										String ins_msg=m_u.ConvertedString("ins_msg",LangFile);
										data.setMessage(ins_msg +" "+gName);
									} 

									status="empty";
								} 
								else{ 
									status="notempty";
									context.put("selected_users",user_list);
									context.put("groupname",g);
								}              
							} //ifgName
						} //ifgName
					}//flag
				}//ALL
			
				/**
			 	* This 'else' part is executed when the user
			 	* list is required for all groups
			 	*/
				else{ 
					/**
			 		* Get all the user ids with specific role i.e. either 
			 		* 'instructor' or 'student'
			 		*/

					Vector AUID=UserGroupRoleUtil.getAllUID(roleId);

					/**
			 		* For all the user ids obtained above, add the user details in
			 		* a vector and the name of group in another vector
			 		*/

					for(int i=0;i<AUID.size();i++){  
						int uid=Integer.parseInt(AUID.elementAt(i).toString());
						Vector gid=UserGroupRoleUtil.getGID(uid,roleId);

						for(int j=1;j<=gid.size();j++){ 
							Criteria crit=new Criteria();
							crit.add(TurbineUserPeer.USER_ID,uid);
							try {
								user=TurbineUserPeer.doSelect(crit);
							}catch(Exception e){}
							user_list.addElement(user);
						} 

						for(int j=0;j<gid.size();j++){ 
							String gname=GroupUtil.getGroupName(Integer.parseInt(gid.elementAt(j).toString()));
							g.addElement(gname);
						} 
	                   		} 

					/**
				 	* If no users are found satisfying the condition, then the
				 	* proper error message should be displayed. Else the vectors
				 	* having user details and group should be put into context.
				 	*/

					if(user_list.size()==0){ 
						if(roleId==3){ 

							String stu_msg=m_u.ConvertedString("stu_msg",LangFile);
							data.setMessage(stu_msg);
						} 

						else{ 
						
							String ins_msg1=m_u.ConvertedString("ins_msg1",LangFile);
							data.setMessage(ins_msg1);
						} 


						status="empty";
					} 
					else{ 
						status="notempty";
						context.put("selected_users",user_list);
						context.put("groupname",g);
					} 
				}//else  
			}//null instcheck 
			else{ 

				String c_msg12=m_u.ConvertedString("c_msg12",LangFile);
				data.setMessage(c_msg12);
				//Course is not selected
			} 

			context.put("status",status);
		}//try
		catch(Exception e){data.setMessage("Error in Searching"+e);}
	}//method
	/**
          * ActionEvent responsible for removing a users(Instructors or Students) from the system 
          * @param data RunData
          * @param context Context
          */
	
	public void doRemoveUser(RunData data, Context context)
	{
		try{
			MultilingualUtil m_u=new MultilingualUtil();
			ParameterParser pp=data.getParameters();
                	String loginName = data.getUser().getName();

			/**
	                 * Getting the value of file from temporary variable 		
			 * According to selection of Language.  	
			 */
			LangFile=(String)data.getUser().getTemp("LangFile");
			Vector Err_Msg=new Vector();
			Vector Err_AllMsg=new Vector();
			CourseUserDetail CuDetail;
			UserManagement umt=new UserManagement();	
		
			/**
		 	* Get the list of users for removal and role
		 	*/

			String userList=pp.getString("deleteFileNames","");
			String user_role=pp.getString("role");
			context.put("role",user_role);
			StringTokenizer st=new StringTokenizer(userList,"^");

			/***
			*Get Server Name and Server Port 
			*For Using in Mail on deletion
			*/
			StringTokenizer st1;
                        //String server_name=TurbineServlet.getServerName();
                        //String srvrPort=TurbineServlet.getServerPort();
			String postString="";
                        String email="";
                        String message="";
			String[] msg;
			String uid="";
			String fileName=TurbineServlet.getRealPath("/WEB-INF/conf/brihaspati.properties");
			String subject="";
			int index;
			TurbineUser element;

			System.gc();

			/**
		 	* The 'if' loop is executed if the user list is not empty
		 	*/
			if(!userList.equals(""))
			{
				if(user_role.equals("student"))
				{
					String preString="";
					while(st.hasMoreTokens())
					{
						String s=st.nextToken();
                                		index=s.indexOf(":",0);

                                		/**
                                 		* 'preString' has the group name
                                 		* 'postString' has the user name
                                 		*/

                                		preString=s.substring(0,index);
                               	 		postString=s.substring(index+1);
						CuDetail=new CourseUserDetail();
						/**
						*Send Mail on deletion User
						*/
						//subject=checkUserAvailabilityDifferntGroup(postString,3,srvrPort);
						subject=checkUserAvailabilityDifferntGroup(postString,3);
						uid=Integer.toString(UserUtil.getUID(postString));
						//TurbineUser element=(TurbineUser)UserManagement.getUserDetail(uid).get(0);
						element=(TurbineUser)UserManagement.getUserDetail(uid).get(0);
                        	        	email=element.getEmail();
						/**
						 * Delete all student one by one
						 */
						
						//message=umt.removeUserProfileWithMail(postString,preString,LangFile,subject,email,"",loginName,"","",fileName,server_name,srvrPort);
						message=umt.removeUserProfileWithMail(postString,preString,LangFile,subject,email,"",loginName,"","",fileName);
						msg = message.split(":");
			                	data.setMessage(msg[0]);
	                                        CuDetail.setErr_User(postString);
        	                                CuDetail.setErr_Type(msg[1]);
                	                        Err_Msg.add(CuDetail);
						element= null;
					}

				}
				else
				{
				String msg1="", not_rem="";
				for(int i=0;st.hasMoreTokens();i++)
                                        {
                                                String s=st.nextToken();
                                                index=s.indexOf(":",0);
                                                /**
                                                * 'preString' has the group name
                                                * 'postString' has the user name
                                                */
                                                String preString=s.substring(0,index);
                                                postString=s.substring(index+1);
						String dmnarray[]=preString.split("@");
						String dmn=dmnarray[1];
						String iddmnarray[]=dmn.split("_");
						String iid=iddmnarray[1];
                                                //subject=checkUserAvailabilityDifferntGroup(postString,2,srvrPort);
						subject=checkUserAvailabilityDifferntGroup(postString,2);
                                                /**
                                                * Check if the course obtained above is active
                                                * or inactive and PrimaryInstructor
                                                * @see CourseManagement from Utils
                                                */
                                                boolean check_Primary=CourseManagement.IsPrimaryInstructor(preString,(postString+"_"+iid));
                                                int gId=GroupUtil.getGID(preString);
						boolean check_Active=CourseManagement.CheckcourseIsActive(gId);
                                                /**
                                                * Check if the user is not a primary instructor in
                                                * the course If he is and the course is not active
                                                * then remove user otherwise executed else
                                                */
						if(check_Primary==true)
                                                {
                                                        if(check_Active==true)
							{
								/**
								*Send Mail on deletion	
								*/
								uid=Integer.toString(UserUtil.getUID(postString));
								//TurbineUser element=(TurbineUser)UserManagement.getUserDetail(uid).get(0);
								element=(TurbineUser)UserManagement.getUserDetail(uid).get(0);
                                				email=element.getEmail();
								//message=umt.removeUserProfileWithMail(postString,preString,LangFile,subject,email, "",loginName,"","",fileName,server_name,srvrPort);
								message=umt.removeUserProfileWithMail(postString,preString,LangFile,subject,email, "",loginName,"","",fileName);
								msg = message.split(":");
				                		data.setMessage(msg[0]);
 								if(umt.flag.booleanValue()==false)
                                                                {
                                                                	CuDetail=new CourseUserDetail();
                                                                        CuDetail.setErr_User(postString);
                                                                        CuDetail.setErr_Type(msg[1]);
                                                                        Err_Msg.add(CuDetail);
                                                                }
                                                                /**
                                                                * Delete the entries of the course from the
                                                                * related tables
                                                                */
                                                                //String msg1=CourseManagement.RemoveCourse(preString,"ByCourseMgmt",LangFile);
                                                                msg1=CourseManagement.RemoveCourse(preString,"ByCourseMgmt",LangFile);

                                                        }
                                       	 		else
                                        		{
                                                		CuDetail=new CourseUserDetail();
                                                		CuDetail.setErr_User(postString);
                                        			//String not_rem=m_u.ConvertedString("remove_msg",LangFile);
                                        			not_rem=m_u.ConvertedString("remove_msg",LangFile);
								CuDetail.setErr_Type(not_rem+" " +preString);

                                                		Err_Msg.add(CuDetail);
                                        		}
						}
						else
						{
							/**
							*Send Mail on deletion
							*/
	                                                //subject=checkUserAvailabilityDifferntGroup(postString,2,srvrPort);
							subject=checkUserAvailabilityDifferntGroup(postString,2);
							uid=Integer.toString(UserUtil.getUID(postString));
							//TurbineUser element=(TurbineUser)UserManagement.getUserDetail(uid).get(0);
							element=(TurbineUser)UserManagement.getUserDetail(uid).get(0);
                                			email=element.getEmail();
							//message=umt.removeUserProfileWithMail(postString,preString,LangFile,subject,email, "", loginName,"","",fileName,server_name,srvrPort);
							message=umt.removeUserProfileWithMail(postString,preString,LangFile,subject,email, "", loginName,"","",fileName);
							msg = message.split(":");
			                		data.setMessage(msg[0]);
                                                        if(umt.flag.booleanValue()==false)
                                                        {
                                                        	CuDetail=new CourseUserDetail();
                                                                CuDetail.setErr_User(postString);
                                                                CuDetail.setErr_Type(msg[1]);
                                                                Err_Msg.add(CuDetail);
                                                         }
						
						}
                                	}
				element =null;
				not_rem = "";
				msg1 = "";
                                }
				context.put("error_user",Err_Msg);
			}
			else{

				
				String sel_the=m_u.ConvertedString("selThe",LangFile);
				String for_rem=m_u.ConvertedString("forRemove",LangFile); 
				data.setMessage(sel_the+" " +user_role+" "+ for_rem);		

			}
		}
		catch (Exception ex)
		{

			data.setMessage("The Error in Removing User" +ex);
			
			

		}

	}
	//public String checkUserAvailabilityDifferntGroup( String userName, int roleId, String srvrPort)
	public String checkUserAvailabilityDifferntGroup( String userName, int roleId)
	{
		String subject="";
                int userId=UserUtil.getUID(userName);
                Vector studGrp=UserGroupRoleUtil.getGID(userId,roleId);
		if( (studGrp.size() > 1) )
                {
                	//if(srvrPort.equals("8080"))
                                 subject="deleteFromGroup$newUser";
			//else
			//	subject="deleteFromGrouphttps$newUserhttps";
		}
                else
		{                 
                	//if(srvrPort.equals("8080"))
                        	subject="deleteUser$newUser";
			//else
                        //	subject="deleteUserhttps$newUserhttps";
		}
		return subject;
	}
	/**
          * ActionEvent responsible if no method found in this action i.e. Default Method
          * @param data RunData
          * @param context Context
          */
	public void doPerform(RunData data,Context context) throws Exception{
	
		/**
	        * Getting the value of file from temporary variable 		
		* According to selection of Language.  	
		*/
		LangFile=(String)data.getUser().getTemp("LangFile");
         	String action=data.getParameters().getString("actionName","");
		if(action.equals("eventSubmit_doRemoveUser")){
                	doRemoveUser(data,context);
		}	
		else if(action.equals("eventSubmit_doSearch"))
                	doSearch(data,context);
                else
		{
			String c_msg=MultilingualUtil.ConvertedString("c_msg",LangFile);
                        data.setMessage(c_msg);
		}
	}
}

