
package org.iitk.brihaspati.modules.utils;

/*@(#)EncrptDecrpt.java
 *  Copyright (c) 2012 ETRG,IIT Kanpur. http://www.iitk.ac.in/
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
 */

/**
 * @author <a href="mailto:nksinghiitk@gmail.com">Nagendra Kumar Singh</a>
 */

import java.util.Vector;
import java.util.List;
import org.apache.torque.util.Criteria;
import org.iitk.brihaspati.om.TurbineUserGroupRolePeer;
import org.iitk.brihaspati.om.TurbineUserGroupRole;
import org.iitk.brihaspati.om.TurbineGroupPeer;
import org.iitk.brihaspati.om.TurbineGroup;
import org.iitk.brihaspati.om.CoursesPeer;
import org.iitk.brihaspati.om.Courses;
import org.iitk.brihaspati.modules.utils.AdminProperties;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.apache.turbine.services.servlet.TurbineServlet;


public class RemoteData{
	/**
 	* This method retrun the course list with courseId, Course Name and role in that course
 	*/

	public static Vector getUserCrsList(String email,String InstituteId)
        {
		Vector roleid=new Vector();
		List v=null;
		List v1=null;
		List v2=null;
		Criteria crit=new Criteria();
		try{
		int uid=UserUtil.getUID(email);
		crit.add(TurbineUserGroupRolePeer.USER_ID,uid);
                crit.setDistinct();
                v=TurbineUserGroupRolePeer.doSelect(crit);
		}
                catch(Exception e){
                        ErrorDumpUtil.ErrorLog("The error in try1 getUserCrsList()- RemoteData !!"+e);
                }
                try{
                        for(int i=0;i<v.size();i++){
                                TurbineUserGroupRole element=(TurbineUserGroupRole)v.get(i);
				//Integer.parseInt((rid.elementAt( i )).toString());
                                int s=element.getRoleId();
                                int gp=element.getGroupId();
				crit=new Criteria();
				crit.add(TurbineGroupPeer.GROUP_ID,gp);
				v1=TurbineGroupPeer.doSelect(crit);
				for(int j=0;j<v1.size();j++){
					TurbineGroup tg=(TurbineGroup)v1.get(j);
					String gnm=tg.getGroupName();
					if(gnm.endsWith(InstituteId)){
						crit=new Criteria();
						crit.add(CoursesPeer.GROUP_NAME,gnm);
						v2=CoursesPeer.doSelect(crit);
						for(int k=0;k<v2.size();k++){
							Courses crs=(Courses)v2.get(k);
							String cnm=crs.getCname();
							String rnme=UserGroupRoleUtil.getRoleName(s);
							String fnl=gnm+"-"+cnm+"-"+rnme;
							roleid.add(fnl);
						}
					}
				}
			}
		}
		catch(Exception e){
                        ErrorDumpUtil.ErrorLog("The error in try1 getUserCrsList()- RemoteData !!"+e);
                }
	return roleid;
	}
}
