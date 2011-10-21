package org.iitk.brihaspati.modules.screens.call.Quota_Mgmt;

/*
 * @(#)Quota.java	
 *
 *  Copyright (c)2008- 2009,2011 ETRG,IIT Kanpur. 
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
import org.iitk.brihaspati.modules.screens.call.SecureScreen_Institute_Admin;
import org.apache.turbine.util.parser.ParameterParser;
import org.apache.turbine.services.servlet.TurbineServlet;
import org.iitk.brihaspati.modules.utils.AdminProperties;
import org.iitk.brihaspati.om.InstituteQuota;
import org.iitk.brihaspati.om.Courses;
import org.iitk.brihaspati.om.InstituteQuotaPeer;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.iitk.brihaspati.modules.utils.QuotaUtil;

/**
 * @author <a href="mailto:singh_jaivir@rediffmail.com">Jaivir Singh</a>
 */

public class Quota extends SecureScreen_Institute_Admin
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
			String mode=data.getParameters().getString("mode","");
			String counter=data.getParameters().getString("count","");
			String status=pp.getString("status","");
			context.put("tdcolor",counter);
			String query="",valueString="";
			Vector userList=new Vector();
			List lst;
			Vector vct=new Vector();
			int uid[]={0,1};
			String instituteId=(data.getUser().getTemp("Institute_id")).toString();
			long instquota=QuotaUtil.getInstituteQuota(instituteId);
			context.put("allotedquota",instquota);	
			List clst= null;
			List clstsmode=null;
			Vector cvct=new Vector();
			Vector courseList=new Vector();
			Criteria crit=new Criteria();
			crit=new Criteria();
			crit.addGroupByColumn(CoursesPeer.GROUP_NAME);
			clst=CoursesPeer.doSelect(crit);
			context.put("status","nosearch");
			Vector v=new Vector();
			Vector v1=new Vector();
			Vector v2=new Vector();
			for(int x=0;x<clst.size();x++)
			{
				Courses crs=(Courses)clst.get(x);
				String gnm=crs.getGroupName();
				String cnm=crs.getCname();
				BigDecimal quota=crs.getQuota();
				long qt=quota.longValue();
				if(gnm.endsWith(instituteId)){
					cvct.add(gnm);
					v1.add(cnm);
					v2.add(qt);
				}
			}
			long qtingb=QuotaUtil.getInstituteUsedQuota(instituteId);
			long remquota =(instquota - qtingb);
			context.put("rquota",remquota);
			String path=TurbineServlet.getRealPath("/WEB-INF")+"/conf"+"/InstituteProfileDir/"+instituteId+"Admin.properties";
                        String conf =AdminProperties.getValue(path,"brihaspati.admin.listconfiguration.value");
                        int list_conf=Integer.parseInt(conf);
                        context.put("userConf",new Integer(list_conf));
                        context.put("userConf_string",conf);
			if(!mode.equals("cedit"))
			{
				if((status.equals("Search")))
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
                                	clstsmode=CoursesPeer.doSelect(crit);
					cvct=new Vector(clstsmode);
					context.put("status","Search");
					context.put("detail",clstsmode);
				}
                                Vector vctr= CommonUtility.PListing(data ,context ,cvct,list_conf);
                                Vector vctr1= CommonUtility.PListing(data ,context ,v1,list_conf);
                                Vector vctr2= CommonUtility.PListing(data ,context ,v2,list_conf);
                                context.put("entry",vctr);
                                context.put("entry1",vctr1);
                                context.put("entry2",vctr2);
			}
			if(mode.equals("cedit")){
                                String cName=data.getParameters().getString("cname");
                                String cQuota=data.getParameters().getString("cquota");
                                String gname=data.getParameters().getString("grName");
                                context.put("gname",gname);
                                context.put("cname",cName);
                                context.put("cquota",cQuota);
			}
			context.put("mode",mode);
		}
		catch(Exception ex)
		{data.setMessage("The error in getting list:"+ex);}		
	}
}


