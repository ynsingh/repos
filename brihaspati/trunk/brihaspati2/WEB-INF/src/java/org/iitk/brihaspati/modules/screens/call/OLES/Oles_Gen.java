package org.iitk.brihaspati.modules.screens.call.OLES;

/*
 * @(#)Oles_Gen.java	
 *
 *  Copyright (c) 2010 DEI Agra. 
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
 *  Contributors: Members of MHRD Project DEI Agra 
 * 
 */

//Turbine
import java.util.Vector;

import org.apache.velocity.context.Context;
import org.apache.turbine.util.RunData;
import org.apache.turbine.om.security.User;
import org.apache.turbine.util.parser.ParameterParser;
//brihaspati
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.iitk.brihaspati.modules.screens.call.SecureScreen;
import org.iitk.brihaspati.modules.utils.QuizMetaDataXmlWriter;
import org.iitk.brihaspati.modules.utils.QuizMetaDataXmlReader;
import org.iitk.brihaspati.modules.utils.CommonUtility;
import java.io.File;


/**
* This class manage Quiz Generation process 
* @author <a href="mailto:noopur.here@gmail.com">Nupur Dixit</a>
*/

public class Oles_Gen extends SecureScreen{

	public void doBuildTemplate(RunData data,Context context) {		
		
		try{
			ErrorDumpUtil.ErrorLog("hello");
			User user=data.getUser();
			ParameterParser pp=data.getParameters();
			context.put("tdcolor",pp.getString("count",""));
			context.put("course",(String)user.getTemp("course_name"));
			String crsId=(String)data.getUser().getTemp("course_id");
			String mode =pp.getString("mode","");
			context.put("mode",mode);
			String quizName=pp.getString("quizName","");
			context.put("quizName",quizName);
			String quizID=pp.getString("quizID","");
			context.put("quizID",quizID);
			String maxMarks=pp.getString("maxMarks","");
			context.put("maxMarks",maxMarks);
			String maxTime=pp.getString("maxTime","");
			context.put("maxTime",maxTime);
			String noQuestion=pp.getString("noQuestion","");
			context.put("noQuestion",noQuestion);
			String status=pp.getString("status","");
            context.put("status",status);
            String type =pp.getString("type","");
			context.put("type",type);
			String checkstatus=pp.getString("checkstatus","");
			ErrorDumpUtil.ErrorLog("\n type==========="+type);
			ErrorDumpUtil.ErrorLog("\n quiz name======"+quizName+"\nmaxTime====="+maxTime);
			//====================================
			if(mode.equals("QuizDetail"))
			{
				String qname=pp.getString("qname","");
				context.put("qid",qname);
				String quizMode=pp.getString("quizMode","");
				context.put("quizMode",quizMode);
				String filePath=data.getServletContext().getRealPath("/Courses"+"/"+crsId+"/Exam/");
				QuizMetaDataXmlReader quizmetadata=null;
	            Vector allQuiz=new Vector();
	            String quizPath="/Quiz.xml";
//				String fulltopic=topic+"_"+difflevel+"_"+questiontype;
				File f=new File(filePath+"/"+quizPath);
				ErrorDumpUtil.ErrorLog("\nquiz id"+qname);
				if(f.exists()){
					quizmetadata=new QuizMetaDataXmlReader(filePath+"/"+quizPath);				
					allQuiz=quizmetadata.getQuiz_Detail(qname);
					ErrorDumpUtil.ErrorLog("\nallQuizes"+allQuiz);
				}
				if(allQuiz==null)
	                return;
	                if(allQuiz.size()!=0){
	                   	checkstatus="NoBlank";
	                   	context.put("allQuiz",allQuiz);	                   	
//	                    CommonUtility.PListing(data,context,allQuiz);
	                }
	               	else{
	                   	checkstatus="blank";
	               	}
				context.put("checkstatus",checkstatus);
				
			}			
			//==================================================
			if(!mode.equals("QuizDetail")) {
				String filePath=data.getServletContext().getRealPath("/Courses"+"/"+crsId+"/Exam/");
				QuizMetaDataXmlReader quizmetadata=null;
	            Vector allQuiz=new Vector();
	            String maxQuizID = "";
	            String quizPath="/Quiz.xml";
				File f=new File(filePath+"/"+quizPath);
//				ErrorDumpUtil.ErrorLog("\nquiz id"+qname);
				if(f.exists()){
					quizmetadata=new QuizMetaDataXmlReader(filePath+"/"+quizPath);				
					maxQuizID=quizmetadata.getMaxQuizID();
					context.put("qid",maxQuizID);
//					quizmetadata.getMaxQuizID();
//					ErrorDumpUtil.ErrorLog("\nallQuizes"+allQuiz);
				}
				else{
					context.put("qid","Quiz1");
				}
//				if(allQuiz==null)
//	                return;
//	                if(allQuiz.size()!=0){
//	                   	checkstatus="NoBlank";
//	                   	context.put("allQuiz",allQuiz);	                   	
//	                    CommonUtility.PListing(data,context,allQuiz);
//	                }
//	               	else{
//	                   	checkstatus="blank";
//	               	}
//				context.put("checkstatus",checkstatus);
//				
//				
//				context.put("cmonth",cmonth);
//				context.put("totaltime","notBlockUpdate");
//                context.put("cdays",cday);
//				Criteria crit=new Criteria();
//            	crit.add(QuizPeer.CID,courseid);
//            	crit.addAscendingOrderByColumn(QuizPeer.ID);
//	        	List list=QuizPeer.doSelect(crit);
//				if(list.size()!=0){
//					String ele="";
//					for(int i=0;i<list.size();i++){
//						ele=(((Quiz)(list.get(i))).getQuizId()).toString();
//					}
//					int num=Integer.parseInt(ele.substring(4));
//					context.put("qid","quiz"+(num+1));
//				}
//				else{
//					context.put("qid","quiz1");
//				}
			}
			
			//=====================================================
		}//try
		
		catch(Exception ex)
		{
		data.setMessage("The error in Oles_Gen !! "+ex);
		}
	}
}

