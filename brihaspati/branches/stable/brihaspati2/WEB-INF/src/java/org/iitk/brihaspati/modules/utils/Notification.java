package org.iitk.brihaspati.modules.utils;
/*
 * @(#)Notification.java
 *
 *  Copyright (c) 2005-2006 ETRG,IIT Kanpur. 
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
import org.iitk.brihaspati.modules.utils.UserUtil;
import java.util.List;
import java.util.Iterator;
import org.apache.turbine.util.RunData;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.apache.velocity.context.Context;
import com.workingdogs.village.Record;
import org.iitk.brihaspati.om.DbReceivePeer;
import org.apache.turbine.om.security.User;

/**
 *  @author <a href="mailto:sisaudiya.dewan17@gmail.com">Dewanshu Singh Sisaudiya</a>
 */
public class Notification{
                        static String unreads1;

        public static String DisBoardNf(String user_name, String dir, String stats, String mode2) throws Exception{
                int user_id = UserUtil.getUID(user_name);
                        int unread1_flag=0;
                        int gid=0;
                        if(stats.equals("fromIndex")){
                                gid=4;
                        }else if(mode2.equals("instituteWise")){
                                gid=5;
                        }else
                                gid=GroupUtil.getGID(dir);

                        unreads1=new String();
                        // Count the UnRead messages according to userId
                        String unread_msg="SELECT COUNT(DB_RECEIVE.MSG_ID) UNREAD FROM DB_RECEIVE, DB_SEND WHERE DB_SEND.MSG_ID=DB_RECEIVE.MSG_ID AND DB_SEND.GROUP_ID="+gid+" AND RECEIVER_ID="+user_id+" AND READ_FLAG="+unread1_flag;
                        List u1=DbReceivePeer.executeQuery(unread_msg);
                        for(Iterator j=u1.iterator();j.hasNext();)
                        {
                                Record item1=(Record)j.next();
                                unreads1=item1.getValue("UNREAD").asString();
                        }
                 return(unreads1);
         }

}
     
