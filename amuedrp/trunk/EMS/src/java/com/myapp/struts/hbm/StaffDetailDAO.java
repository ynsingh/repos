/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.hbm;

import java.util.List;
import org.hibernate.*;

/**
 *
 * @author Edrp-04
 */
public class StaffDetailDAO {
public static StaffDetail searchStaffId(String staff_id,String institute_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        StaffDetail tx=null;
        try {
            session.beginTransaction();
            Query query = session.createQuery("FROM  StaffDetail  WHERE institute_id =:institute_id and  enrollment=:staff_id ");
            query.setString("institute_id", institute_id);

            query.setString("staff_id", staff_id);
            tx= ( StaffDetail) query.uniqueResult();
            session.getTransaction().commit();
        }
        catch(RuntimeException e){
        e.printStackTrace();
        }
        finally {
            session.close();
        }
return tx;
}
     public void insert(StaffDetail staffDetails){
    Session session =null;
    Transaction tx = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.save(staffDetails);
            tx.commit();
        }
        catch (RuntimeException e) {
            if(staffDetails != null)
                tx.rollback();
            e.printStackTrace();
            throw e;
        }
        finally {
          session.close();
        }
    }
public void update(StaffDetail staffDetails) {
        Session session =null;
    Transaction tx = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.update(staffDetails);
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
public void delete(int user_id,String institute_id) {
        Session session =null;
    Transaction tx = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
           
            Query query = session.createQuery("DELETE FROM StaffDetail  WHERE  id.staff = :userId and id.institute_id=:instituteId");
            query.setInteger("userId",user_id );
            query.setString("instituteId",institute_id );
            query.executeUpdate();
            tx.commit();
         
        }
catch(RuntimeException e){
e.printStackTrace();
}
        finally {
            session.close();
        }
    }

public List getStaffDetails(){
  Session session =null;
    List tx = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("FROM StaffDetail");
             
           tx= query.list();
           session.getTransaction().commit();
        }
    catch(RuntimeException e){
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
            Query query = session.createQuery("FROM StaffDetail where id.staffId=:staffId and id.instituteId=:instituteId");
             query.setString("staffId", staffId);
             query.setString("instituteId", instituteId);
            tx= query.list();
            session.getTransaction().commit();
        }
    catch(RuntimeException e){
e.printStackTrace();
}
        finally {
            session.close();
        }
        return tx;
}
// code on 11 March 2014
public static StaffDetail getStaffDetails2(String uid,String instituteId){
 Session session =null;
    StaffDetail tx = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("FROM StaffDetail where emailId=:emailId and id.instituteId=:instituteId");
             query.setString("emailId", uid);
             query.setString("instituteId", instituteId);
           tx= (StaffDetail)query.uniqueResult();
           session.getTransaction().commit();
        }

    catch(RuntimeException e){
e.printStackTrace();
}
        finally {
            session.close();
        }
        return tx;
}
// code on 11 March 2014
public StaffDetail getStaffDetails1(String staffId,String instituteId){
 Session session =null;
    StaffDetail tx = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("FROM StaffDetail where id.staffId=:staffId and id.instituteId=:instituteId");
             query.setString("staffId", staffId);
             query.setString("instituteId", instituteId);
           tx= (StaffDetail)query.uniqueResult();
           session.getTransaction().commit();
        }

    catch(RuntimeException e){
e.printStackTrace();
}
        finally {
            session.close();
        }
        return tx;
}

}
