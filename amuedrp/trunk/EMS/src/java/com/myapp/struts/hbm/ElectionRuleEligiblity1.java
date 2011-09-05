/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.hbm;


/**
 *
 * @author Edrp-04
 */
public class ElectionRuleEligiblity1  implements java.io.Serializable {
private Election election;
private Eligibility eligibility;
private Electionrule electionrule;
private Ballot ballot;

    public Election getElection() {
        
        return election;
    }

    public void setElection(Election election) {
        this.election = election;
    }

    public Electionrule getElectionrule() {
        
        return electionrule;
    }

    public void setElectionrule(Electionrule electionrule) {
        this.electionrule = electionrule;
    }

    public Eligibility getEligibility() {
        
        return eligibility;
    }

    public void setEligibility(Eligibility eligibility) {
        this.eligibility = eligibility;
    }

    /**
     * @return the ballot
     */
    public Ballot getBallot() {
        return ballot;
    }

    /**
     * @param ballot the ballot to set
     */
    public void setBallot(Ballot ballot) {
        this.ballot = ballot;
    }


}
