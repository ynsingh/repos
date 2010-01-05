package org.iitk.brihaspati.modules.screens.call.Quota_Mgmt;

/*
 * @(#)Quota.java	
 *
 *  Copyright (c)2008- 2009 ETRG,IIT Kanpur. 
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
import java.util.Vector;
import java.math.BigDecimal;
import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;
import org.apache.torque.util.Criteria;
import org.iitk.brihaspati.om.TurbineUserPeer;
import org.iitk.brihaspati.om.CoursesPeer;
import org.iitk.brihaspati.modules.utils.CommonUtility;
import org.iitk.brihaspati.modules.utils.ListManagement;
import org.iitk.brihaspati.modules.utils.StringUtil;
import org.iitk.brihaspati.modules.utils.AdminProperties;
import org.iitk.brihaspati.modules.screens.call.SecureScreen_Admin;
import org.apache.turbine.util.parser.ParameterParser;
/**
 * @author <a href="mailto:singh_jaivir@rediffmail.com">Jaivir Singh</a>
 */

public class Quota extends SecureScreen_Admin
{
    /**
     * Place all the data object in the context
     * for use in the template.
     */
	public void doBuildTemplate( RunData data, Context context )
	{
		try
		{
			ParameterParser pp=data.getParameters();
			String mode=data.getParameters().getString("mode"," ");
			String counter=data.getParameters().getString("count","");
			String status=pp.getString("status","");
			context.put("tdcolor",counter);
			String query="",valueString="";
			Vector userList=new Vector();
			Criteria crit=new Criteria();
			List lst;
			Vector vct=new Vector();
			int uid[]={0,1};
			if(mode.equals("uquota"))
			{
				if((status.equals("nosearch")))
				{
					crit.addNotIn(TurbineUserPeer.USER_ID,uid);
					crit.addGroupByColumn(TurbineUserPeer.USER_ID);
					lst=TurbineUserPeer.doSelect(crit);
					vct=new Vector(lst);
					context.put("status","nosearch");
                        	}
				else
                        	{
					crit=new Criteria();
                                	String str="";
                                	query=pp.getString("queryList");
                                	if(query.equals(""))
                                        query=pp.getString("query");
                                	if(query.equals("First Name"))
                                        	str="FIRST_NAME";
                                	else if(query.equals("Last Name"))
                                        	str="LAST_NAME";
                                	else if(query.equals("User Name"))
                                        	str="LOGIN_NAME";
                                	else if(query.equals("Email"))
                                        	str="EMAIL";
                           		valueString =StringUtil.replaceXmlSpecialCharacters(pp.getString("valueString"));
                                	String table="TURBINE_USER";
                                	crit.addNotIn(TurbineUserPeer.USER_ID,uid);
                                	crit.add(table,str,(Object)("%"+valueString+"%"),crit.LIKE);
                                	lst=TurbineUserPeer.doSelect(crit);
					vct=new Vector(lst);
                                	context.put("query",query);
                                	context.put("valueString",valueString);
                                	context.put("status","Search");
                        	}
				CommonUtility.PListing(data ,context ,vct);
			}
			List clst;
			Vector cvct=new Vector();
			if(mode.equals("cquota"))
			{
				Vector courseList=new Vector();
				if((status.equals("nosearch")))
				{
					crit=new Criteria();
					crit.addGroupByColumn(CoursesPeer.GROUP_NAME);
					clst=CoursesPeer.doSelect(crit);
					cvct=new Vector(clst);
					context.put("status","nosearch");
				}
				else
				{
                                	query=data.getParameters().getString("queryList");
                                	if(query.equals(""))
                                        query=data.getParameters().getString("query");
                           		valueString =StringUtil.replaceXmlSpecialCharacters(data.getParameters().getString("valueString"));
                                	context.put("query",query);
                                	context.put("valueString",valueString);
                                	String str="GROUP_NAME";
                                	if(query.equals("CourseId"))
                                        	str="GROUP_NAME";
                                	else if(query.equals("Course Name"))
                                        	str="CNAME";
                                	else if(query.equals("Dept"))
                                        	str="DEPT";
                                	String table="COURSES";
                                	crit=new Criteria();
                                	crit.add(table,str,(Object)("%"+valueString+"%"),crit.LIKE);
                                	clst=CoursesPeer.doSelect(crit);
					cvct=new Vector(clst);
                                	context.put("status","Search");

				}
			CommonUtility.PListing(data ,context ,cvct);
			}
			context.put("mode",mode);
			if(mode.equals("edit")){
				String uidedit=data.getParameters().getString("uid");
				String Name=data.getParameters().getString("name");
				String quota=data.getParameters().getString("quota");
				context.put("uid",uidedit);
				context.put("name",Name);
				context.put("quota",quota);
			}
			if(mode.equals("cedit")){
                                String cName=data.getParameters().getString("cname");
                                String cQuota=data.getParameters().getString("cquota");
                                String gname=data.getParameters().getString("grName");
                                context.put("gname",gname);
                                context.put("cname",cName);
                                context.put("cquota",cQuota);
			}
		}
		catch(Exception ex)
		{data.setMessage("The error in getting list:"+ex);}		
	}
}


