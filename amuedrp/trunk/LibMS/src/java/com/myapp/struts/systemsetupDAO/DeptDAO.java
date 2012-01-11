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
List<SubLibrary> obj=null;
        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(SubLibrary.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.ne("id.sublibraryId", sublibrary_id))
                    );
            obj= (List<SubLibrary>) criteria.list();


        }
        catch(Exception e){
        e.printStackTrace();
        }
        finally {
            session.close();
        }
        return obj;
}

   public static List<Department> getDeptLibrary(String library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
       List<Department> obj=null;
        try {
            session.beginTransaction();
           query = session.createQuery("FROM  Department  WHERE id.libraryId =:library_id");
            query.setString("library_id", library_id);


           obj=  query.list();
        }
         catch(Exception e){
        e.printStackTrace();
        }
        finally {
            session.close();
        }
        return obj;
}
   public static List<SubLibrary> listsub1(String library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
List<SubLibrary> obj=null;
        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(SubLibrary.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    );
            obj= (List<SubLibrary>) criteria.list();


        }   catch(Exception e){
        e.printStackTrace();
        }
        finally {
            session.close();
        }
        return obj;
}
public static Department getDeptId(String library_id,String faculty_id,String dept_name) {
        Session session = HibernateUtil.getSessionFactory().openSession();
       Department obj=null;
        try {
            session.beginTransaction();
            Query query = session.createQuery("FROM  Department  WHERE id.libraryId =:library_id and id.facultyId=:faculty_id and deptName = :dept_name");
            query.setString("library_id", library_id);
            query.setString("dept_name",dept_name);
            query.setString("faculty_id",faculty_id);

           obj= (Department) query.uniqueResult();
        }
         catch(Exception e){
        e.printStackTrace();
        }
        finally {
            session.close();
        }
        return obj;
}

public static Department getDeptName(String library_id,String dept_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Department obj=null;
        try {
            session.beginTransaction();
            Query query = session.createQuery("FROM  Department  WHERE id.libraryId =:library_id and id.deptId = :deptId");
            query.setString("library_id", library_id);
            query.setString("deptId",dept_id);

           obj= (Department) query.uniqueResult();
        }
          catch(Exception e){
        e.printStackTrace();
        }
        finally {
            session.close();
        }
        return obj;
}
public static Department getDeptByFaculty(String library_id,String faculty_id,String dept_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
       Department obj=null;
        try {
            session.beginTransaction();
            Query query = session.createQuery("FROM  Department  WHERE id.libraryId =:library_id and  id.facultyId=:faculty_id and id.deptId = :deptId");
            query.setString("library_id", library_id);
            query.setString("faculty_id", faculty_id);
            query.setString("deptId",dept_id);

            obj= (Department) query.uniqueResult();
        }
        catch(Exception e){
        e.printStackTrace();
        }
        finally {
            session.close();
        }
        return obj;
}
public static List<Department> getDept(String library_id,String faculty_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Department> obj=null;
        try {
            session.beginTransaction();
            Query query = session.createQuery("FROM  Department  WHERE id.libraryId =:library_id and id.facultyId = :faculty_id");
            query.setString("library_id", library_id);
            query.setString("faculty_id",faculty_id);

            obj=  query.list();
        }
        catch(Exception e){
        e.printStackTrace();
        }
        finally {
            session.close();
        }
        return obj;
}

public static List getDeptRecord(String library_id,String faculty_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
       List obj=null;
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
            obj= query.list();
        }
         catch(Exception e){
        e.printStackTrace();
        }
        finally {
            session.close();
        }
        return obj;
}



public static List<Department> searchDept(String library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
      List<Department> obj = null;
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

            obj=(List<Department>) query1.list();
        }
         catch(Exception e){
        e.printStackTrace();
        }
        finally {
            session.close();
        }
        return obj;
}
public static List<SubLibrary> searchSubLib(String library_id,String sublibrary_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
List<SubLibrary> obj=null;
        try {
            session.beginTransaction();
            Query query1 = session.createQuery("FROM  SubLibrary  WHERE id.sublibraryId !=:sublibrary_id and id.libraryId=:library_id");
            query1.setString("library_id", library_id);
            query1.setString("sublibrary_id", sublibrary_id);

            obj= (List<SubLibrary>) query1.list();
        }
         catch(Exception e){
        e.printStackTrace();
        }
        finally {
            session.close();
        }
        return obj;
}
  public static SubLibrary searchLibraryName(String sublibrary_name) {
        Session session = HibernateUtil.getSessionFactory().openSession();
SubLibrary obj=null;
        try {
            session.beginTransaction();
            Query query1 = session.createQuery("FROM  SubLibrary  WHERE sublibName=:sublibraryname");
           
            query1.setString("sublibraryname",sublibrary_name);

            obj= (SubLibrary) query1.uniqueResult();
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
        catch (Exception e) {

                tx.rollback();
                e.printStackTrace();
                return false;

        }
finally{
session.close();
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
                e.printStackTrace();
                return false;

        }
        finally{
        session.close();
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
        catch (Exception e) {

                tx.rollback();
                e.printStackTrace();
                return false;

        }
        finally{
        session.close();
        }

   

}



 public static List getMaxDeptRecordIdNo(String library_id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        List obj=null;
        try {
            session.beginTransaction();
            Query query = session.createQuery("SELECT Max(id.deptId)FROM Department where id.libraryId = :library_id ");
            query.setString("library_id",library_id );
            obj=  query.list();
        }
        catch (Exception e) {

               
                e.printStackTrace();
               

        }
        finally{
        session.close();
        }

   return obj;

}




  public static Department getDeptRecordIdNo(String library_id,String dept_id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
      Department obj=null;
        try {
            session.beginTransaction();
            query = session.createQuery("FROM Department where id.libraryId = :libraryId and id.deptId = :deptId ");
            query.setString("libraryId",library_id );
            query.setString("deptId",dept_id );
            obj=(Department)query.uniqueResult();
            
        }
         catch (RuntimeException e) {

                
                e.printStackTrace();
                

        }
        finally{
        session.close();
        }

   return obj;

}



  public static Department getDeptRecordId(String library_id,String dept_name) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
       Department obj=null;
        try {
            session.beginTransaction();
            query = session.createQuery("FROM Department where id.libraryId = :libraryId and deptName = :deptName ");
            query.setString("libraryId",library_id );
            query.setString("deptName",dept_name);
           obj=(Department)query.uniqueResult();
            
        }
          catch (RuntimeException e) {

                
                e.printStackTrace();
                

        }
        finally{
        session.close();
        }

   return obj;

}


}
