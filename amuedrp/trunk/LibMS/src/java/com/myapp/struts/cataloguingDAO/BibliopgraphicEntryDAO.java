/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.struts.cataloguingDAO;

import com.myapp.struts.hbm.AccessionRegister;
import com.myapp.struts.hbm.BibliographicDetails;
import com.myapp.struts.hbm.AcqFinalDemandList;
import com.myapp.struts.hbm.BibliographicDetailsLang;
import com.myapp.struts.hbm.DocumentDetails;
import com.myapp.struts.hbm.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Query;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author <a href="mailto:asif633@gmail.com">Asif Iqubal</a>
 */
public class BibliopgraphicEntryDAO {
                  public List<String> suggestTitle(String library_id, String sub_library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<String> obj=null;
        try {
            session.beginTransaction();
           Query query = session.createQuery("select $a FROM  Biblio WHERE id.libraryId = :libraryId and sublibraryId = :subLibraryId and id.marctag = :marc");
               
                       query.setString("libraryId", library_id);
            query.setString("subLibraryId", sub_library_id);
            query.setString("marc", "245");
           obj= (List<String>) query.list();
        }
        catch(Exception e){
        e.printStackTrace();
        }
        finally {
            session.close();
        }
        return obj;
    }
   

        public void deleteBib(ArrayList<Integer> biblio_id, String library_id, String sub_library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
       

        try {
            tx = session.beginTransaction();
            if(!biblio_id.isEmpty()){
            for(int i=0;i<biblio_id.size();i++)
            {Query query = session.createQuery("DELETE FROM BibliographicDetails  WHERE  id.biblioId = :biblioId and id.libraryId = :libraryId and id.sublibraryId = :subLibraryId");

            query.setInteger("biblioId", biblio_id.get(i));
            query.setString("libraryId", library_id);
            query.setString("subLibraryId", sub_library_id);
            query.executeUpdate();
            tx.commit();
            }
            }
          
        }  catch(Exception e){
        e.printStackTrace();
        tx.rollback();
        }
        finally {
            session.close();
        }
       
    }



    public BibliographicDetails searchisbn10(String library_id, String sub_library_id, String isbn10) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        BibliographicDetails obj=null;
        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(BibliographicDetails.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.sublibraryId", sub_library_id))

                    .add(Restrictions.eq("isbn10", isbn10)));

            obj= (BibliographicDetails) criteria.uniqueResult();
        } catch(Exception e){
        e.printStackTrace();
        }
        finally {
            session.close();
        }
        return obj;
    }

         public List<BibliographicDetails> searchTitle(String library_id, String sub_library_id, String title) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<BibliographicDetails> obj=null;
        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(BibliographicDetails.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.sublibraryId", sub_library_id))

                    .add(Restrictions.eq("title", title)));
            
            obj= (List<BibliographicDetails>) criteria.list();
        }  catch(Exception e){
        e.printStackTrace();
        }
        finally {
            session.close();
        }
        return obj;
    }




           public DocumentDetails searchAccession(String library_id, String sub_library_id, String accession_no) {
        Session session = HibernateUtil.getSessionFactory().openSession();
         DocumentDetails  obj=null;
        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(DocumentDetails.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.sublibraryId", sub_library_id))

                    .add(Restrictions.eq("accessionNo",accession_no)));
           
           obj=(DocumentDetails) criteria.uniqueResult();
        }  catch(Exception e){
        e.printStackTrace();
        }
        finally {
            session.close();
        }
        return obj;
    }



    /**
     * This method is used to insert data into table.
     * @param bibDetails
     */

    public void insert(BibliographicDetails bibDetails) {

       Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(bibDetails);
            tx.commit();
        } catch (RuntimeException e) {
           
            tx.rollback();
           e.printStackTrace();
        } finally {
            session.close();
        }
    }
   public void insertBiblang(BibliographicDetailsLang bibDetails) {

       Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(bibDetails);
            tx.commit();
        } catch (RuntimeException e) {
            
            tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    /**
     * This method is used to insert data into table.
     * @param docDetails
     */
    public void insert1(DocumentDetails docDetails) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(docDetails);
            tx.commit();
        } catch (RuntimeException e) {
          
            tx.rollback();
          e.printStackTrace();
        } finally {
            session.close();
        }
    }

    /**
     * This method is used to insert data into table.
     * @param docDetails
     */
    public void insert2(AccessionRegister docDetails) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(docDetails);
            tx.commit();
        } catch (RuntimeException e) {
            
            tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    // All updates

    /**
     * This method updates bibliographic details.
     * @param bibDetails
     */
    public void update(BibliographicDetails bibDetails) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.update(bibDetails);
            tx.commit();
        } catch (RuntimeException e) {
           
            tx.rollback();
           e.printStackTrace();
        } finally {
            session.close();
        }
    }
    public void updateBiblioLang(BibliographicDetailsLang bibDetails) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.update(bibDetails);
            tx.commit();
        } catch (RuntimeException e) {
            
            tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    public void update2(AccessionRegister bibDetails) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.update(bibDetails);
            tx.commit();
        } catch (RuntimeException e) {
           
            tx.rollback();
           e.printStackTrace();
        } finally {
             session.close();
        }
    }

    /**
     * This method updates document details used in OPAC
     * @param docDetails
     */
    public void update1(DocumentDetails docDetails) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.update(docDetails);
            tx.commit();
        } catch (RuntimeException e) {
            
            tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    // All deletes

    /**
     * This method is used to delete the record from document details table used for OPAC
     * @param biblio_id
     * @param library_id
     */
    public void delete1(int biblio_id, String library_id, String sub_library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        

        try {
            tx = session.beginTransaction();
           
            Query query = session.createQuery("DELETE FROM DocumentDetails  WHERE  biblioId = :biblioId and id.libraryId = :libraryId and id.sublibraryId = :subLibraryId");

            query.setInteger("biblioId", biblio_id);
            query.setString("libraryId", library_id);
            query.setString("subLibraryId", sub_library_id);
            query.executeUpdate();
            tx.commit();
           
        } catch (RuntimeException e) {

            tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void deletedocItem(String acc_no, String library_id, String sub_library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
       

        try {
            tx = session.beginTransaction();
       
            Query query = session.createQuery("DELETE FROM DocumentDetails  WHERE  accessionNo = :accessionNo and id.libraryId = :libraryId and id.sublibraryId = :subLibraryId");

            query.setString("accessionNo", acc_no);
            query.setString("libraryId", library_id);
            query.setString("subLibraryId", sub_library_id);
            query.executeUpdate();
            tx.commit();
           
        }catch (RuntimeException e) {

            tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
/**
 * This method deletes accession register items.
 * @param acc_no
 * @param library_id
 * @param sub_library_id
 */
    public void deleteaccItem(String acc_no, String library_id, String sub_library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        //Object acqdocentry = null;

        try {
            tx = session.beginTransaction();
           
            Query query = session.createQuery("DELETE FROM AccessionRegister  WHERE  accessionNo = :accessionNo and id.libraryId = :libraryId and id.sublibraryId = :subLibraryId");

            query.setString("accessionNo", acc_no);
            query.setString("libraryId", library_id);
            query.setString("subLibraryId", sub_library_id);
            query.executeUpdate();
            tx.commit();
           
        }catch (RuntimeException e) {

            tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    public void deleteaccItemBiblio(int acc_no, String library_id, String sub_library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        //Object acqdocentry = null;

        try {
            tx = session.beginTransaction();
            //acqdocentry = session.load(BibliographicDetails.class, id);
            Query query = session.createQuery("DELETE FROM AccessionRegister  WHERE  bibliographicDetails.id.biblioId = :accessionNo and id.libraryId = :libraryId and id.sublibraryId = :subLibraryId");

            query.setInteger("accessionNo", acc_no);
            query.setString("libraryId", library_id);
            query.setString("subLibraryId", sub_library_id);
            query.executeUpdate();
            tx.commit();
            //return (BibliographicDetails) query.uniqueResult();
        } catch (RuntimeException e) {

            tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    /**
     * This method is used to delete record from bibliographic details table.
     * @param biblio_id
     * @param library_id
     */
    public void delete(int biblio_id, String library_id, String sub_library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        //Object acqdocentry = null;

        try {
            tx = session.beginTransaction();
            //acqdocentry = session.load(BibliographicDetails.class, id);
            Query query = session.createQuery("DELETE FROM BibliographicDetails  WHERE id.biblioId = :biblioId and id.libraryId = :libraryId and id.sublibraryId = :subLibraryId");

            query.setInteger("biblioId", biblio_id);
            query.setString("libraryId", library_id);
            query.setString("subLibraryId", sub_library_id);
            query.executeUpdate();
            tx.commit();
            //return (BibliographicDetails) query.uniqueResult();
        } catch (RuntimeException e) {

            tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
       public void deleteBiblioLang(int biblio_id, String library_id, String sub_library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        //Object acqdocentry = null;

        try {
            tx = session.beginTransaction();
            //acqdocentry = session.load(BibliographicDetails.class, id);
            Query query = session.createQuery("DELETE FROM BibliographicDetailsLang  WHERE id.biblioId = :biblioId and id.libraryId = :libraryId and id.sublibraryId = :subLibraryId");

            query.setInteger("biblioId", biblio_id);
            query.setString("libraryId", library_id);
            query.setString("subLibraryId", sub_library_id);
            query.executeUpdate();
            tx.commit();
            //return (BibliographicDetails) query.uniqueResult();
        } catch (RuntimeException e) {

            tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    //Return Max

    /**
     * This method is used to return the maximum biblio Id specific to a library
     * @param library_id
     * @return maxbiblio
     */
    public Integer returnMaxBiblioId(String library_id, String sublibrary_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Integer maxbiblio=null;
        try {
                    session.beginTransaction();

            Criteria criteria = session.createCriteria(BibliographicDetails.class);
            Criterion a = Restrictions.eq("id.libraryId", library_id);
            Criterion b = Restrictions.eq("id.sublibraryId", sublibrary_id);
            LogicalExpression le = Restrictions.and(a, b);
             maxbiblio = (Integer) criteria.add(le).setProjection(Projections.max("id.biblioId")).uniqueResult();
            if (maxbiblio == null) {
                maxbiblio = 1;
            } else {
                maxbiblio++;
            }

           
        }  catch(Exception e){
        e.printStackTrace();
        }
        finally {
            session.close();
        }
        return maxbiblio;
    }


    /**
     * This method is used to return maximum record no of accession register
     * @param library_id
     * @param sublibrary_id
     * @return returnMaxRecord
     */
    public Integer returnMaxRecord(String library_id, String sublibrary_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
         Integer maxbiblio =null;

        try {
             session.beginTransaction();
            Criteria criteria = session.createCriteria(AccessionRegister.class);
            Criterion a = Restrictions.eq("id.libraryId", library_id);
            Criterion b = Restrictions.eq("id.sublibraryId", sublibrary_id);
            LogicalExpression lexp = Restrictions.and(a, b);
            maxbiblio = (Integer) criteria.add(lexp).setProjection(Projections.max("id.recordNo")).uniqueResult();
            if (maxbiblio == null) {
                maxbiblio = 1;
            } else {
                maxbiblio++;
            }

         
        }  catch(Exception e){
        e.printStackTrace();
        }
        finally {
            session.close();
        }
          return maxbiblio;
    }


    /**
     * This method is used to return maximum document id specific to a library
     * @param library_id
     * @return maxbiblio
     */
    public Integer returnMaxDocumentId(String library_id, String sublibrary_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
         Integer maxbiblio=null;
        try {
                    session.beginTransaction();

            Criteria criteria = session.createCriteria(DocumentDetails.class);
            Criterion a = Restrictions.eq("id.libraryId", library_id);
            Criterion b = Restrictions.eq("id.sublibraryId", sublibrary_id);
            LogicalExpression le = Restrictions.and(a, b);
             maxbiblio = (Integer) criteria.add(le).setProjection(Projections.max("id.documentId")).uniqueResult();
            if (maxbiblio == null) {
                maxbiblio = 1;
            } else {
                maxbiblio++;
            }

            
        }  catch(Exception e){
        e.printStackTrace();
        }
        finally {
            session.close();
        }
        return maxbiblio;
    }


    /**
     *
     * @param library_id
     * @param sublibrary_id
     * @return maxbiblio
     */
    public Integer returnMaxDocumentId1(String library_id, String sublibrary_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Integer maxbiblio=null;
        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(DocumentDetails.class);
            Criterion a = Restrictions.eq("id.libraryId", library_id);
            Criterion b = Restrictions.eq("id.sublibraryId", sublibrary_id);
            LogicalExpression le = Restrictions.and(a, b);

            maxbiblio = (Integer) criteria.add(le).setProjection(Projections.property("id.sublibraryId")).INNER_JOIN;


            if (maxbiblio == null) {
                maxbiblio = 1;
            } else {
                maxbiblio++;
            }

     
        }  catch(Exception e){
        e.printStackTrace();
        }
        finally {
            session.close();
        }
             return maxbiblio;
    }

// All objects
  /**
   * This method is used while updating accession register detail.
   * It searches all the accessioned items and checks duplicate entry.
   * @param record_no
   * @param library_id
   * @param sub_library_id
   * @param acc_no
   * @return AccessionRegister
   */
    public AccessionRegister searchaccupdate(int  record_no,String library_id, String sub_library_id, String acc_no) {
        Session session = HibernateUtil.getSessionFactory().openSession();
       AccessionRegister  obj=null;
        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(AccessionRegister.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.sublibraryId", sub_library_id))
                    .add(Restrictions.eq("accessionNo", acc_no))
                    .add(Restrictions.ne("id.recordNo", record_no)));
            obj= (AccessionRegister) criteria.uniqueResult();
        } catch(Exception e){
        e.printStackTrace();
        }
        finally {
            session.close();
        }
        return obj;
    }

/**
 * This method is used while updating bibliographic detail.
 * It searches all the titles and checks duplicate entry of call no.
 * @param callno
 * @param biblio_id
 * @param library_id
 * @param sub_library_id
 * @return BibliographicDetails
 */
      public BibliographicDetails searchCallNOByBiblio(String callno, int biblio_id, String library_id, String sub_library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
       BibliographicDetails obj=null;

    try{
        session.beginTransaction();
        Criteria criteria = session.createCriteria(BibliographicDetails.class)
            .add(Restrictions.conjunction()
                .add(Restrictions.eq("id.libraryId", library_id))
                .add(Restrictions.eq("id.sublibraryId", sub_library_id))
                .add(Restrictions.ne("id.biblioId", biblio_id))
                .add(Restrictions.eq("callNo", callno)));
        obj= (BibliographicDetails) criteria.uniqueResult();
    }
     catch(Exception e){
        e.printStackTrace();
        }
        finally {
            session.close();
        }
        return obj;
    }

      public BibliographicDetailsLang searchCallNoLangByBiblio(String callno, int biblio_id, String library_id, String sub_library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        BibliographicDetailsLang obj=null;

    try{
        session.beginTransaction();
        Criteria criteria = session.createCriteria(BibliographicDetailsLang.class)
                .add(Restrictions.conjunction()
                .add(Restrictions.eq("id.libraryId", library_id))
                .add(Restrictions.eq("id.sublibraryId", sub_library_id))
                .add(Restrictions.ne("id.biblioId", biblio_id))
                .add(Restrictions.eq("callNo", callno)));
        obj= (BibliographicDetailsLang) criteria.uniqueResult();
    }
    catch(Exception e){
        e.printStackTrace();
        }
        finally {
            session.close();
        }
        return obj;
    }

      /**
 * This method is used while updating bibliographic detail.
 * It searches all the titles and checks duplicate entry of isbn-10.
  * @param isbn
  * @param biblio_id
  * @param library_id
  * @param sub_library_id
  * @return BibliographicDetails
  */
      public BibliographicDetails searchIsbn10ByBiblio(String callno,String isbn10, int biblio_id, String library_id, String sub_library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
      BibliographicDetails obj=null;
      try{
            session.beginTransaction();
          Criteria criteria = session.createCriteria(BibliographicDetails.class)
                .add(Restrictions.conjunction()
                .add(Restrictions.eq("id.libraryId", library_id))
                .add(Restrictions.eq("id.sublibraryId", sub_library_id))
                .add(Restrictions.ne("id.biblioId", biblio_id))
                .add(Restrictions.eq("callNo", callno))
                .add(Restrictions.eq("isbn10", isbn10)));
       obj= (BibliographicDetails) criteria.uniqueResult();
      }
     catch(Exception e){
        e.printStackTrace();
        }
        finally {
            session.close();
        }
        return obj;
    }

         public BibliographicDetailsLang searchIsbn10LangByBiblio(String callno,String isbn10, int biblio_id, String library_id, String sub_library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();

         BibliographicDetailsLang obj=null;

      try{
          session.beginTransaction();
          Criteria criteria = session.createCriteria(BibliographicDetailsLang.class)
                .add(Restrictions.conjunction()
                .add(Restrictions.eq("id.libraryId", library_id))
                .add(Restrictions.eq("id.sublibraryId", sub_library_id))
                .add(Restrictions.ne("id.biblioId", biblio_id))
                .add(Restrictions.eq("callNo", callno))
                .add(Restrictions.eq("isbn10", isbn10)));
       obj= (BibliographicDetailsLang) criteria.uniqueResult();
      }
       catch(Exception e){
        e.printStackTrace();
        }
        finally {
            session.close();
        }
        return obj;
    }

      /**
     * This method returns specific document used in OPAC.
     * @param biblio_id
     * @param library_id
     * @return DocumentDetails
     */
    public DocumentDetails searchDoc(int biblio_id, int record_no, String library_id, String sub_library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        DocumentDetails obj=null;

        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(DocumentDetails.class).add(Restrictions.conjunction().add(Restrictions.eq("id.libraryId", library_id)).add(Restrictions.eq("id.sublibraryId", sub_library_id)).add(Restrictions.eq("biblioId", biblio_id)).add(Restrictions.eq("recordNo", record_no)));
            obj= (DocumentDetails) criteria.uniqueResult();
        }  catch(Exception e){
        e.printStackTrace();
        }
        finally {
            session.close();
        }
        return obj;
    }


    /**
     *
     * @param isbn10
     * @param library_id
     * @param sub_library_id
     * @return BibliographicDetails
     */
    public BibliographicDetails searchIsbn10(String isbn10, String library_id, String sub_library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
BibliographicDetails obj=null;
        try {
            session.beginTransaction();
            Query query = session.createQuery("FROM BibliographicDetails  WHERE isbn10 = :isbn10  and id.libraryId = :libraryId and id.sublibraryId = :subLibraryId");
            query.setString("isbn10", isbn10);
            query.setString("libraryId", library_id);
            query.setString("subLibraryId", sub_library_id);
           obj= (BibliographicDetails) query.uniqueResult();
        } catch(Exception e){
        e.printStackTrace();
        }
        finally {
            session.close();
        }
        return obj;
    }


    /**
     *
     * @param isbn10
     * @param library_id
     * @param sub_library_id
     * @return BibliographicDetails
     */
    public BibliographicDetails search1Isbn10(String isbn10, String library_id, String sub_library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        BibliographicDetails obj=null;

       try{
           session.beginTransaction();
           Criteria criteria = session.createCriteria(BibliographicDetails.class)
               .add(Restrictions.conjunction().add(Restrictions.eq("id.libraryId", library_id)).add(Restrictions.eq("id.sublibraryId", sub_library_id)).add(Restrictions.eq("isbn10", isbn10)));
        obj= (BibliographicDetails) criteria.uniqueResult();
       }
        catch(Exception e){
        e.printStackTrace();
        }
        finally {
            session.close();
        }
        return obj;
    }

    public BibliographicDetailsLang search1LangIsbn10(String isbn10, String library_id, String sub_library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        BibliographicDetailsLang  obj=null;

     try{
         session.beginTransaction();
         Criteria criteria = session.createCriteria(BibliographicDetailsLang.class).add(Restrictions.conjunction().add(Restrictions.eq("id.libraryId", library_id)).add(Restrictions.eq("id.sublibraryId", sub_library_id)).add(Restrictions.eq("isbn10", isbn10)));
        obj=(BibliographicDetailsLang) criteria.uniqueResult();
     }
      catch(Exception e){
        e.printStackTrace();
        }
        finally {
            session.close();
        }
        return obj;
    }

    /**
     *
     * @param title
     * @param isbn10
     * @param library_id
     * @param sub_library_id
     * @return BibliographicDetails
     */
    public BibliographicDetails search2Isbn10(String title, String isbn10, String library_id, String sub_library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
       BibliographicDetails obj=null;

      try{
          session.beginTransaction();
          Criteria criteria = session.createCriteria(BibliographicDetails.class)
                .add(Restrictions.conjunction()
                .add(Restrictions.eq("id.libraryId", library_id))
                .add(Restrictions.eq("id.sublibraryId", sub_library_id))
                .add(Restrictions.eq("isbn10", isbn10))
                .add(Restrictions.eq("title", title)));
      obj= (BibliographicDetails) criteria.uniqueResult();
      }
       catch(Exception e){
        e.printStackTrace();
        }
        finally {
            session.close();
        }
        return obj;
    }

    public BibliographicDetailsLang searchlangbyBiblioid(int biblio_id,String library_id, String sub_library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        BibliographicDetailsLang obj=null;
      try{
          session.beginTransaction();
          Criteria criteria = session.createCriteria(BibliographicDetailsLang.class)
                .add(Restrictions.conjunction()
                .add(Restrictions.eq("id.libraryId", library_id))
                .add(Restrictions.eq("id.sublibraryId", sub_library_id))
                .add(Restrictions.eq("id.biblioId", biblio_id)));
        obj= (BibliographicDetailsLang) criteria.uniqueResult();
      }
       catch(Exception e){
        e.printStackTrace();
        }
        finally {
            session.close();
        }
        return obj;
    }

    /**
     *
     * @param library_id
     * @param sub_library_id
     * @param acc_no
     * @return AccessionRegister
     */
    public AccessionRegister searchacc(String library_id, String sub_library_id, String acc_no) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        AccessionRegister obj=null;
        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(AccessionRegister.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.sublibraryId", sub_library_id))
                    .add(Restrictions.eq("accessionNo", acc_no)));

           
            obj= (AccessionRegister) criteria.uniqueResult();
        } catch(Exception e){
        e.printStackTrace();
        }
        finally {
            session.close();
        }
        return obj;
    }


    /**
     *
     * @param library_id
     * @param sub_library_id
     * @param record_no
     * @return AccessionRegister
     */
    public AccessionRegister searchAccByRecord(String library_id, String sub_library_id, int record_no) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        AccessionRegister  obj=null;
        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(AccessionRegister.class).add(Restrictions.conjunction().add(Restrictions.eq("id.libraryId", library_id)).add(Restrictions.eq("id.sublibraryId", sub_library_id)).add(Restrictions.eq("id.recordNo", record_no)));

          
            obj= (AccessionRegister) criteria.uniqueResult();
        }  catch(Exception e){
        e.printStackTrace();
        }
        finally {
            session.close();
        }
        return obj;
    }


    /**
     *
     * @param library_id
     * @param sub_library_id
     * @param acc_no
     * @return AccessionRegister
     */
    public AccessionRegister searchacc1(String library_id, String sub_library_id, String acc_no) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        AccessionRegister obj=null;
        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(AccessionRegister.class).add(Restrictions.conjunction().add(Restrictions.eq("id.libraryId", library_id)).add(Restrictions.eq("id.sublibraryId", sub_library_id)).add(Restrictions.eq("accessionNo", acc_no)));

           
           obj= (AccessionRegister) criteria.uniqueResult();
        }  catch(Exception e){
        e.printStackTrace();
        }
        finally {
            session.close();
        }
        return obj;
    }


    /**
     *
     * @param call_no
     * @param library_id
     * @param sub_library_id
     * @return BibliographicDetails
     */
    public BibliographicDetails searchcall(String call_no, String library_id, String sub_library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
       BibliographicDetails obj=null;

        try {
              session.beginTransaction();
            Criteria criteria = session.createCriteria(BibliographicDetails.class).add(Restrictions.conjunction().add(Restrictions.eq("id.libraryId", library_id)).add(Restrictions.eq("id.sublibraryId", sub_library_id)).add(Restrictions.eq("callNo", call_no)));
           obj= (BibliographicDetails) criteria.uniqueResult();
        }  catch(Exception e){
        e.printStackTrace();
        }
        finally {
            session.close();
        }
        return obj;
    }

    public BibliographicDetailsLang searchLangcall(String call_no, String library_id, String sub_library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        BibliographicDetailsLang obj=null;

        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(BibliographicDetailsLang.class).add(Restrictions.conjunction().add(Restrictions.eq("id.libraryId", library_id)).add(Restrictions.eq("id.sublibraryId", sub_library_id)).add(Restrictions.eq("callNo", call_no)));
            obj=(BibliographicDetailsLang) criteria.uniqueResult();
        } catch(Exception e){
        e.printStackTrace();
        }
        finally {
            session.close();
        }
        return obj;
    }

    /**
     *
     * @param isbn10
     * @param library_id
     * @param sub_library_id
     * @return AcqFinalDemandList
     */
    public AcqFinalDemandList searchacqIsbn10(String isbn10, String library_id, String sub_library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
AcqFinalDemandList obj=null;
        try {
            session.beginTransaction();
            Query query = session.createQuery("FROM AcqFinalDemandList  WHERE isbn = :isbn  and id.libraryId = :libraryId and id.sublibraryId = :subLibraryId");
            query.setString("isbn", isbn10);
            query.setString("libraryId", library_id);
            query.setString("subLibraryId", sub_library_id);
           obj=(AcqFinalDemandList) query.uniqueResult();
        }  catch(Exception e){
        e.printStackTrace();
        }
        finally {
            session.close();
        }
        return obj;
    }


    /**
     *
     * @param isbn10
     * @param title
     * @param library_id
     * @param sub_library_id
     * @return AcqFinalDemandList
     */
    public AcqFinalDemandList searchacq1Isbn10(String isbn10, String title, String library_id, String sub_library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
       AcqFinalDemandList obj=null;

        try {
             session.beginTransaction();
            Criteria criteria = session.createCriteria(BibliographicDetails.class).add(Restrictions.conjunction().add(Restrictions.eq("id.libraryId", library_id)).add(Restrictions.eq("id.sublibraryId", sub_library_id)).add(Restrictions.eq("title", title)).add(Restrictions.eq("isbn10", isbn10)));
           obj= (AcqFinalDemandList) criteria.uniqueResult();
        }  catch(Exception e){
        e.printStackTrace();
        }
        finally {
            session.close();
        }
        return obj;
    }


    /**
     *
     * @param control_no
     * @param library_id
     * @param sub_library_id
     * @return AcqFinalDemandList
     */
    public AcqFinalDemandList searchacq(String control_no, String library_id, String sub_library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        AcqFinalDemandList obj=null;


        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(AcqFinalDemandList.class).add(Restrictions.conjunction().add(Restrictions.eq("id.libraryId", library_id)).add(Restrictions.eq("id.sublibraryId", sub_library_id)).add(Restrictions.eq("id.controlNo", control_no)));
            obj= (AcqFinalDemandList) criteria.uniqueResult();
        } catch(Exception e){
        e.printStackTrace();
        }
        finally {
            session.close();
        }
        return obj;
    }

    /**
     *
     * @param library_id
     * @param sub_library_id
     * @param biblio_id
     * @return BibliographicDetails
     */
    public BibliographicDetails getBiblio(String library_id, String sub_library_id, int biblio_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
         BibliographicDetails obj=null;

        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(BibliographicDetails.class).add(Restrictions.conjunction().add(Restrictions.eq("id.libraryId", library_id)).add(Restrictions.eq("id.sublibraryId", sub_library_id)).add(Restrictions.eq("id.biblioId", biblio_id)));
           obj= (BibliographicDetails) criteria.uniqueResult();
        }  catch(Exception e){
        e.printStackTrace();
        }
        finally {
            session.close();
        }
        return obj;
    }


    /**
     *
     * @param isbn13
     * @param library_id
     * @return BibliographicDetails
     */
    public BibliographicDetails searchIsbn13(String isbn13, String library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
 BibliographicDetails obj=null;
        try {
            session.beginTransaction();
            Query query = session.createQuery("FROM BibliographicDetails  WHERE isbn13 = :isbn13  and id.libraryId = :libraryId ");
            query.setString("isbn13", isbn13);
            query.setString("libraryId", library_id);
           obj= (BibliographicDetails) query.uniqueResult();
        } catch(Exception e){
        e.printStackTrace();
        }
        finally {
            session.close();
        }
        return obj;
    }

// All Lists

    /**
     *
     * @param biblio_id
     * @param library_id
     * @param sub_library_id
     * @return List
     */
    public List<DocumentDetails> searchDoc2(int biblio_id, String library_id, String sub_library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<DocumentDetails>  obj=null;

        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(DocumentDetails.class);
                    criteria.add(Restrictions.conjunction());
                  /*  .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.sublibraryId", sub_library_id))
                   *
                   */
                if(!library_id.equalsIgnoreCase("all"))
                    criteria.add(Restrictions.eq("id.libraryId",library_id));
                if(!sub_library_id.equalsIgnoreCase("all"))
                    criteria.add(Restrictions.eq("id.sublibraryId",sub_library_id));


                    criteria.add(Restrictions.eq("biblioId", biblio_id));
           obj= (List<DocumentDetails>)criteria.list();
        }  catch(Exception e){
        e.printStackTrace();
        }
        finally {
            session.close();
        }
        return obj;
    }

       public List<DocumentDetails> searchDoc1(int biblio_id, String library_id, String sub_library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
       List<DocumentDetails>  obj=null;

        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(DocumentDetails.class)
                    .add(Restrictions.conjunction());
              if(library_id.equalsIgnoreCase("all")==false)
                   criteria.add(Restrictions.eq("id.libraryId", library_id));
             if(sub_library_id.equalsIgnoreCase("all")==false)
                   criteria.add(Restrictions.eq("id.sublibraryId", sub_library_id));


                    criteria.add(Restrictions.eq("biblioId", biblio_id));
            obj= (List<DocumentDetails>)criteria.list();
        }  catch(Exception e){
        e.printStackTrace();
        }
        finally {
            session.close();
        }
        return obj;
    }

    /**
     *
     * @param title
     * @param library_id
     * @param sub_library_id
     * @param doc_type
     * @return List
     */
    public List getTitles(String title, String library_id, String sub_library_id, String doc_type) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List obj=null;

        try {
             session.beginTransaction();
            Criteria criteria = session.createCriteria(BibliographicDetails.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.sublibraryId", sub_library_id))
                    .add(Restrictions.eq("title", title))
                    .add(Restrictions.eq("documentType", doc_type)));
           obj= (List) criteria.list();
        }  catch(Exception e){
        e.printStackTrace();
        }
        finally {
            session.close();
        }
        return obj;
    }


    /**
     *
     * @param title
     * @param library_id
     * @param doc_type
     * @return List
     */
    public List getTitles2(String title, String library_id, String doc_type) {
        Session session = HibernateUtil.getSessionFactory().openSession();
       List obj=null;

        try {
              session.beginTransaction();
            Criteria criteria = session.createCriteria(BibliographicDetails.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("title", title))
                    .add(Restrictions.eq("documentType", doc_type)));
           obj= (List) criteria.list();
        }  catch(Exception e){
        e.printStackTrace();
        }
        finally {
            session.close();
        }
        return obj;
    }


    /**
     *
     * @param search_by
     * @param search_keyword
     * @param sort_by
     * @return List
     */
   public List getBiblio(String library_id,String sublibrary_id,String search_by, String search_keyword, String sort_by) {
        Session session = HibernateUtil.getSessionFactory().openSession();
       List obj=null;

        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(BibliographicDetails.class)
                    //.add(Restrictions.conjunction())
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.sublibraryId", sublibrary_id))
                    .add(Restrictions.ilike(search_by,search_keyword+"%"))
                    .addOrder(Property.forName(sort_by).asc());
           obj= (List) criteria.list();
        } catch(Exception e){
        e.printStackTrace();
        }
        finally {
            session.close();
        }
        return obj;
    }

/*public List getBiblio(String library_id,String sublibrary_id,String search_by,String search_keyword, String sort_by){
  Session session =null;
    Transaction tx = null;
        try {
            session.beginTransaction();
            Query query = session.createQuery("FROM BibliographicDetails where id.libraryId = :libraryId and id.sublibraryId = :subLibraryId and"+search_by+"like"+search_keyword+"% order by"+sort_by+" asc");
            query.setString("libraryId", library_id);
            query.setString("subLibraryId", sublibrary_id);

            return query.list();
        } finally {
//            session.close();
        }
}*/
    /**
     *
     * @param library_id
     * @param sub_library_id
     * @param biblio_id
     * @return List
     */
    public List getItems(String library_id, String sub_library_id, int biblio_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List obj=null;

        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(AccessionRegister.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.sublibraryId", sub_library_id))
                    .add(Restrictions.eq("biblioId", biblio_id)));
           obj= (List) criteria.list();
        } catch(Exception e){
        e.printStackTrace();
        }
        finally {
            session.close();
        }
        return obj;
    }


    /**
     *
     * @param title
     * @param library_id
     * @param sub_library_id
     * @return List
     */
    public List getTitles1(String title, String library_id, String sub_library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List obj=null;

        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(AcqFinalDemandList.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.sublibraryId", sub_library_id))
                    .add(Restrictions.eq("title", title)));
           obj= (List) criteria.list();
        } catch(Exception e){
        e.printStackTrace();
        }
        finally {
            session.close();
        }
        return obj;
    }


        /**
     *
     * @param title
     * @param library_id
     * @param sub_library_id
     * @return List
     */
    public List getAllTitles(String library_id, String sub_library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
       List obj=null;
        try {
                    session.beginTransaction();

            Criteria criteria = session.createCriteria(BibliographicDetails.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.sublibraryId", sub_library_id)));
            obj= (List) criteria.list();
        }  catch(Exception e){
        e.printStackTrace();
        }
        finally {
            session.close();
        }
        return obj;
    }

    /**
     *
     * @param library_id
     * @param sub_library_id
     * @return List
     */
    public List getBibliographicDetails(String library_id, String sub_library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
List obj=null;
        try {
            session.beginTransaction();
            Query query = session.createQuery("FROM BibliographicDetails where id.libraryId = :libraryId and sublibraryId = :subLibraryId");
            query.setString("libraryId", library_id);
            query.setString("subLibraryId", sub_library_id);
            obj= query.list();
        }  catch(Exception e){
        e.printStackTrace();
        }
        finally {
            session.close();
        }
        return obj;
    }

}
