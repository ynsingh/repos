/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.hbm;

/**
 *
 * @author Edrp-04
 */
public class BallotPositionCandi {
   
    private Candidate1 candidate1;
    private Position1 position1;

    /**
     * @return the ballot
  

    /**
     * @return the candidate1
     */
    public Candidate1 getCandidate1() {
       if(candidate1==null)
            candidate1 = new Candidate1();
        return candidate1;
    }

    /**
     * @param candidate1 the candidate1 to set
     */
    public void setCandidate1(Candidate1 candidate1) {
        this.candidate1 = candidate1;
    }

    /**
     * @return the position1
     */
    public Position1 getPosition1() {
        if(position1==null)
            position1 = new Position1();
        return position1;
    }

    /**
     * @param position1 the position1 to set
     */
    public void setPosition1(Position1 position1) {
        this.position1 = position1;
    }

}
