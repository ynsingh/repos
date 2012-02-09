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


    public List getAdminDeatilsByInstituteName(String instituteName){
  Session session =null;
    List obj = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            //int i = registrationId.intValue();
            Query query = session.createQuery("FROM AdminRegistration where instituteName = :instituteName");
           query.setString("instituteName",instituteName);
            obj= query.list();
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

    public Login getLoginUser(String user_id){
  Session session =null;
    Transaction tx = null;
    Login obj=null;

        session= HibernateUtil.getSessionFactory().openSession();
        try{
        session.beginTransaction();
         Query query = session.createQuery("FROM Login where userId = :userId");
             query.setString("userId",user_id );



            obj=(Login)query.uniqueResult();
   session.getTransaction().commit();
        }
        catch(Exception e){

        e.printStackTrace();

        }
        finally{
        session.close();
        }
        return obj;

}

 public Integer insert(AdminRegistration adminDetails){
    Session session =null;
    Transaction tx = null;
    Integer obj =null;
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
            obj= maxNewAdminId;
            
        }
        catch (RuntimeException e) {
            if(adminDetails != null)
                tx.rollback();
            e.printStackTrace();
            throw e;
        }
        finally {
          session.close();
        }
        return obj;
    }
 public void migrate_election_manager(ElectionManagerMigrate adminDetails) {
        Session session =null;
    Transaction tx = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.save(adminDetails);
            tx.commit();
        }
        catch (RuntimeException e) {
          //  if(bibDetails != null)
            e.printStackTrace();
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
            e.printStackTrace();
                tx.rollback();
            throw e;
        }
        finally {
           session.close();
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
        catch(RuntimeException d){
        d.printStackTrace();
        }
        finally {
            session.close();
        }
    }

public Integer getAdminRequestCount(String status){
        Session session =null;
    Integer obj=null;
    try {

        session = HibernateUtil.getSessionFactory().openSession();
       session.beginTransaction();
        Criteria criteria = session.createCriteria(AdminRegistration.class)
                 .setProjection(Projections.count("registrationId"));
        criteria.add(Restrictions.eq("status",status ));
            Integer countrequest = (Integer)criteria.uniqueResult();

    //Session session = HibernateUtil.getSessionFactory().openSession();

                   
           obj= countrequest;
           session.getTransaction().commit();
        }
    catch(RuntimeException d){
        d.printStackTrace();
        }
        finally {
            session.close();
        }
        return obj;
}
public List getAdminDetailsByStatus(String status){
  Session session =null;
    List obj=null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("FROM AdminRegistration where status = :status");
             query.setString("status",status );

            obj= query.list();
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
public List getAdminDetails(){
  Session session =null;
    List obj=null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("FROM AdminRegistration");
            obj= query.list();
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
public List getAdminInstituteDetails(){
  Session session =null;
    List obj=null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createSQLQuery("select a.*,b.* FROM admin_registration a,institute b where a.registration_id=b.registration_id and b.working_status='Blocked'")
                    .addEntity(AdminRegistration.class)
                    .addEntity(Institute.class)
                    .setResultTransformer(Transformers.aliasToBean(AdminReg_Institute.class));
            obj= query.list();
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

public List getAdminInstituteDetailsById(Integer registerationId){
  Session session =null;
    List obj=null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createSQLQuery("select a.*,b.* from admin_registration a left outer join institute b on a.registration_id=b.registration_id where a.registration_id=:registrationId")
                    .addEntity(AdminRegistration.class)
                    .addEntity(Institute.class)
                    .setResultTransformer(Transformers.aliasToBean(AdminReg_Institute.class));
            query.setInteger("registrationId", registerationId);
            System.out.println("Sql Query="+query.getQueryString());
            obj= query.list();
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

public List<AdminReg_Institute> getAdminInstituteDetailsById(String registerationId){
  Session session =null;
    List<AdminReg_Institute> obj=null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createSQLQuery("select a.*,b.* from admin_registration a left outer join institute b on a.registration_id=b.registration_id where b.institute_id=:instituteId")
                    .addEntity(AdminRegistration.class)
                    .addEntity(Institute.class)
                    .setResultTransformer(Transformers.aliasToBean(AdminReg_Institute.class));
            if(registerationId!=null && !registerationId.equals(""))
                query.setString("instituteId", registerationId);
            else
                query.setString("instituteId", "amu");
            System.out.println("Sql Query="+query.getQueryString());
            obj= (List<AdminReg_Institute>)query.list();
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

public List getAdminInstituteDetailsByStatus(String status){
  Session session =null;
   List obj=null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createSQLQuery("select a.*,b.* from admin_registration a left outer join institute b on a.registration_id=b.registration_id where a.status=:status")
                    .addEntity(AdminRegistration.class)
                    .addEntity(Institute.class)
                    .setResultTransformer(Transformers.aliasToBean(AdminReg_Institute.class));
            query.setString("status", status);
            System.out.println("Sql Query="+query.getQueryString());
            obj= query.list();
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

public List getAdminDeatilsById(Integer registrationId){
 Session session =null;
    List obj=null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            int i = registrationId.intValue();
            Query query = session.createQuery("FROM AdminRegistration where id.registrationId = :registrationId");
           query.setInteger("registrationId",i);
            obj= query.list();
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
public AdminRegistration getAdminDeatilsByUserId1(String UserId){
  Session session =null;
    AdminRegistration obj = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            //int i = registrationId.intValue();
            Query query = session.createQuery("FROM AdminRegistration where userId = :userId");
           query.setString("userId",UserId);
            obj= (AdminRegistration)query.uniqueResult();
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
public List getAdminDeatilsByUserId(String UserId){
  Session session =null;
    List obj = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            //int i = registrationId.intValue();
            Query query = session.createQuery("FROM AdminRegistration where userId = :userId");
           query.setString("userId",UserId);
            obj= query.list();
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
 public static AdminRegistration searchVoterRegistration(String instituteid,String Enrollment) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        AdminRegistration obj=null;
        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(AdminRegistration.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("enrollment", Enrollment))
                    .add(Restrictions.eq("instituteId", instituteid)));

            obj= (AdminRegistration) criteria.uniqueResult();
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
 public static List<Election> searchElectionDetails(String instituteid,String user_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Election> obj=null;
        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(Election.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("createdBy", user_id))
                    .add(Restrictions.eq("id.instituteId", instituteid)));

            obj= (List<Election>) criteria.list();
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

public Integer getAdminRequestCount(){
        Session session =null;
    Integer obj = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
       session.beginTransaction();
        //Transaction tx = null;
        Criteria criteria = session.createCriteria(AdminRegistration.class)
                 .setProjection(Projections.count("registrationId"));
       // criteria.add(Restrictions.eq("status",status ));
            Integer countrequest = (Integer)criteria.uniqueResult();

    //Session session = HibernateUtil.getSessionFactory().openSession();


            obj= countrequest;
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

public boolean checkDuplicate(String instituteId)
{
return true;
}

}
