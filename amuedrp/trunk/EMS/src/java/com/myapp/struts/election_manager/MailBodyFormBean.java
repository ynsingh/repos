/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.election_manager;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 *
 * @author guest
 */
public class MailBodyFormBean extends org.apache.struts.action.ActionForm {
    
    private String voterid;

    private String candidateid;
     private String onetime;
      private String resetpassonetime;
       private String resetpassonetimewithlink;
        private String changepass;
         private String resetlogin;
         private String electionmail;

    public String getChangepass() {
        return changepass;
    }

    public void setChangepass(String changepass) {
        this.changepass = changepass;
    }

    public String getOnetime() {
        return onetime;
    }

    public void setOnetime(String onetime) {
        this.onetime = onetime;
    }

    public String getResetlogin() {
        return resetlogin;
    }

    public void setResetlogin(String resetlogin) {
        this.resetlogin = resetlogin;
    }

    public String getResetpassonetime() {
        return resetpassonetime;
    }

    public void setResetpassonetime(String resetpassonetime) {
        this.resetpassonetime = resetpassonetime;
    }

    public String getResetpassonetimewithlink() {
        return resetpassonetimewithlink;
    }

    public void setResetpassonetimewithlink(String resetpassonetimewithlink) {
        this.resetpassonetimewithlink = resetpassonetimewithlink;
    }

    
 

    public String getElectionmail() {
        return electionmail;
    }

    public void setElectionmail(String electionmail) {
        this.electionmail = electionmail;
    }
   

    public String getCandidateid() {
        return candidateid;
    }

    public void setCandidateid(String candidateid) {
        this.candidateid = candidateid;
    }

    public String getVoterid() {
        return voterid;
    }

    public void setVoterid(String voterid) {
        this.voterid = voterid;
    }

   
    
    /**
     *
     */
    public MailBodyFormBean() {
        super();
        // TODO Auto-generated constructor stub
    }

   
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        
        return null;
    }
}
