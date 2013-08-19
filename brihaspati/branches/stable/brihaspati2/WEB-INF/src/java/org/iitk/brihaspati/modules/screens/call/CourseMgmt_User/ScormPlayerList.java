package org.iitk.brihaspati.modules.screens.call.CourseMgmt_User;

/*
 * @(#)ScormPlayerList.java	
 *
 *  Copyright (c) 2009,2012 ETRG,IIT Kanpur. 
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
/**
 *This class contains code for the total list of scorm package
 *@author: <a href="mailto:seema_020504@yahoo.com">Seemapal</a>
 *@author: <a href="mailto:kshuklak@rediffmail.com">Kishore Kumar shukla</a>
 */

import java.io.File;
import java.util.Vector;
import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;
import org.apache.turbine.om.security.User;
import org.apache.turbine.util.parser.ParameterParser;
import org.apache.turbine.services.servlet.TurbineServlet;
import org.iitk.brihaspati.modules.screens.call.SecureScreen;
import org.iitk.brihaspati.modules.utils.XmlWriter;
import org.iitk.brihaspati.modules.utils.TopicMetaDataXmlWriter;
import org.iitk.brihaspati.modules.utils.TopicMetaDataXmlReader;
import org.iitk.brihaspati.modules.utils.AdminProperties;
import org.iitk.brihaspati.modules.utils.ListManagement;
import org.iitk.brihaspati.modules.utils.FileEntry;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;

import org.iitk.brihaspati.modules.utils.UserUtil;
import org.iitk.brihaspati.modules.utils.ModuleTimeThread;

 public class ScormPlayerList extends SecureScreen 
  {
  
	public void doBuildTemplate( RunData data, Context context )
    	{
		try
		{
			User user=data.getUser();
                	ParameterParser pp=data.getParameters();
			String course_id=(String)user.getTemp("course_id");
                	context.put("courseid",course_id);
			context.put("course",(String)user.getTemp("course_name"));
                        context.put("tdcolor",pp.getString("count",""));
                	String Role=(String)user.getTemp("role");
                	context.put("user_role",Role);
			String zip_topic=pp.getString("ziptopic","");
                	context.put("ziptopic",zip_topic);
			String zip_file=pp.getString("zipfile","");
                	context.put("zipfile",zip_file);
                	String mode=pp.getString("mode"," ");
                	context.put("mode",mode);
                	String type=pp.getString("type","");
                	context.put("type",type);
			String inst_id=(String)user.getTemp("Institute_id");
			/**
                         *Time calculaion for how long user use this page.
                         */
                         int uid=UserUtil.getUID(user.getName());
                         if(((Role.equals("student")) || (Role.equals("instructor")) || Role.equals("teacher_assistant")))
                         {
				int eid=0;
				ModuleTimeThread.getController().CourseTimeSystem(uid,eid);
                         }

			String Mode=new String();
                        File scormDir=new File(TurbineServlet.getRealPath("/Courses")+"/"+course_id+"/Scormpackage/");
                        if(!scormDir.exists())
                                scormDir.mkdirs();
			String courseRealPath=TurbineServlet.getRealPath("/Courses");
			String filepath=(courseRealPath+"/"+course_id+"/"+"Scormpackage");
			File pathlistxml= new File(filepath+"/PackageList.xml");
                        if(!pathlistxml.exists())
                                TopicMetaDataXmlWriter.writeWithRootOnly(pathlistxml.getAbsolutePath());
			String xmlfile="/PackageList.xml";
			XmlWriter xmlWriter=null;
			TopicMetaDataXmlReader topicmetadata=null;
                	Vector topicList=new Vector();
			boolean found=false;
			String PDate="",topicname="",status="";
			topicmetadata=new TopicMetaDataXmlReader(filepath+xmlfile);
			if((Role.equals("instructor")) || (Role.equals("teacher_assistant")))
                		topicList=topicmetadata.getFileDetails();
			else
				topicList=ReadEntry(filepath,xmlfile);
          		if(topicList==null)
                                return;
                                if(topicList.size()!=0)
                                {
                                       String path=data.getServletContext().getRealPath("/WEB-INF")+"/conf"+"/"+"InstituteProfileDir"+"/"+inst_id+"Admin.properties";
                                       String AdminConf = AdminProperties.getValue(path,"brihaspati.admin.listconfiguration.value");
                                       context.put("userConf",new Integer(AdminConf));
                                       context.put("userConf_string",AdminConf);

                                        int startIndex=pp.getInt("startIndex",0);
                                        int t_size=topicList.size();
                                        Mode="NoBlank";
                                        int value[]=new int[7];
                                        value=ListManagement.linkVisibility(startIndex,t_size,Integer.parseInt(AdminConf));
                                        int k=value[6];
                                        context.put("k",String.valueOf(k));
                                        Integer total_size=new Integer(t_size);
                                        context.put("total_size",total_size);

                                        int eI=value[1];
                                        Integer endIndex=new Integer(eI);
                                        context.put("endIndex",endIndex);
                                        //check_first shows the first five records
                                        int check_first=value[2];
                                        context.put("check_first",String.valueOf(check_first));
                                        //check_pre shows the first the previous list to the current records
                                        int check_pre=value[3];
                                        context.put("check_pre",String.valueOf(check_pre));
                                        //check_last1 gives the remainder values:-
                                        int check_last1=value[4];
                                        context.put("check_last1",String.valueOf(check_last1));
                                        //check_last shows the last records:-
                                        int check_last=value[5];
                                        context.put("check_last",String.valueOf(check_last));
                                        context.put("startIndex",String.valueOf(eI));
					Vector splitlist=ListManagement.listDivide(topicList,startIndex,Integer.parseInt(AdminConf));
                                        context.put("allTopics",splitlist);
				}
                                else
                                Mode="Blank";
                                context.put("Mode",Mode);

		}
		catch(Exception e)
		{
			data.setMessage("The Error in ScormPlayerList screen !! "+e);
		}
    	}
	public  Vector ReadEntry(String filePath,String xmlfile)
        {
                Vector Read=new Vector() ;
                try
                {
                        XmlWriter xmlWriter=null;
                        TopicMetaDataXmlReader tr =new TopicMetaDataXmlReader(filePath+xmlfile);
                        Vector Readtr=tr.getFileDetails();
                        if(Read != null)
                        {
                                for(int n=0;n<Readtr.size();n++)
                                {
                                        String name =((FileEntry)Readtr.elementAt(n)).getName();
                                        String Alias =((FileEntry)Readtr.elementAt(n)).getAlias();
                                        if(Alias.equals("Launch"))
                                        {
						FileEntry filelist=new FileEntry();
						filelist.setName(name);
                                                filelist.setAlias(Alias);
                                                Read.addElement(filelist);
                                        }
                                }
                        }
                }//try
                catch(Exception e){ErrorDumpUtil.ErrorLog("Error in method [ScormPlayerList]:ReadEntry !!"+e);}
                return Read;
        }//readmethod
}
