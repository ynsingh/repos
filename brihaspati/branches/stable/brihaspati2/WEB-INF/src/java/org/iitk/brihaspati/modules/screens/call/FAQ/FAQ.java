package org.iitk.brihaspati.modules.screens.call.FAQ;

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
 *
 *
 *  Contributors: Members of ETRG, I.I.T. Kanpur
 *
 */

/**
 *@author: <a href="mailto:seema_020504@yahoo.com">Manorama Pal</a>
 *@author: <a href="mailto:Kishore.shukla@gmail.com">Kishorekumar shukla</a>
 */

import java.io.File;
import java.util.List;
import java.util.Vector;
import java.util.Date;
import java.util.ListIterator;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import com.workingdogs.village.Record;

import org.apache.turbine.util.RunData;
import org.apache.torque.util.Criteria;
import org.apache.velocity.context.Context;
import org.iitk.brihaspati.modules.utils.UserUtil;
import org.iitk.brihaspati.modules.utils.FileEntry;
import org.iitk.brihaspati.modules.utils.ExpiryUtil;
import org.iitk.brihaspati.modules.utils.FAQDetail;
import org.iitk.brihaspati.modules.utils.GroupUtil;
import org.iitk.brihaspati.modules.utils.XmlWriter;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.iitk.brihaspati.modules.screens.call.SecureScreen;
import org.iitk.brihaspati.modules.utils.TopicMetaDataXmlReader;
import org.iitk.brihaspati.modules.utils.TopicMetaDataXmlWriter;
import org.apache.turbine.util.parser.ParameterParser;

import org.iitk.brihaspati.om.Faq;
import org.iitk.brihaspati.om.FaqPeer;
import org.iitk.brihaspati.om.FaqVote;
import org.iitk.brihaspati.om.FaqVotePeer;
import org.apache.turbine.om.security.User;
import org.apache.turbine.services.security.torque.om.TurbineUserGroupRolePeer;
import org.apache.turbine.services.security.torque.om.TurbineUserPeer;

/**
  * This class contains code for Faq  
  * Get all the records in a table using a Peer, and
  * place the Vector of data objects in the context
  * where they can be displayed by a #foreach loop.
  */
public class FAQ extends SecureScreen
{
	 /**
         * Place all the data object in the context for use in the template.
         * @param data RunData instance
         * @param context Context instance
         * @return nothing
        */

	public void doBuildTemplate(RunData data, Context context)
	{
		try
		{
			/**
                        *Retrieve the parameters by using the ParameterParser
                        *Putting the parameters context for use in templates.
                        */
                        ParameterParser pp=data.getParameters();
                	User user=data.getUser();
			String username=data.getUser().getName();
			context.put("username",username);
			context.put("tdcolor",pp.getString("count",""));
			String categoryname=pp.getString("category","");
			context.put("category",categoryname);
			String question=pp.getString("question","");
			context.put("ques",question);
			String Answer=pp.getString("answer","");
			context.put("ans",Answer);
			String quesid=pp.getString("quesid","");
			context.put("quesid",quesid);
			String mode=pp.getString("mode","");
			context.put("mode",mode);
			String edittype=pp.getString("edittype","");
			context.put("edittype",edittype);
			String stat=pp.getString("stat","");
			context.put("stat",stat);
			String modetype=pp.getString("modetype","");
			context.put("modetype",modetype);
			String modeadd=pp.getString("modeadd","");
			context.put("modeadd",modeadd);
			String modeedit=pp.getString("modeedit","");
			context.put("modeedit",modeedit);
			String faqid=pp.getString("faqid","");
			context.put("faqid",faqid);
			/**
			*getting userid and role
			*Putting the parameters context for use in templates.
			*/ 
			int uid=UserUtil.getUID(username);
			context.put("uid",uid);
			String role=(String)user.getTemp("role");
                        context.put("user_role",role);
			String status=new String();
			String filePath="",courseid="";
			Vector entry=new Vector();
			Vector queslist=new Vector();
			Vector queslist2=new Vector();
			Criteria crit=new Criteria();
    			String relation="";
			/*get the Faq path according to the user */
			if((username.equals("admin"))||(role.equals("institute_admin")))
                        {
			filePath=data.getServletContext().getRealPath("/UserArea")+"/"+username+"/FAQ";
                        }
			else
			{
                        context.put("coursename",(String)user.getTemp("course_name"));
                        courseid=(String)user.getTemp("course_id");
                        context.put("courseid",courseid);
			filePath=data.getServletContext().getRealPath("/Courses")+"/"+courseid+"/FAQ";
			}
			/**
			*get the Faq xml path category wise
			*reading the xml if exists by using TopicMetaDataXmlReaderUtil
			*/
			File f=new File(filePath+"/"+categoryname+".xml");
			TopicMetaDataXmlReader topicmetadata=null;
			if(f.exists())
			{
			topicmetadata=new TopicMetaDataXmlReader(filePath+"/"+categoryname+".xml");
                        entry=topicmetadata.getFaqDetails();
			if(entry==null)
                             return;
                          if(entry.size()!=0)
                          {
				status="NoBlank";
                                context.put("allfaq", entry);
				//---------------------------------FAQdetail(vote,expiry) part------------------------------//
				if((mode.equals("faqcheck"))||(modeedit.equals("edit")))
				{
					/**
					*get the Faq detail (vote,expiry date)according to the QID
					*by using Criteria(FAQ table)
                        		*put in the context for the use in templates
					*/
					XmlWriter xmlWriter=null;
					String Cur_date=ExpiryUtil.getCurrentDate("-");
					String newfaqid="",faqnoid="",expdatenew="";
                                       	FAQDetail faqdetail= new FAQDetail();
					crit=new Criteria();
                        		crit.add(FaqPeer.QUES_ID,quesid);
                        		List u=FaqPeer.doSelect(crit);
                                       	for(int a=0;a<u.size();a++)
					{//for1
						Faq element=(Faq)u.get(a);
                                       		String Fid=Integer.toString(element.getFaqId());
                                       		String Qid=Integer.toString(element.getQuesId());
                                       		String Vote=element.getVote();
						java.util.Date exp=element.getExpiryDate();
                               			String expdate=exp.toString();
                                               	if(Qid.equals(quesid))
						{
							expdatenew=expdate;
                                       			faqdetail= new FAQDetail();
                                       			faqdetail.setFaqId(Fid);
                                       			faqdetail.setVote(Vote);
							queslist2.addElement(faqdetail);
						}
                               			context.put("answerlist",queslist2);
						//---------------------------------FAQdetail(vote,expiry) part------------------------------//
						//-----------------------------------------voting part of answer---------------//
						/**
						*check for the expiry date(ratefaq)
						*selecting FAQ according to the no of votes(good and ok) 
						*by executing the sql query
						*/
						if((!username.equals("admin"))&&(Cur_date.equals(expdatenew)))
						{
							Vector visitval=new Vector();
							int tmp=0;
							for(int s=0;s<queslist2.size();s++)
							{	
								faqnoid=((FAQDetail)queslist2.elementAt(s)).getFaqId();
								String good="SELECT SUM(GOOD)TOTAL FROM FAQ_VOTE WHERE FAQ_ID="+faqnoid;
                               					List goodlist = FaqVotePeer.executeQuery(good);
								int goodnos=0,oknos=0;
								for(ListIterator h = goodlist.listIterator();h.hasNext();)
                        					{
                               						Record item = (Record)h.next();
									goodnos=item.getValue("TOTAL").asInt();
                        					}
								String ok="SELECT SUM(OK)TOTAL FROM FAQ_VOTE WHERE FAQ_ID="+faqnoid;
                               					List oklist = FaqVotePeer.executeQuery(ok);
								for(ListIterator q = oklist.listIterator();q.hasNext();)
                        					{
                               						Record item = (Record)q.next();
									oknos=item.getValue("TOTAL").asInt();
                        					}
								int goodokval=goodnos+oknos;
								if(goodokval > tmp)
								{
									tmp = goodokval;
									newfaqid=faqnoid;
								}
								if(goodokval==tmp)
								{
									if(goodnos>oknos)
									{
										newfaqid=faqnoid;
									}
									if(goodnos==oknos)
									{
										newfaqid=faqnoid;	
									}
								}
							}//for
						}//admin
					}//for1
					//------------------end voting part-------------------------------//
					//------------------------this part is for selecting answer---------------------------//
                                       	for(int i=0;i<entry.size();i++)
                                       	{//for2
						/**
						*selcting  Answer Question wise
						*in all cases(admin,institute_admin and course )
						*checking all cases with date including expiry time 
                        			*put in the context for the use in templates
						*/
                                               	String Id =((FileEntry) entry.elementAt(i)).getFaqid();
                                               	String QuesId =((FileEntry) entry.elementAt(i)).getquestionid();
                                               	String ques=((FileEntry)entry.elementAt(i)).getquestion();
                                               	String ans=((FileEntry) entry.elementAt(i)).getAnswer();
                                               	String version=((FileEntry) entry.elementAt(i)).getVersion();
                                               	if(QuesId.equals(quesid))
						{
							context.put("ques",ques);
							context.put("quesid",QuesId);
							FileEntry filelist=new FileEntry();
							if(username.equals("admin"))
							{
                                               			filelist.setFaqid(Id);
                               					filelist.setquestionid(QuesId);
                                              			filelist.setAnswer(ans);
                                               			queslist.addElement(filelist);
							}
							if(!username.equals("admin"))
							{
								if(!Cur_date.equals(expdatenew))
								{
                                               				filelist.setFaqid(Id);
                               						filelist.setquestionid(QuesId);
                                               				filelist.setAnswer(ans);
                                               				queslist.addElement(filelist);
								}
								if(Cur_date.equals(expdatenew))
								{
									/**
									*selecting the best answer on the be-half of voting
									*by matching the FAQid and deleting entries
									*/
									if(newfaqid.equals(Id))
									{
                                               					filelist.setFaqid(Id);
                               							filelist.setquestionid(QuesId);
                                               					filelist.setAnswer(ans);
                                               					queslist.addElement(filelist);
										if(!version.equals(1.1))
										{
											xmlWriter=TopicMetaDataXmlWriter.FaqXml(filePath,categoryname+".xml");
                                                       					TopicMetaDataXmlWriter.appendFAQ(xmlWriter,newfaqid,quesid,ques,ans,"1.1");
                                                       					xmlWriter.writeXmlFile();
											Vector str=DeleteEntry(filePath,categoryname+".xml",Id,data);
										}
									}
									else
									{
										Vector str=DeleteEntry(filePath,categoryname+".xml",Id,data);
										/*Delete FAQ from database */
                                						crit=new Criteria();
                                						crit.add(FaqPeer.FAQ_ID,Id);
                                						FaqPeer.doDelete(crit);
									}
								}//cur
							}//admin
						}//quesid
                                       	}//for2
                                	context.put("mode",mode);
                                	context.put("queslist",queslist);
					//------------------------end part is for selecting best answer---------------------------//
					/**
					*comparing dates for getting the time limit
					*for Rate faq and for adding answer
                        		*put in the context for the use in templates
					*/
					DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
					Date d1 = df.parse(Cur_date);
					Date d2 = df.parse(expdatenew);
    					//String relation;
    					if (d1.equals(d2))
      					relation = "same";
    					else if (d1.before(d2))
      					relation = "before";
    					else
      					relation = "after";
					context.put("relation",relation);
					if(relation.equals("after"))
					{
						//Deleting the old votes from the database
                                		crit=new Criteria();
                                		crit.add(FaqVotePeer.QUES_ID,quesid);
                                		FaqVotePeer.doDelete(crit);
					}
                                }//ifmodecheck
				
			}
                        else
                        	status="Blank";
                        context.put("mode",mode);
                        context.put("status",status);
			String QuesId="",ques="",vers="";
			Vector entryques=new Vector();
			Vector entrydetail=new Vector();
			/**
			*getting the queslist without repeating the question
			*by reading xml file
			*/
			for(int k=0;k<entry.size();k++)
			{
                                String FAQId =((FileEntry) entry.elementAt(k)).getFaqid();
				QuesId =((FileEntry) entry.elementAt(k)).getquestionid();
                               	ques=((FileEntry) entry.elementAt(k)).getquestion();
                               	vers=((FileEntry) entry.elementAt(k)).getVersion();
				if(vers.equals("1.1"))
				{
					FileEntry filelist=new FileEntry();
                               		filelist.setFaqid(FAQId);
                               		filelist.setquestionid(QuesId);
                               		filelist.setquestion(ques);
                               		entryques.addElement(filelist);
				}
			}
                        context.put("entryques",entryques);
			Date date;
			/**
			*comparing the queslist also Faq table
			*matching with Faqid add in the vector
                       	*put in the context for the use in templates
			*/
			crit=new Criteria();
                        crit.add(FaqPeer.CATEGORY,categoryname);
                        List w=FaqPeer.doSelect(crit);
			for(int a=0;a<w.size();a++)
                        {
                        	Faq element=(Faq)(w.get(a));
                                String Fid=Integer.toString(element.getFaqId());
                                String userid=Integer.toString(element.getUserId());
                                String Qid=Integer.toString(element.getQuesId());
				date=element.getPostTime();
                                String posttime=date.toString();
                                date=element.getExpiryDate();
                                String expirydate=date.toString();
                                String Vote=element.getVote();
				for(int n=0;n<entryques.size();n++)
                                {
                                       	String FAQId1 =((FileEntry) entryques.elementAt(n)).getFaqid();
                                       	if(FAQId1.equals(Fid))
                                       	{
                                       		FAQDetail faqdetail= new FAQDetail();
                                       		faqdetail.setFaqId(Fid);
                                       		faqdetail.setQuesId(Qid);
                                       		faqdetail.setUserId(userid);
						faqdetail.setPDate(posttime);
						faqdetail.setPDate(expirydate);
                                       		faqdetail.setVote(Vote);
                                       		entrydetail.addElement(faqdetail);
					}
                                }
			}
                        context.put("entrydetail",entrydetail);
			//---------------------------fav--unfav-----------------------------------//
			Vector favlist=new Vector();
			Vector unfavlist=new Vector();
			String stat1=pp.getString("stat","");
			String empty=new String();
			/**
			*checking the status for fav unfav faq
			*selecting the favorite and unfavorite list of Faq
                       	*put in the context for the use in templates
			*/
			if(stat.equals("unfav")||stat.equals("addfav"))
			{
				crit=new Criteria();
                        	crit.add(FaqPeer.CATEGORY,categoryname);
                        	List u=FaqPeer.doSelect(crit);
				for(int j=0;j<u.size();j++)
				{
					Faq element=(Faq)u.get(j);
                                        int Faqid=(element.getFaqId());
					String vote=element.getVote();
                                        String category=(element.getCategory());
					if(vote.equals("Favorite"))
					{
						for(int l=0;l<entry.size();l++)
						{
							QuesId =((FileEntry) entry.elementAt(l)).getquestionid();
                               				ques=((FileEntry) entry.elementAt(l)).getquestion();
                               				vers=((FileEntry) entry.elementAt(l)).getVersion();
							if((Faqid == Integer.parseInt(QuesId))&&(vers.equals("1.1")))
							{
								FileEntry filelist=new FileEntry();
                               					filelist.setquestionid(QuesId);
                               					filelist.setquestion(ques);
								favlist.addElement(filelist);
							}
						}
					}//if
					else
					{
						for(int m=0;m<entry.size();m++)
						{
							QuesId =((FileEntry) entry.elementAt(m)).getquestionid();
                               				ques=((FileEntry) entry.elementAt(m)).getquestion();
                               				vers=((FileEntry) entry.elementAt(m)).getVersion();
							if((Faqid == Integer.parseInt(QuesId))&&(vers.equals("1.1")))
							{
								FileEntry filelist=new FileEntry();
                              					filelist.setquestionid(QuesId);
                             					filelist.setquestion(ques);
                               					unfavlist.addElement(filelist);
							}
						}
					}//else
				}//for	
			}//if
			if((stat1.equals("addfav"))&&(favlist.size()!=0))
			{
				empty="noempty";
			}
			else if((stat1.equals("unfav"))&&(unfavlist.size()!=0))
			{
				empty="noempty";
			}
			else
			empty="empty";
                       	context.put("empty",empty);
                       	context.put("favlist",favlist);
                       	context.put("unfavlist",unfavlist);
			context.put("stat",stat1);
			//--------------------------------reading aswer of the particular question for editing-------------//
			String editans="";
			for(int z=0;z<queslist.size();z++)
			{
				QuesId =((FileEntry) queslist.elementAt(z)).getFaqid();
                                String ansedit=((FileEntry) queslist.elementAt(z)).getAnswer();
				if(faqid.equals(QuesId))
				editans=ansedit;
			}
			context.put("editans",editans);
			//--------------------------------ending partreading aswer of the particular question for editing-------------//
			//---------------------------end--fav--unfav-----------------------------------//
			/*checking the status of voting*/
			if(!username.equals("admin"))
                       	{
				Vector ansvote=new Vector();
				Vector votedetail=new Vector();
				String check="",Fid="",userid="",Qid="";
				crit=new Criteria();
                       		crit.add(FaqVotePeer.QUES_ID,quesid);
                       		List v=FaqVotePeer.doSelect(crit);
				crit=new Criteria();
                       		crit.add(FaqPeer.QUES_ID,quesid);
                       		List s=FaqPeer.doSelect(crit);
				for(int n=0;n<s.size();n++)
                               	{
                                       	Faq element=(Faq)(s.get(n));
					Fid=Integer.toString(element.getFaqId());
					userid=Integer.toString(element.getUserId());
					Qid=Integer.toString(element.getQuesId());
					for(int m=0;m<v.size();m++)
					{
                                       		FaqVote element1=(FaqVote)(v.get(m));
						String Fid1=Integer.toString(element1.getFaqId());
						String userid1=Integer.toString(element1.getUserId());
						String Qid1=Integer.toString(element1.getQuesId());
						if((Fid.equals(Fid1))&&(userid1.equals(Integer.toString(uid))))
						userid=userid1;
						if(Qid1.equals(quesid))
						{
							if(!ansvote.contains(userid1))
								 ansvote.addElement(userid1);
						}
					}
					FAQDetail faqdetail= new FAQDetail();
                                       	faqdetail.setFaqId(Fid);
                                       	faqdetail.setUserId(userid);
					votedetail.addElement(faqdetail);
				}		
				/* put the totalvote in the context for use in templates*/
				if(modetype.equals("rate"))
					context.put("check",check);
				context.put("votedetail",votedetail);
				context.put("totalvote",ansvote.size());
				/*getting the list of student of particular course */
				int g_id=GroupUtil.getGID(courseid);
				crit=new Criteria();
                        	crit.addJoin(TurbineUserPeer.USER_ID,TurbineUserGroupRolePeer.USER_ID);
                        	crit.add(TurbineUserGroupRolePeer.ROLE_ID,3);
                        	crit.add(TurbineUserGroupRolePeer.GROUP_ID,g_id);
                        	crit.setDistinct();
                        	List totalstu=TurbineUserPeer.doSelect(crit);
				context.put("totalstu",totalstu.size());
			}
		}//if exists
	}//try	
	catch(Exception e){ data.setMessage("Error in Screen [FAQ] !!"+e);}
	}//methodclose
 	public  Vector DeleteEntry(String filePath,String xmlfile,String Faqid,RunData data)
        {
                Vector Read=null;
                try
                {
                        XmlWriter xmlWriter=null;
                        int seq=-1;
                        TopicMetaDataXmlReader tr =new TopicMetaDataXmlReader(filePath+"/"+xmlfile);
                        Read=tr.getFaqDetails();
                        if(Read != null)
                        {
                                for(int n=0;n<Read.size();n++)
                                {
                                        String Id =((FileEntry)Read.elementAt(n)).getFaqid();
                                        String quesid =((FileEntry)Read.elementAt(n)).getquestionid();
                                        if(Faqid.equals(Id))
                                        {
                                                seq=n;
                                                break;
                                        }
                                }
                        }
                        /**
                        Here we deleting the particular entry from the "xml" file
                        */
                        xmlWriter=TopicMetaDataXmlWriter.FaqXml(filePath,xmlfile);
                        xmlWriter.removeElement("FAQ",seq);
                        xmlWriter.writeXmlFile();
                }//try
                catch(Exception e){data.setMessage("Error in method[FAQ]:DeleteEntry !!"+e);}
        return Read;
        } //methodDeleteEntry
}
