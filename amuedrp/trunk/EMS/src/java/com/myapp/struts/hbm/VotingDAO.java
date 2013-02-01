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

            sql = "select q.election_name,CONVERT(q.position_id USING utf8) position_id,q.position_name,q.number_of_choice,q.enrolment,q.candidate_name,q.offline_vote,q.agm,CONVERT(q.votes USING utf8) as votes from (select e.election_name,p1.position_id,p1.position_name,p1.number_of_choice,c1.enrolment,c1.candidateName candidate_name,c1.offline_vote offline_vote,c1.agm agm,(select count(*) vote from voting_ballot vb where vb.position_id=p1.position_id and vb.candidate_id=c1.candidate_id) as votes from election e,position1 p1,candidate1 c1 where e.election_id = p1.election_id and p1.institute_id = e.institute_id and p1.position_id = c1.position_id and e.election_id=:election_id and e.institute_id=:institute_id order by e.election_id,p1.position_id,(select count(*) from voting_ballot vb where vb.position_id=p1.position_id and vb.candidate_id=c1.candidate_id) desc) q";


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
  public Result GetNoOfCand(String institute_id,String election_id)
    {
    Session session =null;
    Result tx = null;
    
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

     
            
            String sql="";

            sql = "select max(candidate_id) cand from candidate1 where election_id=:election_id and institute_id=:institute_id group by election_id";
                    
      
                
           
             Query query =  session.createSQLQuery(sql)
                  .setResultTransformer(Transformers.aliasToBean(Result.class));

          query.setString("institute_id", institute_id);
          query.setString("election_id",election_id);
          
            tx=(Result) query.uniqueResult();
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
 
public List<Result> GetPreferencialResult(String institute_id,String election_id)
    {
    Session session =null;
    List<Result> tx = null;
    
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

     Result no_of_pref= (Result)GetNoOfCand(institute_id,election_id);
            //get max candidate count from positions select a.voter_id,a.candidate_id,  (SELECT count(preference) FROM preferencial_voting  WHERE preference = 1  AND candidate_id = a.candidate_id  GROUP BY candidate_id, preference) Pef, (SELECT count(preference) FROM preferencial_voting  WHERE preference = 2  AND candidate_id = a.candidate_id  GROUP BY candidate_id, preference) Pef, (SELECT count(preference) FROM preferencial_voting  WHERE preference = 3  AND candidate_id = a.candidate_id  GROUP BY candidate_id, preference) Pef, (SELECT count(preference) FROM preferencial_voting  WHERE preference = 4  AND candidate_id = a.candidate_id  GROUP BY candidate_id, preference) Pef, (SELECT count(preference) FROM preferencial_voting  WHERE preference = 5  AND candidate_id = a.candidate_id  GROUP BY candidate_id, preference) Pef FROM preferencial_voting AS a WHERE a.voter_id IN (SELECT MAX(voter_id) FROM preferencial_voting GROUP BY candidate_id) GROUP BY a.candidate_id, a.voter_id**********************************

       
            String sql="";
      
    sql="select  e.election_name,a.position_id,p1.position_name,p1.number_of_choice,c1.enrolment,c1.candidateName candidate_name,concat(" ;       
      
                
    for(int i=0;i<no_of_pref.getCand();i++)
     sql+="CONVERT(Coalesce((SELECT count(preference) FROM preferencial_voting  WHERE preference = "+(i+1)+"  and candidate_id = a.candidate_id and position_id=a.position_id  and election_id=a.election_id  GROUP BY candidate_id, preference),0) USING utf8),"  ;         
        
      sql=sql.substring(0,sql.length()-1);
      
     sql+=")  pref FROM preferencial_voting a,election e,position1 p1,candidate1 c1 WHERE a.election_id=e.election_id and a.institute_id=e.institute_id and e.election_id = p1.election_id and p1.institute_id = e.institute_id and p1.position_id = c1.position_id and a.position_id=p1.position_id and c1.candidate_id=a.candidate_id and a.voter_id IN (SELECT MAX(voter_id) FROM preferencial_voting GROUP BY candidate_id) and a.institute_id=:institute_id and a.election_id=:election_id GROUP BY a.candidate_id, a.voter_id,a.position_id order by a.position_id,c1.candidateName";
System.out.println(sql+"**********************************");
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

public List<Result> GetNoOfPos(String institute_id,String election_id)
    {
    Session session =null;
    List<Result> tx = null;
    
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

     
            
            String sql="";

            sql = "select convert(position_id using utf8) position_id  from position1  where election_id=:election_id and institute_id=:institute_id";
                    
      
                
           
             Query query =  session.createSQLQuery(sql)
                  .setResultTransformer(Transformers.aliasToBean(Result.class));

          query.setString("institute_id", institute_id);
          query.setString("election_id",election_id);
          
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

public List<Result> GetPreferencialResult1(String institute_id,String election_id,List<Result> res)
    {
    Session session =null;
    List<Result> tx = null;
    
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

     Result no_of_pref= (Result)GetNoOfCand(institute_id,election_id);
            //get max candidate count from positions select a.voter_id,a.candidate_id,  (SELECT count(preference) FROM preferencial_voting  WHERE preference = 1  AND candidate_id = a.candidate_id  GROUP BY candidate_id, preference) Pef, (SELECT count(preference) FROM preferencial_voting  WHERE preference = 2  AND candidate_id = a.candidate_id  GROUP BY candidate_id, preference) Pef, (SELECT count(preference) FROM preferencial_voting  WHERE preference = 3  AND candidate_id = a.candidate_id  GROUP BY candidate_id, preference) Pef, (SELECT count(preference) FROM preferencial_voting  WHERE preference = 4  AND candidate_id = a.candidate_id  GROUP BY candidate_id, preference) Pef, (SELECT count(preference) FROM preferencial_voting  WHERE preference = 5  AND candidate_id = a.candidate_id  GROUP BY candidate_id, preference) Pef FROM preferencial_voting AS a WHERE a.voter_id IN (SELECT MAX(voter_id) FROM preferencial_voting GROUP BY candidate_id) GROUP BY a.candidate_id, a.voter_id**********************************

       
            String sql="";
      
    sql="select  e.election_name,a.position_id,p1.position_name,p1.number_of_choice,c1.enrolment,c1.candidateName candidate_name,concat(" ;       
      
                
     for(int i=0;i<no_of_pref.getCand();i++)
     sql+="CONVERT(Coalesce((SELECT count(preference) FROM preferencial_voting  WHERE preference = "+(i+1)+"  and candidate_id = a.candidate_id and position_id=a.position_id  and election_id=a.election_id  GROUP BY candidate_id, preference),0) USING utf8),"  ;         
        
   
     
     sql=sql.substring(0,sql.length()-1);
      
     sql+=")  pref FROM preferencial_voting a,election e,position1 p1,candidate1 c1 WHERE a.election_id=e.election_id and a.institute_id=e.institute_id and e.election_id = p1.election_id and p1.institute_id = e.institute_id and p1.position_id = c1.position_id and a.position_id=p1.position_id and c1.candidate_id=a.candidate_id and a.voter_id IN (SELECT MAX(voter_id) FROM preferencial_voting GROUP BY candidate_id) and a.institute_id=:institute_id and a.election_id=:election_id and a.position_id in (";
     
     for(int index=0;index<res.size();index++)
     sql+=res.get(index).getPosition_id()+",";
     
     sql=sql.substring(0,sql.length()-1);
             
             
          sql+=") GROUP BY a.candidate_id, a.voter_id,a.position_id,preference order by pref desc limit "+res.size();
System.out.println(sql+"**********************************");
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
