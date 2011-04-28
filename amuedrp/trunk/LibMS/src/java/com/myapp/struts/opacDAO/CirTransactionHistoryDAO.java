/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.opacDAO;


import com.myapp.struts.hbm.*;
import com.myapp.struts.opac.MemberFinewithDocument;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

/**
 *
 * @author edrp02
 */
public class CirTransactionHistoryDAO {

  public static  boolean insert(CirRequestfromOpac obj)
{
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;


        try
        {
            tx = (Transaction) session.beginTransaction();

            session.save(obj);
            tx.commit();

            return true;

        }
        catch (Exception ex)
        {
             return false;

      
        }
        finally
        {
          session.close();
        }


}


 

     public static CirTransactionHistory getMemberDetail(String library_id,String sublibrary_id,String memid) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            session.beginTransaction();
            Query query = session.createQuery("FROM CirTransactionHistory where id.libraryId = :library_id and id.sublibraryId=:sublibrary_id and memid=:mem_id");
            query.setString("library_id",library_id);
            query.setString("sublibrary_id",sublibrary_id);
            query.setString("mem_id",memid);
            

            return  (CirTransactionHistory)query.uniqueResult();
        }
        finally {
         
        }

     }

public static List<MemberFinewithDocument> getMemberFineWithDocumentDetail(String library_id,String sublibrary_id,String memid) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        Criterion criterion;
        try {
            session.beginTransaction();


            Query query = session.createSQLQuery("select * from cir_transaction_history cth, document_details dd, cir_checkin cc where cth.library_id = dd.library_id and cth.sublibrary_id = dd.sublibrary_id and cth.checkin_id = cc.checkin_id and cth.document_id = dd.document_id and dd.document_id = cc.document_id and cth.memid = :mem_id and dd.library_id = :library_id and dd.sublibrary_id = :sublibrary_id and cth.fine_amt>0")
                    .addEntity(CirTransactionHistory.class)
                    .addEntity(DocumentDetails.class)
                    .addEntity(CirCheckin.class);


            query.setString("library_id",library_id);
            query.setString("sublibrary_id",sublibrary_id);
            query.setString("mem_id",memid);
            query.setResultTransformer(Transformers.aliasToBean(MemberFinewithDocument.class));

            return  (List<MemberFinewithDocument>)query.list();
        }
        finally {

        }

     }
      



}
