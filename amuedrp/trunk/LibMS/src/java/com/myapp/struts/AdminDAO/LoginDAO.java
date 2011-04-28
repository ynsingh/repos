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
           //session.close();
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
            Query query = session.createQuery("FROM Login where loginId = :userId and password=:password");
             query.setString("userId",user_id );
             query.setString("password",password);
             System.out.println("user_id="+user_id+ "  Passwoord="+password);
            return query.list();
        }
        finally {
            session.close();
        }

}
public static Login getSuperAdminLoginDetails(String user_id,String password,String role)
{
 Session session =null;
    Transaction tx = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("FROM Login where loginId = :userId and password=:password and role=:role");
             query.setString("userId",user_id );
             query.setString("password",password);
             query.setString("role",role);
          return (Login)query.uniqueResult();

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
                Query query = session.createQuery("FROM Login where loginId = :userId");
                query.setString("userId",user_id );
                return query.list();
        }
        finally {
            session.close();
        }
}


public static Login searchAns(String staff_id,String library_id,String ans1) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();
            Query query = session.createQuery("FROM  Login  WHERE id.libraryId =:library_id  and id.staffId=:staff_id and ans=:ans1");
            query.setString("staff_id",staff_id );
            query.setString("library_id",library_id );
            query.setString("ans1",ans1 );

            return ( Login) query.uniqueResult();
        }
        finally {
          //  session.close();
        }

}

public static Login searchLoginID(String login_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();
            Query query = session.createQuery("FROM  Login  WHERE loginId =:login_id  ");
            query.setString("login_id", login_id);

            return ( Login) query.uniqueResult();
        }
        finally {
          //  session.close();
        }

}

public static Login searchAllStaffAccount(String library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();
            Query query = session.createQuery("FROM  Login  WHERE id.libraryId =:library_id  ");
            query.setString("login_id", library_id);

            return ( Login) query.uniqueResult();
        }
        finally {
         //   session.close();
        }

}
public static Login searchAllStaffAccount(String library_id,String sublibrary_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();
            Query query = session.createQuery("FROM  Login  WHERE id.libraryId =:library_id  and sublibraryId=:sublibrary_id ");
            query.setString("library_id", library_id);
            query.setString("sublibrary_id", sublibrary_id);
            return (Login) query.uniqueResult();
        }
        finally {
           // session.close();
        }

}

public static List<AccountSubLibrary> searchAllStaffListAccount(String library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        Query query = session.createSQLQuery("select a.*,b.* from login a inner join sub_library b on a.sublibrary_id=b.sublibrary_id and a.library_id=b.library_id where a.library_id=:library_id")
                .addEntity(Login.class)
                .addEntity(SubLibrary.class)
                .setResultTransformer(Transformers.aliasToBean(AccountSubLibrary.class));

            query.setString("library_id", library_id);

            return (List<AccountSubLibrary>) query.list();

}
public static List<AccountSubLibrary> searchAllStaffListAccount(String library_id,String sublibrary_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            Query query = session.createSQLQuery("select a.*,b.* from login a inner join sub_library b on a.sublibrary_id=b.sublibrary_id and a.library_id=b.library_id where a.library_id=:library_id and  a.sublibrary_id=:sublibrary_id")
                .addEntity(Login.class)
                .addEntity(SubLibrary.class)
                .setResultTransformer(Transformers.aliasToBean(AccountSubLibrary.class));

            query.setString("library_id", library_id);
            query.setString("sublibrary_id", sublibrary_id);

             return (List<AccountSubLibrary>) query.list();
        }
        finally {
           // session.close();
        }

}
public static Login searchStaffLogin(String staff_id,String library_id,String sublibrary_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();
            Query query = session.createQuery("FROM  Login  WHERE id.staffId =:staff_id and id.libraryId=:library_id and sublibraryId=:sublibrary_id");
            query.setString("staff_id", staff_id);
            query.setString("library_id", library_id);
            query.setString("sublibrary_id", sublibrary_id);
            return ( Login) query.uniqueResult();
        }
        finally {
          //  session.close();
        }

}
public static Login searchStaffLogin(String staff_id,String library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();
            Query query = session.createQuery("FROM  Login  WHERE id.staffId =:staff_id and id.libraryId=:library_id");
            query.setString("staff_id", staff_id);
            query.setString("library_id", library_id);

            return ( Login) query.uniqueResult();
        }
        finally {
          //  session.close();
        }

}

public static  boolean update1(Login obj)
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

public static  boolean updatePriv(Login obj,Privilege priv,AcqPrivilege acqpriv,CatPrivilege catpriv,CirPrivilege cirpriv,SerPrivilege serpriv)
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
          //session.close();
        }
   return true;

}
public static Login searchStaffId(String staff_id,String library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();
            Query query = session.createQuery("FROM  Login  where id.libraryId =:library_id and id.staffId =:staff_id");
            query.setString("staff_id", staff_id);
            query.setString("library_id", library_id);
           
            return ( Login) query.uniqueResult();
        }
        finally {
          //  session.close();
        }

}

public static Login searchRole(String staff_id,String library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();
            Query query = session.createQuery("FROM  Login  where id.libraryId =:library_id and id.staffId =:staff_id");
            query.setString("staff_id", staff_id);
            query.setString("library_id", library_id);
           

            return ( Login) query.uniqueResult();
        }
        finally {
         //   session.close();
        }

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
             return false;

       //  System.out.println(ex.toString());

        }
        finally
        {
          //session.close();
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
             return false;

       //  System.out.println(ex.toString());

        }
        finally
        {
          //session.close();
        }
   return true;


}
public static Login searchForgetPassword(String login_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();
            Query query = session.createQuery("FROM  Login  WHERE loginId =:login_id and question!=:ques");
            query.setString("login_id", login_id);
            query.setString("ques", "@");

            return ( Login) query.uniqueResult();
        }
        finally {
           // session.close();
        }

}

public static Login searchFirstLogin(String staff_id,String library_id,String sublibrary_id,String login) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();

        

            Query query = session.createQuery("FROM  Login  WHERE id.libraryId =:library_id and id.staffId =:staff_id and sublibraryId= :sublibrary_id and question = :ques  and loginId= :login_id");
            query.setString("library_id",library_id);
            query.setString("sublibrary_id", sublibrary_id);
            query.setString("staff_id", staff_id);
            query.setString("ques", "@");
            
            query.setString("login_id", login);
         
           
            return (Login) query.uniqueResult();

            
            
        }
        finally {
         //   session.close();
        }

}
public static Login searchUser(String login_id,String password) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();
            Query query = session.createQuery("FROM  Login  WHERE loginId =:login_id and password =:password");
            query.setString("login_id", login_id);
            query.setString("password", password);
            return (Login) query.uniqueResult();
        }
        finally {
          //  session.close();
        }

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
             return false;

       //  System.out.println(ex.toString());

        }
        finally
        {
          //session.close();
        }
   return true;

}

}
