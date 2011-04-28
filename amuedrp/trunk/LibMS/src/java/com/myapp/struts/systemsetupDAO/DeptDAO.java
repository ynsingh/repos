/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.systemsetupDAO;
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
public class DeptDAO {


 
   static Query query;
   public static List<SubLibrary> listsub(String library_id,String sublibrary_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(SubLibrary.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.ne("id.sublibraryId", sublibrary_id))
                    );
            return (List<SubLibrary>) criteria.list();


        } finally {
            session.close();
        }
}

   public static List<Department> getDeptLibrary(String library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            session.beginTransaction();
            Query query = session.createQuery("FROM  Department  WHERE id.libraryId =:library_id");
            query.setString("library_id", library_id);


            return  query.list();
        }
        finally {
            session.close();
        }

}
   public static List<SubLibrary> listsub1(String library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(SubLibrary.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    );
            return (List<SubLibrary>) criteria.list();


        } finally {
            session.close();
        }
}

public static Department getDeptId(String library_id,String faculty_id,String dept_name) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            session.beginTransaction();
            Query query = session.createQuery("FROM  Department  WHERE id.libraryId =:library_id and id.facultyId=:faculty_id and deptName = :dept_name");
            query.setString("library_id", library_id);
            query.setString("dept_name",dept_name);
            query.setString("faculty_id",faculty_id);

            return (Department) query.uniqueResult();
        }
        finally {
            session.close();
        }

}


public static Department getDeptName(String library_id,String dept_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            session.beginTransaction();
            Query query = session.createQuery("FROM  Department  WHERE id.libraryId =:library_id and id.deptId = :deptId");
            query.setString("library_id", library_id);
            query.setString("deptId",dept_id);

            return (Department) query.uniqueResult();
        }
        finally {
            session.close();
        }

}
public static Department getDeptByFaculty(String library_id,String faculty_id,String dept_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            session.beginTransaction();
            Query query = session.createQuery("FROM  Department  WHERE id.libraryId =:library_id and  id.facultyId=:faculty_id and id.deptId = :deptId");
            query.setString("library_id", library_id);
            query.setString("faculty_id", faculty_id);
            query.setString("deptId",dept_id);

            return (Department) query.uniqueResult();
        }
        finally {
            session.close();
        }

}
public static List<Department> getDept(String library_id,String faculty_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            session.beginTransaction();
            Query query = session.createQuery("FROM  Department  WHERE id.libraryId =:library_id and id.facultyId = :faculty_id");
            query.setString("library_id", library_id);
            query.setString("faculty_id",faculty_id);

            return  query.list();
        }
        finally {
            session.close();
        }

}


public static List getDeptRecord(String library_id,String faculty_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            session.beginTransaction();
            Query query;
            if(library_id!=null){
             query = session.createQuery("FROM  Department  WHERE id.libraryId = :library_id and id.facultyId=:faculty_id");
            query.setString("library_id", library_id);
            query.setString("faculty_id", faculty_id);
            }else{
             query = session.createQuery("FROM  Department");
            }
            return query.list();
        }
        finally {
            session.close();
        }

}



public static List<Department> searchDept(String library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            session.beginTransaction();
            Query query1;
            if(library_id!=null)
            {
                 query1 = session.createQuery("FROM  Department  WHERE id.libraryId =:library_id");
            query1.setString("library_id", library_id);
            }else{
          query1 = session.createQuery("FROM  Department");
            }

            return (List<Department>) query1.list();
        }
        finally {
            session.close();
        }

}
public static List<SubLibrary> searchSubLib(String library_id,String sublibrary_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();
            Query query1 = session.createQuery("FROM  SubLibrary  WHERE id.sublibraryId !=:sublibrary_id and id.libraryId=:library_id");
            query1.setString("library_id", library_id);
            query1.setString("sublibrary_id", sublibrary_id);

            return (List<SubLibrary>) query1.list();
        }
        finally {
           session.close();
        }

}

  public static SubLibrary searchLibraryName(String sublibrary_name) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();
            Query query1 = session.createQuery("FROM  SubLibrary  WHERE sublibName=:sublibraryname");
           
            query1.setString("sublibraryname",sublibrary_name);

            return (SubLibrary) query1.uniqueResult();
        }
        finally {
           session.close();
        }

}
public static  boolean update(Department obj)
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


public static  boolean Delete(Department obj)
{
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.delete(obj);
            tx.commit();
        }
        catch (RuntimeException e) {

                tx.rollback();
                return false;

        }

   return true;

}



public static  boolean insert(Department obj)
{
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;


        try
        {
            tx = (Transaction) session.beginTransaction();

            session.save(obj);
            tx.commit();

            return true;

        }
        catch (Exception ex)
        {
             return false;

       //  System.out.println(ex.toString());

        }
        finally
        {
          session.close();
        }
   

}


 public static List getMaxDeptRecordIdNo(String library_id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            session.beginTransaction();
            Query query = session.createQuery("SELECT Max(id.deptId)FROM Department where id.libraryId = :library_id ");
            query.setString("library_id",library_id );
            return  query.list();
        }
        finally {
            session.close();
        }



}



  public static Department getDeptRecordIdNo(String library_id,String dept_id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            session.beginTransaction();
            Query query = session.createQuery("FROM Department where id.libraryId = :libraryId and id.deptId = :deptId ");
            query.setString("libraryId",library_id );
            query.setString("deptId",dept_id );
            Department dept=(Department)query.uniqueResult();
            return dept;
        }
        finally {
            session.close();
        }



}


  public static Department getDeptRecordId(String library_id,String dept_name) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            session.beginTransaction();
            Query query = session.createQuery("FROM Department where id.libraryId = :libraryId and deptName = :deptName ");
            query.setString("libraryId",library_id );
            query.setString("deptName",dept_name);
            Department dept=(Department)query.uniqueResult();
            return dept;
        }
        finally {
            session.close();
        }



}

}
