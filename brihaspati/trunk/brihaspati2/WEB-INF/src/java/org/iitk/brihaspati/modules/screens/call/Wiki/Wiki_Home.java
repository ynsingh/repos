package org.iitk.brihaspati.modules.screens.call.Wiki;    

/*
 * @(#)Wiki_Home.java	
 *
 *  Copyright (c) 2012 ETRG,IIT Kanpur. 
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
import org.iitk.brihaspati.modules.utils.UserUtil;
import org.iitk.brihaspati.modules.screens.call.SecureScreen;    
import java.util.Vector;
import java.util.Arrays;
import java.io.File;
import org.iitk.brihaspati.modules.utils.WikiUtil;
import org.iitk.brihaspati.modules.utils.CourseUtil;
import org.iitk.brihaspati.modules.utils.InstituteIdUtil;
import org.iitk.brihaspati.modules.utils.CourseManagement;
import org.apache.velocity.context.Context; 
import org.apache.turbine.util.RunData;
import org.apache.turbine.util.parser.ParameterParser;
import org.apache.turbine.om.security.User;  
import org.iitk.brihaspati.modules.utils.MultilingualUtil;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import java.io.BufferedReader;
import org.iitk.brihaspati.modules.utils.CourseUserDetail;
import org.iitk.brihaspati.modules.utils.StudentInstructorMAP;
import org.iitk.brihaspati.modules.utils.NoticeUnreadMsg;
import java.io.FileReader;
import java.util.List;
import org.iitk.brihaspati.modules.utils.AssignmentDetail;
import org.iitk.brihaspati.om.CoursesPeer;
import org.iitk.brihaspati.om.Courses;
import org.apache.torque.util.Criteria;
import java.io.*;
import org.iitk.brihaspati.modules.utils.ListManagement;

/**
 * @author <a href="mailto:sunil.singh6094@gmail.com">Sunil Kumar Pal</a>
 */

public class Wiki_Home extends SecureScreen{

	/**
	 * This is the default method that builds the template page
	 * @param data Rundata
	 * @param context Context
	 */

	public void doBuildTemplate(RunData data,Context context) 
	{
		String LangFile= null;
		try{	
			User user=data.getUser();
			LangFile =(String)user.getTemp("LangFile");
			ParameterParser pp=data.getParameters();
			String mode=pp.getString("mode","");
			String filenameforedit=pp.getString("filename","");
                        String filename="brihaspati3";
                        if(!filenameforedit.equals("")){
                                filename=filenameforedit;
                        }

                        String cId=(String)user.getTemp("course_id");
			context.put("filename",filename);
                        context.put("mode",mode);
			String username=user.getName();
			String firstname=user.getFirstName();
                        String lastname=user.getLastName();
			context.put("courseid",cId);
			context.put("course",(String)user.getTemp("course_name"));
			/**
			* check if user is primary instructor
			* as he alone can access Adminwiki.vm
			*/

			String filePath=data.getServletContext().getRealPath("/WIKI"+"/"+cId+"/");
                        String filePathH=filePath + "/Wikihistory/" ;
                        String filePathF=filePath + "/Wikifirst/" ;
                        String filePathL=filePath + "/Wikilast/" ;
                        String filePathLog=filePath + "/Wikilog/";

                        File f=new File(filePathH);
                        if(!f.exists())
                                f.mkdir();
                        f=new File(filePathF);
                        if(!f.exists())
                                f.mkdir();
                        f=new File(filePathL);
                        if(!f.exists())
                                f.mkdir();
                        f=new File(filePathLog);
                        if(!f.exists())
                                f.mkdir();
                        String wikipath=data.getServletContext().getRealPath("/WIKI")+"/"+cId+"/Wikilast/brihaspati3";
                        wikipath="";
                        Vector FileViewId_tiopic1=new Vector();
                        String msg="";
                        String topic_name_file_name="";
                        Vector v = new Vector();
                                try {
                                        wikipath=data.getServletContext().getRealPath("/WIKI")+"/"+cId+"/Wikilast/"+filename;//+ContentList[s];
                                        if(new java.io.File(wikipath).exists()) {
                                                BufferedReader br=new BufferedReader(new FileReader(wikipath));
                                                String s2=null;
                                                while((s2=br.readLine())!=null)
                                                {
                                                        msg=msg+"wde"+s2;
                                                }
                                        }
                                }catch(Exception e){}
				//}
			//}
			File flog;
			flog=new File(filePathLog);
			context.put("topic",FileViewId_tiopic1);
                        context.put("topic_wiki",msg);
                        context.put("v",v);
                        context.put("topic_name_file_name",filename);
		}//try
		catch(Exception e)
		{
			data.setMessage("Error in screen call,Wiki,Wiki.java is ========>  "+ e);

		}
	}
}
