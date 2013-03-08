package org.iitk.brihaspati.modules.screens;
/*
 * @(#)Shutdown.java	
 *
 *  Copyright (c) 2013 ETRG,IIT Kanpur. 
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
 *  Contributors: Members of ETRG, I.I.T. Kanpur 
 * 
 */
import java.io.File;
import org.apache.velocity.context.Context;
import org.apache.turbine.util.RunData;
import org.apache.turbine.modules.screens.VelocityScreen;
import org.apache.turbine.util.parser.ParameterParser;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.iitk.brihaspati.modules.utils.CommonUtility;
import org.apache.turbine.services.servlet.TurbineServlet;
/**
 * @author <a href="tejdgurung20@gmail.com">Tej Bahadur</a>
 */

public class Shutdown extends VelocityScreen
{
     /**
     * Place all the data object in the context
     * for use in the template.
     */

	public void doBuildTemplate(RunData data, Context context)
	{
		try{
			 /**
                          *This block of code read shutdown notice from property file and set value for template
			  * to show in template.
                          */
			ParameterParser pp=data.getParameters();
			pp.add("str","session");
			String lang=pp.getString("lang","english");
                        context.put("lang",lang);
			String ShutdNoticePath = TurbineServlet.getRealPath("/WEB-INF/conf/Shutdown.properties");
			File sdpath=new File(ShutdNoticePath);
			if(sdpath.exists()){
                        String Shutdownnotice=CommonUtility.removeShutDownNotice(ShutdNoticePath);
                        context.put("sdnotice",Shutdownnotice);
			}
		}
		catch(Exception ex)
		{
			data.setMessage("The error in Shutdown notices !! "+ex);
		}

	}
}
