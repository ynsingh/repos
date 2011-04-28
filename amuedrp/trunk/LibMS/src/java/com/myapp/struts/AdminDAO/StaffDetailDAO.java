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
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

/**
 * Developed By : Kedar Kumar
 * Modified By  : 18-Feb-2011
 * Use to Check the Admin Registration Login_ID in Staff Detail Table Duplication
 */
public class StaffDetailDAO {

public static boolean DeleteStaff(String staff_id,String library_id,String sublibrary_id) {
      Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;


        try
        {
            tx = (Transaction) session.beginTransaction();
            Query query = session.createQuery("Delete From  StaffDetail where id.libraryId =:library_id and id.staffId =:staff_id and sublibraryId= :sublibrary_id  ");
            query.setString("staff_id", staff_id);
            query.setString("library_id", library_id);
            query.setString("sublibrary_id", sublibrary_id);

            query.executeUpdate();
            tx.commit();



        }
        catch (Exception ex)
        {
            System.out.println(ex);
             return false;

       //  System.out.println(ex.toString());

        }
        finally
        {
        session.close();
        }
   return true;


}
public static StaffDetail searchLoginID(String login_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();
            Query query = session.createQuery("FROM  StaffDetail  WHERE loginId =:login_id  ");
            query.setString("login_id", login_id);

            return ( StaffDetail) query.uniqueResult();
        }
        finally {
            session.close();
        }

}

public static StaffDetail searchLibraryID(String library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();
            Query query = session.createQuery("FROM  StaffDetail  WHERE id.libraryId =:library_id");
            query.setString("library_id", library_id);

            return ( StaffDetail) query.uniqueResult();
        }
        finally {
            session.close();
        }

}
public static StaffDetail searchStaffId(String staff_id,String library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();
            Query query = session.createQuery("FROM  StaffDetail  WHERE id.libraryId =:library_id and  id.staffId=:staff_id ");
            query.setString("library_id", library_id);
           
            query.setString("staff_id", staff_id);
            return ( StaffDetail) query.uniqueResult();
        }
        finally {
            session.close();
        }

}
public static List<staffsubLib> searchAllStaff(String library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();
        Query query = session.createSQLQuery("select a.*,b.* from staff_detail a inner join sub_library b on a.sublibrary_id=b.sublibrary_id and a.library_id=b.library_id where  a.library_id=:library_id")
                .addEntity(StaffDetail.class)
                .addEntity(SubLibrary.class)
                .setResultTransformer(Transformers.aliasToBean(staffsubLib.class));

            query.setString("library_id", library_id);

            return (List<staffsubLib>) query.list();
        }
        finally {
            session.close();
        }

}
public static List<staffsubLib> searchAllStaff(String library_id,String sublibrary_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();

       try {
            session.beginTransaction();
        Query query = session.createSQLQuery("select a.*,b.* from staff_detail a inner join sub_library b on a.sublibrary_id=b.sublibrary_id and a.library_id=b.library_id where a.library_id=:library_id and a.sublibrary_id=:sublibrary_id")
                .addEntity(StaffDetail.class)
                .addEntity(SubLibrary.class)
                .setResultTransformer(Transformers.aliasToBean(staffsubLib.class));

            query.setString("library_id", library_id);
            query.setString("sublibrary_id", sublibrary_id);

            return (List<staffsubLib>) query.list();
        }
        finally {
            session.close();
        }

}
public static StaffDetail searchStaffId(String staff_id,String library_id,String sublibrary_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();
            Query query = session.createQuery("FROM  StaffDetail  WHERE id.libraryId =:library_id and  id.staffId=:staff_id and sublibraryId=:sublibrary_id");
            query.setString("library_id", library_id);
            query.setString("staff_id", staff_id);
            query.setString("sublibrary_id",sublibrary_id);
            return ( StaffDetail) query.uniqueResult();
        }
        finally {
          session.close();
        }

}
public static  boolean insert1(StaffDetail obj)
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
         System.out.println(ex.toString());
             return false;

       

        }
        finally
        {
          session.close();
        }
   return true;

}

public static  boolean update1(StaffDetail obj)
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
        session.close();
        }
   return true;

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
          //  if(bibDetails != null)
                tx.rollback();
            throw e;
        }
        finally {
           //session.close();
        }
    }

public void delete(int user_id,String institute_id) {
        Session session =null;
    Transaction tx = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            //acqdocentry = session.load(BibliographicDetails.class, id);
            Query query = session.createQuery("DELETE FROM StaffDetail  WHERE  id.staffId = :userId and id.library_id=:libraryId");
            query.setInteger("userId",user_id );
            query.setString("libraryId",institute_id );
            query.executeUpdate();
            tx.commit();
            //return (BibliographicDetails) query.uniqueResult();
        }

        finally {
            //session.close();
        }
    }

public List getStaffDetails(){
  Session session =null;
    Transaction tx = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("FROM StaffDetail");

            return query.list();
        }
        finally {
            session.close();
        }
}

public List getStaffDetails(String staffId,String libraryId){
 Session session =null;
    Transaction tx = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("FROM StaffDetail where id.staffId=:staffId and id.libraryId=:libraryId");
             query.setString("staffId", staffId);
             query.setString("libraryId", libraryId);
            return query.list();
        }
        finally {
            session.close();
        }
}


}
