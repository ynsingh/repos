package org.iitk.brihaspati.modules.actions;
/*
 * @(#) AddUser_Institute.java	
 *
 *  Copyright (c) 2009-2010, 2011 ETRG,IIT Kanpur. 
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
import java.util.List;
import java.util.Vector;
import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;
import org.apache.turbine.om.security.User; 
import org.apache.turbine.util.parser.ParameterParser;
import org.iitk.brihaspati.modules.utils.InstituteIdUtil;
import org.iitk.brihaspati.modules.utils.UserManagement;
import org.iitk.brihaspati.modules.utils.MultilingualUtil;
import org.iitk.brihaspati.modules.actions.SecureAction_Institute_Admin;
import org.iitk.brihaspati.modules.utils.ExpiryUtil;
import org.iitk.brihaspati.modules.utils.UserUtil;
import org.iitk.brihaspati.modules.utils.StringUtil;
import org.apache.turbine.om.security.User;
/**
 * This class is responsible for adding a new user in specified group and 
 * assigned role to the system.
 * @author <a href="mailto:madhavi_mungole@hotmail.com">Madhavi Mungole</a> 
 * @author <a href="mailto:awadhk_t@yahoo.com">Awadhesh Kumar Trivedi</a> 
 * @author <a href="mailto:singh_jaivir@rediffmail.com">Jaivir Singh</a> 
 * @author <a href="mailto:sharad23nov@yahoo.com">Sharad Singh</a> 
 * @author <a href="mailto:richa.tandon1@gmail.com">Richa Tandon</a> 
 * @author <a href="mailto:shikhashuklaa@gmail.com">Shikha Shukla</a>
 * @modified date:20-10-2010,23-12-2010,26-02-2011,05-08-2011(Richa)
 */

public class AddUser_Institute extends SecureAction_Institute_Admin 
{
/**
 * Method for registered a user as Secondary Instructor,Student
 * and Content Author
 * Get all details entered by user then register profile.
 * @param data RunData instance
 * @param context Context instance
 *
 */
	private String LangFile=null;
	
	public void doRegister(RunData data, Context context) throws Exception
	{
		try{
		/**
                *get the value of server name and server port used in sending a mail.
                *and get the role in which user registered.
                */
		ParameterParser pp=data.getParameters();
		String serverName=data.getServerName();
                int srvrPort=data.getServerPort();
                String serverPort=Integer.toString(srvrPort);
                String roleName=pp.getString("role","");
		/**
                 * Getting the value of file from temporary variable
                 * According to selection of Language.
                 * and replacing the String from property file.
                 * @see MultilingualUtil in utils
                 */   

		LangFile=(String)data.getUser().getTemp("LangFile");
		User user=data.getUser();
		/**
                 * Retreiving details entered by the user
                 */
                String rollno = pp.getString("rollno","").trim();
		/**
 		 * check if rollno have any special character then return message
 		 */
		if(StringUtil.checkString(rollno) != -1)
                {
                	data.addMessage(MultilingualUtil.ConvertedString("c_msg3",LangFile));
                        return;
                }
                String program = pp.getString("prg","");
		String gname=new String();
		gname=pp.getString("group","");
		if(gname.equals(""))
		{
			gname=new String();	
			gname=pp.getString("group_author");
		}
		if(roleName.equals(""))
		{
			roleName=new String();	
			roleName=pp.getString("role_author");
		}
                String fname=pp.getString("FNAME");
		
                String lname=pp.getString("LNAME");
                String email=pp.getString("EMAIL");
                String passwd=pp.getString("PASSWD");
		email=UserManagement.ChkMailId(email);

		/**
		* if password field is null,set the password.
		* break email using "@" and set the password as the value of email at 0th position.
		*/
                if(passwd.equals(""))
		{
			passwd=email;
                	String []starr=passwd.split("@");
                	String mailname=starr[0];
                	passwd=mailname;
		}
		
		/**
                 * Get institute id from temp and institute name from that id 
                 * check value of program, if it is RWP ie RegistrationWithoutProgram then
                 * generate random rollno.
		 * Adds the new user in the database.
		 * @see UserManagement in utils
		 */
		 
		 String instituteId=(data.getUser().getTemp("Institute_id")).toString();
                 int instid=Integer.parseInt(instituteId);
                 String instName=InstituteIdUtil.getIstName(instid);
		if(program.equals("RWP"))
		{
			rollno = InstituteIdUtil.generateRollno(instid);
		}
		String msg=UserManagement.CreateUserProfile(email,passwd,fname,lname,instName,email,gname,roleName,serverName,serverPort,LangFile,rollno,program);
		data.setMessage(msg);
		}
		catch(Exception ex){
		data.setMessage("The Error in Action AddUser_Institute. Please Update the Profile !!");
		}
	}
	/**
	 * This is the default method called when the button is not found
	 * @param data RunData
	 * @param context Context
	 */
	public void doPerform(RunData data,Context context) throws Exception
	{
		String action=data.getParameters().getString("actionName","");
               	 /**  
		 * Passing the value of file from temporary variable
                 * According to selection of Language.
		 */
		MultilingualUtil m_u= new MultilingualUtil();
		LangFile=(String)data.getUser().getTemp("LangFile");
		if(action.equals("eventSubmit_doRegister"))
		{
			doRegister(data,context);
		}
		else
		{
			String str=m_u.ConvertedString("c_msg",LangFile);
                        data.setMessage(str);
		}
	}
}

