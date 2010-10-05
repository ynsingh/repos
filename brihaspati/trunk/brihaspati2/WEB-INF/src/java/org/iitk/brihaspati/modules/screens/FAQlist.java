package org.iitk.brihaspati.modules.screens;

/*
 * @(#)FAQlist.java
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
 *This class contains code for FAQlist 
 *@author: <a href="mailto:seema_020504@yahoo.com">Seemapal</a>
 *@author: <a href="mailto:kshuklak@rediffmail.com">Kishore Kumar shukla</a>
 */

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;
import org.apache.turbine.om.security.User;
import org.apache.torque.util.Criteria;
import java.util.StringTokenizer;
import org.apache.turbine.util.parser.ParameterParser;
import org.apache.turbine.modules.screens.VelocityScreen;

import org.iitk.brihaspati.modules.utils.UserUtil;
import org.iitk.brihaspati.modules.utils.FAQDetail;
import org.iitk.brihaspati.modules.utils.FileEntry;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.iitk.brihaspati.modules.utils.GroupUtil;
import org.iitk.brihaspati.modules.utils.ListManagement;
import org.iitk.brihaspati.modules.utils.CommonUtility;
import org.iitk.brihaspati.modules.utils.AdminProperties;
import org.iitk.brihaspati.modules.utils.TopicMetaDataXmlReader;
import org.apache.turbine.services.security.torque.om.TurbineUserGroupRolePeer;
import org.apache.turbine.services.security.torque.om.TurbineUserGroupRole;
import org.iitk.brihaspati.om.FaqPeer;
import org.iitk.brihaspati.om.Faq;

import java.util.Vector;
import java.util.List;
import java.io.File;


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
			String mode=pp.getString("mode","");
			context.put("mode",mode);
			String quesid=pp.getString("quesid","");
			context.put("quesid",quesid);
			String status=pp.getString("status","");
			String type=pp.getString("type","");
			context.put("type",type);
			String modefaq=pp.getString("modefaq","");
			context.put("modefaq",modefaq);
			/**
			*Getting the list from the Database
                        *put in the context for the use in templates
			*/
			Vector entry=new Vector();
			Vector queslist=new Vector();
			Vector gidlist=new Vector();
			String username="",filePath="";
			int uid=0,gid=0;
			int[]  uId2={1,0,3,2};
			//getting istitute id for the institute admin FAQs
			if(type.equals("instadmin"))
			{
				/*getting username and uid*/
				User user=data.getUser();
                        	username=data.getUser().getName();
				uid=UserUtil.getUID(username);
				/*getting group id for the FAQ*/
				Criteria crit=new Criteria();
                        	crit.add(TurbineUserGroupRolePeer.USER_ID,uid);
				crit.andNotIn(TurbineUserGroupRolePeer.GROUP_ID,uId2);
                        	List v=TurbineUserGroupRolePeer.doSelect(crit);
				for(int i=0;i<v.size();i++)
				{
                                	TurbineUserGroupRole element=(TurbineUserGroupRole)v.get(i);
                                	int s=(element.getGroupId());
					String gname=GroupUtil.getGroupName(s);
					StringTokenizer st=new StringTokenizer(gname,"_");
					for(int j=0;st.hasMoreTokens();j++)
                                	{ //first 'for' loop
                                        	String instid=st.nextToken();
						String ef=st.nextToken();
                                		gidlist.add(ef);
					}
                        	}
			}
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
					for(int i=0;i<gidlist.size();i++)
					{
						gid=Integer.parseInt(gidlist.elementAt(i).toString());
                        			crit.add(FaqPeer.GROUP_ID,3);
                        			crit.add(FaqPeer.USER_ID,gid);
                        			u=FaqPeer.doSelect(crit);
						if(u.size()!=0)
						{
                        				for(int m=0;m<u.size();m++)
                        				{
                        					Faq element=(Faq)(u.get(m));
                                				String cat_Name=element.getCategory();
								if(!entry.contains(cat_Name))
								{
                                					entry.addElement(cat_Name);
								}
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
							if(!entry.contains(cat_Name))
							{
                                				entry.addElement(cat_Name);
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
					for(int i=0;i<gidlist.size();i++)
					{
						gid=Integer.parseInt(gidlist.elementAt(i).toString());
                        			username=UserUtil.getLoginName(gid);
                        			filePath=data.getServletContext().getRealPath("/UserArea")+"/"+username+"/FAQ";
						file=new File(filePath+"/"+category+".xml");
						if(file.exists())
						{
							topicmetadata=new TopicMetaDataXmlReader(filePath+"/"+category+".xml");
                        				entry=topicmetadata.getFaqDetails();
						}
					}
				}
				else
				{
					username=UserUtil.getLoginName(1);
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
				else
				CommonUtility.PListing(data ,context ,entry);
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
		}//try
		catch(Exception e){ data.setMessage("Error in screen [FAQlist] !! " + e); }
	}
}
