/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.Voting;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 *
 * @author faraz
 */
public class VoterElectionActionForm extends org.apache.struts.action.ActionForm {
    
    private String election;

    public String getElection() {
        return election;
    }

    public void setElection(String election) {
        this.election = election;
    }

    

    /**
     * @return
     */
    
    /**
     *
     */
    public VoterElectionActionForm() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param request The HTTP Request we are processing.
     * @return
     */
    
}
