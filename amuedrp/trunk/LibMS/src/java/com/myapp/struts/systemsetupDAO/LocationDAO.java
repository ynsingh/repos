/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.systemsetupDAO;

import com.myapp.struts.hbm.DocumentDetails;
import com.myapp.struts.hbm.HibernateUtil;
import com.myapp.struts.hbm.Location;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author EdRP-05
 */
public class LocationDAO {
     public static List<Location> getLocation(String library_id,String sublibrary_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            session.beginTransaction();
            Query query1 = session.createQuery("FROM  Location  WHERE id.libraryId =:library_id and id.sublibraryId = :sublibrary_id ");
            query1.setString("library_id", library_id);
            query1.setString("sublibrary_id", sublibrary_id);

            return (List<Location>) query1.list();
        }
        finally {
            session.close();
        }

}

      public static List<DocumentDetails> Search(String library_id,String loc) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            session.beginTransaction();
            Query query1 = session.createQuery("FROM  DocumentDetails  WHERE id.libraryId =:library_id and location = :loc ");
            query1.setString("library_id", library_id);
            query1.setString("loc", loc);

            return (List<DocumentDetails>)  query1.list();
        }
        finally {
            session.close();
        }

}

public static Location getLocationByName(String library_id,String sublibrary_id,String locName) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            session.beginTransaction();
            Query query1 = session.createQuery("FROM  Location  WHERE id.libraryId =:library_id and locationName=:locationName  and id.sublibraryId = :sublibrary_id ");
            query1.setString("library_id", library_id);
            query1.setString("sublibrary_id", sublibrary_id);
            query1.setString("locationName", locName);

            return (Location) query1.uniqueResult();
        }
        finally {
            session.close();
        }

}
    public static boolean insert(Location obj) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;


        try {
            tx = (Transaction) session.beginTransaction();

            session.save(obj);
            tx.commit();



        } catch (Exception ex) {
            return false;

            //  System.out.println(ex.toString());

        } finally {
            //session.close();
        }
        return true;

    }
        public static boolean update(Location obj) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.update(obj);
            tx.commit();
        } catch (RuntimeException e) {

            tx.rollback();
            return false;

        }

        return true;

    }
        public static boolean delete(String library_id,String sublibrary_id,String location_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;


        try {
            tx = (Transaction) session.beginTransaction();
            Query query = session.createQuery("Delete From  Location where id.libraryId = :library_id and id.sublibraryId = :sublibraryId and id.locationId= :locationId");
           query.setString("library_id", library_id);
            query.setString("sublibraryId", sublibrary_id);
            query.setString("locationId", location_id);
            query.executeUpdate();
            tx.commit();



        } catch (Exception ex) {
            System.out.println(ex);
            return false;

            //  System.out.println(ex.toString());

        } finally {
            // session.close();
        }
        return true;

    }
    public static Location searchLocation(String library_id, String sublibrary_id, String location_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(Location.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.sublibraryId", sublibrary_id))
                    .add(Restrictions.eq("id.locationId", location_id)));
            return (Location) criteria.uniqueResult();


        } finally {
            session.close();
        }
    }
    public static List<Location> listlocation(String library_id, String sublibrary_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(Location.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.sublibraryId", sublibrary_id)));
            return (List<Location>) criteria.list();


        } finally {
            session.close();
        }
}
}
