package org.iitk.brihaspati.modules.screens.call.UserMgmt_User;

/*
 * @(#)UserForm_Instructor.java	
 *
 *  Copyright (c) 2005,2010 ETRG,IIT Kanpur. 
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
import org.apache.turbine.util.RunData;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.apache.velocity.context.Context;
import org.apache.torque.util.Criteria;
import org.iitk.brihaspati.modules.screens.call.SecureScreen_Instructor;
import org.apache.turbine.services.security.torque.om.TurbineUserPeer;
import org.iitk.brihaspati.om.StudentRollnoPeer;
import org.iitk.brihaspati.om.StudentRollno;


/**
 *  This class displays the details of the user to be modified
 * @author <a href="mailto:awadhesh_trivedi@yahoo.co.in ">Awadhesh Kumar Trivedi</a>
 * @author <a href="mailto:shaistashekh@gmail.com">Shaista</a>
 * @author <a href="mailto:richa.tandon1@gmail.com">Richa Tandon</a>
 * @modified date: 20-10-2010,3-11-2010
 */
  
public class UserForm_Instructor extends SecureScreen_Instructor{

	public void doBuildTemplate( RunData data, Context context ){
		try{
		Criteria crit=new Criteria();
		String Rollno="";
		String course_name=(String)data.getUser().getTemp("course_name");
		String username=data.getParameters().getString("username");
		String status=data.getParameters().getString("status");
		context.put("tdcolor",data.getParameters().getString("count",""));
		crit.add(TurbineUserPeer.LOGIN_NAME,username);
		List details=TurbineUserPeer.doSelect(crit);
		/**
		 * Getting user rollno record 
		 * if record size is not zero it shows user already have rollno
		 */
		crit=new Criteria();
		crit.add(StudentRollnoPeer.EMAIL_ID,username);
                List v=StudentRollnoPeer.doSelect(crit);
                //ErrorDumpUtil.ErrorLog("list in view marks---------------->"+v);
		if(v.size()!=0)
		{
	                StudentRollno element=(StudentRollno)v.get(0);
	                Rollno=element.getRollNo();
		}
		context.put("rollno",Rollno);
		context.put("course",course_name);
		context.put("stat",status);
		context.put("user_details",details);
		}
		catch(Exception ex)
		{
			data.setMessage("The Error in UserForm_Instructor"+ex );
		}
	}
}


