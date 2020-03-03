package org.iitk.brihaspati.modules.screens.call.Answerbook;

/*
 * @(#)View.java	
 *
 *  Copyright (c) 2005-2006,2009,2013 2020 ETRG,IIT Kanpur. 
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
 * This class contains code for viewing contents and downloading
 * @author <a href="mailto:nksinghiitk@yahoo.co.in">Nagendra Kumar Singh</a>
 */
import java.io.File;
import java.util.List;
import java.util.Vector;

import org.apache.turbine.Turbine;
import org.apache.turbine.util.RunData;
import org.apache.turbine.om.security.User;
import org.apache.turbine.util.security.AccessControlList;
import org.apache.turbine.services.servlet.TurbineServlet;
import org.apache.torque.util.Criteria;
import org.apache.velocity.context.Context;

import org.iitk.brihaspati.om.StudentRollno;
import org.iitk.brihaspati.om.StudentRollnoPeer;
import org.iitk.brihaspati.modules.utils.UserUtil;
import org.iitk.brihaspati.modules.utils.ModuleTimeThread;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.iitk.brihaspati.modules.screens.call.SecureScreen_Instructor_Student;

public class ViewAnsBook extends SecureScreen_Instructor_Student{
		String rollno1="",rollno2="";
		Vector<String> fileType=new Vector<String>();
	/**
	 * This is the default method that builds the template page
	 * @param data Rundata
	 * @param context Context
	 */
	public void doBuildTemplate(RunData data,Context context) 
	{
	        List lv=null;
		Vector v=new Vector();
		User user=data.getUser();
		/**
		 * Get the topic name from URL and put it in context
		 */
		
		String topic=data.getParameters().getString("topic","");
		context.put("topic",topic);
		String cName=data.getParameters().getString("cName","");
		context.put("tdcolor",data.getParameters().getString("count",""));
		/**
		 * Get User Role
		 */
		 String Role = (String)user.getTemp("role");
		/**
                 *Time calculaion for how long user use this page.
                 */
                 int uid=UserUtil.getUID(user.getName());
                 if((Role.equals("student")) || (Role.equals("instructor")) || (Role.equals("teacher_assistant")))
                 {
			 int eid=0;
			 ModuleTimeThread.getController().CourseTimeSystem(uid,eid);
                }

		/**
		 * Check if the user is an admin or not
		 */

		AccessControlList acl=data.getACL();
		context.put("isAdmin",acl.hasRole("turbine_root")?"true":"false");
		String group,dir;

		/**
		 * Retreive the course id and course name from the
		 * temporary variable and get the file path for XML file name
		 */

		group=dir=(String)user.getTemp("course_id");
		context.put("course",(String)user.getTemp("course_name"));
		String filePath=null;
		filePath=data.getServletContext().getRealPath("/Courses")+"/"+dir+"/AnsCopy/";
		if(acl.hasRole("instructor",group)||acl.hasRole("author",group))
		{
			context.put("isInstructor","true");
			context.put("isAuthor","true");
		}
		String topicDesc="";
		Vector<String> fileType1=new Vector<String>();
			/**
                         * Get rollno from table
                         */

                try
                {
                                Criteria crit=new Criteria();
                                crit.add(StudentRollnoPeer.EMAIL_ID,user.getName());
                                lv=StudentRollnoPeer.doSelect(crit);
                                if(lv.size()>0){
                                        StudentRollno element=(StudentRollno)lv.get(0);
                                        rollno1=element.getRollNo();
                                        rollno2="";
                                        /**
                                         * Vector size greater than 1 shows that user have more
                                         * than 1 rollno then get another rollno
                                         */
                                        if(lv.size()>1)
                                        {
                                                StudentRollno element1=(StudentRollno)lv.get(1);
                                                rollno2=element1.getRollNo();
                                        }
                                }
		}
                catch(Exception e)
                {
                        ErrorDumpUtil.ErrorLog("Error inside getting roll no value in view answer book"+e);
                }
		try{
			String vabmsg=" Roll No 1 is  "+rollno1 +" and mail id is "+user.getName() +" Roll no 2 is "+rollno2;
			ErrorDumpUtil.ErrorLog("\nThe person goes to view answer copy (ViewAnsBook) "+vabmsg, TurbineServlet.getRealPath("/logs/ViewAnsCopy.txt"));
			File folder = new File(filePath);
			if((rollno1 != null) && !(rollno1.trim().isEmpty())){
				fileType=new Vector<String>();
				fileType1=listAllFiles(folder);
			}
		 	context.put("dirContent",fileType1);
		       if(fileType1.size() >0){		
				context.put("Mode","NoBlank");
		 	}
			
		}//try
		catch(Exception e)
		{
			data.setMessage("Your answer copy does not exist on the server. Contact to Instructor. !!"+e);
		}

	}
	/**
	 * @param data file
	 * @return list
	 */
	public Vector listAllFiles(File folder){
		File[] files = folder.listFiles();
        	if(files.length >0){
                	for (File file : files){
                        	if(file.isDirectory()){
                                        listAllFiles(file);
                                }else{
                                      //  ErrorDumpUtil.ErrorLog(file.toString());
					String fdnme=file.getAbsolutePath().substring(file.getAbsolutePath().lastIndexOf("AnsCopy")+8);
//					ErrorDumpUtil.ErrorLog("The fd name is "+fdnme);
					String filenme=file.getName();
//					ErrorDumpUtil.ErrorLog(filenme);
                                       // if(filenme.startsWith(rollno1)){
                                        if(filenme.equals(rollno1+".pdf")){
                                	        fileType.add(fdnme);
                                            //          ErrorDumpUtil.ErrorLog("Inside loop"+fdnme);
                                        }
                                        if((rollno2 != null) && !(rollno2.trim().isEmpty())){
                                               // if(filenme.startsWith(rollno2)){
                                                if(filenme.equals(rollno2+".pdf")){
                                                        fileType.add(fdnme);
                //                                            ErrorDumpUtil.ErrorLog(file);
                                                }
                                        }
                                }
                        }
//			ErrorDumpUtil.ErrorLog(fileType);
                }
		return fileType;
     	}


	/**
	 * This method checks the authorization of the user
	 * @param data Rundata
	 * @return boolean
	 */

	protected boolean isAuthorized( RunData data )  throws Exception
        {
                boolean isAuthorized = false;
                try
                {
                        AccessControlList acl = data.getACL();
                        User user=data.getUser();
                        String g=user.getTemp("course_id").toString();

                        if (acl==null || (! acl.hasRole("instructor",g) && !acl.hasRole("Author",g) && !acl.hasRole("student",g)&& !acl.hasRole("turbine_root")&& !acl.hasRole("teacher_assistant",g))) 
                        {
                                data.setScreenTemplate( Turbine.getConfiguration().getString("template.login"));

                                isAuthorized = false;
                        }
                        else if(acl.hasRole("instructor",g) || acl.hasRole("student",g) || acl.hasRole("turbine_root")||acl.hasRole("teacher_assistant",g))
                        {
                                isAuthorized = true;
                        }
                }
                catch(Exception e)
                {
                        data.setScreenTemplate(Turbine.getConfiguration().getString("template.login"));
                        return false;
                }
                return isAuthorized;
        }

}

