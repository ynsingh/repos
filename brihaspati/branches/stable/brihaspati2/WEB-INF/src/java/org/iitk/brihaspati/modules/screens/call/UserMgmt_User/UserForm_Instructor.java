package org.iitk.brihaspati.modules.screens.call.UserMgmt_User;

/*
 * @(#)UserForm_Instructor.java	
 *
 *  Copyright (c) 2005,2010-2011,2012 ETRG,IIT Kanpur. 
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
import java.util.ListIterator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import com.workingdogs.village.Record;
import java.util.Vector;
import org.apache.turbine.util.RunData;
import org.apache.turbine.om.security.User;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.iitk.brihaspati.modules.utils.CourseUserDetail;
import org.apache.velocity.context.Context;
import org.apache.torque.util.Criteria;
import org.iitk.brihaspati.modules.screens.call.SecureScreen_Instructor;
import org.apache.turbine.services.security.torque.om.TurbineUserPeer;
import org.iitk.brihaspati.om.InstituteAdminRegistrationPeer;
import org.iitk.brihaspati.om.InstituteProgramPeer;
import org.iitk.brihaspati.om.InstituteProgram;
import org.iitk.brihaspati.om.InstituteAdminRegistration;
import org.iitk.brihaspati.om.StudentRollnoPeer;
import org.iitk.brihaspati.om.ProgramPeer;
import org.iitk.brihaspati.om.StudentRollno;
import org.iitk.brihaspati.modules.utils.InstituteIdUtil;
import org.iitk.brihaspati.modules.utils.UserUtil;
import org.iitk.brihaspati.modules.utils.CourseProgramUtil;

//import org.iitk.brihaspati.modules.utils.CourseTimeUtil;
//import org.iitk.brihaspati.modules.utils.ModuleTimeUtil;
import org.iitk.brihaspati.modules.utils.MailNotificationThread;

/**
 * @author <a href="mailto:awadhesh_trivedi@yahoo.co.in ">Awadhesh Kumar Trivedi</a>
 * @author <a href="mailto:shaistashekh@gmail.com">Shaista</a>
 * @author <a href="mailto:richa.tandon1@gmail.com">Richa Tandon</a>
 * @modified date: 20-10-2010,3-11-2010, 05-08-2011, 30-10-2012(Richa)
 */
  
public class UserForm_Instructor extends SecureScreen_Instructor{
	/**
 	 * This class contain code for details of the user to be modified.
 	 * Get user detail from turbine user table ie first name,last name
 	 * and email id .Get registered institute list and its corresponding program 
 	 * from Institute program table.   
  	 */ 	
	public void doBuildTemplate( RunData data, Context context ){
		try{
		User user=data.getUser();
		String userName=user.getName();
		Criteria crit=new Criteria();
                CourseUserDetail cDetails=new CourseUserDetail();
		String Rollno="", PrgCode="",Prgname="" ;
		int usrid=UserUtil.getUID(userName);
		Vector InsDetail = new Vector();
                Vector PrgDetail = new Vector();
		String Role = (String)user.getTemp("role");
		String InstId = (String)data.getUser().getTemp("Institute_id");
		String course_name=(String)data.getUser().getTemp("course_name");
		String course_id=(String)data.getUser().getTemp("course_id");
		String username=data.getParameters().getString("username");
		String status=data.getParameters().getString("status","");
		context.put("tdcolor",data.getParameters().getString("count","4"));
		/**
                 *Time calculaion for how long user use this page.
                 */
                 int uid=UserUtil.getUID(userName);
                 if((Role.equals("student")) || (Role.equals("instructor")))
                 {
	                 //CourseTimeUtil.getCalculation(uid);
        	         //ModuleTimeUtil.getModuleCalculation(uid);
			 int eid=0;
			 MailNotificationThread.getController().CourseTimeSystem(uid,eid);
                 }

		/**
 		 * get list of registered program for institute  
 		 */
		crit=new Criteria();
                crit.add(InstituteProgramPeer.INSTITUTE_ID,InstId);
		List Instprglist= InstituteProgramPeer.doSelect(crit);
                for(int j=0;j<Instprglist.size();j++)
               	{
                       	InstituteProgram element = (InstituteProgram)Instprglist.get(j);
                        PrgCode = element.getProgramCode();	
			String prgName = InstituteIdUtil.getPrgName(PrgCode);
       	                cDetails=new CourseUserDetail();
       	        	cDetails.setPrgName(prgName);
                       	cDetails.setPrgCode(PrgCode);
			PrgDetail.add(cDetails);
		}
		context.put("PrgDetail",PrgDetail);
		/**
 		 * getting all details of user into list from turbine user table 
 		 * context put list to print in vm
 		 */ 	
		crit=new Criteria();
		crit.add(TurbineUserPeer.LOGIN_NAME,username);
		List details=TurbineUserPeer.doSelect(crit);
		/**
		 * Getting user rollno detail  
		 */
                List rlrecord = CourseProgramUtil.getUserInstituteRollnoList(username,InstId);
                //ErrorDumpUtil.ErrorLog("rlrecord fromscreen file----"+rlrecord);
                int rlsize = rlrecord.size();
                Vector rollprglist = new Vector();
                for(int k=0;k<rlrecord.size();k++)
                {
	                StudentRollno element = (StudentRollno)rlrecord.get(k);
                        String rollno = element.getRollNo();
                        String Program = element.getProgram();
			String prgName = InstituteIdUtil.getPrgName(Program);
                        cDetails = new CourseUserDetail();
			cDetails.setPrgName(prgName);
                        cDetails.setPrgCode(Program);
                        cDetails.setRollNo(rollno);
                        rollprglist.add(cDetails);
                }
                context.put("rlprglist",rollprglist);
                Vector UsDetail = new Vector();
		ArrayList list = new ArrayList();
                Map map = new HashMap();
		String rl="",pgcode="",pgname="",CrsId="";
		List lst = CourseProgramUtil.getCourseRollnoDetail(username,Integer.parseInt(InstId));
		if(lst.size()>0)
		{
			for(ListIterator j = lst.listIterator();j.hasNext();)
                        {
	                        Record item = (Record)j.next();
                                CrsId = item.getValue ("COURSE_ID").asString();
                                //ErrorDumpUtil.ErrorLog("return value cid from execute query  :- "+CrsId);
                                if(CrsId.equals(course_id))
				{
		                        rl = item.getValue ("ROLL_NO").asString();
        	                        //ErrorDumpUtil.ErrorLog("return value from execute query  :- "+rl);
                       	                pgcode= item.getValue ("PROGRAM_CODE").asString();
                               	        //ErrorDumpUtil.ErrorLog("return value pgr from execute query  :- "+pgcode);
	                                pgname = InstituteIdUtil.getPrgName(pgcode);
        	                        //ErrorDumpUtil.ErrorLog("pgname from util :- "+pgname);
					map = new HashMap();
	                                map.put("rlno",rl);
	                                map.put("pgcode",pgcode);
	                                map.put("pgname",pgname);
					list.add(map);
				}
			}
		}
                        context.put("UDetail",list);
		context.put("course",course_name);
		context.put("stat",status);
		context.put("user_details",details);
		context.put("username",username);
		}
		catch(Exception ex)
		{
			data.setMessage("The Error in UserForm_Instructor"+ex );
		}
	}

}
