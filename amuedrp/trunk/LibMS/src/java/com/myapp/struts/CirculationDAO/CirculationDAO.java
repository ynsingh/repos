/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.CirculationDAO;
import com.myapp.struts.admin.AdminReg_Institute;

import com.myapp.struts.hbm.*;
import com.myapp.struts.hbm.HibernateUtil;
import com.myapp.struts.opac.MemberFineWithCheckoutDetails;
import com.myapp.struts.utility.DateCalculation;
import java.util.List;
import org.hibernate.Transaction;
import org.hibernate.Session;
import org.hibernate.Query;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
/**
 *
 * @author edrp01
 */
public class CirculationDAO
{
   static  Integer maxNewRegId;
   static Query query;
   public static  CirCheckin searchCheckinMemDetails(String library_id,String mem_id)
{
        Session session = HibernateUtil.getSessionFactory().openSession();

        try
        {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(CirCheckin.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))

                    .add(Restrictions.eq("memberId", mem_id)));
            return (CirCheckin) criteria.uniqueResult();


        }
        catch(Exception e){
        System.out.println(e+".......................");
        return null;
        }
        finally
        {
          
        }
}

   public static  CirCheckin searchCheckinMemDetails(String library_id,String sublibrary_id,String mem_id,String checkIn)
{
        Session session = HibernateUtil.getSessionFactory().openSession();

        try
        {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(CirCheckin.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))

                    .add(Restrictions.eq("memberId", mem_id))
                    .add(Restrictions.eq("id.sublibraryId", sublibrary_id))
                    .add(Restrictions.eq("id.checkinId", checkIn))


                    );
            return (CirCheckin) criteria.uniqueResult();


        }
        catch(Exception e){
        System.out.println(e+".......................");
        return null;
        }
        finally
        {

        }
}


    public static  List<CirMemberAccount> searchCirMemAccount1(String library_id,String sublibrary_id)
{
        Session session = HibernateUtil.getSessionFactory().openSession();

        try
        {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(CirMemberAccount.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("status", "Active"))
                    .add(Restrictions.eq("id.sublibraryId", sublibrary_id)));
            return (List<CirMemberAccount>) criteria.list();


        }
        finally
        {
           session.close();
        }
}



public static boolean insert(CirMemberAccount cma)
        {
            Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.save(cma);

            tx.commit();
        }
        catch (RuntimeException e) {
                System.out.println("CirculationDAO.Insert():*****"+e);
                tx.rollback();
                return false;

        }
        finally
        {
          session.close();
        }

   return true;

        }

   public static  DocumentDetails searchDocumentID(String library_id,String sub_library_id,String accession_no)
{
        Session session = HibernateUtil.getSessionFactory().openSession();

        try
        {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(DocumentDetails.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.sublibraryId", sub_library_id))
                    .add(Restrictions.eq("accessionNo", accession_no)));
            return (DocumentDetails) criteria.uniqueResult();


        }
        finally
        {
           session.close();
        }
}

      public static  CirCheckout searchCheckOutDetails(String library_id,String sub_library_id,String document_id)
{
        Session session = HibernateUtil.getSessionFactory().openSession();

        try
        {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(CirCheckout.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.sublibraryId", sub_library_id))
                    .add(Restrictions.eq("documentId", document_id)));
            return (CirCheckout) criteria.uniqueResult();


        }
        finally
        {
           session.close();
        }
}

       public static  CirCheckout searchCheckOutDetails1(String library_id,String sub_library_id,String document_id,String status)
{
        Session session = HibernateUtil.getSessionFactory().openSession();

        try
        {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(CirCheckout.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.sublibraryId", sub_library_id))
                    .add(Restrictions.eq("documentId", document_id))
            .add(Restrictions.eq("status", status)));
            return (CirCheckout) criteria.uniqueResult();


        }
        finally
        {
           session.close();
        }
}
           public static  CirCheckout searchCheckOutDetailsByStatus(String library_id,String sub_library_id,String document_id,String status)
{
        Session session = HibernateUtil.getSessionFactory().openSession();

        try
        {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(CirCheckout.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.sublibraryId", sub_library_id))
                    .add(Restrictions.eq("documentId", document_id))
                    .add(Restrictions.eq("status", status)));
            return (CirCheckout) criteria.uniqueResult();


        }
        finally
        {
           session.close();
        }
}

            public static  List<CirCheckout> searchCheckOutListByStatus(String library_id,String sub_library_id,String memId,String status)
{
        Session session = HibernateUtil.getSessionFactory().openSession();

        try
        {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(CirCheckout.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.sublibraryId", sub_library_id))
                    .add(Restrictions.eq("memid", memId))
                    .add(Restrictions.eq("status", status)));
            return (List<CirCheckout>) criteria.list();


        }
        finally
        {
           session.close();
        }
}

            public static  CirMemberDetail searchCirMemDetails(String library_id,String mem_id)
{
        Session session = HibernateUtil.getSessionFactory().openSession();

        try
        {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(CirMemberDetail.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                   
                    .add(Restrictions.eq("id.memId", mem_id)));
            return (CirMemberDetail) criteria.uniqueResult();


        }
        catch(Exception e){
        System.out.println(e+".......................");
        return null;
        }
        finally
        {
           session.close();
        }
}
      public static  CirOpacRequest searchCirOpacRequest(String library_id,String sublibrary_id,String document_id,String accession_no)
{
        Session session = HibernateUtil.getSessionFactory().openSession();

        try
        {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(CirOpacRequest.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                     .add(Restrictions.eq("id.sublibraryId", sublibrary_id))
                     .add(Restrictions.eq("accessionNo", accession_no))
                    .add(Restrictions.eq("documentId",Integer.parseInt(document_id))));
            return (CirOpacRequest) criteria.uniqueResult();


        }
        catch(Exception e){
        System.out.println(e+".......................");
        return null;
        }
        finally
        {
           
        }
}
public static  CirOpacRequest searchCirOpacRequest1(String library_id,String sublibrary_id,String document_id,String accession_no)
{
        Session session = HibernateUtil.getSessionFactory().openSession();

        try
        {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(CirOpacRequest.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                     .add(Restrictions.eq("id.sublibraryId", sublibrary_id))
                     .add(Restrictions.eq("accessionNo", accession_no))
                     .add(Restrictions.eq("status", "Pending"))
                    .add(Restrictions.eq("documentId",Integer.parseInt(document_id))));
            return (CirOpacRequest) criteria.uniqueResult();


        }
        catch(Exception e){
        System.out.println(e+".......................");
        return null;
        }
        finally
        {

        }
}
      public static  CirOpacRequest searchCirOpacRequestByMemId(String library_id,String sublibrary_id,String memId,String accession_no)
{
        Session session = HibernateUtil.getSessionFactory().openSession();

        try
        {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(CirOpacRequest.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                     .add(Restrictions.eq("id.sublibraryId", sublibrary_id))
                     .add(Restrictions.eq("accessionNo", accession_no))
                     .add(Restrictions.eq("status", "Pending"))
                    .add(Restrictions.eq("memid",memId)));
            return (CirOpacRequest) criteria.uniqueResult();


        }
        catch(Exception e){
        System.out.println(e+".......................");
        return null;
        }
        finally
        {

        }
}
  public static  List<CirMemberAccount> searchCirMemAccountDetailsBySubMember(String library_id,String emptype_id,String sub_emptype_id)
{
        Session session = HibernateUtil.getSessionFactory().openSession();

        try
        {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(CirMemberAccount.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("memType", emptype_id))
                    .add(Restrictions.eq("subMemberType", sub_emptype_id))

                    );
            return (List<CirMemberAccount>) criteria.list();


        }
        finally
        {
           session.close();
        }
}
        public static  List<CirMemberAccount> searchCirMemAccountDetails(String library_id,String mem_id)
{
        Session session = HibernateUtil.getSessionFactory().openSession();

        try
        {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(CirMemberAccount.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    
                    .add(Restrictions.eq("id.memid", mem_id)));
            return (List<CirMemberAccount>) criteria.list();


        }
        finally
        {
           session.close();
        }
}
public static  CirMemberAccount searchCirMemAccountDetails(String library_id,String sublibrary_id,String mem_id)
{
        Session session = HibernateUtil.getSessionFactory().openSession();

        try
        {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(CirMemberAccount.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.sublibraryId", sublibrary_id))

                    .add(Restrictions.eq("id.memid", mem_id)));
            return (CirMemberAccount) criteria.uniqueResult();


        }
        finally
        {
           session.close();
        }
}
public static  List<CirMemberAccount> searchCirMemAccountDetailsLst(String library_id,String sublibrary_id,String mem_id)
{
        Session session = HibernateUtil.getSessionFactory().openSession();

        try
        {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(CirMemberAccount.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.not(Restrictions.eq("id.sublibraryId", sublibrary_id)))
                    .add(Restrictions.eq("id.memid", mem_id)));
            return (List<CirMemberAccount>) criteria.list();


        }
        finally
        {
           session.close();
        }
}

       public static  CirTransactionHistory searchCirTransactionHistory(String library_id,String sub_library_id,Integer checkoutId)
{
        Session session = HibernateUtil.getSessionFactory().openSession();

        try
        {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(CirTransactionHistory.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.sublibraryId", sub_library_id))
                    .add(Restrictions.eq("checkoutId", checkoutId)));
            return (CirTransactionHistory) criteria.uniqueResult();


        }
        finally
        {
           session.close();
        }
}
public static  BookCategory searchfineDetails(String library_id,String book_type,String memtype,String submemtype)
{
        Session session = HibernateUtil.getSessionFactory().openSession();

        try
        {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(BookCategory.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.bookType",book_type ))
                    .add(Restrictions.eq("id.emptypeId",memtype ))
                    .add(Restrictions.eq("id.subEmptypeId",submemtype ))
                    );
           return (BookCategory) criteria.uniqueResult();


        }
        finally
        {
           session.close();
        }
}

        public static  BookCategory searchfineDetails(String library_id,String book_type)
{
        Session session = HibernateUtil.getSessionFactory().openSession();

        try
        {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(BookCategory.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.bookType",book_type )));
           return (BookCategory) criteria.uniqueResult();


        }
        finally
        {
           session.close();
        }
}

        public static  BookCategory searchfineDetailsByType(String library_id,String sublibraryId,String docId)
{
        Session session = HibernateUtil.getSessionFactory().openSession();

        try
        {
            session.beginTransaction();
            Query query = session.createSQLQuery("select a.* from book_category a,document_details d,cir_checkout cc,cir_member_account cma where a.library_id=d.library_id and a.book_type=d.book_type and a.emptype_id=cma.mem_type and a.sub_emptype_id=cma.sub_member_type and a.library_id=cc.library_id and d.sublibrary_id = cc.sublibrary_id and cc.memid=cma.memid and cc.library_id=cma.library_id and cc.sublibrary_id=cma.sublibrary_id  and cc.document_id=d.document_id  and cc.library_id=:library_id and d.status='issued' and cc.document_id=:documentId and d.sublibrary_id=:sublibraryId")
                    .addEntity(BookCategory.class);
            query.setString("library_id", library_id);
            query.setString("sublibraryId",sublibraryId);
            query.setString("documentId", docId);
                    return (BookCategory) query.uniqueResult();


        }
        finally
        {
           session.close();
        }
}

         public static  List<MemberFineWithCheckoutDetails> searchfineDetailsByMemId(String library_id,String subLibraryId,String memId)
{
        Session session = HibernateUtil.getSessionFactory().openSession();

        try
        {
            session.beginTransaction();
            Query query = session.createSQLQuery("select * from book_category a,document_details d,cir_checkout cc,cir_member_account cma where a.library_id=d.library_id and a.book_type=d.book_type and a.library_id=cc.library_id and d.sublibrary_id = cc.sublibrary_id and cc.memid=cma.memid and cc.library_id=cma.library_id and cc.sublibrary_id=cma.sublibrary_id and cc.document_id=d.document_id and d.status='issued' and cc.library_id=:library_id and cc.sublibrary_id=:sublibraryId and cma.memid=:memId")
                    .addEntity(BookCategory.class)
                    .addEntity(DocumentDetails.class)
                    .addEntity(CirCheckout.class)
                    .addEntity(CirMemberAccount.class)
                    .setResultTransformer(Transformers.aliasToBean(MemberFineWithCheckoutDetails.class));
            query.setString("library_id", library_id);
            query.setString("memId", memId);
            query.setString("sublibraryId", subLibraryId);

                    return (List<MemberFineWithCheckoutDetails>) query.list();


        }
        finally
        {
           session.close();
        }
}

        public Integer returnMaxCheckInId(String library_id, String sublibrary_id) {

         /*    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            session.beginTransaction();
            Query query = session.createQuery("SELECT Max(id.checkinId)FROM CirCheckin where id.libraryId = :library_id and id.sublibraryId=:sublibrary_id ");
            query.setString("library_id",library_id );
            query.setString("sublibrary_id",sublibrary_id );
            return  query.list();
        }
        finally {
            session.close();
        }
*/



        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            Criteria criteria = session.createCriteria(CirCheckin.class);
            Criterion a = Restrictions.eq("id.libraryId", library_id);
            Criterion b = Restrictions.eq("id.sublibraryId", sublibrary_id);
            LogicalExpression le = Restrictions.and(a, b);
            Integer maxbiblio = criteria.add(le).setProjection(Projections.count("id.checkinId")).uniqueResult()==null?0:Integer.valueOf(criteria.add(le).setProjection(Projections.count("id.checkinId")).uniqueResult().toString());
           System.out.println(maxbiblio);

            if (maxbiblio == null) {
                maxbiblio = 1;
            } else {
                maxbiblio++;
            }

            return maxbiblio;
        } finally {
            session.close();
        }

    }

        public static boolean updateCheckin(CirCheckin obj1,CirCheckout obj2,CirMemberAccount obj3,DocumentDetails obj4,CirTransactionHistory obj5)
        {
            Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.save(obj1);
            session.update(obj2);
            session.update(obj3);
            session.update(obj4);
            session.update(obj5);
            tx.commit();
        }
        catch (RuntimeException e) {
                System.out.println("CirculationDAO.Update():*****"+e);
                tx.rollback();
                return false;

        }
        finally
        {
          session.close();
        }

   return true;

        }

         public static boolean updateCheckOut(CirOpacRequest cmd)
        {
            Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
        
            session.update(cmd);
            tx.commit();
        }
        catch (RuntimeException e) {
                System.out.println("CirculationDAO.Update():*****"+e);
                tx.rollback();
                return false;

        }
        finally
        {
         
        }

   return true;

        }

public static boolean delete(String library_id,String sublibrary_id,String memid) {
        Session session =null;
    Transaction tx = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            //acqdocentry = session.load(BibliographicDetails.class, id);
            Query query = session.createQuery("DELETE FROM CirMemberDetail  WHERE  id.memId = :mem_id and id.libraryId=:library_id and id.sublibraryId=:sublibrary_id");

            query.setString("mem_id",memid );
            query.setString("library_id",library_id );
            query.setString("sublibrary_id",sublibrary_id );

            query.executeUpdate();
            tx.commit();

        }
 catch (Exception ex)
        {
            System.out.println(ex);
             return false;

       //  System.out.println(ex.toString());

        }
        finally
        {
        session.close();
        }
   return true;
}
public static boolean deleteAccount(String library_id,String sublibrary_id,String memid) {
        Session session =null;
    Transaction tx = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            //acqdocentry = session.load(BibliographicDetails.class, id);
           Query query = session.createQuery("DELETE FROM CirMemberAccount WHERE  id.memid = :mem_id and id.libraryId=:library_id and id.sublibraryId=:sublibrary_id");

            query.setString("mem_id",memid );
            query.setString("library_id",library_id );
            query.setString("sublibrary_id",sublibrary_id );
            query.executeUpdate();
            tx.commit();

        }
catch (Exception ex)
        {
            System.out.println(ex);
             return false;



        }
        finally
        {
        session.close();
        }
   return true;
}

  public static boolean update(CirMemberDetail cmd)
        {
            Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.update(cmd);

            tx.commit();
        }
        catch (RuntimeException e) {
                System.out.println("CirculationDAO.Update():*****"+e);
                tx.rollback();
                return false;

        }
        finally
        {
          session.close();
        }

   return true;

        }

   public static boolean insert(CirMemberDetail cmd)
        {
            Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.save(cmd);

            tx.commit();
        }
        catch (RuntimeException e) {
                System.out.println("CirculationDAO.Insert():*****"+e);
                tx.rollback();
                return false;

        }
        finally
        {
          session.close();
        }

   return true;

        }

      public static List<CirMemberAccount> getMaxReservationId(String library_id, String mem_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            Criteria criteria = session.createCriteria(CirMemberAccount.class);
            criteria.add(Restrictions.eq("id.libraryId", library_id));
            criteria.add(Restrictions.eq("id.memid", mem_id));
            
            

            return (List<CirMemberAccount>)criteria.list();
        } finally {
            session.close();
        }

    }
      public static boolean updateAccount(CirMemberAccount cmd)
        {
            Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.update(cmd);

            tx.commit();
        }
        catch (RuntimeException e) {
                System.out.println("CirculationDAO.Update():*****"+e);
                tx.rollback();
                return false;

        }
        finally
        {
          session.close();
        }

   return true;

        }
 public static boolean insert(CirOpacRequest cmd)
        {
            Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.save(cmd);

            tx.commit();
        }
        catch (RuntimeException e) {
                System.out.println("CirculationDAO.Insert(CirOpacRequest):*****"+e);
                tx.rollback();
                return false;

        }
        finally
        {
          session.close();
        }

   return true;

        }

public static  List<MixDocumentType> getDocument_Cat_Details(String library_id,String sublibrary_id,String doc_type)
{
        Session session = HibernateUtil.getSessionFactory().openSession();

        try
        {
            session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            String sql="";

            sql = "select * from document_details a,cir_checkout b where a.document_id=b.document_id and a.library_id=b.library_id and a.sublibrary_id=b.sublibrary_id and a.book_type=:book_type and b.library_id=:library_id and b.sublibrary_id=:sublibrary_id";

            System.out.println(sql);

     query =  session.createSQLQuery(sql)
                    .addEntity(DocumentDetails.class)
                    .addEntity(CirCheckout.class)
                    .setResultTransformer(Transformers.aliasToBean(MixDocumentType.class));
          query.setString("library_id", library_id);
          query.setString("sublibrary_id", sublibrary_id);
          query.setString("book_type", doc_type);
         

        }
        catch(Exception e)
        {
        System.out.println(e.toString());
        }
      return (List<MixDocumentType>) query.list();
}





 public static  List<CirMemberDetail> searchCirMemDetails1(String library_id)
{
        Session session = HibernateUtil.getSessionFactory().openSession();

        try
        {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(CirMemberDetail.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))

                    );
            return (List<CirMemberDetail>) criteria.list();


        }
        catch(Exception e){
        System.out.println(e+".......................");
        return null;
        }
        finally
        {
           session.close();
        }
}

public static List  CheckInReport(String library_id,String sub_lib,String year1,String year2,String memid)
    {
        //int count=1;
        Session hsession=HibernateUtil.getSessionFactory().openSession();
        try
        {
         hsession.beginTransaction();
         Criteria criteria = hsession.createCriteria(CirCheckin.class);
         if(!library_id.equalsIgnoreCase("all"))
         criteria.add(Restrictions.eq("id.libraryId",library_id));
         if(!sub_lib.equalsIgnoreCase("all"))
         criteria.add(Restrictions.eq("id.sublibraryId",sub_lib));

         if(!memid.equalsIgnoreCase("all"))
             if(!memid.equals(""))
         criteria.add(Restrictions.eq("memberId",memid));

         if(year1!=null){
           if(!year1.equals(""))
         criteria.add(Restrictions.gt("returningDate",year1));
         }
         if(year2!=null){
          if(!year2.equals(""))
         criteria.add(Restrictions.lt("returningDate",year2));
         }

         return criteria.list();

        }
        catch(Exception e)
        {
            //System.out.println("Error***** OpacSearchDAO.SimpleSearch():"+e);
            return null;
        }
        finally
        {
           hsession.close();
        }
   }

public static List  CheckInReport(String library_id,String sub_lib)
    {
        //int count=1;
        Session hsession=HibernateUtil.getSessionFactory().openSession();
        try
        {
         hsession.beginTransaction();
         Criteria criteria = hsession.createCriteria(CirCheckin.class);

         criteria.add(Restrictions.eq("id.libraryId",library_id));

         criteria.add(Restrictions.eq("id.sublibraryId",sub_lib));



         return criteria.list();

        }
        catch(Exception e)
        {
            //System.out.println("Error***** OpacSearchDAO.SimpleSearch():"+e);
            return null;
        }
        finally
        {
           hsession.close();
        }
   }
 public static List  CheckoutReport(String library_id,String sub_lib,String year1,String year2,String memid)
    {
        //int count=1;
        Session hsession=HibernateUtil.getSessionFactory().openSession();
        try
        {
         hsession.beginTransaction();
         Criteria criteria = hsession.createCriteria(CirCheckout.class);

         criteria.add(Restrictions.eq("id.libraryId",library_id));
         System.out.println("library_id="+library_id);

         criteria.add(Restrictions.eq("id.sublibraryId",sub_lib));
         System.out.println("sublibrary_id="+sub_lib);
          System.out.println("member_id="+memid);

           if(memid!=null)
               if(!memid.equals(""))
        {  criteria.add(Restrictions.eq("memid",memid));}


          System.out.println("year1="+year1);
         if(year1!=null){
           if(!year1.equals(""))
         criteria.add(Restrictions.ge("issueDate",year1));
         }

         System.out.println("year2="+year2);
         if(year2!=null){
           if(!year2.equals(""))
         criteria.add(Restrictions.le("issueDate",year2));
         }

         return criteria.list();

        }
        catch(Exception e)
        {
            System.out.println("Error***** OpacSearchDAO.SimpleSearch():"+e);
            return null;
        }
        finally
        {
           hsession.close();
        }
   }
 public static  CirCheckout searchCheckoutMemDetails(String library_id,String mem_id)
{
        Session session = HibernateUtil.getSessionFactory().openSession();

        try
        {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(CirCheckout.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))

                    .add(Restrictions.eq("memid", mem_id)));
            return (CirCheckout) criteria.uniqueResult();


        }
        catch(Exception e){
        System.out.println(e+".......................");
        return null;
        }
        finally
        {
           session.close();
        }
}
public static  List<CirCheckout> searchCheckoutMemDetails(String library_id,String sublibrary_id,String mem_id)
{
        Session session = HibernateUtil.getSessionFactory().openSession();

        try
        {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(CirCheckout.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.sublibraryId", sublibrary_id))
                    .add(Restrictions.eq("status", "issued"))
                    .add(Restrictions.eq("memid", mem_id)));
            return (List<CirCheckout>) criteria.list();


        }
        catch(Exception e){
        System.out.println(e+".......................");
        return null;
        }
        finally
        {
           session.close();
        }
}

public static  CirCheckout searchCheckoutMemDetails(String library_id,String sublibrary_id,String mem_id,String checkoutId)
{
        Session session = HibernateUtil.getSessionFactory().openSession();

        try
        {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(CirCheckout.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.sublibraryId", sublibrary_id))
                    .add(Restrictions.eq("memid", mem_id))
                    .add(Restrictions.eq("id.checkoutId",Integer.parseInt(checkoutId)))
                    );
            return (CirCheckout) criteria.uniqueResult();


        }
        catch(Exception e){
        System.out.println(e+".......................");
        return null;
        }
        finally
        {
           session.close();
        }
}
public static List<EmployeeType> getAllEmployeeTypes(String library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();
            session.flush();
            Query query1 = session.createQuery("FROM  EmployeeType  WHERE id.libraryId =:library_id");
            query1.setString("library_id", library_id);
            //query1.setString("sublibraryId", sublibraryId);


            return (List<EmployeeType>) query1.list();
        }
        finally {
            session.close();
        }

}

public static List<SubEmployeeType> getAllSubEmployeeTypes(String library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();
            session.flush();
            Query query1 = session.createQuery("FROM  SubEmployeeType  WHERE id.libraryId =:library_id");
            query1.setString("library_id", library_id);
            //query1.setString("sublibraryId", sublibraryId);


            return (List<SubEmployeeType>) query1.list();
        }
        finally {
            session.close();
        }

}
public static List<Faculty> getAllFaculty(String library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();
            session.flush();
            Query query1 = session.createQuery("FROM  Faculty  WHERE id.libraryId =:library_id");
            query1.setString("library_id", library_id);
            //query1.setString("sublibraryId", sublibraryId);


            return (List<Faculty>) query1.list();
        }
        finally {
            session.close();
        }

}

public static List<SubLibrary> getAllSubLibrary(String library_id,String sublibraryId,String memId) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();
            Query query1 = session.createSQLQuery("select * from sub_library s where s.library_id = :library_id and s.library_id=s.sublibrary_id and s.sublibrary_id not in (select cma.sublibrary_id from cir_member_account cma where cma.memid=:memId and  cma.status='Active')")
                    .addEntity(SubLibrary.class);
                    //.setResultTransformer(Transformers.aliasToBean(SubLibrary.class));

            query1.setString("library_id", library_id);
            query1.setString("memId", memId);
            //System.out.println("query = "+query1.toString());

            return (List<SubLibrary>) query1.list();
        }
        finally {
           // session.close();
        }

}
public static List<SubLibrary> getAllSubLibrary1(String library_id,String sublibraryId,String memId) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();
            Query query1 = session.createSQLQuery("select * from sub_library s where s.library_id = :library_id and s.sublibrary_id IN (:library_id,:sublibraryId) and s.sublibrary_id not in (select cma.sublibrary_id from cir_member_account cma where cma.memid=:memId and cma.status='Active')")
                    .addEntity(SubLibrary.class);
                    //.setResultTransformer(Transformers.TO_LIST);
            query1.setString("library_id", library_id);
            query1.setString("sublibraryId", sublibraryId);
            query1.setString("memId", memId);
            //System.out.println("query = "+query1.toString());

            return (List<SubLibrary>) query1.list();
        }
        finally {
           // session.close();
        }



}

public static List<SubLibrary> getAllSubLibrary2(String library_id,String sublibraryId) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();
            Query query1 = session.createSQLQuery("select * from sub_library s where s.library_id = :library_id and s.sublibrary_id =:sublibraryId")
                    .addEntity(SubLibrary.class);
                    //.setResultTransformer(Transformers.TO_LIST);
            query1.setString("library_id", library_id);
            query1.setString("sublibraryId", sublibraryId);
            //query1.setString("memId", memId);
            //System.out.println("query = "+query1.toString());

            return (List<SubLibrary>) query1.list();
        }
        finally {
           // session.close();
        }



}
public static  CirMemberAccount searchCirMemAccountDetails1(String library_id,String sublibrary_id,String mem_id,String password)
{
        Session session = HibernateUtil.getSessionFactory().openSession();

        try
        {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(CirMemberAccount.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.sublibraryId", sublibrary_id))
                    .add(Restrictions.gt("expiryDate", DateCalculation.now()))
                    .add(Restrictions.eq("id.memid", mem_id))
                    .add(Restrictions.eq("password", password)));
            return (CirMemberAccount) criteria.uniqueResult();


        }
        finally
        {
           session.close();
        }
}

public static  CirMemberAccount searchCirMemAccountDetails(String library_id,String sublibrary_id,String mem_id,String password)
{
        Session session = HibernateUtil.getSessionFactory().openSession();

        try
        {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(CirMemberAccount.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.sublibraryId", sublibrary_id))
                    .add(Restrictions.eq("id.memid", mem_id))
                    .add(Restrictions.eq("password", password)));
            return (CirMemberAccount) criteria.uniqueResult();


        }
        finally
        {
           session.close();
        }
}

}
