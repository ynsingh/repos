package org.iitk.brihaspati.modules.screens.call.FAQ;

/*
 * @(#)FAQ_Ques.java
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

/**
 *@author: <a href="mailto:seema_020504@yahoo.com">Seemapal</a>
 *@author: <a href="mailto:kshuklak@rediffmail.com">Kishore Kumar shukla</a>
 */

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;
import org.apache.turbine.om.security.User;
import org.apache.torque.util.Criteria;
import org.apache.turbine.services.servlet.TurbineServlet;
import org.iitk.brihaspati.modules.utils.UserUtil;
import org.iitk.brihaspati.modules.utils.GroupUtil;
import org.iitk.brihaspati.modules.utils.CommonUtility;
import org.iitk.brihaspati.modules.utils.AdminProperties;
import org.apache.turbine.util.parser.ParameterParser;
import org.iitk.brihaspati.modules.screens.call.SecureScreen;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;

import org.iitk.brihaspati.om.FaqPeer;
import org.iitk.brihaspati.om.Faq;

import java.util.Vector;
import java.util.List;

/*This class contain the code for FAQ category*/
public class FAQ_Ques extends SecureScreen
{
	/**
     	* Place all the data object in the context
     	* for use in the template.
     	*/
	public void doBuildTemplate(RunData data, Context context)
	{
		try
		{
			/**
                        *Retrieve the parameters by using the ParameterParser
                        *Putting the parameters context for use in templates.
                        */
			
                        ParameterParser pp=data.getParameters();
                	User user=data.getUser();
			String counter=data.getParameters().getString("count","");
                        context.put("tdcolor",counter);
			String username=data.getUser().getName();
			context.put("username",username);
			String catname=pp.getString("catname","");
			context.put("catname",catname);
			String category=pp.getString("category","");
			context.put("category",category);
			String mode=pp.getString("mode","");
			context.put("mode",mode);
			String deltype=pp.getString("deltype","");
			context.put("deltype",deltype);
			String type=pp.getString("type","");
			context.put("type",type);
			String status=pp.getString("status","");
			String categoryval=pp.getString("categoryval","");
			context.put("categoryval",categoryval);
			String actionName=pp.getString("actionName","");
			String  inst_id=(String)user.getTemp("Institute_id");
			int uid=UserUtil.getUID(username);
			int roleid=0;
			if(username.equals("admin"))
                        roleid=1;
                        else
                        roleid=7;
                        String gname=GroupUtil.getGroupName(uid,roleid);
                        int gid=GroupUtil.getGID(gname);
			/**
			*Getting the Faq category list from the Database
			*using CommonUtility (PListing method) for pagination
                        *put in the context for the use in templates
			*/
			String path=TurbineServlet.getRealPath("/WEB-INF")+"/conf"+"/"+"Admin.properties";
                        String conf =AdminProperties.getValue(path,"brihaspati.admin.listconfiguration.value");
                        int list_conf=Integer.parseInt(conf);
                        context.put("userConf",new Integer(list_conf));
                        context.put("userConf_string",conf);
			Vector entry=new Vector();
			List u=null;
                        Criteria crit=new Criteria();
                        crit.add(FaqPeer.USER_ID,uid);
                        u=FaqPeer.doSelect(crit);
			if(u.size()!=0)
			{
                        	for(int m=0;m<u.size();m++)
                        	{
                        		Faq element=(Faq)(u.get(m));
                                	String cat_Name=element.getCategory();
                                	String groupid=Integer.toString(element.getGroupId());
                                	int quesid=element.getQuesId();
					if((gid== 3) && (groupid.endsWith(inst_id)) && quesid==0)
					{
						if(!entry.contains(cat_Name))
                                		entry.addElement(cat_Name);
					}
					if(gid ==1)
					{
						if(!entry.contains(cat_Name))
                                		entry.addElement(cat_Name);
					}
                        	} //for
			}
                        if(entry.size()!=0)
                        {
				status="Noblank";
				if((mode.equals("delete"))||(mode.equals("add")))
				context.put("entry",entry);
				else{
				Vector vct=CommonUtility.PListing(data ,context ,entry,list_conf);
				context.put("entry",vct);	
				}
                        }//ifentry
                        else
				status="blank";
			context.put("status",status);
			String mode1=pp.getString("mode1","");
			context.put("mode1",mode1);
		}//try
		catch(Exception e){ data.setMessage("Error in Screen [FAQ_Ques]"+e); }
	}
}
