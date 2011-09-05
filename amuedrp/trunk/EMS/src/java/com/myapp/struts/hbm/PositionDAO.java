/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.hbm;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

/**
 *
 * @author Edrp-04
 */
public class PositionDAO {

     public void insert(Position1 position){
    Session session =null;
    Transaction tx = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
        tx = session.beginTransaction();
        Criteria criteria = session.createCriteria(Position1.class)
                .setProjection(Projections.max("id.positionId"));
                 
            Integer maxPositionId = new Integer((Integer)criteria.uniqueResult()==null?0:(Integer)criteria.uniqueResult());
            maxPositionId++;
            System.out.println("Max Position Id="+maxPositionId);
            position.getId().setPositionId(maxPositionId);
        
            session.save(position);
            tx.commit();
        }
        catch (RuntimeException e) {
            if(position != null)
                tx.rollback();
            throw e;
        }
        finally {
          
        }
    }
     public void updatePosition(Position1 position) {
        Session session =null;
    Transaction tx = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.update(position);
            tx.commit();
        }
        catch (RuntimeException e) {
          //  if(bibDetails != null)
                tx.rollback();
            throw e;
        }
        finally {
          // session.close();
        }
    }
     public void deletePosition(Position1 position) {
        Session session =null;
    Transaction tx = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.delete(position);
            tx.commit();
        }
        catch (RuntimeException e) {
          //  if(bibDetails != null)
                tx.rollback();
            throw e;
        }
        finally {
          // session.close();
        }
    }
      public void insertcandidate(Candidate1 candidate){
    Session session =null;
    Transaction tx = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();



            Criteria criteria = session.createCriteria(Candidate1.class);
            Criterion a = Restrictions.eq("id.positionId", candidate.getId().getPositionId());
            Criterion b = Restrictions.eq("id.electionId", candidate.getId().getElectionId());
            Criterion c = Restrictions.eq("id.instituteId", candidate.getId().getInstituteId());
            LogicalExpression le =Restrictions.and(a, b);
            LogicalExpression le1 = Restrictions.and(le, c);
            Integer maxPositionId = new Integer((Integer)criteria.add(le1).setProjection(Projections.max("id.candidateId")).uniqueResult()==null?0:(Integer)criteria.add(le1).setProjection(Projections.max("id.candidateId")).uniqueResult());
            maxPositionId++;
            System.out.println("Max Candidate Id="+maxPositionId);
            candidate.getId().setCandidateId(maxPositionId);

            session.save(candidate);
            tx.commit();
        }
        catch (RuntimeException e) {
            if(candidate != null)
                tx.rollback();
            throw e;
        }
        finally {
          //session.close();
        }
    }
public void updateCandidate(Candidate1 candiadte) {
        Session session =null;
    Transaction tx = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.update(candiadte);
            tx.commit();
        }
        catch (RuntimeException e) {
          //  if(bibDetails != null)
                tx.rollback();
            throw e;
        }
        finally {
          // session.close();
        }
    }

public Position1 getPositionByName(String positionName,String electionId, String instituteId) {
        Session session =null;
    Transaction tx = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            Query query = session.createQuery("FROM Position1 where id.electionId = :electionId and id.instituteId=:instituteId and positionName=:positionName");
             query.setString("electionId",electionId );
             query.setString("instituteId", instituteId);
             query.setString("positionName", positionName);
            return (Position1)query.uniqueResult();
        }
        catch (RuntimeException e) {
          //  if(bibDetails != null)


        }
        return null;
    }
public List<PositionWithCandidature> getPositionWithCandidature(String electionId, String instituteId) {
        Session session =null;
    Transaction tx = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            Query query = session.createSQLQuery("select p.position_id,p.position_name,(select count(*) from candidate_registration cr where cr.`position`=p.position_id and cr.institute_id=p.institute_id and cr.status1='REGISTERED') candidature from position1 p where p.election_id = :electionId and p.institute_id=:instituteId");
             query.setString("electionId",electionId );
             query.setString("instituteId", instituteId);
             query.setResultTransformer(Transformers.aliasToBean(PositionWithCandidature.class));
             System.out.println(query.getQueryString()+" electionId="+electionId+" instituteId="+instituteId);
            return (List<PositionWithCandidature>)query.list();
        }
        catch (RuntimeException e) {
          //  if(bibDetails != null)
System.out.println(e);

        }
    finally{
        session=null;
    }
        return null;
    }
public List<Position1> getPosition(String electionId, String instituteId) {
        Session session =null;
    Transaction tx = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            Query query = session.createQuery("FROM Position1 where id.electionId = :electionId and id.instituteId=:instituteId");
             query.setString("electionId",electionId );
             query.setString("instituteId", instituteId);
            return (List<Position1>)query.list();
        }
        catch (RuntimeException e) {
          //  if(bibDetails != null)

            
        }
    finally{
        session=null;
    }
        return null;
    }
public List<Candidate1> getCandidate(int positionId,String electionId, String instituteId) {
        Session session =null;
    Transaction tx = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            Query query = session.createQuery("FROM Candidate1 where id.positionId=:positionId and id.electionId = :electionId and id.instituteId=:instituteId");
            query.setInteger("positionId", positionId);
            query.setString("electionId",electionId );
             query.setString("instituteId", instituteId);
            return (List<Candidate1>)query.list();
        }
        catch (RuntimeException e) {
          //  if(bibDetails != null)


        }
        return null;
    }
public Candidate1 getCandidateDetailByName(String candidateName,int positionId,String electionId, String instituteId) {
        Session session =null;
    Transaction tx = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            Query query = session.createQuery("FROM Candidate1 where id.positionId=:positionId and id.electionId = :electionId and id.instituteId=:instituteId and candidateName=:candidateId");
            query.setInteger("positionId", positionId);
            query.setString("electionId",electionId );
             query.setString("instituteId", instituteId);
              query.setString("candidateId", candidateName);
            return (Candidate1)query.uniqueResult();
        }
        catch (RuntimeException e) {
          //  if(bibDetails != null)


        }
        return null;
    }
public Candidate1 getCandidateByName(String candidateId,int positionId,String electionId, String instituteId) {
        Session session =null;
    Transaction tx = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            Query query = session.createQuery("FROM Candidate1 where id.positionId=:positionId and id.electionId = :electionId and id.instituteId=:instituteId and id.candidateId=:candidateId");
            query.setInteger("positionId", positionId);
            query.setString("electionId",electionId );
             query.setString("instituteId", instituteId);
              query.setString("candidateId", candidateId);
            return (Candidate1)query.uniqueResult();
        }
        catch (RuntimeException e) {
          //  if(bibDetails != null)


        }
        return null;
    }
public Candidate1 checkCandidateByName(String candidateName,String candidateId,int positionId,String electionId, String instituteId) {
        Session session =null;
    Transaction tx = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            Query query = session.createQuery("FROM Candidate1 where id.positionId=:positionId and id.electionId = :electionId and id.instituteId=:instituteId and candidateName=:candidateName and id.candidateId!=:candidateId");
            query.setInteger("positionId", positionId);
            query.setString("electionId",electionId );
             query.setString("instituteId", instituteId);
              query.setString("candidateName", candidateName);
              query.setString("candidateId", candidateId);
            return (Candidate1)query.uniqueResult();
        }
        catch (RuntimeException e) {
          //  if(bibDetails != null)


        }
        return null;
    }

 public  Position1 searchPosition(Integer PositionID) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(Position1.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.positionId", PositionID)));
            return (Position1) criteria.uniqueResult();


        } finally {
            session.close();
        }
    }


  public  Position1 searchPosition1(Integer PositionID,String electionid,String instituteid) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(Position1.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.positionId", PositionID)))
                    .add(Restrictions.eq("id.electionId", electionid))
                    .add(Restrictions.eq("id.instituteId",instituteid));
            return (Position1) criteria.uniqueResult();


        } finally {
            session.close();
        }
    }


   public  List<Candidate1> ElectionId(Integer PositionID,String instituteid) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(Candidate1.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.positionId", PositionID)))
                    .add(Restrictions.eq("id.instituteId", instituteid));
            return (List<Candidate1>) criteria.list();


        } finally {
            session.close();
        }
    }


}
