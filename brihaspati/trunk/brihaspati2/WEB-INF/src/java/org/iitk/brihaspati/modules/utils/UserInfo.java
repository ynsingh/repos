package org.iitk.brihaspati.modules.utils;
/*
 * @(#)UserInfo.java  
 *
 *  Copyright (c) 2005,2009-2013 ETRG,IIT Kanpur. 
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
 */

import java.io.IOException;
import org.apache.turbine.Turbine;
import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;
import org.apache.turbine.om.security.User;
import org.apache.turbine.util.parser.ParameterParser;
import org.apache.torque.util.Criteria;
import org.apache.commons.lang.StringUtils;

import org.iitk.brihaspati.modules.utils.AdminProperties;
import org.iitk.brihaspati.modules.utils.StringUtil;
import org.apache.turbine.services.security.TurbineSecurity;
import org.iitk.brihaspati.om.TurbineUser;
import org.iitk.brihaspati.om.TurbineUserPeer;
import java.util.List;

/**
 * @author <a href="mailto:seemanti05@gmail.com">Seemanti Shukla</a>
 * @modified date : 07-10-2015 (Seemanti)
 */

//Create UserInfo class so as to retrieve userinfo data object.

public class UserInfo {

   private String AFName;
   private String ALName;
   private String eMail;
   StringBuffer sb = new StringBuffer();
   public UserInfo(String a, String b, String c) {
      AFName=a;
      ALName=b;
      eMail =c; 
   }
   //This method sets the UserInfo data in database as TurbineSecurity User if the user information changes else do not updates it.
   public boolean setUserInfo(RunData data,String path) {
      try {
         //Create a stringUtil object to check Invalid characters !!!
         StringUtil S = new StringUtil();
         //if (S.checkString(AFName)==correctvalue && S.checkString(ALName)==correctvalue)
         if (S.checkString(AFName)==-1 && S.checkString(ALName)==-1) {
            User user=data.getUser();
            String uname=user.getName();
            int uid=UserUtil.getUID(uname);
            Criteria crit = new Criteria();
            crit.add(TurbineUserPeer.USER_ID,uid);
            List list = TurbineUserPeer.doSelect(crit);
            //Get user FirstName,LastName & Email.
            String admin_fname =((TurbineUser)list.get(0)).getFirstName();
            String admin_lname =((TurbineUser)list.get(0)).getLastName();
            String admin_email =((TurbineUser)list.get(0)).getEmail();
            //Compare UserInfo Runtime Strings with already saved userinfo strings as in turbine user. 
            //If Admin's name is changed then true else false.
            if( (!(admin_fname.equals(AFName))) || (!(admin_lname.equals(ALName))) )
            {
               //set user FirstName & LastName.
               user.setFirstName(AFName);
               user.setLastName(ALName);
               TurbineSecurity.saveUser(user);
               sb.append("User Name updated successfully."+"\n");
            }
            else sb.append("No change in User Information."+"\n");
            //If Admin's E-mail changes then true else false.
            
            if( ( (admin_email == null) && (eMail != null && !eMail.equals("")) )  ||  ( (!(admin_email.equals(eMail))) && (eMail != null && !eMail.equals("")) ) )
            {
               //set user e-mail id.
               user.setEmail(eMail);
               TurbineSecurity.saveUser(user);
               AdminProperties.setPropertyValue(path,eMail,"brihaspati.mail.email");
               sb.append("E-mail Id updated successfully."+"\n");
            }
            data.addMessage(sb.toString());
         }
      }//End of try block.
      catch(Exception e) {
         ErrorDumpUtil.ErrorLog("The exception in setting UserInfo( FirstName,LastName & Email) in TurbineSecurity !"+e);
      }
      return true;
   }

}//End of class
