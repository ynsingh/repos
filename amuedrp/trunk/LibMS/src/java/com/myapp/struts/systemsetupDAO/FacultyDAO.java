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
public class FacultyDAO {


 
   static Query query;
  




public static Faculty getFacultyName(String library_id,String faculty_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Faculty obj = null;
        try {
            session.beginTransaction();
             query = session.createQuery("FROM  Faculty  WHERE id.libraryId =:library_id and id.facultyId = :facultyId");
            query.setString("library_id", library_id);
            query.setString("facultyId",faculty_id);

            obj= (Faculty) query.uniqueResult();
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

public static List<CirMemberAccount> searchAccountCourse(String library_id,String course_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
       List<CirMemberAccount> obj = null;
        try {
            session.beginTransaction();
            query = session.createQuery("FROM  CirMemberAccount  WHERE id.libraryId =:library_id and courseId = :course_id");
            query.setString("library_id", library_id);
            query.setString("course_id", course_id);

           obj= (List<CirMemberAccount>) query.list();
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
public static List<CirMemberAccount> searchAccount(String library_id,String faculty_id,String dept_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
      List<CirMemberAccount> obj=null;
        try {
            session.beginTransaction();
         query = session.createQuery("FROM  CirMemberAccount  WHERE id.libraryId =:library_id and facultyId = :faculty_id and deptId=:dept_id");
            query.setString("library_id", library_id);
            query.setString("faculty_id", faculty_id);
            query.setString("dept_id", dept_id);
            obj= (List<CirMemberAccount>) query.list();
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
public static List<CirMemberAccount> searchAccount(String library_id,String faculty_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
       List<CirMemberAccount>  obj=null;
        try {
            session.beginTransaction();
        query = session.createQuery("FROM  CirMemberAccount  WHERE id.libraryId =:library_id and facultyId = :faculty_id");
            query.setString("library_id", library_id);
            query.setString("faculty_id", faculty_id);

           obj= (List<CirMemberAccount>) query.list();
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
public static List<Faculty> getFaculty(String library_id,String faculty_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
         List<Faculty> obj=null;
        try {
            session.beginTransaction();
            query = session.createQuery("FROM  Faculty  WHERE id.libraryId =:library_id and id.facultyId = :facultyId");
            query.setString("library_id", library_id);
            query.setString("facultyId",faculty_id);

            obj= (List<Faculty>) query.list();
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

public static Faculty getFacultyId(String library_id,String faculty_name) {
        Session session = HibernateUtil.getSessionFactory().openSession();
       Faculty obj=null;
        try {
            session.beginTransaction();
             query = session.createQuery("FROM  Faculty  WHERE id.libraryId =:library_id and facultyName= :faculty_name");
            query.setString("library_id", library_id);
            query.setString("faculty_name",faculty_name);

           obj=  (Faculty)query.uniqueResult();
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
public static List<Faculty> getFacultyRecord(String library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
       List<Faculty> obj=null;
        try {
            session.beginTransaction();
            Query query1 = session.createQuery("FROM  Faculty  WHERE id.libraryId =:library_id");
            query1.setString("library_id", library_id);
           

            obj= (List<Faculty>) query1.list();
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
public static List<Faculty> searchFaculty(String library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
 List<Faculty> obj=null;
        try {
            session.beginTransaction();
            
            
            if(library_id!=null)
            {    query= session.createQuery("FROM  Faculty  WHERE id.libraryId=:library_id");

            query.setString("library_id", library_id);
            }else{
                query= session.createQuery("FROM  Faculty ");
            }

            obj=(List<Faculty>) query.list();
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

  public static Faculty searchFacultyName(String library_id,String faculty_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Faculty obj=null;
        try {
            session.beginTransaction();
            Query query1 = session.createQuery("FROM  Faculty  WHERE id.libraryId = :libraryId and id.facultyId = :facultyId");
            query1.setString("libraryId",library_id);
            query1.setString("facultyId",faculty_id);
            obj= (Faculty) query1.uniqueResult();
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
public static  boolean update(Faculty obj,String library_id)
{
         Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
 Query query1 = session.createQuery("Update  Faculty set id.facultyId= :faculty_id,facultyName= :faculty_name  WHERE id.libraryId = :libraryId and id.facultyId = :faculty_id");
query1.setString("faculty_id",obj.getId().getFacultyId());
query1.setString("faculty_name",obj.getFacultyName());
query1.setString("libraryId",library_id);
int i=query1.executeUpdate();
 tx.commit();
if(i!=0)
    return true;
else
    return false;
           //session.update(obj);
           
        }
        catch (RuntimeException e) {
e.printStackTrace();
                tx.rollback();
                return false;

        }

   finally
        {
          session.close();
        }

}


public static  boolean Delete(Faculty obj)
{
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.delete(obj);
            tx.commit();
        }
        catch (RuntimeException e) {
             e.printStackTrace();
                tx.rollback();
                return false;

        }
        finally
        {
          session.close();
        }

   return true;

}



public static  boolean insert(Faculty obj)
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


 public static List getMaxFacultyRecordIdNo(String library_id) {
        Session session =HibernateUtil.getSessionFactory().openSession();
      List obj=null;
        try {
            session.beginTransaction();
            query = session.createQuery("SELECT Max(id.facultyId)FROM Faculty where id.libraryId = :library_id ");
            query.setString("library_id",library_id );
           obj=  query.list();
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



  public static Faculty getFacultyRecordIdNo1(String library_id,String faculty_name) {
        Session session = HibernateUtil.getSessionFactory().openSession();
      Faculty obj=null;
        try {
            session.beginTransaction();
            query = session.createQuery("FROM Faculty where id.libraryId = :libraryId and facultyName = :facultyName ");
            query.setString("libraryId",library_id );
            query.setString("facultyName",faculty_name);
            obj= (Faculty) query.uniqueResult();
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
public String getDeptByFacultyID (String library_id,String faculty_id) {
StringBuffer dept_ids = new StringBuffer();

try {
List<Department> subemp = (List<Department>)DeptDAO.getDept(library_id, faculty_id);
Iterator it = subemp.iterator();
int tcount=0;
dept_ids.append("<dept_ids>");
while (it.hasNext())
{

//construct the xml string.
Department subemppojo = subemp.get(tcount);


   dept_ids.append("<dept_id>"+subemppojo.getId().getDeptId()+"</dept_id>");
   dept_ids.append("<dept_name>"+subemppojo.getDeptName()+"</dept_name>");
tcount++;
it.next();
}
dept_ids.append("</dept_ids>");
}

catch(Exception se) {
se.printStackTrace();
}


return dept_ids.toString();
}


public String getCourseByDeptID (String library_id,String faculty_id,String dept_id) {
StringBuffer dept_ids = new StringBuffer();

try {
List<Courses> subemp = (List<Courses>)CourseDAO.getCourse(library_id, faculty_id,dept_id);
Iterator it = subemp.iterator();
int tcount=0;
dept_ids.append("<course_ids>");
while (it.hasNext())
{

//construct the xml string.
Courses subemppojo = subemp.get(tcount);


   dept_ids.append("<course_id>"+subemppojo.getId().getCourseId()+"</course_id>");
   dept_ids.append("<course_name>"+subemppojo.getCourseName()+"</course_name>");
tcount++;
it.next();
}
dept_ids.append("</course_ids>");
}

catch(Exception se) {
se.printStackTrace();
}


return dept_ids.toString();



}



public static String getFacultyByLibrary(String library_id) {
     StringBuffer dept_ids = new StringBuffer();

try {
List<Faculty> subemp = (List<Faculty>)FacultyDAO.getFacultyRecord(library_id);
Iterator it = subemp.iterator();
System.out.println(subemp.size());
int tcount=0;
dept_ids.append("<faculty_ids>");
while (it.hasNext())
{

//construct the xml string.
Faculty subemppojo = subemp.get(tcount);


   dept_ids.append("<faculty_id>"+subemppojo.getId().getFacultyId()+"</faculty_id>");
   dept_ids.append("<faculty_name>"+subemppojo.getFacultyName()+"</faculty_name>");
tcount++;
it.next();
}
dept_ids.append("</faculty_ids>");
}

catch(Exception se) {
se.printStackTrace();
}

return dept_ids.toString();


}
}
