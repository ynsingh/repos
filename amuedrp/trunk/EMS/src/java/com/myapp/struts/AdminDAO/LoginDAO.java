/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.AdminDAO;
import com.myapp.struts.hbm.*;
import com.myapp.struts.hbm.HibernateUtil;
import org.hibernate.Transaction;
import org.hibernate.Session;
import org.hibernate.Query;
import java.util.*;

/**
 * Developed By : Kedar Kumar
 * Modified By  : 18-Feb-2011
 * Use to Check the Admin Registration Login_ID in Login Table Duplication
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
          e.printStackTrace();
                tx.rollback();
            throw e;
        }
        finally {
           session.close();
        }
    }

public boolean updateadmin(Login loginDetails) {
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
                return false;


        }
        finally {
           session.close();
        }
        return true;
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
    
    List obj=null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("FROM Login where loginId = :userId and password=:password");
             query.setString("userId",user_id );
             query.setString("password",password);
             System.out.println("user_id="+user_id+ "  Passwoord="+password);
            obj= query.list();
            session.getTransaction().commit();
        }
    catch (RuntimeException e) {
         e.printStackTrace();
throw e;
            
        }
        finally {
            session.close();
        }
return obj;
}
public static Login getSuperAdminLoginDetails(String user_id,String password,String role)
{
 Session session =null;
    Login obj=null;

    try {
        session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("FROM Login where loginId = :userId and password=:password and role=:role");
             query.setString("userId",user_id );
             query.setString("password",password);
             query.setString("role",role);
          obj= (Login)query.uniqueResult();
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


public List getLoginDetailsbyRole(String role){
 Session session =null;
    List obj=null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("FROM Login where role = :role");
             query.setString("role",role );
            
      obj= query.list();
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


public Login getUser1(String user_id){
 Session session =null;
    Login obj=null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
                session.beginTransaction();
                Query query = session.createQuery("FROM Login where userId = :userId");
                query.setString("userId",user_id );
                obj=(Login) query.uniqueResult();
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
public List getUser(String user_id){
 Session session =null;
    List obj=null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
                session.beginTransaction();
                Query query = session.createQuery("FROM Login where userId = :userId");
                query.setString("userId",user_id );
                obj=query.list();
                 session.getTransaction().commit();
        }
        catch (RuntimeException e) {
         e.printStackTrace();
throw e;

        }
        finally {
            session.close();
        }
return obj;
}


public static Login searchAns(String staff_id,String institute_id,String ans1) {
        Session session = HibernateUtil.getSessionFactory().openSession();
Login obj=null;
        try {
            session.beginTransaction();
            Query query = session.createQuery("FROM  Login  WHERE institute_id =:institute_id  and staff_id=:staff_id and ans=:ans1");
            query.setString("staff_id",staff_id );
            query.setString("institute_id",institute_id );
            query.setString("ans1",ans1 );

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

public static Login searchLoginID(String login_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
Login obj=null;
        try {
            session.beginTransaction();
            Query query = session.createQuery("FROM  Login  WHERE userId =:login_id  ");
            query.setString("login_id", login_id);

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

public static Login searchAllStaffAccount(String library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
Login obj=null;
        try {
            session.beginTransaction();
            Query query = session.createQuery("FROM  Login  WHERE id.libraryId =:library_id  ");
            query.setString("login_id", library_id);

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
public static Login searchAllStaffAccount(String library_id,String sublibrary_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
Login obj=null;
        try {
            session.beginTransaction();
            Query query = session.createQuery("FROM  Login  WHERE id.libraryId =:library_id  and sublibraryId=:sublibrary_id ");
            query.setString("library_id", library_id);
            query.setString("sublibrary_id", sublibrary_id);
            obj= (Login) query.uniqueResult();
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


public static Login searchStaffLogin(String staff_id,String library_id,String sublibrary_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
Login obj=null;
        try {
            session.beginTransaction();
            Query query = session.createQuery("FROM  Login  WHERE id.staffId =:staff_id and id.libraryId=:library_id and sublibraryId=:sublibrary_id");
            query.setString("staff_id", staff_id);
            query.setString("library_id", library_id);
            query.setString("sublibrary_id", sublibrary_id);
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
public static Login searchStaffLogin(String staff_id,String library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
Login obj=null;
        try {
            session.beginTransaction();
            Query query = session.createQuery("FROM  Login  WHERE id.staffId =:staff_id and id.libraryId=:library_id");
            query.setString("staff_id", staff_id);
            query.setString("library_id", library_id);

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
            private static void wait1(int k){
            	do{
                          k--;
                }
                while(k>0);
            }
           


public static  boolean update1(Login obj)
{
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try
        {
		session.update(obj);
		 boolean flg1;
                do{
                        flg1=true;
                        wait1(1000);
                        tx = session.beginTransaction();
                        tx.commit();
                        if(!(tx.wasCommitted())){
                                tx.rollback();
                                flg1=false;
                        }
                }
                while(!(flg1));

//            tx = (Transaction) session.beginTransaction();

  //          session.update(obj);
    //        tx.commit();

        }
        catch (RuntimeException e) {
         e.printStackTrace();
                tx.rollback();
                return false;
            

        }
        finally {
           session.close();
        }
   return true;

}


public static Login searchStaffId(String staff_id,String library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
Login obj=null;
        try {
            session.beginTransaction();
            Query query = session.createQuery("FROM  Login  where id.libraryId =:library_id and id.staffId =:staff_id");
            query.setString("staff_id", staff_id);
            query.setString("library_id", library_id);
           
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

public static Login searchRole(String staff_id,String institute_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Login obj=null;
        try {
            session.beginTransaction();
            Query query = session.createQuery("FROM  Login  where institute_id =:institute_id and staff_id =:staff_id");
            query.setString("staff_id", staff_id);
            query.setString("institute_id", institute_id);
           

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
public static boolean DeleteLogin(String staff_id,String library_id,String sublibrary_id) {
      Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;


        try
        {
            tx = (Transaction) session.beginTransaction();
            Query query = session.createQuery("Delete From  Login where id.libraryId =:library_id and id.staffId =:staff_id and sublibraryId= :sublibrary_id  ");
            query.setString("staff_id", staff_id);
            query.setString("library_id", library_id);
            query.setString("sublibrary_id", sublibrary_id);

              query.executeUpdate();
            tx.commit();



        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            tx.rollback();
             return false;

      

        }
        finally
        {
          session.close();
        }
   return true;


}
public static boolean DeleteLogin(String staff_id,String library_id) {
      Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;


        try
        {
            tx = (Transaction) session.beginTransaction();
            Query query = session.createQuery("Delete From  Login where id.libraryId =:library_id and id.staffId =:staff_id");
            query.setString("staff_id", staff_id);
            query.setString("library_id", library_id);


              query.executeUpdate();
            tx.commit();



        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            tx.rollback();
             return false;


        }
        finally
        {
          //session.close();
        }
   return true;


}
public static Login searchForgetPassword(String login_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
Login obj=null;
        try {
            session.beginTransaction();
            Query query = session.createQuery("FROM  Login  WHERE loginId =:login_id and question!=:ques");
            query.setString("login_id", login_id);
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

public static Login searchFirstLogin(String staff_id,String library_id,String sublibrary_id,String login) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Login obj=null;
        try {
            session.beginTransaction();

        

            Query query = session.createQuery("FROM  Login  WHERE id.libraryId =:library_id and id.staffId =:staff_id and sublibraryId= :sublibrary_id and question = :ques  and loginId= :login_id");
            query.setString("library_id",library_id);
            query.setString("sublibrary_id", sublibrary_id);
            query.setString("staff_id", staff_id);
            query.setString("ques", "@");
            
            query.setString("login_id", login);
         
           
            obj= (Login) query.uniqueResult();
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
public static Login searchUser(String login_id,String password) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Login obj=null;
        try {
            session.beginTransaction();
            Query query = session.createQuery("FROM  Login  WHERE userId =:login_id and password =:password");
            query.setString("login_id", login_id);
            query.setString("password", password);
            obj= (Login) query.uniqueResult();
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
public static  boolean insert1(Login obj)
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
            ex.printStackTrace();
            tx.rollback();
             return false;

       

        }
        finally
        {
          session.close();
        }
   return true;

}

}
