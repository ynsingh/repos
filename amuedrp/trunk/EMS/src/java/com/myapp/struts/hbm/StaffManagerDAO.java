/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.hbm;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Query;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

/**
 *
 * @author Edrp-04
 */
public class StaffManagerDAO {




    public void insert(Election_Manager_StaffDetail staffmanager){
    Session session =null;
    Transaction tx = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.save(staffmanager);
            tx.commit();
        }
        catch (RuntimeException e) {
            if(staffmanager != null)
                tx.rollback();
            e.printStackTrace();
            throw e;
        }
        finally {
          session.close();
        }
    }


public void delete(Election_Manager_StaffDetail staffmanager){
    Session session =null;
    Transaction tx = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();

        tx = session.beginTransaction();
            session.delete(staffmanager.getStaffDetail());
            session.delete(staffmanager.getElectionManager());
            session.delete(staffmanager.getLogin());
            tx.commit();
        }
        catch (RuntimeException e) {
            if(staffmanager != null)
                tx.rollback();
            e.printStackTrace();
            throw e;
        }
        finally {
          session.close();
        }
    }
//    public  void delete(String staffId) {
//        Session session = HibernateUtil.getSessionFactory().openSession();
//        Transaction tx = null;
//
//
//        try {
//            tx = (Transaction) session.beginTransaction();
//            Query query = session.createQuery("Delete From  election_manager where :staffId = staffId");
//
//            query.executeUpdate();
//            tx.commit();
//
//
//
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            tx.rollback();
////            return false;
//
//
//
//        } finally {
//             session.close();
//        }
////        return true;
//
//    }

    public void update(Election_Manager_StaffDetail staffmanager) {
        Session session =null;
    Transaction tx = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.update(staffmanager.getStaffDetail());
             session.update(staffmanager.getElectionManager());
            tx.commit();
        }
        catch (RuntimeException e) {
         
                tx.rollback();
                e.printStackTrace();
            throw e;
        }
finally {
          session.close();
        }
}
}
