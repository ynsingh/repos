/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.Voting;

import java.io.Serializable;

/**
 *
 * @author Edrp-04
 */

public class Result implements  Serializable {

    private String election_name;
    private String position_name;
    private String number_of_choice;
    private String candidate_name;
     private String candidate_id;
      private String position_id;
       private String election_id;
        private String institute_id;
        private String votes;
          private String enrolment;

    public String getEnrolment() {
        return enrolment;
    }

    public void setEnrolment(String enrollment) {
        this.enrolment = enrollment;
    }

    public String getVotes() {
        return votes;
    }

    public void setVotes(String votes) {
        this.votes = votes;
    }

    /**
     * @return the election_name
     */
    public String getElection_name() {
        return election_name;
    }

    /**
     * @param election_name the election_name to set
     */
    public void setElection_name(String election_name) {
        this.election_name = election_name;
    }

    /**
     * @return the position_name
     */
    public String getPosition_name() {
        return position_name;
    }

    /**
     * @param position_name the position_name to set
     */
    public void setPosition_name(String position_name) {
        this.position_name = position_name;
    }

    /**
     * @return the number_of_choice
     */
    public String getNumber_of_choice() {
        return number_of_choice;
    }

    /**
     * @param number_of_choice the number_of_choice to set
     */
    public void setNumber_of_choice(String number_of_choice) {
        this.number_of_choice = number_of_choice;
    }

    /**
     * @return the candidate_name
     */
    public String getCandidate_name() {
        return candidate_name;
    }

    /**
     * @param candidate_name the candidate_name to set
     */
    public void setCandidate_name(String candidate_name) {
        this.candidate_name = candidate_name;
    }

    /**
     * @return the candidate_id
     */
    public String getCandidate_id() {
        return candidate_id;
    }

    /**
     * @param candidate_id the candidate_id to set
     */
    public void setCandidate_id(String candidate_id) {
        this.candidate_id = candidate_id;
    }

    /**
     * @return the position_id
     */
    public String getPosition_id() {
        return position_id;
    }

    /**
     * @param position_id the position_id to set
     */
    public void setPosition_id(String position_id) {
        this.position_id = position_id;
    }

    /**
     * @return the election_id
     */
    public String getElection_id() {
        return election_id;
    }

    /**
     * @param election_id the election_id to set
     */
    public void setElection_id(String election_id) {
        this.election_id = election_id;
    }

    /**
     * @return the institute_id
     */
    public String getInstitute_id() {
        return institute_id;
    }

    /**
     * @param institute_id the institute_id to set
     */
    public void setInstitute_id(String institute_id) {
        this.institute_id = institute_id;
    }




}
