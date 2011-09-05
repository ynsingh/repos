package com.myapp.struts.hbm;
// Generated Jun 16, 2011 5:12:32 PM by Hibernate Tools 3.2.1.GA



/**
 * VotingBallotId generated by hbm2java
 */
public class VotingBallotId  implements java.io.Serializable {


     private String voterBallotId;
     private String positionId;
     private String candidateId;

    public VotingBallotId() {
    }

    public VotingBallotId(String voterBallotId, String positionId, String candidateId) {
       this.voterBallotId = voterBallotId;
       this.positionId = positionId;
       this.candidateId = candidateId;
    }
   
    public String getVoterBallotId() {
        return this.voterBallotId;
    }
    
    public void setVoterBallotId(String voterBallotId) {
        this.voterBallotId = voterBallotId;
    }
    public String getPositionId() {
        return this.positionId;
    }
    
    public void setPositionId(String positionId) {
        this.positionId = positionId;
    }
    public String getCandidateId() {
        return this.candidateId;
    }
    
    public void setCandidateId(String candidateId) {
        this.candidateId = candidateId;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof VotingBallotId) ) return false;
		 VotingBallotId castOther = ( VotingBallotId ) other; 
         
		 return ( (this.getVoterBallotId()==castOther.getVoterBallotId()) || ( this.getVoterBallotId()!=null && castOther.getVoterBallotId()!=null && this.getVoterBallotId().equals(castOther.getVoterBallotId()) ) )
 && ( (this.getPositionId()==castOther.getPositionId()) || ( this.getPositionId()!=null && castOther.getPositionId()!=null && this.getPositionId().equals(castOther.getPositionId()) ) )
 && ( (this.getCandidateId()==castOther.getCandidateId()) || ( this.getCandidateId()!=null && castOther.getCandidateId()!=null && this.getCandidateId().equals(castOther.getCandidateId()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getVoterBallotId() == null ? 0 : this.getVoterBallotId().hashCode() );
         result = 37 * result + ( getPositionId() == null ? 0 : this.getPositionId().hashCode() );
         result = 37 * result + ( getCandidateId() == null ? 0 : this.getCandidateId().hashCode() );
         return result;
   }   


}


