package org.iitk.brihaspati.modules.screens.call.CourseMgmt_User;

/*
 * @(#) Navigation.java
 *
 *  Copyright (c) 2011-12 ETRG,IIT Kanpur.
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
 *@author: <a href="mailto:seema_020504@yahoo.com">Seemapal</a>
 *@author: <a href="mailto:kshuklak@rediffmail.com">Kishore Kumar shukla</a>
 */
import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;
import org.apache.turbine.om.security.User;
import org.iitk.brihaspati.modules.utils.UserUtil;
import org.apache.turbine.util.parser.ParameterParser;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.iitk.brihaspati.modules.screens.call.SecureScreen;

/* this class contain the code for scorm navigation*/
 
public class Navigation extends SecureScreen
{
	private String URL=new String();
    public void doBuildTemplate( RunData data, Context context )
    {
	  try{
		URL = data.getRequest().getPathInfo().toString();

                ParameterParser pp=data.getParameters();
		pp.add("str","runscorm");
	}//try
	catch(Exception ex)
	{
		data.setMessage("The error in Navigation screen !!"+ex);
	}
          
    }
}

