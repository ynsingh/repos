package org.iitk.brihaspati.modules.utils;

/*
 * @(#) UsageDetailsUtil.java 
 * Copyright (c) 2010 ETRG,IIT Kanpur
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
 */
import java.util.List;
import java.util.Vector;
import java.util.Collections;
import org.apache.torque.util.Criteria;
import org.apache.turbine.om.security.User;
import org.apache.turbine.services.security.TurbineSecurity;
import org.apache.turbine.services.security.torque.om.TurbineUser;
import org.apache.turbine.services.security.torque.om.TurbineUserPeer;
import org.iitk.brihaspati.om.UsageDetails;
import org.iitk.brihaspati.om.UsageDetailsPeer;

/**
 * This utils class have all details of User
 * @author <a href="mailto:smita37uiet@gmail.com">smita</a>
 */


public class UsageDetailsUtil
{
        /**
         * @param USERID intger
         * @return integer
         */
        public static int getentryId(int uid)
        {
                int entryid=0;
                try{
                	Vector vec=new Vector();
                	Criteria crit=new Criteria();
                	crit.add(UsageDetailsPeer.USER_ID,uid);
                	List v=UsageDetailsPeer.doSelect(crit);
                	for(int i=0;i<v.size();i++){
                		UsageDetails element=(UsageDetails)v.get(i);
                		int etryid=(element.getEntryId());
                		vec.add(etryid);
                	}
                	Object obm = Collections.max(vec);
                	String eid = obm.toString();
                	entryid=Integer.parseInt(eid);

                }catch(Exception e){ ErrorDumpUtil.ErrorLog("Exception in getting entryid of currently login user-----------");}
                return entryid;
        }



}

