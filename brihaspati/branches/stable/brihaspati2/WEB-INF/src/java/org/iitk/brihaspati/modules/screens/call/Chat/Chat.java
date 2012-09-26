package org.iitk.brihaspati.modules.screens.call.Chat;

/*
 * @(#) Chat.java
 *
 *  Copyright (c) 2005, 2009, 2010, 2011 ETRG,IIT Kanpur.
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
//java class
import java.util.List;
import java.util.Vector;
import java.io.File;
import java.util.Iterator;

//Apache classes
import org.apache.torque.util.Criteria;
import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;
//Brihaspati classes
import org.iitk.brihaspati.modules.screens.call.SecureScreen;
import org.iitk.brihaspati.om.UserConfiguration;
import org.iitk.brihaspati.om.UserConfigurationPeer;
import org.iitk.brihaspati.om.DbReceivePeer;
import org.iitk.brihaspati.modules.utils.UserUtil;
import org.iitk.brihaspati.modules.utils.UserGroupRoleUtil;
import org.iitk.brihaspati.modules.utils.UserUtil;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
//import org.iitk.brihaspati.modules.utils.CourseTimeUtil;
//import org.iitk.brihaspati.modules.utils.ModuleTimeUtil;
import org.iitk.brihaspati.modules.utils.MailNotificationThread;
import com.workingdogs.village.Record;
import org.iitk.brihaspati.modules.utils.GroupUtil;
import org.apache.turbine.om.security.User;
import org.iitk.brihaspati.modules.utils.CourseUserDetail;
import org.iitk.brihaspati.modules.utils.Notification;
import org.iitk.brihaspati.modules.utils.NoticeUnreadMsg;
/**
 * In this class, Get all details for Chat applet server
 *
 * @author <a href="mailto:awadhesh_trivedi@yahoo.co.in">Awadhesh Kumar Trivedi</a>
 * @author <a href="mailto:shaistashekh@hotmail.com">Shaista Bano</a>
 * @author <a href="mailto:shaistashekh@hotmail.com">Sunil Kumar</a>
 * @author <a href="mailto:dewanshu.sisaudiya17@gmail.com">Dewanshu Singh Sisaudiya</a>
 * @modified date: 10-08-2010 (Shaista), 26-08-2011(Sunil kr) 
 * @modified date: 10-02-2011, 04-04-2011 (Shaista)
* @modified date: 30-01-2012 (Dewanshu Singh Sisaudiya) 
*/

public class Chat extends SecureScreen
{
    /**
     * Place all the data object in the context
     * for use in the template.
     */
    public void doBuildTemplate( RunData data, Context context )

    {
	  try{
				/**
		* Get UserName, Passwd, CourseId, serverName, Base Path, mode
		*/
          	String uname=data.getUser().getName();
	  	String pword=data.getUser().getPassword();
		String cid ="";
		String mode1=data.getParameters().getString("mode","");
		String mode2=data.getParameters().getString("mode2","");
	        //context.put("mode2",mode2);
		String stat=data.getParameters().getString("mode1","");
		User user=data.getUser();
		//ErrorDumpUtil.ErrorLog("mode2===="+stat+"\n\n\n mode222="+mode2);
	
		/**
		 * if { mode is general then room name is General} 
		   else {
			if { status is equal to groupmanagemnt so room name is group name to chat group wise}
			else{ room name is groupid to chat course wise}
		    }
		 * if {role is instructor then save the chat in a .txt file to play back } else {chat doesnt save in .txt file}
		 * if{ Saved file is exist list of saved file is sent to babylon chat}
		 * Sending babylon path to save & play back a file.
		 */
		if(mode1.equals("general"))
		{
			cid="General";
                	context.put("mode",mode1);
		}
		else if(mode2.equals("instituteWise"))
		{
			cid = data.getParameters().getString("grpName","");
                	//context.put("mode",mode1);
                	context.put("mode",mode2);
			context.put("grpName",cid);
			
		}
		else {
			if (stat.equals("grpmgmt"))
			{
				context.put("grpName",data.getParameters().getString("val1",""));
				context.put("mode",stat);
			}
	  		cid=data.getUser().getTemp("course_id").toString();
                	context.put("course",data.getUser().getTemp("course_name").toString());
		}
		context.put("tdcolor",data.getParameters().getString("count","2"));
	  	String hostIP=data.getServerName();
	  	String codeBase=data.getServerScheme()+"://"+hostIP+":"+data.getServerPort()+data.getContextPath()+"/babylon/";
		int uid=UserUtil.getUID(uname);

/**
		Criteria crt=new Criteria();
                crt.add(UserConfigurationPeer.USER_ID,uid);
                List qu=UserConfigurationPeer.doSelect(crt);
                int qid=0;
                if(qu != null && qu.size() > 0){
                        for(int i=0;i<qu.size();i++)
                        {
                                UserConfiguration uc=(UserConfiguration)qu.get(i);
                                context.put("conf",uc.getListConfiguration());
                                context.put("TaskConf",uc.getTaskConfiguration());
                                ErrorDumpUtil.ErrorLog("TaskConf ..."+uc.getTaskConfiguration());
                                ErrorDumpUtil.ErrorLog("Configration list ..."+uc.getListConfiguration());
                        }
		}
		else
                	{
				context.put("course",data.getUser().getTemp("course_name").toString());
                                setTemplate(data,"call,Chat,Chat.vm");
                                data.setMessage("Please insert Task Configuration time in days, in your profile !!");
                        }
**/
			Vector Instructor_Role=UserGroupRoleUtil.getGID(uid,2);
                	// check for Student Role
                        Vector Student_Role=UserGroupRoleUtil.getGID(uid,3);

                        if(Instructor_Role != null && Instructor_Role.size()>0)
                                context.put("role","instructor");
                        if(Student_Role != null && Student_Role.size()>0)
                                context.put("role","student");

			String temp="";
                        File f= new File(data.getServletContext().getRealPath("/babylon/")+"/"+cid);
			if(f.exists()){
                        	String filel [] = f.list();
                        	for( int i=0; i< filel.length; i++)
                                	temp=filel[i]+"__"+temp;
			}
        	        context.put("fullfile",temp);
	  		context.put("serverName",hostIP);
		  	context.put("codeBase",codeBase);
		  	context.put("password",pword);
	  		context.put("chatRoom",cid);
		  	context.put("username",uname);
		  	//context.put("course",data.getUser().getTemp("course_name").toString());
			context.put("babylonPath",data.getServletContext().getRealPath("/babylon/"));
		/*
		 *method for how much time user spend in this page.
		 */
				
		if((!mode1.equals("general")) || (!mode2.equals("instituteWise"))){
			String Role=(String)data.getUser().getTemp("role");
			if((Role.equals("student")) || (Role.equals("instructor")))
			{
				//CourseTimeUtil.getCalculation(uid);
				//ModuleTimeUtil.getModuleCalculation(uid);
				int eid=0;
				MailNotificationThread.getController().CourseTimeSystem(uid,eid);
			}
			
			///////////////////////////////////
                        String user_name = user.getName();
			 int user_id = UserUtil.getUID(user_name);
                        String dir=(String)user.getTemp("course_id");
                        String stats=data.getParameters().getString("stats","");
                       //String mode2=data.getParameters().getString("mode2","");
                        String dev = Notification.DisBoardNf(user_name,dir,stats,mode2);
                        context.put("unreadm",dev);
			int role_id=0;
                        if(Role.equals("instructor"))
                                role_id=2;
                        else if(Role.equals("student"))
                                role_id=3;
                        Vector unreadMsg=NoticeUnreadMsg.getUnreadNotice(user_id,role_id,dir);
                        context.put("unreadMsg",unreadMsg);
                        ErrorDumpUtil.ErrorLog("===============================unread"+unreadMsg);
	                ///////////////////////////////////

		}
	}
	catch(Exception ex)
	{
		context.put("course",data.getUser().getTemp("course_name").toString());
		data.setMessage("The error in chat !!"+ex);
		}
    }	


}

