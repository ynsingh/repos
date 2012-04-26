/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.systemsetupDAO;
import com.myapp.struts.circulation.FineDetailGrid;
import com.myapp.struts.hbm.*;
import java.util.*;
import com.myapp.struts.hbm.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Transaction;
import org.hibernate.Session;
import org.hibernate.Query;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
/**
 * Developed By : Kedar Kumar
 * Modified By  : 18-Feb-2011
 * Use to Check the Multiple occurance of Library_Id in Library POJO
 */
public class BookCategoryDAO {

public static Integer returnIssueLimit(String library_id,String document_category,String mem_type,String submem_type) {
        Session session = HibernateUtil.getSessionFactory().openSession();
       Integer maxbiblio=null;
        try {
             session.beginTransaction();
            Criteria criteria = session.createCriteria(BookCategory.class)
            .add(Restrictions.eq("id.libraryId", library_id))

            .add(Restrictions.eq("id.bookType", document_category))
            .add(Restrictions.eq("id.subEmptypeId", submem_type))
            .add(Restrictions.eq("id.emptypeId",mem_type));
            maxbiblio = (Integer) criteria.setProjection(Projections.property("issueDaysLimit")).uniqueResult();
          session.getTransaction().commit();
        }   catch(Exception e){
        e.printStackTrace();
        }
        finally {
           session.close();
        }
        return maxbiblio;

}

 public static List bookList(String library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
List obj=null;
        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(BookCategory.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id)));
           obj=  criteria.list();
session.getTransaction().commit();
        }   catch(Exception e){
        e.printStackTrace();
        }
        finally {
           session.close();
        }
        return obj;

}
   
 public static List<BookCategory> ListbookType(String library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
       List<BookCategory>  obj=null;
        try {
            session.beginTransaction();
            Query query = session.createQuery("Select id.bookType,fine,issueDaysLimit,id.emptypeId,id.subEmptypeId From  BookCategory where id.libraryId =:library_id");
            query.setString("library_id", library_id);
            query.setResultTransformer(Transformers.TO_LIST);
            obj= (List<BookCategory>)  query.list();
session.getTransaction().commit();
       
        }  catch(Exception e){
        e.printStackTrace();
        }
        finally {
           session.close();
        }
        return obj;

}
  public static List<FineDetailGrid> ListbookType1(String library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
       List<FineDetailGrid> obj=null;
        try {
            session.beginTransaction();
            Query query = session.createSQLQuery("select *  from document_category dc, book_category bc, employee_type et, sub_employee_type st where dc.library_id=bc.library_id and dc.library_id = et.library_id and dc.library_id = st.library_id and dc.document_category_id = bc.book_type and bc.emptype_id = et.emptype_id and bc.sub_emptype_id = st.sub_emptype_id and dc.library_id=:library_id")
                    .addEntity(DocumentCategory.class)
                    .addEntity(BookCategory.class)
                    .addEntity(EmployeeType.class)
                    .addEntity(SubEmployeeType.class);
            query.setString("library_id", library_id);
            query.setResultTransformer(Transformers.aliasToBean(FineDetailGrid.class));
           obj=(List<FineDetailGrid>)  query.list();
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

    public static  BookCategory searchBookTypeDetails(String library_id,String emptype_id,String subemptype_id,String book_type)
{
        Session session = HibernateUtil.getSessionFactory().openSession();
BookCategory obj=null;
        try
        {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(BookCategory.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.emptypeId", emptype_id))
                    .add(Restrictions.eq("id.subEmptypeId", subemptype_id))
                    .add(Restrictions.eq("id.bookType", book_type)));
           obj= (BookCategory) criteria.uniqueResult();
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

  public static  EmployeeType searchMemTypeId(String library_id,String emptype_id)
{
        Session session = HibernateUtil.getSessionFactory().openSession();
 EmployeeType obj=null;
        try
        {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(EmployeeType.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.emptypeId", emptype_id)));
            obj= (EmployeeType) criteria.uniqueResult();
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
   public static  SubEmployeeType searchSubMemTypeId(String library_id,String emp_id,String subemptype_id)
{
        Session session = HibernateUtil.getSessionFactory().openSession();
SubEmployeeType obj=null;
        try
        {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(SubEmployeeType.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.emptypeId", emp_id))
                    .add(Restrictions.eq("id.subEmptypeId",subemptype_id)));
            obj= (SubEmployeeType) criteria.uniqueResult();
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
  


   public static  List<BookCategory> searchBookCategoryBySubMemberId(String library_id,String emptype_id,String subemptype_id)
{
        Session session = HibernateUtil.getSessionFactory().openSession();
List<BookCategory> obj=null;
        try
        {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(BookCategory.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.emptypeId", emptype_id))
                    .add(Restrictions.eq("id.subEmptypeId",subemptype_id)));
            obj= (List<BookCategory>) criteria.list();
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


public static  boolean insert(BookCategory obj)
{
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;


        try
        {
            tx = (Transaction) session.beginTransaction();

            session.save(obj);
            tx.commit();



        }
         catch(Exception e){
             tx.rollback();
        e.printStackTrace();
        return false;
        }
        finally {
           session.close();
        }
      return true;

}

public static  boolean update(BookCategory obj)
{
         Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.update(obj);
            tx.commit();
        }
        catch(Exception e){
            tx.rollback();
        e.printStackTrace();
        return false;
        }
        finally {
           session.close();
        }
        return true;

}

public static boolean DeleteBook(String library_id,String emptype_id,String subemptype_id,String book_type) {
      Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;


        try
        {
            tx = (Transaction) session.beginTransaction();
            Query query = session.createQuery("Delete From  BookCategory where id.libraryId =:library_id and id.emptypeId=:emptype_id and id.subEmptypeId=:subemptype_id and id.bookType=:book_type");


            query.setString("library_id", library_id);
            query.setString("emptype_id", emptype_id);
            query.setString("subemptype_id", subemptype_id);
            query.setString("book_type", book_type);

            query.executeUpdate();
            tx.commit();



        }
          catch(Exception e){
        e.printStackTrace();
        tx.rollback();
        return false;
        }
        finally {
           session.close();
        }
        return true;

}


}
