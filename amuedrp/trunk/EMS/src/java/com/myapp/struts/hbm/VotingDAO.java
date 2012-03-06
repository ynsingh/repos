/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.hbm;

import java.util.List;
import com.myapp.struts.Voting.Result;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.ResultTransformer;
import org.hibernate.transform.Transformers;

/**
 *
 * @author Edrp-04
 */
public class VotingDAO {
	public VotingProcess GetVoteStatus(String institute_id,String election_id,String enroll)
    	{
    		Session session =null;
    		VotingProcess obj=null;
    		try {
        		session= HibernateUtil.getSessionFactory().openSession();
            		session.beginTransaction();
               		Query query = session.createQuery("FROM VotingProcess where id.instituteId = :institute_id and id.electionId=:election_id and id.voterId=:user_id");
                	query.setString("user_id",enroll );
                   	query.setString("institute_id",institute_id );
                      	query.setString("election_id",election_id );
               		obj=(VotingProcess)query.uniqueResult();
            		session.getTransaction().commit();
        	}
    		catch(RuntimeException e){
    			e.printStackTrace();
    		}
    		finally {
            		session.close();
        	}
    	return obj;
	}


    public List<Result> GetResult(String institute_id,String election_id)
    {
    Session session =null;
    List<Result> tx = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            String sql="";

            sql = "select q.election_name,CONVERT(q.position_id USING utf8) position_id,q.position_name,q.number_of_choice,q.enrolment,q.candidate_name,CONVERT(q.votes USING utf8) as votes from (select e.election_name,p1.position_id,p1.position_name,p1.number_of_choice,c1.enrolment,c1.candidateName candidate_name,(select count(*) vote from voting_ballot vb where vb.position_id=p1.position_id and vb.candidate_id=c1.candidate_id) as votes from election e,position1 p1,candidate1 c1 where e.election_id = p1.election_id and p1.institute_id = e.institute_id and p1.position_id = c1.position_id and e.election_id=:election_id and e.institute_id=:institute_id order by e.election_id,p1.position_id,(select count(*) from voting_ballot vb where vb.position_id=p1.position_id and vb.candidate_id=c1.candidate_id) desc) q";


             Query query =  session.createSQLQuery(sql)
                  .setResultTransformer(Transformers.aliasToBean(Result.class));

          query.setString("institute_id", institute_id);
          query.setString("election_id",election_id);
          System.out.println(query.list().getClass());
            tx=(List<Result>) query.list();
            session.getTransaction().commit();
        }
    catch(RuntimeException e){
    e.printStackTrace();
    }
    finally {
            session.close();
        }
    return tx;
}

     public List<Result> GetResultPostal(String institute_id,String election_id)
    {
    Session session =null;
    List<Result> tx = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            String sql="";

            sql = "select q.election_name,CONVERT(q.position_id USING utf8) position_id,q.position_name,q.number_of_choice,q.enrolment,q.candidate_name,CONVERT(q.votes USING utf8) as votes from (select e.election_name,p1.position_id,p1.position_name,p1.number_of_choice,c1.enrolment,c1.candidateName candidate_name,c1.offline_vote as votes from election e,position1 p1,candidate1 c1 where e.election_id = p1.election_id and p1.institute_id = e.institute_id and p1.position_id = c1.position_id and e.election_id=:election_id and e.institute_id=:institute_id order by e.election_id,p1.position_id,(select count(*) from voting_ballot vb where vb.position_id=p1.position_id and vb.candidate_id=c1.candidate_id) desc) q";


             Query query =  session.createSQLQuery(sql)
                  .setResultTransformer(Transformers.aliasToBean(Result.class));

          query.setString("institute_id", institute_id);
          query.setString("election_id",election_id);
          System.out.println(query.list().getClass());
            tx=(List<Result>) query.list();
            session.getTransaction().commit();
        }
    catch(RuntimeException e){
    e.printStackTrace();
    }
    finally {
            session.close();
        }
    return tx;
}

      public List<Result> GetResultAGM(String institute_id,String election_id)
    {
    Session session =null;
    List<Result> tx = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            String sql="";

            sql = "select q.election_name,CONVERT(q.position_id USING utf8) position_id,q.position_name,q.number_of_choice,q.enrolment,q.candidate_name,CONVERT(q.votes USING utf8) as votes from (select e.election_name,p1.position_id,p1.position_name,p1.number_of_choice,c1.enrolment,c1.candidateName candidate_name,c1.agm as votes from election e,position1 p1,candidate1 c1 where e.election_id = p1.election_id and p1.institute_id = e.institute_id and p1.position_id = c1.position_id and e.election_id=:election_id and e.institute_id=:institute_id order by e.election_id,p1.position_id,(select count(*) from voting_ballot vb where vb.position_id=p1.position_id and vb.candidate_id=c1.candidate_id) desc) q";


             Query query =  session.createSQLQuery(sql)
                  .setResultTransformer(Transformers.aliasToBean(Result.class));

          query.setString("institute_id", institute_id);
          query.setString("election_id",election_id);
          System.out.println(query.list().getClass());
            tx=(List<Result>) query.list();
            session.getTransaction().commit();
        }
    catch(RuntimeException e){
    e.printStackTrace();
    }
    finally {
            session.close();
        }
    return tx;
}

}
