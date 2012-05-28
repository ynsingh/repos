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
import  org.iitk.brihaspati.modules.screens.call.SecureScreen;    
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

/**
 * @author <a href="mailto:sunil.singh6094@gmail.com">Sunil Kumar Pal</a>
 *
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
                        context.put("mode",mode);
			String username=user.getName();
			String firstname=user.getFirstName();
                        String lastname=user.getLastName();
			String cId=(String)user.getTemp("course_id");
			context.put("courseid",cId);
			context.put("course",(String)user.getTemp("course_name"));
                        String userrole=(String)user.getTemp("role");
			context.put("userrole",userrole);
			// this is use for set Alias from Course table
			Criteria crit=new Criteria();
                        crit.add(CoursesPeer.GROUP_NAME,cId);
                        List lst=CoursesPeer.doSelect(crit);
			for(int i=0;i<lst.size();i++){
				Courses element=(Courses)(lst.get(i));
                                //String subject=(element.getGroupAlias());
                                String subject=(element.getCname());
		                context.put("subject",subject);
			}

			/**
			* check if user is primary instructor
			* as he alone can access Adminwiki.vm
			*/
			String name="";
			boolean check_Primary=CourseManagement.IsPrimaryInstructor(cId,username);
			if(check_Primary)
			context.put("role","instructor");			
			else
			context.put("role","");
			WikiUtil ol = new WikiUtil();
			int i=0;
			Vector all=new Vector();
			String filePath=data.getServletContext().getRealPath("/WIKI"+"/"+cId+"/");
			String filePathH=filePath + "/Wikihistory/" ;
			String filePathF=filePath + "/Wikifirst/" ;
			String filePathL=filePath + "/Wikilast/" ;
			String filePathLog=filePath + "/Wikilog/";
                       	String wikipath=data.getServletContext().getRealPath("/WIKI")+"/"+cId+"/Wikilast";
			File topicDir1=new File(wikipath);
                        String ContentList[]=topicDir1.list();
			wikipath="";
                        Vector FileViewId_tiopic1=new Vector();
                        for(int s=0;s<ContentList.length;s++){
				if(ContentList[s].lastIndexOf(",v")<0){
					AssignmentDetail assignmentdetail=new AssignmentDetail();
                       			wikipath=data.getServletContext().getRealPath("/WIKI")+"/"+cId+"/Wikilast/"+ContentList[s];
					BufferedReader br=new BufferedReader(new FileReader (wikipath));
					String msg="";
					String str;
                        		while ((str=br.readLine()) != null) {
		                                msg=msg+str;
                		        }
                                       	assignmentdetail.setStudentname(ContentList[s]);
                        		assignmentdetail.setStudentfile(msg);
                        		FileViewId_tiopic1.add(assignmentdetail);
					wikipath="";
				}
			}
			File flog;
			flog=new File(filePathLog);
			context.put("topic",FileViewId_tiopic1);
			File Ftr[]=flog.listFiles();

                        Arrays.sort(Ftr);
                        for(i=0;i<Ftr.length;i++)
                        {
                               all.addElement(Ftr[i]);
                        }

                        context.put("dirContent",all);
			//ErrorDumpUtil.ErrorLog("\n alll===>>"+all);
		}//try
		catch(Exception e)
		{
			data.setMessage("Error in screen call,Wiki,Wiki.java is ========>  "+ e);

		}
	}
}
