/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.systemsetupDAO;
import com.myapp.struts.hbm.*;
import java.util.*;
import com.myapp.struts.hbm.HibernateUtil;
import org.hibernate.Transaction;
import org.hibernate.Session;
import org.hibernate.Query;

/**
 * Developed By : Kedar Kumar
 * Modified By  : 18-Feb-2011
 * Use to Check the Multiple occurance of Library_Id in Library POJO
 */
public class CourseDAO {

public static List<SubLibrary> searchSubLib(String library_id,String sublibrary_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
List<SubLibrary> obj=null;
        try {
            session.beginTransaction();
            Query query1 = session.createQuery("FROM  SubLibrary  WHERE id.sublibraryId != :sublibrary_id and id.libraryId= :library_id");
            query1.setString("library_id", library_id);
            query1.setString("sublibrary_id", sublibrary_id);

            obj=(List<SubLibrary>) query1.list();
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

public static Courses searchCourseByName(String library_id,String faculty_id,String dept_id,String name) {
        Session session = HibernateUtil.getSessionFactory().openSession();
Courses obj=null;
        try {
               
            session.beginTransaction();
            Query query1 = session.createQuery("FROM  Courses  WHERE id.libraryId=:library_id and id.facultyId=:faculty_id and id.deptId=:dept_id and courseName = :course_id");
            query1.setString("library_id",library_id);
            query1.setString("faculty_id",faculty_id);
            query1.setString("dept_id",dept_id);
            query1.setString("course_id",name);
           
            obj= (Courses) query1.uniqueResult();
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

  public static Courses searchCourseName(String library_id,String faculty_id,String dept_id,String course_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
Courses obj=null;
        try {
               
            session.beginTransaction();
            Query query1 = session.createQuery("FROM  Courses  WHERE id.libraryId=:library_id and id.facultyId=:faculty_id and id.deptId=:dept_id and id.courseId = :course_id");
            query1.setString("library_id",library_id);
            query1.setString("faculty_id",faculty_id);
            query1.setString("dept_id",dept_id);
            query1.setString("course_id",course_id);
           
            obj= (Courses) query1.uniqueResult();
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
public static  boolean update(Department obj)
{
         Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.update(obj);
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
public static  boolean update1(Courses obj)
{
         Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.update(obj);
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


public static  boolean Delete(Courses obj)
{
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.delete(obj);
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



public static  boolean insert(Courses obj)
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
        ex.printStackTrace();
        tx.rollback();
            return false;

         

        }
        finally
        {
          session.close();
        }
   

}


 public static List getMaxCourseRecordIdNo(String library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        List obj=null;
        try {
            session.beginTransaction();
            Query query = session.createQuery("SELECT Max(id.courseId)FROM Courses where id.libraryId = :library_id ");
            query.setString("library_id",library_id );
            obj=query.list();
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



  public static Department getDeptRecordIdNo(String library_id,String dept_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
       Department obj=null;
        try {
            session.beginTransaction();
            Query query = session.createQuery("FROM Department where id.libraryId = :libraryId and id.deptId = :deptId ");
            query.setString("libraryId",library_id );
            query.setString("deptId",dept_id );
           obj=(Department)query.uniqueResult();
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
public static List<Courses> getCourse(String library_id,String faculty_id,String dept_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Courses> obj=null;
        try {
            session.beginTransaction();
            Query query = session.createQuery("FROM Courses where id.libraryId = :libraryId and id.deptId = :deptId and id.facultyId=:faculty_id ");
            query.setString("libraryId",library_id );
            query.setString("deptId",dept_id );
            query.setString("faculty_id",faculty_id );
          obj=(List<Courses>)query.list();
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


public static List<Courses> getCourse(String library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Courses> obj=null;
        try {
            session.beginTransaction();
            Query query ;

            if(library_id!=null)
            {
            query= session.createQuery("FROM Courses where id.libraryId = :libraryId");
            query.setString("libraryId",library_id );
            }else{
            query= session.createQuery("FROM Courses");

            }

            
           obj= query.list();
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
}
