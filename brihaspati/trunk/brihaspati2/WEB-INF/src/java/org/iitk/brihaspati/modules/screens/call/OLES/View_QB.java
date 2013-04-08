package org.iitk.brihaspati.modules.screens.call.OLES;

/*
 * @(#)View_QB.java	
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
import org.iitk.brihaspati.modules.utils.CommonUtility;
import org.iitk.brihaspati.modules.screens.call.SecureScreen;
import org.iitk.brihaspati.modules.utils.TopicMetaDataXmlWriter;
import org.iitk.brihaspati.modules.utils.TopicMetaDataXmlReader;
import org.apache.turbine.services.servlet.TurbineServlet;
import org.iitk.brihaspati.modules.utils.AdminProperties;
import org.iitk.brihaspati.modules.utils.MultilingualUtil;
import org.iitk.brihaspati.modules.utils.UserUtil;
import org.iitk.brihaspati.modules.utils.ModuleTimeThread;
import org.iitk.brihaspati.modules.utils.ViewAllQuestionUtil;
import org.iitk.brihaspati.modules.utils.NotInclude;
//import org.apache.commons.lang.StringUtils;
//import org.iitk.brihaspati.modules.utils.XmlWriter;
//import org.iitk.brihaspati.modules.utils.FileEntry;

/**
* This class manage all online examination system 
* @author <a href="mailto:palseema30@gmail.com">Manorama Pal</a>
*/

public class View_QB extends SecureScreen{

	public void doBuildTemplate(RunData data,Context context) 
	{
		String lang=data.getUser().getTemp("LangFile").toString();
		try{

			User user=data.getUser();
			String crsId=(String)data.getUser().getTemp("course_id");
			String username=data.getUser().getName();
			ParameterParser pp=data.getParameters();
			context.put("tdcolor",pp.getString("count",""));
			context.put("course",(String)user.getTemp("course_name"));
			String mode =pp.getString("mode","");
			context.put("mode",mode);
			String topic=pp.getString("topic","");
			context.put("topic",topic);
			String questiontype=pp.getString("questype","");
			context.put("questype",questiontype);
			String difflevel=pp.getString("difflevel","");
			context.put("difflevel",difflevel);
			String checkstatus=pp.getString("checkstatus","");
			String filePath=data.getServletContext().getRealPath("/QuestionBank"+"/"+username+"/"+crsId);
			TopicMetaDataXmlReader topicmetadata=null;
                        Vector allQuestions=new Vector();
			if(!questiontype.equals("")&&(!difflevel.equals("")))
			{
				allQuestions=ViewAllQuestionUtil.ReadTopicAllFile(topic,filePath,questiontype,difflevel);
			}
			if(allQuestions==null)
                        return;
                        if(allQuestions.size()!=0)
                        {
                               	checkstatus="NoBlank";
                               	context.put("allQues",allQuestions);
				String path=TurbineServlet.getRealPath("/WEB-INF")+"/conf"+"/"+"Admin.properties";
                                String conf =AdminProperties.getValue(path,"brihaspati.admin.listconfiguration.value");
                                int list_conf=Integer.parseInt(conf);
                                context.put("userConf",new Integer(list_conf));
                                context.put("userConf_string",conf);
                                Vector vctr= CommonUtility.PListing(data ,context ,allQuestions,list_conf);
                                context.put("entry",vctr);
                       	}
                       	else
                       	{
                               	checkstatus="blank";
                       	}
			context.put("checkstatus",checkstatus);
			/**
                         *Time calculaion for how long user use this page.
                         */
			 String Role = (String)user.getTemp("role");
                         int uid=UserUtil.getUID(user.getName());
                         if((Role.equals("student")) || (Role.equals("instructor")) || (Role.equals("teacher_assistant")))
                         {
				int eid=0;
				ModuleTimeThread.getController().CourseTimeSystem(uid,eid);
                         }
		}//try
		catch(Exception ex)
		{
		data.setMessage(MultilingualUtil.ConvertedString("brih_exception"+ex,lang));
		}
	}
}

