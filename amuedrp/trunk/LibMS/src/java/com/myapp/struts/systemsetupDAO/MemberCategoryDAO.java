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
    public static List<EmployeeType> searchEmpType(String library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();
            Query query1;
            if(library_id!=null){
            query1 = session.createQuery("FROM  EmployeeType  WHERE id.libraryId = :library_id");
            query1.setString("library_id", library_id);
            }
            else
                query1 = session.createQuery("FROM  EmployeeType");

            return (List<EmployeeType>) query1.list();
        }
        finally {
           session.close();
        }

}

            public static SubEmployeeType searchSubMemberType(String library_id,String memtype_id,String submemtype_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();
            Query query1;

            query1 = session.createQuery("FROM  SubEmployeeType  WHERE id.libraryId = :library_id and id.emptypeId=:memtype_id and id.subEmptypeId=:submemtype_id");
            query1.setString("library_id", library_id);
            query1.setString("memtype_id", memtype_id);
            query1.setString("submemtype_id", submemtype_id);


            return (SubEmployeeType) query1.uniqueResult();
        }
        finally {
           session.close();
        }

}



     public static List<SubEmployeeType> searchSubEmpType(String library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();
            Query query1;
           if(library_id!=null){
            query1 = session.createQuery("FROM  SubEmployeeType  WHERE id.libraryId = :library_id");
            query1.setString("library_id", library_id);

           }else{
           query1 = session.createQuery("FROM  SubEmployeeType ");
           }
            return (List<SubEmployeeType>) query1.list();
        }
        finally {
           session.close();
        }

}
public static EmployeeType searchMemberType(String library_id,String memtype_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();
            Query query1;

            query1 = session.createQuery("FROM  EmployeeType  WHERE id.libraryId = :library_id and id.emptypeId=:memtype_id");
            query1.setString("library_id", library_id);
            query1.setString("memtype_id", memtype_id);


            return (EmployeeType) query1.uniqueResult();
        }
        finally {
           session.close();
        }

}




     public static List<SubEmployeeType> searchSubEmpTypeByEmpId(String library_id,String empTypeId) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();
            Query query1;
            

                    query1 = session.createQuery("FROM  SubEmployeeType  WHERE id.libraryId = :library_id and id.emptypeId=:empTypeId");
            query1.setString("library_id", library_id);
            query1.setString("empTypeId", empTypeId);

            return (List<SubEmployeeType>) query1.list();
        }
        finally {
           session.close();
        }

}
}
