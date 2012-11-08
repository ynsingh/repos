/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.SerialDAO;

import com.myapp.struts.hbm.HibernateUtil;
import com.myapp.struts.hbm.SerBiolioDetails;
import com.myapp.struts.hbm.SerLanguage;
import com.myapp.struts.hbm.SerNewEntry;
import com.myapp.struts.hbm.SerPublisher;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author edrp02
 */
public class SerialDAO {


    public   SerNewEntry getSerBiblio(String library_id, String sub_library_id, String new_serial_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
       SerNewEntry obj=null;

        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(SerNewEntry.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.sublibraryId", sub_library_id))
                    .add(Restrictions.eq("id.newSerialId", new_serial_id)));
            obj= (SerNewEntry) criteria.uniqueResult();
            session.getTransaction().commit();
        }
        catch(Exception e)
        {
        e.printStackTrace();
        }
        finally {
            session.close();
        }
        return obj;
    }

public  List getBiblio(String library_id,String sublibrary_id,String search_by, String search_keyword, String sort_by) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List obj=null;

        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(SerNewEntry.class)
                    
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.sublibraryId", sublibrary_id))
                    .add(Restrictions.ilike(search_by,search_keyword+"%"))
                    .addOrder(Property.forName(sort_by).asc());
            obj= (List) criteria.list();
            session.getTransaction().commit();
        }   catch(Exception e)
        {
        e.printStackTrace();
        }
        finally {
            session.close();
        }
        return obj;
    }


    

static Integer id=0;
    public  Integer returnMaxSerialId(String library_id, String sublibrary_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx=session.beginTransaction();

        try {

            Criteria criteria = session.createCriteria(SerNewEntry.class);
            Criterion a = Restrictions.eq("id.libraryId", library_id);
            Criterion b = Restrictions.eq("id.sublibraryId", sublibrary_id);
            LogicalExpression le = Restrictions.and(a, b);
            String maxbiblio = (String)criteria.add(le).setProjection(Projections.max("id.newSerialId")).uniqueResult();
            System.out.println("!!!!!!!!!!!!"+maxbiblio);

            if(maxbiblio==null)
            {
             id=0;
            }
            else
            {
             id=Integer.parseInt(maxbiblio);
            }

            if (id == 0) {
                id = 1;
            } else {
                id++;
            }

            // Integer maxbiblio = Integer.parseInt((String)criteria.add(le).setProjection(Projections.max("id.newSerialId")).uniqueResult());
//            if (maxbiblio == null) {
//                maxbiblio = 1;
//            } else {
//                maxbiblio++;
//            }

            return id;
        } finally {
            session.close();
        }

    }
  public List searchSerial(String library_id, String sub_library_id,String title) {
        Session session = HibernateUtil.getSessionFactory().openSession();
         Transaction tx=session.beginTransaction();
        try {
            Criteria criteria = session.createCriteria(SerNewEntry.class)
                    .add(Restrictions.conjunction().add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.sublibraryId", sub_library_id))
                    .add(Restrictions.eq("title",title)));
            return criteria.list();
        } finally {
           // session.close();
        }
    }



   

   public Integer returnMaxControlNo(String library_id, String sublibrary_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
           Integer maxbiblio=null;
        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(SerBiolioDetails.class);
            Criterion a = Restrictions.eq("id.libraryId", library_id);
            Criterion b = Restrictions.eq("id.sublibraryId", sublibrary_id);
            LogicalExpression le = Restrictions.and(a, b);
             maxbiblio = (Integer) criteria.add(le).setProjection(Projections.max("id.serControlNo")).uniqueResult();
                System.out.println(maxbiblio+"###################");
            if (maxbiblio == null) {
                maxbiblio = 1;
            } else {
                maxbiblio++;
            }
          
session.getTransaction().commit();

        } catch(Exception e){
        e.printStackTrace();
        }
        finally
        {
        session.close();
        }
       return maxbiblio;
    }



    public  void insert1(SerBiolioDetails bibDetails) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(bibDetails);
            tx.commit();
        } catch (Exception e) {

            tx.rollback();
           e.printStackTrace();
        } finally {
            session.close();
        }
    }
        
public  boolean insertSerialNewEntry(SerNewEntry bibDetails) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(bibDetails);
            tx.commit();
        } catch (Exception e) {

            tx.rollback();
           e.printStackTrace();
           return false;
        } finally {
            session.close();
        }
        return true;
    }



    


    public  SerNewEntry searchSerialTitle(String library_id, String sub_library_id,String title) {
        Session session = HibernateUtil.getSessionFactory().openSession();
       SerNewEntry obj=null;
        try {
              session.beginTransaction();
            Criteria criteria = session.createCriteria(SerNewEntry.class)
                    .add(Restrictions.conjunction().add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.sublibraryId", sub_library_id))
                    .add(Restrictions.eq("title",title)));
            obj=(SerNewEntry) criteria.uniqueResult();
            session.getTransaction().commit();
        }  catch(Exception e)
        {
        e.printStackTrace();
        }
        finally {
            session.close();
        }
        return obj;
    }


     
     public  void insert1(SerNewEntry obj) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx =(Transaction) session.beginTransaction();
            session.save(obj);
            tx.commit();
        } catch (RuntimeException e) {
            //if(bibDetails != null)
            tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    public  SerLanguage searchLanguage(String library_id, String language_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
SerLanguage obj=null;
        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(SerLanguage.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.languageId", language_id)));
            obj= (SerLanguage) criteria.uniqueResult();
            session.getTransaction().commit();


        }   catch(Exception e)
        {
        e.printStackTrace();
        }
        finally {
            session.close();
        }
        return obj;
    }



   public  SerPublisher searchSerialPubisher(String library_id, String sub_library_id,String pub_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
         SerPublisher  obj=null;

        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(SerPublisher.class)
                    .add(Restrictions.conjunction().add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.sublibraryId", sub_library_id))
                    .add(Restrictions.eq("id.pubId",pub_id)));
            obj= (SerPublisher) criteria.uniqueResult();
            session.getTransaction().commit();
        }   catch(Exception e)
        {
        e.printStackTrace();
        }
        finally {
            session.close();
        }
        return obj;
    }


     public  boolean insert(SerLanguage obj) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;


        try {
            tx = (Transaction) session.beginTransaction();

            session.save(obj);
            tx.commit();



        }   catch(Exception e)
        {
        e.printStackTrace();
        tx.rollback();
        return false;
        }
        finally {
            session.close();
        }
        return true;
    }

     public  boolean insert(SerPublisher obj) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;


        try {
            tx = (Transaction) session.beginTransaction();

            session.save(obj);
            tx.commit();



        }  catch(Exception e)
        {
        e.printStackTrace();
        tx.rollback();
        return false;
        }
        finally {
            session.close();
        }
        return true;
    }


     public  boolean update1(SerNewEntry obj) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.update(obj);
            tx.commit();
        }  catch(Exception e)
        {
        e.printStackTrace();
        tx.rollback();
        return false;
        }
        finally {
            session.close();
        }
        return true;
    }
 public  boolean update1(SerLanguage obj) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.update(obj);
            tx.commit();
        }  catch(Exception e)
        {
        e.printStackTrace();
        tx.rollback();
        return false;
        }
        finally {
            session.close();
        }
        return true;
    }

  public  boolean update(SerPublisher obj) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.update(obj);
            tx.commit();
        }   catch(Exception e)
        {
        e.printStackTrace();
        tx.rollback();
        return false;
        }
        finally {
            session.close();
        }
        return true;
    }
public  boolean update(SerLanguage obj) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.update(obj);
            tx.commit();
        }   catch(Exception e)
        {
        e.printStackTrace();
        tx.rollback();
        return false;
        }
        finally {
            session.close();
        }
        return true;
    }


    public   boolean update8(SerBiolioDetails obj) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.update(obj);
            tx.commit();
        }  catch(Exception e)
        {
        e.printStackTrace();
        tx.rollback();
        return false;
        }
        finally {
            session.close();
        }
        return true;
    }



    public   boolean update3(SerNewEntry obj) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.update(obj);
            tx.commit();
        }  catch(Exception e)
        {
        e.printStackTrace();
        tx.rollback();
        return false;
        }
        finally {
            session.close();
        }
        return true;
    }


     public  boolean delete(SerLanguage obj) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.delete(obj);
            tx.commit();
        }   catch(Exception e)
        {
        e.printStackTrace();
        tx.rollback();
        return false;
        }
        finally {
            session.close();
        }
        return true;
    }



     public boolean delete(SerPublisher obj) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.delete(obj);
            tx.commit();
        }   catch(Exception e)
        {
        e.printStackTrace();
        tx.rollback();
        return false;
        }
        finally {
            session.close();
        }
        return true;
    }


   public void delete2(String serial_id, String library_id, String sub_library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
       

        try {
            tx = session.beginTransaction();
       
            Query query = session.createQuery("DELETE FROM SerNewEntry  WHERE  id.newSerialId = :newSerialId and id.libraryId = :libraryId and id.sublibraryId = :sublibraryId");

            query.setString("newSerialId", serial_id);
            query.setString("libraryId", library_id);
            query.setString("sublibraryId", sub_library_id);
            query.executeUpdate();
            tx.commit();
            
        }   catch(Exception e)
        {
        e.printStackTrace();
        tx.rollback();

        }
        finally {
            session.close();
        }
        
    }

}
