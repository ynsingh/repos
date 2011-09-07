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
    private Login login;
    private Candidate1 candidate1;
    //private StaffDetail staffDetail;
    private CandidateRegistration candidateRegistration;
    private VoterRegistration voterRegistration;
    
    private Election election;
    private Position1 position1;

    public Election getElection() {
        return election;
    }

    public void setElection(Election election) {
        this.election = election;
    }

    public Position1 getPosition1() {
        return position1;
    }

    public void setPosition1(Position1 position) {
        this.position1 = position;
    }
    
    

    public VoterRegistration getVoterRegistration() {
        return voterRegistration;
    }

    public void setVoterRegistration(VoterRegistration voter) {
        this.voterRegistration = voter;
    }

    

    public CandidateRegistration getCandidateRegistration() {
        return candidateRegistration;
    }

    public void setCandidateRegistration(CandidateRegistration candiReg) {
        this.candidateRegistration = candiReg;
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
//    public StaffDetail getStaffDetail() {
//        return staffDetail;
//    }
//
//    /**
//     * @param staffDetail the staffDetail to set
//     */
//    public void setStaffDetail(StaffDetail staffDetail) {
//        this.staffDetail = staffDetail;
//    }

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
