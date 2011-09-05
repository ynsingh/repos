/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.hbm;

import java.io.Serializable;

/**
 *
 * @author Edrp-04
 */
public class Election_Manager_StaffDetail implements Serializable {
    private ElectionManager electionManager;
    private StaffDetail staffDetail;
    private Login login;

    /**
     * @return the electionManager
     */
    public ElectionManager getElectionManager() {
        return electionManager;
    }

    /**
     * @param electionManager the electionManager to set
     */
    public void setElectionManager(ElectionManager electionManager) {
        this.electionManager = electionManager;
    }

    /**
     * @return the staffDetail
     */
    public StaffDetail getStaffDetail() {
        return staffDetail;
    }

    /**
     * @param staffDetail the staffDetail to set
     */
    public void setStaffDetail(StaffDetail staffDetail) {
        this.staffDetail = staffDetail;
    }

    /**
     * @return the login
     */
    public Login getLogin() {
        return login;
    }

    /**
     * @param login the login to set
     */
    public void setLogin(Login login) {
        this.login = login;
    }

    

}
