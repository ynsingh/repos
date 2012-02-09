/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.Voting;

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

    

  
    public VoterElectionActionForm() {
        super();
        // TODO Auto-generated constructor stub
    }

    
}
