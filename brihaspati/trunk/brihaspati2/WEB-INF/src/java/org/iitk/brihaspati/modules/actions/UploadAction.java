package org.iitk.brihaspati.modules.actions;
/*
 * @(#)UploadAction.java
 *
 *  Copyright (c) 2005-2012, 2013 ETRG,IIT Kanpur.
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
import java.io.InputStream;
import java.io.FileOutputStream;
import java.util.Vector;
import java.util.List;
import java.util.StringTokenizer;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.HttpURLConnection;
import java.net.URLConnection;
import java.io.IOException;

import org.apache.velocity.context.Context;
import org.apache.turbine.util.RunData;
import org.apache.turbine.util.parser.ParameterParser;
import org.apache.turbine.om.security.User;
import org.apache.turbine.services.servlet.TurbineServlet;
import org.apache.turbine.services.security.torque.om.TurbineUserGroupRolePeer;
import org.apache.turbine.services.security.torque.om.TurbineUserGroupRole;
import org.apache.turbine.services.security.torque.om.TurbineUserPeer;
import org.apache.turbine.services.security.torque.om.TurbineUser;
import org.apache.torque.util.Criteria;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Date;
import org.iitk.brihaspati.om.CoursesPeer;
import org.iitk.brihaspati.modules.utils.UserUtil;
import org.iitk.brihaspati.modules.utils.CourseUtil;
import org.iitk.brihaspati.modules.utils.FileEntry;
import org.iitk.brihaspati.modules.utils.XmlWriter;
import org.iitk.brihaspati.modules.utils.FileEntry;
import org.iitk.brihaspati.modules.utils.QuotaUtil;
import org.iitk.brihaspati.modules.utils.GroupUtil;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.iitk.brihaspati.modules.utils.TotalFileCount;
import org.iitk.brihaspati.modules.utils.InstituteIdUtil;
import org.iitk.brihaspati.modules.utils.MultilingualUtil;
import org.iitk.brihaspati.modules.utils.UserGroupRoleUtil;
import org.iitk.brihaspati.modules.utils.TopicMetaDataXmlWriter;
import org.iitk.brihaspati.modules.utils.TopicMetaDataXmlReader;
import org.iitk.brihaspati.modules.utils.MailNotificationThread;
import org.iitk.brihaspati.modules.utils.HDFSClient;
import org.apache.commons.lang.StringUtils;
import org.iitk.brihaspati.modules.utils.AdminProperties;


/**
 * Class responsible for Upload files in particuler Area
 *
 * @author <a href="mailto:ammu_india@yahoo.com">Amit Joshi</a>
 * @author <a href="mailto:nksinghiitk@yahoo.co.in">Nagendra Kumar Singh</a>
 * @author <a href="mailto:awadhesh_trivedi@yahoo.co.in">Awadhesh Kumar Trivedi</a>
 * @author <a href="mailto:singh_jaivir@rediffmail.com">Jaivir Singh</a>
 * @author <a href="mailto:seema_020504@yahoo.com">Seema Pal</a>
 * @author <a href="mailto:kshuklak@rediffmail.com">Kishore kumar shukla</a> 
 * @author <a href="mailto:parasharirajeev@gmail.com">Rajeev Parashari</a> 
 * @author <a href="mailto:sunil.singh6094@gmail.com">Sunil Kumar</a>
 * @author <a href="mailto:richa.tandon1@gmail.com">Richa Tandon</a>
 * @modified date:30-Apr-2012(Richa)(Guest access enable/disable)
 * @modified date: 04-Jan-2013 (Shaista) Sending Mail on Upload Content.
 * @modified date:25-04-2016 (Seemanti) Multiple File Uploading at one go implementation.
 */

public class UploadAction extends SecureAction
{
   /**
    * This method responsible for uploadng of files
    * @param data Rundata
    * @param context Context
    */
   private String LangFile=null;
   private Log log = LogFactory.getLog(this.getClass());

   public void doUpload(RunData data, Context context)
   {
/* psuedocode description
 *     get the username from RunData, current course from context, ContentTopic, publishFlag from the parameterparser.
 *     get the list of files uploaded in the course.
 *     find the sum of sizes of all the uploaded files as sumSize.
 *     Get the total space used, space allocated to be course.
 *     Find the unused space.
 *     If the unused space is more than sumSize {
 *         Find the space used by the institute, and the space allocated.
 *         If space available is more than required {
 *            If the publishFlag is true {
 *              for each file {
 *                   place the file in contentTopic area
 *                   update the xml descriptor
 *              }
 *            }
 *            else {
 *              for each file {
 *                   place each file in the unpublish area of the ContentTopic
 *              }
 *            }
 *        }
 *        else {
 *            put the message "Insitute quota will exceed with this upload. Request your institute admin to get the institute quota enhanced or upload smaller/less files, or create space by deleting existing ones."
 *            Remove the files from temporary space.
 *        }
 *     }
 *     else {
 *         put the message "course quota will exceed with this upload. Upload smaller/less files, or create space by removing existing ones."
 *         Remove the files from temporary space.
 *     }
 */
try
{
   User user=data.getUser();
   String uName=user.getName();
   int uid=UserUtil.getUID(uName);
   String fullName = UserUtil.getFullName(uid);
   LangFile=(String)user.getTemp("LangFile");
   String courseHome=(String)user.getTemp("course_id","");
   ParameterParser pp=data.getParameters();
   String contentTopic=pp.getString("contentTopic","").replaceAll("\\s+", "");
   String location=pp.getString("course","");
   String Pub=pp.getString("publish","");
   context.put("pub",Pub);
   String sendMail = pp.getString("sendMail","");
   Vector new_files_uploaded=new Vector();
   XmlWriter xmlWriter=null;
   int instituteId=Integer.parseInt(data.getUser().getTemp("Institute_id").toString());
   if(contentTopic.equals(""))
   {
      String Mu_msg=MultilingualUtil.ConvertedString("uploadAction_msg ",LangFile);
      context.put("errorMess",Mu_msg);
      return;
   }	
   if(contentTopic.indexOf('/')!=-1 || contentTopic.indexOf("\\")!=-1)
   {
      String Mu_msg1=MultilingualUtil.ConvertedString("uploadAction_msg1",LangFile);
      context.put("errorMess",Mu_msg1);
      return;
   }
   context.put("errorMess","");
   String dateOfCreation=(new java.util.Date()).toString();
   String dateOfModification="";
   String coursesRealPath=TurbineServlet.getRealPath("/Courses");
   String instituteid=user.getTemp("Institute_id").toString();
   String path1 = data.getServletContext().getRealPath("/WEB-INF")+"/conf"+"/InstituteProfileDir/"+instituteid+"Admin.properties";
   //Get the Maximum File size parameter from Institute Admin's propeties file and use it to define the size of Array below.
   String Max_File_upld_no = AdminProperties.getValue(path1,"brihaspati.user.maxFileUploadSize.value");
   int sz = Integer.parseInt(Max_File_upld_no);
   String tempFile[]=new String[sz];
   //FileItem fileItem;
   File f=null;
   File topicDir=new File(coursesRealPath+"/"+courseHome+"/Content/"+contentTopic);
   File tDir=new File(coursesRealPath+"/"+courseHome+"/Content/");
   String Path=coursesRealPath+"/"+courseHome+"/Content/"+contentTopic;
   String way=coursesRealPath+"/"+courseHome+"/Content/";
   String filePath="";
   f=new File(topicDir.getPath()+"/Unpublished/");
   /** Put the check for file storage system get value from configuration file
    *  on the basis set the path of storage area
    **/
   String path=data.getServletContext().getRealPath("/WEB-INF")+"/conf"+"/"+"Admin.properties";
   String dstore = AdminProperties.getValue(path,"brihaspati.admin.datastore.value");
   String hdfsurl = AdminProperties.getValue(path,"brihaspati.admin.hdfsurl.value");
   if(StringUtils.isBlank(dstore))
   {
      dstore="Local";
   }
   /**
    * check guest access for course inside database
    */
   int gid = GroupUtil.getGID(courseHome);
   Criteria crit = new Criteria();
   crit.add(TurbineUserGroupRolePeer.GROUP_ID,gid);
   crit.add(TurbineUserGroupRolePeer.ROLE_ID,3);
   crit.add(TurbineUserGroupRolePeer.USER_ID,0);
   List v=TurbineUserGroupRolePeer.doSelect(crit);	
   //check for available quota space and return true if space available
   long dirS=QuotaUtil.getDirSizeInMegabytes(new File(coursesRealPath+"/"+courseHome));
   //ErrorDumpUtil.ErrorLog(" The dirs is "+ Long.toString(dirS)+ "C Name "+gname+ " C Home "+courseHome );
   boolean check=QuotaUtil.CompareQuotainCourse(dirS,courseHome);
   //ErrorDumpUtil.ErrorLog(" The Compare quota in course is "+check);
   if(check)
   {
      try
      {
         if(!Pub.equals("Publish"))	
	    filePath = f.getPath()+"/";
	 else
	    filePath = topicDir.getPath()+"/";
	 try
	 {
	    File dFile=new File(tDir+"/"+"coursecontent__des.xml");
	    Vector dc=new Vector();
	    boolean flag=false;	
	    if(dFile.exists())//If coursecontent_des.xml file already exists.
            {
              //Create Topic MetaDataXmlReader object to access get File Details.
	       TopicMetaDataXmlReader topicMetaData=new TopicMetaDataXmlReader(way+"/"+"coursecontent__des.xml");
               dc=topicMetaData.getFileDetailsModify();
	       for(int i=0;i<dc.size();i++)
               {
	          String st=((FileEntry) dc.elementAt(i)).getName();
		  String guestaccess=((FileEntry) dc.elementAt(i)).getGuestAccess();	
		  if(st.equals(contentTopic))//check if topic entered is same as already existing one.
                  {
		     flag=true;
		     if(v.size()==0)//If guest permission is enabled.
		        xmlWriter=TopicMetaDataXmlWriter.WriteXml_NewModify(way,"coursecontent",st,"true");
		     else//If guest permission is not enabled.
			xmlWriter=TopicMetaDataXmlWriter.WriteXml_NewModify(way,"coursecontent",st,guestaccess);
                     xmlWriter.writeXmlFile();
		  }
	       }
	       if(!flag)//If topic entered at runtime by user is new .....
               {
                  xmlWriter=TopicMetaDataXmlWriter.WriteXml_NewModify(way,"coursecontent");
		  if(v.size()==0)
	   	     TopicMetaDataXmlWriter.appendFileElementModify(xmlWriter,contentTopic,contentTopic,dateOfCreation,uName,location,"true");
	     	  else
	  	     TopicMetaDataXmlWriter.appendFileElementModify(xmlWriter,contentTopic,contentTopic,dateOfCreation,uName,location,"false");
                  xmlWriter.writeXmlFile();
	       }
	    }
	    else//If coursecontent_des.xml file donot exists...
            {	
	       TopicMetaDataXmlWriter.writeWithRootOnly(dFile.getAbsolutePath());
	       if(contentTopic.length()>0)
               {
                  xmlWriter=TopicMetaDataXmlWriter.WriteXml_NewModify(way,"coursecontent");
	          if(v.size()==0)
                     TopicMetaDataXmlWriter.appendFileElementModify(xmlWriter,contentTopic,contentTopic,dateOfCreation,uName,location,"true");
                  else
                     TopicMetaDataXmlWriter.appendFileElementModify(xmlWriter,contentTopic,contentTopic,dateOfCreation,uName,location,"false");
                  xmlWriter.writeXmlFile();
               }
            }
	 }//try
	 catch(FileUploadException ex)
	 {
	    ErrorDumpUtil.ErrorLog("Error in writing topic in xml"+ex);
	    data.setMessage("See ExceptionLog");
	 }
	 int successfulUploadFilesCount=0;
	 int totalFilesEntries=0;
	 Vector failedFiles=new Vector();
	 String upldmsg="";
         FileItem[] fileItem = pp.getFileItems("uploaded_Files");//Get files to store in fileitem's object.
         int itm_sz= fileItem.length;
	 boolean flag1=false;
         int counter;
         //get upload file size parameter(Max_File_upld_no) from properties file as "sz".
         if (itm_sz <= sz)//Means user has uploaded files less than or equal to max. allowed size.
         {
            counter = itm_sz;
         }
         else//Means user has uploaded files more than max. allowed size.So allow only first "sz" no. of files.
         {
            counter = sz;
         }
	 for(int count=0;count<counter;count++)
	 {
	    boolean fileExists=false;
	    //ErrorDumpUtil.ErrorLog("fitm in uploadaction at line 180=="+fileItem);
	    if(fileItem[count]!=null && fileItem[count].getSize() != 0)
	    {
	       String temp=fileItem[count].getName();
	       if(!temp.equals(contentTopic+"__des.xml"))
               {
	          int index=temp.lastIndexOf("\\");
		  ++totalFilesEntries;
		  fileExists=false;
		  tempFile[count]=temp.substring(index+1);
		  File uploadedFileInUnpub=new File(f,tempFile[count]);
		  File uploadedFileInTopicDir=new File(topicDir,tempFile[count]);
		  if(uploadedFileInUnpub.exists() || uploadedFileInTopicDir.exists() || (temp.indexOf(',')!=-1) )
		  {
		     fileExists=true;
		     failedFiles.addElement(tempFile[count]);
		  }
		  if(fileExists)
		     continue;
		  ++successfulUploadFilesCount;
		  new_files_uploaded.addElement(tempFile[count]);
	  	  //if start data storage on Local disk
		  if((StringUtils.equalsIgnoreCase(dstore,"Local"))||(StringUtils.equalsIgnoreCase(dstore,"Both"))){
		     long fsize=fileItem[count].getSize()/1024/1024;
		     long uquota=QuotaUtil.getCrsQuota(courseHome);
		     uquota= uquota - dirS;
		     long disSpace=QuotaUtil.getFileSystemSpace(instituteId);
		     //ErrorDumpUtil.ErrorLog("The different value of quota parameter in upload course content"+uquota+"and f size "+fsize +"and dspace "+disSpace +"I ID "+instituteId);
		     if((uquota>fsize)&&(disSpace>fsize))
		     {
                        f.mkdirs();
	                flag1=true;
        	        if(flag1)
			{
			   String descfilepath=topicDir+"/"+contentTopic+"__des.xml";
                	   String fospath=filePath+tempFile[count];
                           writeData(descfilepath, fileItem, fospath , count);
        	       	}
	             }
		     else
                     {
		        data.addMessage(MultilingualUtil.ConvertedString("qmgmt_msg5",LangFile));
	             }
		     System.gc();
		  }//if disk storage local
		  if((StringUtils.equalsIgnoreCase(dstore,"HDFS"))||(StringUtils.equalsIgnoreCase(dstore,"Both")))
                  {
		     // write the code here for storing data in hdfs file system
		     // check name node is running is running or not
		     boolean serverOn=false;
		     if(StringUtils.isNotBlank(hdfsurl))
                     {
		        try 
                        {
	                   URL myURL = new URL(hdfsurl);
			   HttpURLConnection connection = (HttpURLConnection)myURL.openConnection();
        		   connection.setDoOutput(true);
			   connection.setRequestMethod("POST");
                           connection.connect();
			   if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) 
                           {
                              serverOn=true;
                           }
		        }
			catch (MalformedURLException e) 
                        {
                	   data.setMessage("The problem in connecting to server "+e);
			}
                        catch (IOException e) 
                        {
			   data.setMessage("The problem in connecting to server (IO exception) "+e);
                        }
			// then write the name of file in xml file
			if(serverOn)
                        {
			   // set the location of the file
			   if(StringUtils.equalsIgnoreCase(dstore,"HDFS"))
                           {
			      f.mkdirs();
			      String descfilepath=topicDir+"/"+contentTopic+"__des.xml";
	                      String fospath=filePath+tempFile[count];
                              writeData(descfilepath, fileItem, fospath , count);
			   }
			   HDFSClient.mkdir(filePath);
			   HDFSClient.addFile(filePath+temp, filePath);
			   if(StringUtils.equalsIgnoreCase(dstore,"HDFS"))
                           {
			      (new File(filePath+temp)).delete();
			   }
			}
                	else{
			   data.setMessage("The problem in connecting to server due to either network failure or server/service down");
	                }
	             }//if url is not blank
		     else
                     {
		        ErrorDumpUtil.ErrorLog("The hdfs server url is blank so file is not stored on distributed server");
			data.setMessage("The hdfs server url is blank so file is not stored on distributed server");
	             }
	          }//if end data storage on hdfs
               }
	       else
	       {
	          upldmsg=temp+" "+MultilingualUtil.ConvertedString("topicUpload_msg",LangFile);
		     //data.addMessage(upldmsg);
	       }
	    }//fileTiem	
	 }//count
	    String courseName = CourseUtil.getCourseName(courseHome);	
	    String Mail_msg = "";
	    if(flag1)
            {	
	       if(Pub.equals("Publish"))
	       {
	          if(new_files_uploaded.size()!=0)
                  {
                     for(int k=0;k<new_files_uploaded.size();k++)
                     {
                        String fileName=new_files_uploaded.get(k).toString();
                        xmlWriter=TopicMetaDataXmlWriter.WriteXml_New(Path,contentTopic);
                        TopicMetaDataXmlWriter.appendFileElement(xmlWriter,fileName,fileName,dateOfCreation);
                        xmlWriter.writeXmlFile();
			Date d=new Date();
			updateLastModified(courseHome,d);
                     }//for
                  }//if
		  if(sendMail.equals("sendMail")){
		     try{
		        String newText=pp.getString("text1","");
			int roleId[]={2,3};
			int userId[]={uid,0};
			crit = new Criteria();
			crit.add(TurbineUserGroupRolePeer.GROUP_ID,gid);
			crit.addIn(TurbineUserGroupRolePeer.ROLE_ID,roleId);
			crit.andNotIn(TurbineUserGroupRolePeer.USER_ID,userId);
			List v1=TurbineUserGroupRolePeer.doSelect(crit);				
			if(v1.size() >0){
			 
			   for(int i=0; i < v1.size(); i ++) {
			      int usrId =((TurbineUserGroupRole) v1.get(i)).getUserId();
			      crit = new Criteria();
			      crit.add(TurbineUserPeer.USER_ID, usrId);
			      List usrList = TurbineUserPeer.doSelect(crit);
			      String userEmail = ((TurbineUser) usrList.get(0)).getEmail();
			      Mail_msg=  MailNotificationThread.getController().set_Message("\n\nCourse content is uploaded in "+courseName+" taught by "+fullName+" ." +"the Information regarding the upload is as given below ."+"<br>"+newText +"."+"<br>"+fullName, "", "", "", userEmail, "Course content uploaded", "", LangFile);
			   }
			   if(Mail_msg.equals("Success")) {
			      crit = new Criteria();
        	              crit.add(TurbineUserPeer.USER_ID, uid);
                	      List usrList = TurbineUserPeer.doSelect(crit);
	                      String senderEmail = ((TurbineUser) usrList.get(0)).getEmail();
			      Mail_msg=  MailNotificationThread.getController().set_Message("\n\nCourse content is uploaded in "+courseName+" taught by "+fullName+ " . "+"the Information regarding the upload is as given below"+"<br>"+newText+"."+"<br>"+ fullName, "", "", "", senderEmail, "Course content uploaded", "", LangFile);
			      Mail_msg=MultilingualUtil.ConvertedString("mail_msg",LangFile);
			      data.addMessage(Mail_msg);
			   }
			}
	             }
		     catch(Exception e) { ErrorDumpUtil.ErrorLog("Exception in Upload Action class on 411 line "+e.getMessage());}
		  }
	       }//ifpublish
	    }//ifflag1
	    else
	       data.addMessage(MultilingualUtil.ConvertedString("qmgmt_msg2",LangFile));
	    if(successfulUploadFilesCount>0) 
	    {	
	       String userRole = (String) user.getTemp("role");
	       if(userRole.equals("student")){
	          String insEmail = CourseUtil. getCourseInstrEmail(courseHome);
		  Mail_msg=  MailNotificationThread.getController().set_Message("Course content is uploaded by student named "+fullName +"in "+courseName+".", "", "", "", insEmail, "Course content is uploaded by student named "+fullName, "", LangFile);
	       }
	       if(successfulUploadFilesCount==totalFilesEntries)
				{
				// all the entries given were uploaded successfully
				context.put("uploadStatus","full");	
				}
				else
				{
				// some of the entries given were uploaded successfully
				context.put("uploadStatus","partial");	
				context.put("failedFiles",failedFiles);
		
				}
			}
			else
			{	
			// nothing was uploaded
			context.put("uploadStatus","nothing");	
			context.put("totalFilesEntries",(new TotalFileCount(totalFilesEntries) ) );
			context.put("failedFiles",failedFiles);
			}
			/*}//ifflag1
			else
			data.addMessage(MultilingualUtil.ConvertedString("qmgmt_msg2",LangFile));*/
			if(StringUtils.isNotBlank(upldmsg)){
				context.put("tmpupload","uploadXmlMsg");		
				context.put("XmlMsg",upldmsg);
			}
			//Maintain Log
			String loginName = user.getName();
                        String strInstId =  (String)user.getTemp("Institute_id","");
                        String instName=InstituteIdUtil.getIstName(Integer.parseInt(strInstId));
                        String gName=data.getUser().getTemp("course_id").toString();
                        log.info("Course content has been uploaded by --> "+loginName +" | Institute Name -->"+instName +" | Course Name -->"+gName + " | IP Address --> "+data.getRemoteAddr());

		}//try
		catch(FileUploadException ex)
		{
			data.addMessage("The Error in Upload a file"+ex);
		}
	 }//if 
         else{
			data.setMessage(MultilingualUtil.ConvertedString("qmgmt_msg3",LangFile));
	}
	}//try
	catch(Exception ex)
	{
		data.addMessage("The Error in Uploading in Course contents !!"+ex);
	}
}//do
		/**
                 * This method is responsble for wrting data to local disk location
                 * @param descfilepath String this is XML descriptor file path
                 * @param fileItem FileItem  class which allows to get input stream
                 * @param fospath String This is content data path, where we want to write data
                 * @return boolean return true or false
                 */
	
	 public boolean writeData(String descfilepath, FileItem[] fileItem, String fospath,int count){
                        boolean wd=false;
                try{
                        File descFile= new File(descfilepath);
                        if(!descFile.exists())
                        TopicMetaDataXmlWriter.writeWithRootOnly(descFile.getAbsolutePath());
                        int readCount;
                        InputStream is=fileItem[count].getInputStream();
                        FileOutputStream fos=new FileOutputStream(fospath);
                        byte[] buf=new byte[4*1024];
                        while((readCount=is.read(buf)) !=-1)
                        {
                                fos.write(buf,0,readCount);
                        }
                        fos.close();
                        is.close();
                        wd=true;
                }
                catch(Exception ex){ErrorDumpUtil.ErrorLog(" The error in writing file data "+ex);wd=false;}
                return wd;
        }

//////////////////////////////////////////////////////////////////////
	public void updateLastModified(String courseId,Date lastMod) throws Exception
        {
		//ErrorDumpUtil.ErrorLog("tttttttttt======update last modified");
                Criteria crit=new Criteria();
                crit.add(CoursesPeer.GROUP_NAME,courseId);
                crit.add(CoursesPeer.LASTMODIFIED,lastMod);
                CoursesPeer.doUpdate(crit);
        }

/////////////////////////////////////////////////////////////////////
	public void topicSequence(String way,String contentTopic,String dateOfCreation,String uName,String location){
		try
                {
			XmlWriter xmlWriter=null;
                        //File dFile=new File(way+"/"+"content__des.xml");
                        File dFile=new File(way+"/"+"coursecontent__des.xml");
                        Vector dc=new Vector();
                        boolean flag=false;
			try{
                        if(dFile.exists()){
                                //TopicMetaDataXmlReader topicMetaData=new TopicMetaDataXmlReader(way+"/"+"content__des.xml");
                                TopicMetaDataXmlReader topicMetaData=new TopicMetaDataXmlReader(way+"/"+"coursecontent__des.xml");
                                dc=topicMetaData.getFileDetailsModify();
                                for(int i=0;i<dc.size();i++){
                                        String st=((FileEntry) dc.elementAt(i)).getName();
                                        if(st.equals(contentTopic)){
                                                flag=true;
                                        }
                                }
                                if(!flag){
                                        //xmlWriter=TopicMetaDataXmlWriter.WriteXml_NewModify(way,"content");
                                        xmlWriter=TopicMetaDataXmlWriter.WriteXml_NewModify(way,"coursecontent");
	                                TopicMetaDataXmlWriter.appendFileElementModify(xmlWriter,contentTopic,contentTopic,dateOfCreation,uName,location,"true");
                                        xmlWriter.writeXmlFile();
                                }
                        }
                        else{
                                TopicMetaDataXmlWriter.writeWithRootOnly(dFile.getAbsolutePath());
                                if(contentTopic.length()>0){
                                        //xmlWriter=TopicMetaDataXmlWriter.WriteXml_NewModify(way,"content");
                                        xmlWriter=TopicMetaDataXmlWriter.WriteXml_NewModify(way,"coursecontent");
	                                TopicMetaDataXmlWriter.appendFileElementModify(xmlWriter,contentTopic,contentTopic,dateOfCreation,uName,location,"true");
                                        xmlWriter.writeXmlFile();
                                }
                        }
                        }//try
                        //xmlWriter.writeXmlFile();
			catch(FileUploadException e)
			{
			ErrorDumpUtil.ErrorLog("Error in writing topic in xml in topic sequence method"+e);
			System.out.println("See ExceptionLog");
                        //data.setMessage("See ExceptionLog");
			}
                }//try
                catch(Exception ex)
                {
                        ErrorDumpUtil.ErrorLog("Error in writing topic in xml in action topic sequence method"+ex);
			System.out.println("See ExceptionLog");
                      //  data.setMessage("See ExceptionLog");
                }

	}

    /**
     * Default action to perform if the specified action
     * cannot be executed
     * @param data RunData
     * @param context Context
     */
    public void doPerform( RunData data,Context context )
    {
	try{
		User user=data.getUser();
		String LangFile=(String)user.getTemp("LangFile"); 
		String actionName=data.getParameters().getString("actionName","");
//		ErrorDumpUtil.ErrorLog("acname in upload action==========="+actionName);
		context.put("actionName",actionName);
		if(actionName.equals("eventSubmit_doUpload"))
		{
			doUpload(data,context);
		}
		else{
        		data.setMessage(MultilingualUtil.ConvertedString("usr_prof2",LangFile));
		
		}
	}
	catch(Exception ex)
	{
		data.setMessage("The Error in Default method !!"+ex);
	}
    }
}
