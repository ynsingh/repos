/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.api.email;

import org.apache.commons.mail.HtmlEmail;
import org.smvdu.payroll.api.UserOrgReg.RandomCharGen;
import org.smvdu.payroll.api.UserOrgReg.UserOrgRegBeans;

/**
 *
 *  *  Copyright (c) 2010 - 2011 SMVDU, Katra.
*  All Rights Reserved.
**  Redistribution and use in source and binary forms, with or 
*  without modification, are permitted provided that the following 
*  conditions are met: 
**  Redistributions of source code must retain the above copyright 
*  notice, this  list of conditions and the following disclaimer. 
* 
*  Redistribution in binary form must reproduce the above copyright
*  notice, this list of conditions and the following disclaimer in 
*  the documentation and/or other materials provided with the 
*  distribution. 
* 
* 
*  THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED 
*  WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES 
*  OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE 
*  DISCLAIMED.  IN NO EVENT SHALL SMVDU OR ITS CONTRIBUTORS BE LIABLE 
*  FOR ANY DIRECT, INDIRECT, INCIDENTAL,SPECIAL, EXEMPLARY, OR 
*  CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT 
*  OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR 
*  BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
*  WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE 
*  OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, 
*  EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE. 
* 
* 
*  Contributors: Members of ERP Team @ SMVDU, Katra
*
 */
public class MailTest {


    public boolean sendMail(UserOrgRegBeans uor)  {
              try
              {
                  HtmlEmail email = new HtmlEmail();
                  email.setHostName("smtp.gmail.com");
                  email.setDebug(true);
                  email.setSSL(true);
                  email.addTo(uor.getEmail(),uor.getName());
                  email.setFrom("erpmission.smvdu@gmail.com", "workshop2011");
                  email.setSmtpPort(465);
                  email.setAuthentication("erpmission.smvdu@gmail.com", "workshop2011");
                  email.setSubject("Registration Code");
                  email.setHtmlMsg("<html>" 
                                    +"<font style='color:#4B4B4B;font-size:13px;'> Enter The Following Code For Confirming Your Registration Code</font><br><hr>"
                                    +"<font style='color:red;font-size:13px;font-weight:bold;'>"+new RandomCharGen().nextSessionId(uor)+"</font><br><hr>"
                          + "</html>");
                  email.setTextMsg("Thanks And Regards");
                  email.send();
                  return true;
              }
              catch(Exception e)
              {
                  e.printStackTrace();
                  return false;
              }
    }

}
