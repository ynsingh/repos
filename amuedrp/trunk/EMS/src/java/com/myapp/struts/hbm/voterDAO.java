/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.hbm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import com.myapp.struts.utility.UserLog;


public class voterDAO {
	public String InsertVote1(List E)
	{
		Session session =null;
		String obj=null;
		Transaction tx = null;
		try {
        		session= HibernateUtil.getSessionFactory().openSession();
			int i=0;
        		while(i<E.size())
			{
	               		Candidate1 vot1 = (Candidate1)E.get(i);
				session.update(vot1);
			        System.out.println(vot1);
				if ( session.isDirty()) { //20, same as the JDBC batch size
//flush a batch of inserts and release memory:
					session.flush();
					session.clear();
				}
				boolean flg1;
			        do{
					flg1=true;
				        wait1(1000);
				        tx = session.beginTransaction();
				        tx.commit();
			                if(!(tx.wasCommitted())){
			                        tx.rollback();
			                        flg1=false;
			                }
			        }
			        while(!(flg1));
			i++;
			}
		//	String path = servlet.getRealPath("/EMSLOG");
			
			obj= (String)"Record Updated Successfully";
		}
	        catch (RuntimeException e) {
			System.out.println("voterDAo InsertVote1:"+e.getMessage());
		        tx.rollback();
			obj="Record not Updated Successfull";
	        }
		finally{
			session.close();
		}
		return obj;
	}



public String getMaxVoterBallotId(String electionId,String instituteId)
{
     Session session =null;
    String obj=null;
	Transaction tx = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();

           session.beginTransaction();
     Criteria criteria = session.createCriteria(Voting.class);
            Criterion a = Restrictions.eq("id.electionId", electionId);
            Criterion b = Restrictions.eq("id.instituteId", instituteId);
            LogicalExpression le = Restrictions.and(a, b);
             Integer maxbiblio = criteria.add(le).setProjection(Projections.count("id.voterBallotId")).uniqueResult()==null?0:Integer.valueOf(criteria.add(le).setProjection(Projections.count("id.voterBallotId")).uniqueResult().toString());
            if (maxbiblio == null) {
                maxbiblio = 1;
            } else {
                maxbiblio++;
            }
 obj=String.valueOf(maxbiblio);
           session.getTransaction().commit();
        }
        catch (RuntimeException e) {
        e.printStackTrace();
        }
    finally{
    session.close();
    }
        return obj;
}

public String InsertVote(ArrayList E, String path)
{
    Session session =null;
    String obj=null;
    Transaction tx = null;
	StringBuffer  st=new StringBuffer();     
    try {
        session= HibernateUtil.getSessionFactory().openSession();
           Iterator it = E.iterator();
          // System.out.println("its working"+E);
           
           VotingProcess vot1 = (VotingProcess)it.next();
            session.save(vot1);
		st.append("Voting Process (Institue ID, Election Id,Voter Id)==>"+vot1.getId().getInstituteId()+"|"+vot1.getId().getElectionId()+"|"+vot1.getId().getVoterId()+"\n");
           Voting vot = (Voting)it.next();
            session.save(vot);
		st.append("Voting (Election id, Institue Id, voter ballot id)==> "+vot.getId().getElectionId()+"|"+vot.getId().getInstituteId()+"|"+vot.getId().getVoterBallotId()+"\n");
           while(it.hasNext())
           {
               ArrayList col = (ArrayList)it.next();
               Iterator itcol = col.iterator();
            //   System.out.println("its working1"+col);
               ArrayList<VotingBallot> l = (ArrayList<VotingBallot>) E.get(2);
              Iterator it1 = l.iterator();
               while(it1.hasNext())
               {
                  VotingBallot vb = new VotingBallot();
                   vb = (VotingBallot)it1.next();
                   session.save(vb);
                   st.append("Voting Ballot (Voter Ballot id,Position id,candidate id)==>"+vb.getId().getVoterBallotId()+"|"+vb.getId().getPositionId()+"|"+vb.getId().getCandidateId()+"\n");

               }
               itcol = null;
               col=null;
           }
	    boolean flg1;
	do{
	    flg1=true;	    
	    wait1(100000);
            tx = session.beginTransaction();
            tx.commit();
  		if(!(tx.wasCommitted())){
			tx.rollback();
			flg1=false;
		}

	}
	while(!(flg1));
            obj= (String)"Your Vote Successfully Casted";
		UserLog.ErrorLog(st.toString()+" "+obj,path);		
        }
        catch (RuntimeException e) {
          
            System.out.println(" I am in catch voterDAo InsertVote:");e.printStackTrace();
            tx.rollback();
          obj="Vote not Saved!";
	UserLog.ErrorLog(st.toString()+"\n"+ obj,path);
        }
    finally{
    session.close();
    }
    return obj;
        
}




	private void wait1(int k){
		do{
		k--;
		}
		while(k>0);
	}


        public String InsertPVote(ArrayList E, String path)
{
    Session session =null;
    String obj=null;
    Transaction tx = null;
    StringBuffer  st=new StringBuffer();
    try {
        session= HibernateUtil.getSessionFactory().openSession();
           Iterator it = E.iterator();
          // System.out.println("its working"+E);

           VotingProcess vot1 = (VotingProcess)it.next();
           //String election_id=vot1.getId().getElectionId();
           //String insti_id=vot1.getId().getInstituteId();

            session.save(vot1);
		st.append("Voting Process (Institue ID, Election Id,Voter Id)==>"+vot1.getId().getInstituteId()+"|"+vot1.getId().getElectionId()+"|"+vot1.getId().getVoterId()+"\n");
           Voting vot = (Voting)it.next();
            session.save(vot);
		st.append("Voting (Election id, Institue Id, voter ballot id)==> "+vot.getId().getElectionId()+"|"+vot.getId().getInstituteId()+"|"+vot.getId().getVoterBallotId()+"\n");
           while(it.hasNext())
           {
               ArrayList col = (ArrayList)it.next();
               Iterator itcol = col.iterator();
            //   System.out.println("its working1"+col);
               ArrayList<VotingBallot> l = (ArrayList<VotingBallot>) E.get(2);
              Iterator it1 = l.iterator();
               while(it1.hasNext())
               {
                  VotingBallot vb = new VotingBallot();
                   vb = (VotingBallot)it1.next();
                   session.save(vb);
                   st.append("Voting Ballot (Voter Ballot id,Position id,candidate id)==>"+vb.getId().getVoterBallotId()+"|"+vb.getId().getPositionId()+"|"+vb.getId().getCandidateId()+"\n");
               }
              //prefertional Voting Table Insertion

                ArrayList<PreferencialVoting> l1 = (ArrayList<PreferencialVoting>) E.get(3);
              it1 = l1.iterator();
               while(it1.hasNext())
               {
                  PreferencialVoting pv = new PreferencialVoting();
                   pv = (PreferencialVoting)it1.next();
                   session.save(pv);
                   st.append("Prefertional Voting Ballot (Voter Ballot id,Position id,candidate id)==>"+pv.getVoterBallotId()+"|"+pv.getId().getPositionId()+"|"+pv.getId().getCandidateId()+"\n");
               }

               itcol = null;
               col=null;
           }
	    boolean flg1;
	do{
	    flg1=true;
	    wait1(100000);
            tx = session.beginTransaction();
            tx.commit();
  		if(!(tx.wasCommitted())){
			tx.rollback();
			flg1=false;
		}

	}
	while(!(flg1));
            obj= (String)"Your Vote Successfully Casted";
		UserLog.ErrorLog(st.toString()+" "+obj,path);
        }
        catch (RuntimeException e) {

            System.out.println(" I am in catch voterDAo InsertVote:");e.printStackTrace();
            tx.rollback();
          obj="Vote not Saved!";
	UserLog.ErrorLog(st.toString()+"\n"+ obj,path);
        }
    finally{
    session.close();
    }
    return obj;

}

public VotingProcess getVoter(String institute_id,String election_id,String voter_id)
{
    Session session =null;
    VotingProcess obj=null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("FROM VotingProcess where id.voterId=:voterId and id.electionId = :electionId and id.instituteId=:instituteId");

            query.setString("voterId",voter_id);
            query.setString("electionId",election_id );
            query.setString("instituteId", institute_id);

            obj= (VotingProcess)query.uniqueResult();
            session.getTransaction().commit();
        }
        catch (RuntimeException e) {
          e.printStackTrace();


        }
    finally{
    session.close();
    }
        return obj;

}

public PreferencialVoting getPreferencialVoter(String institute_id,String election_id,String voter_id)
{
    Session session =null;
    PreferencialVoting obj=null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("FROM PreferencialVoting where id.voterId=:voterId and id.electionId = :electionId and instituteId=:instituteId");

            query.setString("voterId",voter_id);
            query.setString("electionId",election_id );
            query.setString("instituteId", institute_id);

            obj= (PreferencialVoting)query.uniqueResult();
            session.getTransaction().commit();
        }
        catch (RuntimeException e) {
          e.printStackTrace();


        }
    finally{
    session.close();
    }
        return obj;

}
}
