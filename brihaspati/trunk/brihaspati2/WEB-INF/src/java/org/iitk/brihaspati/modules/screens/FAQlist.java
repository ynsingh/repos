package org.iitk.brihaspati.modules.screens;

/*
 * @(#)FAQlist.java
 *
 *  Copyright (c) 2010, 2012 ETRG,IIT Kanpur.
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

/**
 *This class contains code for FAQlist 
 *@author: <a href="mailto:seema_020504@yahoo.com">Seemapal</a>
 *@author: <a href="mailto:kshuklak@rediffmail.com">Kishore Kumar shukla</a>
 *@author: <a href="mailto:tpthshobhi30@gmail.com">Shobhika</a>
 * @author <a href="mailto:shaistashekh@hotmail.com">Shaista</a>
 * @ modified date: 05-Aug-2012 (Shaista)

 */

import org.apache.turbine.util.RunData;
import org.apache.turbine.om.security.User;
import org.apache.turbine.util.parser.ParameterParser;
import org.apache.turbine.modules.screens.VelocityScreen;
import org.apache.turbine.services.servlet.TurbineServlet;
import org.apache.turbine.services.security.torque.om.TurbineUserGroupRolePeer;
import org.apache.turbine.services.security.torque.om.TurbineUserGroupRole;

import org.apache.torque.util.Criteria;
import org.apache.velocity.context.Context;

import org.iitk.brihaspati.modules.utils.UserUtil;
import org.iitk.brihaspati.modules.utils.FAQDetail;
import org.iitk.brihaspati.modules.utils.FileEntry;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.iitk.brihaspati.modules.utils.GroupUtil;
import org.iitk.brihaspati.modules.utils.ListManagement;
import org.iitk.brihaspati.modules.utils.InstituteIdUtil;
import org.iitk.brihaspati.modules.utils.CommonUtility;
import org.iitk.brihaspati.modules.utils.AdminProperties;
import org.iitk.brihaspati.modules.utils.TopicMetaDataXmlReader;
import org.iitk.brihaspati.modules.utils.TopicMetaDataXmlReader;
import org.iitk.brihaspati.modules.utils.MailNotificationThread;
import org.iitk.brihaspati.modules.utils.DbDetail;
import org.iitk.brihaspati.modules.utils.AssignmentDetail;
import org.iitk.brihaspati.modules.utils.MultilingualUtil;
import org.iitk.brihaspati.modules.utils.NotInclude;


import org.iitk.brihaspati.om.FaqPeer;
import org.iitk.brihaspati.om.Faq;
import org.iitk.brihaspati.om.FaqmovePeer;
import org.iitk.brihaspati.om.Faqmove;
import org.iitk.brihaspati.om.DbSendPeer;
import org.iitk.brihaspati.om.DbSend;

import java.util.StringTokenizer;
import java.util.Vector;
import java.util.List;
import java.util.Date;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;

public class FAQlist extends VelocityScreen
{
	public void doBuildTemplate(RunData data, Context context)
	{
		try
		{
			
                        ParameterParser pp=data.getParameters();

			/**
                        *Retrieve the parameters by using the ParameterParser
                        *Putting the parameters context for use in templates.
                        */
			String lang=pp.getString("lang","english");
                        context.put("lang",lang);
			String category=pp.getString("category","");
			context.put("category",category);
			String userid1=pp.getString("userid1","");
			context.put("userid1",userid1);
			String mode=pp.getString("mode","");
			context.put("mode",mode);
			String quesid=pp.getString("quesid","");
			context.put("quesid",quesid);
			String status=pp.getString("status","");
			String type=pp.getString("type","");
			//context.put("type",type);
			String modefaq=pp.getString("modefaq","");
			context.put("modefaq",modefaq);
			String group ="", dir = "",topicDesc="";
			group=dir=pp.getString("course_id","");
			if(group.equals("fromFAQ"))
				type = group;
			context.put("type",type);
			//String DB_subject=pp.getString("DB_subject","");
			User user=data.getUser();
			/**
			*Getting the list from the Database
                        *put in the context for the use in templates
			*/
			Vector entry=new Vector();
			Vector queslist=new Vector();
			Vector gidlist=new Vector();
			Vector gidlist1=new Vector();
			Vector allAdminuname=new Vector();
			String username="",filePath="";
			int uid=0,gid=0;
			//getting istitute id for the institute admin FAQs
                        username=data.getUser().getName();
			if(type.equals("instadmin"))
			{
				/*getting username and uid*/
			//	User user=data.getUser();
				uid=UserUtil.getUID(username);
                               	gidlist=InstituteIdUtil.getAllInstId(uid);
				for(int m=0;m<gidlist.size();m++){
                                	String instid=(String)gidlist.get(m);
                                	if(!gidlist1.contains(instid))
                                	{
                                        	gidlist1.addElement(instid);
                                	}
                        	}
				/*getting group id for the FAQ*/
			}
			/**
			* this is use for show topic and message in text area
			*/
/**
		        if(mode.equals("showmessage")){
					user=data.getUser();
                                        uid=UserUtil.getUID(username);
                                        Criteria crit2=new Criteria();
                                        crit2.add(FaqmovePeer.USER_ID,uid);
                                        List v=FaqmovePeer.doSelect(crit2);
                                        for(int n=0;n<v.size();n++)
                                        {
						Faqmove element=(Faqmove)v.get(n);
	                                        int instid3=(element.getInstId());
						String subject=pp.getString("subject","");
						String faqpath=data.getServletContext().getRealPath("/UserArea")+"/"+instid3+"/"+subject;	
						File topicDir=new File(faqpath);
		                        	String ContentList1 []= topicDir.list();
						faqpath=data.getServletContext().getRealPath("/UserArea")+"/"+instid3+"/"+subject+"/"+ContentList1[0];
						BufferedReader br=new BufferedReader(new FileReader (faqpath));
						String msg="";
						String str;
		                                while ((str=br.readLine()) != null) {
							msg=msg+str;	
                		                }
						String str1[]=ContentList1[0].split(".txt");
						context.put("subject",str1[0]);
						context.put("message",msg);
                		              	br.close();
					}//for
			}//end showmessage
*/
			if(mode.equals("alllist"))
			{
				/**
				*reading category from database
				* add in vector for use in templates
				*/
				List u=null;
                        	Criteria crit=new Criteria();
				if(type.equals("instadmin"))
				{
					for(int i=0;i<gidlist1.size();i++)
					{
						gid=Integer.parseInt(gidlist.elementAt(i).toString());
						String inst_id="3";
						gid=Integer.parseInt(inst_id+(Integer.toString(gid)));
                        			crit.add(FaqPeer.GROUP_ID,gid);
                        			u=FaqPeer.doSelect(crit);
						if(u.size()!=0)
						{
                        				for(int m=0;m<u.size();m++)
                        				{
                        					Faq element=(Faq)(u.get(m));
                                				String cat_Name=element.getCategory();
								String userid=Integer.toString(element.getUserId());
								FAQDetail faqdetail= new FAQDetail();
                                         			faqdetail.setCategory(cat_Name);
                                                		faqdetail.setUserId(userid);
                                				entry.addElement(faqdetail);
							}
                        			} 
					}
				}
				else
				{
                        		crit.add(FaqPeer.USER_ID,1);
                        		u=FaqPeer.doSelect(crit);
					if(u.size()!=0)
					{
                        			for(int m=0;m<u.size();m++)
                        			{
                        				Faq element=(Faq)(u.get(m));
                                			String cat_Name=element.getCategory();
							String userid=Integer.toString(element.getUserId());
							String Quesid=Integer.toString(element.getQuesId());
							if(Integer.parseInt(Quesid)==0)
							{
								FAQDetail faqdetail= new FAQDetail();
                                                                faqdetail.setCategory(cat_Name);
                                                                faqdetail.setUserId(userid);
                                                                entry.addElement(faqdetail);
							}
                        			} //for
					}
				}
			}//mode allist
			else
			{
				/**
				*Reading xml according to category
				*by TopicMetaDataXmlReader util 
				*/
				File file;
				TopicMetaDataXmlReader topicmetadata;
				if(type.equals("instadmin"))
				{
                        		username=UserUtil.getLoginName(Integer.parseInt(userid1));
                        		filePath=data.getServletContext().getRealPath("/UserArea")+"/"+username+"/FAQ";
					file=new File(filePath+"/"+category+".xml");
					if(file.exists())
					{
						topicmetadata=new TopicMetaDataXmlReader(filePath+"/"+category+".xml");
                        			entry=topicmetadata.getFaqDetails();
					}
				}
				else
				{
					username=UserUtil.getLoginName(Integer.parseInt(userid1));
                        		filePath=data.getServletContext().getRealPath("/UserArea")+"/"+username+"/FAQ";
					file=new File(filePath+"/"+category+".xml");
					if(file.exists())
					{
						topicmetadata=new TopicMetaDataXmlReader(filePath+"/"+category+".xml");
                        			entry=topicmetadata.getFaqDetails();
					}
				}
			}//else
			if(entry==null)
                              return;
                        if(entry.size()!=0)
                        {
                        	status="NoBlank";
				if(!mode.equals("alllist"))
				context.put("entry",entry);
				else{
					String path=TurbineServlet.getRealPath("/WEB-INF")+"/conf"+"/"+"Admin.properties";
                                	String conf =AdminProperties.getValue(path,"brihaspati.admin.listconfiguration.value");
                                	int list_conf=Integer.parseInt(conf);
                                	context.put("userConf",new Integer(list_conf));
                                	context.put("userConf_string",conf);
					Vector vct=CommonUtility.PListing(data ,context ,entry,list_conf);
					context.put("entry",vct);
				}
			}
			else
                        {
                              	status="blank";
                        }
			context.put("status",status);
			/**
			*Reading xml according to category
			*reading the FAQs details from the xml file
			*by TopicMetaDataXmlReader util 
			*/
			if(modefaq.equals("faqcheck"))
			{
				for(int i=0;i<entry.size();i++)
                                {//for2
                                      	String Id =((FileEntry) entry.elementAt(i)).getFaqid();
                                        String QuesId =((FileEntry) entry.elementAt(i)).getquestionid();
                                        String ques=((FileEntry)entry.elementAt(i)).getquestion();
                                        String ans=((FileEntry) entry.elementAt(i)).getAnswer();
                                        String version=((FileEntry) entry.elementAt(i)).getVersion();
                                        if(QuesId.equals(quesid))
                                        {
                                               	context.put("ques",ques);
                                                FileEntry filelist=new FileEntry();
                                                filelist.setquestionid(QuesId);
                                                filelist.setAnswer(ans);
                                                queslist.addElement(filelist);
					}//if
				}//for
				context.put("queslist",queslist);
			}
			/**
			*This is use for show message subject 
			*/
			if(mode.equals("alllist")){
				Criteria crit1 =null;
				//user=data.getUser();
				uid=UserUtil.getUID(username);
///////////////////////////////////////////////////////////////////////
			if(group.equals("fromFAQ")){
				int msg_id=data.getParameters().getInt("msgid");
				String topic=pp.getString("topic","");
				String sender = pp.getString("sender","");
				String permission = pp.getString("permission","");
				context.put("sender",sender);
				context.put("permission",permission);
				context.put("topic",topic);
				context.put("msgid",Integer.toString(msg_id));
				/**
	                         * Getting the actual path where the DB file is stored
	                  	 * @return String
        	                */

	                        filePath=data.getServletContext().getRealPath("/UserArea")+"/"+dir+"/DisBoard";

				/**
                        	* read the file using XML descriptior
	                        * @try and catch Identifies statements that user want to monitor
        	                * and catch for exceptoin.
                	        */
                        	try{//try2
                                	String str[]=new String[1000];
	                                int i=0;
        	                        int start=0;
                	                int stop = 0;
                        	        try{
                                	        BufferedReader br=new BufferedReader(new FileReader (filePath+"/"+topic+"/Msg.txt"));
                        	                while ((str[i]=br.readLine()) != null)
                                	        {
                                        	        if (str[i].equals("<"+msg_id+">"))
                                                	{
                                                        	start = i;
	                                                }
        	                                        else if(str[i].equals("</"+msg_id+">"))
                	                                {
                        	                                stop = i;
                                	                }
                                        	        i= i +1;
	                                        }
        	                                br.close();
                	                }//try3
                        	        catch(Exception e)
                                	{
                                        	data.addMessage("The error in reading message in DBView Screens !!"+e);
	                                        //e.printStackTrace();
        	                        }
                	                for(int j=start+1;j<stop;j++)
                        	        {
                                	        topicDesc=topicDesc+ "\n"+str[j];
	                                }

						/*String Role = (String)user.getTemp("role");
						if((Role.equals("student")) || (Role.equals("instructor")))
                       	        		{
                                        	        //CourseTimeUtil.getCalculation(uid);
                                                	//ModuleTimeUtil.getModuleCalculation(uid);
	                                                MailNotificationThread.getController().CourseTimeSystem(uid);
        	                                }*/
			         }
        	                catch(Exception e) {data.setMessage("The error in DBView Screens !!"+e);}
				/**
                        	* here comes the code to view attachment with the message
	                        **/	
        	                context.put("message",topicDesc);
                	        File dirHandle=new File(filePath+"/"+topic+"/"+"Attachment/"+msg_id);
                        	FilenameFilter exclude=new NotInclude("__desc.txt");
	                        File file[]=dirHandle.listFiles(exclude);
        	                String servContext=TurbineServlet.getContextPath();
                	        Vector content=new Vector();
                        	for(int i=0;i<file.length;i++)
	                        {
        	                        Date date=new Date(file[i].lastModified());
                	                String fileData[]={file[i].getName(),date.toString()};
                        	        content.addElement(fileData);
	                        }	
        	                context.put("dirContent",content);
	
        	                /** 
                	        * From Here comes the code for change in Status.( Unread to Read ) 
                        	* @return nothing
	                        */
				 try
                	        {
                        	        crit1=new Criteria();
                                	crit1.add(DbSendPeer.STATUS,"1");
	                                crit1.add(DbSendPeer.MSG_ID,msg_id);
        	                        List u=DbSendPeer.doSelect(crit1);
                	                for(int count=0;count<u.size();count++)
                        	        {
						DbSend element=(DbSend)(u.get(count));
						String subject=(element.getMsgSubject());
                                	        /**
	                                        * Adds the information to the context
        	                                */
                	                        context.put("sub",subject);
                        			//ErrorDumpUtil.ErrorLog("subject========>"+subject+"\nusername========>"+username+"\nretrive_date"+retrive_date);
	                                }//for
        	                }
                	        catch(Exception e) {data.setMessage("The error in sender name and post date in DBView Screens !!"+e);}
			
			}
			else{
				/**
                                * Select all the messages according to the MessageId
                                * from the Db_Send table
                                */

				crit1 = new Criteria();
                                crit1.addGroupByColumn(FaqmovePeer.MSG_ID);
                                List dbList=FaqmovePeer.doSelect(crit1);
				for(int count1=0;count1<dbList.size();count1++)
                                {//for2 
                                        Faqmove element1=(Faqmove)(dbList.get(count1));
                                        String msgid=Integer.toString(element1.getMsgId());

                                        String message_subject=(element1.getMsgSubject());
					String sender_name = element1.getSenderName();
                                        String permit=Integer.toString(element1.getPermission());

                                        DbDetail dbDetail= new DbDetail();
					dbDetail.setSender(sender_name);
                                        dbDetail.setMSubject(message_subject);
                                        dbDetail.setMsgID(msgid);
                                        dbDetail.setPermission(permit);
                                        entry.addElement(dbDetail);
                                }//for2 
				if(entry.size()!=0)
	                        {
        	                        context.put("status","Noblank");
                	                context.put("assign",entry);
                        	}
	                        else
        	                {
                	                context.put("status","blank");
                        	        String LangFile=(String)data.getUser().getTemp("LangFile");
                                	if(mode.equals("All")){
                                        	String  mssg=MultilingualUtil.ConvertedString("db-Contmsg",LangFile);
	                                        data.setMessage(mssg);
        	                        }
                	                else{
                        	                String  mssg=MultilingualUtil.ConvertedString("db-Contmsg1",LangFile);
                                	        data.setMessage(mssg);
                                	}
	                        }				
			}
/////////////////////////////////////////////////////////////////////////
/*
                                for(int m=0;m<u.size();m++)
                                {
					Faqmove element=(Faqmove)u.get(m);
	                                int instid2=(element.getInstId());
        	                       	String subject=pp.getString("subject","");
					String faqpath1=data.getServletContext().getRealPath("/UserArea")+"/"+instid2;
                	               	File topicDir1=new File(faqpath1);
	                	        String ContentList []=topicDir1.list();
        	                        String faqpath2="";
                	               	Vector FileViewId_tiopic=new Vector();
	                                if(ContentList.length == 0) {
        	                               	String LangFile=(String)data.getUser().getTemp("LangFile");
	        	                        String  mssg=MultilingualUtil.ConvertedString("faq_msg3",LangFile);
        	        	                data.setMessage(mssg);
                	                        return;
                                	}
	                               	for(int s=0;s<=ContentList.length;s++)
        	                       	{
                	                       	AssignmentDetail assignmentdetail=new AssignmentDetail();
						ErrorDumpUtil.ErrorLog("\n\n\nContentList[s]=========FAQ class in screen==="+ContentList[s]);
	                	                faqpath2=faqpath1+"/"+ContentList[s];
        	                	        File topicDir=new File(faqpath2);
                	                        String ContentList1 []= topicDir.list();
						ErrorDumpUtil.ErrorLog("\nContentList1[0]=========FAQ class in screen==="+ContentList1[0]);
                        	               	assignmentdetail.setStudentname(ContentList1[0]);
	                                        assignmentdetail.setAssignmentfile(ContentList[s]);                             
						ErrorDumpUtil.ErrorLog("\nContentList1[s]=========FAQ class in screen==="+ContentList1[s]);
        	                               	FileViewId_tiopic.add(assignmentdetail);
	        	                        context.put("assign",FileViewId_tiopic);
        	        	        }
        	                }
*/
		  	}
		}//try
		catch(Exception e){ data.setMessage("Error in screen [FAQlist] !! " + e); }
	}
}
