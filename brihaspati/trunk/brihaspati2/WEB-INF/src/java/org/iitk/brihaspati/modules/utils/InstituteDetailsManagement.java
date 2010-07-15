package org.iitk.brihaspati.modules.utils;

/*
 *  @(#) InstituteDetailsManagement.java
 *  Copyright (c) 2010 ETRG,IIT Kanpur 
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
 */

import java.util.List;
import java.util.Vector;
import org.apache.torque.util.Criteria;
import org.apache.turbine.services.security.torque.om.TurbineUser;
import org.apache.turbine.services.security.torque.om.TurbineUserGroupRole;
import org.apache.turbine.services.security.torque.om.TurbineUserGroupRolePeer;

/**
 * @author <a href="mailto:singh_jaivir@rediffmail.com">Jaivir Singh</a>
 * @author <a href="mailto:sharad23nov@yahoo.com">Sharad Singh</a>
 */

public class InstituteDetailsManagement 
{
	public static Vector getInstUserDeatil(String instituteId)
	{
		Vector vct=new Vector();
		Vector uidvct=new Vector();
		List details=null;
		try{
			Criteria crit=new Criteria();
			List userList=UserManagement.getUserDetail1("All",instituteId);
			for(int i=0;i<userList.size();i++)
			{
				TurbineUser element=(TurbineUser)(userList.get(i));
				int userid=element.getUserId();
				vct.add(userid);
			}
			for(int j=0;j<vct.size();j++){
				String str=(vct.get(j)).toString();
                        	crit=new Criteria();
                                crit.add(TurbineUserGroupRolePeer.USER_ID,str);
                                details=TurbineUserGroupRolePeer.doSelect(crit);
				for(int r=0;r<details.size();r++){
					TurbineUserGroupRole tugr=(TurbineUserGroupRole) (details.get(r));
					int roleid=tugr.getRoleId();
					String roleId=Integer.toString(roleid);
					if(roleId.equals("2"))
					{
						int userid = tugr.getUserId();
						uidvct.add(userid);			
						
					}
                                }

			}
		}
		catch(Exception ex){}
		return uidvct;	
	}
}
