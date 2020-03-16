package org.iitk.brihaspati.modules.actions;

/*
 * @(#)UploadMarksAction.java	
 *
 *  Copyright (c) 2005, 2009, 2010 ETRG,IIT Kanpur. 
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
 */

import java.io.File;
import java.io.*;
import java.util.*;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

import org.apache.velocity.context.Context;
import org.apache.commons.fileupload.FileItem;

import org.apache.torque.util.Criteria;

import org.apache.turbine.util.RunData;
import org.apache.turbine.util.parser.ParameterParser;
import org.apache.turbine.om.security.User;
import org.apache.turbine.services.servlet.TurbineServlet;
import org.apache.turbine.services.security.torque.om.TurbineUser;
import org.apache.turbine.services.security.torque.om.TurbineUserPeer;

import org.iitk.brihaspati.modules.utils.GroupUtil;
import org.iitk.brihaspati.modules.utils.StringUtil;
import org.iitk.brihaspati.modules.utils.MultilingualUtil;
import org.iitk.brihaspati.modules.utils.MailNotification;
import org.iitk.brihaspati.modules.utils.MailNotificationThread;
import org.iitk.brihaspati.modules.utils.CourseUserDetail;
import org.iitk.brihaspati.modules.utils.UserGroupRoleUtil;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.iitk.brihaspati.modules.utils.XMLWriter_Marks;
import org.apache.commons.lang.StringUtils;

/**
 * In this class, We upload marks in txt format file
 * @author <a href="mailto:nksinghiitk@yahoo.co.in">Nagendra Kumar Singh</a>
 * @author <a href="mailto:ammu_india@yahoo.com">Amit Joshi</a>
 * @author <a href="mailto:awadhesh_trivedi@yahoo.co.in">Awadhesh Kumar Trivedi</a>
 * @author <a href="mailto:shaistashekh@hotmail.com">Shaista</a>
 * @author <a href="mailto:sunil.singh6094@gmail.com">Sunil Kumar Pal</a>
 * @author <a href="mailto:richa.tandon1@gmail.com">Richa Tandon</a>
 * @author <a href="mailto:vipulk@iitk.ac.in">Vipul Kumar Pal</a>
 * @modified date 29-12-2009
 * @modified date: 08-07-2010
 * @modified date 15-09-2010, 16-06-2011 (Shaista)
 */

public class UploadMarksAction extends SecureAction_Instructor
{
	
    public void doUpload(RunData data, Context context)
    {
	try{
		String LangFile=(String)data.getUser().getTemp("LangFile");
		ParameterParser pp=data.getParameters();
		User user=data.getUser();
		String userName=user.getName();
		String serverName= TurbineServlet.getServerName();
                String serverPort= TurbineServlet.getServerPort();
		String cName=(String)user.getTemp("course_id","");
		String coursesRealPath=TurbineServlet.getRealPath("/Courses");
                String destDir=coursesRealPath+"/"+cName+"/Marks";
                String xmlpath=coursesRealPath+"/"+cName+"/Marks.xml";
		File MarksDir=new File(destDir);
		boolean marksExist=false;
		if(!MarksDir.exists()){
	                MarksDir.mkdirs();
		}
		String text="null";
		String myvalue = pp.getString("myvalue");
		String mailflag = pp.getString("sendMailu","");
		for(int count=0;count<10;count++){
			FileItem fileItem=pp.getFileItem("file"+(count+1));
			text = pp.getString("text"+(count+1));
			String fileName=fileItem.getName();
			if((fileItem!=null) && (fileItem.getSize()!=0))
        	        {
				if(!(fileName.toLowerCase()).endsWith(".txt") && !(fileName.toLowerCase()).endsWith(".csv"))
        	        	        {
                	        	         /**
                        	        	 * Getting file value from temporary variable according to selection of Language
	                                	 * Replacing the static value from Property file
		                                 **/
        		                        String upload_msg3=MultilingualUtil.ConvertedString("upload_msg2",LangFile);
                		                data.addMessage(upload_msg3);
	                        	}
				else{
					File marksFile=new File(MarksDir,text+"-"+fileItem.getName());
					String check = "";

					String checked = XMLWriter_Marks.Check(xmlpath,"checked");
					if(checked.equals("Exist")){
						check = "checked";
					}
					String checkalias = XMLWriter_Marks.CheckElement(xmlpath,text);
					if(checkalias.equals("Exist")){
						marksExist=true;
						String FName = XMLWriter_Marks.ReadFileNameElement(xmlpath,text);
						String remove = XMLWriter_Marks.RemoveElement(xmlpath,text);
						File DelFile=new File(destDir+"/"+text+"-"+FName);
		                	        DelFile.delete();
					}
					String markxml = XMLWriter_Marks.MarksXml(xmlpath,fileItem.getName(),text,check);
                                	File tempFile=new File(TurbineServlet.getRealPath("/tmp")+"/"+userName+".txt");
	                                fileItem.write(tempFile);
        	                        StringUtil.insertCharacter(tempFile.getAbsolutePath(),marksFile.getAbsolutePath(),',','-');
                	                tempFile.delete();
					if(mailflag.equals("sendMail")){
						SendMail(data,user,cName,userName,marksExist);
					}
				}
			}
			else{
				data.setMessage(MultilingualUtil.ConvertedString("Marks_msg8",LangFile));
			}
		}
	}
	catch(Exception ex)
	{
		ErrorDumpUtil.ErrorLog("we are in Exception of uploadmarksaction"+ex);
	}
    }

	/**
	 *Below method for saving marks 
	 */
	 public void doSave(RunData data, Context context)
		{// function start
        		try{// 1 try
				String LangFile=data.getUser().getTemp("LangFile").toString();
				/**
	                         * @param user Getting User object
        	                 */
				User user=data.getUser();
				String fileName="";
				/**
                                 * @param cName getting course name which is set by setTemp() method
                                 */
                                String cName=(String)user.getTemp("course_id");

                                /**
                                 * getting actual path where marks have to be saved
                                 */
                                String coursesRealPath=TurbineServlet.getRealPath("/Courses");
                                String destDir=coursesRealPath+"/"+cName+"/Marks/";
                                String destDirtemp=coursesRealPath+"/"+cName;
                		String xmlpath=coursesRealPath+"/"+cName+"/Marks.xml";
				/**
				 *set status null that shows status is not edit
				 */
				context.put("status","null");
				ParameterParser pp=data.getParameters();
				String filename = pp.getString("fileName","MARK.txt");
				String type = pp.getString("type","");
				String alias = pp.getString("alias","");
				String mailflag = pp.getString("sendMails","");
				if(type.equals("SpreadSheet")){
					fileName=alias+"-"+filename;
					String check = "";
		                        String checked = XMLWriter_Marks.Check(xmlpath,"checked");
                		        if(checked.equals("Exist")){
		                        check = "checked";
                		        }
		                        String checkalias = XMLWriter_Marks.CheckElement(xmlpath,alias);
                		        if(!checkalias.equals("Exist")){
                                		String markxml = XMLWriter_Marks.MarksXml(xmlpath,filename,alias,check);
		                                String Marks_msg2=MultilingualUtil.ConvertedString("Marks_msg2",LangFile);
                		                data.addMessage(Marks_msg2);
		                        }else{
                	                String FName = XMLWriter_Marks.ReadFileNameElement(xmlpath,alias);
                        	        String remove = XMLWriter_Marks.RemoveElement(xmlpath,alias);
                                	File DelFile=new File(destDir+alias+"-"+FName);
	                                DelFile.delete();
        	                        String markxml = XMLWriter_Marks.MarksXml(xmlpath,filename,alias,check);
                	                String Marks_msg6=MultilingualUtil.ConvertedString("Marks_msg6",LangFile);
                        	        data.addMessage(Marks_msg6);
                        		}
				}
				else{
					fileName = filename;
				}
			
				File marksDir=new File(destDir);
				File marksDirtemp=new File(destDirtemp);
                                File marksFile=new File(marksDir,fileName);
                                File tmpFile=new File(marksDirtemp,"TMPMARK.txt");
                                boolean marksExist=false;

                                /**
                                 * Check if the marks file exists
                                 */
                                if(marksFile.exists())
                                {// 1 if
                                        marksExist=true;
                                }// end of 1 if
                                marksDir.mkdirs();
 
				/**
				 *@param userName Getting username 
				 */		
				String userName=user.getName();
			
				/**
				 *@param items String having values of spreadsheet
				 */
				String items = pp.getString("fieldValue");
			
				/**
				 *@param tempFile Create file inside brihaspati's tmp folder having name as username.txt
				 */
			        File tempFile=new File(TurbineServlet.getRealPath("/tmp")+"/"+userName+".txt");
			
				/**
				 * Write items of spreadsheet in tempFile
				 */
				try
				{// 2 try
					Writer output = null;
					output = new BufferedWriter(new FileWriter(tempFile));
					output.write(items);
					output.close();
				}// end of 2 try
				catch(Exception e)
				{//2 catch
					ErrorDumpUtil.ErrorLog("Exception in writing temp marks file"+e);
				}// end of 2 catch	
				/**
				 * Check length of temp file
				 * if {length is 100, it gives file is empty
				 */
                       		long len = tempFile.length();
				//ErrorDumpUtil.ErrorLog("length of temp file is"+len); 
				if(len == 160)
				{// 2 if
					String UploadFile = MultilingualUtil.ConvertedString("brih_file",LangFile);
					String  empty = MultilingualUtil.ConvertedString("brih_empty",LangFile);
					data.setMessage(UploadFile+" "+empty);
					tempFile.delete();
					
				}// end of 2 if
				else
				{// 2 else
				/**
				 * else Call StringUtil for writing values in MARK.txt and TMPMARK.txt
				 */
			        	StringUtil.insertCharSpreadsheet(tempFile.getAbsolutePath(),marksFile.getAbsolutePath(),tmpFile.getAbsolutePath(),',','-');
					tempFile.delete();
					if(mailflag.equals("sendMail")){
						SendMail(data,user,cName,userName,marksExist);
					}
					
				}// end of 2 else
			}// end of 1 try
			catch(Exception ex)
			{// 1 catch
				data.setMessage("The error in Saving Marks !!"+ex);
        		}// end of 1 catch
		}// end of function
	
	/**
	 *method for deleting already exist file
	 */
		public void doDelete(RunData data, Context context)
		{// function start
			try{// 1 try
				String LangFile=data.getUser().getTemp("LangFile").toString();

				/**
                                 * @param user Getting User object
                                 */
				User user=data.getUser();
				/**
				 * @param pp instance of ParameterParser
				 */
                                ParameterParser pp=data.getParameters();
				String alias = pp.getString("alias","");
				 /**
                                 * @param cName getting course id which is set by setTemp() method
                                 */
                                String cName=(String)user.getTemp("course_id");
				 /**
                                 * getting actual path where marks saved
                                 * @RETURN String
                                 */


				String coursesRealPath1=TurbineServlet.getRealPath("/Courses"+"/"+cName+"/Marks");
				String FileName = pp.getString("FileName","");
		                String coursesRealPath11=TurbineServlet.getRealPath("/Courses"+"/"+cName+"/Marks.xml");
				String remove = XMLWriter_Marks.RemoveElement(coursesRealPath11,alias);

		                File MarksDir=new File(coursesRealPath1);
				String[] listOfFiles = MarksDir.list();

                 		for (int i=0; i<listOfFiles.length; i++){
		                        String filename=listOfFiles[i];
					String flName = StringUtils.substringBefore(filename,"-");
                		        //String flName[]=filename.split("\\-");

		 	               if( alias.equals(flName)){
		 				File DelFile=new File(coursesRealPath1+"/"+filename);
		 				DelFile.delete();
                                		String Marks_msg7=MultilingualUtil.ConvertedString("Marks_msg7",LangFile);
						data.setMessage(Marks_msg7);
                			}
                		}
			}// end of 1 try
			catch(Exception e)
			{// 1 catch
				data.setMessage("The error in delete Marks file!!"+e);
			}// end of 1 catch

		}// end of function

	/**
	 * below method for sending mail to instructor and students 
	 */

	public void SendMail(RunData data,User user, String courseHome,String userName, boolean marksExist)
		{
			try
				{
					String [] splitCrs = courseHome.split("_");
					ErrorDumpUtil.ErrorLog("splitCrs length in uploadMarks Action="+splitCrs.length); 
					//String instId = splitCrs[1];
					//ErrorDumpUtil.ErrorLog("instId in uploadMarks Action="+instId); 
					String LangFile=data.getUser().getTemp("LangFile").toString();
//					String serverName= TurbineServlet.getServerName();
  //                              	String serverPort= TurbineServlet.getServerPort();
//					String server_scheme = TurbineServlet.getServerScheme();
	                                String mailMsg = "";//help.ubuntu.com/


				 	String mailId="";
	                                //String marksUploadStr="Marks are uploaded in"+courseHome;
					// Shaista did Modification for mail Sending 
	                                //String info_new ="", info_Opt="";

	                                /*if(serverPort == "8080"){
        	                                info_new = "marksUpload";
						 info_Opt = "newUser";
					}
	                                else {
        	                                info_new = "marksUpload_https";
						 info_Opt = "newUserhttps";
                                	}*/
	                                Properties pr =MailNotification.uploadingPropertiesFile(TurbineServlet.getRealPath("/WEB-INF/conf/brihaspati.properties"));
	                                //String subject = MailNotification.subjectFormate(info_new, "", pr );
	                                String subject = MailNotification.subjectFormate("marksUpload", "", pr );
					//String message = MailNotification.getMessage(info_new, courseHome, "", "", "", serverName, serverPort, pr);
					String message = MailNotification.getMessage("marksUpload", courseHome, "", "", "", pr);
					message = MailNotification.replaceServerPort(message);
					//String msgRegard=pr.getProperty("brihaspati.Mailnotification."+info_Opt+".msgRegard");
	                                String msgRegard=pr.getProperty("brihaspati.Mailnotification.newUser.msgRegard");
					//msgRegard = MailNotification.replaceServerPort(msgRegard, serverName, serverPort);
	                                msgRegard = MailNotification.replaceServerPort(msgRegard);
					//ErrorDumpUtil.ErrorLog("\n\n\n\n Upload marks.java message="+message+"      subject="+subject);
					Criteria crit=new Criteria();
					crit.add(TurbineUserPeer.LOGIN_NAME,userName);
                                	//mail for Instructor
	                                try
	                                {
	                                        List v=TurbineUserPeer.doSelect(crit);
	                                        TurbineUser element=(TurbineUser)v.get(0);
	                                        mailId=element.getEmail();
	                                        if(mailId != null && mailId != "")
		                                                //mailMsg=MailNotification.sendMail(message, mailId, subject, "", (String)user.getTemp("LangFile"));
					/*		if(!instId.equals(""))
								 mailMsg = MailNotificationThread.getController().set_Message(message, "", msgRegard, " ", mailId, subject, "", LangFile, instId,"");//last parameter added by Priyanka
							else			
								 mailMsg = MailNotificationThread.getController().set_Message(message, "", msgRegard, " ", mailId, subject, "", LangFile, "","");//last parameter added by Priyanka
					*/
						mailMsg = MailNotificationThread.getController().set_Message(message, "", msgRegard, " ", mailId, subject, "", LangFile);
					mailId = "";
	                                }
	
			
			catch(Exception e)
	                                        {data.setMessage("The error in sending mail to instructor for update/Upload marks !!"+e);}
	
	                                //mail for student
	                                try{
	                                        int grid=GroupUtil.getGID(courseHome);
	                                        Vector usDetail=UserGroupRoleUtil.getUDetail(grid, 3);
		                                        for(int i=0; i<usDetail.size(); i++){
		                                                mailId=((CourseUserDetail) usDetail.elementAt(i)).getEmail().trim();
	                                                if(mailId != null && mailId != ""){
	                                                //mailMsg=MailNotification.sendMail(message, mailId, subject, "", (String)user.getTemp("LangFile"));
						/*	if(!instId.equals(""))
								 mailMsg = MailNotificationThread.getController().set_Message(message, "", msgRegard, " ", mailId, subject, "", LangFile, instId,"");//last parameter added by Priyanka
							else						
								mailMsg = MailNotificationThread.getController().set_Message(message, "", msgRegard, " ", mailId, subject, "", LangFile, "","");//last parameter added by Priyanka
	                                          */
							mailMsg = MailNotificationThread.getController().set_Message(message, "", msgRegard, " ", mailId, subject, "", LangFile);
							}
	 	                                       }
	                                               usDetail=null;
                	                }
                        	        catch(Exception ex)
                                	        { data.setMessage("The error in sending mail to student for update/Upload marks" + ex); }
	

        	                        String onUploadMessage=MultilingualUtil.ConvertedString("Marks_msg2",LangFile);
                	                if(marksExist)
                        	        {
                                	        onUploadMessage=MultilingualUtil.ConvertedString("Marks_msg3",LangFile);
                                	}
                                	data.setMessage(onUploadMessage);
                                	if(mailId != null && mailId != "")
                                        	data.addMessage(mailMsg);
                                	else
                                        	data.addMessage( MultilingualUtil.ConvertedString("mailNotification_msg2",(String)user.getTemp("LangFile")));

			}
			catch(Exception ex)
				{data.setMessage("The error in sending mail method");}
  		}
    /**
     * Default action to perform if the specified action
     * cannot be executed.
     */
    public void doPerform( RunData data,Context context )
        throws Exception
    {
	String action=data.getParameters().getString("actionName","");
	if(action.equals("eventSubmit_doUpload"))
		doUpload(data,context);
	else if(action.equals("eventSubmit_doSave"))
                doSave(data,context);
	else if(action.equals("eventSubmit_doDelete"))
		doDelete(data,context);
	else
        	data.setMessage("Can't find the requested action! ");
    }
}
