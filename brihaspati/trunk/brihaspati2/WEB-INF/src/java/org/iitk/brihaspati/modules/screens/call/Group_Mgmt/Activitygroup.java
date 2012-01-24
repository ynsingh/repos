package org.iitk.brihaspati.modules.screens.call.Group_Mgmt;

/*
 * @(#)Activitygroup.java
 *
 *  Copyright (c) 2006-07,2009 ETRG,IIT Kanpur.
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

/**This class contain the code for  Set activity
*@author: <a href="mailto:seema_020504@yahoo.com">Seemapal</a>
*@author: <a href="mailto:kshuklak@rediffmail.com">Kishore Kumar shukla</a>
*/

import java.util.Vector;
import java.util.List;
import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;
import org.apache.turbine.util.parser.ParameterParser;
import org.apache.turbine.om.security.User;

import org.apache.torque.util.Criteria;
import org.apache.turbine.services.security.torque.om.TurbineUserGroupRolePeer;
import org.apache.turbine.services.security.torque.om.TurbineUserPeer;
import org.apache.turbine.services.security.torque.om.TurbineUser;
import org.iitk.brihaspati.modules.utils.GroupUtil;
import org.iitk.brihaspati.modules.utils.UserGroupRoleUtil;
import org.iitk.brihaspati.modules.utils.MultilingualUtil;

import org.iitk.brihaspati.modules.screens.call.SecureScreen;
import org.iitk.brihaspati.modules.utils.TopicMetaDataXmlReader;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.iitk.brihaspati.modules.utils.FileEntry;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.iitk.brihaspati.modules.utils.UserUtil;
import org.iitk.brihaspati.modules.utils.CourseTimeUtil;
import org.iitk.brihaspati.modules.utils.ModuleTimeUtil;

import java.util.Vector;
import java.util.StringTokenizer;
import java.io.File;

public class Activitygroup extends SecureScreen
{
	/**
        * This class for using set and reset actvity for a particular group
     	* Place all the data object in the context
     	* for use in the template.
     	*/
	public void doBuildTemplate(RunData data, Context context)
	{
		try
		{
			String LangFile=data.getUser().getTemp("LangFile").toString();
                        ParameterParser pp=data.getParameters();
			
			/**
                        * Get courseid  and coursename for the user currently logged in
			*Put it in the context for Using in templates
                        * @see UserUtil in Util.
                        */
                	User user=data.getUser();
			context.put("coursename",(String)user.getTemp("course_name"));
			String courseid=(String)user.getTemp("course_id");
			context.put("courseid",courseid);
			context.put("tdcolor",pp.getString("count",""));
			int g_id=GroupUtil.getGID(courseid);
			String valact=pp.getString("actmode","");
			context.put("valact",valact);
			String pollval=pp.getString("pollno","");
			context.put("pollno",pollval);

		        String groupPath=data.getServletContext().getRealPath("/Courses"+"/"+courseid+"/GroupManagement");
			File f=new File(groupPath+"/GroupList__des.xml");

                        /**
                        *Reading the GroupList xml file
                        *put in the vector and context for the use in templtes
                        *@see TopicMetatDataXmlReader in utils
                        */
			TopicMetaDataXmlReader topicmetadata=null;
			Vector grplist=new Vector();
			if(f.exists())
			{
				topicmetadata=new TopicMetaDataXmlReader(groupPath+"/GroupList__des.xml");
				grplist=topicmetadata.getGroupDetails();
				if(grplist==null)
				return;	
				if(grplist.size()!=0)
				{
					context.put("grplist",grplist);
					context.put("Mode","NoBlank");
				}
				else
					context.put("Mode","Blank"); 
			}//if exists
			 /**
                        *Selecting the particular course student detail
                        *put in the context for the use in templates.
                        */
			boolean found=false;
			String studentname="",sname1="";
			Vector studentlist=new Vector();
                        Criteria crit =new Criteria();
                        crit.addJoin(TurbineUserPeer.USER_ID,TurbineUserGroupRolePeer.USER_ID);
                        crit.add(TurbineUserGroupRolePeer.ROLE_ID,3);
                        crit.and(TurbineUserGroupRolePeer.GROUP_ID,g_id);
                        crit.setDistinct();
                        List v=TurbineUserPeer.doSelect(crit);
                        for(int i=0;i<v.size();i++)
                        {
                                TurbineUser element=(TurbineUser)v.get(i);
                                studentname=element.getUserName();
				topicmetadata=new TopicMetaDataXmlReader(groupPath+"/GroupList__des.xml");
                                Vector grouplist=topicmetadata.getGroupDetails();
				for(int n=0;n<grouplist.size();n++)
				{
					String gname =((FileEntry) grouplist.elementAt(n)).getName();
                                        topicmetadata=new TopicMetaDataXmlReader(groupPath+"/"+gname+"__des.xml");
                                        Vector sellist=topicmetadata.getGroupDetails();
                                        if(sellist!=null)
                                        {
                                        	for(int k=0;k<sellist.size();k++)
                                                {
                                                	String sname =((FileEntry) sellist.elementAt(k)).getUserName();
							if(sname.equals(studentname))
								sname1=sname;
                                                }//for
                                        }//if
				}
				if(!studentname.equals(sname1))
                                studentlist.addElement(studentname);
                        }
                        if(studentlist.size()==0)
                        {
                                context.put("nolist","nolist");
                        }
                        else
                        {
                                context.put("studentlist",studentlist);
                                context.put("nolist","yeslist");
                        }
			/**
                         *Time calculaion for how long user use this page.
                         */
			 String Role = (String)user.getTemp("role");
			 int uid=UserUtil.getUID(user.getName());
                         if((Role.equals("student")) || (Role.equals("instructor")))
                         {
                                CourseTimeUtil.getCalculation(uid);
                                ModuleTimeUtil.getModuleCalculation(uid);
                         }

		}//try
                   catch(Exception e){
                                      ErrorDumpUtil.ErrorLog("Error in Screen:Activitygroup"+e);
                                      data.setMessage("See ExceptionLog !! " );
                                    }

	}
}
