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
 * @author Edrp-04
 */
public class BallotActionForm extends org.apache.struts.action.ActionForm {
    
    private int position_id;
    private String position;
    private String candidate;
    private String numberofchoice;
    private String election;

  

    public BallotActionForm() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @return the position_id
     */
    public int getPosition_id() {
        return position_id;
    }

    public String getElection() {
        return election;
    }

    public void setElection(String election) {
        this.election = election;
    }

    /**
     * @param position_id the position_id to set
     */
    public void setPosition_id(int position_id) {
        this.position_id = position_id;
    }

    /**
     * @return the position
     */
    public String getPosition() {
        return position;
    }

    /**
     * @param position the position to set
     */
    public void setPosition(String position) {
        this.position = position;
    }

    /**
     * @return the candidate
     */
    public String getCandidate() {
        return candidate;
    }

    /**
     * @param candidate the candidate to set
     */
    public void setCandidate(String candidate) {
        this.candidate = candidate;
    }

    /**
     * @return the numberofchoice
     */
    public String getNumberofchoice() {
        return numberofchoice;
    }

    /**
     * @param numberofchoice the numberofchoice to set
     */
    public void setNumberofchoice(String numberofchoice) {
        this.numberofchoice = numberofchoice;
    }

   
}
