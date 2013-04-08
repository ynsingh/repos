package org.iitk.brihaspati.modules.screens.call.Notice_User;

/*
 * @(#)DeletePosted.java	
 *
 *  Copyright (c) 2005,2009, 2010 ETRG,IIT Kanpur. 
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
 * In this class,We deleted self posted notices
 * @author <a href="mailto:madhavi_mungole@hotmail.com ">Madhavi Mungole</a>
 * @author <a href="mailto:awadhesh_trivedi">Awadhesh Kumar Trivedi</a>
 * @author <a href="mailto:shaistashekh@hotmail.com">Shaista Bano</a>
 * @ modified date: 26-07-2010, 13-Oct-2010 (Shaista)
 */

import org.iitk.brihaspati.modules.utils.UserUtil;
import org.iitk.brihaspati.modules.utils.MultilingualUtil;
import org.iitk.brihaspati.modules.utils.GroupUtil;
import org.iitk.brihaspati.modules.utils.CourseUtil;
import org.iitk.brihaspati.modules.utils.ModuleTimeThread;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.iitk.brihaspati.modules.utils.Notice_SRDetail;
import org.apache.turbine.util.RunData;
import org.apache.turbine.util.parser.ParameterParser;
import org.apache.velocity.context.Context;
import org.apache.turbine.om.security.User;
import org.apache.turbine.om.security.TurbineUser;
import org.apache.turbine.om.security.peer.TurbineUserPeer;
import org.apache.torque.util.Criteria;
import org.iitk.brihaspati.om.NoticeSendPeer;
import org.iitk.brihaspati.om.NoticeSend;
import org.iitk.brihaspati.modules.screens.call.SecureScreen_Instructor;
import java.util.Vector;
import java.util.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;

public class DeletePosted extends SecureScreen_Instructor
{
	public void doBuildTemplate( RunData data, Context context ){
		try{
			/**
			 * Retrevies the course details and user details
			 */

			Vector noticeSentRec=new Vector();
			
			/**
			 * @param user Getting User object
			 */
			User user=data.getUser();
			/**
			 * @param crit Instance of Criteria
			 */
			
			Criteria crit=new Criteria();

			/**
			 * @param courseid getting group name from parameter parser which is sent by link
			 * @param cName getting course name according to groupname
			 * Course name is seta  in context to display
			 */
			
		
			//String cName=(String)(user.getTemp("course_name"));
			String cName ="", courseid ="";
			ParameterParser pp = data.getParameters();
			courseid = pp.getString("courseId","");
			String userInCourse=(String)(user.getTemp("course_id"));
			//ErrorDumpUtil.ErrorLog("\n\n\n\n\nfrom delete Posted  courseId="+courseid +"\n userInCourse="+userInCourse);
			if( userInCourse!=null && !userInCourse.equals("") && courseid.equals(""))
			{
				courseid = userInCourse;
				cName=(String)user.getTemp("course_name");
			}
			else
			{
	                        user.setTemp("course_id",courseid);
				cName=CourseUtil.getCourseName(courseid);
				user.setTemp("course_name",cName);
			}

			/**
			 * @param Username getting user name 
			 * @param name Selecting  list according to user name
			 * @param fname getting first name from the list
			 * @param lname getting last name from the list
			 */

			String Username=user.getName();
			crit.add(TurbineUserPeer.USERNAME,Username);
			List name=TurbineUserPeer.doSelect(crit);
			String fname=((TurbineUser)(name.get(0))).getFirstName();
			String lname=((TurbineUser)(name.get(0))).getLastName();

			context.put("loginName",Username);
			context.put("username",Username);
			context.put("firstname",fname);
			context.put("lastname",lname);
			
			/**
			 * @param user_id getting user id by giving username
			 * @param group_id getting group id by giving groupname
			 */
			int user_id=UserUtil.getUID(Username);
			int group_id=GroupUtil.getGID(courseid);
			
			
			/**
			 * @param tdcolor setting the color for current feature 
			 */
			context.put("tdcolor",data.getParameters().getString("count",""));
			context.put("tdcolor1",data.getParameters().getString("countTemp",""));
			/**
			 * Retreives the notice details from MESSAGE_SEND table
			 * according to group_id and user id to display the list of Notices to delete
			 * @param messageDetails having the details of notices towhome it is send
			 * if{ there is no notice shows the message }
			 * else {
			 * all the notice detail is set in Notice_SRDetail (which is a util class) Object 
			 * add this Notice_SRDetail (which is a util class) Object in a vector named, noticeSentRec 
			 * if {vector named, noticeSentRec is empty it shows proper message}
			 * else{ vector named, noticeSentRec, vector size & course name is put in context }
			 *
			 * } else close
			 */

			crit=new Criteria();
			crit.add(NoticeSendPeer.USER_ID,Integer.toString(user_id));
			crit.and(NoticeSendPeer.GROUP_ID,Integer.toString(group_id));
			crit.addDescendingOrderByColumn(NoticeSendPeer.POST_TIME);
			List messageDetails=NoticeSendPeer.doSelect(crit);

			String LangFile=data.getUser().getTemp("LangFile").toString();
			if(messageDetails.size() != 0 || messageDetails != null) {
				for(int i=0;i<messageDetails.size();i++)
				{
					Notice_SRDetail noticeSRDetails=new Notice_SRDetail();
                                        NoticeSend element1=(NoticeSend)(messageDetails.get(i));
                                        
					StringBuffer sb=new StringBuffer(element1.getNoticeSubject());
                                        //sb.deleteCharAt(0);

                                        noticeSRDetails.setMsgSubject(new String(sb));
                                        noticeSRDetails.setPostTime(element1.getPostTime());
                                        noticeSRDetails.setMsgId(Integer.toString(element1.getNoticeId()));
                                        noticeSentRec.add(noticeSRDetails);
				}

				if(noticeSentRec.size()!=0)
					context.put("msgDetail",noticeSentRec);
				else
					data.setMessage(MultilingualUtil.ConvertedString("notice_msg4",LangFile));
	
			}
			else
			{
				data.setMessage(MultilingualUtil.ConvertedString("notice_msg4",LangFile));
			}
			context.put("Mas_size",Integer.toString(noticeSentRec.size()));
			context.put("courseId",courseid);
			context.put("Course",cName);
			String desc=data.getParameters().getString("desc","");
                	String topicDesc="";
	
			/**
			 * @param msg_id String, gets message id according to notice id
			 * @param desc String
			 * set desc in context to display
			 * bydefault it is null else have String "Notice_Des"
			 * if { desc is equal "Notice_Des" 
			 * Gets Notice object according to message id
			 * Gets notice subject to show heading
			 * Read Notice_Msg.txt for getting message, stored  according to message_id
			 * put message in context
			 * }
			 * else it would not run
			 */

			if(desc.equals("Notice_Des"))
			{
				String msg_id=data.getParameters().getString("notice_id","");
                		context.put("desc",desc);
				crit=new Criteria();
                        	crit.add(NoticeSendPeer.NOTICE_ID,msg_id);
				List sub=NoticeSendPeer.doSelect(crit);
				StringBuffer Notice_sub=new StringBuffer(((NoticeSend)(sub.get(0))).getNoticeSubject());
				//Notice_sub.deleteCharAt(0);
                		context.put("Notice_sub",Notice_sub);
                		/**
                 		* Getting the actual path where the Mail is stored
                 		* @return String
                 		*/
                		String filePath=data.getServletContext().getRealPath("/Courses")+"/"+courseid+"/Notice_Msg.txt";
                		/**
                 		* read the file using XML descriptior
                 		*  @try and catch Identifies statements that user want to monitor
                 		*  and catch for exceptoin.
                 		*/
                        		int i =0;
                        		int start=0;
                        		int stop=0;
                        		String str[] = new String[10000];
                		try
                		{
                        		try{
                        		BufferedReader br=new BufferedReader(new FileReader (filePath));
                        		while ((str[i]=br.readLine()) != null)
                        		{
                                		if (str[i].equals("<"+msg_id+">")) {
                                		start = i;      }
                                		else if(str[i].equals("</"+msg_id+">"))
                                		{
                                        		stop = i;

                                		}
                                        	i= i +1;
                                	}
                                	br.close();
					}
                        		catch(IOException e)
                        		{
                                		e.printStackTrace();
                        		}
				}
				catch(Exception ex)
				{
					data.setMessage("The Error in reading file "+ex);
				}
                        	for(int j=start+1;j<stop;j++)
                        	{
                                	topicDesc=topicDesc+ "\n"+str[j];
                        	}
                		/**
                 		* here comes the code to view attachment with the message
                 		*/
                		context.put("message",topicDesc);
			}
				/*
	                         *method for how much time user spend in this page.
        	                 */
				 String Role = (String)user.getTemp("role");
				 if((Role.equals("student")) || (Role.equals("instructor")) || (Role.equals("teacher_assistant")))
                        	{
					int eid=0;
					ModuleTimeThread.getController().CourseTimeSystem(user_id,eid);
                        	}


                	
		}
		catch(Exception e)
		{
			data.setMessage("The Error in "+e);
		}
	}
}

