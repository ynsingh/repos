/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.beans.setup;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.smvdu.payroll.api.email.Mail;
import org.smvdu.payroll.beans.db.ForgotPasswordDB;

/**
*
*  Copyright (c) 2010 - 2011.2014,2015 SMVDU, Katra.
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
*
* @author Om Prakash<omprakashkgp@gmail.com> IITK
*/
public class ForgotPassword implements Serializable{
    
    private int fpid;
    private String email;
    private String rkey;
    private String passdate;
    private String expdate; 
    private String password;
    private String repassword;
    private String message;
    private String newpasswd;

    
    public ForgotPassword(){}
    
    public int getFpid() {
        return fpid;
    }

    public void setFpid(int fpid) {
        this.fpid = fpid;
    }
        
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
        public String getRkey() {
         return rkey;
    }

    public void setRkey(String rkey) {
         this.rkey = rkey;
    }

    public String getPassdate() {
        return passdate;
    }

    public void setPassdate(String passdate) {
        this.passdate = passdate;
    }

    public String getExpdate() {
        return expdate;
    }

    public void setExpdate(String expdate) {
        this.expdate = expdate;
    }

    
    public String getNewpasswd() {
        return newpasswd;
    }

    public void setNewpasswd(String newpasswd) {
        this.newpasswd = newpasswd;
    }
    
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
     
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepassword() {
        return repassword;
    }

    public void setRepassword(String repassword) {
        this.repassword = repassword;
    }
        	
    
     /**
      * Forget Password  "When click on get new password, verify the user into data base if user exist into data base
      * then a password reset Email goes to user EmailID "
      */
     public void forgotPassword(){
     
      try
        {
            FacesContext fc = FacesContext.getCurrentInstance();
            FacesMessage message = new FacesMessage();
            if (this.getEmail().matches("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$") == false) {
                message.setSeverity(FacesMessage.SEVERITY_ERROR);
                message.setSummary(" Enter EmailID in correct Format ");
                fc.addMessage("", message);
                return;
            }
           // DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
           // Date dateT=new Date();
           // System.out.println("Current date=========>"+dateFormat.format(dateT));
            new ForgotPasswordDB().deleteExpiry();
            ForgotPasswordDB fpdb = new ForgotPasswordDB();
            
            if(fpdb.CheckUserExist(this.getEmail())==1)
            { 
                new ForgotPasswordDB().sendPassResetMail(this);
                FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "Password ResetMail has been  sent successfuly to :"+this.getEmail(),""));
            }
            else
            {
                message.setSeverity(FacesMessage.SEVERITY_INFO);
                message.setSummary("Sorry user is not exist in database ...");
                fc.addMessage(""+fpdb.CheckUserExist(this.getEmail()), message);  
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
     }
     
     /**
      * Reset The Password "click on activation link and set the password,Then the Email of new password goes to user Emailid"
      * Verify the activation link used or not. 
      */
     
    public void reSetPassword()
    {
     try
       {
            FacesContext fc = FacesContext.getCurrentInstance();
            String rkey1 = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("rkey");
            FacesMessage message = new FacesMessage();
            if(this.getPassword().equals(this.getRepassword()) == false)
            {
                message.setSeverity(FacesMessage.SEVERITY_ERROR);
                message.setSummary("Password Are Not Matching.....Please Try Again.");
                fc.addMessage("", message);
                return;
            }
            
            ForgotPasswordDB fpdb = new ForgotPasswordDB();
            String useremail=fpdb.getUserEmail(rkey1);
            
            if(useremail==null)
            {
                message.setSeverity(FacesMessage.SEVERITY_ERROR);
                message.setSummary("This activation link has been expired. OR Already used! ");
                fc.addMessage("", message);
                return;
            }
            
            String servername = new Mail().getserverUrl();
            newpasswd = this.password;
            if(fpdb.changePassword(useremail, newpasswd))
            {
               new ForgotPasswordDB().sendUpdatePasswordMail(this,useremail);
                //new Mail().sendMailMessage("Your PayrollSys Password has been changed ", useremail, newpasswd);
                callLoginPage();
                FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "New Password has been  sent successfuly ", ""));
                new ForgotPasswordDB().deleteEntry(rkey1);
            }
            else
            {
                message.setSeverity(FacesMessage.SEVERITY_INFO);
                message.setSummary("Password are not updated Please contact to Administrator.......");
                fc.addMessage("", message); 
            }
                        
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
   
    public void callLoginPage(){
           try
           {
                FacesContext.getCurrentInstance().getExternalContext().redirect("../Login.jsf");
                           
           }
           catch(Exception e)
           {
            e.printStackTrace();
           }       
    }
 
}
