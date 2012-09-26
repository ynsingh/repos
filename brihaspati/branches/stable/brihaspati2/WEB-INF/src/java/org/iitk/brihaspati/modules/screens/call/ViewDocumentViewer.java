package org.iitk.brihaspati.modules.screens.call;
/*
 * @(#)ViewDocumetViewer.java   
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

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;
import org.apache.turbine.util.parser.ParameterParser;
import org.iitk.brihaspati.modules.utils.AdminProperties;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;

/**
 *  @author <a href="mailto:richa.tandon1@gamil.com">Richa Tandon</a>
 **/

public class ViewDocumentViewer extends SecureScreen{
        public void doBuildTemplate( RunData data, Context context ){
                try{
			ParameterParser pp=data.getParameters();
			String path=data.getServletContext().getRealPath("/WEB-INF")+"/conf"+"/"+"Admin.properties";
			String port = AdminProperties.getValue(path,"brihaspati.spring.port");
                        context.put("port",port);
			pp.add("str","document");
			String topic=pp.getString("topic","");
                        String filename=pp.getString("filename","");
			String filePath = data.getServletContext().getRealPath("/Courses")+"/";
			String dir=(String)data.getUser().getTemp("course_id")+"/Content/";
			context.put("path",filePath);
			context.put("courseid",dir);
			context.put("topic",topic);
			context.put("filename",filename);
		}
		catch(Exception e)
		{
			ErrorDumpUtil.ErrorLog("Exception in ViewDocumentViewer------"+e);
		}
	}
}
