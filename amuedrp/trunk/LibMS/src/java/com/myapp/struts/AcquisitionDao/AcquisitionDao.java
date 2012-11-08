/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.AcquisitionDao;
import com.myapp.struts.Acquisition.AcqAccession;
import com.myapp.struts.Acquisition.AcqForOrderProcessReport;
import com.myapp.struts.Acquisition.AllInvoiceList;
import com.myapp.struts.Acquisition.ApprovalList;
import com.myapp.struts.Acquisition.CirculationList_1_1;
import com.myapp.struts.Acquisition.InvoiceList;
import com.myapp.struts.Acquisition.PaymentUpdateClass;
import com.myapp.struts.Acquisition.PaymentUpdateClass2;
import com.myapp.struts.Acquisition.PlacedOrderList;
import com.myapp.struts.Acquisition.RequestForPayment;
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
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Property;
import org.hibernate.transform.Transformers;
public class AcquisitionDao {

    public static List<AcqForOrderProcessReport> orderProcessReport(String library_id, String sub_library_id,String order_no){
     Session session =null;
     List<AcqForOrderProcessReport> obj = null;
     try {
            session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            String sql="";
            sql="select ab.title,m.recieving_no,m.control_no,m.recieved_copies,m.pending_copies,m.vendor_id,m.order_no from acq_bibliography ab,(select ard.recieving_item_id,ard.recieving_no,ard.control_no,ard.title_id,ard.recieved_copies,ard.pending_copies,arh.vendor_id,arh.order_no from  acq_recieving_header arh,acq_recieving_details ard where ard.recieving_no=arh.recieving_no and ard.library_id='"+library_id+"' and ard.sub_library_id='"+sub_library_id+"' and arh.order_no='"+order_no+"') m where ab.title_id=m.title_id and ab.library_id='"+library_id+"' and ab.sub_library_id='"+sub_library_id+"'";
            Query query =  session.createSQLQuery(sql)
                    .setResultTransformer(Transformers.aliasToBean(AcqForOrderProcessReport.class));
            obj=(List<AcqForOrderProcessReport>) query.list();
            session.getTransaction().commit();
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

  public static List<AcqOrder1> orderProcessReport2(String library_id,String sub_library_id,String recieving_no,String order_no) {
        Session session = HibernateUtil.getSessionFactory().openSession();
  List<AcqOrder1> obj=null;
        try {
            session.beginTransaction();

            Criteria criteria = session.createCriteria(AcqOrder1.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.subLibraryId", sub_library_id))
                    .add(Restrictions.eq("id.orderNo", order_no))
                    .add(Restrictions.eq("recievingNo",recieving_no )));
                  obj= criteria.list();
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


   public static List allOrderList(String library_id,String sub_library_id ) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List obj=null;
        try {
            session.beginTransaction();

            Criteria criteria = session.createCriteria(AcqOrderHeader.class,"aliasOfTableA");
            criteria.createAlias("aliasOfTableA.acqOrder1s","aliasOfTableB",CriteriaSpecification.INNER_JOIN);
            criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            //        if(!library_id.equalsIgnoreCase("all"))
                      criteria.add(Restrictions.eq("aliasOfTableA.id.libraryId",library_id ));
                //    if(!sub_library_id.equalsIgnoreCase("all"))
                      criteria.add(Restrictions.eq("aliasOfTableA.id.subLibraryId",sub_library_id ));
                      Criterion a = Restrictions.eq("aliasOfTableB.recievingStatus", "Received");
                      Criterion b = Restrictions.eq("aliasOfTableB.recievingStatus", "Partially Received");
                      LogicalExpression le = Restrictions.or(a, b);
                      criteria.add(le);
                 //   criteria.add(Restrictions.eq("aliasOfTableB.recievingStatus", "Received"));
                      obj= criteria.list();
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




    public   List<AcqAccession> getBookForAccession(String library_id, String sub_library_id){
    Session session =null;
    List<AcqAccession> obj = null;
    try {
          session= HibernateUtil.getSessionFactory().openSession();
          session.beginTransaction();
          String sql="";
          sql="select nnnt.prn,nnnt.invoice_no,nnnt.recieving_no,nnnt.control_no,nnnt.recieved_copies,nnnt.title_id,ab.title,ab.doc_type,ab.publisher_name,ab.author,ab.lcc_no,ab.sub_author,ab.publishing_yr,ab.publishing_place,ab.edition,ab.isbn,ab.volume_no,ab.sub_author0,ab.sub_author1,ab.sub_author2 from (select nnt.prn,nnt.invoice_no,nnt.recieving_no,nnt.recieved_copies,nnt.control_no,abd.title_id,nnt.library_id,nnt.sub_library_id  from (select areqdet.prn,nt.invoice_no,nt.recieving_no,nt.recieved_copies,nt.control_no,nt.library_id,nt.sub_library_id from (select aid.invoice_no,ared.recieving_no,ared.recieved_copies,ared.control_no,ared.library_id,ared.sub_library_id from acq_recieving_details ared, acq_invoice_detail aid where ared.library_id='"+library_id+"' and ared.sub_library_id='"+sub_library_id+"' and ared.recieving_item_id=aid.recieving_item_id and ared.status='invoice' and aid.status='rfp' ) nt,acq_requestpayment_details areqdet where areqdet.library_id='"+library_id+"' and areqdet.sub_library_id='"+sub_library_id+"' and areqdet.invoice_no=nt.invoice_no and areqdet.recieving_no=nt.recieving_no and areqdet.status='Processed' and areqdet.accession_status is null) nnt,acq_bibliography_details abd where abd.control_no=nnt.control_no and abd.library_id='"+library_id+"' and abd.sub_library_id='"+sub_library_id+"') nnnt,acq_bibliography ab where ab.title_id=nnnt.title_id and ab.library_id='"+library_id+"' and ab.sub_library_id='"+sub_library_id+"'";
          Query query =  session.createSQLQuery(sql)
                    .setResultTransformer(Transformers.aliasToBean(AcqAccession.class));
          obj=(List<AcqAccession>) query.list();
          session.getTransaction().commit();
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


public   List<AcqVendor> searchDoc7(String library_id, String sub_library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
         List<AcqVendor> obj=null;
        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(AcqVendor.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.subLibraryId", sub_library_id)));


            obj= criteria.list();
            session.getTransaction().commit();
        }  catch(Exception e){
        e.printStackTrace();
        }
        finally
        {
        session.close();
        }
        return  obj;
    }



    public   List<AcqOrderHeader> searchOrderHeader(String library_id,String sublibrary_id) {
         Session session = HibernateUtil.getSessionFactory().openSession();
         List<AcqOrderHeader> obj=null;

        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(AcqOrderHeader.class)

                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.subLibraryId", sublibrary_id)));
            Criterion a = Restrictions.eq("orderStatus", "Placed");
            Criterion b = Restrictions.eq("orderStatus", "PC");
            LogicalExpression le = Restrictions.or(a, b);
            criteria.add(le);
            obj=criteria.list();
            session.getTransaction().commit();
        } catch(Exception e){
        e.printStackTrace();
        }
        finally
        {
        session.close();
        }
        return obj;
    }




 public  List<PlacedOrderList> getOrderPlaced(String library_id, String sub_library_id){
  Session session =null;
    List<PlacedOrderList> obj = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            String sql="";



          sql="(select f.title,f.publisher_name,f.author,f.isbn,g.no_of_copies ,g.control_no,g.recieved_copies,g.pending_copies,g.order_no,g.title_id,g.unit_price,g.acq_mode,g.vendor_id from acq_bibliography f,(select e.title_id, e.no_of_copies,e.control_no,e.status,e.unit_price,e.acq_mode,d.recieved_copies,d.pending_copies,d.order_no,d.vendor_id from acq_bibliography_details e,(select a.order_no,a.vendor_id,b.control_no, s.recieved_copies,s.pending_copies from acq_order_header a ,acq_order1 b left outer join (select  recieved_copies,pending_copies,control_no,`time` from acq_recieving_details a  where time =(select max(`time`) from acq_recieving_details b where a.control_no=b.control_no)) s on (b.control_no =s.control_no) where  a.order_no=b.order_no and a.library_id='"+library_id+"' and a.sub_library_id='"+sub_library_id+"') d where e.control_no=d.control_no  and ((e.status='Ordered' or e.status='Partially Received') and e.acq_mode='Firm Order') and e.library_id='"+library_id+"' and e.sub_library_id='"+sub_library_id+"') g where f.title_id=g.title_id and f.library_id='"+library_id+"' and f.sub_library_id='"+sub_library_id+"' ORDER BY g.order_no ASC) union all (select f.title,f.publisher_name,f.author,f.isbn,g.no_of_copies,g.control_no,g.recieved_copies,g.pending_copies,g.order_no,g.title_id,g.unit_price,g.acq_mode,g.vendor_id from acq_bibliography f,(select e.title_id, k.no_of_copies,e.control_no,e.status,e.unit_price,e.acq_mode,d.recieved_copies,d.pending_copies,d.order_no,d.vendor_id from  acq_bibliography_details e,acq_approval k,(select a.order_no,a.vendor_id,b.control_no,s.recieved_copies,s.pending_copies from acq_order_header a,acq_order1 b left outer join (select  recieved_copies,pending_copies,control_no,`time` from acq_recieving_details a  where time =(select max(`time`) from acq_recieving_details b where a.control_no=b.control_no)) s on (b.control_no =s.control_no) where a.order_no=b.order_no and a.library_id='"+library_id+"' and a.sub_library_id='"+sub_library_id+"') d where e.control_no=d.control_no and e.control_no=k.control_no and  k.control_no=d.control_no and d.order_no=k.order_no and ((e.status='Partially Approved' or e.status='Partially Received' or e.status='Fully Approved' or e.status='Ordered') and (e.acq_mode='On Approval' or e.acq_mode='Approved')) and k.status='Ordered' and e.library_id='"+library_id+"' and e.sub_library_id='"+sub_library_id+"') g where f.title_id=g.title_id and f.library_id='"+library_id+"' and f.sub_library_id='"+sub_library_id+"'  ORDER BY g.order_no ASC)";

          Query query =  session.createSQLQuery(sql)
                    .setResultTransformer(Transformers.aliasToBean(PlacedOrderList.class));
            obj=(List<PlacedOrderList>) query.list();
            session.getTransaction().commit();
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



   public  List<PlacedOrderList> getOrderPlaced1(String library_id, String sub_library_id,String order_no,String checkbox,String vendor_id){
     Session session =null;
     List<PlacedOrderList> obj = null;
     try {
        session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            String sql="";

             if(order_no!=null && !order_no.equals("onumber"))
             {    sql="(select f.title,f.publisher_name,f.author,f.isbn,g.no_of_copies ,g.control_no,g.recieved_copies,g.pending_copies,g.order_no,g.title_id,g.unit_price,g.acq_mode,g.vendor_id from acq_bibliography f,(select e.title_id, e.no_of_copies,e.control_no,e.status,e.unit_price,e.acq_mode,d.recieved_copies,d.pending_copies,d.order_no,d.vendor_id from acq_bibliography_details e,(select a.order_no,a.vendor_id,b.control_no,s.recieved_copies,s.pending_copies from acq_order_header a ,acq_order1 b left outer join (select  recieved_copies,pending_copies,control_no,`time` from acq_recieving_details a  where time =(select max(`time`) from acq_recieving_details b where a.control_no=b.control_no)) s on (b.control_no =s.control_no) where a.order_no=b.order_no and a.library_id='"+library_id+"' and a.sub_library_id='"+sub_library_id+"' and b.order_no='"+order_no+"') d where e.control_no=d.control_no  and ((e.status='Ordered' or e.status='Partially Received') and e.acq_mode='Firm Order') and e.library_id='"+library_id+"' and e.sub_library_id='"+sub_library_id+"') g where f.title_id=g.title_id and f.library_id='"+library_id+"' and f.sub_library_id='"+sub_library_id+"' ORDER BY g.order_no ASC) union all (select f.title,f.publisher_name,f.author,f.isbn,g.no_of_copies,g.control_no,g.recieved_copies,g.pending_copies,g.order_no,g.title_id,g.unit_price,g.acq_mode,g.vendor_id from acq_bibliography f,(select e.title_id, k.no_of_copies,e.control_no,e.status,e.unit_price,e.acq_mode,d.recieved_copies,d.pending_copies,d.order_no,d.vendor_id from  acq_bibliography_details e,acq_approval k,(select a.order_no,a.vendor_id,b.control_no,s.recieved_copies,s.pending_copies from acq_order_header a,acq_order1 b left outer join (select  recieved_copies,pending_copies,control_no,`time` from acq_recieving_details a  where time =(select max(`time`) from acq_recieving_details b where a.control_no=b.control_no)) s on (b.control_no =s.control_no ) where a.order_no=b.order_no and a.library_id='"+library_id+"' and a.sub_library_id='"+sub_library_id+"' and b.order_no='"+order_no+"') d where e.control_no=d.control_no and e.control_no=k.control_no and  k.control_no=d.control_no and d.order_no=k.order_no and ((e.status='Partially Approved' or e.status='Partially Received' or e.status='Fully Approved' or e.status='Ordered') and (e.acq_mode='On Approval' or e.acq_mode='Approved')) and k.status='Ordered' and e.library_id='"+library_id+"' and e.sub_library_id='"+sub_library_id+"') g where f.title_id=g.title_id and f.library_id='"+library_id+"' and f.sub_library_id='"+sub_library_id+"'  ORDER BY g.order_no ASC)";
                  System.out.println("i am in order_no");
             }
             if(checkbox!=null)
             {    sql="(select f.title,f.publisher_name,f.author,f.isbn,g.no_of_copies ,g.control_no,g.recieved_copies,g.pending_copies,g.order_no,g.title_id,g.unit_price,g.acq_mode,g.vendor_id from acq_bibliography f,(select e.title_id, e.no_of_copies,e.control_no,e.status,e.unit_price,e.acq_mode,d.recieved_copies,d.pending_copies,d.order_no,d.vendor_id from acq_bibliography_details e,(select a.order_no,a.vendor_id,b.control_no,s.recieved_copies,s.pending_copies from acq_order_header a ,acq_order1 b left outer join (select  recieved_copies,pending_copies,control_no,`time` from acq_recieving_details a  where time =(select max(`time`) from acq_recieving_details b where a.control_no=b.control_no)) s on (b.control_no =s.control_no) where a.order_no=b.order_no and a.library_id='"+library_id+"' and a.sub_library_id='"+sub_library_id+"') d where e.control_no=d.control_no  and ((e.status='Ordered' or e.status='Partially Received') and e.acq_mode='Firm Order') and e.library_id='"+library_id+"' and e.sub_library_id='"+sub_library_id+"') g where f.title_id=g.title_id and f.library_id='"+library_id+"' and f.sub_library_id='"+sub_library_id+"' ORDER BY g.order_no ASC) union all (select f.title,f.publisher_name,f.author,f.isbn,g.no_of_copies,g.control_no,g.recieved_copies,g.pending_copies,g.order_no,g.title_id,g.unit_price,g.acq_mode,g.vendor_id from acq_bibliography f,(select e.title_id, k.no_of_copies,e.control_no,e.status,e.unit_price,e.acq_mode,d.recieved_copies,d.pending_copies,d.order_no,d.vendor_id from  acq_bibliography_details e,acq_approval k,(select a.order_no,a.vendor_id,b.control_no,s.recieved_copies,s.pending_copies from acq_order_header a,acq_order1 b left outer join (select  recieved_copies,pending_copies,control_no,`time` from acq_recieving_details a  where time =(select max(`time`) from acq_recieving_details b where a.control_no=b.control_no)) s on (b.control_no =s.control_no) where a.order_no=b.order_no and a.library_id='"+library_id+"' and a.sub_library_id='"+sub_library_id+"') d where e.control_no=d.control_no and e.control_no=k.control_no and  k.control_no=d.control_no and d.order_no=k.order_no and ((e.status='Partially Approved' or e.status='Partially Received' or e.status='Fully Approved' or e.status='Ordered') and (e.acq_mode='On Approval' or e.acq_mode='Approved')) and k.status='Ordered' and e.library_id='"+library_id+"' and e.sub_library_id='"+sub_library_id+"') g where f.title_id=g.title_id and f.library_id='"+library_id+"' and f.sub_library_id='"+sub_library_id+"'  ORDER BY g.order_no ASC)";
                  System.out.println("i am in checkbox");
             }
             if(vendor_id!=null && !vendor_id.equals("all"))
             {
                 sql="(select f.title,f.publisher_name,f.author,f.isbn,g.no_of_copies ,g.control_no,g.recieved_copies,g.pending_copies,g.order_no,g.title_id,g.unit_price,g.acq_mode,g.vendor_id from acq_bibliography f,(select e.title_id, e.no_of_copies,e.control_no,e.status,e.unit_price,e.acq_mode,d.recieved_copies,d.pending_copies,d.order_no,d.vendor_id from acq_bibliography_details e,(select a.order_no,a.vendor_id,b.control_no,s.recieved_copies,s.pending_copies from acq_order_header a ,acq_order1 b left outer join (select  recieved_copies,pending_copies,control_no,`time` from acq_recieving_details a  where time =(select max(`time`) from acq_recieving_details b where a.control_no=b.control_no)) s on (b.control_no =s.control_no) where a.order_no=b.order_no and a.vendor_id='"+vendor_id+"' and a.library_id='"+library_id+"' and a.sub_library_id='"+sub_library_id+"') d where e.control_no=d.control_no  and ((e.status='Ordered' or e.status='Partially Received') and e.acq_mode='Firm Order') and e.library_id='"+library_id+"' and e.sub_library_id='"+sub_library_id+"') g where f.title_id=g.title_id  and f.library_id='"+library_id+"' and f.sub_library_id='"+sub_library_id+"' ORDER BY g.order_no ASC) union all (select f.title,f.publisher_name,f.author,f.isbn,g.no_of_copies,g.control_no,g.recieved_copies,g.pending_copies,g.order_no,g.title_id,g.unit_price,g.acq_mode,g.vendor_id from acq_bibliography f,(select e.title_id, k.no_of_copies,e.control_no,e.status,e.unit_price,e.acq_mode,d.recieved_copies,d.pending_copies,d.order_no,d.vendor_id from  acq_bibliography_details e,acq_approval k,(select a.order_no,a.vendor_id,b.control_no,s.recieved_copies,s.pending_copies from acq_order_header a,acq_order1 b left outer join (select  recieved_copies,pending_copies,control_no,`time` from acq_recieving_details a  where time =(select max(`time`) from acq_recieving_details b where a.control_no=b.control_no)) s on (b.control_no =s.control_no) where a.order_no=b.order_no and a.vendor_id='"+vendor_id+"'  and a.library_id='"+library_id+"' and a.sub_library_id='"+sub_library_id+"') d where e.control_no=d.control_no and e.control_no=k.control_no and  k.control_no=d.control_no and d.order_no=k.order_no and ((e.status='Partially Approved' or e.status='Partially Received' or e.status='Fully Approved' or e.status='Ordered') and (e.acq_mode='On Approval' or e.acq_mode='Approved')) and k.status='Ordered' and e.library_id='"+library_id+"' and e.sub_library_id='"+sub_library_id+"') g where f.title_id=g.title_id and f.library_id='"+library_id+"' and f.sub_library_id='"+sub_library_id+"'  ORDER BY g.order_no ASC)";
                 System.out.println("i am in vendor_id");
             }
             if(vendor_id.equals("all") && order_no.equals("onumber") && checkbox==null )
             {
                sql="(select f.title,f.publisher_name,f.author,f.isbn,g.no_of_copies ,g.control_no,g.recieved_copies,g.pending_copies,g.order_no,g.title_id,g.unit_price,g.acq_mode,g.vendor_id from acq_bibliography f,(select e.title_id, e.no_of_copies,e.control_no,e.status,e.unit_price,e.acq_mode,d.recieved_copies,d.pending_copies,d.order_no,d.vendor_id from acq_bibliography_details e,(select a.order_no,a.vendor_id,b.control_no,s.recieved_copies,s.pending_copies from acq_order_header a ,acq_order1 b left outer join (select  recieved_copies,pending_copies,control_no,`time` from acq_recieving_details a  where time =(select max(`time`) from acq_recieving_details b where a.control_no=b.control_no)) s on (b.control_no =s.control_no) where a.order_no=b.order_no and a.library_id='"+library_id+"' and a.sub_library_id='"+sub_library_id+"') d where e.control_no=d.control_no  and ((e.status='Ordered' or e.status='Partially Received') and e.acq_mode='Firm Order') and e.library_id='"+library_id+"' and e.sub_library_id='"+sub_library_id+"') g where f.title_id=g.title_id and f.library_id='"+library_id+"' and f.sub_library_id='"+sub_library_id+"' ORDER BY g.order_no ASC) union all (select f.title,f.publisher_name,f.author,f.isbn,g.no_of_copies,g.control_no,g.recieved_copies,g.pending_copies,g.order_no,g.title_id,g.unit_price,g.acq_mode,g.vendor_id from acq_bibliography f,(select e.title_id, k.no_of_copies,e.control_no,e.status,e.unit_price,e.acq_mode,d.recieved_copies,d.pending_copies,d.order_no,d.vendor_id from  acq_bibliography_details e,acq_approval k,(select a.order_no,a.vendor_id,b.control_no,s.recieved_copies,s.pending_copies from acq_order_header a,acq_order1 b left outer join (select  recieved_copies,pending_copies,control_no,`time` from acq_recieving_details a  where time =(select max(`time`) from acq_recieving_details b where a.control_no=b.control_no)) s on (b.control_no =s.control_no) where a.order_no=b.order_no and a.library_id='"+library_id+"' and a.sub_library_id='"+sub_library_id+"') d where e.control_no=d.control_no and e.control_no=k.control_no and  k.control_no=d.control_no and d.order_no=k.order_no and ((e.status='Partially Approved' or e.status='Partially Received' or e.status='Fully Approved' or e.status='Ordered') and (e.acq_mode='On Approval'  or e.acq_mode='Approved')) and k.status='Ordered' and e.library_id='"+library_id+"' and e.sub_library_id='"+sub_library_id+"') g where f.title_id=g.title_id and f.library_id='"+library_id+"' and f.sub_library_id='"+sub_library_id+"'  ORDER BY g.order_no ASC)";
                System.out.println("i am in simple");
             }
            Query query =  session.createSQLQuery(sql)
                    .setResultTransformer(Transformers.aliasToBean(PlacedOrderList.class));
            obj=(List<PlacedOrderList>) query.list();
            session.getTransaction().commit();
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



  


    public   boolean insertReceiveOrder(AcqRecievingHeader acqrecheader,AcqRecievingDetails acqrecdetails,AcqOrder1 acqorder1,AcqOrderHeader acqorderheader,AcqBibliographyDetails acqbibdetails) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(acqrecheader);
            session.save(acqrecdetails);
            session.update(acqorder1);
            session.update(acqorderheader);
            session.update(acqbibdetails);

            tx.commit();
            return true;
        } catch (Exception e) {

            tx.rollback();
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }


    public   boolean insertReceiveOrder1(AcqRecievingDetails acqrecdetails,AcqOrder1 acqorder1,AcqOrderHeader acqorderheader,AcqBibliographyDetails acqbibdetails) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(acqrecdetails);
            session.update(acqorder1);
            session.update(acqorderheader);
            session.update(acqbibdetails);

            tx.commit();
            return true;
        } catch (Exception e) {

            tx.rollback();
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }


    public   Integer returnReceivingItemId(String library_id, String sublibrary_id) {

          Integer maxreceivingitemid =null;
          Session session = HibernateUtil.getSessionFactory().openSession();
          try {
               session.beginTransaction();
               Criteria criteria = session.createCriteria(AcqRecievingDetails.class);
               Criterion a = Restrictions.eq("id.libraryId", library_id);
               Criterion b = Restrictions.eq("id.subLibraryId", sublibrary_id);
               LogicalExpression le = Restrictions.and(a, b);
               maxreceivingitemid = criteria.add(le).setProjection(Projections.count("id.recievingItemId")).uniqueResult()==null?0:Integer.valueOf(criteria.add(le).setProjection(Projections.count("id.recievingItemId")).uniqueResult().toString());
          if (maxreceivingitemid == null)
          {
               maxreceivingitemid = 1;
          }
          else
          {
                maxreceivingitemid++;
          }
          session.getTransaction().commit();

         } catch(Exception e){
        e.printStackTrace();
        }
        finally
        {
           session.close();
        }
        return maxreceivingitemid;
}



    public   AcqOrder1 searchAcqOrder1( String library_id, String sub_library_id,String order_no,int control_no) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        AcqOrder1 obj=null;
        try{
        session.beginTransaction();
        Criteria criteria = session.createCriteria(AcqOrder1.class).add(Restrictions.conjunction().add(Restrictions.eq("id.libraryId", library_id)).add(Restrictions.eq("id.subLibraryId", sub_library_id)).add(Restrictions.eq("id.orderNo",order_no)).add(Restrictions.eq("controlNo",control_no)));
                obj=(AcqOrder1) criteria.uniqueResult();
                session.getTransaction().commit();
        }
        catch(Exception e){
        e.printStackTrace();
        }
        finally{
        session.close();
        }
        return obj;
    }

     public   boolean updateOrder1table(AcqOrder1 obj) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(obj);
            tx.commit();
            return true;
        } catch (Exception e) {

            tx.rollback();
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }


    public   List<AcqOrder1> searchListAcqOrder1( String library_id, String sub_library_id,String order_no) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<AcqOrder1> obj=null;
        try{
        session.beginTransaction();
        Criteria criteria = session.createCriteria(AcqOrder1.class).add(Restrictions.conjunction().add(Restrictions.eq("id.libraryId", library_id)).add(Restrictions.eq("id.subLibraryId", sub_library_id)).add(Restrictions.eq("id.orderNo",order_no)));
                obj=(List<AcqOrder1>) criteria.list();
                session.getTransaction().commit();
        }
        catch(Exception e){
        e.printStackTrace();
        }
        finally{
        session.close();
        }
        return obj;
    }


     public   AcqRecievingHeader searchByRecievingNo(String library_id,String sublibrary_id,String recieving_no) {
         Session session = HibernateUtil.getSessionFactory().openSession();
         AcqRecievingHeader obj=null;

        try {
         session.beginTransaction();
            Criteria criteria = session.createCriteria(AcqRecievingHeader.class)

                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.subLibraryId", sublibrary_id))
                    .add(Restrictions.eq("id.recievingNo", recieving_no))
                   // .add(Restrictions.eq("orderNoorderNo", order_no))
                    );

         obj=(AcqRecievingHeader)criteria.uniqueResult();
         session.getTransaction().commit();
        }  catch(Exception e){
        e.printStackTrace();
        }
        finally
        {
        session.close();
        }
        return obj;
    }


     public   AcqRecievingDetails searchRecievingDetails(String library_id,String sublibrary_id,int control_no) {
         Session session = HibernateUtil.getSessionFactory().openSession();
         AcqRecievingDetails obj=null;

        try {
         session.beginTransaction();
            Criteria criteria = session.createCriteria(AcqRecievingDetails.class)

                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.subLibraryId", sublibrary_id))
                    .add(Restrictions.eq("controlNo", control_no))
                   // .add(Restrictions.eq("orderNoorderNo", order_no))
                    );

         obj=(AcqRecievingDetails)criteria.uniqueResult();
         session.getTransaction().commit();
        }  catch(Exception e){
        e.printStackTrace();
        }
        finally
        {
        session.close();
        }
        return obj;
    }

    public   List<AcqRecievingDetails> searchRecievingDetailsByReceivingNo(String library_id,String sublibrary_id,String Recieving_no) {
         Session session = HibernateUtil.getSessionFactory().openSession();
         List<AcqRecievingDetails> obj=null;

        try {
         session.beginTransaction();
            Criteria criteria = session.createCriteria(AcqRecievingDetails.class)

                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.subLibraryId", sublibrary_id))
                    .add(Restrictions.eq("id.recievingNo", Recieving_no))
                   // .add(Restrictions.eq("orderNoorderNo", order_no))
                    );

         obj=(List<AcqRecievingDetails>)criteria.list();
         session.getTransaction().commit();
        }  catch(Exception e){
        e.printStackTrace();
        }
        finally
        {
        session.close();
        }
        return obj;
    }


       public   AcqRecievingDetails searchRecievingDetailsByKey(String library_id,String sublibrary_id,String recieving_no,int recieving_item_id) {
         Session session = HibernateUtil.getSessionFactory().openSession();
         AcqRecievingDetails obj=null;

        try {
         session.beginTransaction();
            Criteria criteria = session.createCriteria(AcqRecievingDetails.class)

                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.subLibraryId", sublibrary_id))
                    .add(Restrictions.eq("id.recievingNo", recieving_no))
                    .add(Restrictions.eq("id.recievingItemId", recieving_item_id))
                    );

         obj=(AcqRecievingDetails)criteria.uniqueResult();
         session.getTransaction().commit();
        }  catch(Exception e){
        e.printStackTrace();
        }
        finally
        {
        session.close();
        }
        return obj;
    }



    public   List<AcqRecievingHeader> searchForReceivinggNo(String library_id,String sublibrary_id) {
         Session session = HibernateUtil.getSessionFactory().openSession();
         List<AcqRecievingHeader> obj=null;

        try {
         session.beginTransaction();
            Criteria criteria = session.createCriteria(AcqRecievingHeader.class)

                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.subLibraryId", sublibrary_id)));

         obj=(List<AcqRecievingHeader>)criteria.list();
         session.getTransaction().commit();
        }  catch(Exception e){
        e.printStackTrace();
        }
        finally
        {
        session.close();
        }
        return obj;
    }


    public   List<InvoiceList> getReceivedItems(String library_id, String sub_library_id,String receiving_no,String order_no){
    Session session =null;
    List<InvoiceList> obj = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            String sql="";



          sql="select b.order_no,a.control_no,a.recieved_copies,a.unit_price,b.recieving_no,a.vendor_id,b.recieved_by,a.status,a.title,a.pending_copies  from (select e.library_id,e.sub_library_id,e.recieving_no,e.status,e.control_no,e.recieved_copies,e.unit_price,e.vendor_id,e.pending_copies ,d.title from acq_recieving_details e,acq_bibliography d where e.library_id=d.library_id and e.sub_library_id=d.sub_library_id and e.title_id=d.title_id ) a,acq_recieving_header b where a.library_id=b.library_id and a.sub_library_id=b.sub_library_id and a.recieving_no=b.recieving_no and a.status is null";

          if(order_no!=null)
          {
              if(order_no!="")
              {
                  if(order_no.equals("onumber"))sql=sql;
                  else
                  sql=sql+" and b.order_no='"+order_no+"'";

              }
              else
                  sql=sql+";";
          }

          if(receiving_no!=null)
          {
              if(receiving_no!="")
              {
                  if(receiving_no.equals("all"))sql=sql;
                  else
                  sql=sql+" and b.recieving_no='"+receiving_no+"';";

              }
              else
                  sql=sql+";";
          }



          Query query =  session.createSQLQuery(sql)
                    .setResultTransformer(Transformers.aliasToBean(InvoiceList.class));
            obj=(List<InvoiceList>) query.list();
            session.getTransaction().commit();
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




     public   List<AcqInvoiceHeader> searchByInovoiceHeader(String library_id,String sublibrary_id,String invoice_no) {
         Session session = HibernateUtil.getSessionFactory().openSession();
         List<AcqInvoiceHeader> obj=null;

        try {
         session.beginTransaction();
            Criteria criteria = session.createCriteria(AcqInvoiceHeader.class)

                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.sublibraryId", sublibrary_id))
                    .add(Restrictions.eq("id.invoiceNo", invoice_no))
                   );

         obj=(List<AcqInvoiceHeader>)criteria.list();
         session.getTransaction().commit();
        }  catch(Exception e){
        e.printStackTrace();
        }
        finally
        {
        session.close();
        }
        return obj;
    }


    public   List<AcqInvoiceHeader> allByInovoiceHeader(String library_id,String sublibrary_id) {
         Session session = HibernateUtil.getSessionFactory().openSession();
         List<AcqInvoiceHeader> obj=null;

        try {
         session.beginTransaction();
            Criteria criteria = session.createCriteria(AcqInvoiceHeader.class)

                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.sublibraryId", sublibrary_id))

                   );

         obj=(List<AcqInvoiceHeader>)criteria.list();
         session.getTransaction().commit();
        }  catch(Exception e){
        e.printStackTrace();
        }
        finally
        {
        session.close();
        }
        return obj;
    }

   public   AcqInvoiceDetail searchByInovoiceDetails(String library_id,String sublibrary_id,String invoice_no,String receiving_no) {
         Session session = HibernateUtil.getSessionFactory().openSession();
         AcqInvoiceDetail obj=null;

        try {
         session.beginTransaction();
            Criteria criteria = session.createCriteria(AcqInvoiceDetail.class)

                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.subLibraryId", sublibrary_id))
                    .add(Restrictions.eq("id.invoiceNo", invoice_no))
                    .add(Restrictions.eq("id.recievingNo", receiving_no))
                   );

         obj=(AcqInvoiceDetail)criteria.uniqueResult();
         session.getTransaction().commit();
        }  catch(Exception e){
        e.printStackTrace();
        }
        finally
        {
        session.close();
        }
        return obj;
    }

   public   List<AcqInvoiceDetail> searchInovoiceDetails(String library_id,String sublibrary_id,String invoice_no) {
         Session session = HibernateUtil.getSessionFactory().openSession();
         List<AcqInvoiceDetail> obj=null;

        try {
         session.beginTransaction();
            Criteria criteria = session.createCriteria(AcqInvoiceDetail.class)

                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.subLibraryId", sublibrary_id))
                    .add(Restrictions.eq("id.invoiceNo", invoice_no))
                   );

         obj=(List<AcqInvoiceDetail>)criteria.list();
         session.getTransaction().commit();
        }  catch(Exception e){
        e.printStackTrace();
        }
        finally
        {
        session.close();
        }
        return obj;
    }


     public   boolean insertInInovoiceDetails(AcqInvoiceDetail acqindetail,AcqRecievingDetails acqredetails) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(acqindetail);
            session.update(acqredetails);
            tx.commit();
            return true;
        } catch (Exception e) {

            tx.rollback();
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }

    public   boolean insertInInovoiceHeader(AcqInvoiceHeader obj) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(obj);
            tx.commit();
            return true;
        } catch (Exception e) {

            tx.rollback();
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }


   public   AcqRecievingDetails searchInRecievingDetails(String library_id,String sublibrary_id,String recieving_no,String pending_copies) {
         Session session = HibernateUtil.getSessionFactory().openSession();
         AcqRecievingDetails obj=null;

        try {
         session.beginTransaction();
            Criteria criteria = session.createCriteria(AcqRecievingDetails.class)

                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.subLibraryId", sublibrary_id))
                    .add(Restrictions.eq("id.recievingNo", recieving_no))
                    .add(Restrictions.eq("pendingCopies", pending_copies))
                    );

         obj=(AcqRecievingDetails)criteria.uniqueResult();
         session.getTransaction().commit();
        }  catch(Exception e){
        e.printStackTrace();
        }
        finally
        {
        session.close();
        }
        return obj;
    }


    public   List<AllInvoiceList> getAllInvoice(String library_id, String sub_library_id,String invoice_no,String order_no,String vendor_id){
    Session session =null;
    List<AllInvoiceList> obj = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            String sql="";



          sql="select h.invoice_no,d.order_no,h.invoice_date,h.recieved_by,d.recieving_no,d.total_amount,d.vendor_id,d.discount,d.net_total from acq_invoice_header h,acq_invoice_detail d where h.invoice_no=d.invoice_no and h.library_id=d.library_id and h.sublibrary_id=d.sub_library_id and (d.status is null or d.status=' ')";

           if(invoice_no!=null)
          {
              if(invoice_no!="")
              {
                  if(invoice_no.equals("i_no"))sql=sql;
                  else
                  sql=sql+" and h.invoice_no='"+invoice_no+"'";

              }
              else
                  sql=sql+";";
          }

          if(order_no!=null)
          {
              if(order_no!="")
              {
                  if(order_no.equals("o_no"))sql=sql;
                  else
                  sql=sql+" and h.order_no='"+order_no+"'";

              }
              else
                  sql=sql+";";
          }

          if(vendor_id!=null)
          {
              if(vendor_id!="")
              {
                  if(vendor_id.equals("v_id"))sql=sql;
                  else
                  sql=sql+" and d.vendor_id='"+vendor_id+"';";

              }
              else
                  sql=sql+";";
          }

          Query query =  session.createSQLQuery(sql)
                    .setResultTransformer(Transformers.aliasToBean(AllInvoiceList.class));
            obj=(List<AllInvoiceList>) query.list();
            session.getTransaction().commit();
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



    public   List<AcqRequestpaymentHeader> searchForPrn(String library_id,String sublibrary_id,String prn) {
         Session session = HibernateUtil.getSessionFactory().openSession();
         List<AcqRequestpaymentHeader> obj=null;

        try {
         session.beginTransaction();
            Criteria criteria = session.createCriteria(AcqRequestpaymentHeader.class)

                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.subLibraryId", sublibrary_id))
                    .add(Restrictions.eq("id.prn", prn))
                    );

         obj=(List<AcqRequestpaymentHeader>)criteria.list();
         session.getTransaction().commit();
        }  catch(Exception e){
        e.printStackTrace();
        }
        finally
        {
        session.close();
        }
        return obj;
    }

     public   List<AcqRequestpaymentHeader> searchForAllPrn(String library_id,String sublibrary_id) {
         Session session = HibernateUtil.getSessionFactory().openSession();
         List<AcqRequestpaymentHeader> obj=null;

        try {
         session.beginTransaction();
            Criteria criteria = session.createCriteria(AcqRequestpaymentHeader.class)

                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.subLibraryId", sublibrary_id))

                    );

         obj=(List<AcqRequestpaymentHeader>)criteria.list();
         session.getTransaction().commit();
        }  catch(Exception e){
        e.printStackTrace();
        }
        finally
        {
        session.close();
        }
        return obj;
    }

    public   List<AcqRequestpaymentHeader> searchForAllPrn1(String library_id,String sublibrary_id) {
         Session session = HibernateUtil.getSessionFactory().openSession();
         List<AcqRequestpaymentHeader> obj=null;

        try {
         session.beginTransaction();
            Criteria criteria = session.createCriteria(AcqRequestpaymentHeader.class)

                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.subLibraryId", sublibrary_id))
                    .add(Restrictions.eq("status", "processing"))
                    );

         obj=(List<AcqRequestpaymentHeader>)criteria.list();
         session.getTransaction().commit();
        }  catch(Exception e){
        e.printStackTrace();
        }
        finally
        {
        session.close();
        }
        return obj;
    }

     public   List<AcqRequestpaymentHeader> searchForAllPrn2(String library_id,String sublibrary_id) {
         Session session = HibernateUtil.getSessionFactory().openSession();
         List<AcqRequestpaymentHeader> obj=null;

        try {
         session.beginTransaction();
            Criteria criteria = session.createCriteria(AcqRequestpaymentHeader.class)

                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.subLibraryId", sublibrary_id))
                    .add(Restrictions.isNull("status"))
                    );

         obj=(List<AcqRequestpaymentHeader>)criteria.list();
         session.getTransaction().commit();
        }  catch(Exception e){
        e.printStackTrace();
        }
        finally
        {
        session.close();
        }
        return obj;
    }


    public   AcqRequestpaymentHeader searchForPrnNo(String library_id,String sublibrary_id,String prn) {
         Session session = HibernateUtil.getSessionFactory().openSession();
         AcqRequestpaymentHeader obj=null;

        try {
         session.beginTransaction();
            Criteria criteria = session.createCriteria(AcqRequestpaymentHeader.class)

                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.subLibraryId", sublibrary_id))
                    .add(Restrictions.eq("id.prn", prn))
                    );

         obj=(AcqRequestpaymentHeader)criteria.uniqueResult();
         session.getTransaction().commit();
        }  catch(Exception e){
        e.printStackTrace();
        }
        finally
        {
        session.close();
        }
        return obj;
    }

    public   List<AcqRequestpaymentDetails> searchForPrnList(String library_id,String sublibrary_id,String prn) {
         Session session = HibernateUtil.getSessionFactory().openSession();
         List<AcqRequestpaymentDetails> obj=null;

        try {
         session.beginTransaction();
            Criteria criteria = session.createCriteria(AcqRequestpaymentDetails.class)

                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.subLibraryId", sublibrary_id))
                    .add(Restrictions.eq("id.prn", prn))
                    );

         obj=(List<AcqRequestpaymentDetails>)criteria.list();
         session.getTransaction().commit();
        }  catch(Exception e){
        e.printStackTrace();
        }
        finally
        {
        session.close();
        }
        return obj;
    }

    public   List<AcqRequestpaymentDetails> searchForPrnList1(String library_id,String sublibrary_id,String prn) {
         Session session = HibernateUtil.getSessionFactory().openSession();
         List<AcqRequestpaymentDetails> obj=null;

        try {
         session.beginTransaction();
            Criteria criteria = session.createCriteria(AcqRequestpaymentDetails.class)

                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.subLibraryId", sublibrary_id))
                    .add(Restrictions.eq("id.prn", prn))
                    .add(Restrictions.eq("status", "processed"))
                    );

         obj=(List<AcqRequestpaymentDetails>)criteria.list();
         session.getTransaction().commit();
        }  catch(Exception e){
        e.printStackTrace();
        }
        finally
        {
        session.close();
        }
        return obj;
    }


    public   AcqRequestpaymentDetails ProcessForPrnList(String library_id,String sublibrary_id,String prn,String invoice_no,String recieving_no) {
         Session session = HibernateUtil.getSessionFactory().openSession();
         AcqRequestpaymentDetails obj=null;

        try {
         session.beginTransaction();
            Criteria criteria = session.createCriteria(AcqRequestpaymentDetails.class)

                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.subLibraryId", sublibrary_id))
                    .add(Restrictions.eq("id.prn", prn))
                    .add(Restrictions.eq("id.invoiceNo", invoice_no))
                    .add(Restrictions.eq("id.recievingNo", recieving_no))
                    );

         obj=(AcqRequestpaymentDetails)criteria.uniqueResult();
         session.getTransaction().commit();
        }  catch(Exception e){
        e.printStackTrace();
        }
        finally
        {
        session.close();
        }
        return obj;
    }



     public   boolean insertInPaymentRequestDetail(AcqRequestpaymentDetails obj,AcqInvoiceDetail obj2) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(obj);
            session.update(obj2);
            tx.commit();
            return true;
        } catch (Exception e) {

            tx.rollback();
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }


     public   boolean insertInPaymentRequestHeader(AcqRequestpaymentHeader obj) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.saveOrUpdate(obj);
            tx.commit();
            return true;
        } catch (Exception e) {

            tx.rollback();
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }


     public   List<RequestForPayment> getViewPrn(String library_id, String sub_library_id,String prn){
     Session session =null;
     List<RequestForPayment> obj = null;
     try {
            session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            String sql="";
            sql="select d.prn,d.invoice_no,d.order_no,d.recieving_no,d.vendor_id,d.total_amt,h.prn_date,h.total_amount from acq_requestpayment_header h,acq_requestpayment_details d where h.library_id=d.library_id and h.sub_library_id=d.sub_library_id and h.prn=d.prn and h.prn='"+prn+"' and d.status is null";
            Query query =  session.createSQLQuery(sql)
                    .setResultTransformer(Transformers.aliasToBean(RequestForPayment.class));
            obj=(List<RequestForPayment>) query.list();
            session.getTransaction().commit();
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

     public   List<PaymentUpdateClass> getDistinctPrn(String library_id, String sub_library_id){
     Session session =null;
     List<PaymentUpdateClass> obj = null;
     try {
            session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            String sql="";
            sql="select distinct d.prn from acq_requestpayment_details d,acq_requestpayment_header h where d.library_id='"+library_id+"' and d.sub_library_id='"+sub_library_id+"' and d.prn=h.prn and d.status='process'";
            Query query =  session.createSQLQuery(sql)
                    .setResultTransformer(Transformers.aliasToBean(PaymentUpdateClass.class));
            obj=(List<PaymentUpdateClass>) query.list();
            session.getTransaction().commit();
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

     public   List<PaymentUpdateClass2> getDistinctPrnProcessed(String library_id, String sub_library_id){
     Session session =null;
     List<PaymentUpdateClass2> obj = null;
     try {
            session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            String sql="";
            sql="select distinct h.prn,h.total_amount,h.vendor_id,h.status,d.payment_update_date,h.no_of_invoices from acq_requestpayment_details d,acq_requestpayment_header h where d.library_id='"+library_id+"' and d.sub_library_id='"+sub_library_id+"' and d.prn=h.prn and h.status='processed'";
            Query query =  session.createSQLQuery(sql)
                    .setResultTransformer(Transformers.aliasToBean(PaymentUpdateClass2.class));
            obj=(List<PaymentUpdateClass2>) query.list();
            session.getTransaction().commit();
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

    public   boolean deleteInPaymentRequestDetail(AcqRequestpaymentDetails obj,AcqInvoiceDetail obj2) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(obj);
            session.update(obj2);
            tx.commit();
            return true;
        } catch (Exception e) {

            tx.rollback();
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }

      public   boolean processInPaymentRequestDetail(AcqRequestpaymentDetails obj) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(obj);
            tx.commit();
            return true;
        } catch (Exception e) {

            tx.rollback();
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }



     public   AcqBudgetTransaction acqBudgetTransactionControlNo(String library_id,String control_no) {
         Session session = HibernateUtil.getSessionFactory().openSession();
         AcqBudgetTransaction obj=null;

        try {
         session.beginTransaction();
            Criteria criteria = session.createCriteria(AcqBudgetTransaction.class)

                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("controlNo", control_no))
                   );

         obj=(AcqBudgetTransaction)criteria.uniqueResult();
         session.getTransaction().commit();
        }  catch(Exception e){
        e.printStackTrace();
        }
        finally
        {
        session.close();
        }
        return obj;
    }

    public   AcqBudgetAllocation acqBudgetAllocationDelete(String library_id,String budgetheadid,String financial_yr) {
         Session session = HibernateUtil.getSessionFactory().openSession();
         AcqBudgetAllocation obj=null;

        try {
         session.beginTransaction();
            Criteria criteria = session.createCriteria(AcqBudgetAllocation.class)

                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("budgetheadId", budgetheadid))
                    .add(Restrictions.eq("financialYr1", financial_yr))
                   );

         obj=(AcqBudgetAllocation)criteria.uniqueResult();
         session.getTransaction().commit();
        }  catch(Exception e){
        e.printStackTrace();
        }
        finally
        {
        session.close();
        }
        return obj;
    }

     public   boolean paymentUpdate(AcqRequestpaymentDetails obj1,AcqBudgetTransaction obj2,AcqBudgetAllocation obj3) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(obj1);
            session.update(obj2);
            session.update(obj3);
            tx.commit();
            return true;
        } catch (Exception e) {

            tx.rollback();
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }


//    public   List<AcqAccession> getBookForAccession(String library_id, String sub_library_id){
//    Session session =null;
//    List<AcqAccession> obj = null;
//    try {
//          session= HibernateUtil.getSessionFactory().openSession();
//          session.beginTransaction();
//          String sql="";
//          sql="select nnnt.recieved_copies,nnnt.title_id,ab.title,ab.doc_type,ab.publisher_name,ab.author,ab.lcc_no,ab.sub_author,ab.publishing_yr,ab.publishing_place,ab.edition,ab.isbn,ab.volume_no,ab.sub_author0,ab.sub_author1,ab.sub_author2 from (select nnt.recieved_copies,nnt.control_no,abd.title_id,nnt.library_id,nnt.sub_library_id  from (select nt.recieved_copies,nt.control_no,nt.library_id,nt.sub_library_id from (select aid.invoice_no,ared.recieving_no,ared.recieved_copies,ared.control_no,ared.library_id,ared.sub_library_id from acq_recieving_details ared, acq_invoice_detail aid where ared.library_id='"+library_id+"' and ared.sub_library_id='"+sub_library_id+"' and ared.recieving_item_id=aid.recieving_item_id and ared.status='invoice' and aid.status='rfp' ) nt,acq_requestpayment_details areqdet where areqdet.library_id='"+library_id+"' and areqdet.sub_library_id='"+sub_library_id+"' and areqdet.invoice_no=nt.invoice_no and areqdet.recieving_no=nt.recieving_no and areqdet.status='Processed' and areqdet.accession_status is null) nnt,acq_bibliography_details abd where abd.control_no=nnt.control_no and abd.library_id='"+library_id+"' and abd.sub_library_id='"+sub_library_id+"') nnnt,acq_bibliography ab where ab.title_id=nnnt.title_id and ab.library_id='"+library_id+"' and ab.sub_library_id='"+sub_library_id+"'";
//          Query query =  session.createSQLQuery(sql)
//                    .setResultTransformer(Transformers.aliasToBean(AcqAccession.class));
//          obj=(List<AcqAccession>) query.list();
//          session.getTransaction().commit();
//        }
//        catch(Exception e){
//        e.printStackTrace();
//        }
//        finally
//        {
//        session.close();
//        }
//        return obj;
//    }


     public List getBiblioFromOpacUpdate(String library_id,String sublibrary_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
     List obj=null;
        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(Demandlist.class)

                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.sublibraryId", sublibrary_id))
            .add(Restrictions.eq("status", "pending"));

           obj= (List) criteria.list();
           session.getTransaction().commit();
        } finally {
            session.close();
        }
        return obj;
    }

 public Demandlist BibliobyMemId(int demand_id, String library_id, String sub_library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Demandlist obj=null;
        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(Demandlist.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.sublibraryId", sub_library_id))
                    .add(Restrictions.eq("id.demandId", demand_id)));
            obj= (Demandlist) criteria.uniqueResult();

            session.getTransaction().commit();
        } finally {
            session.close();
        }
        return obj;
    }

     public List getBiblioFromOpac(String library_id,String sublibrary_id,String search_by, String search_keyword, String sort_by) {
        Session session = HibernateUtil.getSessionFactory().openSession();
         List obj=null;

        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(Demandlist.class)
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.sublibraryId", sublibrary_id))
            .add(Restrictions.eq("status", "pending"));

            obj=(List)criteria.list();
            session.getTransaction().commit();
        } finally {
            session.close();
        }
        return obj;
    }


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
            session.getTransaction().commit();
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
        Session session = HibernateUtil.getSessionFactory().openSession();
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


   public  List<AcqBudgetTransaction> BudgetHeadId(String library_id,String budget_head_id,int con_no) {
        Session session = HibernateUtil.getSessionFactory().openSession();
List<AcqBudgetTransaction> obj=null;
        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(AcqBudgetTransaction.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("acqBudgetHeadId", budget_head_id))
                    .add(Restrictions.eq("controlNo",con_no )));
                   obj=  (List<AcqBudgetTransaction>) criteria.uniqueResult();
                   session.getTransaction().commit();
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

    public   AcqBibliographyDetails BibliobyControlIdonApproval(String library_id,String sub_library_id,int con_no) {
        Session session = HibernateUtil.getSessionFactory().openSession();
AcqBibliographyDetails obj=null;
        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(AcqBibliographyDetails.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.subLibraryId", sub_library_id))
                    .add(Restrictions.eq("id.controlNo",con_no )));

          obj= (AcqBibliographyDetails) criteria.uniqueResult();
          session.getTransaction().commit();
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
        Session session = HibernateUtil.getSessionFactory().openSession();
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
           session.getTransaction().commit();
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
        Session session = HibernateUtil.getSessionFactory().openSession();
 AcqBudgetAllocation obj=null;
        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(AcqBudgetAllocation.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("controlNo",con_no ))
                    .add(Restrictions.eq("amount",x )));
           obj= (AcqBudgetAllocation) criteria.uniqueResult();
           session.getTransaction().commit();
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
session.getTransaction().commit();
            
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

          
           obj= (List<approval_1>)query.list();

            session.getTransaction().commit();

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

    public   List  CheckInReport2(String library_id,String sub_lib)
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
session.getTransaction().commit();
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
 public   List  approvalReport(String library_id,String sub_lib)
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
            session.getTransaction().commit();
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

public   boolean updateAcqBib(AcqBibliographyDetails bibDetails) {
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


   public   boolean updateApproval(AcqApproval app) {
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



    public   boolean updateBudget(AcqBudgetAllocation app) {
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
session.getTransaction().commit();
            
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
session.getTransaction().commit();
          
        }  catch(Exception e){
        e.printStackTrace();
        }
        finally
        {
        session.close();
        }
       return maxbiblio;
    }
           public   AcqApproval searchApproval(String library_id,String sublibrary_id,String approval_no,int control_no) {
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
            session.getTransaction().commit();
        } catch(Exception e){
        e.printStackTrace();
        }
        finally
        {
        session.close();
        }
        return obj;
    }
               public   AcqVendor searchVendor(String library_id,String vendor_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
         AcqVendor obj=null;
        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(AcqVendor.class)

                    .add(Restrictions.eq("id.libraryId", library_id))


                    .add(Restrictions.eq("id.vendorId", vendor_id));

            obj= (AcqVendor) criteria.uniqueResult();
            session.getTransaction().commit();
        } catch(Exception e){
        e.printStackTrace();
        }
        finally
        {
        session.close();
        }
        return obj;
    }
 public   AcqCurrency searchVendorCurrency(String library_id,String SourceCurrency)
{
        Session session = HibernateUtil.getSessionFactory().openSession();
        AcqCurrency obj=null;
        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(AcqCurrency.class)

                    .add(Restrictions.eq("id.libraryId", library_id))


                    .add(Restrictions.eq("sourceCurrency", SourceCurrency));

            obj= (AcqCurrency) criteria.uniqueResult();
            session.getTransaction().commit();
        }  catch(Exception e){
        e.printStackTrace();
        }
        finally
        {
        session.close();
        }
        return obj;
    }

             public   List<AcqApproval> searchApproval2(String library_id,String sublibrary_id,String approval_no) {
        Session session = HibernateUtil.getSessionFactory().openSession();
         List<AcqApproval> obj=null;
        try {
             session.beginTransaction();
            Criteria criteria = session.createCriteria(AcqApproval.class)

                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.subLibraryId", sublibrary_id))
                    .add(Restrictions.eq("approvalNo", approval_no));

            obj=(List<AcqApproval>) criteria.list();
            session.getTransaction().commit();
        }  catch(Exception e){
        e.printStackTrace();
        }
        finally
        {
        session.close();
        }
        return obj;
    }
             public   List<AcqApproval> searchApprovalStatus(String library_id,String sublibrary_id,int control_no) {
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
           session.getTransaction().commit();
        } catch(Exception e){
        e.printStackTrace();
        }
        finally
        {
        session.close();
        }
        return obj;
    }

             public   AcqApproval searchApprovalStatus1(String library_id,String sublibrary_id,int control_no,int approval_item_id) {
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
          session.getTransaction().commit();
        } catch(Exception e){
        e.printStackTrace();
        }
        finally
        {
        session.close();
        }
        return obj;
    }

               public   AcqApproval searchAcqApproval(String library_id,String sublibrary_id,int approval_id)
               {        Session session = HibernateUtil.getSessionFactory().openSession();
       AcqApproval obj=null;

        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(AcqApproval.class)

                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.subLibraryId", sublibrary_id))
                    .add(Restrictions.eq("id.approvalItemId", approval_id)
                    );


            obj= (AcqApproval) criteria.uniqueResult();
            session.getTransaction().commit();
        }  catch(Exception e){
        e.printStackTrace();
        }
        finally
        {
        session.close();
        }
        return obj;
    }


     public   List<AcqOrder1> searchAcqOrder(String library_id,String sublibrary_id,int control_no,String order_no) {
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
                    

           obj= (List<AcqOrder1>) criteria.list();
            session.getTransaction().commit();
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
            obj= (List) criteria.list();
            session.getTransaction().commit();
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
  public   AcqBibliographyDetails searchAcqBibByControlNo(String library_id,String sublibrary_id,int control_no) {
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
            session.getTransaction().commit();
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

   public   AcqBibliographyDetails searchAcqBibPartialByControlNo(String library_id,String sublibrary_id,int control_no) {
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
           session.getTransaction().commit();
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




     public   AcqRecievingHeader searchOrderHeaderByRecievingNo(String library_id,String sublibrary_id,String recieving_no) {
         Session session = HibernateUtil.getSessionFactory().openSession();
       AcqRecievingHeader obj=null;
        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(AcqRecievingHeader.class)
        
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.subLibraryId", sublibrary_id))
                    .add(Restrictions.eq("id.recievingNo", recieving_no))

                    );


            obj= (AcqRecievingHeader)criteria.uniqueResult();
            session.getTransaction().commit();
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
           session.getTransaction().commit();
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

    public   AcqApprovalHeader searchApprovalHeaderByOrderNo(String library_id,String sublibrary_id,String order_no) {
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
            session.getTransaction().commit();
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
           session.getTransaction().commit();
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
           session.getTransaction().commit();
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
            session.getTransaction().commit();
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
           session.getTransaction().commit();
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
            session.getTransaction().commit();
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
            session.getTransaction().commit();
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
            session.getTransaction().commit();
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
                  session.getTransaction().commit();
        }  catch(Exception e){
        e.printStackTrace();
        }
        finally
        {
        session.close();
        }
  return obj;
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
            session.getTransaction().commit();
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
            session.getTransaction().commit();
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
            session.getTransaction().commit();
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
            session.getTransaction().commit();
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
        Session session = HibernateUtil.getSessionFactory().openSession();
 AcqBibliographyDetails obj=null;
        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(AcqBibliographyDetails.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.subLibraryId", sub_library_id))
                    .add(Restrictions.eq("id.controlNo",control_no )));
            obj=(AcqBibliographyDetails) criteria.uniqueResult();
            session.getTransaction().commit();
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
            obj= criteria.list();
            session.getTransaction().commit();
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
           session.getTransaction().commit();
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
            session.getTransaction().commit();
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
            session.getTransaction().commit();
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
              session.getTransaction().commit();
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
            session.getTransaction().commit();
        } catch(Exception e){
        e.printStackTrace();
        }
        finally
        {
        session.close();
        }
        return obj;
    }
  

     public   List<String> searchDoc6(String library_id, String sub_library_id) {
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
            session.getTransaction().commit();
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
            session.getTransaction().commit();
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
            session.getTransaction().commit();
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
            session.getTransaction().commit();
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
            session.getTransaction().commit();
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
            obj= criteria.list();
            session.getTransaction().commit();
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
            session.getTransaction().commit();
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
            session.getTransaction().commit();
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
        session.getTransaction().commit();
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
    

     public   boolean deleteApprovalHeader(String library_id, String sub_library_id,String approval_no) {
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
      return true;
    }
     public   boolean deleteApproval(String library_id, String sub_library_id,String approval_no) {
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
       return true;
    }

     public   AcqOrderHeader searchOrderHeaderByOrderNo(String library_id,String sublibrary_id,String order_no) {
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
            session.getTransaction().commit();
        } catch(Exception e){
        e.printStackTrace();
        }
        finally
        {
        session.close();
        }
        return obj;
    }


      public   ArrayList<AcqRecievingDetails> searchRecieveByRecievingNo(String library_id,String sublibrary_id,String recieving_no) {
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
         session.getTransaction().commit();
        }  catch(Exception e){
        e.printStackTrace();
        }
        finally
        {
        session.close();
        }
        return obj;
    }


  public   ArrayList<AcqOrder1> searchOrderByOrderNo(String library_id,String sublibrary_id,String order_no) {
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
          session.getTransaction().commit();
        }  catch(Exception e){
        e.printStackTrace();
        }
        finally
        {
        session.close();
        }
        return obj;
    }
  public   List<ApprovalList> getViewApprovalList(String library_id, String sub_library_id,String orderno){
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
            session.getTransaction().commit();
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

 public   List<ApprovalList> getViewApprovalList1(String library_id, String sub_library_id,String orderno){
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
            session.getTransaction().commit();
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
