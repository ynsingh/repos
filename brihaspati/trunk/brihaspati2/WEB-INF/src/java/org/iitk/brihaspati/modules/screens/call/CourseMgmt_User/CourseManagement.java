package org.iitk.brihaspati.modules.screens.call.CourseMgmt_User;

/*
 * @(#)CourseManagement.java	
 *
 *  Copyright (c) 2005,2009,2010 ETRG,IIT Kanpur. 
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
import java.util.Vector;
import java.util.List;
import java.io.File;
import java.math.BigDecimal;
import org.apache.turbine.util.RunData;
import org.apache.torque.util.Criteria;
import org.apache.velocity.context.Context;
import org.apache.turbine.om.security.User;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.iitk.brihaspati.modules.utils.QuotaUtil;
import org.iitk.brihaspati.modules.utils.AdminProperties;
import org.iitk.brihaspati.modules.utils.NotInclude;
import org.iitk.brihaspati.modules.utils.UserUtil;
import org.iitk.brihaspati.modules.utils.TopicMetaDataXmlReader;
import org.iitk.brihaspati.modules.utils.FileEntry;
import org.apache.turbine.util.parser.ParameterParser;
import org.iitk.brihaspati.modules.screens.call.SecureScreen;
import org.iitk.brihaspati.om.CoursesPeer;
import org.iitk.brihaspati.om.Courses;
import org.iitk.brihaspati.modules.utils.CommonUtility;
import org.iitk.brihaspati.modules.utils.ModuleTimeThread;

/**
 * This class Upload file for course Content
 * @author <a href="mailto:ammuamit@hotmail.com">Amit Joshi</a>
 * @author <a href="mailto:awadhesh_trivedi@yahoo.co.in">Awadhesh Kumar Trivedi</a>
 * @author <a href="mailto:singh_jaivir@rediffmail.com">Jaivir Singh</a>
 *@author <a href="mailto:parasharirajeev@gmail.com">Rajeev Parashari</a>
 */
/**
 *  @author <a href="mailto:seemanti05@gmail.com">Seemanti Shukla</a>
 *  @modified date: 25-04-2016 (Seemanti);Get Max. Upload file parameter from Institute Admin's Profile. 
 */


public class CourseManagement extends SecureScreen
{
    public void doBuildTemplate( RunData data, Context context )
    {
	try
	{
		Vector v=new Vector();
                User user=data.getUser();
                String uName=user.getName();
                int uid=UserUtil.getUID(uName);
                ParameterParser pp=data.getParameters();
		 /*
                 *Time calculaion for how long user use this page.
                 */
		 String Role = (String)user.getTemp("role");
                 if((Role.equals("student")) || (Role.equals("instructor")) || (Role.equals("teacher_assistant")))
                 {
			int eid=0;
			ModuleTimeThread.getController().CourseTimeSystem(uid,eid);
                 }
                String dir=(String)user.getTemp("course_id");
                context.put("course",(String)user.getTemp("course_name"));
		String counter=pp.getString("count","");
		context.put("tdcolor",counter);
		String role=(String)user.getTemp("role");
                context.put("user_role",role);
                String filePath1=data.getServletContext().getRealPath("/Courses")+"/"+dir;
                File dirHandle1=new File(filePath1);
                long unpdir=QuotaUtil.getDirSizeInMegabytes(dirHandle1);
                context.put("TUSize",unpdir);
                String filePath=data.getServletContext().getRealPath("/Courses")+"/"+dir+"/Content";
		
		//Help Video Configuration
                         String h_Video=data.getServletContext().getRealPath("/resources")+"/youTubeLinks"+"/"+"help_Video.properties";
                         String v_Id = CommonUtility.GetBrihVideoId(h_Video,"upload_Course_Content");
                         context.put("vid",v_Id);

		/*File Path=new File(filePath+"/coursecontent__des.xml");
                        String tnme="";
                        String location="";
                        if(Path.exists())
                        {
                                TopicMetaDataXmlReader topicMetaData=new TopicMetaDataXmlReader(filePath+"/"+"coursecontent__des.xml");
                                Vector vct=topicMetaData.getFileDetailsModify();
                                if(vct.size()!=0)
                                {
                                        for(int k=0;k<vct.size();k++){
                                                tnme=((FileEntry)vct.elementAt(k)).getName();
                                                location=((FileEntry)vct.elementAt(k)).getLocation();
						if(location.equals("course")){
							v.addElement(tnme);
						}
                                        }
                                }
                        }*/
                File dirHandle=new File(filePath);
        //      File UnpubDir=null;
                String filter[]={"Permission","Remotecourse","content__des.xml","coursecontent__des.xml"};
                NotInclude exclude=new  NotInclude(filter);
                String file[]=dirHandle.list(exclude);
        //      long unpdir=0;
                for(int i=0;i<file.length;i++)
                {
                        v.addElement(file[i]);
                }
                context.put("allTopics",v);
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
                String instituteid=user.getTemp("Institute_id").toString();
                String path = data.getServletContext().getRealPath("/WEB-INF")+"/conf"+"/InstituteProfileDir/"+instituteid+"Admin.properties";
                //Get the Maximum File size parameter from Institute Admin's propeties file 
                String MaxFileUpldno = AdminProperties.getValue(path,"brihaspati.user.maxFileUploadSize.value");
                //If properties file didn't had this parameter then set it equal to 10.
                if(MaxFileUpldno ==null || MaxFileUpldno.equals("") )
                {  ErrorDumpUtil.ErrorLog("Hii i am screen......7");////////////////////////////////// 
                   int mxUpld = 10 ;
                   String MaxFileUpld_no = Integer.toString(mxUpld);
                   AdminProperties.setPropertyValue(path,MaxFileUpld_no,"brihaspati.user.maxFileUploadSize.value");
                   context.put("max_sz",(MaxFileUpld_no));
                } 
                else//Otherwise fetch the existing value of this parameter from properties file.
                   context.put("max_sz",(MaxFileUpldno));
		//ErrorDumpUtil.ErrorLog("asize at line 112=="+remlmt);

	}
	catch(Exception e)
	{
		data.setMessage("The Error in CourseManagement !! "+e);
	}
    }
}

