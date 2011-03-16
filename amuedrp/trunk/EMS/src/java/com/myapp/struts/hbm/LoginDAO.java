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
        catch (RuntimeException e) {
            if(loginDetails != null)
                tx.rollback();
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
          //  if(bibDetails != null)
                tx.rollback();
            throw e;
        }
        finally {
           //session.close();
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
          //  if(bibDetails != null)
                tx.rollback();
            throw e;
        }
        finally {
           //session.close();
        }
    }
public void delete(String user_id) {
        Session session =null;
    Transaction tx = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            //acqdocentry = session.load(BibliographicDetails.class, id);
            Query query = session.createQuery("DELETE FROM Login  WHERE  id.userId = :userId ");
 
            query.setString("userId",user_id );
            //query.setString("instituteId",institute_id );
            query.executeUpdate();
            tx.commit();
            //return (BibliographicDetails) query.uniqueResult();
        }

        finally {
            //session.close();
        }
    }

public List getLoginDetails(String user_id,String password){
 Session session =null;
    Transaction tx = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("FROM Login where id.userId = :userId and password=:password");
             query.setString("userId",user_id );
             query.setString("password",password);
             System.out.println("user_id="+user_id+ "  Passwoord="+password);
            return query.list();
        }
        finally {
            session.close();
        }
        
}


public List getLoginDetailsbyRole(String role){
 Session session =null;
    Transaction tx = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("FROM Login where role = :role");
             query.setString("role",role );
            // query.setString("password",password.trim() );
            return query.list();
        }
        finally {
            session.close();
        }
}

public List getUser(String user_id){
 Session session =null;
    Transaction tx = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
                session.beginTransaction();
                Query query = session.createQuery("FROM Login where id.userId = :userId");
                query.setString("userId",user_id );
                return query.list();
        }
        finally {
            session.close();
        }
}

}
