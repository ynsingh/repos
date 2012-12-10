package org.iitk.brihaspati.modules.actions;

/*
 * @(#)InstructorRegisteration.java	
 *
 *  Copyright (c) 2005, 2010, 2012 ETRG,IIT Kanpur. 
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


import org.apache.velocity.context.Context;
import org.apache.turbine.util.RunData;
import org.apache.turbine.util.parser.ParameterParser;
import org.iitk.brihaspati.modules.utils.UserManagement;
import org.iitk.brihaspati.modules.utils.MultilingualUtil;
import org.iitk.brihaspati.modules.utils.StringUtil;
import org.iitk.brihaspati.modules.utils.InstituteIdUtil;
import org.apache.turbine.services.servlet.TurbineServlet;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.apache.turbine.om.security.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * This class is responsible for adding a secondary instructor to the system.
 *
 *  @author <a href="mailto:awadhk_t@yahoo.com">Awadhesh Kumar Trivedi</a>
 *  @author <a href="mailto:nksngh_p@yahoo.co.in">Nagendra Kumar Singh</a>
 *  @author <a href="mailto:singh_jaivir@rediffmail.com">Jaivir Singh</a>
 *  @author <a href="mailto:richa.tandon1@gmail.com">Richa Tandon</a>
 *  @author <a href="mailto:shaistashekh@hotmail.com">Shaista</a>
 *  @modified date: 20-10-2010, 23-12-2010, 08-09-2012
 *  @author <a href="mailto:rpriyanka12@ymail.com">Priyanka Rawat</a>
 *  @modify date: 09-08-2012 (Priyanka)
 */
 

public class InstructorRegisteration extends SecureAction
{

/**
 * Method for registering a new student,secondary instructor
 * @param data RunData instance
 * @param context Context instance
 * @return nothing
 */
	private String LangFile=null;
	private Log log = LogFactory.getLog(this.getClass());
	public void doRegister(RunData data, Context context)
	{	
		MultilingualUtil m_u=new MultilingualUtil();
		try
		{

		/**
                 * getting the value of file from temporary variable
                 * According to selection of Language.
                 **/  
		LangFile=(String)data.getUser().getTemp("LangFile");
		User user = data.getUser();
		ParameterParser pp = data.getParameters();
		/**
		 * Retreiving details entered by the user
		 */
		String instName="";	
		String mod=pp.getString("mode");
		context.put("mode",mod);
		String gName=pp.getString("cName");
		String email=pp.getString("EMAIL");
		String fname=pp.getString("FNAME");
		String lname=pp.getString("LNAME");
		String passwd=pp.getString("PASSWD");
		String rollno =pp.getString("rollno","").trim();
		if(StringUtil.checkString(rollno) != -1)
		/**
                 * check if rollno have any special character then return message
                 */
                {
	                data.addMessage(MultilingualUtil.ConvertedString("c_msg3",LangFile));
                        return;
                }	
		String program =pp.getString("prg","");
		/**
		 *If password is empty, then set the password as value of 0th position of emailId.  
		 */	
		if(passwd.equals("")){
			String []starr=email.split("@");
                	passwd =starr[0];
		}
		if(!gName.equals("")){
			//String []arr=gName.split("_");
			String arr = org.apache.commons.lang.StringUtils.substringAfterLast(gName, "_");
			int instId = Integer.parseInt(arr);
			instName= InstituteIdUtil.getIstName(instId);
		}
		String mail_msg="";
		String serverName=data.getServerName();
                int srvrPort=data.getServerPort();
                String serverPort=Integer.toString(srvrPort);
		String msg=UserManagement.CreateUserProfile(email,passwd,fname,lname,instName,email,gName,"instructor",serverName,serverPort,LangFile,rollno,program, "act"); //modified by Shikha
		context.put("msg",msg);
		data.setMessage(msg +" "+ mail_msg);
		// Maintain Log
		if(msg.equals("Registration Successful !!  Message is in queue !!"))
                log.info("Secondary Course Instructor Registered by "+user.getName() +" on "+gName + " with mailid "+email +" | IP Address --> "+data.getRemoteAddr());

		}
		catch(Exception ex)
		{
			data.setMessage("The error !!!"+ex);
			
		}
	}

	/**
	 * This is the default method called when the button is not found
	 */

	public void doPerform(RunData data,Context context) throws Exception{
		String action=data.getParameters().getString("actionName","");
		LangFile=(String)data.getUser().getTemp("LangFile");
		if(action.equals("eventSubmit_doRegister"))
			doRegister(data,context);
		else
			
			data.setMessage(MultilingualUtil.ConvertedString("c_msg",LangFile));
	}
}

