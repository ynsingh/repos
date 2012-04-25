package org.iitk.brihaspati.modules.screens.call.Question_Bank;


/* @(#)	InsertEdit_TF.java
 *
 *  Copyright (c) 2005-2006,2009 ETRG,IIT Kanpur.
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
 * @author <a href="mailto:tarankhan1@yahoo.com">Tarannum Khan</a>
 * @author <a href="mailto:manju_14feb@yahoo.com">Mithelesh Parihar</a>
 * @author <a href="mailto:singh_jaivir@rediffmail.com">Jaivir Singh</a>
 */

import org.iitk.brihaspati.modules.screens.call.SecureScreen; 
import org.iitk.brihaspati.modules.utils.ReadQuestionBank; 
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil; 
import org.apache.turbine.util.parser.ParameterParser;  
import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;
import org.apache.turbine.om.security.User;

public class InsertEdit_TF extends SecureScreen
{
    
    /*
     * Places all the data objects in the context for further use
     */
  
	public void doBuildTemplate(RunData data,Context context)
	{
		ParameterParser pp=data.getParameters();
        	String mName=pp.getString("modulename","");
        	String mode=pp.getString("mode","");
        	context.put("modulename",mName);
        	context.put("mode",mode);
        	context.put("Ques_Type",pp.getString("Ques_Type",""));
        	context.put("FromPath",pp.getString("FromPath"," "));
		context.put("tdcolor",pp.getString("count",""));
		String CName=(String)data.getUser().getTemp("course_name");
                context.put("course",CName);
		if((mode.equals("Edit"))||(mode.equals("View")))
		{
        		String userName =data.getUser().getName();
        		String quesid =pp.getString("qid","");
        		String filePath=data.getServletContext().getRealPath("/QuestionBank/"+userName+"/"+mName+"__QB.xml");
        		String question=ReadQuestionBank.getQuestion(filePath,quesid);
        		String ans=ReadQuestionBank.getAns(filePath,quesid);
        		String hint=ReadQuestionBank.getHint(filePath,quesid);
        	
			context.put("qid",quesid);
        		context.put("question",question);
        		context.put("ans",ans);
        		context.put("hint",hint);
		}

	}
}

