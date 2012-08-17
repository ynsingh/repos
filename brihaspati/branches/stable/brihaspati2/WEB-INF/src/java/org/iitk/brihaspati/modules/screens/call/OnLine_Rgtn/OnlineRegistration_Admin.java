package org.iitk.brihaspati.modules.screens.call.OnLine_Rgtn;

/*
 * @(#) OnlineRegistration_Admin.java
 *
 *  Copyright (c) 2008, 2010 ETRG,IIT Kanpur.
 *  
 *  All Rights Reserved.
 *  Redistributions of source code must retain the above copyright
 *  notice, this  list of conditions and the following disclaimer.
 *  Redistribution in binary form must reproducuce the above copyright
 *  notice, this list of conditions and the following disclaimer in
 *  the documentation and/or other materials provided with the
 *  distribution.
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



import java.io.File;
import java.util.List;
import java.util.Vector;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;
import org.apache.turbine.om.security.User;
import org.apache.turbine.util.parser.ParameterParser;
import org.apache.turbine.services.servlet.TurbineServlet;

import org.iitk.brihaspati.modules.utils.TopicMetaDataXmlReader;
import org.iitk.brihaspati.modules.utils.CourseUserDetail;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.iitk.brihaspati.modules.utils.MultilingualUtil;
import org.iitk.brihaspati.modules.utils.DbDetail;
import org.iitk.brihaspati.modules.utils.InstituteIdUtil;
import org.iitk.brihaspati.modules.screens.call.SecureScreen_Institute_Admin;



/**
 *This class contain the list of request of user registration and list of course registration.
 * institute admin accept or reject the request.
 
 * @author  <a href="mailto:singh_jaivir@rediffmail.com">Jaivir Singh</a>
 * @author  <a href="mailto:omprakash_kgp@yahoo.co.in">Om Prakash</a>
 * @author  <a href="mailto:richa.tandon1@gmail.com">Richa Tandon</a>
 * @author <a href="mailto:rpriyanka12@ymail.com">Priyanka Rawat</a>
 * @modified date: 20-10-2010,23-12-2010
 * @modified date: 09-08-2012 (Priyanka)
 */

public class OnlineRegistration_Admin extends SecureScreen_Institute_Admin {

	/**
	 * Place all the data object in the context for use in the template.
	 * @param data RunData
	 * @param context Context
	 */

	public void doBuildTemplate( RunData data, Context context ){
	try{
		
		User user=data.getUser();
                ParameterParser pp=data.getParameters();
		context.put("lang",pp.getString("lang","english"));
	 	String status=pp.getString("status","");
                String lang=pp.getString("lang","english");
                context.put("lang",lang);
		String counter=pp.getString("count","");
		context.put("tdcolor",counter);
		String instituteid=data.getUser().getTemp("Institute_id").toString();
		String prgname="";
                if(status.equals("UserResitration"))
                {
                	context.put("status","UserResitration");
                       	String path=TurbineServlet.getRealPath("/OnlineUsers");
                       	Vector entry=new Vector();
                       	File xmlfile= new File(path+"/OnlineUser.xml");
                       	if(xmlfile.exists())
                       	{
                       	        TopicMetaDataXmlReader topicmetadata=null;
                               	Vector list=new Vector();
                               	topicmetadata=new TopicMetaDataXmlReader(path+"/OnlineUser.xml");
                               	list=topicmetadata.getOnlineUserDetails();
                               	if(list!=null)
                               	{
                                       	for(int i=0;i<list.size();i++)
                                       	{
					   String flag=((CourseUserDetail) list.elementAt(i)).getFlag();
					   //following check added by Priyanka 
					   if(flag.equals("1"))
					   {
                                       	        String uname=((CourseUserDetail) list.elementAt(i)).getLoginName();
                                       	        String passwd=((CourseUserDetail) list.elementAt(i)).getActive();
                                       	        String email=((CourseUserDetail) list.elementAt(i)).getEmail();
						String fname=((CourseUserDetail) list.elementAt(i)).getInstructorName();
						String lname=((CourseUserDetail) list.elementAt(i)).getUserName();
						String orgtn=((CourseUserDetail) list.elementAt(i)).getDept();
                                       	        String gname=((CourseUserDetail) list.elementAt(i)).getGroupName();
                                       	        String roleName=((CourseUserDetail) list.elementAt(i)).getRoleName();
                                       	        String rollno=((CourseUserDetail) list.elementAt(i)).getRollNo();
                                       	        String program=((CourseUserDetail) list.elementAt(i)).getPrgCode();
						if(!program.equals(""))
							prgname = InstituteIdUtil.getPrgName(program);
						else
							prgname="";
						if(gname.endsWith(instituteid)){
                                       	        DbDetail dbDetail= new DbDetail();
                                       	        dbDetail.setSender(uname);
                                       	        dbDetail.setPDate(passwd);
                                       	        dbDetail.setMSubject(email);
						dbDetail.setExpiryDate(fname);
						dbDetail.setPermission(lname);
						dbDetail.setGrpmgmtType(orgtn);
                                       	        dbDetail.setStatus(gname);
                                       	        dbDetail.setMsgID(roleName);
                                       	        dbDetail.setRollno(rollno);
                                       	        dbDetail.setPrgCode(prgname);
                                       	        entry.addElement(dbDetail);
						//ErrorDumpUtil.ErrorLog("entry in screen file------>"+entry);
						}
					   }
				
                                       	}
                               	}

                                else {
                                   	xmlfile.delete();
                               	}
                        }
 			else{
                              
                               	data.setMessage(MultilingualUtil.ConvertedString("online_msg8",user.getTemp("LangFile").toString()));
//"Sorry, No User Registration till now !!");
                       	}
			context.put("entry",entry);
               	}

                else if(status.equals("CourseRegistration"))
                {
 
               		context.put("status","CourseRegistration");
	                String path=TurbineServlet.getRealPath("/OnlineUsers");
			Vector entry=new Vector();
			File xmlfile= new File(path+"/courses.xml");
	                if(xmlfile.exists())
        	        {
				TopicMetaDataXmlReader topicmetadata=null;
	        	        Vector list=new Vector();
        	        	topicmetadata=new TopicMetaDataXmlReader(path+"/courses.xml");
	        	       	list=topicmetadata.getOnlineCourseDetails();
			
                		if(list!=null)
                        	{
                        		for(int i=0;i<list.size();i++)
                                	{
					   String flag=((CourseUserDetail) list.elementAt(i)).getFlag();
					//following check added by Priyanka
					   if(flag.equals("1"))
					   {
						int instid=((CourseUserDetail) list.elementAt(i)).getInstId();
						String instituteId=Integer.toString(instid);
						if(instituteId.equals(instituteid))
						{
						String gname=((CourseUserDetail) list.elementAt(i)).getGroupName();
						if(!gname.isEmpty())
						gname=gname.replace("&colon",":");
						String cname=((CourseUserDetail) list.elementAt(i)).getCourseName();
			              		String uname=((CourseUserDetail) list.elementAt(i)).getLoginName();
						String orgtn=((CourseUserDetail) list.elementAt(i)).getDept();
                	               		String email=((CourseUserDetail) list.elementAt(i)).getEmail();
                        	       		String fname=((CourseUserDetail) list.elementAt(i)).getInstructorName();
                               			String lname=((CourseUserDetail) list.elementAt(i)).getUserName();
						DbDetail dbDetail= new DbDetail();
        	                                dbDetail.setSender(gname);
                	                        dbDetail.setPDate(cname);
                        	                dbDetail.setMSubject(uname);
						dbDetail.setGrpmgmtType(orgtn);
                                	        dbDetail.setStatus(email);
                                                dbDetail.setMsgID(fname);
                                       	        dbDetail.setPermission(lname);
                                        	entry.addElement(dbDetail);
						}
					   }
					}
				}
				else{
				xmlfile.delete();
				}
			
			}
			else { 
				data.setMessage(MultilingualUtil.ConvertedString("online_msg7",user.getTemp("LangFile").toString()));
			}
			context.put("entry",entry);        			      	                					
		}
	}//try end
	catch(Exception e) { 
		ErrorDumpUtil.ErrorLog("The error in Online registartion reading file" +e);
		data.setMessage("Please see Error log or Contact to administrator");
	}
	}//end method
}//end class

