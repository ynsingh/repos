/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.AcquisitionDao;

import  com.myapp.struts.hbm.*;
import  com.myapp.struts.Acquisition.*;
import java.util.ArrayList;
import java.util.Iterator;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author maqbool
 */
public class AcqOrderDao {

    public AcqOrderHeader searchOrderHeaderByVendor(String order_no,String vendor, String library_id, String sub_library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(AcqOrderHeader.class).add(Restrictions.conjunction().add(Restrictions.eq("id.libraryId", library_id)).
                add(Restrictions.eq("id.subLibraryId", sub_library_id)).
                add(Restrictions.eq("id.orderNo",order_no)).
                add(Restrictions.eq("vendorId",vendor))
                );
        //session.close();
        return (AcqOrderHeader) criteria.uniqueResult();
    }


    public ArrayList<ApprovalList> searchOrderByVendor(String order_no, String library_id, String sublibrary_id) {

        Session session =null;
    Transaction tx = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            String sql="";

sql="(select d.isbn,d.title,d.author,ca.no_of_copies,b.control_no from acq_order_header a,acq_order1 b,acq_bibliography_details ca,acq_bibliography d"
 +" where a.library_id=b.library_id and a.sub_library_id=b.sub_library_id "
 + " and a.order_no=b.order_no   and b.library_id=ca.library_id and b.sub_library_id=ca.sub_library_id and b.control_no=ca.control_no and ca.library_id=d.library_id "
+" and ca.sub_library_id=d.sub_library_id and ca.title_id=d.title_id and a.order_no='"+order_no+"' and (b.recieving_status is  null or b.recieving_status = 'Partially Recieved') and b.approval_item_id=0 and a.library_id='"+library_id+"' and a.sub_library_id='"+sublibrary_id+"') "
+" union all (select e.isbn,e.title,e.author,ca.no_of_copies,b.control_no from acq_order_header a,acq_order1 b,acq_approval ca,acq_bibliography_details d,acq_bibliography e where a.library_id=b.library_id and a.sub_library_id=b.sub_library_id and a.order_no=b.order_no and "
+" a.order_no='"+order_no+"' and  (b.recieving_status is  null or b.recieving_status = 'Partially Recieved') and ca.approval_item_id>0 and a.order_status!='cancel'  and b.library_id=ca.library_id and b.sub_library_id=ca.sub_library_id and "
+" b.control_no=ca.control_no and b.order_no=ca.order_no and ca.library_id=d.library_id and ca.sub_library_id=d.sub_library_id and ca.control_no=d.control_no "
+" and d.library_id=e.library_id and d.sub_library_id=e.sub_library_id and d.title_id=e.title_id and ca.approval_item_id>0 and a.library_id='"+library_id+"' and a.sub_library_id='"+sublibrary_id+"')";


 Query query =  session.createSQLQuery(sql)

                    .setResultTransformer(Transformers.aliasToBean(ApprovalList.class));
            return (ArrayList<ApprovalList>)query.list();
        }
        finally {
            session.close();
        }



    }




     public ArrayList<ApprovalList> searchOrderByVendor1(String order_no, String library_id, String sub_library_id, String recieving_no) {

        Session session =null;
    Transaction tx = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            String sql="";

sql="(select a.recieving_no,a.order_no,a.recieved_by,a.vendor_id,a.recieved_date,d.isbn,d.title,d.author,b.recieved_copies,b.control_no "
 +" from acq_recieving_header a,acq_recieving_details b,acq_bibliography_details ca,acq_bibliography d where a.library_id=b.library_id and a.sub_library_id=b.sub_library_id "
+" and a.recieving_no=b.recieving_no and b.library_id=ca.library_id and b.sub_library_id=ca.sub_library_id and b.control_no=ca.control_no  and "
+" ca.library_id=d.library_id and ca.sub_library_id=d.sub_library_id and ca.title_id=d.title_id  and "
+" a.library_id='"+library_id+"' and a.sub_library_id='"+sub_library_id+"' and a.order_no='"+order_no+"' and a.recieving_no='"+recieving_no+"')";

 Query query =  session.createSQLQuery(sql)

                    .setResultTransformer(Transformers.aliasToBean(ApprovalList.class));
            return (ArrayList<ApprovalList>)query.list();
        }
        finally {
            session.close();
        }



    }



     public static String getApprovalItemId(String library_id,String sublibrary_id,int control_no,String approval_no) {
        Session sess = null;
            String amount=null;
    int count = 0;
    try {
      SessionFactory fact = new Configuration().configure().buildSessionFactory();
      sess = fact.openSession();
      String SQL_QUERY = "select id.approvalItemId from AcqApproval a where a.id.libraryId='"+library_id+"' and a.id.subLibraryId='"+sublibrary_id+"' and a.controlNo="+control_no+" and a.approvalNo='"+approval_no+"'";
        Query query = sess.createQuery(SQL_QUERY);
        for (Iterator it = query.iterate(); it.hasNext();) {
          it.next();
            count++;
        }
        System.out.println("Total rows: " + count);
        Iterator it = query.iterate(); it.hasNext();
     amount=query.uniqueResult().toString();
        System.out.println("Total rows1: " + amount);

      sess.close();
    }
    catch(Exception e){
      System.out.println
(e.getMessage());
    }
     return amount;
}


    public boolean insert(AcqOrderHeader bibDetails) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(bibDetails);
            tx.commit();
        } catch (Exception e) {
            //if(bibDetails != null)
            tx.rollback();
            System.out.println("EXXXXXXXXXXXXX"+e);
return false;
            //throw e;
        } finally {
        //    session.close();

        }
    return  true;
    }

     public void insert1(AcqOrder1 bibDetails) {
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
          //  session.close();
        }
    }

     public PurchaseOrderClass getOrderList(String library_id, String sublibrary_id, int control_no,String order_no){
  Session session =null;
    Transaction tx = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            String sql="";

            sql = "select * from acq_bibliography where acq_bibliography.title_id in (select b.title_id from acq_order1 a inner join acq_bibliography_details b on a.library_id=b.library_id and a.sub_library_id=b.sub_library_id and a.control_no=b.control_no where a.control_no="+control_no+" and a.order_no='"+order_no+"' and a.library_id='"+library_id+"' and a.sub_library_id='"+sublibrary_id+"')";
            System.out.println(sql);

          Query query =  session.createSQLQuery(sql)
                    .addEntity(AcqOrder1.class)
                    .addEntity(AcqBibliography.class)
                    .addEntity(AcqBibliographyDetails.class)
                    .setResultTransformer(Transformers.aliasToBean(PurchaseOrderClass.class));
            return (PurchaseOrderClass)query.uniqueResult();
        }
        finally {
            session.close();
        }
}

 public List<AcqOrder1> searchByOrderno(String order_no, String library_id, String sub_library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(AcqOrder1.class).add(Restrictions.conjunction().add(Restrictions.eq("id.libraryId", library_id)).add(Restrictions.eq("id.subLibraryId", sub_library_id)).add(Restrictions.eq("id.orderNo",order_no)));
        //session.close();
        return (List<AcqOrder1>) criteria.list();
    }

 public AcqOrderHeader search1Orderno(String order_no, String library_id, String sub_library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(AcqOrderHeader.class).add(Restrictions.conjunction().add(Restrictions.eq("id.libraryId", library_id)).add(Restrictions.eq("id.subLibraryId", sub_library_id)).add(Restrictions.eq("id.orderNo",order_no)));
        //session.close();
        return (AcqOrderHeader) criteria.uniqueResult();
    }

 public AcqOrderHeader search1Orderno1(String order_no, String library_id, String sub_library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(AcqOrderHeader.class).add(Restrictions.conjunction().add(Restrictions.eq("id.libraryId", library_id)).add(Restrictions.eq("orderStatus", "Placed")).add(Restrictions.eq("id.subLibraryId", sub_library_id)).add(Restrictions.eq("id.orderNo",order_no)));
        //session.close();
        return (AcqOrderHeader) criteria.uniqueResult();
    }

 public AcqOrder1 search2Orderno(String order_no, String library_id, String sub_library_id,String order_item_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(AcqOrder1.class)
                .add(Restrictions.conjunction().add(Restrictions.eq("id.libraryId", library_id))
                .add(Restrictions.eq("id.subLibraryId", sub_library_id))
                .add(Restrictions.eq("id.orderNo",order_no))
           
                .add(Restrictions.eq("id.orderItemId",order_item_id)));
        //session.close();
        return (AcqOrder1) criteria.uniqueResult();
    }


 public AcqBibliographyDetails searchByControlNo(String library_id, String sub_library_id,int con_no) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(AcqBibliographyDetails.class).add(Restrictions.conjunction().add(Restrictions.eq("id.libraryId", library_id)).add(Restrictions.eq("id.subLibraryId", sub_library_id)).add(Restrictions.eq("id.controlNo",con_no)));
        
        return (AcqBibliographyDetails) criteria.uniqueResult();
    }

public static void updateAcqBibliographyDetails(AcqBibliographyDetails obj) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.update(obj);
            tx.commit();
        } catch (RuntimeException e) {

            tx.rollback();
        //    return false;

        }

   //     return true;

    }




public static void updateAcqOrder1(AcqOrder1 obj) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.update(obj);
            tx.commit();
        } catch (RuntimeException e) {

            tx.rollback();
        //    return false;

        }

   //     return true;

    }

public static boolean updateAcqOrderHeader(AcqOrderHeader obj) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.update(obj);
            tx.commit();
        } catch (RuntimeException e) {
System.out.println(e);
            tx.rollback();
           return false;

        }
//session.close();
        return true;

    }
public static boolean updateAcqApproval(AcqApproval obj) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.update(obj);
            tx.commit();
        } catch (RuntimeException e) {
System.out.println(e);
            tx.rollback();
           return false;

        }
session.close();
        return true;

    }


  public Integer returnMaxItemId(String library_id, String sublibrary_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
         Transaction tx=session.beginTransaction();
        try {
            Criteria criteria = session.createCriteria(AcqApproval.class);
            Criterion a = Restrictions.eq("id.libraryId", library_id);
            Criterion b = Restrictions.eq("id.subLibraryId", sublibrary_id);
            LogicalExpression le = Restrictions.and(a, b);
            Integer maxbiblio = (Integer) criteria.add(le).setProjection(Projections.max("id.orderItemId")).uniqueResult();
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
      public Integer returnMaxItemIdAcqOrder(String library_id, String sublibrary_id) {



        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            Criteria criteria = session.createCriteria(AcqOrder1.class);
            Criterion a = Restrictions.eq("id.libraryId", library_id);
            Criterion b = Restrictions.eq("id.subLibraryId", sublibrary_id);
            LogicalExpression le = Restrictions.and(a, b);
            Integer maxbiblio = criteria.add(le).setProjection(Projections.count("id.orderItemId")).uniqueResult()==null?0:Integer.valueOf(criteria.add(le).setProjection(Projections.count("id.orderItemId")).uniqueResult().toString());
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

      public static boolean  delete1(String library_id,String sub_library_id,String order_no) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = (Transaction) session.beginTransaction();
            Query query = session.createQuery("Delete From  AcqOrderHeader where id.libraryId = :library_id and  id.orderNo = :orderNo and id.subLibraryId = :subLibraryId");
           query.setString("library_id", library_id);
           query.setString("subLibraryId", sub_library_id);
            query.setString("orderNo", order_no);
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
 

}
