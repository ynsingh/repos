package org.iitk.brihaspati.modules.screens.call;
/*
 * @(#)ParentChildList.java	
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
import java.util.List;
import org.apache.commons.lang.StringUtils;
import java.util.Vector;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.HashMap;
import java.util.Map;
import org.iitk.brihaspati.om.ParentInfo;
import org.iitk.brihaspati.om.ParentInfoPeer;
import org.apache.torque.util.Criteria;
import org.apache.turbine.om.security.User;
import org.apache.velocity.context.Context;
import org.apache.turbine.util.RunData;
import org.apache.turbine.modules.screens.VelocityScreen;
import org.apache.turbine.util.parser.ParameterParser;
import org.iitk.brihaspati.modules.utils.UserGroupRoleUtil;
import org.iitk.brihaspati.modules.utils.UserUtil;
import org.iitk.brihaspati.modules.utils.GroupUtil;
import org.iitk.brihaspati.modules.utils.CourseUtil;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
/**
 * @author <a href="richa.tandon1@gmail.com">Richa Tandon</a>
 */

public class ParentChildList extends VelocityScreen
{
	public void doBuildTemplate(RunData data, Context context)
	{
		try{
			User user=data.getUser();
                        String username=user.getName();
                        int uid=UserUtil.getUID(username);
			ParameterParser pp=data.getParameters();
			String lang=pp.getString("lang","english");
                        context.put("lang",lang);
			String semail = UserGroupRoleUtil.getChildId(Integer.toString(uid));
                        if (StringUtils.isNotBlank(semail)){
        	                String str[] = semail.split("#");
				ArrayList list = new ArrayList();
	                        Map map = new HashMap();
	                        
	                        for(int i =0; i < str.length ; i++){
					Vector studentmail = new Vector();
				      	String Stdnt_id = str[i];
					String loginName = UserUtil.getLoginName(Integer.parseInt(Stdnt_id));
					Vector GroupId=UserGroupRoleUtil.getGID(Integer.parseInt(Stdnt_id),3);
					map = new HashMap();
					map.put("StudntId",loginName);
		                        for(int j=0;j<GroupId.size();j++){
                		                String gid=GroupId.elementAt(j).toString();
                                		int g = Integer.parseInt(gid);
						String GrpName = GroupUtil.getGroupName(g);
						
		                                Vector instructorId=UserGroupRoleUtil.getUID(g,2);
		
                		                for(int k=0;k<instructorId.size();k++){
		                                        String id=instructorId.elementAt(k).toString();
                		                        int iid = Integer.parseInt(id);
                                		        String instructorName=UserUtil.getFullName(iid);
							String Cname = CourseUtil.getCourseName(GrpName);
	                                                String Crs_Instr = Cname + " / " +instructorName;
	                                                studentmail.add(Crs_Instr);
							map.put("Crs_InstName",studentmail);
                                	        }
						
                                	}
					list.add(map);
        	                        context.put("childDetail",list);
					}
                        }
			Criteria stu = new Criteria();
                        stu.add(ParentInfoPeer.PARENT_ID,Integer.toString(uid));
                        List stu_email = ParentInfoPeer.doSelect(stu);
                        ParentInfo ele=(ParentInfo)stu_email.get(0);
                        String stdntId=ele.getStudentId();
			String instructorName=null;
                        Vector GroupId=new Vector();
                        Vector instructorId=new Vector();
                        Vector name=new Vector();
		}
		catch(Exception ex)
		{
			data.setMessage("The error in ParentChildList !! "+ex);
		}
	}
}
