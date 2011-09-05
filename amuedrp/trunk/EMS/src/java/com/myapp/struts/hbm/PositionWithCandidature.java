/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.hbm;

import java.math.BigInteger;

/**
 *
 * @author faraz
 */
public class PositionWithCandidature {
    private String position_name;
    private BigInteger candidature;
    private int position_id;

    public BigInteger getCandidature() {
        return candidature;
    }

    public void setCandidature(BigInteger candidature) {
        //System.out.println(candidature.getClass());
        this.candidature = candidature;
    }

    public int getPosition_id() {
        return position_id;
    }

    public void setPosition_id(int position_id) {
        this.position_id = position_id;
    }

    public String getPosition_name() {
        return position_name;
    }

    public void setPosition_name(String position_name) {
        this.position_name = position_name;
    }

    

    
}
