package org.iitk.brihaspati.modules.utils;


/*@(#)OnlineExamSystemMail.java
 *  Copyright (c) 2013 ETRG,IIT Kanpur. http://www.iitk.ac.in/
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
 */

import java.util.List;
import java.util.Vector;
import java.util.Properties;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.apache.turbine.services.servlet.TurbineServlet;

/** This class is responsible for sending mail to student
  *about quiz information(Quiz time,Quiz is for practice or not)
  *@author <a href="mailto:jaivirpal@gmail.com">Jaivir Singh</a>
  *@author <a href="mailto:palseema30@gmail.com">Manorama Pal</a>
  */
public class OnlineExamSystemMail{

	/**This method is responsible to send the mail to student.
	  *@see MailNotificationThread Util
	  *@param courseID String
	  *@param username String
	  *@param mailfor String
	  *@param quizdate String
	  *@param quiztime String
	  *@param quizname String
	  *@param securitykey String
	  *@param LangFile String
	  */	

	public static String SendMail(String courseID,String username,String mailfor,String quizdate,String quiztime,String quizname,String securitykey,String LangFile){
		String Mail_msg=null;
		try{
			String srvrPort=TurbineServlet.getServerPort();
			String Crsname=CourseUtil.getCourseName(courseID);
			String subject="",message="",msgDear="",msgRegard="";
			Properties pr =MailNotification.uploadingPropertiesFile(TurbineServlet.getRealPath("/WEB-INF/conf/brihaspati.properties"));
			if(mailfor.equals("announce")){
				if(srvrPort.equals("8080")){
                        		subject = MailNotification.subjectFormate("studentquiz", "", pr );
                        		message = MailNotification.getQuizMessage("studentquiz",Crsname,quizdate,quiztime,"","",username,pr);
				}
				else{
                        		subject = MailNotification.subjectFormate("studentquizhttps", "", pr );
                        		message = MailNotification.getQuizMessage("studentquizhttps",Crsname,quizdate,quiztime,"","",username,pr);
				}
			}
			if(mailfor.equals("practice")){
				if(srvrPort.equals("8080")){	
                        		subject = MailNotification.subjectFormate("studentpractice", "", pr );
                        		message = MailNotification.getMessage("studentpractice",Crsname,"",username,"",pr);
				}
                        	else{
					subject = MailNotification.subjectFormate("studentpracticehttps", "", pr );
                        		message = MailNotification.getMessage("studentpracticehttps",Crsname,"",username,"",pr);
				}
			}
			if(srvrPort.equals("8080")){	
                        	msgDear = pr.getProperty("brihaspati.Mailnotification.newUser.msgDear");
                        	msgRegard=pr.getProperty("brihaspati.Mailnotification.newUser.msgRegard");
			}
                       	else{
                        	msgDear = pr.getProperty("brihaspati.Mailnotification.newUserhttps.msgDear");
                        	msgRegard=pr.getProperty("brihaspati.Mailnotification.newUserhttps.msgRegard");
			}	
                        msgRegard = MailNotification.replaceServerPort(msgRegard);
                        int gid=GroupUtil.getGID(courseID);
                        Vector stuuid=UserGroupRoleUtil.getUID(gid,3);
                        for(int a=0;a<stuuid.size();a++)
                        {
                                int stuid=Integer.parseInt((stuuid.get(a)).toString());
                                String UName=UserUtil.getFullName(stuid);
                                String email=UserUtil.getEmail(stuid);
                                String newmsgDear = MailNotification.getMessage_new(msgDear, "","", "",UName);
                        	Mail_msg =MailNotificationThread.getController().set_Message(message,newmsgDear,msgRegard,"",email,subject,"",LangFile);
			}
		}
		catch(Exception e){ErrorDumpUtil.ErrorLog("The Exception in OnlineExamSystemMail util method(SendMail)"+e);}
		return Mail_msg;
        }
}

