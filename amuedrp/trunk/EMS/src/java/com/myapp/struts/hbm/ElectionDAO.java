/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.hbm;

import java.sql.Timestamp;
import java.util.Date;
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
public class ElectionDAO {
 public static void insert(Election obj) {
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
        public static boolean update(Election obj) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.update(obj);
            tx.commit();
        } catch (RuntimeException e) {

            tx.rollback();
            return false;

        }

        return true;

    }
        public static void delete(String electionId,String instituteId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;


        try {
            tx = (Transaction) session.beginTransaction();
            Query query = session.createQuery("Delete From  Election where id.electionId = :electionid2  and id.instituteId= :instituteid");

            query.setString("electionid2", electionId);
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
    public static Election searchElection(String Election_id,String institue_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(Election.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.electionId", Election_id))
                    .add(Restrictions.eq("id.instituteId",institue_id)));
            return (Election) criteria.uniqueResult();


        } finally {
            session.close();
        }
    }
        public static List<VoterRegistration> searchVoter(String institue_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(VoterRegistration.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.instituteId",institue_id)));
            return (List<VoterRegistration>) criteria.list();


        } finally {
            session.close();
        }
    }
    public static Election searchElectionByName(String ElectionName,String institue_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(Election.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("electionName", ElectionName))
                    .add(Restrictions.eq("id.instituteId",institue_id)));
            return (Election) criteria.uniqueResult();


        } finally {
            session.close();
        }
    }
    public static List <ElectionRuleEligiblity1> GetElectionDetails(String institute_id,String Election_id)
    {
    Session session =null;
    Transaction tx = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            String sql="";

            sql = "select a.*,b.*,c.* from election a, electionrule b,eligibility c where a.election_id=b.election_id and a.election_id=c.election_id and a.institute_id=c.institute_id  and  a.institute_id=b.institute_id and a.institute_id=:institute_id and a.election_id=:election_id";


            System.out.println(sql);

          Query query =  session.createSQLQuery(sql)
                    .addEntity(Election.class)
                    .addEntity(Electionrule.class)
                    .addEntity(Eligibility.class)
                    .setResultTransformer(Transformers.aliasToBean(ElectionRuleEligiblity1.class));
          query.setString("institute_id", institute_id);
          query.setString("election_id", Election_id);
            return (List<ElectionRuleEligiblity1>) query.list();
        }
    finally {
            session.close();
        }
}

    public static List ElectionID(String ElectionID,String instituteId)
{
Session session =null;
    Transaction tx = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
                session.beginTransaction();
                Query query = session.createQuery("FROM Election  where id.electionId = :electionId and  id.instituteId=:instituteId");
                query.setString("electionId",ElectionID );
               query.setString("instituteId",instituteId);
                return query.list();
        }
        finally {
            session.close();
        }
}
    public static List<Election> Elections(String instituteId)
{
Session session =null;
    Transaction tx = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
                session.beginTransaction();
                Query query = session.createQuery("FROM Election  where id.instituteId=:instituteId");
                query.setString("instituteId",instituteId);
                return query.list();
        }
        finally {
            session.close();
        }
}

    public static List<Election> getCurrentElections(String instituteId,Date t)
{
Session session =null;
    Transaction tx = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
                session.beginTransaction();
                Query query = session.createQuery("FROM Election  where id.instituteId=:instituteId and nstart<=:systime and nend>=:systime");
                query.setString("instituteId",instituteId);
                query.setDate("systime",t);
                return query.list();
        }
        finally {
            session.close();
        }
}

    public static List <ElectionRuleEligiblity1> GetElectionDetails1(String institute_id,String managerid)
    {
    Session session =null;
    Transaction tx = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            String sql="";

            sql = "select a.*,b.*,c.* from election a, electionrule b,eligibility c where a.election_id=b.election_id and a.election_id=c.election_id and a.institute_id=c.institute_id  and  a.institute_id=b.institute_id and a.institute_id=:institute_id and a.created_by=:created_by";


            System.out.println(sql);

          Query query =  session.createSQLQuery(sql)
                    .addEntity(Election.class)
                    .addEntity(Electionrule.class)
                    .addEntity(Eligibility.class)
                    .setResultTransformer(Transformers.aliasToBean(ElectionRuleEligiblity1.class));
          query.setString("institute_id", institute_id);
          query.setString("created_by", managerid);
            return (List<ElectionRuleEligiblity1>) query.list();
        }
    finally {
            session.close();
        }
}



    public static List <ElectionRuleEligiblity1> GetElectionDetailsbyinstituteId(String institute_id)
    {
    Session session =null;
    Transaction tx = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            String sql="";

            sql = "select a.*,b.*,c.* from election a, electionrule b,eligibility c where a.election_id=b.election_id and a.election_id=c.election_id and a.institute_id=c.institute_id  and  a.institute_id=b.institute_id and a.institute_id=:institute_id";


            System.out.println(sql);

          Query query =  session.createSQLQuery(sql)
                    .addEntity(Election.class)
                    .addEntity(Electionrule.class)
                    .addEntity(Eligibility.class)
                    .setResultTransformer(Transformers.aliasToBean(ElectionRuleEligiblity1.class));
          query.setString("institute_id", institute_id);
         
            return (List<ElectionRuleEligiblity1>) query.list();
        }
    finally {
            session.close();
        }
}



     public List Positions(String instituteId)
{
Session session =null;
    Transaction tx = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
                session.beginTransaction();
                Query query = session.createQuery("FROM Position1  where id.instituteId=:instituteId");
                query.setString("instituteId",instituteId);
                return query.list();
        }
        finally {
            session.close();
        }
}


      public List Position1(String instituteId,String electionId)
{
Session session =null;
    Transaction tx = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
                session.beginTransaction();
                Query query = session.createQuery("FROM Position1  where id.instituteId=:instituteId and id.electionId=:electionId");
                query.setString("instituteId",instituteId);
                query.setString("electionId",electionId);
                return query.list();
        }
        finally {
            session.close();
        }
}


       public Election Electionname(String instituteId,String electionId)
{
Session session =null;
    Transaction tx = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
                session.beginTransaction();
                Query query = session.createQuery("FROM Election  where id.instituteId=:instituteId and id.electionId=:electionId");
                query.setString("instituteId",instituteId);
                query.setString("electionId",electionId);
                return(Election) query.uniqueResult();
        }
        finally {
            session.close();
        }
}

}
