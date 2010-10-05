package org.iitk.brihaspati.modules.utils;

/*
 *  @(#) InstituteIdUtil.java
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
import java.io.*;
import java.lang.*;
import java.util.List;
import java.util.Vector;
import java.util.StringTokenizer;
import org.apache.torque.util.Criteria;
import org.apache.turbine.services.security.torque.om.TurbineUser;
import org.apache.turbine.services.security.torque.om.TurbineUserGroupRole;
import org.apache.turbine.services.security.torque.om.TurbineUserGroupRolePeer;
import org.iitk.brihaspati.om.InstituteAdminUserPeer;
import org.iitk.brihaspati.om.InstituteAdminUser;
import org.iitk.brihaspati.modules.utils.UserUtil;
/**
 * @author <a href="mailto:smita37uiet@gmail.com">Smita Pal</a>
 */
public class InstituteIdUtil
{
        /*
         * getting institute id of instructor and studend user by the help of userid 
         */
        public static String getInstId(int uid)
        {
                Criteria crit=new Criteria();
                String e=null;
                int[] uId2 ={0,1};
                try{
                crit.add(TurbineUserGroupRolePeer.USER_ID,uid);
                crit.andNotIn(TurbineUserGroupRolePeer.GROUP_ID,uId2);
                List v=TurbineUserGroupRolePeer.doSelect(crit);
                                for(int k=0;k<=v.size();k++){
                                TurbineUserGroupRole element=(TurbineUserGroupRole)v.get(k);
                                int s=(element.getGroupId());
                                String gname=GroupUtil.getGroupName(s);
                                StringTokenizer st=new  StringTokenizer(gname,"_");
                                for(int j=0;st.hasMoreTokens();j++){
                                                String instid=st.nextToken();
                                                e=st.nextToken();
                                        }
                                }

                }catch(Exception ex){}
                return e;
 }
        /*
         * getting the instituteid of institute admin by the help of userid
         */
        public static String getAdminInstId(int uid)
        {
                Criteria crit=new Criteria();
                String ef=null;
                try{
                        String username=UserUtil.getLoginName(uid);
                                crit.add(InstituteAdminUserPeer.ADMIN_EMAIL,username);
                                List v3=InstituteAdminUserPeer.doSelect(crit);

                                for(int b=0;b<v3.size();b++)
                                        {
                                                InstituteAdminUser el=(InstituteAdminUser)v3.get(b);
                                                int aid=(el.getInstituteId());
                                                ef = Integer.toString(aid);
                                        }
                        }catch(Exception ex){}
                        return ef;
 }
        /*
         * search method for given id  is a instituteAdmin or normal user
         */
        public static String getSearch(int uid)
        {
                String efg=null;
                String f=null;
                try{
                        efg=InstituteIdUtil.getInstId(uid);
                        if(efg.equals("admin")){
                        f=InstituteIdUtil.getAdminInstId(uid);
                        }
                        else{
                         f=InstituteIdUtil.getInstId(uid);
                        }
                }catch(Exception ex){}
                return f;
        }


}


