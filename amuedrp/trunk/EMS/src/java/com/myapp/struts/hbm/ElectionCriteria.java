/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.hbm;

import java.io.Serializable;

/**
 *
 * @author iqubal
 */
public class ElectionCriteria implements Serializable{

    private Ruleanswer Ruleanswer;
    private Electionrule Electionrule;

    public Electionrule getElectionrule() {
        return Electionrule;
    }

    public void setElectionrule(Electionrule Electionrule) {
        this.Electionrule = Electionrule;
    }

    public Ruleanswer getRuleanswer() {
        return Ruleanswer;
    }

    public void setRuleanswer(Ruleanswer Ruleanswer) {
        this.Ruleanswer = Ruleanswer;
    }
}
