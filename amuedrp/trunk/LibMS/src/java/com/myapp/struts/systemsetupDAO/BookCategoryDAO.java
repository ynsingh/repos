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
        session.beginTransaction();
        try {
            Criteria criteria = session.createCriteria(BookCategory.class)
            .add(Restrictions.eq("id.libraryId", library_id))

            .add(Restrictions.eq("id.bookType", document_category))
            .add(Restrictions.eq("id.subEmptypeId", submem_type))
            .add(Restrictions.eq("id.emptypeId",mem_type));
            Integer maxbiblio = (Integer) criteria.setProjection(Projections.property("issueDaysLimit")).uniqueResult();
            return maxbiblio;
        } finally {
            session.close();
        }

    }

 public static List bookList(String library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(BookCategory.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id)));
            return  criteria.list();

        } finally {
            session.close();
        }
    }
   
 public static List<BookCategory> ListbookType(String library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("Select id.bookType,fine,issueDaysLimit,id.emptypeId,id.subEmptypeId From  BookCategory where id.libraryId =:library_id");
            query.setString("library_id", library_id);
            query.setResultTransformer(Transformers.TO_LIST);
            return (List<BookCategory>)  query.list();

        //    List results = session.createCriteria(Cat.class).setProjection(Projections.projectionList().add(Projections.rowCount()).add(Projections.avg("weight")).add(Projections.max("weight")).add(Projections.groupProperty("color"))).list();


         /*   Criteria criteria = session.createCriteria(BookCategory.class)
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .setProjection(Projections.projectionList())
                    .add(Projections.distinct(Property.forName("id.bookType"))
                    .add(Projections.distinct(Property.forName("detail")));*/
            //   criteria.setProjection(projList);

/*Criteria criteria=session.createCriteria(BookCategory.class)
        .add(Restrictions.eq("id.libraryId", library_id));

ProjectionList p=Projections.projectionList();
p.add(Projections.property("id.bookType"));
p.add(Projections.property("detail"));
criteria.setProjection(Projections.distinct(p));*/

            // .add(Restrictions.conjunction()

            // List<BookCategory> doclist =  criteria.setProjection(Projections.groupProperty("detail")).list();
         //   return (List<BookCategory>) criteria.list();
        } finally {
            session.close();
        }
    }

  public static List<FineDetailGrid> ListbookType1(String library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            Query query = session.createSQLQuery("select *  from document_category dc, book_category bc, employee_type et, sub_employee_type st where dc.library_id=bc.library_id and dc.library_id = et.library_id and dc.library_id = st.library_id and dc.document_category_id = bc.book_type and bc.emptype_id = et.emptype_id and bc.sub_emptype_id = st.sub_emptype_id and dc.library_id=:library_id")
                    .addEntity(DocumentCategory.class)
                    .addEntity(BookCategory.class)
                    .addEntity(EmployeeType.class)
                    .addEntity(SubEmployeeType.class);
            query.setString("library_id", library_id);
            query.setResultTransformer(Transformers.aliasToBean(FineDetailGrid.class));
            return (List<FineDetailGrid>)  query.list();

        //    List results = session.createCriteria(Cat.class).setProjection(Projections.projectionList().add(Projections.rowCount()).add(Projections.avg("weight")).add(Projections.max("weight")).add(Projections.groupProperty("color"))).list();


         /*   Criteria criteria = session.createCriteria(BookCategory.class)
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .setProjection(Projections.projectionList())
                    .add(Projections.distinct(Property.forName("id.bookType"))
                    .add(Projections.distinct(Property.forName("detail")));*/
            //   criteria.setProjection(projList);

/*Criteria criteria=session.createCriteria(BookCategory.class)
        .add(Restrictions.eq("id.libraryId", library_id));

ProjectionList p=Projections.projectionList();
p.add(Projections.property("id.bookType"));
p.add(Projections.property("detail"));
criteria.setProjection(Projections.distinct(p));*/

            // .add(Restrictions.conjunction()

            // List<BookCategory> doclist =  criteria.setProjection(Projections.groupProperty("detail")).list();
         //   return (List<BookCategory>) criteria.list();
        } finally {
            session.close();
        }
    }


    public static  BookCategory searchBookTypeDetails(String library_id,String emptype_id,String subemptype_id,String book_type)
{
        Session session = HibernateUtil.getSessionFactory().openSession();

        try
        {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(BookCategory.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.emptypeId", emptype_id))
                    .add(Restrictions.eq("id.subEmptypeId", subemptype_id))
                    .add(Restrictions.eq("id.bookType", book_type)));
            return (BookCategory) criteria.uniqueResult();


        }
        finally
        {
           session.close();
        }
}

  public static  EmployeeType searchMemTypeId(String library_id,String emptype_id)
{
        Session session = HibernateUtil.getSessionFactory().openSession();

        try
        {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(EmployeeType.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.emptypeId", emptype_id)));
            return (EmployeeType) criteria.uniqueResult();


        }
        finally
        {
           session.close();
        }
}
   public static  SubEmployeeType searchSubMemTypeId(String library_id,String subemptype_id)
{
        Session session = HibernateUtil.getSessionFactory().openSession();

        try
        {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(SubEmployeeType.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.subEmptypeId",subemptype_id)));
            return (SubEmployeeType) criteria.uniqueResult();


        }
        finally
        {
           session.close();
        }
}

  


   public static  List<BookCategory> searchBookCategoryBySubMemberId(String library_id,String emptype_id,String subemptype_id)
{
        Session session = HibernateUtil.getSessionFactory().openSession();

        try
        {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(BookCategory.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.emptypeId", emptype_id))
                    .add(Restrictions.eq("id.subEmptypeId",subemptype_id)));
            return (List<BookCategory>) criteria.list();


        }
        finally
        {
           session.close();
        }
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


public static  boolean update(BookCategory obj)
{
         Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.update(obj);
            tx.commit();
        }
        catch (RuntimeException e) {

                tx.rollback();
                return false;

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
        catch (Exception ex)
        {
            System.out.println(ex);
             return false;

       //  System.out.println(ex.toString());

        }
        finally
        {
        // session.close();
        }
   return true;


}


}
