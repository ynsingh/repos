package org.iitk.brihaspati.modules.screens.call;

/*
 * @(#)Directory.java	
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
import org.apache.turbine.om.security.User;
import org.iitk.brihaspati.modules.utils.UserUtil;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.apache.turbine.util.parser.ParameterParser;
import org.iitk.brihaspati.modules.utils.AdminProperties;
import org.iitk.brihaspati.modules.utils.InstituteIdUtil;
import java.util.Vector;


/**
 *  @author <a href="mailto:vipulpal08@gamil.com">vipul kumar pal</a>
 */

public class Directory extends SecureScreen{
        public void doBuildTemplate( RunData data, Context context ){
                try{
			String path=data.getServletContext().getRealPath("/WEB-INF")+"/conf"+"/"+"Admin.properties";
                        int AdminConf = Integer.valueOf(AdminProperties.getValue(path,"brihaspati.admin.listconfiguration.value"));
                        context.put("AdminConf",AdminConf);
			String port = AdminProperties.getValue(path,"brihaspati.spring.port");
			context.put("port",port);
			int usedport = data.getServerPort();
			context.put("usedport",usedport);

			String  instituteId=(data.getUser().getTemp("Institute_id")).toString();
			ParameterParser pp=data.getParameters();
			String mode = pp.getString("mode");
			context.put("mode",mode);
                        User user=data.getUser();
                        System.gc();
			String role = (String)user.getTemp("role");
			context.put("role",role);
                        String username=user.getName();
                        int u_id=UserUtil.getUID(username);
                        context.put("uid",u_id);
			Vector instid1=InstituteIdUtil.getAllInstId(u_id);
			String str11="";
                        String instid="";
                        String str33="";
                        try{
                                for(int j=0;j<=instid1.size();j++){
                                        str11=instid1.elementAt(j).toString();
                                        if(!str11.equals(str33)){
                                        instid=instid+"/"+str11;
                                        }
                                        str33=str11;
                                }
                        }catch(Exception ex){};
                        context.put("instid",instid);
                }catch(Exception e){data.setMessage("Some Error Found to Create Directory !!"+e);}
        }
}

