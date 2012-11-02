package org.iitk.brihaspatisync;

import java.util.Properties;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import org.apache.torque.util.Criteria;
import org.apache.turbine.services.security.torque.om.TurbineUserGroupRolePeer;
import org.apache.turbine.services.security.torque.om.TurbineUserPeer;
import org.apache.turbine.services.security.torque.om.TurbineUserGroupRole;

import org.apache.turbine.services.security.torque.om.TurbineUser;
import org.apache.turbine.services.security.torque.om.TurbineUserPeer;


import java.util.Vector;
import java.util.List;

import org.iitk.brihaspatisync.util.ServerLog;

public class AdminProperties {
	
 	private static Properties p = null;
	
	public static String getValue(String path,String key) throws Exception {
		if(p == null) {
			try {
 				p = new Properties();
	 			InputStream f = new FileInputStream(path);
		 		p.load(f);
			}catch(Exception e){
				p=null;
			}
		}
		String val = p.getProperty(key);
		return(val);
	}

	public static Vector getUDetail(int gid,int role_id) {
                Vector uid=new Vector();
                List v=null;
                int [] uId={1,0};
		try{
                        Criteria crit=new Criteria();
                        crit.add(TurbineUserGroupRolePeer.GROUP_ID,gid);
                       	crit.and(TurbineUserGroupRolePeer.ROLE_ID,role_id);
                        v=TurbineUserGroupRolePeer.doSelect(crit);
                        for(int i=0;i<v.size();i++)
                        {
                                TurbineUserGroupRole element=(TurbineUserGroupRole)v.get(i);
                                String s=Integer.toString(element.getUserId());
				List st=getUserDetail(s);
				for(int j=0;j<st.size();j++)
                                {
                                	TurbineUser element1=(TurbineUser)st.get(j);
                                	String uName=element1.getUserName();
                                	String fName=element1.getFirstName();
	                                String lName=element1.getLastName();
					if(fName.equals(""))
						fName=uName;	
                               		String eMail=element1.getEmail();
					uid.add(eMail+"-"+fName+" "+lName);
                                }
				
                        }
                } catch(Exception e){ org.iitk.brihaspatisync.util.ServerLog.getController().Log("This is the exception in get user UDetail method in AdminProperties class ---> "+e);	}
                return uid;
  	}


	private static List getUserDetail(String uid)
        {
                List v=null;
                try {
                        Criteria crit=new Criteria();
                        if(!uid.equals("All"))
                        {
                                int UID=Integer.parseInt(uid);
                                crit.add(TurbineUserPeer.USER_ID,UID);
                                v=TurbineUserPeer.doSelect(crit);
                        }
			/*
                        else
                        {
                                int noUid[]={0,1};
                                crit.addNotIn(TurbineUserPeer.USER_ID,noUid);
                                v=TurbineUserPeer.doSelect(crit);
                        }*/
                }
                catch(Exception e) { org.iitk.brihaspatisync.util.ServerLog.getController().Log(" This is the exception in get user UDetail method in AdminProperties class ---> "+e); }
                return v;
        }
                
}