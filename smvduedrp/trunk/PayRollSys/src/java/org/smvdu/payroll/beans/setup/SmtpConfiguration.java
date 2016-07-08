/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.beans.setup;

import java.io.Serializable;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIData;
import javax.faces.context.FacesContext;
import org.smvdu.payroll.api.Administrator.CollegeList;

/**
 *
 *
 *  Copyright (c) 2010 - 2011.2014 SMVDU, Katra.
 *  Copyright (c) 2014, 2015, 2016 ETRG, IITK.
 *  All Rights Reserved.
 ** Redistribution and use in source and binary forms, with or 
 *  without modification, are permitted provided that the following 
 *  conditions are met: 
 ** Redistributions of source code must retain the above copyright 
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
 *
 * @author Om Prakash<omprakashkgp@gmail.com> IITK, (Dec 2015)
 *
 */


public class SmtpConfiguration implements Serializable{
    
    
    private int seqId;
    private int smtpPort;
    private String fromEmailId;
    private String fromPassword;
    private boolean smtpStatus;
    private String hostName;
    private String imgUrl;
    private String mailfrom;
    
    public SmtpConfiguration(){
    }

    public int getSeqId() {
        return seqId;
    }

    public void setSeqId(int seqId) {
        this.seqId = seqId;
    }

    public int getSmtpPort() {
        return smtpPort;
    }

    public void setSmtpPort(int smtpPort) {
        this.smtpPort = smtpPort;
    }

    public String getFromEmailId() {
        return fromEmailId;
    }

    public void setFromEmailId(String fromEmailId) {
        this.fromEmailId = fromEmailId;
    }

    public String getFromPassword() {
        return fromPassword;
    }

    public void setFromPassword(String fromPassword) {
        this.fromPassword = fromPassword;
    }

    public boolean isSmtpStatus() {
        return smtpStatus;
    }

    public void setSmtpStatus(boolean smtpStatus) {
        this.smtpStatus = smtpStatus;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getMailfrom() {
        return mailfrom;
    }

    public void setMailfrom(String mailfrom) {
        this.mailfrom = mailfrom;
    }
       
    private ArrayList<SmtpConfiguration> smtpDetails;
    private UIData dataGrid7;

    public UIData getDataGrid7() {
        return dataGrid7;
    }

    public void setDataGrid7(UIData dataGrid7) {
        this.dataGrid7 = dataGrid7;
    }
    
    public ArrayList<SmtpConfiguration> getSmtpDetails() {
        smtpDetails = new CollegeList().getSMTPDetails();
        dataGrid7.setValue(smtpDetails); 
        return smtpDetails;
    }

    public void setSmtpDetails(ArrayList<SmtpConfiguration> smtpDetails) {
        this.smtpDetails = smtpDetails;
    }

  
     public void saveSMTPDetails()
     {
         try
         {
            FacesContext fc = FacesContext.getCurrentInstance();
            FacesMessage message = new FacesMessage();
            Exception ex = new CollegeList().saveSMTPDetails(this);
            if(ex == null)
            {
                message.setSeverity(FacesMessage.SEVERITY_INFO);
                message.setSummary("SMTP Details Added Successfuly");
                fc.addMessage("", message);
            }
         }
         catch(Exception ex)
         {
             ex.printStackTrace();
         }
     }
     public void updateAdminSMTP()
     {
        try
        {
            FacesContext fc = FacesContext.getCurrentInstance();
            FacesMessage message = new FacesMessage();
            ArrayList<SmtpConfiguration> admin = (ArrayList<SmtpConfiguration>) dataGrid7.getValue();
            int active = 0;
            for(SmtpConfiguration ad : admin)
            {
                if(ad.isSmtpStatus() == true)
                {
                    active++;
                }
            }
            if(active > 1)
            {
                 message.setSeverity(FacesMessage.SEVERITY_ERROR);
                 message.setSummary("More Than One SMTP Server  Can't Be Activated...");
                 fc.addMessage("", message);
                 return;
            }

            Exception ex = new CollegeList().updateAdminSMTP(admin); 
            if(ex == null)
            {
                message.setSeverity(FacesMessage.SEVERITY_INFO);
                 message.setSummary("SMTP Server Updated...");
                 fc.addMessage("", message);
            }
            else
            {
                message.setSeverity(FacesMessage.SEVERITY_ERROR);
                 message.setSummary("SMTP Server Not Activated.....PleaseTry Again");
                 fc.addMessage(""+ex , message);
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

}
