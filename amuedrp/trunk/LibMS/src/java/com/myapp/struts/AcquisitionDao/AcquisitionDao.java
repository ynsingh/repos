/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.AcquisitionDao;
//import acquisition.approval;
import com.myapp.struts.Acquisition.ApprovalList;
import com.myapp.struts.Acquisition.CirculationList_1_1;
import com.myapp.struts.Acquisition.approval_1;
import  com.myapp.struts.hbm.*;
import java.util.ArrayList;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Property;
import org.hibernate.transform.Transformers;
/**
 *
 * @author maqbool
 */
public class AcquisitionDao {

     public AcqRecievingHeader getRecieveOrder( String library_id, String sub_library_id,String recieving_no,String order_no,String vendor_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx=session.beginTransaction();
        try {
            Criteria criteria = session.createCriteria(AcqRecievingHeader.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.subLibraryId", sub_library_id))
                    .add(Restrictions.eq("orderNo", order_no))
                    .add(Restrictions.eq("vendorId", vendor_id))
                    .add(Restrictions.eq("id.recievingNo", recieving_no)));
            return (AcqRecievingHeader) criteria.uniqueResult();
        } finally {
           // session.close();
        }
    }
  public  List<AcqOrder1> BibliobyControlId2(String library_id,String sub_library_id,int con_no,String order_no) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
 Transaction tx=session.beginTransaction();
        try {
            Criteria criteria = session.createCriteria(AcqOrder1.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.subLibraryId", sub_library_id))
                    .add(Restrictions.eq("id.orderNo", order_no))
                    .add(Restrictions.eq("controlNo",con_no )));
                   return  criteria.list();
        } finally {
            session.close();
        }
  }

  public List<AcqOrder1> getOrderno1( String library_id, String sub_library_id,String order_no,int con_no ) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx=session.beginTransaction();
        try {
            Criteria criteria = session.createCriteria(AcqOrder1.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.subLibraryId", sub_library_id))
                    .add(Restrictions.eq("controlNo", con_no))

                    .add(Restrictions.eq("id.orderNo", order_no)));
            return (List<AcqOrder1>) criteria.list();
        } finally {
            //session.close();
        }
    }

    public AcqBibliographyDetails BibliobyControlIdonApproval(String library_id,String sub_library_id,int con_no) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
 Transaction tx=session.beginTransaction();
        try {
            Criteria criteria = session.createCriteria(AcqBibliographyDetails.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.subLibraryId", sub_library_id))
                    .add(Restrictions.eq("id.controlNo",con_no )));

            return (AcqBibliographyDetails) criteria.uniqueResult();
        }
        finally
        {
        session.close();
        }
    }
    
public AcqApproval BibliobyControlId3(String library_id,String sub_library_id,int con_no,int x) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
 Transaction tx=session.beginTransaction();
        try {
            Criteria criteria = session.createCriteria(AcqApproval.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.subLibraryId", sub_library_id))
                    .add(Restrictions.eq("controlNo",con_no ))
                    .add(Restrictions.eq("id.approvalItemId",x )));
            return (AcqApproval) criteria.uniqueResult();
        } finally {
            session.close();
        }
  }


            public Integer returnMaxRecievingItemId(String library_id, String sublibrary_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
         Transaction tx=session.beginTransaction();
        try {
            Criteria criteria = session.createCriteria(AcqRecievingDetails.class);
            Criterion a = Restrictions.eq("id.libraryId", library_id);
            Criterion b = Restrictions.eq("id.subLibraryId", sublibrary_id);
            LogicalExpression le = Restrictions.and(a, b);
            Integer maxbiblio = (Integer) criteria.add(le).setProjection(Projections.max("id.recievingItemId")).uniqueResult();
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
                public void insert5(AcqRecievingDetails bibDetails) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(bibDetails);
            tx.commit();
        } catch (RuntimeException e) {
            //if(bibDetails != null)
            tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    public void insert7(AcqRecievingHeader bibDetails) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(bibDetails);
            tx.commit();
        } catch (RuntimeException e) {
            //if(bibDetails != null)
            tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }


    public List searchDoc5(String library_id, String sub_library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Session hsession=HibernateUtil.getSessionFactory().openSession();
        try
        {
         hsession.beginTransaction();
          session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            String sql="";

              sql = "(select a.isbn,b.acq_mode,ca.approval_no,d.vendor_id,ca.approval_item_id,b.control_no,a.title,a.author,ca.no_of_copies from acq_bibliography a,acq_bibliography_details b,acq_approval ca,acq_approval_header d where a.title_id=b.title_id and b.acq_mode='Approved' and b.control_no=ca.control_no and b.library_id=ca.library_id and b.sub_library_id=ca.sub_library_id and ca.approval_no=d.approval_no and ca.library_id=d.library_id and ca.sub_library_id=d.sub_library_id and ca.status='pending' and a.Library_id='"+library_id+"' and a.sub_library_id='"+sub_library_id+"')";




          Query query =  session.createSQLQuery(sql)
                    .setResultTransformer(Transformers.aliasToBean(approval_1.class));

          System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            return (List<approval_1>)query.list();

            

        } finally {
            session.close();
        }
    }


    public static List  CheckInReport2(String library_id,String sub_lib)
    {
        //int count=1;
    Session session=null;
    Transaction tx=null;
        Session hsession=HibernateUtil.getSessionFactory().openSession();
        try
        {
         hsession.beginTransaction();
          session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            String sql="";

             // sql = "(select a.approval_no,a.vendor_id,b.approval_item_id,b.control_no from acq_approval_header a,acq_approval b where a.library_id=b.library_id and a.sub_library_id=b.sub_library_id and a.approval_no=b.approval_no order by a.approval_no )";
 sql = "(select b.control_no,a.title,a.author,b.no_of_copies,b.unit_price,b.currency,ac.budgethead_name from acq_bibliography  a,acq_bibliography_details b ,acq_budget ac  where a.library_id=b.library_id and a.sub_library_id=b.sub_library_id and a.title_id=b.title_id and b.library_id=ac.library_id and b.acq_mode='On Approval')";



          Query query =  session.createSQLQuery(sql)
                    .setResultTransformer(Transformers.aliasToBean(CirculationList_1_1.class));

          System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            return (List<CirculationList_1_1>)query.list();
/*
        *  Criteria criteria = hsession.createCriteria(CirCheckin.class);
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

         return criteria.list();*/

        }
        catch(Exception e)
        {
            System.out.println("Error***** OpacSearchDAO.SimpleSearch()lllllllllllllllllllllllllllllllllllllllllllll:"+e);
            return null;
        }
        finally
        {
           hsession.close();
        }
   }
 public static List  approvalReport(String library_id,String sub_lib)
    {
        //int count=1;
    Session session=null;
    Transaction tx=null;
        Session hsession=HibernateUtil.getSessionFactory().openSession();
        try
        {
         hsession.beginTransaction();
          session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            String sql="";

             // sql = "(select a.approval_no,a.vendor_id,b.approval_item_id,b.control_no from acq_approval_header a,acq_approval b where a.library_id=b.library_id and a.sub_library_id=b.sub_library_id and a.approval_no=b.approval_no order by a.approval_no )";
 sql = "(select b.library_id,b.vendor,b.control_no,a.title,a.author,b.no_of_copies,b.unit_price,b.currency,ac.budgethead_name from acq_bibliography  a,acq_bibliography_details b ,acq_budget ac  where a.library_id=b.library_id and a.sub_library_id=b.sub_library_id and a.title_id=b.title_id and b.library_id=ac.library_id and b.acq_mode='Firm Order' and b.status is null)";



          Query query =  session.createSQLQuery(sql)
                    .setResultTransformer(Transformers.aliasToBean(CirculationList_1_1.class));

          System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            return (List<CirculationList_1_1>)query.list();
/*
        *  Criteria criteria = hsession.createCriteria(CirCheckin.class);
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

         return criteria.list();*/

        }
        catch(Exception e)
        {
            System.out.println("Error***** OpacSearchDAO.SimpleSearch()lllllllllllllllllllllllllllllllllllllllllllll:"+e);
            return null;
        }
        finally
        {
           hsession.close();
        }
   }


    public void insert(AcqBibliography bibDetails) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(bibDetails);
            tx.commit();
        } catch (RuntimeException e) {
            //if(bibDetails != null)
            tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }
      public void insert1(AcqBibliographyDetails bibDetails) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(bibDetails);
            tx.commit();
        } catch (RuntimeException e) {
            //if(bibDetails != null)
            tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }
            public void insert2(AcqApprovalHeader bibDetails) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(bibDetails);
            tx.commit();
        } catch (RuntimeException e) {
            //if(bibDetails != null)
            tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }
                        public void updateApprovalHeader(AcqApprovalHeader bibDetails) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(bibDetails);
            tx.commit();
        } catch (RuntimeException e) {
            //if(bibDetails != null)
            tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

                                public static boolean updateAcqBib(AcqBibliographyDetails bibDetails) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(bibDetails);
            tx.commit();
        } catch (RuntimeException e) {
            //if(bibDetails != null)
            tx.rollback();
            return false;
            
        } finally {
            session.close();
        }
        return true;
    }

                public void insert3(AcqApproval bibDetails) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(bibDetails);
            tx.commit();
        } catch (RuntimeException e) {
            //if(bibDetails != null)
            tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }



     public void insert4(AcqVendor acqVendorDetails) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(acqVendorDetails);
            tx.commit();
        } catch (RuntimeException e) {
            //if(bibDetails != null)
            tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }



      public void update2(AcqVendor acqVendorDetails) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.update(acqVendorDetails);
            tx.commit();

        }
         catch (Exception ex)
        {
             tx.rollback();
         System.out.println(ex.toString());
           //  return false;



        }
        finally
        {
          session.close();
        }
 //  return true;

    }
       public void update2(AcqOrder1 acqorder1) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.update(acqorder1);
            tx.commit();

        }
         catch (Exception ex)
        {
             tx.rollback();
         System.out.println(ex.toString());
           //  return false;



        }
        finally
        {
          session.close();
        }
 //  return true;

    }


   public static boolean updateApproval(AcqApproval app) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.update(app);
            tx.commit();
        } catch (RuntimeException e) {
            //  if(bibDetails != null)
            tx.rollback();
            return false;
           // throw e;
        } finally {
          //  session.close();
        }
        return true;
    }

        public void update(AcqBibliographyDetails bibDetails) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.update(bibDetails);
            tx.commit();
        } catch (RuntimeException e) {
            //  if(bibDetails != null)
            tx.rollback();
            throw e;
        } finally {
          //  session.close();
        }
    }
            public Integer returnMaxBiblioId(String library_id, String sublibrary_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx=session.beginTransaction();
        try {
            Criteria criteria = session.createCriteria(AcqBibliography.class);
            Criterion a = Restrictions.eq("id.libraryId", library_id);
            Criterion b = Restrictions.eq("id.subLibraryId", sublibrary_id);
            LogicalExpression le = Restrictions.and(a, b);
            Integer maxbiblio = (Integer) criteria.add(le).setProjection(Projections.max("id.titleId")).uniqueResult();
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
        public Integer returnMaxItemId(String library_id, String sublibrary_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
         Transaction tx=session.beginTransaction();
        try {
            Criteria criteria = session.createCriteria(AcqApproval.class);
            Criterion a = Restrictions.eq("id.libraryId", library_id);
            Criterion b = Restrictions.eq("id.subLibraryId", sublibrary_id);
            LogicalExpression le = Restrictions.and(a, b);
            Integer maxbiblio = (Integer) criteria.add(le).setProjection(Projections.max("id.approvalItemId")).uniqueResult();
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
        public Integer returnMaxControlNo(String library_id, String sublibrary_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
         Transaction tx=session.beginTransaction();
        try {
            Criteria criteria = session.createCriteria(AcqBibliographyDetails.class);
            Criterion a = Restrictions.eq("id.libraryId", library_id);
            Criterion b = Restrictions.eq("id.subLibraryId", sublibrary_id);
            LogicalExpression le = Restrictions.and(a, b);
            Integer maxbiblio = (Integer) criteria.add(le).setProjection(Projections.max("id.controlNo")).uniqueResult();
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
         public Integer returnMaxTransNo(String library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
         Transaction tx=session.beginTransaction();
        try {
            Criteria criteria = session.createCriteria(AcqBudgetTransaction.class);
            Criterion a = Restrictions.eq("id.libraryId", library_id);


            Integer maxbiblio = (Integer) criteria.add(a).setProjection(Projections.max("id.transactionId")).uniqueResult();
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
           public static AcqApproval searchApproval(String library_id,String sublibrary_id,String approval_no,int control_no) {
        Session session = HibernateUtil.getSessionFactory().openSession();
         Transaction tx=session.beginTransaction();
        try {
            Criteria criteria = session.createCriteria(AcqApproval.class)

                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.subLibraryId", sublibrary_id))
                    .add(Restrictions.eq("controlNo", control_no))
                    .add(Restrictions.eq("approvalNo", approval_no));

            return (AcqApproval) criteria.uniqueResult();
        } finally {
            session.close();
        }
    }

               public static AcqVendor searchVendor(String library_id,String vendor_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
         Transaction tx=session.beginTransaction();
        try {
            Criteria criteria = session.createCriteria(AcqVendor.class)

                    .add(Restrictions.eq("id.libraryId", library_id))


                    .add(Restrictions.eq("id.vendorId", vendor_id));

            return (AcqVendor) criteria.uniqueResult();
        } finally {
            session.close();
        }
    }


               public static AcqCurrency searchVendorCurrency(String library_id,String SourceCurrency) {
        Session session = HibernateUtil.getSessionFactory().openSession();
         Transaction tx=session.beginTransaction();
        try {
            Criteria criteria = session.createCriteria(AcqCurrency.class)

                    .add(Restrictions.eq("id.libraryId", library_id))


                    .add(Restrictions.eq("sourceCurrency", SourceCurrency));

            return (AcqCurrency) criteria.uniqueResult();
        } finally {
            session.close();
        }
    }


             public static List<AcqApproval> searchApproval2(String library_id,String sublibrary_id,String approval_no) {
        Session session = HibernateUtil.getSessionFactory().openSession();
         Transaction tx=session.beginTransaction();
        try {
            Criteria criteria = session.createCriteria(AcqApproval.class)

                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.subLibraryId", sublibrary_id))
                    .add(Restrictions.eq("approvalNo", approval_no));

            return (List<AcqApproval>) criteria.list();
        } finally {
            session.close();
        }
    }

             public static List<AcqApproval> searchApprovalStatus(String library_id,String sublibrary_id,int control_no) {
        Session session = HibernateUtil.getSessionFactory().openSession();
         Transaction tx=session.beginTransaction();
        try {
            Criteria criteria = session.createCriteria(AcqApproval.class)

                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.subLibraryId", sublibrary_id))
                    .add(Restrictions.eq("controlNo", control_no))
                    .add(Restrictions.ne("status", "Ordered"));
                    

            return (List<AcqApproval>) criteria.list();
        } finally {
            session.close();
        }
    }
             public static AcqApproval searchApprovalStatus1(String library_id,String sublibrary_id,int control_no,int approval_item_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
         Transaction tx=session.beginTransaction();
        try {
            Criteria criteria = session.createCriteria(AcqApproval.class)

                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.subLibraryId", sublibrary_id))
                    .add(Restrictions.eq("controlNo", control_no))
                    .add(Restrictions.eq("id.approvalItemId", approval_item_id))
                    .add(Restrictions.eq("status", "Ordered"));


            return (AcqApproval) criteria.uniqueResult();
        } finally {
            session.close();
        }
    }

               public static AcqApproval searchAcqApproval(String library_id,String sublibrary_id,int approval_id)
               {        Session session = HibernateUtil.getSessionFactory().openSession();
         Transaction tx=session.beginTransaction();
        try {
            Criteria criteria = session.createCriteria(AcqApproval.class)

                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.subLibraryId", sublibrary_id))
                    .add(Restrictions.eq("id.approvalItemId", approval_id)
                    );


            return (AcqApproval) criteria.uniqueResult();
        } finally {
            session.close();
        }
    }


     public static List<AcqOrder1> searchAcqOrder(String library_id,String sublibrary_id,int control_no,String order_no) {
        Session session = HibernateUtil.getSessionFactory().openSession();
         Transaction tx=session.beginTransaction();
        try {
            Criteria criteria = session.createCriteria(AcqOrder1.class)

                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.subLibraryId", sublibrary_id))
                    .add(Restrictions.eq("id.orderNo", order_no))
                    .add(Restrictions.eq("controlNo", control_no)
                    );
                    

            return (List<AcqOrder1>) criteria.list();
        } finally {
            session.close();
        }
    }

    public List getBiblio(String library_id,String sublibrary_id,String search_by, String search_keyword, String sort_by) {
        Session session = HibernateUtil.getSessionFactory().openSession();
         Transaction tx=session.beginTransaction();
        try {
            Criteria criteria = session.createCriteria(AcqBibliography.class)
                    //.add(Restrictions.conjunction())
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.subLibraryId", sublibrary_id))
                    .add(Restrictions.ilike(search_by,search_keyword+"%"))
                    .addOrder(Property.forName(sort_by).asc());
            return (List) criteria.list();
        } finally {
            session.close();
        }
    }
  public static AcqBibliographyDetails searchAcqBibByControlNo(String library_id,String sublibrary_id,int control_no) {
         Session session = HibernateUtil.getSessionFactory().openSession();
         Transaction tx=session.beginTransaction();
        try {
            Criteria criteria = session.createCriteria(AcqBibliographyDetails.class)
         // .setProjection(Projections.groupProperty("vendor"))
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.subLibraryId", sublibrary_id))
                    .add(Restrictions.eq("id.controlNo", control_no))

                    );

//criteria.setProjection(Property.forName("vendor").group().as("vendor"));
            return (AcqBibliographyDetails)criteria.uniqueResult();
        } finally {
         // session.close();
        }

    }

   public static AcqBibliographyDetails searchAcqBibPartialByControlNo(String library_id,String sublibrary_id,int control_no) {
         Session session = HibernateUtil.getSessionFactory().openSession();
         Transaction tx=session.beginTransaction();
        try {
            Criteria criteria = session.createCriteria(AcqBibliographyDetails.class)
         // .setProjection(Projections.groupProperty("vendor"))
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.subLibraryId", sublibrary_id))
                    .add(Restrictions.eq("id.controlNo", control_no))
                    .add(Restrictions.eq("status", "Partially Approved"))

                    );

//criteria.setProjection(Property.forName("vendor").group().as("vendor"));
            return (AcqBibliographyDetails)criteria.uniqueResult();
        } finally {
         // session.close();
        }

    }



      public ArrayList<ApprovalList> getBibByControlNo(String library_id,String sublibrary_id,String approval_no) {

          Session session =null;
    Transaction tx = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            String sql="";

            sql = "select  b.control_no,a.no_of_copies,ca.author,ca.isbn,ca.title,b.unit_price,a.approval_no from acq_approval a,acq_bibliography_details b, acq_bibliography ca where a.library_id=b.library_id and a.sub_library_id=b.sub_library_id and a.control_no=b.control_no and b.library_id=ca.library_id and b.sub_library_id=ca.sub_library_id and b.title_id=ca.title_id and b.library_id='"+library_id+"' and b.sub_library_id='"+sublibrary_id+"' and a.approval_no='"+approval_no+"'";



          Query query =  session.createSQLQuery(sql)
                    .setResultTransformer(Transformers.aliasToBean(ApprovalList.class));
            return (ArrayList<ApprovalList>) query.list();
        }
        finally {
            session.close();
        }
         
    }

    public static AcqApprovalHeader searchApprovalHeaderByOrderNo(String library_id,String sublibrary_id,String order_no) {
         Session session = HibernateUtil.getSessionFactory().openSession();
         Transaction tx=session.beginTransaction();
        try {
            Criteria criteria = session.createCriteria(AcqApprovalHeader.class)
         // .setProjection(Projections.groupProperty("vendor"))
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.subLibraryId", sublibrary_id))
                    .add(Restrictions.eq("id.orderNo", order_no))

                    );

//criteria.setProjection(Property.forName("vendor").group().as("vendor"));
            return (AcqApprovalHeader)criteria.uniqueResult();
        } finally {
          //session.close();
        }

    }
  

    public AcqBibliography BibliobyTitleId(int title_id, String library_id, String sub_library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx=session.beginTransaction();
        try {
            Criteria criteria = session.createCriteria(AcqBibliography.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.subLibraryId", sub_library_id))
                    .add(Restrictions.eq("id.titleId", title_id)));
            return (AcqBibliography) criteria.uniqueResult();
        } finally {
            session.close();
        }
    }


    /*
     *
     *
     *
     */
    public AcqBibliographyDetails BibliobyControlId(int control_no, String library_id, String sub_library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx=session.beginTransaction();
        try {
            Criteria criteria = session.createCriteria(AcqBibliographyDetails.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.subLibraryId", sub_library_id))
                    .add(Restrictions.eq("id.controlNo", control_no)));
            return (AcqBibliographyDetails) criteria.uniqueResult();
        } finally {
           // session.close();
        }
    }

      public AcqApprovalHeader getApproval( String library_id, String sub_library_id,String approval) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx=session.beginTransaction();
        try {
            Criteria criteria = session.createCriteria(AcqApprovalHeader.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.subLibraryId", sub_library_id))
                    .add(Restrictions.eq("id.approvalNo", approval)));
            return (AcqApprovalHeader) criteria.uniqueResult();
        } finally {
           // session.close();
        }
    }

       public List<AcqApproval> getApproval1( String library_id, String sub_library_id,String approval) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx=session.beginTransaction();
        try {
            Criteria criteria = session.createCriteria(AcqApproval.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.subLibraryId", sub_library_id))
                    .add(Restrictions.eq("approvalNo", approval)));
            return (List<AcqApproval>) criteria.list();
        } finally {
            //session.close();
        }
    }

        public List<AcqBibliographyDetails> getPendingApproval( String library_id, String sub_library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx=session.beginTransaction();
        try {
            Criteria criteria = session.createCriteria(AcqBibliographyDetails.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.subLibraryId", sub_library_id))
                    .add(Restrictions.eq("acqMode","On Approval")));
            return (List<AcqBibliographyDetails>) criteria.list();
        } finally {
            //session.close();
        }
    }

    public List<AcqBibliographyDetails> searchDoc1(String library_id, String sub_library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
         Transaction tx=session.beginTransaction();
        try {
            Criteria criteria = session.createCriteria(AcqBibliographyDetails.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.subLibraryId", sub_library_id))
                    .add(Restrictions.eq("acqMode", "On Approval")));
            return criteria.list();
        } finally {
          session.close();
        }
    }

 public List<ApprovalList> searchOnApprovalList(String library_id, String sub_library_id) {
     Session session =null;
    Transaction tx = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            String sql="";

            sql = "select  b.control_no,b.no_of_copies,a.author,a.isbn,a.title,b.unit_price,b.acq_mode from acq_bibliography a,acq_bibliography_details b where a.library_id=b.library_id and a.sub_library_id=b.sub_library_id and a.title_id=b.title_id and b.acq_mode='On Approval'  and  b.library_id='"+library_id+"' and b.sub_library_id='"+sub_library_id+"'";



          Query query =  session.createSQLQuery(sql)
                    .setResultTransformer(Transformers.aliasToBean(ApprovalList.class));
            return (List<ApprovalList>)query.list();
        }
        finally {
            session.close();
        }
    }

     public List<AcqBibliographyDetails> searchDoc3(String library_id, String sub_library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
         Transaction tx=session.beginTransaction();
        try {
            Criteria criteria = session.createCriteria(AcqBibliographyDetails.class)
         // .setProjection(Projections.groupProperty("vendor"))
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.subLibraryId", sub_library_id))
                    .add(Restrictions.eq("acqMode", "Firm Order"))
                  // .add(Property.forName("vendor").group().as("vendor"))
                    );
            
//criteria.setProjection(Property.forName("vendor").group().as("vendor"));
            return criteria.list();
        } finally {
          //session.close();
        }
    }



     public List<AcqBibliographyDetails> searchDoc4(String library_id, String sub_library_id) {
        Session session =null;
    Transaction tx = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            //int i = registrationId.intValue();
            Query query = session.createQuery("FROM AcqBibliographyDetails where  id.libraryId = :l and id.subLibraryId = :subLibraryId and acqMode = :acqMode group by vendor ");
           query.setString("l",library_id);
           query.setString("subLibraryId",sub_library_id);
           query.setString("acqMode","Firm Order");
            return query.list();
        }
        finally {
          //  session.close();
        }
    }
    public List getBiblioDetails(String library_id,String sublibrary_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
         Transaction tx=session.beginTransaction();
        try {
            Criteria criteria = session.createCriteria(AcqBibliographyDetails.class)
                    //.add(Restrictions.conjunction())
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.subLibraryId", sublibrary_id));
                   // .add(Restrictions.ilike(search_by,search_keyword+"%"))
                    //.addOrder(Property.forName(sort_by).asc());
            return (List) criteria.list();
        } finally {
           // session.close();
        }
        
    }

     public List getBiblioVendor(String library_id,String sublibrary_id,String vendor) {
        Session session = HibernateUtil.getSessionFactory().openSession();
         Transaction tx=session.beginTransaction();
        try {
            Criteria criteria = session.createCriteria(AcqBibliographyDetails.class)
                    //.add(Restrictions.conjunction())
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.subLibraryId", sublibrary_id))
                    .add(Restrictions.eq("vendor", vendor));
                   // .add(Restrictions.ilike(search_by,search_keyword+"%"))
                    //.addOrder(Property.forName(sort_by).asc());
            return (List) criteria.list();
        } finally {
           // session.close();
        }

    }

     public List getAcqBiblio2(String library_id, String sub_library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
         Transaction tx=session.beginTransaction();
        try {
            Criteria criteria = session.createCriteria(AcqBibliographyDetails.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.subLibraryId", sub_library_id)));
            return criteria.list();
        } finally {
           // session.close();
        }
    }

     public AcqBibliographyDetails BibliobyControlId(String library_id,String sub_library_id,int control_no) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
 Transaction tx=session.beginTransaction();
        try {
            Criteria criteria = session.createCriteria(AcqBibliographyDetails.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.subLibraryId", sub_library_id))
                    .add(Restrictions.eq("id.controlNo",control_no )));
            return (AcqBibliographyDetails) criteria.uniqueResult();
        } finally {
            session.close();
        }
  }

   public List searchDoc2(String library_id, String sub_library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
         Transaction tx=session.beginTransaction();
        try {
            Criteria criteria = session.createCriteria(AcqBibliographyDetails.class)
                    .add(Restrictions.conjunction().add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.subLibraryId", sub_library_id))
                      .add(Restrictions.ne("status", "Ordered"))
                      .add(Restrictions.ne("status", "Ordered/PartiallyRecieved"))
                    .add(Restrictions.eq("acqMode", "Firm Order")));
            return criteria.list();
        } finally {
           // session.close();
        }
    }

public List<ApprovalList> getApprovalList(String library_id, String sub_library_id,String vendor){
  Session session =null;
    Transaction tx = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            String sql="";

            sql = "(select  b.control_no,ca.author,a.no_of_copies,ca.isbn,ca.title,b.unit_price,a.approval_no,b.status from acq_approval a,acq_approval_header d,acq_bibliography_details b, acq_bibliography ca where a.library_id=d.library_id and a.sub_library_id=d.sub_library_id and a.approval_no=d.approval_no and a.library_id=b.library_id and a.sub_library_id=b.sub_library_id and a.control_no=b.control_no and b.library_id=ca.library_id and b.sub_library_id=ca.sub_library_id and b.title_id=ca.title_id and (b.acq_mode='On Approval' and b.status='Partially Approved')   and b.library_id='"+library_id+"' and b.sub_library_id='"+sub_library_id+"' and d.vendor_id='"+vendor+"' and a.status='pending')"
+" union all (select  b.control_no,ca.author,a.no_of_copies,ca.isbn,ca.title,b.unit_price,a.approval_no,b.status from acq_approval a,acq_approval_header d,acq_bibliography_details b, acq_bibliography ca where a.library_id=d.library_id and a.sub_library_id=d.sub_library_id and a.approval_no=d.approval_no  and a.library_id=b.library_id and a.sub_library_id=b.sub_library_id and a.control_no=b.control_no and b.library_id=ca.library_id and b.sub_library_id=ca.sub_library_id and b.title_id=ca.title_id and (b.acq_mode='Approved' and b.status='Fully Approved')   and b.library_id='"+library_id+"' and b.sub_library_id='"+sub_library_id+"' and d.vendor_id='"+vendor+"' and a.status='pending')"
 +" union All (select b.control_no,ca.author,b.no_of_copies,ca.isbn,ca.title,b.unit_price,b.volume,b.acq_mode from acq_bibliography_details b,acq_bibliography ca where  b.library_id=ca.library_id and b.sub_library_id=ca.sub_library_id and b.title_id=ca.title_id and b.library_id='"+library_id+"' and b.sub_library_id='"+sub_library_id+"' and b.acq_mode='Firm Order' and b.vendor='"+vendor+"' and b.status is null)";




          Query query =  session.createSQLQuery(sql)
                    .setResultTransformer(Transformers.aliasToBean(ApprovalList.class));
            return (List<ApprovalList>)query.list();
        }
        finally {
            session.close();
        }
}

public ApprovalList getApprovalListbySelectionF(String library_id, String sub_library_id,int control_no){
  Session session =null;
    Transaction tx = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            String sql="";

           

            sql = "select b.control_no,b.no_of_copies,ca.author,ca.isbn,ca.title,b.unit_price,b.volume from acq_bibliography_details b,acq_bibliography ca where b.library_id=ca.library_id and b.sub_library_id=ca.sub_library_id and b.title_id=ca.title_id and b.library_id='"+library_id+"' and b.sub_library_id='"+sub_library_id+"' and b.acq_mode='Firm Order' and b.control_no="+control_no;
           


          Query query =  session.createSQLQuery(sql)
                    .setResultTransformer(Transformers.aliasToBean(ApprovalList.class));
            return (ApprovalList)query.uniqueResult();
        }
        finally {
            session.close();
        }
}

public ApprovalList getApprovalListbySelectionA(String library_id, String sub_library_id,String approval_no,int control_no){
  Session session =null;
    Transaction tx = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            String sql="";

          

                        sql="select  b.control_no,ca.author,a.no_of_copies,ca.isbn,ca.title,b.unit_price,a.approval_no from acq_approval a,acq_bibliography_details b, acq_bibliography ca where a.library_id=b.library_id and a.sub_library_id=b.sub_library_id and a.control_no=b.control_no and b.library_id=ca.library_id and b.sub_library_id=ca.sub_library_id and b.title_id=ca.title_id and b.library_id='"+library_id+"' and b.sub_library_id='"+sub_library_id+"' and a.approval_no='"+approval_no+"' and a.control_no='"+control_no+"'";
        


          Query query =  session.createSQLQuery(sql)
                    .setResultTransformer(Transformers.aliasToBean(ApprovalList.class));
            return (ApprovalList)query.uniqueResult();
        }
        finally {
            session.close();
        }
}





   public List searchListByVendor(String library_id, String sub_library_id,String vendor) {
        Session session = HibernateUtil.getSessionFactory().openSession();
         Transaction tx=session.beginTransaction();
        try {
            Criteria criteria = session.createCriteria(AcqBibliographyDetails.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.subLibraryId", sub_library_id))
                    .add(Restrictions.eq("acqMode","Firm Order"))
                    .add(Restrictions.eq("vendor", vendor)));
         
      
              return criteria.list();
        } finally {
            session.close();
        }

       


   }

  /**
   * This method is used for getting list of title from AcqBibliography
   * @param library_id
   * @param sub_library_id
   * @param title
   * @return
   */

    public List searchDoc1(String library_id, String sub_library_id,String title) {
        Session session = HibernateUtil.getSessionFactory().openSession();
         Transaction tx=session.beginTransaction();
        try {
            Criteria criteria = session.createCriteria(AcqBibliography.class)
                    .add(Restrictions.conjunction().add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.subLibraryId", sub_library_id))
                    .add(Restrictions.eq("title",title)));
            return criteria.list();
        } finally {
           // session.close();
        }
    }
/**
   * This method is used get title from AcqBibliography from titleid
   * @param library_id
   * @param sub_library_id
   * @param title
   * @return
   */


     public static List<String> searchDoc6(String library_id, String sub_library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
         Transaction tx=session.beginTransaction();
        try {
            Criteria criteria = session.createCriteria(AcqBibliographyDetails.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.subLibraryId", sub_library_id)));
            Criterion a= Restrictions.eq("acqMode","Firm Order");   
            Criterion b= Restrictions.eq("acqMode", "Approved");
            LogicalExpression le1=Restrictions.or(a, b);
            Criterion c= Restrictions.eq("status", "Partially Approved");
            LogicalExpression le = Restrictions.or(le1,c);
            criteria.add(le);
            criteria.setProjection(Projections.distinct(Property.forName("vendor")));
            return criteria.list();
        } finally {
            session.close();
        }
    }





    public AcqBibliography getBiblio(String library_id, String sub_library_id, int title_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            Criteria criteria = session.createCriteria(AcqBibliography.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.subLibraryId", sub_library_id))
                    .add(Restrictions.eq("id.titleId", title_id)));
            return (AcqBibliography) criteria.uniqueResult();
        } finally {
            session.close();
        }
    }
       public AcqBibliographyDetails getBiblioBytitleid(String library_id, String sub_library_id, int title_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            Criteria criteria = session.createCriteria(AcqBibliographyDetails.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.subLibraryId", sub_library_id))
                    .add(Restrictions.eq("titleId", title_id)));
            return (AcqBibliographyDetails) criteria.uniqueResult();
        } finally {
            session.close();
        }
    }
        public AcqVendor getBiblio(String library_id, String sub_library_id, String vendor_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            Criteria criteria = session.createCriteria(AcqVendor.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.subLibraryId", sub_library_id))
                    .add(Restrictions.eq("id.vendorId", vendor_id)));
            return (AcqVendor) criteria.uniqueResult();
        } finally {
            //session.close();
        }
    }



   public List searchDoc3(String library_id, String sub_library_id,String title) {
        Session session = HibernateUtil.getSessionFactory().openSession();
         Transaction tx=session.beginTransaction();
        try {
            Criteria criteria = session.createCriteria(AcqBibliography.class)
                    .add(Restrictions.conjunction().add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.subLibraryId", sub_library_id))
                    .add(Restrictions.eq("title",title)));
            return  criteria.list();
        } finally {
           // session.close();
        }
    }


     public List searchDoc4(String library_id, String sub_library_id,int control_no) {
        Session session = HibernateUtil.getSessionFactory().openSession();
         Transaction tx=session.beginTransaction();
        try {
            Criteria criteria = session.createCriteria(AcqBibliography.class)
                    .add(Restrictions.conjunction().add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.subLibraryId", sub_library_id))
                    .add(Restrictions.eq("id.controlNo",control_no)));
            return criteria.list();
        } finally {
           // session.close();
        }
    }


      public AcqVendor searchDoc5(String library_id, String sub_library_id,String vendor_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
         Transaction tx=session.beginTransaction();
        try {
            Criteria criteria = session.createCriteria(AcqVendor.class)
                    .add(Restrictions.conjunction().add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.subLibraryId", sub_library_id))
                    .add(Restrictions.eq("id.vendorId",vendor_id)));
            return (AcqVendor)criteria.uniqueResult();
        } finally {
           // session.close();
        }
    }

      public void update1(AcqBibliography bibDetails) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.update(bibDetails);
            tx.commit();
        } catch (RuntimeException e) {
            //  if(bibDetails != null)
            tx.rollback();
            throw e;
        } finally {
           session.close();
        }
    }
       public List getTitles(String title, String library_id, String sub_library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            Criteria criteria = session.createCriteria(BibliographicDetails.class).add(Restrictions.conjunction().add(Restrictions.eq("id.libraryId", library_id)).add(Restrictions.eq("id.sublibraryId", sub_library_id)).add(Restrictions.eq("title", title)));
            return (List) criteria.list();
        } finally {
            session.close();
        }
    }

         public void delete1(int title, String library_id, String sub_library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        //Object acqdocentry = null;

        try {
            tx = session.beginTransaction();
            //acqdocentry = session.load(BibliographicDetails.class, id);
            Query query = session.createQuery("DELETE FROM AcqBibliography  WHERE  id.titleId = :titleId and id.libraryId = :libraryId and id.subLibraryId = :subLibraryId");

            query.setInteger("titleId", title);
            query.setString("libraryId", library_id);
            query.setString("subLibraryId", sub_library_id);
            query.executeUpdate();
            tx.commit();
            //return (BibliographicDetails) query.uniqueResult();
        } finally {
            //  session.close();
        }
    }


           public AcqApprovalHeader search1Approvalno(String approval_no, String library_id, String sub_library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(AcqApprovalHeader.class).add(Restrictions.conjunction().add(Restrictions.eq("id.libraryId", library_id)).add(Restrictions.eq("id.subLibraryId", sub_library_id)).add(Restrictions.eq("id.approvalNo", approval_no)));
        //session.close();
        return (AcqApprovalHeader) criteria.uniqueResult();
    }

     public static boolean deleteApprovalHeader(String library_id, String sub_library_id,String approval_no) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        //Object acqdocentry = null;

        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("DELETE FROM AcqApprovalHeader  WHERE  id.libraryId = :libraryId and id.subLibraryId = :subLibraryId and id.approvalNo=:approvalNo");

            query.setString("approvalNo", approval_no);
            query.setString("libraryId", library_id);
            query.setString("subLibraryId", sub_library_id);
            query.executeUpdate();
            tx.commit();
            return true;

        }
        catch(Exception e){
            tx.rollback();
            return false;

        }finally {
              session.close();
        }
    }
     public static boolean deleteApproval(String library_id, String sub_library_id,String approval_no) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        //Object acqdocentry = null;

        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("DELETE FROM AcqApproval  WHERE  id.libraryId = :libraryId and id.subLibraryId = :subLibraryId and approvalNo=:approvalNo");

            query.setString("approvalNo", approval_no);
            query.setString("libraryId", library_id);
            query.setString("subLibraryId", sub_library_id);
            query.executeUpdate();
            tx.commit();
            return true;

        }
        catch(Exception e){
            tx.rollback();
            return false;

        }finally {
              session.close();
        }
    }

     public static AcqOrderHeader searchOrderHeaderByOrderNo(String library_id,String sublibrary_id,String order_no) {
         Session session = HibernateUtil.getSessionFactory().openSession();
         Transaction tx=session.beginTransaction();
        try {
            Criteria criteria = session.createCriteria(AcqOrderHeader.class)
         // .setProjection(Projections.groupProperty("vendor"))
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.subLibraryId", sublibrary_id))
                    .add(Restrictions.eq("id.orderNo", order_no))

                    );

//criteria.setProjection(Property.forName("vendor").group().as("vendor"));
            return (AcqOrderHeader)criteria.uniqueResult();
        } finally {
          session.close();
        }

    }

  public static ArrayList<AcqOrder1> searchOrderByOrderNo(String library_id,String sublibrary_id,String order_no) {
         Session session = HibernateUtil.getSessionFactory().openSession();
         Transaction tx=session.beginTransaction();
        try {
            Criteria criteria = session.createCriteria(AcqOrder1.class)
          //.setProjection(Projections.groupProperty("vendor"))
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.subLibraryId", sublibrary_id))
                    .add(Restrictions.eq("id.orderNo", order_no))

                    );

           return (ArrayList<AcqOrder1>)criteria.list();
        } finally {
          session.close();
        }




    }
  public static List<ApprovalList> getViewApprovalList(String library_id, String sub_library_id,String orderno){
  Session session =null;
    Transaction tx = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            String sql="";

            sql="(select   b.control_no,a.no_of_copies,ca.isbn,ca.title,ca.author,b.unit_price,b.acq_mode,(select ac.conversion_rate   from acq_currency ac where ac.library_id=v.library_id and ac.source_currency=v.vendor_currency) conversion_rate "
+" from acq_order_header e,acq_order1 d,acq_approval a,acq_bibliography_details b,acq_bibliography ca,acq_vendor v "
+" where e.library_id=d.library_id and e.sub_library_id=d.sub_library_id and e.order_no=d.order_no "
+ " and d.library_id=a.library_id and d.sub_library_id=a.sub_library_id and d.approval_item_id=a.approval_item_id "
+"and a.library_id=b.library_id and a.sub_library_id=b.sub_library_id and a.control_no=b.control_no and b.library_id=ca.library_id "
+" and b.sub_library_id=ca.sub_library_id and b.title_id=ca.title_id and b.library_id=v.library_id and b.vendor=v.vendor_id and d.approval_item_id>0 and e.library_id='"+library_id+"' and e.sub_library_id='"+sub_library_id+"' and e.order_no='"+orderno+"')"

            
+ " union all (select  b.control_no,b.no_of_copies,ca.isbn,ca.title,ca.author,b.unit_price,b.acq_mode,g.conversion_rate from acq_bibliography_details b, acq_bibliography ca,acq_order1 d,acq_order_header e,acq_vendor f,acq_currency g   where  b.library_id=ca.library_id and b.sub_library_id=ca.sub_library_id and b.title_id=ca.title_id and ca.library_id=d.library_id and ca.sub_library_id=d.sub_library_id and b.control_no=d.control_no and d.library_id=e.library_id and d.sub_library_id=e.sub_library_id and d.order_no=e.order_no and e.library_id=f.library_id and e.sub_library_id=f.sub_library_id and e.vendor_id=f.vendor_id and f.library_id=g.library_id and f.vendor_currency=g.source_currency and b.library_id='"+library_id+"' and b.sub_library_id='"+sub_library_id+"' and b.acq_mode='Firm Order' and d.order_no='"+orderno+"')";


          Query query =  session.createSQLQuery(sql)
                    .setResultTransformer(Transformers.aliasToBean(ApprovalList.class));
            return (List<ApprovalList>)query.list();
        }
        finally {
            session.close();
        }
}

 public static List<ApprovalList> getViewApprovalList1(String library_id, String sub_library_id,String orderno){
  Session session =null;
    Transaction tx = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            String sql="";



            sql="(select  b.control_no,a.no_of_copies,ca.isbn,ca.title,b.unit_price,a.approval_no,ca.author,b.acq_mode"+
" from acq_approval a,acq_bibliography_details b, acq_bibliography ca,acq_order1 d,acq_order_header e,acq_vendor f"+
" where a.library_id=b.library_id and a.sub_library_id=b.sub_library_id and a.control_no=b.control_no and "+
 "b.library_id=ca.library_id and b.sub_library_id=ca.sub_library_id and b.title_id=ca.title_id and ca.library_id=d.library_id and "+
" ca.sub_library_id=d.sub_library_id and d.approval_item_id=a.approval_item_id and d.library_id=e.library_id and "+
" d.sub_library_id=e.sub_library_id and d.order_no=e.order_no and "+
" e.library_id=f.library_id and e.sub_library_id=f.sub_library_id and e.vendor_id=f.vendor_id and "+

 " b.library_id='"+library_id+"' and b.sub_library_id='"+sub_library_id+"' and e.order_no='"+orderno+"' and d.approval_item_id>0)"+


 " union all (select  b.control_no,b.no_of_copies,ca.isbn,ca.title,b.unit_price,b.no_of_copies,b.author,b.acq_mode from acq_bibliography_details b, acq_bibliography ca,acq_order1 d,acq_order_header e,acq_vendor f   where  b.library_id=ca.library_id and b.sub_library_id=ca.sub_library_id and b.title_id=ca.title_id and ca.library_id=d.library_id and ca.sub_library_id=d.sub_library_id and b.control_no=d.control_no and d.library_id=e.library_id and d.sub_library_id=e.sub_library_id and d.order_no=e.order_no and e.library_id=f.library_id and e.sub_library_id=f.sub_library_id and e.vendor_id=f.vendor_id and b.library_id='"+library_id+"' and b.sub_library_id='"+sub_library_id+"' and b.acq_mode='Firm Order' and d.order_no='"+orderno+"')";


          Query query =  session.createSQLQuery(sql)
                    .setResultTransformer(Transformers.aliasToBean(ApprovalList.class));
            return (List<ApprovalList>)query.list();
        }
        finally {
            session.close();
        }
}

}
