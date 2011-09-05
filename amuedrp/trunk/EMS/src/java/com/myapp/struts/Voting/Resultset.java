/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.Voting;

import com.myapp.struts.hbm.Candidate1;
import com.myapp.struts.hbm.Election;
import com.myapp.struts.hbm.Position1;

/**
 *
 * @author Edrp-04
 */
public class Resultset {
    private Election election;
    private Position1 position;
    private     Candidate1 candidate;

    /**
     * @return the election
     */
    public Election getElection() {
        return election;
    }

    /**
     * @param election the election to set
     */
    public void setElection(Election election) {
        this.election = election;
    }

    /**
     * @return the position
     */
    public Position1 getPosition() {
        return position;
    }

    /**
     * @param position the position to set
     */
    public void setPosition(Position1 position) {
        this.position = position;
    }

    /**
     * @return the candidate
     */
    public Candidate1 getCandidate() {
        return candidate;
    }

    /**
     * @param candidate the candidate to set
     */
    public void setCandidate(Candidate1 candidate) {
        this.candidate = candidate;
    }


}
