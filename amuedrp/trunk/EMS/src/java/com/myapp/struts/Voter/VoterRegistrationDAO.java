/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.Voter;

import com.myapp.struts.hbm.AdminRegistration;
import com.myapp.struts.hbm.HibernateUtil;
import com.myapp.struts.hbm.VoterRegistration;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author akhtar
 */
public class VoterRegistrationDAO {
public static void insert(VoterRegistration obj) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;


        try {
            tx = (Transaction) session.beginTransaction();

            session.save(obj);
            tx.commit();



        } catch (Exception ex) {
        //    return false;

            //  System.out.println(ex.toString());

        } finally {
            //session.close();
        }
     //   return true;


}
public static boolean update(VoterRegistration obj) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = (Transaction) session.beginTransaction();
            session.update(obj);
            tx.commit();
        } catch (RuntimeException e) {

            tx.rollback();
            return false;

        }

        return true;

    }
        public static void delete(String enrollment,String instituteId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;


        try {
            tx = (Transaction) session.beginTransaction();
            Query query = (Query) session.createQuery("Delete From  VoterRegistration where id.enrollment = :en  and id.instituteId= :instituteid");

            query.setString("en", enrollment);
            query.setString("instituteid", instituteId);
            query.executeUpdate();
            tx.commit();



        } catch (Exception ex) {
            System.out.println(ex);
       //     return false;

            //  System.out.println(ex.toString());

        } finally {
            // session.close();
        }
      //  return true;

    }
    public static VoterRegistration searchVoterRegistration(String instituteid,String Enrollment) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(VoterRegistration.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.enrollment", Enrollment))
                    .add(Restrictions.eq("id.instituteId", instituteid)));

            return (VoterRegistration) criteria.uniqueResult();


        } finally {
            session.close();
        }
    }

    public List getVoterDetailsByStatus(String instituteid,String status){
  Session session =null;
    Transaction tx = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            String query1 = "FROM VoterRegistration where id.instituteId=:instituteId";

            if(status!=null && !status.equalsIgnoreCase("AB"))
                query1 = query1 + " and status = :status";
            if(status!=null && status.equalsIgnoreCase("AB"))
                query1 = query1 + " and (status like 'Block' or status like 'Registered'";
            Query query = session.createQuery(query1);
            if(status!=null && !status.equalsIgnoreCase("AB"))
                query.setString("status",status );
             query.setString("instituteId",instituteid );

            return query.list();
        }
        finally {
            session.close();
        }
}


     public List<VoterRegistration> getVoterDetailsByStatus1(String instituteid){
  Session session =null;
    Transaction tx = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("FROM VoterRegistration where status = :status and id.instituteId=:instituteId");
             query.setString("status","NOT REGISTERED" );
             query.setString("instituteId",instituteid );

            return (List<VoterRegistration>) query.list();
        }
        finally {
            session.close();
        }
}

   public Integer getVoterRequestCount(String instituteid,String status){
        Session session =null;
    Transaction tx = null;
    try {

        session = HibernateUtil.getSessionFactory().openSession();
        //Transaction tx = null;
        Criteria criteria = session.createCriteria(VoterRegistration.class)
                 .setProjection(Projections.count("id.instituteId"));
        criteria.add(Restrictions.eq("status",status ));
         criteria.add(Restrictions.eq("id.instituteId",instituteid ));
            Integer countrequest = (Integer)criteria.uniqueResult();

    //Session session = HibernateUtil.getSessionFactory().openSession();


            return countrequest;
        }
        finally {
            session.close();
        }
}
}


