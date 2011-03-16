/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.hbm;
import com.myapp.struts.admin.AdminReg_Institute;
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
 * @author Client
 */
public class AdminRegistrationDAO {
 public Integer insert(AdminRegistration adminDetails){
    Session session =null;
    Transaction tx = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(AdminRegistration.class)
                 .setProjection(Projections.max("registrationId"));
            Integer maxNewAdminId = new Integer((Integer)criteria.uniqueResult()==null?0:(Integer)criteria.uniqueResult());
            maxNewAdminId++;
            System.out.println("New Registration Id="+maxNewAdminId);

        
            tx = session.beginTransaction();
            adminDetails.setRegistrationId(maxNewAdminId);
            session.save(adminDetails);
            tx.commit();
            return maxNewAdminId;
        }
        catch (RuntimeException e) {
            if(adminDetails != null)
                tx.rollback();
            throw e;
        }
        finally {
          session.close();
        }
    }
public void update(AdminRegistration adminDetails) {
        Session session =null;
    Transaction tx = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.update(adminDetails);
            tx.commit();
        }
        catch (RuntimeException e) {
          //  if(bibDetails != null)
                tx.rollback();
            throw e;
        }
        finally {
           //session.close();
        }
    }
public void delete(int registration_id) {
       Session session =null;
    Transaction tx = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            //acqdocentry = session.load(BibliographicDetails.class, id);
            Query query = session.createQuery("DELETE FROM AdminRegistration  WHERE  id.registrationId = :registrationId");

            query.setInteger("registrationId",registration_id );

            query.executeUpdate();
            tx.commit();
            //return (BibliographicDetails) query.uniqueResult();
        }

        finally {
            //session.close();
        }
    }

public Integer getAdminRequestCount(String status){
        Session session =null;
    Transaction tx = null;
    try {

        session = HibernateUtil.getSessionFactory().openSession();
        //Transaction tx = null;
        Criteria criteria = session.createCriteria(AdminRegistration.class)
                 .setProjection(Projections.count("registrationId"));
        criteria.add(Restrictions.eq("status",status ));
            Integer countrequest = (Integer)criteria.uniqueResult();

    //Session session = HibernateUtil.getSessionFactory().openSession();

                   
            return countrequest;
        }
        finally {
            session.close();
        }
}
public List getAdminDetailsByStatus(String status){
  Session session =null;
    Transaction tx = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("FROM AdminRegistration where status = :status");
             query.setString("status",status );

            return query.list();
        }
        finally {
            session.close();
        }
}
public List getAdminDetails(){
  Session session =null;
    Transaction tx = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("FROM AdminRegistration");
            return query.list();
        }
        finally {
            session.close();
        }
}
public List getAdminInstituteDetails(){
  Session session =null;
    Transaction tx = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createSQLQuery("select a.*,b.* FROM admin_registration a,institute b where a.registration_id=b.registration_id and b.working_status='Blocked'")
                    .addEntity(AdminRegistration.class)
                    .addEntity(Institute.class)
                    .setResultTransformer(Transformers.aliasToBean(AdminReg_Institute.class));
            return query.list();
        }
        finally {
            session.close();
        }
}

public List getAdminInstituteDetailsById(Integer registerationId){
  Session session =null;
    Transaction tx = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createSQLQuery("select a.*,b.* from admin_registration a left outer join institute b on a.registration_id=b.registration_id where a.registration_id=:registrationId")
                    .addEntity(AdminRegistration.class)
                    .addEntity(Institute.class)
                    .setResultTransformer(Transformers.aliasToBean(AdminReg_Institute.class));
            query.setInteger("registrationId", registerationId);
            System.out.println("Sql Query="+query.getQueryString());
            return query.list();
        }
        finally {
            session.close();
        }
}
public List getAdminInstituteDetailsByStatus(String status){
  Session session =null;
    Transaction tx = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createSQLQuery("select a.*,b.* from admin_registration a left outer join institute b on a.registration_id=b.registration_id where a.status=:status")
                    .addEntity(AdminRegistration.class)
                    .addEntity(Institute.class)
                    .setResultTransformer(Transformers.aliasToBean(AdminReg_Institute.class));
            query.setString("status", status);
            System.out.println("Sql Query="+query.getQueryString());
            return query.list();
        }
        finally {
            session.close();
        }
}

public List getAdminDeatilsById(Integer registrationId){
 Session session =null;
    Transaction tx = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            int i = registrationId.intValue();
            Query query = session.createQuery("FROM AdminRegistration where id.registrationId = :registrationId");
           query.setInteger("registrationId",i);
            return query.list();
        }
        finally {
            session.close();
        }
}

public List getAdminDeatilsByUserId(String UserId){
  Session session =null;
    Transaction tx = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            //int i = registrationId.intValue();
            Query query = session.createQuery("FROM AdminRegistration where userId = :userId");
           query.setString("userId",UserId);
            return query.list();
        }
        finally {
            session.close();
        }
}

public Integer getAdminRequestCount(){
        Session session =null;
    Transaction tx = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
        session = HibernateUtil.getSessionFactory().openSession();
        //Transaction tx = null;
        Criteria criteria = session.createCriteria(AdminRegistration.class)
                 .setProjection(Projections.count("registrationId"));
       // criteria.add(Restrictions.eq("status",status ));
            Integer countrequest = (Integer)criteria.uniqueResult();

    //Session session = HibernateUtil.getSessionFactory().openSession();


            return countrequest;
        }
        finally {
            session.close();
        }
}

public boolean checkDuplicate(String instituteId)
{
return true;
}

}
