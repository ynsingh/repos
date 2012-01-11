/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.AcquisitionDao;
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
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Property;
import org.hibernate.transform.Transformers;
/**
 *
 * @author maqbool
 */
public class AcquisitionDao {

     public AcqRecievingHeader getRecieveOrder( String library_id, String sub_library_id,String recieving_no,String order_no,String vendor_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        AcqRecievingHeader obj=null;
        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(AcqRecievingHeader.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.subLibraryId", sub_library_id))
                    .add(Restrictions.eq("orderNo", order_no))
                    .add(Restrictions.eq("vendorId", vendor_id))
                    .add(Restrictions.eq("id.recievingNo", recieving_no)));
            obj= (AcqRecievingHeader) criteria.uniqueResult();
        }
        catch(HibernateException e){
        e.printStackTrace();
        }
        finally {
            session.close();
        }
        return obj;
    }
  public  List<AcqOrder1> BibliobyControlId2(String library_id,String sub_library_id,int con_no,String order_no) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
 List<AcqOrder1> obj=null;
        try {
            session.beginTransaction();

            Criteria criteria = session.createCriteria(AcqOrder1.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.subLibraryId", sub_library_id))
                    .add(Restrictions.eq("id.orderNo", order_no))
                    .add(Restrictions.eq("controlNo",con_no )));
                  obj= criteria.list();
        }
        catch(Exception e){
        e.printStackTrace();
        }
        finally {
            session.close();
        }
        return obj;
  }


   public  List<AcqBudgetTransaction> BudgetHeadId(String library_id,String budget_head_id,int con_no) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
List<AcqBudgetTransaction> obj=null;
        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(AcqBudgetTransaction.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("acqBudgetHeadId", budget_head_id))
                    .add(Restrictions.eq("controlNo",con_no )));
                   obj=  (List<AcqBudgetTransaction>) criteria.uniqueResult();
        } finally {
            session.close();
        }
        return obj;
  }


  public List<AcqOrder1> getOrderno1( String library_id, String sub_library_id,String order_no,int con_no ) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<AcqOrder1> obj=null;
        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(AcqOrder1.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.subLibraryId", sub_library_id))
                    .add(Restrictions.eq("controlNo", con_no))

                    .add(Restrictions.eq("id.orderNo", order_no)));
           obj= (List<AcqOrder1>) criteria.list();
        }
        catch(Exception e){
        e.printStackTrace();
        }
        finally {
            session.close();
        }
        return obj;
    }

    public AcqBibliographyDetails BibliobyControlIdonApproval(String library_id,String sub_library_id,int con_no) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
AcqBibliographyDetails obj=null;
        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(AcqBibliographyDetails.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.subLibraryId", sub_library_id))
                    .add(Restrictions.eq("id.controlNo",con_no )));

          obj= (AcqBibliographyDetails) criteria.uniqueResult();
        }
         catch(Exception e){
        e.printStackTrace();
        }
        finally
        {
        session.close();
        }
        return obj;
    }
    
public AcqApproval BibliobyControlId3(String library_id,String sub_library_id,int con_no,int x) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
 AcqApproval obj=null;
        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(AcqApproval.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.subLibraryId", sub_library_id))
                    .add(Restrictions.eq("controlNo",con_no ))
                    .add(Restrictions.eq("id.approvalItemId",x )));
           obj= (AcqApproval) criteria.uniqueResult();
        }  catch(Exception e){
        e.printStackTrace();
        }
        finally
        {
        session.close();
        }
        return obj;
    }


public AcqBudgetAllocation BudgetByControlId(String library_id,int con_no,double x) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
 AcqBudgetAllocation obj=null;
        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(AcqBudgetAllocation.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("controlNo",con_no ))
                    .add(Restrictions.eq("amount",x )));
           obj= (AcqBudgetAllocation) criteria.uniqueResult();
        }  catch(Exception e){
        e.printStackTrace();
        }
        finally
        {
        session.close();
        }
        return obj;
    }


            public Integer returnMaxRecievingItemId(String library_id, String sublibrary_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Integer maxbiblio=null;
        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(AcqRecievingDetails.class);
            Criterion a = Restrictions.eq("id.libraryId", library_id);
            Criterion b = Restrictions.eq("id.subLibraryId", sublibrary_id);
            LogicalExpression le = Restrictions.and(a, b);
             maxbiblio = (Integer) criteria.add(le).setProjection(Projections.max("id.recievingItemId")).uniqueResult();
            if (maxbiblio == null) {
                maxbiblio = 1;
            } else {
                maxbiblio++;
            }

            
        }  
         catch(Exception e)
        {
        e.printStackTrace();
        }
        finally
        {
        session.close();
        }
       return maxbiblio;
    }
                public void insert5(AcqRecievingDetails bibDetails) {
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

    public void insert7(AcqRecievingHeader bibDetails) {
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


    public List searchDoc5(String library_id, String sub_library_id) {
      List obj=null;

        Session session=HibernateUtil.getSessionFactory().openSession();
        try
        {
         
          
          session.beginTransaction();
          String sql="";

              sql = "(select a.isbn,b.acq_mode,ca.approval_no,d.vendor_id,ca.approval_item_id,b.control_no,a.title,a.author,ca.no_of_copies from acq_bibliography a,acq_bibliography_details b,acq_approval ca,acq_approval_header d where a.title_id=b.title_id and b.acq_mode='Approved' and b.control_no=ca.control_no and b.library_id=ca.library_id and b.sub_library_id=ca.sub_library_id and ca.approval_no=d.approval_no and ca.library_id=d.library_id and ca.sub_library_id=d.sub_library_id and ca.status='pending' and a.Library_id='"+library_id+"' and a.sub_library_id='"+sub_library_id+"')";




          Query query =  session.createSQLQuery(sql)
                    .setResultTransformer(Transformers.aliasToBean(approval_1.class));

          
            return (List<approval_1>)query.list();

            

        }
        catch(Exception e){
        e.printStackTrace();
        }
        finally
        {
        session.close();
        }
        return obj;
    }

    public static List  CheckInReport2(String library_id,String sub_lib)
    {
       
    Session session=null;
    List obj=null;
        
        try
        {
         
          session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            String sql="";

          
             sql = "(select b.control_no,a.title,a.author,b.no_of_copies,b.unit_price,b.currency,ac.budgethead_name from acq_bibliography  a,acq_bibliography_details b ,acq_budget ac  where a.library_id=b.library_id and a.sub_library_id=b.sub_library_id and a.title_id=b.title_id and b.library_id=ac.library_id and b.acq_mode='On Approval')";



          Query query =  session.createSQLQuery(sql)
                    .setResultTransformer(Transformers.aliasToBean(CirculationList_1_1.class));

        
           obj= (List<CirculationList_1_1>)query.list();

        }
        catch(Exception e)
        {
        e.printStackTrace();
        }
        finally
        {
        session.close();
        }
        return obj;
    }
 public static List  approvalReport(String library_id,String sub_lib)
    {
      
    Session session=null;
  List obj=null;
        
        try
        {
        
          session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            String sql="";

             
            sql = "(select b.library_id,b.vendor,b.control_no,a.title,a.author,b.no_of_copies,b.unit_price,b.currency,ac.budgethead_name from acq_bibliography  a,acq_bibliography_details b ,acq_budget ac  where a.library_id=b.library_id and a.sub_library_id=b.sub_library_id and a.title_id=b.title_id and b.library_id=ac.library_id and b.acq_mode='Firm Order' and b.status is null)";



          Query query =  session.createSQLQuery(sql)
                    .setResultTransformer(Transformers.aliasToBean(CirculationList_1_1.class));

          System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            obj= (List<CirculationList_1_1>)query.list();
        }
        catch(Exception e){
        e.printStackTrace();
        }
        finally
        {
        session.close();
        }
        return obj;
    }

    public void insert(AcqBibliography bibDetails) {
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
      public void insert1(AcqBibliographyDetails bibDetails) {
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
            public void insert2(AcqApprovalHeader bibDetails) {
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
public void updateApprovalHeader(AcqApprovalHeader bibDetails) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(bibDetails);
            tx.commit();
        } catch (Exception e)
        {
           
            tx.rollback();
           e.printStackTrace();
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
        } catch (Exception e) {
          
            tx.rollback();
            e.printStackTrace();
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
        } catch (Exception e) {
           
            tx.rollback();
           e.printStackTrace();
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
        } catch (Exception e) {
          
            tx.rollback();
          e.printStackTrace();
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
        ex.printStackTrace();
        



        }
        finally
        {
          session.close();
        }
 

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
        ex.printStackTrace();


        }
        finally
        {
          session.close();
        }
 

    }


   public static boolean updateApproval(AcqApproval app) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.update(app);
            tx.commit();
        } catch (Exception e) {
          
            tx.rollback();
          e.printStackTrace();
          
        } finally {
            session.close();
        }
        return true;
    }



    public static boolean updateBudget(AcqBudgetAllocation app) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.update(app);
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

        public void update(AcqBibliographyDetails bibDetails) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.update(bibDetails);
            tx.commit();
        } catch (Exception e) {
            
            tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
            public Integer returnMaxBiblioId(String library_id, String sublibrary_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
       Integer maxbiblio=null;
        try {
              session.beginTransaction();
            Criteria criteria = session.createCriteria(AcqBibliography.class);
            Criterion a = Restrictions.eq("id.libraryId", library_id);
            Criterion b = Restrictions.eq("id.subLibraryId", sublibrary_id);
            LogicalExpression le = Restrictions.and(a, b);
            maxbiblio = (Integer) criteria.add(le).setProjection(Projections.max("id.titleId")).uniqueResult();
            if (maxbiblio == null) {
                maxbiblio = 1;
            } else {
                maxbiblio++;
            }

           
        } catch(Exception e){
        e.printStackTrace();
        }
        finally
        {
        session.close();
        }
        return maxbiblio;
    }
        public Integer returnMaxItemId(String library_id, String sublibrary_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Integer maxbiblio=null;
        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(AcqApproval.class);
            Criterion a = Restrictions.eq("id.libraryId", library_id);
            Criterion b = Restrictions.eq("id.subLibraryId", sublibrary_id);
            LogicalExpression le = Restrictions.and(a, b);
             maxbiblio = (Integer) criteria.add(le).setProjection(Projections.max("id.approvalItemId")).uniqueResult();
            if (maxbiblio == null) {
                maxbiblio = 1;
            } else {
                maxbiblio++;
            }

            
        }  catch(Exception e){
        e.printStackTrace();
        }
        finally
        {
        session.close();
        }
      return maxbiblio;
    }
        public Integer returnMaxControlNo(String library_id, String sublibrary_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
           Integer maxbiblio=null;
        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(AcqBibliographyDetails.class);
            Criterion a = Restrictions.eq("id.libraryId", library_id);
            Criterion b = Restrictions.eq("id.subLibraryId", sublibrary_id);
            LogicalExpression le = Restrictions.and(a, b);
             maxbiblio = (Integer) criteria.add(le).setProjection(Projections.max("id.controlNo")).uniqueResult();
            if (maxbiblio == null) {
                maxbiblio = 1;
            } else {
                maxbiblio++;
            }

          
        } catch(Exception e){
        e.printStackTrace();
        }
        finally
        {
        session.close();
        }
       return maxbiblio;
    }
         public Integer returnMaxTransNo(String library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Integer maxbiblio =null;
        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(AcqBudgetTransaction.class);
            Criterion a = Restrictions.eq("id.libraryId", library_id);


             maxbiblio = (Integer) criteria.add(a).setProjection(Projections.max("id.transactionId")).uniqueResult();
            if (maxbiblio == null) {
                maxbiblio = 1;
            } else {
                maxbiblio++;
            }

          
        }  catch(Exception e){
        e.printStackTrace();
        }
        finally
        {
        session.close();
        }
       return maxbiblio;
    }
           public static AcqApproval searchApproval(String library_id,String sublibrary_id,String approval_no,int control_no) {
        Session session = HibernateUtil.getSessionFactory().openSession();
         AcqApproval obj=null;

        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(AcqApproval.class)

                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.subLibraryId", sublibrary_id))
                    .add(Restrictions.eq("controlNo", control_no))
                    .add(Restrictions.eq("approvalNo", approval_no));

            obj=(AcqApproval) criteria.uniqueResult();
        } catch(Exception e){
        e.printStackTrace();
        }
        finally
        {
        session.close();
        }
        return obj;
    }
               public static AcqVendor searchVendor(String library_id,String vendor_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
         AcqVendor obj=null;
        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(AcqVendor.class)

                    .add(Restrictions.eq("id.libraryId", library_id))


                    .add(Restrictions.eq("id.vendorId", vendor_id));

            obj= (AcqVendor) criteria.uniqueResult();
        } catch(Exception e){
        e.printStackTrace();
        }
        finally
        {
        session.close();
        }
        return obj;
    }
 public static AcqCurrency searchVendorCurrency(String library_id,String SourceCurrency)
{
        Session session = HibernateUtil.getSessionFactory().openSession();
        AcqCurrency obj=null;
        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(AcqCurrency.class)

                    .add(Restrictions.eq("id.libraryId", library_id))


                    .add(Restrictions.eq("sourceCurrency", SourceCurrency));

            obj= (AcqCurrency) criteria.uniqueResult();
        }  catch(Exception e){
        e.printStackTrace();
        }
        finally
        {
        session.close();
        }
        return obj;
    }

             public static List<AcqApproval> searchApproval2(String library_id,String sublibrary_id,String approval_no) {
        Session session = HibernateUtil.getSessionFactory().openSession();
         List<AcqApproval> obj=null;
        try {
             session.beginTransaction();
            Criteria criteria = session.createCriteria(AcqApproval.class)

                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.subLibraryId", sublibrary_id))
                    .add(Restrictions.eq("approvalNo", approval_no));

            obj=(List<AcqApproval>) criteria.list();
        }  catch(Exception e){
        e.printStackTrace();
        }
        finally
        {
        session.close();
        }
        return obj;
    }
             public static List<AcqApproval> searchApprovalStatus(String library_id,String sublibrary_id,int control_no) {
        Session session = HibernateUtil.getSessionFactory().openSession();
         List<AcqApproval> obj=null;
        try {
             session.beginTransaction();
            Criteria criteria = session.createCriteria(AcqApproval.class)

                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.subLibraryId", sublibrary_id))
                    .add(Restrictions.eq("controlNo", control_no))
                    .add(Restrictions.ne("status", "Ordered"));
                    

           obj= (List<AcqApproval>) criteria.list();
        } catch(Exception e){
        e.printStackTrace();
        }
        finally
        {
        session.close();
        }
        return obj;
    }

             public static AcqApproval searchApprovalStatus1(String library_id,String sublibrary_id,int control_no,int approval_item_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        AcqApproval obj=null;
        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(AcqApproval.class)

                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.subLibraryId", sublibrary_id))
                    .add(Restrictions.eq("controlNo", control_no))
                    .add(Restrictions.eq("id.approvalItemId", approval_item_id))
                    .add(Restrictions.eq("status", "Ordered"));


          obj=(AcqApproval) criteria.uniqueResult();
        } catch(Exception e){
        e.printStackTrace();
        }
        finally
        {
        session.close();
        }
        return obj;
    }

               public static AcqApproval searchAcqApproval(String library_id,String sublibrary_id,int approval_id)
               {        Session session = HibernateUtil.getSessionFactory().openSession();
       AcqApproval obj=null;

        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(AcqApproval.class)

                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.subLibraryId", sublibrary_id))
                    .add(Restrictions.eq("id.approvalItemId", approval_id)
                    );


            return (AcqApproval) criteria.uniqueResult();
        }  catch(Exception e){
        e.printStackTrace();
        }
        finally
        {
        session.close();
        }
        return obj;
    }


     public static List<AcqOrder1> searchAcqOrder(String library_id,String sublibrary_id,int control_no,String order_no) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<AcqOrder1>  obj=null;
        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(AcqOrder1.class)

                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.subLibraryId", sublibrary_id))
                    .add(Restrictions.eq("id.orderNo", order_no))
                    .add(Restrictions.eq("controlNo", control_no)
                    );
                    

            return (List<AcqOrder1>) criteria.list();
        }  catch(Exception e){
        e.printStackTrace();
        }
        finally
        {
        session.close();
        }
        return obj;
    }

    public List getBiblio(String library_id,String sublibrary_id,String search_by, String search_keyword, String sort_by) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List obj=null;

        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(AcqBibliography.class)
                    
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.subLibraryId", sublibrary_id))
                    .add(Restrictions.ilike(search_by,search_keyword+"%"))
                    .addOrder(Property.forName(sort_by).asc());
            return (List) criteria.list();
        }
         catch(Exception e){
        e.printStackTrace();
        }
        finally
        {
        session.close();
        }
        return obj;
    }
  public static AcqBibliographyDetails searchAcqBibByControlNo(String library_id,String sublibrary_id,int control_no) {
         Session session = HibernateUtil.getSessionFactory().openSession();
         AcqBibliographyDetails obj=null;
        try {
             session.beginTransaction();
            Criteria criteria = session.createCriteria(AcqBibliographyDetails.class)
         
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.subLibraryId", sublibrary_id))
                    .add(Restrictions.eq("id.controlNo", control_no))

                    );


            obj=(AcqBibliographyDetails)criteria.uniqueResult();
        }
        catch(Exception e){
        e.printStackTrace();
        }
        finally
        {
        session.close();
        }
        return obj;
    }

   public static AcqBibliographyDetails searchAcqBibPartialByControlNo(String library_id,String sublibrary_id,int control_no) {
         Session session = HibernateUtil.getSessionFactory().openSession();
        AcqBibliographyDetails obj=null;
        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(AcqBibliographyDetails.class)
         
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.subLibraryId", sublibrary_id))
                    .add(Restrictions.eq("id.controlNo", control_no))
                    .add(Restrictions.eq("status", "Partially Approved"))

                    );


           obj=(AcqBibliographyDetails)criteria.uniqueResult();
        }
         catch(Exception e){
        e.printStackTrace();
        }
        finally
        {
        session.close();
        }
        return obj;
    }




     public static AcqRecievingHeader searchOrderHeaderByRecievingNo(String library_id,String sublibrary_id,String recieving_no) {
         Session session = HibernateUtil.getSessionFactory().openSession();
       AcqRecievingHeader obj=null;
        try {
            Criteria criteria = session.createCriteria(AcqRecievingHeader.class)
        
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.subLibraryId", sublibrary_id))
                    .add(Restrictions.eq("id.recievingNo", recieving_no))

                    );


            obj= (AcqRecievingHeader)criteria.uniqueResult();
        }
         catch(Exception e){
        e.printStackTrace();
        }
        finally
        {
        session.close();
        }
        return obj;
    }


      public ArrayList<ApprovalList> getBibByControlNo(String library_id,String sublibrary_id,String approval_no) {

          Session session =null;
   ArrayList<ApprovalList> obj=null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            String sql="";

            sql = "select  b.control_no,a.no_of_copies,ca.author,ca.isbn,ca.title,b.unit_price,a.approval_no from acq_approval a,acq_bibliography_details b, acq_bibliography ca where a.library_id=b.library_id and a.sub_library_id=b.sub_library_id and a.control_no=b.control_no and b.library_id=ca.library_id and b.sub_library_id=ca.sub_library_id and b.title_id=ca.title_id and b.library_id='"+library_id+"' and b.sub_library_id='"+sublibrary_id+"' and a.approval_no='"+approval_no+"'";



          Query query =  session.createSQLQuery(sql)
                    .setResultTransformer(Transformers.aliasToBean(ApprovalList.class));
           obj= (ArrayList<ApprovalList>) query.list();
        }
         catch(Exception e){
        e.printStackTrace();
        }
        finally
        {
        session.close();
        }
        return obj;
    }

    public static AcqApprovalHeader searchApprovalHeaderByOrderNo(String library_id,String sublibrary_id,String order_no) {
         Session session = HibernateUtil.getSessionFactory().openSession();
         AcqApprovalHeader obj=null;
        try {
             session.beginTransaction();
            Criteria criteria = session.createCriteria(AcqApprovalHeader.class)
         
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.subLibraryId", sublibrary_id))
                    .add(Restrictions.eq("id.orderNo", order_no))

                    );


            obj= (AcqApprovalHeader)criteria.uniqueResult();
        }
         catch(Exception e){
        e.printStackTrace();
        }
        finally
        {
        session.close();
        }
        return obj;
    }
  

    public AcqBibliography BibliobyTitleId(int title_id, String library_id, String sub_library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        AcqBibliography obj=null;
        try {
             session.beginTransaction();
            Criteria criteria = session.createCriteria(AcqBibliography.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.subLibraryId", sub_library_id))
                    .add(Restrictions.eq("id.titleId", title_id)));
           obj=(AcqBibliography) criteria.uniqueResult();
        }
         catch(Exception e){
        e.printStackTrace();
        }
        finally
        {
        session.close();
        }
        return obj;
    }


    public AcqBibliographyDetails BibliobyControlId(int control_no, String library_id, String sub_library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        AcqBibliographyDetails obj=null;
        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(AcqBibliographyDetails.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.subLibraryId", sub_library_id))
                    .add(Restrictions.eq("id.controlNo", control_no)));
           obj= (AcqBibliographyDetails) criteria.uniqueResult();
        }
         catch(Exception e){
        e.printStackTrace();
        }
        finally
        {
        session.close();
        }
        return obj;
    }
      public AcqApprovalHeader getApproval( String library_id, String sub_library_id,String approval) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        AcqApprovalHeader obj=null;
        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(AcqApprovalHeader.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.subLibraryId", sub_library_id))
                    .add(Restrictions.eq("id.approvalNo", approval)));
            obj= (AcqApprovalHeader) criteria.uniqueResult();
        }
         catch(Exception e){
        e.printStackTrace();
        }
        finally
        {
        session.close();
        }
        return obj;
    }

       public List<AcqApproval> getApproval1( String library_id, String sub_library_id,String approval) {
        Session session = HibernateUtil.getSessionFactory().openSession();
       List<AcqApproval> obj=null;
        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(AcqApproval.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.subLibraryId", sub_library_id))
                    .add(Restrictions.eq("approvalNo", approval)));
           obj= (List<AcqApproval>) criteria.list();
        }  catch(Exception e){
        e.printStackTrace();
        }
        finally
        {
        session.close();
        }
        return obj;
    }

        public List<AcqBibliographyDetails> getPendingApproval( String library_id, String sub_library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<AcqBibliographyDetails> obj=null;
        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(AcqBibliographyDetails.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.subLibraryId", sub_library_id))
                    .add(Restrictions.eq("acqMode","On Approval")));
            obj= (List<AcqBibliographyDetails>) criteria.list();
        }
        catch(Exception e){
        e.printStackTrace();
        }
        finally
        {
        session.close();
        }
        return obj;
    }

    public List<AcqBibliographyDetails> searchDoc1(String library_id, String sub_library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<AcqBibliographyDetails> obj=null;
        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(AcqBibliographyDetails.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.subLibraryId", sub_library_id))
                    .add(Restrictions.eq("acqMode", "On Approval")));
            obj= criteria.list();
        } catch(Exception e){
        e.printStackTrace();
        }
        finally
        {
        session.close();
        }
        return obj;
    }

 public List<ApprovalList> searchOnApprovalList(String library_id, String sub_library_id) {
     Session session =null;
   List<ApprovalList> obj = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            String sql="";

            sql = "select  b.control_no,b.no_of_copies,a.author,a.isbn,a.title,b.unit_price,b.acq_mode from acq_bibliography a,acq_bibliography_details b where a.library_id=b.library_id and a.sub_library_id=b.sub_library_id and a.title_id=b.title_id and b.acq_mode='On Approval'  and  b.library_id='"+library_id+"' and b.sub_library_id='"+sub_library_id+"'";



          Query query =  session.createSQLQuery(sql)
                    .setResultTransformer(Transformers.aliasToBean(ApprovalList.class));
            obj= (List<ApprovalList>)query.list();
        }
         catch(Exception e){
        e.printStackTrace();
        }
        finally
        {
        session.close();
        }
        return obj;
    }

     public List<AcqBibliographyDetails> searchDoc3(String library_id, String sub_library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
       List<AcqBibliographyDetails> obj=null;
        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(AcqBibliographyDetails.class)
         
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.subLibraryId", sub_library_id))
                    .add(Restrictions.eq("acqMode", "Firm Order"))
                 
                    );
            

            obj= criteria.list();
                  return obj;
        }  catch(Exception e){
        e.printStackTrace();
        }
        finally
        {
        session.close();
        }
  return null;
    }



     public List<AcqBibliographyDetails> searchDoc4(String library_id, String sub_library_id) {
        Session session =null;
   List<AcqBibliographyDetails> obj = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            
            Query query = session.createQuery("FROM AcqBibliographyDetails where  id.libraryId = :l and id.subLibraryId = :subLibraryId and acqMode = :acqMode group by vendor ");
           query.setString("l",library_id);
           query.setString("subLibraryId",sub_library_id);
           query.setString("acqMode","Firm Order");
            obj= query.list();
        }
         catch(Exception e){
        e.printStackTrace();
        }
        finally
        {
        session.close();
        }
        return obj;
    }
    public List getBiblioDetails(String library_id,String sublibrary_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List obj=null;
        try {
             session.beginTransaction();
            Criteria criteria = session.createCriteria(AcqBibliographyDetails.class)
                   
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.subLibraryId", sublibrary_id));
                   
            obj= (List) criteria.list();
        }  catch(Exception e){
        e.printStackTrace();
        }
        finally
        {
        session.close();
        }
        return obj;
    }

     public List getBiblioVendor(String library_id,String sublibrary_id,String vendor) {
        Session session = HibernateUtil.getSessionFactory().openSession();
         List obj=null;
        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(AcqBibliographyDetails.class)
                    
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.subLibraryId", sublibrary_id))
                    .add(Restrictions.eq("vendor", vendor));
                   
            obj= (List) criteria.list();
        }  catch(Exception e){
        e.printStackTrace();
        }
        finally
        {
        session.close();
        }
        return obj;
    }

     public List getAcqBiblio2(String library_id, String sub_library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
         List obj=null;
        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(AcqBibliographyDetails.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.subLibraryId", sub_library_id)));
            obj= criteria.list();
        }  catch(Exception e){
        e.printStackTrace();
        }
        finally
        {
        session.close();
        }
        return obj;
    }

     public AcqBibliographyDetails BibliobyControlId(String library_id,String sub_library_id,int control_no) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
 AcqBibliographyDetails obj=null;
        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(AcqBibliographyDetails.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.subLibraryId", sub_library_id))
                    .add(Restrictions.eq("id.controlNo",control_no )));
            obj=(AcqBibliographyDetails) criteria.uniqueResult();
        } catch(Exception e){
        e.printStackTrace();
        }
        finally
        {
        session.close();
        }
        return obj;
    }

   public List searchDoc2(String library_id, String sub_library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List obj=null;
        try {
             session.beginTransaction();
            Criteria criteria = session.createCriteria(AcqBibliographyDetails.class)
                    .add(Restrictions.conjunction().add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.subLibraryId", sub_library_id))
                      .add(Restrictions.ne("status", "Ordered"))
                      .add(Restrictions.ne("status", "Ordered/PartiallyRecieved"))
                    .add(Restrictions.eq("acqMode", "Firm Order")));
            return criteria.list();
        }  catch(Exception e){
        e.printStackTrace();
        }
        finally
        {
        session.close();
        }
        return obj;
    }

public List<ApprovalList> getApprovalList(String library_id, String sub_library_id,String vendor){
  Session session =null;
  List<ApprovalList> obj=null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            String sql="";

            sql = "(select  b.control_no,ca.author,a.no_of_copies,ca.isbn,ca.title,b.unit_price,a.approval_no,b.status from acq_approval a,acq_approval_header d,acq_bibliography_details b, acq_bibliography ca where a.library_id=d.library_id and a.sub_library_id=d.sub_library_id and a.approval_no=d.approval_no and a.library_id=b.library_id and a.sub_library_id=b.sub_library_id and a.control_no=b.control_no and b.library_id=ca.library_id and b.sub_library_id=ca.sub_library_id and b.title_id=ca.title_id and (b.acq_mode='On Approval' and b.status='Partially Approved')   and b.library_id='"+library_id+"' and b.sub_library_id='"+sub_library_id+"' and d.vendor_id='"+vendor+"' and a.status='pending')"
+" union all (select  b.control_no,ca.author,a.no_of_copies,ca.isbn,ca.title,b.unit_price,a.approval_no,b.status from acq_approval a,acq_approval_header d,acq_bibliography_details b, acq_bibliography ca where a.library_id=d.library_id and a.sub_library_id=d.sub_library_id and a.approval_no=d.approval_no  and a.library_id=b.library_id and a.sub_library_id=b.sub_library_id and a.control_no=b.control_no and b.library_id=ca.library_id and b.sub_library_id=ca.sub_library_id and b.title_id=ca.title_id and (b.acq_mode='Approved' and b.status='Fully Approved')   and b.library_id='"+library_id+"' and b.sub_library_id='"+sub_library_id+"' and d.vendor_id='"+vendor+"' and a.status='pending')"
 +" union All (select b.control_no,ca.author,b.no_of_copies,ca.isbn,ca.title,b.unit_price,b.volume,b.acq_mode from acq_bibliography_details b,acq_bibliography ca where  b.library_id=ca.library_id and b.sub_library_id=ca.sub_library_id and b.title_id=ca.title_id and b.library_id='"+library_id+"' and b.sub_library_id='"+sub_library_id+"' and b.acq_mode='Firm Order' and b.vendor='"+vendor+"' and b.status is null)";




          Query query =  session.createSQLQuery(sql)
                    .setResultTransformer(Transformers.aliasToBean(ApprovalList.class));
           obj=(List<ApprovalList>)query.list();
        }
         catch(Exception e){
        e.printStackTrace();
        }
        finally
        {
        session.close();
        }
        return obj;
    }

public ApprovalList getApprovalListbySelectionF(String library_id, String sub_library_id,int control_no){
  Session session =null;
   ApprovalList obj=null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            String sql="";

           

            sql = "select b.control_no,b.no_of_copies,ca.author,ca.isbn,ca.title,b.unit_price,b.volume from acq_bibliography_details b,acq_bibliography ca where b.library_id=ca.library_id and b.sub_library_id=ca.sub_library_id and b.title_id=ca.title_id and b.library_id='"+library_id+"' and b.sub_library_id='"+sub_library_id+"' and b.acq_mode='Firm Order' and b.control_no="+control_no;
           


          Query query =  session.createSQLQuery(sql)
                    .setResultTransformer(Transformers.aliasToBean(ApprovalList.class));
            obj= (ApprovalList)query.uniqueResult();
        }
         catch(Exception e){
        e.printStackTrace();
        }
        finally
        {
        session.close();
        }
        return obj;
    }
public ApprovalList getApprovalListbySelectionA(String library_id, String sub_library_id,String approval_no,int control_no){
  Session session =null;
    ApprovalList obj = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            String sql="";

          

                        sql="select  b.control_no,ca.author,a.no_of_copies,ca.isbn,ca.title,b.unit_price,a.approval_no from acq_approval a,acq_bibliography_details b, acq_bibliography ca where a.library_id=b.library_id and a.sub_library_id=b.sub_library_id and a.control_no=b.control_no and b.library_id=ca.library_id and b.sub_library_id=ca.sub_library_id and b.title_id=ca.title_id and b.library_id='"+library_id+"' and b.sub_library_id='"+sub_library_id+"' and a.approval_no='"+approval_no+"' and a.control_no='"+control_no+"'";
        


          Query query =  session.createSQLQuery(sql)
                    .setResultTransformer(Transformers.aliasToBean(ApprovalList.class));
            obj= (ApprovalList)query.uniqueResult();
        }
         catch(Exception e){
        e.printStackTrace();
        }
        finally
        {
        session.close();
        }
        return obj;
    }





   public List searchListByVendor(String library_id, String sub_library_id,String vendor) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List obj=null;
        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(AcqBibliographyDetails.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.subLibraryId", sub_library_id))
                    .add(Restrictions.eq("acqMode","Firm Order"))
                    .add(Restrictions.eq("vendor", vendor)));
         
      
              obj= criteria.list();
        }  catch(Exception e){
        e.printStackTrace();
        }
        finally
        {
        session.close();
        }
        return obj;
    }

 

    public List searchDoc1(String library_id, String sub_library_id,String title) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List obj=null;
        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(AcqBibliography.class)
                    .add(Restrictions.conjunction().add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.subLibraryId", sub_library_id))
                    .add(Restrictions.eq("title",title)));
            obj= criteria.list();
        } catch(Exception e){
        e.printStackTrace();
        }
        finally
        {
        session.close();
        }
        return obj;
    }
  

     public static List<String> searchDoc6(String library_id, String sub_library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
         List<String> obj=null;
        try {
            session.beginTransaction();
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
            obj= criteria.list();
        }  catch(Exception e){
        e.printStackTrace();
        }
        finally
        {
        session.close();
        }
        return obj;
    }





    public AcqBibliography getBiblio(String library_id, String sub_library_id, int title_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        AcqBibliography obj=null;

        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(AcqBibliography.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.subLibraryId", sub_library_id))
                    .add(Restrictions.eq("id.titleId", title_id)));
            obj= (AcqBibliography) criteria.uniqueResult();
        } catch(Exception e){
        e.printStackTrace();
        }
        finally
        {
        session.close();
        }
        return obj;
    }
       public AcqBibliographyDetails getBiblioBytitleid(String library_id, String sub_library_id, int title_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
       AcqBibliographyDetails obj=null;

        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(AcqBibliographyDetails.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.subLibraryId", sub_library_id))
                    .add(Restrictions.eq("titleId", title_id)));
            obj=(AcqBibliographyDetails) criteria.uniqueResult();
        }  catch(Exception e){
        e.printStackTrace();
        }
        finally
        {
        session.close();
        }
        return obj;
    }
        public AcqVendor getBiblio(String library_id, String sub_library_id, String vendor_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
       AcqVendor obj=null;

        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(AcqVendor.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.subLibraryId", sub_library_id))
                    .add(Restrictions.eq("id.vendorId", vendor_id)));
            obj=(AcqVendor) criteria.uniqueResult();
        }  catch(Exception e){
        e.printStackTrace();
        }
        finally
        {
        session.close();
        }
        return obj;
    }



   public List searchDoc3(String library_id, String sub_library_id,String title) {
        Session session = HibernateUtil.getSessionFactory().openSession();
         List obj=null;
        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(AcqBibliography.class)
                    .add(Restrictions.conjunction().add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.subLibraryId", sub_library_id))
                    .add(Restrictions.eq("title",title)));
            obj= criteria.list();
        }  catch(Exception e){
        e.printStackTrace();
        }
        finally
        {
        session.close();
        }
        return obj;
    }


     public List searchDoc4(String library_id, String sub_library_id,int control_no) {
        Session session = HibernateUtil.getSessionFactory().openSession();
         List obj=null;
        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(AcqBibliography.class)
                    .add(Restrictions.conjunction().add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.subLibraryId", sub_library_id))
                    .add(Restrictions.eq("id.controlNo",control_no)));
            return criteria.list();
        } catch(Exception e){
        e.printStackTrace();
        }
        finally
        {
        session.close();
        }
        return obj;
    }


      public AcqVendor searchDoc5(String library_id, String sub_library_id,String vendor_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
         AcqVendor obj=null;
        try {
             session.beginTransaction();
            Criteria criteria = session.createCriteria(AcqVendor.class)
                    .add(Restrictions.conjunction().add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.subLibraryId", sub_library_id))
                    .add(Restrictions.eq("id.vendorId",vendor_id)));
            obj=(AcqVendor)criteria.uniqueResult();
        }  catch(Exception e){
        e.printStackTrace();
        }
        finally
        {
        session.close();
        }
        return obj;
    }

      public void update1(AcqBibliography bibDetails) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.update(bibDetails);
            tx.commit();
        } catch (Exception e) {
            
            tx.rollback();
        e.printStackTrace();
        } 
        finally
        {
        session.close();
        }
        
    }
       public List getTitles(String title, String library_id, String sub_library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List obj=null;
        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(BibliographicDetails.class).add(Restrictions.conjunction().add(Restrictions.eq("id.libraryId", library_id)).add(Restrictions.eq("id.sublibraryId", sub_library_id)).add(Restrictions.eq("title", title)));
            obj= (List) criteria.list();
        } catch(Exception e){
        e.printStackTrace();
        }
        finally
        {
        session.close();
        }
        return obj;
    }

         public void delete1(int title, String library_id, String sub_library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        

        try {
            tx = session.beginTransaction();
        
            Query query = session.createQuery("DELETE FROM AcqBibliography  WHERE  id.titleId = :titleId and id.libraryId = :libraryId and id.subLibraryId = :subLibraryId");

            query.setInteger("titleId", title);
            query.setString("libraryId", library_id);
            query.setString("subLibraryId", sub_library_id);
            query.executeUpdate();
            tx.commit();
        
        }  catch(Exception e){
        e.printStackTrace();
        tx.rollback();
        }
        finally
        {
        session.close();
        }
        
    }


           public AcqApprovalHeader search1Approvalno(String approval_no, String library_id, String sub_library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        AcqApprovalHeader obj=null;
        try{
        session.beginTransaction();
        Criteria criteria = session.createCriteria(AcqApprovalHeader.class).add(Restrictions.conjunction().add(Restrictions.eq("id.libraryId", library_id)).add(Restrictions.eq("id.subLibraryId", sub_library_id)).add(Restrictions.eq("id.approvalNo", approval_no)));
        
        obj=(AcqApprovalHeader) criteria.uniqueResult();
        }
         catch(Exception e){
        e.printStackTrace();
        }
        finally
        {
        session.close();
        }
        return obj;
    }
    

     public static boolean deleteApprovalHeader(String library_id, String sub_library_id,String approval_no) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        

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
        e.printStackTrace();
        tx.rollback();
        return false;
        }
        finally
        {
        session.close();
        }
        
    }
     public static boolean deleteApproval(String library_id, String sub_library_id,String approval_no) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        

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
        e.printStackTrace();
        tx.rollback();
        return false;
        }
        finally
        {
        session.close();
        }
        
    }

     public static AcqOrderHeader searchOrderHeaderByOrderNo(String library_id,String sublibrary_id,String order_no) {
         Session session = HibernateUtil.getSessionFactory().openSession();
         AcqOrderHeader obj=null;

        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(AcqOrderHeader.class)
         
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.subLibraryId", sublibrary_id))
                    .add(Restrictions.eq("id.orderNo", order_no))

                    );


            obj= (AcqOrderHeader)criteria.uniqueResult();
        } catch(Exception e){
        e.printStackTrace();
        }
        finally
        {
        session.close();
        }
        return obj;
    }


      public static ArrayList<AcqRecievingDetails> searchRecieveByRecievingNo(String library_id,String sublibrary_id,String recieving_no) {
         Session session = HibernateUtil.getSessionFactory().openSession();
         ArrayList<AcqRecievingDetails> obj=null;

        try {
         session.beginTransaction();
            Criteria criteria = session.createCriteria(AcqRecievingDetails.class)
         
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.subLibraryId", sublibrary_id))
                    .add(Restrictions.eq("id.recievingNo", recieving_no))

                    );

         obj=(ArrayList<AcqRecievingDetails>)criteria.list();
        }  catch(Exception e){
        e.printStackTrace();
        }
        finally
        {
        session.close();
        }
        return obj;
    }


  public static ArrayList<AcqOrder1> searchOrderByOrderNo(String library_id,String sublibrary_id,String order_no) {
         Session session = HibernateUtil.getSessionFactory().openSession();
         ArrayList<AcqOrder1> obj=null;

        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(AcqOrder1.class)
          
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.subLibraryId", sublibrary_id))
                    .add(Restrictions.eq("id.orderNo", order_no))

                    );

          obj=(ArrayList<AcqOrder1>)criteria.list();
        }  catch(Exception e){
        e.printStackTrace();
        }
        finally
        {
        session.close();
        }
        return obj;
    }
  public static List<ApprovalList> getViewApprovalList(String library_id, String sub_library_id,String orderno){
  Session session =null;
  List<ApprovalList> obj=null;
    
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
            obj=(List<ApprovalList>)query.list();
        }
        catch(Exception e){
        e.printStackTrace();
        }
        finally
        {
        session.close();
        }
        return obj;
    }

 public static List<ApprovalList> getViewApprovalList1(String library_id, String sub_library_id,String orderno){
  Session session =null;
    List<ApprovalList> obj=null;
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
            obj=(List<ApprovalList>)query.list();
        }
         catch(Exception e){
        e.printStackTrace();
        }
        finally
        {
        session.close();
        }
        return obj;
    }
}
