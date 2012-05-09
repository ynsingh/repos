package org.iitk.brihaspati.modules.screens.call;         
/*
 * @(#)ViewANameList.java	
 *
 *  Copyright (c) 2011 ETRG,IIT Kanpur. 
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
import java.util.List;
import org.apache.torque.util.Criteria;
import org.apache.turbine.util.RunData;
import javax.servlet.ServletOutputStream;
import org.apache.velocity.context.Context;
import org.iitk.brihaspati.om.InstituteAdminUser;
import org.iitk.brihaspati.modules.utils.UserUtil;
import org.apache.turbine.util.parser.ParameterParser;  
import org.iitk.brihaspati.om.InstituteAdminUserPeer;
import org.apache.turbine.modules.screens.VelocityScreen;

/**
 * This class, Displays the admin name list of Institute used for popup window.
 * @author <a href="mailto:singh_jaivir@rediffmail.com">Jaivir Singh</a>
 * @author <a href="mailto:palseema@rediffmail.com">Seema Pal</a>
 */

public class ViewANameList extends VelocityScreen
{
     	public void doBuildTemplate( RunData data, Context context) 
     	{
		try{
			/**
 			*Get the parameters using ParameterParser. 
                        */
			ParameterParser pp=data.getParameters();
			String instituteid=pp.getString("instituteid");
			/**
 			*Get the list of admin,s name from 'INSTITUTE ADMIN USER' table.
 			*/ 
			Criteria crit=new Criteria();
			crit.add(InstituteAdminUserPeer.INSTITUTE_ID,instituteid);
			List list=InstituteAdminUserPeer.doSelect(crit);
			for(int i=0;i<list.size();i++){
				InstituteAdminUser iauser=(InstituteAdminUser)list.get(i);
				int Auid=UserUtil.getUID(iauser.getAdminUname());
                        	String str=UserUtil.getFullName(Auid)+"<br>";
                		data.getResponse().setHeader("Content-Type","text/html");
                		ServletOutputStream out=data.getResponse().getOutputStream();
                		out.println(str);
			}		
		}//try
		catch(Exception e)
		{	
			  data.setMessage("Error in Raw Page for display institute admin list name !!"+ e);

		}
    }//dobuild
}


