package org.iitk.brihaspati.modules.screens.call.Assignment;

/*
 * @(#)ASS_subm.java
 *
 *  Copyright (c) 2005-2010 ETRG,IIT Kanpur.
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
import java.util.Date;
import java.util.Vector;
import java.util.List;
import org.iitk.brihaspati.om.Assignment;
import org.iitk.brihaspati.om.AssignmentPeer;
import org.iitk.brihaspati.om.News;
import org.iitk.brihaspati.om.NewsPeer;
import org.iitk.brihaspati.modules.utils.MultilingualUtil;
import org.iitk.brihaspati.modules.utils.ExpiryUtil;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.iitk.brihaspati.modules.utils.FileEntry;
import org.iitk.brihaspati.modules.utils.YearListUtil;
import org.iitk.brihaspati.modules.utils.TopicMetaDataXmlReader;
import org.apache.torque.util.Criteria;
import org.apache.velocity.context.Context;
import org.apache.turbine.util.RunData;
import org.apache.turbine.om.security.User;
import org.apache.turbine.util.parser.ParameterParser;
import org.apache.turbine.services.servlet.TurbineServlet;
import org.iitk.brihaspati.modules.screens.call.SecureScreen;
import org.iitk.brihaspati.modules.utils.UserUtil;
import org.iitk.brihaspati.modules.utils.GroupUtil;
//import org.iitk.brihaspati.modules.utils.CourseTimeUtil;
//import org.iitk.brihaspati.modules.utils.ModuleTimeUtil;
import org.iitk.brihaspati.modules.utils.MailNotificationThread;
/**
 *   This class contains code for all discussions in workgroup
 *   Compose a discussion and reply.
 *   @author  <a href="arvindjss17@yahoo.co.in">Arvind Pal</a>
 *   @author  <a href="sunil.singh6094@gmail.com">Sunil Kumar Pal</a>
 *   @author  <a href="smita37uiet@gmail.com">Smita Pal</a>
 */

public class  ASS_subm  extends  SecureScreen

{
	/**
         * @param data RunData instance
         * @param context Context instance
         * @return nothing
         * @try and catch Identifies statements that user want to monitor
         * and catch for exceptoin.
        */
	
        public void doBuildTemplate(RunData data, Context context)
        {
                try
                {
			User user=data.getUser();
                        ParameterParser pp=data.getParameters();
                        String courseid=(String)user.getTemp("course_id");
			/**
                        * this mode use for update assignment
                        */
			String mode=pp.getString("mode","");
                        context.put("mode",mode);
			
			/**
                        * Get courseid  and coursename for the user currently logged in
                        *Put it in the context for Using in templates
                        * @see UserUtil in Util.
                        */
			
			context.put("coursename",((String)user.getTemp("course_name")));
                        Date curDate=new Date();
                        long longCurDate= curDate.getTime();
                        String Role = (String)user.getTemp("role");
                        context.put("user_role",Role);
                        context.put("tdcolor",pp.getString("count",""));
			//topic
                        String Asmt_topic=pp.getString("topicAsmt","");
			/**
			 *Time calculaion for how long user use this page.
			 */
			 int uid=UserUtil.getUID(user.getName());
			 if((Role.equals("student")) || (Role.equals("instructor")))
                         {
				MailNotificationThread.getController().CourseTimeSystem(uid);
                                //CourseTimeUtil.getCalculation(uid);
                                //ModuleTimeUtil.getModuleCalculation(uid);
                         }

			/**
                        * Select all the information according to GroupName
                        * from the Assignment table
                        */
                        
			Criteria crit1=new Criteria();
                        crit1.add(AssignmentPeer.GROUP_NAME,courseid);
                        List u=AssignmentPeer.doSelect(crit1);
			ErrorDumpUtil.ErrorLog("Size of Assignment list -ASS_sub.java"+u.size());
                        boolean view=false,postAns=false,perdate=false;
                        if( u.size() != 0 )
                        {
                                for(int i=0;i<u.size();i++)
                                {
                                        Assignment element=(Assignment)(u.get(i));
                                        Date str1=(element.getDueDate());
                                        Date str2=(element.getPerDate());
                                        long longCurDate2= str2.getTime();
                                        long longCurDate1= str1.getTime();
                                        long coursedate=(longCurDate1-longCurDate)/(24*3600*1000);
                                        long perdate1=(longCurDate2-longCurDate)/(24*3600*1000);
                                        String Assign=TurbineServlet.getRealPath("/Courses"+"/"+courseid+"/Assignment");
                                        Assign=Assign+"/"+(element.getAssignId());

                                        /**
                                        * View Assignment
                                        */
					
                                        if(coursedate>=0 || coursedate<0)
                                        view=true;
                                        /**
                                        * post Ans,Post Grade,
					*/
                                        if(Role.equals("student"))
                                        {
                                                if(coursedate>=0)
                                                        postAns=true;
                                        }
                                        if(Role.equals("instructor"))
                                        {
                                                if(coursedate<0)
                                                        postAns=true;
                                        }

                                        /**
                                        *  RePost Permissions
                                        */
						
                                        if(Role.equals("student") && perdate != true )
                                        {
                                        if(perdate1>=0 )
                                                perdate=true;
                                        else
                                        {

                                        /**
                                        *show the particular student Repost Permissions
                                        *Show read the pemissions .xml file
                                        */
                                        int curdate1=Integer.parseInt(ExpiryUtil.getCurrentDate(""));
					File f2= new File(Assign+"/__permission.xml");
					if(f2.exists())
					{
	                                        try{
                                                	TopicMetaDataXmlReader topicmetadata=null;
                                                	Vector Assignmentlist1=new Vector();
                                                	topicmetadata=new TopicMetaDataXmlReader(Assign+"/__permission.xml"); 
							Assignmentlist1=topicmetadata.getAssignmentDetails();
                                                        if(Assignmentlist1!=null)
                                                        {       for(int grade=0;grade<Assignmentlist1.size();grade++)
								{
                                                                        String Duedate =((FileEntry) Assignmentlist1.elementAt(grade)).getDuedate();
                                                                        String  fileName =((FileEntry) Assignmentlist1.elementAt(grade)).getfileName();
									Duedate=Duedate.replaceAll("-","");
		                                                        int date4=Integer.parseInt(Duedate);
                                                                        if(date4>=curdate1)
                                                                        {
                                                                                if(fileName.equals(data.getUser().getName()))
                                                                                               perdate=true;
                                                                        }
                                                                } //end for
                                                        }
                                                } //end if
                                                catch(Exception e){ data.setMessage("Exception in [Assignment] !!"); }
					}
                                        }/* else */  } //if
                                        context.put("u",view); context.put("u1",postAns); context.put("u2",perdate);
                                }  //for
                        } //for
                        else {
				String LangFile=data.getUser().getTemp("LangFile").toString();
				data.setMessage(MultilingualUtil.ConvertedString("assignment_msg14",LangFile));
				//data.setMessage("There are no Assignment in this course !!");
			}
		////////////////////////////////////////////////////////////AssView Screen add by jai
			if(Role.equals("instructor")){
			Criteria crit=new Criteria();
                        crit.add(AssignmentPeer.GROUP_NAME,courseid);
                        List lst=AssignmentPeer.doSelect(crit);
			Vector w=new Vector();
                        for(int i=0;i<lst.size();i++)
                        {
                                Assignment element=(Assignment)(u.get(i));
                                String str2=(element.getTopicName());
                                w.add(str2);
                        }

                        context.put("allTopics",w);

                        /**
                        *  Retrieves the current date of the System
                        *  @see ExpiryUtil in utils
                        */

			String cdate="";
   			if(mode.equals("Update")) {
                        	lst.clear();
                        	String tcid=pp.getString("cid","");
				crit1=new Criteria();
        	                crit1.add(AssignmentPeer.GROUP_NAME,courseid);
        	                crit1.add(AssignmentPeer.ASSIGN_ID,tcid);
	                        List uu=AssignmentPeer.doSelect(crit1);
				if( uu.size() != 0 )
                        	{
                                	for(int i=0;i<uu.size();i++)
                                	{
                                        	Assignment element=(Assignment)(uu.get(i));
	                                        String topic=(element.getTopicName());
	                                        cdate=(element.getDueDate()).toString();
						cdate=cdate.substring(0,10);
               	        			cdate=cdate.replace("-","");
	                                        int mmarks=(element.getGrade());
	                                        String assid=(element.getAssignId());
						context.put("topic",topic);
						context.put("maxmarks",mmarks);
		                                context.put("cid",assid);
						
					}
				}

				/**
                                * Break topic name if same name not exist
                                */

                                String topicname=pp.getString("DB_subject1","");
                                context.put("DB_subject",topicname);				
			}else{
				cdate=ExpiryUtil.getCurrentDate("");
                      	}//mode update close

                        /**
                         * Get the current day from the currentdate
                         */

                        context.put("cdays",cdate.substring(6,8));
                        context.put("cmonth",cdate.substring(4,6));
                        context.put("cyear",cdate.substring(0,4));
                        context.put("year_list",YearListUtil.getYearList());
			}//role instructor close
                }
                catch(Exception e) { data.setMessage("Exception screens [Assignment]" + e);   }
        }
}
