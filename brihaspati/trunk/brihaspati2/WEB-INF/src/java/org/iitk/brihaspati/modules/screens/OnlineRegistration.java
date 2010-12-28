package org.iitk.brihaspati.modules.screens;

/* 
 * @(#)OnlineRegistration.java
 *
 *  Copyright (c) 2008-2010 ETRG,IIT Kanpur.
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
 *  Contributors: Members of ETRG, I.I.T. Kanpur
 *
 */


import java.util.List;
import org.apache.turbine.util.RunData;
import org.apache.torque.util.Criteria;
import org.apache.velocity.context.Context;
import org.apache.turbine.util.parser.ParameterParser;
import org.apache.turbine.modules.screens.VelocityScreen;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
//import org.apache.turbine.services.servlet.TurbineServlet;
import org.iitk.brihaspati.om.InstituteAdminRegistrationPeer;
import org.iitk.brihaspati.om.ProgramPeer;
 /**
 * @author <a href="mailto:omprakash_kgp@yahoo.co.in">Om Prakash</a>
 * @author <a href="mailto:shaistashekh@hotmail.com">Shaista</a>
 * @author <a href="mailto:singh_jaivir@rediffmail.com">jaivir Singh</a>20092010
 * @author <a href="mailto:richa.tandon1@gmail.com">Richa Tandon</a>20092010
 * @modify:23-12-2010
 */
/**
* This class called when user request for online registration as an Student,Instructor(course) and author.   
*/
public class OnlineRegistration extends VelocityScreen
{
	public void doBuildTemplate(RunData data, Context context)
	{
		try{
			/**
			*Get the list of Institute and set in context 
			*for using in templates and
			* set the status for User Registration 
			*as well as Course Registration,count for tab colour.
			*/
			Criteria crit=new Criteria();
			int addnot[]={0,2};
			crit.addGroupByColumn(InstituteAdminRegistrationPeer.INSTITUTE_NAME);
			crit.addNotIn(InstituteAdminRegistrationPeer.INSTITUTE_STATUS,addnot);
			List list=InstituteAdminRegistrationPeer.doSelect(crit);
			context.put("instList",list);
			crit=new Criteria();
	                crit.addGroupByColumn(ProgramPeer.PROGRAM_CODE);
        	        List plist=ProgramPeer.doSelect(crit);
                	context.put("prgList",plist);
 			ParameterParser pp=data.getParameters();
			String lang=pp.getString("lang","english");
        	        context.put("lang",lang);
			String status=pp.getString("status","");
			String counter=pp.getString("count","");
			context.put("tdcolor",counter);	
			if(status.equals("UserResitration"))
				context.put("status","UserResitration");
				
			else if(status.equals("CourseRegistration"))
				context.put("status","CourseRegistration");

		}
		catch(Exception e) { 	data.setMessage("Error in Online Registration !!" +e); }
		
	}
}

