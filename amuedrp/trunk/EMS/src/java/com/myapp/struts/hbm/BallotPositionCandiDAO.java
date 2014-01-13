/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.hbm;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
/**
 *
 * @author Edrp-04
 */
public class BallotPositionCandiDAO {

     public void insert(BallotPositionCandi bpc){
    Session session =null;
    Transaction tx = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            
            session.save(bpc.getCandidate1());
            session.save(bpc.getPosition1());
            tx.commit();
        }
        catch (RuntimeException e) {
            if(bpc != null)
                tx.rollback();
            e.printStackTrace();
            throw e;
        }
        finally {
          session.close();
        }
    }

    public void update(BallotPositionCandi bpc) {
        Session session =null;
    Transaction tx = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            
            session.update(bpc.getCandidate1());
            session.update(bpc.getPosition1());

            tx.commit();
        }
        catch (RuntimeException e) {
          e.printStackTrace();
                tx.rollback();
            throw e;
        }
finally {
          session.close();
        }
}

    public static void deleteBallot(String electionId,String instituteId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;


        try {
            tx = (Transaction) session.beginTransaction();
            Query query = session.createQuery("Delete From  Ballot where id.electionId = :electionid2  and id.instituteId= :instituteid");

            query.setString("electionid2", electionId);
            query.setString("instituteid", instituteId);
            query.executeUpdate();
            tx.commit();

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            tx.rollback();
            System.out.println(ex);
       //     return false;

            //  System.out.println(ex.toString());

        }
        finally
        {
            session.close();
        }
      //  return true;

    }
    
    public List getPositionID(int positionId,String instituteId){
 Session session =null;
    List tx = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("FROM Position1 where id.positionId=:positionId and id.instituteId=:instituteId");
             query.setInteger("positionId", positionId);
             query.setString("instituteId", instituteId);
            tx= query.list();
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
