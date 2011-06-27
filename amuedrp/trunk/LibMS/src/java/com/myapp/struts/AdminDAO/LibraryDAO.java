/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.AdminDAO;
import com.myapp.struts.admin.AdminReg_Institute;
import com.myapp.struts.hbm.*;
import com.myapp.struts.hbm.HibernateUtil;
import java.util.List;
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
public class LibraryDAO {


   static  Integer maxNewRegId;
   static Query query;
   public void insert(Library instituteDetails){
    Session session =null;
    Transaction tx = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.save(instituteDetails);
            tx.commit();
        }
        catch (RuntimeException e) {
            if(instituteDetails != null)
                tx.rollback();
            throw e;
        }
        finally {
        //  session.close();
        }
    }
public void update(Library instituteDetails) {
        Session session =null;
    Transaction tx = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.update(instituteDetails);
            tx.commit();
        }
        catch (RuntimeException e) {
          //  if(bibDetails != null)
                tx.rollback();
            throw e;
        }
        finally {
       session.close();
        }
    }
public void delete(int instituteId) {
        Session session =null;
    Transaction tx = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            //acqdocentry = session.load(BibliographicDetails.class, id);
            Query query = session.createQuery("DELETE FROM Library  WHERE  id.instituteId = :instituteId");

            query.setInteger("instituteId",instituteId );

            query.executeUpdate();
            tx.commit();
            //return (BibliographicDetails) query.uniqueResult();
        }

        finally {
            //session.close();
        }
    }

public Integer getInstituteRequestCount(String status){
        Session session = null;
    try {
        session = HibernateUtil.getSessionFactory().openSession();
        //Transaction tx = null;
        Criteria criteria = session.createCriteria(Library.class)
                 .setProjection(Projections.count("id.libraryId"));
        criteria.add(Restrictions.eq("status",status ));
            Integer countrequest = Integer.parseInt((String)criteria.uniqueResult());

    //Session session = HibernateUtil.getSessionFactory().openSession();


            return countrequest;
        }
        finally {
          //  session.close();
        }
}
public List getInstituteDetailsByStatus(String institute_id, String status){
  Session session =null;
    Transaction tx = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("FROM Library where id.libraryId = :libraryId and working_status = :status");
            query.setString("libraryId",institute_id );
            query.setString("status",status );

            return (List)query.list();
        }
        finally {
          //  session.close();
        }
}
public Library getInstituteDetails(String instituteId){
  Session session =null;
    Transaction tx = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("FROM Library where id.libraryId = :instituteId");
            query.setString("instituteId", instituteId);
            return (Library)query.uniqueResult();
        }
        finally {
            session.close();
        }
}
public Library getInstituteDetailsByRegistrationId(Integer RegistrationId){
  Session session =null;
    Transaction tx = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("FROM Library where registrationId = :registrationId");
            query.setInteger("registrationId", RegistrationId);
            return (Library)query.uniqueResult();
        }
        finally {
            session.close();
        }
}

public List getLibrarySearch(String search_by, String search_keyword, String sort_by){
  Session session =null;
    Transaction tx = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            String sql="";
            if (search_by.equals("institute_id")){
            sql = "select a.*,b.* from admin_registration a left outer join library b on a.registration_id=b.registration_id  where b."+search_by+" like '"+search_keyword +"%' order by a."+sort_by+" asc";}
            else{
            sql = "select a.*,b.* from admin_registration a left outer join library b on a.registration_id=b.registration_id  where a."+search_by+" like '"+search_keyword +"%' order by a."+sort_by+" asc";}
            System.out.println(sql);

          Query query =  session.createSQLQuery(sql)
                    .addEntity(AdminRegistration.class)
                    .addEntity(Library.class)
                    .setResultTransformer(Transformers.aliasToBean(AdminReg_Institute.class));
            return query.list();
        }
        finally {
         //   session.close();
        }
}


public Integer getLibraryRequestCount(){
        Session session = null;
    try {
        session = HibernateUtil.getSessionFactory().openSession();
        //Transaction tx = null;
        Criteria criteria = session.createCriteria(Library.class)
                 .setProjection(Projections.count("id.libraryId"));
       // criteria.add(Restrictions.eq("status",status ));
            Integer countrequest = Integer.parseInt((String)criteria.uniqueResult());

    //Session session = HibernateUtil.getSessionFactory().openSession();


            return countrequest;
        }
        finally {
          //  session.close();
        }
}




public static  List<Library> searchAllLibrary()
{
        Session session = HibernateUtil.getSessionFactory().openSession();

        try
        {
            session.beginTransaction();
            query = session.createQuery("FROM  Library where id.libraryId !=:lib");
            query.setString("lib","libms");



        }
        catch(Exception e)
        {
        System.out.println(e.toString());
        }
        finally
        {
         //   session.close();
        }
        return (List<Library>) query.list();

}



  
public static  Library searchLibraryID(String library_id)
{
        Session session = HibernateUtil.getSessionFactory().openSession();

        try
        {
            session.beginTransaction();
            query = session.createQuery("FROM  Library  WHERE libraryId =:library_id  ");
            query.setString("library_id", library_id);
            
            
        }
        catch(Exception e)
        {
        System.out.println(e.toString());
        }
        finally
        {
         //   session.close();
        }
        return ( Library) query.uniqueResult();

}

public static  Library searchBlockLibrary(String library_id)
{
        Session session = HibernateUtil.getSessionFactory().openSession();

        try
        {
            session.beginTransaction();
            query = session.createQuery("FROM  Library  WHERE libraryId =:library_id and workingStatus =:working_status ");
            query.setString("library_id", library_id);
            query.setString("working_status", "Blocked");

        }
        catch(Exception e)
        {
        System.out.println(e.toString());
        }
        finally
        {
         //  session.close();
        }
        return ( Library) query.uniqueResult();

}

public static  Library getLibraryName(String library_id)
{
        Session session = HibernateUtil.getSessionFactory().openSession();

        try
        {
            session.beginTransaction();
            query = session.createQuery("FROM  Library  WHERE libraryId =:library_id  ");
            query.setString("library_id", library_id);


        }
        catch(Exception e)
        {
        System.out.println(e.toString());
        }
        finally
        {
       //    session.close();
        }
        return ( Library) query.uniqueResult();

}

public static  Library getLibraryNameByID(String library_name)
{
        Session session = HibernateUtil.getSessionFactory().openSession();

        try
        {
            session.beginTransaction();
            query = session.createQuery("FROM  Library  WHERE libraryName =:library_name");
            query.setString("library_name", library_name);


        }
        catch(Exception e)
        {
        System.out.println(e.toString());
        }
        finally
        {
         //   session.close();
        }
        return ( Library) query.uniqueResult();

}

public static  boolean insert1(Library obj)
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


}
