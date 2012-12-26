package org.iitk.brihaspati.modules.actions;

/*
 * @(#)FAQ.java 
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
 */

import java.io.File;
import java.util.Vector;
import java.util.List;
import java.sql.Date;
import java.util.ListIterator;
import com.workingdogs.village.Record;
import java.util.StringTokenizer;

import org.apache.torque.util.Criteria;
import org.apache.velocity.context.Context;
import org.apache.turbine.util.RunData;
import org.apache.turbine.om.security.User;
import org.apache.turbine.util.parser.ParameterParser;
import org.apache.turbine.modules.screens.VelocityScreen;
import org.apache.turbine.services.servlet.TurbineServlet;

import org.iitk.brihaspati.modules.utils.MultilingualUtil;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.iitk.brihaspati.modules.utils.UserUtil;
import org.iitk.brihaspati.modules.utils.StringUtil;
import org.iitk.brihaspati.modules.utils.ExpiryUtil;
import org.iitk.brihaspati.modules.utils.AdminProperties;
import org.iitk.brihaspati.modules.utils.TopicMetaDataXmlReader;
import org.iitk.brihaspati.modules.utils.TopicMetaDataXmlWriter;
import org.iitk.brihaspati.modules.utils.XmlWriter;
import org.iitk.brihaspati.modules.utils.FileEntry;
import org.iitk.brihaspati.modules.utils.GroupUtil;


import org.iitk.brihaspati.om.FaqPeer;
import org.iitk.brihaspati.om.Faq;
import org.iitk.brihaspati.om.FaqVote;
import org.iitk.brihaspati.om.FaqVotePeer;

/**This class contain the code for Create, Delete and Update FAQ
*@author: <a href="mailto:seema_020504@yahoo.com">Manorama Pal</a>
*@author: <a href="mailto:kishore.shukla@gmail.com">Kishore kumar shukla</a>
*@author: <a href="mailto:shaistashekh@hotmail.com">Shaista </a>
*@modified date: 22-11-2010
*/


public class FAQ extends SecureAction
{
		/**
		*get path for Faq
		*/
		String FaqPath=TurbineServlet.getRealPath("/UserArea");
		VelocityScreen vs=new VelocityScreen();
		String LangFile ="";
	public void doSaveFaq(RunData data,Context context)
	{
		try
		{
			/**
                  	*Getting Language value from temporary variable
			*getting the parameters by using the ParameterParser
                        *Putting the parameters in context for use in templates.
                        */

			LangFile=data.getUser().getTemp("LangFile").toString();
			ParameterParser pp=data.getParameters();
			User user=data.getUser();
			String username=data.getUser().getName();
			int uid=UserUtil.getUID(username);
			String mode1=pp.getString("mode","");
			context.put("mode",mode1);
			String modetype=pp.getString("modetype","");
			context.put("modetype",modetype);
			String categoryold=pp.getString("category","");
			String category=pp.getString("catname","");
			String inst_id=(String)user.getTemp("Institute_id");
			/* Check for special character*/
			if(StringUtil.checkString(category) != -1)
                        {
                                data.addMessage(MultilingualUtil.ConvertedString("usr_prof1",LangFile));
                                return;
                        }

			String vote="notattempt";
			int roleid=0;
			/*get gid by using roleid*/
			if(username.equals("admin"))
			roleid=1;
			else
			roleid=7;
			String gname=GroupUtil.getGroupName(uid,roleid);
			int gid=GroupUtil.getGID(gname);
			gid=Integer.parseInt((Integer.toString(gid))+inst_id);
			/*Getting currentdate and expdate by using ExpiryUtil*/
			String Cur_date=ExpiryUtil.getCurrentDate("-");
                        Date Post_date=Date.valueOf(Cur_date);
			String path=data.getServletContext().getRealPath("/WEB-INF")+"/conf"+"/"+"Admin.properties";
                        String AdminConf = AdminProperties.getValue(path,"brihaspati.admin.FaqExpiry");
			Date expDate=Date.valueOf(ExpiryUtil.getExpired(Cur_date,new Integer(AdminConf)));
                       	Criteria crit=new Criteria();
			
			/**
			*insert entry(category) related to Faq in the database
			*set message using MultilingualUtil
			*/
			boolean flag=false;
			crit=new Criteria();
                       	crit.add(FaqPeer.USER_ID,uid);
                       	List u=FaqPeer.doSelect(crit);
			if(mode1.equals("add"))
			{
				for(int i=0;i<u.size();i++)
				{
					Faq element=(Faq)u.get(i);
					String categoryname=(element.getCategory());
					if(categoryname.equals(category))
						flag=true;
						//data.setMessage(MultilingualUtil.ConvertedString("brih_category",LangFile)+" "+MultilingualUtil.ConvertedString("brih_is",LangFile)+" "+MultilingualUtil.ConvertedString("Wikiaction6",LangFile));
						data.setMessage(MultilingualUtil.ConvertedString("brih_category",LangFile)+" "+MultilingualUtil.ConvertedString("Wikiaction6",LangFile));
				}
				if(flag==false)
				{
                       			crit=new Criteria();
                        		crit.add(FaqPeer.CATEGORY,category);
                        		crit.add(FaqPeer.USER_ID,uid);
                        		crit.add(FaqPeer.GROUP_ID,gid);
                        		crit.add(FaqPeer.QUES_ID,0);
                        		crit.add(FaqPeer.POST_TIME,Post_date);
                        		crit.add(FaqPeer.EXPIRY_DATE,expDate);
                        		crit.add(FaqPeer.VOTE,vote);
                        		FaqPeer.doInsert(crit);
					context.put("mode","alllist");
					//data.setMessage("Category Add Successfully !!");
				if( LangFile.endsWith("hi.properties") || LangFile.endsWith("urd.properties"))
					data.setMessage(MultilingualUtil.ConvertedString("brih_FAQ",LangFile)+" "+MultilingualUtil.ConvertedString("brih_successfully", LangFile)+" "+MultilingualUtil.ConvertedString("brih_Added", LangFile));
                	        else{
					data.setMessage(MultilingualUtil.ConvertedString("brih_category",LangFile)+" "+MultilingualUtil.ConvertedString("brih_Added",LangFile)+" "+MultilingualUtil.ConvertedString("brih_successfully",LangFile));
				log.info("Category Added successfully in FAQ with name "+category+ " By "+username+" IP Address : "+data.getRemoteAddr());
				}
				}
			}//if
			/**
			*check for edit
			*Read the deatil from table(category,id)
			*and edit the category
			*/
			if(mode1.equals("edit"))
			{
				for(int i=0;i<u.size();i++)
				{
					Faq element=(Faq)u.get(i);
                               		int Faqid=(element.getFaqId());
					String categoryname=(element.getCategory());
					if(categoryname.equals(categoryold))
					{
						crit=new Criteria();
						crit.add(FaqPeer.FAQ_ID,Faqid);
						crit.add(FaqPeer.CATEGORY,category);
                                       		FaqPeer.doUpdate(crit); 
					}
				}
				/**
				*get xml path for edit category 
				*and rename the xml file 
				*/
				String filepath=FaqPath+"/"+username+"/FAQ"+"/"+categoryold+".xml";
				File oldfile=new File(filepath);
				String newpath=FaqPath+"/"+username+"/FAQ"+"/"+category+".xml";
				File newfile=new File(newpath);
				oldfile.renameTo(newfile);
				String mode="alllist";
				context.put("mode1",mode);
				vs.doRedirect(data,"call,FAQ,FAQ_Ques.vm");
				//data.setMessage("Category Edit Successfully !!");
				data.setMessage(MultilingualUtil.ConvertedString("brih_category",LangFile)+" "+MultilingualUtil.ConvertedString("brih_hasbeenedit",LangFile));
				log.info("Category updated successfully "+categoryold+" to "+category+ " By "+username+" IP Address : "+data.getRemoteAddr());
			}
		}
		catch(Exception e){data.setMessage("Error in action:FAQ[SaveFaq] !!"+e);}
	}//method close
	//method for insert question 
	public void doSaveQues(RunData data,Context context)
	{
		try
		{
			/**
                        *Getting Language value from temporary variable
                        *getting the parameters by using the ParameterParser
                        *Putting the parameters in context for use in templates.
                        */

			String LangFile=data.getUser().getTemp("LangFile").toString();
			ParameterParser pp=data.getParameters();
			User user=data.getUser();	
			String role=(String)user.getTemp("role");
			String username=data.getUser().getName();
			int uid=UserUtil.getUID(username);
			String mode=pp.getString("mode","");
			context.put("mode",mode);
			String category=pp.getString("category","");
			String Question=pp.getString("question","");
			Question=StringUtil.replaceXmlSpecialCharacters(Question);
			String Answer=pp.getString("answer","");
			//Answer=StringUtil.replaceXmlSpecialCharacters(Answer);
                       	String courseid=(String)user.getTemp("course_id");
			String gname="";
			/*get gid by using roleid*/
			int roleid=0;
			if(username.equals("admin"))
				roleid=1;
			if(role.equals("instructor"))
				roleid=2;
			if(role.equals("student"))
				roleid=3;
			if(role.equals("institute_admin"))
				roleid=7;
			if(roleid==1||roleid==7)
			gname=GroupUtil.getGroupName(uid,roleid);
			else
			gname=courseid;
			int gid=GroupUtil.getGID(gname);
			String vote="notattempt";
			float version=1.1f;
			/*Getting currentdate and expdate for Faq by using ExpiryUtil*/
			String Cur_date=ExpiryUtil.getCurrentDate("-");
                        Date Post_date=Date.valueOf(Cur_date);
			String path=data.getServletContext().getRealPath("/WEB-INF")+"/conf"+"/"+"Admin.properties";
                        String AdminConf = AdminProperties.getValue(path,"brihaspati.admin.FaqExpiry");
			Date expDate;
			if((username.equals("admin"))||(role.equals("institute_admin")))
			expDate=Date.valueOf(ExpiryUtil.getExpired(Cur_date,new Integer(AdminConf)));
			else
			{
				String expiry=pp.getString("expiry","");
				int exp=Integer.parseInt(expiry);
				expDate=Date.valueOf(ExpiryUtil.getExpired(Cur_date,exp));
			}
			//insert ques in the database.
                        Criteria crit=new Criteria();
                        crit.add(FaqPeer.CATEGORY,category);
                        crit.add(FaqPeer.USER_ID,uid);
                       	crit.add(FaqPeer.GROUP_ID,gid);
                        crit.add(FaqPeer.QUES_ID,0);
                        crit.add(FaqPeer.POST_TIME,Post_date);
                        crit.add(FaqPeer.EXPIRY_DATE,expDate);
                        crit.add(FaqPeer.VOTE,vote);
                        FaqPeer.doInsert(crit);
                        /* Select faq_id from FAQ table*/
			int faq_id=0;
                        String qfaqid="SELECT MAX(FAQ_ID)FROM FAQ";
                        List vfaqid = FaqPeer.executeQuery(qfaqid);
                        /**
                        * getting the faq_id.
                        */
                        for(ListIterator j = vfaqid.listIterator();j.hasNext();)
                        {
                        	Record item = (Record)j.next();
                                faq_id = item.getValue ("MAX(FAQ_ID)").asInt();
                        }
			/*updating the QId field according to the FAQId*/
			crit=new Criteria();
                        crit.add(FaqPeer.USER_ID,uid);
                        List u=FaqPeer.doSelect(crit);
			for(int i=0;i<u.size();i++)
			{
				Faq element=(Faq)u.get(i);
                               	int Faqid=(element.getFaqId());
                               	int quesid=(element.getQuesId());
				if((faq_id==Faqid)&&(quesid==0))
				{
					crit=new Criteria();
					crit.add(FaqPeer.FAQ_ID,Faqid);
					crit.add(FaqPeer.QUES_ID,Faqid);
                                       	FaqPeer.doUpdate(crit); 
				}
			}
			/**
                        *Get the path for creating FAQ dir.
                        */
			String filepath="";
			File f ;
			if((username.equals("admin"))||(role.equals("institute_admin")))
				filepath=FaqPath+"/"+username+"/FAQ";
			else
			{
				filepath=data.getServletContext().getRealPath("/Courses")+"/"+courseid+"/FAQ";
			}
                       	f=new File(filepath);
                        if(!f.exists())
                        f.mkdirs();
			/**
			*reading xml file for the duplicate entry
			*and after checking add new question in the xml file
			*@see TopicMetaDataXmlReader in Util
			*@see TopicMetaDataXmlWriter in Util 
			*/
                       	TopicMetaDataXmlReader topicmetadata=null;
                        XmlWriter xmlWriter=null;
                        boolean found=false;
			File Faqxml= new File(filepath+"/"+category+".xml");
                        if(!Faqxml.exists())
			{
                                TopicMetaDataXmlWriter.writeWithRootOnly(Faqxml.getAbsolutePath());
			}
                        if(!Faqxml.exists())
                        {
                                xmlWriter=new XmlWriter(filepath+"/"+category+".xml");
                        }
                        /**
                        *Checking for  the existing Faq
                        *@see TopicMetaDataXmlReader in Util.
                        */
			else
                        {
                                topicmetadata=new TopicMetaDataXmlReader(filepath+"/"+category+".xml");
                                Vector collect=topicmetadata.getFaqDetails();
                                if(collect!=null)
                                {
                                        for(int i=0;i<collect.size();i++)
                                        {//for
                                                String QuesId =((FileEntry) collect.elementAt(i)).getquestionid();
                                                String ques=((FileEntry) collect.elementAt(i)).getquestion();
                                                String ans=((FileEntry) collect.elementAt(i)).getAnswer();
                                                String Version=((FileEntry) collect.elementAt(i)).getVersion();
                                                if(Question.equals(ques))
                                                        found=true;
                                                        //data.setMessage("This Question is already exists !!");
							data.setMessage(MultilingualUtil.ConvertedString("brih_This",LangFile)+" "+MultilingualUtil.ConvertedString("brih_qus",LangFile)+" "+MultilingualUtil.ConvertedString("Wikiaction6",LangFile));
                                        }//for
                                }//if
                        }//else
			/**
                        *Appending the entry in the xml File
			*@see TopicMetaDataXmlWriter in Util.
                        */
			if(found==false)
                        {
                                xmlWriter=TopicMetaDataXmlWriter.FaqXml(filepath,category+".xml");
                                TopicMetaDataXmlWriter.appendFAQ(xmlWriter,Integer.toString(faq_id),Integer.toString(faq_id),Question,Answer,Float.toString(version));
                                xmlWriter.writeXmlFile();
				//data.setMessage("FAQ Add Successfully !!");
				if( LangFile.endsWith("hi.properties") || LangFile.endsWith("urd.properties"))
					data.setMessage(MultilingualUtil.ConvertedString("brih_FAQ",LangFile)+" "+MultilingualUtil.ConvertedString("brih_successfully", LangFile)+" "+MultilingualUtil.ConvertedString("brih_Added", LangFile));
                	        else
					data.setMessage(MultilingualUtil.ConvertedString("brih_FAQ",LangFile)+" "+MultilingualUtil.ConvertedString("brih_Added",LangFile)+" "+MultilingualUtil.ConvertedString("brih_successfully",LangFile));
                        }//
		}
		catch(Exception e){data.setMessage("Error in action FAQ:[SaveQues] !!"+e);}
	}//method close

	//method for deleting question and category 
	public void doDeleteQues(RunData data,Context context)
	{
		try
		{
			/**
                        *Getting Language value from temporary variable
                        *getting the parameters by using the ParameterParser
                        *Putting the parameters in context for use in templates.
                        */

			String LangFile=data.getUser().getTemp("LangFile").toString();
			ParameterParser pp=data.getParameters();
			User user=data.getUser();
			String username=data.getUser().getName();
			int uid=UserUtil.getUID(username);
			String mode=pp.getString("mode","");
			context.put("mode",mode);
			String category=pp.getString("category","");
			String quesid=pp.getString("quesid","");
			String deltype=pp.getString("deltype","");
			String role=(String)user.getTemp("role");
			Criteria crit=new Criteria();
			String filepath="";
			if((username.equals("admin"))||(role.equals("institute_admin")))
				filepath=FaqPath+"/"+username+"/FAQ";
			else
			{
                        	String courseid=(String)user.getTemp("course_id");
				filepath=data.getServletContext().getRealPath("/Courses")+"/"+courseid+"/FAQ";
			}
			/**
			*Deleting the Question from the xml
			*and deleting Question from the database
			*/ 
			if(!deltype.equals("delcat"))
			{
                        	TopicMetaDataXmlReader topicmetadata=new TopicMetaDataXmlReader(filepath+"/"+category+".xml");
                        	Vector collect=topicmetadata.getFaqDetails();
                        	if(collect!=null)
                        	{
                        		for(int i=0;i<collect.size();i++)
                                	{//for
                                		String QuesId =((FileEntry) collect.elementAt(i)).getquestionid();
                                        	String ques=((FileEntry) collect.elementAt(i)).getquestion();
                                        	String ans=((FileEntry) collect.elementAt(i)).getAnswer();
                                        	if(QuesId.equals(quesid))
						{
							Vector str=DeleteEntry(filepath,category+".xml",quesid,"del",data);
						}
                               		}//for
                      		}//if
				/*Delete question from table */
				crit=new Criteria();
                        	crit.add(FaqPeer.QUES_ID,quesid);
                        	FaqPeer.doDelete(crit);
                      		//data.setMessage("Question deleted successfully !!");
				data.setMessage(MultilingualUtil.ConvertedString("brih_qus",LangFile)+" "+MultilingualUtil.ConvertedString("brih_hasbeendelete",LangFile));
			}//if
			/**
			*Getting the parameters for the deletion
			*Delete category from table 
			*/
			if(deltype.equals("delcat"))
			{
                        	String deleteList=pp.getString("deleteFileNames","");
                        	StringTokenizer st=new StringTokenizer(deleteList,"^");
                        	String fileToDelete[]=new String[st.countTokens()];
                        	for(int i=0;st.hasMoreTokens();i++)
                        	{
                                	fileToDelete[i]=st.nextToken();
                        	}
				/**
                        	* getting the category for deleting
                        	*and also deleting file related to the category.
                        	*/
                        	for(int i=0;i<fileToDelete.length;i++)
                        	{
                                	String catname =fileToDelete[i];
					crit=new Criteria();
                        		crit.add(FaqPeer.CATEGORY,catname);
                        		List u=FaqPeer.doSelect(crit);
					for(int k=0;k<u.size();k++)
					{
						Faq element=(Faq)u.get(k);
                               			int Faqid=(element.getFaqId());
                               			String categoryname=(element.getCategory());
						if(catname.equals(categoryname))
						{
							crit=new Criteria();
                        				crit.add(FaqPeer.FAQ_ID,Faqid);
                        				FaqPeer.doDelete(crit);
						}
					}
					File file=new File(filepath+"/"+catname+".xml");
                                	file.delete();
				}//for
                      		//data.setMessage("Category deleted successfully !!");
				data.setMessage(MultilingualUtil.ConvertedString("brih_category",LangFile)+" "+MultilingualUtil.ConvertedString("brih_hasbeendelete",LangFile));
				log.info("Category deleted successfully By "+username+" IP Address : "+data.getRemoteAddr());
			}//if
		}
		catch(Exception e){data.setMessage("Error in action:FAQ[SaveQues] !!"+e);}
	}//method close

	//method for adding answer 
	public void doAddAnswer(RunData data,Context context)
	{
		try
		{
			/**
                        *Getting Language value from temporary variable
                        *getting the parameters by using the ParameterParser
                        *Putting the parameters in context for use in templates.
                        */
			String LangFile=data.getUser().getTemp("LangFile").toString();
			ParameterParser pp=data.getParameters();
			User user=data.getUser();
			String username=data.getUser().getName();
			int uid=UserUtil.getUID(username);
			String mode=pp.getString("mode","");
			String edittype=pp.getString("edittype","");
			String modeedit=pp.getString("modeedit","");
			context.put("modeedit",modeedit);
			String category=pp.getString("category","");
			String Answer=pp.getString("answer","");
			String role=(String)user.getTemp("role","");
                       	String courseid=(String)user.getTemp("course_id","");
			int roleid=0;
			String vote="notattempt",gname="",quesid="";
                        if(username.equals("admin"))
                                roleid=1;
                        if(role.equals("instructor"))
                                roleid=2;
                        if(role.equals("student"))
                                roleid=3;
                        if(role.equals("institute_admin"))
                                roleid=7;
			 if(roleid==1||roleid==7)
			{
                        gname=GroupUtil.getGroupName(uid,roleid);
			quesid=pp.getString("quesid","");
			}
                        else
			{
                        gname=courseid;
			if(edittype.equals("addedit"))
			quesid=pp.getString("quesid","");
			else
			quesid=pp.getString("faqid","");
			}
                        int gid=GroupUtil.getGID(gname);
			float newversion=0.0f,tmp=0.0f;
			int faqid=0;
			/*Getting currentdate and expdate for Faq by using ExpiryUtil*/
			java.util.Date expDate1=new java.util.Date();
			String Cur_date=ExpiryUtil.getCurrentDate("-");
                        Date Post_date=Date.valueOf(Cur_date);
			/*String path=data.getServletContext().getRealPath("/WEB-INF")+"/conf"+"/"+"Admin.properties";
                        String AdminConf = AdminProperties.getValue(path,"brihaspati.admin.FaqExpiry");
			Date expDate=Date.valueOf(ExpiryUtil.getExpired(Cur_date,new Integer(AdminConf)));*/
			if((!username.equals("admin"))&&(edittype.equals("addedit")))
			{
				/**
				*getting the expdate of the Question
				*for adding the Answer
				*/
                        	Criteria crit=new Criteria();
			 	crit=new Criteria();
                                crit.add(FaqPeer.CATEGORY,category);
                                List u=FaqPeer.doSelect(crit);
                                for(int k=0;k<u.size();k++)
                                {
                                	Faq element=(Faq)u.get(k);
                                        int Faqid=(element.getFaqId());
                                        String categoryname=(element.getCategory());
                                        java.util.Date exp= element.getExpiryDate();
                                        if((category.equals(categoryname))&&(Integer.toString(Faqid).equals(quesid)))
                                        {
						expDate1=exp;
                                        }
				}
				//insert faq ans in the table.
                        	crit=new Criteria();
                        	crit.add(FaqPeer.CATEGORY,category);
                        	crit.add(FaqPeer.USER_ID,uid);
                       		crit.add(FaqPeer.GROUP_ID,gid);
                        	crit.add(FaqPeer.QUES_ID,quesid);
                        	crit.add(FaqPeer.POST_TIME,Post_date);
                        	crit.add(FaqPeer.EXPIRY_DATE,expDate1);
                        	crit.add(FaqPeer.VOTE,vote);
                        	FaqPeer.doInsert(crit);
			}
                       	/*execute query for selecting FAQid from Faq table*/
			String qver="SELECT MAX(FAQ_ID)FROM FAQ";
                       	List v = FaqPeer.executeQuery(qver);
                       	for(ListIterator j = v.listIterator();j.hasNext();)
                       	{
                               	Record item = (Record)j.next();
                               	faqid= item.getValue ("MAX(FAQ_ID)").asInt();
                       	}
			//-------------------------------//
			Vector verlist=new Vector();
			TopicMetaDataXmlReader topicmetadata=null;
			String filepath="",version1="",newid="",Id="",QuesId="",ques="",ans="";
                        XmlWriter xmlWriter=null;
			if((username.equals("admin"))||(role.equals("institute_admin")))
				filepath=FaqPath+"/"+username+"/FAQ";
			else
			{
                                filepath=data.getServletContext().getRealPath("/Courses")+"/"+courseid+"/FAQ";
			}
			/*
			*Reading xml file for edit the answer
			*and also for adding answer with new version
			*by matching quesid edit and add the answer
			*/
			topicmetadata=new TopicMetaDataXmlReader(filepath+"/"+category+".xml");
                        Vector collect=topicmetadata.getFaqDetails();
                        if(collect!=null)
                        {
				if((!username.equals("admin"))||(!role.equals("institute_admin")))
				{
                        		for(int i=0;i<collect.size();i++)
                               		{//for
						Id =((FileEntry) collect.elementAt(i)).getFaqid();
                                		QuesId =((FileEntry) collect.elementAt(i)).getquestionid();
                                		ques=((FileEntry) collect.elementAt(i)).getquestion();
                                       		ans=((FileEntry) collect.elementAt(i)).getAnswer();
                                        	version1=((FileEntry) collect.elementAt(i)).getVersion();
                                        	if((QuesId.equals(quesid))&&(edittype.equals("addedit")))
                                        	{
							/**
							*checking the type(add/edit)
							*getting the last version of the answer
							*/
							Float f = new Float(version1);
							double d = f.doubleValue();
							if(f>tmp)
							tmp=f;
                                       		}
						else
						{
							/*edit the answer in the xml file*/
							if(Id.equals(quesid))
							{
                        				xmlWriter=TopicMetaDataXmlWriter.FaqXml(filepath,category+".xml");
                        				TopicMetaDataXmlWriter.appendFAQ(xmlWriter,quesid,QuesId,ques,Answer,version1);
                        				xmlWriter.writeXmlFile();
                                       			Vector str=DeleteEntry(filepath,category+".xml",quesid,"add",data);
							}
							data.setMessage(MultilingualUtil.ConvertedString("brih_FAQ",LangFile)+" "+MultilingualUtil.ConvertedString("brih_hasbeenedit",LangFile));
						}
					}//for
					if(edittype.equals("addedit"))
					{
						/*adding the answer with new version in the xml file*/
						newversion=tmp+0.1f;
                        			xmlWriter=TopicMetaDataXmlWriter.FaqXml(filepath,category+".xml");
                        			TopicMetaDataXmlWriter.appendFAQ(xmlWriter,Integer.toString(faqid),quesid,ques,Answer,Float.toString(newversion));
                        			xmlWriter.writeXmlFile();
						//data.setMessage("FAQ Answer add Successfully !!");
						data.setMessage(MultilingualUtil.ConvertedString("quiz_msg17",LangFile));
					}
				}//if
				if((username.equals("admin"))||(role.equals("institute_admin")))
				{
					/**
					*editing the anwer in the xml file
					*in admin and institute_admin case(in FAQ).
					*/
                        		for(int i=0;i<collect.size();i++)
                               		{//for
                               			QuesId =((FileEntry) collect.elementAt(i)).getquestionid();
                                       		ques=((FileEntry) collect.elementAt(i)).getquestion();
                                        	ans=((FileEntry) collect.elementAt(i)).getAnswer();
                                        	version1=((FileEntry) collect.elementAt(i)).getVersion();
                                        	if(quesid.equals(QuesId))
						{
                        				xmlWriter=TopicMetaDataXmlWriter.FaqXml(filepath,category+".xml");
                        				TopicMetaDataXmlWriter.appendFAQ(xmlWriter,quesid,quesid,ques,Answer,version1);
                        				xmlWriter.writeXmlFile();
                                            		Vector str=DeleteEntry(filepath,category+".xml",quesid,"del",data);
						}//if	
					}//for
					//data.setMessage("FAQ Edit Successfully !!");
					data.setMessage(MultilingualUtil.ConvertedString("brih_FAQ",LangFile)+" "+MultilingualUtil.ConvertedString("brih_hasbeenedit",LangFile));
				}//if
       			}//if
		}//try
		catch(Exception e){data.setMessage("Error in action:FAQ[Addanswer] !!"+e);}
	}//method close
	public void doSelectFav_Unfav(RunData data,Context context)
	{
		try
		{
			/**
                        *Getting Language value from temporary variable
                        *getting the parameters by using the ParameterParser
                        *Putting the parameters in context for use in templates.
                        */
			String LangFile=data.getUser().getTemp("LangFile").toString();
                        ParameterParser pp=data.getParameters();
                        User user=data.getUser();
                        String username=data.getUser().getName();
                        int uid=UserUtil.getUID(username);
                        String stat=pp.getString("stat","");
                        String category=pp.getString("category","");
                       	String courseid=(String)user.getTemp("course_id");
			String filepath=data.getServletContext().getRealPath("/Courses")+"/"+courseid+"/FAQ";
                        Criteria crit=new Criteria();
			/*getting the selcted question from the templates*/
			String deleteList=pp.getString("deleteFileNames","");
                        StringTokenizer st=new StringTokenizer(deleteList,"^");
                        String fileToDelete[]=new String[st.countTokens()];
                        for(int i=0;st.hasMoreTokens();i++)
                        {
                        	fileToDelete[i]=st.nextToken();
                        }
                        for(int i=0;i<fileToDelete.length;i++)
                        {
				/*Reading the xml file
				*by matching question getting id
				*and update favorite and unfavorite in the table
				*/
                        	String question =fileToDelete[i];
				st=new StringTokenizer(question,":");
                               	for(int j=0;st.hasMoreTokens();j++)
                               	{ //first 'for' loop
                               		String instid=st.nextToken();
                                       	String ef=st.nextToken();
                        		int questionid =Integer.parseInt(ef);
					crit=new Criteria();
                                	crit.add(FaqPeer.CATEGORY,category);
                                	List u=FaqPeer.doSelect(crit);
					for(int l=0;l<u.size();l++)
                        		{
						Faq element=(Faq)u.get(l);
                                		int Faqid=(element.getFaqId());
                                		String Catgydata=(element.getCategory());
                                		int quesid=(element.getQuesId());
                                		if((quesid==questionid) && (Catgydata.equals(category)))
                                		{
                                        		crit=new Criteria();
                                        		crit.add(FaqPeer.FAQ_ID,Faqid);
							if(stat.equals("unfav"))
                                        		crit.add(FaqPeer.VOTE,"Favorite");
							if(stat.equals("addfav"))
                                        		crit.add(FaqPeer.VOTE,"notattempt");
                                        		FaqPeer.doUpdate(crit);
                                		}
					}//for
				}//for
			}//for
			if(stat.equals("addfav"))
			//data.setMessage(" FAQ set as unfavorite  !!");
			data.setMessage(MultilingualUtil.ConvertedString("brih_FAQ",LangFile)+" "+MultilingualUtil.ConvertedString("faqmsg",LangFile));
			else
			//data.setMessage(" FAQ set as favorite !!");
			data.setMessage(MultilingualUtil.ConvertedString("brih_FAQ",LangFile)+" "+MultilingualUtil.ConvertedString("faqmsg1",LangFile));
		}
		catch(Exception e){data.setMessage("Error in action:FAQ[SelectFav_Unfav] !!"+e);}
	}//method close
	public void doRateFaq(RunData data,Context context)
	{
		try
		{
			/**
                        *Getting Language value from temporary variable
                        *getting the parameters by using the ParameterParser
                        *Putting the parameters in context for use in templates.
                        */
			String LangFile=data.getUser().getTemp("LangFile").toString();
                        ParameterParser pp=data.getParameters();
                        User user=data.getUser();
                        String username=data.getUser().getName();
                        int uid=UserUtil.getUID(username);
                        String ratetype=pp.getString("ratetype","");
                        String category=pp.getString("category","");
			String quesid=pp.getString("quesid","");
			String faqid=pp.getString("faqid","");
			boolean flag=false;
			/* insert the vote option(good,ok an worst) in the table*/
			Criteria crit=new Criteria();
			crit=new Criteria();
                        crit.add(FaqVotePeer.QUES_ID,quesid);
                        List v=FaqVotePeer.doSelect(crit);
			for(int m=0;m<v.size();m++)
                        {
                        	FaqVote element=(FaqVote)(v.get(m));
                                String Fid=Integer.toString(element.getFaqId());
                                int userid=element.getUserId();
				if((Fid.equals(faqid))&&(userid==uid))
				flag=true;
				data.setMessage(MultilingualUtil.ConvertedString("faqmsg2",LangFile)+" "+MultilingualUtil.ConvertedString("Grpmgmt_msg12",LangFile));
			}
			if(flag==false)
			{	
				int votevalue=1;
				crit=new Criteria();
                       		crit.add(FaqVotePeer.FAQ_ID,faqid);
                       		crit.add(FaqVotePeer.QUES_ID,quesid);
                       		crit.add(FaqVotePeer.USER_ID,uid);
				if(ratetype.equals("Good"))
                       		crit.add(FaqVotePeer.GOOD,votevalue);
				else
                       		crit.add(FaqVotePeer.GOOD,0);
				if(ratetype.equals("OK"))
                        	crit.add(FaqVotePeer.OK,votevalue);
				else
                        	crit.add(FaqVotePeer.OK,0);
				if(ratetype.equals("Worst"))
                       		crit.add(FaqVotePeer.WORST,votevalue);
				else
                       		crit.add(FaqVotePeer.WORST,0);
                       		FaqVotePeer.doInsert(crit);
				//data.setMessage("Rate  successfully");
				data.setMessage(MultilingualUtil.ConvertedString("brih_rate",LangFile)+" "+MultilingualUtil.ConvertedString("brih_successfully",LangFile));
			}
		}//try
		catch(Exception e){data.setMessage("Error in action:FAQ [RateFaq] !!"+e);}
	}//method close

 	/**
	* Default action to perform if the specified action
	* cannot be executed.
	* @param data RunData
        * @param context Context
        */
	public void doPerform(RunData data,Context context)throws Exception
	{
		String LangFile=data.getUser().getTemp("LangFile").toString();
		ParameterParser pp=data.getParameters();
       		String action = pp.getString("actionName","");
		context.put("actionName",action);
       		if(action.equals("eventSubmit_doSaveFaq"))
       			doSaveFaq(data,context);
       		else if(action.equals("eventSubmit_doSaveQues"))
       			doSaveQues(data,context);
       		else if(action.equals("eventSubmit_doDeleteQues"))
       			doDeleteQues(data,context);
       		else if(action.equals("eventSubmit_doAddAnswer"))
       			doAddAnswer(data,context);
       		else if(action.equals("eventSubmit_doSelectFav_Unfav"))
       			doSelectFav_Unfav(data,context);
       		else if(action.equals("eventSubmit_doRateFaq"))
       			doRateFaq(data,context);
		
       		else
       	 		data.setMessage(MultilingualUtil.ConvertedString("action_msg",LangFile));	
	}
	//method for deleting entry from xml file
	public  Vector DeleteEntry(String filePath,String xmlfile,String questionid,String Faqdel,RunData data)
        {
                Vector Read=null;
                try
                {
                        XmlWriter xmlWriter=null;
                        int seq=-1;
			String quesid="";
			/**
			*reading the xml file
			*for selecting the seqence
			*@see  TopicMetaDataXmlReader in Utils
			*/
                        TopicMetaDataXmlReader tr =new TopicMetaDataXmlReader(filePath+"/"+xmlfile);
                        Read=tr.getFaqDetails();
                        if(Read != null)
                        {
                                for(int n=0;n<Read.size();n++)
                                {
					if(Faqdel.equals("add"))
                                        quesid =((FileEntry)Read.elementAt(n)).getFaqid();
					if(Faqdel.equals("del"))
                                        quesid =((FileEntry)Read.elementAt(n)).getquestionid();
                                        if(questionid.equals(quesid))
                                        {
                                                seq=n;
                                                break;
                                        }
                                }
                        }
                        /**
                        Here we deleting the particular entry from the "xml" file
			*by seqence
			*@see xmlWriter in Util
                        */
                        xmlWriter=TopicMetaDataXmlWriter.FaqXml(filePath,xmlfile);
                        xmlWriter.removeElement("FAQ",seq);
                        xmlWriter.writeXmlFile();
                }//try
                catch(Exception e){data.setMessage("Error in method:DeleteEntry !!"+e);}
        return Read;
        }//methodDeleteEntry
}//class
