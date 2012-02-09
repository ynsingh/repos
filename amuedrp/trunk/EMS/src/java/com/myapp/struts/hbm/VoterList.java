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
public class VoterList implements Serializable {
private VoterRegistration voterRegistration;
private SetVoter setVoter;

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
