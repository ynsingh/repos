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
import org.hibernate.HibernateException;
import org.hibernate.transform.Transformers;

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
        catch (HibernateException e) {
          
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
        catch (HibernateException e) {
          
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
 catch (HibernateException e) {
          
                tx.rollback();
            throw e;
        }
        finally {
           session.close();
        }
    }

public List getLoginDetails(String user_id,String password){
 Session session =null;
 Query query=null;
    List obj=null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
             query = session.createQuery("FROM Login where loginId = :userId and password=:password");
             query.setString("userId",user_id );
             query.setString("password",password);
             obj=query.list();
            session.getTransaction().commit();
        }
     catch (HibernateException e) {
            System.out.println(e);
        }
        finally {
        session.close();
        }
return obj;
}
public  Login getSuperAdminLoginDetails(String user_id,String password,String role)
{
 Session session =null;
    Login obj=null;
    Query query=null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
             query = session.createQuery("FROM Login where loginId = :userId and password=:password and role=:role");
             query.setString("userId",user_id );
             query.setString("password",password);
             query.setString("role",role);
          
obj=(Login)query.uniqueResult();
session.getTransaction().commit();
        }
 catch (HibernateException e) {


            System.out.println(e);
        }
        finally {
        session.close();
        }
        return obj;
}


public List getLoginDetailsbyRole(String role){
 Session session =null;
    Query query=null;
    List obj=null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
             query = session.createQuery("FROM Login where role = :role");
             query.setString("role",role );
            obj=query.list();
            session.getTransaction().commit();
            
        }
    catch (HibernateException e) {


            System.out.println(e);
        }
        finally {
           session.close();
        }
        return obj;
}

public List getUser(String user_id){
 Session session =HibernateUtil.getSessionFactory().openSession();
    Query query=null;
    List obj=null;
    try {
      
       
                session.beginTransaction();
                
                 query = session.createQuery("FROM Login where loginId = :userId");
                query.setString("userId",user_id );
                 obj= query.list();
                 session.getTransaction().commit();
        }
    catch (HibernateException e) {

e.printStackTrace();
            
        }
        finally {
    session.close();
        }
      return obj;

}


public  Login searchAns(String staff_id,String library_id,String ans1) {
        Session session = HibernateUtil.getSessionFactory().openSession();
Query query=null;
Login obj=null;
        try {
            session.beginTransaction();
             query = session.createQuery("FROM  Login  WHERE id.libraryId =:library_id  and id.staffId=:staff_id and ans=:ans1");
            query.setString("staff_id",staff_id );
            query.setString("library_id",library_id );
            query.setString("ans1",ans1 );
obj=( Login) query.uniqueResult();
session.getTransaction().commit();
           
        } catch (HibernateException e) {


            System.out.println(e);
        }

        finally {
        session.close();
        }
 return obj;
}

public  Login searchLoginID(String login_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
Query query=null;
Login obj=null;
try {
            session.beginTransaction();
            query = session.createQuery("FROM  Login  WHERE loginId =:login_id  ");
            query.setString("login_id", login_id);
obj=( Login) query.uniqueResult();
           session.getTransaction().commit();
        }
         catch (HibernateException e) {


            System.out.println(e);
        }

        finally {
        session.close();
        }
 return obj;
}

public  Login searchAllStaffAccount(String library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
Query query=null;
Login obj=null;
        try {
            session.beginTransaction();
            query = session.createQuery("FROM  Login  WHERE id.libraryId =:library_id  ");
            query.setString("login_id", library_id);
obj=( Login) query.uniqueResult();
   session.getTransaction().commit();
        }
         catch (HibernateException e) {


            System.out.println(e);
        }

        finally {
     HibernateUtil.getSessionFactory().close();
        }
  return obj;

}
public  Login searchAllStaffAccount(String library_id,String sublibrary_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
Query query=null;
Login obj=null;
        try {
            session.beginTransaction();
             query = session.createQuery("FROM  Login  WHERE id.libraryId =:library_id  and sublibraryId=:sublibrary_id ");
            query.setString("library_id", library_id);
            query.setString("sublibrary_id", sublibrary_id);
          obj=  (Login) query.uniqueResult();
          session.getTransaction().commit();
        }
         catch (HibernateException e) {


            System.out.println(e);
        }

        finally {
     session.close();
        }
return obj;
}

public  List<AccountSubLibrary> searchAllStaffListAccount(String library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query=null;
        List<AccountSubLibrary> obj=null;
         try {
            session.beginTransaction();
         query = session.createSQLQuery("select a.*,b.* from login a inner join sub_library b on a.sublibrary_id=b.sublibrary_id and a.library_id=b.library_id where a.library_id=:library_id")
                .addEntity(Login.class)
                .addEntity(SubLibrary.class)
                .setResultTransformer(Transformers.aliasToBean(AccountSubLibrary.class));

            query.setString("library_id", library_id);
            
obj=(List<AccountSubLibrary>) query.list();
session.getTransaction().commit();
           
              }
         catch (HibernateException e) {


            System.out.println(e);
        }

        finally {
         session.close();
        }
 return obj;
}
public  List<AccountSubLibrary> searchAllStaffListAccount(String library_id,String sublibrary_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
Query query=null;
List<AccountSubLibrary>  obj=null;
        try {
            session.beginTransaction();
             query = session.createSQLQuery("select a.*,b.* from login a inner join sub_library b on a.sublibrary_id=b.sublibrary_id and a.library_id=b.library_id where a.library_id=:library_id and  a.sublibrary_id=:sublibrary_id")
                .addEntity(Login.class)
                .addEntity(SubLibrary.class)
                .setResultTransformer(Transformers.aliasToBean(AccountSubLibrary.class));

            query.setString("library_id", library_id);
            query.setString("sublibrary_id", sublibrary_id);
obj=(List<AccountSubLibrary>) query.list();
     session.getTransaction().commit();
        }
         catch (HibernateException e) {


            System.out.println(e);
        }

        finally {
         session.close();
        }
return obj;
}
public  Login searchStaffLogin(String staff_id,String library_id,String sublibrary_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
Query query=null;
Login login=null;
        try {
            session.beginTransaction();
            query = session.createQuery("FROM  Login  WHERE id.staffId =:staff_id and id.libraryId=:library_id and sublibraryId=:sublibrary_id");
            query.setString("staff_id", staff_id);
            query.setString("library_id", library_id);
            query.setString("sublibrary_id", sublibrary_id);
         login=( Login) query.uniqueResult();
         session.getTransaction().commit();
        }
         catch (HibernateException e) {


            System.out.println(e);
        }

        finally {
         session.close();
        }
   return login;
}
public  Login searchStaffLogin(String staff_id,String library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
Query query=null;
Login obj=null;
        try {
            session.beginTransaction();
             query = session.createQuery("FROM  Login  WHERE id.staffId =:staff_id and id.libraryId=:library_id");
            query.setString("staff_id", staff_id);
            query.setString("library_id", library_id);
obj=( Login) query.uniqueResult();
      session.getTransaction().commit();
        }
        catch (HibernateException e) {


            System.out.println(e);
        }
        finally {
          session.close();
        }
 return obj;
}

public  boolean update1(Login obj)
{
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;


        try
        {
            tx = (Transaction) session.beginTransaction();

            session.update(obj);
            tx.commit();



        }
        catch (HibernateException ex)
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

public boolean updatePriv(Login obj,Privilege priv,AcqPrivilege acqpriv,CatPrivilege catpriv,CirPrivilege cirpriv,SerPrivilege serpriv)
{
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;


        try
        {
            tx = (Transaction) session.beginTransaction();

            session.update(obj);
            session.update(priv);
            session.update(acqpriv);
            session.update(catpriv);
            session.update(cirpriv);
            session.update(serpriv);
            tx.commit();



        }
        catch (Exception ex)
        {
         System.out.println(ex.toString());
         tx.rollback();
         return false;



        }
        finally
        {
          session.close();
        }
   return true;

}
public Login searchStaffId(String staff_id,String library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
Query  query=null;
Login obj=null;
        try {
            session.beginTransaction();
            query = session.createQuery("FROM  Login  where id.libraryId =:library_id and id.staffId =:staff_id");
            query.setString("staff_id", staff_id);
            query.setString("library_id", library_id);
           
         obj=( Login) query.uniqueResult();
         session.getTransaction().commit();
        }
           catch (Exception ex)
        {
         System.out.println(ex.toString());





        }
        finally {
         session.close();
        }
   return obj;
}

public Login searchRole(String staff_id,String library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
Query query=null;
Login obj=null;
        try {
            session.beginTransaction();
             query = session.createQuery("FROM  Login  where id.libraryId =:library_id and id.staffId =:staff_id");
            query.setString("staff_id", staff_id);
            query.setString("library_id", library_id);
           obj=( Login) query.uniqueResult();

        session.getTransaction().commit();
        }
        catch(HibernateException e){
        e.printStackTrace();
        }
        finally {
        session.close();
        }
return obj;
}
public boolean DeleteLogin(String staff_id,String library_id,String sublibrary_id) {
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
public boolean DeleteLogin(String staff_id,String library_id) {
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
        { tx.rollback();
            System.out.println(ex.toString());
             return false;

       

        }
        finally
        {
         session.close();
        }
   return true;


}
public Login searchForgetPassword(String login_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
Query query=null;
Login obj=null;
        try {
            session.beginTransaction();
             query = session.createQuery("FROM  Login  WHERE loginId =:login_id and question!=:ques");
            query.setString("login_id", login_id);
            query.setString("ques", "@");
obj=( Login) query.uniqueResult();
   session.getTransaction().commit();
        }

         catch (Exception ex)
        {
            System.out.println(ex.toString());




        }
        finally
        {
         session.close();
        }
  return obj;
}

public Login searchFirstLogin(String staff_id,String library_id,String sublibrary_id,String login) {
        Session session = HibernateUtil.getSessionFactory().openSession();
Query query=null;
Login obj=null;
        try {
            session.beginTransaction();

        

            query = session.createQuery("FROM  Login  WHERE id.libraryId =:library_id and id.staffId =:staff_id and sublibraryId= :sublibrary_id and question = :ques  and loginId= :login_id");
            query.setString("library_id",library_id);
            query.setString("sublibrary_id", sublibrary_id);
            query.setString("staff_id", staff_id);
            query.setString("ques", "@");
            
            query.setString("login_id", login);
         
           obj=(Login) query.uniqueResult();
           

            session.getTransaction().commit();
            
        }
         catch (Exception ex)
        {
            System.out.println(ex.toString());




        }
        finally
        {
         session.close();
        }
 return obj;
}
public  Login searchUser(String login_id,String password) {
        Session session = HibernateUtil.getSessionFactory().openSession();
Query query=null;
Login obj=null;
        try {
            session.beginTransaction();
             query = session.createQuery("FROM  Login  WHERE loginId =:login_id and password =:password");
            query.setString("login_id", login_id);
            query.setString("password", password);
            obj=(Login) query.uniqueResult();
            session.getTransaction().commit();
        }

       catch (Exception ex)
        {
            System.out.println(ex.toString());




        }
        finally
        {
         session.close();
        }
      return obj;
}
public  boolean insert1(Login obj)
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

}
