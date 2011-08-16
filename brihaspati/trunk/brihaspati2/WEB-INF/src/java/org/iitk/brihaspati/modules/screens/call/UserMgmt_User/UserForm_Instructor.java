package org.iitk.brihaspati.modules.screens.call.UserMgmt_User;

/*
 * @(#)UserForm_Instructor.java	
 *
 *  Copyright (c) 2005,2010-2011 ETRG,IIT Kanpur. 
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
import org.iitk.brihaspati.modules.utils.UserManagement;

/**
 * @author <a href="mailto:awadhesh_trivedi@yahoo.co.in ">Awadhesh Kumar Trivedi</a>
 * @author <a href="mailto:shaistashekh@gmail.com">Shaista</a>
 * @author <a href="mailto:richa.tandon1@gmail.com">Richa Tandon</a>
 * @modified date: 20-10-2010,3-11-2010, 05-08-2011
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
		/**
 		 * get list of registered institute for Instructor 
 		 */
		Vector instlist = InstituteIdUtil.getInstructorInstId(usrid);
		Vector tmpvec=new Vector();
		Vector tmpvec1=new Vector();
		/**
 		 * Now take each institute from list and get its corresponding program from table
 		 * then loop for getting program code
 		 * put it into context to print in vm   
 		 * and also context put institute list to print in vm
 		 */ 	
		for(int i=0;i<instlist.size();i++)
		{
			int instid=Integer.parseInt((String)instlist.get(i));
			crit=new Criteria();
	                crit.add(InstituteProgramPeer.INSTITUTE_ID,instid);
			List Instprglist= InstituteProgramPeer.doSelect(crit);
                        for(int j=0;j<Instprglist.size();j++)
                       	{
                               	InstituteProgram element = (InstituteProgram)Instprglist.get(j);
                                PrgCode = element.getProgramCode();	
				String prgName = InstituteIdUtil.getPrgName(PrgCode);
               	                cDetails=new CourseUserDetail();
				/**
 				 * Code to avoid duplicate values of program inside vector 	
 				 */	 	
				if(!tmpvec.contains(prgName) && !tmpvec1.contains(PrgCode)){
                       	        	cDetails.setPrgName(prgName);
	       	                       	cDetails.setPrgCode(PrgCode);
					tmpvec.add(prgName);
					tmpvec1.add(PrgCode);
					PrgDetail.add(cDetails);
				}
			}
			context.put("PrgDetail",PrgDetail);
			String Instname=InstituteIdUtil.getIstName(instid);
			cDetails=new CourseUserDetail();
                        cDetails.setInstName(Instname);
                        cDetails.setInstId(instid);
			InsDetail.add(cDetails);
			context.put("InsDetail",InsDetail);
		}
		tmpvec.clear();
		tmpvec1.clear();
		tmpvec=null;	
		tmpvec1=null;	
		String course_name=(String)data.getUser().getTemp("course_name");
		String username=data.getParameters().getString("username");
		String status=data.getParameters().getString("status","");
		String type=data.getParameters().getString("type","");
		context.put("tdcolor",data.getParameters().getString("count","4"));
		/**
 		 * getting all details of user into list from turbine user table 
 		 * context put list to print in vm
 		 */ 	
		crit=new Criteria();
		crit.add(TurbineUserPeer.LOGIN_NAME,username);
		List details=TurbineUserPeer.doSelect(crit);
		/**
		 * Getting user rollno detail  
		 * if record size is not zero it shows user already have rollno
		 * by for loop get institute,program,roll no,serial id of the user
		 * from record and add all values in vector .
		 * context put vector to print values in vm 
		 */
		String InstId = (String)data.getUser().getTemp("Institute_id");
                //List rlrecord=UserManagement.getUserPrgRollNo(username,Prgcode,InstId);
                List rlrecord=UserManagement.getUserRollNo(username);
                int rlsize = rlrecord.size();
                Vector UsDetail = new Vector();
                for(int k=0;k<rlrecord.size();k++)
                {
                        StudentRollno element = (StudentRollno)rlrecord.get(k);
                        int rlinstid = Integer.parseInt(element.getInstituteId());
                        String RlIname=InstituteIdUtil.getIstName(rlinstid);
                        String rlprgcode = element.getProgram();
                        int sturlid = element.getId();
                        String pName =InstituteIdUtil.getPrgName(rlprgcode);
                        String rlrollno = element.getRollNo();
                        cDetails=new CourseUserDetail();
                        cDetails.setStudsrid(sturlid);
                        cDetails.setInstName(RlIname);
                        cDetails.setInstId(rlinstid);
                        cDetails.setPrgCode(rlprgcode);
                        cDetails.setPrgName(pName);
                        cDetails.setRollNo(rlrollno);
                        UsDetail.add(cDetails);
                        context.put("UDetail",UsDetail);
                 }
			context.put("counter",rlsize);
		if(rlsize==0)
                        context.put("sizecount",rlsize);
		context.put("type",type);
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


