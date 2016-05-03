/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.beans.setup;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.smvdu.payroll.user.UserRegistration;

/**
 **  Copyright (c) 2010, 2011, 2014 SMVDU Katra.
 *  Copyright (c) 2016 ETRG, IITK.
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
 * @author Om Prakash <omprakashkgp@gmail.com> iitk, 19 April 2016
 */

public class UserVerification {
    
    private String emailid;
    private String msg;
    private String imageUrl;
    private String eventPage;
    private String userEvent;

    public String getUserEvent() {
        return userEvent;
    }

    public void setUserEvent(String userEvent) {
        this.userEvent = userEvent;
    }
    

    public String getEmailid() {
        return emailid;
    }

    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getEventPage() {
        return eventPage;
    }

    public void setEventPage(String eventPage) {
        this.eventPage = eventPage;
    }
    
     public UserVerification() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
        String rkey1 = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("vcode");
        String email1 = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("email");
        //System.out.print("VCode=======>"+rkey1+"Email=======>"+email1);
        // requestUrl = request.getRequestURL().toString();
               
        UserRegistration userrg = new UserRegistration();
        boolean flag =  userrg.updateUserVerification(email1,rkey1);
        if(flag == true)
        {
           imageUrl = "success.png";
           msg = "Your Registration Is Successfuly Registered";
        }
        else
        {
           imageUrl = "err.png";
           msg = "Try Again For User Registration ";
           
        }

   }
}