package com.myapp.struts.hbm;
// Generated Jun 13, 2011 4:44:17 PM by Hibernate Tools 3.2.1.GA



/**
 * Candidate1 generated by hbm2java
 */
public class Candidate1  implements java.io.Serializable {


     private Candidate1Id id;
     private String candidateName;
     private String enrollment;
     private byte[] menifesto;
     private int offlineVote;
     private int agm;

    public int getAgm() {
        return agm;
    }

    public void setAgm(int agm) {
        this.agm = agm;
    }

    public int getOfflineVote() {
        return offlineVote;
    }

    public void setOfflineVote(int offlineVote) {
        this.offlineVote = offlineVote;
    }


    public byte[] getMenifesto() {
        return menifesto;
    }

    public void setMenifesto(byte[] menifesto) {
        this.menifesto = menifesto;
    }

     

    public String getEnrollment() {
        return enrollment;
    }

    public void setEnrollment(String enrollment) {
        this.enrollment = enrollment;
    }

     
    public String getCandidateName() {
        return candidateName;
    }

    public void setCandidateName(String candidateName) {
        this.candidateName = candidateName;
    }
    public Candidate1() {
    }

    public Candidate1(Candidate1Id id) {
       this.id = id;
    }
   
    public Candidate1Id getId() {
        if(id==null)
            id = new Candidate1Id();
        return this.id;
    }
    
    public void setId(Candidate1Id id) {
        this.id = id;
    }




}


