/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.Acquisition;

//import com.myapp.struts.Library;
import com.myapp.struts.hbm.AcqFinalDemandList;
import com.myapp.struts.hbm.AcqOrder1;
import com.myapp.struts.hbm.AcqOrderHeader;
import com.myapp.struts.hbm.AcqVendor;
import com.myapp.struts.hbm.HibernateUtil;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author SONU
 */
public class ReportDao {

    public static List<String> getReport() {
        Session session = HibernateUtil.getSessionFactory().openSession();
       
        try {
            session.beginTransaction();
            System.out.println("zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz");
            Query query = session.createQuery("Select distinct staffId From Library");
System.out.println("mmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm");
            return   query.list();
        } finally {
            session.close();
        }
    }
    

 public static List<AcqOrder1> getList1() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();
            Query query = session.createQuery("From AcqOrder1");


            return (List<AcqOrder1>) query.list() ;
        } finally {
            session.close();
        }
    }

  public static List<AcqFinalDemandList> getList4() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();
          Query query = session.createQuery("From AcqFinalDemandList order by id.controlNo");
            



            return (List<AcqFinalDemandList>) query.list() ;
        } finally {
            session.close();
        }
    }


 public static List<AcqOrderHeader> getList2(String orderno) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();
            Query query = session.createQuery("From AcqOrderHeader WHERE id.orderNo=:order_no ");
query.setString("order_no",orderno);

            return (List<AcqOrderHeader>) query.list();
        } finally {
            session.close();
        }
    }
public static List<AcqOrderHeader> getListSum(String orderno) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();
            Query query = session.createQuery("From AcqOrderHeader WHERE id.orderNo=:order_no ");
            //Criteria cr = new Criteria() {}
query.setString("order_no",orderno);

            return (List<AcqOrderHeader>) query.list();
        } finally {
            session.close();
        }
    }
 public static List<AcqVendor> getList3(String library_id,String sublibrary_id,String vendor_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();
           Query query = session.createQuery("From AcqVendor  where  id.libraryId=:library_id and id.subLibraryId=:sublibrary_id and id.vendorId=:vendor_id");
           query.setString("vendor_id", vendor_id);
           query.setString("library_id", library_id);
           query.setString("sublibrary_id", sublibrary_id);
  
            return (List<AcqVendor>) query.list() ;
        } finally {
            session.close();
        }
    }


}
