package org.iitk.brihaspati.modules.screens.call.OnLine_Rgtn;

/*
 * @(#) OnlineCourseApproval.java
 *
 *  Copyright (c) 2010 ETRG,IIT Kanpur.
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
import org.apache.turbine.util.RunData;
import org.apache.torque.util.Criteria;
import org.apache.velocity.context.Context;
import java.util.List;
import java.util.Vector;
import org.apache.turbine.om.security.User;
import org.apache.turbine.util.parser.ParameterParser;
import org.apache.turbine.services.servlet.TurbineServlet;
import org.iitk.brihaspati.modules.utils.TopicMetaDataXmlReader;
import org.iitk.brihaspati.modules.utils.CourseUserDetail;
import org.iitk.brihaspati.modules.utils.DbDetail;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.iitk.brihaspati.modules.utils.MultilingualUtil;
import org.iitk.brihaspati.modules.screens.call.SecureScreen_Institute_Admin;
import org.apache.turbine.modules.screens.VelocityScreen;

/**
 * This class contain the list of request of online course registration.institute 
 * admin accept or reject the request.
 
 * @author  <a href="mailto:sharad23nov@yahoo.com">Sharad Singh</a>
 */

//class for viewing to approve/reject online course request by institute admin

public class OnlineCourseApproval extends SecureScreen_Institute_Admin
{
        public void doBuildTemplate(RunData data, Context context)
        {
		try
		{	
			ParameterParser pp=data.getParameters();
			String lang=pp.getString("lang","english");
	                context.put("lang",lang);
			User user=data.getUser();
			//get online course request file stored in OnlineUsers directory 
                        String path=TurbineServlet.getRealPath("/OnlineUsers");
                        Vector entry=new Vector();
                        File xmlfile= new File(path+"/courses.xml");
			String instituteid=pp.getString("instituteid");
			context.put("instituteid",instituteid);
                        if(xmlfile.exists())
                        {
                                TopicMetaDataXmlReader topicmetadata=null;
                                Vector list=new Vector();
				//read the file and get online courses request 
                                topicmetadata=new TopicMetaDataXmlReader(path+"/courses.xml");
                                list=topicmetadata.getOnlineCourseDetails();
				
                                if(list!=null)
                                {
                                        for(int i=0;i<list.size();i++)
                                        {
						String flag=((CourseUserDetail) list.elementAt(i)).getFlag();
						if(flag.equals("1"))
	                                        {
                                                	int instid=((CourseUserDetail) list.elementAt(i)).getInstId();
							//get the requested online courses in an institute
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
        	}//try end
        	catch(Exception e) {
                	ErrorDumpUtil.ErrorLog("The error in Online registartion reading file" +e);
                	data.setMessage("Please see Error log or Contact to administrator");
        	}
	
	}
}

