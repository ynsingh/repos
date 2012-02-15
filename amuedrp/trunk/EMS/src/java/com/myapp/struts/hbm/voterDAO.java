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
                    tx = session.beginTransaction();
               Candidate1 vot1 = (Candidate1)E.get(i);
            session.update(vot1);
            System.out.println(vot1);
            if ( session.isDirty()) { //20, same as the JDBC batch size
//flush a batch of inserts and release memory:
session.flush();
session.clear();
}


  tx.commit();
   i++;
           }

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
    try {
        session= HibernateUtil.getSessionFactory().openSession();
           session.beginTransaction();
   /* Session session =null;
    String obj=null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
           session.beginTransaction();
            Query query = session.createQuery("Select Max(id.voterBallotId) FROM Voting where  id.electionId = :electionId and id.instituteId=:instituteId");

            query.setString("electionId",electionId );
             query.setString("instituteId", instituteId);

           obj=(String)query.uniqueResult();
           session.getTransaction().commit();
        }

        catch (RuntimeException e) {
        e.printStackTrace();


        }
    finally{
    session.close();
    }
        return obj;*/

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


 /*   String id="";

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            Criteria criteria = session.createCriteria(ElectionManager.class);
            Criterion a = Restrictions.eq("id.instituteId", instituteId);



           // LogicalExpression le = Restrictions.and(a, b);
            Integer maxbiblio = criteria.add(a).setProjection(Projections.count("id.managerId")).uniqueResult()==null?0:Integer.valueOf(criteria.add(a).setProjection(Projections.count("id.managerId")).uniqueResult().toString());
           System.out.println(maxbiblio);

            if (maxbiblio == null) {
                maxbiblio = 1;
            } else {
                maxbiblio++;
            }

           id=String.valueOf(maxbiblio);



            session.getTransaction().commit();
        }
        catch(Exception e){
        e.printStackTrace();

        }
        finally {
            session.close();
        }
 return id;

}*/
public String InsertVote(ArrayList E)
{
    Session session =null;
    String obj=null;
    Transaction tx = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
           Iterator it = E.iterator();
           System.out.println("its working"+E);
           
           VotingProcess vot1 = (VotingProcess)it.next();
            session.save(vot1);

           Voting vot = (Voting)it.next();
            session.save(vot);

           while(it.hasNext())
           {
               ArrayList col = (ArrayList)it.next();
               Iterator itcol = col.iterator();
               System.out.println("its working1"+col);
               ArrayList<VotingBallot> l = (ArrayList<VotingBallot>) E.get(2);
              Iterator it1 = l.iterator();
               while(it1.hasNext())
               {
                  VotingBallot vb = new VotingBallot();
                   vb = (VotingBallot)it1.next();
                   session.save(vb);
                   

               }
               
             /*  while(itcol.hasNext())
               {
                   Object i = itcol.next();
                   if(i.getClass().getName().equalsIgnoreCase("com.myapp.struts.hbm.Voting"))
                   {
                        Voting vot = (Voting)i;
                        session.save(vot);
                        vot = null;
                   }
                   else if(i.getClass().getName().equalsIgnoreCase("com.myapp.struts.hbm.VotingBallot"))
                   {
                        VotingBallot votBal = (VotingBallot)i;
                        session.save(votBal);
                        votBal=null;
                   }
                   System.out.println("its working2");
               }*/
               itcol = null;
               col=null;
           }
           tx.commit();
            obj= (String)"Your Vote Successfully Casted";
        }
        catch (RuntimeException e) {
          
            System.out.println("voterDAo InsertVote:");e.printStackTrace();
            tx.rollback();
          obj="Vote not Saved!";
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
}
