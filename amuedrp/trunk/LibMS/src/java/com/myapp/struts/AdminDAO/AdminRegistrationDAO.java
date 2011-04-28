/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.AdminDAO;
import com.myapp.struts.admin.AdminReg_Institute;
import com.myapp.struts.hbm.*;
import com.myapp.struts.hbm.HibernateUtil;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Transaction;
import org.hibernate.Session;
import org.hibernate.Query;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

/**
 * Developed By : Kedar Kumar
 * Modified By  : 18-Feb-2011
 * Use to Check the Institute Registration Login_ID Duplication
 */
public class AdminRegistrationDAO {


   static  Integer maxNewRegId;
   static Query query;
public static  boolean insert1(AdminRegistration obj)
{
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;


        try
        {
            tx = (Transaction) session.beginTransaction();

            session.save(obj);
            tx.commit();



        }
        catch (Exception ex)
        {
             return false;

       //  System.out.println(ex.toString());

        }
        finally
        {
          //session.close();
        }
   return true;

}

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
            Query query = session.createSQLQuery("select a.*,b.* FROM admin_registration a,library b where a.registration_id=b.registration_id and b.working_status='Blocked'")
                    .addEntity(AdminRegistration.class)
                    .addEntity(Library.class)
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
            Query query = session.createSQLQuery("select a.*,b.* from admin_registration a left outer join library b on a.registration_id=b.registration_id where a.registration_id=:registrationId")
                    .addEntity(AdminRegistration.class)
                    .addEntity(Library.class)
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
            Query query = session.createSQLQuery("select a.*,b.* from admin_registration a left outer join library b on a.registration_id=b.registration_id where a.status=:status")
                    .addEntity(AdminRegistration.class)
                    .addEntity(Library.class)
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
            Query query = session.createQuery("FROM AdminRegistration where loginId = :userId");
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
       
        Criteria criteria = session.createCriteria(AdminRegistration.class)
                 .setProjection(Projections.count("registrationId"));
   
            Integer countrequest = (Integer)criteria.uniqueResult();

   


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


    public static Integer maxRegistrationID()
    {

        
        try
          {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = null;
            Criteria criteria = session.createCriteria(AdminRegistration.class).setProjection(Projections.max("registrationId"));
            maxNewRegId = (Integer)criteria.uniqueResult();
            if(maxNewRegId==null)
                maxNewRegId=1;
            else
                maxNewRegId++;
            
    
        }
        catch(Exception e)
        {

             System.out.println(e.toString());
        }
         return maxNewRegId;
    }


  
public static  AdminRegistration searchLoginID(String login_id)
{
        Session session = HibernateUtil.getSessionFactory().openSession();

        try
        {
            session.beginTransaction();
            query = session.createQuery("FROM  AdminRegistration  WHERE loginId =:login_id  ");
            query.setString("login_id", login_id);
           
            
        }
        catch(Exception e)
        {
        System.out.println(e.toString());
        }
        finally
        {
          //  session.close();
        }
        return (AdminRegistration) query.uniqueResult();

}

public static  AdminRegistration searchInstituteAdmin(String staff_id,String library_id)
{
        Session session = HibernateUtil.getSessionFactory().openSession();

        try
        {
            session.beginTransaction();
            query = session.createQuery("FROM  AdminRegistration  WHERE staffId =:staff_id and libraryId=:library_id");
            query.setString("staff_id", staff_id);
            query.setString("library_id", library_id);


        }
        catch(Exception e)
        {
        System.out.println(e.toString());
        }
        finally
        {
          //  session.close();
        }
        return (AdminRegistration) query.uniqueResult();

}
public static  AdminRegistration searchInstituteAdmin(String login_id)
{
        Session session = HibernateUtil.getSessionFactory().openSession();

        try
        {
            session.beginTransaction();
            query = session.createQuery("FROM  AdminRegistration  WHERE loginId =:login_id");
            query.setString("login_id", login_id);


        }
        catch(Exception e)
        {
        System.out.println(e.toString());
        }
        finally
        {
          //  session.close();
        }
        return (AdminRegistration) query.uniqueResult();

}
public static  boolean update1(AdminRegistration obj)
{
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;


        try
        {
            tx = (Transaction) session.beginTransaction();

            session.update(obj);
            tx.commit();



        }
        catch (Exception ex)
        {
         System.out.println(ex.toString());
             return false;



        }
        finally
        {
          //session.close();
        }
   return true;

}
}
