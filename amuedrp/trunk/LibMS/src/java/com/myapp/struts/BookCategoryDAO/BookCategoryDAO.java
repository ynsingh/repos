/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.BookCategoryDAO;
import com.myapp.struts.hbm.*;
import java.util.*;
import com.myapp.struts.hbm.HibernateUtil;
import org.hibernate.Transaction;
import org.hibernate.Session;
import org.hibernate.Query;
/**
 *
 * @author edrp02
 */
public class BookCategoryDAO {

public static BookCategory getMemid(String library_id,String memtype,String sub_memtype,String book_type) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        BookCategory obj=null;
        
        try {
            session.beginTransaction();
            Query query = session.createQuery("FROM BookCategory WHERE id.libraryId =:library_id and id.emptypeId= :memtype and id.subEmptypeId=:sub_memtype and id.bookType=:book_type");
            query.setString("library_id", library_id);
            query.setString("memtype",memtype);
            query.setString("sub_memtype",sub_memtype);
            query.setString("book_type",book_type);
           obj=(BookCategory) query.uniqueResult();
           session.getTransaction().commit();
        }
        catch(Exception e){
        e.printStackTrace();
        }
        finally{
        session.close();
        }
        return obj;
        
}

}
