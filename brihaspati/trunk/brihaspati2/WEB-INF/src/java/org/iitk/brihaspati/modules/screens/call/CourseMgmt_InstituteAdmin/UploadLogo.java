package org.iitk.brihaspati.modules.screens.call.CourseMgmt_InstituteAdmin;
/*
 * @(#) UploadLogo.java
 *
 *  Copyright (c) 2010 ETRG,IIT Kanpur.
 *  All Rights Reserved.
 *
 *
 *
 *  Contributors: Members of Brihaspati Software Solutions  Kanpur
 *
 */
import org.apache.velocity.context.Context;
import org.apache.turbine.util.RunData;
//import org.apache.turbine.om.security.User;
import org.apache.turbine.util.parser.ParameterParser;
//import org.apache.turbine.services.servlet.TurbineServlet;	
//import org.iitk.brihaspati.modules.utils.TopicMetaDataXmlReader;
//import org.iitk.brihaspati.modules.utils.FileEntry;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;

//import java.util.Vector;
//import org.iitk.brihaspati.modules.screens.call.SecureScreen_Instructor;
import org.iitk.brihaspati.modules.screens.call.SecureScreen_Institute_Admin;


/**
 * This class is used for Upload Institute Logo
 *
 */
	
public class UploadLogo extends SecureScreen_Institute_Admin {

	public void doBuildTemplate(RunData data,Context context)
	{
		try {
			ParameterParser pp = data.getParameters();
//        		String mode = pp.getString("mode","UploadImage");
//			User user=data.getUser();
//			String Courseid=(String)user.getTemp("course_name");	
//			String courseid=(String)user.getTemp("course_id","");
//			context.put("course",Courseid);
//			context.put("mode",mode);
			context.put("tdcolor",pp.getString("count",""));
		}catch(Exception e){ErrorDumpUtil.ErrorLog(e.getMessage());}
	}
}

