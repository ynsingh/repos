package org.iitk.brihaspati.modules.screens.call.Group_Mgmt;

/*
 * @(#)ViewActivity.java
 *
 *  Copyright (c) 2006-07 ETRG,IIT Kanpur.
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
 *  @author: <a href="mailto:seema_020504@yahoo.com">Seemapal</a>
 *  @author: <a href="mailto:kshuklak@rediffmail.com">Kishore Kumar shukla</a>
 *  @author: <a href="mailto:shastashekh@hotmail.com">Shaista</a>
 *  @modified date: 15-02-2011
 */


import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;
import org.apache.turbine.util.parser.ParameterParser;
import org.apache.turbine.om.security.User;

import org.iitk.brihaspati.modules.utils.TopicMetaDataXmlReader;
import org.iitk.brihaspati.modules.screens.call.SecureScreen;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.iitk.brihaspati.modules.utils.FileEntry;
import org.iitk.brihaspati.modules.utils.ExpiryUtil;
import org.iitk.brihaspati.modules.utils.XmlWriter;
import org.iitk.brihaspati.modules.utils.TopicMetaDataXmlWriter;

import org.iitk.brihaspati.modules.utils.UserUtil;
//import org.iitk.brihaspati.modules.utils.CourseTimeUtil;
//import org.iitk.brihaspati.modules.utils.ModuleTimeUtil;
import org.iitk.brihaspati.modules.utils.MailNotificationThread;

import java.util.Vector;
import java.util.StringTokenizer;
import java.io.File;


public class ViewActivity extends SecureScreen
{
	/**
	*This class is use for viewing activity set by group administrator
     	* Place all the data object in the context
     	* for use in the template.
     	*/
	public void doBuildTemplate(RunData data, Context context)
	{
		try
		{
                        ParameterParser pp=data.getParameters();
			
			/**
                        * Get courseid,username and coursename for the user currently logged in
                        * Put it in the context for Using in templates
                        * @see UserUtil in Util.
                        */
			File f1=null;
                	User user=data.getUser();
			String username=user.getName();
			context.put("username",username);
			context.put("coursename",(String)user.getTemp("course_name"));
			String courseid=(String)user.getTemp("course_id");
			context.put("courseid",courseid);
			String curdate=ExpiryUtil.getCurrentDate("-");
			context.put("curdate",curdate);

			//Get the path where the GroupList and groupname xml are there.
		        String groupPath=data.getServletContext().getRealPath("/Courses"+"/"+courseid+"/GroupManagement");
			File f=new File(groupPath+"/GroupList__des.xml");

			TopicMetaDataXmlReader topicmetadata=null;
			Vector uName=new Vector();
			Vector sName = new Vector();
			String uname="",grpname="",groupdesc="",gnam="",leadname="";
			if(f.exists())
			{//if1
                        	/**
                        	*Reading the GroupList xml for getting the details
                        	*groups (grouplist,groupname,grouptype) and Mode
                        	*Put in the contexts for use in template
                        	*@see TopicMetaDataXmlReader in Utils.
                                **/
				topicmetadata=new TopicMetaDataXmlReader(groupPath+"/GroupList__des.xml");
				Vector grplist=topicmetadata.getGroupDetails();
                                for(int i=0;i<grplist.size();i++)
				{//for1
					grpname=((FileEntry) grplist.elementAt(i)).getName();
					String type =((FileEntry) grplist.elementAt(i)).gettype();
					leadname=((FileEntry) grplist.elementAt(i)).getUserName();
					topicmetadata=new TopicMetaDataXmlReader(groupPath+"/"+grpname+"__des.xml");
					groupdesc=topicmetadata.getActivity();
                                        Vector list=topicmetadata.getGroupDetails();
					if(list!=null) 
					{//if2
						for(int j=0;j<list.size();j++)
						{//for2
							//String gnam=new String();
							uname=((FileEntry) list.elementAt(j)).getUserName();
							uName.addElement(uname);
                                                        if(username.equals(uname))
					                {//if3
                                                		context.put("type",type);
                                                        	context.put("grpname",grpname);
								if(groupdesc!=null)
								{
									/**
									* Get the groupActivity
									* and set the vm according to the activity.
									*/
	                                                        	StringTokenizer st=new StringTokenizer(groupdesc,",");
				                                	for(int kk=0;st.hasMoreTokens();kk++)
                                					{ //first 'for' loop
                                        					String msg_idd=st.nextToken();
										if(msg_idd.equals("Discussion Board"))
											context.put("act0",msg_idd);
										if(msg_idd.equals("Notice"))
											context.put("act1",msg_idd);
                                                                     	 	if(msg_idd.equals("Chat"))
  	                                                                        	context.put("act2",msg_idd);
										if(msg_idd.equals("Local Mail"))
											context.put("act3",msg_idd);
										if(msg_idd.equals("Assignment"))
                                                                        		context.put("act4",msg_idd);
                                        				}//for
								}//if
								gnam=grpname;
								if(gnam.equals(grpname))
                                                        		context.put("mblist",uName);
                                                        		context.put("Mode","noempty");
									context.put("groupdesc",groupdesc);
									if((gnam.equals(grpname)) && (!leadname.equals("")))
									{
										context.put("grpleader",leadname);
										context.put("setlead","nolead");
										ErrorDumpUtil.ErrorLog("\nleadname"+leadname+"\ngnam"+gnam);
									}
							}//if3
						}//for2
						uName = new Vector();
						for(int k=0;k<list.size();k++)
                                                {//for3
							uname=((FileEntry) list.elementAt(k)).getUserName();
                                                        if(username.equals(uname))
							{
								gnam=grpname;
							}
							else
							{
								uName.addElement(uname);
							}
							if(gnam.equals(grpname))
                                                       	context.put("mblist1",uName);
						}//for3
						uName = new Vector();
						f1=new File(groupPath+"/Pollexptime__des.xml");
						if(f1.exists())//f1.exists()
						{
							for(int s=0;s<list.size();s++)
        	                                        {//for4
                	                                        uname=((FileEntry) list.elementAt(s)).getUserName();
                        	                                if(username.equals(uname))
                                	                        {
                                        	                        gnam=grpname;
                       							topicmetadata=new TopicMetaDataXmlReader(groupPath+"/Pollexptime__des.xml");
                        						Vector grplist2=topicmetadata.getGroupDetails();
									boolean found=false;
									String check="";
									if(grplist2!=null)
									{
										for(int b=0;b<grplist2.size();b++)
										{
											String name1 =((FileEntry) grplist2.elementAt(b)).getName();
											String stuno=((FileEntry) grplist2.elementAt(b)).getstudentno();
											String expday=((FileEntry) grplist2.elementAt(b)).getUserName();
											if(gnam.equals(name1))
											{
												context.put("stuno",stuno);
												context.put("expday",expday);
											}
										}//for
										for(int c=0;c<grplist2.size();c++)
                        	                                                {
                                	                                            	String name2 =((FileEntry) grplist2.elementAt(c)).getName();
											if(gnam.equals(name2))
											{
												found=true;
											}
										}
										if(found==false)
											context.put("check","nocheck");
									}
                        	                                }
							}//for4
						} // f1.exists()
					}//if2
				}//for1
			}//if1
			f1=new File(groupPath+"/PollingList__des.xml");
			if(f1.exists())
                       	topicmetadata=new TopicMetaDataXmlReader(groupPath+"/PollingList__des.xml");
                        Vector grplist1=topicmetadata.getGroupDetails();
			if(grplist1!=null)
			{
				for(int l=0;l<grplist1.size();l++)
				{
					String name =((FileEntry) grplist1.elementAt(l)).gettype();
					if(username.equals(name))
						context.put("name",name);
				}
			}
			/**
                         *Time calculaion for how long user use this page.
                         */
			 String Role = (String)user.getTemp("role");
                         int uid=UserUtil.getUID(user.getName());
                         if((Role.equals("student")) || (Role.equals("instructor")))
                         {
                                //CourseTimeUtil.getCalculation(uid);
                                //ModuleTimeUtil.getModuleCalculation(uid);
				int eid=0;
				MailNotificationThread.getController().CourseTimeSystem(uid,eid);
                         }

		}//try
		catch(Exception e){
                                        ErrorDumpUtil.ErrorLog("Error in Screen:ViewActivity !!"+e);
                                        data.setMessage("See ExceptionLog !! " );
                                  }
	}//method
}//class
