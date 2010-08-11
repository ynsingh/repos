package org.iitk.brihaspati.modules.screens.call.Chat;

/*
 * @(#) Chat.java
 *
 *  Copyright (c) 2005, 2009 ETRG,IIT Kanpur.
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
//Apache classes
import org.apache.torque.util.Criteria;
import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;
//Brihaspati classes
import org.iitk.brihaspati.modules.screens.call.SecureScreen;
import org.iitk.brihaspati.om.UserConfiguration;
import org.iitk.brihaspati.om.UserConfigurationPeer;
import org.iitk.brihaspati.modules.utils.UserUtil;
import org.iitk.brihaspati.modules.utils.UserGroupRoleUtil;
import org.iitk.brihaspati.modules.utils.UserUtil;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;

/**
 * In this class, Get all details for Chat applet server
 *
 * @author <a href="mailto:awadhesh_trivedi@yahoo.co.in">Awadhesh Kumar Trivedi</a>
 * @author <a href="mailto:shaistashekh@hotmail.com">Shaista Bano</a>
 * @modified date: 10-08-2010 (Shaista)
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
		
		/**
		 * if { mode is general then room name is General} else {room name is group name}
		 * if {role is instructor then save the chat in a .txt file to play back } else {chat doesnt save in .txt file}
		 * if{ Saved file is exist list of saved file is sent to babylon chat}
		 * Sending babylon path to save & play back a file.
		 */
		if(mode1.equals("general"))
		{
			cid="General";
			//context.put("course","General");
		}
		else
		{
	  		cid=data.getUser().getTemp("course_id").toString();
                	context.put("course",data.getUser().getTemp("course_name").toString());
		}
		context.put("tdcolor",data.getParameters().getString("count",""));
	  	String hostIP=data.getServerName();
	  	String codeBase=data.getServerScheme()+"://"+hostIP+":"+data.getServerPort()+data.getContextPath()+"/babylon/";
		int uid=UserUtil.getUID(uname);
                context.put("mode",mode1);

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

	}
	catch(Exception ex)
	{
		context.put("course",data.getUser().getTemp("course_name").toString());
		data.setMessage("The error in chat !!"+ex);
	}
          
    }
}

