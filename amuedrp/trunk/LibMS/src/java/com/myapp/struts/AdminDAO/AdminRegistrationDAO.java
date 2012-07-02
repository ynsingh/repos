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
            tx.rollback();
              System.out.println(ex.toString());
             return false;

       

        }
        finally
        {
          session.close();
        }
   return true;

}

 public Integer insert(AdminRegistration adminDetails){
    Session session =null;
    Transaction tx = null;
    Integer maxNewAdminId=null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
          tx = session.beginTransaction();
        Criteria criteria = session.createCriteria(AdminRegistration.class)
                 .setProjection(Projections.max("registrationId"));
             maxNewAdminId= new Integer((Integer)criteria.uniqueResult()==null?0:(Integer)criteria.uniqueResult());
            maxNewAdminId++;
            System.out.println("New Registration Id="+maxNewAdminId);


          
            adminDetails.setRegistrationId(maxNewAdminId);
            session.save(adminDetails);
            tx.commit();

            
        }
        catch (Exception e) {
            
                tx.rollback();
                System.out.println(e);
            
        }
        finally {
          session.close();
        }
        return maxNewAdminId;
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
        catch (Exception e) {
          
                tx.rollback();
            e.printStackTrace();
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
            
            Query query = session.createQuery("DELETE FROM AdminRegistration  WHERE  id.registrationId = :registrationId");

            query.setInteger("registrationId",registration_id );

            query.executeUpdate();
            tx.commit();
          
        }
catch (Exception e) {

                tx.rollback();
            e.printStackTrace();
        }
        
        finally {
            session.close();
        }
    }

public Integer getAdminRequestCount(String status){
        Session session =null;
    Transaction tx = null;
    Integer countrequest=null;
    try {

        session = HibernateUtil.getSessionFactory().openSession();
      session.beginTransaction();
        Criteria criteria = session.createCriteria(AdminRegistration.class)
                 .setProjection(Projections.count("registrationId"));
        criteria.add(Restrictions.eq("status",status ));
           countrequest  = (Integer)criteria.uniqueResult();

   
session.getTransaction().commit();

        
        }
    catch (Exception e) {
                if(tx!=null)
                tx.rollback();
            e.printStackTrace();
        }

        finally {
           session.close();
        }
            return countrequest;
}

public List getAdminDetailsByStatus(String status){
  Session session =null;
    Transaction tx = null;
    List obj=null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            query = session.createQuery("FROM AdminRegistration where status = :status");
             query.setString("status",status );
obj=query.list();
       session.getTransaction().commit();
        }
      catch (Exception e) {

                tx.rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
        }
         return obj;
}
public List getAdminDetails(){
  Session session =null;
    Transaction tx = null;
    List obj=null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            query = session.createQuery("FROM AdminRegistration");
        obj=    query.list();
        session.getTransaction().commit();
        }
    catch (Exception e) {

                tx.rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        return obj;
}
public List getAdminInstituteDetails(){
  Session session =null;
    Transaction tx = null;
    List obj=null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
             query = session.createSQLQuery("select a.*,b.* FROM admin_registration a,library b where a.registration_id=b.registration_id and b.working_status='Blocked'")
                    .addEntity(AdminRegistration.class)
                    .addEntity(Library.class)
                    .setResultTransformer(Transformers.aliasToBean(AdminReg_Institute.class));
           obj=query.list();
           session.getTransaction().commit();
        }
    catch (Exception e) {

                tx.rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
        }
         return obj;
}

public List getAdminInstituteDetailsById(Integer registerationId){
  Session session =null;
    Transaction tx = null;
    List obj=null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            query = session.createSQLQuery("select a.*,b.* from admin_registration a left outer join library b on a.registration_id=b.registration_id where a.registration_id=:registrationId")
                    .addEntity(AdminRegistration.class)
                    .addEntity(Library.class)
                    .setResultTransformer(Transformers.aliasToBean(AdminReg_Institute.class));
            query.setInteger("registrationId", registerationId);
            obj= query.list();
            session.getTransaction().commit();
         
        }
    catch (Exception e) {

                tx.rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
        }
           return obj;
}
public List getAdminInstituteDetailsByStatus(String status){
  Session session =null;
    Transaction tx = null;
    List list=null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createSQLQuery("select a.*,b.* from admin_registration a left outer join library b on a.registration_id=b.registration_id where a.status=:status")
                    .addEntity(AdminRegistration.class)
                    .addEntity(Library.class)
                    .setResultTransformer(Transformers.aliasToBean(AdminReg_Institute.class));
            query.setString("status", status);
            System.out.println("Sql Query="+query.getQueryString());
           list=query.list();
           session.getTransaction().commit();
        }
    catch (Exception e) {

                tx.rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
        }
         return list;
}

public List getAdminDeatilsById(Integer registrationId){
 Session session =null;
    Transaction tx = null;
    List list=null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            int i = registrationId.intValue();
            Query query = session.createQuery("FROM AdminRegistration where id.registrationId = :registrationId");
           query.setInteger("registrationId",i);
          list= query.list();
          session.getTransaction().commit();
        }
      catch (Exception e) {

                tx.rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
        }
         return list;
}

public List getAdminDeatilsByUserId(String UserId){
  Session session =null;
    Transaction tx = null;
    List list=null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            //int i = registrationId.intValue();
            Query query = session.createQuery("FROM AdminRegistration where loginId = :userId");
           query.setString("userId",UserId);
           list=query.list();
           session.getTransaction().commit();
        }
      catch (Exception e) {

                tx.rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
        }
         return list;
}

public Integer getAdminRequestCount(){
        Session session =null;

    Integer countrequest=null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
     session.beginTransaction();
       
        Criteria criteria = session.createCriteria(AdminRegistration.class)
                 .setProjection(Projections.count("registrationId"));
   
           countrequest  = (Integer)criteria.uniqueResult();

   
session.getTransaction().commit();

           
        }
    catch (Exception e) {


            e.printStackTrace();
        }
        finally {
            session.close();
        }
         return countrequest;
}

public boolean checkDuplicate(String instituteId)
{
return true;
}


    public static Integer maxRegistrationID()
    {
         Session session = HibernateUtil.getSessionFactory().openSession();
        
        try
          {
          
            session.beginTransaction();
            Criteria criteria = session.createCriteria(AdminRegistration.class).setProjection(Projections.max("registrationId"));
            maxNewRegId = (Integer)criteria.uniqueResult();
            if(maxNewRegId==null)
                maxNewRegId=1;
            else
                maxNewRegId++;
            session.getTransaction().commit();
    
        }
        catch(Exception e)
        {

             System.out.println(e.toString());
        }

        finally{
        session.close();
        }
         return maxNewRegId;
    }


  
public static  AdminRegistration searchLoginID(String login_id)
{
        Session session = HibernateUtil.getSessionFactory().openSession();
AdminRegistration obj=null;
        try
        {
            session.beginTransaction();
            query = session.createQuery("FROM  AdminRegistration  WHERE loginId =:login_id  ");
            query.setString("login_id", login_id);
           
            obj=(AdminRegistration) query.uniqueResult();
            session.getTransaction().commit();
        }
        catch(Exception e)
        {
        System.out.println(e.toString());
        }
        finally
        {
            session.close();
        }
        return obj;

}
public static  AdminRegistration searchInstitute(String library_id)
{
        Session session = HibernateUtil.getSessionFactory().openSession();
AdminRegistration obj=null;
        try
        {
            session.beginTransaction();
            query = session.createQuery("FROM  AdminRegistration  WHERE  libraryId=:library_id");

            query.setString("library_id", library_id);
        obj=(AdminRegistration) query.uniqueResult();
        session.getTransaction().commit();

        }
        catch(Exception e)
        {
        System.out.println(e.toString());
        }
        finally
        {
            session.close();
        }
        return obj;

}
public static  AdminRegistration searchInstituteAdmin(String staff_id,String library_id)
{
        Session session = HibernateUtil.getSessionFactory().openSession();
AdminRegistration obj=null;
        try
        {
            session.beginTransaction();
            query = session.createQuery("FROM  AdminRegistration  WHERE staffId =:staff_id and libraryId=:library_id");
            query.setString("staff_id", staff_id);
            query.setString("library_id", library_id);
obj=(AdminRegistration) query.uniqueResult();
session.getTransaction().commit();

        }
        catch(Exception e)
        {
        System.out.println(e.toString());
        }
        finally
        {
            session.close();
        }
        return obj;

}
public static  AdminRegistration searchInstituteAdmin(String login_id)
{
        Session session = HibernateUtil.getSessionFactory().openSession();
AdminRegistration obj=null;
        try
        {
            session.beginTransaction();
            query = session.createQuery("FROM  AdminRegistration  WHERE loginId =:login_id");
            query.setString("login_id", login_id);
            obj=(AdminRegistration) query.uniqueResult();
            session.getTransaction().commit();

        }
        catch(Exception e)
        {
        System.out.println(e.toString());
        }
        finally
        {
            session.close();
        }
        return obj;

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
            tx.rollback();
         System.out.println(ex.toString());
             return false;



        }
        finally
        {
          session.close();
        }
   return true;

}
}
