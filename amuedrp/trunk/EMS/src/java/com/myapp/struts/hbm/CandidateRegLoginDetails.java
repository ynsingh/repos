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
public class CandidateRegLoginDetails implements Serializable {
    private Candidate1 candidate1;
    private StaffDetail staffDetail;
    private CandidateRegistration candiReg;
    private VoterRegistration voter;
    private Login login;
    private Election election;
    private Position1 position;

    public Election getElection() {
        return election;
    }

    public void setElection(Election election) {
        this.election = election;
    }

    public Position1 getPosition() {
        return position;
    }

    public void setPosition(Position1 position) {
        this.position = position;
    }
    
    

    public VoterRegistration getVoter() {
        return voter;
    }

    public void setVoter(VoterRegistration voter) {
        this.voter = voter;
    }

    

    public CandidateRegistration getCandiReg() {
        return candiReg;
    }

    public void setCandiReg(CandidateRegistration candiReg) {
        this.candiReg = candiReg;
    }

    public Candidate1 getCandidate1() {
        return candidate1;
    }

    public void setCandidate1(Candidate1 candidate1) {
        this.candidate1 = candidate1;
    }

    /**
     * @return the electionManager
     */
    
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
