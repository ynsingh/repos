/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.opacDAO;


import com.myapp.struts.CirculationDAO.CirculationDAO;

import com.myapp.struts.hbm.*;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

/**
 *
 * @author edrp02
 */
public class CirRequestfromOpacDAO {
public static CirMemberDetail getMemberId(String library_id,String memid) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            session.beginTransaction();
            Query query = session.createQuery("FROM CirMemberDetail where id.libraryId = :library_id  and id.memId=:mem_id");
            query.setString("library_id",library_id);
            query.setString("mem_id",memid);


            return  (CirMemberDetail)query.uniqueResult();
        }
        finally {

        }

     }

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


 

     public static CirRequestfromOpac getMemberDetail(String library_id,String sublibrary_id,String memid) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            session.beginTransaction();
            Query query = session.createQuery("FROM CirRequestfromOpac where libraryId = :library_id and sublibraryId=:sublibrary_id and memId=:mem_id");
            query.setString("library_id",library_id);
            query.setString("sublibrary_id",sublibrary_id);
            query.setString("mem_id",memid);
            

            return  (CirRequestfromOpac)query.uniqueResult();
        }
        finally {
         
        }

     }



public static  boolean update(CirRequestfromOpac obj)
{
         Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.update(obj);
            tx.commit();
        }
        catch (RuntimeException e) {

                tx.rollback();
                return false;

        }

   return true;

}
  
public static List<CirOpacRequest> checkDuplicateRequest(String library_id,String sublibrary_id,String memid,String docId) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(CirOpacRequest.class);
            criteria.add(Restrictions.eq("id.libraryId", library_id));
            criteria.add(Restrictions.eq("id.sublibraryId", sublibrary_id));
            criteria.add(Restrictions.eq("memid", memid));
            criteria.add(Restrictions.eq("documentId", Integer.parseInt(docId)));
            criteria.add(Restrictions.eq("status", "Pending"));

            return  (List<CirOpacRequest>)criteria.list();
        }
        finally {

        }

     }
      

public static Integer returnMaxRequestId(String library_id, String sublibrary_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            Criteria criteria = session.createCriteria(CirOpacRequest.class);
            Criterion a = Restrictions.eq("id.libraryId", library_id);
            Criterion b = Restrictions.eq("id.sublibraryId", sublibrary_id);
            LogicalExpression le = Restrictions.and(a, b);
            Integer maxrequestId = (Integer) criteria.add(le).setProjection(Projections.max("id.rid")).uniqueResult();
            if (maxrequestId == null) {
                maxrequestId = 1;
            } else {
                maxrequestId++;
            }

            return maxrequestId;
        } finally {
            session.close();
        }

    }

public static boolean SendCheckOutRequest(String memId,String libId, String sublibId, String docId)
{


    
                CirOpacRequestId ciropacReqId = new CirOpacRequestId();
                CirOpacRequest ciropacReq = new CirOpacRequest();
                //getting next value for request Id corresponding to library Id and SubLibrary Id
                Integer rId = CirRequestfromOpacDAO.returnMaxRequestId(libId, sublibId);
                CirMemberDetail memDetail = (CirMemberDetail)CirculationDAO.searchCirMemDetails(libId, memId);
                DocumentDetails dd = (DocumentDetails)CirculationDAO.getDocument(libId, sublibId, Integer.parseInt(docId));
                ciropacReqId.setRid(rId);
                ciropacReqId.setLibraryId(libId);
                ciropacReqId.setSublibraryId(sublibId);

                ciropacReq.setId(ciropacReqId);

                String memName = memDetail.getFname()+" "+ memDetail.getMname()+ " "+memDetail.getLname();
                String accno = dd.getAccessionNo();
                String author = dd.getMainEntry();
                String title = dd.getTitle();
                String callno = dd.getCallNo();

                ciropacReq.setMemid(memId);
                ciropacReq.setMemname(memName);
                ciropacReq.setAccessionNo(accno);
                ciropacReq.setAuthor(author);
                ciropacReq.setCallNo(callno);
                ciropacReq.setDocumentId(Integer.parseInt(docId));
                ciropacReq.setTitle(title);
                ciropacReq.setStatus("Pending");
		ciropacReq.setCirMemberDetail(memDetail);
                boolean flag = CirculationDAO.insert(ciropacReq);
                return flag;
}

public static List<CheckoutDeocumentDetails> getCheckOuts(String library_id,String sublibrary_id,String memid) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            session.beginTransaction();
   session.flush();
            /*Criteria criteria = session.createCriteria(CirOpacRequest.class);
            criteria.add(Restrictions.eq("id.libraryId", library_id));
            criteria.add(Restrictions.eq("id.sublibraryId", sublibrary_id));
            criteria.add(Restrictions.eq("memid", memid));
            criteria.add(Restrictions.eq("status", "issued"));
*/
            Query query1 = session.createSQLQuery("select * from cir_checkout cc,document_details dd where cc.library_id=dd.library_id and cc.sublibrary_id=dd.sublibrary_id and cc.document_id=dd.document_id and cc.memid=:memId and cc.status = 'issued' and cc.library_id=:libraryId and cc.sublibrary_id=:sublibrary_id")
                    .addEntity(CirCheckout.class)
                    .addEntity(DocumentDetails.class)
                    .setResultTransformer(Transformers.aliasToBean(CheckoutDeocumentDetails.class)) ;
                    query1.setParameter("memId", memid);
                    query1.setParameter("libraryId", library_id);
                    query1.setParameter("sublibrary_id", sublibrary_id);
            return  (List<CheckoutDeocumentDetails>)query1.list();
        }
        finally {

        }

     }


}
