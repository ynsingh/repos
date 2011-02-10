/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.cataloguingDAO;
import com.myapp.struts.hbm.BibliographicDetails;
import com.myapp.struts.hbm.AcqFinalDemandList;
import com.myapp.struts.hbm.HibernateUtil;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Query;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;

/**
 *
 * @author Client
 */
public class BibliopgraphicEntryDAO {
 public void insert(BibliographicDetails bibDetails){
    Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        Criteria criteria = session.createCriteria(BibliographicDetails.class)
                 .setProjection(Projections.max("id.biblioId"));
            Integer maxNewSerialId = Integer.parseInt((String)criteria.uniqueResult());
            maxNewSerialId++;
          
        try {
            tx = session.beginTransaction();
            session.save(bibDetails);
            tx.commit();
        }
        catch (RuntimeException e) {
            if(bibDetails != null)
                tx.rollback();
            throw e;
        }
        finally {
          session.close();
        }
    }
public void update(BibliographicDetails bibDetails) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.update(bibDetails);
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
public void delete(int biblio_id,String library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        //Object acqdocentry = null;

        try {
            tx = session.beginTransaction();
            //acqdocentry = session.load(BibliographicDetails.class, id);
            Query query = session.createQuery("DELETE FROM BibliographicDetails  WHERE  id.biblioId = :biblioId and id.libraryId = :libraryId ");
 
            query.setInteger("biblioId",biblio_id );
            query.setString("libraryId",library_id );
            query.executeUpdate();
            tx.commit();
            //return (BibliographicDetails) query.uniqueResult();
        }

        finally {
            //session.close();
        }
    }
public BibliographicDetails searchIsbn10(String isbn10,String library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();
            Query query = session.createQuery("FROM BibliographicDetails  WHERE isbn10 = :isbn10  and id.libraryId = :libraryId ");
            query.setString("isbn10", isbn10);
            query.setString("libraryId",library_id );
            return (BibliographicDetails) query.uniqueResult();
        }
        finally {
            //session.close();
        }

}
public BibliographicDetails searchcall(String call_no,String library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();
            Query query = session.createQuery("FROM BibliographicDetails  WHERE id.callNo = :callNo  and id.libraryId = :libraryId ");
            query.setString("callNo", call_no);
            query.setString("libraryId",library_id );
            return (BibliographicDetails) query.uniqueResult();
        }
        finally {
            //session.close();
        }

}
public AcqFinalDemandList searchacqIsbn10(String isbn10,String library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();
            Query query = session.createQuery("FROM AcqFinalDemandList  WHERE isbn = :isbn  and id.libraryId = :libraryId ");
            query.setString("isbn", isbn10);
            query.setString("libraryId",library_id );
            return (AcqFinalDemandList) query.uniqueResult();
        }
        finally {
            //session.close();
        }

}
public BibliographicDetails searchIsbn13(String isbn13,String library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();
            Query query = session.createQuery("FROM BibliographicDetails  WHERE isbn13 = :isbn13  and id.libraryId = :libraryId ");
            query.setString("isbn13", isbn13);
            query.setString("libraryId",library_id );
            return (BibliographicDetails) query.uniqueResult();
        }
        finally {
            //session.close();
        }

}
public String isbn10search(String isbn10,String library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        StringBuffer isbn10s = new StringBuffer();
        try {
            session.beginTransaction();
            Query query = session.createQuery("FROM BibliographicDetails  WHERE isbn10 = :isbn10  and id.libraryId = :libraryId ");
            query.setString("isbn10", isbn10);
            query.setString("libraryId",library_id );
            List l=query.list();
            Iterator it = l.iterator();
            if(it.hasNext()){
            isbn10s.append("<isbn10s>");
            isbn10s.append("<isbn13>This isbn already exists</isbn13>");
            isbn10s.append("</isbn10s>");
            }
            else
           {
            isbn10s.append("<isbn10s>");
            isbn10s.append("<isbn103></isbn10>");
            isbn10s.append("<isbn10s>");
           }
           return isbn10s.toString();
        }
        finally {
            //session.close();
        }

}
public String isbn13search(String isbn13,String library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        StringBuffer isbn13s = new StringBuffer();
        try {
            session.beginTransaction();
            Query query = session.createQuery("FROM BibliographicDetails  WHERE isbn13 = :isbn13  and id.libraryId = :libraryId ");
            query.setString("isbn13", isbn13);
            query.setString("libraryId",library_id );
            List l=query.list();
            Iterator it = l.iterator();
            if(it.hasNext()){
            isbn13s.append("<isbn13s>");
            isbn13s.append("<isbn13>This isbn already exists</isbn13>");
            isbn13s.append("</isbn13s>");
            }
            else
           {
            isbn13s.append("<isbn13s>");
            isbn13s.append("<isbn133></isbn13>");
            isbn13s.append("<isbn13s>");
           }
           return isbn13s.toString();
        }
        finally {
            //session.close();
        }

}
public List getBibliographicDetails(String library_id){
  Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();
            Query query = session.createQuery("FROM BibliographicDetails where id.libraryId = :libraryId");
             query.setString("libraryId",library_id );
            return query.list();
        }
        finally {
            session.close();
        }
}

}
