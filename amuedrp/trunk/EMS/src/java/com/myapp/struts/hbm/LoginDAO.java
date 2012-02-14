/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.hbm;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Query;

/**
 *
 * @author Client
 */
public class LoginDAO {
     public void insert(Login loginDetails,String userId){
    Session session =null;
    Transaction tx = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.save(loginDetails);
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

    public Login getUserId(String user_id){
 Session session =null;
 Login x=new Login();
   
    try {
        session= HibernateUtil.getSessionFactory().openSession();
                session.beginTransaction();
                Query query = session.createQuery("FROM Login where id.userId = :userId");
                query.setString("userId",user_id );
               x=(Login)query.uniqueResult();
               session.getTransaction().commit();
        }
    catch(Exception e){
    e.printStackTrace();
    
    
    }
        finally {
            session.close();
        }
        return x;
}
    public Login getUserDetails(String userId,String instituteId){
 Session session =null;
   Login obj=null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("FROM Login where id.userId=:user_id and institute_id=:instituteId");
             query.setString("user_id", userId);
             query.setString("instituteId", instituteId);
           obj= (Login)query.uniqueResult();
           session.getTransaction().commit();
        }
     catch(Exception e){
    e.printStackTrace();


    }
        finally {
            session.close();
        }
        return obj;
}
 public void insert(Login loginDetails){
    Session session =null;
    Transaction tx = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.save(loginDetails);
            tx.commit();
        }
        catch (RuntimeException e) {
            if(loginDetails != null)
                tx.rollback();
            e.printStackTrace();
            throw e;
        }
        finally {
          session.close();
        }
    }
public void update(Login loginDetails) {
    Session session =null;
    Transaction tx = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
        

        
            tx = session.beginTransaction();
            session.update(loginDetails);
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
public void updateByStaffId(Login loginDetails) {
        Session session =null;
    Transaction tx = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.saveOrUpdate(loginDetails);
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
public void delete(String user_id) {
        Session session =null;
    Transaction tx = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            
            Query query = session.createQuery("DELETE FROM Login  WHERE  id.userId = :userId ");
 
            query.setString("userId",user_id );
          
            query.executeUpdate();
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

public List getLoginDetails(String user_id,String password){
 Session session =null;
    List tx = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("FROM Login where id.userId = :userId and password=:password");
             query.setString("userId",user_id );
             query.setString("password",password);
             System.out.println("user_id="+user_id+ "  Passwoord="+password);
            tx= query.list();
            session.getTransaction().commit();
        }
    catch (RuntimeException e) {
          
            e.printStackTrace();
               
        }
        finally {
            session.close();
        }
        return tx;
}
public Login getbyStaffId(String staff_id){
 Session session =null;
    Login tx = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("FROM Login where staffDetail.staffId = :staffId");
             query.setString("staffId",staff_id );
           tx= (Login) query.uniqueResult();
           session.getTransaction().commit();
        }
    catch (RuntimeException e) {

            e.printStackTrace();

        }
        finally {
            session.close();
        }
        return tx;
}

public List getLoginDetailsbyRole(String role){
 Session session =null;
    List tx = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("FROM Login where role = :role");
             query.setString("role",role );
          
            tx= query.list();
            session.getTransaction().commit();
        }
    catch (RuntimeException e) {

            e.printStackTrace();

        }
        finally {
            session.close();
        }
        return tx;
}

public List getUser(String user_id){
 Session session =null;
    List tx = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
                session.beginTransaction();
                Query query = session.createQuery("FROM Login where id.userId = :userId");
                query.setString("userId",user_id );
                tx= query.list();
                session.getTransaction().commit();
        }
    catch (RuntimeException e) {

            e.printStackTrace();

        }
        finally {
            session.close();
        }
        return tx;
}


public List getStaffDetails(String staffId,String instituteId){
 Session session =null;
    List tx = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("FROM Login where staff_id=:staffId and institute_id=:instituteId");
             query.setString("staffId", staffId);
             query.setString("instituteId", instituteId);
         tx= query.list();
         session.getTransaction().commit();
        }
    catch (RuntimeException e) {

            e.printStackTrace();

        }
        finally {
            session.close();
        }
        return tx;
}
public Login getStaffDetails1(String staffId,String instituteId){
 Session session =null;
    Login tx = null;
    try {
        System.out.println(staffId+"   "+instituteId);
        session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("FROM Login where staff_id=:staffId and institute_id=:instituteId");
             query.setString("staffId", staffId);
             query.setString("instituteId", instituteId);
            tx= (Login)query.uniqueResult();
            System.out.println(staffId+"   "+instituteId+tx);
            session.getTransaction().commit();
        } 
    catch (RuntimeException e) {

            e.printStackTrace();

        }

        finally {
            session.close();
        }
        return tx;
}
public static Login searchForgetPassword(String login_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
Login obj=null;
        try {
            session.beginTransaction();
            Query query = session.createQuery("FROM  Login  WHERE userId =:user_id and question!=:ques");
            query.setString("user_id", login_id);
            query.setString("ques", "@");

            obj= ( Login) query.uniqueResult();
            session.getTransaction().commit();
        }

         catch (RuntimeException e) {

            e.printStackTrace();

        }
        finally {
            session.close();
        }
        return obj;

}
}
