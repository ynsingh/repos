/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.systemsetupDAO;

import com.myapp.struts.hbm.*;
import com.myapp.struts.hbm.HibernateUtil;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;


public class MemberDAO {


    static Query query;




public static EmployeeType getEployeeName(String library_id,String emptype_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
       EmployeeType obj=null;
        try {
            session.beginTransaction();
            Query query = session.createQuery("FROM  EmployeeType  WHERE id.libraryId =:library_id and id.emptypeId = :emptype_id");
            query.setString("library_id", library_id);
            query.setString("emptype_id",emptype_id);

            obj= (EmployeeType) query.uniqueResult();
            session.getTransaction().commit();
        }
         catch (Exception ex)
        {
            ex.printStackTrace();



        }
        finally {
            session.close();
        }
return obj;
}

public static List<CirMemberAccount> searchAccount(String library_id,String emptype_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
       List<CirMemberAccount> obj=null;
        try {
            session.beginTransaction();
            Query query = session.createQuery("FROM  CirMemberAccount  WHERE id.libraryId =:library_id and memType = :emptype_id");
            query.setString("library_id", library_id);
            query.setString("emptype_id",emptype_id);

           obj= (List<CirMemberAccount>) query.list();
           session.getTransaction().commit();
        }
         catch (Exception ex)
        {
            ex.printStackTrace();



        }
        finally {
            session.close();
        }
return obj;
}

public static List<CirMemberAccount> searchAccount(String library_id,String emptype_id,String subemp_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<CirMemberAccount> obj=null;
        try {
            session.beginTransaction();
            Query query = session.createQuery("FROM  CirMemberAccount  WHERE id.libraryId =:library_id and memType = :emptype_id and subMemberType=:subemp_id");
            query.setString("library_id", library_id);
            query.setString("emptype_id",emptype_id);
            query.setString("subemp_id",subemp_id);
           obj= (List<CirMemberAccount>) query.list();
           session.getTransaction().commit();
        }
         catch (Exception ex)
        {
            ex.printStackTrace();



        }
        finally {
            session.close();
        }
return obj;
}

public static EmployeeType getEployeeByName(String library_id,String emptype_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
       EmployeeType obj=null;
        try {
            session.beginTransaction();
            Query query = session.createQuery("FROM  EmployeeType  WHERE id.libraryId =:library_id and id.emptypeId = :emptype_id");
            query.setString("library_id", library_id);
            query.setString("emptype_id",emptype_id);

            obj= (EmployeeType) query.uniqueResult();
            session.getTransaction().commit();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();



        }
        finally {
            session.close();
        }
return obj;
}



public static EmployeeType getEmployeeByName(String library_id,String empname) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        EmployeeType obj=null;
        try {
            session.beginTransaction();
            Query query = session.createQuery("FROM  EmployeeType  WHERE id.libraryId =:library_id and emptypeFullName = :emptype_id");
            query.setString("library_id", library_id);
            query.setString("emptype_id",empname);

            obj= (EmployeeType) query.uniqueResult();
            session.getTransaction().commit();
        }
         catch (Exception ex)
        {
            ex.printStackTrace();



        }
        finally {
            session.close();
        }
return obj;
}


public static SubEmployeeType getSubEmployeeByName(String library_id,String emptype_id,String empname) {
        Session session = HibernateUtil.getSessionFactory().openSession();
       SubEmployeeType obj=null;
        try {
            session.beginTransaction();
            Query query = session.createQuery("FROM  SubEmployeeType  WHERE id.libraryId =:library_id and id.emptypeId=:emptypeId and subEmptypeFullName = :empname");
            query.setString("library_id", library_id);
            query.setString("emptypeId", emptype_id);
            query.setString("empname",empname);

            obj= (SubEmployeeType) query.uniqueResult();
            session.getTransaction().commit();
        }
         catch (Exception ex)
        {
            ex.printStackTrace();



        }
        finally {
            session.close();
        }
return obj;
}

public static List<EmployeeType> searchEmployeeType(String library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
 List<EmployeeType> obj=null;
        try {
            session.beginTransaction();
            Query query1 = session.createQuery("FROM  EmployeeType  WHERE id.libraryId=:library_id");
            query1.setString("library_id", library_id);


            obj= (List<EmployeeType>) query1.list();
            session.getTransaction().commit();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();



        }
        finally {
            session.close();
        }
return obj;
}

public static  boolean insert(EmployeeType obj)
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
        finally {
            session.close();
        }

}






public static  boolean update(EmployeeType obj,String library_id,String emptype_full_name)
{
         Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
 Query query1 = session.createQuery("Update  EmployeeType set emptypeFullName= :emptype_full_name  WHERE id.libraryId = :library_id and id.emptypeId = :emptype_id");
query1.setString("emptype_id",obj.getId().getEmptypeId());
query1.setString("emptype_full_name",emptype_full_name);
query1.setString("library_id",library_id);
int i=query1.executeUpdate();
 tx.commit();
if(i!=0)
    return true;
else
    return false;
         

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            tx.rollback();
            return false;


        }
        finally {
            session.close();
        }

}





public static  boolean Delete(EmployeeType obj)
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


public static String getMemberByLibrary(String library_id) {
     StringBuffer dept_ids = new StringBuffer();

try {
List<EmployeeType> subemp = (List<EmployeeType>)MemberDAO.searchEmployeeType(library_id);
Iterator it = subemp.iterator();
System.out.println(subemp.size());
int tcount=0;
dept_ids.append("<emp_ids>");
while (it.hasNext())
{

//construct the xml string.
EmployeeType subemppojo = subemp.get(tcount);


   dept_ids.append("<emp_id>"+subemppojo.getId().getEmptypeId()+"</emp_id>");
   dept_ids.append("<emp_name>"+subemppojo.getEmptypeFullName()+"</emp_name>");
tcount++;
it.next();
}
dept_ids.append("</emp_ids>");
}

catch(Exception se) {
se.printStackTrace();
}

return dept_ids.toString();


}
}
