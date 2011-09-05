/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.Candidate;

import com.myapp.struts.hbm.Candidate1;
import com.myapp.struts.hbm.CandidateRegLoginDetails;
import com.myapp.struts.hbm.CandidateRegistration;
import com.myapp.struts.hbm.HibernateUtil;
import com.myapp.struts.hbm.Login;
import com.myapp.struts.hbm.VoterCandidate;
import com.myapp.struts.hbm.VoterRegistration;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;


/**
 *
 * @author akhtar
 */
public class CandidateRegistrationDAO {
public static void insert(CandidateRegistration obj) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;


        try {
            tx = (Transaction) session.beginTransaction();

            session.save(obj);
            tx.commit();



        } catch (Exception ex) {
        //    return false;

              System.out.println(ex.toString());

        } finally {
            //session.close();
        }
     //   return true;


}

public static boolean update(CandidateRegistration obj,VoterRegistration obj1) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = (Transaction) session.beginTransaction();
            if(obj!=null)
                session.update(obj);
            if(obj1!=null)
                session.update(obj1);
            tx.commit();
        } catch (RuntimeException e) {

            tx.rollback();
            return false;

        }

        return true;

    }
public static boolean updateCandidature(Login login,CandidateRegistration obj,Candidate1 cand) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = (Transaction) session.beginTransaction();
            session.save(login);
            session.update(obj);
            session.save(cand);
            tx.commit();
        } catch (RuntimeException e) {

            tx.rollback();
            throw e;

        }

        return true;

    }
public static boolean update(CandidateRegistration obj) {
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


    
    public static VoterRegistration searchVoterRegistration(String instituteId,String Enrollment) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(VoterRegistration.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.enrollment", Enrollment))
                   .add(Restrictions.eq("id.instituteId", instituteId)));
            return (VoterRegistration) criteria.uniqueResult();


        } finally {
            session.close();
        }
    }

    public static List<CandidateRegLoginDetails> searchCandidate(String Enrollment, String instituteId) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();
            Query query = session.createQuery("select l.*,c1.*,cr.*,vr.*,e.*,p.* from Login l,Candidate1 c1,CandidateRegistration cr,VoterRegistration vr,Election e,Position1 p where l.id.instituteId=c1.id.instituteId and l.staffId = cr.id.enrollment and c1.id.positionId = cr.`position` and c1.id.electionId = cr.id.electionId and c1.id.instituteId = cr.id.instituteId and c1.id.instituteId = vr.id.instituteId and cr.id.enrollment = vr.id.enrollment and cr.institute_id = e.institute_id and cr.id.electionId = e.id.electionId and e.id.electionId = p.id.electionId and c1.id.positionId = p.id.positionId and l.`role` = 'candidate' and c1.id.positionId IN (select distinct c2.`position` from CandidateRegistration c2 where c2.id.enrollment = :enrollment) and cr.id.enrollment <> :enrollment and l.id.institute_id = :institute_id")
                    .setResultTransformer(Transformers.aliasToBean(CandidateRegLoginDetails.class));
            query.setString("enrollment", Enrollment);
            query.setString("institute_id", instituteId);

            return (List<CandidateRegLoginDetails>)query.list();


        } finally {
            session.close();
        }
    }

public static CandidateRegistration searchCandidature(String electionId,String Enrollment, String instituteId) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(CandidateRegistration.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.enrollment", Enrollment))
                   .add(Restrictions.eq("id.electionId", electionId)))
                   .add(Restrictions.eq("id.instituteId", instituteId));
            return (CandidateRegistration) ((criteria.list()!=null && criteria.list().size()==1)?criteria.uniqueResult():null);


        } finally {
            session.close();
        }
    }

      public static CandidateRegistration searchCandidateRegistration(String instituteId,String Enrollment,String position) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(CandidateRegistration.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.enrollment", Enrollment))
                    .add(Restrictions.eq("position", position))
                   .add(Restrictions.eq("id.instituteId", instituteId)));
            return (CandidateRegistration) criteria.uniqueResult();


        } finally {
            session.close();
        }
    }


     public List getCandidateDetailsByStatus(String instituteid,String status){
  Session session =null;
    Transaction tx = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("FROM CandidateRegistration where status = :status and id.instituteId=:instituteId");
             query.setString("status",status );
             query.setString("instituteId",instituteid );

            return query.list();
        }
        finally {
            session.close();
        }
}


     public List<VoterCandidate> GetDetails(String instituteId,String status)
    {
         Session session =null;
    Transaction tx = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            String sql="";

            sql = "select a.*,b.* from  candidate_registration b, voter_registration a where a.enrollment=b.enrollment and a.institute_id=b.institute_id and b.institute_id=:institute_id and b.status1=:status";


            System.out.println(sql);

          Query query =  session.createSQLQuery(sql)
                    .addEntity(VoterRegistration.class)
                    .addEntity(CandidateRegistration.class)

                    .setResultTransformer(Transformers.aliasToBean(VoterCandidate.class));
          query.setString("institute_id",instituteId );
          query.setString("status", "not registered");

          return (List<VoterCandidate>)query.list();
        }
    finally {
            session.close();
        }
}



      public List<VoterCandidate> GetDetails1(String instituteId,String status)
    {
         Session session =null;
    Transaction tx = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            String sql="";
            System.out.println("status="+ status);
            sql = "select a.*,b.* from  candidate_registration b, voter_registration a where a.enrollment=b.enrollment and a.institute_id=b.institute_id and b.institute_id=:institute_id";
            if(status!=null && !status.equalsIgnoreCase("null"))
                {sql += " and b.status1=:status";}

            System.out.println(sql);

          Query query =  session.createSQLQuery(sql)
                    .addEntity(VoterRegistration.class)
                    .addEntity(CandidateRegistration.class)

                    .setResultTransformer(Transformers.aliasToBean(VoterCandidate.class));
          query.setString("institute_id",instituteId );
          if(status!=null && !status.equalsIgnoreCase("null"))
          query.setString("status", status);

          return (List<VoterCandidate>)query.list();
        }
    finally {
            session.close();
        }
}



      public List<CandidateRegistration> getCandidateDetailsByStatus1(String instituteid,String electionId){
  Session session =null;
    Transaction tx = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("FROM CandidateRegistration where status = :status and id.instituteId=:instituteId and id.electionId=:electionId");
             query.setString("status","not registered" );
             query.setString("instituteId",instituteid );
                     query.setString("electionId",electionId );

            return (List<CandidateRegistration>) query.list();
        }
        finally {
            session.close();
        }
}


      public List getEmail(String Enrollment)
      {
       Session session =null;
    Transaction tx = null;

    try {
        session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("FROM VoterRegistration where id.enrollment=:enrollment");
             query.setString("enrollment",Enrollment );


            return query.list();
        }
        finally {
            session.close();
        }

      }

}