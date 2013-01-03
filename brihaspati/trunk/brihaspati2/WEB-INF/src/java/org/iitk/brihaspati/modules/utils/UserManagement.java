package org.iitk.brihaspati.modules.utils;
/*
 *  @(#) UserManagement.java
 *  Copyright (c) 2005-2011 ETRG,IIT Kanpur 
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

//JDK

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.text.DateFormat;
import java.util.Date;
import java.io.File;
import java.util.List;
import java.util.Properties;
import java.util.Vector;
import java.util.LinkedHashSet;
import java.util.Collection;
//Turbine
import org.apache.turbine.Turbine;
import org.apache.torque.util.Criteria;
import org.apache.turbine.om.security.User;
import org.apache.turbine.om.security.Role;
import org.apache.turbine.om.security.Group;
import org.apache.turbine.services.servlet.TurbineServlet;
import org.apache.turbine.services.security.TurbineSecurity;
import org.apache.turbine.util.security.DataBackendException;
import org.apache.turbine.util.security.UnknownEntityException;
import org.apache.turbine.services.security.torque.om.TurbineUser;
import org.apache.turbine.services.security.torque.om.TurbineUserPeer;
import org.apache.turbine.services.security.torque.om.TurbineGroup;
import org.apache.turbine.services.security.torque.om.TurbineGroupPeer;
import org.apache.turbine.services.security.torque.om.TurbineUserGroupRole;
import org.apache.turbine.services.security.torque.om.TurbineUserGroupRolePeer;
//Brihaspati
import org.iitk.brihaspati.om.DbSend;
import org.iitk.brihaspati.om.Courses;
import org.iitk.brihaspati.om.NewsPeer;
import org.iitk.brihaspati.om.DbSendPeer;
import org.iitk.brihaspati.om.NoticeSend;
import org.iitk.brihaspati.om.CoursesPeer;
import org.iitk.brihaspati.om.DbReceivePeer;
import org.iitk.brihaspati.om.NoticeSendPeer;
import org.iitk.brihaspati.om.UsageDetailsPeer;
import org.iitk.brihaspati.om.NoticeReceivePeer;
import org.iitk.brihaspati.om.StudentRollnoPeer;
import org.iitk.brihaspati.om.StudentRollno;
import org.iitk.brihaspati.om.UserConfigurationPeer;
import org.iitk.brihaspati.om.StudentExpiryPeer;
import org.iitk.brihaspati.om.StudentExpiry;
import org.iitk.brihaspati.om.InstituteAdminUser;
import org.iitk.brihaspati.om.InstituteAdminUserPeer;
import org.iitk.brihaspati.om.UserPrefPeer;
import org.iitk.brihaspati.om.CourseProgram;
import org.iitk.brihaspati.om.CourseProgramPeer;
import org.iitk.brihaspati.modules.utils.UserUtil;
import org.iitk.brihaspati.modules.utils.GroupUtil;
import org.iitk.brihaspati.modules.utils.StringUtil;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.iitk.brihaspati.modules.utils.MailNotification;
import org.iitk.brihaspati.modules.utils.MultilingualUtil;
import org.iitk.brihaspati.modules.utils.CourseUserDetail;
import org.iitk.brihaspati.modules.utils.SystemIndependentUtil;
import org.iitk.brihaspati.modules.utils.InstituteIdUtil;
import org.iitk.brihaspati.modules.utils.PasswordUtil;
//import org.iitk.brihaspati.modules.utils.security.RandPasswordUtil;
//Babylon
import babylon.babylonUserTool;
import babylon.babylonPasswordEncryptor;

//import org.iitk.brihaspati.modules.utils.GongUtil;
/**
 * In this class manage all utility of user
 * @author <a href="mailto:sharad23nov@yahoo.com">Sharad Singh</a>
 * @author <a href="mailto:singh_jaivir@rediffmail.com">Jaivir Singh</a>
 * @author <a href="mailto:awadhk_t@yahoo.com">Awadhesh Kumar Trivedi</a>
 * @author <a href="mailto:nksngh_p@yahoo.co.in">Nagendra Kumar Singh</a>
 * @author <a href="mailto:madhavi_mungole@hotmail.com">Madhavi Mungole</a>
 * @author <a href="mailto:satyapalsingh@gmail.com">Satyapal Singh</a>
 * @author <a href="mailto:shaistashekh@gmail.com">Shaista</a>
 * @author <a href="mailto:richa.tandon1@gmail.com">Richa Tandon</a>
 * @author <a href="mailto:rpriyanka12@ymail.com">Priyanka Rawat</a>
 * @author <a href="mailto:sunil0711@gmail.com">Sunil YAdav</a>
 * @modified date: 08-07-2010, 20-10-2010, 3-11-2010, 26-12-2010
 * @modified date: 27-07-2011, 05-08-2011(Richa), 09-08-2012(Priyanka)
 * @modified date: 16-08-2012(Sunil Yadav), 25-09-2012 (Priyanka), 30-10-2012(Richa)
 * @modified date: 02-11-2012 (Priyanka)
 * @modified date: 27-12-2012 (Shaista) 
 */

public class UserManagement
{
    public static String Msg=null;	
    public Boolean flag=new Boolean(true);

		/**
		 * Adds the new user in the database as well as in babylon chat server
		 * @param UName String The Login Name of new user who has to be registered
		 * @param Passwd String The password of new user who has to be registered
		 * @param FName String The first name of new user who has to be registered
		 * @param LName String The last name of new user who has to be registered
		 * @param Email String The email of new user who has to be registered
		 * @param GroupName String The group in which the user has to be registered	   
		 * @param Role String The role of the new user 
		 * @param RollNo String The rollno of the new user 
		 * @param Program String The program of the new user 
		 * @param mode String Defines whether activation link will be sent or not
		 * @return String
		 */
	public static String CreateUserProfile(String UName,String Passwd,String FName,String LName,String i_name,String Email,String GroupName,String Role,String serverName,String serverPort,String file,String RollNo,String Program,String mode)//last parameter added by Priyanka
	{
    		babylonUserTool tool=new babylonUserTool();	
		String message=new String();
		String messageFormate= "", subject = "";
		Properties pr ;
		StringUtil S=new StringUtil();
		Criteria crit=new Criteria();
		java.sql.Date expdate=null;
		String instituteid="";
		int instIdint = 0,Auid=0, check=0;
		/**
		 * Below  check is added by Shaista
		 * In admin case iname is recieved null 
		 * This chek will remove later on
		 * getting institute id as a string  to pass in mail Notification Thread to compare
		 * getting instid as an integer to use in criteria to get admin's First & Last name
		 */	
		if(!Role.equals("turbine_root")) {
			if(i_name.equals("")){
				instituteid=InstituteDetailsManagement.getInsId(GroupName);
				instIdint=Integer.parseInt(instituteid);
			}
			else{
				instituteid=Integer.toString(InstituteIdUtil.getIst_Id(i_name));
				instIdint=InstituteIdUtil.getIst_Id(i_name);
			}
		}
		if(Role.equals("student")){
			try{
				String path12=TurbineServlet.getRealPath("/WEB-INF")+"/conf"+"/InstituteProfileDir/"+instituteid+"Admin.properties";
				String expdays = AdminProperties.getValue(path12,"brihaspati.user.expdays.value");
		                Integer exp1 = Integer.valueOf(expdays);		
				String c_date=ExpiryUtil.getCurrentDate("-");
	                	String E_date=ExpiryUtil.getExpired(c_date,exp1);
	                	expdate=java.sql.Date.valueOf(E_date);
			}
			catch(Exception ex){ErrorDumpUtil.ErrorLog("This is the exception in getting path :--utils(UserManagement) "+ex);}
		}

		int userid=UserUtil.getUID(UName);
		/**
		 * Checks if there are any illegal characters in the values
		 * entered
		 */
		if(S.checkString(UName)==-1 && S.checkString(FName)==-1 && S.checkString(LName)==-1)
		{
				String userRole=new String();
			try{
				/**
				* It is used if passwd is equal to ename of UName
				* then set password to random password
				*/
				String spltunm[]=UName.split("@");
				String gnme=spltunm[0];
				if ((gnme.equals(Passwd))||(Passwd==null))
				{
					Passwd=PasswordUtil.randmPass();
				}
			
				String Mail_msg=new String();
				String Rollno_msg="", info_Opt="", msgRegard="", msgDear="", msgBrihAdmin="", instFirstLastName="";
				String email_existing=new String();
				String cAlias=new String();
				String dept=new String();
				String fileName=new String();
				String activationLink="";
				/**
			 	* Get the group and role from TurbineSecurity
			 	*/
				Group user_group=TurbineSecurity.getGroupByName(GroupName);
				Role user_role=TurbineSecurity.getRoleByName(Role);
				
				
				/**
				* Check if the user profile already exists 
				* then assign a role in group else create a
				* user profile and assign role in group
				*/
				boolean UserExist=checkUserExist(UName);
				if(UserExist==false)
				{
					try
					{
					int user_id=UserUtil.getUID(UName);
					int group_id=GroupUtil.getGID(GroupName);
					/**
					* Checks if existing user already has some 
					* role in the group if yes then the specified
					* message is given else the user is assigned
					* the role in specified group
					*/
					boolean RoleExist=checkRoleinCourse(user_id,group_id);
					if(RoleExist==false)
					{
						if(group_id==3)	
						{
							 message=MultilingualUtil.ConvertedString("u_msg1",file);
						}
						else{
							 message=MultilingualUtil.ConvertedString("u_msg",file);
						}
						
					}
					else
					{
						/**
						 * if role is student then insert roll no in table
						 */
						if((Role.equals("student"))&& (!(Program.equals("")))&&(!(Program.equals("Select Program"))))
						{
							String actgname[]=GroupName.split("_");
                                                        String InsId=actgname[1];
							Rollno_msg = CourseProgramUtil.InsertPrgRollNo(UName,RollNo,Program,InsId,file,GroupName);
						}
						User existingUser=TurbineSecurity.getUser(UName);
						TurbineSecurity.grant(existingUser,user_group,user_role);

						/** set student expiry */
						if(Role.equals("student")){
                					crit=new Criteria();
					                crit.add(StudentExpiryPeer.UID,UName);
					                crit.add(StudentExpiryPeer.CID,GroupName);
					                crit.add(StudentExpiryPeer.EXPIRY_DATE,expdate);
				                	StudentExpiryPeer.doInsert(crit);
						}
						crit=new Criteria();
						crit.add(TurbineUserPeer.LOGIN_NAME,UName);
						List result=TurbineUserPeer.doSelect(crit);
						email_existing=((TurbineUser)result.get(0)).getEmail();
						if((!Role.equals("author")) && (!Role.equals("institute_admin")))
						{	
							crit=new Criteria();
							crit.add(CoursesPeer.GROUP_NAME,GroupName);
							List result1=CoursesPeer.doSelect(crit);	
							cAlias=((Courses)result1.get(0)).getGroupAlias();
							dept=((Courses)result1.get(0)).getDept();
						}
						if(Role.equals("instructor")){
		                	                if(serverPort.equals("8080"))
                	                	        	userRole="newInstructor";
                        		        	else
                                        			userRole="newInstructorhttps";
                        			}
						else if(Role.equals("teacher_assistant")){
                                                        if(serverPort.equals("8080"))
                                                                userRole="newTeacherAssistant";
                                                        else
                                                                userRole="newTeacherAssistanthttps";
                                                }

                        			else if(Role.equals("student")){
                                			if(serverPort.equals("8080"))
                                        			userRole="newStudent";
                                			else
                                        			userRole="newStudenthttps";
                        			}
						
						else if(Role.equals("institute_admin")){
							if(serverPort.equals("8080"))
								userRole="newInstituteAdmin";
							else
								userRole="newInstituteAdminhttps";
						}
                        			else{
                                			if(serverPort.equals("8080"))
                                        			userRole="newAuthor";
                                			else
                       						userRole="newAuthorhttps";
                        			}
						/**
						 * Added by shaista
						 * Assigning a string "newUser" in info_opt to get the keys like msgDear, msgRegard, 
						 * instAdmin/ brihaspatiAdmin defined in brihasapti.properties
						 */
						if(serverPort.equals("8080"))
							info_Opt = "newUser";
						else
							info_Opt = "newUserhttps";
                        			/**
						 *  Added by shaista
                         			 * Check if the user already exists. If the user exists then 
			                         * if(Role is author or institute admin to register{
			                         * 	Getting Brihaspati Admin String from brihaspati.properties for author and 
			                         * 	institute admin registration}
			                         * else { getting Institute admin's First and last name according to institute id to send in mail}
			                         * if(UserProfile already exist){
			                         *  Calling  MailNotificationThread.getController().set_Message Method to push the message in queue
			                         *  and to send mail.
			                         *  Informing to user that he is registered again in Brihaspati.
						 * initialize the variable which will fetch approriate message from the properties file
                                                 * @see MailNotificationThread class in utils
                        			 */
						fileName=TurbineServlet.getRealPath("/WEB-INF/conf/brihaspati.properties");
						pr =MailNotification.uploadingPropertiesFile(fileName);
						if(Role.equals("author") || Role.equals("institute_admin"))
							msgBrihAdmin=msgBrihAdmin=pr.getProperty("brihaspati.Mailnotification."+info_Opt+".msgBrihAdmin");
						else{ 
							crit=new Criteria();
							try{
        				                        crit.add(InstituteAdminUserPeer.INSTITUTE_ID,instIdint);
	                                			List inm=InstituteAdminUserPeer.doSelect(crit);
	                                		        InstituteAdminUser element=(InstituteAdminUser)inm.get(0);
								/**modify by jaivir,seema 
								*Getting full name of user using UserUtil.
								*@see UserUtil in utils
								*/
	                                		        Auid=UserUtil.getUID(element.getAdminUname());
								instFirstLastName=UserUtil.getFullName(Auid);
                        			       }
			                               catch(Exception ex){
                        			               ErrorDumpUtil.ErrorLog("The error in User Managemen Util class at line 282 to 288 !!"+ex);
			                               }

							msgBrihAdmin = pr.getProperty("brihaspati.Mailnotification."+info_Opt+".msgInstAdmin");
							msgBrihAdmin = MailNotification.getMessage_new(msgBrihAdmin, "", "", instFirstLastName, "");
							//ErrorDumpUtil.ErrorLog("\n\nline 301 msgBrihAdmin"+msgBrihAdmin);
						}
						msgDear = pr.getProperty("brihaspati.Mailnotification."+info_Opt+".msgDear");
						msgDear = MailNotification.getMessage_new(msgDear, FName, LName, "", UName);
						msgRegard=pr.getProperty("brihaspati.Mailnotification."+info_Opt+".msgRegard");
						msgRegard = MailNotification.replaceServerPort(msgRegard, serverName, serverPort);
				                subject = MailNotification.subjectFormate(userRole, "", pr );
						messageFormate = MailNotification.getMessage(userRole, cAlias, dept, UName, "", serverName, serverPort, pr);
                                                messageFormate=MailNotification.getMessage_new(messageFormate,"","",i_name,"");
						Mail_msg = message + MailNotificationThread.getController().set_Message(messageFormate, msgDear, msgRegard, msgBrihAdmin, email_existing, subject, "", file, instituteid,"");//last parameter added by Priyanka
						pr = null;
						subject ="";
						messageFormate = "";msgBrihAdmin =""; msgDear=""; msgRegard="";	
						Msg =MultilingualUtil.ConvertedString("u_msg1",file);
						String Msg2 =MultilingualUtil.ConvertedString("u_msg5",file);
						/**
						*
        					* If assigning role of existing user in existing group then
        					* received all notices and Group Discussion Board messages by
        					* existing user
						*/
						InsertMessages(UName,GroupName,Role);
						message=Msg+" "+Msg2+" "+Rollno_msg +" "+Mail_msg;

					}//role else part end
					}
					catch(Exception e)
					{
					message="The error in assign role of existing user"+e+userRole;
					
					}
				}
				//user exist start 
				else
				{
					try{
						User new_user=TurbineSecurity.getUserInstance();
			 			/**
			  			* Sets the data entered by the user in a blank user object
			  			*/
						java.util.Date date=new java.util.Date();
						new_user.setName(UName);
                       	 			new_user.setPassword(Passwd);
                        			new_user.setFirstName(FName);
                        			new_user.setLastName(LName);
                        			new_user.setCreateDate(date);
                        			new_user.setLastLogin(date);
						String email_new=new String();
						fileName=TurbineServlet.getRealPath("/WEB-INF/conf/brihaspati.properties");
                                        	userRole=new String();						
	                                        if(Role.equals("instructor")){
        	                                	if(serverPort.equals("8080"))
                	                                	userRole="newInstructor";
                        	                        else
                                	                        userRole="newInstructorhttps";
                                        	}
						 else if(Role.equals("teacher_assistant")){
                                                        if(serverPort.equals("8080"))
                                                                userRole="newTeacherAssistant";
                                                        else
                                                                userRole="newTeacherAssistanthttps";
                                                }
						else if(Role.equals("student")){
                                			if(serverPort.equals("8080"))
                                        			userRole="newStudent";
                                			else
                                        			userRole="newStudenthttps";
                        			}
					
						else if(Role.equals("institute_admin")){
                                                        if(serverPort.equals("8080"))
                                                                userRole="newInstituteAdmin";
                                                        else
                                                                userRole="newInstituteAdminhttps";	
                                                }

                        			else{
                                			if(serverPort.equals("8080"))
                                        			userRole="newAuthor";
                                			else
                                        			userRole="newAuthorhttps";
                        			}

                        			if(Email.equals("") || Email.equals(null))
						{
							new_user.setEmail(Email);
						}
						else
						{
						
							if(!Role.equals("author") && (!Role.equals("institute_admin")))
							{
								crit=new Criteria();
        	                                        	crit.add(CoursesPeer.GROUP_NAME,GroupName);
                	                                	List result2=CoursesPeer.doSelect(crit);
								cAlias=((Courses)result2.get(0)).getGroupAlias();
                                	                	dept=((Courses)result2.get(0)).getDept();
							}
        	                                        email_existing=Email;
							email_new=ChkMailId(email_existing);
							new_user.setEmail(email_new);
						}
						
						/**
			 			* Encrypt the password entered by the user
			 			* @see EncryptionUtil in utils
			 			*/
						String encrPassword=EncryptionUtil.createDigest("SHA1",Passwd);
						/**
				 		* Adds the new user using TurbineSecurity which throws
				 		* EntityExistsException and DataBackendException

				 		*/	

						TurbineSecurity.addUser(new_user,encrPassword);
						/**
						* Update last login, user quota  and create date field of turbine user
						*/
						//String path=TurbineServlet.getRealPath("/WEB-INF")+"/conf"+"/"+"Admin.properties";
						String path=TurbineServlet.getRealPath("/WEB-INF")+"/conf"+"/InstituteProfileDir/"+instituteid+"Admin.properties";	
						if(!((new File(path)).exists())){
							path=TurbineServlet.getRealPath("/WEB-INF")+"/conf"+"/"+"Admin.properties";
						}
				                String SpacefPrp=AdminProperties.getValue(path,"brihaspati.user.quota.value");
				                long UQuota=new Long(SpacefPrp).longValue();
						int u1=UserUtil.getUID(UName);
						crit=new Criteria();
                                                crit.add(org.iitk.brihaspati.om.TurbineUserPeer.USER_ID,u1);
                                                crit.add(org.iitk.brihaspati.om.TurbineUserPeer.CREATED,date);
                                                crit.add(org.iitk.brihaspati.om.TurbineUserPeer.LAST_LOGIN,date);
                                                //crit.add(org.iitk.brihaspati.om.TurbineUserPeer.QUOTA,UQuota);
                                                org.iitk.brihaspati.om.TurbineUserPeer.doUpdate(crit);
						/**
                                 		* The code below does not executes if the user already exists in 
						* turbine database
                                 		*/
						/**
                                 		* Here we set the user preference ( Lang) 
                                 		*/
						Date passExpdate=getExpirydate();
						crit=new Criteria();
						crit.add(UserPrefPeer.USER_ID,u1);
						crit.add(UserPrefPeer.USER_LANG,"english");
						crit.add(UserPrefPeer.PASSWORD_EXPIRY,passExpdate);
						UserPrefPeer.doInsert(crit);

                                		/**
                                 		* The password sent as the parameter in encryptPassword() method of
                                 		* babylonPasswordEncryptor() is in encrypted form. Hence, the babylon
                                 		* password is double encrypted.
                                 		*/

                                		String encrPasswd_babylon=new babylonPasswordEncryptor().encryptPassword(encrPassword);
                                		/**
                                 		* Create the new user entry in the babylon chat server
                                 		*/
				      		//ErrorDumpUtil.ErrorLog("to test-"+encrPasswd_babylon+" "+UName);
						tool.createUser(UName,encrPasswd_babylon);
						//ErrorDumpUtil.ErrorLog("to test-1");
						/**
				 		* Grants the new user in the role "user" in "global" group
				 		* Grants the role in specified group
				 		*/
						Group global=TurbineSecurity.getGroupByName("global");
						Role role_of_user=TurbineSecurity.getRoleByName("user");
						TurbineSecurity.grant(new_user,global,role_of_user);
						TurbineSecurity.grant(new_user,user_group,user_role);
						/**
						 * Insert roll no in table if role is student
						 */
						if((Role.equals("student"))&&(!(Program.equals("")))&&(!(Program.equals("Select Program"))))
						{
							String actgname[]=GroupName.split("_");
                                                        String InsId=actgname[1];
                                                        Rollno_msg = CourseProgramUtil.InsertPrgRollNo(UName,RollNo,Program,InsId,file,GroupName);
                                                }

						/** set student expiry */
						if(Role.equals("student")){
                					crit=new Criteria();
					                crit.add(StudentExpiryPeer.UID,UName);
					                crit.add(StudentExpiryPeer.CID,GroupName);
					                crit.add(StudentExpiryPeer.EXPIRY_DATE,expdate);
				                	StudentExpiryPeer.doInsert(crit);
						}
						String NewUser= new String();
						if(serverPort.equals("8080"))
							NewUser="newUser";
						else
							NewUser="newUserhttps";
                        			/**
						 *  Added by shaista
                         			 * One more mail is sent to New User on behalf of Brihaspati Admin if given user is new one.  
                         			 * To inform that user is registered on Brihaspati 
                         			 * To inform his user name and password.
			                         * if(Role is author or institute admin to register{
			                        * 	Getting Brihaspati Admin String from brihaspati.properties for author and 
			                         * 	institute admin registration to send in mail }
			                         * else { getting Institute admin's First and last name according to institute id to send in mail}
			                         *  Calling  MailNotificationThread.getController().set_Message Method to push the message in queue
			                         *  and to send mail.
                                                 * @see MailNotificationThread class in utils
						 * Initialize the variable which will fetch approriate message from the properties file
			                         */
						/**
                                                 * This mail will specify the user name and password of the new user
                                                 * @see MailNotification in utils
                                                 */
						pr =MailNotification.uploadingPropertiesFile(fileName);
						if(!mode.equals("cnfrm_i"))
						{
							msgDear = pr.getProperty("brihaspati.Mailnotification."+NewUser+".msgDear");
                                                	msgDear = MailNotification.getMessage_new(msgDear, FName, LName, "", UName);
						}
						msgBrihAdmin=msgBrihAdmin=pr.getProperty("brihaspati.Mailnotification."+NewUser+".msgBrihAdmin");
                                                //msgDear = pr.getProperty("brihaspati.Mailnotification."+NewUser+".msgDear");
                                                //msgDear = MailNotification.getMessage_new(msgDear, FName, LName, "", UName);
                                                msgRegard=pr.getProperty("brihaspati.Mailnotification."+NewUser+".msgRegard");
                                                msgRegard = MailNotification.replaceServerPort(msgRegard, serverName, serverPort);
                                                subject = MailNotification.subjectFormate(NewUser, "", pr );
				                messageFormate = MailNotification.getMessage(NewUser, "", "", UName, Passwd, serverName, serverPort,pr);
						//messageFormate=MailNotification.getMessage_new( messageFormate,FName,LName,i_name,UName);
                                                messageFormate=MailNotification.getMessage_new( messageFormate,"","",i_name,"");
						//ErrorDumpUtil.ErrorLog("mode = "+mode);
						//ErrorDumpUtil.ErrorLog("to test----------------->else part");
						
						// Following lines added by Priyanka
						if(mode.equals("act") || mode.equals(""))					
						{
							String randm_n = PasswordUtil.randmPass();	
                                                	String str=randm_n+Email;
                                                	String a_key=EncryptionUtil.createDigest("SHA1",str);
                                                	//ErrorDumpUtil.ErrorLog("Inside User Management Activation key = "+a_key);
							activationLink=pr.getProperty("brihaspati.Mailnotification."+NewUser+".activationLink");
	                                         	activationLink=MailNotification.getMessage(activationLink, Email, a_key, mode,"english");
						 	//activationLink=MailNotification.getMessage(activationLink, Email, a_key, mode);
							activationLink=MailNotification.replaceServerPort(activationLink, serverName, serverPort);
						 	messageFormate = messageFormate+activationLink;
						 	MailNotificationThread.getController().set_Message(messageFormate, msgDear, msgRegard, msgBrihAdmin, email_new, subject, "", file, instituteid,mode);//last parameter added by Priyanka
							// UPDATE USER_PREF TABLE......................
						 	crit = new Criteria();
                                                	int uid=UserUtil.getUID(UName);
                                                	crit.add(UserPrefPeer.USER_ID,uid);
                                                	crit.add(UserPrefPeer.ACTIVATION,a_key);
                                                	UserPrefPeer.doUpdate(crit);
						}
						//ErrorDumpUtil.ErrorLog("to test----------------->else2 part");				
						if((mode.equals("cnfrm_i")) || (mode.equals("cnfrm_u")) || (mode.equals("cnfrm_c")))
						{
							MailNotificationThread.getController().set_Message(messageFormate, msgDear, msgRegard, msgBrihAdmin, email_new, subject, "", file, instituteid,mode);//last parameter added by Priyanka
       							 crit = new Criteria();
						         int uid=UserUtil.getUID(Email);
						         crit.add(UserPrefPeer.USER_ID,uid);
						         crit.add(UserPrefPeer.ACTIVATION,"ACTIVATE");
						         UserPrefPeer.doUpdate(crit);
						}
						
						subject = ""; messageFormate =""; msgBrihAdmin="";
						//ErrorDumpUtil.ErrorLog("to test----------------->else3 part");
						if(Role.equals("author") || Role.equals("institute_admin"))
                                                        msgBrihAdmin=msgBrihAdmin=pr.getProperty("brihaspati.Mailnotification."+NewUser+".msgBrihAdmin");
                                                else{
                                                        try{
                                                                crit=new Criteria();
                                                                crit.add(InstituteAdminUserPeer.INSTITUTE_ID,instIdint);
                                                                List inm=InstituteAdminUserPeer.doSelect(crit);
                                                                InstituteAdminUser element=(InstituteAdminUser)inm.get(0);
								/**modify by jaivir,seema 
                                                                *Getting full name of user using UserUtil.
                                                                *@see UserUtil in utils
                                                                */

								Auid=UserUtil.getUID(element.getAdminUname());
                                                                instFirstLastName=UserUtil.getFullName(Auid);
                                                       }
                                                       catch(Exception ex){
                                                               ErrorDumpUtil.ErrorLog("The error in User Managemen Util class at line 506 to 513 !!"+ex);
                                                       }
                                                        msgBrihAdmin = pr.getProperty("brihaspati.Mailnotification."+NewUser+".msgInstAdmin");
                                                        msgBrihAdmin = MailNotification.getMessage_new(msgBrihAdmin, "", "", instFirstLastName, "");
                                                }
						//ErrorDumpUtil.ErrorLog("to test----------------->else4 part");
						//if(!(mode.equals("cnfrm_i")&&(check>0)))
                                               	subject = MailNotification.subjectFormate(userRole, "", pr );
						if(Role.equals("author"))
						{
                                               		messageFormate = MailNotification.getMessage(userRole, "", "", "", "", serverName, serverPort,pr);
							messageFormate=MailNotification.getMessage_new( messageFormate,"","",i_name, "");
							 Mail_msg = message+MailNotificationThread.getController().set_Message(messageFormate, msgDear, msgRegard, msgBrihAdmin, email_new, subject, "", file, instituteid,"");//last parameter added by Priyanka
						}
						else
						{
                                               		messageFormate = MailNotification.getMessage(userRole, cAlias, dept, "", "", serverName, serverPort,pr);
							if(mode.equals("cnfrm_i"))
								messageFormate=MailNotification.getMessage_new( messageFormate,FName,LName,i_name,"");
							else
								messageFormate=MailNotification.getMessage_new( messageFormate,"","",i_name, "");
							Mail_msg = message+MailNotificationThread.getController().set_Message(messageFormate, msgDear, msgRegard, msgBrihAdmin, email_new, subject, "", file, instituteid,"");//last parameter added by Priyanka							
						}
						/**
						  * Insert default value of configuartion parameter
						  */
						int u_id=UserUtil.getUID(UName);
						crit=new Criteria();
						crit.add(UserConfigurationPeer.USER_ID,u_id);
						UserConfigurationPeer.doInsert(crit);
						/**
						 * Create User area for the new user
						 */
						String userRealPath=TurbineServlet.getRealPath("/UserArea");
						File f=new File(userRealPath+"/"+UName);
						if(!f.exists())
						{
							boolean b=f.mkdirs();
							String cd="/"+userRealPath+"/"+UName+"/Private";
							File ff=new File(cd);
							boolean cc=ff.mkdirs();
							File f3=new File(userRealPath+"/"+UName+"/Shared");
							boolean c3=f3.mkdirs();
							Msg=MultilingualUtil.ConvertedString("u_msg3",file);
						}
						else
						{
							String Msg1 =MultilingualUtil.ConvertedString("u_msg4",file);
							String Msg2 =MultilingualUtil.ConvertedString("u_msg5",file);
							Msg=Msg1+""+Msg2;
						}
						message=Msg+" "+Rollno_msg+" "+Mail_msg;
							
					}
					catch(Exception e)
					{
						message="The error in create a new user profile"+e;
						e.printStackTrace();
						ErrorDumpUtil.ErrorLog(message);
					}
					/**
					*
        				* If register new user in existing group then
        				* received all notices and Group Discussion Board messages by new user
        				* 
					*/
					InsertMessages(UName,GroupName,Role);
				}
			}
			catch(Exception ex)
			{
				message="The exception in create user profile in user management util !!"+ex;
			}
		}
		else
		{
			message=MultilingualUtil.ConvertedString("c_msg3",file);
		}
		/**
		 * Return the message which is set above as per the condition
		 */
		return(message);
	}
	/**
	 * In this method,Check Mailid for adding local domain in mailid
	 * @param email String The Email_id of the user
	 * @return boolean
	 */
	public static String ChkMailId(String email)
        {
        	try{
                	if(!email.equals("")){
                        	int i=email.indexOf("@");
                                if(i==-1){
					/**
					* get local domain name from TurbineResource.properties
					*/
                                        String local_domain=Turbine.getConfiguration().getString("local.domain.name","");
                                       	if (local_domain.equals(""))
					{
                                                email="";
						ErrorDumpUtil.ErrorLog("Local domain name not found in TurbineResource.properties !!");
                                        }
                                        else
					{
                                       		email=email.concat(local_domain);
                                        }
                                }
                        }
                }
                catch(Exception cx)
                {
                        ErrorDumpUtil.ErrorLog("This is the exception in check mail id :--utils(UserManagement) "+cx);
                }
                return email;
        }
	public static String DeleteInstructor(String Gname,String LangFile)
        {	
		String Message="", messageFormate="";
		try
		{
			int gid=GroupUtil.getGID(Gname);
                        Criteria crit=new Criteria();
                        crit.add(TurbineUserGroupRolePeer.GROUP_ID,gid);
                        List lst=TurbineUserGroupRolePeer.doSelect(crit);
                        int userId=((TurbineUserGroupRole)lst.get(0)).getUserId();
                        String uid=Integer.toString(userId);
                        String server_name=TurbineServlet.getServerName();
                        String srvrPort=TurbineServlet.getServerPort();
                        String subject="";
                        if(srvrPort.equals("8080"))
                                subject="deleteUser";
                        else
                                subject="deleteUserhttps";
                        String email="";
             		TurbineUser element=(TurbineUser)UserManagement.getUserDetail(uid).get(0);
                        email=element.getEmail();
                        String fileName=TurbineServlet.getRealPath("/WEB-INF/conf/brihaspati.properties");
                        String rmsg=CourseManagement.RemoveCourse(Gname,"ByCourseMgmt",LangFile);
			Properties pr =MailNotification.uploadingPropertiesFile(fileName);
			String subj = MailNotification.subjectFormate(subject, "", pr );
                        messageFormate = MailNotification.getMessage(subject, Gname, "", "", "", server_name, srvrPort,pr);
			/**
				@param Last Parameter as a String is added as null for fileName if it gets failure. added by shaista
			*/
                        String Mail_msg=MailNotification.sendMail(messageFormate, email, subj, "", LangFile, "");
                        Message=rmsg+" "+Mail_msg;

                }
                catch(Exception cx)
                {
                        ErrorDumpUtil.ErrorLog("The exception in DeleteInstructor method :--utils(UserManagement) "+cx);
                }
                return Message;
        }
	public static String DeleteInstructor(String Gname,String LangFile, int instId)
	{
		String Message="", messageFormate="", msgRegard="", msgInstAdmin="", instFirstLastName ="",  institutename="";
		int Auid=0;
 		try
 		{
			int gid=GroupUtil.getGID(Gname);
			Criteria crit=new Criteria();
                        crit.add(TurbineUserGroupRolePeer.GROUP_ID,gid);
                        List lst=TurbineUserGroupRolePeer.doSelect(crit);
                        int userId=((TurbineUserGroupRole)lst.get(0)).getUserId();
			String uid=Integer.toString(userId);
                        String server_name=TurbineServlet.getServerName();
			String srvrPort=TurbineServlet.getServerPort();
			String subject="", NewUser="";
			 if(srvrPort.equals("8080")){
				subject="deleteUser";
				NewUser = "newUser";
                        }
                        else{
                                subject="deleteUserhttps";
                                NewUser = "newUserhttps";
                        }
			String email="";
                        if(instId != 0)
                        {
                                institutename= InstituteIdUtil.getIstName(instId);
                                try{
                                        crit=new Criteria();
                                        crit.add(InstituteAdminUserPeer.INSTITUTE_ID,instId);
                                        List inm=InstituteAdminUserPeer.doSelect(crit);
                                        InstituteAdminUser element=(InstituteAdminUser)inm.get(0);
					Auid=UserUtil.getUID(element.getAdminUname());
                                        instFirstLastName=UserUtil.getFullName(Auid);
                                }
                                catch(Exception ex){
                                        ErrorDumpUtil.ErrorLog("The error in User Managemen Util class. See at line 655 to 662 !!"+ex);
                                }
			}
			TurbineUser element=(TurbineUser)UserManagement.getUserDetail(uid).get(0);
                        email=element.getEmail();
                        String fileName=TurbineServlet.getRealPath("/WEB-INF/conf/brihaspati.properties");
                        String rmsg=CourseManagement.RemoveCourse(Gname,"ByCourseMgmt",LangFile);
                        Properties pr =MailNotification.uploadingPropertiesFile(fileName);
                        String subj = MailNotification.subjectFormate(subject, "", pr );
                        messageFormate = MailNotification.getMessage(subject, Gname, "", "", "", pr);
                        msgRegard=pr.getProperty("brihaspati.Mailnotification."+NewUser+".msgRegard");
                        msgRegard = MailNotification.replaceServerPort(msgRegard, server_name, srvrPort);
                        if(instId != 0)
                        {
                                messageFormate=MailNotification.getMessage_new(messageFormate, "" ,"" ,institutename,"");
                                msgInstAdmin = pr.getProperty("brihaspati.Mailnotification."+NewUser+".msgInstAdmin");
                                msgInstAdmin = MailNotification.getMessage_new(msgInstAdmin, "", "", instFirstLastName, "");
                        }
                        else
                                msgInstAdmin = pr.getProperty("brihaspati.Mailnotification."+NewUser+".msgBrihAdmin");

                        String Mail_msg= MailNotificationThread.getController().set_Message(messageFormate, "", msgRegard, msgInstAdmin, email, subj, "", LangFile, Integer.toString(instId),"");//last parameter added by Priyanka
                        Message=rmsg+" "+Mail_msg;

                }
                catch(Exception cx)
                {
                        ErrorDumpUtil.ErrorLog("The exception in DeleteInstructor method :--utils(UserManagement) "+cx);
                }
                return Message;
	}

	/**
	 * In this method,Check existing user
	 * @param uName String The user name of the user
	 * @return boolean
	 */
	public static boolean checkUserExist(String uName)
	{
		boolean userExist=false;
		try
		{
			Criteria crit=new Criteria();
			crit.add(TurbineUserPeer.LOGIN_NAME,uName);
			List v=TurbineUserPeer.doSelect(crit);
			userExist=v.isEmpty();
		}
		catch(Exception e)
		{
                        ErrorDumpUtil.ErrorLog("This is the exception in check user Exist :--utils(UserManagement) "+e);
		}
		return userExist;
	}
	/**
	 * In this method,Check role of user
	 * 
	 * @param uid Integer The userid of the user
	 * @param gid Integer The groupid of the user which registered in group
	 * @return boolean
	 */
	public static boolean checkRoleinCourse(int uid,int gid)
	{
		boolean roleExist=false;
		try
		{
			Criteria crit=new Criteria();
			crit.add(TurbineUserGroupRolePeer.USER_ID,uid);
			crit.and(TurbineUserGroupRolePeer.GROUP_ID,gid);
			List v=TurbineUserGroupRolePeer.doSelect(crit);
			roleExist=v.isEmpty();
		}
		catch(Exception e){
                        ErrorDumpUtil.ErrorLog("This is the exception in check role in Course :--utils(UserManagement) "+e);
		}
		return roleExist;
	}
	/**
	 * In this method,get all role of user in particular group
	 * 
	 * @param uid Integer The userid of the user
	 * @param gid Integer The groupid of the user which registered in group
	 * @return int
	 */
	public static int getRoleinCourse(int uid,int gid)
	{
		int roleid=0;
		try
		{
			Criteria crit=new Criteria();
			crit.add(TurbineUserGroupRolePeer.USER_ID,uid);
			crit.and(TurbineUserGroupRolePeer.GROUP_ID,gid);
			List v=TurbineUserGroupRolePeer.doSelect(crit);
			TurbineUserGroupRole element=(TurbineUserGroupRole)v.get(0);
			roleid=element.getRoleId();
		}
		catch(Exception e){
                        ErrorDumpUtil.ErrorLog("This is the exception in get role in Course -utils(UserManagement) :- "+e);
			
		}
		return(roleid);
	}
	/**
	 * In this method,get details of specific user
	 * 
	 * @param uid Integer The userid of the user
	 * @return List 
	 */

	/*Modified for getting User according to Institute wise.
	  Call in getUserListMethod inListManagement Util.
	*/
	public static List getUserDetail(String uid)
	{
		List v=null;
		try
		{
			Criteria crit=new Criteria();
			if(!uid.equals("All"))
			{
				int UID=Integer.parseInt(uid);
				crit.add(TurbineUserPeer.USER_ID,UID);
				v=TurbineUserPeer.doSelect(crit);
			}
			else
			{
				int noUid[]={0,1};
				crit.addNotIn(TurbineUserPeer.USER_ID,noUid);
				v=TurbineUserPeer.doSelect(crit);
			}
		}
		catch(Exception e)
		{
                        ErrorDumpUtil.ErrorLog("This is the exception in get user details -utils(UserManagement)  :- "+e);
					
		}
		return v;
	}
	/*Modified for getting User according to Institute wise.
          Call in getUserListMethod inListManagement Util.
        */
        public static List getUserDetail1(String uid,String InstituteId)
        {
                List v=null;
                Vector v1=new Vector();
                List crslist=null;
                List details=null;
		List UidList=null;
                List tulist=null;
		Vector UidVector=new Vector();
                try
                {
                        Criteria crit=new Criteria();
			Vector grpList=new Vector();
			Vector uidList=new Vector();
			Vector grpIdList=new Vector();
			crit.addGroupByColumn(CoursesPeer.GROUP_NAME);
                        crslist=CoursesPeer.doSelect(crit);
			for(int i=0;i<crslist.size();i++){
				Courses element=(Courses)(crslist.get(i));
                          	String gname=(element.getGroupName()).toString();
				if(gname.endsWith(InstituteId)){
					grpList.add(gname);
				}
			}
			for(int j=0;j<grpList.size();j++){
				String Gname=(grpList.get(j)).toString();
				crit=new Criteria();
				crit.add(TurbineGroupPeer.GROUP_NAME,Gname);
				details=TurbineGroupPeer.doSelect(crit);
				for(int l=0;l<details.size();l++){
					TurbineGroup tgrp=(TurbineGroup)(details.get(l));
					int grpId=tgrp.getGroupId();
					grpIdList.add(grpId);
				}
			}
			for(int k=0;k<grpIdList.size();k++){
				String Gid=(grpIdList.get(k)).toString();
				crit=new Criteria();
				crit.add(TurbineUserGroupRolePeer.GROUP_ID,Gid);
				UidList=TurbineUserGroupRolePeer.doSelect(crit);
				for(int p=0;p<UidList.size();p++){
					TurbineUserGroupRole tugrole=(TurbineUserGroupRole)(UidList.get(p));
					int user_id=tugrole.getUserId();
					UidVector.add(user_id);
				}
			}
			//code for adding userid once in vector, modified by sharad on 31-12-2010

			Collection noDup = new LinkedHashSet(UidVector);
			UidVector.clear();
			UidVector.addAll(noDup);

			//put check for match userid exist.?
			for(int u=0;u<UidVector.size();u++){
				String userId=(UidVector.get(u)).toString(); 
				int UserId=Integer.parseInt(userId);
				crit=new Criteria();
				//modified on 25-12-2010 by sharad
				if(UserId!=0)
				{
					crit.add(TurbineUserPeer.USER_ID,UserId);
				try{
					tulist=TurbineUserPeer.doSelect(crit);
					for(int r=0;r<tulist.size();r++){
					TurbineUser tuser=(TurbineUser)tulist.get(r);
                                       	v1.add(tuser);
                                	}
				}catch(Exception ex){}
				v=v1;
				}
			}
                }
                catch(Exception e)
                {
                        ErrorDumpUtil.ErrorLog("This is the exception in get user details -utils(UserManagement)  :- "+e);

                }
                return v;
        }

	/**
	 * In this method,Update the user profile.
	 * Get all details of user to be updated then check serial id
	 * if(serial id is not null and other information along with rollno) {
	 * 	then update information}
	 * else{insert new entry of student}
	 * 
	 * @param userName String The loginName of the user
	 * @param fName String The FirstName of the user 
	 * @param lName String The LastName of the user 
	 * @param eMail String The Email of the user 
	 * @param file String language file 
	 * @param RollNo String The Rollno of the user 
	 * @param Program String The Program of the user 
	 * @param Instid String The institute id of the user 
	 * @param CourseId String The course id of the user 
	 *
	 * @return String
	 */
	public static String updateUserDetails(String userName,String fName,String lName,String eMail,String file,String RollNo,String Program,String Instid,String CourseId)
	{
		String msg=new String();
		Criteria crit=new Criteria();
		String flag = "false";
		try
		{
			String rollmsg = "";
			int stdntid=0;
                	User user = TurbineSecurity.getUser(userName);
                	user.setFirstName(fName);
                	user.setLastName(lName);
			if(!eMail.equals(""))
			{
				String NewEmail=ChkMailId(eMail);
				user.setEmail(NewEmail);
			}
			else
                		user.setEmail(eMail);

                	TurbineSecurity.saveUser(user);
			int uid=UserUtil.getUID(userName);
			//For updating student rollno, program and course
			//@see InsertPrgRollNo method in CourseProgramUtil in utils
			rollmsg=CourseProgramUtil.InsertPrgRollNo(userName,RollNo,Program,Instid,file,CourseId);
                	String profileOf=MultilingualUtil.ConvertedString("profileOf",file);
                        String update_msg=MultilingualUtil.ConvertedString("update_msg",file);
			if(file.endsWith("hi.properties"))
                                msg=userName+" "+profileOf+" "+update_msg+" "+rollmsg;
                        else if(file.endsWith("urd.properties"))
                                msg=profileOf+" "+update_msg +" "+userName+" "+rollmsg;
                        else
                                msg= profileOf+" "+userName+" "+update_msg+" "+rollmsg;


			//ErrorDumpUtil.ErrorLog(userName +" Profile has been changed");

		}
		catch(Exception ex)
		{
			msg="Error in "+userName+" profile updation"+ex;
		}
		return(msg);
        }
	/**
        * Delete the users from the specified group and if the
        * user is an orphan user then deletes the user profile
        * @param userName String Contains the user name of the users for removal
        * @param group_name String The name of the group in which all the above
        *                    users are registered
        * @return String
        */
        public String removeUserProfile(String userName,String group_name,String file)
	{
    		babylonUserTool tool=new babylonUserTool();	
                String message=new String();
                this.flag=new Boolean(true);
                try{
                	/**
                        * Get the 'Group' and 'Role' objects
                        * for the group and role specified in
                        * the parameters passed
                        */
			//ErrorDumpUtil.ErrorLog("user in removeUserProfile userName= "+userName+"\t  group_name="+ group_name+"\tfile="+file);
                        User user=TurbineSecurity.getUser(userName);
                        Group user_group=TurbineSecurity.getGroupByName(group_name);
			//ErrorDumpUtil.ErrorLog("\nUM class at line 1124 in removeUserProfileuser_group= "+user_group);
			int uid=UserUtil.getUID(userName);
			int gid=GroupUtil.getGID(group_name);
			int role=getRoleinCourse(uid,gid);
			String roleName=UserGroupRoleUtil.getRoleName(role);
                        Role user_role=TurbineSecurity.getRoleByName(roleName);
                        Criteria crit=new Criteria();
			//ErrorDumpUtil.ErrorLog("user in removeUserProfile userName= "+userName+"\t  group_name="+ group_name);

						/**
                                                * Remove student membership from the Student Expiry 
                                                */
                                                	crit=new Criteria();
	       	                                        crit.add(StudentExpiryPeer.UID,userName);
        	       	                                crit.add(StudentExpiryPeer.CID,group_name);
                	       	                        StudentExpiryPeer.doDelete(crit);
                        /**
                        * Delete the role of the user from the specified group
                        */
			crit=new Criteria();
                        try{
				this.flag=new Boolean(false);
                                TurbineSecurity.revoke(user,user_group,user_role);
				CourseProgramUtil.DeleteCoursePrg(userName,group_name);
				this.flag=new Boolean(false);
                                String usr_role=MultilingualUtil.ConvertedString("usr's_role",file);
                                String remove=MultilingualUtil.ConvertedString("remove",file);
				String msg1="";
				if(file.endsWith("hi.properties"))
	                                 msg1= group_name+" "+usr_role+" "+remove;       
				else if(file.endsWith("urd.properties"))
                                         msg1= usr_role+" "+remove +" "+group_name;

				else
					 msg1= usr_role+" "+group_name+" "+remove;
                                /**
                                * Check if the user has any other role except 'user'
                                * in 'global' group.If not then entries related to the
                                * user from the database are removed
                                */
				String msg2="";
				int user_id=UserUtil.getUID(userName);
				//ErrorDumpUtil.ErrorLog("...............................user_id."+user_id);
                                if(user_id!=0 && user_id!=1){
					int i[]={1,2};
                                       	crit.add(TurbineUserGroupRolePeer.USER_ID,user_id);
					crit.addNotIn(TurbineUserGroupRolePeer.GROUP_ID,i);
					List check=TurbineUserGroupRolePeer.doSelect(crit);
					if(check.size()==0){
                                               	/**
                                               	* Remove the login details for the user
                                               	*/	
		                                    	crit=new Criteria();
	        	                                crit.add(UsageDetailsPeer.USER_ID,user_id);
							UsageDetailsPeer.doDelete(crit);
						/**
                                                * Remove the Configuration details for the user
                                                */
	                                                crit=new Criteria();
        	                                        crit.add(UserConfigurationPeer.USER_ID,user_id);
                	                                UserConfigurationPeer.doDelete(crit);
						
                                               	/**
                                               	* Finally remove the user profile from the
                                               	* database and babylon chat server
                                               	*/
                                      		TurbineSecurity.removeUser(user);
                                               	tool.deleteUser(userName);
						/**
                                                 * Remove the user rollno and Program from database  
                                                 */
							CourseProgramUtil.DeleteCoursePrg(userName,"");
							crit = new Criteria();
        	                                        crit.add(StudentRollnoPeer.EMAIL_ID,userName);
                	                                StudentRollnoPeer.doDelete(crit);

						/**Remove UserId from UserPref table
 						* Add by Jaivir Singh and Seema
 			 			*/
						crit=new Criteria();
                                                crit.add(UserPrefPeer.USER_ID,user_id);
                                                UserPrefPeer.doDelete(crit);
                                               	/**
                       				* Delete the repository from the server for
                       				* this User
                       				*/
                       				String userRealPath=TurbineServlet.getRealPath("/UserArea");
                       				String fileName=userRealPath+"/"+userName;
                       				File f=new File(fileName);
                       				SystemIndependentUtil.deleteFile(f);
						this.flag=new Boolean(false);
                                               	msg2=MultilingualUtil.ConvertedString("profile2",file);
                                       	}
	                       	} 
				if(file.endsWith("urd.properties")) {message= usr_role+" "+remove +"-"+" "+msg2+ " "+group_name;}
                                else
				message=msg1+" "+msg2;
			}
                        catch(DataBackendException dbe){
                        	this.flag=new Boolean(false);
                                message="There was an error accessing the data backend.";
                                return(message);
                        }
                        catch(UnknownEntityException uee){
                        	this.flag=new Boolean(false);
                                message="User account, group or role is not present.";
				return(message);
                        }

		} //end of 'try' block
                catch(Exception e){
                	this.flag=new Boolean(false);
                        message="The error in removal Profile !! "+e;
		} 
                return(message);
	} 
	/**
        * This method is main for removal a user
	* Delete the users from the specified group and if the
        * user is an orphan user then deletes the user profile
        * @param userName String Contains the user name of the users for removal
        * @return String
        */
	public static Vector RemoveUser(String userName,String file)
	{
        	Vector Err_type=new Vector();
		Vector Err_user=new Vector();
		Vector Err_Msg=new Vector();
		try
		{
                Criteria crit=new Criteria();
                /**
                * Get the username from the screen and find the corresponding user id
                */
		UserManagement umt=new UserManagement();
                CourseUserDetail CuDetail=new CourseUserDetail();
                int userId=UserUtil.getUID(userName);
		Vector gid_author=UserGroupRoleUtil.getGID(userId,5);
		if(gid_author.size()!=0)
		{
			for(int count1=0;count1<gid_author.size();count1++)
			{
                       		int Gid=Integer.parseInt((String)gid_author.get(count1));
				if(Gid==2)
				{
					String msg=umt.removeUserProfile(userName,"author",file);
                                        if(umt.flag.booleanValue()==false)
                                	{
                                               	CuDetail=new CourseUserDetail();
                                                CuDetail.setErr_User(userName);
                                                CuDetail.setErr_Type(msg);
                        	                Err_Msg.add(CuDetail);
                	                }
				}
			}
				
		}
                /**
                * Get all the group id in vector "grpInstructor" where the user is instructor
                */
                Vector grpInstructor=UserGroupRoleUtil.getGID(userId,2);
                Vector primary_active=new Vector();
                Vector primary_inactive=new Vector();
                Vector sec_Instructor=new Vector();
		String groupName="";
		/**
                * This 'if' checks if the user is an instructor in any
                * group. If he is not, then 'else' part is executed.
                */
                if(grpInstructor.size()!=0)
		{
                	/**
                	* This for loop will check for each for each group id
                	* in "grpInstructor" whether the user is primary instructor or not
                 	*/
			for(int count=0;count<grpInstructor.size();count++)
			{
                        	/**
                         	* Get the group id and find the corresponding group name
                         	*/
                        	int gId=Integer.parseInt((String)grpInstructor.get(count));
                        	groupName=GroupUtil.getGroupName(gId);
                        	/**
                         	* Check if the course obtained above is active
                         	* or inactive and PrimaryInstructor
				* @see CourseManagement from Utils
                         	*/
				boolean check_Primary=CourseManagement.IsPrimaryInstructor(groupName,userName);
				boolean check_Active=CourseManagement.CheckcourseIsActive(gId);
                        	/**
                         	* Check if the user is a primary instructor in
                         	* the course. If he is and the course is active
                         	* then add the group id to vector "primary_active"
                         	* else add the group id to vector "primary_inactive".
				* otherwise add the group id to "sec_Instructor".
                         	*/
                        	if(check_Primary==true)
				{
                                	if(check_Active==false)
                                       		primary_active.add(Integer.toString(gId));
                                	else
                        			primary_inactive.add(Integer.toString(gId));
                        	}
				else
				{
					sec_Instructor.add(Integer.toString(gId));
				}
                	}
                        /**
                        * This 'if' checks if the user is a primary
                        * instructor. If he is,
                        * then 'else' part is executed
                        * (condition) grpInstructor.size()!=0 && check_Primary==true
                        */
                        if(sec_Instructor.size()==0)
			{
                                /**
                                * This 'if' checks if the user is
                                * primary instructor of an inactive
                                * course. If he is not, then 'else'
                                * part is executed.
                                * (condition) grpInstructor.size()!=0  && primary_active.size()  
				* ==0 && primary_inactive.size()!=0 && check_Primary==true
                                */
				if((primary_active.size()==0) && (primary_inactive.size()!=0))
				{
					String msg1="";
                                       	/**
                                       	* The 'for' loop will execute for each of the group id
                                       	* of inactive course in which the user is primary
                                       	* instructor
                                       	*/
                                       	for(int i=0;i<primary_inactive.size();i++)
					{
	                                       	int group_id=Integer.parseInt((String)primary_inactive.get(i));
                                               	String gName=GroupUtil.getGroupName(group_id);
                                               	/**
                                               	* Get the user ids of the users who are
                                               	* registered as instructor in this course in
                                               	* vector "groups"
                                               	* @see UserGroupRoleUtil in util
  						*/
                                               	Vector groups_User=UserGroupRoleUtil.getUID(group_id);
                                               	int counter=0;
                                               	/**
                                               	* Delete each such user obtained in "group"
                                               	* except this user who is primary instructor
                                              	* @see UserUtil in utils
                                               	*/
                                               	for(counter=0;counter<groups_User.size();counter++)
						{
                                                    	int uId=Integer.parseInt((String)groups_User.get(counter));
                                                       	String uName=UserUtil.getLoginName(uId);
                                                        String msg=umt.removeUserProfile(uName,gName,file);
                                                        if(umt.flag.booleanValue()==false)
							{
                                      				CuDetail=new CourseUserDetail();
								CuDetail.setErr_User(uName);
								CuDetail.setErr_Type(msg);
								Err_Msg.add(CuDetail);
                                                	}
                                               	}
                                       		/**
                                       		* Delete the entries of the course from the
                                       		* related tables
                                       		*/
						msg1=CourseManagement.RemoveCourse(gName,"ByUserMgmt",file);
                               		}
				}
              			else if((primary_active.size()!=0) && (primary_inactive.size()==0))
				{
                              		for(int i=0;i<primary_active.size();i++)
					{
       	                                       	int g_id=Integer.parseInt((String)primary_active.get(i));
                                               	String GName=GroupUtil.getGroupName(g_id);
						CuDetail=new CourseUserDetail();
                               			CuDetail.setErr_User(userName);
                               			String remove_msg=MultilingualUtil.ConvertedString("remove_msg",file);
                                                CuDetail.setErr_Type(remove_msg);
						Err_Msg.add(CuDetail);
						}
					}
				}
				else
				{
                                       	for(int i=0;i<sec_Instructor.size();i++)
                                       	{
                                               	int gId=Integer.parseInt((String)sec_Instructor.get(i));
                                       		String gName=GroupUtil.getGroupName(gId);
                                               	String msg=umt.removeUserProfile(userName,gName,file);
						// Remove messages from Notices, Discussion Board and News 
						RemoveMessages(userId,gId);
                                               	if(umt.flag.booleanValue()==false)
                                               	{
                                                    	CuDetail=new CourseUserDetail();
                                                       	CuDetail.setErr_User(userName);
                                                       	CuDetail.setErr_Type(msg);
                                                       	Err_Msg.add(CuDetail);
                                               	}
                                       	}
                        	}
                	}
                        Vector stud_grp=UserGroupRoleUtil.getGID(userId,3);
                	if(stud_grp.size()!=0)
			{
                       		for(int i=0;i<stud_grp.size();i++)
				{
                               		int gId=Integer.parseInt((String)stud_grp.get(i));
                               		String gName=GroupUtil.getGroupName(gId);

                               		String msg=umt.removeUserProfile(userName,gName,file);
					// Remove messages from Notices, Discussion Board and News 
					RemoveMessages(userId,gId);
                               		if(umt.flag.booleanValue()==false)
					{
                                               	CuDetail=new CourseUserDetail();
						CuDetail.setErr_User(userName);
						CuDetail.setErr_Type(msg);
                                       		Err_Msg.add(CuDetail);
                              		}
                       		}
			}
		}
		catch(Exception e)
		{
			String masg="The Error " +e;
			Err_Msg.add(masg +e);
			
		}
		return(Err_Msg);
	}
	/**
        * In this method, If register new user or assigning role in existing group then
	* received all notices and Group Discussion Board messages by new user
	* or existing user
        * 
        * @param userName String Contains the name of existing user or new user
        * @param groupName String Contains the groupname of existing user or new user
        * @param role String Contains the role of existing user or new user
        */
	public static void InsertMessages(String userName,String groupName,String role)
	{
                Criteria crit = new Criteria();
		try
		{	
			/**
                         * Send the notices meant for the new user registered or assigning role
                         */
                        int role_id=0;
                        if(role.equals("instructor"))
                                role_id=2;
                        else if(role.equals("student"))
                                role_id=3;

                        int gid=GroupUtil.getGID(groupName);
                        int userid=UserUtil.getUID(userName);
			boolean userExists=checkUserExist(userName);
                        crit = new Criteria();
                        crit.add(NoticeSendPeer.GROUP_ID,gid);
                        crit.or(NoticeSendPeer.GROUP_ID,"1");
                        List v=NoticeSendPeer.doSelect(crit);

                        for(int j=0;j<v.size();j++)
			{
                                NoticeSend element=(NoticeSend)v.get(j);
                                if(element.getRoleId()==role_id || element.getRoleId()==7)
				{
                                        int noticeId=element.getNoticeId();
                                        crit=new Criteria();
                                        crit.add(NoticeReceivePeer.NOTICE_ID,noticeId);
                                        crit.add(NoticeReceivePeer.RECEIVER_ID,userid);
                                        crit.add(NoticeReceivePeer.GROUP_ID,gid);
					if(userExists==false)
					{
                                        	List notice_posted=NoticeReceivePeer.doSelect(crit);
						if(notice_posted.isEmpty())
						{
                                        		crit.add(NoticeReceivePeer.READ_FLAG,0);
                                        		NoticeReceivePeer.doInsert(crit);
						}
					}
					else
					{
                                        	crit.add(NoticeReceivePeer.READ_FLAG,0);
                                        	NoticeReceivePeer.doInsert(crit);
					}
                                }
                        }
                        /**
                        * Send the Messages(DB) meant for the new user registered or assigning role
                        */
                        crit=new Criteria();
                        crit.add(DbSendPeer.GROUP_ID,gid);
                        List check=DbSendPeer.doSelect(crit);
                        for(int k=0;k<check.size();k++)
                        {
                                DbSend element=(DbSend)check.get(k);
                                int MsgId=element.getMsgId();
                                crit=new Criteria();
                                crit.add(DbReceivePeer.MSG_ID,MsgId);
                                crit.add(DbReceivePeer.GROUP_ID,gid);
                                crit.add(DbReceivePeer.RECEIVER_ID,userid);
                                if(userExists==false)
                                {
                                        List msg_posted=DbReceivePeer.doSelect(crit);
                                        if(msg_posted.isEmpty())
                                        {
                                                crit.add(DbReceivePeer.READ_FLAG,"0");
                                                DbReceivePeer.doInsert(crit);
                                        }
                                }
                                else
                                {
                                        crit.add(DbReceivePeer.READ_FLAG,"0");
                                        DbReceivePeer.doInsert(crit);
                                }
                        }
		}
		catch(Exception ex)
		{
			ErrorDumpUtil.ErrorLog("The error in exist message received by new user or assigning Role !!-utils(UserManagement)"+ex);
		}
	}		
	/**
        * In this method, If remove user from existing group then
	* removed all notices and Group Discussion Board messages
        * 
        * @param uid Integer Contains the id of existing user
        * @param gid Integer Contains the gid of existing group
        */
	public static void RemoveMessages(int uid,int gid)
	{
                Criteria crit = new Criteria();
		try
		{
			/**
                	* Delete the entries of the Notices from the related tables
                	*/
                        crit=new Criteria();
                        crit.add(NoticeSendPeer.USER_ID,uid);
                        crit.add(NoticeSendPeer.GROUP_ID,gid);
			List v=NoticeSendPeer.doSelect(crit);
			boolean entry=v.isEmpty();
			if(entry==true)
                        {	
                        	crit=new Criteria();
                        	crit.add(NoticeReceivePeer.RECEIVER_ID,uid);
                        	crit.add(NoticeReceivePeer.GROUP_ID,gid);
                        	NoticeReceivePeer.doDelete(crit);
			}
			else
			{
				NoticeSendPeer.doDelete(crit);
                        	crit=new Criteria();
                        	crit.add(NoticeReceivePeer.GROUP_ID,gid);
                        	NoticeReceivePeer.doDelete(crit);
			}
                        /**
                        * Delete the entries of the discussion board from the related tables
                        */
                        crit=new Criteria();
                        crit.add(DbSendPeer.USER_ID,uid);
                        crit.add(DbSendPeer.GROUP_ID,gid);
			v=NoticeSendPeer.doSelect(crit);
			entry=v.isEmpty();
			if(entry==true)
                        {	
                        	crit=new Criteria();
                        	crit.add(DbReceivePeer.RECEIVER_ID,uid);
                        	crit.add(DbReceivePeer.GROUP_ID,gid);
                        	DbReceivePeer.doDelete(crit);
			}
			else
			{
                        	DbSendPeer.doDelete(crit);
                        	crit=new Criteria();
                        	crit.add(DbReceivePeer.GROUP_ID,gid);
                        	DbReceivePeer.doDelete(crit);
			}
                        /**
                        * Delete the entries of the News from the related tables
                        */
                        crit=new Criteria();
                        crit.add(NewsPeer.USER_ID,uid);
                        NewsPeer.doDelete(crit);
		}
		catch(Exception ex)
		{
			ErrorDumpUtil.ErrorLog("The error in RemoveMessages - UserManagement.java Util !!" +ex);
		}
	}
	/**
	 * Sending a mail to user after removing his profile from brihaspati
	 * @param userName String the name of User
	 * @param group_name String the name of a group
	 * @param langFile String the name of a propeties file for multilingual
	 * @param info_new String Portion of a property define in brihaspati.properties file like(deleteUser, deleteUserhttps, onlineStudentRegRequest 
	 * @param mail_id String mail id of a user
	 * @param courseId String course id 
	 * @param dept String name of department
	 * @param userPassword String users's password
	 * @param file String name of property file to read property define in it.
	 * @param serverName String  address of server
	 * @param serverPort String Port Number of the server
	 * @return String
	**/
        public String removeUserProfileWithMail(String userName, String group_name, String langFile, String info_new1, String mail_id,String instName,String courseId, String dept, String userPassword, String file, String serverName, String serverPort )
	{
		//String Msg ="", Mail_msg = "";
		String Msg ="", Mail_msg = "", info_Opt="", msgRegard="", msgInstAdmin="", instAdminName="";
		int instId=0, Auid=0;
		try{
			Msg=removeUserProfile(userName,group_name,langFile);
                        Properties pr =MailNotification.uploadingPropertiesFile(file);
                        String info_new = org.apache.commons.lang.StringUtils.substringBeforeLast(info_new1, "$");
                        info_Opt = org.apache.commons.lang.StringUtils.substringAfterLast(info_new1, "$");
			//ErrorDumpUtil.ErrorLog("\n\n\n\n in UserManagement util  info_new=="+info_new+"\t info_Opt ==="+info_Opt );
                        if(! instName.equals("")){
                                instId= InstituteIdUtil.getIst_Id(instName);
                                Criteria crit=new Criteria();
                                try{
                                       crit.add(InstituteAdminUserPeer.INSTITUTE_ID,instId);
                                       List inm=InstituteAdminUserPeer.doSelect(crit);
                                       for(int in=0; in <inm.size(); in++){
                                                InstituteAdminUser element=(InstituteAdminUser)inm.get(in);
                                                instAdminName= element.getAdminUname();
                                                if(courseId.trim().equals(instAdminName.trim()))
							Auid=UserUtil.getUID(element.getAdminUname());
							instAdminName = UserUtil.getFullName(Auid);
                                       }
                               }
                               catch(Exception ex){
                                       ErrorDumpUtil.ErrorLog("The error in removeUserProfileWithMail() -User Management Util class !!"+ex);
                               }
                        }
                        msgRegard=pr.getProperty("brihaspati.Mailnotification."+info_Opt+".msgRegard");
                       	msgRegard = MailNotification.replaceServerPort(msgRegard, serverName, serverPort);
                       	msgInstAdmin=pr.getProperty("brihaspati.Mailnotification."+info_Opt+".msgInstAdmin");
                        msgInstAdmin = MailNotification.getMessage_new(msgInstAdmin,"","",instAdminName,"");
                        String subject = MailNotification.subjectFormate(info_new, "", pr );
                        String message = MailNotification.getMessage(info_new, group_name, "", "", "", pr);
			if(message.length() >0)
	                        message = MailNotification.getMessage_new(message,"","",instName,userName);
                        //ErrorDumpUtil.ErrorLog("\n\n\n\n in UserManagement util  message="+message+"      subject="+subject);
                        if(instId == 0)
                                Mail_msg=  MailNotificationThread.getController().set_Message(message, "", msgRegard, msgInstAdmin, mail_id, subject, "", langFile, "","");//last parameter added by Priyanka
                        else
                                Mail_msg=  MailNotificationThread.getController().set_Message(message, "", msgRegard, msgInstAdmin, mail_id, subject, "", langFile, Integer.toString(instId),"");//last parameter added by Priyanka
                }

/*
			Properties pr =MailNotification.uploadingPropertiesFile(file);
        	        String subject = MailNotification.subjectFormate(info_new, "", pr );
                	String message = MailNotification.getMessage(info_new, group_name, "", "", "", serverName, serverPort,pr);
			message = MailNotification.getMessage_new(message,"","",instName,userName);
		////////////////////////////////////////
*/
		catch(Exception e){ErrorDumpUtil.ErrorLog("Error in removeUserProfileWithMail method in UserManagment util"+e);}
		return Mail_msg+":"+Msg;
	}

/*public String getStackTrace(Throwable throwable)
{
             Writer writer = new StringWriter();
             PrintWriter printWriter = new PrintWriter(writer);
             throwable.printStackTrace(printWriter);
             return writer.toString();
}*/
	public static Date getExpirydate()
	{
		Date expdate=null;
		try{
			String path=TurbineServlet.getRealPath("/WEB-INF")+"/conf"+"/"+"Admin.properties";
	                String pasExpday=AdminProperties.getValue(path,"brihaspati.admin.passwordExpiry");
			int pex=180;
			if(!(org.apache.commons.lang.StringUtils.isBlank(pasExpday)))
	        	        pex=Integer.parseInt(pasExpday);
			Date date=new Date();
                        Calendar now = Calendar.getInstance();
                        now.add(Calendar.DATE,pex);
                        String Strexpdat=now.get(Calendar.YEAR)+"-"+(now.get(Calendar.MONTH)+1) + "-" + now.get(Calendar.DATE);
			DateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
                        expdate=formatter.parse(Strexpdat);

		}catch(Exception e){
			ErrorDumpUtil.ErrorLog("Error in getExpiarydate in UserManagement---"+e);}
		return expdate;
	}

}
