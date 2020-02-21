package org.iitk.brihaspati.modules.screens.call.Answerbook;
/*
 * @(#) UploadAndwerbook.java
 *
 *  Copyright (c) 2005, 2008-2009 2020 ETRG,IIT Kanpur.
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

import java.io.File;
import java.util.List;
import java.math.BigDecimal;

import org.apache.turbine.util.RunData;
import org.apache.turbine.om.security.User;
import org.apache.torque.util.Criteria;
import org.apache.velocity.context.Context;

import org.iitk.brihaspati.om.Courses;
import org.iitk.brihaspati.om.CoursesPeer;
import org.iitk.brihaspati.modules.utils.UserUtil;
import org.iitk.brihaspati.modules.utils.QuotaUtil;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.iitk.brihaspati.modules.utils.MailNotificationThread;
import org.iitk.brihaspati.modules.screens.call.SecureScreen_Instructor;

/**
 * This class Upload file for course Answer book
 * @author <a href="mailto:nksinghiitk@yahoo.co.in">Nagendra Kumar Singh</a>
 *
 */
public class UploadAnsBook extends SecureScreen_Instructor {

	public void doBuildTemplate(RunData data,Context context)
	{
		try{
		
	//	Vector v=new Vector();
		User user=data.getUser();
		String uName=user.getName();
                int uid=UserUtil.getUID(uName);
//		ParameterParser pp=data.getParameters(); 	
		String dir=(String)user.getTemp("course_id");
		context.put("course",(String)user.getTemp("course_name"));

		String filePath1=data.getServletContext().getRealPath("/Courses")+"/"+dir;		
		File dirHandle1=new File(filePath1);
		long unpdir=QuotaUtil.getDirSizeInMegabytes(dirHandle1);
		context.put("TUSize",unpdir);

		String filePath=data.getServletContext().getRealPath("/Courses")+"/"+dir+"/AnsCopy";		
		File dirHandle=new File(filePath);
	//	File UnpubDir=null;
	//	String filter[]={"Permission","Remotecourse","content__des.xml"};
  //        //      NotInclude exclude=new  NotInclude(filter);
	//	String file[]=dirHandle.list(exclude);
//	//	long unpdir=0;
	//	for(int i=0;i<file.length;i++)
	//	{
	//		v.addElement(file[i]);
	//	}
	//	context.put("allTopics",v);
		long tlmt=0;



		Criteria crit=new Criteria();
		crit.add(CoursesPeer.GROUP_NAME,dir);
		List lst=CoursesPeer.doSelect(crit);
		for(int i=0;i<lst.size();i++)
		{
			Courses crs=(Courses)lst.get(i);
                        BigDecimal qt=crs.getQuota();
			tlmt=qt.longValue(); 
			//get total size in MB
		}	
		long remlmt=tlmt-unpdir;
		context.put("aSize",(remlmt));
		 /*
                  *method for how much time user spend in this page.
                  */

		String Role = (String)user.getTemp("role");
		if((Role.equals("student")) || (Role.equals("instructor")))
                {
                       //CourseTimeUtil.getCalculation(uid);
                       //ModuleTimeUtil.getModuleCalculation(uid);
			int eid=0;
		       MailNotificationThread.getController().CourseTimeSystem(uid,eid);
                }

		}catch(Exception ex){data.setMessage("the error in upload screens !!"+ex);}
	}
}

