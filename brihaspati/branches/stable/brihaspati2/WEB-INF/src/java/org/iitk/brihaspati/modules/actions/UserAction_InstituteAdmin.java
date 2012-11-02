package org.iitk.brihaspati.modules.actions;

/*
 * @(#)UserAction_InstituteAdmin.java	
 *
 *  Copyright (c) 2005-2006, 2008, 2010, 2011,2012 ETRG,IIT Kanpur. 
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
 *  Contributors: Members of ETRG, I.I.T. Kanpur 
 * 
 */
//JDK
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Vector;
import java.util.StringTokenizer;
//Turbine
import org.apache.turbine.util.RunData;
import org.apache.torque.util.Criteria;
import org.apache.velocity.context.Context;
import org.apache.turbine.om.security.User;
import org.apache.commons.fileupload.FileItem;
import org.apache.turbine.util.parser.ParameterParser;
import org.apache.turbine.services.servlet.TurbineServlet;
import org.apache.turbine.services.security.TurbineSecurity;
import org.apache.commons.lang.StringUtils;
//Brihaspati
import org.iitk.brihaspati.modules.utils.GetUnzip;
import org.iitk.brihaspati.modules.utils.UserUtil;
import org.iitk.brihaspati.modules.utils.StringUtil;
import org.iitk.brihaspati.modules.utils.PasswordUtil;
import org.iitk.brihaspati.modules.utils.UserManagement;
import org.iitk.brihaspati.modules.utils.MultilingualUtil;
import org.iitk.brihaspati.modules.utils.RegisterMultiUser;
import org.iitk.brihaspati.modules.utils.MailNotification;
import org.iitk.brihaspati.modules.utils.MailNotificationThread;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.iitk.brihaspati.modules.utils.CourseManagement;
import org.iitk.brihaspati.modules.utils.UserGroupRoleUtil;
import org.iitk.brihaspati.modules.utils.InstituteIdUtil;
import org.iitk.brihaspati.modules.utils.GroupUtil;
//import org.iitk.brihaspati.modules.utils.ExtractZipFile;
import org.iitk.brihaspati.modules.utils.InstituteIdUtil;
import org.iitk.brihaspati.modules.utils.CourseProgramUtil;
import org.iitk.brihaspati.modules.utils.usercourseinfr;
import org.apache.turbine.services.servlet.TurbineServlet;
import org.apache.turbine.services.security.torque.om.TurbineUser;

import org.iitk.brihaspati.om.UserConfigurationPeer;
import org.iitk.brihaspati.om.StudentRollnoPeer;
import org.iitk.brihaspati.om.CourseProgramPeer;
import org.iitk.brihaspati.om.TurbineUserGroupRolePeer;
import org.iitk.brihaspati.om.TurbineUserGroupRole;
import org.iitk.brihaspati.om.TurbineRole;
import org.iitk.brihaspati.om.TurbineRolePeer;
import org.iitk.brihaspati.om.Courses;
import org.iitk.brihaspati.om.CoursesPeer;
import org.iitk.brihaspati.om.InstituteAdminUser;
import org.iitk.brihaspati.om.StudentRollno;
import org.iitk.brihaspati.om.InstituteAdminUserPeer;
/** 
 * This class contains code for registration, updation profile and password or
 * removal of user in the system
 * @author <a href="mailto:nksngh_p@yahoo.co.in">Nagendra Kumar Singh</a> 
 * @author <a href="mailto:awadhesh_trivedi@yahoo.co.in">Awadhesh Kumar Trivedi</a>
 * @author <a href="mailto:shaistashekh@gmail.com">Shaista</a>
 * @author <a href="mailto:singh_jaivir@rediffmail.com">Jaivir Singh</a>
 * @author <a href="mailto:richa.tandon1@gmail.com">Richa Tandon</a>
 * @author <a href="mailto:tejdgurung20@gmail.com">Tej Bahadur</a>
 * @modified date: 08-07-2010, 20-10-2010, 26-12-2010, 27-07-2011, 05-08-2011,07-02-2012
 * @modified date:30-10-2012(Richa)
 */

public class UserAction_InstituteAdmin extends SecureAction{
    	/**
    	  * ActionEvent responsible for register multiple user in the system
     	  * @param data RunData
      	  * @param context Context
	  * @see RegisterMultiUser from Utils
     	  * @return nothing
     	  */
	String LangFile="";
	
	public void doUploadMultiUser(RunData data, Context context){
        	try{

		/**
		 * Getting file value from temporary variable according to selection
		 * Replacing the static value from Property file
        	 **/
			System.gc();
			LangFile=(String)data.getUser().getTemp("LangFile");
			/**
			 * Added by shaista
			 * Getting institute id as a String from prameter Parser Variable
			 * Getting instName as a String according to institute id
			 * Replacing the static value from Property file
			 **/
			String  instituteId=(data.getUser().getTemp("Institute_id")).toString();
			String instName=InstituteIdUtil.getIstName(Integer.parseInt(instituteId));
			ParameterParser pp=data.getParameters();
	        	FileItem file = pp.getFileItem("file");
        		String fileName=file.getName();
			if((!(fileName.toLowerCase()).endsWith(".txt"))||(file.getSize()<=0))
	        	{
				 /**
                                 * Getting file value from temporary variable according to selection of Language
                                 * Replacing the static value from Property file
                                 **/ 
        	        	
				String upload_msg2=MultilingualUtil.ConvertedString("upload_msg2",LangFile);
				data.setMessage(upload_msg2);
        		}
	        	else{
				//String serverName=data.getServerName();
        	                //int srvrPort=data.getServerPort();
	                        //String serverPort=Integer.toString(srvrPort);
				String group=pp.getString("GroupName");
        			String role=pp.getString("Role");
				Date date=new Date();
                		File f=new File(TurbineServlet.getRealPath("/tmp")+"/"+group+role+date.toString()+".txt");
				file.write(f);
				/**
 				 * pass institute name as a variable as string 
 				 * for getting exprydate according to admin profile
 				 * @see RegisterMultiUser util
 				 * @see UserManagement Util
 				 **/
        			Vector msg=RegisterMultiUser.Multi_Register(f,group,role,LangFile,instName);
	        		context.put("Msg",msg);
			}
		}
		catch(Exception ex)
		{
			data.setMessage("The Error in Uploading!! "+ex);
		}
    	}

    	/**
    	  * ActionEvent responsible for updating user profile in the system and
     	  * Must check the input for integrity before allowing the user info
     	  * to be update in the database.
     	  * @param data RunData
      	  * @param context Context
	  * @see UserManagement from Utils
     	  */
	public void doUpdate(RunData data, Context context)
	{
		String msg="";
		LangFile=(String)data.getUser().getTemp("LangFile");	
	 	ParameterParser pp=data.getParameters();
                String uname=pp.getString("username");
		/* Counter tells in how many institute the user is registered*/
		int count=Integer.parseInt(pp.getString("counter",""));
		if(StringUtil.checkString(uname) != -1)
                {
                	data.addMessage(MultilingualUtil.ConvertedString("usr_prof1",LangFile));
                        return;
                }
		String fname=StringUtil.replaceXmlSpecialCharacters(pp.getString("firstname"));
	 	String lname=StringUtil.replaceXmlSpecialCharacters(pp.getString("lastname"));
         	String email=StringUtil.replaceXmlSpecialCharacters(pp.getString("email"));
		String Instid=((String)data.getUser().getTemp("Institute_id")).toString();
		/**
 		 * Loop for getting values of program and institute to update.
 		 * Serial id to update perticular student of that id. 	
 		 */ 
		if(count==0)
			count=1;	
		for(int k=1;k<=count;k++)
                {
	                String PrgCode = pp.getString("prg"+k,"");
                        String rollno = pp.getString("rollno"+k,"").trim();
			if(StringUtil.checkString(rollno) != -1)
			/**
	                 * check if rollno have any special character then return message
                         */
                        {
                                data.addMessage(MultilingualUtil.ConvertedString("quiz_msg8",LangFile));
                                data.addMessage(MultilingualUtil.ConvertedString("ProxyuserMsg3",LangFile));
                               return;
                        }
                       	String CId = pp.getString("group"+k,"");
	                msg=UserManagement.updateUserDetails(uname,fname,lname,email,LangFile,rollno,PrgCode,Instid,CId);
		       	/**
 			* If msg is equal to true, it shows error in updating profile
 			* then show message.	 
 			*/ 	
		       if(msg.equals("true")){
                       		String msg1 = MultilingualUtil.ConvertedString("rollno_msg2",LangFile);
                                String msg2 = MultilingualUtil.ConvertedString("rollno_msg",LangFile);
                                msg = msg1+" "+msg2;
                                break;}
                }
		data.setMessage(msg);
	}
    	/**
     	  * ActionEvent responsible for updating user password in the system
     	  * @param data RunData
     	  * @param context Context
	  * @see PasswordUtil from Utils
     	  */
	public void doUpdatePass(RunData data, Context context)
	{
		try
		{
			LangFile=(String)data.getUser().getTemp("LangFile");
			ParameterParser pp=data.getParameters();
			/**
		 	* Get the user name and new password enterd by admin for the user.
		 	*/
              //  	String uname=pp.getString("username");
			String userName=pp.getString("username");
			if(StringUtil.checkString(userName) != -1)
                       	{
                               data.addMessage(MultilingualUtil.ConvertedString("usr_prof1",LangFile));
                               return;
                       	}
			String newPW=StringUtil.replaceXmlSpecialCharacters(pp.getString("newpass"));
			User user=TurbineSecurity.getUser(userName);
			/**
		 	* Update password entered by the admin for the user
		 	* @see PasswordUtil in utils
		 	*/
			String serverName =TurbineServlet.getServerName();
	                String serverPort =TurbineServlet.getServerPort();
			PasswordUtil.passwordFromUtil(serverName, serverPort);
			String msg=PasswordUtil.doChangepassword(user,"",newPW,LangFile);
			data.setMessage(msg);
		}
		catch(Exception ex)
		{
			data.setMessage("Password updataion failed! Error occured "+ex);
			
			
		}
	}
	
	 /**
          * ActionEvent responsible for removing a user from the system
          * @param data RunData
          * @param context Context
          */
/*
        public void doDelete(RunData data, Context context) throws Exception
	{
		String instFirstLastName="";
		User user = data.getUser();
		String instituteId=(user.getTemp("Institute_id")).toString();	
		MultilingualUtil mu=new MultilingualUtil();	
		LangFile=(String)user.getTemp("LangFile");
		ParameterParser pp=data.getParameters();
		String userName=pp.getString("username");
		context.put("uname",userName);
		int userId=UserUtil.getUID(userName);
		String uid=Integer.toString(userId);
		Vector Messages=new Vector();
		int in[]={1};
		Criteria crit=new Criteria();
		crit.add(TurbineUserGroupRolePeer.USER_ID,uid);
		crit.addNotIn(TurbineUserGroupRolePeer.GROUP_ID,in);
                List lst=TurbineUserGroupRolePeer.doSelect(crit);
                int roleId=((TurbineUserGroupRole)lst.get(0)).getRoleId();
                crit=new Criteria();
                crit.add(TurbineRolePeer.ROLE_ID,roleId);
                List listone=TurbineRolePeer.doSelect(crit);
                String RoleName=((TurbineRole)listone.get(0)).getRoleName();
		context.put("roleName",RoleName);
		//Vector grpInstructor = UserGroupRoleUtil.getGID(userId,2);
		//int gId=0;
		int aUid = 0;
		String server_name=TurbineServlet.getServerName();
                String srvrPort=TurbineServlet.getServerPort();
		String subject="", info_new = "", info_Opt="", msgRegard="", msgInstAdmin="";
                String instAdminName = user.getName();
		//String strInstId = instituteId;
                String instName=InstituteIdUtil.getIstName(Integer.parseInt(instituteId));
		//////////////////////////////////////
		 int instIdint=InstituteIdUtil.getIst_Id(instName);
		crit=new Criteria();
                //ErrorDumpUtil.ErrorLog("\n\n\n\n UM CLASS line 283 instIdint="+instIdint);
                try{
                	crit.add(InstituteAdminUserPeer.INSTITUTE_ID,instIdint);
                        List inm=InstituteAdminUserPeer.doSelect(crit);
                        InstituteAdminUser element=(InstituteAdminUser)inm.get(0);
			/****** shaista ***********
                         * Getting full name of user using UserUtil.
                         * @see UserUtil in utils
                         */
	/*		aUid = UserUtil.getUID(element.getAdminUname());
                        instFirstLastName =UserUtil.getFullName(aUid);
                        //ErrorDumpUtil.ErrorLog("\n\ninstFirstLastName"+instFirstLastName);
		}
                catch(Exception ex){
                	ErrorDumpUtil.ErrorLog("The error in UserAction_InstituteAdmin class at line 263 !!"+ex);
		}

		///////////////////////////////////////
		if(srvrPort.equals("8080")){
			info_new = "deleteUser";
			info_Opt = "newUser";
		}
                else{
			info_new = "deleteUserhttps";
			info_Opt = "newUserhttps";	
		}
		String email="";
		String Mail_msg="", message ="";
		boolean flag=false;

                TurbineUser element=(TurbineUser)UserManagement.getUserDetail(uid).get(0);
                email=element.getEmail();
		String fileName=TurbineServlet.getRealPath("/WEB-INF/conf/brihaspati.properties");
		////////////////////////////////////////////////////
		
		Properties pr =MailNotification.uploadingPropertiesFile(fileName);
		msgRegard=pr.getProperty("brihaspati.Mailnotification."+info_Opt+".msgRegard");
                msgRegard = MailNotification.replaceServerPort(msgRegard, server_name, srvrPort);
                msgInstAdmin=pr.getProperty("brihaspati.Mailnotification."+info_Opt+".msgInstAdmin");
                if ( instFirstLastName.length() > 0)
                	msgInstAdmin = msgInstAdmin.replaceAll("institute_admin", instFirstLastName);
		else if(instAdminName.length() > 0)
                	msgInstAdmin = msgInstAdmin.replaceAll("institute_admin", instAdminName);

		subject = MailNotification.subjectFormate(info_new, "", pr );
		if(grpInstructor.size()!=0)
		{
			gId=Integer.parseInt((String)grpInstructor.get(0));
		}
		//message = MailNotification.getMessage(info_new, groupName, "", "", "", server_name, srvrPort,pr);
		//ErrorDumpUtil.ErrorLog("\nRoleName="+RoleName+"\n   subject="+subject);	




		if(RoleName.equals("instructor"))
		{
			String Gname="";
			crit=new Criteria();
			crit.addGroupByColumn(CoursesPeer.GROUP_NAME);
                	List lstt=CoursesPeer.doSelect(crit);
                	for(int j=0;j<lstt.size();j++)
                	{
                		Courses nm=(Courses)lstt.get(j);
                       		Gname=nm.getGroupName();
				if(Gname.endsWith(userName+"_"+instituteId))
                                {
					boolean check_Primary=CourseManagement.IsPrimaryInstructor(Gname,userName+"_"+instituteId);
					context.put("pInst",check_Primary);
					flag=true;
				}
                       		String cName=nm.getCname();
                       		String active=(new Byte(nm.getActive())).toString();
				if(active.equals("0"))
				{
					String Message=CourseManagement.RemoveCourse(Gname,"ByCourseMgmt",LangFile);
					message = MailNotification.getMessage(info_new, cName, "", "", "", pr);
                			message = message.replaceAll("institute_admin",instName);
					//Mail_msg=MailNotification.sendMail(message, email, subject, "", LangFile);
					Mail_msg=  MailNotificationThread.getController().set_Message(message, "", msgRegard, msgInstAdmin, email, subject, "", LangFile, instituteId);
					data.setMessage(Mail_msg);
					String st1=mu.ConvertedString("delIns1",LangFile);
					String st2=mu.ConvertedString("delIns2",LangFile);
					String infrmtn=st1+" "+userName+" "+st2;
					context.put("infrmtn",infrmtn);
				}
				else
				data.setScreenTemplate("call,ListMgmt_InstituteAdmin,InstAdminviewall.vm");
			}
		}
		if(flag)
                {
			return;
		}
		else{
			String groupName = "";
			if(RoleName.equals("student"))
				groupName = GroupUtil.getGroupName(userId,3);
			if(RoleName.equals("instructor"))
				groupName = GroupUtil.getGroupName(userId,2);

			message = MailNotification.getMessage(info_new, groupName, "", "", "", pr);
       			message = message.replaceAll("institute_admin",instName);
			//Mail_msg=MailNotification.sendMail(message, email, subject, "", LangFile);
			Mail_msg=  MailNotificationThread.getController().set_Message(message, "", msgRegard, msgInstAdmin, email, subject, "", LangFile, instituteId);
			Messages=UserManagement.RemoveUser(userName,LangFile);
			context.put("error_Messages",Messages);
			data.setMessage(Mail_msg);
		}
        }
*/
	/**
	 * ActionEvent responsible for uploading  users photo from the system
         * @param data RunData
         * @param context Context
	 */
	public void doUploadMultiUserPhoto(RunData data, Context context){
                try{
		
		 /**
                 * Getting file value from temporary variable according to selection
                 * Replacing the static value from Property file
                 **/
			LangFile=(String)data.getUser().getTemp("LangFile");
                        ParameterParser pp=data.getParameters();
                        FileItem file = pp.getFileItem("file");
                        String fileName=file.getName();
                        if((!(fileName.toLowerCase()).endsWith(".zip"))||(file.getSize()<=0))
                        {
                                 /**
                                 * Getting file value from temporary variable according to selection of Language
                                 * Replacing the static value from Property file
                                 **/

                                String upload_msg3=MultilingualUtil.ConvertedString("upload_msg3",LangFile);
                                data.setMessage(upload_msg3);
                        }
                        else{
				//here code for unzip a zip file in appropriate place
				Date date=new Date();
                                File f=new File(TurbineServlet.getRealPath("/tmp")+"/"+date.toString()+".zip");
                                file.write(f);
				File photoDir=new File(TurbineServlet.getRealPath("/localImages")+"/Photo/");
				photoDir.mkdirs();
				GetUnzip guz=new GetUnzip(f.getAbsolutePath(),photoDir.getAbsolutePath());	
				String photoArr[]=photoDir.list();
				for(int i=0;i<photoArr.length;i++){
					String photoName=photoArr[i];
					int indx=photoName.indexOf(".");
					if (indx > 0){
						photoName=photoName.substring(0,indx);
					}
					int uid=UserUtil.getUID(photoName);
					Criteria crit=new Criteria();
                                        crit.add(UserConfigurationPeer.USER_ID,uid);
                                        crit.add(UserConfigurationPeer.PHOTO,photoName);
                                        UserConfigurationPeer.doUpdate(crit);

				}

                                data.setMessage(MultilingualUtil.ConvertedString("upload_msg4",LangFile));
				f.delete();
			 }
                }
                catch(Exception ex){
	                data.setMessage("The Error in Photo Uploading!! "+ex);

                }
        }
/*
	public void doExpire(RunData data, Context context)
        {
                try{
			LangFile=(String)data.getUser().getTemp("LangFile");
			String Grp_List=data.getParameters().getString("deleteFileNames","");
                        String Gname="";
			String msg="";
			if(!Grp_List.equals(""))
                	{
				StringTokenizer st=new StringTokenizer(Grp_List,"^");
                        	for(int j=0;st.hasMoreTokens();j++)
                        	{
					String str1=st.nextToken();
					String str2[]=str1.split(",");
					Gname=str2[0];
					String CourseName=str2[1];
					String act=str2[2];
					if(act.equals("1"))
						act="0";
					else
						act="1";
	                        	String CStatus=CourseManagement.UpdateCourseDetails(Gname,CourseName,"","",act,LangFile);
					msg=UserManagement.DeleteInstructor(Gname,LangFile,Integer.parseInt((String)data.getUser().getTemp("Institute_id")));
				}
			}
                        data.setMessage(msg);
                }
                catch(Exception ex)
                {
                        data.setMessage("The error in doExpire method -"+ex);
                }
        }
*/
 /**
          * ActionEvent responsible for upload institute logo in the system
          * @param data RunData
          * @param context Context
          */
        public void doUploadLogo(RunData data, Context context)
        {
                User user=data.getUser();
                LangFile=(String)user.getTemp("LangFile");
		String ImageName=(data.getUser().getTemp("Institute_id")).toString();
//                String ImageName=(String)user.getTemp("course_id");
                ParameterParser pp=data.getParameters();
                FileItem file = pp.getFileItem("file");
                String fileName=file.getName();
		fileName=fileName.toLowerCase();
                String imagesRealPath=TurbineServlet.getRealPath("/localImages");
		String msg1;
                if(fileName.endsWith("jpg")|| fileName.endsWith("gif")|| fileName.endsWith("png")||fileName.endsWith("jpeg"))
                {
                        try{
                                File filePath=new File(imagesRealPath+"/Logo/");
                                filePath.mkdirs();
                                filePath=new File(filePath+"/"+ImageName);
                                file.write(filePath);
                                msg1=MultilingualUtil.ConvertedString("c_msg5",LangFile);
                                data.setMessage(msg1);
                        }
                        catch(Exception ex){data.setMessage("The Error in Uploading!! "+ex);}
                }
                else
                {
                        msg1=MultilingualUtil.ConvertedString("Profile_PhotoMsg2",LangFile);
                        data.setMessage(msg1);
                        setTemplate(data,"call,CourseMgmt_InstituteAdmin,UploadLogo.vm");
                }


        }

	public void doSearch(RunData data, Context context)
        {
		setTemplate(data,"call,ListMgmt_InstituteAdmin,InstAdminviewall.vm");
	}
/**
        * ActionEvent responsible for registers a new course along with the instructor and student
        * in the system in a single zip file.
        * @param data RunData
        * @param context Context
        * @see UploadMUserSingleZipFile from Utils
        */
        public void doUploadMultiUserZip(RunData data, Context context){
        try{
                /**
                * Getting file value from temporary variable according to selection
                * Replacing the static value from Property file
                **/
                Runtime r=Runtime.getRuntime();
                Process p=null;
                File Zipfnm=new File("");
                Vector msg=new Vector();
		String instName="";
                ParameterParser pp=data.getParameters();
		String Role=pp.getString("role","");
                LangFile=(String)data.getUser().getTemp("LangFile");
                String inst_Id=(data.getUser().getTemp("Institute_id")).toString();
		if(inst_Id.equals("")&&(Role.equals("admin"))){
			instName = pp.getString("instName","");
		}
		else{
	                int InstituteId=Integer.parseInt(inst_Id);
			instName=InstituteIdUtil.getIstName(InstituteId);
		}
                FileItem file = pp.getFileItem("file");
                String fileName=file.getName();
                String flName[]=fileName.split("\\.");
                String fname=flName[0];
                File filnm=new File(fname);
                if((!(fileName.toLowerCase()).endsWith(".zip"))||(file.getSize()<=0))
                {
                        /**
                        * Getting file value from temporary variable according to selection of Language
                        * Replacing the static value from Property file
                        * show message if extension of file not found
                        **/
                        String upload_msg3=MultilingualUtil.ConvertedString("upload_msg3",LangFile);
                        data.setMessage(upload_msg3);
                }
                else{
                        /**
                        * Here code for unzip a zip file in their appropriate place.
                        **/
                        Vector AddStudfile=new Vector();
                        Vector AddInstfile=new Vector();
                        Date date=new Date();
                        File f=new File(TurbineServlet.getRealPath("/tmp")+"/"+date.toString()+"-"+fileName);
                        file.write(f);
                        File ZipDir=new File(TurbineServlet.getRealPath("/Registration/"));
                        ZipDir.mkdirs();
			GetUnzip.UnzipFileIntoDirectory(f.getAbsolutePath(),ZipDir.getAbsolutePath());
                        Zipfnm=new File(TurbineServlet.getRealPath("/Registration/"+filnm));
                        boolean exists = Zipfnm.exists();
                        /**
                        * condition for check the Directory exist or not
                        * after successfully unzip the zip file in temporary folder
                        * if directory not exist make a directory for unzip the file
                        **/
                        if (!exists){
                                Zipfnm.mkdir();
				GetUnzip.UnzipFileIntoDirectory(f.getAbsolutePath(),Zipfnm.getAbsolutePath());
                        }
                                /**
                                * Get the list of file from unzip folder
                                * If file list is not null then get all file name within flder.
                                **/
                                String[] listOfFiles = Zipfnm.list();
				String msg3="";
                                if (listOfFiles.length != 0){
                                        for (int i=0; i<listOfFiles.length; i++)
                                        {
                                                /**
                                                * Get "inst.txt" file for primary instructor registeration from list of files from unzip folder
                                                * Add all files in new vector except "inst.txt" file for student registeration
                                                */
                                                String filenme = listOfFiles[i];
                                                if(!filenme.equals("inst.txt")){
                                                        AddStudfile.add(filenme);
						}
                                                else{
							AddInstfile.add(filenme);
						}
					}
						if(AddInstfile.size()!=0){
							for(int k=0;k<AddInstfile.size();k++)
							{
								String InstF=AddInstfile.get(k).toString();
                                                        	File InstFile=new File(TurbineServlet.getRealPath("/Registration/"+filnm+"/"+InstF));
                                        			Vector msg1=RegisterMultiUser.RegisterInstructor(InstFile,LangFile,instName);
                                                        	msg.addAll(msg1);
                                                	}
						}
						else{
							data.setMessage(MultilingualUtil.ConvertedString("upload_msg6",LangFile));
						}	
                                		/**
	                                	* Get file name one by one within loop for reading content of each file
	        	                        */
						if(AddStudfile.size()!=0){
                        	        		for(int j=0;j<AddStudfile.size();j++)
                                			{
                                        			String StudF=AddStudfile.get(j).toString();
	                                        		File StudFile=new File(TurbineServlet.getRealPath("/Registration/"+filnm+"/"+StudF));
        	                                		Vector msg2=RegisterMultiUser.Multi_Register(StudFile,"","student",LangFile,instName);
	                	                        	msg.addAll(msg2);
        	                	        	}
						}
						else{
							data.setMessage(MultilingualUtil.ConvertedString("upload_msg7",LangFile));
						}
				}
				else{
					 data.setMessage(MultilingualUtil.ConvertedString("upload_msg8",LangFile));
				}
                                context.put("Msg",msg);
                        
                        /**
                        * Remove Directory after successfully registration of User.
                        **/
				p = r.exec("rm  -fr "+ZipDir);
                     		p.waitFor();
               } 
        }
                catch(Exception ex){
                data.setMessage("The Error in Zip File Uploading!! "+ex);
                }
        }

	public void doDeleteRollno(RunData data, Context context)
        {
                try{
			ParameterParser pp=data.getParameters();
			String msg="";
			String ProgramList=data.getParameters().getString("deleteFileNames","");
                	ErrorDumpUtil.ErrorLog("inside do update method in action file"+ProgramList);
			if(!ProgramList.equals("")){
                                StringTokenizer st=new StringTokenizer(ProgramList,"^");
                                for(int i=0;st.hasMoreTokens();i++){
                                        String s=st.nextToken();
                                        ErrorDumpUtil.ErrorLog("Token inside string---"+s);
                                        String rlno=StringUtils.substringAfterLast(s,":");
                                        ErrorDumpUtil.ErrorLog("rollno after ---"+rlno);
                                        String Prgname=StringUtils.substringBeforeLast(s,":");
                                        ErrorDumpUtil.ErrorLog("prg before---"+Prgname);

					String  InstId=(data.getUser().getTemp("Institute_id")).toString();
					String uname = pp.getString("username");
					//String rlno = pp.getString("rollno");
					//String Prgname = pp.getString("Prg");
					String Pgcode = InstituteIdUtil.getPrgCode(Prgname);
					//CourseProgramUtil.DeleteCoursePrg(uname,"");
					Criteria crit=new Criteria();
					crit.add(CourseProgramPeer.EMAIL_ID,uname);
		                        crit.and(CourseProgramPeer.PROGRAM_CODE,Pgcode);
		                        List v=CourseProgramPeer.doSelect(crit);
					if(v.isEmpty()==false){
						crit=new Criteria();
						crit.add(CourseProgramPeer.EMAIL_ID,uname);
		        	                crit.and(CourseProgramPeer.PROGRAM_CODE,Pgcode);
                			        CourseProgramPeer.doDelete(crit);
					}
					crit=new Criteria();
                		        crit.add(StudentRollnoPeer.EMAIL_ID,uname);
		                        crit.and(StudentRollnoPeer.INSTITUTE_ID,InstId);
		                        crit.and(StudentRollnoPeer.ROLL_NO,rlno);
		                        crit.and(StudentRollnoPeer.PROGRAM,Pgcode);
		                        List ve=StudentRollnoPeer.doSelect(crit);
					if(ve.isEmpty()==false){
						crit=new Criteria();
		        	                crit.add(StudentRollnoPeer.EMAIL_ID,uname);
		                	        crit.and(StudentRollnoPeer.INSTITUTE_ID,InstId);
						crit.and(StudentRollnoPeer.ROLL_NO,rlno);
						crit.and(StudentRollnoPeer.PROGRAM,Pgcode);
        			                StudentRollnoPeer.doDelete(crit);	
					}
				}
			}
                        else{
                               msg="Program is not selected.";
                	}
			data.setMessage(msg);
		}
		catch(Exception e){
			ErrorDumpUtil.ErrorLog("Exception in deleting rollno (UserAction_InstituteAdmin)!! "+e);
		}
	}
	public void doUpdateRollno(RunData data, Context context)
	{
		try{
			String msg="";
	                LangFile=(String)data.getUser().getTemp("LangFile");
        	        MultilingualUtil m_u=new MultilingualUtil();
			String InstId=(data.getUser().getTemp("Institute_id")).toString();
			String ProgramList=data.getParameters().getString("deleteFileNames","");
			String uname=data.getParameters().getString("username");
                	//ErrorDumpUtil.ErrorLog("inside do update method in action file"+ProgramList);
			ArrayList list = new ArrayList();
                        Map map = new HashMap();
        	        if(!ProgramList.equals("")){
				StringTokenizer st=new StringTokenizer(ProgramList,"^");
        	                for(int i=0;st.hasMoreTokens();i++){
                                        String s=st.nextToken();
                                        //ErrorDumpUtil.ErrorLog("Token inside string---"+s);
                                        String rollno=StringUtils.substringAfterLast(s,":");
                                        //ErrorDumpUtil.ErrorLog("rollno after ---"+rollno);
					if(StringUtil.checkString(rollno) != -1)
                        		/**
				         * check if rollno have any special character then return message
                                         */
		                        {
                		                data.addMessage(MultilingualUtil.ConvertedString("quiz_msg8",LangFile));
		                                data.addMessage(MultilingualUtil.ConvertedString("ProxyuserMsg3",LangFile));
                		               return;
		                        }
                                        String prg=StringUtils.substringBeforeLast(s,":");
                                        //ErrorDumpUtil.ErrorLog("prg before---"+prg);
					String prgcode = InstituteIdUtil.getPrgCode(prg);
					//List lst=CheckRollnoExistence(uname,prgcode,rollno,InstId);
					List rollnolist=CourseProgramUtil.CheckDuplicateRollno(rollno,prgcode,InstId);
                                        //ErrorDumpUtil.ErrorLog("rollnolist inside doupdate action---"+rollnolist);
					if(rollnolist.size()>0)
		                        {
						String usrname= ((StudentRollno)rollnolist.get(0)).getEmailId();
						if(!uname.equals(usrname)){
						String Program= ((StudentRollno)rollnolist.get(0)).getProgram();
						String pgname = InstituteIdUtil.getPrgName(Program);
						String rlno= ((StudentRollno)rollnolist.get(0)).getRollNo();
						map = new HashMap();
        	                                map.put("confuname",usrname);
        	                                map.put("confProgram",pgname);
        	                                map.put("confRollno",rlno);
						map.put("uname",uname);
						map.put("Program",prg);
						map.put("Rollno",rollno);
						list.add(map);
						context.put("Rollnolist",list);
						msg=MultilingualUtil.ConvertedString("prgm_msg3",LangFile);
						}
		                        }
					else
					{
						msg=CourseProgramUtil.updateRollno(uname,prg,rollno,InstId,LangFile);
					}
                	        }
			}
			else{
				msg=MultilingualUtil.ConvertedString("prgm_msg4",LangFile);
                	}
                        	data.setMessage(msg);
		}
		catch(Exception e)
		{
			ErrorDumpUtil.ErrorLog("Exception in update rollno (UserAction_InstituteAdmin)!! "+e);
		}
		
	}
	 public void doUpdateConflictRollno(RunData data, Context context)
        {
                try{
			String msg="";
			ArrayList list = new ArrayList();
                         Map map = new HashMap();
			String  InstId=(data.getUser().getTemp("Institute_id")).toString();
			String confPrg=data.getParameters().getString("confProgram","");
			//ErrorDumpUtil.ErrorLog("Confprogram -----------"+confPrg);
			int rlcount=Integer.parseInt(data.getParameters().getString("rlnocount",""));
			//ErrorDumpUtil.ErrorLog("rlcount -----------"+rlcount);
			String pgcode = InstituteIdUtil.getPrgCode(confPrg);
			//ErrorDumpUtil.ErrorLog("Confprogram code-----------"+pgcode);
			String rlno="",uname="";
			boolean flag=true;
			for(int i=1;i<=rlcount;i++)
			{
				if(flag){
				rlno=data.getParameters().getString("rollno"+i,"");
				//ErrorDumpUtil.ErrorLog("rollno -----------"+rlno);
				if(StringUtil.checkString(rlno) != -1)
	                        /**
	                         * check if rollno have any special character then return message
                                 */
	                        {
        	                        data.addMessage(MultilingualUtil.ConvertedString("quiz_msg8",LangFile));
                	                data.addMessage(MultilingualUtil.ConvertedString("ProxyuserMsg3",LangFile));
                        	       return;
	                        }
				if(i==1)
					uname=data.getParameters().getString("confuser","");
				else
					uname=data.getParameters().getString("username");
				//List lst1=CheckRollnoExistence(confuname,pgcode,rlno,InstId);
				//ErrorDumpUtil.ErrorLog("uname -----------"+uname);
				List rollnolist=CourseProgramUtil.CheckDuplicateRollno(rlno,pgcode,InstId);
                        	if(rollnolist.size()>0)
	                        {
 		                       	String usrname= ((StudentRollno)rollnolist.get(0)).getEmailId();
					//ErrorDumpUtil.ErrorLog("username inside loop---"+usrname);
                	               	String Program= ((StudentRollno)rollnolist.get(0)).getProgram();
	                               	String pgname = InstituteIdUtil.getPrgName(Program);
        	                       	String rollno= ((StudentRollno)rollnolist.get(0)).getRollNo();
	                               	map = new HashMap();
        	                       	map.put("confuname",usrname);
                	               	map.put("confProgram",pgname);
	                               	map.put("confRollno",rollno);
        	                       	map.put("uname",uname);
                	               	map.put("Program",confPrg);
	                       	       	map.put("Rollno",rollno);
        	                       	list.add(map);
					flag=false;
	                	        context.put("Rollnolist",list);
	                        }
        		        else
                	        {
                        	        msg=CourseProgramUtil.updateRollno(uname,confPrg,rlno,InstId,LangFile);
	                        }
				}
			}
			data.setMessage(msg);
		}
		catch(Exception e)
		{
			ErrorDumpUtil.ErrorLog("Exception in update conflict roll no (UserAction_InstituteAdmin)!! "+e);
		}
	}
	/**
         * ActionEvent responsible for removing a user from the system
	 * @param data RunData
         * @param context Context
         */
	public void doDelete(RunData data,Context context)
        {
             	try{
	                //get username of an user whom to be removed.
                        String instituteid=(data.getUser().getTemp("Institute_id")).toString();
                        MultilingualUtil mu=new MultilingualUtil();
                        LangFile=(String)data.getUser().getTemp("LangFile");
                        ParameterParser pp=data.getParameters();
                        String username=pp.getString("username");
                        String mode=pp.getString("mode");
                        String count=pp.getString("count");
                        int userid = UserUtil.getUID(username);

                        //Get the associated institute by an user.

                        InstituteIdUtil ini=new InstituteIdUtil();
                        Vector instid=ini.getUserAllInstId(userid);

                        //Get associated group of an user.

                        List groupid=null;
                        GroupUtil grputil=new GroupUtil();
                        UserUtil usu=new UserUtil();
                        groupid=usu.inst_stud_group(userid);
                        //get all group name from group id

                        Vector grpname = new Vector();
                        for(int grpid=0;grpid<groupid.size();grpid++)
                        {
                                TurbineUserGroupRole element=(TurbineUserGroupRole)groupid.get(grpid);
                                int idgroup=element.getGroupId();
                                int idrole=element.getRoleId();
                                //get role of user in a group

                                String role="";
                                String grp_name=grputil.getGroupName(idgroup);
				//ErrorDumpUtil.ErrorLog();
                                if(!grp_name.contains(username) && (idrole==3))
                                        role="student";

                                if(grp_name.contains(username) && (idrole==2))
                                        role="pinstructor";

                                if(!grp_name.contains(username) && (idrole==2))
                                        role="sinstructor";
				if(!grp_name.contains(username) && (idrole==8))
                                        role="teaching_assistant";


                                usercourseinfr usrcsrinf = new usercourseinfr();
                                if(grp_name.endsWith(instituteid))
                                {
                                        Criteria crit=new Criteria();
                                        crit.add(CoursesPeer.GROUP_NAME,grp_name);
                                        List grprecord=CoursesPeer.doSelect(crit);
                                        Courses crs=(Courses)grprecord.get(0);
                                        String active=(new Byte(crs.getActive())).toString();
                                        String act="";
                                        if(active.equals("0"))
                                                act="Inactive";
                                        if(active.equals("1"))
                                                act="Active";
                                        String cname=crs.getCname();
                                        usrcsrinf.setgrp(grp_name);
                                        usrcsrinf.setrole(role);
                                        usrcsrinf.setcname(cname);
                                        usrcsrinf.setactive(act);
                                        grpname.add(usrcsrinf);
                                }
                        }

                        //ErrorDumpUtil.ErrorLog("Size of GroupName vector ===>"+grpname.size());
                        context.put("grpname",grpname);
                        context.put("uname",username);
                        context.put("noList","nolist");
                }
                catch(Exception exp)
                {
                }
        }
	
        public void doExpire(RunData data, Context context)
        {
                try{

                        LangFile=(String)data.getUser().getTemp("LangFile");
                        String grouprecord=data.getParameters().getString("deleteFileNames","");
                        MultilingualUtil mu=new MultilingualUtil();
                        String username=data.getParameters().getString("username");
			String subject="", info_new = "", info_Opt="", msgRegard="", msgInstAdmin="",groupname="",userrole="",coursestatus="",coursename="",instFirstLastName="",mailsubject="";
			User user = data.getUser();
			String instituteId=(user.getTemp("Institute_id")).toString();
                	String instAdminName = user.getName();
			int aUid = 0;
                        String msg="";
                        Vector Message=new Vector();
                        UserManagement usmt=new UserManagement();
			String instName=InstituteIdUtil.getIstName(Integer.parseInt(instituteId));
		 	int instIdint=InstituteIdUtil.getIst_Id(instName);
			String server_name=TurbineServlet.getServerName();
		        String serverport=TurbineServlet.getServerPort();
                        if(serverport.equals("8080"))
			{
	                        info_new = "deleteUser";
				info_Opt = "newUser";
			}
                        else
			{
                                info_new = "deleteUserhttps";
				info_Opt = "newUserhttps";;
			}	
			Criteria crit=new Criteria();
                	try{
                		crit.add(InstituteAdminUserPeer.INSTITUTE_ID,instIdint);
                        	List inm=InstituteAdminUserPeer.doSelect(crit);
                        	InstituteAdminUser element=(InstituteAdminUser)inm.get(0);
				/****** shaista ***********
                         	* Getting full name of user using UserUtil.
                         	* @see UserUtil in utils
                         	*/
				aUid = UserUtil.getUID(element.getAdminUname());
                        	instFirstLastName =UserUtil.getFullName(aUid);
			}
                	catch(Exception ex){
                		ErrorDumpUtil.ErrorLog("The error in UserAction_InstituteAdmin class at line 263 !!"+ex);
			}
                        String filename=TurbineServlet.getRealPath("/WEB-INF/conf/brihaspati.properties");
                        Properties pr =MailNotification.uploadingPropertiesFile(filename);
			try{
			msgRegard=pr.getProperty("brihaspati.Mailnotification."+info_Opt+".msgRegard");
	                msgRegard = MailNotification.replaceServerPort(msgRegard, server_name, serverport);
			}catch(Exception ex1){ErrorDumpUtil.ErrorLog("exception message===>"+ex1.getMessage());}
        	        msgInstAdmin=pr.getProperty("brihaspati.Mailnotification."+info_Opt+".msgInstAdmin");
                	if ( instFirstLastName.length() > 0)
                		msgInstAdmin = msgInstAdmin.replaceAll("institute_admin", instFirstLastName);
			else if(instAdminName.length() > 0)
                		msgInstAdmin = msgInstAdmin.replaceAll("institute_admin", instAdminName);
			subject = MailNotification.subjectFormate(info_new, "", pr );
                        String email="", Mail_msg="", message="", loginname="",infrmtn="";
                        loginname=username;
                        String userid=Integer.toString(UserUtil.getUID(username));
                        email=((TurbineUser)UserManagement.getUserDetail(userid).get(0)).getEmail();
                        if(!grouprecord.equals(""))
                        {
                                StringTokenizer st=new StringTokenizer(grouprecord,"^");
                                for(int j=0;st.hasMoreTokens();j++)
                                {
                                        String str1=st.nextToken();
                                        String str2[]=str1.split(",");
                                        groupname=str2[0];
                                        userrole=str2[1];
                                        coursestatus=str2[2];
                                        coursename=str2[3];
					message = MailNotification.getMessage(info_new, coursename, "", "", "", pr);
                			message = message.replaceAll("institute_admin",instName);
					Mail_msg=  MailNotificationThread.getController().set_Message(message, "", msgRegard, msgInstAdmin, email, subject, "", LangFile, instituteId,"");
                                        data.setMessage(Mail_msg);
                                        String first=mu.ConvertedString("delIns1",LangFile);
                                        String second=mu.ConvertedString("delIns2",LangFile);
                                        infrmtn=first+" "+username+" "+second;
                                        if(userrole.equals("pinstructor"))	
                                        {

                                                if(coursestatus.equals("Active"))
                                                        coursestatus="0";
                                                else
                                                        coursestatus="0";

                                                String updatecoursestatus=CourseManagement.UpdateCourseDetails(groupname,coursename,"","",coursestatus,LangFile);
                                                msg=UserManagement.DeleteInstructor(groupname,LangFile);	
						message = MailNotification.getMessage(info_new, coursename, "", "", "", pr);
	                                        message = message.replaceAll("institute_admin",instName);
        	                                Mail_msg=  MailNotificationThread.getController().set_Message(message, "", msgRegard, msgInstAdmin, email, subject, "", LangFile, instituteId,"");
                	                        data.setMessage(Mail_msg);
                                                Message.add(msg);
                                        }
                                        else
                                        {
						message = MailNotification.getMessage(info_new, groupname, "", "", "", pr);
       						message = message.replaceAll("institute_admin",instName);
						Mail_msg=  MailNotificationThread.getController().set_Message(message, "", msgRegard, msgInstAdmin, email, subject, "", LangFile, instituteId,"");						
                                                msg=usmt.removeUserProfileWithMail(username,groupname,LangFile,info_new,email,"firstName",loginname,"","",filename,server_name,serverport);
                                                Message.add(msg);
                                        }
                                }
                        }
                        context.put("Message",Message);
                        context.put("infrmtn",username);
                }
                catch(Exception ex)
                {
                        data.setMessage("The error in doExpire method -"+ex);
                }
        }


	 /**
          * ActionEvent responsible if no method found in this action i.e. Default Method
          * @param data RunData
          * @param context Context
	  */

	public void doPerform( RunData data,Context context )
	throws Exception
    	{
        	String action=data.getParameters().getString("actionName","");
		context.put("actionName",action);
		LangFile=(String)data.getUser().getTemp("LangFile");
		if(action.equals("eventSubmit_doUpdatePass"))
			doUpdatePass(data,context);
		else if(action.equals("eventSubmit_doDelete"))
			doDelete(data,context);
		else if(action.equals("eventSubmit_doExpire"))
			doExpire(data,context);
		else if(action.equals("eventSubmit_doUpdate"))
			doUpdate(data,context);
		else if(action.equals("eventSubmit_doUploadadmin"))
			doUploadMultiUser(data,context);
		else if(action.equals("eventSubmit_doUploadphoto"))
			doUploadMultiUserPhoto(data,context);
		else if(action.equals("eventSubmit_doUploadLogo"))
			doUploadLogo(data,context);
		else if(action.equals("eventSubmit_doSearch"))
			doSearch(data,context);
		else if(action.equals("Search"))
                        setTemplate(data,"call,ListMgmt_InstituteAdmin,InstAdminviewall.vm");
		else if(action.equals("eventSubmit_doUploadMultiUserZip"))
                        doUploadMultiUserZip(data,context);
		else if(action.equals("eventSubmit_doDeleteRollno"))
			doDeleteRollno(data,context);
		else if(action.equals("eventSubmit_doUpdateRollno"))
			doUpdateRollno(data,context);
		else if(action.equals("eventSubmit_doUpdateConflictRollno"))
			doUpdateConflictRollno(data,context);
		else
		{
			 /**
                          * Getting file value from temporary variable according to selection of Language
                          * Replacing the static value from Property file
                          **/ 
			String action_msg=MultilingualUtil.ConvertedString("action_msg",LangFile);
			data.setMessage(action_msg);
			
		}
    	}
}
