/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.hbm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Faraz Hasan
 */
public class voterDAO {
public String getMaxVoterBallotId(String electionId,String instituteId)
{
    Session session =null;
    Transaction tx = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            Query query = session.createQuery("Select Max(id.voterBallotId) FROM Voting where  id.electionId = :electionId and id.instituteId=:instituteId");

            query.setString("electionId",electionId );
             query.setString("instituteId", instituteId);

            return (String)query.uniqueResult();
        }
        catch (RuntimeException e) {
          //  if(bibDetails != null)


        }
        return null;
}
public String InsertVote(ArrayList E)
{
    Session session =null;
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
            return (String)"Your Vote Successfully Casted";
        }
        catch (RuntimeException e) {
          //  if(bibDetails != null)
            System.out.println("voterDAo InsertVote:"+e.getMessage());
            tx.rollback();
            return "Vote not Saved!";
        }
        
}
public VotingProcess getVoter(String institute_id,String election_id,String voter_id)
{
    Session session =null;
    Transaction tx = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            Query query = session.createQuery("FROM VotingProcess where id.voterId=:voterId and id.electionId = :electionId and id.instituteId=:instituteId");

            query.setString("voterId",voter_id);
            query.setString("electionId",election_id );
            query.setString("instituteId", institute_id);

            return (VotingProcess)query.uniqueResult();
        }
        catch (RuntimeException e) {
          //  if(bibDetails != null)


        }
        return null;

}
}
