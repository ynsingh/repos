package org.iitk.brihaspati.modules.screens.call.UserMgmt_User;

/*
 * @(#) StudentManagement.java	
 *
 *  Copyright (c) 2005,2010, 2013 ETRG,IIT Kanpur. 
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
import java.util.List;
import java.util.Vector;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.iitk.brihaspati.modules.utils.CourseUtil;
import org.iitk.brihaspati.modules.utils.CourseUserDetail;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.iitk.brihaspati.modules.utils.InstituteIdUtil;
import org.apache.turbine.util.RunData;
import org.apache.commons.lang.StringUtils;
import org.apache.velocity.context.Context;
import org.apache.turbine.om.security.User;
import org.iitk.brihaspati.modules.screens.call.SecureScreen_Instructor;
import org.apache.torque.util.Criteria;
import org.iitk.brihaspati.om.ProgramPeer;
import org.iitk.brihaspati.om.InstituteProgramPeer;
import org.iitk.brihaspati.om.InstituteProgram;
import org.iitk.brihaspati.om.StudentRollno;
import org.iitk.brihaspati.modules.utils.UserUtil;
import org.iitk.brihaspati.modules.utils.CourseProgramUtil;
//import org.iitk.brihaspati.modules.utils.CourseTimeUtil;
//import org.iitk.brihaspati.modules.utils.ModuleTimeUtil;
import org.iitk.brihaspati.modules.utils.MailNotificationThread;
/**
 * This class responsible manage student management 
 * @author <a href="mailto:awadhesh_trivedi@yahoo.co.in ">Awadhesh Kumar Trivedi</a>
 * @author <a href="mailto:shaistashekh@gmail.com">Shaista</a>
 * @author <a href="mailto:richa.tandon1@gmail.com">Richa Tandon</a>
 * @modified date:23-12-2010, 11-01-2011, 01-02-2013(Richa)
 */

public class StudentManagement extends SecureScreen_Instructor
{
    public void doBuildTemplate( RunData data, Context context )
    {
	try
	{
		User user=data.getUser();
		String C_Name=data.getParameters().getString("courseName");
		String counter=data.getParameters().getString("count","");
		context.put("tdcolor",counter);
		if(((String)user.getTemp("course_name")).equals(""))
		{
			user.setTemp("course_name",C_Name);
		}
		
		String C_ID=CourseUtil.getCourseId(C_Name);

		if(((String)user.getTemp("course_id")).equals(""))
		{
			user.setTemp("course_id",C_ID);
		}

		String Role=data.getParameters().getString("role");	
		String Role1 = (String)user.getTemp("role");
		/**
                  *Time calculaion for how long user use this page.
                  */
                 int uid=UserUtil.getUID(user.getName());
                 if((Role1.equals("student")) || (Role1.equals("instructor")) ||  (Role1.equals("teacher_assistant")))
                 {
                           //CourseTimeUtil.getCalculation(uid);
                           //ModuleTimeUtil.getModuleCalculation(uid);
			   int eid=0;
			   MailNotificationThread.getController().CourseTimeSystem(uid,eid);
                 }

		if(((String)user.getTemp("role")).equals("")){
			user.setTemp("role",Role);
		}
		String userName=user.getName();
		if(userName.equals("guest"))
		{
				context.put("guestRole","yes");
		}
		else
		{
				context.put("guestRole","no");
		}
		context.put("user_role",(String)user.getTemp("role"));
		context.put("course",(String)user.getTemp("course_name"));
		/**
 		 * Getting list of program according to institute id  
 		 */ 	
		String InstId =(String)data.getUser().getTemp("Institute_id");
		Criteria crit=new Criteria();
                crit.add(InstituteProgramPeer.INSTITUTE_ID,Integer.parseInt(InstId));
	        List Instplist= InstituteProgramPeer.doSelect(crit);
                Vector PrgDetail = new Vector();
                for(int i=0;i<Instplist.size();i++)
                {
                	InstituteProgram element = (InstituteProgram)Instplist.get(i);
                        String PrgCode = element.getProgramCode();
                        String prgName = InstituteIdUtil.getPrgName(PrgCode);
                	CourseUserDetail cDetails=new CourseUserDetail();
                        cDetails.setPrgName(prgName);
                        cDetails.setPrgCode(PrgCode);
                        PrgDetail.add(cDetails);
                }
                context.put("PrgDetail",PrgDetail);
		/**
 		 * Getting values of all fields of template and set into hash map for 
 		 * display in template after reloading the page for checking existing rollno .
 		 */ 
		ArrayList list = new ArrayList();
                Map map = new HashMap();
		String uname=data.getParameters().getString("EMAIL","");
		String Passwd=data.getParameters().getString("PASSWD","");
		String Fname=data.getParameters().getString("FNAME","");
		String Lname=data.getParameters().getString("LNAME","");
		String pgcode=data.getParameters().getString("PrgName","");
		String pgname="";
		if(StringUtils.isNotBlank(pgcode))
			pgname=InstituteIdUtil.getPrgName(pgcode);
		map = new HashMap();
		map.put("usrname",uname);
		map.put("Pswd",Passwd);
		map.put("Fstname",Fname);
		map.put("Lstname",Lname);
		map.put("Pgcode",pgcode);
		map.put("Pgname",pgname);
		list.add(map);
		context.put("UsrDetail",list);
		//Get rollno of the user if exist in databse and context put in template.
		List userRollNo=CourseProgramUtil.getUserPrgRollNo(uname,pgcode,InstId);
		context.put("rollno",userRollNo);
	}
	catch(Exception e)
	{
		data.setMessage("The error in Student Management !!"+e);
	}
    }
}

