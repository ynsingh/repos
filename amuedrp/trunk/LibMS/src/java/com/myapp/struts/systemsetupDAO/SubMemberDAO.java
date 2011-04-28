/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.systemsetupDAO;
import com.myapp.struts.hbm.*;
import com.myapp.struts.hbm.HibernateUtil;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Criteria;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;


public class SubMemberDAO {


    static Query query;


 public static  SubEmployeeType searchIssueLimit(String library_id,String emptype_id,String subemptype_id)
{
        Session session = HibernateUtil.getSessionFactory().openSession();

        try
        {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(SubEmployeeType.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.emptypeId", emptype_id))
                    .add(Restrictions.eq("id.subEmptypeId", subemptype_id))
                  );
            return (SubEmployeeType) criteria.uniqueResult();


        }
        finally
        {
           session.close();
        }
}


public static SubEmployeeType getSubEployeeName(String library_id,String emptype_id,String sub_emptype_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            session.beginTransaction();
            Query query = session.createQuery("FROM  SubEmployeeType  WHERE id.libraryId =:library_id and id.subEmptypeId = :sub_emptype_id and id.emptypeId=:emptype_id");
            query.setString("library_id", library_id);
            query.setString("sub_emptype_id",sub_emptype_id);
            query.setString("emptype_id",emptype_id);

            return (SubEmployeeType) query.uniqueResult();
        }
        finally {
            session.close();
        }

}


public static List<SubEmployeeType> searchSubEmployeeType(String library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();
            Query query1 = session.createQuery("FROM  SubEmployeeType  WHERE id.libraryId=:library_id");
            query1.setString("library_id", library_id);


            return (List<SubEmployeeType>) query1.list();
        }
        finally {
            session.close();
        }

}
public static List<SubEmployeeType> searchSubEmployeeType(String library_id,String emptype_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();
            Query query1 = session.createQuery("FROM  SubEmployeeType  WHERE id.libraryId=:library_id and id.emptypeId=:emptype_id");
            query1.setString("library_id", library_id);
            query1.setString("emptype_id",emptype_id);

            return (List<SubEmployeeType>) query1.list();
        }
        finally {
            session.close();
        }

}

public static  boolean insert(SubEmployeeType obj)
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
public static  boolean Update(SubEmployeeType obj,List<CirMemberAccount> obj1)
{
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;


        try
        {
            tx = (Transaction) session.beginTransaction();

            session.update(obj);
            if(obj1!=null && !obj1.isEmpty())
            {
                Iterator it=obj1.iterator();
                while(it.hasNext())
                {
                 CirMemberAccount obj2 = (CirMemberAccount)it.next();
                session.update(obj2);
                }
            }
            tx.commit();

            return true;

        }
        catch (Exception ex)
        {
        System.out.println(ex.toString());
             return false;

       

        }
        finally
        {
          session.close();
        }


}
public static  boolean Update(SubEmployeeType obj)
{
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;


        try
        {
            tx = (Transaction) session.beginTransaction();

            session.update(obj);
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





public static  boolean Delete(SubEmployeeType obj)
{
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.delete(obj);
            tx.commit();
        }
        catch (RuntimeException e) {
                System.out.println("FacultyDAO.Delete():*****"+e);
                tx.rollback();
                return false;

        }
        finally
        {
          session.close();
        }

   return true;

}



}
