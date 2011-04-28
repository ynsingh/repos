/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.circulationDAO;
import com.myapp.struts.hbm.*;
import java.util.*;
import com.myapp.struts.hbm.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Transaction;
import org.hibernate.Session;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
/**
 * Developed By : Kedar Kumar
 * Modified By  : 18-Feb-2011
 * Use to Check the Multiple occurance of Library_Id in Library POJO
 */
public class cirDAO {


 
   static Query query;

   public static CirMemberAccount getAccountDate(String library_id,String sublibrary_id,String memid) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(CirMemberAccount.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.sublibraryId", sublibrary_id))
                    .add(Restrictions.eq("id.memid", memid))
                    .add(Restrictions.eq("status", "Active")));
            return (CirMemberAccount) criteria.uniqueResult();
        }
        finally {
            session.close();
        }

}

 public static List searchDoc1(String library_id, String sub_library_id,String call_no) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            Criteria criteria = session.createCriteria(DocumentDetails.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.sublibraryId", sub_library_id))
                    .add(Restrictions.eq("callNo", call_no))
                    .add(Restrictions.eq("status", "available"))
                 //   .add(Restrictions.ne("bookType", ))
                    );
            return criteria.list();
        } finally {
            session.close();
        }
    }
 public static String DistinctDocType(String library_id,String sublibrary_id,String call_no) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String status="available";
        try {
            session.beginTransaction();
            Query query = session.createQuery("Select distinct bookType From  DocumentDetails where id.libraryId =:library_id and callNo= :callNo and status = :status and id.sublibraryId=:sublibrary_id");
            query.setString("library_id", library_id);
            query.setString("callNo", call_no);
            query.setString("status", status);
            query.setString("sublibrary_id",sublibrary_id);
            return (String)  query.uniqueResult();
        } finally {
            session.close();
        }
    }


public static CirMemberAccount getAccount2(String library_id,String sublibrary_id,String memid) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        String status="Registered";
        try {
            session.beginTransaction();
            Query query = session.createQuery("FROM  CirMemberAccount  WHERE id.libraryId =:library_id and id.memid = :memid and id.sublibraryId=:sublibrary_id ");
            query.setString("library_id", library_id);
            query.setString("memid",memid);
            query.setString("sublibrary_id",sublibrary_id);
            //query.setString("status",status);
            return (CirMemberAccount) query.uniqueResult();
        }
        finally {
            session.close();
        }

}



public static CirMemberDetail getMemberDetail(String library_id,String memid) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            session.beginTransaction();
            Query query = session.createQuery("FROM  CirMemberDetail  WHERE id.libraryId =:library_id and id.memId = :memid");
            query.setString("library_id", library_id);
            query.setString("memid",memid);

            return (CirMemberDetail) query.uniqueResult();
        }
        finally {
            session.close();
        }

}




public static  boolean update2(CirMemberAccount obj)
{
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.update(obj);
            tx.commit();
        }
        catch (RuntimeException e) {
                System.out.println("FacultyDAO.Update():*****"+e);
                tx.rollback();
                return false;

        }
        finally
        {
          session.close();
        }

   return true;

}



public static CirMemberDetail getMemid(String library_id,String memid) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            session.beginTransaction();
            Query query = session.createQuery("FROM  CirMemberDetail  WHERE id.libraryId =:library_id and id.memId = :memId ");
            query.setString("library_id", library_id);
            query.setString("memId",memid);
           
            return (CirMemberDetail) query.uniqueResult();
        }
        finally {
            session.close();
        }

}

public static CirMemberAccount getAccount(String library_id,String sublibrary_id,String memid) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            session.beginTransaction();
            Query query = session.createQuery("FROM  CirMemberAccount  WHERE id.libraryId =:library_id and id.memid = :memid and id.sublibraryId=:sublibrary_id");
            query.setString("library_id", library_id);
            query.setString("memid",memid);
            query.setString("sublibrary_id",sublibrary_id);
            return (CirMemberAccount) query.uniqueResult();
        }
        finally {
            session.close();
        }

}

public static List<CirOpacRequest> getOpacCheckOut(String library_id,String sublibrary_id,String status) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            session.beginTransaction();
            Query query = session.createQuery("FROM  CirOpacRequest  WHERE id.libraryId =:library_id and id.sublibraryId=:sublibrary_id and status=:status");
            query.setString("library_id", library_id);
            query.setString("sublibrary_id",sublibrary_id);
            query.setString("status",status);
            return (List<CirOpacRequest>) query.list();
        }
        finally {
            session.close();
        }

}


public static List<DocumentDetails> searchBYCallno(String library_id,String sublibrary_id,String call_no) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String status="available";
        try {
            session.beginTransaction();
            Query query1 = session.createQuery("FROM  DocumentDetails  WHERE id.libraryId= :library_id  and callNo= :callNo and status = :status and id.sublibraryId=:sublibrary_id");
            query1.setString("library_id", library_id);
            query1.setString("callNo", call_no);
            query1.setString("status", status);
             query1.setString("sublibrary_id",sublibrary_id);
            return (List<DocumentDetails>) query1.list();
        }
        finally {
            session.close();
        }

}

public static DocumentDetails getBook(String library_id,String sublibrary_id,String accession_no) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            session.beginTransaction();
            Query query = session.createQuery("FROM  DocumentDetails  WHERE id.libraryId =:library_id and id.sublibraryId = :sublibrary_id and accessionNo = :accession_no");
            query.setString("library_id", library_id);
            query.setString("sublibrary_id", sublibrary_id);
            query.setString("accession_no",accession_no);
            return (DocumentDetails) query.uniqueResult();
        }
        finally {
            session.close();
        }

}
public static DocumentDetails getBookStatus(String library_id,String sublibrary_id,String accession_no) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            session.beginTransaction();
            Query query = session.createQuery("FROM  DocumentDetails  WHERE id.libraryId =:library_id and id.sublibraryId = :sublibrary_id and accessionNo = :accession_no and status=:status");
            query.setString("library_id", library_id);
            query.setString("sublibrary_id", sublibrary_id);
            query.setString("accession_no",accession_no);
            query.setString("status", "issued");
            return (DocumentDetails) query.uniqueResult();
        }
        finally {
            session.close();
        }

}

public static DocumentDetails getDocument(String library_id,String sublibrary_id,int document_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            session.beginTransaction();
            Query query = session.createQuery("FROM  DocumentDetails  WHERE id.libraryId =:library_id and id.sublibraryId = :sublibrary_id and id.documentId = :document_id");
            query.setString("library_id", library_id);
            query.setString("sublibrary_id", sublibrary_id);
            query.setInteger("document_id",document_id);
            return (DocumentDetails) query.uniqueResult();
        }
        finally {
            session.close();
        }

}

public static CirMemberAccount getCirMem(String library_id,String sublibrary_id,String memid) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            session.beginTransaction();
            Query query = session.createQuery("FROM  CirMemberAccount  WHERE id.libraryId =:library_id and id.sublibraryId = :sublibrary_id and id.memid = :memid");
            query.setString("library_id", library_id);
            query.setString("sublibrary_id", sublibrary_id);
            query.setString("memid",memid);
            return (CirMemberAccount) query.uniqueResult();
        }
        finally {
            session.close();
        }

}






public static  boolean update(CirCheckout obj1,CirTransactionHistory obj2,CirMemberAccount obj3,DocumentDetails obj4)
{
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.save(obj1);
            session.save(obj2);
            session.update(obj3);
            session.update(obj4);
            tx.commit();
        }
        catch (RuntimeException e) {
                System.out.println("FacultyDAO.Update():*****"+e);
                tx.rollback();
                return false;

        }
        finally
        {
          session.close();
        }

   return true;

}






 public static List getMaxChkoutId(String library_id,String sublibrary_id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            session.beginTransaction();
            Query query = session.createQuery("SELECT Max(id.checkoutId)FROM CirCheckout where id.libraryId = :library_id and id.sublibraryId = :sublibrary_id");
            query.setString("library_id",library_id );
            query.setString("sublibrary_id",sublibrary_id );
            return  query.list();
        }
        finally {
            session.close();
        }



}


 public static List getMaxTransId(String library_id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            session.beginTransaction();
            Query query = session.createQuery("SELECT Max(id.transactionId)FROM CirTransactionHistory where id.libraryId = :library_id ");
            query.setString("library_id",library_id );
            return  query.list();
        }
        finally {
            session.close();
        }



}

public static  boolean delete(CirMemberAccount obj)
{
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.delete(obj);
            tx.commit();
        }
        catch (RuntimeException e) {
                System.out.println("FacultyDAO.Update():*****"+e);
                tx.rollback();
                return false;

        }
        finally
        {
          session.close();
        }

   return true;

}
public static  boolean insert(CirMemberAccount obj)
{
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.saveOrUpdate(obj);
            tx.commit();
        }
        catch (RuntimeException e) {
                System.out.println("FacultyDAO.Update():*****"+e);
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
