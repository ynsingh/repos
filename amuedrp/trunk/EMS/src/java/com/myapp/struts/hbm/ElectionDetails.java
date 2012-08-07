/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.hbm;
import java.io.Serializable;
/**
 *
 * @author edrp01
 */
public class ElectionDetails implements Serializable {

     private VoterRegistration voterRegistration;
     private Election election;
     private SetVoter setVoter;

    public Election getElection() {
        return election;
    }

    public void setElection(Election election) {
        this.election = election;
    }

    public SetVoter getSetVoter() {
        return setVoter;
    }

    public void setSetVoter(SetVoter setVoter) {
        this.setVoter = setVoter;
    }

    public VoterRegistration getVoterRegistration() {
        return voterRegistration;
    }

    public void setVoterRegistration(VoterRegistration voterRegistration) {
        this.voterRegistration = voterRegistration;
    }

}
