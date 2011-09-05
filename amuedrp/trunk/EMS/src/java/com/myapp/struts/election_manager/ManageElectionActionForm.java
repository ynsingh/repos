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
 * @author faraz
 */
public class ManageElectionActionForm extends org.apache.struts.action.ActionForm {
    
    private String election_id;

    private String button;

    public String getButton() {
        return button;
    }

    public void setButton(String button) {
        this.button = button;
    }

    public String getElection_id() {
        return election_id;
    }

    public void setElection_id(String election_id) {
        this.election_id = election_id;
    }

    
    /**
     * @return
     */
    

    /**
     *
     */
    public ManageElectionActionForm() {
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
