package org.iitk.brihaspati.modules.screens.call.OLES;


/* @(#)Insert_Multiple.java
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
/**
 * @author <a href="mailto:palseema30@gmail.com">Manorama Pal</a>
 */
//Jdk
import java.util.Vector;
//apache
import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;
import org.apache.turbine.om.security.User;
import org.apache.turbine.services.servlet.TurbineServlet;
//Brihaspati
import org.iitk.brihaspati.modules.utils.FileEntry;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil; 
import org.apache.turbine.util.parser.ParameterParser;  
import org.iitk.brihaspati.modules.utils.TopicMetaDataXmlReader;
import org.iitk.brihaspati.modules.screens.call.SecureScreen; 
//import org.iitk.brihaspati.modules.utils.CourseTimeUtil;
//import org.iitk.brihaspati.modules.utils.ModuleTimeUtil;
import org.iitk.brihaspati.modules.utils.MailNotificationThread;
import org.iitk.brihaspati.modules.utils.UserUtil;
public class Insert_Multiple extends SecureScreen
{
    
    /*
     * Places all the data objects in the context for further use
     */
 	String QuestionBankPath=TurbineServlet.getRealPath("/QuestionBank"); 

	public void doBuildTemplate(RunData data,Context context)
	{
		try
		{
			ParameterParser pp=data.getParameters();
			User user=data.getUser();
			String crsId=(String)data.getUser().getTemp("course_id");
                	String username=data.getUser().getName();
			context.put("tdcolor",pp.getString("count",""));
			context.put("course",(String)user.getTemp("course_name"));
        		String mode=pp.getString("mode","");
        		context.put("mode",mode);
			String topic=pp.getString("Topicname","");
                	context.put("Topicname",topic);
                	String typeques=pp.getString("typeques","");
                	context.put("typeques",typeques);
			String Questype=pp.getString("valQuestype","");
                	context.put("valQuestype",Questype);
                	String difflevel=pp.getString("valdifflevel","");
                	context.put("valdifflevel",difflevel);
			String Ques=pp.getString("Question","");
                	String Answer=pp.getString("Answer","");
                	String Desc=pp.getString("hint","");
                	String option1=pp.getString("op1","");
                	String option2=pp.getString("op2","");
                	String option3=pp.getString("op3","");
                	String option4=pp.getString("op4","");
			String actype=pp.getString("acttype","");
                	context.put("acttype",actype);
			if((actype.equals("editques"))||(actype.equals("viewques")))
			{
				String edtopic=pp.getString("topic","");
                		context.put("topic",edtopic);
				String quesid=pp.getString("quesid","");
				String questiontype=pp.getString("questype","");
				context.put("questype",questiontype);
				String difflevel12=pp.getString("difflevel","");
				context.put("difflevel",difflevel12);
				String fulltopic=edtopic+"_"+difflevel12+"_"+questiontype;
				String filepath=QuestionBankPath+"/"+username+"/"+crsId;
				Vector Read=new Vector();
				TopicMetaDataXmlReader tr=null;
				tr =new TopicMetaDataXmlReader(filepath+"/"+fulltopic+".xml");
                        	Read=tr.getQuesBank_Detail();
				if(Read != null)
                        	{
                        	        for(int n=0;n<Read.size();n++)
                        	        {
                                	        String questionid=((FileEntry)Read.elementAt(n)).getquestionid();
                                	        String ques=((FileEntry)Read.elementAt(n)).getquestion();
                                	        String opt1=((FileEntry)Read.elementAt(n)).getoptionA();
                                	        String opt2=((FileEntry)Read.elementAt(n)).getoptionB();
                                	        String opt3=((FileEntry)Read.elementAt(n)).getoptionC();
                                	        String opt4=((FileEntry)Read.elementAt(n)).getoptionD();
                                        	String Ans=((FileEntry)Read.elementAt(n)).getAnswer();
                                        	String desc=((FileEntry)Read.elementAt(n)).getDescription();
                                        	if(questionid.equals(quesid))
                                        	{
							context.put("quesid",questionid);
                					context.put("Ques",ques);
                					context.put("opt1",opt1);
                					context.put("opt2",opt2);
                					context.put("opt3",opt3);
                					context.put("opt4",opt4);
                					context.put("Ans",Ans);
                					context.put("Desc",desc);
                                        	}
                                	}
                        	}
			}
			/**
                         *Time calculaion for how long user use this page.
                         */
			 String Role = (String)user.getTemp("role");
			 int userid=UserUtil.getUID(user.getName());
                         if((Role.equals("student")) || (Role.equals("instructor")))
                         {
                                //CourseTimeUtil.getCalculation(userid);
                                //ModuleTimeUtil.getModuleCalculation(userid);
				MailNotificationThread.getController().CourseTimeSystem(userid);
                         }

		}//try
                catch(Exception e){
                                   ErrorDumpUtil.ErrorLog("Error in screen[Insert_Multiple] !!"+e);
                                   data.setMessage("See ExceptionLog !! " );
                                }
	}
}

