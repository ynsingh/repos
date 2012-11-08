/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.systemsetupDAO;

import com.myapp.struts.hbm.HibernateUtil;
import com.myapp.struts.hbm.*;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author edrp01
 */
public class MemberCategoryDAO {
    public  List<EmployeeType> searchEmpType(String library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
List<EmployeeType> obj=null;
        try {
            session.beginTransaction();
            Query query1;
            if(library_id!=null){
            query1 = session.createQuery("FROM  EmployeeType  WHERE id.libraryId = :library_id");
            query1.setString("library_id", library_id);
            }
            else
                query1 = session.createQuery("FROM  EmployeeType");

            obj= (List<EmployeeType>) query1.list();
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

            public  SubEmployeeType searchSubMemberType(String library_id,String memtype_id,String submemtype_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
SubEmployeeType obj=null;
        try {
            session.beginTransaction();
            Query query1;

            query1 = session.createQuery("FROM  SubEmployeeType  WHERE id.libraryId = :library_id and id.emptypeId=:memtype_id and id.subEmptypeId=:submemtype_id");
            query1.setString("library_id", library_id);
            query1.setString("memtype_id", memtype_id);
            query1.setString("submemtype_id", submemtype_id);


            obj= (SubEmployeeType)query1.uniqueResult();
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



     public  List<SubEmployeeType> searchSubEmpType(String library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
List<SubEmployeeType> obj=null;
        try {
            session.beginTransaction();
            Query query1;
           if(library_id!=null){
            query1 = session.createQuery("FROM  SubEmployeeType  WHERE id.libraryId = :library_id");
            query1.setString("library_id", library_id);

           }else{
           query1 = session.createQuery("FROM  SubEmployeeType ");
           }
           obj=(List<SubEmployeeType>) query1.list();
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
public  EmployeeType searchMemberType(String library_id,String memtype_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
EmployeeType obj=null;
        try {
            session.beginTransaction();
            Query query1;

            query1 = session.createQuery("FROM  EmployeeType  WHERE id.libraryId = :library_id and id.emptypeId=:memtype_id");
            query1.setString("library_id", library_id);
            query1.setString("memtype_id", memtype_id);


            obj=(EmployeeType) query1.uniqueResult();
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




     public  List<SubEmployeeType> searchSubEmpTypeByEmpId(String library_id,String empTypeId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
List<SubEmployeeType> obj=null;
        try {
            session.beginTransaction();
            Query query1;
            

                    query1 = session.createQuery("FROM  SubEmployeeType  WHERE id.libraryId = :library_id and id.emptypeId=:empTypeId");
            query1.setString("library_id", library_id);
            query1.setString("empTypeId", empTypeId);

           obj= (List<SubEmployeeType>) query1.list();
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
