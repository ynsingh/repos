package org.iitk.brihaspati.modules.screens.call.OLES;

/*
 * @(#)Quiz_Detail.java	
 *
 *	Copyright (c) 2010 DEI Agra.
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
 *  Contributors: Members of MHRD Project, DEI Agra
 * 
 */

import java.util.List;
import java.util.Vector;
import org.iitk.brihaspati.om.Quiz;
import org.iitk.brihaspati.om.QuizPeer;
import org.apache.torque.util.Criteria;
import org.apache.turbine.util.RunData;
import org.apache.turbine.om.security.User;
import org.apache.velocity.context.Context;
import org.iitk.brihaspati.modules.utils.UserUtil;
import org.iitk.brihaspati.modules.utils.GroupUtil;
import org.iitk.brihaspati.modules.utils.CourseUtil;
import org.iitk.brihaspati.modules.utils.UserGroupRoleUtil;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.iitk.brihaspati.modules.screens.call.SecureScreen;
import org.apache.turbine.services.servlet.TurbineServlet;
import org.iitk.brihaspati.modules.utils.QuizDetail;
import org.apache.turbine.util.parser.ParameterParser;
import java.io.File;
import org.iitk.brihaspati.modules.utils.QuizMetaDataXmlWriter;
import org.iitk.brihaspati.modules.utils.QuizMetaDataXmlReader;
import org.iitk.brihaspati.modules.utils.CommonUtility;

/**
 *   This class contains code for displaying list of all active quiz 
 *   (active quiz=quizes which are already created)
 *   @author<a href="noopur.here@"gmail.com>Nupur Dixit</a>
 */


public class Quiz_Detail extends SecureScreen{
    	public void doBuildTemplate( RunData data, Context context ){
		try {			
            User user=data.getUser();
            String uname=user.getName();
            int userid=UserUtil.getUID(uname);
            String uid=Integer.toString(userid);
            String courseid=(String)user.getTemp("course_id");
            ParameterParser pp=data.getParameters();
            context.put("tdcolor",pp.getString("count",""));
			context.put("course",(String)user.getTemp("course_name"));
			String mode =pp.getString("mode","");
			context.put("mode",mode);
			String checkstatus=pp.getString("checkstatus","");

			String filePath=data.getServletContext().getRealPath("/Courses"+"/"+courseid+"/Exam/");
			QuizMetaDataXmlReader quizmetadata=null;
            Vector allQuiz=new Vector();
            String quizPath="/Quiz.xml";
			File f=new File(filePath+"/"+quizPath);
			ErrorDumpUtil.ErrorLog("\nquiz path"+f.getName());
			if(f.exists()){
				quizmetadata=new QuizMetaDataXmlReader(filePath+"/"+quizPath);				
				allQuiz=quizmetadata.getActiveQuiz_Detail();
				ErrorDumpUtil.ErrorLog("\nallQuizes"+allQuiz);
			}
			if(allQuiz==null)
                return;
                if(allQuiz.size()!=0){
                   	checkstatus="NoBlank";
                   	context.put("allQuiz",allQuiz);
//                    CommonUtility.PListing(data,context,allQuiz);
                }
               	else{
                   	checkstatus="blank";
               	}
			context.put("checkstatus",checkstatus);
		}//try
		        
		catch(Exception ex){
			ErrorDumpUtil.ErrorLog("The exception in detail quiz file!!"+ex);
			data.setMessage("See ExceptionLog !! ");
        }
	}
}


