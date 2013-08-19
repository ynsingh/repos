package org.iitk.brihaspati.modules.actions;

/**
 * @(#)ParentRegistration.java
 *
 *  Copyright (c) 2013 ETRG,IIT Kanpur.
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


//JDK
import java.io.File;
import java.io.FileWriter;
import java.sql.Date;
import java.util.Vector;
import java.util.List;
import java.util.StringTokenizer;
import java.util.ListIterator;
import com.workingdogs.village.Record;
//Turbine
import org.apache.torque.util.Criteria;
import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;
import org.apache.turbine.om.security.User;
import org.apache.turbine.util.parser.ParameterParser;
//Brihaspati

import org.iitk.brihaspati.om.ParentInfo;
import org.iitk.brihaspati.om.TurbineUser;
import org.iitk.brihaspati.om.TurbineUserPeer;
import org.iitk.brihaspati.om.NoticeSendPeer;
import org.iitk.brihaspati.om.NoticeReceive;
import org.iitk.brihaspati.om.NoticeReceivePeer;
import org.iitk.brihaspati.om.ParentInfoPeer;
import org.iitk.brihaspati.om.TelephoneDirectory;
import org.iitk.brihaspati.om.TelephoneDirectoryPeer;
import org.iitk.brihaspati.modules.utils.UserGroupRoleUtil;
import org.iitk.brihaspati.modules.utils.GroupUtil;
import org.iitk.brihaspati.modules.utils.MailNotification;
import org.iitk.brihaspati.modules.utils.MailNotificationThread;
import org.iitk.brihaspati.modules.utils.StudentInstructorMAP;
import org.iitk.brihaspati.modules.utils.UserManagement;
import org.iitk.brihaspati.modules.utils.MultilingualUtil;
import org.iitk.brihaspati.modules.utils.ExpiryUtil;
import org.iitk.brihaspati.modules.utils.CourseUtil;
import org.iitk.brihaspati.modules.utils.XMLWriter_InstituteRegistration;
import org.iitk.brihaspati.modules.utils.UserUtil;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;



/**
 * @author: <a href="mailto:richa.tandon1@gmail.com">Richa Tandon </a>
 */
/**
* class for registration of a new Parent 
* in the brihaspati system
* registration process successfull in the system and email will go to Parent 
* for confirmation.
*/

public class ParentRegistration extends SecureAction_User
{

	public void doPerform(RunData data,Context context) throws Exception
        {
                String action = data.getParameters().getString("actionName","");
                if(action.equals("eventSubmit_doRegister")){
                        ParentRegister(data,context);
		}
		else if(action.equals("eventSubmit_doSendMsg")){
                        doSendMsg(data,context);
                }
                else
                {
                        data.setMessage("Action not found");
                }
        }

	/**method for registeration of a new Parent.
	 *@param rundata (RunData)	   		
	 *@param context (Context)	   		
	 */
	private Log log = LogFactory.getLog(this.getClass());
        private String LangFile=null;
	public void ParentRegister(RunData data, Context context) 
	{
		  try
                {

                        /**
                        * getting property file According to selection of Language in temporary variable
                        * getting the values of first,last names and
                        * configuration parameter.
                        */
                        User user=data.getUser();
                        String serverName=data.getServerName();
                        ParameterParser pp=data.getParameters();
			String language = pp.getString("lang");
			LangFile = MultilingualUtil.LanguageSelectionForScreenMessage(language);
                        String email=pp.getString("EMAIL","");
                        String passwd=pp.getString("PASSWD","");
                        String pemail=pp.getString("email","");
                        String ppasswd=pp.getString("password","");
                        String pfname=pp.getString("fname","");
                        String plname=pp.getString("lname","");
                        String address=pp.getString("address","");
                        String mobileno=pp.getString("mobileno","");
                        int counter=Integer.parseInt(pp.getString("myvalue"));
                        String semail = "null";
                        String studentsemail = "";
			boolean exist;
			String stdnt_id = "";
			List mailid = null;
                        for(int count=0;count<counter;count++){
                                semail = pp.getString("text"+(count+1));
				Criteria mail = new Criteria();
				mail.add(TurbineUserPeer.LOGIN_NAME,semail);
				mailid = TurbineUserPeer.doSelect(mail);
				if(mailid.size()>0){
					stdnt_id = Integer.toString(((TurbineUser)mailid.get(0)).getUserId());
				}
				else
					{data.setMessage(semail+" is not Register with our system"); break;}
				if(StringUtils.isBlank(studentsemail)){
					 studentsemail=stdnt_id;
				}
				else{ 
					studentsemail = studentsemail+"#"+stdnt_id;
				}
                        }
			if(mailid.size()!=0){
				/**
	                        * for creating user profile use UserManagement util.
        	                * @see UserManagement util in utils 
                	        */
                        	String msg=UserManagement.CreateUserProfile(pemail,ppasswd,pfname,plname,"",pemail,"general","parent",LangFile,"","","act");
	                        /*
        	                * The Code is Responsible For inserting data into ParentInfo Table
                	        */
				int parentId = UserUtil.getUID(pemail);
				if(parentId!=-1){
		                        Criteria s = new Criteria();
	        	                s.add(ParentInfoPeer.PARENT_ID,parentId);
        	        	        List lst = ParentInfoPeer.doSelect(s);
                	        	if(lst.size()==0){
	                		        Criteria crit = new Criteria();
        	                		crit.add(ParentInfoPeer.PARENT_ID,Integer.toString(parentId));
		        	                crit.add(ParentInfoPeer.STUDENT_ID,studentsemail);
	        		                ParentInfoPeer.doInsert(crit);
						
						String fullName=UserUtil.getFullName(parentId);
						crit = new Criteria();
						crit.add(TelephoneDirectoryPeer.USER_ID,parentId);
						List UserExist = TelephoneDirectoryPeer.doSelect(crit);
						if(UserExist.size()>0)
						{
							int existParntid = ((TelephoneDirectory)UserExist.get(0)).getId();
							Criteria tele = new Criteria();
							tele.add(TelephoneDirectoryPeer.ID,existParntid);
							tele.add(TelephoneDirectoryPeer.NAME,fullName );
	        	        	                tele.add(TelephoneDirectoryPeer.ADDRESS, address);
	        	                	        tele.add(TelephoneDirectoryPeer.MOBILE_NO, mobileno);
							TelephoneDirectoryPeer.doUpdate(tele);						
						}
						else
						{
							Criteria tele = new Criteria();
                                                        tele.add(TelephoneDirectoryPeer.USER_ID,parentId);
                                                        tele.add(TelephoneDirectoryPeer.MAIL_ID,pemail);
                                                        tele.add(TelephoneDirectoryPeer.NAME,fullName );
                                                        tele.add(TelephoneDirectoryPeer.ADDRESS, address);
                                                        tele.add(TelephoneDirectoryPeer.MOBILE_NO, mobileno);
                                                        TelephoneDirectoryPeer.doInsert(tele);
						}

        	                	}
	        	                data.setMessage(msg);
				}
			}

                }
                catch (Exception ex)
                {
                        data.setMessage("The error in Parent Registration !!"+ex);
                }

	}	
	protected boolean isAuthorized( RunData rundata ) throws Exception
        {
                return true;
        }

	public void doSendMsg(RunData data, Context context)
        {
                try
                {
			String mailMsg = "";
			User user = data.getUser();
			String username=user.getName();
			ParameterParser pp=data.getParameters();
			String LangFile=user.getTemp("LangFile").toString();
			String subject =pp.getString("subject","");
			String Instremail = pp.getString("courseInstrmail","");
			String message = pp.getString("msg_val","");
			if( Instremail != null && Instremail != "") 
				mailMsg =  MailNotificationThread.getController().set_Message(message, "", "", "", Instremail, subject, "", LangFile);
			String msg1=MultilingualUtil.ConvertedString("mail_msg",LangFile);
                        data.setMessage(msg1);
			String Crsname= pp.getString("courseId","");
			String course_id = CourseUtil.getCourseId((Crsname).trim());
			int gid = GroupUtil.getGID(course_id);
			int rec_uid = UserUtil.getUID(Instremail);
			String c_date=ExpiryUtil.getCurrentDate("-");
			Date current_date=Date.valueOf(c_date);
			int notice_expiry=30;
			String E_date=ExpiryUtil.getExpired(c_date,notice_expiry);
			Date Expiry_date=Date.valueOf(E_date);

			int uid = UserUtil.getUID(username);
			Criteria crit=new Criteria();
                        crit.add(NoticeSendPeer.NOTICE_SUBJECT,subject);
                        crit.add(NoticeSendPeer.USER_ID,uid);
                        crit.add(NoticeSendPeer.GROUP_ID,gid);
                        crit.add(NoticeSendPeer.ROLE_ID,9);
                        crit.add(NoticeSendPeer.POST_TIME,current_date);
                        crit.add(NoticeSendPeer.EXPIRY,notice_expiry);
                        crit.add(NoticeSendPeer.EXPIRY_DATE,Expiry_date);
                        NoticeSendPeer.doInsert(crit);

			int msg_id=0;

                                String Query_msgid="SELECT MAX(NOTICE_ID) FROM NOTICE_SEND";
                                List u=NoticeSendPeer.executeQuery(Query_msgid);

                                for(ListIterator j=u.listIterator();j.hasNext();){
                                        Record item=(Record)j.next();
                                        msg_id=item.getValue("MAX(NOTICE_ID)").asInt();
                                }
			String path = data.getServletContext().getRealPath("/Courses")+"/"+course_id+"/";
                        File first = new File(path);
                        first.mkdirs();
                        path = path+"/"+"Notice_Msg.txt";
                        FileWriter fw = new FileWriter(path,true);
                        fw.write("\r\n");
                        fw.write("<" + msg_id + ">");
                        fw.write("\r\n");
                        fw.write(message);
                        fw.write("\r\n"+"</" + msg_id + ">");
                        fw.close();
		
			crit=new Criteria();
                        crit.add(NoticeReceivePeer.NOTICE_ID,msg_id);
                        crit.add(NoticeReceivePeer.RECEIVER_ID,rec_uid);
                        crit.add(NoticeReceivePeer.GROUP_ID,gid);
                        crit.add(NoticeReceivePeer.READ_FLAG,0);
                        NoticeReceivePeer.doInsert(crit);
		}
		catch(Exception ex)
		{
			ErrorDumpUtil.ErrorLog("The error in doSend method!! "+ex);
		}
	}

}//class


