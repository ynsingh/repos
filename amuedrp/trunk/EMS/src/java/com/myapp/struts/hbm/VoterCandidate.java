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
public class VoterCandidate implements Serializable {
private VoterRegistration voter;
private CandidateRegistration candidate;

    public CandidateRegistration getCandidateRegistration() {
        return candidate;
    }

    public void setCandidateRegistration(CandidateRegistration candidate) {
        this.candidate = candidate;
    }

    public VoterRegistration getVoterRegistration() {
        return voter;
    }

    public void setVoterRegistration(VoterRegistration voter) {
        this.voter = voter;
    }


}
