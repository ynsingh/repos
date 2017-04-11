package org.iitk.brihaspati.modules.screens.call.OLES;


/* @(#)Insert_Short_Ag.java
 *
 *  Copyright (c) 2017 ETRG,IIT Kanpur.
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
 */
/**
 *author <a href="mailto:sharad23nov@gmail.com">Sharad Singh</a>
 *@Created date: 02-03-2017
 */
//Jdk
import java.util.Vector;
//apache
import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;
import org.apache.turbine.om.security.User;
import org.apache.turbine.util.parser.ParameterParser;  
import org.apache.turbine.services.servlet.TurbineServlet;
//Brihaspati
import org.iitk.brihaspati.modules.utils.FileEntry;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil; 
import org.iitk.brihaspati.modules.screens.call.SecureScreen; 
import org.iitk.brihaspati.modules.utils.TopicMetaDataXmlReader;
import org.iitk.brihaspati.modules.utils.UserUtil;
import org.iitk.brihaspati.modules.utils.ModuleTimeThread;
import org.iitk.brihaspati.modules.utils.ViewAllQuestionUtil;

public class Insert_Short_Ag extends SecureScreen
{
    
    /*
     * Places all the data objects in the context for further use
     */
 	String QuestionBankPath=TurbineServlet.getRealPath("/QuestionBank");
 
	public void doBuildTemplate(RunData data,Context context)
	{
		try
		{
            ErrorDumpUtil.ErrorLog("-----------------Insert_Short_Ag()------------");
			ParameterParser pp=data.getParameters();
			User user=data.getUser();
            
			String crsId=(String)data.getUser().getTemp("course_id");
           	context.put("crsId",crsId);
			String username=pp.getString("username","");
            if(username.equals("")){
               	username=data.getUser().getName();
            }
            ErrorDumpUtil.ErrorLog("Message1--->");
           	context.put("username",username);
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
           	//String Answer=pp.getString("Answer","");
            String val1=pp.getString("val1","");
            String val2=pp.getString("val2","");
            ErrorDumpUtil.ErrorLog("Ques--->"+Ques+"---val1--->"+val1+"---"+"val2--->"+val2);

           	String Desc=pp.getString("hint","");
            String actype=pp.getString("acttype","");
            context.put("acttype",actype);
           	String filepath=QuestionBankPath+"/"+username+"/"+crsId;
			Vector allQuestion=ViewAllQuestionUtil.ReadTopicAllFile(topic,filepath,Questype,difflevel);
            context.put("qsize",allQuestion);

			if((actype.equals("editques"))||(actype.equals("viewques")))
            {
                String edtopic=pp.getString("topic","");
                context.put("topic",edtopic);
                String quesid=pp.getString("quesid","");
                String questiontype=pp.getString("qtype","");
                context.put("qtype",questiontype);
				String selquestiontype=pp.getString("questype","");
                context.put("questype",selquestiontype);
                String difflevel12=pp.getString("dlevel","");
                context.put("dlevel",difflevel12);
                String seldifflevel=pp.getString("difflevel","");
                context.put("difflevel",seldifflevel);
                String fulltopic=edtopic+"_"+difflevel12+"_"+questiontype;
                Vector Read=new Vector();
               	TopicMetaDataXmlReader tr=null;
               	tr =new TopicMetaDataXmlReader(filepath+"/"+fulltopic+".xml");
               	Read=tr.getQuesBank_DetailAg();
               	if(Read != null)
               	{
               		for(int n=0;n<Read.size();n++)
                   	{

                  		String questionid=((FileEntry)Read.elementAt(n)).getquestionid();
                       	String ques=((FileEntry)Read.elementAt(n)).getquestion();
                        //modify later @sharad
                      	String minval=((FileEntry)Read.elementAt(n)).getMin();
                      	String maxval=((FileEntry)Read.elementAt(n)).getMax();
                       	String desc=((FileEntry)Read.elementAt(n)).getDescription();
                       	String Quesimage=((FileEntry)Read.elementAt(n)).getUrl();
		    			if(questionid.equals(quesid))
                      	{
                       		context.put("quesid",questionid);
                       		context.put("Ques",ques);
                   	        context.put("minval",minval);
                   	        context.put("maxval",maxval);
                   	        context.put("Desc",desc);
                  	        context.put("quesimage",Quesimage);
                            ErrorDumpUtil.ErrorLog("Ques--->"+ques+"---val1--->"+minval+"---"+"val2--->"+maxval);
				    		if(!Quesimage.equals(""))
					    		context.put("typeques","imgtypeques");
                        
				        }
				    }
                }
			/**
                         *Time calculaion for how long user use this page.
                         */
			    String Role = (String)user.getTemp("role");
                int uid=UserUtil.getUID(user.getName());
                if((Role.equals("student")) || (Role.equals("instructor")))
                {
				    int eid=0;
				    ModuleTimeThread.getController().CourseTimeSystem(uid,eid);
                }
            }
		}//try
        catch(Exception e)
        {
            ErrorDumpUtil.ErrorLog("Error in screen[Insert_short] !!"+e);
            data.setMessage("See ExceptionLog !! " );
        }
	}
}

