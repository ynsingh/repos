package org.iitk.brihaspati.modules.actions;
/*
 * @(#)RegisterICInstructor.java	
 *
 *  Copyright (c) 2009-2010 ETRG,IIT Kanpur. 
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

import java.io.File;
import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;
import org.apache.turbine.om.security.User;
import org.apache.torque.util.Criteria;
import org.apache.turbine.util.parser.ParameterParser;
import org.iitk.brihaspati.modules.utils.CourseManagement;
import org.iitk.brihaspati.modules.utils.InstituteIdUtil;
import org.iitk.brihaspati.modules.utils.MultilingualUtil;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.iitk.brihaspati.modules.utils.QuotaUtil;
/**
 *
 * This Action class for Registering a particular course with Instructor(Primary) 
 * in the system.
 * @author <a href="mailto:sharad23nov@yahoo.com">Sharad Singh</a>
 * @author <a href="mailto:singh_jaivir@rediffmail.com">Jaivir Singh</a>
 * @author <a href="mailto:sunil.singh6094@gmail.com">Sunil Kumar</a>
 */
public class RegisterICInstructor extends SecureAction_Institute_Admin
{
	
	private String LangFile=new String();
	/**
 	  * This method actually registers a new course along with the instructor 
	  * in the system
 	  * @param data RunData instance
 	  * @param context Context instance
  	  * @exception Exception, a generic exception
 	  * @exception Exception, a generic exception
 	  */
	public void doRegister(RunData data, Context context)
	{
	        try
		{
	 		/**
          		*Getting file value from temporary variable according to selection
          		*Replacing the value from Property file
         		**/
				//get Institute Name(iname)
			         
				String iname=data.getParameters().getString("iname","");
		                context.put("iname",iname);
	 		        LangFile=(String)data.getUser().getTemp("LangFile");
				ParameterParser pp=data.getParameters();
                               	String path = "";
				path=data.getServletContext().getRealPath("/WEB-INF")+"/conf"+"/"+"iname"+"Admin.properties";
		 		/**
		  		* Gather details from the page where user has entered them
		  		*/
		 		String gname=pp.getString("COURSEID").toUpperCase();
		 		String cname=pp.getString("CNAME");
		 		String dept=pp.getString("DEPARTMENT","");
		 		String description=pp.getString("DESCRIPTION","");
		 		//String uname=pp.getString("UNAME");
		 		String fname=pp.getString("FNAME","");
		 	        String lname=pp.getString("LNAME","");
		 		String email=pp.getString("EMAIL","");
		 		String passwd=pp.getString("PASSWD","");
				String serverName=data.getServerName();
                 		int srvrPort=data.getServerPort();
                 		String serverPort=Integer.toString(srvrPort);
				String instId=(data.getUser().getTemp("Institute_id")).toString();
				int instituteId=Integer.parseInt(instId);
				String instName=InstituteIdUtil.getIstName(instituteId); //added by Shikha
                                                                

				/**
				* if password is empty then set password.
				* password is the value of 0th position of email id
				*/
		 		if(passwd.equals("")){
			 	passwd=email;
				String []starr=passwd.split("@");
                		passwd =starr[0];
				}
		 		/**
		  		* Register a new course with instructor
				* Here we give 100MB quota for course, once he is login in the system and immediate his quota is updated
		  		* @see CourseManagement Utils
		  		*/ 
	 			//String msg=CourseManagement.CreateCourse(gname,cname,dept,description,uname,passwd,fname,lname,email,serverName,serverPort,LangFile,instituteId);
	 			boolean check=QuotaUtil.CompareAllotedQuota(instId);
				ErrorDumpUtil.ErrorLog("check at line 116 in registration action==========="+check);
				//if(check){
	 			String msg=CourseManagement.CreateCourse(gname,cname,dept,description,email,passwd,fname,lname,email,serverName,serverPort,LangFile,instituteId,instName); //modified by Shikha                   
		 		data.setMessage(msg);
				/*}
				else{
					data.setMessage(MultilingualUtil.ConvertedString("qmgmt_msg7",LangFile));
					//data.setMessage("For Register new Course ,the Space is not enough,so contact to Sysadmin");
				}*/
		}
		catch(Exception e)
		{
			 data.setMessage("The error"+e);	
                          
		}
		
	}

	/**
 	  * This method is invoked when no button corresponding to 
 	  * doRegister is found
 	  * @param data RunData
  	  * @param context Context
 	  * @exception Exception, a generic exception
 	  */
	public void doPerform(RunData data,Context context) throws Exception{
		String action=data.getParameters().getString("actionName","");
		if(action.equals("eventSubmit_doRegister"))
			doRegister(data,context);
		else{
			
		
		/**
                 * getting property file According to selection of Language in temporary variable
                 * getting the values of first,last names and
                 * configuration parameter.
                 */
 
                LangFile=(String)data.getUser().getTemp("LangFile"); 
			String str=MultilingualUtil.ConvertedString("c_msg",LangFile);
                        data.setMessage(str);
		}
	}
}

