package org.iitk.brihaspati.modules.screens.call.OLES;

/*
 * @(#)Oles_QB.java	
 *
 *  Copyright (c) 2010 ETRG,IIT Kanpur. 
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

//Jdk
import java.io.File;
import java.util.Vector;
//Turbine
import org.apache.velocity.context.Context;
import org.apache.turbine.util.RunData;
import org.apache.turbine.om.security.User;
import org.apache.turbine.util.parser.ParameterParser;
//brihaspati
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.iitk.brihaspati.modules.utils.AdminProperties;
import org.iitk.brihaspati.modules.utils.ListManagement;
import org.iitk.brihaspati.modules.screens.call.SecureScreen;
import org.iitk.brihaspati.modules.utils.TopicMetaDataXmlWriter;
import org.iitk.brihaspati.modules.utils.TopicMetaDataXmlReader;
import org.iitk.brihaspati.modules.utils.FileEntry;
import org.iitk.brihaspati.modules.utils.MultilingualUtil;

import org.iitk.brihaspati.modules.utils.UserUtil;
//import org.iitk.brihaspati.modules.utils.CourseTimeUtil;
//import org.iitk.brihaspati.modules.utils.ModuleTimeUtil;
import org.iitk.brihaspati.modules.utils.MailNotificationThread;

/**
* This class manage all online examination system 
* @author <a href="mailto:palseema30@gmail.com">Manorama Pal</a>
*/

public class Oles_QB extends SecureScreen{

	public void doBuildTemplate(RunData data,Context context) 
	{
		String lang=data.getUser().getTemp("LangFile").toString();
		try{

			User user=data.getUser();
			String crsId=(String)data.getUser().getTemp("course_id");
			String username=data.getUser().getName();
			ParameterParser pp=data.getParameters();
			context.put("tdcolor",pp.getString("count","1"));
			context.put("course",(String)user.getTemp("course_name"));
			String mode =pp.getString("mode","");
			context.put("mode",mode);
			String topic=pp.getString("topic","");
			context.put("topic",topic);
			String Role = (String)user.getTemp("role");
			/**
                         *Time calculaion for how long user use this page.
                         */
                         int uid=UserUtil.getUID(user.getName());
                         if((Role.equals("student")) || (Role.equals("instructor")))
                         {
                                //CourseTimeUtil.getCalculation(uid);
                                //ModuleTimeUtil.getModuleCalculation(uid);
				 MailNotificationThread.getController().CourseTimeSystem(uid);
                         }

			String instid=(String)user.getTemp("Institute_id");
			String checkstatus=pp.getString("checkstatus","");
			String filePath=data.getServletContext().getRealPath("/QuestionBank"+"/"+username+"/"+crsId);
			File f=new File(filePath+"/QBtopiclist.xml");
			TopicMetaDataXmlReader topicmetadata=null;
                        Vector allTopics=new Vector();
                        Vector allcomtopics=new Vector();
			if(f.exists())
                        {
				topicmetadata=new TopicMetaDataXmlReader(filePath+"/QBtopiclist.xml");
                        	allTopics=topicmetadata.getQuesBanklist_Detail();
				if(allTopics!=null){
					for(int i=0;i<allTopics.size();i++)
        	                        {//for
						String topicnew=((FileEntry) allTopics.elementAt(i)).getTopic();
						if(!allcomtopics.contains(topicnew))
                                	        {
                                                	allcomtopics.addElement(topicnew);
                                        	}
					}
				}
			}
			if(allTopics==null)
                        return;
                        if(allTopics.size()!=0)
                        {
				String path=data.getServletContext().getRealPath("/WEB-INF")+"/conf"+"/InstituteProfileDir"+"/"+instid+"Admin.properties";
                                String AdminConf = AdminProperties.getValue(path,"brihaspati.admin.listconfiguration.value");
                                context.put("userConf",new Integer(AdminConf));
                                context.put("userConf_string",AdminConf);
	                        int startIndex=pp.getInt("startIndex",0);
                                int t_size=allcomtopics.size();
                               	checkstatus="NoBlank";

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
                                Vector splitlist=ListManagement.listDivide(allcomtopics,startIndex,Integer.parseInt(AdminConf));
                                context.put("allTopics",splitlist);

                       	}
                       	else
                       	{
                               	checkstatus="blank";
                       	}
			context.put("checkstatus",checkstatus);
		}//try
		catch(Exception ex)
		{
		data.setMessage(MultilingualUtil.ConvertedString("brih_exception"+ex,lang));
		}
	}
}

