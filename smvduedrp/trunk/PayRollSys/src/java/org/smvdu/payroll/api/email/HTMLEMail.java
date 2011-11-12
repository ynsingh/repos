/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.smvdu.payroll.api.email;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.apache.commons.mail.HtmlEmail;

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
public class HTMLEMail {



    public String getMailContent(String to)
    {
        String s = "<HTML>";
        s+="<table border='1'>";
        s+="<tr><th>Subject</th><th>Date Of Creation</th><th>Priority</th><th>Creator</th>"
                + "<th>Due Date/Time</th><th>Assigned To</th><th>Project</th></tr>";

        s+="</table></html>";
        System.err.println(s);
        return s;
    }
    
    public void send(String to,String subject)
    {
          try
          {
              HtmlEmail email = new HtmlEmail();
              email.setHostName("smtp.gmail.com");
              email.setDebug(true);
              email.setSSL(true);
              email.addTo("to email", "Name");
              email.addTo(to);
              email.setFrom("", "From");
              email.setSmtpPort(465);
              email.setAuthentication("email id", "password");
              email.setSubject(subject);
              //email.setHtmlMsg(getMailContent(tasks, to));
              email.setTextMsg("Your email client does not support HTML messages");
              email.send();
              FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "E-Mail Sent", ""));
          }
          catch(Exception e)
          {
              e.printStackTrace();
              FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mail not sent,"+e.getMessage(), ""));
          }
    }
    
}
