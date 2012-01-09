package org.iitk.brihaspati.modules.actions;

/*
 * @(#) EditContent_Action.java	
 *
 *  Copyright (c) 2005-2006,2010 ETRG,IIT Kanpur. 
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

import java.util.List;
import java.io.File;
import java.io.IOException;
import java.util.Vector;					
import java.util.StringTokenizer;
import org.apache.velocity.context.Context;
import org.apache.turbine.util.RunData;
import org.apache.turbine.util.parser.ParameterParser;
import org.apache.turbine.om.security.User;
import org.iitk.brihaspati.modules.utils.XmlWriter;
import org.iitk.brihaspati.modules.utils.TopicMetaDataXmlWriter;
import org.iitk.brihaspati.modules.utils.TopicMetaDataXmlReader;
import org.iitk.brihaspati.modules.utils.SystemIndependentUtil;
import org.iitk.brihaspati.modules.utils.StringUtil;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.iitk.brihaspati.modules.utils.CalendarUtil;
import org.apache.turbine.services.servlet.TurbineServlet;
import org.iitk.brihaspati.modules.utils.CourseUtil;
import org.iitk.brihaspati.modules.utils.UserUtil;
import org.iitk.brihaspati.modules.utils.CourseUtil;
import org.iitk.brihaspati.modules.utils.GroupUtil;
import org.iitk.brihaspati.modules.utils.XMLWriter_Cms;
import org.iitk.brihaspati.modules.utils.UserGroupRoleUtil;
import org.iitk.brihaspati.om.InstructorPermissionsPeer;
import org.iitk.brihaspati.om.InstructorPermissions;
import org.iitk.brihaspati.om.CoursesPeer;
import org.iitk.brihaspati.om.Courses;
import org.iitk.brihaspati.om.TurbineUserPeer;
import org.iitk.brihaspati.om.TurbineUser;
import org.iitk.brihaspati.om.InstructorPermissionsPeer;
import org.iitk.brihaspati.om.InstructorPermissions;
import org.apache.torque.util.Criteria;
import org.iitk.brihaspati.modules.utils.MultilingualUtil;


/**
 * This Class responsible for all type editing after publish contents file then write and
 * save in xml format file   
 *
 * @author <a href="mailto:awadhesh_trivedi@yahoo.co.in">Awadhesh Kumar Trivedi</a>
 * @author <a href="mailto:seema_020504@yahoo.com">Seema Pal</a>
 * @author <a href="mailto:kshuklak@rediffmail.com">Kishore Kumar Shukla</a>
 * @author <a href="mailto:manav_cv@yahoo.co.in">Manvendra Baghel</a>
 * @author <a href="mailto:singh_jaivir@rediffmail.com">Jaivir Singh</a>
 * @author <a href="mailto:nksinghiitk@gmail.com">Nagendra Kuamr Singh</a>
 * @author <a href="mailto:parasharirajeev@gmail.com">Rajeev Parashari</a>
 */

public class EditContent_Action extends SecureAction
{
	/**
	* This method responsible for find path 
	* @param data RunData
	* @param context Context
	*/
	public String CoursePath(RunData data,Context context)
	{
		String Path="";
		try
		{
			User user=data.getUser();
			//Course course=data.getCourse();
                        ParameterParser pp=data.getParameters();
                        String topic=pp.getString("topic","");
                        String dir=(String)user.getTemp("course_id");
                        String cName=pp.getString("cName","");
			String status=pp.getString("status","");
			//String status=pp.getString("location","");
			//ErrorDumpUtil.ErrorLog("locatior in crspath===="+status);
                        String username=pp.getString("uname");
			if(status.equals("Remote"))
                        {
                                Path=data.getServletContext().getRealPath("/Courses")+"/"+dir+"/Content/Remotecourse/"+username+"/"+cName+"/"+topic;
                        }

			else if(status.equals("Repo"))
			{
                        	Path=data.getServletContext().getRealPath("/Courses")+"/"+dir+"/Content/Permission/"+username+"/"+topic;
			}
			else
			{
                        	Path=data.getServletContext().getRealPath("/Courses")+"/"+dir+"/Content/"+topic;
			}
		}//try
		catch(Exception e)
		{
			data.setMessage("The error in find Path !! "+e);
		}
		return (Path);
	}

	/**
	* This method responsible for delete a Topic
	* @param data RunData
	* @param context Context
	*/
	public void doDeleteTopic(RunData data,Context context) 
	{
		try
		{
			ParameterParser pp=data.getParameters();
			User user=data.getUser();
			String username=user.getName();
                        String dir=(String)user.getTemp("course_id");
                        String topic=pp.getString("topic","");
                        String cName=pp.getString("cName","");
			String status=pp.getString("status","");
                        String username1=pp.getString("uname");
			String filePath=CoursePath(data,context);
			File f=new File(filePath);
			SystemIndependentUtil.deleteFile(f);
			data.setScreenTemplate("call,CourseMgmt_User,CourseContent.vm");
			if(status.equals("Remote"))
                        {
                                RemoteDelTopic(dir,cName,topic,username1);
			}	
			else if(status.equals("Repo"))
			{
				/**
				*Get the path for delete the particular topic from the xml file
				*/
				String Path=data.getServletContext().getRealPath("/Courses")+"/"+dir+"/Content/Permission";
				String PathR=data.getServletContext().getRealPath("/Repository")+"/"+username1;
				String xmlFile= "/permissionReceive__des.xml";
				String xmlFile1 = "/permissiongiven__des.xml";
				Vector Readrec=new Vector();
				Vector Readgiven=new Vector();
				/**
				*Here we read the  permissionReceive xml
				*and compare with the topicname,username,course_id which we want to delete
				*@see SystemIndependentUtil in utils
				*/
				String match=topic+username1+dir;
				Readrec=SystemIndependentUtil.DeleteXmlEntry(Path,xmlFile,match,username1);
				/**
				*Here we read the  permissiongiven xml
				*and compare with the topicname,username,coursename which we want to delete
				*@see SystemIndependentUtil in utils
				*/
				Readgiven=SystemIndependentUtil.DeleteXmlEntry(PathR,xmlFile1,match,username1);
				/**
				*Here we deleting the author dir in which permission given
				*when it empty
				*/
				SystemIndependentUtil.deleteEmptDir(Path, username1);
				/**
                                * if this is last record in xml file delete the file
                                */
                                if(Readgiven.size()==1)
                                {
                                	File filepath = new File(PathR+"/permissiongiven__des.xml");
                                	filepath.delete();
                               	} //inner if
				
			}//outer if
			XmlWriter xmlwriter=null;
			String BPath=data.getServletContext().getRealPath("/Courses")+"/"+dir+"/Content";
			//File f1=new File(BPath+"/"+"content__des.xml");
			File f1=new File(BPath+"/"+"coursecontent__des.xml");
			 //int seq=CalendarUtil.getSequence(BPath,"content",topic);
			 int seq=CalendarUtil.getSequence(BPath,"coursecontent",topic);
                                xmlwriter=TopicMetaDataXmlWriter.WriteXml_NewModify(BPath,"coursecontent");
                                xmlwriter.writeXmlFile();
                                TopicMetaDataXmlReader topicMetaData=new TopicMetaDataXmlReader(BPath+"/"+"coursecontent__des.xml");
                                Vector dc=topicMetaData.getFileDetailsModify();
                                if(dc.size()>1)
                                {
                                        xmlwriter.removeElement("File",seq);
                                        xmlwriter.writeXmlFile();
                                }
                                else{
                                        SystemIndependentUtil.deleteFile(f1);
                                }

		}//try
		catch(Exception ex)
		{
			data.addMessage("The error Exception in Topic Deletion !! "+ ex);
		}
	}

	/**
	* This method responsible for update description
	* @param data RunData
	* @param context Context
	*/

	public void doUpdate(RunData data,Context context) 
	{
		ParameterParser pp=data.getParameters();
		String topicDescription=pp.getString("description","");
                String topic=pp.getString("topic","");

		topicDescription=StringUtil.replaceXmlSpecialCharacters(topicDescription);

		String filePath=CoursePath(data,context);
		try 
		{		
			XmlWriter xmlWriter=TopicMetaDataXmlWriter.WriteXml_New(filePath,topic);
			xmlWriter.changeData("Desc",topicDescription,0);
			xmlWriter.writeXmlFile();
		}
		catch(Exception e)
		{
			data.setMessage("The error Exception in Change Description !! "+ e); 
		}
	}
	/**
	* This method responsible for delete files
	* @param data RunData
	* @param context Context
	*/
	public void doDelete(RunData data,Context context) 
	{
		User user=data.getUser();
		ParameterParser pp=data.getParameters();		
                String status=pp.getString("status","");
                String username1=pp.getString("uname");
                String dir=(String)user.getTemp("course_id");
		Vector Read=new Vector();
		Vector ReadAcces=new Vector();
		Vector ReadUnp=new Vector();
                String topic=pp.getString("topic","");
		String cName=pp.getString("cName","");
		String deleteList=pp.getString("deleteFileNames","");
		StringTokenizer st=new StringTokenizer(deleteList,"^");
		String fileToDelete[]=new String[st.countTokens()];
		for(int i=0;st.hasMoreTokens();i++)
		{
			fileToDelete[i]=st.nextToken();
		}
		String filePath=CoursePath(data,context);
		XmlWriter xmlWriter;
		int fileseqno[]=new int[fileToDelete.length];
		try{
			for(int i=0,j=1;i<fileToDelete.length;i++)
			{	
				char replaceWith='.';
				String fileName=fileToDelete[i].replace('$',replaceWith);
				fileseqno[i]=pp.getInt(fileName);
				File file=new File(filePath+"/"+fileName);
				file.delete();
				/**
                                * Delete entry from (published)  .xml file fileName wise
                                */
                                if(status.equals("Remote"))
                                {
                                        RemoteDelFile(dir,cName,topic,username1,fileName,3,data);
                                }
                        }
                        if(status.equals("Remote"))
                        {
                                return;
                        }

			//}
			xmlWriter=TopicMetaDataXmlWriter.WriteXml_New(filePath,topic);
			xmlWriter.removeElement("File",fileseqno);
			xmlWriter.writeXmlFile();
			if(status.equals("Repo"))
			{
				String Path=data.getServletContext().getRealPath("/Courses")+"/"+dir+"/Content/Permission";
                                String PathR=data.getServletContext().getRealPath("/Repository")+"/"+username1;
                                String xmlFile= "/permissionReceive__des.xml";
                                String xmlFile1 = "/permissiongiven__des.xml";
                                String match=topic+username1+dir;
				/**
				*Here we reading the xml files 
				*put the all details in the Vector
				*/
                                TopicMetaDataXmlReader tr=null;
                                tr=new TopicMetaDataXmlReader(filePath+"/"+topic+"__des.xml");
                                Read=tr.getFileDetails();
				tr=new TopicMetaDataXmlReader(filePath+"/Access__des.xml");
                                ReadAcces=tr.getFileDetails();
                                tr=new TopicMetaDataXmlReader(filePath+"/Unpublished"+"/"+topic+"__des.xml");
                                ReadUnp=tr.getFileDetails();
				if((Read==null)&&(ReadAcces==null)&& (ReadUnp==null))
                                {//if inner
					/**
					*Here we checking the Vector value
					*for deleting the whole topic
					*@see SystemIndependentUtil in utils
					*/
                                        File filedel=new File(filePath);
                                        SystemIndependentUtil.deleteFile(filedel);
                                        data.setScreenTemplate("call,CourseMgmt_User,CourseContent.vm");
                                        /**
                                        *Here we read the  permissionReceive xml
                                        *and compare with the topicname,username,coursename which we want to delete
					*@see SystemIndependentUtil in utils
                                        */
                                        Vector Readrec=new Vector();
					Readrec=SystemIndependentUtil.DeleteXmlEntry(Path,xmlFile,match,username1);
                                        /**
                                        *Here we read the  permissiongiven xml
                                        *and compare with the topicname,username,coursename which we want to delete
					*@see SystemIndependentUtil in utils
                                        */
                                        Vector Readgiven=new Vector();
					Readgiven=SystemIndependentUtil.DeleteXmlEntry(PathR,xmlFile1,match,username1);
					/**
                                        *Here we deleting the author dir when permission given is empty
                                        */
					SystemIndependentUtil.deleteEmptDir(Path, username1);
                                        /**
                                        *if this is last record in xml file delete the file
                                        */
                                        if(Readgiven.size()==1)
                                        {
                                                File filepath = new File(PathR+"/permissiongiven__des.xml");
                                                filepath.delete();
                                        }
                                } //inner if
                        } //status if
		}
		catch(Exception e)
		{
			data.setMessage("The error Exception in Deletion !! "+ e);
		}
	
	}
	/**
	* This method responsible for Change the file Order
	* @param data RunData
	* @param context Context
	*/
	public void doChangeOrder(RunData data,Context context) 
	{
		ParameterParser pp=data.getParameters();
                String topic=pp.getString("topic","");
		String topicDescription=pp.getString("description","");
		String dir=(String)data.getUser().getTemp("course_id");
		String filePath=CoursePath(data,context);
		int temp=pp.getInt("actionType");
		String radioButton=pp.getString("orderRadio","");
		char replaceWith='.';
		String fileName=radioButton.replace('$',replaceWith);
		int seqnoFinal=pp.getInt("destinationSeqno"),seqnoInitial=pp.getInt(fileName);
		boolean exchange=false;
		if( temp==1)
			exchange=false;
		else if(temp==2)
			exchange=true;
		if(temp!=-1 && seqnoFinal!=-1)
		{
			try
			{
				XmlWriter xmlWriter=TopicMetaDataXmlWriter.WriteXml_New(filePath,topic);
				xmlWriter.changeSeqNo("File",seqnoInitial,seqnoFinal,exchange);
				xmlWriter.writeXmlFile();
			}
			catch(Exception e)
			{
				data.setMessage("The error Exception in File Change Order !! "+ e); 
			}
		}
	}
	/**
	* This method responsible for Change the file Alias 
	* @param data RunData
	* @param context Context
	*/
	public void doChangeAlias(RunData data,Context context) 
	{
		ParameterParser pp=data.getParameters();		
                String topic=pp.getString("topic","");
		String filePath=CoursePath(data,context);
		XmlWriter xmlWriter;
		try
		{
			xmlWriter=TopicMetaDataXmlWriter.WriteXml_New(filePath,topic);
			int fileCount=xmlWriter.getElementCount("File");
			for(int i=0;i<fileCount;i++)
			{	
				String alias=pp.getString(Integer.toString(i));

				TopicMetaDataXmlWriter.changeFileAlias(xmlWriter,alias,i);		
			}
			xmlWriter.writeXmlFile();
		}catch(Exception e)
		{
			data.setMessage("The error Exception in File Change Alias !! "+ e);
		}
	
	}
	/**
	* This method responsible for Updation and deletion in whole topic 
	* @param data RunData
	* @param context Context
	*/

	public void doUpdateDelete(RunData data,Context context) 
	{
		ParameterParser pp=data.getParameters();		
                String topic=pp.getString("topic","");
		String deleteList=pp.getString("deleteFileNames","");
		String topicDescription=pp.getString("description","");
		StringTokenizer st=new StringTokenizer(deleteList,"^");
		String fileToDelete[]=new String[st.countTokens()];
		for(int i=0;st.hasMoreTokens();i++)
		{
			fileToDelete[i]=st.nextToken();
		}
		String filePath=CoursePath(data,context);
		int fileseqno[]=new int[fileToDelete.length];
		XmlWriter xmlWriter;
		try
		{
		xmlWriter=TopicMetaDataXmlWriter.WriteXml_New(filePath,topic);
		xmlWriter.changeData("Desc",topicDescription,0);
		for(int i=0,j=1;i<fileToDelete.length;i++)
		{	
			char replaceWith='.';
			String fileName=fileToDelete[i].replace('$',replaceWith);
			fileseqno[i]=pp.getInt(fileName);
			File file=new File(filePath+"/"+fileName);
			file.delete();
		}

		xmlWriter.removeElement("File",fileseqno);
		xmlWriter.writeXmlFile();
		}
		catch(Exception e)
		{
			data.setMessage("The error Exception in Updation & Deletion !! "+ e);
		}
	
	}
	public void doChangeTopicOrder(RunData data,Context context) 
	{
		ParameterParser pp=data.getParameters();
                String topic=pp.getString("changeFileNameOrder","");
		String topicDescription=pp.getString("description","");
		String dir=(String)data.getUser().getTemp("course_id");
		String filePath=data.getServletContext().getRealPath("/Courses")+"/"+dir+"/Content/";
		int temp=pp.getInt("actionType");
		String radioButton=pp.getString("orderRadio","");
		char replaceWith='.';
		String fileName=radioButton.replace('$',replaceWith);
		int seqnoFinal=pp.getInt("destinationSeqno"),seqnoInitial=pp.getInt("changeFileSeqnoOrder");
		boolean exchange=false;
		if( temp==1)
			exchange=false;
		else if(temp==2)
			exchange=true;
		if(temp!=-1 && seqnoFinal!=-1)
		{
			try
			{
				XmlWriter xmlWriter=TopicMetaDataXmlWriter.WriteXml_NewModify(filePath,"coursecontent");
				xmlWriter.changeSeqNo("File",seqnoInitial,seqnoFinal,exchange);
				xmlWriter.writeXmlFile();
			}
			catch(Exception e)
			{
				data.setMessage("The error Exception in File Change Order !! "+ e); 
			}
		}
	}
	/**
	* This method responsible for update the value 
	* into the database regarding course
        * @param data RunData
        * @param context Context
	*/
	public void doSelect(RunData data,Context context)
	{
		ParameterParser pp=data.getParameters();
		User user = data.getUser();
                String LangFile=data.getUser().getTemp("LangFile").toString();
                String msg="";
		String courseid=((String)user.getTemp("course_id"));
		String midsem=pp.getString("midsem","null");
                String quiz=pp.getString("quiz","null");
                String labwork=pp.getString("labwork","null");
                String endsem=pp.getString("endsem","null");
                String message=pp.getString("message","null");
		String labinst=pp.getString("labinst","null");
		String labinst1=pp.getString("labinst1","null");
		String tute=pp.getString("tute","null");
		String tute1=pp.getString("tute1","null");
		
		String t=pp.getString("t","null");
		String t1=pp.getString("t1","null");
		String t2=pp.getString("t2","null");
		String t3=pp.getString("t3","null");
		String t4=pp.getString("t4","null");
		String t5=pp.getString("t5","null");
		
		String sch=pp.getString("sch","null");
		String sch1=pp.getString("sch1","null");
		String sch2=pp.getString("sch2","null");
		String sch3=pp.getString("sch3","null");
                String sch4=pp.getString("sch4","null");
		String sch5=pp.getString("sch5","null");
		org.apache.commons.fileupload.FileItem fileItem;
                fileItem = pp.getFileItem("file");
                String fileName=fileItem.getName();
		fileName=fileName.toLowerCase();
		//file upload only .txt, .pdf and .html extension
                if(fileName.endsWith(".txt")||fileName.endsWith(".pdf")||fileName.endsWith(".html"))
                {//if1

			int startIndex=fileName.lastIndexOf(".")+1;
                	String fileExt="."+fileName.substring(startIndex);
			String ffileName=courseid+fileExt;
			//file path where save xml file
			String filePath=data.getServletContext().getRealPath("/Courses")+"/"+(String)data.getUser().getTemp("course_id")+"/coursemgmt";
			File f=new File(filePath);
			if(!f.exists())		
				f.mkdir();
			String message1 =XMLWriter_Cms.searchElement(filePath+"/Coursemgmt.xml",courseid);		
			if(!message1.equals("Successfull")) {
				XMLWriter_Cms.CourseManageMentSystem(filePath+"/Coursemgmt.xml",courseid,sch4,sch5,midsem,quiz,labwork,endsem,message,ffileName,labinst,labinst1,tute,tute1,t,t1,t2,t3,t4,t5,sch,sch1,sch2,sch3);
			}else{
				String ss=XMLWriter_Cms.updateCourseManageMentSystem(filePath+"/Coursemgmt.xml",courseid,sch4,sch5,midsem,quiz,labwork,endsem,message,ffileName,labinst,labinst1,tute,tute1,t,t1,t2,t3,t4,t5,sch,sch1,sch2,sch3);
			}
			try {
                               	File filewrite=new File(filePath+"/"+ffileName);
                               	fileItem.write(filewrite);
              			msg= MultilingualUtil.ConvertedString("cms_msg",LangFile);
                                data.setMessage(msg);
			}catch(Exception e){ErrorDumpUtil.ErrorLog("The Exception in do select method under EditContent_action===="+e);}
		}else {
			msg= MultilingualUtil.ConvertedString("assignment_msg6",LangFile);
                 	data.setMessage(msg);
		}
	}
	/**
	* This is default method,to perform if the specified action cannot be executed 
	* @param data RunData
	* @param context Context
	*/

	public void doPerform(RunData data,Context context)
	{
		String actionToPerform=data.getParameters().getString("actionName","");
		context.put("action",actionToPerform);
			
		if( actionToPerform.equals("eventSubmit_doChangeOrder") )
		{
			doChangeOrder(data,context);
		}
		else if( actionToPerform.equals("eventSubmit_doDelete") )
		{
			doDelete(data,context);
		}
		else if( actionToPerform.equals("eventSubmit_doUpdate") )
		{
			doUpdate(data,context);
		}
		else if( actionToPerform.equals("eventSubmit_doUpdateDelete") )
		{
			doUpdateDelete(data,context);
		}
		else if( actionToPerform.equals("eventSubmit_doChangeAlias") )
		{
			doChangeAlias(data,context);
		}
		else if( actionToPerform.equals("eventSubmit_doDeleteTopic") )
		{
			doDeleteTopic(data,context);
		}
		else if( actionToPerform.equals("eventSubmit_doSelect") )
		{
			doSelect(data,context);
		}
		else if( actionToPerform.equals("eventSubmit_doChangeTopicOrder") )
		{
			doChangeTopicOrder(data,context);
		}
	}
	/**
        * This method responsible for  deletion of  topic when status is Remote
        * @param localCourseId String
        * @param cName String
        * @param topic String
        * @param RemoteIp String
        */

        public void RemoteDelTopic(String localCourseId,String cName, String topic,String RemoteIp)
        {
                try{
                        String match=topic + RemoteIp + cName;
			String RemotePath=TurbineServlet.getServletContext().getRealPath("/Courses")+"/"+ localCourseId +"/Content/Remotecourse/";
                        Vector Readrec=new Vector();
                        String xmlFile = "/RemoteCourse__des.xml";
                        /**
                        * Check if /RemoteCourse__des.xml exists
                        */
                        if(!(new File(RemotePath + xmlFile )).exists())
                        {
                                return;
                        }
                        Readrec=SystemIndependentUtil.DeleteXmlEntry(RemotePath,xmlFile,match,RemoteIp);
			/**
                        *Here we  are deleting the  topic directory
                        */
                        File filepathtp = new File(RemotePath + "/" + RemoteIp +"/"+cName+"/"+topic);
                        SystemIndependentUtil.deleteFile(filepathtp);
                        if(Readrec.size()== 1)
                        {
				/**
                                *Here we  are deleting the  Remotecourse directory when RemoteCourse__des.xml is  empty
                                */
                                File filepath = new File(RemotePath);
                                SystemIndependentUtil.deleteFile(filepath);
                        }
                }//try
                catch(Exception e)
                {
                        ErrorDumpUtil.ErrorLog("Exception in action[EditContent_Action.java]in method[RemoteDelTopic]is"+e);
                }
        }//RemoteDelTopic
	/**
        * This method responsible for  deletion of  file when status is Remote
        * @param localCourseId String
        * @param cName String
        * @param topic String
        * @param RemoteIp String
        * @param fileName String
        * @param xmlFile int  1=unpublished 2=accesible 3=visible
        * @param data RunData
        */

        public void RemoteDelFile(String localCourseId,String cName, String topic,String RemoteIp,String fileName,int xmlFile,RunData data)
        {
                try{
                                /**
                                * See if subtopic file is empty or not
                                * if yes then delete subtopic
                                */

                                String filePath =TurbineServlet.getServletContext().getRealPath("/Courses")+"/"+ localCourseId +"/Content/Remotecourse/"+ RemoteIp +"/"+cName + "/"+ topic;
                                //ReadPath is xmlFile Name to read value will be /Access__des.xml etc.
                                String ReadPath =null;
                                /**
                                * AddPath is path to be added after filePath to get to actual directory
                                * its value will be "" or "/Unpublished"
                                */
                                String AddPath ="";
                                Vector Read=null;
                                Vector ReadAcces=null;
                                Vector ReadUnp=null;
                                Vector Readrec=null;

                                TopicMetaDataXmlReader tr=null;
				String match=topic + RemoteIp + cName;

                                /**
                                * Check if all xml files are created
                                */
                                File visible = new File(filePath+"/"+topic+"__des.xml");
                                File accessible = new File(filePath+"/Access__des.xml");
                                File unpublished = new File(filePath+"/Unpublished"+"/"+topic+"__des.xml");
                                boolean bvisible = visible.exists();
                                boolean baccessible = accessible.exists();
                                boolean bunpublished = unpublished.exists();


                                switch(xmlFile)
                                {
                                        case 1:
                                                        if(bvisible)
                                                        {
                                                                tr=new TopicMetaDataXmlReader(filePath+"/"+topic+"__des.xml");
                                                                Read=tr.getFileDetails();
                                                        }
                                                        if(baccessible)
                                                        {
                                                                tr=new TopicMetaDataXmlReader(filePath+"/Access__des.xml");
                                                                ReadAcces=tr.getFileDetails();
                                                        }
                                                        tr=new TopicMetaDataXmlReader(filePath+"/Unpublished"+"/"+topic+"__des.xml");
                                                        Readrec=tr.getFileDetails();
                                                        ReadPath = "/"+topic+"__des.xml";
                                                        AddPath = "/Unpublished";
                                                        if(Read == null && bvisible)
                                                        {
                                                                visible.delete();
                                                        }
							  if(ReadAcces == null && baccessible)
                                                        {
                                                                accessible.delete();
                                                        }
                                                        break;
                                        case 2:
                                                        if(bvisible)
                                                        {
                                                                tr=new TopicMetaDataXmlReader(filePath+"/"+topic+"__des.xml");
                                                                Read=tr.getFileDetails();
                                                        }
                                                        tr=new TopicMetaDataXmlReader(filePath+"/Access__des.xml");
                                                        Readrec=tr.getFileDetails();
                                                        ReadPath = "/Access__des.xml";
                                                        if(bunpublished)
                                                        {
                                                                tr=new TopicMetaDataXmlReader(filePath+"/Unpublished"+"/"+topic+"__des.xml");
                                                                ReadUnp=tr.getFileDetails();
                                                        }
                                                        if(Read == null && bvisible)
                                                        {
                                                                visible.delete();
                                                        }
                                                        if(ReadUnp == null && bunpublished)
                                                        {
                                                                unpublished.delete();
                                                        }
                                                        break;

					 case 3:
                                                        tr=new TopicMetaDataXmlReader(filePath+"/"+topic+"__des.xml");
                                                        Readrec=tr.getFileDetails();
                                                        ReadPath = "/"+topic+"__des.xml";
                                                        if(baccessible)
                                                        {
                                                                tr=new TopicMetaDataXmlReader(filePath+"/Access__des.xml");
                                                                ReadAcces=tr.getFileDetails();
                                                        }
                                                        if(bunpublished)
                                                        {
                                                                tr=new TopicMetaDataXmlReader(filePath+"/Unpublished"+"/"+topic+"__des.xml");
                                                                ReadUnp=tr.getFileDetails();
                                                        }
                                                        if(ReadAcces == null && baccessible)
                                                        {
                                                                accessible.delete();
                                                        }
                                                        if(ReadUnp == null && bunpublished)
                                                        {
                                                                unpublished.delete();
                                                        }

                                }//switch


                                if(Readrec != null)
                                {
                                        PublishAction PA = new PublishAction();
                                        PA.fileName = fileName;
                                        Readrec=PA.Readxmlfile(filePath+AddPath,ReadPath);

                                        if(Readrec == null)
                                        {
                                                File filepath11 = new File(filePath+AddPath+ReadPath);
                                                filepath11.delete();
					 }
                                }

                                if((Read==null)&&(ReadAcces==null)&& (ReadUnp==null)&&(Readrec==null))
                                {
                                        /**
                                        * if this is last record in all inner (published) xml file delete in the innerxml file
                                        */
                                        RemoteDelTopic(localCourseId,cName,topic,RemoteIp);
                                        data.setScreenTemplate("call,CourseMgmt_User,CourseContent.vm");

                                }//if


                }//try
                catch(Exception e)
                {
                         data.addMessage("Exception in action[EditContent_Action.java]in method[RemoteDelFile]is"+e);
                }
        }//RemoteDelFile
}

